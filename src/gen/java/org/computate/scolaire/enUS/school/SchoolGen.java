package org.computate.scolaire.enUS.school;

import java.lang.Double;
import java.util.Date;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
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
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(School.class);

	public static final String School_UnNom = "a school";
	public static final String School_Ce = "this ";
	public static final String School_CeNom = "this school";
	public static final String School_Un = "an ";
	public static final String School_LeNom = "the school";
	public static final String School_NomSingulier = "school";
	public static final String School_NomPluriel = "schools";
	public static final String School_NomActuel = "current school";
	public static final String School_TousNom = "the schools";
	public static final String School_RechercherTousNomPar = "search schools by ";
	public static final String School_LesNoms = "the schools";
	public static final String School_AucunNomTrouve = "no school found";
	public static final String School_NomVar = "school";
	public static final String School_DeNom = "of school";
	public static final String School_Couleur = "pink";
	public static final String School_IconeGroupe = "duotone";
	public static final String School_IconeNom = "school";

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Trouver l'entité schoolKey dans Solr</a>
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
	public School setSchoolKey(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.schoolKey = Long.parseLong(o);
		this.schoolKeyWrap.alreadyInitialized = true;
		return (School)this;
	}
	protected School schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (School)this;
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
		return "key";
	}

	public String htmTooltipSchoolKey() {
		return null;
	}

	public String htmSchoolKey() {
		return schoolKey == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolKey());
	}

	public void htmSchoolKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SchoolKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SchoolKey() {");
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

	///////////////
	// childKeys //
	///////////////

	/**	L'entité « childKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> childKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> childKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("childKeys").o(childKeys);

	/**	<br/>L'entité « childKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childKeys">Trouver l'entité childKeys dans Solr</a>
	 * <br/>
	 * @param childKeys est l'entité déjà construit. 
	 **/
	protected abstract void _childKeys(List<Long> o);

	public List<Long> getChildKeys() {
		return childKeys;
	}

	public void setChildKeys(List<Long> childKeys) {
		this.childKeys = childKeys;
		this.childKeysWrap.alreadyInitialized = true;
	}
	public School addChildKeys(Long...objets) {
		for(Long o : objets) {
			addChildKeys(o);
		}
		return (School)this;
	}
	public School addChildKeys(Long o) {
		if(o != null && !childKeys.contains(o))
			this.childKeys.add(o);
		return (School)this;
	}
	public School setChildKeys(JsonArray objets) {
		childKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addChildKeys(o);
		}
		return (School)this;
	}
	public School addChildKeys(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addChildKeys(p);
		}
		return (School)this;
	}
	protected School childKeysInit() {
		if(!childKeysWrap.alreadyInitialized) {
			_childKeys(childKeys);
		}
		childKeysWrap.alreadyInitialized(true);
		return (School)this;
	}

	public List<Long> solrChildKeys() {
		return childKeys;
	}

	public String strChildKeys() {
		return childKeys == null ? "" : childKeys.toString();
	}

	public String jsonChildKeys() {
		return childKeys == null ? "" : childKeys.toString();
	}

	public String nomAffichageChildKeys() {
		return "";
	}

	public String htmTooltipChildKeys() {
		return null;
	}

	public String htmChildKeys() {
		return childKeys == null ? "" : StringEscapeUtils.escapeHtml4(strChildKeys());
	}

	public void htmChildKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "ChildKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "ChildKeys() {");
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
				r.l("				data: {\"setChildKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageChildKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"childKeys\"");
							r.s(" value=\"", htmChildKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmChildKeys());
			}
			r.l("</div>");
		}
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockKeys">Trouver l'entité blockKeys dans Solr</a>
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
	public School addBlockKeys(Long...objets) {
		for(Long o : objets) {
			addBlockKeys(o);
		}
		return (School)this;
	}
	public School addBlockKeys(Long o) {
		if(o != null && !blockKeys.contains(o))
			this.blockKeys.add(o);
		return (School)this;
	}
	public School setBlockKeys(JsonArray objets) {
		blockKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlockKeys(o);
		}
		return (School)this;
	}
	public School addBlockKeys(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addBlockKeys(p);
		}
		return (School)this;
	}
	protected School blockKeysInit() {
		if(!blockKeysWrap.alreadyInitialized) {
			_blockKeys(blockKeys);
		}
		blockKeysWrap.alreadyInitialized(true);
		return (School)this;
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
		return "";
	}

	public String htmTooltipBlockKeys() {
		return null;
	}

	public String htmBlockKeys() {
		return blockKeys == null ? "" : StringEscapeUtils.escapeHtml4(strBlockKeys());
	}

	public void htmBlockKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "BlockKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "BlockKeys() {");
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
				r.l("				data: {\"setBlockKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlockKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"blockKeys\"");
							r.s(" value=\"", htmBlockKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlockKeys());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// ageGroupKeys //
	//////////////////

	/**	L'entité « ageGroupKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> ageGroupKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> ageGroupKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("ageGroupKeys").o(ageGroupKeys);

	/**	<br/>L'entité « ageGroupKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageGroupKeys">Trouver l'entité ageGroupKeys dans Solr</a>
	 * <br/>
	 * @param ageGroupKeys est l'entité déjà construit. 
	 **/
	protected abstract void _ageGroupKeys(List<Long> o);

	public List<Long> getAgeGroupKeys() {
		return ageGroupKeys;
	}

	public void setAgeGroupKeys(List<Long> ageGroupKeys) {
		this.ageGroupKeys = ageGroupKeys;
		this.ageGroupKeysWrap.alreadyInitialized = true;
	}
	public School addAgeGroupKeys(Long...objets) {
		for(Long o : objets) {
			addAgeGroupKeys(o);
		}
		return (School)this;
	}
	public School addAgeGroupKeys(Long o) {
		if(o != null && !ageGroupKeys.contains(o))
			this.ageGroupKeys.add(o);
		return (School)this;
	}
	public School setAgeGroupKeys(JsonArray objets) {
		ageGroupKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeGroupKeys(o);
		}
		return (School)this;
	}
	public School addAgeGroupKeys(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addAgeGroupKeys(p);
		}
		return (School)this;
	}
	protected School ageGroupKeysInit() {
		if(!ageGroupKeysWrap.alreadyInitialized) {
			_ageGroupKeys(ageGroupKeys);
		}
		ageGroupKeysWrap.alreadyInitialized(true);
		return (School)this;
	}

	public List<Long> solrAgeGroupKeys() {
		return ageGroupKeys;
	}

	public String strAgeGroupKeys() {
		return ageGroupKeys == null ? "" : ageGroupKeys.toString();
	}

	public String jsonAgeGroupKeys() {
		return ageGroupKeys == null ? "" : ageGroupKeys.toString();
	}

	public String nomAffichageAgeGroupKeys() {
		return "";
	}

	public String htmTooltipAgeGroupKeys() {
		return null;
	}

	public String htmAgeGroupKeys() {
		return ageGroupKeys == null ? "" : StringEscapeUtils.escapeHtml4(strAgeGroupKeys());
	}

	public void htmAgeGroupKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "AgeGroupKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "AgeGroupKeys() {");
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
				r.l("				data: {\"setAgeGroupKeys\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeGroupKeys()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageGroupKeys\"");
							r.s(" value=\"", htmAgeGroupKeys(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeGroupKeys());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKeys">Trouver l'entité sessionKeys dans Solr</a>
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
	public School addSessionKeys(Long...objets) {
		for(Long o : objets) {
			addSessionKeys(o);
		}
		return (School)this;
	}
	public School addSessionKeys(Long o) {
		if(o != null && !sessionKeys.contains(o))
			this.sessionKeys.add(o);
		return (School)this;
	}
	public School setSessionKeys(JsonArray objets) {
		sessionKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionKeys(o);
		}
		return (School)this;
	}
	public School addSessionKeys(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addSessionKeys(p);
		}
		return (School)this;
	}
	protected School sessionKeysInit() {
		if(!sessionKeysWrap.alreadyInitialized) {
			_sessionKeys(sessionKeys);
		}
		sessionKeysWrap.alreadyInitialized(true);
		return (School)this;
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
		return "";
	}

	public String htmTooltipSessionKeys() {
		return null;
	}

	public String htmSessionKeys() {
		return sessionKeys == null ? "" : StringEscapeUtils.escapeHtml4(strSessionKeys());
	}

	public void htmSessionKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SessionKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SessionKeys() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKeys">Trouver l'entité seasonKeys dans Solr</a>
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
	public School addSeasonKeys(Long...objets) {
		for(Long o : objets) {
			addSeasonKeys(o);
		}
		return (School)this;
	}
	public School addSeasonKeys(Long o) {
		if(o != null && !seasonKeys.contains(o))
			this.seasonKeys.add(o);
		return (School)this;
	}
	public School setSeasonKeys(JsonArray objets) {
		seasonKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSeasonKeys(o);
		}
		return (School)this;
	}
	public School addSeasonKeys(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addSeasonKeys(p);
		}
		return (School)this;
	}
	protected School seasonKeysInit() {
		if(!seasonKeysWrap.alreadyInitialized) {
			_seasonKeys(seasonKeys);
		}
		seasonKeysWrap.alreadyInitialized(true);
		return (School)this;
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
		return "";
	}

	public String htmTooltipSeasonKeys() {
		return null;
	}

	public String htmSeasonKeys() {
		return seasonKeys == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonKeys());
	}

	public void htmSeasonKeys(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SeasonKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SeasonKeys() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKeys">Trouver l'entité yearKeys dans Solr</a>
	 * <br/>
	 * @param yearKeys est l'entité déjà construit. 
	 **/
	protected abstract void _yearKeys(List<Long> o);

	public List<Long> getYearKeys() {
		return yearKeys;
	}

	public void setYearKeys(List<Long> yearKeys) {
		this.yearKeys = yearKeys;
		this.yearKeysWrap.alreadyInitialized = true;
	}
	public School addYearKeys(Long...objets) {
		for(Long o : objets) {
			addYearKeys(o);
		}
		return (School)this;
	}
	public School addYearKeys(Long o) {
		if(o != null && !yearKeys.contains(o))
			this.yearKeys.add(o);
		return (School)this;
	}
	public School setYearKeys(JsonArray objets) {
		yearKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addYearKeys(o);
		}
		return (School)this;
	}
	public School addYearKeys(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addYearKeys(p);
		}
		return (School)this;
	}
	protected School yearKeysInit() {
		if(!yearKeysWrap.alreadyInitialized) {
			_yearKeys(yearKeys);
		}
		yearKeysWrap.alreadyInitialized(true);
		return (School)this;
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
			r.s("<div id=\"patchSchool", strPk(), "YearKeys\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "YearKeys() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Trouver l'entité educationSort dans Solr</a>
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
	public School setEducationSort(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.educationSort = Integer.parseInt(o);
		this.educationSortWrap.alreadyInitialized = true;
		return (School)this;
	}
	protected School educationSortInit() {
		if(!educationSortWrap.alreadyInitialized) {
			_educationSort(educationSortWrap);
			if(educationSort == null)
				setEducationSort(educationSortWrap.o);
		}
		educationSortWrap.alreadyInitialized(true);
		return (School)this;
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
		return "";
	}

	public String htmTooltipEducationSort() {
		return null;
	}

	public String htmEducationSort() {
		return educationSort == null ? "" : StringEscapeUtils.escapeHtml4(strEducationSort());
	}

	public void htmEducationSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "EducationSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "EducationSort() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Trouver l'entité schoolSort dans Solr</a>
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
	public School setSchoolSort(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.schoolSort = Integer.parseInt(o);
		this.schoolSortWrap.alreadyInitialized = true;
		return (School)this;
	}
	protected School schoolSortInit() {
		if(!schoolSortWrap.alreadyInitialized) {
			_schoolSort(schoolSortWrap);
			if(schoolSort == null)
				setSchoolSort(schoolSortWrap.o);
		}
		schoolSortWrap.alreadyInitialized(true);
		return (School)this;
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
		return "";
	}

	public String htmTooltipSchoolSort() {
		return null;
	}

	public String htmSchoolSort() {
		return schoolSort == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolSort());
	}

	public void htmSchoolSort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SchoolSort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SchoolSort() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Trouver l'entité schoolName dans Solr</a>
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
	protected School schoolNameInit() {
		if(!schoolNameWrap.alreadyInitialized) {
			_schoolName(schoolNameWrap);
			if(schoolName == null)
				setSchoolName(schoolNameWrap.o);
		}
		schoolNameWrap.alreadyInitialized(true);
		return (School)this;
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
		return "name of the school";
	}

	public String htmTooltipSchoolName() {
		return null;
	}

	public String htmSchoolName() {
		return schoolName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolName());
	}

	public void htmSchoolName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SchoolName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SchoolName() {");
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
				r.l("				data: {\"setSchoolName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolName()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolName\"");
							r.s(" value=\"", htmSchoolName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolName());
			}
			r.l("</div>");
		}
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Trouver l'entité schoolPhoneNumber dans Solr</a>
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
	protected School schoolPhoneNumberInit() {
		if(!schoolPhoneNumberWrap.alreadyInitialized) {
			_schoolPhoneNumber(schoolPhoneNumberWrap);
			if(schoolPhoneNumber == null)
				setSchoolPhoneNumber(schoolPhoneNumberWrap.o);
		}
		schoolPhoneNumberWrap.alreadyInitialized(true);
		return (School)this;
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

	public void htmSchoolPhoneNumber(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SchoolPhoneNumber\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SchoolPhoneNumber() {");
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
				r.l("				data: {\"setSchoolPhoneNumber\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolPhoneNumber()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolPhoneNumber\"");
							r.s(" value=\"", htmSchoolPhoneNumber(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolPhoneNumber());
			}
			r.l("</div>");
		}
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Trouver l'entité schoolAdministratorName dans Solr</a>
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
	protected School schoolAdministratorNameInit() {
		if(!schoolAdministratorNameWrap.alreadyInitialized) {
			_schoolAdministratorName(schoolAdministratorNameWrap);
			if(schoolAdministratorName == null)
				setSchoolAdministratorName(schoolAdministratorNameWrap.o);
		}
		schoolAdministratorNameWrap.alreadyInitialized(true);
		return (School)this;
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

	public void htmSchoolAdministratorName(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SchoolAdministratorName\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SchoolAdministratorName() {");
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
				r.l("				data: {\"setSchoolAdministratorName\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolAdministratorName()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolAdministratorName\"");
							r.s(" value=\"", htmSchoolAdministratorName(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolAdministratorName());
			}
			r.l("</div>");
		}
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Trouver l'entité schoolAddress dans Solr</a>
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
	protected School schoolAddressInit() {
		if(!schoolAddressWrap.alreadyInitialized) {
			_schoolAddress(schoolAddressWrap);
			if(schoolAddress == null)
				setSchoolAddress(schoolAddressWrap.o);
		}
		schoolAddressWrap.alreadyInitialized(true);
		return (School)this;
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

	public void htmSchoolAddress(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SchoolAddress\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SchoolAddress() {");
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
				r.l("				data: {\"setSchoolAddress\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolAddress()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolAddress\"");
							r.s(" value=\"", htmSchoolAddress(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolAddress());
			}
			r.l("</div>");
		}
	}

	/////////////////////////
	// objectSuggestWeight //
	/////////////////////////

	/**	L'entité « objectSuggestWeight »
	 *	 is defined as null before being initialized. 
	 */
	protected Double objectSuggestWeight;
	@JsonIgnore
	public Wrap<Double> objectSuggestWeightWrap = new Wrap<Double>().p(this).c(Double.class).var("objectSuggestWeight").o(objectSuggestWeight);

	/**	<br/>L'entité « objectSuggestWeight »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objectSuggestWeight">Trouver l'entité objectSuggestWeight dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objectSuggestWeight(Wrap<Double> c);

	public Double getObjectSuggestWeight() {
		return objectSuggestWeight;
	}

	public void setObjectSuggestWeight(Double objectSuggestWeight) {
		this.objectSuggestWeight = objectSuggestWeight;
		this.objectSuggestWeightWrap.alreadyInitialized = true;
	}
	public School setObjectSuggestWeight(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.objectSuggestWeight = Double.parseDouble(o);
		this.objectSuggestWeightWrap.alreadyInitialized = true;
		return (School)this;
	}
	protected School objectSuggestWeightInit() {
		if(!objectSuggestWeightWrap.alreadyInitialized) {
			_objectSuggestWeight(objectSuggestWeightWrap);
			if(objectSuggestWeight == null)
				setObjectSuggestWeight(objectSuggestWeightWrap.o);
		}
		objectSuggestWeightWrap.alreadyInitialized(true);
		return (School)this;
	}

	public Double solrObjectSuggestWeight() {
		return objectSuggestWeight;
	}

	public String strObjectSuggestWeight() {
		return objectSuggestWeight == null ? "" : objectSuggestWeight.toString();
	}

	public String jsonObjectSuggestWeight() {
		return objectSuggestWeight == null ? "" : objectSuggestWeight.toString();
	}

	public String nomAffichageObjectSuggestWeight() {
		return "";
	}

	public String htmTooltipObjectSuggestWeight() {
		return null;
	}

	public String htmObjectSuggestWeight() {
		return objectSuggestWeight == null ? "" : StringEscapeUtils.escapeHtml4(strObjectSuggestWeight());
	}

	public void htmObjectSuggestWeight(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "ObjectSuggestWeight\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "ObjectSuggestWeight() {");
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
				r.l("				data: {\"setObjectSuggestWeight\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageObjectSuggestWeight()), "</span>");
				r.s("			<input");
							r.s(" name=\"objectSuggestWeight\"");
							r.s(" value=\"", htmObjectSuggestWeight(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmObjectSuggestWeight());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objectSuggest">Trouver l'entité objectSuggest dans Solr</a>
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
	protected School objectSuggestInit() {
		if(!objectSuggestWrap.alreadyInitialized) {
			_objectSuggest(objectSuggestWrap);
			if(objectSuggest == null)
				setObjectSuggest(objectSuggestWrap.o);
		}
		objectSuggestWrap.alreadyInitialized(true);
		return (School)this;
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
		return "r: ecoleNom";
	}

	public String htmTooltipObjectSuggest() {
		return null;
	}

	public String htmObjectSuggest() {
		return objectSuggest == null ? "" : StringEscapeUtils.escapeHtml4(strObjectSuggest());
	}

	public void htmObjectSuggest(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "ObjectSuggest\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "ObjectSuggest() {");
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

	/////////////////////
	// schoolNameShort //
	/////////////////////

	/**	L'entité « schoolNameShort »
	 *	 is defined as null before being initialized. 
	 */
	protected String schoolNameShort;
	@JsonIgnore
	public Wrap<String> schoolNameShortWrap = new Wrap<String>().p(this).c(String.class).var("schoolNameShort").o(schoolNameShort);

	/**	<br/>L'entité « schoolNameShort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolNameShort">Trouver l'entité schoolNameShort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolNameShort(Wrap<String> c);

	public String getSchoolNameShort() {
		return schoolNameShort;
	}

	public void setSchoolNameShort(String schoolNameShort) {
		this.schoolNameShort = schoolNameShort;
		this.schoolNameShortWrap.alreadyInitialized = true;
	}
	protected School schoolNameShortInit() {
		if(!schoolNameShortWrap.alreadyInitialized) {
			_schoolNameShort(schoolNameShortWrap);
			if(schoolNameShort == null)
				setSchoolNameShort(schoolNameShortWrap.o);
		}
		schoolNameShortWrap.alreadyInitialized(true);
		return (School)this;
	}

	public String solrSchoolNameShort() {
		return schoolNameShort;
	}

	public String strSchoolNameShort() {
		return schoolNameShort == null ? "" : schoolNameShort;
	}

	public String jsonSchoolNameShort() {
		return schoolNameShort == null ? "" : schoolNameShort;
	}

	public String nomAffichageSchoolNameShort() {
		return "r: ecoleNom";
	}

	public String htmTooltipSchoolNameShort() {
		return null;
	}

	public String htmSchoolNameShort() {
		return schoolNameShort == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolNameShort());
	}

	public void htmSchoolNameShort(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SchoolNameShort\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SchoolNameShort() {");
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
				r.l("				data: {\"setSchoolNameShort\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolNameShort()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolNameShort\"");
							r.s(" value=\"", htmSchoolNameShort(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolNameShort());
			}
			r.l("</div>");
		}
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolNameComplete">Trouver l'entité schoolNameComplete dans Solr</a>
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
	protected School schoolNameCompleteInit() {
		if(!schoolNameCompleteWrap.alreadyInitialized) {
			_schoolNameComplete(schoolNameCompleteWrap);
			if(schoolNameComplete == null)
				setSchoolNameComplete(schoolNameCompleteWrap.o);
		}
		schoolNameCompleteWrap.alreadyInitialized(true);
		return (School)this;
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
		return "r: ecoleNom";
	}

	public String htmTooltipSchoolNameComplete() {
		return null;
	}

	public String htmSchoolNameComplete() {
		return schoolNameComplete == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolNameComplete());
	}

	public void htmSchoolNameComplete(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SchoolNameComplete\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SchoolNameComplete() {");
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

	//////////////
	// schoolId //
	//////////////

	/**	L'entité « schoolId »
	 *	 is defined as null before being initialized. 
	 */
	protected String schoolId;
	@JsonIgnore
	public Wrap<String> schoolIdWrap = new Wrap<String>().p(this).c(String.class).var("schoolId").o(schoolId);

	/**	<br/>L'entité « schoolId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolId">Trouver l'entité schoolId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolId(Wrap<String> c);

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
		this.schoolIdWrap.alreadyInitialized = true;
	}
	protected School schoolIdInit() {
		if(!schoolIdWrap.alreadyInitialized) {
			_schoolId(schoolIdWrap);
			if(schoolId == null)
				setSchoolId(schoolIdWrap.o);
		}
		schoolIdWrap.alreadyInitialized(true);
		return (School)this;
	}

	public String solrSchoolId() {
		return schoolId;
	}

	public String strSchoolId() {
		return schoolId == null ? "" : schoolId;
	}

	public String jsonSchoolId() {
		return schoolId == null ? "" : schoolId;
	}

	public String nomAffichageSchoolId() {
		return "ID";
	}

	public String htmTooltipSchoolId() {
		return null;
	}

	public String htmSchoolId() {
		return schoolId == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolId());
	}

	public void htmSchoolId(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SchoolId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SchoolId() {");
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
				r.l("				data: {\"setSchoolId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSchoolId()), "</span>");
				r.s("			<input");
							r.s(" name=\"schoolId\"");
							r.s(" value=\"", htmSchoolId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSchoolId());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
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
	protected School pageUrlInit() {
		if(!pageUrlWrap.alreadyInitialized) {
			_pageUrl(pageUrlWrap);
			if(pageUrl == null)
				setPageUrl(pageUrlWrap.o);
		}
		pageUrlWrap.alreadyInitialized(true);
		return (School)this;
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
			r.s("<div id=\"patchSchool", strPk(), "PageUrl\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "PageUrl() {");
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

	////////////
	// pageH1 //
	////////////

	/**	L'entité « pageH1 »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageH1;
	@JsonIgnore
	public Wrap<String> pageH1Wrap = new Wrap<String>().p(this).c(String.class).var("pageH1").o(pageH1);

	/**	<br/>L'entité « pageH1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageH1">Trouver l'entité pageH1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageH1(Wrap<String> c);

	public String getPageH1() {
		return pageH1;
	}

	public void setPageH1(String pageH1) {
		this.pageH1 = pageH1;
		this.pageH1Wrap.alreadyInitialized = true;
	}
	protected School pageH1Init() {
		if(!pageH1Wrap.alreadyInitialized) {
			_pageH1(pageH1Wrap);
			if(pageH1 == null)
				setPageH1(pageH1Wrap.o);
		}
		pageH1Wrap.alreadyInitialized(true);
		return (School)this;
	}

	public String solrPageH1() {
		return pageH1;
	}

	public String strPageH1() {
		return pageH1 == null ? "" : pageH1;
	}

	public String jsonPageH1() {
		return pageH1 == null ? "" : pageH1;
	}

	public String nomAffichagePageH1() {
		return null;
	}

	public String htmTooltipPageH1() {
		return null;
	}

	public String htmPageH1() {
		return pageH1 == null ? "" : StringEscapeUtils.escapeHtml4(strPageH1());
	}

	public void htmPageH1(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "PageH1\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "PageH1() {");
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
				r.l("				data: {\"setPageH1\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePageH1()), "</span>");
				r.s("			<input");
							r.s(" name=\"pageH1\"");
							r.s(" value=\"", htmPageH1(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPageH1());
			}
			r.l("</div>");
		}
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSchool = false;

	public School initDeepSchool(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchool) {
			alreadyInitializedSchool = true;
			initDeepSchool();
		}
		return (School)this;
	}

	public void initDeepSchool() {
		super.initDeepCluster(siteRequest_);
		initSchool();
	}

	public void initSchool() {
		schoolKeyInit();
		childKeysInit();
		blockKeysInit();
		ageGroupKeysInit();
		sessionKeysInit();
		seasonKeysInit();
		yearKeysInit();
		educationSortInit();
		schoolSortInit();
		schoolNameInit();
		schoolPhoneNumberInit();
		schoolAdministratorNameInit();
		schoolAddressInit();
		objectSuggestWeightInit();
		objectSuggestInit();
		schoolNameShortInit();
		schoolNameCompleteInit();
		schoolIdInit();
		pageUrlInit();
		pageH1Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchool(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchool(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchool(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchool(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchool(String var) {
		School oSchool = (School)this;
		switch(var) {
			case "schoolKey":
				return oSchool.schoolKey;
			case "childKeys":
				return oSchool.childKeys;
			case "blockKeys":
				return oSchool.blockKeys;
			case "ageGroupKeys":
				return oSchool.ageGroupKeys;
			case "sessionKeys":
				return oSchool.sessionKeys;
			case "seasonKeys":
				return oSchool.seasonKeys;
			case "yearKeys":
				return oSchool.yearKeys;
			case "educationSort":
				return oSchool.educationSort;
			case "schoolSort":
				return oSchool.schoolSort;
			case "schoolName":
				return oSchool.schoolName;
			case "schoolPhoneNumber":
				return oSchool.schoolPhoneNumber;
			case "schoolAdministratorName":
				return oSchool.schoolAdministratorName;
			case "schoolAddress":
				return oSchool.schoolAddress;
			case "objectSuggestWeight":
				return oSchool.objectSuggestWeight;
			case "objectSuggest":
				return oSchool.objectSuggest;
			case "schoolNameShort":
				return oSchool.schoolNameShort;
			case "schoolNameComplete":
				return oSchool.schoolNameComplete;
			case "schoolId":
				return oSchool.schoolId;
			case "pageUrl":
				return oSchool.pageUrl;
			case "pageH1":
				return oSchool.pageH1;
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
				o = attributeSchool(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchool(String var, Object val) {
		School oSchool = (School)this;
		switch(var) {
			case "yearKeys":
				oSchool.addYearKeys((Long)val);
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
					o = defineSchool(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchool(String var, String val) {
		switch(var) {
			case "schoolName":
				setSchoolName(val);
				savesSchool.add(var);
				return val;
			case "schoolPhoneNumber":
				setSchoolPhoneNumber(val);
				savesSchool.add(var);
				return val;
			case "schoolAdministratorName":
				setSchoolAdministratorName(val);
				savesSchool.add(var);
				return val;
			case "schoolAddress":
				setSchoolAddress(val);
				savesSchool.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesSchool = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchool(solrDocument);
	}
	public void populateSchool(SolrDocument solrDocument) {
		School oSchool = (School)this;
		savesSchool = (List<String>)solrDocument.get("savesSchool_stored_strings");
		if(savesSchool != null) {

			if(savesSchool.contains("schoolKey")) {
				Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
				if(schoolKey != null)
					oSchool.setSchoolKey(schoolKey);
			}

			if(savesSchool.contains("childKeys")) {
				List<Long> childKeys = (List<Long>)solrDocument.get("childKeys_stored_longs");
				if(childKeys != null)
					oSchool.childKeys.addAll(childKeys);
			}

			if(savesSchool.contains("blockKeys")) {
				List<Long> blockKeys = (List<Long>)solrDocument.get("blockKeys_stored_longs");
				if(blockKeys != null)
					oSchool.blockKeys.addAll(blockKeys);
			}

			if(savesSchool.contains("ageGroupKeys")) {
				List<Long> ageGroupKeys = (List<Long>)solrDocument.get("ageGroupKeys_stored_longs");
				if(ageGroupKeys != null)
					oSchool.ageGroupKeys.addAll(ageGroupKeys);
			}

			if(savesSchool.contains("sessionKeys")) {
				List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
				if(sessionKeys != null)
					oSchool.sessionKeys.addAll(sessionKeys);
			}

			if(savesSchool.contains("seasonKeys")) {
				List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
				if(seasonKeys != null)
					oSchool.seasonKeys.addAll(seasonKeys);
			}

			List<Long> yearKeys = (List<Long>)solrDocument.get("yearKeys_stored_longs");
			if(yearKeys != null)
				oSchool.yearKeys.addAll(yearKeys);

			if(savesSchool.contains("educationSort")) {
				Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
				if(educationSort != null)
					oSchool.setEducationSort(educationSort);
			}

			if(savesSchool.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchool.setSchoolSort(schoolSort);
			}

			if(savesSchool.contains("schoolName")) {
				String schoolName = (String)solrDocument.get("schoolName_stored_string");
				if(schoolName != null)
					oSchool.setSchoolName(schoolName);
			}

			if(savesSchool.contains("schoolPhoneNumber")) {
				String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
				if(schoolPhoneNumber != null)
					oSchool.setSchoolPhoneNumber(schoolPhoneNumber);
			}

			if(savesSchool.contains("schoolAdministratorName")) {
				String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
				if(schoolAdministratorName != null)
					oSchool.setSchoolAdministratorName(schoolAdministratorName);
			}

			if(savesSchool.contains("schoolAddress")) {
				String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
				if(schoolAddress != null)
					oSchool.setSchoolAddress(schoolAddress);
			}

			if(savesSchool.contains("objectSuggest")) {
				String objectSuggest = (String)solrDocument.get("objectSuggest_stored_string");
				if(objectSuggest != null)
					oSchool.setObjectSuggest(objectSuggest);
			}

			if(savesSchool.contains("schoolNameShort")) {
				String schoolNameShort = (String)solrDocument.get("schoolNameShort_stored_string");
				if(schoolNameShort != null)
					oSchool.setSchoolNameShort(schoolNameShort);
			}

			if(savesSchool.contains("schoolNameComplete")) {
				String schoolNameComplete = (String)solrDocument.get("schoolNameComplete_stored_string");
				if(schoolNameComplete != null)
					oSchool.setSchoolNameComplete(schoolNameComplete);
			}

			if(savesSchool.contains("schoolId")) {
				String schoolId = (String)solrDocument.get("schoolId_stored_string");
				if(schoolId != null)
					oSchool.setSchoolId(schoolId);
			}

			if(savesSchool.contains("pageUrl")) {
				String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
				if(pageUrl != null)
					oSchool.setPageUrl(pageUrl);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.school.School"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			School o = new School();
			o.siteRequestSchool(siteRequest);
			o.initDeepSchool(siteRequest);
			o.indexSchool();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchool();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchool(document);
	}

	public void indexSchool(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchool(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchool() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchool(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchool(SolrInputDocument document) {
		if(savesSchool != null)
			document.addField("savesSchool_stored_strings", savesSchool);

		if(schoolKey != null) {
			document.addField("schoolKey_indexed_long", schoolKey);
			document.addField("schoolKey_stored_long", schoolKey);
		}
		if(childKeys != null) {
			for(java.lang.Long o : childKeys) {
				document.addField("childKeys_indexed_longs", o);
			}
			for(java.lang.Long o : childKeys) {
				document.addField("childKeys_stored_longs", o);
			}
		}
		if(blockKeys != null) {
			for(java.lang.Long o : blockKeys) {
				document.addField("blockKeys_indexed_longs", o);
			}
			for(java.lang.Long o : blockKeys) {
				document.addField("blockKeys_stored_longs", o);
			}
		}
		if(ageGroupKeys != null) {
			for(java.lang.Long o : ageGroupKeys) {
				document.addField("ageGroupKeys_indexed_longs", o);
			}
			for(java.lang.Long o : ageGroupKeys) {
				document.addField("ageGroupKeys_stored_longs", o);
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
		if(seasonKeys != null) {
			for(java.lang.Long o : seasonKeys) {
				document.addField("seasonKeys_indexed_longs", o);
			}
			for(java.lang.Long o : seasonKeys) {
				document.addField("seasonKeys_stored_longs", o);
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
		if(educationSort != null) {
			document.addField("educationSort_indexed_int", educationSort);
			document.addField("educationSort_stored_int", educationSort);
		}
		if(schoolSort != null) {
			document.addField("schoolSort_indexed_int", schoolSort);
			document.addField("schoolSort_stored_int", schoolSort);
		}
		if(schoolName != null) {
			document.addField("schoolName_indexed_string", schoolName);
			document.addField("schoolName_stored_string", schoolName);
		}
		if(schoolPhoneNumber != null) {
			document.addField("schoolPhoneNumber_indexed_string", schoolPhoneNumber);
			document.addField("schoolPhoneNumber_stored_string", schoolPhoneNumber);
		}
		if(schoolAdministratorName != null) {
			document.addField("schoolAdministratorName_indexed_string", schoolAdministratorName);
			document.addField("schoolAdministratorName_stored_string", schoolAdministratorName);
		}
		if(schoolAddress != null) {
			document.addField("schoolAddress_indexed_string", schoolAddress);
			document.addField("schoolAddress_stored_string", schoolAddress);
		}
		if(objectSuggest != null) {
			document.addField("objectSuggest_suggested", objectSuggest);
			document.addField("objectSuggest_indexed_string", objectSuggest);
		}
		if(schoolNameShort != null) {
			document.addField("schoolNameShort_indexed_string", schoolNameShort);
			document.addField("schoolNameShort_stored_string", schoolNameShort);
		}
		if(schoolNameComplete != null) {
			document.addField("schoolNameComplete_indexed_string", schoolNameComplete);
			document.addField("schoolNameComplete_stored_string", schoolNameComplete);
		}
		if(schoolId != null) {
			document.addField("schoolId_indexed_string", schoolId);
			document.addField("schoolId_stored_string", schoolId);
		}
		if(pageUrl != null) {
			document.addField("pageUrl_indexed_string", pageUrl);
			document.addField("pageUrl_stored_string", pageUrl);
		}
		super.indexCluster(document);

	}

	public void unindexSchool() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchool(siteRequest);
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
		storeSchool(solrDocument);
	}
	public void storeSchool(SolrDocument solrDocument) {
		School oSchool = (School)this;

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oSchool.setSchoolKey(schoolKey);

		List<Long> childKeys = (List<Long>)solrDocument.get("childKeys_stored_longs");
		if(childKeys != null)
			oSchool.childKeys.addAll(childKeys);

		List<Long> blockKeys = (List<Long>)solrDocument.get("blockKeys_stored_longs");
		if(blockKeys != null)
			oSchool.blockKeys.addAll(blockKeys);

		List<Long> ageGroupKeys = (List<Long>)solrDocument.get("ageGroupKeys_stored_longs");
		if(ageGroupKeys != null)
			oSchool.ageGroupKeys.addAll(ageGroupKeys);

		List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
		if(sessionKeys != null)
			oSchool.sessionKeys.addAll(sessionKeys);

		List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
		if(seasonKeys != null)
			oSchool.seasonKeys.addAll(seasonKeys);

		List<Long> yearKeys = (List<Long>)solrDocument.get("yearKeys_stored_longs");
		if(yearKeys != null)
			oSchool.yearKeys.addAll(yearKeys);

		Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
		if(educationSort != null)
			oSchool.setEducationSort(educationSort);

		Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
		if(schoolSort != null)
			oSchool.setSchoolSort(schoolSort);

		String schoolName = (String)solrDocument.get("schoolName_stored_string");
		if(schoolName != null)
			oSchool.setSchoolName(schoolName);

		String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
		if(schoolPhoneNumber != null)
			oSchool.setSchoolPhoneNumber(schoolPhoneNumber);

		String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
		if(schoolAdministratorName != null)
			oSchool.setSchoolAdministratorName(schoolAdministratorName);

		String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
		if(schoolAddress != null)
			oSchool.setSchoolAddress(schoolAddress);

		String objectSuggest = (String)solrDocument.get("objectSuggest_stored_string");
		if(objectSuggest != null)
			oSchool.setObjectSuggest(objectSuggest);

		String schoolNameShort = (String)solrDocument.get("schoolNameShort_stored_string");
		if(schoolNameShort != null)
			oSchool.setSchoolNameShort(schoolNameShort);

		String schoolNameComplete = (String)solrDocument.get("schoolNameComplete_stored_string");
		if(schoolNameComplete != null)
			oSchool.setSchoolNameComplete(schoolNameComplete);

		String schoolId = (String)solrDocument.get("schoolId_stored_string");
		if(schoolId != null)
			oSchool.setSchoolId(schoolId);

		String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
		if(pageUrl != null)
			oSchool.setPageUrl(pageUrl);

		super.storeCluster(solrDocument);
	}

	//////////////
	// htmlBody //
	//////////////

	public void htmlBody() {
		htmlBodySchool();
	}

	public void htmlBodySchool() {
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), yearKeys, schoolName, schoolPhoneNumber, schoolAdministratorName, schoolAddress);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof School))
			return false;
		School that = (School)o;
		return super.equals(o)
				&& Objects.equals( yearKeys, that.yearKeys )
				&& Objects.equals( schoolName, that.schoolName )
				&& Objects.equals( schoolPhoneNumber, that.schoolPhoneNumber )
				&& Objects.equals( schoolAdministratorName, that.schoolAdministratorName )
				&& Objects.equals( schoolAddress, that.schoolAddress );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("School { ");
		sb.append( "yearKeys: " ).append(yearKeys);
		sb.append( ", schoolName: \"" ).append(schoolName).append( "\"" );
		sb.append( ", schoolPhoneNumber: \"" ).append(schoolPhoneNumber).append( "\"" );
		sb.append( ", schoolAdministratorName: \"" ).append(schoolAdministratorName).append( "\"" );
		sb.append( ", schoolAddress: \"" ).append(schoolAddress).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
