package org.computate.scolaire.frFR.annee;

import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.ecole.Ecole;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import java.lang.Long;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe objectSuggest dans Solr</a>
 * <br/>
 **/
public abstract class AnneeScolaireGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnneeScolaire.class);

	public static final String AnneeScolaire_UnNom = "une année";
	public static final String AnneeScolaire_Ce = "cette ";
	public static final String AnneeScolaire_CeNom = "cette année";
	public static final String AnneeScolaire_Un = "une ";
	public static final String AnneeScolaire_LeNom = "l'année";
	public static final String AnneeScolaire_NomSingulier = "année";
	public static final String AnneeScolaire_NomPluriel = "années";
	public static final String AnneeScolaire_NomActuel = "année actuelle";
	public static final String AnneeScolaire_TousNom = "les années";
	public static final String AnneeScolaire_RechercherTousNomPar = "rechercher années par ";
	public static final String AnneeScolaire_RechercherTousNom = "rechercher années";
	public static final String AnneeScolaire_LesNoms = "les années";
	public static final String AnneeScolaire_AucunNomTrouve = "aucune année trouvée";
	public static final String AnneeScolaire_NomVar = "année";
	public static final String AnneeScolaire_DeNom = "d'année";
	public static final String AnneeScolaire_Couleur = "orange";
	public static final String AnneeScolaire_IconeGroupe = "duotone";
	public static final String AnneeScolaire_IconeNom = "calendar-check-o";

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
	public AnneeScolaire setEcoleCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.ecoleCle = Long.parseLong(o);
		this.ecoleCleCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
			r.s("<div id=\"patchAnneeScolaire", strPk(), "EcoleCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "EcoleCle() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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
	public AnneeScolaire setAnneeCle(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.anneeCle = Long.parseLong(o);
		this.anneeCleCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire anneeCleInit() {
		if(!anneeCleCouverture.dejaInitialise) {
			_anneeCle(anneeCleCouverture);
			if(anneeCle == null)
				setAnneeCle(anneeCleCouverture.o);
		}
		anneeCleCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
	}

	public Long solrAnneeCle() {
		return anneeCle;
	}

	public String strAnneeCle() {
		return anneeCle == null ? "" : anneeCle.toString();
	}

	public String nomAffichageAnneeCle() {
		return "clé";
	}

	public String htmTooltipAnneeCle() {
		return null;
	}

	public String htmAnneeCle() {
		return anneeCle == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCle());
	}

	public void htmAnneeCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchAnneeScolaire", strPk(), "AnneeCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "AnneeCle() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
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
	public AnneeScolaire addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (AnneeScolaire)this;
	}
	public AnneeScolaire addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (AnneeScolaire)this;
	}
	public AnneeScolaire setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
		return (AnneeScolaire)this;
	}
	public AnneeScolaire addInscriptionCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
			r.s("<div id=\"patchAnneeScolaire", strPk(), "InscriptionCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "InscriptionCles() {");
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

	////////////////
	// saisonCles //
	////////////////

	/**	L'entité « saisonCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> saisonCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> saisonClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("saisonCles").o(saisonCles);

	/**	<br/>L'entité « saisonCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCles">Trouver l'entité saisonCles dans Solr</a>
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
	public AnneeScolaire addSaisonCles(Long...objets) {
		for(Long o : objets) {
			addSaisonCles(o);
		}
		return (AnneeScolaire)this;
	}
	public AnneeScolaire addSaisonCles(Long o) {
		if(o != null && !saisonCles.contains(o))
			this.saisonCles.add(o);
		return (AnneeScolaire)this;
	}
	public AnneeScolaire setSaisonCles(JsonArray objets) {
		saisonCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSaisonCles(o);
		}
		return (AnneeScolaire)this;
	}
	public AnneeScolaire addSaisonCles(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {
			Long p = Long.parseLong(o);
			addSaisonCles(p);
		}
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire saisonClesInit() {
		if(!saisonClesCouverture.dejaInitialise) {
			_saisonCles(saisonCles);
		}
		saisonClesCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
	}

	public List<Long> solrSaisonCles() {
		return saisonCles;
	}

	public String strSaisonCles() {
		return saisonCles == null ? "" : saisonCles.toString();
	}

	public String nomAffichageSaisonCles() {
		return null;
	}

	public String htmTooltipSaisonCles() {
		return null;
	}

	public String htmSaisonCles() {
		return saisonCles == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonCles());
	}

	public void htmSaisonCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchAnneeScolaire", strPk(), "SaisonCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "SaisonCles() {");
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

	////////////////////
	// ecoleRecherche //
	////////////////////

	/**	L'entité « ecoleRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<Ecole>(). 
	 */
	@JsonIgnore
	protected ListeRecherche<Ecole> ecoleRecherche = new ListeRecherche<Ecole>();
	@JsonIgnore
	public Couverture<ListeRecherche<Ecole>> ecoleRechercheCouverture = new Couverture<ListeRecherche<Ecole>>().p(this).c(ListeRecherche.class).var("ecoleRecherche").o(ecoleRecherche);

	/**	<br/>L'entité « ecoleRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<Ecole>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleRecherche">Trouver l'entité ecoleRecherche dans Solr</a>
	 * <br/>
	 * @param ecoleRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _ecoleRecherche(ListeRecherche<Ecole> l);

	public ListeRecherche<Ecole> getEcoleRecherche() {
		return ecoleRecherche;
	}

	public void setEcoleRecherche(ListeRecherche<Ecole> ecoleRecherche) {
		this.ecoleRecherche = ecoleRecherche;
		this.ecoleRechercheCouverture.dejaInitialise = true;
	}
	protected AnneeScolaire ecoleRechercheInit() {
		if(!ecoleRechercheCouverture.dejaInitialise) {
			_ecoleRecherche(ecoleRecherche);
		}
		ecoleRecherche.initLoinPourClasse(requeteSite_);
		ecoleRechercheCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
	}

	///////////////////
	// ecoleDocument //
	///////////////////

	/**	L'entité « ecoleDocument »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected SolrDocument ecoleDocument;
	@JsonIgnore
	public Couverture<SolrDocument> ecoleDocumentCouverture = new Couverture<SolrDocument>().p(this).c(SolrDocument.class).var("ecoleDocument").o(ecoleDocument);

	/**	<br/>L'entité « ecoleDocument »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleDocument">Trouver l'entité ecoleDocument dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleDocument(Couverture<SolrDocument> c);

	public SolrDocument getEcoleDocument() {
		return ecoleDocument;
	}

	public void setEcoleDocument(SolrDocument ecoleDocument) {
		this.ecoleDocument = ecoleDocument;
		this.ecoleDocumentCouverture.dejaInitialise = true;
	}
	protected AnneeScolaire ecoleDocumentInit() {
		if(!ecoleDocumentCouverture.dejaInitialise) {
			_ecoleDocument(ecoleDocumentCouverture);
			if(ecoleDocument == null)
				setEcoleDocument(ecoleDocumentCouverture.o);
		}
		ecoleDocumentCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
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
	protected AnneeScolaire ecoleNomCompletInit() {
		if(!ecoleNomCompletCouverture.dejaInitialise) {
			_ecoleNomComplet(ecoleNomCompletCouverture);
			if(ecoleNomComplet == null)
				setEcoleNomComplet(ecoleNomCompletCouverture.o);
		}
		ecoleNomCompletCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
			r.s("<div id=\"patchAnneeScolaire", strPk(), "EcoleNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "EcoleNomComplet() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
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
	public AnneeScolaire setAnneeDebut(Instant o) {
		this.anneeDebut = LocalDate.from(o);
		this.anneeDebutCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public AnneeScolaire setAnneeDebut(String o) {
		this.anneeDebut = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.anneeDebutCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	public AnneeScolaire setAnneeDebut(Date o) {
		this.anneeDebut = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.anneeDebutCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire anneeDebutInit() {
		if(!anneeDebutCouverture.dejaInitialise) {
			_anneeDebut(anneeDebutCouverture);
			if(anneeDebut == null)
				setAnneeDebut(anneeDebutCouverture.o);
		}
		anneeDebutCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
	}

	public Date solrAnneeDebut() {
		return anneeDebut == null ? null : Date.from(anneeDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strAnneeDebut() {
		return anneeDebut == null ? "" : anneeDebut.toString();
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
			r.s("<div id=\"patchAnneeScolaire", strPk(), "AnneeDebut\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "AnneeDebut() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
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
	public AnneeScolaire setAnneeFin(Instant o) {
		this.anneeFin = LocalDate.from(o);
		this.anneeFinCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public AnneeScolaire setAnneeFin(String o) {
		this.anneeFin = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.anneeFinCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	public AnneeScolaire setAnneeFin(Date o) {
		this.anneeFin = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.anneeFinCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire anneeFinInit() {
		if(!anneeFinCouverture.dejaInitialise) {
			_anneeFin(anneeFinCouverture);
			if(anneeFin == null)
				setAnneeFin(anneeFinCouverture.o);
		}
		anneeFinCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
	}

	public Date solrAnneeFin() {
		return anneeFin == null ? null : Date.from(anneeFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strAnneeFin() {
		return anneeFin == null ? "" : anneeFin.toString();
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
			r.s("<div id=\"patchAnneeScolaire", strPk(), "AnneeFin\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "AnneeFin() {");
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

	///////////////////
	// anneeNomCourt //
	///////////////////

	/**	L'entité « anneeNomCourt »
	 *	 is defined as null before being initialized. 
	 */
	protected String anneeNomCourt;
	@JsonIgnore
	public Couverture<String> anneeNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("anneeNomCourt").o(anneeNomCourt);

	/**	<br/>L'entité « anneeNomCourt »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeNomCourt">Trouver l'entité anneeNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeNomCourt(Couverture<String> c);

	public String getAnneeNomCourt() {
		return anneeNomCourt;
	}

	public void setAnneeNomCourt(String anneeNomCourt) {
		this.anneeNomCourt = anneeNomCourt;
		this.anneeNomCourtCouverture.dejaInitialise = true;
	}
	protected AnneeScolaire anneeNomCourtInit() {
		if(!anneeNomCourtCouverture.dejaInitialise) {
			_anneeNomCourt(anneeNomCourtCouverture);
			if(anneeNomCourt == null)
				setAnneeNomCourt(anneeNomCourtCouverture.o);
		}
		anneeNomCourtCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
	}

	public String solrAnneeNomCourt() {
		return anneeNomCourt;
	}

	public String strAnneeNomCourt() {
		return anneeNomCourt == null ? "" : anneeNomCourt;
	}

	public String nomAffichageAnneeNomCourt() {
		return null;
	}

	public String htmTooltipAnneeNomCourt() {
		return null;
	}

	public String htmAnneeNomCourt() {
		return anneeNomCourt == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeNomCourt());
	}

	public void htmAnneeNomCourt(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchAnneeScolaire", strPk(), "AnneeNomCourt\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "AnneeNomCourt() {");
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
				r.l("				data: {\"setAnneeNomCourt\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAnneeNomCourt()), "</span>");
				r.s("			<input");
							r.s(" name=\"anneeNomCourt\"");
							r.s(" value=\"", htmAnneeNomCourt(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAnneeNomCourt());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// anneeNomComplet //
	/////////////////////

	/**	L'entité « anneeNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String anneeNomComplet;
	@JsonIgnore
	public Couverture<String> anneeNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("anneeNomComplet").o(anneeNomComplet);

	/**	<br/>L'entité « anneeNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeNomComplet">Trouver l'entité anneeNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeNomComplet(Couverture<String> c);

	public String getAnneeNomComplet() {
		return anneeNomComplet;
	}

	public void setAnneeNomComplet(String anneeNomComplet) {
		this.anneeNomComplet = anneeNomComplet;
		this.anneeNomCompletCouverture.dejaInitialise = true;
	}
	protected AnneeScolaire anneeNomCompletInit() {
		if(!anneeNomCompletCouverture.dejaInitialise) {
			_anneeNomComplet(anneeNomCompletCouverture);
			if(anneeNomComplet == null)
				setAnneeNomComplet(anneeNomCompletCouverture.o);
		}
		anneeNomCompletCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
	}

	public String solrAnneeNomComplet() {
		return anneeNomComplet;
	}

	public String strAnneeNomComplet() {
		return anneeNomComplet == null ? "" : anneeNomComplet;
	}

	public String nomAffichageAnneeNomComplet() {
		return null;
	}

	public String htmTooltipAnneeNomComplet() {
		return null;
	}

	public String htmAnneeNomComplet() {
		return anneeNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeNomComplet());
	}

	public void htmAnneeNomComplet(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchAnneeScolaire", strPk(), "AnneeNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "AnneeNomComplet() {");
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
				r.l("				data: {\"setAnneeNomComplet\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAnneeNomComplet()), "</span>");
				r.s("			<input");
							r.s(" name=\"anneeNomComplet\"");
							r.s(" value=\"", htmAnneeNomComplet(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAnneeNomComplet());
			}
			r.l("</div>");
		}
	}

	/////////////
	// anneeId //
	/////////////

	/**	L'entité « anneeId »
	 *	 is defined as null before being initialized. 
	 */
	protected String anneeId;
	@JsonIgnore
	public Couverture<String> anneeIdCouverture = new Couverture<String>().p(this).c(String.class).var("anneeId").o(anneeId);

	/**	<br/>L'entité « anneeId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeId">Trouver l'entité anneeId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeId(Couverture<String> c);

	public String getAnneeId() {
		return anneeId;
	}

	public void setAnneeId(String anneeId) {
		this.anneeId = anneeId;
		this.anneeIdCouverture.dejaInitialise = true;
	}
	protected AnneeScolaire anneeIdInit() {
		if(!anneeIdCouverture.dejaInitialise) {
			_anneeId(anneeIdCouverture);
			if(anneeId == null)
				setAnneeId(anneeIdCouverture.o);
		}
		anneeIdCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
	}

	public String solrAnneeId() {
		return anneeId;
	}

	public String strAnneeId() {
		return anneeId == null ? "" : anneeId;
	}

	public String nomAffichageAnneeId() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipAnneeId() {
		return null;
	}

	public String htmAnneeId() {
		return anneeId == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeId());
	}

	public void htmAnneeId(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchAnneeScolaire", strPk(), "AnneeId\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "AnneeId() {");
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
				r.l("				data: {\"setAnneeId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAnneeId()), "</span>");
				r.s("			<input");
							r.s(" name=\"anneeId\"");
							r.s(" value=\"", htmAnneeId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAnneeId());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
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
	protected AnneeScolaire pageUrlInit() {
		if(!pageUrlCouverture.dejaInitialise) {
			_pageUrl(pageUrlCouverture);
			if(pageUrl == null)
				setPageUrl(pageUrlCouverture.o);
		}
		pageUrlCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
			r.s("<div id=\"patchAnneeScolaire", strPk(), "PageUrl\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "PageUrl() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetSuggere">Trouver l'entité objetSuggere dans Solr</a>
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
	protected AnneeScolaire objetSuggereInit() {
		if(!objetSuggereCouverture.dejaInitialise) {
			_objetSuggere(objetSuggereCouverture);
			if(objetSuggere == null)
				setObjetSuggere(objetSuggereCouverture.o);
		}
		objetSuggereCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
			r.s("<div id=\"patchAnneeScolaire", strPk(), "ObjetSuggere\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchAnneeScolaire", strPk(), "ObjetSuggere() {");
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

	protected boolean dejaInitialiseAnneeScolaire = false;

	public AnneeScolaire initLoinAnneeScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseAnneeScolaire) {
			dejaInitialiseAnneeScolaire = true;
			initLoinAnneeScolaire();
		}
		return (AnneeScolaire)this;
	}

	public void initLoinAnneeScolaire() {
		super.initLoinCluster(requeteSite_);
		initAnneeScolaire();
	}

	public void initAnneeScolaire() {
		ecoleCleInit();
		anneeCleInit();
		inscriptionClesInit();
		saisonClesInit();
		ecoleRechercheInit();
		ecoleDocumentInit();
		ecoleNomCompletInit();
		anneeDebutInit();
		anneeFinInit();
		anneeNomCourtInit();
		anneeNomCompletInit();
		anneeIdInit();
		pageUrlInit();
		objetSuggereInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinAnneeScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteAnneeScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(ecoleRecherche != null)
			ecoleRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteAnneeScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAnneeScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirAnneeScolaire(String var) {
		AnneeScolaire oAnneeScolaire = (AnneeScolaire)this;
		switch(var) {
			case "ecoleCle":
				return oAnneeScolaire.ecoleCle;
			case "anneeCle":
				return oAnneeScolaire.anneeCle;
			case "inscriptionCles":
				return oAnneeScolaire.inscriptionCles;
			case "saisonCles":
				return oAnneeScolaire.saisonCles;
			case "ecoleRecherche":
				return oAnneeScolaire.ecoleRecherche;
			case "ecoleDocument":
				return oAnneeScolaire.ecoleDocument;
			case "ecoleNomComplet":
				return oAnneeScolaire.ecoleNomComplet;
			case "anneeDebut":
				return oAnneeScolaire.anneeDebut;
			case "anneeFin":
				return oAnneeScolaire.anneeFin;
			case "anneeNomCourt":
				return oAnneeScolaire.anneeNomCourt;
			case "anneeNomComplet":
				return oAnneeScolaire.anneeNomComplet;
			case "anneeId":
				return oAnneeScolaire.anneeId;
			case "pageUrl":
				return oAnneeScolaire.pageUrl;
			case "objetSuggere":
				return oAnneeScolaire.objetSuggere;
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
				o = attribuerAnneeScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAnneeScolaire(String var, Object val) {
		AnneeScolaire oAnneeScolaire = (AnneeScolaire)this;
		switch(var) {
			case "ecoleCle":
				oAnneeScolaire.setEcoleCle((Long)val);
				return val;
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
					o = definirAnneeScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAnneeScolaire(String var, String val) {
		switch(var) {
			case "anneeDebut":
				setAnneeDebut(val);
				sauvegardesAnneeScolaire.add(var);
				return val;
			case "anneeFin":
				setAnneeFin(val);
				sauvegardesAnneeScolaire.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesAnneeScolaire = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerAnneeScolaire(solrDocument);
	}
	public void peuplerAnneeScolaire(SolrDocument solrDocument) {
		AnneeScolaire oAnneeScolaire = (AnneeScolaire)this;
		sauvegardesAnneeScolaire = (List<String>)solrDocument.get("sauvegardesAnneeScolaire_stored_strings");
		if(sauvegardesAnneeScolaire != null) {

			Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
			if(ecoleCle != null)
				oAnneeScolaire.setEcoleCle(ecoleCle);

			if(sauvegardesAnneeScolaire.contains("anneeCle")) {
				Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
				if(anneeCle != null)
					oAnneeScolaire.setAnneeCle(anneeCle);
			}

			if(sauvegardesAnneeScolaire.contains("inscriptionCles")) {
				List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
				if(inscriptionCles != null)
					oAnneeScolaire.inscriptionCles.addAll(inscriptionCles);
			}

			if(sauvegardesAnneeScolaire.contains("saisonCles")) {
				List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
				if(saisonCles != null)
					oAnneeScolaire.saisonCles.addAll(saisonCles);
			}

			if(sauvegardesAnneeScolaire.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oAnneeScolaire.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardesAnneeScolaire.contains("anneeDebut")) {
				Date anneeDebut = (Date)solrDocument.get("anneeDebut_stored_date");
				if(anneeDebut != null)
					oAnneeScolaire.setAnneeDebut(anneeDebut);
			}

			if(sauvegardesAnneeScolaire.contains("anneeFin")) {
				Date anneeFin = (Date)solrDocument.get("anneeFin_stored_date");
				if(anneeFin != null)
					oAnneeScolaire.setAnneeFin(anneeFin);
			}

			if(sauvegardesAnneeScolaire.contains("anneeNomCourt")) {
				String anneeNomCourt = (String)solrDocument.get("anneeNomCourt_stored_string");
				if(anneeNomCourt != null)
					oAnneeScolaire.setAnneeNomCourt(anneeNomCourt);
			}

			if(sauvegardesAnneeScolaire.contains("anneeNomComplet")) {
				String anneeNomComplet = (String)solrDocument.get("anneeNomComplet_stored_string");
				if(anneeNomComplet != null)
					oAnneeScolaire.setAnneeNomComplet(anneeNomComplet);
			}

			if(sauvegardesAnneeScolaire.contains("anneeId")) {
				String anneeId = (String)solrDocument.get("anneeId_stored_string");
				if(anneeId != null)
					oAnneeScolaire.setAnneeId(anneeId);
			}

			if(sauvegardesAnneeScolaire.contains("pageUrl")) {
				String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
				if(pageUrl != null)
					oAnneeScolaire.setPageUrl(pageUrl);
			}

			if(sauvegardesAnneeScolaire.contains("objetSuggere")) {
				String objetSuggere = (String)solrDocument.get("objetSuggere_stored_string");
				if(objetSuggere != null)
					oAnneeScolaire.setObjetSuggere(objetSuggere);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.annee.AnneeScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			AnneeScolaire o = new AnneeScolaire();
			o.requeteSiteAnneeScolaire(requeteSite);
			o.initLoinAnneeScolaire(requeteSite);
			o.indexerAnneeScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerAnneeScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerAnneeScolaire(document);
	}

	public void indexerAnneeScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerAnneeScolaire(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerAnneeScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerAnneeScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerAnneeScolaire(SolrInputDocument document) {
		if(sauvegardesAnneeScolaire != null)
			document.addField("sauvegardesAnneeScolaire_stored_strings", sauvegardesAnneeScolaire);

		if(ecoleCle != null) {
			document.addField("ecoleCle_indexed_long", ecoleCle);
			document.addField("ecoleCle_stored_long", ecoleCle);
		}
		if(anneeCle != null) {
			document.addField("anneeCle_indexed_long", anneeCle);
			document.addField("anneeCle_stored_long", anneeCle);
		}
		if(inscriptionCles != null) {
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_indexed_longs", o);
			}
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_stored_longs", o);
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
		if(anneeNomCourt != null) {
			document.addField("anneeNomCourt_indexed_string", anneeNomCourt);
			document.addField("anneeNomCourt_stored_string", anneeNomCourt);
		}
		if(anneeNomComplet != null) {
			document.addField("anneeNomComplet_indexed_string", anneeNomComplet);
			document.addField("anneeNomComplet_stored_string", anneeNomComplet);
		}
		if(anneeId != null) {
			document.addField("anneeId_indexed_string", anneeId);
			document.addField("anneeId_stored_string", anneeId);
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

	public void desindexerAnneeScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinAnneeScolaire(requeteSite);
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
		stockerAnneeScolaire(solrDocument);
	}
	public void stockerAnneeScolaire(SolrDocument solrDocument) {
		AnneeScolaire oAnneeScolaire = (AnneeScolaire)this;

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oAnneeScolaire.setEcoleCle(ecoleCle);

		Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
		if(anneeCle != null)
			oAnneeScolaire.setAnneeCle(anneeCle);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oAnneeScolaire.inscriptionCles.addAll(inscriptionCles);

		List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
		if(saisonCles != null)
			oAnneeScolaire.saisonCles.addAll(saisonCles);

		String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
		if(ecoleNomComplet != null)
			oAnneeScolaire.setEcoleNomComplet(ecoleNomComplet);

		Date anneeDebut = (Date)solrDocument.get("anneeDebut_stored_date");
		if(anneeDebut != null)
			oAnneeScolaire.setAnneeDebut(anneeDebut);

		Date anneeFin = (Date)solrDocument.get("anneeFin_stored_date");
		if(anneeFin != null)
			oAnneeScolaire.setAnneeFin(anneeFin);

		String anneeNomCourt = (String)solrDocument.get("anneeNomCourt_stored_string");
		if(anneeNomCourt != null)
			oAnneeScolaire.setAnneeNomCourt(anneeNomCourt);

		String anneeNomComplet = (String)solrDocument.get("anneeNomComplet_stored_string");
		if(anneeNomComplet != null)
			oAnneeScolaire.setAnneeNomComplet(anneeNomComplet);

		String anneeId = (String)solrDocument.get("anneeId_stored_string");
		if(anneeId != null)
			oAnneeScolaire.setAnneeId(anneeId);

		String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
		if(pageUrl != null)
			oAnneeScolaire.setPageUrl(pageUrl);

		String objetSuggere = (String)solrDocument.get("objetSuggere_stored_string");
		if(objetSuggere != null)
			oAnneeScolaire.setObjetSuggere(objetSuggere);

		super.stockerCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), ecoleCle, anneeDebut, anneeFin);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof AnneeScolaire))
			return false;
		AnneeScolaire that = (AnneeScolaire)o;
		return super.equals(o)
				&& Objects.equals( ecoleCle, that.ecoleCle )
				&& Objects.equals( anneeDebut, that.anneeDebut )
				&& Objects.equals( anneeFin, that.anneeFin );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("AnneeScolaire { ");
		sb.append( "ecoleCle: " ).append(ecoleCle);
		sb.append( ", anneeDebut: " ).append(anneeDebut);
		sb.append( ", anneeFin: " ).append(anneeFin);
		sb.append(" }");
		return sb.toString();
	}
}
