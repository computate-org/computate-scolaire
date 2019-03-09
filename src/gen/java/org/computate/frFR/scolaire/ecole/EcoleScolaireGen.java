package org.computate.frFR.scolaire.ecole;

import org.computate.frFR.scolaire.cluster.Cluster;
import java.lang.Double;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import org.computate.frFR.scolaire.couverture.Couverture;
import java.lang.Long;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import java.lang.String;
import io.vertx.core.logging.Logger;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.SQLClient;
import org.computate.frFR.scolaire.requete.RequeteSite;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EcoleScolaireGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(EcoleScolaire.class);

	public static final String EcoleScolaire_UnNom = "une école";
	public static final String EcoleScolaire_Ce = "cette ";
	public static final String EcoleScolaire_CeNom = "cette école";
	public static final String EcoleScolaire_Un = "une ";
	public static final String EcoleScolaire_LeNom = "l'école";
	public static final String EcoleScolaire_NomSingulier = "école";
	public static final String EcoleScolaire_NomPluriel = "écoles";
	public static final String EcoleScolaire_NomActuel = "école actuelle";
	public static final String EcoleScolaire_TousNom = "les écoles";
	public static final String EcoleScolaire_LesNoms = "les écoles";
	public static final String EcoleScolaire_AucunNomTrouve = "aucune école trouvée";
	public static final String EcoleScolaire_NomVar = "école";
	public static final String EcoleScolaire_DeNom = "d'école";
	public static final String EcoleScolaire_Couleur = "pink";
	public static final String EcoleScolaire_IconeGroupe = "regular";
	public static final String EcoleScolaire_IconeNom = "fort-awesome";

	//////////////
	// ecoleCle //
	//////////////

	/**	L'entité « ecoleCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long ecoleCle;
	public Couverture<Long> ecoleCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("ecoleCle").o(ecoleCle);

	/**	<br/>L'entité « ecoleCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleCle(Couverture<Long> c);

	public Long getEcoleCle() {
		return ecoleCle;
	}

	public void setEcoleCle(Long ecoleCle) {
		this.ecoleCle = ecoleCle;
		this.ecoleCleCouverture.dejaInitialise = true;
	}
	public EcoleScolaire setEcoleCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ecoleCle = Long.parseLong(o);
		this.ecoleCleCouverture.dejaInitialise = true;
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public Long solrEcoleCle() {
		return ecoleCle;
	}

	public String strEcoleCle() {
		return ecoleCle == null ? "" : ecoleCle.toString();
	}

	public String nomAffichageEcoleCle() {
		return "Cl\u00E9";
	}

	public String htmTooltipEcoleCle() {
		return null;
	}

	public String htmEcoleCle() {
		return ecoleCle == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleCle());
	}

	public void htmEcoleCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "EcoleCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "EcoleCle() {");
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
	public Couverture<List<Long>> enfantClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("enfantCles").o(enfantCles);

	/**	<br/>L'entité « enfantCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantCles">Trouver l'entité enfantCles dans Solr</a>
	 * <br/>
	 * @param enfantCles est l'entité déjà construit. 
	 **/
	protected abstract void _enfantCles(List<Long> o);

	public List<Long> getEnfantCles() {
		return enfantCles;
	}

	public void setEnfantCles(List<Long> enfantCles) {
		this.enfantCles = enfantCles;
		this.enfantClesCouverture.dejaInitialise = true;
	}
	public EcoleScolaire addEnfantCles(Long...objets) {
		for(Long o : objets) {
			addEnfantCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addEnfantCles(Long o) {
		if(o != null && !enfantCles.contains(o))
			this.enfantCles.add(o);
		return (EcoleScolaire)this;
	}
	public EcoleScolaire setEnfantCles(JsonArray objets) {
		enfantCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnfantCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addEnfantCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addEnfantCles(p);
		}
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire enfantClesInit() {
		if(!enfantClesCouverture.dejaInitialise) {
			_enfantCles(enfantCles);
		}
		enfantClesCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public List<Long> solrEnfantCles() {
		return enfantCles;
	}

	public String strEnfantCles() {
		return enfantCles == null ? "" : enfantCles.toString();
	}

	public String nomAffichageEnfantCles() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEnfantCles() {
		return null;
	}

	public String htmEnfantCles() {
		return enfantCles == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantCles());
	}

	public void htmEnfantCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "EnfantCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "EnfantCles() {");
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
	public Couverture<List<Long>> blocClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("blocCles").o(blocCles);

	/**	<br/>L'entité « blocCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocCles">Trouver l'entité blocCles dans Solr</a>
	 * <br/>
	 * @param blocCles est l'entité déjà construit. 
	 **/
	protected abstract void _blocCles(List<Long> o);

	public List<Long> getBlocCles() {
		return blocCles;
	}

	public void setBlocCles(List<Long> blocCles) {
		this.blocCles = blocCles;
		this.blocClesCouverture.dejaInitialise = true;
	}
	public EcoleScolaire addBlocCles(Long...objets) {
		for(Long o : objets) {
			addBlocCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addBlocCles(Long o) {
		if(o != null && !blocCles.contains(o))
			this.blocCles.add(o);
		return (EcoleScolaire)this;
	}
	public EcoleScolaire setBlocCles(JsonArray objets) {
		blocCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlocCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addBlocCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addBlocCles(p);
		}
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire blocClesInit() {
		if(!blocClesCouverture.dejaInitialise) {
			_blocCles(blocCles);
		}
		blocClesCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public List<Long> solrBlocCles() {
		return blocCles;
	}

	public String strBlocCles() {
		return blocCles == null ? "" : blocCles.toString();
	}

	public String nomAffichageBlocCles() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipBlocCles() {
		return null;
	}

	public String htmBlocCles() {
		return blocCles == null ? "" : StringEscapeUtils.escapeHtml4(strBlocCles());
	}

	public void htmBlocCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "BlocCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "BlocCles() {");
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
	public Couverture<List<Long>> groupeAgeClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("groupeAgeCles").o(groupeAgeCles);

	/**	<br/>L'entité « groupeAgeCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:groupeAgeCles">Trouver l'entité groupeAgeCles dans Solr</a>
	 * <br/>
	 * @param groupeAgeCles est l'entité déjà construit. 
	 **/
	protected abstract void _groupeAgeCles(List<Long> o);

	public List<Long> getGroupeAgeCles() {
		return groupeAgeCles;
	}

	public void setGroupeAgeCles(List<Long> groupeAgeCles) {
		this.groupeAgeCles = groupeAgeCles;
		this.groupeAgeClesCouverture.dejaInitialise = true;
	}
	public EcoleScolaire addGroupeAgeCles(Long...objets) {
		for(Long o : objets) {
			addGroupeAgeCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addGroupeAgeCles(Long o) {
		if(o != null && !groupeAgeCles.contains(o))
			this.groupeAgeCles.add(o);
		return (EcoleScolaire)this;
	}
	public EcoleScolaire setGroupeAgeCles(JsonArray objets) {
		groupeAgeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGroupeAgeCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addGroupeAgeCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addGroupeAgeCles(p);
		}
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire groupeAgeClesInit() {
		if(!groupeAgeClesCouverture.dejaInitialise) {
			_groupeAgeCles(groupeAgeCles);
		}
		groupeAgeClesCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public List<Long> solrGroupeAgeCles() {
		return groupeAgeCles;
	}

	public String strGroupeAgeCles() {
		return groupeAgeCles == null ? "" : groupeAgeCles.toString();
	}

	public String nomAffichageGroupeAgeCles() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipGroupeAgeCles() {
		return null;
	}

	public String htmGroupeAgeCles() {
		return groupeAgeCles == null ? "" : StringEscapeUtils.escapeHtml4(strGroupeAgeCles());
	}

	public void htmGroupeAgeCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "GroupeAgeCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "GroupeAgeCles() {");
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
	public Couverture<List<Long>> sessionClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("sessionCles").o(sessionCles);

	/**	<br/>L'entité « sessionCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCles">Trouver l'entité sessionCles dans Solr</a>
	 * <br/>
	 * @param sessionCles est l'entité déjà construit. 
	 **/
	protected abstract void _sessionCles(List<Long> o);

	public List<Long> getSessionCles() {
		return sessionCles;
	}

	public void setSessionCles(List<Long> sessionCles) {
		this.sessionCles = sessionCles;
		this.sessionClesCouverture.dejaInitialise = true;
	}
	public EcoleScolaire addSessionCles(Long...objets) {
		for(Long o : objets) {
			addSessionCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addSessionCles(Long o) {
		if(o != null && !sessionCles.contains(o))
			this.sessionCles.add(o);
		return (EcoleScolaire)this;
	}
	public EcoleScolaire setSessionCles(JsonArray objets) {
		sessionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addSessionCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addSessionCles(p);
		}
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire sessionClesInit() {
		if(!sessionClesCouverture.dejaInitialise) {
			_sessionCles(sessionCles);
		}
		sessionClesCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public List<Long> solrSessionCles() {
		return sessionCles;
	}

	public String strSessionCles() {
		return sessionCles == null ? "" : sessionCles.toString();
	}

	public String nomAffichageSessionCles() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipSessionCles() {
		return null;
	}

	public String htmSessionCles() {
		return sessionCles == null ? "" : StringEscapeUtils.escapeHtml4(strSessionCles());
	}

	public void htmSessionCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "SessionCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "SessionCles() {");
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
	public Couverture<List<Long>> saisonClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("saisonCles").o(saisonCles);

	/**	<br/>L'entité « saisonCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCles">Trouver l'entité saisonCles dans Solr</a>
	 * <br/>
	 * @param saisonCles est l'entité déjà construit. 
	 **/
	protected abstract void _saisonCles(List<Long> o);

	public List<Long> getSaisonCles() {
		return saisonCles;
	}

	public void setSaisonCles(List<Long> saisonCles) {
		this.saisonCles = saisonCles;
		this.saisonClesCouverture.dejaInitialise = true;
	}
	public EcoleScolaire addSaisonCles(Long...objets) {
		for(Long o : objets) {
			addSaisonCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addSaisonCles(Long o) {
		if(o != null && !saisonCles.contains(o))
			this.saisonCles.add(o);
		return (EcoleScolaire)this;
	}
	public EcoleScolaire setSaisonCles(JsonArray objets) {
		saisonCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSaisonCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addSaisonCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addSaisonCles(p);
		}
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire saisonClesInit() {
		if(!saisonClesCouverture.dejaInitialise) {
			_saisonCles(saisonCles);
		}
		saisonClesCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public List<Long> solrSaisonCles() {
		return saisonCles;
	}

	public String strSaisonCles() {
		return saisonCles == null ? "" : saisonCles.toString();
	}

	public String nomAffichageSaisonCles() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipSaisonCles() {
		return null;
	}

	public String htmSaisonCles() {
		return saisonCles == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonCles());
	}

	public void htmSaisonCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "SaisonCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "SaisonCles() {");
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
	public Couverture<List<Long>> anneeClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("anneeCles").o(anneeCles);

	/**	<br/>L'entité « anneeCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCles">Trouver l'entité anneeCles dans Solr</a>
	 * <br/>
	 * @param anneeCles est l'entité déjà construit. 
	 **/
	protected abstract void _anneeCles(List<Long> o);

	public List<Long> getAnneeCles() {
		return anneeCles;
	}

	public void setAnneeCles(List<Long> anneeCles) {
		this.anneeCles = anneeCles;
		this.anneeClesCouverture.dejaInitialise = true;
	}
	public EcoleScolaire addAnneeCles(Long...objets) {
		for(Long o : objets) {
			addAnneeCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addAnneeCles(Long o) {
		if(o != null && !anneeCles.contains(o))
			this.anneeCles.add(o);
		return (EcoleScolaire)this;
	}
	public EcoleScolaire setAnneeCles(JsonArray objets) {
		anneeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAnneeCles(o);
		}
		return (EcoleScolaire)this;
	}
	public EcoleScolaire addAnneeCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addAnneeCles(p);
		}
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire anneeClesInit() {
		if(!anneeClesCouverture.dejaInitialise) {
			_anneeCles(anneeCles);
		}
		anneeClesCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public List<Long> solrAnneeCles() {
		return anneeCles;
	}

	public String strAnneeCles() {
		return anneeCles == null ? "" : anneeCles.toString();
	}

	public String nomAffichageAnneeCles() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipAnneeCles() {
		return null;
	}

	public String htmAnneeCles() {
		return anneeCles == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCles());
	}

	public void htmAnneeCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "AnneeCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "AnneeCles() {");
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

	/////////////
	// archive //
	/////////////

	/**	L'entité « archive »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean archive;
	public Couverture<Boolean> archiveCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("archive").o(archive);

	/**	<br/>L'entité « archive »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:archive">Trouver l'entité archive dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _archive(Couverture<Boolean> c);

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
		this.archiveCouverture.dejaInitialise = true;
	}
	public EcoleScolaire setArchive(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.archive = Boolean.parseBoolean(o);
		this.archiveCouverture.dejaInitialise = true;
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire archiveInit() {
		if(!archiveCouverture.dejaInitialise) {
			_archive(archiveCouverture);
			if(archive == null)
				setArchive(archiveCouverture.o);
		}
		archiveCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public Boolean solrArchive() {
		return archive;
	}

	public String strArchive() {
		return archive == null ? "" : archive.toString();
	}

	public String nomAffichageArchive() {
		return "Archiv\u00E9";
	}

	public String htmTooltipArchive() {
		return null;
	}

	public String htmArchive() {
		return archive == null ? "" : StringEscapeUtils.escapeHtml4(strArchive());
	}

	public void htmArchive(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "Archive\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "Archive() {");
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
				r.l("				data: {\"setArchive\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageArchive()), "</span>");
				r.s("			<input");
							r.s(" name=\"archive\"");
							r.s(" value=\"", htmArchive(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmArchive());
			}
			r.l("</div>");
		}
	}

	//////////////
	// supprime //
	//////////////

	/**	L'entité « supprime »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean supprime;
	public Couverture<Boolean> supprimeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("supprime").o(supprime);

	/**	<br/>L'entité « supprime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:supprime">Trouver l'entité supprime dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _supprime(Couverture<Boolean> c);

	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
		this.supprimeCouverture.dejaInitialise = true;
	}
	public EcoleScolaire setSupprime(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.supprime = Boolean.parseBoolean(o);
		this.supprimeCouverture.dejaInitialise = true;
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire supprimeInit() {
		if(!supprimeCouverture.dejaInitialise) {
			_supprime(supprimeCouverture);
			if(supprime == null)
				setSupprime(supprimeCouverture.o);
		}
		supprimeCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public Boolean solrSupprime() {
		return supprime;
	}

	public String strSupprime() {
		return supprime == null ? "" : supprime.toString();
	}

	public String nomAffichageSupprime() {
		return "Supprim\u00E9";
	}

	public String htmTooltipSupprime() {
		return null;
	}

	public String htmSupprime() {
		return supprime == null ? "" : StringEscapeUtils.escapeHtml4(strSupprime());
	}

	public void htmSupprime(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "Supprime\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "Supprime() {");
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
				r.l("				data: {\"setSupprime\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSupprime()), "</span>");
				r.s("			<input");
							r.s(" name=\"supprime\"");
							r.s(" value=\"", htmSupprime(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSupprime());
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
	public Couverture<Integer> scolaireTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("scolaireTri").o(scolaireTri);

	/**	<br/>L'entité « scolaireTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:scolaireTri">Trouver l'entité scolaireTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _scolaireTri(Couverture<Integer> c);

	public Integer getScolaireTri() {
		return scolaireTri;
	}

	public void setScolaireTri(Integer scolaireTri) {
		this.scolaireTri = scolaireTri;
		this.scolaireTriCouverture.dejaInitialise = true;
	}
	public EcoleScolaire setScolaireTri(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.scolaireTri = Integer.parseInt(o);
		this.scolaireTriCouverture.dejaInitialise = true;
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire scolaireTriInit() {
		if(!scolaireTriCouverture.dejaInitialise) {
			_scolaireTri(scolaireTriCouverture);
			if(scolaireTri == null)
				setScolaireTri(scolaireTriCouverture.o);
		}
		scolaireTriCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public Integer solrScolaireTri() {
		return scolaireTri;
	}

	public String strScolaireTri() {
		return scolaireTri == null ? "" : scolaireTri.toString();
	}

	public String nomAffichageScolaireTri() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipScolaireTri() {
		return null;
	}

	public String htmScolaireTri() {
		return scolaireTri == null ? "" : StringEscapeUtils.escapeHtml4(strScolaireTri());
	}

	public void htmScolaireTri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "ScolaireTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "ScolaireTri() {");
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
	public Couverture<Integer> ecoleTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ecoleTri").o(ecoleTri);

	/**	<br/>L'entité « ecoleTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleTri">Trouver l'entité ecoleTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleTri(Couverture<Integer> c);

	public Integer getEcoleTri() {
		return ecoleTri;
	}

	public void setEcoleTri(Integer ecoleTri) {
		this.ecoleTri = ecoleTri;
		this.ecoleTriCouverture.dejaInitialise = true;
	}
	public EcoleScolaire setEcoleTri(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ecoleTri = Integer.parseInt(o);
		this.ecoleTriCouverture.dejaInitialise = true;
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire ecoleTriInit() {
		if(!ecoleTriCouverture.dejaInitialise) {
			_ecoleTri(ecoleTriCouverture);
			if(ecoleTri == null)
				setEcoleTri(ecoleTriCouverture.o);
		}
		ecoleTriCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public Integer solrEcoleTri() {
		return ecoleTri;
	}

	public String strEcoleTri() {
		return ecoleTri == null ? "" : ecoleTri.toString();
	}

	public String nomAffichageEcoleTri() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEcoleTri() {
		return null;
	}

	public String htmEcoleTri() {
		return ecoleTri == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleTri());
	}

	public void htmEcoleTri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "EcoleTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "EcoleTri() {");
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
	public Couverture<String> ecoleNomCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNom").o(ecoleNom);

	/**	<br/>L'entité « ecoleNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNom(Couverture<String> c);

	public String getEcoleNom() {
		return ecoleNom;
	}

	public void setEcoleNom(String ecoleNom) {
		this.ecoleNom = ecoleNom;
		this.ecoleNomCouverture.dejaInitialise = true;
	}
	protected EcoleScolaire ecoleNomInit() {
		if(!ecoleNomCouverture.dejaInitialise) {
			_ecoleNom(ecoleNomCouverture);
			if(ecoleNom == null)
				setEcoleNom(ecoleNomCouverture.o);
		}
		ecoleNomCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public String solrEcoleNom() {
		return ecoleNom;
	}

	public String strEcoleNom() {
		return ecoleNom == null ? "" : ecoleNom;
	}

	public String nomAffichageEcoleNom() {
		return "Nom de l'\u00E9cole";
	}

	public String htmTooltipEcoleNom() {
		return null;
	}

	public String htmEcoleNom() {
		return ecoleNom == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNom());
	}

	public void htmEcoleNom(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "EcoleNom\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "EcoleNom() {");
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
	public Couverture<String> ecoleNumeroTelephoneCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNumeroTelephone").o(ecoleNumeroTelephone);

	/**	<br/>L'entité « ecoleNumeroTelephone »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNumeroTelephone(Couverture<String> c);

	public String getEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}

	public void setEcoleNumeroTelephone(String ecoleNumeroTelephone) {
		this.ecoleNumeroTelephone = ecoleNumeroTelephone;
		this.ecoleNumeroTelephoneCouverture.dejaInitialise = true;
	}
	protected EcoleScolaire ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneCouverture.dejaInitialise) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneCouverture);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneCouverture.o);
		}
		ecoleNumeroTelephoneCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public String solrEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}

	public String strEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : ecoleNumeroTelephone;
	}

	public String nomAffichageEcoleNumeroTelephone() {
		return "Num\u00E9ro de t\u00E9l\u00E9phone";
	}

	public String htmTooltipEcoleNumeroTelephone() {
		return null;
	}

	public String htmEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNumeroTelephone());
	}

	public void htmEcoleNumeroTelephone(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "EcoleNumeroTelephone\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "EcoleNumeroTelephone() {");
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
	public Couverture<String> ecoleAdministrateurNomCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleAdministrateurNom").o(ecoleAdministrateurNom);

	/**	<br/>L'entité « ecoleAdministrateurNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAdministrateurNom">Trouver l'entité ecoleAdministrateurNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAdministrateurNom(Couverture<String> c);

	public String getEcoleAdministrateurNom() {
		return ecoleAdministrateurNom;
	}

	public void setEcoleAdministrateurNom(String ecoleAdministrateurNom) {
		this.ecoleAdministrateurNom = ecoleAdministrateurNom;
		this.ecoleAdministrateurNomCouverture.dejaInitialise = true;
	}
	protected EcoleScolaire ecoleAdministrateurNomInit() {
		if(!ecoleAdministrateurNomCouverture.dejaInitialise) {
			_ecoleAdministrateurNom(ecoleAdministrateurNomCouverture);
			if(ecoleAdministrateurNom == null)
				setEcoleAdministrateurNom(ecoleAdministrateurNomCouverture.o);
		}
		ecoleAdministrateurNomCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public String solrEcoleAdministrateurNom() {
		return ecoleAdministrateurNom;
	}

	public String strEcoleAdministrateurNom() {
		return ecoleAdministrateurNom == null ? "" : ecoleAdministrateurNom;
	}

	public String nomAffichageEcoleAdministrateurNom() {
		return "Administrateur de l'\u00E9cole";
	}

	public String htmTooltipEcoleAdministrateurNom() {
		return null;
	}

	public String htmEcoleAdministrateurNom() {
		return ecoleAdministrateurNom == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleAdministrateurNom());
	}

	public void htmEcoleAdministrateurNom(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "EcoleAdministrateurNom\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "EcoleAdministrateurNom() {");
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
	public Couverture<String> ecoleAddresseCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleAddresse").o(ecoleAddresse);

	/**	<br/>L'entité « ecoleAddresse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAddresse(Couverture<String> c);

	public String getEcoleAddresse() {
		return ecoleAddresse;
	}

	public void setEcoleAddresse(String ecoleAddresse) {
		this.ecoleAddresse = ecoleAddresse;
		this.ecoleAddresseCouverture.dejaInitialise = true;
	}
	protected EcoleScolaire ecoleAddresseInit() {
		if(!ecoleAddresseCouverture.dejaInitialise) {
			_ecoleAddresse(ecoleAddresseCouverture);
			if(ecoleAddresse == null)
				setEcoleAddresse(ecoleAddresseCouverture.o);
		}
		ecoleAddresseCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public String solrEcoleAddresse() {
		return ecoleAddresse;
	}

	public String strEcoleAddresse() {
		return ecoleAddresse == null ? "" : ecoleAddresse;
	}

	public String nomAffichageEcoleAddresse() {
		return "Addresse";
	}

	public String htmTooltipEcoleAddresse() {
		return null;
	}

	public String htmEcoleAddresse() {
		return ecoleAddresse == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleAddresse());
	}

	public void htmEcoleAddresse(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "EcoleAddresse\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "EcoleAddresse() {");
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
	public Couverture<Double> objetSuggerePoidsCouverture = new Couverture<Double>().p(this).c(Double.class).var("objetSuggerePoids").o(objetSuggerePoids);

	/**	<br/>L'entité « objetSuggerePoids »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetSuggerePoids">Trouver l'entité objetSuggerePoids dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objetSuggerePoids(Couverture<Double> c);

	public Double getObjetSuggerePoids() {
		return objetSuggerePoids;
	}

	public void setObjetSuggerePoids(Double objetSuggerePoids) {
		this.objetSuggerePoids = objetSuggerePoids;
		this.objetSuggerePoidsCouverture.dejaInitialise = true;
	}
	public EcoleScolaire setObjetSuggerePoids(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.objetSuggerePoids = Double.parseDouble(o);
		this.objetSuggerePoidsCouverture.dejaInitialise = true;
		return (EcoleScolaire)this;
	}
	protected EcoleScolaire objetSuggerePoidsInit() {
		if(!objetSuggerePoidsCouverture.dejaInitialise) {
			_objetSuggerePoids(objetSuggerePoidsCouverture);
			if(objetSuggerePoids == null)
				setObjetSuggerePoids(objetSuggerePoidsCouverture.o);
		}
		objetSuggerePoidsCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public Double solrObjetSuggerePoids() {
		return objetSuggerePoids;
	}

	public String strObjetSuggerePoids() {
		return objetSuggerePoids == null ? "" : objetSuggerePoids.toString();
	}

	public String nomAffichageObjetSuggerePoids() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipObjetSuggerePoids() {
		return null;
	}

	public String htmObjetSuggerePoids() {
		return objetSuggerePoids == null ? "" : StringEscapeUtils.escapeHtml4(strObjetSuggerePoids());
	}

	public void htmObjetSuggerePoids(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "ObjetSuggerePoids\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "ObjetSuggerePoids() {");
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
	public Couverture<String> objetSuggereCouverture = new Couverture<String>().p(this).c(String.class).var("objetSuggere").o(objetSuggere);

	/**	<br/>L'entité « objetSuggere »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetSuggere">Trouver l'entité objetSuggere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _objetSuggere(Couverture<String> c);

	public String getObjetSuggere() {
		return objetSuggere;
	}

	public void setObjetSuggere(String objetSuggere) {
		this.objetSuggere = objetSuggere;
		this.objetSuggereCouverture.dejaInitialise = true;
	}
	protected EcoleScolaire objetSuggereInit() {
		if(!objetSuggereCouverture.dejaInitialise) {
			_objetSuggere(objetSuggereCouverture);
			if(objetSuggere == null)
				setObjetSuggere(objetSuggereCouverture.o);
		}
		objetSuggereCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public String solrObjetSuggere() {
		return objetSuggere;
	}

	public String strObjetSuggere() {
		return objetSuggere == null ? "" : objetSuggere;
	}

	public String nomAffichageObjetSuggere() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipObjetSuggere() {
		return null;
	}

	public String htmObjetSuggere() {
		return objetSuggere == null ? "" : StringEscapeUtils.escapeHtml4(strObjetSuggere());
	}

	public void htmObjetSuggere(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "ObjetSuggere\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "ObjetSuggere() {");
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
	public Couverture<String> ecoleNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNomCourt").o(ecoleNomCourt);

	/**	<br/>L'entité « ecoleNomCourt »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomCourt">Trouver l'entité ecoleNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNomCourt(Couverture<String> c);

	public String getEcoleNomCourt() {
		return ecoleNomCourt;
	}

	public void setEcoleNomCourt(String ecoleNomCourt) {
		this.ecoleNomCourt = ecoleNomCourt;
		this.ecoleNomCourtCouverture.dejaInitialise = true;
	}
	protected EcoleScolaire ecoleNomCourtInit() {
		if(!ecoleNomCourtCouverture.dejaInitialise) {
			_ecoleNomCourt(ecoleNomCourtCouverture);
			if(ecoleNomCourt == null)
				setEcoleNomCourt(ecoleNomCourtCouverture.o);
		}
		ecoleNomCourtCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public String solrEcoleNomCourt() {
		return ecoleNomCourt;
	}

	public String strEcoleNomCourt() {
		return ecoleNomCourt == null ? "" : ecoleNomCourt;
	}

	public String nomAffichageEcoleNomCourt() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEcoleNomCourt() {
		return null;
	}

	public String htmEcoleNomCourt() {
		return ecoleNomCourt == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNomCourt());
	}

	public void htmEcoleNomCourt(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "EcoleNomCourt\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "EcoleNomCourt() {");
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
	public Couverture<String> ecoleIdCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleId").o(ecoleId);

	/**	<br/>L'entité « ecoleId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleId">Trouver l'entité ecoleId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleId(Couverture<String> c);

	public String getEcoleId() {
		return ecoleId;
	}

	public void setEcoleId(String ecoleId) {
		this.ecoleId = ecoleId;
		this.ecoleIdCouverture.dejaInitialise = true;
	}
	protected EcoleScolaire ecoleIdInit() {
		if(!ecoleIdCouverture.dejaInitialise) {
			_ecoleId(ecoleIdCouverture);
			if(ecoleId == null)
				setEcoleId(ecoleIdCouverture.o);
		}
		ecoleIdCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
	}

	public String solrEcoleId() {
		return ecoleId;
	}

	public String strEcoleId() {
		return ecoleId == null ? "" : ecoleId;
	}

	public String nomAffichageEcoleId() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEcoleId() {
		return null;
	}

	public String htmEcoleId() {
		return ecoleId == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleId());
	}

	public void htmEcoleId(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "EcoleId\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "EcoleId() {");
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
	public Couverture<String> pageUriCouverture = new Couverture<String>().p(this).c(String.class).var("pageUri").o(pageUri);

	/**	<br/>L'entité « pageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.ecole.EcoleScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUri">Trouver l'entité pageUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUri(Couverture<String> c);

	public String getPageUri() {
		return pageUri;
	}

	public void setPageUri(String pageUri) {
		this.pageUri = pageUri;
		this.pageUriCouverture.dejaInitialise = true;
	}
	protected EcoleScolaire pageUriInit() {
		if(!pageUriCouverture.dejaInitialise) {
			_pageUri(pageUriCouverture);
			if(pageUri == null)
				setPageUri(pageUriCouverture.o);
		}
		pageUriCouverture.dejaInitialise(true);
		return (EcoleScolaire)this;
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

	public void htmPageUri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEcoleScolaire", strPk(), "PageUri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEcoleScolaire", strPk(), "PageUri() {");
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
	// initLoin //
	//////////////

	protected boolean dejaInitialiseEcoleScolaire = false;

	public EcoleScolaire initLoinEcoleScolaire(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseEcoleScolaire) {
			dejaInitialiseEcoleScolaire = true;
			initLoinEcoleScolaire();
		}
		return (EcoleScolaire)this;
	}

	public void initLoinEcoleScolaire() {
		super.initLoinCluster(requeteSite_);
		initEcoleScolaire();
	}

	public void initEcoleScolaire() {
		ecoleCleInit();
		enfantClesInit();
		blocClesInit();
		groupeAgeClesInit();
		sessionClesInit();
		saisonClesInit();
		anneeClesInit();
		archiveInit();
		supprimeInit();
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

	@Override public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinEcoleScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEcoleScolaire(RequeteSite requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteEcoleScolaire(requeteSite_);
	}

	/////////////
	// indexer //
	/////////////

	public static void indexer() {
		try {
			RequeteSite requeteSite = new RequeteSite();
			requeteSite.initLoinRequeteSite();
			SiteContexte siteContexte = new SiteContexte();
			siteContexte.getConfigSite().setConfigChemin("/usr/local/src/computate-scolaire/config/computate-scolaire.config");
			siteContexte.initLoinSiteContexte();
			siteContexte.setRequeteSite_(requeteSite);
			requeteSite.setSiteContexte_(siteContexte);
			EcoleScolaire o = new EcoleScolaire();
			o.requeteSiteEcoleScolaire(requeteSite);
			o.initLoinEcoleScolaire(requeteSite);
			o.indexerEcoleScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() throws Exception {
		indexerEcoleScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) throws Exception {
		indexerEcoleScolaire(document);
	}

	public void indexerEcoleScolaire(SolrClient clientSolr) throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		indexerEcoleScolaire(document);
		clientSolr.add(document);
		clientSolr.commit();
	}

	public void indexerEcoleScolaire() throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		indexerEcoleScolaire(document);
		SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
		clientSolr.add(document);
		clientSolr.commit();
	}

	public void indexerEcoleScolaire(SolrInputDocument document) throws Exception {
		if(sauvegardesEcoleScolaire != null)
			document.addField("sauvegardesEcoleScolaire_stored_strings", sauvegardesEcoleScolaire);

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
		if(archive != null) {
			document.addField("archive_indexed_boolean", archive);
			document.addField("archive_stored_boolean", archive);
		}
		if(supprime != null) {
			document.addField("supprime_indexed_boolean", supprime);
			document.addField("supprime_stored_boolean", supprime);
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
		super.indexerCluster(document);

	}

	public void desindexerEcoleScolaire() throws Exception {
		RequeteSite requeteSite = new RequeteSite();
		requeteSite.initLoinRequeteSite();
		SiteContexte siteContexte = new SiteContexte();
		siteContexte.initLoinSiteContexte();
		siteContexte.setRequeteSite_(requeteSite);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		initLoinEcoleScolaire(siteContexte.getRequeteSite_());
		SolrClient clientSolr = siteContexte.getClientSolr();
		clientSolr.deleteById(id.toString());
		clientSolr.commit();
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEcoleScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEcoleScolaire(String var) throws Exception {
		EcoleScolaire oEcoleScolaire = (EcoleScolaire)this;
		switch(var) {
			case "ecoleCle":
				return oEcoleScolaire.ecoleCle;
			case "enfantCles":
				return oEcoleScolaire.enfantCles;
			case "blocCles":
				return oEcoleScolaire.blocCles;
			case "groupeAgeCles":
				return oEcoleScolaire.groupeAgeCles;
			case "sessionCles":
				return oEcoleScolaire.sessionCles;
			case "saisonCles":
				return oEcoleScolaire.saisonCles;
			case "anneeCles":
				return oEcoleScolaire.anneeCles;
			case "archive":
				return oEcoleScolaire.archive;
			case "supprime":
				return oEcoleScolaire.supprime;
			case "scolaireTri":
				return oEcoleScolaire.scolaireTri;
			case "ecoleTri":
				return oEcoleScolaire.ecoleTri;
			case "ecoleNom":
				return oEcoleScolaire.ecoleNom;
			case "ecoleNumeroTelephone":
				return oEcoleScolaire.ecoleNumeroTelephone;
			case "ecoleAdministrateurNom":
				return oEcoleScolaire.ecoleAdministrateurNom;
			case "ecoleAddresse":
				return oEcoleScolaire.ecoleAddresse;
			case "objetSuggerePoids":
				return oEcoleScolaire.objetSuggerePoids;
			case "objetSuggere":
				return oEcoleScolaire.objetSuggere;
			case "ecoleNomCourt":
				return oEcoleScolaire.ecoleNomCourt;
			case "ecoleId":
				return oEcoleScolaire.ecoleId;
			case "pageUri":
				return oEcoleScolaire.pageUri;
			default:
				return super.obtenirCluster(var);
		}
	}

	///////////////
	// attribuer //
	///////////////

	@Override public boolean attribuerPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerEcoleScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEcoleScolaire(String var, Object val) {
		EcoleScolaire oEcoleScolaire = (EcoleScolaire)this;
		switch(var) {
			default:
				return super.attribuerCluster(var, val);
		}
	}

	/////////////
	// definir //
	/////////////

	@Override public boolean definirPourClasse(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirEcoleScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEcoleScolaire(String var, String val) {
		switch(var) {
			case "ecoleCle":
				setEcoleCle(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "enfantCles":
				addEnfantCles(val);
				if(!sauvegardesEcoleScolaire.contains(var))
					sauvegardesEcoleScolaire.add(var);
				return val;
			case "blocCles":
				addBlocCles(val);
				if(!sauvegardesEcoleScolaire.contains(var))
					sauvegardesEcoleScolaire.add(var);
				return val;
			case "groupeAgeCles":
				addGroupeAgeCles(val);
				if(!sauvegardesEcoleScolaire.contains(var))
					sauvegardesEcoleScolaire.add(var);
				return val;
			case "sessionCles":
				addSessionCles(val);
				if(!sauvegardesEcoleScolaire.contains(var))
					sauvegardesEcoleScolaire.add(var);
				return val;
			case "saisonCles":
				addSaisonCles(val);
				if(!sauvegardesEcoleScolaire.contains(var))
					sauvegardesEcoleScolaire.add(var);
				return val;
			case "anneeCles":
				addAnneeCles(val);
				if(!sauvegardesEcoleScolaire.contains(var))
					sauvegardesEcoleScolaire.add(var);
				return val;
			case "archive":
				setArchive(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "supprime":
				setSupprime(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "scolaireTri":
				setScolaireTri(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "ecoleTri":
				setEcoleTri(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "ecoleNom":
				setEcoleNom(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "ecoleNumeroTelephone":
				setEcoleNumeroTelephone(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "ecoleAdministrateurNom":
				setEcoleAdministrateurNom(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "ecoleAddresse":
				setEcoleAddresse(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "objetSuggerePoids":
				setObjetSuggerePoids(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "objetSuggere":
				setObjetSuggere(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "ecoleNomCourt":
				setEcoleNomCourt(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "ecoleId":
				setEcoleId(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			case "pageUri":
				setPageUri(val);
				sauvegardesEcoleScolaire.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesEcoleScolaire = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerEcoleScolaire(solrDocument);
	}
	public void peuplerEcoleScolaire(SolrDocument solrDocument) {
		EcoleScolaire oEcoleScolaire = (EcoleScolaire)this;
		sauvegardesEcoleScolaire = (List<String>)solrDocument.get("sauvegardesEcoleScolaire_stored_strings");
		if(sauvegardesEcoleScolaire != null) {

			if(sauvegardesEcoleScolaire.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oEcoleScolaire.setEcoleCle(ecoleCle);
			}

			if(sauvegardesEcoleScolaire.contains("enfantCles")) {
				List<Long> enfantCles = (List<Long>)solrDocument.get("enfantCles_stored_longs");
				if(enfantCles != null)
					oEcoleScolaire.enfantCles.addAll(enfantCles);
			}

			if(sauvegardesEcoleScolaire.contains("blocCles")) {
				List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
				if(blocCles != null)
					oEcoleScolaire.blocCles.addAll(blocCles);
			}

			if(sauvegardesEcoleScolaire.contains("groupeAgeCles")) {
				List<Long> groupeAgeCles = (List<Long>)solrDocument.get("groupeAgeCles_stored_longs");
				if(groupeAgeCles != null)
					oEcoleScolaire.groupeAgeCles.addAll(groupeAgeCles);
			}

			if(sauvegardesEcoleScolaire.contains("sessionCles")) {
				List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
				if(sessionCles != null)
					oEcoleScolaire.sessionCles.addAll(sessionCles);
			}

			if(sauvegardesEcoleScolaire.contains("saisonCles")) {
				List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
				if(saisonCles != null)
					oEcoleScolaire.saisonCles.addAll(saisonCles);
			}

			if(sauvegardesEcoleScolaire.contains("anneeCles")) {
				List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
				if(anneeCles != null)
					oEcoleScolaire.anneeCles.addAll(anneeCles);
			}

			if(sauvegardesEcoleScolaire.contains("archive")) {
				Boolean archive = (Boolean)solrDocument.get("archive_stored_boolean");
				if(archive != null)
					oEcoleScolaire.setArchive(archive);
			}

			if(sauvegardesEcoleScolaire.contains("supprime")) {
				Boolean supprime = (Boolean)solrDocument.get("supprime_stored_boolean");
				if(supprime != null)
					oEcoleScolaire.setSupprime(supprime);
			}

			if(sauvegardesEcoleScolaire.contains("scolaireTri")) {
				Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
				if(scolaireTri != null)
					oEcoleScolaire.setScolaireTri(scolaireTri);
			}

			if(sauvegardesEcoleScolaire.contains("ecoleTri")) {
				Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
				if(ecoleTri != null)
					oEcoleScolaire.setEcoleTri(ecoleTri);
			}

			if(sauvegardesEcoleScolaire.contains("ecoleNom")) {
				String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
				if(ecoleNom != null)
					oEcoleScolaire.setEcoleNom(ecoleNom);
			}

			if(sauvegardesEcoleScolaire.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oEcoleScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardesEcoleScolaire.contains("ecoleAdministrateurNom")) {
				String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
				if(ecoleAdministrateurNom != null)
					oEcoleScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);
			}

			if(sauvegardesEcoleScolaire.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oEcoleScolaire.setEcoleAddresse(ecoleAddresse);
			}

			if(sauvegardesEcoleScolaire.contains("ecoleNomCourt")) {
				String ecoleNomCourt = (String)solrDocument.get("ecoleNomCourt_stored_string");
				if(ecoleNomCourt != null)
					oEcoleScolaire.setEcoleNomCourt(ecoleNomCourt);
			}

			if(sauvegardesEcoleScolaire.contains("ecoleId")) {
				String ecoleId = (String)solrDocument.get("ecoleId_stored_string");
				if(ecoleId != null)
					oEcoleScolaire.setEcoleId(ecoleId);
			}

			if(sauvegardesEcoleScolaire.contains("pageUri")) {
				String pageUri = (String)solrDocument.get("pageUri_stored_string");
				if(pageUri != null)
					oEcoleScolaire.setPageUri(pageUri);
			}
		}

		super.peuplerCluster(solrDocument);
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerEcoleScolaire(solrDocument);
	}
	public void stockerEcoleScolaire(SolrDocument solrDocument) {
		EcoleScolaire oEcoleScolaire = (EcoleScolaire)this;

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oEcoleScolaire.setEcoleCle(ecoleCle);

		List<Long> enfantCles = (List<Long>)solrDocument.get("enfantCles_stored_longs");
		if(enfantCles != null)
			oEcoleScolaire.enfantCles.addAll(enfantCles);

		List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
		if(blocCles != null)
			oEcoleScolaire.blocCles.addAll(blocCles);

		List<Long> groupeAgeCles = (List<Long>)solrDocument.get("groupeAgeCles_stored_longs");
		if(groupeAgeCles != null)
			oEcoleScolaire.groupeAgeCles.addAll(groupeAgeCles);

		List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
		if(sessionCles != null)
			oEcoleScolaire.sessionCles.addAll(sessionCles);

		List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
		if(saisonCles != null)
			oEcoleScolaire.saisonCles.addAll(saisonCles);

		List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
		if(anneeCles != null)
			oEcoleScolaire.anneeCles.addAll(anneeCles);

		Boolean archive = (Boolean)solrDocument.get("archive_stored_boolean");
		if(archive != null)
			oEcoleScolaire.setArchive(archive);

		Boolean supprime = (Boolean)solrDocument.get("supprime_stored_boolean");
		if(supprime != null)
			oEcoleScolaire.setSupprime(supprime);

		Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
		if(scolaireTri != null)
			oEcoleScolaire.setScolaireTri(scolaireTri);

		Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
		if(ecoleTri != null)
			oEcoleScolaire.setEcoleTri(ecoleTri);

		String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
		if(ecoleNom != null)
			oEcoleScolaire.setEcoleNom(ecoleNom);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oEcoleScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
		if(ecoleAdministrateurNom != null)
			oEcoleScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);

		String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
		if(ecoleAddresse != null)
			oEcoleScolaire.setEcoleAddresse(ecoleAddresse);

		String ecoleNomCourt = (String)solrDocument.get("ecoleNomCourt_stored_string");
		if(ecoleNomCourt != null)
			oEcoleScolaire.setEcoleNomCourt(ecoleNomCourt);

		String ecoleId = (String)solrDocument.get("ecoleId_stored_string");
		if(ecoleId != null)
			oEcoleScolaire.setEcoleId(ecoleId);

		String pageUri = (String)solrDocument.get("pageUri_stored_string");
		if(pageUri != null)
			oEcoleScolaire.setPageUri(pageUri);

		super.stockerCluster(solrDocument);
	}

	//////////////
	// htmlBody //
	//////////////

	public void htmlBody() {
		htmlBodyEcoleScolaire();
	}

	public void htmlBodyEcoleScolaire() {
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), ecoleCle, enfantCles, blocCles, groupeAgeCles, sessionCles, saisonCles, anneeCles, archive, supprime, scolaireTri, ecoleTri, ecoleNom, ecoleNumeroTelephone, ecoleAdministrateurNom, ecoleAddresse, objetSuggerePoids, objetSuggere, ecoleNomCourt, ecoleId, pageUri);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof EcoleScolaire))
			return false;
		EcoleScolaire that = (EcoleScolaire)o;
		return super.equals(o)
				&& Objects.equals( ecoleCle, that.ecoleCle )
				&& Objects.equals( enfantCles, that.enfantCles )
				&& Objects.equals( blocCles, that.blocCles )
				&& Objects.equals( groupeAgeCles, that.groupeAgeCles )
				&& Objects.equals( sessionCles, that.sessionCles )
				&& Objects.equals( saisonCles, that.saisonCles )
				&& Objects.equals( anneeCles, that.anneeCles )
				&& Objects.equals( archive, that.archive )
				&& Objects.equals( supprime, that.supprime )
				&& Objects.equals( scolaireTri, that.scolaireTri )
				&& Objects.equals( ecoleTri, that.ecoleTri )
				&& Objects.equals( ecoleNom, that.ecoleNom )
				&& Objects.equals( ecoleNumeroTelephone, that.ecoleNumeroTelephone )
				&& Objects.equals( ecoleAdministrateurNom, that.ecoleAdministrateurNom )
				&& Objects.equals( ecoleAddresse, that.ecoleAddresse )
				&& Objects.equals( objetSuggerePoids, that.objetSuggerePoids )
				&& Objects.equals( objetSuggere, that.objetSuggere )
				&& Objects.equals( ecoleNomCourt, that.ecoleNomCourt )
				&& Objects.equals( ecoleId, that.ecoleId )
				&& Objects.equals( pageUri, that.pageUri );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EcoleScolaire {");
		sb.append( "ecoleCle: " ).append(ecoleCle);
		sb.append( ", enfantCles: " ).append(enfantCles);
		sb.append( ", blocCles: " ).append(blocCles);
		sb.append( ", groupeAgeCles: " ).append(groupeAgeCles);
		sb.append( ", sessionCles: " ).append(sessionCles);
		sb.append( ", saisonCles: " ).append(saisonCles);
		sb.append( ", anneeCles: " ).append(anneeCles);
		sb.append( ", archive: " ).append(archive);
		sb.append( ", supprime: " ).append(supprime);
		sb.append( ", scolaireTri: " ).append(scolaireTri);
		sb.append( ", ecoleTri: " ).append(ecoleTri);
		sb.append( ", ecoleNom: \"" ).append(ecoleNom).append( "\"" );
		sb.append( ", ecoleNumeroTelephone: \"" ).append(ecoleNumeroTelephone).append( "\"" );
		sb.append( ", ecoleAdministrateurNom: \"" ).append(ecoleAdministrateurNom).append( "\"" );
		sb.append( ", ecoleAddresse: \"" ).append(ecoleAddresse).append( "\"" );
		sb.append( ", objetSuggerePoids: " ).append(objetSuggerePoids);
		sb.append( ", objetSuggere: \"" ).append(objetSuggere).append( "\"" );
		sb.append( ", ecoleNomCourt: \"" ).append(ecoleNomCourt).append( "\"" );
		sb.append( ", ecoleId: \"" ).append(ecoleId).append( "\"" );
		sb.append( ", pageUri: \"" ).append(pageUri).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
