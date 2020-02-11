package org.computate.scolaire.frFR.inscription;

import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.Long;
import java.util.Locale;
import java.time.LocalTime;
import org.computate.scolaire.frFR.saison.SaisonScolaire;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
import org.computate.scolaire.frFR.enfant.EnfantScolaire;
import org.computate.scolaire.frFR.mere.MereScolaire;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import java.util.Set;
import org.computate.scolaire.frFR.gardien.GardienScolaire;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.List;
import java.time.LocalDate;
import org.apache.solr.client.solrj.SolrQuery;
import java.util.Optional;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.frFR.bloc.BlocScolaire;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import org.computate.scolaire.frFR.couverture.Couverture;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import java.lang.String;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.SolrClient;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.time.temporal.ChronoUnit;
import org.computate.scolaire.frFR.pere.PereScolaire;
import java.time.format.DateTimeFormatter;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe enrollmentCompleteName dans Solr</a>
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
	@JsonSerialize(using = ToStringSerializer.class)
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

	//////////////
	// anneeCle //
	//////////////

	/**	L'entité « anneeCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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
		return "année";
	}

	public String htmTooltipAnneeCle() {
		return null;
	}

	public String htmAnneeCle() {
		return anneeCle == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCle());
	}

	////////////////////
	// anneeRecherche //
	////////////////////

	/**	L'entité « anneeRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<AnneeScolaire>(). 
	 */
	@JsonIgnore
	protected ListeRecherche<AnneeScolaire> anneeRecherche = new ListeRecherche<AnneeScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<AnneeScolaire>> anneeRechercheCouverture = new Couverture<ListeRecherche<AnneeScolaire>>().p(this).c(ListeRecherche.class).var("anneeRecherche").o(anneeRecherche);

	/**	<br/>L'entité « anneeRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<AnneeScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeRecherche">Trouver l'entité anneeRecherche dans Solr</a>
	 * <br/>
	 * @param anneeRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _anneeRecherche(ListeRecherche<AnneeScolaire> l);

	public ListeRecherche<AnneeScolaire> getAnneeRecherche() {
		return anneeRecherche;
	}

	public void setAnneeRecherche(ListeRecherche<AnneeScolaire> anneeRecherche) {
		this.anneeRecherche = anneeRecherche;
		this.anneeRechercheCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire anneeRechercheInit() {
		if(!anneeRechercheCouverture.dejaInitialise) {
			_anneeRecherche(anneeRecherche);
		}
		anneeRecherche.initLoinPourClasse(requeteSite_);
		anneeRechercheCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	////////////
	// annee_ //
	////////////

	/**	L'entité « annee_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected AnneeScolaire annee_;
	@JsonIgnore
	public Couverture<AnneeScolaire> annee_Couverture = new Couverture<AnneeScolaire>().p(this).c(AnneeScolaire.class).var("annee_").o(annee_);

	/**	<br/>L'entité « annee_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:annee_">Trouver l'entité annee_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _annee_(Couverture<AnneeScolaire> c);

	public AnneeScolaire getAnnee_() {
		return annee_;
	}

	public void setAnnee_(AnneeScolaire annee_) {
		this.annee_ = annee_;
		this.annee_Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire annee_Init() {
		if(!annee_Couverture.dejaInitialise) {
			_annee_(annee_Couverture);
			if(annee_ == null)
				setAnnee_(annee_Couverture.o);
		}
		annee_Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
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

	public void inputBlocCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "blocs")
				.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
				.a("class", "valeur suggereBlocCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setBlocCles")
				.a("id", classeApiMethodeMethode, "_blocCles")
				.a("autocomplete", "off")
				.a("oninput", "suggereInscriptionScolaireBlocCles($(this).val() ? rechercherBlocScolaireFiltres($('#suggere", classeApiMethodeMethode, "InscriptionScolaireBlocCles')) : [{'name':'fq','value':'inscriptionCles:", pk, "'}], $('#listInscriptionScolaireBlocCles_", classeApiMethodeMethode, "'), ", pk, "); ")
			.fg();

	}

	public void htmBlocCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireBlocCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-indigo w3-hover-indigo ").f();
								e("i").a("class", "far fa-bell ").f().g("i");
								sx("blocs");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputBlocCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireBlocCles_", classeApiMethodeMethode).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
										.a("onclick", "postBlocScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() { patchInscriptionScolaireVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "blocCles')); });")
										.f().sx("ajouter un bloc")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	////////////
	// blocs_ //
	////////////

	/**	L'entité « blocs_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected List<BlocScolaire> blocs_;
	@JsonIgnore
	public Couverture<List<BlocScolaire>> blocs_Couverture = new Couverture<List<BlocScolaire>>().p(this).c(List.class).var("blocs_").o(blocs_);

	/**	<br/>L'entité « blocs_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocs_">Trouver l'entité blocs_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocs_(Couverture<List<BlocScolaire>> c);

	public List<BlocScolaire> getBlocs_() {
		return blocs_;
	}

	public void setBlocs_(List<BlocScolaire> blocs_) {
		this.blocs_ = blocs_;
		this.blocs_Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire addBlocs_(BlocScolaire...objets) {
		for(BlocScolaire o : objets) {
			addBlocs_(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addBlocs_(BlocScolaire o) {
		if(o != null && !blocs_.contains(o))
			this.blocs_.add(o);
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocs_Init() {
		if(!blocs_Couverture.dejaInitialise) {
			_blocs_(blocs_Couverture);
			if(blocs_ == null)
				setBlocs_(blocs_Couverture.o);
		}
		blocs_Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	//////////////
	// saisons_ //
	//////////////

	/**	L'entité « saisons_ »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SaisonScolaire>(). 
	 */
	@JsonIgnore
	protected List<SaisonScolaire> saisons_ = new java.util.ArrayList<org.computate.scolaire.frFR.saison.SaisonScolaire>();
	@JsonIgnore
	public Couverture<List<SaisonScolaire>> saisons_Couverture = new Couverture<List<SaisonScolaire>>().p(this).c(List.class).var("saisons_").o(saisons_);

	/**	<br/>L'entité « saisons_ »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<SaisonScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisons_">Trouver l'entité saisons_ dans Solr</a>
	 * <br/>
	 * @param saisons_ est l'entité déjà construit. 
	 **/
	protected abstract void _saisons_(List<SaisonScolaire> c);

	public List<SaisonScolaire> getSaisons_() {
		return saisons_;
	}

	public void setSaisons_(List<SaisonScolaire> saisons_) {
		this.saisons_ = saisons_;
		this.saisons_Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire addSaisons_(SaisonScolaire...objets) {
		for(SaisonScolaire o : objets) {
			addSaisons_(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addSaisons_(SaisonScolaire o) {
		if(o != null && !saisons_.contains(o))
			this.saisons_.add(o);
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire saisons_Init() {
		if(!saisons_Couverture.dejaInitialise) {
			_saisons_(saisons_);
		}
		saisons_Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	///////////
	// bloc_ //
	///////////

	/**	L'entité « bloc_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected BlocScolaire bloc_;
	@JsonIgnore
	public Couverture<BlocScolaire> bloc_Couverture = new Couverture<BlocScolaire>().p(this).c(BlocScolaire.class).var("bloc_").o(bloc_);

	/**	<br/>L'entité « bloc_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:bloc_">Trouver l'entité bloc_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _bloc_(Couverture<BlocScolaire> c);

	public BlocScolaire getBloc_() {
		return bloc_;
	}

	public void setBloc_(BlocScolaire bloc_) {
		this.bloc_ = bloc_;
		this.bloc_Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire bloc_Init() {
		if(!bloc_Couverture.dejaInitialise) {
			_bloc_(bloc_Couverture);
			if(bloc_ == null)
				setBloc_(bloc_Couverture.o);
		}
		bloc_Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	//////////////
	// ecoleCle //
	//////////////

	/**	L'entité « ecoleCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	///////////////
	// saisonCle //
	///////////////

	/**	L'entité « saisonCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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
		return "année";
	}

	public String htmTooltipSaisonCle() {
		return null;
	}

	public String htmSaisonCle() {
		return saisonCle == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonCle());
	}

	////////////////
	// sessionCle //
	////////////////

	/**	L'entité « sessionCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	////////////
	// ageCle //
	////////////

	/**	L'entité « ageCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	/////////////
	// blocCle //
	/////////////

	/**	L'entité « blocCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	///////////////
	// enfantCle //
	///////////////

	/**	L'entité « enfantCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	public void inputEnfantCle(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "enfants")
				.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
				.a("class", "valeur suggereEnfantCle w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setEnfantCle")
				.a("id", classeApiMethodeMethode, "_enfantCle")
				.a("autocomplete", "off")
				.a("oninput", "suggereInscriptionScolaireEnfantCle($(this).val() ? rechercherEnfantScolaireFiltres($('#suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantCle')) : [{'name':'fq','value':'inscriptionCles:", pk, "'}], $('#listInscriptionScolaireEnfantCle_", classeApiMethodeMethode, "'), ", pk, "); ")
			.fg();

	}

	public void htmEnfantCle(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "far fa-child ").f().g("i");
								sx("enfants");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier un enfant a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputEnfantCle(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireEnfantCle_", classeApiMethodeMethode).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
										.a("onclick", "postEnfantScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() { patchInscriptionScolaireVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "enfantCle')); });")
										.f().sx("ajouter un enfant")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputMereCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "mères")
				.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
				.a("class", "valeur suggereMereCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setMereCles")
				.a("id", classeApiMethodeMethode, "_mereCles")
				.a("autocomplete", "off")
				.a("oninput", "suggereInscriptionScolaireMereCles($(this).val() ? rechercherMereScolaireFiltres($('#suggere", classeApiMethodeMethode, "InscriptionScolaireMereCles')) : [{'name':'fq','value':'inscriptionCles:", pk, "'}], $('#listInscriptionScolaireMereCles_", classeApiMethodeMethode, "'), ", pk, "); ")
			.fg();

	}

	public void htmMereCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireMereCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-pink w3-hover-pink ").f();
								e("i").a("class", "far fa-female ").f().g("i");
								sx("mères");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputMereCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireMereCles_", classeApiMethodeMethode).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
										.a("onclick", "postMereScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() { patchInscriptionScolaireVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "mereCles')); });")
										.f().sx("ajouter une mère")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputPereCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "pères")
				.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
				.a("class", "valeur suggerePereCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setPereCles")
				.a("id", classeApiMethodeMethode, "_pereCles")
				.a("autocomplete", "off")
				.a("oninput", "suggereInscriptionScolairePereCles($(this).val() ? rechercherPereScolaireFiltres($('#suggere", classeApiMethodeMethode, "InscriptionScolairePereCles')) : [{'name':'fq','value':'inscriptionCles:", pk, "'}], $('#listInscriptionScolairePereCles_", classeApiMethodeMethode, "'), ", pk, "); ")
			.fg();

	}

	public void htmPereCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolairePereCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-light-blue w3-hover-light-blue ").f();
								e("i").a("class", "far fa-male ").f().g("i");
								sx("pères");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputPereCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolairePereCles_", classeApiMethodeMethode).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
										.a("onclick", "postPereScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() { patchInscriptionScolaireVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "pereCles')); });")
										.f().sx("ajouter un père")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputGardienCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "gardiens")
				.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
				.a("class", "valeur suggereGardienCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setGardienCles")
				.a("id", classeApiMethodeMethode, "_gardienCles")
				.a("autocomplete", "off")
				.a("oninput", "suggereInscriptionScolaireGardienCles($(this).val() ? rechercherGardienScolaireFiltres($('#suggere", classeApiMethodeMethode, "InscriptionScolaireGardienCles')) : [{'name':'fq','value':'inscriptionCles:", pk, "'}], $('#listInscriptionScolaireGardienCles_", classeApiMethodeMethode, "'), ", pk, "); ")
			.fg();

	}

	public void htmGardienCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireGardienCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
								e("i").a("class", "far fa-phone ").f().g("i");
								sx("gardiens");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputGardienCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireGardienCles_", classeApiMethodeMethode).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postGardienScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() { patchInscriptionScolaireVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "gardienCles')); });")
										.f().sx("ajouter un gardien")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputPaiementCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			e("input")
				.a("type", "text")
				.a("placeholder", "paiements")
				.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
				.a("class", "valeur suggerePaiementCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setPaiementCles")
				.a("id", classeApiMethodeMethode, "_paiementCles")
				.a("autocomplete", "off")
				.a("oninput", "suggereInscriptionScolairePaiementCles($(this).val() ? rechercherPaiementScolaireFiltres($('#suggere", classeApiMethodeMethode, "InscriptionScolairePaiementCles')) : [{'name':'fq','value':'inscriptionCles:", pk, "'}], $('#listInscriptionScolairePaiementCles_", classeApiMethodeMethode, "'), ", pk, "); ")
			.fg();

	}

	public void htmPaiementCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolairePaiementCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "fas fa-search-dollar ").f().g("i");
								sx("paiements");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputPaiementCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolairePaiementCles_", classeApiMethodeMethode).f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
										.a("onclick", "postPaiementScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() { patchInscriptionScolaireVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "paiementCles')); });")
										.f().sx("ajouter un paiement")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////
	// formInscriptionCle //
	////////////////////////

	/**	L'entité « formInscriptionCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Long formInscriptionCle;
	@JsonIgnore
	public Couverture<Long> formInscriptionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("formInscriptionCle").o(formInscriptionCle);

	/**	<br/>L'entité « formInscriptionCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:formInscriptionCle">Trouver l'entité formInscriptionCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _formInscriptionCle(Couverture<Long> c);

	public Long getFormInscriptionCle() {
		return formInscriptionCle;
	}

	public void setFormInscriptionCle(Long formInscriptionCle) {
		this.formInscriptionCle = formInscriptionCle;
		this.formInscriptionCleCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setFormInscriptionCle(String o) {
		if(NumberUtils.isParsable(o))
			this.formInscriptionCle = Long.parseLong(o);
		this.formInscriptionCleCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire formInscriptionCleInit() {
		if(!formInscriptionCleCouverture.dejaInitialise) {
			_formInscriptionCle(formInscriptionCleCouverture);
			if(formInscriptionCle == null)
				setFormInscriptionCle(formInscriptionCleCouverture.o);
		}
		formInscriptionCleCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Long solrFormInscriptionCle() {
		return formInscriptionCle;
	}

	public String strFormInscriptionCle() {
		return formInscriptionCle == null ? "" : formInscriptionCle.toString();
	}

	public String jsonFormInscriptionCle() {
		return formInscriptionCle == null ? "" : formInscriptionCle.toString();
	}

	public String nomAffichageFormInscriptionCle() {
		return "formulaire d'inscription";
	}

	public String htmTooltipFormInscriptionCle() {
		return null;
	}

	public String htmFormInscriptionCle() {
		return formInscriptionCle == null ? "" : StringEscapeUtils.escapeHtml4(strFormInscriptionCle());
	}

	/////////////////
	// scolaireTri //
	/////////////////

	/**	L'entité « scolaireTri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	//////////////
	// ecoleTri //
	//////////////

	/**	L'entité « ecoleTri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	//////////////
	// anneeTri //
	//////////////

	/**	L'entité « anneeTri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	///////////////
	// saisonTri //
	///////////////

	/**	L'entité « saisonTri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	////////////////
	// sessionTri //
	////////////////

	/**	L'entité « sessionTri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	////////////
	// ageTri //
	////////////

	/**	L'entité « ageTri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	///////////////////
	// mereRecherche //
	///////////////////

	/**	L'entité « mereRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<MereScolaire>(). 
	 */
	@JsonIgnore
	protected ListeRecherche<MereScolaire> mereRecherche = new ListeRecherche<MereScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<MereScolaire>> mereRechercheCouverture = new Couverture<ListeRecherche<MereScolaire>>().p(this).c(ListeRecherche.class).var("mereRecherche").o(mereRecherche);

	/**	<br/>L'entité « mereRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<MereScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereRecherche">Trouver l'entité mereRecherche dans Solr</a>
	 * <br/>
	 * @param mereRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _mereRecherche(ListeRecherche<MereScolaire> l);

	public ListeRecherche<MereScolaire> getMereRecherche() {
		return mereRecherche;
	}

	public void setMereRecherche(ListeRecherche<MereScolaire> mereRecherche) {
		this.mereRecherche = mereRecherche;
		this.mereRechercheCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire mereRechercheInit() {
		if(!mereRechercheCouverture.dejaInitialise) {
			_mereRecherche(mereRecherche);
		}
		mereRecherche.initLoinPourClasse(requeteSite_);
		mereRechercheCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	///////////
	// meres //
	///////////

	/**	L'entité « meres »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected List<MereScolaire> meres;
	@JsonIgnore
	public Couverture<List<MereScolaire>> meresCouverture = new Couverture<List<MereScolaire>>().p(this).c(List.class).var("meres").o(meres);

	/**	<br/>L'entité « meres »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:meres">Trouver l'entité meres dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _meres(Couverture<List<MereScolaire>> c);

	public List<MereScolaire> getMeres() {
		return meres;
	}

	public void setMeres(List<MereScolaire> meres) {
		this.meres = meres;
		this.meresCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addMeres(MereScolaire...objets) {
		for(MereScolaire o : objets) {
			addMeres(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addMeres(MereScolaire o) {
		if(o != null && !meres.contains(o))
			this.meres.add(o);
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire meresInit() {
		if(!meresCouverture.dejaInitialise) {
			_meres(meresCouverture);
			if(meres == null)
				setMeres(meresCouverture.o);
		}
		meresCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	///////////////////
	// pereRecherche //
	///////////////////

	/**	L'entité « pereRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<PereScolaire>(). 
	 */
	@JsonIgnore
	protected ListeRecherche<PereScolaire> pereRecherche = new ListeRecherche<PereScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<PereScolaire>> pereRechercheCouverture = new Couverture<ListeRecherche<PereScolaire>>().p(this).c(ListeRecherche.class).var("pereRecherche").o(pereRecherche);

	/**	<br/>L'entité « pereRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<PereScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereRecherche">Trouver l'entité pereRecherche dans Solr</a>
	 * <br/>
	 * @param pereRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _pereRecherche(ListeRecherche<PereScolaire> l);

	public ListeRecherche<PereScolaire> getPereRecherche() {
		return pereRecherche;
	}

	public void setPereRecherche(ListeRecherche<PereScolaire> pereRecherche) {
		this.pereRecherche = pereRecherche;
		this.pereRechercheCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire pereRechercheInit() {
		if(!pereRechercheCouverture.dejaInitialise) {
			_pereRecherche(pereRecherche);
		}
		pereRecherche.initLoinPourClasse(requeteSite_);
		pereRechercheCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	///////////
	// peres //
	///////////

	/**	L'entité « peres »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected List<PereScolaire> peres;
	@JsonIgnore
	public Couverture<List<PereScolaire>> peresCouverture = new Couverture<List<PereScolaire>>().p(this).c(List.class).var("peres").o(peres);

	/**	<br/>L'entité « peres »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:peres">Trouver l'entité peres dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _peres(Couverture<List<PereScolaire>> c);

	public List<PereScolaire> getPeres() {
		return peres;
	}

	public void setPeres(List<PereScolaire> peres) {
		this.peres = peres;
		this.peresCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addPeres(PereScolaire...objets) {
		for(PereScolaire o : objets) {
			addPeres(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addPeres(PereScolaire o) {
		if(o != null && !peres.contains(o))
			this.peres.add(o);
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire peresInit() {
		if(!peresCouverture.dejaInitialise) {
			_peres(peresCouverture);
			if(peres == null)
				setPeres(peresCouverture.o);
		}
		peresCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	//////////////////////
	// gardienRecherche //
	//////////////////////

	/**	L'entité « gardienRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<GardienScolaire>(). 
	 */
	@JsonIgnore
	protected ListeRecherche<GardienScolaire> gardienRecherche = new ListeRecherche<GardienScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<GardienScolaire>> gardienRechercheCouverture = new Couverture<ListeRecherche<GardienScolaire>>().p(this).c(ListeRecherche.class).var("gardienRecherche").o(gardienRecherche);

	/**	<br/>L'entité « gardienRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<GardienScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienRecherche">Trouver l'entité gardienRecherche dans Solr</a>
	 * <br/>
	 * @param gardienRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _gardienRecherche(ListeRecherche<GardienScolaire> l);

	public ListeRecherche<GardienScolaire> getGardienRecherche() {
		return gardienRecherche;
	}

	public void setGardienRecherche(ListeRecherche<GardienScolaire> gardienRecherche) {
		this.gardienRecherche = gardienRecherche;
		this.gardienRechercheCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire gardienRechercheInit() {
		if(!gardienRechercheCouverture.dejaInitialise) {
			_gardienRecherche(gardienRecherche);
		}
		gardienRecherche.initLoinPourClasse(requeteSite_);
		gardienRechercheCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	//////////////
	// gardiens //
	//////////////

	/**	L'entité « gardiens »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	protected List<GardienScolaire> gardiens;
	@JsonIgnore
	public Couverture<List<GardienScolaire>> gardiensCouverture = new Couverture<List<GardienScolaire>>().p(this).c(List.class).var("gardiens").o(gardiens);

	/**	<br/>L'entité « gardiens »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardiens">Trouver l'entité gardiens dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _gardiens(Couverture<List<GardienScolaire>> c);

	public List<GardienScolaire> getGardiens() {
		return gardiens;
	}

	public void setGardiens(List<GardienScolaire> gardiens) {
		this.gardiens = gardiens;
		this.gardiensCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addGardiens(GardienScolaire...objets) {
		for(GardienScolaire o : objets) {
			addGardiens(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addGardiens(GardienScolaire o) {
		if(o != null && !gardiens.contains(o))
			this.gardiens.add(o);
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire gardiensInit() {
		if(!gardiensCouverture.dejaInitialise) {
			_gardiens(gardiensCouverture);
			if(gardiens == null)
				setGardiens(gardiensCouverture.o);
		}
		gardiensCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	//////////////////
	// enfantPrenom //
	//////////////////

	/**	L'entité « enfantPrenom »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantPrenom;
	@JsonIgnore
	public Couverture<String> enfantPrenomCouverture = new Couverture<String>().p(this).c(String.class).var("enfantPrenom").o(enfantPrenom);

	/**	<br/>L'entité « enfantPrenom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantPrenom">Trouver l'entité enfantPrenom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantPrenom(Couverture<String> c);

	public String getEnfantPrenom() {
		return enfantPrenom;
	}

	public void setEnfantPrenom(String enfantPrenom) {
		this.enfantPrenom = enfantPrenom;
		this.enfantPrenomCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire enfantPrenomInit() {
		if(!enfantPrenomCouverture.dejaInitialise) {
			_enfantPrenom(enfantPrenomCouverture);
			if(enfantPrenom == null)
				setEnfantPrenom(enfantPrenomCouverture.o);
		}
		enfantPrenomCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEnfantPrenom() {
		return enfantPrenom;
	}

	public String strEnfantPrenom() {
		return enfantPrenom == null ? "" : enfantPrenom;
	}

	public String jsonEnfantPrenom() {
		return enfantPrenom == null ? "" : enfantPrenom;
	}

	public String nomAffichageEnfantPrenom() {
		return null;
	}

	public String htmTooltipEnfantPrenom() {
		return null;
	}

	public String htmEnfantPrenom() {
		return enfantPrenom == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantPrenom());
	}

	/////////////////////////
	// enfantPrenomPrefere //
	/////////////////////////

	/**	L'entité « enfantPrenomPrefere »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantPrenomPrefere;
	@JsonIgnore
	public Couverture<String> enfantPrenomPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("enfantPrenomPrefere").o(enfantPrenomPrefere);

	/**	<br/>L'entité « enfantPrenomPrefere »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantPrenomPrefere">Trouver l'entité enfantPrenomPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantPrenomPrefere(Couverture<String> c);

	public String getEnfantPrenomPrefere() {
		return enfantPrenomPrefere;
	}

	public void setEnfantPrenomPrefere(String enfantPrenomPrefere) {
		this.enfantPrenomPrefere = enfantPrenomPrefere;
		this.enfantPrenomPrefereCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire enfantPrenomPrefereInit() {
		if(!enfantPrenomPrefereCouverture.dejaInitialise) {
			_enfantPrenomPrefere(enfantPrenomPrefereCouverture);
			if(enfantPrenomPrefere == null)
				setEnfantPrenomPrefere(enfantPrenomPrefereCouverture.o);
		}
		enfantPrenomPrefereCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEnfantPrenomPrefere() {
		return enfantPrenomPrefere;
	}

	public String strEnfantPrenomPrefere() {
		return enfantPrenomPrefere == null ? "" : enfantPrenomPrefere;
	}

	public String jsonEnfantPrenomPrefere() {
		return enfantPrenomPrefere == null ? "" : enfantPrenomPrefere;
	}

	public String nomAffichageEnfantPrenomPrefere() {
		return null;
	}

	public String htmTooltipEnfantPrenomPrefere() {
		return null;
	}

	public String htmEnfantPrenomPrefere() {
		return enfantPrenomPrefere == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantPrenomPrefere());
	}

	//////////////////////
	// enfantFamilleNom //
	//////////////////////

	/**	L'entité « enfantFamilleNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantFamilleNom;
	@JsonIgnore
	public Couverture<String> enfantFamilleNomCouverture = new Couverture<String>().p(this).c(String.class).var("enfantFamilleNom").o(enfantFamilleNom);

	/**	<br/>L'entité « enfantFamilleNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantFamilleNom">Trouver l'entité enfantFamilleNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantFamilleNom(Couverture<String> c);

	public String getEnfantFamilleNom() {
		return enfantFamilleNom;
	}

	public void setEnfantFamilleNom(String enfantFamilleNom) {
		this.enfantFamilleNom = enfantFamilleNom;
		this.enfantFamilleNomCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire enfantFamilleNomInit() {
		if(!enfantFamilleNomCouverture.dejaInitialise) {
			_enfantFamilleNom(enfantFamilleNomCouverture);
			if(enfantFamilleNom == null)
				setEnfantFamilleNom(enfantFamilleNomCouverture.o);
		}
		enfantFamilleNomCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEnfantFamilleNom() {
		return enfantFamilleNom;
	}

	public String strEnfantFamilleNom() {
		return enfantFamilleNom == null ? "" : enfantFamilleNom;
	}

	public String jsonEnfantFamilleNom() {
		return enfantFamilleNom == null ? "" : enfantFamilleNom;
	}

	public String nomAffichageEnfantFamilleNom() {
		return null;
	}

	public String htmTooltipEnfantFamilleNom() {
		return null;
	}

	public String htmEnfantFamilleNom() {
		return enfantFamilleNom == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantFamilleNom());
	}

	////////////////
	// merePrenom //
	////////////////

	/**	L'entité « merePrenom »
	 *	 is defined as null before being initialized. 
	 */
	protected String merePrenom;
	@JsonIgnore
	public Couverture<String> merePrenomCouverture = new Couverture<String>().p(this).c(String.class).var("merePrenom").o(merePrenom);

	/**	<br/>L'entité « merePrenom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:merePrenom">Trouver l'entité merePrenom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _merePrenom(Couverture<String> c);

	public String getMerePrenom() {
		return merePrenom;
	}

	public void setMerePrenom(String merePrenom) {
		this.merePrenom = merePrenom;
		this.merePrenomCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire merePrenomInit() {
		if(!merePrenomCouverture.dejaInitialise) {
			_merePrenom(merePrenomCouverture);
			if(merePrenom == null)
				setMerePrenom(merePrenomCouverture.o);
		}
		merePrenomCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrMerePrenom() {
		return merePrenom;
	}

	public String strMerePrenom() {
		return merePrenom == null ? "" : merePrenom;
	}

	public String jsonMerePrenom() {
		return merePrenom == null ? "" : merePrenom;
	}

	public String nomAffichageMerePrenom() {
		return null;
	}

	public String htmTooltipMerePrenom() {
		return null;
	}

	public String htmMerePrenom() {
		return merePrenom == null ? "" : StringEscapeUtils.escapeHtml4(strMerePrenom());
	}

	///////////////////////
	// merePrenomPrefere //
	///////////////////////

	/**	L'entité « merePrenomPrefere »
	 *	 is defined as null before being initialized. 
	 */
	protected String merePrenomPrefere;
	@JsonIgnore
	public Couverture<String> merePrenomPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("merePrenomPrefere").o(merePrenomPrefere);

	/**	<br/>L'entité « merePrenomPrefere »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:merePrenomPrefere">Trouver l'entité merePrenomPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _merePrenomPrefere(Couverture<String> c);

	public String getMerePrenomPrefere() {
		return merePrenomPrefere;
	}

	public void setMerePrenomPrefere(String merePrenomPrefere) {
		this.merePrenomPrefere = merePrenomPrefere;
		this.merePrenomPrefereCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire merePrenomPrefereInit() {
		if(!merePrenomPrefereCouverture.dejaInitialise) {
			_merePrenomPrefere(merePrenomPrefereCouverture);
			if(merePrenomPrefere == null)
				setMerePrenomPrefere(merePrenomPrefereCouverture.o);
		}
		merePrenomPrefereCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrMerePrenomPrefere() {
		return merePrenomPrefere;
	}

	public String strMerePrenomPrefere() {
		return merePrenomPrefere == null ? "" : merePrenomPrefere;
	}

	public String jsonMerePrenomPrefere() {
		return merePrenomPrefere == null ? "" : merePrenomPrefere;
	}

	public String nomAffichageMerePrenomPrefere() {
		return null;
	}

	public String htmTooltipMerePrenomPrefere() {
		return null;
	}

	public String htmMerePrenomPrefere() {
		return merePrenomPrefere == null ? "" : StringEscapeUtils.escapeHtml4(strMerePrenomPrefere());
	}

	////////////////
	// perePrenom //
	////////////////

	/**	L'entité « perePrenom »
	 *	 is defined as null before being initialized. 
	 */
	protected String perePrenom;
	@JsonIgnore
	public Couverture<String> perePrenomCouverture = new Couverture<String>().p(this).c(String.class).var("perePrenom").o(perePrenom);

	/**	<br/>L'entité « perePrenom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:perePrenom">Trouver l'entité perePrenom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _perePrenom(Couverture<String> c);

	public String getPerePrenom() {
		return perePrenom;
	}

	public void setPerePrenom(String perePrenom) {
		this.perePrenom = perePrenom;
		this.perePrenomCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire perePrenomInit() {
		if(!perePrenomCouverture.dejaInitialise) {
			_perePrenom(perePrenomCouverture);
			if(perePrenom == null)
				setPerePrenom(perePrenomCouverture.o);
		}
		perePrenomCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrPerePrenom() {
		return perePrenom;
	}

	public String strPerePrenom() {
		return perePrenom == null ? "" : perePrenom;
	}

	public String jsonPerePrenom() {
		return perePrenom == null ? "" : perePrenom;
	}

	public String nomAffichagePerePrenom() {
		return null;
	}

	public String htmTooltipPerePrenom() {
		return null;
	}

	public String htmPerePrenom() {
		return perePrenom == null ? "" : StringEscapeUtils.escapeHtml4(strPerePrenom());
	}

	///////////////////////
	// perePrenomPrefere //
	///////////////////////

	/**	L'entité « perePrenomPrefere »
	 *	 is defined as null before being initialized. 
	 */
	protected String perePrenomPrefere;
	@JsonIgnore
	public Couverture<String> perePrenomPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("perePrenomPrefere").o(perePrenomPrefere);

	/**	<br/>L'entité « perePrenomPrefere »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:perePrenomPrefere">Trouver l'entité perePrenomPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _perePrenomPrefere(Couverture<String> c);

	public String getPerePrenomPrefere() {
		return perePrenomPrefere;
	}

	public void setPerePrenomPrefere(String perePrenomPrefere) {
		this.perePrenomPrefere = perePrenomPrefere;
		this.perePrenomPrefereCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire perePrenomPrefereInit() {
		if(!perePrenomPrefereCouverture.dejaInitialise) {
			_perePrenomPrefere(perePrenomPrefereCouverture);
			if(perePrenomPrefere == null)
				setPerePrenomPrefere(perePrenomPrefereCouverture.o);
		}
		perePrenomPrefereCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrPerePrenomPrefere() {
		return perePrenomPrefere;
	}

	public String strPerePrenomPrefere() {
		return perePrenomPrefere == null ? "" : perePrenomPrefere;
	}

	public String jsonPerePrenomPrefere() {
		return perePrenomPrefere == null ? "" : perePrenomPrefere;
	}

	public String nomAffichagePerePrenomPrefere() {
		return null;
	}

	public String htmTooltipPerePrenomPrefere() {
		return null;
	}

	public String htmPerePrenomPrefere() {
		return perePrenomPrefere == null ? "" : StringEscapeUtils.escapeHtml4(strPerePrenomPrefere());
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

	public void inputEnfantNomComplet(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "NomAffichage.enUS: ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_enfantNomComplet");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setEnfantNomComplet inputInscriptionScolaire", pk, "EnfantNomComplet w3-input w3-border ");
				a("name", "setEnfantNomComplet");
			} else {
				a("class", "valeurEnfantNomComplet w3-input w3-border inputInscriptionScolaire", pk, "EnfantNomComplet w3-input w3-border ");
				a("name", "enfantNomComplet");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantNomComplet', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantNomComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantNomComplet')); }); ");
			}
			a("value", strEnfantNomComplet())
		.fg();

	}

	public void htmEnfantNomComplet(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantNomComplet").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantNomComplet").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantNomComplet(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantNomComplet')); $('#", classeApiMethodeMethode, "_enfantNomComplet').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantNomComplet', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantNomComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantNomComplet')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////////
	// enfantNomCompletPrefere //
	/////////////////////////////

	/**	L'entité « enfantNomCompletPrefere »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantNomCompletPrefere;
	@JsonIgnore
	public Couverture<String> enfantNomCompletPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("enfantNomCompletPrefere").o(enfantNomCompletPrefere);

	/**	<br/>L'entité « enfantNomCompletPrefere »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantNomCompletPrefere">Trouver l'entité enfantNomCompletPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantNomCompletPrefere(Couverture<String> c);

	public String getEnfantNomCompletPrefere() {
		return enfantNomCompletPrefere;
	}

	public void setEnfantNomCompletPrefere(String enfantNomCompletPrefere) {
		this.enfantNomCompletPrefere = enfantNomCompletPrefere;
		this.enfantNomCompletPrefereCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire enfantNomCompletPrefereInit() {
		if(!enfantNomCompletPrefereCouverture.dejaInitialise) {
			_enfantNomCompletPrefere(enfantNomCompletPrefereCouverture);
			if(enfantNomCompletPrefere == null)
				setEnfantNomCompletPrefere(enfantNomCompletPrefereCouverture.o);
		}
		enfantNomCompletPrefereCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEnfantNomCompletPrefere() {
		return enfantNomCompletPrefere;
	}

	public String strEnfantNomCompletPrefere() {
		return enfantNomCompletPrefere == null ? "" : enfantNomCompletPrefere;
	}

	public String jsonEnfantNomCompletPrefere() {
		return enfantNomCompletPrefere == null ? "" : enfantNomCompletPrefere;
	}

	public String nomAffichageEnfantNomCompletPrefere() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEnfantNomCompletPrefere() {
		return null;
	}

	public String htmEnfantNomCompletPrefere() {
		return enfantNomCompletPrefere == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantNomCompletPrefere());
	}

	public void inputEnfantNomCompletPrefere(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "NomAffichage.enUS: ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_enfantNomCompletPrefere");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setEnfantNomCompletPrefere inputInscriptionScolaire", pk, "EnfantNomCompletPrefere w3-input w3-border ");
				a("name", "setEnfantNomCompletPrefere");
			} else {
				a("class", "valeurEnfantNomCompletPrefere w3-input w3-border inputInscriptionScolaire", pk, "EnfantNomCompletPrefere w3-input w3-border ");
				a("name", "enfantNomCompletPrefere");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantNomCompletPrefere', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }); ");
			}
			a("value", strEnfantNomCompletPrefere())
		.fg();

	}

	public void htmEnfantNomCompletPrefere(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantNomCompletPrefere").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantNomCompletPrefere").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantNomCompletPrefere(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); $('#", classeApiMethodeMethode, "_enfantNomCompletPrefere').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantNomCompletPrefere', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////
	// enfantDateNaissance //
	/////////////////////////

	/**	L'entité « enfantDateNaissance »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate enfantDateNaissance;
	@JsonIgnore
	public Couverture<LocalDate> enfantDateNaissanceCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("enfantDateNaissance").o(enfantDateNaissance);

	/**	<br/>L'entité « enfantDateNaissance »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantDateNaissance">Trouver l'entité enfantDateNaissance dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantDateNaissance(Couverture<LocalDate> c);

	public LocalDate getEnfantDateNaissance() {
		return enfantDateNaissance;
	}

	public void setEnfantDateNaissance(LocalDate enfantDateNaissance) {
		this.enfantDateNaissance = enfantDateNaissance;
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setEnfantDateNaissance(Instant o) {
		this.enfantDateNaissance = LocalDate.from(o);
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setEnfantDateNaissance(String o) {
		this.enfantDateNaissance = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setEnfantDateNaissance(Date o) {
		this.enfantDateNaissance = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire enfantDateNaissanceInit() {
		if(!enfantDateNaissanceCouverture.dejaInitialise) {
			_enfantDateNaissance(enfantDateNaissanceCouverture);
			if(enfantDateNaissance == null)
				setEnfantDateNaissance(enfantDateNaissanceCouverture.o);
		}
		enfantDateNaissanceCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrEnfantDateNaissance() {
		return enfantDateNaissance == null ? null : Date.from(enfantDateNaissance.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strEnfantDateNaissance() {
		return enfantDateNaissance == null ? "" : enfantDateNaissance.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonEnfantDateNaissance() {
		return enfantDateNaissance == null ? "" : enfantDateNaissance.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageEnfantDateNaissance() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEnfantDateNaissance() {
		return null;
	}

	public String htmEnfantDateNaissance() {
		return enfantDateNaissance == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantDateNaissance());
	}

	public void inputEnfantDateNaissance(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setEnfantDateNaissance inputInscriptionScolaire", pk, "EnfantDateNaissance w3-input w3-border ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_enfantDateNaissance")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données.  (DD-MM-YYYY)")
			.a("value", enfantDateNaissance == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(enfantDateNaissance))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantDateNaissance', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }); } ")
			.fg();
	}

	public void htmEnfantDateNaissance(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantDateNaissance").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantDateNaissance").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnfantDateNaissance(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); $('#", classeApiMethodeMethode, "_enfantDateNaissance').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantDateNaissance', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////
	// ecoleNom //
	//////////////

	/**	L'entité « ecoleNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleNom;
	@JsonIgnore
	public Couverture<String> ecoleNomCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNom").o(ecoleNom);

	/**	<br/>L'entité « ecoleNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
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
	protected InscriptionScolaire ecoleNomInit() {
		if(!ecoleNomCouverture.dejaInitialise) {
			_ecoleNom(ecoleNomCouverture);
			if(ecoleNom == null)
				setEcoleNom(ecoleNomCouverture.o);
		}
		ecoleNomCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEcoleNom() {
		return ecoleNom;
	}

	public String strEcoleNom() {
		return ecoleNom == null ? "" : ecoleNom;
	}

	public String jsonEcoleNom() {
		return ecoleNom == null ? "" : ecoleNom;
	}

	public String nomAffichageEcoleNom() {
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipEcoleNom() {
		return null;
	}

	public String htmEcoleNom() {
		return ecoleNom == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNom());
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

	//////////////////////
	// ecoleEmplacement //
	//////////////////////

	/**	L'entité « ecoleEmplacement »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleEmplacement;
	@JsonIgnore
	public Couverture<String> ecoleEmplacementCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleEmplacement").o(ecoleEmplacement);

	/**	<br/>L'entité « ecoleEmplacement »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleEmplacement">Trouver l'entité ecoleEmplacement dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleEmplacement(Couverture<String> c);

	public String getEcoleEmplacement() {
		return ecoleEmplacement;
	}

	public void setEcoleEmplacement(String ecoleEmplacement) {
		this.ecoleEmplacement = ecoleEmplacement;
		this.ecoleEmplacementCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire ecoleEmplacementInit() {
		if(!ecoleEmplacementCouverture.dejaInitialise) {
			_ecoleEmplacement(ecoleEmplacementCouverture);
			if(ecoleEmplacement == null)
				setEcoleEmplacement(ecoleEmplacementCouverture.o);
		}
		ecoleEmplacementCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEcoleEmplacement() {
		return ecoleEmplacement;
	}

	public String strEcoleEmplacement() {
		return ecoleEmplacement == null ? "" : ecoleEmplacement;
	}

	public String jsonEcoleEmplacement() {
		return ecoleEmplacement == null ? "" : ecoleEmplacement;
	}

	public String nomAffichageEcoleEmplacement() {
		return "l'emplacement";
	}

	public String htmTooltipEcoleEmplacement() {
		return null;
	}

	public String htmEcoleEmplacement() {
		return ecoleEmplacement == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleEmplacement());
	}

	///////////////////
	// ecoleAddresse //
	///////////////////

	/**	L'entité « ecoleAddresse »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleAddresse;
	@JsonIgnore
	public Couverture<String> ecoleAddresseCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleAddresse").o(ecoleAddresse);

	/**	<br/>L'entité « ecoleAddresse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
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
	protected InscriptionScolaire ecoleAddresseInit() {
		if(!ecoleAddresseCouverture.dejaInitialise) {
			_ecoleAddresse(ecoleAddresseCouverture);
			if(ecoleAddresse == null)
				setEcoleAddresse(ecoleAddresseCouverture.o);
		}
		ecoleAddresseCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEcoleAddresse() {
		return ecoleAddresse;
	}

	public String strEcoleAddresse() {
		return ecoleAddresse == null ? "" : ecoleAddresse;
	}

	public String jsonEcoleAddresse() {
		return ecoleAddresse == null ? "" : ecoleAddresse;
	}

	public String nomAffichageEcoleAddresse() {
		return "addresse";
	}

	public String htmTooltipEcoleAddresse() {
		return null;
	}

	public String htmEcoleAddresse() {
		return ecoleAddresse == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleAddresse());
	}

	public void inputEcoleAddresse(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "addresse")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_ecoleAddresse");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setEcoleAddresse inputInscriptionScolaire", pk, "EcoleAddresse w3-input w3-border ");
				a("name", "setEcoleAddresse");
			} else {
				a("class", "valeurEcoleAddresse w3-input w3-border inputInscriptionScolaire", pk, "EcoleAddresse w3-input w3-border ");
				a("name", "ecoleAddresse");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleAddresse', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }); ");
			}
			a("value", strEcoleAddresse())
		.fg();

	}

	public void htmEcoleAddresse(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEcoleAddresse").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleAddresse").a("class", "").f().sx("addresse").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleAddresse(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); $('#", classeApiMethodeMethode, "_ecoleAddresse').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEcoleAddresse', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////////
	// ecoleNumeroTelephone //
	//////////////////////////

	/**	L'entité « ecoleNumeroTelephone »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleNumeroTelephone;
	@JsonIgnore
	public Couverture<String> ecoleNumeroTelephoneCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNumeroTelephone").o(ecoleNumeroTelephone);

	/**	<br/>L'entité « ecoleNumeroTelephone »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
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
	protected InscriptionScolaire ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneCouverture.dejaInitialise) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneCouverture);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneCouverture.o);
		}
		ecoleNumeroTelephoneCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}

	public String strEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : ecoleNumeroTelephone;
	}

	public String jsonEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : ecoleNumeroTelephone;
	}

	public String nomAffichageEcoleNumeroTelephone() {
		return "numéro de téléphone";
	}

	public String htmTooltipEcoleNumeroTelephone() {
		return null;
	}

	public String htmEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNumeroTelephone());
	}

	////////////////////////////
	// ecoleAdministrateurNom //
	////////////////////////////

	/**	L'entité « ecoleAdministrateurNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String ecoleAdministrateurNom;
	@JsonIgnore
	public Couverture<String> ecoleAdministrateurNomCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleAdministrateurNom").o(ecoleAdministrateurNom);

	/**	<br/>L'entité « ecoleAdministrateurNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAdministrateurNom">Trouver l'entité ecoleAdministrateurNom dans Solr</a>
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
	protected InscriptionScolaire ecoleAdministrateurNomInit() {
		if(!ecoleAdministrateurNomCouverture.dejaInitialise) {
			_ecoleAdministrateurNom(ecoleAdministrateurNomCouverture);
			if(ecoleAdministrateurNom == null)
				setEcoleAdministrateurNom(ecoleAdministrateurNomCouverture.o);
		}
		ecoleAdministrateurNomCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEcoleAdministrateurNom() {
		return ecoleAdministrateurNom;
	}

	public String strEcoleAdministrateurNom() {
		return ecoleAdministrateurNom == null ? "" : ecoleAdministrateurNom;
	}

	public String jsonEcoleAdministrateurNom() {
		return ecoleAdministrateurNom == null ? "" : ecoleAdministrateurNom;
	}

	public String nomAffichageEcoleAdministrateurNom() {
		return "administrateur de l'école";
	}

	public String htmTooltipEcoleAdministrateurNom() {
		return null;
	}

	public String htmEcoleAdministrateurNom() {
		return ecoleAdministrateurNom == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleAdministrateurNom());
	}

	////////////////
	// anneeDebut //
	////////////////

	/**	L'entité « anneeDebut »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	//////////////
	// anneeFin //
	//////////////

	/**	L'entité « anneeFin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	/////////////////////
	// saisonJourDebut //
	/////////////////////

	/**	L'entité « saisonJourDebut »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	///////////////////////////
	// anneeFraisInscription //
	///////////////////////////

	/**	L'entité « anneeFraisInscription »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected BigDecimal anneeFraisInscription;
	@JsonIgnore
	public Couverture<BigDecimal> anneeFraisInscriptionCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("anneeFraisInscription").o(anneeFraisInscription);

	/**	<br/>L'entité « anneeFraisInscription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFraisInscription">Trouver l'entité anneeFraisInscription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeFraisInscription(Couverture<BigDecimal> c);

	public BigDecimal getAnneeFraisInscription() {
		return anneeFraisInscription;
	}

	public void setAnneeFraisInscription(BigDecimal anneeFraisInscription) {
		this.anneeFraisInscription = anneeFraisInscription;
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setAnneeFraisInscription(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setAnneeFraisInscription(Double o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setAnneeFraisInscription(Integer o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire anneeFraisInscriptionInit() {
		if(!anneeFraisInscriptionCouverture.dejaInitialise) {
			_anneeFraisInscription(anneeFraisInscriptionCouverture);
			if(anneeFraisInscription == null)
				setAnneeFraisInscription(anneeFraisInscriptionCouverture.o);
		}
		anneeFraisInscriptionCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Double solrAnneeFraisInscription() {
		return anneeFraisInscription == null ? null : anneeFraisInscription.doubleValue();
	}

	public String strAnneeFraisInscription() {
		return anneeFraisInscription == null ? "" : anneeFraisInscription.setScale(2).toString();
	}

	public String jsonAnneeFraisInscription() {
		return anneeFraisInscription == null ? "" : anneeFraisInscription.toString();
	}

	public String nomAffichageAnneeFraisInscription() {
		return "le fin de l'année";
	}

	public String htmTooltipAnneeFraisInscription() {
		return null;
	}

	public String htmAnneeFraisInscription() {
		return anneeFraisInscription == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeFraisInscription());
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

	//////////////////////
	// sessionJourDebut //
	//////////////////////

	/**	L'entité « sessionJourDebut »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	////////////////////
	// sessionJourFin //
	////////////////////

	/**	L'entité « sessionJourFin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	//////////////
	// ageDebut //
	//////////////

	/**	L'entité « ageDebut »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	////////////
	// ageFin //
	////////////

	/**	L'entité « ageFin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	////////////////////
	// blocHeureDebut //
	////////////////////

	/**	L'entité « blocHeureDebut »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	//////////////////
	// blocHeureFin //
	//////////////////

	/**	L'entité « blocHeureFin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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

	/////////////////////
	// blocPrixParMois //
	/////////////////////

	/**	L'entité « blocPrixParMois »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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
		return blocPrixParMois == null ? "" : blocPrixParMois.setScale(2).toString();
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

	///////////////////
	// blocPrixTotal //
	///////////////////

	/**	L'entité « blocPrixTotal »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected BigDecimal blocPrixTotal;
	@JsonIgnore
	public Couverture<BigDecimal> blocPrixTotalCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("blocPrixTotal").o(blocPrixTotal);

	/**	<br/>L'entité « blocPrixTotal »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocPrixTotal">Trouver l'entité blocPrixTotal dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocPrixTotal(Couverture<BigDecimal> c);

	public BigDecimal getBlocPrixTotal() {
		return blocPrixTotal;
	}

	public void setBlocPrixTotal(BigDecimal blocPrixTotal) {
		this.blocPrixTotal = blocPrixTotal;
		this.blocPrixTotalCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setBlocPrixTotal(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blocPrixTotal = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixTotalCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setBlocPrixTotal(Double o) {
			this.blocPrixTotal = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixTotalCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setBlocPrixTotal(Integer o) {
			this.blocPrixTotal = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixTotalCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire blocPrixTotalInit() {
		if(!blocPrixTotalCouverture.dejaInitialise) {
			_blocPrixTotal(blocPrixTotalCouverture);
			if(blocPrixTotal == null)
				setBlocPrixTotal(blocPrixTotalCouverture.o);
		}
		blocPrixTotalCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Double solrBlocPrixTotal() {
		return blocPrixTotal == null ? null : blocPrixTotal.doubleValue();
	}

	public String strBlocPrixTotal() {
		return blocPrixTotal == null ? "" : blocPrixTotal.setScale(2).toString();
	}

	public String jsonBlocPrixTotal() {
		return blocPrixTotal == null ? "" : blocPrixTotal.toString();
	}

	public String nomAffichageBlocPrixTotal() {
		return "prix total";
	}

	public String htmTooltipBlocPrixTotal() {
		return null;
	}

	public String htmBlocPrixTotal() {
		return blocPrixTotal == null ? "" : StringEscapeUtils.escapeHtml4(strBlocPrixTotal());
	}

	//////////////////
	// blocNomAdmin //
	//////////////////

	/**	L'entité « blocNomAdmin »
	 *	 is defined as null before being initialized. 
	 */
	protected String blocNomAdmin;
	@JsonIgnore
	public Couverture<String> blocNomAdminCouverture = new Couverture<String>().p(this).c(String.class).var("blocNomAdmin").o(blocNomAdmin);

	/**	<br/>L'entité « blocNomAdmin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocNomAdmin">Trouver l'entité blocNomAdmin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocNomAdmin(Couverture<String> c);

	public String getBlocNomAdmin() {
		return blocNomAdmin;
	}

	public void setBlocNomAdmin(String blocNomAdmin) {
		this.blocNomAdmin = blocNomAdmin;
		this.blocNomAdminCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire blocNomAdminInit() {
		if(!blocNomAdminCouverture.dejaInitialise) {
			_blocNomAdmin(blocNomAdminCouverture);
			if(blocNomAdmin == null)
				setBlocNomAdmin(blocNomAdminCouverture.o);
		}
		blocNomAdminCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrBlocNomAdmin() {
		return blocNomAdmin;
	}

	public String strBlocNomAdmin() {
		return blocNomAdmin == null ? "" : blocNomAdmin;
	}

	public String jsonBlocNomAdmin() {
		return blocNomAdmin == null ? "" : blocNomAdmin;
	}

	public String nomAffichageBlocNomAdmin() {
		return null;
	}

	public String htmTooltipBlocNomAdmin() {
		return null;
	}

	public String htmBlocNomAdmin() {
		return blocNomAdmin == null ? "" : StringEscapeUtils.escapeHtml4(strBlocNomAdmin());
	}

	//////////////////
	// blocNomCourt //
	//////////////////

	/**	L'entité « blocNomCourt »
	 *	 is defined as null before being initialized. 
	 */
	protected String blocNomCourt;
	@JsonIgnore
	public Couverture<String> blocNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("blocNomCourt").o(blocNomCourt);

	/**	<br/>L'entité « blocNomCourt »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocNomCourt">Trouver l'entité blocNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocNomCourt(Couverture<String> c);

	public String getBlocNomCourt() {
		return blocNomCourt;
	}

	public void setBlocNomCourt(String blocNomCourt) {
		this.blocNomCourt = blocNomCourt;
		this.blocNomCourtCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire blocNomCourtInit() {
		if(!blocNomCourtCouverture.dejaInitialise) {
			_blocNomCourt(blocNomCourtCouverture);
			if(blocNomCourt == null)
				setBlocNomCourt(blocNomCourtCouverture.o);
		}
		blocNomCourtCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrBlocNomCourt() {
		return blocNomCourt;
	}

	public String strBlocNomCourt() {
		return blocNomCourt == null ? "" : blocNomCourt;
	}

	public String jsonBlocNomCourt() {
		return blocNomCourt == null ? "" : blocNomCourt;
	}

	public String nomAffichageBlocNomCourt() {
		return null;
	}

	public String htmTooltipBlocNomCourt() {
		return null;
	}

	public String htmBlocNomCourt() {
		return blocNomCourt == null ? "" : StringEscapeUtils.escapeHtml4(strBlocNomCourt());
	}

	////////////////////
	// blocNomComplet //
	////////////////////

	/**	L'entité « blocNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	protected String blocNomComplet;
	@JsonIgnore
	public Couverture<String> blocNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("blocNomComplet").o(blocNomComplet);

	/**	<br/>L'entité « blocNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocNomComplet">Trouver l'entité blocNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocNomComplet(Couverture<String> c);

	public String getBlocNomComplet() {
		return blocNomComplet;
	}

	public void setBlocNomComplet(String blocNomComplet) {
		this.blocNomComplet = blocNomComplet;
		this.blocNomCompletCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire blocNomCompletInit() {
		if(!blocNomCompletCouverture.dejaInitialise) {
			_blocNomComplet(blocNomCompletCouverture);
			if(blocNomComplet == null)
				setBlocNomComplet(blocNomCompletCouverture.o);
		}
		blocNomCompletCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrBlocNomComplet() {
		return blocNomComplet;
	}

	public String strBlocNomComplet() {
		return blocNomComplet == null ? "" : blocNomComplet;
	}

	public String jsonBlocNomComplet() {
		return blocNomComplet == null ? "" : blocNomComplet;
	}

	public String nomAffichageBlocNomComplet() {
		return null;
	}

	public String htmTooltipBlocNomComplet() {
		return null;
	}

	public String htmBlocNomComplet() {
		return blocNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strBlocNomComplet());
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

	public void inputInscriptionApprouve(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if("Page".equals(classeApiMethodeMethode)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classeApiMethodeMethode, "_inscriptionApprouve")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classeApiMethodeMethode, "_inscriptionApprouve");
		}
		if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
			a("class", "setInscriptionApprouve inputInscriptionScolaire", pk, "InscriptionApprouve w3-input w3-border ");
			a("name", "setInscriptionApprouve");
		} else {
			a("class", "valeurInscriptionApprouve inputInscriptionScolaire", pk, "InscriptionApprouve w3-input w3-border ");
			a("name", "inscriptionApprouve");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionApprouve', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionApprouve')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionApprouve')); }); ");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			if(getInscriptionApprouve() != null && getInscriptionApprouve())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmInscriptionApprouve(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionApprouve").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_inscriptionApprouve").a("class", "").f().sx("approuvé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionApprouve(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputInscriptionImmunisations(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if("Page".equals(classeApiMethodeMethode)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classeApiMethodeMethode, "_inscriptionImmunisations")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classeApiMethodeMethode, "_inscriptionImmunisations");
		}
		if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
			a("class", "setInscriptionImmunisations inputInscriptionScolaire", pk, "InscriptionImmunisations w3-input w3-border ");
			a("name", "setInscriptionImmunisations");
		} else {
			a("class", "valeurInscriptionImmunisations inputInscriptionScolaire", pk, "InscriptionImmunisations w3-input w3-border ");
			a("name", "inscriptionImmunisations");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionImmunisations', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionImmunisations')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionImmunisations')); }); ");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			if(getInscriptionImmunisations() != null && getInscriptionImmunisations())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmInscriptionImmunisations(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionImmunisations").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_inscriptionImmunisations").a("class", "").f().sx("vacciné").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionImmunisations(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputFamilleMarie(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if("Page".equals(classeApiMethodeMethode)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classeApiMethodeMethode, "_familleMarie")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classeApiMethodeMethode, "_familleMarie");
		}
		if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
			a("class", "setFamilleMarie inputInscriptionScolaire", pk, "FamilleMarie w3-input w3-border ");
			a("name", "setFamilleMarie");
		} else {
			a("class", "valeurFamilleMarie inputInscriptionScolaire", pk, "FamilleMarie w3-input w3-border ");
			a("name", "familleMarie");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleMarie', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleMarie')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleMarie')); }); ");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			if(getFamilleMarie() != null && getFamilleMarie())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmFamilleMarie(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireFamilleMarie").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_familleMarie").a("class", "").f().sx("parents marié").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilleMarie(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputFamilleSepare(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if("Page".equals(classeApiMethodeMethode)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classeApiMethodeMethode, "_familleSepare")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classeApiMethodeMethode, "_familleSepare");
		}
		if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
			a("class", "setFamilleSepare inputInscriptionScolaire", pk, "FamilleSepare w3-input w3-border ");
			a("name", "setFamilleSepare");
		} else {
			a("class", "valeurFamilleSepare inputInscriptionScolaire", pk, "FamilleSepare w3-input w3-border ");
			a("name", "familleSepare");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleSepare', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleSepare')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleSepare')); }); ");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			if(getFamilleSepare() != null && getFamilleSepare())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmFamilleSepare(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireFamilleSepare").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_familleSepare").a("class", "").f().sx("parents séparé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilleSepare(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputFamilleDivorce(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if("Page".equals(classeApiMethodeMethode)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classeApiMethodeMethode, "_familleDivorce")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classeApiMethodeMethode, "_familleDivorce");
		}
		if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
			a("class", "setFamilleDivorce inputInscriptionScolaire", pk, "FamilleDivorce w3-input w3-border ");
			a("name", "setFamilleDivorce");
		} else {
			a("class", "valeurFamilleDivorce inputInscriptionScolaire", pk, "FamilleDivorce w3-input w3-border ");
			a("name", "familleDivorce");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleDivorce', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleDivorce')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleDivorce')); }); ");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			if(getFamilleDivorce() != null && getFamilleDivorce())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmFamilleDivorce(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireFamilleDivorce").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_familleDivorce").a("class", "").f().sx("parents divorcé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilleDivorce(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputFamilleAddresse(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("textarea")
			.a("placeholder", "addresse de la famille")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_familleAddresse");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setFamilleAddresse inputInscriptionScolaire", pk, "FamilleAddresse w3-input w3-border ");
				a("name", "setFamilleAddresse");
			} else {
				a("class", "valeurFamilleAddresse w3-input w3-border inputInscriptionScolaire", pk, "FamilleAddresse w3-input w3-border ");
				a("name", "familleAddresse");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleAddresse', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleAddresse')); }); ");
			}
		f().sx(strFamilleAddresse()).g("textarea");

	}

	public void htmFamilleAddresse(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireFamilleAddresse").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_familleAddresse").a("class", "").f().sx("addresse de la famille").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilleAddresse(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_familleAddresse')); $('#", classeApiMethodeMethode, "_familleAddresse').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setFamilleAddresse', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleAddresse')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputFamilleCommentVousConnaissezEcole(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("textarea")
			.a("placeholder", "comment vous connaissez l'école ? ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setFamilleCommentVousConnaissezEcole inputInscriptionScolaire", pk, "FamilleCommentVousConnaissezEcole w3-input w3-border ");
				a("name", "setFamilleCommentVousConnaissezEcole");
			} else {
				a("class", "valeurFamilleCommentVousConnaissezEcole w3-input w3-border inputInscriptionScolaire", pk, "FamilleCommentVousConnaissezEcole w3-input w3-border ");
				a("name", "familleCommentVousConnaissezEcole");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleCommentVousConnaissezEcole', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole')); }); ");
			}
		f().sx(strFamilleCommentVousConnaissezEcole()).g("textarea");

	}

	public void htmFamilleCommentVousConnaissezEcole(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireFamilleCommentVousConnaissezEcole").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole").a("class", "").f().sx("comment vous connaissez l'école ? ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilleCommentVousConnaissezEcole(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole')); $('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setFamilleCommentVousConnaissezEcole', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputInscriptionConsiderationsSpeciales(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("textarea")
			.a("placeholder", "considérations spéciale")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setInscriptionConsiderationsSpeciales inputInscriptionScolaire", pk, "InscriptionConsiderationsSpeciales w3-input w3-border ");
				a("name", "setInscriptionConsiderationsSpeciales");
			} else {
				a("class", "valeurInscriptionConsiderationsSpeciales w3-input w3-border inputInscriptionScolaire", pk, "InscriptionConsiderationsSpeciales w3-input w3-border ");
				a("name", "inscriptionConsiderationsSpeciales");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionConsiderationsSpeciales', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales')); }); ");
			}
		f().sx(strInscriptionConsiderationsSpeciales()).g("textarea");

	}

	public void htmInscriptionConsiderationsSpeciales(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionConsiderationsSpeciales").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales").a("class", "").f().sx("considérations spéciale").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionConsiderationsSpeciales(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales')); $('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionConsiderationsSpeciales', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantConditionsMedicales">Trouver l'entité enfantConditionsMedicales dans Solr</a>
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
	protected InscriptionScolaire enfantConditionsMedicalesInit() {
		if(!enfantConditionsMedicalesCouverture.dejaInitialise) {
			_enfantConditionsMedicales(enfantConditionsMedicalesCouverture);
			if(enfantConditionsMedicales == null)
				setEnfantConditionsMedicales(enfantConditionsMedicalesCouverture.o);
		}
		enfantConditionsMedicalesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
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

	public void inputEnfantConditionsMedicales(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("textarea")
			.a("placeholder", "conditions médicales")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_enfantConditionsMedicales");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setEnfantConditionsMedicales inputInscriptionScolaire", pk, "EnfantConditionsMedicales w3-input w3-border ");
				a("name", "setEnfantConditionsMedicales");
			} else {
				a("class", "valeurEnfantConditionsMedicales w3-input w3-border inputInscriptionScolaire", pk, "EnfantConditionsMedicales w3-input w3-border ");
				a("name", "enfantConditionsMedicales");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantConditionsMedicales', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantConditionsMedicales')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantConditionsMedicales')); }); ");
			}
		f().sx(strEnfantConditionsMedicales()).g("textarea");

	}

	public void htmEnfantConditionsMedicales(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantConditionsMedicales").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantConditionsMedicales").a("class", "").f().sx("conditions médicales").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantConditionsMedicales(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantConditionsMedicales')); $('#", classeApiMethodeMethode, "_enfantConditionsMedicales').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantConditionsMedicales', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantConditionsMedicales')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantConditionsMedicales')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantEcolesPrecedemmentFrequentees">Trouver l'entité enfantEcolesPrecedemmentFrequentees dans Solr</a>
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
	protected InscriptionScolaire enfantEcolesPrecedemmentFrequenteesInit() {
		if(!enfantEcolesPrecedemmentFrequenteesCouverture.dejaInitialise) {
			_enfantEcolesPrecedemmentFrequentees(enfantEcolesPrecedemmentFrequenteesCouverture);
			if(enfantEcolesPrecedemmentFrequentees == null)
				setEnfantEcolesPrecedemmentFrequentees(enfantEcolesPrecedemmentFrequenteesCouverture.o);
		}
		enfantEcolesPrecedemmentFrequenteesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
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

	public void inputEnfantEcolesPrecedemmentFrequentees(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("textarea")
			.a("placeholder", "écoles précedemment fréqentées")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setEnfantEcolesPrecedemmentFrequentees inputInscriptionScolaire", pk, "EnfantEcolesPrecedemmentFrequentees w3-input w3-border ");
				a("name", "setEnfantEcolesPrecedemmentFrequentees");
			} else {
				a("class", "valeurEnfantEcolesPrecedemmentFrequentees w3-input w3-border inputInscriptionScolaire", pk, "EnfantEcolesPrecedemmentFrequentees w3-input w3-border ");
				a("name", "enfantEcolesPrecedemmentFrequentees");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantEcolesPrecedemmentFrequentees', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees')); }); ");
			}
		f().sx(strEnfantEcolesPrecedemmentFrequentees()).g("textarea");

	}

	public void htmEnfantEcolesPrecedemmentFrequentees(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantEcolesPrecedemmentFrequentees").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees").a("class", "").f().sx("écoles précedemment fréqentées").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantEcolesPrecedemmentFrequentees(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees')); $('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantEcolesPrecedemmentFrequentees', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantDescription">Trouver l'entité enfantDescription dans Solr</a>
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
	protected InscriptionScolaire enfantDescriptionInit() {
		if(!enfantDescriptionCouverture.dejaInitialise) {
			_enfantDescription(enfantDescriptionCouverture);
			if(enfantDescription == null)
				setEnfantDescription(enfantDescriptionCouverture.o);
		}
		enfantDescriptionCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
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

	public void inputEnfantDescription(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("textarea")
			.a("placeholder", "description")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_enfantDescription");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setEnfantDescription inputInscriptionScolaire", pk, "EnfantDescription w3-input w3-border ");
				a("name", "setEnfantDescription");
			} else {
				a("class", "valeurEnfantDescription w3-input w3-border inputInscriptionScolaire", pk, "EnfantDescription w3-input w3-border ");
				a("name", "enfantDescription");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantDescription', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantDescription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantDescription')); }); ");
			}
		f().sx(strEnfantDescription()).g("textarea");

	}

	public void htmEnfantDescription(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantDescription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantDescription").a("class", "").f().sx("description").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantDescription(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantDescription')); $('#", classeApiMethodeMethode, "_enfantDescription').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantDescription', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantDescription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantDescription')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantObjectifs">Trouver l'entité enfantObjectifs dans Solr</a>
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
	protected InscriptionScolaire enfantObjectifsInit() {
		if(!enfantObjectifsCouverture.dejaInitialise) {
			_enfantObjectifs(enfantObjectifsCouverture);
			if(enfantObjectifs == null)
				setEnfantObjectifs(enfantObjectifsCouverture.o);
		}
		enfantObjectifsCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
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

	public void inputEnfantObjectifs(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("textarea")
			.a("placeholder", "objectifs")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_enfantObjectifs");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setEnfantObjectifs inputInscriptionScolaire", pk, "EnfantObjectifs w3-input w3-border ");
				a("name", "setEnfantObjectifs");
			} else {
				a("class", "valeurEnfantObjectifs w3-input w3-border inputInscriptionScolaire", pk, "EnfantObjectifs w3-input w3-border ");
				a("name", "enfantObjectifs");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantObjectifs', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantObjectifs')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantObjectifs')); }); ");
			}
		f().sx(strEnfantObjectifs()).g("textarea");

	}

	public void htmEnfantObjectifs(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantObjectifs").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantObjectifs").a("class", "").f().sx("objectifs").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantObjectifs(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantObjectifs')); $('#", classeApiMethodeMethode, "_enfantObjectifs').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantObjectifs', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantObjectifs')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantObjectifs')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantPropre">Trouver l'entité enfantPropre dans Solr</a>
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
	public InscriptionScolaire setEnfantPropre(String o) {
		this.enfantPropre = Boolean.parseBoolean(o);
		this.enfantPropreCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire enfantPropreInit() {
		if(!enfantPropreCouverture.dejaInitialise) {
			_enfantPropre(enfantPropreCouverture);
			if(enfantPropre == null)
				setEnfantPropre(enfantPropreCouverture.o);
		}
		enfantPropreCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
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

	public void inputEnfantPropre(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if("Page".equals(classeApiMethodeMethode)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classeApiMethodeMethode, "_enfantPropre")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classeApiMethodeMethode, "_enfantPropre");
		}
		if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
			a("class", "setEnfantPropre inputInscriptionScolaire", pk, "EnfantPropre w3-input w3-border ");
			a("name", "setEnfantPropre");
		} else {
			a("class", "valeurEnfantPropre inputInscriptionScolaire", pk, "EnfantPropre w3-input w3-border ");
			a("name", "enfantPropre");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantPropre', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantPropre')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantPropre')); }); ");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			if(getEnfantPropre() != null && getEnfantPropre())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmEnfantPropre(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantPropre").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantPropre").a("class", "").f().sx("propre").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantPropre(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputInscriptionNomGroupe(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("placeholder", "nom du groupe")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_inscriptionNomGroupe");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setInscriptionNomGroupe inputInscriptionScolaire", pk, "InscriptionNomGroupe w3-input w3-border ");
				a("name", "setInscriptionNomGroupe");
			} else {
				a("class", "valeurInscriptionNomGroupe w3-input w3-border inputInscriptionScolaire", pk, "InscriptionNomGroupe w3-input w3-border ");
				a("name", "inscriptionNomGroupe");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionNomGroupe', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionNomGroupe')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionNomGroupe')); }); ");
			}
			a("value", strInscriptionNomGroupe())
		.fg();

	}

	public void htmInscriptionNomGroupe(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionNomGroupe").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_inscriptionNomGroupe").a("class", "").f().sx("nom du groupe").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionNomGroupe(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionNomGroupe')); $('#", classeApiMethodeMethode, "_inscriptionNomGroupe').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionNomGroupe', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionNomGroupe')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionNomGroupe')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputInscriptionPaimentChaqueMois(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if("Page".equals(classeApiMethodeMethode)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classeApiMethodeMethode, "_inscriptionPaimentChaqueMois")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classeApiMethodeMethode, "_inscriptionPaimentChaqueMois");
		}
		if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
			a("class", "setInscriptionPaimentChaqueMois inputInscriptionScolaire", pk, "InscriptionPaimentChaqueMois w3-input w3-border ");
			a("name", "setInscriptionPaimentChaqueMois");
		} else {
			a("class", "valeurInscriptionPaimentChaqueMois inputInscriptionScolaire", pk, "InscriptionPaimentChaqueMois w3-input w3-border ");
			a("name", "inscriptionPaimentChaqueMois");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionPaimentChaqueMois', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionPaimentChaqueMois')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionPaimentChaqueMois')); }); ");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			if(getInscriptionPaimentChaqueMois() != null && getInscriptionPaimentChaqueMois())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmInscriptionPaimentChaqueMois(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionPaimentChaqueMois").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_inscriptionPaimentChaqueMois").a("class", "").f().sx("paiement chaque mois").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionPaimentChaqueMois(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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

	public void inputInscriptionPaimentComplet(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if("Page".equals(classeApiMethodeMethode)) {
			e("input")
				.a("type", "checkbox")
				.a("id", classeApiMethodeMethode, "_inscriptionPaimentComplet")
				.a("value", "true");
		} else {
			e("select")
				.a("id", classeApiMethodeMethode, "_inscriptionPaimentComplet");
		}
		if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
			a("class", "setInscriptionPaimentComplet inputInscriptionScolaire", pk, "InscriptionPaimentComplet w3-input w3-border ");
			a("name", "setInscriptionPaimentComplet");
		} else {
			a("class", "valeurInscriptionPaimentComplet inputInscriptionScolaire", pk, "InscriptionPaimentComplet w3-input w3-border ");
			a("name", "inscriptionPaimentComplet");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionPaimentComplet', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionPaimentComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionPaimentComplet')); }); ");
		}
		if("Page".equals(classeApiMethodeMethode)) {
			if(getInscriptionPaimentComplet() != null && getInscriptionPaimentComplet())
				a("checked", "checked");
			fg();
		} else {
			f();
			e("option").a("value", "").a("selected", "selected").f().g("option");
			e("option").a("value", "true").f().sx("true").g("option");
			e("option").a("value", "false").f().sx("false").g("option");
			g("select");
		}

	}

	public void htmInscriptionPaimentComplet(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionPaimentComplet").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", classeApiMethodeMethode, "_inscriptionPaimentComplet").a("class", "").f().sx("paiement complet").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionPaimentComplet(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////////
	// inscriptionNomsParents //
	////////////////////////////

	/**	L'entité « inscriptionNomsParents »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionNomsParents;
	@JsonIgnore
	public Couverture<String> inscriptionNomsParentsCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionNomsParents").o(inscriptionNomsParents);

	/**	<br/>L'entité « inscriptionNomsParents »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNomsParents">Trouver l'entité inscriptionNomsParents dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionNomsParents(Couverture<String> c);

	public String getInscriptionNomsParents() {
		return inscriptionNomsParents;
	}

	public void setInscriptionNomsParents(String inscriptionNomsParents) {
		this.inscriptionNomsParents = inscriptionNomsParents;
		this.inscriptionNomsParentsCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionNomsParentsInit() {
		if(!inscriptionNomsParentsCouverture.dejaInitialise) {
			_inscriptionNomsParents(inscriptionNomsParentsCouverture);
			if(inscriptionNomsParents == null)
				setInscriptionNomsParents(inscriptionNomsParentsCouverture.o);
		}
		inscriptionNomsParentsCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionNomsParents() {
		return inscriptionNomsParents;
	}

	public String strInscriptionNomsParents() {
		return inscriptionNomsParents == null ? "" : inscriptionNomsParents;
	}

	public String jsonInscriptionNomsParents() {
		return inscriptionNomsParents == null ? "" : inscriptionNomsParents;
	}

	public String nomAffichageInscriptionNomsParents() {
		return null;
	}

	public String htmTooltipInscriptionNomsParents() {
		return null;
	}

	public String htmInscriptionNomsParents() {
		return inscriptionNomsParents == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionNomsParents());
	}

	public void inputInscriptionNomsParents(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
			.a("id", classeApiMethodeMethode, "_inscriptionNomsParents");
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setInscriptionNomsParents inputInscriptionScolaire", pk, "InscriptionNomsParents w3-input w3-border ");
				a("name", "setInscriptionNomsParents");
			} else {
				a("class", "valeurInscriptionNomsParents w3-input w3-border inputInscriptionScolaire", pk, "InscriptionNomsParents w3-input w3-border ");
				a("name", "inscriptionNomsParents");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionNomsParents', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionNomsParents')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionNomsParents')); }); ");
			}
			a("value", strInscriptionNomsParents())
		.fg();

	}

	public void htmInscriptionNomsParents(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionNomsParents").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionNomsParents(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionNomsParents')); $('#", classeApiMethodeMethode, "_inscriptionNomsParents').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionNomsParents', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionNomsParents')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionNomsParents')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////////////
	// inscriptionNomParentLignes //
	////////////////////////////////

	/**	L'entité « inscriptionNomParentLignes »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionNomParentLignes;
	@JsonIgnore
	public Couverture<String> inscriptionNomParentLignesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionNomParentLignes").o(inscriptionNomParentLignes);

	/**	<br/>L'entité « inscriptionNomParentLignes »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNomParentLignes">Trouver l'entité inscriptionNomParentLignes dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionNomParentLignes(Couverture<String> c);

	public String getInscriptionNomParentLignes() {
		return inscriptionNomParentLignes;
	}

	public void setInscriptionNomParentLignes(String inscriptionNomParentLignes) {
		this.inscriptionNomParentLignes = inscriptionNomParentLignes;
		this.inscriptionNomParentLignesCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionNomParentLignesInit() {
		if(!inscriptionNomParentLignesCouverture.dejaInitialise) {
			_inscriptionNomParentLignes(inscriptionNomParentLignesCouverture);
			if(inscriptionNomParentLignes == null)
				setInscriptionNomParentLignes(inscriptionNomParentLignesCouverture.o);
		}
		inscriptionNomParentLignesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionNomParentLignes() {
		return inscriptionNomParentLignes;
	}

	public String strInscriptionNomParentLignes() {
		return inscriptionNomParentLignes == null ? "" : inscriptionNomParentLignes;
	}

	public String jsonInscriptionNomParentLignes() {
		return inscriptionNomParentLignes == null ? "" : inscriptionNomParentLignes;
	}

	public String nomAffichageInscriptionNomParentLignes() {
		return null;
	}

	public String htmTooltipInscriptionNomParentLignes() {
		return null;
	}

	public String htmInscriptionNomParentLignes() {
		return inscriptionNomParentLignes == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionNomParentLignes());
	}

	/////////////////////////////////
	// inscriptionMailParentLignes //
	/////////////////////////////////

	/**	L'entité « inscriptionMailParentLignes »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionMailParentLignes;
	@JsonIgnore
	public Couverture<String> inscriptionMailParentLignesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionMailParentLignes").o(inscriptionMailParentLignes);

	/**	<br/>L'entité « inscriptionMailParentLignes »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionMailParentLignes">Trouver l'entité inscriptionMailParentLignes dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionMailParentLignes(Couverture<String> c);

	public String getInscriptionMailParentLignes() {
		return inscriptionMailParentLignes;
	}

	public void setInscriptionMailParentLignes(String inscriptionMailParentLignes) {
		this.inscriptionMailParentLignes = inscriptionMailParentLignes;
		this.inscriptionMailParentLignesCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionMailParentLignesInit() {
		if(!inscriptionMailParentLignesCouverture.dejaInitialise) {
			_inscriptionMailParentLignes(inscriptionMailParentLignesCouverture);
			if(inscriptionMailParentLignes == null)
				setInscriptionMailParentLignes(inscriptionMailParentLignesCouverture.o);
		}
		inscriptionMailParentLignesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionMailParentLignes() {
		return inscriptionMailParentLignes;
	}

	public String strInscriptionMailParentLignes() {
		return inscriptionMailParentLignes == null ? "" : inscriptionMailParentLignes;
	}

	public String jsonInscriptionMailParentLignes() {
		return inscriptionMailParentLignes == null ? "" : inscriptionMailParentLignes;
	}

	public String nomAffichageInscriptionMailParentLignes() {
		return null;
	}

	public String htmTooltipInscriptionMailParentLignes() {
		return null;
	}

	public String htmInscriptionMailParentLignes() {
		return inscriptionMailParentLignes == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionMailParentLignes());
	}

	///////////////////////////////////
	// inscriptionDetailParentLignes //
	///////////////////////////////////

	/**	L'entité « inscriptionDetailParentLignes »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionDetailParentLignes;
	@JsonIgnore
	public Couverture<String> inscriptionDetailParentLignesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionDetailParentLignes").o(inscriptionDetailParentLignes);

	/**	<br/>L'entité « inscriptionDetailParentLignes »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDetailParentLignes">Trouver l'entité inscriptionDetailParentLignes dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDetailParentLignes(Couverture<String> c);

	public String getInscriptionDetailParentLignes() {
		return inscriptionDetailParentLignes;
	}

	public void setInscriptionDetailParentLignes(String inscriptionDetailParentLignes) {
		this.inscriptionDetailParentLignes = inscriptionDetailParentLignes;
		this.inscriptionDetailParentLignesCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionDetailParentLignesInit() {
		if(!inscriptionDetailParentLignesCouverture.dejaInitialise) {
			_inscriptionDetailParentLignes(inscriptionDetailParentLignesCouverture);
			if(inscriptionDetailParentLignes == null)
				setInscriptionDetailParentLignes(inscriptionDetailParentLignesCouverture.o);
		}
		inscriptionDetailParentLignesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionDetailParentLignes() {
		return inscriptionDetailParentLignes;
	}

	public String strInscriptionDetailParentLignes() {
		return inscriptionDetailParentLignes == null ? "" : inscriptionDetailParentLignes;
	}

	public String jsonInscriptionDetailParentLignes() {
		return inscriptionDetailParentLignes == null ? "" : inscriptionDetailParentLignes;
	}

	public String nomAffichageInscriptionDetailParentLignes() {
		return null;
	}

	public String htmTooltipInscriptionDetailParentLignes() {
		return null;
	}

	public String htmInscriptionDetailParentLignes() {
		return inscriptionDetailParentLignes == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDetailParentLignes());
	}

	/////////////////////////////////////
	// inscriptionChercherParentLignes //
	/////////////////////////////////////

	/**	L'entité « inscriptionChercherParentLignes »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionChercherParentLignes;
	@JsonIgnore
	public Couverture<String> inscriptionChercherParentLignesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionChercherParentLignes").o(inscriptionChercherParentLignes);

	/**	<br/>L'entité « inscriptionChercherParentLignes »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionChercherParentLignes">Trouver l'entité inscriptionChercherParentLignes dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionChercherParentLignes(Couverture<String> c);

	public String getInscriptionChercherParentLignes() {
		return inscriptionChercherParentLignes;
	}

	public void setInscriptionChercherParentLignes(String inscriptionChercherParentLignes) {
		this.inscriptionChercherParentLignes = inscriptionChercherParentLignes;
		this.inscriptionChercherParentLignesCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionChercherParentLignesInit() {
		if(!inscriptionChercherParentLignesCouverture.dejaInitialise) {
			_inscriptionChercherParentLignes(inscriptionChercherParentLignesCouverture);
			if(inscriptionChercherParentLignes == null)
				setInscriptionChercherParentLignes(inscriptionChercherParentLignesCouverture.o);
		}
		inscriptionChercherParentLignesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionChercherParentLignes() {
		return inscriptionChercherParentLignes;
	}

	public String strInscriptionChercherParentLignes() {
		return inscriptionChercherParentLignes == null ? "" : inscriptionChercherParentLignes;
	}

	public String jsonInscriptionChercherParentLignes() {
		return inscriptionChercherParentLignes == null ? "" : inscriptionChercherParentLignes;
	}

	public String nomAffichageInscriptionChercherParentLignes() {
		return null;
	}

	public String htmTooltipInscriptionChercherParentLignes() {
		return null;
	}

	public String htmInscriptionChercherParentLignes() {
		return inscriptionChercherParentLignes == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionChercherParentLignes());
	}

	///////////////////////////////////////////
	// inscriptionContactUrgenceParentLignes //
	///////////////////////////////////////////

	/**	L'entité « inscriptionContactUrgenceParentLignes »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionContactUrgenceParentLignes;
	@JsonIgnore
	public Couverture<String> inscriptionContactUrgenceParentLignesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionContactUrgenceParentLignes").o(inscriptionContactUrgenceParentLignes);

	/**	<br/>L'entité « inscriptionContactUrgenceParentLignes »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionContactUrgenceParentLignes">Trouver l'entité inscriptionContactUrgenceParentLignes dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionContactUrgenceParentLignes(Couverture<String> c);

	public String getInscriptionContactUrgenceParentLignes() {
		return inscriptionContactUrgenceParentLignes;
	}

	public void setInscriptionContactUrgenceParentLignes(String inscriptionContactUrgenceParentLignes) {
		this.inscriptionContactUrgenceParentLignes = inscriptionContactUrgenceParentLignes;
		this.inscriptionContactUrgenceParentLignesCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionContactUrgenceParentLignesInit() {
		if(!inscriptionContactUrgenceParentLignesCouverture.dejaInitialise) {
			_inscriptionContactUrgenceParentLignes(inscriptionContactUrgenceParentLignesCouverture);
			if(inscriptionContactUrgenceParentLignes == null)
				setInscriptionContactUrgenceParentLignes(inscriptionContactUrgenceParentLignesCouverture.o);
		}
		inscriptionContactUrgenceParentLignesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionContactUrgenceParentLignes() {
		return inscriptionContactUrgenceParentLignes;
	}

	public String strInscriptionContactUrgenceParentLignes() {
		return inscriptionContactUrgenceParentLignes == null ? "" : inscriptionContactUrgenceParentLignes;
	}

	public String jsonInscriptionContactUrgenceParentLignes() {
		return inscriptionContactUrgenceParentLignes == null ? "" : inscriptionContactUrgenceParentLignes;
	}

	public String nomAffichageInscriptionContactUrgenceParentLignes() {
		return null;
	}

	public String htmTooltipInscriptionContactUrgenceParentLignes() {
		return null;
	}

	public String htmInscriptionContactUrgenceParentLignes() {
		return inscriptionContactUrgenceParentLignes == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionContactUrgenceParentLignes());
	}

	///////////////////////////
	// inscriptionSignature1 //
	///////////////////////////

	/**	L'entité « inscriptionSignature1 »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionSignature1;
	@JsonIgnore
	public Couverture<String> inscriptionSignature1Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature1").o(inscriptionSignature1);

	/**	<br/>L'entité « inscriptionSignature1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature1">Trouver l'entité inscriptionSignature1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionSignature1(Couverture<String> c);

	public String getInscriptionSignature1() {
		return inscriptionSignature1;
	}

	public void setInscriptionSignature1(String inscriptionSignature1) {
		this.inscriptionSignature1 = inscriptionSignature1;
		this.inscriptionSignature1Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionSignature1Init() {
		if(!inscriptionSignature1Couverture.dejaInitialise) {
			_inscriptionSignature1(inscriptionSignature1Couverture);
			if(inscriptionSignature1 == null)
				setInscriptionSignature1(inscriptionSignature1Couverture.o);
		}
		inscriptionSignature1Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionSignature1() {
		return inscriptionSignature1;
	}

	public String strInscriptionSignature1() {
		return inscriptionSignature1 == null ? "" : inscriptionSignature1;
	}

	public String jsonInscriptionSignature1() {
		return inscriptionSignature1 == null ? "" : inscriptionSignature1;
	}

	public String nomAffichageInscriptionSignature1() {
		return null;
	}

	public String htmTooltipInscriptionSignature1() {
		return null;
	}

	public String htmInscriptionSignature1() {
		return inscriptionSignature1 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionSignature1());
	}

	public void inputInscriptionSignature1(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("div").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature1").f();
			e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature1");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(inscriptionSignature1) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature1");
				a("src", StringUtils.isBlank(inscriptionSignature1) ? "data:image/png;base64," : inscriptionSignature1).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature1) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature1").f();
			e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature1");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature1').show(); ");
					s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature1').hide(); ");
					s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature1')); ");
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature1', null); ");
					s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature1')) { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature1').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature1').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Vider");
			g("button");
			e("button").a("id", "signatureButtonAccepterInscriptionScolaire", pk, "inscriptionSignature1");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputInscriptionScolaire", pk, "inscriptionSignature1').jSignature('getData', 'default'); "); 
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature1', src); ");
				s("\"");
				f().sx("Accepter la signature");
			g("button");
		g("div");
	}

	public void htmInscriptionSignature1(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature1").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature1(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature1')); $('#", classeApiMethodeMethode, "_inscriptionSignature1').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature1', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature1')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature1')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////
	// inscriptionSignature2 //
	///////////////////////////

	/**	L'entité « inscriptionSignature2 »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionSignature2;
	@JsonIgnore
	public Couverture<String> inscriptionSignature2Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature2").o(inscriptionSignature2);

	/**	<br/>L'entité « inscriptionSignature2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature2">Trouver l'entité inscriptionSignature2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionSignature2(Couverture<String> c);

	public String getInscriptionSignature2() {
		return inscriptionSignature2;
	}

	public void setInscriptionSignature2(String inscriptionSignature2) {
		this.inscriptionSignature2 = inscriptionSignature2;
		this.inscriptionSignature2Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionSignature2Init() {
		if(!inscriptionSignature2Couverture.dejaInitialise) {
			_inscriptionSignature2(inscriptionSignature2Couverture);
			if(inscriptionSignature2 == null)
				setInscriptionSignature2(inscriptionSignature2Couverture.o);
		}
		inscriptionSignature2Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionSignature2() {
		return inscriptionSignature2;
	}

	public String strInscriptionSignature2() {
		return inscriptionSignature2 == null ? "" : inscriptionSignature2;
	}

	public String jsonInscriptionSignature2() {
		return inscriptionSignature2 == null ? "" : inscriptionSignature2;
	}

	public String nomAffichageInscriptionSignature2() {
		return null;
	}

	public String htmTooltipInscriptionSignature2() {
		return null;
	}

	public String htmInscriptionSignature2() {
		return inscriptionSignature2 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionSignature2());
	}

	public void inputInscriptionSignature2(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("div").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature2").f();
			e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature2");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(inscriptionSignature2) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature2");
				a("src", StringUtils.isBlank(inscriptionSignature2) ? "data:image/png;base64," : inscriptionSignature2).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature2) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature2").f();
			e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature2");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature2').show(); ");
					s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature2').hide(); ");
					s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature2')); ");
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature2', null); ");
					s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature2')) { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature2').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature2').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Vider");
			g("button");
			e("button").a("id", "signatureButtonAccepterInscriptionScolaire", pk, "inscriptionSignature2");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputInscriptionScolaire", pk, "inscriptionSignature2').jSignature('getData', 'default'); "); 
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature2', src); ");
				s("\"");
				f().sx("Accepter la signature");
			g("button");
		g("div");
	}

	public void htmInscriptionSignature2(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature2").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature2(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature2')); $('#", classeApiMethodeMethode, "_inscriptionSignature2').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature2', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature2')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature2')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////
	// inscriptionSignature3 //
	///////////////////////////

	/**	L'entité « inscriptionSignature3 »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionSignature3;
	@JsonIgnore
	public Couverture<String> inscriptionSignature3Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature3").o(inscriptionSignature3);

	/**	<br/>L'entité « inscriptionSignature3 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature3">Trouver l'entité inscriptionSignature3 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionSignature3(Couverture<String> c);

	public String getInscriptionSignature3() {
		return inscriptionSignature3;
	}

	public void setInscriptionSignature3(String inscriptionSignature3) {
		this.inscriptionSignature3 = inscriptionSignature3;
		this.inscriptionSignature3Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionSignature3Init() {
		if(!inscriptionSignature3Couverture.dejaInitialise) {
			_inscriptionSignature3(inscriptionSignature3Couverture);
			if(inscriptionSignature3 == null)
				setInscriptionSignature3(inscriptionSignature3Couverture.o);
		}
		inscriptionSignature3Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionSignature3() {
		return inscriptionSignature3;
	}

	public String strInscriptionSignature3() {
		return inscriptionSignature3 == null ? "" : inscriptionSignature3;
	}

	public String jsonInscriptionSignature3() {
		return inscriptionSignature3 == null ? "" : inscriptionSignature3;
	}

	public String nomAffichageInscriptionSignature3() {
		return null;
	}

	public String htmTooltipInscriptionSignature3() {
		return null;
	}

	public String htmInscriptionSignature3() {
		return inscriptionSignature3 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionSignature3());
	}

	public void inputInscriptionSignature3(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("div").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature3").f();
			e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature3");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(inscriptionSignature3) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature3");
				a("src", StringUtils.isBlank(inscriptionSignature3) ? "data:image/png;base64," : inscriptionSignature3).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature3) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature3").f();
			e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature3");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature3').show(); ");
					s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature3').hide(); ");
					s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature3')); ");
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature3', null); ");
					s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature3')) { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature3').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature3').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Vider");
			g("button");
			e("button").a("id", "signatureButtonAccepterInscriptionScolaire", pk, "inscriptionSignature3");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputInscriptionScolaire", pk, "inscriptionSignature3').jSignature('getData', 'default'); "); 
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature3', src); ");
				s("\"");
				f().sx("Accepter la signature");
			g("button");
		g("div");
	}

	public void htmInscriptionSignature3(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature3").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature3(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature3')); $('#", classeApiMethodeMethode, "_inscriptionSignature3').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature3', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature3')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature3')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////
	// inscriptionSignature4 //
	///////////////////////////

	/**	L'entité « inscriptionSignature4 »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionSignature4;
	@JsonIgnore
	public Couverture<String> inscriptionSignature4Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature4").o(inscriptionSignature4);

	/**	<br/>L'entité « inscriptionSignature4 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature4">Trouver l'entité inscriptionSignature4 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionSignature4(Couverture<String> c);

	public String getInscriptionSignature4() {
		return inscriptionSignature4;
	}

	public void setInscriptionSignature4(String inscriptionSignature4) {
		this.inscriptionSignature4 = inscriptionSignature4;
		this.inscriptionSignature4Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionSignature4Init() {
		if(!inscriptionSignature4Couverture.dejaInitialise) {
			_inscriptionSignature4(inscriptionSignature4Couverture);
			if(inscriptionSignature4 == null)
				setInscriptionSignature4(inscriptionSignature4Couverture.o);
		}
		inscriptionSignature4Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionSignature4() {
		return inscriptionSignature4;
	}

	public String strInscriptionSignature4() {
		return inscriptionSignature4 == null ? "" : inscriptionSignature4;
	}

	public String jsonInscriptionSignature4() {
		return inscriptionSignature4 == null ? "" : inscriptionSignature4;
	}

	public String nomAffichageInscriptionSignature4() {
		return null;
	}

	public String htmTooltipInscriptionSignature4() {
		return null;
	}

	public String htmInscriptionSignature4() {
		return inscriptionSignature4 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionSignature4());
	}

	public void inputInscriptionSignature4(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("div").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature4").f();
			e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature4");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(inscriptionSignature4) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature4");
				a("src", StringUtils.isBlank(inscriptionSignature4) ? "data:image/png;base64," : inscriptionSignature4).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature4) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature4").f();
			e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature4");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature4').show(); ");
					s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature4').hide(); ");
					s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature4')); ");
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature4', null); ");
					s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature4')) { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature4').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature4').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Vider");
			g("button");
			e("button").a("id", "signatureButtonAccepterInscriptionScolaire", pk, "inscriptionSignature4");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputInscriptionScolaire", pk, "inscriptionSignature4').jSignature('getData', 'default'); "); 
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature4', src); ");
				s("\"");
				f().sx("Accepter la signature");
			g("button");
		g("div");
	}

	public void htmInscriptionSignature4(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature4").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature4(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature4')); $('#", classeApiMethodeMethode, "_inscriptionSignature4').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature4', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature4')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature4')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////
	// inscriptionSignature5 //
	///////////////////////////

	/**	L'entité « inscriptionSignature5 »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionSignature5;
	@JsonIgnore
	public Couverture<String> inscriptionSignature5Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature5").o(inscriptionSignature5);

	/**	<br/>L'entité « inscriptionSignature5 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature5">Trouver l'entité inscriptionSignature5 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionSignature5(Couverture<String> c);

	public String getInscriptionSignature5() {
		return inscriptionSignature5;
	}

	public void setInscriptionSignature5(String inscriptionSignature5) {
		this.inscriptionSignature5 = inscriptionSignature5;
		this.inscriptionSignature5Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionSignature5Init() {
		if(!inscriptionSignature5Couverture.dejaInitialise) {
			_inscriptionSignature5(inscriptionSignature5Couverture);
			if(inscriptionSignature5 == null)
				setInscriptionSignature5(inscriptionSignature5Couverture.o);
		}
		inscriptionSignature5Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionSignature5() {
		return inscriptionSignature5;
	}

	public String strInscriptionSignature5() {
		return inscriptionSignature5 == null ? "" : inscriptionSignature5;
	}

	public String jsonInscriptionSignature5() {
		return inscriptionSignature5 == null ? "" : inscriptionSignature5;
	}

	public String nomAffichageInscriptionSignature5() {
		return null;
	}

	public String htmTooltipInscriptionSignature5() {
		return null;
	}

	public String htmInscriptionSignature5() {
		return inscriptionSignature5 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionSignature5());
	}

	public void inputInscriptionSignature5(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("div").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature5").f();
			e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature5");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(inscriptionSignature5) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature5");
				a("src", StringUtils.isBlank(inscriptionSignature5) ? "data:image/png;base64," : inscriptionSignature5).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature5) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature5").f();
			e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature5");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature5').show(); ");
					s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature5').hide(); ");
					s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature5')); ");
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature5', null); ");
					s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature5')) { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature5').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature5').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Vider");
			g("button");
			e("button").a("id", "signatureButtonAccepterInscriptionScolaire", pk, "inscriptionSignature5");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputInscriptionScolaire", pk, "inscriptionSignature5').jSignature('getData', 'default'); "); 
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature5', src); ");
				s("\"");
				f().sx("Accepter la signature");
			g("button");
		g("div");
	}

	public void htmInscriptionSignature5(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature5").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature5(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature5')); $('#", classeApiMethodeMethode, "_inscriptionSignature5').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature5', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature5')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature5')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////
	// inscriptionSignature6 //
	///////////////////////////

	/**	L'entité « inscriptionSignature6 »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionSignature6;
	@JsonIgnore
	public Couverture<String> inscriptionSignature6Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature6").o(inscriptionSignature6);

	/**	<br/>L'entité « inscriptionSignature6 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature6">Trouver l'entité inscriptionSignature6 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionSignature6(Couverture<String> c);

	public String getInscriptionSignature6() {
		return inscriptionSignature6;
	}

	public void setInscriptionSignature6(String inscriptionSignature6) {
		this.inscriptionSignature6 = inscriptionSignature6;
		this.inscriptionSignature6Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionSignature6Init() {
		if(!inscriptionSignature6Couverture.dejaInitialise) {
			_inscriptionSignature6(inscriptionSignature6Couverture);
			if(inscriptionSignature6 == null)
				setInscriptionSignature6(inscriptionSignature6Couverture.o);
		}
		inscriptionSignature6Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionSignature6() {
		return inscriptionSignature6;
	}

	public String strInscriptionSignature6() {
		return inscriptionSignature6 == null ? "" : inscriptionSignature6;
	}

	public String jsonInscriptionSignature6() {
		return inscriptionSignature6 == null ? "" : inscriptionSignature6;
	}

	public String nomAffichageInscriptionSignature6() {
		return null;
	}

	public String htmTooltipInscriptionSignature6() {
		return null;
	}

	public String htmInscriptionSignature6() {
		return inscriptionSignature6 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionSignature6());
	}

	public void inputInscriptionSignature6(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("div").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature6").f();
			e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature6");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(inscriptionSignature6) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature6");
				a("src", StringUtils.isBlank(inscriptionSignature6) ? "data:image/png;base64," : inscriptionSignature6).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature6) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature6").f();
			e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature6");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature6').show(); ");
					s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature6').hide(); ");
					s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature6')); ");
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature6', null); ");
					s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature6')) { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature6').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature6').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Vider");
			g("button");
			e("button").a("id", "signatureButtonAccepterInscriptionScolaire", pk, "inscriptionSignature6");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputInscriptionScolaire", pk, "inscriptionSignature6').jSignature('getData', 'default'); "); 
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature6', src); ");
				s("\"");
				f().sx("Accepter la signature");
			g("button");
		g("div");
	}

	public void htmInscriptionSignature6(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature6").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature6(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature6')); $('#", classeApiMethodeMethode, "_inscriptionSignature6').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature6', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature6')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature6')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////
	// inscriptionSignature7 //
	///////////////////////////

	/**	L'entité « inscriptionSignature7 »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionSignature7;
	@JsonIgnore
	public Couverture<String> inscriptionSignature7Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature7").o(inscriptionSignature7);

	/**	<br/>L'entité « inscriptionSignature7 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature7">Trouver l'entité inscriptionSignature7 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionSignature7(Couverture<String> c);

	public String getInscriptionSignature7() {
		return inscriptionSignature7;
	}

	public void setInscriptionSignature7(String inscriptionSignature7) {
		this.inscriptionSignature7 = inscriptionSignature7;
		this.inscriptionSignature7Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionSignature7Init() {
		if(!inscriptionSignature7Couverture.dejaInitialise) {
			_inscriptionSignature7(inscriptionSignature7Couverture);
			if(inscriptionSignature7 == null)
				setInscriptionSignature7(inscriptionSignature7Couverture.o);
		}
		inscriptionSignature7Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionSignature7() {
		return inscriptionSignature7;
	}

	public String strInscriptionSignature7() {
		return inscriptionSignature7 == null ? "" : inscriptionSignature7;
	}

	public String jsonInscriptionSignature7() {
		return inscriptionSignature7 == null ? "" : inscriptionSignature7;
	}

	public String nomAffichageInscriptionSignature7() {
		return null;
	}

	public String htmTooltipInscriptionSignature7() {
		return null;
	}

	public String htmInscriptionSignature7() {
		return inscriptionSignature7 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionSignature7());
	}

	public void inputInscriptionSignature7(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("div").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature7").f();
			e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature7");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(inscriptionSignature7) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature7");
				a("src", StringUtils.isBlank(inscriptionSignature7) ? "data:image/png;base64," : inscriptionSignature7).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature7) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature7").f();
			e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature7");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature7').show(); ");
					s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature7').hide(); ");
					s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature7')); ");
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature7', null); ");
					s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature7')) { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature7').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature7').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Vider");
			g("button");
			e("button").a("id", "signatureButtonAccepterInscriptionScolaire", pk, "inscriptionSignature7");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputInscriptionScolaire", pk, "inscriptionSignature7').jSignature('getData', 'default'); "); 
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature7', src); ");
				s("\"");
				f().sx("Accepter la signature");
			g("button");
		g("div");
	}

	public void htmInscriptionSignature7(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature7").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature7(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature7')); $('#", classeApiMethodeMethode, "_inscriptionSignature7').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature7', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature7')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature7')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////
	// inscriptionSignature8 //
	///////////////////////////

	/**	L'entité « inscriptionSignature8 »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionSignature8;
	@JsonIgnore
	public Couverture<String> inscriptionSignature8Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature8").o(inscriptionSignature8);

	/**	<br/>L'entité « inscriptionSignature8 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature8">Trouver l'entité inscriptionSignature8 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionSignature8(Couverture<String> c);

	public String getInscriptionSignature8() {
		return inscriptionSignature8;
	}

	public void setInscriptionSignature8(String inscriptionSignature8) {
		this.inscriptionSignature8 = inscriptionSignature8;
		this.inscriptionSignature8Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionSignature8Init() {
		if(!inscriptionSignature8Couverture.dejaInitialise) {
			_inscriptionSignature8(inscriptionSignature8Couverture);
			if(inscriptionSignature8 == null)
				setInscriptionSignature8(inscriptionSignature8Couverture.o);
		}
		inscriptionSignature8Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionSignature8() {
		return inscriptionSignature8;
	}

	public String strInscriptionSignature8() {
		return inscriptionSignature8 == null ? "" : inscriptionSignature8;
	}

	public String jsonInscriptionSignature8() {
		return inscriptionSignature8 == null ? "" : inscriptionSignature8;
	}

	public String nomAffichageInscriptionSignature8() {
		return null;
	}

	public String htmTooltipInscriptionSignature8() {
		return null;
	}

	public String htmInscriptionSignature8() {
		return inscriptionSignature8 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionSignature8());
	}

	public void inputInscriptionSignature8(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("div").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature8").f();
			e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature8");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(inscriptionSignature8) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature8");
				a("src", StringUtils.isBlank(inscriptionSignature8) ? "data:image/png;base64," : inscriptionSignature8).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature8) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature8").f();
			e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature8");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature8').show(); ");
					s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature8').hide(); ");
					s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature8')); ");
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature8', null); ");
					s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature8')) { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature8').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature8').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Vider");
			g("button");
			e("button").a("id", "signatureButtonAccepterInscriptionScolaire", pk, "inscriptionSignature8");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputInscriptionScolaire", pk, "inscriptionSignature8').jSignature('getData', 'default'); "); 
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature8', src); ");
				s("\"");
				f().sx("Accepter la signature");
			g("button");
		g("div");
	}

	public void htmInscriptionSignature8(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature8").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature8(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature8')); $('#", classeApiMethodeMethode, "_inscriptionSignature8').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature8', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature8')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature8')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////
	// inscriptionSignature9 //
	///////////////////////////

	/**	L'entité « inscriptionSignature9 »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionSignature9;
	@JsonIgnore
	public Couverture<String> inscriptionSignature9Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature9").o(inscriptionSignature9);

	/**	<br/>L'entité « inscriptionSignature9 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature9">Trouver l'entité inscriptionSignature9 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionSignature9(Couverture<String> c);

	public String getInscriptionSignature9() {
		return inscriptionSignature9;
	}

	public void setInscriptionSignature9(String inscriptionSignature9) {
		this.inscriptionSignature9 = inscriptionSignature9;
		this.inscriptionSignature9Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionSignature9Init() {
		if(!inscriptionSignature9Couverture.dejaInitialise) {
			_inscriptionSignature9(inscriptionSignature9Couverture);
			if(inscriptionSignature9 == null)
				setInscriptionSignature9(inscriptionSignature9Couverture.o);
		}
		inscriptionSignature9Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionSignature9() {
		return inscriptionSignature9;
	}

	public String strInscriptionSignature9() {
		return inscriptionSignature9 == null ? "" : inscriptionSignature9;
	}

	public String jsonInscriptionSignature9() {
		return inscriptionSignature9 == null ? "" : inscriptionSignature9;
	}

	public String nomAffichageInscriptionSignature9() {
		return null;
	}

	public String htmTooltipInscriptionSignature9() {
		return null;
	}

	public String htmInscriptionSignature9() {
		return inscriptionSignature9 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionSignature9());
	}

	public void inputInscriptionSignature9(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("div").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature9").f();
			e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature9");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(inscriptionSignature9) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature9");
				a("src", StringUtils.isBlank(inscriptionSignature9) ? "data:image/png;base64," : inscriptionSignature9).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature9) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature9").f();
			e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature9");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature9').show(); ");
					s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature9').hide(); ");
					s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature9')); ");
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature9', null); ");
					s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature9')) { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature9').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature9').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Vider");
			g("button");
			e("button").a("id", "signatureButtonAccepterInscriptionScolaire", pk, "inscriptionSignature9");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputInscriptionScolaire", pk, "inscriptionSignature9').jSignature('getData', 'default'); "); 
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature9', src); ");
				s("\"");
				f().sx("Accepter la signature");
			g("button");
		g("div");
	}

	public void htmInscriptionSignature9(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature9").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature9(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature9')); $('#", classeApiMethodeMethode, "_inscriptionSignature9').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature9', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature9')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature9')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////////
	// inscriptionSignature10 //
	////////////////////////////

	/**	L'entité « inscriptionSignature10 »
	 *	 is defined as null before being initialized. 
	 */
	protected String inscriptionSignature10;
	@JsonIgnore
	public Couverture<String> inscriptionSignature10Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature10").o(inscriptionSignature10);

	/**	<br/>L'entité « inscriptionSignature10 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature10">Trouver l'entité inscriptionSignature10 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionSignature10(Couverture<String> c);

	public String getInscriptionSignature10() {
		return inscriptionSignature10;
	}

	public void setInscriptionSignature10(String inscriptionSignature10) {
		this.inscriptionSignature10 = inscriptionSignature10;
		this.inscriptionSignature10Couverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionSignature10Init() {
		if(!inscriptionSignature10Couverture.dejaInitialise) {
			_inscriptionSignature10(inscriptionSignature10Couverture);
			if(inscriptionSignature10 == null)
				setInscriptionSignature10(inscriptionSignature10Couverture.o);
		}
		inscriptionSignature10Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionSignature10() {
		return inscriptionSignature10;
	}

	public String strInscriptionSignature10() {
		return inscriptionSignature10 == null ? "" : inscriptionSignature10;
	}

	public String jsonInscriptionSignature10() {
		return inscriptionSignature10 == null ? "" : inscriptionSignature10;
	}

	public String nomAffichageInscriptionSignature10() {
		return null;
	}

	public String htmTooltipInscriptionSignature10() {
		return null;
	}

	public String htmInscriptionSignature10() {
		return inscriptionSignature10 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionSignature10());
	}

	public void inputInscriptionSignature10(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("div").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature10").f();
			e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature10");
				a("style", "border: 1px solid black; display: ", StringUtils.isBlank(inscriptionSignature10) ? "block" : "none", "; ");
			f().g("div");
			e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature10");
				a("src", StringUtils.isBlank(inscriptionSignature10) ? "data:image/png;base64," : inscriptionSignature10).a("alt", "");
				a("style", "border: 1px solid black; padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature10) ? "none" : "block", "; ");
			fg();
		g("div");
		e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature10").f();
			e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature10");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature10').show(); ");
					s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature10').hide(); ");
					s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature10')); ");
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature10', null); ");
					s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature10')) { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature10').jSignature('reset'); ");
					s(" } else { ");
					s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature10').jSignature({'height':200}); ");
					s(" } ");
				s("\"");
				f().sx("Vider");
			g("button");
			e("button").a("id", "signatureButtonAccepterInscriptionScolaire", pk, "inscriptionSignature10");
				a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
				s(" onclick=", "\"");
					s("var src = $('#signatureInputInscriptionScolaire", pk, "inscriptionSignature10').jSignature('getData', 'default'); "); 
					s("patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature10', src); ");
				s("\"");
				f().sx("Accepter la signature");
			g("button");
		g("div");
	}

	public void htmInscriptionSignature10(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature10").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature10(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature10')); $('#", classeApiMethodeMethode, "_inscriptionSignature10').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature10', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature10')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature10')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// inscriptionDate1 //
	//////////////////////

	/**	L'entité « inscriptionDate1 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate inscriptionDate1;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate1Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate1").o(inscriptionDate1);

	/**	<br/>L'entité « inscriptionDate1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate1">Trouver l'entité inscriptionDate1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDate1(Couverture<LocalDate> c);

	public LocalDate getInscriptionDate1() {
		return inscriptionDate1;
	}

	public void setInscriptionDate1(LocalDate inscriptionDate1) {
		this.inscriptionDate1 = inscriptionDate1;
		this.inscriptionDate1Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionDate1(Instant o) {
		this.inscriptionDate1 = LocalDate.from(o);
		this.inscriptionDate1Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate1(String o) {
		this.inscriptionDate1 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.inscriptionDate1Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate1(Date o) {
		this.inscriptionDate1 = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate1Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionDate1Init() {
		if(!inscriptionDate1Couverture.dejaInitialise) {
			_inscriptionDate1(inscriptionDate1Couverture);
			if(inscriptionDate1 == null)
				setInscriptionDate1(inscriptionDate1Couverture.o);
		}
		inscriptionDate1Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrInscriptionDate1() {
		return inscriptionDate1 == null ? null : Date.from(inscriptionDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strInscriptionDate1() {
		return inscriptionDate1 == null ? "" : inscriptionDate1.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonInscriptionDate1() {
		return inscriptionDate1 == null ? "" : inscriptionDate1.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageInscriptionDate1() {
		return null;
	}

	public String htmTooltipInscriptionDate1() {
		return null;
	}

	public String htmInscriptionDate1() {
		return inscriptionDate1 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDate1());
	}

	public void inputInscriptionDate1(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setInscriptionDate1 inputInscriptionScolaire", pk, "InscriptionDate1 w3-input w3-border ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_inscriptionDate1")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données.  (DD-MM-YYYY)")
			.a("value", inscriptionDate1 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(inscriptionDate1))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate1', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate1')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate1')); }); } ")
			.fg();
	}

	public void htmInscriptionDate1(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate1").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate1(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate1')); $('#", classeApiMethodeMethode, "_inscriptionDate1').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate1', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate1')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate1')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// inscriptionDate2 //
	//////////////////////

	/**	L'entité « inscriptionDate2 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate inscriptionDate2;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate2Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate2").o(inscriptionDate2);

	/**	<br/>L'entité « inscriptionDate2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate2">Trouver l'entité inscriptionDate2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDate2(Couverture<LocalDate> c);

	public LocalDate getInscriptionDate2() {
		return inscriptionDate2;
	}

	public void setInscriptionDate2(LocalDate inscriptionDate2) {
		this.inscriptionDate2 = inscriptionDate2;
		this.inscriptionDate2Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionDate2(Instant o) {
		this.inscriptionDate2 = LocalDate.from(o);
		this.inscriptionDate2Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate2(String o) {
		this.inscriptionDate2 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.inscriptionDate2Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate2(Date o) {
		this.inscriptionDate2 = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate2Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionDate2Init() {
		if(!inscriptionDate2Couverture.dejaInitialise) {
			_inscriptionDate2(inscriptionDate2Couverture);
			if(inscriptionDate2 == null)
				setInscriptionDate2(inscriptionDate2Couverture.o);
		}
		inscriptionDate2Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrInscriptionDate2() {
		return inscriptionDate2 == null ? null : Date.from(inscriptionDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strInscriptionDate2() {
		return inscriptionDate2 == null ? "" : inscriptionDate2.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonInscriptionDate2() {
		return inscriptionDate2 == null ? "" : inscriptionDate2.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageInscriptionDate2() {
		return null;
	}

	public String htmTooltipInscriptionDate2() {
		return null;
	}

	public String htmInscriptionDate2() {
		return inscriptionDate2 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDate2());
	}

	public void inputInscriptionDate2(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setInscriptionDate2 inputInscriptionScolaire", pk, "InscriptionDate2 w3-input w3-border ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_inscriptionDate2")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données.  (DD-MM-YYYY)")
			.a("value", inscriptionDate2 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(inscriptionDate2))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate2', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate2')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate2')); }); } ")
			.fg();
	}

	public void htmInscriptionDate2(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate2").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate2(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate2')); $('#", classeApiMethodeMethode, "_inscriptionDate2').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate2', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate2')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate2')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// inscriptionDate3 //
	//////////////////////

	/**	L'entité « inscriptionDate3 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate inscriptionDate3;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate3Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate3").o(inscriptionDate3);

	/**	<br/>L'entité « inscriptionDate3 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate3">Trouver l'entité inscriptionDate3 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDate3(Couverture<LocalDate> c);

	public LocalDate getInscriptionDate3() {
		return inscriptionDate3;
	}

	public void setInscriptionDate3(LocalDate inscriptionDate3) {
		this.inscriptionDate3 = inscriptionDate3;
		this.inscriptionDate3Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionDate3(Instant o) {
		this.inscriptionDate3 = LocalDate.from(o);
		this.inscriptionDate3Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate3(String o) {
		this.inscriptionDate3 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.inscriptionDate3Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate3(Date o) {
		this.inscriptionDate3 = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate3Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionDate3Init() {
		if(!inscriptionDate3Couverture.dejaInitialise) {
			_inscriptionDate3(inscriptionDate3Couverture);
			if(inscriptionDate3 == null)
				setInscriptionDate3(inscriptionDate3Couverture.o);
		}
		inscriptionDate3Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrInscriptionDate3() {
		return inscriptionDate3 == null ? null : Date.from(inscriptionDate3.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strInscriptionDate3() {
		return inscriptionDate3 == null ? "" : inscriptionDate3.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonInscriptionDate3() {
		return inscriptionDate3 == null ? "" : inscriptionDate3.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageInscriptionDate3() {
		return null;
	}

	public String htmTooltipInscriptionDate3() {
		return null;
	}

	public String htmInscriptionDate3() {
		return inscriptionDate3 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDate3());
	}

	public void inputInscriptionDate3(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setInscriptionDate3 inputInscriptionScolaire", pk, "InscriptionDate3 w3-input w3-border ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_inscriptionDate3")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données.  (DD-MM-YYYY)")
			.a("value", inscriptionDate3 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(inscriptionDate3))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate3', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate3')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate3')); }); } ")
			.fg();
	}

	public void htmInscriptionDate3(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate3").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate3(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate3')); $('#", classeApiMethodeMethode, "_inscriptionDate3').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate3', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate3')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate3')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// inscriptionDate4 //
	//////////////////////

	/**	L'entité « inscriptionDate4 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate inscriptionDate4;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate4Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate4").o(inscriptionDate4);

	/**	<br/>L'entité « inscriptionDate4 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate4">Trouver l'entité inscriptionDate4 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDate4(Couverture<LocalDate> c);

	public LocalDate getInscriptionDate4() {
		return inscriptionDate4;
	}

	public void setInscriptionDate4(LocalDate inscriptionDate4) {
		this.inscriptionDate4 = inscriptionDate4;
		this.inscriptionDate4Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionDate4(Instant o) {
		this.inscriptionDate4 = LocalDate.from(o);
		this.inscriptionDate4Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate4(String o) {
		this.inscriptionDate4 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.inscriptionDate4Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate4(Date o) {
		this.inscriptionDate4 = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate4Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionDate4Init() {
		if(!inscriptionDate4Couverture.dejaInitialise) {
			_inscriptionDate4(inscriptionDate4Couverture);
			if(inscriptionDate4 == null)
				setInscriptionDate4(inscriptionDate4Couverture.o);
		}
		inscriptionDate4Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrInscriptionDate4() {
		return inscriptionDate4 == null ? null : Date.from(inscriptionDate4.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strInscriptionDate4() {
		return inscriptionDate4 == null ? "" : inscriptionDate4.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonInscriptionDate4() {
		return inscriptionDate4 == null ? "" : inscriptionDate4.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageInscriptionDate4() {
		return null;
	}

	public String htmTooltipInscriptionDate4() {
		return null;
	}

	public String htmInscriptionDate4() {
		return inscriptionDate4 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDate4());
	}

	public void inputInscriptionDate4(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setInscriptionDate4 inputInscriptionScolaire", pk, "InscriptionDate4 w3-input w3-border ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_inscriptionDate4")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données.  (DD-MM-YYYY)")
			.a("value", inscriptionDate4 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(inscriptionDate4))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate4', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate4')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate4')); }); } ")
			.fg();
	}

	public void htmInscriptionDate4(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate4").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate4(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate4')); $('#", classeApiMethodeMethode, "_inscriptionDate4').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate4', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate4')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate4')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// inscriptionDate5 //
	//////////////////////

	/**	L'entité « inscriptionDate5 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate inscriptionDate5;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate5Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate5").o(inscriptionDate5);

	/**	<br/>L'entité « inscriptionDate5 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate5">Trouver l'entité inscriptionDate5 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDate5(Couverture<LocalDate> c);

	public LocalDate getInscriptionDate5() {
		return inscriptionDate5;
	}

	public void setInscriptionDate5(LocalDate inscriptionDate5) {
		this.inscriptionDate5 = inscriptionDate5;
		this.inscriptionDate5Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionDate5(Instant o) {
		this.inscriptionDate5 = LocalDate.from(o);
		this.inscriptionDate5Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate5(String o) {
		this.inscriptionDate5 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.inscriptionDate5Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate5(Date o) {
		this.inscriptionDate5 = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate5Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionDate5Init() {
		if(!inscriptionDate5Couverture.dejaInitialise) {
			_inscriptionDate5(inscriptionDate5Couverture);
			if(inscriptionDate5 == null)
				setInscriptionDate5(inscriptionDate5Couverture.o);
		}
		inscriptionDate5Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrInscriptionDate5() {
		return inscriptionDate5 == null ? null : Date.from(inscriptionDate5.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strInscriptionDate5() {
		return inscriptionDate5 == null ? "" : inscriptionDate5.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonInscriptionDate5() {
		return inscriptionDate5 == null ? "" : inscriptionDate5.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageInscriptionDate5() {
		return null;
	}

	public String htmTooltipInscriptionDate5() {
		return null;
	}

	public String htmInscriptionDate5() {
		return inscriptionDate5 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDate5());
	}

	public void inputInscriptionDate5(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setInscriptionDate5 inputInscriptionScolaire", pk, "InscriptionDate5 w3-input w3-border ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_inscriptionDate5")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données.  (DD-MM-YYYY)")
			.a("value", inscriptionDate5 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(inscriptionDate5))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate5', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate5')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate5')); }); } ")
			.fg();
	}

	public void htmInscriptionDate5(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate5").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate5(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate5')); $('#", classeApiMethodeMethode, "_inscriptionDate5').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate5', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate5')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate5')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// inscriptionDate6 //
	//////////////////////

	/**	L'entité « inscriptionDate6 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate inscriptionDate6;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate6Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate6").o(inscriptionDate6);

	/**	<br/>L'entité « inscriptionDate6 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate6">Trouver l'entité inscriptionDate6 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDate6(Couverture<LocalDate> c);

	public LocalDate getInscriptionDate6() {
		return inscriptionDate6;
	}

	public void setInscriptionDate6(LocalDate inscriptionDate6) {
		this.inscriptionDate6 = inscriptionDate6;
		this.inscriptionDate6Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionDate6(Instant o) {
		this.inscriptionDate6 = LocalDate.from(o);
		this.inscriptionDate6Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate6(String o) {
		this.inscriptionDate6 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.inscriptionDate6Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate6(Date o) {
		this.inscriptionDate6 = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate6Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionDate6Init() {
		if(!inscriptionDate6Couverture.dejaInitialise) {
			_inscriptionDate6(inscriptionDate6Couverture);
			if(inscriptionDate6 == null)
				setInscriptionDate6(inscriptionDate6Couverture.o);
		}
		inscriptionDate6Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrInscriptionDate6() {
		return inscriptionDate6 == null ? null : Date.from(inscriptionDate6.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strInscriptionDate6() {
		return inscriptionDate6 == null ? "" : inscriptionDate6.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonInscriptionDate6() {
		return inscriptionDate6 == null ? "" : inscriptionDate6.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageInscriptionDate6() {
		return null;
	}

	public String htmTooltipInscriptionDate6() {
		return null;
	}

	public String htmInscriptionDate6() {
		return inscriptionDate6 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDate6());
	}

	public void inputInscriptionDate6(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setInscriptionDate6 inputInscriptionScolaire", pk, "InscriptionDate6 w3-input w3-border ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_inscriptionDate6")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données.  (DD-MM-YYYY)")
			.a("value", inscriptionDate6 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(inscriptionDate6))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate6', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate6')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate6')); }); } ")
			.fg();
	}

	public void htmInscriptionDate6(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate6").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate6(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate6')); $('#", classeApiMethodeMethode, "_inscriptionDate6').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate6', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate6')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate6')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// inscriptionDate7 //
	//////////////////////

	/**	L'entité « inscriptionDate7 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate inscriptionDate7;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate7Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate7").o(inscriptionDate7);

	/**	<br/>L'entité « inscriptionDate7 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate7">Trouver l'entité inscriptionDate7 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDate7(Couverture<LocalDate> c);

	public LocalDate getInscriptionDate7() {
		return inscriptionDate7;
	}

	public void setInscriptionDate7(LocalDate inscriptionDate7) {
		this.inscriptionDate7 = inscriptionDate7;
		this.inscriptionDate7Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionDate7(Instant o) {
		this.inscriptionDate7 = LocalDate.from(o);
		this.inscriptionDate7Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate7(String o) {
		this.inscriptionDate7 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.inscriptionDate7Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate7(Date o) {
		this.inscriptionDate7 = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate7Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionDate7Init() {
		if(!inscriptionDate7Couverture.dejaInitialise) {
			_inscriptionDate7(inscriptionDate7Couverture);
			if(inscriptionDate7 == null)
				setInscriptionDate7(inscriptionDate7Couverture.o);
		}
		inscriptionDate7Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrInscriptionDate7() {
		return inscriptionDate7 == null ? null : Date.from(inscriptionDate7.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strInscriptionDate7() {
		return inscriptionDate7 == null ? "" : inscriptionDate7.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonInscriptionDate7() {
		return inscriptionDate7 == null ? "" : inscriptionDate7.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageInscriptionDate7() {
		return null;
	}

	public String htmTooltipInscriptionDate7() {
		return null;
	}

	public String htmInscriptionDate7() {
		return inscriptionDate7 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDate7());
	}

	public void inputInscriptionDate7(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setInscriptionDate7 inputInscriptionScolaire", pk, "InscriptionDate7 w3-input w3-border ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_inscriptionDate7")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données.  (DD-MM-YYYY)")
			.a("value", inscriptionDate7 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(inscriptionDate7))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate7', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate7')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate7')); }); } ")
			.fg();
	}

	public void htmInscriptionDate7(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate7").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate7(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate7')); $('#", classeApiMethodeMethode, "_inscriptionDate7').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate7', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate7')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate7')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// inscriptionDate8 //
	//////////////////////

	/**	L'entité « inscriptionDate8 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate inscriptionDate8;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate8Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate8").o(inscriptionDate8);

	/**	<br/>L'entité « inscriptionDate8 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate8">Trouver l'entité inscriptionDate8 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDate8(Couverture<LocalDate> c);

	public LocalDate getInscriptionDate8() {
		return inscriptionDate8;
	}

	public void setInscriptionDate8(LocalDate inscriptionDate8) {
		this.inscriptionDate8 = inscriptionDate8;
		this.inscriptionDate8Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionDate8(Instant o) {
		this.inscriptionDate8 = LocalDate.from(o);
		this.inscriptionDate8Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate8(String o) {
		this.inscriptionDate8 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.inscriptionDate8Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate8(Date o) {
		this.inscriptionDate8 = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate8Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionDate8Init() {
		if(!inscriptionDate8Couverture.dejaInitialise) {
			_inscriptionDate8(inscriptionDate8Couverture);
			if(inscriptionDate8 == null)
				setInscriptionDate8(inscriptionDate8Couverture.o);
		}
		inscriptionDate8Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrInscriptionDate8() {
		return inscriptionDate8 == null ? null : Date.from(inscriptionDate8.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strInscriptionDate8() {
		return inscriptionDate8 == null ? "" : inscriptionDate8.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonInscriptionDate8() {
		return inscriptionDate8 == null ? "" : inscriptionDate8.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageInscriptionDate8() {
		return null;
	}

	public String htmTooltipInscriptionDate8() {
		return null;
	}

	public String htmInscriptionDate8() {
		return inscriptionDate8 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDate8());
	}

	public void inputInscriptionDate8(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setInscriptionDate8 inputInscriptionScolaire", pk, "InscriptionDate8 w3-input w3-border ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_inscriptionDate8")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données.  (DD-MM-YYYY)")
			.a("value", inscriptionDate8 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(inscriptionDate8))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate8', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate8')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate8')); }); } ")
			.fg();
	}

	public void htmInscriptionDate8(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate8").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate8(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate8')); $('#", classeApiMethodeMethode, "_inscriptionDate8').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate8', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate8')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate8')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// inscriptionDate9 //
	//////////////////////

	/**	L'entité « inscriptionDate9 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate inscriptionDate9;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate9Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate9").o(inscriptionDate9);

	/**	<br/>L'entité « inscriptionDate9 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate9">Trouver l'entité inscriptionDate9 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDate9(Couverture<LocalDate> c);

	public LocalDate getInscriptionDate9() {
		return inscriptionDate9;
	}

	public void setInscriptionDate9(LocalDate inscriptionDate9) {
		this.inscriptionDate9 = inscriptionDate9;
		this.inscriptionDate9Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionDate9(Instant o) {
		this.inscriptionDate9 = LocalDate.from(o);
		this.inscriptionDate9Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate9(String o) {
		this.inscriptionDate9 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.inscriptionDate9Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate9(Date o) {
		this.inscriptionDate9 = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate9Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionDate9Init() {
		if(!inscriptionDate9Couverture.dejaInitialise) {
			_inscriptionDate9(inscriptionDate9Couverture);
			if(inscriptionDate9 == null)
				setInscriptionDate9(inscriptionDate9Couverture.o);
		}
		inscriptionDate9Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrInscriptionDate9() {
		return inscriptionDate9 == null ? null : Date.from(inscriptionDate9.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strInscriptionDate9() {
		return inscriptionDate9 == null ? "" : inscriptionDate9.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonInscriptionDate9() {
		return inscriptionDate9 == null ? "" : inscriptionDate9.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageInscriptionDate9() {
		return null;
	}

	public String htmTooltipInscriptionDate9() {
		return null;
	}

	public String htmInscriptionDate9() {
		return inscriptionDate9 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDate9());
	}

	public void inputInscriptionDate9(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setInscriptionDate9 inputInscriptionScolaire", pk, "InscriptionDate9 w3-input w3-border ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_inscriptionDate9")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données.  (DD-MM-YYYY)")
			.a("value", inscriptionDate9 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(inscriptionDate9))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate9', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate9')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate9')); }); } ")
			.fg();
	}

	public void htmInscriptionDate9(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate9").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate9(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate9')); $('#", classeApiMethodeMethode, "_inscriptionDate9').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate9', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate9')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate9')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////
	// inscriptionDate10 //
	///////////////////////

	/**	L'entité « inscriptionDate10 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected LocalDate inscriptionDate10;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate10Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate10").o(inscriptionDate10);

	/**	<br/>L'entité « inscriptionDate10 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate10">Trouver l'entité inscriptionDate10 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDate10(Couverture<LocalDate> c);

	public LocalDate getInscriptionDate10() {
		return inscriptionDate10;
	}

	public void setInscriptionDate10(LocalDate inscriptionDate10) {
		this.inscriptionDate10 = inscriptionDate10;
		this.inscriptionDate10Couverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionDate10(Instant o) {
		this.inscriptionDate10 = LocalDate.from(o);
		this.inscriptionDate10Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate10(String o) {
		this.inscriptionDate10 = LocalDate.parse(o, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		this.inscriptionDate10Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate10(Date o) {
		this.inscriptionDate10 = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate10Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionDate10Init() {
		if(!inscriptionDate10Couverture.dejaInitialise) {
			_inscriptionDate10(inscriptionDate10Couverture);
			if(inscriptionDate10 == null)
				setInscriptionDate10(inscriptionDate10Couverture.o);
		}
		inscriptionDate10Couverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrInscriptionDate10() {
		return inscriptionDate10 == null ? null : Date.from(inscriptionDate10.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strInscriptionDate10() {
		return inscriptionDate10 == null ? "" : inscriptionDate10.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE));
	}

	public String jsonInscriptionDate10() {
		return inscriptionDate10 == null ? "" : inscriptionDate10.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.FRANCE));
	}

	public String nomAffichageInscriptionDate10() {
		return null;
	}

	public String htmTooltipInscriptionDate10() {
		return null;
	}

	public String htmInscriptionDate10() {
		return inscriptionDate10 == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDate10());
	}

	public void inputInscriptionDate10(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		e("input")
			.a("type", "text")
			.a("class", "w3-input w3-border datepicker setInscriptionDate10 inputInscriptionScolaire", pk, "InscriptionDate10 w3-input w3-border ")
			.a("placeholder", "DD-MM-YYYY")
			.a("data-timeformat", "DD-MM-YYYY")
			.a("id", classeApiMethodeMethode, "_inscriptionDate10")
			.a("onclick", "enleverLueur($(this)); ")
			.a("title", "La clé primaire des contacts d'urgence dans la base de données.  (DD-MM-YYYY)")
			.a("value", inscriptionDate10 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.forLanguageTag("fr-FR")).format(inscriptionDate10))
			.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('MM/DD/YYYY'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate10', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate10')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate10')); }); } ")
			.fg();
	}

	public void htmInscriptionDate10(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate10").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate10(classeApiMethodeMethode);
							} g("div");
							if("Page".equals(classeApiMethodeMethode)) {
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate10')); $('#", classeApiMethodeMethode, "_inscriptionDate10').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate10', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate10')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate10')); }); ")
										.f();
										e("i").a("class", "far fa-eraser ").f().g("i");
									} g("button");
								} g("div");
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////////
	// inscriptionsInscription //
	/////////////////////////////

	/**	L'entité « inscriptionsInscription »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 */
	protected List<InscriptionScolaire> inscriptionsInscription = new java.util.ArrayList<org.computate.scolaire.frFR.inscription.InscriptionScolaire>();
	@JsonIgnore
	public Couverture<List<InscriptionScolaire>> inscriptionsInscriptionCouverture = new Couverture<List<InscriptionScolaire>>().p(this).c(List.class).var("inscriptionsInscription").o(inscriptionsInscription);

	/**	<br/>L'entité « inscriptionsInscription »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionsInscription">Trouver l'entité inscriptionsInscription dans Solr</a>
	 * <br/>
	 * @param inscriptionsInscription est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionsInscription(List<InscriptionScolaire> l);

	public List<InscriptionScolaire> getInscriptionsInscription() {
		return inscriptionsInscription;
	}

	public void setInscriptionsInscription(List<InscriptionScolaire> inscriptionsInscription) {
		this.inscriptionsInscription = inscriptionsInscription;
		this.inscriptionsInscriptionCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addInscriptionsInscription(InscriptionScolaire...objets) {
		for(InscriptionScolaire o : objets) {
			addInscriptionsInscription(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addInscriptionsInscription(InscriptionScolaire o) {
		if(o != null && !inscriptionsInscription.contains(o))
			this.inscriptionsInscription.add(o);
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionsInscriptionInit() {
		if(!inscriptionsInscriptionCouverture.dejaInitialise) {
			_inscriptionsInscription(inscriptionsInscription);
		}
		inscriptionsInscriptionCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	/////////////////////////////
	// enfantImmunisationsRecu //
	/////////////////////////////

	/**	L'entité « enfantImmunisationsRecu »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantImmunisationsRecu;
	@JsonIgnore
	public Couverture<String> enfantImmunisationsRecuCouverture = new Couverture<String>().p(this).c(String.class).var("enfantImmunisationsRecu").o(enfantImmunisationsRecu);

	/**	<br/>L'entité « enfantImmunisationsRecu »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantImmunisationsRecu">Trouver l'entité enfantImmunisationsRecu dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantImmunisationsRecu(Couverture<String> c);

	public String getEnfantImmunisationsRecu() {
		return enfantImmunisationsRecu;
	}

	public void setEnfantImmunisationsRecu(String enfantImmunisationsRecu) {
		this.enfantImmunisationsRecu = enfantImmunisationsRecu;
		this.enfantImmunisationsRecuCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire enfantImmunisationsRecuInit() {
		if(!enfantImmunisationsRecuCouverture.dejaInitialise) {
			_enfantImmunisationsRecu(enfantImmunisationsRecuCouverture);
			if(enfantImmunisationsRecu == null)
				setEnfantImmunisationsRecu(enfantImmunisationsRecuCouverture.o);
		}
		enfantImmunisationsRecuCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEnfantImmunisationsRecu() {
		return enfantImmunisationsRecu;
	}

	public String strEnfantImmunisationsRecu() {
		return enfantImmunisationsRecu == null ? "" : enfantImmunisationsRecu;
	}

	public String jsonEnfantImmunisationsRecu() {
		return enfantImmunisationsRecu == null ? "" : enfantImmunisationsRecu;
	}

	public String nomAffichageEnfantImmunisationsRecu() {
		return null;
	}

	public String htmTooltipEnfantImmunisationsRecu() {
		return null;
	}

	public String htmEnfantImmunisationsRecu() {
		return enfantImmunisationsRecu == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantImmunisationsRecu());
	}

	//////////////////////////
	// enfantPhotosApprouve //
	//////////////////////////

	/**	L'entité « enfantPhotosApprouve »
	 *	 is defined as null before being initialized. 
	 */
	protected String enfantPhotosApprouve;
	@JsonIgnore
	public Couverture<String> enfantPhotosApprouveCouverture = new Couverture<String>().p(this).c(String.class).var("enfantPhotosApprouve").o(enfantPhotosApprouve);

	/**	<br/>L'entité « enfantPhotosApprouve »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantPhotosApprouve">Trouver l'entité enfantPhotosApprouve dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantPhotosApprouve(Couverture<String> c);

	public String getEnfantPhotosApprouve() {
		return enfantPhotosApprouve;
	}

	public void setEnfantPhotosApprouve(String enfantPhotosApprouve) {
		this.enfantPhotosApprouve = enfantPhotosApprouve;
		this.enfantPhotosApprouveCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire enfantPhotosApprouveInit() {
		if(!enfantPhotosApprouveCouverture.dejaInitialise) {
			_enfantPhotosApprouve(enfantPhotosApprouveCouverture);
			if(enfantPhotosApprouve == null)
				setEnfantPhotosApprouve(enfantPhotosApprouveCouverture.o);
		}
		enfantPhotosApprouveCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEnfantPhotosApprouve() {
		return enfantPhotosApprouve;
	}

	public String strEnfantPhotosApprouve() {
		return enfantPhotosApprouve == null ? "" : enfantPhotosApprouve;
	}

	public String jsonEnfantPhotosApprouve() {
		return enfantPhotosApprouve == null ? "" : enfantPhotosApprouve;
	}

	public String nomAffichageEnfantPhotosApprouve() {
		return null;
	}

	public String htmTooltipEnfantPhotosApprouve() {
		return null;
	}

	public String htmEnfantPhotosApprouve() {
		return enfantPhotosApprouve == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantPhotosApprouve());
	}

	///////////////////////
	// inscriptionNumero //
	///////////////////////

	/**	L'entité « inscriptionNumero »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	protected Integer inscriptionNumero;
	@JsonIgnore
	public Couverture<Integer> inscriptionNumeroCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("inscriptionNumero").o(inscriptionNumero);

	/**	<br/>L'entité « inscriptionNumero »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNumero">Trouver l'entité inscriptionNumero dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionNumero(Couverture<Integer> c);

	public Integer getInscriptionNumero() {
		return inscriptionNumero;
	}

	public void setInscriptionNumero(Integer inscriptionNumero) {
		this.inscriptionNumero = inscriptionNumero;
		this.inscriptionNumeroCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionNumero(String o) {
		if(NumberUtils.isParsable(o))
			this.inscriptionNumero = Integer.parseInt(o);
		this.inscriptionNumeroCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionNumeroInit() {
		if(!inscriptionNumeroCouverture.dejaInitialise) {
			_inscriptionNumero(inscriptionNumeroCouverture);
			if(inscriptionNumero == null)
				setInscriptionNumero(inscriptionNumeroCouverture.o);
		}
		inscriptionNumeroCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrInscriptionNumero() {
		return inscriptionNumero;
	}

	public String strInscriptionNumero() {
		return inscriptionNumero == null ? "" : inscriptionNumero.toString();
	}

	public String jsonInscriptionNumero() {
		return inscriptionNumero == null ? "" : inscriptionNumero.toString();
	}

	public String nomAffichageInscriptionNumero() {
		return null;
	}

	public String htmTooltipInscriptionNumero() {
		return null;
	}

	public String htmInscriptionNumero() {
		return inscriptionNumero == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionNumero());
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
		initInscriptionScolaire();
		super.initLoinCluster(requeteSite_);
	}

	public void initInscriptionScolaire() {
		inscriptionCleInit();
		anneeCleInit();
		anneeRechercheInit();
		annee_Init();
		blocClesInit();
		blocRechercheInit();
		blocs_Init();
		saisons_Init();
		bloc_Init();
		ecoleCleInit();
		saisonCleInit();
		sessionCleInit();
		ageCleInit();
		blocCleInit();
		enfantCleInit();
		mereClesInit();
		pereClesInit();
		gardienClesInit();
		paiementClesInit();
		formInscriptionCleInit();
		scolaireTriInit();
		ecoleTriInit();
		anneeTriInit();
		saisonTriInit();
		sessionTriInit();
		ageTriInit();
		enfantRechercheInit();
		enfant_Init();
		mereRechercheInit();
		meresInit();
		pereRechercheInit();
		peresInit();
		gardienRechercheInit();
		gardiensInit();
		enfantPrenomInit();
		enfantPrenomPrefereInit();
		enfantFamilleNomInit();
		merePrenomInit();
		merePrenomPrefereInit();
		perePrenomInit();
		perePrenomPrefereInit();
		enfantNomCompletInit();
		enfantNomCompletPrefereInit();
		enfantDateNaissanceInit();
		ecoleNomInit();
		ecoleNomCompletInit();
		ecoleEmplacementInit();
		ecoleAddresseInit();
		ecoleNumeroTelephoneInit();
		ecoleAdministrateurNomInit();
		anneeDebutInit();
		anneeFinInit();
		saisonJourDebutInit();
		saisonEteInit();
		saisonHiverInit();
		anneeFraisInscriptionInit();
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
		blocPrixTotalInit();
		blocNomAdminInit();
		blocNomCourtInit();
		blocNomCompletInit();
		inscriptionApprouveInit();
		inscriptionImmunisationsInit();
		familleMarieInit();
		familleSepareInit();
		familleDivorceInit();
		familleAddresseInit();
		familleCommentVousConnaissezEcoleInit();
		inscriptionConsiderationsSpecialesInit();
		enfantConditionsMedicalesInit();
		enfantEcolesPrecedemmentFrequenteesInit();
		enfantDescriptionInit();
		enfantObjectifsInit();
		enfantPropreInit();
		inscriptionNomGroupeInit();
		inscriptionPaimentChaqueMoisInit();
		inscriptionPaimentCompletInit();
		inscriptionNomsParentsInit();
		inscriptionNomParentLignesInit();
		inscriptionMailParentLignesInit();
		inscriptionDetailParentLignesInit();
		inscriptionChercherParentLignesInit();
		inscriptionContactUrgenceParentLignesInit();
		inscriptionSignature1Init();
		inscriptionSignature2Init();
		inscriptionSignature3Init();
		inscriptionSignature4Init();
		inscriptionSignature5Init();
		inscriptionSignature6Init();
		inscriptionSignature7Init();
		inscriptionSignature8Init();
		inscriptionSignature9Init();
		inscriptionSignature10Init();
		inscriptionDate1Init();
		inscriptionDate2Init();
		inscriptionDate3Init();
		inscriptionDate4Init();
		inscriptionDate5Init();
		inscriptionDate6Init();
		inscriptionDate7Init();
		inscriptionDate8Init();
		inscriptionDate9Init();
		inscriptionDate10Init();
		inscriptionsInscriptionInit();
		enfantImmunisationsRecuInit();
		enfantPhotosApprouveInit();
		inscriptionNumeroInit();
		inscriptionNomCompletInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinInscriptionScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteInscriptionScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(anneeRecherche != null)
			anneeRecherche.setRequeteSite_(requeteSite_);
		if(blocRecherche != null)
			blocRecherche.setRequeteSite_(requeteSite_);
		if(enfantRecherche != null)
			enfantRecherche.setRequeteSite_(requeteSite_);
		if(mereRecherche != null)
			mereRecherche.setRequeteSite_(requeteSite_);
		if(pereRecherche != null)
			pereRecherche.setRequeteSite_(requeteSite_);
		if(gardienRecherche != null)
			gardienRecherche.setRequeteSite_(requeteSite_);
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
			case "anneeCle":
				return oInscriptionScolaire.anneeCle;
			case "anneeRecherche":
				return oInscriptionScolaire.anneeRecherche;
			case "annee_":
				return oInscriptionScolaire.annee_;
			case "blocCles":
				return oInscriptionScolaire.blocCles;
			case "blocRecherche":
				return oInscriptionScolaire.blocRecherche;
			case "blocs_":
				return oInscriptionScolaire.blocs_;
			case "saisons_":
				return oInscriptionScolaire.saisons_;
			case "bloc_":
				return oInscriptionScolaire.bloc_;
			case "ecoleCle":
				return oInscriptionScolaire.ecoleCle;
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
			case "formInscriptionCle":
				return oInscriptionScolaire.formInscriptionCle;
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
			case "mereRecherche":
				return oInscriptionScolaire.mereRecherche;
			case "meres":
				return oInscriptionScolaire.meres;
			case "pereRecherche":
				return oInscriptionScolaire.pereRecherche;
			case "peres":
				return oInscriptionScolaire.peres;
			case "gardienRecherche":
				return oInscriptionScolaire.gardienRecherche;
			case "gardiens":
				return oInscriptionScolaire.gardiens;
			case "enfantPrenom":
				return oInscriptionScolaire.enfantPrenom;
			case "enfantPrenomPrefere":
				return oInscriptionScolaire.enfantPrenomPrefere;
			case "enfantFamilleNom":
				return oInscriptionScolaire.enfantFamilleNom;
			case "merePrenom":
				return oInscriptionScolaire.merePrenom;
			case "merePrenomPrefere":
				return oInscriptionScolaire.merePrenomPrefere;
			case "perePrenom":
				return oInscriptionScolaire.perePrenom;
			case "perePrenomPrefere":
				return oInscriptionScolaire.perePrenomPrefere;
			case "enfantNomComplet":
				return oInscriptionScolaire.enfantNomComplet;
			case "enfantNomCompletPrefere":
				return oInscriptionScolaire.enfantNomCompletPrefere;
			case "enfantDateNaissance":
				return oInscriptionScolaire.enfantDateNaissance;
			case "ecoleNom":
				return oInscriptionScolaire.ecoleNom;
			case "ecoleNomComplet":
				return oInscriptionScolaire.ecoleNomComplet;
			case "ecoleEmplacement":
				return oInscriptionScolaire.ecoleEmplacement;
			case "ecoleAddresse":
				return oInscriptionScolaire.ecoleAddresse;
			case "ecoleNumeroTelephone":
				return oInscriptionScolaire.ecoleNumeroTelephone;
			case "ecoleAdministrateurNom":
				return oInscriptionScolaire.ecoleAdministrateurNom;
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
			case "anneeFraisInscription":
				return oInscriptionScolaire.anneeFraisInscription;
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
			case "blocPrixTotal":
				return oInscriptionScolaire.blocPrixTotal;
			case "blocNomAdmin":
				return oInscriptionScolaire.blocNomAdmin;
			case "blocNomCourt":
				return oInscriptionScolaire.blocNomCourt;
			case "blocNomComplet":
				return oInscriptionScolaire.blocNomComplet;
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
			case "enfantConditionsMedicales":
				return oInscriptionScolaire.enfantConditionsMedicales;
			case "enfantEcolesPrecedemmentFrequentees":
				return oInscriptionScolaire.enfantEcolesPrecedemmentFrequentees;
			case "enfantDescription":
				return oInscriptionScolaire.enfantDescription;
			case "enfantObjectifs":
				return oInscriptionScolaire.enfantObjectifs;
			case "enfantPropre":
				return oInscriptionScolaire.enfantPropre;
			case "inscriptionNomGroupe":
				return oInscriptionScolaire.inscriptionNomGroupe;
			case "inscriptionPaimentChaqueMois":
				return oInscriptionScolaire.inscriptionPaimentChaqueMois;
			case "inscriptionPaimentComplet":
				return oInscriptionScolaire.inscriptionPaimentComplet;
			case "inscriptionNomsParents":
				return oInscriptionScolaire.inscriptionNomsParents;
			case "inscriptionNomParentLignes":
				return oInscriptionScolaire.inscriptionNomParentLignes;
			case "inscriptionMailParentLignes":
				return oInscriptionScolaire.inscriptionMailParentLignes;
			case "inscriptionDetailParentLignes":
				return oInscriptionScolaire.inscriptionDetailParentLignes;
			case "inscriptionChercherParentLignes":
				return oInscriptionScolaire.inscriptionChercherParentLignes;
			case "inscriptionContactUrgenceParentLignes":
				return oInscriptionScolaire.inscriptionContactUrgenceParentLignes;
			case "inscriptionSignature1":
				return oInscriptionScolaire.inscriptionSignature1;
			case "inscriptionSignature2":
				return oInscriptionScolaire.inscriptionSignature2;
			case "inscriptionSignature3":
				return oInscriptionScolaire.inscriptionSignature3;
			case "inscriptionSignature4":
				return oInscriptionScolaire.inscriptionSignature4;
			case "inscriptionSignature5":
				return oInscriptionScolaire.inscriptionSignature5;
			case "inscriptionSignature6":
				return oInscriptionScolaire.inscriptionSignature6;
			case "inscriptionSignature7":
				return oInscriptionScolaire.inscriptionSignature7;
			case "inscriptionSignature8":
				return oInscriptionScolaire.inscriptionSignature8;
			case "inscriptionSignature9":
				return oInscriptionScolaire.inscriptionSignature9;
			case "inscriptionSignature10":
				return oInscriptionScolaire.inscriptionSignature10;
			case "inscriptionDate1":
				return oInscriptionScolaire.inscriptionDate1;
			case "inscriptionDate2":
				return oInscriptionScolaire.inscriptionDate2;
			case "inscriptionDate3":
				return oInscriptionScolaire.inscriptionDate3;
			case "inscriptionDate4":
				return oInscriptionScolaire.inscriptionDate4;
			case "inscriptionDate5":
				return oInscriptionScolaire.inscriptionDate5;
			case "inscriptionDate6":
				return oInscriptionScolaire.inscriptionDate6;
			case "inscriptionDate7":
				return oInscriptionScolaire.inscriptionDate7;
			case "inscriptionDate8":
				return oInscriptionScolaire.inscriptionDate8;
			case "inscriptionDate9":
				return oInscriptionScolaire.inscriptionDate9;
			case "inscriptionDate10":
				return oInscriptionScolaire.inscriptionDate10;
			case "inscriptionsInscription":
				return oInscriptionScolaire.inscriptionsInscription;
			case "enfantImmunisationsRecu":
				return oInscriptionScolaire.enfantImmunisationsRecu;
			case "enfantPhotosApprouve":
				return oInscriptionScolaire.enfantPhotosApprouve;
			case "inscriptionNumero":
				return oInscriptionScolaire.inscriptionNumero;
			case "inscriptionNomComplet":
				return oInscriptionScolaire.inscriptionNomComplet;
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
			case "anneeCle":
				oInscriptionScolaire.setAnneeCle((Long)val);
				return val;
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
			case "enfantNomComplet":
				setEnfantNomComplet(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "enfantNomCompletPrefere":
				setEnfantNomCompletPrefere(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "enfantDateNaissance":
				setEnfantDateNaissance(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "ecoleAddresse":
				setEcoleAddresse(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
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
			case "enfantConditionsMedicales":
				setEnfantConditionsMedicales(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "enfantEcolesPrecedemmentFrequentees":
				setEnfantEcolesPrecedemmentFrequentees(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "enfantDescription":
				setEnfantDescription(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "enfantObjectifs":
				setEnfantObjectifs(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "enfantPropre":
				setEnfantPropre(val);
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
			case "inscriptionNomsParents":
				setInscriptionNomsParents(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionSignature1":
				setInscriptionSignature1(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionSignature2":
				setInscriptionSignature2(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionSignature3":
				setInscriptionSignature3(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionSignature4":
				setInscriptionSignature4(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionSignature5":
				setInscriptionSignature5(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionSignature6":
				setInscriptionSignature6(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionSignature7":
				setInscriptionSignature7(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionSignature8":
				setInscriptionSignature8(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionSignature9":
				setInscriptionSignature9(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionSignature10":
				setInscriptionSignature10(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionDate1":
				setInscriptionDate1(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionDate2":
				setInscriptionDate2(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionDate3":
				setInscriptionDate3(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionDate4":
				setInscriptionDate4(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionDate5":
				setInscriptionDate5(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionDate6":
				setInscriptionDate6(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionDate7":
				setInscriptionDate7(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionDate8":
				setInscriptionDate8(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionDate9":
				setInscriptionDate9(val);
				sauvegardesInscriptionScolaire.add(var);
				return val;
			case "inscriptionDate10":
				setInscriptionDate10(val);
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

			Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
			if(anneeCle != null)
				oInscriptionScolaire.setAnneeCle(anneeCle);

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

			if(sauvegardesInscriptionScolaire.contains("formInscriptionCle")) {
				Long formInscriptionCle = (Long)solrDocument.get("formInscriptionCle_stored_long");
				if(formInscriptionCle != null)
					oInscriptionScolaire.setFormInscriptionCle(formInscriptionCle);
			}

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

			if(sauvegardesInscriptionScolaire.contains("enfantPrenom")) {
				String enfantPrenom = (String)solrDocument.get("enfantPrenom_stored_string");
				if(enfantPrenom != null)
					oInscriptionScolaire.setEnfantPrenom(enfantPrenom);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantPrenomPrefere")) {
				String enfantPrenomPrefere = (String)solrDocument.get("enfantPrenomPrefere_stored_string");
				if(enfantPrenomPrefere != null)
					oInscriptionScolaire.setEnfantPrenomPrefere(enfantPrenomPrefere);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantFamilleNom")) {
				String enfantFamilleNom = (String)solrDocument.get("enfantFamilleNom_stored_string");
				if(enfantFamilleNom != null)
					oInscriptionScolaire.setEnfantFamilleNom(enfantFamilleNom);
			}

			if(sauvegardesInscriptionScolaire.contains("merePrenom")) {
				String merePrenom = (String)solrDocument.get("merePrenom_stored_string");
				if(merePrenom != null)
					oInscriptionScolaire.setMerePrenom(merePrenom);
			}

			if(sauvegardesInscriptionScolaire.contains("merePrenomPrefere")) {
				String merePrenomPrefere = (String)solrDocument.get("merePrenomPrefere_stored_string");
				if(merePrenomPrefere != null)
					oInscriptionScolaire.setMerePrenomPrefere(merePrenomPrefere);
			}

			if(sauvegardesInscriptionScolaire.contains("perePrenom")) {
				String perePrenom = (String)solrDocument.get("perePrenom_stored_string");
				if(perePrenom != null)
					oInscriptionScolaire.setPerePrenom(perePrenom);
			}

			if(sauvegardesInscriptionScolaire.contains("perePrenomPrefere")) {
				String perePrenomPrefere = (String)solrDocument.get("perePrenomPrefere_stored_string");
				if(perePrenomPrefere != null)
					oInscriptionScolaire.setPerePrenomPrefere(perePrenomPrefere);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantNomComplet")) {
				String enfantNomComplet = (String)solrDocument.get("enfantNomComplet_stored_string");
				if(enfantNomComplet != null)
					oInscriptionScolaire.setEnfantNomComplet(enfantNomComplet);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantNomCompletPrefere")) {
				String enfantNomCompletPrefere = (String)solrDocument.get("enfantNomCompletPrefere_stored_string");
				if(enfantNomCompletPrefere != null)
					oInscriptionScolaire.setEnfantNomCompletPrefere(enfantNomCompletPrefere);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantDateNaissance")) {
				Date enfantDateNaissance = (Date)solrDocument.get("enfantDateNaissance_stored_date");
				if(enfantDateNaissance != null)
					oInscriptionScolaire.setEnfantDateNaissance(enfantDateNaissance);
			}

			if(sauvegardesInscriptionScolaire.contains("ecoleNom")) {
				String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
				if(ecoleNom != null)
					oInscriptionScolaire.setEcoleNom(ecoleNom);
			}

			if(sauvegardesInscriptionScolaire.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oInscriptionScolaire.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardesInscriptionScolaire.contains("ecoleEmplacement")) {
				String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
				if(ecoleEmplacement != null)
					oInscriptionScolaire.setEcoleEmplacement(ecoleEmplacement);
			}

			if(sauvegardesInscriptionScolaire.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oInscriptionScolaire.setEcoleAddresse(ecoleAddresse);
			}

			if(sauvegardesInscriptionScolaire.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oInscriptionScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardesInscriptionScolaire.contains("ecoleAdministrateurNom")) {
				String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
				if(ecoleAdministrateurNom != null)
					oInscriptionScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);
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

			if(sauvegardesInscriptionScolaire.contains("anneeFraisInscription")) {
				Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
				if(anneeFraisInscription != null)
					oInscriptionScolaire.setAnneeFraisInscription(anneeFraisInscription);
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

			if(sauvegardesInscriptionScolaire.contains("blocPrixTotal")) {
				Double blocPrixTotal = (Double)solrDocument.get("blocPrixTotal_stored_double");
				if(blocPrixTotal != null)
					oInscriptionScolaire.setBlocPrixTotal(blocPrixTotal);
			}

			if(sauvegardesInscriptionScolaire.contains("blocNomAdmin")) {
				String blocNomAdmin = (String)solrDocument.get("blocNomAdmin_stored_string");
				if(blocNomAdmin != null)
					oInscriptionScolaire.setBlocNomAdmin(blocNomAdmin);
			}

			if(sauvegardesInscriptionScolaire.contains("blocNomCourt")) {
				String blocNomCourt = (String)solrDocument.get("blocNomCourt_stored_string");
				if(blocNomCourt != null)
					oInscriptionScolaire.setBlocNomCourt(blocNomCourt);
			}

			if(sauvegardesInscriptionScolaire.contains("blocNomComplet")) {
				String blocNomComplet = (String)solrDocument.get("blocNomComplet_stored_string");
				if(blocNomComplet != null)
					oInscriptionScolaire.setBlocNomComplet(blocNomComplet);
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

			if(sauvegardesInscriptionScolaire.contains("enfantConditionsMedicales")) {
				String enfantConditionsMedicales = (String)solrDocument.get("enfantConditionsMedicales_stored_string");
				if(enfantConditionsMedicales != null)
					oInscriptionScolaire.setEnfantConditionsMedicales(enfantConditionsMedicales);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantEcolesPrecedemmentFrequentees")) {
				String enfantEcolesPrecedemmentFrequentees = (String)solrDocument.get("enfantEcolesPrecedemmentFrequentees_stored_string");
				if(enfantEcolesPrecedemmentFrequentees != null)
					oInscriptionScolaire.setEnfantEcolesPrecedemmentFrequentees(enfantEcolesPrecedemmentFrequentees);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantDescription")) {
				String enfantDescription = (String)solrDocument.get("enfantDescription_stored_string");
				if(enfantDescription != null)
					oInscriptionScolaire.setEnfantDescription(enfantDescription);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantObjectifs")) {
				String enfantObjectifs = (String)solrDocument.get("enfantObjectifs_stored_string");
				if(enfantObjectifs != null)
					oInscriptionScolaire.setEnfantObjectifs(enfantObjectifs);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantPropre")) {
				Boolean enfantPropre = (Boolean)solrDocument.get("enfantPropre_stored_boolean");
				if(enfantPropre != null)
					oInscriptionScolaire.setEnfantPropre(enfantPropre);
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

			if(sauvegardesInscriptionScolaire.contains("inscriptionNomsParents")) {
				String inscriptionNomsParents = (String)solrDocument.get("inscriptionNomsParents_stored_string");
				if(inscriptionNomsParents != null)
					oInscriptionScolaire.setInscriptionNomsParents(inscriptionNomsParents);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionNomParentLignes")) {
				String inscriptionNomParentLignes = (String)solrDocument.get("inscriptionNomParentLignes_stored_string");
				if(inscriptionNomParentLignes != null)
					oInscriptionScolaire.setInscriptionNomParentLignes(inscriptionNomParentLignes);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionMailParentLignes")) {
				String inscriptionMailParentLignes = (String)solrDocument.get("inscriptionMailParentLignes_stored_string");
				if(inscriptionMailParentLignes != null)
					oInscriptionScolaire.setInscriptionMailParentLignes(inscriptionMailParentLignes);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionDetailParentLignes")) {
				String inscriptionDetailParentLignes = (String)solrDocument.get("inscriptionDetailParentLignes_stored_string");
				if(inscriptionDetailParentLignes != null)
					oInscriptionScolaire.setInscriptionDetailParentLignes(inscriptionDetailParentLignes);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionChercherParentLignes")) {
				String inscriptionChercherParentLignes = (String)solrDocument.get("inscriptionChercherParentLignes_stored_string");
				if(inscriptionChercherParentLignes != null)
					oInscriptionScolaire.setInscriptionChercherParentLignes(inscriptionChercherParentLignes);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionContactUrgenceParentLignes")) {
				String inscriptionContactUrgenceParentLignes = (String)solrDocument.get("inscriptionContactUrgenceParentLignes_stored_string");
				if(inscriptionContactUrgenceParentLignes != null)
					oInscriptionScolaire.setInscriptionContactUrgenceParentLignes(inscriptionContactUrgenceParentLignes);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionSignature1")) {
				String inscriptionSignature1 = (String)solrDocument.get("inscriptionSignature1_stored_string");
				if(inscriptionSignature1 != null)
					oInscriptionScolaire.setInscriptionSignature1(inscriptionSignature1);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionSignature2")) {
				String inscriptionSignature2 = (String)solrDocument.get("inscriptionSignature2_stored_string");
				if(inscriptionSignature2 != null)
					oInscriptionScolaire.setInscriptionSignature2(inscriptionSignature2);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionSignature3")) {
				String inscriptionSignature3 = (String)solrDocument.get("inscriptionSignature3_stored_string");
				if(inscriptionSignature3 != null)
					oInscriptionScolaire.setInscriptionSignature3(inscriptionSignature3);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionSignature4")) {
				String inscriptionSignature4 = (String)solrDocument.get("inscriptionSignature4_stored_string");
				if(inscriptionSignature4 != null)
					oInscriptionScolaire.setInscriptionSignature4(inscriptionSignature4);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionSignature5")) {
				String inscriptionSignature5 = (String)solrDocument.get("inscriptionSignature5_stored_string");
				if(inscriptionSignature5 != null)
					oInscriptionScolaire.setInscriptionSignature5(inscriptionSignature5);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionSignature6")) {
				String inscriptionSignature6 = (String)solrDocument.get("inscriptionSignature6_stored_string");
				if(inscriptionSignature6 != null)
					oInscriptionScolaire.setInscriptionSignature6(inscriptionSignature6);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionSignature7")) {
				String inscriptionSignature7 = (String)solrDocument.get("inscriptionSignature7_stored_string");
				if(inscriptionSignature7 != null)
					oInscriptionScolaire.setInscriptionSignature7(inscriptionSignature7);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionSignature8")) {
				String inscriptionSignature8 = (String)solrDocument.get("inscriptionSignature8_stored_string");
				if(inscriptionSignature8 != null)
					oInscriptionScolaire.setInscriptionSignature8(inscriptionSignature8);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionSignature9")) {
				String inscriptionSignature9 = (String)solrDocument.get("inscriptionSignature9_stored_string");
				if(inscriptionSignature9 != null)
					oInscriptionScolaire.setInscriptionSignature9(inscriptionSignature9);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionSignature10")) {
				String inscriptionSignature10 = (String)solrDocument.get("inscriptionSignature10_stored_string");
				if(inscriptionSignature10 != null)
					oInscriptionScolaire.setInscriptionSignature10(inscriptionSignature10);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionDate1")) {
				Date inscriptionDate1 = (Date)solrDocument.get("inscriptionDate1_stored_date");
				if(inscriptionDate1 != null)
					oInscriptionScolaire.setInscriptionDate1(inscriptionDate1);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionDate2")) {
				Date inscriptionDate2 = (Date)solrDocument.get("inscriptionDate2_stored_date");
				if(inscriptionDate2 != null)
					oInscriptionScolaire.setInscriptionDate2(inscriptionDate2);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionDate3")) {
				Date inscriptionDate3 = (Date)solrDocument.get("inscriptionDate3_stored_date");
				if(inscriptionDate3 != null)
					oInscriptionScolaire.setInscriptionDate3(inscriptionDate3);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionDate4")) {
				Date inscriptionDate4 = (Date)solrDocument.get("inscriptionDate4_stored_date");
				if(inscriptionDate4 != null)
					oInscriptionScolaire.setInscriptionDate4(inscriptionDate4);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionDate5")) {
				Date inscriptionDate5 = (Date)solrDocument.get("inscriptionDate5_stored_date");
				if(inscriptionDate5 != null)
					oInscriptionScolaire.setInscriptionDate5(inscriptionDate5);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionDate6")) {
				Date inscriptionDate6 = (Date)solrDocument.get("inscriptionDate6_stored_date");
				if(inscriptionDate6 != null)
					oInscriptionScolaire.setInscriptionDate6(inscriptionDate6);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionDate7")) {
				Date inscriptionDate7 = (Date)solrDocument.get("inscriptionDate7_stored_date");
				if(inscriptionDate7 != null)
					oInscriptionScolaire.setInscriptionDate7(inscriptionDate7);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionDate8")) {
				Date inscriptionDate8 = (Date)solrDocument.get("inscriptionDate8_stored_date");
				if(inscriptionDate8 != null)
					oInscriptionScolaire.setInscriptionDate8(inscriptionDate8);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionDate9")) {
				Date inscriptionDate9 = (Date)solrDocument.get("inscriptionDate9_stored_date");
				if(inscriptionDate9 != null)
					oInscriptionScolaire.setInscriptionDate9(inscriptionDate9);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionDate10")) {
				Date inscriptionDate10 = (Date)solrDocument.get("inscriptionDate10_stored_date");
				if(inscriptionDate10 != null)
					oInscriptionScolaire.setInscriptionDate10(inscriptionDate10);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantImmunisationsRecu")) {
				String enfantImmunisationsRecu = (String)solrDocument.get("enfantImmunisationsRecu_stored_string");
				if(enfantImmunisationsRecu != null)
					oInscriptionScolaire.setEnfantImmunisationsRecu(enfantImmunisationsRecu);
			}

			if(sauvegardesInscriptionScolaire.contains("enfantPhotosApprouve")) {
				String enfantPhotosApprouve = (String)solrDocument.get("enfantPhotosApprouve_stored_string");
				if(enfantPhotosApprouve != null)
					oInscriptionScolaire.setEnfantPhotosApprouve(enfantPhotosApprouve);
			}

			if(sauvegardesInscriptionScolaire.contains("inscriptionNomComplet")) {
				String inscriptionNomComplet = (String)solrDocument.get("inscriptionNomComplet_stored_string");
				if(inscriptionNomComplet != null)
					oInscriptionScolaire.setInscriptionNomComplet(inscriptionNomComplet);
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
			clientSolr.commit(false, false, true);
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
			clientSolr.commit(false, false, true);
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
		if(anneeCle != null) {
			document.addField("anneeCle_indexed_long", anneeCle);
			document.addField("anneeCle_stored_long", anneeCle);
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
		if(formInscriptionCle != null) {
			document.addField("formInscriptionCle_indexed_long", formInscriptionCle);
			document.addField("formInscriptionCle_stored_long", formInscriptionCle);
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
		if(enfantPrenom != null) {
			document.addField("enfantPrenom_indexed_string", enfantPrenom);
			document.addField("enfantPrenom_stored_string", enfantPrenom);
		}
		if(enfantPrenomPrefere != null) {
			document.addField("enfantPrenomPrefere_indexed_string", enfantPrenomPrefere);
			document.addField("enfantPrenomPrefere_stored_string", enfantPrenomPrefere);
		}
		if(enfantFamilleNom != null) {
			document.addField("enfantFamilleNom_indexed_string", enfantFamilleNom);
			document.addField("enfantFamilleNom_stored_string", enfantFamilleNom);
		}
		if(merePrenom != null) {
			document.addField("merePrenom_indexed_string", merePrenom);
			document.addField("merePrenom_stored_string", merePrenom);
		}
		if(merePrenomPrefere != null) {
			document.addField("merePrenomPrefere_indexed_string", merePrenomPrefere);
			document.addField("merePrenomPrefere_stored_string", merePrenomPrefere);
		}
		if(perePrenom != null) {
			document.addField("perePrenom_indexed_string", perePrenom);
			document.addField("perePrenom_stored_string", perePrenom);
		}
		if(perePrenomPrefere != null) {
			document.addField("perePrenomPrefere_indexed_string", perePrenomPrefere);
			document.addField("perePrenomPrefere_stored_string", perePrenomPrefere);
		}
		if(enfantNomComplet != null) {
			document.addField("enfantNomComplet_indexed_string", enfantNomComplet);
			document.addField("enfantNomComplet_stored_string", enfantNomComplet);
		}
		if(enfantNomCompletPrefere != null) {
			document.addField("enfantNomCompletPrefere_indexed_string", enfantNomCompletPrefere);
			document.addField("enfantNomCompletPrefere_stored_string", enfantNomCompletPrefere);
		}
		if(enfantDateNaissance != null) {
			document.addField("enfantDateNaissance_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enfantDateNaissance.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enfantDateNaissance_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enfantDateNaissance.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(ecoleNom != null) {
			document.addField("ecoleNom_indexed_string", ecoleNom);
			document.addField("ecoleNom_stored_string", ecoleNom);
		}
		if(ecoleNomComplet != null) {
			document.addField("ecoleNomComplet_indexed_string", ecoleNomComplet);
			document.addField("ecoleNomComplet_stored_string", ecoleNomComplet);
		}
		if(ecoleEmplacement != null) {
			document.addField("ecoleEmplacement_indexed_string", ecoleEmplacement);
			document.addField("ecoleEmplacement_stored_string", ecoleEmplacement);
		}
		if(ecoleAddresse != null) {
			document.addField("ecoleAddresse_indexed_string", ecoleAddresse);
			document.addField("ecoleAddresse_stored_string", ecoleAddresse);
		}
		if(ecoleNumeroTelephone != null) {
			document.addField("ecoleNumeroTelephone_indexed_string", ecoleNumeroTelephone);
			document.addField("ecoleNumeroTelephone_stored_string", ecoleNumeroTelephone);
		}
		if(ecoleAdministrateurNom != null) {
			document.addField("ecoleAdministrateurNom_indexed_string", ecoleAdministrateurNom);
			document.addField("ecoleAdministrateurNom_stored_string", ecoleAdministrateurNom);
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
		if(anneeFraisInscription != null) {
			document.addField("anneeFraisInscription_indexed_double", anneeFraisInscription.doubleValue());
			document.addField("anneeFraisInscription_stored_double", anneeFraisInscription.doubleValue());
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
		if(blocPrixTotal != null) {
			document.addField("blocPrixTotal_indexed_double", blocPrixTotal.doubleValue());
			document.addField("blocPrixTotal_stored_double", blocPrixTotal.doubleValue());
		}
		if(blocNomAdmin != null) {
			document.addField("blocNomAdmin_indexed_string", blocNomAdmin);
			document.addField("blocNomAdmin_stored_string", blocNomAdmin);
		}
		if(blocNomCourt != null) {
			document.addField("blocNomCourt_indexed_string", blocNomCourt);
			document.addField("blocNomCourt_stored_string", blocNomCourt);
		}
		if(blocNomComplet != null) {
			document.addField("blocNomComplet_indexed_string", blocNomComplet);
			document.addField("blocNomComplet_stored_string", blocNomComplet);
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
		if(enfantPropre != null) {
			document.addField("enfantPropre_indexed_boolean", enfantPropre);
			document.addField("enfantPropre_stored_boolean", enfantPropre);
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
		if(inscriptionNomsParents != null) {
			document.addField("inscriptionNomsParents_indexed_string", inscriptionNomsParents);
			document.addField("inscriptionNomsParents_stored_string", inscriptionNomsParents);
		}
		if(inscriptionNomParentLignes != null) {
			document.addField("inscriptionNomParentLignes_stored_string", inscriptionNomParentLignes);
		}
		if(inscriptionMailParentLignes != null) {
			document.addField("inscriptionMailParentLignes_stored_string", inscriptionMailParentLignes);
		}
		if(inscriptionDetailParentLignes != null) {
			document.addField("inscriptionDetailParentLignes_stored_string", inscriptionDetailParentLignes);
		}
		if(inscriptionChercherParentLignes != null) {
			document.addField("inscriptionChercherParentLignes_stored_string", inscriptionChercherParentLignes);
		}
		if(inscriptionContactUrgenceParentLignes != null) {
			document.addField("inscriptionContactUrgenceParentLignes_stored_string", inscriptionContactUrgenceParentLignes);
		}
		if(inscriptionSignature1 != null) {
			document.addField("inscriptionSignature1_stored_string", inscriptionSignature1);
		}
		if(inscriptionSignature2 != null) {
			document.addField("inscriptionSignature2_stored_string", inscriptionSignature2);
		}
		if(inscriptionSignature3 != null) {
			document.addField("inscriptionSignature3_stored_string", inscriptionSignature3);
		}
		if(inscriptionSignature4 != null) {
			document.addField("inscriptionSignature4_stored_string", inscriptionSignature4);
		}
		if(inscriptionSignature5 != null) {
			document.addField("inscriptionSignature5_stored_string", inscriptionSignature5);
		}
		if(inscriptionSignature6 != null) {
			document.addField("inscriptionSignature6_stored_string", inscriptionSignature6);
		}
		if(inscriptionSignature7 != null) {
			document.addField("inscriptionSignature7_stored_string", inscriptionSignature7);
		}
		if(inscriptionSignature8 != null) {
			document.addField("inscriptionSignature8_stored_string", inscriptionSignature8);
		}
		if(inscriptionSignature9 != null) {
			document.addField("inscriptionSignature9_stored_string", inscriptionSignature9);
		}
		if(inscriptionSignature10 != null) {
			document.addField("inscriptionSignature10_stored_string", inscriptionSignature10);
		}
		if(inscriptionDate1 != null) {
			document.addField("inscriptionDate1_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate1.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate1_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate1.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate2 != null) {
			document.addField("inscriptionDate2_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate2.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate2_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate2.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate3 != null) {
			document.addField("inscriptionDate3_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate3.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate3_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate3.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate4 != null) {
			document.addField("inscriptionDate4_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate4.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate4_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate4.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate5 != null) {
			document.addField("inscriptionDate5_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate5.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate5_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate5.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate6 != null) {
			document.addField("inscriptionDate6_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate6.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate6_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate6.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate7 != null) {
			document.addField("inscriptionDate7_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate7.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate7_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate7.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate8 != null) {
			document.addField("inscriptionDate8_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate8.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate8_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate8.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate9 != null) {
			document.addField("inscriptionDate9_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate9.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate9_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate9.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate10 != null) {
			document.addField("inscriptionDate10_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate10.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate10_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate10.atStartOfDay(ZoneId.systemDefault()).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enfantImmunisationsRecu != null) {
			document.addField("enfantImmunisationsRecu_indexed_string", enfantImmunisationsRecu);
			document.addField("enfantImmunisationsRecu_stored_string", enfantImmunisationsRecu);
		}
		if(enfantPhotosApprouve != null) {
			document.addField("enfantPhotosApprouve_indexed_string", enfantPhotosApprouve);
			document.addField("enfantPhotosApprouve_stored_string", enfantPhotosApprouve);
		}
		if(inscriptionNomComplet != null) {
			document.addField("inscriptionNomComplet_indexed_string", inscriptionNomComplet);
			document.addField("inscriptionNomComplet_stored_string", inscriptionNomComplet);
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
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeInscriptionScolaire(String entiteVar) {
		switch(entiteVar) {
			case "inscriptionCle":
				return "inscriptionCle_indexed_long";
			case "anneeCle":
				return "anneeCle_indexed_long";
			case "blocCles":
				return "blocCles_indexed_longs";
			case "ecoleCle":
				return "ecoleCle_indexed_long";
			case "saisonCle":
				return "saisonCle_indexed_long";
			case "sessionCle":
				return "sessionCle_indexed_long";
			case "ageCle":
				return "ageCle_indexed_long";
			case "blocCle":
				return "blocCle_indexed_long";
			case "enfantCle":
				return "enfantCle_indexed_long";
			case "mereCles":
				return "mereCles_indexed_longs";
			case "pereCles":
				return "pereCles_indexed_longs";
			case "gardienCles":
				return "gardienCles_indexed_longs";
			case "paiementCles":
				return "paiementCles_indexed_longs";
			case "formInscriptionCle":
				return "formInscriptionCle_indexed_long";
			case "scolaireTri":
				return "scolaireTri_indexed_int";
			case "ecoleTri":
				return "ecoleTri_indexed_int";
			case "anneeTri":
				return "anneeTri_indexed_int";
			case "saisonTri":
				return "saisonTri_indexed_int";
			case "sessionTri":
				return "sessionTri_indexed_int";
			case "ageTri":
				return "ageTri_indexed_int";
			case "enfantPrenom":
				return "enfantPrenom_indexed_string";
			case "enfantPrenomPrefere":
				return "enfantPrenomPrefere_indexed_string";
			case "enfantFamilleNom":
				return "enfantFamilleNom_indexed_string";
			case "merePrenom":
				return "merePrenom_indexed_string";
			case "merePrenomPrefere":
				return "merePrenomPrefere_indexed_string";
			case "perePrenom":
				return "perePrenom_indexed_string";
			case "perePrenomPrefere":
				return "perePrenomPrefere_indexed_string";
			case "enfantNomComplet":
				return "enfantNomComplet_indexed_string";
			case "enfantNomCompletPrefere":
				return "enfantNomCompletPrefere_indexed_string";
			case "enfantDateNaissance":
				return "enfantDateNaissance_indexed_date";
			case "ecoleNom":
				return "ecoleNom_indexed_string";
			case "ecoleNomComplet":
				return "ecoleNomComplet_indexed_string";
			case "ecoleEmplacement":
				return "ecoleEmplacement_indexed_string";
			case "ecoleAddresse":
				return "ecoleAddresse_indexed_string";
			case "ecoleNumeroTelephone":
				return "ecoleNumeroTelephone_indexed_string";
			case "ecoleAdministrateurNom":
				return "ecoleAdministrateurNom_indexed_string";
			case "anneeDebut":
				return "anneeDebut_indexed_int";
			case "anneeFin":
				return "anneeFin_indexed_int";
			case "saisonJourDebut":
				return "saisonJourDebut_indexed_date";
			case "saisonEte":
				return "saisonEte_indexed_boolean";
			case "saisonHiver":
				return "saisonHiver_indexed_boolean";
			case "anneeFraisInscription":
				return "anneeFraisInscription_indexed_double";
			case "saisonNomComplet":
				return "saisonNomComplet_indexed_string";
			case "sessionJourDebut":
				return "sessionJourDebut_indexed_date";
			case "sessionJourFin":
				return "sessionJourFin_indexed_date";
			case "ageNomComplet":
				return "ageNomComplet_indexed_string";
			case "ageDebut":
				return "ageDebut_indexed_int";
			case "ageFin":
				return "ageFin_indexed_int";
			case "blocHeureDebut":
				return "blocHeureDebut_indexed_string";
			case "blocHeureFin":
				return "blocHeureFin_indexed_string";
			case "blocPrixParMois":
				return "blocPrixParMois_indexed_double";
			case "blocDimanche":
				return "blocDimanche_indexed_boolean";
			case "blocLundi":
				return "blocLundi_indexed_boolean";
			case "blocMardi":
				return "blocMardi_indexed_boolean";
			case "blocMercredi":
				return "blocMercredi_indexed_boolean";
			case "blocJeudi":
				return "blocJeudi_indexed_boolean";
			case "blocVendredi":
				return "blocVendredi_indexed_boolean";
			case "blocSamedi":
				return "blocSamedi_indexed_boolean";
			case "blocPrixTotal":
				return "blocPrixTotal_indexed_double";
			case "blocNomAdmin":
				return "blocNomAdmin_indexed_string";
			case "blocNomCourt":
				return "blocNomCourt_indexed_string";
			case "blocNomComplet":
				return "blocNomComplet_indexed_string";
			case "inscriptionApprouve":
				return "inscriptionApprouve_indexed_boolean";
			case "inscriptionImmunisations":
				return "inscriptionImmunisations_indexed_boolean";
			case "familleMarie":
				return "familleMarie_indexed_boolean";
			case "familleSepare":
				return "familleSepare_indexed_boolean";
			case "familleDivorce":
				return "familleDivorce_indexed_boolean";
			case "familleAddresse":
				return "familleAddresse_indexed_string";
			case "familleCommentVousConnaissezEcole":
				return "familleCommentVousConnaissezEcole_indexed_string";
			case "inscriptionConsiderationsSpeciales":
				return "inscriptionConsiderationsSpeciales_indexed_string";
			case "enfantConditionsMedicales":
				return "enfantConditionsMedicales_indexed_string";
			case "enfantEcolesPrecedemmentFrequentees":
				return "enfantEcolesPrecedemmentFrequentees_indexed_string";
			case "enfantDescription":
				return "enfantDescription_indexed_string";
			case "enfantObjectifs":
				return "enfantObjectifs_indexed_string";
			case "enfantPropre":
				return "enfantPropre_indexed_boolean";
			case "inscriptionNomGroupe":
				return "inscriptionNomGroupe_indexed_string";
			case "inscriptionPaimentChaqueMois":
				return "inscriptionPaimentChaqueMois_indexed_boolean";
			case "inscriptionPaimentComplet":
				return "inscriptionPaimentComplet_indexed_boolean";
			case "inscriptionNomsParents":
				return "inscriptionNomsParents_indexed_string";
			case "inscriptionDate1":
				return "inscriptionDate1_indexed_date";
			case "inscriptionDate2":
				return "inscriptionDate2_indexed_date";
			case "inscriptionDate3":
				return "inscriptionDate3_indexed_date";
			case "inscriptionDate4":
				return "inscriptionDate4_indexed_date";
			case "inscriptionDate5":
				return "inscriptionDate5_indexed_date";
			case "inscriptionDate6":
				return "inscriptionDate6_indexed_date";
			case "inscriptionDate7":
				return "inscriptionDate7_indexed_date";
			case "inscriptionDate8":
				return "inscriptionDate8_indexed_date";
			case "inscriptionDate9":
				return "inscriptionDate9_indexed_date";
			case "inscriptionDate10":
				return "inscriptionDate10_indexed_date";
			case "enfantImmunisationsRecu":
				return "enfantImmunisationsRecu_indexed_string";
			case "enfantPhotosApprouve":
				return "enfantPhotosApprouve_indexed_string";
			case "inscriptionNomComplet":
				return "inscriptionNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheInscriptionScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereInscriptionScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
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

		Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
		if(anneeCle != null)
			oInscriptionScolaire.setAnneeCle(anneeCle);

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

		Long formInscriptionCle = (Long)solrDocument.get("formInscriptionCle_stored_long");
		if(formInscriptionCle != null)
			oInscriptionScolaire.setFormInscriptionCle(formInscriptionCle);

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

		String enfantPrenom = (String)solrDocument.get("enfantPrenom_stored_string");
		if(enfantPrenom != null)
			oInscriptionScolaire.setEnfantPrenom(enfantPrenom);

		String enfantPrenomPrefere = (String)solrDocument.get("enfantPrenomPrefere_stored_string");
		if(enfantPrenomPrefere != null)
			oInscriptionScolaire.setEnfantPrenomPrefere(enfantPrenomPrefere);

		String enfantFamilleNom = (String)solrDocument.get("enfantFamilleNom_stored_string");
		if(enfantFamilleNom != null)
			oInscriptionScolaire.setEnfantFamilleNom(enfantFamilleNom);

		String merePrenom = (String)solrDocument.get("merePrenom_stored_string");
		if(merePrenom != null)
			oInscriptionScolaire.setMerePrenom(merePrenom);

		String merePrenomPrefere = (String)solrDocument.get("merePrenomPrefere_stored_string");
		if(merePrenomPrefere != null)
			oInscriptionScolaire.setMerePrenomPrefere(merePrenomPrefere);

		String perePrenom = (String)solrDocument.get("perePrenom_stored_string");
		if(perePrenom != null)
			oInscriptionScolaire.setPerePrenom(perePrenom);

		String perePrenomPrefere = (String)solrDocument.get("perePrenomPrefere_stored_string");
		if(perePrenomPrefere != null)
			oInscriptionScolaire.setPerePrenomPrefere(perePrenomPrefere);

		String enfantNomComplet = (String)solrDocument.get("enfantNomComplet_stored_string");
		if(enfantNomComplet != null)
			oInscriptionScolaire.setEnfantNomComplet(enfantNomComplet);

		String enfantNomCompletPrefere = (String)solrDocument.get("enfantNomCompletPrefere_stored_string");
		if(enfantNomCompletPrefere != null)
			oInscriptionScolaire.setEnfantNomCompletPrefere(enfantNomCompletPrefere);

		Date enfantDateNaissance = (Date)solrDocument.get("enfantDateNaissance_stored_date");
		if(enfantDateNaissance != null)
			oInscriptionScolaire.setEnfantDateNaissance(enfantDateNaissance);

		String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
		if(ecoleNom != null)
			oInscriptionScolaire.setEcoleNom(ecoleNom);

		String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
		if(ecoleNomComplet != null)
			oInscriptionScolaire.setEcoleNomComplet(ecoleNomComplet);

		String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
		if(ecoleEmplacement != null)
			oInscriptionScolaire.setEcoleEmplacement(ecoleEmplacement);

		String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
		if(ecoleAddresse != null)
			oInscriptionScolaire.setEcoleAddresse(ecoleAddresse);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oInscriptionScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
		if(ecoleAdministrateurNom != null)
			oInscriptionScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);

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

		Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
		if(anneeFraisInscription != null)
			oInscriptionScolaire.setAnneeFraisInscription(anneeFraisInscription);

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

		Double blocPrixTotal = (Double)solrDocument.get("blocPrixTotal_stored_double");
		if(blocPrixTotal != null)
			oInscriptionScolaire.setBlocPrixTotal(blocPrixTotal);

		String blocNomAdmin = (String)solrDocument.get("blocNomAdmin_stored_string");
		if(blocNomAdmin != null)
			oInscriptionScolaire.setBlocNomAdmin(blocNomAdmin);

		String blocNomCourt = (String)solrDocument.get("blocNomCourt_stored_string");
		if(blocNomCourt != null)
			oInscriptionScolaire.setBlocNomCourt(blocNomCourt);

		String blocNomComplet = (String)solrDocument.get("blocNomComplet_stored_string");
		if(blocNomComplet != null)
			oInscriptionScolaire.setBlocNomComplet(blocNomComplet);

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

		String enfantConditionsMedicales = (String)solrDocument.get("enfantConditionsMedicales_stored_string");
		if(enfantConditionsMedicales != null)
			oInscriptionScolaire.setEnfantConditionsMedicales(enfantConditionsMedicales);

		String enfantEcolesPrecedemmentFrequentees = (String)solrDocument.get("enfantEcolesPrecedemmentFrequentees_stored_string");
		if(enfantEcolesPrecedemmentFrequentees != null)
			oInscriptionScolaire.setEnfantEcolesPrecedemmentFrequentees(enfantEcolesPrecedemmentFrequentees);

		String enfantDescription = (String)solrDocument.get("enfantDescription_stored_string");
		if(enfantDescription != null)
			oInscriptionScolaire.setEnfantDescription(enfantDescription);

		String enfantObjectifs = (String)solrDocument.get("enfantObjectifs_stored_string");
		if(enfantObjectifs != null)
			oInscriptionScolaire.setEnfantObjectifs(enfantObjectifs);

		Boolean enfantPropre = (Boolean)solrDocument.get("enfantPropre_stored_boolean");
		if(enfantPropre != null)
			oInscriptionScolaire.setEnfantPropre(enfantPropre);

		String inscriptionNomGroupe = (String)solrDocument.get("inscriptionNomGroupe_stored_string");
		if(inscriptionNomGroupe != null)
			oInscriptionScolaire.setInscriptionNomGroupe(inscriptionNomGroupe);

		Boolean inscriptionPaimentChaqueMois = (Boolean)solrDocument.get("inscriptionPaimentChaqueMois_stored_boolean");
		if(inscriptionPaimentChaqueMois != null)
			oInscriptionScolaire.setInscriptionPaimentChaqueMois(inscriptionPaimentChaqueMois);

		Boolean inscriptionPaimentComplet = (Boolean)solrDocument.get("inscriptionPaimentComplet_stored_boolean");
		if(inscriptionPaimentComplet != null)
			oInscriptionScolaire.setInscriptionPaimentComplet(inscriptionPaimentComplet);

		String inscriptionNomsParents = (String)solrDocument.get("inscriptionNomsParents_stored_string");
		if(inscriptionNomsParents != null)
			oInscriptionScolaire.setInscriptionNomsParents(inscriptionNomsParents);

		String inscriptionNomParentLignes = (String)solrDocument.get("inscriptionNomParentLignes_stored_string");
		if(inscriptionNomParentLignes != null)
			oInscriptionScolaire.setInscriptionNomParentLignes(inscriptionNomParentLignes);

		String inscriptionMailParentLignes = (String)solrDocument.get("inscriptionMailParentLignes_stored_string");
		if(inscriptionMailParentLignes != null)
			oInscriptionScolaire.setInscriptionMailParentLignes(inscriptionMailParentLignes);

		String inscriptionDetailParentLignes = (String)solrDocument.get("inscriptionDetailParentLignes_stored_string");
		if(inscriptionDetailParentLignes != null)
			oInscriptionScolaire.setInscriptionDetailParentLignes(inscriptionDetailParentLignes);

		String inscriptionChercherParentLignes = (String)solrDocument.get("inscriptionChercherParentLignes_stored_string");
		if(inscriptionChercherParentLignes != null)
			oInscriptionScolaire.setInscriptionChercherParentLignes(inscriptionChercherParentLignes);

		String inscriptionContactUrgenceParentLignes = (String)solrDocument.get("inscriptionContactUrgenceParentLignes_stored_string");
		if(inscriptionContactUrgenceParentLignes != null)
			oInscriptionScolaire.setInscriptionContactUrgenceParentLignes(inscriptionContactUrgenceParentLignes);

		String inscriptionSignature1 = (String)solrDocument.get("inscriptionSignature1_stored_string");
		if(inscriptionSignature1 != null)
			oInscriptionScolaire.setInscriptionSignature1(inscriptionSignature1);

		String inscriptionSignature2 = (String)solrDocument.get("inscriptionSignature2_stored_string");
		if(inscriptionSignature2 != null)
			oInscriptionScolaire.setInscriptionSignature2(inscriptionSignature2);

		String inscriptionSignature3 = (String)solrDocument.get("inscriptionSignature3_stored_string");
		if(inscriptionSignature3 != null)
			oInscriptionScolaire.setInscriptionSignature3(inscriptionSignature3);

		String inscriptionSignature4 = (String)solrDocument.get("inscriptionSignature4_stored_string");
		if(inscriptionSignature4 != null)
			oInscriptionScolaire.setInscriptionSignature4(inscriptionSignature4);

		String inscriptionSignature5 = (String)solrDocument.get("inscriptionSignature5_stored_string");
		if(inscriptionSignature5 != null)
			oInscriptionScolaire.setInscriptionSignature5(inscriptionSignature5);

		String inscriptionSignature6 = (String)solrDocument.get("inscriptionSignature6_stored_string");
		if(inscriptionSignature6 != null)
			oInscriptionScolaire.setInscriptionSignature6(inscriptionSignature6);

		String inscriptionSignature7 = (String)solrDocument.get("inscriptionSignature7_stored_string");
		if(inscriptionSignature7 != null)
			oInscriptionScolaire.setInscriptionSignature7(inscriptionSignature7);

		String inscriptionSignature8 = (String)solrDocument.get("inscriptionSignature8_stored_string");
		if(inscriptionSignature8 != null)
			oInscriptionScolaire.setInscriptionSignature8(inscriptionSignature8);

		String inscriptionSignature9 = (String)solrDocument.get("inscriptionSignature9_stored_string");
		if(inscriptionSignature9 != null)
			oInscriptionScolaire.setInscriptionSignature9(inscriptionSignature9);

		String inscriptionSignature10 = (String)solrDocument.get("inscriptionSignature10_stored_string");
		if(inscriptionSignature10 != null)
			oInscriptionScolaire.setInscriptionSignature10(inscriptionSignature10);

		Date inscriptionDate1 = (Date)solrDocument.get("inscriptionDate1_stored_date");
		if(inscriptionDate1 != null)
			oInscriptionScolaire.setInscriptionDate1(inscriptionDate1);

		Date inscriptionDate2 = (Date)solrDocument.get("inscriptionDate2_stored_date");
		if(inscriptionDate2 != null)
			oInscriptionScolaire.setInscriptionDate2(inscriptionDate2);

		Date inscriptionDate3 = (Date)solrDocument.get("inscriptionDate3_stored_date");
		if(inscriptionDate3 != null)
			oInscriptionScolaire.setInscriptionDate3(inscriptionDate3);

		Date inscriptionDate4 = (Date)solrDocument.get("inscriptionDate4_stored_date");
		if(inscriptionDate4 != null)
			oInscriptionScolaire.setInscriptionDate4(inscriptionDate4);

		Date inscriptionDate5 = (Date)solrDocument.get("inscriptionDate5_stored_date");
		if(inscriptionDate5 != null)
			oInscriptionScolaire.setInscriptionDate5(inscriptionDate5);

		Date inscriptionDate6 = (Date)solrDocument.get("inscriptionDate6_stored_date");
		if(inscriptionDate6 != null)
			oInscriptionScolaire.setInscriptionDate6(inscriptionDate6);

		Date inscriptionDate7 = (Date)solrDocument.get("inscriptionDate7_stored_date");
		if(inscriptionDate7 != null)
			oInscriptionScolaire.setInscriptionDate7(inscriptionDate7);

		Date inscriptionDate8 = (Date)solrDocument.get("inscriptionDate8_stored_date");
		if(inscriptionDate8 != null)
			oInscriptionScolaire.setInscriptionDate8(inscriptionDate8);

		Date inscriptionDate9 = (Date)solrDocument.get("inscriptionDate9_stored_date");
		if(inscriptionDate9 != null)
			oInscriptionScolaire.setInscriptionDate9(inscriptionDate9);

		Date inscriptionDate10 = (Date)solrDocument.get("inscriptionDate10_stored_date");
		if(inscriptionDate10 != null)
			oInscriptionScolaire.setInscriptionDate10(inscriptionDate10);

		String enfantImmunisationsRecu = (String)solrDocument.get("enfantImmunisationsRecu_stored_string");
		if(enfantImmunisationsRecu != null)
			oInscriptionScolaire.setEnfantImmunisationsRecu(enfantImmunisationsRecu);

		String enfantPhotosApprouve = (String)solrDocument.get("enfantPhotosApprouve_stored_string");
		if(enfantPhotosApprouve != null)
			oInscriptionScolaire.setEnfantPhotosApprouve(enfantPhotosApprouve);

		String inscriptionNomComplet = (String)solrDocument.get("inscriptionNomComplet_stored_string");
		if(inscriptionNomComplet != null)
			oInscriptionScolaire.setInscriptionNomComplet(inscriptionNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiInscriptionScolaire() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		InscriptionScolaire original = (InscriptionScolaire)Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(original != null) {
			if(!Objects.equals(anneeCle, original.getAnneeCle()))
				requeteApi.addVars("anneeCle");
			if(!Objects.equals(blocCles, original.getBlocCles()))
				requeteApi.addVars("blocCles");
			if(!Objects.equals(enfantCle, original.getEnfantCle()))
				requeteApi.addVars("enfantCle");
			if(!Objects.equals(mereCles, original.getMereCles()))
				requeteApi.addVars("mereCles");
			if(!Objects.equals(pereCles, original.getPereCles()))
				requeteApi.addVars("pereCles");
			if(!Objects.equals(gardienCles, original.getGardienCles()))
				requeteApi.addVars("gardienCles");
			if(!Objects.equals(paiementCles, original.getPaiementCles()))
				requeteApi.addVars("paiementCles");
			if(!Objects.equals(enfantNomComplet, original.getEnfantNomComplet()))
				requeteApi.addVars("enfantNomComplet");
			if(!Objects.equals(enfantNomCompletPrefere, original.getEnfantNomCompletPrefere()))
				requeteApi.addVars("enfantNomCompletPrefere");
			if(!Objects.equals(enfantDateNaissance, original.getEnfantDateNaissance()))
				requeteApi.addVars("enfantDateNaissance");
			if(!Objects.equals(ecoleAddresse, original.getEcoleAddresse()))
				requeteApi.addVars("ecoleAddresse");
			if(!Objects.equals(inscriptionApprouve, original.getInscriptionApprouve()))
				requeteApi.addVars("inscriptionApprouve");
			if(!Objects.equals(inscriptionImmunisations, original.getInscriptionImmunisations()))
				requeteApi.addVars("inscriptionImmunisations");
			if(!Objects.equals(familleMarie, original.getFamilleMarie()))
				requeteApi.addVars("familleMarie");
			if(!Objects.equals(familleSepare, original.getFamilleSepare()))
				requeteApi.addVars("familleSepare");
			if(!Objects.equals(familleDivorce, original.getFamilleDivorce()))
				requeteApi.addVars("familleDivorce");
			if(!Objects.equals(familleAddresse, original.getFamilleAddresse()))
				requeteApi.addVars("familleAddresse");
			if(!Objects.equals(familleCommentVousConnaissezEcole, original.getFamilleCommentVousConnaissezEcole()))
				requeteApi.addVars("familleCommentVousConnaissezEcole");
			if(!Objects.equals(inscriptionConsiderationsSpeciales, original.getInscriptionConsiderationsSpeciales()))
				requeteApi.addVars("inscriptionConsiderationsSpeciales");
			if(!Objects.equals(enfantConditionsMedicales, original.getEnfantConditionsMedicales()))
				requeteApi.addVars("enfantConditionsMedicales");
			if(!Objects.equals(enfantEcolesPrecedemmentFrequentees, original.getEnfantEcolesPrecedemmentFrequentees()))
				requeteApi.addVars("enfantEcolesPrecedemmentFrequentees");
			if(!Objects.equals(enfantDescription, original.getEnfantDescription()))
				requeteApi.addVars("enfantDescription");
			if(!Objects.equals(enfantObjectifs, original.getEnfantObjectifs()))
				requeteApi.addVars("enfantObjectifs");
			if(!Objects.equals(enfantPropre, original.getEnfantPropre()))
				requeteApi.addVars("enfantPropre");
			if(!Objects.equals(inscriptionNomGroupe, original.getInscriptionNomGroupe()))
				requeteApi.addVars("inscriptionNomGroupe");
			if(!Objects.equals(inscriptionPaimentChaqueMois, original.getInscriptionPaimentChaqueMois()))
				requeteApi.addVars("inscriptionPaimentChaqueMois");
			if(!Objects.equals(inscriptionPaimentComplet, original.getInscriptionPaimentComplet()))
				requeteApi.addVars("inscriptionPaimentComplet");
			if(!Objects.equals(inscriptionNomsParents, original.getInscriptionNomsParents()))
				requeteApi.addVars("inscriptionNomsParents");
			if(!Objects.equals(inscriptionSignature1, original.getInscriptionSignature1()))
				requeteApi.addVars("inscriptionSignature1");
			if(!Objects.equals(inscriptionSignature2, original.getInscriptionSignature2()))
				requeteApi.addVars("inscriptionSignature2");
			if(!Objects.equals(inscriptionSignature3, original.getInscriptionSignature3()))
				requeteApi.addVars("inscriptionSignature3");
			if(!Objects.equals(inscriptionSignature4, original.getInscriptionSignature4()))
				requeteApi.addVars("inscriptionSignature4");
			if(!Objects.equals(inscriptionSignature5, original.getInscriptionSignature5()))
				requeteApi.addVars("inscriptionSignature5");
			if(!Objects.equals(inscriptionSignature6, original.getInscriptionSignature6()))
				requeteApi.addVars("inscriptionSignature6");
			if(!Objects.equals(inscriptionSignature7, original.getInscriptionSignature7()))
				requeteApi.addVars("inscriptionSignature7");
			if(!Objects.equals(inscriptionSignature8, original.getInscriptionSignature8()))
				requeteApi.addVars("inscriptionSignature8");
			if(!Objects.equals(inscriptionSignature9, original.getInscriptionSignature9()))
				requeteApi.addVars("inscriptionSignature9");
			if(!Objects.equals(inscriptionSignature10, original.getInscriptionSignature10()))
				requeteApi.addVars("inscriptionSignature10");
			if(!Objects.equals(inscriptionDate1, original.getInscriptionDate1()))
				requeteApi.addVars("inscriptionDate1");
			if(!Objects.equals(inscriptionDate2, original.getInscriptionDate2()))
				requeteApi.addVars("inscriptionDate2");
			if(!Objects.equals(inscriptionDate3, original.getInscriptionDate3()))
				requeteApi.addVars("inscriptionDate3");
			if(!Objects.equals(inscriptionDate4, original.getInscriptionDate4()))
				requeteApi.addVars("inscriptionDate4");
			if(!Objects.equals(inscriptionDate5, original.getInscriptionDate5()))
				requeteApi.addVars("inscriptionDate5");
			if(!Objects.equals(inscriptionDate6, original.getInscriptionDate6()))
				requeteApi.addVars("inscriptionDate6");
			if(!Objects.equals(inscriptionDate7, original.getInscriptionDate7()))
				requeteApi.addVars("inscriptionDate7");
			if(!Objects.equals(inscriptionDate8, original.getInscriptionDate8()))
				requeteApi.addVars("inscriptionDate8");
			if(!Objects.equals(inscriptionDate9, original.getInscriptionDate9()))
				requeteApi.addVars("inscriptionDate9");
			if(!Objects.equals(inscriptionDate10, original.getInscriptionDate10()))
				requeteApi.addVars("inscriptionDate10");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), anneeCle, blocCles, enfantCle, mereCles, pereCles, gardienCles, paiementCles, enfantNomComplet, enfantNomCompletPrefere, enfantDateNaissance, ecoleAddresse, inscriptionApprouve, inscriptionImmunisations, familleMarie, familleSepare, familleDivorce, familleAddresse, familleCommentVousConnaissezEcole, inscriptionConsiderationsSpeciales, enfantConditionsMedicales, enfantEcolesPrecedemmentFrequentees, enfantDescription, enfantObjectifs, enfantPropre, inscriptionNomGroupe, inscriptionPaimentChaqueMois, inscriptionPaimentComplet, inscriptionNomsParents, inscriptionSignature1, inscriptionSignature2, inscriptionSignature3, inscriptionSignature4, inscriptionSignature5, inscriptionSignature6, inscriptionSignature7, inscriptionSignature8, inscriptionSignature9, inscriptionSignature10, inscriptionDate1, inscriptionDate2, inscriptionDate3, inscriptionDate4, inscriptionDate5, inscriptionDate6, inscriptionDate7, inscriptionDate8, inscriptionDate9, inscriptionDate10);
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
				&& Objects.equals( anneeCle, that.anneeCle )
				&& Objects.equals( blocCles, that.blocCles )
				&& Objects.equals( enfantCle, that.enfantCle )
				&& Objects.equals( mereCles, that.mereCles )
				&& Objects.equals( pereCles, that.pereCles )
				&& Objects.equals( gardienCles, that.gardienCles )
				&& Objects.equals( paiementCles, that.paiementCles )
				&& Objects.equals( enfantNomComplet, that.enfantNomComplet )
				&& Objects.equals( enfantNomCompletPrefere, that.enfantNomCompletPrefere )
				&& Objects.equals( enfantDateNaissance, that.enfantDateNaissance )
				&& Objects.equals( ecoleAddresse, that.ecoleAddresse )
				&& Objects.equals( inscriptionApprouve, that.inscriptionApprouve )
				&& Objects.equals( inscriptionImmunisations, that.inscriptionImmunisations )
				&& Objects.equals( familleMarie, that.familleMarie )
				&& Objects.equals( familleSepare, that.familleSepare )
				&& Objects.equals( familleDivorce, that.familleDivorce )
				&& Objects.equals( familleAddresse, that.familleAddresse )
				&& Objects.equals( familleCommentVousConnaissezEcole, that.familleCommentVousConnaissezEcole )
				&& Objects.equals( inscriptionConsiderationsSpeciales, that.inscriptionConsiderationsSpeciales )
				&& Objects.equals( enfantConditionsMedicales, that.enfantConditionsMedicales )
				&& Objects.equals( enfantEcolesPrecedemmentFrequentees, that.enfantEcolesPrecedemmentFrequentees )
				&& Objects.equals( enfantDescription, that.enfantDescription )
				&& Objects.equals( enfantObjectifs, that.enfantObjectifs )
				&& Objects.equals( enfantPropre, that.enfantPropre )
				&& Objects.equals( inscriptionNomGroupe, that.inscriptionNomGroupe )
				&& Objects.equals( inscriptionPaimentChaqueMois, that.inscriptionPaimentChaqueMois )
				&& Objects.equals( inscriptionPaimentComplet, that.inscriptionPaimentComplet )
				&& Objects.equals( inscriptionNomsParents, that.inscriptionNomsParents )
				&& Objects.equals( inscriptionSignature1, that.inscriptionSignature1 )
				&& Objects.equals( inscriptionSignature2, that.inscriptionSignature2 )
				&& Objects.equals( inscriptionSignature3, that.inscriptionSignature3 )
				&& Objects.equals( inscriptionSignature4, that.inscriptionSignature4 )
				&& Objects.equals( inscriptionSignature5, that.inscriptionSignature5 )
				&& Objects.equals( inscriptionSignature6, that.inscriptionSignature6 )
				&& Objects.equals( inscriptionSignature7, that.inscriptionSignature7 )
				&& Objects.equals( inscriptionSignature8, that.inscriptionSignature8 )
				&& Objects.equals( inscriptionSignature9, that.inscriptionSignature9 )
				&& Objects.equals( inscriptionSignature10, that.inscriptionSignature10 )
				&& Objects.equals( inscriptionDate1, that.inscriptionDate1 )
				&& Objects.equals( inscriptionDate2, that.inscriptionDate2 )
				&& Objects.equals( inscriptionDate3, that.inscriptionDate3 )
				&& Objects.equals( inscriptionDate4, that.inscriptionDate4 )
				&& Objects.equals( inscriptionDate5, that.inscriptionDate5 )
				&& Objects.equals( inscriptionDate6, that.inscriptionDate6 )
				&& Objects.equals( inscriptionDate7, that.inscriptionDate7 )
				&& Objects.equals( inscriptionDate8, that.inscriptionDate8 )
				&& Objects.equals( inscriptionDate9, that.inscriptionDate9 )
				&& Objects.equals( inscriptionDate10, that.inscriptionDate10 );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("InscriptionScolaire { ");
		sb.append( "anneeCle: " ).append(anneeCle);
		sb.append( ", blocCles: " ).append(blocCles);
		sb.append( ", enfantCle: " ).append(enfantCle);
		sb.append( ", mereCles: " ).append(mereCles);
		sb.append( ", pereCles: " ).append(pereCles);
		sb.append( ", gardienCles: " ).append(gardienCles);
		sb.append( ", paiementCles: " ).append(paiementCles);
		sb.append( ", enfantNomComplet: \"" ).append(enfantNomComplet).append( "\"" );
		sb.append( ", enfantNomCompletPrefere: \"" ).append(enfantNomCompletPrefere).append( "\"" );
		sb.append( ", enfantDateNaissance: " ).append(enfantDateNaissance);
		sb.append( ", ecoleAddresse: \"" ).append(ecoleAddresse).append( "\"" );
		sb.append( ", inscriptionApprouve: " ).append(inscriptionApprouve);
		sb.append( ", inscriptionImmunisations: " ).append(inscriptionImmunisations);
		sb.append( ", familleMarie: " ).append(familleMarie);
		sb.append( ", familleSepare: " ).append(familleSepare);
		sb.append( ", familleDivorce: " ).append(familleDivorce);
		sb.append( ", familleAddresse: \"" ).append(familleAddresse).append( "\"" );
		sb.append( ", familleCommentVousConnaissezEcole: \"" ).append(familleCommentVousConnaissezEcole).append( "\"" );
		sb.append( ", inscriptionConsiderationsSpeciales: \"" ).append(inscriptionConsiderationsSpeciales).append( "\"" );
		sb.append( ", enfantConditionsMedicales: \"" ).append(enfantConditionsMedicales).append( "\"" );
		sb.append( ", enfantEcolesPrecedemmentFrequentees: \"" ).append(enfantEcolesPrecedemmentFrequentees).append( "\"" );
		sb.append( ", enfantDescription: \"" ).append(enfantDescription).append( "\"" );
		sb.append( ", enfantObjectifs: \"" ).append(enfantObjectifs).append( "\"" );
		sb.append( ", enfantPropre: " ).append(enfantPropre);
		sb.append( ", inscriptionNomGroupe: \"" ).append(inscriptionNomGroupe).append( "\"" );
		sb.append( ", inscriptionPaimentChaqueMois: " ).append(inscriptionPaimentChaqueMois);
		sb.append( ", inscriptionPaimentComplet: " ).append(inscriptionPaimentComplet);
		sb.append( ", inscriptionNomsParents: \"" ).append(inscriptionNomsParents).append( "\"" );
		sb.append( ", inscriptionSignature1: \"" ).append(inscriptionSignature1).append( "\"" );
		sb.append( ", inscriptionSignature2: \"" ).append(inscriptionSignature2).append( "\"" );
		sb.append( ", inscriptionSignature3: \"" ).append(inscriptionSignature3).append( "\"" );
		sb.append( ", inscriptionSignature4: \"" ).append(inscriptionSignature4).append( "\"" );
		sb.append( ", inscriptionSignature5: \"" ).append(inscriptionSignature5).append( "\"" );
		sb.append( ", inscriptionSignature6: \"" ).append(inscriptionSignature6).append( "\"" );
		sb.append( ", inscriptionSignature7: \"" ).append(inscriptionSignature7).append( "\"" );
		sb.append( ", inscriptionSignature8: \"" ).append(inscriptionSignature8).append( "\"" );
		sb.append( ", inscriptionSignature9: \"" ).append(inscriptionSignature9).append( "\"" );
		sb.append( ", inscriptionSignature10: \"" ).append(inscriptionSignature10).append( "\"" );
		sb.append( ", inscriptionDate1: " ).append(inscriptionDate1);
		sb.append( ", inscriptionDate2: " ).append(inscriptionDate2);
		sb.append( ", inscriptionDate3: " ).append(inscriptionDate3);
		sb.append( ", inscriptionDate4: " ).append(inscriptionDate4);
		sb.append( ", inscriptionDate5: " ).append(inscriptionDate5);
		sb.append( ", inscriptionDate6: " ).append(inscriptionDate6);
		sb.append( ", inscriptionDate7: " ).append(inscriptionDate7);
		sb.append( ", inscriptionDate8: " ).append(inscriptionDate8);
		sb.append( ", inscriptionDate9: " ).append(inscriptionDate9);
		sb.append( ", inscriptionDate10: " ).append(inscriptionDate10);
		sb.append(" }");
		return sb.toString();
	}
}
