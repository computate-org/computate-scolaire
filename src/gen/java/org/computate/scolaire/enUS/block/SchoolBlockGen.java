package org.computate.scolaire.enUS.block;

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
import java.time.LocalTime;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
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
import org.computate.scolaire.enUS.age.SchoolAge;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolBlockGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolBlock.class);

	public static final String SchoolBlock_UnNom = "a block";
	public static final String SchoolBlock_Ce = "this ";
	public static final String SchoolBlock_CeNom = "this block";
	public static final String SchoolBlock_Un = "a ";
	public static final String SchoolBlock_LeNom = "the block";
	public static final String SchoolBlock_NomSingulier = "block";
	public static final String SchoolBlock_NomPluriel = "blocks";
	public static final String SchoolBlock_NomActuel = "current block";
	public static final String SchoolBlock_TousNom = "all the blocks";
	public static final String SchoolBlock_RechercherTousNomPar = "search blocks by ";
	public static final String SchoolBlock_LesNoms = "the blocks";
	public static final String SchoolBlock_AucunNomTrouve = "no block found";
	public static final String SchoolBlock_NomVar = "block";
	public static final String SchoolBlock_DeNom = "of block";
	public static final String SchoolBlock_UnNomAdjectif = "a block";
	public static final String SchoolBlock_NomAdjectifSingulier = "block";
	public static final String SchoolBlock_NomAdjectifPluriel = "blocks";
	public static final String SchoolBlock_Couleur = "indigo";
	public static final String SchoolBlock_IconeGroupe = "regular";
	public static final String SchoolBlock_IconeNom = "bell";

	//////////////
	// blockKey //
	//////////////

	/**	L'entité « blockKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long blockKey;
	@JsonIgnore
	public Wrap<Long> blockKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("blockKey").o(blockKey);

	/**	<br/>L'entité « blockKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKey">Trouver l'entité blockKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockKey(Wrap<Long> c);

	public Long getBlockKey() {
		return blockKey;
	}

	public void setBlockKey(Long blockKey) {
		this.blockKey = blockKey;
		this.blockKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockKey(String o) {
		if(NumberUtils.isParsable(o))
			this.blockKey = Long.parseLong(o);
		this.blockKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockKeyInit() {
		if(!blockKeyWrap.alreadyInitialized) {
			_blockKey(blockKeyWrap);
			if(blockKey == null)
				setBlockKey(blockKeyWrap.o);
		}
		blockKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Long solrBlockKey() {
		return blockKey;
	}

	public String strBlockKey() {
		return blockKey == null ? "" : blockKey.toString();
	}

	public String jsonBlockKey() {
		return blockKey == null ? "" : blockKey.toString();
	}

	public String nomAffichageBlockKey() {
		return "key";
	}

	public String htmTooltipBlockKey() {
		return null;
	}

	public String htmBlockKey() {
		return blockKey == null ? "" : StringEscapeUtils.escapeHtml4(strBlockKey());
	}

	//////////////
	// childKey //
	//////////////

	/**	L'entité « childKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long childKey;
	@JsonIgnore
	public Wrap<Long> childKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("childKey").o(childKey);

	/**	<br/>L'entité « childKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childKey">Trouver l'entité childKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _childKey(Wrap<Long> c);

	public Long getChildKey() {
		return childKey;
	}

	public void setChildKey(Long childKey) {
		this.childKey = childKey;
		this.childKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setChildKey(String o) {
		if(NumberUtils.isParsable(o))
			this.childKey = Long.parseLong(o);
		this.childKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock childKeyInit() {
		if(!childKeyWrap.alreadyInitialized) {
			_childKey(childKeyWrap);
			if(childKey == null)
				setChildKey(childKeyWrap.o);
		}
		childKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Long solrChildKey() {
		return childKey;
	}

	public String strChildKey() {
		return childKey == null ? "" : childKey.toString();
	}

	public String jsonChildKey() {
		return childKey == null ? "" : childKey.toString();
	}

	public String nomAffichageChildKey() {
		return "child";
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

	/**	L'entité « enrollmentKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> enrollmentKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> enrollmentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("enrollmentKeys").o(enrollmentKeys);

	/**	<br/>L'entité « enrollmentKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Trouver l'entité enrollmentKeys dans Solr</a>
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
	public SchoolBlock addEnrollmentKeys(Long...objets) {
		for(Long o : objets) {
			addEnrollmentKeys(o);
		}
		return (SchoolBlock)this;
	}
	public SchoolBlock addEnrollmentKeys(Long o) {
		if(o != null && !enrollmentKeys.contains(o))
			this.enrollmentKeys.add(o);
		return (SchoolBlock)this;
	}
	public SchoolBlock setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
		return (SchoolBlock)this;
	}
	public SchoolBlock addEnrollmentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnrollmentKeys(p);
		}
		return (SchoolBlock)this;
	}
	protected SchoolBlock enrollmentKeysInit() {
		if(!enrollmentKeysWrap.alreadyInitialized) {
			_enrollmentKeys(enrollmentKeys);
		}
		enrollmentKeysWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "enrollments")
				.a("class", "valueObjectSuggest suggestEnrollmentKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setEnrollmentKeys")
				.a("id", classApiMethodMethod, "_enrollmentKeys")
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolBlockEnrollmentKeys($(this).val() ? searchSchoolEnrollmentFilters($('#suggestSchoolBlockEnrollmentKeys')) : [{'name':'fq','value':'blockKeys:", pk, "'}], $('#listSchoolBlockEnrollmentKeys_", classApiMethodMethod, "'), ", pk, "); ")
			.fg();

	}

	public void htmEnrollmentKeys(String classApiMethodMethod) {
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolBlockEnrollmentKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-purple w3-hover-purple ").f();
								e("i").a("class", "fas fa-edit w3-padding-small ").f().g("i");
								sx("enrollments");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate enrollments to this block");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolBlockEnrollmentKeys_", classApiMethodMethod).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
										.a("onclick", "postSchoolEnrollmentVals({ blockKeys: [ \"", pk, "\" ] }, function() { patchSchoolBlockVals([{ name: 'fq', value: 'pk:", pk, "' }], {}, function() { suggestSchoolBlockEnrollmentKeys($('#' + ($('#", classApiMethodMethod, "enrollmentKeys').val() ? 'suggest' : 'form') + 'SchoolBlockEnrollmentKeys'), $('#listSchoolBlockEnrollmentKeys_", classApiMethodMethod, "')); var $e = $('#", classApiMethodMethod, "enrollmentKeys'); $e.html($e.val()); }, function() { addError($('#", classApiMethodMethod, "enrollmentKeys')); }); }, function() { addError($('#", classApiMethodMethod, "enrollmentKeys')); });")
										.f().sx("add an enrollment")
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Trouver l'entité educationSort dans Solr</a>
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
	public SchoolBlock setEducationSort(String o) {
		if(NumberUtils.isParsable(o))
			this.educationSort = Integer.parseInt(o);
		this.educationSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock educationSortInit() {
		if(!educationSortWrap.alreadyInitialized) {
			_educationSort(educationSortWrap);
			if(educationSort == null)
				setEducationSort(educationSortWrap.o);
		}
		educationSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Trouver l'entité schoolSort dans Solr</a>
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
	public SchoolBlock setSchoolSort(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolSort = Integer.parseInt(o);
		this.schoolSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock schoolSortInit() {
		if(!schoolSortWrap.alreadyInitialized) {
			_schoolSort(schoolSortWrap);
			if(schoolSort == null)
				setSchoolSort(schoolSortWrap.o);
		}
		schoolSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSort">Trouver l'entité yearSort dans Solr</a>
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
	public SchoolBlock setYearSort(String o) {
		if(NumberUtils.isParsable(o))
			this.yearSort = Integer.parseInt(o);
		this.yearSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock yearSortInit() {
		if(!yearSortWrap.alreadyInitialized) {
			_yearSort(yearSortWrap);
			if(yearSort == null)
				setYearSort(yearSortWrap.o);
		}
		yearSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSort">Trouver l'entité seasonSort dans Solr</a>
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
	public SchoolBlock setSeasonSort(String o) {
		if(NumberUtils.isParsable(o))
			this.seasonSort = Integer.parseInt(o);
		this.seasonSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonSortInit() {
		if(!seasonSortWrap.alreadyInitialized) {
			_seasonSort(seasonSortWrap);
			if(seasonSort == null)
				setSeasonSort(seasonSortWrap.o);
		}
		seasonSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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

	/////////////////
	// sessionSort //
	/////////////////

	/**	L'entité « sessionSort »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer sessionSort;
	@JsonIgnore
	public Wrap<Integer> sessionSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("sessionSort").o(sessionSort);

	/**	<br/>L'entité « sessionSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionSort">Trouver l'entité sessionSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionSort(Wrap<Integer> c);

	public Integer getSessionSort() {
		return sessionSort;
	}

	public void setSessionSort(Integer sessionSort) {
		this.sessionSort = sessionSort;
		this.sessionSortWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSessionSort(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionSort = Integer.parseInt(o);
		this.sessionSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock sessionSortInit() {
		if(!sessionSortWrap.alreadyInitialized) {
			_sessionSort(sessionSortWrap);
			if(sessionSort == null)
				setSessionSort(sessionSortWrap.o);
		}
		sessionSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrSessionSort() {
		return sessionSort;
	}

	public String strSessionSort() {
		return sessionSort == null ? "" : sessionSort.toString();
	}

	public String jsonSessionSort() {
		return sessionSort == null ? "" : sessionSort.toString();
	}

	public String nomAffichageSessionSort() {
		return null;
	}

	public String htmTooltipSessionSort() {
		return null;
	}

	public String htmSessionSort() {
		return sessionSort == null ? "" : StringEscapeUtils.escapeHtml4(strSessionSort());
	}

	/////////////
	// ageSort //
	/////////////

	/**	L'entité « ageSort »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer ageSort;
	@JsonIgnore
	public Wrap<Integer> ageSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageSort").o(ageSort);

	/**	<br/>L'entité « ageSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageSort">Trouver l'entité ageSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageSort(Wrap<Integer> c);

	public Integer getAgeSort() {
		return ageSort;
	}

	public void setAgeSort(Integer ageSort) {
		this.ageSort = ageSort;
		this.ageSortWrap.alreadyInitialized = true;
	}
	public SchoolBlock setAgeSort(String o) {
		if(NumberUtils.isParsable(o))
			this.ageSort = Integer.parseInt(o);
		this.ageSortWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock ageSortInit() {
		if(!ageSortWrap.alreadyInitialized) {
			_ageSort(ageSortWrap);
			if(ageSort == null)
				setAgeSort(ageSortWrap.o);
		}
		ageSortWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrAgeSort() {
		return ageSort;
	}

	public String strAgeSort() {
		return ageSort == null ? "" : ageSort.toString();
	}

	public String jsonAgeSort() {
		return ageSort == null ? "" : ageSort.toString();
	}

	public String nomAffichageAgeSort() {
		return null;
	}

	public String htmTooltipAgeSort() {
		return null;
	}

	public String htmAgeSort() {
		return ageSort == null ? "" : StringEscapeUtils.escapeHtml4(strAgeSort());
	}

	////////////
	// ageKey //
	////////////

	/**	L'entité « ageKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long ageKey;
	@JsonIgnore
	public Wrap<Long> ageKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("ageKey").o(ageKey);

	/**	<br/>L'entité « ageKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKey">Trouver l'entité ageKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageKey(Wrap<Long> c);

	public Long getAgeKey() {
		return ageKey;
	}

	public void setAgeKey(Long ageKey) {
		this.ageKey = ageKey;
		this.ageKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setAgeKey(String o) {
		if(NumberUtils.isParsable(o))
			this.ageKey = Long.parseLong(o);
		this.ageKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock ageKeyInit() {
		if(!ageKeyWrap.alreadyInitialized) {
			_ageKey(ageKeyWrap);
			if(ageKey == null)
				setAgeKey(ageKeyWrap.o);
		}
		ageKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Long solrAgeKey() {
		return ageKey;
	}

	public String strAgeKey() {
		return ageKey == null ? "" : ageKey.toString();
	}

	public String jsonAgeKey() {
		return ageKey == null ? "" : ageKey.toString();
	}

	public String nomAffichageAgeKey() {
		return "age";
	}

	public String htmTooltipAgeKey() {
		return null;
	}

	public String htmAgeKey() {
		return ageKey == null ? "" : StringEscapeUtils.escapeHtml4(strAgeKey());
	}

	public void inputAgeKey(String classApiMethodMethod) {
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "age")
				.a("class", "valueObjectSuggest suggestAgeKey w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setAgeKey")
				.a("id", classApiMethodMethod, "_ageKey")
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolBlockAgeKey($(this).val() ? searchSchoolAgeFilters($('#suggestSchoolBlockAgeKey')) : [{'name':'fq','value':'blockKeys:", pk, "'}], $('#listSchoolBlockAgeKey_", classApiMethodMethod, "'), ", pk, "); ")
			.fg();

	}

	public void htmAgeKey(String classApiMethodMethod) {
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolBlockAgeKey").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue w3-hover-blue ").f();
								e("i").a("class", "fad fa-birthday-cake w3-padding-small ").f().g("i");
								sx("age");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate an age to this block");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputAgeKey(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolBlockAgeKey_", classApiMethodMethod).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
										.a("onclick", "postSchoolAgeVals({ blockKeys: [ \"", pk, "\" ] }, function() { patchSchoolBlockVals([{ name: 'fq', value: 'pk:", pk, "' }], {}, function() { suggestSchoolBlockAgeKey($('#' + ($('#", classApiMethodMethod, "ageKey').val() ? 'suggest' : 'form') + 'SchoolBlockAgeKey'), $('#listSchoolBlockAgeKey_", classApiMethodMethod, "')); var $e = $('#", classApiMethodMethod, "ageKey'); $e.html($e.val()); }, function() { addError($('#", classApiMethodMethod, "ageKey')); }); }, function() { addError($('#", classApiMethodMethod, "ageKey')); });")
										.f().sx("add an age")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////
	// ageSearch //
	///////////////

	/**	L'entité « ageSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolAge>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolAge> ageSearch = new SearchList<SchoolAge>();
	@JsonIgnore
	public Wrap<SearchList<SchoolAge>> ageSearchWrap = new Wrap<SearchList<SchoolAge>>().p(this).c(SearchList.class).var("ageSearch").o(ageSearch);

	/**	<br/>L'entité « ageSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolAge>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageSearch">Trouver l'entité ageSearch dans Solr</a>
	 * <br/>
	 * @param ageSearch est l'entité déjà construit. 
	 **/
	protected abstract void _ageSearch(SearchList<SchoolAge> l);

	public SearchList<SchoolAge> getAgeSearch() {
		return ageSearch;
	}

	public void setAgeSearch(SearchList<SchoolAge> ageSearch) {
		this.ageSearch = ageSearch;
		this.ageSearchWrap.alreadyInitialized = true;
	}
	protected SchoolBlock ageSearchInit() {
		if(!ageSearchWrap.alreadyInitialized) {
			_ageSearch(ageSearch);
		}
		ageSearch.initDeepForClass(siteRequest_);
		ageSearchWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	//////////
	// age_ //
	//////////

	/**	L'entité « age_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SchoolAge age_;
	@JsonIgnore
	public Wrap<SchoolAge> age_Wrap = new Wrap<SchoolAge>().p(this).c(SchoolAge.class).var("age_").o(age_);

	/**	<br/>L'entité « age_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:age_">Trouver l'entité age_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _age_(Wrap<SchoolAge> c);

	public SchoolAge getAge_() {
		return age_;
	}

	public void setAge_(SchoolAge age_) {
		this.age_ = age_;
		this.age_Wrap.alreadyInitialized = true;
	}
	protected SchoolBlock age_Init() {
		if(!age_Wrap.alreadyInitialized) {
			_age_(age_Wrap);
			if(age_ == null)
				setAge_(age_Wrap.o);
		}
		age_Wrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Trouver l'entité schoolKey dans Solr</a>
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
	public SchoolBlock setSchoolKey(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolKey = Long.parseLong(o);
		this.schoolKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Trouver l'entité yearKey dans Solr</a>
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
	public SchoolBlock setYearKey(String o) {
		if(NumberUtils.isParsable(o))
			this.yearKey = Long.parseLong(o);
		this.yearKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock yearKeyInit() {
		if(!yearKeyWrap.alreadyInitialized) {
			_yearKey(yearKeyWrap);
			if(yearKey == null)
				setYearKey(yearKeyWrap.o);
		}
		yearKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKey">Trouver l'entité seasonKey dans Solr</a>
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
	public SchoolBlock setSeasonKey(String o) {
		if(NumberUtils.isParsable(o))
			this.seasonKey = Long.parseLong(o);
		this.seasonKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonKeyInit() {
		if(!seasonKeyWrap.alreadyInitialized) {
			_seasonKey(seasonKeyWrap);
			if(seasonKey == null)
				setSeasonKey(seasonKeyWrap.o);
		}
		seasonKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
		return "year";
	}

	public String htmTooltipSeasonKey() {
		return null;
	}

	public String htmSeasonKey() {
		return seasonKey == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonKey());
	}

	////////////////
	// sessionKey //
	////////////////

	/**	L'entité « sessionKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long sessionKey;
	@JsonIgnore
	public Wrap<Long> sessionKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("sessionKey").o(sessionKey);

	/**	<br/>L'entité « sessionKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKey">Trouver l'entité sessionKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionKey(Wrap<Long> c);

	public Long getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(Long sessionKey) {
		this.sessionKey = sessionKey;
		this.sessionKeyWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSessionKey(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionKey = Long.parseLong(o);
		this.sessionKeyWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock sessionKeyInit() {
		if(!sessionKeyWrap.alreadyInitialized) {
			_sessionKey(sessionKeyWrap);
			if(sessionKey == null)
				setSessionKey(sessionKeyWrap.o);
		}
		sessionKeyWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Long solrSessionKey() {
		return sessionKey;
	}

	public String strSessionKey() {
		return sessionKey == null ? "" : sessionKey.toString();
	}

	public String jsonSessionKey() {
		return sessionKey == null ? "" : sessionKey.toString();
	}

	public String nomAffichageSessionKey() {
		return "session";
	}

	public String htmTooltipSessionKey() {
		return null;
	}

	public String htmSessionKey() {
		return sessionKey == null ? "" : StringEscapeUtils.escapeHtml4(strSessionKey());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Trouver l'entité schoolName dans Solr</a>
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
	protected SchoolBlock schoolNameInit() {
		if(!schoolNameWrap.alreadyInitialized) {
			_schoolName(schoolNameWrap);
			if(schoolName == null)
				setSchoolName(schoolNameWrap.o);
		}
		schoolNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Trouver l'entité schoolCompleteName dans Solr</a>
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
	protected SchoolBlock schoolCompleteNameInit() {
		if(!schoolCompleteNameWrap.alreadyInitialized) {
			_schoolCompleteName(schoolCompleteNameWrap);
			if(schoolCompleteName == null)
				setSchoolCompleteName(schoolCompleteNameWrap.o);
		}
		schoolCompleteNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Trouver l'entité schoolLocation dans Solr</a>
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
	protected SchoolBlock schoolLocationInit() {
		if(!schoolLocationWrap.alreadyInitialized) {
			_schoolLocation(schoolLocationWrap);
			if(schoolLocation == null)
				setSchoolLocation(schoolLocationWrap.o);
		}
		schoolLocationWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Trouver l'entité schoolAddress dans Solr</a>
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
	protected SchoolBlock schoolAddressInit() {
		if(!schoolAddressWrap.alreadyInitialized) {
			_schoolAddress(schoolAddressWrap);
			if(schoolAddress == null)
				setSchoolAddress(schoolAddressWrap.o);
		}
		schoolAddressWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Trouver l'entité schoolPhoneNumber dans Solr</a>
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
	protected SchoolBlock schoolPhoneNumberInit() {
		if(!schoolPhoneNumberWrap.alreadyInitialized) {
			_schoolPhoneNumber(schoolPhoneNumberWrap);
			if(schoolPhoneNumber == null)
				setSchoolPhoneNumber(schoolPhoneNumberWrap.o);
		}
		schoolPhoneNumberWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Trouver l'entité schoolAdministratorName dans Solr</a>
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
	protected SchoolBlock schoolAdministratorNameInit() {
		if(!schoolAdministratorNameWrap.alreadyInitialized) {
			_schoolAdministratorName(schoolAdministratorNameWrap);
			if(schoolAdministratorName == null)
				setSchoolAdministratorName(schoolAdministratorNameWrap.o);
		}
		schoolAdministratorNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Trouver l'entité yearStart dans Solr</a>
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
	public SchoolBlock setYearStart(String o) {
		if(NumberUtils.isParsable(o))
			this.yearStart = Integer.parseInt(o);
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock yearStartInit() {
		if(!yearStartWrap.alreadyInitialized) {
			_yearStart(yearStartWrap);
			if(yearStart == null)
				setYearStart(yearStartWrap.o);
		}
		yearStartWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Trouver l'entité yearEnd dans Solr</a>
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
	public SchoolBlock setYearEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.yearEnd = Integer.parseInt(o);
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock yearEndInit() {
		if(!yearEndWrap.alreadyInitialized) {
			_yearEnd(yearEndWrap);
			if(yearEnd == null)
				setYearEnd(yearEndWrap.o);
		}
		yearEndWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Trouver l'entité seasonStartDate dans Solr</a>
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
	public SchoolBlock setSeasonStartDate(Instant o) {
		this.seasonStartDate = LocalDate.from(o);
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setSeasonStartDate(String o) {
		this.seasonStartDate = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSeasonStartDate(Date o) {
		this.seasonStartDate = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonStartDateInit() {
		if(!seasonStartDateWrap.alreadyInitialized) {
			_seasonStartDate(seasonStartDateWrap);
			if(seasonStartDate == null)
				setSeasonStartDate(seasonStartDateWrap.o);
		}
		seasonStartDateWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
		return "start of season";
	}

	public String htmTooltipSeasonStartDate() {
		return null;
	}

	public String htmSeasonStartDate() {
		return seasonStartDate == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonStartDate());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSummer">Trouver l'entité seasonSummer dans Solr</a>
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
	public SchoolBlock setSeasonSummer(String o) {
		this.seasonSummer = Boolean.parseBoolean(o);
		this.seasonSummerWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonSummerInit() {
		if(!seasonSummerWrap.alreadyInitialized) {
			_seasonSummer(seasonSummerWrap);
			if(seasonSummer == null)
				setSeasonSummer(seasonSummerWrap.o);
		}
		seasonSummerWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonWinter">Trouver l'entité seasonWinter dans Solr</a>
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
	public SchoolBlock setSeasonWinter(String o) {
		this.seasonWinter = Boolean.parseBoolean(o);
		this.seasonWinterWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonWinterInit() {
		if(!seasonWinterWrap.alreadyInitialized) {
			_seasonWinter(seasonWinterWrap);
			if(seasonWinter == null)
				setSeasonWinter(seasonWinterWrap.o);
		}
		seasonWinterWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonEnrollmentFee">Trouver l'entité seasonEnrollmentFee dans Solr</a>
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
	public SchoolBlock setSeasonEnrollmentFee(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSeasonEnrollmentFee(Double o) {
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSeasonEnrollmentFee(Integer o) {
			this.seasonEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.seasonEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock seasonEnrollmentFeeInit() {
		if(!seasonEnrollmentFeeWrap.alreadyInitialized) {
			_seasonEnrollmentFee(seasonEnrollmentFeeWrap);
			if(seasonEnrollmentFee == null)
				setSeasonEnrollmentFee(seasonEnrollmentFeeWrap.o);
		}
		seasonEnrollmentFeeWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Double solrSeasonEnrollmentFee() {
		return seasonEnrollmentFee == null ? null : seasonEnrollmentFee.doubleValue();
	}

	public String strSeasonEnrollmentFee() {
		return seasonEnrollmentFee == null ? "" : seasonEnrollmentFee.toString();
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonCompleteName">Trouver l'entité seasonCompleteName dans Solr</a>
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
	protected SchoolBlock seasonCompleteNameInit() {
		if(!seasonCompleteNameWrap.alreadyInitialized) {
			_seasonCompleteName(seasonCompleteNameWrap);
			if(seasonCompleteName == null)
				setSeasonCompleteName(seasonCompleteNameWrap.o);
		}
		seasonCompleteNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
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
		return null;
	}

	public String htmTooltipSeasonCompleteName() {
		return null;
	}

	public String htmSeasonCompleteName() {
		return seasonCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonCompleteName());
	}

	/////////////////////
	// sessionStartDay //
	/////////////////////

	/**	L'entité « sessionStartDay »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate sessionStartDay;
	@JsonIgnore
	public Wrap<LocalDate> sessionStartDayWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionStartDay").o(sessionStartDay);

	/**	<br/>L'entité « sessionStartDay »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionStartDay">Trouver l'entité sessionStartDay dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionStartDay(Wrap<LocalDate> c);

	public LocalDate getSessionStartDay() {
		return sessionStartDay;
	}

	public void setSessionStartDay(LocalDate sessionStartDay) {
		this.sessionStartDay = sessionStartDay;
		this.sessionStartDayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSessionStartDay(Instant o) {
		this.sessionStartDay = LocalDate.from(o);
		this.sessionStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setSessionStartDay(String o) {
		this.sessionStartDay = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSessionStartDay(Date o) {
		this.sessionStartDay = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionStartDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock sessionStartDayInit() {
		if(!sessionStartDayWrap.alreadyInitialized) {
			_sessionStartDay(sessionStartDayWrap);
			if(sessionStartDay == null)
				setSessionStartDay(sessionStartDayWrap.o);
		}
		sessionStartDayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Date solrSessionStartDay() {
		return sessionStartDay == null ? null : Date.from(sessionStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSessionStartDay() {
		return sessionStartDay == null ? "" : sessionStartDay.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonSessionStartDay() {
		return sessionStartDay == null ? "" : sessionStartDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageSessionStartDay() {
		return "start of the session";
	}

	public String htmTooltipSessionStartDay() {
		return null;
	}

	public String htmSessionStartDay() {
		return sessionStartDay == null ? "" : StringEscapeUtils.escapeHtml4(strSessionStartDay());
	}

	///////////////////
	// sessionEndDay //
	///////////////////

	/**	L'entité « sessionEndDay »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate sessionEndDay;
	@JsonIgnore
	public Wrap<LocalDate> sessionEndDayWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionEndDay").o(sessionEndDay);

	/**	<br/>L'entité « sessionEndDay »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionEndDay">Trouver l'entité sessionEndDay dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionEndDay(Wrap<LocalDate> c);

	public LocalDate getSessionEndDay() {
		return sessionEndDay;
	}

	public void setSessionEndDay(LocalDate sessionEndDay) {
		this.sessionEndDay = sessionEndDay;
		this.sessionEndDayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setSessionEndDay(Instant o) {
		this.sessionEndDay = LocalDate.from(o);
		this.sessionEndDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolBlock setSessionEndDay(String o) {
		this.sessionEndDay = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionEndDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setSessionEndDay(Date o) {
		this.sessionEndDay = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionEndDayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock sessionEndDayInit() {
		if(!sessionEndDayWrap.alreadyInitialized) {
			_sessionEndDay(sessionEndDayWrap);
			if(sessionEndDay == null)
				setSessionEndDay(sessionEndDayWrap.o);
		}
		sessionEndDayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Date solrSessionEndDay() {
		return sessionEndDay == null ? null : Date.from(sessionEndDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSessionEndDay() {
		return sessionEndDay == null ? "" : sessionEndDay.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonSessionEndDay() {
		return sessionEndDay == null ? "" : sessionEndDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageSessionEndDay() {
		return "end of the session";
	}

	public String htmTooltipSessionEndDay() {
		return null;
	}

	public String htmSessionEndDay() {
		return sessionEndDay == null ? "" : StringEscapeUtils.escapeHtml4(strSessionEndDay());
	}

	/////////////////////
	// ageCompleteName //
	/////////////////////

	/**	L'entité « ageCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String ageCompleteName;
	@JsonIgnore
	public Wrap<String> ageCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("ageCompleteName").o(ageCompleteName);

	/**	<br/>L'entité « ageCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageCompleteName">Trouver l'entité ageCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageCompleteName(Wrap<String> c);

	public String getAgeCompleteName() {
		return ageCompleteName;
	}

	public void setAgeCompleteName(String ageCompleteName) {
		this.ageCompleteName = ageCompleteName;
		this.ageCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolBlock ageCompleteNameInit() {
		if(!ageCompleteNameWrap.alreadyInitialized) {
			_ageCompleteName(ageCompleteNameWrap);
			if(ageCompleteName == null)
				setAgeCompleteName(ageCompleteNameWrap.o);
		}
		ageCompleteNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrAgeCompleteName() {
		return ageCompleteName;
	}

	public String strAgeCompleteName() {
		return ageCompleteName == null ? "" : ageCompleteName;
	}

	public String jsonAgeCompleteName() {
		return ageCompleteName == null ? "" : ageCompleteName;
	}

	public String nomAffichageAgeCompleteName() {
		return null;
	}

	public String htmTooltipAgeCompleteName() {
		return null;
	}

	public String htmAgeCompleteName() {
		return ageCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strAgeCompleteName());
	}

	//////////////
	// ageStart //
	//////////////

	/**	L'entité « ageStart »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer ageStart;
	@JsonIgnore
	public Wrap<Integer> ageStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageStart").o(ageStart);

	/**	<br/>L'entité « ageStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageStart">Trouver l'entité ageStart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageStart(Wrap<Integer> c);

	public Integer getAgeStart() {
		return ageStart;
	}

	public void setAgeStart(Integer ageStart) {
		this.ageStart = ageStart;
		this.ageStartWrap.alreadyInitialized = true;
	}
	public SchoolBlock setAgeStart(String o) {
		if(NumberUtils.isParsable(o))
			this.ageStart = Integer.parseInt(o);
		this.ageStartWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock ageStartInit() {
		if(!ageStartWrap.alreadyInitialized) {
			_ageStart(ageStartWrap);
			if(ageStart == null)
				setAgeStart(ageStartWrap.o);
		}
		ageStartWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrAgeStart() {
		return ageStart;
	}

	public String strAgeStart() {
		return ageStart == null ? "" : ageStart.toString();
	}

	public String jsonAgeStart() {
		return ageStart == null ? "" : ageStart.toString();
	}

	public String nomAffichageAgeStart() {
		return "start of the age group";
	}

	public String htmTooltipAgeStart() {
		return null;
	}

	public String htmAgeStart() {
		return ageStart == null ? "" : StringEscapeUtils.escapeHtml4(strAgeStart());
	}

	////////////
	// ageEnd //
	////////////

	/**	L'entité « ageEnd »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer ageEnd;
	@JsonIgnore
	public Wrap<Integer> ageEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageEnd").o(ageEnd);

	/**	<br/>L'entité « ageEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageEnd">Trouver l'entité ageEnd dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageEnd(Wrap<Integer> c);

	public Integer getAgeEnd() {
		return ageEnd;
	}

	public void setAgeEnd(Integer ageEnd) {
		this.ageEnd = ageEnd;
		this.ageEndWrap.alreadyInitialized = true;
	}
	public SchoolBlock setAgeEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.ageEnd = Integer.parseInt(o);
		this.ageEndWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock ageEndInit() {
		if(!ageEndWrap.alreadyInitialized) {
			_ageEnd(ageEndWrap);
			if(ageEnd == null)
				setAgeEnd(ageEndWrap.o);
		}
		ageEndWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Integer solrAgeEnd() {
		return ageEnd;
	}

	public String strAgeEnd() {
		return ageEnd == null ? "" : ageEnd.toString();
	}

	public String jsonAgeEnd() {
		return ageEnd == null ? "" : ageEnd.toString();
	}

	public String nomAffichageAgeEnd() {
		return "end of the age group";
	}

	public String htmTooltipAgeEnd() {
		return null;
	}

	public String htmAgeEnd() {
		return ageEnd == null ? "" : StringEscapeUtils.escapeHtml4(strAgeEnd());
	}

	////////////////////
	// blockStartTime //
	////////////////////

	/**	L'entité « blockStartTime »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalTime blockStartTime;
	@JsonIgnore
	public Wrap<LocalTime> blockStartTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockStartTime").o(blockStartTime);

	/**	<br/>L'entité « blockStartTime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockStartTime">Trouver l'entité blockStartTime dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockStartTime(Wrap<LocalTime> c);

	public LocalTime getBlockStartTime() {
		return blockStartTime;
	}

	public void setBlockStartTime(LocalTime blockStartTime) {
		this.blockStartTime = blockStartTime;
		this.blockStartTimeWrap.alreadyInitialized = true;
	}
	/** Example: 01:00 **/
	public SchoolBlock setBlockStartTime(String o) {
		try {
			this.blockStartTime = LocalTime.parse(o, DateTimeFormatter.ofPattern("HH mm"));
			this.blockStartTimeWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockStartTimeInit() {
		if(!blockStartTimeWrap.alreadyInitialized) {
			_blockStartTime(blockStartTimeWrap);
			if(blockStartTime == null)
				setBlockStartTime(blockStartTimeWrap.o);
		}
		blockStartTimeWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrBlockStartTime() {
		return blockStartTime == null ? null : blockStartTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public String strBlockStartTime() {
		return blockStartTime == null ? "" : blockStartTime.format(DateTimeFormatter.ofPattern("h:mm a", Locale.US));
	}

	public String jsonBlockStartTime() {
		return blockStartTime == null ? "" : blockStartTime.format(DateTimeFormatter.ofPattern("HH mm", Locale.US));
	}

	public String nomAffichageBlockStartTime() {
		return "start time";
	}

	public String htmTooltipBlockStartTime() {
		return null;
	}

	public String htmBlockStartTime() {
		return blockStartTime == null ? "" : StringEscapeUtils.escapeHtml4(strBlockStartTime());
	}

	public void inputBlockStartTime(String classApiMethodMethod) {
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border timepicker ")
			.a("placeholder", "HH:MM AM")
			.a("id", classApiMethodMethod, "_blockStartTime")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", blockStartTime == null ? "" : DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("en-US")).format(blockStartTime))
			.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, 'HH MM'); patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockStartTime', s, function() { addGlow($('#", classApiMethodMethod, "_blockStartTime')); }, function() { addError($('#", classApiMethodMethod, "_blockStartTime')); }); } ")
			.fg();
	}

	public void htmBlockStartTime(String classApiMethodMethod) {
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolBlockBlockStartTime").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockStartTime").a("class", "").f().sx("start time").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputBlockStartTime(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_blockStartTime')); $('#", classApiMethodMethod, "_blockStartTime').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=pk]').val() }], 'setBlockStartTime', null, function() { addGlow($('#", classApiMethodMethod, "_blockStartTime')); }, function() { addError($('#", classApiMethodMethod, "_blockStartTime')); }); ")
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
	// blockEndTime //
	//////////////////

	/**	L'entité « blockEndTime »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalTime blockEndTime;
	@JsonIgnore
	public Wrap<LocalTime> blockEndTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockEndTime").o(blockEndTime);

	/**	<br/>L'entité « blockEndTime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockEndTime">Trouver l'entité blockEndTime dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockEndTime(Wrap<LocalTime> c);

	public LocalTime getBlockEndTime() {
		return blockEndTime;
	}

	public void setBlockEndTime(LocalTime blockEndTime) {
		this.blockEndTime = blockEndTime;
		this.blockEndTimeWrap.alreadyInitialized = true;
	}
	/** Example: 01:00 **/
	public SchoolBlock setBlockEndTime(String o) {
		try {
			this.blockEndTime = LocalTime.parse(o, DateTimeFormatter.ofPattern("HH mm"));
			this.blockEndTimeWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockEndTimeInit() {
		if(!blockEndTimeWrap.alreadyInitialized) {
			_blockEndTime(blockEndTimeWrap);
			if(blockEndTime == null)
				setBlockEndTime(blockEndTimeWrap.o);
		}
		blockEndTimeWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrBlockEndTime() {
		return blockEndTime == null ? null : blockEndTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public String strBlockEndTime() {
		return blockEndTime == null ? "" : blockEndTime.format(DateTimeFormatter.ofPattern("h:mm a", Locale.US));
	}

	public String jsonBlockEndTime() {
		return blockEndTime == null ? "" : blockEndTime.format(DateTimeFormatter.ofPattern("HH mm", Locale.US));
	}

	public String nomAffichageBlockEndTime() {
		return "end time";
	}

	public String htmTooltipBlockEndTime() {
		return null;
	}

	public String htmBlockEndTime() {
		return blockEndTime == null ? "" : StringEscapeUtils.escapeHtml4(strBlockEndTime());
	}

	public void inputBlockEndTime(String classApiMethodMethod) {
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border timepicker ")
			.a("placeholder", "HH:MM AM")
			.a("id", classApiMethodMethod, "_blockEndTime")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", blockEndTime == null ? "" : DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("en-US")).format(blockEndTime))
			.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, 'HH MM'); patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockEndTime', s, function() { addGlow($('#", classApiMethodMethod, "_blockEndTime')); }, function() { addError($('#", classApiMethodMethod, "_blockEndTime')); }); } ")
			.fg();
	}

	public void htmBlockEndTime(String classApiMethodMethod) {
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolBlockBlockEndTime").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockEndTime").a("class", "").f().sx("end time").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputBlockEndTime(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_blockEndTime')); $('#", classApiMethodMethod, "_blockEndTime').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=pk]').val() }], 'setBlockEndTime', null, function() { addGlow($('#", classApiMethodMethod, "_blockEndTime')); }, function() { addError($('#", classApiMethodMethod, "_blockEndTime')); }); ")
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

	////////////////////////
	// blockPricePerMonth //
	////////////////////////

	/**	L'entité « blockPricePerMonth »
	 *	 is defined as null before being initialized. 
	 */
	protected BigDecimal blockPricePerMonth;
	@JsonIgnore
	public Wrap<BigDecimal> blockPricePerMonthWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("blockPricePerMonth").o(blockPricePerMonth);

	/**	<br/>L'entité « blockPricePerMonth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockPricePerMonth">Trouver l'entité blockPricePerMonth dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockPricePerMonth(Wrap<BigDecimal> c);

	public BigDecimal getBlockPricePerMonth() {
		return blockPricePerMonth;
	}

	public void setBlockPricePerMonth(BigDecimal blockPricePerMonth) {
		this.blockPricePerMonth = blockPricePerMonth;
		this.blockPricePerMonthWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockPricePerMonth(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setBlockPricePerMonth(Double o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setBlockPricePerMonth(Integer o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockPricePerMonthInit() {
		if(!blockPricePerMonthWrap.alreadyInitialized) {
			_blockPricePerMonth(blockPricePerMonthWrap);
			if(blockPricePerMonth == null)
				setBlockPricePerMonth(blockPricePerMonthWrap.o);
		}
		blockPricePerMonthWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Double solrBlockPricePerMonth() {
		return blockPricePerMonth == null ? null : blockPricePerMonth.doubleValue();
	}

	public String strBlockPricePerMonth() {
		return blockPricePerMonth == null ? "" : blockPricePerMonth.toString();
	}

	public String jsonBlockPricePerMonth() {
		return blockPricePerMonth == null ? "" : blockPricePerMonth.toString();
	}

	public String nomAffichageBlockPricePerMonth() {
		return "price per month";
	}

	public String htmTooltipBlockPricePerMonth() {
		return null;
	}

	public String htmBlockPricePerMonth() {
		return blockPricePerMonth == null ? "" : StringEscapeUtils.escapeHtml4(strBlockPricePerMonth());
	}

	public void inputBlockPricePerMonth(String classApiMethodMethod) {
		e("input")
			.a("type", "text")
			.a("placeholder", "price per month")
			.a("id", classApiMethodMethod, "_blockPricePerMonth");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setBlockPricePerMonth w3-input w3-border ");
				a("name", "setBlockPricePerMonth");
			} else {
				a("class", "valueBlockPricePerMonth w3-input w3-border ");
				a("name", "blockPricePerMonth");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockPricePerMonth', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_blockPricePerMonth')); }, function() { addError($('#", classApiMethodMethod, "_blockPricePerMonth')); }); ");
			}
			a("value", strBlockPricePerMonth())
		.fg();

	}

	public void htmBlockPricePerMonth(String classApiMethodMethod) {
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolBlockBlockPricePerMonth").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockPricePerMonth").a("class", "").f().sx("price per month").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockPricePerMonth(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_blockPricePerMonth')); $('#", classApiMethodMethod, "_blockPricePerMonth').val(null); patchSchoolBlockVal([{ name: 'fq', value: 'pk:' + $('#SchoolBlockForm :input[name=pk]').val() }], 'setBlockPricePerMonth', null, function() { addGlow($('#", classApiMethodMethod, "_blockPricePerMonth')); }, function() { addError($('#", classApiMethodMethod, "_blockPricePerMonth')); }); ")
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

	/////////////////
	// blockSunday //
	/////////////////

	/**	L'entité « blockSunday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockSunday;
	@JsonIgnore
	public Wrap<Boolean> blockSundayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockSunday").o(blockSunday);

	/**	<br/>L'entité « blockSunday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSunday">Trouver l'entité blockSunday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockSunday(Wrap<Boolean> c);

	public Boolean getBlockSunday() {
		return blockSunday;
	}

	public void setBlockSunday(Boolean blockSunday) {
		this.blockSunday = blockSunday;
		this.blockSundayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockSunday(String o) {
		this.blockSunday = Boolean.parseBoolean(o);
		this.blockSundayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockSundayInit() {
		if(!blockSundayWrap.alreadyInitialized) {
			_blockSunday(blockSundayWrap);
			if(blockSunday == null)
				setBlockSunday(blockSundayWrap.o);
		}
		blockSundayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockSunday() {
		return blockSunday;
	}

	public String strBlockSunday() {
		return blockSunday == null ? "" : blockSunday.toString();
	}

	public String jsonBlockSunday() {
		return blockSunday == null ? "" : blockSunday.toString();
	}

	public String nomAffichageBlockSunday() {
		return "sunday";
	}

	public String htmTooltipBlockSunday() {
		return null;
	}

	public String htmBlockSunday() {
		return blockSunday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockSunday());
	}

	/////////////////
	// blockMonday //
	/////////////////

	/**	L'entité « blockMonday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockMonday;
	@JsonIgnore
	public Wrap<Boolean> blockMondayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockMonday").o(blockMonday);

	/**	<br/>L'entité « blockMonday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockMonday">Trouver l'entité blockMonday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockMonday(Wrap<Boolean> c);

	public Boolean getBlockMonday() {
		return blockMonday;
	}

	public void setBlockMonday(Boolean blockMonday) {
		this.blockMonday = blockMonday;
		this.blockMondayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockMonday(String o) {
		this.blockMonday = Boolean.parseBoolean(o);
		this.blockMondayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockMondayInit() {
		if(!blockMondayWrap.alreadyInitialized) {
			_blockMonday(blockMondayWrap);
			if(blockMonday == null)
				setBlockMonday(blockMondayWrap.o);
		}
		blockMondayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockMonday() {
		return blockMonday;
	}

	public String strBlockMonday() {
		return blockMonday == null ? "" : blockMonday.toString();
	}

	public String jsonBlockMonday() {
		return blockMonday == null ? "" : blockMonday.toString();
	}

	public String nomAffichageBlockMonday() {
		return "monday";
	}

	public String htmTooltipBlockMonday() {
		return null;
	}

	public String htmBlockMonday() {
		return blockMonday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockMonday());
	}

	public void inputBlockMonday(String classApiMethodMethod) {
		e("input")
			.a("type", "checkbox")
			.a("id", classApiMethodMethod, "_blockMonday")
			.a("value", "true");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setBlockMonday");
				a("name", "setBlockMonday");
			} else {
				a("class", "valueBlockMonday");
				a("name", "blockMonday");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockMonday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockMonday')); }, function() { addError($('#", classApiMethodMethod, "_blockMonday')); }); ");
			}
			;
			if(getBlockMonday() != null && getBlockMonday())
				a("checked", "checked");
		fg();

	}

	public void htmBlockMonday(String classApiMethodMethod) {
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolBlockBlockMonday").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockMonday").a("class", "").f().sx("monday").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockMonday(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// blockTuesday //
	//////////////////

	/**	L'entité « blockTuesday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockTuesday;
	@JsonIgnore
	public Wrap<Boolean> blockTuesdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockTuesday").o(blockTuesday);

	/**	<br/>L'entité « blockTuesday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTuesday">Trouver l'entité blockTuesday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockTuesday(Wrap<Boolean> c);

	public Boolean getBlockTuesday() {
		return blockTuesday;
	}

	public void setBlockTuesday(Boolean blockTuesday) {
		this.blockTuesday = blockTuesday;
		this.blockTuesdayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockTuesday(String o) {
		this.blockTuesday = Boolean.parseBoolean(o);
		this.blockTuesdayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockTuesdayInit() {
		if(!blockTuesdayWrap.alreadyInitialized) {
			_blockTuesday(blockTuesdayWrap);
			if(blockTuesday == null)
				setBlockTuesday(blockTuesdayWrap.o);
		}
		blockTuesdayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockTuesday() {
		return blockTuesday;
	}

	public String strBlockTuesday() {
		return blockTuesday == null ? "" : blockTuesday.toString();
	}

	public String jsonBlockTuesday() {
		return blockTuesday == null ? "" : blockTuesday.toString();
	}

	public String nomAffichageBlockTuesday() {
		return "tuesday";
	}

	public String htmTooltipBlockTuesday() {
		return null;
	}

	public String htmBlockTuesday() {
		return blockTuesday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockTuesday());
	}

	public void inputBlockTuesday(String classApiMethodMethod) {
		e("input")
			.a("type", "checkbox")
			.a("id", classApiMethodMethod, "_blockTuesday")
			.a("value", "true");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setBlockTuesday");
				a("name", "setBlockTuesday");
			} else {
				a("class", "valueBlockTuesday");
				a("name", "blockTuesday");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockTuesday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockTuesday')); }, function() { addError($('#", classApiMethodMethod, "_blockTuesday')); }); ");
			}
			;
			if(getBlockTuesday() != null && getBlockTuesday())
				a("checked", "checked");
		fg();

	}

	public void htmBlockTuesday(String classApiMethodMethod) {
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolBlockBlockTuesday").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockTuesday").a("class", "").f().sx("tuesday").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockTuesday(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////
	// blockWednesday //
	////////////////////

	/**	L'entité « blockWednesday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockWednesday;
	@JsonIgnore
	public Wrap<Boolean> blockWednesdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockWednesday").o(blockWednesday);

	/**	<br/>L'entité « blockWednesday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockWednesday">Trouver l'entité blockWednesday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockWednesday(Wrap<Boolean> c);

	public Boolean getBlockWednesday() {
		return blockWednesday;
	}

	public void setBlockWednesday(Boolean blockWednesday) {
		this.blockWednesday = blockWednesday;
		this.blockWednesdayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockWednesday(String o) {
		this.blockWednesday = Boolean.parseBoolean(o);
		this.blockWednesdayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockWednesdayInit() {
		if(!blockWednesdayWrap.alreadyInitialized) {
			_blockWednesday(blockWednesdayWrap);
			if(blockWednesday == null)
				setBlockWednesday(blockWednesdayWrap.o);
		}
		blockWednesdayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockWednesday() {
		return blockWednesday;
	}

	public String strBlockWednesday() {
		return blockWednesday == null ? "" : blockWednesday.toString();
	}

	public String jsonBlockWednesday() {
		return blockWednesday == null ? "" : blockWednesday.toString();
	}

	public String nomAffichageBlockWednesday() {
		return "wednesday";
	}

	public String htmTooltipBlockWednesday() {
		return null;
	}

	public String htmBlockWednesday() {
		return blockWednesday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockWednesday());
	}

	public void inputBlockWednesday(String classApiMethodMethod) {
		e("input")
			.a("type", "checkbox")
			.a("id", classApiMethodMethod, "_blockWednesday")
			.a("value", "true");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setBlockWednesday");
				a("name", "setBlockWednesday");
			} else {
				a("class", "valueBlockWednesday");
				a("name", "blockWednesday");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockWednesday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockWednesday')); }, function() { addError($('#", classApiMethodMethod, "_blockWednesday')); }); ");
			}
			;
			if(getBlockWednesday() != null && getBlockWednesday())
				a("checked", "checked");
		fg();

	}

	public void htmBlockWednesday(String classApiMethodMethod) {
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolBlockBlockWednesday").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockWednesday").a("class", "").f().sx("wednesday").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockWednesday(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// blockThursday //
	///////////////////

	/**	L'entité « blockThursday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockThursday;
	@JsonIgnore
	public Wrap<Boolean> blockThursdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockThursday").o(blockThursday);

	/**	<br/>L'entité « blockThursday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockThursday">Trouver l'entité blockThursday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockThursday(Wrap<Boolean> c);

	public Boolean getBlockThursday() {
		return blockThursday;
	}

	public void setBlockThursday(Boolean blockThursday) {
		this.blockThursday = blockThursday;
		this.blockThursdayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockThursday(String o) {
		this.blockThursday = Boolean.parseBoolean(o);
		this.blockThursdayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockThursdayInit() {
		if(!blockThursdayWrap.alreadyInitialized) {
			_blockThursday(blockThursdayWrap);
			if(blockThursday == null)
				setBlockThursday(blockThursdayWrap.o);
		}
		blockThursdayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockThursday() {
		return blockThursday;
	}

	public String strBlockThursday() {
		return blockThursday == null ? "" : blockThursday.toString();
	}

	public String jsonBlockThursday() {
		return blockThursday == null ? "" : blockThursday.toString();
	}

	public String nomAffichageBlockThursday() {
		return "thursday";
	}

	public String htmTooltipBlockThursday() {
		return null;
	}

	public String htmBlockThursday() {
		return blockThursday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockThursday());
	}

	public void inputBlockThursday(String classApiMethodMethod) {
		e("input")
			.a("type", "checkbox")
			.a("id", classApiMethodMethod, "_blockThursday")
			.a("value", "true");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setBlockThursday");
				a("name", "setBlockThursday");
			} else {
				a("class", "valueBlockThursday");
				a("name", "blockThursday");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockThursday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockThursday')); }, function() { addError($('#", classApiMethodMethod, "_blockThursday')); }); ");
			}
			;
			if(getBlockThursday() != null && getBlockThursday())
				a("checked", "checked");
		fg();

	}

	public void htmBlockThursday(String classApiMethodMethod) {
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolBlockBlockThursday").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockThursday").a("class", "").f().sx("thursday").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockThursday(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// blockFriday //
	/////////////////

	/**	L'entité « blockFriday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockFriday;
	@JsonIgnore
	public Wrap<Boolean> blockFridayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockFriday").o(blockFriday);

	/**	<br/>L'entité « blockFriday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockFriday">Trouver l'entité blockFriday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockFriday(Wrap<Boolean> c);

	public Boolean getBlockFriday() {
		return blockFriday;
	}

	public void setBlockFriday(Boolean blockFriday) {
		this.blockFriday = blockFriday;
		this.blockFridayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockFriday(String o) {
		this.blockFriday = Boolean.parseBoolean(o);
		this.blockFridayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockFridayInit() {
		if(!blockFridayWrap.alreadyInitialized) {
			_blockFriday(blockFridayWrap);
			if(blockFriday == null)
				setBlockFriday(blockFridayWrap.o);
		}
		blockFridayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockFriday() {
		return blockFriday;
	}

	public String strBlockFriday() {
		return blockFriday == null ? "" : blockFriday.toString();
	}

	public String jsonBlockFriday() {
		return blockFriday == null ? "" : blockFriday.toString();
	}

	public String nomAffichageBlockFriday() {
		return "friday";
	}

	public String htmTooltipBlockFriday() {
		return null;
	}

	public String htmBlockFriday() {
		return blockFriday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockFriday());
	}

	public void inputBlockFriday(String classApiMethodMethod) {
		e("input")
			.a("type", "checkbox")
			.a("id", classApiMethodMethod, "_blockFriday")
			.a("value", "true");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setBlockFriday");
				a("name", "setBlockFriday");
			} else {
				a("class", "valueBlockFriday");
				a("name", "blockFriday");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolBlockVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setBlockFriday', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_blockFriday')); }, function() { addError($('#", classApiMethodMethod, "_blockFriday')); }); ");
			}
			;
			if(getBlockFriday() != null && getBlockFriday())
				a("checked", "checked");
		fg();

	}

	public void htmBlockFriday(String classApiMethodMethod) {
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolBlockBlockFriday").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("for", classApiMethodMethod, "_blockFriday").a("class", "").f().sx("friday").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputBlockFriday(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// blockSaturday //
	///////////////////

	/**	L'entité « blockSaturday »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blockSaturday;
	@JsonIgnore
	public Wrap<Boolean> blockSaturdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockSaturday").o(blockSaturday);

	/**	<br/>L'entité « blockSaturday »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSaturday">Trouver l'entité blockSaturday dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockSaturday(Wrap<Boolean> c);

	public Boolean getBlockSaturday() {
		return blockSaturday;
	}

	public void setBlockSaturday(Boolean blockSaturday) {
		this.blockSaturday = blockSaturday;
		this.blockSaturdayWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockSaturday(String o) {
		this.blockSaturday = Boolean.parseBoolean(o);
		this.blockSaturdayWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockSaturdayInit() {
		if(!blockSaturdayWrap.alreadyInitialized) {
			_blockSaturday(blockSaturdayWrap);
			if(blockSaturday == null)
				setBlockSaturday(blockSaturdayWrap.o);
		}
		blockSaturdayWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Boolean solrBlockSaturday() {
		return blockSaturday;
	}

	public String strBlockSaturday() {
		return blockSaturday == null ? "" : blockSaturday.toString();
	}

	public String jsonBlockSaturday() {
		return blockSaturday == null ? "" : blockSaturday.toString();
	}

	public String nomAffichageBlockSaturday() {
		return "saturday";
	}

	public String htmTooltipBlockSaturday() {
		return null;
	}

	public String htmBlockSaturday() {
		return blockSaturday == null ? "" : StringEscapeUtils.escapeHtml4(strBlockSaturday());
	}

	/////////////////////
	// blockTotalPrice //
	/////////////////////

	/**	L'entité « blockTotalPrice »
	 *	 is defined as null before being initialized. 
	 */
	protected BigDecimal blockTotalPrice;
	@JsonIgnore
	public Wrap<BigDecimal> blockTotalPriceWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("blockTotalPrice").o(blockTotalPrice);

	/**	<br/>L'entité « blockTotalPrice »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTotalPrice">Trouver l'entité blockTotalPrice dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockTotalPrice(Wrap<BigDecimal> c);

	public BigDecimal getBlockTotalPrice() {
		return blockTotalPrice;
	}

	public void setBlockTotalPrice(BigDecimal blockTotalPrice) {
		this.blockTotalPrice = blockTotalPrice;
		this.blockTotalPriceWrap.alreadyInitialized = true;
	}
	public SchoolBlock setBlockTotalPrice(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setBlockTotalPrice(Double o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	public SchoolBlock setBlockTotalPrice(Integer o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolBlock)this;
	}
	protected SchoolBlock blockTotalPriceInit() {
		if(!blockTotalPriceWrap.alreadyInitialized) {
			_blockTotalPrice(blockTotalPriceWrap);
			if(blockTotalPrice == null)
				setBlockTotalPrice(blockTotalPriceWrap.o);
		}
		blockTotalPriceWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public Double solrBlockTotalPrice() {
		return blockTotalPrice == null ? null : blockTotalPrice.doubleValue();
	}

	public String strBlockTotalPrice() {
		return blockTotalPrice == null ? "" : blockTotalPrice.toString();
	}

	public String jsonBlockTotalPrice() {
		return blockTotalPrice == null ? "" : blockTotalPrice.toString();
	}

	public String nomAffichageBlockTotalPrice() {
		return "total price";
	}

	public String htmTooltipBlockTotalPrice() {
		return null;
	}

	public String htmBlockTotalPrice() {
		return blockTotalPrice == null ? "" : StringEscapeUtils.escapeHtml4(strBlockTotalPrice());
	}

	///////////////////////
	// blockCompleteName //
	///////////////////////

	/**	L'entité « blockCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String blockCompleteName;
	@JsonIgnore
	public Wrap<String> blockCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("blockCompleteName").o(blockCompleteName);

	/**	<br/>L'entité « blockCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.block.SchoolBlock&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockCompleteName">Trouver l'entité blockCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockCompleteName(Wrap<String> c);

	public String getBlockCompleteName() {
		return blockCompleteName;
	}

	public void setBlockCompleteName(String blockCompleteName) {
		this.blockCompleteName = blockCompleteName;
		this.blockCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolBlock blockCompleteNameInit() {
		if(!blockCompleteNameWrap.alreadyInitialized) {
			_blockCompleteName(blockCompleteNameWrap);
			if(blockCompleteName == null)
				setBlockCompleteName(blockCompleteNameWrap.o);
		}
		blockCompleteNameWrap.alreadyInitialized(true);
		return (SchoolBlock)this;
	}

	public String solrBlockCompleteName() {
		return blockCompleteName;
	}

	public String strBlockCompleteName() {
		return blockCompleteName == null ? "" : blockCompleteName;
	}

	public String jsonBlockCompleteName() {
		return blockCompleteName == null ? "" : blockCompleteName;
	}

	public String nomAffichageBlockCompleteName() {
		return "name";
	}

	public String htmTooltipBlockCompleteName() {
		return null;
	}

	public String htmBlockCompleteName() {
		return blockCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strBlockCompleteName());
	}

	public void inputBlockCompleteName(String classApiMethodMethod) {
	}

	public void htmBlockCompleteName(String classApiMethodMethod) {
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			if("Page".equals(classApiMethodMethod)) {
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-indigo ").f();
							e("label").a("class", "").f().sx("name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(strBlockCompleteName()).g("span");
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

	protected boolean alreadyInitializedSchoolBlock = false;

	public SchoolBlock initDeepSchoolBlock(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolBlock) {
			alreadyInitializedSchoolBlock = true;
			initDeepSchoolBlock();
		}
		return (SchoolBlock)this;
	}

	public void initDeepSchoolBlock() {
		initSchoolBlock();
		super.initDeepCluster(siteRequest_);
	}

	public void initSchoolBlock() {
		blockKeyInit();
		childKeyInit();
		enrollmentKeysInit();
		educationSortInit();
		schoolSortInit();
		yearSortInit();
		seasonSortInit();
		sessionSortInit();
		ageSortInit();
		ageKeyInit();
		ageSearchInit();
		age_Init();
		schoolKeyInit();
		yearKeyInit();
		seasonKeyInit();
		sessionKeyInit();
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
		seasonCompleteNameInit();
		sessionStartDayInit();
		sessionEndDayInit();
		ageCompleteNameInit();
		ageStartInit();
		ageEndInit();
		blockStartTimeInit();
		blockEndTimeInit();
		blockPricePerMonthInit();
		blockSundayInit();
		blockMondayInit();
		blockTuesdayInit();
		blockWednesdayInit();
		blockThursdayInit();
		blockFridayInit();
		blockSaturdayInit();
		blockTotalPriceInit();
		blockCompleteNameInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolBlock(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolBlock(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(ageSearch != null)
			ageSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolBlock(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolBlock(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolBlock(String var) {
		SchoolBlock oSchoolBlock = (SchoolBlock)this;
		switch(var) {
			case "blockKey":
				return oSchoolBlock.blockKey;
			case "childKey":
				return oSchoolBlock.childKey;
			case "enrollmentKeys":
				return oSchoolBlock.enrollmentKeys;
			case "educationSort":
				return oSchoolBlock.educationSort;
			case "schoolSort":
				return oSchoolBlock.schoolSort;
			case "yearSort":
				return oSchoolBlock.yearSort;
			case "seasonSort":
				return oSchoolBlock.seasonSort;
			case "sessionSort":
				return oSchoolBlock.sessionSort;
			case "ageSort":
				return oSchoolBlock.ageSort;
			case "ageKey":
				return oSchoolBlock.ageKey;
			case "ageSearch":
				return oSchoolBlock.ageSearch;
			case "age_":
				return oSchoolBlock.age_;
			case "schoolKey":
				return oSchoolBlock.schoolKey;
			case "yearKey":
				return oSchoolBlock.yearKey;
			case "seasonKey":
				return oSchoolBlock.seasonKey;
			case "sessionKey":
				return oSchoolBlock.sessionKey;
			case "schoolName":
				return oSchoolBlock.schoolName;
			case "schoolCompleteName":
				return oSchoolBlock.schoolCompleteName;
			case "schoolLocation":
				return oSchoolBlock.schoolLocation;
			case "schoolAddress":
				return oSchoolBlock.schoolAddress;
			case "schoolPhoneNumber":
				return oSchoolBlock.schoolPhoneNumber;
			case "schoolAdministratorName":
				return oSchoolBlock.schoolAdministratorName;
			case "yearStart":
				return oSchoolBlock.yearStart;
			case "yearEnd":
				return oSchoolBlock.yearEnd;
			case "seasonStartDate":
				return oSchoolBlock.seasonStartDate;
			case "seasonSummer":
				return oSchoolBlock.seasonSummer;
			case "seasonWinter":
				return oSchoolBlock.seasonWinter;
			case "seasonEnrollmentFee":
				return oSchoolBlock.seasonEnrollmentFee;
			case "seasonCompleteName":
				return oSchoolBlock.seasonCompleteName;
			case "sessionStartDay":
				return oSchoolBlock.sessionStartDay;
			case "sessionEndDay":
				return oSchoolBlock.sessionEndDay;
			case "ageCompleteName":
				return oSchoolBlock.ageCompleteName;
			case "ageStart":
				return oSchoolBlock.ageStart;
			case "ageEnd":
				return oSchoolBlock.ageEnd;
			case "blockStartTime":
				return oSchoolBlock.blockStartTime;
			case "blockEndTime":
				return oSchoolBlock.blockEndTime;
			case "blockPricePerMonth":
				return oSchoolBlock.blockPricePerMonth;
			case "blockSunday":
				return oSchoolBlock.blockSunday;
			case "blockMonday":
				return oSchoolBlock.blockMonday;
			case "blockTuesday":
				return oSchoolBlock.blockTuesday;
			case "blockWednesday":
				return oSchoolBlock.blockWednesday;
			case "blockThursday":
				return oSchoolBlock.blockThursday;
			case "blockFriday":
				return oSchoolBlock.blockFriday;
			case "blockSaturday":
				return oSchoolBlock.blockSaturday;
			case "blockTotalPrice":
				return oSchoolBlock.blockTotalPrice;
			case "blockCompleteName":
				return oSchoolBlock.blockCompleteName;
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
				o = attributeSchoolBlock(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolBlock(String var, Object val) {
		SchoolBlock oSchoolBlock = (SchoolBlock)this;
		switch(var) {
			case "enrollmentKeys":
				oSchoolBlock.addEnrollmentKeys((Long)val);
				return val;
			case "ageKey":
				oSchoolBlock.setAgeKey((Long)val);
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
					o = defineSchoolBlock(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolBlock(String var, String val) {
		switch(var) {
			case "schoolAddress":
				setSchoolAddress(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockStartTime":
				setBlockStartTime(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockEndTime":
				setBlockEndTime(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockPricePerMonth":
				setBlockPricePerMonth(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockSunday":
				setBlockSunday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockMonday":
				setBlockMonday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockTuesday":
				setBlockTuesday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockWednesday":
				setBlockWednesday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockThursday":
				setBlockThursday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockFriday":
				setBlockFriday(val);
				savesSchoolBlock.add(var);
				return val;
			case "blockSaturday":
				setBlockSaturday(val);
				savesSchoolBlock.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesSchoolBlock = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolBlock(solrDocument);
	}
	public void populateSchoolBlock(SolrDocument solrDocument) {
		SchoolBlock oSchoolBlock = (SchoolBlock)this;
		savesSchoolBlock = (List<String>)solrDocument.get("savesSchoolBlock_stored_strings");
		if(savesSchoolBlock != null) {

			if(savesSchoolBlock.contains("blockKey")) {
				Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
				if(blockKey != null)
					oSchoolBlock.setBlockKey(blockKey);
			}

			if(savesSchoolBlock.contains("childKey")) {
				Long childKey = (Long)solrDocument.get("childKey_stored_long");
				if(childKey != null)
					oSchoolBlock.setChildKey(childKey);
			}

			List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
			if(enrollmentKeys != null)
				oSchoolBlock.enrollmentKeys.addAll(enrollmentKeys);

			if(savesSchoolBlock.contains("educationSort")) {
				Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
				if(educationSort != null)
					oSchoolBlock.setEducationSort(educationSort);
			}

			if(savesSchoolBlock.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolBlock.setSchoolSort(schoolSort);
			}

			if(savesSchoolBlock.contains("yearSort")) {
				Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
				if(yearSort != null)
					oSchoolBlock.setYearSort(yearSort);
			}

			if(savesSchoolBlock.contains("seasonSort")) {
				Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
				if(seasonSort != null)
					oSchoolBlock.setSeasonSort(seasonSort);
			}

			if(savesSchoolBlock.contains("sessionSort")) {
				Integer sessionSort = (Integer)solrDocument.get("sessionSort_stored_int");
				if(sessionSort != null)
					oSchoolBlock.setSessionSort(sessionSort);
			}

			if(savesSchoolBlock.contains("ageSort")) {
				Integer ageSort = (Integer)solrDocument.get("ageSort_stored_int");
				if(ageSort != null)
					oSchoolBlock.setAgeSort(ageSort);
			}

			Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
			if(ageKey != null)
				oSchoolBlock.setAgeKey(ageKey);

			if(savesSchoolBlock.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oSchoolBlock.setSchoolKey(schoolKey);
			}

			if(savesSchoolBlock.contains("yearKey")) {
				Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
				if(yearKey != null)
					oSchoolBlock.setYearKey(yearKey);
			}

			if(savesSchoolBlock.contains("seasonKey")) {
				Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
				if(seasonKey != null)
					oSchoolBlock.setSeasonKey(seasonKey);
			}

			if(savesSchoolBlock.contains("sessionKey")) {
				Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
				if(sessionKey != null)
					oSchoolBlock.setSessionKey(sessionKey);
			}

			if(savesSchoolBlock.contains("schoolName")) {
				String schoolName = (String)solrDocument.get("schoolName_stored_string");
				if(schoolName != null)
					oSchoolBlock.setSchoolName(schoolName);
			}

			if(savesSchoolBlock.contains("schoolCompleteName")) {
				String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
				if(schoolCompleteName != null)
					oSchoolBlock.setSchoolCompleteName(schoolCompleteName);
			}

			if(savesSchoolBlock.contains("schoolLocation")) {
				String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
				if(schoolLocation != null)
					oSchoolBlock.setSchoolLocation(schoolLocation);
			}

			if(savesSchoolBlock.contains("schoolAddress")) {
				String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
				if(schoolAddress != null)
					oSchoolBlock.setSchoolAddress(schoolAddress);
			}

			if(savesSchoolBlock.contains("schoolPhoneNumber")) {
				String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
				if(schoolPhoneNumber != null)
					oSchoolBlock.setSchoolPhoneNumber(schoolPhoneNumber);
			}

			if(savesSchoolBlock.contains("schoolAdministratorName")) {
				String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
				if(schoolAdministratorName != null)
					oSchoolBlock.setSchoolAdministratorName(schoolAdministratorName);
			}

			if(savesSchoolBlock.contains("yearStart")) {
				Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
				if(yearStart != null)
					oSchoolBlock.setYearStart(yearStart);
			}

			if(savesSchoolBlock.contains("yearEnd")) {
				Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
				if(yearEnd != null)
					oSchoolBlock.setYearEnd(yearEnd);
			}

			if(savesSchoolBlock.contains("seasonStartDate")) {
				Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
				if(seasonStartDate != null)
					oSchoolBlock.setSeasonStartDate(seasonStartDate);
			}

			if(savesSchoolBlock.contains("seasonSummer")) {
				Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
				if(seasonSummer != null)
					oSchoolBlock.setSeasonSummer(seasonSummer);
			}

			if(savesSchoolBlock.contains("seasonWinter")) {
				Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
				if(seasonWinter != null)
					oSchoolBlock.setSeasonWinter(seasonWinter);
			}

			if(savesSchoolBlock.contains("seasonEnrollmentFee")) {
				Double seasonEnrollmentFee = (Double)solrDocument.get("seasonEnrollmentFee_stored_double");
				if(seasonEnrollmentFee != null)
					oSchoolBlock.setSeasonEnrollmentFee(seasonEnrollmentFee);
			}

			if(savesSchoolBlock.contains("seasonCompleteName")) {
				String seasonCompleteName = (String)solrDocument.get("seasonCompleteName_stored_string");
				if(seasonCompleteName != null)
					oSchoolBlock.setSeasonCompleteName(seasonCompleteName);
			}

			if(savesSchoolBlock.contains("sessionStartDay")) {
				Date sessionStartDay = (Date)solrDocument.get("sessionStartDay_stored_date");
				if(sessionStartDay != null)
					oSchoolBlock.setSessionStartDay(sessionStartDay);
			}

			if(savesSchoolBlock.contains("sessionEndDay")) {
				Date sessionEndDay = (Date)solrDocument.get("sessionEndDay_stored_date");
				if(sessionEndDay != null)
					oSchoolBlock.setSessionEndDay(sessionEndDay);
			}

			if(savesSchoolBlock.contains("ageCompleteName")) {
				String ageCompleteName = (String)solrDocument.get("ageCompleteName_stored_string");
				if(ageCompleteName != null)
					oSchoolBlock.setAgeCompleteName(ageCompleteName);
			}

			if(savesSchoolBlock.contains("ageStart")) {
				Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
				if(ageStart != null)
					oSchoolBlock.setAgeStart(ageStart);
			}

			if(savesSchoolBlock.contains("ageEnd")) {
				Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
				if(ageEnd != null)
					oSchoolBlock.setAgeEnd(ageEnd);
			}

			if(savesSchoolBlock.contains("blockStartTime")) {
				String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
				if(blockStartTime != null)
					oSchoolBlock.setBlockStartTime(blockStartTime);
			}

			if(savesSchoolBlock.contains("blockEndTime")) {
				String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
				if(blockEndTime != null)
					oSchoolBlock.setBlockEndTime(blockEndTime);
			}

			if(savesSchoolBlock.contains("blockPricePerMonth")) {
				Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
				if(blockPricePerMonth != null)
					oSchoolBlock.setBlockPricePerMonth(blockPricePerMonth);
			}

			if(savesSchoolBlock.contains("blockSunday")) {
				Boolean blockSunday = (Boolean)solrDocument.get("blockSunday_stored_boolean");
				if(blockSunday != null)
					oSchoolBlock.setBlockSunday(blockSunday);
			}

			if(savesSchoolBlock.contains("blockMonday")) {
				Boolean blockMonday = (Boolean)solrDocument.get("blockMonday_stored_boolean");
				if(blockMonday != null)
					oSchoolBlock.setBlockMonday(blockMonday);
			}

			if(savesSchoolBlock.contains("blockTuesday")) {
				Boolean blockTuesday = (Boolean)solrDocument.get("blockTuesday_stored_boolean");
				if(blockTuesday != null)
					oSchoolBlock.setBlockTuesday(blockTuesday);
			}

			if(savesSchoolBlock.contains("blockWednesday")) {
				Boolean blockWednesday = (Boolean)solrDocument.get("blockWednesday_stored_boolean");
				if(blockWednesday != null)
					oSchoolBlock.setBlockWednesday(blockWednesday);
			}

			if(savesSchoolBlock.contains("blockThursday")) {
				Boolean blockThursday = (Boolean)solrDocument.get("blockThursday_stored_boolean");
				if(blockThursday != null)
					oSchoolBlock.setBlockThursday(blockThursday);
			}

			if(savesSchoolBlock.contains("blockFriday")) {
				Boolean blockFriday = (Boolean)solrDocument.get("blockFriday_stored_boolean");
				if(blockFriday != null)
					oSchoolBlock.setBlockFriday(blockFriday);
			}

			if(savesSchoolBlock.contains("blockSaturday")) {
				Boolean blockSaturday = (Boolean)solrDocument.get("blockSaturday_stored_boolean");
				if(blockSaturday != null)
					oSchoolBlock.setBlockSaturday(blockSaturday);
			}

			if(savesSchoolBlock.contains("blockTotalPrice")) {
				Double blockTotalPrice = (Double)solrDocument.get("blockTotalPrice_stored_double");
				if(blockTotalPrice != null)
					oSchoolBlock.setBlockTotalPrice(blockTotalPrice);
			}

			if(savesSchoolBlock.contains("blockCompleteName")) {
				String blockCompleteName = (String)solrDocument.get("blockCompleteName_stored_string");
				if(blockCompleteName != null)
					oSchoolBlock.setBlockCompleteName(blockCompleteName);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.block.SchoolBlock"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolBlock o = new SchoolBlock();
			o.siteRequestSchoolBlock(siteRequest);
			o.initDeepSchoolBlock(siteRequest);
			o.indexSchoolBlock();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolBlock();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolBlock(document);
	}

	public void indexSchoolBlock(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolBlock(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolBlock() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolBlock(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolBlock(SolrInputDocument document) {
		if(savesSchoolBlock != null)
			document.addField("savesSchoolBlock_stored_strings", savesSchoolBlock);

		if(blockKey != null) {
			document.addField("blockKey_indexed_long", blockKey);
			document.addField("blockKey_stored_long", blockKey);
		}
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
		if(sessionSort != null) {
			document.addField("sessionSort_indexed_int", sessionSort);
			document.addField("sessionSort_stored_int", sessionSort);
		}
		if(ageSort != null) {
			document.addField("ageSort_indexed_int", ageSort);
			document.addField("ageSort_stored_int", ageSort);
		}
		if(ageKey != null) {
			document.addField("ageKey_indexed_long", ageKey);
			document.addField("ageKey_stored_long", ageKey);
		}
		if(schoolKey != null) {
			document.addField("schoolKey_indexed_long", schoolKey);
			document.addField("schoolKey_stored_long", schoolKey);
		}
		if(yearKey != null) {
			document.addField("yearKey_indexed_long", yearKey);
			document.addField("yearKey_stored_long", yearKey);
		}
		if(seasonKey != null) {
			document.addField("seasonKey_indexed_long", seasonKey);
			document.addField("seasonKey_stored_long", seasonKey);
		}
		if(sessionKey != null) {
			document.addField("sessionKey_indexed_long", sessionKey);
			document.addField("sessionKey_stored_long", sessionKey);
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
		if(seasonCompleteName != null) {
			document.addField("seasonCompleteName_indexed_string", seasonCompleteName);
			document.addField("seasonCompleteName_stored_string", seasonCompleteName);
		}
		if(sessionStartDay != null) {
			document.addField("sessionStartDay_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionStartDay_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionEndDay != null) {
			document.addField("sessionEndDay_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionEndDay_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(ageCompleteName != null) {
			document.addField("ageCompleteName_indexed_string", ageCompleteName);
			document.addField("ageCompleteName_stored_string", ageCompleteName);
		}
		if(ageStart != null) {
			document.addField("ageStart_indexed_int", ageStart);
			document.addField("ageStart_stored_int", ageStart);
		}
		if(ageEnd != null) {
			document.addField("ageEnd_indexed_int", ageEnd);
			document.addField("ageEnd_stored_int", ageEnd);
		}
		if(blockStartTime != null) {
			document.addField("blockStartTime_indexed_string", DateTimeFormatter.ofPattern("HH mm").format(blockStartTime.atOffset(ZoneOffset.UTC)));
			document.addField("blockStartTime_stored_string", DateTimeFormatter.ofPattern("HH mm").format(blockStartTime.atOffset(ZoneOffset.UTC)));
		}
		if(blockEndTime != null) {
			document.addField("blockEndTime_indexed_string", DateTimeFormatter.ofPattern("HH mm").format(blockEndTime.atOffset(ZoneOffset.UTC)));
			document.addField("blockEndTime_stored_string", DateTimeFormatter.ofPattern("HH mm").format(blockEndTime.atOffset(ZoneOffset.UTC)));
		}
		if(blockPricePerMonth != null) {
			document.addField("blockPricePerMonth_indexed_double", blockPricePerMonth.doubleValue());
			document.addField("blockPricePerMonth_stored_double", blockPricePerMonth.doubleValue());
		}
		if(blockSunday != null) {
			document.addField("blockSunday_indexed_boolean", blockSunday);
			document.addField("blockSunday_stored_boolean", blockSunday);
		}
		if(blockMonday != null) {
			document.addField("blockMonday_indexed_boolean", blockMonday);
			document.addField("blockMonday_stored_boolean", blockMonday);
		}
		if(blockTuesday != null) {
			document.addField("blockTuesday_indexed_boolean", blockTuesday);
			document.addField("blockTuesday_stored_boolean", blockTuesday);
		}
		if(blockWednesday != null) {
			document.addField("blockWednesday_indexed_boolean", blockWednesday);
			document.addField("blockWednesday_stored_boolean", blockWednesday);
		}
		if(blockThursday != null) {
			document.addField("blockThursday_indexed_boolean", blockThursday);
			document.addField("blockThursday_stored_boolean", blockThursday);
		}
		if(blockFriday != null) {
			document.addField("blockFriday_indexed_boolean", blockFriday);
			document.addField("blockFriday_stored_boolean", blockFriday);
		}
		if(blockSaturday != null) {
			document.addField("blockSaturday_indexed_boolean", blockSaturday);
			document.addField("blockSaturday_stored_boolean", blockSaturday);
		}
		if(blockTotalPrice != null) {
			document.addField("blockTotalPrice_indexed_double", blockTotalPrice.doubleValue());
			document.addField("blockTotalPrice_stored_double", blockTotalPrice.doubleValue());
		}
		if(blockCompleteName != null) {
			document.addField("blockCompleteName_indexed_string", blockCompleteName);
			document.addField("blockCompleteName_stored_string", blockCompleteName);
		}
		super.indexCluster(document);

	}

	public void unindexSchoolBlock() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolBlock(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeSchoolBlock(solrDocument);
	}
	public void storeSchoolBlock(SolrDocument solrDocument) {
		SchoolBlock oSchoolBlock = (SchoolBlock)this;

		Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
		if(blockKey != null)
			oSchoolBlock.setBlockKey(blockKey);

		Long childKey = (Long)solrDocument.get("childKey_stored_long");
		if(childKey != null)
			oSchoolBlock.setChildKey(childKey);

		List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
		if(enrollmentKeys != null)
			oSchoolBlock.enrollmentKeys.addAll(enrollmentKeys);

		Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
		if(educationSort != null)
			oSchoolBlock.setEducationSort(educationSort);

		Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
		if(schoolSort != null)
			oSchoolBlock.setSchoolSort(schoolSort);

		Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
		if(yearSort != null)
			oSchoolBlock.setYearSort(yearSort);

		Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
		if(seasonSort != null)
			oSchoolBlock.setSeasonSort(seasonSort);

		Integer sessionSort = (Integer)solrDocument.get("sessionSort_stored_int");
		if(sessionSort != null)
			oSchoolBlock.setSessionSort(sessionSort);

		Integer ageSort = (Integer)solrDocument.get("ageSort_stored_int");
		if(ageSort != null)
			oSchoolBlock.setAgeSort(ageSort);

		Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
		if(ageKey != null)
			oSchoolBlock.setAgeKey(ageKey);

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oSchoolBlock.setSchoolKey(schoolKey);

		Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
		if(yearKey != null)
			oSchoolBlock.setYearKey(yearKey);

		Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
		if(seasonKey != null)
			oSchoolBlock.setSeasonKey(seasonKey);

		Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
		if(sessionKey != null)
			oSchoolBlock.setSessionKey(sessionKey);

		String schoolName = (String)solrDocument.get("schoolName_stored_string");
		if(schoolName != null)
			oSchoolBlock.setSchoolName(schoolName);

		String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
		if(schoolCompleteName != null)
			oSchoolBlock.setSchoolCompleteName(schoolCompleteName);

		String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
		if(schoolLocation != null)
			oSchoolBlock.setSchoolLocation(schoolLocation);

		String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
		if(schoolAddress != null)
			oSchoolBlock.setSchoolAddress(schoolAddress);

		String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
		if(schoolPhoneNumber != null)
			oSchoolBlock.setSchoolPhoneNumber(schoolPhoneNumber);

		String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
		if(schoolAdministratorName != null)
			oSchoolBlock.setSchoolAdministratorName(schoolAdministratorName);

		Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
		if(yearStart != null)
			oSchoolBlock.setYearStart(yearStart);

		Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
		if(yearEnd != null)
			oSchoolBlock.setYearEnd(yearEnd);

		Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
		if(seasonStartDate != null)
			oSchoolBlock.setSeasonStartDate(seasonStartDate);

		Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
		if(seasonSummer != null)
			oSchoolBlock.setSeasonSummer(seasonSummer);

		Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
		if(seasonWinter != null)
			oSchoolBlock.setSeasonWinter(seasonWinter);

		Double seasonEnrollmentFee = (Double)solrDocument.get("seasonEnrollmentFee_stored_double");
		if(seasonEnrollmentFee != null)
			oSchoolBlock.setSeasonEnrollmentFee(seasonEnrollmentFee);

		String seasonCompleteName = (String)solrDocument.get("seasonCompleteName_stored_string");
		if(seasonCompleteName != null)
			oSchoolBlock.setSeasonCompleteName(seasonCompleteName);

		Date sessionStartDay = (Date)solrDocument.get("sessionStartDay_stored_date");
		if(sessionStartDay != null)
			oSchoolBlock.setSessionStartDay(sessionStartDay);

		Date sessionEndDay = (Date)solrDocument.get("sessionEndDay_stored_date");
		if(sessionEndDay != null)
			oSchoolBlock.setSessionEndDay(sessionEndDay);

		String ageCompleteName = (String)solrDocument.get("ageCompleteName_stored_string");
		if(ageCompleteName != null)
			oSchoolBlock.setAgeCompleteName(ageCompleteName);

		Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
		if(ageStart != null)
			oSchoolBlock.setAgeStart(ageStart);

		Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
		if(ageEnd != null)
			oSchoolBlock.setAgeEnd(ageEnd);

		String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
		if(blockStartTime != null)
			oSchoolBlock.setBlockStartTime(blockStartTime);

		String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
		if(blockEndTime != null)
			oSchoolBlock.setBlockEndTime(blockEndTime);

		Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
		if(blockPricePerMonth != null)
			oSchoolBlock.setBlockPricePerMonth(blockPricePerMonth);

		Boolean blockSunday = (Boolean)solrDocument.get("blockSunday_stored_boolean");
		if(blockSunday != null)
			oSchoolBlock.setBlockSunday(blockSunday);

		Boolean blockMonday = (Boolean)solrDocument.get("blockMonday_stored_boolean");
		if(blockMonday != null)
			oSchoolBlock.setBlockMonday(blockMonday);

		Boolean blockTuesday = (Boolean)solrDocument.get("blockTuesday_stored_boolean");
		if(blockTuesday != null)
			oSchoolBlock.setBlockTuesday(blockTuesday);

		Boolean blockWednesday = (Boolean)solrDocument.get("blockWednesday_stored_boolean");
		if(blockWednesday != null)
			oSchoolBlock.setBlockWednesday(blockWednesday);

		Boolean blockThursday = (Boolean)solrDocument.get("blockThursday_stored_boolean");
		if(blockThursday != null)
			oSchoolBlock.setBlockThursday(blockThursday);

		Boolean blockFriday = (Boolean)solrDocument.get("blockFriday_stored_boolean");
		if(blockFriday != null)
			oSchoolBlock.setBlockFriday(blockFriday);

		Boolean blockSaturday = (Boolean)solrDocument.get("blockSaturday_stored_boolean");
		if(blockSaturday != null)
			oSchoolBlock.setBlockSaturday(blockSaturday);

		Double blockTotalPrice = (Double)solrDocument.get("blockTotalPrice_stored_double");
		if(blockTotalPrice != null)
			oSchoolBlock.setBlockTotalPrice(blockTotalPrice);

		String blockCompleteName = (String)solrDocument.get("blockCompleteName_stored_string");
		if(blockCompleteName != null)
			oSchoolBlock.setBlockCompleteName(blockCompleteName);

		super.storeCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), enrollmentKeys, ageKey, schoolAddress, blockStartTime, blockEndTime, blockPricePerMonth, blockSunday, blockMonday, blockTuesday, blockWednesday, blockThursday, blockFriday, blockSaturday);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolBlock))
			return false;
		SchoolBlock that = (SchoolBlock)o;
		return super.equals(o)
				&& Objects.equals( enrollmentKeys, that.enrollmentKeys )
				&& Objects.equals( ageKey, that.ageKey )
				&& Objects.equals( schoolAddress, that.schoolAddress )
				&& Objects.equals( blockStartTime, that.blockStartTime )
				&& Objects.equals( blockEndTime, that.blockEndTime )
				&& Objects.equals( blockPricePerMonth, that.blockPricePerMonth )
				&& Objects.equals( blockSunday, that.blockSunday )
				&& Objects.equals( blockMonday, that.blockMonday )
				&& Objects.equals( blockTuesday, that.blockTuesday )
				&& Objects.equals( blockWednesday, that.blockWednesday )
				&& Objects.equals( blockThursday, that.blockThursday )
				&& Objects.equals( blockFriday, that.blockFriday )
				&& Objects.equals( blockSaturday, that.blockSaturday );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolBlock { ");
		sb.append( "enrollmentKeys: " ).append(enrollmentKeys);
		sb.append( ", ageKey: " ).append(ageKey);
		sb.append( ", schoolAddress: \"" ).append(schoolAddress).append( "\"" );
		sb.append( ", blockStartTime: " ).append(blockStartTime);
		sb.append( ", blockEndTime: " ).append(blockEndTime);
		sb.append( ", blockPricePerMonth: " ).append(blockPricePerMonth);
		sb.append( ", blockSunday: " ).append(blockSunday);
		sb.append( ", blockMonday: " ).append(blockMonday);
		sb.append( ", blockTuesday: " ).append(blockTuesday);
		sb.append( ", blockWednesday: " ).append(blockWednesday);
		sb.append( ", blockThursday: " ).append(blockThursday);
		sb.append( ", blockFriday: " ).append(blockFriday);
		sb.append( ", blockSaturday: " ).append(blockSaturday);
		sb.append(" }");
		return sb.toString();
	}
}
