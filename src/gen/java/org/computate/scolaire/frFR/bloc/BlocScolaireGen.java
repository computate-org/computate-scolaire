package org.computate.scolaire.frFR.bloc;

import java.util.Date;
import org.computate.scolaire.frFR.session.SessionScolaire;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.computate.scolaire.frFR.couverture.Couverture;
import java.lang.Long;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.frFR.cluster.Cluster;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe objectSuggest dans Solr</a>
 * <br/>
 **/
public abstract class BlocScolaireGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(BlocScolaire.class);

	public static final String BlocScolaire_UnNom = "un bloc";
	public static final String BlocScolaire_Ce = "ce ";
	public static final String BlocScolaire_CeNom = "ce bloc";
	public static final String BlocScolaire_Un = "un ";
	public static final String BlocScolaire_LeNom = "le bloc";
	public static final String BlocScolaire_NomSingulier = "bloc";
	public static final String BlocScolaire_NomPluriel = "blocs";
	public static final String BlocScolaire_NomActuel = "bloc actuel";
	public static final String BlocScolaire_TousNom = "tous les blocs";
	public static final String BlocScolaire_RechercherTousNomPar = "rechercher blocs par ";
	public static final String BlocScolaire_RechercherTousNom = "rechercher blocs";
	public static final String BlocScolaire_LesNoms = "les blocs";
	public static final String BlocScolaire_AucunNomTrouve = "aucun bloc trouvé";
	public static final String BlocScolaire_NomVar = "bloc";
	public static final String BlocScolaire_DeNom = "de bloc";
	public static final String BlocScolaire_Couleur = "indigo";
	public static final String BlocScolaire_IconeGroupe = "duotone";
	public static final String BlocScolaire_IconeNom = "bell-o";

	//////////////
	// ecoleCle //
	//////////////

	/**	L'entité « ecoleCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long ecoleCle;
	@JsonIgnore
	public Couverture<Long> ecoleCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("ecoleCle").o(ecoleCle);

	/**	<br/>L'entité « ecoleCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
	public BlocScolaire setEcoleCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ecoleCle = Long.parseLong(o);
		this.ecoleCleCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Long solrEcoleCle() {
		return ecoleCle;
	}

	public String strEcoleCle() {
		return ecoleCle == null ? "" : ecoleCle.toString();
	}

	public String nomAffichageEcoleCle() {
		return "école";
	}

	public String htmTooltipEcoleCle() {
		return null;
	}

	public String htmEcoleCle() {
		return ecoleCle == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleCle());
	}

	public void htmEcoleCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "EcoleCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "EcoleCle() {");
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

	//////////////
	// anneeCle //
	//////////////

	/**	L'entité « anneeCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long anneeCle;
	@JsonIgnore
	public Couverture<Long> anneeCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("anneeCle").o(anneeCle);

	/**	<br/>L'entité « anneeCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeCle(Couverture<Long> c);

	public Long getAnneeCle() {
		return anneeCle;
	}

	public void setAnneeCle(Long anneeCle) {
		this.anneeCle = anneeCle;
		this.anneeCleCouverture.dejaInitialise = true;
	}
	public BlocScolaire setAnneeCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.anneeCle = Long.parseLong(o);
		this.anneeCleCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire anneeCleInit() {
		if(!anneeCleCouverture.dejaInitialise) {
			_anneeCle(anneeCleCouverture);
			if(anneeCle == null)
				setAnneeCle(anneeCleCouverture.o);
		}
		anneeCleCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Long solrAnneeCle() {
		return anneeCle;
	}

	public String strAnneeCle() {
		return anneeCle == null ? "" : anneeCle.toString();
	}

	public String nomAffichageAnneeCle() {
		return null;
	}

	public String htmTooltipAnneeCle() {
		return null;
	}

	public String htmAnneeCle() {
		return anneeCle == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCle());
	}

	public void htmAnneeCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "AnneeCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "AnneeCle() {");
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
				r.l("				data: {\"setAnneeCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAnneeCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"anneeCle\"");
							r.s(" value=\"", htmAnneeCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAnneeCle());
			}
			r.l("</div>");
		}
	}

	///////////////
	// saisonCle //
	///////////////

	/**	L'entité « saisonCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long saisonCle;
	@JsonIgnore
	public Couverture<Long> saisonCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("saisonCle").o(saisonCle);

	/**	<br/>L'entité « saisonCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCle">Trouver l'entité saisonCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonCle(Couverture<Long> c);

	public Long getSaisonCle() {
		return saisonCle;
	}

	public void setSaisonCle(Long saisonCle) {
		this.saisonCle = saisonCle;
		this.saisonCleCouverture.dejaInitialise = true;
	}
	public BlocScolaire setSaisonCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.saisonCle = Long.parseLong(o);
		this.saisonCleCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire saisonCleInit() {
		if(!saisonCleCouverture.dejaInitialise) {
			_saisonCle(saisonCleCouverture);
			if(saisonCle == null)
				setSaisonCle(saisonCleCouverture.o);
		}
		saisonCleCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Long solrSaisonCle() {
		return saisonCle;
	}

	public String strSaisonCle() {
		return saisonCle == null ? "" : saisonCle.toString();
	}

	public String nomAffichageSaisonCle() {
		return "saison";
	}

	public String htmTooltipSaisonCle() {
		return null;
	}

	public String htmSaisonCle() {
		return saisonCle == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonCle());
	}

	public void htmSaisonCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SaisonCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SaisonCle() {");
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
				r.l("				data: {\"setSaisonCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSaisonCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"saisonCle\"");
							r.s(" value=\"", htmSaisonCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSaisonCle());
			}
			r.l("</div>");
		}
	}

	////////////////
	// sessionCle //
	////////////////

	/**	L'entité « sessionCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long sessionCle;
	@JsonIgnore
	public Couverture<Long> sessionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("sessionCle").o(sessionCle);

	/**	<br/>L'entité « sessionCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCle">Trouver l'entité sessionCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionCle(Couverture<Long> c);

	public Long getSessionCle() {
		return sessionCle;
	}

	public void setSessionCle(Long sessionCle) {
		this.sessionCle = sessionCle;
		this.sessionCleCouverture.dejaInitialise = true;
	}
	public BlocScolaire setSessionCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.sessionCle = Long.parseLong(o);
		this.sessionCleCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire sessionCleInit() {
		if(!sessionCleCouverture.dejaInitialise) {
			_sessionCle(sessionCleCouverture);
			if(sessionCle == null)
				setSessionCle(sessionCleCouverture.o);
		}
		sessionCleCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Long solrSessionCle() {
		return sessionCle;
	}

	public String strSessionCle() {
		return sessionCle == null ? "" : sessionCle.toString();
	}

	public String nomAffichageSessionCle() {
		return "clé";
	}

	public String htmTooltipSessionCle() {
		return null;
	}

	public String htmSessionCle() {
		return sessionCle == null ? "" : StringEscapeUtils.escapeHtml4(strSessionCle());
	}

	public void htmSessionCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SessionCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SessionCle() {");
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
				r.l("				data: {\"setSessionCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionCle\"");
							r.s(" value=\"", htmSessionCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionCle());
			}
			r.l("</div>");
		}
	}

	////////////
	// ageCle //
	////////////

	/**	L'entité « ageCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long ageCle;
	@JsonIgnore
	public Couverture<Long> ageCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("ageCle").o(ageCle);

	/**	<br/>L'entité « ageCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCle">Trouver l'entité ageCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageCle(Couverture<Long> c);

	public Long getAgeCle() {
		return ageCle;
	}

	public void setAgeCle(Long ageCle) {
		this.ageCle = ageCle;
		this.ageCleCouverture.dejaInitialise = true;
	}
	public BlocScolaire setAgeCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ageCle = Long.parseLong(o);
		this.ageCleCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire ageCleInit() {
		if(!ageCleCouverture.dejaInitialise) {
			_ageCle(ageCleCouverture);
			if(ageCle == null)
				setAgeCle(ageCleCouverture.o);
		}
		ageCleCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Long solrAgeCle() {
		return ageCle;
	}

	public String strAgeCle() {
		return ageCle == null ? "" : ageCle.toString();
	}

	public String nomAffichageAgeCle() {
		return "clé";
	}

	public String htmTooltipAgeCle() {
		return null;
	}

	public String htmAgeCle() {
		return ageCle == null ? "" : StringEscapeUtils.escapeHtml4(strAgeCle());
	}

	public void htmAgeCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "AgeCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "AgeCle() {");
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
				r.l("				data: {\"setAgeCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageCle\"");
							r.s(" value=\"", htmAgeCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeCle());
			}
			r.l("</div>");
		}
	}

	/////////////
	// blocCle //
	/////////////

	/**	L'entité « blocCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long blocCle;
	@JsonIgnore
	public Couverture<Long> blocCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("blocCle").o(blocCle);

	/**	<br/>L'entité « blocCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocCle">Trouver l'entité blocCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocCle(Couverture<Long> c);

	public Long getBlocCle() {
		return blocCle;
	}

	public void setBlocCle(Long blocCle) {
		this.blocCle = blocCle;
		this.blocCleCouverture.dejaInitialise = true;
	}
	public BlocScolaire setBlocCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.blocCle = Long.parseLong(o);
		this.blocCleCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire blocCleInit() {
		if(!blocCleCouverture.dejaInitialise) {
			_blocCle(blocCleCouverture);
			if(blocCle == null)
				setBlocCle(blocCleCouverture.o);
		}
		blocCleCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Long solrBlocCle() {
		return blocCle;
	}

	public String strBlocCle() {
		return blocCle == null ? "" : blocCle.toString();
	}

	public String nomAffichageBlocCle() {
		return "clé";
	}

	public String htmTooltipBlocCle() {
		return null;
	}

	public String htmBlocCle() {
		return blocCle == null ? "" : StringEscapeUtils.escapeHtml4(strBlocCle());
	}

	public void htmBlocCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "BlocCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "BlocCle() {");
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
				r.l("				data: {\"setBlocCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocCle\"");
							r.s(" value=\"", htmBlocCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocCle());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// inscriptionCles //
	/////////////////////

	/**	L'entité « inscriptionCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> inscriptionCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> inscriptionClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("inscriptionCles").o(inscriptionCles);

	/**	<br/>L'entité « inscriptionCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
	 * <br/>
	 * @param inscriptionCles est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionCles(List<Long> o);

	public List<Long> getInscriptionCles() {
		return inscriptionCles;
	}

	public void setInscriptionCles(List<Long> inscriptionCles) {
		this.inscriptionCles = inscriptionCles;
		this.inscriptionClesCouverture.dejaInitialise = true;
	}
	public BlocScolaire addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (BlocScolaire)this;
	}
	public BlocScolaire addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (BlocScolaire)this;
	}
	public BlocScolaire setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
		return (BlocScolaire)this;
	}
	public BlocScolaire addInscriptionCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (BlocScolaire)this;
	}
	protected BlocScolaire inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public List<Long> solrInscriptionCles() {
		return inscriptionCles;
	}

	public String strInscriptionCles() {
		return inscriptionCles == null ? "" : inscriptionCles.toString();
	}

	public String nomAffichageInscriptionCles() {
		return null;
	}

	public String htmTooltipInscriptionCles() {
		return null;
	}

	public String htmInscriptionCles() {
		return inscriptionCles == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionCles());
	}

	public void htmInscriptionCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "InscriptionCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "InscriptionCles() {");
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
				r.l("				data: {\"setInscriptionCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageInscriptionCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"inscriptionCles\"");
							r.s(" value=\"", htmInscriptionCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmInscriptionCles());
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
	@JsonIgnore
	public Couverture<Integer> scolaireTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("scolaireTri").o(scolaireTri);

	/**	<br/>L'entité « scolaireTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:scolaireTri">Trouver l'entité scolaireTri dans Solr</a>
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
	public BlocScolaire setScolaireTri(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.scolaireTri = Integer.parseInt(o);
		this.scolaireTriCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire scolaireTriInit() {
		if(!scolaireTriCouverture.dejaInitialise) {
			_scolaireTri(scolaireTriCouverture);
			if(scolaireTri == null)
				setScolaireTri(scolaireTriCouverture.o);
		}
		scolaireTriCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Integer solrScolaireTri() {
		return scolaireTri;
	}

	public String strScolaireTri() {
		return scolaireTri == null ? "" : scolaireTri.toString();
	}

	public String nomAffichageScolaireTri() {
		return null;
	}

	public String htmTooltipScolaireTri() {
		return null;
	}

	public String htmScolaireTri() {
		return scolaireTri == null ? "" : StringEscapeUtils.escapeHtml4(strScolaireTri());
	}

	public void htmScolaireTri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "ScolaireTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "ScolaireTri() {");
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
	@JsonIgnore
	public Couverture<Integer> ecoleTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ecoleTri").o(ecoleTri);

	/**	<br/>L'entité « ecoleTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleTri">Trouver l'entité ecoleTri dans Solr</a>
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
	public BlocScolaire setEcoleTri(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ecoleTri = Integer.parseInt(o);
		this.ecoleTriCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire ecoleTriInit() {
		if(!ecoleTriCouverture.dejaInitialise) {
			_ecoleTri(ecoleTriCouverture);
			if(ecoleTri == null)
				setEcoleTri(ecoleTriCouverture.o);
		}
		ecoleTriCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Integer solrEcoleTri() {
		return ecoleTri;
	}

	public String strEcoleTri() {
		return ecoleTri == null ? "" : ecoleTri.toString();
	}

	public String nomAffichageEcoleTri() {
		return null;
	}

	public String htmTooltipEcoleTri() {
		return null;
	}

	public String htmEcoleTri() {
		return ecoleTri == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleTri());
	}

	public void htmEcoleTri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "EcoleTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "EcoleTri() {");
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
	// anneeTri //
	//////////////

	/**	L'entité « anneeTri »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer anneeTri;
	@JsonIgnore
	public Couverture<Integer> anneeTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeTri").o(anneeTri);

	/**	<br/>L'entité « anneeTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeTri">Trouver l'entité anneeTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeTri(Couverture<Integer> c);

	public Integer getAnneeTri() {
		return anneeTri;
	}

	public void setAnneeTri(Integer anneeTri) {
		this.anneeTri = anneeTri;
		this.anneeTriCouverture.dejaInitialise = true;
	}
	public BlocScolaire setAnneeTri(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.anneeTri = Integer.parseInt(o);
		this.anneeTriCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire anneeTriInit() {
		if(!anneeTriCouverture.dejaInitialise) {
			_anneeTri(anneeTriCouverture);
			if(anneeTri == null)
				setAnneeTri(anneeTriCouverture.o);
		}
		anneeTriCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Integer solrAnneeTri() {
		return anneeTri;
	}

	public String strAnneeTri() {
		return anneeTri == null ? "" : anneeTri.toString();
	}

	public String nomAffichageAnneeTri() {
		return null;
	}

	public String htmTooltipAnneeTri() {
		return null;
	}

	public String htmAnneeTri() {
		return anneeTri == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeTri());
	}

	public void htmAnneeTri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "AnneeTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "AnneeTri() {");
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
				r.l("				data: {\"setAnneeTri\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAnneeTri()), "</span>");
				r.s("			<input");
							r.s(" name=\"anneeTri\"");
							r.s(" value=\"", htmAnneeTri(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAnneeTri());
			}
			r.l("</div>");
		}
	}

	///////////////
	// saisonTri //
	///////////////

	/**	L'entité « saisonTri »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer saisonTri;
	@JsonIgnore
	public Couverture<Integer> saisonTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("saisonTri").o(saisonTri);

	/**	<br/>L'entité « saisonTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonTri">Trouver l'entité saisonTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonTri(Couverture<Integer> c);

	public Integer getSaisonTri() {
		return saisonTri;
	}

	public void setSaisonTri(Integer saisonTri) {
		this.saisonTri = saisonTri;
		this.saisonTriCouverture.dejaInitialise = true;
	}
	public BlocScolaire setSaisonTri(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.saisonTri = Integer.parseInt(o);
		this.saisonTriCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire saisonTriInit() {
		if(!saisonTriCouverture.dejaInitialise) {
			_saisonTri(saisonTriCouverture);
			if(saisonTri == null)
				setSaisonTri(saisonTriCouverture.o);
		}
		saisonTriCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Integer solrSaisonTri() {
		return saisonTri;
	}

	public String strSaisonTri() {
		return saisonTri == null ? "" : saisonTri.toString();
	}

	public String nomAffichageSaisonTri() {
		return null;
	}

	public String htmTooltipSaisonTri() {
		return null;
	}

	public String htmSaisonTri() {
		return saisonTri == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonTri());
	}

	public void htmSaisonTri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SaisonTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SaisonTri() {");
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
				r.l("				data: {\"setSaisonTri\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSaisonTri()), "</span>");
				r.s("			<input");
							r.s(" name=\"saisonTri\"");
							r.s(" value=\"", htmSaisonTri(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSaisonTri());
			}
			r.l("</div>");
		}
	}

	////////////////
	// sessionTri //
	////////////////

	/**	L'entité « sessionTri »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer sessionTri;
	@JsonIgnore
	public Couverture<Integer> sessionTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("sessionTri").o(sessionTri);

	/**	<br/>L'entité « sessionTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionTri">Trouver l'entité sessionTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionTri(Couverture<Integer> c);

	public Integer getSessionTri() {
		return sessionTri;
	}

	public void setSessionTri(Integer sessionTri) {
		this.sessionTri = sessionTri;
		this.sessionTriCouverture.dejaInitialise = true;
	}
	public BlocScolaire setSessionTri(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.sessionTri = Integer.parseInt(o);
		this.sessionTriCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire sessionTriInit() {
		if(!sessionTriCouverture.dejaInitialise) {
			_sessionTri(sessionTriCouverture);
			if(sessionTri == null)
				setSessionTri(sessionTriCouverture.o);
		}
		sessionTriCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Integer solrSessionTri() {
		return sessionTri;
	}

	public String strSessionTri() {
		return sessionTri == null ? "" : sessionTri.toString();
	}

	public String nomAffichageSessionTri() {
		return null;
	}

	public String htmTooltipSessionTri() {
		return null;
	}

	public String htmSessionTri() {
		return sessionTri == null ? "" : StringEscapeUtils.escapeHtml4(strSessionTri());
	}

	public void htmSessionTri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SessionTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SessionTri() {");
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
				r.l("				data: {\"setSessionTri\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionTri()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionTri\"");
							r.s(" value=\"", htmSessionTri(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionTri());
			}
			r.l("</div>");
		}
	}

	//////////////////////
	// sessionRecherche //
	//////////////////////

	/**	L'entité « sessionRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<SessionScolaire>(). 
	 */
	@JsonIgnore
	protected ListeRecherche<SessionScolaire> sessionRecherche = new ListeRecherche<SessionScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<SessionScolaire>> sessionRechercheCouverture = new Couverture<ListeRecherche<SessionScolaire>>().p(this).c(ListeRecherche.class).var("sessionRecherche").o(sessionRecherche);

	/**	<br/>L'entité « sessionRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<SessionScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionRecherche">Trouver l'entité sessionRecherche dans Solr</a>
	 * <br/>
	 * @param sessionRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _sessionRecherche(ListeRecherche<SessionScolaire> l);

	public ListeRecherche<SessionScolaire> getSessionRecherche() {
		return sessionRecherche;
	}

	public void setSessionRecherche(ListeRecherche<SessionScolaire> sessionRecherche) {
		this.sessionRecherche = sessionRecherche;
		this.sessionRechercheCouverture.dejaInitialise = true;
	}
	protected BlocScolaire sessionRechercheInit() {
		if(!sessionRechercheCouverture.dejaInitialise) {
			_sessionRecherche(sessionRecherche);
		}
		sessionRecherche.initLoinPourClasse(requeteSite_);
		sessionRechercheCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	/////////////
	// session //
	/////////////

	/**	L'entité « session »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SessionScolaire session;
	@JsonIgnore
	public Couverture<SessionScolaire> sessionCouverture = new Couverture<SessionScolaire>().p(this).c(SessionScolaire.class).var("session").o(session);

	/**	<br/>L'entité « session »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:session">Trouver l'entité session dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _session(Couverture<SessionScolaire> c);

	public SessionScolaire getSession() {
		return session;
	}

	public void setSession(SessionScolaire session) {
		this.session = session;
		this.sessionCouverture.dejaInitialise = true;
	}
	protected BlocScolaire sessionInit() {
		if(!sessionCouverture.dejaInitialise) {
			_session(sessionCouverture);
			if(session == null)
				setSession(sessionCouverture.o);
		}
		if(session != null)
			session.initLoinPourClasse(requeteSite_);
		sessionCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	/////////////////////
	// ecoleNomComplet //
	/////////////////////

	/**	L'entité « ecoleNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleNomComplet;
	@JsonIgnore
	public Couverture<String> ecoleNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNomComplet").o(ecoleNomComplet);

	/**	<br/>L'entité « ecoleNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNomComplet(Couverture<String> c);

	public String getEcoleNomComplet() {
		return ecoleNomComplet;
	}

	public void setEcoleNomComplet(String ecoleNomComplet) {
		this.ecoleNomComplet = ecoleNomComplet;
		this.ecoleNomCompletCouverture.dejaInitialise = true;
	}
	protected BlocScolaire ecoleNomCompletInit() {
		if(!ecoleNomCompletCouverture.dejaInitialise) {
			_ecoleNomComplet(ecoleNomCompletCouverture);
			if(ecoleNomComplet == null)
				setEcoleNomComplet(ecoleNomCompletCouverture.o);
		}
		ecoleNomCompletCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public String solrEcoleNomComplet() {
		return ecoleNomComplet;
	}

	public String strEcoleNomComplet() {
		return ecoleNomComplet == null ? "" : ecoleNomComplet;
	}

	public String nomAffichageEcoleNomComplet() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEcoleNomComplet() {
		return null;
	}

	public String htmEcoleNomComplet() {
		return ecoleNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNomComplet());
	}

	public void htmEcoleNomComplet(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "EcoleNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "EcoleNomComplet() {");
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
				r.l("				data: {\"setEcoleNomComplet\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEcoleNomComplet()), "</span>");
				r.s("			<input");
							r.s(" name=\"ecoleNomComplet\"");
							r.s(" value=\"", htmEcoleNomComplet(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEcoleNomComplet());
			}
			r.l("</div>");
		}
	}

	////////////////
	// anneeDebut //
	////////////////

	/**	L'entité « anneeDebut »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate anneeDebut;
	@JsonIgnore
	public Couverture<LocalDate> anneeDebutCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("anneeDebut").o(anneeDebut);

	/**	<br/>L'entité « anneeDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeDebut(Couverture<LocalDate> c);

	public LocalDate getAnneeDebut() {
		return anneeDebut;
	}

	public void setAnneeDebut(LocalDate anneeDebut) {
		this.anneeDebut = anneeDebut;
		this.anneeDebutCouverture.dejaInitialise = true;
	}
	public BlocScolaire setAnneeDebut(Instant o) {
		this.anneeDebut = LocalDate.from(o);
		this.anneeDebutCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public BlocScolaire setAnneeDebut(String o) {
		this.anneeDebut = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.anneeDebutCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	public BlocScolaire setAnneeDebut(Date o) {
		this.anneeDebut = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.anneeDebutCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire anneeDebutInit() {
		if(!anneeDebutCouverture.dejaInitialise) {
			_anneeDebut(anneeDebutCouverture);
			if(anneeDebut == null)
				setAnneeDebut(anneeDebutCouverture.o);
		}
		anneeDebutCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Date solrAnneeDebut() {
		return anneeDebut == null ? null : Date.from(anneeDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strAnneeDebut() {
		return anneeDebut == null ? "" : anneeDebut.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageAnneeDebut() {
		return "début de l'année";
	}

	public String htmTooltipAnneeDebut() {
		return null;
	}

	public String htmAnneeDebut() {
		return anneeDebut == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeDebut());
	}

	public void htmAnneeDebut(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "AnneeDebut\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "AnneeDebut() {");
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
				r.l("				data: {\"setAnneeDebut\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAnneeDebut()), "</span>");
				r.s("			<input");
							r.s(" name=\"anneeDebut\"");
							r.s(" value=\"", htmAnneeDebut(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAnneeDebut());
			}
			r.l("</div>");
		}
	}

	//////////////
	// anneeFin //
	//////////////

	/**	L'entité « anneeFin »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate anneeFin;
	@JsonIgnore
	public Couverture<LocalDate> anneeFinCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("anneeFin").o(anneeFin);

	/**	<br/>L'entité « anneeFin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeFin(Couverture<LocalDate> c);

	public LocalDate getAnneeFin() {
		return anneeFin;
	}

	public void setAnneeFin(LocalDate anneeFin) {
		this.anneeFin = anneeFin;
		this.anneeFinCouverture.dejaInitialise = true;
	}
	public BlocScolaire setAnneeFin(Instant o) {
		this.anneeFin = LocalDate.from(o);
		this.anneeFinCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public BlocScolaire setAnneeFin(String o) {
		this.anneeFin = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.anneeFinCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	public BlocScolaire setAnneeFin(Date o) {
		this.anneeFin = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.anneeFinCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire anneeFinInit() {
		if(!anneeFinCouverture.dejaInitialise) {
			_anneeFin(anneeFinCouverture);
			if(anneeFin == null)
				setAnneeFin(anneeFinCouverture.o);
		}
		anneeFinCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Date solrAnneeFin() {
		return anneeFin == null ? null : Date.from(anneeFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strAnneeFin() {
		return anneeFin == null ? "" : anneeFin.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageAnneeFin() {
		return "le fin de l'année";
	}

	public String htmTooltipAnneeFin() {
		return null;
	}

	public String htmAnneeFin() {
		return anneeFin == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeFin());
	}

	public void htmAnneeFin(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "AnneeFin\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "AnneeFin() {");
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
				r.l("				data: {\"setAnneeFin\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAnneeFin()), "</span>");
				r.s("			<input");
							r.s(" name=\"anneeFin\"");
							r.s(" value=\"", htmAnneeFin(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAnneeFin());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// saisonJourDebut //
	/////////////////////

	/**	L'entité « saisonJourDebut »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate saisonJourDebut;
	@JsonIgnore
	public Couverture<LocalDate> saisonJourDebutCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("saisonJourDebut").o(saisonJourDebut);

	/**	<br/>L'entité « saisonJourDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonJourDebut">Trouver l'entité saisonJourDebut dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonJourDebut(Couverture<LocalDate> c);

	public LocalDate getSaisonJourDebut() {
		return saisonJourDebut;
	}

	public void setSaisonJourDebut(LocalDate saisonJourDebut) {
		this.saisonJourDebut = saisonJourDebut;
		this.saisonJourDebutCouverture.dejaInitialise = true;
	}
	public BlocScolaire setSaisonJourDebut(Instant o) {
		this.saisonJourDebut = LocalDate.from(o);
		this.saisonJourDebutCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public BlocScolaire setSaisonJourDebut(String o) {
		this.saisonJourDebut = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.saisonJourDebutCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	public BlocScolaire setSaisonJourDebut(Date o) {
		this.saisonJourDebut = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.saisonJourDebutCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire saisonJourDebutInit() {
		if(!saisonJourDebutCouverture.dejaInitialise) {
			_saisonJourDebut(saisonJourDebutCouverture);
			if(saisonJourDebut == null)
				setSaisonJourDebut(saisonJourDebutCouverture.o);
		}
		saisonJourDebutCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Date solrSaisonJourDebut() {
		return saisonJourDebut == null ? null : Date.from(saisonJourDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSaisonJourDebut() {
		return saisonJourDebut == null ? "" : saisonJourDebut.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageSaisonJourDebut() {
		return "début de l'année";
	}

	public String htmTooltipSaisonJourDebut() {
		return null;
	}

	public String htmSaisonJourDebut() {
		return saisonJourDebut == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonJourDebut());
	}

	public void htmSaisonJourDebut(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SaisonJourDebut\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SaisonJourDebut() {");
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
				r.l("				data: {\"setSaisonJourDebut\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSaisonJourDebut()), "</span>");
				r.s("			<input");
							r.s(" name=\"saisonJourDebut\"");
							r.s(" value=\"", htmSaisonJourDebut(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSaisonJourDebut());
			}
			r.l("</div>");
		}
	}

	///////////////
	// saisonEte //
	///////////////

	/**	L'entité « saisonEte »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean saisonEte;
	@JsonIgnore
	public Couverture<Boolean> saisonEteCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonEte").o(saisonEte);

	/**	<br/>L'entité « saisonEte »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonEte">Trouver l'entité saisonEte dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonEte(Couverture<Boolean> c);

	public Boolean getSaisonEte() {
		return saisonEte;
	}

	public void setSaisonEte(Boolean saisonEte) {
		this.saisonEte = saisonEte;
		this.saisonEteCouverture.dejaInitialise = true;
	}
	public BlocScolaire setSaisonEte(String o) {
		this.saisonEte = Boolean.parseBoolean(o);
		this.saisonEteCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire saisonEteInit() {
		if(!saisonEteCouverture.dejaInitialise) {
			_saisonEte(saisonEteCouverture);
			if(saisonEte == null)
				setSaisonEte(saisonEteCouverture.o);
		}
		saisonEteCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Boolean solrSaisonEte() {
		return saisonEte;
	}

	public String strSaisonEte() {
		return saisonEte == null ? "" : saisonEte.toString();
	}

	public String nomAffichageSaisonEte() {
		return "été";
	}

	public String htmTooltipSaisonEte() {
		return null;
	}

	public String htmSaisonEte() {
		return saisonEte == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonEte());
	}

	public void htmSaisonEte(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SaisonEte\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SaisonEte() {");
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
				r.l("				data: {\"setSaisonEte\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSaisonEte()), "</span>");
				r.s("			<input");
							r.s(" name=\"saisonEte\"");
							r.s(" value=\"", htmSaisonEte(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSaisonEte());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// saisonHiver //
	/////////////////

	/**	L'entité « saisonHiver »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean saisonHiver;
	@JsonIgnore
	public Couverture<Boolean> saisonHiverCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonHiver").o(saisonHiver);

	/**	<br/>L'entité « saisonHiver »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonHiver">Trouver l'entité saisonHiver dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonHiver(Couverture<Boolean> c);

	public Boolean getSaisonHiver() {
		return saisonHiver;
	}

	public void setSaisonHiver(Boolean saisonHiver) {
		this.saisonHiver = saisonHiver;
		this.saisonHiverCouverture.dejaInitialise = true;
	}
	public BlocScolaire setSaisonHiver(String o) {
		this.saisonHiver = Boolean.parseBoolean(o);
		this.saisonHiverCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire saisonHiverInit() {
		if(!saisonHiverCouverture.dejaInitialise) {
			_saisonHiver(saisonHiverCouverture);
			if(saisonHiver == null)
				setSaisonHiver(saisonHiverCouverture.o);
		}
		saisonHiverCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Boolean solrSaisonHiver() {
		return saisonHiver;
	}

	public String strSaisonHiver() {
		return saisonHiver == null ? "" : saisonHiver.toString();
	}

	public String nomAffichageSaisonHiver() {
		return "hiver";
	}

	public String htmTooltipSaisonHiver() {
		return null;
	}

	public String htmSaisonHiver() {
		return saisonHiver == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonHiver());
	}

	public void htmSaisonHiver(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SaisonHiver\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SaisonHiver() {");
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
				r.l("				data: {\"setSaisonHiver\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSaisonHiver()), "</span>");
				r.s("			<input");
							r.s(" name=\"saisonHiver\"");
							r.s(" value=\"", htmSaisonHiver(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSaisonHiver());
			}
			r.l("</div>");
		}
	}

	////////////////////////////
	// saisonFraisInscription //
	////////////////////////////

	/**	L'entité « saisonFraisInscription »
	 *	 is defined as null before being initialized. 
	 */
	protected BigDecimal saisonFraisInscription;
	@JsonIgnore
	public Couverture<BigDecimal> saisonFraisInscriptionCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("saisonFraisInscription").o(saisonFraisInscription);

	/**	<br/>L'entité « saisonFraisInscription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonFraisInscription">Trouver l'entité saisonFraisInscription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonFraisInscription(Couverture<BigDecimal> c);

	public BigDecimal getSaisonFraisInscription() {
		return saisonFraisInscription;
	}

	public void setSaisonFraisInscription(BigDecimal saisonFraisInscription) {
		this.saisonFraisInscription = saisonFraisInscription;
		this.saisonFraisInscriptionCouverture.dejaInitialise = true;
	}
	public BlocScolaire setSaisonFraisInscription(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.saisonFraisInscription = new BigDecimal(o);
		this.saisonFraisInscriptionCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	public BlocScolaire setSaisonFraisInscription(Double o) {
			this.saisonFraisInscription = new BigDecimal(o);
		this.saisonFraisInscriptionCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	public BlocScolaire setSaisonFraisInscription(Integer o) {
			this.saisonFraisInscription = new BigDecimal(o);
		this.saisonFraisInscriptionCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire saisonFraisInscriptionInit() {
		if(!saisonFraisInscriptionCouverture.dejaInitialise) {
			_saisonFraisInscription(saisonFraisInscriptionCouverture);
			if(saisonFraisInscription == null)
				setSaisonFraisInscription(saisonFraisInscriptionCouverture.o);
		}
		saisonFraisInscriptionCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Double solrSaisonFraisInscription() {
		return saisonFraisInscription == null ? null : saisonFraisInscription.doubleValue();
	}

	public String strSaisonFraisInscription() {
		return saisonFraisInscription == null ? "" : saisonFraisInscription.toString();
	}

	public String nomAffichageSaisonFraisInscription() {
		return "frais d'inscription";
	}

	public String htmTooltipSaisonFraisInscription() {
		return null;
	}

	public String htmSaisonFraisInscription() {
		return saisonFraisInscription == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonFraisInscription());
	}

	public void htmSaisonFraisInscription(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SaisonFraisInscription\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SaisonFraisInscription() {");
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
				r.l("				data: {\"setSaisonFraisInscription\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSaisonFraisInscription()), "</span>");
				r.s("			<input");
							r.s(" name=\"saisonFraisInscription\"");
							r.s(" value=\"", htmSaisonFraisInscription(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSaisonFraisInscription());
			}
			r.l("</div>");
		}
	}

	//////////////////////
	// saisonNomComplet //
	//////////////////////

	/**	L'entité « saisonNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String saisonNomComplet;
	@JsonIgnore
	public Couverture<String> saisonNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("saisonNomComplet").o(saisonNomComplet);

	/**	<br/>L'entité « saisonNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonNomComplet">Trouver l'entité saisonNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonNomComplet(Couverture<String> c);

	public String getSaisonNomComplet() {
		return saisonNomComplet;
	}

	public void setSaisonNomComplet(String saisonNomComplet) {
		this.saisonNomComplet = saisonNomComplet;
		this.saisonNomCompletCouverture.dejaInitialise = true;
	}
	protected BlocScolaire saisonNomCompletInit() {
		if(!saisonNomCompletCouverture.dejaInitialise) {
			_saisonNomComplet(saisonNomCompletCouverture);
			if(saisonNomComplet == null)
				setSaisonNomComplet(saisonNomCompletCouverture.o);
		}
		saisonNomCompletCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public String solrSaisonNomComplet() {
		return saisonNomComplet;
	}

	public String strSaisonNomComplet() {
		return saisonNomComplet == null ? "" : saisonNomComplet;
	}

	public String nomAffichageSaisonNomComplet() {
		return null;
	}

	public String htmTooltipSaisonNomComplet() {
		return null;
	}

	public String htmSaisonNomComplet() {
		return saisonNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonNomComplet());
	}

	public void htmSaisonNomComplet(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SaisonNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SaisonNomComplet() {");
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
				r.l("				data: {\"setSaisonNomComplet\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSaisonNomComplet()), "</span>");
				r.s("			<input");
							r.s(" name=\"saisonNomComplet\"");
							r.s(" value=\"", htmSaisonNomComplet(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSaisonNomComplet());
			}
			r.l("</div>");
		}
	}

	///////////////
	// saisonFin //
	///////////////

	/**	L'entité « saisonFin »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate saisonFin;
	@JsonIgnore
	public Couverture<LocalDate> saisonFinCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("saisonFin").o(saisonFin);

	/**	<br/>L'entité « saisonFin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonFin">Trouver l'entité saisonFin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonFin(Couverture<LocalDate> c);

	public LocalDate getSaisonFin() {
		return saisonFin;
	}

	public void setSaisonFin(LocalDate saisonFin) {
		this.saisonFin = saisonFin;
		this.saisonFinCouverture.dejaInitialise = true;
	}
	public BlocScolaire setSaisonFin(Instant o) {
		this.saisonFin = LocalDate.from(o);
		this.saisonFinCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public BlocScolaire setSaisonFin(String o) {
		this.saisonFin = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.saisonFinCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	public BlocScolaire setSaisonFin(Date o) {
		this.saisonFin = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.saisonFinCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire saisonFinInit() {
		if(!saisonFinCouverture.dejaInitialise) {
			_saisonFin(saisonFinCouverture);
			if(saisonFin == null)
				setSaisonFin(saisonFinCouverture.o);
		}
		saisonFinCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Date solrSaisonFin() {
		return saisonFin == null ? null : Date.from(saisonFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSaisonFin() {
		return saisonFin == null ? "" : saisonFin.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageSaisonFin() {
		return "le fin de l'année";
	}

	public String htmTooltipSaisonFin() {
		return null;
	}

	public String htmSaisonFin() {
		return saisonFin == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonFin());
	}

	public void htmSaisonFin(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SaisonFin\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SaisonFin() {");
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
				r.l("				data: {\"setSaisonFin\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSaisonFin()), "</span>");
				r.s("			<input");
							r.s(" name=\"saisonFin\"");
							r.s(" value=\"", htmSaisonFin(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSaisonFin());
			}
			r.l("</div>");
		}
	}

	//////////////////////
	// sessionJourDebut //
	//////////////////////

	/**	L'entité « sessionJourDebut »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate sessionJourDebut;
	@JsonIgnore
	public Couverture<LocalDate> sessionJourDebutCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("sessionJourDebut").o(sessionJourDebut);

	/**	<br/>L'entité « sessionJourDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionJourDebut">Trouver l'entité sessionJourDebut dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionJourDebut(Couverture<LocalDate> c);

	public LocalDate getSessionJourDebut() {
		return sessionJourDebut;
	}

	public void setSessionJourDebut(LocalDate sessionJourDebut) {
		this.sessionJourDebut = sessionJourDebut;
		this.sessionJourDebutCouverture.dejaInitialise = true;
	}
	public BlocScolaire setSessionJourDebut(Instant o) {
		this.sessionJourDebut = LocalDate.from(o);
		this.sessionJourDebutCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public BlocScolaire setSessionJourDebut(String o) {
		this.sessionJourDebut = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionJourDebutCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	public BlocScolaire setSessionJourDebut(Date o) {
		this.sessionJourDebut = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.sessionJourDebutCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire sessionJourDebutInit() {
		if(!sessionJourDebutCouverture.dejaInitialise) {
			_sessionJourDebut(sessionJourDebutCouverture);
			if(sessionJourDebut == null)
				setSessionJourDebut(sessionJourDebutCouverture.o);
		}
		sessionJourDebutCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Date solrSessionJourDebut() {
		return sessionJourDebut == null ? null : Date.from(sessionJourDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSessionJourDebut() {
		return sessionJourDebut == null ? "" : sessionJourDebut.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageSessionJourDebut() {
		return "début de la session";
	}

	public String htmTooltipSessionJourDebut() {
		return null;
	}

	public String htmSessionJourDebut() {
		return sessionJourDebut == null ? "" : StringEscapeUtils.escapeHtml4(strSessionJourDebut());
	}

	public void htmSessionJourDebut(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SessionJourDebut\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SessionJourDebut() {");
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
				r.l("				data: {\"setSessionJourDebut\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionJourDebut()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionJourDebut\"");
							r.s(" value=\"", htmSessionJourDebut(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionJourDebut());
			}
			r.l("</div>");
		}
	}

	////////////////////
	// sessionJourFin //
	////////////////////

	/**	L'entité « sessionJourFin »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate sessionJourFin;
	@JsonIgnore
	public Couverture<LocalDate> sessionJourFinCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("sessionJourFin").o(sessionJourFin);

	/**	<br/>L'entité « sessionJourFin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionJourFin">Trouver l'entité sessionJourFin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionJourFin(Couverture<LocalDate> c);

	public LocalDate getSessionJourFin() {
		return sessionJourFin;
	}

	public void setSessionJourFin(LocalDate sessionJourFin) {
		this.sessionJourFin = sessionJourFin;
		this.sessionJourFinCouverture.dejaInitialise = true;
	}
	public BlocScolaire setSessionJourFin(Instant o) {
		this.sessionJourFin = LocalDate.from(o);
		this.sessionJourFinCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public BlocScolaire setSessionJourFin(String o) {
		this.sessionJourFin = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionJourFinCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	public BlocScolaire setSessionJourFin(Date o) {
		this.sessionJourFin = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.sessionJourFinCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire sessionJourFinInit() {
		if(!sessionJourFinCouverture.dejaInitialise) {
			_sessionJourFin(sessionJourFinCouverture);
			if(sessionJourFin == null)
				setSessionJourFin(sessionJourFinCouverture.o);
		}
		sessionJourFinCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Date solrSessionJourFin() {
		return sessionJourFin == null ? null : Date.from(sessionJourFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSessionJourFin() {
		return sessionJourFin == null ? "" : sessionJourFin.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageSessionJourFin() {
		return "fin de la session";
	}

	public String htmTooltipSessionJourFin() {
		return null;
	}

	public String htmSessionJourFin() {
		return sessionJourFin == null ? "" : StringEscapeUtils.escapeHtml4(strSessionJourFin());
	}

	public void htmSessionJourFin(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SessionJourFin\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SessionJourFin() {");
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
				r.l("				data: {\"setSessionJourFin\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionJourFin()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionJourFin\"");
							r.s(" value=\"", htmSessionJourFin(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionJourFin());
			}
			r.l("</div>");
		}
	}

	///////////////////////
	// sessionNomComplet //
	///////////////////////

	/**	L'entité « sessionNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String sessionNomComplet;
	@JsonIgnore
	public Couverture<String> sessionNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("sessionNomComplet").o(sessionNomComplet);

	/**	<br/>L'entité « sessionNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionNomComplet">Trouver l'entité sessionNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionNomComplet(Couverture<String> c);

	public String getSessionNomComplet() {
		return sessionNomComplet;
	}

	public void setSessionNomComplet(String sessionNomComplet) {
		this.sessionNomComplet = sessionNomComplet;
		this.sessionNomCompletCouverture.dejaInitialise = true;
	}
	protected BlocScolaire sessionNomCompletInit() {
		if(!sessionNomCompletCouverture.dejaInitialise) {
			_sessionNomComplet(sessionNomCompletCouverture);
			if(sessionNomComplet == null)
				setSessionNomComplet(sessionNomCompletCouverture.o);
		}
		sessionNomCompletCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public String solrSessionNomComplet() {
		return sessionNomComplet;
	}

	public String strSessionNomComplet() {
		return sessionNomComplet == null ? "" : sessionNomComplet;
	}

	public String nomAffichageSessionNomComplet() {
		return null;
	}

	public String htmTooltipSessionNomComplet() {
		return null;
	}

	public String htmSessionNomComplet() {
		return sessionNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strSessionNomComplet());
	}

	public void htmSessionNomComplet(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "SessionNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "SessionNomComplet() {");
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
				r.l("				data: {\"setSessionNomComplet\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSessionNomComplet()), "</span>");
				r.s("			<input");
							r.s(" name=\"sessionNomComplet\"");
							r.s(" value=\"", htmSessionNomComplet(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSessionNomComplet());
			}
			r.l("</div>");
		}
	}

	//////////////
	// ageDebut //
	//////////////

	/**	L'entité « ageDebut »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer ageDebut;
	@JsonIgnore
	public Couverture<Integer> ageDebutCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ageDebut").o(ageDebut);

	/**	<br/>L'entité « ageDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageDebut">Trouver l'entité ageDebut dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageDebut(Couverture<Integer> c);

	public Integer getAgeDebut() {
		return ageDebut;
	}

	public void setAgeDebut(Integer ageDebut) {
		this.ageDebut = ageDebut;
		this.ageDebutCouverture.dejaInitialise = true;
	}
	public BlocScolaire setAgeDebut(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ageDebut = Integer.parseInt(o);
		this.ageDebutCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire ageDebutInit() {
		if(!ageDebutCouverture.dejaInitialise) {
			_ageDebut(ageDebutCouverture);
			if(ageDebut == null)
				setAgeDebut(ageDebutCouverture.o);
		}
		ageDebutCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Integer solrAgeDebut() {
		return ageDebut;
	}

	public String strAgeDebut() {
		return ageDebut == null ? "" : ageDebut.toString();
	}

	public String nomAffichageAgeDebut() {
		return "début du groupe d'âge";
	}

	public String htmTooltipAgeDebut() {
		return null;
	}

	public String htmAgeDebut() {
		return ageDebut == null ? "" : StringEscapeUtils.escapeHtml4(strAgeDebut());
	}

	public void htmAgeDebut(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "AgeDebut\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "AgeDebut() {");
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
				r.l("				data: {\"setAgeDebut\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeDebut()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageDebut\"");
							r.s(" value=\"", htmAgeDebut(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeDebut());
			}
			r.l("</div>");
		}
	}

	////////////
	// ageFin //
	////////////

	/**	L'entité « ageFin »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer ageFin;
	@JsonIgnore
	public Couverture<Integer> ageFinCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ageFin").o(ageFin);

	/**	<br/>L'entité « ageFin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageFin">Trouver l'entité ageFin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageFin(Couverture<Integer> c);

	public Integer getAgeFin() {
		return ageFin;
	}

	public void setAgeFin(Integer ageFin) {
		this.ageFin = ageFin;
		this.ageFinCouverture.dejaInitialise = true;
	}
	public BlocScolaire setAgeFin(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ageFin = Integer.parseInt(o);
		this.ageFinCouverture.dejaInitialise = true;
		return (BlocScolaire)this;
	}
	protected BlocScolaire ageFinInit() {
		if(!ageFinCouverture.dejaInitialise) {
			_ageFin(ageFinCouverture);
			if(ageFin == null)
				setAgeFin(ageFinCouverture.o);
		}
		ageFinCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public Integer solrAgeFin() {
		return ageFin;
	}

	public String strAgeFin() {
		return ageFin == null ? "" : ageFin.toString();
	}

	public String nomAffichageAgeFin() {
		return "fin du groupe d'âge";
	}

	public String htmTooltipAgeFin() {
		return null;
	}

	public String htmAgeFin() {
		return ageFin == null ? "" : StringEscapeUtils.escapeHtml4(strAgeFin());
	}

	public void htmAgeFin(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "AgeFin\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "AgeFin() {");
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
				r.l("				data: {\"setAgeFin\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeFin()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageFin\"");
							r.s(" value=\"", htmAgeFin(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeFin());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// ageNomComplet //
	///////////////////

	/**	L'entité « ageNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String ageNomComplet;
	@JsonIgnore
	public Couverture<String> ageNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("ageNomComplet").o(ageNomComplet);

	/**	<br/>L'entité « ageNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageNomComplet">Trouver l'entité ageNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageNomComplet(Couverture<String> c);

	public String getAgeNomComplet() {
		return ageNomComplet;
	}

	public void setAgeNomComplet(String ageNomComplet) {
		this.ageNomComplet = ageNomComplet;
		this.ageNomCompletCouverture.dejaInitialise = true;
	}
	protected BlocScolaire ageNomCompletInit() {
		if(!ageNomCompletCouverture.dejaInitialise) {
			_ageNomComplet(ageNomCompletCouverture);
			if(ageNomComplet == null)
				setAgeNomComplet(ageNomCompletCouverture.o);
		}
		ageNomCompletCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public String solrAgeNomComplet() {
		return ageNomComplet;
	}

	public String strAgeNomComplet() {
		return ageNomComplet == null ? "" : ageNomComplet;
	}

	public String nomAffichageAgeNomComplet() {
		return null;
	}

	public String htmTooltipAgeNomComplet() {
		return null;
	}

	public String htmAgeNomComplet() {
		return ageNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strAgeNomComplet());
	}

	public void htmAgeNomComplet(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "AgeNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "AgeNomComplet() {");
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
				r.l("				data: {\"setAgeNomComplet\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeNomComplet()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageNomComplet\"");
							r.s(" value=\"", htmAgeNomComplet(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeNomComplet());
			}
			r.l("</div>");
		}
	}

	///////////
	// ageId //
	///////////

	/**	L'entité « ageId »
	 *	 is defined as null before being initialized. 
	 */
	protected String ageId;
	@JsonIgnore
	public Couverture<String> ageIdCouverture = new Couverture<String>().p(this).c(String.class).var("ageId").o(ageId);

	/**	<br/>L'entité « ageId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageId">Trouver l'entité ageId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageId(Couverture<String> c);

	public String getAgeId() {
		return ageId;
	}

	public void setAgeId(String ageId) {
		this.ageId = ageId;
		this.ageIdCouverture.dejaInitialise = true;
	}
	protected BlocScolaire ageIdInit() {
		if(!ageIdCouverture.dejaInitialise) {
			_ageId(ageIdCouverture);
			if(ageId == null)
				setAgeId(ageIdCouverture.o);
		}
		ageIdCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public String solrAgeId() {
		return ageId;
	}

	public String strAgeId() {
		return ageId == null ? "" : ageId;
	}

	public String nomAffichageAgeId() {
		return "ID";
	}

	public String htmTooltipAgeId() {
		return null;
	}

	public String htmAgeId() {
		return ageId == null ? "" : StringEscapeUtils.escapeHtml4(strAgeId());
	}

	public void htmAgeId(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "AgeId\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "AgeId() {");
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
				r.l("				data: {\"setAgeId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeId()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageId\"");
							r.s(" value=\"", htmAgeId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeId());
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
	public Couverture<String> pageUrlCouverture = new Couverture<String>().p(this).c(String.class).var("pageUrl").o(pageUrl);

	/**	<br/>L'entité « pageUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUrl(Couverture<String> c);

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
		this.pageUrlCouverture.dejaInitialise = true;
	}
	protected BlocScolaire pageUrlInit() {
		if(!pageUrlCouverture.dejaInitialise) {
			_pageUrl(pageUrlCouverture);
			if(pageUrl == null)
				setPageUrl(pageUrlCouverture.o);
		}
		pageUrlCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
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

	public void htmPageUrl(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "PageUrl\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "PageUrl() {");
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

	//////////////////
	// objetSuggere //
	//////////////////

	/**	L'entité « objetSuggere »
	 *	 is defined as null before being initialized. 
	 */
	protected String objetSuggere;
	@JsonIgnore
	public Couverture<String> objetSuggereCouverture = new Couverture<String>().p(this).c(String.class).var("objetSuggere").o(objetSuggere);

	/**	<br/>L'entité « objetSuggere »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.bloc.BlocScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetSuggere">Trouver l'entité objetSuggere dans Solr</a>
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
	protected BlocScolaire objetSuggereInit() {
		if(!objetSuggereCouverture.dejaInitialise) {
			_objetSuggere(objetSuggereCouverture);
			if(objetSuggere == null)
				setObjetSuggere(objetSuggereCouverture.o);
		}
		objetSuggereCouverture.dejaInitialise(true);
		return (BlocScolaire)this;
	}

	public String solrObjetSuggere() {
		return objetSuggere;
	}

	public String strObjetSuggere() {
		return objetSuggere == null ? "" : objetSuggere;
	}

	public String nomAffichageObjetSuggere() {
		return null;
	}

	public String htmTooltipObjetSuggere() {
		return null;
	}

	public String htmObjetSuggere() {
		return objetSuggere == null ? "" : StringEscapeUtils.escapeHtml4(strObjetSuggere());
	}

	public void htmObjetSuggere(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchBlocScolaire", strPk(), "ObjetSuggere\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchBlocScolaire", strPk(), "ObjetSuggere() {");
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

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseBlocScolaire = false;

	public BlocScolaire initLoinBlocScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseBlocScolaire) {
			dejaInitialiseBlocScolaire = true;
			initLoinBlocScolaire();
		}
		return (BlocScolaire)this;
	}

	public void initLoinBlocScolaire() {
		super.initLoinCluster(requeteSite_);
		initBlocScolaire();
	}

	public void initBlocScolaire() {
		ecoleCleInit();
		anneeCleInit();
		saisonCleInit();
		sessionCleInit();
		ageCleInit();
		blocCleInit();
		inscriptionClesInit();
		scolaireTriInit();
		ecoleTriInit();
		anneeTriInit();
		saisonTriInit();
		sessionTriInit();
		sessionRechercheInit();
		sessionInit();
		ecoleNomCompletInit();
		anneeDebutInit();
		anneeFinInit();
		saisonJourDebutInit();
		saisonEteInit();
		saisonHiverInit();
		saisonFraisInscriptionInit();
		saisonNomCompletInit();
		saisonFinInit();
		sessionJourDebutInit();
		sessionJourFinInit();
		sessionNomCompletInit();
		ageDebutInit();
		ageFinInit();
		ageNomCompletInit();
		ageIdInit();
		pageUrlInit();
		objetSuggereInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinBlocScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteBlocScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(sessionRecherche != null)
			sessionRecherche.setRequeteSite_(requeteSite_);
		if(session != null)
			session.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteBlocScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirBlocScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirBlocScolaire(String var) {
		BlocScolaire oBlocScolaire = (BlocScolaire)this;
		switch(var) {
			case "ecoleCle":
				return oBlocScolaire.ecoleCle;
			case "anneeCle":
				return oBlocScolaire.anneeCle;
			case "saisonCle":
				return oBlocScolaire.saisonCle;
			case "sessionCle":
				return oBlocScolaire.sessionCle;
			case "ageCle":
				return oBlocScolaire.ageCle;
			case "blocCle":
				return oBlocScolaire.blocCle;
			case "inscriptionCles":
				return oBlocScolaire.inscriptionCles;
			case "scolaireTri":
				return oBlocScolaire.scolaireTri;
			case "ecoleTri":
				return oBlocScolaire.ecoleTri;
			case "anneeTri":
				return oBlocScolaire.anneeTri;
			case "saisonTri":
				return oBlocScolaire.saisonTri;
			case "sessionTri":
				return oBlocScolaire.sessionTri;
			case "sessionRecherche":
				return oBlocScolaire.sessionRecherche;
			case "session":
				return oBlocScolaire.session;
			case "ecoleNomComplet":
				return oBlocScolaire.ecoleNomComplet;
			case "anneeDebut":
				return oBlocScolaire.anneeDebut;
			case "anneeFin":
				return oBlocScolaire.anneeFin;
			case "saisonJourDebut":
				return oBlocScolaire.saisonJourDebut;
			case "saisonEte":
				return oBlocScolaire.saisonEte;
			case "saisonHiver":
				return oBlocScolaire.saisonHiver;
			case "saisonFraisInscription":
				return oBlocScolaire.saisonFraisInscription;
			case "saisonNomComplet":
				return oBlocScolaire.saisonNomComplet;
			case "saisonFin":
				return oBlocScolaire.saisonFin;
			case "sessionJourDebut":
				return oBlocScolaire.sessionJourDebut;
			case "sessionJourFin":
				return oBlocScolaire.sessionJourFin;
			case "sessionNomComplet":
				return oBlocScolaire.sessionNomComplet;
			case "ageDebut":
				return oBlocScolaire.ageDebut;
			case "ageFin":
				return oBlocScolaire.ageFin;
			case "ageNomComplet":
				return oBlocScolaire.ageNomComplet;
			case "ageId":
				return oBlocScolaire.ageId;
			case "pageUrl":
				return oBlocScolaire.pageUrl;
			case "objetSuggere":
				return oBlocScolaire.objetSuggere;
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
				o = attribuerBlocScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerBlocScolaire(String var, Object val) {
		BlocScolaire oBlocScolaire = (BlocScolaire)this;
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
					o = definirBlocScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirBlocScolaire(String var, String val) {
		switch(var) {
			case "ageDebut":
				setAgeDebut(val);
				sauvegardesBlocScolaire.add(var);
				return val;
			case "ageFin":
				setAgeFin(val);
				sauvegardesBlocScolaire.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesBlocScolaire = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerBlocScolaire(solrDocument);
	}
	public void peuplerBlocScolaire(SolrDocument solrDocument) {
		BlocScolaire oBlocScolaire = (BlocScolaire)this;
		sauvegardesBlocScolaire = (List<String>)solrDocument.get("sauvegardesBlocScolaire_stored_strings");
		if(sauvegardesBlocScolaire != null) {

			if(sauvegardesBlocScolaire.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oBlocScolaire.setEcoleCle(ecoleCle);
			}

			if(sauvegardesBlocScolaire.contains("saisonCle")) {
				Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
				if(saisonCle != null)
					oBlocScolaire.setSaisonCle(saisonCle);
			}

			if(sauvegardesBlocScolaire.contains("sessionCle")) {
				Long sessionCle = (Long)solrDocument.get("sessionCle_stored_long");
				if(sessionCle != null)
					oBlocScolaire.setSessionCle(sessionCle);
			}

			if(sauvegardesBlocScolaire.contains("ageCle")) {
				Long ageCle = (Long)solrDocument.get("ageCle_stored_long");
				if(ageCle != null)
					oBlocScolaire.setAgeCle(ageCle);
			}

			if(sauvegardesBlocScolaire.contains("blocCle")) {
				Long blocCle = (Long)solrDocument.get("blocCle_stored_long");
				if(blocCle != null)
					oBlocScolaire.setBlocCle(blocCle);
			}

			if(sauvegardesBlocScolaire.contains("inscriptionCles")) {
				List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
				if(inscriptionCles != null)
					oBlocScolaire.inscriptionCles.addAll(inscriptionCles);
			}

			if(sauvegardesBlocScolaire.contains("scolaireTri")) {
				Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
				if(scolaireTri != null)
					oBlocScolaire.setScolaireTri(scolaireTri);
			}

			if(sauvegardesBlocScolaire.contains("ecoleTri")) {
				Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
				if(ecoleTri != null)
					oBlocScolaire.setEcoleTri(ecoleTri);
			}

			if(sauvegardesBlocScolaire.contains("anneeTri")) {
				Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
				if(anneeTri != null)
					oBlocScolaire.setAnneeTri(anneeTri);
			}

			if(sauvegardesBlocScolaire.contains("saisonTri")) {
				Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
				if(saisonTri != null)
					oBlocScolaire.setSaisonTri(saisonTri);
			}

			if(sauvegardesBlocScolaire.contains("sessionTri")) {
				Integer sessionTri = (Integer)solrDocument.get("sessionTri_stored_int");
				if(sessionTri != null)
					oBlocScolaire.setSessionTri(sessionTri);
			}

			if(sauvegardesBlocScolaire.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oBlocScolaire.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardesBlocScolaire.contains("anneeDebut")) {
				Date anneeDebut = (Date)solrDocument.get("anneeDebut_stored_date");
				if(anneeDebut != null)
					oBlocScolaire.setAnneeDebut(anneeDebut);
			}

			if(sauvegardesBlocScolaire.contains("anneeFin")) {
				Date anneeFin = (Date)solrDocument.get("anneeFin_stored_date");
				if(anneeFin != null)
					oBlocScolaire.setAnneeFin(anneeFin);
			}

			if(sauvegardesBlocScolaire.contains("saisonJourDebut")) {
				Date saisonJourDebut = (Date)solrDocument.get("saisonJourDebut_stored_date");
				if(saisonJourDebut != null)
					oBlocScolaire.setSaisonJourDebut(saisonJourDebut);
			}

			if(sauvegardesBlocScolaire.contains("saisonEte")) {
				Boolean saisonEte = (Boolean)solrDocument.get("saisonEte_stored_boolean");
				if(saisonEte != null)
					oBlocScolaire.setSaisonEte(saisonEte);
			}

			if(sauvegardesBlocScolaire.contains("saisonHiver")) {
				Boolean saisonHiver = (Boolean)solrDocument.get("saisonHiver_stored_boolean");
				if(saisonHiver != null)
					oBlocScolaire.setSaisonHiver(saisonHiver);
			}

			if(sauvegardesBlocScolaire.contains("saisonFraisInscription")) {
				Double saisonFraisInscription = (Double)solrDocument.get("saisonFraisInscription_stored_double");
				if(saisonFraisInscription != null)
					oBlocScolaire.setSaisonFraisInscription(saisonFraisInscription);
			}

			if(sauvegardesBlocScolaire.contains("saisonNomComplet")) {
				String saisonNomComplet = (String)solrDocument.get("saisonNomComplet_stored_string");
				if(saisonNomComplet != null)
					oBlocScolaire.setSaisonNomComplet(saisonNomComplet);
			}

			if(sauvegardesBlocScolaire.contains("saisonFin")) {
				Date saisonFin = (Date)solrDocument.get("saisonFin_stored_date");
				if(saisonFin != null)
					oBlocScolaire.setSaisonFin(saisonFin);
			}

			if(sauvegardesBlocScolaire.contains("sessionJourDebut")) {
				Date sessionJourDebut = (Date)solrDocument.get("sessionJourDebut_stored_date");
				if(sessionJourDebut != null)
					oBlocScolaire.setSessionJourDebut(sessionJourDebut);
			}

			if(sauvegardesBlocScolaire.contains("sessionJourFin")) {
				Date sessionJourFin = (Date)solrDocument.get("sessionJourFin_stored_date");
				if(sessionJourFin != null)
					oBlocScolaire.setSessionJourFin(sessionJourFin);
			}

			if(sauvegardesBlocScolaire.contains("sessionNomComplet")) {
				String sessionNomComplet = (String)solrDocument.get("sessionNomComplet_stored_string");
				if(sessionNomComplet != null)
					oBlocScolaire.setSessionNomComplet(sessionNomComplet);
			}

			if(sauvegardesBlocScolaire.contains("ageDebut")) {
				Integer ageDebut = (Integer)solrDocument.get("ageDebut_stored_int");
				if(ageDebut != null)
					oBlocScolaire.setAgeDebut(ageDebut);
			}

			if(sauvegardesBlocScolaire.contains("ageFin")) {
				Integer ageFin = (Integer)solrDocument.get("ageFin_stored_int");
				if(ageFin != null)
					oBlocScolaire.setAgeFin(ageFin);
			}

			if(sauvegardesBlocScolaire.contains("ageNomComplet")) {
				String ageNomComplet = (String)solrDocument.get("ageNomComplet_stored_string");
				if(ageNomComplet != null)
					oBlocScolaire.setAgeNomComplet(ageNomComplet);
			}

			if(sauvegardesBlocScolaire.contains("ageId")) {
				String ageId = (String)solrDocument.get("ageId_stored_string");
				if(ageId != null)
					oBlocScolaire.setAgeId(ageId);
			}

			if(sauvegardesBlocScolaire.contains("pageUrl")) {
				String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
				if(pageUrl != null)
					oBlocScolaire.setPageUrl(pageUrl);
			}

			if(sauvegardesBlocScolaire.contains("objetSuggere")) {
				String objetSuggere = (String)solrDocument.get("objetSuggere_stored_string");
				if(objetSuggere != null)
					oBlocScolaire.setObjetSuggere(objetSuggere);
			}
		}

		super.peuplerCluster(solrDocument);
	}

	/////////////
	// indexer //
	/////////////

	public static void indexer() {
		try {
			RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.getConfigSite().setConfigChemin("/usr/local/src/computate-scolaire/config/computate-scolaire.config");
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			SolrQuery rechercheSolr = new SolrQuery();
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.bloc.BlocScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			BlocScolaire o = new BlocScolaire();
			o.requeteSiteBlocScolaire(requeteSite);
			o.initLoinBlocScolaire(requeteSite);
			o.indexerBlocScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerBlocScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerBlocScolaire(document);
	}

	public void indexerBlocScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerBlocScolaire(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerBlocScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerBlocScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerBlocScolaire(SolrInputDocument document) {
		if(sauvegardesBlocScolaire != null)
			document.addField("sauvegardesBlocScolaire_stored_strings", sauvegardesBlocScolaire);

		if(ecoleCle != null) {
			document.addField("ecoleCle_indexed_long", ecoleCle);
			document.addField("ecoleCle_stored_long", ecoleCle);
		}
		if(saisonCle != null) {
			document.addField("saisonCle_indexed_long", saisonCle);
			document.addField("saisonCle_stored_long", saisonCle);
		}
		if(sessionCle != null) {
			document.addField("sessionCle_indexed_long", sessionCle);
			document.addField("sessionCle_stored_long", sessionCle);
		}
		if(ageCle != null) {
			document.addField("ageCle_indexed_long", ageCle);
			document.addField("ageCle_stored_long", ageCle);
		}
		if(blocCle != null) {
			document.addField("blocCle_indexed_long", blocCle);
			document.addField("blocCle_stored_long", blocCle);
		}
		if(inscriptionCles != null) {
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_indexed_longs", o);
			}
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_stored_longs", o);
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
		if(anneeTri != null) {
			document.addField("anneeTri_indexed_int", anneeTri);
			document.addField("anneeTri_stored_int", anneeTri);
		}
		if(saisonTri != null) {
			document.addField("saisonTri_indexed_int", saisonTri);
			document.addField("saisonTri_stored_int", saisonTri);
		}
		if(sessionTri != null) {
			document.addField("sessionTri_indexed_int", sessionTri);
			document.addField("sessionTri_stored_int", sessionTri);
		}
		if(ecoleNomComplet != null) {
			document.addField("ecoleNomComplet_indexed_string", ecoleNomComplet);
			document.addField("ecoleNomComplet_stored_string", ecoleNomComplet);
		}
		if(anneeDebut != null) {
			document.addField("anneeDebut_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(anneeDebut.atStartOfDay(ZoneId.of("Z"))));
			document.addField("anneeDebut_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(anneeDebut.atStartOfDay(ZoneId.of("Z"))));
		}
		if(anneeFin != null) {
			document.addField("anneeFin_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(anneeFin.atStartOfDay(ZoneId.of("Z"))));
			document.addField("anneeFin_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(anneeFin.atStartOfDay(ZoneId.of("Z"))));
		}
		if(saisonJourDebut != null) {
			document.addField("saisonJourDebut_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(saisonJourDebut.atStartOfDay(ZoneId.of("Z"))));
			document.addField("saisonJourDebut_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(saisonJourDebut.atStartOfDay(ZoneId.of("Z"))));
		}
		if(saisonEte != null) {
			document.addField("saisonEte_indexed_boolean", saisonEte);
			document.addField("saisonEte_stored_boolean", saisonEte);
		}
		if(saisonHiver != null) {
			document.addField("saisonHiver_indexed_boolean", saisonHiver);
			document.addField("saisonHiver_stored_boolean", saisonHiver);
		}
		if(saisonFraisInscription != null) {
			document.addField("saisonFraisInscription_indexed_double", saisonFraisInscription);
			document.addField("saisonFraisInscription_stored_double", saisonFraisInscription);
		}
		if(saisonNomComplet != null) {
			document.addField("saisonNomComplet_indexed_string", saisonNomComplet);
			document.addField("saisonNomComplet_stored_string", saisonNomComplet);
		}
		if(saisonFin != null) {
			document.addField("saisonFin_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(saisonFin.atStartOfDay(ZoneId.of("Z"))));
			document.addField("saisonFin_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(saisonFin.atStartOfDay(ZoneId.of("Z"))));
		}
		if(sessionJourDebut != null) {
			document.addField("sessionJourDebut_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(sessionJourDebut.atStartOfDay(ZoneId.of("Z"))));
			document.addField("sessionJourDebut_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(sessionJourDebut.atStartOfDay(ZoneId.of("Z"))));
		}
		if(sessionJourFin != null) {
			document.addField("sessionJourFin_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(sessionJourFin.atStartOfDay(ZoneId.of("Z"))));
			document.addField("sessionJourFin_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(sessionJourFin.atStartOfDay(ZoneId.of("Z"))));
		}
		if(sessionNomComplet != null) {
			document.addField("sessionNomComplet_indexed_string", sessionNomComplet);
			document.addField("sessionNomComplet_stored_string", sessionNomComplet);
		}
		if(ageDebut != null) {
			document.addField("ageDebut_indexed_int", ageDebut);
			document.addField("ageDebut_stored_int", ageDebut);
		}
		if(ageFin != null) {
			document.addField("ageFin_indexed_int", ageFin);
			document.addField("ageFin_stored_int", ageFin);
		}
		if(ageNomComplet != null) {
			document.addField("ageNomComplet_indexed_string", ageNomComplet);
			document.addField("ageNomComplet_stored_string", ageNomComplet);
		}
		if(ageId != null) {
			document.addField("ageId_indexed_string", ageId);
			document.addField("ageId_stored_string", ageId);
		}
		if(pageUrl != null) {
			document.addField("pageUrl_indexed_string", pageUrl);
			document.addField("pageUrl_stored_string", pageUrl);
		}
		if(objetSuggere != null) {
			document.addField("objetSuggere_suggested", objetSuggere);
			document.addField("objetSuggere_indexed_string", objetSuggere);
		}
		super.indexerCluster(document);

	}

	public void desindexerBlocScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinBlocScolaire(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerBlocScolaire(solrDocument);
	}
	public void stockerBlocScolaire(SolrDocument solrDocument) {
		BlocScolaire oBlocScolaire = (BlocScolaire)this;

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oBlocScolaire.setEcoleCle(ecoleCle);

		Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
		if(saisonCle != null)
			oBlocScolaire.setSaisonCle(saisonCle);

		Long sessionCle = (Long)solrDocument.get("sessionCle_stored_long");
		if(sessionCle != null)
			oBlocScolaire.setSessionCle(sessionCle);

		Long ageCle = (Long)solrDocument.get("ageCle_stored_long");
		if(ageCle != null)
			oBlocScolaire.setAgeCle(ageCle);

		Long blocCle = (Long)solrDocument.get("blocCle_stored_long");
		if(blocCle != null)
			oBlocScolaire.setBlocCle(blocCle);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oBlocScolaire.inscriptionCles.addAll(inscriptionCles);

		Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
		if(scolaireTri != null)
			oBlocScolaire.setScolaireTri(scolaireTri);

		Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
		if(ecoleTri != null)
			oBlocScolaire.setEcoleTri(ecoleTri);

		Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
		if(anneeTri != null)
			oBlocScolaire.setAnneeTri(anneeTri);

		Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
		if(saisonTri != null)
			oBlocScolaire.setSaisonTri(saisonTri);

		Integer sessionTri = (Integer)solrDocument.get("sessionTri_stored_int");
		if(sessionTri != null)
			oBlocScolaire.setSessionTri(sessionTri);

		String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
		if(ecoleNomComplet != null)
			oBlocScolaire.setEcoleNomComplet(ecoleNomComplet);

		Date anneeDebut = (Date)solrDocument.get("anneeDebut_stored_date");
		if(anneeDebut != null)
			oBlocScolaire.setAnneeDebut(anneeDebut);

		Date anneeFin = (Date)solrDocument.get("anneeFin_stored_date");
		if(anneeFin != null)
			oBlocScolaire.setAnneeFin(anneeFin);

		Date saisonJourDebut = (Date)solrDocument.get("saisonJourDebut_stored_date");
		if(saisonJourDebut != null)
			oBlocScolaire.setSaisonJourDebut(saisonJourDebut);

		Boolean saisonEte = (Boolean)solrDocument.get("saisonEte_stored_boolean");
		if(saisonEte != null)
			oBlocScolaire.setSaisonEte(saisonEte);

		Boolean saisonHiver = (Boolean)solrDocument.get("saisonHiver_stored_boolean");
		if(saisonHiver != null)
			oBlocScolaire.setSaisonHiver(saisonHiver);

		Double saisonFraisInscription = (Double)solrDocument.get("saisonFraisInscription_stored_double");
		if(saisonFraisInscription != null)
			oBlocScolaire.setSaisonFraisInscription(saisonFraisInscription);

		String saisonNomComplet = (String)solrDocument.get("saisonNomComplet_stored_string");
		if(saisonNomComplet != null)
			oBlocScolaire.setSaisonNomComplet(saisonNomComplet);

		Date saisonFin = (Date)solrDocument.get("saisonFin_stored_date");
		if(saisonFin != null)
			oBlocScolaire.setSaisonFin(saisonFin);

		Date sessionJourDebut = (Date)solrDocument.get("sessionJourDebut_stored_date");
		if(sessionJourDebut != null)
			oBlocScolaire.setSessionJourDebut(sessionJourDebut);

		Date sessionJourFin = (Date)solrDocument.get("sessionJourFin_stored_date");
		if(sessionJourFin != null)
			oBlocScolaire.setSessionJourFin(sessionJourFin);

		String sessionNomComplet = (String)solrDocument.get("sessionNomComplet_stored_string");
		if(sessionNomComplet != null)
			oBlocScolaire.setSessionNomComplet(sessionNomComplet);

		Integer ageDebut = (Integer)solrDocument.get("ageDebut_stored_int");
		if(ageDebut != null)
			oBlocScolaire.setAgeDebut(ageDebut);

		Integer ageFin = (Integer)solrDocument.get("ageFin_stored_int");
		if(ageFin != null)
			oBlocScolaire.setAgeFin(ageFin);

		String ageNomComplet = (String)solrDocument.get("ageNomComplet_stored_string");
		if(ageNomComplet != null)
			oBlocScolaire.setAgeNomComplet(ageNomComplet);

		String ageId = (String)solrDocument.get("ageId_stored_string");
		if(ageId != null)
			oBlocScolaire.setAgeId(ageId);

		String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
		if(pageUrl != null)
			oBlocScolaire.setPageUrl(pageUrl);

		String objetSuggere = (String)solrDocument.get("objetSuggere_stored_string");
		if(objetSuggere != null)
			oBlocScolaire.setObjetSuggere(objetSuggere);

		super.stockerCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), ageDebut, ageFin);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof BlocScolaire))
			return false;
		BlocScolaire that = (BlocScolaire)o;
		return super.equals(o)
				&& Objects.equals( ageDebut, that.ageDebut )
				&& Objects.equals( ageFin, that.ageFin );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("BlocScolaire { ");
		sb.append( "ageDebut: " ).append(ageDebut);
		sb.append( ", ageFin: " ).append(ageFin);
		sb.append(" }");
		return sb.toString();
	}
}
