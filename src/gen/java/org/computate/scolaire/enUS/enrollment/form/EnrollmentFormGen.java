package org.computate.scolaire.enUS.enrollment.form;

import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.form.part.FormPart;
import java.lang.Long;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.year.SchoolYear;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EnrollmentFormGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentForm.class);

	public static final String EnrollmentForm_UnNom = "an enrollment form";
	public static final String EnrollmentForm_Ce = "this ";
	public static final String EnrollmentForm_CeNom = "this enrollment form";
	public static final String EnrollmentForm_Un = "a ";
	public static final String EnrollmentForm_LeNom = "theenrollment form";
	public static final String EnrollmentForm_NomSingulier = "enrollment form";
	public static final String EnrollmentForm_NomPluriel = "enrollment forms";
	public static final String EnrollmentForm_NomActuel = "current enrollment form";
	public static final String EnrollmentForm_TousNom = "all the enrollment forms";
	public static final String EnrollmentForm_RechercherTousNomPar = "search enrollment forms by ";
	public static final String EnrollmentForm_LesNoms = "the enrollment forms";
	public static final String EnrollmentForm_AucunNomTrouve = "no enrollment form found";
	public static final String EnrollmentForm_NomVar = "enrollmentForm";
	public static final String EnrollmentForm_DeNom = "of enrollment form";
	public static final String EnrollmentForm_UnNomAdjectif = "an enrollment form";
	public static final String EnrollmentForm_NomAdjectifSingulier = "enrollment form";
	public static final String EnrollmentForm_NomAdjectifPluriel = "enrollment forms";
	public static final String EnrollmentForm_Couleur = "indigo";
	public static final String EnrollmentForm_IconeGroupe = "regular";
	public static final String EnrollmentForm_IconeNom = "bell";

	///////////////////////
	// enrollmentFormKey //
	///////////////////////

	/**	L'entité « enrollmentFormKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long enrollmentFormKey;
	@JsonIgnore
	public Wrap<Long> enrollmentFormKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("enrollmentFormKey").o(enrollmentFormKey);

	/**	<br/>L'entité « enrollmentFormKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentFormKey">Trouver l'entité enrollmentFormKey dans Solr</a>
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
	public EnrollmentForm setEnrollmentFormKey(String o) {
		if(NumberUtils.isParsable(o))
			this.enrollmentFormKey = Long.parseLong(o);
		this.enrollmentFormKeyWrap.alreadyInitialized = true;
		return (EnrollmentForm)this;
	}
	protected EnrollmentForm enrollmentFormKeyInit() {
		if(!enrollmentFormKeyWrap.alreadyInitialized) {
			_enrollmentFormKey(enrollmentFormKeyWrap);
			if(enrollmentFormKey == null)
				setEnrollmentFormKey(enrollmentFormKeyWrap.o);
		}
		enrollmentFormKeyWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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
		return "key";
	}

	public String htmTooltipEnrollmentFormKey() {
		return null;
	}

	public String htmEnrollmentFormKey() {
		return enrollmentFormKey == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentFormKey());
	}

	public void htmEnrollmentFormKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchEnrollmentForm", strPk(), "EnrollmentFormKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "EnrollmentFormKey() {");
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
				r.l("				data: {\"setEnrollmentFormKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentFormKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentFormKey\"");
							r.s(" value=\"", htmEnrollmentFormKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentFormKey());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// formPartKeys //
	//////////////////

	/**	L'entité « formPartKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> formPartKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> formPartKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("formPartKeys").o(formPartKeys);

	/**	<br/>L'entité « formPartKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:formPartKeys">Trouver l'entité formPartKeys dans Solr</a>
	 * <br/>
	 * @param formPartKeys est l'entité déjà construit. 
	 **/
	protected abstract void _formPartKeys(List<Long> o);

	public List<Long> getFormPartKeys() {
		return formPartKeys;
	}

	public void setFormPartKeys(List<Long> formPartKeys) {
		this.formPartKeys = formPartKeys;
		this.formPartKeysWrap.alreadyInitialized = true;
	}
	public EnrollmentForm addFormPartKeys(Long...objets) {
		for(Long o : objets) {
			addFormPartKeys(o);
		}
		return (EnrollmentForm)this;
	}
	public EnrollmentForm addFormPartKeys(Long o) {
		if(o != null && !formPartKeys.contains(o))
			this.formPartKeys.add(o);
		return (EnrollmentForm)this;
	}
	public EnrollmentForm setFormPartKeys(JsonArray objets) {
		formPartKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addFormPartKeys(o);
		}
		return (EnrollmentForm)this;
	}
	public EnrollmentForm addFormPartKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addFormPartKeys(p);
		}
		return (EnrollmentForm)this;
	}
	protected EnrollmentForm formPartKeysInit() {
		if(!formPartKeysWrap.alreadyInitialized) {
			_formPartKeys(formPartKeys);
		}
		formPartKeysWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
	}

	public List<Long> solrFormPartKeys() {
		return formPartKeys;
	}

	public String strFormPartKeys() {
		return formPartKeys == null ? "" : formPartKeys.toString();
	}

	public String jsonFormPartKeys() {
		return formPartKeys == null ? "" : formPartKeys.toString();
	}

	public String nomAffichageFormPartKeys() {
		return "parts";
	}

	public String htmTooltipFormPartKeys() {
		return null;
	}

	public String htmFormPartKeys() {
		return formPartKeys == null ? "" : StringEscapeUtils.escapeHtml4(strFormPartKeys());
	}

	public void htmFormPartKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchEnrollmentForm", strPk(), "FormPartKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "FormPartKeys() {");
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
				r.l("				data: {\"setFormPartKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFormPartKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"formPartKeys\"");
							r.s(" value=\"", htmFormPartKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFormPartKeys());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Trouver l'entité enrollmentKeys dans Solr</a>
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
	public EnrollmentForm addEnrollmentKeys(Long...objets) {
		for(Long o : objets) {
			addEnrollmentKeys(o);
		}
		return (EnrollmentForm)this;
	}
	public EnrollmentForm addEnrollmentKeys(Long o) {
		if(o != null && !enrollmentKeys.contains(o))
			this.enrollmentKeys.add(o);
		return (EnrollmentForm)this;
	}
	public EnrollmentForm setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
		return (EnrollmentForm)this;
	}
	public EnrollmentForm addEnrollmentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnrollmentKeys(p);
		}
		return (EnrollmentForm)this;
	}
	protected EnrollmentForm enrollmentKeysInit() {
		if(!enrollmentKeysWrap.alreadyInitialized) {
			_enrollmentKeys(enrollmentKeys);
		}
		enrollmentKeysWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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
			r.s("<div id=\"patchEnrollmentForm", strPk(), "EnrollmentKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "EnrollmentKeys() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSearch">Trouver l'entité yearSearch dans Solr</a>
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
	protected EnrollmentForm yearSearchInit() {
		if(!yearSearchWrap.alreadyInitialized) {
			_yearSearch(yearSearch);
		}
		yearSearch.initDeepForClass(siteRequest_);
		yearSearchWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:year_">Trouver l'entité year_ dans Solr</a>
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
	protected EnrollmentForm year_Init() {
		if(!year_Wrap.alreadyInitialized) {
			_year_(year_Wrap);
			if(year_ == null)
				setYear_(year_Wrap.o);
		}
		year_Wrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
	}

	////////////////////
	// formPartSearch //
	////////////////////

	/**	L'entité « formPartSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<FormPart>(). 
	 */
	@JsonIgnore
	protected SearchList<FormPart> formPartSearch = new SearchList<FormPart>();
	@JsonIgnore
	public Wrap<SearchList<FormPart>> formPartSearchWrap = new Wrap<SearchList<FormPart>>().p(this).c(SearchList.class).var("formPartSearch").o(formPartSearch);

	/**	<br/>L'entité « formPartSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<FormPart>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:formPartSearch">Trouver l'entité formPartSearch dans Solr</a>
	 * <br/>
	 * @param formPartSearch est l'entité déjà construit. 
	 **/
	protected abstract void _formPartSearch(SearchList<FormPart> l);

	public SearchList<FormPart> getFormPartSearch() {
		return formPartSearch;
	}

	public void setFormPartSearch(SearchList<FormPart> formPartSearch) {
		this.formPartSearch = formPartSearch;
		this.formPartSearchWrap.alreadyInitialized = true;
	}
	protected EnrollmentForm formPartSearchInit() {
		if(!formPartSearchWrap.alreadyInitialized) {
			_formPartSearch(formPartSearch);
		}
		formPartSearch.initDeepForClass(siteRequest_);
		formPartSearchWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
	}

	//////////////////
	// partFormList //
	//////////////////

	/**	L'entité « partFormList »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected List<FormPart> partFormList;
	@JsonIgnore
	public Wrap<List<FormPart>> partFormListWrap = new Wrap<List<FormPart>>().p(this).c(List.class).var("partFormList").o(partFormList);

	/**	<br/>L'entité « partFormList »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:partFormList">Trouver l'entité partFormList dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _partFormList(Wrap<List<FormPart>> c);

	public List<FormPart> getPartFormList() {
		return partFormList;
	}

	public void setPartFormList(List<FormPart> partFormList) {
		this.partFormList = partFormList;
		this.partFormListWrap.alreadyInitialized = true;
	}
	public EnrollmentForm addPartFormList(FormPart...objets) {
		for(FormPart o : objets) {
			addPartFormList(o);
		}
		return (EnrollmentForm)this;
	}
	public EnrollmentForm addPartFormList(FormPart o) {
		if(o != null && !partFormList.contains(o))
			this.partFormList.add(o);
		return (EnrollmentForm)this;
	}
	protected EnrollmentForm partFormListInit() {
		if(!partFormListWrap.alreadyInitialized) {
			_partFormList(partFormListWrap);
			if(partFormList == null)
				setPartFormList(partFormListWrap.o);
		}
		partFormListWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Trouver l'entité schoolKey dans Solr</a>
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
	public EnrollmentForm setSchoolKey(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolKey = Long.parseLong(o);
		this.schoolKeyWrap.alreadyInitialized = true;
		return (EnrollmentForm)this;
	}
	protected EnrollmentForm schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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
			r.s("<div id=\"patchEnrollmentForm", strPk(), "SchoolKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "SchoolKey() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Trouver l'entité yearKey dans Solr</a>
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
	public EnrollmentForm setYearKey(String o) {
		if(NumberUtils.isParsable(o))
			this.yearKey = Long.parseLong(o);
		this.yearKeyWrap.alreadyInitialized = true;
		return (EnrollmentForm)this;
	}
	protected EnrollmentForm yearKeyInit() {
		if(!yearKeyWrap.alreadyInitialized) {
			_yearKey(yearKeyWrap);
			if(yearKey == null)
				setYearKey(yearKeyWrap.o);
		}
		yearKeyWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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
			r.s("<div id=\"patchEnrollmentForm", strPk(), "YearKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "YearKey() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Trouver l'entité schoolCompleteName dans Solr</a>
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
	protected EnrollmentForm schoolCompleteNameInit() {
		if(!schoolCompleteNameWrap.alreadyInitialized) {
			_schoolCompleteName(schoolCompleteNameWrap);
			if(schoolCompleteName == null)
				setSchoolCompleteName(schoolCompleteNameWrap.o);
		}
		schoolCompleteNameWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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

	public void htmSchoolCompleteName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchEnrollmentForm", strPk(), "SchoolCompleteName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "SchoolCompleteName() {");
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
				r.l("				data: {\"setSchoolCompleteName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolCompleteName()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolCompleteName\"");
							r.s(" value=\"", htmSchoolCompleteName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolCompleteName());
			}
			r.l("</div>");
		}
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Trouver l'entité schoolLocation dans Solr</a>
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
	protected EnrollmentForm schoolLocationInit() {
		if(!schoolLocationWrap.alreadyInitialized) {
			_schoolLocation(schoolLocationWrap);
			if(schoolLocation == null)
				setSchoolLocation(schoolLocationWrap.o);
		}
		schoolLocationWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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

	public void htmSchoolLocation(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchEnrollmentForm", strPk(), "SchoolLocation\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "SchoolLocation() {");
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
				r.l("				data: {\"setSchoolLocation\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolLocation()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolLocation\"");
							r.s(" value=\"", htmSchoolLocation(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolLocation());
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
	protected Integer yearStart;
	@JsonIgnore
	public Wrap<Integer> yearStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearStart").o(yearStart);

	/**	<br/>L'entité « yearStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Trouver l'entité yearStart dans Solr</a>
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
	public EnrollmentForm setYearStart(String o) {
		if(NumberUtils.isParsable(o))
			this.yearStart = Integer.parseInt(o);
		this.yearStartWrap.alreadyInitialized = true;
		return (EnrollmentForm)this;
	}
	protected EnrollmentForm yearStartInit() {
		if(!yearStartWrap.alreadyInitialized) {
			_yearStart(yearStartWrap);
			if(yearStart == null)
				setYearStart(yearStartWrap.o);
		}
		yearStartWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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

	public void htmYearStart(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchEnrollmentForm", strPk(), "YearStart\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "YearStart() {");
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
	protected Integer yearEnd;
	@JsonIgnore
	public Wrap<Integer> yearEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearEnd").o(yearEnd);

	/**	<br/>L'entité « yearEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Trouver l'entité yearEnd dans Solr</a>
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
	public EnrollmentForm setYearEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.yearEnd = Integer.parseInt(o);
		this.yearEndWrap.alreadyInitialized = true;
		return (EnrollmentForm)this;
	}
	protected EnrollmentForm yearEndInit() {
		if(!yearEndWrap.alreadyInitialized) {
			_yearEnd(yearEndWrap);
			if(yearEnd == null)
				setYearEnd(yearEndWrap.o);
		}
		yearEndWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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

	public void htmYearEnd(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchEnrollmentForm", strPk(), "YearEnd\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "YearEnd() {");
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
	// yearShortName //
	///////////////////

	/**	L'entité « yearShortName »
	 *	 is defined as null before being initialized. 
	 */
	protected String yearShortName;
	@JsonIgnore
	public Wrap<String> yearShortNameWrap = new Wrap<String>().p(this).c(String.class).var("yearShortName").o(yearShortName);

	/**	<br/>L'entité « yearShortName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearShortName">Trouver l'entité yearShortName dans Solr</a>
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
	protected EnrollmentForm yearShortNameInit() {
		if(!yearShortNameWrap.alreadyInitialized) {
			_yearShortName(yearShortNameWrap);
			if(yearShortName == null)
				setYearShortName(yearShortNameWrap.o);
		}
		yearShortNameWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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

	public void htmYearShortName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchEnrollmentForm", strPk(), "YearShortName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "YearShortName() {");
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
				r.l("				data: {\"setYearShortName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearShortName()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearShortName\"");
							r.s(" value=\"", htmYearShortName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearShortName());
			}
			r.l("</div>");
		}
	}

	//////////////////////
	// yearCompleteName //
	//////////////////////

	/**	L'entité « yearCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String yearCompleteName;
	@JsonIgnore
	public Wrap<String> yearCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("yearCompleteName").o(yearCompleteName);

	/**	<br/>L'entité « yearCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearCompleteName">Trouver l'entité yearCompleteName dans Solr</a>
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
	protected EnrollmentForm yearCompleteNameInit() {
		if(!yearCompleteNameWrap.alreadyInitialized) {
			_yearCompleteName(yearCompleteNameWrap);
			if(yearCompleteName == null)
				setYearCompleteName(yearCompleteNameWrap.o);
		}
		yearCompleteNameWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
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

	public void htmYearCompleteName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchEnrollmentForm", strPk(), "YearCompleteName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "YearCompleteName() {");
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
				r.l("				data: {\"setYearCompleteName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageYearCompleteName()), "</span>");
				r.s("			<input");
							r.s(" name=\"yearCompleteName\"");
							r.s(" value=\"", htmYearCompleteName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmYearCompleteName());
			}
			r.l("</div>");
		}
	}

	////////////////////////////////
	// enrollmentFormCompleteName //
	////////////////////////////////

	/**	L'entité « enrollmentFormCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	protected String enrollmentFormCompleteName;
	@JsonIgnore
	public Wrap<String> enrollmentFormCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("enrollmentFormCompleteName").o(enrollmentFormCompleteName);

	/**	<br/>L'entité « enrollmentFormCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.form.EnrollmentForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentFormCompleteName">Trouver l'entité enrollmentFormCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentFormCompleteName(Wrap<String> c);

	public String getEnrollmentFormCompleteName() {
		return enrollmentFormCompleteName;
	}

	public void setEnrollmentFormCompleteName(String enrollmentFormCompleteName) {
		this.enrollmentFormCompleteName = enrollmentFormCompleteName;
		this.enrollmentFormCompleteNameWrap.alreadyInitialized = true;
	}
	protected EnrollmentForm enrollmentFormCompleteNameInit() {
		if(!enrollmentFormCompleteNameWrap.alreadyInitialized) {
			_enrollmentFormCompleteName(enrollmentFormCompleteNameWrap);
			if(enrollmentFormCompleteName == null)
				setEnrollmentFormCompleteName(enrollmentFormCompleteNameWrap.o);
		}
		enrollmentFormCompleteNameWrap.alreadyInitialized(true);
		return (EnrollmentForm)this;
	}

	public String solrEnrollmentFormCompleteName() {
		return enrollmentFormCompleteName;
	}

	public String strEnrollmentFormCompleteName() {
		return enrollmentFormCompleteName == null ? "" : enrollmentFormCompleteName;
	}

	public String jsonEnrollmentFormCompleteName() {
		return enrollmentFormCompleteName == null ? "" : enrollmentFormCompleteName;
	}

	public String nomAffichageEnrollmentFormCompleteName() {
		return "name";
	}

	public String htmTooltipEnrollmentFormCompleteName() {
		return null;
	}

	public String htmEnrollmentFormCompleteName() {
		return enrollmentFormCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentFormCompleteName());
	}

	public void htmEnrollmentFormCompleteName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchEnrollmentForm", strPk(), "EnrollmentFormCompleteName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnrollmentForm", strPk(), "EnrollmentFormCompleteName() {");
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
				r.l("				data: {\"setEnrollmentFormCompleteName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentFormCompleteName()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentFormCompleteName\"");
							r.s(" value=\"", htmEnrollmentFormCompleteName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentFormCompleteName());
			}
			r.l("</div>");
		}
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedEnrollmentForm = false;

	public EnrollmentForm initDeepEnrollmentForm(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedEnrollmentForm) {
			alreadyInitializedEnrollmentForm = true;
			initDeepEnrollmentForm();
		}
		return (EnrollmentForm)this;
	}

	public void initDeepEnrollmentForm() {
		initEnrollmentForm();
		super.initDeepCluster(siteRequest_);
	}

	public void initEnrollmentForm() {
		enrollmentFormKeyInit();
		formPartKeysInit();
		enrollmentKeysInit();
		yearSearchInit();
		year_Init();
		formPartSearchInit();
		partFormListInit();
		schoolKeyInit();
		yearKeyInit();
		schoolCompleteNameInit();
		schoolLocationInit();
		yearStartInit();
		yearEndInit();
		yearShortNameInit();
		yearCompleteNameInit();
		enrollmentFormCompleteNameInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepEnrollmentForm(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestEnrollmentForm(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(yearSearch != null)
			yearSearch.setSiteRequest_(siteRequest_);
		if(formPartSearch != null)
			formPartSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestEnrollmentForm(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainEnrollmentForm(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainEnrollmentForm(String var) {
		EnrollmentForm oEnrollmentForm = (EnrollmentForm)this;
		switch(var) {
			case "enrollmentFormKey":
				return oEnrollmentForm.enrollmentFormKey;
			case "formPartKeys":
				return oEnrollmentForm.formPartKeys;
			case "enrollmentKeys":
				return oEnrollmentForm.enrollmentKeys;
			case "yearSearch":
				return oEnrollmentForm.yearSearch;
			case "year_":
				return oEnrollmentForm.year_;
			case "formPartSearch":
				return oEnrollmentForm.formPartSearch;
			case "partFormList":
				return oEnrollmentForm.partFormList;
			case "schoolKey":
				return oEnrollmentForm.schoolKey;
			case "yearKey":
				return oEnrollmentForm.yearKey;
			case "schoolCompleteName":
				return oEnrollmentForm.schoolCompleteName;
			case "schoolLocation":
				return oEnrollmentForm.schoolLocation;
			case "yearStart":
				return oEnrollmentForm.yearStart;
			case "yearEnd":
				return oEnrollmentForm.yearEnd;
			case "yearShortName":
				return oEnrollmentForm.yearShortName;
			case "yearCompleteName":
				return oEnrollmentForm.yearCompleteName;
			case "enrollmentFormCompleteName":
				return oEnrollmentForm.enrollmentFormCompleteName;
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
				o = attributeEnrollmentForm(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeEnrollmentForm(String var, Object val) {
		EnrollmentForm oEnrollmentForm = (EnrollmentForm)this;
		switch(var) {
			case "formPartKeys":
				oEnrollmentForm.addFormPartKeys((Long)val);
				return val;
			case "enrollmentKeys":
				oEnrollmentForm.addEnrollmentKeys((Long)val);
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
					o = defineEnrollmentForm(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineEnrollmentForm(String var, String val) {
		switch(var) {
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesEnrollmentForm = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateEnrollmentForm(solrDocument);
	}
	public void populateEnrollmentForm(SolrDocument solrDocument) {
		EnrollmentForm oEnrollmentForm = (EnrollmentForm)this;
		savesEnrollmentForm = (List<String>)solrDocument.get("savesEnrollmentForm_stored_strings");
		if(savesEnrollmentForm != null) {

			if(savesEnrollmentForm.contains("enrollmentFormKey")) {
				Long enrollmentFormKey = (Long)solrDocument.get("enrollmentFormKey_stored_long");
				if(enrollmentFormKey != null)
					oEnrollmentForm.setEnrollmentFormKey(enrollmentFormKey);
			}

			List<Long> formPartKeys = (List<Long>)solrDocument.get("formPartKeys_stored_longs");
			if(formPartKeys != null)
				oEnrollmentForm.formPartKeys.addAll(formPartKeys);

			List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
			if(enrollmentKeys != null)
				oEnrollmentForm.enrollmentKeys.addAll(enrollmentKeys);

			if(savesEnrollmentForm.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oEnrollmentForm.setSchoolKey(schoolKey);
			}

			if(savesEnrollmentForm.contains("yearKey")) {
				Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
				if(yearKey != null)
					oEnrollmentForm.setYearKey(yearKey);
			}

			if(savesEnrollmentForm.contains("schoolCompleteName")) {
				String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
				if(schoolCompleteName != null)
					oEnrollmentForm.setSchoolCompleteName(schoolCompleteName);
			}

			if(savesEnrollmentForm.contains("schoolLocation")) {
				String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
				if(schoolLocation != null)
					oEnrollmentForm.setSchoolLocation(schoolLocation);
			}

			if(savesEnrollmentForm.contains("yearStart")) {
				Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
				if(yearStart != null)
					oEnrollmentForm.setYearStart(yearStart);
			}

			if(savesEnrollmentForm.contains("yearEnd")) {
				Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
				if(yearEnd != null)
					oEnrollmentForm.setYearEnd(yearEnd);
			}

			if(savesEnrollmentForm.contains("yearShortName")) {
				String yearShortName = (String)solrDocument.get("yearShortName_stored_string");
				if(yearShortName != null)
					oEnrollmentForm.setYearShortName(yearShortName);
			}

			if(savesEnrollmentForm.contains("yearCompleteName")) {
				String yearCompleteName = (String)solrDocument.get("yearCompleteName_stored_string");
				if(yearCompleteName != null)
					oEnrollmentForm.setYearCompleteName(yearCompleteName);
			}

			if(savesEnrollmentForm.contains("enrollmentFormCompleteName")) {
				String enrollmentFormCompleteName = (String)solrDocument.get("enrollmentFormCompleteName_stored_string");
				if(enrollmentFormCompleteName != null)
					oEnrollmentForm.setEnrollmentFormCompleteName(enrollmentFormCompleteName);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.enrollment.form.EnrollmentForm"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			EnrollmentForm o = new EnrollmentForm();
			o.siteRequestEnrollmentForm(siteRequest);
			o.initDeepEnrollmentForm(siteRequest);
			o.indexEnrollmentForm();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexEnrollmentForm();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexEnrollmentForm(document);
	}

	public void indexEnrollmentForm(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexEnrollmentForm(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexEnrollmentForm() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexEnrollmentForm(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexEnrollmentForm(SolrInputDocument document) {
		if(savesEnrollmentForm != null)
			document.addField("savesEnrollmentForm_stored_strings", savesEnrollmentForm);

		if(enrollmentFormKey != null) {
			document.addField("enrollmentFormKey_indexed_long", enrollmentFormKey);
			document.addField("enrollmentFormKey_stored_long", enrollmentFormKey);
		}
		if(formPartKeys != null) {
			for(java.lang.Long o : formPartKeys) {
				document.addField("formPartKeys_indexed_longs", o);
			}
			for(java.lang.Long o : formPartKeys) {
				document.addField("formPartKeys_stored_longs", o);
			}
		}
		if(enrollmentKeys != null) {
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_indexed_longs", o);
			}
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_stored_longs", o);
			}
		}
		if(schoolKey != null) {
			document.addField("schoolKey_indexed_long", schoolKey);
			document.addField("schoolKey_stored_long", schoolKey);
		}
		if(yearKey != null) {
			document.addField("yearKey_indexed_long", yearKey);
			document.addField("yearKey_stored_long", yearKey);
		}
		if(schoolCompleteName != null) {
			document.addField("schoolCompleteName_indexed_string", schoolCompleteName);
			document.addField("schoolCompleteName_stored_string", schoolCompleteName);
		}
		if(schoolLocation != null) {
			document.addField("schoolLocation_indexed_string", schoolLocation);
			document.addField("schoolLocation_stored_string", schoolLocation);
		}
		if(yearStart != null) {
			document.addField("yearStart_indexed_int", yearStart);
			document.addField("yearStart_stored_int", yearStart);
		}
		if(yearEnd != null) {
			document.addField("yearEnd_indexed_int", yearEnd);
			document.addField("yearEnd_stored_int", yearEnd);
		}
		if(yearShortName != null) {
			document.addField("yearShortName_indexed_string", yearShortName);
			document.addField("yearShortName_stored_string", yearShortName);
		}
		if(yearCompleteName != null) {
			document.addField("yearCompleteName_indexed_string", yearCompleteName);
			document.addField("yearCompleteName_stored_string", yearCompleteName);
		}
		if(enrollmentFormCompleteName != null) {
			document.addField("enrollmentFormCompleteName_indexed_string", enrollmentFormCompleteName);
			document.addField("enrollmentFormCompleteName_stored_string", enrollmentFormCompleteName);
		}
		super.indexCluster(document);

	}

	public void unindexEnrollmentForm() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepEnrollmentForm(siteRequest);
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
		storeEnrollmentForm(solrDocument);
	}
	public void storeEnrollmentForm(SolrDocument solrDocument) {
		EnrollmentForm oEnrollmentForm = (EnrollmentForm)this;

		Long enrollmentFormKey = (Long)solrDocument.get("enrollmentFormKey_stored_long");
		if(enrollmentFormKey != null)
			oEnrollmentForm.setEnrollmentFormKey(enrollmentFormKey);

		List<Long> formPartKeys = (List<Long>)solrDocument.get("formPartKeys_stored_longs");
		if(formPartKeys != null)
			oEnrollmentForm.formPartKeys.addAll(formPartKeys);

		List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
		if(enrollmentKeys != null)
			oEnrollmentForm.enrollmentKeys.addAll(enrollmentKeys);

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oEnrollmentForm.setSchoolKey(schoolKey);

		Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
		if(yearKey != null)
			oEnrollmentForm.setYearKey(yearKey);

		String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
		if(schoolCompleteName != null)
			oEnrollmentForm.setSchoolCompleteName(schoolCompleteName);

		String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
		if(schoolLocation != null)
			oEnrollmentForm.setSchoolLocation(schoolLocation);

		Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
		if(yearStart != null)
			oEnrollmentForm.setYearStart(yearStart);

		Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
		if(yearEnd != null)
			oEnrollmentForm.setYearEnd(yearEnd);

		String yearShortName = (String)solrDocument.get("yearShortName_stored_string");
		if(yearShortName != null)
			oEnrollmentForm.setYearShortName(yearShortName);

		String yearCompleteName = (String)solrDocument.get("yearCompleteName_stored_string");
		if(yearCompleteName != null)
			oEnrollmentForm.setYearCompleteName(yearCompleteName);

		String enrollmentFormCompleteName = (String)solrDocument.get("enrollmentFormCompleteName_stored_string");
		if(enrollmentFormCompleteName != null)
			oEnrollmentForm.setEnrollmentFormCompleteName(enrollmentFormCompleteName);

		super.storeCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), formPartKeys, enrollmentKeys);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof EnrollmentForm))
			return false;
		EnrollmentForm that = (EnrollmentForm)o;
		return super.equals(o)
				&& Objects.equals( formPartKeys, that.formPartKeys )
				&& Objects.equals( enrollmentKeys, that.enrollmentKeys );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EnrollmentForm { ");
		sb.append( "formPartKeys: " ).append(formPartKeys);
		sb.append( ", enrollmentKeys: " ).append(enrollmentKeys);
		sb.append(" }");
		return sb.toString();
	}
}
