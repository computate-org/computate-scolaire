package org.computate.scolaire.enUS.enrollment;

import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.Long;
import java.util.Locale;
import java.time.LocalTime;
import org.computate.scolaire.enUS.season.SchoolSeason;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.mom.SchoolMom;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.List;
import java.time.LocalDate;
import org.apache.solr.client.solrj.SolrQuery;
import java.util.Optional;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.enUS.writer.AllWriter;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.wrap.Wrap;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import java.lang.String;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.SolrClient;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.time.temporal.ChronoUnit;
import org.computate.scolaire.enUS.dad.SchoolDad;
import java.time.format.DateTimeFormatter;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolEnrollmentGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolEnrollment.class);

	public static final String SchoolEnrollment_UnNom = "an enrollment";
	public static final String SchoolEnrollment_Ce = "this ";
	public static final String SchoolEnrollment_CeNom = "this enrollment";
	public static final String SchoolEnrollment_Un = "a ";
	public static final String SchoolEnrollment_LeNom = "theenrollment";
	public static final String SchoolEnrollment_NomSingulier = "enrollment";
	public static final String SchoolEnrollment_NomPluriel = "enrollments";
	public static final String SchoolEnrollment_NomActuel = "current enrollment";
	public static final String SchoolEnrollment_TousNom = "all the enrollments";
	public static final String SchoolEnrollment_RechercherTousNomPar = "search enrollments by ";
	public static final String SchoolEnrollment_LesNoms = "the enrollments";
	public static final String SchoolEnrollment_AucunNomTrouve = "no enrollment found";
	public static final String SchoolEnrollment_NomVar = "enrollment";
	public static final String SchoolEnrollment_DeNom = "of enrollment";
	public static final String SchoolEnrollment_UnNomAdjectif = "an enrollment";
	public static final String SchoolEnrollment_NomAdjectifSingulier = "enrollment";
	public static final String SchoolEnrollment_NomAdjectifPluriel = "enrollments";
	public static final String SchoolEnrollment_Couleur = "purple";
	public static final String SchoolEnrollment_IconeGroupe = "solid";
	public static final String SchoolEnrollment_IconeNom = "edit";

	///////////////////
	// enrollmentKey //
	///////////////////

	/**	L'entité « enrollmentKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long enrollmentKey;
	@JsonIgnore
	public Wrap<Long> enrollmentKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("enrollmentKey").o(enrollmentKey);

	/**	<br/>L'entité « enrollmentKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKey">Trouver l'entité enrollmentKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentKey(Wrap<Long> c);

	public Long getEnrollmentKey() {
		return enrollmentKey;
	}

	public void setEnrollmentKey(Long enrollmentKey) {
		this.enrollmentKey = enrollmentKey;
		this.enrollmentKeyWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentKey(String o) {
		if(NumberUtils.isParsable(o))
			this.enrollmentKey = Long.parseLong(o);
		this.enrollmentKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentKeyInit() {
		if(!enrollmentKeyWrap.alreadyInitialized) {
			_enrollmentKey(enrollmentKeyWrap);
			if(enrollmentKey == null)
				setEnrollmentKey(enrollmentKeyWrap.o);
		}
		enrollmentKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Long solrEnrollmentKey() {
		return enrollmentKey;
	}

	public String strEnrollmentKey() {
		return enrollmentKey == null ? "" : enrollmentKey.toString();
	}

	public String jsonEnrollmentKey() {
		return enrollmentKey == null ? "" : enrollmentKey.toString();
	}

	public String nomAffichageEnrollmentKey() {
		return "key";
	}

	public String htmTooltipEnrollmentKey() {
		return null;
	}

	public String htmEnrollmentKey() {
		return enrollmentKey == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentKey());
	}

	/////////////
	// yearKey //
	/////////////

	/**	L'entité « yearKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long yearKey;
	@JsonIgnore
	public Wrap<Long> yearKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("yearKey").o(yearKey);

	/**	<br/>L'entité « yearKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Trouver l'entité yearKey dans Solr</a>
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
	public SchoolEnrollment setYearKey(String o) {
		if(NumberUtils.isParsable(o))
			this.yearKey = Long.parseLong(o);
		this.yearKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment yearKeyInit() {
		if(!yearKeyWrap.alreadyInitialized) {
			_yearKey(yearKeyWrap);
			if(yearKey == null)
				setYearKey(yearKeyWrap.o);
		}
		yearKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSearch">Trouver l'entité yearSearch dans Solr</a>
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
	protected SchoolEnrollment yearSearchInit() {
		if(!yearSearchWrap.alreadyInitialized) {
			_yearSearch(yearSearch);
		}
		yearSearch.initDeepForClass(siteRequest_);
		yearSearchWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:year_">Trouver l'entité year_ dans Solr</a>
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
	protected SchoolEnrollment year_Init() {
		if(!year_Wrap.alreadyInitialized) {
			_year_(year_Wrap);
			if(year_ == null)
				setYear_(year_Wrap.o);
		}
		year_Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	///////////////
	// blockKeys //
	///////////////

	/**	L'entité « blockKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> blockKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> blockKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("blockKeys").o(blockKeys);

	/**	<br/>L'entité « blockKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKeys">Trouver l'entité blockKeys dans Solr</a>
	 * <br/>
	 * @param blockKeys est l'entité déjà construit. 
	 **/
	protected abstract void _blockKeys(List<Long> o);

	public List<Long> getBlockKeys() {
		return blockKeys;
	}

	public void setBlockKeys(List<Long> blockKeys) {
		this.blockKeys = blockKeys;
		this.blockKeysWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addBlockKeys(Long...objets) {
		for(Long o : objets) {
			addBlockKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addBlockKeys(Long o) {
		if(o != null && !blockKeys.contains(o))
			this.blockKeys.add(o);
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setBlockKeys(JsonArray objets) {
		blockKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlockKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addBlockKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addBlockKeys(p);
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockKeysInit() {
		if(!blockKeysWrap.alreadyInitialized) {
			_blockKeys(blockKeys);
		}
		blockKeysWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public List<Long> solrBlockKeys() {
		return blockKeys;
	}

	public String strBlockKeys() {
		return blockKeys == null ? "" : blockKeys.toString();
	}

	public String jsonBlockKeys() {
		return blockKeys == null ? "" : blockKeys.toString();
	}

	public String nomAffichageBlockKeys() {
		return "blocks";
	}

	public String htmTooltipBlockKeys() {
		return null;
	}

	public String htmBlockKeys() {
		return blockKeys == null ? "" : StringEscapeUtils.escapeHtml4(strBlockKeys());
	}

	public void inputBlockKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "blocks")
				.a("class", "valueObjectSuggest suggestBlockKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setBlockKeys")
				.a("id", classApiMethodMethod, "_blockKeys")
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolEnrollmentBlockKeys($(this).val() ? searchSchoolBlockFilters($('#suggestSchoolEnrollmentBlockKeys')) : [{'name':'fq','value':'enrollmentKeys:", pk, "'}], $('#listSchoolEnrollmentBlockKeys_", classApiMethodMethod, "'), ", pk, "); ")
			.fg();

	}

	public void htmBlockKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentBlockKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/block?fq=enrollmentKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-indigo w3-hover-indigo ").f();
								e("i").a("class", "far fa-bell ").f().g("i");
								sx("blocks");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate blocks to this enrollment");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputBlockKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolEnrollmentBlockKeys_", classApiMethodMethod).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
										.a("onclick", "postSchoolBlockVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() { patchSchoolEnrollmentVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { addError($('#", classApiMethodMethod, "blockKeys')); });")
										.f().sx("add a block")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// blockSearch //
	/////////////////

	/**	L'entité « blockSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolBlock>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolBlock> blockSearch = new SearchList<SchoolBlock>();
	@JsonIgnore
	public Wrap<SearchList<SchoolBlock>> blockSearchWrap = new Wrap<SearchList<SchoolBlock>>().p(this).c(SearchList.class).var("blockSearch").o(blockSearch);

	/**	<br/>L'entité « blockSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolBlock>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSearch">Trouver l'entité blockSearch dans Solr</a>
	 * <br/>
	 * @param blockSearch est l'entité déjà construit. 
	 **/
	protected abstract void _blockSearch(SearchList<SchoolBlock> l);

	public SearchList<SchoolBlock> getBlockSearch() {
		return blockSearch;
	}

	public void setBlockSearch(SearchList<SchoolBlock> blockSearch) {
		this.blockSearch = blockSearch;
		this.blockSearchWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment blockSearchInit() {
		if(!blockSearchWrap.alreadyInitialized) {
			_blockSearch(blockSearch);
		}
		blockSearch.initDeepForClass(siteRequest_);
		blockSearchWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	/////////////
	// blocks_ //
	/////////////

	/**	L'entité « blocks_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected List<SchoolBlock> blocks_;
	@JsonIgnore
	public Wrap<List<SchoolBlock>> blocks_Wrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("blocks_").o(blocks_);

	/**	<br/>L'entité « blocks_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blocks_">Trouver l'entité blocks_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocks_(Wrap<List<SchoolBlock>> c);

	public List<SchoolBlock> getBlocks_() {
		return blocks_;
	}

	public void setBlocks_(List<SchoolBlock> blocks_) {
		this.blocks_ = blocks_;
		this.blocks_Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addBlocks_(SchoolBlock...objets) {
		for(SchoolBlock o : objets) {
			addBlocks_(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addBlocks_(SchoolBlock o) {
		if(o != null && !blocks_.contains(o))
			this.blocks_.add(o);
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blocks_Init() {
		if(!blocks_Wrap.alreadyInitialized) {
			_blocks_(blocks_Wrap);
			if(blocks_ == null)
				setBlocks_(blocks_Wrap.o);
		}
		blocks_Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	//////////////
	// seasons_ //
	//////////////

	/**	L'entité « seasons_ »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolSeason>(). 
	 */
	@JsonIgnore
	protected List<SchoolSeason> seasons_ = new java.util.ArrayList<org.computate.scolaire.enUS.season.SchoolSeason>();
	@JsonIgnore
	public Wrap<List<SchoolSeason>> seasons_Wrap = new Wrap<List<SchoolSeason>>().p(this).c(List.class).var("seasons_").o(seasons_);

	/**	<br/>L'entité « seasons_ »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolSeason>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasons_">Trouver l'entité seasons_ dans Solr</a>
	 * <br/>
	 * @param seasons_ est l'entité déjà construit. 
	 **/
	protected abstract void _seasons_(List<SchoolSeason> c);

	public List<SchoolSeason> getSeasons_() {
		return seasons_;
	}

	public void setSeasons_(List<SchoolSeason> seasons_) {
		this.seasons_ = seasons_;
		this.seasons_Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addSeasons_(SchoolSeason...objets) {
		for(SchoolSeason o : objets) {
			addSeasons_(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addSeasons_(SchoolSeason o) {
		if(o != null && !seasons_.contains(o))
			this.seasons_.add(o);
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasons_Init() {
		if(!seasons_Wrap.alreadyInitialized) {
			_seasons_(seasons_);
		}
		seasons_Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	////////////
	// block_ //
	////////////

	/**	L'entité « block_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SchoolBlock block_;
	@JsonIgnore
	public Wrap<SchoolBlock> block_Wrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("block_").o(block_);

	/**	<br/>L'entité « block_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:block_">Trouver l'entité block_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _block_(Wrap<SchoolBlock> c);

	public SchoolBlock getBlock_() {
		return block_;
	}

	public void setBlock_(SchoolBlock block_) {
		this.block_ = block_;
		this.block_Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment block_Init() {
		if(!block_Wrap.alreadyInitialized) {
			_block_(block_Wrap);
			if(block_ == null)
				setBlock_(block_Wrap.o);
		}
		block_Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	///////////////
	// schoolKey //
	///////////////

	/**	L'entité « schoolKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long schoolKey;
	@JsonIgnore
	public Wrap<Long> schoolKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("schoolKey").o(schoolKey);

	/**	<br/>L'entité « schoolKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Trouver l'entité schoolKey dans Solr</a>
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
	public SchoolEnrollment setSchoolKey(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolKey = Long.parseLong(o);
		this.schoolKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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

	///////////////
	// seasonKey //
	///////////////

	/**	L'entité « seasonKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long seasonKey;
	@JsonIgnore
	public Wrap<Long> seasonKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("seasonKey").o(seasonKey);

	/**	<br/>L'entité « seasonKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKey">Trouver l'entité seasonKey dans Solr</a>
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
	public SchoolEnrollment setSeasonKey(String o) {
		if(NumberUtils.isParsable(o))
			this.seasonKey = Long.parseLong(o);
		this.seasonKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasonKeyInit() {
		if(!seasonKeyWrap.alreadyInitialized) {
			_seasonKey(seasonKeyWrap);
			if(seasonKey == null)
				setSeasonKey(seasonKeyWrap.o);
		}
		seasonKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long sessionKey;
	@JsonIgnore
	public Wrap<Long> sessionKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("sessionKey").o(sessionKey);

	/**	<br/>L'entité « sessionKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKey">Trouver l'entité sessionKey dans Solr</a>
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
	public SchoolEnrollment setSessionKey(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionKey = Long.parseLong(o);
		this.sessionKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment sessionKeyInit() {
		if(!sessionKeyWrap.alreadyInitialized) {
			_sessionKey(sessionKeyWrap);
			if(sessionKey == null)
				setSessionKey(sessionKeyWrap.o);
		}
		sessionKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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

	////////////
	// ageKey //
	////////////

	/**	L'entité « ageKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long ageKey;
	@JsonIgnore
	public Wrap<Long> ageKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("ageKey").o(ageKey);

	/**	<br/>L'entité « ageKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKey">Trouver l'entité ageKey dans Solr</a>
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
	public SchoolEnrollment setAgeKey(String o) {
		if(NumberUtils.isParsable(o))
			this.ageKey = Long.parseLong(o);
		this.ageKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment ageKeyInit() {
		if(!ageKeyWrap.alreadyInitialized) {
			_ageKey(ageKeyWrap);
			if(ageKey == null)
				setAgeKey(ageKeyWrap.o);
		}
		ageKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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

	//////////////
	// blockKey //
	//////////////

	/**	L'entité « blockKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long blockKey;
	@JsonIgnore
	public Wrap<Long> blockKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("blockKey").o(blockKey);

	/**	<br/>L'entité « blockKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKey">Trouver l'entité blockKey dans Solr</a>
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
	public SchoolEnrollment setBlockKey(String o) {
		if(NumberUtils.isParsable(o))
			this.blockKey = Long.parseLong(o);
		this.blockKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockKeyInit() {
		if(!blockKeyWrap.alreadyInitialized) {
			_blockKey(blockKeyWrap);
			if(blockKey == null)
				setBlockKey(blockKeyWrap.o);
		}
		blockKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long childKey;
	@JsonIgnore
	public Wrap<Long> childKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("childKey").o(childKey);

	/**	<br/>L'entité « childKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childKey">Trouver l'entité childKey dans Solr</a>
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
	public SchoolEnrollment setChildKey(String o) {
		if(NumberUtils.isParsable(o))
			this.childKey = Long.parseLong(o);
		this.childKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment childKeyInit() {
		if(!childKeyWrap.alreadyInitialized) {
			_childKey(childKeyWrap);
			if(childKey == null)
				setChildKey(childKeyWrap.o);
		}
		childKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
		return "children";
	}

	public String htmTooltipChildKey() {
		return null;
	}

	public String htmChildKey() {
		return childKey == null ? "" : StringEscapeUtils.escapeHtml4(strChildKey());
	}

	public void inputChildKey(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "children")
				.a("class", "valueObjectSuggest suggestChildKey w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setChildKey")
				.a("id", classApiMethodMethod, "_childKey")
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolEnrollmentChildKey($(this).val() ? searchSchoolChildFilters($('#suggestSchoolEnrollmentChildKey')) : [{'name':'fq','value':'enrollmentKeys:", pk, "'}], $('#listSchoolEnrollmentChildKey_", classApiMethodMethod, "'), ", pk, "); ")
			.fg();

	}

	public void htmChildKey(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentChildKey").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/child?fq=enrollmentKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "far fa-child ").f().g("i");
								sx("children");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate a child to this enrollment");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputChildKey(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolEnrollmentChildKey_", classApiMethodMethod).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
										.a("onclick", "postSchoolChildVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() { patchSchoolEnrollmentVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { addError($('#", classApiMethodMethod, "childKey')); });")
										.f().sx("add a child")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////
	// momKeys //
	/////////////

	/**	L'entité « momKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> momKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> momKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("momKeys").o(momKeys);

	/**	<br/>L'entité « momKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:momKeys">Trouver l'entité momKeys dans Solr</a>
	 * <br/>
	 * @param momKeys est l'entité déjà construit. 
	 **/
	protected abstract void _momKeys(List<Long> o);

	public List<Long> getMomKeys() {
		return momKeys;
	}

	public void setMomKeys(List<Long> momKeys) {
		this.momKeys = momKeys;
		this.momKeysWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addMomKeys(Long...objets) {
		for(Long o : objets) {
			addMomKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addMomKeys(Long o) {
		if(o != null && !momKeys.contains(o))
			this.momKeys.add(o);
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setMomKeys(JsonArray objets) {
		momKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addMomKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addMomKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addMomKeys(p);
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment momKeysInit() {
		if(!momKeysWrap.alreadyInitialized) {
			_momKeys(momKeys);
		}
		momKeysWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public List<Long> solrMomKeys() {
		return momKeys;
	}

	public String strMomKeys() {
		return momKeys == null ? "" : momKeys.toString();
	}

	public String jsonMomKeys() {
		return momKeys == null ? "" : momKeys.toString();
	}

	public String nomAffichageMomKeys() {
		return "moms";
	}

	public String htmTooltipMomKeys() {
		return null;
	}

	public String htmMomKeys() {
		return momKeys == null ? "" : StringEscapeUtils.escapeHtml4(strMomKeys());
	}

	public void inputMomKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "moms")
				.a("class", "valueObjectSuggest suggestMomKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setMomKeys")
				.a("id", classApiMethodMethod, "_momKeys")
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolEnrollmentMomKeys($(this).val() ? searchSchoolMomFilters($('#suggestSchoolEnrollmentMomKeys')) : [{'name':'fq','value':'enrollmentKeys:", pk, "'}], $('#listSchoolEnrollmentMomKeys_", classApiMethodMethod, "'), ", pk, "); ")
			.fg();

	}

	public void htmMomKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentMomKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/mom?fq=enrollmentKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-pink w3-hover-pink ").f();
								e("i").a("class", "far fa-female ").f().g("i");
								sx("moms");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate moms to this enrollment");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputMomKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolEnrollmentMomKeys_", classApiMethodMethod).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
										.a("onclick", "postSchoolMomVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() { patchSchoolEnrollmentVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { addError($('#", classApiMethodMethod, "momKeys')); });")
										.f().sx("add a mom")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////
	// dadKeys //
	/////////////

	/**	L'entité « dadKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> dadKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> dadKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("dadKeys").o(dadKeys);

	/**	<br/>L'entité « dadKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadKeys">Trouver l'entité dadKeys dans Solr</a>
	 * <br/>
	 * @param dadKeys est l'entité déjà construit. 
	 **/
	protected abstract void _dadKeys(List<Long> o);

	public List<Long> getDadKeys() {
		return dadKeys;
	}

	public void setDadKeys(List<Long> dadKeys) {
		this.dadKeys = dadKeys;
		this.dadKeysWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addDadKeys(Long...objets) {
		for(Long o : objets) {
			addDadKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addDadKeys(Long o) {
		if(o != null && !dadKeys.contains(o))
			this.dadKeys.add(o);
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setDadKeys(JsonArray objets) {
		dadKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addDadKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addDadKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addDadKeys(p);
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment dadKeysInit() {
		if(!dadKeysWrap.alreadyInitialized) {
			_dadKeys(dadKeys);
		}
		dadKeysWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public List<Long> solrDadKeys() {
		return dadKeys;
	}

	public String strDadKeys() {
		return dadKeys == null ? "" : dadKeys.toString();
	}

	public String jsonDadKeys() {
		return dadKeys == null ? "" : dadKeys.toString();
	}

	public String nomAffichageDadKeys() {
		return "dads";
	}

	public String htmTooltipDadKeys() {
		return null;
	}

	public String htmDadKeys() {
		return dadKeys == null ? "" : StringEscapeUtils.escapeHtml4(strDadKeys());
	}

	public void inputDadKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "dads")
				.a("class", "valueObjectSuggest suggestDadKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setDadKeys")
				.a("id", classApiMethodMethod, "_dadKeys")
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolEnrollmentDadKeys($(this).val() ? searchSchoolDadFilters($('#suggestSchoolEnrollmentDadKeys')) : [{'name':'fq','value':'enrollmentKeys:", pk, "'}], $('#listSchoolEnrollmentDadKeys_", classApiMethodMethod, "'), ", pk, "); ")
			.fg();

	}

	public void htmDadKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentDadKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/dad?fq=enrollmentKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-light-blue w3-hover-light-blue ").f();
								e("i").a("class", "far fa-male ").f().g("i");
								sx("dads");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate dads to this enrollment");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputDadKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolEnrollmentDadKeys_", classApiMethodMethod).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
										.a("onclick", "postSchoolDadVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() { patchSchoolEnrollmentVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { addError($('#", classApiMethodMethod, "dadKeys')); });")
										.f().sx("add a dad")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// guardianKeys //
	//////////////////

	/**	L'entité « guardianKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> guardianKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> guardianKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("guardianKeys").o(guardianKeys);

	/**	<br/>L'entité « guardianKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianKeys">Trouver l'entité guardianKeys dans Solr</a>
	 * <br/>
	 * @param guardianKeys est l'entité déjà construit. 
	 **/
	protected abstract void _guardianKeys(List<Long> o);

	public List<Long> getGuardianKeys() {
		return guardianKeys;
	}

	public void setGuardianKeys(List<Long> guardianKeys) {
		this.guardianKeys = guardianKeys;
		this.guardianKeysWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addGuardianKeys(Long...objets) {
		for(Long o : objets) {
			addGuardianKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addGuardianKeys(Long o) {
		if(o != null && !guardianKeys.contains(o))
			this.guardianKeys.add(o);
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setGuardianKeys(JsonArray objets) {
		guardianKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGuardianKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addGuardianKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addGuardianKeys(p);
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment guardianKeysInit() {
		if(!guardianKeysWrap.alreadyInitialized) {
			_guardianKeys(guardianKeys);
		}
		guardianKeysWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public List<Long> solrGuardianKeys() {
		return guardianKeys;
	}

	public String strGuardianKeys() {
		return guardianKeys == null ? "" : guardianKeys.toString();
	}

	public String jsonGuardianKeys() {
		return guardianKeys == null ? "" : guardianKeys.toString();
	}

	public String nomAffichageGuardianKeys() {
		return "guardians";
	}

	public String htmTooltipGuardianKeys() {
		return null;
	}

	public String htmGuardianKeys() {
		return guardianKeys == null ? "" : StringEscapeUtils.escapeHtml4(strGuardianKeys());
	}

	public void inputGuardianKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "guardians")
				.a("class", "valueObjectSuggest suggestGuardianKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setGuardianKeys")
				.a("id", classApiMethodMethod, "_guardianKeys")
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolEnrollmentGuardianKeys($(this).val() ? searchSchoolGuardianFilters($('#suggestSchoolEnrollmentGuardianKeys')) : [{'name':'fq','value':'enrollmentKeys:", pk, "'}], $('#listSchoolEnrollmentGuardianKeys_", classApiMethodMethod, "'), ", pk, "); ")
			.fg();

	}

	public void htmGuardianKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentGuardianKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/guardian?fq=enrollmentKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
								e("i").a("class", "far fa-phone ").f().g("i");
								sx("guardians");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate guardians to this enrollment");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputGuardianKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolEnrollmentGuardianKeys_", classApiMethodMethod).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postSchoolGuardianVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() { patchSchoolEnrollmentVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { addError($('#", classApiMethodMethod, "guardianKeys')); });")
										.f().sx("add a guardian")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// paymentKeys //
	/////////////////

	/**	L'entité « paymentKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> paymentKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> paymentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("paymentKeys").o(paymentKeys);

	/**	<br/>L'entité « paymentKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentKeys">Trouver l'entité paymentKeys dans Solr</a>
	 * <br/>
	 * @param paymentKeys est l'entité déjà construit. 
	 **/
	protected abstract void _paymentKeys(List<Long> o);

	public List<Long> getPaymentKeys() {
		return paymentKeys;
	}

	public void setPaymentKeys(List<Long> paymentKeys) {
		this.paymentKeys = paymentKeys;
		this.paymentKeysWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addPaymentKeys(Long...objets) {
		for(Long o : objets) {
			addPaymentKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addPaymentKeys(Long o) {
		if(o != null && !paymentKeys.contains(o))
			this.paymentKeys.add(o);
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setPaymentKeys(JsonArray objets) {
		paymentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPaymentKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addPaymentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPaymentKeys(p);
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment paymentKeysInit() {
		if(!paymentKeysWrap.alreadyInitialized) {
			_paymentKeys(paymentKeys);
		}
		paymentKeysWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public List<Long> solrPaymentKeys() {
		return paymentKeys;
	}

	public String strPaymentKeys() {
		return paymentKeys == null ? "" : paymentKeys.toString();
	}

	public String jsonPaymentKeys() {
		return paymentKeys == null ? "" : paymentKeys.toString();
	}

	public String nomAffichagePaymentKeys() {
		return "payments";
	}

	public String htmTooltipPaymentKeys() {
		return null;
	}

	public String htmPaymentKeys() {
		return paymentKeys == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentKeys());
	}

	public void inputPaymentKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "payments")
				.a("class", "valueObjectSuggest suggestPaymentKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setPaymentKeys")
				.a("id", classApiMethodMethod, "_paymentKeys")
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolEnrollmentPaymentKeys($(this).val() ? searchSchoolPaymentFilters($('#suggestSchoolEnrollmentPaymentKeys')) : [{'name':'fq','value':'enrollmentKeys:", pk, "'}], $('#listSchoolEnrollmentPaymentKeys_", classApiMethodMethod, "'), ", pk, "); ")
			.fg();

	}

	public void htmPaymentKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentPaymentKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/payment?fq=enrollmentKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "fas fa-search-dollar ").f().g("i");
								sx("payments");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate payments to this enrollment");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputPaymentKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolEnrollmentPaymentKeys_", classApiMethodMethod).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
										.a("onclick", "postSchoolPaymentVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() { patchSchoolEnrollmentVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { addError($('#", classApiMethodMethod, "paymentKeys')); });")
										.f().sx("add a payment")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////
	// enrollmentFormKey //
	///////////////////////

	/**	L'entité « enrollmentFormKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long enrollmentFormKey;
	@JsonIgnore
	public Wrap<Long> enrollmentFormKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("enrollmentFormKey").o(enrollmentFormKey);

	/**	<br/>L'entité « enrollmentFormKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentFormKey">Trouver l'entité enrollmentFormKey dans Solr</a>
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
	public SchoolEnrollment setEnrollmentFormKey(String o) {
		if(NumberUtils.isParsable(o))
			this.enrollmentFormKey = Long.parseLong(o);
		this.enrollmentFormKeyWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentFormKeyInit() {
		if(!enrollmentFormKeyWrap.alreadyInitialized) {
			_enrollmentFormKey(enrollmentFormKeyWrap);
			if(enrollmentFormKey == null)
				setEnrollmentFormKey(enrollmentFormKeyWrap.o);
		}
		enrollmentFormKeyWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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

	///////////////////
	// educationSort //
	///////////////////

	/**	L'entité « educationSort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Integer educationSort;
	@JsonIgnore
	public Wrap<Integer> educationSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("educationSort").o(educationSort);

	/**	<br/>L'entité « educationSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Trouver l'entité educationSort dans Solr</a>
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
	public SchoolEnrollment setEducationSort(String o) {
		if(NumberUtils.isParsable(o))
			this.educationSort = Integer.parseInt(o);
		this.educationSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment educationSortInit() {
		if(!educationSortWrap.alreadyInitialized) {
			_educationSort(educationSortWrap);
			if(educationSort == null)
				setEducationSort(educationSortWrap.o);
		}
		educationSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	protected Integer schoolSort;
	@JsonIgnore
	public Wrap<Integer> schoolSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("schoolSort").o(schoolSort);

	/**	<br/>L'entité « schoolSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Trouver l'entité schoolSort dans Solr</a>
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
	public SchoolEnrollment setSchoolSort(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolSort = Integer.parseInt(o);
		this.schoolSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment schoolSortInit() {
		if(!schoolSortWrap.alreadyInitialized) {
			_schoolSort(schoolSortWrap);
			if(schoolSort == null)
				setSchoolSort(schoolSortWrap.o);
		}
		schoolSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	protected Integer yearSort;
	@JsonIgnore
	public Wrap<Integer> yearSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearSort").o(yearSort);

	/**	<br/>L'entité « yearSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSort">Trouver l'entité yearSort dans Solr</a>
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
	public SchoolEnrollment setYearSort(String o) {
		if(NumberUtils.isParsable(o))
			this.yearSort = Integer.parseInt(o);
		this.yearSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment yearSortInit() {
		if(!yearSortWrap.alreadyInitialized) {
			_yearSort(yearSortWrap);
			if(yearSort == null)
				setYearSort(yearSortWrap.o);
		}
		yearSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected Integer seasonSort;
	@JsonIgnore
	public Wrap<Integer> seasonSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("seasonSort").o(seasonSort);

	/**	<br/>L'entité « seasonSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSort">Trouver l'entité seasonSort dans Solr</a>
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
	public SchoolEnrollment setSeasonSort(String o) {
		if(NumberUtils.isParsable(o))
			this.seasonSort = Integer.parseInt(o);
		this.seasonSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasonSortInit() {
		if(!seasonSortWrap.alreadyInitialized) {
			_seasonSort(seasonSortWrap);
			if(seasonSort == null)
				setSeasonSort(seasonSortWrap.o);
		}
		seasonSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected Integer sessionSort;
	@JsonIgnore
	public Wrap<Integer> sessionSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("sessionSort").o(sessionSort);

	/**	<br/>L'entité « sessionSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionSort">Trouver l'entité sessionSort dans Solr</a>
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
	public SchoolEnrollment setSessionSort(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionSort = Integer.parseInt(o);
		this.sessionSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment sessionSortInit() {
		if(!sessionSortWrap.alreadyInitialized) {
			_sessionSort(sessionSortWrap);
			if(sessionSort == null)
				setSessionSort(sessionSortWrap.o);
		}
		sessionSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected Integer ageSort;
	@JsonIgnore
	public Wrap<Integer> ageSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageSort").o(ageSort);

	/**	<br/>L'entité « ageSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageSort">Trouver l'entité ageSort dans Solr</a>
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
	public SchoolEnrollment setAgeSort(String o) {
		if(NumberUtils.isParsable(o))
			this.ageSort = Integer.parseInt(o);
		this.ageSortWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment ageSortInit() {
		if(!ageSortWrap.alreadyInitialized) {
			_ageSort(ageSortWrap);
			if(ageSort == null)
				setAgeSort(ageSortWrap.o);
		}
		ageSortWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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

	/////////////////
	// childSearch //
	/////////////////

	/**	L'entité « childSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolChild>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolChild> childSearch = new SearchList<SchoolChild>();
	@JsonIgnore
	public Wrap<SearchList<SchoolChild>> childSearchWrap = new Wrap<SearchList<SchoolChild>>().p(this).c(SearchList.class).var("childSearch").o(childSearch);

	/**	<br/>L'entité « childSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolChild>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childSearch">Trouver l'entité childSearch dans Solr</a>
	 * <br/>
	 * @param childSearch est l'entité déjà construit. 
	 **/
	protected abstract void _childSearch(SearchList<SchoolChild> l);

	public SearchList<SchoolChild> getChildSearch() {
		return childSearch;
	}

	public void setChildSearch(SearchList<SchoolChild> childSearch) {
		this.childSearch = childSearch;
		this.childSearchWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment childSearchInit() {
		if(!childSearchWrap.alreadyInitialized) {
			_childSearch(childSearch);
		}
		childSearch.initDeepForClass(siteRequest_);
		childSearchWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	////////////
	// child_ //
	////////////

	/**	L'entité « child_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SchoolChild child_;
	@JsonIgnore
	public Wrap<SchoolChild> child_Wrap = new Wrap<SchoolChild>().p(this).c(SchoolChild.class).var("child_").o(child_);

	/**	<br/>L'entité « child_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:child_">Trouver l'entité child_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _child_(Wrap<SchoolChild> c);

	public SchoolChild getChild_() {
		return child_;
	}

	public void setChild_(SchoolChild child_) {
		this.child_ = child_;
		this.child_Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment child_Init() {
		if(!child_Wrap.alreadyInitialized) {
			_child_(child_Wrap);
			if(child_ == null)
				setChild_(child_Wrap.o);
		}
		child_Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	///////////////
	// momSearch //
	///////////////

	/**	L'entité « momSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolMom>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolMom> momSearch = new SearchList<SchoolMom>();
	@JsonIgnore
	public Wrap<SearchList<SchoolMom>> momSearchWrap = new Wrap<SearchList<SchoolMom>>().p(this).c(SearchList.class).var("momSearch").o(momSearch);

	/**	<br/>L'entité « momSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolMom>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:momSearch">Trouver l'entité momSearch dans Solr</a>
	 * <br/>
	 * @param momSearch est l'entité déjà construit. 
	 **/
	protected abstract void _momSearch(SearchList<SchoolMom> l);

	public SearchList<SchoolMom> getMomSearch() {
		return momSearch;
	}

	public void setMomSearch(SearchList<SchoolMom> momSearch) {
		this.momSearch = momSearch;
		this.momSearchWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment momSearchInit() {
		if(!momSearchWrap.alreadyInitialized) {
			_momSearch(momSearch);
		}
		momSearch.initDeepForClass(siteRequest_);
		momSearchWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	//////////
	// moms //
	//////////

	/**	L'entité « moms »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected List<SchoolMom> moms;
	@JsonIgnore
	public Wrap<List<SchoolMom>> momsWrap = new Wrap<List<SchoolMom>>().p(this).c(List.class).var("moms").o(moms);

	/**	<br/>L'entité « moms »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:moms">Trouver l'entité moms dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _moms(Wrap<List<SchoolMom>> c);

	public List<SchoolMom> getMoms() {
		return moms;
	}

	public void setMoms(List<SchoolMom> moms) {
		this.moms = moms;
		this.momsWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addMoms(SchoolMom...objets) {
		for(SchoolMom o : objets) {
			addMoms(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addMoms(SchoolMom o) {
		if(o != null && !moms.contains(o))
			this.moms.add(o);
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment momsInit() {
		if(!momsWrap.alreadyInitialized) {
			_moms(momsWrap);
			if(moms == null)
				setMoms(momsWrap.o);
		}
		momsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	///////////////
	// dadSearch //
	///////////////

	/**	L'entité « dadSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolDad>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolDad> dadSearch = new SearchList<SchoolDad>();
	@JsonIgnore
	public Wrap<SearchList<SchoolDad>> dadSearchWrap = new Wrap<SearchList<SchoolDad>>().p(this).c(SearchList.class).var("dadSearch").o(dadSearch);

	/**	<br/>L'entité « dadSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolDad>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadSearch">Trouver l'entité dadSearch dans Solr</a>
	 * <br/>
	 * @param dadSearch est l'entité déjà construit. 
	 **/
	protected abstract void _dadSearch(SearchList<SchoolDad> l);

	public SearchList<SchoolDad> getDadSearch() {
		return dadSearch;
	}

	public void setDadSearch(SearchList<SchoolDad> dadSearch) {
		this.dadSearch = dadSearch;
		this.dadSearchWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment dadSearchInit() {
		if(!dadSearchWrap.alreadyInitialized) {
			_dadSearch(dadSearch);
		}
		dadSearch.initDeepForClass(siteRequest_);
		dadSearchWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	//////////
	// dads //
	//////////

	/**	L'entité « dads »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected List<SchoolDad> dads;
	@JsonIgnore
	public Wrap<List<SchoolDad>> dadsWrap = new Wrap<List<SchoolDad>>().p(this).c(List.class).var("dads").o(dads);

	/**	<br/>L'entité « dads »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dads">Trouver l'entité dads dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _dads(Wrap<List<SchoolDad>> c);

	public List<SchoolDad> getDads() {
		return dads;
	}

	public void setDads(List<SchoolDad> dads) {
		this.dads = dads;
		this.dadsWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addDads(SchoolDad...objets) {
		for(SchoolDad o : objets) {
			addDads(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addDads(SchoolDad o) {
		if(o != null && !dads.contains(o))
			this.dads.add(o);
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment dadsInit() {
		if(!dadsWrap.alreadyInitialized) {
			_dads(dadsWrap);
			if(dads == null)
				setDads(dadsWrap.o);
		}
		dadsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	////////////////////
	// guardianSearch //
	////////////////////

	/**	L'entité « guardianSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolGuardian>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolGuardian> guardianSearch = new SearchList<SchoolGuardian>();
	@JsonIgnore
	public Wrap<SearchList<SchoolGuardian>> guardianSearchWrap = new Wrap<SearchList<SchoolGuardian>>().p(this).c(SearchList.class).var("guardianSearch").o(guardianSearch);

	/**	<br/>L'entité « guardianSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolGuardian>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianSearch">Trouver l'entité guardianSearch dans Solr</a>
	 * <br/>
	 * @param guardianSearch est l'entité déjà construit. 
	 **/
	protected abstract void _guardianSearch(SearchList<SchoolGuardian> l);

	public SearchList<SchoolGuardian> getGuardianSearch() {
		return guardianSearch;
	}

	public void setGuardianSearch(SearchList<SchoolGuardian> guardianSearch) {
		this.guardianSearch = guardianSearch;
		this.guardianSearchWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment guardianSearchInit() {
		if(!guardianSearchWrap.alreadyInitialized) {
			_guardianSearch(guardianSearch);
		}
		guardianSearch.initDeepForClass(siteRequest_);
		guardianSearchWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	///////////////
	// guardians //
	///////////////

	/**	L'entité « guardians »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected List<SchoolGuardian> guardians;
	@JsonIgnore
	public Wrap<List<SchoolGuardian>> guardiansWrap = new Wrap<List<SchoolGuardian>>().p(this).c(List.class).var("guardians").o(guardians);

	/**	<br/>L'entité « guardians »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardians">Trouver l'entité guardians dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _guardians(Wrap<List<SchoolGuardian>> c);

	public List<SchoolGuardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<SchoolGuardian> guardians) {
		this.guardians = guardians;
		this.guardiansWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addGuardians(SchoolGuardian...objets) {
		for(SchoolGuardian o : objets) {
			addGuardians(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addGuardians(SchoolGuardian o) {
		if(o != null && !guardians.contains(o))
			this.guardians.add(o);
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment guardiansInit() {
		if(!guardiansWrap.alreadyInitialized) {
			_guardians(guardiansWrap);
			if(guardians == null)
				setGuardians(guardiansWrap.o);
		}
		guardiansWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	///////////////////////
	// childCompleteName //
	///////////////////////

	/**	L'entité « childCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String childCompleteName;
	@JsonIgnore
	public Wrap<String> childCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("childCompleteName").o(childCompleteName);

	/**	<br/>L'entité « childCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childCompleteName">Trouver l'entité childCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _childCompleteName(Wrap<String> c);

	public String getChildCompleteName() {
		return childCompleteName;
	}

	public void setChildCompleteName(String childCompleteName) {
		this.childCompleteName = childCompleteName;
		this.childCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment childCompleteNameInit() {
		if(!childCompleteNameWrap.alreadyInitialized) {
			_childCompleteName(childCompleteNameWrap);
			if(childCompleteName == null)
				setChildCompleteName(childCompleteNameWrap.o);
		}
		childCompleteNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrChildCompleteName() {
		return childCompleteName;
	}

	public String strChildCompleteName() {
		return childCompleteName == null ? "" : childCompleteName;
	}

	public String jsonChildCompleteName() {
		return childCompleteName == null ? "" : childCompleteName;
	}

	public String nomAffichageChildCompleteName() {
		return "r: EnfantNomComplet";
	}

	public String htmTooltipChildCompleteName() {
		return null;
	}

	public String htmChildCompleteName() {
		return childCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strChildCompleteName());
	}

	public void inputChildCompleteName(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "r: EnfantNomComplet")
			.a("id", classApiMethodMethod, "_childCompleteName");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setChildCompleteName inputSchoolEnrollment", pk, "ChildCompleteName w3-input w3-border ");
				a("name", "setChildCompleteName");
			} else {
				a("class", "valueChildCompleteName w3-input w3-border inputSchoolEnrollment", pk, "ChildCompleteName w3-input w3-border ");
				a("name", "childCompleteName");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildCompleteName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childCompleteName')); }, function() { addError($('#", classApiMethodMethod, "_childCompleteName')); }); ");
			}
			a("value", strChildCompleteName())
		.fg();

	}

	public void htmChildCompleteName(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentChildCompleteName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_childCompleteName").a("class", "").f().sx("r: EnfantNomComplet").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildCompleteName(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childCompleteName')); $('#", classApiMethodMethod, "_childCompleteName').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildCompleteName', null, function() { addGlow($('#", classApiMethodMethod, "_childCompleteName')); }, function() { addError($('#", classApiMethodMethod, "_childCompleteName')); }); ")
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

	////////////////////
	// childBirthDate //
	////////////////////

	/**	L'entité « childBirthDate »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate childBirthDate;
	@JsonIgnore
	public Wrap<LocalDate> childBirthDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("childBirthDate").o(childBirthDate);

	/**	<br/>L'entité « childBirthDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childBirthDate">Trouver l'entité childBirthDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _childBirthDate(Wrap<LocalDate> c);

	public LocalDate getChildBirthDate() {
		return childBirthDate;
	}

	public void setChildBirthDate(LocalDate childBirthDate) {
		this.childBirthDate = childBirthDate;
		this.childBirthDateWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setChildBirthDate(Instant o) {
		this.childBirthDate = LocalDate.from(o);
		this.childBirthDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setChildBirthDate(String o) {
		this.childBirthDate = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.childBirthDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setChildBirthDate(Date o) {
		this.childBirthDate = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.childBirthDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment childBirthDateInit() {
		if(!childBirthDateWrap.alreadyInitialized) {
			_childBirthDate(childBirthDateWrap);
			if(childBirthDate == null)
				setChildBirthDate(childBirthDateWrap.o);
		}
		childBirthDateWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrChildBirthDate() {
		return childBirthDate == null ? null : Date.from(childBirthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strChildBirthDate() {
		return childBirthDate == null ? "" : childBirthDate.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonChildBirthDate() {
		return childBirthDate == null ? "" : childBirthDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageChildBirthDate() {
		return "r: enfant_";
	}

	public String htmTooltipChildBirthDate() {
		return null;
	}

	public String htmChildBirthDate() {
		return childBirthDate == null ? "" : StringEscapeUtils.escapeHtml4(strChildBirthDate());
	}

	public void inputChildBirthDate(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setChildBirthDate inputSchoolEnrollment", pk, "ChildBirthDate w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_childBirthDate")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", childBirthDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(childBirthDate))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildBirthDate', s, function() { addGlow($('#", classApiMethodMethod, "_childBirthDate')); }, function() { addError($('#", classApiMethodMethod, "_childBirthDate')); }); } ")
			.fg();
	}

	public void htmChildBirthDate(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentChildBirthDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_childBirthDate").a("class", "").f().sx("r: enfant_").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputChildBirthDate(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childBirthDate')); $('#", classApiMethodMethod, "_childBirthDate').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildBirthDate', null, function() { addGlow($('#", classApiMethodMethod, "_childBirthDate')); }, function() { addError($('#", classApiMethodMethod, "_childBirthDate')); }); ")
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Trouver l'entité schoolName dans Solr</a>
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
	protected SchoolEnrollment schoolNameInit() {
		if(!schoolNameWrap.alreadyInitialized) {
			_schoolName(schoolNameWrap);
			if(schoolName == null)
				setSchoolName(schoolNameWrap.o);
		}
		schoolNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Trouver l'entité schoolCompleteName dans Solr</a>
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
	protected SchoolEnrollment schoolCompleteNameInit() {
		if(!schoolCompleteNameWrap.alreadyInitialized) {
			_schoolCompleteName(schoolCompleteNameWrap);
			if(schoolCompleteName == null)
				setSchoolCompleteName(schoolCompleteNameWrap.o);
		}
		schoolCompleteNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Trouver l'entité schoolLocation dans Solr</a>
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
	protected SchoolEnrollment schoolLocationInit() {
		if(!schoolLocationWrap.alreadyInitialized) {
			_schoolLocation(schoolLocationWrap);
			if(schoolLocation == null)
				setSchoolLocation(schoolLocationWrap.o);
		}
		schoolLocationWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Trouver l'entité schoolAddress dans Solr</a>
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
	protected SchoolEnrollment schoolAddressInit() {
		if(!schoolAddressWrap.alreadyInitialized) {
			_schoolAddress(schoolAddressWrap);
			if(schoolAddress == null)
				setSchoolAddress(schoolAddressWrap.o);
		}
		schoolAddressWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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

	public void inputSchoolAddress(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "address")
			.a("id", classApiMethodMethod, "_schoolAddress");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSchoolAddress inputSchoolEnrollment", pk, "SchoolAddress w3-input w3-border ");
				a("name", "setSchoolAddress");
			} else {
				a("class", "valueSchoolAddress w3-input w3-border inputSchoolEnrollment", pk, "SchoolAddress w3-input w3-border ");
				a("name", "schoolAddress");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setSchoolAddress', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_schoolAddress')); }, function() { addError($('#", classApiMethodMethod, "_schoolAddress')); }); ");
			}
			a("value", strSchoolAddress())
		.fg();

	}

	public void htmSchoolAddress(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentSchoolAddress").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_schoolAddress").a("class", "").f().sx("address").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSchoolAddress(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_schoolAddress')); $('#", classApiMethodMethod, "_schoolAddress').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setSchoolAddress', null, function() { addGlow($('#", classApiMethodMethod, "_schoolAddress')); }, function() { addError($('#", classApiMethodMethod, "_schoolAddress')); }); ")
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Trouver l'entité schoolPhoneNumber dans Solr</a>
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
	protected SchoolEnrollment schoolPhoneNumberInit() {
		if(!schoolPhoneNumberWrap.alreadyInitialized) {
			_schoolPhoneNumber(schoolPhoneNumberWrap);
			if(schoolPhoneNumber == null)
				setSchoolPhoneNumber(schoolPhoneNumberWrap.o);
		}
		schoolPhoneNumberWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Trouver l'entité schoolAdministratorName dans Solr</a>
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
	protected SchoolEnrollment schoolAdministratorNameInit() {
		if(!schoolAdministratorNameWrap.alreadyInitialized) {
			_schoolAdministratorName(schoolAdministratorNameWrap);
			if(schoolAdministratorName == null)
				setSchoolAdministratorName(schoolAdministratorNameWrap.o);
		}
		schoolAdministratorNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected Integer yearStart;
	@JsonIgnore
	public Wrap<Integer> yearStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearStart").o(yearStart);

	/**	<br/>L'entité « yearStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Trouver l'entité yearStart dans Solr</a>
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
	public SchoolEnrollment setYearStart(String o) {
		if(NumberUtils.isParsable(o))
			this.yearStart = Integer.parseInt(o);
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment yearStartInit() {
		if(!yearStartWrap.alreadyInitialized) {
			_yearStart(yearStartWrap);
			if(yearStart == null)
				setYearStart(yearStartWrap.o);
		}
		yearStartWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected Integer yearEnd;
	@JsonIgnore
	public Wrap<Integer> yearEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearEnd").o(yearEnd);

	/**	<br/>L'entité « yearEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Trouver l'entité yearEnd dans Solr</a>
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
	public SchoolEnrollment setYearEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.yearEnd = Integer.parseInt(o);
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment yearEndInit() {
		if(!yearEndWrap.alreadyInitialized) {
			_yearEnd(yearEndWrap);
			if(yearEnd == null)
				setYearEnd(yearEndWrap.o);
		}
		yearEndWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate seasonStartDate;
	@JsonIgnore
	public Wrap<LocalDate> seasonStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("seasonStartDate").o(seasonStartDate);

	/**	<br/>L'entité « seasonStartDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Trouver l'entité seasonStartDate dans Solr</a>
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
	public SchoolEnrollment setSeasonStartDate(Instant o) {
		this.seasonStartDate = LocalDate.from(o);
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setSeasonStartDate(String o) {
		this.seasonStartDate = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setSeasonStartDate(Date o) {
		this.seasonStartDate = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasonStartDateInit() {
		if(!seasonStartDateWrap.alreadyInitialized) {
			_seasonStartDate(seasonStartDateWrap);
			if(seasonStartDate == null)
				setSeasonStartDate(seasonStartDateWrap.o);
		}
		seasonStartDateWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSummer">Trouver l'entité seasonSummer dans Solr</a>
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
	public SchoolEnrollment setSeasonSummer(String o) {
		this.seasonSummer = Boolean.parseBoolean(o);
		this.seasonSummerWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasonSummerInit() {
		if(!seasonSummerWrap.alreadyInitialized) {
			_seasonSummer(seasonSummerWrap);
			if(seasonSummer == null)
				setSeasonSummer(seasonSummerWrap.o);
		}
		seasonSummerWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonWinter">Trouver l'entité seasonWinter dans Solr</a>
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
	public SchoolEnrollment setSeasonWinter(String o) {
		this.seasonWinter = Boolean.parseBoolean(o);
		this.seasonWinterWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment seasonWinterInit() {
		if(!seasonWinterWrap.alreadyInitialized) {
			_seasonWinter(seasonWinterWrap);
			if(seasonWinter == null)
				setSeasonWinter(seasonWinterWrap.o);
		}
		seasonWinterWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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

	///////////////////////
	// yearEnrollmentFee //
	///////////////////////

	/**	L'entité « yearEnrollmentFee »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected BigDecimal yearEnrollmentFee;
	@JsonIgnore
	public Wrap<BigDecimal> yearEnrollmentFeeWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("yearEnrollmentFee").o(yearEnrollmentFee);

	/**	<br/>L'entité « yearEnrollmentFee »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnrollmentFee">Trouver l'entité yearEnrollmentFee dans Solr</a>
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
	public SchoolEnrollment setYearEnrollmentFee(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setYearEnrollmentFee(Double o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setYearEnrollmentFee(Integer o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment yearEnrollmentFeeInit() {
		if(!yearEnrollmentFeeWrap.alreadyInitialized) {
			_yearEnrollmentFee(yearEnrollmentFeeWrap);
			if(yearEnrollmentFee == null)
				setYearEnrollmentFee(yearEnrollmentFeeWrap.o);
		}
		yearEnrollmentFeeWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
		return "end of year";
	}

	public String htmTooltipYearEnrollmentFee() {
		return null;
	}

	public String htmYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : StringEscapeUtils.escapeHtml4(strYearEnrollmentFee());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonCompleteName">Trouver l'entité seasonCompleteName dans Solr</a>
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
	protected SchoolEnrollment seasonCompleteNameInit() {
		if(!seasonCompleteNameWrap.alreadyInitialized) {
			_seasonCompleteName(seasonCompleteNameWrap);
			if(seasonCompleteName == null)
				setSeasonCompleteName(seasonCompleteNameWrap.o);
		}
		seasonCompleteNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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

	//////////////////////
	// sessionStartDate //
	//////////////////////

	/**	L'entité « sessionStartDate »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate sessionStartDate;
	@JsonIgnore
	public Wrap<LocalDate> sessionStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionStartDate").o(sessionStartDate);

	/**	<br/>L'entité « sessionStartDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionStartDate">Trouver l'entité sessionStartDate dans Solr</a>
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
	public SchoolEnrollment setSessionStartDate(Instant o) {
		this.sessionStartDate = LocalDate.from(o);
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setSessionStartDate(String o) {
		this.sessionStartDate = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setSessionStartDate(Date o) {
		this.sessionStartDate = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment sessionStartDateInit() {
		if(!sessionStartDateWrap.alreadyInitialized) {
			_sessionStartDate(sessionStartDateWrap);
			if(sessionStartDate == null)
				setSessionStartDate(sessionStartDateWrap.o);
		}
		sessionStartDateWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrSessionStartDate() {
		return sessionStartDate == null ? null : Date.from(sessionStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSessionStartDate() {
		return sessionStartDate == null ? "" : sessionStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonSessionStartDate() {
		return sessionStartDate == null ? "" : sessionStartDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
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

	////////////////////
	// sessionEndDate //
	////////////////////

	/**	L'entité « sessionEndDate »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate sessionEndDate;
	@JsonIgnore
	public Wrap<LocalDate> sessionEndDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionEndDate").o(sessionEndDate);

	/**	<br/>L'entité « sessionEndDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionEndDate">Trouver l'entité sessionEndDate dans Solr</a>
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
	public SchoolEnrollment setSessionEndDate(Instant o) {
		this.sessionEndDate = LocalDate.from(o);
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setSessionEndDate(String o) {
		this.sessionEndDate = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setSessionEndDate(Date o) {
		this.sessionEndDate = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment sessionEndDateInit() {
		if(!sessionEndDateWrap.alreadyInitialized) {
			_sessionEndDate(sessionEndDateWrap);
			if(sessionEndDate == null)
				setSessionEndDate(sessionEndDateWrap.o);
		}
		sessionEndDateWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrSessionEndDate() {
		return sessionEndDate == null ? null : Date.from(sessionEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSessionEndDate() {
		return sessionEndDate == null ? "" : sessionEndDate.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonSessionEndDate() {
		return sessionEndDate == null ? "" : sessionEndDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageCompleteName">Trouver l'entité ageCompleteName dans Solr</a>
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
	protected SchoolEnrollment ageCompleteNameInit() {
		if(!ageCompleteNameWrap.alreadyInitialized) {
			_ageCompleteName(ageCompleteNameWrap);
			if(ageCompleteName == null)
				setAgeCompleteName(ageCompleteNameWrap.o);
		}
		ageCompleteNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected Integer ageStart;
	@JsonIgnore
	public Wrap<Integer> ageStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageStart").o(ageStart);

	/**	<br/>L'entité « ageStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageStart">Trouver l'entité ageStart dans Solr</a>
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
	public SchoolEnrollment setAgeStart(String o) {
		if(NumberUtils.isParsable(o))
			this.ageStart = Integer.parseInt(o);
		this.ageStartWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment ageStartInit() {
		if(!ageStartWrap.alreadyInitialized) {
			_ageStart(ageStartWrap);
			if(ageStart == null)
				setAgeStart(ageStartWrap.o);
		}
		ageStartWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected Integer ageEnd;
	@JsonIgnore
	public Wrap<Integer> ageEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageEnd").o(ageEnd);

	/**	<br/>L'entité « ageEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageEnd">Trouver l'entité ageEnd dans Solr</a>
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
	public SchoolEnrollment setAgeEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.ageEnd = Integer.parseInt(o);
		this.ageEndWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment ageEndInit() {
		if(!ageEndWrap.alreadyInitialized) {
			_ageEnd(ageEndWrap);
			if(ageEnd == null)
				setAgeEnd(ageEndWrap.o);
		}
		ageEndWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalTime blockStartTime;
	@JsonIgnore
	public Wrap<LocalTime> blockStartTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockStartTime").o(blockStartTime);

	/**	<br/>L'entité « blockStartTime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockStartTime">Trouver l'entité blockStartTime dans Solr</a>
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
	public SchoolEnrollment setBlockStartTime(String o) {
		try {
			this.blockStartTime = LocalTime.parse(o, DateTimeFormatter.ofPattern("HH mm"));
			this.blockStartTimeWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockStartTimeInit() {
		if(!blockStartTimeWrap.alreadyInitialized) {
			_blockStartTime(blockStartTimeWrap);
			if(blockStartTime == null)
				setBlockStartTime(blockStartTimeWrap.o);
		}
		blockStartTimeWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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

	//////////////////
	// blockEndTime //
	//////////////////

	/**	L'entité « blockEndTime »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalTime blockEndTime;
	@JsonIgnore
	public Wrap<LocalTime> blockEndTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockEndTime").o(blockEndTime);

	/**	<br/>L'entité « blockEndTime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockEndTime">Trouver l'entité blockEndTime dans Solr</a>
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
	public SchoolEnrollment setBlockEndTime(String o) {
		try {
			this.blockEndTime = LocalTime.parse(o, DateTimeFormatter.ofPattern("HH mm"));
			this.blockEndTimeWrap.alreadyInitialized = true;
		} catch(Exception e) {
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockEndTimeInit() {
		if(!blockEndTimeWrap.alreadyInitialized) {
			_blockEndTime(blockEndTimeWrap);
			if(blockEndTime == null)
				setBlockEndTime(blockEndTimeWrap.o);
		}
		blockEndTimeWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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

	////////////////////////
	// blockPricePerMonth //
	////////////////////////

	/**	L'entité « blockPricePerMonth »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected BigDecimal blockPricePerMonth;
	@JsonIgnore
	public Wrap<BigDecimal> blockPricePerMonthWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("blockPricePerMonth").o(blockPricePerMonth);

	/**	<br/>L'entité « blockPricePerMonth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockPricePerMonth">Trouver l'entité blockPricePerMonth dans Solr</a>
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
	public SchoolEnrollment setBlockPricePerMonth(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setBlockPricePerMonth(Double o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setBlockPricePerMonth(Integer o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockPricePerMonthInit() {
		if(!blockPricePerMonthWrap.alreadyInitialized) {
			_blockPricePerMonth(blockPricePerMonthWrap);
			if(blockPricePerMonth == null)
				setBlockPricePerMonth(blockPricePerMonthWrap.o);
		}
		blockPricePerMonthWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Double solrBlockPricePerMonth() {
		return blockPricePerMonth == null ? null : blockPricePerMonth.doubleValue();
	}

	public String strBlockPricePerMonth() {
		return blockPricePerMonth == null ? "" : blockPricePerMonth.setScale(2).toString();
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSunday">Trouver l'entité blockSunday dans Solr</a>
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
	public SchoolEnrollment setBlockSunday(String o) {
		this.blockSunday = Boolean.parseBoolean(o);
		this.blockSundayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockSundayInit() {
		if(!blockSundayWrap.alreadyInitialized) {
			_blockSunday(blockSundayWrap);
			if(blockSunday == null)
				setBlockSunday(blockSundayWrap.o);
		}
		blockSundayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockMonday">Trouver l'entité blockMonday dans Solr</a>
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
	public SchoolEnrollment setBlockMonday(String o) {
		this.blockMonday = Boolean.parseBoolean(o);
		this.blockMondayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockMondayInit() {
		if(!blockMondayWrap.alreadyInitialized) {
			_blockMonday(blockMondayWrap);
			if(blockMonday == null)
				setBlockMonday(blockMondayWrap.o);
		}
		blockMondayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTuesday">Trouver l'entité blockTuesday dans Solr</a>
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
	public SchoolEnrollment setBlockTuesday(String o) {
		this.blockTuesday = Boolean.parseBoolean(o);
		this.blockTuesdayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockTuesdayInit() {
		if(!blockTuesdayWrap.alreadyInitialized) {
			_blockTuesday(blockTuesdayWrap);
			if(blockTuesday == null)
				setBlockTuesday(blockTuesdayWrap.o);
		}
		blockTuesdayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockWednesday">Trouver l'entité blockWednesday dans Solr</a>
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
	public SchoolEnrollment setBlockWednesday(String o) {
		this.blockWednesday = Boolean.parseBoolean(o);
		this.blockWednesdayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockWednesdayInit() {
		if(!blockWednesdayWrap.alreadyInitialized) {
			_blockWednesday(blockWednesdayWrap);
			if(blockWednesday == null)
				setBlockWednesday(blockWednesdayWrap.o);
		}
		blockWednesdayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockThursday">Trouver l'entité blockThursday dans Solr</a>
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
	public SchoolEnrollment setBlockThursday(String o) {
		this.blockThursday = Boolean.parseBoolean(o);
		this.blockThursdayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockThursdayInit() {
		if(!blockThursdayWrap.alreadyInitialized) {
			_blockThursday(blockThursdayWrap);
			if(blockThursday == null)
				setBlockThursday(blockThursdayWrap.o);
		}
		blockThursdayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockFriday">Trouver l'entité blockFriday dans Solr</a>
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
	public SchoolEnrollment setBlockFriday(String o) {
		this.blockFriday = Boolean.parseBoolean(o);
		this.blockFridayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockFridayInit() {
		if(!blockFridayWrap.alreadyInitialized) {
			_blockFriday(blockFridayWrap);
			if(blockFriday == null)
				setBlockFriday(blockFridayWrap.o);
		}
		blockFridayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSaturday">Trouver l'entité blockSaturday dans Solr</a>
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
	public SchoolEnrollment setBlockSaturday(String o) {
		this.blockSaturday = Boolean.parseBoolean(o);
		this.blockSaturdayWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockSaturdayInit() {
		if(!blockSaturdayWrap.alreadyInitialized) {
			_blockSaturday(blockSaturdayWrap);
			if(blockSaturday == null)
				setBlockSaturday(blockSaturdayWrap.o);
		}
		blockSaturdayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
	@JsonSerialize(using = ToStringSerializer.class)
	protected BigDecimal blockTotalPrice;
	@JsonIgnore
	public Wrap<BigDecimal> blockTotalPriceWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("blockTotalPrice").o(blockTotalPrice);

	/**	<br/>L'entité « blockTotalPrice »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTotalPrice">Trouver l'entité blockTotalPrice dans Solr</a>
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
	public SchoolEnrollment setBlockTotalPrice(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setBlockTotalPrice(Double o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setBlockTotalPrice(Integer o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blockTotalPriceWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment blockTotalPriceInit() {
		if(!blockTotalPriceWrap.alreadyInitialized) {
			_blockTotalPrice(blockTotalPriceWrap);
			if(blockTotalPrice == null)
				setBlockTotalPrice(blockTotalPriceWrap.o);
		}
		blockTotalPriceWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Double solrBlockTotalPrice() {
		return blockTotalPrice == null ? null : blockTotalPrice.doubleValue();
	}

	public String strBlockTotalPrice() {
		return blockTotalPrice == null ? "" : blockTotalPrice.setScale(2).toString();
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

	////////////////////
	// blockAdminName //
	////////////////////

	/**	L'entité « blockAdminName »
	 *	 is defined as null before being initialized. 
	 */
	protected String blockAdminName;
	@JsonIgnore
	public Wrap<String> blockAdminNameWrap = new Wrap<String>().p(this).c(String.class).var("blockAdminName").o(blockAdminName);

	/**	<br/>L'entité « blockAdminName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockAdminName">Trouver l'entité blockAdminName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockAdminName(Wrap<String> c);

	public String getBlockAdminName() {
		return blockAdminName;
	}

	public void setBlockAdminName(String blockAdminName) {
		this.blockAdminName = blockAdminName;
		this.blockAdminNameWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment blockAdminNameInit() {
		if(!blockAdminNameWrap.alreadyInitialized) {
			_blockAdminName(blockAdminNameWrap);
			if(blockAdminName == null)
				setBlockAdminName(blockAdminNameWrap.o);
		}
		blockAdminNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrBlockAdminName() {
		return blockAdminName;
	}

	public String strBlockAdminName() {
		return blockAdminName == null ? "" : blockAdminName;
	}

	public String jsonBlockAdminName() {
		return blockAdminName == null ? "" : blockAdminName;
	}

	public String nomAffichageBlockAdminName() {
		return null;
	}

	public String htmTooltipBlockAdminName() {
		return null;
	}

	public String htmBlockAdminName() {
		return blockAdminName == null ? "" : StringEscapeUtils.escapeHtml4(strBlockAdminName());
	}

	////////////////////
	// blockShortName //
	////////////////////

	/**	L'entité « blockShortName »
	 *	 is defined as null before being initialized. 
	 */
	protected String blockShortName;
	@JsonIgnore
	public Wrap<String> blockShortNameWrap = new Wrap<String>().p(this).c(String.class).var("blockShortName").o(blockShortName);

	/**	<br/>L'entité « blockShortName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockShortName">Trouver l'entité blockShortName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blockShortName(Wrap<String> c);

	public String getBlockShortName() {
		return blockShortName;
	}

	public void setBlockShortName(String blockShortName) {
		this.blockShortName = blockShortName;
		this.blockShortNameWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment blockShortNameInit() {
		if(!blockShortNameWrap.alreadyInitialized) {
			_blockShortName(blockShortNameWrap);
			if(blockShortName == null)
				setBlockShortName(blockShortNameWrap.o);
		}
		blockShortNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrBlockShortName() {
		return blockShortName;
	}

	public String strBlockShortName() {
		return blockShortName == null ? "" : blockShortName;
	}

	public String jsonBlockShortName() {
		return blockShortName == null ? "" : blockShortName;
	}

	public String nomAffichageBlockShortName() {
		return null;
	}

	public String htmTooltipBlockShortName() {
		return null;
	}

	public String htmBlockShortName() {
		return blockShortName == null ? "" : StringEscapeUtils.escapeHtml4(strBlockShortName());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockCompleteName">Trouver l'entité blockCompleteName dans Solr</a>
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
	protected SchoolEnrollment blockCompleteNameInit() {
		if(!blockCompleteNameWrap.alreadyInitialized) {
			_blockCompleteName(blockCompleteNameWrap);
			if(blockCompleteName == null)
				setBlockCompleteName(blockCompleteNameWrap.o);
		}
		blockCompleteNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
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
		return null;
	}

	public String htmTooltipBlockCompleteName() {
		return null;
	}

	public String htmBlockCompleteName() {
		return blockCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strBlockCompleteName());
	}

	////////////////////////
	// enrollmentApproved //
	////////////////////////

	/**	L'entité « enrollmentApproved »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean enrollmentApproved;
	@JsonIgnore
	public Wrap<Boolean> enrollmentApprovedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentApproved").o(enrollmentApproved);

	/**	<br/>L'entité « enrollmentApproved »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentApproved">Trouver l'entité enrollmentApproved dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentApproved(Wrap<Boolean> c);

	public Boolean getEnrollmentApproved() {
		return enrollmentApproved;
	}

	public void setEnrollmentApproved(Boolean enrollmentApproved) {
		this.enrollmentApproved = enrollmentApproved;
		this.enrollmentApprovedWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentApproved(String o) {
		this.enrollmentApproved = Boolean.parseBoolean(o);
		this.enrollmentApprovedWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentApprovedInit() {
		if(!enrollmentApprovedWrap.alreadyInitialized) {
			_enrollmentApproved(enrollmentApprovedWrap);
			if(enrollmentApproved == null)
				setEnrollmentApproved(enrollmentApprovedWrap.o);
		}
		enrollmentApprovedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrEnrollmentApproved() {
		return enrollmentApproved;
	}

	public String strEnrollmentApproved() {
		return enrollmentApproved == null ? "" : enrollmentApproved.toString();
	}

	public String jsonEnrollmentApproved() {
		return enrollmentApproved == null ? "" : enrollmentApproved.toString();
	}

	public String nomAffichageEnrollmentApproved() {
		return "approved";
	}

	public String htmTooltipEnrollmentApproved() {
		return null;
	}

	public String htmEnrollmentApproved() {
		return enrollmentApproved == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentApproved());
	}

	public void inputEnrollmentApproved(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		if("Page".equals(classApiMethodMethod)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_enrollmentApproved")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classApiMethodMethod, "_enrollmentApproved");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			a("class", "setEnrollmentApproved inputSchoolEnrollment", pk, "EnrollmentApproved w3-input w3-border ");
			a("name", "setEnrollmentApproved");
		} else {
			a("class", "valueEnrollmentApproved inputSchoolEnrollment", pk, "EnrollmentApproved w3-input w3-border ");
			a("name", "enrollmentApproved");
		}
		if("Page".equals(classApiMethodMethod)) {
			a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentApproved', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_enrollmentApproved')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentApproved')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getEnrollmentApproved() != null && getEnrollmentApproved())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmEnrollmentApproved(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentApproved").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_enrollmentApproved").a("class", "").f().sx("approved").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentApproved(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////////
	// enrollmentImmunizations //
	/////////////////////////////

	/**	L'entité « enrollmentImmunizations »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean enrollmentImmunizations;
	@JsonIgnore
	public Wrap<Boolean> enrollmentImmunizationsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentImmunizations").o(enrollmentImmunizations);

	/**	<br/>L'entité « enrollmentImmunizations »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentImmunizations">Trouver l'entité enrollmentImmunizations dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentImmunizations(Wrap<Boolean> c);

	public Boolean getEnrollmentImmunizations() {
		return enrollmentImmunizations;
	}

	public void setEnrollmentImmunizations(Boolean enrollmentImmunizations) {
		this.enrollmentImmunizations = enrollmentImmunizations;
		this.enrollmentImmunizationsWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentImmunizations(String o) {
		this.enrollmentImmunizations = Boolean.parseBoolean(o);
		this.enrollmentImmunizationsWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentImmunizationsInit() {
		if(!enrollmentImmunizationsWrap.alreadyInitialized) {
			_enrollmentImmunizations(enrollmentImmunizationsWrap);
			if(enrollmentImmunizations == null)
				setEnrollmentImmunizations(enrollmentImmunizationsWrap.o);
		}
		enrollmentImmunizationsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrEnrollmentImmunizations() {
		return enrollmentImmunizations;
	}

	public String strEnrollmentImmunizations() {
		return enrollmentImmunizations == null ? "" : enrollmentImmunizations.toString();
	}

	public String jsonEnrollmentImmunizations() {
		return enrollmentImmunizations == null ? "" : enrollmentImmunizations.toString();
	}

	public String nomAffichageEnrollmentImmunizations() {
		return "immunized";
	}

	public String htmTooltipEnrollmentImmunizations() {
		return null;
	}

	public String htmEnrollmentImmunizations() {
		return enrollmentImmunizations == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentImmunizations());
	}

	public void inputEnrollmentImmunizations(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		if("Page".equals(classApiMethodMethod)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_enrollmentImmunizations")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classApiMethodMethod, "_enrollmentImmunizations");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			a("class", "setEnrollmentImmunizations inputSchoolEnrollment", pk, "EnrollmentImmunizations w3-input w3-border ");
			a("name", "setEnrollmentImmunizations");
		} else {
			a("class", "valueEnrollmentImmunizations inputSchoolEnrollment", pk, "EnrollmentImmunizations w3-input w3-border ");
			a("name", "enrollmentImmunizations");
		}
		if("Page".equals(classApiMethodMethod)) {
			a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentImmunizations', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_enrollmentImmunizations')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentImmunizations')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getEnrollmentImmunizations() != null && getEnrollmentImmunizations())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmEnrollmentImmunizations(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentImmunizations").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_enrollmentImmunizations").a("class", "").f().sx("immunized").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentImmunizations(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// familyMarried //
	///////////////////

	/**	L'entité « familyMarried »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean familyMarried;
	@JsonIgnore
	public Wrap<Boolean> familyMarriedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("familyMarried").o(familyMarried);

	/**	<br/>L'entité « familyMarried »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyMarried">Trouver l'entité familyMarried dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familyMarried(Wrap<Boolean> c);

	public Boolean getFamilyMarried() {
		return familyMarried;
	}

	public void setFamilyMarried(Boolean familyMarried) {
		this.familyMarried = familyMarried;
		this.familyMarriedWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setFamilyMarried(String o) {
		this.familyMarried = Boolean.parseBoolean(o);
		this.familyMarriedWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment familyMarriedInit() {
		if(!familyMarriedWrap.alreadyInitialized) {
			_familyMarried(familyMarriedWrap);
			if(familyMarried == null)
				setFamilyMarried(familyMarriedWrap.o);
		}
		familyMarriedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrFamilyMarried() {
		return familyMarried;
	}

	public String strFamilyMarried() {
		return familyMarried == null ? "" : familyMarried.toString();
	}

	public String jsonFamilyMarried() {
		return familyMarried == null ? "" : familyMarried.toString();
	}

	public String nomAffichageFamilyMarried() {
		return "parents married";
	}

	public String htmTooltipFamilyMarried() {
		return null;
	}

	public String htmFamilyMarried() {
		return familyMarried == null ? "" : StringEscapeUtils.escapeHtml4(strFamilyMarried());
	}

	public void inputFamilyMarried(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		if("Page".equals(classApiMethodMethod)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_familyMarried")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classApiMethodMethod, "_familyMarried");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			a("class", "setFamilyMarried inputSchoolEnrollment", pk, "FamilyMarried w3-input w3-border ");
			a("name", "setFamilyMarried");
		} else {
			a("class", "valueFamilyMarried inputSchoolEnrollment", pk, "FamilyMarried w3-input w3-border ");
			a("name", "familyMarried");
		}
		if("Page".equals(classApiMethodMethod)) {
			a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyMarried', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_familyMarried')); }, function() { addError($('#", classApiMethodMethod, "_familyMarried')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getFamilyMarried() != null && getFamilyMarried())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmFamilyMarried(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentFamilyMarried").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_familyMarried").a("class", "").f().sx("parents married").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilyMarried(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////
	// familySeparated //
	/////////////////////

	/**	L'entité « familySeparated »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean familySeparated;
	@JsonIgnore
	public Wrap<Boolean> familySeparatedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("familySeparated").o(familySeparated);

	/**	<br/>L'entité « familySeparated »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familySeparated">Trouver l'entité familySeparated dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familySeparated(Wrap<Boolean> c);

	public Boolean getFamilySeparated() {
		return familySeparated;
	}

	public void setFamilySeparated(Boolean familySeparated) {
		this.familySeparated = familySeparated;
		this.familySeparatedWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setFamilySeparated(String o) {
		this.familySeparated = Boolean.parseBoolean(o);
		this.familySeparatedWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment familySeparatedInit() {
		if(!familySeparatedWrap.alreadyInitialized) {
			_familySeparated(familySeparatedWrap);
			if(familySeparated == null)
				setFamilySeparated(familySeparatedWrap.o);
		}
		familySeparatedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrFamilySeparated() {
		return familySeparated;
	}

	public String strFamilySeparated() {
		return familySeparated == null ? "" : familySeparated.toString();
	}

	public String jsonFamilySeparated() {
		return familySeparated == null ? "" : familySeparated.toString();
	}

	public String nomAffichageFamilySeparated() {
		return "parents separated";
	}

	public String htmTooltipFamilySeparated() {
		return null;
	}

	public String htmFamilySeparated() {
		return familySeparated == null ? "" : StringEscapeUtils.escapeHtml4(strFamilySeparated());
	}

	public void inputFamilySeparated(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		if("Page".equals(classApiMethodMethod)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_familySeparated")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classApiMethodMethod, "_familySeparated");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			a("class", "setFamilySeparated inputSchoolEnrollment", pk, "FamilySeparated w3-input w3-border ");
			a("name", "setFamilySeparated");
		} else {
			a("class", "valueFamilySeparated inputSchoolEnrollment", pk, "FamilySeparated w3-input w3-border ");
			a("name", "familySeparated");
		}
		if("Page".equals(classApiMethodMethod)) {
			a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilySeparated', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_familySeparated')); }, function() { addError($('#", classApiMethodMethod, "_familySeparated')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getFamilySeparated() != null && getFamilySeparated())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmFamilySeparated(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentFamilySeparated").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_familySeparated").a("class", "").f().sx("parents separated").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilySeparated(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////
	// familyDivorced //
	////////////////////

	/**	L'entité « familyDivorced »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean familyDivorced;
	@JsonIgnore
	public Wrap<Boolean> familyDivorcedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("familyDivorced").o(familyDivorced);

	/**	<br/>L'entité « familyDivorced »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyDivorced">Trouver l'entité familyDivorced dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familyDivorced(Wrap<Boolean> c);

	public Boolean getFamilyDivorced() {
		return familyDivorced;
	}

	public void setFamilyDivorced(Boolean familyDivorced) {
		this.familyDivorced = familyDivorced;
		this.familyDivorcedWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setFamilyDivorced(String o) {
		this.familyDivorced = Boolean.parseBoolean(o);
		this.familyDivorcedWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment familyDivorcedInit() {
		if(!familyDivorcedWrap.alreadyInitialized) {
			_familyDivorced(familyDivorcedWrap);
			if(familyDivorced == null)
				setFamilyDivorced(familyDivorcedWrap.o);
		}
		familyDivorcedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrFamilyDivorced() {
		return familyDivorced;
	}

	public String strFamilyDivorced() {
		return familyDivorced == null ? "" : familyDivorced.toString();
	}

	public String jsonFamilyDivorced() {
		return familyDivorced == null ? "" : familyDivorced.toString();
	}

	public String nomAffichageFamilyDivorced() {
		return "parents divorced";
	}

	public String htmTooltipFamilyDivorced() {
		return null;
	}

	public String htmFamilyDivorced() {
		return familyDivorced == null ? "" : StringEscapeUtils.escapeHtml4(strFamilyDivorced());
	}

	public void inputFamilyDivorced(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		if("Page".equals(classApiMethodMethod)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_familyDivorced")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classApiMethodMethod, "_familyDivorced");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			a("class", "setFamilyDivorced inputSchoolEnrollment", pk, "FamilyDivorced w3-input w3-border ");
			a("name", "setFamilyDivorced");
		} else {
			a("class", "valueFamilyDivorced inputSchoolEnrollment", pk, "FamilyDivorced w3-input w3-border ");
			a("name", "familyDivorced");
		}
		if("Page".equals(classApiMethodMethod)) {
			a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyDivorced', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_familyDivorced')); }, function() { addError($('#", classApiMethodMethod, "_familyDivorced')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getFamilyDivorced() != null && getFamilyDivorced())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmFamilyDivorced(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentFamilyDivorced").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_familyDivorced").a("class", "").f().sx("parents divorced").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilyDivorced(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////
	// familyAddress //
	///////////////////

	/**	L'entité « familyAddress »
	 *	 is defined as null before being initialized. 
	 */
	protected String familyAddress;
	@JsonIgnore
	public Wrap<String> familyAddressWrap = new Wrap<String>().p(this).c(String.class).var("familyAddress").o(familyAddress);

	/**	<br/>L'entité « familyAddress »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyAddress">Trouver l'entité familyAddress dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familyAddress(Wrap<String> c);

	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
		this.familyAddressWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment familyAddressInit() {
		if(!familyAddressWrap.alreadyInitialized) {
			_familyAddress(familyAddressWrap);
			if(familyAddress == null)
				setFamilyAddress(familyAddressWrap.o);
		}
		familyAddressWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrFamilyAddress() {
		return familyAddress;
	}

	public String strFamilyAddress() {
		return familyAddress == null ? "" : familyAddress;
	}

	public String jsonFamilyAddress() {
		return familyAddress == null ? "" : familyAddress;
	}

	public String nomAffichageFamilyAddress() {
		return "family address";
	}

	public String htmTooltipFamilyAddress() {
		return null;
	}

	public String htmFamilyAddress() {
		return familyAddress == null ? "" : StringEscapeUtils.escapeHtml4(strFamilyAddress());
	}

	public void inputFamilyAddress(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("textarea")
			.a("placeholder", "family address")
			.a("id", classApiMethodMethod, "_familyAddress");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setFamilyAddress inputSchoolEnrollment", pk, "FamilyAddress w3-input w3-border ");
				a("name", "setFamilyAddress");
			} else {
				a("class", "valueFamilyAddress w3-input w3-border inputSchoolEnrollment", pk, "FamilyAddress w3-input w3-border ");
				a("name", "familyAddress");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyAddress', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_familyAddress')); }, function() { addError($('#", classApiMethodMethod, "_familyAddress')); }); ");
			}
		f().sx(strFamilyAddress()).g("textarea");

	}

	public void htmFamilyAddress(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentFamilyAddress").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_familyAddress").a("class", "").f().sx("family address").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilyAddress(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_familyAddress')); $('#", classApiMethodMethod, "_familyAddress').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setFamilyAddress', null, function() { addGlow($('#", classApiMethodMethod, "_familyAddress')); }, function() { addError($('#", classApiMethodMethod, "_familyAddress')); }); ")
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

	/////////////////////////////////
	// familyHowDoYouKnowTheSchool //
	/////////////////////////////////

	/**	L'entité « familyHowDoYouKnowTheSchool »
	 *	 is defined as null before being initialized. 
	 */
	protected String familyHowDoYouKnowTheSchool;
	@JsonIgnore
	public Wrap<String> familyHowDoYouKnowTheSchoolWrap = new Wrap<String>().p(this).c(String.class).var("familyHowDoYouKnowTheSchool").o(familyHowDoYouKnowTheSchool);

	/**	<br/>L'entité « familyHowDoYouKnowTheSchool »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyHowDoYouKnowTheSchool">Trouver l'entité familyHowDoYouKnowTheSchool dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familyHowDoYouKnowTheSchool(Wrap<String> c);

	public String getFamilyHowDoYouKnowTheSchool() {
		return familyHowDoYouKnowTheSchool;
	}

	public void setFamilyHowDoYouKnowTheSchool(String familyHowDoYouKnowTheSchool) {
		this.familyHowDoYouKnowTheSchool = familyHowDoYouKnowTheSchool;
		this.familyHowDoYouKnowTheSchoolWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment familyHowDoYouKnowTheSchoolInit() {
		if(!familyHowDoYouKnowTheSchoolWrap.alreadyInitialized) {
			_familyHowDoYouKnowTheSchool(familyHowDoYouKnowTheSchoolWrap);
			if(familyHowDoYouKnowTheSchool == null)
				setFamilyHowDoYouKnowTheSchool(familyHowDoYouKnowTheSchoolWrap.o);
		}
		familyHowDoYouKnowTheSchoolWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrFamilyHowDoYouKnowTheSchool() {
		return familyHowDoYouKnowTheSchool;
	}

	public String strFamilyHowDoYouKnowTheSchool() {
		return familyHowDoYouKnowTheSchool == null ? "" : familyHowDoYouKnowTheSchool;
	}

	public String jsonFamilyHowDoYouKnowTheSchool() {
		return familyHowDoYouKnowTheSchool == null ? "" : familyHowDoYouKnowTheSchool;
	}

	public String nomAffichageFamilyHowDoYouKnowTheSchool() {
		return "how do you know the school? ";
	}

	public String htmTooltipFamilyHowDoYouKnowTheSchool() {
		return null;
	}

	public String htmFamilyHowDoYouKnowTheSchool() {
		return familyHowDoYouKnowTheSchool == null ? "" : StringEscapeUtils.escapeHtml4(strFamilyHowDoYouKnowTheSchool());
	}

	public void inputFamilyHowDoYouKnowTheSchool(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("textarea")
			.a("placeholder", "how do you know the school? ")
			.a("id", classApiMethodMethod, "_familyHowDoYouKnowTheSchool");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setFamilyHowDoYouKnowTheSchool inputSchoolEnrollment", pk, "FamilyHowDoYouKnowTheSchool w3-input w3-border ");
				a("name", "setFamilyHowDoYouKnowTheSchool");
			} else {
				a("class", "valueFamilyHowDoYouKnowTheSchool w3-input w3-border inputSchoolEnrollment", pk, "FamilyHowDoYouKnowTheSchool w3-input w3-border ");
				a("name", "familyHowDoYouKnowTheSchool");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyHowDoYouKnowTheSchool', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool')); }, function() { addError($('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool')); }); ");
			}
		f().sx(strFamilyHowDoYouKnowTheSchool()).g("textarea");

	}

	public void htmFamilyHowDoYouKnowTheSchool(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentFamilyHowDoYouKnowTheSchool").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_familyHowDoYouKnowTheSchool").a("class", "").f().sx("how do you know the school? ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilyHowDoYouKnowTheSchool(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool')); $('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setFamilyHowDoYouKnowTheSchool', null, function() { addGlow($('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool')); }, function() { addError($('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool')); }); ")
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

	/////////////////////////////////////
	// enrollmentSpecialConsiderations //
	/////////////////////////////////////

	/**	L'entité « enrollmentSpecialConsiderations »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSpecialConsiderations;
	@JsonIgnore
	public Wrap<String> enrollmentSpecialConsiderationsWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSpecialConsiderations").o(enrollmentSpecialConsiderations);

	/**	<br/>L'entité « enrollmentSpecialConsiderations »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSpecialConsiderations">Trouver l'entité enrollmentSpecialConsiderations dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSpecialConsiderations(Wrap<String> c);

	public String getEnrollmentSpecialConsiderations() {
		return enrollmentSpecialConsiderations;
	}

	public void setEnrollmentSpecialConsiderations(String enrollmentSpecialConsiderations) {
		this.enrollmentSpecialConsiderations = enrollmentSpecialConsiderations;
		this.enrollmentSpecialConsiderationsWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSpecialConsiderationsInit() {
		if(!enrollmentSpecialConsiderationsWrap.alreadyInitialized) {
			_enrollmentSpecialConsiderations(enrollmentSpecialConsiderationsWrap);
			if(enrollmentSpecialConsiderations == null)
				setEnrollmentSpecialConsiderations(enrollmentSpecialConsiderationsWrap.o);
		}
		enrollmentSpecialConsiderationsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSpecialConsiderations() {
		return enrollmentSpecialConsiderations;
	}

	public String strEnrollmentSpecialConsiderations() {
		return enrollmentSpecialConsiderations == null ? "" : enrollmentSpecialConsiderations;
	}

	public String jsonEnrollmentSpecialConsiderations() {
		return enrollmentSpecialConsiderations == null ? "" : enrollmentSpecialConsiderations;
	}

	public String nomAffichageEnrollmentSpecialConsiderations() {
		return "special considerations";
	}

	public String htmTooltipEnrollmentSpecialConsiderations() {
		return null;
	}

	public String htmEnrollmentSpecialConsiderations() {
		return enrollmentSpecialConsiderations == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSpecialConsiderations());
	}

	public void inputEnrollmentSpecialConsiderations(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("textarea")
			.a("placeholder", "special considerations")
			.a("id", classApiMethodMethod, "_enrollmentSpecialConsiderations");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setEnrollmentSpecialConsiderations inputSchoolEnrollment", pk, "EnrollmentSpecialConsiderations w3-input w3-border ");
				a("name", "setEnrollmentSpecialConsiderations");
			} else {
				a("class", "valueEnrollmentSpecialConsiderations w3-input w3-border inputSchoolEnrollment", pk, "EnrollmentSpecialConsiderations w3-input w3-border ");
				a("name", "enrollmentSpecialConsiderations");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSpecialConsiderations', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_enrollmentSpecialConsiderations')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSpecialConsiderations')); }); ");
			}
		f().sx(strEnrollmentSpecialConsiderations()).g("textarea");

	}

	public void htmEnrollmentSpecialConsiderations(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentSpecialConsiderations").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_enrollmentSpecialConsiderations").a("class", "").f().sx("special considerations").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSpecialConsiderations(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSpecialConsiderations')); $('#", classApiMethodMethod, "_enrollmentSpecialConsiderations').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSpecialConsiderations', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSpecialConsiderations')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSpecialConsiderations')); }); ")
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

	////////////////////////////
	// childMedicalConditions //
	////////////////////////////

	/**	L'entité « childMedicalConditions »
	 *	 is defined as null before being initialized. 
	 */
	protected String childMedicalConditions;
	@JsonIgnore
	public Wrap<String> childMedicalConditionsWrap = new Wrap<String>().p(this).c(String.class).var("childMedicalConditions").o(childMedicalConditions);

	/**	<br/>L'entité « childMedicalConditions »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childMedicalConditions">Trouver l'entité childMedicalConditions dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _childMedicalConditions(Wrap<String> c);

	public String getChildMedicalConditions() {
		return childMedicalConditions;
	}

	public void setChildMedicalConditions(String childMedicalConditions) {
		this.childMedicalConditions = childMedicalConditions;
		this.childMedicalConditionsWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment childMedicalConditionsInit() {
		if(!childMedicalConditionsWrap.alreadyInitialized) {
			_childMedicalConditions(childMedicalConditionsWrap);
			if(childMedicalConditions == null)
				setChildMedicalConditions(childMedicalConditionsWrap.o);
		}
		childMedicalConditionsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrChildMedicalConditions() {
		return childMedicalConditions;
	}

	public String strChildMedicalConditions() {
		return childMedicalConditions == null ? "" : childMedicalConditions;
	}

	public String jsonChildMedicalConditions() {
		return childMedicalConditions == null ? "" : childMedicalConditions;
	}

	public String nomAffichageChildMedicalConditions() {
		return "medical conditions";
	}

	public String htmTooltipChildMedicalConditions() {
		return null;
	}

	public String htmChildMedicalConditions() {
		return childMedicalConditions == null ? "" : StringEscapeUtils.escapeHtml4(strChildMedicalConditions());
	}

	public void inputChildMedicalConditions(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("textarea")
			.a("placeholder", "medical conditions")
			.a("id", classApiMethodMethod, "_childMedicalConditions");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setChildMedicalConditions inputSchoolEnrollment", pk, "ChildMedicalConditions w3-input w3-border ");
				a("name", "setChildMedicalConditions");
			} else {
				a("class", "valueChildMedicalConditions w3-input w3-border inputSchoolEnrollment", pk, "ChildMedicalConditions w3-input w3-border ");
				a("name", "childMedicalConditions");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildMedicalConditions', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childMedicalConditions')); }, function() { addError($('#", classApiMethodMethod, "_childMedicalConditions')); }); ");
			}
		f().sx(strChildMedicalConditions()).g("textarea");

	}

	public void htmChildMedicalConditions(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentChildMedicalConditions").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_childMedicalConditions").a("class", "").f().sx("medical conditions").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildMedicalConditions(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childMedicalConditions')); $('#", classApiMethodMethod, "_childMedicalConditions').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildMedicalConditions', null, function() { addGlow($('#", classApiMethodMethod, "_childMedicalConditions')); }, function() { addError($('#", classApiMethodMethod, "_childMedicalConditions')); }); ")
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

	//////////////////////////////////
	// childPreviousSchoolsAttended //
	//////////////////////////////////

	/**	L'entité « childPreviousSchoolsAttended »
	 *	 is defined as null before being initialized. 
	 */
	protected String childPreviousSchoolsAttended;
	@JsonIgnore
	public Wrap<String> childPreviousSchoolsAttendedWrap = new Wrap<String>().p(this).c(String.class).var("childPreviousSchoolsAttended").o(childPreviousSchoolsAttended);

	/**	<br/>L'entité « childPreviousSchoolsAttended »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childPreviousSchoolsAttended">Trouver l'entité childPreviousSchoolsAttended dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _childPreviousSchoolsAttended(Wrap<String> c);

	public String getChildPreviousSchoolsAttended() {
		return childPreviousSchoolsAttended;
	}

	public void setChildPreviousSchoolsAttended(String childPreviousSchoolsAttended) {
		this.childPreviousSchoolsAttended = childPreviousSchoolsAttended;
		this.childPreviousSchoolsAttendedWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment childPreviousSchoolsAttendedInit() {
		if(!childPreviousSchoolsAttendedWrap.alreadyInitialized) {
			_childPreviousSchoolsAttended(childPreviousSchoolsAttendedWrap);
			if(childPreviousSchoolsAttended == null)
				setChildPreviousSchoolsAttended(childPreviousSchoolsAttendedWrap.o);
		}
		childPreviousSchoolsAttendedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrChildPreviousSchoolsAttended() {
		return childPreviousSchoolsAttended;
	}

	public String strChildPreviousSchoolsAttended() {
		return childPreviousSchoolsAttended == null ? "" : childPreviousSchoolsAttended;
	}

	public String jsonChildPreviousSchoolsAttended() {
		return childPreviousSchoolsAttended == null ? "" : childPreviousSchoolsAttended;
	}

	public String nomAffichageChildPreviousSchoolsAttended() {
		return "schools previously attended";
	}

	public String htmTooltipChildPreviousSchoolsAttended() {
		return null;
	}

	public String htmChildPreviousSchoolsAttended() {
		return childPreviousSchoolsAttended == null ? "" : StringEscapeUtils.escapeHtml4(strChildPreviousSchoolsAttended());
	}

	public void inputChildPreviousSchoolsAttended(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("textarea")
			.a("placeholder", "schools previously attended")
			.a("id", classApiMethodMethod, "_childPreviousSchoolsAttended");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setChildPreviousSchoolsAttended inputSchoolEnrollment", pk, "ChildPreviousSchoolsAttended w3-input w3-border ");
				a("name", "setChildPreviousSchoolsAttended");
			} else {
				a("class", "valueChildPreviousSchoolsAttended w3-input w3-border inputSchoolEnrollment", pk, "ChildPreviousSchoolsAttended w3-input w3-border ");
				a("name", "childPreviousSchoolsAttended");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildPreviousSchoolsAttended', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childPreviousSchoolsAttended')); }, function() { addError($('#", classApiMethodMethod, "_childPreviousSchoolsAttended')); }); ");
			}
		f().sx(strChildPreviousSchoolsAttended()).g("textarea");

	}

	public void htmChildPreviousSchoolsAttended(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentChildPreviousSchoolsAttended").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_childPreviousSchoolsAttended").a("class", "").f().sx("schools previously attended").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildPreviousSchoolsAttended(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childPreviousSchoolsAttended')); $('#", classApiMethodMethod, "_childPreviousSchoolsAttended').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildPreviousSchoolsAttended', null, function() { addGlow($('#", classApiMethodMethod, "_childPreviousSchoolsAttended')); }, function() { addError($('#", classApiMethodMethod, "_childPreviousSchoolsAttended')); }); ")
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

	//////////////////////
	// childDescription //
	//////////////////////

	/**	L'entité « childDescription »
	 *	 is defined as null before being initialized. 
	 */
	protected String childDescription;
	@JsonIgnore
	public Wrap<String> childDescriptionWrap = new Wrap<String>().p(this).c(String.class).var("childDescription").o(childDescription);

	/**	<br/>L'entité « childDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childDescription">Trouver l'entité childDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _childDescription(Wrap<String> c);

	public String getChildDescription() {
		return childDescription;
	}

	public void setChildDescription(String childDescription) {
		this.childDescription = childDescription;
		this.childDescriptionWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment childDescriptionInit() {
		if(!childDescriptionWrap.alreadyInitialized) {
			_childDescription(childDescriptionWrap);
			if(childDescription == null)
				setChildDescription(childDescriptionWrap.o);
		}
		childDescriptionWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrChildDescription() {
		return childDescription;
	}

	public String strChildDescription() {
		return childDescription == null ? "" : childDescription;
	}

	public String jsonChildDescription() {
		return childDescription == null ? "" : childDescription;
	}

	public String nomAffichageChildDescription() {
		return "description";
	}

	public String htmTooltipChildDescription() {
		return null;
	}

	public String htmChildDescription() {
		return childDescription == null ? "" : StringEscapeUtils.escapeHtml4(strChildDescription());
	}

	public void inputChildDescription(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("textarea")
			.a("placeholder", "description")
			.a("id", classApiMethodMethod, "_childDescription");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setChildDescription inputSchoolEnrollment", pk, "ChildDescription w3-input w3-border ");
				a("name", "setChildDescription");
			} else {
				a("class", "valueChildDescription w3-input w3-border inputSchoolEnrollment", pk, "ChildDescription w3-input w3-border ");
				a("name", "childDescription");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildDescription', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childDescription')); }, function() { addError($('#", classApiMethodMethod, "_childDescription')); }); ");
			}
		f().sx(strChildDescription()).g("textarea");

	}

	public void htmChildDescription(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentChildDescription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_childDescription").a("class", "").f().sx("description").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildDescription(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childDescription')); $('#", classApiMethodMethod, "_childDescription').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildDescription', null, function() { addGlow($('#", classApiMethodMethod, "_childDescription')); }, function() { addError($('#", classApiMethodMethod, "_childDescription')); }); ")
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

	/////////////////////
	// childObjectives //
	/////////////////////

	/**	L'entité « childObjectives »
	 *	 is defined as null before being initialized. 
	 */
	protected String childObjectives;
	@JsonIgnore
	public Wrap<String> childObjectivesWrap = new Wrap<String>().p(this).c(String.class).var("childObjectives").o(childObjectives);

	/**	<br/>L'entité « childObjectives »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childObjectives">Trouver l'entité childObjectives dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _childObjectives(Wrap<String> c);

	public String getChildObjectives() {
		return childObjectives;
	}

	public void setChildObjectives(String childObjectives) {
		this.childObjectives = childObjectives;
		this.childObjectivesWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment childObjectivesInit() {
		if(!childObjectivesWrap.alreadyInitialized) {
			_childObjectives(childObjectivesWrap);
			if(childObjectives == null)
				setChildObjectives(childObjectivesWrap.o);
		}
		childObjectivesWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrChildObjectives() {
		return childObjectives;
	}

	public String strChildObjectives() {
		return childObjectives == null ? "" : childObjectives;
	}

	public String jsonChildObjectives() {
		return childObjectives == null ? "" : childObjectives;
	}

	public String nomAffichageChildObjectives() {
		return "objectives";
	}

	public String htmTooltipChildObjectives() {
		return null;
	}

	public String htmChildObjectives() {
		return childObjectives == null ? "" : StringEscapeUtils.escapeHtml4(strChildObjectives());
	}

	public void inputChildObjectives(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("textarea")
			.a("placeholder", "objectives")
			.a("id", classApiMethodMethod, "_childObjectives");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setChildObjectives inputSchoolEnrollment", pk, "ChildObjectives w3-input w3-border ");
				a("name", "setChildObjectives");
			} else {
				a("class", "valueChildObjectives w3-input w3-border inputSchoolEnrollment", pk, "ChildObjectives w3-input w3-border ");
				a("name", "childObjectives");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildObjectives', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childObjectives')); }, function() { addError($('#", classApiMethodMethod, "_childObjectives')); }); ");
			}
		f().sx(strChildObjectives()).g("textarea");

	}

	public void htmChildObjectives(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentChildObjectives").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_childObjectives").a("class", "").f().sx("objectives").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildObjectives(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childObjectives')); $('#", classApiMethodMethod, "_childObjectives').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildObjectives', null, function() { addGlow($('#", classApiMethodMethod, "_childObjectives')); }, function() { addError($('#", classApiMethodMethod, "_childObjectives')); }); ")
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

	///////////////////////
	// childPottyTrained //
	///////////////////////

	/**	L'entité « childPottyTrained »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean childPottyTrained;
	@JsonIgnore
	public Wrap<Boolean> childPottyTrainedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("childPottyTrained").o(childPottyTrained);

	/**	<br/>L'entité « childPottyTrained »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childPottyTrained">Trouver l'entité childPottyTrained dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _childPottyTrained(Wrap<Boolean> c);

	public Boolean getChildPottyTrained() {
		return childPottyTrained;
	}

	public void setChildPottyTrained(Boolean childPottyTrained) {
		this.childPottyTrained = childPottyTrained;
		this.childPottyTrainedWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setChildPottyTrained(String o) {
		this.childPottyTrained = Boolean.parseBoolean(o);
		this.childPottyTrainedWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment childPottyTrainedInit() {
		if(!childPottyTrainedWrap.alreadyInitialized) {
			_childPottyTrained(childPottyTrainedWrap);
			if(childPottyTrained == null)
				setChildPottyTrained(childPottyTrainedWrap.o);
		}
		childPottyTrainedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrChildPottyTrained() {
		return childPottyTrained;
	}

	public String strChildPottyTrained() {
		return childPottyTrained == null ? "" : childPottyTrained.toString();
	}

	public String jsonChildPottyTrained() {
		return childPottyTrained == null ? "" : childPottyTrained.toString();
	}

	public String nomAffichageChildPottyTrained() {
		return "potty trained";
	}

	public String htmTooltipChildPottyTrained() {
		return null;
	}

	public String htmChildPottyTrained() {
		return childPottyTrained == null ? "" : StringEscapeUtils.escapeHtml4(strChildPottyTrained());
	}

	public void inputChildPottyTrained(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		if("Page".equals(classApiMethodMethod)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_childPottyTrained")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classApiMethodMethod, "_childPottyTrained");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			a("class", "setChildPottyTrained inputSchoolEnrollment", pk, "ChildPottyTrained w3-input w3-border ");
			a("name", "setChildPottyTrained");
		} else {
			a("class", "valueChildPottyTrained inputSchoolEnrollment", pk, "ChildPottyTrained w3-input w3-border ");
			a("name", "childPottyTrained");
		}
		if("Page".equals(classApiMethodMethod)) {
			a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildPottyTrained', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_childPottyTrained')); }, function() { addError($('#", classApiMethodMethod, "_childPottyTrained')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getChildPottyTrained() != null && getChildPottyTrained())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmChildPottyTrained(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentChildPottyTrained").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_childPottyTrained").a("class", "").f().sx("potty trained").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildPottyTrained(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////
	// enrollmentGroupName //
	/////////////////////////

	/**	L'entité « enrollmentGroupName »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentGroupName;
	@JsonIgnore
	public Wrap<String> enrollmentGroupNameWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentGroupName").o(enrollmentGroupName);

	/**	<br/>L'entité « enrollmentGroupName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentGroupName">Trouver l'entité enrollmentGroupName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentGroupName(Wrap<String> c);

	public String getEnrollmentGroupName() {
		return enrollmentGroupName;
	}

	public void setEnrollmentGroupName(String enrollmentGroupName) {
		this.enrollmentGroupName = enrollmentGroupName;
		this.enrollmentGroupNameWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentGroupNameInit() {
		if(!enrollmentGroupNameWrap.alreadyInitialized) {
			_enrollmentGroupName(enrollmentGroupNameWrap);
			if(enrollmentGroupName == null)
				setEnrollmentGroupName(enrollmentGroupNameWrap.o);
		}
		enrollmentGroupNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentGroupName() {
		return enrollmentGroupName;
	}

	public String strEnrollmentGroupName() {
		return enrollmentGroupName == null ? "" : enrollmentGroupName;
	}

	public String jsonEnrollmentGroupName() {
		return enrollmentGroupName == null ? "" : enrollmentGroupName;
	}

	public String nomAffichageEnrollmentGroupName() {
		return "group name";
	}

	public String htmTooltipEnrollmentGroupName() {
		return null;
	}

	public String htmEnrollmentGroupName() {
		return enrollmentGroupName == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentGroupName());
	}

	public void inputEnrollmentGroupName(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "group name")
			.a("id", classApiMethodMethod, "_enrollmentGroupName");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setEnrollmentGroupName inputSchoolEnrollment", pk, "EnrollmentGroupName w3-input w3-border ");
				a("name", "setEnrollmentGroupName");
			} else {
				a("class", "valueEnrollmentGroupName w3-input w3-border inputSchoolEnrollment", pk, "EnrollmentGroupName w3-input w3-border ");
				a("name", "enrollmentGroupName");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentGroupName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_enrollmentGroupName')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentGroupName')); }); ");
			}
			a("value", strEnrollmentGroupName())
		.fg();

	}

	public void htmEnrollmentGroupName(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentGroupName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_enrollmentGroupName").a("class", "").f().sx("group name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentGroupName(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentGroupName')); $('#", classApiMethodMethod, "_enrollmentGroupName').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentGroupName', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentGroupName')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentGroupName')); }); ")
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

	////////////////////////////////
	// enrollmentPaymentEachMonth //
	////////////////////////////////

	/**	L'entité « enrollmentPaymentEachMonth »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean enrollmentPaymentEachMonth;
	@JsonIgnore
	public Wrap<Boolean> enrollmentPaymentEachMonthWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentPaymentEachMonth").o(enrollmentPaymentEachMonth);

	/**	<br/>L'entité « enrollmentPaymentEachMonth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentPaymentEachMonth">Trouver l'entité enrollmentPaymentEachMonth dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentPaymentEachMonth(Wrap<Boolean> c);

	public Boolean getEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth;
	}

	public void setEnrollmentPaymentEachMonth(Boolean enrollmentPaymentEachMonth) {
		this.enrollmentPaymentEachMonth = enrollmentPaymentEachMonth;
		this.enrollmentPaymentEachMonthWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentPaymentEachMonth(String o) {
		this.enrollmentPaymentEachMonth = Boolean.parseBoolean(o);
		this.enrollmentPaymentEachMonthWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentPaymentEachMonthInit() {
		if(!enrollmentPaymentEachMonthWrap.alreadyInitialized) {
			_enrollmentPaymentEachMonth(enrollmentPaymentEachMonthWrap);
			if(enrollmentPaymentEachMonth == null)
				setEnrollmentPaymentEachMonth(enrollmentPaymentEachMonthWrap.o);
		}
		enrollmentPaymentEachMonthWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth;
	}

	public String strEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth == null ? "" : enrollmentPaymentEachMonth.toString();
	}

	public String jsonEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth == null ? "" : enrollmentPaymentEachMonth.toString();
	}

	public String nomAffichageEnrollmentPaymentEachMonth() {
		return "payment each month";
	}

	public String htmTooltipEnrollmentPaymentEachMonth() {
		return null;
	}

	public String htmEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentPaymentEachMonth());
	}

	public void inputEnrollmentPaymentEachMonth(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		if("Page".equals(classApiMethodMethod)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_enrollmentPaymentEachMonth")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classApiMethodMethod, "_enrollmentPaymentEachMonth");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			a("class", "setEnrollmentPaymentEachMonth inputSchoolEnrollment", pk, "EnrollmentPaymentEachMonth w3-input w3-border ");
			a("name", "setEnrollmentPaymentEachMonth");
		} else {
			a("class", "valueEnrollmentPaymentEachMonth inputSchoolEnrollment", pk, "EnrollmentPaymentEachMonth w3-input w3-border ");
			a("name", "enrollmentPaymentEachMonth");
		}
		if("Page".equals(classApiMethodMethod)) {
			a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentPaymentEachMonth', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_enrollmentPaymentEachMonth')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentPaymentEachMonth')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getEnrollmentPaymentEachMonth() != null && getEnrollmentPaymentEachMonth())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmEnrollmentPaymentEachMonth(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentPaymentEachMonth").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_enrollmentPaymentEachMonth").a("class", "").f().sx("payment each month").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentPaymentEachMonth(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////////
	// enrollmentPaymentComplete //
	///////////////////////////////

	/**	L'entité « enrollmentPaymentComplete »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean enrollmentPaymentComplete;
	@JsonIgnore
	public Wrap<Boolean> enrollmentPaymentCompleteWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentPaymentComplete").o(enrollmentPaymentComplete);

	/**	<br/>L'entité « enrollmentPaymentComplete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentPaymentComplete">Trouver l'entité enrollmentPaymentComplete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentPaymentComplete(Wrap<Boolean> c);

	public Boolean getEnrollmentPaymentComplete() {
		return enrollmentPaymentComplete;
	}

	public void setEnrollmentPaymentComplete(Boolean enrollmentPaymentComplete) {
		this.enrollmentPaymentComplete = enrollmentPaymentComplete;
		this.enrollmentPaymentCompleteWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentPaymentComplete(String o) {
		this.enrollmentPaymentComplete = Boolean.parseBoolean(o);
		this.enrollmentPaymentCompleteWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentPaymentCompleteInit() {
		if(!enrollmentPaymentCompleteWrap.alreadyInitialized) {
			_enrollmentPaymentComplete(enrollmentPaymentCompleteWrap);
			if(enrollmentPaymentComplete == null)
				setEnrollmentPaymentComplete(enrollmentPaymentCompleteWrap.o);
		}
		enrollmentPaymentCompleteWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Boolean solrEnrollmentPaymentComplete() {
		return enrollmentPaymentComplete;
	}

	public String strEnrollmentPaymentComplete() {
		return enrollmentPaymentComplete == null ? "" : enrollmentPaymentComplete.toString();
	}

	public String jsonEnrollmentPaymentComplete() {
		return enrollmentPaymentComplete == null ? "" : enrollmentPaymentComplete.toString();
	}

	public String nomAffichageEnrollmentPaymentComplete() {
		return "complete payment";
	}

	public String htmTooltipEnrollmentPaymentComplete() {
		return null;
	}

	public String htmEnrollmentPaymentComplete() {
		return enrollmentPaymentComplete == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentPaymentComplete());
	}

	public void inputEnrollmentPaymentComplete(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		if("Page".equals(classApiMethodMethod)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classApiMethodMethod, "_enrollmentPaymentComplete")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classApiMethodMethod, "_enrollmentPaymentComplete");
		}
		if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
			a("class", "setEnrollmentPaymentComplete inputSchoolEnrollment", pk, "EnrollmentPaymentComplete w3-input w3-border ");
			a("name", "setEnrollmentPaymentComplete");
		} else {
			a("class", "valueEnrollmentPaymentComplete inputSchoolEnrollment", pk, "EnrollmentPaymentComplete w3-input w3-border ");
			a("name", "enrollmentPaymentComplete");
		}
		if("Page".equals(classApiMethodMethod)) {
			a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentPaymentComplete', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_enrollmentPaymentComplete')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentPaymentComplete')); }); ");
		}
		if("Page".equals(classApiMethodMethod)) {
			if(getEnrollmentPaymentComplete() != null && getEnrollmentPaymentComplete())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmEnrollmentPaymentComplete(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentPaymentComplete").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classApiMethodMethod, "_enrollmentPaymentComplete").a("class", "").f().sx("complete payment").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentPaymentComplete(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////
	// enrollmentParentNames //
	///////////////////////////

	/**	L'entité « enrollmentParentNames »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentParentNames;
	@JsonIgnore
	public Wrap<String> enrollmentParentNamesWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentParentNames").o(enrollmentParentNames);

	/**	<br/>L'entité « enrollmentParentNames »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentParentNames">Trouver l'entité enrollmentParentNames dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentParentNames(Wrap<String> c);

	public String getEnrollmentParentNames() {
		return enrollmentParentNames;
	}

	public void setEnrollmentParentNames(String enrollmentParentNames) {
		this.enrollmentParentNames = enrollmentParentNames;
		this.enrollmentParentNamesWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentParentNamesInit() {
		if(!enrollmentParentNamesWrap.alreadyInitialized) {
			_enrollmentParentNames(enrollmentParentNamesWrap);
			if(enrollmentParentNames == null)
				setEnrollmentParentNames(enrollmentParentNamesWrap.o);
		}
		enrollmentParentNamesWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentParentNames() {
		return enrollmentParentNames;
	}

	public String strEnrollmentParentNames() {
		return enrollmentParentNames == null ? "" : enrollmentParentNames;
	}

	public String jsonEnrollmentParentNames() {
		return enrollmentParentNames == null ? "" : enrollmentParentNames;
	}

	public String nomAffichageEnrollmentParentNames() {
		return null;
	}

	public String htmTooltipEnrollmentParentNames() {
		return null;
	}

	public String htmEnrollmentParentNames() {
		return enrollmentParentNames == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentParentNames());
	}

	public void inputEnrollmentParentNames(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("id", classApiMethodMethod, "_enrollmentParentNames");
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setEnrollmentParentNames inputSchoolEnrollment", pk, "EnrollmentParentNames w3-input w3-border ");
				a("name", "setEnrollmentParentNames");
			} else {
				a("class", "valueEnrollmentParentNames w3-input w3-border inputSchoolEnrollment", pk, "EnrollmentParentNames w3-input w3-border ");
				a("name", "enrollmentParentNames");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentParentNames', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_enrollmentParentNames')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentParentNames')); }); ");
			}
			a("value", strEnrollmentParentNames())
		.fg();

	}

	public void htmEnrollmentParentNames(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentParentNames").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentParentNames(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentParentNames')); $('#", classApiMethodMethod, "_enrollmentParentNames').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentParentNames', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentParentNames')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentParentNames')); }); ")
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

	//////////////////////////
	// enrollmentSignature1 //
	//////////////////////////

	/**	L'entité « enrollmentSignature1 »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSignature1;
	@JsonIgnore
	public Wrap<String> enrollmentSignature1Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature1").o(enrollmentSignature1);

	/**	<br/>L'entité « enrollmentSignature1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature1">Trouver l'entité enrollmentSignature1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSignature1(Wrap<String> c);

	public String getEnrollmentSignature1() {
		return enrollmentSignature1;
	}

	public void setEnrollmentSignature1(String enrollmentSignature1) {
		this.enrollmentSignature1 = enrollmentSignature1;
		this.enrollmentSignature1Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSignature1Init() {
		if(!enrollmentSignature1Wrap.alreadyInitialized) {
			_enrollmentSignature1(enrollmentSignature1Wrap);
			if(enrollmentSignature1 == null)
				setEnrollmentSignature1(enrollmentSignature1Wrap.o);
		}
		enrollmentSignature1Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSignature1() {
		return enrollmentSignature1;
	}

	public String strEnrollmentSignature1() {
		return enrollmentSignature1 == null ? "" : enrollmentSignature1;
	}

	public String jsonEnrollmentSignature1() {
		return enrollmentSignature1 == null ? "" : enrollmentSignature1;
	}

	public String nomAffichageEnrollmentSignature1() {
		return null;
	}

	public String htmTooltipEnrollmentSignature1() {
		return null;
	}

	public String htmEnrollmentSignature1() {
		return enrollmentSignature1 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSignature1());
	}

	public void inputEnrollmentSignature1(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("div").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature1").f();
			e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature1");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(enrollmentSignature1) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature1");
				a("src", StringUtils.isBlank(enrollmentSignature1) ? "data:image/png;base64," : enrollmentSignature1).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature1) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature1").f();
			e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature1");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature1').show(); ");
					s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature1').hide(); ");
					s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature1')); ");
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature1', null); ");
					s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature1')) { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature1').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature1').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Clear");
			g("button");
			e("button").a("id", "signatureButtonAcceptSchoolEnrollment", pk, "enrollmentSignature1");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputSchoolEnrollment", pk, "enrollmentSignature1').jSignature('getData', 'default'); "); 
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature1', src); ");
				s("\"");
				f().sx("Accept the signature");
			g("button");
		g("div");
	}

	public void htmEnrollmentSignature1(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentSignature1").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature1(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature1')); $('#", classApiMethodMethod, "_enrollmentSignature1').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature1', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature1')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature1')); }); ")
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

	//////////////////////////
	// enrollmentSignature2 //
	//////////////////////////

	/**	L'entité « enrollmentSignature2 »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSignature2;
	@JsonIgnore
	public Wrap<String> enrollmentSignature2Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature2").o(enrollmentSignature2);

	/**	<br/>L'entité « enrollmentSignature2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature2">Trouver l'entité enrollmentSignature2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSignature2(Wrap<String> c);

	public String getEnrollmentSignature2() {
		return enrollmentSignature2;
	}

	public void setEnrollmentSignature2(String enrollmentSignature2) {
		this.enrollmentSignature2 = enrollmentSignature2;
		this.enrollmentSignature2Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSignature2Init() {
		if(!enrollmentSignature2Wrap.alreadyInitialized) {
			_enrollmentSignature2(enrollmentSignature2Wrap);
			if(enrollmentSignature2 == null)
				setEnrollmentSignature2(enrollmentSignature2Wrap.o);
		}
		enrollmentSignature2Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSignature2() {
		return enrollmentSignature2;
	}

	public String strEnrollmentSignature2() {
		return enrollmentSignature2 == null ? "" : enrollmentSignature2;
	}

	public String jsonEnrollmentSignature2() {
		return enrollmentSignature2 == null ? "" : enrollmentSignature2;
	}

	public String nomAffichageEnrollmentSignature2() {
		return null;
	}

	public String htmTooltipEnrollmentSignature2() {
		return null;
	}

	public String htmEnrollmentSignature2() {
		return enrollmentSignature2 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSignature2());
	}

	public void inputEnrollmentSignature2(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("div").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature2").f();
			e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature2");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(enrollmentSignature2) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature2");
				a("src", StringUtils.isBlank(enrollmentSignature2) ? "data:image/png;base64," : enrollmentSignature2).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature2) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature2").f();
			e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature2");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature2').show(); ");
					s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature2').hide(); ");
					s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature2')); ");
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature2', null); ");
					s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature2')) { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature2').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature2').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Clear");
			g("button");
			e("button").a("id", "signatureButtonAcceptSchoolEnrollment", pk, "enrollmentSignature2");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputSchoolEnrollment", pk, "enrollmentSignature2').jSignature('getData', 'default'); "); 
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature2', src); ");
				s("\"");
				f().sx("Accept the signature");
			g("button");
		g("div");
	}

	public void htmEnrollmentSignature2(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentSignature2").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature2(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature2')); $('#", classApiMethodMethod, "_enrollmentSignature2').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature2', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature2')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature2')); }); ")
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

	//////////////////////////
	// enrollmentSignature3 //
	//////////////////////////

	/**	L'entité « enrollmentSignature3 »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSignature3;
	@JsonIgnore
	public Wrap<String> enrollmentSignature3Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature3").o(enrollmentSignature3);

	/**	<br/>L'entité « enrollmentSignature3 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature3">Trouver l'entité enrollmentSignature3 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSignature3(Wrap<String> c);

	public String getEnrollmentSignature3() {
		return enrollmentSignature3;
	}

	public void setEnrollmentSignature3(String enrollmentSignature3) {
		this.enrollmentSignature3 = enrollmentSignature3;
		this.enrollmentSignature3Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSignature3Init() {
		if(!enrollmentSignature3Wrap.alreadyInitialized) {
			_enrollmentSignature3(enrollmentSignature3Wrap);
			if(enrollmentSignature3 == null)
				setEnrollmentSignature3(enrollmentSignature3Wrap.o);
		}
		enrollmentSignature3Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSignature3() {
		return enrollmentSignature3;
	}

	public String strEnrollmentSignature3() {
		return enrollmentSignature3 == null ? "" : enrollmentSignature3;
	}

	public String jsonEnrollmentSignature3() {
		return enrollmentSignature3 == null ? "" : enrollmentSignature3;
	}

	public String nomAffichageEnrollmentSignature3() {
		return null;
	}

	public String htmTooltipEnrollmentSignature3() {
		return null;
	}

	public String htmEnrollmentSignature3() {
		return enrollmentSignature3 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSignature3());
	}

	public void inputEnrollmentSignature3(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("div").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature3").f();
			e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature3");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(enrollmentSignature3) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature3");
				a("src", StringUtils.isBlank(enrollmentSignature3) ? "data:image/png;base64," : enrollmentSignature3).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature3) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature3").f();
			e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature3");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature3').show(); ");
					s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature3').hide(); ");
					s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature3')); ");
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature3', null); ");
					s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature3')) { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature3').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature3').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Clear");
			g("button");
			e("button").a("id", "signatureButtonAcceptSchoolEnrollment", pk, "enrollmentSignature3");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputSchoolEnrollment", pk, "enrollmentSignature3').jSignature('getData', 'default'); "); 
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature3', src); ");
				s("\"");
				f().sx("Accept the signature");
			g("button");
		g("div");
	}

	public void htmEnrollmentSignature3(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentSignature3").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature3(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature3')); $('#", classApiMethodMethod, "_enrollmentSignature3').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature3', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature3')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature3')); }); ")
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

	//////////////////////////
	// enrollmentSignature4 //
	//////////////////////////

	/**	L'entité « enrollmentSignature4 »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSignature4;
	@JsonIgnore
	public Wrap<String> enrollmentSignature4Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature4").o(enrollmentSignature4);

	/**	<br/>L'entité « enrollmentSignature4 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature4">Trouver l'entité enrollmentSignature4 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSignature4(Wrap<String> c);

	public String getEnrollmentSignature4() {
		return enrollmentSignature4;
	}

	public void setEnrollmentSignature4(String enrollmentSignature4) {
		this.enrollmentSignature4 = enrollmentSignature4;
		this.enrollmentSignature4Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSignature4Init() {
		if(!enrollmentSignature4Wrap.alreadyInitialized) {
			_enrollmentSignature4(enrollmentSignature4Wrap);
			if(enrollmentSignature4 == null)
				setEnrollmentSignature4(enrollmentSignature4Wrap.o);
		}
		enrollmentSignature4Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSignature4() {
		return enrollmentSignature4;
	}

	public String strEnrollmentSignature4() {
		return enrollmentSignature4 == null ? "" : enrollmentSignature4;
	}

	public String jsonEnrollmentSignature4() {
		return enrollmentSignature4 == null ? "" : enrollmentSignature4;
	}

	public String nomAffichageEnrollmentSignature4() {
		return null;
	}

	public String htmTooltipEnrollmentSignature4() {
		return null;
	}

	public String htmEnrollmentSignature4() {
		return enrollmentSignature4 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSignature4());
	}

	public void inputEnrollmentSignature4(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("div").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature4").f();
			e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature4");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(enrollmentSignature4) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature4");
				a("src", StringUtils.isBlank(enrollmentSignature4) ? "data:image/png;base64," : enrollmentSignature4).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature4) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature4").f();
			e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature4");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature4').show(); ");
					s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature4').hide(); ");
					s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature4')); ");
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature4', null); ");
					s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature4')) { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature4').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature4').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Clear");
			g("button");
			e("button").a("id", "signatureButtonAcceptSchoolEnrollment", pk, "enrollmentSignature4");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputSchoolEnrollment", pk, "enrollmentSignature4').jSignature('getData', 'default'); "); 
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature4', src); ");
				s("\"");
				f().sx("Accept the signature");
			g("button");
		g("div");
	}

	public void htmEnrollmentSignature4(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentSignature4").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature4(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature4')); $('#", classApiMethodMethod, "_enrollmentSignature4').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature4', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature4')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature4')); }); ")
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

	//////////////////////////
	// enrollmentSignature5 //
	//////////////////////////

	/**	L'entité « enrollmentSignature5 »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSignature5;
	@JsonIgnore
	public Wrap<String> enrollmentSignature5Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature5").o(enrollmentSignature5);

	/**	<br/>L'entité « enrollmentSignature5 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature5">Trouver l'entité enrollmentSignature5 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSignature5(Wrap<String> c);

	public String getEnrollmentSignature5() {
		return enrollmentSignature5;
	}

	public void setEnrollmentSignature5(String enrollmentSignature5) {
		this.enrollmentSignature5 = enrollmentSignature5;
		this.enrollmentSignature5Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSignature5Init() {
		if(!enrollmentSignature5Wrap.alreadyInitialized) {
			_enrollmentSignature5(enrollmentSignature5Wrap);
			if(enrollmentSignature5 == null)
				setEnrollmentSignature5(enrollmentSignature5Wrap.o);
		}
		enrollmentSignature5Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSignature5() {
		return enrollmentSignature5;
	}

	public String strEnrollmentSignature5() {
		return enrollmentSignature5 == null ? "" : enrollmentSignature5;
	}

	public String jsonEnrollmentSignature5() {
		return enrollmentSignature5 == null ? "" : enrollmentSignature5;
	}

	public String nomAffichageEnrollmentSignature5() {
		return null;
	}

	public String htmTooltipEnrollmentSignature5() {
		return null;
	}

	public String htmEnrollmentSignature5() {
		return enrollmentSignature5 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSignature5());
	}

	public void inputEnrollmentSignature5(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("div").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature5").f();
			e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature5");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(enrollmentSignature5) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature5");
				a("src", StringUtils.isBlank(enrollmentSignature5) ? "data:image/png;base64," : enrollmentSignature5).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature5) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature5").f();
			e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature5");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature5').show(); ");
					s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature5').hide(); ");
					s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature5')); ");
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature5', null); ");
					s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature5')) { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature5').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature5').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Clear");
			g("button");
			e("button").a("id", "signatureButtonAcceptSchoolEnrollment", pk, "enrollmentSignature5");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputSchoolEnrollment", pk, "enrollmentSignature5').jSignature('getData', 'default'); "); 
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature5', src); ");
				s("\"");
				f().sx("Accept the signature");
			g("button");
		g("div");
	}

	public void htmEnrollmentSignature5(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentSignature5").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature5(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature5')); $('#", classApiMethodMethod, "_enrollmentSignature5').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature5', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature5')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature5')); }); ")
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

	//////////////////////////
	// enrollmentSignature6 //
	//////////////////////////

	/**	L'entité « enrollmentSignature6 »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSignature6;
	@JsonIgnore
	public Wrap<String> enrollmentSignature6Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature6").o(enrollmentSignature6);

	/**	<br/>L'entité « enrollmentSignature6 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature6">Trouver l'entité enrollmentSignature6 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSignature6(Wrap<String> c);

	public String getEnrollmentSignature6() {
		return enrollmentSignature6;
	}

	public void setEnrollmentSignature6(String enrollmentSignature6) {
		this.enrollmentSignature6 = enrollmentSignature6;
		this.enrollmentSignature6Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSignature6Init() {
		if(!enrollmentSignature6Wrap.alreadyInitialized) {
			_enrollmentSignature6(enrollmentSignature6Wrap);
			if(enrollmentSignature6 == null)
				setEnrollmentSignature6(enrollmentSignature6Wrap.o);
		}
		enrollmentSignature6Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSignature6() {
		return enrollmentSignature6;
	}

	public String strEnrollmentSignature6() {
		return enrollmentSignature6 == null ? "" : enrollmentSignature6;
	}

	public String jsonEnrollmentSignature6() {
		return enrollmentSignature6 == null ? "" : enrollmentSignature6;
	}

	public String nomAffichageEnrollmentSignature6() {
		return null;
	}

	public String htmTooltipEnrollmentSignature6() {
		return null;
	}

	public String htmEnrollmentSignature6() {
		return enrollmentSignature6 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSignature6());
	}

	public void inputEnrollmentSignature6(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("div").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature6").f();
			e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature6");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(enrollmentSignature6) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature6");
				a("src", StringUtils.isBlank(enrollmentSignature6) ? "data:image/png;base64," : enrollmentSignature6).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature6) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature6").f();
			e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature6");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature6').show(); ");
					s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature6').hide(); ");
					s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature6')); ");
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature6', null); ");
					s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature6')) { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature6').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature6').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Clear");
			g("button");
			e("button").a("id", "signatureButtonAcceptSchoolEnrollment", pk, "enrollmentSignature6");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputSchoolEnrollment", pk, "enrollmentSignature6').jSignature('getData', 'default'); "); 
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature6', src); ");
				s("\"");
				f().sx("Accept the signature");
			g("button");
		g("div");
	}

	public void htmEnrollmentSignature6(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentSignature6").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature6(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature6')); $('#", classApiMethodMethod, "_enrollmentSignature6').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature6', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature6')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature6')); }); ")
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

	//////////////////////////
	// enrollmentSignature7 //
	//////////////////////////

	/**	L'entité « enrollmentSignature7 »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSignature7;
	@JsonIgnore
	public Wrap<String> enrollmentSignature7Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature7").o(enrollmentSignature7);

	/**	<br/>L'entité « enrollmentSignature7 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature7">Trouver l'entité enrollmentSignature7 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSignature7(Wrap<String> c);

	public String getEnrollmentSignature7() {
		return enrollmentSignature7;
	}

	public void setEnrollmentSignature7(String enrollmentSignature7) {
		this.enrollmentSignature7 = enrollmentSignature7;
		this.enrollmentSignature7Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSignature7Init() {
		if(!enrollmentSignature7Wrap.alreadyInitialized) {
			_enrollmentSignature7(enrollmentSignature7Wrap);
			if(enrollmentSignature7 == null)
				setEnrollmentSignature7(enrollmentSignature7Wrap.o);
		}
		enrollmentSignature7Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSignature7() {
		return enrollmentSignature7;
	}

	public String strEnrollmentSignature7() {
		return enrollmentSignature7 == null ? "" : enrollmentSignature7;
	}

	public String jsonEnrollmentSignature7() {
		return enrollmentSignature7 == null ? "" : enrollmentSignature7;
	}

	public String nomAffichageEnrollmentSignature7() {
		return null;
	}

	public String htmTooltipEnrollmentSignature7() {
		return null;
	}

	public String htmEnrollmentSignature7() {
		return enrollmentSignature7 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSignature7());
	}

	public void inputEnrollmentSignature7(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("div").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature7").f();
			e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature7");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(enrollmentSignature7) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature7");
				a("src", StringUtils.isBlank(enrollmentSignature7) ? "data:image/png;base64," : enrollmentSignature7).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature7) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature7").f();
			e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature7");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature7').show(); ");
					s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature7').hide(); ");
					s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature7')); ");
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature7', null); ");
					s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature7')) { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature7').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature7').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Clear");
			g("button");
			e("button").a("id", "signatureButtonAcceptSchoolEnrollment", pk, "enrollmentSignature7");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputSchoolEnrollment", pk, "enrollmentSignature7').jSignature('getData', 'default'); "); 
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature7', src); ");
				s("\"");
				f().sx("Accept the signature");
			g("button");
		g("div");
	}

	public void htmEnrollmentSignature7(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentSignature7").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature7(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature7')); $('#", classApiMethodMethod, "_enrollmentSignature7').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature7', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature7')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature7')); }); ")
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

	//////////////////////////
	// enrollmentSignature8 //
	//////////////////////////

	/**	L'entité « enrollmentSignature8 »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSignature8;
	@JsonIgnore
	public Wrap<String> enrollmentSignature8Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature8").o(enrollmentSignature8);

	/**	<br/>L'entité « enrollmentSignature8 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature8">Trouver l'entité enrollmentSignature8 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSignature8(Wrap<String> c);

	public String getEnrollmentSignature8() {
		return enrollmentSignature8;
	}

	public void setEnrollmentSignature8(String enrollmentSignature8) {
		this.enrollmentSignature8 = enrollmentSignature8;
		this.enrollmentSignature8Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSignature8Init() {
		if(!enrollmentSignature8Wrap.alreadyInitialized) {
			_enrollmentSignature8(enrollmentSignature8Wrap);
			if(enrollmentSignature8 == null)
				setEnrollmentSignature8(enrollmentSignature8Wrap.o);
		}
		enrollmentSignature8Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSignature8() {
		return enrollmentSignature8;
	}

	public String strEnrollmentSignature8() {
		return enrollmentSignature8 == null ? "" : enrollmentSignature8;
	}

	public String jsonEnrollmentSignature8() {
		return enrollmentSignature8 == null ? "" : enrollmentSignature8;
	}

	public String nomAffichageEnrollmentSignature8() {
		return null;
	}

	public String htmTooltipEnrollmentSignature8() {
		return null;
	}

	public String htmEnrollmentSignature8() {
		return enrollmentSignature8 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSignature8());
	}

	public void inputEnrollmentSignature8(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("div").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature8").f();
			e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature8");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(enrollmentSignature8) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature8");
				a("src", StringUtils.isBlank(enrollmentSignature8) ? "data:image/png;base64," : enrollmentSignature8).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature8) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature8").f();
			e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature8");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature8').show(); ");
					s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature8').hide(); ");
					s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature8')); ");
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature8', null); ");
					s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature8')) { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature8').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature8').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Clear");
			g("button");
			e("button").a("id", "signatureButtonAcceptSchoolEnrollment", pk, "enrollmentSignature8");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputSchoolEnrollment", pk, "enrollmentSignature8').jSignature('getData', 'default'); "); 
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature8', src); ");
				s("\"");
				f().sx("Accept the signature");
			g("button");
		g("div");
	}

	public void htmEnrollmentSignature8(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentSignature8").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature8(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature8')); $('#", classApiMethodMethod, "_enrollmentSignature8').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature8', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature8')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature8')); }); ")
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

	//////////////////////////
	// enrollmentSignature9 //
	//////////////////////////

	/**	L'entité « enrollmentSignature9 »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSignature9;
	@JsonIgnore
	public Wrap<String> enrollmentSignature9Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature9").o(enrollmentSignature9);

	/**	<br/>L'entité « enrollmentSignature9 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature9">Trouver l'entité enrollmentSignature9 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSignature9(Wrap<String> c);

	public String getEnrollmentSignature9() {
		return enrollmentSignature9;
	}

	public void setEnrollmentSignature9(String enrollmentSignature9) {
		this.enrollmentSignature9 = enrollmentSignature9;
		this.enrollmentSignature9Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSignature9Init() {
		if(!enrollmentSignature9Wrap.alreadyInitialized) {
			_enrollmentSignature9(enrollmentSignature9Wrap);
			if(enrollmentSignature9 == null)
				setEnrollmentSignature9(enrollmentSignature9Wrap.o);
		}
		enrollmentSignature9Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSignature9() {
		return enrollmentSignature9;
	}

	public String strEnrollmentSignature9() {
		return enrollmentSignature9 == null ? "" : enrollmentSignature9;
	}

	public String jsonEnrollmentSignature9() {
		return enrollmentSignature9 == null ? "" : enrollmentSignature9;
	}

	public String nomAffichageEnrollmentSignature9() {
		return null;
	}

	public String htmTooltipEnrollmentSignature9() {
		return null;
	}

	public String htmEnrollmentSignature9() {
		return enrollmentSignature9 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSignature9());
	}

	public void inputEnrollmentSignature9(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("div").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature9").f();
			e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature9");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(enrollmentSignature9) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature9");
				a("src", StringUtils.isBlank(enrollmentSignature9) ? "data:image/png;base64," : enrollmentSignature9).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature9) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature9").f();
			e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature9");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature9').show(); ");
					s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature9').hide(); ");
					s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature9')); ");
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature9', null); ");
					s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature9')) { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature9').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature9').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Clear");
			g("button");
			e("button").a("id", "signatureButtonAcceptSchoolEnrollment", pk, "enrollmentSignature9");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputSchoolEnrollment", pk, "enrollmentSignature9').jSignature('getData', 'default'); "); 
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature9', src); ");
				s("\"");
				f().sx("Accept the signature");
			g("button");
		g("div");
	}

	public void htmEnrollmentSignature9(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentSignature9").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature9(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature9')); $('#", classApiMethodMethod, "_enrollmentSignature9').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature9', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature9')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature9')); }); ")
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

	///////////////////////////
	// enrollmentSignature10 //
	///////////////////////////

	/**	L'entité « enrollmentSignature10 »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentSignature10;
	@JsonIgnore
	public Wrap<String> enrollmentSignature10Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature10").o(enrollmentSignature10);

	/**	<br/>L'entité « enrollmentSignature10 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature10">Trouver l'entité enrollmentSignature10 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentSignature10(Wrap<String> c);

	public String getEnrollmentSignature10() {
		return enrollmentSignature10;
	}

	public void setEnrollmentSignature10(String enrollmentSignature10) {
		this.enrollmentSignature10 = enrollmentSignature10;
		this.enrollmentSignature10Wrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentSignature10Init() {
		if(!enrollmentSignature10Wrap.alreadyInitialized) {
			_enrollmentSignature10(enrollmentSignature10Wrap);
			if(enrollmentSignature10 == null)
				setEnrollmentSignature10(enrollmentSignature10Wrap.o);
		}
		enrollmentSignature10Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentSignature10() {
		return enrollmentSignature10;
	}

	public String strEnrollmentSignature10() {
		return enrollmentSignature10 == null ? "" : enrollmentSignature10;
	}

	public String jsonEnrollmentSignature10() {
		return enrollmentSignature10 == null ? "" : enrollmentSignature10;
	}

	public String nomAffichageEnrollmentSignature10() {
		return null;
	}

	public String htmTooltipEnrollmentSignature10() {
		return null;
	}

	public String htmEnrollmentSignature10() {
		return enrollmentSignature10 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentSignature10());
	}

	public void inputEnrollmentSignature10(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("div").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature10").f();
			e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature10");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(enrollmentSignature10) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature10");
				a("src", StringUtils.isBlank(enrollmentSignature10) ? "data:image/png;base64," : enrollmentSignature10).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature10) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature10").f();
			e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature10");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature10').show(); ");
					s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature10').hide(); ");
					s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature10')); ");
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature10', null); ");
					s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature10')) { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature10').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature10').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Clear");
			g("button");
			e("button").a("id", "signatureButtonAcceptSchoolEnrollment", pk, "enrollmentSignature10");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputSchoolEnrollment", pk, "enrollmentSignature10').jSignature('getData', 'default'); "); 
					s("patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature10', src); ");
				s("\"");
				f().sx("Accept the signature");
			g("button");
		g("div");
	}

	public void htmEnrollmentSignature10(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentSignature10").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature10(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature10')); $('#", classApiMethodMethod, "_enrollmentSignature10').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature10', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature10')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature10')); }); ")
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

	/////////////////////
	// enrollmentDate1 //
	/////////////////////

	/**	L'entité « enrollmentDate1 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate enrollmentDate1;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate1Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate1").o(enrollmentDate1);

	/**	<br/>L'entité « enrollmentDate1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate1">Trouver l'entité enrollmentDate1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDate1(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate1() {
		return enrollmentDate1;
	}

	public void setEnrollmentDate1(LocalDate enrollmentDate1) {
		this.enrollmentDate1 = enrollmentDate1;
		this.enrollmentDate1Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentDate1(Instant o) {
		this.enrollmentDate1 = LocalDate.from(o);
		this.enrollmentDate1Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setEnrollmentDate1(String o) {
		this.enrollmentDate1 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.enrollmentDate1Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setEnrollmentDate1(Date o) {
		this.enrollmentDate1 = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate1Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentDate1Init() {
		if(!enrollmentDate1Wrap.alreadyInitialized) {
			_enrollmentDate1(enrollmentDate1Wrap);
			if(enrollmentDate1 == null)
				setEnrollmentDate1(enrollmentDate1Wrap.o);
		}
		enrollmentDate1Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrEnrollmentDate1() {
		return enrollmentDate1 == null ? null : Date.from(enrollmentDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strEnrollmentDate1() {
		return enrollmentDate1 == null ? "" : enrollmentDate1.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonEnrollmentDate1() {
		return enrollmentDate1 == null ? "" : enrollmentDate1.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageEnrollmentDate1() {
		return null;
	}

	public String htmTooltipEnrollmentDate1() {
		return null;
	}

	public String htmEnrollmentDate1() {
		return enrollmentDate1 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDate1());
	}

	public void inputEnrollmentDate1(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setEnrollmentDate1 inputSchoolEnrollment", pk, "EnrollmentDate1 w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_enrollmentDate1")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", enrollmentDate1 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(enrollmentDate1))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate1', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate1')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate1')); }); } ")
			.fg();
	}

	public void htmEnrollmentDate1(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentDate1").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate1(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate1')); $('#", classApiMethodMethod, "_enrollmentDate1').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate1', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate1')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate1')); }); ")
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

	/////////////////////
	// enrollmentDate2 //
	/////////////////////

	/**	L'entité « enrollmentDate2 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate enrollmentDate2;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate2Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate2").o(enrollmentDate2);

	/**	<br/>L'entité « enrollmentDate2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate2">Trouver l'entité enrollmentDate2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDate2(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate2() {
		return enrollmentDate2;
	}

	public void setEnrollmentDate2(LocalDate enrollmentDate2) {
		this.enrollmentDate2 = enrollmentDate2;
		this.enrollmentDate2Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentDate2(Instant o) {
		this.enrollmentDate2 = LocalDate.from(o);
		this.enrollmentDate2Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setEnrollmentDate2(String o) {
		this.enrollmentDate2 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.enrollmentDate2Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setEnrollmentDate2(Date o) {
		this.enrollmentDate2 = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate2Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentDate2Init() {
		if(!enrollmentDate2Wrap.alreadyInitialized) {
			_enrollmentDate2(enrollmentDate2Wrap);
			if(enrollmentDate2 == null)
				setEnrollmentDate2(enrollmentDate2Wrap.o);
		}
		enrollmentDate2Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrEnrollmentDate2() {
		return enrollmentDate2 == null ? null : Date.from(enrollmentDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strEnrollmentDate2() {
		return enrollmentDate2 == null ? "" : enrollmentDate2.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonEnrollmentDate2() {
		return enrollmentDate2 == null ? "" : enrollmentDate2.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageEnrollmentDate2() {
		return null;
	}

	public String htmTooltipEnrollmentDate2() {
		return null;
	}

	public String htmEnrollmentDate2() {
		return enrollmentDate2 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDate2());
	}

	public void inputEnrollmentDate2(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setEnrollmentDate2 inputSchoolEnrollment", pk, "EnrollmentDate2 w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_enrollmentDate2")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", enrollmentDate2 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(enrollmentDate2))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate2', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate2')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate2')); }); } ")
			.fg();
	}

	public void htmEnrollmentDate2(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentDate2").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate2(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate2')); $('#", classApiMethodMethod, "_enrollmentDate2').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate2', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate2')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate2')); }); ")
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

	/////////////////////
	// enrollmentDate3 //
	/////////////////////

	/**	L'entité « enrollmentDate3 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate enrollmentDate3;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate3Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate3").o(enrollmentDate3);

	/**	<br/>L'entité « enrollmentDate3 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate3">Trouver l'entité enrollmentDate3 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDate3(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate3() {
		return enrollmentDate3;
	}

	public void setEnrollmentDate3(LocalDate enrollmentDate3) {
		this.enrollmentDate3 = enrollmentDate3;
		this.enrollmentDate3Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentDate3(Instant o) {
		this.enrollmentDate3 = LocalDate.from(o);
		this.enrollmentDate3Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setEnrollmentDate3(String o) {
		this.enrollmentDate3 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.enrollmentDate3Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setEnrollmentDate3(Date o) {
		this.enrollmentDate3 = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate3Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentDate3Init() {
		if(!enrollmentDate3Wrap.alreadyInitialized) {
			_enrollmentDate3(enrollmentDate3Wrap);
			if(enrollmentDate3 == null)
				setEnrollmentDate3(enrollmentDate3Wrap.o);
		}
		enrollmentDate3Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrEnrollmentDate3() {
		return enrollmentDate3 == null ? null : Date.from(enrollmentDate3.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strEnrollmentDate3() {
		return enrollmentDate3 == null ? "" : enrollmentDate3.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonEnrollmentDate3() {
		return enrollmentDate3 == null ? "" : enrollmentDate3.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageEnrollmentDate3() {
		return null;
	}

	public String htmTooltipEnrollmentDate3() {
		return null;
	}

	public String htmEnrollmentDate3() {
		return enrollmentDate3 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDate3());
	}

	public void inputEnrollmentDate3(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setEnrollmentDate3 inputSchoolEnrollment", pk, "EnrollmentDate3 w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_enrollmentDate3")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", enrollmentDate3 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(enrollmentDate3))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate3', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate3')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate3')); }); } ")
			.fg();
	}

	public void htmEnrollmentDate3(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentDate3").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate3(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate3')); $('#", classApiMethodMethod, "_enrollmentDate3').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate3', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate3')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate3')); }); ")
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

	/////////////////////
	// enrollmentDate4 //
	/////////////////////

	/**	L'entité « enrollmentDate4 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate enrollmentDate4;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate4Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate4").o(enrollmentDate4);

	/**	<br/>L'entité « enrollmentDate4 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate4">Trouver l'entité enrollmentDate4 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDate4(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate4() {
		return enrollmentDate4;
	}

	public void setEnrollmentDate4(LocalDate enrollmentDate4) {
		this.enrollmentDate4 = enrollmentDate4;
		this.enrollmentDate4Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentDate4(Instant o) {
		this.enrollmentDate4 = LocalDate.from(o);
		this.enrollmentDate4Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setEnrollmentDate4(String o) {
		this.enrollmentDate4 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.enrollmentDate4Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setEnrollmentDate4(Date o) {
		this.enrollmentDate4 = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate4Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentDate4Init() {
		if(!enrollmentDate4Wrap.alreadyInitialized) {
			_enrollmentDate4(enrollmentDate4Wrap);
			if(enrollmentDate4 == null)
				setEnrollmentDate4(enrollmentDate4Wrap.o);
		}
		enrollmentDate4Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrEnrollmentDate4() {
		return enrollmentDate4 == null ? null : Date.from(enrollmentDate4.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strEnrollmentDate4() {
		return enrollmentDate4 == null ? "" : enrollmentDate4.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonEnrollmentDate4() {
		return enrollmentDate4 == null ? "" : enrollmentDate4.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageEnrollmentDate4() {
		return null;
	}

	public String htmTooltipEnrollmentDate4() {
		return null;
	}

	public String htmEnrollmentDate4() {
		return enrollmentDate4 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDate4());
	}

	public void inputEnrollmentDate4(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setEnrollmentDate4 inputSchoolEnrollment", pk, "EnrollmentDate4 w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_enrollmentDate4")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", enrollmentDate4 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(enrollmentDate4))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate4', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate4')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate4')); }); } ")
			.fg();
	}

	public void htmEnrollmentDate4(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentDate4").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate4(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate4')); $('#", classApiMethodMethod, "_enrollmentDate4').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate4', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate4')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate4')); }); ")
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

	/////////////////////
	// enrollmentDate5 //
	/////////////////////

	/**	L'entité « enrollmentDate5 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate enrollmentDate5;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate5Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate5").o(enrollmentDate5);

	/**	<br/>L'entité « enrollmentDate5 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate5">Trouver l'entité enrollmentDate5 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDate5(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate5() {
		return enrollmentDate5;
	}

	public void setEnrollmentDate5(LocalDate enrollmentDate5) {
		this.enrollmentDate5 = enrollmentDate5;
		this.enrollmentDate5Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentDate5(Instant o) {
		this.enrollmentDate5 = LocalDate.from(o);
		this.enrollmentDate5Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setEnrollmentDate5(String o) {
		this.enrollmentDate5 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.enrollmentDate5Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setEnrollmentDate5(Date o) {
		this.enrollmentDate5 = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate5Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentDate5Init() {
		if(!enrollmentDate5Wrap.alreadyInitialized) {
			_enrollmentDate5(enrollmentDate5Wrap);
			if(enrollmentDate5 == null)
				setEnrollmentDate5(enrollmentDate5Wrap.o);
		}
		enrollmentDate5Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrEnrollmentDate5() {
		return enrollmentDate5 == null ? null : Date.from(enrollmentDate5.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strEnrollmentDate5() {
		return enrollmentDate5 == null ? "" : enrollmentDate5.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonEnrollmentDate5() {
		return enrollmentDate5 == null ? "" : enrollmentDate5.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageEnrollmentDate5() {
		return null;
	}

	public String htmTooltipEnrollmentDate5() {
		return null;
	}

	public String htmEnrollmentDate5() {
		return enrollmentDate5 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDate5());
	}

	public void inputEnrollmentDate5(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setEnrollmentDate5 inputSchoolEnrollment", pk, "EnrollmentDate5 w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_enrollmentDate5")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", enrollmentDate5 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(enrollmentDate5))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate5', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate5')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate5')); }); } ")
			.fg();
	}

	public void htmEnrollmentDate5(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentDate5").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate5(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate5')); $('#", classApiMethodMethod, "_enrollmentDate5').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate5', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate5')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate5')); }); ")
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

	/////////////////////
	// enrollmentDate6 //
	/////////////////////

	/**	L'entité « enrollmentDate6 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate enrollmentDate6;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate6Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate6").o(enrollmentDate6);

	/**	<br/>L'entité « enrollmentDate6 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate6">Trouver l'entité enrollmentDate6 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDate6(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate6() {
		return enrollmentDate6;
	}

	public void setEnrollmentDate6(LocalDate enrollmentDate6) {
		this.enrollmentDate6 = enrollmentDate6;
		this.enrollmentDate6Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentDate6(Instant o) {
		this.enrollmentDate6 = LocalDate.from(o);
		this.enrollmentDate6Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setEnrollmentDate6(String o) {
		this.enrollmentDate6 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.enrollmentDate6Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setEnrollmentDate6(Date o) {
		this.enrollmentDate6 = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate6Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentDate6Init() {
		if(!enrollmentDate6Wrap.alreadyInitialized) {
			_enrollmentDate6(enrollmentDate6Wrap);
			if(enrollmentDate6 == null)
				setEnrollmentDate6(enrollmentDate6Wrap.o);
		}
		enrollmentDate6Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrEnrollmentDate6() {
		return enrollmentDate6 == null ? null : Date.from(enrollmentDate6.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strEnrollmentDate6() {
		return enrollmentDate6 == null ? "" : enrollmentDate6.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonEnrollmentDate6() {
		return enrollmentDate6 == null ? "" : enrollmentDate6.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageEnrollmentDate6() {
		return null;
	}

	public String htmTooltipEnrollmentDate6() {
		return null;
	}

	public String htmEnrollmentDate6() {
		return enrollmentDate6 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDate6());
	}

	public void inputEnrollmentDate6(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setEnrollmentDate6 inputSchoolEnrollment", pk, "EnrollmentDate6 w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_enrollmentDate6")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", enrollmentDate6 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(enrollmentDate6))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate6', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate6')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate6')); }); } ")
			.fg();
	}

	public void htmEnrollmentDate6(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentDate6").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate6(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate6')); $('#", classApiMethodMethod, "_enrollmentDate6').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate6', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate6')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate6')); }); ")
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

	/////////////////////
	// enrollmentDate7 //
	/////////////////////

	/**	L'entité « enrollmentDate7 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate enrollmentDate7;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate7Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate7").o(enrollmentDate7);

	/**	<br/>L'entité « enrollmentDate7 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate7">Trouver l'entité enrollmentDate7 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDate7(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate7() {
		return enrollmentDate7;
	}

	public void setEnrollmentDate7(LocalDate enrollmentDate7) {
		this.enrollmentDate7 = enrollmentDate7;
		this.enrollmentDate7Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentDate7(Instant o) {
		this.enrollmentDate7 = LocalDate.from(o);
		this.enrollmentDate7Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setEnrollmentDate7(String o) {
		this.enrollmentDate7 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.enrollmentDate7Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setEnrollmentDate7(Date o) {
		this.enrollmentDate7 = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate7Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentDate7Init() {
		if(!enrollmentDate7Wrap.alreadyInitialized) {
			_enrollmentDate7(enrollmentDate7Wrap);
			if(enrollmentDate7 == null)
				setEnrollmentDate7(enrollmentDate7Wrap.o);
		}
		enrollmentDate7Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrEnrollmentDate7() {
		return enrollmentDate7 == null ? null : Date.from(enrollmentDate7.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strEnrollmentDate7() {
		return enrollmentDate7 == null ? "" : enrollmentDate7.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonEnrollmentDate7() {
		return enrollmentDate7 == null ? "" : enrollmentDate7.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageEnrollmentDate7() {
		return null;
	}

	public String htmTooltipEnrollmentDate7() {
		return null;
	}

	public String htmEnrollmentDate7() {
		return enrollmentDate7 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDate7());
	}

	public void inputEnrollmentDate7(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setEnrollmentDate7 inputSchoolEnrollment", pk, "EnrollmentDate7 w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_enrollmentDate7")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", enrollmentDate7 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(enrollmentDate7))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate7', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate7')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate7')); }); } ")
			.fg();
	}

	public void htmEnrollmentDate7(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentDate7").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate7(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate7')); $('#", classApiMethodMethod, "_enrollmentDate7').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate7', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate7')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate7')); }); ")
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

	/////////////////////
	// enrollmentDate8 //
	/////////////////////

	/**	L'entité « enrollmentDate8 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate enrollmentDate8;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate8Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate8").o(enrollmentDate8);

	/**	<br/>L'entité « enrollmentDate8 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate8">Trouver l'entité enrollmentDate8 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDate8(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate8() {
		return enrollmentDate8;
	}

	public void setEnrollmentDate8(LocalDate enrollmentDate8) {
		this.enrollmentDate8 = enrollmentDate8;
		this.enrollmentDate8Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentDate8(Instant o) {
		this.enrollmentDate8 = LocalDate.from(o);
		this.enrollmentDate8Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setEnrollmentDate8(String o) {
		this.enrollmentDate8 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.enrollmentDate8Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setEnrollmentDate8(Date o) {
		this.enrollmentDate8 = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate8Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentDate8Init() {
		if(!enrollmentDate8Wrap.alreadyInitialized) {
			_enrollmentDate8(enrollmentDate8Wrap);
			if(enrollmentDate8 == null)
				setEnrollmentDate8(enrollmentDate8Wrap.o);
		}
		enrollmentDate8Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrEnrollmentDate8() {
		return enrollmentDate8 == null ? null : Date.from(enrollmentDate8.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strEnrollmentDate8() {
		return enrollmentDate8 == null ? "" : enrollmentDate8.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonEnrollmentDate8() {
		return enrollmentDate8 == null ? "" : enrollmentDate8.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageEnrollmentDate8() {
		return null;
	}

	public String htmTooltipEnrollmentDate8() {
		return null;
	}

	public String htmEnrollmentDate8() {
		return enrollmentDate8 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDate8());
	}

	public void inputEnrollmentDate8(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setEnrollmentDate8 inputSchoolEnrollment", pk, "EnrollmentDate8 w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_enrollmentDate8")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", enrollmentDate8 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(enrollmentDate8))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate8', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate8')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate8')); }); } ")
			.fg();
	}

	public void htmEnrollmentDate8(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentDate8").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate8(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate8')); $('#", classApiMethodMethod, "_enrollmentDate8').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate8', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate8')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate8')); }); ")
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

	/////////////////////
	// enrollmentDate9 //
	/////////////////////

	/**	L'entité « enrollmentDate9 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate enrollmentDate9;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate9Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate9").o(enrollmentDate9);

	/**	<br/>L'entité « enrollmentDate9 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate9">Trouver l'entité enrollmentDate9 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDate9(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate9() {
		return enrollmentDate9;
	}

	public void setEnrollmentDate9(LocalDate enrollmentDate9) {
		this.enrollmentDate9 = enrollmentDate9;
		this.enrollmentDate9Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentDate9(Instant o) {
		this.enrollmentDate9 = LocalDate.from(o);
		this.enrollmentDate9Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setEnrollmentDate9(String o) {
		this.enrollmentDate9 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.enrollmentDate9Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setEnrollmentDate9(Date o) {
		this.enrollmentDate9 = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate9Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentDate9Init() {
		if(!enrollmentDate9Wrap.alreadyInitialized) {
			_enrollmentDate9(enrollmentDate9Wrap);
			if(enrollmentDate9 == null)
				setEnrollmentDate9(enrollmentDate9Wrap.o);
		}
		enrollmentDate9Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrEnrollmentDate9() {
		return enrollmentDate9 == null ? null : Date.from(enrollmentDate9.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strEnrollmentDate9() {
		return enrollmentDate9 == null ? "" : enrollmentDate9.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonEnrollmentDate9() {
		return enrollmentDate9 == null ? "" : enrollmentDate9.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageEnrollmentDate9() {
		return null;
	}

	public String htmTooltipEnrollmentDate9() {
		return null;
	}

	public String htmEnrollmentDate9() {
		return enrollmentDate9 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDate9());
	}

	public void inputEnrollmentDate9(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setEnrollmentDate9 inputSchoolEnrollment", pk, "EnrollmentDate9 w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_enrollmentDate9")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", enrollmentDate9 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(enrollmentDate9))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate9', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate9')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate9')); }); } ")
			.fg();
	}

	public void htmEnrollmentDate9(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentDate9").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate9(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate9')); $('#", classApiMethodMethod, "_enrollmentDate9').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate9', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate9')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate9')); }); ")
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

	//////////////////////
	// enrollmentDate10 //
	//////////////////////

	/**	L'entité « enrollmentDate10 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate enrollmentDate10;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate10Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate10").o(enrollmentDate10);

	/**	<br/>L'entité « enrollmentDate10 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate10">Trouver l'entité enrollmentDate10 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDate10(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate10() {
		return enrollmentDate10;
	}

	public void setEnrollmentDate10(LocalDate enrollmentDate10) {
		this.enrollmentDate10 = enrollmentDate10;
		this.enrollmentDate10Wrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentDate10(Instant o) {
		this.enrollmentDate10 = LocalDate.from(o);
		this.enrollmentDate10Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolEnrollment setEnrollmentDate10(String o) {
		this.enrollmentDate10 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.enrollmentDate10Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment setEnrollmentDate10(Date o) {
		this.enrollmentDate10 = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate10Wrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentDate10Init() {
		if(!enrollmentDate10Wrap.alreadyInitialized) {
			_enrollmentDate10(enrollmentDate10Wrap);
			if(enrollmentDate10 == null)
				setEnrollmentDate10(enrollmentDate10Wrap.o);
		}
		enrollmentDate10Wrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Date solrEnrollmentDate10() {
		return enrollmentDate10 == null ? null : Date.from(enrollmentDate10.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strEnrollmentDate10() {
		return enrollmentDate10 == null ? "" : enrollmentDate10.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonEnrollmentDate10() {
		return enrollmentDate10 == null ? "" : enrollmentDate10.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageEnrollmentDate10() {
		return null;
	}

	public String htmTooltipEnrollmentDate10() {
		return null;
	}

	public String htmEnrollmentDate10() {
		return enrollmentDate10 == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDate10());
	}

	public void inputEnrollmentDate10(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setEnrollmentDate10 inputSchoolEnrollment", pk, "EnrollmentDate10 w3-input w3-border ")
			.a("placeholder", "MM/DD/YYYY")
			.a("data-timeformat", "MM/DD/YYYY")
			.a("id", classApiMethodMethod, "_enrollmentDate10")
			.a("onclick", "removeGlow($(this)); ")
			.a("value", enrollmentDate10 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.forLanguageTag("en-US")).format(enrollmentDate10))
			.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate10', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate10')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate10')); }); } ")
			.fg();
	}

	public void htmEnrollmentDate10(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggestSchoolEnrollmentEnrollmentDate10").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate10(classApiMethodMethod);
							} g("div");
							if("Page".equals(classApiMethodMethod)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate10')); $('#", classApiMethodMethod, "_enrollmentDate10').val(null); patchSchoolEnrollmentVal([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate10', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate10')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate10')); }); ")
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

	///////////////////////////
	// enrollmentEnrollments //
	///////////////////////////

	/**	L'entité « enrollmentEnrollments »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 */
	protected List<SchoolEnrollment> enrollmentEnrollments = new java.util.ArrayList<org.computate.scolaire.enUS.enrollment.SchoolEnrollment>();
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentEnrollmentsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentEnrollments").o(enrollmentEnrollments);

	/**	<br/>L'entité « enrollmentEnrollments »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentEnrollments">Trouver l'entité enrollmentEnrollments dans Solr</a>
	 * <br/>
	 * @param enrollmentEnrollments est l'entité déjà construit. 
	 **/
	protected abstract void _enrollmentEnrollments(List<SchoolEnrollment> l);

	public List<SchoolEnrollment> getEnrollmentEnrollments() {
		return enrollmentEnrollments;
	}

	public void setEnrollmentEnrollments(List<SchoolEnrollment> enrollmentEnrollments) {
		this.enrollmentEnrollments = enrollmentEnrollments;
		this.enrollmentEnrollmentsWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment addEnrollmentEnrollments(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollmentEnrollments(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addEnrollmentEnrollments(SchoolEnrollment o) {
		if(o != null && !enrollmentEnrollments.contains(o))
			this.enrollmentEnrollments.add(o);
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentEnrollmentsInit() {
		if(!enrollmentEnrollmentsWrap.alreadyInitialized) {
			_enrollmentEnrollments(enrollmentEnrollments);
		}
		enrollmentEnrollmentsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	//////////////////////
	// enrollmentNumber //
	//////////////////////

	/**	L'entité « enrollmentNumber »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Integer enrollmentNumber;
	@JsonIgnore
	public Wrap<Integer> enrollmentNumberWrap = new Wrap<Integer>().p(this).c(Integer.class).var("enrollmentNumber").o(enrollmentNumber);

	/**	<br/>L'entité « enrollmentNumber »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentNumber">Trouver l'entité enrollmentNumber dans Solr</a>
	 * <br/>
	 * @param l est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentNumber(Wrap<Integer> l);

	public Integer getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(Integer enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
		this.enrollmentNumberWrap.alreadyInitialized = true;
	}
	public SchoolEnrollment setEnrollmentNumber(String o) {
		if(NumberUtils.isParsable(o))
			this.enrollmentNumber = Integer.parseInt(o);
		this.enrollmentNumberWrap.alreadyInitialized = true;
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentNumberInit() {
		if(!enrollmentNumberWrap.alreadyInitialized) {
			_enrollmentNumber(enrollmentNumberWrap);
			if(enrollmentNumber == null)
				setEnrollmentNumber(enrollmentNumberWrap.o);
		}
		enrollmentNumberWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public Integer solrEnrollmentNumber() {
		return enrollmentNumber;
	}

	public String strEnrollmentNumber() {
		return enrollmentNumber == null ? "" : enrollmentNumber.toString();
	}

	public String jsonEnrollmentNumber() {
		return enrollmentNumber == null ? "" : enrollmentNumber.toString();
	}

	public String nomAffichageEnrollmentNumber() {
		return null;
	}

	public String htmTooltipEnrollmentNumber() {
		return null;
	}

	public String htmEnrollmentNumber() {
		return enrollmentNumber == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentNumber());
	}

	////////////////////////////
	// enrollmentCompleteName //
	////////////////////////////

	/**	L'entité « enrollmentCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentCompleteName;
	@JsonIgnore
	public Wrap<String> enrollmentCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentCompleteName").o(enrollmentCompleteName);

	/**	<br/>L'entité « enrollmentCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentCompleteName">Trouver l'entité enrollmentCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentCompleteName(Wrap<String> c);

	public String getEnrollmentCompleteName() {
		return enrollmentCompleteName;
	}

	public void setEnrollmentCompleteName(String enrollmentCompleteName) {
		this.enrollmentCompleteName = enrollmentCompleteName;
		this.enrollmentCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentCompleteNameInit() {
		if(!enrollmentCompleteNameWrap.alreadyInitialized) {
			_enrollmentCompleteName(enrollmentCompleteNameWrap);
			if(enrollmentCompleteName == null)
				setEnrollmentCompleteName(enrollmentCompleteNameWrap.o);
		}
		enrollmentCompleteNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public String solrEnrollmentCompleteName() {
		return enrollmentCompleteName;
	}

	public String strEnrollmentCompleteName() {
		return enrollmentCompleteName == null ? "" : enrollmentCompleteName;
	}

	public String jsonEnrollmentCompleteName() {
		return enrollmentCompleteName == null ? "" : enrollmentCompleteName;
	}

	public String nomAffichageEnrollmentCompleteName() {
		return "name";
	}

	public String htmTooltipEnrollmentCompleteName() {
		return null;
	}

	public String htmEnrollmentCompleteName() {
		return enrollmentCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentCompleteName());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSchoolEnrollment = false;

	public SchoolEnrollment initDeepSchoolEnrollment(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolEnrollment) {
			alreadyInitializedSchoolEnrollment = true;
			initDeepSchoolEnrollment();
		}
		return (SchoolEnrollment)this;
	}

	public void initDeepSchoolEnrollment() {
		initSchoolEnrollment();
		super.initDeepCluster(siteRequest_);
	}

	public void initSchoolEnrollment() {
		enrollmentKeyInit();
		yearKeyInit();
		yearSearchInit();
		year_Init();
		blockKeysInit();
		blockSearchInit();
		blocks_Init();
		seasons_Init();
		block_Init();
		schoolKeyInit();
		seasonKeyInit();
		sessionKeyInit();
		ageKeyInit();
		blockKeyInit();
		childKeyInit();
		momKeysInit();
		dadKeysInit();
		guardianKeysInit();
		paymentKeysInit();
		enrollmentFormKeyInit();
		educationSortInit();
		schoolSortInit();
		yearSortInit();
		seasonSortInit();
		sessionSortInit();
		ageSortInit();
		childSearchInit();
		child_Init();
		momSearchInit();
		momsInit();
		dadSearchInit();
		dadsInit();
		guardianSearchInit();
		guardiansInit();
		childCompleteNameInit();
		childBirthDateInit();
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
		yearEnrollmentFeeInit();
		seasonCompleteNameInit();
		sessionStartDateInit();
		sessionEndDateInit();
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
		blockAdminNameInit();
		blockShortNameInit();
		blockCompleteNameInit();
		enrollmentApprovedInit();
		enrollmentImmunizationsInit();
		familyMarriedInit();
		familySeparatedInit();
		familyDivorcedInit();
		familyAddressInit();
		familyHowDoYouKnowTheSchoolInit();
		enrollmentSpecialConsiderationsInit();
		childMedicalConditionsInit();
		childPreviousSchoolsAttendedInit();
		childDescriptionInit();
		childObjectivesInit();
		childPottyTrainedInit();
		enrollmentGroupNameInit();
		enrollmentPaymentEachMonthInit();
		enrollmentPaymentCompleteInit();
		enrollmentParentNamesInit();
		enrollmentSignature1Init();
		enrollmentSignature2Init();
		enrollmentSignature3Init();
		enrollmentSignature4Init();
		enrollmentSignature5Init();
		enrollmentSignature6Init();
		enrollmentSignature7Init();
		enrollmentSignature8Init();
		enrollmentSignature9Init();
		enrollmentSignature10Init();
		enrollmentDate1Init();
		enrollmentDate2Init();
		enrollmentDate3Init();
		enrollmentDate4Init();
		enrollmentDate5Init();
		enrollmentDate6Init();
		enrollmentDate7Init();
		enrollmentDate8Init();
		enrollmentDate9Init();
		enrollmentDate10Init();
		enrollmentEnrollmentsInit();
		enrollmentNumberInit();
		enrollmentCompleteNameInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolEnrollment(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolEnrollment(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(yearSearch != null)
			yearSearch.setSiteRequest_(siteRequest_);
		if(blockSearch != null)
			blockSearch.setSiteRequest_(siteRequest_);
		if(childSearch != null)
			childSearch.setSiteRequest_(siteRequest_);
		if(momSearch != null)
			momSearch.setSiteRequest_(siteRequest_);
		if(dadSearch != null)
			dadSearch.setSiteRequest_(siteRequest_);
		if(guardianSearch != null)
			guardianSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolEnrollment(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolEnrollment(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolEnrollment(String var) {
		SchoolEnrollment oSchoolEnrollment = (SchoolEnrollment)this;
		switch(var) {
			case "enrollmentKey":
				return oSchoolEnrollment.enrollmentKey;
			case "yearKey":
				return oSchoolEnrollment.yearKey;
			case "yearSearch":
				return oSchoolEnrollment.yearSearch;
			case "year_":
				return oSchoolEnrollment.year_;
			case "blockKeys":
				return oSchoolEnrollment.blockKeys;
			case "blockSearch":
				return oSchoolEnrollment.blockSearch;
			case "blocks_":
				return oSchoolEnrollment.blocks_;
			case "seasons_":
				return oSchoolEnrollment.seasons_;
			case "block_":
				return oSchoolEnrollment.block_;
			case "schoolKey":
				return oSchoolEnrollment.schoolKey;
			case "seasonKey":
				return oSchoolEnrollment.seasonKey;
			case "sessionKey":
				return oSchoolEnrollment.sessionKey;
			case "ageKey":
				return oSchoolEnrollment.ageKey;
			case "blockKey":
				return oSchoolEnrollment.blockKey;
			case "childKey":
				return oSchoolEnrollment.childKey;
			case "momKeys":
				return oSchoolEnrollment.momKeys;
			case "dadKeys":
				return oSchoolEnrollment.dadKeys;
			case "guardianKeys":
				return oSchoolEnrollment.guardianKeys;
			case "paymentKeys":
				return oSchoolEnrollment.paymentKeys;
			case "enrollmentFormKey":
				return oSchoolEnrollment.enrollmentFormKey;
			case "educationSort":
				return oSchoolEnrollment.educationSort;
			case "schoolSort":
				return oSchoolEnrollment.schoolSort;
			case "yearSort":
				return oSchoolEnrollment.yearSort;
			case "seasonSort":
				return oSchoolEnrollment.seasonSort;
			case "sessionSort":
				return oSchoolEnrollment.sessionSort;
			case "ageSort":
				return oSchoolEnrollment.ageSort;
			case "childSearch":
				return oSchoolEnrollment.childSearch;
			case "child_":
				return oSchoolEnrollment.child_;
			case "momSearch":
				return oSchoolEnrollment.momSearch;
			case "moms":
				return oSchoolEnrollment.moms;
			case "dadSearch":
				return oSchoolEnrollment.dadSearch;
			case "dads":
				return oSchoolEnrollment.dads;
			case "guardianSearch":
				return oSchoolEnrollment.guardianSearch;
			case "guardians":
				return oSchoolEnrollment.guardians;
			case "childCompleteName":
				return oSchoolEnrollment.childCompleteName;
			case "childBirthDate":
				return oSchoolEnrollment.childBirthDate;
			case "schoolName":
				return oSchoolEnrollment.schoolName;
			case "schoolCompleteName":
				return oSchoolEnrollment.schoolCompleteName;
			case "schoolLocation":
				return oSchoolEnrollment.schoolLocation;
			case "schoolAddress":
				return oSchoolEnrollment.schoolAddress;
			case "schoolPhoneNumber":
				return oSchoolEnrollment.schoolPhoneNumber;
			case "schoolAdministratorName":
				return oSchoolEnrollment.schoolAdministratorName;
			case "yearStart":
				return oSchoolEnrollment.yearStart;
			case "yearEnd":
				return oSchoolEnrollment.yearEnd;
			case "seasonStartDate":
				return oSchoolEnrollment.seasonStartDate;
			case "seasonSummer":
				return oSchoolEnrollment.seasonSummer;
			case "seasonWinter":
				return oSchoolEnrollment.seasonWinter;
			case "yearEnrollmentFee":
				return oSchoolEnrollment.yearEnrollmentFee;
			case "seasonCompleteName":
				return oSchoolEnrollment.seasonCompleteName;
			case "sessionStartDate":
				return oSchoolEnrollment.sessionStartDate;
			case "sessionEndDate":
				return oSchoolEnrollment.sessionEndDate;
			case "ageCompleteName":
				return oSchoolEnrollment.ageCompleteName;
			case "ageStart":
				return oSchoolEnrollment.ageStart;
			case "ageEnd":
				return oSchoolEnrollment.ageEnd;
			case "blockStartTime":
				return oSchoolEnrollment.blockStartTime;
			case "blockEndTime":
				return oSchoolEnrollment.blockEndTime;
			case "blockPricePerMonth":
				return oSchoolEnrollment.blockPricePerMonth;
			case "blockSunday":
				return oSchoolEnrollment.blockSunday;
			case "blockMonday":
				return oSchoolEnrollment.blockMonday;
			case "blockTuesday":
				return oSchoolEnrollment.blockTuesday;
			case "blockWednesday":
				return oSchoolEnrollment.blockWednesday;
			case "blockThursday":
				return oSchoolEnrollment.blockThursday;
			case "blockFriday":
				return oSchoolEnrollment.blockFriday;
			case "blockSaturday":
				return oSchoolEnrollment.blockSaturday;
			case "blockTotalPrice":
				return oSchoolEnrollment.blockTotalPrice;
			case "blockAdminName":
				return oSchoolEnrollment.blockAdminName;
			case "blockShortName":
				return oSchoolEnrollment.blockShortName;
			case "blockCompleteName":
				return oSchoolEnrollment.blockCompleteName;
			case "enrollmentApproved":
				return oSchoolEnrollment.enrollmentApproved;
			case "enrollmentImmunizations":
				return oSchoolEnrollment.enrollmentImmunizations;
			case "familyMarried":
				return oSchoolEnrollment.familyMarried;
			case "familySeparated":
				return oSchoolEnrollment.familySeparated;
			case "familyDivorced":
				return oSchoolEnrollment.familyDivorced;
			case "familyAddress":
				return oSchoolEnrollment.familyAddress;
			case "familyHowDoYouKnowTheSchool":
				return oSchoolEnrollment.familyHowDoYouKnowTheSchool;
			case "enrollmentSpecialConsiderations":
				return oSchoolEnrollment.enrollmentSpecialConsiderations;
			case "childMedicalConditions":
				return oSchoolEnrollment.childMedicalConditions;
			case "childPreviousSchoolsAttended":
				return oSchoolEnrollment.childPreviousSchoolsAttended;
			case "childDescription":
				return oSchoolEnrollment.childDescription;
			case "childObjectives":
				return oSchoolEnrollment.childObjectives;
			case "childPottyTrained":
				return oSchoolEnrollment.childPottyTrained;
			case "enrollmentGroupName":
				return oSchoolEnrollment.enrollmentGroupName;
			case "enrollmentPaymentEachMonth":
				return oSchoolEnrollment.enrollmentPaymentEachMonth;
			case "enrollmentPaymentComplete":
				return oSchoolEnrollment.enrollmentPaymentComplete;
			case "enrollmentParentNames":
				return oSchoolEnrollment.enrollmentParentNames;
			case "enrollmentSignature1":
				return oSchoolEnrollment.enrollmentSignature1;
			case "enrollmentSignature2":
				return oSchoolEnrollment.enrollmentSignature2;
			case "enrollmentSignature3":
				return oSchoolEnrollment.enrollmentSignature3;
			case "enrollmentSignature4":
				return oSchoolEnrollment.enrollmentSignature4;
			case "enrollmentSignature5":
				return oSchoolEnrollment.enrollmentSignature5;
			case "enrollmentSignature6":
				return oSchoolEnrollment.enrollmentSignature6;
			case "enrollmentSignature7":
				return oSchoolEnrollment.enrollmentSignature7;
			case "enrollmentSignature8":
				return oSchoolEnrollment.enrollmentSignature8;
			case "enrollmentSignature9":
				return oSchoolEnrollment.enrollmentSignature9;
			case "enrollmentSignature10":
				return oSchoolEnrollment.enrollmentSignature10;
			case "enrollmentDate1":
				return oSchoolEnrollment.enrollmentDate1;
			case "enrollmentDate2":
				return oSchoolEnrollment.enrollmentDate2;
			case "enrollmentDate3":
				return oSchoolEnrollment.enrollmentDate3;
			case "enrollmentDate4":
				return oSchoolEnrollment.enrollmentDate4;
			case "enrollmentDate5":
				return oSchoolEnrollment.enrollmentDate5;
			case "enrollmentDate6":
				return oSchoolEnrollment.enrollmentDate6;
			case "enrollmentDate7":
				return oSchoolEnrollment.enrollmentDate7;
			case "enrollmentDate8":
				return oSchoolEnrollment.enrollmentDate8;
			case "enrollmentDate9":
				return oSchoolEnrollment.enrollmentDate9;
			case "enrollmentDate10":
				return oSchoolEnrollment.enrollmentDate10;
			case "enrollmentEnrollments":
				return oSchoolEnrollment.enrollmentEnrollments;
			case "enrollmentNumber":
				return oSchoolEnrollment.enrollmentNumber;
			case "enrollmentCompleteName":
				return oSchoolEnrollment.enrollmentCompleteName;
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
				o = attributeSchoolEnrollment(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolEnrollment(String var, Object val) {
		SchoolEnrollment oSchoolEnrollment = (SchoolEnrollment)this;
		switch(var) {
			case "yearKey":
				oSchoolEnrollment.setYearKey((Long)val);
				return val;
			case "blockKeys":
				oSchoolEnrollment.addBlockKeys((Long)val);
				return val;
			case "childKey":
				oSchoolEnrollment.setChildKey((Long)val);
				return val;
			case "momKeys":
				oSchoolEnrollment.addMomKeys((Long)val);
				return val;
			case "dadKeys":
				oSchoolEnrollment.addDadKeys((Long)val);
				return val;
			case "guardianKeys":
				oSchoolEnrollment.addGuardianKeys((Long)val);
				return val;
			case "paymentKeys":
				oSchoolEnrollment.addPaymentKeys((Long)val);
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
					o = defineSchoolEnrollment(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolEnrollment(String var, String val) {
		switch(var) {
			case "childCompleteName":
				setChildCompleteName(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "childBirthDate":
				setChildBirthDate(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "schoolAddress":
				setSchoolAddress(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentApproved":
				setEnrollmentApproved(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentImmunizations":
				setEnrollmentImmunizations(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "familyMarried":
				setFamilyMarried(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "familySeparated":
				setFamilySeparated(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "familyDivorced":
				setFamilyDivorced(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "familyAddress":
				setFamilyAddress(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "familyHowDoYouKnowTheSchool":
				setFamilyHowDoYouKnowTheSchool(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSpecialConsiderations":
				setEnrollmentSpecialConsiderations(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "childMedicalConditions":
				setChildMedicalConditions(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "childPreviousSchoolsAttended":
				setChildPreviousSchoolsAttended(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "childDescription":
				setChildDescription(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "childObjectives":
				setChildObjectives(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "childPottyTrained":
				setChildPottyTrained(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentGroupName":
				setEnrollmentGroupName(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentPaymentEachMonth":
				setEnrollmentPaymentEachMonth(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentPaymentComplete":
				setEnrollmentPaymentComplete(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentParentNames":
				setEnrollmentParentNames(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSignature1":
				setEnrollmentSignature1(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSignature2":
				setEnrollmentSignature2(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSignature3":
				setEnrollmentSignature3(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSignature4":
				setEnrollmentSignature4(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSignature5":
				setEnrollmentSignature5(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSignature6":
				setEnrollmentSignature6(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSignature7":
				setEnrollmentSignature7(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSignature8":
				setEnrollmentSignature8(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSignature9":
				setEnrollmentSignature9(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentSignature10":
				setEnrollmentSignature10(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentDate1":
				setEnrollmentDate1(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentDate2":
				setEnrollmentDate2(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentDate3":
				setEnrollmentDate3(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentDate4":
				setEnrollmentDate4(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentDate5":
				setEnrollmentDate5(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentDate6":
				setEnrollmentDate6(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentDate7":
				setEnrollmentDate7(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentDate8":
				setEnrollmentDate8(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentDate9":
				setEnrollmentDate9(val);
				savesSchoolEnrollment.add(var);
				return val;
			case "enrollmentDate10":
				setEnrollmentDate10(val);
				savesSchoolEnrollment.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesSchoolEnrollment = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolEnrollment(solrDocument);
	}
	public void populateSchoolEnrollment(SolrDocument solrDocument) {
		SchoolEnrollment oSchoolEnrollment = (SchoolEnrollment)this;
		savesSchoolEnrollment = (List<String>)solrDocument.get("savesSchoolEnrollment_stored_strings");
		if(savesSchoolEnrollment != null) {

			if(savesSchoolEnrollment.contains("enrollmentKey")) {
				Long enrollmentKey = (Long)solrDocument.get("enrollmentKey_stored_long");
				if(enrollmentKey != null)
					oSchoolEnrollment.setEnrollmentKey(enrollmentKey);
			}

			Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
			if(yearKey != null)
				oSchoolEnrollment.setYearKey(yearKey);

			List<Long> blockKeys = (List<Long>)solrDocument.get("blockKeys_stored_longs");
			if(blockKeys != null)
				oSchoolEnrollment.blockKeys.addAll(blockKeys);

			if(savesSchoolEnrollment.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oSchoolEnrollment.setSchoolKey(schoolKey);
			}

			if(savesSchoolEnrollment.contains("seasonKey")) {
				Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
				if(seasonKey != null)
					oSchoolEnrollment.setSeasonKey(seasonKey);
			}

			if(savesSchoolEnrollment.contains("sessionKey")) {
				Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
				if(sessionKey != null)
					oSchoolEnrollment.setSessionKey(sessionKey);
			}

			if(savesSchoolEnrollment.contains("ageKey")) {
				Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
				if(ageKey != null)
					oSchoolEnrollment.setAgeKey(ageKey);
			}

			if(savesSchoolEnrollment.contains("blockKey")) {
				Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
				if(blockKey != null)
					oSchoolEnrollment.setBlockKey(blockKey);
			}

			Long childKey = (Long)solrDocument.get("childKey_stored_long");
			if(childKey != null)
				oSchoolEnrollment.setChildKey(childKey);

			List<Long> momKeys = (List<Long>)solrDocument.get("momKeys_stored_longs");
			if(momKeys != null)
				oSchoolEnrollment.momKeys.addAll(momKeys);

			List<Long> dadKeys = (List<Long>)solrDocument.get("dadKeys_stored_longs");
			if(dadKeys != null)
				oSchoolEnrollment.dadKeys.addAll(dadKeys);

			List<Long> guardianKeys = (List<Long>)solrDocument.get("guardianKeys_stored_longs");
			if(guardianKeys != null)
				oSchoolEnrollment.guardianKeys.addAll(guardianKeys);

			List<Long> paymentKeys = (List<Long>)solrDocument.get("paymentKeys_stored_longs");
			if(paymentKeys != null)
				oSchoolEnrollment.paymentKeys.addAll(paymentKeys);

			if(savesSchoolEnrollment.contains("enrollmentFormKey")) {
				Long enrollmentFormKey = (Long)solrDocument.get("enrollmentFormKey_stored_long");
				if(enrollmentFormKey != null)
					oSchoolEnrollment.setEnrollmentFormKey(enrollmentFormKey);
			}

			if(savesSchoolEnrollment.contains("educationSort")) {
				Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
				if(educationSort != null)
					oSchoolEnrollment.setEducationSort(educationSort);
			}

			if(savesSchoolEnrollment.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolEnrollment.setSchoolSort(schoolSort);
			}

			if(savesSchoolEnrollment.contains("yearSort")) {
				Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
				if(yearSort != null)
					oSchoolEnrollment.setYearSort(yearSort);
			}

			if(savesSchoolEnrollment.contains("seasonSort")) {
				Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
				if(seasonSort != null)
					oSchoolEnrollment.setSeasonSort(seasonSort);
			}

			if(savesSchoolEnrollment.contains("sessionSort")) {
				Integer sessionSort = (Integer)solrDocument.get("sessionSort_stored_int");
				if(sessionSort != null)
					oSchoolEnrollment.setSessionSort(sessionSort);
			}

			if(savesSchoolEnrollment.contains("ageSort")) {
				Integer ageSort = (Integer)solrDocument.get("ageSort_stored_int");
				if(ageSort != null)
					oSchoolEnrollment.setAgeSort(ageSort);
			}

			if(savesSchoolEnrollment.contains("childCompleteName")) {
				String childCompleteName = (String)solrDocument.get("childCompleteName_stored_string");
				if(childCompleteName != null)
					oSchoolEnrollment.setChildCompleteName(childCompleteName);
			}

			if(savesSchoolEnrollment.contains("childBirthDate")) {
				Date childBirthDate = (Date)solrDocument.get("childBirthDate_stored_date");
				if(childBirthDate != null)
					oSchoolEnrollment.setChildBirthDate(childBirthDate);
			}

			if(savesSchoolEnrollment.contains("schoolName")) {
				String schoolName = (String)solrDocument.get("schoolName_stored_string");
				if(schoolName != null)
					oSchoolEnrollment.setSchoolName(schoolName);
			}

			if(savesSchoolEnrollment.contains("schoolCompleteName")) {
				String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
				if(schoolCompleteName != null)
					oSchoolEnrollment.setSchoolCompleteName(schoolCompleteName);
			}

			if(savesSchoolEnrollment.contains("schoolLocation")) {
				String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
				if(schoolLocation != null)
					oSchoolEnrollment.setSchoolLocation(schoolLocation);
			}

			if(savesSchoolEnrollment.contains("schoolAddress")) {
				String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
				if(schoolAddress != null)
					oSchoolEnrollment.setSchoolAddress(schoolAddress);
			}

			if(savesSchoolEnrollment.contains("schoolPhoneNumber")) {
				String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
				if(schoolPhoneNumber != null)
					oSchoolEnrollment.setSchoolPhoneNumber(schoolPhoneNumber);
			}

			if(savesSchoolEnrollment.contains("schoolAdministratorName")) {
				String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
				if(schoolAdministratorName != null)
					oSchoolEnrollment.setSchoolAdministratorName(schoolAdministratorName);
			}

			if(savesSchoolEnrollment.contains("yearStart")) {
				Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
				if(yearStart != null)
					oSchoolEnrollment.setYearStart(yearStart);
			}

			if(savesSchoolEnrollment.contains("yearEnd")) {
				Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
				if(yearEnd != null)
					oSchoolEnrollment.setYearEnd(yearEnd);
			}

			if(savesSchoolEnrollment.contains("seasonStartDate")) {
				Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
				if(seasonStartDate != null)
					oSchoolEnrollment.setSeasonStartDate(seasonStartDate);
			}

			if(savesSchoolEnrollment.contains("seasonSummer")) {
				Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
				if(seasonSummer != null)
					oSchoolEnrollment.setSeasonSummer(seasonSummer);
			}

			if(savesSchoolEnrollment.contains("seasonWinter")) {
				Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
				if(seasonWinter != null)
					oSchoolEnrollment.setSeasonWinter(seasonWinter);
			}

			if(savesSchoolEnrollment.contains("yearEnrollmentFee")) {
				Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
				if(yearEnrollmentFee != null)
					oSchoolEnrollment.setYearEnrollmentFee(yearEnrollmentFee);
			}

			if(savesSchoolEnrollment.contains("seasonCompleteName")) {
				String seasonCompleteName = (String)solrDocument.get("seasonCompleteName_stored_string");
				if(seasonCompleteName != null)
					oSchoolEnrollment.setSeasonCompleteName(seasonCompleteName);
			}

			if(savesSchoolEnrollment.contains("sessionStartDate")) {
				Date sessionStartDate = (Date)solrDocument.get("sessionStartDate_stored_date");
				if(sessionStartDate != null)
					oSchoolEnrollment.setSessionStartDate(sessionStartDate);
			}

			if(savesSchoolEnrollment.contains("sessionEndDate")) {
				Date sessionEndDate = (Date)solrDocument.get("sessionEndDate_stored_date");
				if(sessionEndDate != null)
					oSchoolEnrollment.setSessionEndDate(sessionEndDate);
			}

			if(savesSchoolEnrollment.contains("ageCompleteName")) {
				String ageCompleteName = (String)solrDocument.get("ageCompleteName_stored_string");
				if(ageCompleteName != null)
					oSchoolEnrollment.setAgeCompleteName(ageCompleteName);
			}

			if(savesSchoolEnrollment.contains("ageStart")) {
				Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
				if(ageStart != null)
					oSchoolEnrollment.setAgeStart(ageStart);
			}

			if(savesSchoolEnrollment.contains("ageEnd")) {
				Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
				if(ageEnd != null)
					oSchoolEnrollment.setAgeEnd(ageEnd);
			}

			if(savesSchoolEnrollment.contains("blockStartTime")) {
				String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
				if(blockStartTime != null)
					oSchoolEnrollment.setBlockStartTime(blockStartTime);
			}

			if(savesSchoolEnrollment.contains("blockEndTime")) {
				String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
				if(blockEndTime != null)
					oSchoolEnrollment.setBlockEndTime(blockEndTime);
			}

			if(savesSchoolEnrollment.contains("blockPricePerMonth")) {
				Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
				if(blockPricePerMonth != null)
					oSchoolEnrollment.setBlockPricePerMonth(blockPricePerMonth);
			}

			if(savesSchoolEnrollment.contains("blockSunday")) {
				Boolean blockSunday = (Boolean)solrDocument.get("blockSunday_stored_boolean");
				if(blockSunday != null)
					oSchoolEnrollment.setBlockSunday(blockSunday);
			}

			if(savesSchoolEnrollment.contains("blockMonday")) {
				Boolean blockMonday = (Boolean)solrDocument.get("blockMonday_stored_boolean");
				if(blockMonday != null)
					oSchoolEnrollment.setBlockMonday(blockMonday);
			}

			if(savesSchoolEnrollment.contains("blockTuesday")) {
				Boolean blockTuesday = (Boolean)solrDocument.get("blockTuesday_stored_boolean");
				if(blockTuesday != null)
					oSchoolEnrollment.setBlockTuesday(blockTuesday);
			}

			if(savesSchoolEnrollment.contains("blockWednesday")) {
				Boolean blockWednesday = (Boolean)solrDocument.get("blockWednesday_stored_boolean");
				if(blockWednesday != null)
					oSchoolEnrollment.setBlockWednesday(blockWednesday);
			}

			if(savesSchoolEnrollment.contains("blockThursday")) {
				Boolean blockThursday = (Boolean)solrDocument.get("blockThursday_stored_boolean");
				if(blockThursday != null)
					oSchoolEnrollment.setBlockThursday(blockThursday);
			}

			if(savesSchoolEnrollment.contains("blockFriday")) {
				Boolean blockFriday = (Boolean)solrDocument.get("blockFriday_stored_boolean");
				if(blockFriday != null)
					oSchoolEnrollment.setBlockFriday(blockFriday);
			}

			if(savesSchoolEnrollment.contains("blockSaturday")) {
				Boolean blockSaturday = (Boolean)solrDocument.get("blockSaturday_stored_boolean");
				if(blockSaturday != null)
					oSchoolEnrollment.setBlockSaturday(blockSaturday);
			}

			if(savesSchoolEnrollment.contains("blockTotalPrice")) {
				Double blockTotalPrice = (Double)solrDocument.get("blockTotalPrice_stored_double");
				if(blockTotalPrice != null)
					oSchoolEnrollment.setBlockTotalPrice(blockTotalPrice);
			}

			if(savesSchoolEnrollment.contains("blockAdminName")) {
				String blockAdminName = (String)solrDocument.get("blockAdminName_stored_string");
				if(blockAdminName != null)
					oSchoolEnrollment.setBlockAdminName(blockAdminName);
			}

			if(savesSchoolEnrollment.contains("blockShortName")) {
				String blockShortName = (String)solrDocument.get("blockShortName_stored_string");
				if(blockShortName != null)
					oSchoolEnrollment.setBlockShortName(blockShortName);
			}

			if(savesSchoolEnrollment.contains("blockCompleteName")) {
				String blockCompleteName = (String)solrDocument.get("blockCompleteName_stored_string");
				if(blockCompleteName != null)
					oSchoolEnrollment.setBlockCompleteName(blockCompleteName);
			}

			if(savesSchoolEnrollment.contains("enrollmentApproved")) {
				Boolean enrollmentApproved = (Boolean)solrDocument.get("enrollmentApproved_stored_boolean");
				if(enrollmentApproved != null)
					oSchoolEnrollment.setEnrollmentApproved(enrollmentApproved);
			}

			if(savesSchoolEnrollment.contains("enrollmentImmunizations")) {
				Boolean enrollmentImmunizations = (Boolean)solrDocument.get("enrollmentImmunizations_stored_boolean");
				if(enrollmentImmunizations != null)
					oSchoolEnrollment.setEnrollmentImmunizations(enrollmentImmunizations);
			}

			if(savesSchoolEnrollment.contains("familyMarried")) {
				Boolean familyMarried = (Boolean)solrDocument.get("familyMarried_stored_boolean");
				if(familyMarried != null)
					oSchoolEnrollment.setFamilyMarried(familyMarried);
			}

			if(savesSchoolEnrollment.contains("familySeparated")) {
				Boolean familySeparated = (Boolean)solrDocument.get("familySeparated_stored_boolean");
				if(familySeparated != null)
					oSchoolEnrollment.setFamilySeparated(familySeparated);
			}

			if(savesSchoolEnrollment.contains("familyDivorced")) {
				Boolean familyDivorced = (Boolean)solrDocument.get("familyDivorced_stored_boolean");
				if(familyDivorced != null)
					oSchoolEnrollment.setFamilyDivorced(familyDivorced);
			}

			if(savesSchoolEnrollment.contains("familyAddress")) {
				String familyAddress = (String)solrDocument.get("familyAddress_stored_string");
				if(familyAddress != null)
					oSchoolEnrollment.setFamilyAddress(familyAddress);
			}

			if(savesSchoolEnrollment.contains("familyHowDoYouKnowTheSchool")) {
				String familyHowDoYouKnowTheSchool = (String)solrDocument.get("familyHowDoYouKnowTheSchool_stored_string");
				if(familyHowDoYouKnowTheSchool != null)
					oSchoolEnrollment.setFamilyHowDoYouKnowTheSchool(familyHowDoYouKnowTheSchool);
			}

			if(savesSchoolEnrollment.contains("enrollmentSpecialConsiderations")) {
				String enrollmentSpecialConsiderations = (String)solrDocument.get("enrollmentSpecialConsiderations_stored_string");
				if(enrollmentSpecialConsiderations != null)
					oSchoolEnrollment.setEnrollmentSpecialConsiderations(enrollmentSpecialConsiderations);
			}

			if(savesSchoolEnrollment.contains("childMedicalConditions")) {
				String childMedicalConditions = (String)solrDocument.get("childMedicalConditions_stored_string");
				if(childMedicalConditions != null)
					oSchoolEnrollment.setChildMedicalConditions(childMedicalConditions);
			}

			if(savesSchoolEnrollment.contains("childPreviousSchoolsAttended")) {
				String childPreviousSchoolsAttended = (String)solrDocument.get("childPreviousSchoolsAttended_stored_string");
				if(childPreviousSchoolsAttended != null)
					oSchoolEnrollment.setChildPreviousSchoolsAttended(childPreviousSchoolsAttended);
			}

			if(savesSchoolEnrollment.contains("childDescription")) {
				String childDescription = (String)solrDocument.get("childDescription_stored_string");
				if(childDescription != null)
					oSchoolEnrollment.setChildDescription(childDescription);
			}

			if(savesSchoolEnrollment.contains("childObjectives")) {
				String childObjectives = (String)solrDocument.get("childObjectives_stored_string");
				if(childObjectives != null)
					oSchoolEnrollment.setChildObjectives(childObjectives);
			}

			if(savesSchoolEnrollment.contains("childPottyTrained")) {
				Boolean childPottyTrained = (Boolean)solrDocument.get("childPottyTrained_stored_boolean");
				if(childPottyTrained != null)
					oSchoolEnrollment.setChildPottyTrained(childPottyTrained);
			}

			if(savesSchoolEnrollment.contains("enrollmentGroupName")) {
				String enrollmentGroupName = (String)solrDocument.get("enrollmentGroupName_stored_string");
				if(enrollmentGroupName != null)
					oSchoolEnrollment.setEnrollmentGroupName(enrollmentGroupName);
			}

			if(savesSchoolEnrollment.contains("enrollmentPaymentEachMonth")) {
				Boolean enrollmentPaymentEachMonth = (Boolean)solrDocument.get("enrollmentPaymentEachMonth_stored_boolean");
				if(enrollmentPaymentEachMonth != null)
					oSchoolEnrollment.setEnrollmentPaymentEachMonth(enrollmentPaymentEachMonth);
			}

			if(savesSchoolEnrollment.contains("enrollmentPaymentComplete")) {
				Boolean enrollmentPaymentComplete = (Boolean)solrDocument.get("enrollmentPaymentComplete_stored_boolean");
				if(enrollmentPaymentComplete != null)
					oSchoolEnrollment.setEnrollmentPaymentComplete(enrollmentPaymentComplete);
			}

			if(savesSchoolEnrollment.contains("enrollmentParentNames")) {
				String enrollmentParentNames = (String)solrDocument.get("enrollmentParentNames_stored_string");
				if(enrollmentParentNames != null)
					oSchoolEnrollment.setEnrollmentParentNames(enrollmentParentNames);
			}

			if(savesSchoolEnrollment.contains("enrollmentSignature1")) {
				String enrollmentSignature1 = (String)solrDocument.get("enrollmentSignature1_stored_string");
				if(enrollmentSignature1 != null)
					oSchoolEnrollment.setEnrollmentSignature1(enrollmentSignature1);
			}

			if(savesSchoolEnrollment.contains("enrollmentSignature2")) {
				String enrollmentSignature2 = (String)solrDocument.get("enrollmentSignature2_stored_string");
				if(enrollmentSignature2 != null)
					oSchoolEnrollment.setEnrollmentSignature2(enrollmentSignature2);
			}

			if(savesSchoolEnrollment.contains("enrollmentSignature3")) {
				String enrollmentSignature3 = (String)solrDocument.get("enrollmentSignature3_stored_string");
				if(enrollmentSignature3 != null)
					oSchoolEnrollment.setEnrollmentSignature3(enrollmentSignature3);
			}

			if(savesSchoolEnrollment.contains("enrollmentSignature4")) {
				String enrollmentSignature4 = (String)solrDocument.get("enrollmentSignature4_stored_string");
				if(enrollmentSignature4 != null)
					oSchoolEnrollment.setEnrollmentSignature4(enrollmentSignature4);
			}

			if(savesSchoolEnrollment.contains("enrollmentSignature5")) {
				String enrollmentSignature5 = (String)solrDocument.get("enrollmentSignature5_stored_string");
				if(enrollmentSignature5 != null)
					oSchoolEnrollment.setEnrollmentSignature5(enrollmentSignature5);
			}

			if(savesSchoolEnrollment.contains("enrollmentSignature6")) {
				String enrollmentSignature6 = (String)solrDocument.get("enrollmentSignature6_stored_string");
				if(enrollmentSignature6 != null)
					oSchoolEnrollment.setEnrollmentSignature6(enrollmentSignature6);
			}

			if(savesSchoolEnrollment.contains("enrollmentSignature7")) {
				String enrollmentSignature7 = (String)solrDocument.get("enrollmentSignature7_stored_string");
				if(enrollmentSignature7 != null)
					oSchoolEnrollment.setEnrollmentSignature7(enrollmentSignature7);
			}

			if(savesSchoolEnrollment.contains("enrollmentSignature8")) {
				String enrollmentSignature8 = (String)solrDocument.get("enrollmentSignature8_stored_string");
				if(enrollmentSignature8 != null)
					oSchoolEnrollment.setEnrollmentSignature8(enrollmentSignature8);
			}

			if(savesSchoolEnrollment.contains("enrollmentSignature9")) {
				String enrollmentSignature9 = (String)solrDocument.get("enrollmentSignature9_stored_string");
				if(enrollmentSignature9 != null)
					oSchoolEnrollment.setEnrollmentSignature9(enrollmentSignature9);
			}

			if(savesSchoolEnrollment.contains("enrollmentSignature10")) {
				String enrollmentSignature10 = (String)solrDocument.get("enrollmentSignature10_stored_string");
				if(enrollmentSignature10 != null)
					oSchoolEnrollment.setEnrollmentSignature10(enrollmentSignature10);
			}

			if(savesSchoolEnrollment.contains("enrollmentDate1")) {
				Date enrollmentDate1 = (Date)solrDocument.get("enrollmentDate1_stored_date");
				if(enrollmentDate1 != null)
					oSchoolEnrollment.setEnrollmentDate1(enrollmentDate1);
			}

			if(savesSchoolEnrollment.contains("enrollmentDate2")) {
				Date enrollmentDate2 = (Date)solrDocument.get("enrollmentDate2_stored_date");
				if(enrollmentDate2 != null)
					oSchoolEnrollment.setEnrollmentDate2(enrollmentDate2);
			}

			if(savesSchoolEnrollment.contains("enrollmentDate3")) {
				Date enrollmentDate3 = (Date)solrDocument.get("enrollmentDate3_stored_date");
				if(enrollmentDate3 != null)
					oSchoolEnrollment.setEnrollmentDate3(enrollmentDate3);
			}

			if(savesSchoolEnrollment.contains("enrollmentDate4")) {
				Date enrollmentDate4 = (Date)solrDocument.get("enrollmentDate4_stored_date");
				if(enrollmentDate4 != null)
					oSchoolEnrollment.setEnrollmentDate4(enrollmentDate4);
			}

			if(savesSchoolEnrollment.contains("enrollmentDate5")) {
				Date enrollmentDate5 = (Date)solrDocument.get("enrollmentDate5_stored_date");
				if(enrollmentDate5 != null)
					oSchoolEnrollment.setEnrollmentDate5(enrollmentDate5);
			}

			if(savesSchoolEnrollment.contains("enrollmentDate6")) {
				Date enrollmentDate6 = (Date)solrDocument.get("enrollmentDate6_stored_date");
				if(enrollmentDate6 != null)
					oSchoolEnrollment.setEnrollmentDate6(enrollmentDate6);
			}

			if(savesSchoolEnrollment.contains("enrollmentDate7")) {
				Date enrollmentDate7 = (Date)solrDocument.get("enrollmentDate7_stored_date");
				if(enrollmentDate7 != null)
					oSchoolEnrollment.setEnrollmentDate7(enrollmentDate7);
			}

			if(savesSchoolEnrollment.contains("enrollmentDate8")) {
				Date enrollmentDate8 = (Date)solrDocument.get("enrollmentDate8_stored_date");
				if(enrollmentDate8 != null)
					oSchoolEnrollment.setEnrollmentDate8(enrollmentDate8);
			}

			if(savesSchoolEnrollment.contains("enrollmentDate9")) {
				Date enrollmentDate9 = (Date)solrDocument.get("enrollmentDate9_stored_date");
				if(enrollmentDate9 != null)
					oSchoolEnrollment.setEnrollmentDate9(enrollmentDate9);
			}

			if(savesSchoolEnrollment.contains("enrollmentDate10")) {
				Date enrollmentDate10 = (Date)solrDocument.get("enrollmentDate10_stored_date");
				if(enrollmentDate10 != null)
					oSchoolEnrollment.setEnrollmentDate10(enrollmentDate10);
			}

			if(savesSchoolEnrollment.contains("enrollmentCompleteName")) {
				String enrollmentCompleteName = (String)solrDocument.get("enrollmentCompleteName_stored_string");
				if(enrollmentCompleteName != null)
					oSchoolEnrollment.setEnrollmentCompleteName(enrollmentCompleteName);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.enrollment.SchoolEnrollment"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolEnrollment o = new SchoolEnrollment();
			o.siteRequestSchoolEnrollment(siteRequest);
			o.initDeepSchoolEnrollment(siteRequest);
			o.indexSchoolEnrollment();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolEnrollment();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolEnrollment(document);
	}

	public void indexSchoolEnrollment(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolEnrollment(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolEnrollment() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolEnrollment(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolEnrollment(SolrInputDocument document) {
		if(savesSchoolEnrollment != null)
			document.addField("savesSchoolEnrollment_stored_strings", savesSchoolEnrollment);

		if(enrollmentKey != null) {
			document.addField("enrollmentKey_indexed_long", enrollmentKey);
			document.addField("enrollmentKey_stored_long", enrollmentKey);
		}
		if(yearKey != null) {
			document.addField("yearKey_indexed_long", yearKey);
			document.addField("yearKey_stored_long", yearKey);
		}
		if(blockKeys != null) {
			for(java.lang.Long o : blockKeys) {
				document.addField("blockKeys_indexed_longs", o);
			}
			for(java.lang.Long o : blockKeys) {
				document.addField("blockKeys_stored_longs", o);
			}
		}
		if(schoolKey != null) {
			document.addField("schoolKey_indexed_long", schoolKey);
			document.addField("schoolKey_stored_long", schoolKey);
		}
		if(seasonKey != null) {
			document.addField("seasonKey_indexed_long", seasonKey);
			document.addField("seasonKey_stored_long", seasonKey);
		}
		if(sessionKey != null) {
			document.addField("sessionKey_indexed_long", sessionKey);
			document.addField("sessionKey_stored_long", sessionKey);
		}
		if(ageKey != null) {
			document.addField("ageKey_indexed_long", ageKey);
			document.addField("ageKey_stored_long", ageKey);
		}
		if(blockKey != null) {
			document.addField("blockKey_indexed_long", blockKey);
			document.addField("blockKey_stored_long", blockKey);
		}
		if(childKey != null) {
			document.addField("childKey_indexed_long", childKey);
			document.addField("childKey_stored_long", childKey);
		}
		if(momKeys != null) {
			for(java.lang.Long o : momKeys) {
				document.addField("momKeys_indexed_longs", o);
			}
			for(java.lang.Long o : momKeys) {
				document.addField("momKeys_stored_longs", o);
			}
		}
		if(dadKeys != null) {
			for(java.lang.Long o : dadKeys) {
				document.addField("dadKeys_indexed_longs", o);
			}
			for(java.lang.Long o : dadKeys) {
				document.addField("dadKeys_stored_longs", o);
			}
		}
		if(guardianKeys != null) {
			for(java.lang.Long o : guardianKeys) {
				document.addField("guardianKeys_indexed_longs", o);
			}
			for(java.lang.Long o : guardianKeys) {
				document.addField("guardianKeys_stored_longs", o);
			}
		}
		if(paymentKeys != null) {
			for(java.lang.Long o : paymentKeys) {
				document.addField("paymentKeys_indexed_longs", o);
			}
			for(java.lang.Long o : paymentKeys) {
				document.addField("paymentKeys_stored_longs", o);
			}
		}
		if(enrollmentFormKey != null) {
			document.addField("enrollmentFormKey_indexed_long", enrollmentFormKey);
			document.addField("enrollmentFormKey_stored_long", enrollmentFormKey);
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
		if(childCompleteName != null) {
			document.addField("childCompleteName_indexed_string", childCompleteName);
			document.addField("childCompleteName_stored_string", childCompleteName);
		}
		if(childBirthDate != null) {
			document.addField("childBirthDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(childBirthDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("childBirthDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(childBirthDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
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
		if(yearEnrollmentFee != null) {
			document.addField("yearEnrollmentFee_indexed_double", yearEnrollmentFee.doubleValue());
			document.addField("yearEnrollmentFee_stored_double", yearEnrollmentFee.doubleValue());
		}
		if(seasonCompleteName != null) {
			document.addField("seasonCompleteName_indexed_string", seasonCompleteName);
			document.addField("seasonCompleteName_stored_string", seasonCompleteName);
		}
		if(sessionStartDate != null) {
			document.addField("sessionStartDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionStartDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionEndDate != null) {
			document.addField("sessionEndDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionEndDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
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
		if(blockAdminName != null) {
			document.addField("blockAdminName_indexed_string", blockAdminName);
			document.addField("blockAdminName_stored_string", blockAdminName);
		}
		if(blockShortName != null) {
			document.addField("blockShortName_indexed_string", blockShortName);
			document.addField("blockShortName_stored_string", blockShortName);
		}
		if(blockCompleteName != null) {
			document.addField("blockCompleteName_indexed_string", blockCompleteName);
			document.addField("blockCompleteName_stored_string", blockCompleteName);
		}
		if(enrollmentApproved != null) {
			document.addField("enrollmentApproved_indexed_boolean", enrollmentApproved);
			document.addField("enrollmentApproved_stored_boolean", enrollmentApproved);
		}
		if(enrollmentImmunizations != null) {
			document.addField("enrollmentImmunizations_indexed_boolean", enrollmentImmunizations);
			document.addField("enrollmentImmunizations_stored_boolean", enrollmentImmunizations);
		}
		if(familyMarried != null) {
			document.addField("familyMarried_indexed_boolean", familyMarried);
			document.addField("familyMarried_stored_boolean", familyMarried);
		}
		if(familySeparated != null) {
			document.addField("familySeparated_indexed_boolean", familySeparated);
			document.addField("familySeparated_stored_boolean", familySeparated);
		}
		if(familyDivorced != null) {
			document.addField("familyDivorced_indexed_boolean", familyDivorced);
			document.addField("familyDivorced_stored_boolean", familyDivorced);
		}
		if(familyAddress != null) {
			document.addField("familyAddress_indexed_string", familyAddress);
			document.addField("familyAddress_stored_string", familyAddress);
		}
		if(familyHowDoYouKnowTheSchool != null) {
			document.addField("familyHowDoYouKnowTheSchool_indexed_string", familyHowDoYouKnowTheSchool);
			document.addField("familyHowDoYouKnowTheSchool_stored_string", familyHowDoYouKnowTheSchool);
		}
		if(enrollmentSpecialConsiderations != null) {
			document.addField("enrollmentSpecialConsiderations_indexed_string", enrollmentSpecialConsiderations);
			document.addField("enrollmentSpecialConsiderations_stored_string", enrollmentSpecialConsiderations);
		}
		if(childMedicalConditions != null) {
			document.addField("childMedicalConditions_indexed_string", childMedicalConditions);
			document.addField("childMedicalConditions_stored_string", childMedicalConditions);
		}
		if(childPreviousSchoolsAttended != null) {
			document.addField("childPreviousSchoolsAttended_indexed_string", childPreviousSchoolsAttended);
			document.addField("childPreviousSchoolsAttended_stored_string", childPreviousSchoolsAttended);
		}
		if(childDescription != null) {
			document.addField("childDescription_indexed_string", childDescription);
			document.addField("childDescription_stored_string", childDescription);
		}
		if(childObjectives != null) {
			document.addField("childObjectives_indexed_string", childObjectives);
			document.addField("childObjectives_stored_string", childObjectives);
		}
		if(childPottyTrained != null) {
			document.addField("childPottyTrained_indexed_boolean", childPottyTrained);
			document.addField("childPottyTrained_stored_boolean", childPottyTrained);
		}
		if(enrollmentGroupName != null) {
			document.addField("enrollmentGroupName_indexed_string", enrollmentGroupName);
			document.addField("enrollmentGroupName_stored_string", enrollmentGroupName);
		}
		if(enrollmentPaymentEachMonth != null) {
			document.addField("enrollmentPaymentEachMonth_indexed_boolean", enrollmentPaymentEachMonth);
			document.addField("enrollmentPaymentEachMonth_stored_boolean", enrollmentPaymentEachMonth);
		}
		if(enrollmentPaymentComplete != null) {
			document.addField("enrollmentPaymentComplete_indexed_boolean", enrollmentPaymentComplete);
			document.addField("enrollmentPaymentComplete_stored_boolean", enrollmentPaymentComplete);
		}
		if(enrollmentParentNames != null) {
			document.addField("enrollmentParentNames_indexed_string", enrollmentParentNames);
			document.addField("enrollmentParentNames_stored_string", enrollmentParentNames);
		}
		if(enrollmentSignature1 != null) {
			document.addField("enrollmentSignature1_stored_string", enrollmentSignature1);
		}
		if(enrollmentSignature2 != null) {
			document.addField("enrollmentSignature2_stored_string", enrollmentSignature2);
		}
		if(enrollmentSignature3 != null) {
			document.addField("enrollmentSignature3_stored_string", enrollmentSignature3);
		}
		if(enrollmentSignature4 != null) {
			document.addField("enrollmentSignature4_stored_string", enrollmentSignature4);
		}
		if(enrollmentSignature5 != null) {
			document.addField("enrollmentSignature5_stored_string", enrollmentSignature5);
		}
		if(enrollmentSignature6 != null) {
			document.addField("enrollmentSignature6_stored_string", enrollmentSignature6);
		}
		if(enrollmentSignature7 != null) {
			document.addField("enrollmentSignature7_stored_string", enrollmentSignature7);
		}
		if(enrollmentSignature8 != null) {
			document.addField("enrollmentSignature8_stored_string", enrollmentSignature8);
		}
		if(enrollmentSignature9 != null) {
			document.addField("enrollmentSignature9_stored_string", enrollmentSignature9);
		}
		if(enrollmentSignature10 != null) {
			document.addField("enrollmentSignature10_stored_string", enrollmentSignature10);
		}
		if(enrollmentDate1 != null) {
			document.addField("enrollmentDate1_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate1.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate1_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate1.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate2 != null) {
			document.addField("enrollmentDate2_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate2.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate2_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate2.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate3 != null) {
			document.addField("enrollmentDate3_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate3.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate3_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate3.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate4 != null) {
			document.addField("enrollmentDate4_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate4.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate4_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate4.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate5 != null) {
			document.addField("enrollmentDate5_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate5.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate5_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate5.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate6 != null) {
			document.addField("enrollmentDate6_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate6.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate6_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate6.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate7 != null) {
			document.addField("enrollmentDate7_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate7.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate7_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate7.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate8 != null) {
			document.addField("enrollmentDate8_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate8.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate8_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate8.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate9 != null) {
			document.addField("enrollmentDate9_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate9.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate9_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate9.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate10 != null) {
			document.addField("enrollmentDate10_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate10.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate10_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate10.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentCompleteName != null) {
			document.addField("enrollmentCompleteName_indexed_string", enrollmentCompleteName);
			document.addField("enrollmentCompleteName_stored_string", enrollmentCompleteName);
		}
		super.indexCluster(document);

	}

	public void unindexSchoolEnrollment() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolEnrollment(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedSchoolEnrollment(String entityVar) {
		switch(entityVar) {
			case "enrollmentKey":
				return "enrollmentKey_indexed_long";
			case "yearKey":
				return "yearKey_indexed_long";
			case "blockKeys":
				return "blockKeys_indexed_longs";
			case "schoolKey":
				return "schoolKey_indexed_long";
			case "seasonKey":
				return "seasonKey_indexed_long";
			case "sessionKey":
				return "sessionKey_indexed_long";
			case "ageKey":
				return "ageKey_indexed_long";
			case "blockKey":
				return "blockKey_indexed_long";
			case "childKey":
				return "childKey_indexed_long";
			case "momKeys":
				return "momKeys_indexed_longs";
			case "dadKeys":
				return "dadKeys_indexed_longs";
			case "guardianKeys":
				return "guardianKeys_indexed_longs";
			case "paymentKeys":
				return "paymentKeys_indexed_longs";
			case "enrollmentFormKey":
				return "enrollmentFormKey_indexed_long";
			case "educationSort":
				return "educationSort_indexed_int";
			case "schoolSort":
				return "schoolSort_indexed_int";
			case "yearSort":
				return "yearSort_indexed_int";
			case "seasonSort":
				return "seasonSort_indexed_int";
			case "sessionSort":
				return "sessionSort_indexed_int";
			case "ageSort":
				return "ageSort_indexed_int";
			case "childCompleteName":
				return "childCompleteName_indexed_string";
			case "childBirthDate":
				return "childBirthDate_indexed_date";
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
			case "yearEnrollmentFee":
				return "yearEnrollmentFee_indexed_double";
			case "seasonCompleteName":
				return "seasonCompleteName_indexed_string";
			case "sessionStartDate":
				return "sessionStartDate_indexed_date";
			case "sessionEndDate":
				return "sessionEndDate_indexed_date";
			case "ageCompleteName":
				return "ageCompleteName_indexed_string";
			case "ageStart":
				return "ageStart_indexed_int";
			case "ageEnd":
				return "ageEnd_indexed_int";
			case "blockStartTime":
				return "blockStartTime_indexed_string";
			case "blockEndTime":
				return "blockEndTime_indexed_string";
			case "blockPricePerMonth":
				return "blockPricePerMonth_indexed_double";
			case "blockSunday":
				return "blockSunday_indexed_boolean";
			case "blockMonday":
				return "blockMonday_indexed_boolean";
			case "blockTuesday":
				return "blockTuesday_indexed_boolean";
			case "blockWednesday":
				return "blockWednesday_indexed_boolean";
			case "blockThursday":
				return "blockThursday_indexed_boolean";
			case "blockFriday":
				return "blockFriday_indexed_boolean";
			case "blockSaturday":
				return "blockSaturday_indexed_boolean";
			case "blockTotalPrice":
				return "blockTotalPrice_indexed_double";
			case "blockAdminName":
				return "blockAdminName_indexed_string";
			case "blockShortName":
				return "blockShortName_indexed_string";
			case "blockCompleteName":
				return "blockCompleteName_indexed_string";
			case "enrollmentApproved":
				return "enrollmentApproved_indexed_boolean";
			case "enrollmentImmunizations":
				return "enrollmentImmunizations_indexed_boolean";
			case "familyMarried":
				return "familyMarried_indexed_boolean";
			case "familySeparated":
				return "familySeparated_indexed_boolean";
			case "familyDivorced":
				return "familyDivorced_indexed_boolean";
			case "familyAddress":
				return "familyAddress_indexed_string";
			case "familyHowDoYouKnowTheSchool":
				return "familyHowDoYouKnowTheSchool_indexed_string";
			case "enrollmentSpecialConsiderations":
				return "enrollmentSpecialConsiderations_indexed_string";
			case "childMedicalConditions":
				return "childMedicalConditions_indexed_string";
			case "childPreviousSchoolsAttended":
				return "childPreviousSchoolsAttended_indexed_string";
			case "childDescription":
				return "childDescription_indexed_string";
			case "childObjectives":
				return "childObjectives_indexed_string";
			case "childPottyTrained":
				return "childPottyTrained_indexed_boolean";
			case "enrollmentGroupName":
				return "enrollmentGroupName_indexed_string";
			case "enrollmentPaymentEachMonth":
				return "enrollmentPaymentEachMonth_indexed_boolean";
			case "enrollmentPaymentComplete":
				return "enrollmentPaymentComplete_indexed_boolean";
			case "enrollmentParentNames":
				return "enrollmentParentNames_indexed_string";
			case "enrollmentDate1":
				return "enrollmentDate1_indexed_date";
			case "enrollmentDate2":
				return "enrollmentDate2_indexed_date";
			case "enrollmentDate3":
				return "enrollmentDate3_indexed_date";
			case "enrollmentDate4":
				return "enrollmentDate4_indexed_date";
			case "enrollmentDate5":
				return "enrollmentDate5_indexed_date";
			case "enrollmentDate6":
				return "enrollmentDate6_indexed_date";
			case "enrollmentDate7":
				return "enrollmentDate7_indexed_date";
			case "enrollmentDate8":
				return "enrollmentDate8_indexed_date";
			case "enrollmentDate9":
				return "enrollmentDate9_indexed_date";
			case "enrollmentDate10":
				return "enrollmentDate10_indexed_date";
			case "enrollmentCompleteName":
				return "enrollmentCompleteName_indexed_string";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchSchoolEnrollment(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestSchoolEnrollment(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestCluster(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeSchoolEnrollment(solrDocument);
	}
	public void storeSchoolEnrollment(SolrDocument solrDocument) {
		SchoolEnrollment oSchoolEnrollment = (SchoolEnrollment)this;

		Long enrollmentKey = (Long)solrDocument.get("enrollmentKey_stored_long");
		if(enrollmentKey != null)
			oSchoolEnrollment.setEnrollmentKey(enrollmentKey);

		Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
		if(yearKey != null)
			oSchoolEnrollment.setYearKey(yearKey);

		List<Long> blockKeys = (List<Long>)solrDocument.get("blockKeys_stored_longs");
		if(blockKeys != null)
			oSchoolEnrollment.blockKeys.addAll(blockKeys);

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oSchoolEnrollment.setSchoolKey(schoolKey);

		Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
		if(seasonKey != null)
			oSchoolEnrollment.setSeasonKey(seasonKey);

		Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
		if(sessionKey != null)
			oSchoolEnrollment.setSessionKey(sessionKey);

		Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
		if(ageKey != null)
			oSchoolEnrollment.setAgeKey(ageKey);

		Long blockKey = (Long)solrDocument.get("blockKey_stored_long");
		if(blockKey != null)
			oSchoolEnrollment.setBlockKey(blockKey);

		Long childKey = (Long)solrDocument.get("childKey_stored_long");
		if(childKey != null)
			oSchoolEnrollment.setChildKey(childKey);

		List<Long> momKeys = (List<Long>)solrDocument.get("momKeys_stored_longs");
		if(momKeys != null)
			oSchoolEnrollment.momKeys.addAll(momKeys);

		List<Long> dadKeys = (List<Long>)solrDocument.get("dadKeys_stored_longs");
		if(dadKeys != null)
			oSchoolEnrollment.dadKeys.addAll(dadKeys);

		List<Long> guardianKeys = (List<Long>)solrDocument.get("guardianKeys_stored_longs");
		if(guardianKeys != null)
			oSchoolEnrollment.guardianKeys.addAll(guardianKeys);

		List<Long> paymentKeys = (List<Long>)solrDocument.get("paymentKeys_stored_longs");
		if(paymentKeys != null)
			oSchoolEnrollment.paymentKeys.addAll(paymentKeys);

		Long enrollmentFormKey = (Long)solrDocument.get("enrollmentFormKey_stored_long");
		if(enrollmentFormKey != null)
			oSchoolEnrollment.setEnrollmentFormKey(enrollmentFormKey);

		Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
		if(educationSort != null)
			oSchoolEnrollment.setEducationSort(educationSort);

		Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
		if(schoolSort != null)
			oSchoolEnrollment.setSchoolSort(schoolSort);

		Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
		if(yearSort != null)
			oSchoolEnrollment.setYearSort(yearSort);

		Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
		if(seasonSort != null)
			oSchoolEnrollment.setSeasonSort(seasonSort);

		Integer sessionSort = (Integer)solrDocument.get("sessionSort_stored_int");
		if(sessionSort != null)
			oSchoolEnrollment.setSessionSort(sessionSort);

		Integer ageSort = (Integer)solrDocument.get("ageSort_stored_int");
		if(ageSort != null)
			oSchoolEnrollment.setAgeSort(ageSort);

		String childCompleteName = (String)solrDocument.get("childCompleteName_stored_string");
		if(childCompleteName != null)
			oSchoolEnrollment.setChildCompleteName(childCompleteName);

		Date childBirthDate = (Date)solrDocument.get("childBirthDate_stored_date");
		if(childBirthDate != null)
			oSchoolEnrollment.setChildBirthDate(childBirthDate);

		String schoolName = (String)solrDocument.get("schoolName_stored_string");
		if(schoolName != null)
			oSchoolEnrollment.setSchoolName(schoolName);

		String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
		if(schoolCompleteName != null)
			oSchoolEnrollment.setSchoolCompleteName(schoolCompleteName);

		String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
		if(schoolLocation != null)
			oSchoolEnrollment.setSchoolLocation(schoolLocation);

		String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
		if(schoolAddress != null)
			oSchoolEnrollment.setSchoolAddress(schoolAddress);

		String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
		if(schoolPhoneNumber != null)
			oSchoolEnrollment.setSchoolPhoneNumber(schoolPhoneNumber);

		String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
		if(schoolAdministratorName != null)
			oSchoolEnrollment.setSchoolAdministratorName(schoolAdministratorName);

		Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
		if(yearStart != null)
			oSchoolEnrollment.setYearStart(yearStart);

		Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
		if(yearEnd != null)
			oSchoolEnrollment.setYearEnd(yearEnd);

		Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
		if(seasonStartDate != null)
			oSchoolEnrollment.setSeasonStartDate(seasonStartDate);

		Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
		if(seasonSummer != null)
			oSchoolEnrollment.setSeasonSummer(seasonSummer);

		Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
		if(seasonWinter != null)
			oSchoolEnrollment.setSeasonWinter(seasonWinter);

		Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
		if(yearEnrollmentFee != null)
			oSchoolEnrollment.setYearEnrollmentFee(yearEnrollmentFee);

		String seasonCompleteName = (String)solrDocument.get("seasonCompleteName_stored_string");
		if(seasonCompleteName != null)
			oSchoolEnrollment.setSeasonCompleteName(seasonCompleteName);

		Date sessionStartDate = (Date)solrDocument.get("sessionStartDate_stored_date");
		if(sessionStartDate != null)
			oSchoolEnrollment.setSessionStartDate(sessionStartDate);

		Date sessionEndDate = (Date)solrDocument.get("sessionEndDate_stored_date");
		if(sessionEndDate != null)
			oSchoolEnrollment.setSessionEndDate(sessionEndDate);

		String ageCompleteName = (String)solrDocument.get("ageCompleteName_stored_string");
		if(ageCompleteName != null)
			oSchoolEnrollment.setAgeCompleteName(ageCompleteName);

		Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
		if(ageStart != null)
			oSchoolEnrollment.setAgeStart(ageStart);

		Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
		if(ageEnd != null)
			oSchoolEnrollment.setAgeEnd(ageEnd);

		String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
		if(blockStartTime != null)
			oSchoolEnrollment.setBlockStartTime(blockStartTime);

		String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
		if(blockEndTime != null)
			oSchoolEnrollment.setBlockEndTime(blockEndTime);

		Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
		if(blockPricePerMonth != null)
			oSchoolEnrollment.setBlockPricePerMonth(blockPricePerMonth);

		Boolean blockSunday = (Boolean)solrDocument.get("blockSunday_stored_boolean");
		if(blockSunday != null)
			oSchoolEnrollment.setBlockSunday(blockSunday);

		Boolean blockMonday = (Boolean)solrDocument.get("blockMonday_stored_boolean");
		if(blockMonday != null)
			oSchoolEnrollment.setBlockMonday(blockMonday);

		Boolean blockTuesday = (Boolean)solrDocument.get("blockTuesday_stored_boolean");
		if(blockTuesday != null)
			oSchoolEnrollment.setBlockTuesday(blockTuesday);

		Boolean blockWednesday = (Boolean)solrDocument.get("blockWednesday_stored_boolean");
		if(blockWednesday != null)
			oSchoolEnrollment.setBlockWednesday(blockWednesday);

		Boolean blockThursday = (Boolean)solrDocument.get("blockThursday_stored_boolean");
		if(blockThursday != null)
			oSchoolEnrollment.setBlockThursday(blockThursday);

		Boolean blockFriday = (Boolean)solrDocument.get("blockFriday_stored_boolean");
		if(blockFriday != null)
			oSchoolEnrollment.setBlockFriday(blockFriday);

		Boolean blockSaturday = (Boolean)solrDocument.get("blockSaturday_stored_boolean");
		if(blockSaturday != null)
			oSchoolEnrollment.setBlockSaturday(blockSaturday);

		Double blockTotalPrice = (Double)solrDocument.get("blockTotalPrice_stored_double");
		if(blockTotalPrice != null)
			oSchoolEnrollment.setBlockTotalPrice(blockTotalPrice);

		String blockAdminName = (String)solrDocument.get("blockAdminName_stored_string");
		if(blockAdminName != null)
			oSchoolEnrollment.setBlockAdminName(blockAdminName);

		String blockShortName = (String)solrDocument.get("blockShortName_stored_string");
		if(blockShortName != null)
			oSchoolEnrollment.setBlockShortName(blockShortName);

		String blockCompleteName = (String)solrDocument.get("blockCompleteName_stored_string");
		if(blockCompleteName != null)
			oSchoolEnrollment.setBlockCompleteName(blockCompleteName);

		Boolean enrollmentApproved = (Boolean)solrDocument.get("enrollmentApproved_stored_boolean");
		if(enrollmentApproved != null)
			oSchoolEnrollment.setEnrollmentApproved(enrollmentApproved);

		Boolean enrollmentImmunizations = (Boolean)solrDocument.get("enrollmentImmunizations_stored_boolean");
		if(enrollmentImmunizations != null)
			oSchoolEnrollment.setEnrollmentImmunizations(enrollmentImmunizations);

		Boolean familyMarried = (Boolean)solrDocument.get("familyMarried_stored_boolean");
		if(familyMarried != null)
			oSchoolEnrollment.setFamilyMarried(familyMarried);

		Boolean familySeparated = (Boolean)solrDocument.get("familySeparated_stored_boolean");
		if(familySeparated != null)
			oSchoolEnrollment.setFamilySeparated(familySeparated);

		Boolean familyDivorced = (Boolean)solrDocument.get("familyDivorced_stored_boolean");
		if(familyDivorced != null)
			oSchoolEnrollment.setFamilyDivorced(familyDivorced);

		String familyAddress = (String)solrDocument.get("familyAddress_stored_string");
		if(familyAddress != null)
			oSchoolEnrollment.setFamilyAddress(familyAddress);

		String familyHowDoYouKnowTheSchool = (String)solrDocument.get("familyHowDoYouKnowTheSchool_stored_string");
		if(familyHowDoYouKnowTheSchool != null)
			oSchoolEnrollment.setFamilyHowDoYouKnowTheSchool(familyHowDoYouKnowTheSchool);

		String enrollmentSpecialConsiderations = (String)solrDocument.get("enrollmentSpecialConsiderations_stored_string");
		if(enrollmentSpecialConsiderations != null)
			oSchoolEnrollment.setEnrollmentSpecialConsiderations(enrollmentSpecialConsiderations);

		String childMedicalConditions = (String)solrDocument.get("childMedicalConditions_stored_string");
		if(childMedicalConditions != null)
			oSchoolEnrollment.setChildMedicalConditions(childMedicalConditions);

		String childPreviousSchoolsAttended = (String)solrDocument.get("childPreviousSchoolsAttended_stored_string");
		if(childPreviousSchoolsAttended != null)
			oSchoolEnrollment.setChildPreviousSchoolsAttended(childPreviousSchoolsAttended);

		String childDescription = (String)solrDocument.get("childDescription_stored_string");
		if(childDescription != null)
			oSchoolEnrollment.setChildDescription(childDescription);

		String childObjectives = (String)solrDocument.get("childObjectives_stored_string");
		if(childObjectives != null)
			oSchoolEnrollment.setChildObjectives(childObjectives);

		Boolean childPottyTrained = (Boolean)solrDocument.get("childPottyTrained_stored_boolean");
		if(childPottyTrained != null)
			oSchoolEnrollment.setChildPottyTrained(childPottyTrained);

		String enrollmentGroupName = (String)solrDocument.get("enrollmentGroupName_stored_string");
		if(enrollmentGroupName != null)
			oSchoolEnrollment.setEnrollmentGroupName(enrollmentGroupName);

		Boolean enrollmentPaymentEachMonth = (Boolean)solrDocument.get("enrollmentPaymentEachMonth_stored_boolean");
		if(enrollmentPaymentEachMonth != null)
			oSchoolEnrollment.setEnrollmentPaymentEachMonth(enrollmentPaymentEachMonth);

		Boolean enrollmentPaymentComplete = (Boolean)solrDocument.get("enrollmentPaymentComplete_stored_boolean");
		if(enrollmentPaymentComplete != null)
			oSchoolEnrollment.setEnrollmentPaymentComplete(enrollmentPaymentComplete);

		String enrollmentParentNames = (String)solrDocument.get("enrollmentParentNames_stored_string");
		if(enrollmentParentNames != null)
			oSchoolEnrollment.setEnrollmentParentNames(enrollmentParentNames);

		String enrollmentSignature1 = (String)solrDocument.get("enrollmentSignature1_stored_string");
		if(enrollmentSignature1 != null)
			oSchoolEnrollment.setEnrollmentSignature1(enrollmentSignature1);

		String enrollmentSignature2 = (String)solrDocument.get("enrollmentSignature2_stored_string");
		if(enrollmentSignature2 != null)
			oSchoolEnrollment.setEnrollmentSignature2(enrollmentSignature2);

		String enrollmentSignature3 = (String)solrDocument.get("enrollmentSignature3_stored_string");
		if(enrollmentSignature3 != null)
			oSchoolEnrollment.setEnrollmentSignature3(enrollmentSignature3);

		String enrollmentSignature4 = (String)solrDocument.get("enrollmentSignature4_stored_string");
		if(enrollmentSignature4 != null)
			oSchoolEnrollment.setEnrollmentSignature4(enrollmentSignature4);

		String enrollmentSignature5 = (String)solrDocument.get("enrollmentSignature5_stored_string");
		if(enrollmentSignature5 != null)
			oSchoolEnrollment.setEnrollmentSignature5(enrollmentSignature5);

		String enrollmentSignature6 = (String)solrDocument.get("enrollmentSignature6_stored_string");
		if(enrollmentSignature6 != null)
			oSchoolEnrollment.setEnrollmentSignature6(enrollmentSignature6);

		String enrollmentSignature7 = (String)solrDocument.get("enrollmentSignature7_stored_string");
		if(enrollmentSignature7 != null)
			oSchoolEnrollment.setEnrollmentSignature7(enrollmentSignature7);

		String enrollmentSignature8 = (String)solrDocument.get("enrollmentSignature8_stored_string");
		if(enrollmentSignature8 != null)
			oSchoolEnrollment.setEnrollmentSignature8(enrollmentSignature8);

		String enrollmentSignature9 = (String)solrDocument.get("enrollmentSignature9_stored_string");
		if(enrollmentSignature9 != null)
			oSchoolEnrollment.setEnrollmentSignature9(enrollmentSignature9);

		String enrollmentSignature10 = (String)solrDocument.get("enrollmentSignature10_stored_string");
		if(enrollmentSignature10 != null)
			oSchoolEnrollment.setEnrollmentSignature10(enrollmentSignature10);

		Date enrollmentDate1 = (Date)solrDocument.get("enrollmentDate1_stored_date");
		if(enrollmentDate1 != null)
			oSchoolEnrollment.setEnrollmentDate1(enrollmentDate1);

		Date enrollmentDate2 = (Date)solrDocument.get("enrollmentDate2_stored_date");
		if(enrollmentDate2 != null)
			oSchoolEnrollment.setEnrollmentDate2(enrollmentDate2);

		Date enrollmentDate3 = (Date)solrDocument.get("enrollmentDate3_stored_date");
		if(enrollmentDate3 != null)
			oSchoolEnrollment.setEnrollmentDate3(enrollmentDate3);

		Date enrollmentDate4 = (Date)solrDocument.get("enrollmentDate4_stored_date");
		if(enrollmentDate4 != null)
			oSchoolEnrollment.setEnrollmentDate4(enrollmentDate4);

		Date enrollmentDate5 = (Date)solrDocument.get("enrollmentDate5_stored_date");
		if(enrollmentDate5 != null)
			oSchoolEnrollment.setEnrollmentDate5(enrollmentDate5);

		Date enrollmentDate6 = (Date)solrDocument.get("enrollmentDate6_stored_date");
		if(enrollmentDate6 != null)
			oSchoolEnrollment.setEnrollmentDate6(enrollmentDate6);

		Date enrollmentDate7 = (Date)solrDocument.get("enrollmentDate7_stored_date");
		if(enrollmentDate7 != null)
			oSchoolEnrollment.setEnrollmentDate7(enrollmentDate7);

		Date enrollmentDate8 = (Date)solrDocument.get("enrollmentDate8_stored_date");
		if(enrollmentDate8 != null)
			oSchoolEnrollment.setEnrollmentDate8(enrollmentDate8);

		Date enrollmentDate9 = (Date)solrDocument.get("enrollmentDate9_stored_date");
		if(enrollmentDate9 != null)
			oSchoolEnrollment.setEnrollmentDate9(enrollmentDate9);

		Date enrollmentDate10 = (Date)solrDocument.get("enrollmentDate10_stored_date");
		if(enrollmentDate10 != null)
			oSchoolEnrollment.setEnrollmentDate10(enrollmentDate10);

		String enrollmentCompleteName = (String)solrDocument.get("enrollmentCompleteName_stored_string");
		if(enrollmentCompleteName != null)
			oSchoolEnrollment.setEnrollmentCompleteName(enrollmentCompleteName);

		super.storeCluster(solrDocument);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSchoolEnrollment() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		SchoolEnrollment original = (SchoolEnrollment)Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(original != null) {
			if(!Objects.equals(yearKey, original.getYearKey()))
				apiRequest.addVars("yearKey");
			if(!Objects.equals(blockKeys, original.getBlockKeys()))
				apiRequest.addVars("blockKeys");
			if(!Objects.equals(childKey, original.getChildKey()))
				apiRequest.addVars("childKey");
			if(!Objects.equals(momKeys, original.getMomKeys()))
				apiRequest.addVars("momKeys");
			if(!Objects.equals(dadKeys, original.getDadKeys()))
				apiRequest.addVars("dadKeys");
			if(!Objects.equals(guardianKeys, original.getGuardianKeys()))
				apiRequest.addVars("guardianKeys");
			if(!Objects.equals(paymentKeys, original.getPaymentKeys()))
				apiRequest.addVars("paymentKeys");
			if(!Objects.equals(childCompleteName, original.getChildCompleteName()))
				apiRequest.addVars("childCompleteName");
			if(!Objects.equals(childBirthDate, original.getChildBirthDate()))
				apiRequest.addVars("childBirthDate");
			if(!Objects.equals(schoolAddress, original.getSchoolAddress()))
				apiRequest.addVars("schoolAddress");
			if(!Objects.equals(enrollmentApproved, original.getEnrollmentApproved()))
				apiRequest.addVars("enrollmentApproved");
			if(!Objects.equals(enrollmentImmunizations, original.getEnrollmentImmunizations()))
				apiRequest.addVars("enrollmentImmunizations");
			if(!Objects.equals(familyMarried, original.getFamilyMarried()))
				apiRequest.addVars("familyMarried");
			if(!Objects.equals(familySeparated, original.getFamilySeparated()))
				apiRequest.addVars("familySeparated");
			if(!Objects.equals(familyDivorced, original.getFamilyDivorced()))
				apiRequest.addVars("familyDivorced");
			if(!Objects.equals(familyAddress, original.getFamilyAddress()))
				apiRequest.addVars("familyAddress");
			if(!Objects.equals(familyHowDoYouKnowTheSchool, original.getFamilyHowDoYouKnowTheSchool()))
				apiRequest.addVars("familyHowDoYouKnowTheSchool");
			if(!Objects.equals(enrollmentSpecialConsiderations, original.getEnrollmentSpecialConsiderations()))
				apiRequest.addVars("enrollmentSpecialConsiderations");
			if(!Objects.equals(childMedicalConditions, original.getChildMedicalConditions()))
				apiRequest.addVars("childMedicalConditions");
			if(!Objects.equals(childPreviousSchoolsAttended, original.getChildPreviousSchoolsAttended()))
				apiRequest.addVars("childPreviousSchoolsAttended");
			if(!Objects.equals(childDescription, original.getChildDescription()))
				apiRequest.addVars("childDescription");
			if(!Objects.equals(childObjectives, original.getChildObjectives()))
				apiRequest.addVars("childObjectives");
			if(!Objects.equals(childPottyTrained, original.getChildPottyTrained()))
				apiRequest.addVars("childPottyTrained");
			if(!Objects.equals(enrollmentGroupName, original.getEnrollmentGroupName()))
				apiRequest.addVars("enrollmentGroupName");
			if(!Objects.equals(enrollmentPaymentEachMonth, original.getEnrollmentPaymentEachMonth()))
				apiRequest.addVars("enrollmentPaymentEachMonth");
			if(!Objects.equals(enrollmentPaymentComplete, original.getEnrollmentPaymentComplete()))
				apiRequest.addVars("enrollmentPaymentComplete");
			if(!Objects.equals(enrollmentParentNames, original.getEnrollmentParentNames()))
				apiRequest.addVars("enrollmentParentNames");
			if(!Objects.equals(enrollmentSignature1, original.getEnrollmentSignature1()))
				apiRequest.addVars("enrollmentSignature1");
			if(!Objects.equals(enrollmentSignature2, original.getEnrollmentSignature2()))
				apiRequest.addVars("enrollmentSignature2");
			if(!Objects.equals(enrollmentSignature3, original.getEnrollmentSignature3()))
				apiRequest.addVars("enrollmentSignature3");
			if(!Objects.equals(enrollmentSignature4, original.getEnrollmentSignature4()))
				apiRequest.addVars("enrollmentSignature4");
			if(!Objects.equals(enrollmentSignature5, original.getEnrollmentSignature5()))
				apiRequest.addVars("enrollmentSignature5");
			if(!Objects.equals(enrollmentSignature6, original.getEnrollmentSignature6()))
				apiRequest.addVars("enrollmentSignature6");
			if(!Objects.equals(enrollmentSignature7, original.getEnrollmentSignature7()))
				apiRequest.addVars("enrollmentSignature7");
			if(!Objects.equals(enrollmentSignature8, original.getEnrollmentSignature8()))
				apiRequest.addVars("enrollmentSignature8");
			if(!Objects.equals(enrollmentSignature9, original.getEnrollmentSignature9()))
				apiRequest.addVars("enrollmentSignature9");
			if(!Objects.equals(enrollmentSignature10, original.getEnrollmentSignature10()))
				apiRequest.addVars("enrollmentSignature10");
			if(!Objects.equals(enrollmentDate1, original.getEnrollmentDate1()))
				apiRequest.addVars("enrollmentDate1");
			if(!Objects.equals(enrollmentDate2, original.getEnrollmentDate2()))
				apiRequest.addVars("enrollmentDate2");
			if(!Objects.equals(enrollmentDate3, original.getEnrollmentDate3()))
				apiRequest.addVars("enrollmentDate3");
			if(!Objects.equals(enrollmentDate4, original.getEnrollmentDate4()))
				apiRequest.addVars("enrollmentDate4");
			if(!Objects.equals(enrollmentDate5, original.getEnrollmentDate5()))
				apiRequest.addVars("enrollmentDate5");
			if(!Objects.equals(enrollmentDate6, original.getEnrollmentDate6()))
				apiRequest.addVars("enrollmentDate6");
			if(!Objects.equals(enrollmentDate7, original.getEnrollmentDate7()))
				apiRequest.addVars("enrollmentDate7");
			if(!Objects.equals(enrollmentDate8, original.getEnrollmentDate8()))
				apiRequest.addVars("enrollmentDate8");
			if(!Objects.equals(enrollmentDate9, original.getEnrollmentDate9()))
				apiRequest.addVars("enrollmentDate9");
			if(!Objects.equals(enrollmentDate10, original.getEnrollmentDate10()))
				apiRequest.addVars("enrollmentDate10");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), yearKey, blockKeys, childKey, momKeys, dadKeys, guardianKeys, paymentKeys, childCompleteName, childBirthDate, schoolAddress, enrollmentApproved, enrollmentImmunizations, familyMarried, familySeparated, familyDivorced, familyAddress, familyHowDoYouKnowTheSchool, enrollmentSpecialConsiderations, childMedicalConditions, childPreviousSchoolsAttended, childDescription, childObjectives, childPottyTrained, enrollmentGroupName, enrollmentPaymentEachMonth, enrollmentPaymentComplete, enrollmentParentNames, enrollmentSignature1, enrollmentSignature2, enrollmentSignature3, enrollmentSignature4, enrollmentSignature5, enrollmentSignature6, enrollmentSignature7, enrollmentSignature8, enrollmentSignature9, enrollmentSignature10, enrollmentDate1, enrollmentDate2, enrollmentDate3, enrollmentDate4, enrollmentDate5, enrollmentDate6, enrollmentDate7, enrollmentDate8, enrollmentDate9, enrollmentDate10);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolEnrollment))
			return false;
		SchoolEnrollment that = (SchoolEnrollment)o;
		return super.equals(o)
				&& Objects.equals( yearKey, that.yearKey )
				&& Objects.equals( blockKeys, that.blockKeys )
				&& Objects.equals( childKey, that.childKey )
				&& Objects.equals( momKeys, that.momKeys )
				&& Objects.equals( dadKeys, that.dadKeys )
				&& Objects.equals( guardianKeys, that.guardianKeys )
				&& Objects.equals( paymentKeys, that.paymentKeys )
				&& Objects.equals( childCompleteName, that.childCompleteName )
				&& Objects.equals( childBirthDate, that.childBirthDate )
				&& Objects.equals( schoolAddress, that.schoolAddress )
				&& Objects.equals( enrollmentApproved, that.enrollmentApproved )
				&& Objects.equals( enrollmentImmunizations, that.enrollmentImmunizations )
				&& Objects.equals( familyMarried, that.familyMarried )
				&& Objects.equals( familySeparated, that.familySeparated )
				&& Objects.equals( familyDivorced, that.familyDivorced )
				&& Objects.equals( familyAddress, that.familyAddress )
				&& Objects.equals( familyHowDoYouKnowTheSchool, that.familyHowDoYouKnowTheSchool )
				&& Objects.equals( enrollmentSpecialConsiderations, that.enrollmentSpecialConsiderations )
				&& Objects.equals( childMedicalConditions, that.childMedicalConditions )
				&& Objects.equals( childPreviousSchoolsAttended, that.childPreviousSchoolsAttended )
				&& Objects.equals( childDescription, that.childDescription )
				&& Objects.equals( childObjectives, that.childObjectives )
				&& Objects.equals( childPottyTrained, that.childPottyTrained )
				&& Objects.equals( enrollmentGroupName, that.enrollmentGroupName )
				&& Objects.equals( enrollmentPaymentEachMonth, that.enrollmentPaymentEachMonth )
				&& Objects.equals( enrollmentPaymentComplete, that.enrollmentPaymentComplete )
				&& Objects.equals( enrollmentParentNames, that.enrollmentParentNames )
				&& Objects.equals( enrollmentSignature1, that.enrollmentSignature1 )
				&& Objects.equals( enrollmentSignature2, that.enrollmentSignature2 )
				&& Objects.equals( enrollmentSignature3, that.enrollmentSignature3 )
				&& Objects.equals( enrollmentSignature4, that.enrollmentSignature4 )
				&& Objects.equals( enrollmentSignature5, that.enrollmentSignature5 )
				&& Objects.equals( enrollmentSignature6, that.enrollmentSignature6 )
				&& Objects.equals( enrollmentSignature7, that.enrollmentSignature7 )
				&& Objects.equals( enrollmentSignature8, that.enrollmentSignature8 )
				&& Objects.equals( enrollmentSignature9, that.enrollmentSignature9 )
				&& Objects.equals( enrollmentSignature10, that.enrollmentSignature10 )
				&& Objects.equals( enrollmentDate1, that.enrollmentDate1 )
				&& Objects.equals( enrollmentDate2, that.enrollmentDate2 )
				&& Objects.equals( enrollmentDate3, that.enrollmentDate3 )
				&& Objects.equals( enrollmentDate4, that.enrollmentDate4 )
				&& Objects.equals( enrollmentDate5, that.enrollmentDate5 )
				&& Objects.equals( enrollmentDate6, that.enrollmentDate6 )
				&& Objects.equals( enrollmentDate7, that.enrollmentDate7 )
				&& Objects.equals( enrollmentDate8, that.enrollmentDate8 )
				&& Objects.equals( enrollmentDate9, that.enrollmentDate9 )
				&& Objects.equals( enrollmentDate10, that.enrollmentDate10 );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolEnrollment { ");
		sb.append( "yearKey: " ).append(yearKey);
		sb.append( ", blockKeys: " ).append(blockKeys);
		sb.append( ", childKey: " ).append(childKey);
		sb.append( ", momKeys: " ).append(momKeys);
		sb.append( ", dadKeys: " ).append(dadKeys);
		sb.append( ", guardianKeys: " ).append(guardianKeys);
		sb.append( ", paymentKeys: " ).append(paymentKeys);
		sb.append( ", childCompleteName: \"" ).append(childCompleteName).append( "\"" );
		sb.append( ", childBirthDate: " ).append(childBirthDate);
		sb.append( ", schoolAddress: \"" ).append(schoolAddress).append( "\"" );
		sb.append( ", enrollmentApproved: " ).append(enrollmentApproved);
		sb.append( ", enrollmentImmunizations: " ).append(enrollmentImmunizations);
		sb.append( ", familyMarried: " ).append(familyMarried);
		sb.append( ", familySeparated: " ).append(familySeparated);
		sb.append( ", familyDivorced: " ).append(familyDivorced);
		sb.append( ", familyAddress: \"" ).append(familyAddress).append( "\"" );
		sb.append( ", familyHowDoYouKnowTheSchool: \"" ).append(familyHowDoYouKnowTheSchool).append( "\"" );
		sb.append( ", enrollmentSpecialConsiderations: \"" ).append(enrollmentSpecialConsiderations).append( "\"" );
		sb.append( ", childMedicalConditions: \"" ).append(childMedicalConditions).append( "\"" );
		sb.append( ", childPreviousSchoolsAttended: \"" ).append(childPreviousSchoolsAttended).append( "\"" );
		sb.append( ", childDescription: \"" ).append(childDescription).append( "\"" );
		sb.append( ", childObjectives: \"" ).append(childObjectives).append( "\"" );
		sb.append( ", childPottyTrained: " ).append(childPottyTrained);
		sb.append( ", enrollmentGroupName: \"" ).append(enrollmentGroupName).append( "\"" );
		sb.append( ", enrollmentPaymentEachMonth: " ).append(enrollmentPaymentEachMonth);
		sb.append( ", enrollmentPaymentComplete: " ).append(enrollmentPaymentComplete);
		sb.append( ", enrollmentParentNames: \"" ).append(enrollmentParentNames).append( "\"" );
		sb.append( ", enrollmentSignature1: \"" ).append(enrollmentSignature1).append( "\"" );
		sb.append( ", enrollmentSignature2: \"" ).append(enrollmentSignature2).append( "\"" );
		sb.append( ", enrollmentSignature3: \"" ).append(enrollmentSignature3).append( "\"" );
		sb.append( ", enrollmentSignature4: \"" ).append(enrollmentSignature4).append( "\"" );
		sb.append( ", enrollmentSignature5: \"" ).append(enrollmentSignature5).append( "\"" );
		sb.append( ", enrollmentSignature6: \"" ).append(enrollmentSignature6).append( "\"" );
		sb.append( ", enrollmentSignature7: \"" ).append(enrollmentSignature7).append( "\"" );
		sb.append( ", enrollmentSignature8: \"" ).append(enrollmentSignature8).append( "\"" );
		sb.append( ", enrollmentSignature9: \"" ).append(enrollmentSignature9).append( "\"" );
		sb.append( ", enrollmentSignature10: \"" ).append(enrollmentSignature10).append( "\"" );
		sb.append( ", enrollmentDate1: " ).append(enrollmentDate1);
		sb.append( ", enrollmentDate2: " ).append(enrollmentDate2);
		sb.append( ", enrollmentDate3: " ).append(enrollmentDate3);
		sb.append( ", enrollmentDate4: " ).append(enrollmentDate4);
		sb.append( ", enrollmentDate5: " ).append(enrollmentDate5);
		sb.append( ", enrollmentDate6: " ).append(enrollmentDate6);
		sb.append( ", enrollmentDate7: " ).append(enrollmentDate7);
		sb.append( ", enrollmentDate8: " ).append(enrollmentDate8);
		sb.append( ", enrollmentDate9: " ).append(enrollmentDate9);
		sb.append( ", enrollmentDate10: " ).append(enrollmentDate10);
		sb.append(" }");
		return sb.toString();
	}
}
