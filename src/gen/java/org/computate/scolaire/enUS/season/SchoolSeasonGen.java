package org.computate.scolaire.enUS.season;

import java.util.Arrays;
import java.util.Date;
import org.computate.scolaire.enUS.session.SchoolSession;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
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
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SchoolSeasonGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolSeason.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String SchoolSeason_AName = "a season";
	public static final String SchoolSeason_This = "this ";
	public static final String SchoolSeason_ThisName = "this season";
	public static final String SchoolSeason_A = "a ";
	public static final String SchoolSeason_TheName = "the season";
	public static final String SchoolSeason_NameSingular = "season";
	public static final String SchoolSeason_NamePlural = "seasons";
	public static final String SchoolSeason_NameActual = "current season";
	public static final String SchoolSeason_AllName = "all the seasons";
	public static final String SchoolSeason_SearchAllNameBy = "search seasons by ";
	public static final String SchoolSeason_Title = "seasons";
	public static final String SchoolSeason_ThePluralName = "the seasons";
	public static final String SchoolSeason_NoNameFound = "no season found";
	public static final String SchoolSeason_NameVar = "season";
	public static final String SchoolSeason_OfName = "of season";
	public static final String SchoolSeason_AdjectivePlural = "school";
	public static final String SchoolSeason_AdjectiveVar = "school";
	public static final String SchoolSeason_ANameAdjective = "a school season";
	public static final String SchoolSeason_NameAdjectiveSingular = "school season";
	public static final String SchoolSeason_NameAdjectivePlural = "school seasons";
	public static final String SchoolSeason_Color = "yellow";
	public static final String SchoolSeason_IconGroup = "regular";
	public static final String SchoolSeason_IconName = "sun";

	///////////////
	// seasonKey //
	///////////////

	/**	 The entity seasonKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long seasonKey;
	@JsonIgnore
	public Wrap<Long> seasonKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("seasonKey").o(seasonKey);

	/**	<br/> The entity seasonKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKey">Find the entity seasonKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonKey(Wrap<Long> c);

	public Long getSeasonKey() {
		return seasonKey;
	}

	public void setSeasonKey(Long seasonKey) {
		this.seasonKey = seasonKey;
		this.seasonKeyWrap.alreadyInitialized = true;
	}
	public void setSeasonKey(String o) {
		this.seasonKey = SchoolSeason.staticSetSeasonKey(siteRequest_, o);
		this.seasonKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetSeasonKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrSeasonKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSeasonKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSeasonKey(siteRequest_, SchoolSeason.staticSolrSeasonKey(siteRequest_, SchoolSeason.staticSetSeasonKey(siteRequest_, o)));
	}

	public Long solrSeasonKey() {
		return SchoolSeason.staticSolrSeasonKey(siteRequest_, seasonKey);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Find the entity enrollmentKeys in Solr</a>
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
		Long l = SchoolSeason.staticSetEnrollmentKeys(siteRequest_, o);
		if(l != null)
			addEnrollmentKeys(l);
		this.enrollmentKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
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

	public static Long staticSolrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrEnrollmentKeys(siteRequest_, SchoolSeason.staticSolrEnrollmentKeys(siteRequest_, SchoolSeason.staticSetEnrollmentKeys(siteRequest_, o)));
	}

	public List<Long> solrEnrollmentKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : enrollmentKeys) {
			l.add(SchoolSeason.staticSolrEnrollmentKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Find the entity yearKey in Solr</a>
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
		this.yearKey = SchoolSeason.staticSetYearKey(siteRequest_, o);
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetYearKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrYearKey(siteRequest_, SchoolSeason.staticSolrYearKey(siteRequest_, SchoolSeason.staticSetYearKey(siteRequest_, o)));
	}

	public Long solrYearKey() {
		return SchoolSeason.staticSolrYearKey(siteRequest_, yearKey);
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
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
				a("oninput", "suggestSchoolSeasonYearKey($(this).val() ? searchSchoolYearFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'seasonKeys:" + pk + "'}", "], $('#listSchoolSeasonYearKey_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolSeason", pk, "YearKey ").f().sx(htmYearKey()).g("span");
			}
		}
	}

	public void htmYearKey(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolSeasonYearKey").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/year?fq=seasonKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-orange w3-hover-orange ").f();
								e("i").a("class", "far fa-calendar-check ").f().g("i");
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
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), SchoolYear.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), SchoolYear.ROLES)
										) {
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
												.a("id", classApiMethodMethod, "_yearKey_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolYearVals({ seasonKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "yearKey')); });")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKeys">Find the entity sessionKeys in Solr</a>
	 * <br/>
	 * @param sessionKeys is the entity already constructed. 
	 **/
	protected abstract void _sessionKeys(List<Long> o);

	public List<Long> getSessionKeys() {
		return sessionKeys;
	}

	public void setSessionKeys(List<Long> sessionKeys) {
		this.sessionKeys = sessionKeys;
		this.sessionKeysWrap.alreadyInitialized = true;
	}
	public void setSessionKeys(String o) {
		Long l = SchoolSeason.staticSetSessionKeys(siteRequest_, o);
		if(l != null)
			addSessionKeys(l);
		this.sessionKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetSessionKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setSessionKeys(JsonArray objets) {
		sessionKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionKeys(o);
		}
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

	public static Long staticSolrSessionKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSessionKeys(siteRequest_, SchoolSeason.staticSolrSessionKeys(siteRequest_, SchoolSeason.staticSetSessionKeys(siteRequest_, o)));
	}

	public List<Long> solrSessionKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : sessionKeys) {
			l.add(SchoolSeason.staticSolrSessionKeys(siteRequest_, o));
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

	public void inputSessionKeys(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			if("PUTCopy".equals(classApiMethodMethod)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classApiMethodMethod, "_sessionKeys_clear")
						.a("class", "sessionKeys_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_sessionKeys_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "sessions")
				.a("class", "valueObjectSuggest suggestSessionKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setSessionKeys")
				.a("id", classApiMethodMethod, "_sessionKeys")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolSeasonSessionKeys($(this).val() ? searchSchoolSessionFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'seasonKey:" + pk + "'}", "], $('#listSchoolSeasonSessionKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolSeason", pk, "SessionKeys ").f().sx(htmSessionKeys()).g("span");
			}
		}
	}

	public void htmSessionKeys(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolSeasonSessionKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/session?fq=seasonKey:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "fad fa-graduation-cap ").f().g("i");
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
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), SchoolSession.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), SchoolSession.ROLES)
										) {
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
												.a("id", classApiMethodMethod, "_sessionKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolSessionVals({ seasonKey: \"", pk, "\" }, function() {}, function() { addError($('#", classApiMethodMethod, "sessionKeys')); });")
												.f().sx("add a session")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Find the entity educationSort in Solr</a>
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
		this.educationSort = SchoolSeason.staticSetEducationSort(siteRequest_, o);
		this.educationSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetEducationSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEducationSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrEducationSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEducationSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrEducationSort(siteRequest_, SchoolSeason.staticSolrEducationSort(siteRequest_, SchoolSeason.staticSetEducationSort(siteRequest_, o)));
	}

	public Integer solrEducationSort() {
		return SchoolSeason.staticSolrEducationSort(siteRequest_, educationSort);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Find the entity schoolSort in Solr</a>
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
		this.schoolSort = SchoolSeason.staticSetSchoolSort(siteRequest_, o);
		this.schoolSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSchoolSort(siteRequest_, SchoolSeason.staticSolrSchoolSort(siteRequest_, SchoolSeason.staticSetSchoolSort(siteRequest_, o)));
	}

	public Integer solrSchoolSort() {
		return SchoolSeason.staticSolrSchoolSort(siteRequest_, schoolSort);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSort">Find the entity yearSort in Solr</a>
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
		this.yearSort = SchoolSeason.staticSetYearSort(siteRequest_, o);
		this.yearSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrYearSort(siteRequest_, SchoolSeason.staticSolrYearSort(siteRequest_, SchoolSeason.staticSetYearSort(siteRequest_, o)));
	}

	public Integer solrYearSort() {
		return SchoolSeason.staticSolrYearSort(siteRequest_, yearSort);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSort">Find the entity seasonSort in Solr</a>
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
		this.seasonSort = SchoolSeason.staticSetSeasonSort(siteRequest_, o);
		this.seasonSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSeasonSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSeasonSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSeasonSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSeasonSort(siteRequest_, SchoolSeason.staticSolrSeasonSort(siteRequest_, SchoolSeason.staticSetSeasonSort(siteRequest_, o)));
	}

	public Integer solrSeasonSort() {
		return SchoolSeason.staticSolrSeasonSort(siteRequest_, seasonSort);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSearch">Find the entity yearSearch in Solr</a>
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:year_">Find the entity year_ in Solr</a>
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Find the entity schoolKey in Solr</a>
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
		this.schoolKey = SchoolSeason.staticSetSchoolKey(siteRequest_, o);
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSchoolKey(siteRequest_, SchoolSeason.staticSolrSchoolKey(siteRequest_, SchoolSeason.staticSetSchoolKey(siteRequest_, o)));
	}

	public Long solrSchoolKey() {
		return SchoolSeason.staticSolrSchoolKey(siteRequest_, schoolKey);
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

	/**	 The entity schoolName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolName;
	@JsonIgnore
	public Wrap<String> schoolNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolName").o(schoolName);

	/**	<br/> The entity schoolName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Find the entity schoolName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolName(Wrap<String> c);

	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String o) {
		this.schoolName = SchoolSeason.staticSetSchoolName(siteRequest_, o);
		this.schoolNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSchoolName(siteRequest_, SchoolSeason.staticSolrSchoolName(siteRequest_, SchoolSeason.staticSetSchoolName(siteRequest_, o)));
	}

	public String solrSchoolName() {
		return SchoolSeason.staticSolrSchoolName(siteRequest_, schoolName);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Find the entity schoolCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolCompleteName(Wrap<String> c);

	public String getSchoolCompleteName() {
		return schoolCompleteName;
	}
	public void setSchoolCompleteName(String o) {
		this.schoolCompleteName = SchoolSeason.staticSetSchoolCompleteName(siteRequest_, o);
		this.schoolCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSchoolCompleteName(siteRequest_, SchoolSeason.staticSolrSchoolCompleteName(siteRequest_, SchoolSeason.staticSetSchoolCompleteName(siteRequest_, o)));
	}

	public String solrSchoolCompleteName() {
		return SchoolSeason.staticSolrSchoolCompleteName(siteRequest_, schoolCompleteName);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Find the entity schoolLocation in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolLocation(Wrap<String> c);

	public String getSchoolLocation() {
		return schoolLocation;
	}
	public void setSchoolLocation(String o) {
		this.schoolLocation = SchoolSeason.staticSetSchoolLocation(siteRequest_, o);
		this.schoolLocationWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSchoolLocation(siteRequest_, SchoolSeason.staticSolrSchoolLocation(siteRequest_, SchoolSeason.staticSetSchoolLocation(siteRequest_, o)));
	}

	public String solrSchoolLocation() {
		return SchoolSeason.staticSolrSchoolLocation(siteRequest_, schoolLocation);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Find the entity schoolAddress in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAddress(Wrap<String> c);

	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String o) {
		this.schoolAddress = SchoolSeason.staticSetSchoolAddress(siteRequest_, o);
		this.schoolAddressWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSchoolAddress(siteRequest_, SchoolSeason.staticSolrSchoolAddress(siteRequest_, SchoolSeason.staticSetSchoolAddress(siteRequest_, o)));
	}

	public String solrSchoolAddress() {
		return SchoolSeason.staticSolrSchoolAddress(siteRequest_, schoolAddress);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Find the entity schoolPhoneNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolPhoneNumber(Wrap<String> c);

	public String getSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}
	public void setSchoolPhoneNumber(String o) {
		this.schoolPhoneNumber = SchoolSeason.staticSetSchoolPhoneNumber(siteRequest_, o);
		this.schoolPhoneNumberWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSchoolPhoneNumber(siteRequest_, SchoolSeason.staticSolrSchoolPhoneNumber(siteRequest_, SchoolSeason.staticSetSchoolPhoneNumber(siteRequest_, o)));
	}

	public String solrSchoolPhoneNumber() {
		return SchoolSeason.staticSolrSchoolPhoneNumber(siteRequest_, schoolPhoneNumber);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolForm">Find the entity schoolForm in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolForm(Wrap<String> c);

	public String getSchoolForm() {
		return schoolForm;
	}
	public void setSchoolForm(String o) {
		this.schoolForm = SchoolSeason.staticSetSchoolForm(siteRequest_, o);
		this.schoolFormWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolSeason schoolFormInit() {
		if(!schoolFormWrap.alreadyInitialized) {
			_schoolForm(schoolFormWrap);
			if(schoolForm == null)
				setSchoolForm(schoolFormWrap.o);
		}
		schoolFormWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public static String staticSolrSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolForm(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSchoolForm(siteRequest_, SchoolSeason.staticSolrSchoolForm(siteRequest_, SchoolSeason.staticSetSchoolForm(siteRequest_, o)));
	}

	public String solrSchoolForm() {
		return SchoolSeason.staticSolrSchoolForm(siteRequest_, schoolForm);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolNumber">Find the entity schoolNumber in Solr</a>
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
		this.schoolNumber = SchoolSeason.staticSetSchoolNumber(siteRequest_, o);
		this.schoolNumberWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolNumber(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolSeason schoolNumberInit() {
		if(!schoolNumberWrap.alreadyInitialized) {
			_schoolNumber(schoolNumberWrap);
			if(schoolNumber == null)
				setSchoolNumber(schoolNumberWrap.o);
		}
		schoolNumberWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public static Integer staticSolrSchoolNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolNumber(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSchoolNumber(siteRequest_, SchoolSeason.staticSolrSchoolNumber(siteRequest_, SchoolSeason.staticSetSchoolNumber(siteRequest_, o)));
	}

	public Integer solrSchoolNumber() {
		return SchoolSeason.staticSolrSchoolNumber(siteRequest_, schoolNumber);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Find the entity schoolAdministratorName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAdministratorName(Wrap<String> c);

	public String getSchoolAdministratorName() {
		return schoolAdministratorName;
	}
	public void setSchoolAdministratorName(String o) {
		this.schoolAdministratorName = SchoolSeason.staticSetSchoolAdministratorName(siteRequest_, o);
		this.schoolAdministratorNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSchoolAdministratorName(siteRequest_, SchoolSeason.staticSolrSchoolAdministratorName(siteRequest_, SchoolSeason.staticSetSchoolAdministratorName(siteRequest_, o)));
	}

	public String solrSchoolAdministratorName() {
		return SchoolSeason.staticSolrSchoolAdministratorName(siteRequest_, schoolAdministratorName);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Find the entity yearStart in Solr</a>
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
		this.yearStart = SchoolSeason.staticSetYearStart(siteRequest_, o);
		this.yearStartWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearStart(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearStart(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrYearStart(siteRequest_, SchoolSeason.staticSolrYearStart(siteRequest_, SchoolSeason.staticSetYearStart(siteRequest_, o)));
	}

	public Integer solrYearStart() {
		return SchoolSeason.staticSolrYearStart(siteRequest_, yearStart);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Find the entity yearEnd in Solr</a>
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
		this.yearEnd = SchoolSeason.staticSetYearEnd(siteRequest_, o);
		this.yearEndWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearEnd(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnd(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrYearEnd(siteRequest_, SchoolSeason.staticSolrYearEnd(siteRequest_, SchoolSeason.staticSetYearEnd(siteRequest_, o)));
	}

	public Integer solrYearEnd() {
		return SchoolSeason.staticSolrYearEnd(siteRequest_, yearEnd);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnrollmentFee">Find the entity yearEnrollmentFee in Solr</a>
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
		this.yearEnrollmentFee = SchoolSeason.staticSetYearEnrollmentFee(siteRequest_, o);
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
	protected SchoolSeason yearEnrollmentFeeInit() {
		if(!yearEnrollmentFeeWrap.alreadyInitialized) {
			_yearEnrollmentFee(yearEnrollmentFeeWrap);
			if(yearEnrollmentFee == null)
				setYearEnrollmentFee(yearEnrollmentFeeWrap.o);
		}
		yearEnrollmentFeeWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public static Double staticSolrYearEnrollmentFee(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrYearEnrollmentFee(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnrollmentFee(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrYearEnrollmentFee(siteRequest_, SchoolSeason.staticSolrYearEnrollmentFee(siteRequest_, SchoolSeason.staticSetYearEnrollmentFee(siteRequest_, o)));
	}

	public Double solrYearEnrollmentFee() {
		return SchoolSeason.staticSolrYearEnrollmentFee(siteRequest_, yearEnrollmentFee);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Find the entity seasonStartDate in Solr</a>
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
		this.seasonStartDate = SchoolSeason.staticSetSeasonStartDate(siteRequest_, o);
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSeasonStartDate(Date o) {
		this.seasonStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
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

	public static Date staticSolrSeasonStartDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSeasonStartDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSeasonStartDate(siteRequest_, SchoolSeason.staticSolrSeasonStartDate(siteRequest_, SchoolSeason.staticSetSeasonStartDate(siteRequest_, o)));
	}

	public Date solrSeasonStartDate() {
		return SchoolSeason.staticSolrSeasonStartDate(siteRequest_, seasonStartDate);
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
		SchoolSeason s = (SchoolSeason)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setSeasonStartDate classSchoolSeason inputSchoolSeason", pk, "SeasonStartDate w3-input w3-border ")
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
				e("span").a("class", "varSchoolSeason", pk, "SeasonStartDate ").f().sx(htmSeasonStartDate()).g("span");
			}
		}
	}

	public void htmSeasonStartDate(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolSeasonSeasonStartDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_seasonStartDate')); $('#", classApiMethodMethod, "_seasonStartDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolSeasonForm :input[name=pk]').val() }], 'setSeasonStartDate', null, function() { addGlow($('#", classApiMethodMethod, "_seasonStartDate')); }, function() { addError($('#", classApiMethodMethod, "_seasonStartDate')); }); ")
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

	//////////////////
	// seasonSummer //
	//////////////////

	/**	 The entity seasonSummer
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean seasonSummer;
	@JsonIgnore
	public Wrap<Boolean> seasonSummerWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seasonSummer").o(seasonSummer);

	/**	<br/> The entity seasonSummer
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonSummer">Find the entity seasonSummer in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonSummer(Wrap<Boolean> c);

	public Boolean getSeasonSummer() {
		return seasonSummer;
	}

	public void setSeasonSummer(Boolean seasonSummer) {
		this.seasonSummer = seasonSummer;
		this.seasonSummerWrap.alreadyInitialized = true;
	}
	public void setSeasonSummer(String o) {
		this.seasonSummer = SchoolSeason.staticSetSeasonSummer(siteRequest_, o);
		this.seasonSummerWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetSeasonSummer(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrSeasonSummer(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrSeasonSummer(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonSummer(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSeasonSummer(siteRequest_, SchoolSeason.staticSolrSeasonSummer(siteRequest_, SchoolSeason.staticSetSeasonSummer(siteRequest_, o)));
	}

	public Boolean solrSeasonSummer() {
		return SchoolSeason.staticSolrSeasonSummer(siteRequest_, seasonSummer);
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_seasonSummer")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_seasonSummer");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSeasonSummer classSchoolSeason inputSchoolSeason", pk, "SeasonSummer w3-input w3-border ");
				a("name", "setSeasonSummer");
			} else {
				a("class", "valueSeasonSummer classSchoolSeason inputSchoolSeason", pk, "SeasonSummer w3-input w3-border ");
				a("name", "seasonSummer");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSeasonSummer', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_seasonSummer')); }, function() { addError($('#", classApiMethodMethod, "_seasonSummer')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getSeasonSummer() != null && getSeasonSummer())
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolSeason", pk, "SeasonSummer ").f().sx(htmSeasonSummer()).g("span");
			}
		}
	}

	public void htmSeasonSummer(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolSeasonSeasonSummer").f();
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

	/**	 The entity seasonWinter
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean seasonWinter;
	@JsonIgnore
	public Wrap<Boolean> seasonWinterWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seasonWinter").o(seasonWinter);

	/**	<br/> The entity seasonWinter
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonWinter">Find the entity seasonWinter in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonWinter(Wrap<Boolean> c);

	public Boolean getSeasonWinter() {
		return seasonWinter;
	}

	public void setSeasonWinter(Boolean seasonWinter) {
		this.seasonWinter = seasonWinter;
		this.seasonWinterWrap.alreadyInitialized = true;
	}
	public void setSeasonWinter(String o) {
		this.seasonWinter = SchoolSeason.staticSetSeasonWinter(siteRequest_, o);
		this.seasonWinterWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetSeasonWinter(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrSeasonWinter(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrSeasonWinter(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonWinter(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSeasonWinter(siteRequest_, SchoolSeason.staticSolrSeasonWinter(siteRequest_, SchoolSeason.staticSetSeasonWinter(siteRequest_, o)));
	}

	public Boolean solrSeasonWinter() {
		return SchoolSeason.staticSolrSeasonWinter(siteRequest_, seasonWinter);
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_seasonWinter")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_seasonWinter");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSeasonWinter classSchoolSeason inputSchoolSeason", pk, "SeasonWinter w3-input w3-border ");
				a("name", "setSeasonWinter");
			} else {
				a("class", "valueSeasonWinter classSchoolSeason inputSchoolSeason", pk, "SeasonWinter w3-input w3-border ");
				a("name", "seasonWinter");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSeasonWinter', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_seasonWinter')); }, function() { addError($('#", classApiMethodMethod, "_seasonWinter')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getSeasonWinter() != null && getSeasonWinter())
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolSeason", pk, "SeasonWinter ").f().sx(htmSeasonWinter()).g("span");
			}
		}
	}

	public void htmSeasonWinter(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolSeasonSeasonWinter").f();
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

	//////////////////
	// seasonFuture //
	//////////////////

	/**	 The entity seasonFuture
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean seasonFuture;
	@JsonIgnore
	public Wrap<Boolean> seasonFutureWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("seasonFuture").o(seasonFuture);

	/**	<br/> The entity seasonFuture
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonFuture">Find the entity seasonFuture in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonFuture(Wrap<Boolean> c);

	public Boolean getSeasonFuture() {
		return seasonFuture;
	}

	public void setSeasonFuture(Boolean seasonFuture) {
		this.seasonFuture = seasonFuture;
		this.seasonFutureWrap.alreadyInitialized = true;
	}
	public void setSeasonFuture(String o) {
		this.seasonFuture = SchoolSeason.staticSetSeasonFuture(siteRequest_, o);
		this.seasonFutureWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetSeasonFuture(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrSeasonFuture(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrSeasonFuture(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonFuture(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSeasonFuture(siteRequest_, SchoolSeason.staticSolrSeasonFuture(siteRequest_, SchoolSeason.staticSetSeasonFuture(siteRequest_, o)));
	}

	public Boolean solrSeasonFuture() {
		return SchoolSeason.staticSolrSeasonFuture(siteRequest_, seasonFuture);
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_seasonFuture")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_seasonFuture");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSeasonFuture classSchoolSeason inputSchoolSeason", pk, "SeasonFuture w3-input w3-border ");
				a("name", "setSeasonFuture");
			} else {
				a("class", "valueSeasonFuture classSchoolSeason inputSchoolSeason", pk, "SeasonFuture w3-input w3-border ");
				a("name", "seasonFuture");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSeasonFuture', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_seasonFuture')); }, function() { addError($('#", classApiMethodMethod, "_seasonFuture')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getSeasonFuture() != null && getSeasonFuture())
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
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
				e("span").a("class", "varSchoolSeason", pk, "SeasonFuture ").f().sx(htmSeasonFuture()).g("span");
			}
		}
	}

	public void htmSeasonFuture(String classApiMethodMethod) {
		SchoolSeason s = (SchoolSeason)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolSeasonSeasonFuture").f();
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

	/**	 The entity seasonShortName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String seasonShortName;
	@JsonIgnore
	public Wrap<String> seasonShortNameWrap = new Wrap<String>().p(this).c(String.class).var("seasonShortName").o(seasonShortName);

	/**	<br/> The entity seasonShortName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonShortName">Find the entity seasonShortName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonShortName(Wrap<String> c);

	public String getSeasonShortName() {
		return seasonShortName;
	}
	public void setSeasonShortName(String o) {
		this.seasonShortName = SchoolSeason.staticSetSeasonShortName(siteRequest_, o);
		this.seasonShortNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSeasonShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSeasonShortName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSeasonShortName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonShortName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSeasonShortName(siteRequest_, SchoolSeason.staticSolrSeasonShortName(siteRequest_, SchoolSeason.staticSetSeasonShortName(siteRequest_, o)));
	}

	public String solrSeasonShortName() {
		return SchoolSeason.staticSolrSeasonShortName(siteRequest_, seasonShortName);
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

	/**	 The entity seasonCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String seasonCompleteName;
	@JsonIgnore
	public Wrap<String> seasonCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("seasonCompleteName").o(seasonCompleteName);

	/**	<br/> The entity seasonCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonCompleteName">Find the entity seasonCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonCompleteName(Wrap<String> c);

	public String getSeasonCompleteName() {
		return seasonCompleteName;
	}
	public void setSeasonCompleteName(String o) {
		this.seasonCompleteName = SchoolSeason.staticSetSeasonCompleteName(siteRequest_, o);
		this.seasonCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSeasonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
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

	public static String staticSolrSeasonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSeasonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolSeason.staticSolrStrSeasonCompleteName(siteRequest_, SchoolSeason.staticSolrSeasonCompleteName(siteRequest_, SchoolSeason.staticSetSeasonCompleteName(siteRequest_, o)));
	}

	public String solrSeasonCompleteName() {
		return SchoolSeason.staticSolrSeasonCompleteName(siteRequest_, seasonCompleteName);
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
		yearKeyInit();
		sessionKeysInit();
		educationSortInit();
		schoolSortInit();
		yearSortInit();
		seasonSortInit();
		yearSearchInit();
		year_Init();
		schoolKeyInit();
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
		yearEnrollmentFeeInit();
		seasonStartDateInit();
		seasonSummerInit();
		seasonWinterInit();
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
			case "yearKey":
				return oSchoolSeason.yearKey;
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
			case "schoolForm":
				return oSchoolSeason.schoolForm;
			case "schoolNumber":
				return oSchoolSeason.schoolNumber;
			case "schoolAdministratorName":
				return oSchoolSeason.schoolAdministratorName;
			case "yearStart":
				return oSchoolSeason.yearStart;
			case "yearEnd":
				return oSchoolSeason.yearEnd;
			case "yearEnrollmentFee":
				return oSchoolSeason.yearEnrollmentFee;
			case "seasonStartDate":
				return oSchoolSeason.seasonStartDate;
			case "seasonSummer":
				return oSchoolSeason.seasonSummer;
			case "seasonWinter":
				return oSchoolSeason.seasonWinter;
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
			case "yearKey":
				if(oSchoolSeason.getYearKey() == null)
					oSchoolSeason.setYearKey((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "sessionKeys":
				oSchoolSeason.addSessionKeys((Long)val);
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
		return staticSetSchoolSeason(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSchoolSeason(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "seasonKey":
			return SchoolSeason.staticSetSeasonKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolSeason.staticSetEnrollmentKeys(siteRequest_, o);
		case "yearKey":
			return SchoolSeason.staticSetYearKey(siteRequest_, o);
		case "sessionKeys":
			return SchoolSeason.staticSetSessionKeys(siteRequest_, o);
		case "educationSort":
			return SchoolSeason.staticSetEducationSort(siteRequest_, o);
		case "schoolSort":
			return SchoolSeason.staticSetSchoolSort(siteRequest_, o);
		case "yearSort":
			return SchoolSeason.staticSetYearSort(siteRequest_, o);
		case "seasonSort":
			return SchoolSeason.staticSetSeasonSort(siteRequest_, o);
		case "schoolKey":
			return SchoolSeason.staticSetSchoolKey(siteRequest_, o);
		case "schoolName":
			return SchoolSeason.staticSetSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return SchoolSeason.staticSetSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return SchoolSeason.staticSetSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return SchoolSeason.staticSetSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return SchoolSeason.staticSetSchoolPhoneNumber(siteRequest_, o);
		case "schoolForm":
			return SchoolSeason.staticSetSchoolForm(siteRequest_, o);
		case "schoolNumber":
			return SchoolSeason.staticSetSchoolNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return SchoolSeason.staticSetSchoolAdministratorName(siteRequest_, o);
		case "yearStart":
			return SchoolSeason.staticSetYearStart(siteRequest_, o);
		case "yearEnd":
			return SchoolSeason.staticSetYearEnd(siteRequest_, o);
		case "yearEnrollmentFee":
			return SchoolSeason.staticSetYearEnrollmentFee(siteRequest_, o);
		case "seasonStartDate":
			return SchoolSeason.staticSetSeasonStartDate(siteRequest_, o);
		case "seasonSummer":
			return SchoolSeason.staticSetSeasonSummer(siteRequest_, o);
		case "seasonWinter":
			return SchoolSeason.staticSetSeasonWinter(siteRequest_, o);
		case "seasonFuture":
			return SchoolSeason.staticSetSeasonFuture(siteRequest_, o);
		case "seasonShortName":
			return SchoolSeason.staticSetSeasonShortName(siteRequest_, o);
		case "seasonCompleteName":
			return SchoolSeason.staticSetSeasonCompleteName(siteRequest_, o);
			default:
				return Cluster.staticSetCluster(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSchoolSeason(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSchoolSeason(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "seasonKey":
			return SchoolSeason.staticSolrSeasonKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolSeason.staticSolrEnrollmentKeys(siteRequest_, (Long)o);
		case "yearKey":
			return SchoolSeason.staticSolrYearKey(siteRequest_, (Long)o);
		case "sessionKeys":
			return SchoolSeason.staticSolrSessionKeys(siteRequest_, (Long)o);
		case "educationSort":
			return SchoolSeason.staticSolrEducationSort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolSeason.staticSolrSchoolSort(siteRequest_, (Integer)o);
		case "yearSort":
			return SchoolSeason.staticSolrYearSort(siteRequest_, (Integer)o);
		case "seasonSort":
			return SchoolSeason.staticSolrSeasonSort(siteRequest_, (Integer)o);
		case "schoolKey":
			return SchoolSeason.staticSolrSchoolKey(siteRequest_, (Long)o);
		case "schoolName":
			return SchoolSeason.staticSolrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return SchoolSeason.staticSolrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return SchoolSeason.staticSolrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return SchoolSeason.staticSolrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return SchoolSeason.staticSolrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolForm":
			return SchoolSeason.staticSolrSchoolForm(siteRequest_, (String)o);
		case "schoolNumber":
			return SchoolSeason.staticSolrSchoolNumber(siteRequest_, (Integer)o);
		case "schoolAdministratorName":
			return SchoolSeason.staticSolrSchoolAdministratorName(siteRequest_, (String)o);
		case "yearStart":
			return SchoolSeason.staticSolrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return SchoolSeason.staticSolrYearEnd(siteRequest_, (Integer)o);
		case "yearEnrollmentFee":
			return SchoolSeason.staticSolrYearEnrollmentFee(siteRequest_, (BigDecimal)o);
		case "seasonStartDate":
			return SchoolSeason.staticSolrSeasonStartDate(siteRequest_, (LocalDate)o);
		case "seasonSummer":
			return SchoolSeason.staticSolrSeasonSummer(siteRequest_, (Boolean)o);
		case "seasonWinter":
			return SchoolSeason.staticSolrSeasonWinter(siteRequest_, (Boolean)o);
		case "seasonFuture":
			return SchoolSeason.staticSolrSeasonFuture(siteRequest_, (Boolean)o);
		case "seasonShortName":
			return SchoolSeason.staticSolrSeasonShortName(siteRequest_, (String)o);
		case "seasonCompleteName":
			return SchoolSeason.staticSolrSeasonCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrCluster(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSchoolSeason(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSchoolSeason(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "seasonKey":
			return SchoolSeason.staticSolrStrSeasonKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolSeason.staticSolrStrEnrollmentKeys(siteRequest_, (Long)o);
		case "yearKey":
			return SchoolSeason.staticSolrStrYearKey(siteRequest_, (Long)o);
		case "sessionKeys":
			return SchoolSeason.staticSolrStrSessionKeys(siteRequest_, (Long)o);
		case "educationSort":
			return SchoolSeason.staticSolrStrEducationSort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolSeason.staticSolrStrSchoolSort(siteRequest_, (Integer)o);
		case "yearSort":
			return SchoolSeason.staticSolrStrYearSort(siteRequest_, (Integer)o);
		case "seasonSort":
			return SchoolSeason.staticSolrStrSeasonSort(siteRequest_, (Integer)o);
		case "schoolKey":
			return SchoolSeason.staticSolrStrSchoolKey(siteRequest_, (Long)o);
		case "schoolName":
			return SchoolSeason.staticSolrStrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return SchoolSeason.staticSolrStrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return SchoolSeason.staticSolrStrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return SchoolSeason.staticSolrStrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return SchoolSeason.staticSolrStrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolForm":
			return SchoolSeason.staticSolrStrSchoolForm(siteRequest_, (String)o);
		case "schoolNumber":
			return SchoolSeason.staticSolrStrSchoolNumber(siteRequest_, (Integer)o);
		case "schoolAdministratorName":
			return SchoolSeason.staticSolrStrSchoolAdministratorName(siteRequest_, (String)o);
		case "yearStart":
			return SchoolSeason.staticSolrStrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return SchoolSeason.staticSolrStrYearEnd(siteRequest_, (Integer)o);
		case "yearEnrollmentFee":
			return SchoolSeason.staticSolrStrYearEnrollmentFee(siteRequest_, (Double)o);
		case "seasonStartDate":
			return SchoolSeason.staticSolrStrSeasonStartDate(siteRequest_, (Date)o);
		case "seasonSummer":
			return SchoolSeason.staticSolrStrSeasonSummer(siteRequest_, (Boolean)o);
		case "seasonWinter":
			return SchoolSeason.staticSolrStrSeasonWinter(siteRequest_, (Boolean)o);
		case "seasonFuture":
			return SchoolSeason.staticSolrStrSeasonFuture(siteRequest_, (Boolean)o);
		case "seasonShortName":
			return SchoolSeason.staticSolrStrSeasonShortName(siteRequest_, (String)o);
		case "seasonCompleteName":
			return SchoolSeason.staticSolrStrSeasonCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSchoolSeason(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSchoolSeason(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "seasonKey":
			return SchoolSeason.staticSolrFqSeasonKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolSeason.staticSolrFqEnrollmentKeys(siteRequest_, o);
		case "yearKey":
			return SchoolSeason.staticSolrFqYearKey(siteRequest_, o);
		case "sessionKeys":
			return SchoolSeason.staticSolrFqSessionKeys(siteRequest_, o);
		case "educationSort":
			return SchoolSeason.staticSolrFqEducationSort(siteRequest_, o);
		case "schoolSort":
			return SchoolSeason.staticSolrFqSchoolSort(siteRequest_, o);
		case "yearSort":
			return SchoolSeason.staticSolrFqYearSort(siteRequest_, o);
		case "seasonSort":
			return SchoolSeason.staticSolrFqSeasonSort(siteRequest_, o);
		case "schoolKey":
			return SchoolSeason.staticSolrFqSchoolKey(siteRequest_, o);
		case "schoolName":
			return SchoolSeason.staticSolrFqSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return SchoolSeason.staticSolrFqSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return SchoolSeason.staticSolrFqSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return SchoolSeason.staticSolrFqSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return SchoolSeason.staticSolrFqSchoolPhoneNumber(siteRequest_, o);
		case "schoolForm":
			return SchoolSeason.staticSolrFqSchoolForm(siteRequest_, o);
		case "schoolNumber":
			return SchoolSeason.staticSolrFqSchoolNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return SchoolSeason.staticSolrFqSchoolAdministratorName(siteRequest_, o);
		case "yearStart":
			return SchoolSeason.staticSolrFqYearStart(siteRequest_, o);
		case "yearEnd":
			return SchoolSeason.staticSolrFqYearEnd(siteRequest_, o);
		case "yearEnrollmentFee":
			return SchoolSeason.staticSolrFqYearEnrollmentFee(siteRequest_, o);
		case "seasonStartDate":
			return SchoolSeason.staticSolrFqSeasonStartDate(siteRequest_, o);
		case "seasonSummer":
			return SchoolSeason.staticSolrFqSeasonSummer(siteRequest_, o);
		case "seasonWinter":
			return SchoolSeason.staticSolrFqSeasonWinter(siteRequest_, o);
		case "seasonFuture":
			return SchoolSeason.staticSolrFqSeasonFuture(siteRequest_, o);
		case "seasonShortName":
			return SchoolSeason.staticSolrFqSeasonShortName(siteRequest_, o);
		case "seasonCompleteName":
			return SchoolSeason.staticSolrFqSeasonCompleteName(siteRequest_, o);
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
				if(val != null)
					setSeasonStartDate(val);
				saves.add(var);
				return val;
			case "seasonSummer":
				if(val != null)
					setSeasonSummer(val);
				saves.add(var);
				return val;
			case "seasonWinter":
				if(val != null)
					setSeasonWinter(val);
				saves.add(var);
				return val;
			case "seasonFuture":
				if(val != null)
					setSeasonFuture(val);
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
		populateSchoolSeason(solrDocument);
	}
	public void populateSchoolSeason(SolrDocument solrDocument) {
		SchoolSeason oSchoolSeason = (SchoolSeason)this;
		saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null) {

			if(saves.contains("seasonKey")) {
				Long seasonKey = (Long)solrDocument.get("seasonKey_stored_long");
				if(seasonKey != null)
					oSchoolSeason.setSeasonKey(seasonKey);
			}

			if(saves.contains("enrollmentKeys")) {
				List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
				if(enrollmentKeys != null)
					oSchoolSeason.enrollmentKeys.addAll(enrollmentKeys);
			}

			Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
			if(yearKey != null)
				oSchoolSeason.setYearKey(yearKey);

			List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
			if(sessionKeys != null)
				oSchoolSeason.sessionKeys.addAll(sessionKeys);

			if(saves.contains("educationSort")) {
				Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
				if(educationSort != null)
					oSchoolSeason.setEducationSort(educationSort);
			}

			if(saves.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolSeason.setSchoolSort(schoolSort);
			}

			if(saves.contains("yearSort")) {
				Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
				if(yearSort != null)
					oSchoolSeason.setYearSort(yearSort);
			}

			if(saves.contains("seasonSort")) {
				Integer seasonSort = (Integer)solrDocument.get("seasonSort_stored_int");
				if(seasonSort != null)
					oSchoolSeason.setSeasonSort(seasonSort);
			}

			if(saves.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oSchoolSeason.setSchoolKey(schoolKey);
			}

			if(saves.contains("schoolName")) {
				String schoolName = (String)solrDocument.get("schoolName_stored_string");
				if(schoolName != null)
					oSchoolSeason.setSchoolName(schoolName);
			}

			if(saves.contains("schoolCompleteName")) {
				String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
				if(schoolCompleteName != null)
					oSchoolSeason.setSchoolCompleteName(schoolCompleteName);
			}

			if(saves.contains("schoolLocation")) {
				String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
				if(schoolLocation != null)
					oSchoolSeason.setSchoolLocation(schoolLocation);
			}

			if(saves.contains("schoolAddress")) {
				String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
				if(schoolAddress != null)
					oSchoolSeason.setSchoolAddress(schoolAddress);
			}

			if(saves.contains("schoolPhoneNumber")) {
				String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
				if(schoolPhoneNumber != null)
					oSchoolSeason.setSchoolPhoneNumber(schoolPhoneNumber);
			}

			if(saves.contains("schoolForm")) {
				String schoolForm = (String)solrDocument.get("schoolForm_stored_string");
				if(schoolForm != null)
					oSchoolSeason.setSchoolForm(schoolForm);
			}

			if(saves.contains("schoolNumber")) {
				Integer schoolNumber = (Integer)solrDocument.get("schoolNumber_stored_int");
				if(schoolNumber != null)
					oSchoolSeason.setSchoolNumber(schoolNumber);
			}

			if(saves.contains("schoolAdministratorName")) {
				String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
				if(schoolAdministratorName != null)
					oSchoolSeason.setSchoolAdministratorName(schoolAdministratorName);
			}

			if(saves.contains("yearStart")) {
				Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
				if(yearStart != null)
					oSchoolSeason.setYearStart(yearStart);
			}

			if(saves.contains("yearEnd")) {
				Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
				if(yearEnd != null)
					oSchoolSeason.setYearEnd(yearEnd);
			}

			if(saves.contains("yearEnrollmentFee")) {
				Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
				if(yearEnrollmentFee != null)
					oSchoolSeason.setYearEnrollmentFee(yearEnrollmentFee);
			}

			if(saves.contains("seasonStartDate")) {
				Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
				if(seasonStartDate != null)
					oSchoolSeason.setSeasonStartDate(seasonStartDate);
			}

			if(saves.contains("seasonSummer")) {
				Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
				if(seasonSummer != null)
					oSchoolSeason.setSeasonSummer(seasonSummer);
			}

			if(saves.contains("seasonWinter")) {
				Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
				if(seasonWinter != null)
					oSchoolSeason.setSeasonWinter(seasonWinter);
			}

			if(saves.contains("seasonFuture")) {
				Boolean seasonFuture = (Boolean)solrDocument.get("seasonFuture_stored_boolean");
				if(seasonFuture != null)
					oSchoolSeason.setSeasonFuture(seasonFuture);
			}

			if(saves.contains("seasonShortName")) {
				String seasonShortName = (String)solrDocument.get("seasonShortName_stored_string");
				if(seasonShortName != null)
					oSchoolSeason.setSeasonShortName(seasonShortName);
			}

			if(saves.contains("seasonCompleteName")) {
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
		if(yearKey != null) {
			document.addField("yearKey_indexed_long", yearKey);
			document.addField("yearKey_stored_long", yearKey);
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
		if(yearEnrollmentFee != null) {
			document.addField("yearEnrollmentFee_indexed_double", yearEnrollmentFee.doubleValue());
			document.addField("yearEnrollmentFee_stored_double", yearEnrollmentFee.doubleValue());
		}
		if(seasonStartDate != null) {
			document.addField("seasonStartDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("seasonStartDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(seasonStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(seasonSummer != null) {
			document.addField("seasonSummer_indexed_boolean", seasonSummer);
			document.addField("seasonSummer_stored_boolean", seasonSummer);
		}
		if(seasonWinter != null) {
			document.addField("seasonWinter_indexed_boolean", seasonWinter);
			document.addField("seasonWinter_stored_boolean", seasonWinter);
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
			case "yearKey":
				return "yearKey_indexed_long";
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
			case "yearEnrollmentFee":
				return "yearEnrollmentFee_indexed_double";
			case "seasonStartDate":
				return "seasonStartDate_indexed_date";
			case "seasonSummer":
				return "seasonSummer_indexed_boolean";
			case "seasonWinter":
				return "seasonWinter_indexed_boolean";
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

	public static String varSuggestedSchoolSeason(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestedCluster(entityVar);
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

		Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
		if(yearKey != null)
			oSchoolSeason.setYearKey(yearKey);

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

		String schoolForm = (String)solrDocument.get("schoolForm_stored_string");
		if(schoolForm != null)
			oSchoolSeason.setSchoolForm(schoolForm);

		Integer schoolNumber = (Integer)solrDocument.get("schoolNumber_stored_int");
		if(schoolNumber != null)
			oSchoolSeason.setSchoolNumber(schoolNumber);

		String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
		if(schoolAdministratorName != null)
			oSchoolSeason.setSchoolAdministratorName(schoolAdministratorName);

		Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
		if(yearStart != null)
			oSchoolSeason.setYearStart(yearStart);

		Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
		if(yearEnd != null)
			oSchoolSeason.setYearEnd(yearEnd);

		Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
		if(yearEnrollmentFee != null)
			oSchoolSeason.setYearEnrollmentFee(yearEnrollmentFee);

		Date seasonStartDate = (Date)solrDocument.get("seasonStartDate_stored_date");
		if(seasonStartDate != null)
			oSchoolSeason.setSeasonStartDate(seasonStartDate);

		Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
		if(seasonSummer != null)
			oSchoolSeason.setSeasonSummer(seasonSummer);

		Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
		if(seasonWinter != null)
			oSchoolSeason.setSeasonWinter(seasonWinter);

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
	// apiRequest //
	//////////////////

	public void apiRequestSchoolSeason() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SchoolSeason) {
			SchoolSeason original = (SchoolSeason)o;
			if(!Objects.equals(seasonKey, original.getSeasonKey()))
				apiRequest.addVars("seasonKey");
			if(!Objects.equals(enrollmentKeys, original.getEnrollmentKeys()))
				apiRequest.addVars("enrollmentKeys");
			if(!Objects.equals(yearKey, original.getYearKey()))
				apiRequest.addVars("yearKey");
			if(!Objects.equals(sessionKeys, original.getSessionKeys()))
				apiRequest.addVars("sessionKeys");
			if(!Objects.equals(educationSort, original.getEducationSort()))
				apiRequest.addVars("educationSort");
			if(!Objects.equals(schoolSort, original.getSchoolSort()))
				apiRequest.addVars("schoolSort");
			if(!Objects.equals(yearSort, original.getYearSort()))
				apiRequest.addVars("yearSort");
			if(!Objects.equals(seasonSort, original.getSeasonSort()))
				apiRequest.addVars("seasonSort");
			if(!Objects.equals(schoolKey, original.getSchoolKey()))
				apiRequest.addVars("schoolKey");
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
			if(!Objects.equals(yearEnrollmentFee, original.getYearEnrollmentFee()))
				apiRequest.addVars("yearEnrollmentFee");
			if(!Objects.equals(seasonStartDate, original.getSeasonStartDate()))
				apiRequest.addVars("seasonStartDate");
			if(!Objects.equals(seasonSummer, original.getSeasonSummer()))
				apiRequest.addVars("seasonSummer");
			if(!Objects.equals(seasonWinter, original.getSeasonWinter()))
				apiRequest.addVars("seasonWinter");
			if(!Objects.equals(seasonFuture, original.getSeasonFuture()))
				apiRequest.addVars("seasonFuture");
			if(!Objects.equals(seasonShortName, original.getSeasonShortName()))
				apiRequest.addVars("seasonShortName");
			if(!Objects.equals(seasonCompleteName, original.getSeasonCompleteName()))
				apiRequest.addVars("seasonCompleteName");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), seasonKey, enrollmentKeys, yearKey, sessionKeys, educationSort, schoolSort, yearSort, seasonSort, schoolKey, schoolName, schoolCompleteName, schoolLocation, schoolAddress, schoolPhoneNumber, schoolForm, schoolNumber, schoolAdministratorName, yearStart, yearEnd, yearEnrollmentFee, seasonStartDate, seasonSummer, seasonWinter, seasonFuture, seasonShortName, seasonCompleteName);
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
				&& Objects.equals( seasonKey, that.seasonKey )
				&& Objects.equals( enrollmentKeys, that.enrollmentKeys )
				&& Objects.equals( yearKey, that.yearKey )
				&& Objects.equals( sessionKeys, that.sessionKeys )
				&& Objects.equals( educationSort, that.educationSort )
				&& Objects.equals( schoolSort, that.schoolSort )
				&& Objects.equals( yearSort, that.yearSort )
				&& Objects.equals( seasonSort, that.seasonSort )
				&& Objects.equals( schoolKey, that.schoolKey )
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
				&& Objects.equals( yearEnrollmentFee, that.yearEnrollmentFee )
				&& Objects.equals( seasonStartDate, that.seasonStartDate )
				&& Objects.equals( seasonSummer, that.seasonSummer )
				&& Objects.equals( seasonWinter, that.seasonWinter )
				&& Objects.equals( seasonFuture, that.seasonFuture )
				&& Objects.equals( seasonShortName, that.seasonShortName )
				&& Objects.equals( seasonCompleteName, that.seasonCompleteName );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolSeason { ");
		sb.append( "seasonKey: " ).append(seasonKey);
		sb.append( ", enrollmentKeys: " ).append(enrollmentKeys);
		sb.append( ", yearKey: " ).append(yearKey);
		sb.append( ", sessionKeys: " ).append(sessionKeys);
		sb.append( ", educationSort: " ).append(educationSort);
		sb.append( ", schoolSort: " ).append(schoolSort);
		sb.append( ", yearSort: " ).append(yearSort);
		sb.append( ", seasonSort: " ).append(seasonSort);
		sb.append( ", schoolKey: " ).append(schoolKey);
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
		sb.append( ", yearEnrollmentFee: " ).append(yearEnrollmentFee);
		sb.append( ", seasonStartDate: " ).append(seasonStartDate);
		sb.append( ", seasonSummer: " ).append(seasonSummer);
		sb.append( ", seasonWinter: " ).append(seasonWinter);
		sb.append( ", seasonFuture: " ).append(seasonFuture);
		sb.append( ", seasonShortName: \"" ).append(seasonShortName).append( "\"" );
		sb.append( ", seasonCompleteName: \"" ).append(seasonCompleteName).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
