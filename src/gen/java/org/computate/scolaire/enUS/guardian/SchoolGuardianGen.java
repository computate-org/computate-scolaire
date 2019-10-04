package org.computate.scolaire.enUS.guardian;

import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
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
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolGuardianGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolGuardian.class);

	public static final String SchoolGuardian_UnNom = "a guardian";
	public static final String SchoolGuardian_Ce = "this ";
	public static final String SchoolGuardian_CeNom = "this guardian";
	public static final String SchoolGuardian_Un = "a ";
	public static final String SchoolGuardian_LeNom = "the guardian";
	public static final String SchoolGuardian_NomSingulier = "guardian";
	public static final String SchoolGuardian_NomPluriel = "guardians";
	public static final String SchoolGuardian_NomActuel = "current guardian";
	public static final String SchoolGuardian_TousNom = "all the guardians";
	public static final String SchoolGuardian_RechercherTousNomPar = "search guardians by ";
	public static final String SchoolGuardian_LesNoms = "the guardians";
	public static final String SchoolGuardian_AucunNomTrouve = "no guardian found";
	public static final String SchoolGuardian_NomVar = "guardian";
	public static final String SchoolGuardian_DeNom = "of guardian";
	public static final String SchoolGuardian_UnNomAdjectif = "a guardian";
	public static final String SchoolGuardian_NomAdjectifSingulier = "guardian";
	public static final String SchoolGuardian_NomAdjectifPluriel = "guardians";
	public static final String SchoolGuardian_Couleur = "yellow";
	public static final String SchoolGuardian_IconeGroupe = "regular";
	public static final String SchoolGuardian_IconeNom = "phone";

	/////////////////
	// guardianKey //
	/////////////////

	/**	L'entité « guardianKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long guardianKey;
	@JsonIgnore
	public Wrap<Long> guardianKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("guardianKey").o(guardianKey);

	/**	<br/>L'entité « guardianKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianKey">Trouver l'entité guardianKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _guardianKey(Wrap<Long> c);

	public Long getGuardianKey() {
		return guardianKey;
	}

	public void setGuardianKey(Long guardianKey) {
		this.guardianKey = guardianKey;
		this.guardianKeyWrap.alreadyInitialized = true;
	}
	public SchoolGuardian setGuardianKey(String o) {
		if(NumberUtils.isParsable(o))
			this.guardianKey = Long.parseLong(o);
		this.guardianKeyWrap.alreadyInitialized = true;
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian guardianKeyInit() {
		if(!guardianKeyWrap.alreadyInitialized) {
			_guardianKey(guardianKeyWrap);
			if(guardianKey == null)
				setGuardianKey(guardianKeyWrap.o);
		}
		guardianKeyWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public Long solrGuardianKey() {
		return guardianKey;
	}

	public String strGuardianKey() {
		return guardianKey == null ? "" : guardianKey.toString();
	}

	public String jsonGuardianKey() {
		return guardianKey == null ? "" : guardianKey.toString();
	}

	public String nomAffichageGuardianKey() {
		return "key";
	}

	public String htmTooltipGuardianKey() {
		return null;
	}

	public String htmGuardianKey() {
		return guardianKey == null ? "" : StringEscapeUtils.escapeHtml4(strGuardianKey());
	}

	public void htmGuardianKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "GuardianKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "GuardianKey() {");
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
				r.l("				data: {\"setGuardianKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageGuardianKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"guardianKey\"");
							r.s(" value=\"", htmGuardianKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmGuardianKey());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Trouver l'entité enrollmentKeys dans Solr</a>
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
	public SchoolGuardian addEnrollmentKeys(Long...objets) {
		for(Long o : objets) {
			addEnrollmentKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addEnrollmentKeys(Long o) {
		if(o != null && !enrollmentKeys.contains(o))
			this.enrollmentKeys.add(o);
		return (SchoolGuardian)this;
	}
	public SchoolGuardian setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addEnrollmentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnrollmentKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian enrollmentKeysInit() {
		if(!enrollmentKeysWrap.alreadyInitialized) {
			_enrollmentKeys(enrollmentKeys);
		}
		enrollmentKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmEnrollmentKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "EnrollmentKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "EnrollmentKeys() {");
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
	// familySort //
	////////////////

	/**	L'entité « familySort »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer familySort;
	@JsonIgnore
	public Wrap<Integer> familySortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("familySort").o(familySort);

	/**	<br/>L'entité « familySort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familySort">Trouver l'entité familySort dans Solr</a>
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
	public SchoolGuardian setFamilySort(String o) {
		if(NumberUtils.isParsable(o))
			this.familySort = Integer.parseInt(o);
		this.familySortWrap.alreadyInitialized = true;
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian familySortInit() {
		if(!familySortWrap.alreadyInitialized) {
			_familySort(familySortWrap);
			if(familySort == null)
				setFamilySort(familySortWrap.o);
		}
		familySortWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmFamilySort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "FamilySort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "FamilySort() {");
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
				r.l("				data: {\"setFamilySort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilySort()), "</span>");
				r.s("			<input");
							r.s(" name=\"familySort\"");
							r.s(" value=\"", htmFamilySort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilySort());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Trouver l'entité schoolSort dans Solr</a>
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
	public SchoolGuardian setSchoolSort(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolSort = Integer.parseInt(o);
		this.schoolSortWrap.alreadyInitialized = true;
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian schoolSortInit() {
		if(!schoolSortWrap.alreadyInitialized) {
			_schoolSort(schoolSortWrap);
			if(schoolSort == null)
				setSchoolSort(schoolSortWrap.o);
		}
		schoolSortWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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
			r.s("<div id=\"patchSchoolGuardian", strPk(), "SchoolSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "SchoolSort() {");
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

	//////////////////////
	// enrollmentSearch //
	//////////////////////

	/**	L'entité « enrollmentSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolEnrollment>(). 
	 */
	@JsonIgnore
	protected SearchList<SchoolEnrollment> enrollmentSearch = new SearchList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<SearchList<SchoolEnrollment>> enrollmentSearchWrap = new Wrap<SearchList<SchoolEnrollment>>().p(this).c(SearchList.class).var("enrollmentSearch").o(enrollmentSearch);

	/**	<br/>L'entité « enrollmentSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSearch">Trouver l'entité enrollmentSearch dans Solr</a>
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
	protected SchoolGuardian enrollmentSearchInit() {
		if(!enrollmentSearchWrap.alreadyInitialized) {
			_enrollmentSearch(enrollmentSearch);
		}
		enrollmentSearch.initDeepForClass(siteRequest_);
		enrollmentSearchWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	//////////////////
	// inscriptions //
	//////////////////

	/**	L'entité « inscriptions »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 */
	@JsonIgnore
	protected List<SchoolEnrollment> inscriptions = new java.util.ArrayList<org.computate.scolaire.enUS.enrollment.SchoolEnrollment>();
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> inscriptionsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("inscriptions").o(inscriptions);

	/**	<br/>L'entité « inscriptions »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:inscriptions">Trouver l'entité inscriptions dans Solr</a>
	 * <br/>
	 * @param inscriptions est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptions(List<SchoolEnrollment> l);

	public List<SchoolEnrollment> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<SchoolEnrollment> inscriptions) {
		this.inscriptions = inscriptions;
		this.inscriptionsWrap.alreadyInitialized = true;
	}
	public SchoolGuardian addInscriptions(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addInscriptions(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addInscriptions(SchoolEnrollment o) {
		if(o != null && !inscriptions.contains(o))
			this.inscriptions.add(o);
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian inscriptionsInit() {
		if(!inscriptionsWrap.alreadyInitialized) {
			_inscriptions(inscriptions);
		}
		inscriptionsWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	////////////////
	// schoolKeys //
	////////////////

	/**	L'entité « schoolKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> schoolKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> schoolKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("schoolKeys").o(schoolKeys);

	/**	<br/>L'entité « schoolKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKeys">Trouver l'entité schoolKeys dans Solr</a>
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
	public SchoolGuardian addSchoolKeys(Long...objets) {
		for(Long o : objets) {
			addSchoolKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addSchoolKeys(Long o) {
		if(o != null && !schoolKeys.contains(o))
			this.schoolKeys.add(o);
		return (SchoolGuardian)this;
	}
	public SchoolGuardian setSchoolKeys(JsonArray objets) {
		schoolKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSchoolKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addSchoolKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSchoolKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian schoolKeysInit() {
		if(!schoolKeysWrap.alreadyInitialized) {
			_schoolKeys(schoolKeys);
		}
		schoolKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmSchoolKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "SchoolKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "SchoolKeys() {");
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
				r.l("				data: {\"setSchoolKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolKeys\"");
							r.s(" value=\"", htmSchoolKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolKeys());
			}
			r.l("</div>");
		}
	}

	//////////////
	// yearKeys //
	//////////////

	/**	L'entité « yearKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> yearKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> yearKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("yearKeys").o(yearKeys);

	/**	<br/>L'entité « yearKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKeys">Trouver l'entité yearKeys dans Solr</a>
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
	public SchoolGuardian addYearKeys(Long...objets) {
		for(Long o : objets) {
			addYearKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addYearKeys(Long o) {
		if(o != null && !yearKeys.contains(o))
			this.yearKeys.add(o);
		return (SchoolGuardian)this;
	}
	public SchoolGuardian setYearKeys(JsonArray objets) {
		yearKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addYearKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addYearKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addYearKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian yearKeysInit() {
		if(!yearKeysWrap.alreadyInitialized) {
			_yearKeys(yearKeys);
		}
		yearKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmYearKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "YearKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "YearKeys() {");
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
				r.l("				data: {\"setYearKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearKeys\"");
							r.s(" value=\"", htmYearKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearKeys());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKeys">Trouver l'entité seasonKeys dans Solr</a>
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
	public SchoolGuardian addSeasonKeys(Long...objets) {
		for(Long o : objets) {
			addSeasonKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addSeasonKeys(Long o) {
		if(o != null && !seasonKeys.contains(o))
			this.seasonKeys.add(o);
		return (SchoolGuardian)this;
	}
	public SchoolGuardian setSeasonKeys(JsonArray objets) {
		seasonKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSeasonKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addSeasonKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSeasonKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian seasonKeysInit() {
		if(!seasonKeysWrap.alreadyInitialized) {
			_seasonKeys(seasonKeys);
		}
		seasonKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmSeasonKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "SeasonKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "SeasonKeys() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKeys">Trouver l'entité sessionKeys dans Solr</a>
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
	public SchoolGuardian addSessionKeys(Long...objets) {
		for(Long o : objets) {
			addSessionKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addSessionKeys(Long o) {
		if(o != null && !sessionKeys.contains(o))
			this.sessionKeys.add(o);
		return (SchoolGuardian)this;
	}
	public SchoolGuardian setSessionKeys(JsonArray objets) {
		sessionKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addSessionKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian sessionKeysInit() {
		if(!sessionKeysWrap.alreadyInitialized) {
			_sessionKeys(sessionKeys);
		}
		sessionKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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
			r.s("<div id=\"patchSchoolGuardian", strPk(), "SessionKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "SessionKeys() {");
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

	/////////////
	// ageKeys //
	/////////////

	/**	L'entité « ageKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> ageKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> ageKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("ageKeys").o(ageKeys);

	/**	<br/>L'entité « ageKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKeys">Trouver l'entité ageKeys dans Solr</a>
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
	public SchoolGuardian addAgeKeys(Long...objets) {
		for(Long o : objets) {
			addAgeKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addAgeKeys(Long o) {
		if(o != null && !ageKeys.contains(o))
			this.ageKeys.add(o);
		return (SchoolGuardian)this;
	}
	public SchoolGuardian setAgeKeys(JsonArray objets) {
		ageKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addAgeKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAgeKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian ageKeysInit() {
		if(!ageKeysWrap.alreadyInitialized) {
			_ageKeys(ageKeys);
		}
		ageKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmAgeKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "AgeKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "AgeKeys() {");
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
				r.l("				data: {\"setAgeKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageKeys\"");
							r.s(" value=\"", htmAgeKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeKeys());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// personFirstName //
	/////////////////////

	/**	L'entité « personFirstName »
	 *	 is defined as null before being initialized. 
	 */
	protected String personFirstName;
	@JsonIgnore
	public Wrap<String> personFirstNameWrap = new Wrap<String>().p(this).c(String.class).var("personFirstName").o(personFirstName);

	/**	<br/>L'entité « personFirstName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFirstName">Trouver l'entité personFirstName dans Solr</a>
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
	protected SchoolGuardian personFirstNameInit() {
		if(!personFirstNameWrap.alreadyInitialized) {
			_personFirstName(personFirstNameWrap);
			if(personFirstName == null)
				setPersonFirstName(personFirstNameWrap.o);
		}
		personFirstNameWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonFirstName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonFirstName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonFirstName() {");
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
				r.l("				data: {\"setPersonFirstName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonFirstName()), "</span>");
				r.s("			<input");
							r.s(" name=\"personFirstName\"");
							r.s(" value=\"", htmPersonFirstName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonFirstName());
			}
			r.l("</div>");
		}
	}

	//////////////////////////////
	// personFirstNamePreferred //
	//////////////////////////////

	/**	L'entité « personFirstNamePreferred »
	 *	 is defined as null before being initialized. 
	 */
	protected String personFirstNamePreferred;
	@JsonIgnore
	public Wrap<String> personFirstNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("personFirstNamePreferred").o(personFirstNamePreferred);

	/**	<br/>L'entité « personFirstNamePreferred »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFirstNamePreferred">Trouver l'entité personFirstNamePreferred dans Solr</a>
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
	protected SchoolGuardian personFirstNamePreferredInit() {
		if(!personFirstNamePreferredWrap.alreadyInitialized) {
			_personFirstNamePreferred(personFirstNamePreferredWrap);
			if(personFirstNamePreferred == null)
				setPersonFirstNamePreferred(personFirstNamePreferredWrap.o);
		}
		personFirstNamePreferredWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonFirstNamePreferred(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonFirstNamePreferred\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonFirstNamePreferred() {");
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
				r.l("				data: {\"setPersonFirstNamePreferred\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonFirstNamePreferred()), "</span>");
				r.s("			<input");
							r.s(" name=\"personFirstNamePreferred\"");
							r.s(" value=\"", htmPersonFirstNamePreferred(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonFirstNamePreferred());
			}
			r.l("</div>");
		}
	}

	////////////////
	// familyName //
	////////////////

	/**	L'entité « familyName »
	 *	 is defined as null before being initialized. 
	 */
	protected String familyName;
	@JsonIgnore
	public Wrap<String> familyNameWrap = new Wrap<String>().p(this).c(String.class).var("familyName").o(familyName);

	/**	<br/>L'entité « familyName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyName">Trouver l'entité familyName dans Solr</a>
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
	protected SchoolGuardian familyNameInit() {
		if(!familyNameWrap.alreadyInitialized) {
			_familyName(familyNameWrap);
			if(familyName == null)
				setFamilyName(familyNameWrap.o);
		}
		familyNameWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmFamilyName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "FamilyName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "FamilyName() {");
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
				r.l("				data: {\"setFamilyName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilyName()), "</span>");
				r.s("			<input");
							r.s(" name=\"familyName\"");
							r.s(" value=\"", htmFamilyName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilyName());
			}
			r.l("</div>");
		}
	}

	////////////////////////
	// personCompleteName //
	////////////////////////

	/**	L'entité « personCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String personCompleteName;
	@JsonIgnore
	public Wrap<String> personCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("personCompleteName").o(personCompleteName);

	/**	<br/>L'entité « personCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personCompleteName">Trouver l'entité personCompleteName dans Solr</a>
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
	protected SchoolGuardian personCompleteNameInit() {
		if(!personCompleteNameWrap.alreadyInitialized) {
			_personCompleteName(personCompleteNameWrap);
			if(personCompleteName == null)
				setPersonCompleteName(personCompleteNameWrap.o);
		}
		personCompleteNameWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonCompleteName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonCompleteName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonCompleteName() {");
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
				r.l("				data: {\"setPersonCompleteName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonCompleteName()), "</span>");
				r.s("			<input");
							r.s(" name=\"personCompleteName\"");
							r.s(" value=\"", htmPersonCompleteName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonCompleteName());
			}
			r.l("</div>");
		}
	}

	/////////////////////////////////
	// personCompleteNamePreferred //
	/////////////////////////////////

	/**	L'entité « personCompleteNamePreferred »
	 *	 is defined as null before being initialized. 
	 */
	protected String personCompleteNamePreferred;
	@JsonIgnore
	public Wrap<String> personCompleteNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("personCompleteNamePreferred").o(personCompleteNamePreferred);

	/**	<br/>L'entité « personCompleteNamePreferred »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personCompleteNamePreferred">Trouver l'entité personCompleteNamePreferred dans Solr</a>
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
	protected SchoolGuardian personCompleteNamePreferredInit() {
		if(!personCompleteNamePreferredWrap.alreadyInitialized) {
			_personCompleteNamePreferred(personCompleteNamePreferredWrap);
			if(personCompleteNamePreferred == null)
				setPersonCompleteNamePreferred(personCompleteNamePreferredWrap.o);
		}
		personCompleteNamePreferredWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonCompleteNamePreferred(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonCompleteNamePreferred\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonCompleteNamePreferred() {");
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
				r.l("				data: {\"setPersonCompleteNamePreferred\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonCompleteNamePreferred()), "</span>");
				r.s("			<input");
							r.s(" name=\"personCompleteNamePreferred\"");
							r.s(" value=\"", htmPersonCompleteNamePreferred(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonCompleteNamePreferred());
			}
			r.l("</div>");
		}
	}

	//////////////////////
	// personFormalName //
	//////////////////////

	/**	L'entité « personFormalName »
	 *	 is defined as null before being initialized. 
	 */
	protected String personFormalName;
	@JsonIgnore
	public Wrap<String> personFormalNameWrap = new Wrap<String>().p(this).c(String.class).var("personFormalName").o(personFormalName);

	/**	<br/>L'entité « personFormalName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFormalName">Trouver l'entité personFormalName dans Solr</a>
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
	protected SchoolGuardian personFormalNameInit() {
		if(!personFormalNameWrap.alreadyInitialized) {
			_personFormalName(personFormalNameWrap);
			if(personFormalName == null)
				setPersonFormalName(personFormalNameWrap.o);
		}
		personFormalNameWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonFormalName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonFormalName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonFormalName() {");
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
				r.l("				data: {\"setPersonFormalName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonFormalName()), "</span>");
				r.s("			<input");
							r.s(" name=\"personFormalName\"");
							r.s(" value=\"", htmPersonFormalName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonFormalName());
			}
			r.l("</div>");
		}
	}

	//////////////////////
	// personOccupation //
	//////////////////////

	/**	L'entité « personOccupation »
	 *	 is defined as null before being initialized. 
	 */
	protected String personOccupation;
	@JsonIgnore
	public Wrap<String> personOccupationWrap = new Wrap<String>().p(this).c(String.class).var("personOccupation").o(personOccupation);

	/**	<br/>L'entité « personOccupation »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personOccupation">Trouver l'entité personOccupation dans Solr</a>
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
	protected SchoolGuardian personOccupationInit() {
		if(!personOccupationWrap.alreadyInitialized) {
			_personOccupation(personOccupationWrap);
			if(personOccupation == null)
				setPersonOccupation(personOccupationWrap.o);
		}
		personOccupationWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonOccupation(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonOccupation\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonOccupation() {");
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
				r.l("				data: {\"setPersonOccupation\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonOccupation()), "</span>");
				r.s("			<input");
							r.s(" name=\"personOccupation\"");
							r.s(" value=\"", htmPersonOccupation(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonOccupation());
			}
			r.l("</div>");
		}
	}

	///////////////////////
	// personPhoneNumber //
	///////////////////////

	/**	L'entité « personPhoneNumber »
	 *	 is defined as null before being initialized. 
	 */
	protected String personPhoneNumber;
	@JsonIgnore
	public Wrap<String> personPhoneNumberWrap = new Wrap<String>().p(this).c(String.class).var("personPhoneNumber").o(personPhoneNumber);

	/**	<br/>L'entité « personPhoneNumber »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personPhoneNumber">Trouver l'entité personPhoneNumber dans Solr</a>
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
	protected SchoolGuardian personPhoneNumberInit() {
		if(!personPhoneNumberWrap.alreadyInitialized) {
			_personPhoneNumber(personPhoneNumberWrap);
			if(personPhoneNumber == null)
				setPersonPhoneNumber(personPhoneNumberWrap.o);
		}
		personPhoneNumberWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonPhoneNumber(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonPhoneNumber\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonPhoneNumber() {");
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
				r.l("				data: {\"setPersonPhoneNumber\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonPhoneNumber()), "</span>");
				r.s("			<input");
							r.s(" name=\"personPhoneNumber\"");
							r.s(" value=\"", htmPersonPhoneNumber(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonPhoneNumber());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// personEmail //
	/////////////////

	/**	L'entité « personEmail »
	 *	 is defined as null before being initialized. 
	 */
	protected String personEmail;
	@JsonIgnore
	public Wrap<String> personEmailWrap = new Wrap<String>().p(this).c(String.class).var("personEmail").o(personEmail);

	/**	<br/>L'entité « personEmail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personEmail">Trouver l'entité personEmail dans Solr</a>
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
	protected SchoolGuardian personEmailInit() {
		if(!personEmailWrap.alreadyInitialized) {
			_personEmail(personEmailWrap);
			if(personEmail == null)
				setPersonEmail(personEmailWrap.o);
		}
		personEmailWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonEmail(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonEmail\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonEmail() {");
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
				r.l("				data: {\"setPersonEmail\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonEmail()), "</span>");
				r.s("			<input");
							r.s(" name=\"personEmail\"");
							r.s(" value=\"", htmPersonEmail(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonEmail());
			}
			r.l("</div>");
		}
	}

	////////////////////
	// personRelation //
	////////////////////

	/**	L'entité « personRelation »
	 *	 is defined as null before being initialized. 
	 */
	protected String personRelation;
	@JsonIgnore
	public Wrap<String> personRelationWrap = new Wrap<String>().p(this).c(String.class).var("personRelation").o(personRelation);

	/**	<br/>L'entité « personRelation »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personRelation">Trouver l'entité personRelation dans Solr</a>
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
	protected SchoolGuardian personRelationInit() {
		if(!personRelationWrap.alreadyInitialized) {
			_personRelation(personRelationWrap);
			if(personRelation == null)
				setPersonRelation(personRelationWrap.o);
		}
		personRelationWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonRelation(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonRelation\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonRelation() {");
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
				r.l("				data: {\"setPersonRelation\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonRelation()), "</span>");
				r.s("			<input");
							r.s(" name=\"personRelation\"");
							r.s(" value=\"", htmPersonRelation(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonRelation());
			}
			r.l("</div>");
		}
	}

	///////////////
	// personSms //
	///////////////

	/**	L'entité « personSms »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean personSms;
	@JsonIgnore
	public Wrap<Boolean> personSmsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personSms").o(personSms);

	/**	<br/>L'entité « personSms »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personSms">Trouver l'entité personSms dans Solr</a>
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
	public SchoolGuardian setPersonSms(String o) {
		this.personSms = Boolean.parseBoolean(o);
		this.personSmsWrap.alreadyInitialized = true;
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian personSmsInit() {
		if(!personSmsWrap.alreadyInitialized) {
			_personSms(personSmsWrap);
			if(personSms == null)
				setPersonSms(personSmsWrap.o);
		}
		personSmsWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonSms(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonSms\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonSms() {");
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
				r.l("				data: {\"setPersonSms\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonSms()), "</span>");
				r.s("			<input");
							r.s(" name=\"personSms\"");
							r.s(" value=\"", htmPersonSms(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonSms());
			}
			r.l("</div>");
		}
	}

	////////////////////////
	// personReceiveEmail //
	////////////////////////

	/**	L'entité « personReceiveEmail »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean personReceiveEmail;
	@JsonIgnore
	public Wrap<Boolean> personReceiveEmailWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personReceiveEmail").o(personReceiveEmail);

	/**	<br/>L'entité « personReceiveEmail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personReceiveEmail">Trouver l'entité personReceiveEmail dans Solr</a>
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
	public SchoolGuardian setPersonReceiveEmail(String o) {
		this.personReceiveEmail = Boolean.parseBoolean(o);
		this.personReceiveEmailWrap.alreadyInitialized = true;
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian personReceiveEmailInit() {
		if(!personReceiveEmailWrap.alreadyInitialized) {
			_personReceiveEmail(personReceiveEmailWrap);
			if(personReceiveEmail == null)
				setPersonReceiveEmail(personReceiveEmailWrap.o);
		}
		personReceiveEmailWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonReceiveEmail(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonReceiveEmail\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonReceiveEmail() {");
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
				r.l("				data: {\"setPersonReceiveEmail\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonReceiveEmail()), "</span>");
				r.s("			<input");
							r.s(" name=\"personReceiveEmail\"");
							r.s(" value=\"", htmPersonReceiveEmail(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonReceiveEmail());
			}
			r.l("</div>");
		}
	}

	////////////////////////////
	// personEmergencyContact //
	////////////////////////////

	/**	L'entité « personEmergencyContact »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean personEmergencyContact;
	@JsonIgnore
	public Wrap<Boolean> personEmergencyContactWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personEmergencyContact").o(personEmergencyContact);

	/**	<br/>L'entité « personEmergencyContact »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personEmergencyContact">Trouver l'entité personEmergencyContact dans Solr</a>
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
	public SchoolGuardian setPersonEmergencyContact(String o) {
		this.personEmergencyContact = Boolean.parseBoolean(o);
		this.personEmergencyContactWrap.alreadyInitialized = true;
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian personEmergencyContactInit() {
		if(!personEmergencyContactWrap.alreadyInitialized) {
			_personEmergencyContact(personEmergencyContactWrap);
			if(personEmergencyContact == null)
				setPersonEmergencyContact(personEmergencyContactWrap.o);
		}
		personEmergencyContactWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonEmergencyContact(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonEmergencyContact\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonEmergencyContact() {");
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
				r.l("				data: {\"setPersonEmergencyContact\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonEmergencyContact()), "</span>");
				r.s("			<input");
							r.s(" name=\"personEmergencyContact\"");
							r.s(" value=\"", htmPersonEmergencyContact(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonEmergencyContact());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// personPickup //
	//////////////////

	/**	L'entité « personPickup »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean personPickup;
	@JsonIgnore
	public Wrap<Boolean> personPickupWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personPickup").o(personPickup);

	/**	<br/>L'entité « personPickup »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personPickup">Trouver l'entité personPickup dans Solr</a>
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
	public SchoolGuardian setPersonPickup(String o) {
		this.personPickup = Boolean.parseBoolean(o);
		this.personPickupWrap.alreadyInitialized = true;
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian personPickupInit() {
		if(!personPickupWrap.alreadyInitialized) {
			_personPickup(personPickupWrap);
			if(personPickup == null)
				setPersonPickup(personPickupWrap.o);
		}
		personPickupWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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

	public void htmPersonPickup(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PersonPickup\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PersonPickup() {");
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
				r.l("				data: {\"setPersonPickup\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonPickup()), "</span>");
				r.s("			<input");
							r.s(" name=\"personPickup\"");
							r.s(" value=\"", htmPersonPickup(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonPickup());
			}
			r.l("</div>");
		}
	}

	//////////////////////////
	// guardianCompleteName //
	//////////////////////////

	/**	L'entité « guardianCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String guardianCompleteName;
	@JsonIgnore
	public Wrap<String> guardianCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("guardianCompleteName").o(guardianCompleteName);

	/**	<br/>L'entité « guardianCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianCompleteName">Trouver l'entité guardianCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _guardianCompleteName(Wrap<String> c);

	public String getGuardianCompleteName() {
		return guardianCompleteName;
	}

	public void setGuardianCompleteName(String guardianCompleteName) {
		this.guardianCompleteName = guardianCompleteName;
		this.guardianCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolGuardian guardianCompleteNameInit() {
		if(!guardianCompleteNameWrap.alreadyInitialized) {
			_guardianCompleteName(guardianCompleteNameWrap);
			if(guardianCompleteName == null)
				setGuardianCompleteName(guardianCompleteNameWrap.o);
		}
		guardianCompleteNameWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public String solrGuardianCompleteName() {
		return guardianCompleteName;
	}

	public String strGuardianCompleteName() {
		return guardianCompleteName == null ? "" : guardianCompleteName;
	}

	public String jsonGuardianCompleteName() {
		return guardianCompleteName == null ? "" : guardianCompleteName;
	}

	public String nomAffichageGuardianCompleteName() {
		return null;
	}

	public String htmTooltipGuardianCompleteName() {
		return null;
	}

	public String htmGuardianCompleteName() {
		return guardianCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strGuardianCompleteName());
	}

	public void htmGuardianCompleteName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "GuardianCompleteName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "GuardianCompleteName() {");
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
				r.l("				data: {\"setGuardianCompleteName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageGuardianCompleteName()), "</span>");
				r.s("			<input");
							r.s(" name=\"guardianCompleteName\"");
							r.s(" value=\"", htmGuardianCompleteName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmGuardianCompleteName());
			}
			r.l("</div>");
		}
	}

	////////////////
	// guardianId //
	////////////////

	/**	L'entité « guardianId »
	 *	 is defined as null before being initialized. 
	 */
	protected String guardianId;
	@JsonIgnore
	public Wrap<String> guardianIdWrap = new Wrap<String>().p(this).c(String.class).var("guardianId").o(guardianId);

	/**	<br/>L'entité « guardianId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianId">Trouver l'entité guardianId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _guardianId(Wrap<String> c);

	public String getGuardianId() {
		return guardianId;
	}

	public void setGuardianId(String guardianId) {
		this.guardianId = guardianId;
		this.guardianIdWrap.alreadyInitialized = true;
	}
	protected SchoolGuardian guardianIdInit() {
		if(!guardianIdWrap.alreadyInitialized) {
			_guardianId(guardianIdWrap);
			if(guardianId == null)
				setGuardianId(guardianIdWrap.o);
		}
		guardianIdWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public String solrGuardianId() {
		return guardianId;
	}

	public String strGuardianId() {
		return guardianId == null ? "" : guardianId;
	}

	public String jsonGuardianId() {
		return guardianId == null ? "" : guardianId;
	}

	public String nomAffichageGuardianId() {
		return "ID";
	}

	public String htmTooltipGuardianId() {
		return null;
	}

	public String htmGuardianId() {
		return guardianId == null ? "" : StringEscapeUtils.escapeHtml4(strGuardianId());
	}

	public void htmGuardianId(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchoolGuardian", strPk(), "GuardianId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "GuardianId() {");
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
				r.l("				data: {\"setGuardianId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageGuardianId()), "</span>");
				r.s("			<input");
							r.s(" name=\"guardianId\"");
							r.s(" value=\"", htmGuardianId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmGuardianId());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
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
	protected SchoolGuardian pageUrlInit() {
		if(!pageUrlWrap.alreadyInitialized) {
			_pageUrl(pageUrlWrap);
			if(pageUrl == null)
				setPageUrl(pageUrlWrap.o);
		}
		pageUrlWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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
			r.s("<div id=\"patchSchoolGuardian", strPk(), "PageUrl\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "PageUrl() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objectSuggest">Trouver l'entité objectSuggest dans Solr</a>
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
	protected SchoolGuardian objectSuggestInit() {
		if(!objectSuggestWrap.alreadyInitialized) {
			_objectSuggest(objectSuggestWrap);
			if(objectSuggest == null)
				setObjectSuggest(objectSuggestWrap.o);
		}
		objectSuggestWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
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
			r.s("<div id=\"patchSchoolGuardian", strPk(), "ObjectSuggest\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchoolGuardian", strPk(), "ObjectSuggest() {");
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

	protected boolean alreadyInitializedSchoolGuardian = false;

	public SchoolGuardian initDeepSchoolGuardian(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolGuardian) {
			alreadyInitializedSchoolGuardian = true;
			initDeepSchoolGuardian();
		}
		return (SchoolGuardian)this;
	}

	public void initDeepSchoolGuardian() {
		super.initDeepCluster(siteRequest_);
		initSchoolGuardian();
	}

	public void initSchoolGuardian() {
		guardianKeyInit();
		enrollmentKeysInit();
		familySortInit();
		schoolSortInit();
		enrollmentSearchInit();
		inscriptionsInit();
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
		guardianCompleteNameInit();
		guardianIdInit();
		pageUrlInit();
		objectSuggestInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolGuardian(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolGuardian(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(enrollmentSearch != null)
			enrollmentSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolGuardian(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolGuardian(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolGuardian(String var) {
		SchoolGuardian oSchoolGuardian = (SchoolGuardian)this;
		switch(var) {
			case "guardianKey":
				return oSchoolGuardian.guardianKey;
			case "enrollmentKeys":
				return oSchoolGuardian.enrollmentKeys;
			case "familySort":
				return oSchoolGuardian.familySort;
			case "schoolSort":
				return oSchoolGuardian.schoolSort;
			case "enrollmentSearch":
				return oSchoolGuardian.enrollmentSearch;
			case "inscriptions":
				return oSchoolGuardian.inscriptions;
			case "schoolKeys":
				return oSchoolGuardian.schoolKeys;
			case "yearKeys":
				return oSchoolGuardian.yearKeys;
			case "seasonKeys":
				return oSchoolGuardian.seasonKeys;
			case "sessionKeys":
				return oSchoolGuardian.sessionKeys;
			case "ageKeys":
				return oSchoolGuardian.ageKeys;
			case "personFirstName":
				return oSchoolGuardian.personFirstName;
			case "personFirstNamePreferred":
				return oSchoolGuardian.personFirstNamePreferred;
			case "familyName":
				return oSchoolGuardian.familyName;
			case "personCompleteName":
				return oSchoolGuardian.personCompleteName;
			case "personCompleteNamePreferred":
				return oSchoolGuardian.personCompleteNamePreferred;
			case "personFormalName":
				return oSchoolGuardian.personFormalName;
			case "personOccupation":
				return oSchoolGuardian.personOccupation;
			case "personPhoneNumber":
				return oSchoolGuardian.personPhoneNumber;
			case "personEmail":
				return oSchoolGuardian.personEmail;
			case "personRelation":
				return oSchoolGuardian.personRelation;
			case "personSms":
				return oSchoolGuardian.personSms;
			case "personReceiveEmail":
				return oSchoolGuardian.personReceiveEmail;
			case "personEmergencyContact":
				return oSchoolGuardian.personEmergencyContact;
			case "personPickup":
				return oSchoolGuardian.personPickup;
			case "guardianCompleteName":
				return oSchoolGuardian.guardianCompleteName;
			case "guardianId":
				return oSchoolGuardian.guardianId;
			case "pageUrl":
				return oSchoolGuardian.pageUrl;
			case "objectSuggest":
				return oSchoolGuardian.objectSuggest;
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
				o = attributeSchoolGuardian(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolGuardian(String var, Object val) {
		SchoolGuardian oSchoolGuardian = (SchoolGuardian)this;
		switch(var) {
			case "enrollmentKeys":
				oSchoolGuardian.addEnrollmentKeys((Long)val);
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
					o = defineSchoolGuardian(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolGuardian(String var, String val) {
		switch(var) {
			case "personFirstName":
				setPersonFirstName(val);
				savesSchoolGuardian.add(var);
				return val;
			case "personFirstNamePreferred":
				setPersonFirstNamePreferred(val);
				savesSchoolGuardian.add(var);
				return val;
			case "familyName":
				setFamilyName(val);
				savesSchoolGuardian.add(var);
				return val;
			case "personOccupation":
				setPersonOccupation(val);
				savesSchoolGuardian.add(var);
				return val;
			case "personPhoneNumber":
				setPersonPhoneNumber(val);
				savesSchoolGuardian.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesSchoolGuardian = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolGuardian(solrDocument);
	}
	public void populateSchoolGuardian(SolrDocument solrDocument) {
		SchoolGuardian oSchoolGuardian = (SchoolGuardian)this;
		savesSchoolGuardian = (List<String>)solrDocument.get("savesSchoolGuardian_stored_strings");
		if(savesSchoolGuardian != null) {

			if(savesSchoolGuardian.contains("guardianKey")) {
				Long guardianKey = (Long)solrDocument.get("guardianKey_stored_long");
				if(guardianKey != null)
					oSchoolGuardian.setGuardianKey(guardianKey);
			}

			List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
			if(enrollmentKeys != null)
				oSchoolGuardian.enrollmentKeys.addAll(enrollmentKeys);

			if(savesSchoolGuardian.contains("familySort")) {
				Integer familySort = (Integer)solrDocument.get("familySort_stored_int");
				if(familySort != null)
					oSchoolGuardian.setFamilySort(familySort);
			}

			if(savesSchoolGuardian.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolGuardian.setSchoolSort(schoolSort);
			}

			if(savesSchoolGuardian.contains("schoolKeys")) {
				List<Long> schoolKeys = (List<Long>)solrDocument.get("schoolKeys_stored_longs");
				if(schoolKeys != null)
					oSchoolGuardian.schoolKeys.addAll(schoolKeys);
			}

			if(savesSchoolGuardian.contains("yearKeys")) {
				List<Long> yearKeys = (List<Long>)solrDocument.get("yearKeys_stored_longs");
				if(yearKeys != null)
					oSchoolGuardian.yearKeys.addAll(yearKeys);
			}

			if(savesSchoolGuardian.contains("seasonKeys")) {
				List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
				if(seasonKeys != null)
					oSchoolGuardian.seasonKeys.addAll(seasonKeys);
			}

			if(savesSchoolGuardian.contains("sessionKeys")) {
				List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
				if(sessionKeys != null)
					oSchoolGuardian.sessionKeys.addAll(sessionKeys);
			}

			if(savesSchoolGuardian.contains("ageKeys")) {
				List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
				if(ageKeys != null)
					oSchoolGuardian.ageKeys.addAll(ageKeys);
			}

			if(savesSchoolGuardian.contains("personFirstName")) {
				String personFirstName = (String)solrDocument.get("personFirstName_stored_string");
				if(personFirstName != null)
					oSchoolGuardian.setPersonFirstName(personFirstName);
			}

			if(savesSchoolGuardian.contains("personFirstNamePreferred")) {
				String personFirstNamePreferred = (String)solrDocument.get("personFirstNamePreferred_stored_string");
				if(personFirstNamePreferred != null)
					oSchoolGuardian.setPersonFirstNamePreferred(personFirstNamePreferred);
			}

			if(savesSchoolGuardian.contains("familyName")) {
				String familyName = (String)solrDocument.get("familyName_stored_string");
				if(familyName != null)
					oSchoolGuardian.setFamilyName(familyName);
			}

			if(savesSchoolGuardian.contains("personCompleteName")) {
				String personCompleteName = (String)solrDocument.get("personCompleteName_stored_string");
				if(personCompleteName != null)
					oSchoolGuardian.setPersonCompleteName(personCompleteName);
			}

			if(savesSchoolGuardian.contains("personCompleteNamePreferred")) {
				String personCompleteNamePreferred = (String)solrDocument.get("personCompleteNamePreferred_stored_string");
				if(personCompleteNamePreferred != null)
					oSchoolGuardian.setPersonCompleteNamePreferred(personCompleteNamePreferred);
			}

			if(savesSchoolGuardian.contains("personFormalName")) {
				String personFormalName = (String)solrDocument.get("personFormalName_stored_string");
				if(personFormalName != null)
					oSchoolGuardian.setPersonFormalName(personFormalName);
			}

			if(savesSchoolGuardian.contains("personOccupation")) {
				String personOccupation = (String)solrDocument.get("personOccupation_stored_string");
				if(personOccupation != null)
					oSchoolGuardian.setPersonOccupation(personOccupation);
			}

			if(savesSchoolGuardian.contains("personPhoneNumber")) {
				String personPhoneNumber = (String)solrDocument.get("personPhoneNumber_stored_string");
				if(personPhoneNumber != null)
					oSchoolGuardian.setPersonPhoneNumber(personPhoneNumber);
			}

			if(savesSchoolGuardian.contains("personEmail")) {
				String personEmail = (String)solrDocument.get("personEmail_stored_string");
				if(personEmail != null)
					oSchoolGuardian.setPersonEmail(personEmail);
			}

			if(savesSchoolGuardian.contains("personRelation")) {
				String personRelation = (String)solrDocument.get("personRelation_stored_string");
				if(personRelation != null)
					oSchoolGuardian.setPersonRelation(personRelation);
			}

			if(savesSchoolGuardian.contains("personSms")) {
				Boolean personSms = (Boolean)solrDocument.get("personSms_stored_boolean");
				if(personSms != null)
					oSchoolGuardian.setPersonSms(personSms);
			}

			if(savesSchoolGuardian.contains("personReceiveEmail")) {
				Boolean personReceiveEmail = (Boolean)solrDocument.get("personReceiveEmail_stored_boolean");
				if(personReceiveEmail != null)
					oSchoolGuardian.setPersonReceiveEmail(personReceiveEmail);
			}

			if(savesSchoolGuardian.contains("personEmergencyContact")) {
				Boolean personEmergencyContact = (Boolean)solrDocument.get("personEmergencyContact_stored_boolean");
				if(personEmergencyContact != null)
					oSchoolGuardian.setPersonEmergencyContact(personEmergencyContact);
			}

			if(savesSchoolGuardian.contains("personPickup")) {
				Boolean personPickup = (Boolean)solrDocument.get("personPickup_stored_boolean");
				if(personPickup != null)
					oSchoolGuardian.setPersonPickup(personPickup);
			}

			if(savesSchoolGuardian.contains("guardianCompleteName")) {
				String guardianCompleteName = (String)solrDocument.get("guardianCompleteName_stored_string");
				if(guardianCompleteName != null)
					oSchoolGuardian.setGuardianCompleteName(guardianCompleteName);
			}

			if(savesSchoolGuardian.contains("guardianId")) {
				String guardianId = (String)solrDocument.get("guardianId_stored_string");
				if(guardianId != null)
					oSchoolGuardian.setGuardianId(guardianId);
			}

			if(savesSchoolGuardian.contains("pageUrl")) {
				String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
				if(pageUrl != null)
					oSchoolGuardian.setPageUrl(pageUrl);
			}

			if(savesSchoolGuardian.contains("objectSuggest")) {
				String objectSuggest = (String)solrDocument.get("objectSuggest_suggested");
				oSchoolGuardian.setObjectSuggest(objectSuggest);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.guardian.SchoolGuardian"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolGuardian o = new SchoolGuardian();
			o.siteRequestSchoolGuardian(siteRequest);
			o.initDeepSchoolGuardian(siteRequest);
			o.indexSchoolGuardian();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolGuardian();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolGuardian(document);
	}

	public void indexSchoolGuardian(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolGuardian(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolGuardian() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolGuardian(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolGuardian(SolrInputDocument document) {
		if(savesSchoolGuardian != null)
			document.addField("savesSchoolGuardian_stored_strings", savesSchoolGuardian);

		if(guardianKey != null) {
			document.addField("guardianKey_indexed_long", guardianKey);
			document.addField("guardianKey_stored_long", guardianKey);
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
		if(guardianCompleteName != null) {
			document.addField("guardianCompleteName_indexed_string", guardianCompleteName);
			document.addField("guardianCompleteName_stored_string", guardianCompleteName);
		}
		if(guardianId != null) {
			document.addField("guardianId_indexed_string", guardianId);
			document.addField("guardianId_stored_string", guardianId);
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

	public void unindexSchoolGuardian() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolGuardian(siteRequest);
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
		storeSchoolGuardian(solrDocument);
	}
	public void storeSchoolGuardian(SolrDocument solrDocument) {
		SchoolGuardian oSchoolGuardian = (SchoolGuardian)this;

		Long guardianKey = (Long)solrDocument.get("guardianKey_stored_long");
		if(guardianKey != null)
			oSchoolGuardian.setGuardianKey(guardianKey);

		List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
		if(enrollmentKeys != null)
			oSchoolGuardian.enrollmentKeys.addAll(enrollmentKeys);

		Integer familySort = (Integer)solrDocument.get("familySort_stored_int");
		if(familySort != null)
			oSchoolGuardian.setFamilySort(familySort);

		Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
		if(schoolSort != null)
			oSchoolGuardian.setSchoolSort(schoolSort);

		List<Long> schoolKeys = (List<Long>)solrDocument.get("schoolKeys_stored_longs");
		if(schoolKeys != null)
			oSchoolGuardian.schoolKeys.addAll(schoolKeys);

		List<Long> yearKeys = (List<Long>)solrDocument.get("yearKeys_stored_longs");
		if(yearKeys != null)
			oSchoolGuardian.yearKeys.addAll(yearKeys);

		List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
		if(seasonKeys != null)
			oSchoolGuardian.seasonKeys.addAll(seasonKeys);

		List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
		if(sessionKeys != null)
			oSchoolGuardian.sessionKeys.addAll(sessionKeys);

		List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
		if(ageKeys != null)
			oSchoolGuardian.ageKeys.addAll(ageKeys);

		String personFirstName = (String)solrDocument.get("personFirstName_stored_string");
		if(personFirstName != null)
			oSchoolGuardian.setPersonFirstName(personFirstName);

		String personFirstNamePreferred = (String)solrDocument.get("personFirstNamePreferred_stored_string");
		if(personFirstNamePreferred != null)
			oSchoolGuardian.setPersonFirstNamePreferred(personFirstNamePreferred);

		String familyName = (String)solrDocument.get("familyName_stored_string");
		if(familyName != null)
			oSchoolGuardian.setFamilyName(familyName);

		String personCompleteName = (String)solrDocument.get("personCompleteName_stored_string");
		if(personCompleteName != null)
			oSchoolGuardian.setPersonCompleteName(personCompleteName);

		String personCompleteNamePreferred = (String)solrDocument.get("personCompleteNamePreferred_stored_string");
		if(personCompleteNamePreferred != null)
			oSchoolGuardian.setPersonCompleteNamePreferred(personCompleteNamePreferred);

		String personFormalName = (String)solrDocument.get("personFormalName_stored_string");
		if(personFormalName != null)
			oSchoolGuardian.setPersonFormalName(personFormalName);

		String personOccupation = (String)solrDocument.get("personOccupation_stored_string");
		if(personOccupation != null)
			oSchoolGuardian.setPersonOccupation(personOccupation);

		String personPhoneNumber = (String)solrDocument.get("personPhoneNumber_stored_string");
		if(personPhoneNumber != null)
			oSchoolGuardian.setPersonPhoneNumber(personPhoneNumber);

		String personEmail = (String)solrDocument.get("personEmail_stored_string");
		if(personEmail != null)
			oSchoolGuardian.setPersonEmail(personEmail);

		String personRelation = (String)solrDocument.get("personRelation_stored_string");
		if(personRelation != null)
			oSchoolGuardian.setPersonRelation(personRelation);

		Boolean personSms = (Boolean)solrDocument.get("personSms_stored_boolean");
		if(personSms != null)
			oSchoolGuardian.setPersonSms(personSms);

		Boolean personReceiveEmail = (Boolean)solrDocument.get("personReceiveEmail_stored_boolean");
		if(personReceiveEmail != null)
			oSchoolGuardian.setPersonReceiveEmail(personReceiveEmail);

		Boolean personEmergencyContact = (Boolean)solrDocument.get("personEmergencyContact_stored_boolean");
		if(personEmergencyContact != null)
			oSchoolGuardian.setPersonEmergencyContact(personEmergencyContact);

		Boolean personPickup = (Boolean)solrDocument.get("personPickup_stored_boolean");
		if(personPickup != null)
			oSchoolGuardian.setPersonPickup(personPickup);

		String guardianCompleteName = (String)solrDocument.get("guardianCompleteName_stored_string");
		if(guardianCompleteName != null)
			oSchoolGuardian.setGuardianCompleteName(guardianCompleteName);

		String guardianId = (String)solrDocument.get("guardianId_stored_string");
		if(guardianId != null)
			oSchoolGuardian.setGuardianId(guardianId);

		String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
		if(pageUrl != null)
			oSchoolGuardian.setPageUrl(pageUrl);

		String objectSuggest = (String)solrDocument.get("objectSuggest_suggested");
		oSchoolGuardian.setObjectSuggest(objectSuggest);

		super.storeCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), enrollmentKeys, personFirstName, personFirstNamePreferred, familyName, personOccupation, personPhoneNumber);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolGuardian))
			return false;
		SchoolGuardian that = (SchoolGuardian)o;
		return super.equals(o)
				&& Objects.equals( enrollmentKeys, that.enrollmentKeys )
				&& Objects.equals( personFirstName, that.personFirstName )
				&& Objects.equals( personFirstNamePreferred, that.personFirstNamePreferred )
				&& Objects.equals( familyName, that.familyName )
				&& Objects.equals( personOccupation, that.personOccupation )
				&& Objects.equals( personPhoneNumber, that.personPhoneNumber );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolGuardian { ");
		sb.append( "enrollmentKeys: " ).append(enrollmentKeys);
		sb.append( ", personFirstName: \"" ).append(personFirstName).append( "\"" );
		sb.append( ", personFirstNamePreferred: \"" ).append(personFirstNamePreferred).append( "\"" );
		sb.append( ", familyName: \"" ).append(familyName).append( "\"" );
		sb.append( ", personOccupation: \"" ).append(personOccupation).append( "\"" );
		sb.append( ", personPhoneNumber: \"" ).append(personPhoneNumber).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
