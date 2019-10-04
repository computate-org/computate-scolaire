package org.computate.scolaire.frFR.inscription;

import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.computate.scolaire.frFR.couverture.Couverture;
import java.lang.Long;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalTime;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.frFR.enfant.EnfantScolaire;
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
import org.computate.scolaire.frFR.bloc.BlocScolaire;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe objectSuggest dans Solr</a>
 * <br/>
 **/
public abstract class InscriptionScolaireGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(InscriptionScolaire.class);

	public static final String InscriptionScolaire_UnNom = "une inscription";
	public static final String InscriptionScolaire_Ce = "cette ";
	public static final String InscriptionScolaire_CeNom = "cette inscription";
	public static final String InscriptionScolaire_Un = "une ";
	public static final String InscriptionScolaire_LeNom = "l'inscription";
	public static final String InscriptionScolaire_NomSingulier = "inscription";
	public static final String InscriptionScolaire_NomPluriel = "inscriptions";
	public static final String InscriptionScolaire_NomActuel = "inscription actuelle";
	public static final String InscriptionScolaire_Tous = "all ";
	public static final String InscriptionScolaire_TousNom = "toutes les inscriptions";
	public static final String InscriptionScolaire_RechercherTousNomPar = "rechercher inscriptions par ";
	public static final String InscriptionScolaire_RechercherTousNom = "rechercher inscriptions";
	public static final String InscriptionScolaire_LesNoms = "les inscriptions";
	public static final String InscriptionScolaire_AucunNomTrouve = "aucune inscription trouvée";
	public static final String InscriptionScolaire_NomVar = "inscription";
	public static final String InscriptionScolaire_DeNom = "d'inscription";
	public static final String InscriptionScolaire_NomAdjectifSingulier = "inscription";
	public static final String InscriptionScolaire_NomAdjectifPluriel = "inscriptions";
	public static final String InscriptionScolaire_Couleur = "purple";
	public static final String InscriptionScolaire_IconeGroupe = "solid";
	public static final String InscriptionScolaire_IconeNom = "edit";

	////////////////////
	// inscriptionCle //
	////////////////////

	/**	L'entité « inscriptionCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long inscriptionCle;
	@JsonIgnore
	public Couverture<Long> inscriptionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("inscriptionCle").o(inscriptionCle);

	/**	<br/>L'entité « inscriptionCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCle">Trouver l'entité inscriptionCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionCle(Couverture<Long> c);

	public Long getInscriptionCle() {
		return inscriptionCle;
	}

	public void setInscriptionCle(Long inscriptionCle) {
		this.inscriptionCle = inscriptionCle;
		this.inscriptionCleCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionCle(String o) {
		if(NumberUtils.isParsable(o))
			this.inscriptionCle = Long.parseLong(o);
		this.inscriptionCleCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionCleInit() {
		if(!inscriptionCleCouverture.dejaInitialise) {
			_inscriptionCle(inscriptionCleCouverture);
			if(inscriptionCle == null)
				setInscriptionCle(inscriptionCleCouverture.o);
		}
		inscriptionCleCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Long solrInscriptionCle() {
		return inscriptionCle;
	}

	public String strInscriptionCle() {
		return inscriptionCle == null ? "" : inscriptionCle.toString();
	}

	public String jsonInscriptionCle() {
		return inscriptionCle == null ? "" : inscriptionCle.toString();
	}

	public String nomAffichageInscriptionCle() {
		return "clé";
	}

	public String htmTooltipInscriptionCle() {
		return null;
	}

	public String htmInscriptionCle() {
		return inscriptionCle == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionCle());
	}

	public void htmInscriptionCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "InscriptionCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "InscriptionCle() {");
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
				r.l("				data: {\"setInscriptionCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageInscriptionCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"inscriptionCle\"");
							r.s(" value=\"", htmInscriptionCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmInscriptionCle());
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
	@JsonIgnore
	public Couverture<List<Long>> blocClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("blocCles").o(blocCles);

	/**	<br/>L'entité « blocCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocCles">Trouver l'entité blocCles dans Solr</a>
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
	public InscriptionScolaire addBlocCles(Long...objets) {
		for(Long o : objets) {
			addBlocCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addBlocCles(Long o) {
		if(o != null && !blocCles.contains(o))
			this.blocCles.add(o);
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setBlocCles(JsonArray objets) {
		blocCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlocCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addBlocCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addBlocCles(p);
		}
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocClesInit() {
		if(!blocClesCouverture.dejaInitialise) {
			_blocCles(blocCles);
		}
		blocClesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public List<Long> solrBlocCles() {
		return blocCles;
	}

	public String strBlocCles() {
		return blocCles == null ? "" : blocCles.toString();
	}

	public String jsonBlocCles() {
		return blocCles == null ? "" : blocCles.toString();
	}

	public String nomAffichageBlocCles() {
		return "blocs";
	}

	public String htmTooltipBlocCles() {
		return null;
	}

	public String htmBlocCles() {
		return blocCles == null ? "" : StringEscapeUtils.escapeHtml4(strBlocCles());
	}

	public void htmBlocCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocCles() {");
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
	// blocRecherche //
	///////////////////

	/**	L'entité « blocRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<BlocScolaire>(). 
	 */
	@JsonIgnore
	protected ListeRecherche<BlocScolaire> blocRecherche = new ListeRecherche<BlocScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<BlocScolaire>> blocRechercheCouverture = new Couverture<ListeRecherche<BlocScolaire>>().p(this).c(ListeRecherche.class).var("blocRecherche").o(blocRecherche);

	/**	<br/>L'entité « blocRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<BlocScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocRecherche">Trouver l'entité blocRecherche dans Solr</a>
	 * <br/>
	 * @param blocRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _blocRecherche(ListeRecherche<BlocScolaire> l);

	public ListeRecherche<BlocScolaire> getBlocRecherche() {
		return blocRecherche;
	}

	public void setBlocRecherche(ListeRecherche<BlocScolaire> blocRecherche) {
		this.blocRecherche = blocRecherche;
		this.blocRechercheCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire blocRechercheInit() {
		if(!blocRechercheCouverture.dejaInitialise) {
			_blocRecherche(blocRecherche);
		}
		blocRecherche.initLoinPourClasse(requeteSite_);
		blocRechercheCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	//////////
	// bloc //
	//////////

	/**	L'entité « bloc »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected BlocScolaire bloc;
	@JsonIgnore
	public Couverture<BlocScolaire> blocCouverture = new Couverture<BlocScolaire>().p(this).c(BlocScolaire.class).var("bloc").o(bloc);

	/**	<br/>L'entité « bloc »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:bloc">Trouver l'entité bloc dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _bloc(Couverture<BlocScolaire> c);

	public BlocScolaire getBloc() {
		return bloc;
	}

	public void setBloc(BlocScolaire bloc) {
		this.bloc = bloc;
		this.blocCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire blocInit() {
		if(!blocCouverture.dejaInitialise) {
			_bloc(blocCouverture);
			if(bloc == null)
				setBloc(blocCouverture.o);
		}
		if(bloc != null)
			bloc.initLoinPourClasse(requeteSite_);
		blocCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
	public InscriptionScolaire setEcoleCle(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleCle = Long.parseLong(o);
		this.ecoleCleCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Long solrEcoleCle() {
		return ecoleCle;
	}

	public String strEcoleCle() {
		return ecoleCle == null ? "" : ecoleCle.toString();
	}

	public String jsonEcoleCle() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "EcoleCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "EcoleCle() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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
	public InscriptionScolaire setAnneeCle(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeCle = Long.parseLong(o);
		this.anneeCleCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire anneeCleInit() {
		if(!anneeCleCouverture.dejaInitialise) {
			_anneeCle(anneeCleCouverture);
			if(anneeCle == null)
				setAnneeCle(anneeCleCouverture.o);
		}
		anneeCleCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Long solrAnneeCle() {
		return anneeCle;
	}

	public String strAnneeCle() {
		return anneeCle == null ? "" : anneeCle.toString();
	}

	public String jsonAnneeCle() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "AnneeCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "AnneeCle() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCle">Trouver l'entité saisonCle dans Solr</a>
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
	public InscriptionScolaire setSaisonCle(String o) {
		if(NumberUtils.isParsable(o))
			this.saisonCle = Long.parseLong(o);
		this.saisonCleCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire saisonCleInit() {
		if(!saisonCleCouverture.dejaInitialise) {
			_saisonCle(saisonCleCouverture);
			if(saisonCle == null)
				setSaisonCle(saisonCleCouverture.o);
		}
		saisonCleCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Long solrSaisonCle() {
		return saisonCle;
	}

	public String strSaisonCle() {
		return saisonCle == null ? "" : saisonCle.toString();
	}

	public String jsonSaisonCle() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "SaisonCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "SaisonCle() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCle">Trouver l'entité sessionCle dans Solr</a>
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
	public InscriptionScolaire setSessionCle(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionCle = Long.parseLong(o);
		this.sessionCleCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire sessionCleInit() {
		if(!sessionCleCouverture.dejaInitialise) {
			_sessionCle(sessionCleCouverture);
			if(sessionCle == null)
				setSessionCle(sessionCleCouverture.o);
		}
		sessionCleCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Long solrSessionCle() {
		return sessionCle;
	}

	public String strSessionCle() {
		return sessionCle == null ? "" : sessionCle.toString();
	}

	public String jsonSessionCle() {
		return sessionCle == null ? "" : sessionCle.toString();
	}

	public String nomAffichageSessionCle() {
		return "session";
	}

	public String htmTooltipSessionCle() {
		return null;
	}

	public String htmSessionCle() {
		return sessionCle == null ? "" : StringEscapeUtils.escapeHtml4(strSessionCle());
	}

	public void htmSessionCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "SessionCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "SessionCle() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCle">Trouver l'entité ageCle dans Solr</a>
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
	public InscriptionScolaire setAgeCle(String o) {
		if(NumberUtils.isParsable(o))
			this.ageCle = Long.parseLong(o);
		this.ageCleCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire ageCleInit() {
		if(!ageCleCouverture.dejaInitialise) {
			_ageCle(ageCleCouverture);
			if(ageCle == null)
				setAgeCle(ageCleCouverture.o);
		}
		ageCleCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Long solrAgeCle() {
		return ageCle;
	}

	public String strAgeCle() {
		return ageCle == null ? "" : ageCle.toString();
	}

	public String jsonAgeCle() {
		return ageCle == null ? "" : ageCle.toString();
	}

	public String nomAffichageAgeCle() {
		return "âge";
	}

	public String htmTooltipAgeCle() {
		return null;
	}

	public String htmAgeCle() {
		return ageCle == null ? "" : StringEscapeUtils.escapeHtml4(strAgeCle());
	}

	public void htmAgeCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "AgeCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "AgeCle() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocCle">Trouver l'entité blocCle dans Solr</a>
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
	public InscriptionScolaire setBlocCle(String o) {
		if(NumberUtils.isParsable(o))
			this.blocCle = Long.parseLong(o);
		this.blocCleCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocCleInit() {
		if(!blocCleCouverture.dejaInitialise) {
			_blocCle(blocCleCouverture);
			if(blocCle == null)
				setBlocCle(blocCleCouverture.o);
		}
		blocCleCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Long solrBlocCle() {
		return blocCle;
	}

	public String strBlocCle() {
		return blocCle == null ? "" : blocCle.toString();
	}

	public String jsonBlocCle() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocCle() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantCle">Trouver l'entité enfantCle dans Solr</a>
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
	public InscriptionScolaire setEnfantCle(String o) {
		if(NumberUtils.isParsable(o))
			this.enfantCle = Long.parseLong(o);
		this.enfantCleCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire enfantCleInit() {
		if(!enfantCleCouverture.dejaInitialise) {
			_enfantCle(enfantCleCouverture);
			if(enfantCle == null)
				setEnfantCle(enfantCleCouverture.o);
		}
		enfantCleCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
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
		return "enfants";
	}

	public String htmTooltipEnfantCle() {
		return null;
	}

	public String htmEnfantCle() {
		return enfantCle == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantCle());
	}

	public void htmEnfantCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "EnfantCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "EnfantCle() {");
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

	//////////////
	// mereCles //
	//////////////

	/**	L'entité « mereCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> mereCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> mereClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("mereCles").o(mereCles);

	/**	<br/>L'entité « mereCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereCles">Trouver l'entité mereCles dans Solr</a>
	 * <br/>
	 * @param mereCles est l'entité déjà construit. 
	 **/
	protected abstract void _mereCles(List<Long> o);

	public List<Long> getMereCles() {
		return mereCles;
	}

	public void setMereCles(List<Long> mereCles) {
		this.mereCles = mereCles;
		this.mereClesCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addMereCles(Long...objets) {
		for(Long o : objets) {
			addMereCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addMereCles(Long o) {
		if(o != null && !mereCles.contains(o))
			this.mereCles.add(o);
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setMereCles(JsonArray objets) {
		mereCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addMereCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addMereCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addMereCles(p);
		}
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire mereClesInit() {
		if(!mereClesCouverture.dejaInitialise) {
			_mereCles(mereCles);
		}
		mereClesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public List<Long> solrMereCles() {
		return mereCles;
	}

	public String strMereCles() {
		return mereCles == null ? "" : mereCles.toString();
	}

	public String jsonMereCles() {
		return mereCles == null ? "" : mereCles.toString();
	}

	public String nomAffichageMereCles() {
		return "mères";
	}

	public String htmTooltipMereCles() {
		return null;
	}

	public String htmMereCles() {
		return mereCles == null ? "" : StringEscapeUtils.escapeHtml4(strMereCles());
	}

	public void htmMereCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "MereCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "MereCles() {");
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
				r.l("				data: {\"setMereCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageMereCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"mereCles\"");
							r.s(" value=\"", htmMereCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmMereCles());
			}
			r.l("</div>");
		}
	}

	//////////////
	// pereCles //
	//////////////

	/**	L'entité « pereCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> pereCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> pereClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("pereCles").o(pereCles);

	/**	<br/>L'entité « pereCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereCles">Trouver l'entité pereCles dans Solr</a>
	 * <br/>
	 * @param pereCles est l'entité déjà construit. 
	 **/
	protected abstract void _pereCles(List<Long> o);

	public List<Long> getPereCles() {
		return pereCles;
	}

	public void setPereCles(List<Long> pereCles) {
		this.pereCles = pereCles;
		this.pereClesCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addPereCles(Long...objets) {
		for(Long o : objets) {
			addPereCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addPereCles(Long o) {
		if(o != null && !pereCles.contains(o))
			this.pereCles.add(o);
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setPereCles(JsonArray objets) {
		pereCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPereCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addPereCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPereCles(p);
		}
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire pereClesInit() {
		if(!pereClesCouverture.dejaInitialise) {
			_pereCles(pereCles);
		}
		pereClesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public List<Long> solrPereCles() {
		return pereCles;
	}

	public String strPereCles() {
		return pereCles == null ? "" : pereCles.toString();
	}

	public String jsonPereCles() {
		return pereCles == null ? "" : pereCles.toString();
	}

	public String nomAffichagePereCles() {
		return "pères";
	}

	public String htmTooltipPereCles() {
		return null;
	}

	public String htmPereCles() {
		return pereCles == null ? "" : StringEscapeUtils.escapeHtml4(strPereCles());
	}

	public void htmPereCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "PereCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "PereCles() {");
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
				r.l("				data: {\"setPereCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePereCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"pereCles\"");
							r.s(" value=\"", htmPereCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPereCles());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// gardienCles //
	/////////////////

	/**	L'entité « gardienCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> gardienCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> gardienClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("gardienCles").o(gardienCles);

	/**	<br/>L'entité « gardienCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienCles">Trouver l'entité gardienCles dans Solr</a>
	 * <br/>
	 * @param gardienCles est l'entité déjà construit. 
	 **/
	protected abstract void _gardienCles(List<Long> o);

	public List<Long> getGardienCles() {
		return gardienCles;
	}

	public void setGardienCles(List<Long> gardienCles) {
		this.gardienCles = gardienCles;
		this.gardienClesCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addGardienCles(Long...objets) {
		for(Long o : objets) {
			addGardienCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addGardienCles(Long o) {
		if(o != null && !gardienCles.contains(o))
			this.gardienCles.add(o);
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setGardienCles(JsonArray objets) {
		gardienCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGardienCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addGardienCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addGardienCles(p);
		}
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire gardienClesInit() {
		if(!gardienClesCouverture.dejaInitialise) {
			_gardienCles(gardienCles);
		}
		gardienClesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public List<Long> solrGardienCles() {
		return gardienCles;
	}

	public String strGardienCles() {
		return gardienCles == null ? "" : gardienCles.toString();
	}

	public String jsonGardienCles() {
		return gardienCles == null ? "" : gardienCles.toString();
	}

	public String nomAffichageGardienCles() {
		return "gardiens";
	}

	public String htmTooltipGardienCles() {
		return null;
	}

	public String htmGardienCles() {
		return gardienCles == null ? "" : StringEscapeUtils.escapeHtml4(strGardienCles());
	}

	public void htmGardienCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "GardienCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "GardienCles() {");
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
				r.l("				data: {\"setGardienCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageGardienCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"gardienCles\"");
							r.s(" value=\"", htmGardienCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmGardienCles());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// paiementCles //
	//////////////////

	/**	L'entité « paiementCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	protected List<Long> paiementCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> paiementClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("paiementCles").o(paiementCles);

	/**	<br/>L'entité « paiementCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementCles">Trouver l'entité paiementCles dans Solr</a>
	 * <br/>
	 * @param paiementCles est l'entité déjà construit. 
	 **/
	protected abstract void _paiementCles(List<Long> o);

	public List<Long> getPaiementCles() {
		return paiementCles;
	}

	public void setPaiementCles(List<Long> paiementCles) {
		this.paiementCles = paiementCles;
		this.paiementClesCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addPaiementCles(Long...objets) {
		for(Long o : objets) {
			addPaiementCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addPaiementCles(Long o) {
		if(o != null && !paiementCles.contains(o))
			this.paiementCles.add(o);
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setPaiementCles(JsonArray objets) {
		paiementCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPaiementCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addPaiementCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPaiementCles(p);
		}
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire paiementClesInit() {
		if(!paiementClesCouverture.dejaInitialise) {
			_paiementCles(paiementCles);
		}
		paiementClesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public List<Long> solrPaiementCles() {
		return paiementCles;
	}

	public String strPaiementCles() {
		return paiementCles == null ? "" : paiementCles.toString();
	}

	public String jsonPaiementCles() {
		return paiementCles == null ? "" : paiementCles.toString();
	}

	public String nomAffichagePaiementCles() {
		return "paiements";
	}

	public String htmTooltipPaiementCles() {
		return null;
	}

	public String htmPaiementCles() {
		return paiementCles == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementCles());
	}

	public void htmPaiementCles(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "PaiementCles\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "PaiementCles() {");
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
				r.l("				data: {\"setPaiementCles\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePaiementCles()), "</span>");
				r.s("			<input");
							r.s(" name=\"paiementCles\"");
							r.s(" value=\"", htmPaiementCles(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPaiementCles());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:scolaireTri">Trouver l'entité scolaireTri dans Solr</a>
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
	public InscriptionScolaire setScolaireTri(String o) {
		if(NumberUtils.isParsable(o))
			this.scolaireTri = Integer.parseInt(o);
		this.scolaireTriCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire scolaireTriInit() {
		if(!scolaireTriCouverture.dejaInitialise) {
			_scolaireTri(scolaireTriCouverture);
			if(scolaireTri == null)
				setScolaireTri(scolaireTriCouverture.o);
		}
		scolaireTriCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrScolaireTri() {
		return scolaireTri;
	}

	public String strScolaireTri() {
		return scolaireTri == null ? "" : scolaireTri.toString();
	}

	public String jsonScolaireTri() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "ScolaireTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "ScolaireTri() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleTri">Trouver l'entité ecoleTri dans Solr</a>
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
	public InscriptionScolaire setEcoleTri(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleTri = Integer.parseInt(o);
		this.ecoleTriCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire ecoleTriInit() {
		if(!ecoleTriCouverture.dejaInitialise) {
			_ecoleTri(ecoleTriCouverture);
			if(ecoleTri == null)
				setEcoleTri(ecoleTriCouverture.o);
		}
		ecoleTriCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrEcoleTri() {
		return ecoleTri;
	}

	public String strEcoleTri() {
		return ecoleTri == null ? "" : ecoleTri.toString();
	}

	public String jsonEcoleTri() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "EcoleTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "EcoleTri() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeTri">Trouver l'entité anneeTri dans Solr</a>
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
	public InscriptionScolaire setAnneeTri(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeTri = Integer.parseInt(o);
		this.anneeTriCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire anneeTriInit() {
		if(!anneeTriCouverture.dejaInitialise) {
			_anneeTri(anneeTriCouverture);
			if(anneeTri == null)
				setAnneeTri(anneeTriCouverture.o);
		}
		anneeTriCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrAnneeTri() {
		return anneeTri;
	}

	public String strAnneeTri() {
		return anneeTri == null ? "" : anneeTri.toString();
	}

	public String jsonAnneeTri() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "AnneeTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "AnneeTri() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonTri">Trouver l'entité saisonTri dans Solr</a>
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
	public InscriptionScolaire setSaisonTri(String o) {
		if(NumberUtils.isParsable(o))
			this.saisonTri = Integer.parseInt(o);
		this.saisonTriCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire saisonTriInit() {
		if(!saisonTriCouverture.dejaInitialise) {
			_saisonTri(saisonTriCouverture);
			if(saisonTri == null)
				setSaisonTri(saisonTriCouverture.o);
		}
		saisonTriCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrSaisonTri() {
		return saisonTri;
	}

	public String strSaisonTri() {
		return saisonTri == null ? "" : saisonTri.toString();
	}

	public String jsonSaisonTri() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "SaisonTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "SaisonTri() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionTri">Trouver l'entité sessionTri dans Solr</a>
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
	public InscriptionScolaire setSessionTri(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionTri = Integer.parseInt(o);
		this.sessionTriCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire sessionTriInit() {
		if(!sessionTriCouverture.dejaInitialise) {
			_sessionTri(sessionTriCouverture);
			if(sessionTri == null)
				setSessionTri(sessionTriCouverture.o);
		}
		sessionTriCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrSessionTri() {
		return sessionTri;
	}

	public String strSessionTri() {
		return sessionTri == null ? "" : sessionTri.toString();
	}

	public String jsonSessionTri() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "SessionTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "SessionTri() {");
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

	////////////
	// ageTri //
	////////////

	/**	L'entité « ageTri »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer ageTri;
	@JsonIgnore
	public Couverture<Integer> ageTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ageTri").o(ageTri);

	/**	<br/>L'entité « ageTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageTri">Trouver l'entité ageTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageTri(Couverture<Integer> c);

	public Integer getAgeTri() {
		return ageTri;
	}

	public void setAgeTri(Integer ageTri) {
		this.ageTri = ageTri;
		this.ageTriCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setAgeTri(String o) {
		if(NumberUtils.isParsable(o))
			this.ageTri = Integer.parseInt(o);
		this.ageTriCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire ageTriInit() {
		if(!ageTriCouverture.dejaInitialise) {
			_ageTri(ageTriCouverture);
			if(ageTri == null)
				setAgeTri(ageTriCouverture.o);
		}
		ageTriCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrAgeTri() {
		return ageTri;
	}

	public String strAgeTri() {
		return ageTri == null ? "" : ageTri.toString();
	}

	public String jsonAgeTri() {
		return ageTri == null ? "" : ageTri.toString();
	}

	public String nomAffichageAgeTri() {
		return null;
	}

	public String htmTooltipAgeTri() {
		return null;
	}

	public String htmAgeTri() {
		return ageTri == null ? "" : StringEscapeUtils.escapeHtml4(strAgeTri());
	}

	public void htmAgeTri(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "AgeTri\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "AgeTri() {");
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
				r.l("				data: {\"setAgeTri\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAgeTri()), "</span>");
				r.s("			<input");
							r.s(" name=\"ageTri\"");
							r.s(" value=\"", htmAgeTri(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAgeTri());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// enfantRecherche //
	/////////////////////

	/**	L'entité « enfantRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<EnfantScolaire>(). 
	 */
	@JsonIgnore
	protected ListeRecherche<EnfantScolaire> enfantRecherche = new ListeRecherche<EnfantScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<EnfantScolaire>> enfantRechercheCouverture = new Couverture<ListeRecherche<EnfantScolaire>>().p(this).c(ListeRecherche.class).var("enfantRecherche").o(enfantRecherche);

	/**	<br/>L'entité « enfantRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<EnfantScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantRecherche">Trouver l'entité enfantRecherche dans Solr</a>
	 * <br/>
	 * @param enfantRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _enfantRecherche(ListeRecherche<EnfantScolaire> l);

	public ListeRecherche<EnfantScolaire> getEnfantRecherche() {
		return enfantRecherche;
	}

	public void setEnfantRecherche(ListeRecherche<EnfantScolaire> enfantRecherche) {
		this.enfantRecherche = enfantRecherche;
		this.enfantRechercheCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire enfantRechercheInit() {
		if(!enfantRechercheCouverture.dejaInitialise) {
			_enfantRecherche(enfantRecherche);
		}
		enfantRecherche.initLoinPourClasse(requeteSite_);
		enfantRechercheCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	/////////////
	// enfant_ //
	/////////////

	/**	L'entité « enfant_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected EnfantScolaire enfant_;
	@JsonIgnore
	public Couverture<EnfantScolaire> enfant_Couverture = new Couverture<EnfantScolaire>().p(this).c(EnfantScolaire.class).var("enfant_").o(enfant_);

	/**	<br/>L'entité « enfant_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfant_">Trouver l'entité enfant_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfant_(Couverture<EnfantScolaire> c);

	public EnfantScolaire getEnfant_() {
		return enfant_;
	}

	public void setEnfant_(EnfantScolaire enfant_) {
		this.enfant_ = enfant_;
		this.enfant_Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire enfant_Init() {
		if(!enfant_Couverture.dejaInitialise) {
			_enfant_(enfant_Couverture);
			if(enfant_ == null)
				setEnfant_(enfant_Couverture.o);
		}
		enfant_Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantNomComplet">Trouver l'entité enfantNomComplet dans Solr</a>
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
	protected InscriptionScolaire enfantNomCompletInit() {
		if(!enfantNomCompletCouverture.dejaInitialise) {
			_enfantNomComplet(enfantNomCompletCouverture);
			if(enfantNomComplet == null)
				setEnfantNomComplet(enfantNomCompletCouverture.o);
		}
		enfantNomCompletCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
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
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEnfantNomComplet() {
		return null;
	}

	public String htmEnfantNomComplet() {
		return enfantNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantNomComplet());
	}

	public void htmEnfantNomComplet(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "EnfantNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "EnfantNomComplet() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
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
	protected InscriptionScolaire ecoleNomCompletInit() {
		if(!ecoleNomCompletCouverture.dejaInitialise) {
			_ecoleNomComplet(ecoleNomCompletCouverture);
			if(ecoleNomComplet == null)
				setEcoleNomComplet(ecoleNomCompletCouverture.o);
		}
		ecoleNomCompletCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEcoleNomComplet() {
		return ecoleNomComplet;
	}

	public String strEcoleNomComplet() {
		return ecoleNomComplet == null ? "" : ecoleNomComplet;
	}

	public String jsonEcoleNomComplet() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "EcoleNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "EcoleNomComplet() {");
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
	protected Integer anneeDebut;
	@JsonIgnore
	public Couverture<Integer> anneeDebutCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeDebut").o(anneeDebut);

	/**	<br/>L'entité « anneeDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeDebut(Couverture<Integer> c);

	public Integer getAnneeDebut() {
		return anneeDebut;
	}

	public void setAnneeDebut(Integer anneeDebut) {
		this.anneeDebut = anneeDebut;
		this.anneeDebutCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setAnneeDebut(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeDebut = Integer.parseInt(o);
		this.anneeDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire anneeDebutInit() {
		if(!anneeDebutCouverture.dejaInitialise) {
			_anneeDebut(anneeDebutCouverture);
			if(anneeDebut == null)
				setAnneeDebut(anneeDebutCouverture.o);
		}
		anneeDebutCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrAnneeDebut() {
		return anneeDebut;
	}

	public String strAnneeDebut() {
		return anneeDebut == null ? "" : anneeDebut.toString();
	}

	public String jsonAnneeDebut() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "AnneeDebut\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "AnneeDebut() {");
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
	protected Integer anneeFin;
	@JsonIgnore
	public Couverture<Integer> anneeFinCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeFin").o(anneeFin);

	/**	<br/>L'entité « anneeFin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeFin(Couverture<Integer> c);

	public Integer getAnneeFin() {
		return anneeFin;
	}

	public void setAnneeFin(Integer anneeFin) {
		this.anneeFin = anneeFin;
		this.anneeFinCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setAnneeFin(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeFin = Integer.parseInt(o);
		this.anneeFinCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire anneeFinInit() {
		if(!anneeFinCouverture.dejaInitialise) {
			_anneeFin(anneeFinCouverture);
			if(anneeFin == null)
				setAnneeFin(anneeFinCouverture.o);
		}
		anneeFinCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrAnneeFin() {
		return anneeFin;
	}

	public String strAnneeFin() {
		return anneeFin == null ? "" : anneeFin.toString();
	}

	public String jsonAnneeFin() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "AnneeFin\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "AnneeFin() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonJourDebut">Trouver l'entité saisonJourDebut dans Solr</a>
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
	public InscriptionScolaire setSaisonJourDebut(Instant o) {
		this.saisonJourDebut = LocalDate.from(o);
		this.saisonJourDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setSaisonJourDebut(String o) {
		this.saisonJourDebut = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.saisonJourDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setSaisonJourDebut(Date o) {
		this.saisonJourDebut = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.saisonJourDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire saisonJourDebutInit() {
		if(!saisonJourDebutCouverture.dejaInitialise) {
			_saisonJourDebut(saisonJourDebutCouverture);
			if(saisonJourDebut == null)
				setSaisonJourDebut(saisonJourDebutCouverture.o);
		}
		saisonJourDebutCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrSaisonJourDebut() {
		return saisonJourDebut == null ? null : Date.from(saisonJourDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSaisonJourDebut() {
		return saisonJourDebut == null ? "" : saisonJourDebut.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonSaisonJourDebut() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "SaisonJourDebut\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "SaisonJourDebut() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonEte">Trouver l'entité saisonEte dans Solr</a>
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
	public InscriptionScolaire setSaisonEte(String o) {
		this.saisonEte = Boolean.parseBoolean(o);
		this.saisonEteCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire saisonEteInit() {
		if(!saisonEteCouverture.dejaInitialise) {
			_saisonEte(saisonEteCouverture);
			if(saisonEte == null)
				setSaisonEte(saisonEteCouverture.o);
		}
		saisonEteCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrSaisonEte() {
		return saisonEte;
	}

	public String strSaisonEte() {
		return saisonEte == null ? "" : saisonEte.toString();
	}

	public String jsonSaisonEte() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "SaisonEte\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "SaisonEte() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonHiver">Trouver l'entité saisonHiver dans Solr</a>
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
	public InscriptionScolaire setSaisonHiver(String o) {
		this.saisonHiver = Boolean.parseBoolean(o);
		this.saisonHiverCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire saisonHiverInit() {
		if(!saisonHiverCouverture.dejaInitialise) {
			_saisonHiver(saisonHiverCouverture);
			if(saisonHiver == null)
				setSaisonHiver(saisonHiverCouverture.o);
		}
		saisonHiverCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrSaisonHiver() {
		return saisonHiver;
	}

	public String strSaisonHiver() {
		return saisonHiver == null ? "" : saisonHiver.toString();
	}

	public String jsonSaisonHiver() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "SaisonHiver\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "SaisonHiver() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonFraisInscription">Trouver l'entité saisonFraisInscription dans Solr</a>
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
	public InscriptionScolaire setSaisonFraisInscription(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.saisonFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.saisonFraisInscriptionCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setSaisonFraisInscription(Double o) {
			this.saisonFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.saisonFraisInscriptionCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setSaisonFraisInscription(Integer o) {
			this.saisonFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.saisonFraisInscriptionCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire saisonFraisInscriptionInit() {
		if(!saisonFraisInscriptionCouverture.dejaInitialise) {
			_saisonFraisInscription(saisonFraisInscriptionCouverture);
			if(saisonFraisInscription == null)
				setSaisonFraisInscription(saisonFraisInscriptionCouverture.o);
		}
		saisonFraisInscriptionCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Double solrSaisonFraisInscription() {
		return saisonFraisInscription == null ? null : saisonFraisInscription.doubleValue();
	}

	public String strSaisonFraisInscription() {
		return saisonFraisInscription == null ? "" : saisonFraisInscription.toString();
	}

	public String jsonSaisonFraisInscription() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "SaisonFraisInscription\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "SaisonFraisInscription() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonNomComplet">Trouver l'entité saisonNomComplet dans Solr</a>
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
	protected InscriptionScolaire saisonNomCompletInit() {
		if(!saisonNomCompletCouverture.dejaInitialise) {
			_saisonNomComplet(saisonNomCompletCouverture);
			if(saisonNomComplet == null)
				setSaisonNomComplet(saisonNomCompletCouverture.o);
		}
		saisonNomCompletCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrSaisonNomComplet() {
		return saisonNomComplet;
	}

	public String strSaisonNomComplet() {
		return saisonNomComplet == null ? "" : saisonNomComplet;
	}

	public String jsonSaisonNomComplet() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "SaisonNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "SaisonNomComplet() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionJourDebut">Trouver l'entité sessionJourDebut dans Solr</a>
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
	public InscriptionScolaire setSessionJourDebut(Instant o) {
		this.sessionJourDebut = LocalDate.from(o);
		this.sessionJourDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setSessionJourDebut(String o) {
		this.sessionJourDebut = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionJourDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setSessionJourDebut(Date o) {
		this.sessionJourDebut = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionJourDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire sessionJourDebutInit() {
		if(!sessionJourDebutCouverture.dejaInitialise) {
			_sessionJourDebut(sessionJourDebutCouverture);
			if(sessionJourDebut == null)
				setSessionJourDebut(sessionJourDebutCouverture.o);
		}
		sessionJourDebutCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrSessionJourDebut() {
		return sessionJourDebut == null ? null : Date.from(sessionJourDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSessionJourDebut() {
		return sessionJourDebut == null ? "" : sessionJourDebut.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonSessionJourDebut() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "SessionJourDebut\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "SessionJourDebut() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionJourFin">Trouver l'entité sessionJourFin dans Solr</a>
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
	public InscriptionScolaire setSessionJourFin(Instant o) {
		this.sessionJourFin = LocalDate.from(o);
		this.sessionJourFinCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setSessionJourFin(String o) {
		this.sessionJourFin = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.sessionJourFinCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setSessionJourFin(Date o) {
		this.sessionJourFin = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionJourFinCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire sessionJourFinInit() {
		if(!sessionJourFinCouverture.dejaInitialise) {
			_sessionJourFin(sessionJourFinCouverture);
			if(sessionJourFin == null)
				setSessionJourFin(sessionJourFinCouverture.o);
		}
		sessionJourFinCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrSessionJourFin() {
		return sessionJourFin == null ? null : Date.from(sessionJourFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSessionJourFin() {
		return sessionJourFin == null ? "" : sessionJourFin.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonSessionJourFin() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "SessionJourFin\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "SessionJourFin() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageNomComplet">Trouver l'entité ageNomComplet dans Solr</a>
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
	protected InscriptionScolaire ageNomCompletInit() {
		if(!ageNomCompletCouverture.dejaInitialise) {
			_ageNomComplet(ageNomCompletCouverture);
			if(ageNomComplet == null)
				setAgeNomComplet(ageNomCompletCouverture.o);
		}
		ageNomCompletCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrAgeNomComplet() {
		return ageNomComplet;
	}

	public String strAgeNomComplet() {
		return ageNomComplet == null ? "" : ageNomComplet;
	}

	public String jsonAgeNomComplet() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "AgeNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "AgeNomComplet() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageDebut">Trouver l'entité ageDebut dans Solr</a>
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
	public InscriptionScolaire setAgeDebut(String o) {
		if(NumberUtils.isParsable(o))
			this.ageDebut = Integer.parseInt(o);
		this.ageDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire ageDebutInit() {
		if(!ageDebutCouverture.dejaInitialise) {
			_ageDebut(ageDebutCouverture);
			if(ageDebut == null)
				setAgeDebut(ageDebutCouverture.o);
		}
		ageDebutCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrAgeDebut() {
		return ageDebut;
	}

	public String strAgeDebut() {
		return ageDebut == null ? "" : ageDebut.toString();
	}

	public String jsonAgeDebut() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "AgeDebut\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "AgeDebut() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageFin">Trouver l'entité ageFin dans Solr</a>
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
	public InscriptionScolaire setAgeFin(String o) {
		if(NumberUtils.isParsable(o))
			this.ageFin = Integer.parseInt(o);
		this.ageFinCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire ageFinInit() {
		if(!ageFinCouverture.dejaInitialise) {
			_ageFin(ageFinCouverture);
			if(ageFin == null)
				setAgeFin(ageFinCouverture.o);
		}
		ageFinCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrAgeFin() {
		return ageFin;
	}

	public String strAgeFin() {
		return ageFin == null ? "" : ageFin.toString();
	}

	public String jsonAgeFin() {
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "AgeFin\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "AgeFin() {");
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

	////////////////////
	// blocHeureDebut //
	////////////////////

	/**	L'entité « blocHeureDebut »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalTime blocHeureDebut;
	@JsonIgnore
	public Couverture<LocalTime> blocHeureDebutCouverture = new Couverture<LocalTime>().p(this).c(LocalTime.class).var("blocHeureDebut").o(blocHeureDebut);

	/**	<br/>L'entité « blocHeureDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocHeureDebut">Trouver l'entité blocHeureDebut dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocHeureDebut(Couverture<LocalTime> c);

	public LocalTime getBlocHeureDebut() {
		return blocHeureDebut;
	}

	public void setBlocHeureDebut(LocalTime blocHeureDebut) {
		this.blocHeureDebut = blocHeureDebut;
		this.blocHeureDebutCouverture.dejaInitialise = true;
	}
	/** Example: 01:00 **/
	public InscriptionScolaire setBlocHeureDebut(String o) {
		try {
			this.blocHeureDebut = LocalTime.parse(o, DateTimeFormatter.ofPattern("HH mm"));
			this.blocHeureDebutCouverture.dejaInitialise = true;
		} catch(Exception e) {
		}
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocHeureDebutInit() {
		if(!blocHeureDebutCouverture.dejaInitialise) {
			_blocHeureDebut(blocHeureDebutCouverture);
			if(blocHeureDebut == null)
				setBlocHeureDebut(blocHeureDebutCouverture.o);
		}
		blocHeureDebutCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrBlocHeureDebut() {
		return blocHeureDebut == null ? null : blocHeureDebut.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public String strBlocHeureDebut() {
		return blocHeureDebut == null ? "" : blocHeureDebut.format(DateTimeFormatter.ofPattern("H'h'mm", Locale.FRANCE));
	}

	public String jsonBlocHeureDebut() {
		return blocHeureDebut == null ? "" : blocHeureDebut.format(DateTimeFormatter.ofPattern("HH mm", Locale.US));
	}

	public String nomAffichageBlocHeureDebut() {
		return "heure début";
	}

	public String htmTooltipBlocHeureDebut() {
		return null;
	}

	public String htmBlocHeureDebut() {
		return blocHeureDebut == null ? "" : StringEscapeUtils.escapeHtml4(strBlocHeureDebut());
	}

	public void htmBlocHeureDebut(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocHeureDebut\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocHeureDebut() {");
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
				r.l("				data: {\"setBlocHeureDebut\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocHeureDebut()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocHeureDebut\"");
							r.s(" value=\"", htmBlocHeureDebut(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocHeureDebut());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// blocHeureFin //
	//////////////////

	/**	L'entité « blocHeureFin »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalTime blocHeureFin;
	@JsonIgnore
	public Couverture<LocalTime> blocHeureFinCouverture = new Couverture<LocalTime>().p(this).c(LocalTime.class).var("blocHeureFin").o(blocHeureFin);

	/**	<br/>L'entité « blocHeureFin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocHeureFin">Trouver l'entité blocHeureFin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocHeureFin(Couverture<LocalTime> c);

	public LocalTime getBlocHeureFin() {
		return blocHeureFin;
	}

	public void setBlocHeureFin(LocalTime blocHeureFin) {
		this.blocHeureFin = blocHeureFin;
		this.blocHeureFinCouverture.dejaInitialise = true;
	}
	/** Example: 01:00 **/
	public InscriptionScolaire setBlocHeureFin(String o) {
		try {
			this.blocHeureFin = LocalTime.parse(o, DateTimeFormatter.ofPattern("HH mm"));
			this.blocHeureFinCouverture.dejaInitialise = true;
		} catch(Exception e) {
		}
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocHeureFinInit() {
		if(!blocHeureFinCouverture.dejaInitialise) {
			_blocHeureFin(blocHeureFinCouverture);
			if(blocHeureFin == null)
				setBlocHeureFin(blocHeureFinCouverture.o);
		}
		blocHeureFinCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrBlocHeureFin() {
		return blocHeureFin == null ? null : blocHeureFin.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public String strBlocHeureFin() {
		return blocHeureFin == null ? "" : blocHeureFin.format(DateTimeFormatter.ofPattern("H'h'mm", Locale.FRANCE));
	}

	public String jsonBlocHeureFin() {
		return blocHeureFin == null ? "" : blocHeureFin.format(DateTimeFormatter.ofPattern("HH mm", Locale.US));
	}

	public String nomAffichageBlocHeureFin() {
		return "heure fin";
	}

	public String htmTooltipBlocHeureFin() {
		return null;
	}

	public String htmBlocHeureFin() {
		return blocHeureFin == null ? "" : StringEscapeUtils.escapeHtml4(strBlocHeureFin());
	}

	public void htmBlocHeureFin(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocHeureFin\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocHeureFin() {");
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
				r.l("				data: {\"setBlocHeureFin\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocHeureFin()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocHeureFin\"");
							r.s(" value=\"", htmBlocHeureFin(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocHeureFin());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// blocPrixParMois //
	/////////////////////

	/**	L'entité « blocPrixParMois »
	 *	 is defined as null before being initialized. 
	 */
	protected BigDecimal blocPrixParMois;
	@JsonIgnore
	public Couverture<BigDecimal> blocPrixParMoisCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("blocPrixParMois").o(blocPrixParMois);

	/**	<br/>L'entité « blocPrixParMois »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocPrixParMois">Trouver l'entité blocPrixParMois dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocPrixParMois(Couverture<BigDecimal> c);

	public BigDecimal getBlocPrixParMois() {
		return blocPrixParMois;
	}

	public void setBlocPrixParMois(BigDecimal blocPrixParMois) {
		this.blocPrixParMois = blocPrixParMois;
		this.blocPrixParMoisCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setBlocPrixParMois(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blocPrixParMois = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixParMoisCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setBlocPrixParMois(Double o) {
			this.blocPrixParMois = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixParMoisCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setBlocPrixParMois(Integer o) {
			this.blocPrixParMois = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixParMoisCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocPrixParMoisInit() {
		if(!blocPrixParMoisCouverture.dejaInitialise) {
			_blocPrixParMois(blocPrixParMoisCouverture);
			if(blocPrixParMois == null)
				setBlocPrixParMois(blocPrixParMoisCouverture.o);
		}
		blocPrixParMoisCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Double solrBlocPrixParMois() {
		return blocPrixParMois == null ? null : blocPrixParMois.doubleValue();
	}

	public String strBlocPrixParMois() {
		return blocPrixParMois == null ? "" : blocPrixParMois.toString();
	}

	public String jsonBlocPrixParMois() {
		return blocPrixParMois == null ? "" : blocPrixParMois.toString();
	}

	public String nomAffichageBlocPrixParMois() {
		return "prix par mois";
	}

	public String htmTooltipBlocPrixParMois() {
		return null;
	}

	public String htmBlocPrixParMois() {
		return blocPrixParMois == null ? "" : StringEscapeUtils.escapeHtml4(strBlocPrixParMois());
	}

	public void htmBlocPrixParMois(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocPrixParMois\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocPrixParMois() {");
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
				r.l("				data: {\"setBlocPrixParMois\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocPrixParMois()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocPrixParMois\"");
							r.s(" value=\"", htmBlocPrixParMois(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocPrixParMois());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// blocDimanche //
	//////////////////

	/**	L'entité « blocDimanche »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blocDimanche;
	@JsonIgnore
	public Couverture<Boolean> blocDimancheCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocDimanche").o(blocDimanche);

	/**	<br/>L'entité « blocDimanche »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocDimanche">Trouver l'entité blocDimanche dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocDimanche(Couverture<Boolean> c);

	public Boolean getBlocDimanche() {
		return blocDimanche;
	}

	public void setBlocDimanche(Boolean blocDimanche) {
		this.blocDimanche = blocDimanche;
		this.blocDimancheCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setBlocDimanche(String o) {
		this.blocDimanche = Boolean.parseBoolean(o);
		this.blocDimancheCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocDimancheInit() {
		if(!blocDimancheCouverture.dejaInitialise) {
			_blocDimanche(blocDimancheCouverture);
			if(blocDimanche == null)
				setBlocDimanche(blocDimancheCouverture.o);
		}
		blocDimancheCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrBlocDimanche() {
		return blocDimanche;
	}

	public String strBlocDimanche() {
		return blocDimanche == null ? "" : blocDimanche.toString();
	}

	public String jsonBlocDimanche() {
		return blocDimanche == null ? "" : blocDimanche.toString();
	}

	public String nomAffichageBlocDimanche() {
		return "dimanche";
	}

	public String htmTooltipBlocDimanche() {
		return null;
	}

	public String htmBlocDimanche() {
		return blocDimanche == null ? "" : StringEscapeUtils.escapeHtml4(strBlocDimanche());
	}

	public void htmBlocDimanche(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocDimanche\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocDimanche() {");
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
				r.l("				data: {\"setBlocDimanche\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocDimanche()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocDimanche\"");
							r.s(" value=\"", htmBlocDimanche(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocDimanche());
			}
			r.l("</div>");
		}
	}

	///////////////
	// blocLundi //
	///////////////

	/**	L'entité « blocLundi »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blocLundi;
	@JsonIgnore
	public Couverture<Boolean> blocLundiCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocLundi").o(blocLundi);

	/**	<br/>L'entité « blocLundi »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocLundi">Trouver l'entité blocLundi dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocLundi(Couverture<Boolean> c);

	public Boolean getBlocLundi() {
		return blocLundi;
	}

	public void setBlocLundi(Boolean blocLundi) {
		this.blocLundi = blocLundi;
		this.blocLundiCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setBlocLundi(String o) {
		this.blocLundi = Boolean.parseBoolean(o);
		this.blocLundiCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocLundiInit() {
		if(!blocLundiCouverture.dejaInitialise) {
			_blocLundi(blocLundiCouverture);
			if(blocLundi == null)
				setBlocLundi(blocLundiCouverture.o);
		}
		blocLundiCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrBlocLundi() {
		return blocLundi;
	}

	public String strBlocLundi() {
		return blocLundi == null ? "" : blocLundi.toString();
	}

	public String jsonBlocLundi() {
		return blocLundi == null ? "" : blocLundi.toString();
	}

	public String nomAffichageBlocLundi() {
		return "lundi";
	}

	public String htmTooltipBlocLundi() {
		return null;
	}

	public String htmBlocLundi() {
		return blocLundi == null ? "" : StringEscapeUtils.escapeHtml4(strBlocLundi());
	}

	public void htmBlocLundi(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocLundi\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocLundi() {");
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
				r.l("				data: {\"setBlocLundi\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocLundi()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocLundi\"");
							r.s(" value=\"", htmBlocLundi(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocLundi());
			}
			r.l("</div>");
		}
	}

	///////////////
	// blocMardi //
	///////////////

	/**	L'entité « blocMardi »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blocMardi;
	@JsonIgnore
	public Couverture<Boolean> blocMardiCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocMardi").o(blocMardi);

	/**	<br/>L'entité « blocMardi »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocMardi">Trouver l'entité blocMardi dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocMardi(Couverture<Boolean> c);

	public Boolean getBlocMardi() {
		return blocMardi;
	}

	public void setBlocMardi(Boolean blocMardi) {
		this.blocMardi = blocMardi;
		this.blocMardiCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setBlocMardi(String o) {
		this.blocMardi = Boolean.parseBoolean(o);
		this.blocMardiCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocMardiInit() {
		if(!blocMardiCouverture.dejaInitialise) {
			_blocMardi(blocMardiCouverture);
			if(blocMardi == null)
				setBlocMardi(blocMardiCouverture.o);
		}
		blocMardiCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrBlocMardi() {
		return blocMardi;
	}

	public String strBlocMardi() {
		return blocMardi == null ? "" : blocMardi.toString();
	}

	public String jsonBlocMardi() {
		return blocMardi == null ? "" : blocMardi.toString();
	}

	public String nomAffichageBlocMardi() {
		return "mardi";
	}

	public String htmTooltipBlocMardi() {
		return null;
	}

	public String htmBlocMardi() {
		return blocMardi == null ? "" : StringEscapeUtils.escapeHtml4(strBlocMardi());
	}

	public void htmBlocMardi(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocMardi\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocMardi() {");
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
				r.l("				data: {\"setBlocMardi\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocMardi()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocMardi\"");
							r.s(" value=\"", htmBlocMardi(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocMardi());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// blocMercredi //
	//////////////////

	/**	L'entité « blocMercredi »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blocMercredi;
	@JsonIgnore
	public Couverture<Boolean> blocMercrediCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocMercredi").o(blocMercredi);

	/**	<br/>L'entité « blocMercredi »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocMercredi">Trouver l'entité blocMercredi dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocMercredi(Couverture<Boolean> c);

	public Boolean getBlocMercredi() {
		return blocMercredi;
	}

	public void setBlocMercredi(Boolean blocMercredi) {
		this.blocMercredi = blocMercredi;
		this.blocMercrediCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setBlocMercredi(String o) {
		this.blocMercredi = Boolean.parseBoolean(o);
		this.blocMercrediCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocMercrediInit() {
		if(!blocMercrediCouverture.dejaInitialise) {
			_blocMercredi(blocMercrediCouverture);
			if(blocMercredi == null)
				setBlocMercredi(blocMercrediCouverture.o);
		}
		blocMercrediCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrBlocMercredi() {
		return blocMercredi;
	}

	public String strBlocMercredi() {
		return blocMercredi == null ? "" : blocMercredi.toString();
	}

	public String jsonBlocMercredi() {
		return blocMercredi == null ? "" : blocMercredi.toString();
	}

	public String nomAffichageBlocMercredi() {
		return "mercredi";
	}

	public String htmTooltipBlocMercredi() {
		return null;
	}

	public String htmBlocMercredi() {
		return blocMercredi == null ? "" : StringEscapeUtils.escapeHtml4(strBlocMercredi());
	}

	public void htmBlocMercredi(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocMercredi\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocMercredi() {");
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
				r.l("				data: {\"setBlocMercredi\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocMercredi()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocMercredi\"");
							r.s(" value=\"", htmBlocMercredi(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocMercredi());
			}
			r.l("</div>");
		}
	}

	///////////////
	// blocJeudi //
	///////////////

	/**	L'entité « blocJeudi »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blocJeudi;
	@JsonIgnore
	public Couverture<Boolean> blocJeudiCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocJeudi").o(blocJeudi);

	/**	<br/>L'entité « blocJeudi »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocJeudi">Trouver l'entité blocJeudi dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocJeudi(Couverture<Boolean> c);

	public Boolean getBlocJeudi() {
		return blocJeudi;
	}

	public void setBlocJeudi(Boolean blocJeudi) {
		this.blocJeudi = blocJeudi;
		this.blocJeudiCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setBlocJeudi(String o) {
		this.blocJeudi = Boolean.parseBoolean(o);
		this.blocJeudiCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocJeudiInit() {
		if(!blocJeudiCouverture.dejaInitialise) {
			_blocJeudi(blocJeudiCouverture);
			if(blocJeudi == null)
				setBlocJeudi(blocJeudiCouverture.o);
		}
		blocJeudiCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrBlocJeudi() {
		return blocJeudi;
	}

	public String strBlocJeudi() {
		return blocJeudi == null ? "" : blocJeudi.toString();
	}

	public String jsonBlocJeudi() {
		return blocJeudi == null ? "" : blocJeudi.toString();
	}

	public String nomAffichageBlocJeudi() {
		return "jeudi";
	}

	public String htmTooltipBlocJeudi() {
		return null;
	}

	public String htmBlocJeudi() {
		return blocJeudi == null ? "" : StringEscapeUtils.escapeHtml4(strBlocJeudi());
	}

	public void htmBlocJeudi(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocJeudi\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocJeudi() {");
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
				r.l("				data: {\"setBlocJeudi\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocJeudi()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocJeudi\"");
							r.s(" value=\"", htmBlocJeudi(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocJeudi());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// blocVendredi //
	//////////////////

	/**	L'entité « blocVendredi »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blocVendredi;
	@JsonIgnore
	public Couverture<Boolean> blocVendrediCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocVendredi").o(blocVendredi);

	/**	<br/>L'entité « blocVendredi »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocVendredi">Trouver l'entité blocVendredi dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocVendredi(Couverture<Boolean> c);

	public Boolean getBlocVendredi() {
		return blocVendredi;
	}

	public void setBlocVendredi(Boolean blocVendredi) {
		this.blocVendredi = blocVendredi;
		this.blocVendrediCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setBlocVendredi(String o) {
		this.blocVendredi = Boolean.parseBoolean(o);
		this.blocVendrediCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocVendrediInit() {
		if(!blocVendrediCouverture.dejaInitialise) {
			_blocVendredi(blocVendrediCouverture);
			if(blocVendredi == null)
				setBlocVendredi(blocVendrediCouverture.o);
		}
		blocVendrediCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrBlocVendredi() {
		return blocVendredi;
	}

	public String strBlocVendredi() {
		return blocVendredi == null ? "" : blocVendredi.toString();
	}

	public String jsonBlocVendredi() {
		return blocVendredi == null ? "" : blocVendredi.toString();
	}

	public String nomAffichageBlocVendredi() {
		return "vendredi";
	}

	public String htmTooltipBlocVendredi() {
		return null;
	}

	public String htmBlocVendredi() {
		return blocVendredi == null ? "" : StringEscapeUtils.escapeHtml4(strBlocVendredi());
	}

	public void htmBlocVendredi(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocVendredi\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocVendredi() {");
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
				r.l("				data: {\"setBlocVendredi\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocVendredi()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocVendredi\"");
							r.s(" value=\"", htmBlocVendredi(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocVendredi());
			}
			r.l("</div>");
		}
	}

	////////////////
	// blocSamedi //
	////////////////

	/**	L'entité « blocSamedi »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean blocSamedi;
	@JsonIgnore
	public Couverture<Boolean> blocSamediCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocSamedi").o(blocSamedi);

	/**	<br/>L'entité « blocSamedi »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocSamedi">Trouver l'entité blocSamedi dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocSamedi(Couverture<Boolean> c);

	public Boolean getBlocSamedi() {
		return blocSamedi;
	}

	public void setBlocSamedi(Boolean blocSamedi) {
		this.blocSamedi = blocSamedi;
		this.blocSamediCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setBlocSamedi(String o) {
		this.blocSamedi = Boolean.parseBoolean(o);
		this.blocSamediCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocSamediInit() {
		if(!blocSamediCouverture.dejaInitialise) {
			_blocSamedi(blocSamediCouverture);
			if(blocSamedi == null)
				setBlocSamedi(blocSamediCouverture.o);
		}
		blocSamediCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrBlocSamedi() {
		return blocSamedi;
	}

	public String strBlocSamedi() {
		return blocSamedi == null ? "" : blocSamedi.toString();
	}

	public String jsonBlocSamedi() {
		return blocSamedi == null ? "" : blocSamedi.toString();
	}

	public String nomAffichageBlocSamedi() {
		return "samedi";
	}

	public String htmTooltipBlocSamedi() {
		return null;
	}

	public String htmBlocSamedi() {
		return blocSamedi == null ? "" : StringEscapeUtils.escapeHtml4(strBlocSamedi());
	}

	public void htmBlocSamedi(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "BlocSamedi\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "BlocSamedi() {");
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
				r.l("				data: {\"setBlocSamedi\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageBlocSamedi()), "</span>");
				r.s("			<input");
							r.s(" name=\"blocSamedi\"");
							r.s(" value=\"", htmBlocSamedi(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmBlocSamedi());
			}
			r.l("</div>");
		}
	}

	/////////////////////////
	// inscriptionApprouve //
	/////////////////////////

	/**	L'entité « inscriptionApprouve »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean inscriptionApprouve;
	@JsonIgnore
	public Couverture<Boolean> inscriptionApprouveCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("inscriptionApprouve").o(inscriptionApprouve);

	/**	<br/>L'entité « inscriptionApprouve »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionApprouve">Trouver l'entité inscriptionApprouve dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionApprouve(Couverture<Boolean> c);

	public Boolean getInscriptionApprouve() {
		return inscriptionApprouve;
	}

	public void setInscriptionApprouve(Boolean inscriptionApprouve) {
		this.inscriptionApprouve = inscriptionApprouve;
		this.inscriptionApprouveCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionApprouve(String o) {
		this.inscriptionApprouve = Boolean.parseBoolean(o);
		this.inscriptionApprouveCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionApprouveInit() {
		if(!inscriptionApprouveCouverture.dejaInitialise) {
			_inscriptionApprouve(inscriptionApprouveCouverture);
			if(inscriptionApprouve == null)
				setInscriptionApprouve(inscriptionApprouveCouverture.o);
		}
		inscriptionApprouveCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrInscriptionApprouve() {
		return inscriptionApprouve;
	}

	public String strInscriptionApprouve() {
		return inscriptionApprouve == null ? "" : inscriptionApprouve.toString();
	}

	public String jsonInscriptionApprouve() {
		return inscriptionApprouve == null ? "" : inscriptionApprouve.toString();
	}

	public String nomAffichageInscriptionApprouve() {
		return "approuvé";
	}

	public String htmTooltipInscriptionApprouve() {
		return null;
	}

	public String htmInscriptionApprouve() {
		return inscriptionApprouve == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionApprouve());
	}

	public void htmInscriptionApprouve(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "InscriptionApprouve\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "InscriptionApprouve() {");
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
				r.l("				data: {\"setInscriptionApprouve\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageInscriptionApprouve()), "</span>");
				r.s("			<input");
							r.s(" name=\"inscriptionApprouve\"");
							r.s(" value=\"", htmInscriptionApprouve(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmInscriptionApprouve());
			}
			r.l("</div>");
		}
	}

	//////////////////////////////
	// inscriptionImmunisations //
	//////////////////////////////

	/**	L'entité « inscriptionImmunisations »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean inscriptionImmunisations;
	@JsonIgnore
	public Couverture<Boolean> inscriptionImmunisationsCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("inscriptionImmunisations").o(inscriptionImmunisations);

	/**	<br/>L'entité « inscriptionImmunisations »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionImmunisations">Trouver l'entité inscriptionImmunisations dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionImmunisations(Couverture<Boolean> c);

	public Boolean getInscriptionImmunisations() {
		return inscriptionImmunisations;
	}

	public void setInscriptionImmunisations(Boolean inscriptionImmunisations) {
		this.inscriptionImmunisations = inscriptionImmunisations;
		this.inscriptionImmunisationsCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionImmunisations(String o) {
		this.inscriptionImmunisations = Boolean.parseBoolean(o);
		this.inscriptionImmunisationsCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionImmunisationsInit() {
		if(!inscriptionImmunisationsCouverture.dejaInitialise) {
			_inscriptionImmunisations(inscriptionImmunisationsCouverture);
			if(inscriptionImmunisations == null)
				setInscriptionImmunisations(inscriptionImmunisationsCouverture.o);
		}
		inscriptionImmunisationsCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrInscriptionImmunisations() {
		return inscriptionImmunisations;
	}

	public String strInscriptionImmunisations() {
		return inscriptionImmunisations == null ? "" : inscriptionImmunisations.toString();
	}

	public String jsonInscriptionImmunisations() {
		return inscriptionImmunisations == null ? "" : inscriptionImmunisations.toString();
	}

	public String nomAffichageInscriptionImmunisations() {
		return "vacciné";
	}

	public String htmTooltipInscriptionImmunisations() {
		return null;
	}

	public String htmInscriptionImmunisations() {
		return inscriptionImmunisations == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionImmunisations());
	}

	public void htmInscriptionImmunisations(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "InscriptionImmunisations\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "InscriptionImmunisations() {");
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
				r.l("				data: {\"setInscriptionImmunisations\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageInscriptionImmunisations()), "</span>");
				r.s("			<input");
							r.s(" name=\"inscriptionImmunisations\"");
							r.s(" value=\"", htmInscriptionImmunisations(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmInscriptionImmunisations());
			}
			r.l("</div>");
		}
	}

	//////////////////
	// familleMarie //
	//////////////////

	/**	L'entité « familleMarie »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean familleMarie;
	@JsonIgnore
	public Couverture<Boolean> familleMarieCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("familleMarie").o(familleMarie);

	/**	<br/>L'entité « familleMarie »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleMarie">Trouver l'entité familleMarie dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familleMarie(Couverture<Boolean> c);

	public Boolean getFamilleMarie() {
		return familleMarie;
	}

	public void setFamilleMarie(Boolean familleMarie) {
		this.familleMarie = familleMarie;
		this.familleMarieCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setFamilleMarie(String o) {
		this.familleMarie = Boolean.parseBoolean(o);
		this.familleMarieCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire familleMarieInit() {
		if(!familleMarieCouverture.dejaInitialise) {
			_familleMarie(familleMarieCouverture);
			if(familleMarie == null)
				setFamilleMarie(familleMarieCouverture.o);
		}
		familleMarieCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrFamilleMarie() {
		return familleMarie;
	}

	public String strFamilleMarie() {
		return familleMarie == null ? "" : familleMarie.toString();
	}

	public String jsonFamilleMarie() {
		return familleMarie == null ? "" : familleMarie.toString();
	}

	public String nomAffichageFamilleMarie() {
		return "parents marié";
	}

	public String htmTooltipFamilleMarie() {
		return null;
	}

	public String htmFamilleMarie() {
		return familleMarie == null ? "" : StringEscapeUtils.escapeHtml4(strFamilleMarie());
	}

	public void htmFamilleMarie(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "FamilleMarie\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "FamilleMarie() {");
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
				r.l("				data: {\"setFamilleMarie\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilleMarie()), "</span>");
				r.s("			<input");
							r.s(" name=\"familleMarie\"");
							r.s(" value=\"", htmFamilleMarie(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilleMarie());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// familleSepare //
	///////////////////

	/**	L'entité « familleSepare »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean familleSepare;
	@JsonIgnore
	public Couverture<Boolean> familleSepareCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("familleSepare").o(familleSepare);

	/**	<br/>L'entité « familleSepare »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleSepare">Trouver l'entité familleSepare dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familleSepare(Couverture<Boolean> c);

	public Boolean getFamilleSepare() {
		return familleSepare;
	}

	public void setFamilleSepare(Boolean familleSepare) {
		this.familleSepare = familleSepare;
		this.familleSepareCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setFamilleSepare(String o) {
		this.familleSepare = Boolean.parseBoolean(o);
		this.familleSepareCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire familleSepareInit() {
		if(!familleSepareCouverture.dejaInitialise) {
			_familleSepare(familleSepareCouverture);
			if(familleSepare == null)
				setFamilleSepare(familleSepareCouverture.o);
		}
		familleSepareCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrFamilleSepare() {
		return familleSepare;
	}

	public String strFamilleSepare() {
		return familleSepare == null ? "" : familleSepare.toString();
	}

	public String jsonFamilleSepare() {
		return familleSepare == null ? "" : familleSepare.toString();
	}

	public String nomAffichageFamilleSepare() {
		return "parents séparé";
	}

	public String htmTooltipFamilleSepare() {
		return null;
	}

	public String htmFamilleSepare() {
		return familleSepare == null ? "" : StringEscapeUtils.escapeHtml4(strFamilleSepare());
	}

	public void htmFamilleSepare(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "FamilleSepare\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "FamilleSepare() {");
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
				r.l("				data: {\"setFamilleSepare\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilleSepare()), "</span>");
				r.s("			<input");
							r.s(" name=\"familleSepare\"");
							r.s(" value=\"", htmFamilleSepare(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilleSepare());
			}
			r.l("</div>");
		}
	}

	////////////////////
	// familleDivorce //
	////////////////////

	/**	L'entité « familleDivorce »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean familleDivorce;
	@JsonIgnore
	public Couverture<Boolean> familleDivorceCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("familleDivorce").o(familleDivorce);

	/**	<br/>L'entité « familleDivorce »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleDivorce">Trouver l'entité familleDivorce dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familleDivorce(Couverture<Boolean> c);

	public Boolean getFamilleDivorce() {
		return familleDivorce;
	}

	public void setFamilleDivorce(Boolean familleDivorce) {
		this.familleDivorce = familleDivorce;
		this.familleDivorceCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setFamilleDivorce(String o) {
		this.familleDivorce = Boolean.parseBoolean(o);
		this.familleDivorceCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire familleDivorceInit() {
		if(!familleDivorceCouverture.dejaInitialise) {
			_familleDivorce(familleDivorceCouverture);
			if(familleDivorce == null)
				setFamilleDivorce(familleDivorceCouverture.o);
		}
		familleDivorceCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrFamilleDivorce() {
		return familleDivorce;
	}

	public String strFamilleDivorce() {
		return familleDivorce == null ? "" : familleDivorce.toString();
	}

	public String jsonFamilleDivorce() {
		return familleDivorce == null ? "" : familleDivorce.toString();
	}

	public String nomAffichageFamilleDivorce() {
		return "parents divorcé";
	}

	public String htmTooltipFamilleDivorce() {
		return null;
	}

	public String htmFamilleDivorce() {
		return familleDivorce == null ? "" : StringEscapeUtils.escapeHtml4(strFamilleDivorce());
	}

	public void htmFamilleDivorce(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "FamilleDivorce\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "FamilleDivorce() {");
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
				r.l("				data: {\"setFamilleDivorce\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilleDivorce()), "</span>");
				r.s("			<input");
							r.s(" name=\"familleDivorce\"");
							r.s(" value=\"", htmFamilleDivorce(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilleDivorce());
			}
			r.l("</div>");
		}
	}

	/////////////////////
	// familleAddresse //
	/////////////////////

	/**	L'entité « familleAddresse »
	 *	 is defined as null before being initialized. 
	 */
	protected String familleAddresse;
	@JsonIgnore
	public Couverture<String> familleAddresseCouverture = new Couverture<String>().p(this).c(String.class).var("familleAddresse").o(familleAddresse);

	/**	<br/>L'entité « familleAddresse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleAddresse">Trouver l'entité familleAddresse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familleAddresse(Couverture<String> c);

	public String getFamilleAddresse() {
		return familleAddresse;
	}

	public void setFamilleAddresse(String familleAddresse) {
		this.familleAddresse = familleAddresse;
		this.familleAddresseCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire familleAddresseInit() {
		if(!familleAddresseCouverture.dejaInitialise) {
			_familleAddresse(familleAddresseCouverture);
			if(familleAddresse == null)
				setFamilleAddresse(familleAddresseCouverture.o);
		}
		familleAddresseCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrFamilleAddresse() {
		return familleAddresse;
	}

	public String strFamilleAddresse() {
		return familleAddresse == null ? "" : familleAddresse;
	}

	public String jsonFamilleAddresse() {
		return familleAddresse == null ? "" : familleAddresse;
	}

	public String nomAffichageFamilleAddresse() {
		return "addresse de la famille";
	}

	public String htmTooltipFamilleAddresse() {
		return null;
	}

	public String htmFamilleAddresse() {
		return familleAddresse == null ? "" : StringEscapeUtils.escapeHtml4(strFamilleAddresse());
	}

	public void htmFamilleAddresse(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "FamilleAddresse\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "FamilleAddresse() {");
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
				r.l("				data: {\"setFamilleAddresse\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilleAddresse()), "</span>");
				r.s("			<input");
							r.s(" name=\"familleAddresse\"");
							r.s(" value=\"", htmFamilleAddresse(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilleAddresse());
			}
			r.l("</div>");
		}
	}

	///////////////////////////////////////
	// familleCommentVousConnaissezEcole //
	///////////////////////////////////////

	/**	L'entité « familleCommentVousConnaissezEcole »
	 *	 is defined as null before being initialized. 
	 */
	protected String familleCommentVousConnaissezEcole;
	@JsonIgnore
	public Couverture<String> familleCommentVousConnaissezEcoleCouverture = new Couverture<String>().p(this).c(String.class).var("familleCommentVousConnaissezEcole").o(familleCommentVousConnaissezEcole);

	/**	<br/>L'entité « familleCommentVousConnaissezEcole »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleCommentVousConnaissezEcole">Trouver l'entité familleCommentVousConnaissezEcole dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familleCommentVousConnaissezEcole(Couverture<String> c);

	public String getFamilleCommentVousConnaissezEcole() {
		return familleCommentVousConnaissezEcole;
	}

	public void setFamilleCommentVousConnaissezEcole(String familleCommentVousConnaissezEcole) {
		this.familleCommentVousConnaissezEcole = familleCommentVousConnaissezEcole;
		this.familleCommentVousConnaissezEcoleCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire familleCommentVousConnaissezEcoleInit() {
		if(!familleCommentVousConnaissezEcoleCouverture.dejaInitialise) {
			_familleCommentVousConnaissezEcole(familleCommentVousConnaissezEcoleCouverture);
			if(familleCommentVousConnaissezEcole == null)
				setFamilleCommentVousConnaissezEcole(familleCommentVousConnaissezEcoleCouverture.o);
		}
		familleCommentVousConnaissezEcoleCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrFamilleCommentVousConnaissezEcole() {
		return familleCommentVousConnaissezEcole;
	}

	public String strFamilleCommentVousConnaissezEcole() {
		return familleCommentVousConnaissezEcole == null ? "" : familleCommentVousConnaissezEcole;
	}

	public String jsonFamilleCommentVousConnaissezEcole() {
		return familleCommentVousConnaissezEcole == null ? "" : familleCommentVousConnaissezEcole;
	}

	public String nomAffichageFamilleCommentVousConnaissezEcole() {
		return "comment vous connaissez l'école ? ";
	}

	public String htmTooltipFamilleCommentVousConnaissezEcole() {
		return null;
	}

	public String htmFamilleCommentVousConnaissezEcole() {
		return familleCommentVousConnaissezEcole == null ? "" : StringEscapeUtils.escapeHtml4(strFamilleCommentVousConnaissezEcole());
	}

	public void htmFamilleCommentVousConnaissezEcole(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "FamilleCommentVousConnaissezEcole\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "FamilleCommentVousConnaissezEcole() {");
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
				r.l("				data: {\"setFamilleCommentVousConnaissezEcole\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFamilleCommentVousConnaissezEcole()), "</span>");
				r.s("			<input");
							r.s(" name=\"familleCommentVousConnaissezEcole\"");
							r.s(" value=\"", htmFamilleCommentVousConnaissezEcole(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFamilleCommentVousConnaissezEcole());
			}
			r.l("</div>");
		}
	}

	////////////////////////////////////////
	// inscriptionConsiderationsSpeciales //
	////////////////////////////////////////

	/**	L'entité « inscriptionConsiderationsSpeciales »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionConsiderationsSpeciales;
	@JsonIgnore
	public Couverture<String> inscriptionConsiderationsSpecialesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionConsiderationsSpeciales").o(inscriptionConsiderationsSpeciales);

	/**	<br/>L'entité « inscriptionConsiderationsSpeciales »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionConsiderationsSpeciales">Trouver l'entité inscriptionConsiderationsSpeciales dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionConsiderationsSpeciales(Couverture<String> c);

	public String getInscriptionConsiderationsSpeciales() {
		return inscriptionConsiderationsSpeciales;
	}

	public void setInscriptionConsiderationsSpeciales(String inscriptionConsiderationsSpeciales) {
		this.inscriptionConsiderationsSpeciales = inscriptionConsiderationsSpeciales;
		this.inscriptionConsiderationsSpecialesCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionConsiderationsSpecialesInit() {
		if(!inscriptionConsiderationsSpecialesCouverture.dejaInitialise) {
			_inscriptionConsiderationsSpeciales(inscriptionConsiderationsSpecialesCouverture);
			if(inscriptionConsiderationsSpeciales == null)
				setInscriptionConsiderationsSpeciales(inscriptionConsiderationsSpecialesCouverture.o);
		}
		inscriptionConsiderationsSpecialesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionConsiderationsSpeciales() {
		return inscriptionConsiderationsSpeciales;
	}

	public String strInscriptionConsiderationsSpeciales() {
		return inscriptionConsiderationsSpeciales == null ? "" : inscriptionConsiderationsSpeciales;
	}

	public String jsonInscriptionConsiderationsSpeciales() {
		return inscriptionConsiderationsSpeciales == null ? "" : inscriptionConsiderationsSpeciales;
	}

	public String nomAffichageInscriptionConsiderationsSpeciales() {
		return "considérations spéciale";
	}

	public String htmTooltipInscriptionConsiderationsSpeciales() {
		return null;
	}

	public String htmInscriptionConsiderationsSpeciales() {
		return inscriptionConsiderationsSpeciales == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionConsiderationsSpeciales());
	}

	public void htmInscriptionConsiderationsSpeciales(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "InscriptionConsiderationsSpeciales\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "InscriptionConsiderationsSpeciales() {");
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
				r.l("				data: {\"setInscriptionConsiderationsSpeciales\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageInscriptionConsiderationsSpeciales()), "</span>");
				r.s("			<input");
							r.s(" name=\"inscriptionConsiderationsSpeciales\"");
							r.s(" value=\"", htmInscriptionConsiderationsSpeciales(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmInscriptionConsiderationsSpeciales());
			}
			r.l("</div>");
		}
	}

	//////////////////////////
	// inscriptionNomGroupe //
	//////////////////////////

	/**	L'entité « inscriptionNomGroupe »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionNomGroupe;
	@JsonIgnore
	public Couverture<String> inscriptionNomGroupeCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionNomGroupe").o(inscriptionNomGroupe);

	/**	<br/>L'entité « inscriptionNomGroupe »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNomGroupe">Trouver l'entité inscriptionNomGroupe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionNomGroupe(Couverture<String> c);

	public String getInscriptionNomGroupe() {
		return inscriptionNomGroupe;
	}

	public void setInscriptionNomGroupe(String inscriptionNomGroupe) {
		this.inscriptionNomGroupe = inscriptionNomGroupe;
		this.inscriptionNomGroupeCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionNomGroupeInit() {
		if(!inscriptionNomGroupeCouverture.dejaInitialise) {
			_inscriptionNomGroupe(inscriptionNomGroupeCouverture);
			if(inscriptionNomGroupe == null)
				setInscriptionNomGroupe(inscriptionNomGroupeCouverture.o);
		}
		inscriptionNomGroupeCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionNomGroupe() {
		return inscriptionNomGroupe;
	}

	public String strInscriptionNomGroupe() {
		return inscriptionNomGroupe == null ? "" : inscriptionNomGroupe;
	}

	public String jsonInscriptionNomGroupe() {
		return inscriptionNomGroupe == null ? "" : inscriptionNomGroupe;
	}

	public String nomAffichageInscriptionNomGroupe() {
		return "nom du groupe";
	}

	public String htmTooltipInscriptionNomGroupe() {
		return null;
	}

	public String htmInscriptionNomGroupe() {
		return inscriptionNomGroupe == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionNomGroupe());
	}

	public void htmInscriptionNomGroupe(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "InscriptionNomGroupe\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "InscriptionNomGroupe() {");
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
				r.l("				data: {\"setInscriptionNomGroupe\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageInscriptionNomGroupe()), "</span>");
				r.s("			<input");
							r.s(" name=\"inscriptionNomGroupe\"");
							r.s(" value=\"", htmInscriptionNomGroupe(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmInscriptionNomGroupe());
			}
			r.l("</div>");
		}
	}

	//////////////////////////////////
	// inscriptionPaimentChaqueMois //
	//////////////////////////////////

	/**	L'entité « inscriptionPaimentChaqueMois »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean inscriptionPaimentChaqueMois;
	@JsonIgnore
	public Couverture<Boolean> inscriptionPaimentChaqueMoisCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("inscriptionPaimentChaqueMois").o(inscriptionPaimentChaqueMois);

	/**	<br/>L'entité « inscriptionPaimentChaqueMois »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionPaimentChaqueMois">Trouver l'entité inscriptionPaimentChaqueMois dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionPaimentChaqueMois(Couverture<Boolean> c);

	public Boolean getInscriptionPaimentChaqueMois() {
		return inscriptionPaimentChaqueMois;
	}

	public void setInscriptionPaimentChaqueMois(Boolean inscriptionPaimentChaqueMois) {
		this.inscriptionPaimentChaqueMois = inscriptionPaimentChaqueMois;
		this.inscriptionPaimentChaqueMoisCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionPaimentChaqueMois(String o) {
		this.inscriptionPaimentChaqueMois = Boolean.parseBoolean(o);
		this.inscriptionPaimentChaqueMoisCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionPaimentChaqueMoisInit() {
		if(!inscriptionPaimentChaqueMoisCouverture.dejaInitialise) {
			_inscriptionPaimentChaqueMois(inscriptionPaimentChaqueMoisCouverture);
			if(inscriptionPaimentChaqueMois == null)
				setInscriptionPaimentChaqueMois(inscriptionPaimentChaqueMoisCouverture.o);
		}
		inscriptionPaimentChaqueMoisCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrInscriptionPaimentChaqueMois() {
		return inscriptionPaimentChaqueMois;
	}

	public String strInscriptionPaimentChaqueMois() {
		return inscriptionPaimentChaqueMois == null ? "" : inscriptionPaimentChaqueMois.toString();
	}

	public String jsonInscriptionPaimentChaqueMois() {
		return inscriptionPaimentChaqueMois == null ? "" : inscriptionPaimentChaqueMois.toString();
	}

	public String nomAffichageInscriptionPaimentChaqueMois() {
		return "paiement chaque mois";
	}

	public String htmTooltipInscriptionPaimentChaqueMois() {
		return null;
	}

	public String htmInscriptionPaimentChaqueMois() {
		return inscriptionPaimentChaqueMois == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionPaimentChaqueMois());
	}

	public void htmInscriptionPaimentChaqueMois(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "InscriptionPaimentChaqueMois\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "InscriptionPaimentChaqueMois() {");
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
				r.l("				data: {\"setInscriptionPaimentChaqueMois\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageInscriptionPaimentChaqueMois()), "</span>");
				r.s("			<input");
							r.s(" name=\"inscriptionPaimentChaqueMois\"");
							r.s(" value=\"", htmInscriptionPaimentChaqueMois(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmInscriptionPaimentChaqueMois());
			}
			r.l("</div>");
		}
	}

	///////////////////////////////
	// inscriptionPaimentComplet //
	///////////////////////////////

	/**	L'entité « inscriptionPaimentComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean inscriptionPaimentComplet;
	@JsonIgnore
	public Couverture<Boolean> inscriptionPaimentCompletCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("inscriptionPaimentComplet").o(inscriptionPaimentComplet);

	/**	<br/>L'entité « inscriptionPaimentComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionPaimentComplet">Trouver l'entité inscriptionPaimentComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionPaimentComplet(Couverture<Boolean> c);

	public Boolean getInscriptionPaimentComplet() {
		return inscriptionPaimentComplet;
	}

	public void setInscriptionPaimentComplet(Boolean inscriptionPaimentComplet) {
		this.inscriptionPaimentComplet = inscriptionPaimentComplet;
		this.inscriptionPaimentCompletCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionPaimentComplet(String o) {
		this.inscriptionPaimentComplet = Boolean.parseBoolean(o);
		this.inscriptionPaimentCompletCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionPaimentCompletInit() {
		if(!inscriptionPaimentCompletCouverture.dejaInitialise) {
			_inscriptionPaimentComplet(inscriptionPaimentCompletCouverture);
			if(inscriptionPaimentComplet == null)
				setInscriptionPaimentComplet(inscriptionPaimentCompletCouverture.o);
		}
		inscriptionPaimentCompletCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrInscriptionPaimentComplet() {
		return inscriptionPaimentComplet;
	}

	public String strInscriptionPaimentComplet() {
		return inscriptionPaimentComplet == null ? "" : inscriptionPaimentComplet.toString();
	}

	public String jsonInscriptionPaimentComplet() {
		return inscriptionPaimentComplet == null ? "" : inscriptionPaimentComplet.toString();
	}

	public String nomAffichageInscriptionPaimentComplet() {
		return "paiement complet";
	}

	public String htmTooltipInscriptionPaimentComplet() {
		return null;
	}

	public String htmInscriptionPaimentComplet() {
		return inscriptionPaimentComplet == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionPaimentComplet());
	}

	public void htmInscriptionPaimentComplet(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "InscriptionPaimentComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "InscriptionPaimentComplet() {");
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
				r.l("				data: {\"setInscriptionPaimentComplet\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageInscriptionPaimentComplet()), "</span>");
				r.s("			<input");
							r.s(" name=\"inscriptionPaimentComplet\"");
							r.s(" value=\"", htmInscriptionPaimentComplet(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmInscriptionPaimentComplet());
			}
			r.l("</div>");
		}
	}

	///////////////////////////
	// inscriptionNomComplet //
	///////////////////////////

	/**	L'entité « inscriptionNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionNomComplet;
	@JsonIgnore
	public Couverture<String> inscriptionNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionNomComplet").o(inscriptionNomComplet);

	/**	<br/>L'entité « inscriptionNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNomComplet">Trouver l'entité inscriptionNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionNomComplet(Couverture<String> c);

	public String getInscriptionNomComplet() {
		return inscriptionNomComplet;
	}

	public void setInscriptionNomComplet(String inscriptionNomComplet) {
		this.inscriptionNomComplet = inscriptionNomComplet;
		this.inscriptionNomCompletCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionNomCompletInit() {
		if(!inscriptionNomCompletCouverture.dejaInitialise) {
			_inscriptionNomComplet(inscriptionNomCompletCouverture);
			if(inscriptionNomComplet == null)
				setInscriptionNomComplet(inscriptionNomCompletCouverture.o);
		}
		inscriptionNomCompletCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionNomComplet() {
		return inscriptionNomComplet;
	}

	public String strInscriptionNomComplet() {
		return inscriptionNomComplet == null ? "" : inscriptionNomComplet;
	}

	public String jsonInscriptionNomComplet() {
		return inscriptionNomComplet == null ? "" : inscriptionNomComplet;
	}

	public String nomAffichageInscriptionNomComplet() {
		return "nom";
	}

	public String htmTooltipInscriptionNomComplet() {
		return null;
	}

	public String htmInscriptionNomComplet() {
		return inscriptionNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionNomComplet());
	}

	public void htmInscriptionNomComplet(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "InscriptionNomComplet\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "InscriptionNomComplet() {");
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
				r.l("				data: {\"setInscriptionNomComplet\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageInscriptionNomComplet()), "</span>");
				r.s("			<input");
							r.s(" name=\"inscriptionNomComplet\"");
							r.s(" value=\"", htmInscriptionNomComplet(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmInscriptionNomComplet());
			}
			r.l("</div>");
		}
	}

	///////////////////
	// inscriptionId //
	///////////////////

	/**	L'entité « inscriptionId »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionId;
	@JsonIgnore
	public Couverture<String> inscriptionIdCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionId").o(inscriptionId);

	/**	<br/>L'entité « inscriptionId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionId">Trouver l'entité inscriptionId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionId(Couverture<String> c);

	public String getInscriptionId() {
		return inscriptionId;
	}

	public void setInscriptionId(String inscriptionId) {
		this.inscriptionId = inscriptionId;
		this.inscriptionIdCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionIdInit() {
		if(!inscriptionIdCouverture.dejaInitialise) {
			_inscriptionId(inscriptionIdCouverture);
			if(inscriptionId == null)
				setInscriptionId(inscriptionIdCouverture.o);
		}
		inscriptionIdCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionId() {
		return inscriptionId;
	}

	public String strInscriptionId() {
		return inscriptionId == null ? "" : inscriptionId;
	}

	public String jsonInscriptionId() {
		return inscriptionId == null ? "" : inscriptionId;
	}

	public String nomAffichageInscriptionId() {
		return "ID";
	}

	public String htmTooltipInscriptionId() {
		return null;
	}

	public String htmInscriptionId() {
		return inscriptionId == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionId());
	}

	public void htmInscriptionId(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "InscriptionId\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "InscriptionId() {");
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
				r.l("				data: {\"setInscriptionId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageInscriptionId()), "</span>");
				r.s("			<input");
							r.s(" name=\"inscriptionId\"");
							r.s(" value=\"", htmInscriptionId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmInscriptionId());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
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
	protected InscriptionScolaire pageUrlInit() {
		if(!pageUrlCouverture.dejaInitialise) {
			_pageUrl(pageUrlCouverture);
			if(pageUrl == null)
				setPageUrl(pageUrlCouverture.o);
		}
		pageUrlCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "PageUrl\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "PageUrl() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:objetSuggere">Trouver l'entité objetSuggere dans Solr</a>
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
	protected InscriptionScolaire objetSuggereInit() {
		if(!objetSuggereCouverture.dejaInitialise) {
			_objetSuggere(objetSuggereCouverture);
			if(objetSuggere == null)
				setObjetSuggere(objetSuggereCouverture.o);
		}
		objetSuggereCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
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
			r.s("<div id=\"patchInscriptionScolaire", strPk(), "ObjetSuggere\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchInscriptionScolaire", strPk(), "ObjetSuggere() {");
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

	protected boolean dejaInitialiseInscriptionScolaire = false;

	public InscriptionScolaire initLoinInscriptionScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseInscriptionScolaire) {
			dejaInitialiseInscriptionScolaire = true;
			initLoinInscriptionScolaire();
		}
		return (InscriptionScolaire)this;
	}

	public void initLoinInscriptionScolaire() {
		super.initLoinCluster(requeteSite_);
		initInscriptionScolaire();
	}

	public void initInscriptionScolaire() {
		inscriptionCleInit();
		blocClesInit();
		blocRechercheInit();
		blocInit();
		ecoleCleInit();
		anneeCleInit();
		saisonCleInit();
		sessionCleInit();
		ageCleInit();
		blocCleInit();
		enfantCleInit();
		mereClesInit();
		pereClesInit();
		gardienClesInit();
		paiementClesInit();
		scolaireTriInit();
		ecoleTriInit();
		anneeTriInit();
		saisonTriInit();
		sessionTriInit();
		ageTriInit();
		enfantRechercheInit();
		enfant_Init();
		enfantNomCompletInit();
		ecoleNomCompletInit();
		anneeDebutInit();
		anneeFinInit();
		saisonJourDebutInit();
		saisonEteInit();
		saisonHiverInit();
		saisonFraisInscriptionInit();
		saisonNomCompletInit();
		sessionJourDebutInit();
		sessionJourFinInit();
		ageNomCompletInit();
		ageDebutInit();
		ageFinInit();
		blocHeureDebutInit();
		blocHeureFinInit();
		blocPrixParMoisInit();
		blocDimancheInit();
		blocLundiInit();
		blocMardiInit();
		blocMercrediInit();
		blocJeudiInit();
		blocVendrediInit();
		blocSamediInit();
		inscriptionApprouveInit();
		inscriptionImmunisationsInit();
		familleMarieInit();
		familleSepareInit();
		familleDivorceInit();
		familleAddresseInit();
		familleCommentVousConnaissezEcoleInit();
		inscriptionConsiderationsSpecialesInit();
		inscriptionNomGroupeInit();
		inscriptionPaimentChaqueMoisInit();
		inscriptionPaimentCompletInit();
		inscriptionNomCompletInit();
		inscriptionIdInit();
		pageUrlInit();
		objetSuggereInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinInscriptionScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteInscriptionScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(blocRecherche != null)
			blocRecherche.setRequeteSite_(requeteSite_);
		if(bloc != null)
			bloc.setRequeteSite_(requeteSite_);
		if(enfantRecherche != null)
			enfantRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteInscriptionScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirInscriptionScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirInscriptionScolaire(String var) {
		InscriptionScolaire oInscriptionScolaire = (InscriptionScolaire)this;
		switch(var) {
			case "inscriptionCle":
				return oInscriptionScolaire.inscriptionCle;
			case "blocCles":
				return oInscriptionScolaire.blocCles;
			case "blocRecherche":
				return oInscriptionScolaire.blocRecherche;
			case "bloc":
				return oInscriptionScolaire.bloc;
			case "ecoleCle":
				return oInscriptionScolaire.ecoleCle;
			case "anneeCle":
				return oInscriptionScolaire.anneeCle;
			case "saisonCle":
				return oInscriptionScolaire.saisonCle;
			case "sessionCle":
				return oInscriptionScolaire.sessionCle;
			case "ageCle":
				return oInscriptionScolaire.ageCle;
			case "blocCle":
				return oInscriptionScolaire.blocCle;
			case "enfantCle":
				return oInscriptionScolaire.enfantCle;
			case "mereCles":
				return oInscriptionScolaire.mereCles;
			case "pereCles":
				return oInscriptionScolaire.pereCles;
			case "gardienCles":
				return oInscriptionScolaire.gardienCles;
			case "paiementCles":
				return oInscriptionScolaire.paiementCles;
			case "scolaireTri":
				return oInscriptionScolaire.scolaireTri;
			case "ecoleTri":
				return oInscriptionScolaire.ecoleTri;
			case "anneeTri":
				return oInscriptionScolaire.anneeTri;
			case "saisonTri":
				return oInscriptionScolaire.saisonTri;
			case "sessionTri":
				return oInscriptionScolaire.sessionTri;
			case "ageTri":
				return oInscriptionScolaire.ageTri;
			case "enfantRecherche":
				return oInscriptionScolaire.enfantRecherche;
			case "enfant_":
				return oInscriptionScolaire.enfant_;
			case "enfantNomComplet":
				return oInscriptionScolaire.enfantNomComplet;
			case "ecoleNomComplet":
				return oInscriptionScolaire.ecoleNomComplet;
			case "anneeDebut":
				return oInscriptionScolaire.anneeDebut;
			case "anneeFin":
				return oInscriptionScolaire.anneeFin;
			case "saisonJourDebut":
				return oInscriptionScolaire.saisonJourDebut;
			case "saisonEte":
				return oInscriptionScolaire.saisonEte;
			case "saisonHiver":
				return oInscriptionScolaire.saisonHiver;
			case "saisonFraisInscription":
				return oInscriptionScolaire.saisonFraisInscription;
			case "saisonNomComplet":
				return oInscriptionScolaire.saisonNomComplet;
			case "sessionJourDebut":
				return oInscriptionScolaire.sessionJourDebut;
			case "sessionJourFin":
				return oInscriptionScolaire.sessionJourFin;
			case "ageNomComplet":
				return oInscriptionScolaire.ageNomComplet;
			case "ageDebut":
				return oInscriptionScolaire.ageDebut;
			case "ageFin":
				return oInscriptionScolaire.ageFin;
			case "blocHeureDebut":
				return oInscriptionScolaire.blocHeureDebut;
			case "blocHeureFin":
				return oInscriptionScolaire.blocHeureFin;
			case "blocPrixParMois":
				return oInscriptionScolaire.blocPrixParMois;
			case "blocDimanche":
				return oInscriptionScolaire.blocDimanche;
			case "blocLundi":
				return oInscriptionScolaire.blocLundi;
			case "blocMardi":
				return oInscriptionScolaire.blocMardi;
			case "blocMercredi":
				return oInscriptionScolaire.blocMercredi;
			case "blocJeudi":
				return oInscriptionScolaire.blocJeudi;
			case "blocVendredi":
				return oInscriptionScolaire.blocVendredi;
			case "blocSamedi":
				return oInscriptionScolaire.blocSamedi;
			case "inscriptionApprouve":
				return oInscriptionScolaire.inscriptionApprouve;
			case "inscriptionImmunisations":
				return oInscriptionScolaire.inscriptionImmunisations;
			case "familleMarie":
				return oInscriptionScolaire.familleMarie;
			case "familleSepare":
				return oInscriptionScolaire.familleSepare;
			case "familleDivorce":
				return oInscriptionScolaire.familleDivorce;
			case "familleAddresse":
				return oInscriptionScolaire.familleAddresse;
			case "familleCommentVousConnaissezEcole":
				return oInscriptionScolaire.familleCommentVousConnaissezEcole;
			case "inscriptionConsiderationsSpeciales":
				return oInscriptionScolaire.inscriptionConsiderationsSpeciales;
			case "inscriptionNomGroupe":
				return oInscriptionScolaire.inscriptionNomGroupe;
			case "inscriptionPaimentChaqueMois":
				return oInscriptionScolaire.inscriptionPaimentChaqueMois;
			case "inscriptionPaimentComplet":
				return oInscriptionScolaire.inscriptionPaimentComplet;
			case "inscriptionNomComplet":
				return oInscriptionScolaire.inscriptionNomComplet;
			case "inscriptionId":
				return oInscriptionScolaire.inscriptionId;
			case "pageUrl":
				return oInscriptionScolaire.pageUrl;
			case "objetSuggere":
				return oInscriptionScolaire.objetSuggere;
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
				o = attribuerInscriptionScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerInscriptionScolaire(String var, Object val) {
		InscriptionScolaire oInscriptionScolaire = (InscriptionScolaire)this;
		switch(var) {
			case "blocCles":
				oInscriptionScolaire.addBlocCles((Long)val);
				return val;
			case "enfantCle":
				oInscriptionScolaire.setEnfantCle((Long)val);
				return val;
			case "mereCles":
				oInscriptionScolaire.addMereCles((Long)val);
				return val;
			case "pereCles":
				oInscriptionScolaire.addPereCles((Long)val);
				return val;
			case "gardienCles":
				oInscriptionScolaire.addGardienCles((Long)val);
				return val;
			case "paiementCles":
				oInscriptionScolaire.addPaiementCles((Long)val);
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
					o = definirInscriptionScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirInscriptionScolaire(String var, String val) {
		switch(var) {
			case "inscriptionApprouve":
				setInscriptionApprouve(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionImmunisations":
				setInscriptionImmunisations(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "familleMarie":
				setFamilleMarie(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "familleSepare":
				setFamilleSepare(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "familleDivorce":
				setFamilleDivorce(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "familleAddresse":
				setFamilleAddresse(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "familleCommentVousConnaissezEcole":
				setFamilleCommentVousConnaissezEcole(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionConsiderationsSpeciales":
				setInscriptionConsiderationsSpeciales(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionNomGroupe":
				setInscriptionNomGroupe(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionPaimentChaqueMois":
				setInscriptionPaimentChaqueMois(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionPaimentComplet":
				setInscriptionPaimentComplet(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesInscriptionScolaire = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerInscriptionScolaire(solrDocument);
	}
	public void peuplerInscriptionScolaire(SolrDocument solrDocument) {
		InscriptionScolaire oInscriptionScolaire = (InscriptionScolaire)this;
		sauvegardesInscriptionScolaire = (List<String>)solrDocument.get("sauvegardesInscriptionScolaire_stored_strings");
		if(sauvegardesInscriptionScolaire != null) {

			if(sauvegardesInscriptionScolaire.contains("inscriptionCle")) {
				Long inscriptionCle = (Long)solrDocument.get("inscriptionCle_stored_long");
				if(inscriptionCle != null)
					oInscriptionScolaire.setInscriptionCle(inscriptionCle);
			}

			List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
			if(blocCles != null)
				oInscriptionScolaire.blocCles.addAll(blocCles);

			if(sauvegardesInscriptionScolaire.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oInscriptionScolaire.setEcoleCle(ecoleCle);
			}

			if(sauvegardesInscriptionScolaire.contains("saisonCle")) {
				Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
				if(saisonCle != null)
					oInscriptionScolaire.setSaisonCle(saisonCle);
			}

			if(sauvegardesInscriptionScolaire.contains("sessionCle")) {
				Long sessionCle = (Long)solrDocument.get("sessionCle_stored_long");
				if(sessionCle != null)
					oInscriptionScolaire.setSessionCle(sessionCle);
			}

			if(sauvegardesInscriptionScolaire.contains("ageCle")) {
				Long ageCle = (Long)solrDocument.get("ageCle_stored_long");
				if(ageCle != null)
					oInscriptionScolaire.setAgeCle(ageCle);
			}

			if(sauvegardesInscriptionScolaire.contains("blocCle")) {
				Long blocCle = (Long)solrDocument.get("blocCle_stored_long");
				if(blocCle != null)
					oInscriptionScolaire.setBlocCle(blocCle);
			}

			Long enfantCle = (Long)solrDocument.get("enfantCle_stored_long");
			if(enfantCle != null)
				oInscriptionScolaire.setEnfantCle(enfantCle);

			List<Long> mereCles = (List<Long>)solrDocument.get("mereCles_stored_longs");
			if(mereCles != null)
				oInscriptionScolaire.mereCles.addAll(mereCles);

			List<Long> pereCles = (List<Long>)solrDocument.get("pereCles_stored_longs");
			if(pereCles != null)
				oInscriptionScolaire.pereCles.addAll(pereCles);

			List<Long> gardienCles = (List<Long>)solrDocument.get("gardienCles_stored_longs");
			if(gardienCles != null)
				oInscriptionScolaire.gardienCles.addAll(gardienCles);

			List<Long> paiementCles = (List<Long>)solrDocument.get("paiementCles_stored_longs");
			if(paiementCles != null)
				oInscriptionScolaire.paiementCles.addAll(paiementCles);

			if(sauvegardesInscriptionScolaire.contains("scolaireTri")) {
				Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
				if(scolaireTri != null)
					oInscriptionScolaire.setScolaireTri(scolaireTri);
			}

			if(sauvegardesInscriptionScolaire.contains("ecoleTri")) {
				Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
				if(ecoleTri != null)
					oInscriptionScolaire.setEcoleTri(ecoleTri);
			}

			if(sauvegardesInscriptionScolaire.contains("anneeTri")) {
				Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
				if(anneeTri != null)
					oInscriptionScolaire.setAnneeTri(anneeTri);
			}

			if(sauvegardesInscriptionScolaire.contains("saisonTri")) {
				Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
				if(saisonTri != null)
					oInscriptionScolaire.setSaisonTri(saisonTri);
			}

			if(sauvegardesInscriptionScolaire.contains("sessionTri")) {
				Integer sessionTri = (Integer)solrDocument.get("sessionTri_stored_int");
				if(sessionTri != null)
					oInscriptionScolaire.setSessionTri(sessionTri);
			}

			if(sauvegardesInscriptionScolaire.contains("ageTri")) {
				Integer ageTri = (Integer)solrDocument.get("ageTri_stored_int");
				if(ageTri != null)
					oInscriptionScolaire.setAgeTri(ageTri);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantNomComplet")) {
				String enfantNomComplet = (String)solrDocument.get("enfantNomComplet_stored_string");
				if(enfantNomComplet != null)
					oInscriptionScolaire.setEnfantNomComplet(enfantNomComplet);
			}

			if(sauvegardesInscriptionScolaire.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oInscriptionScolaire.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardesInscriptionScolaire.contains("anneeDebut")) {
				Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
				if(anneeDebut != null)
					oInscriptionScolaire.setAnneeDebut(anneeDebut);
			}

			if(sauvegardesInscriptionScolaire.contains("anneeFin")) {
				Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
				if(anneeFin != null)
					oInscriptionScolaire.setAnneeFin(anneeFin);
			}

			if(sauvegardesInscriptionScolaire.contains("saisonJourDebut")) {
				Date saisonJourDebut = (Date)solrDocument.get("saisonJourDebut_stored_date");
				if(saisonJourDebut != null)
					oInscriptionScolaire.setSaisonJourDebut(saisonJourDebut);
			}

			if(sauvegardesInscriptionScolaire.contains("saisonEte")) {
				Boolean saisonEte = (Boolean)solrDocument.get("saisonEte_stored_boolean");
				if(saisonEte != null)
					oInscriptionScolaire.setSaisonEte(saisonEte);
			}

			if(sauvegardesInscriptionScolaire.contains("saisonHiver")) {
				Boolean saisonHiver = (Boolean)solrDocument.get("saisonHiver_stored_boolean");
				if(saisonHiver != null)
					oInscriptionScolaire.setSaisonHiver(saisonHiver);
			}

			if(sauvegardesInscriptionScolaire.contains("saisonFraisInscription")) {
				Double saisonFraisInscription = (Double)solrDocument.get("saisonFraisInscription_stored_double");
				if(saisonFraisInscription != null)
					oInscriptionScolaire.setSaisonFraisInscription(saisonFraisInscription);
			}

			if(sauvegardesInscriptionScolaire.contains("saisonNomComplet")) {
				String saisonNomComplet = (String)solrDocument.get("saisonNomComplet_stored_string");
				if(saisonNomComplet != null)
					oInscriptionScolaire.setSaisonNomComplet(saisonNomComplet);
			}

			if(sauvegardesInscriptionScolaire.contains("sessionJourDebut")) {
				Date sessionJourDebut = (Date)solrDocument.get("sessionJourDebut_stored_date");
				if(sessionJourDebut != null)
					oInscriptionScolaire.setSessionJourDebut(sessionJourDebut);
			}

			if(sauvegardesInscriptionScolaire.contains("sessionJourFin")) {
				Date sessionJourFin = (Date)solrDocument.get("sessionJourFin_stored_date");
				if(sessionJourFin != null)
					oInscriptionScolaire.setSessionJourFin(sessionJourFin);
			}

			if(sauvegardesInscriptionScolaire.contains("ageNomComplet")) {
				String ageNomComplet = (String)solrDocument.get("ageNomComplet_stored_string");
				if(ageNomComplet != null)
					oInscriptionScolaire.setAgeNomComplet(ageNomComplet);
			}

			if(sauvegardesInscriptionScolaire.contains("ageDebut")) {
				Integer ageDebut = (Integer)solrDocument.get("ageDebut_stored_int");
				if(ageDebut != null)
					oInscriptionScolaire.setAgeDebut(ageDebut);
			}

			if(sauvegardesInscriptionScolaire.contains("ageFin")) {
				Integer ageFin = (Integer)solrDocument.get("ageFin_stored_int");
				if(ageFin != null)
					oInscriptionScolaire.setAgeFin(ageFin);
			}

			if(sauvegardesInscriptionScolaire.contains("blocHeureDebut")) {
				String blocHeureDebut = (String)solrDocument.get("blocHeureDebut_stored_string");
				if(blocHeureDebut != null)
					oInscriptionScolaire.setBlocHeureDebut(blocHeureDebut);
			}

			if(sauvegardesInscriptionScolaire.contains("blocHeureFin")) {
				String blocHeureFin = (String)solrDocument.get("blocHeureFin_stored_string");
				if(blocHeureFin != null)
					oInscriptionScolaire.setBlocHeureFin(blocHeureFin);
			}

			if(sauvegardesInscriptionScolaire.contains("blocPrixParMois")) {
				Double blocPrixParMois = (Double)solrDocument.get("blocPrixParMois_stored_double");
				if(blocPrixParMois != null)
					oInscriptionScolaire.setBlocPrixParMois(blocPrixParMois);
			}

			if(sauvegardesInscriptionScolaire.contains("blocDimanche")) {
				Boolean blocDimanche = (Boolean)solrDocument.get("blocDimanche_stored_boolean");
				if(blocDimanche != null)
					oInscriptionScolaire.setBlocDimanche(blocDimanche);
			}

			if(sauvegardesInscriptionScolaire.contains("blocLundi")) {
				Boolean blocLundi = (Boolean)solrDocument.get("blocLundi_stored_boolean");
				if(blocLundi != null)
					oInscriptionScolaire.setBlocLundi(blocLundi);
			}

			if(sauvegardesInscriptionScolaire.contains("blocMardi")) {
				Boolean blocMardi = (Boolean)solrDocument.get("blocMardi_stored_boolean");
				if(blocMardi != null)
					oInscriptionScolaire.setBlocMardi(blocMardi);
			}

			if(sauvegardesInscriptionScolaire.contains("blocMercredi")) {
				Boolean blocMercredi = (Boolean)solrDocument.get("blocMercredi_stored_boolean");
				if(blocMercredi != null)
					oInscriptionScolaire.setBlocMercredi(blocMercredi);
			}

			if(sauvegardesInscriptionScolaire.contains("blocJeudi")) {
				Boolean blocJeudi = (Boolean)solrDocument.get("blocJeudi_stored_boolean");
				if(blocJeudi != null)
					oInscriptionScolaire.setBlocJeudi(blocJeudi);
			}

			if(sauvegardesInscriptionScolaire.contains("blocVendredi")) {
				Boolean blocVendredi = (Boolean)solrDocument.get("blocVendredi_stored_boolean");
				if(blocVendredi != null)
					oInscriptionScolaire.setBlocVendredi(blocVendredi);
			}

			if(sauvegardesInscriptionScolaire.contains("blocSamedi")) {
				Boolean blocSamedi = (Boolean)solrDocument.get("blocSamedi_stored_boolean");
				if(blocSamedi != null)
					oInscriptionScolaire.setBlocSamedi(blocSamedi);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionApprouve")) {
				Boolean inscriptionApprouve = (Boolean)solrDocument.get("inscriptionApprouve_stored_boolean");
				if(inscriptionApprouve != null)
					oInscriptionScolaire.setInscriptionApprouve(inscriptionApprouve);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionImmunisations")) {
				Boolean inscriptionImmunisations = (Boolean)solrDocument.get("inscriptionImmunisations_stored_boolean");
				if(inscriptionImmunisations != null)
					oInscriptionScolaire.setInscriptionImmunisations(inscriptionImmunisations);
			}

			if(sauvegardesInscriptionScolaire.contains("familleMarie")) {
				Boolean familleMarie = (Boolean)solrDocument.get("familleMarie_stored_boolean");
				if(familleMarie != null)
					oInscriptionScolaire.setFamilleMarie(familleMarie);
			}

			if(sauvegardesInscriptionScolaire.contains("familleSepare")) {
				Boolean familleSepare = (Boolean)solrDocument.get("familleSepare_stored_boolean");
				if(familleSepare != null)
					oInscriptionScolaire.setFamilleSepare(familleSepare);
			}

			if(sauvegardesInscriptionScolaire.contains("familleDivorce")) {
				Boolean familleDivorce = (Boolean)solrDocument.get("familleDivorce_stored_boolean");
				if(familleDivorce != null)
					oInscriptionScolaire.setFamilleDivorce(familleDivorce);
			}

			if(sauvegardesInscriptionScolaire.contains("familleAddresse")) {
				String familleAddresse = (String)solrDocument.get("familleAddresse_stored_string");
				if(familleAddresse != null)
					oInscriptionScolaire.setFamilleAddresse(familleAddresse);
			}

			if(sauvegardesInscriptionScolaire.contains("familleCommentVousConnaissezEcole")) {
				String familleCommentVousConnaissezEcole = (String)solrDocument.get("familleCommentVousConnaissezEcole_stored_string");
				if(familleCommentVousConnaissezEcole != null)
					oInscriptionScolaire.setFamilleCommentVousConnaissezEcole(familleCommentVousConnaissezEcole);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionConsiderationsSpeciales")) {
				String inscriptionConsiderationsSpeciales = (String)solrDocument.get("inscriptionConsiderationsSpeciales_stored_string");
				if(inscriptionConsiderationsSpeciales != null)
					oInscriptionScolaire.setInscriptionConsiderationsSpeciales(inscriptionConsiderationsSpeciales);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionNomGroupe")) {
				String inscriptionNomGroupe = (String)solrDocument.get("inscriptionNomGroupe_stored_string");
				if(inscriptionNomGroupe != null)
					oInscriptionScolaire.setInscriptionNomGroupe(inscriptionNomGroupe);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionPaimentChaqueMois")) {
				Boolean inscriptionPaimentChaqueMois = (Boolean)solrDocument.get("inscriptionPaimentChaqueMois_stored_boolean");
				if(inscriptionPaimentChaqueMois != null)
					oInscriptionScolaire.setInscriptionPaimentChaqueMois(inscriptionPaimentChaqueMois);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionPaimentComplet")) {
				Boolean inscriptionPaimentComplet = (Boolean)solrDocument.get("inscriptionPaimentComplet_stored_boolean");
				if(inscriptionPaimentComplet != null)
					oInscriptionScolaire.setInscriptionPaimentComplet(inscriptionPaimentComplet);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionNomComplet")) {
				String inscriptionNomComplet = (String)solrDocument.get("inscriptionNomComplet_stored_string");
				if(inscriptionNomComplet != null)
					oInscriptionScolaire.setInscriptionNomComplet(inscriptionNomComplet);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionId")) {
				String inscriptionId = (String)solrDocument.get("inscriptionId_stored_string");
				if(inscriptionId != null)
					oInscriptionScolaire.setInscriptionId(inscriptionId);
			}

			if(sauvegardesInscriptionScolaire.contains("pageUrl")) {
				String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
				if(pageUrl != null)
					oInscriptionScolaire.setPageUrl(pageUrl);
			}

			if(sauvegardesInscriptionScolaire.contains("objetSuggere")) {
				String objetSuggere = (String)solrDocument.get("objetSuggere_suggested");
				oInscriptionScolaire.setObjetSuggere(objetSuggere);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.inscription.InscriptionScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			InscriptionScolaire o = new InscriptionScolaire();
			o.requeteSiteInscriptionScolaire(requeteSite);
			o.initLoinInscriptionScolaire(requeteSite);
			o.indexerInscriptionScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerInscriptionScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerInscriptionScolaire(document);
	}

	public void indexerInscriptionScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerInscriptionScolaire(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerInscriptionScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerInscriptionScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerInscriptionScolaire(SolrInputDocument document) {
		if(sauvegardesInscriptionScolaire != null)
			document.addField("sauvegardesInscriptionScolaire_stored_strings", sauvegardesInscriptionScolaire);

		if(inscriptionCle != null) {
			document.addField("inscriptionCle_indexed_long", inscriptionCle);
			document.addField("inscriptionCle_stored_long", inscriptionCle);
		}
		if(blocCles != null) {
			for(java.lang.Long o : blocCles) {
				document.addField("blocCles_indexed_longs", o);
			}
			for(java.lang.Long o : blocCles) {
				document.addField("blocCles_stored_longs", o);
			}
		}
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
		if(enfantCle != null) {
			document.addField("enfantCle_indexed_long", enfantCle);
			document.addField("enfantCle_stored_long", enfantCle);
		}
		if(mereCles != null) {
			for(java.lang.Long o : mereCles) {
				document.addField("mereCles_indexed_longs", o);
			}
			for(java.lang.Long o : mereCles) {
				document.addField("mereCles_stored_longs", o);
			}
		}
		if(pereCles != null) {
			for(java.lang.Long o : pereCles) {
				document.addField("pereCles_indexed_longs", o);
			}
			for(java.lang.Long o : pereCles) {
				document.addField("pereCles_stored_longs", o);
			}
		}
		if(gardienCles != null) {
			for(java.lang.Long o : gardienCles) {
				document.addField("gardienCles_indexed_longs", o);
			}
			for(java.lang.Long o : gardienCles) {
				document.addField("gardienCles_stored_longs", o);
			}
		}
		if(paiementCles != null) {
			for(java.lang.Long o : paiementCles) {
				document.addField("paiementCles_indexed_longs", o);
			}
			for(java.lang.Long o : paiementCles) {
				document.addField("paiementCles_stored_longs", o);
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
		if(ageTri != null) {
			document.addField("ageTri_indexed_int", ageTri);
			document.addField("ageTri_stored_int", ageTri);
		}
		if(enfantNomComplet != null) {
			document.addField("enfantNomComplet_indexed_string", enfantNomComplet);
			document.addField("enfantNomComplet_stored_string", enfantNomComplet);
		}
		if(ecoleNomComplet != null) {
			document.addField("ecoleNomComplet_indexed_string", ecoleNomComplet);
			document.addField("ecoleNomComplet_stored_string", ecoleNomComplet);
		}
		if(anneeDebut != null) {
			document.addField("anneeDebut_indexed_int", anneeDebut);
			document.addField("anneeDebut_stored_int", anneeDebut);
		}
		if(anneeFin != null) {
			document.addField("anneeFin_indexed_int", anneeFin);
			document.addField("anneeFin_stored_int", anneeFin);
		}
		if(saisonJourDebut != null) {
			document.addField("saisonJourDebut_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(saisonJourDebut.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("saisonJourDebut_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(saisonJourDebut.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
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
			document.addField("saisonFraisInscription_indexed_double", saisonFraisInscription.doubleValue());
			document.addField("saisonFraisInscription_stored_double", saisonFraisInscription.doubleValue());
		}
		if(saisonNomComplet != null) {
			document.addField("saisonNomComplet_indexed_string", saisonNomComplet);
			document.addField("saisonNomComplet_stored_string", saisonNomComplet);
		}
		if(sessionJourDebut != null) {
			document.addField("sessionJourDebut_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionJourDebut.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionJourDebut_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionJourDebut.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionJourFin != null) {
			document.addField("sessionJourFin_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionJourFin.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionJourFin_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionJourFin.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(ageNomComplet != null) {
			document.addField("ageNomComplet_indexed_string", ageNomComplet);
			document.addField("ageNomComplet_stored_string", ageNomComplet);
		}
		if(ageDebut != null) {
			document.addField("ageDebut_indexed_int", ageDebut);
			document.addField("ageDebut_stored_int", ageDebut);
		}
		if(ageFin != null) {
			document.addField("ageFin_indexed_int", ageFin);
			document.addField("ageFin_stored_int", ageFin);
		}
		if(blocHeureDebut != null) {
			document.addField("blocHeureDebut_indexed_string", DateTimeFormatter.ofPattern("HH mm").format(blocHeureDebut.atOffset(ZoneOffset.UTC)));
			document.addField("blocHeureDebut_stored_string", DateTimeFormatter.ofPattern("HH mm").format(blocHeureDebut.atOffset(ZoneOffset.UTC)));
		}
		if(blocHeureFin != null) {
			document.addField("blocHeureFin_indexed_string", DateTimeFormatter.ofPattern("HH mm").format(blocHeureFin.atOffset(ZoneOffset.UTC)));
			document.addField("blocHeureFin_stored_string", DateTimeFormatter.ofPattern("HH mm").format(blocHeureFin.atOffset(ZoneOffset.UTC)));
		}
		if(blocPrixParMois != null) {
			document.addField("blocPrixParMois_indexed_double", blocPrixParMois.doubleValue());
			document.addField("blocPrixParMois_stored_double", blocPrixParMois.doubleValue());
		}
		if(blocDimanche != null) {
			document.addField("blocDimanche_indexed_boolean", blocDimanche);
			document.addField("blocDimanche_stored_boolean", blocDimanche);
		}
		if(blocLundi != null) {
			document.addField("blocLundi_indexed_boolean", blocLundi);
			document.addField("blocLundi_stored_boolean", blocLundi);
		}
		if(blocMardi != null) {
			document.addField("blocMardi_indexed_boolean", blocMardi);
			document.addField("blocMardi_stored_boolean", blocMardi);
		}
		if(blocMercredi != null) {
			document.addField("blocMercredi_indexed_boolean", blocMercredi);
			document.addField("blocMercredi_stored_boolean", blocMercredi);
		}
		if(blocJeudi != null) {
			document.addField("blocJeudi_indexed_boolean", blocJeudi);
			document.addField("blocJeudi_stored_boolean", blocJeudi);
		}
		if(blocVendredi != null) {
			document.addField("blocVendredi_indexed_boolean", blocVendredi);
			document.addField("blocVendredi_stored_boolean", blocVendredi);
		}
		if(blocSamedi != null) {
			document.addField("blocSamedi_indexed_boolean", blocSamedi);
			document.addField("blocSamedi_stored_boolean", blocSamedi);
		}
		if(inscriptionApprouve != null) {
			document.addField("inscriptionApprouve_indexed_boolean", inscriptionApprouve);
			document.addField("inscriptionApprouve_stored_boolean", inscriptionApprouve);
		}
		if(inscriptionImmunisations != null) {
			document.addField("inscriptionImmunisations_indexed_boolean", inscriptionImmunisations);
			document.addField("inscriptionImmunisations_stored_boolean", inscriptionImmunisations);
		}
		if(familleMarie != null) {
			document.addField("familleMarie_indexed_boolean", familleMarie);
			document.addField("familleMarie_stored_boolean", familleMarie);
		}
		if(familleSepare != null) {
			document.addField("familleSepare_indexed_boolean", familleSepare);
			document.addField("familleSepare_stored_boolean", familleSepare);
		}
		if(familleDivorce != null) {
			document.addField("familleDivorce_indexed_boolean", familleDivorce);
			document.addField("familleDivorce_stored_boolean", familleDivorce);
		}
		if(familleAddresse != null) {
			document.addField("familleAddresse_indexed_string", familleAddresse);
			document.addField("familleAddresse_stored_string", familleAddresse);
		}
		if(familleCommentVousConnaissezEcole != null) {
			document.addField("familleCommentVousConnaissezEcole_indexed_string", familleCommentVousConnaissezEcole);
			document.addField("familleCommentVousConnaissezEcole_stored_string", familleCommentVousConnaissezEcole);
		}
		if(inscriptionConsiderationsSpeciales != null) {
			document.addField("inscriptionConsiderationsSpeciales_indexed_string", inscriptionConsiderationsSpeciales);
			document.addField("inscriptionConsiderationsSpeciales_stored_string", inscriptionConsiderationsSpeciales);
		}
		if(inscriptionNomGroupe != null) {
			document.addField("inscriptionNomGroupe_indexed_string", inscriptionNomGroupe);
			document.addField("inscriptionNomGroupe_stored_string", inscriptionNomGroupe);
		}
		if(inscriptionPaimentChaqueMois != null) {
			document.addField("inscriptionPaimentChaqueMois_indexed_boolean", inscriptionPaimentChaqueMois);
			document.addField("inscriptionPaimentChaqueMois_stored_boolean", inscriptionPaimentChaqueMois);
		}
		if(inscriptionPaimentComplet != null) {
			document.addField("inscriptionPaimentComplet_indexed_boolean", inscriptionPaimentComplet);
			document.addField("inscriptionPaimentComplet_stored_boolean", inscriptionPaimentComplet);
		}
		if(inscriptionNomComplet != null) {
			document.addField("inscriptionNomComplet_indexed_string", inscriptionNomComplet);
			document.addField("inscriptionNomComplet_stored_string", inscriptionNomComplet);
		}
		if(inscriptionId != null) {
			document.addField("inscriptionId_indexed_string", inscriptionId);
			document.addField("inscriptionId_stored_string", inscriptionId);
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

	public void desindexerInscriptionScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinInscriptionScolaire(requeteSite);
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
		stockerInscriptionScolaire(solrDocument);
	}
	public void stockerInscriptionScolaire(SolrDocument solrDocument) {
		InscriptionScolaire oInscriptionScolaire = (InscriptionScolaire)this;

		Long inscriptionCle = (Long)solrDocument.get("inscriptionCle_stored_long");
		if(inscriptionCle != null)
			oInscriptionScolaire.setInscriptionCle(inscriptionCle);

		List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
		if(blocCles != null)
			oInscriptionScolaire.blocCles.addAll(blocCles);

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oInscriptionScolaire.setEcoleCle(ecoleCle);

		Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
		if(saisonCle != null)
			oInscriptionScolaire.setSaisonCle(saisonCle);

		Long sessionCle = (Long)solrDocument.get("sessionCle_stored_long");
		if(sessionCle != null)
			oInscriptionScolaire.setSessionCle(sessionCle);

		Long ageCle = (Long)solrDocument.get("ageCle_stored_long");
		if(ageCle != null)
			oInscriptionScolaire.setAgeCle(ageCle);

		Long blocCle = (Long)solrDocument.get("blocCle_stored_long");
		if(blocCle != null)
			oInscriptionScolaire.setBlocCle(blocCle);

		Long enfantCle = (Long)solrDocument.get("enfantCle_stored_long");
		if(enfantCle != null)
			oInscriptionScolaire.setEnfantCle(enfantCle);

		List<Long> mereCles = (List<Long>)solrDocument.get("mereCles_stored_longs");
		if(mereCles != null)
			oInscriptionScolaire.mereCles.addAll(mereCles);

		List<Long> pereCles = (List<Long>)solrDocument.get("pereCles_stored_longs");
		if(pereCles != null)
			oInscriptionScolaire.pereCles.addAll(pereCles);

		List<Long> gardienCles = (List<Long>)solrDocument.get("gardienCles_stored_longs");
		if(gardienCles != null)
			oInscriptionScolaire.gardienCles.addAll(gardienCles);

		List<Long> paiementCles = (List<Long>)solrDocument.get("paiementCles_stored_longs");
		if(paiementCles != null)
			oInscriptionScolaire.paiementCles.addAll(paiementCles);

		Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
		if(scolaireTri != null)
			oInscriptionScolaire.setScolaireTri(scolaireTri);

		Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
		if(ecoleTri != null)
			oInscriptionScolaire.setEcoleTri(ecoleTri);

		Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
		if(anneeTri != null)
			oInscriptionScolaire.setAnneeTri(anneeTri);

		Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
		if(saisonTri != null)
			oInscriptionScolaire.setSaisonTri(saisonTri);

		Integer sessionTri = (Integer)solrDocument.get("sessionTri_stored_int");
		if(sessionTri != null)
			oInscriptionScolaire.setSessionTri(sessionTri);

		Integer ageTri = (Integer)solrDocument.get("ageTri_stored_int");
		if(ageTri != null)
			oInscriptionScolaire.setAgeTri(ageTri);

		String enfantNomComplet = (String)solrDocument.get("enfantNomComplet_stored_string");
		if(enfantNomComplet != null)
			oInscriptionScolaire.setEnfantNomComplet(enfantNomComplet);

		String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
		if(ecoleNomComplet != null)
			oInscriptionScolaire.setEcoleNomComplet(ecoleNomComplet);

		Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
		if(anneeDebut != null)
			oInscriptionScolaire.setAnneeDebut(anneeDebut);

		Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
		if(anneeFin != null)
			oInscriptionScolaire.setAnneeFin(anneeFin);

		Date saisonJourDebut = (Date)solrDocument.get("saisonJourDebut_stored_date");
		if(saisonJourDebut != null)
			oInscriptionScolaire.setSaisonJourDebut(saisonJourDebut);

		Boolean saisonEte = (Boolean)solrDocument.get("saisonEte_stored_boolean");
		if(saisonEte != null)
			oInscriptionScolaire.setSaisonEte(saisonEte);

		Boolean saisonHiver = (Boolean)solrDocument.get("saisonHiver_stored_boolean");
		if(saisonHiver != null)
			oInscriptionScolaire.setSaisonHiver(saisonHiver);

		Double saisonFraisInscription = (Double)solrDocument.get("saisonFraisInscription_stored_double");
		if(saisonFraisInscription != null)
			oInscriptionScolaire.setSaisonFraisInscription(saisonFraisInscription);

		String saisonNomComplet = (String)solrDocument.get("saisonNomComplet_stored_string");
		if(saisonNomComplet != null)
			oInscriptionScolaire.setSaisonNomComplet(saisonNomComplet);

		Date sessionJourDebut = (Date)solrDocument.get("sessionJourDebut_stored_date");
		if(sessionJourDebut != null)
			oInscriptionScolaire.setSessionJourDebut(sessionJourDebut);

		Date sessionJourFin = (Date)solrDocument.get("sessionJourFin_stored_date");
		if(sessionJourFin != null)
			oInscriptionScolaire.setSessionJourFin(sessionJourFin);

		String ageNomComplet = (String)solrDocument.get("ageNomComplet_stored_string");
		if(ageNomComplet != null)
			oInscriptionScolaire.setAgeNomComplet(ageNomComplet);

		Integer ageDebut = (Integer)solrDocument.get("ageDebut_stored_int");
		if(ageDebut != null)
			oInscriptionScolaire.setAgeDebut(ageDebut);

		Integer ageFin = (Integer)solrDocument.get("ageFin_stored_int");
		if(ageFin != null)
			oInscriptionScolaire.setAgeFin(ageFin);

		String blocHeureDebut = (String)solrDocument.get("blocHeureDebut_stored_string");
		if(blocHeureDebut != null)
			oInscriptionScolaire.setBlocHeureDebut(blocHeureDebut);

		String blocHeureFin = (String)solrDocument.get("blocHeureFin_stored_string");
		if(blocHeureFin != null)
			oInscriptionScolaire.setBlocHeureFin(blocHeureFin);

		Double blocPrixParMois = (Double)solrDocument.get("blocPrixParMois_stored_double");
		if(blocPrixParMois != null)
			oInscriptionScolaire.setBlocPrixParMois(blocPrixParMois);

		Boolean blocDimanche = (Boolean)solrDocument.get("blocDimanche_stored_boolean");
		if(blocDimanche != null)
			oInscriptionScolaire.setBlocDimanche(blocDimanche);

		Boolean blocLundi = (Boolean)solrDocument.get("blocLundi_stored_boolean");
		if(blocLundi != null)
			oInscriptionScolaire.setBlocLundi(blocLundi);

		Boolean blocMardi = (Boolean)solrDocument.get("blocMardi_stored_boolean");
		if(blocMardi != null)
			oInscriptionScolaire.setBlocMardi(blocMardi);

		Boolean blocMercredi = (Boolean)solrDocument.get("blocMercredi_stored_boolean");
		if(blocMercredi != null)
			oInscriptionScolaire.setBlocMercredi(blocMercredi);

		Boolean blocJeudi = (Boolean)solrDocument.get("blocJeudi_stored_boolean");
		if(blocJeudi != null)
			oInscriptionScolaire.setBlocJeudi(blocJeudi);

		Boolean blocVendredi = (Boolean)solrDocument.get("blocVendredi_stored_boolean");
		if(blocVendredi != null)
			oInscriptionScolaire.setBlocVendredi(blocVendredi);

		Boolean blocSamedi = (Boolean)solrDocument.get("blocSamedi_stored_boolean");
		if(blocSamedi != null)
			oInscriptionScolaire.setBlocSamedi(blocSamedi);

		Boolean inscriptionApprouve = (Boolean)solrDocument.get("inscriptionApprouve_stored_boolean");
		if(inscriptionApprouve != null)
			oInscriptionScolaire.setInscriptionApprouve(inscriptionApprouve);

		Boolean inscriptionImmunisations = (Boolean)solrDocument.get("inscriptionImmunisations_stored_boolean");
		if(inscriptionImmunisations != null)
			oInscriptionScolaire.setInscriptionImmunisations(inscriptionImmunisations);

		Boolean familleMarie = (Boolean)solrDocument.get("familleMarie_stored_boolean");
		if(familleMarie != null)
			oInscriptionScolaire.setFamilleMarie(familleMarie);

		Boolean familleSepare = (Boolean)solrDocument.get("familleSepare_stored_boolean");
		if(familleSepare != null)
			oInscriptionScolaire.setFamilleSepare(familleSepare);

		Boolean familleDivorce = (Boolean)solrDocument.get("familleDivorce_stored_boolean");
		if(familleDivorce != null)
			oInscriptionScolaire.setFamilleDivorce(familleDivorce);

		String familleAddresse = (String)solrDocument.get("familleAddresse_stored_string");
		if(familleAddresse != null)
			oInscriptionScolaire.setFamilleAddresse(familleAddresse);

		String familleCommentVousConnaissezEcole = (String)solrDocument.get("familleCommentVousConnaissezEcole_stored_string");
		if(familleCommentVousConnaissezEcole != null)
			oInscriptionScolaire.setFamilleCommentVousConnaissezEcole(familleCommentVousConnaissezEcole);

		String inscriptionConsiderationsSpeciales = (String)solrDocument.get("inscriptionConsiderationsSpeciales_stored_string");
		if(inscriptionConsiderationsSpeciales != null)
			oInscriptionScolaire.setInscriptionConsiderationsSpeciales(inscriptionConsiderationsSpeciales);

		String inscriptionNomGroupe = (String)solrDocument.get("inscriptionNomGroupe_stored_string");
		if(inscriptionNomGroupe != null)
			oInscriptionScolaire.setInscriptionNomGroupe(inscriptionNomGroupe);

		Boolean inscriptionPaimentChaqueMois = (Boolean)solrDocument.get("inscriptionPaimentChaqueMois_stored_boolean");
		if(inscriptionPaimentChaqueMois != null)
			oInscriptionScolaire.setInscriptionPaimentChaqueMois(inscriptionPaimentChaqueMois);

		Boolean inscriptionPaimentComplet = (Boolean)solrDocument.get("inscriptionPaimentComplet_stored_boolean");
		if(inscriptionPaimentComplet != null)
			oInscriptionScolaire.setInscriptionPaimentComplet(inscriptionPaimentComplet);

		String inscriptionNomComplet = (String)solrDocument.get("inscriptionNomComplet_stored_string");
		if(inscriptionNomComplet != null)
			oInscriptionScolaire.setInscriptionNomComplet(inscriptionNomComplet);

		String inscriptionId = (String)solrDocument.get("inscriptionId_stored_string");
		if(inscriptionId != null)
			oInscriptionScolaire.setInscriptionId(inscriptionId);

		String pageUrl = (String)solrDocument.get("pageUrl_stored_string");
		if(pageUrl != null)
			oInscriptionScolaire.setPageUrl(pageUrl);

		String objetSuggere = (String)solrDocument.get("objetSuggere_suggested");
		oInscriptionScolaire.setObjetSuggere(objetSuggere);

		super.stockerCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), blocCles, enfantCle, mereCles, pereCles, gardienCles, paiementCles, inscriptionApprouve, inscriptionImmunisations, familleMarie, familleSepare, familleDivorce, familleAddresse, familleCommentVousConnaissezEcole, inscriptionConsiderationsSpeciales, inscriptionNomGroupe, inscriptionPaimentChaqueMois, inscriptionPaimentComplet);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof InscriptionScolaire))
			return false;
		InscriptionScolaire that = (InscriptionScolaire)o;
		return super.equals(o)
				&& Objects.equals( blocCles, that.blocCles )
				&& Objects.equals( enfantCle, that.enfantCle )
				&& Objects.equals( mereCles, that.mereCles )
				&& Objects.equals( pereCles, that.pereCles )
				&& Objects.equals( gardienCles, that.gardienCles )
				&& Objects.equals( paiementCles, that.paiementCles )
				&& Objects.equals( inscriptionApprouve, that.inscriptionApprouve )
				&& Objects.equals( inscriptionImmunisations, that.inscriptionImmunisations )
				&& Objects.equals( familleMarie, that.familleMarie )
				&& Objects.equals( familleSepare, that.familleSepare )
				&& Objects.equals( familleDivorce, that.familleDivorce )
				&& Objects.equals( familleAddresse, that.familleAddresse )
				&& Objects.equals( familleCommentVousConnaissezEcole, that.familleCommentVousConnaissezEcole )
				&& Objects.equals( inscriptionConsiderationsSpeciales, that.inscriptionConsiderationsSpeciales )
				&& Objects.equals( inscriptionNomGroupe, that.inscriptionNomGroupe )
				&& Objects.equals( inscriptionPaimentChaqueMois, that.inscriptionPaimentChaqueMois )
				&& Objects.equals( inscriptionPaimentComplet, that.inscriptionPaimentComplet );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("InscriptionScolaire { ");
		sb.append( "blocCles: " ).append(blocCles);
		sb.append( ", enfantCle: " ).append(enfantCle);
		sb.append( ", mereCles: " ).append(mereCles);
		sb.append( ", pereCles: " ).append(pereCles);
		sb.append( ", gardienCles: " ).append(gardienCles);
		sb.append( ", paiementCles: " ).append(paiementCles);
		sb.append( ", inscriptionApprouve: " ).append(inscriptionApprouve);
		sb.append( ", inscriptionImmunisations: " ).append(inscriptionImmunisations);
		sb.append( ", familleMarie: " ).append(familleMarie);
		sb.append( ", familleSepare: " ).append(familleSepare);
		sb.append( ", familleDivorce: " ).append(familleDivorce);
		sb.append( ", familleAddresse: \"" ).append(familleAddresse).append( "\"" );
		sb.append( ", familleCommentVousConnaissezEcole: \"" ).append(familleCommentVousConnaissezEcole).append( "\"" );
		sb.append( ", inscriptionConsiderationsSpeciales: \"" ).append(inscriptionConsiderationsSpeciales).append( "\"" );
		sb.append( ", inscriptionNomGroupe: \"" ).append(inscriptionNomGroupe).append( "\"" );
		sb.append( ", inscriptionPaimentChaqueMois: " ).append(inscriptionPaimentChaqueMois);
		sb.append( ", inscriptionPaimentComplet: " ).append(inscriptionPaimentComplet);
		sb.append(" }");
		return sb.toString();
	}
}
