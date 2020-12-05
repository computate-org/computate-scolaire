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
import org.computate.scolaire.enUS.season.SchoolSeason;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.year.SchoolYear;
import java.math.RoundingMode;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SchoolYearGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolYear.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
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
	public static final String SchoolYear_Title = "years";
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Find the entity schoolKey in Solr</a>
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
		this.schoolKey = SchoolYear.staticSetSchoolKey(siteRequest_, o);
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSchoolKey(siteRequest_, SchoolYear.staticSolrSchoolKey(siteRequest_, SchoolYear.staticSetSchoolKey(siteRequest_, o)));
	}

	public Long solrSchoolKey() {
		return SchoolYear.staticSolrSchoolKey(siteRequest_, schoolKey);
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			if("PUTCopy".equals(classApiMethodMethod)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classApiMethodMethod, "_schoolKey_clear")
						.a("class", "schoolKey_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_schoolKey_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "school")
				.a("class", "valueObjectSuggest suggestSchoolKey w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setSchoolKey")
				.a("id", classApiMethodMethod, "_schoolKey")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolYearSchoolKey($(this).val() ? searchSchoolFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'yearKeys:" + pk + "'}", "], $('#listSchoolYearSchoolKey_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolYear", pk, "SchoolKey ").f().sx(htmSchoolKey()).g("span");
			}
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
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
												.a("id", classApiMethodMethod, "_schoolKey_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolVals({ yearKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "schoolKey')); });")
												.f().sx("add a school")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Find the entity yearKey in Solr</a>
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
		this.yearKey = SchoolYear.staticSetYearKey(siteRequest_, o);
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetYearKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrYearKey(siteRequest_, SchoolYear.staticSolrYearKey(siteRequest_, SchoolYear.staticSetYearKey(siteRequest_, o)));
	}

	public Long solrYearKey() {
		return SchoolYear.staticSolrYearKey(siteRequest_, yearKey);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Find the entity enrollmentKeys in Solr</a>
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
		Long l = SchoolYear.staticSetEnrollmentKeys(siteRequest_, o);
		if(l != null)
			addEnrollmentKeys(l);
		this.enrollmentKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
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

	public static Long staticSolrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrEnrollmentKeys(siteRequest_, SchoolYear.staticSolrEnrollmentKeys(siteRequest_, SchoolYear.staticSetEnrollmentKeys(siteRequest_, o)));
	}

	public List<Long> solrEnrollmentKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : enrollmentKeys) {
			l.add(SchoolYear.staticSolrEnrollmentKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKeys">Find the entity seasonKeys in Solr</a>
	 * <br/>
	 * @param seasonKeys is the entity already constructed. 
	 **/
	protected abstract void _seasonKeys(List<Long> o);

	public List<Long> getSeasonKeys() {
		return seasonKeys;
	}

	public void setSeasonKeys(List<Long> seasonKeys) {
		this.seasonKeys = seasonKeys;
		this.seasonKeysWrap.alreadyInitialized = true;
	}
	public void setSeasonKeys(String o) {
		Long l = SchoolYear.staticSetSeasonKeys(siteRequest_, o);
		if(l != null)
			addSeasonKeys(l);
		this.seasonKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetSeasonKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setSeasonKeys(JsonArray objets) {
		seasonKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSeasonKeys(o);
		}
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

	public static Long staticSolrSeasonKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSeasonKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSeasonKeys(siteRequest_, SchoolYear.staticSolrSeasonKeys(siteRequest_, SchoolYear.staticSetSeasonKeys(siteRequest_, o)));
	}

	public List<Long> solrSeasonKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : seasonKeys) {
			l.add(SchoolYear.staticSolrSeasonKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKeys">Find the entity ageKeys in Solr</a>
	 * <br/>
	 * @param ageKeys is the entity already constructed. 
	 **/
	protected abstract void _ageKeys(List<Long> o);

	public List<Long> getAgeKeys() {
		return ageKeys;
	}

	public void setAgeKeys(List<Long> ageKeys) {
		this.ageKeys = ageKeys;
		this.ageKeysWrap.alreadyInitialized = true;
	}
	public void setAgeKeys(String o) {
		Long l = SchoolYear.staticSetAgeKeys(siteRequest_, o);
		if(l != null)
			addAgeKeys(l);
		this.ageKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetAgeKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setAgeKeys(JsonArray objets) {
		ageKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeKeys(o);
		}
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

	public static Long staticSolrAgeKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrAgeKeys(siteRequest_, SchoolYear.staticSolrAgeKeys(siteRequest_, SchoolYear.staticSetAgeKeys(siteRequest_, o)));
	}

	public List<Long> solrAgeKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : ageKeys) {
			l.add(SchoolYear.staticSolrAgeKeys(siteRequest_, o));
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

	public void inputAgeKeys(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			if("PUTCopy".equals(classApiMethodMethod)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classApiMethodMethod, "_ageKeys_clear")
						.a("class", "ageKeys_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_ageKeys_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "ages")
				.a("class", "valueObjectSuggest suggestAgeKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setAgeKeys")
				.a("id", classApiMethodMethod, "_ageKeys")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolYearAgeKeys($(this).val() ? searchSchoolAgeFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'yearKey:" + pk + "'}", "], $('#listSchoolYearAgeKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolYear", pk, "AgeKeys ").f().sx(htmAgeKeys()).g("span");
			}
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
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
												.a("id", classApiMethodMethod, "_ageKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolAgeVals({ yearKey: \"", pk, "\" }, function() {}, function() { addError($('#", classApiMethodMethod, "ageKeys')); });")
												.f().sx("add an age")
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

	///////////////////
	// educationSort //
	///////////////////

	/**	 The entity educationSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer educationSort;
	@JsonIgnore
	public Wrap<Integer> educationSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("educationSort").o(educationSort);

	/**	<br/> The entity educationSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Find the entity educationSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _educationSort(Wrap<Integer> c);

	public Integer getEducationSort() {
		return educationSort;
	}

	public void setEducationSort(Integer educationSort) {
		this.educationSort = educationSort;
		this.educationSortWrap.alreadyInitialized = true;
	}
	public void setEducationSort(String o) {
		this.educationSort = SchoolYear.staticSetEducationSort(siteRequest_, o);
		this.educationSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetEducationSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEducationSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrEducationSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEducationSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrEducationSort(siteRequest_, SchoolYear.staticSolrEducationSort(siteRequest_, SchoolYear.staticSetEducationSort(siteRequest_, o)));
	}

	public Integer solrEducationSort() {
		return SchoolYear.staticSolrEducationSort(siteRequest_, educationSort);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Find the entity schoolSort in Solr</a>
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
		this.schoolSort = SchoolYear.staticSetSchoolSort(siteRequest_, o);
		this.schoolSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSchoolSort(siteRequest_, SchoolYear.staticSolrSchoolSort(siteRequest_, SchoolYear.staticSetSchoolSort(siteRequest_, o)));
	}

	public Integer solrSchoolSort() {
		return SchoolYear.staticSolrSchoolSort(siteRequest_, schoolSort);
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

	/**	 The entity yearSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearSort;
	@JsonIgnore
	public Wrap<Integer> yearSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearSort").o(yearSort);

	/**	<br/> The entity yearSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSort">Find the entity yearSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearSort(Wrap<Integer> c);

	public Integer getYearSort() {
		return yearSort;
	}

	public void setYearSort(Integer yearSort) {
		this.yearSort = yearSort;
		this.yearSortWrap.alreadyInitialized = true;
	}
	public void setYearSort(String o) {
		this.yearSort = SchoolYear.staticSetYearSort(siteRequest_, o);
		this.yearSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrYearSort(siteRequest_, SchoolYear.staticSolrYearSort(siteRequest_, SchoolYear.staticSetYearSort(siteRequest_, o)));
	}

	public Integer solrYearSort() {
		return SchoolYear.staticSolrYearSort(siteRequest_, yearSort);
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

	/**	 The entity schoolSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<School>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<School> schoolSearch = new SearchList<School>();
	@JsonIgnore
	public Wrap<SearchList<School>> schoolSearchWrap = new Wrap<SearchList<School>>().p(this).c(SearchList.class).var("schoolSearch").o(schoolSearch);

	/**	<br/> The entity schoolSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<School>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSearch">Find the entity schoolSearch in Solr</a>
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

	/**	 The entity school_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected School school_;
	@JsonIgnore
	public Wrap<School> school_Wrap = new Wrap<School>().p(this).c(School.class).var("school_").o(school_);

	/**	<br/> The entity school_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:school_">Find the entity school_ in Solr</a>
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

	/**	 The entity schoolName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolName;
	@JsonIgnore
	public Wrap<String> schoolNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolName").o(schoolName);

	/**	<br/> The entity schoolName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Find the entity schoolName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolName(Wrap<String> c);

	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String o) {
		this.schoolName = SchoolYear.staticSetSchoolName(siteRequest_, o);
		this.schoolNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSchoolName(siteRequest_, SchoolYear.staticSolrSchoolName(siteRequest_, SchoolYear.staticSetSchoolName(siteRequest_, o)));
	}

	public String solrSchoolName() {
		return SchoolYear.staticSolrSchoolName(siteRequest_, schoolName);
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

	/**	 The entity schoolCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolCompleteName;
	@JsonIgnore
	public Wrap<String> schoolCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolCompleteName").o(schoolCompleteName);

	/**	<br/> The entity schoolCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Find the entity schoolCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolCompleteName(Wrap<String> c);

	public String getSchoolCompleteName() {
		return schoolCompleteName;
	}
	public void setSchoolCompleteName(String o) {
		this.schoolCompleteName = SchoolYear.staticSetSchoolCompleteName(siteRequest_, o);
		this.schoolCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSchoolCompleteName(siteRequest_, SchoolYear.staticSolrSchoolCompleteName(siteRequest_, SchoolYear.staticSetSchoolCompleteName(siteRequest_, o)));
	}

	public String solrSchoolCompleteName() {
		return SchoolYear.staticSolrSchoolCompleteName(siteRequest_, schoolCompleteName);
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

	/**	 The entity schoolLocation
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation;
	@JsonIgnore
	public Wrap<String> schoolLocationWrap = new Wrap<String>().p(this).c(String.class).var("schoolLocation").o(schoolLocation);

	/**	<br/> The entity schoolLocation
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Find the entity schoolLocation in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolLocation(Wrap<String> c);

	public String getSchoolLocation() {
		return schoolLocation;
	}
	public void setSchoolLocation(String o) {
		this.schoolLocation = SchoolYear.staticSetSchoolLocation(siteRequest_, o);
		this.schoolLocationWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSchoolLocation(siteRequest_, SchoolYear.staticSolrSchoolLocation(siteRequest_, SchoolYear.staticSetSchoolLocation(siteRequest_, o)));
	}

	public String solrSchoolLocation() {
		return SchoolYear.staticSolrSchoolLocation(siteRequest_, schoolLocation);
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

	/**	 The entity schoolAddress
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAddress;
	@JsonIgnore
	public Wrap<String> schoolAddressWrap = new Wrap<String>().p(this).c(String.class).var("schoolAddress").o(schoolAddress);

	/**	<br/> The entity schoolAddress
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Find the entity schoolAddress in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAddress(Wrap<String> c);

	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String o) {
		this.schoolAddress = SchoolYear.staticSetSchoolAddress(siteRequest_, o);
		this.schoolAddressWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSchoolAddress(siteRequest_, SchoolYear.staticSolrSchoolAddress(siteRequest_, SchoolYear.staticSetSchoolAddress(siteRequest_, o)));
	}

	public String solrSchoolAddress() {
		return SchoolYear.staticSolrSchoolAddress(siteRequest_, schoolAddress);
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

	/**	 The entity schoolPhoneNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolPhoneNumber;
	@JsonIgnore
	public Wrap<String> schoolPhoneNumberWrap = new Wrap<String>().p(this).c(String.class).var("schoolPhoneNumber").o(schoolPhoneNumber);

	/**	<br/> The entity schoolPhoneNumber
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Find the entity schoolPhoneNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolPhoneNumber(Wrap<String> c);

	public String getSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}
	public void setSchoolPhoneNumber(String o) {
		this.schoolPhoneNumber = SchoolYear.staticSetSchoolPhoneNumber(siteRequest_, o);
		this.schoolPhoneNumberWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSchoolPhoneNumber(siteRequest_, SchoolYear.staticSolrSchoolPhoneNumber(siteRequest_, SchoolYear.staticSetSchoolPhoneNumber(siteRequest_, o)));
	}

	public String solrSchoolPhoneNumber() {
		return SchoolYear.staticSolrSchoolPhoneNumber(siteRequest_, schoolPhoneNumber);
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

	////////////////
	// schoolForm //
	////////////////

	/**	 The entity schoolForm
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolForm;
	@JsonIgnore
	public Wrap<String> schoolFormWrap = new Wrap<String>().p(this).c(String.class).var("schoolForm").o(schoolForm);

	/**	<br/> The entity schoolForm
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolForm">Find the entity schoolForm in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolForm(Wrap<String> c);

	public String getSchoolForm() {
		return schoolForm;
	}
	public void setSchoolForm(String o) {
		this.schoolForm = SchoolYear.staticSetSchoolForm(siteRequest_, o);
		this.schoolFormWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolYear schoolFormInit() {
		if(!schoolFormWrap.alreadyInitialized) {
			_schoolForm(schoolFormWrap);
			if(schoolForm == null)
				setSchoolForm(schoolFormWrap.o);
		}
		schoolFormWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public static String staticSolrSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSchoolForm(siteRequest_, SchoolYear.staticSolrSchoolForm(siteRequest_, SchoolYear.staticSetSchoolForm(siteRequest_, o)));
	}

	public String solrSchoolForm() {
		return SchoolYear.staticSolrSchoolForm(siteRequest_, schoolForm);
	}

	public String strSchoolForm() {
		return schoolForm == null ? "" : schoolForm;
	}

	public String jsonSchoolForm() {
		return schoolForm == null ? "" : schoolForm;
	}

	public String nomAffichageSchoolForm() {
		return null;
	}

	public String htmTooltipSchoolForm() {
		return null;
	}

	public String htmSchoolForm() {
		return schoolForm == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolForm());
	}

	//////////////////
	// schoolNumber //
	//////////////////

	/**	 The entity schoolNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer schoolNumber;
	@JsonIgnore
	public Wrap<Integer> schoolNumberWrap = new Wrap<Integer>().p(this).c(Integer.class).var("schoolNumber").o(schoolNumber);

	/**	<br/> The entity schoolNumber
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolNumber">Find the entity schoolNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolNumber(Wrap<Integer> c);

	public Integer getSchoolNumber() {
		return schoolNumber;
	}

	public void setSchoolNumber(Integer schoolNumber) {
		this.schoolNumber = schoolNumber;
		this.schoolNumberWrap.alreadyInitialized = true;
	}
	public void setSchoolNumber(String o) {
		this.schoolNumber = SchoolYear.staticSetSchoolNumber(siteRequest_, o);
		this.schoolNumberWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolNumber(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSchoolNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSchoolNumber(siteRequest_, SchoolYear.staticSolrSchoolNumber(siteRequest_, SchoolYear.staticSetSchoolNumber(siteRequest_, o)));
	}

	public Integer solrSchoolNumber() {
		return SchoolYear.staticSolrSchoolNumber(siteRequest_, schoolNumber);
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

	/**	 The entity schoolAdministratorName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAdministratorName;
	@JsonIgnore
	public Wrap<String> schoolAdministratorNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolAdministratorName").o(schoolAdministratorName);

	/**	<br/> The entity schoolAdministratorName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Find the entity schoolAdministratorName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAdministratorName(Wrap<String> c);

	public String getSchoolAdministratorName() {
		return schoolAdministratorName;
	}
	public void setSchoolAdministratorName(String o) {
		this.schoolAdministratorName = SchoolYear.staticSetSchoolAdministratorName(siteRequest_, o);
		this.schoolAdministratorNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSchoolAdministratorName(siteRequest_, SchoolYear.staticSolrSchoolAdministratorName(siteRequest_, SchoolYear.staticSetSchoolAdministratorName(siteRequest_, o)));
	}

	public String solrSchoolAdministratorName() {
		return SchoolYear.staticSolrSchoolAdministratorName(siteRequest_, schoolAdministratorName);
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

	/**	 The entity enrollmentFormKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long enrollmentFormKey;
	@JsonIgnore
	public Wrap<Long> enrollmentFormKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("enrollmentFormKey").o(enrollmentFormKey);

	/**	<br/> The entity enrollmentFormKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentFormKey">Find the entity enrollmentFormKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentFormKey(Wrap<Long> c);

	public Long getEnrollmentFormKey() {
		return enrollmentFormKey;
	}

	public void setEnrollmentFormKey(Long enrollmentFormKey) {
		this.enrollmentFormKey = enrollmentFormKey;
		this.enrollmentFormKeyWrap.alreadyInitialized = true;
	}
	public void setEnrollmentFormKey(String o) {
		this.enrollmentFormKey = SchoolYear.staticSetEnrollmentFormKey(siteRequest_, o);
		this.enrollmentFormKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentFormKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrEnrollmentFormKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentFormKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentFormKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrEnrollmentFormKey(siteRequest_, SchoolYear.staticSolrEnrollmentFormKey(siteRequest_, SchoolYear.staticSetEnrollmentFormKey(siteRequest_, o)));
	}

	public Long solrEnrollmentFormKey() {
		return SchoolYear.staticSolrEnrollmentFormKey(siteRequest_, enrollmentFormKey);
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

	/**	 The entity sessionStartDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionStartDate;
	@JsonIgnore
	public Wrap<LocalDate> sessionStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionStartDate").o(sessionStartDate);

	/**	<br/> The entity sessionStartDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionStartDate">Find the entity sessionStartDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionStartDate(Wrap<LocalDate> c);

	public LocalDate getSessionStartDate() {
		return sessionStartDate;
	}

	public void setSessionStartDate(LocalDate sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	public void setSessionStartDate(Instant o) {
		this.sessionStartDate = o == null ? null : LocalDate.from(o);
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSessionStartDate(String o) {
		this.sessionStartDate = SchoolYear.staticSetSessionStartDate(siteRequest_, o);
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSessionStartDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionStartDate(Date o) {
		this.sessionStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionStartDateWrap.alreadyInitialized = true;
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

	public static Date staticSolrSessionStartDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionStartDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionStartDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSessionStartDate(siteRequest_, SchoolYear.staticSolrSessionStartDate(siteRequest_, SchoolYear.staticSetSessionStartDate(siteRequest_, o)));
	}

	public Date solrSessionStartDate() {
		return SchoolYear.staticSolrSessionStartDate(siteRequest_, sessionStartDate);
	}

	public String strSessionStartDate() {
		return sessionStartDate == null ? "" : sessionStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonSessionStartDate() {
		return sessionStartDate == null ? "" : sessionStartDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSessionStartDate() {
		return "start of the year";
	}

	public String htmTooltipSessionStartDate() {
		return null;
	}

	public String htmSessionStartDate() {
		return sessionStartDate == null ? "" : StringEscapeUtils.escapeHtml4(strSessionStartDate());
	}

	public void inputSessionStartDate(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setSessionStartDate classSchoolYear inputSchoolYear", pk, "SessionStartDate w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_sessionStartDate")
					.a("value", sessionStartDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(sessionStartDate));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSessionStartDate', s, function() { addGlow($('#", classApiMethodMethod, "_sessionStartDate')); }, function() { addError($('#", classApiMethodMethod, "_sessionStartDate')); }); } ");
			}
			fg();
		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolYear", pk, "SessionStartDate ").f().sx(htmSessionStartDate()).g("span");
			}
		}
	}

	public void htmSessionStartDate(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolYearSessionStartDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classApiMethodMethod, "_sessionStartDate").a("class", "").f().sx("start of the year").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputSessionStartDate(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Find the entity seasonStartDate in Solr</a>
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
		this.seasonStartDate = SchoolYear.staticSetSeasonStartDate(siteRequest_, o);
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSeasonStartDate(Date o) {
		this.seasonStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	protected SchoolYear seasonStartDateInit() {
		if(!seasonStartDateWrap.alreadyInitialized) {
			_seasonStartDate(seasonStartDateWrap);
			if(seasonStartDate == null)
				setSeasonStartDate(seasonStartDateWrap.o);
		}
		seasonStartDateWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public static Date staticSolrSeasonStartDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSeasonStartDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSeasonStartDate(siteRequest_, SchoolYear.staticSolrSeasonStartDate(siteRequest_, SchoolYear.staticSetSeasonStartDate(siteRequest_, o)));
	}

	public Date solrSeasonStartDate() {
		return SchoolYear.staticSolrSeasonStartDate(siteRequest_, seasonStartDate);
	}

	public String strSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSeasonStartDate() {
		return "start of the season";
	}

	public String htmTooltipSeasonStartDate() {
		return null;
	}

	public String htmSeasonStartDate() {
		return seasonStartDate == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonStartDate());
	}

	public void inputSeasonStartDate(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setSeasonStartDate classSchoolYear inputSchoolYear", pk, "SeasonStartDate w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_seasonStartDate")
					.a("value", seasonStartDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(seasonStartDate));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSeasonStartDate', s, function() { addGlow($('#", classApiMethodMethod, "_seasonStartDate')); }, function() { addError($('#", classApiMethodMethod, "_seasonStartDate')); }); } ");
			}
			fg();
		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolYear", pk, "SeasonStartDate ").f().sx(htmSeasonStartDate()).g("span");
			}
		}
	}

	public void htmSeasonStartDate(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolYearSeasonStartDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classApiMethodMethod, "_seasonStartDate").a("class", "").f().sx("start of the season").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputSeasonStartDate(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_seasonStartDate')); $('#", classApiMethodMethod, "_seasonStartDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolYearForm :input[name=pk]').val() }], 'setSeasonStartDate', null, function() { addGlow($('#", classApiMethodMethod, "_seasonStartDate')); }, function() { addError($('#", classApiMethodMethod, "_seasonStartDate')); }); ")
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

	/**	 The entity sessionEndDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionEndDate;
	@JsonIgnore
	public Wrap<LocalDate> sessionEndDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionEndDate").o(sessionEndDate);

	/**	<br/> The entity sessionEndDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionEndDate">Find the entity sessionEndDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionEndDate(Wrap<LocalDate> c);

	public LocalDate getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(LocalDate sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	public void setSessionEndDate(Instant o) {
		this.sessionEndDate = o == null ? null : LocalDate.from(o);
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSessionEndDate(String o) {
		this.sessionEndDate = SchoolYear.staticSetSessionEndDate(siteRequest_, o);
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSessionEndDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionEndDate(Date o) {
		this.sessionEndDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionEndDateWrap.alreadyInitialized = true;
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

	public static Date staticSolrSessionEndDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionEndDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionEndDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrSessionEndDate(siteRequest_, SchoolYear.staticSolrSessionEndDate(siteRequest_, SchoolYear.staticSetSessionEndDate(siteRequest_, o)));
	}

	public Date solrSessionEndDate() {
		return SchoolYear.staticSolrSessionEndDate(siteRequest_, sessionEndDate);
	}

	public String strSessionEndDate() {
		return sessionEndDate == null ? "" : sessionEndDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonSessionEndDate() {
		return sessionEndDate == null ? "" : sessionEndDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSessionEndDate() {
		return "end of the year";
	}

	public String htmTooltipSessionEndDate() {
		return null;
	}

	public String htmSessionEndDate() {
		return sessionEndDate == null ? "" : StringEscapeUtils.escapeHtml4(strSessionEndDate());
	}

	public void inputSessionEndDate(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setSessionEndDate classSchoolYear inputSchoolYear", pk, "SessionEndDate w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_sessionEndDate")
					.a("value", sessionEndDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(sessionEndDate));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSessionEndDate', s, function() { addGlow($('#", classApiMethodMethod, "_sessionEndDate')); }, function() { addError($('#", classApiMethodMethod, "_sessionEndDate')); }); } ");
			}
			fg();
		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolYear", pk, "SessionEndDate ").f().sx(htmSessionEndDate()).g("span");
			}
		}
	}

	public void htmSessionEndDate(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolYearSessionEndDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classApiMethodMethod, "_sessionEndDate").a("class", "").f().sx("end of the year").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputSessionEndDate(classApiMethodMethod);
							} g("div");
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Find the entity yearStart in Solr</a>
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
		this.yearStart = SchoolYear.staticSetYearStart(siteRequest_, o);
		this.yearStartWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearStart(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearStart(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrYearStart(siteRequest_, SchoolYear.staticSolrYearStart(siteRequest_, SchoolYear.staticSetYearStart(siteRequest_, o)));
	}

	public Integer solrYearStart() {
		return SchoolYear.staticSolrYearStart(siteRequest_, yearStart);
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
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

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolYear", pk, "YearStart ").f().sx(htmYearStart()).g("span");
			}
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
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Find the entity yearEnd in Solr</a>
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
		this.yearEnd = SchoolYear.staticSetYearEnd(siteRequest_, o);
		this.yearEndWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearEnd(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnd(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrYearEnd(siteRequest_, SchoolYear.staticSolrYearEnd(siteRequest_, SchoolYear.staticSetYearEnd(siteRequest_, o)));
	}

	public Integer solrYearEnd() {
		return SchoolYear.staticSolrYearEnd(siteRequest_, yearEnd);
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
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

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolYear", pk, "YearEnd ").f().sx(htmYearEnd()).g("span");
			}
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
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
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

	/**	 The entity yearEnrollmentFee
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal yearEnrollmentFee;
	@JsonIgnore
	public Wrap<BigDecimal> yearEnrollmentFeeWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("yearEnrollmentFee").o(yearEnrollmentFee);

	/**	<br/> The entity yearEnrollmentFee
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnrollmentFee">Find the entity yearEnrollmentFee in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearEnrollmentFee(Wrap<BigDecimal> c);

	public BigDecimal getYearEnrollmentFee() {
		return yearEnrollmentFee;
	}

	public void setYearEnrollmentFee(BigDecimal yearEnrollmentFee) {
		this.yearEnrollmentFee = yearEnrollmentFee;
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
	}
	public void setYearEnrollmentFee(String o) {
		this.yearEnrollmentFee = SchoolYear.staticSetYearEnrollmentFee(siteRequest_, o);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetYearEnrollmentFee(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setYearEnrollmentFee(Double o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
	}
	public void setYearEnrollmentFee(Integer o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
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

	public static Double staticSolrYearEnrollmentFee(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrYearEnrollmentFee(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnrollmentFee(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrYearEnrollmentFee(siteRequest_, SchoolYear.staticSolrYearEnrollmentFee(siteRequest_, SchoolYear.staticSetYearEnrollmentFee(siteRequest_, o)));
	}

	public Double solrYearEnrollmentFee() {
		return SchoolYear.staticSolrYearEnrollmentFee(siteRequest_, yearEnrollmentFee);
	}

	public String strYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : yearEnrollmentFee.setScale(2, RoundingMode.HALF_UP).toString();
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
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

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolYear", pk, "YearEnrollmentFee ").f().sx(htmYearEnrollmentFee()).g("span");
			}
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
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
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

	/**	 The entity yearYears
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolYear>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolYear> yearYears = new ArrayList<SchoolYear>();
	@JsonIgnore
	public Wrap<List<SchoolYear>> yearYearsWrap = new Wrap<List<SchoolYear>>().p(this).c(List.class).var("yearYears").o(yearYears);

	/**	<br/> The entity yearYears
	 *  It is constructed before being initialized with the constructor by default List<SchoolYear>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearYears">Find the entity yearYears in Solr</a>
	 * <br/>
	 * @param yearYears is the entity already constructed. 
	 **/
	protected abstract void _yearYears(List<SchoolYear> l);

	public List<SchoolYear> getYearYears() {
		return yearYears;
	}

	public void setYearYears(List<SchoolYear> yearYears) {
		this.yearYears = yearYears;
		this.yearYearsWrap.alreadyInitialized = true;
	}
	public static SchoolYear staticSetYearYears(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity yearShortName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String yearShortName;
	@JsonIgnore
	public Wrap<String> yearShortNameWrap = new Wrap<String>().p(this).c(String.class).var("yearShortName").o(yearShortName);

	/**	<br/> The entity yearShortName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearShortName">Find the entity yearShortName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearShortName(Wrap<String> c);

	public String getYearShortName() {
		return yearShortName;
	}
	public void setYearShortName(String o) {
		this.yearShortName = SchoolYear.staticSetYearShortName(siteRequest_, o);
		this.yearShortNameWrap.alreadyInitialized = true;
	}
	public static String staticSetYearShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrYearShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrYearShortName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearShortName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrYearShortName(siteRequest_, SchoolYear.staticSolrYearShortName(siteRequest_, SchoolYear.staticSetYearShortName(siteRequest_, o)));
	}

	public String solrYearShortName() {
		return SchoolYear.staticSolrYearShortName(siteRequest_, yearShortName);
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

	/**	 The entity yearCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String yearCompleteName;
	@JsonIgnore
	public Wrap<String> yearCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("yearCompleteName").o(yearCompleteName);

	/**	<br/> The entity yearCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearCompleteName">Find the entity yearCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearCompleteName(Wrap<String> c);

	public String getYearCompleteName() {
		return yearCompleteName;
	}
	public void setYearCompleteName(String o) {
		this.yearCompleteName = SchoolYear.staticSetYearCompleteName(siteRequest_, o);
		this.yearCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetYearCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrYearCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrYearCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolYear.staticSolrStrYearCompleteName(siteRequest_, SchoolYear.staticSolrYearCompleteName(siteRequest_, SchoolYear.staticSetYearCompleteName(siteRequest_, o)));
	}

	public String solrYearCompleteName() {
		return SchoolYear.staticSolrYearCompleteName(siteRequest_, yearCompleteName);
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
		schoolFormInit();
		schoolNumberInit();
		schoolAdministratorNameInit();
		enrollmentFormKeyInit();
		sessionStartDateInit();
		seasonStartDateInit();
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
			case "schoolForm":
				return oSchoolYear.schoolForm;
			case "schoolNumber":
				return oSchoolYear.schoolNumber;
			case "schoolAdministratorName":
				return oSchoolYear.schoolAdministratorName;
			case "enrollmentFormKey":
				return oSchoolYear.enrollmentFormKey;
			case "sessionStartDate":
				return oSchoolYear.sessionStartDate;
			case "seasonStartDate":
				return oSchoolYear.seasonStartDate;
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
				if(oSchoolYear.getSchoolKey() == null)
					oSchoolYear.setSchoolKey((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "enrollmentKeys":
				oSchoolYear.addEnrollmentKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "seasonKeys":
				oSchoolYear.addSeasonKeys((Long)val);
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetSchoolYear(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSchoolYear(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "schoolKey":
			return SchoolYear.staticSetSchoolKey(siteRequest_, o);
		case "yearKey":
			return SchoolYear.staticSetYearKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolYear.staticSetEnrollmentKeys(siteRequest_, o);
		case "seasonKeys":
			return SchoolYear.staticSetSeasonKeys(siteRequest_, o);
		case "ageKeys":
			return SchoolYear.staticSetAgeKeys(siteRequest_, o);
		case "educationSort":
			return SchoolYear.staticSetEducationSort(siteRequest_, o);
		case "schoolSort":
			return SchoolYear.staticSetSchoolSort(siteRequest_, o);
		case "yearSort":
			return SchoolYear.staticSetYearSort(siteRequest_, o);
		case "schoolName":
			return SchoolYear.staticSetSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return SchoolYear.staticSetSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return SchoolYear.staticSetSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return SchoolYear.staticSetSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return SchoolYear.staticSetSchoolPhoneNumber(siteRequest_, o);
		case "schoolForm":
			return SchoolYear.staticSetSchoolForm(siteRequest_, o);
		case "schoolNumber":
			return SchoolYear.staticSetSchoolNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return SchoolYear.staticSetSchoolAdministratorName(siteRequest_, o);
		case "enrollmentFormKey":
			return SchoolYear.staticSetEnrollmentFormKey(siteRequest_, o);
		case "sessionStartDate":
			return SchoolYear.staticSetSessionStartDate(siteRequest_, o);
		case "seasonStartDate":
			return SchoolYear.staticSetSeasonStartDate(siteRequest_, o);
		case "sessionEndDate":
			return SchoolYear.staticSetSessionEndDate(siteRequest_, o);
		case "yearStart":
			return SchoolYear.staticSetYearStart(siteRequest_, o);
		case "yearEnd":
			return SchoolYear.staticSetYearEnd(siteRequest_, o);
		case "yearEnrollmentFee":
			return SchoolYear.staticSetYearEnrollmentFee(siteRequest_, o);
		case "yearShortName":
			return SchoolYear.staticSetYearShortName(siteRequest_, o);
		case "yearCompleteName":
			return SchoolYear.staticSetYearCompleteName(siteRequest_, o);
			default:
				return Cluster.staticSetCluster(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSchoolYear(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSchoolYear(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "schoolKey":
			return SchoolYear.staticSolrSchoolKey(siteRequest_, (Long)o);
		case "yearKey":
			return SchoolYear.staticSolrYearKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolYear.staticSolrEnrollmentKeys(siteRequest_, (Long)o);
		case "seasonKeys":
			return SchoolYear.staticSolrSeasonKeys(siteRequest_, (Long)o);
		case "ageKeys":
			return SchoolYear.staticSolrAgeKeys(siteRequest_, (Long)o);
		case "educationSort":
			return SchoolYear.staticSolrEducationSort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolYear.staticSolrSchoolSort(siteRequest_, (Integer)o);
		case "yearSort":
			return SchoolYear.staticSolrYearSort(siteRequest_, (Integer)o);
		case "schoolName":
			return SchoolYear.staticSolrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return SchoolYear.staticSolrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return SchoolYear.staticSolrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return SchoolYear.staticSolrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return SchoolYear.staticSolrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolForm":
			return SchoolYear.staticSolrSchoolForm(siteRequest_, (String)o);
		case "schoolNumber":
			return SchoolYear.staticSolrSchoolNumber(siteRequest_, (Integer)o);
		case "schoolAdministratorName":
			return SchoolYear.staticSolrSchoolAdministratorName(siteRequest_, (String)o);
		case "enrollmentFormKey":
			return SchoolYear.staticSolrEnrollmentFormKey(siteRequest_, (Long)o);
		case "sessionStartDate":
			return SchoolYear.staticSolrSessionStartDate(siteRequest_, (LocalDate)o);
		case "seasonStartDate":
			return SchoolYear.staticSolrSeasonStartDate(siteRequest_, (LocalDate)o);
		case "sessionEndDate":
			return SchoolYear.staticSolrSessionEndDate(siteRequest_, (LocalDate)o);
		case "yearStart":
			return SchoolYear.staticSolrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return SchoolYear.staticSolrYearEnd(siteRequest_, (Integer)o);
		case "yearEnrollmentFee":
			return SchoolYear.staticSolrYearEnrollmentFee(siteRequest_, (BigDecimal)o);
		case "yearShortName":
			return SchoolYear.staticSolrYearShortName(siteRequest_, (String)o);
		case "yearCompleteName":
			return SchoolYear.staticSolrYearCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrCluster(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSchoolYear(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSchoolYear(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "schoolKey":
			return SchoolYear.staticSolrStrSchoolKey(siteRequest_, (Long)o);
		case "yearKey":
			return SchoolYear.staticSolrStrYearKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolYear.staticSolrStrEnrollmentKeys(siteRequest_, (Long)o);
		case "seasonKeys":
			return SchoolYear.staticSolrStrSeasonKeys(siteRequest_, (Long)o);
		case "ageKeys":
			return SchoolYear.staticSolrStrAgeKeys(siteRequest_, (Long)o);
		case "educationSort":
			return SchoolYear.staticSolrStrEducationSort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolYear.staticSolrStrSchoolSort(siteRequest_, (Integer)o);
		case "yearSort":
			return SchoolYear.staticSolrStrYearSort(siteRequest_, (Integer)o);
		case "schoolName":
			return SchoolYear.staticSolrStrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return SchoolYear.staticSolrStrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return SchoolYear.staticSolrStrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return SchoolYear.staticSolrStrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return SchoolYear.staticSolrStrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolForm":
			return SchoolYear.staticSolrStrSchoolForm(siteRequest_, (String)o);
		case "schoolNumber":
			return SchoolYear.staticSolrStrSchoolNumber(siteRequest_, (Integer)o);
		case "schoolAdministratorName":
			return SchoolYear.staticSolrStrSchoolAdministratorName(siteRequest_, (String)o);
		case "enrollmentFormKey":
			return SchoolYear.staticSolrStrEnrollmentFormKey(siteRequest_, (Long)o);
		case "sessionStartDate":
			return SchoolYear.staticSolrStrSessionStartDate(siteRequest_, (Date)o);
		case "seasonStartDate":
			return SchoolYear.staticSolrStrSeasonStartDate(siteRequest_, (Date)o);
		case "sessionEndDate":
			return SchoolYear.staticSolrStrSessionEndDate(siteRequest_, (Date)o);
		case "yearStart":
			return SchoolYear.staticSolrStrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return SchoolYear.staticSolrStrYearEnd(siteRequest_, (Integer)o);
		case "yearEnrollmentFee":
			return SchoolYear.staticSolrStrYearEnrollmentFee(siteRequest_, (Double)o);
		case "yearShortName":
			return SchoolYear.staticSolrStrYearShortName(siteRequest_, (String)o);
		case "yearCompleteName":
			return SchoolYear.staticSolrStrYearCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSchoolYear(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSchoolYear(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "schoolKey":
			return SchoolYear.staticSolrFqSchoolKey(siteRequest_, o);
		case "yearKey":
			return SchoolYear.staticSolrFqYearKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolYear.staticSolrFqEnrollmentKeys(siteRequest_, o);
		case "seasonKeys":
			return SchoolYear.staticSolrFqSeasonKeys(siteRequest_, o);
		case "ageKeys":
			return SchoolYear.staticSolrFqAgeKeys(siteRequest_, o);
		case "educationSort":
			return SchoolYear.staticSolrFqEducationSort(siteRequest_, o);
		case "schoolSort":
			return SchoolYear.staticSolrFqSchoolSort(siteRequest_, o);
		case "yearSort":
			return SchoolYear.staticSolrFqYearSort(siteRequest_, o);
		case "schoolName":
			return SchoolYear.staticSolrFqSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return SchoolYear.staticSolrFqSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return SchoolYear.staticSolrFqSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return SchoolYear.staticSolrFqSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return SchoolYear.staticSolrFqSchoolPhoneNumber(siteRequest_, o);
		case "schoolForm":
			return SchoolYear.staticSolrFqSchoolForm(siteRequest_, o);
		case "schoolNumber":
			return SchoolYear.staticSolrFqSchoolNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return SchoolYear.staticSolrFqSchoolAdministratorName(siteRequest_, o);
		case "enrollmentFormKey":
			return SchoolYear.staticSolrFqEnrollmentFormKey(siteRequest_, o);
		case "sessionStartDate":
			return SchoolYear.staticSolrFqSessionStartDate(siteRequest_, o);
		case "seasonStartDate":
			return SchoolYear.staticSolrFqSeasonStartDate(siteRequest_, o);
		case "sessionEndDate":
			return SchoolYear.staticSolrFqSessionEndDate(siteRequest_, o);
		case "yearStart":
			return SchoolYear.staticSolrFqYearStart(siteRequest_, o);
		case "yearEnd":
			return SchoolYear.staticSolrFqYearEnd(siteRequest_, o);
		case "yearEnrollmentFee":
			return SchoolYear.staticSolrFqYearEnrollmentFee(siteRequest_, o);
		case "yearShortName":
			return SchoolYear.staticSolrFqYearShortName(siteRequest_, o);
		case "yearCompleteName":
			return SchoolYear.staticSolrFqYearCompleteName(siteRequest_, o);
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
			case "seasonStartDate":
				if(val != null)
					setSeasonStartDate(val);
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

			List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
			if(enrollmentKeys != null)
				oSchoolYear.enrollmentKeys.addAll(enrollmentKeys);

			List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
			if(seasonKeys != null)
				oSchoolYear.seasonKeys.addAll(seasonKeys);

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

			if(saves.contains("schoolForm")) {
				String schoolForm = (String)solrDocument.get("schoolForm_stored_string");
				if(schoolForm != null)
					oSchoolYear.setSchoolForm(schoolForm);
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

			if(saves.contains("seasonStartDate")) {
				Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
				if(seasonStartDate != null)
					oSchoolYear.setSeasonStartDate(seasonStartDate);
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
		if(schoolForm != null) {
			document.addField("schoolForm_indexed_string", schoolForm);
			document.addField("schoolForm_stored_string", schoolForm);
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
		if(seasonStartDate != null) {
			document.addField("seasonStartDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("seasonStartDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
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
			case "schoolForm":
				return "schoolForm_indexed_string";
			case "schoolNumber":
				return "schoolNumber_indexed_int";
			case "schoolAdministratorName":
				return "schoolAdministratorName_indexed_string";
			case "enrollmentFormKey":
				return "enrollmentFormKey_indexed_long";
			case "sessionStartDate":
				return "sessionStartDate_indexed_date";
			case "seasonStartDate":
				return "seasonStartDate_indexed_date";
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

		String schoolForm = (String)solrDocument.get("schoolForm_stored_string");
		if(schoolForm != null)
			oSchoolYear.setSchoolForm(schoolForm);

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

		Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
		if(seasonStartDate != null)
			oSchoolYear.setSeasonStartDate(seasonStartDate);

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
			if(!Objects.equals(yearKey, original.getYearKey()))
				apiRequest.addVars("yearKey");
			if(!Objects.equals(enrollmentKeys, original.getEnrollmentKeys()))
				apiRequest.addVars("enrollmentKeys");
			if(!Objects.equals(seasonKeys, original.getSeasonKeys()))
				apiRequest.addVars("seasonKeys");
			if(!Objects.equals(ageKeys, original.getAgeKeys()))
				apiRequest.addVars("ageKeys");
			if(!Objects.equals(educationSort, original.getEducationSort()))
				apiRequest.addVars("educationSort");
			if(!Objects.equals(schoolSort, original.getSchoolSort()))
				apiRequest.addVars("schoolSort");
			if(!Objects.equals(yearSort, original.getYearSort()))
				apiRequest.addVars("yearSort");
			if(!Objects.equals(schoolName, original.getSchoolName()))
				apiRequest.addVars("schoolName");
			if(!Objects.equals(schoolCompleteName, original.getSchoolCompleteName()))
				apiRequest.addVars("schoolCompleteName");
			if(!Objects.equals(schoolLocation, original.getSchoolLocation()))
				apiRequest.addVars("schoolLocation");
			if(!Objects.equals(schoolAddress, original.getSchoolAddress()))
				apiRequest.addVars("schoolAddress");
			if(!Objects.equals(schoolPhoneNumber, original.getSchoolPhoneNumber()))
				apiRequest.addVars("schoolPhoneNumber");
			if(!Objects.equals(schoolForm, original.getSchoolForm()))
				apiRequest.addVars("schoolForm");
			if(!Objects.equals(schoolNumber, original.getSchoolNumber()))
				apiRequest.addVars("schoolNumber");
			if(!Objects.equals(schoolAdministratorName, original.getSchoolAdministratorName()))
				apiRequest.addVars("schoolAdministratorName");
			if(!Objects.equals(enrollmentFormKey, original.getEnrollmentFormKey()))
				apiRequest.addVars("enrollmentFormKey");
			if(!Objects.equals(sessionStartDate, original.getSessionStartDate()))
				apiRequest.addVars("sessionStartDate");
			if(!Objects.equals(seasonStartDate, original.getSeasonStartDate()))
				apiRequest.addVars("seasonStartDate");
			if(!Objects.equals(sessionEndDate, original.getSessionEndDate()))
				apiRequest.addVars("sessionEndDate");
			if(!Objects.equals(yearStart, original.getYearStart()))
				apiRequest.addVars("yearStart");
			if(!Objects.equals(yearEnd, original.getYearEnd()))
				apiRequest.addVars("yearEnd");
			if(!Objects.equals(yearEnrollmentFee, original.getYearEnrollmentFee()))
				apiRequest.addVars("yearEnrollmentFee");
			if(!Objects.equals(yearShortName, original.getYearShortName()))
				apiRequest.addVars("yearShortName");
			if(!Objects.equals(yearCompleteName, original.getYearCompleteName()))
				apiRequest.addVars("yearCompleteName");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), schoolKey, yearKey, enrollmentKeys, seasonKeys, ageKeys, educationSort, schoolSort, yearSort, schoolName, schoolCompleteName, schoolLocation, schoolAddress, schoolPhoneNumber, schoolForm, schoolNumber, schoolAdministratorName, enrollmentFormKey, sessionStartDate, seasonStartDate, sessionEndDate, yearStart, yearEnd, yearEnrollmentFee, yearShortName, yearCompleteName);
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
				&& Objects.equals( yearKey, that.yearKey )
				&& Objects.equals( enrollmentKeys, that.enrollmentKeys )
				&& Objects.equals( seasonKeys, that.seasonKeys )
				&& Objects.equals( ageKeys, that.ageKeys )
				&& Objects.equals( educationSort, that.educationSort )
				&& Objects.equals( schoolSort, that.schoolSort )
				&& Objects.equals( yearSort, that.yearSort )
				&& Objects.equals( schoolName, that.schoolName )
				&& Objects.equals( schoolCompleteName, that.schoolCompleteName )
				&& Objects.equals( schoolLocation, that.schoolLocation )
				&& Objects.equals( schoolAddress, that.schoolAddress )
				&& Objects.equals( schoolPhoneNumber, that.schoolPhoneNumber )
				&& Objects.equals( schoolForm, that.schoolForm )
				&& Objects.equals( schoolNumber, that.schoolNumber )
				&& Objects.equals( schoolAdministratorName, that.schoolAdministratorName )
				&& Objects.equals( enrollmentFormKey, that.enrollmentFormKey )
				&& Objects.equals( sessionStartDate, that.sessionStartDate )
				&& Objects.equals( seasonStartDate, that.seasonStartDate )
				&& Objects.equals( sessionEndDate, that.sessionEndDate )
				&& Objects.equals( yearStart, that.yearStart )
				&& Objects.equals( yearEnd, that.yearEnd )
				&& Objects.equals( yearEnrollmentFee, that.yearEnrollmentFee )
				&& Objects.equals( yearShortName, that.yearShortName )
				&& Objects.equals( yearCompleteName, that.yearCompleteName );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolYear { ");
		sb.append( "schoolKey: " ).append(schoolKey);
		sb.append( ", yearKey: " ).append(yearKey);
		sb.append( ", enrollmentKeys: " ).append(enrollmentKeys);
		sb.append( ", seasonKeys: " ).append(seasonKeys);
		sb.append( ", ageKeys: " ).append(ageKeys);
		sb.append( ", educationSort: " ).append(educationSort);
		sb.append( ", schoolSort: " ).append(schoolSort);
		sb.append( ", yearSort: " ).append(yearSort);
		sb.append( ", schoolName: \"" ).append(schoolName).append( "\"" );
		sb.append( ", schoolCompleteName: \"" ).append(schoolCompleteName).append( "\"" );
		sb.append( ", schoolLocation: \"" ).append(schoolLocation).append( "\"" );
		sb.append( ", schoolAddress: \"" ).append(schoolAddress).append( "\"" );
		sb.append( ", schoolPhoneNumber: \"" ).append(schoolPhoneNumber).append( "\"" );
		sb.append( ", schoolForm: \"" ).append(schoolForm).append( "\"" );
		sb.append( ", schoolNumber: " ).append(schoolNumber);
		sb.append( ", schoolAdministratorName: \"" ).append(schoolAdministratorName).append( "\"" );
		sb.append( ", enrollmentFormKey: " ).append(enrollmentFormKey);
		sb.append( ", sessionStartDate: " ).append(sessionStartDate);
		sb.append( ", seasonStartDate: " ).append(seasonStartDate);
		sb.append( ", sessionEndDate: " ).append(sessionEndDate);
		sb.append( ", yearStart: " ).append(yearStart);
		sb.append( ", yearEnd: " ).append(yearEnd);
		sb.append( ", yearEnrollmentFee: " ).append(yearEnrollmentFee);
		sb.append( ", yearShortName: \"" ).append(yearShortName).append( "\"" );
		sb.append( ", yearCompleteName: \"" ).append(yearCompleteName).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
