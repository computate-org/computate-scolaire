package org.computate.scolaire.enUS.season;

import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.request.patch.PatchRequest;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import java.time.Instant;
import java.time.ZoneId;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolSeasonGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolSeason.class);

	public static final String SchoolSeason_UnNom = "a season";
	public static final String SchoolSeason_Ce = "this ";
	public static final String SchoolSeason_CeNom = "this season";
	public static final String SchoolSeason_Un = "a ";
	public static final String SchoolSeason_LeNom = "the season";
	public static final String SchoolSeason_NomSingulier = "season";
	public static final String SchoolSeason_NomPluriel = "seasons";
	public static final String SchoolSeason_NomActuel = "current season";
	public static final String SchoolSeason_TousNom = "all the seasons";
	public static final String SchoolSeason_RechercherTousNomPar = "search seasons by ";
	public static final String SchoolSeason_LesNoms = "the seasons";
	public static final String SchoolSeason_AucunNomTrouve = "no season found";
	public static final String SchoolSeason_NomVar = "season";
	public static final String SchoolSeason_DeNom = "of season";
	public static final String SchoolSeason_AdjectifPluriel = "school";
	public static final String SchoolSeason_AdjectifVar = "school";
	public static final String SchoolSeason_UnNomAdjectif = "a school season";
	public static final String SchoolSeason_NomAdjectifSingulier = "school season";
	public static final String SchoolSeason_NomAdjectifPluriel = "school seasons";
	public static final String SchoolSeason_Couleur = "yellow";
	public static final String SchoolSeason_IconeGroupe = "regular";
	public static final String SchoolSeason_IconeNom = "sun";

	///////////////
	// seasonKey //
	///////////////

	/**	L'entité « seasonKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long seasonKey;
	@JsonIgnore
	public Wrap<Long> seasonKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("seasonKey").o(seasonKey);

	/**	<br/>L'entité « seasonKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKey">Trouver l'entité seasonKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonKey(Wrap<Long> c);

	public Long getSeasonKey() {
		return seasonKey;
	}

	public void setSeasonKey(Long seasonKey) {
		this.seasonKey = seasonKey;
		this.seasonKeyWrap.alreadyInitialized = true;
	}
	public SchoolSeason setSeasonKey(String o) {
		if(NumberUtils.isParsable(o))
			this.seasonKey = Long.parseLong(o);
		this.seasonKeyWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason seasonKeyInit() {
		if(!seasonKeyWrap.alreadyInitialized) {
			_seasonKey(seasonKeyWrap);
			if(seasonKey == null)
				setSeasonKey(seasonKeyWrap.o);
		}
		seasonKeyWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public Long solrSeasonKey() {
		return seasonKey;
	}

	public String strSeasonKey() {
		return seasonKey == null ? "" : seasonKey.toString();
	}

	public String jsonSeasonKey() {
		return seasonKey == null ? "" : seasonKey.toString();
	}

	public String nomAffichageSeasonKey() {
		return "key";
	}

	public String htmTooltipSeasonKey() {
		return null;
	}

	public String htmSeasonKey() {
		return seasonKey == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonKey());
	}

	////////////////////
	// enrollmentKeys //
	////////////////////

	/**	L'entité « enrollmentKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> enrollmentKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> enrollmentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("enrollmentKeys").o(enrollmentKeys);

	/**	<br/>L'entité « enrollmentKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Trouver l'entité enrollmentKeys dans Solr</a>
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
	public SchoolSeason addEnrollmentKeys(Long...objets) {
		for(Long o : objets) {
			addEnrollmentKeys(o);
		}
		return (SchoolSeason)this;
	}
	public SchoolSeason addEnrollmentKeys(Long o) {
		if(o != null && !enrollmentKeys.contains(o))
			this.enrollmentKeys.add(o);
		return (SchoolSeason)this;
	}
	public SchoolSeason setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
		return (SchoolSeason)this;
	}
	public SchoolSeason addEnrollmentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnrollmentKeys(p);
		}
		return (SchoolSeason)this;
	}
	protected SchoolSeason enrollmentKeysInit() {
		if(!enrollmentKeysWrap.alreadyInitialized) {
			_enrollmentKeys(enrollmentKeys);
		}
		enrollmentKeysWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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

	/////////////////
	// sessionKeys //
	/////////////////

	/**	L'entité « sessionKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> sessionKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> sessionKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("sessionKeys").o(sessionKeys);

	/**	<br/>L'entité « sessionKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKeys">Trouver l'entité sessionKeys dans Solr</a>
	 * <br/>
	 * @param sessionKeys est l'entité déjà construit. 
	 **/
	protected abstract void _sessionKeys(List<Long> o);

	public List<Long> getSessionKeys() {
		return sessionKeys;
	}

	public void setSessionKeys(List<Long> sessionKeys) {
		this.sessionKeys = sessionKeys;
		this.sessionKeysWrap.alreadyInitialized = true;
	}
	public SchoolSeason addSessionKeys(Long...objets) {
		for(Long o : objets) {
			addSessionKeys(o);
		}
		return (SchoolSeason)this;
	}
	public SchoolSeason addSessionKeys(Long o) {
		if(o != null && !sessionKeys.contains(o))
			this.sessionKeys.add(o);
		return (SchoolSeason)this;
	}
	public SchoolSeason setSessionKeys(JsonArray objets) {
		sessionKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionKeys(o);
		}
		return (SchoolSeason)this;
	}
	public SchoolSeason addSessionKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionKeys(p);
		}
		return (SchoolSeason)this;
	}
	protected SchoolSeason sessionKeysInit() {
		if(!sessionKeysWrap.alreadyInitialized) {
			_sessionKeys(sessionKeys);
		}
		sessionKeysWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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

	public void inputSessionKeys(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "sessions")
				.a("class", "valueObjectSuggest suggestSessionKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setSessionKeys")
				.a("id", classApiMethodMethod, "_sessionKeys")
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolSeasonSessionKeys($(this).val() ? searchSchoolSessionFilters($('#suggestSchoolSeasonSessionKeys')) : [{'name':'fq','value':'seasonKey:", pk, "'}], $('#listSchoolSeasonSessionKeys_", classApiMethodMethod, "'), ", pk, "); ")
			.fg();

	}

	public void htmSessionKeys(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolSeasonSessionKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "fad fa-graduation-cap w3-padding-small ").f().g("i");
								sx("sessions");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate sessions to this season");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputSessionKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolSeasonSessionKeys_", classApiMethodMethod).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
										.a("onclick", "postSchoolSessionVals({ seasonKey: [ \"", pk, "\" ] }, function() { patchSchoolSeasonVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { addError($('#", classApiMethodMethod, "sessionKeys')); });")
										.f().sx("add a session")
									.g("button");
								} g("div");
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
	protected Integer educationSort;
	@JsonIgnore
	public Wrap<Integer> educationSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("educationSort").o(educationSort);

	/**	<br/>L'entité « educationSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Trouver l'entité educationSort dans Solr</a>
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
	public SchoolSeason setEducationSort(String o) {
		if(NumberUtils.isParsable(o))
			this.educationSort = Integer.parseInt(o);
		this.educationSortWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason educationSortInit() {
		if(!educationSortWrap.alreadyInitialized) {
			_educationSort(educationSortWrap);
			if(educationSort == null)
				setEducationSort(educationSortWrap.o);
		}
		educationSortWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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
	protected Integer schoolSort;
	@JsonIgnore
	public Wrap<Integer> schoolSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("schoolSort").o(schoolSort);

	/**	<br/>L'entité « schoolSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Trouver l'entité schoolSort dans Solr</a>
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
	public SchoolSeason setSchoolSort(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolSort = Integer.parseInt(o);
		this.schoolSortWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason schoolSortInit() {
		if(!schoolSortWrap.alreadyInitialized) {
			_schoolSort(schoolSortWrap);
			if(schoolSort == null)
				setSchoolSort(schoolSortWrap.o);
		}
		schoolSortWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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
	protected Integer yearSort;
	@JsonIgnore
	public Wrap<Integer> yearSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearSort").o(yearSort);

	/**	<br/>L'entité « yearSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSort">Trouver l'entité yearSort dans Solr</a>
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
	public SchoolSeason setYearSort(String o) {
		if(NumberUtils.isParsable(o))
			this.yearSort = Integer.parseInt(o);
		this.yearSortWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason yearSortInit() {
		if(!yearSortWrap.alreadyInitialized) {
			_yearSort(yearSortWrap);
			if(yearSort == null)
				setYearSort(yearSortWrap.o);
		}
		yearSortWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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

	////////////////
	// seasonSort //
	////////////////

	/**	L'entité « seasonSort »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer seasonSort;
	@JsonIgnore
	public Wrap<Integer> seasonSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("seasonSort").o(seasonSort);

	/**	<br/>L'entité « seasonSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSort">Trouver l'entité seasonSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonSort(Wrap<Integer> c);

	public Integer getSeasonSort() {
		return seasonSort;
	}

	public void setSeasonSort(Integer seasonSort) {
		this.seasonSort = seasonSort;
		this.seasonSortWrap.alreadyInitialized = true;
	}
	public SchoolSeason setSeasonSort(String o) {
		if(NumberUtils.isParsable(o))
			this.seasonSort = Integer.parseInt(o);
		this.seasonSortWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason seasonSortInit() {
		if(!seasonSortWrap.alreadyInitialized) {
			_seasonSort(seasonSortWrap);
			if(seasonSort == null)
				setSeasonSort(seasonSortWrap.o);
		}
		seasonSortWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public Integer solrSeasonSort() {
		return seasonSort;
	}

	public String strSeasonSort() {
		return seasonSort == null ? "" : seasonSort.toString();
	}

	public String jsonSeasonSort() {
		return seasonSort == null ? "" : seasonSort.toString();
	}

	public String nomAffichageSeasonSort() {
		return null;
	}

	public String htmTooltipSeasonSort() {
		return null;
	}

	public String htmSeasonSort() {
		return seasonSort == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonSort());
	}

	/////////////
	// yearKey //
	/////////////

	/**	L'entité « yearKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long yearKey;
	@JsonIgnore
	public Wrap<Long> yearKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("yearKey").o(yearKey);

	/**	<br/>L'entité « yearKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Trouver l'entité yearKey dans Solr</a>
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
	public SchoolSeason setYearKey(String o) {
		if(NumberUtils.isParsable(o))
			this.yearKey = Long.parseLong(o);
		this.yearKeyWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason yearKeyInit() {
		if(!yearKeyWrap.alreadyInitialized) {
			_yearKey(yearKeyWrap);
			if(yearKey == null)
				setYearKey(yearKeyWrap.o);
		}
		yearKeyWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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
		return "year";
	}

	public String htmTooltipYearKey() {
		return null;
	}

	public String htmYearKey() {
		return yearKey == null ? "" : StringEscapeUtils.escapeHtml4(strYearKey());
	}

	public void inputYearKey(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "year")
				.a("class", "valueObjectSuggest suggestYearKey w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setYearKey")
				.a("id", classApiMethodMethod, "_yearKey")
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolSeasonYearKey($(this).val() ? searchSchoolYearFilters($('#suggestSchoolSeasonYearKey')) : [{'name':'fq','value':'seasonKeys:", pk, "'}], $('#listSchoolSeasonYearKey_", classApiMethodMethod, "'), ", pk, "); ")
			.fg();

	}

	public void htmYearKey(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolSeasonYearKey").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-orange w3-hover-orange ").f();
								e("i").a("class", "far fa-calendar-check w3-padding-small ").f().g("i");
								sx("year");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate a year to this season");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputYearKey(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolSeasonYearKey_", classApiMethodMethod).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
										.a("onclick", "postSchoolYearVals({ seasonKeys: [ \"", pk, "\" ] }, function() { patchSchoolSeasonVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { addError($('#", classApiMethodMethod, "yearKey')); });")
										.f().sx("add a year")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////
	// yearSearch //
	////////////////

	/**	L'entité « yearSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolYear>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolYear> yearSearch = new SearchList<SchoolYear>();
	@JsonIgnore
	public Wrap<SearchList<SchoolYear>> yearSearchWrap = new Wrap<SearchList<SchoolYear>>().p(this).c(SearchList.class).var("yearSearch").o(yearSearch);

	/**	<br/>L'entité « yearSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolYear>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSearch">Trouver l'entité yearSearch dans Solr</a>
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
	protected SchoolSeason yearSearchInit() {
		if(!yearSearchWrap.alreadyInitialized) {
			_yearSearch(yearSearch);
		}
		yearSearch.initDeepForClass(siteRequest_);
		yearSearchWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	///////////
	// year_ //
	///////////

	/**	L'entité « year_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SchoolYear year_;
	@JsonIgnore
	public Wrap<SchoolYear> year_Wrap = new Wrap<SchoolYear>().p(this).c(SchoolYear.class).var("year_").o(year_);

	/**	<br/>L'entité « year_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:year_">Trouver l'entité year_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _year_(Wrap<SchoolYear> c);

	public SchoolYear getYear_() {
		return year_;
	}

	public void setYear_(SchoolYear year_) {
		this.year_ = year_;
		this.year_Wrap.alreadyInitialized = true;
	}
	protected SchoolSeason year_Init() {
		if(!year_Wrap.alreadyInitialized) {
			_year_(year_Wrap);
			if(year_ == null)
				setYear_(year_Wrap.o);
		}
		year_Wrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	///////////////
	// schoolKey //
	///////////////

	/**	L'entité « schoolKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long schoolKey;
	@JsonIgnore
	public Wrap<Long> schoolKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("schoolKey").o(schoolKey);

	/**	<br/>L'entité « schoolKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Trouver l'entité schoolKey dans Solr</a>
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
	public SchoolSeason setSchoolKey(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolKey = Long.parseLong(o);
		this.schoolKeyWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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

	////////////////
	// schoolName //
	////////////////

	/**	L'entité « schoolName »
	 *	 is defined as null before being initialized. 
	 */
	protected String schoolName;
	@JsonIgnore
	public Wrap<String> schoolNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolName").o(schoolName);

	/**	<br/>L'entité « schoolName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Trouver l'entité schoolName dans Solr</a>
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
	protected SchoolSeason schoolNameInit() {
		if(!schoolNameWrap.alreadyInitialized) {
			_schoolName(schoolNameWrap);
			if(schoolName == null)
				setSchoolName(schoolNameWrap.o);
		}
		schoolNameWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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
	protected String schoolCompleteName;
	@JsonIgnore
	public Wrap<String> schoolCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolCompleteName").o(schoolCompleteName);

	/**	<br/>L'entité « schoolCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Trouver l'entité schoolCompleteName dans Solr</a>
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
	protected SchoolSeason schoolCompleteNameInit() {
		if(!schoolCompleteNameWrap.alreadyInitialized) {
			_schoolCompleteName(schoolCompleteNameWrap);
			if(schoolCompleteName == null)
				setSchoolCompleteName(schoolCompleteNameWrap.o);
		}
		schoolCompleteNameWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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
	protected String schoolLocation;
	@JsonIgnore
	public Wrap<String> schoolLocationWrap = new Wrap<String>().p(this).c(String.class).var("schoolLocation").o(schoolLocation);

	/**	<br/>L'entité « schoolLocation »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Trouver l'entité schoolLocation dans Solr</a>
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
	protected SchoolSeason schoolLocationInit() {
		if(!schoolLocationWrap.alreadyInitialized) {
			_schoolLocation(schoolLocationWrap);
			if(schoolLocation == null)
				setSchoolLocation(schoolLocationWrap.o);
		}
		schoolLocationWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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
	protected String schoolAddress;
	@JsonIgnore
	public Wrap<String> schoolAddressWrap = new Wrap<String>().p(this).c(String.class).var("schoolAddress").o(schoolAddress);

	/**	<br/>L'entité « schoolAddress »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Trouver l'entité schoolAddress dans Solr</a>
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
	protected SchoolSeason schoolAddressInit() {
		if(!schoolAddressWrap.alreadyInitialized) {
			_schoolAddress(schoolAddressWrap);
			if(schoolAddress == null)
				setSchoolAddress(schoolAddressWrap.o);
		}
		schoolAddressWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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
	protected String schoolPhoneNumber;
	@JsonIgnore
	public Wrap<String> schoolPhoneNumberWrap = new Wrap<String>().p(this).c(String.class).var("schoolPhoneNumber").o(schoolPhoneNumber);

	/**	<br/>L'entité « schoolPhoneNumber »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Trouver l'entité schoolPhoneNumber dans Solr</a>
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
	protected SchoolSeason schoolPhoneNumberInit() {
		if(!schoolPhoneNumberWrap.alreadyInitialized) {
			_schoolPhoneNumber(schoolPhoneNumberWrap);
			if(schoolPhoneNumber == null)
				setSchoolPhoneNumber(schoolPhoneNumberWrap.o);
		}
		schoolPhoneNumberWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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

	/////////////////////////////
	// schoolAdministratorName //
	/////////////////////////////

	/**	L'entité « schoolAdministratorName »
	 *	 is defined as null before being initialized. 
	 */
	protected String schoolAdministratorName;
	@JsonIgnore
	public Wrap<String> schoolAdministratorNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolAdministratorName").o(schoolAdministratorName);

	/**	<br/>L'entité « schoolAdministratorName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Trouver l'entité schoolAdministratorName dans Solr</a>
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
	protected SchoolSeason schoolAdministratorNameInit() {
		if(!schoolAdministratorNameWrap.alreadyInitialized) {
			_schoolAdministratorName(schoolAdministratorNameWrap);
			if(schoolAdministratorName == null)
				setSchoolAdministratorName(schoolAdministratorNameWrap.o);
		}
		schoolAdministratorNameWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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

	///////////////
	// yearStart //
	///////////////

	/**	L'entité « yearStart »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer yearStart;
	@JsonIgnore
	public Wrap<Integer> yearStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearStart").o(yearStart);

	/**	<br/>L'entité « yearStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Trouver l'entité yearStart dans Solr</a>
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
	public SchoolSeason setYearStart(String o) {
		if(NumberUtils.isParsable(o))
			this.yearStart = Integer.parseInt(o);
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason yearStartInit() {
		if(!yearStartWrap.alreadyInitialized) {
			_yearStart(yearStartWrap);
			if(yearStart == null)
				setYearStart(yearStartWrap.o);
		}
		yearStartWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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

	/////////////
	// yearEnd //
	/////////////

	/**	L'entité « yearEnd »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer yearEnd;
	@JsonIgnore
	public Wrap<Integer> yearEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearEnd").o(yearEnd);

	/**	<br/>L'entité « yearEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Trouver l'entité yearEnd dans Solr</a>
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
	public SchoolSeason setYearEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.yearEnd = Integer.parseInt(o);
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason yearEndInit() {
		if(!yearEndWrap.alreadyInitialized) {
			_yearEnd(yearEndWrap);
			if(yearEnd == null)
				setYearEnd(yearEndWrap.o);
		}
		yearEndWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
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

	/////////////////////
	// seasonStartDate //
	/////////////////////

	/**	L'entité « seasonStartDate »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate seasonStartDate;
	@JsonIgnore
	public Wrap<LocalDate> seasonStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("seasonStartDate").o(seasonStartDate);

	/**	<br/>L'entité « seasonStartDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Trouver l'entité seasonStartDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonStartDate(Wrap<LocalDate> c);

	public LocalDate getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(LocalDate seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public SchoolSeason setSeasonStartDate(Instant o) {
		this.seasonStartDate = LocalDate.from(o);
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolSeason setSeasonStartDate(String o) {
		this.seasonStartDate = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	public SchoolSeason setSeasonStartDate(Date o) {
		this.seasonStartDate = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason seasonStartDateInit() {
		if(!seasonStartDateWrap.alreadyInitialized) {
			_seasonStartDate(seasonStartDateWrap);
			if(seasonStartDate == null)
				setSeasonStartDate(seasonStartDateWrap.o);
		}
		seasonStartDateWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public Date solrSeasonStartDate() {
		return seasonStartDate == null ? null : Date.from(seasonStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
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
		SchoolSeason s = (SchoolSeason)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_seasonStartDate")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", seasonStartDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(seasonStartDate))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolSeasonVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setSeasonStartDate', s, function() { addGlow($('#", classApiMethodMethod, "_seasonStartDate')); }, function() { addError($('#", classApiMethodMethod, "_seasonStartDate')); }); } ")
			.fg();
	}

	public void htmSeasonStartDate(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolSeasonSeasonStartDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_seasonStartDate").a("class", "").f().sx("start of the season").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputSeasonStartDate(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_seasonStartDate')); $('#", classApiMethodMethod, "_seasonStartDate').val(null); patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=pk]').val() }], 'setSeasonStartDate', null, function() { addGlow($('#", classApiMethodMethod, "_seasonStartDate')); }, function() { addError($('#", classApiMethodMethod, "_seasonStartDate')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// seasonSummer //
	//////////////////

	/**	L'entité « seasonSummer »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean seasonSummer;
	@JsonIgnore
	public Wrap<Boolean> seasonSummerWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seasonSummer").o(seasonSummer);

	/**	<br/>L'entité « seasonSummer »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSummer">Trouver l'entité seasonSummer dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonSummer(Wrap<Boolean> c);

	public Boolean getSeasonSummer() {
		return seasonSummer;
	}

	public void setSeasonSummer(Boolean seasonSummer) {
		this.seasonSummer = seasonSummer;
		this.seasonSummerWrap.alreadyInitialized = true;
	}
	public SchoolSeason setSeasonSummer(String o) {
		this.seasonSummer = Boolean.parseBoolean(o);
		this.seasonSummerWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason seasonSummerInit() {
		if(!seasonSummerWrap.alreadyInitialized) {
			_seasonSummer(seasonSummerWrap);
			if(seasonSummer == null)
				setSeasonSummer(seasonSummerWrap.o);
		}
		seasonSummerWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public Boolean solrSeasonSummer() {
		return seasonSummer;
	}

	public String strSeasonSummer() {
		return seasonSummer == null ? "" : seasonSummer.toString();
	}

	public String jsonSeasonSummer() {
		return seasonSummer == null ? "" : seasonSummer.toString();
	}

	public String nomAffichageSeasonSummer() {
		return "summer";
	}

	public String htmTooltipSeasonSummer() {
		return null;
	}

	public String htmSeasonSummer() {
		return seasonSummer == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonSummer());
	}

	public void inputSeasonSummer(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		e("input")
			.a("type", "checkbox")
			.a("id", classApiMethodMethod, "_seasonSummer")
			.a("value", "true");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSeasonSummer");
				a("name", "setSeasonSummer");
			} else {
				a("class", "valueSeasonSummer");
				a("name", "seasonSummer");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolSeasonVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setSeasonSummer', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_seasonSummer')); }, function() { addError($('#", classApiMethodMethod, "_seasonSummer')); }); ");
			}
			;
			if(getSeasonSummer() != null && getSeasonSummer())
				a("checked", "checked");
		fg();

	}

	public void htmSeasonSummer(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolSeasonSeasonSummer").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_seasonSummer").a("class", "").f().sx("summer").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSeasonSummer(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// seasonWinter //
	//////////////////

	/**	L'entité « seasonWinter »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean seasonWinter;
	@JsonIgnore
	public Wrap<Boolean> seasonWinterWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seasonWinter").o(seasonWinter);

	/**	<br/>L'entité « seasonWinter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonWinter">Trouver l'entité seasonWinter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonWinter(Wrap<Boolean> c);

	public Boolean getSeasonWinter() {
		return seasonWinter;
	}

	public void setSeasonWinter(Boolean seasonWinter) {
		this.seasonWinter = seasonWinter;
		this.seasonWinterWrap.alreadyInitialized = true;
	}
	public SchoolSeason setSeasonWinter(String o) {
		this.seasonWinter = Boolean.parseBoolean(o);
		this.seasonWinterWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason seasonWinterInit() {
		if(!seasonWinterWrap.alreadyInitialized) {
			_seasonWinter(seasonWinterWrap);
			if(seasonWinter == null)
				setSeasonWinter(seasonWinterWrap.o);
		}
		seasonWinterWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public Boolean solrSeasonWinter() {
		return seasonWinter;
	}

	public String strSeasonWinter() {
		return seasonWinter == null ? "" : seasonWinter.toString();
	}

	public String jsonSeasonWinter() {
		return seasonWinter == null ? "" : seasonWinter.toString();
	}

	public String nomAffichageSeasonWinter() {
		return "winter";
	}

	public String htmTooltipSeasonWinter() {
		return null;
	}

	public String htmSeasonWinter() {
		return seasonWinter == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonWinter());
	}

	public void inputSeasonWinter(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		e("input")
			.a("type", "checkbox")
			.a("id", classApiMethodMethod, "_seasonWinter")
			.a("value", "true");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSeasonWinter");
				a("name", "setSeasonWinter");
			} else {
				a("class", "valueSeasonWinter");
				a("name", "seasonWinter");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolSeasonVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setSeasonWinter', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_seasonWinter')); }, function() { addError($('#", classApiMethodMethod, "_seasonWinter')); }); ");
			}
			;
			if(getSeasonWinter() != null && getSeasonWinter())
				a("checked", "checked");
		fg();

	}

	public void htmSeasonWinter(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolSeasonSeasonWinter").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_seasonWinter").a("class", "").f().sx("winter").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSeasonWinter(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////
	// seasonEnrollmentFee //
	/////////////////////////

	/**	L'entité « seasonEnrollmentFee »
	 *	 is defined as null before being initialized. 
	 */
	protected BigDecimal seasonEnrollmentFee;
	@JsonIgnore
	public Wrap<BigDecimal> seasonEnrollmentFeeWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("seasonEnrollmentFee").o(seasonEnrollmentFee);

	/**	<br/>L'entité « seasonEnrollmentFee »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonEnrollmentFee">Trouver l'entité seasonEnrollmentFee dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonEnrollmentFee(Wrap<BigDecimal> c);

	public BigDecimal getSeasonEnrollmentFee() {
		return seasonEnrollmentFee;
	}

	public void setSeasonEnrollmentFee(BigDecimal seasonEnrollmentFee) {
		this.seasonEnrollmentFee = seasonEnrollmentFee;
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
	}
	public SchoolSeason setSeasonEnrollmentFee(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	public SchoolSeason setSeasonEnrollmentFee(Double o) {
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	public SchoolSeason setSeasonEnrollmentFee(Integer o) {
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason seasonEnrollmentFeeInit() {
		if(!seasonEnrollmentFeeWrap.alreadyInitialized) {
			_seasonEnrollmentFee(seasonEnrollmentFeeWrap);
			if(seasonEnrollmentFee == null)
				setSeasonEnrollmentFee(seasonEnrollmentFeeWrap.o);
		}
		seasonEnrollmentFeeWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public Double solrSeasonEnrollmentFee() {
		return seasonEnrollmentFee == null ? null : seasonEnrollmentFee.doubleValue();
	}

	public String strSeasonEnrollmentFee() {
		return seasonEnrollmentFee == null ? "" : seasonEnrollmentFee.setScale(2).toString();
	}

	public String jsonSeasonEnrollmentFee() {
		return seasonEnrollmentFee == null ? "" : seasonEnrollmentFee.toString();
	}

	public String nomAffichageSeasonEnrollmentFee() {
		return "enrollment fee";
	}

	public String htmTooltipSeasonEnrollmentFee() {
		return null;
	}

	public String htmSeasonEnrollmentFee() {
		return seasonEnrollmentFee == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonEnrollmentFee());
	}

	public void inputSeasonEnrollmentFee(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "enrollment fee")
			.a("id", classApiMethodMethod, "_seasonEnrollmentFee");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSeasonEnrollmentFee w3-input w3-border ");
				a("name", "setSeasonEnrollmentFee");
			} else {
				a("class", "valueSeasonEnrollmentFee w3-input w3-border ");
				a("name", "seasonEnrollmentFee");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolSeasonVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setSeasonEnrollmentFee', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_seasonEnrollmentFee')); }, function() { addError($('#", classApiMethodMethod, "_seasonEnrollmentFee')); }); ");
			}
			a("value", strSeasonEnrollmentFee())
		.fg();

	}

	public void htmSeasonEnrollmentFee(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolSeasonSeasonEnrollmentFee").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_seasonEnrollmentFee").a("class", "").f().sx("enrollment fee").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSeasonEnrollmentFee(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_seasonEnrollmentFee')); $('#", classApiMethodMethod, "_seasonEnrollmentFee').val(null); patchSchoolSeasonVal([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=pk]').val() }], 'setSeasonEnrollmentFee', null, function() { addGlow($('#", classApiMethodMethod, "_seasonEnrollmentFee')); }, function() { addError($('#", classApiMethodMethod, "_seasonEnrollmentFee')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// seasonFuture //
	//////////////////

	/**	L'entité « seasonFuture »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean seasonFuture;
	@JsonIgnore
	public Wrap<Boolean> seasonFutureWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seasonFuture").o(seasonFuture);

	/**	<br/>L'entité « seasonFuture »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonFuture">Trouver l'entité seasonFuture dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonFuture(Wrap<Boolean> c);

	public Boolean getSeasonFuture() {
		return seasonFuture;
	}

	public void setSeasonFuture(Boolean seasonFuture) {
		this.seasonFuture = seasonFuture;
		this.seasonFutureWrap.alreadyInitialized = true;
	}
	public SchoolSeason setSeasonFuture(String o) {
		this.seasonFuture = Boolean.parseBoolean(o);
		this.seasonFutureWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason seasonFutureInit() {
		if(!seasonFutureWrap.alreadyInitialized) {
			_seasonFuture(seasonFutureWrap);
			if(seasonFuture == null)
				setSeasonFuture(seasonFutureWrap.o);
		}
		seasonFutureWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public Boolean solrSeasonFuture() {
		return seasonFuture;
	}

	public String strSeasonFuture() {
		return seasonFuture == null ? "" : seasonFuture.toString();
	}

	public String jsonSeasonFuture() {
		return seasonFuture == null ? "" : seasonFuture.toString();
	}

	public String nomAffichageSeasonFuture() {
		return "future season";
	}

	public String htmTooltipSeasonFuture() {
		return null;
	}

	public String htmSeasonFuture() {
		return seasonFuture == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonFuture());
	}

	public void inputSeasonFuture(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		e("input")
			.a("type", "checkbox")
			.a("id", classApiMethodMethod, "_seasonFuture")
			.a("value", "true");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSeasonFuture");
				a("name", "setSeasonFuture");
			} else {
				a("class", "valueSeasonFuture");
				a("name", "seasonFuture");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolSeasonVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setSeasonFuture', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_seasonFuture')); }, function() { addError($('#", classApiMethodMethod, "_seasonFuture')); }); ");
			}
			;
			if(getSeasonFuture() != null && getSeasonFuture())
				a("checked", "checked");
		fg();

	}

	public void htmSeasonFuture(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolSeasonSeasonFuture").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_seasonFuture").a("class", "").f().sx("future season").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSeasonFuture(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////
	// seasonShortName //
	/////////////////////

	/**	L'entité « seasonShortName »
	 *	 is defined as null before being initialized. 
	 */
	protected String seasonShortName;
	@JsonIgnore
	public Wrap<String> seasonShortNameWrap = new Wrap<String>().p(this).c(String.class).var("seasonShortName").o(seasonShortName);

	/**	<br/>L'entité « seasonShortName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonShortName">Trouver l'entité seasonShortName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonShortName(Wrap<String> c);

	public String getSeasonShortName() {
		return seasonShortName;
	}

	public void setSeasonShortName(String seasonShortName) {
		this.seasonShortName = seasonShortName;
		this.seasonShortNameWrap.alreadyInitialized = true;
	}
	protected SchoolSeason seasonShortNameInit() {
		if(!seasonShortNameWrap.alreadyInitialized) {
			_seasonShortName(seasonShortNameWrap);
			if(seasonShortName == null)
				setSeasonShortName(seasonShortNameWrap.o);
		}
		seasonShortNameWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public String solrSeasonShortName() {
		return seasonShortName;
	}

	public String strSeasonShortName() {
		return seasonShortName == null ? "" : seasonShortName;
	}

	public String jsonSeasonShortName() {
		return seasonShortName == null ? "" : seasonShortName;
	}

	public String nomAffichageSeasonShortName() {
		return null;
	}

	public String htmTooltipSeasonShortName() {
		return null;
	}

	public String htmSeasonShortName() {
		return seasonShortName == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonShortName());
	}

	////////////////////////
	// seasonCompleteName //
	////////////////////////

	/**	L'entité « seasonCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String seasonCompleteName;
	@JsonIgnore
	public Wrap<String> seasonCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("seasonCompleteName").o(seasonCompleteName);

	/**	<br/>L'entité « seasonCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonCompleteName">Trouver l'entité seasonCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonCompleteName(Wrap<String> c);

	public String getSeasonCompleteName() {
		return seasonCompleteName;
	}

	public void setSeasonCompleteName(String seasonCompleteName) {
		this.seasonCompleteName = seasonCompleteName;
		this.seasonCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolSeason seasonCompleteNameInit() {
		if(!seasonCompleteNameWrap.alreadyInitialized) {
			_seasonCompleteName(seasonCompleteNameWrap);
			if(seasonCompleteName == null)
				setSeasonCompleteName(seasonCompleteNameWrap.o);
		}
		seasonCompleteNameWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public String solrSeasonCompleteName() {
		return seasonCompleteName;
	}

	public String strSeasonCompleteName() {
		return seasonCompleteName == null ? "" : seasonCompleteName;
	}

	public String jsonSeasonCompleteName() {
		return seasonCompleteName == null ? "" : seasonCompleteName;
	}

	public String nomAffichageSeasonCompleteName() {
		return "name";
	}

	public String htmTooltipSeasonCompleteName() {
		return null;
	}

	public String htmSeasonCompleteName() {
		return seasonCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonCompleteName());
	}

	public void inputSeasonCompleteName(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
	}

	public void htmSeasonCompleteName(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			if("Page".equals(classApiMethodMethod)) {
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("class", "").f().sx("name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(strSeasonCompleteName()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			}
		} g("div");
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSchoolSeason = false;

	public SchoolSeason initDeepSchoolSeason(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolSeason) {
			alreadyInitializedSchoolSeason = true;
			initDeepSchoolSeason();
		}
		return (SchoolSeason)this;
	}

	public void initDeepSchoolSeason() {
		initSchoolSeason();
		super.initDeepCluster(siteRequest_);
	}

	public void initSchoolSeason() {
		seasonKeyInit();
		enrollmentKeysInit();
		sessionKeysInit();
		educationSortInit();
		schoolSortInit();
		yearSortInit();
		seasonSortInit();
		yearKeyInit();
		yearSearchInit();
		year_Init();
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
		seasonSummerInit();
		seasonWinterInit();
		seasonEnrollmentFeeInit();
		seasonFutureInit();
		seasonShortNameInit();
		seasonCompleteNameInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolSeason(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolSeason(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(yearSearch != null)
			yearSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolSeason(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolSeason(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolSeason(String var) {
		SchoolSeason oSchoolSeason = (SchoolSeason)this;
		switch(var) {
			case "seasonKey":
				return oSchoolSeason.seasonKey;
			case "enrollmentKeys":
				return oSchoolSeason.enrollmentKeys;
			case "sessionKeys":
				return oSchoolSeason.sessionKeys;
			case "educationSort":
				return oSchoolSeason.educationSort;
			case "schoolSort":
				return oSchoolSeason.schoolSort;
			case "yearSort":
				return oSchoolSeason.yearSort;
			case "seasonSort":
				return oSchoolSeason.seasonSort;
			case "yearKey":
				return oSchoolSeason.yearKey;
			case "yearSearch":
				return oSchoolSeason.yearSearch;
			case "year_":
				return oSchoolSeason.year_;
			case "schoolKey":
				return oSchoolSeason.schoolKey;
			case "schoolName":
				return oSchoolSeason.schoolName;
			case "schoolCompleteName":
				return oSchoolSeason.schoolCompleteName;
			case "schoolLocation":
				return oSchoolSeason.schoolLocation;
			case "schoolAddress":
				return oSchoolSeason.schoolAddress;
			case "schoolPhoneNumber":
				return oSchoolSeason.schoolPhoneNumber;
			case "schoolAdministratorName":
				return oSchoolSeason.schoolAdministratorName;
			case "yearStart":
				return oSchoolSeason.yearStart;
			case "yearEnd":
				return oSchoolSeason.yearEnd;
			case "seasonStartDate":
				return oSchoolSeason.seasonStartDate;
			case "seasonSummer":
				return oSchoolSeason.seasonSummer;
			case "seasonWinter":
				return oSchoolSeason.seasonWinter;
			case "seasonEnrollmentFee":
				return oSchoolSeason.seasonEnrollmentFee;
			case "seasonFuture":
				return oSchoolSeason.seasonFuture;
			case "seasonShortName":
				return oSchoolSeason.seasonShortName;
			case "seasonCompleteName":
				return oSchoolSeason.seasonCompleteName;
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
				o = attributeSchoolSeason(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolSeason(String var, Object val) {
		SchoolSeason oSchoolSeason = (SchoolSeason)this;
		switch(var) {
			case "sessionKeys":
				oSchoolSeason.addSessionKeys((Long)val);
				return val;
			case "yearKey":
				oSchoolSeason.setYearKey((Long)val);
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
					o = defineSchoolSeason(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolSeason(String var, String val) {
		switch(var) {
			case "seasonStartDate":
				setSeasonStartDate(val);
				savesSchoolSeason.add(var);
				return val;
			case "seasonSummer":
				setSeasonSummer(val);
				savesSchoolSeason.add(var);
				return val;
			case "seasonWinter":
				setSeasonWinter(val);
				savesSchoolSeason.add(var);
				return val;
			case "seasonEnrollmentFee":
				setSeasonEnrollmentFee(val);
				savesSchoolSeason.add(var);
				return val;
			case "seasonFuture":
				setSeasonFuture(val);
				savesSchoolSeason.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesSchoolSeason = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolSeason(solrDocument);
	}
	public void populateSchoolSeason(SolrDocument solrDocument) {
		SchoolSeason oSchoolSeason = (SchoolSeason)this;
		savesSchoolSeason = (List<String>)solrDocument.get("savesSchoolSeason_stored_strings");
		if(savesSchoolSeason != null) {

			if(savesSchoolSeason.contains("seasonKey")) {
				Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
				if(seasonKey != null)
					oSchoolSeason.setSeasonKey(seasonKey);
			}

			if(savesSchoolSeason.contains("enrollmentKeys")) {
				List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
				if(enrollmentKeys != null)
					oSchoolSeason.enrollmentKeys.addAll(enrollmentKeys);
			}

			List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
			if(sessionKeys != null)
				oSchoolSeason.sessionKeys.addAll(sessionKeys);

			if(savesSchoolSeason.contains("educationSort")) {
				Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
				if(educationSort != null)
					oSchoolSeason.setEducationSort(educationSort);
			}

			if(savesSchoolSeason.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolSeason.setSchoolSort(schoolSort);
			}

			if(savesSchoolSeason.contains("yearSort")) {
				Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
				if(yearSort != null)
					oSchoolSeason.setYearSort(yearSort);
			}

			if(savesSchoolSeason.contains("seasonSort")) {
				Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
				if(seasonSort != null)
					oSchoolSeason.setSeasonSort(seasonSort);
			}

			Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
			if(yearKey != null)
				oSchoolSeason.setYearKey(yearKey);

			if(savesSchoolSeason.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oSchoolSeason.setSchoolKey(schoolKey);
			}

			if(savesSchoolSeason.contains("schoolName")) {
				String schoolName = (String)solrDocument.get("schoolName_stored_string");
				if(schoolName != null)
					oSchoolSeason.setSchoolName(schoolName);
			}

			if(savesSchoolSeason.contains("schoolCompleteName")) {
				String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
				if(schoolCompleteName != null)
					oSchoolSeason.setSchoolCompleteName(schoolCompleteName);
			}

			if(savesSchoolSeason.contains("schoolLocation")) {
				String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
				if(schoolLocation != null)
					oSchoolSeason.setSchoolLocation(schoolLocation);
			}

			if(savesSchoolSeason.contains("schoolAddress")) {
				String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
				if(schoolAddress != null)
					oSchoolSeason.setSchoolAddress(schoolAddress);
			}

			if(savesSchoolSeason.contains("schoolPhoneNumber")) {
				String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
				if(schoolPhoneNumber != null)
					oSchoolSeason.setSchoolPhoneNumber(schoolPhoneNumber);
			}

			if(savesSchoolSeason.contains("schoolAdministratorName")) {
				String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
				if(schoolAdministratorName != null)
					oSchoolSeason.setSchoolAdministratorName(schoolAdministratorName);
			}

			if(savesSchoolSeason.contains("yearStart")) {
				Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
				if(yearStart != null)
					oSchoolSeason.setYearStart(yearStart);
			}

			if(savesSchoolSeason.contains("yearEnd")) {
				Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
				if(yearEnd != null)
					oSchoolSeason.setYearEnd(yearEnd);
			}

			if(savesSchoolSeason.contains("seasonStartDate")) {
				Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
				if(seasonStartDate != null)
					oSchoolSeason.setSeasonStartDate(seasonStartDate);
			}

			if(savesSchoolSeason.contains("seasonSummer")) {
				Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
				if(seasonSummer != null)
					oSchoolSeason.setSeasonSummer(seasonSummer);
			}

			if(savesSchoolSeason.contains("seasonWinter")) {
				Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
				if(seasonWinter != null)
					oSchoolSeason.setSeasonWinter(seasonWinter);
			}

			if(savesSchoolSeason.contains("seasonEnrollmentFee")) {
				Double seasonEnrollmentFee = (Double)solrDocument.get("seasonEnrollmentFee_stored_double");
				if(seasonEnrollmentFee != null)
					oSchoolSeason.setSeasonEnrollmentFee(seasonEnrollmentFee);
			}

			if(savesSchoolSeason.contains("seasonFuture")) {
				Boolean seasonFuture = (Boolean)solrDocument.get("seasonFuture_stored_boolean");
				if(seasonFuture != null)
					oSchoolSeason.setSeasonFuture(seasonFuture);
			}

			if(savesSchoolSeason.contains("seasonShortName")) {
				String seasonShortName = (String)solrDocument.get("seasonShortName_stored_string");
				if(seasonShortName != null)
					oSchoolSeason.setSeasonShortName(seasonShortName);
			}

			if(savesSchoolSeason.contains("seasonCompleteName")) {
				String seasonCompleteName = (String)solrDocument.get("seasonCompleteName_stored_string");
				if(seasonCompleteName != null)
					oSchoolSeason.setSeasonCompleteName(seasonCompleteName);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.season.SchoolSeason"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolSeason o = new SchoolSeason();
			o.siteRequestSchoolSeason(siteRequest);
			o.initDeepSchoolSeason(siteRequest);
			o.indexSchoolSeason();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolSeason();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolSeason(document);
	}

	public void indexSchoolSeason(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolSeason(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolSeason() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolSeason(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolSeason(SolrInputDocument document) {
		if(savesSchoolSeason != null)
			document.addField("savesSchoolSeason_stored_strings", savesSchoolSeason);

		if(seasonKey != null) {
			document.addField("seasonKey_indexed_long", seasonKey);
			document.addField("seasonKey_stored_long", seasonKey);
		}
		if(enrollmentKeys != null) {
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_indexed_longs", o);
			}
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_stored_longs", o);
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
		if(seasonSort != null) {
			document.addField("seasonSort_indexed_int", seasonSort);
			document.addField("seasonSort_stored_int", seasonSort);
		}
		if(yearKey != null) {
			document.addField("yearKey_indexed_long", yearKey);
			document.addField("yearKey_stored_long", yearKey);
		}
		if(schoolKey != null) {
			document.addField("schoolKey_indexed_long", schoolKey);
			document.addField("schoolKey_stored_long", schoolKey);
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
		if(schoolAdministratorName != null) {
			document.addField("schoolAdministratorName_indexed_string", schoolAdministratorName);
			document.addField("schoolAdministratorName_stored_string", schoolAdministratorName);
		}
		if(yearStart != null) {
			document.addField("yearStart_indexed_int", yearStart);
			document.addField("yearStart_stored_int", yearStart);
		}
		if(yearEnd != null) {
			document.addField("yearEnd_indexed_int", yearEnd);
			document.addField("yearEnd_stored_int", yearEnd);
		}
		if(seasonStartDate != null) {
			document.addField("seasonStartDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("seasonStartDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(seasonSummer != null) {
			document.addField("seasonSummer_indexed_boolean", seasonSummer);
			document.addField("seasonSummer_stored_boolean", seasonSummer);
		}
		if(seasonWinter != null) {
			document.addField("seasonWinter_indexed_boolean", seasonWinter);
			document.addField("seasonWinter_stored_boolean", seasonWinter);
		}
		if(seasonEnrollmentFee != null) {
			document.addField("seasonEnrollmentFee_indexed_double", seasonEnrollmentFee.doubleValue());
			document.addField("seasonEnrollmentFee_stored_double", seasonEnrollmentFee.doubleValue());
		}
		if(seasonFuture != null) {
			document.addField("seasonFuture_indexed_boolean", seasonFuture);
			document.addField("seasonFuture_stored_boolean", seasonFuture);
		}
		if(seasonShortName != null) {
			document.addField("seasonShortName_indexed_string", seasonShortName);
			document.addField("seasonShortName_stored_string", seasonShortName);
		}
		if(seasonCompleteName != null) {
			document.addField("seasonCompleteName_indexed_string", seasonCompleteName);
			document.addField("seasonCompleteName_stored_string", seasonCompleteName);
		}
		super.indexCluster(document);

	}

	public void unindexSchoolSeason() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolSeason(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedSchoolSeason(String entityVar) {
		switch(entityVar) {
			case "seasonKey":
				return "seasonKey_indexed_long";
			case "enrollmentKeys":
				return "enrollmentKeys_indexed_longs";
			case "sessionKeys":
				return "sessionKeys_indexed_longs";
			case "educationSort":
				return "educationSort_indexed_int";
			case "schoolSort":
				return "schoolSort_indexed_int";
			case "yearSort":
				return "yearSort_indexed_int";
			case "seasonSort":
				return "seasonSort_indexed_int";
			case "yearKey":
				return "yearKey_indexed_long";
			case "schoolKey":
				return "schoolKey_indexed_long";
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
			case "schoolAdministratorName":
				return "schoolAdministratorName_indexed_string";
			case "yearStart":
				return "yearStart_indexed_int";
			case "yearEnd":
				return "yearEnd_indexed_int";
			case "seasonStartDate":
				return "seasonStartDate_indexed_date";
			case "seasonSummer":
				return "seasonSummer_indexed_boolean";
			case "seasonWinter":
				return "seasonWinter_indexed_boolean";
			case "seasonEnrollmentFee":
				return "seasonEnrollmentFee_indexed_double";
			case "seasonFuture":
				return "seasonFuture_indexed_boolean";
			case "seasonShortName":
				return "seasonShortName_indexed_string";
			case "seasonCompleteName":
				return "seasonCompleteName_indexed_string";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchSchoolSeason(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestSchoolSeason(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestCluster(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeSchoolSeason(solrDocument);
	}
	public void storeSchoolSeason(SolrDocument solrDocument) {
		SchoolSeason oSchoolSeason = (SchoolSeason)this;

		Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
		if(seasonKey != null)
			oSchoolSeason.setSeasonKey(seasonKey);

		List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
		if(enrollmentKeys != null)
			oSchoolSeason.enrollmentKeys.addAll(enrollmentKeys);

		List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
		if(sessionKeys != null)
			oSchoolSeason.sessionKeys.addAll(sessionKeys);

		Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
		if(educationSort != null)
			oSchoolSeason.setEducationSort(educationSort);

		Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
		if(schoolSort != null)
			oSchoolSeason.setSchoolSort(schoolSort);

		Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
		if(yearSort != null)
			oSchoolSeason.setYearSort(yearSort);

		Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
		if(seasonSort != null)
			oSchoolSeason.setSeasonSort(seasonSort);

		Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
		if(yearKey != null)
			oSchoolSeason.setYearKey(yearKey);

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oSchoolSeason.setSchoolKey(schoolKey);

		String schoolName = (String)solrDocument.get("schoolName_stored_string");
		if(schoolName != null)
			oSchoolSeason.setSchoolName(schoolName);

		String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
		if(schoolCompleteName != null)
			oSchoolSeason.setSchoolCompleteName(schoolCompleteName);

		String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
		if(schoolLocation != null)
			oSchoolSeason.setSchoolLocation(schoolLocation);

		String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
		if(schoolAddress != null)
			oSchoolSeason.setSchoolAddress(schoolAddress);

		String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
		if(schoolPhoneNumber != null)
			oSchoolSeason.setSchoolPhoneNumber(schoolPhoneNumber);

		String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
		if(schoolAdministratorName != null)
			oSchoolSeason.setSchoolAdministratorName(schoolAdministratorName);

		Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
		if(yearStart != null)
			oSchoolSeason.setYearStart(yearStart);

		Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
		if(yearEnd != null)
			oSchoolSeason.setYearEnd(yearEnd);

		Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
		if(seasonStartDate != null)
			oSchoolSeason.setSeasonStartDate(seasonStartDate);

		Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
		if(seasonSummer != null)
			oSchoolSeason.setSeasonSummer(seasonSummer);

		Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
		if(seasonWinter != null)
			oSchoolSeason.setSeasonWinter(seasonWinter);

		Double seasonEnrollmentFee = (Double)solrDocument.get("seasonEnrollmentFee_stored_double");
		if(seasonEnrollmentFee != null)
			oSchoolSeason.setSeasonEnrollmentFee(seasonEnrollmentFee);

		Boolean seasonFuture = (Boolean)solrDocument.get("seasonFuture_stored_boolean");
		if(seasonFuture != null)
			oSchoolSeason.setSeasonFuture(seasonFuture);

		String seasonShortName = (String)solrDocument.get("seasonShortName_stored_string");
		if(seasonShortName != null)
			oSchoolSeason.setSeasonShortName(seasonShortName);

		String seasonCompleteName = (String)solrDocument.get("seasonCompleteName_stored_string");
		if(seasonCompleteName != null)
			oSchoolSeason.setSeasonCompleteName(seasonCompleteName);

		super.storeCluster(solrDocument);
	}

	//////////////////
	// patchRequest //
	//////////////////

	public void patchRequestSchoolSeason() {
		PatchRequest patchRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getPatchRequest_).orElse(null);
		SchoolSeason original = (SchoolSeason)Optional.ofNullable(patchRequest).map(PatchRequest::getOriginal).orElse(null);
		if(original != null) {
			if(!Objects.equals(sessionKeys, original.getSessionKeys()))
				patchRequest.addVars("sessionKeys");
			if(!Objects.equals(yearKey, original.getYearKey()))
				patchRequest.addVars("yearKey");
			if(!Objects.equals(seasonStartDate, original.getSeasonStartDate()))
				patchRequest.addVars("seasonStartDate");
			if(!Objects.equals(seasonSummer, original.getSeasonSummer()))
				patchRequest.addVars("seasonSummer");
			if(!Objects.equals(seasonWinter, original.getSeasonWinter()))
				patchRequest.addVars("seasonWinter");
			if(!Objects.equals(seasonEnrollmentFee, original.getSeasonEnrollmentFee()))
				patchRequest.addVars("seasonEnrollmentFee");
			if(!Objects.equals(seasonFuture, original.getSeasonFuture()))
				patchRequest.addVars("seasonFuture");
			super.patchRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), sessionKeys, yearKey, seasonStartDate, seasonSummer, seasonWinter, seasonEnrollmentFee, seasonFuture);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolSeason))
			return false;
		SchoolSeason that = (SchoolSeason)o;
		return super.equals(o)
				&& Objects.equals( sessionKeys, that.sessionKeys )
				&& Objects.equals( yearKey, that.yearKey )
				&& Objects.equals( seasonStartDate, that.seasonStartDate )
				&& Objects.equals( seasonSummer, that.seasonSummer )
				&& Objects.equals( seasonWinter, that.seasonWinter )
				&& Objects.equals( seasonEnrollmentFee, that.seasonEnrollmentFee )
				&& Objects.equals( seasonFuture, that.seasonFuture );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolSeason { ");
		sb.append( "sessionKeys: " ).append(sessionKeys);
		sb.append( ", yearKey: " ).append(yearKey);
		sb.append( ", seasonStartDate: " ).append(seasonStartDate);
		sb.append( ", seasonSummer: " ).append(seasonSummer);
		sb.append( ", seasonWinter: " ).append(seasonWinter);
		sb.append( ", seasonEnrollmentFee: " ).append(seasonEnrollmentFee);
		sb.append( ", seasonFuture: " ).append(seasonFuture);
		sb.append(" }");
		return sb.toString();
	}
}
