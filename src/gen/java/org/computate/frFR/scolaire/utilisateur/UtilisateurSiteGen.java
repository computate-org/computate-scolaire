package org.computate.frFR.scolaire.utilisateur;

import org.computate.frFR.scolaire.cluster.Cluster;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class UtilisateurSiteGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurSite.class);

	public static final String UtilisateurSite_UnNom = "un utilisateur";
	public static final String UtilisateurSite_Ce = "ce ";
	public static final String UtilisateurSite_CeNom = "cet utilisateur";
	public static final String UtilisateurSite_Un = "un ";
	public static final String UtilisateurSite_LeNom = "l'utilisateur";
	public static final String UtilisateurSite_NomSingulier = "utilisateur";
	public static final String UtilisateurSite_NomPluriel = "utilisateurs";
	public static final String UtilisateurSite_NomActuel = "utilisateur actuel";
	public static final String UtilisateurSite_TousNom = "tous les utilisateurs";
	public static final String UtilisateurSite_LesNoms = "les utilisateurs";
	public static final String UtilisateurSite_AucunNomTrouve = "aucun utilisateur trouvé";
	public static final String UtilisateurSite_NomVar = "utilisateur";
	public static final String UtilisateurSite_DeNom = "d'utilisateur";
	public static final String UtilisateurSite_Couleur = "deep-purple";
	public static final String UtilisateurSite_IconeGroupe = "regular";
	public static final String UtilisateurSite_IconeNom = "user";

	///////////////////
	// utilisateurId //
	///////////////////

	/**	L'entité « utilisateurId »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurId;
	public Couverture<String> utilisateurIdCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurId").o(utilisateurId);

	/**	<br/>L'entité « utilisateurId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurId">Trouver l'entité utilisateurId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurId(Couverture<String> c);

	public String getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(String utilisateurId) {
		this.utilisateurId = utilisateurId;
		this.utilisateurIdCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurIdInit() {
		if(!utilisateurIdCouverture.dejaInitialise) {
			_utilisateurId(utilisateurIdCouverture);
			if(utilisateurId == null)
				setUtilisateurId(utilisateurIdCouverture.o);
		}
		utilisateurIdCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurId() {
		return utilisateurId;
	}

	public String strUtilisateurId() {
		return utilisateurId == null ? "" : utilisateurId;
	}

	public String nomAffichageUtilisateurId() {
		return "utilisateur ID";
	}

	public String htmTooltipUtilisateurId() {
		return null;
	}

	public String htmUtilisateurId() {
		return utilisateurId == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurId());
	}

	public void htmUtilisateurId(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "UtilisateurId\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "UtilisateurId() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUtilisateurId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUtilisateurId()), "</span>");
				r.s("			<input");
							r.s(" name=\"utilisateurId\"");
							r.s(" value=\"", htmUtilisateurId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUtilisateurId());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// calculInrPks //
	//////////////////

	/**	L'entité « calculInrPks »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> calculInrPks = new java.util.ArrayList<java.lang.Long>();
	public Couverture<List<Long>> calculInrPksCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("calculInrPks").o(calculInrPks);

	/**	<br/>L'entité « calculInrPks »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:calculInrPks">Trouver l'entité calculInrPks dans Solr</a>
	 * <br/>
	 * @param calculInrPks est l'entité déjà construit. 
	 **/
	protected abstract void _calculInrPks(List<Long> l);

	public List<Long> getCalculInrPks() {
		return calculInrPks;
	}

	public void setCalculInrPks(List<Long> calculInrPks) {
		this.calculInrPks = calculInrPks;
		this.calculInrPksCouverture.dejaInitialise = true;
	}
	public UtilisateurSite addCalculInrPks(Long...objets) {
		for(Long o : objets) {
			addCalculInrPks(o);
		}
		return (UtilisateurSite)this;
	}
	public UtilisateurSite addCalculInrPks(Long o) {
		if(o != null && !calculInrPks.contains(o))
			this.calculInrPks.add(o);
		return (UtilisateurSite)this;
	}
	public UtilisateurSite setCalculInrPks(JsonArray objets) {
		calculInrPks.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addCalculInrPks(o);
		}
		return (UtilisateurSite)this;
	}
	public UtilisateurSite addCalculInrPks(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addCalculInrPks(p);
		}
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite calculInrPksInit() {
		if(!calculInrPksCouverture.dejaInitialise) {
			_calculInrPks(calculInrPks);
		}
		calculInrPksCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public List<Long> solrCalculInrPks() {
		return calculInrPks;
	}

	public String strCalculInrPks() {
		return calculInrPks == null ? "" : calculInrPks.toString();
	}

	public String nomAffichageCalculInrPks() {
		return null;
	}

	public String htmTooltipCalculInrPks() {
		return null;
	}

	public String htmCalculInrPks() {
		return calculInrPks == null ? "" : StringEscapeUtils.escapeHtml4(strCalculInrPks());
	}

	public void htmCalculInrPks(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "CalculInrPks\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "CalculInrPks() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setCalculInrPks\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageCalculInrPks()), "</span>");
				r.s("			<input");
							r.s(" name=\"calculInrPks\"");
							r.s(" value=\"", htmCalculInrPks(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmCalculInrPks());
			}
			r.l("</div>");
		}
	}

	////////////////////
	// utilisateurNom //
	////////////////////

	/**	L'entité « utilisateurNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurNom;
	public Couverture<String> utilisateurNomCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNom").o(utilisateurNom);

	/**	<br/>L'entité « utilisateurNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNom">Trouver l'entité utilisateurNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNom(Couverture<String> c);

	public String getUtilisateurNom() {
		return utilisateurNom;
	}

	public void setUtilisateurNom(String utilisateurNom) {
		this.utilisateurNom = utilisateurNom;
		this.utilisateurNomCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurNomInit() {
		if(!utilisateurNomCouverture.dejaInitialise) {
			_utilisateurNom(utilisateurNomCouverture);
			if(utilisateurNom == null)
				setUtilisateurNom(utilisateurNomCouverture.o);
		}
		utilisateurNomCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurNom() {
		return utilisateurNom;
	}

	public String strUtilisateurNom() {
		return utilisateurNom == null ? "" : utilisateurNom;
	}

	public String nomAffichageUtilisateurNom() {
		return null;
	}

	public String htmTooltipUtilisateurNom() {
		return null;
	}

	public String htmUtilisateurNom() {
		return utilisateurNom == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurNom());
	}

	public void htmUtilisateurNom(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "UtilisateurNom\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "UtilisateurNom() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUtilisateurNom\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUtilisateurNom()), "</span>");
				r.s("			<input");
							r.s(" name=\"utilisateurNom\"");
							r.s(" value=\"", htmUtilisateurNom(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUtilisateurNom());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// utilisateurMail //
	/////////////////////

	/**	L'entité « utilisateurMail »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurMail;
	public Couverture<String> utilisateurMailCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurMail").o(utilisateurMail);

	/**	<br/>L'entité « utilisateurMail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurMail">Trouver l'entité utilisateurMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurMail(Couverture<String> c);

	public String getUtilisateurMail() {
		return utilisateurMail;
	}

	public void setUtilisateurMail(String utilisateurMail) {
		this.utilisateurMail = utilisateurMail;
		this.utilisateurMailCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurMailInit() {
		if(!utilisateurMailCouverture.dejaInitialise) {
			_utilisateurMail(utilisateurMailCouverture);
			if(utilisateurMail == null)
				setUtilisateurMail(utilisateurMailCouverture.o);
		}
		utilisateurMailCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurMail() {
		return utilisateurMail;
	}

	public String strUtilisateurMail() {
		return utilisateurMail == null ? "" : utilisateurMail;
	}

	public String nomAffichageUtilisateurMail() {
		return null;
	}

	public String htmTooltipUtilisateurMail() {
		return null;
	}

	public String htmUtilisateurMail() {
		return utilisateurMail == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurMail());
	}

	public void htmUtilisateurMail(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "UtilisateurMail\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "UtilisateurMail() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUtilisateurMail\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUtilisateurMail()), "</span>");
				r.s("			<input");
							r.s(" name=\"utilisateurMail\"");
							r.s(" value=\"", htmUtilisateurMail(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUtilisateurMail());
			}
			r.l("</div>");
		}
	}

	///////////////////////
	// utilisateurPrenom //
	///////////////////////

	/**	L'entité « utilisateurPrenom »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurPrenom;
	public Couverture<String> utilisateurPrenomCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurPrenom").o(utilisateurPrenom);

	/**	<br/>L'entité « utilisateurPrenom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurPrenom">Trouver l'entité utilisateurPrenom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurPrenom(Couverture<String> c);

	public String getUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	public void setUtilisateurPrenom(String utilisateurPrenom) {
		this.utilisateurPrenom = utilisateurPrenom;
		this.utilisateurPrenomCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurPrenomInit() {
		if(!utilisateurPrenomCouverture.dejaInitialise) {
			_utilisateurPrenom(utilisateurPrenomCouverture);
			if(utilisateurPrenom == null)
				setUtilisateurPrenom(utilisateurPrenomCouverture.o);
		}
		utilisateurPrenomCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	public String strUtilisateurPrenom() {
		return utilisateurPrenom == null ? "" : utilisateurPrenom;
	}

	public String nomAffichageUtilisateurPrenom() {
		return null;
	}

	public String htmTooltipUtilisateurPrenom() {
		return null;
	}

	public String htmUtilisateurPrenom() {
		return utilisateurPrenom == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurPrenom());
	}

	public void htmUtilisateurPrenom(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "UtilisateurPrenom\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "UtilisateurPrenom() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUtilisateurPrenom\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUtilisateurPrenom()), "</span>");
				r.s("			<input");
							r.s(" name=\"utilisateurPrenom\"");
							r.s(" value=\"", htmUtilisateurPrenom(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUtilisateurPrenom());
			}
			r.l("</div>");
		}
	}

	///////////////////////////
	// utilisateurNomFamille //
	///////////////////////////

	/**	L'entité « utilisateurNomFamille »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurNomFamille;
	public Couverture<String> utilisateurNomFamilleCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNomFamille").o(utilisateurNomFamille);

	/**	<br/>L'entité « utilisateurNomFamille »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNomFamille">Trouver l'entité utilisateurNomFamille dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNomFamille(Couverture<String> c);

	public String getUtilisateurNomFamille() {
		return utilisateurNomFamille;
	}

	public void setUtilisateurNomFamille(String utilisateurNomFamille) {
		this.utilisateurNomFamille = utilisateurNomFamille;
		this.utilisateurNomFamilleCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurNomFamilleInit() {
		if(!utilisateurNomFamilleCouverture.dejaInitialise) {
			_utilisateurNomFamille(utilisateurNomFamilleCouverture);
			if(utilisateurNomFamille == null)
				setUtilisateurNomFamille(utilisateurNomFamilleCouverture.o);
		}
		utilisateurNomFamilleCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurNomFamille() {
		return utilisateurNomFamille;
	}

	public String strUtilisateurNomFamille() {
		return utilisateurNomFamille == null ? "" : utilisateurNomFamille;
	}

	public String nomAffichageUtilisateurNomFamille() {
		return null;
	}

	public String htmTooltipUtilisateurNomFamille() {
		return null;
	}

	public String htmUtilisateurNomFamille() {
		return utilisateurNomFamille == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurNomFamille());
	}

	public void htmUtilisateurNomFamille(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "UtilisateurNomFamille\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "UtilisateurNomFamille() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUtilisateurNomFamille\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUtilisateurNomFamille()), "</span>");
				r.s("			<input");
							r.s(" name=\"utilisateurNomFamille\"");
							r.s(" value=\"", htmUtilisateurNomFamille(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUtilisateurNomFamille());
			}
			r.l("</div>");
		}
	}

	///////////////////////////
	// utilisateurNomComplet //
	///////////////////////////

	/**	L'entité « utilisateurNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurNomComplet;
	public Couverture<String> utilisateurNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNomComplet").o(utilisateurNomComplet);

	/**	<br/>L'entité « utilisateurNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNomComplet">Trouver l'entité utilisateurNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNomComplet(Couverture<String> c);

	public String getUtilisateurNomComplet() {
		return utilisateurNomComplet;
	}

	public void setUtilisateurNomComplet(String utilisateurNomComplet) {
		this.utilisateurNomComplet = utilisateurNomComplet;
		this.utilisateurNomCompletCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurNomCompletInit() {
		if(!utilisateurNomCompletCouverture.dejaInitialise) {
			_utilisateurNomComplet(utilisateurNomCompletCouverture);
			if(utilisateurNomComplet == null)
				setUtilisateurNomComplet(utilisateurNomCompletCouverture.o);
		}
		utilisateurNomCompletCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurNomComplet() {
		return utilisateurNomComplet;
	}

	public String strUtilisateurNomComplet() {
		return utilisateurNomComplet == null ? "" : utilisateurNomComplet;
	}

	public String nomAffichageUtilisateurNomComplet() {
		return null;
	}

	public String htmTooltipUtilisateurNomComplet() {
		return null;
	}

	public String htmUtilisateurNomComplet() {
		return utilisateurNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurNomComplet());
	}

	public void htmUtilisateurNomComplet(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "UtilisateurNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "UtilisateurNomComplet() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUtilisateurNomComplet\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUtilisateurNomComplet()), "</span>");
				r.s("			<input");
							r.s(" name=\"utilisateurNomComplet\"");
							r.s(" value=\"", htmUtilisateurNomComplet(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUtilisateurNomComplet());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// utilisateurSite //
	/////////////////////

	/**	L'entité « utilisateurSite »
	 *	 is defined as null before being initialized. 
	 */
	protected String utilisateurSite;
	public Couverture<String> utilisateurSiteCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurSite").o(utilisateurSite);

	/**	<br/>L'entité « utilisateurSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurSite">Trouver l'entité utilisateurSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurSite(Couverture<String> c);

	public String getUtilisateurSite() {
		return utilisateurSite;
	}

	public void setUtilisateurSite(String utilisateurSite) {
		this.utilisateurSite = utilisateurSite;
		this.utilisateurSiteCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurSiteInit() {
		if(!utilisateurSiteCouverture.dejaInitialise) {
			_utilisateurSite(utilisateurSiteCouverture);
			if(utilisateurSite == null)
				setUtilisateurSite(utilisateurSiteCouverture.o);
		}
		utilisateurSiteCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurSite() {
		return utilisateurSite;
	}

	public String strUtilisateurSite() {
		return utilisateurSite == null ? "" : utilisateurSite;
	}

	public String nomAffichageUtilisateurSite() {
		return null;
	}

	public String htmTooltipUtilisateurSite() {
		return null;
	}

	public String htmUtilisateurSite() {
		return utilisateurSite == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurSite());
	}

	public void htmUtilisateurSite(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "UtilisateurSite\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "UtilisateurSite() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUtilisateurSite\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUtilisateurSite()), "</span>");
				r.s("			<input");
							r.s(" name=\"utilisateurSite\"");
							r.s(" value=\"", htmUtilisateurSite(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUtilisateurSite());
			}
			r.l("</div>");
		}
	}

	//////////////////////////////////
	// utilisateurRecevoirCourriels //
	//////////////////////////////////

	/**	L'entité « utilisateurRecevoirCourriels »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean utilisateurRecevoirCourriels;
	public Couverture<Boolean> utilisateurRecevoirCourrielsCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("utilisateurRecevoirCourriels").o(utilisateurRecevoirCourriels);

	/**	<br/>L'entité « utilisateurRecevoirCourriels »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurRecevoirCourriels">Trouver l'entité utilisateurRecevoirCourriels dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurRecevoirCourriels(Couverture<Boolean> c);

	public Boolean getUtilisateurRecevoirCourriels() {
		return utilisateurRecevoirCourriels;
	}

	public void setUtilisateurRecevoirCourriels(Boolean utilisateurRecevoirCourriels) {
		this.utilisateurRecevoirCourriels = utilisateurRecevoirCourriels;
		this.utilisateurRecevoirCourrielsCouverture.dejaInitialise = true;
	}
	public UtilisateurSite setUtilisateurRecevoirCourriels(String o) {
		this.utilisateurRecevoirCourriels = Boolean.parseBoolean(o);
		this.utilisateurRecevoirCourrielsCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite utilisateurRecevoirCourrielsInit() {
		if(!utilisateurRecevoirCourrielsCouverture.dejaInitialise) {
			_utilisateurRecevoirCourriels(utilisateurRecevoirCourrielsCouverture);
			if(utilisateurRecevoirCourriels == null)
				setUtilisateurRecevoirCourriels(utilisateurRecevoirCourrielsCouverture.o);
		}
		utilisateurRecevoirCourrielsCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public Boolean solrUtilisateurRecevoirCourriels() {
		return utilisateurRecevoirCourriels;
	}

	public String strUtilisateurRecevoirCourriels() {
		return utilisateurRecevoirCourriels == null ? "" : utilisateurRecevoirCourriels.toString();
	}

	public String nomAffichageUtilisateurRecevoirCourriels() {
		return null;
	}

	public String htmTooltipUtilisateurRecevoirCourriels() {
		return null;
	}

	public String htmUtilisateurRecevoirCourriels() {
		return utilisateurRecevoirCourriels == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurRecevoirCourriels());
	}

	public void htmUtilisateurRecevoirCourriels(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "UtilisateurRecevoirCourriels\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "UtilisateurRecevoirCourriels() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setUtilisateurRecevoirCourriels\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageUtilisateurRecevoirCourriels()), "</span>");
				r.s("			<input");
							r.s(" name=\"utilisateurRecevoirCourriels\"");
							r.s(" value=\"", htmUtilisateurRecevoirCourriels(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmUtilisateurRecevoirCourriels());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// voirArchive //
	/////////////////

	/**	L'entité « voirArchive »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean voirArchive;
	public Couverture<Boolean> voirArchiveCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("voirArchive").o(voirArchive);

	/**	<br/>L'entité « voirArchive »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:voirArchive">Trouver l'entité voirArchive dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _voirArchive(Couverture<Boolean> c);

	public Boolean getVoirArchive() {
		return voirArchive;
	}

	public void setVoirArchive(Boolean voirArchive) {
		this.voirArchive = voirArchive;
		this.voirArchiveCouverture.dejaInitialise = true;
	}
	public UtilisateurSite setVoirArchive(String o) {
		this.voirArchive = Boolean.parseBoolean(o);
		this.voirArchiveCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite voirArchiveInit() {
		if(!voirArchiveCouverture.dejaInitialise) {
			_voirArchive(voirArchiveCouverture);
			if(voirArchive == null)
				setVoirArchive(voirArchiveCouverture.o);
		}
		voirArchiveCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public Boolean solrVoirArchive() {
		return voirArchive;
	}

	public String strVoirArchive() {
		return voirArchive == null ? "" : voirArchive.toString();
	}

	public String nomAffichageVoirArchive() {
		return "voir archiv\u00E9";
	}

	public String htmTooltipVoirArchive() {
		return null;
	}

	public String htmVoirArchive() {
		return voirArchive == null ? "" : StringEscapeUtils.escapeHtml4(strVoirArchive());
	}

	public void htmVoirArchive(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "VoirArchive\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "VoirArchive() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setVoirArchive\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageVoirArchive()), "</span>");
				r.s("			<input");
							r.s(" name=\"voirArchive\"");
							r.s(" value=\"", htmVoirArchive(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmVoirArchive());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// voirSupprime //
	//////////////////

	/**	L'entité « voirSupprime »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean voirSupprime;
	public Couverture<Boolean> voirSupprimeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("voirSupprime").o(voirSupprime);

	/**	<br/>L'entité « voirSupprime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:voirSupprime">Trouver l'entité voirSupprime dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _voirSupprime(Couverture<Boolean> c);

	public Boolean getVoirSupprime() {
		return voirSupprime;
	}

	public void setVoirSupprime(Boolean voirSupprime) {
		this.voirSupprime = voirSupprime;
		this.voirSupprimeCouverture.dejaInitialise = true;
	}
	public UtilisateurSite setVoirSupprime(String o) {
		this.voirSupprime = Boolean.parseBoolean(o);
		this.voirSupprimeCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite voirSupprimeInit() {
		if(!voirSupprimeCouverture.dejaInitialise) {
			_voirSupprime(voirSupprimeCouverture);
			if(voirSupprime == null)
				setVoirSupprime(voirSupprimeCouverture.o);
		}
		voirSupprimeCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public Boolean solrVoirSupprime() {
		return voirSupprime;
	}

	public String strVoirSupprime() {
		return voirSupprime == null ? "" : voirSupprime.toString();
	}

	public String nomAffichageVoirSupprime() {
		return "voir supprim\u00E9";
	}

	public String htmTooltipVoirSupprime() {
		return null;
	}

	public String htmVoirSupprime() {
		return voirSupprime == null ? "" : StringEscapeUtils.escapeHtml4(strVoirSupprime());
	}

	public void htmVoirSupprime(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "VoirSupprime\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "VoirSupprime() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setVoirSupprime\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageVoirSupprime()), "</span>");
				r.s("			<input");
							r.s(" name=\"voirSupprime\"");
							r.s(" value=\"", htmVoirSupprime(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmVoirSupprime());
			}
			r.l("</div>");
		}
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseUtilisateurSite = false;

	public UtilisateurSite initLoinUtilisateurSite(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseUtilisateurSite) {
			dejaInitialiseUtilisateurSite = true;
			initLoinUtilisateurSite();
		}
		return (UtilisateurSite)this;
	}

	public void initLoinUtilisateurSite() {
		super.initLoinCluster(requeteSite_);
		initUtilisateurSite();
	}

	public void initUtilisateurSite() {
		utilisateurIdInit();
		calculInrPksInit();
		utilisateurNomInit();
		utilisateurMailInit();
		utilisateurPrenomInit();
		utilisateurNomFamilleInit();
		utilisateurNomCompletInit();
		utilisateurSiteInit();
		utilisateurRecevoirCourrielsInit();
		voirArchiveInit();
		voirSupprimeInit();
	}

	@Override public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinUtilisateurSite(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteUtilisateurSite(RequeteSite requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteUtilisateurSite(requeteSite_);
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
			UtilisateurSite o = new UtilisateurSite();
			o.requeteSiteUtilisateurSite(requeteSite);
			o.initLoinUtilisateurSite(requeteSite);
			o.indexerUtilisateurSite();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() throws Exception {
		indexerUtilisateurSite();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) throws Exception {
		indexerUtilisateurSite(document);
	}

	public void indexerUtilisateurSite(SolrClient clientSolr) throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		indexerUtilisateurSite(document);
		clientSolr.add(document);
		clientSolr.commit();
	}

	public void indexerUtilisateurSite() throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		indexerUtilisateurSite(document);
		SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
		clientSolr.add(document);
		clientSolr.commit();
	}

	public void indexerUtilisateurSite(SolrInputDocument document) throws Exception {
		if(sauvegardesUtilisateurSite != null)
			document.addField("sauvegardesUtilisateurSite_stored_strings", sauvegardesUtilisateurSite);

		if(utilisateurId != null) {
			document.addField("utilisateurId_indexed_string", utilisateurId);
			document.addField("utilisateurId_stored_string", utilisateurId);
		}
		if(calculInrPks != null) {
			for(java.lang.Long o : calculInrPks) {
				document.addField("calculInrPks_indexed_longs", o);
			}
			for(java.lang.Long o : calculInrPks) {
				document.addField("calculInrPks_stored_longs", o);
			}
		}
		if(utilisateurNom != null) {
			document.addField("utilisateurNom_indexed_string", utilisateurNom);
			document.addField("utilisateurNom_stored_string", utilisateurNom);
		}
		if(utilisateurMail != null) {
			document.addField("utilisateurMail_indexed_string", utilisateurMail);
			document.addField("utilisateurMail_stored_string", utilisateurMail);
		}
		if(utilisateurPrenom != null) {
			document.addField("utilisateurPrenom_indexed_string", utilisateurPrenom);
			document.addField("utilisateurPrenom_stored_string", utilisateurPrenom);
		}
		if(utilisateurNomFamille != null) {
			document.addField("utilisateurNomFamille_indexed_string", utilisateurNomFamille);
			document.addField("utilisateurNomFamille_stored_string", utilisateurNomFamille);
		}
		if(utilisateurNomComplet != null) {
			document.addField("utilisateurNomComplet_indexed_string", utilisateurNomComplet);
			document.addField("utilisateurNomComplet_stored_string", utilisateurNomComplet);
		}
		if(utilisateurSite != null) {
			document.addField("utilisateurSite_indexed_string", utilisateurSite);
			document.addField("utilisateurSite_stored_string", utilisateurSite);
		}
		if(utilisateurRecevoirCourriels != null) {
			document.addField("utilisateurRecevoirCourriels_indexed_boolean", utilisateurRecevoirCourriels);
			document.addField("utilisateurRecevoirCourriels_stored_boolean", utilisateurRecevoirCourriels);
		}
		if(voirArchive != null) {
			document.addField("voirArchive_indexed_boolean", voirArchive);
			document.addField("voirArchive_stored_boolean", voirArchive);
		}
		if(voirSupprime != null) {
			document.addField("voirSupprime_indexed_boolean", voirSupprime);
			document.addField("voirSupprime_stored_boolean", voirSupprime);
		}
		super.indexerCluster(document);

	}

	public void desindexerUtilisateurSite() throws Exception {
		RequeteSite requeteSite = new RequeteSite();
		requeteSite.initLoinRequeteSite();
		SiteContexte siteContexte = new SiteContexte();
		siteContexte.initLoinSiteContexte();
		siteContexte.setRequeteSite_(requeteSite);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		initLoinUtilisateurSite(siteContexte.getRequeteSite_());
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
				o = obtenirUtilisateurSite(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirUtilisateurSite(String var) throws Exception {
		UtilisateurSite oUtilisateurSite = (UtilisateurSite)this;
		switch(var) {
			case "utilisateurId":
				return oUtilisateurSite.utilisateurId;
			case "calculInrPks":
				return oUtilisateurSite.calculInrPks;
			case "utilisateurNom":
				return oUtilisateurSite.utilisateurNom;
			case "utilisateurMail":
				return oUtilisateurSite.utilisateurMail;
			case "utilisateurPrenom":
				return oUtilisateurSite.utilisateurPrenom;
			case "utilisateurNomFamille":
				return oUtilisateurSite.utilisateurNomFamille;
			case "utilisateurNomComplet":
				return oUtilisateurSite.utilisateurNomComplet;
			case "utilisateurSite":
				return oUtilisateurSite.utilisateurSite;
			case "utilisateurRecevoirCourriels":
				return oUtilisateurSite.utilisateurRecevoirCourriels;
			case "voirArchive":
				return oUtilisateurSite.voirArchive;
			case "voirSupprime":
				return oUtilisateurSite.voirSupprime;
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
				o = attribuerUtilisateurSite(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerUtilisateurSite(String var, Object val) {
		UtilisateurSite oUtilisateurSite = (UtilisateurSite)this;
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
					o = definirUtilisateurSite(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirUtilisateurSite(String var, String val) {
		switch(var) {
			case "utilisateurId":
				setUtilisateurId(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "calculInrPks":
				addCalculInrPks(val);
				if(!sauvegardesUtilisateurSite.contains(var))
					sauvegardesUtilisateurSite.add(var);
				return val;
			case "utilisateurNom":
				setUtilisateurNom(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "utilisateurMail":
				setUtilisateurMail(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "utilisateurPrenom":
				setUtilisateurPrenom(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "utilisateurNomFamille":
				setUtilisateurNomFamille(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "utilisateurNomComplet":
				setUtilisateurNomComplet(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "utilisateurSite":
				setUtilisateurSite(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "utilisateurRecevoirCourriels":
				setUtilisateurRecevoirCourriels(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "voirArchive":
				setVoirArchive(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "voirSupprime":
				setVoirSupprime(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesUtilisateurSite = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerUtilisateurSite(solrDocument);
	}
	public void peuplerUtilisateurSite(SolrDocument solrDocument) {
		UtilisateurSite oUtilisateurSite = (UtilisateurSite)this;
		sauvegardesUtilisateurSite = (List<String>)solrDocument.get("sauvegardesUtilisateurSite_stored_strings");
		if(sauvegardesUtilisateurSite != null) {

			if(sauvegardesUtilisateurSite.contains("utilisateurId")) {
				String utilisateurId = (String)solrDocument.get("utilisateurId_stored_string");
				if(utilisateurId != null)
					oUtilisateurSite.setUtilisateurId(utilisateurId);
			}

			if(sauvegardesUtilisateurSite.contains("calculInrPks")) {
				List<Long> calculInrPks = (List<Long>)solrDocument.get("calculInrPks_stored_longs");
				if(calculInrPks != null)
					oUtilisateurSite.calculInrPks.addAll(calculInrPks);
			}

			if(sauvegardesUtilisateurSite.contains("utilisateurNom")) {
				String utilisateurNom = (String)solrDocument.get("utilisateurNom_stored_string");
				if(utilisateurNom != null)
					oUtilisateurSite.setUtilisateurNom(utilisateurNom);
			}

			if(sauvegardesUtilisateurSite.contains("utilisateurMail")) {
				String utilisateurMail = (String)solrDocument.get("utilisateurMail_stored_string");
				if(utilisateurMail != null)
					oUtilisateurSite.setUtilisateurMail(utilisateurMail);
			}

			if(sauvegardesUtilisateurSite.contains("utilisateurPrenom")) {
				String utilisateurPrenom = (String)solrDocument.get("utilisateurPrenom_stored_string");
				if(utilisateurPrenom != null)
					oUtilisateurSite.setUtilisateurPrenom(utilisateurPrenom);
			}

			if(sauvegardesUtilisateurSite.contains("utilisateurNomFamille")) {
				String utilisateurNomFamille = (String)solrDocument.get("utilisateurNomFamille_stored_string");
				if(utilisateurNomFamille != null)
					oUtilisateurSite.setUtilisateurNomFamille(utilisateurNomFamille);
			}

			if(sauvegardesUtilisateurSite.contains("utilisateurNomComplet")) {
				String utilisateurNomComplet = (String)solrDocument.get("utilisateurNomComplet_stored_string");
				if(utilisateurNomComplet != null)
					oUtilisateurSite.setUtilisateurNomComplet(utilisateurNomComplet);
			}

			if(sauvegardesUtilisateurSite.contains("utilisateurSite")) {
				String utilisateurSite = (String)solrDocument.get("utilisateurSite_stored_string");
				if(utilisateurSite != null)
					oUtilisateurSite.setUtilisateurSite(utilisateurSite);
			}

			if(sauvegardesUtilisateurSite.contains("utilisateurRecevoirCourriels")) {
				Boolean utilisateurRecevoirCourriels = (Boolean)solrDocument.get("utilisateurRecevoirCourriels_stored_boolean");
				if(utilisateurRecevoirCourriels != null)
					oUtilisateurSite.setUtilisateurRecevoirCourriels(utilisateurRecevoirCourriels);
			}

			if(sauvegardesUtilisateurSite.contains("voirArchive")) {
				Boolean voirArchive = (Boolean)solrDocument.get("voirArchive_stored_boolean");
				if(voirArchive != null)
					oUtilisateurSite.setVoirArchive(voirArchive);
			}

			if(sauvegardesUtilisateurSite.contains("voirSupprime")) {
				Boolean voirSupprime = (Boolean)solrDocument.get("voirSupprime_stored_boolean");
				if(voirSupprime != null)
					oUtilisateurSite.setVoirSupprime(voirSupprime);
			}
		}

		super.peuplerCluster(solrDocument);
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerUtilisateurSite(solrDocument);
	}
	public void stockerUtilisateurSite(SolrDocument solrDocument) {
		UtilisateurSite oUtilisateurSite = (UtilisateurSite)this;

		String utilisateurId = (String)solrDocument.get("utilisateurId_stored_string");
		if(utilisateurId != null)
			oUtilisateurSite.setUtilisateurId(utilisateurId);

		List<Long> calculInrPks = (List<Long>)solrDocument.get("calculInrPks_stored_longs");
		if(calculInrPks != null)
			oUtilisateurSite.calculInrPks.addAll(calculInrPks);

		String utilisateurNom = (String)solrDocument.get("utilisateurNom_stored_string");
		if(utilisateurNom != null)
			oUtilisateurSite.setUtilisateurNom(utilisateurNom);

		String utilisateurMail = (String)solrDocument.get("utilisateurMail_stored_string");
		if(utilisateurMail != null)
			oUtilisateurSite.setUtilisateurMail(utilisateurMail);

		String utilisateurPrenom = (String)solrDocument.get("utilisateurPrenom_stored_string");
		if(utilisateurPrenom != null)
			oUtilisateurSite.setUtilisateurPrenom(utilisateurPrenom);

		String utilisateurNomFamille = (String)solrDocument.get("utilisateurNomFamille_stored_string");
		if(utilisateurNomFamille != null)
			oUtilisateurSite.setUtilisateurNomFamille(utilisateurNomFamille);

		String utilisateurNomComplet = (String)solrDocument.get("utilisateurNomComplet_stored_string");
		if(utilisateurNomComplet != null)
			oUtilisateurSite.setUtilisateurNomComplet(utilisateurNomComplet);

		String utilisateurSite = (String)solrDocument.get("utilisateurSite_stored_string");
		if(utilisateurSite != null)
			oUtilisateurSite.setUtilisateurSite(utilisateurSite);

		Boolean utilisateurRecevoirCourriels = (Boolean)solrDocument.get("utilisateurRecevoirCourriels_stored_boolean");
		if(utilisateurRecevoirCourriels != null)
			oUtilisateurSite.setUtilisateurRecevoirCourriels(utilisateurRecevoirCourriels);

		Boolean voirArchive = (Boolean)solrDocument.get("voirArchive_stored_boolean");
		if(voirArchive != null)
			oUtilisateurSite.setVoirArchive(voirArchive);

		Boolean voirSupprime = (Boolean)solrDocument.get("voirSupprime_stored_boolean");
		if(voirSupprime != null)
			oUtilisateurSite.setVoirSupprime(voirSupprime);

		super.stockerCluster(solrDocument);
	}

	//////////////
	// htmlBody //
	//////////////

	public void htmlBody() {
		htmlBodyUtilisateurSite();
	}

	public void htmlBodyUtilisateurSite() {
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), utilisateurId, calculInrPks, utilisateurNom, utilisateurMail, utilisateurPrenom, utilisateurNomFamille, utilisateurNomComplet, utilisateurSite, utilisateurRecevoirCourriels, voirArchive, voirSupprime);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof UtilisateurSite))
			return false;
		UtilisateurSite that = (UtilisateurSite)o;
		return super.equals(o)
				&& Objects.equals( utilisateurId, that.utilisateurId )
				&& Objects.equals( calculInrPks, that.calculInrPks )
				&& Objects.equals( utilisateurNom, that.utilisateurNom )
				&& Objects.equals( utilisateurMail, that.utilisateurMail )
				&& Objects.equals( utilisateurPrenom, that.utilisateurPrenom )
				&& Objects.equals( utilisateurNomFamille, that.utilisateurNomFamille )
				&& Objects.equals( utilisateurNomComplet, that.utilisateurNomComplet )
				&& Objects.equals( utilisateurSite, that.utilisateurSite )
				&& Objects.equals( utilisateurRecevoirCourriels, that.utilisateurRecevoirCourriels )
				&& Objects.equals( voirArchive, that.voirArchive )
				&& Objects.equals( voirSupprime, that.voirSupprime );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("UtilisateurSite {");
		sb.append( "utilisateurId: \"" ).append(utilisateurId).append( "\"" );
		sb.append( ", calculInrPks: " ).append(calculInrPks);
		sb.append( ", utilisateurNom: \"" ).append(utilisateurNom).append( "\"" );
		sb.append( ", utilisateurMail: \"" ).append(utilisateurMail).append( "\"" );
		sb.append( ", utilisateurPrenom: \"" ).append(utilisateurPrenom).append( "\"" );
		sb.append( ", utilisateurNomFamille: \"" ).append(utilisateurNomFamille).append( "\"" );
		sb.append( ", utilisateurNomComplet: \"" ).append(utilisateurNomComplet).append( "\"" );
		sb.append( ", utilisateurSite: \"" ).append(utilisateurSite).append( "\"" );
		sb.append( ", utilisateurRecevoirCourriels: " ).append(utilisateurRecevoirCourriels);
		sb.append( ", voirArchive: " ).append(voirArchive);
		sb.append( ", voirSupprime: " ).append(voirSupprime);
		sb.append(" }");
		return sb.toString();
	}
}
