package org.computate.scolaire.enUS.year;

import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
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
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolYearGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolYear.class);

	public static final String SchoolYear_UnNom = "a year";
	public static final String SchoolYear_Ce = "this ";
	public static final String SchoolYear_CeNom = "this year";
	public static final String SchoolYear_Un = "an ";
	public static final String SchoolYear_LeNom = "theyear";
	public static final String SchoolYear_NomSingulier = "year";
	public static final String SchoolYear_NomPluriel = "years";
	public static final String SchoolYear_NomActuel = "current year";
	public static final String SchoolYear_TousNom = "the years";
	public static final String SchoolYear_RechercherTousNomPar = "search years by ";
	public static final String SchoolYear_LesNoms = "the years";
	public static final String SchoolYear_AucunNomTrouve = "no year found";
	public static final String SchoolYear_NomVar = "year";
	public static final String SchoolYear_DeNom = "of year";
	public static final String SchoolYear_Couleur = "orange";
	public static final String SchoolYear_IconeGroupe = "duotone";
	public static final String SchoolYear_IconeNom = "calendar-check-o";

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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
			r.s("<div id=\"patchSchoolYear", strPk(), "SchoolKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "SchoolKey() {");
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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

	public String nomAffichageYearKey() {
		return "key";
	}

	public String htmTooltipYearKey() {
		return null;
	}

	public String htmYearKey() {
		return yearKey == null ? "" : StringEscapeUtils.escapeHtml4(strYearKey());
	}

	public void htmYearKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolYear", strPk(), "YearKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "YearKey() {");
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
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
			r.s("<div id=\"patchSchoolYear", strPk(), "EnrollmentKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "EnrollmentKeys() {");
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

	////////////////
	// seasonKeys //
	////////////////

	/**	L'entité « seasonKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> seasonKeys = new java.util.ArrayList<java.lang.Long>();
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
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

	public String nomAffichageSeasonKeys() {
		return null;
	}

	public String htmTooltipSeasonKeys() {
		return null;
	}

	public String htmSeasonKeys() {
		return seasonKeys == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonKeys());
	}

	public void htmSeasonKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolYear", strPk(), "SeasonKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "SeasonKeys() {");
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
				r.l("				data: {\"setSeasonKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSeasonKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"seasonKeys\"");
							r.s(" value=\"", htmSeasonKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSeasonKeys());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// schoolSearch //
	//////////////////

	/**	L'entité « schoolSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<School>(). 
	 */
	@JsonIgnore
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

	////////////////////
	// schoolDocument //
	////////////////////

	/**	L'entité « schoolDocument »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SolrDocument schoolDocument;
	@JsonIgnore
	public Wrap<SolrDocument> schoolDocumentWrap = new Wrap<SolrDocument>().p(this).c(SolrDocument.class).var("schoolDocument").o(schoolDocument);

	/**	<br/>L'entité « schoolDocument »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolDocument">Trouver l'entité schoolDocument dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolDocument(Wrap<SolrDocument> c);

	public SolrDocument getSchoolDocument() {
		return schoolDocument;
	}

	public void setSchoolDocument(SolrDocument schoolDocument) {
		this.schoolDocument = schoolDocument;
		this.schoolDocumentWrap.alreadyInitialized = true;
	}
	protected SchoolYear schoolDocumentInit() {
		if(!schoolDocumentWrap.alreadyInitialized) {
			_schoolDocument(schoolDocumentWrap);
			if(schoolDocument == null)
				setSchoolDocument(schoolDocumentWrap.o);
		}
		schoolDocumentWrap.alreadyInitialized(true);
		return (SchoolYear)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolNameComplete">Trouver l'entité schoolNameComplete dans Solr</a>
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
	protected SchoolYear schoolNameCompleteInit() {
		if(!schoolNameCompleteWrap.alreadyInitialized) {
			_schoolNameComplete(schoolNameCompleteWrap);
			if(schoolNameComplete == null)
				setSchoolNameComplete(schoolNameCompleteWrap.o);
		}
		schoolNameCompleteWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrSchoolNameComplete() {
		return schoolNameComplete;
	}

	public String strSchoolNameComplete() {
		return schoolNameComplete == null ? "" : schoolNameComplete;
	}

	public String nomAffichageSchoolNameComplete() {
		return "r: ecoleNomComplet";
	}

	public String htmTooltipSchoolNameComplete() {
		return null;
	}

	public String htmSchoolNameComplete() {
		return schoolNameComplete == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolNameComplete());
	}

	public void htmSchoolNameComplete(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolYear", strPk(), "SchoolNameComplete\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "SchoolNameComplete() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Trouver l'entité yearStart dans Solr</a>
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
	public SchoolYear setYearStart(Instant o) {
		this.yearStart = LocalDate.from(o);
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolYear setYearStart(String o) {
		this.yearStart = LocalDate.parse(o, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	public SchoolYear setYearStart(Date o) {
		this.yearStart = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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

	public Date solrYearStart() {
		return yearStart == null ? null : Date.from(yearStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strYearStart() {
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

	public void htmYearStart(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolYear", strPk(), "YearStart\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "YearStart() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Trouver l'entité yearEnd dans Solr</a>
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
	public SchoolYear setYearEnd(Instant o) {
		this.yearEnd = LocalDate.from(o);
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolYear setYearEnd(String o) {
		this.yearEnd = LocalDate.parse(o, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	public SchoolYear setYearEnd(Date o) {
		this.yearEnd = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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

	public Date solrYearEnd() {
		return yearEnd == null ? null : Date.from(yearEnd.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strYearEnd() {
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

	public void htmYearEnd(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolYear", strPk(), "YearEnd\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "YearEnd() {");
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

	///////////////////
	// yearNameShort //
	///////////////////

	/**	L'entité « yearNameShort »
	 *	 is defined as null before being initialized. 
	 */
	protected String yearNameShort;
	@JsonIgnore
	public Wrap<String> yearNameShortWrap = new Wrap<String>().p(this).c(String.class).var("yearNameShort").o(yearNameShort);

	/**	<br/>L'entité « yearNameShort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearNameShort">Trouver l'entité yearNameShort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearNameShort(Wrap<String> c);

	public String getYearNameShort() {
		return yearNameShort;
	}

	public void setYearNameShort(String yearNameShort) {
		this.yearNameShort = yearNameShort;
		this.yearNameShortWrap.alreadyInitialized = true;
	}
	protected SchoolYear yearNameShortInit() {
		if(!yearNameShortWrap.alreadyInitialized) {
			_yearNameShort(yearNameShortWrap);
			if(yearNameShort == null)
				setYearNameShort(yearNameShortWrap.o);
		}
		yearNameShortWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrYearNameShort() {
		return yearNameShort;
	}

	public String strYearNameShort() {
		return yearNameShort == null ? "" : yearNameShort;
	}

	public String nomAffichageYearNameShort() {
		return null;
	}

	public String htmTooltipYearNameShort() {
		return null;
	}

	public String htmYearNameShort() {
		return yearNameShort == null ? "" : StringEscapeUtils.escapeHtml4(strYearNameShort());
	}

	public void htmYearNameShort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolYear", strPk(), "YearNameShort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "YearNameShort() {");
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
				r.l("				data: {\"setYearNameShort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearNameShort()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearNameShort\"");
							r.s(" value=\"", htmYearNameShort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearNameShort());
			}
			r.l("</div>");
		}
	}

	//////////////////////
	// yearNameComplete //
	//////////////////////

	/**	L'entité « yearNameComplete »
	 *	 is defined as null before being initialized. 
	 */
	protected String yearNameComplete;
	@JsonIgnore
	public Wrap<String> yearNameCompleteWrap = new Wrap<String>().p(this).c(String.class).var("yearNameComplete").o(yearNameComplete);

	/**	<br/>L'entité « yearNameComplete »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearNameComplete">Trouver l'entité yearNameComplete dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearNameComplete(Wrap<String> c);

	public String getYearNameComplete() {
		return yearNameComplete;
	}

	public void setYearNameComplete(String yearNameComplete) {
		this.yearNameComplete = yearNameComplete;
		this.yearNameCompleteWrap.alreadyInitialized = true;
	}
	protected SchoolYear yearNameCompleteInit() {
		if(!yearNameCompleteWrap.alreadyInitialized) {
			_yearNameComplete(yearNameCompleteWrap);
			if(yearNameComplete == null)
				setYearNameComplete(yearNameCompleteWrap.o);
		}
		yearNameCompleteWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrYearNameComplete() {
		return yearNameComplete;
	}

	public String strYearNameComplete() {
		return yearNameComplete == null ? "" : yearNameComplete;
	}

	public String nomAffichageYearNameComplete() {
		return null;
	}

	public String htmTooltipYearNameComplete() {
		return null;
	}

	public String htmYearNameComplete() {
		return yearNameComplete == null ? "" : StringEscapeUtils.escapeHtml4(strYearNameComplete());
	}

	public void htmYearNameComplete(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolYear", strPk(), "YearNameComplete\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "YearNameComplete() {");
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
				r.l("				data: {\"setYearNameComplete\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearNameComplete()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearNameComplete\"");
							r.s(" value=\"", htmYearNameComplete(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearNameComplete());
			}
			r.l("</div>");
		}
	}

	////////////
	// yearId //
	////////////

	/**	L'entité « yearId »
	 *	 is defined as null before being initialized. 
	 */
	protected String yearId;
	@JsonIgnore
	public Wrap<String> yearIdWrap = new Wrap<String>().p(this).c(String.class).var("yearId").o(yearId);

	/**	<br/>L'entité « yearId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearId">Trouver l'entité yearId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearId(Wrap<String> c);

	public String getYearId() {
		return yearId;
	}

	public void setYearId(String yearId) {
		this.yearId = yearId;
		this.yearIdWrap.alreadyInitialized = true;
	}
	protected SchoolYear yearIdInit() {
		if(!yearIdWrap.alreadyInitialized) {
			_yearId(yearIdWrap);
			if(yearId == null)
				setYearId(yearIdWrap.o);
		}
		yearIdWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrYearId() {
		return yearId;
	}

	public String strYearId() {
		return yearId == null ? "" : yearId;
	}

	public String nomAffichageYearId() {
		return "r: anneeNomComplet";
	}

	public String htmTooltipYearId() {
		return null;
	}

	public String htmYearId() {
		return yearId == null ? "" : StringEscapeUtils.escapeHtml4(strYearId());
	}

	public void htmYearId(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolYear", strPk(), "YearId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "YearId() {");
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
				r.l("				data: {\"setYearId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearId()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearId\"");
							r.s(" value=\"", htmYearId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearId());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
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
	protected SchoolYear pageUrlInit() {
		if(!pageUrlWrap.alreadyInitialized) {
			_pageUrl(pageUrlWrap);
			if(pageUrl == null)
				setPageUrl(pageUrlWrap.o);
		}
		pageUrlWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrPageUrl() {
		return pageUrl;
	}

	public String strPageUrl() {
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
			r.s("<div id=\"patchSchoolYear", strPk(), "PageUrl\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "PageUrl() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objectSuggest">Trouver l'entité objectSuggest dans Solr</a>
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
	protected SchoolYear objectSuggestInit() {
		if(!objectSuggestWrap.alreadyInitialized) {
			_objectSuggest(objectSuggestWrap);
			if(objectSuggest == null)
				setObjectSuggest(objectSuggestWrap.o);
		}
		objectSuggestWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrObjectSuggest() {
		return objectSuggest;
	}

	public String strObjectSuggest() {
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
			r.s("<div id=\"patchSchoolYear", strPk(), "ObjectSuggest\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolYear", strPk(), "ObjectSuggest() {");
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
		super.initDeepCluster(siteRequest_);
		initSchoolYear();
	}

	public void initSchoolYear() {
		schoolKeyInit();
		yearKeyInit();
		enrollmentKeysInit();
		seasonKeysInit();
		schoolSearchInit();
		schoolDocumentInit();
		schoolNameCompleteInit();
		yearStartInit();
		yearEndInit();
		yearNameShortInit();
		yearNameCompleteInit();
		yearIdInit();
		pageUrlInit();
		objectSuggestInit();
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
			case "schoolSearch":
				return oSchoolYear.schoolSearch;
			case "schoolDocument":
				return oSchoolYear.schoolDocument;
			case "schoolNameComplete":
				return oSchoolYear.schoolNameComplete;
			case "yearStart":
				return oSchoolYear.yearStart;
			case "yearEnd":
				return oSchoolYear.yearEnd;
			case "yearNameShort":
				return oSchoolYear.yearNameShort;
			case "yearNameComplete":
				return oSchoolYear.yearNameComplete;
			case "yearId":
				return oSchoolYear.yearId;
			case "pageUrl":
				return oSchoolYear.pageUrl;
			case "objectSuggest":
				return oSchoolYear.objectSuggest;
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
			case "yearStart":
				setYearStart(val);
				savesSchoolYear.add(var);
				return val;
			case "yearEnd":
				setYearEnd(val);
				savesSchoolYear.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesSchoolYear = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolYear(solrDocument);
	}
	public void populateSchoolYear(SolrDocument solrDocument) {
		SchoolYear oSchoolYear = (SchoolYear)this;
		savesSchoolYear = (List<String>)solrDocument.get("savesSchoolYear_stored_strings");
		if(savesSchoolYear != null) {

			Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
			if(schoolKey != null)
				oSchoolYear.setSchoolKey(schoolKey);

			if(savesSchoolYear.contains("yearKey")) {
				Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
				if(yearKey != null)
					oSchoolYear.setYearKey(yearKey);
			}

			if(savesSchoolYear.contains("enrollmentKeys")) {
				List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
				if(enrollmentKeys != null)
					oSchoolYear.enrollmentKeys.addAll(enrollmentKeys);
			}

			if(savesSchoolYear.contains("seasonKeys")) {
				List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
				if(seasonKeys != null)
					oSchoolYear.seasonKeys.addAll(seasonKeys);
			}

			if(savesSchoolYear.contains("schoolNameComplete")) {
				String schoolNameComplete = (String)solrDocument.get("schoolNameComplete_stored_string");
				if(schoolNameComplete != null)
					oSchoolYear.setSchoolNameComplete(schoolNameComplete);
			}

			if(savesSchoolYear.contains("yearStart")) {
				Date yearStart = (Date)solrDocument.get("yearStart_stored_date");
				if(yearStart != null)
					oSchoolYear.setYearStart(yearStart);
			}

			if(savesSchoolYear.contains("yearEnd")) {
				Date yearEnd = (Date)solrDocument.get("yearEnd_stored_date");
				if(yearEnd != null)
					oSchoolYear.setYearEnd(yearEnd);
			}

			if(savesSchoolYear.contains("yearNameShort")) {
				String yearNameShort = (String)solrDocument.get("yearNameShort_stored_string");
				if(yearNameShort != null)
					oSchoolYear.setYearNameShort(yearNameShort);
			}

			if(savesSchoolYear.contains("yearNameComplete")) {
				String yearNameComplete = (String)solrDocument.get("yearNameComplete_stored_string");
				if(yearNameComplete != null)
					oSchoolYear.setYearNameComplete(yearNameComplete);
			}

			if(savesSchoolYear.contains("yearId")) {
				String yearId = (String)solrDocument.get("yearId_stored_string");
				if(yearId != null)
					oSchoolYear.setYearId(yearId);
			}

			if(savesSchoolYear.contains("pageUrl")) {
				String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
				if(pageUrl != null)
					oSchoolYear.setPageUrl(pageUrl);
			}

			if(savesSchoolYear.contains("objectSuggest")) {
				String objectSuggest = (String)solrDocument.get("objectSuggest_stored_string");
				if(objectSuggest != null)
					oSchoolYear.setObjectSuggest(objectSuggest);
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
			clientSolr.commit();
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
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolYear(SolrInputDocument document) {
		if(savesSchoolYear != null)
			document.addField("savesSchoolYear_stored_strings", savesSchoolYear);

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
		if(schoolNameComplete != null) {
			document.addField("schoolNameComplete_indexed_string", schoolNameComplete);
			document.addField("schoolNameComplete_stored_string", schoolNameComplete);
		}
		if(yearStart != null) {
			document.addField("yearStart_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearStart.atStartOfDay(ZoneId.of("Z"))));
			document.addField("yearStart_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearStart.atStartOfDay(ZoneId.of("Z"))));
		}
		if(yearEnd != null) {
			document.addField("yearEnd_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearEnd.atStartOfDay(ZoneId.of("Z"))));
			document.addField("yearEnd_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(yearEnd.atStartOfDay(ZoneId.of("Z"))));
		}
		if(yearNameShort != null) {
			document.addField("yearNameShort_indexed_string", yearNameShort);
			document.addField("yearNameShort_stored_string", yearNameShort);
		}
		if(yearNameComplete != null) {
			document.addField("yearNameComplete_indexed_string", yearNameComplete);
			document.addField("yearNameComplete_stored_string", yearNameComplete);
		}
		if(yearId != null) {
			document.addField("yearId_indexed_string", yearId);
			document.addField("yearId_stored_string", yearId);
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
			solrClient.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
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

		String schoolNameComplete = (String)solrDocument.get("schoolNameComplete_stored_string");
		if(schoolNameComplete != null)
			oSchoolYear.setSchoolNameComplete(schoolNameComplete);

		Date yearStart = (Date)solrDocument.get("yearStart_stored_date");
		if(yearStart != null)
			oSchoolYear.setYearStart(yearStart);

		Date yearEnd = (Date)solrDocument.get("yearEnd_stored_date");
		if(yearEnd != null)
			oSchoolYear.setYearEnd(yearEnd);

		String yearNameShort = (String)solrDocument.get("yearNameShort_stored_string");
		if(yearNameShort != null)
			oSchoolYear.setYearNameShort(yearNameShort);

		String yearNameComplete = (String)solrDocument.get("yearNameComplete_stored_string");
		if(yearNameComplete != null)
			oSchoolYear.setYearNameComplete(yearNameComplete);

		String yearId = (String)solrDocument.get("yearId_stored_string");
		if(yearId != null)
			oSchoolYear.setYearId(yearId);

		String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
		if(pageUrl != null)
			oSchoolYear.setPageUrl(pageUrl);

		String objectSuggest = (String)solrDocument.get("objectSuggest_stored_string");
		if(objectSuggest != null)
			oSchoolYear.setObjectSuggest(objectSuggest);

		super.storeCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), schoolKey, yearStart, yearEnd);
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
				&& Objects.equals( yearStart, that.yearStart )
				&& Objects.equals( yearEnd, that.yearEnd );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolYear { ");
		sb.append( "schoolKey: " ).append(schoolKey);
		sb.append( ", yearStart: " ).append(yearStart);
		sb.append( ", yearEnd: " ).append(yearEnd);
		sb.append(" }");
		return sb.toString();
	}
}
