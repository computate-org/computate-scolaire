package org.computate.frFR.scolaire.utilisateur;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import org.computate.frFR.scolaire.requete.RequeteSite;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class UtilisateurSiteGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurSite.class);

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
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected RequeteSite requeteSite_;
	public Couverture<RequeteSite> requeteSite_Couverture = new Couverture<RequeteSite>().p(this).c(RequeteSite.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteSite_(Couverture<RequeteSite> c);

	public RequeteSite getRequeteSite_() {
		return requeteSite_;
	}

	public void setRequeteSite_(RequeteSite requeteSite_) {
		this.requeteSite_ = requeteSite_;
		this.requeteSite_Couverture.dejaInitialise = true;
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
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
		return null;
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
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
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
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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

	////////////////////
	// modeleSupprime //
	////////////////////

	/**	L'entité « modeleSupprime »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean modeleSupprime;
	public Couverture<Boolean> modeleSupprimeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("modeleSupprime").o(modeleSupprime);

	/**	<br/>L'entité « modeleSupprime »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:modeleSupprime">Trouver l'entité modeleSupprime dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _modeleSupprime(Couverture<Boolean> c);

	public Boolean getModeleSupprime() {
		return modeleSupprime;
	}

	public void setModeleSupprime(Boolean modeleSupprime) {
		this.modeleSupprime = modeleSupprime;
		this.modeleSupprimeCouverture.dejaInitialise = true;
	}
	public UtilisateurSite setModeleSupprime(String o) {
		if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))
			this.modeleSupprime = Boolean.parseBoolean(o);
		this.modeleSupprimeCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite modeleSupprimeInit() {
		if(!modeleSupprimeCouverture.dejaInitialise) {
			_modeleSupprime(modeleSupprimeCouverture);
			if(modeleSupprime == null)
				setModeleSupprime(modeleSupprimeCouverture.o);
		}
		modeleSupprimeCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public Boolean solrModeleSupprime() {
		return modeleSupprime;
	}

	public String strModeleSupprime() {
		return modeleSupprime == null ? "" : modeleSupprime.toString();
	}

	public String nomAffichageModeleSupprime() {
		return null;
	}

	public String htmTooltipModeleSupprime() {
		return null;
	}

	public String htmModeleSupprime() {
		return modeleSupprime == null ? "" : StringEscapeUtils.escapeHtml4(strModeleSupprime());
	}

	public void htmModeleSupprime(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "ModeleSupprime\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "ModeleSupprime() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setModeleSupprime\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageModeleSupprime()), "</span>");
				r.s("			<input");
							r.s(" name=\"modeleSupprime\"");
							r.s(" value=\"", htmModeleSupprime(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmModeleSupprime());
			}
			r.l("</div>");
		}
	}

	////////////////
	// modeleCree //
	////////////////

	/**	L'entité « modeleCree »
	 *	 is defined as null before being initialized. 
	 */
	protected ZonedDateTime modeleCree;
	public Couverture<ZonedDateTime> modeleCreeCouverture = new Couverture<ZonedDateTime>().p(this).c(ZonedDateTime.class).var("modeleCree").o(modeleCree);

	/**	<br/>L'entité « modeleCree »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:modeleCree">Trouver l'entité modeleCree dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _modeleCree(Couverture<ZonedDateTime> c);

	public ZonedDateTime getModeleCree() {
		return modeleCree;
	}

	public void setModeleCree(ZonedDateTime modeleCree) {
		this.modeleCree = modeleCree;
		this.modeleCreeCouverture.dejaInitialise = true;
	}
	public UtilisateurSite setModeleCree(Instant o) {
		this.modeleCree = ZonedDateTime.from(o);
		this.modeleCreeCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public UtilisateurSite setModeleCree(String o) {
		this.modeleCree = ZonedDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		this.modeleCreeCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	public UtilisateurSite setModeleCree(Date o) {
		this.modeleCree = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());
		this.modeleCreeCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite modeleCreeInit() {
		if(!modeleCreeCouverture.dejaInitialise) {
			_modeleCree(modeleCreeCouverture);
			if(modeleCree == null)
				setModeleCree(modeleCreeCouverture.o);
		}
		modeleCreeCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public Date solrModeleCree() {
		return modeleCree == null ? null : Date.from(modeleCree.toInstant());
	}

	public String strModeleCree() {
		return modeleCree == null ? "" : modeleCree.toString();
	}

	public String nomAffichageModeleCree() {
		return null;
	}

	public String htmTooltipModeleCree() {
		return null;
	}

	public String htmModeleCree() {
		return modeleCree == null ? "" : StringEscapeUtils.escapeHtml4(strModeleCree());
	}

	public void htmModeleCree(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "ModeleCree\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "ModeleCree() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setModeleCree\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageModeleCree()), "</span>");
				r.s("			<input");
							r.s(" name=\"modeleCree\"");
							r.s(" value=\"", htmModeleCree(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmModeleCree());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// modeleModifie //
	///////////////////

	/**	L'entité « modeleModifie »
	 *	 is defined as null before being initialized. 
	 */
	protected ZonedDateTime modeleModifie;
	public Couverture<ZonedDateTime> modeleModifieCouverture = new Couverture<ZonedDateTime>().p(this).c(ZonedDateTime.class).var("modeleModifie").o(modeleModifie);

	/**	<br/>L'entité « modeleModifie »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:modeleModifie">Trouver l'entité modeleModifie dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _modeleModifie(Couverture<ZonedDateTime> c);

	public ZonedDateTime getModeleModifie() {
		return modeleModifie;
	}

	public void setModeleModifie(ZonedDateTime modeleModifie) {
		this.modeleModifie = modeleModifie;
		this.modeleModifieCouverture.dejaInitialise = true;
	}
	public UtilisateurSite setModeleModifie(Instant o) {
		this.modeleModifie = ZonedDateTime.from(o);
		this.modeleModifieCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public UtilisateurSite setModeleModifie(String o) {
		this.modeleModifie = ZonedDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		this.modeleModifieCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	public UtilisateurSite setModeleModifie(Date o) {
		this.modeleModifie = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());
		this.modeleModifieCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite modeleModifieInit() {
		if(!modeleModifieCouverture.dejaInitialise) {
			_modeleModifie(modeleModifieCouverture);
			if(modeleModifie == null)
				setModeleModifie(modeleModifieCouverture.o);
		}
		modeleModifieCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public Date solrModeleModifie() {
		return modeleModifie == null ? null : Date.from(modeleModifie.toInstant());
	}

	public String strModeleModifie() {
		return modeleModifie == null ? "" : modeleModifie.toString();
	}

	public String nomAffichageModeleModifie() {
		return null;
	}

	public String htmTooltipModeleModifie() {
		return null;
	}

	public String htmModeleModifie() {
		return modeleModifie == null ? "" : StringEscapeUtils.escapeHtml4(strModeleModifie());
	}

	public void htmModeleModifie(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "ModeleModifie\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "ModeleModifie() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setModeleModifie\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageModeleModifie()), "</span>");
				r.s("			<input");
							r.s(" name=\"modeleModifie\"");
							r.s(" value=\"", htmModeleModifie(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmModeleModifie());
			}
			r.l("</div>");
		}
	}

	//////////////////////////////
	// modeleClasseNomCanonique //
	//////////////////////////////

	/**	L'entité « modeleClasseNomCanonique »
	 *	 is defined as null before being initialized. 
	 */
	protected String modeleClasseNomCanonique;
	public Couverture<String> modeleClasseNomCanoniqueCouverture = new Couverture<String>().p(this).c(String.class).var("modeleClasseNomCanonique").o(modeleClasseNomCanonique);

	/**	<br/>L'entité « modeleClasseNomCanonique »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:modeleClasseNomCanonique">Trouver l'entité modeleClasseNomCanonique dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _modeleClasseNomCanonique(Couverture<String> c);

	public String getModeleClasseNomCanonique() {
		return modeleClasseNomCanonique;
	}

	public void setModeleClasseNomCanonique(String modeleClasseNomCanonique) {
		this.modeleClasseNomCanonique = modeleClasseNomCanonique;
		this.modeleClasseNomCanoniqueCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite modeleClasseNomCanoniqueInit() {
		if(!modeleClasseNomCanoniqueCouverture.dejaInitialise) {
			_modeleClasseNomCanonique(modeleClasseNomCanoniqueCouverture);
			if(modeleClasseNomCanonique == null)
				setModeleClasseNomCanonique(modeleClasseNomCanoniqueCouverture.o);
		}
		modeleClasseNomCanoniqueCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrModeleClasseNomCanonique() {
		return modeleClasseNomCanonique;
	}

	public String strModeleClasseNomCanonique() {
		return modeleClasseNomCanonique == null ? "" : modeleClasseNomCanonique;
	}

	public String nomAffichageModeleClasseNomCanonique() {
		return null;
	}

	public String htmTooltipModeleClasseNomCanonique() {
		return null;
	}

	public String htmModeleClasseNomCanonique() {
		return modeleClasseNomCanonique == null ? "" : StringEscapeUtils.escapeHtml4(strModeleClasseNomCanonique());
	}

	public void htmModeleClasseNomCanonique(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "ModeleClasseNomCanonique\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "ModeleClasseNomCanonique() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setModeleClasseNomCanonique\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageModeleClasseNomCanonique()), "</span>");
				r.s("			<input");
							r.s(" name=\"modeleClasseNomCanonique\"");
							r.s(" value=\"", htmModeleClasseNomCanonique(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmModeleClasseNomCanonique());
			}
			r.l("</div>");
		}
	}

	///////////////
	// modeleCle //
	///////////////

	/**	L'entité « modeleCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long modeleCle;
	public Couverture<Long> modeleCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("modeleCle").o(modeleCle);

	/**	<br/>L'entité « modeleCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:modeleCle">Trouver l'entité modeleCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _modeleCle(Couverture<Long> c);

	public Long getModeleCle() {
		return modeleCle;
	}

	public void setModeleCle(Long modeleCle) {
		this.modeleCle = modeleCle;
		this.modeleCleCouverture.dejaInitialise = true;
	}
	public UtilisateurSite setModeleCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.modeleCle = Long.parseLong(o);
		this.modeleCleCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite modeleCleInit() {
		if(!modeleCleCouverture.dejaInitialise) {
			_modeleCle(modeleCleCouverture);
			if(modeleCle == null)
				setModeleCle(modeleCleCouverture.o);
		}
		modeleCleCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public Long solrModeleCle() {
		return modeleCle;
	}

	public String strModeleCle() {
		return modeleCle == null ? "" : modeleCle.toString();
	}

	public String nomAffichageModeleCle() {
		return null;
	}

	public String htmTooltipModeleCle() {
		return null;
	}

	public String htmModeleCle() {
		return modeleCle == null ? "" : StringEscapeUtils.escapeHtml4(strModeleCle());
	}

	public void htmModeleCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "ModeleCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "ModeleCle() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setModeleCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageModeleCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"modeleCle\"");
							r.s(" value=\"", htmModeleCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmModeleCle());
			}
			r.l("</div>");
		}
	}

	////////////////////////////
	// modeleSuggestionStocke //
	////////////////////////////

	/**	L'entité « modeleSuggestionStocke »
	 *	 is defined as null before being initialized. 
	 */
	protected String modeleSuggestionStocke;
	public Couverture<String> modeleSuggestionStockeCouverture = new Couverture<String>().p(this).c(String.class).var("modeleSuggestionStocke").o(modeleSuggestionStocke);

	/**	<br/>L'entité « modeleSuggestionStocke »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:modeleSuggestionStocke">Trouver l'entité modeleSuggestionStocke dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _modeleSuggestionStocke(Couverture<String> c);

	public String getModeleSuggestionStocke() {
		return modeleSuggestionStocke;
	}

	public void setModeleSuggestionStocke(String modeleSuggestionStocke) {
		this.modeleSuggestionStocke = modeleSuggestionStocke;
		this.modeleSuggestionStockeCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite modeleSuggestionStockeInit() {
		if(!modeleSuggestionStockeCouverture.dejaInitialise) {
			_modeleSuggestionStocke(modeleSuggestionStockeCouverture);
			if(modeleSuggestionStocke == null)
				setModeleSuggestionStocke(modeleSuggestionStockeCouverture.o);
		}
		modeleSuggestionStockeCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrModeleSuggestionStocke() {
		return modeleSuggestionStocke;
	}

	public String strModeleSuggestionStocke() {
		return modeleSuggestionStocke == null ? "" : modeleSuggestionStocke;
	}

	public String nomAffichageModeleSuggestionStocke() {
		return null;
	}

	public String htmTooltipModeleSuggestionStocke() {
		return null;
	}

	public String htmModeleSuggestionStocke() {
		return modeleSuggestionStocke == null ? "" : StringEscapeUtils.escapeHtml4(strModeleSuggestionStocke());
	}

	public void htmModeleSuggestionStocke(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "ModeleSuggestionStocke\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "ModeleSuggestionStocke() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setModeleSuggestionStocke\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageModeleSuggestionStocke()), "</span>");
				r.s("			<input");
							r.s(" name=\"modeleSuggestionStocke\"");
							r.s(" value=\"", htmModeleSuggestionStocke(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmModeleSuggestionStocke());
			}
			r.l("</div>");
		}
	}

	////////////////////////////
	// modeleSuggestionIndexe //
	////////////////////////////

	/**	L'entité « modeleSuggestionIndexe »
	 *	 is defined as null before being initialized. 
	 */
	protected String modeleSuggestionIndexe;
	public Couverture<String> modeleSuggestionIndexeCouverture = new Couverture<String>().p(this).c(String.class).var("modeleSuggestionIndexe").o(modeleSuggestionIndexe);

	/**	<br/>L'entité « modeleSuggestionIndexe »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:modeleSuggestionIndexe">Trouver l'entité modeleSuggestionIndexe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _modeleSuggestionIndexe(Couverture<String> c);

	public String getModeleSuggestionIndexe() {
		return modeleSuggestionIndexe;
	}

	public void setModeleSuggestionIndexe(String modeleSuggestionIndexe) {
		this.modeleSuggestionIndexe = modeleSuggestionIndexe;
		this.modeleSuggestionIndexeCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite modeleSuggestionIndexeInit() {
		if(!modeleSuggestionIndexeCouverture.dejaInitialise) {
			_modeleSuggestionIndexe(modeleSuggestionIndexeCouverture);
			if(modeleSuggestionIndexe == null)
				setModeleSuggestionIndexe(modeleSuggestionIndexeCouverture.o);
		}
		modeleSuggestionIndexeCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrModeleSuggestionIndexe() {
		return modeleSuggestionIndexe;
	}

	public String strModeleSuggestionIndexe() {
		return modeleSuggestionIndexe == null ? "" : modeleSuggestionIndexe;
	}

	public String nomAffichageModeleSuggestionIndexe() {
		return null;
	}

	public String htmTooltipModeleSuggestionIndexe() {
		return null;
	}

	public String htmModeleSuggestionIndexe() {
		return modeleSuggestionIndexe == null ? "" : StringEscapeUtils.escapeHtml4(strModeleSuggestionIndexe());
	}

	public void htmModeleSuggestionIndexe(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchUtilisateurSite", strPk(), "ModeleSuggestionIndexe\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchUtilisateurSite", strPk(), "ModeleSuggestionIndexe() {");
				r.l("			$.ajax({");
				r.l("				url: '/api/v1/site/utilisateur?fq=pk:", strPk(), "',");
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
				r.l("				data: {\"setModeleSuggestionIndexe\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageModeleSuggestionIndexe()), "</span>");
				r.s("			<input");
							r.s(" name=\"modeleSuggestionIndexe\"");
							r.s(" value=\"", htmModeleSuggestionIndexe(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmModeleSuggestionIndexe());
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
		calculInrPksInit();
		utilisateurNomInit();
		utilisateurMailInit();
		utilisateurIdInit();
		utilisateurPrenomInit();
		utilisateurNomFamilleInit();
		utilisateurNomCompletInit();
		utilisateurSiteInit();
		utilisateurRecevoirCourrielsInit();
		modeleSupprimeInit();
		modeleCreeInit();
		modeleModifieInit();
		modeleClasseNomCanoniqueInit();
		modeleCleInit();
		modeleSuggestionStockeInit();
		modeleSuggestionIndexeInit();
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

	//public void indexerUtilisateurSite() throws Exception {
		//RequeteSite requeteSite = new RequeteSite();
		//requeteSite.initLoinRequeteSite();
		//SiteContexte siteContexte = new SiteContexte();
		//siteContexte.initLoinSiteContexte();
		//siteContexte.setRequeteSite_(requeteSite);
		//requeteSite.setSiteContexte_(siteContexte);
		//requeteSiteUtilisateurSite(requeteSite);
		//initLoinUtilisateurSite(requeteSite);
		//indexerUtilisateurSite();
	//}


	@Override public void indexerPourClasse() throws Exception {
		indexerUtilisateurSite();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) throws Exception {
		indexerUtilisateurSite(document);
	}

	public void indexerUtilisateurSite(SolrClient clientSolr) throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		indexerUtilisateurSite(document);
		document.addField("sauvegardesUtilisateurSite_stored_strings", sauvegardesUtilisateurSite);
		clientSolr.add(document);
		clientSolr.commit();
	}

	public void indexerUtilisateurSite() throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		indexerUtilisateurSite(document);
		document.addField("sauvegardesUtilisateurSite_stored_strings", sauvegardesUtilisateurSite);
		SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
		clientSolr.add(document);
		clientSolr.commit();
	}

	public void indexerUtilisateurSite(SolrInputDocument document) throws Exception {
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
		if(utilisateurId != null) {
			document.addField("utilisateurId_indexed_string", utilisateurId);
			document.addField("utilisateurId_stored_string", utilisateurId);
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
		if(modeleSupprime != null) {
			document.addField("modeleSupprime_indexed_boolean", modeleSupprime);
			document.addField("modeleSupprime_stored_boolean", modeleSupprime);
		}
		if(modeleCree != null) {
			document.addField("modeleCree_indexed_date", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(modeleCree));
			document.addField("modeleCree_stored_date", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(modeleCree));
		}
		if(modeleModifie != null) {
			document.addField("modeleModifie_indexed_date", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(modeleModifie));
			document.addField("modeleModifie_stored_date", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(modeleModifie));
		}
		if(modeleClasseNomCanonique != null) {
			document.addField("modeleClasseNomCanonique_indexed_string", modeleClasseNomCanonique);
			document.addField("modeleClasseNomCanonique_stored_string", modeleClasseNomCanonique);
		}
		if(modeleCle != null) {
			document.addField("modeleCle_indexed_long", modeleCle);
			document.addField("modeleCle_stored_long", modeleCle);
		}
		if(modeleSuggestionStocke != null) {
			document.addField("modeleSuggestionStocke_indexed_string", modeleSuggestionStocke);
			document.addField("modeleSuggestionStocke_stored_string", modeleSuggestionStocke);
		}
		if(modeleSuggestionIndexe != null) {
			document.addField("modeleSuggestionIndexe_indexed_string", modeleSuggestionIndexe);
			document.addField("modeleSuggestionIndexe_stored_string", modeleSuggestionIndexe);
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
			case "calculInrPks":
				return oUtilisateurSite.calculInrPks;
			case "requeteSite_":
				return oUtilisateurSite.requeteSite_;
			case "utilisateurNom":
				return oUtilisateurSite.utilisateurNom;
			case "utilisateurMail":
				return oUtilisateurSite.utilisateurMail;
			case "utilisateurId":
				return oUtilisateurSite.utilisateurId;
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
			case "modeleSupprime":
				return oUtilisateurSite.modeleSupprime;
			case "modeleCree":
				return oUtilisateurSite.modeleCree;
			case "modeleModifie":
				return oUtilisateurSite.modeleModifie;
			case "modeleClasseNomCanonique":
				return oUtilisateurSite.modeleClasseNomCanonique;
			case "modeleCle":
				return oUtilisateurSite.modeleCle;
			case "modeleSuggestionStocke":
				return oUtilisateurSite.modeleSuggestionStocke;
			case "modeleSuggestionIndexe":
				return oUtilisateurSite.modeleSuggestionIndexe;
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
			case "utilisateurId":
				setUtilisateurId(val);
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
			case "modeleSupprime":
				setModeleSupprime(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "modeleCree":
				setModeleCree(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "modeleModifie":
				setModeleModifie(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "modeleClasseNomCanonique":
				setModeleClasseNomCanonique(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "modeleCle":
				setModeleCle(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "modeleSuggestionStocke":
				setModeleSuggestionStocke(val);
				sauvegardesUtilisateurSite.add(var);
				return val;
			case "modeleSuggestionIndexe":
				setModeleSuggestionIndexe(val);
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

			if(sauvegardesUtilisateurSite.contains("utilisateurId")) {
				String utilisateurId = (String)solrDocument.get("utilisateurId_stored_string");
				if(utilisateurId != null)
					oUtilisateurSite.setUtilisateurId(utilisateurId);
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

			if(sauvegardesUtilisateurSite.contains("modeleSupprime")) {
				Boolean modeleSupprime = (Boolean)solrDocument.get("modeleSupprime_stored_boolean");
				if(modeleSupprime != null)
					oUtilisateurSite.setModeleSupprime(modeleSupprime);
			}

			if(sauvegardesUtilisateurSite.contains("modeleCree")) {
				Date modeleCree = (Date)solrDocument.get("modeleCree_stored_date");
				if(modeleCree != null)
					oUtilisateurSite.setModeleCree(modeleCree);
			}

			if(sauvegardesUtilisateurSite.contains("modeleModifie")) {
				Date modeleModifie = (Date)solrDocument.get("modeleModifie_stored_date");
				if(modeleModifie != null)
					oUtilisateurSite.setModeleModifie(modeleModifie);
			}

			if(sauvegardesUtilisateurSite.contains("modeleClasseNomCanonique")) {
				String modeleClasseNomCanonique = (String)solrDocument.get("modeleClasseNomCanonique_stored_string");
				if(modeleClasseNomCanonique != null)
					oUtilisateurSite.setModeleClasseNomCanonique(modeleClasseNomCanonique);
			}

			if(sauvegardesUtilisateurSite.contains("modeleCle")) {
				Long modeleCle = (Long)solrDocument.get("modeleCle_stored_long");
				if(modeleCle != null)
					oUtilisateurSite.setModeleCle(modeleCle);
			}

			if(sauvegardesUtilisateurSite.contains("modeleSuggestionStocke")) {
				String modeleSuggestionStocke = (String)solrDocument.get("modeleSuggestionStocke_stored_string");
				if(modeleSuggestionStocke != null)
					oUtilisateurSite.setModeleSuggestionStocke(modeleSuggestionStocke);
			}

			if(sauvegardesUtilisateurSite.contains("modeleSuggestionIndexe")) {
				String modeleSuggestionIndexe = (String)solrDocument.get("modeleSuggestionIndexe_stored_string");
				if(modeleSuggestionIndexe != null)
					oUtilisateurSite.setModeleSuggestionIndexe(modeleSuggestionIndexe);
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

		List<Long> calculInrPks = (List<Long>)solrDocument.get("calculInrPks_stored_longs");
		if(calculInrPks != null)
			oUtilisateurSite.calculInrPks.addAll(calculInrPks);

		String utilisateurNom = (String)solrDocument.get("utilisateurNom_stored_string");
		if(utilisateurNom != null)
			oUtilisateurSite.setUtilisateurNom(utilisateurNom);

		String utilisateurMail = (String)solrDocument.get("utilisateurMail_stored_string");
		if(utilisateurMail != null)
			oUtilisateurSite.setUtilisateurMail(utilisateurMail);

		String utilisateurId = (String)solrDocument.get("utilisateurId_stored_string");
		if(utilisateurId != null)
			oUtilisateurSite.setUtilisateurId(utilisateurId);

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

		Boolean modeleSupprime = (Boolean)solrDocument.get("modeleSupprime_stored_boolean");
		if(modeleSupprime != null)
			oUtilisateurSite.setModeleSupprime(modeleSupprime);

		Date modeleCree = (Date)solrDocument.get("modeleCree_stored_date");
		if(modeleCree != null)
			oUtilisateurSite.setModeleCree(modeleCree);

		Date modeleModifie = (Date)solrDocument.get("modeleModifie_stored_date");
		if(modeleModifie != null)
			oUtilisateurSite.setModeleModifie(modeleModifie);

		String modeleClasseNomCanonique = (String)solrDocument.get("modeleClasseNomCanonique_stored_string");
		if(modeleClasseNomCanonique != null)
			oUtilisateurSite.setModeleClasseNomCanonique(modeleClasseNomCanonique);

		Long modeleCle = (Long)solrDocument.get("modeleCle_stored_long");
		if(modeleCle != null)
			oUtilisateurSite.setModeleCle(modeleCle);

		String modeleSuggestionStocke = (String)solrDocument.get("modeleSuggestionStocke_stored_string");
		if(modeleSuggestionStocke != null)
			oUtilisateurSite.setModeleSuggestionStocke(modeleSuggestionStocke);

		String modeleSuggestionIndexe = (String)solrDocument.get("modeleSuggestionIndexe_stored_string");
		if(modeleSuggestionIndexe != null)
			oUtilisateurSite.setModeleSuggestionIndexe(modeleSuggestionIndexe);

		super.stockerCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), calculInrPks, utilisateurNom, utilisateurMail, utilisateurId, utilisateurPrenom, utilisateurNomFamille, utilisateurNomComplet, utilisateurSite, utilisateurRecevoirCourriels, modeleSupprime, modeleCree, modeleModifie, modeleClasseNomCanonique, modeleCle, modeleSuggestionStocke, modeleSuggestionIndexe);
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
				&& Objects.equals( calculInrPks, that.calculInrPks )
				&& Objects.equals( utilisateurNom, that.utilisateurNom )
				&& Objects.equals( utilisateurMail, that.utilisateurMail )
				&& Objects.equals( utilisateurId, that.utilisateurId )
				&& Objects.equals( utilisateurPrenom, that.utilisateurPrenom )
				&& Objects.equals( utilisateurNomFamille, that.utilisateurNomFamille )
				&& Objects.equals( utilisateurNomComplet, that.utilisateurNomComplet )
				&& Objects.equals( utilisateurSite, that.utilisateurSite )
				&& Objects.equals( utilisateurRecevoirCourriels, that.utilisateurRecevoirCourriels )
				&& Objects.equals( modeleSupprime, that.modeleSupprime )
				&& Objects.equals( modeleCree, that.modeleCree )
				&& Objects.equals( modeleModifie, that.modeleModifie )
				&& Objects.equals( modeleClasseNomCanonique, that.modeleClasseNomCanonique )
				&& Objects.equals( modeleCle, that.modeleCle )
				&& Objects.equals( modeleSuggestionStocke, that.modeleSuggestionStocke )
				&& Objects.equals( modeleSuggestionIndexe, that.modeleSuggestionIndexe );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("UtilisateurSite {");
		sb.append( "calculInrPks: " ).append(calculInrPks);
		sb.append( ", utilisateurNom: \"" ).append(utilisateurNom).append( "\"" );
		sb.append( ", utilisateurMail: \"" ).append(utilisateurMail).append( "\"" );
		sb.append( ", utilisateurId: \"" ).append(utilisateurId).append( "\"" );
		sb.append( ", utilisateurPrenom: \"" ).append(utilisateurPrenom).append( "\"" );
		sb.append( ", utilisateurNomFamille: \"" ).append(utilisateurNomFamille).append( "\"" );
		sb.append( ", utilisateurNomComplet: \"" ).append(utilisateurNomComplet).append( "\"" );
		sb.append( ", utilisateurSite: \"" ).append(utilisateurSite).append( "\"" );
		sb.append( ", utilisateurRecevoirCourriels: " ).append(utilisateurRecevoirCourriels);
		sb.append( ", modeleSupprime: " ).append(modeleSupprime);
		sb.append( ", modeleCree: " ).append(modeleCree);
		sb.append( ", modeleModifie: " ).append(modeleModifie);
		sb.append( ", modeleClasseNomCanonique: \"" ).append(modeleClasseNomCanonique).append( "\"" );
		sb.append( ", modeleCle: " ).append(modeleCle);
		sb.append( ", modeleSuggestionStocke: \"" ).append(modeleSuggestionStocke).append( "\"" );
		sb.append( ", modeleSuggestionIndexe: \"" ).append(modeleSuggestionIndexe).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
