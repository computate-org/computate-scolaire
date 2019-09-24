package org.computate.scolaire.frFR.enfant;

import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
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
import java.math.MathContext;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe objectSuggest dans Solr</a>
 * <br/>
 **/
public abstract class EnfantScolaireGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(EnfantScolaire.class);

	public static final String EnfantScolaire_UnNom = "un enfant";
	public static final String EnfantScolaire_Ce = "ce ";
	public static final String EnfantScolaire_CeNom = "cet enfant";
	public static final String EnfantScolaire_Un = "un ";
	public static final String EnfantScolaire_LeNom = "l'enfant";
	public static final String EnfantScolaire_NomSingulier = "enfant";
	public static final String EnfantScolaire_NomPluriel = "enfants";
	public static final String EnfantScolaire_NomActuel = "enfant actuel";
	public static final String EnfantScolaire_Tous = "all ";
	public static final String EnfantScolaire_TousNom = "tous les enfants";
	public static final String EnfantScolaire_RechercherTousNomPar = "rechercher enfants par ";
	public static final String EnfantScolaire_RechercherTousNom = "rechercher enfants";
	public static final String EnfantScolaire_LesNoms = "les enfants";
	public static final String EnfantScolaire_AucunNomTrouve = "aucun enfant trouvé";
	public static final String EnfantScolaire_NomVar = "enfant";
	public static final String EnfantScolaire_DeNom = "d'enfant";
	public static final String EnfantScolaire_NomAdjectifSingulier = "enfant";
	public static final String EnfantScolaire_NomAdjectifPluriel = "enfants";
	public static final String EnfantScolaire_Couleur = "green";
	public static final String EnfantScolaire_IconeGroupe = "regular";
	public static final String EnfantScolaire_IconeNom = "child";

	///////////////
	// enfantCle //
	///////////////

	/**	L'entité « enfantCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long enfantCle;
	@JsonIgnore
	public Couverture<Long> enfantCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("enfantCle").o(enfantCle);

	/**	<br/>L'entité « enfantCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantCle">Trouver l'entité enfantCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantCle(Couverture<Long> c);

	public Long getEnfantCle() {
		return enfantCle;
	}

	public void setEnfantCle(Long enfantCle) {
		this.enfantCle = enfantCle;
		this.enfantCleCouverture.dejaInitialise = true;
	}
	public EnfantScolaire setEnfantCle(String o) {
		if(NumberUtils.isParsable(o))
			this.enfantCle = Long.parseLong(o);
		this.enfantCleCouverture.dejaInitialise = true;
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire enfantCleInit() {
		if(!enfantCleCouverture.dejaInitialise) {
			_enfantCle(enfantCleCouverture);
			if(enfantCle == null)
				setEnfantCle(enfantCleCouverture.o);
		}
		enfantCleCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public Long solrEnfantCle() {
		return enfantCle;
	}

	public String strEnfantCle() {
		return enfantCle == null ? "" : enfantCle.toString();
	}

	public String jsonEnfantCle() {
		return enfantCle == null ? "" : enfantCle.toString();
	}

	public String nomAffichageEnfantCle() {
		return "clé";
	}

	public String htmTooltipEnfantCle() {
		return null;
	}

	public String htmEnfantCle() {
		return enfantCle == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantCle());
	}

	public void htmEnfantCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "EnfantCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "EnfantCle() {");
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
				r.l("				data: {\"setEnfantCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnfantCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"enfantCle\"");
							r.s(" value=\"", htmEnfantCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnfantCle());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
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
	public EnfantScolaire addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (EnfantScolaire)this;
	}
	public EnfantScolaire setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addInscriptionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public List<Long> solrInscriptionCles() {
		return inscriptionCles;
	}

	public String strInscriptionCles() {
		return inscriptionCles == null ? "" : inscriptionCles.toString();
	}

	public String jsonInscriptionCles() {
		return inscriptionCles == null ? "" : inscriptionCles.toString();
	}

	public String nomAffichageInscriptionCles() {
		return "inscriptions";
	}

	public String htmTooltipInscriptionCles() {
		return null;
	}

	public String htmInscriptionCles() {
		return inscriptionCles == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionCles());
	}

	public void htmInscriptionCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "InscriptionCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "InscriptionCles() {");
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
	// familleTri //
	////////////////

	/**	L'entité « familleTri »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer familleTri;
	@JsonIgnore
	public Couverture<Integer> familleTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("familleTri").o(familleTri);

	/**	<br/>L'entité « familleTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleTri">Trouver l'entité familleTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familleTri(Couverture<Integer> c);

	public Integer getFamilleTri() {
		return familleTri;
	}

	public void setFamilleTri(Integer familleTri) {
		this.familleTri = familleTri;
		this.familleTriCouverture.dejaInitialise = true;
	}
	public EnfantScolaire setFamilleTri(String o) {
		if(NumberUtils.isParsable(o))
			this.familleTri = Integer.parseInt(o);
		this.familleTriCouverture.dejaInitialise = true;
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire familleTriInit() {
		if(!familleTriCouverture.dejaInitialise) {
			_familleTri(familleTriCouverture);
			if(familleTri == null)
				setFamilleTri(familleTriCouverture.o);
		}
		familleTriCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public Integer solrFamilleTri() {
		return familleTri;
	}

	public String strFamilleTri() {
		return familleTri == null ? "" : familleTri.toString();
	}

	public String jsonFamilleTri() {
		return familleTri == null ? "" : familleTri.toString();
	}

	public String nomAffichageFamilleTri() {
		return null;
	}

	public String htmTooltipFamilleTri() {
		return null;
	}

	public String htmFamilleTri() {
		return familleTri == null ? "" : StringEscapeUtils.escapeHtml4(strFamilleTri());
	}

	public void htmFamilleTri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "FamilleTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "FamilleTri() {");
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
				r.l("				data: {\"setFamilleTri\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilleTri()), "</span>");
				r.s("			<input");
							r.s(" name=\"familleTri\"");
							r.s(" value=\"", htmFamilleTri(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilleTri());
			}
			r.l("</div>");
		}
	}

	///////////////
	// enfantTri //
	///////////////

	/**	L'entité « enfantTri »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer enfantTri;
	@JsonIgnore
	public Couverture<Integer> enfantTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("enfantTri").o(enfantTri);

	/**	<br/>L'entité « enfantTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantTri">Trouver l'entité enfantTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantTri(Couverture<Integer> c);

	public Integer getEnfantTri() {
		return enfantTri;
	}

	public void setEnfantTri(Integer enfantTri) {
		this.enfantTri = enfantTri;
		this.enfantTriCouverture.dejaInitialise = true;
	}
	public EnfantScolaire setEnfantTri(String o) {
		if(NumberUtils.isParsable(o))
			this.enfantTri = Integer.parseInt(o);
		this.enfantTriCouverture.dejaInitialise = true;
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire enfantTriInit() {
		if(!enfantTriCouverture.dejaInitialise) {
			_enfantTri(enfantTriCouverture);
			if(enfantTri == null)
				setEnfantTri(enfantTriCouverture.o);
		}
		enfantTriCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public Integer solrEnfantTri() {
		return enfantTri;
	}

	public String strEnfantTri() {
		return enfantTri == null ? "" : enfantTri.toString();
	}

	public String jsonEnfantTri() {
		return enfantTri == null ? "" : enfantTri.toString();
	}

	public String nomAffichageEnfantTri() {
		return null;
	}

	public String htmTooltipEnfantTri() {
		return null;
	}

	public String htmEnfantTri() {
		return enfantTri == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantTri());
	}

	public void htmEnfantTri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "EnfantTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "EnfantTri() {");
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
				r.l("				data: {\"setEnfantTri\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnfantTri()), "</span>");
				r.s("			<input");
							r.s(" name=\"enfantTri\"");
							r.s(" value=\"", htmEnfantTri(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnfantTri());
			}
			r.l("</div>");
		}
	}

	//////////////////////////
	// inscriptionRecherche //
	//////////////////////////

	/**	L'entité « inscriptionRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<InscriptionScolaire>(). 
	 */
	@JsonIgnore
	protected ListeRecherche<InscriptionScolaire> inscriptionRecherche = new ListeRecherche<InscriptionScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<InscriptionScolaire>> inscriptionRechercheCouverture = new Couverture<ListeRecherche<InscriptionScolaire>>().p(this).c(ListeRecherche.class).var("inscriptionRecherche").o(inscriptionRecherche);

	/**	<br/>L'entité « inscriptionRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<InscriptionScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionRecherche">Trouver l'entité inscriptionRecherche dans Solr</a>
	 * <br/>
	 * @param inscriptionRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionRecherche(ListeRecherche<InscriptionScolaire> l);

	public ListeRecherche<InscriptionScolaire> getInscriptionRecherche() {
		return inscriptionRecherche;
	}

	public void setInscriptionRecherche(ListeRecherche<InscriptionScolaire> inscriptionRecherche) {
		this.inscriptionRecherche = inscriptionRecherche;
		this.inscriptionRechercheCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire inscriptionRechercheInit() {
		if(!inscriptionRechercheCouverture.dejaInitialise) {
			_inscriptionRecherche(inscriptionRecherche);
		}
		inscriptionRecherche.initLoinPourClasse(requeteSite_);
		inscriptionRechercheCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	//////////////////
	// inscriptions //
	//////////////////

	/**	L'entité « inscriptions »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 */
	@JsonIgnore
	protected List<InscriptionScolaire> inscriptions = new java.util.ArrayList<org.computate.scolaire.frFR.inscription.InscriptionScolaire>();
	@JsonIgnore
	public Couverture<List<InscriptionScolaire>> inscriptionsCouverture = new Couverture<List<InscriptionScolaire>>().p(this).c(List.class).var("inscriptions").o(inscriptions);

	/**	<br/>L'entité « inscriptions »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptions">Trouver l'entité inscriptions dans Solr</a>
	 * <br/>
	 * @param inscriptions est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptions(List<InscriptionScolaire> l);

	public List<InscriptionScolaire> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<InscriptionScolaire> inscriptions) {
		this.inscriptions = inscriptions;
		this.inscriptionsCouverture.dejaInitialise = true;
	}
	public EnfantScolaire addInscriptions(InscriptionScolaire...objets) {
		for(InscriptionScolaire o : objets) {
			addInscriptions(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addInscriptions(InscriptionScolaire o) {
		if(o != null && !inscriptions.contains(o))
			this.inscriptions.add(o);
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire inscriptionsInit() {
		if(!inscriptionsCouverture.dejaInitialise) {
			_inscriptions(inscriptions);
		}
		inscriptionsCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	///////////////
	// ecoleCles //
	///////////////

	/**	L'entité « ecoleCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> ecoleCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> ecoleClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("ecoleCles").o(ecoleCles);

	/**	<br/>L'entité « ecoleCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCles">Trouver l'entité ecoleCles dans Solr</a>
	 * <br/>
	 * @param ecoleCles est l'entité déjà construit. 
	 **/
	protected abstract void _ecoleCles(List<Long> l);

	public List<Long> getEcoleCles() {
		return ecoleCles;
	}

	public void setEcoleCles(List<Long> ecoleCles) {
		this.ecoleCles = ecoleCles;
		this.ecoleClesCouverture.dejaInitialise = true;
	}
	public EnfantScolaire addEcoleCles(Long...objets) {
		for(Long o : objets) {
			addEcoleCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addEcoleCles(Long o) {
		if(o != null && !ecoleCles.contains(o))
			this.ecoleCles.add(o);
		return (EnfantScolaire)this;
	}
	public EnfantScolaire setEcoleCles(JsonArray objets) {
		ecoleCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEcoleCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addEcoleCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEcoleCles(p);
		}
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire ecoleClesInit() {
		if(!ecoleClesCouverture.dejaInitialise) {
			_ecoleCles(ecoleCles);
		}
		ecoleClesCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public List<Long> solrEcoleCles() {
		return ecoleCles;
	}

	public String strEcoleCles() {
		return ecoleCles == null ? "" : ecoleCles.toString();
	}

	public String jsonEcoleCles() {
		return ecoleCles == null ? "" : ecoleCles.toString();
	}

	public String nomAffichageEcoleCles() {
		return "écoles";
	}

	public String htmTooltipEcoleCles() {
		return null;
	}

	public String htmEcoleCles() {
		return ecoleCles == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleCles());
	}

	public void htmEcoleCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "EcoleCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "EcoleCles() {");
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
				r.l("				data: {\"setEcoleCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEcoleCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"ecoleCles\"");
							r.s(" value=\"", htmEcoleCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEcoleCles());
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
	@JsonIgnore
	public Couverture<List<Long>> anneeClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("anneeCles").o(anneeCles);

	/**	<br/>L'entité « anneeCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCles">Trouver l'entité anneeCles dans Solr</a>
	 * <br/>
	 * @param anneeCles est l'entité déjà construit. 
	 **/
	protected abstract void _anneeCles(List<Long> l);

	public List<Long> getAnneeCles() {
		return anneeCles;
	}

	public void setAnneeCles(List<Long> anneeCles) {
		this.anneeCles = anneeCles;
		this.anneeClesCouverture.dejaInitialise = true;
	}
	public EnfantScolaire addAnneeCles(Long...objets) {
		for(Long o : objets) {
			addAnneeCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addAnneeCles(Long o) {
		if(o != null && !anneeCles.contains(o))
			this.anneeCles.add(o);
		return (EnfantScolaire)this;
	}
	public EnfantScolaire setAnneeCles(JsonArray objets) {
		anneeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAnneeCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addAnneeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAnneeCles(p);
		}
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire anneeClesInit() {
		if(!anneeClesCouverture.dejaInitialise) {
			_anneeCles(anneeCles);
		}
		anneeClesCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public List<Long> solrAnneeCles() {
		return anneeCles;
	}

	public String strAnneeCles() {
		return anneeCles == null ? "" : anneeCles.toString();
	}

	public String jsonAnneeCles() {
		return anneeCles == null ? "" : anneeCles.toString();
	}

	public String nomAffichageAnneeCles() {
		return "années";
	}

	public String htmTooltipAnneeCles() {
		return null;
	}

	public String htmAnneeCles() {
		return anneeCles == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCles());
	}

	public void htmAnneeCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "AnneeCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "AnneeCles() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCles">Trouver l'entité saisonCles dans Solr</a>
	 * <br/>
	 * @param saisonCles est l'entité déjà construit. 
	 **/
	protected abstract void _saisonCles(List<Long> l);

	public List<Long> getSaisonCles() {
		return saisonCles;
	}

	public void setSaisonCles(List<Long> saisonCles) {
		this.saisonCles = saisonCles;
		this.saisonClesCouverture.dejaInitialise = true;
	}
	public EnfantScolaire addSaisonCles(Long...objets) {
		for(Long o : objets) {
			addSaisonCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addSaisonCles(Long o) {
		if(o != null && !saisonCles.contains(o))
			this.saisonCles.add(o);
		return (EnfantScolaire)this;
	}
	public EnfantScolaire setSaisonCles(JsonArray objets) {
		saisonCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSaisonCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addSaisonCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSaisonCles(p);
		}
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire saisonClesInit() {
		if(!saisonClesCouverture.dejaInitialise) {
			_saisonCles(saisonCles);
		}
		saisonClesCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public List<Long> solrSaisonCles() {
		return saisonCles;
	}

	public String strSaisonCles() {
		return saisonCles == null ? "" : saisonCles.toString();
	}

	public String jsonSaisonCles() {
		return saisonCles == null ? "" : saisonCles.toString();
	}

	public String nomAffichageSaisonCles() {
		return "saisons";
	}

	public String htmTooltipSaisonCles() {
		return null;
	}

	public String htmSaisonCles() {
		return saisonCles == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonCles());
	}

	public void htmSaisonCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "SaisonCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "SaisonCles() {");
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

	/////////////////
	// sessionCles //
	/////////////////

	/**	L'entité « sessionCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> sessionCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> sessionClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("sessionCles").o(sessionCles);

	/**	<br/>L'entité « sessionCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCles">Trouver l'entité sessionCles dans Solr</a>
	 * <br/>
	 * @param sessionCles est l'entité déjà construit. 
	 **/
	protected abstract void _sessionCles(List<Long> l);

	public List<Long> getSessionCles() {
		return sessionCles;
	}

	public void setSessionCles(List<Long> sessionCles) {
		this.sessionCles = sessionCles;
		this.sessionClesCouverture.dejaInitialise = true;
	}
	public EnfantScolaire addSessionCles(Long...objets) {
		for(Long o : objets) {
			addSessionCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addSessionCles(Long o) {
		if(o != null && !sessionCles.contains(o))
			this.sessionCles.add(o);
		return (EnfantScolaire)this;
	}
	public EnfantScolaire setSessionCles(JsonArray objets) {
		sessionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addSessionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionCles(p);
		}
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire sessionClesInit() {
		if(!sessionClesCouverture.dejaInitialise) {
			_sessionCles(sessionCles);
		}
		sessionClesCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public List<Long> solrSessionCles() {
		return sessionCles;
	}

	public String strSessionCles() {
		return sessionCles == null ? "" : sessionCles.toString();
	}

	public String jsonSessionCles() {
		return sessionCles == null ? "" : sessionCles.toString();
	}

	public String nomAffichageSessionCles() {
		return "sessions";
	}

	public String htmTooltipSessionCles() {
		return null;
	}

	public String htmSessionCles() {
		return sessionCles == null ? "" : StringEscapeUtils.escapeHtml4(strSessionCles());
	}

	public void htmSessionCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "SessionCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "SessionCles() {");
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

	/////////////
	// ageCles //
	/////////////

	/**	L'entité « ageCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> ageCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> ageClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("ageCles").o(ageCles);

	/**	<br/>L'entité « ageCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCles">Trouver l'entité ageCles dans Solr</a>
	 * <br/>
	 * @param ageCles est l'entité déjà construit. 
	 **/
	protected abstract void _ageCles(List<Long> l);

	public List<Long> getAgeCles() {
		return ageCles;
	}

	public void setAgeCles(List<Long> ageCles) {
		this.ageCles = ageCles;
		this.ageClesCouverture.dejaInitialise = true;
	}
	public EnfantScolaire addAgeCles(Long...objets) {
		for(Long o : objets) {
			addAgeCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addAgeCles(Long o) {
		if(o != null && !ageCles.contains(o))
			this.ageCles.add(o);
		return (EnfantScolaire)this;
	}
	public EnfantScolaire setAgeCles(JsonArray objets) {
		ageCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addAgeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAgeCles(p);
		}
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire ageClesInit() {
		if(!ageClesCouverture.dejaInitialise) {
			_ageCles(ageCles);
		}
		ageClesCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public List<Long> solrAgeCles() {
		return ageCles;
	}

	public String strAgeCles() {
		return ageCles == null ? "" : ageCles.toString();
	}

	public String jsonAgeCles() {
		return ageCles == null ? "" : ageCles.toString();
	}

	public String nomAffichageAgeCles() {
		return "âges";
	}

	public String htmTooltipAgeCles() {
		return null;
	}

	public String htmAgeCles() {
		return ageCles == null ? "" : StringEscapeUtils.escapeHtml4(strAgeCles());
	}

	public void htmAgeCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "AgeCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "AgeCles() {");
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
				r.l("				data: {\"setAgeCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageCles\"");
							r.s(" value=\"", htmAgeCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeCles());
			}
			r.l("</div>");
		}
	}

	////////////////////
	// personnePrenom //
	////////////////////

	/**	L'entité « personnePrenom »
	 *	 is defined as null before being initialized. 
	 */
	protected String personnePrenom;
	@JsonIgnore
	public Couverture<String> personnePrenomCouverture = new Couverture<String>().p(this).c(String.class).var("personnePrenom").o(personnePrenom);

	/**	<br/>L'entité « personnePrenom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personnePrenom">Trouver l'entité personnePrenom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personnePrenom(Couverture<String> c);

	public String getPersonnePrenom() {
		return personnePrenom;
	}

	public void setPersonnePrenom(String personnePrenom) {
		this.personnePrenom = personnePrenom;
		this.personnePrenomCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire personnePrenomInit() {
		if(!personnePrenomCouverture.dejaInitialise) {
			_personnePrenom(personnePrenomCouverture);
			if(personnePrenom == null)
				setPersonnePrenom(personnePrenomCouverture.o);
		}
		personnePrenomCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrPersonnePrenom() {
		return personnePrenom;
	}

	public String strPersonnePrenom() {
		return personnePrenom == null ? "" : personnePrenom;
	}

	public String jsonPersonnePrenom() {
		return personnePrenom == null ? "" : personnePrenom;
	}

	public String nomAffichagePersonnePrenom() {
		return "prénom";
	}

	public String htmTooltipPersonnePrenom() {
		return null;
	}

	public String htmPersonnePrenom() {
		return personnePrenom == null ? "" : StringEscapeUtils.escapeHtml4(strPersonnePrenom());
	}

	public void htmPersonnePrenom(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "PersonnePrenom\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "PersonnePrenom() {");
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
				r.l("				data: {\"setPersonnePrenom\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonnePrenom()), "</span>");
				r.s("			<input");
							r.s(" name=\"personnePrenom\"");
							r.s(" value=\"", htmPersonnePrenom(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonnePrenom());
			}
			r.l("</div>");
		}
	}

	////////////////
	// familleNom //
	////////////////

	/**	L'entité « familleNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String familleNom;
	@JsonIgnore
	public Couverture<String> familleNomCouverture = new Couverture<String>().p(this).c(String.class).var("familleNom").o(familleNom);

	/**	<br/>L'entité « familleNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleNom">Trouver l'entité familleNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familleNom(Couverture<String> c);

	public String getFamilleNom() {
		return familleNom;
	}

	public void setFamilleNom(String familleNom) {
		this.familleNom = familleNom;
		this.familleNomCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire familleNomInit() {
		if(!familleNomCouverture.dejaInitialise) {
			_familleNom(familleNomCouverture);
			if(familleNom == null)
				setFamilleNom(familleNomCouverture.o);
		}
		familleNomCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrFamilleNom() {
		return familleNom;
	}

	public String strFamilleNom() {
		return familleNom == null ? "" : familleNom;
	}

	public String jsonFamilleNom() {
		return familleNom == null ? "" : familleNom;
	}

	public String nomAffichageFamilleNom() {
		return "nom de famille";
	}

	public String htmTooltipFamilleNom() {
		return null;
	}

	public String htmFamilleNom() {
		return familleNom == null ? "" : StringEscapeUtils.escapeHtml4(strFamilleNom());
	}

	public void htmFamilleNom(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "FamilleNom\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "FamilleNom() {");
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
				r.l("				data: {\"setFamilleNom\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilleNom()), "</span>");
				r.s("			<input");
							r.s(" name=\"familleNom\"");
							r.s(" value=\"", htmFamilleNom(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilleNom());
			}
			r.l("</div>");
		}
	}

	////////////////////////
	// personneNomComplet //
	////////////////////////

	/**	L'entité « personneNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String personneNomComplet;
	@JsonIgnore
	public Couverture<String> personneNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("personneNomComplet").o(personneNomComplet);

	/**	<br/>L'entité « personneNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomComplet">Trouver l'entité personneNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomComplet(Couverture<String> c);

	public String getPersonneNomComplet() {
		return personneNomComplet;
	}

	public void setPersonneNomComplet(String personneNomComplet) {
		this.personneNomComplet = personneNomComplet;
		this.personneNomCompletCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire personneNomCompletInit() {
		if(!personneNomCompletCouverture.dejaInitialise) {
			_personneNomComplet(personneNomCompletCouverture);
			if(personneNomComplet == null)
				setPersonneNomComplet(personneNomCompletCouverture.o);
		}
		personneNomCompletCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrPersonneNomComplet() {
		return personneNomComplet;
	}

	public String strPersonneNomComplet() {
		return personneNomComplet == null ? "" : personneNomComplet;
	}

	public String jsonPersonneNomComplet() {
		return personneNomComplet == null ? "" : personneNomComplet;
	}

	public String nomAffichagePersonneNomComplet() {
		return "nom complèt";
	}

	public String htmTooltipPersonneNomComplet() {
		return null;
	}

	public String htmPersonneNomComplet() {
		return personneNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneNomComplet());
	}

	public void htmPersonneNomComplet(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "PersonneNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "PersonneNomComplet() {");
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
				r.l("				data: {\"setPersonneNomComplet\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonneNomComplet()), "</span>");
				r.s("			<input");
							r.s(" name=\"personneNomComplet\"");
							r.s(" value=\"", htmPersonneNomComplet(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonneNomComplet());
			}
			r.l("</div>");
		}
	}

	///////////////////////////////
	// personneNomCompletPrefere //
	///////////////////////////////

	/**	L'entité « personneNomCompletPrefere »
	 *	 is defined as null before being initialized. 
	 */
	protected String personneNomCompletPrefere;
	@JsonIgnore
	public Couverture<String> personneNomCompletPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("personneNomCompletPrefere").o(personneNomCompletPrefere);

	/**	<br/>L'entité « personneNomCompletPrefere »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomCompletPrefere">Trouver l'entité personneNomCompletPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomCompletPrefere(Couverture<String> c);

	public String getPersonneNomCompletPrefere() {
		return personneNomCompletPrefere;
	}

	public void setPersonneNomCompletPrefere(String personneNomCompletPrefere) {
		this.personneNomCompletPrefere = personneNomCompletPrefere;
		this.personneNomCompletPrefereCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire personneNomCompletPrefereInit() {
		if(!personneNomCompletPrefereCouverture.dejaInitialise) {
			_personneNomCompletPrefere(personneNomCompletPrefereCouverture);
			if(personneNomCompletPrefere == null)
				setPersonneNomCompletPrefere(personneNomCompletPrefereCouverture.o);
		}
		personneNomCompletPrefereCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrPersonneNomCompletPrefere() {
		return personneNomCompletPrefere;
	}

	public String strPersonneNomCompletPrefere() {
		return personneNomCompletPrefere == null ? "" : personneNomCompletPrefere;
	}

	public String jsonPersonneNomCompletPrefere() {
		return personneNomCompletPrefere == null ? "" : personneNomCompletPrefere;
	}

	public String nomAffichagePersonneNomCompletPrefere() {
		return "nom complèt préferé";
	}

	public String htmTooltipPersonneNomCompletPrefere() {
		return null;
	}

	public String htmPersonneNomCompletPrefere() {
		return personneNomCompletPrefere == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneNomCompletPrefere());
	}

	public void htmPersonneNomCompletPrefere(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "PersonneNomCompletPrefere\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "PersonneNomCompletPrefere() {");
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
				r.l("				data: {\"setPersonneNomCompletPrefere\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonneNomCompletPrefere()), "</span>");
				r.s("			<input");
							r.s(" name=\"personneNomCompletPrefere\"");
							r.s(" value=\"", htmPersonneNomCompletPrefere(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonneNomCompletPrefere());
			}
			r.l("</div>");
		}
	}

	///////////////////////
	// personneNomFormel //
	///////////////////////

	/**	L'entité « personneNomFormel »
	 *	 is defined as null before being initialized. 
	 */
	protected String personneNomFormel;
	@JsonIgnore
	public Couverture<String> personneNomFormelCouverture = new Couverture<String>().p(this).c(String.class).var("personneNomFormel").o(personneNomFormel);

	/**	<br/>L'entité « personneNomFormel »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomFormel">Trouver l'entité personneNomFormel dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomFormel(Couverture<String> c);

	public String getPersonneNomFormel() {
		return personneNomFormel;
	}

	public void setPersonneNomFormel(String personneNomFormel) {
		this.personneNomFormel = personneNomFormel;
		this.personneNomFormelCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire personneNomFormelInit() {
		if(!personneNomFormelCouverture.dejaInitialise) {
			_personneNomFormel(personneNomFormelCouverture);
			if(personneNomFormel == null)
				setPersonneNomFormel(personneNomFormelCouverture.o);
		}
		personneNomFormelCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrPersonneNomFormel() {
		return personneNomFormel;
	}

	public String strPersonneNomFormel() {
		return personneNomFormel == null ? "" : personneNomFormel;
	}

	public String jsonPersonneNomFormel() {
		return personneNomFormel == null ? "" : personneNomFormel;
	}

	public String nomAffichagePersonneNomFormel() {
		return "nom formel";
	}

	public String htmTooltipPersonneNomFormel() {
		return null;
	}

	public String htmPersonneNomFormel() {
		return personneNomFormel == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneNomFormel());
	}

	public void htmPersonneNomFormel(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "PersonneNomFormel\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "PersonneNomFormel() {");
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
				r.l("				data: {\"setPersonneNomFormel\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonneNomFormel()), "</span>");
				r.s("			<input");
							r.s(" name=\"personneNomFormel\"");
							r.s(" value=\"", htmPersonneNomFormel(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonneNomFormel());
			}
			r.l("</div>");
		}
	}

	///////////////////////////
	// personneDateNaissance //
	///////////////////////////

	/**	L'entité « personneDateNaissance »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDate personneDateNaissance;
	@JsonIgnore
	public Couverture<LocalDate> personneDateNaissanceCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("personneDateNaissance").o(personneDateNaissance);

	/**	<br/>L'entité « personneDateNaissance »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneDateNaissance">Trouver l'entité personneDateNaissance dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneDateNaissance(Couverture<LocalDate> c);

	public LocalDate getPersonneDateNaissance() {
		return personneDateNaissance;
	}

	public void setPersonneDateNaissance(LocalDate personneDateNaissance) {
		this.personneDateNaissance = personneDateNaissance;
		this.personneDateNaissanceCouverture.dejaInitialise = true;
	}
	public EnfantScolaire setPersonneDateNaissance(Instant o) {
		this.personneDateNaissance = LocalDate.from(o);
		this.personneDateNaissanceCouverture.dejaInitialise = true;
		return (EnfantScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public EnfantScolaire setPersonneDateNaissance(String o) {
		this.personneDateNaissance = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.personneDateNaissanceCouverture.dejaInitialise = true;
		return (EnfantScolaire)this;
	}
	public EnfantScolaire setPersonneDateNaissance(Date o) {
		this.personneDateNaissance = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.personneDateNaissanceCouverture.dejaInitialise = true;
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire personneDateNaissanceInit() {
		if(!personneDateNaissanceCouverture.dejaInitialise) {
			_personneDateNaissance(personneDateNaissanceCouverture);
			if(personneDateNaissance == null)
				setPersonneDateNaissance(personneDateNaissanceCouverture.o);
		}
		personneDateNaissanceCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public Date solrPersonneDateNaissance() {
		return personneDateNaissance == null ? null : Date.from(personneDateNaissance.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strPersonneDateNaissance() {
		return personneDateNaissance == null ? "" : personneDateNaissance.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonPersonneDateNaissance() {
		return personneDateNaissance == null ? "" : personneDateNaissance.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichagePersonneDateNaissance() {
		return "date de naissance";
	}

	public String htmTooltipPersonneDateNaissance() {
		return null;
	}

	public String htmPersonneDateNaissance() {
		return personneDateNaissance == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneDateNaissance());
	}

	public void htmPersonneDateNaissance(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "PersonneDateNaissance\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "PersonneDateNaissance() {");
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
				r.l("				data: {\"setPersonneDateNaissance\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePersonneDateNaissance()), "</span>");
				r.s("			<input");
							r.s(" name=\"personneDateNaissance\"");
							r.s(" value=\"", htmPersonneDateNaissance(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPersonneDateNaissance());
			}
			r.l("</div>");
		}
	}

	///////////////////////////////
	// enfantConditionsMedicales //
	///////////////////////////////

	/**	L'entité « enfantConditionsMedicales »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantConditionsMedicales;
	@JsonIgnore
	public Couverture<String> enfantConditionsMedicalesCouverture = new Couverture<String>().p(this).c(String.class).var("enfantConditionsMedicales").o(enfantConditionsMedicales);

	/**	<br/>L'entité « enfantConditionsMedicales »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantConditionsMedicales">Trouver l'entité enfantConditionsMedicales dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantConditionsMedicales(Couverture<String> c);

	public String getEnfantConditionsMedicales() {
		return enfantConditionsMedicales;
	}

	public void setEnfantConditionsMedicales(String enfantConditionsMedicales) {
		this.enfantConditionsMedicales = enfantConditionsMedicales;
		this.enfantConditionsMedicalesCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire enfantConditionsMedicalesInit() {
		if(!enfantConditionsMedicalesCouverture.dejaInitialise) {
			_enfantConditionsMedicales(enfantConditionsMedicalesCouverture);
			if(enfantConditionsMedicales == null)
				setEnfantConditionsMedicales(enfantConditionsMedicalesCouverture.o);
		}
		enfantConditionsMedicalesCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrEnfantConditionsMedicales() {
		return enfantConditionsMedicales;
	}

	public String strEnfantConditionsMedicales() {
		return enfantConditionsMedicales == null ? "" : enfantConditionsMedicales;
	}

	public String jsonEnfantConditionsMedicales() {
		return enfantConditionsMedicales == null ? "" : enfantConditionsMedicales;
	}

	public String nomAffichageEnfantConditionsMedicales() {
		return "conditions médicales";
	}

	public String htmTooltipEnfantConditionsMedicales() {
		return null;
	}

	public String htmEnfantConditionsMedicales() {
		return enfantConditionsMedicales == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantConditionsMedicales());
	}

	public void htmEnfantConditionsMedicales(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "EnfantConditionsMedicales\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "EnfantConditionsMedicales() {");
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
				r.l("				data: {\"setEnfantConditionsMedicales\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnfantConditionsMedicales()), "</span>");
				r.s("			<input");
							r.s(" name=\"enfantConditionsMedicales\"");
							r.s(" value=\"", htmEnfantConditionsMedicales(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnfantConditionsMedicales());
			}
			r.l("</div>");
		}
	}

	/////////////////////////////////////////
	// enfantEcolesPrecedemmentFrequentees //
	/////////////////////////////////////////

	/**	L'entité « enfantEcolesPrecedemmentFrequentees »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantEcolesPrecedemmentFrequentees;
	@JsonIgnore
	public Couverture<String> enfantEcolesPrecedemmentFrequenteesCouverture = new Couverture<String>().p(this).c(String.class).var("enfantEcolesPrecedemmentFrequentees").o(enfantEcolesPrecedemmentFrequentees);

	/**	<br/>L'entité « enfantEcolesPrecedemmentFrequentees »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantEcolesPrecedemmentFrequentees">Trouver l'entité enfantEcolesPrecedemmentFrequentees dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantEcolesPrecedemmentFrequentees(Couverture<String> c);

	public String getEnfantEcolesPrecedemmentFrequentees() {
		return enfantEcolesPrecedemmentFrequentees;
	}

	public void setEnfantEcolesPrecedemmentFrequentees(String enfantEcolesPrecedemmentFrequentees) {
		this.enfantEcolesPrecedemmentFrequentees = enfantEcolesPrecedemmentFrequentees;
		this.enfantEcolesPrecedemmentFrequenteesCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire enfantEcolesPrecedemmentFrequenteesInit() {
		if(!enfantEcolesPrecedemmentFrequenteesCouverture.dejaInitialise) {
			_enfantEcolesPrecedemmentFrequentees(enfantEcolesPrecedemmentFrequenteesCouverture);
			if(enfantEcolesPrecedemmentFrequentees == null)
				setEnfantEcolesPrecedemmentFrequentees(enfantEcolesPrecedemmentFrequenteesCouverture.o);
		}
		enfantEcolesPrecedemmentFrequenteesCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrEnfantEcolesPrecedemmentFrequentees() {
		return enfantEcolesPrecedemmentFrequentees;
	}

	public String strEnfantEcolesPrecedemmentFrequentees() {
		return enfantEcolesPrecedemmentFrequentees == null ? "" : enfantEcolesPrecedemmentFrequentees;
	}

	public String jsonEnfantEcolesPrecedemmentFrequentees() {
		return enfantEcolesPrecedemmentFrequentees == null ? "" : enfantEcolesPrecedemmentFrequentees;
	}

	public String nomAffichageEnfantEcolesPrecedemmentFrequentees() {
		return "écoles précedemment fréqentées";
	}

	public String htmTooltipEnfantEcolesPrecedemmentFrequentees() {
		return null;
	}

	public String htmEnfantEcolesPrecedemmentFrequentees() {
		return enfantEcolesPrecedemmentFrequentees == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantEcolesPrecedemmentFrequentees());
	}

	public void htmEnfantEcolesPrecedemmentFrequentees(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "EnfantEcolesPrecedemmentFrequentees\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "EnfantEcolesPrecedemmentFrequentees() {");
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
				r.l("				data: {\"setEnfantEcolesPrecedemmentFrequentees\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnfantEcolesPrecedemmentFrequentees()), "</span>");
				r.s("			<input");
							r.s(" name=\"enfantEcolesPrecedemmentFrequentees\"");
							r.s(" value=\"", htmEnfantEcolesPrecedemmentFrequentees(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnfantEcolesPrecedemmentFrequentees());
			}
			r.l("</div>");
		}
	}

	///////////////////////
	// enfantDescription //
	///////////////////////

	/**	L'entité « enfantDescription »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantDescription;
	@JsonIgnore
	public Couverture<String> enfantDescriptionCouverture = new Couverture<String>().p(this).c(String.class).var("enfantDescription").o(enfantDescription);

	/**	<br/>L'entité « enfantDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantDescription">Trouver l'entité enfantDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantDescription(Couverture<String> c);

	public String getEnfantDescription() {
		return enfantDescription;
	}

	public void setEnfantDescription(String enfantDescription) {
		this.enfantDescription = enfantDescription;
		this.enfantDescriptionCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire enfantDescriptionInit() {
		if(!enfantDescriptionCouverture.dejaInitialise) {
			_enfantDescription(enfantDescriptionCouverture);
			if(enfantDescription == null)
				setEnfantDescription(enfantDescriptionCouverture.o);
		}
		enfantDescriptionCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrEnfantDescription() {
		return enfantDescription;
	}

	public String strEnfantDescription() {
		return enfantDescription == null ? "" : enfantDescription;
	}

	public String jsonEnfantDescription() {
		return enfantDescription == null ? "" : enfantDescription;
	}

	public String nomAffichageEnfantDescription() {
		return "description";
	}

	public String htmTooltipEnfantDescription() {
		return null;
	}

	public String htmEnfantDescription() {
		return enfantDescription == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantDescription());
	}

	public void htmEnfantDescription(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "EnfantDescription\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "EnfantDescription() {");
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
				r.l("				data: {\"setEnfantDescription\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnfantDescription()), "</span>");
				r.s("			<input");
							r.s(" name=\"enfantDescription\"");
							r.s(" value=\"", htmEnfantDescription(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnfantDescription());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// enfantObjectifs //
	/////////////////////

	/**	L'entité « enfantObjectifs »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantObjectifs;
	@JsonIgnore
	public Couverture<String> enfantObjectifsCouverture = new Couverture<String>().p(this).c(String.class).var("enfantObjectifs").o(enfantObjectifs);

	/**	<br/>L'entité « enfantObjectifs »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantObjectifs">Trouver l'entité enfantObjectifs dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantObjectifs(Couverture<String> c);

	public String getEnfantObjectifs() {
		return enfantObjectifs;
	}

	public void setEnfantObjectifs(String enfantObjectifs) {
		this.enfantObjectifs = enfantObjectifs;
		this.enfantObjectifsCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire enfantObjectifsInit() {
		if(!enfantObjectifsCouverture.dejaInitialise) {
			_enfantObjectifs(enfantObjectifsCouverture);
			if(enfantObjectifs == null)
				setEnfantObjectifs(enfantObjectifsCouverture.o);
		}
		enfantObjectifsCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrEnfantObjectifs() {
		return enfantObjectifs;
	}

	public String strEnfantObjectifs() {
		return enfantObjectifs == null ? "" : enfantObjectifs;
	}

	public String jsonEnfantObjectifs() {
		return enfantObjectifs == null ? "" : enfantObjectifs;
	}

	public String nomAffichageEnfantObjectifs() {
		return "objectifs";
	}

	public String htmTooltipEnfantObjectifs() {
		return null;
	}

	public String htmEnfantObjectifs() {
		return enfantObjectifs == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantObjectifs());
	}

	public void htmEnfantObjectifs(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "EnfantObjectifs\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "EnfantObjectifs() {");
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
				r.l("				data: {\"setEnfantObjectifs\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnfantObjectifs()), "</span>");
				r.s("			<input");
							r.s(" name=\"enfantObjectifs\"");
							r.s(" value=\"", htmEnfantObjectifs(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnfantObjectifs());
			}
			r.l("</div>");
		}
	}

	////////////////////////
	// enfantVaccinsAJour //
	////////////////////////

	/**	L'entité « enfantVaccinsAJour »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean enfantVaccinsAJour;
	@JsonIgnore
	public Couverture<Boolean> enfantVaccinsAJourCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("enfantVaccinsAJour").o(enfantVaccinsAJour);

	/**	<br/>L'entité « enfantVaccinsAJour »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantVaccinsAJour">Trouver l'entité enfantVaccinsAJour dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantVaccinsAJour(Couverture<Boolean> c);

	public Boolean getEnfantVaccinsAJour() {
		return enfantVaccinsAJour;
	}

	public void setEnfantVaccinsAJour(Boolean enfantVaccinsAJour) {
		this.enfantVaccinsAJour = enfantVaccinsAJour;
		this.enfantVaccinsAJourCouverture.dejaInitialise = true;
	}
	public EnfantScolaire setEnfantVaccinsAJour(String o) {
		this.enfantVaccinsAJour = Boolean.parseBoolean(o);
		this.enfantVaccinsAJourCouverture.dejaInitialise = true;
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire enfantVaccinsAJourInit() {
		if(!enfantVaccinsAJourCouverture.dejaInitialise) {
			_enfantVaccinsAJour(enfantVaccinsAJourCouverture);
			if(enfantVaccinsAJour == null)
				setEnfantVaccinsAJour(enfantVaccinsAJourCouverture.o);
		}
		enfantVaccinsAJourCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public Boolean solrEnfantVaccinsAJour() {
		return enfantVaccinsAJour;
	}

	public String strEnfantVaccinsAJour() {
		return enfantVaccinsAJour == null ? "" : enfantVaccinsAJour.toString();
	}

	public String jsonEnfantVaccinsAJour() {
		return enfantVaccinsAJour == null ? "" : enfantVaccinsAJour.toString();
	}

	public String nomAffichageEnfantVaccinsAJour() {
		return "vaccins à jour";
	}

	public String htmTooltipEnfantVaccinsAJour() {
		return null;
	}

	public String htmEnfantVaccinsAJour() {
		return enfantVaccinsAJour == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantVaccinsAJour());
	}

	public void htmEnfantVaccinsAJour(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "EnfantVaccinsAJour\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "EnfantVaccinsAJour() {");
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
				r.l("				data: {\"setEnfantVaccinsAJour\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnfantVaccinsAJour()), "</span>");
				r.s("			<input");
							r.s(" name=\"enfantVaccinsAJour\"");
							r.s(" value=\"", htmEnfantVaccinsAJour(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnfantVaccinsAJour());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// enfantPropre //
	//////////////////

	/**	L'entité « enfantPropre »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean enfantPropre;
	@JsonIgnore
	public Couverture<Boolean> enfantPropreCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("enfantPropre").o(enfantPropre);

	/**	<br/>L'entité « enfantPropre »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantPropre">Trouver l'entité enfantPropre dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantPropre(Couverture<Boolean> c);

	public Boolean getEnfantPropre() {
		return enfantPropre;
	}

	public void setEnfantPropre(Boolean enfantPropre) {
		this.enfantPropre = enfantPropre;
		this.enfantPropreCouverture.dejaInitialise = true;
	}
	public EnfantScolaire setEnfantPropre(String o) {
		this.enfantPropre = Boolean.parseBoolean(o);
		this.enfantPropreCouverture.dejaInitialise = true;
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire enfantPropreInit() {
		if(!enfantPropreCouverture.dejaInitialise) {
			_enfantPropre(enfantPropreCouverture);
			if(enfantPropre == null)
				setEnfantPropre(enfantPropreCouverture.o);
		}
		enfantPropreCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public Boolean solrEnfantPropre() {
		return enfantPropre;
	}

	public String strEnfantPropre() {
		return enfantPropre == null ? "" : enfantPropre.toString();
	}

	public String jsonEnfantPropre() {
		return enfantPropre == null ? "" : enfantPropre.toString();
	}

	public String nomAffichageEnfantPropre() {
		return "propre";
	}

	public String htmTooltipEnfantPropre() {
		return null;
	}

	public String htmEnfantPropre() {
		return enfantPropre == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantPropre());
	}

	public void htmEnfantPropre(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "EnfantPropre\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "EnfantPropre() {");
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
				r.l("				data: {\"setEnfantPropre\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnfantPropre()), "</span>");
				r.s("			<input");
							r.s(" name=\"enfantPropre\"");
							r.s(" value=\"", htmEnfantPropre(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnfantPropre());
			}
			r.l("</div>");
		}
	}

	//////////////////////
	// enfantNomComplet //
	//////////////////////

	/**	L'entité « enfantNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantNomComplet;
	@JsonIgnore
	public Couverture<String> enfantNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("enfantNomComplet").o(enfantNomComplet);

	/**	<br/>L'entité « enfantNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantNomComplet">Trouver l'entité enfantNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantNomComplet(Couverture<String> c);

	public String getEnfantNomComplet() {
		return enfantNomComplet;
	}

	public void setEnfantNomComplet(String enfantNomComplet) {
		this.enfantNomComplet = enfantNomComplet;
		this.enfantNomCompletCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire enfantNomCompletInit() {
		if(!enfantNomCompletCouverture.dejaInitialise) {
			_enfantNomComplet(enfantNomCompletCouverture);
			if(enfantNomComplet == null)
				setEnfantNomComplet(enfantNomCompletCouverture.o);
		}
		enfantNomCompletCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrEnfantNomComplet() {
		return enfantNomComplet;
	}

	public String strEnfantNomComplet() {
		return enfantNomComplet == null ? "" : enfantNomComplet;
	}

	public String jsonEnfantNomComplet() {
		return enfantNomComplet == null ? "" : enfantNomComplet;
	}

	public String nomAffichageEnfantNomComplet() {
		return null;
	}

	public String htmTooltipEnfantNomComplet() {
		return null;
	}

	public String htmEnfantNomComplet() {
		return enfantNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantNomComplet());
	}

	public void htmEnfantNomComplet(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "EnfantNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "EnfantNomComplet() {");
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
				r.l("				data: {\"setEnfantNomComplet\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnfantNomComplet()), "</span>");
				r.s("			<input");
							r.s(" name=\"enfantNomComplet\"");
							r.s(" value=\"", htmEnfantNomComplet(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnfantNomComplet());
			}
			r.l("</div>");
		}
	}

	//////////////
	// enfantId //
	//////////////

	/**	L'entité « enfantId »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantId;
	@JsonIgnore
	public Couverture<String> enfantIdCouverture = new Couverture<String>().p(this).c(String.class).var("enfantId").o(enfantId);

	/**	<br/>L'entité « enfantId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantId">Trouver l'entité enfantId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantId(Couverture<String> c);

	public String getEnfantId() {
		return enfantId;
	}

	public void setEnfantId(String enfantId) {
		this.enfantId = enfantId;
		this.enfantIdCouverture.dejaInitialise = true;
	}
	protected EnfantScolaire enfantIdInit() {
		if(!enfantIdCouverture.dejaInitialise) {
			_enfantId(enfantIdCouverture);
			if(enfantId == null)
				setEnfantId(enfantIdCouverture.o);
		}
		enfantIdCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrEnfantId() {
		return enfantId;
	}

	public String strEnfantId() {
		return enfantId == null ? "" : enfantId;
	}

	public String jsonEnfantId() {
		return enfantId == null ? "" : enfantId;
	}

	public String nomAffichageEnfantId() {
		return "ID";
	}

	public String htmTooltipEnfantId() {
		return null;
	}

	public String htmEnfantId() {
		return enfantId == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantId());
	}

	public void htmEnfantId(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "EnfantId\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "EnfantId() {");
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
				r.l("				data: {\"setEnfantId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnfantId()), "</span>");
				r.s("			<input");
							r.s(" name=\"enfantId\"");
							r.s(" value=\"", htmEnfantId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnfantId());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
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
	protected EnfantScolaire pageUrlInit() {
		if(!pageUrlCouverture.dejaInitialise) {
			_pageUrl(pageUrlCouverture);
			if(pageUrl == null)
				setPageUrl(pageUrlCouverture.o);
		}
		pageUrlCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
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

	public void htmPageUrl(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchEnfantScolaire", strPk(), "PageUrl\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "PageUrl() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetSuggere">Trouver l'entité objetSuggere dans Solr</a>
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
	protected EnfantScolaire objetSuggereInit() {
		if(!objetSuggereCouverture.dejaInitialise) {
			_objetSuggere(objetSuggereCouverture);
			if(objetSuggere == null)
				setObjetSuggere(objetSuggereCouverture.o);
		}
		objetSuggereCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public String solrObjetSuggere() {
		return objetSuggere;
	}

	public String strObjetSuggere() {
		return objetSuggere == null ? "" : objetSuggere;
	}

	public String jsonObjetSuggere() {
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
			r.s("<div id=\"patchEnfantScolaire", strPk(), "ObjetSuggere\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchEnfantScolaire", strPk(), "ObjetSuggere() {");
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

	protected boolean dejaInitialiseEnfantScolaire = false;

	public EnfantScolaire initLoinEnfantScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseEnfantScolaire) {
			dejaInitialiseEnfantScolaire = true;
			initLoinEnfantScolaire();
		}
		return (EnfantScolaire)this;
	}

	public void initLoinEnfantScolaire() {
		super.initLoinCluster(requeteSite_);
		initEnfantScolaire();
	}

	public void initEnfantScolaire() {
		enfantCleInit();
		inscriptionClesInit();
		familleTriInit();
		enfantTriInit();
		inscriptionRechercheInit();
		inscriptionsInit();
		ecoleClesInit();
		anneeClesInit();
		saisonClesInit();
		sessionClesInit();
		ageClesInit();
		personnePrenomInit();
		familleNomInit();
		personneNomCompletInit();
		personneNomCompletPrefereInit();
		personneNomFormelInit();
		personneDateNaissanceInit();
		enfantConditionsMedicalesInit();
		enfantEcolesPrecedemmentFrequenteesInit();
		enfantDescriptionInit();
		enfantObjectifsInit();
		enfantVaccinsAJourInit();
		enfantPropreInit();
		enfantNomCompletInit();
		enfantIdInit();
		pageUrlInit();
		objetSuggereInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinEnfantScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEnfantScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(inscriptionRecherche != null)
			inscriptionRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteEnfantScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEnfantScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEnfantScolaire(String var) {
		EnfantScolaire oEnfantScolaire = (EnfantScolaire)this;
		switch(var) {
			case "enfantCle":
				return oEnfantScolaire.enfantCle;
			case "inscriptionCles":
				return oEnfantScolaire.inscriptionCles;
			case "familleTri":
				return oEnfantScolaire.familleTri;
			case "enfantTri":
				return oEnfantScolaire.enfantTri;
			case "inscriptionRecherche":
				return oEnfantScolaire.inscriptionRecherche;
			case "inscriptions":
				return oEnfantScolaire.inscriptions;
			case "ecoleCles":
				return oEnfantScolaire.ecoleCles;
			case "anneeCles":
				return oEnfantScolaire.anneeCles;
			case "saisonCles":
				return oEnfantScolaire.saisonCles;
			case "sessionCles":
				return oEnfantScolaire.sessionCles;
			case "ageCles":
				return oEnfantScolaire.ageCles;
			case "personnePrenom":
				return oEnfantScolaire.personnePrenom;
			case "familleNom":
				return oEnfantScolaire.familleNom;
			case "personneNomComplet":
				return oEnfantScolaire.personneNomComplet;
			case "personneNomCompletPrefere":
				return oEnfantScolaire.personneNomCompletPrefere;
			case "personneNomFormel":
				return oEnfantScolaire.personneNomFormel;
			case "personneDateNaissance":
				return oEnfantScolaire.personneDateNaissance;
			case "enfantConditionsMedicales":
				return oEnfantScolaire.enfantConditionsMedicales;
			case "enfantEcolesPrecedemmentFrequentees":
				return oEnfantScolaire.enfantEcolesPrecedemmentFrequentees;
			case "enfantDescription":
				return oEnfantScolaire.enfantDescription;
			case "enfantObjectifs":
				return oEnfantScolaire.enfantObjectifs;
			case "enfantVaccinsAJour":
				return oEnfantScolaire.enfantVaccinsAJour;
			case "enfantPropre":
				return oEnfantScolaire.enfantPropre;
			case "enfantNomComplet":
				return oEnfantScolaire.enfantNomComplet;
			case "enfantId":
				return oEnfantScolaire.enfantId;
			case "pageUrl":
				return oEnfantScolaire.pageUrl;
			case "objetSuggere":
				return oEnfantScolaire.objetSuggere;
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
				o = attribuerEnfantScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEnfantScolaire(String var, Object val) {
		EnfantScolaire oEnfantScolaire = (EnfantScolaire)this;
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
					o = definirEnfantScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEnfantScolaire(String var, String val) {
		switch(var) {
			case "personnePrenom":
				setPersonnePrenom(val);
				sauvegardesEnfantScolaire.add(var);
				return val;
			case "familleNom":
				setFamilleNom(val);
				sauvegardesEnfantScolaire.add(var);
				return val;
			case "personneNomComplet":
				setPersonneNomComplet(val);
				sauvegardesEnfantScolaire.add(var);
				return val;
			case "personneNomCompletPrefere":
				setPersonneNomCompletPrefere(val);
				sauvegardesEnfantScolaire.add(var);
				return val;
			case "personneNomFormel":
				setPersonneNomFormel(val);
				sauvegardesEnfantScolaire.add(var);
				return val;
			case "enfantConditionsMedicales":
				setEnfantConditionsMedicales(val);
				sauvegardesEnfantScolaire.add(var);
				return val;
			case "enfantEcolesPrecedemmentFrequentees":
				setEnfantEcolesPrecedemmentFrequentees(val);
				sauvegardesEnfantScolaire.add(var);
				return val;
			case "enfantDescription":
				setEnfantDescription(val);
				sauvegardesEnfantScolaire.add(var);
				return val;
			case "enfantObjectifs":
				setEnfantObjectifs(val);
				sauvegardesEnfantScolaire.add(var);
				return val;
			case "enfantVaccinsAJour":
				setEnfantVaccinsAJour(val);
				sauvegardesEnfantScolaire.add(var);
				return val;
			case "enfantPropre":
				setEnfantPropre(val);
				sauvegardesEnfantScolaire.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesEnfantScolaire = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerEnfantScolaire(solrDocument);
	}
	public void peuplerEnfantScolaire(SolrDocument solrDocument) {
		EnfantScolaire oEnfantScolaire = (EnfantScolaire)this;
		sauvegardesEnfantScolaire = (List<String>)solrDocument.get("sauvegardesEnfantScolaire_stored_strings");
		if(sauvegardesEnfantScolaire != null) {

			if(sauvegardesEnfantScolaire.contains("enfantCle")) {
				Long enfantCle = (Long)solrDocument.get("enfantCle_stored_long");
				if(enfantCle != null)
					oEnfantScolaire.setEnfantCle(enfantCle);
			}

			if(sauvegardesEnfantScolaire.contains("inscriptionCles")) {
				List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
				if(inscriptionCles != null)
					oEnfantScolaire.inscriptionCles.addAll(inscriptionCles);
			}

			if(sauvegardesEnfantScolaire.contains("familleTri")) {
				Integer familleTri = (Integer)solrDocument.get("familleTri_stored_int");
				if(familleTri != null)
					oEnfantScolaire.setFamilleTri(familleTri);
			}

			if(sauvegardesEnfantScolaire.contains("enfantTri")) {
				Integer enfantTri = (Integer)solrDocument.get("enfantTri_stored_int");
				if(enfantTri != null)
					oEnfantScolaire.setEnfantTri(enfantTri);
			}

			if(sauvegardesEnfantScolaire.contains("ecoleCles")) {
				List<Long> ecoleCles = (List<Long>)solrDocument.get("ecoleCles_stored_longs");
				if(ecoleCles != null)
					oEnfantScolaire.ecoleCles.addAll(ecoleCles);
			}

			if(sauvegardesEnfantScolaire.contains("anneeCles")) {
				List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
				if(anneeCles != null)
					oEnfantScolaire.anneeCles.addAll(anneeCles);
			}

			if(sauvegardesEnfantScolaire.contains("saisonCles")) {
				List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
				if(saisonCles != null)
					oEnfantScolaire.saisonCles.addAll(saisonCles);
			}

			if(sauvegardesEnfantScolaire.contains("sessionCles")) {
				List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
				if(sessionCles != null)
					oEnfantScolaire.sessionCles.addAll(sessionCles);
			}

			if(sauvegardesEnfantScolaire.contains("ageCles")) {
				List<Long> ageCles = (List<Long>)solrDocument.get("ageCles_stored_longs");
				if(ageCles != null)
					oEnfantScolaire.ageCles.addAll(ageCles);
			}

			if(sauvegardesEnfantScolaire.contains("personnePrenom")) {
				String personnePrenom = (String)solrDocument.get("personnePrenom_stored_string");
				if(personnePrenom != null)
					oEnfantScolaire.setPersonnePrenom(personnePrenom);
			}

			if(sauvegardesEnfantScolaire.contains("familleNom")) {
				String familleNom = (String)solrDocument.get("familleNom_stored_string");
				if(familleNom != null)
					oEnfantScolaire.setFamilleNom(familleNom);
			}

			if(sauvegardesEnfantScolaire.contains("personneNomComplet")) {
				String personneNomComplet = (String)solrDocument.get("personneNomComplet_stored_string");
				if(personneNomComplet != null)
					oEnfantScolaire.setPersonneNomComplet(personneNomComplet);
			}

			if(sauvegardesEnfantScolaire.contains("personneNomCompletPrefere")) {
				String personneNomCompletPrefere = (String)solrDocument.get("personneNomCompletPrefere_stored_string");
				if(personneNomCompletPrefere != null)
					oEnfantScolaire.setPersonneNomCompletPrefere(personneNomCompletPrefere);
			}

			if(sauvegardesEnfantScolaire.contains("personneNomFormel")) {
				String personneNomFormel = (String)solrDocument.get("personneNomFormel_stored_string");
				if(personneNomFormel != null)
					oEnfantScolaire.setPersonneNomFormel(personneNomFormel);
			}

			if(sauvegardesEnfantScolaire.contains("personneDateNaissance")) {
				Date personneDateNaissance = (Date)solrDocument.get("personneDateNaissance_stored_date");
				if(personneDateNaissance != null)
					oEnfantScolaire.setPersonneDateNaissance(personneDateNaissance);
			}

			if(sauvegardesEnfantScolaire.contains("enfantConditionsMedicales")) {
				String enfantConditionsMedicales = (String)solrDocument.get("enfantConditionsMedicales_stored_string");
				if(enfantConditionsMedicales != null)
					oEnfantScolaire.setEnfantConditionsMedicales(enfantConditionsMedicales);
			}

			if(sauvegardesEnfantScolaire.contains("enfantEcolesPrecedemmentFrequentees")) {
				String enfantEcolesPrecedemmentFrequentees = (String)solrDocument.get("enfantEcolesPrecedemmentFrequentees_stored_string");
				if(enfantEcolesPrecedemmentFrequentees != null)
					oEnfantScolaire.setEnfantEcolesPrecedemmentFrequentees(enfantEcolesPrecedemmentFrequentees);
			}

			if(sauvegardesEnfantScolaire.contains("enfantDescription")) {
				String enfantDescription = (String)solrDocument.get("enfantDescription_stored_string");
				if(enfantDescription != null)
					oEnfantScolaire.setEnfantDescription(enfantDescription);
			}

			if(sauvegardesEnfantScolaire.contains("enfantObjectifs")) {
				String enfantObjectifs = (String)solrDocument.get("enfantObjectifs_stored_string");
				if(enfantObjectifs != null)
					oEnfantScolaire.setEnfantObjectifs(enfantObjectifs);
			}

			if(sauvegardesEnfantScolaire.contains("enfantVaccinsAJour")) {
				Boolean enfantVaccinsAJour = (Boolean)solrDocument.get("enfantVaccinsAJour_stored_boolean");
				if(enfantVaccinsAJour != null)
					oEnfantScolaire.setEnfantVaccinsAJour(enfantVaccinsAJour);
			}

			if(sauvegardesEnfantScolaire.contains("enfantPropre")) {
				Boolean enfantPropre = (Boolean)solrDocument.get("enfantPropre_stored_boolean");
				if(enfantPropre != null)
					oEnfantScolaire.setEnfantPropre(enfantPropre);
			}

			if(sauvegardesEnfantScolaire.contains("enfantNomComplet")) {
				String enfantNomComplet = (String)solrDocument.get("enfantNomComplet_stored_string");
				if(enfantNomComplet != null)
					oEnfantScolaire.setEnfantNomComplet(enfantNomComplet);
			}

			if(sauvegardesEnfantScolaire.contains("enfantId")) {
				String enfantId = (String)solrDocument.get("enfantId_stored_string");
				if(enfantId != null)
					oEnfantScolaire.setEnfantId(enfantId);
			}

			if(sauvegardesEnfantScolaire.contains("pageUrl")) {
				String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
				if(pageUrl != null)
					oEnfantScolaire.setPageUrl(pageUrl);
			}

			if(sauvegardesEnfantScolaire.contains("objetSuggere")) {
				String objetSuggere = (String)solrDocument.get("objetSuggere_suggested");
				oEnfantScolaire.setObjetSuggere(objetSuggere);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.enfant.EnfantScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			EnfantScolaire o = new EnfantScolaire();
			o.requeteSiteEnfantScolaire(requeteSite);
			o.initLoinEnfantScolaire(requeteSite);
			o.indexerEnfantScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerEnfantScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerEnfantScolaire(document);
	}

	public void indexerEnfantScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerEnfantScolaire(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerEnfantScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerEnfantScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerEnfantScolaire(SolrInputDocument document) {
		if(sauvegardesEnfantScolaire != null)
			document.addField("sauvegardesEnfantScolaire_stored_strings", sauvegardesEnfantScolaire);

		if(enfantCle != null) {
			document.addField("enfantCle_indexed_long", enfantCle);
			document.addField("enfantCle_stored_long", enfantCle);
		}
		if(inscriptionCles != null) {
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_indexed_longs", o);
			}
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_stored_longs", o);
			}
		}
		if(familleTri != null) {
			document.addField("familleTri_indexed_int", familleTri);
			document.addField("familleTri_stored_int", familleTri);
		}
		if(enfantTri != null) {
			document.addField("enfantTri_indexed_int", enfantTri);
			document.addField("enfantTri_stored_int", enfantTri);
		}
		if(ecoleCles != null) {
			for(java.lang.Long o : ecoleCles) {
				document.addField("ecoleCles_indexed_longs", o);
			}
			for(java.lang.Long o : ecoleCles) {
				document.addField("ecoleCles_stored_longs", o);
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
		if(saisonCles != null) {
			for(java.lang.Long o : saisonCles) {
				document.addField("saisonCles_indexed_longs", o);
			}
			for(java.lang.Long o : saisonCles) {
				document.addField("saisonCles_stored_longs", o);
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
		if(ageCles != null) {
			for(java.lang.Long o : ageCles) {
				document.addField("ageCles_indexed_longs", o);
			}
			for(java.lang.Long o : ageCles) {
				document.addField("ageCles_stored_longs", o);
			}
		}
		if(personnePrenom != null) {
			document.addField("personnePrenom_indexed_string", personnePrenom);
			document.addField("personnePrenom_stored_string", personnePrenom);
		}
		if(familleNom != null) {
			document.addField("familleNom_indexed_string", familleNom);
			document.addField("familleNom_stored_string", familleNom);
		}
		if(personneNomComplet != null) {
			document.addField("personneNomComplet_indexed_string", personneNomComplet);
			document.addField("personneNomComplet_stored_string", personneNomComplet);
		}
		if(personneNomCompletPrefere != null) {
			document.addField("personneNomCompletPrefere_indexed_string", personneNomCompletPrefere);
			document.addField("personneNomCompletPrefere_stored_string", personneNomCompletPrefere);
		}
		if(personneNomFormel != null) {
			document.addField("personneNomFormel_indexed_string", personneNomFormel);
			document.addField("personneNomFormel_stored_string", personneNomFormel);
		}
		if(personneDateNaissance != null) {
			document.addField("personneDateNaissance_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(personneDateNaissance.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("personneDateNaissance_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(personneDateNaissance.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enfantConditionsMedicales != null) {
			document.addField("enfantConditionsMedicales_indexed_string", enfantConditionsMedicales);
			document.addField("enfantConditionsMedicales_stored_string", enfantConditionsMedicales);
		}
		if(enfantEcolesPrecedemmentFrequentees != null) {
			document.addField("enfantEcolesPrecedemmentFrequentees_indexed_string", enfantEcolesPrecedemmentFrequentees);
			document.addField("enfantEcolesPrecedemmentFrequentees_stored_string", enfantEcolesPrecedemmentFrequentees);
		}
		if(enfantDescription != null) {
			document.addField("enfantDescription_indexed_string", enfantDescription);
			document.addField("enfantDescription_stored_string", enfantDescription);
		}
		if(enfantObjectifs != null) {
			document.addField("enfantObjectifs_indexed_string", enfantObjectifs);
			document.addField("enfantObjectifs_stored_string", enfantObjectifs);
		}
		if(enfantVaccinsAJour != null) {
			document.addField("enfantVaccinsAJour_indexed_boolean", enfantVaccinsAJour);
			document.addField("enfantVaccinsAJour_stored_boolean", enfantVaccinsAJour);
		}
		if(enfantPropre != null) {
			document.addField("enfantPropre_indexed_boolean", enfantPropre);
			document.addField("enfantPropre_stored_boolean", enfantPropre);
		}
		if(enfantNomComplet != null) {
			document.addField("enfantNomComplet_indexed_string", enfantNomComplet);
			document.addField("enfantNomComplet_stored_string", enfantNomComplet);
		}
		if(enfantId != null) {
			document.addField("enfantId_indexed_string", enfantId);
			document.addField("enfantId_stored_string", enfantId);
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

	public void desindexerEnfantScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinEnfantScolaire(requeteSite);
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
		stockerEnfantScolaire(solrDocument);
	}
	public void stockerEnfantScolaire(SolrDocument solrDocument) {
		EnfantScolaire oEnfantScolaire = (EnfantScolaire)this;

		Long enfantCle = (Long)solrDocument.get("enfantCle_stored_long");
		if(enfantCle != null)
			oEnfantScolaire.setEnfantCle(enfantCle);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oEnfantScolaire.inscriptionCles.addAll(inscriptionCles);

		Integer familleTri = (Integer)solrDocument.get("familleTri_stored_int");
		if(familleTri != null)
			oEnfantScolaire.setFamilleTri(familleTri);

		Integer enfantTri = (Integer)solrDocument.get("enfantTri_stored_int");
		if(enfantTri != null)
			oEnfantScolaire.setEnfantTri(enfantTri);

		List<Long> ecoleCles = (List<Long>)solrDocument.get("ecoleCles_stored_longs");
		if(ecoleCles != null)
			oEnfantScolaire.ecoleCles.addAll(ecoleCles);

		List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
		if(anneeCles != null)
			oEnfantScolaire.anneeCles.addAll(anneeCles);

		List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
		if(saisonCles != null)
			oEnfantScolaire.saisonCles.addAll(saisonCles);

		List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
		if(sessionCles != null)
			oEnfantScolaire.sessionCles.addAll(sessionCles);

		List<Long> ageCles = (List<Long>)solrDocument.get("ageCles_stored_longs");
		if(ageCles != null)
			oEnfantScolaire.ageCles.addAll(ageCles);

		String personnePrenom = (String)solrDocument.get("personnePrenom_stored_string");
		if(personnePrenom != null)
			oEnfantScolaire.setPersonnePrenom(personnePrenom);

		String familleNom = (String)solrDocument.get("familleNom_stored_string");
		if(familleNom != null)
			oEnfantScolaire.setFamilleNom(familleNom);

		String personneNomComplet = (String)solrDocument.get("personneNomComplet_stored_string");
		if(personneNomComplet != null)
			oEnfantScolaire.setPersonneNomComplet(personneNomComplet);

		String personneNomCompletPrefere = (String)solrDocument.get("personneNomCompletPrefere_stored_string");
		if(personneNomCompletPrefere != null)
			oEnfantScolaire.setPersonneNomCompletPrefere(personneNomCompletPrefere);

		String personneNomFormel = (String)solrDocument.get("personneNomFormel_stored_string");
		if(personneNomFormel != null)
			oEnfantScolaire.setPersonneNomFormel(personneNomFormel);

		Date personneDateNaissance = (Date)solrDocument.get("personneDateNaissance_stored_date");
		if(personneDateNaissance != null)
			oEnfantScolaire.setPersonneDateNaissance(personneDateNaissance);

		String enfantConditionsMedicales = (String)solrDocument.get("enfantConditionsMedicales_stored_string");
		if(enfantConditionsMedicales != null)
			oEnfantScolaire.setEnfantConditionsMedicales(enfantConditionsMedicales);

		String enfantEcolesPrecedemmentFrequentees = (String)solrDocument.get("enfantEcolesPrecedemmentFrequentees_stored_string");
		if(enfantEcolesPrecedemmentFrequentees != null)
			oEnfantScolaire.setEnfantEcolesPrecedemmentFrequentees(enfantEcolesPrecedemmentFrequentees);

		String enfantDescription = (String)solrDocument.get("enfantDescription_stored_string");
		if(enfantDescription != null)
			oEnfantScolaire.setEnfantDescription(enfantDescription);

		String enfantObjectifs = (String)solrDocument.get("enfantObjectifs_stored_string");
		if(enfantObjectifs != null)
			oEnfantScolaire.setEnfantObjectifs(enfantObjectifs);

		Boolean enfantVaccinsAJour = (Boolean)solrDocument.get("enfantVaccinsAJour_stored_boolean");
		if(enfantVaccinsAJour != null)
			oEnfantScolaire.setEnfantVaccinsAJour(enfantVaccinsAJour);

		Boolean enfantPropre = (Boolean)solrDocument.get("enfantPropre_stored_boolean");
		if(enfantPropre != null)
			oEnfantScolaire.setEnfantPropre(enfantPropre);

		String enfantNomComplet = (String)solrDocument.get("enfantNomComplet_stored_string");
		if(enfantNomComplet != null)
			oEnfantScolaire.setEnfantNomComplet(enfantNomComplet);

		String enfantId = (String)solrDocument.get("enfantId_stored_string");
		if(enfantId != null)
			oEnfantScolaire.setEnfantId(enfantId);

		String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
		if(pageUrl != null)
			oEnfantScolaire.setPageUrl(pageUrl);

		String objetSuggere = (String)solrDocument.get("objetSuggere_suggested");
		oEnfantScolaire.setObjetSuggere(objetSuggere);

		super.stockerCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), personnePrenom, familleNom, personneNomComplet, personneNomCompletPrefere, personneNomFormel, enfantConditionsMedicales, enfantEcolesPrecedemmentFrequentees, enfantDescription, enfantObjectifs, enfantVaccinsAJour, enfantPropre);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof EnfantScolaire))
			return false;
		EnfantScolaire that = (EnfantScolaire)o;
		return super.equals(o)
				&& Objects.equals( personnePrenom, that.personnePrenom )
				&& Objects.equals( familleNom, that.familleNom )
				&& Objects.equals( personneNomComplet, that.personneNomComplet )
				&& Objects.equals( personneNomCompletPrefere, that.personneNomCompletPrefere )
				&& Objects.equals( personneNomFormel, that.personneNomFormel )
				&& Objects.equals( enfantConditionsMedicales, that.enfantConditionsMedicales )
				&& Objects.equals( enfantEcolesPrecedemmentFrequentees, that.enfantEcolesPrecedemmentFrequentees )
				&& Objects.equals( enfantDescription, that.enfantDescription )
				&& Objects.equals( enfantObjectifs, that.enfantObjectifs )
				&& Objects.equals( enfantVaccinsAJour, that.enfantVaccinsAJour )
				&& Objects.equals( enfantPropre, that.enfantPropre );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EnfantScolaire { ");
		sb.append( "personnePrenom: \"" ).append(personnePrenom).append( "\"" );
		sb.append( ", familleNom: \"" ).append(familleNom).append( "\"" );
		sb.append( ", personneNomComplet: \"" ).append(personneNomComplet).append( "\"" );
		sb.append( ", personneNomCompletPrefere: \"" ).append(personneNomCompletPrefere).append( "\"" );
		sb.append( ", personneNomFormel: \"" ).append(personneNomFormel).append( "\"" );
		sb.append( ", enfantConditionsMedicales: \"" ).append(enfantConditionsMedicales).append( "\"" );
		sb.append( ", enfantEcolesPrecedemmentFrequentees: \"" ).append(enfantEcolesPrecedemmentFrequentees).append( "\"" );
		sb.append( ", enfantDescription: \"" ).append(enfantDescription).append( "\"" );
		sb.append( ", enfantObjectifs: \"" ).append(enfantObjectifs).append( "\"" );
		sb.append( ", enfantVaccinsAJour: " ).append(enfantVaccinsAJour);
		sb.append( ", enfantPropre: " ).append(enfantPropre);
		sb.append(" }");
		return sb.toString();
	}
}
