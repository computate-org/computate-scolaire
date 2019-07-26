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
	public static final String School_IconeGroupe = "regular";
	public static final String School_IconeNom = "fort-awesome";
	public static final String EcoleFrFRPage_Uri = "/frFR/ecole";
	public static final String EcoleFrFRPage_ImageUri = "/png/frFR/ecole-999.png";
	public static final String EcoleEnUSPage_Uri = "/enUS/school";
	public static final String EcoleEnUSPage_ImageUri = "/png/enUS/school-999.png";

	//////////////
	// ecoleCle //
	//////////////

	/**	L'entité « ecoleCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long ecoleCle;
	public Wrap<Long> ecoleCleWrap = new Wrap<Long>().p(this).c(Long.class).var("ecoleCle").o(ecoleCle);

	/**	<br/>L'entité « ecoleCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleCle(Wrap<Long> c);

	public Long getEcoleCle() {
		return ecoleCle;
	}

	public void setEcoleCle(Long ecoleCle) {
		this.ecoleCle = ecoleCle;
		this.ecoleCleWrap.alreadyInitialized = true;
	}
	public School setEcoleCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ecoleCle = Long.parseLong(o);
		this.ecoleCleWrap.alreadyInitialized = true;
		return (School)this;
	}
	protected School ecoleCleInit() {
		if(!ecoleCleWrap.alreadyInitialized) {
			_ecoleCle(ecoleCleWrap);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleWrap.o);
		}
		ecoleCleWrap.alreadyInitialized(true);
		return (School)this;
	}

	public Long solrEcoleCle() {
		return ecoleCle;
	}

	public String strEcoleCle() {
		return ecoleCle == null ? "" : ecoleCle.toString();
	}

	public String nomAffichageEcoleCle() {
		return "key";
	}

	public String htmTooltipEcoleCle() {
		return null;
	}

	public String htmEcoleCle() {
		return ecoleCle == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleCle());
	}

	public void htmEcoleCle(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "EcoleCle\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "EcoleCle() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setEcoleCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEcoleCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"ecoleCle\"");
							r.s(" value=\"", htmEcoleCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEcoleCle());
			}
			r.l("</div>");
		}
	}

	////////////////
	// enfantCles //
	////////////////

	/**	L'entité « enfantCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> enfantCles = new java.util.ArrayList<java.lang.Long>();
	public Wrap<List<Long>> enfantClesWrap = new Wrap<List<Long>>().p(this).c(List.class).var("enfantCles").o(enfantCles);

	/**	<br/>L'entité « enfantCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enfantCles">Trouver l'entité enfantCles dans Solr</a>
	 * <br/>
	 * @param enfantCles est l'entité déjà construit. 
	 **/
	protected abstract void _enfantCles(List<Long> o);

	public List<Long> getEnfantCles() {
		return enfantCles;
	}

	public void setEnfantCles(List<Long> enfantCles) {
		this.enfantCles = enfantCles;
		this.enfantClesWrap.alreadyInitialized = true;
	}
	public School addEnfantCles(Long...objets) {
		for(Long o : objets) {
			addEnfantCles(o);
		}
		return (School)this;
	}
	public School addEnfantCles(Long o) {
		if(o != null && !enfantCles.contains(o))
			this.enfantCles.add(o);
		return (School)this;
	}
	public School setEnfantCles(JsonArray objets) {
		enfantCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnfantCles(o);
		}
		return (School)this;
	}
	public School addEnfantCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addEnfantCles(p);
		}
		return (School)this;
	}
	protected School enfantClesInit() {
		if(!enfantClesWrap.alreadyInitialized) {
			_enfantCles(enfantCles);
		}
		enfantClesWrap.alreadyInitialized(true);
		return (School)this;
	}

	public List<Long> solrEnfantCles() {
		return enfantCles;
	}

	public String strEnfantCles() {
		return enfantCles == null ? "" : enfantCles.toString();
	}

	public String nomAffichageEnfantCles() {
		return "";
	}

	public String htmTooltipEnfantCles() {
		return null;
	}

	public String htmEnfantCles() {
		return enfantCles == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantCles());
	}

	public void htmEnfantCles(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "EnfantCles\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "EnfantCles() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setEnfantCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnfantCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"enfantCles\"");
							r.s(" value=\"", htmEnfantCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnfantCles());
			}
			r.l("</div>");
		}
	}

	//////////////
	// blocCles //
	//////////////

	/**	L'entité « blocCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> blocCles = new java.util.ArrayList<java.lang.Long>();
	public Wrap<List<Long>> blocClesWrap = new Wrap<List<Long>>().p(this).c(List.class).var("blocCles").o(blocCles);

	/**	<br/>L'entité « blocCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blocCles">Trouver l'entité blocCles dans Solr</a>
	 * <br/>
	 * @param blocCles est l'entité déjà construit. 
	 **/
	protected abstract void _blocCles(List<Long> o);

	public List<Long> getBlocCles() {
		return blocCles;
	}

	public void setBlocCles(List<Long> blocCles) {
		this.blocCles = blocCles;
		this.blocClesWrap.alreadyInitialized = true;
	}
	public School addBlocCles(Long...objets) {
		for(Long o : objets) {
			addBlocCles(o);
		}
		return (School)this;
	}
	public School addBlocCles(Long o) {
		if(o != null && !blocCles.contains(o))
			this.blocCles.add(o);
		return (School)this;
	}
	public School setBlocCles(JsonArray objets) {
		blocCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlocCles(o);
		}
		return (School)this;
	}
	public School addBlocCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addBlocCles(p);
		}
		return (School)this;
	}
	protected School blocClesInit() {
		if(!blocClesWrap.alreadyInitialized) {
			_blocCles(blocCles);
		}
		blocClesWrap.alreadyInitialized(true);
		return (School)this;
	}

	public List<Long> solrBlocCles() {
		return blocCles;
	}

	public String strBlocCles() {
		return blocCles == null ? "" : blocCles.toString();
	}

	public String nomAffichageBlocCles() {
		return "";
	}

	public String htmTooltipBlocCles() {
		return null;
	}

	public String htmBlocCles() {
		return blocCles == null ? "" : StringEscapeUtils.escapeHtml4(strBlocCles());
	}

	public void htmBlocCles(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "BlocCles\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "BlocCles() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setBlocCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocCles\"");
							r.s(" value=\"", htmBlocCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocCles());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// groupeAgeCles //
	///////////////////

	/**	L'entité « groupeAgeCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> groupeAgeCles = new java.util.ArrayList<java.lang.Long>();
	public Wrap<List<Long>> groupeAgeClesWrap = new Wrap<List<Long>>().p(this).c(List.class).var("groupeAgeCles").o(groupeAgeCles);

	/**	<br/>L'entité « groupeAgeCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:groupeAgeCles">Trouver l'entité groupeAgeCles dans Solr</a>
	 * <br/>
	 * @param groupeAgeCles est l'entité déjà construit. 
	 **/
	protected abstract void _groupeAgeCles(List<Long> o);

	public List<Long> getGroupeAgeCles() {
		return groupeAgeCles;
	}

	public void setGroupeAgeCles(List<Long> groupeAgeCles) {
		this.groupeAgeCles = groupeAgeCles;
		this.groupeAgeClesWrap.alreadyInitialized = true;
	}
	public School addGroupeAgeCles(Long...objets) {
		for(Long o : objets) {
			addGroupeAgeCles(o);
		}
		return (School)this;
	}
	public School addGroupeAgeCles(Long o) {
		if(o != null && !groupeAgeCles.contains(o))
			this.groupeAgeCles.add(o);
		return (School)this;
	}
	public School setGroupeAgeCles(JsonArray objets) {
		groupeAgeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGroupeAgeCles(o);
		}
		return (School)this;
	}
	public School addGroupeAgeCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addGroupeAgeCles(p);
		}
		return (School)this;
	}
	protected School groupeAgeClesInit() {
		if(!groupeAgeClesWrap.alreadyInitialized) {
			_groupeAgeCles(groupeAgeCles);
		}
		groupeAgeClesWrap.alreadyInitialized(true);
		return (School)this;
	}

	public List<Long> solrGroupeAgeCles() {
		return groupeAgeCles;
	}

	public String strGroupeAgeCles() {
		return groupeAgeCles == null ? "" : groupeAgeCles.toString();
	}

	public String nomAffichageGroupeAgeCles() {
		return "";
	}

	public String htmTooltipGroupeAgeCles() {
		return null;
	}

	public String htmGroupeAgeCles() {
		return groupeAgeCles == null ? "" : StringEscapeUtils.escapeHtml4(strGroupeAgeCles());
	}

	public void htmGroupeAgeCles(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "GroupeAgeCles\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "GroupeAgeCles() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setGroupeAgeCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageGroupeAgeCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"groupeAgeCles\"");
							r.s(" value=\"", htmGroupeAgeCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmGroupeAgeCles());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// sessionCles //
	/////////////////

	/**	L'entité « sessionCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> sessionCles = new java.util.ArrayList<java.lang.Long>();
	public Wrap<List<Long>> sessionClesWrap = new Wrap<List<Long>>().p(this).c(List.class).var("sessionCles").o(sessionCles);

	/**	<br/>L'entité « sessionCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionCles">Trouver l'entité sessionCles dans Solr</a>
	 * <br/>
	 * @param sessionCles est l'entité déjà construit. 
	 **/
	protected abstract void _sessionCles(List<Long> o);

	public List<Long> getSessionCles() {
		return sessionCles;
	}

	public void setSessionCles(List<Long> sessionCles) {
		this.sessionCles = sessionCles;
		this.sessionClesWrap.alreadyInitialized = true;
	}
	public School addSessionCles(Long...objets) {
		for(Long o : objets) {
			addSessionCles(o);
		}
		return (School)this;
	}
	public School addSessionCles(Long o) {
		if(o != null && !sessionCles.contains(o))
			this.sessionCles.add(o);
		return (School)this;
	}
	public School setSessionCles(JsonArray objets) {
		sessionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionCles(o);
		}
		return (School)this;
	}
	public School addSessionCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addSessionCles(p);
		}
		return (School)this;
	}
	protected School sessionClesInit() {
		if(!sessionClesWrap.alreadyInitialized) {
			_sessionCles(sessionCles);
		}
		sessionClesWrap.alreadyInitialized(true);
		return (School)this;
	}

	public List<Long> solrSessionCles() {
		return sessionCles;
	}

	public String strSessionCles() {
		return sessionCles == null ? "" : sessionCles.toString();
	}

	public String nomAffichageSessionCles() {
		return "";
	}

	public String htmTooltipSessionCles() {
		return null;
	}

	public String htmSessionCles() {
		return sessionCles == null ? "" : StringEscapeUtils.escapeHtml4(strSessionCles());
	}

	public void htmSessionCles(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SessionCles\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SessionCles() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setSessionCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionCles\"");
							r.s(" value=\"", htmSessionCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionCles());
			}
			r.l("</div>");
		}
	}

	////////////////
	// saisonCles //
	////////////////

	/**	L'entité « saisonCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> saisonCles = new java.util.ArrayList<java.lang.Long>();
	public Wrap<List<Long>> saisonClesWrap = new Wrap<List<Long>>().p(this).c(List.class).var("saisonCles").o(saisonCles);

	/**	<br/>L'entité « saisonCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:saisonCles">Trouver l'entité saisonCles dans Solr</a>
	 * <br/>
	 * @param saisonCles est l'entité déjà construit. 
	 **/
	protected abstract void _saisonCles(List<Long> o);

	public List<Long> getSaisonCles() {
		return saisonCles;
	}

	public void setSaisonCles(List<Long> saisonCles) {
		this.saisonCles = saisonCles;
		this.saisonClesWrap.alreadyInitialized = true;
	}
	public School addSaisonCles(Long...objets) {
		for(Long o : objets) {
			addSaisonCles(o);
		}
		return (School)this;
	}
	public School addSaisonCles(Long o) {
		if(o != null && !saisonCles.contains(o))
			this.saisonCles.add(o);
		return (School)this;
	}
	public School setSaisonCles(JsonArray objets) {
		saisonCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSaisonCles(o);
		}
		return (School)this;
	}
	public School addSaisonCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addSaisonCles(p);
		}
		return (School)this;
	}
	protected School saisonClesInit() {
		if(!saisonClesWrap.alreadyInitialized) {
			_saisonCles(saisonCles);
		}
		saisonClesWrap.alreadyInitialized(true);
		return (School)this;
	}

	public List<Long> solrSaisonCles() {
		return saisonCles;
	}

	public String strSaisonCles() {
		return saisonCles == null ? "" : saisonCles.toString();
	}

	public String nomAffichageSaisonCles() {
		return "";
	}

	public String htmTooltipSaisonCles() {
		return null;
	}

	public String htmSaisonCles() {
		return saisonCles == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonCles());
	}

	public void htmSaisonCles(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "SaisonCles\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "SaisonCles() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setSaisonCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSaisonCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"saisonCles\"");
							r.s(" value=\"", htmSaisonCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSaisonCles());
			}
			r.l("</div>");
		}
	}

	///////////////
	// anneeCles //
	///////////////

	/**	L'entité « anneeCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> anneeCles = new java.util.ArrayList<java.lang.Long>();
	public Wrap<List<Long>> anneeClesWrap = new Wrap<List<Long>>().p(this).c(List.class).var("anneeCles").o(anneeCles);

	/**	<br/>L'entité « anneeCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:anneeCles">Trouver l'entité anneeCles dans Solr</a>
	 * <br/>
	 * @param anneeCles est l'entité déjà construit. 
	 **/
	protected abstract void _anneeCles(List<Long> o);

	public List<Long> getAnneeCles() {
		return anneeCles;
	}

	public void setAnneeCles(List<Long> anneeCles) {
		this.anneeCles = anneeCles;
		this.anneeClesWrap.alreadyInitialized = true;
	}
	public School addAnneeCles(Long...objets) {
		for(Long o : objets) {
			addAnneeCles(o);
		}
		return (School)this;
	}
	public School addAnneeCles(Long o) {
		if(o != null && !anneeCles.contains(o))
			this.anneeCles.add(o);
		return (School)this;
	}
	public School setAnneeCles(JsonArray objets) {
		anneeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAnneeCles(o);
		}
		return (School)this;
	}
	public School addAnneeCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addAnneeCles(p);
		}
		return (School)this;
	}
	protected School anneeClesInit() {
		if(!anneeClesWrap.alreadyInitialized) {
			_anneeCles(anneeCles);
		}
		anneeClesWrap.alreadyInitialized(true);
		return (School)this;
	}

	public List<Long> solrAnneeCles() {
		return anneeCles;
	}

	public String strAnneeCles() {
		return anneeCles == null ? "" : anneeCles.toString();
	}

	public String nomAffichageAnneeCles() {
		return "";
	}

	public String htmTooltipAnneeCles() {
		return null;
	}

	public String htmAnneeCles() {
		return anneeCles == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCles());
	}

	public void htmAnneeCles(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "AnneeCles\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "AnneeCles() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setAnneeCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAnneeCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"anneeCles\"");
							r.s(" value=\"", htmAnneeCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAnneeCles());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// scolaireTri //
	/////////////////

	/**	L'entité « scolaireTri »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer scolaireTri;
	public Wrap<Integer> scolaireTriWrap = new Wrap<Integer>().p(this).c(Integer.class).var("scolaireTri").o(scolaireTri);

	/**	<br/>L'entité « scolaireTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:scolaireTri">Trouver l'entité scolaireTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _scolaireTri(Wrap<Integer> c);

	public Integer getScolaireTri() {
		return scolaireTri;
	}

	public void setScolaireTri(Integer scolaireTri) {
		this.scolaireTri = scolaireTri;
		this.scolaireTriWrap.alreadyInitialized = true;
	}
	public School setScolaireTri(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.scolaireTri = Integer.parseInt(o);
		this.scolaireTriWrap.alreadyInitialized = true;
		return (School)this;
	}
	protected School scolaireTriInit() {
		if(!scolaireTriWrap.alreadyInitialized) {
			_scolaireTri(scolaireTriWrap);
			if(scolaireTri == null)
				setScolaireTri(scolaireTriWrap.o);
		}
		scolaireTriWrap.alreadyInitialized(true);
		return (School)this;
	}

	public Integer solrScolaireTri() {
		return scolaireTri;
	}

	public String strScolaireTri() {
		return scolaireTri == null ? "" : scolaireTri.toString();
	}

	public String nomAffichageScolaireTri() {
		return "";
	}

	public String htmTooltipScolaireTri() {
		return null;
	}

	public String htmScolaireTri() {
		return scolaireTri == null ? "" : StringEscapeUtils.escapeHtml4(strScolaireTri());
	}

	public void htmScolaireTri(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "ScolaireTri\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "ScolaireTri() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setScolaireTri\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageScolaireTri()), "</span>");
				r.s("			<input");
							r.s(" name=\"scolaireTri\"");
							r.s(" value=\"", htmScolaireTri(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmScolaireTri());
			}
			r.l("</div>");
		}
	}

	//////////////
	// ecoleTri //
	//////////////

	/**	L'entité « ecoleTri »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer ecoleTri;
	public Wrap<Integer> ecoleTriWrap = new Wrap<Integer>().p(this).c(Integer.class).var("ecoleTri").o(ecoleTri);

	/**	<br/>L'entité « ecoleTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ecoleTri">Trouver l'entité ecoleTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleTri(Wrap<Integer> c);

	public Integer getEcoleTri() {
		return ecoleTri;
	}

	public void setEcoleTri(Integer ecoleTri) {
		this.ecoleTri = ecoleTri;
		this.ecoleTriWrap.alreadyInitialized = true;
	}
	public School setEcoleTri(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ecoleTri = Integer.parseInt(o);
		this.ecoleTriWrap.alreadyInitialized = true;
		return (School)this;
	}
	protected School ecoleTriInit() {
		if(!ecoleTriWrap.alreadyInitialized) {
			_ecoleTri(ecoleTriWrap);
			if(ecoleTri == null)
				setEcoleTri(ecoleTriWrap.o);
		}
		ecoleTriWrap.alreadyInitialized(true);
		return (School)this;
	}

	public Integer solrEcoleTri() {
		return ecoleTri;
	}

	public String strEcoleTri() {
		return ecoleTri == null ? "" : ecoleTri.toString();
	}

	public String nomAffichageEcoleTri() {
		return "";
	}

	public String htmTooltipEcoleTri() {
		return null;
	}

	public String htmEcoleTri() {
		return ecoleTri == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleTri());
	}

	public void htmEcoleTri(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "EcoleTri\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "EcoleTri() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setEcoleTri\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEcoleTri()), "</span>");
				r.s("			<input");
							r.s(" name=\"ecoleTri\"");
							r.s(" value=\"", htmEcoleTri(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEcoleTri());
			}
			r.l("</div>");
		}
	}

	//////////////
	// ecoleNom //
	//////////////

	/**	L'entité « ecoleNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleNom;
	public Wrap<String> ecoleNomWrap = new Wrap<String>().p(this).c(String.class).var("ecoleNom").o(ecoleNom);

	/**	<br/>L'entité « ecoleNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNom(Wrap<String> c);

	public String getEcoleNom() {
		return ecoleNom;
	}

	public void setEcoleNom(String ecoleNom) {
		this.ecoleNom = ecoleNom;
		this.ecoleNomWrap.alreadyInitialized = true;
	}
	protected School ecoleNomInit() {
		if(!ecoleNomWrap.alreadyInitialized) {
			_ecoleNom(ecoleNomWrap);
			if(ecoleNom == null)
				setEcoleNom(ecoleNomWrap.o);
		}
		ecoleNomWrap.alreadyInitialized(true);
		return (School)this;
	}

	public String solrEcoleNom() {
		return ecoleNom;
	}

	public String strEcoleNom() {
		return ecoleNom == null ? "" : ecoleNom;
	}

	public String nomAffichageEcoleNom() {
		return "name of the school";
	}

	public String htmTooltipEcoleNom() {
		return null;
	}

	public String htmEcoleNom() {
		return ecoleNom == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNom());
	}

	public void htmEcoleNom(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "EcoleNom\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "EcoleNom() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setEcoleNom\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEcoleNom()), "</span>");
				r.s("			<input");
							r.s(" name=\"ecoleNom\"");
							r.s(" value=\"", htmEcoleNom(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEcoleNom());
			}
			r.l("</div>");
		}
	}

	//////////////////////////
	// ecoleNumeroTelephone //
	//////////////////////////

	/**	L'entité « ecoleNumeroTelephone »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleNumeroTelephone;
	public Wrap<String> ecoleNumeroTelephoneWrap = new Wrap<String>().p(this).c(String.class).var("ecoleNumeroTelephone").o(ecoleNumeroTelephone);

	/**	<br/>L'entité « ecoleNumeroTelephone »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNumeroTelephone(Wrap<String> c);

	public String getEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}

	public void setEcoleNumeroTelephone(String ecoleNumeroTelephone) {
		this.ecoleNumeroTelephone = ecoleNumeroTelephone;
		this.ecoleNumeroTelephoneWrap.alreadyInitialized = true;
	}
	protected School ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneWrap.alreadyInitialized) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneWrap);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneWrap.o);
		}
		ecoleNumeroTelephoneWrap.alreadyInitialized(true);
		return (School)this;
	}

	public String solrEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}

	public String strEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : ecoleNumeroTelephone;
	}

	public String nomAffichageEcoleNumeroTelephone() {
		return "phone number";
	}

	public String htmTooltipEcoleNumeroTelephone() {
		return null;
	}

	public String htmEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNumeroTelephone());
	}

	public void htmEcoleNumeroTelephone(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "EcoleNumeroTelephone\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "EcoleNumeroTelephone() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setEcoleNumeroTelephone\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEcoleNumeroTelephone()), "</span>");
				r.s("			<input");
							r.s(" name=\"ecoleNumeroTelephone\"");
							r.s(" value=\"", htmEcoleNumeroTelephone(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEcoleNumeroTelephone());
			}
			r.l("</div>");
		}
	}

	////////////////////////////
	// ecoleAdministrateurNom //
	////////////////////////////

	/**	L'entité « ecoleAdministrateurNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleAdministrateurNom;
	public Wrap<String> ecoleAdministrateurNomWrap = new Wrap<String>().p(this).c(String.class).var("ecoleAdministrateurNom").o(ecoleAdministrateurNom);

	/**	<br/>L'entité « ecoleAdministrateurNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ecoleAdministrateurNom">Trouver l'entité ecoleAdministrateurNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAdministrateurNom(Wrap<String> c);

	public String getEcoleAdministrateurNom() {
		return ecoleAdministrateurNom;
	}

	public void setEcoleAdministrateurNom(String ecoleAdministrateurNom) {
		this.ecoleAdministrateurNom = ecoleAdministrateurNom;
		this.ecoleAdministrateurNomWrap.alreadyInitialized = true;
	}
	protected School ecoleAdministrateurNomInit() {
		if(!ecoleAdministrateurNomWrap.alreadyInitialized) {
			_ecoleAdministrateurNom(ecoleAdministrateurNomWrap);
			if(ecoleAdministrateurNom == null)
				setEcoleAdministrateurNom(ecoleAdministrateurNomWrap.o);
		}
		ecoleAdministrateurNomWrap.alreadyInitialized(true);
		return (School)this;
	}

	public String solrEcoleAdministrateurNom() {
		return ecoleAdministrateurNom;
	}

	public String strEcoleAdministrateurNom() {
		return ecoleAdministrateurNom == null ? "" : ecoleAdministrateurNom;
	}

	public String nomAffichageEcoleAdministrateurNom() {
		return "administrator of the school";
	}

	public String htmTooltipEcoleAdministrateurNom() {
		return null;
	}

	public String htmEcoleAdministrateurNom() {
		return ecoleAdministrateurNom == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleAdministrateurNom());
	}

	public void htmEcoleAdministrateurNom(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "EcoleAdministrateurNom\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "EcoleAdministrateurNom() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setEcoleAdministrateurNom\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEcoleAdministrateurNom()), "</span>");
				r.s("			<input");
							r.s(" name=\"ecoleAdministrateurNom\"");
							r.s(" value=\"", htmEcoleAdministrateurNom(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEcoleAdministrateurNom());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// ecoleAddresse //
	///////////////////

	/**	L'entité « ecoleAddresse »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleAddresse;
	public Wrap<String> ecoleAddresseWrap = new Wrap<String>().p(this).c(String.class).var("ecoleAddresse").o(ecoleAddresse);

	/**	<br/>L'entité « ecoleAddresse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAddresse(Wrap<String> c);

	public String getEcoleAddresse() {
		return ecoleAddresse;
	}

	public void setEcoleAddresse(String ecoleAddresse) {
		this.ecoleAddresse = ecoleAddresse;
		this.ecoleAddresseWrap.alreadyInitialized = true;
	}
	protected School ecoleAddresseInit() {
		if(!ecoleAddresseWrap.alreadyInitialized) {
			_ecoleAddresse(ecoleAddresseWrap);
			if(ecoleAddresse == null)
				setEcoleAddresse(ecoleAddresseWrap.o);
		}
		ecoleAddresseWrap.alreadyInitialized(true);
		return (School)this;
	}

	public String solrEcoleAddresse() {
		return ecoleAddresse;
	}

	public String strEcoleAddresse() {
		return ecoleAddresse == null ? "" : ecoleAddresse;
	}

	public String nomAffichageEcoleAddresse() {
		return "Address";
	}

	public String htmTooltipEcoleAddresse() {
		return null;
	}

	public String htmEcoleAddresse() {
		return ecoleAddresse == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleAddresse());
	}

	public void htmEcoleAddresse(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "EcoleAddresse\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "EcoleAddresse() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setEcoleAddresse\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEcoleAddresse()), "</span>");
				r.s("			<input");
							r.s(" name=\"ecoleAddresse\"");
							r.s(" value=\"", htmEcoleAddresse(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEcoleAddresse());
			}
			r.l("</div>");
		}
	}

	///////////////////////
	// objetSuggerePoids //
	///////////////////////

	/**	L'entité « objetSuggerePoids »
	 *	 is defined as null before being initialized. 
	 */
	protected Double objetSuggerePoids;
	public Wrap<Double> objetSuggerePoidsWrap = new Wrap<Double>().p(this).c(Double.class).var("objetSuggerePoids").o(objetSuggerePoids);

	/**	<br/>L'entité « objetSuggerePoids »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objetSuggerePoids">Trouver l'entité objetSuggerePoids dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objetSuggerePoids(Wrap<Double> c);

	public Double getObjetSuggerePoids() {
		return objetSuggerePoids;
	}

	public void setObjetSuggerePoids(Double objetSuggerePoids) {
		this.objetSuggerePoids = objetSuggerePoids;
		this.objetSuggerePoidsWrap.alreadyInitialized = true;
	}
	public School setObjetSuggerePoids(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.objetSuggerePoids = Double.parseDouble(o);
		this.objetSuggerePoidsWrap.alreadyInitialized = true;
		return (School)this;
	}
	protected School objetSuggerePoidsInit() {
		if(!objetSuggerePoidsWrap.alreadyInitialized) {
			_objetSuggerePoids(objetSuggerePoidsWrap);
			if(objetSuggerePoids == null)
				setObjetSuggerePoids(objetSuggerePoidsWrap.o);
		}
		objetSuggerePoidsWrap.alreadyInitialized(true);
		return (School)this;
	}

	public Double solrObjetSuggerePoids() {
		return objetSuggerePoids;
	}

	public String strObjetSuggerePoids() {
		return objetSuggerePoids == null ? "" : objetSuggerePoids.toString();
	}

	public String nomAffichageObjetSuggerePoids() {
		return "";
	}

	public String htmTooltipObjetSuggerePoids() {
		return null;
	}

	public String htmObjetSuggerePoids() {
		return objetSuggerePoids == null ? "" : StringEscapeUtils.escapeHtml4(strObjetSuggerePoids());
	}

	public void htmObjetSuggerePoids(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "ObjetSuggerePoids\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "ObjetSuggerePoids() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setObjetSuggerePoids\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageObjetSuggerePoids()), "</span>");
				r.s("			<input");
							r.s(" name=\"objetSuggerePoids\"");
							r.s(" value=\"", htmObjetSuggerePoids(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmObjetSuggerePoids());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// objetSuggere //
	//////////////////

	/**	L'entité « objetSuggere »
	 *	 is defined as null before being initialized. 
	 */
	protected String objetSuggere;
	public Wrap<String> objetSuggereWrap = new Wrap<String>().p(this).c(String.class).var("objetSuggere").o(objetSuggere);

	/**	<br/>L'entité « objetSuggere »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:objetSuggere">Trouver l'entité objetSuggere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objetSuggere(Wrap<String> c);

	public String getObjetSuggere() {
		return objetSuggere;
	}

	public void setObjetSuggere(String objetSuggere) {
		this.objetSuggere = objetSuggere;
		this.objetSuggereWrap.alreadyInitialized = true;
	}
	protected School objetSuggereInit() {
		if(!objetSuggereWrap.alreadyInitialized) {
			_objetSuggere(objetSuggereWrap);
			if(objetSuggere == null)
				setObjetSuggere(objetSuggereWrap.o);
		}
		objetSuggereWrap.alreadyInitialized(true);
		return (School)this;
	}

	public String solrObjetSuggere() {
		return objetSuggere;
	}

	public String strObjetSuggere() {
		return objetSuggere == null ? "" : objetSuggere;
	}

	public String nomAffichageObjetSuggere() {
		return "";
	}

	public String htmTooltipObjetSuggere() {
		return null;
	}

	public String htmObjetSuggere() {
		return objetSuggere == null ? "" : StringEscapeUtils.escapeHtml4(strObjetSuggere());
	}

	public void htmObjetSuggere(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "ObjetSuggere\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "ObjetSuggere() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setObjetSuggere\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageObjetSuggere()), "</span>");
				r.s("			<input");
							r.s(" name=\"objetSuggere\"");
							r.s(" value=\"", htmObjetSuggere(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmObjetSuggere());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// ecoleNomCourt //
	///////////////////

	/**	L'entité « ecoleNomCourt »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleNomCourt;
	public Wrap<String> ecoleNomCourtWrap = new Wrap<String>().p(this).c(String.class).var("ecoleNomCourt").o(ecoleNomCourt);

	/**	<br/>L'entité « ecoleNomCourt »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ecoleNomCourt">Trouver l'entité ecoleNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNomCourt(Wrap<String> c);

	public String getEcoleNomCourt() {
		return ecoleNomCourt;
	}

	public void setEcoleNomCourt(String ecoleNomCourt) {
		this.ecoleNomCourt = ecoleNomCourt;
		this.ecoleNomCourtWrap.alreadyInitialized = true;
	}
	protected School ecoleNomCourtInit() {
		if(!ecoleNomCourtWrap.alreadyInitialized) {
			_ecoleNomCourt(ecoleNomCourtWrap);
			if(ecoleNomCourt == null)
				setEcoleNomCourt(ecoleNomCourtWrap.o);
		}
		ecoleNomCourtWrap.alreadyInitialized(true);
		return (School)this;
	}

	public String solrEcoleNomCourt() {
		return ecoleNomCourt;
	}

	public String strEcoleNomCourt() {
		return ecoleNomCourt == null ? "" : ecoleNomCourt;
	}

	public String nomAffichageEcoleNomCourt() {
		return "";
	}

	public String htmTooltipEcoleNomCourt() {
		return null;
	}

	public String htmEcoleNomCourt() {
		return ecoleNomCourt == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNomCourt());
	}

	public void htmEcoleNomCourt(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "EcoleNomCourt\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "EcoleNomCourt() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setEcoleNomCourt\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEcoleNomCourt()), "</span>");
				r.s("			<input");
							r.s(" name=\"ecoleNomCourt\"");
							r.s(" value=\"", htmEcoleNomCourt(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEcoleNomCourt());
			}
			r.l("</div>");
		}
	}

	/////////////
	// ecoleId //
	/////////////

	/**	L'entité « ecoleId »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleId;
	public Wrap<String> ecoleIdWrap = new Wrap<String>().p(this).c(String.class).var("ecoleId").o(ecoleId);

	/**	<br/>L'entité « ecoleId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ecoleId">Trouver l'entité ecoleId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleId(Wrap<String> c);

	public String getEcoleId() {
		return ecoleId;
	}

	public void setEcoleId(String ecoleId) {
		this.ecoleId = ecoleId;
		this.ecoleIdWrap.alreadyInitialized = true;
	}
	protected School ecoleIdInit() {
		if(!ecoleIdWrap.alreadyInitialized) {
			_ecoleId(ecoleIdWrap);
			if(ecoleId == null)
				setEcoleId(ecoleIdWrap.o);
		}
		ecoleIdWrap.alreadyInitialized(true);
		return (School)this;
	}

	public String solrEcoleId() {
		return ecoleId;
	}

	public String strEcoleId() {
		return ecoleId == null ? "" : ecoleId;
	}

	public String nomAffichageEcoleId() {
		return "";
	}

	public String htmTooltipEcoleId() {
		return null;
	}

	public String htmEcoleId() {
		return ecoleId == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleId());
	}

	public void htmEcoleId(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "EcoleId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "EcoleId() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setEcoleId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEcoleId()), "</span>");
				r.s("			<input");
							r.s(" name=\"ecoleId\"");
							r.s(" value=\"", htmEcoleId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEcoleId());
			}
			r.l("</div>");
		}
	}

	/////////////
	// pageUri //
	/////////////

	/**	L'entité « pageUri »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUri;
	public Wrap<String> pageUriWrap = new Wrap<String>().p(this).c(String.class).var("pageUri").o(pageUri);

	/**	<br/>L'entité « pageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.school.School&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUri">Trouver l'entité pageUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUri(Wrap<String> c);

	public String getPageUri() {
		return pageUri;
	}

	public void setPageUri(String pageUri) {
		this.pageUri = pageUri;
		this.pageUriWrap.alreadyInitialized = true;
	}
	protected School pageUriInit() {
		if(!pageUriWrap.alreadyInitialized) {
			_pageUri(pageUriWrap);
			if(pageUri == null)
				setPageUri(pageUriWrap.o);
		}
		pageUriWrap.alreadyInitialized(true);
		return (School)this;
	}

	public String solrPageUri() {
		return pageUri;
	}

	public String strPageUri() {
		return pageUri == null ? "" : pageUri;
	}

	public String nomAffichagePageUri() {
		return null;
	}

	public String htmTooltipPageUri() {
		return null;
	}

	public String htmPageUri() {
		return pageUri == null ? "" : StringEscapeUtils.escapeHtml4(strPageUri());
	}

	public void htmPageUri(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchSchool", strPk(), "PageUri\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchSchool", strPk(), "PageUri() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/ecole?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setPageUri\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePageUri()), "</span>");
				r.s("			<input");
							r.s(" name=\"pageUri\"");
							r.s(" value=\"", htmPageUri(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPageUri());
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
		ecoleCleInit();
		enfantClesInit();
		blocClesInit();
		groupeAgeClesInit();
		sessionClesInit();
		saisonClesInit();
		anneeClesInit();
		scolaireTriInit();
		ecoleTriInit();
		ecoleNomInit();
		ecoleNumeroTelephoneInit();
		ecoleAdministrateurNomInit();
		ecoleAddresseInit();
		objetSuggerePoidsInit();
		objetSuggereInit();
		ecoleNomCourtInit();
		ecoleIdInit();
		pageUriInit();
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
			case "ecoleCle":
				return oSchool.ecoleCle;
			case "enfantCles":
				return oSchool.enfantCles;
			case "blocCles":
				return oSchool.blocCles;
			case "groupeAgeCles":
				return oSchool.groupeAgeCles;
			case "sessionCles":
				return oSchool.sessionCles;
			case "saisonCles":
				return oSchool.saisonCles;
			case "anneeCles":
				return oSchool.anneeCles;
			case "scolaireTri":
				return oSchool.scolaireTri;
			case "ecoleTri":
				return oSchool.ecoleTri;
			case "ecoleNom":
				return oSchool.ecoleNom;
			case "ecoleNumeroTelephone":
				return oSchool.ecoleNumeroTelephone;
			case "ecoleAdministrateurNom":
				return oSchool.ecoleAdministrateurNom;
			case "ecoleAddresse":
				return oSchool.ecoleAddresse;
			case "objetSuggerePoids":
				return oSchool.objetSuggerePoids;
			case "objetSuggere":
				return oSchool.objetSuggere;
			case "ecoleNomCourt":
				return oSchool.ecoleNomCourt;
			case "ecoleId":
				return oSchool.ecoleId;
			case "pageUri":
				return oSchool.pageUri;
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
			case "ecoleNom":
				setEcoleNom(val);
				savesSchool.add(var);
				return val;
			case "ecoleNumeroTelephone":
				setEcoleNumeroTelephone(val);
				savesSchool.add(var);
				return val;
			case "ecoleAdministrateurNom":
				setEcoleAdministrateurNom(val);
				savesSchool.add(var);
				return val;
			case "ecoleAddresse":
				setEcoleAddresse(val);
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

			if(savesSchool.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oSchool.setEcoleCle(ecoleCle);
			}

			if(savesSchool.contains("enfantCles")) {
				List<Long> enfantCles = (List<Long>)solrDocument.get("enfantCles_stored_longs");
				if(enfantCles != null)
					oSchool.enfantCles.addAll(enfantCles);
			}

			if(savesSchool.contains("blocCles")) {
				List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
				if(blocCles != null)
					oSchool.blocCles.addAll(blocCles);
			}

			if(savesSchool.contains("groupeAgeCles")) {
				List<Long> groupeAgeCles = (List<Long>)solrDocument.get("groupeAgeCles_stored_longs");
				if(groupeAgeCles != null)
					oSchool.groupeAgeCles.addAll(groupeAgeCles);
			}

			if(savesSchool.contains("sessionCles")) {
				List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
				if(sessionCles != null)
					oSchool.sessionCles.addAll(sessionCles);
			}

			if(savesSchool.contains("saisonCles")) {
				List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
				if(saisonCles != null)
					oSchool.saisonCles.addAll(saisonCles);
			}

			if(savesSchool.contains("anneeCles")) {
				List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
				if(anneeCles != null)
					oSchool.anneeCles.addAll(anneeCles);
			}

			if(savesSchool.contains("scolaireTri")) {
				Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
				if(scolaireTri != null)
					oSchool.setScolaireTri(scolaireTri);
			}

			if(savesSchool.contains("ecoleTri")) {
				Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
				if(ecoleTri != null)
					oSchool.setEcoleTri(ecoleTri);
			}

			if(savesSchool.contains("ecoleNom")) {
				String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
				if(ecoleNom != null)
					oSchool.setEcoleNom(ecoleNom);
			}

			if(savesSchool.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oSchool.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(savesSchool.contains("ecoleAdministrateurNom")) {
				String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
				if(ecoleAdministrateurNom != null)
					oSchool.setEcoleAdministrateurNom(ecoleAdministrateurNom);
			}

			if(savesSchool.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oSchool.setEcoleAddresse(ecoleAddresse);
			}

			if(savesSchool.contains("objetSuggere")) {
				String objetSuggere = (String)solrDocument.get("objetSuggere_stored_string");
				if(objetSuggere != null)
					oSchool.setObjetSuggere(objetSuggere);
			}

			if(savesSchool.contains("ecoleNomCourt")) {
				String ecoleNomCourt = (String)solrDocument.get("ecoleNomCourt_stored_string");
				if(ecoleNomCourt != null)
					oSchool.setEcoleNomCourt(ecoleNomCourt);
			}

			if(savesSchool.contains("ecoleId")) {
				String ecoleId = (String)solrDocument.get("ecoleId_stored_string");
				if(ecoleId != null)
					oSchool.setEcoleId(ecoleId);
			}

			if(savesSchool.contains("pageUri")) {
				String pageUri = (String)solrDocument.get("pageUri_stored_string");
				if(pageUri != null)
					oSchool.setPageUri(pageUri);
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

		if(ecoleCle != null) {
			document.addField("ecoleCle_indexed_long", ecoleCle);
			document.addField("ecoleCle_stored_long", ecoleCle);
		}
		if(enfantCles != null) {
			for(java.lang.Long o : enfantCles) {
				document.addField("enfantCles_indexed_longs", o);
			}
			for(java.lang.Long o : enfantCles) {
				document.addField("enfantCles_stored_longs", o);
			}
		}
		if(blocCles != null) {
			for(java.lang.Long o : blocCles) {
				document.addField("blocCles_indexed_longs", o);
			}
			for(java.lang.Long o : blocCles) {
				document.addField("blocCles_stored_longs", o);
			}
		}
		if(groupeAgeCles != null) {
			for(java.lang.Long o : groupeAgeCles) {
				document.addField("groupeAgeCles_indexed_longs", o);
			}
			for(java.lang.Long o : groupeAgeCles) {
				document.addField("groupeAgeCles_stored_longs", o);
			}
		}
		if(sessionCles != null) {
			for(java.lang.Long o : sessionCles) {
				document.addField("sessionCles_indexed_longs", o);
			}
			for(java.lang.Long o : sessionCles) {
				document.addField("sessionCles_stored_longs", o);
			}
		}
		if(saisonCles != null) {
			for(java.lang.Long o : saisonCles) {
				document.addField("saisonCles_indexed_longs", o);
			}
			for(java.lang.Long o : saisonCles) {
				document.addField("saisonCles_stored_longs", o);
			}
		}
		if(anneeCles != null) {
			for(java.lang.Long o : anneeCles) {
				document.addField("anneeCles_indexed_longs", o);
			}
			for(java.lang.Long o : anneeCles) {
				document.addField("anneeCles_stored_longs", o);
			}
		}
		if(scolaireTri != null) {
			document.addField("scolaireTri_indexed_int", scolaireTri);
			document.addField("scolaireTri_stored_int", scolaireTri);
		}
		if(ecoleTri != null) {
			document.addField("ecoleTri_indexed_int", ecoleTri);
			document.addField("ecoleTri_stored_int", ecoleTri);
		}
		if(ecoleNom != null) {
			document.addField("ecoleNom_indexed_string", ecoleNom);
			document.addField("ecoleNom_stored_string", ecoleNom);
		}
		if(ecoleNumeroTelephone != null) {
			document.addField("ecoleNumeroTelephone_indexed_string", ecoleNumeroTelephone);
			document.addField("ecoleNumeroTelephone_stored_string", ecoleNumeroTelephone);
		}
		if(ecoleAdministrateurNom != null) {
			document.addField("ecoleAdministrateurNom_indexed_string", ecoleAdministrateurNom);
			document.addField("ecoleAdministrateurNom_stored_string", ecoleAdministrateurNom);
		}
		if(ecoleAddresse != null) {
			document.addField("ecoleAddresse_indexed_string", ecoleAddresse);
			document.addField("ecoleAddresse_stored_string", ecoleAddresse);
		}
		if(objetSuggere != null) {
			document.addField("objetSuggere_suggested_string", objetSuggere);
			document.addField("objetSuggere_indexed_string", objetSuggere);
		}
		if(ecoleNomCourt != null) {
			document.addField("ecoleNomCourt_stored_string", ecoleNomCourt);
		}
		if(ecoleId != null) {
			document.addField("ecoleId_stored_string", ecoleId);
		}
		if(pageUri != null) {
			document.addField("pageUri_indexed_string", pageUri);
			document.addField("pageUri_stored_string", pageUri);
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

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oSchool.setEcoleCle(ecoleCle);

		List<Long> enfantCles = (List<Long>)solrDocument.get("enfantCles_stored_longs");
		if(enfantCles != null)
			oSchool.enfantCles.addAll(enfantCles);

		List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
		if(blocCles != null)
			oSchool.blocCles.addAll(blocCles);

		List<Long> groupeAgeCles = (List<Long>)solrDocument.get("groupeAgeCles_stored_longs");
		if(groupeAgeCles != null)
			oSchool.groupeAgeCles.addAll(groupeAgeCles);

		List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
		if(sessionCles != null)
			oSchool.sessionCles.addAll(sessionCles);

		List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
		if(saisonCles != null)
			oSchool.saisonCles.addAll(saisonCles);

		List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
		if(anneeCles != null)
			oSchool.anneeCles.addAll(anneeCles);

		Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
		if(scolaireTri != null)
			oSchool.setScolaireTri(scolaireTri);

		Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
		if(ecoleTri != null)
			oSchool.setEcoleTri(ecoleTri);

		String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
		if(ecoleNom != null)
			oSchool.setEcoleNom(ecoleNom);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oSchool.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
		if(ecoleAdministrateurNom != null)
			oSchool.setEcoleAdministrateurNom(ecoleAdministrateurNom);

		String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
		if(ecoleAddresse != null)
			oSchool.setEcoleAddresse(ecoleAddresse);

		String objetSuggere = (String)solrDocument.get("objetSuggere_stored_string");
		if(objetSuggere != null)
			oSchool.setObjetSuggere(objetSuggere);

		String ecoleNomCourt = (String)solrDocument.get("ecoleNomCourt_stored_string");
		if(ecoleNomCourt != null)
			oSchool.setEcoleNomCourt(ecoleNomCourt);

		String ecoleId = (String)solrDocument.get("ecoleId_stored_string");
		if(ecoleId != null)
			oSchool.setEcoleId(ecoleId);

		String pageUri = (String)solrDocument.get("pageUri_stored_string");
		if(pageUri != null)
			oSchool.setPageUri(pageUri);

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
		return Objects.hash(super.hashCode(), ecoleNom, ecoleNumeroTelephone, ecoleAdministrateurNom, ecoleAddresse);
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
				&& Objects.equals( ecoleNom, that.ecoleNom )
				&& Objects.equals( ecoleNumeroTelephone, that.ecoleNumeroTelephone )
				&& Objects.equals( ecoleAdministrateurNom, that.ecoleAdministrateurNom )
				&& Objects.equals( ecoleAddresse, that.ecoleAddresse );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("School {");
		sb.append( "ecoleNom: \"" ).append(ecoleNom).append( "\"" );
		sb.append( ", ecoleNumeroTelephone: \"" ).append(ecoleNumeroTelephone).append( "\"" );
		sb.append( ", ecoleAdministrateurNom: \"" ).append(ecoleAdministrateurNom).append( "\"" );
		sb.append( ", ecoleAddresse: \"" ).append(ecoleAddresse).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
