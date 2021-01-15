package org.computate.scolaire.enUS.enrollment;

import java.util.Arrays;
import org.apache.solr.common.util.SimpleOrderedMap;
import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import java.time.LocalTime;
import org.computate.scolaire.enUS.season.SchoolSeason;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.user.SiteUser;
import java.math.RoundingMode;
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.mom.SchoolMom;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
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
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.computate.scolaire.enUS.writer.AllWriter;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import java.lang.String;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.solr.client.solrj.SolrClient;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.time.temporal.ChronoUnit;
import org.computate.scolaire.enUS.dad.SchoolDad;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SchoolEnrollmentGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolEnrollment.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String SchoolEnrollment_AName = "an enrollment";
	public static final String SchoolEnrollment_This = "this ";
	public static final String SchoolEnrollment_ThisName = "this enrollment";
	public static final String SchoolEnrollment_A = "a ";
	public static final String SchoolEnrollment_TheName = "theenrollment";
	public static final String SchoolEnrollment_NameSingular = "enrollment";
	public static final String SchoolEnrollment_NamePlural = "enrollments";
	public static final String SchoolEnrollment_NameActual = "current enrollment";
	public static final String SchoolEnrollment_AllName = "all the enrollments";
	public static final String SchoolEnrollment_SearchAllNameBy = "search enrollments by ";
	public static final String SchoolEnrollment_Title = "enrollments";
	public static final String SchoolEnrollment_ThePluralName = "the enrollments";
	public static final String SchoolEnrollment_NoNameFound = "no enrollment found";
	public static final String SchoolEnrollment_NameVar = "enrollment";
	public static final String SchoolEnrollment_OfName = "of enrollment";
	public static final String SchoolEnrollment_ANameAdjective = "an enrollment";
	public static final String SchoolEnrollment_NameAdjectiveSingular = "enrollment";
	public static final String SchoolEnrollment_NameAdjectivePlural = "enrollments";
	public static final String SchoolEnrollment_Color = "blue-gray";
	public static final String SchoolEnrollment_IconGroup = "solid";
	public static final String SchoolEnrollment_IconName = "edit";
	public static final Integer SchoolEnrollment_Rows = 20;

	///////////////////
	// enrollmentKey //
	///////////////////

	/**	 The entity enrollmentKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long enrollmentKey;
	@JsonIgnore
	public Wrap<Long> enrollmentKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("enrollmentKey").o(enrollmentKey);

	/**	<br/> The entity enrollmentKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKey">Find the entity enrollmentKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentKey(Wrap<Long> c);

	public Long getEnrollmentKey() {
		return enrollmentKey;
	}

	public void setEnrollmentKey(Long enrollmentKey) {
		this.enrollmentKey = enrollmentKey;
		this.enrollmentKeyWrap.alreadyInitialized = true;
	}
	public void setEnrollmentKey(String o) {
		this.enrollmentKey = SchoolEnrollment.staticSetEnrollmentKey(siteRequest_, o);
		this.enrollmentKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrEnrollmentKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentKey(siteRequest_, SchoolEnrollment.staticSolrEnrollmentKey(siteRequest_, SchoolEnrollment.staticSetEnrollmentKey(siteRequest_, o)));
	}

	public Long solrEnrollmentKey() {
		return SchoolEnrollment.staticSolrEnrollmentKey(siteRequest_, enrollmentKey);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Find the entity yearKey in Solr</a>
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
		this.yearKey = SchoolEnrollment.staticSetYearKey(siteRequest_, o);
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetYearKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrYearKey(siteRequest_, SchoolEnrollment.staticSolrYearKey(siteRequest_, SchoolEnrollment.staticSetYearKey(siteRequest_, o)));
	}

	public Long solrYearKey() {
		return SchoolEnrollment.staticSolrYearKey(siteRequest_, yearKey);
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
		SchoolEnrollment s = (SchoolEnrollment)this;
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
						.a("id", classApiMethodMethod, "_yearKey_clear")
						.a("class", "yearKey_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_yearKey_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "year")
				.a("class", "valueObjectSuggest suggestYearKey w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setYearKey")
				.a("id", classApiMethodMethod, "_yearKey")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolEnrollmentYearKey($(this).val() ? searchSchoolYearFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'enrollmentKeys:" + pk + "'}", "], $('#listSchoolEnrollmentYearKey_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "YearKey ").f().sx(htmYearKey()).g("span");
		}
	}

	public void htmYearKey(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentYearKey").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/year?fq=enrollmentKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-orange w3-hover-orange ").f();
								e("i").a("class", "far fa-calendar-check ").f().g("i");
								sx("year");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate a year to this enrollment");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolEnrollmentYearKey_", classApiMethodMethod).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), SchoolYear.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), SchoolYear.ROLES)
										) {
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
												.a("id", classApiMethodMethod, "_yearKey_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolYearVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "yearKey')); });")
												.f().sx("add a year")
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
	// yearSearch //
	////////////////

	/**	 The entity yearSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolYear>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolYear> yearSearch = new SearchList<SchoolYear>();
	@JsonIgnore
	public Wrap<SearchList<SchoolYear>> yearSearchWrap = new Wrap<SearchList<SchoolYear>>().p(this).c(SearchList.class).var("yearSearch").o(yearSearch);

	/**	<br/> The entity yearSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolYear>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSearch">Find the entity yearSearch in Solr</a>
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

	/**	 The entity year_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SchoolYear year_;
	@JsonIgnore
	public Wrap<SchoolYear> year_Wrap = new Wrap<SchoolYear>().p(this).c(SchoolYear.class).var("year_").o(year_);

	/**	<br/> The entity year_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:year_">Find the entity year_ in Solr</a>
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

	/**	 The entity blockKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> blockKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> blockKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("blockKeys").o(blockKeys);

	/**	<br/> The entity blockKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKeys">Find the entity blockKeys in Solr</a>
	 * <br/>
	 * @param blockKeys is the entity already constructed. 
	 **/
	protected abstract void _blockKeys(List<Long> o);

	public List<Long> getBlockKeys() {
		return blockKeys;
	}

	public void setBlockKeys(List<Long> blockKeys) {
		this.blockKeys = blockKeys;
		this.blockKeysWrap.alreadyInitialized = true;
	}
	public void setBlockKeys(String o) {
		Long l = SchoolEnrollment.staticSetBlockKeys(siteRequest_, o);
		if(l != null)
			addBlockKeys(l);
		this.blockKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetBlockKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setBlockKeys(JsonArray objets) {
		blockKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlockKeys(o);
		}
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

	public static Long staticSolrBlockKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrBlockKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockKeys(siteRequest_, SchoolEnrollment.staticSolrBlockKeys(siteRequest_, SchoolEnrollment.staticSetBlockKeys(siteRequest_, o)));
	}

	public List<Long> solrBlockKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : blockKeys) {
			l.add(SchoolEnrollment.staticSolrBlockKeys(siteRequest_, o));
		}
		return l;
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
						.a("id", classApiMethodMethod, "_blockKeys_clear")
						.a("class", "blockKeys_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_blockKeys_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "blocks")
				.a("class", "valueObjectSuggest suggestBlockKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setBlockKeys")
				.a("id", classApiMethodMethod, "_blockKeys")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolEnrollmentBlockKeys($(this).val() ? searchSchoolBlockFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'enrollmentKeys:" + pk + "'}", "], $('#listSchoolEnrollmentBlockKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "BlockKeys ").f().sx(htmBlockKeys()).g("span");
		}
	}

	public void htmBlockKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentBlockKeys").f();
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
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), SchoolBlock.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), SchoolBlock.ROLES)
										) {
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
												.a("id", classApiMethodMethod, "_blockKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolBlockVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "blockKeys')); });")
												.f().sx("add a block")
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

	/////////////////
	// blockSearch //
	/////////////////

	/**	 The entity blockSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolBlock>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolBlock> blockSearch = new SearchList<SchoolBlock>();
	@JsonIgnore
	public Wrap<SearchList<SchoolBlock>> blockSearchWrap = new Wrap<SearchList<SchoolBlock>>().p(this).c(SearchList.class).var("blockSearch").o(blockSearch);

	/**	<br/> The entity blockSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolBlock>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSearch">Find the entity blockSearch in Solr</a>
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

	/**	 The entity blocks_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolBlock> blocks_;
	@JsonIgnore
	public Wrap<List<SchoolBlock>> blocks_Wrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("blocks_").o(blocks_);

	/**	<br/> The entity blocks_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blocks_">Find the entity blocks_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blocks_(Wrap<List<SchoolBlock>> c);

	public List<SchoolBlock> getBlocks_() {
		return blocks_;
	}

	public void setBlocks_(List<SchoolBlock> blocks_) {
		this.blocks_ = blocks_;
		this.blocks_Wrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetBlocks_(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity seasons_
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolSeason>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolSeason> seasons_ = new ArrayList<SchoolSeason>();
	@JsonIgnore
	public Wrap<List<SchoolSeason>> seasons_Wrap = new Wrap<List<SchoolSeason>>().p(this).c(List.class).var("seasons_").o(seasons_);

	/**	<br/> The entity seasons_
	 *  It is constructed before being initialized with the constructor by default List<SchoolSeason>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasons_">Find the entity seasons_ in Solr</a>
	 * <br/>
	 * @param seasons_ is the entity already constructed. 
	 **/
	protected abstract void _seasons_(List<SchoolSeason> c);

	public List<SchoolSeason> getSeasons_() {
		return seasons_;
	}

	public void setSeasons_(List<SchoolSeason> seasons_) {
		this.seasons_ = seasons_;
		this.seasons_Wrap.alreadyInitialized = true;
	}
	public static SchoolSeason staticSetSeasons_(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity block_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SchoolBlock block_;
	@JsonIgnore
	public Wrap<SchoolBlock> block_Wrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("block_").o(block_);

	/**	<br/> The entity block_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:block_">Find the entity block_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _block_(Wrap<SchoolBlock> c);

	public SchoolBlock getBlock_() {
		return block_;
	}

	public void setBlock_(SchoolBlock block_) {
		this.block_ = block_;
		this.block_Wrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetBlock_(SiteRequestEnUS siteRequest_, String o) {
		return null;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Find the entity schoolKey in Solr</a>
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
		this.schoolKey = SchoolEnrollment.staticSetSchoolKey(siteRequest_, o);
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSchoolKey(siteRequest_, SchoolEnrollment.staticSolrSchoolKey(siteRequest_, SchoolEnrollment.staticSetSchoolKey(siteRequest_, o)));
	}

	public Long solrSchoolKey() {
		return SchoolEnrollment.staticSolrSchoolKey(siteRequest_, schoolKey);
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
	// sessionKey //
	////////////////

	/**	 The entity sessionKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long sessionKey;
	@JsonIgnore
	public Wrap<Long> sessionKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("sessionKey").o(sessionKey);

	/**	<br/> The entity sessionKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKey">Find the entity sessionKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionKey(Wrap<Long> c);

	public Long getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(Long sessionKey) {
		this.sessionKey = sessionKey;
		this.sessionKeyWrap.alreadyInitialized = true;
	}
	public void setSessionKey(String o) {
		this.sessionKey = SchoolEnrollment.staticSetSessionKey(siteRequest_, o);
		this.sessionKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetSessionKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrSessionKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSessionKey(siteRequest_, SchoolEnrollment.staticSolrSessionKey(siteRequest_, SchoolEnrollment.staticSetSessionKey(siteRequest_, o)));
	}

	public Long solrSessionKey() {
		return SchoolEnrollment.staticSolrSessionKey(siteRequest_, sessionKey);
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

	/**	 The entity ageKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long ageKey;
	@JsonIgnore
	public Wrap<Long> ageKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("ageKey").o(ageKey);

	/**	<br/> The entity ageKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKey">Find the entity ageKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageKey(Wrap<Long> c);

	public Long getAgeKey() {
		return ageKey;
	}

	public void setAgeKey(Long ageKey) {
		this.ageKey = ageKey;
		this.ageKeyWrap.alreadyInitialized = true;
	}
	public void setAgeKey(String o) {
		this.ageKey = SchoolEnrollment.staticSetAgeKey(siteRequest_, o);
		this.ageKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetAgeKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrAgeKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrAgeKey(siteRequest_, SchoolEnrollment.staticSolrAgeKey(siteRequest_, SchoolEnrollment.staticSetAgeKey(siteRequest_, o)));
	}

	public Long solrAgeKey() {
		return SchoolEnrollment.staticSolrAgeKey(siteRequest_, ageKey);
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

	/**	 The entity blockKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long blockKey;
	@JsonIgnore
	public Wrap<Long> blockKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("blockKey").o(blockKey);

	/**	<br/> The entity blockKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKey">Find the entity blockKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockKey(Wrap<Long> c);

	public Long getBlockKey() {
		return blockKey;
	}

	public void setBlockKey(Long blockKey) {
		this.blockKey = blockKey;
		this.blockKeyWrap.alreadyInitialized = true;
	}
	public void setBlockKey(String o) {
		this.blockKey = SchoolEnrollment.staticSetBlockKey(siteRequest_, o);
		this.blockKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetBlockKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrBlockKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrBlockKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockKey(siteRequest_, SchoolEnrollment.staticSolrBlockKey(siteRequest_, SchoolEnrollment.staticSetBlockKey(siteRequest_, o)));
	}

	public Long solrBlockKey() {
		return SchoolEnrollment.staticSolrBlockKey(siteRequest_, blockKey);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childKey">Find the entity childKey in Solr</a>
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
		this.childKey = SchoolEnrollment.staticSetChildKey(siteRequest_, o);
		this.childKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetChildKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrChildKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrChildKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildKey(siteRequest_, SchoolEnrollment.staticSolrChildKey(siteRequest_, SchoolEnrollment.staticSetChildKey(siteRequest_, o)));
	}

	public Long solrChildKey() {
		return SchoolEnrollment.staticSolrChildKey(siteRequest_, childKey);
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
						.a("id", classApiMethodMethod, "_childKey_clear")
						.a("class", "childKey_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_childKey_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "children")
				.a("class", "valueObjectSuggest suggestChildKey w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setChildKey")
				.a("id", classApiMethodMethod, "_childKey")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolEnrollmentChildKey($(this).val() ? searchSchoolChildFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'enrollmentKeys:" + pk + "'}", "], $('#listSchoolEnrollmentChildKey_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "ChildKey ").f().sx(htmChildKey()).g("span");
		}
	}

	public void htmChildKey(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentChildKey").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/child?fq=enrollmentKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-orange w3-hover-orange ").f();
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
								{
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
												.a("id", classApiMethodMethod, "_childKey_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolChildVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "childKey')); });")
												.f().sx("add a child")
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
	// momKeys //
	/////////////

	/**	 The entity momKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> momKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> momKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("momKeys").o(momKeys);

	/**	<br/> The entity momKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:momKeys">Find the entity momKeys in Solr</a>
	 * <br/>
	 * @param momKeys is the entity already constructed. 
	 **/
	protected abstract void _momKeys(List<Long> o);

	public List<Long> getMomKeys() {
		return momKeys;
	}

	public void setMomKeys(List<Long> momKeys) {
		this.momKeys = momKeys;
		this.momKeysWrap.alreadyInitialized = true;
	}
	public void setMomKeys(String o) {
		Long l = SchoolEnrollment.staticSetMomKeys(siteRequest_, o);
		if(l != null)
			addMomKeys(l);
		this.momKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetMomKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setMomKeys(JsonArray objets) {
		momKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addMomKeys(o);
		}
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

	public static Long staticSolrMomKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrMomKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMomKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrMomKeys(siteRequest_, SchoolEnrollment.staticSolrMomKeys(siteRequest_, SchoolEnrollment.staticSetMomKeys(siteRequest_, o)));
	}

	public List<Long> solrMomKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : momKeys) {
			l.add(SchoolEnrollment.staticSolrMomKeys(siteRequest_, o));
		}
		return l;
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
						.a("id", classApiMethodMethod, "_momKeys_clear")
						.a("class", "momKeys_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_momKeys_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "moms")
				.a("class", "valueObjectSuggest suggestMomKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setMomKeys")
				.a("id", classApiMethodMethod, "_momKeys")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolEnrollmentMomKeys($(this).val() ? searchSchoolMomFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'enrollmentKeys:" + pk + "'}", "], $('#listSchoolEnrollmentMomKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "MomKeys ").f().sx(htmMomKeys()).g("span");
		}
	}

	public void htmMomKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentMomKeys").f();
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
								{
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
												.a("id", classApiMethodMethod, "_momKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolMomVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "momKeys')); });")
												.f().sx("add a mom")
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
	// dadKeys //
	/////////////

	/**	 The entity dadKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> dadKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> dadKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("dadKeys").o(dadKeys);

	/**	<br/> The entity dadKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadKeys">Find the entity dadKeys in Solr</a>
	 * <br/>
	 * @param dadKeys is the entity already constructed. 
	 **/
	protected abstract void _dadKeys(List<Long> o);

	public List<Long> getDadKeys() {
		return dadKeys;
	}

	public void setDadKeys(List<Long> dadKeys) {
		this.dadKeys = dadKeys;
		this.dadKeysWrap.alreadyInitialized = true;
	}
	public void setDadKeys(String o) {
		Long l = SchoolEnrollment.staticSetDadKeys(siteRequest_, o);
		if(l != null)
			addDadKeys(l);
		this.dadKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetDadKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setDadKeys(JsonArray objets) {
		dadKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addDadKeys(o);
		}
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

	public static Long staticSolrDadKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrDadKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDadKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrDadKeys(siteRequest_, SchoolEnrollment.staticSolrDadKeys(siteRequest_, SchoolEnrollment.staticSetDadKeys(siteRequest_, o)));
	}

	public List<Long> solrDadKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : dadKeys) {
			l.add(SchoolEnrollment.staticSolrDadKeys(siteRequest_, o));
		}
		return l;
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
						.a("id", classApiMethodMethod, "_dadKeys_clear")
						.a("class", "dadKeys_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_dadKeys_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "dads")
				.a("class", "valueObjectSuggest suggestDadKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setDadKeys")
				.a("id", classApiMethodMethod, "_dadKeys")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolEnrollmentDadKeys($(this).val() ? searchSchoolDadFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'enrollmentKeys:" + pk + "'}", "], $('#listSchoolEnrollmentDadKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "DadKeys ").f().sx(htmDadKeys()).g("span");
		}
	}

	public void htmDadKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentDadKeys").f();
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
								{
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
												.a("id", classApiMethodMethod, "_dadKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolDadVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "dadKeys')); });")
												.f().sx("add a dad")
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

	//////////////////
	// guardianKeys //
	//////////////////

	/**	 The entity guardianKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> guardianKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> guardianKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("guardianKeys").o(guardianKeys);

	/**	<br/> The entity guardianKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianKeys">Find the entity guardianKeys in Solr</a>
	 * <br/>
	 * @param guardianKeys is the entity already constructed. 
	 **/
	protected abstract void _guardianKeys(List<Long> o);

	public List<Long> getGuardianKeys() {
		return guardianKeys;
	}

	public void setGuardianKeys(List<Long> guardianKeys) {
		this.guardianKeys = guardianKeys;
		this.guardianKeysWrap.alreadyInitialized = true;
	}
	public void setGuardianKeys(String o) {
		Long l = SchoolEnrollment.staticSetGuardianKeys(siteRequest_, o);
		if(l != null)
			addGuardianKeys(l);
		this.guardianKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetGuardianKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setGuardianKeys(JsonArray objets) {
		guardianKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGuardianKeys(o);
		}
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

	public static Long staticSolrGuardianKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrGuardianKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqGuardianKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrGuardianKeys(siteRequest_, SchoolEnrollment.staticSolrGuardianKeys(siteRequest_, SchoolEnrollment.staticSetGuardianKeys(siteRequest_, o)));
	}

	public List<Long> solrGuardianKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : guardianKeys) {
			l.add(SchoolEnrollment.staticSolrGuardianKeys(siteRequest_, o));
		}
		return l;
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
						.a("id", classApiMethodMethod, "_guardianKeys_clear")
						.a("class", "guardianKeys_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_guardianKeys_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "guardians")
				.a("class", "valueObjectSuggest suggestGuardianKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setGuardianKeys")
				.a("id", classApiMethodMethod, "_guardianKeys")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolEnrollmentGuardianKeys($(this).val() ? searchSchoolGuardianFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'enrollmentKeys:" + pk + "'}", "], $('#listSchoolEnrollmentGuardianKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "GuardianKeys ").f().sx(htmGuardianKeys()).g("span");
		}
	}

	public void htmGuardianKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentGuardianKeys").f();
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
								{
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
												.a("id", classApiMethodMethod, "_guardianKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolGuardianVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "guardianKeys')); });")
												.f().sx("add a guardian")
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

	/////////////////
	// paymentKeys //
	/////////////////

	/**	 The entity paymentKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> paymentKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> paymentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("paymentKeys").o(paymentKeys);

	/**	<br/> The entity paymentKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentKeys">Find the entity paymentKeys in Solr</a>
	 * <br/>
	 * @param paymentKeys is the entity already constructed. 
	 **/
	protected abstract void _paymentKeys(List<Long> o);

	public List<Long> getPaymentKeys() {
		return paymentKeys;
	}

	public void setPaymentKeys(List<Long> paymentKeys) {
		this.paymentKeys = paymentKeys;
		this.paymentKeysWrap.alreadyInitialized = true;
	}
	public void setPaymentKeys(String o) {
		Long l = SchoolEnrollment.staticSetPaymentKeys(siteRequest_, o);
		if(l != null)
			addPaymentKeys(l);
		this.paymentKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetPaymentKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setPaymentKeys(JsonArray objets) {
		paymentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPaymentKeys(o);
		}
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

	public static Long staticSolrPaymentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrPaymentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrPaymentKeys(siteRequest_, SchoolEnrollment.staticSolrPaymentKeys(siteRequest_, SchoolEnrollment.staticSetPaymentKeys(siteRequest_, o)));
	}

	public List<Long> solrPaymentKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : paymentKeys) {
			l.add(SchoolEnrollment.staticSolrPaymentKeys(siteRequest_, o));
		}
		return l;
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
						.a("id", classApiMethodMethod, "_paymentKeys_clear")
						.a("class", "paymentKeys_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_paymentKeys_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "payments")
				.a("class", "valueObjectSuggest suggestPaymentKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setPaymentKeys")
				.a("id", classApiMethodMethod, "_paymentKeys")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolEnrollmentPaymentKeys($(this).val() ? searchSchoolPaymentFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'enrollmentKey:" + pk + "'}", "], $('#listSchoolEnrollmentPaymentKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "PaymentKeys ").f().sx(htmPaymentKeys()).g("span");
		}
	}

	public void htmPaymentKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentPaymentKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/payment?fq=enrollmentKey:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
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
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), SchoolPayment.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), SchoolPayment.ROLES)
										) {
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
												.a("id", classApiMethodMethod, "_paymentKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolPaymentVals({ enrollmentKey: \"", pk, "\" }, function() {}, function() { addError($('#", classApiMethodMethod, "paymentKeys')); });")
												.f().sx("add a payment")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentFormKey">Find the entity enrollmentFormKey in Solr</a>
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
		this.enrollmentFormKey = SchoolEnrollment.staticSetEnrollmentFormKey(siteRequest_, o);
		this.enrollmentFormKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentFormKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrEnrollmentFormKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentFormKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentFormKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentFormKey(siteRequest_, SchoolEnrollment.staticSolrEnrollmentFormKey(siteRequest_, SchoolEnrollment.staticSetEnrollmentFormKey(siteRequest_, o)));
	}

	public Long solrEnrollmentFormKey() {
		return SchoolEnrollment.staticSolrEnrollmentFormKey(siteRequest_, enrollmentFormKey);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userKeys">Find the entity userKeys in Solr</a>
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
		Long l = SchoolEnrollment.staticSetUserKeys(siteRequest_, o);
		if(l != null)
			addUserKeys(l);
		this.userKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetUserKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolEnrollment addUserKeys(Long...objets) {
		for(Long o : objets) {
			addUserKeys(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addUserKeys(Long o) {
		if(o != null && !userKeys.contains(o))
			this.userKeys.add(o);
		return (SchoolEnrollment)this;
	}
	public void setUserKeys(JsonArray objets) {
		userKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUserKeys(o);
		}
	}
	public SchoolEnrollment addUserKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUserKeys(p);
		}
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment userKeysInit() {
		if(!userKeysWrap.alreadyInitialized) {
			_userKeys(userKeys);
		}
		userKeysWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Long staticSolrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrUserKeys(siteRequest_, SchoolEnrollment.staticSolrUserKeys(siteRequest_, SchoolEnrollment.staticSetUserKeys(siteRequest_, o)));
	}

	public List<Long> solrUserKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : userKeys) {
			l.add(SchoolEnrollment.staticSolrUserKeys(siteRequest_, o));
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
		return "users";
	}

	public String htmTooltipUserKeys() {
		return null;
	}

	public String htmUserKeys() {
		return userKeys == null ? "" : StringEscapeUtils.escapeHtml4(strUserKeys());
	}

	public void inputUserKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
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
						.a("id", classApiMethodMethod, "_userKeys_clear")
						.a("class", "userKeys_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_userKeys_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "users")
				.a("class", "valueObjectSuggest suggestUserKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setUserKeys")
				.a("id", classApiMethodMethod, "_userKeys")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolEnrollmentUserKeys($(this).val() ? searchSiteUserFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'enrollmentKeys:" + pk + "'}", "], $('#listSchoolEnrollmentUserKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "UserKeys ").f().sx(htmUserKeys()).g("span");
		}
	}

	public void htmUserKeys(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentUserKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/user?fq=enrollmentKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-gray w3-hover-gray ").f();
								e("i").a("class", "far fa-user-cog ").f().g("i");
								sx("users");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate site users to this enrollment");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputUserKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolEnrollmentUserKeys_", classApiMethodMethod).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), SiteUser.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), SiteUser.ROLES)
										) {
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
												.a("id", classApiMethodMethod, "_userKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSiteUserVals({ enrollmentKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "userKeys')); });")
												.f().sx("add a site user")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Find the entity educationSort in Solr</a>
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
		this.educationSort = SchoolEnrollment.staticSetEducationSort(siteRequest_, o);
		this.educationSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetEducationSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEducationSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrEducationSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEducationSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEducationSort(siteRequest_, SchoolEnrollment.staticSolrEducationSort(siteRequest_, SchoolEnrollment.staticSetEducationSort(siteRequest_, o)));
	}

	public Integer solrEducationSort() {
		return SchoolEnrollment.staticSolrEducationSort(siteRequest_, educationSort);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Find the entity schoolSort in Solr</a>
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
		this.schoolSort = SchoolEnrollment.staticSetSchoolSort(siteRequest_, o);
		this.schoolSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSchoolSort(siteRequest_, SchoolEnrollment.staticSolrSchoolSort(siteRequest_, SchoolEnrollment.staticSetSchoolSort(siteRequest_, o)));
	}

	public Integer solrSchoolSort() {
		return SchoolEnrollment.staticSolrSchoolSort(siteRequest_, schoolSort);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSort">Find the entity yearSort in Solr</a>
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
		this.yearSort = SchoolEnrollment.staticSetYearSort(siteRequest_, o);
		this.yearSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrYearSort(siteRequest_, SchoolEnrollment.staticSolrYearSort(siteRequest_, SchoolEnrollment.staticSetYearSort(siteRequest_, o)));
	}

	public Integer solrYearSort() {
		return SchoolEnrollment.staticSolrYearSort(siteRequest_, yearSort);
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

	/**	 The entity seasonSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer seasonSort;
	@JsonIgnore
	public Wrap<Integer> seasonSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("seasonSort").o(seasonSort);

	/**	<br/> The entity seasonSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSort">Find the entity seasonSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonSort(Wrap<Integer> c);

	public Integer getSeasonSort() {
		return seasonSort;
	}

	public void setSeasonSort(Integer seasonSort) {
		this.seasonSort = seasonSort;
		this.seasonSortWrap.alreadyInitialized = true;
	}
	public void setSeasonSort(String o) {
		this.seasonSort = SchoolEnrollment.staticSetSeasonSort(siteRequest_, o);
		this.seasonSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSeasonSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSeasonSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSeasonSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSeasonSort(siteRequest_, SchoolEnrollment.staticSolrSeasonSort(siteRequest_, SchoolEnrollment.staticSetSeasonSort(siteRequest_, o)));
	}

	public Integer solrSeasonSort() {
		return SchoolEnrollment.staticSolrSeasonSort(siteRequest_, seasonSort);
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

	/**	 The entity sessionSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer sessionSort;
	@JsonIgnore
	public Wrap<Integer> sessionSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("sessionSort").o(sessionSort);

	/**	<br/> The entity sessionSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionSort">Find the entity sessionSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionSort(Wrap<Integer> c);

	public Integer getSessionSort() {
		return sessionSort;
	}

	public void setSessionSort(Integer sessionSort) {
		this.sessionSort = sessionSort;
		this.sessionSortWrap.alreadyInitialized = true;
	}
	public void setSessionSort(String o) {
		this.sessionSort = SchoolEnrollment.staticSetSessionSort(siteRequest_, o);
		this.sessionSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSessionSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSessionSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSessionSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSessionSort(siteRequest_, SchoolEnrollment.staticSolrSessionSort(siteRequest_, SchoolEnrollment.staticSetSessionSort(siteRequest_, o)));
	}

	public Integer solrSessionSort() {
		return SchoolEnrollment.staticSolrSessionSort(siteRequest_, sessionSort);
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

	/**	 The entity ageSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageSort;
	@JsonIgnore
	public Wrap<Integer> ageSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageSort").o(ageSort);

	/**	<br/> The entity ageSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageSort">Find the entity ageSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageSort(Wrap<Integer> c);

	public Integer getAgeSort() {
		return ageSort;
	}

	public void setAgeSort(Integer ageSort) {
		this.ageSort = ageSort;
		this.ageSortWrap.alreadyInitialized = true;
	}
	public void setAgeSort(String o) {
		this.ageSort = SchoolEnrollment.staticSetAgeSort(siteRequest_, o);
		this.ageSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetAgeSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAgeSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrAgeSort(siteRequest_, SchoolEnrollment.staticSolrAgeSort(siteRequest_, SchoolEnrollment.staticSetAgeSort(siteRequest_, o)));
	}

	public Integer solrAgeSort() {
		return SchoolEnrollment.staticSolrAgeSort(siteRequest_, ageSort);
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

	/**	 The entity childSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolChild>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolChild> childSearch = new SearchList<SchoolChild>();
	@JsonIgnore
	public Wrap<SearchList<SchoolChild>> childSearchWrap = new Wrap<SearchList<SchoolChild>>().p(this).c(SearchList.class).var("childSearch").o(childSearch);

	/**	<br/> The entity childSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolChild>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childSearch">Find the entity childSearch in Solr</a>
	 * <br/>
	 * @param childSearch is the entity already constructed. 
	 **/
	protected abstract void _childSearch(SearchList<SchoolChild> l);

	public SearchList<SchoolChild> getChildSearch() {
		return childSearch;
	}

	public void setChildSearch(SearchList<SchoolChild> childSearch) {
		this.childSearch = childSearch;
		this.childSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolChild> staticSetChildSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity child_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SchoolChild child_;
	@JsonIgnore
	public Wrap<SchoolChild> child_Wrap = new Wrap<SchoolChild>().p(this).c(SchoolChild.class).var("child_").o(child_);

	/**	<br/> The entity child_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:child_">Find the entity child_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _child_(Wrap<SchoolChild> c);

	public SchoolChild getChild_() {
		return child_;
	}

	public void setChild_(SchoolChild child_) {
		this.child_ = child_;
		this.child_Wrap.alreadyInitialized = true;
	}
	public static SchoolChild staticSetChild_(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity momSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolMom>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolMom> momSearch = new SearchList<SchoolMom>();
	@JsonIgnore
	public Wrap<SearchList<SchoolMom>> momSearchWrap = new Wrap<SearchList<SchoolMom>>().p(this).c(SearchList.class).var("momSearch").o(momSearch);

	/**	<br/> The entity momSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolMom>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:momSearch">Find the entity momSearch in Solr</a>
	 * <br/>
	 * @param momSearch is the entity already constructed. 
	 **/
	protected abstract void _momSearch(SearchList<SchoolMom> l);

	public SearchList<SchoolMom> getMomSearch() {
		return momSearch;
	}

	public void setMomSearch(SearchList<SchoolMom> momSearch) {
		this.momSearch = momSearch;
		this.momSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolMom> staticSetMomSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity moms
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolMom> moms;
	@JsonIgnore
	public Wrap<List<SchoolMom>> momsWrap = new Wrap<List<SchoolMom>>().p(this).c(List.class).var("moms").o(moms);

	/**	<br/> The entity moms
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:moms">Find the entity moms in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _moms(Wrap<List<SchoolMom>> c);

	public List<SchoolMom> getMoms() {
		return moms;
	}

	public void setMoms(List<SchoolMom> moms) {
		this.moms = moms;
		this.momsWrap.alreadyInitialized = true;
	}
	public static SchoolMom staticSetMoms(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity dadSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolDad>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolDad> dadSearch = new SearchList<SchoolDad>();
	@JsonIgnore
	public Wrap<SearchList<SchoolDad>> dadSearchWrap = new Wrap<SearchList<SchoolDad>>().p(this).c(SearchList.class).var("dadSearch").o(dadSearch);

	/**	<br/> The entity dadSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolDad>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadSearch">Find the entity dadSearch in Solr</a>
	 * <br/>
	 * @param dadSearch is the entity already constructed. 
	 **/
	protected abstract void _dadSearch(SearchList<SchoolDad> l);

	public SearchList<SchoolDad> getDadSearch() {
		return dadSearch;
	}

	public void setDadSearch(SearchList<SchoolDad> dadSearch) {
		this.dadSearch = dadSearch;
		this.dadSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolDad> staticSetDadSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity dads
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolDad> dads;
	@JsonIgnore
	public Wrap<List<SchoolDad>> dadsWrap = new Wrap<List<SchoolDad>>().p(this).c(List.class).var("dads").o(dads);

	/**	<br/> The entity dads
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dads">Find the entity dads in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dads(Wrap<List<SchoolDad>> c);

	public List<SchoolDad> getDads() {
		return dads;
	}

	public void setDads(List<SchoolDad> dads) {
		this.dads = dads;
		this.dadsWrap.alreadyInitialized = true;
	}
	public static SchoolDad staticSetDads(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity guardianSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolGuardian>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolGuardian> guardianSearch = new SearchList<SchoolGuardian>();
	@JsonIgnore
	public Wrap<SearchList<SchoolGuardian>> guardianSearchWrap = new Wrap<SearchList<SchoolGuardian>>().p(this).c(SearchList.class).var("guardianSearch").o(guardianSearch);

	/**	<br/> The entity guardianSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolGuardian>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianSearch">Find the entity guardianSearch in Solr</a>
	 * <br/>
	 * @param guardianSearch is the entity already constructed. 
	 **/
	protected abstract void _guardianSearch(SearchList<SchoolGuardian> l);

	public SearchList<SchoolGuardian> getGuardianSearch() {
		return guardianSearch;
	}

	public void setGuardianSearch(SearchList<SchoolGuardian> guardianSearch) {
		this.guardianSearch = guardianSearch;
		this.guardianSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolGuardian> staticSetGuardianSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity guardians
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolGuardian> guardians;
	@JsonIgnore
	public Wrap<List<SchoolGuardian>> guardiansWrap = new Wrap<List<SchoolGuardian>>().p(this).c(List.class).var("guardians").o(guardians);

	/**	<br/> The entity guardians
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardians">Find the entity guardians in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _guardians(Wrap<List<SchoolGuardian>> c);

	public List<SchoolGuardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<SchoolGuardian> guardians) {
		this.guardians = guardians;
		this.guardiansWrap.alreadyInitialized = true;
	}
	public static SchoolGuardian staticSetGuardians(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	///////////////
	// feeSearch //
	///////////////

	/**	 The entity feeSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolPayment>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolPayment> feeSearch = new SearchList<SchoolPayment>();
	@JsonIgnore
	public Wrap<SearchList<SchoolPayment>> feeSearchWrap = new Wrap<SearchList<SchoolPayment>>().p(this).c(SearchList.class).var("feeSearch").o(feeSearch);

	/**	<br/> The entity feeSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolPayment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:feeSearch">Find the entity feeSearch in Solr</a>
	 * <br/>
	 * @param feeSearch is the entity already constructed. 
	 **/
	protected abstract void _feeSearch(SearchList<SchoolPayment> l);

	public SearchList<SchoolPayment> getFeeSearch() {
		return feeSearch;
	}

	public void setFeeSearch(SearchList<SchoolPayment> feeSearch) {
		this.feeSearch = feeSearch;
		this.feeSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolPayment> staticSetFeeSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SchoolEnrollment feeSearchInit() {
		if(!feeSearchWrap.alreadyInitialized) {
			_feeSearch(feeSearch);
		}
		feeSearch.initDeepForClass(siteRequest_);
		feeSearchWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	///////////////////
	// paymentSearch //
	///////////////////

	/**	 The entity paymentSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolPayment>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolPayment> paymentSearch = new SearchList<SchoolPayment>();
	@JsonIgnore
	public Wrap<SearchList<SchoolPayment>> paymentSearchWrap = new Wrap<SearchList<SchoolPayment>>().p(this).c(SearchList.class).var("paymentSearch").o(paymentSearch);

	/**	<br/> The entity paymentSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolPayment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentSearch">Find the entity paymentSearch in Solr</a>
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
	protected SchoolEnrollment paymentSearchInit() {
		if(!paymentSearchWrap.alreadyInitialized) {
			_paymentSearch(paymentSearch);
		}
		paymentSearch.initDeepForClass(siteRequest_);
		paymentSearchWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	////////////////////
	// childFirstName //
	////////////////////

	/**	 The entity childFirstName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childFirstName;
	@JsonIgnore
	public Wrap<String> childFirstNameWrap = new Wrap<String>().p(this).c(String.class).var("childFirstName").o(childFirstName);

	/**	<br/> The entity childFirstName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childFirstName">Find the entity childFirstName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childFirstName(Wrap<String> c);

	public String getChildFirstName() {
		return childFirstName;
	}
	public void setChildFirstName(String o) {
		this.childFirstName = SchoolEnrollment.staticSetChildFirstName(siteRequest_, o);
		this.childFirstNameWrap.alreadyInitialized = true;
	}
	public static String staticSetChildFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment childFirstNameInit() {
		if(!childFirstNameWrap.alreadyInitialized) {
			_childFirstName(childFirstNameWrap);
			if(childFirstName == null)
				setChildFirstName(childFirstNameWrap.o);
		}
		childFirstNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrChildFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildFirstName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildFirstName(siteRequest_, SchoolEnrollment.staticSolrChildFirstName(siteRequest_, SchoolEnrollment.staticSetChildFirstName(siteRequest_, o)));
	}

	public String solrChildFirstName() {
		return SchoolEnrollment.staticSolrChildFirstName(siteRequest_, childFirstName);
	}

	public String strChildFirstName() {
		return childFirstName == null ? "" : childFirstName;
	}

	public String jsonChildFirstName() {
		return childFirstName == null ? "" : childFirstName;
	}

	public String nomAffichageChildFirstName() {
		return null;
	}

	public String htmTooltipChildFirstName() {
		return null;
	}

	public String htmChildFirstName() {
		return childFirstName == null ? "" : StringEscapeUtils.escapeHtml4(strChildFirstName());
	}

	/////////////////////////////
	// childFirstNamePreferred //
	/////////////////////////////

	/**	 The entity childFirstNamePreferred
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childFirstNamePreferred;
	@JsonIgnore
	public Wrap<String> childFirstNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("childFirstNamePreferred").o(childFirstNamePreferred);

	/**	<br/> The entity childFirstNamePreferred
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childFirstNamePreferred">Find the entity childFirstNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childFirstNamePreferred(Wrap<String> c);

	public String getChildFirstNamePreferred() {
		return childFirstNamePreferred;
	}
	public void setChildFirstNamePreferred(String o) {
		this.childFirstNamePreferred = SchoolEnrollment.staticSetChildFirstNamePreferred(siteRequest_, o);
		this.childFirstNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetChildFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment childFirstNamePreferredInit() {
		if(!childFirstNamePreferredWrap.alreadyInitialized) {
			_childFirstNamePreferred(childFirstNamePreferredWrap);
			if(childFirstNamePreferred == null)
				setChildFirstNamePreferred(childFirstNamePreferredWrap.o);
		}
		childFirstNamePreferredWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrChildFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildFirstNamePreferred(siteRequest_, SchoolEnrollment.staticSolrChildFirstNamePreferred(siteRequest_, SchoolEnrollment.staticSetChildFirstNamePreferred(siteRequest_, o)));
	}

	public String solrChildFirstNamePreferred() {
		return SchoolEnrollment.staticSolrChildFirstNamePreferred(siteRequest_, childFirstNamePreferred);
	}

	public String strChildFirstNamePreferred() {
		return childFirstNamePreferred == null ? "" : childFirstNamePreferred;
	}

	public String jsonChildFirstNamePreferred() {
		return childFirstNamePreferred == null ? "" : childFirstNamePreferred;
	}

	public String nomAffichageChildFirstNamePreferred() {
		return null;
	}

	public String htmTooltipChildFirstNamePreferred() {
		return null;
	}

	public String htmChildFirstNamePreferred() {
		return childFirstNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strChildFirstNamePreferred());
	}

	/////////////////////
	// childFamilyName //
	/////////////////////

	/**	 The entity childFamilyName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childFamilyName;
	@JsonIgnore
	public Wrap<String> childFamilyNameWrap = new Wrap<String>().p(this).c(String.class).var("childFamilyName").o(childFamilyName);

	/**	<br/> The entity childFamilyName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childFamilyName">Find the entity childFamilyName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childFamilyName(Wrap<String> c);

	public String getChildFamilyName() {
		return childFamilyName;
	}
	public void setChildFamilyName(String o) {
		this.childFamilyName = SchoolEnrollment.staticSetChildFamilyName(siteRequest_, o);
		this.childFamilyNameWrap.alreadyInitialized = true;
	}
	public static String staticSetChildFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment childFamilyNameInit() {
		if(!childFamilyNameWrap.alreadyInitialized) {
			_childFamilyName(childFamilyNameWrap);
			if(childFamilyName == null)
				setChildFamilyName(childFamilyNameWrap.o);
		}
		childFamilyNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrChildFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildFamilyName(siteRequest_, SchoolEnrollment.staticSolrChildFamilyName(siteRequest_, SchoolEnrollment.staticSetChildFamilyName(siteRequest_, o)));
	}

	public String solrChildFamilyName() {
		return SchoolEnrollment.staticSolrChildFamilyName(siteRequest_, childFamilyName);
	}

	public String strChildFamilyName() {
		return childFamilyName == null ? "" : childFamilyName;
	}

	public String jsonChildFamilyName() {
		return childFamilyName == null ? "" : childFamilyName;
	}

	public String nomAffichageChildFamilyName() {
		return null;
	}

	public String htmTooltipChildFamilyName() {
		return null;
	}

	public String htmChildFamilyName() {
		return childFamilyName == null ? "" : StringEscapeUtils.escapeHtml4(strChildFamilyName());
	}

	//////////////////
	// momFirstName //
	//////////////////

	/**	 The entity momFirstName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String momFirstName;
	@JsonIgnore
	public Wrap<String> momFirstNameWrap = new Wrap<String>().p(this).c(String.class).var("momFirstName").o(momFirstName);

	/**	<br/> The entity momFirstName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:momFirstName">Find the entity momFirstName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _momFirstName(Wrap<String> c);

	public String getMomFirstName() {
		return momFirstName;
	}
	public void setMomFirstName(String o) {
		this.momFirstName = SchoolEnrollment.staticSetMomFirstName(siteRequest_, o);
		this.momFirstNameWrap.alreadyInitialized = true;
	}
	public static String staticSetMomFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment momFirstNameInit() {
		if(!momFirstNameWrap.alreadyInitialized) {
			_momFirstName(momFirstNameWrap);
			if(momFirstName == null)
				setMomFirstName(momFirstNameWrap.o);
		}
		momFirstNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrMomFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrMomFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMomFirstName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrMomFirstName(siteRequest_, SchoolEnrollment.staticSolrMomFirstName(siteRequest_, SchoolEnrollment.staticSetMomFirstName(siteRequest_, o)));
	}

	public String solrMomFirstName() {
		return SchoolEnrollment.staticSolrMomFirstName(siteRequest_, momFirstName);
	}

	public String strMomFirstName() {
		return momFirstName == null ? "" : momFirstName;
	}

	public String jsonMomFirstName() {
		return momFirstName == null ? "" : momFirstName;
	}

	public String nomAffichageMomFirstName() {
		return null;
	}

	public String htmTooltipMomFirstName() {
		return null;
	}

	public String htmMomFirstName() {
		return momFirstName == null ? "" : StringEscapeUtils.escapeHtml4(strMomFirstName());
	}

	///////////////////////////
	// momFirstNamePreferred //
	///////////////////////////

	/**	 The entity momFirstNamePreferred
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String momFirstNamePreferred;
	@JsonIgnore
	public Wrap<String> momFirstNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("momFirstNamePreferred").o(momFirstNamePreferred);

	/**	<br/> The entity momFirstNamePreferred
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:momFirstNamePreferred">Find the entity momFirstNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _momFirstNamePreferred(Wrap<String> c);

	public String getMomFirstNamePreferred() {
		return momFirstNamePreferred;
	}
	public void setMomFirstNamePreferred(String o) {
		this.momFirstNamePreferred = SchoolEnrollment.staticSetMomFirstNamePreferred(siteRequest_, o);
		this.momFirstNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetMomFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment momFirstNamePreferredInit() {
		if(!momFirstNamePreferredWrap.alreadyInitialized) {
			_momFirstNamePreferred(momFirstNamePreferredWrap);
			if(momFirstNamePreferred == null)
				setMomFirstNamePreferred(momFirstNamePreferredWrap.o);
		}
		momFirstNamePreferredWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrMomFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrMomFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMomFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrMomFirstNamePreferred(siteRequest_, SchoolEnrollment.staticSolrMomFirstNamePreferred(siteRequest_, SchoolEnrollment.staticSetMomFirstNamePreferred(siteRequest_, o)));
	}

	public String solrMomFirstNamePreferred() {
		return SchoolEnrollment.staticSolrMomFirstNamePreferred(siteRequest_, momFirstNamePreferred);
	}

	public String strMomFirstNamePreferred() {
		return momFirstNamePreferred == null ? "" : momFirstNamePreferred;
	}

	public String jsonMomFirstNamePreferred() {
		return momFirstNamePreferred == null ? "" : momFirstNamePreferred;
	}

	public String nomAffichageMomFirstNamePreferred() {
		return null;
	}

	public String htmTooltipMomFirstNamePreferred() {
		return null;
	}

	public String htmMomFirstNamePreferred() {
		return momFirstNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strMomFirstNamePreferred());
	}

	//////////////////////////////
	// momCompleteNamePreferred //
	//////////////////////////////

	/**	 The entity momCompleteNamePreferred
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String momCompleteNamePreferred;
	@JsonIgnore
	public Wrap<String> momCompleteNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("momCompleteNamePreferred").o(momCompleteNamePreferred);

	/**	<br/> The entity momCompleteNamePreferred
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:momCompleteNamePreferred">Find the entity momCompleteNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _momCompleteNamePreferred(Wrap<String> c);

	public String getMomCompleteNamePreferred() {
		return momCompleteNamePreferred;
	}
	public void setMomCompleteNamePreferred(String o) {
		this.momCompleteNamePreferred = SchoolEnrollment.staticSetMomCompleteNamePreferred(siteRequest_, o);
		this.momCompleteNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetMomCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment momCompleteNamePreferredInit() {
		if(!momCompleteNamePreferredWrap.alreadyInitialized) {
			_momCompleteNamePreferred(momCompleteNamePreferredWrap);
			if(momCompleteNamePreferred == null)
				setMomCompleteNamePreferred(momCompleteNamePreferredWrap.o);
		}
		momCompleteNamePreferredWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrMomCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrMomCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMomCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrMomCompleteNamePreferred(siteRequest_, SchoolEnrollment.staticSolrMomCompleteNamePreferred(siteRequest_, SchoolEnrollment.staticSetMomCompleteNamePreferred(siteRequest_, o)));
	}

	public String solrMomCompleteNamePreferred() {
		return SchoolEnrollment.staticSolrMomCompleteNamePreferred(siteRequest_, momCompleteNamePreferred);
	}

	public String strMomCompleteNamePreferred() {
		return momCompleteNamePreferred == null ? "" : momCompleteNamePreferred;
	}

	public String jsonMomCompleteNamePreferred() {
		return momCompleteNamePreferred == null ? "" : momCompleteNamePreferred;
	}

	public String nomAffichageMomCompleteNamePreferred() {
		return null;
	}

	public String htmTooltipMomCompleteNamePreferred() {
		return null;
	}

	public String htmMomCompleteNamePreferred() {
		return momCompleteNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strMomCompleteNamePreferred());
	}

	//////////////////
	// dadFirstName //
	//////////////////

	/**	 The entity dadFirstName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String dadFirstName;
	@JsonIgnore
	public Wrap<String> dadFirstNameWrap = new Wrap<String>().p(this).c(String.class).var("dadFirstName").o(dadFirstName);

	/**	<br/> The entity dadFirstName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadFirstName">Find the entity dadFirstName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dadFirstName(Wrap<String> c);

	public String getDadFirstName() {
		return dadFirstName;
	}
	public void setDadFirstName(String o) {
		this.dadFirstName = SchoolEnrollment.staticSetDadFirstName(siteRequest_, o);
		this.dadFirstNameWrap.alreadyInitialized = true;
	}
	public static String staticSetDadFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment dadFirstNameInit() {
		if(!dadFirstNameWrap.alreadyInitialized) {
			_dadFirstName(dadFirstNameWrap);
			if(dadFirstName == null)
				setDadFirstName(dadFirstNameWrap.o);
		}
		dadFirstNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrDadFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrDadFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDadFirstName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrDadFirstName(siteRequest_, SchoolEnrollment.staticSolrDadFirstName(siteRequest_, SchoolEnrollment.staticSetDadFirstName(siteRequest_, o)));
	}

	public String solrDadFirstName() {
		return SchoolEnrollment.staticSolrDadFirstName(siteRequest_, dadFirstName);
	}

	public String strDadFirstName() {
		return dadFirstName == null ? "" : dadFirstName;
	}

	public String jsonDadFirstName() {
		return dadFirstName == null ? "" : dadFirstName;
	}

	public String nomAffichageDadFirstName() {
		return null;
	}

	public String htmTooltipDadFirstName() {
		return null;
	}

	public String htmDadFirstName() {
		return dadFirstName == null ? "" : StringEscapeUtils.escapeHtml4(strDadFirstName());
	}

	///////////////////////////
	// dadFirstNamePreferred //
	///////////////////////////

	/**	 The entity dadFirstNamePreferred
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String dadFirstNamePreferred;
	@JsonIgnore
	public Wrap<String> dadFirstNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("dadFirstNamePreferred").o(dadFirstNamePreferred);

	/**	<br/> The entity dadFirstNamePreferred
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadFirstNamePreferred">Find the entity dadFirstNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dadFirstNamePreferred(Wrap<String> c);

	public String getDadFirstNamePreferred() {
		return dadFirstNamePreferred;
	}
	public void setDadFirstNamePreferred(String o) {
		this.dadFirstNamePreferred = SchoolEnrollment.staticSetDadFirstNamePreferred(siteRequest_, o);
		this.dadFirstNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetDadFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment dadFirstNamePreferredInit() {
		if(!dadFirstNamePreferredWrap.alreadyInitialized) {
			_dadFirstNamePreferred(dadFirstNamePreferredWrap);
			if(dadFirstNamePreferred == null)
				setDadFirstNamePreferred(dadFirstNamePreferredWrap.o);
		}
		dadFirstNamePreferredWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrDadFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrDadFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDadFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrDadFirstNamePreferred(siteRequest_, SchoolEnrollment.staticSolrDadFirstNamePreferred(siteRequest_, SchoolEnrollment.staticSetDadFirstNamePreferred(siteRequest_, o)));
	}

	public String solrDadFirstNamePreferred() {
		return SchoolEnrollment.staticSolrDadFirstNamePreferred(siteRequest_, dadFirstNamePreferred);
	}

	public String strDadFirstNamePreferred() {
		return dadFirstNamePreferred == null ? "" : dadFirstNamePreferred;
	}

	public String jsonDadFirstNamePreferred() {
		return dadFirstNamePreferred == null ? "" : dadFirstNamePreferred;
	}

	public String nomAffichageDadFirstNamePreferred() {
		return null;
	}

	public String htmTooltipDadFirstNamePreferred() {
		return null;
	}

	public String htmDadFirstNamePreferred() {
		return dadFirstNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strDadFirstNamePreferred());
	}

	//////////////////////////////
	// dadCompleteNamePreferred //
	//////////////////////////////

	/**	 The entity dadCompleteNamePreferred
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String dadCompleteNamePreferred;
	@JsonIgnore
	public Wrap<String> dadCompleteNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("dadCompleteNamePreferred").o(dadCompleteNamePreferred);

	/**	<br/> The entity dadCompleteNamePreferred
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadCompleteNamePreferred">Find the entity dadCompleteNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dadCompleteNamePreferred(Wrap<String> c);

	public String getDadCompleteNamePreferred() {
		return dadCompleteNamePreferred;
	}
	public void setDadCompleteNamePreferred(String o) {
		this.dadCompleteNamePreferred = SchoolEnrollment.staticSetDadCompleteNamePreferred(siteRequest_, o);
		this.dadCompleteNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetDadCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment dadCompleteNamePreferredInit() {
		if(!dadCompleteNamePreferredWrap.alreadyInitialized) {
			_dadCompleteNamePreferred(dadCompleteNamePreferredWrap);
			if(dadCompleteNamePreferred == null)
				setDadCompleteNamePreferred(dadCompleteNamePreferredWrap.o);
		}
		dadCompleteNamePreferredWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrDadCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrDadCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDadCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrDadCompleteNamePreferred(siteRequest_, SchoolEnrollment.staticSolrDadCompleteNamePreferred(siteRequest_, SchoolEnrollment.staticSetDadCompleteNamePreferred(siteRequest_, o)));
	}

	public String solrDadCompleteNamePreferred() {
		return SchoolEnrollment.staticSolrDadCompleteNamePreferred(siteRequest_, dadCompleteNamePreferred);
	}

	public String strDadCompleteNamePreferred() {
		return dadCompleteNamePreferred == null ? "" : dadCompleteNamePreferred;
	}

	public String jsonDadCompleteNamePreferred() {
		return dadCompleteNamePreferred == null ? "" : dadCompleteNamePreferred;
	}

	public String nomAffichageDadCompleteNamePreferred() {
		return null;
	}

	public String htmTooltipDadCompleteNamePreferred() {
		return null;
	}

	public String htmDadCompleteNamePreferred() {
		return dadCompleteNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strDadCompleteNamePreferred());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childCompleteName">Find the entity childCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childCompleteName(Wrap<String> c);

	public String getChildCompleteName() {
		return childCompleteName;
	}
	public void setChildCompleteName(String o) {
		this.childCompleteName = SchoolEnrollment.staticSetChildCompleteName(siteRequest_, o);
		this.childCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetChildCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrChildCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildCompleteName(siteRequest_, SchoolEnrollment.staticSolrChildCompleteName(siteRequest_, SchoolEnrollment.staticSetChildCompleteName(siteRequest_, o)));
	}

	public String solrChildCompleteName() {
		return SchoolEnrollment.staticSolrChildCompleteName(siteRequest_, childCompleteName);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "r: EnfantNomComplet")
				.a("id", classApiMethodMethod, "_childCompleteName");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setChildCompleteName classSchoolEnrollment inputSchoolEnrollment", pk, "ChildCompleteName w3-input w3-border ");
					a("name", "setChildCompleteName");
				} else {
					a("class", "valueChildCompleteName w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "ChildCompleteName w3-input w3-border ");
					a("name", "childCompleteName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildCompleteName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childCompleteName')); }, function() { addError($('#", classApiMethodMethod, "_childCompleteName')); }); ");
				}
				a("value", strChildCompleteName())
			.fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "ChildCompleteName ").f().sx(htmChildCompleteName()).g("span");
		}
	}

	public void htmChildCompleteName(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentChildCompleteName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_childCompleteName").a("class", "").f().sx("r: EnfantNomComplet").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildCompleteName(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childCompleteName')); $('#", classApiMethodMethod, "_childCompleteName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildCompleteName', null, function() { addGlow($('#", classApiMethodMethod, "_childCompleteName')); }, function() { addError($('#", classApiMethodMethod, "_childCompleteName')); }); ")
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

	////////////////////////////////
	// childCompleteNamePreferred //
	////////////////////////////////

	/**	 The entity childCompleteNamePreferred
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childCompleteNamePreferred;
	@JsonIgnore
	public Wrap<String> childCompleteNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("childCompleteNamePreferred").o(childCompleteNamePreferred);

	/**	<br/> The entity childCompleteNamePreferred
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childCompleteNamePreferred">Find the entity childCompleteNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childCompleteNamePreferred(Wrap<String> c);

	public String getChildCompleteNamePreferred() {
		return childCompleteNamePreferred;
	}
	public void setChildCompleteNamePreferred(String o) {
		this.childCompleteNamePreferred = SchoolEnrollment.staticSetChildCompleteNamePreferred(siteRequest_, o);
		this.childCompleteNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetChildCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment childCompleteNamePreferredInit() {
		if(!childCompleteNamePreferredWrap.alreadyInitialized) {
			_childCompleteNamePreferred(childCompleteNamePreferredWrap);
			if(childCompleteNamePreferred == null)
				setChildCompleteNamePreferred(childCompleteNamePreferredWrap.o);
		}
		childCompleteNamePreferredWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrChildCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildCompleteNamePreferred(siteRequest_, SchoolEnrollment.staticSolrChildCompleteNamePreferred(siteRequest_, SchoolEnrollment.staticSetChildCompleteNamePreferred(siteRequest_, o)));
	}

	public String solrChildCompleteNamePreferred() {
		return SchoolEnrollment.staticSolrChildCompleteNamePreferred(siteRequest_, childCompleteNamePreferred);
	}

	public String strChildCompleteNamePreferred() {
		return childCompleteNamePreferred == null ? "" : childCompleteNamePreferred;
	}

	public String jsonChildCompleteNamePreferred() {
		return childCompleteNamePreferred == null ? "" : childCompleteNamePreferred;
	}

	public String nomAffichageChildCompleteNamePreferred() {
		return "r: enfant_";
	}

	public String htmTooltipChildCompleteNamePreferred() {
		return null;
	}

	public String htmChildCompleteNamePreferred() {
		return childCompleteNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strChildCompleteNamePreferred());
	}

	public void inputChildCompleteNamePreferred(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "r: enfant_")
				.a("id", classApiMethodMethod, "_childCompleteNamePreferred");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setChildCompleteNamePreferred classSchoolEnrollment inputSchoolEnrollment", pk, "ChildCompleteNamePreferred w3-input w3-border ");
					a("name", "setChildCompleteNamePreferred");
				} else {
					a("class", "valueChildCompleteNamePreferred w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "ChildCompleteNamePreferred w3-input w3-border ");
					a("name", "childCompleteNamePreferred");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildCompleteNamePreferred', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childCompleteNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_childCompleteNamePreferred')); }); ");
				}
				a("value", strChildCompleteNamePreferred())
			.fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "ChildCompleteNamePreferred ").f().sx(htmChildCompleteNamePreferred()).g("span");
		}
	}

	public void htmChildCompleteNamePreferred(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentChildCompleteNamePreferred").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_childCompleteNamePreferred").a("class", "").f().sx("r: enfant_").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildCompleteNamePreferred(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childCompleteNamePreferred')); $('#", classApiMethodMethod, "_childCompleteNamePreferred').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildCompleteNamePreferred', null, function() { addGlow($('#", classApiMethodMethod, "_childCompleteNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_childCompleteNamePreferred')); }); ")
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
	// childBirthDate //
	////////////////////

	/**	 The entity childBirthDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate childBirthDate;
	@JsonIgnore
	public Wrap<LocalDate> childBirthDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("childBirthDate").o(childBirthDate);

	/**	<br/> The entity childBirthDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childBirthDate">Find the entity childBirthDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childBirthDate(Wrap<LocalDate> c);

	public LocalDate getChildBirthDate() {
		return childBirthDate;
	}

	public void setChildBirthDate(LocalDate childBirthDate) {
		this.childBirthDate = childBirthDate;
		this.childBirthDateWrap.alreadyInitialized = true;
	}
	public void setChildBirthDate(Instant o) {
		this.childBirthDate = o == null ? null : LocalDate.from(o);
		this.childBirthDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setChildBirthDate(String o) {
		this.childBirthDate = SchoolEnrollment.staticSetChildBirthDate(siteRequest_, o);
		this.childBirthDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetChildBirthDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setChildBirthDate(Date o) {
		this.childBirthDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.childBirthDateWrap.alreadyInitialized = true;
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

	public static Date staticSolrChildBirthDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrChildBirthDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqChildBirthDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildBirthDate(siteRequest_, SchoolEnrollment.staticSolrChildBirthDate(siteRequest_, SchoolEnrollment.staticSetChildBirthDate(siteRequest_, o)));
	}

	public Date solrChildBirthDate() {
		return SchoolEnrollment.staticSolrChildBirthDate(siteRequest_, childBirthDate);
	}

	public String strChildBirthDate() {
		return childBirthDate == null ? "" : childBirthDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonChildBirthDate() {
		return childBirthDate == null ? "" : childBirthDate.format(DateTimeFormatter.ISO_DATE);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setChildBirthDate classSchoolEnrollment inputSchoolEnrollment", pk, "ChildBirthDate w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_childBirthDate")
					.a("value", childBirthDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(childBirthDate));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildBirthDate', s, function() { addGlow($('#", classApiMethodMethod, "_childBirthDate')); }, function() { addError($('#", classApiMethodMethod, "_childBirthDate')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "ChildBirthDate ").f().sx(htmChildBirthDate()).g("span");
		}
	}

	public void htmChildBirthDate(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentChildBirthDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_childBirthDate").a("class", "").f().sx("r: enfant_").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputChildBirthDate(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childBirthDate')); $('#", classApiMethodMethod, "_childBirthDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildBirthDate', null, function() { addGlow($('#", classApiMethodMethod, "_childBirthDate')); }, function() { addError($('#", classApiMethodMethod, "_childBirthDate')); }); ")
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
	// childBirthDateYear //
	////////////////////////

	/**	 The entity childBirthDateYear
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer childBirthDateYear;
	@JsonIgnore
	public Wrap<Integer> childBirthDateYearWrap = new Wrap<Integer>().p(this).c(Integer.class).var("childBirthDateYear").o(childBirthDateYear);

	/**	<br/> The entity childBirthDateYear
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childBirthDateYear">Find the entity childBirthDateYear in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childBirthDateYear(Wrap<Integer> c);

	public Integer getChildBirthDateYear() {
		return childBirthDateYear;
	}

	public void setChildBirthDateYear(Integer childBirthDateYear) {
		this.childBirthDateYear = childBirthDateYear;
		this.childBirthDateYearWrap.alreadyInitialized = true;
	}
	public void setChildBirthDateYear(String o) {
		this.childBirthDateYear = SchoolEnrollment.staticSetChildBirthDateYear(siteRequest_, o);
		this.childBirthDateYearWrap.alreadyInitialized = true;
	}
	public static Integer staticSetChildBirthDateYear(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolEnrollment childBirthDateYearInit() {
		if(!childBirthDateYearWrap.alreadyInitialized) {
			_childBirthDateYear(childBirthDateYearWrap);
			if(childBirthDateYear == null)
				setChildBirthDateYear(childBirthDateYearWrap.o);
		}
		childBirthDateYearWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Integer staticSolrChildBirthDateYear(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrChildBirthDateYear(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildBirthDateYear(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildBirthDateYear(siteRequest_, SchoolEnrollment.staticSolrChildBirthDateYear(siteRequest_, SchoolEnrollment.staticSetChildBirthDateYear(siteRequest_, o)));
	}

	public Integer solrChildBirthDateYear() {
		return SchoolEnrollment.staticSolrChildBirthDateYear(siteRequest_, childBirthDateYear);
	}

	public String strChildBirthDateYear() {
		return childBirthDateYear == null ? "" : childBirthDateYear.toString();
	}

	public String jsonChildBirthDateYear() {
		return childBirthDateYear == null ? "" : childBirthDateYear.toString();
	}

	public String nomAffichageChildBirthDateYear() {
		return null;
	}

	public String htmTooltipChildBirthDateYear() {
		return null;
	}

	public String htmChildBirthDateYear() {
		return childBirthDateYear == null ? "" : StringEscapeUtils.escapeHtml4(strChildBirthDateYear());
	}

	///////////////////////////////
	// childBirthDateMonthOfYear //
	///////////////////////////////

	/**	 The entity childBirthDateMonthOfYear
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childBirthDateMonthOfYear;
	@JsonIgnore
	public Wrap<String> childBirthDateMonthOfYearWrap = new Wrap<String>().p(this).c(String.class).var("childBirthDateMonthOfYear").o(childBirthDateMonthOfYear);

	/**	<br/> The entity childBirthDateMonthOfYear
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childBirthDateMonthOfYear">Find the entity childBirthDateMonthOfYear in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childBirthDateMonthOfYear(Wrap<String> c);

	public String getChildBirthDateMonthOfYear() {
		return childBirthDateMonthOfYear;
	}
	public void setChildBirthDateMonthOfYear(String o) {
		this.childBirthDateMonthOfYear = SchoolEnrollment.staticSetChildBirthDateMonthOfYear(siteRequest_, o);
		this.childBirthDateMonthOfYearWrap.alreadyInitialized = true;
	}
	public static String staticSetChildBirthDateMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment childBirthDateMonthOfYearInit() {
		if(!childBirthDateMonthOfYearWrap.alreadyInitialized) {
			_childBirthDateMonthOfYear(childBirthDateMonthOfYearWrap);
			if(childBirthDateMonthOfYear == null)
				setChildBirthDateMonthOfYear(childBirthDateMonthOfYearWrap.o);
		}
		childBirthDateMonthOfYearWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrChildBirthDateMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildBirthDateMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildBirthDateMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildBirthDateMonthOfYear(siteRequest_, SchoolEnrollment.staticSolrChildBirthDateMonthOfYear(siteRequest_, SchoolEnrollment.staticSetChildBirthDateMonthOfYear(siteRequest_, o)));
	}

	public String solrChildBirthDateMonthOfYear() {
		return SchoolEnrollment.staticSolrChildBirthDateMonthOfYear(siteRequest_, childBirthDateMonthOfYear);
	}

	public String strChildBirthDateMonthOfYear() {
		return childBirthDateMonthOfYear == null ? "" : childBirthDateMonthOfYear;
	}

	public String jsonChildBirthDateMonthOfYear() {
		return childBirthDateMonthOfYear == null ? "" : childBirthDateMonthOfYear;
	}

	public String nomAffichageChildBirthDateMonthOfYear() {
		return null;
	}

	public String htmTooltipChildBirthDateMonthOfYear() {
		return null;
	}

	public String htmChildBirthDateMonthOfYear() {
		return childBirthDateMonthOfYear == null ? "" : StringEscapeUtils.escapeHtml4(strChildBirthDateMonthOfYear());
	}

	/////////////////////////////
	// childBirthDateDayOfWeek //
	/////////////////////////////

	/**	 The entity childBirthDateDayOfWeek
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childBirthDateDayOfWeek;
	@JsonIgnore
	public Wrap<String> childBirthDateDayOfWeekWrap = new Wrap<String>().p(this).c(String.class).var("childBirthDateDayOfWeek").o(childBirthDateDayOfWeek);

	/**	<br/> The entity childBirthDateDayOfWeek
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childBirthDateDayOfWeek">Find the entity childBirthDateDayOfWeek in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childBirthDateDayOfWeek(Wrap<String> c);

	public String getChildBirthDateDayOfWeek() {
		return childBirthDateDayOfWeek;
	}
	public void setChildBirthDateDayOfWeek(String o) {
		this.childBirthDateDayOfWeek = SchoolEnrollment.staticSetChildBirthDateDayOfWeek(siteRequest_, o);
		this.childBirthDateDayOfWeekWrap.alreadyInitialized = true;
	}
	public static String staticSetChildBirthDateDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment childBirthDateDayOfWeekInit() {
		if(!childBirthDateDayOfWeekWrap.alreadyInitialized) {
			_childBirthDateDayOfWeek(childBirthDateDayOfWeekWrap);
			if(childBirthDateDayOfWeek == null)
				setChildBirthDateDayOfWeek(childBirthDateDayOfWeekWrap.o);
		}
		childBirthDateDayOfWeekWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrChildBirthDateDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildBirthDateDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildBirthDateDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildBirthDateDayOfWeek(siteRequest_, SchoolEnrollment.staticSolrChildBirthDateDayOfWeek(siteRequest_, SchoolEnrollment.staticSetChildBirthDateDayOfWeek(siteRequest_, o)));
	}

	public String solrChildBirthDateDayOfWeek() {
		return SchoolEnrollment.staticSolrChildBirthDateDayOfWeek(siteRequest_, childBirthDateDayOfWeek);
	}

	public String strChildBirthDateDayOfWeek() {
		return childBirthDateDayOfWeek == null ? "" : childBirthDateDayOfWeek;
	}

	public String jsonChildBirthDateDayOfWeek() {
		return childBirthDateDayOfWeek == null ? "" : childBirthDateDayOfWeek;
	}

	public String nomAffichageChildBirthDateDayOfWeek() {
		return null;
	}

	public String htmTooltipChildBirthDateDayOfWeek() {
		return null;
	}

	public String htmChildBirthDateDayOfWeek() {
		return childBirthDateDayOfWeek == null ? "" : StringEscapeUtils.escapeHtml4(strChildBirthDateDayOfWeek());
	}

	/////////////////////
	// childBirthMonth //
	/////////////////////

	/**	 The entity childBirthMonth
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer childBirthMonth;
	@JsonIgnore
	public Wrap<Integer> childBirthMonthWrap = new Wrap<Integer>().p(this).c(Integer.class).var("childBirthMonth").o(childBirthMonth);

	/**	<br/> The entity childBirthMonth
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childBirthMonth">Find the entity childBirthMonth in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childBirthMonth(Wrap<Integer> c);

	public Integer getChildBirthMonth() {
		return childBirthMonth;
	}

	public void setChildBirthMonth(Integer childBirthMonth) {
		this.childBirthMonth = childBirthMonth;
		this.childBirthMonthWrap.alreadyInitialized = true;
	}
	public void setChildBirthMonth(String o) {
		this.childBirthMonth = SchoolEnrollment.staticSetChildBirthMonth(siteRequest_, o);
		this.childBirthMonthWrap.alreadyInitialized = true;
	}
	public static Integer staticSetChildBirthMonth(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolEnrollment childBirthMonthInit() {
		if(!childBirthMonthWrap.alreadyInitialized) {
			_childBirthMonth(childBirthMonthWrap);
			if(childBirthMonth == null)
				setChildBirthMonth(childBirthMonthWrap.o);
		}
		childBirthMonthWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Integer staticSolrChildBirthMonth(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrChildBirthMonth(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildBirthMonth(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildBirthMonth(siteRequest_, SchoolEnrollment.staticSolrChildBirthMonth(siteRequest_, SchoolEnrollment.staticSetChildBirthMonth(siteRequest_, o)));
	}

	public Integer solrChildBirthMonth() {
		return SchoolEnrollment.staticSolrChildBirthMonth(siteRequest_, childBirthMonth);
	}

	public String strChildBirthMonth() {
		return childBirthMonth == null ? "" : childBirthMonth.toString();
	}

	public String jsonChildBirthMonth() {
		return childBirthMonth == null ? "" : childBirthMonth.toString();
	}

	public String nomAffichageChildBirthMonth() {
		return null;
	}

	public String htmTooltipChildBirthMonth() {
		return null;
	}

	public String htmChildBirthMonth() {
		return childBirthMonth == null ? "" : StringEscapeUtils.escapeHtml4(strChildBirthMonth());
	}

	///////////////////
	// childBirthDay //
	///////////////////

	/**	 The entity childBirthDay
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer childBirthDay;
	@JsonIgnore
	public Wrap<Integer> childBirthDayWrap = new Wrap<Integer>().p(this).c(Integer.class).var("childBirthDay").o(childBirthDay);

	/**	<br/> The entity childBirthDay
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childBirthDay">Find the entity childBirthDay in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childBirthDay(Wrap<Integer> c);

	public Integer getChildBirthDay() {
		return childBirthDay;
	}

	public void setChildBirthDay(Integer childBirthDay) {
		this.childBirthDay = childBirthDay;
		this.childBirthDayWrap.alreadyInitialized = true;
	}
	public void setChildBirthDay(String o) {
		this.childBirthDay = SchoolEnrollment.staticSetChildBirthDay(siteRequest_, o);
		this.childBirthDayWrap.alreadyInitialized = true;
	}
	public static Integer staticSetChildBirthDay(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolEnrollment childBirthDayInit() {
		if(!childBirthDayWrap.alreadyInitialized) {
			_childBirthDay(childBirthDayWrap);
			if(childBirthDay == null)
				setChildBirthDay(childBirthDayWrap.o);
		}
		childBirthDayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Integer staticSolrChildBirthDay(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrChildBirthDay(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildBirthDay(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildBirthDay(siteRequest_, SchoolEnrollment.staticSolrChildBirthDay(siteRequest_, SchoolEnrollment.staticSetChildBirthDay(siteRequest_, o)));
	}

	public Integer solrChildBirthDay() {
		return SchoolEnrollment.staticSolrChildBirthDay(siteRequest_, childBirthDay);
	}

	public String strChildBirthDay() {
		return childBirthDay == null ? "" : childBirthDay.toString();
	}

	public String jsonChildBirthDay() {
		return childBirthDay == null ? "" : childBirthDay.toString();
	}

	public String nomAffichageChildBirthDay() {
		return null;
	}

	public String htmTooltipChildBirthDay() {
		return null;
	}

	public String htmChildBirthDay() {
		return childBirthDay == null ? "" : StringEscapeUtils.escapeHtml4(strChildBirthDay());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Find the entity schoolName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolName(Wrap<String> c);

	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String o) {
		this.schoolName = SchoolEnrollment.staticSetSchoolName(siteRequest_, o);
		this.schoolNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSchoolName(siteRequest_, SchoolEnrollment.staticSolrSchoolName(siteRequest_, SchoolEnrollment.staticSetSchoolName(siteRequest_, o)));
	}

	public String solrSchoolName() {
		return SchoolEnrollment.staticSolrSchoolName(siteRequest_, schoolName);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Find the entity schoolCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolCompleteName(Wrap<String> c);

	public String getSchoolCompleteName() {
		return schoolCompleteName;
	}
	public void setSchoolCompleteName(String o) {
		this.schoolCompleteName = SchoolEnrollment.staticSetSchoolCompleteName(siteRequest_, o);
		this.schoolCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSchoolCompleteName(siteRequest_, SchoolEnrollment.staticSolrSchoolCompleteName(siteRequest_, SchoolEnrollment.staticSetSchoolCompleteName(siteRequest_, o)));
	}

	public String solrSchoolCompleteName() {
		return SchoolEnrollment.staticSolrSchoolCompleteName(siteRequest_, schoolCompleteName);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Find the entity schoolLocation in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolLocation(Wrap<String> c);

	public String getSchoolLocation() {
		return schoolLocation;
	}
	public void setSchoolLocation(String o) {
		this.schoolLocation = SchoolEnrollment.staticSetSchoolLocation(siteRequest_, o);
		this.schoolLocationWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSchoolLocation(siteRequest_, SchoolEnrollment.staticSolrSchoolLocation(siteRequest_, SchoolEnrollment.staticSetSchoolLocation(siteRequest_, o)));
	}

	public String solrSchoolLocation() {
		return SchoolEnrollment.staticSolrSchoolLocation(siteRequest_, schoolLocation);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Find the entity schoolAddress in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAddress(Wrap<String> c);

	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String o) {
		this.schoolAddress = SchoolEnrollment.staticSetSchoolAddress(siteRequest_, o);
		this.schoolAddressWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSchoolAddress(siteRequest_, SchoolEnrollment.staticSolrSchoolAddress(siteRequest_, SchoolEnrollment.staticSetSchoolAddress(siteRequest_, o)));
	}

	public String solrSchoolAddress() {
		return SchoolEnrollment.staticSolrSchoolAddress(siteRequest_, schoolAddress);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "address")
				.a("id", classApiMethodMethod, "_schoolAddress");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setSchoolAddress classSchoolEnrollment inputSchoolEnrollment", pk, "SchoolAddress w3-input w3-border ");
					a("name", "setSchoolAddress");
				} else {
					a("class", "valueSchoolAddress w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "SchoolAddress w3-input w3-border ");
					a("name", "schoolAddress");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSchoolAddress', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_schoolAddress')); }, function() { addError($('#", classApiMethodMethod, "_schoolAddress')); }); ");
				}
				a("value", strSchoolAddress())
			.fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "SchoolAddress ").f().sx(htmSchoolAddress()).g("span");
		}
	}

	public void htmSchoolAddress(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentSchoolAddress").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_schoolAddress").a("class", "").f().sx("address").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSchoolAddress(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_schoolAddress')); $('#", classApiMethodMethod, "_schoolAddress').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setSchoolAddress', null, function() { addGlow($('#", classApiMethodMethod, "_schoolAddress')); }, function() { addError($('#", classApiMethodMethod, "_schoolAddress')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Find the entity schoolPhoneNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolPhoneNumber(Wrap<String> c);

	public String getSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}
	public void setSchoolPhoneNumber(String o) {
		this.schoolPhoneNumber = SchoolEnrollment.staticSetSchoolPhoneNumber(siteRequest_, o);
		this.schoolPhoneNumberWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSchoolPhoneNumber(siteRequest_, SchoolEnrollment.staticSolrSchoolPhoneNumber(siteRequest_, SchoolEnrollment.staticSetSchoolPhoneNumber(siteRequest_, o)));
	}

	public String solrSchoolPhoneNumber() {
		return SchoolEnrollment.staticSolrSchoolPhoneNumber(siteRequest_, schoolPhoneNumber);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolForm">Find the entity schoolForm in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolForm(Wrap<String> c);

	public String getSchoolForm() {
		return schoolForm;
	}
	public void setSchoolForm(String o) {
		this.schoolForm = SchoolEnrollment.staticSetSchoolForm(siteRequest_, o);
		this.schoolFormWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment schoolFormInit() {
		if(!schoolFormWrap.alreadyInitialized) {
			_schoolForm(schoolFormWrap);
			if(schoolForm == null)
				setSchoolForm(schoolFormWrap.o);
		}
		schoolFormWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSchoolForm(siteRequest_, SchoolEnrollment.staticSolrSchoolForm(siteRequest_, SchoolEnrollment.staticSetSchoolForm(siteRequest_, o)));
	}

	public String solrSchoolForm() {
		return SchoolEnrollment.staticSolrSchoolForm(siteRequest_, schoolForm);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolNumber">Find the entity schoolNumber in Solr</a>
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
		this.schoolNumber = SchoolEnrollment.staticSetSchoolNumber(siteRequest_, o);
		this.schoolNumberWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolNumber(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolEnrollment schoolNumberInit() {
		if(!schoolNumberWrap.alreadyInitialized) {
			_schoolNumber(schoolNumberWrap);
			if(schoolNumber == null)
				setSchoolNumber(schoolNumberWrap.o);
		}
		schoolNumberWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Integer staticSolrSchoolNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSchoolNumber(siteRequest_, SchoolEnrollment.staticSolrSchoolNumber(siteRequest_, SchoolEnrollment.staticSetSchoolNumber(siteRequest_, o)));
	}

	public Integer solrSchoolNumber() {
		return SchoolEnrollment.staticSolrSchoolNumber(siteRequest_, schoolNumber);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Find the entity schoolAdministratorName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAdministratorName(Wrap<String> c);

	public String getSchoolAdministratorName() {
		return schoolAdministratorName;
	}
	public void setSchoolAdministratorName(String o) {
		this.schoolAdministratorName = SchoolEnrollment.staticSetSchoolAdministratorName(siteRequest_, o);
		this.schoolAdministratorNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSchoolAdministratorName(siteRequest_, SchoolEnrollment.staticSolrSchoolAdministratorName(siteRequest_, SchoolEnrollment.staticSetSchoolAdministratorName(siteRequest_, o)));
	}

	public String solrSchoolAdministratorName() {
		return SchoolEnrollment.staticSolrSchoolAdministratorName(siteRequest_, schoolAdministratorName);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Find the entity yearStart in Solr</a>
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
		this.yearStart = SchoolEnrollment.staticSetYearStart(siteRequest_, o);
		this.yearStartWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearStart(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearStart(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrYearStart(siteRequest_, SchoolEnrollment.staticSolrYearStart(siteRequest_, SchoolEnrollment.staticSetYearStart(siteRequest_, o)));
	}

	public Integer solrYearStart() {
		return SchoolEnrollment.staticSolrYearStart(siteRequest_, yearStart);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Find the entity yearEnd in Solr</a>
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
		this.yearEnd = SchoolEnrollment.staticSetYearEnd(siteRequest_, o);
		this.yearEndWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearEnd(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnd(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrYearEnd(siteRequest_, SchoolEnrollment.staticSolrYearEnd(siteRequest_, SchoolEnrollment.staticSetYearEnd(siteRequest_, o)));
	}

	public Integer solrYearEnd() {
		return SchoolEnrollment.staticSolrYearEnd(siteRequest_, yearEnd);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Find the entity seasonStartDate in Solr</a>
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
		this.seasonStartDate = SchoolEnrollment.staticSetSeasonStartDate(siteRequest_, o);
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSeasonStartDate(Date o) {
		this.seasonStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
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

	public static Date staticSolrSeasonStartDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSeasonStartDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSeasonStartDate(siteRequest_, SchoolEnrollment.staticSolrSeasonStartDate(siteRequest_, SchoolEnrollment.staticSetSeasonStartDate(siteRequest_, o)));
	}

	public Date solrSeasonStartDate() {
		return SchoolEnrollment.staticSolrSeasonStartDate(siteRequest_, seasonStartDate);
	}

	public String strSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ISO_DATE);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnrollmentFee">Find the entity yearEnrollmentFee in Solr</a>
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
		this.yearEnrollmentFee = SchoolEnrollment.staticSetYearEnrollmentFee(siteRequest_, o);
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
	protected SchoolEnrollment yearEnrollmentFeeInit() {
		if(!yearEnrollmentFeeWrap.alreadyInitialized) {
			_yearEnrollmentFee(yearEnrollmentFeeWrap);
			if(yearEnrollmentFee == null)
				setYearEnrollmentFee(yearEnrollmentFeeWrap.o);
		}
		yearEnrollmentFeeWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Double staticSolrYearEnrollmentFee(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrYearEnrollmentFee(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnrollmentFee(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrYearEnrollmentFee(siteRequest_, SchoolEnrollment.staticSolrYearEnrollmentFee(siteRequest_, SchoolEnrollment.staticSetYearEnrollmentFee(siteRequest_, o)));
	}

	public Double solrYearEnrollmentFee() {
		return SchoolEnrollment.staticSolrYearEnrollmentFee(siteRequest_, yearEnrollmentFee);
	}

	public String strYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : yearEnrollmentFee.setScale(2, RoundingMode.HALF_UP).toString();
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionStartDate">Find the entity sessionStartDate in Solr</a>
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
		this.sessionStartDate = SchoolEnrollment.staticSetSessionStartDate(siteRequest_, o);
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSessionStartDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionStartDate(Date o) {
		this.sessionStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionStartDateWrap.alreadyInitialized = true;
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

	public static Date staticSolrSessionStartDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionStartDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionStartDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSessionStartDate(siteRequest_, SchoolEnrollment.staticSolrSessionStartDate(siteRequest_, SchoolEnrollment.staticSetSessionStartDate(siteRequest_, o)));
	}

	public Date solrSessionStartDate() {
		return SchoolEnrollment.staticSolrSessionStartDate(siteRequest_, sessionStartDate);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionEndDate">Find the entity sessionEndDate in Solr</a>
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
		this.sessionEndDate = SchoolEnrollment.staticSetSessionEndDate(siteRequest_, o);
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSessionEndDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionEndDate(Date o) {
		this.sessionEndDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionEndDateWrap.alreadyInitialized = true;
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

	public static Date staticSolrSessionEndDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionEndDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionEndDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrSessionEndDate(siteRequest_, SchoolEnrollment.staticSolrSessionEndDate(siteRequest_, SchoolEnrollment.staticSetSessionEndDate(siteRequest_, o)));
	}

	public Date solrSessionEndDate() {
		return SchoolEnrollment.staticSolrSessionEndDate(siteRequest_, sessionEndDate);
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

	/////////////////////
	// ageCompleteName //
	/////////////////////

	/**	 The entity ageCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ageCompleteName;
	@JsonIgnore
	public Wrap<String> ageCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("ageCompleteName").o(ageCompleteName);

	/**	<br/> The entity ageCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageCompleteName">Find the entity ageCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageCompleteName(Wrap<String> c);

	public String getAgeCompleteName() {
		return ageCompleteName;
	}
	public void setAgeCompleteName(String o) {
		this.ageCompleteName = SchoolEnrollment.staticSetAgeCompleteName(siteRequest_, o);
		this.ageCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetAgeCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrAgeCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrAgeCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrAgeCompleteName(siteRequest_, SchoolEnrollment.staticSolrAgeCompleteName(siteRequest_, SchoolEnrollment.staticSetAgeCompleteName(siteRequest_, o)));
	}

	public String solrAgeCompleteName() {
		return SchoolEnrollment.staticSolrAgeCompleteName(siteRequest_, ageCompleteName);
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

	/**	 The entity ageStart
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageStart;
	@JsonIgnore
	public Wrap<Integer> ageStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageStart").o(ageStart);

	/**	<br/> The entity ageStart
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageStart">Find the entity ageStart in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageStart(Wrap<Integer> c);

	public Integer getAgeStart() {
		return ageStart;
	}

	public void setAgeStart(Integer ageStart) {
		this.ageStart = ageStart;
		this.ageStartWrap.alreadyInitialized = true;
	}
	public void setAgeStart(String o) {
		this.ageStart = SchoolEnrollment.staticSetAgeStart(siteRequest_, o);
		this.ageStartWrap.alreadyInitialized = true;
	}
	public static Integer staticSetAgeStart(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAgeStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeStart(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrAgeStart(siteRequest_, SchoolEnrollment.staticSolrAgeStart(siteRequest_, SchoolEnrollment.staticSetAgeStart(siteRequest_, o)));
	}

	public Integer solrAgeStart() {
		return SchoolEnrollment.staticSolrAgeStart(siteRequest_, ageStart);
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

	/**	 The entity ageEnd
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageEnd;
	@JsonIgnore
	public Wrap<Integer> ageEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ageEnd").o(ageEnd);

	/**	<br/> The entity ageEnd
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageEnd">Find the entity ageEnd in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageEnd(Wrap<Integer> c);

	public Integer getAgeEnd() {
		return ageEnd;
	}

	public void setAgeEnd(Integer ageEnd) {
		this.ageEnd = ageEnd;
		this.ageEndWrap.alreadyInitialized = true;
	}
	public void setAgeEnd(String o) {
		this.ageEnd = SchoolEnrollment.staticSetAgeEnd(siteRequest_, o);
		this.ageEndWrap.alreadyInitialized = true;
	}
	public static Integer staticSetAgeEnd(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAgeEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeEnd(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrAgeEnd(siteRequest_, SchoolEnrollment.staticSolrAgeEnd(siteRequest_, SchoolEnrollment.staticSetAgeEnd(siteRequest_, o)));
	}

	public Integer solrAgeEnd() {
		return SchoolEnrollment.staticSolrAgeEnd(siteRequest_, ageEnd);
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

	/**	 The entity blockStartTime
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected LocalTime blockStartTime;
	@JsonIgnore
	public Wrap<LocalTime> blockStartTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockStartTime").o(blockStartTime);

	/**	<br/> The entity blockStartTime
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockStartTime">Find the entity blockStartTime in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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
	public void setBlockStartTime(String o) {
		this.blockStartTime = SchoolEnrollment.staticSetBlockStartTime(siteRequest_, o);
		this.blockStartTimeWrap.alreadyInitialized = true;
	}
	public static LocalTime staticSetBlockStartTime(SiteRequestEnUS siteRequest_, String o) {
		try {
			return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
		} catch(Exception e) {
		}
		return null;
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

	public static String staticSolrBlockStartTime(SiteRequestEnUS siteRequest_, LocalTime o) {
		return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static String staticSolrStrBlockStartTime(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockStartTime(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockStartTime(siteRequest_, SchoolEnrollment.staticSolrBlockStartTime(siteRequest_, SchoolEnrollment.staticSetBlockStartTime(siteRequest_, o)));
	}

	public String solrBlockStartTime() {
		return SchoolEnrollment.staticSolrBlockStartTime(siteRequest_, blockStartTime);
	}

	public String strBlockStartTime() {
		return blockStartTime == null ? "" : blockStartTime.format(DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("en-US")));
	}

	public String jsonBlockStartTime() {
		return blockStartTime == null ? "" : blockStartTime.format(DateTimeFormatter.ISO_TIME);
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

	/**	 The entity blockEndTime
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected LocalTime blockEndTime;
	@JsonIgnore
	public Wrap<LocalTime> blockEndTimeWrap = new Wrap<LocalTime>().p(this).c(LocalTime.class).var("blockEndTime").o(blockEndTime);

	/**	<br/> The entity blockEndTime
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockEndTime">Find the entity blockEndTime in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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
	public void setBlockEndTime(String o) {
		this.blockEndTime = SchoolEnrollment.staticSetBlockEndTime(siteRequest_, o);
		this.blockEndTimeWrap.alreadyInitialized = true;
	}
	public static LocalTime staticSetBlockEndTime(SiteRequestEnUS siteRequest_, String o) {
		try {
			return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
		} catch(Exception e) {
		}
		return null;
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

	public static String staticSolrBlockEndTime(SiteRequestEnUS siteRequest_, LocalTime o) {
		return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static String staticSolrStrBlockEndTime(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockEndTime(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockEndTime(siteRequest_, SchoolEnrollment.staticSolrBlockEndTime(siteRequest_, SchoolEnrollment.staticSetBlockEndTime(siteRequest_, o)));
	}

	public String solrBlockEndTime() {
		return SchoolEnrollment.staticSolrBlockEndTime(siteRequest_, blockEndTime);
	}

	public String strBlockEndTime() {
		return blockEndTime == null ? "" : blockEndTime.format(DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("en-US")));
	}

	public String jsonBlockEndTime() {
		return blockEndTime == null ? "" : blockEndTime.format(DateTimeFormatter.ISO_TIME);
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

	/**	 The entity blockPricePerMonth
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal blockPricePerMonth;
	@JsonIgnore
	public Wrap<BigDecimal> blockPricePerMonthWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("blockPricePerMonth").o(blockPricePerMonth);

	/**	<br/> The entity blockPricePerMonth
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockPricePerMonth">Find the entity blockPricePerMonth in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockPricePerMonth(Wrap<BigDecimal> c);

	public BigDecimal getBlockPricePerMonth() {
		return blockPricePerMonth;
	}

	public void setBlockPricePerMonth(BigDecimal blockPricePerMonth) {
		this.blockPricePerMonth = blockPricePerMonth;
		this.blockPricePerMonthWrap.alreadyInitialized = true;
	}
	public void setBlockPricePerMonth(String o) {
		this.blockPricePerMonth = SchoolEnrollment.staticSetBlockPricePerMonth(siteRequest_, o);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetBlockPricePerMonth(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setBlockPricePerMonth(Double o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
	}
	public void setBlockPricePerMonth(Integer o) {
			this.blockPricePerMonth = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blockPricePerMonthWrap.alreadyInitialized = true;
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

	public static Double staticSolrBlockPricePerMonth(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrBlockPricePerMonth(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockPricePerMonth(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockPricePerMonth(siteRequest_, SchoolEnrollment.staticSolrBlockPricePerMonth(siteRequest_, SchoolEnrollment.staticSetBlockPricePerMonth(siteRequest_, o)));
	}

	public Double solrBlockPricePerMonth() {
		return SchoolEnrollment.staticSolrBlockPricePerMonth(siteRequest_, blockPricePerMonth);
	}

	public String strBlockPricePerMonth() {
		return blockPricePerMonth == null ? "" : blockPricePerMonth.setScale(2, RoundingMode.HALF_UP).toString();
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

	/**	 The entity blockSunday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockSunday;
	@JsonIgnore
	public Wrap<Boolean> blockSundayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockSunday").o(blockSunday);

	/**	<br/> The entity blockSunday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSunday">Find the entity blockSunday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockSunday(Wrap<Boolean> c);

	public Boolean getBlockSunday() {
		return blockSunday;
	}

	public void setBlockSunday(Boolean blockSunday) {
		this.blockSunday = blockSunday;
		this.blockSundayWrap.alreadyInitialized = true;
	}
	public void setBlockSunday(String o) {
		this.blockSunday = SchoolEnrollment.staticSetBlockSunday(siteRequest_, o);
		this.blockSundayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockSunday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockSunday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockSunday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockSunday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockSunday(siteRequest_, SchoolEnrollment.staticSolrBlockSunday(siteRequest_, SchoolEnrollment.staticSetBlockSunday(siteRequest_, o)));
	}

	public Boolean solrBlockSunday() {
		return SchoolEnrollment.staticSolrBlockSunday(siteRequest_, blockSunday);
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

	/**	 The entity blockMonday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockMonday;
	@JsonIgnore
	public Wrap<Boolean> blockMondayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockMonday").o(blockMonday);

	/**	<br/> The entity blockMonday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockMonday">Find the entity blockMonday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockMonday(Wrap<Boolean> c);

	public Boolean getBlockMonday() {
		return blockMonday;
	}

	public void setBlockMonday(Boolean blockMonday) {
		this.blockMonday = blockMonday;
		this.blockMondayWrap.alreadyInitialized = true;
	}
	public void setBlockMonday(String o) {
		this.blockMonday = SchoolEnrollment.staticSetBlockMonday(siteRequest_, o);
		this.blockMondayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockMonday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockMonday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockMonday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockMonday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockMonday(siteRequest_, SchoolEnrollment.staticSolrBlockMonday(siteRequest_, SchoolEnrollment.staticSetBlockMonday(siteRequest_, o)));
	}

	public Boolean solrBlockMonday() {
		return SchoolEnrollment.staticSolrBlockMonday(siteRequest_, blockMonday);
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

	/**	 The entity blockTuesday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockTuesday;
	@JsonIgnore
	public Wrap<Boolean> blockTuesdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockTuesday").o(blockTuesday);

	/**	<br/> The entity blockTuesday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTuesday">Find the entity blockTuesday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockTuesday(Wrap<Boolean> c);

	public Boolean getBlockTuesday() {
		return blockTuesday;
	}

	public void setBlockTuesday(Boolean blockTuesday) {
		this.blockTuesday = blockTuesday;
		this.blockTuesdayWrap.alreadyInitialized = true;
	}
	public void setBlockTuesday(String o) {
		this.blockTuesday = SchoolEnrollment.staticSetBlockTuesday(siteRequest_, o);
		this.blockTuesdayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockTuesday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockTuesday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockTuesday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockTuesday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockTuesday(siteRequest_, SchoolEnrollment.staticSolrBlockTuesday(siteRequest_, SchoolEnrollment.staticSetBlockTuesday(siteRequest_, o)));
	}

	public Boolean solrBlockTuesday() {
		return SchoolEnrollment.staticSolrBlockTuesday(siteRequest_, blockTuesday);
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

	/**	 The entity blockWednesday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockWednesday;
	@JsonIgnore
	public Wrap<Boolean> blockWednesdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockWednesday").o(blockWednesday);

	/**	<br/> The entity blockWednesday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockWednesday">Find the entity blockWednesday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockWednesday(Wrap<Boolean> c);

	public Boolean getBlockWednesday() {
		return blockWednesday;
	}

	public void setBlockWednesday(Boolean blockWednesday) {
		this.blockWednesday = blockWednesday;
		this.blockWednesdayWrap.alreadyInitialized = true;
	}
	public void setBlockWednesday(String o) {
		this.blockWednesday = SchoolEnrollment.staticSetBlockWednesday(siteRequest_, o);
		this.blockWednesdayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockWednesday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockWednesday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockWednesday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockWednesday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockWednesday(siteRequest_, SchoolEnrollment.staticSolrBlockWednesday(siteRequest_, SchoolEnrollment.staticSetBlockWednesday(siteRequest_, o)));
	}

	public Boolean solrBlockWednesday() {
		return SchoolEnrollment.staticSolrBlockWednesday(siteRequest_, blockWednesday);
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

	/**	 The entity blockThursday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockThursday;
	@JsonIgnore
	public Wrap<Boolean> blockThursdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockThursday").o(blockThursday);

	/**	<br/> The entity blockThursday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockThursday">Find the entity blockThursday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockThursday(Wrap<Boolean> c);

	public Boolean getBlockThursday() {
		return blockThursday;
	}

	public void setBlockThursday(Boolean blockThursday) {
		this.blockThursday = blockThursday;
		this.blockThursdayWrap.alreadyInitialized = true;
	}
	public void setBlockThursday(String o) {
		this.blockThursday = SchoolEnrollment.staticSetBlockThursday(siteRequest_, o);
		this.blockThursdayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockThursday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockThursday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockThursday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockThursday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockThursday(siteRequest_, SchoolEnrollment.staticSolrBlockThursday(siteRequest_, SchoolEnrollment.staticSetBlockThursday(siteRequest_, o)));
	}

	public Boolean solrBlockThursday() {
		return SchoolEnrollment.staticSolrBlockThursday(siteRequest_, blockThursday);
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

	/**	 The entity blockFriday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockFriday;
	@JsonIgnore
	public Wrap<Boolean> blockFridayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockFriday").o(blockFriday);

	/**	<br/> The entity blockFriday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockFriday">Find the entity blockFriday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockFriday(Wrap<Boolean> c);

	public Boolean getBlockFriday() {
		return blockFriday;
	}

	public void setBlockFriday(Boolean blockFriday) {
		this.blockFriday = blockFriday;
		this.blockFridayWrap.alreadyInitialized = true;
	}
	public void setBlockFriday(String o) {
		this.blockFriday = SchoolEnrollment.staticSetBlockFriday(siteRequest_, o);
		this.blockFridayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockFriday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockFriday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockFriday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockFriday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockFriday(siteRequest_, SchoolEnrollment.staticSolrBlockFriday(siteRequest_, SchoolEnrollment.staticSetBlockFriday(siteRequest_, o)));
	}

	public Boolean solrBlockFriday() {
		return SchoolEnrollment.staticSolrBlockFriday(siteRequest_, blockFriday);
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

	/**	 The entity blockSaturday
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blockSaturday;
	@JsonIgnore
	public Wrap<Boolean> blockSaturdayWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("blockSaturday").o(blockSaturday);

	/**	<br/> The entity blockSaturday
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSaturday">Find the entity blockSaturday in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockSaturday(Wrap<Boolean> c);

	public Boolean getBlockSaturday() {
		return blockSaturday;
	}

	public void setBlockSaturday(Boolean blockSaturday) {
		this.blockSaturday = blockSaturday;
		this.blockSaturdayWrap.alreadyInitialized = true;
	}
	public void setBlockSaturday(String o) {
		this.blockSaturday = SchoolEnrollment.staticSetBlockSaturday(siteRequest_, o);
		this.blockSaturdayWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetBlockSaturday(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlockSaturday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlockSaturday(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockSaturday(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockSaturday(siteRequest_, SchoolEnrollment.staticSolrBlockSaturday(siteRequest_, SchoolEnrollment.staticSetBlockSaturday(siteRequest_, o)));
	}

	public Boolean solrBlockSaturday() {
		return SchoolEnrollment.staticSolrBlockSaturday(siteRequest_, blockSaturday);
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

	/**	 The entity blockTotalPrice
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal blockTotalPrice;
	@JsonIgnore
	public Wrap<BigDecimal> blockTotalPriceWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("blockTotalPrice").o(blockTotalPrice);

	/**	<br/> The entity blockTotalPrice
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockTotalPrice">Find the entity blockTotalPrice in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockTotalPrice(Wrap<BigDecimal> c);

	public BigDecimal getBlockTotalPrice() {
		return blockTotalPrice;
	}

	public void setBlockTotalPrice(BigDecimal blockTotalPrice) {
		this.blockTotalPrice = blockTotalPrice;
		this.blockTotalPriceWrap.alreadyInitialized = true;
	}
	public void setBlockTotalPrice(String o) {
		this.blockTotalPrice = SchoolEnrollment.staticSetBlockTotalPrice(siteRequest_, o);
		this.blockTotalPriceWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetBlockTotalPrice(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setBlockTotalPrice(Double o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blockTotalPriceWrap.alreadyInitialized = true;
	}
	public void setBlockTotalPrice(Integer o) {
			this.blockTotalPrice = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blockTotalPriceWrap.alreadyInitialized = true;
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

	public static Double staticSolrBlockTotalPrice(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrBlockTotalPrice(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockTotalPrice(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockTotalPrice(siteRequest_, SchoolEnrollment.staticSolrBlockTotalPrice(siteRequest_, SchoolEnrollment.staticSetBlockTotalPrice(siteRequest_, o)));
	}

	public Double solrBlockTotalPrice() {
		return SchoolEnrollment.staticSolrBlockTotalPrice(siteRequest_, blockTotalPrice);
	}

	public String strBlockTotalPrice() {
		return blockTotalPrice == null ? "" : blockTotalPrice.setScale(2, RoundingMode.HALF_UP).toString();
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

	/**	 The entity blockAdminName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blockAdminName;
	@JsonIgnore
	public Wrap<String> blockAdminNameWrap = new Wrap<String>().p(this).c(String.class).var("blockAdminName").o(blockAdminName);

	/**	<br/> The entity blockAdminName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockAdminName">Find the entity blockAdminName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockAdminName(Wrap<String> c);

	public String getBlockAdminName() {
		return blockAdminName;
	}
	public void setBlockAdminName(String o) {
		this.blockAdminName = SchoolEnrollment.staticSetBlockAdminName(siteRequest_, o);
		this.blockAdminNameWrap.alreadyInitialized = true;
	}
	public static String staticSetBlockAdminName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrBlockAdminName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrBlockAdminName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockAdminName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockAdminName(siteRequest_, SchoolEnrollment.staticSolrBlockAdminName(siteRequest_, SchoolEnrollment.staticSetBlockAdminName(siteRequest_, o)));
	}

	public String solrBlockAdminName() {
		return SchoolEnrollment.staticSolrBlockAdminName(siteRequest_, blockAdminName);
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

	/**	 The entity blockShortName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blockShortName;
	@JsonIgnore
	public Wrap<String> blockShortNameWrap = new Wrap<String>().p(this).c(String.class).var("blockShortName").o(blockShortName);

	/**	<br/> The entity blockShortName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockShortName">Find the entity blockShortName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockShortName(Wrap<String> c);

	public String getBlockShortName() {
		return blockShortName;
	}
	public void setBlockShortName(String o) {
		this.blockShortName = SchoolEnrollment.staticSetBlockShortName(siteRequest_, o);
		this.blockShortNameWrap.alreadyInitialized = true;
	}
	public static String staticSetBlockShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrBlockShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrBlockShortName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockShortName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockShortName(siteRequest_, SchoolEnrollment.staticSolrBlockShortName(siteRequest_, SchoolEnrollment.staticSetBlockShortName(siteRequest_, o)));
	}

	public String solrBlockShortName() {
		return SchoolEnrollment.staticSolrBlockShortName(siteRequest_, blockShortName);
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

	/**	 The entity blockCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blockCompleteName;
	@JsonIgnore
	public Wrap<String> blockCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("blockCompleteName").o(blockCompleteName);

	/**	<br/> The entity blockCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockCompleteName">Find the entity blockCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockCompleteName(Wrap<String> c);

	public String getBlockCompleteName() {
		return blockCompleteName;
	}
	public void setBlockCompleteName(String o) {
		this.blockCompleteName = SchoolEnrollment.staticSetBlockCompleteName(siteRequest_, o);
		this.blockCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlockCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrBlockCompleteName(siteRequest_, SchoolEnrollment.staticSolrBlockCompleteName(siteRequest_, SchoolEnrollment.staticSetBlockCompleteName(siteRequest_, o)));
	}

	public String solrBlockCompleteName() {
		return SchoolEnrollment.staticSolrBlockCompleteName(siteRequest_, blockCompleteName);
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

	/**	 The entity enrollmentApproved
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean enrollmentApproved;
	@JsonIgnore
	public Wrap<Boolean> enrollmentApprovedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentApproved").o(enrollmentApproved);

	/**	<br/> The entity enrollmentApproved
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentApproved">Find the entity enrollmentApproved in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentApproved(Wrap<Boolean> c);

	public Boolean getEnrollmentApproved() {
		return enrollmentApproved;
	}

	public void setEnrollmentApproved(Boolean enrollmentApproved) {
		this.enrollmentApproved = enrollmentApproved;
		this.enrollmentApprovedWrap.alreadyInitialized = true;
	}
	public void setEnrollmentApproved(String o) {
		this.enrollmentApproved = SchoolEnrollment.staticSetEnrollmentApproved(siteRequest_, o);
		this.enrollmentApprovedWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetEnrollmentApproved(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrEnrollmentApproved(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrEnrollmentApproved(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentApproved(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentApproved(siteRequest_, SchoolEnrollment.staticSolrEnrollmentApproved(siteRequest_, SchoolEnrollment.staticSetEnrollmentApproved(siteRequest_, o)));
	}

	public Boolean solrEnrollmentApproved() {
		return SchoolEnrollment.staticSolrEnrollmentApproved(siteRequest_, enrollmentApproved);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
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
				a("class", "setEnrollmentApproved classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentApproved w3-input w3-border ");
				a("name", "setEnrollmentApproved");
			} else {
				a("class", "valueEnrollmentApproved classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentApproved w3-input w3-border ");
				a("name", "enrollmentApproved");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentApproved', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_enrollmentApproved')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentApproved')); }); ");
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

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentApproved ").f().sx(htmEnrollmentApproved()).g("span");
		}
	}

	public void htmEnrollmentApproved(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentApproved").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 The entity enrollmentImmunizations
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean enrollmentImmunizations;
	@JsonIgnore
	public Wrap<Boolean> enrollmentImmunizationsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentImmunizations").o(enrollmentImmunizations);

	/**	<br/> The entity enrollmentImmunizations
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentImmunizations">Find the entity enrollmentImmunizations in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentImmunizations(Wrap<Boolean> c);

	public Boolean getEnrollmentImmunizations() {
		return enrollmentImmunizations;
	}

	public void setEnrollmentImmunizations(Boolean enrollmentImmunizations) {
		this.enrollmentImmunizations = enrollmentImmunizations;
		this.enrollmentImmunizationsWrap.alreadyInitialized = true;
	}
	public void setEnrollmentImmunizations(String o) {
		this.enrollmentImmunizations = SchoolEnrollment.staticSetEnrollmentImmunizations(siteRequest_, o);
		this.enrollmentImmunizationsWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetEnrollmentImmunizations(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrEnrollmentImmunizations(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrEnrollmentImmunizations(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentImmunizations(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentImmunizations(siteRequest_, SchoolEnrollment.staticSolrEnrollmentImmunizations(siteRequest_, SchoolEnrollment.staticSetEnrollmentImmunizations(siteRequest_, o)));
	}

	public Boolean solrEnrollmentImmunizations() {
		return SchoolEnrollment.staticSolrEnrollmentImmunizations(siteRequest_, enrollmentImmunizations);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
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
				a("class", "setEnrollmentImmunizations classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentImmunizations w3-input w3-border ");
				a("name", "setEnrollmentImmunizations");
			} else {
				a("class", "valueEnrollmentImmunizations classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentImmunizations w3-input w3-border ");
				a("name", "enrollmentImmunizations");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentImmunizations', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_enrollmentImmunizations')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentImmunizations')); }); ");
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

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentImmunizations ").f().sx(htmEnrollmentImmunizations()).g("span");
		}
	}

	public void htmEnrollmentImmunizations(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentImmunizations").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:photo">Find the entity photo in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _photo(Wrap<String> c);

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String o) {
		this.photo = SchoolEnrollment.staticSetPhoto(siteRequest_, o);
		this.photoWrap.alreadyInitialized = true;
	}
	public static String staticSetPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment photoInit() {
		if(!photoWrap.alreadyInitialized) {
			_photo(photoWrap);
			if(photo == null)
				setPhoto(photoWrap.o);
		}
		photoWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPhoto(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrPhoto(siteRequest_, SchoolEnrollment.staticSolrPhoto(siteRequest_, SchoolEnrollment.staticSetPhoto(siteRequest_, o)));
	}

	public String solrPhoto() {
		return SchoolEnrollment.staticSolrPhoto(siteRequest_, photo);
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
		SchoolEnrollment s = (SchoolEnrollment)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "imageBase64Div1SchoolEnrollment_photo").a("id", "imageBase64Div1SchoolEnrollment", pk, "photo").f();
				e("h5").f().sx("Upload image").g("h5"); 
				e("form").a("method", "POST").a("enctype", "multipart/form-data").a("action", "/photo").a("class", "").f();
				e("input").a("type", "hidden").a("name", "pk").a("value", pk).fg(); 
				e("input").a("type", "hidden").a("name", "classSimpleName").a("value", "SchoolEnrollment").fg(); 
				e("input").a("name", "file").a("type", "file").a("onchange", "$.ajax({ type: 'POST', enctype: 'multipart/form-data', url: '/photo', data: new FormData(this.form), processData: false, contentType: false}); ").fg(); 
				g("form");
				e("img").a("id", "imageBase64ImgSchoolEnrollment", pk, "photo");
					a("class", "imgSchoolEnrollment", pk, "Photo w3-image ");
					a("src", StringUtils.isBlank(photo) ? "data:image/png;base64," : photo).a("alt", "");
				fg();
			g("div");
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "Photo ").f().sx(htmPhoto()).g("span");
		}
	}

	public void htmPhoto(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentPhoto").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_photo')); $('#", classApiMethodMethod, "_photo').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setPhoto', null, function() { addGlow($('#", classApiMethodMethod, "_photo')); }, function() { addError($('#", classApiMethodMethod, "_photo')); }); ")
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

	///////////////////
	// familyMarried //
	///////////////////

	/**	 The entity familyMarried
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean familyMarried;
	@JsonIgnore
	public Wrap<Boolean> familyMarriedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("familyMarried").o(familyMarried);

	/**	<br/> The entity familyMarried
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyMarried">Find the entity familyMarried in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _familyMarried(Wrap<Boolean> c);

	public Boolean getFamilyMarried() {
		return familyMarried;
	}

	public void setFamilyMarried(Boolean familyMarried) {
		this.familyMarried = familyMarried;
		this.familyMarriedWrap.alreadyInitialized = true;
	}
	public void setFamilyMarried(String o) {
		this.familyMarried = SchoolEnrollment.staticSetFamilyMarried(siteRequest_, o);
		this.familyMarriedWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetFamilyMarried(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrFamilyMarried(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrFamilyMarried(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilyMarried(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrFamilyMarried(siteRequest_, SchoolEnrollment.staticSolrFamilyMarried(siteRequest_, SchoolEnrollment.staticSetFamilyMarried(siteRequest_, o)));
	}

	public Boolean solrFamilyMarried() {
		return SchoolEnrollment.staticSolrFamilyMarried(siteRequest_, familyMarried);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
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
				a("class", "setFamilyMarried classSchoolEnrollment inputSchoolEnrollment", pk, "FamilyMarried w3-input w3-border ");
				a("name", "setFamilyMarried");
			} else {
				a("class", "valueFamilyMarried classSchoolEnrollment inputSchoolEnrollment", pk, "FamilyMarried w3-input w3-border ");
				a("name", "familyMarried");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyMarried', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_familyMarried')); }, function() { addError($('#", classApiMethodMethod, "_familyMarried')); }); ");
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

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "FamilyMarried ").f().sx(htmFamilyMarried()).g("span");
		}
	}

	public void htmFamilyMarried(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentFamilyMarried").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 The entity familySeparated
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean familySeparated;
	@JsonIgnore
	public Wrap<Boolean> familySeparatedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("familySeparated").o(familySeparated);

	/**	<br/> The entity familySeparated
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familySeparated">Find the entity familySeparated in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _familySeparated(Wrap<Boolean> c);

	public Boolean getFamilySeparated() {
		return familySeparated;
	}

	public void setFamilySeparated(Boolean familySeparated) {
		this.familySeparated = familySeparated;
		this.familySeparatedWrap.alreadyInitialized = true;
	}
	public void setFamilySeparated(String o) {
		this.familySeparated = SchoolEnrollment.staticSetFamilySeparated(siteRequest_, o);
		this.familySeparatedWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetFamilySeparated(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrFamilySeparated(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrFamilySeparated(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilySeparated(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrFamilySeparated(siteRequest_, SchoolEnrollment.staticSolrFamilySeparated(siteRequest_, SchoolEnrollment.staticSetFamilySeparated(siteRequest_, o)));
	}

	public Boolean solrFamilySeparated() {
		return SchoolEnrollment.staticSolrFamilySeparated(siteRequest_, familySeparated);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
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
				a("class", "setFamilySeparated classSchoolEnrollment inputSchoolEnrollment", pk, "FamilySeparated w3-input w3-border ");
				a("name", "setFamilySeparated");
			} else {
				a("class", "valueFamilySeparated classSchoolEnrollment inputSchoolEnrollment", pk, "FamilySeparated w3-input w3-border ");
				a("name", "familySeparated");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilySeparated', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_familySeparated')); }, function() { addError($('#", classApiMethodMethod, "_familySeparated')); }); ");
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

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "FamilySeparated ").f().sx(htmFamilySeparated()).g("span");
		}
	}

	public void htmFamilySeparated(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentFamilySeparated").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 The entity familyDivorced
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean familyDivorced;
	@JsonIgnore
	public Wrap<Boolean> familyDivorcedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("familyDivorced").o(familyDivorced);

	/**	<br/> The entity familyDivorced
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyDivorced">Find the entity familyDivorced in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _familyDivorced(Wrap<Boolean> c);

	public Boolean getFamilyDivorced() {
		return familyDivorced;
	}

	public void setFamilyDivorced(Boolean familyDivorced) {
		this.familyDivorced = familyDivorced;
		this.familyDivorcedWrap.alreadyInitialized = true;
	}
	public void setFamilyDivorced(String o) {
		this.familyDivorced = SchoolEnrollment.staticSetFamilyDivorced(siteRequest_, o);
		this.familyDivorcedWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetFamilyDivorced(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrFamilyDivorced(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrFamilyDivorced(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilyDivorced(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrFamilyDivorced(siteRequest_, SchoolEnrollment.staticSolrFamilyDivorced(siteRequest_, SchoolEnrollment.staticSetFamilyDivorced(siteRequest_, o)));
	}

	public Boolean solrFamilyDivorced() {
		return SchoolEnrollment.staticSolrFamilyDivorced(siteRequest_, familyDivorced);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
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
				a("class", "setFamilyDivorced classSchoolEnrollment inputSchoolEnrollment", pk, "FamilyDivorced w3-input w3-border ");
				a("name", "setFamilyDivorced");
			} else {
				a("class", "valueFamilyDivorced classSchoolEnrollment inputSchoolEnrollment", pk, "FamilyDivorced w3-input w3-border ");
				a("name", "familyDivorced");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyDivorced', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_familyDivorced')); }, function() { addError($('#", classApiMethodMethod, "_familyDivorced')); }); ");
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

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "FamilyDivorced ").f().sx(htmFamilyDivorced()).g("span");
		}
	}

	public void htmFamilyDivorced(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentFamilyDivorced").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 The entity familyAddress
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String familyAddress;
	@JsonIgnore
	public Wrap<String> familyAddressWrap = new Wrap<String>().p(this).c(String.class).var("familyAddress").o(familyAddress);

	/**	<br/> The entity familyAddress
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyAddress">Find the entity familyAddress in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _familyAddress(Wrap<String> c);

	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String o) {
		this.familyAddress = SchoolEnrollment.staticSetFamilyAddress(siteRequest_, o);
		this.familyAddressWrap.alreadyInitialized = true;
	}
	public static String staticSetFamilyAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrFamilyAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrFamilyAddress(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilyAddress(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrFamilyAddress(siteRequest_, SchoolEnrollment.staticSolrFamilyAddress(siteRequest_, SchoolEnrollment.staticSetFamilyAddress(siteRequest_, o)));
	}

	public String solrFamilyAddress() {
		return SchoolEnrollment.staticSolrFamilyAddress(siteRequest_, familyAddress);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "family address")
				.a("id", classApiMethodMethod, "_familyAddress");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setFamilyAddress classSchoolEnrollment inputSchoolEnrollment", pk, "FamilyAddress w3-input w3-border ");
					a("name", "setFamilyAddress");
				} else {
					a("class", "valueFamilyAddress w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "FamilyAddress w3-input w3-border ");
					a("name", "familyAddress");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyAddress', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_familyAddress')); }, function() { addError($('#", classApiMethodMethod, "_familyAddress')); }); ");
				}
			f().sx(strFamilyAddress()).g("textarea");

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "FamilyAddress ").f().sx(htmFamilyAddress()).g("span");
		}
	}

	public void htmFamilyAddress(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentFamilyAddress").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_familyAddress").a("class", "").f().sx("family address").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilyAddress(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_familyAddress')); $('#", classApiMethodMethod, "_familyAddress').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setFamilyAddress', null, function() { addGlow($('#", classApiMethodMethod, "_familyAddress')); }, function() { addError($('#", classApiMethodMethod, "_familyAddress')); }); ")
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

	/////////////////////////////////
	// familyHowDoYouKnowTheSchool //
	/////////////////////////////////

	/**	 The entity familyHowDoYouKnowTheSchool
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String familyHowDoYouKnowTheSchool;
	@JsonIgnore
	public Wrap<String> familyHowDoYouKnowTheSchoolWrap = new Wrap<String>().p(this).c(String.class).var("familyHowDoYouKnowTheSchool").o(familyHowDoYouKnowTheSchool);

	/**	<br/> The entity familyHowDoYouKnowTheSchool
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyHowDoYouKnowTheSchool">Find the entity familyHowDoYouKnowTheSchool in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _familyHowDoYouKnowTheSchool(Wrap<String> c);

	public String getFamilyHowDoYouKnowTheSchool() {
		return familyHowDoYouKnowTheSchool;
	}
	public void setFamilyHowDoYouKnowTheSchool(String o) {
		this.familyHowDoYouKnowTheSchool = SchoolEnrollment.staticSetFamilyHowDoYouKnowTheSchool(siteRequest_, o);
		this.familyHowDoYouKnowTheSchoolWrap.alreadyInitialized = true;
	}
	public static String staticSetFamilyHowDoYouKnowTheSchool(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrFamilyHowDoYouKnowTheSchool(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrFamilyHowDoYouKnowTheSchool(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilyHowDoYouKnowTheSchool(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrFamilyHowDoYouKnowTheSchool(siteRequest_, SchoolEnrollment.staticSolrFamilyHowDoYouKnowTheSchool(siteRequest_, SchoolEnrollment.staticSetFamilyHowDoYouKnowTheSchool(siteRequest_, o)));
	}

	public String solrFamilyHowDoYouKnowTheSchool() {
		return SchoolEnrollment.staticSolrFamilyHowDoYouKnowTheSchool(siteRequest_, familyHowDoYouKnowTheSchool);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "how do you know the school? ")
				.a("id", classApiMethodMethod, "_familyHowDoYouKnowTheSchool");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setFamilyHowDoYouKnowTheSchool classSchoolEnrollment inputSchoolEnrollment", pk, "FamilyHowDoYouKnowTheSchool w3-input w3-border ");
					a("name", "setFamilyHowDoYouKnowTheSchool");
				} else {
					a("class", "valueFamilyHowDoYouKnowTheSchool w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "FamilyHowDoYouKnowTheSchool w3-input w3-border ");
					a("name", "familyHowDoYouKnowTheSchool");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyHowDoYouKnowTheSchool', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool')); }, function() { addError($('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool')); }); ");
				}
			f().sx(strFamilyHowDoYouKnowTheSchool()).g("textarea");

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "FamilyHowDoYouKnowTheSchool ").f().sx(htmFamilyHowDoYouKnowTheSchool()).g("span");
		}
	}

	public void htmFamilyHowDoYouKnowTheSchool(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentFamilyHowDoYouKnowTheSchool").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_familyHowDoYouKnowTheSchool").a("class", "").f().sx("how do you know the school? ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilyHowDoYouKnowTheSchool(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool')); $('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setFamilyHowDoYouKnowTheSchool', null, function() { addGlow($('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool')); }, function() { addError($('#", classApiMethodMethod, "_familyHowDoYouKnowTheSchool')); }); ")
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

	/////////////////////////////////////
	// enrollmentSpecialConsiderations //
	/////////////////////////////////////

	/**	 The entity enrollmentSpecialConsiderations
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentSpecialConsiderations;
	@JsonIgnore
	public Wrap<String> enrollmentSpecialConsiderationsWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSpecialConsiderations").o(enrollmentSpecialConsiderations);

	/**	<br/> The entity enrollmentSpecialConsiderations
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSpecialConsiderations">Find the entity enrollmentSpecialConsiderations in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSpecialConsiderations(Wrap<String> c);

	public String getEnrollmentSpecialConsiderations() {
		return enrollmentSpecialConsiderations;
	}
	public void setEnrollmentSpecialConsiderations(String o) {
		this.enrollmentSpecialConsiderations = SchoolEnrollment.staticSetEnrollmentSpecialConsiderations(siteRequest_, o);
		this.enrollmentSpecialConsiderationsWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentSpecialConsiderations(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentSpecialConsiderations(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentSpecialConsiderations(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentSpecialConsiderations(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentSpecialConsiderations(siteRequest_, SchoolEnrollment.staticSolrEnrollmentSpecialConsiderations(siteRequest_, SchoolEnrollment.staticSetEnrollmentSpecialConsiderations(siteRequest_, o)));
	}

	public String solrEnrollmentSpecialConsiderations() {
		return SchoolEnrollment.staticSolrEnrollmentSpecialConsiderations(siteRequest_, enrollmentSpecialConsiderations);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "special considerations")
				.a("id", classApiMethodMethod, "_enrollmentSpecialConsiderations");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setEnrollmentSpecialConsiderations classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentSpecialConsiderations w3-input w3-border ");
					a("name", "setEnrollmentSpecialConsiderations");
				} else {
					a("class", "valueEnrollmentSpecialConsiderations w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentSpecialConsiderations w3-input w3-border ");
					a("name", "enrollmentSpecialConsiderations");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSpecialConsiderations', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_enrollmentSpecialConsiderations')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSpecialConsiderations')); }); ");
				}
			f().sx(strEnrollmentSpecialConsiderations()).g("textarea");

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentSpecialConsiderations ").f().sx(htmEnrollmentSpecialConsiderations()).g("span");
		}
	}

	public void htmEnrollmentSpecialConsiderations(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentSpecialConsiderations").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_enrollmentSpecialConsiderations").a("class", "").f().sx("special considerations").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSpecialConsiderations(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSpecialConsiderations')); $('#", classApiMethodMethod, "_enrollmentSpecialConsiderations').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSpecialConsiderations', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSpecialConsiderations')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSpecialConsiderations')); }); ")
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

	////////////////////////////
	// childMedicalConditions //
	////////////////////////////

	/**	 The entity childMedicalConditions
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childMedicalConditions;
	@JsonIgnore
	public Wrap<String> childMedicalConditionsWrap = new Wrap<String>().p(this).c(String.class).var("childMedicalConditions").o(childMedicalConditions);

	/**	<br/> The entity childMedicalConditions
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childMedicalConditions">Find the entity childMedicalConditions in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childMedicalConditions(Wrap<String> c);

	public String getChildMedicalConditions() {
		return childMedicalConditions;
	}
	public void setChildMedicalConditions(String o) {
		this.childMedicalConditions = SchoolEnrollment.staticSetChildMedicalConditions(siteRequest_, o);
		this.childMedicalConditionsWrap.alreadyInitialized = true;
	}
	public static String staticSetChildMedicalConditions(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrChildMedicalConditions(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildMedicalConditions(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildMedicalConditions(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildMedicalConditions(siteRequest_, SchoolEnrollment.staticSolrChildMedicalConditions(siteRequest_, SchoolEnrollment.staticSetChildMedicalConditions(siteRequest_, o)));
	}

	public String solrChildMedicalConditions() {
		return SchoolEnrollment.staticSolrChildMedicalConditions(siteRequest_, childMedicalConditions);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "medical conditions")
				.a("id", classApiMethodMethod, "_childMedicalConditions");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setChildMedicalConditions classSchoolEnrollment inputSchoolEnrollment", pk, "ChildMedicalConditions w3-input w3-border ");
					a("name", "setChildMedicalConditions");
				} else {
					a("class", "valueChildMedicalConditions w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "ChildMedicalConditions w3-input w3-border ");
					a("name", "childMedicalConditions");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildMedicalConditions', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childMedicalConditions')); }, function() { addError($('#", classApiMethodMethod, "_childMedicalConditions')); }); ");
				}
			f().sx(strChildMedicalConditions()).g("textarea");

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "ChildMedicalConditions ").f().sx(htmChildMedicalConditions()).g("span");
		}
	}

	public void htmChildMedicalConditions(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentChildMedicalConditions").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_childMedicalConditions").a("class", "").f().sx("medical conditions").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildMedicalConditions(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childMedicalConditions')); $('#", classApiMethodMethod, "_childMedicalConditions').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildMedicalConditions', null, function() { addGlow($('#", classApiMethodMethod, "_childMedicalConditions')); }, function() { addError($('#", classApiMethodMethod, "_childMedicalConditions')); }); ")
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

	//////////////////////////////////
	// childPreviousSchoolsAttended //
	//////////////////////////////////

	/**	 The entity childPreviousSchoolsAttended
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childPreviousSchoolsAttended;
	@JsonIgnore
	public Wrap<String> childPreviousSchoolsAttendedWrap = new Wrap<String>().p(this).c(String.class).var("childPreviousSchoolsAttended").o(childPreviousSchoolsAttended);

	/**	<br/> The entity childPreviousSchoolsAttended
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childPreviousSchoolsAttended">Find the entity childPreviousSchoolsAttended in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childPreviousSchoolsAttended(Wrap<String> c);

	public String getChildPreviousSchoolsAttended() {
		return childPreviousSchoolsAttended;
	}
	public void setChildPreviousSchoolsAttended(String o) {
		this.childPreviousSchoolsAttended = SchoolEnrollment.staticSetChildPreviousSchoolsAttended(siteRequest_, o);
		this.childPreviousSchoolsAttendedWrap.alreadyInitialized = true;
	}
	public static String staticSetChildPreviousSchoolsAttended(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrChildPreviousSchoolsAttended(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildPreviousSchoolsAttended(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildPreviousSchoolsAttended(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildPreviousSchoolsAttended(siteRequest_, SchoolEnrollment.staticSolrChildPreviousSchoolsAttended(siteRequest_, SchoolEnrollment.staticSetChildPreviousSchoolsAttended(siteRequest_, o)));
	}

	public String solrChildPreviousSchoolsAttended() {
		return SchoolEnrollment.staticSolrChildPreviousSchoolsAttended(siteRequest_, childPreviousSchoolsAttended);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "schools previously attended")
				.a("id", classApiMethodMethod, "_childPreviousSchoolsAttended");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setChildPreviousSchoolsAttended classSchoolEnrollment inputSchoolEnrollment", pk, "ChildPreviousSchoolsAttended w3-input w3-border ");
					a("name", "setChildPreviousSchoolsAttended");
				} else {
					a("class", "valueChildPreviousSchoolsAttended w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "ChildPreviousSchoolsAttended w3-input w3-border ");
					a("name", "childPreviousSchoolsAttended");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildPreviousSchoolsAttended', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childPreviousSchoolsAttended')); }, function() { addError($('#", classApiMethodMethod, "_childPreviousSchoolsAttended')); }); ");
				}
			f().sx(strChildPreviousSchoolsAttended()).g("textarea");

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "ChildPreviousSchoolsAttended ").f().sx(htmChildPreviousSchoolsAttended()).g("span");
		}
	}

	public void htmChildPreviousSchoolsAttended(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentChildPreviousSchoolsAttended").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_childPreviousSchoolsAttended").a("class", "").f().sx("schools previously attended").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildPreviousSchoolsAttended(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childPreviousSchoolsAttended')); $('#", classApiMethodMethod, "_childPreviousSchoolsAttended').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildPreviousSchoolsAttended', null, function() { addGlow($('#", classApiMethodMethod, "_childPreviousSchoolsAttended')); }, function() { addError($('#", classApiMethodMethod, "_childPreviousSchoolsAttended')); }); ")
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

	//////////////////////
	// childDescription //
	//////////////////////

	/**	 The entity childDescription
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childDescription;
	@JsonIgnore
	public Wrap<String> childDescriptionWrap = new Wrap<String>().p(this).c(String.class).var("childDescription").o(childDescription);

	/**	<br/> The entity childDescription
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childDescription">Find the entity childDescription in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childDescription(Wrap<String> c);

	public String getChildDescription() {
		return childDescription;
	}
	public void setChildDescription(String o) {
		this.childDescription = SchoolEnrollment.staticSetChildDescription(siteRequest_, o);
		this.childDescriptionWrap.alreadyInitialized = true;
	}
	public static String staticSetChildDescription(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrChildDescription(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildDescription(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildDescription(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildDescription(siteRequest_, SchoolEnrollment.staticSolrChildDescription(siteRequest_, SchoolEnrollment.staticSetChildDescription(siteRequest_, o)));
	}

	public String solrChildDescription() {
		return SchoolEnrollment.staticSolrChildDescription(siteRequest_, childDescription);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "description")
				.a("id", classApiMethodMethod, "_childDescription");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setChildDescription classSchoolEnrollment inputSchoolEnrollment", pk, "ChildDescription w3-input w3-border ");
					a("name", "setChildDescription");
				} else {
					a("class", "valueChildDescription w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "ChildDescription w3-input w3-border ");
					a("name", "childDescription");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildDescription', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childDescription')); }, function() { addError($('#", classApiMethodMethod, "_childDescription')); }); ");
				}
			f().sx(strChildDescription()).g("textarea");

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "ChildDescription ").f().sx(htmChildDescription()).g("span");
		}
	}

	public void htmChildDescription(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentChildDescription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_childDescription").a("class", "").f().sx("description").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildDescription(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childDescription')); $('#", classApiMethodMethod, "_childDescription').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildDescription', null, function() { addGlow($('#", classApiMethodMethod, "_childDescription')); }, function() { addError($('#", classApiMethodMethod, "_childDescription')); }); ")
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
	// childObjectives //
	/////////////////////

	/**	 The entity childObjectives
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childObjectives;
	@JsonIgnore
	public Wrap<String> childObjectivesWrap = new Wrap<String>().p(this).c(String.class).var("childObjectives").o(childObjectives);

	/**	<br/> The entity childObjectives
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childObjectives">Find the entity childObjectives in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childObjectives(Wrap<String> c);

	public String getChildObjectives() {
		return childObjectives;
	}
	public void setChildObjectives(String o) {
		this.childObjectives = SchoolEnrollment.staticSetChildObjectives(siteRequest_, o);
		this.childObjectivesWrap.alreadyInitialized = true;
	}
	public static String staticSetChildObjectives(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrChildObjectives(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildObjectives(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildObjectives(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildObjectives(siteRequest_, SchoolEnrollment.staticSolrChildObjectives(siteRequest_, SchoolEnrollment.staticSetChildObjectives(siteRequest_, o)));
	}

	public String solrChildObjectives() {
		return SchoolEnrollment.staticSolrChildObjectives(siteRequest_, childObjectives);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "objectives")
				.a("id", classApiMethodMethod, "_childObjectives");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setChildObjectives classSchoolEnrollment inputSchoolEnrollment", pk, "ChildObjectives w3-input w3-border ");
					a("name", "setChildObjectives");
				} else {
					a("class", "valueChildObjectives w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "ChildObjectives w3-input w3-border ");
					a("name", "childObjectives");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildObjectives', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_childObjectives')); }, function() { addError($('#", classApiMethodMethod, "_childObjectives')); }); ");
				}
			f().sx(strChildObjectives()).g("textarea");

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "ChildObjectives ").f().sx(htmChildObjectives()).g("span");
		}
	}

	public void htmChildObjectives(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentChildObjectives").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_childObjectives").a("class", "").f().sx("objectives").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputChildObjectives(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_childObjectives')); $('#", classApiMethodMethod, "_childObjectives').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setChildObjectives', null, function() { addGlow($('#", classApiMethodMethod, "_childObjectives')); }, function() { addError($('#", classApiMethodMethod, "_childObjectives')); }); ")
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
	// childPottyTrained //
	///////////////////////

	/**	 The entity childPottyTrained
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean childPottyTrained;
	@JsonIgnore
	public Wrap<Boolean> childPottyTrainedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("childPottyTrained").o(childPottyTrained);

	/**	<br/> The entity childPottyTrained
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childPottyTrained">Find the entity childPottyTrained in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childPottyTrained(Wrap<Boolean> c);

	public Boolean getChildPottyTrained() {
		return childPottyTrained;
	}

	public void setChildPottyTrained(Boolean childPottyTrained) {
		this.childPottyTrained = childPottyTrained;
		this.childPottyTrainedWrap.alreadyInitialized = true;
	}
	public void setChildPottyTrained(String o) {
		this.childPottyTrained = SchoolEnrollment.staticSetChildPottyTrained(siteRequest_, o);
		this.childPottyTrainedWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetChildPottyTrained(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrChildPottyTrained(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrChildPottyTrained(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildPottyTrained(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildPottyTrained(siteRequest_, SchoolEnrollment.staticSolrChildPottyTrained(siteRequest_, SchoolEnrollment.staticSetChildPottyTrained(siteRequest_, o)));
	}

	public Boolean solrChildPottyTrained() {
		return SchoolEnrollment.staticSolrChildPottyTrained(siteRequest_, childPottyTrained);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
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
				a("class", "setChildPottyTrained classSchoolEnrollment inputSchoolEnrollment", pk, "ChildPottyTrained w3-input w3-border ");
				a("name", "setChildPottyTrained");
			} else {
				a("class", "valueChildPottyTrained classSchoolEnrollment inputSchoolEnrollment", pk, "ChildPottyTrained w3-input w3-border ");
				a("name", "childPottyTrained");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setChildPottyTrained', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_childPottyTrained')); }, function() { addError($('#", classApiMethodMethod, "_childPottyTrained')); }); ");
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

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "ChildPottyTrained ").f().sx(htmChildPottyTrained()).g("span");
		}
	}

	public void htmChildPottyTrained(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentChildPottyTrained").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 The entity enrollmentGroupName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentGroupName;
	@JsonIgnore
	public Wrap<String> enrollmentGroupNameWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentGroupName").o(enrollmentGroupName);

	/**	<br/> The entity enrollmentGroupName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentGroupName">Find the entity enrollmentGroupName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentGroupName(Wrap<String> c);

	public String getEnrollmentGroupName() {
		return enrollmentGroupName;
	}
	public void setEnrollmentGroupName(String o) {
		this.enrollmentGroupName = SchoolEnrollment.staticSetEnrollmentGroupName(siteRequest_, o);
		this.enrollmentGroupNameWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentGroupName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentGroupName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentGroupName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentGroupName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentGroupName(siteRequest_, SchoolEnrollment.staticSolrEnrollmentGroupName(siteRequest_, SchoolEnrollment.staticSetEnrollmentGroupName(siteRequest_, o)));
	}

	public String solrEnrollmentGroupName() {
		return SchoolEnrollment.staticSolrEnrollmentGroupName(siteRequest_, enrollmentGroupName);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "group name")
				.a("id", classApiMethodMethod, "_enrollmentGroupName");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setEnrollmentGroupName classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentGroupName w3-input w3-border ");
					a("name", "setEnrollmentGroupName");
				} else {
					a("class", "valueEnrollmentGroupName w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentGroupName w3-input w3-border ");
					a("name", "enrollmentGroupName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentGroupName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_enrollmentGroupName')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentGroupName')); }); ");
				}
				a("value", strEnrollmentGroupName())
			.fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentGroupName ").f().sx(htmEnrollmentGroupName()).g("span");
		}
	}

	public void htmEnrollmentGroupName(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentGroupName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_enrollmentGroupName").a("class", "").f().sx("group name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentGroupName(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentGroupName')); $('#", classApiMethodMethod, "_enrollmentGroupName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentGroupName', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentGroupName')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentGroupName')); }); ")
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
	// enrollmentGroupColor //
	//////////////////////////

	/**	 The entity enrollmentGroupColor
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentGroupColor;
	@JsonIgnore
	public Wrap<String> enrollmentGroupColorWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentGroupColor").o(enrollmentGroupColor);

	/**	<br/> The entity enrollmentGroupColor
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentGroupColor">Find the entity enrollmentGroupColor in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentGroupColor(Wrap<String> c);

	public String getEnrollmentGroupColor() {
		return enrollmentGroupColor;
	}
	public void setEnrollmentGroupColor(String o) {
		this.enrollmentGroupColor = SchoolEnrollment.staticSetEnrollmentGroupColor(siteRequest_, o);
		this.enrollmentGroupColorWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentGroupColor(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment enrollmentGroupColorInit() {
		if(!enrollmentGroupColorWrap.alreadyInitialized) {
			_enrollmentGroupColor(enrollmentGroupColorWrap);
			if(enrollmentGroupColor == null)
				setEnrollmentGroupColor(enrollmentGroupColorWrap.o);
		}
		enrollmentGroupColorWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentGroupColor(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentGroupColor(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentGroupColor(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentGroupColor(siteRequest_, SchoolEnrollment.staticSolrEnrollmentGroupColor(siteRequest_, SchoolEnrollment.staticSetEnrollmentGroupColor(siteRequest_, o)));
	}

	public String solrEnrollmentGroupColor() {
		return SchoolEnrollment.staticSolrEnrollmentGroupColor(siteRequest_, enrollmentGroupColor);
	}

	public String strEnrollmentGroupColor() {
		return enrollmentGroupColor == null ? "" : enrollmentGroupColor;
	}

	public String jsonEnrollmentGroupColor() {
		return enrollmentGroupColor == null ? "" : enrollmentGroupColor;
	}

	public String nomAffichageEnrollmentGroupColor() {
		return "group color";
	}

	public String htmTooltipEnrollmentGroupColor() {
		return null;
	}

	public String htmEnrollmentGroupColor() {
		return enrollmentGroupColor == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentGroupColor());
	}

	////////////////////////////////
	// enrollmentPaymentEachMonth //
	////////////////////////////////

	/**	 The entity enrollmentPaymentEachMonth
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean enrollmentPaymentEachMonth;
	@JsonIgnore
	public Wrap<Boolean> enrollmentPaymentEachMonthWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentPaymentEachMonth").o(enrollmentPaymentEachMonth);

	/**	<br/> The entity enrollmentPaymentEachMonth
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentPaymentEachMonth">Find the entity enrollmentPaymentEachMonth in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentPaymentEachMonth(Wrap<Boolean> c);

	public Boolean getEnrollmentPaymentEachMonth() {
		return enrollmentPaymentEachMonth;
	}

	public void setEnrollmentPaymentEachMonth(Boolean enrollmentPaymentEachMonth) {
		this.enrollmentPaymentEachMonth = enrollmentPaymentEachMonth;
		this.enrollmentPaymentEachMonthWrap.alreadyInitialized = true;
	}
	public void setEnrollmentPaymentEachMonth(String o) {
		this.enrollmentPaymentEachMonth = SchoolEnrollment.staticSetEnrollmentPaymentEachMonth(siteRequest_, o);
		this.enrollmentPaymentEachMonthWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetEnrollmentPaymentEachMonth(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrEnrollmentPaymentEachMonth(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrEnrollmentPaymentEachMonth(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentPaymentEachMonth(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentPaymentEachMonth(siteRequest_, SchoolEnrollment.staticSolrEnrollmentPaymentEachMonth(siteRequest_, SchoolEnrollment.staticSetEnrollmentPaymentEachMonth(siteRequest_, o)));
	}

	public Boolean solrEnrollmentPaymentEachMonth() {
		return SchoolEnrollment.staticSolrEnrollmentPaymentEachMonth(siteRequest_, enrollmentPaymentEachMonth);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
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
				a("class", "setEnrollmentPaymentEachMonth classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentPaymentEachMonth w3-input w3-border ");
				a("name", "setEnrollmentPaymentEachMonth");
			} else {
				a("class", "valueEnrollmentPaymentEachMonth classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentPaymentEachMonth w3-input w3-border ");
				a("name", "enrollmentPaymentEachMonth");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentPaymentEachMonth', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_enrollmentPaymentEachMonth')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentPaymentEachMonth')); }); ");
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

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentPaymentEachMonth ").f().sx(htmEnrollmentPaymentEachMonth()).g("span");
		}
	}

	public void htmEnrollmentPaymentEachMonth(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentPaymentEachMonth").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 The entity enrollmentPaymentComplete
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean enrollmentPaymentComplete;
	@JsonIgnore
	public Wrap<Boolean> enrollmentPaymentCompleteWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("enrollmentPaymentComplete").o(enrollmentPaymentComplete);

	/**	<br/> The entity enrollmentPaymentComplete
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentPaymentComplete">Find the entity enrollmentPaymentComplete in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentPaymentComplete(Wrap<Boolean> c);

	public Boolean getEnrollmentPaymentComplete() {
		return enrollmentPaymentComplete;
	}

	public void setEnrollmentPaymentComplete(Boolean enrollmentPaymentComplete) {
		this.enrollmentPaymentComplete = enrollmentPaymentComplete;
		this.enrollmentPaymentCompleteWrap.alreadyInitialized = true;
	}
	public void setEnrollmentPaymentComplete(String o) {
		this.enrollmentPaymentComplete = SchoolEnrollment.staticSetEnrollmentPaymentComplete(siteRequest_, o);
		this.enrollmentPaymentCompleteWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetEnrollmentPaymentComplete(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrEnrollmentPaymentComplete(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrEnrollmentPaymentComplete(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentPaymentComplete(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentPaymentComplete(siteRequest_, SchoolEnrollment.staticSolrEnrollmentPaymentComplete(siteRequest_, SchoolEnrollment.staticSetEnrollmentPaymentComplete(siteRequest_, o)));
	}

	public Boolean solrEnrollmentPaymentComplete() {
		return SchoolEnrollment.staticSolrEnrollmentPaymentComplete(siteRequest_, enrollmentPaymentComplete);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
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
				a("class", "setEnrollmentPaymentComplete classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentPaymentComplete w3-input w3-border ");
				a("name", "setEnrollmentPaymentComplete");
			} else {
				a("class", "valueEnrollmentPaymentComplete classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentPaymentComplete w3-input w3-border ");
				a("name", "enrollmentPaymentComplete");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentPaymentComplete', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_enrollmentPaymentComplete')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentPaymentComplete')); }); ");
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

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentPaymentComplete ").f().sx(htmEnrollmentPaymentComplete()).g("span");
		}
	}

	public void htmEnrollmentPaymentComplete(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentPaymentComplete").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	///////////////////////
	// customerProfileId //
	///////////////////////

	/**	 The entity customerProfileId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId;
	@JsonIgnore
	public Wrap<String> customerProfileIdWrap = new Wrap<String>().p(this).c(String.class).var("customerProfileId").o(customerProfileId);

	/**	<br/> The entity customerProfileId
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:customerProfileId">Find the entity customerProfileId in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _customerProfileId(Wrap<String> c);

	public String getCustomerProfileId() {
		return customerProfileId;
	}
	public void setCustomerProfileId(String o) {
		this.customerProfileId = SchoolEnrollment.staticSetCustomerProfileId(siteRequest_, o);
		this.customerProfileIdWrap.alreadyInitialized = true;
	}
	public static String staticSetCustomerProfileId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment customerProfileIdInit() {
		if(!customerProfileIdWrap.alreadyInitialized) {
			_customerProfileId(customerProfileIdWrap);
			if(customerProfileId == null)
				setCustomerProfileId(customerProfileIdWrap.o);
		}
		customerProfileIdWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrCustomerProfileId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrCustomerProfileId(siteRequest_, SchoolEnrollment.staticSolrCustomerProfileId(siteRequest_, SchoolEnrollment.staticSetCustomerProfileId(siteRequest_, o)));
	}

	public String solrCustomerProfileId() {
		return SchoolEnrollment.staticSolrCustomerProfileId(siteRequest_, customerProfileId);
	}

	public String strCustomerProfileId() {
		return customerProfileId == null ? "" : customerProfileId;
	}

	public String jsonCustomerProfileId() {
		return customerProfileId == null ? "" : customerProfileId;
	}

	public String nomAffichageCustomerProfileId() {
		return "customer profile ID";
	}

	public String htmTooltipCustomerProfileId() {
		return null;
	}

	public String htmCustomerProfileId() {
		return customerProfileId == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId());
	}

	public void inputCustomerProfileId(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "customer profile ID")
				.a("id", classApiMethodMethod, "_customerProfileId");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setCustomerProfileId classSchoolEnrollment inputSchoolEnrollment", pk, "CustomerProfileId w3-input w3-border ");
					a("name", "setCustomerProfileId");
				} else {
					a("class", "valueCustomerProfileId w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "CustomerProfileId w3-input w3-border ");
					a("name", "customerProfileId");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_customerProfileId')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId')); }); ");
				}
				a("value", strCustomerProfileId())
			.fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "CustomerProfileId ").f().sx(htmCustomerProfileId()).g("span");
		}
	}

	public void htmCustomerProfileId(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentCustomerProfileId").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classApiMethodMethod, "_customerProfileId").a("class", "").f().sx("customer profile ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_customerProfileId')); $('#", classApiMethodMethod, "_customerProfileId').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setCustomerProfileId', null, function() { addGlow($('#", classApiMethodMethod, "_customerProfileId')); }, function() { addError($('#", classApiMethodMethod, "_customerProfileId')); }); ")
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
	// enrollmentChargeDate //
	//////////////////////////

	/**	 The entity enrollmentChargeDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enrollmentChargeDate;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentChargeDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentChargeDate").o(enrollmentChargeDate);

	/**	<br/> The entity enrollmentChargeDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentChargeDate">Find the entity enrollmentChargeDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentChargeDate(Wrap<LocalDate> c);

	public LocalDate getEnrollmentChargeDate() {
		return enrollmentChargeDate;
	}

	public void setEnrollmentChargeDate(LocalDate enrollmentChargeDate) {
		this.enrollmentChargeDate = enrollmentChargeDate;
		this.enrollmentChargeDateWrap.alreadyInitialized = true;
	}
	public void setEnrollmentChargeDate(Instant o) {
		this.enrollmentChargeDate = o == null ? null : LocalDate.from(o);
		this.enrollmentChargeDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnrollmentChargeDate(String o) {
		this.enrollmentChargeDate = SchoolEnrollment.staticSetEnrollmentChargeDate(siteRequest_, o);
		this.enrollmentChargeDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetEnrollmentChargeDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnrollmentChargeDate(Date o) {
		this.enrollmentChargeDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentChargeDateWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment enrollmentChargeDateInit() {
		if(!enrollmentChargeDateWrap.alreadyInitialized) {
			_enrollmentChargeDate(enrollmentChargeDateWrap);
			if(enrollmentChargeDate == null)
				setEnrollmentChargeDate(enrollmentChargeDateWrap.o);
		}
		enrollmentChargeDateWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Date staticSolrEnrollmentChargeDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnrollmentChargeDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnrollmentChargeDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentChargeDate(siteRequest_, SchoolEnrollment.staticSolrEnrollmentChargeDate(siteRequest_, SchoolEnrollment.staticSetEnrollmentChargeDate(siteRequest_, o)));
	}

	public Date solrEnrollmentChargeDate() {
		return SchoolEnrollment.staticSolrEnrollmentChargeDate(siteRequest_, enrollmentChargeDate);
	}

	public String strEnrollmentChargeDate() {
		return enrollmentChargeDate == null ? "" : enrollmentChargeDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonEnrollmentChargeDate() {
		return enrollmentChargeDate == null ? "" : enrollmentChargeDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageEnrollmentChargeDate() {
		return null;
	}

	public String htmTooltipEnrollmentChargeDate() {
		return null;
	}

	public String htmEnrollmentChargeDate() {
		return enrollmentChargeDate == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentChargeDate());
	}

	public void inputEnrollmentChargeDate(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setEnrollmentChargeDate classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentChargeDate w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_enrollmentChargeDate")
					.a("value", enrollmentChargeDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(enrollmentChargeDate));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentChargeDate', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentChargeDate')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentChargeDate')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentChargeDate ").f().sx(htmEnrollmentChargeDate()).g("span");
		}
	}

	public void htmEnrollmentChargeDate(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentChargeDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentChargeDate(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentChargeDate')); $('#", classApiMethodMethod, "_enrollmentChargeDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentChargeDate', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentChargeDate')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentChargeDate')); }); ")
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

	///////////////////
	// paymentFacets //
	///////////////////

	/**	 The entity paymentFacets
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SimpleOrderedMap paymentFacets;
	@JsonIgnore
	public Wrap<SimpleOrderedMap> paymentFacetsWrap = new Wrap<SimpleOrderedMap>().p(this).c(SimpleOrderedMap.class).var("paymentFacets").o(paymentFacets);

	/**	<br/> The entity paymentFacets
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentFacets">Find the entity paymentFacets in Solr</a>
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
	protected SchoolEnrollment paymentFacetsInit() {
		if(!paymentFacetsWrap.alreadyInitialized) {
			_paymentFacets(paymentFacetsWrap);
			if(paymentFacets == null)
				setPaymentFacets(paymentFacetsWrap.o);
		}
		paymentFacetsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	/////////////////////
	// paymentLastDate //
	/////////////////////

	/**	 The entity paymentLastDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate paymentLastDate;
	@JsonIgnore
	public Wrap<LocalDate> paymentLastDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("paymentLastDate").o(paymentLastDate);

	/**	<br/> The entity paymentLastDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentLastDate">Find the entity paymentLastDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentLastDate(Wrap<LocalDate> c);

	public LocalDate getPaymentLastDate() {
		return paymentLastDate;
	}

	public void setPaymentLastDate(LocalDate paymentLastDate) {
		this.paymentLastDate = paymentLastDate;
		this.paymentLastDateWrap.alreadyInitialized = true;
	}
	public void setPaymentLastDate(Instant o) {
		this.paymentLastDate = o == null ? null : LocalDate.from(o);
		this.paymentLastDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setPaymentLastDate(String o) {
		this.paymentLastDate = SchoolEnrollment.staticSetPaymentLastDate(siteRequest_, o);
		this.paymentLastDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetPaymentLastDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setPaymentLastDate(Date o) {
		this.paymentLastDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.paymentLastDateWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment paymentLastDateInit() {
		if(!paymentLastDateWrap.alreadyInitialized) {
			_paymentLastDate(paymentLastDateWrap);
			if(paymentLastDate == null)
				setPaymentLastDate(paymentLastDateWrap.o);
		}
		paymentLastDateWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Date staticSolrPaymentLastDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrPaymentLastDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqPaymentLastDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrPaymentLastDate(siteRequest_, SchoolEnrollment.staticSolrPaymentLastDate(siteRequest_, SchoolEnrollment.staticSetPaymentLastDate(siteRequest_, o)));
	}

	public Date solrPaymentLastDate() {
		return SchoolEnrollment.staticSolrPaymentLastDate(siteRequest_, paymentLastDate);
	}

	public String strPaymentLastDate() {
		return paymentLastDate == null ? "" : paymentLastDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonPaymentLastDate() {
		return paymentLastDate == null ? "" : paymentLastDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichagePaymentLastDate() {
		return null;
	}

	public String htmTooltipPaymentLastDate() {
		return null;
	}

	public String htmPaymentLastDate() {
		return paymentLastDate == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentLastDate());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentLastStr">Find the entity paymentLastStr in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentLastStr(Wrap<String> c);

	public String getPaymentLastStr() {
		return paymentLastStr;
	}
	public void setPaymentLastStr(String o) {
		this.paymentLastStr = SchoolEnrollment.staticSetPaymentLastStr(siteRequest_, o);
		this.paymentLastStrWrap.alreadyInitialized = true;
	}
	public static String staticSetPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment paymentLastStrInit() {
		if(!paymentLastStrWrap.alreadyInitialized) {
			_paymentLastStr(paymentLastStrWrap);
			if(paymentLastStr == null)
				setPaymentLastStr(paymentLastStrWrap.o);
		}
		paymentLastStrWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrPaymentLastStr(siteRequest_, SchoolEnrollment.staticSolrPaymentLastStr(siteRequest_, SchoolEnrollment.staticSetPaymentLastStr(siteRequest_, o)));
	}

	public String solrPaymentLastStr() {
		return SchoolEnrollment.staticSolrPaymentLastStr(siteRequest_, paymentLastStr);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentAmount">Find the entity paymentAmount in Solr</a>
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
		this.paymentAmount = SchoolEnrollment.staticSetPaymentAmount(siteRequest_, o);
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
	protected SchoolEnrollment paymentAmountInit() {
		if(!paymentAmountWrap.alreadyInitialized) {
			_paymentAmount(paymentAmountWrap);
			if(paymentAmount == null)
				setPaymentAmount(paymentAmountWrap.o);
		}
		paymentAmountWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Double staticSolrPaymentAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaymentAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentAmount(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrPaymentAmount(siteRequest_, SchoolEnrollment.staticSolrPaymentAmount(siteRequest_, SchoolEnrollment.staticSetPaymentAmount(siteRequest_, o)));
	}

	public Double solrPaymentAmount() {
		return SchoolEnrollment.staticSolrPaymentAmount(siteRequest_, paymentAmount);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmount">Find the entity chargeAmount in Solr</a>
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
		this.chargeAmount = SchoolEnrollment.staticSetChargeAmount(siteRequest_, o);
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
	protected SchoolEnrollment chargeAmountInit() {
		if(!chargeAmountWrap.alreadyInitialized) {
			_chargeAmount(chargeAmountWrap);
			if(chargeAmount == null)
				setChargeAmount(chargeAmountWrap.o);
		}
		chargeAmountWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Double staticSolrChargeAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmount(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChargeAmount(siteRequest_, SchoolEnrollment.staticSolrChargeAmount(siteRequest_, SchoolEnrollment.staticSetChargeAmount(siteRequest_, o)));
	}

	public Double solrChargeAmount() {
		return SchoolEnrollment.staticSolrChargeAmount(siteRequest_, chargeAmount);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmountFuture">Find the entity chargeAmountFuture in Solr</a>
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
		this.chargeAmountFuture = SchoolEnrollment.staticSetChargeAmountFuture(siteRequest_, o);
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
	protected SchoolEnrollment chargeAmountFutureInit() {
		if(!chargeAmountFutureWrap.alreadyInitialized) {
			_chargeAmountFuture(chargeAmountFutureWrap);
			if(chargeAmountFuture == null)
				setChargeAmountFuture(chargeAmountFutureWrap.o);
		}
		chargeAmountFutureWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Double staticSolrChargeAmountFuture(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmountFuture(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmountFuture(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChargeAmountFuture(siteRequest_, SchoolEnrollment.staticSolrChargeAmountFuture(siteRequest_, SchoolEnrollment.staticSetChargeAmountFuture(siteRequest_, o)));
	}

	public Double solrChargeAmountFuture() {
		return SchoolEnrollment.staticSolrChargeAmountFuture(siteRequest_, chargeAmountFuture);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmountDue">Find the entity chargeAmountDue in Solr</a>
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
		this.chargeAmountDue = SchoolEnrollment.staticSetChargeAmountDue(siteRequest_, o);
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
	protected SchoolEnrollment chargeAmountDueInit() {
		if(!chargeAmountDueWrap.alreadyInitialized) {
			_chargeAmountDue(chargeAmountDueWrap);
			if(chargeAmountDue == null)
				setChargeAmountDue(chargeAmountDueWrap.o);
		}
		chargeAmountDueWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Double staticSolrChargeAmountDue(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmountDue(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmountDue(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChargeAmountDue(siteRequest_, SchoolEnrollment.staticSolrChargeAmountDue(siteRequest_, SchoolEnrollment.staticSetChargeAmountDue(siteRequest_, o)));
	}

	public Double solrChargeAmountDue() {
		return SchoolEnrollment.staticSolrChargeAmountDue(siteRequest_, chargeAmountDue);
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

	///////////////////////////
	// chargeAmountNotPassed //
	///////////////////////////

	/**	 The entity chargeAmountNotPassed
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmountNotPassed;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountNotPassedWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmountNotPassed").o(chargeAmountNotPassed);

	/**	<br/> The entity chargeAmountNotPassed
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmountNotPassed">Find the entity chargeAmountNotPassed in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmountNotPassed(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmountNotPassed() {
		return chargeAmountNotPassed;
	}

	public void setChargeAmountNotPassed(BigDecimal chargeAmountNotPassed) {
		this.chargeAmountNotPassed = chargeAmountNotPassed;
		this.chargeAmountNotPassedWrap.alreadyInitialized = true;
	}
	public void setChargeAmountNotPassed(String o) {
		this.chargeAmountNotPassed = SchoolEnrollment.staticSetChargeAmountNotPassed(siteRequest_, o);
		this.chargeAmountNotPassedWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmountNotPassed(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmountNotPassed(Double o) {
			this.chargeAmountNotPassed = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountNotPassedWrap.alreadyInitialized = true;
	}
	public void setChargeAmountNotPassed(Integer o) {
			this.chargeAmountNotPassed = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountNotPassedWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment chargeAmountNotPassedInit() {
		if(!chargeAmountNotPassedWrap.alreadyInitialized) {
			_chargeAmountNotPassed(chargeAmountNotPassedWrap);
			if(chargeAmountNotPassed == null)
				setChargeAmountNotPassed(chargeAmountNotPassedWrap.o);
		}
		chargeAmountNotPassedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Double staticSolrChargeAmountNotPassed(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmountNotPassed(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmountNotPassed(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChargeAmountNotPassed(siteRequest_, SchoolEnrollment.staticSolrChargeAmountNotPassed(siteRequest_, SchoolEnrollment.staticSetChargeAmountNotPassed(siteRequest_, o)));
	}

	public Double solrChargeAmountNotPassed() {
		return SchoolEnrollment.staticSolrChargeAmountNotPassed(siteRequest_, chargeAmountNotPassed);
	}

	public String strChargeAmountNotPassed() {
		return chargeAmountNotPassed == null ? "" : chargeAmountNotPassed.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonChargeAmountNotPassed() {
		return chargeAmountNotPassed == null ? "" : chargeAmountNotPassed.toString();
	}

	public String nomAffichageChargeAmountNotPassed() {
		return null;
	}

	public String htmTooltipChargeAmountNotPassed() {
		return null;
	}

	public String htmChargeAmountNotPassed() {
		return chargeAmountNotPassed == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmountNotPassed());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargesNow">Find the entity chargesNow in Solr</a>
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
		this.chargesNow = SchoolEnrollment.staticSetChargesNow(siteRequest_, o);
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
	protected SchoolEnrollment chargesNowInit() {
		if(!chargesNowWrap.alreadyInitialized) {
			_chargesNow(chargesNowWrap);
			if(chargesNow == null)
				setChargesNow(chargesNowWrap.o);
		}
		chargesNowWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Double staticSolrChargesNow(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargesNow(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargesNow(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChargesNow(siteRequest_, SchoolEnrollment.staticSolrChargesNow(siteRequest_, SchoolEnrollment.staticSetChargesNow(siteRequest_, o)));
	}

	public Double solrChargesNow() {
		return SchoolEnrollment.staticSolrChargesNow(siteRequest_, chargesNow);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsCurrent">Find the entity paymentsCurrent in Solr</a>
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
		this.paymentsCurrent = SchoolEnrollment.staticSetPaymentsCurrent(siteRequest_, o);
		this.paymentsCurrentWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentsCurrent(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolEnrollment paymentsCurrentInit() {
		if(!paymentsCurrentWrap.alreadyInitialized) {
			_paymentsCurrent(paymentsCurrentWrap);
			if(paymentsCurrent == null)
				setPaymentsCurrent(paymentsCurrentWrap.o);
		}
		paymentsCurrentWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Boolean staticSolrPaymentsCurrent(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentsCurrent(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsCurrent(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrPaymentsCurrent(siteRequest_, SchoolEnrollment.staticSolrPaymentsCurrent(siteRequest_, SchoolEnrollment.staticSetPaymentsCurrent(siteRequest_, o)));
	}

	public Boolean solrPaymentsCurrent() {
		return SchoolEnrollment.staticSolrPaymentsCurrent(siteRequest_, paymentsCurrent);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsLate">Find the entity paymentsLate in Solr</a>
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
		this.paymentsLate = SchoolEnrollment.staticSetPaymentsLate(siteRequest_, o);
		this.paymentsLateWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentsLate(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolEnrollment paymentsLateInit() {
		if(!paymentsLateWrap.alreadyInitialized) {
			_paymentsLate(paymentsLateWrap);
			if(paymentsLate == null)
				setPaymentsLate(paymentsLateWrap.o);
		}
		paymentsLateWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Boolean staticSolrPaymentsLate(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentsLate(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsLate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrPaymentsLate(siteRequest_, SchoolEnrollment.staticSolrPaymentsLate(siteRequest_, SchoolEnrollment.staticSetPaymentsLate(siteRequest_, o)));
	}

	public Boolean solrPaymentsLate() {
		return SchoolEnrollment.staticSolrPaymentsLate(siteRequest_, paymentsLate);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsLateAmount">Find the entity paymentsLateAmount in Solr</a>
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
		this.paymentsLateAmount = SchoolEnrollment.staticSetPaymentsLateAmount(siteRequest_, o);
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
	protected SchoolEnrollment paymentsLateAmountInit() {
		if(!paymentsLateAmountWrap.alreadyInitialized) {
			_paymentsLateAmount(paymentsLateAmountWrap);
			if(paymentsLateAmount == null)
				setPaymentsLateAmount(paymentsLateAmountWrap.o);
		}
		paymentsLateAmountWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Double staticSolrPaymentsLateAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaymentsLateAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsLateAmount(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrPaymentsLateAmount(siteRequest_, SchoolEnrollment.staticSolrPaymentsLateAmount(siteRequest_, SchoolEnrollment.staticSetPaymentsLateAmount(siteRequest_, o)));
	}

	public Double solrPaymentsLateAmount() {
		return SchoolEnrollment.staticSolrPaymentsLateAmount(siteRequest_, paymentsLateAmount);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsAhead">Find the entity paymentsAhead in Solr</a>
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
		this.paymentsAhead = SchoolEnrollment.staticSetPaymentsAhead(siteRequest_, o);
		this.paymentsAheadWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentsAhead(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolEnrollment paymentsAheadInit() {
		if(!paymentsAheadWrap.alreadyInitialized) {
			_paymentsAhead(paymentsAheadWrap);
			if(paymentsAhead == null)
				setPaymentsAhead(paymentsAheadWrap.o);
		}
		paymentsAheadWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Boolean staticSolrPaymentsAhead(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentsAhead(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsAhead(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrPaymentsAhead(siteRequest_, SchoolEnrollment.staticSolrPaymentsAhead(siteRequest_, SchoolEnrollment.staticSetPaymentsAhead(siteRequest_, o)));
	}

	public Boolean solrPaymentsAhead() {
		return SchoolEnrollment.staticSolrPaymentsAhead(siteRequest_, paymentsAhead);
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

	/////////////////////
	// paymentsPastDue //
	/////////////////////

	/**	 The entity paymentsPastDue
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentsPastDue;
	@JsonIgnore
	public Wrap<Boolean> paymentsPastDueWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentsPastDue").o(paymentsPastDue);

	/**	<br/> The entity paymentsPastDue
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsPastDue">Find the entity paymentsPastDue in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentsPastDue(Wrap<Boolean> c);

	public Boolean getPaymentsPastDue() {
		return paymentsPastDue;
	}

	public void setPaymentsPastDue(Boolean paymentsPastDue) {
		this.paymentsPastDue = paymentsPastDue;
		this.paymentsPastDueWrap.alreadyInitialized = true;
	}
	public void setPaymentsPastDue(String o) {
		this.paymentsPastDue = SchoolEnrollment.staticSetPaymentsPastDue(siteRequest_, o);
		this.paymentsPastDueWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentsPastDue(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolEnrollment paymentsPastDueInit() {
		if(!paymentsPastDueWrap.alreadyInitialized) {
			_paymentsPastDue(paymentsPastDueWrap);
			if(paymentsPastDue == null)
				setPaymentsPastDue(paymentsPastDueWrap.o);
		}
		paymentsPastDueWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Boolean staticSolrPaymentsPastDue(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentsPastDue(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsPastDue(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrPaymentsPastDue(siteRequest_, SchoolEnrollment.staticSolrPaymentsPastDue(siteRequest_, SchoolEnrollment.staticSetPaymentsPastDue(siteRequest_, o)));
	}

	public Boolean solrPaymentsPastDue() {
		return SchoolEnrollment.staticSolrPaymentsPastDue(siteRequest_, paymentsPastDue);
	}

	public String strPaymentsPastDue() {
		return paymentsPastDue == null ? "" : paymentsPastDue.toString();
	}

	public String jsonPaymentsPastDue() {
		return paymentsPastDue == null ? "" : paymentsPastDue.toString();
	}

	public String nomAffichagePaymentsPastDue() {
		return null;
	}

	public String htmTooltipPaymentsPastDue() {
		return null;
	}

	public String htmPaymentsPastDue() {
		return paymentsPastDue == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentsPastDue());
	}

	///////////////////////////
	// paymentsPastDueAmount //
	///////////////////////////

	/**	 The entity paymentsPastDueAmount
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal paymentsPastDueAmount;
	@JsonIgnore
	public Wrap<BigDecimal> paymentsPastDueAmountWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("paymentsPastDueAmount").o(paymentsPastDueAmount);

	/**	<br/> The entity paymentsPastDueAmount
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsPastDueAmount">Find the entity paymentsPastDueAmount in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentsPastDueAmount(Wrap<BigDecimal> c);

	public BigDecimal getPaymentsPastDueAmount() {
		return paymentsPastDueAmount;
	}

	public void setPaymentsPastDueAmount(BigDecimal paymentsPastDueAmount) {
		this.paymentsPastDueAmount = paymentsPastDueAmount;
		this.paymentsPastDueAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentsPastDueAmount(String o) {
		this.paymentsPastDueAmount = SchoolEnrollment.staticSetPaymentsPastDueAmount(siteRequest_, o);
		this.paymentsPastDueAmountWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetPaymentsPastDueAmount(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setPaymentsPastDueAmount(Double o) {
			this.paymentsPastDueAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentsPastDueAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentsPastDueAmount(Integer o) {
			this.paymentsPastDueAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentsPastDueAmountWrap.alreadyInitialized = true;
	}
	protected SchoolEnrollment paymentsPastDueAmountInit() {
		if(!paymentsPastDueAmountWrap.alreadyInitialized) {
			_paymentsPastDueAmount(paymentsPastDueAmountWrap);
			if(paymentsPastDueAmount == null)
				setPaymentsPastDueAmount(paymentsPastDueAmountWrap.o);
		}
		paymentsPastDueAmountWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Double staticSolrPaymentsPastDueAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaymentsPastDueAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsPastDueAmount(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrPaymentsPastDueAmount(siteRequest_, SchoolEnrollment.staticSolrPaymentsPastDueAmount(siteRequest_, SchoolEnrollment.staticSetPaymentsPastDueAmount(siteRequest_, o)));
	}

	public Double solrPaymentsPastDueAmount() {
		return SchoolEnrollment.staticSolrPaymentsPastDueAmount(siteRequest_, paymentsPastDueAmount);
	}

	public String strPaymentsPastDueAmount() {
		return paymentsPastDueAmount == null ? "" : paymentsPastDueAmount.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonPaymentsPastDueAmount() {
		return paymentsPastDueAmount == null ? "" : paymentsPastDueAmount.toString();
	}

	public String nomAffichagePaymentsPastDueAmount() {
		return null;
	}

	public String htmTooltipPaymentsPastDueAmount() {
		return null;
	}

	public String htmPaymentsPastDueAmount() {
		return paymentsPastDueAmount == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentsPastDueAmount());
	}

	////////////////////
	// chargesCreated //
	////////////////////

	/**	 The entity chargesCreated
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean chargesCreated;
	@JsonIgnore
	public Wrap<Boolean> chargesCreatedWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("chargesCreated").o(chargesCreated);

	/**	<br/> The entity chargesCreated
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargesCreated">Find the entity chargesCreated in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargesCreated(Wrap<Boolean> c);

	public Boolean getChargesCreated() {
		return chargesCreated;
	}

	public void setChargesCreated(Boolean chargesCreated) {
		this.chargesCreated = chargesCreated;
		this.chargesCreatedWrap.alreadyInitialized = true;
	}
	public void setChargesCreated(String o) {
		this.chargesCreated = SchoolEnrollment.staticSetChargesCreated(siteRequest_, o);
		this.chargesCreatedWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetChargesCreated(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolEnrollment chargesCreatedInit() {
		if(!chargesCreatedWrap.alreadyInitialized) {
			_chargesCreated(chargesCreatedWrap);
			if(chargesCreated == null)
				setChargesCreated(chargesCreatedWrap.o);
		}
		chargesCreatedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Boolean staticSolrChargesCreated(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrChargesCreated(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargesCreated(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChargesCreated(siteRequest_, SchoolEnrollment.staticSolrChargesCreated(siteRequest_, SchoolEnrollment.staticSetChargesCreated(siteRequest_, o)));
	}

	public Boolean solrChargesCreated() {
		return SchoolEnrollment.staticSolrChargesCreated(siteRequest_, chargesCreated);
	}

	public String strChargesCreated() {
		return chargesCreated == null ? "" : chargesCreated.toString();
	}

	public String jsonChargesCreated() {
		return chargesCreated == null ? "" : chargesCreated.toString();
	}

	public String nomAffichageChargesCreated() {
		return null;
	}

	public String htmTooltipChargesCreated() {
		return null;
	}

	public String htmChargesCreated() {
		return chargesCreated == null ? "" : StringEscapeUtils.escapeHtml4(strChargesCreated());
	}

	/////////////////
	// createdYear //
	/////////////////

	/**	 The entity createdYear
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer createdYear;
	@JsonIgnore
	public Wrap<Integer> createdYearWrap = new Wrap<Integer>().p(this).c(Integer.class).var("createdYear").o(createdYear);

	/**	<br/> The entity createdYear
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:createdYear">Find the entity createdYear in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _createdYear(Wrap<Integer> c);

	public Integer getCreatedYear() {
		return createdYear;
	}

	public void setCreatedYear(Integer createdYear) {
		this.createdYear = createdYear;
		this.createdYearWrap.alreadyInitialized = true;
	}
	public void setCreatedYear(String o) {
		this.createdYear = SchoolEnrollment.staticSetCreatedYear(siteRequest_, o);
		this.createdYearWrap.alreadyInitialized = true;
	}
	public static Integer staticSetCreatedYear(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolEnrollment createdYearInit() {
		if(!createdYearWrap.alreadyInitialized) {
			_createdYear(createdYearWrap);
			if(createdYear == null)
				setCreatedYear(createdYearWrap.o);
		}
		createdYearWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static Integer staticSolrCreatedYear(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrCreatedYear(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCreatedYear(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrCreatedYear(siteRequest_, SchoolEnrollment.staticSolrCreatedYear(siteRequest_, SchoolEnrollment.staticSetCreatedYear(siteRequest_, o)));
	}

	public Integer solrCreatedYear() {
		return SchoolEnrollment.staticSolrCreatedYear(siteRequest_, createdYear);
	}

	public String strCreatedYear() {
		return createdYear == null ? "" : createdYear.toString();
	}

	public String jsonCreatedYear() {
		return createdYear == null ? "" : createdYear.toString();
	}

	public String nomAffichageCreatedYear() {
		return "created year";
	}

	public String htmTooltipCreatedYear() {
		return null;
	}

	public String htmCreatedYear() {
		return createdYear == null ? "" : StringEscapeUtils.escapeHtml4(strCreatedYear());
	}

	//////////////////////
	// createdDayOfWeek //
	//////////////////////

	/**	 The entity createdDayOfWeek
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String createdDayOfWeek;
	@JsonIgnore
	public Wrap<String> createdDayOfWeekWrap = new Wrap<String>().p(this).c(String.class).var("createdDayOfWeek").o(createdDayOfWeek);

	/**	<br/> The entity createdDayOfWeek
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:createdDayOfWeek">Find the entity createdDayOfWeek in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _createdDayOfWeek(Wrap<String> c);

	public String getCreatedDayOfWeek() {
		return createdDayOfWeek;
	}
	public void setCreatedDayOfWeek(String o) {
		this.createdDayOfWeek = SchoolEnrollment.staticSetCreatedDayOfWeek(siteRequest_, o);
		this.createdDayOfWeekWrap.alreadyInitialized = true;
	}
	public static String staticSetCreatedDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment createdDayOfWeekInit() {
		if(!createdDayOfWeekWrap.alreadyInitialized) {
			_createdDayOfWeek(createdDayOfWeekWrap);
			if(createdDayOfWeek == null)
				setCreatedDayOfWeek(createdDayOfWeekWrap.o);
		}
		createdDayOfWeekWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrCreatedDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCreatedDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCreatedDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrCreatedDayOfWeek(siteRequest_, SchoolEnrollment.staticSolrCreatedDayOfWeek(siteRequest_, SchoolEnrollment.staticSetCreatedDayOfWeek(siteRequest_, o)));
	}

	public String solrCreatedDayOfWeek() {
		return SchoolEnrollment.staticSolrCreatedDayOfWeek(siteRequest_, createdDayOfWeek);
	}

	public String strCreatedDayOfWeek() {
		return createdDayOfWeek == null ? "" : createdDayOfWeek;
	}

	public String jsonCreatedDayOfWeek() {
		return createdDayOfWeek == null ? "" : createdDayOfWeek;
	}

	public String nomAffichageCreatedDayOfWeek() {
		return "created day of the week";
	}

	public String htmTooltipCreatedDayOfWeek() {
		return null;
	}

	public String htmCreatedDayOfWeek() {
		return createdDayOfWeek == null ? "" : StringEscapeUtils.escapeHtml4(strCreatedDayOfWeek());
	}

	////////////////////////
	// createdMonthOfYear //
	////////////////////////

	/**	 The entity createdMonthOfYear
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String createdMonthOfYear;
	@JsonIgnore
	public Wrap<String> createdMonthOfYearWrap = new Wrap<String>().p(this).c(String.class).var("createdMonthOfYear").o(createdMonthOfYear);

	/**	<br/> The entity createdMonthOfYear
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:createdMonthOfYear">Find the entity createdMonthOfYear in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _createdMonthOfYear(Wrap<String> c);

	public String getCreatedMonthOfYear() {
		return createdMonthOfYear;
	}
	public void setCreatedMonthOfYear(String o) {
		this.createdMonthOfYear = SchoolEnrollment.staticSetCreatedMonthOfYear(siteRequest_, o);
		this.createdMonthOfYearWrap.alreadyInitialized = true;
	}
	public static String staticSetCreatedMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment createdMonthOfYearInit() {
		if(!createdMonthOfYearWrap.alreadyInitialized) {
			_createdMonthOfYear(createdMonthOfYearWrap);
			if(createdMonthOfYear == null)
				setCreatedMonthOfYear(createdMonthOfYearWrap.o);
		}
		createdMonthOfYearWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrCreatedMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCreatedMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCreatedMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrCreatedMonthOfYear(siteRequest_, SchoolEnrollment.staticSolrCreatedMonthOfYear(siteRequest_, SchoolEnrollment.staticSetCreatedMonthOfYear(siteRequest_, o)));
	}

	public String solrCreatedMonthOfYear() {
		return SchoolEnrollment.staticSolrCreatedMonthOfYear(siteRequest_, createdMonthOfYear);
	}

	public String strCreatedMonthOfYear() {
		return createdMonthOfYear == null ? "" : createdMonthOfYear;
	}

	public String jsonCreatedMonthOfYear() {
		return createdMonthOfYear == null ? "" : createdMonthOfYear;
	}

	public String nomAffichageCreatedMonthOfYear() {
		return "created month of the year";
	}

	public String htmTooltipCreatedMonthOfYear() {
		return null;
	}

	public String htmCreatedMonthOfYear() {
		return createdMonthOfYear == null ? "" : StringEscapeUtils.escapeHtml4(strCreatedMonthOfYear());
	}

	//////////////////////
	// createdHourOfDay //
	//////////////////////

	/**	 The entity createdHourOfDay
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String createdHourOfDay;
	@JsonIgnore
	public Wrap<String> createdHourOfDayWrap = new Wrap<String>().p(this).c(String.class).var("createdHourOfDay").o(createdHourOfDay);

	/**	<br/> The entity createdHourOfDay
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:createdHourOfDay">Find the entity createdHourOfDay in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _createdHourOfDay(Wrap<String> c);

	public String getCreatedHourOfDay() {
		return createdHourOfDay;
	}
	public void setCreatedHourOfDay(String o) {
		this.createdHourOfDay = SchoolEnrollment.staticSetCreatedHourOfDay(siteRequest_, o);
		this.createdHourOfDayWrap.alreadyInitialized = true;
	}
	public static String staticSetCreatedHourOfDay(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment createdHourOfDayInit() {
		if(!createdHourOfDayWrap.alreadyInitialized) {
			_createdHourOfDay(createdHourOfDayWrap);
			if(createdHourOfDay == null)
				setCreatedHourOfDay(createdHourOfDayWrap.o);
		}
		createdHourOfDayWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrCreatedHourOfDay(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrCreatedHourOfDay(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCreatedHourOfDay(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrCreatedHourOfDay(siteRequest_, SchoolEnrollment.staticSolrCreatedHourOfDay(siteRequest_, SchoolEnrollment.staticSetCreatedHourOfDay(siteRequest_, o)));
	}

	public String solrCreatedHourOfDay() {
		return SchoolEnrollment.staticSolrCreatedHourOfDay(siteRequest_, createdHourOfDay);
	}

	public String strCreatedHourOfDay() {
		return createdHourOfDay == null ? "" : createdHourOfDay;
	}

	public String jsonCreatedHourOfDay() {
		return createdHourOfDay == null ? "" : createdHourOfDay;
	}

	public String nomAffichageCreatedHourOfDay() {
		return "hour of day";
	}

	public String htmTooltipCreatedHourOfDay() {
		return null;
	}

	public String htmCreatedHourOfDay() {
		return createdHourOfDay == null ? "" : StringEscapeUtils.escapeHtml4(strCreatedHourOfDay());
	}

	//////////////////////////
	// enrollmentDaysOfWeek //
	//////////////////////////

	/**	 The entity enrollmentDaysOfWeek
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> enrollmentDaysOfWeek = new ArrayList<String>();
	@JsonIgnore
	public Wrap<List<String>> enrollmentDaysOfWeekWrap = new Wrap<List<String>>().p(this).c(List.class).var("enrollmentDaysOfWeek").o(enrollmentDaysOfWeek);

	/**	<br/> The entity enrollmentDaysOfWeek
	 *  It is constructed before being initialized with the constructor by default List<String>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDaysOfWeek">Find the entity enrollmentDaysOfWeek in Solr</a>
	 * <br/>
	 * @param enrollmentDaysOfWeek is the entity already constructed. 
	 **/
	protected abstract void _enrollmentDaysOfWeek(List<String> l);

	public List<String> getEnrollmentDaysOfWeek() {
		return enrollmentDaysOfWeek;
	}

	public void setEnrollmentDaysOfWeek(List<String> enrollmentDaysOfWeek) {
		this.enrollmentDaysOfWeek = enrollmentDaysOfWeek;
		this.enrollmentDaysOfWeekWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentDaysOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolEnrollment addEnrollmentDaysOfWeek(String...objets) {
		for(String o : objets) {
			addEnrollmentDaysOfWeek(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addEnrollmentDaysOfWeek(String o) {
		if(o != null && !enrollmentDaysOfWeek.contains(o))
			this.enrollmentDaysOfWeek.add(o);
		return (SchoolEnrollment)this;
	}
	public void setEnrollmentDaysOfWeek(JsonArray objets) {
		enrollmentDaysOfWeek.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addEnrollmentDaysOfWeek(o);
		}
	}
	protected SchoolEnrollment enrollmentDaysOfWeekInit() {
		if(!enrollmentDaysOfWeekWrap.alreadyInitialized) {
			_enrollmentDaysOfWeek(enrollmentDaysOfWeek);
		}
		enrollmentDaysOfWeekWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentDaysOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentDaysOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentDaysOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentDaysOfWeek(siteRequest_, SchoolEnrollment.staticSolrEnrollmentDaysOfWeek(siteRequest_, SchoolEnrollment.staticSetEnrollmentDaysOfWeek(siteRequest_, o)));
	}

	public List<String> solrEnrollmentDaysOfWeek() {
		List<String> l = new ArrayList<String>();
		for(String o : enrollmentDaysOfWeek) {
			l.add(SchoolEnrollment.staticSolrEnrollmentDaysOfWeek(siteRequest_, o));
		}
		return l;
	}

	public String strEnrollmentDaysOfWeek() {
		return enrollmentDaysOfWeek == null ? "" : enrollmentDaysOfWeek.toString();
	}

	public String jsonEnrollmentDaysOfWeek() {
		return enrollmentDaysOfWeek == null ? "" : enrollmentDaysOfWeek.toString();
	}

	public String nomAffichageEnrollmentDaysOfWeek() {
		return "days of the week";
	}

	public String htmTooltipEnrollmentDaysOfWeek() {
		return null;
	}

	public String htmEnrollmentDaysOfWeek() {
		return enrollmentDaysOfWeek == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDaysOfWeek());
	}

	///////////////////////////
	// enrollmentParentNames //
	///////////////////////////

	/**	 The entity enrollmentParentNames
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentParentNames;
	@JsonIgnore
	public Wrap<String> enrollmentParentNamesWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentParentNames").o(enrollmentParentNames);

	/**	<br/> The entity enrollmentParentNames
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentParentNames">Find the entity enrollmentParentNames in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentParentNames(Wrap<String> c);

	public String getEnrollmentParentNames() {
		return enrollmentParentNames;
	}
	public void setEnrollmentParentNames(String o) {
		this.enrollmentParentNames = SchoolEnrollment.staticSetEnrollmentParentNames(siteRequest_, o);
		this.enrollmentParentNamesWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentParentNames(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentParentNames(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentParentNames(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentParentNames(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentParentNames(siteRequest_, SchoolEnrollment.staticSolrEnrollmentParentNames(siteRequest_, SchoolEnrollment.staticSetEnrollmentParentNames(siteRequest_, o)));
	}

	public String solrEnrollmentParentNames() {
		return SchoolEnrollment.staticSolrEnrollmentParentNames(siteRequest_, enrollmentParentNames);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("id", classApiMethodMethod, "_enrollmentParentNames");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setEnrollmentParentNames classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentParentNames w3-input w3-border ");
					a("name", "setEnrollmentParentNames");
				} else {
					a("class", "valueEnrollmentParentNames w3-input w3-border classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentParentNames w3-input w3-border ");
					a("name", "enrollmentParentNames");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentParentNames', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_enrollmentParentNames')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentParentNames')); }); ");
				}
				a("value", strEnrollmentParentNames())
			.fg();

		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentParentNames ").f().sx(htmEnrollmentParentNames()).g("span");
		}
	}

	public void htmEnrollmentParentNames(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentParentNames").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentParentNames(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentParentNames')); $('#", classApiMethodMethod, "_enrollmentParentNames').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentParentNames', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentParentNames')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentParentNames')); }); ")
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

	//////////////////////
	// enrollmentEmails //
	//////////////////////

	/**	 The entity enrollmentEmails
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> enrollmentEmails = new ArrayList<String>();
	@JsonIgnore
	public Wrap<List<String>> enrollmentEmailsWrap = new Wrap<List<String>>().p(this).c(List.class).var("enrollmentEmails").o(enrollmentEmails);

	/**	<br/> The entity enrollmentEmails
	 *  It is constructed before being initialized with the constructor by default List<String>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentEmails">Find the entity enrollmentEmails in Solr</a>
	 * <br/>
	 * @param enrollmentEmails is the entity already constructed. 
	 **/
	protected abstract void _enrollmentEmails(List<String> l);

	public List<String> getEnrollmentEmails() {
		return enrollmentEmails;
	}

	public void setEnrollmentEmails(List<String> enrollmentEmails) {
		this.enrollmentEmails = enrollmentEmails;
		this.enrollmentEmailsWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentEmails(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolEnrollment addEnrollmentEmails(String...objets) {
		for(String o : objets) {
			addEnrollmentEmails(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addEnrollmentEmails(String o) {
		if(o != null && !enrollmentEmails.contains(o))
			this.enrollmentEmails.add(o);
		return (SchoolEnrollment)this;
	}
	public void setEnrollmentEmails(JsonArray objets) {
		enrollmentEmails.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addEnrollmentEmails(o);
		}
	}
	protected SchoolEnrollment enrollmentEmailsInit() {
		if(!enrollmentEmailsWrap.alreadyInitialized) {
			_enrollmentEmails(enrollmentEmails);
		}
		enrollmentEmailsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentEmails(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentEmails(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentEmails(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentEmails(siteRequest_, SchoolEnrollment.staticSolrEnrollmentEmails(siteRequest_, SchoolEnrollment.staticSetEnrollmentEmails(siteRequest_, o)));
	}

	public List<String> solrEnrollmentEmails() {
		List<String> l = new ArrayList<String>();
		for(String o : enrollmentEmails) {
			l.add(SchoolEnrollment.staticSolrEnrollmentEmails(siteRequest_, o));
		}
		return l;
	}

	public String strEnrollmentEmails() {
		return enrollmentEmails == null ? "" : enrollmentEmails.toString();
	}

	public String jsonEnrollmentEmails() {
		return enrollmentEmails == null ? "" : enrollmentEmails.toString();
	}

	public String nomAffichageEnrollmentEmails() {
		return null;
	}

	public String htmTooltipEnrollmentEmails() {
		return null;
	}

	public String htmEnrollmentEmails() {
		return enrollmentEmails == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentEmails());
	}

	/////////////////////
	// enrollmentEmail //
	/////////////////////

	/**	 The entity enrollmentEmail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentEmail;
	@JsonIgnore
	public Wrap<String> enrollmentEmailWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentEmail").o(enrollmentEmail);

	/**	<br/> The entity enrollmentEmail
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentEmail">Find the entity enrollmentEmail in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentEmail(Wrap<String> c);

	public String getEnrollmentEmail() {
		return enrollmentEmail;
	}
	public void setEnrollmentEmail(String o) {
		this.enrollmentEmail = SchoolEnrollment.staticSetEnrollmentEmail(siteRequest_, o);
		this.enrollmentEmailWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentEmail(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment enrollmentEmailInit() {
		if(!enrollmentEmailWrap.alreadyInitialized) {
			_enrollmentEmail(enrollmentEmailWrap);
			if(enrollmentEmail == null)
				setEnrollmentEmail(enrollmentEmailWrap.o);
		}
		enrollmentEmailWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentEmail(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentEmail(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentEmail(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentEmail(siteRequest_, SchoolEnrollment.staticSolrEnrollmentEmail(siteRequest_, SchoolEnrollment.staticSetEnrollmentEmail(siteRequest_, o)));
	}

	public String solrEnrollmentEmail() {
		return SchoolEnrollment.staticSolrEnrollmentEmail(siteRequest_, enrollmentEmail);
	}

	public String strEnrollmentEmail() {
		return enrollmentEmail == null ? "" : enrollmentEmail;
	}

	public String jsonEnrollmentEmail() {
		return enrollmentEmail == null ? "" : enrollmentEmail;
	}

	public String nomAffichageEnrollmentEmail() {
		return null;
	}

	public String htmTooltipEnrollmentEmail() {
		return null;
	}

	public String htmEnrollmentEmail() {
		return enrollmentEmail == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentEmail());
	}

	////////////////////////////
	// enrollmentParentEmails //
	////////////////////////////

	/**	 The entity enrollmentParentEmails
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentParentEmails;
	@JsonIgnore
	public Wrap<String> enrollmentParentEmailsWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentParentEmails").o(enrollmentParentEmails);

	/**	<br/> The entity enrollmentParentEmails
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentParentEmails">Find the entity enrollmentParentEmails in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentParentEmails(Wrap<String> c);

	public String getEnrollmentParentEmails() {
		return enrollmentParentEmails;
	}
	public void setEnrollmentParentEmails(String o) {
		this.enrollmentParentEmails = SchoolEnrollment.staticSetEnrollmentParentEmails(siteRequest_, o);
		this.enrollmentParentEmailsWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentParentEmails(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment enrollmentParentEmailsInit() {
		if(!enrollmentParentEmailsWrap.alreadyInitialized) {
			_enrollmentParentEmails(enrollmentParentEmailsWrap);
			if(enrollmentParentEmails == null)
				setEnrollmentParentEmails(enrollmentParentEmailsWrap.o);
		}
		enrollmentParentEmailsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentParentEmails(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentParentEmails(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentParentEmails(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentParentEmails(siteRequest_, SchoolEnrollment.staticSolrEnrollmentParentEmails(siteRequest_, SchoolEnrollment.staticSetEnrollmentParentEmails(siteRequest_, o)));
	}

	public String solrEnrollmentParentEmails() {
		return SchoolEnrollment.staticSolrEnrollmentParentEmails(siteRequest_, enrollmentParentEmails);
	}

	public String strEnrollmentParentEmails() {
		return enrollmentParentEmails == null ? "" : enrollmentParentEmails;
	}

	public String jsonEnrollmentParentEmails() {
		return enrollmentParentEmails == null ? "" : enrollmentParentEmails;
	}

	public String nomAffichageEnrollmentParentEmails() {
		return null;
	}

	public String htmTooltipEnrollmentParentEmails() {
		return null;
	}

	public String htmEnrollmentParentEmails() {
		return enrollmentParentEmails == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentParentEmails());
	}

	////////////////////////////
	// enrollmentPhoneNumbers //
	////////////////////////////

	/**	 The entity enrollmentPhoneNumbers
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> enrollmentPhoneNumbers = new ArrayList<String>();
	@JsonIgnore
	public Wrap<List<String>> enrollmentPhoneNumbersWrap = new Wrap<List<String>>().p(this).c(List.class).var("enrollmentPhoneNumbers").o(enrollmentPhoneNumbers);

	/**	<br/> The entity enrollmentPhoneNumbers
	 *  It is constructed before being initialized with the constructor by default List<String>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentPhoneNumbers">Find the entity enrollmentPhoneNumbers in Solr</a>
	 * <br/>
	 * @param enrollmentPhoneNumbers is the entity already constructed. 
	 **/
	protected abstract void _enrollmentPhoneNumbers(List<String> l);

	public List<String> getEnrollmentPhoneNumbers() {
		return enrollmentPhoneNumbers;
	}

	public void setEnrollmentPhoneNumbers(List<String> enrollmentPhoneNumbers) {
		this.enrollmentPhoneNumbers = enrollmentPhoneNumbers;
		this.enrollmentPhoneNumbersWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentPhoneNumbers(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolEnrollment addEnrollmentPhoneNumbers(String...objets) {
		for(String o : objets) {
			addEnrollmentPhoneNumbers(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addEnrollmentPhoneNumbers(String o) {
		if(o != null && !enrollmentPhoneNumbers.contains(o))
			this.enrollmentPhoneNumbers.add(o);
		return (SchoolEnrollment)this;
	}
	public void setEnrollmentPhoneNumbers(JsonArray objets) {
		enrollmentPhoneNumbers.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addEnrollmentPhoneNumbers(o);
		}
	}
	protected SchoolEnrollment enrollmentPhoneNumbersInit() {
		if(!enrollmentPhoneNumbersWrap.alreadyInitialized) {
			_enrollmentPhoneNumbers(enrollmentPhoneNumbers);
		}
		enrollmentPhoneNumbersWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentPhoneNumbers(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentPhoneNumbers(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentPhoneNumbers(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentPhoneNumbers(siteRequest_, SchoolEnrollment.staticSolrEnrollmentPhoneNumbers(siteRequest_, SchoolEnrollment.staticSetEnrollmentPhoneNumbers(siteRequest_, o)));
	}

	public List<String> solrEnrollmentPhoneNumbers() {
		List<String> l = new ArrayList<String>();
		for(String o : enrollmentPhoneNumbers) {
			l.add(SchoolEnrollment.staticSolrEnrollmentPhoneNumbers(siteRequest_, o));
		}
		return l;
	}

	public String strEnrollmentPhoneNumbers() {
		return enrollmentPhoneNumbers == null ? "" : enrollmentPhoneNumbers.toString();
	}

	public String jsonEnrollmentPhoneNumbers() {
		return enrollmentPhoneNumbers == null ? "" : enrollmentPhoneNumbers.toString();
	}

	public String nomAffichageEnrollmentPhoneNumbers() {
		return null;
	}

	public String htmTooltipEnrollmentPhoneNumbers() {
		return null;
	}

	public String htmEnrollmentPhoneNumbers() {
		return enrollmentPhoneNumbers == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentPhoneNumbers());
	}

	///////////////////////////
	// enrollmentPhoneNumber //
	///////////////////////////

	/**	 The entity enrollmentPhoneNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentPhoneNumber;
	@JsonIgnore
	public Wrap<String> enrollmentPhoneNumberWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentPhoneNumber").o(enrollmentPhoneNumber);

	/**	<br/> The entity enrollmentPhoneNumber
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentPhoneNumber">Find the entity enrollmentPhoneNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentPhoneNumber(Wrap<String> c);

	public String getEnrollmentPhoneNumber() {
		return enrollmentPhoneNumber;
	}
	public void setEnrollmentPhoneNumber(String o) {
		this.enrollmentPhoneNumber = SchoolEnrollment.staticSetEnrollmentPhoneNumber(siteRequest_, o);
		this.enrollmentPhoneNumberWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment enrollmentPhoneNumberInit() {
		if(!enrollmentPhoneNumberWrap.alreadyInitialized) {
			_enrollmentPhoneNumber(enrollmentPhoneNumberWrap);
			if(enrollmentPhoneNumber == null)
				setEnrollmentPhoneNumber(enrollmentPhoneNumberWrap.o);
		}
		enrollmentPhoneNumberWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentPhoneNumber(siteRequest_, SchoolEnrollment.staticSolrEnrollmentPhoneNumber(siteRequest_, SchoolEnrollment.staticSetEnrollmentPhoneNumber(siteRequest_, o)));
	}

	public String solrEnrollmentPhoneNumber() {
		return SchoolEnrollment.staticSolrEnrollmentPhoneNumber(siteRequest_, enrollmentPhoneNumber);
	}

	public String strEnrollmentPhoneNumber() {
		return enrollmentPhoneNumber == null ? "" : enrollmentPhoneNumber;
	}

	public String jsonEnrollmentPhoneNumber() {
		return enrollmentPhoneNumber == null ? "" : enrollmentPhoneNumber;
	}

	public String nomAffichageEnrollmentPhoneNumber() {
		return null;
	}

	public String htmTooltipEnrollmentPhoneNumber() {
		return null;
	}

	public String htmEnrollmentPhoneNumber() {
		return enrollmentPhoneNumber == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentPhoneNumber());
	}

	//////////////////////////
	// enrollmentParentName //
	//////////////////////////

	/**	 The entity enrollmentParentName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentParentName;
	@JsonIgnore
	public Wrap<String> enrollmentParentNameWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentParentName").o(enrollmentParentName);

	/**	<br/> The entity enrollmentParentName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentParentName">Find the entity enrollmentParentName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentParentName(Wrap<String> c);

	public String getEnrollmentParentName() {
		return enrollmentParentName;
	}
	public void setEnrollmentParentName(String o) {
		this.enrollmentParentName = SchoolEnrollment.staticSetEnrollmentParentName(siteRequest_, o);
		this.enrollmentParentNameWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentParentName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment enrollmentParentNameInit() {
		if(!enrollmentParentNameWrap.alreadyInitialized) {
			_enrollmentParentName(enrollmentParentNameWrap);
			if(enrollmentParentName == null)
				setEnrollmentParentName(enrollmentParentNameWrap.o);
		}
		enrollmentParentNameWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentParentName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentParentName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentParentName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentParentName(siteRequest_, SchoolEnrollment.staticSolrEnrollmentParentName(siteRequest_, SchoolEnrollment.staticSetEnrollmentParentName(siteRequest_, o)));
	}

	public String solrEnrollmentParentName() {
		return SchoolEnrollment.staticSolrEnrollmentParentName(siteRequest_, enrollmentParentName);
	}

	public String strEnrollmentParentName() {
		return enrollmentParentName == null ? "" : enrollmentParentName;
	}

	public String jsonEnrollmentParentName() {
		return enrollmentParentName == null ? "" : enrollmentParentName;
	}

	public String nomAffichageEnrollmentParentName() {
		return null;
	}

	public String htmTooltipEnrollmentParentName() {
		return null;
	}

	public String htmEnrollmentParentName() {
		return enrollmentParentName == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentParentName());
	}

	///////////////////////////////
	// enrollmentParentNameLines //
	///////////////////////////////

	/**	 The entity enrollmentParentNameLines
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentParentNameLines;
	@JsonIgnore
	public Wrap<String> enrollmentParentNameLinesWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentParentNameLines").o(enrollmentParentNameLines);

	/**	<br/> The entity enrollmentParentNameLines
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentParentNameLines">Find the entity enrollmentParentNameLines in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentParentNameLines(Wrap<String> c);

	public String getEnrollmentParentNameLines() {
		return enrollmentParentNameLines;
	}
	public void setEnrollmentParentNameLines(String o) {
		this.enrollmentParentNameLines = SchoolEnrollment.staticSetEnrollmentParentNameLines(siteRequest_, o);
		this.enrollmentParentNameLinesWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentParentNameLines(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment enrollmentParentNameLinesInit() {
		if(!enrollmentParentNameLinesWrap.alreadyInitialized) {
			_enrollmentParentNameLines(enrollmentParentNameLinesWrap);
			if(enrollmentParentNameLines == null)
				setEnrollmentParentNameLines(enrollmentParentNameLinesWrap.o);
		}
		enrollmentParentNameLinesWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentParentNameLines(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentParentNameLines(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentParentNameLines(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentParentNameLines(siteRequest_, SchoolEnrollment.staticSolrEnrollmentParentNameLines(siteRequest_, SchoolEnrollment.staticSetEnrollmentParentNameLines(siteRequest_, o)));
	}

	public String solrEnrollmentParentNameLines() {
		return SchoolEnrollment.staticSolrEnrollmentParentNameLines(siteRequest_, enrollmentParentNameLines);
	}

	public String strEnrollmentParentNameLines() {
		return enrollmentParentNameLines == null ? "" : enrollmentParentNameLines;
	}

	public String jsonEnrollmentParentNameLines() {
		return enrollmentParentNameLines == null ? "" : enrollmentParentNameLines;
	}

	public String nomAffichageEnrollmentParentNameLines() {
		return null;
	}

	public String htmTooltipEnrollmentParentNameLines() {
		return null;
	}

	public String htmEnrollmentParentNameLines() {
		return enrollmentParentNameLines == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentParentNameLines());
	}

	////////////////////////////////
	// enrollmentParentEmailLines //
	////////////////////////////////

	/**	 The entity enrollmentParentEmailLines
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentParentEmailLines;
	@JsonIgnore
	public Wrap<String> enrollmentParentEmailLinesWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentParentEmailLines").o(enrollmentParentEmailLines);

	/**	<br/> The entity enrollmentParentEmailLines
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentParentEmailLines">Find the entity enrollmentParentEmailLines in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentParentEmailLines(Wrap<String> c);

	public String getEnrollmentParentEmailLines() {
		return enrollmentParentEmailLines;
	}
	public void setEnrollmentParentEmailLines(String o) {
		this.enrollmentParentEmailLines = SchoolEnrollment.staticSetEnrollmentParentEmailLines(siteRequest_, o);
		this.enrollmentParentEmailLinesWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentParentEmailLines(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment enrollmentParentEmailLinesInit() {
		if(!enrollmentParentEmailLinesWrap.alreadyInitialized) {
			_enrollmentParentEmailLines(enrollmentParentEmailLinesWrap);
			if(enrollmentParentEmailLines == null)
				setEnrollmentParentEmailLines(enrollmentParentEmailLinesWrap.o);
		}
		enrollmentParentEmailLinesWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentParentEmailLines(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentParentEmailLines(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentParentEmailLines(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentParentEmailLines(siteRequest_, SchoolEnrollment.staticSolrEnrollmentParentEmailLines(siteRequest_, SchoolEnrollment.staticSetEnrollmentParentEmailLines(siteRequest_, o)));
	}

	public String solrEnrollmentParentEmailLines() {
		return SchoolEnrollment.staticSolrEnrollmentParentEmailLines(siteRequest_, enrollmentParentEmailLines);
	}

	public String strEnrollmentParentEmailLines() {
		return enrollmentParentEmailLines == null ? "" : enrollmentParentEmailLines;
	}

	public String jsonEnrollmentParentEmailLines() {
		return enrollmentParentEmailLines == null ? "" : enrollmentParentEmailLines;
	}

	public String nomAffichageEnrollmentParentEmailLines() {
		return null;
	}

	public String htmTooltipEnrollmentParentEmailLines() {
		return null;
	}

	public String htmEnrollmentParentEmailLines() {
		return enrollmentParentEmailLines == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentParentEmailLines());
	}

	/////////////////////////////////
	// enrollmentParentDetailLines //
	/////////////////////////////////

	/**	 The entity enrollmentParentDetailLines
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentParentDetailLines;
	@JsonIgnore
	public Wrap<String> enrollmentParentDetailLinesWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentParentDetailLines").o(enrollmentParentDetailLines);

	/**	<br/> The entity enrollmentParentDetailLines
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentParentDetailLines">Find the entity enrollmentParentDetailLines in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentParentDetailLines(Wrap<String> c);

	public String getEnrollmentParentDetailLines() {
		return enrollmentParentDetailLines;
	}
	public void setEnrollmentParentDetailLines(String o) {
		this.enrollmentParentDetailLines = SchoolEnrollment.staticSetEnrollmentParentDetailLines(siteRequest_, o);
		this.enrollmentParentDetailLinesWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentParentDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment enrollmentParentDetailLinesInit() {
		if(!enrollmentParentDetailLinesWrap.alreadyInitialized) {
			_enrollmentParentDetailLines(enrollmentParentDetailLinesWrap);
			if(enrollmentParentDetailLines == null)
				setEnrollmentParentDetailLines(enrollmentParentDetailLinesWrap.o);
		}
		enrollmentParentDetailLinesWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentParentDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentParentDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentParentDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentParentDetailLines(siteRequest_, SchoolEnrollment.staticSolrEnrollmentParentDetailLines(siteRequest_, SchoolEnrollment.staticSetEnrollmentParentDetailLines(siteRequest_, o)));
	}

	public String solrEnrollmentParentDetailLines() {
		return SchoolEnrollment.staticSolrEnrollmentParentDetailLines(siteRequest_, enrollmentParentDetailLines);
	}

	public String strEnrollmentParentDetailLines() {
		return enrollmentParentDetailLines == null ? "" : enrollmentParentDetailLines;
	}

	public String jsonEnrollmentParentDetailLines() {
		return enrollmentParentDetailLines == null ? "" : enrollmentParentDetailLines;
	}

	public String nomAffichageEnrollmentParentDetailLines() {
		return null;
	}

	public String htmTooltipEnrollmentParentDetailLines() {
		return null;
	}

	public String htmEnrollmentParentDetailLines() {
		return enrollmentParentDetailLines == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentParentDetailLines());
	}

	/////////////////////////////////
	// enrollmentPickupDetailLines //
	/////////////////////////////////

	/**	 The entity enrollmentPickupDetailLines
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentPickupDetailLines;
	@JsonIgnore
	public Wrap<String> enrollmentPickupDetailLinesWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentPickupDetailLines").o(enrollmentPickupDetailLines);

	/**	<br/> The entity enrollmentPickupDetailLines
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentPickupDetailLines">Find the entity enrollmentPickupDetailLines in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentPickupDetailLines(Wrap<String> c);

	public String getEnrollmentPickupDetailLines() {
		return enrollmentPickupDetailLines;
	}
	public void setEnrollmentPickupDetailLines(String o) {
		this.enrollmentPickupDetailLines = SchoolEnrollment.staticSetEnrollmentPickupDetailLines(siteRequest_, o);
		this.enrollmentPickupDetailLinesWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentPickupDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment enrollmentPickupDetailLinesInit() {
		if(!enrollmentPickupDetailLinesWrap.alreadyInitialized) {
			_enrollmentPickupDetailLines(enrollmentPickupDetailLinesWrap);
			if(enrollmentPickupDetailLines == null)
				setEnrollmentPickupDetailLines(enrollmentPickupDetailLinesWrap.o);
		}
		enrollmentPickupDetailLinesWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentPickupDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentPickupDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentPickupDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentPickupDetailLines(siteRequest_, SchoolEnrollment.staticSolrEnrollmentPickupDetailLines(siteRequest_, SchoolEnrollment.staticSetEnrollmentPickupDetailLines(siteRequest_, o)));
	}

	public String solrEnrollmentPickupDetailLines() {
		return SchoolEnrollment.staticSolrEnrollmentPickupDetailLines(siteRequest_, enrollmentPickupDetailLines);
	}

	public String strEnrollmentPickupDetailLines() {
		return enrollmentPickupDetailLines == null ? "" : enrollmentPickupDetailLines;
	}

	public String jsonEnrollmentPickupDetailLines() {
		return enrollmentPickupDetailLines == null ? "" : enrollmentPickupDetailLines;
	}

	public String nomAffichageEnrollmentPickupDetailLines() {
		return null;
	}

	public String htmTooltipEnrollmentPickupDetailLines() {
		return null;
	}

	public String htmEnrollmentPickupDetailLines() {
		return enrollmentPickupDetailLines == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentPickupDetailLines());
	}

	///////////////////////////////////////////
	// enrollmentEmergencyContactDetailLines //
	///////////////////////////////////////////

	/**	 The entity enrollmentEmergencyContactDetailLines
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentEmergencyContactDetailLines;
	@JsonIgnore
	public Wrap<String> enrollmentEmergencyContactDetailLinesWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentEmergencyContactDetailLines").o(enrollmentEmergencyContactDetailLines);

	/**	<br/> The entity enrollmentEmergencyContactDetailLines
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentEmergencyContactDetailLines">Find the entity enrollmentEmergencyContactDetailLines in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentEmergencyContactDetailLines(Wrap<String> c);

	public String getEnrollmentEmergencyContactDetailLines() {
		return enrollmentEmergencyContactDetailLines;
	}
	public void setEnrollmentEmergencyContactDetailLines(String o) {
		this.enrollmentEmergencyContactDetailLines = SchoolEnrollment.staticSetEnrollmentEmergencyContactDetailLines(siteRequest_, o);
		this.enrollmentEmergencyContactDetailLinesWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentEmergencyContactDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment enrollmentEmergencyContactDetailLinesInit() {
		if(!enrollmentEmergencyContactDetailLinesWrap.alreadyInitialized) {
			_enrollmentEmergencyContactDetailLines(enrollmentEmergencyContactDetailLinesWrap);
			if(enrollmentEmergencyContactDetailLines == null)
				setEnrollmentEmergencyContactDetailLines(enrollmentEmergencyContactDetailLinesWrap.o);
		}
		enrollmentEmergencyContactDetailLinesWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrEnrollmentEmergencyContactDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentEmergencyContactDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentEmergencyContactDetailLines(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentEmergencyContactDetailLines(siteRequest_, SchoolEnrollment.staticSolrEnrollmentEmergencyContactDetailLines(siteRequest_, SchoolEnrollment.staticSetEnrollmentEmergencyContactDetailLines(siteRequest_, o)));
	}

	public String solrEnrollmentEmergencyContactDetailLines() {
		return SchoolEnrollment.staticSolrEnrollmentEmergencyContactDetailLines(siteRequest_, enrollmentEmergencyContactDetailLines);
	}

	public String strEnrollmentEmergencyContactDetailLines() {
		return enrollmentEmergencyContactDetailLines == null ? "" : enrollmentEmergencyContactDetailLines;
	}

	public String jsonEnrollmentEmergencyContactDetailLines() {
		return enrollmentEmergencyContactDetailLines == null ? "" : enrollmentEmergencyContactDetailLines;
	}

	public String nomAffichageEnrollmentEmergencyContactDetailLines() {
		return null;
	}

	public String htmTooltipEnrollmentEmergencyContactDetailLines() {
		return null;
	}

	public String htmEnrollmentEmergencyContactDetailLines() {
		return enrollmentEmergencyContactDetailLines == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentEmergencyContactDetailLines());
	}

	//////////////////////////
	// enrollmentSignature1 //
	//////////////////////////

	/**	 The entity enrollmentSignature1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentSignature1;
	@JsonIgnore
	public Wrap<String> enrollmentSignature1Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature1").o(enrollmentSignature1);

	/**	<br/> The entity enrollmentSignature1
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature1">Find the entity enrollmentSignature1 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSignature1(Wrap<String> c);

	public String getEnrollmentSignature1() {
		return enrollmentSignature1;
	}
	public void setEnrollmentSignature1(String o) {
		this.enrollmentSignature1 = SchoolEnrollment.staticSetEnrollmentSignature1(siteRequest_, o);
		this.enrollmentSignature1Wrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentSignature1(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentSignature1(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentSignature1(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentSignature1(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentSignature1(siteRequest_, SchoolEnrollment.staticSolrEnrollmentSignature1(siteRequest_, SchoolEnrollment.staticSetEnrollmentSignature1(siteRequest_, o)));
	}

	public String solrEnrollmentSignature1() {
		return SchoolEnrollment.staticSolrEnrollmentSignature1(siteRequest_, enrollmentSignature1);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "signatureDiv1SchoolEnrollment_enrollmentSignature1 signatureInputSchoolEnrollment", pk, "EnrollmentSignature1").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature1").f();
				e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature1");
					a("style", "display: ", StringUtils.isBlank(enrollmentSignature1) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature1");
					a("class", "signatureImgSchoolEnrollment", pk, "EnrollmentSignature1 ");
					a("src", StringUtils.isBlank(enrollmentSignature1) ? "data:image/png;base64," : enrollmentSignature1).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature1) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature1").f();
				e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature1");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature1').show(); ");
						s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature1').hide(); ");
						s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature1')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature1', null); ");
						s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature1')) { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature1').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature1').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Clear");
				g("button");
			g("div");
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentSignature1 ").f().sx(htmEnrollmentSignature1()).g("span");
		}
	}

	public void htmEnrollmentSignature1(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentSignature1").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature1(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature1')); $('#", classApiMethodMethod, "_enrollmentSignature1').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature1', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature1')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature1')); }); ")
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
	// enrollmentSignature2 //
	//////////////////////////

	/**	 The entity enrollmentSignature2
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentSignature2;
	@JsonIgnore
	public Wrap<String> enrollmentSignature2Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature2").o(enrollmentSignature2);

	/**	<br/> The entity enrollmentSignature2
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature2">Find the entity enrollmentSignature2 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSignature2(Wrap<String> c);

	public String getEnrollmentSignature2() {
		return enrollmentSignature2;
	}
	public void setEnrollmentSignature2(String o) {
		this.enrollmentSignature2 = SchoolEnrollment.staticSetEnrollmentSignature2(siteRequest_, o);
		this.enrollmentSignature2Wrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentSignature2(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentSignature2(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentSignature2(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentSignature2(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentSignature2(siteRequest_, SchoolEnrollment.staticSolrEnrollmentSignature2(siteRequest_, SchoolEnrollment.staticSetEnrollmentSignature2(siteRequest_, o)));
	}

	public String solrEnrollmentSignature2() {
		return SchoolEnrollment.staticSolrEnrollmentSignature2(siteRequest_, enrollmentSignature2);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "signatureDiv1SchoolEnrollment_enrollmentSignature2 signatureInputSchoolEnrollment", pk, "EnrollmentSignature2").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature2").f();
				e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature2");
					a("style", "display: ", StringUtils.isBlank(enrollmentSignature2) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature2");
					a("class", "signatureImgSchoolEnrollment", pk, "EnrollmentSignature2 ");
					a("src", StringUtils.isBlank(enrollmentSignature2) ? "data:image/png;base64," : enrollmentSignature2).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature2) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature2").f();
				e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature2");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature2').show(); ");
						s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature2').hide(); ");
						s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature2')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature2', null); ");
						s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature2')) { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature2').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature2').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Clear");
				g("button");
			g("div");
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentSignature2 ").f().sx(htmEnrollmentSignature2()).g("span");
		}
	}

	public void htmEnrollmentSignature2(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentSignature2").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature2(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature2')); $('#", classApiMethodMethod, "_enrollmentSignature2').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature2', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature2')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature2')); }); ")
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
	// enrollmentSignature3 //
	//////////////////////////

	/**	 The entity enrollmentSignature3
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentSignature3;
	@JsonIgnore
	public Wrap<String> enrollmentSignature3Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature3").o(enrollmentSignature3);

	/**	<br/> The entity enrollmentSignature3
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature3">Find the entity enrollmentSignature3 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSignature3(Wrap<String> c);

	public String getEnrollmentSignature3() {
		return enrollmentSignature3;
	}
	public void setEnrollmentSignature3(String o) {
		this.enrollmentSignature3 = SchoolEnrollment.staticSetEnrollmentSignature3(siteRequest_, o);
		this.enrollmentSignature3Wrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentSignature3(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentSignature3(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentSignature3(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentSignature3(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentSignature3(siteRequest_, SchoolEnrollment.staticSolrEnrollmentSignature3(siteRequest_, SchoolEnrollment.staticSetEnrollmentSignature3(siteRequest_, o)));
	}

	public String solrEnrollmentSignature3() {
		return SchoolEnrollment.staticSolrEnrollmentSignature3(siteRequest_, enrollmentSignature3);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "signatureDiv1SchoolEnrollment_enrollmentSignature3 signatureInputSchoolEnrollment", pk, "EnrollmentSignature3").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature3").f();
				e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature3");
					a("style", "display: ", StringUtils.isBlank(enrollmentSignature3) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature3");
					a("class", "signatureImgSchoolEnrollment", pk, "EnrollmentSignature3 ");
					a("src", StringUtils.isBlank(enrollmentSignature3) ? "data:image/png;base64," : enrollmentSignature3).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature3) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature3").f();
				e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature3");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature3').show(); ");
						s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature3').hide(); ");
						s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature3')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature3', null); ");
						s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature3')) { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature3').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature3').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Clear");
				g("button");
			g("div");
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentSignature3 ").f().sx(htmEnrollmentSignature3()).g("span");
		}
	}

	public void htmEnrollmentSignature3(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentSignature3").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature3(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature3')); $('#", classApiMethodMethod, "_enrollmentSignature3').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature3', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature3')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature3')); }); ")
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
	// enrollmentSignature4 //
	//////////////////////////

	/**	 The entity enrollmentSignature4
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentSignature4;
	@JsonIgnore
	public Wrap<String> enrollmentSignature4Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature4").o(enrollmentSignature4);

	/**	<br/> The entity enrollmentSignature4
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature4">Find the entity enrollmentSignature4 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSignature4(Wrap<String> c);

	public String getEnrollmentSignature4() {
		return enrollmentSignature4;
	}
	public void setEnrollmentSignature4(String o) {
		this.enrollmentSignature4 = SchoolEnrollment.staticSetEnrollmentSignature4(siteRequest_, o);
		this.enrollmentSignature4Wrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentSignature4(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentSignature4(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentSignature4(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentSignature4(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentSignature4(siteRequest_, SchoolEnrollment.staticSolrEnrollmentSignature4(siteRequest_, SchoolEnrollment.staticSetEnrollmentSignature4(siteRequest_, o)));
	}

	public String solrEnrollmentSignature4() {
		return SchoolEnrollment.staticSolrEnrollmentSignature4(siteRequest_, enrollmentSignature4);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "signatureDiv1SchoolEnrollment_enrollmentSignature4 signatureInputSchoolEnrollment", pk, "EnrollmentSignature4").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature4").f();
				e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature4");
					a("style", "display: ", StringUtils.isBlank(enrollmentSignature4) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature4");
					a("class", "signatureImgSchoolEnrollment", pk, "EnrollmentSignature4 ");
					a("src", StringUtils.isBlank(enrollmentSignature4) ? "data:image/png;base64," : enrollmentSignature4).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature4) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature4").f();
				e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature4");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature4').show(); ");
						s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature4').hide(); ");
						s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature4')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature4', null); ");
						s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature4')) { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature4').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature4').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Clear");
				g("button");
			g("div");
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentSignature4 ").f().sx(htmEnrollmentSignature4()).g("span");
		}
	}

	public void htmEnrollmentSignature4(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentSignature4").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature4(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature4')); $('#", classApiMethodMethod, "_enrollmentSignature4').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature4', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature4')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature4')); }); ")
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
	// enrollmentSignature5 //
	//////////////////////////

	/**	 The entity enrollmentSignature5
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentSignature5;
	@JsonIgnore
	public Wrap<String> enrollmentSignature5Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature5").o(enrollmentSignature5);

	/**	<br/> The entity enrollmentSignature5
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature5">Find the entity enrollmentSignature5 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSignature5(Wrap<String> c);

	public String getEnrollmentSignature5() {
		return enrollmentSignature5;
	}
	public void setEnrollmentSignature5(String o) {
		this.enrollmentSignature5 = SchoolEnrollment.staticSetEnrollmentSignature5(siteRequest_, o);
		this.enrollmentSignature5Wrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentSignature5(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentSignature5(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentSignature5(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentSignature5(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentSignature5(siteRequest_, SchoolEnrollment.staticSolrEnrollmentSignature5(siteRequest_, SchoolEnrollment.staticSetEnrollmentSignature5(siteRequest_, o)));
	}

	public String solrEnrollmentSignature5() {
		return SchoolEnrollment.staticSolrEnrollmentSignature5(siteRequest_, enrollmentSignature5);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "signatureDiv1SchoolEnrollment_enrollmentSignature5 signatureInputSchoolEnrollment", pk, "EnrollmentSignature5").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature5").f();
				e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature5");
					a("style", "display: ", StringUtils.isBlank(enrollmentSignature5) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature5");
					a("class", "signatureImgSchoolEnrollment", pk, "EnrollmentSignature5 ");
					a("src", StringUtils.isBlank(enrollmentSignature5) ? "data:image/png;base64," : enrollmentSignature5).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature5) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature5").f();
				e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature5");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature5').show(); ");
						s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature5').hide(); ");
						s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature5')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature5', null); ");
						s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature5')) { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature5').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature5').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Clear");
				g("button");
			g("div");
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentSignature5 ").f().sx(htmEnrollmentSignature5()).g("span");
		}
	}

	public void htmEnrollmentSignature5(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentSignature5").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature5(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature5')); $('#", classApiMethodMethod, "_enrollmentSignature5').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature5', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature5')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature5')); }); ")
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
	// enrollmentSignature6 //
	//////////////////////////

	/**	 The entity enrollmentSignature6
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentSignature6;
	@JsonIgnore
	public Wrap<String> enrollmentSignature6Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature6").o(enrollmentSignature6);

	/**	<br/> The entity enrollmentSignature6
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature6">Find the entity enrollmentSignature6 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSignature6(Wrap<String> c);

	public String getEnrollmentSignature6() {
		return enrollmentSignature6;
	}
	public void setEnrollmentSignature6(String o) {
		this.enrollmentSignature6 = SchoolEnrollment.staticSetEnrollmentSignature6(siteRequest_, o);
		this.enrollmentSignature6Wrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentSignature6(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentSignature6(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentSignature6(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentSignature6(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentSignature6(siteRequest_, SchoolEnrollment.staticSolrEnrollmentSignature6(siteRequest_, SchoolEnrollment.staticSetEnrollmentSignature6(siteRequest_, o)));
	}

	public String solrEnrollmentSignature6() {
		return SchoolEnrollment.staticSolrEnrollmentSignature6(siteRequest_, enrollmentSignature6);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "signatureDiv1SchoolEnrollment_enrollmentSignature6 signatureInputSchoolEnrollment", pk, "EnrollmentSignature6").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature6").f();
				e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature6");
					a("style", "display: ", StringUtils.isBlank(enrollmentSignature6) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature6");
					a("class", "signatureImgSchoolEnrollment", pk, "EnrollmentSignature6 ");
					a("src", StringUtils.isBlank(enrollmentSignature6) ? "data:image/png;base64," : enrollmentSignature6).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature6) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature6").f();
				e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature6");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature6').show(); ");
						s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature6').hide(); ");
						s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature6')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature6', null); ");
						s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature6')) { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature6').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature6').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Clear");
				g("button");
			g("div");
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentSignature6 ").f().sx(htmEnrollmentSignature6()).g("span");
		}
	}

	public void htmEnrollmentSignature6(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentSignature6").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature6(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature6')); $('#", classApiMethodMethod, "_enrollmentSignature6').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature6', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature6')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature6')); }); ")
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
	// enrollmentSignature7 //
	//////////////////////////

	/**	 The entity enrollmentSignature7
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentSignature7;
	@JsonIgnore
	public Wrap<String> enrollmentSignature7Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature7").o(enrollmentSignature7);

	/**	<br/> The entity enrollmentSignature7
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature7">Find the entity enrollmentSignature7 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSignature7(Wrap<String> c);

	public String getEnrollmentSignature7() {
		return enrollmentSignature7;
	}
	public void setEnrollmentSignature7(String o) {
		this.enrollmentSignature7 = SchoolEnrollment.staticSetEnrollmentSignature7(siteRequest_, o);
		this.enrollmentSignature7Wrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentSignature7(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentSignature7(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentSignature7(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentSignature7(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentSignature7(siteRequest_, SchoolEnrollment.staticSolrEnrollmentSignature7(siteRequest_, SchoolEnrollment.staticSetEnrollmentSignature7(siteRequest_, o)));
	}

	public String solrEnrollmentSignature7() {
		return SchoolEnrollment.staticSolrEnrollmentSignature7(siteRequest_, enrollmentSignature7);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "signatureDiv1SchoolEnrollment_enrollmentSignature7 signatureInputSchoolEnrollment", pk, "EnrollmentSignature7").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature7").f();
				e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature7");
					a("style", "display: ", StringUtils.isBlank(enrollmentSignature7) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature7");
					a("class", "signatureImgSchoolEnrollment", pk, "EnrollmentSignature7 ");
					a("src", StringUtils.isBlank(enrollmentSignature7) ? "data:image/png;base64," : enrollmentSignature7).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature7) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature7").f();
				e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature7");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature7').show(); ");
						s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature7').hide(); ");
						s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature7')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature7', null); ");
						s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature7')) { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature7').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature7').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Clear");
				g("button");
			g("div");
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentSignature7 ").f().sx(htmEnrollmentSignature7()).g("span");
		}
	}

	public void htmEnrollmentSignature7(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentSignature7").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature7(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature7')); $('#", classApiMethodMethod, "_enrollmentSignature7').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature7', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature7')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature7')); }); ")
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
	// enrollmentSignature8 //
	//////////////////////////

	/**	 The entity enrollmentSignature8
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentSignature8;
	@JsonIgnore
	public Wrap<String> enrollmentSignature8Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature8").o(enrollmentSignature8);

	/**	<br/> The entity enrollmentSignature8
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature8">Find the entity enrollmentSignature8 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSignature8(Wrap<String> c);

	public String getEnrollmentSignature8() {
		return enrollmentSignature8;
	}
	public void setEnrollmentSignature8(String o) {
		this.enrollmentSignature8 = SchoolEnrollment.staticSetEnrollmentSignature8(siteRequest_, o);
		this.enrollmentSignature8Wrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentSignature8(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentSignature8(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentSignature8(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentSignature8(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentSignature8(siteRequest_, SchoolEnrollment.staticSolrEnrollmentSignature8(siteRequest_, SchoolEnrollment.staticSetEnrollmentSignature8(siteRequest_, o)));
	}

	public String solrEnrollmentSignature8() {
		return SchoolEnrollment.staticSolrEnrollmentSignature8(siteRequest_, enrollmentSignature8);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "signatureDiv1SchoolEnrollment_enrollmentSignature8 signatureInputSchoolEnrollment", pk, "EnrollmentSignature8").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature8").f();
				e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature8");
					a("style", "display: ", StringUtils.isBlank(enrollmentSignature8) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature8");
					a("class", "signatureImgSchoolEnrollment", pk, "EnrollmentSignature8 ");
					a("src", StringUtils.isBlank(enrollmentSignature8) ? "data:image/png;base64," : enrollmentSignature8).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature8) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature8").f();
				e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature8");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature8').show(); ");
						s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature8').hide(); ");
						s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature8')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature8', null); ");
						s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature8')) { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature8').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature8').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Clear");
				g("button");
			g("div");
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentSignature8 ").f().sx(htmEnrollmentSignature8()).g("span");
		}
	}

	public void htmEnrollmentSignature8(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentSignature8").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature8(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature8')); $('#", classApiMethodMethod, "_enrollmentSignature8').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature8', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature8')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature8')); }); ")
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
	// enrollmentSignature9 //
	//////////////////////////

	/**	 The entity enrollmentSignature9
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentSignature9;
	@JsonIgnore
	public Wrap<String> enrollmentSignature9Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature9").o(enrollmentSignature9);

	/**	<br/> The entity enrollmentSignature9
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature9">Find the entity enrollmentSignature9 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSignature9(Wrap<String> c);

	public String getEnrollmentSignature9() {
		return enrollmentSignature9;
	}
	public void setEnrollmentSignature9(String o) {
		this.enrollmentSignature9 = SchoolEnrollment.staticSetEnrollmentSignature9(siteRequest_, o);
		this.enrollmentSignature9Wrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentSignature9(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentSignature9(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentSignature9(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentSignature9(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentSignature9(siteRequest_, SchoolEnrollment.staticSolrEnrollmentSignature9(siteRequest_, SchoolEnrollment.staticSetEnrollmentSignature9(siteRequest_, o)));
	}

	public String solrEnrollmentSignature9() {
		return SchoolEnrollment.staticSolrEnrollmentSignature9(siteRequest_, enrollmentSignature9);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "signatureDiv1SchoolEnrollment_enrollmentSignature9 signatureInputSchoolEnrollment", pk, "EnrollmentSignature9").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature9").f();
				e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature9");
					a("style", "display: ", StringUtils.isBlank(enrollmentSignature9) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature9");
					a("class", "signatureImgSchoolEnrollment", pk, "EnrollmentSignature9 ");
					a("src", StringUtils.isBlank(enrollmentSignature9) ? "data:image/png;base64," : enrollmentSignature9).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature9) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature9").f();
				e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature9");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature9').show(); ");
						s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature9').hide(); ");
						s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature9')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature9', null); ");
						s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature9')) { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature9').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature9').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Clear");
				g("button");
			g("div");
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentSignature9 ").f().sx(htmEnrollmentSignature9()).g("span");
		}
	}

	public void htmEnrollmentSignature9(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentSignature9").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature9(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature9')); $('#", classApiMethodMethod, "_enrollmentSignature9').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature9', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature9')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature9')); }); ")
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

	///////////////////////////
	// enrollmentSignature10 //
	///////////////////////////

	/**	 The entity enrollmentSignature10
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentSignature10;
	@JsonIgnore
	public Wrap<String> enrollmentSignature10Wrap = new Wrap<String>().p(this).c(String.class).var("enrollmentSignature10").o(enrollmentSignature10);

	/**	<br/> The entity enrollmentSignature10
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSignature10">Find the entity enrollmentSignature10 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSignature10(Wrap<String> c);

	public String getEnrollmentSignature10() {
		return enrollmentSignature10;
	}
	public void setEnrollmentSignature10(String o) {
		this.enrollmentSignature10 = SchoolEnrollment.staticSetEnrollmentSignature10(siteRequest_, o);
		this.enrollmentSignature10Wrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentSignature10(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentSignature10(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentSignature10(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentSignature10(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentSignature10(siteRequest_, SchoolEnrollment.staticSolrEnrollmentSignature10(siteRequest_, SchoolEnrollment.staticSetEnrollmentSignature10(siteRequest_, o)));
	}

	public String solrEnrollmentSignature10() {
		return SchoolEnrollment.staticSolrEnrollmentSignature10(siteRequest_, enrollmentSignature10);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "signatureDiv1SchoolEnrollment_enrollmentSignature10 signatureInputSchoolEnrollment", pk, "EnrollmentSignature10").a("id", "signatureDiv1SchoolEnrollment", pk, "enrollmentSignature10").f();
				e("div").a("id", "signatureInputSchoolEnrollment", pk, "enrollmentSignature10");
					a("style", "display: ", StringUtils.isBlank(enrollmentSignature10) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgSchoolEnrollment", pk, "enrollmentSignature10");
					a("class", "signatureImgSchoolEnrollment", pk, "EnrollmentSignature10 ");
					a("src", StringUtils.isBlank(enrollmentSignature10) ? "data:image/png;base64," : enrollmentSignature10).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(enrollmentSignature10) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2SchoolEnrollment", pk, "enrollmentSignature10").f();
				e("button").a("id", "signatureButtonClearSchoolEnrollment", pk, "enrollmentSignature10");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature10').show(); ");
						s("$('#signatureImgSchoolEnrollment", pk, "enrollmentSignature10').hide(); ");
						s("removeGlow($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature10')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentSignature10', null); ");
						s("if($('#signatureInputSchoolEnrollment", pk, "enrollmentSignature10')) { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature10').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputSchoolEnrollment", pk, "enrollmentSignature10').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Clear");
				g("button");
			g("div");
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentSignature10 ").f().sx(htmEnrollmentSignature10()).g("span");
		}
	}

	public void htmEnrollmentSignature10(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentSignature10").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnrollmentSignature10(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentSignature10')); $('#", classApiMethodMethod, "_enrollmentSignature10').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentSignature10', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentSignature10')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentSignature10')); }); ")
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
	// enrollmentDate1 //
	/////////////////////

	/**	 The entity enrollmentDate1
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enrollmentDate1;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate1Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate1").o(enrollmentDate1);

	/**	<br/> The entity enrollmentDate1
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate1">Find the entity enrollmentDate1 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentDate1(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate1() {
		return enrollmentDate1;
	}

	public void setEnrollmentDate1(LocalDate enrollmentDate1) {
		this.enrollmentDate1 = enrollmentDate1;
		this.enrollmentDate1Wrap.alreadyInitialized = true;
	}
	public void setEnrollmentDate1(Instant o) {
		this.enrollmentDate1 = o == null ? null : LocalDate.from(o);
		this.enrollmentDate1Wrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnrollmentDate1(String o) {
		this.enrollmentDate1 = SchoolEnrollment.staticSetEnrollmentDate1(siteRequest_, o);
		this.enrollmentDate1Wrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetEnrollmentDate1(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnrollmentDate1(Date o) {
		this.enrollmentDate1 = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate1Wrap.alreadyInitialized = true;
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

	public static Date staticSolrEnrollmentDate1(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnrollmentDate1(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnrollmentDate1(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentDate1(siteRequest_, SchoolEnrollment.staticSolrEnrollmentDate1(siteRequest_, SchoolEnrollment.staticSetEnrollmentDate1(siteRequest_, o)));
	}

	public Date solrEnrollmentDate1() {
		return SchoolEnrollment.staticSolrEnrollmentDate1(siteRequest_, enrollmentDate1);
	}

	public String strEnrollmentDate1() {
		return enrollmentDate1 == null ? "" : enrollmentDate1.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonEnrollmentDate1() {
		return enrollmentDate1 == null ? "" : enrollmentDate1.format(DateTimeFormatter.ISO_DATE);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setEnrollmentDate1 classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentDate1 w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_enrollmentDate1")
					.a("value", enrollmentDate1 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(enrollmentDate1));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate1', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate1')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate1')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentDate1 ").f().sx(htmEnrollmentDate1()).g("span");
		}
	}

	public void htmEnrollmentDate1(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentDate1").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate1(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate1')); $('#", classApiMethodMethod, "_enrollmentDate1').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate1', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate1')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate1')); }); ")
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
	// enrollmentDate2 //
	/////////////////////

	/**	 The entity enrollmentDate2
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enrollmentDate2;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate2Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate2").o(enrollmentDate2);

	/**	<br/> The entity enrollmentDate2
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate2">Find the entity enrollmentDate2 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentDate2(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate2() {
		return enrollmentDate2;
	}

	public void setEnrollmentDate2(LocalDate enrollmentDate2) {
		this.enrollmentDate2 = enrollmentDate2;
		this.enrollmentDate2Wrap.alreadyInitialized = true;
	}
	public void setEnrollmentDate2(Instant o) {
		this.enrollmentDate2 = o == null ? null : LocalDate.from(o);
		this.enrollmentDate2Wrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnrollmentDate2(String o) {
		this.enrollmentDate2 = SchoolEnrollment.staticSetEnrollmentDate2(siteRequest_, o);
		this.enrollmentDate2Wrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetEnrollmentDate2(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnrollmentDate2(Date o) {
		this.enrollmentDate2 = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate2Wrap.alreadyInitialized = true;
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

	public static Date staticSolrEnrollmentDate2(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnrollmentDate2(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnrollmentDate2(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentDate2(siteRequest_, SchoolEnrollment.staticSolrEnrollmentDate2(siteRequest_, SchoolEnrollment.staticSetEnrollmentDate2(siteRequest_, o)));
	}

	public Date solrEnrollmentDate2() {
		return SchoolEnrollment.staticSolrEnrollmentDate2(siteRequest_, enrollmentDate2);
	}

	public String strEnrollmentDate2() {
		return enrollmentDate2 == null ? "" : enrollmentDate2.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonEnrollmentDate2() {
		return enrollmentDate2 == null ? "" : enrollmentDate2.format(DateTimeFormatter.ISO_DATE);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setEnrollmentDate2 classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentDate2 w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_enrollmentDate2")
					.a("value", enrollmentDate2 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(enrollmentDate2));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate2', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate2')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate2')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentDate2 ").f().sx(htmEnrollmentDate2()).g("span");
		}
	}

	public void htmEnrollmentDate2(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentDate2").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate2(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate2')); $('#", classApiMethodMethod, "_enrollmentDate2').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate2', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate2')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate2')); }); ")
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
	// enrollmentDate3 //
	/////////////////////

	/**	 The entity enrollmentDate3
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enrollmentDate3;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate3Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate3").o(enrollmentDate3);

	/**	<br/> The entity enrollmentDate3
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate3">Find the entity enrollmentDate3 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentDate3(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate3() {
		return enrollmentDate3;
	}

	public void setEnrollmentDate3(LocalDate enrollmentDate3) {
		this.enrollmentDate3 = enrollmentDate3;
		this.enrollmentDate3Wrap.alreadyInitialized = true;
	}
	public void setEnrollmentDate3(Instant o) {
		this.enrollmentDate3 = o == null ? null : LocalDate.from(o);
		this.enrollmentDate3Wrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnrollmentDate3(String o) {
		this.enrollmentDate3 = SchoolEnrollment.staticSetEnrollmentDate3(siteRequest_, o);
		this.enrollmentDate3Wrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetEnrollmentDate3(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnrollmentDate3(Date o) {
		this.enrollmentDate3 = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate3Wrap.alreadyInitialized = true;
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

	public static Date staticSolrEnrollmentDate3(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnrollmentDate3(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnrollmentDate3(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentDate3(siteRequest_, SchoolEnrollment.staticSolrEnrollmentDate3(siteRequest_, SchoolEnrollment.staticSetEnrollmentDate3(siteRequest_, o)));
	}

	public Date solrEnrollmentDate3() {
		return SchoolEnrollment.staticSolrEnrollmentDate3(siteRequest_, enrollmentDate3);
	}

	public String strEnrollmentDate3() {
		return enrollmentDate3 == null ? "" : enrollmentDate3.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonEnrollmentDate3() {
		return enrollmentDate3 == null ? "" : enrollmentDate3.format(DateTimeFormatter.ISO_DATE);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setEnrollmentDate3 classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentDate3 w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_enrollmentDate3")
					.a("value", enrollmentDate3 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(enrollmentDate3));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate3', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate3')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate3')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentDate3 ").f().sx(htmEnrollmentDate3()).g("span");
		}
	}

	public void htmEnrollmentDate3(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentDate3").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate3(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate3')); $('#", classApiMethodMethod, "_enrollmentDate3').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate3', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate3')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate3')); }); ")
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
	// enrollmentDate4 //
	/////////////////////

	/**	 The entity enrollmentDate4
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enrollmentDate4;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate4Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate4").o(enrollmentDate4);

	/**	<br/> The entity enrollmentDate4
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate4">Find the entity enrollmentDate4 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentDate4(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate4() {
		return enrollmentDate4;
	}

	public void setEnrollmentDate4(LocalDate enrollmentDate4) {
		this.enrollmentDate4 = enrollmentDate4;
		this.enrollmentDate4Wrap.alreadyInitialized = true;
	}
	public void setEnrollmentDate4(Instant o) {
		this.enrollmentDate4 = o == null ? null : LocalDate.from(o);
		this.enrollmentDate4Wrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnrollmentDate4(String o) {
		this.enrollmentDate4 = SchoolEnrollment.staticSetEnrollmentDate4(siteRequest_, o);
		this.enrollmentDate4Wrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetEnrollmentDate4(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnrollmentDate4(Date o) {
		this.enrollmentDate4 = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate4Wrap.alreadyInitialized = true;
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

	public static Date staticSolrEnrollmentDate4(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnrollmentDate4(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnrollmentDate4(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentDate4(siteRequest_, SchoolEnrollment.staticSolrEnrollmentDate4(siteRequest_, SchoolEnrollment.staticSetEnrollmentDate4(siteRequest_, o)));
	}

	public Date solrEnrollmentDate4() {
		return SchoolEnrollment.staticSolrEnrollmentDate4(siteRequest_, enrollmentDate4);
	}

	public String strEnrollmentDate4() {
		return enrollmentDate4 == null ? "" : enrollmentDate4.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonEnrollmentDate4() {
		return enrollmentDate4 == null ? "" : enrollmentDate4.format(DateTimeFormatter.ISO_DATE);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setEnrollmentDate4 classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentDate4 w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_enrollmentDate4")
					.a("value", enrollmentDate4 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(enrollmentDate4));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate4', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate4')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate4')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentDate4 ").f().sx(htmEnrollmentDate4()).g("span");
		}
	}

	public void htmEnrollmentDate4(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentDate4").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate4(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate4')); $('#", classApiMethodMethod, "_enrollmentDate4').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate4', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate4')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate4')); }); ")
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
	// enrollmentDate5 //
	/////////////////////

	/**	 The entity enrollmentDate5
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enrollmentDate5;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate5Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate5").o(enrollmentDate5);

	/**	<br/> The entity enrollmentDate5
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate5">Find the entity enrollmentDate5 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentDate5(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate5() {
		return enrollmentDate5;
	}

	public void setEnrollmentDate5(LocalDate enrollmentDate5) {
		this.enrollmentDate5 = enrollmentDate5;
		this.enrollmentDate5Wrap.alreadyInitialized = true;
	}
	public void setEnrollmentDate5(Instant o) {
		this.enrollmentDate5 = o == null ? null : LocalDate.from(o);
		this.enrollmentDate5Wrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnrollmentDate5(String o) {
		this.enrollmentDate5 = SchoolEnrollment.staticSetEnrollmentDate5(siteRequest_, o);
		this.enrollmentDate5Wrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetEnrollmentDate5(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnrollmentDate5(Date o) {
		this.enrollmentDate5 = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate5Wrap.alreadyInitialized = true;
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

	public static Date staticSolrEnrollmentDate5(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnrollmentDate5(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnrollmentDate5(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentDate5(siteRequest_, SchoolEnrollment.staticSolrEnrollmentDate5(siteRequest_, SchoolEnrollment.staticSetEnrollmentDate5(siteRequest_, o)));
	}

	public Date solrEnrollmentDate5() {
		return SchoolEnrollment.staticSolrEnrollmentDate5(siteRequest_, enrollmentDate5);
	}

	public String strEnrollmentDate5() {
		return enrollmentDate5 == null ? "" : enrollmentDate5.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonEnrollmentDate5() {
		return enrollmentDate5 == null ? "" : enrollmentDate5.format(DateTimeFormatter.ISO_DATE);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setEnrollmentDate5 classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentDate5 w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_enrollmentDate5")
					.a("value", enrollmentDate5 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(enrollmentDate5));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate5', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate5')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate5')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentDate5 ").f().sx(htmEnrollmentDate5()).g("span");
		}
	}

	public void htmEnrollmentDate5(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentDate5").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate5(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate5')); $('#", classApiMethodMethod, "_enrollmentDate5').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate5', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate5')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate5')); }); ")
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
	// enrollmentDate6 //
	/////////////////////

	/**	 The entity enrollmentDate6
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enrollmentDate6;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate6Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate6").o(enrollmentDate6);

	/**	<br/> The entity enrollmentDate6
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate6">Find the entity enrollmentDate6 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentDate6(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate6() {
		return enrollmentDate6;
	}

	public void setEnrollmentDate6(LocalDate enrollmentDate6) {
		this.enrollmentDate6 = enrollmentDate6;
		this.enrollmentDate6Wrap.alreadyInitialized = true;
	}
	public void setEnrollmentDate6(Instant o) {
		this.enrollmentDate6 = o == null ? null : LocalDate.from(o);
		this.enrollmentDate6Wrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnrollmentDate6(String o) {
		this.enrollmentDate6 = SchoolEnrollment.staticSetEnrollmentDate6(siteRequest_, o);
		this.enrollmentDate6Wrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetEnrollmentDate6(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnrollmentDate6(Date o) {
		this.enrollmentDate6 = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate6Wrap.alreadyInitialized = true;
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

	public static Date staticSolrEnrollmentDate6(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnrollmentDate6(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnrollmentDate6(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentDate6(siteRequest_, SchoolEnrollment.staticSolrEnrollmentDate6(siteRequest_, SchoolEnrollment.staticSetEnrollmentDate6(siteRequest_, o)));
	}

	public Date solrEnrollmentDate6() {
		return SchoolEnrollment.staticSolrEnrollmentDate6(siteRequest_, enrollmentDate6);
	}

	public String strEnrollmentDate6() {
		return enrollmentDate6 == null ? "" : enrollmentDate6.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonEnrollmentDate6() {
		return enrollmentDate6 == null ? "" : enrollmentDate6.format(DateTimeFormatter.ISO_DATE);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setEnrollmentDate6 classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentDate6 w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_enrollmentDate6")
					.a("value", enrollmentDate6 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(enrollmentDate6));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate6', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate6')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate6')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentDate6 ").f().sx(htmEnrollmentDate6()).g("span");
		}
	}

	public void htmEnrollmentDate6(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentDate6").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate6(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate6')); $('#", classApiMethodMethod, "_enrollmentDate6').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate6', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate6')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate6')); }); ")
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
	// enrollmentDate7 //
	/////////////////////

	/**	 The entity enrollmentDate7
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enrollmentDate7;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate7Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate7").o(enrollmentDate7);

	/**	<br/> The entity enrollmentDate7
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate7">Find the entity enrollmentDate7 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentDate7(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate7() {
		return enrollmentDate7;
	}

	public void setEnrollmentDate7(LocalDate enrollmentDate7) {
		this.enrollmentDate7 = enrollmentDate7;
		this.enrollmentDate7Wrap.alreadyInitialized = true;
	}
	public void setEnrollmentDate7(Instant o) {
		this.enrollmentDate7 = o == null ? null : LocalDate.from(o);
		this.enrollmentDate7Wrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnrollmentDate7(String o) {
		this.enrollmentDate7 = SchoolEnrollment.staticSetEnrollmentDate7(siteRequest_, o);
		this.enrollmentDate7Wrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetEnrollmentDate7(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnrollmentDate7(Date o) {
		this.enrollmentDate7 = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate7Wrap.alreadyInitialized = true;
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

	public static Date staticSolrEnrollmentDate7(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnrollmentDate7(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnrollmentDate7(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentDate7(siteRequest_, SchoolEnrollment.staticSolrEnrollmentDate7(siteRequest_, SchoolEnrollment.staticSetEnrollmentDate7(siteRequest_, o)));
	}

	public Date solrEnrollmentDate7() {
		return SchoolEnrollment.staticSolrEnrollmentDate7(siteRequest_, enrollmentDate7);
	}

	public String strEnrollmentDate7() {
		return enrollmentDate7 == null ? "" : enrollmentDate7.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonEnrollmentDate7() {
		return enrollmentDate7 == null ? "" : enrollmentDate7.format(DateTimeFormatter.ISO_DATE);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setEnrollmentDate7 classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentDate7 w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_enrollmentDate7")
					.a("value", enrollmentDate7 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(enrollmentDate7));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate7', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate7')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate7')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentDate7 ").f().sx(htmEnrollmentDate7()).g("span");
		}
	}

	public void htmEnrollmentDate7(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentDate7").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate7(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate7')); $('#", classApiMethodMethod, "_enrollmentDate7').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate7', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate7')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate7')); }); ")
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
	// enrollmentDate8 //
	/////////////////////

	/**	 The entity enrollmentDate8
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enrollmentDate8;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate8Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate8").o(enrollmentDate8);

	/**	<br/> The entity enrollmentDate8
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate8">Find the entity enrollmentDate8 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentDate8(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate8() {
		return enrollmentDate8;
	}

	public void setEnrollmentDate8(LocalDate enrollmentDate8) {
		this.enrollmentDate8 = enrollmentDate8;
		this.enrollmentDate8Wrap.alreadyInitialized = true;
	}
	public void setEnrollmentDate8(Instant o) {
		this.enrollmentDate8 = o == null ? null : LocalDate.from(o);
		this.enrollmentDate8Wrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnrollmentDate8(String o) {
		this.enrollmentDate8 = SchoolEnrollment.staticSetEnrollmentDate8(siteRequest_, o);
		this.enrollmentDate8Wrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetEnrollmentDate8(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnrollmentDate8(Date o) {
		this.enrollmentDate8 = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate8Wrap.alreadyInitialized = true;
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

	public static Date staticSolrEnrollmentDate8(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnrollmentDate8(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnrollmentDate8(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentDate8(siteRequest_, SchoolEnrollment.staticSolrEnrollmentDate8(siteRequest_, SchoolEnrollment.staticSetEnrollmentDate8(siteRequest_, o)));
	}

	public Date solrEnrollmentDate8() {
		return SchoolEnrollment.staticSolrEnrollmentDate8(siteRequest_, enrollmentDate8);
	}

	public String strEnrollmentDate8() {
		return enrollmentDate8 == null ? "" : enrollmentDate8.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonEnrollmentDate8() {
		return enrollmentDate8 == null ? "" : enrollmentDate8.format(DateTimeFormatter.ISO_DATE);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setEnrollmentDate8 classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentDate8 w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_enrollmentDate8")
					.a("value", enrollmentDate8 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(enrollmentDate8));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate8', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate8')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate8')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentDate8 ").f().sx(htmEnrollmentDate8()).g("span");
		}
	}

	public void htmEnrollmentDate8(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentDate8").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate8(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate8')); $('#", classApiMethodMethod, "_enrollmentDate8').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate8', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate8')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate8')); }); ")
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
	// enrollmentDate9 //
	/////////////////////

	/**	 The entity enrollmentDate9
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enrollmentDate9;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate9Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate9").o(enrollmentDate9);

	/**	<br/> The entity enrollmentDate9
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate9">Find the entity enrollmentDate9 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentDate9(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate9() {
		return enrollmentDate9;
	}

	public void setEnrollmentDate9(LocalDate enrollmentDate9) {
		this.enrollmentDate9 = enrollmentDate9;
		this.enrollmentDate9Wrap.alreadyInitialized = true;
	}
	public void setEnrollmentDate9(Instant o) {
		this.enrollmentDate9 = o == null ? null : LocalDate.from(o);
		this.enrollmentDate9Wrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnrollmentDate9(String o) {
		this.enrollmentDate9 = SchoolEnrollment.staticSetEnrollmentDate9(siteRequest_, o);
		this.enrollmentDate9Wrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetEnrollmentDate9(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnrollmentDate9(Date o) {
		this.enrollmentDate9 = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate9Wrap.alreadyInitialized = true;
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

	public static Date staticSolrEnrollmentDate9(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnrollmentDate9(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnrollmentDate9(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentDate9(siteRequest_, SchoolEnrollment.staticSolrEnrollmentDate9(siteRequest_, SchoolEnrollment.staticSetEnrollmentDate9(siteRequest_, o)));
	}

	public Date solrEnrollmentDate9() {
		return SchoolEnrollment.staticSolrEnrollmentDate9(siteRequest_, enrollmentDate9);
	}

	public String strEnrollmentDate9() {
		return enrollmentDate9 == null ? "" : enrollmentDate9.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonEnrollmentDate9() {
		return enrollmentDate9 == null ? "" : enrollmentDate9.format(DateTimeFormatter.ISO_DATE);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setEnrollmentDate9 classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentDate9 w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_enrollmentDate9")
					.a("value", enrollmentDate9 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(enrollmentDate9));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate9', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate9')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate9')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentDate9 ").f().sx(htmEnrollmentDate9()).g("span");
		}
	}

	public void htmEnrollmentDate9(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentDate9").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate9(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate9')); $('#", classApiMethodMethod, "_enrollmentDate9').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate9', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate9')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate9')); }); ")
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

	//////////////////////
	// enrollmentDate10 //
	//////////////////////

	/**	 The entity enrollmentDate10
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enrollmentDate10;
	@JsonIgnore
	public Wrap<LocalDate> enrollmentDate10Wrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("enrollmentDate10").o(enrollmentDate10);

	/**	<br/> The entity enrollmentDate10
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDate10">Find the entity enrollmentDate10 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentDate10(Wrap<LocalDate> c);

	public LocalDate getEnrollmentDate10() {
		return enrollmentDate10;
	}

	public void setEnrollmentDate10(LocalDate enrollmentDate10) {
		this.enrollmentDate10 = enrollmentDate10;
		this.enrollmentDate10Wrap.alreadyInitialized = true;
	}
	public void setEnrollmentDate10(Instant o) {
		this.enrollmentDate10 = o == null ? null : LocalDate.from(o);
		this.enrollmentDate10Wrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnrollmentDate10(String o) {
		this.enrollmentDate10 = SchoolEnrollment.staticSetEnrollmentDate10(siteRequest_, o);
		this.enrollmentDate10Wrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetEnrollmentDate10(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnrollmentDate10(Date o) {
		this.enrollmentDate10 = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.enrollmentDate10Wrap.alreadyInitialized = true;
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

	public static Date staticSolrEnrollmentDate10(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnrollmentDate10(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnrollmentDate10(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentDate10(siteRequest_, SchoolEnrollment.staticSolrEnrollmentDate10(siteRequest_, SchoolEnrollment.staticSetEnrollmentDate10(siteRequest_, o)));
	}

	public Date solrEnrollmentDate10() {
		return SchoolEnrollment.staticSolrEnrollmentDate10(siteRequest_, enrollmentDate10);
	}

	public String strEnrollmentDate10() {
		return enrollmentDate10 == null ? "" : enrollmentDate10.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonEnrollmentDate10() {
		return enrollmentDate10 == null ? "" : enrollmentDate10.format(DateTimeFormatter.ISO_DATE);
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
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setEnrollmentDate10 classSchoolEnrollment inputSchoolEnrollment", pk, "EnrollmentDate10 w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_enrollmentDate10")
					.a("value", enrollmentDate10 == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(enrollmentDate10));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnrollmentDate10', s, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate10')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate10')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolEnrollment", pk, "EnrollmentDate10 ").f().sx(htmEnrollmentDate10()).g("span");
		}
	}

	public void htmEnrollmentDate10(String classApiMethodMethod) {
		SchoolEnrollment s = (SchoolEnrollment)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolEnrollmentEnrollmentDate10").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnrollmentDate10(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_enrollmentDate10')); $('#", classApiMethodMethod, "_enrollmentDate10').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolEnrollmentForm :input[name=pk]').val() }], 'setEnrollmentDate10', null, function() { addGlow($('#", classApiMethodMethod, "_enrollmentDate10')); }, function() { addError($('#", classApiMethodMethod, "_enrollmentDate10')); }); ")
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
	// enrollmentYears //
	/////////////////////

	/**	 The entity enrollmentYears
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollmentYears = new ArrayList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentYearsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentYears").o(enrollmentYears);

	/**	<br/> The entity enrollmentYears
	 *  It is constructed before being initialized with the constructor by default List<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentYears">Find the entity enrollmentYears in Solr</a>
	 * <br/>
	 * @param enrollmentYears is the entity already constructed. 
	 **/
	protected abstract void _enrollmentYears(List<SchoolEnrollment> l);

	public List<SchoolEnrollment> getEnrollmentYears() {
		return enrollmentYears;
	}

	public void setEnrollmentYears(List<SchoolEnrollment> enrollmentYears) {
		this.enrollmentYears = enrollmentYears;
		this.enrollmentYearsWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentYears(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolEnrollment addEnrollmentYears(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollmentYears(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addEnrollmentYears(SchoolEnrollment o) {
		if(o != null && !enrollmentYears.contains(o))
			this.enrollmentYears.add(o);
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentYearsInit() {
		if(!enrollmentYearsWrap.alreadyInitialized) {
			_enrollmentYears(enrollmentYears);
		}
		enrollmentYearsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	/////////////////////////
	// enrollmentApprovals //
	/////////////////////////

	/**	 The entity enrollmentApprovals
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollmentApprovals = new ArrayList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentApprovalsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentApprovals").o(enrollmentApprovals);

	/**	<br/> The entity enrollmentApprovals
	 *  It is constructed before being initialized with the constructor by default List<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentApprovals">Find the entity enrollmentApprovals in Solr</a>
	 * <br/>
	 * @param enrollmentApprovals is the entity already constructed. 
	 **/
	protected abstract void _enrollmentApprovals(List<SchoolEnrollment> l);

	public List<SchoolEnrollment> getEnrollmentApprovals() {
		return enrollmentApprovals;
	}

	public void setEnrollmentApprovals(List<SchoolEnrollment> enrollmentApprovals) {
		this.enrollmentApprovals = enrollmentApprovals;
		this.enrollmentApprovalsWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentApprovals(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolEnrollment addEnrollmentApprovals(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollmentApprovals(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addEnrollmentApprovals(SchoolEnrollment o) {
		if(o != null && !enrollmentApprovals.contains(o))
			this.enrollmentApprovals.add(o);
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentApprovalsInit() {
		if(!enrollmentApprovalsWrap.alreadyInitialized) {
			_enrollmentApprovals(enrollmentApprovals);
		}
		enrollmentApprovalsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	//////////////////////
	// enrollmentGroups //
	//////////////////////

	/**	 The entity enrollmentGroups
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollmentGroups = new ArrayList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentGroupsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentGroups").o(enrollmentGroups);

	/**	<br/> The entity enrollmentGroups
	 *  It is constructed before being initialized with the constructor by default List<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentGroups">Find the entity enrollmentGroups in Solr</a>
	 * <br/>
	 * @param enrollmentGroups is the entity already constructed. 
	 **/
	protected abstract void _enrollmentGroups(List<SchoolEnrollment> l);

	public List<SchoolEnrollment> getEnrollmentGroups() {
		return enrollmentGroups;
	}

	public void setEnrollmentGroups(List<SchoolEnrollment> enrollmentGroups) {
		this.enrollmentGroups = enrollmentGroups;
		this.enrollmentGroupsWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentGroups(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolEnrollment addEnrollmentGroups(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollmentGroups(o);
		}
		return (SchoolEnrollment)this;
	}
	public SchoolEnrollment addEnrollmentGroups(SchoolEnrollment o) {
		if(o != null && !enrollmentGroups.contains(o))
			this.enrollmentGroups.add(o);
		return (SchoolEnrollment)this;
	}
	protected SchoolEnrollment enrollmentGroupsInit() {
		if(!enrollmentGroupsWrap.alreadyInitialized) {
			_enrollmentGroups(enrollmentGroups);
		}
		enrollmentGroupsWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	///////////////////////////
	// enrollmentEnrollments //
	///////////////////////////

	/**	 The entity enrollmentEnrollments
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollmentEnrollments = new ArrayList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentEnrollmentsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentEnrollments").o(enrollmentEnrollments);

	/**	<br/> The entity enrollmentEnrollments
	 *  It is constructed before being initialized with the constructor by default List<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentEnrollments">Find the entity enrollmentEnrollments in Solr</a>
	 * <br/>
	 * @param enrollmentEnrollments is the entity already constructed. 
	 **/
	protected abstract void _enrollmentEnrollments(List<SchoolEnrollment> l);

	public List<SchoolEnrollment> getEnrollmentEnrollments() {
		return enrollmentEnrollments;
	}

	public void setEnrollmentEnrollments(List<SchoolEnrollment> enrollmentEnrollments) {
		this.enrollmentEnrollments = enrollmentEnrollments;
		this.enrollmentEnrollmentsWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentEnrollments(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	////////////////////////////////
	// childImmunizationsReceived //
	////////////////////////////////

	/**	 The entity childImmunizationsReceived
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childImmunizationsReceived;
	@JsonIgnore
	public Wrap<String> childImmunizationsReceivedWrap = new Wrap<String>().p(this).c(String.class).var("childImmunizationsReceived").o(childImmunizationsReceived);

	/**	<br/> The entity childImmunizationsReceived
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childImmunizationsReceived">Find the entity childImmunizationsReceived in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childImmunizationsReceived(Wrap<String> c);

	public String getChildImmunizationsReceived() {
		return childImmunizationsReceived;
	}
	public void setChildImmunizationsReceived(String o) {
		this.childImmunizationsReceived = SchoolEnrollment.staticSetChildImmunizationsReceived(siteRequest_, o);
		this.childImmunizationsReceivedWrap.alreadyInitialized = true;
	}
	public static String staticSetChildImmunizationsReceived(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment childImmunizationsReceivedInit() {
		if(!childImmunizationsReceivedWrap.alreadyInitialized) {
			_childImmunizationsReceived(childImmunizationsReceivedWrap);
			if(childImmunizationsReceived == null)
				setChildImmunizationsReceived(childImmunizationsReceivedWrap.o);
		}
		childImmunizationsReceivedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrChildImmunizationsReceived(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildImmunizationsReceived(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildImmunizationsReceived(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildImmunizationsReceived(siteRequest_, SchoolEnrollment.staticSolrChildImmunizationsReceived(siteRequest_, SchoolEnrollment.staticSetChildImmunizationsReceived(siteRequest_, o)));
	}

	public String solrChildImmunizationsReceived() {
		return SchoolEnrollment.staticSolrChildImmunizationsReceived(siteRequest_, childImmunizationsReceived);
	}

	public String strChildImmunizationsReceived() {
		return childImmunizationsReceived == null ? "" : childImmunizationsReceived;
	}

	public String jsonChildImmunizationsReceived() {
		return childImmunizationsReceived == null ? "" : childImmunizationsReceived;
	}

	public String nomAffichageChildImmunizationsReceived() {
		return null;
	}

	public String htmTooltipChildImmunizationsReceived() {
		return null;
	}

	public String htmChildImmunizationsReceived() {
		return childImmunizationsReceived == null ? "" : StringEscapeUtils.escapeHtml4(strChildImmunizationsReceived());
	}

	/////////////////////////
	// childPhotosApproved //
	/////////////////////////

	/**	 The entity childPhotosApproved
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childPhotosApproved;
	@JsonIgnore
	public Wrap<String> childPhotosApprovedWrap = new Wrap<String>().p(this).c(String.class).var("childPhotosApproved").o(childPhotosApproved);

	/**	<br/> The entity childPhotosApproved
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childPhotosApproved">Find the entity childPhotosApproved in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childPhotosApproved(Wrap<String> c);

	public String getChildPhotosApproved() {
		return childPhotosApproved;
	}
	public void setChildPhotosApproved(String o) {
		this.childPhotosApproved = SchoolEnrollment.staticSetChildPhotosApproved(siteRequest_, o);
		this.childPhotosApprovedWrap.alreadyInitialized = true;
	}
	public static String staticSetChildPhotosApproved(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolEnrollment childPhotosApprovedInit() {
		if(!childPhotosApprovedWrap.alreadyInitialized) {
			_childPhotosApproved(childPhotosApprovedWrap);
			if(childPhotosApproved == null)
				setChildPhotosApproved(childPhotosApprovedWrap.o);
		}
		childPhotosApprovedWrap.alreadyInitialized(true);
		return (SchoolEnrollment)this;
	}

	public static String staticSolrChildPhotosApproved(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildPhotosApproved(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildPhotosApproved(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrChildPhotosApproved(siteRequest_, SchoolEnrollment.staticSolrChildPhotosApproved(siteRequest_, SchoolEnrollment.staticSetChildPhotosApproved(siteRequest_, o)));
	}

	public String solrChildPhotosApproved() {
		return SchoolEnrollment.staticSolrChildPhotosApproved(siteRequest_, childPhotosApproved);
	}

	public String strChildPhotosApproved() {
		return childPhotosApproved == null ? "" : childPhotosApproved;
	}

	public String jsonChildPhotosApproved() {
		return childPhotosApproved == null ? "" : childPhotosApproved;
	}

	public String nomAffichageChildPhotosApproved() {
		return null;
	}

	public String htmTooltipChildPhotosApproved() {
		return null;
	}

	public String htmChildPhotosApproved() {
		return childPhotosApproved == null ? "" : StringEscapeUtils.escapeHtml4(strChildPhotosApproved());
	}

	//////////////////////
	// enrollmentNumber //
	//////////////////////

	/**	 The entity enrollmentNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer enrollmentNumber;
	@JsonIgnore
	public Wrap<Integer> enrollmentNumberWrap = new Wrap<Integer>().p(this).c(Integer.class).var("enrollmentNumber").o(enrollmentNumber);

	/**	<br/> The entity enrollmentNumber
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentNumber">Find the entity enrollmentNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentNumber(Wrap<Integer> c);

	public Integer getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(Integer enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
		this.enrollmentNumberWrap.alreadyInitialized = true;
	}
	public void setEnrollmentNumber(String o) {
		this.enrollmentNumber = SchoolEnrollment.staticSetEnrollmentNumber(siteRequest_, o);
		this.enrollmentNumberWrap.alreadyInitialized = true;
	}
	public static Integer staticSetEnrollmentNumber(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEnrollmentNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrEnrollmentNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentNumber(siteRequest_, SchoolEnrollment.staticSolrEnrollmentNumber(siteRequest_, SchoolEnrollment.staticSetEnrollmentNumber(siteRequest_, o)));
	}

	public Integer solrEnrollmentNumber() {
		return SchoolEnrollment.staticSolrEnrollmentNumber(siteRequest_, enrollmentNumber);
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

	/**	 The entity enrollmentCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enrollmentCompleteName;
	@JsonIgnore
	public Wrap<String> enrollmentCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentCompleteName").o(enrollmentCompleteName);

	/**	<br/> The entity enrollmentCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.SchoolEnrollment&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentCompleteName">Find the entity enrollmentCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentCompleteName(Wrap<String> c);

	public String getEnrollmentCompleteName() {
		return enrollmentCompleteName;
	}
	public void setEnrollmentCompleteName(String o) {
		this.enrollmentCompleteName = SchoolEnrollment.staticSetEnrollmentCompleteName(siteRequest_, o);
		this.enrollmentCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetEnrollmentCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrEnrollmentCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEnrollmentCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolEnrollment.staticSolrStrEnrollmentCompleteName(siteRequest_, SchoolEnrollment.staticSolrEnrollmentCompleteName(siteRequest_, SchoolEnrollment.staticSetEnrollmentCompleteName(siteRequest_, o)));
	}

	public String solrEnrollmentCompleteName() {
		return SchoolEnrollment.staticSolrEnrollmentCompleteName(siteRequest_, enrollmentCompleteName);
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
		sessionKeyInit();
		ageKeyInit();
		blockKeyInit();
		childKeyInit();
		momKeysInit();
		dadKeysInit();
		guardianKeysInit();
		paymentKeysInit();
		enrollmentFormKeyInit();
		userKeysInit();
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
		feeSearchInit();
		paymentSearchInit();
		childFirstNameInit();
		childFirstNamePreferredInit();
		childFamilyNameInit();
		momFirstNameInit();
		momFirstNamePreferredInit();
		momCompleteNamePreferredInit();
		dadFirstNameInit();
		dadFirstNamePreferredInit();
		dadCompleteNamePreferredInit();
		childCompleteNameInit();
		childCompleteNamePreferredInit();
		childBirthDateInit();
		childBirthDateYearInit();
		childBirthDateMonthOfYearInit();
		childBirthDateDayOfWeekInit();
		childBirthMonthInit();
		childBirthDayInit();
		schoolNameInit();
		schoolCompleteNameInit();
		schoolLocationInit();
		schoolAddressInit();
		schoolPhoneNumberInit();
		schoolFormInit();
		schoolNumberInit();
		schoolAdministratorNameInit();
		yearStartInit();
		yearEndInit();
		seasonStartDateInit();
		yearEnrollmentFeeInit();
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
		photoInit();
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
		enrollmentGroupColorInit();
		enrollmentPaymentEachMonthInit();
		enrollmentPaymentCompleteInit();
		customerProfileIdInit();
		enrollmentChargeDateInit();
		paymentFacetsInit();
		paymentLastDateInit();
		paymentLastStrInit();
		paymentAmountInit();
		chargeAmountInit();
		chargeAmountFutureInit();
		chargeAmountDueInit();
		chargeAmountNotPassedInit();
		chargesNowInit();
		paymentsCurrentInit();
		paymentsLateInit();
		paymentsLateAmountInit();
		paymentsAheadInit();
		paymentsPastDueInit();
		paymentsPastDueAmountInit();
		chargesCreatedInit();
		createdYearInit();
		createdDayOfWeekInit();
		createdMonthOfYearInit();
		createdHourOfDayInit();
		enrollmentDaysOfWeekInit();
		enrollmentParentNamesInit();
		enrollmentEmailsInit();
		enrollmentEmailInit();
		enrollmentParentEmailsInit();
		enrollmentPhoneNumbersInit();
		enrollmentPhoneNumberInit();
		enrollmentParentNameInit();
		enrollmentParentNameLinesInit();
		enrollmentParentEmailLinesInit();
		enrollmentParentDetailLinesInit();
		enrollmentPickupDetailLinesInit();
		enrollmentEmergencyContactDetailLinesInit();
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
		enrollmentYearsInit();
		enrollmentApprovalsInit();
		enrollmentGroupsInit();
		enrollmentEnrollmentsInit();
		childImmunizationsReceivedInit();
		childPhotosApprovedInit();
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
		if(feeSearch != null)
			feeSearch.setSiteRequest_(siteRequest_);
		if(paymentSearch != null)
			paymentSearch.setSiteRequest_(siteRequest_);
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
			case "userKeys":
				return oSchoolEnrollment.userKeys;
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
			case "feeSearch":
				return oSchoolEnrollment.feeSearch;
			case "paymentSearch":
				return oSchoolEnrollment.paymentSearch;
			case "childFirstName":
				return oSchoolEnrollment.childFirstName;
			case "childFirstNamePreferred":
				return oSchoolEnrollment.childFirstNamePreferred;
			case "childFamilyName":
				return oSchoolEnrollment.childFamilyName;
			case "momFirstName":
				return oSchoolEnrollment.momFirstName;
			case "momFirstNamePreferred":
				return oSchoolEnrollment.momFirstNamePreferred;
			case "momCompleteNamePreferred":
				return oSchoolEnrollment.momCompleteNamePreferred;
			case "dadFirstName":
				return oSchoolEnrollment.dadFirstName;
			case "dadFirstNamePreferred":
				return oSchoolEnrollment.dadFirstNamePreferred;
			case "dadCompleteNamePreferred":
				return oSchoolEnrollment.dadCompleteNamePreferred;
			case "childCompleteName":
				return oSchoolEnrollment.childCompleteName;
			case "childCompleteNamePreferred":
				return oSchoolEnrollment.childCompleteNamePreferred;
			case "childBirthDate":
				return oSchoolEnrollment.childBirthDate;
			case "childBirthDateYear":
				return oSchoolEnrollment.childBirthDateYear;
			case "childBirthDateMonthOfYear":
				return oSchoolEnrollment.childBirthDateMonthOfYear;
			case "childBirthDateDayOfWeek":
				return oSchoolEnrollment.childBirthDateDayOfWeek;
			case "childBirthMonth":
				return oSchoolEnrollment.childBirthMonth;
			case "childBirthDay":
				return oSchoolEnrollment.childBirthDay;
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
			case "schoolForm":
				return oSchoolEnrollment.schoolForm;
			case "schoolNumber":
				return oSchoolEnrollment.schoolNumber;
			case "schoolAdministratorName":
				return oSchoolEnrollment.schoolAdministratorName;
			case "yearStart":
				return oSchoolEnrollment.yearStart;
			case "yearEnd":
				return oSchoolEnrollment.yearEnd;
			case "seasonStartDate":
				return oSchoolEnrollment.seasonStartDate;
			case "yearEnrollmentFee":
				return oSchoolEnrollment.yearEnrollmentFee;
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
			case "photo":
				return oSchoolEnrollment.photo;
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
			case "enrollmentGroupColor":
				return oSchoolEnrollment.enrollmentGroupColor;
			case "enrollmentPaymentEachMonth":
				return oSchoolEnrollment.enrollmentPaymentEachMonth;
			case "enrollmentPaymentComplete":
				return oSchoolEnrollment.enrollmentPaymentComplete;
			case "customerProfileId":
				return oSchoolEnrollment.customerProfileId;
			case "enrollmentChargeDate":
				return oSchoolEnrollment.enrollmentChargeDate;
			case "paymentFacets":
				return oSchoolEnrollment.paymentFacets;
			case "paymentLastDate":
				return oSchoolEnrollment.paymentLastDate;
			case "paymentLastStr":
				return oSchoolEnrollment.paymentLastStr;
			case "paymentAmount":
				return oSchoolEnrollment.paymentAmount;
			case "chargeAmount":
				return oSchoolEnrollment.chargeAmount;
			case "chargeAmountFuture":
				return oSchoolEnrollment.chargeAmountFuture;
			case "chargeAmountDue":
				return oSchoolEnrollment.chargeAmountDue;
			case "chargeAmountNotPassed":
				return oSchoolEnrollment.chargeAmountNotPassed;
			case "chargesNow":
				return oSchoolEnrollment.chargesNow;
			case "paymentsCurrent":
				return oSchoolEnrollment.paymentsCurrent;
			case "paymentsLate":
				return oSchoolEnrollment.paymentsLate;
			case "paymentsLateAmount":
				return oSchoolEnrollment.paymentsLateAmount;
			case "paymentsAhead":
				return oSchoolEnrollment.paymentsAhead;
			case "paymentsPastDue":
				return oSchoolEnrollment.paymentsPastDue;
			case "paymentsPastDueAmount":
				return oSchoolEnrollment.paymentsPastDueAmount;
			case "chargesCreated":
				return oSchoolEnrollment.chargesCreated;
			case "createdYear":
				return oSchoolEnrollment.createdYear;
			case "createdDayOfWeek":
				return oSchoolEnrollment.createdDayOfWeek;
			case "createdMonthOfYear":
				return oSchoolEnrollment.createdMonthOfYear;
			case "createdHourOfDay":
				return oSchoolEnrollment.createdHourOfDay;
			case "enrollmentDaysOfWeek":
				return oSchoolEnrollment.enrollmentDaysOfWeek;
			case "enrollmentParentNames":
				return oSchoolEnrollment.enrollmentParentNames;
			case "enrollmentEmails":
				return oSchoolEnrollment.enrollmentEmails;
			case "enrollmentEmail":
				return oSchoolEnrollment.enrollmentEmail;
			case "enrollmentParentEmails":
				return oSchoolEnrollment.enrollmentParentEmails;
			case "enrollmentPhoneNumbers":
				return oSchoolEnrollment.enrollmentPhoneNumbers;
			case "enrollmentPhoneNumber":
				return oSchoolEnrollment.enrollmentPhoneNumber;
			case "enrollmentParentName":
				return oSchoolEnrollment.enrollmentParentName;
			case "enrollmentParentNameLines":
				return oSchoolEnrollment.enrollmentParentNameLines;
			case "enrollmentParentEmailLines":
				return oSchoolEnrollment.enrollmentParentEmailLines;
			case "enrollmentParentDetailLines":
				return oSchoolEnrollment.enrollmentParentDetailLines;
			case "enrollmentPickupDetailLines":
				return oSchoolEnrollment.enrollmentPickupDetailLines;
			case "enrollmentEmergencyContactDetailLines":
				return oSchoolEnrollment.enrollmentEmergencyContactDetailLines;
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
			case "enrollmentYears":
				return oSchoolEnrollment.enrollmentYears;
			case "enrollmentApprovals":
				return oSchoolEnrollment.enrollmentApprovals;
			case "enrollmentGroups":
				return oSchoolEnrollment.enrollmentGroups;
			case "enrollmentEnrollments":
				return oSchoolEnrollment.enrollmentEnrollments;
			case "childImmunizationsReceived":
				return oSchoolEnrollment.childImmunizationsReceived;
			case "childPhotosApproved":
				return oSchoolEnrollment.childPhotosApproved;
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
				if(oSchoolEnrollment.getYearKey() == null)
					oSchoolEnrollment.setYearKey((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "blockKeys":
				oSchoolEnrollment.addBlockKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "childKey":
				if(oSchoolEnrollment.getChildKey() == null)
					oSchoolEnrollment.setChildKey((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "momKeys":
				oSchoolEnrollment.addMomKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "dadKeys":
				oSchoolEnrollment.addDadKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "guardianKeys":
				oSchoolEnrollment.addGuardianKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "paymentKeys":
				oSchoolEnrollment.addPaymentKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "userKeys":
				oSchoolEnrollment.addUserKeys((Long)val);
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
		return staticSetSchoolEnrollment(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSchoolEnrollment(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "enrollmentKey":
			return SchoolEnrollment.staticSetEnrollmentKey(siteRequest_, o);
		case "yearKey":
			return SchoolEnrollment.staticSetYearKey(siteRequest_, o);
		case "blockKeys":
			return SchoolEnrollment.staticSetBlockKeys(siteRequest_, o);
		case "schoolKey":
			return SchoolEnrollment.staticSetSchoolKey(siteRequest_, o);
		case "sessionKey":
			return SchoolEnrollment.staticSetSessionKey(siteRequest_, o);
		case "ageKey":
			return SchoolEnrollment.staticSetAgeKey(siteRequest_, o);
		case "blockKey":
			return SchoolEnrollment.staticSetBlockKey(siteRequest_, o);
		case "childKey":
			return SchoolEnrollment.staticSetChildKey(siteRequest_, o);
		case "momKeys":
			return SchoolEnrollment.staticSetMomKeys(siteRequest_, o);
		case "dadKeys":
			return SchoolEnrollment.staticSetDadKeys(siteRequest_, o);
		case "guardianKeys":
			return SchoolEnrollment.staticSetGuardianKeys(siteRequest_, o);
		case "paymentKeys":
			return SchoolEnrollment.staticSetPaymentKeys(siteRequest_, o);
		case "enrollmentFormKey":
			return SchoolEnrollment.staticSetEnrollmentFormKey(siteRequest_, o);
		case "userKeys":
			return SchoolEnrollment.staticSetUserKeys(siteRequest_, o);
		case "educationSort":
			return SchoolEnrollment.staticSetEducationSort(siteRequest_, o);
		case "schoolSort":
			return SchoolEnrollment.staticSetSchoolSort(siteRequest_, o);
		case "yearSort":
			return SchoolEnrollment.staticSetYearSort(siteRequest_, o);
		case "seasonSort":
			return SchoolEnrollment.staticSetSeasonSort(siteRequest_, o);
		case "sessionSort":
			return SchoolEnrollment.staticSetSessionSort(siteRequest_, o);
		case "ageSort":
			return SchoolEnrollment.staticSetAgeSort(siteRequest_, o);
		case "childFirstName":
			return SchoolEnrollment.staticSetChildFirstName(siteRequest_, o);
		case "childFirstNamePreferred":
			return SchoolEnrollment.staticSetChildFirstNamePreferred(siteRequest_, o);
		case "childFamilyName":
			return SchoolEnrollment.staticSetChildFamilyName(siteRequest_, o);
		case "momFirstName":
			return SchoolEnrollment.staticSetMomFirstName(siteRequest_, o);
		case "momFirstNamePreferred":
			return SchoolEnrollment.staticSetMomFirstNamePreferred(siteRequest_, o);
		case "momCompleteNamePreferred":
			return SchoolEnrollment.staticSetMomCompleteNamePreferred(siteRequest_, o);
		case "dadFirstName":
			return SchoolEnrollment.staticSetDadFirstName(siteRequest_, o);
		case "dadFirstNamePreferred":
			return SchoolEnrollment.staticSetDadFirstNamePreferred(siteRequest_, o);
		case "dadCompleteNamePreferred":
			return SchoolEnrollment.staticSetDadCompleteNamePreferred(siteRequest_, o);
		case "childCompleteName":
			return SchoolEnrollment.staticSetChildCompleteName(siteRequest_, o);
		case "childCompleteNamePreferred":
			return SchoolEnrollment.staticSetChildCompleteNamePreferred(siteRequest_, o);
		case "childBirthDate":
			return SchoolEnrollment.staticSetChildBirthDate(siteRequest_, o);
		case "childBirthDateYear":
			return SchoolEnrollment.staticSetChildBirthDateYear(siteRequest_, o);
		case "childBirthDateMonthOfYear":
			return SchoolEnrollment.staticSetChildBirthDateMonthOfYear(siteRequest_, o);
		case "childBirthDateDayOfWeek":
			return SchoolEnrollment.staticSetChildBirthDateDayOfWeek(siteRequest_, o);
		case "childBirthMonth":
			return SchoolEnrollment.staticSetChildBirthMonth(siteRequest_, o);
		case "childBirthDay":
			return SchoolEnrollment.staticSetChildBirthDay(siteRequest_, o);
		case "schoolName":
			return SchoolEnrollment.staticSetSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return SchoolEnrollment.staticSetSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return SchoolEnrollment.staticSetSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return SchoolEnrollment.staticSetSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return SchoolEnrollment.staticSetSchoolPhoneNumber(siteRequest_, o);
		case "schoolForm":
			return SchoolEnrollment.staticSetSchoolForm(siteRequest_, o);
		case "schoolNumber":
			return SchoolEnrollment.staticSetSchoolNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return SchoolEnrollment.staticSetSchoolAdministratorName(siteRequest_, o);
		case "yearStart":
			return SchoolEnrollment.staticSetYearStart(siteRequest_, o);
		case "yearEnd":
			return SchoolEnrollment.staticSetYearEnd(siteRequest_, o);
		case "seasonStartDate":
			return SchoolEnrollment.staticSetSeasonStartDate(siteRequest_, o);
		case "yearEnrollmentFee":
			return SchoolEnrollment.staticSetYearEnrollmentFee(siteRequest_, o);
		case "sessionStartDate":
			return SchoolEnrollment.staticSetSessionStartDate(siteRequest_, o);
		case "sessionEndDate":
			return SchoolEnrollment.staticSetSessionEndDate(siteRequest_, o);
		case "ageCompleteName":
			return SchoolEnrollment.staticSetAgeCompleteName(siteRequest_, o);
		case "ageStart":
			return SchoolEnrollment.staticSetAgeStart(siteRequest_, o);
		case "ageEnd":
			return SchoolEnrollment.staticSetAgeEnd(siteRequest_, o);
		case "blockStartTime":
			return SchoolEnrollment.staticSetBlockStartTime(siteRequest_, o);
		case "blockEndTime":
			return SchoolEnrollment.staticSetBlockEndTime(siteRequest_, o);
		case "blockPricePerMonth":
			return SchoolEnrollment.staticSetBlockPricePerMonth(siteRequest_, o);
		case "blockSunday":
			return SchoolEnrollment.staticSetBlockSunday(siteRequest_, o);
		case "blockMonday":
			return SchoolEnrollment.staticSetBlockMonday(siteRequest_, o);
		case "blockTuesday":
			return SchoolEnrollment.staticSetBlockTuesday(siteRequest_, o);
		case "blockWednesday":
			return SchoolEnrollment.staticSetBlockWednesday(siteRequest_, o);
		case "blockThursday":
			return SchoolEnrollment.staticSetBlockThursday(siteRequest_, o);
		case "blockFriday":
			return SchoolEnrollment.staticSetBlockFriday(siteRequest_, o);
		case "blockSaturday":
			return SchoolEnrollment.staticSetBlockSaturday(siteRequest_, o);
		case "blockTotalPrice":
			return SchoolEnrollment.staticSetBlockTotalPrice(siteRequest_, o);
		case "blockAdminName":
			return SchoolEnrollment.staticSetBlockAdminName(siteRequest_, o);
		case "blockShortName":
			return SchoolEnrollment.staticSetBlockShortName(siteRequest_, o);
		case "blockCompleteName":
			return SchoolEnrollment.staticSetBlockCompleteName(siteRequest_, o);
		case "enrollmentApproved":
			return SchoolEnrollment.staticSetEnrollmentApproved(siteRequest_, o);
		case "enrollmentImmunizations":
			return SchoolEnrollment.staticSetEnrollmentImmunizations(siteRequest_, o);
		case "photo":
			return SchoolEnrollment.staticSetPhoto(siteRequest_, o);
		case "familyMarried":
			return SchoolEnrollment.staticSetFamilyMarried(siteRequest_, o);
		case "familySeparated":
			return SchoolEnrollment.staticSetFamilySeparated(siteRequest_, o);
		case "familyDivorced":
			return SchoolEnrollment.staticSetFamilyDivorced(siteRequest_, o);
		case "familyAddress":
			return SchoolEnrollment.staticSetFamilyAddress(siteRequest_, o);
		case "familyHowDoYouKnowTheSchool":
			return SchoolEnrollment.staticSetFamilyHowDoYouKnowTheSchool(siteRequest_, o);
		case "enrollmentSpecialConsiderations":
			return SchoolEnrollment.staticSetEnrollmentSpecialConsiderations(siteRequest_, o);
		case "childMedicalConditions":
			return SchoolEnrollment.staticSetChildMedicalConditions(siteRequest_, o);
		case "childPreviousSchoolsAttended":
			return SchoolEnrollment.staticSetChildPreviousSchoolsAttended(siteRequest_, o);
		case "childDescription":
			return SchoolEnrollment.staticSetChildDescription(siteRequest_, o);
		case "childObjectives":
			return SchoolEnrollment.staticSetChildObjectives(siteRequest_, o);
		case "childPottyTrained":
			return SchoolEnrollment.staticSetChildPottyTrained(siteRequest_, o);
		case "enrollmentGroupName":
			return SchoolEnrollment.staticSetEnrollmentGroupName(siteRequest_, o);
		case "enrollmentGroupColor":
			return SchoolEnrollment.staticSetEnrollmentGroupColor(siteRequest_, o);
		case "enrollmentPaymentEachMonth":
			return SchoolEnrollment.staticSetEnrollmentPaymentEachMonth(siteRequest_, o);
		case "enrollmentPaymentComplete":
			return SchoolEnrollment.staticSetEnrollmentPaymentComplete(siteRequest_, o);
		case "customerProfileId":
			return SchoolEnrollment.staticSetCustomerProfileId(siteRequest_, o);
		case "enrollmentChargeDate":
			return SchoolEnrollment.staticSetEnrollmentChargeDate(siteRequest_, o);
		case "paymentLastDate":
			return SchoolEnrollment.staticSetPaymentLastDate(siteRequest_, o);
		case "paymentLastStr":
			return SchoolEnrollment.staticSetPaymentLastStr(siteRequest_, o);
		case "paymentAmount":
			return SchoolEnrollment.staticSetPaymentAmount(siteRequest_, o);
		case "chargeAmount":
			return SchoolEnrollment.staticSetChargeAmount(siteRequest_, o);
		case "chargeAmountFuture":
			return SchoolEnrollment.staticSetChargeAmountFuture(siteRequest_, o);
		case "chargeAmountDue":
			return SchoolEnrollment.staticSetChargeAmountDue(siteRequest_, o);
		case "chargeAmountNotPassed":
			return SchoolEnrollment.staticSetChargeAmountNotPassed(siteRequest_, o);
		case "chargesNow":
			return SchoolEnrollment.staticSetChargesNow(siteRequest_, o);
		case "paymentsCurrent":
			return SchoolEnrollment.staticSetPaymentsCurrent(siteRequest_, o);
		case "paymentsLate":
			return SchoolEnrollment.staticSetPaymentsLate(siteRequest_, o);
		case "paymentsLateAmount":
			return SchoolEnrollment.staticSetPaymentsLateAmount(siteRequest_, o);
		case "paymentsAhead":
			return SchoolEnrollment.staticSetPaymentsAhead(siteRequest_, o);
		case "paymentsPastDue":
			return SchoolEnrollment.staticSetPaymentsPastDue(siteRequest_, o);
		case "paymentsPastDueAmount":
			return SchoolEnrollment.staticSetPaymentsPastDueAmount(siteRequest_, o);
		case "chargesCreated":
			return SchoolEnrollment.staticSetChargesCreated(siteRequest_, o);
		case "createdYear":
			return SchoolEnrollment.staticSetCreatedYear(siteRequest_, o);
		case "createdDayOfWeek":
			return SchoolEnrollment.staticSetCreatedDayOfWeek(siteRequest_, o);
		case "createdMonthOfYear":
			return SchoolEnrollment.staticSetCreatedMonthOfYear(siteRequest_, o);
		case "createdHourOfDay":
			return SchoolEnrollment.staticSetCreatedHourOfDay(siteRequest_, o);
		case "enrollmentDaysOfWeek":
			return SchoolEnrollment.staticSetEnrollmentDaysOfWeek(siteRequest_, o);
		case "enrollmentParentNames":
			return SchoolEnrollment.staticSetEnrollmentParentNames(siteRequest_, o);
		case "enrollmentEmails":
			return SchoolEnrollment.staticSetEnrollmentEmails(siteRequest_, o);
		case "enrollmentEmail":
			return SchoolEnrollment.staticSetEnrollmentEmail(siteRequest_, o);
		case "enrollmentParentEmails":
			return SchoolEnrollment.staticSetEnrollmentParentEmails(siteRequest_, o);
		case "enrollmentPhoneNumbers":
			return SchoolEnrollment.staticSetEnrollmentPhoneNumbers(siteRequest_, o);
		case "enrollmentPhoneNumber":
			return SchoolEnrollment.staticSetEnrollmentPhoneNumber(siteRequest_, o);
		case "enrollmentParentName":
			return SchoolEnrollment.staticSetEnrollmentParentName(siteRequest_, o);
		case "enrollmentParentNameLines":
			return SchoolEnrollment.staticSetEnrollmentParentNameLines(siteRequest_, o);
		case "enrollmentParentEmailLines":
			return SchoolEnrollment.staticSetEnrollmentParentEmailLines(siteRequest_, o);
		case "enrollmentParentDetailLines":
			return SchoolEnrollment.staticSetEnrollmentParentDetailLines(siteRequest_, o);
		case "enrollmentPickupDetailLines":
			return SchoolEnrollment.staticSetEnrollmentPickupDetailLines(siteRequest_, o);
		case "enrollmentEmergencyContactDetailLines":
			return SchoolEnrollment.staticSetEnrollmentEmergencyContactDetailLines(siteRequest_, o);
		case "enrollmentSignature1":
			return SchoolEnrollment.staticSetEnrollmentSignature1(siteRequest_, o);
		case "enrollmentSignature2":
			return SchoolEnrollment.staticSetEnrollmentSignature2(siteRequest_, o);
		case "enrollmentSignature3":
			return SchoolEnrollment.staticSetEnrollmentSignature3(siteRequest_, o);
		case "enrollmentSignature4":
			return SchoolEnrollment.staticSetEnrollmentSignature4(siteRequest_, o);
		case "enrollmentSignature5":
			return SchoolEnrollment.staticSetEnrollmentSignature5(siteRequest_, o);
		case "enrollmentSignature6":
			return SchoolEnrollment.staticSetEnrollmentSignature6(siteRequest_, o);
		case "enrollmentSignature7":
			return SchoolEnrollment.staticSetEnrollmentSignature7(siteRequest_, o);
		case "enrollmentSignature8":
			return SchoolEnrollment.staticSetEnrollmentSignature8(siteRequest_, o);
		case "enrollmentSignature9":
			return SchoolEnrollment.staticSetEnrollmentSignature9(siteRequest_, o);
		case "enrollmentSignature10":
			return SchoolEnrollment.staticSetEnrollmentSignature10(siteRequest_, o);
		case "enrollmentDate1":
			return SchoolEnrollment.staticSetEnrollmentDate1(siteRequest_, o);
		case "enrollmentDate2":
			return SchoolEnrollment.staticSetEnrollmentDate2(siteRequest_, o);
		case "enrollmentDate3":
			return SchoolEnrollment.staticSetEnrollmentDate3(siteRequest_, o);
		case "enrollmentDate4":
			return SchoolEnrollment.staticSetEnrollmentDate4(siteRequest_, o);
		case "enrollmentDate5":
			return SchoolEnrollment.staticSetEnrollmentDate5(siteRequest_, o);
		case "enrollmentDate6":
			return SchoolEnrollment.staticSetEnrollmentDate6(siteRequest_, o);
		case "enrollmentDate7":
			return SchoolEnrollment.staticSetEnrollmentDate7(siteRequest_, o);
		case "enrollmentDate8":
			return SchoolEnrollment.staticSetEnrollmentDate8(siteRequest_, o);
		case "enrollmentDate9":
			return SchoolEnrollment.staticSetEnrollmentDate9(siteRequest_, o);
		case "enrollmentDate10":
			return SchoolEnrollment.staticSetEnrollmentDate10(siteRequest_, o);
		case "childImmunizationsReceived":
			return SchoolEnrollment.staticSetChildImmunizationsReceived(siteRequest_, o);
		case "childPhotosApproved":
			return SchoolEnrollment.staticSetChildPhotosApproved(siteRequest_, o);
		case "enrollmentNumber":
			return SchoolEnrollment.staticSetEnrollmentNumber(siteRequest_, o);
		case "enrollmentCompleteName":
			return SchoolEnrollment.staticSetEnrollmentCompleteName(siteRequest_, o);
			default:
				return Cluster.staticSetCluster(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSchoolEnrollment(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSchoolEnrollment(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "enrollmentKey":
			return SchoolEnrollment.staticSolrEnrollmentKey(siteRequest_, (Long)o);
		case "yearKey":
			return SchoolEnrollment.staticSolrYearKey(siteRequest_, (Long)o);
		case "blockKeys":
			return SchoolEnrollment.staticSolrBlockKeys(siteRequest_, (Long)o);
		case "schoolKey":
			return SchoolEnrollment.staticSolrSchoolKey(siteRequest_, (Long)o);
		case "sessionKey":
			return SchoolEnrollment.staticSolrSessionKey(siteRequest_, (Long)o);
		case "ageKey":
			return SchoolEnrollment.staticSolrAgeKey(siteRequest_, (Long)o);
		case "blockKey":
			return SchoolEnrollment.staticSolrBlockKey(siteRequest_, (Long)o);
		case "childKey":
			return SchoolEnrollment.staticSolrChildKey(siteRequest_, (Long)o);
		case "momKeys":
			return SchoolEnrollment.staticSolrMomKeys(siteRequest_, (Long)o);
		case "dadKeys":
			return SchoolEnrollment.staticSolrDadKeys(siteRequest_, (Long)o);
		case "guardianKeys":
			return SchoolEnrollment.staticSolrGuardianKeys(siteRequest_, (Long)o);
		case "paymentKeys":
			return SchoolEnrollment.staticSolrPaymentKeys(siteRequest_, (Long)o);
		case "enrollmentFormKey":
			return SchoolEnrollment.staticSolrEnrollmentFormKey(siteRequest_, (Long)o);
		case "userKeys":
			return SchoolEnrollment.staticSolrUserKeys(siteRequest_, (Long)o);
		case "educationSort":
			return SchoolEnrollment.staticSolrEducationSort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolEnrollment.staticSolrSchoolSort(siteRequest_, (Integer)o);
		case "yearSort":
			return SchoolEnrollment.staticSolrYearSort(siteRequest_, (Integer)o);
		case "seasonSort":
			return SchoolEnrollment.staticSolrSeasonSort(siteRequest_, (Integer)o);
		case "sessionSort":
			return SchoolEnrollment.staticSolrSessionSort(siteRequest_, (Integer)o);
		case "ageSort":
			return SchoolEnrollment.staticSolrAgeSort(siteRequest_, (Integer)o);
		case "childFirstName":
			return SchoolEnrollment.staticSolrChildFirstName(siteRequest_, (String)o);
		case "childFirstNamePreferred":
			return SchoolEnrollment.staticSolrChildFirstNamePreferred(siteRequest_, (String)o);
		case "childFamilyName":
			return SchoolEnrollment.staticSolrChildFamilyName(siteRequest_, (String)o);
		case "momFirstName":
			return SchoolEnrollment.staticSolrMomFirstName(siteRequest_, (String)o);
		case "momFirstNamePreferred":
			return SchoolEnrollment.staticSolrMomFirstNamePreferred(siteRequest_, (String)o);
		case "momCompleteNamePreferred":
			return SchoolEnrollment.staticSolrMomCompleteNamePreferred(siteRequest_, (String)o);
		case "dadFirstName":
			return SchoolEnrollment.staticSolrDadFirstName(siteRequest_, (String)o);
		case "dadFirstNamePreferred":
			return SchoolEnrollment.staticSolrDadFirstNamePreferred(siteRequest_, (String)o);
		case "dadCompleteNamePreferred":
			return SchoolEnrollment.staticSolrDadCompleteNamePreferred(siteRequest_, (String)o);
		case "childCompleteName":
			return SchoolEnrollment.staticSolrChildCompleteName(siteRequest_, (String)o);
		case "childCompleteNamePreferred":
			return SchoolEnrollment.staticSolrChildCompleteNamePreferred(siteRequest_, (String)o);
		case "childBirthDate":
			return SchoolEnrollment.staticSolrChildBirthDate(siteRequest_, (LocalDate)o);
		case "childBirthDateYear":
			return SchoolEnrollment.staticSolrChildBirthDateYear(siteRequest_, (Integer)o);
		case "childBirthDateMonthOfYear":
			return SchoolEnrollment.staticSolrChildBirthDateMonthOfYear(siteRequest_, (String)o);
		case "childBirthDateDayOfWeek":
			return SchoolEnrollment.staticSolrChildBirthDateDayOfWeek(siteRequest_, (String)o);
		case "childBirthMonth":
			return SchoolEnrollment.staticSolrChildBirthMonth(siteRequest_, (Integer)o);
		case "childBirthDay":
			return SchoolEnrollment.staticSolrChildBirthDay(siteRequest_, (Integer)o);
		case "schoolName":
			return SchoolEnrollment.staticSolrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return SchoolEnrollment.staticSolrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return SchoolEnrollment.staticSolrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return SchoolEnrollment.staticSolrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return SchoolEnrollment.staticSolrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolForm":
			return SchoolEnrollment.staticSolrSchoolForm(siteRequest_, (String)o);
		case "schoolNumber":
			return SchoolEnrollment.staticSolrSchoolNumber(siteRequest_, (Integer)o);
		case "schoolAdministratorName":
			return SchoolEnrollment.staticSolrSchoolAdministratorName(siteRequest_, (String)o);
		case "yearStart":
			return SchoolEnrollment.staticSolrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return SchoolEnrollment.staticSolrYearEnd(siteRequest_, (Integer)o);
		case "seasonStartDate":
			return SchoolEnrollment.staticSolrSeasonStartDate(siteRequest_, (LocalDate)o);
		case "yearEnrollmentFee":
			return SchoolEnrollment.staticSolrYearEnrollmentFee(siteRequest_, (BigDecimal)o);
		case "sessionStartDate":
			return SchoolEnrollment.staticSolrSessionStartDate(siteRequest_, (LocalDate)o);
		case "sessionEndDate":
			return SchoolEnrollment.staticSolrSessionEndDate(siteRequest_, (LocalDate)o);
		case "ageCompleteName":
			return SchoolEnrollment.staticSolrAgeCompleteName(siteRequest_, (String)o);
		case "ageStart":
			return SchoolEnrollment.staticSolrAgeStart(siteRequest_, (Integer)o);
		case "ageEnd":
			return SchoolEnrollment.staticSolrAgeEnd(siteRequest_, (Integer)o);
		case "blockStartTime":
			return SchoolEnrollment.staticSolrBlockStartTime(siteRequest_, (LocalTime)o);
		case "blockEndTime":
			return SchoolEnrollment.staticSolrBlockEndTime(siteRequest_, (LocalTime)o);
		case "blockPricePerMonth":
			return SchoolEnrollment.staticSolrBlockPricePerMonth(siteRequest_, (BigDecimal)o);
		case "blockSunday":
			return SchoolEnrollment.staticSolrBlockSunday(siteRequest_, (Boolean)o);
		case "blockMonday":
			return SchoolEnrollment.staticSolrBlockMonday(siteRequest_, (Boolean)o);
		case "blockTuesday":
			return SchoolEnrollment.staticSolrBlockTuesday(siteRequest_, (Boolean)o);
		case "blockWednesday":
			return SchoolEnrollment.staticSolrBlockWednesday(siteRequest_, (Boolean)o);
		case "blockThursday":
			return SchoolEnrollment.staticSolrBlockThursday(siteRequest_, (Boolean)o);
		case "blockFriday":
			return SchoolEnrollment.staticSolrBlockFriday(siteRequest_, (Boolean)o);
		case "blockSaturday":
			return SchoolEnrollment.staticSolrBlockSaturday(siteRequest_, (Boolean)o);
		case "blockTotalPrice":
			return SchoolEnrollment.staticSolrBlockTotalPrice(siteRequest_, (BigDecimal)o);
		case "blockAdminName":
			return SchoolEnrollment.staticSolrBlockAdminName(siteRequest_, (String)o);
		case "blockShortName":
			return SchoolEnrollment.staticSolrBlockShortName(siteRequest_, (String)o);
		case "blockCompleteName":
			return SchoolEnrollment.staticSolrBlockCompleteName(siteRequest_, (String)o);
		case "enrollmentApproved":
			return SchoolEnrollment.staticSolrEnrollmentApproved(siteRequest_, (Boolean)o);
		case "enrollmentImmunizations":
			return SchoolEnrollment.staticSolrEnrollmentImmunizations(siteRequest_, (Boolean)o);
		case "photo":
			return SchoolEnrollment.staticSolrPhoto(siteRequest_, (String)o);
		case "familyMarried":
			return SchoolEnrollment.staticSolrFamilyMarried(siteRequest_, (Boolean)o);
		case "familySeparated":
			return SchoolEnrollment.staticSolrFamilySeparated(siteRequest_, (Boolean)o);
		case "familyDivorced":
			return SchoolEnrollment.staticSolrFamilyDivorced(siteRequest_, (Boolean)o);
		case "familyAddress":
			return SchoolEnrollment.staticSolrFamilyAddress(siteRequest_, (String)o);
		case "familyHowDoYouKnowTheSchool":
			return SchoolEnrollment.staticSolrFamilyHowDoYouKnowTheSchool(siteRequest_, (String)o);
		case "enrollmentSpecialConsiderations":
			return SchoolEnrollment.staticSolrEnrollmentSpecialConsiderations(siteRequest_, (String)o);
		case "childMedicalConditions":
			return SchoolEnrollment.staticSolrChildMedicalConditions(siteRequest_, (String)o);
		case "childPreviousSchoolsAttended":
			return SchoolEnrollment.staticSolrChildPreviousSchoolsAttended(siteRequest_, (String)o);
		case "childDescription":
			return SchoolEnrollment.staticSolrChildDescription(siteRequest_, (String)o);
		case "childObjectives":
			return SchoolEnrollment.staticSolrChildObjectives(siteRequest_, (String)o);
		case "childPottyTrained":
			return SchoolEnrollment.staticSolrChildPottyTrained(siteRequest_, (Boolean)o);
		case "enrollmentGroupName":
			return SchoolEnrollment.staticSolrEnrollmentGroupName(siteRequest_, (String)o);
		case "enrollmentGroupColor":
			return SchoolEnrollment.staticSolrEnrollmentGroupColor(siteRequest_, (String)o);
		case "enrollmentPaymentEachMonth":
			return SchoolEnrollment.staticSolrEnrollmentPaymentEachMonth(siteRequest_, (Boolean)o);
		case "enrollmentPaymentComplete":
			return SchoolEnrollment.staticSolrEnrollmentPaymentComplete(siteRequest_, (Boolean)o);
		case "customerProfileId":
			return SchoolEnrollment.staticSolrCustomerProfileId(siteRequest_, (String)o);
		case "enrollmentChargeDate":
			return SchoolEnrollment.staticSolrEnrollmentChargeDate(siteRequest_, (LocalDate)o);
		case "paymentLastDate":
			return SchoolEnrollment.staticSolrPaymentLastDate(siteRequest_, (LocalDate)o);
		case "paymentLastStr":
			return SchoolEnrollment.staticSolrPaymentLastStr(siteRequest_, (String)o);
		case "paymentAmount":
			return SchoolEnrollment.staticSolrPaymentAmount(siteRequest_, (BigDecimal)o);
		case "chargeAmount":
			return SchoolEnrollment.staticSolrChargeAmount(siteRequest_, (BigDecimal)o);
		case "chargeAmountFuture":
			return SchoolEnrollment.staticSolrChargeAmountFuture(siteRequest_, (BigDecimal)o);
		case "chargeAmountDue":
			return SchoolEnrollment.staticSolrChargeAmountDue(siteRequest_, (BigDecimal)o);
		case "chargeAmountNotPassed":
			return SchoolEnrollment.staticSolrChargeAmountNotPassed(siteRequest_, (BigDecimal)o);
		case "chargesNow":
			return SchoolEnrollment.staticSolrChargesNow(siteRequest_, (BigDecimal)o);
		case "paymentsCurrent":
			return SchoolEnrollment.staticSolrPaymentsCurrent(siteRequest_, (Boolean)o);
		case "paymentsLate":
			return SchoolEnrollment.staticSolrPaymentsLate(siteRequest_, (Boolean)o);
		case "paymentsLateAmount":
			return SchoolEnrollment.staticSolrPaymentsLateAmount(siteRequest_, (BigDecimal)o);
		case "paymentsAhead":
			return SchoolEnrollment.staticSolrPaymentsAhead(siteRequest_, (Boolean)o);
		case "paymentsPastDue":
			return SchoolEnrollment.staticSolrPaymentsPastDue(siteRequest_, (Boolean)o);
		case "paymentsPastDueAmount":
			return SchoolEnrollment.staticSolrPaymentsPastDueAmount(siteRequest_, (BigDecimal)o);
		case "chargesCreated":
			return SchoolEnrollment.staticSolrChargesCreated(siteRequest_, (Boolean)o);
		case "createdYear":
			return SchoolEnrollment.staticSolrCreatedYear(siteRequest_, (Integer)o);
		case "createdDayOfWeek":
			return SchoolEnrollment.staticSolrCreatedDayOfWeek(siteRequest_, (String)o);
		case "createdMonthOfYear":
			return SchoolEnrollment.staticSolrCreatedMonthOfYear(siteRequest_, (String)o);
		case "createdHourOfDay":
			return SchoolEnrollment.staticSolrCreatedHourOfDay(siteRequest_, (String)o);
		case "enrollmentDaysOfWeek":
			return SchoolEnrollment.staticSolrEnrollmentDaysOfWeek(siteRequest_, (String)o);
		case "enrollmentParentNames":
			return SchoolEnrollment.staticSolrEnrollmentParentNames(siteRequest_, (String)o);
		case "enrollmentEmails":
			return SchoolEnrollment.staticSolrEnrollmentEmails(siteRequest_, (String)o);
		case "enrollmentEmail":
			return SchoolEnrollment.staticSolrEnrollmentEmail(siteRequest_, (String)o);
		case "enrollmentParentEmails":
			return SchoolEnrollment.staticSolrEnrollmentParentEmails(siteRequest_, (String)o);
		case "enrollmentPhoneNumbers":
			return SchoolEnrollment.staticSolrEnrollmentPhoneNumbers(siteRequest_, (String)o);
		case "enrollmentPhoneNumber":
			return SchoolEnrollment.staticSolrEnrollmentPhoneNumber(siteRequest_, (String)o);
		case "enrollmentParentName":
			return SchoolEnrollment.staticSolrEnrollmentParentName(siteRequest_, (String)o);
		case "enrollmentParentNameLines":
			return SchoolEnrollment.staticSolrEnrollmentParentNameLines(siteRequest_, (String)o);
		case "enrollmentParentEmailLines":
			return SchoolEnrollment.staticSolrEnrollmentParentEmailLines(siteRequest_, (String)o);
		case "enrollmentParentDetailLines":
			return SchoolEnrollment.staticSolrEnrollmentParentDetailLines(siteRequest_, (String)o);
		case "enrollmentPickupDetailLines":
			return SchoolEnrollment.staticSolrEnrollmentPickupDetailLines(siteRequest_, (String)o);
		case "enrollmentEmergencyContactDetailLines":
			return SchoolEnrollment.staticSolrEnrollmentEmergencyContactDetailLines(siteRequest_, (String)o);
		case "enrollmentSignature1":
			return SchoolEnrollment.staticSolrEnrollmentSignature1(siteRequest_, (String)o);
		case "enrollmentSignature2":
			return SchoolEnrollment.staticSolrEnrollmentSignature2(siteRequest_, (String)o);
		case "enrollmentSignature3":
			return SchoolEnrollment.staticSolrEnrollmentSignature3(siteRequest_, (String)o);
		case "enrollmentSignature4":
			return SchoolEnrollment.staticSolrEnrollmentSignature4(siteRequest_, (String)o);
		case "enrollmentSignature5":
			return SchoolEnrollment.staticSolrEnrollmentSignature5(siteRequest_, (String)o);
		case "enrollmentSignature6":
			return SchoolEnrollment.staticSolrEnrollmentSignature6(siteRequest_, (String)o);
		case "enrollmentSignature7":
			return SchoolEnrollment.staticSolrEnrollmentSignature7(siteRequest_, (String)o);
		case "enrollmentSignature8":
			return SchoolEnrollment.staticSolrEnrollmentSignature8(siteRequest_, (String)o);
		case "enrollmentSignature9":
			return SchoolEnrollment.staticSolrEnrollmentSignature9(siteRequest_, (String)o);
		case "enrollmentSignature10":
			return SchoolEnrollment.staticSolrEnrollmentSignature10(siteRequest_, (String)o);
		case "enrollmentDate1":
			return SchoolEnrollment.staticSolrEnrollmentDate1(siteRequest_, (LocalDate)o);
		case "enrollmentDate2":
			return SchoolEnrollment.staticSolrEnrollmentDate2(siteRequest_, (LocalDate)o);
		case "enrollmentDate3":
			return SchoolEnrollment.staticSolrEnrollmentDate3(siteRequest_, (LocalDate)o);
		case "enrollmentDate4":
			return SchoolEnrollment.staticSolrEnrollmentDate4(siteRequest_, (LocalDate)o);
		case "enrollmentDate5":
			return SchoolEnrollment.staticSolrEnrollmentDate5(siteRequest_, (LocalDate)o);
		case "enrollmentDate6":
			return SchoolEnrollment.staticSolrEnrollmentDate6(siteRequest_, (LocalDate)o);
		case "enrollmentDate7":
			return SchoolEnrollment.staticSolrEnrollmentDate7(siteRequest_, (LocalDate)o);
		case "enrollmentDate8":
			return SchoolEnrollment.staticSolrEnrollmentDate8(siteRequest_, (LocalDate)o);
		case "enrollmentDate9":
			return SchoolEnrollment.staticSolrEnrollmentDate9(siteRequest_, (LocalDate)o);
		case "enrollmentDate10":
			return SchoolEnrollment.staticSolrEnrollmentDate10(siteRequest_, (LocalDate)o);
		case "childImmunizationsReceived":
			return SchoolEnrollment.staticSolrChildImmunizationsReceived(siteRequest_, (String)o);
		case "childPhotosApproved":
			return SchoolEnrollment.staticSolrChildPhotosApproved(siteRequest_, (String)o);
		case "enrollmentNumber":
			return SchoolEnrollment.staticSolrEnrollmentNumber(siteRequest_, (Integer)o);
		case "enrollmentCompleteName":
			return SchoolEnrollment.staticSolrEnrollmentCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrCluster(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSchoolEnrollment(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSchoolEnrollment(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "enrollmentKey":
			return SchoolEnrollment.staticSolrStrEnrollmentKey(siteRequest_, (Long)o);
		case "yearKey":
			return SchoolEnrollment.staticSolrStrYearKey(siteRequest_, (Long)o);
		case "blockKeys":
			return SchoolEnrollment.staticSolrStrBlockKeys(siteRequest_, (Long)o);
		case "schoolKey":
			return SchoolEnrollment.staticSolrStrSchoolKey(siteRequest_, (Long)o);
		case "sessionKey":
			return SchoolEnrollment.staticSolrStrSessionKey(siteRequest_, (Long)o);
		case "ageKey":
			return SchoolEnrollment.staticSolrStrAgeKey(siteRequest_, (Long)o);
		case "blockKey":
			return SchoolEnrollment.staticSolrStrBlockKey(siteRequest_, (Long)o);
		case "childKey":
			return SchoolEnrollment.staticSolrStrChildKey(siteRequest_, (Long)o);
		case "momKeys":
			return SchoolEnrollment.staticSolrStrMomKeys(siteRequest_, (Long)o);
		case "dadKeys":
			return SchoolEnrollment.staticSolrStrDadKeys(siteRequest_, (Long)o);
		case "guardianKeys":
			return SchoolEnrollment.staticSolrStrGuardianKeys(siteRequest_, (Long)o);
		case "paymentKeys":
			return SchoolEnrollment.staticSolrStrPaymentKeys(siteRequest_, (Long)o);
		case "enrollmentFormKey":
			return SchoolEnrollment.staticSolrStrEnrollmentFormKey(siteRequest_, (Long)o);
		case "userKeys":
			return SchoolEnrollment.staticSolrStrUserKeys(siteRequest_, (Long)o);
		case "educationSort":
			return SchoolEnrollment.staticSolrStrEducationSort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolEnrollment.staticSolrStrSchoolSort(siteRequest_, (Integer)o);
		case "yearSort":
			return SchoolEnrollment.staticSolrStrYearSort(siteRequest_, (Integer)o);
		case "seasonSort":
			return SchoolEnrollment.staticSolrStrSeasonSort(siteRequest_, (Integer)o);
		case "sessionSort":
			return SchoolEnrollment.staticSolrStrSessionSort(siteRequest_, (Integer)o);
		case "ageSort":
			return SchoolEnrollment.staticSolrStrAgeSort(siteRequest_, (Integer)o);
		case "childFirstName":
			return SchoolEnrollment.staticSolrStrChildFirstName(siteRequest_, (String)o);
		case "childFirstNamePreferred":
			return SchoolEnrollment.staticSolrStrChildFirstNamePreferred(siteRequest_, (String)o);
		case "childFamilyName":
			return SchoolEnrollment.staticSolrStrChildFamilyName(siteRequest_, (String)o);
		case "momFirstName":
			return SchoolEnrollment.staticSolrStrMomFirstName(siteRequest_, (String)o);
		case "momFirstNamePreferred":
			return SchoolEnrollment.staticSolrStrMomFirstNamePreferred(siteRequest_, (String)o);
		case "momCompleteNamePreferred":
			return SchoolEnrollment.staticSolrStrMomCompleteNamePreferred(siteRequest_, (String)o);
		case "dadFirstName":
			return SchoolEnrollment.staticSolrStrDadFirstName(siteRequest_, (String)o);
		case "dadFirstNamePreferred":
			return SchoolEnrollment.staticSolrStrDadFirstNamePreferred(siteRequest_, (String)o);
		case "dadCompleteNamePreferred":
			return SchoolEnrollment.staticSolrStrDadCompleteNamePreferred(siteRequest_, (String)o);
		case "childCompleteName":
			return SchoolEnrollment.staticSolrStrChildCompleteName(siteRequest_, (String)o);
		case "childCompleteNamePreferred":
			return SchoolEnrollment.staticSolrStrChildCompleteNamePreferred(siteRequest_, (String)o);
		case "childBirthDate":
			return SchoolEnrollment.staticSolrStrChildBirthDate(siteRequest_, (Date)o);
		case "childBirthDateYear":
			return SchoolEnrollment.staticSolrStrChildBirthDateYear(siteRequest_, (Integer)o);
		case "childBirthDateMonthOfYear":
			return SchoolEnrollment.staticSolrStrChildBirthDateMonthOfYear(siteRequest_, (String)o);
		case "childBirthDateDayOfWeek":
			return SchoolEnrollment.staticSolrStrChildBirthDateDayOfWeek(siteRequest_, (String)o);
		case "childBirthMonth":
			return SchoolEnrollment.staticSolrStrChildBirthMonth(siteRequest_, (Integer)o);
		case "childBirthDay":
			return SchoolEnrollment.staticSolrStrChildBirthDay(siteRequest_, (Integer)o);
		case "schoolName":
			return SchoolEnrollment.staticSolrStrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return SchoolEnrollment.staticSolrStrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return SchoolEnrollment.staticSolrStrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return SchoolEnrollment.staticSolrStrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return SchoolEnrollment.staticSolrStrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolForm":
			return SchoolEnrollment.staticSolrStrSchoolForm(siteRequest_, (String)o);
		case "schoolNumber":
			return SchoolEnrollment.staticSolrStrSchoolNumber(siteRequest_, (Integer)o);
		case "schoolAdministratorName":
			return SchoolEnrollment.staticSolrStrSchoolAdministratorName(siteRequest_, (String)o);
		case "yearStart":
			return SchoolEnrollment.staticSolrStrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return SchoolEnrollment.staticSolrStrYearEnd(siteRequest_, (Integer)o);
		case "seasonStartDate":
			return SchoolEnrollment.staticSolrStrSeasonStartDate(siteRequest_, (Date)o);
		case "yearEnrollmentFee":
			return SchoolEnrollment.staticSolrStrYearEnrollmentFee(siteRequest_, (Double)o);
		case "sessionStartDate":
			return SchoolEnrollment.staticSolrStrSessionStartDate(siteRequest_, (Date)o);
		case "sessionEndDate":
			return SchoolEnrollment.staticSolrStrSessionEndDate(siteRequest_, (Date)o);
		case "ageCompleteName":
			return SchoolEnrollment.staticSolrStrAgeCompleteName(siteRequest_, (String)o);
		case "ageStart":
			return SchoolEnrollment.staticSolrStrAgeStart(siteRequest_, (Integer)o);
		case "ageEnd":
			return SchoolEnrollment.staticSolrStrAgeEnd(siteRequest_, (Integer)o);
		case "blockStartTime":
			return SchoolEnrollment.staticSolrStrBlockStartTime(siteRequest_, (String)o);
		case "blockEndTime":
			return SchoolEnrollment.staticSolrStrBlockEndTime(siteRequest_, (String)o);
		case "blockPricePerMonth":
			return SchoolEnrollment.staticSolrStrBlockPricePerMonth(siteRequest_, (Double)o);
		case "blockSunday":
			return SchoolEnrollment.staticSolrStrBlockSunday(siteRequest_, (Boolean)o);
		case "blockMonday":
			return SchoolEnrollment.staticSolrStrBlockMonday(siteRequest_, (Boolean)o);
		case "blockTuesday":
			return SchoolEnrollment.staticSolrStrBlockTuesday(siteRequest_, (Boolean)o);
		case "blockWednesday":
			return SchoolEnrollment.staticSolrStrBlockWednesday(siteRequest_, (Boolean)o);
		case "blockThursday":
			return SchoolEnrollment.staticSolrStrBlockThursday(siteRequest_, (Boolean)o);
		case "blockFriday":
			return SchoolEnrollment.staticSolrStrBlockFriday(siteRequest_, (Boolean)o);
		case "blockSaturday":
			return SchoolEnrollment.staticSolrStrBlockSaturday(siteRequest_, (Boolean)o);
		case "blockTotalPrice":
			return SchoolEnrollment.staticSolrStrBlockTotalPrice(siteRequest_, (Double)o);
		case "blockAdminName":
			return SchoolEnrollment.staticSolrStrBlockAdminName(siteRequest_, (String)o);
		case "blockShortName":
			return SchoolEnrollment.staticSolrStrBlockShortName(siteRequest_, (String)o);
		case "blockCompleteName":
			return SchoolEnrollment.staticSolrStrBlockCompleteName(siteRequest_, (String)o);
		case "enrollmentApproved":
			return SchoolEnrollment.staticSolrStrEnrollmentApproved(siteRequest_, (Boolean)o);
		case "enrollmentImmunizations":
			return SchoolEnrollment.staticSolrStrEnrollmentImmunizations(siteRequest_, (Boolean)o);
		case "photo":
			return SchoolEnrollment.staticSolrStrPhoto(siteRequest_, (String)o);
		case "familyMarried":
			return SchoolEnrollment.staticSolrStrFamilyMarried(siteRequest_, (Boolean)o);
		case "familySeparated":
			return SchoolEnrollment.staticSolrStrFamilySeparated(siteRequest_, (Boolean)o);
		case "familyDivorced":
			return SchoolEnrollment.staticSolrStrFamilyDivorced(siteRequest_, (Boolean)o);
		case "familyAddress":
			return SchoolEnrollment.staticSolrStrFamilyAddress(siteRequest_, (String)o);
		case "familyHowDoYouKnowTheSchool":
			return SchoolEnrollment.staticSolrStrFamilyHowDoYouKnowTheSchool(siteRequest_, (String)o);
		case "enrollmentSpecialConsiderations":
			return SchoolEnrollment.staticSolrStrEnrollmentSpecialConsiderations(siteRequest_, (String)o);
		case "childMedicalConditions":
			return SchoolEnrollment.staticSolrStrChildMedicalConditions(siteRequest_, (String)o);
		case "childPreviousSchoolsAttended":
			return SchoolEnrollment.staticSolrStrChildPreviousSchoolsAttended(siteRequest_, (String)o);
		case "childDescription":
			return SchoolEnrollment.staticSolrStrChildDescription(siteRequest_, (String)o);
		case "childObjectives":
			return SchoolEnrollment.staticSolrStrChildObjectives(siteRequest_, (String)o);
		case "childPottyTrained":
			return SchoolEnrollment.staticSolrStrChildPottyTrained(siteRequest_, (Boolean)o);
		case "enrollmentGroupName":
			return SchoolEnrollment.staticSolrStrEnrollmentGroupName(siteRequest_, (String)o);
		case "enrollmentGroupColor":
			return SchoolEnrollment.staticSolrStrEnrollmentGroupColor(siteRequest_, (String)o);
		case "enrollmentPaymentEachMonth":
			return SchoolEnrollment.staticSolrStrEnrollmentPaymentEachMonth(siteRequest_, (Boolean)o);
		case "enrollmentPaymentComplete":
			return SchoolEnrollment.staticSolrStrEnrollmentPaymentComplete(siteRequest_, (Boolean)o);
		case "customerProfileId":
			return SchoolEnrollment.staticSolrStrCustomerProfileId(siteRequest_, (String)o);
		case "enrollmentChargeDate":
			return SchoolEnrollment.staticSolrStrEnrollmentChargeDate(siteRequest_, (Date)o);
		case "paymentLastDate":
			return SchoolEnrollment.staticSolrStrPaymentLastDate(siteRequest_, (Date)o);
		case "paymentLastStr":
			return SchoolEnrollment.staticSolrStrPaymentLastStr(siteRequest_, (String)o);
		case "paymentAmount":
			return SchoolEnrollment.staticSolrStrPaymentAmount(siteRequest_, (Double)o);
		case "chargeAmount":
			return SchoolEnrollment.staticSolrStrChargeAmount(siteRequest_, (Double)o);
		case "chargeAmountFuture":
			return SchoolEnrollment.staticSolrStrChargeAmountFuture(siteRequest_, (Double)o);
		case "chargeAmountDue":
			return SchoolEnrollment.staticSolrStrChargeAmountDue(siteRequest_, (Double)o);
		case "chargeAmountNotPassed":
			return SchoolEnrollment.staticSolrStrChargeAmountNotPassed(siteRequest_, (Double)o);
		case "chargesNow":
			return SchoolEnrollment.staticSolrStrChargesNow(siteRequest_, (Double)o);
		case "paymentsCurrent":
			return SchoolEnrollment.staticSolrStrPaymentsCurrent(siteRequest_, (Boolean)o);
		case "paymentsLate":
			return SchoolEnrollment.staticSolrStrPaymentsLate(siteRequest_, (Boolean)o);
		case "paymentsLateAmount":
			return SchoolEnrollment.staticSolrStrPaymentsLateAmount(siteRequest_, (Double)o);
		case "paymentsAhead":
			return SchoolEnrollment.staticSolrStrPaymentsAhead(siteRequest_, (Boolean)o);
		case "paymentsPastDue":
			return SchoolEnrollment.staticSolrStrPaymentsPastDue(siteRequest_, (Boolean)o);
		case "paymentsPastDueAmount":
			return SchoolEnrollment.staticSolrStrPaymentsPastDueAmount(siteRequest_, (Double)o);
		case "chargesCreated":
			return SchoolEnrollment.staticSolrStrChargesCreated(siteRequest_, (Boolean)o);
		case "createdYear":
			return SchoolEnrollment.staticSolrStrCreatedYear(siteRequest_, (Integer)o);
		case "createdDayOfWeek":
			return SchoolEnrollment.staticSolrStrCreatedDayOfWeek(siteRequest_, (String)o);
		case "createdMonthOfYear":
			return SchoolEnrollment.staticSolrStrCreatedMonthOfYear(siteRequest_, (String)o);
		case "createdHourOfDay":
			return SchoolEnrollment.staticSolrStrCreatedHourOfDay(siteRequest_, (String)o);
		case "enrollmentDaysOfWeek":
			return SchoolEnrollment.staticSolrStrEnrollmentDaysOfWeek(siteRequest_, (String)o);
		case "enrollmentParentNames":
			return SchoolEnrollment.staticSolrStrEnrollmentParentNames(siteRequest_, (String)o);
		case "enrollmentEmails":
			return SchoolEnrollment.staticSolrStrEnrollmentEmails(siteRequest_, (String)o);
		case "enrollmentEmail":
			return SchoolEnrollment.staticSolrStrEnrollmentEmail(siteRequest_, (String)o);
		case "enrollmentParentEmails":
			return SchoolEnrollment.staticSolrStrEnrollmentParentEmails(siteRequest_, (String)o);
		case "enrollmentPhoneNumbers":
			return SchoolEnrollment.staticSolrStrEnrollmentPhoneNumbers(siteRequest_, (String)o);
		case "enrollmentPhoneNumber":
			return SchoolEnrollment.staticSolrStrEnrollmentPhoneNumber(siteRequest_, (String)o);
		case "enrollmentParentName":
			return SchoolEnrollment.staticSolrStrEnrollmentParentName(siteRequest_, (String)o);
		case "enrollmentParentNameLines":
			return SchoolEnrollment.staticSolrStrEnrollmentParentNameLines(siteRequest_, (String)o);
		case "enrollmentParentEmailLines":
			return SchoolEnrollment.staticSolrStrEnrollmentParentEmailLines(siteRequest_, (String)o);
		case "enrollmentParentDetailLines":
			return SchoolEnrollment.staticSolrStrEnrollmentParentDetailLines(siteRequest_, (String)o);
		case "enrollmentPickupDetailLines":
			return SchoolEnrollment.staticSolrStrEnrollmentPickupDetailLines(siteRequest_, (String)o);
		case "enrollmentEmergencyContactDetailLines":
			return SchoolEnrollment.staticSolrStrEnrollmentEmergencyContactDetailLines(siteRequest_, (String)o);
		case "enrollmentSignature1":
			return SchoolEnrollment.staticSolrStrEnrollmentSignature1(siteRequest_, (String)o);
		case "enrollmentSignature2":
			return SchoolEnrollment.staticSolrStrEnrollmentSignature2(siteRequest_, (String)o);
		case "enrollmentSignature3":
			return SchoolEnrollment.staticSolrStrEnrollmentSignature3(siteRequest_, (String)o);
		case "enrollmentSignature4":
			return SchoolEnrollment.staticSolrStrEnrollmentSignature4(siteRequest_, (String)o);
		case "enrollmentSignature5":
			return SchoolEnrollment.staticSolrStrEnrollmentSignature5(siteRequest_, (String)o);
		case "enrollmentSignature6":
			return SchoolEnrollment.staticSolrStrEnrollmentSignature6(siteRequest_, (String)o);
		case "enrollmentSignature7":
			return SchoolEnrollment.staticSolrStrEnrollmentSignature7(siteRequest_, (String)o);
		case "enrollmentSignature8":
			return SchoolEnrollment.staticSolrStrEnrollmentSignature8(siteRequest_, (String)o);
		case "enrollmentSignature9":
			return SchoolEnrollment.staticSolrStrEnrollmentSignature9(siteRequest_, (String)o);
		case "enrollmentSignature10":
			return SchoolEnrollment.staticSolrStrEnrollmentSignature10(siteRequest_, (String)o);
		case "enrollmentDate1":
			return SchoolEnrollment.staticSolrStrEnrollmentDate1(siteRequest_, (Date)o);
		case "enrollmentDate2":
			return SchoolEnrollment.staticSolrStrEnrollmentDate2(siteRequest_, (Date)o);
		case "enrollmentDate3":
			return SchoolEnrollment.staticSolrStrEnrollmentDate3(siteRequest_, (Date)o);
		case "enrollmentDate4":
			return SchoolEnrollment.staticSolrStrEnrollmentDate4(siteRequest_, (Date)o);
		case "enrollmentDate5":
			return SchoolEnrollment.staticSolrStrEnrollmentDate5(siteRequest_, (Date)o);
		case "enrollmentDate6":
			return SchoolEnrollment.staticSolrStrEnrollmentDate6(siteRequest_, (Date)o);
		case "enrollmentDate7":
			return SchoolEnrollment.staticSolrStrEnrollmentDate7(siteRequest_, (Date)o);
		case "enrollmentDate8":
			return SchoolEnrollment.staticSolrStrEnrollmentDate8(siteRequest_, (Date)o);
		case "enrollmentDate9":
			return SchoolEnrollment.staticSolrStrEnrollmentDate9(siteRequest_, (Date)o);
		case "enrollmentDate10":
			return SchoolEnrollment.staticSolrStrEnrollmentDate10(siteRequest_, (Date)o);
		case "childImmunizationsReceived":
			return SchoolEnrollment.staticSolrStrChildImmunizationsReceived(siteRequest_, (String)o);
		case "childPhotosApproved":
			return SchoolEnrollment.staticSolrStrChildPhotosApproved(siteRequest_, (String)o);
		case "enrollmentNumber":
			return SchoolEnrollment.staticSolrStrEnrollmentNumber(siteRequest_, (Integer)o);
		case "enrollmentCompleteName":
			return SchoolEnrollment.staticSolrStrEnrollmentCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSchoolEnrollment(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSchoolEnrollment(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "enrollmentKey":
			return SchoolEnrollment.staticSolrFqEnrollmentKey(siteRequest_, o);
		case "yearKey":
			return SchoolEnrollment.staticSolrFqYearKey(siteRequest_, o);
		case "blockKeys":
			return SchoolEnrollment.staticSolrFqBlockKeys(siteRequest_, o);
		case "schoolKey":
			return SchoolEnrollment.staticSolrFqSchoolKey(siteRequest_, o);
		case "sessionKey":
			return SchoolEnrollment.staticSolrFqSessionKey(siteRequest_, o);
		case "ageKey":
			return SchoolEnrollment.staticSolrFqAgeKey(siteRequest_, o);
		case "blockKey":
			return SchoolEnrollment.staticSolrFqBlockKey(siteRequest_, o);
		case "childKey":
			return SchoolEnrollment.staticSolrFqChildKey(siteRequest_, o);
		case "momKeys":
			return SchoolEnrollment.staticSolrFqMomKeys(siteRequest_, o);
		case "dadKeys":
			return SchoolEnrollment.staticSolrFqDadKeys(siteRequest_, o);
		case "guardianKeys":
			return SchoolEnrollment.staticSolrFqGuardianKeys(siteRequest_, o);
		case "paymentKeys":
			return SchoolEnrollment.staticSolrFqPaymentKeys(siteRequest_, o);
		case "enrollmentFormKey":
			return SchoolEnrollment.staticSolrFqEnrollmentFormKey(siteRequest_, o);
		case "userKeys":
			return SchoolEnrollment.staticSolrFqUserKeys(siteRequest_, o);
		case "educationSort":
			return SchoolEnrollment.staticSolrFqEducationSort(siteRequest_, o);
		case "schoolSort":
			return SchoolEnrollment.staticSolrFqSchoolSort(siteRequest_, o);
		case "yearSort":
			return SchoolEnrollment.staticSolrFqYearSort(siteRequest_, o);
		case "seasonSort":
			return SchoolEnrollment.staticSolrFqSeasonSort(siteRequest_, o);
		case "sessionSort":
			return SchoolEnrollment.staticSolrFqSessionSort(siteRequest_, o);
		case "ageSort":
			return SchoolEnrollment.staticSolrFqAgeSort(siteRequest_, o);
		case "childFirstName":
			return SchoolEnrollment.staticSolrFqChildFirstName(siteRequest_, o);
		case "childFirstNamePreferred":
			return SchoolEnrollment.staticSolrFqChildFirstNamePreferred(siteRequest_, o);
		case "childFamilyName":
			return SchoolEnrollment.staticSolrFqChildFamilyName(siteRequest_, o);
		case "momFirstName":
			return SchoolEnrollment.staticSolrFqMomFirstName(siteRequest_, o);
		case "momFirstNamePreferred":
			return SchoolEnrollment.staticSolrFqMomFirstNamePreferred(siteRequest_, o);
		case "momCompleteNamePreferred":
			return SchoolEnrollment.staticSolrFqMomCompleteNamePreferred(siteRequest_, o);
		case "dadFirstName":
			return SchoolEnrollment.staticSolrFqDadFirstName(siteRequest_, o);
		case "dadFirstNamePreferred":
			return SchoolEnrollment.staticSolrFqDadFirstNamePreferred(siteRequest_, o);
		case "dadCompleteNamePreferred":
			return SchoolEnrollment.staticSolrFqDadCompleteNamePreferred(siteRequest_, o);
		case "childCompleteName":
			return SchoolEnrollment.staticSolrFqChildCompleteName(siteRequest_, o);
		case "childCompleteNamePreferred":
			return SchoolEnrollment.staticSolrFqChildCompleteNamePreferred(siteRequest_, o);
		case "childBirthDate":
			return SchoolEnrollment.staticSolrFqChildBirthDate(siteRequest_, o);
		case "childBirthDateYear":
			return SchoolEnrollment.staticSolrFqChildBirthDateYear(siteRequest_, o);
		case "childBirthDateMonthOfYear":
			return SchoolEnrollment.staticSolrFqChildBirthDateMonthOfYear(siteRequest_, o);
		case "childBirthDateDayOfWeek":
			return SchoolEnrollment.staticSolrFqChildBirthDateDayOfWeek(siteRequest_, o);
		case "childBirthMonth":
			return SchoolEnrollment.staticSolrFqChildBirthMonth(siteRequest_, o);
		case "childBirthDay":
			return SchoolEnrollment.staticSolrFqChildBirthDay(siteRequest_, o);
		case "schoolName":
			return SchoolEnrollment.staticSolrFqSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return SchoolEnrollment.staticSolrFqSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return SchoolEnrollment.staticSolrFqSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return SchoolEnrollment.staticSolrFqSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return SchoolEnrollment.staticSolrFqSchoolPhoneNumber(siteRequest_, o);
		case "schoolForm":
			return SchoolEnrollment.staticSolrFqSchoolForm(siteRequest_, o);
		case "schoolNumber":
			return SchoolEnrollment.staticSolrFqSchoolNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return SchoolEnrollment.staticSolrFqSchoolAdministratorName(siteRequest_, o);
		case "yearStart":
			return SchoolEnrollment.staticSolrFqYearStart(siteRequest_, o);
		case "yearEnd":
			return SchoolEnrollment.staticSolrFqYearEnd(siteRequest_, o);
		case "seasonStartDate":
			return SchoolEnrollment.staticSolrFqSeasonStartDate(siteRequest_, o);
		case "yearEnrollmentFee":
			return SchoolEnrollment.staticSolrFqYearEnrollmentFee(siteRequest_, o);
		case "sessionStartDate":
			return SchoolEnrollment.staticSolrFqSessionStartDate(siteRequest_, o);
		case "sessionEndDate":
			return SchoolEnrollment.staticSolrFqSessionEndDate(siteRequest_, o);
		case "ageCompleteName":
			return SchoolEnrollment.staticSolrFqAgeCompleteName(siteRequest_, o);
		case "ageStart":
			return SchoolEnrollment.staticSolrFqAgeStart(siteRequest_, o);
		case "ageEnd":
			return SchoolEnrollment.staticSolrFqAgeEnd(siteRequest_, o);
		case "blockStartTime":
			return SchoolEnrollment.staticSolrFqBlockStartTime(siteRequest_, o);
		case "blockEndTime":
			return SchoolEnrollment.staticSolrFqBlockEndTime(siteRequest_, o);
		case "blockPricePerMonth":
			return SchoolEnrollment.staticSolrFqBlockPricePerMonth(siteRequest_, o);
		case "blockSunday":
			return SchoolEnrollment.staticSolrFqBlockSunday(siteRequest_, o);
		case "blockMonday":
			return SchoolEnrollment.staticSolrFqBlockMonday(siteRequest_, o);
		case "blockTuesday":
			return SchoolEnrollment.staticSolrFqBlockTuesday(siteRequest_, o);
		case "blockWednesday":
			return SchoolEnrollment.staticSolrFqBlockWednesday(siteRequest_, o);
		case "blockThursday":
			return SchoolEnrollment.staticSolrFqBlockThursday(siteRequest_, o);
		case "blockFriday":
			return SchoolEnrollment.staticSolrFqBlockFriday(siteRequest_, o);
		case "blockSaturday":
			return SchoolEnrollment.staticSolrFqBlockSaturday(siteRequest_, o);
		case "blockTotalPrice":
			return SchoolEnrollment.staticSolrFqBlockTotalPrice(siteRequest_, o);
		case "blockAdminName":
			return SchoolEnrollment.staticSolrFqBlockAdminName(siteRequest_, o);
		case "blockShortName":
			return SchoolEnrollment.staticSolrFqBlockShortName(siteRequest_, o);
		case "blockCompleteName":
			return SchoolEnrollment.staticSolrFqBlockCompleteName(siteRequest_, o);
		case "enrollmentApproved":
			return SchoolEnrollment.staticSolrFqEnrollmentApproved(siteRequest_, o);
		case "enrollmentImmunizations":
			return SchoolEnrollment.staticSolrFqEnrollmentImmunizations(siteRequest_, o);
		case "photo":
			return SchoolEnrollment.staticSolrFqPhoto(siteRequest_, o);
		case "familyMarried":
			return SchoolEnrollment.staticSolrFqFamilyMarried(siteRequest_, o);
		case "familySeparated":
			return SchoolEnrollment.staticSolrFqFamilySeparated(siteRequest_, o);
		case "familyDivorced":
			return SchoolEnrollment.staticSolrFqFamilyDivorced(siteRequest_, o);
		case "familyAddress":
			return SchoolEnrollment.staticSolrFqFamilyAddress(siteRequest_, o);
		case "familyHowDoYouKnowTheSchool":
			return SchoolEnrollment.staticSolrFqFamilyHowDoYouKnowTheSchool(siteRequest_, o);
		case "enrollmentSpecialConsiderations":
			return SchoolEnrollment.staticSolrFqEnrollmentSpecialConsiderations(siteRequest_, o);
		case "childMedicalConditions":
			return SchoolEnrollment.staticSolrFqChildMedicalConditions(siteRequest_, o);
		case "childPreviousSchoolsAttended":
			return SchoolEnrollment.staticSolrFqChildPreviousSchoolsAttended(siteRequest_, o);
		case "childDescription":
			return SchoolEnrollment.staticSolrFqChildDescription(siteRequest_, o);
		case "childObjectives":
			return SchoolEnrollment.staticSolrFqChildObjectives(siteRequest_, o);
		case "childPottyTrained":
			return SchoolEnrollment.staticSolrFqChildPottyTrained(siteRequest_, o);
		case "enrollmentGroupName":
			return SchoolEnrollment.staticSolrFqEnrollmentGroupName(siteRequest_, o);
		case "enrollmentGroupColor":
			return SchoolEnrollment.staticSolrFqEnrollmentGroupColor(siteRequest_, o);
		case "enrollmentPaymentEachMonth":
			return SchoolEnrollment.staticSolrFqEnrollmentPaymentEachMonth(siteRequest_, o);
		case "enrollmentPaymentComplete":
			return SchoolEnrollment.staticSolrFqEnrollmentPaymentComplete(siteRequest_, o);
		case "customerProfileId":
			return SchoolEnrollment.staticSolrFqCustomerProfileId(siteRequest_, o);
		case "enrollmentChargeDate":
			return SchoolEnrollment.staticSolrFqEnrollmentChargeDate(siteRequest_, o);
		case "paymentLastDate":
			return SchoolEnrollment.staticSolrFqPaymentLastDate(siteRequest_, o);
		case "paymentLastStr":
			return SchoolEnrollment.staticSolrFqPaymentLastStr(siteRequest_, o);
		case "paymentAmount":
			return SchoolEnrollment.staticSolrFqPaymentAmount(siteRequest_, o);
		case "chargeAmount":
			return SchoolEnrollment.staticSolrFqChargeAmount(siteRequest_, o);
		case "chargeAmountFuture":
			return SchoolEnrollment.staticSolrFqChargeAmountFuture(siteRequest_, o);
		case "chargeAmountDue":
			return SchoolEnrollment.staticSolrFqChargeAmountDue(siteRequest_, o);
		case "chargeAmountNotPassed":
			return SchoolEnrollment.staticSolrFqChargeAmountNotPassed(siteRequest_, o);
		case "chargesNow":
			return SchoolEnrollment.staticSolrFqChargesNow(siteRequest_, o);
		case "paymentsCurrent":
			return SchoolEnrollment.staticSolrFqPaymentsCurrent(siteRequest_, o);
		case "paymentsLate":
			return SchoolEnrollment.staticSolrFqPaymentsLate(siteRequest_, o);
		case "paymentsLateAmount":
			return SchoolEnrollment.staticSolrFqPaymentsLateAmount(siteRequest_, o);
		case "paymentsAhead":
			return SchoolEnrollment.staticSolrFqPaymentsAhead(siteRequest_, o);
		case "paymentsPastDue":
			return SchoolEnrollment.staticSolrFqPaymentsPastDue(siteRequest_, o);
		case "paymentsPastDueAmount":
			return SchoolEnrollment.staticSolrFqPaymentsPastDueAmount(siteRequest_, o);
		case "chargesCreated":
			return SchoolEnrollment.staticSolrFqChargesCreated(siteRequest_, o);
		case "createdYear":
			return SchoolEnrollment.staticSolrFqCreatedYear(siteRequest_, o);
		case "createdDayOfWeek":
			return SchoolEnrollment.staticSolrFqCreatedDayOfWeek(siteRequest_, o);
		case "createdMonthOfYear":
			return SchoolEnrollment.staticSolrFqCreatedMonthOfYear(siteRequest_, o);
		case "createdHourOfDay":
			return SchoolEnrollment.staticSolrFqCreatedHourOfDay(siteRequest_, o);
		case "enrollmentDaysOfWeek":
			return SchoolEnrollment.staticSolrFqEnrollmentDaysOfWeek(siteRequest_, o);
		case "enrollmentParentNames":
			return SchoolEnrollment.staticSolrFqEnrollmentParentNames(siteRequest_, o);
		case "enrollmentEmails":
			return SchoolEnrollment.staticSolrFqEnrollmentEmails(siteRequest_, o);
		case "enrollmentEmail":
			return SchoolEnrollment.staticSolrFqEnrollmentEmail(siteRequest_, o);
		case "enrollmentParentEmails":
			return SchoolEnrollment.staticSolrFqEnrollmentParentEmails(siteRequest_, o);
		case "enrollmentPhoneNumbers":
			return SchoolEnrollment.staticSolrFqEnrollmentPhoneNumbers(siteRequest_, o);
		case "enrollmentPhoneNumber":
			return SchoolEnrollment.staticSolrFqEnrollmentPhoneNumber(siteRequest_, o);
		case "enrollmentParentName":
			return SchoolEnrollment.staticSolrFqEnrollmentParentName(siteRequest_, o);
		case "enrollmentParentNameLines":
			return SchoolEnrollment.staticSolrFqEnrollmentParentNameLines(siteRequest_, o);
		case "enrollmentParentEmailLines":
			return SchoolEnrollment.staticSolrFqEnrollmentParentEmailLines(siteRequest_, o);
		case "enrollmentParentDetailLines":
			return SchoolEnrollment.staticSolrFqEnrollmentParentDetailLines(siteRequest_, o);
		case "enrollmentPickupDetailLines":
			return SchoolEnrollment.staticSolrFqEnrollmentPickupDetailLines(siteRequest_, o);
		case "enrollmentEmergencyContactDetailLines":
			return SchoolEnrollment.staticSolrFqEnrollmentEmergencyContactDetailLines(siteRequest_, o);
		case "enrollmentSignature1":
			return SchoolEnrollment.staticSolrFqEnrollmentSignature1(siteRequest_, o);
		case "enrollmentSignature2":
			return SchoolEnrollment.staticSolrFqEnrollmentSignature2(siteRequest_, o);
		case "enrollmentSignature3":
			return SchoolEnrollment.staticSolrFqEnrollmentSignature3(siteRequest_, o);
		case "enrollmentSignature4":
			return SchoolEnrollment.staticSolrFqEnrollmentSignature4(siteRequest_, o);
		case "enrollmentSignature5":
			return SchoolEnrollment.staticSolrFqEnrollmentSignature5(siteRequest_, o);
		case "enrollmentSignature6":
			return SchoolEnrollment.staticSolrFqEnrollmentSignature6(siteRequest_, o);
		case "enrollmentSignature7":
			return SchoolEnrollment.staticSolrFqEnrollmentSignature7(siteRequest_, o);
		case "enrollmentSignature8":
			return SchoolEnrollment.staticSolrFqEnrollmentSignature8(siteRequest_, o);
		case "enrollmentSignature9":
			return SchoolEnrollment.staticSolrFqEnrollmentSignature9(siteRequest_, o);
		case "enrollmentSignature10":
			return SchoolEnrollment.staticSolrFqEnrollmentSignature10(siteRequest_, o);
		case "enrollmentDate1":
			return SchoolEnrollment.staticSolrFqEnrollmentDate1(siteRequest_, o);
		case "enrollmentDate2":
			return SchoolEnrollment.staticSolrFqEnrollmentDate2(siteRequest_, o);
		case "enrollmentDate3":
			return SchoolEnrollment.staticSolrFqEnrollmentDate3(siteRequest_, o);
		case "enrollmentDate4":
			return SchoolEnrollment.staticSolrFqEnrollmentDate4(siteRequest_, o);
		case "enrollmentDate5":
			return SchoolEnrollment.staticSolrFqEnrollmentDate5(siteRequest_, o);
		case "enrollmentDate6":
			return SchoolEnrollment.staticSolrFqEnrollmentDate6(siteRequest_, o);
		case "enrollmentDate7":
			return SchoolEnrollment.staticSolrFqEnrollmentDate7(siteRequest_, o);
		case "enrollmentDate8":
			return SchoolEnrollment.staticSolrFqEnrollmentDate8(siteRequest_, o);
		case "enrollmentDate9":
			return SchoolEnrollment.staticSolrFqEnrollmentDate9(siteRequest_, o);
		case "enrollmentDate10":
			return SchoolEnrollment.staticSolrFqEnrollmentDate10(siteRequest_, o);
		case "childImmunizationsReceived":
			return SchoolEnrollment.staticSolrFqChildImmunizationsReceived(siteRequest_, o);
		case "childPhotosApproved":
			return SchoolEnrollment.staticSolrFqChildPhotosApproved(siteRequest_, o);
		case "enrollmentNumber":
			return SchoolEnrollment.staticSolrFqEnrollmentNumber(siteRequest_, o);
		case "enrollmentCompleteName":
			return SchoolEnrollment.staticSolrFqEnrollmentCompleteName(siteRequest_, o);
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
					o = defineSchoolEnrollment(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolEnrollment(String var, String val) {
		switch(var) {
			case "childCompleteName":
				if(val != null)
					setChildCompleteName(val);
				saves.add(var);
				return val;
			case "childCompleteNamePreferred":
				if(val != null)
					setChildCompleteNamePreferred(val);
				saves.add(var);
				return val;
			case "childBirthDate":
				if(val != null)
					setChildBirthDate(val);
				saves.add(var);
				return val;
			case "schoolAddress":
				if(val != null)
					setSchoolAddress(val);
				saves.add(var);
				return val;
			case "enrollmentApproved":
				if(val != null)
					setEnrollmentApproved(val);
				saves.add(var);
				return val;
			case "enrollmentImmunizations":
				if(val != null)
					setEnrollmentImmunizations(val);
				saves.add(var);
				return val;
			case "photo":
				if(val != null)
					setPhoto(val);
				saves.add(var);
				return val;
			case "familyMarried":
				if(val != null)
					setFamilyMarried(val);
				saves.add(var);
				return val;
			case "familySeparated":
				if(val != null)
					setFamilySeparated(val);
				saves.add(var);
				return val;
			case "familyDivorced":
				if(val != null)
					setFamilyDivorced(val);
				saves.add(var);
				return val;
			case "familyAddress":
				if(val != null)
					setFamilyAddress(val);
				saves.add(var);
				return val;
			case "familyHowDoYouKnowTheSchool":
				if(val != null)
					setFamilyHowDoYouKnowTheSchool(val);
				saves.add(var);
				return val;
			case "enrollmentSpecialConsiderations":
				if(val != null)
					setEnrollmentSpecialConsiderations(val);
				saves.add(var);
				return val;
			case "childMedicalConditions":
				if(val != null)
					setChildMedicalConditions(val);
				saves.add(var);
				return val;
			case "childPreviousSchoolsAttended":
				if(val != null)
					setChildPreviousSchoolsAttended(val);
				saves.add(var);
				return val;
			case "childDescription":
				if(val != null)
					setChildDescription(val);
				saves.add(var);
				return val;
			case "childObjectives":
				if(val != null)
					setChildObjectives(val);
				saves.add(var);
				return val;
			case "childPottyTrained":
				if(val != null)
					setChildPottyTrained(val);
				saves.add(var);
				return val;
			case "enrollmentGroupName":
				if(val != null)
					setEnrollmentGroupName(val);
				saves.add(var);
				return val;
			case "enrollmentPaymentEachMonth":
				if(val != null)
					setEnrollmentPaymentEachMonth(val);
				saves.add(var);
				return val;
			case "enrollmentPaymentComplete":
				if(val != null)
					setEnrollmentPaymentComplete(val);
				saves.add(var);
				return val;
			case "customerProfileId":
				if(val != null)
					setCustomerProfileId(val);
				saves.add(var);
				return val;
			case "enrollmentChargeDate":
				if(val != null)
					setEnrollmentChargeDate(val);
				saves.add(var);
				return val;
			case "enrollmentParentNames":
				if(val != null)
					setEnrollmentParentNames(val);
				saves.add(var);
				return val;
			case "enrollmentSignature1":
				if(val != null)
					setEnrollmentSignature1(val);
				saves.add(var);
				return val;
			case "enrollmentSignature2":
				if(val != null)
					setEnrollmentSignature2(val);
				saves.add(var);
				return val;
			case "enrollmentSignature3":
				if(val != null)
					setEnrollmentSignature3(val);
				saves.add(var);
				return val;
			case "enrollmentSignature4":
				if(val != null)
					setEnrollmentSignature4(val);
				saves.add(var);
				return val;
			case "enrollmentSignature5":
				if(val != null)
					setEnrollmentSignature5(val);
				saves.add(var);
				return val;
			case "enrollmentSignature6":
				if(val != null)
					setEnrollmentSignature6(val);
				saves.add(var);
				return val;
			case "enrollmentSignature7":
				if(val != null)
					setEnrollmentSignature7(val);
				saves.add(var);
				return val;
			case "enrollmentSignature8":
				if(val != null)
					setEnrollmentSignature8(val);
				saves.add(var);
				return val;
			case "enrollmentSignature9":
				if(val != null)
					setEnrollmentSignature9(val);
				saves.add(var);
				return val;
			case "enrollmentSignature10":
				if(val != null)
					setEnrollmentSignature10(val);
				saves.add(var);
				return val;
			case "enrollmentDate1":
				if(val != null)
					setEnrollmentDate1(val);
				saves.add(var);
				return val;
			case "enrollmentDate2":
				if(val != null)
					setEnrollmentDate2(val);
				saves.add(var);
				return val;
			case "enrollmentDate3":
				if(val != null)
					setEnrollmentDate3(val);
				saves.add(var);
				return val;
			case "enrollmentDate4":
				if(val != null)
					setEnrollmentDate4(val);
				saves.add(var);
				return val;
			case "enrollmentDate5":
				if(val != null)
					setEnrollmentDate5(val);
				saves.add(var);
				return val;
			case "enrollmentDate6":
				if(val != null)
					setEnrollmentDate6(val);
				saves.add(var);
				return val;
			case "enrollmentDate7":
				if(val != null)
					setEnrollmentDate7(val);
				saves.add(var);
				return val;
			case "enrollmentDate8":
				if(val != null)
					setEnrollmentDate8(val);
				saves.add(var);
				return val;
			case "enrollmentDate9":
				if(val != null)
					setEnrollmentDate9(val);
				saves.add(var);
				return val;
			case "enrollmentDate10":
				if(val != null)
					setEnrollmentDate10(val);
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
		populateSchoolEnrollment(solrDocument);
	}
	public void populateSchoolEnrollment(SolrDocument solrDocument) {
		SchoolEnrollment oSchoolEnrollment = (SchoolEnrollment)this;
		saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null) {

			if(saves.contains("enrollmentKey")) {
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

			if(saves.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oSchoolEnrollment.setSchoolKey(schoolKey);
			}

			if(saves.contains("sessionKey")) {
				Long sessionKey = (Long)solrDocument.get("sessionKey_stored_long");
				if(sessionKey != null)
					oSchoolEnrollment.setSessionKey(sessionKey);
			}

			if(saves.contains("ageKey")) {
				Long ageKey = (Long)solrDocument.get("ageKey_stored_long");
				if(ageKey != null)
					oSchoolEnrollment.setAgeKey(ageKey);
			}

			if(saves.contains("blockKey")) {
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

			if(saves.contains("enrollmentFormKey")) {
				Long enrollmentFormKey = (Long)solrDocument.get("enrollmentFormKey_stored_long");
				if(enrollmentFormKey != null)
					oSchoolEnrollment.setEnrollmentFormKey(enrollmentFormKey);
			}

			List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
			if(userKeys != null)
				oSchoolEnrollment.userKeys.addAll(userKeys);

			if(saves.contains("educationSort")) {
				Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
				if(educationSort != null)
					oSchoolEnrollment.setEducationSort(educationSort);
			}

			if(saves.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolEnrollment.setSchoolSort(schoolSort);
			}

			if(saves.contains("yearSort")) {
				Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
				if(yearSort != null)
					oSchoolEnrollment.setYearSort(yearSort);
			}

			if(saves.contains("seasonSort")) {
				Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
				if(seasonSort != null)
					oSchoolEnrollment.setSeasonSort(seasonSort);
			}

			if(saves.contains("sessionSort")) {
				Integer sessionSort = (Integer)solrDocument.get("sessionSort_stored_int");
				if(sessionSort != null)
					oSchoolEnrollment.setSessionSort(sessionSort);
			}

			if(saves.contains("ageSort")) {
				Integer ageSort = (Integer)solrDocument.get("ageSort_stored_int");
				if(ageSort != null)
					oSchoolEnrollment.setAgeSort(ageSort);
			}

			if(saves.contains("childFirstName")) {
				String childFirstName = (String)solrDocument.get("childFirstName_stored_string");
				if(childFirstName != null)
					oSchoolEnrollment.setChildFirstName(childFirstName);
			}

			if(saves.contains("childFirstNamePreferred")) {
				String childFirstNamePreferred = (String)solrDocument.get("childFirstNamePreferred_stored_string");
				if(childFirstNamePreferred != null)
					oSchoolEnrollment.setChildFirstNamePreferred(childFirstNamePreferred);
			}

			if(saves.contains("childFamilyName")) {
				String childFamilyName = (String)solrDocument.get("childFamilyName_stored_string");
				if(childFamilyName != null)
					oSchoolEnrollment.setChildFamilyName(childFamilyName);
			}

			if(saves.contains("momFirstName")) {
				String momFirstName = (String)solrDocument.get("momFirstName_stored_string");
				if(momFirstName != null)
					oSchoolEnrollment.setMomFirstName(momFirstName);
			}

			if(saves.contains("momFirstNamePreferred")) {
				String momFirstNamePreferred = (String)solrDocument.get("momFirstNamePreferred_stored_string");
				if(momFirstNamePreferred != null)
					oSchoolEnrollment.setMomFirstNamePreferred(momFirstNamePreferred);
			}

			if(saves.contains("momCompleteNamePreferred")) {
				String momCompleteNamePreferred = (String)solrDocument.get("momCompleteNamePreferred_stored_string");
				if(momCompleteNamePreferred != null)
					oSchoolEnrollment.setMomCompleteNamePreferred(momCompleteNamePreferred);
			}

			if(saves.contains("dadFirstName")) {
				String dadFirstName = (String)solrDocument.get("dadFirstName_stored_string");
				if(dadFirstName != null)
					oSchoolEnrollment.setDadFirstName(dadFirstName);
			}

			if(saves.contains("dadFirstNamePreferred")) {
				String dadFirstNamePreferred = (String)solrDocument.get("dadFirstNamePreferred_stored_string");
				if(dadFirstNamePreferred != null)
					oSchoolEnrollment.setDadFirstNamePreferred(dadFirstNamePreferred);
			}

			if(saves.contains("dadCompleteNamePreferred")) {
				String dadCompleteNamePreferred = (String)solrDocument.get("dadCompleteNamePreferred_stored_string");
				if(dadCompleteNamePreferred != null)
					oSchoolEnrollment.setDadCompleteNamePreferred(dadCompleteNamePreferred);
			}

			if(saves.contains("childCompleteName")) {
				String childCompleteName = (String)solrDocument.get("childCompleteName_stored_string");
				if(childCompleteName != null)
					oSchoolEnrollment.setChildCompleteName(childCompleteName);
			}

			if(saves.contains("childCompleteNamePreferred")) {
				String childCompleteNamePreferred = (String)solrDocument.get("childCompleteNamePreferred_stored_string");
				if(childCompleteNamePreferred != null)
					oSchoolEnrollment.setChildCompleteNamePreferred(childCompleteNamePreferred);
			}

			if(saves.contains("childBirthDate")) {
				Date childBirthDate = (Date)solrDocument.get("childBirthDate_stored_date");
				if(childBirthDate != null)
					oSchoolEnrollment.setChildBirthDate(childBirthDate);
			}

			if(saves.contains("childBirthDateYear")) {
				Integer childBirthDateYear = (Integer)solrDocument.get("childBirthDateYear_stored_int");
				if(childBirthDateYear != null)
					oSchoolEnrollment.setChildBirthDateYear(childBirthDateYear);
			}

			if(saves.contains("childBirthDateMonthOfYear")) {
				String childBirthDateMonthOfYear = (String)solrDocument.get("childBirthDateMonthOfYear_stored_string");
				if(childBirthDateMonthOfYear != null)
					oSchoolEnrollment.setChildBirthDateMonthOfYear(childBirthDateMonthOfYear);
			}

			if(saves.contains("childBirthDateDayOfWeek")) {
				String childBirthDateDayOfWeek = (String)solrDocument.get("childBirthDateDayOfWeek_stored_string");
				if(childBirthDateDayOfWeek != null)
					oSchoolEnrollment.setChildBirthDateDayOfWeek(childBirthDateDayOfWeek);
			}

			if(saves.contains("childBirthMonth")) {
				Integer childBirthMonth = (Integer)solrDocument.get("childBirthMonth_stored_int");
				if(childBirthMonth != null)
					oSchoolEnrollment.setChildBirthMonth(childBirthMonth);
			}

			if(saves.contains("childBirthDay")) {
				Integer childBirthDay = (Integer)solrDocument.get("childBirthDay_stored_int");
				if(childBirthDay != null)
					oSchoolEnrollment.setChildBirthDay(childBirthDay);
			}

			if(saves.contains("schoolName")) {
				String schoolName = (String)solrDocument.get("schoolName_stored_string");
				if(schoolName != null)
					oSchoolEnrollment.setSchoolName(schoolName);
			}

			if(saves.contains("schoolCompleteName")) {
				String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
				if(schoolCompleteName != null)
					oSchoolEnrollment.setSchoolCompleteName(schoolCompleteName);
			}

			if(saves.contains("schoolLocation")) {
				String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
				if(schoolLocation != null)
					oSchoolEnrollment.setSchoolLocation(schoolLocation);
			}

			if(saves.contains("schoolAddress")) {
				String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
				if(schoolAddress != null)
					oSchoolEnrollment.setSchoolAddress(schoolAddress);
			}

			if(saves.contains("schoolPhoneNumber")) {
				String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
				if(schoolPhoneNumber != null)
					oSchoolEnrollment.setSchoolPhoneNumber(schoolPhoneNumber);
			}

			if(saves.contains("schoolForm")) {
				String schoolForm = (String)solrDocument.get("schoolForm_stored_string");
				if(schoolForm != null)
					oSchoolEnrollment.setSchoolForm(schoolForm);
			}

			if(saves.contains("schoolNumber")) {
				Integer schoolNumber = (Integer)solrDocument.get("schoolNumber_stored_int");
				if(schoolNumber != null)
					oSchoolEnrollment.setSchoolNumber(schoolNumber);
			}

			if(saves.contains("schoolAdministratorName")) {
				String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
				if(schoolAdministratorName != null)
					oSchoolEnrollment.setSchoolAdministratorName(schoolAdministratorName);
			}

			if(saves.contains("yearStart")) {
				Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
				if(yearStart != null)
					oSchoolEnrollment.setYearStart(yearStart);
			}

			if(saves.contains("yearEnd")) {
				Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
				if(yearEnd != null)
					oSchoolEnrollment.setYearEnd(yearEnd);
			}

			if(saves.contains("seasonStartDate")) {
				Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
				if(seasonStartDate != null)
					oSchoolEnrollment.setSeasonStartDate(seasonStartDate);
			}

			if(saves.contains("yearEnrollmentFee")) {
				Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
				if(yearEnrollmentFee != null)
					oSchoolEnrollment.setYearEnrollmentFee(yearEnrollmentFee);
			}

			if(saves.contains("sessionStartDate")) {
				Date sessionStartDate = (Date)solrDocument.get("sessionStartDate_stored_date");
				if(sessionStartDate != null)
					oSchoolEnrollment.setSessionStartDate(sessionStartDate);
			}

			if(saves.contains("sessionEndDate")) {
				Date sessionEndDate = (Date)solrDocument.get("sessionEndDate_stored_date");
				if(sessionEndDate != null)
					oSchoolEnrollment.setSessionEndDate(sessionEndDate);
			}

			if(saves.contains("ageCompleteName")) {
				String ageCompleteName = (String)solrDocument.get("ageCompleteName_stored_string");
				if(ageCompleteName != null)
					oSchoolEnrollment.setAgeCompleteName(ageCompleteName);
			}

			if(saves.contains("ageStart")) {
				Integer ageStart = (Integer)solrDocument.get("ageStart_stored_int");
				if(ageStart != null)
					oSchoolEnrollment.setAgeStart(ageStart);
			}

			if(saves.contains("ageEnd")) {
				Integer ageEnd = (Integer)solrDocument.get("ageEnd_stored_int");
				if(ageEnd != null)
					oSchoolEnrollment.setAgeEnd(ageEnd);
			}

			if(saves.contains("blockStartTime")) {
				String blockStartTime = (String)solrDocument.get("blockStartTime_stored_string");
				if(blockStartTime != null)
					oSchoolEnrollment.setBlockStartTime(blockStartTime);
			}

			if(saves.contains("blockEndTime")) {
				String blockEndTime = (String)solrDocument.get("blockEndTime_stored_string");
				if(blockEndTime != null)
					oSchoolEnrollment.setBlockEndTime(blockEndTime);
			}

			if(saves.contains("blockPricePerMonth")) {
				Double blockPricePerMonth = (Double)solrDocument.get("blockPricePerMonth_stored_double");
				if(blockPricePerMonth != null)
					oSchoolEnrollment.setBlockPricePerMonth(blockPricePerMonth);
			}

			if(saves.contains("blockSunday")) {
				Boolean blockSunday = (Boolean)solrDocument.get("blockSunday_stored_boolean");
				if(blockSunday != null)
					oSchoolEnrollment.setBlockSunday(blockSunday);
			}

			if(saves.contains("blockMonday")) {
				Boolean blockMonday = (Boolean)solrDocument.get("blockMonday_stored_boolean");
				if(blockMonday != null)
					oSchoolEnrollment.setBlockMonday(blockMonday);
			}

			if(saves.contains("blockTuesday")) {
				Boolean blockTuesday = (Boolean)solrDocument.get("blockTuesday_stored_boolean");
				if(blockTuesday != null)
					oSchoolEnrollment.setBlockTuesday(blockTuesday);
			}

			if(saves.contains("blockWednesday")) {
				Boolean blockWednesday = (Boolean)solrDocument.get("blockWednesday_stored_boolean");
				if(blockWednesday != null)
					oSchoolEnrollment.setBlockWednesday(blockWednesday);
			}

			if(saves.contains("blockThursday")) {
				Boolean blockThursday = (Boolean)solrDocument.get("blockThursday_stored_boolean");
				if(blockThursday != null)
					oSchoolEnrollment.setBlockThursday(blockThursday);
			}

			if(saves.contains("blockFriday")) {
				Boolean blockFriday = (Boolean)solrDocument.get("blockFriday_stored_boolean");
				if(blockFriday != null)
					oSchoolEnrollment.setBlockFriday(blockFriday);
			}

			if(saves.contains("blockSaturday")) {
				Boolean blockSaturday = (Boolean)solrDocument.get("blockSaturday_stored_boolean");
				if(blockSaturday != null)
					oSchoolEnrollment.setBlockSaturday(blockSaturday);
			}

			if(saves.contains("blockTotalPrice")) {
				Double blockTotalPrice = (Double)solrDocument.get("blockTotalPrice_stored_double");
				if(blockTotalPrice != null)
					oSchoolEnrollment.setBlockTotalPrice(blockTotalPrice);
			}

			if(saves.contains("blockAdminName")) {
				String blockAdminName = (String)solrDocument.get("blockAdminName_stored_string");
				if(blockAdminName != null)
					oSchoolEnrollment.setBlockAdminName(blockAdminName);
			}

			if(saves.contains("blockShortName")) {
				String blockShortName = (String)solrDocument.get("blockShortName_stored_string");
				if(blockShortName != null)
					oSchoolEnrollment.setBlockShortName(blockShortName);
			}

			if(saves.contains("blockCompleteName")) {
				String blockCompleteName = (String)solrDocument.get("blockCompleteName_stored_string");
				if(blockCompleteName != null)
					oSchoolEnrollment.setBlockCompleteName(blockCompleteName);
			}

			if(saves.contains("enrollmentApproved")) {
				Boolean enrollmentApproved = (Boolean)solrDocument.get("enrollmentApproved_stored_boolean");
				if(enrollmentApproved != null)
					oSchoolEnrollment.setEnrollmentApproved(enrollmentApproved);
			}

			if(saves.contains("enrollmentImmunizations")) {
				Boolean enrollmentImmunizations = (Boolean)solrDocument.get("enrollmentImmunizations_stored_boolean");
				if(enrollmentImmunizations != null)
					oSchoolEnrollment.setEnrollmentImmunizations(enrollmentImmunizations);
			}

			if(saves.contains("photo")) {
				String photo = (String)solrDocument.get("photo_stored_string");
				if(photo != null)
					oSchoolEnrollment.setPhoto(photo);
			}

			if(saves.contains("familyMarried")) {
				Boolean familyMarried = (Boolean)solrDocument.get("familyMarried_stored_boolean");
				if(familyMarried != null)
					oSchoolEnrollment.setFamilyMarried(familyMarried);
			}

			if(saves.contains("familySeparated")) {
				Boolean familySeparated = (Boolean)solrDocument.get("familySeparated_stored_boolean");
				if(familySeparated != null)
					oSchoolEnrollment.setFamilySeparated(familySeparated);
			}

			if(saves.contains("familyDivorced")) {
				Boolean familyDivorced = (Boolean)solrDocument.get("familyDivorced_stored_boolean");
				if(familyDivorced != null)
					oSchoolEnrollment.setFamilyDivorced(familyDivorced);
			}

			if(saves.contains("familyAddress")) {
				String familyAddress = (String)solrDocument.get("familyAddress_stored_string");
				if(familyAddress != null)
					oSchoolEnrollment.setFamilyAddress(familyAddress);
			}

			if(saves.contains("familyHowDoYouKnowTheSchool")) {
				String familyHowDoYouKnowTheSchool = (String)solrDocument.get("familyHowDoYouKnowTheSchool_stored_string");
				if(familyHowDoYouKnowTheSchool != null)
					oSchoolEnrollment.setFamilyHowDoYouKnowTheSchool(familyHowDoYouKnowTheSchool);
			}

			if(saves.contains("enrollmentSpecialConsiderations")) {
				String enrollmentSpecialConsiderations = (String)solrDocument.get("enrollmentSpecialConsiderations_stored_string");
				if(enrollmentSpecialConsiderations != null)
					oSchoolEnrollment.setEnrollmentSpecialConsiderations(enrollmentSpecialConsiderations);
			}

			if(saves.contains("childMedicalConditions")) {
				String childMedicalConditions = (String)solrDocument.get("childMedicalConditions_stored_string");
				if(childMedicalConditions != null)
					oSchoolEnrollment.setChildMedicalConditions(childMedicalConditions);
			}

			if(saves.contains("childPreviousSchoolsAttended")) {
				String childPreviousSchoolsAttended = (String)solrDocument.get("childPreviousSchoolsAttended_stored_string");
				if(childPreviousSchoolsAttended != null)
					oSchoolEnrollment.setChildPreviousSchoolsAttended(childPreviousSchoolsAttended);
			}

			if(saves.contains("childDescription")) {
				String childDescription = (String)solrDocument.get("childDescription_stored_string");
				if(childDescription != null)
					oSchoolEnrollment.setChildDescription(childDescription);
			}

			if(saves.contains("childObjectives")) {
				String childObjectives = (String)solrDocument.get("childObjectives_stored_string");
				if(childObjectives != null)
					oSchoolEnrollment.setChildObjectives(childObjectives);
			}

			if(saves.contains("childPottyTrained")) {
				Boolean childPottyTrained = (Boolean)solrDocument.get("childPottyTrained_stored_boolean");
				if(childPottyTrained != null)
					oSchoolEnrollment.setChildPottyTrained(childPottyTrained);
			}

			if(saves.contains("enrollmentGroupName")) {
				String enrollmentGroupName = (String)solrDocument.get("enrollmentGroupName_stored_string");
				if(enrollmentGroupName != null)
					oSchoolEnrollment.setEnrollmentGroupName(enrollmentGroupName);
			}

			if(saves.contains("enrollmentGroupColor")) {
				String enrollmentGroupColor = (String)solrDocument.get("enrollmentGroupColor_stored_string");
				if(enrollmentGroupColor != null)
					oSchoolEnrollment.setEnrollmentGroupColor(enrollmentGroupColor);
			}

			if(saves.contains("enrollmentPaymentEachMonth")) {
				Boolean enrollmentPaymentEachMonth = (Boolean)solrDocument.get("enrollmentPaymentEachMonth_stored_boolean");
				if(enrollmentPaymentEachMonth != null)
					oSchoolEnrollment.setEnrollmentPaymentEachMonth(enrollmentPaymentEachMonth);
			}

			if(saves.contains("enrollmentPaymentComplete")) {
				Boolean enrollmentPaymentComplete = (Boolean)solrDocument.get("enrollmentPaymentComplete_stored_boolean");
				if(enrollmentPaymentComplete != null)
					oSchoolEnrollment.setEnrollmentPaymentComplete(enrollmentPaymentComplete);
			}

			if(saves.contains("customerProfileId")) {
				String customerProfileId = (String)solrDocument.get("customerProfileId_stored_string");
				if(customerProfileId != null)
					oSchoolEnrollment.setCustomerProfileId(customerProfileId);
			}

			if(saves.contains("enrollmentChargeDate")) {
				Date enrollmentChargeDate = (Date)solrDocument.get("enrollmentChargeDate_stored_date");
				if(enrollmentChargeDate != null)
					oSchoolEnrollment.setEnrollmentChargeDate(enrollmentChargeDate);
			}

			if(saves.contains("paymentLastDate")) {
				Date paymentLastDate = (Date)solrDocument.get("paymentLastDate_stored_date");
				if(paymentLastDate != null)
					oSchoolEnrollment.setPaymentLastDate(paymentLastDate);
			}

			if(saves.contains("paymentLastStr")) {
				String paymentLastStr = (String)solrDocument.get("paymentLastStr_stored_string");
				if(paymentLastStr != null)
					oSchoolEnrollment.setPaymentLastStr(paymentLastStr);
			}

			if(saves.contains("paymentAmount")) {
				Double paymentAmount = (Double)solrDocument.get("paymentAmount_stored_double");
				if(paymentAmount != null)
					oSchoolEnrollment.setPaymentAmount(paymentAmount);
			}

			if(saves.contains("chargeAmount")) {
				Double chargeAmount = (Double)solrDocument.get("chargeAmount_stored_double");
				if(chargeAmount != null)
					oSchoolEnrollment.setChargeAmount(chargeAmount);
			}

			if(saves.contains("chargeAmountFuture")) {
				Double chargeAmountFuture = (Double)solrDocument.get("chargeAmountFuture_stored_double");
				if(chargeAmountFuture != null)
					oSchoolEnrollment.setChargeAmountFuture(chargeAmountFuture);
			}

			if(saves.contains("chargeAmountDue")) {
				Double chargeAmountDue = (Double)solrDocument.get("chargeAmountDue_stored_double");
				if(chargeAmountDue != null)
					oSchoolEnrollment.setChargeAmountDue(chargeAmountDue);
			}

			if(saves.contains("chargeAmountNotPassed")) {
				Double chargeAmountNotPassed = (Double)solrDocument.get("chargeAmountNotPassed_stored_double");
				if(chargeAmountNotPassed != null)
					oSchoolEnrollment.setChargeAmountNotPassed(chargeAmountNotPassed);
			}

			if(saves.contains("chargesNow")) {
				Double chargesNow = (Double)solrDocument.get("chargesNow_stored_double");
				if(chargesNow != null)
					oSchoolEnrollment.setChargesNow(chargesNow);
			}

			if(saves.contains("paymentsCurrent")) {
				Boolean paymentsCurrent = (Boolean)solrDocument.get("paymentsCurrent_stored_boolean");
				if(paymentsCurrent != null)
					oSchoolEnrollment.setPaymentsCurrent(paymentsCurrent);
			}

			if(saves.contains("paymentsLate")) {
				Boolean paymentsLate = (Boolean)solrDocument.get("paymentsLate_stored_boolean");
				if(paymentsLate != null)
					oSchoolEnrollment.setPaymentsLate(paymentsLate);
			}

			if(saves.contains("paymentsLateAmount")) {
				Double paymentsLateAmount = (Double)solrDocument.get("paymentsLateAmount_stored_double");
				if(paymentsLateAmount != null)
					oSchoolEnrollment.setPaymentsLateAmount(paymentsLateAmount);
			}

			if(saves.contains("paymentsAhead")) {
				Boolean paymentsAhead = (Boolean)solrDocument.get("paymentsAhead_stored_boolean");
				if(paymentsAhead != null)
					oSchoolEnrollment.setPaymentsAhead(paymentsAhead);
			}

			if(saves.contains("paymentsPastDue")) {
				Boolean paymentsPastDue = (Boolean)solrDocument.get("paymentsPastDue_stored_boolean");
				if(paymentsPastDue != null)
					oSchoolEnrollment.setPaymentsPastDue(paymentsPastDue);
			}

			if(saves.contains("paymentsPastDueAmount")) {
				Double paymentsPastDueAmount = (Double)solrDocument.get("paymentsPastDueAmount_stored_double");
				if(paymentsPastDueAmount != null)
					oSchoolEnrollment.setPaymentsPastDueAmount(paymentsPastDueAmount);
			}

			if(saves.contains("chargesCreated")) {
				Boolean chargesCreated = (Boolean)solrDocument.get("chargesCreated_stored_boolean");
				if(chargesCreated != null)
					oSchoolEnrollment.setChargesCreated(chargesCreated);
			}

			if(saves.contains("createdYear")) {
				Integer createdYear = (Integer)solrDocument.get("createdYear_stored_int");
				if(createdYear != null)
					oSchoolEnrollment.setCreatedYear(createdYear);
			}

			if(saves.contains("createdDayOfWeek")) {
				String createdDayOfWeek = (String)solrDocument.get("createdDayOfWeek_stored_string");
				if(createdDayOfWeek != null)
					oSchoolEnrollment.setCreatedDayOfWeek(createdDayOfWeek);
			}

			if(saves.contains("createdMonthOfYear")) {
				String createdMonthOfYear = (String)solrDocument.get("createdMonthOfYear_stored_string");
				if(createdMonthOfYear != null)
					oSchoolEnrollment.setCreatedMonthOfYear(createdMonthOfYear);
			}

			if(saves.contains("createdHourOfDay")) {
				String createdHourOfDay = (String)solrDocument.get("createdHourOfDay_stored_string");
				if(createdHourOfDay != null)
					oSchoolEnrollment.setCreatedHourOfDay(createdHourOfDay);
			}

			if(saves.contains("enrollmentDaysOfWeek")) {
				List<String> enrollmentDaysOfWeek = (List<String>)solrDocument.get("enrollmentDaysOfWeek_stored_strings");
				if(enrollmentDaysOfWeek != null)
					oSchoolEnrollment.enrollmentDaysOfWeek.addAll(enrollmentDaysOfWeek);
			}

			if(saves.contains("enrollmentParentNames")) {
				String enrollmentParentNames = (String)solrDocument.get("enrollmentParentNames_stored_string");
				if(enrollmentParentNames != null)
					oSchoolEnrollment.setEnrollmentParentNames(enrollmentParentNames);
			}

			if(saves.contains("enrollmentEmails")) {
				List<String> enrollmentEmails = (List<String>)solrDocument.get("enrollmentEmails_stored_strings");
				if(enrollmentEmails != null)
					oSchoolEnrollment.enrollmentEmails.addAll(enrollmentEmails);
			}

			if(saves.contains("enrollmentEmail")) {
				String enrollmentEmail = (String)solrDocument.get("enrollmentEmail_stored_string");
				if(enrollmentEmail != null)
					oSchoolEnrollment.setEnrollmentEmail(enrollmentEmail);
			}

			if(saves.contains("enrollmentParentEmails")) {
				String enrollmentParentEmails = (String)solrDocument.get("enrollmentParentEmails_stored_string");
				if(enrollmentParentEmails != null)
					oSchoolEnrollment.setEnrollmentParentEmails(enrollmentParentEmails);
			}

			if(saves.contains("enrollmentPhoneNumbers")) {
				List<String> enrollmentPhoneNumbers = (List<String>)solrDocument.get("enrollmentPhoneNumbers_stored_strings");
				if(enrollmentPhoneNumbers != null)
					oSchoolEnrollment.enrollmentPhoneNumbers.addAll(enrollmentPhoneNumbers);
			}

			if(saves.contains("enrollmentPhoneNumber")) {
				String enrollmentPhoneNumber = (String)solrDocument.get("enrollmentPhoneNumber_stored_string");
				if(enrollmentPhoneNumber != null)
					oSchoolEnrollment.setEnrollmentPhoneNumber(enrollmentPhoneNumber);
			}

			if(saves.contains("enrollmentParentName")) {
				String enrollmentParentName = (String)solrDocument.get("enrollmentParentName_stored_string");
				if(enrollmentParentName != null)
					oSchoolEnrollment.setEnrollmentParentName(enrollmentParentName);
			}

			if(saves.contains("enrollmentParentNameLines")) {
				String enrollmentParentNameLines = (String)solrDocument.get("enrollmentParentNameLines_stored_string");
				if(enrollmentParentNameLines != null)
					oSchoolEnrollment.setEnrollmentParentNameLines(enrollmentParentNameLines);
			}

			if(saves.contains("enrollmentParentEmailLines")) {
				String enrollmentParentEmailLines = (String)solrDocument.get("enrollmentParentEmailLines_stored_string");
				if(enrollmentParentEmailLines != null)
					oSchoolEnrollment.setEnrollmentParentEmailLines(enrollmentParentEmailLines);
			}

			if(saves.contains("enrollmentParentDetailLines")) {
				String enrollmentParentDetailLines = (String)solrDocument.get("enrollmentParentDetailLines_stored_string");
				if(enrollmentParentDetailLines != null)
					oSchoolEnrollment.setEnrollmentParentDetailLines(enrollmentParentDetailLines);
			}

			if(saves.contains("enrollmentPickupDetailLines")) {
				String enrollmentPickupDetailLines = (String)solrDocument.get("enrollmentPickupDetailLines_stored_string");
				if(enrollmentPickupDetailLines != null)
					oSchoolEnrollment.setEnrollmentPickupDetailLines(enrollmentPickupDetailLines);
			}

			if(saves.contains("enrollmentEmergencyContactDetailLines")) {
				String enrollmentEmergencyContactDetailLines = (String)solrDocument.get("enrollmentEmergencyContactDetailLines_stored_string");
				if(enrollmentEmergencyContactDetailLines != null)
					oSchoolEnrollment.setEnrollmentEmergencyContactDetailLines(enrollmentEmergencyContactDetailLines);
			}

			if(saves.contains("enrollmentSignature1")) {
				String enrollmentSignature1 = (String)solrDocument.get("enrollmentSignature1_stored_string");
				if(enrollmentSignature1 != null)
					oSchoolEnrollment.setEnrollmentSignature1(enrollmentSignature1);
			}

			if(saves.contains("enrollmentSignature2")) {
				String enrollmentSignature2 = (String)solrDocument.get("enrollmentSignature2_stored_string");
				if(enrollmentSignature2 != null)
					oSchoolEnrollment.setEnrollmentSignature2(enrollmentSignature2);
			}

			if(saves.contains("enrollmentSignature3")) {
				String enrollmentSignature3 = (String)solrDocument.get("enrollmentSignature3_stored_string");
				if(enrollmentSignature3 != null)
					oSchoolEnrollment.setEnrollmentSignature3(enrollmentSignature3);
			}

			if(saves.contains("enrollmentSignature4")) {
				String enrollmentSignature4 = (String)solrDocument.get("enrollmentSignature4_stored_string");
				if(enrollmentSignature4 != null)
					oSchoolEnrollment.setEnrollmentSignature4(enrollmentSignature4);
			}

			if(saves.contains("enrollmentSignature5")) {
				String enrollmentSignature5 = (String)solrDocument.get("enrollmentSignature5_stored_string");
				if(enrollmentSignature5 != null)
					oSchoolEnrollment.setEnrollmentSignature5(enrollmentSignature5);
			}

			if(saves.contains("enrollmentSignature6")) {
				String enrollmentSignature6 = (String)solrDocument.get("enrollmentSignature6_stored_string");
				if(enrollmentSignature6 != null)
					oSchoolEnrollment.setEnrollmentSignature6(enrollmentSignature6);
			}

			if(saves.contains("enrollmentSignature7")) {
				String enrollmentSignature7 = (String)solrDocument.get("enrollmentSignature7_stored_string");
				if(enrollmentSignature7 != null)
					oSchoolEnrollment.setEnrollmentSignature7(enrollmentSignature7);
			}

			if(saves.contains("enrollmentSignature8")) {
				String enrollmentSignature8 = (String)solrDocument.get("enrollmentSignature8_stored_string");
				if(enrollmentSignature8 != null)
					oSchoolEnrollment.setEnrollmentSignature8(enrollmentSignature8);
			}

			if(saves.contains("enrollmentSignature9")) {
				String enrollmentSignature9 = (String)solrDocument.get("enrollmentSignature9_stored_string");
				if(enrollmentSignature9 != null)
					oSchoolEnrollment.setEnrollmentSignature9(enrollmentSignature9);
			}

			if(saves.contains("enrollmentSignature10")) {
				String enrollmentSignature10 = (String)solrDocument.get("enrollmentSignature10_stored_string");
				if(enrollmentSignature10 != null)
					oSchoolEnrollment.setEnrollmentSignature10(enrollmentSignature10);
			}

			if(saves.contains("enrollmentDate1")) {
				Date enrollmentDate1 = (Date)solrDocument.get("enrollmentDate1_stored_date");
				if(enrollmentDate1 != null)
					oSchoolEnrollment.setEnrollmentDate1(enrollmentDate1);
			}

			if(saves.contains("enrollmentDate2")) {
				Date enrollmentDate2 = (Date)solrDocument.get("enrollmentDate2_stored_date");
				if(enrollmentDate2 != null)
					oSchoolEnrollment.setEnrollmentDate2(enrollmentDate2);
			}

			if(saves.contains("enrollmentDate3")) {
				Date enrollmentDate3 = (Date)solrDocument.get("enrollmentDate3_stored_date");
				if(enrollmentDate3 != null)
					oSchoolEnrollment.setEnrollmentDate3(enrollmentDate3);
			}

			if(saves.contains("enrollmentDate4")) {
				Date enrollmentDate4 = (Date)solrDocument.get("enrollmentDate4_stored_date");
				if(enrollmentDate4 != null)
					oSchoolEnrollment.setEnrollmentDate4(enrollmentDate4);
			}

			if(saves.contains("enrollmentDate5")) {
				Date enrollmentDate5 = (Date)solrDocument.get("enrollmentDate5_stored_date");
				if(enrollmentDate5 != null)
					oSchoolEnrollment.setEnrollmentDate5(enrollmentDate5);
			}

			if(saves.contains("enrollmentDate6")) {
				Date enrollmentDate6 = (Date)solrDocument.get("enrollmentDate6_stored_date");
				if(enrollmentDate6 != null)
					oSchoolEnrollment.setEnrollmentDate6(enrollmentDate6);
			}

			if(saves.contains("enrollmentDate7")) {
				Date enrollmentDate7 = (Date)solrDocument.get("enrollmentDate7_stored_date");
				if(enrollmentDate7 != null)
					oSchoolEnrollment.setEnrollmentDate7(enrollmentDate7);
			}

			if(saves.contains("enrollmentDate8")) {
				Date enrollmentDate8 = (Date)solrDocument.get("enrollmentDate8_stored_date");
				if(enrollmentDate8 != null)
					oSchoolEnrollment.setEnrollmentDate8(enrollmentDate8);
			}

			if(saves.contains("enrollmentDate9")) {
				Date enrollmentDate9 = (Date)solrDocument.get("enrollmentDate9_stored_date");
				if(enrollmentDate9 != null)
					oSchoolEnrollment.setEnrollmentDate9(enrollmentDate9);
			}

			if(saves.contains("enrollmentDate10")) {
				Date enrollmentDate10 = (Date)solrDocument.get("enrollmentDate10_stored_date");
				if(enrollmentDate10 != null)
					oSchoolEnrollment.setEnrollmentDate10(enrollmentDate10);
			}

			if(saves.contains("childImmunizationsReceived")) {
				String childImmunizationsReceived = (String)solrDocument.get("childImmunizationsReceived_stored_string");
				if(childImmunizationsReceived != null)
					oSchoolEnrollment.setChildImmunizationsReceived(childImmunizationsReceived);
			}

			if(saves.contains("childPhotosApproved")) {
				String childPhotosApproved = (String)solrDocument.get("childPhotosApproved_stored_string");
				if(childPhotosApproved != null)
					oSchoolEnrollment.setChildPhotosApproved(childPhotosApproved);
			}

			if(saves.contains("enrollmentCompleteName")) {
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
		if(userKeys != null) {
			for(java.lang.Long o : userKeys) {
				document.addField("userKeys_indexed_longs", o);
			}
			for(java.lang.Long o : userKeys) {
				document.addField("userKeys_stored_longs", o);
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
		if(childFirstName != null) {
			document.addField("childFirstName_indexed_string", childFirstName);
			document.addField("childFirstName_stored_string", childFirstName);
		}
		if(childFirstNamePreferred != null) {
			document.addField("childFirstNamePreferred_indexed_string", childFirstNamePreferred);
			document.addField("childFirstNamePreferred_stored_string", childFirstNamePreferred);
		}
		if(childFamilyName != null) {
			document.addField("childFamilyName_indexed_string", childFamilyName);
			document.addField("childFamilyName_stored_string", childFamilyName);
		}
		if(momFirstName != null) {
			document.addField("momFirstName_indexed_string", momFirstName);
			document.addField("momFirstName_stored_string", momFirstName);
		}
		if(momFirstNamePreferred != null) {
			document.addField("momFirstNamePreferred_indexed_string", momFirstNamePreferred);
			document.addField("momFirstNamePreferred_stored_string", momFirstNamePreferred);
		}
		if(momCompleteNamePreferred != null) {
			document.addField("momCompleteNamePreferred_indexed_string", momCompleteNamePreferred);
			document.addField("momCompleteNamePreferred_stored_string", momCompleteNamePreferred);
		}
		if(dadFirstName != null) {
			document.addField("dadFirstName_indexed_string", dadFirstName);
			document.addField("dadFirstName_stored_string", dadFirstName);
		}
		if(dadFirstNamePreferred != null) {
			document.addField("dadFirstNamePreferred_indexed_string", dadFirstNamePreferred);
			document.addField("dadFirstNamePreferred_stored_string", dadFirstNamePreferred);
		}
		if(dadCompleteNamePreferred != null) {
			document.addField("dadCompleteNamePreferred_indexed_string", dadCompleteNamePreferred);
			document.addField("dadCompleteNamePreferred_stored_string", dadCompleteNamePreferred);
		}
		if(childCompleteName != null) {
			document.addField("childCompleteName_indexed_string", childCompleteName);
			document.addField("childCompleteName_stored_string", childCompleteName);
		}
		if(childCompleteNamePreferred != null) {
			document.addField("childCompleteNamePreferred_indexed_string", childCompleteNamePreferred);
			document.addField("childCompleteNamePreferred_stored_string", childCompleteNamePreferred);
		}
		if(childBirthDate != null) {
			document.addField("childBirthDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(childBirthDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("childBirthDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(childBirthDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(childBirthDateYear != null) {
			document.addField("childBirthDateYear_indexed_int", childBirthDateYear);
			document.addField("childBirthDateYear_stored_int", childBirthDateYear);
		}
		if(childBirthDateMonthOfYear != null) {
			document.addField("childBirthDateMonthOfYear_indexed_string", childBirthDateMonthOfYear);
			document.addField("childBirthDateMonthOfYear_stored_string", childBirthDateMonthOfYear);
		}
		if(childBirthDateDayOfWeek != null) {
			document.addField("childBirthDateDayOfWeek_indexed_string", childBirthDateDayOfWeek);
			document.addField("childBirthDateDayOfWeek_stored_string", childBirthDateDayOfWeek);
		}
		if(childBirthMonth != null) {
			document.addField("childBirthMonth_indexed_int", childBirthMonth);
			document.addField("childBirthMonth_stored_int", childBirthMonth);
		}
		if(childBirthDay != null) {
			document.addField("childBirthDay_indexed_int", childBirthDay);
			document.addField("childBirthDay_stored_int", childBirthDay);
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
		if(yearStart != null) {
			document.addField("yearStart_indexed_int", yearStart);
			document.addField("yearStart_stored_int", yearStart);
		}
		if(yearEnd != null) {
			document.addField("yearEnd_indexed_int", yearEnd);
			document.addField("yearEnd_stored_int", yearEnd);
		}
		if(seasonStartDate != null) {
			document.addField("seasonStartDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("seasonStartDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(yearEnrollmentFee != null) {
			document.addField("yearEnrollmentFee_indexed_double", yearEnrollmentFee.doubleValue());
			document.addField("yearEnrollmentFee_stored_double", yearEnrollmentFee.doubleValue());
		}
		if(sessionStartDate != null) {
			document.addField("sessionStartDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionStartDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionEndDate != null) {
			document.addField("sessionEndDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionEndDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
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
			document.addField("blockStartTime_indexed_string", DateTimeFormatter.ofPattern("HH:mm").format(blockStartTime.atOffset(ZoneOffset.UTC)));
			document.addField("blockStartTime_stored_string", DateTimeFormatter.ofPattern("HH:mm").format(blockStartTime.atOffset(ZoneOffset.UTC)));
		}
		if(blockEndTime != null) {
			document.addField("blockEndTime_indexed_string", DateTimeFormatter.ofPattern("HH:mm").format(blockEndTime.atOffset(ZoneOffset.UTC)));
			document.addField("blockEndTime_stored_string", DateTimeFormatter.ofPattern("HH:mm").format(blockEndTime.atOffset(ZoneOffset.UTC)));
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
		if(photo != null) {
			document.addField("photo_stored_string", photo);
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
			document.addField("childMedicalConditions_text_enUS", childMedicalConditions.toString());
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
		if(enrollmentGroupColor != null) {
			document.addField("enrollmentGroupColor_indexed_string", enrollmentGroupColor);
			document.addField("enrollmentGroupColor_stored_string", enrollmentGroupColor);
		}
		if(enrollmentPaymentEachMonth != null) {
			document.addField("enrollmentPaymentEachMonth_indexed_boolean", enrollmentPaymentEachMonth);
			document.addField("enrollmentPaymentEachMonth_stored_boolean", enrollmentPaymentEachMonth);
		}
		if(enrollmentPaymentComplete != null) {
			document.addField("enrollmentPaymentComplete_indexed_boolean", enrollmentPaymentComplete);
			document.addField("enrollmentPaymentComplete_stored_boolean", enrollmentPaymentComplete);
		}
		if(customerProfileId != null) {
			document.addField("customerProfileId_indexed_string", customerProfileId);
			document.addField("customerProfileId_stored_string", customerProfileId);
		}
		if(enrollmentChargeDate != null) {
			document.addField("enrollmentChargeDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentChargeDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentChargeDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentChargeDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(paymentLastDate != null) {
			document.addField("paymentLastDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paymentLastDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("paymentLastDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paymentLastDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(paymentLastStr != null) {
			document.addField("paymentLastStr_indexed_string", paymentLastStr);
			document.addField("paymentLastStr_stored_string", paymentLastStr);
		}
		if(paymentAmount != null) {
			document.addField("paymentAmount_indexed_double", paymentAmount.doubleValue());
			document.addField("paymentAmount_stored_double", paymentAmount.doubleValue());
		}
		if(chargeAmount != null) {
			document.addField("chargeAmount_indexed_double", chargeAmount.doubleValue());
			document.addField("chargeAmount_stored_double", chargeAmount.doubleValue());
		}
		if(chargeAmountFuture != null) {
			document.addField("chargeAmountFuture_indexed_double", chargeAmountFuture.doubleValue());
			document.addField("chargeAmountFuture_stored_double", chargeAmountFuture.doubleValue());
		}
		if(chargeAmountDue != null) {
			document.addField("chargeAmountDue_indexed_double", chargeAmountDue.doubleValue());
			document.addField("chargeAmountDue_stored_double", chargeAmountDue.doubleValue());
		}
		if(chargeAmountNotPassed != null) {
			document.addField("chargeAmountNotPassed_indexed_double", chargeAmountNotPassed.doubleValue());
			document.addField("chargeAmountNotPassed_stored_double", chargeAmountNotPassed.doubleValue());
		}
		if(chargesNow != null) {
			document.addField("chargesNow_indexed_double", chargesNow.doubleValue());
			document.addField("chargesNow_stored_double", chargesNow.doubleValue());
		}
		if(paymentsCurrent != null) {
			document.addField("paymentsCurrent_indexed_boolean", paymentsCurrent);
			document.addField("paymentsCurrent_stored_boolean", paymentsCurrent);
		}
		if(paymentsLate != null) {
			document.addField("paymentsLate_indexed_boolean", paymentsLate);
			document.addField("paymentsLate_stored_boolean", paymentsLate);
		}
		if(paymentsLateAmount != null) {
			document.addField("paymentsLateAmount_indexed_double", paymentsLateAmount.doubleValue());
			document.addField("paymentsLateAmount_stored_double", paymentsLateAmount.doubleValue());
		}
		if(paymentsAhead != null) {
			document.addField("paymentsAhead_indexed_boolean", paymentsAhead);
			document.addField("paymentsAhead_stored_boolean", paymentsAhead);
		}
		if(paymentsPastDue != null) {
			document.addField("paymentsPastDue_indexed_boolean", paymentsPastDue);
			document.addField("paymentsPastDue_stored_boolean", paymentsPastDue);
		}
		if(paymentsPastDueAmount != null) {
			document.addField("paymentsPastDueAmount_indexed_double", paymentsPastDueAmount.doubleValue());
			document.addField("paymentsPastDueAmount_stored_double", paymentsPastDueAmount.doubleValue());
		}
		if(chargesCreated != null) {
			document.addField("chargesCreated_indexed_boolean", chargesCreated);
			document.addField("chargesCreated_stored_boolean", chargesCreated);
		}
		if(createdYear != null) {
			document.addField("createdYear_indexed_int", createdYear);
			document.addField("createdYear_stored_int", createdYear);
		}
		if(createdDayOfWeek != null) {
			document.addField("createdDayOfWeek_indexed_string", createdDayOfWeek);
			document.addField("createdDayOfWeek_stored_string", createdDayOfWeek);
		}
		if(createdMonthOfYear != null) {
			document.addField("createdMonthOfYear_indexed_string", createdMonthOfYear);
			document.addField("createdMonthOfYear_stored_string", createdMonthOfYear);
		}
		if(createdHourOfDay != null) {
			document.addField("createdHourOfDay_indexed_string", createdHourOfDay);
			document.addField("createdHourOfDay_stored_string", createdHourOfDay);
		}
		if(enrollmentDaysOfWeek != null) {
			for(java.lang.String o : enrollmentDaysOfWeek) {
				document.addField("enrollmentDaysOfWeek_indexed_strings", o);
			}
			for(java.lang.String o : enrollmentDaysOfWeek) {
				document.addField("enrollmentDaysOfWeek_stored_strings", o);
			}
		}
		if(enrollmentParentNames != null) {
			document.addField("enrollmentParentNames_stored_string", enrollmentParentNames);
		}
		if(enrollmentEmails != null) {
			for(java.lang.String o : enrollmentEmails) {
				document.addField("enrollmentEmails_indexed_strings", o);
			}
			for(java.lang.String o : enrollmentEmails) {
				document.addField("enrollmentEmails_stored_strings", o);
			}
		}
		if(enrollmentEmail != null) {
			document.addField("enrollmentEmail_indexed_string", enrollmentEmail);
			document.addField("enrollmentEmail_stored_string", enrollmentEmail);
		}
		if(enrollmentParentEmails != null) {
			document.addField("enrollmentParentEmails_stored_string", enrollmentParentEmails);
		}
		if(enrollmentPhoneNumbers != null) {
			for(java.lang.String o : enrollmentPhoneNumbers) {
				document.addField("enrollmentPhoneNumbers_indexed_strings", o);
			}
			for(java.lang.String o : enrollmentPhoneNumbers) {
				document.addField("enrollmentPhoneNumbers_stored_strings", o);
			}
		}
		if(enrollmentPhoneNumber != null) {
			document.addField("enrollmentPhoneNumber_indexed_string", enrollmentPhoneNumber);
			document.addField("enrollmentPhoneNumber_stored_string", enrollmentPhoneNumber);
		}
		if(enrollmentParentName != null) {
			document.addField("enrollmentParentName_indexed_string", enrollmentParentName);
			document.addField("enrollmentParentName_stored_string", enrollmentParentName);
		}
		if(enrollmentParentNameLines != null) {
			document.addField("enrollmentParentNameLines_stored_string", enrollmentParentNameLines);
		}
		if(enrollmentParentEmailLines != null) {
			document.addField("enrollmentParentEmailLines_stored_string", enrollmentParentEmailLines);
		}
		if(enrollmentParentDetailLines != null) {
			document.addField("enrollmentParentDetailLines_stored_string", enrollmentParentDetailLines);
		}
		if(enrollmentPickupDetailLines != null) {
			document.addField("enrollmentPickupDetailLines_stored_string", enrollmentPickupDetailLines);
		}
		if(enrollmentEmergencyContactDetailLines != null) {
			document.addField("enrollmentEmergencyContactDetailLines_stored_string", enrollmentEmergencyContactDetailLines);
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
			document.addField("enrollmentDate1_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate1.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate1_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate1.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate2 != null) {
			document.addField("enrollmentDate2_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate2.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate2_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate2.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate3 != null) {
			document.addField("enrollmentDate3_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate3.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate3_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate3.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate4 != null) {
			document.addField("enrollmentDate4_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate4.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate4_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate4.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate5 != null) {
			document.addField("enrollmentDate5_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate5.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate5_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate5.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate6 != null) {
			document.addField("enrollmentDate6_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate6.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate6_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate6.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate7 != null) {
			document.addField("enrollmentDate7_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate7.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate7_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate7.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate8 != null) {
			document.addField("enrollmentDate8_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate8.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate8_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate8.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate9 != null) {
			document.addField("enrollmentDate9_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate9.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate9_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate9.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enrollmentDate10 != null) {
			document.addField("enrollmentDate10_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate10.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enrollmentDate10_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enrollmentDate10.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(childImmunizationsReceived != null) {
			document.addField("childImmunizationsReceived_indexed_string", childImmunizationsReceived);
			document.addField("childImmunizationsReceived_stored_string", childImmunizationsReceived);
		}
		if(childPhotosApproved != null) {
			document.addField("childPhotosApproved_indexed_string", childPhotosApproved);
			document.addField("childPhotosApproved_stored_string", childPhotosApproved);
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
			case "userKeys":
				return "userKeys_indexed_longs";
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
			case "childFirstName":
				return "childFirstName_indexed_string";
			case "childFirstNamePreferred":
				return "childFirstNamePreferred_indexed_string";
			case "childFamilyName":
				return "childFamilyName_indexed_string";
			case "momFirstName":
				return "momFirstName_indexed_string";
			case "momFirstNamePreferred":
				return "momFirstNamePreferred_indexed_string";
			case "momCompleteNamePreferred":
				return "momCompleteNamePreferred_indexed_string";
			case "dadFirstName":
				return "dadFirstName_indexed_string";
			case "dadFirstNamePreferred":
				return "dadFirstNamePreferred_indexed_string";
			case "dadCompleteNamePreferred":
				return "dadCompleteNamePreferred_indexed_string";
			case "childCompleteName":
				return "childCompleteName_indexed_string";
			case "childCompleteNamePreferred":
				return "childCompleteNamePreferred_indexed_string";
			case "childBirthDate":
				return "childBirthDate_indexed_date";
			case "childBirthDateYear":
				return "childBirthDateYear_indexed_int";
			case "childBirthDateMonthOfYear":
				return "childBirthDateMonthOfYear_indexed_string";
			case "childBirthDateDayOfWeek":
				return "childBirthDateDayOfWeek_indexed_string";
			case "childBirthMonth":
				return "childBirthMonth_indexed_int";
			case "childBirthDay":
				return "childBirthDay_indexed_int";
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
			case "yearStart":
				return "yearStart_indexed_int";
			case "yearEnd":
				return "yearEnd_indexed_int";
			case "seasonStartDate":
				return "seasonStartDate_indexed_date";
			case "yearEnrollmentFee":
				return "yearEnrollmentFee_indexed_double";
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
			case "enrollmentGroupColor":
				return "enrollmentGroupColor_indexed_string";
			case "enrollmentPaymentEachMonth":
				return "enrollmentPaymentEachMonth_indexed_boolean";
			case "enrollmentPaymentComplete":
				return "enrollmentPaymentComplete_indexed_boolean";
			case "customerProfileId":
				return "customerProfileId_indexed_string";
			case "enrollmentChargeDate":
				return "enrollmentChargeDate_indexed_date";
			case "paymentLastDate":
				return "paymentLastDate_indexed_date";
			case "paymentLastStr":
				return "paymentLastStr_indexed_string";
			case "paymentAmount":
				return "paymentAmount_indexed_double";
			case "chargeAmount":
				return "chargeAmount_indexed_double";
			case "chargeAmountFuture":
				return "chargeAmountFuture_indexed_double";
			case "chargeAmountDue":
				return "chargeAmountDue_indexed_double";
			case "chargeAmountNotPassed":
				return "chargeAmountNotPassed_indexed_double";
			case "chargesNow":
				return "chargesNow_indexed_double";
			case "paymentsCurrent":
				return "paymentsCurrent_indexed_boolean";
			case "paymentsLate":
				return "paymentsLate_indexed_boolean";
			case "paymentsLateAmount":
				return "paymentsLateAmount_indexed_double";
			case "paymentsAhead":
				return "paymentsAhead_indexed_boolean";
			case "paymentsPastDue":
				return "paymentsPastDue_indexed_boolean";
			case "paymentsPastDueAmount":
				return "paymentsPastDueAmount_indexed_double";
			case "chargesCreated":
				return "chargesCreated_indexed_boolean";
			case "createdYear":
				return "createdYear_indexed_int";
			case "createdDayOfWeek":
				return "createdDayOfWeek_indexed_string";
			case "createdMonthOfYear":
				return "createdMonthOfYear_indexed_string";
			case "createdHourOfDay":
				return "createdHourOfDay_indexed_string";
			case "enrollmentDaysOfWeek":
				return "enrollmentDaysOfWeek_indexed_strings";
			case "enrollmentEmails":
				return "enrollmentEmails_indexed_strings";
			case "enrollmentEmail":
				return "enrollmentEmail_indexed_string";
			case "enrollmentPhoneNumbers":
				return "enrollmentPhoneNumbers_indexed_strings";
			case "enrollmentPhoneNumber":
				return "enrollmentPhoneNumber_indexed_string";
			case "enrollmentParentName":
				return "enrollmentParentName_indexed_string";
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
			case "childImmunizationsReceived":
				return "childImmunizationsReceived_indexed_string";
			case "childPhotosApproved":
				return "childPhotosApproved_indexed_string";
			case "enrollmentCompleteName":
				return "enrollmentCompleteName_indexed_string";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchSchoolEnrollment(String entityVar) {
		switch(entityVar) {
			case "childMedicalConditions":
				return "childMedicalConditions_text_enUS";
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestedSchoolEnrollment(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestedCluster(entityVar);
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

		List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
		if(userKeys != null)
			oSchoolEnrollment.userKeys.addAll(userKeys);

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

		String childFirstName = (String)solrDocument.get("childFirstName_stored_string");
		if(childFirstName != null)
			oSchoolEnrollment.setChildFirstName(childFirstName);

		String childFirstNamePreferred = (String)solrDocument.get("childFirstNamePreferred_stored_string");
		if(childFirstNamePreferred != null)
			oSchoolEnrollment.setChildFirstNamePreferred(childFirstNamePreferred);

		String childFamilyName = (String)solrDocument.get("childFamilyName_stored_string");
		if(childFamilyName != null)
			oSchoolEnrollment.setChildFamilyName(childFamilyName);

		String momFirstName = (String)solrDocument.get("momFirstName_stored_string");
		if(momFirstName != null)
			oSchoolEnrollment.setMomFirstName(momFirstName);

		String momFirstNamePreferred = (String)solrDocument.get("momFirstNamePreferred_stored_string");
		if(momFirstNamePreferred != null)
			oSchoolEnrollment.setMomFirstNamePreferred(momFirstNamePreferred);

		String momCompleteNamePreferred = (String)solrDocument.get("momCompleteNamePreferred_stored_string");
		if(momCompleteNamePreferred != null)
			oSchoolEnrollment.setMomCompleteNamePreferred(momCompleteNamePreferred);

		String dadFirstName = (String)solrDocument.get("dadFirstName_stored_string");
		if(dadFirstName != null)
			oSchoolEnrollment.setDadFirstName(dadFirstName);

		String dadFirstNamePreferred = (String)solrDocument.get("dadFirstNamePreferred_stored_string");
		if(dadFirstNamePreferred != null)
			oSchoolEnrollment.setDadFirstNamePreferred(dadFirstNamePreferred);

		String dadCompleteNamePreferred = (String)solrDocument.get("dadCompleteNamePreferred_stored_string");
		if(dadCompleteNamePreferred != null)
			oSchoolEnrollment.setDadCompleteNamePreferred(dadCompleteNamePreferred);

		String childCompleteName = (String)solrDocument.get("childCompleteName_stored_string");
		if(childCompleteName != null)
			oSchoolEnrollment.setChildCompleteName(childCompleteName);

		String childCompleteNamePreferred = (String)solrDocument.get("childCompleteNamePreferred_stored_string");
		if(childCompleteNamePreferred != null)
			oSchoolEnrollment.setChildCompleteNamePreferred(childCompleteNamePreferred);

		Date childBirthDate = (Date)solrDocument.get("childBirthDate_stored_date");
		if(childBirthDate != null)
			oSchoolEnrollment.setChildBirthDate(childBirthDate);

		Integer childBirthDateYear = (Integer)solrDocument.get("childBirthDateYear_stored_int");
		if(childBirthDateYear != null)
			oSchoolEnrollment.setChildBirthDateYear(childBirthDateYear);

		String childBirthDateMonthOfYear = (String)solrDocument.get("childBirthDateMonthOfYear_stored_string");
		if(childBirthDateMonthOfYear != null)
			oSchoolEnrollment.setChildBirthDateMonthOfYear(childBirthDateMonthOfYear);

		String childBirthDateDayOfWeek = (String)solrDocument.get("childBirthDateDayOfWeek_stored_string");
		if(childBirthDateDayOfWeek != null)
			oSchoolEnrollment.setChildBirthDateDayOfWeek(childBirthDateDayOfWeek);

		Integer childBirthMonth = (Integer)solrDocument.get("childBirthMonth_stored_int");
		if(childBirthMonth != null)
			oSchoolEnrollment.setChildBirthMonth(childBirthMonth);

		Integer childBirthDay = (Integer)solrDocument.get("childBirthDay_stored_int");
		if(childBirthDay != null)
			oSchoolEnrollment.setChildBirthDay(childBirthDay);

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

		String schoolForm = (String)solrDocument.get("schoolForm_stored_string");
		if(schoolForm != null)
			oSchoolEnrollment.setSchoolForm(schoolForm);

		Integer schoolNumber = (Integer)solrDocument.get("schoolNumber_stored_int");
		if(schoolNumber != null)
			oSchoolEnrollment.setSchoolNumber(schoolNumber);

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

		Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
		if(yearEnrollmentFee != null)
			oSchoolEnrollment.setYearEnrollmentFee(yearEnrollmentFee);

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

		String photo = (String)solrDocument.get("photo_stored_string");
		if(photo != null)
			oSchoolEnrollment.setPhoto(photo);

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

		String enrollmentGroupColor = (String)solrDocument.get("enrollmentGroupColor_stored_string");
		if(enrollmentGroupColor != null)
			oSchoolEnrollment.setEnrollmentGroupColor(enrollmentGroupColor);

		Boolean enrollmentPaymentEachMonth = (Boolean)solrDocument.get("enrollmentPaymentEachMonth_stored_boolean");
		if(enrollmentPaymentEachMonth != null)
			oSchoolEnrollment.setEnrollmentPaymentEachMonth(enrollmentPaymentEachMonth);

		Boolean enrollmentPaymentComplete = (Boolean)solrDocument.get("enrollmentPaymentComplete_stored_boolean");
		if(enrollmentPaymentComplete != null)
			oSchoolEnrollment.setEnrollmentPaymentComplete(enrollmentPaymentComplete);

		String customerProfileId = (String)solrDocument.get("customerProfileId_stored_string");
		if(customerProfileId != null)
			oSchoolEnrollment.setCustomerProfileId(customerProfileId);

		Date enrollmentChargeDate = (Date)solrDocument.get("enrollmentChargeDate_stored_date");
		if(enrollmentChargeDate != null)
			oSchoolEnrollment.setEnrollmentChargeDate(enrollmentChargeDate);

		Date paymentLastDate = (Date)solrDocument.get("paymentLastDate_stored_date");
		if(paymentLastDate != null)
			oSchoolEnrollment.setPaymentLastDate(paymentLastDate);

		String paymentLastStr = (String)solrDocument.get("paymentLastStr_stored_string");
		if(paymentLastStr != null)
			oSchoolEnrollment.setPaymentLastStr(paymentLastStr);

		Double paymentAmount = (Double)solrDocument.get("paymentAmount_stored_double");
		if(paymentAmount != null)
			oSchoolEnrollment.setPaymentAmount(paymentAmount);

		Double chargeAmount = (Double)solrDocument.get("chargeAmount_stored_double");
		if(chargeAmount != null)
			oSchoolEnrollment.setChargeAmount(chargeAmount);

		Double chargeAmountFuture = (Double)solrDocument.get("chargeAmountFuture_stored_double");
		if(chargeAmountFuture != null)
			oSchoolEnrollment.setChargeAmountFuture(chargeAmountFuture);

		Double chargeAmountDue = (Double)solrDocument.get("chargeAmountDue_stored_double");
		if(chargeAmountDue != null)
			oSchoolEnrollment.setChargeAmountDue(chargeAmountDue);

		Double chargeAmountNotPassed = (Double)solrDocument.get("chargeAmountNotPassed_stored_double");
		if(chargeAmountNotPassed != null)
			oSchoolEnrollment.setChargeAmountNotPassed(chargeAmountNotPassed);

		Double chargesNow = (Double)solrDocument.get("chargesNow_stored_double");
		if(chargesNow != null)
			oSchoolEnrollment.setChargesNow(chargesNow);

		Boolean paymentsCurrent = (Boolean)solrDocument.get("paymentsCurrent_stored_boolean");
		if(paymentsCurrent != null)
			oSchoolEnrollment.setPaymentsCurrent(paymentsCurrent);

		Boolean paymentsLate = (Boolean)solrDocument.get("paymentsLate_stored_boolean");
		if(paymentsLate != null)
			oSchoolEnrollment.setPaymentsLate(paymentsLate);

		Double paymentsLateAmount = (Double)solrDocument.get("paymentsLateAmount_stored_double");
		if(paymentsLateAmount != null)
			oSchoolEnrollment.setPaymentsLateAmount(paymentsLateAmount);

		Boolean paymentsAhead = (Boolean)solrDocument.get("paymentsAhead_stored_boolean");
		if(paymentsAhead != null)
			oSchoolEnrollment.setPaymentsAhead(paymentsAhead);

		Boolean paymentsPastDue = (Boolean)solrDocument.get("paymentsPastDue_stored_boolean");
		if(paymentsPastDue != null)
			oSchoolEnrollment.setPaymentsPastDue(paymentsPastDue);

		Double paymentsPastDueAmount = (Double)solrDocument.get("paymentsPastDueAmount_stored_double");
		if(paymentsPastDueAmount != null)
			oSchoolEnrollment.setPaymentsPastDueAmount(paymentsPastDueAmount);

		Boolean chargesCreated = (Boolean)solrDocument.get("chargesCreated_stored_boolean");
		if(chargesCreated != null)
			oSchoolEnrollment.setChargesCreated(chargesCreated);

		Integer createdYear = (Integer)solrDocument.get("createdYear_stored_int");
		if(createdYear != null)
			oSchoolEnrollment.setCreatedYear(createdYear);

		String createdDayOfWeek = (String)solrDocument.get("createdDayOfWeek_stored_string");
		if(createdDayOfWeek != null)
			oSchoolEnrollment.setCreatedDayOfWeek(createdDayOfWeek);

		String createdMonthOfYear = (String)solrDocument.get("createdMonthOfYear_stored_string");
		if(createdMonthOfYear != null)
			oSchoolEnrollment.setCreatedMonthOfYear(createdMonthOfYear);

		String createdHourOfDay = (String)solrDocument.get("createdHourOfDay_stored_string");
		if(createdHourOfDay != null)
			oSchoolEnrollment.setCreatedHourOfDay(createdHourOfDay);

		List<String> enrollmentDaysOfWeek = (List<String>)solrDocument.get("enrollmentDaysOfWeek_stored_strings");
		if(enrollmentDaysOfWeek != null)
			oSchoolEnrollment.enrollmentDaysOfWeek.addAll(enrollmentDaysOfWeek);

		String enrollmentParentNames = (String)solrDocument.get("enrollmentParentNames_stored_string");
		if(enrollmentParentNames != null)
			oSchoolEnrollment.setEnrollmentParentNames(enrollmentParentNames);

		List<String> enrollmentEmails = (List<String>)solrDocument.get("enrollmentEmails_stored_strings");
		if(enrollmentEmails != null)
			oSchoolEnrollment.enrollmentEmails.addAll(enrollmentEmails);

		String enrollmentEmail = (String)solrDocument.get("enrollmentEmail_stored_string");
		if(enrollmentEmail != null)
			oSchoolEnrollment.setEnrollmentEmail(enrollmentEmail);

		String enrollmentParentEmails = (String)solrDocument.get("enrollmentParentEmails_stored_string");
		if(enrollmentParentEmails != null)
			oSchoolEnrollment.setEnrollmentParentEmails(enrollmentParentEmails);

		List<String> enrollmentPhoneNumbers = (List<String>)solrDocument.get("enrollmentPhoneNumbers_stored_strings");
		if(enrollmentPhoneNumbers != null)
			oSchoolEnrollment.enrollmentPhoneNumbers.addAll(enrollmentPhoneNumbers);

		String enrollmentPhoneNumber = (String)solrDocument.get("enrollmentPhoneNumber_stored_string");
		if(enrollmentPhoneNumber != null)
			oSchoolEnrollment.setEnrollmentPhoneNumber(enrollmentPhoneNumber);

		String enrollmentParentName = (String)solrDocument.get("enrollmentParentName_stored_string");
		if(enrollmentParentName != null)
			oSchoolEnrollment.setEnrollmentParentName(enrollmentParentName);

		String enrollmentParentNameLines = (String)solrDocument.get("enrollmentParentNameLines_stored_string");
		if(enrollmentParentNameLines != null)
			oSchoolEnrollment.setEnrollmentParentNameLines(enrollmentParentNameLines);

		String enrollmentParentEmailLines = (String)solrDocument.get("enrollmentParentEmailLines_stored_string");
		if(enrollmentParentEmailLines != null)
			oSchoolEnrollment.setEnrollmentParentEmailLines(enrollmentParentEmailLines);

		String enrollmentParentDetailLines = (String)solrDocument.get("enrollmentParentDetailLines_stored_string");
		if(enrollmentParentDetailLines != null)
			oSchoolEnrollment.setEnrollmentParentDetailLines(enrollmentParentDetailLines);

		String enrollmentPickupDetailLines = (String)solrDocument.get("enrollmentPickupDetailLines_stored_string");
		if(enrollmentPickupDetailLines != null)
			oSchoolEnrollment.setEnrollmentPickupDetailLines(enrollmentPickupDetailLines);

		String enrollmentEmergencyContactDetailLines = (String)solrDocument.get("enrollmentEmergencyContactDetailLines_stored_string");
		if(enrollmentEmergencyContactDetailLines != null)
			oSchoolEnrollment.setEnrollmentEmergencyContactDetailLines(enrollmentEmergencyContactDetailLines);

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

		String childImmunizationsReceived = (String)solrDocument.get("childImmunizationsReceived_stored_string");
		if(childImmunizationsReceived != null)
			oSchoolEnrollment.setChildImmunizationsReceived(childImmunizationsReceived);

		String childPhotosApproved = (String)solrDocument.get("childPhotosApproved_stored_string");
		if(childPhotosApproved != null)
			oSchoolEnrollment.setChildPhotosApproved(childPhotosApproved);

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
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SchoolEnrollment) {
			SchoolEnrollment original = (SchoolEnrollment)o;
			if(!Objects.equals(enrollmentKey, original.getEnrollmentKey()))
				apiRequest.addVars("enrollmentKey");
			if(!Objects.equals(yearKey, original.getYearKey()))
				apiRequest.addVars("yearKey");
			if(!Objects.equals(blockKeys, original.getBlockKeys()))
				apiRequest.addVars("blockKeys");
			if(!Objects.equals(schoolKey, original.getSchoolKey()))
				apiRequest.addVars("schoolKey");
			if(!Objects.equals(sessionKey, original.getSessionKey()))
				apiRequest.addVars("sessionKey");
			if(!Objects.equals(ageKey, original.getAgeKey()))
				apiRequest.addVars("ageKey");
			if(!Objects.equals(blockKey, original.getBlockKey()))
				apiRequest.addVars("blockKey");
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
			if(!Objects.equals(enrollmentFormKey, original.getEnrollmentFormKey()))
				apiRequest.addVars("enrollmentFormKey");
			if(!Objects.equals(userKeys, original.getUserKeys()))
				apiRequest.addVars("userKeys");
			if(!Objects.equals(educationSort, original.getEducationSort()))
				apiRequest.addVars("educationSort");
			if(!Objects.equals(schoolSort, original.getSchoolSort()))
				apiRequest.addVars("schoolSort");
			if(!Objects.equals(yearSort, original.getYearSort()))
				apiRequest.addVars("yearSort");
			if(!Objects.equals(seasonSort, original.getSeasonSort()))
				apiRequest.addVars("seasonSort");
			if(!Objects.equals(sessionSort, original.getSessionSort()))
				apiRequest.addVars("sessionSort");
			if(!Objects.equals(ageSort, original.getAgeSort()))
				apiRequest.addVars("ageSort");
			if(!Objects.equals(childFirstName, original.getChildFirstName()))
				apiRequest.addVars("childFirstName");
			if(!Objects.equals(childFirstNamePreferred, original.getChildFirstNamePreferred()))
				apiRequest.addVars("childFirstNamePreferred");
			if(!Objects.equals(childFamilyName, original.getChildFamilyName()))
				apiRequest.addVars("childFamilyName");
			if(!Objects.equals(momFirstName, original.getMomFirstName()))
				apiRequest.addVars("momFirstName");
			if(!Objects.equals(momFirstNamePreferred, original.getMomFirstNamePreferred()))
				apiRequest.addVars("momFirstNamePreferred");
			if(!Objects.equals(momCompleteNamePreferred, original.getMomCompleteNamePreferred()))
				apiRequest.addVars("momCompleteNamePreferred");
			if(!Objects.equals(dadFirstName, original.getDadFirstName()))
				apiRequest.addVars("dadFirstName");
			if(!Objects.equals(dadFirstNamePreferred, original.getDadFirstNamePreferred()))
				apiRequest.addVars("dadFirstNamePreferred");
			if(!Objects.equals(dadCompleteNamePreferred, original.getDadCompleteNamePreferred()))
				apiRequest.addVars("dadCompleteNamePreferred");
			if(!Objects.equals(childCompleteName, original.getChildCompleteName()))
				apiRequest.addVars("childCompleteName");
			if(!Objects.equals(childCompleteNamePreferred, original.getChildCompleteNamePreferred()))
				apiRequest.addVars("childCompleteNamePreferred");
			if(!Objects.equals(childBirthDate, original.getChildBirthDate()))
				apiRequest.addVars("childBirthDate");
			if(!Objects.equals(childBirthDateYear, original.getChildBirthDateYear()))
				apiRequest.addVars("childBirthDateYear");
			if(!Objects.equals(childBirthDateMonthOfYear, original.getChildBirthDateMonthOfYear()))
				apiRequest.addVars("childBirthDateMonthOfYear");
			if(!Objects.equals(childBirthDateDayOfWeek, original.getChildBirthDateDayOfWeek()))
				apiRequest.addVars("childBirthDateDayOfWeek");
			if(!Objects.equals(childBirthMonth, original.getChildBirthMonth()))
				apiRequest.addVars("childBirthMonth");
			if(!Objects.equals(childBirthDay, original.getChildBirthDay()))
				apiRequest.addVars("childBirthDay");
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
			if(!Objects.equals(yearStart, original.getYearStart()))
				apiRequest.addVars("yearStart");
			if(!Objects.equals(yearEnd, original.getYearEnd()))
				apiRequest.addVars("yearEnd");
			if(!Objects.equals(seasonStartDate, original.getSeasonStartDate()))
				apiRequest.addVars("seasonStartDate");
			if(!Objects.equals(yearEnrollmentFee, original.getYearEnrollmentFee()))
				apiRequest.addVars("yearEnrollmentFee");
			if(!Objects.equals(sessionStartDate, original.getSessionStartDate()))
				apiRequest.addVars("sessionStartDate");
			if(!Objects.equals(sessionEndDate, original.getSessionEndDate()))
				apiRequest.addVars("sessionEndDate");
			if(!Objects.equals(ageCompleteName, original.getAgeCompleteName()))
				apiRequest.addVars("ageCompleteName");
			if(!Objects.equals(ageStart, original.getAgeStart()))
				apiRequest.addVars("ageStart");
			if(!Objects.equals(ageEnd, original.getAgeEnd()))
				apiRequest.addVars("ageEnd");
			if(!Objects.equals(blockStartTime, original.getBlockStartTime()))
				apiRequest.addVars("blockStartTime");
			if(!Objects.equals(blockEndTime, original.getBlockEndTime()))
				apiRequest.addVars("blockEndTime");
			if(!Objects.equals(blockPricePerMonth, original.getBlockPricePerMonth()))
				apiRequest.addVars("blockPricePerMonth");
			if(!Objects.equals(blockSunday, original.getBlockSunday()))
				apiRequest.addVars("blockSunday");
			if(!Objects.equals(blockMonday, original.getBlockMonday()))
				apiRequest.addVars("blockMonday");
			if(!Objects.equals(blockTuesday, original.getBlockTuesday()))
				apiRequest.addVars("blockTuesday");
			if(!Objects.equals(blockWednesday, original.getBlockWednesday()))
				apiRequest.addVars("blockWednesday");
			if(!Objects.equals(blockThursday, original.getBlockThursday()))
				apiRequest.addVars("blockThursday");
			if(!Objects.equals(blockFriday, original.getBlockFriday()))
				apiRequest.addVars("blockFriday");
			if(!Objects.equals(blockSaturday, original.getBlockSaturday()))
				apiRequest.addVars("blockSaturday");
			if(!Objects.equals(blockTotalPrice, original.getBlockTotalPrice()))
				apiRequest.addVars("blockTotalPrice");
			if(!Objects.equals(blockAdminName, original.getBlockAdminName()))
				apiRequest.addVars("blockAdminName");
			if(!Objects.equals(blockShortName, original.getBlockShortName()))
				apiRequest.addVars("blockShortName");
			if(!Objects.equals(blockCompleteName, original.getBlockCompleteName()))
				apiRequest.addVars("blockCompleteName");
			if(!Objects.equals(enrollmentApproved, original.getEnrollmentApproved()))
				apiRequest.addVars("enrollmentApproved");
			if(!Objects.equals(enrollmentImmunizations, original.getEnrollmentImmunizations()))
				apiRequest.addVars("enrollmentImmunizations");
			if(!Objects.equals(photo, original.getPhoto()))
				apiRequest.addVars("photo");
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
			if(!Objects.equals(enrollmentGroupColor, original.getEnrollmentGroupColor()))
				apiRequest.addVars("enrollmentGroupColor");
			if(!Objects.equals(enrollmentPaymentEachMonth, original.getEnrollmentPaymentEachMonth()))
				apiRequest.addVars("enrollmentPaymentEachMonth");
			if(!Objects.equals(enrollmentPaymentComplete, original.getEnrollmentPaymentComplete()))
				apiRequest.addVars("enrollmentPaymentComplete");
			if(!Objects.equals(customerProfileId, original.getCustomerProfileId()))
				apiRequest.addVars("customerProfileId");
			if(!Objects.equals(enrollmentChargeDate, original.getEnrollmentChargeDate()))
				apiRequest.addVars("enrollmentChargeDate");
			if(!Objects.equals(paymentLastDate, original.getPaymentLastDate()))
				apiRequest.addVars("paymentLastDate");
			if(!Objects.equals(paymentLastStr, original.getPaymentLastStr()))
				apiRequest.addVars("paymentLastStr");
			if(!Objects.equals(paymentAmount, original.getPaymentAmount()))
				apiRequest.addVars("paymentAmount");
			if(!Objects.equals(chargeAmount, original.getChargeAmount()))
				apiRequest.addVars("chargeAmount");
			if(!Objects.equals(chargeAmountFuture, original.getChargeAmountFuture()))
				apiRequest.addVars("chargeAmountFuture");
			if(!Objects.equals(chargeAmountDue, original.getChargeAmountDue()))
				apiRequest.addVars("chargeAmountDue");
			if(!Objects.equals(chargeAmountNotPassed, original.getChargeAmountNotPassed()))
				apiRequest.addVars("chargeAmountNotPassed");
			if(!Objects.equals(chargesNow, original.getChargesNow()))
				apiRequest.addVars("chargesNow");
			if(!Objects.equals(paymentsCurrent, original.getPaymentsCurrent()))
				apiRequest.addVars("paymentsCurrent");
			if(!Objects.equals(paymentsLate, original.getPaymentsLate()))
				apiRequest.addVars("paymentsLate");
			if(!Objects.equals(paymentsLateAmount, original.getPaymentsLateAmount()))
				apiRequest.addVars("paymentsLateAmount");
			if(!Objects.equals(paymentsAhead, original.getPaymentsAhead()))
				apiRequest.addVars("paymentsAhead");
			if(!Objects.equals(paymentsPastDue, original.getPaymentsPastDue()))
				apiRequest.addVars("paymentsPastDue");
			if(!Objects.equals(paymentsPastDueAmount, original.getPaymentsPastDueAmount()))
				apiRequest.addVars("paymentsPastDueAmount");
			if(!Objects.equals(chargesCreated, original.getChargesCreated()))
				apiRequest.addVars("chargesCreated");
			if(!Objects.equals(createdYear, original.getCreatedYear()))
				apiRequest.addVars("createdYear");
			if(!Objects.equals(createdDayOfWeek, original.getCreatedDayOfWeek()))
				apiRequest.addVars("createdDayOfWeek");
			if(!Objects.equals(createdMonthOfYear, original.getCreatedMonthOfYear()))
				apiRequest.addVars("createdMonthOfYear");
			if(!Objects.equals(createdHourOfDay, original.getCreatedHourOfDay()))
				apiRequest.addVars("createdHourOfDay");
			if(!Objects.equals(enrollmentDaysOfWeek, original.getEnrollmentDaysOfWeek()))
				apiRequest.addVars("enrollmentDaysOfWeek");
			if(!Objects.equals(enrollmentParentNames, original.getEnrollmentParentNames()))
				apiRequest.addVars("enrollmentParentNames");
			if(!Objects.equals(enrollmentEmails, original.getEnrollmentEmails()))
				apiRequest.addVars("enrollmentEmails");
			if(!Objects.equals(enrollmentEmail, original.getEnrollmentEmail()))
				apiRequest.addVars("enrollmentEmail");
			if(!Objects.equals(enrollmentParentEmails, original.getEnrollmentParentEmails()))
				apiRequest.addVars("enrollmentParentEmails");
			if(!Objects.equals(enrollmentPhoneNumbers, original.getEnrollmentPhoneNumbers()))
				apiRequest.addVars("enrollmentPhoneNumbers");
			if(!Objects.equals(enrollmentPhoneNumber, original.getEnrollmentPhoneNumber()))
				apiRequest.addVars("enrollmentPhoneNumber");
			if(!Objects.equals(enrollmentParentName, original.getEnrollmentParentName()))
				apiRequest.addVars("enrollmentParentName");
			if(!Objects.equals(enrollmentParentNameLines, original.getEnrollmentParentNameLines()))
				apiRequest.addVars("enrollmentParentNameLines");
			if(!Objects.equals(enrollmentParentEmailLines, original.getEnrollmentParentEmailLines()))
				apiRequest.addVars("enrollmentParentEmailLines");
			if(!Objects.equals(enrollmentParentDetailLines, original.getEnrollmentParentDetailLines()))
				apiRequest.addVars("enrollmentParentDetailLines");
			if(!Objects.equals(enrollmentPickupDetailLines, original.getEnrollmentPickupDetailLines()))
				apiRequest.addVars("enrollmentPickupDetailLines");
			if(!Objects.equals(enrollmentEmergencyContactDetailLines, original.getEnrollmentEmergencyContactDetailLines()))
				apiRequest.addVars("enrollmentEmergencyContactDetailLines");
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
			if(!Objects.equals(childImmunizationsReceived, original.getChildImmunizationsReceived()))
				apiRequest.addVars("childImmunizationsReceived");
			if(!Objects.equals(childPhotosApproved, original.getChildPhotosApproved()))
				apiRequest.addVars("childPhotosApproved");
			if(!Objects.equals(enrollmentCompleteName, original.getEnrollmentCompleteName()))
				apiRequest.addVars("enrollmentCompleteName");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), enrollmentKey, yearKey, blockKeys, schoolKey, sessionKey, ageKey, blockKey, childKey, momKeys, dadKeys, guardianKeys, paymentKeys, enrollmentFormKey, userKeys, educationSort, schoolSort, yearSort, seasonSort, sessionSort, ageSort, childFirstName, childFirstNamePreferred, childFamilyName, momFirstName, momFirstNamePreferred, momCompleteNamePreferred, dadFirstName, dadFirstNamePreferred, dadCompleteNamePreferred, childCompleteName, childCompleteNamePreferred, childBirthDate, childBirthDateYear, childBirthDateMonthOfYear, childBirthDateDayOfWeek, childBirthMonth, childBirthDay, schoolName, schoolCompleteName, schoolLocation, schoolAddress, schoolPhoneNumber, schoolForm, schoolNumber, schoolAdministratorName, yearStart, yearEnd, seasonStartDate, yearEnrollmentFee, sessionStartDate, sessionEndDate, ageCompleteName, ageStart, ageEnd, blockStartTime, blockEndTime, blockPricePerMonth, blockSunday, blockMonday, blockTuesday, blockWednesday, blockThursday, blockFriday, blockSaturday, blockTotalPrice, blockAdminName, blockShortName, blockCompleteName, enrollmentApproved, enrollmentImmunizations, photo, familyMarried, familySeparated, familyDivorced, familyAddress, familyHowDoYouKnowTheSchool, enrollmentSpecialConsiderations, childMedicalConditions, childPreviousSchoolsAttended, childDescription, childObjectives, childPottyTrained, enrollmentGroupName, enrollmentGroupColor, enrollmentPaymentEachMonth, enrollmentPaymentComplete, customerProfileId, enrollmentChargeDate, paymentLastDate, paymentLastStr, paymentAmount, chargeAmount, chargeAmountFuture, chargeAmountDue, chargeAmountNotPassed, chargesNow, paymentsCurrent, paymentsLate, paymentsLateAmount, paymentsAhead, paymentsPastDue, paymentsPastDueAmount, chargesCreated, createdYear, createdDayOfWeek, createdMonthOfYear, createdHourOfDay, enrollmentDaysOfWeek, enrollmentParentNames, enrollmentEmails, enrollmentEmail, enrollmentParentEmails, enrollmentPhoneNumbers, enrollmentPhoneNumber, enrollmentParentName, enrollmentParentNameLines, enrollmentParentEmailLines, enrollmentParentDetailLines, enrollmentPickupDetailLines, enrollmentEmergencyContactDetailLines, enrollmentSignature1, enrollmentSignature2, enrollmentSignature3, enrollmentSignature4, enrollmentSignature5, enrollmentSignature6, enrollmentSignature7, enrollmentSignature8, enrollmentSignature9, enrollmentSignature10, enrollmentDate1, enrollmentDate2, enrollmentDate3, enrollmentDate4, enrollmentDate5, enrollmentDate6, enrollmentDate7, enrollmentDate8, enrollmentDate9, enrollmentDate10, childImmunizationsReceived, childPhotosApproved, enrollmentCompleteName);
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
				&& Objects.equals( enrollmentKey, that.enrollmentKey )
				&& Objects.equals( yearKey, that.yearKey )
				&& Objects.equals( blockKeys, that.blockKeys )
				&& Objects.equals( schoolKey, that.schoolKey )
				&& Objects.equals( sessionKey, that.sessionKey )
				&& Objects.equals( ageKey, that.ageKey )
				&& Objects.equals( blockKey, that.blockKey )
				&& Objects.equals( childKey, that.childKey )
				&& Objects.equals( momKeys, that.momKeys )
				&& Objects.equals( dadKeys, that.dadKeys )
				&& Objects.equals( guardianKeys, that.guardianKeys )
				&& Objects.equals( paymentKeys, that.paymentKeys )
				&& Objects.equals( enrollmentFormKey, that.enrollmentFormKey )
				&& Objects.equals( userKeys, that.userKeys )
				&& Objects.equals( educationSort, that.educationSort )
				&& Objects.equals( schoolSort, that.schoolSort )
				&& Objects.equals( yearSort, that.yearSort )
				&& Objects.equals( seasonSort, that.seasonSort )
				&& Objects.equals( sessionSort, that.sessionSort )
				&& Objects.equals( ageSort, that.ageSort )
				&& Objects.equals( childFirstName, that.childFirstName )
				&& Objects.equals( childFirstNamePreferred, that.childFirstNamePreferred )
				&& Objects.equals( childFamilyName, that.childFamilyName )
				&& Objects.equals( momFirstName, that.momFirstName )
				&& Objects.equals( momFirstNamePreferred, that.momFirstNamePreferred )
				&& Objects.equals( momCompleteNamePreferred, that.momCompleteNamePreferred )
				&& Objects.equals( dadFirstName, that.dadFirstName )
				&& Objects.equals( dadFirstNamePreferred, that.dadFirstNamePreferred )
				&& Objects.equals( dadCompleteNamePreferred, that.dadCompleteNamePreferred )
				&& Objects.equals( childCompleteName, that.childCompleteName )
				&& Objects.equals( childCompleteNamePreferred, that.childCompleteNamePreferred )
				&& Objects.equals( childBirthDate, that.childBirthDate )
				&& Objects.equals( childBirthDateYear, that.childBirthDateYear )
				&& Objects.equals( childBirthDateMonthOfYear, that.childBirthDateMonthOfYear )
				&& Objects.equals( childBirthDateDayOfWeek, that.childBirthDateDayOfWeek )
				&& Objects.equals( childBirthMonth, that.childBirthMonth )
				&& Objects.equals( childBirthDay, that.childBirthDay )
				&& Objects.equals( schoolName, that.schoolName )
				&& Objects.equals( schoolCompleteName, that.schoolCompleteName )
				&& Objects.equals( schoolLocation, that.schoolLocation )
				&& Objects.equals( schoolAddress, that.schoolAddress )
				&& Objects.equals( schoolPhoneNumber, that.schoolPhoneNumber )
				&& Objects.equals( schoolForm, that.schoolForm )
				&& Objects.equals( schoolNumber, that.schoolNumber )
				&& Objects.equals( schoolAdministratorName, that.schoolAdministratorName )
				&& Objects.equals( yearStart, that.yearStart )
				&& Objects.equals( yearEnd, that.yearEnd )
				&& Objects.equals( seasonStartDate, that.seasonStartDate )
				&& Objects.equals( yearEnrollmentFee, that.yearEnrollmentFee )
				&& Objects.equals( sessionStartDate, that.sessionStartDate )
				&& Objects.equals( sessionEndDate, that.sessionEndDate )
				&& Objects.equals( ageCompleteName, that.ageCompleteName )
				&& Objects.equals( ageStart, that.ageStart )
				&& Objects.equals( ageEnd, that.ageEnd )
				&& Objects.equals( blockStartTime, that.blockStartTime )
				&& Objects.equals( blockEndTime, that.blockEndTime )
				&& Objects.equals( blockPricePerMonth, that.blockPricePerMonth )
				&& Objects.equals( blockSunday, that.blockSunday )
				&& Objects.equals( blockMonday, that.blockMonday )
				&& Objects.equals( blockTuesday, that.blockTuesday )
				&& Objects.equals( blockWednesday, that.blockWednesday )
				&& Objects.equals( blockThursday, that.blockThursday )
				&& Objects.equals( blockFriday, that.blockFriday )
				&& Objects.equals( blockSaturday, that.blockSaturday )
				&& Objects.equals( blockTotalPrice, that.blockTotalPrice )
				&& Objects.equals( blockAdminName, that.blockAdminName )
				&& Objects.equals( blockShortName, that.blockShortName )
				&& Objects.equals( blockCompleteName, that.blockCompleteName )
				&& Objects.equals( enrollmentApproved, that.enrollmentApproved )
				&& Objects.equals( enrollmentImmunizations, that.enrollmentImmunizations )
				&& Objects.equals( photo, that.photo )
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
				&& Objects.equals( enrollmentGroupColor, that.enrollmentGroupColor )
				&& Objects.equals( enrollmentPaymentEachMonth, that.enrollmentPaymentEachMonth )
				&& Objects.equals( enrollmentPaymentComplete, that.enrollmentPaymentComplete )
				&& Objects.equals( customerProfileId, that.customerProfileId )
				&& Objects.equals( enrollmentChargeDate, that.enrollmentChargeDate )
				&& Objects.equals( paymentLastDate, that.paymentLastDate )
				&& Objects.equals( paymentLastStr, that.paymentLastStr )
				&& Objects.equals( paymentAmount, that.paymentAmount )
				&& Objects.equals( chargeAmount, that.chargeAmount )
				&& Objects.equals( chargeAmountFuture, that.chargeAmountFuture )
				&& Objects.equals( chargeAmountDue, that.chargeAmountDue )
				&& Objects.equals( chargeAmountNotPassed, that.chargeAmountNotPassed )
				&& Objects.equals( chargesNow, that.chargesNow )
				&& Objects.equals( paymentsCurrent, that.paymentsCurrent )
				&& Objects.equals( paymentsLate, that.paymentsLate )
				&& Objects.equals( paymentsLateAmount, that.paymentsLateAmount )
				&& Objects.equals( paymentsAhead, that.paymentsAhead )
				&& Objects.equals( paymentsPastDue, that.paymentsPastDue )
				&& Objects.equals( paymentsPastDueAmount, that.paymentsPastDueAmount )
				&& Objects.equals( chargesCreated, that.chargesCreated )
				&& Objects.equals( createdYear, that.createdYear )
				&& Objects.equals( createdDayOfWeek, that.createdDayOfWeek )
				&& Objects.equals( createdMonthOfYear, that.createdMonthOfYear )
				&& Objects.equals( createdHourOfDay, that.createdHourOfDay )
				&& Objects.equals( enrollmentDaysOfWeek, that.enrollmentDaysOfWeek )
				&& Objects.equals( enrollmentParentNames, that.enrollmentParentNames )
				&& Objects.equals( enrollmentEmails, that.enrollmentEmails )
				&& Objects.equals( enrollmentEmail, that.enrollmentEmail )
				&& Objects.equals( enrollmentParentEmails, that.enrollmentParentEmails )
				&& Objects.equals( enrollmentPhoneNumbers, that.enrollmentPhoneNumbers )
				&& Objects.equals( enrollmentPhoneNumber, that.enrollmentPhoneNumber )
				&& Objects.equals( enrollmentParentName, that.enrollmentParentName )
				&& Objects.equals( enrollmentParentNameLines, that.enrollmentParentNameLines )
				&& Objects.equals( enrollmentParentEmailLines, that.enrollmentParentEmailLines )
				&& Objects.equals( enrollmentParentDetailLines, that.enrollmentParentDetailLines )
				&& Objects.equals( enrollmentPickupDetailLines, that.enrollmentPickupDetailLines )
				&& Objects.equals( enrollmentEmergencyContactDetailLines, that.enrollmentEmergencyContactDetailLines )
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
				&& Objects.equals( enrollmentDate10, that.enrollmentDate10 )
				&& Objects.equals( childImmunizationsReceived, that.childImmunizationsReceived )
				&& Objects.equals( childPhotosApproved, that.childPhotosApproved )
				&& Objects.equals( enrollmentCompleteName, that.enrollmentCompleteName );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolEnrollment { ");
		sb.append( "enrollmentKey: " ).append(enrollmentKey);
		sb.append( ", yearKey: " ).append(yearKey);
		sb.append( ", blockKeys: " ).append(blockKeys);
		sb.append( ", schoolKey: " ).append(schoolKey);
		sb.append( ", sessionKey: " ).append(sessionKey);
		sb.append( ", ageKey: " ).append(ageKey);
		sb.append( ", blockKey: " ).append(blockKey);
		sb.append( ", childKey: " ).append(childKey);
		sb.append( ", momKeys: " ).append(momKeys);
		sb.append( ", dadKeys: " ).append(dadKeys);
		sb.append( ", guardianKeys: " ).append(guardianKeys);
		sb.append( ", paymentKeys: " ).append(paymentKeys);
		sb.append( ", enrollmentFormKey: " ).append(enrollmentFormKey);
		sb.append( ", userKeys: " ).append(userKeys);
		sb.append( ", educationSort: " ).append(educationSort);
		sb.append( ", schoolSort: " ).append(schoolSort);
		sb.append( ", yearSort: " ).append(yearSort);
		sb.append( ", seasonSort: " ).append(seasonSort);
		sb.append( ", sessionSort: " ).append(sessionSort);
		sb.append( ", ageSort: " ).append(ageSort);
		sb.append( ", childFirstName: \"" ).append(childFirstName).append( "\"" );
		sb.append( ", childFirstNamePreferred: \"" ).append(childFirstNamePreferred).append( "\"" );
		sb.append( ", childFamilyName: \"" ).append(childFamilyName).append( "\"" );
		sb.append( ", momFirstName: \"" ).append(momFirstName).append( "\"" );
		sb.append( ", momFirstNamePreferred: \"" ).append(momFirstNamePreferred).append( "\"" );
		sb.append( ", momCompleteNamePreferred: \"" ).append(momCompleteNamePreferred).append( "\"" );
		sb.append( ", dadFirstName: \"" ).append(dadFirstName).append( "\"" );
		sb.append( ", dadFirstNamePreferred: \"" ).append(dadFirstNamePreferred).append( "\"" );
		sb.append( ", dadCompleteNamePreferred: \"" ).append(dadCompleteNamePreferred).append( "\"" );
		sb.append( ", childCompleteName: \"" ).append(childCompleteName).append( "\"" );
		sb.append( ", childCompleteNamePreferred: \"" ).append(childCompleteNamePreferred).append( "\"" );
		sb.append( ", childBirthDate: " ).append(childBirthDate);
		sb.append( ", childBirthDateYear: " ).append(childBirthDateYear);
		sb.append( ", childBirthDateMonthOfYear: \"" ).append(childBirthDateMonthOfYear).append( "\"" );
		sb.append( ", childBirthDateDayOfWeek: \"" ).append(childBirthDateDayOfWeek).append( "\"" );
		sb.append( ", childBirthMonth: " ).append(childBirthMonth);
		sb.append( ", childBirthDay: " ).append(childBirthDay);
		sb.append( ", schoolName: \"" ).append(schoolName).append( "\"" );
		sb.append( ", schoolCompleteName: \"" ).append(schoolCompleteName).append( "\"" );
		sb.append( ", schoolLocation: \"" ).append(schoolLocation).append( "\"" );
		sb.append( ", schoolAddress: \"" ).append(schoolAddress).append( "\"" );
		sb.append( ", schoolPhoneNumber: \"" ).append(schoolPhoneNumber).append( "\"" );
		sb.append( ", schoolForm: \"" ).append(schoolForm).append( "\"" );
		sb.append( ", schoolNumber: " ).append(schoolNumber);
		sb.append( ", schoolAdministratorName: \"" ).append(schoolAdministratorName).append( "\"" );
		sb.append( ", yearStart: " ).append(yearStart);
		sb.append( ", yearEnd: " ).append(yearEnd);
		sb.append( ", seasonStartDate: " ).append(seasonStartDate);
		sb.append( ", yearEnrollmentFee: " ).append(yearEnrollmentFee);
		sb.append( ", sessionStartDate: " ).append(sessionStartDate);
		sb.append( ", sessionEndDate: " ).append(sessionEndDate);
		sb.append( ", ageCompleteName: \"" ).append(ageCompleteName).append( "\"" );
		sb.append( ", ageStart: " ).append(ageStart);
		sb.append( ", ageEnd: " ).append(ageEnd);
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
		sb.append( ", blockTotalPrice: " ).append(blockTotalPrice);
		sb.append( ", blockAdminName: \"" ).append(blockAdminName).append( "\"" );
		sb.append( ", blockShortName: \"" ).append(blockShortName).append( "\"" );
		sb.append( ", blockCompleteName: \"" ).append(blockCompleteName).append( "\"" );
		sb.append( ", enrollmentApproved: " ).append(enrollmentApproved);
		sb.append( ", enrollmentImmunizations: " ).append(enrollmentImmunizations);
		sb.append( ", photo: \"" ).append(photo).append( "\"" );
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
		sb.append( ", enrollmentGroupColor: \"" ).append(enrollmentGroupColor).append( "\"" );
		sb.append( ", enrollmentPaymentEachMonth: " ).append(enrollmentPaymentEachMonth);
		sb.append( ", enrollmentPaymentComplete: " ).append(enrollmentPaymentComplete);
		sb.append( ", customerProfileId: \"" ).append(customerProfileId).append( "\"" );
		sb.append( ", enrollmentChargeDate: " ).append(enrollmentChargeDate);
		sb.append( ", paymentLastDate: " ).append(paymentLastDate);
		sb.append( ", paymentLastStr: \"" ).append(paymentLastStr).append( "\"" );
		sb.append( ", paymentAmount: " ).append(paymentAmount);
		sb.append( ", chargeAmount: " ).append(chargeAmount);
		sb.append( ", chargeAmountFuture: " ).append(chargeAmountFuture);
		sb.append( ", chargeAmountDue: " ).append(chargeAmountDue);
		sb.append( ", chargeAmountNotPassed: " ).append(chargeAmountNotPassed);
		sb.append( ", chargesNow: " ).append(chargesNow);
		sb.append( ", paymentsCurrent: " ).append(paymentsCurrent);
		sb.append( ", paymentsLate: " ).append(paymentsLate);
		sb.append( ", paymentsLateAmount: " ).append(paymentsLateAmount);
		sb.append( ", paymentsAhead: " ).append(paymentsAhead);
		sb.append( ", paymentsPastDue: " ).append(paymentsPastDue);
		sb.append( ", paymentsPastDueAmount: " ).append(paymentsPastDueAmount);
		sb.append( ", chargesCreated: " ).append(chargesCreated);
		sb.append( ", createdYear: " ).append(createdYear);
		sb.append( ", createdDayOfWeek: \"" ).append(createdDayOfWeek).append( "\"" );
		sb.append( ", createdMonthOfYear: \"" ).append(createdMonthOfYear).append( "\"" );
		sb.append( ", createdHourOfDay: \"" ).append(createdHourOfDay).append( "\"" );
		sb.append( ", enrollmentDaysOfWeek: " ).append(enrollmentDaysOfWeek);
		sb.append( ", enrollmentParentNames: \"" ).append(enrollmentParentNames).append( "\"" );
		sb.append( ", enrollmentEmails: " ).append(enrollmentEmails);
		sb.append( ", enrollmentEmail: \"" ).append(enrollmentEmail).append( "\"" );
		sb.append( ", enrollmentParentEmails: \"" ).append(enrollmentParentEmails).append( "\"" );
		sb.append( ", enrollmentPhoneNumbers: " ).append(enrollmentPhoneNumbers);
		sb.append( ", enrollmentPhoneNumber: \"" ).append(enrollmentPhoneNumber).append( "\"" );
		sb.append( ", enrollmentParentName: \"" ).append(enrollmentParentName).append( "\"" );
		sb.append( ", enrollmentParentNameLines: \"" ).append(enrollmentParentNameLines).append( "\"" );
		sb.append( ", enrollmentParentEmailLines: \"" ).append(enrollmentParentEmailLines).append( "\"" );
		sb.append( ", enrollmentParentDetailLines: \"" ).append(enrollmentParentDetailLines).append( "\"" );
		sb.append( ", enrollmentPickupDetailLines: \"" ).append(enrollmentPickupDetailLines).append( "\"" );
		sb.append( ", enrollmentEmergencyContactDetailLines: \"" ).append(enrollmentEmergencyContactDetailLines).append( "\"" );
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
		sb.append( ", childImmunizationsReceived: \"" ).append(childImmunizationsReceived).append( "\"" );
		sb.append( ", childPhotosApproved: \"" ).append(childPhotosApproved).append( "\"" );
		sb.append( ", enrollmentCompleteName: \"" ).append(enrollmentCompleteName).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
