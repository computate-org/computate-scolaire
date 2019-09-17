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

	public void htmSchoolKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SchoolKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SchoolKey() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSchoolKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolKey\"");
							r.s(" value=\"", htmSchoolKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolKey());
			}
			r.l("</div>");
		}
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

	public void htmYearKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "YearKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "YearKey() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setYearKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearKey\"");
							r.s(" value=\"", htmYearKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearKey());
			}
			r.l("</div>");
		}
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

	public void htmSeasonKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SeasonKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SeasonKey() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSeasonKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonKey\"");
							r.s(" value=\"", htmSeasonKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonKey());
			}
			r.l("</div>");
		}
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

	public void htmEnrollmentKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "EnrollmentKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "EnrollmentKeys() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setEnrollmentKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentKeys\"");
							r.s(" value=\"", htmEnrollmentKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentKeys());
			}
			r.l("</div>");
		}
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

	public void htmSessionKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SessionKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SessionKeys() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSessionKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionKeys\"");
							r.s(" value=\"", htmSessionKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionKeys());
			}
			r.l("</div>");
		}
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

	public void htmEducationSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "EducationSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "EducationSort() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setEducationSort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEducationSort()), "</span>");
				r.s("			<input");
							r.s(" name=\"educationSort\"");
							r.s(" value=\"", htmEducationSort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEducationSort());
			}
			r.l("</div>");
		}
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

	public void htmSchoolSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SchoolSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SchoolSort() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSchoolSort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolSort()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolSort\"");
							r.s(" value=\"", htmSchoolSort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolSort());
			}
			r.l("</div>");
		}
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

	public void htmYearSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "YearSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "YearSort() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setYearSort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearSort()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearSort\"");
							r.s(" value=\"", htmYearSort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearSort());
			}
			r.l("</div>");
		}
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

	public void htmSeasonSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SeasonSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SeasonSort() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSeasonSort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonSort()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonSort\"");
							r.s(" value=\"", htmSeasonSort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonSort());
			}
			r.l("</div>");
		}
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

	////////////////////////
	// schoolNameComplete //
	////////////////////////

	/**	L'entité « schoolNameComplete »
	 *	 is defined as null before being initialized. 
	 */
	protected String schoolNameComplete;
	@JsonIgnore
	public Wrap<String> schoolNameCompleteWrap = new Wrap<String>().p(this).c(String.class).var("schoolNameComplete").o(schoolNameComplete);

	/**	<br/>L'entité « schoolNameComplete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolNameComplete">Trouver l'entité schoolNameComplete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolNameComplete(Wrap<String> c);

	public String getSchoolNameComplete() {
		return schoolNameComplete;
	}

	public void setSchoolNameComplete(String schoolNameComplete) {
		this.schoolNameComplete = schoolNameComplete;
		this.schoolNameCompleteWrap.alreadyInitialized = true;
	}
	protected SchoolSeason schoolNameCompleteInit() {
		if(!schoolNameCompleteWrap.alreadyInitialized) {
			_schoolNameComplete(schoolNameCompleteWrap);
			if(schoolNameComplete == null)
				setSchoolNameComplete(schoolNameCompleteWrap.o);
		}
		schoolNameCompleteWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public String solrSchoolNameComplete() {
		return schoolNameComplete;
	}

	public String strSchoolNameComplete() {
		return schoolNameComplete == null ? "" : schoolNameComplete;
	}

	public String jsonSchoolNameComplete() {
		return schoolNameComplete == null ? "" : schoolNameComplete;
	}

	public String nomAffichageSchoolNameComplete() {
		return "r: EcoleNomComplet";
	}

	public String htmTooltipSchoolNameComplete() {
		return null;
	}

	public String htmSchoolNameComplete() {
		return schoolNameComplete == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolNameComplete());
	}

	public void htmSchoolNameComplete(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SchoolNameComplete\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SchoolNameComplete() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSchoolNameComplete\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolNameComplete()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolNameComplete\"");
							r.s(" value=\"", htmSchoolNameComplete(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolNameComplete());
			}
			r.l("</div>");
		}
	}

	///////////////
	// yearStart //
	///////////////

	/**	L'entité « yearStart »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate yearStart;
	@JsonIgnore
	public Wrap<LocalDate> yearStartWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("yearStart").o(yearStart);

	/**	<br/>L'entité « yearStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Trouver l'entité yearStart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearStart(Wrap<LocalDate> c);

	public LocalDate getYearStart() {
		return yearStart;
	}

	public void setYearStart(LocalDate yearStart) {
		this.yearStart = yearStart;
		this.yearStartWrap.alreadyInitialized = true;
	}
	public SchoolSeason setYearStart(Instant o) {
		this.yearStart = LocalDate.from(o);
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolSeason setYearStart(String o) {
		this.yearStart = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	public SchoolSeason setYearStart(Date o) {
		this.yearStart = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
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

	public Date solrYearStart() {
		return yearStart == null ? null : Date.from(yearStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strYearStart() {
		return yearStart == null ? "" : yearStart.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonYearStart() {
		return yearStart == null ? "" : yearStart.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
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

	public void htmYearStart(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "YearStart\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "YearStart() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setYearStart\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearStart()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearStart\"");
							r.s(" value=\"", htmYearStart(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearStart());
			}
			r.l("</div>");
		}
	}

	/////////////
	// yearEnd //
	/////////////

	/**	L'entité « yearEnd »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate yearEnd;
	@JsonIgnore
	public Wrap<LocalDate> yearEndWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("yearEnd").o(yearEnd);

	/**	<br/>L'entité « yearEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Trouver l'entité yearEnd dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearEnd(Wrap<LocalDate> c);

	public LocalDate getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(LocalDate yearEnd) {
		this.yearEnd = yearEnd;
		this.yearEndWrap.alreadyInitialized = true;
	}
	public SchoolSeason setYearEnd(Instant o) {
		this.yearEnd = LocalDate.from(o);
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolSeason setYearEnd(String o) {
		this.yearEnd = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	public SchoolSeason setYearEnd(Date o) {
		this.yearEnd = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
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

	public Date solrYearEnd() {
		return yearEnd == null ? null : Date.from(yearEnd.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strYearEnd() {
		return yearEnd == null ? "" : yearEnd.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonYearEnd() {
		return yearEnd == null ? "" : yearEnd.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
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

	public void htmYearEnd(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "YearEnd\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "YearEnd() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setYearEnd\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearEnd()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearEnd\"");
							r.s(" value=\"", htmYearEnd(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearEnd());
			}
			r.l("</div>");
		}
	}

	////////////////////
	// seasonStartDay //
	////////////////////

	/**	L'entité « seasonStartDay »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate seasonStartDay;
	@JsonIgnore
	public Wrap<LocalDate> seasonStartDayWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("seasonStartDay").o(seasonStartDay);

	/**	<br/>L'entité « seasonStartDay »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDay">Trouver l'entité seasonStartDay dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonStartDay(Wrap<LocalDate> c);

	public LocalDate getSeasonStartDay() {
		return seasonStartDay;
	}

	public void setSeasonStartDay(LocalDate seasonStartDay) {
		this.seasonStartDay = seasonStartDay;
		this.seasonStartDayWrap.alreadyInitialized = true;
	}
	public SchoolSeason setSeasonStartDay(Instant o) {
		this.seasonStartDay = LocalDate.from(o);
		this.seasonStartDayWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolSeason setSeasonStartDay(String o) {
		this.seasonStartDay = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.seasonStartDayWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	public SchoolSeason setSeasonStartDay(Date o) {
		this.seasonStartDay = o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDayWrap.alreadyInitialized = true;
		return (SchoolSeason)this;
	}
	protected SchoolSeason seasonStartDayInit() {
		if(!seasonStartDayWrap.alreadyInitialized) {
			_seasonStartDay(seasonStartDayWrap);
			if(seasonStartDay == null)
				setSeasonStartDay(seasonStartDayWrap.o);
		}
		seasonStartDayWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public Date solrSeasonStartDay() {
		return seasonStartDay == null ? null : Date.from(seasonStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSeasonStartDay() {
		return seasonStartDay == null ? "" : seasonStartDay.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.US));
	}

	public String jsonSeasonStartDay() {
		return seasonStartDay == null ? "" : seasonStartDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US));
	}

	public String nomAffichageSeasonStartDay() {
		return "start of the season";
	}

	public String htmTooltipSeasonStartDay() {
		return null;
	}

	public String htmSeasonStartDay() {
		return seasonStartDay == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonStartDay());
	}

	public void htmSeasonStartDay(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SeasonStartDay\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SeasonStartDay() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSeasonStartDay\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonStartDay()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonStartDay\"");
							r.s(" value=\"", htmSeasonStartDay(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonStartDay());
			}
			r.l("</div>");
		}
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

	public void htmSeasonSummer(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SeasonSummer\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SeasonSummer() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSeasonSummer\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonSummer()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonSummer\"");
							r.s(" value=\"", htmSeasonSummer(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonSummer());
			}
			r.l("</div>");
		}
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

	public void htmSeasonWinter(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SeasonWinter\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SeasonWinter() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSeasonWinter\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonWinter()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonWinter\"");
							r.s(" value=\"", htmSeasonWinter(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonWinter());
			}
			r.l("</div>");
		}
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

	public void htmSeasonEnrollmentFee(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SeasonEnrollmentFee\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SeasonEnrollmentFee() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSeasonEnrollmentFee\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonEnrollmentFee()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonEnrollmentFee\"");
							r.s(" value=\"", htmSeasonEnrollmentFee(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonEnrollmentFee());
			}
			r.l("</div>");
		}
	}

	////////////////////////
	// seasonNameComplete //
	////////////////////////

	/**	L'entité « seasonNameComplete »
	 *	 is defined as null before being initialized. 
	 */
	protected String seasonNameComplete;
	@JsonIgnore
	public Wrap<String> seasonNameCompleteWrap = new Wrap<String>().p(this).c(String.class).var("seasonNameComplete").o(seasonNameComplete);

	/**	<br/>L'entité « seasonNameComplete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonNameComplete">Trouver l'entité seasonNameComplete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonNameComplete(Wrap<String> c);

	public String getSeasonNameComplete() {
		return seasonNameComplete;
	}

	public void setSeasonNameComplete(String seasonNameComplete) {
		this.seasonNameComplete = seasonNameComplete;
		this.seasonNameCompleteWrap.alreadyInitialized = true;
	}
	protected SchoolSeason seasonNameCompleteInit() {
		if(!seasonNameCompleteWrap.alreadyInitialized) {
			_seasonNameComplete(seasonNameCompleteWrap);
			if(seasonNameComplete == null)
				setSeasonNameComplete(seasonNameCompleteWrap.o);
		}
		seasonNameCompleteWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public String solrSeasonNameComplete() {
		return seasonNameComplete;
	}

	public String strSeasonNameComplete() {
		return seasonNameComplete == null ? "" : seasonNameComplete;
	}

	public String jsonSeasonNameComplete() {
		return seasonNameComplete == null ? "" : seasonNameComplete;
	}

	public String nomAffichageSeasonNameComplete() {
		return null;
	}

	public String htmTooltipSeasonNameComplete() {
		return null;
	}

	public String htmSeasonNameComplete() {
		return seasonNameComplete == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonNameComplete());
	}

	public void htmSeasonNameComplete(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SeasonNameComplete\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SeasonNameComplete() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSeasonNameComplete\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonNameComplete()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonNameComplete\"");
							r.s(" value=\"", htmSeasonNameComplete(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonNameComplete());
			}
			r.l("</div>");
		}
	}

	//////////////
	// seasonId //
	//////////////

	/**	L'entité « seasonId »
	 *	 is defined as null before being initialized. 
	 */
	protected String seasonId;
	@JsonIgnore
	public Wrap<String> seasonIdWrap = new Wrap<String>().p(this).c(String.class).var("seasonId").o(seasonId);

	/**	<br/>L'entité « seasonId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonId">Trouver l'entité seasonId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _seasonId(Wrap<String> c);

	public String getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
		this.seasonIdWrap.alreadyInitialized = true;
	}
	protected SchoolSeason seasonIdInit() {
		if(!seasonIdWrap.alreadyInitialized) {
			_seasonId(seasonIdWrap);
			if(seasonId == null)
				setSeasonId(seasonIdWrap.o);
		}
		seasonIdWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public String solrSeasonId() {
		return seasonId;
	}

	public String strSeasonId() {
		return seasonId == null ? "" : seasonId;
	}

	public String jsonSeasonId() {
		return seasonId == null ? "" : seasonId;
	}

	public String nomAffichageSeasonId() {
		return "ID";
	}

	public String htmTooltipSeasonId() {
		return null;
	}

	public String htmSeasonId() {
		return seasonId == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonId());
	}

	public void htmSeasonId(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "SeasonId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "SeasonId() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setSeasonId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonId()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonId\"");
							r.s(" value=\"", htmSeasonId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonId());
			}
			r.l("</div>");
		}
	}

	/////////////
	// pageUrl //
	/////////////

	/**	L'entité « pageUrl »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUrl;
	@JsonIgnore
	public Wrap<String> pageUrlWrap = new Wrap<String>().p(this).c(String.class).var("pageUrl").o(pageUrl);

	/**	<br/>L'entité « pageUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUrl(Wrap<String> c);

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
		this.pageUrlWrap.alreadyInitialized = true;
	}
	protected SchoolSeason pageUrlInit() {
		if(!pageUrlWrap.alreadyInitialized) {
			_pageUrl(pageUrlWrap);
			if(pageUrl == null)
				setPageUrl(pageUrlWrap.o);
		}
		pageUrlWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public String solrPageUrl() {
		return pageUrl;
	}

	public String strPageUrl() {
		return pageUrl == null ? "" : pageUrl;
	}

	public String jsonPageUrl() {
		return pageUrl == null ? "" : pageUrl;
	}

	public String nomAffichagePageUrl() {
		return null;
	}

	public String htmTooltipPageUrl() {
		return null;
	}

	public String htmPageUrl() {
		return pageUrl == null ? "" : StringEscapeUtils.escapeHtml4(strPageUrl());
	}

	public void htmPageUrl(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "PageUrl\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "PageUrl() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setPageUrl\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePageUrl()), "</span>");
				r.s("			<input");
							r.s(" name=\"pageUrl\"");
							r.s(" value=\"", htmPageUrl(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPageUrl());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// objectSuggest //
	///////////////////

	/**	L'entité « objectSuggest »
	 *	 is defined as null before being initialized. 
	 */
	protected String objectSuggest;
	@JsonIgnore
	public Wrap<String> objectSuggestWrap = new Wrap<String>().p(this).c(String.class).var("objectSuggest").o(objectSuggest);

	/**	<br/>L'entité « objectSuggest »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SchoolSeason&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objectSuggest">Trouver l'entité objectSuggest dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objectSuggest(Wrap<String> c);

	public String getObjectSuggest() {
		return objectSuggest;
	}

	public void setObjectSuggest(String objectSuggest) {
		this.objectSuggest = objectSuggest;
		this.objectSuggestWrap.alreadyInitialized = true;
	}
	protected SchoolSeason objectSuggestInit() {
		if(!objectSuggestWrap.alreadyInitialized) {
			_objectSuggest(objectSuggestWrap);
			if(objectSuggest == null)
				setObjectSuggest(objectSuggestWrap.o);
		}
		objectSuggestWrap.alreadyInitialized(true);
		return (SchoolSeason)this;
	}

	public String solrObjectSuggest() {
		return objectSuggest;
	}

	public String strObjectSuggest() {
		return objectSuggest == null ? "" : objectSuggest;
	}

	public String jsonObjectSuggest() {
		return objectSuggest == null ? "" : objectSuggest;
	}

	public String nomAffichageObjectSuggest() {
		return null;
	}

	public String htmTooltipObjectSuggest() {
		return null;
	}

	public String htmObjectSuggest() {
		return objectSuggest == null ? "" : StringEscapeUtils.escapeHtml4(strObjectSuggest());
	}

	public void htmObjectSuggest(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolSeason", strPk(), "ObjectSuggest\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolSeason", strPk(), "ObjectSuggest() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setObjectSuggest\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageObjectSuggest()), "</span>");
				r.s("			<input");
							r.s(" name=\"objectSuggest\"");
							r.s(" value=\"", htmObjectSuggest(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmObjectSuggest());
			}
			r.l("</div>");
		}
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
		super.initDeepCluster(siteRequest_);
		initSchoolSeason();
	}

	public void initSchoolSeason() {
		schoolKeyInit();
		yearKeyInit();
		seasonKeyInit();
		enrollmentKeysInit();
		sessionKeysInit();
		educationSortInit();
		schoolSortInit();
		yearSortInit();
		seasonSortInit();
		yearSearchInit();
		year_Init();
		schoolNameCompleteInit();
		yearStartInit();
		yearEndInit();
		seasonStartDayInit();
		seasonSummerInit();
		seasonWinterInit();
		seasonEnrollmentFeeInit();
		seasonNameCompleteInit();
		seasonIdInit();
		pageUrlInit();
		objectSuggestInit();
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
			case "schoolKey":
				return oSchoolSeason.schoolKey;
			case "yearKey":
				return oSchoolSeason.yearKey;
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
			case "yearSearch":
				return oSchoolSeason.yearSearch;
			case "year_":
				return oSchoolSeason.year_;
			case "schoolNameComplete":
				return oSchoolSeason.schoolNameComplete;
			case "yearStart":
				return oSchoolSeason.yearStart;
			case "yearEnd":
				return oSchoolSeason.yearEnd;
			case "seasonStartDay":
				return oSchoolSeason.seasonStartDay;
			case "seasonSummer":
				return oSchoolSeason.seasonSummer;
			case "seasonWinter":
				return oSchoolSeason.seasonWinter;
			case "seasonEnrollmentFee":
				return oSchoolSeason.seasonEnrollmentFee;
			case "seasonNameComplete":
				return oSchoolSeason.seasonNameComplete;
			case "seasonId":
				return oSchoolSeason.seasonId;
			case "pageUrl":
				return oSchoolSeason.pageUrl;
			case "objectSuggest":
				return oSchoolSeason.objectSuggest;
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
				oSchoolSeason.setYearKey((Long)val);
				return val;
			case "sessionKeys":
				oSchoolSeason.addSessionKeys((Long)val);
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
			case "seasonStartDay":
				setSeasonStartDay(val);
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

			if(savesSchoolSeason.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oSchoolSeason.setSchoolKey(schoolKey);
			}

			Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
			if(yearKey != null)
				oSchoolSeason.setYearKey(yearKey);

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

			if(savesSchoolSeason.contains("schoolNameComplete")) {
				String schoolNameComplete = (String)solrDocument.get("schoolNameComplete_stored_string");
				if(schoolNameComplete != null)
					oSchoolSeason.setSchoolNameComplete(schoolNameComplete);
			}

			if(savesSchoolSeason.contains("yearStart")) {
				Date yearStart = (Date)solrDocument.get("yearStart_stored_date");
				if(yearStart != null)
					oSchoolSeason.setYearStart(yearStart);
			}

			if(savesSchoolSeason.contains("yearEnd")) {
				Date yearEnd = (Date)solrDocument.get("yearEnd_stored_date");
				if(yearEnd != null)
					oSchoolSeason.setYearEnd(yearEnd);
			}

			if(savesSchoolSeason.contains("seasonStartDay")) {
				Date seasonStartDay = (Date)solrDocument.get("seasonStartDay_stored_date");
				if(seasonStartDay != null)
					oSchoolSeason.setSeasonStartDay(seasonStartDay);
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

			if(savesSchoolSeason.contains("seasonNameComplete")) {
				String seasonNameComplete = (String)solrDocument.get("seasonNameComplete_stored_string");
				if(seasonNameComplete != null)
					oSchoolSeason.setSeasonNameComplete(seasonNameComplete);
			}

			if(savesSchoolSeason.contains("seasonId")) {
				String seasonId = (String)solrDocument.get("seasonId_stored_string");
				if(seasonId != null)
					oSchoolSeason.setSeasonId(seasonId);
			}

			if(savesSchoolSeason.contains("pageUrl")) {
				String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
				if(pageUrl != null)
					oSchoolSeason.setPageUrl(pageUrl);
			}

			if(savesSchoolSeason.contains("objectSuggest")) {
				String objectSuggest = (String)solrDocument.get("objectSuggest_suggested");
				oSchoolSeason.setObjectSuggest(objectSuggest);
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
			clientSolr.commit();
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
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolSeason(SolrInputDocument document) {
		if(savesSchoolSeason != null)
			document.addField("savesSchoolSeason_stored_strings", savesSchoolSeason);

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
		if(schoolNameComplete != null) {
			document.addField("schoolNameComplete_indexed_string", schoolNameComplete);
			document.addField("schoolNameComplete_stored_string", schoolNameComplete);
		}
		if(yearStart != null) {
			document.addField("yearStart_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearStart.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("yearStart_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearStart.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(yearEnd != null) {
			document.addField("yearEnd_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearEnd.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("yearEnd_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearEnd.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(seasonStartDay != null) {
			document.addField("seasonStartDay_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(seasonStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("seasonStartDay_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(seasonStartDay.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
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
		if(seasonNameComplete != null) {
			document.addField("seasonNameComplete_indexed_string", seasonNameComplete);
			document.addField("seasonNameComplete_stored_string", seasonNameComplete);
		}
		if(seasonId != null) {
			document.addField("seasonId_indexed_string", seasonId);
			document.addField("seasonId_stored_string", seasonId);
		}
		if(pageUrl != null) {
			document.addField("pageUrl_indexed_string", pageUrl);
			document.addField("pageUrl_stored_string", pageUrl);
		}
		if(objectSuggest != null) {
			document.addField("objectSuggest_suggested", objectSuggest);
			document.addField("objectSuggest_indexed_string", objectSuggest);
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
			solrClient.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
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

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oSchoolSeason.setSchoolKey(schoolKey);

		Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
		if(yearKey != null)
			oSchoolSeason.setYearKey(yearKey);

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

		String schoolNameComplete = (String)solrDocument.get("schoolNameComplete_stored_string");
		if(schoolNameComplete != null)
			oSchoolSeason.setSchoolNameComplete(schoolNameComplete);

		Date yearStart = (Date)solrDocument.get("yearStart_stored_date");
		if(yearStart != null)
			oSchoolSeason.setYearStart(yearStart);

		Date yearEnd = (Date)solrDocument.get("yearEnd_stored_date");
		if(yearEnd != null)
			oSchoolSeason.setYearEnd(yearEnd);

		Date seasonStartDay = (Date)solrDocument.get("seasonStartDay_stored_date");
		if(seasonStartDay != null)
			oSchoolSeason.setSeasonStartDay(seasonStartDay);

		Boolean seasonSummer = (Boolean)solrDocument.get("seasonSummer_stored_boolean");
		if(seasonSummer != null)
			oSchoolSeason.setSeasonSummer(seasonSummer);

		Boolean seasonWinter = (Boolean)solrDocument.get("seasonWinter_stored_boolean");
		if(seasonWinter != null)
			oSchoolSeason.setSeasonWinter(seasonWinter);

		Double seasonEnrollmentFee = (Double)solrDocument.get("seasonEnrollmentFee_stored_double");
		if(seasonEnrollmentFee != null)
			oSchoolSeason.setSeasonEnrollmentFee(seasonEnrollmentFee);

		String seasonNameComplete = (String)solrDocument.get("seasonNameComplete_stored_string");
		if(seasonNameComplete != null)
			oSchoolSeason.setSeasonNameComplete(seasonNameComplete);

		String seasonId = (String)solrDocument.get("seasonId_stored_string");
		if(seasonId != null)
			oSchoolSeason.setSeasonId(seasonId);

		String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
		if(pageUrl != null)
			oSchoolSeason.setPageUrl(pageUrl);

		String objectSuggest = (String)solrDocument.get("objectSuggest_suggested");
		oSchoolSeason.setObjectSuggest(objectSuggest);

		super.storeCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), yearKey, sessionKeys, seasonStartDay, seasonSummer, seasonWinter, seasonEnrollmentFee);
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
				&& Objects.equals( yearKey, that.yearKey )
				&& Objects.equals( sessionKeys, that.sessionKeys )
				&& Objects.equals( seasonStartDay, that.seasonStartDay )
				&& Objects.equals( seasonSummer, that.seasonSummer )
				&& Objects.equals( seasonWinter, that.seasonWinter )
				&& Objects.equals( seasonEnrollmentFee, that.seasonEnrollmentFee );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolSeason { ");
		sb.append( "yearKey: " ).append(yearKey);
		sb.append( ", sessionKeys: " ).append(sessionKeys);
		sb.append( ", seasonStartDay: " ).append(seasonStartDay);
		sb.append( ", seasonSummer: " ).append(seasonSummer);
		sb.append( ", seasonWinter: " ).append(seasonWinter);
		sb.append( ", seasonEnrollmentFee: " ).append(seasonEnrollmentFee);
		sb.append(" }");
		return sb.toString();
	}
}
