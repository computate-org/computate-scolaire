package org.computate.scolaire.frFR.inscription;

import java.util.Arrays;
import org.apache.solr.common.util.SimpleOrderedMap;
import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import java.time.LocalTime;
import org.computate.scolaire.frFR.saison.SaisonScolaire;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.frFR.paiement.PaiementScolaire;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import org.computate.scolaire.frFR.enfant.EnfantScolaire;
import org.computate.scolaire.frFR.mere.MereScolaire;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import java.util.Set;
import org.computate.scolaire.frFR.gardien.GardienScolaire;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.List;
import java.time.LocalDate;
import org.apache.solr.client.solrj.SolrQuery;
import java.util.Optional;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.computate.scolaire.frFR.bloc.BlocScolaire;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import java.lang.String;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.solr.client.solrj.SolrClient;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.time.temporal.ChronoUnit;
import org.computate.scolaire.frFR.pere.PereScolaire;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe enrollmentCompleteName dans Solr. </a>
 * <br/>
 **/
public abstract class InscriptionScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(InscriptionScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

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
	public static final String InscriptionScolaire_Titre = "inscriptions";
	public static final String InscriptionScolaire_LesNom = "les inscriptions";
	public static final String InscriptionScolaire_AucunNomTrouve = "aucune inscription trouvée";
	public static final String InscriptionScolaire_NomVar = "inscription";
	public static final String InscriptionScolaire_DeNom = "d'inscription";
	public static final String InscriptionScolaire_NomAdjectifSingulier = "inscription";
	public static final String InscriptionScolaire_NomAdjectifPluriel = "inscriptions";
	public static final String InscriptionScolaire_Couleur = "blue-gray";
	public static final String InscriptionScolaire_IconeGroupe = "solid";
	public static final String InscriptionScolaire_IconeNom = "edit";
	public static final Integer InscriptionScolaire_Lignes = 300;

	////////////////////
	// inscriptionCle //
	////////////////////

	/**	 L'entité inscriptionCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long inscriptionCle;
	@JsonIgnore
	public Couverture<Long> inscriptionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("inscriptionCle").o(inscriptionCle);

	/**	<br/> L'entité inscriptionCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCle">Trouver l'entité inscriptionCle dans Solr</a>
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

	/**	 L'entité anneeCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long anneeCle;
	@JsonIgnore
	public Couverture<Long> anneeCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("anneeCle").o(anneeCle);

	/**	<br/> L'entité anneeCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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

	/**	 L'entité anneeRecherche
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<AnneeScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<AnneeScolaire> anneeRecherche = new ListeRecherche<AnneeScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<AnneeScolaire>> anneeRechercheCouverture = new Couverture<ListeRecherche<AnneeScolaire>>().p(this).c(ListeRecherche.class).var("anneeRecherche").o(anneeRecherche);

	/**	<br/> L'entité anneeRecherche
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<AnneeScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeRecherche">Trouver l'entité anneeRecherche dans Solr</a>
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

	/**	 L'entité annee_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected AnneeScolaire annee_;
	@JsonIgnore
	public Couverture<AnneeScolaire> annee_Couverture = new Couverture<AnneeScolaire>().p(this).c(AnneeScolaire.class).var("annee_").o(annee_);

	/**	<br/> L'entité annee_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:annee_">Trouver l'entité annee_ dans Solr</a>
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

	/**	 L'entité blocCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> blocCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> blocClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("blocCles").o(blocCles);

	/**	<br/> L'entité blocCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocCles">Trouver l'entité blocCles dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "blocs")
					.a("title", "La clé primaire des utilisateurs dans la base de données. ")
					.a("class", "valeur suggereBlocCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setBlocCles")
					.a("id", classeApiMethodeMethode, "_blocCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereInscriptionScolaireBlocCles($(this).val() ? rechercherBlocScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolaireBlocCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			sx(htmBlocCles());
		}
	}

	public void htmBlocCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
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
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), BlocScolaire.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), BlocScolaire.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
											.a("id", classeApiMethodeMethode, "_blocCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postBlocScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "blocCles')); });")
											.f().sx("ajouter un bloc")
										.g("button");
									} g("div");
								}
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

	/**	 L'entité blocRecherche
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<BlocScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<BlocScolaire> blocRecherche = new ListeRecherche<BlocScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<BlocScolaire>> blocRechercheCouverture = new Couverture<ListeRecherche<BlocScolaire>>().p(this).c(ListeRecherche.class).var("blocRecherche").o(blocRecherche);

	/**	<br/> L'entité blocRecherche
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<BlocScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocRecherche">Trouver l'entité blocRecherche dans Solr</a>
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

	/**	 L'entité blocs_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<BlocScolaire> blocs_;
	@JsonIgnore
	public Couverture<List<BlocScolaire>> blocs_Couverture = new Couverture<List<BlocScolaire>>().p(this).c(List.class).var("blocs_").o(blocs_);

	/**	<br/> L'entité blocs_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocs_">Trouver l'entité blocs_ dans Solr</a>
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

	/**	 L'entité saisons_
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SaisonScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<SaisonScolaire> saisons_ = new ArrayList<SaisonScolaire>();
	@JsonIgnore
	public Couverture<List<SaisonScolaire>> saisons_Couverture = new Couverture<List<SaisonScolaire>>().p(this).c(List.class).var("saisons_").o(saisons_);

	/**	<br/> L'entité saisons_
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<SaisonScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisons_">Trouver l'entité saisons_ dans Solr</a>
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

	/**	 L'entité bloc_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected BlocScolaire bloc_;
	@JsonIgnore
	public Couverture<BlocScolaire> bloc_Couverture = new Couverture<BlocScolaire>().p(this).c(BlocScolaire.class).var("bloc_").o(bloc_);

	/**	<br/> L'entité bloc_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:bloc_">Trouver l'entité bloc_ dans Solr</a>
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

	/**	 L'entité ecoleCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long ecoleCle;
	@JsonIgnore
	public Couverture<Long> ecoleCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("ecoleCle").o(ecoleCle);

	/**	<br/> L'entité ecoleCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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

	////////////////
	// sessionCle //
	////////////////

	/**	 L'entité sessionCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long sessionCle;
	@JsonIgnore
	public Couverture<Long> sessionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("sessionCle").o(sessionCle);

	/**	<br/> L'entité sessionCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCle">Trouver l'entité sessionCle dans Solr</a>
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

	/**	 L'entité ageCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long ageCle;
	@JsonIgnore
	public Couverture<Long> ageCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("ageCle").o(ageCle);

	/**	<br/> L'entité ageCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCle">Trouver l'entité ageCle dans Solr</a>
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

	/**	 L'entité blocCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long blocCle;
	@JsonIgnore
	public Couverture<Long> blocCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("blocCle").o(blocCle);

	/**	<br/> L'entité blocCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocCle">Trouver l'entité blocCle dans Solr</a>
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

	/**	 L'entité enfantCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long enfantCle;
	@JsonIgnore
	public Couverture<Long> enfantCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("enfantCle").o(enfantCle);

	/**	<br/> L'entité enfantCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantCle">Trouver l'entité enfantCle dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "enfants")
					.a("title", "La clé primaire des utilisateurs dans la base de données. ")
					.a("class", "valeur suggereEnfantCle w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setEnfantCle")
					.a("id", classeApiMethodeMethode, "_enfantCle")
					.a("autocomplete", "off")
					.a("oninput", "suggereInscriptionScolaireEnfantCle($(this).val() ? rechercherEnfantScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolaireEnfantCle_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			sx(htmEnfantCle());
		}
	}

	public void htmEnfantCle(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-orange w3-hover-orange ").f();
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
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
											.a("id", classeApiMethodeMethode, "_enfantCle_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postEnfantScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "enfantCle')); });")
											.f().sx("ajouter un enfant")
										.g("button");
									} g("div");
								}
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

	/**	 L'entité mereCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> mereCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> mereClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("mereCles").o(mereCles);

	/**	<br/> L'entité mereCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereCles">Trouver l'entité mereCles dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "mères")
					.a("title", "La clé primaire des utilisateurs dans la base de données. ")
					.a("class", "valeur suggereMereCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setMereCles")
					.a("id", classeApiMethodeMethode, "_mereCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereInscriptionScolaireMereCles($(this).val() ? rechercherMereScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolaireMereCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			sx(htmMereCles());
		}
	}

	public void htmMereCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
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
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
											.a("id", classeApiMethodeMethode, "_mereCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postMereScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "mereCles')); });")
											.f().sx("ajouter une mère")
										.g("button");
									} g("div");
								}
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

	/**	 L'entité pereCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> pereCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> pereClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("pereCles").o(pereCles);

	/**	<br/> L'entité pereCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereCles">Trouver l'entité pereCles dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "pères")
					.a("title", "La clé primaire des utilisateurs dans la base de données. ")
					.a("class", "valeur suggerePereCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setPereCles")
					.a("id", classeApiMethodeMethode, "_pereCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereInscriptionScolairePereCles($(this).val() ? rechercherPereScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolairePereCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			sx(htmPereCles());
		}
	}

	public void htmPereCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
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
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
											.a("id", classeApiMethodeMethode, "_pereCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postPereScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "pereCles')); });")
											.f().sx("ajouter un père")
										.g("button");
									} g("div");
								}
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

	/**	 L'entité gardienCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> gardienCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> gardienClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("gardienCles").o(gardienCles);

	/**	<br/> L'entité gardienCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienCles">Trouver l'entité gardienCles dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "gardiens")
					.a("title", "La clé primaire des utilisateurs dans la base de données. ")
					.a("class", "valeur suggereGardienCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setGardienCles")
					.a("id", classeApiMethodeMethode, "_gardienCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereInscriptionScolaireGardienCles($(this).val() ? rechercherGardienScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolaireGardienCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			sx(htmGardienCles());
		}
	}

	public void htmGardienCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
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
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
											.a("id", classeApiMethodeMethode, "_gardienCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postGardienScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "gardienCles')); });")
											.f().sx("ajouter un gardien")
										.g("button");
									} g("div");
								}
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

	/**	 L'entité paiementCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> paiementCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> paiementClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("paiementCles").o(paiementCles);

	/**	<br/> L'entité paiementCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementCles">Trouver l'entité paiementCles dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "paiements")
					.a("title", "La clé primaire des utilisateurs dans la base de données. ")
					.a("class", "valeur suggerePaiementCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setPaiementCles")
					.a("id", classeApiMethodeMethode, "_paiementCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereInscriptionScolairePaiementCles($(this).val() ? rechercherPaiementScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCle:" + pk + "'}", "], $('#listInscriptionScolairePaiementCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			sx(htmPaiementCles());
		}
	}

	public void htmPaiementCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolairePaiementCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=inscriptionCle:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
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
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), PaiementScolaire.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), PaiementScolaire.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
											.a("id", classeApiMethodeMethode, "_paiementCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postPaiementScolaireVals({ inscriptionCle: \"", pk, "\" }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "paiementCles')); });")
											.f().sx("ajouter un paiement")
										.g("button");
									} g("div");
								}
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

	/**	 L'entité formInscriptionCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long formInscriptionCle;
	@JsonIgnore
	public Couverture<Long> formInscriptionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("formInscriptionCle").o(formInscriptionCle);

	/**	<br/> L'entité formInscriptionCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:formInscriptionCle">Trouver l'entité formInscriptionCle dans Solr</a>
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

	/////////////////////
	// utilisateurCles //
	/////////////////////

	/**	 L'entité utilisateurCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> utilisateurCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> utilisateurClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("utilisateurCles").o(utilisateurCles);

	/**	<br/> L'entité utilisateurCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurCles">Trouver l'entité utilisateurCles dans Solr</a>
	 * <br/>
	 * @param utilisateurCles est l'entité déjà construit. 
	 **/
	protected abstract void _utilisateurCles(List<Long> l);

	public List<Long> getUtilisateurCles() {
		return utilisateurCles;
	}

	public void setUtilisateurCles(List<Long> utilisateurCles) {
		this.utilisateurCles = utilisateurCles;
		this.utilisateurClesCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addUtilisateurCles(Long...objets) {
		for(Long o : objets) {
			addUtilisateurCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addUtilisateurCles(Long o) {
		if(o != null && !utilisateurCles.contains(o))
			this.utilisateurCles.add(o);
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setUtilisateurCles(JsonArray objets) {
		utilisateurCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUtilisateurCles(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addUtilisateurCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUtilisateurCles(p);
		}
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire utilisateurClesInit() {
		if(!utilisateurClesCouverture.dejaInitialise) {
			_utilisateurCles(utilisateurCles);
		}
		utilisateurClesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public List<Long> solrUtilisateurCles() {
		return utilisateurCles;
	}

	public String strUtilisateurCles() {
		return utilisateurCles == null ? "" : utilisateurCles.toString();
	}

	public String jsonUtilisateurCles() {
		return utilisateurCles == null ? "" : utilisateurCles.toString();
	}

	public String nomAffichageUtilisateurCles() {
		return "utilisateurs";
	}

	public String htmTooltipUtilisateurCles() {
		return null;
	}

	public String htmUtilisateurCles() {
		return utilisateurCles == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurCles());
	}

	public void inputUtilisateurCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "utilisateurs")
					.a("title", "La clé primaire des utilisateurs dans la base de données. ")
					.a("class", "valeur suggereUtilisateurCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setUtilisateurCles")
					.a("id", classeApiMethodeMethode, "_utilisateurCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereInscriptionScolaireUtilisateurCles($(this).val() ? rechercherUtilisateurSiteFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolaireUtilisateurCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			sx(htmUtilisateurCles());
		}
	}

	public void htmUtilisateurCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireUtilisateurCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-gray w3-hover-gray ").f();
								e("i").a("class", "far fa-user-cog ").f().g("i");
								sx("utilisateurs");
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

								inputUtilisateurCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireUtilisateurCles_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), UtilisateurSite.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), UtilisateurSite.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
											.a("id", classeApiMethodeMethode, "_utilisateurCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postUtilisateurSiteVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "utilisateurCles')); });")
											.f().sx("ajouter un utilisateur du site")
										.g("button");
									} g("div");
								}
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// scolaireTri //
	/////////////////

	/**	 L'entité scolaireTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer scolaireTri;
	@JsonIgnore
	public Couverture<Integer> scolaireTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("scolaireTri").o(scolaireTri);

	/**	<br/> L'entité scolaireTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:scolaireTri">Trouver l'entité scolaireTri dans Solr</a>
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

	/**	 L'entité ecoleTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ecoleTri;
	@JsonIgnore
	public Couverture<Integer> ecoleTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ecoleTri").o(ecoleTri);

	/**	<br/> L'entité ecoleTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleTri">Trouver l'entité ecoleTri dans Solr</a>
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

	/**	 L'entité anneeTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer anneeTri;
	@JsonIgnore
	public Couverture<Integer> anneeTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeTri").o(anneeTri);

	/**	<br/> L'entité anneeTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeTri">Trouver l'entité anneeTri dans Solr</a>
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

	/**	 L'entité saisonTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer saisonTri;
	@JsonIgnore
	public Couverture<Integer> saisonTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("saisonTri").o(saisonTri);

	/**	<br/> L'entité saisonTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonTri">Trouver l'entité saisonTri dans Solr</a>
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

	/**	 L'entité sessionTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer sessionTri;
	@JsonIgnore
	public Couverture<Integer> sessionTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("sessionTri").o(sessionTri);

	/**	<br/> L'entité sessionTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionTri">Trouver l'entité sessionTri dans Solr</a>
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

	/**	 L'entité ageTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageTri;
	@JsonIgnore
	public Couverture<Integer> ageTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ageTri").o(ageTri);

	/**	<br/> L'entité ageTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageTri">Trouver l'entité ageTri dans Solr</a>
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

	/**	 L'entité enfantRecherche
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<EnfantScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<EnfantScolaire> enfantRecherche = new ListeRecherche<EnfantScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<EnfantScolaire>> enfantRechercheCouverture = new Couverture<ListeRecherche<EnfantScolaire>>().p(this).c(ListeRecherche.class).var("enfantRecherche").o(enfantRecherche);

	/**	<br/> L'entité enfantRecherche
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<EnfantScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantRecherche">Trouver l'entité enfantRecherche dans Solr</a>
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

	/**	 L'entité enfant_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected EnfantScolaire enfant_;
	@JsonIgnore
	public Couverture<EnfantScolaire> enfant_Couverture = new Couverture<EnfantScolaire>().p(this).c(EnfantScolaire.class).var("enfant_").o(enfant_);

	/**	<br/> L'entité enfant_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfant_">Trouver l'entité enfant_ dans Solr</a>
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

	/**	 L'entité mereRecherche
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<MereScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<MereScolaire> mereRecherche = new ListeRecherche<MereScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<MereScolaire>> mereRechercheCouverture = new Couverture<ListeRecherche<MereScolaire>>().p(this).c(ListeRecherche.class).var("mereRecherche").o(mereRecherche);

	/**	<br/> L'entité mereRecherche
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<MereScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereRecherche">Trouver l'entité mereRecherche dans Solr</a>
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

	/**	 L'entité meres
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<MereScolaire> meres;
	@JsonIgnore
	public Couverture<List<MereScolaire>> meresCouverture = new Couverture<List<MereScolaire>>().p(this).c(List.class).var("meres").o(meres);

	/**	<br/> L'entité meres
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:meres">Trouver l'entité meres dans Solr</a>
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

	/**	 L'entité pereRecherche
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<PereScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<PereScolaire> pereRecherche = new ListeRecherche<PereScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<PereScolaire>> pereRechercheCouverture = new Couverture<ListeRecherche<PereScolaire>>().p(this).c(ListeRecherche.class).var("pereRecherche").o(pereRecherche);

	/**	<br/> L'entité pereRecherche
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<PereScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereRecherche">Trouver l'entité pereRecherche dans Solr</a>
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

	/**	 L'entité peres
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<PereScolaire> peres;
	@JsonIgnore
	public Couverture<List<PereScolaire>> peresCouverture = new Couverture<List<PereScolaire>>().p(this).c(List.class).var("peres").o(peres);

	/**	<br/> L'entité peres
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:peres">Trouver l'entité peres dans Solr</a>
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

	/**	 L'entité gardienRecherche
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<GardienScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<GardienScolaire> gardienRecherche = new ListeRecherche<GardienScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<GardienScolaire>> gardienRechercheCouverture = new Couverture<ListeRecherche<GardienScolaire>>().p(this).c(ListeRecherche.class).var("gardienRecherche").o(gardienRecherche);

	/**	<br/> L'entité gardienRecherche
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<GardienScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienRecherche">Trouver l'entité gardienRecherche dans Solr</a>
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

	/**	 L'entité gardiens
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<GardienScolaire> gardiens;
	@JsonIgnore
	public Couverture<List<GardienScolaire>> gardiensCouverture = new Couverture<List<GardienScolaire>>().p(this).c(List.class).var("gardiens").o(gardiens);

	/**	<br/> L'entité gardiens
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardiens">Trouver l'entité gardiens dans Solr</a>
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

	////////////////////
	// fraisRecherche //
	////////////////////

	/**	 L'entité fraisRecherche
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<PaiementScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<PaiementScolaire> fraisRecherche = new ListeRecherche<PaiementScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<PaiementScolaire>> fraisRechercheCouverture = new Couverture<ListeRecherche<PaiementScolaire>>().p(this).c(ListeRecherche.class).var("fraisRecherche").o(fraisRecherche);

	/**	<br/> L'entité fraisRecherche
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<PaiementScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisRecherche">Trouver l'entité fraisRecherche dans Solr</a>
	 * <br/>
	 * @param fraisRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _fraisRecherche(ListeRecherche<PaiementScolaire> l);

	public ListeRecherche<PaiementScolaire> getFraisRecherche() {
		return fraisRecherche;
	}

	public void setFraisRecherche(ListeRecherche<PaiementScolaire> fraisRecherche) {
		this.fraisRecherche = fraisRecherche;
		this.fraisRechercheCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire fraisRechercheInit() {
		if(!fraisRechercheCouverture.dejaInitialise) {
			_fraisRecherche(fraisRecherche);
		}
		fraisRecherche.initLoinPourClasse(requeteSite_);
		fraisRechercheCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	///////////////////////
	// paiementRecherche //
	///////////////////////

	/**	 L'entité paiementRecherche
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<PaiementScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<PaiementScolaire> paiementRecherche = new ListeRecherche<PaiementScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<PaiementScolaire>> paiementRechercheCouverture = new Couverture<ListeRecherche<PaiementScolaire>>().p(this).c(ListeRecherche.class).var("paiementRecherche").o(paiementRecherche);

	/**	<br/> L'entité paiementRecherche
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<PaiementScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementRecherche">Trouver l'entité paiementRecherche dans Solr</a>
	 * <br/>
	 * @param paiementRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _paiementRecherche(ListeRecherche<PaiementScolaire> l);

	public ListeRecherche<PaiementScolaire> getPaiementRecherche() {
		return paiementRecherche;
	}

	public void setPaiementRecherche(ListeRecherche<PaiementScolaire> paiementRecherche) {
		this.paiementRecherche = paiementRecherche;
		this.paiementRechercheCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire paiementRechercheInit() {
		if(!paiementRechercheCouverture.dejaInitialise) {
			_paiementRecherche(paiementRecherche);
		}
		paiementRecherche.initLoinPourClasse(requeteSite_);
		paiementRechercheCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	//////////////////
	// enfantPrenom //
	//////////////////

	/**	 L'entité enfantPrenom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantPrenom;
	@JsonIgnore
	public Couverture<String> enfantPrenomCouverture = new Couverture<String>().p(this).c(String.class).var("enfantPrenom").o(enfantPrenom);

	/**	<br/> L'entité enfantPrenom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantPrenom">Trouver l'entité enfantPrenom dans Solr</a>
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

	/**	 L'entité enfantPrenomPrefere
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantPrenomPrefere;
	@JsonIgnore
	public Couverture<String> enfantPrenomPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("enfantPrenomPrefere").o(enfantPrenomPrefere);

	/**	<br/> L'entité enfantPrenomPrefere
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantPrenomPrefere">Trouver l'entité enfantPrenomPrefere dans Solr</a>
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

	/**	 L'entité enfantFamilleNom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantFamilleNom;
	@JsonIgnore
	public Couverture<String> enfantFamilleNomCouverture = new Couverture<String>().p(this).c(String.class).var("enfantFamilleNom").o(enfantFamilleNom);

	/**	<br/> L'entité enfantFamilleNom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantFamilleNom">Trouver l'entité enfantFamilleNom dans Solr</a>
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

	/**	 L'entité merePrenom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String merePrenom;
	@JsonIgnore
	public Couverture<String> merePrenomCouverture = new Couverture<String>().p(this).c(String.class).var("merePrenom").o(merePrenom);

	/**	<br/> L'entité merePrenom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:merePrenom">Trouver l'entité merePrenom dans Solr</a>
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

	/**	 L'entité merePrenomPrefere
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String merePrenomPrefere;
	@JsonIgnore
	public Couverture<String> merePrenomPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("merePrenomPrefere").o(merePrenomPrefere);

	/**	<br/> L'entité merePrenomPrefere
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:merePrenomPrefere">Trouver l'entité merePrenomPrefere dans Solr</a>
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

	///////////////////////////
	// mereNomCompletPrefere //
	///////////////////////////

	/**	 L'entité mereNomCompletPrefere
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mereNomCompletPrefere;
	@JsonIgnore
	public Couverture<String> mereNomCompletPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("mereNomCompletPrefere").o(mereNomCompletPrefere);

	/**	<br/> L'entité mereNomCompletPrefere
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereNomCompletPrefere">Trouver l'entité mereNomCompletPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mereNomCompletPrefere(Couverture<String> c);

	public String getMereNomCompletPrefere() {
		return mereNomCompletPrefere;
	}

	public void setMereNomCompletPrefere(String mereNomCompletPrefere) {
		this.mereNomCompletPrefere = mereNomCompletPrefere;
		this.mereNomCompletPrefereCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire mereNomCompletPrefereInit() {
		if(!mereNomCompletPrefereCouverture.dejaInitialise) {
			_mereNomCompletPrefere(mereNomCompletPrefereCouverture);
			if(mereNomCompletPrefere == null)
				setMereNomCompletPrefere(mereNomCompletPrefereCouverture.o);
		}
		mereNomCompletPrefereCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrMereNomCompletPrefere() {
		return mereNomCompletPrefere;
	}

	public String strMereNomCompletPrefere() {
		return mereNomCompletPrefere == null ? "" : mereNomCompletPrefere;
	}

	public String jsonMereNomCompletPrefere() {
		return mereNomCompletPrefere == null ? "" : mereNomCompletPrefere;
	}

	public String nomAffichageMereNomCompletPrefere() {
		return null;
	}

	public String htmTooltipMereNomCompletPrefere() {
		return null;
	}

	public String htmMereNomCompletPrefere() {
		return mereNomCompletPrefere == null ? "" : StringEscapeUtils.escapeHtml4(strMereNomCompletPrefere());
	}

	////////////////
	// perePrenom //
	////////////////

	/**	 L'entité perePrenom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String perePrenom;
	@JsonIgnore
	public Couverture<String> perePrenomCouverture = new Couverture<String>().p(this).c(String.class).var("perePrenom").o(perePrenom);

	/**	<br/> L'entité perePrenom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:perePrenom">Trouver l'entité perePrenom dans Solr</a>
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

	/**	 L'entité perePrenomPrefere
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String perePrenomPrefere;
	@JsonIgnore
	public Couverture<String> perePrenomPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("perePrenomPrefere").o(perePrenomPrefere);

	/**	<br/> L'entité perePrenomPrefere
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:perePrenomPrefere">Trouver l'entité perePrenomPrefere dans Solr</a>
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

	///////////////////////////
	// pereNomCompletPrefere //
	///////////////////////////

	/**	 L'entité pereNomCompletPrefere
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pereNomCompletPrefere;
	@JsonIgnore
	public Couverture<String> pereNomCompletPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("pereNomCompletPrefere").o(pereNomCompletPrefere);

	/**	<br/> L'entité pereNomCompletPrefere
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereNomCompletPrefere">Trouver l'entité pereNomCompletPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pereNomCompletPrefere(Couverture<String> c);

	public String getPereNomCompletPrefere() {
		return pereNomCompletPrefere;
	}

	public void setPereNomCompletPrefere(String pereNomCompletPrefere) {
		this.pereNomCompletPrefere = pereNomCompletPrefere;
		this.pereNomCompletPrefereCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire pereNomCompletPrefereInit() {
		if(!pereNomCompletPrefereCouverture.dejaInitialise) {
			_pereNomCompletPrefere(pereNomCompletPrefereCouverture);
			if(pereNomCompletPrefere == null)
				setPereNomCompletPrefere(pereNomCompletPrefereCouverture.o);
		}
		pereNomCompletPrefereCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrPereNomCompletPrefere() {
		return pereNomCompletPrefere;
	}

	public String strPereNomCompletPrefere() {
		return pereNomCompletPrefere == null ? "" : pereNomCompletPrefere;
	}

	public String jsonPereNomCompletPrefere() {
		return pereNomCompletPrefere == null ? "" : pereNomCompletPrefere;
	}

	public String nomAffichagePereNomCompletPrefere() {
		return null;
	}

	public String htmTooltipPereNomCompletPrefere() {
		return null;
	}

	public String htmPereNomCompletPrefere() {
		return pereNomCompletPrefere == null ? "" : StringEscapeUtils.escapeHtml4(strPereNomCompletPrefere());
	}

	//////////////////////
	// enfantNomComplet //
	//////////////////////

	/**	 L'entité enfantNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantNomComplet;
	@JsonIgnore
	public Couverture<String> enfantNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("enfantNomComplet").o(enfantNomComplet);

	/**	<br/> L'entité enfantNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantNomComplet">Trouver l'entité enfantNomComplet dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "NomAffichage.enUS: ")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_enfantNomComplet");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEnfantNomComplet classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantNomComplet w3-input w3-border ");
					a("name", "setEnfantNomComplet");
				} else {
					a("class", "valeurEnfantNomComplet w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantNomComplet w3-input w3-border ");
					a("name", "enfantNomComplet");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantNomComplet', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantNomComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantNomComplet')); }); ");
				}
				a("value", strEnfantNomComplet())
			.fg();

		} else {
			sx(htmEnfantNomComplet());
		}
	}

	public void htmEnfantNomComplet(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantNomComplet").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantNomComplet").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantNomComplet(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantNomComplet')); $('#", classeApiMethodeMethode, "_enfantNomComplet').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantNomComplet', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantNomComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantNomComplet')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité enfantNomCompletPrefere
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantNomCompletPrefere;
	@JsonIgnore
	public Couverture<String> enfantNomCompletPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("enfantNomCompletPrefere").o(enfantNomCompletPrefere);

	/**	<br/> L'entité enfantNomCompletPrefere
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantNomCompletPrefere">Trouver l'entité enfantNomCompletPrefere dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "NomAffichage.enUS: ")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_enfantNomCompletPrefere");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEnfantNomCompletPrefere classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantNomCompletPrefere w3-input w3-border ");
					a("name", "setEnfantNomCompletPrefere");
				} else {
					a("class", "valeurEnfantNomCompletPrefere w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantNomCompletPrefere w3-input w3-border ");
					a("name", "enfantNomCompletPrefere");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantNomCompletPrefere', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }); ");
				}
				a("value", strEnfantNomCompletPrefere())
			.fg();

		} else {
			sx(htmEnfantNomCompletPrefere());
		}
	}

	public void htmEnfantNomCompletPrefere(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantNomCompletPrefere").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantNomCompletPrefere").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantNomCompletPrefere(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); $('#", classeApiMethodeMethode, "_enfantNomCompletPrefere').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantNomCompletPrefere', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité enfantDateNaissance
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enfantDateNaissance;
	@JsonIgnore
	public Couverture<LocalDate> enfantDateNaissanceCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("enfantDateNaissance").o(enfantDateNaissance);

	/**	<br/> L'entité enfantDateNaissance
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantDateNaissance">Trouver l'entité enfantDateNaissance dans Solr</a>
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
		this.enfantDateNaissance = o == null ? null : LocalDate.from(o);
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setEnfantDateNaissance(String o) {
		this.enfantDateNaissance = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setEnfantDateNaissance(Date o) {
		this.enfantDateNaissance = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
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
		return enfantDateNaissance == null ? null : Date.from(enfantDateNaissance.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strEnfantDateNaissance() {
		return enfantDateNaissance == null ? "" : enfantDateNaissance.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonEnfantDateNaissance() {
		return enfantDateNaissance == null ? "" : enfantDateNaissance.format(DateTimeFormatter.ISO_DATE);
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setEnfantDateNaissance classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantDateNaissance w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_enfantDateNaissance")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", enfantDateNaissance == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(enfantDateNaissance))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantDateNaissance', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }); } ")
				.fg();
		} else {
			sx(htmEnfantDateNaissance());
		}
	}

	public void htmEnfantDateNaissance(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantDateNaissance").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantDateNaissance").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnfantDateNaissance(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); $('#", classeApiMethodeMethode, "_enfantDateNaissance').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantDateNaissance', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////////
	// enfantDateNaissanceDAnnee //
	///////////////////////////////

	/**	 L'entité enfantDateNaissanceDAnnee
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer enfantDateNaissanceDAnnee;
	@JsonIgnore
	public Couverture<Integer> enfantDateNaissanceDAnneeCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("enfantDateNaissanceDAnnee").o(enfantDateNaissanceDAnnee);

	/**	<br/> L'entité enfantDateNaissanceDAnnee
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantDateNaissanceDAnnee">Trouver l'entité enfantDateNaissanceDAnnee dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantDateNaissanceDAnnee(Couverture<Integer> c);

	public Integer getEnfantDateNaissanceDAnnee() {
		return enfantDateNaissanceDAnnee;
	}

	public void setEnfantDateNaissanceDAnnee(Integer enfantDateNaissanceDAnnee) {
		this.enfantDateNaissanceDAnnee = enfantDateNaissanceDAnnee;
		this.enfantDateNaissanceDAnneeCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setEnfantDateNaissanceDAnnee(String o) {
		if(NumberUtils.isParsable(o))
			this.enfantDateNaissanceDAnnee = Integer.parseInt(o);
		this.enfantDateNaissanceDAnneeCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire enfantDateNaissanceDAnneeInit() {
		if(!enfantDateNaissanceDAnneeCouverture.dejaInitialise) {
			_enfantDateNaissanceDAnnee(enfantDateNaissanceDAnneeCouverture);
			if(enfantDateNaissanceDAnnee == null)
				setEnfantDateNaissanceDAnnee(enfantDateNaissanceDAnneeCouverture.o);
		}
		enfantDateNaissanceDAnneeCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrEnfantDateNaissanceDAnnee() {
		return enfantDateNaissanceDAnnee;
	}

	public String strEnfantDateNaissanceDAnnee() {
		return enfantDateNaissanceDAnnee == null ? "" : enfantDateNaissanceDAnnee.toString();
	}

	public String jsonEnfantDateNaissanceDAnnee() {
		return enfantDateNaissanceDAnnee == null ? "" : enfantDateNaissanceDAnnee.toString();
	}

	public String nomAffichageEnfantDateNaissanceDAnnee() {
		return null;
	}

	public String htmTooltipEnfantDateNaissanceDAnnee() {
		return null;
	}

	public String htmEnfantDateNaissanceDAnnee() {
		return enfantDateNaissanceDAnnee == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantDateNaissanceDAnnee());
	}

	///////////////////////////////////
	// enfantDateNaissanceMoisDAnnee //
	///////////////////////////////////

	/**	 L'entité enfantDateNaissanceMoisDAnnee
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantDateNaissanceMoisDAnnee;
	@JsonIgnore
	public Couverture<String> enfantDateNaissanceMoisDAnneeCouverture = new Couverture<String>().p(this).c(String.class).var("enfantDateNaissanceMoisDAnnee").o(enfantDateNaissanceMoisDAnnee);

	/**	<br/> L'entité enfantDateNaissanceMoisDAnnee
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantDateNaissanceMoisDAnnee">Trouver l'entité enfantDateNaissanceMoisDAnnee dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantDateNaissanceMoisDAnnee(Couverture<String> c);

	public String getEnfantDateNaissanceMoisDAnnee() {
		return enfantDateNaissanceMoisDAnnee;
	}

	public void setEnfantDateNaissanceMoisDAnnee(String enfantDateNaissanceMoisDAnnee) {
		this.enfantDateNaissanceMoisDAnnee = enfantDateNaissanceMoisDAnnee;
		this.enfantDateNaissanceMoisDAnneeCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire enfantDateNaissanceMoisDAnneeInit() {
		if(!enfantDateNaissanceMoisDAnneeCouverture.dejaInitialise) {
			_enfantDateNaissanceMoisDAnnee(enfantDateNaissanceMoisDAnneeCouverture);
			if(enfantDateNaissanceMoisDAnnee == null)
				setEnfantDateNaissanceMoisDAnnee(enfantDateNaissanceMoisDAnneeCouverture.o);
		}
		enfantDateNaissanceMoisDAnneeCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEnfantDateNaissanceMoisDAnnee() {
		return enfantDateNaissanceMoisDAnnee;
	}

	public String strEnfantDateNaissanceMoisDAnnee() {
		return enfantDateNaissanceMoisDAnnee == null ? "" : enfantDateNaissanceMoisDAnnee;
	}

	public String jsonEnfantDateNaissanceMoisDAnnee() {
		return enfantDateNaissanceMoisDAnnee == null ? "" : enfantDateNaissanceMoisDAnnee;
	}

	public String nomAffichageEnfantDateNaissanceMoisDAnnee() {
		return null;
	}

	public String htmTooltipEnfantDateNaissanceMoisDAnnee() {
		return null;
	}

	public String htmEnfantDateNaissanceMoisDAnnee() {
		return enfantDateNaissanceMoisDAnnee == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantDateNaissanceMoisDAnnee());
	}

	//////////////////////////////////////
	// enfantDateNaissanceJourDeSemaine //
	//////////////////////////////////////

	/**	 L'entité enfantDateNaissanceJourDeSemaine
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantDateNaissanceJourDeSemaine;
	@JsonIgnore
	public Couverture<String> enfantDateNaissanceJourDeSemaineCouverture = new Couverture<String>().p(this).c(String.class).var("enfantDateNaissanceJourDeSemaine").o(enfantDateNaissanceJourDeSemaine);

	/**	<br/> L'entité enfantDateNaissanceJourDeSemaine
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantDateNaissanceJourDeSemaine">Trouver l'entité enfantDateNaissanceJourDeSemaine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantDateNaissanceJourDeSemaine(Couverture<String> c);

	public String getEnfantDateNaissanceJourDeSemaine() {
		return enfantDateNaissanceJourDeSemaine;
	}

	public void setEnfantDateNaissanceJourDeSemaine(String enfantDateNaissanceJourDeSemaine) {
		this.enfantDateNaissanceJourDeSemaine = enfantDateNaissanceJourDeSemaine;
		this.enfantDateNaissanceJourDeSemaineCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire enfantDateNaissanceJourDeSemaineInit() {
		if(!enfantDateNaissanceJourDeSemaineCouverture.dejaInitialise) {
			_enfantDateNaissanceJourDeSemaine(enfantDateNaissanceJourDeSemaineCouverture);
			if(enfantDateNaissanceJourDeSemaine == null)
				setEnfantDateNaissanceJourDeSemaine(enfantDateNaissanceJourDeSemaineCouverture.o);
		}
		enfantDateNaissanceJourDeSemaineCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEnfantDateNaissanceJourDeSemaine() {
		return enfantDateNaissanceJourDeSemaine;
	}

	public String strEnfantDateNaissanceJourDeSemaine() {
		return enfantDateNaissanceJourDeSemaine == null ? "" : enfantDateNaissanceJourDeSemaine;
	}

	public String jsonEnfantDateNaissanceJourDeSemaine() {
		return enfantDateNaissanceJourDeSemaine == null ? "" : enfantDateNaissanceJourDeSemaine;
	}

	public String nomAffichageEnfantDateNaissanceJourDeSemaine() {
		return null;
	}

	public String htmTooltipEnfantDateNaissanceJourDeSemaine() {
		return null;
	}

	public String htmEnfantDateNaissanceJourDeSemaine() {
		return enfantDateNaissanceJourDeSemaine == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantDateNaissanceJourDeSemaine());
	}

	/////////////////////////
	// enfantMoisNaissance //
	/////////////////////////

	/**	 L'entité enfantMoisNaissance
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer enfantMoisNaissance;
	@JsonIgnore
	public Couverture<Integer> enfantMoisNaissanceCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("enfantMoisNaissance").o(enfantMoisNaissance);

	/**	<br/> L'entité enfantMoisNaissance
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantMoisNaissance">Trouver l'entité enfantMoisNaissance dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantMoisNaissance(Couverture<Integer> c);

	public Integer getEnfantMoisNaissance() {
		return enfantMoisNaissance;
	}

	public void setEnfantMoisNaissance(Integer enfantMoisNaissance) {
		this.enfantMoisNaissance = enfantMoisNaissance;
		this.enfantMoisNaissanceCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setEnfantMoisNaissance(String o) {
		if(NumberUtils.isParsable(o))
			this.enfantMoisNaissance = Integer.parseInt(o);
		this.enfantMoisNaissanceCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire enfantMoisNaissanceInit() {
		if(!enfantMoisNaissanceCouverture.dejaInitialise) {
			_enfantMoisNaissance(enfantMoisNaissanceCouverture);
			if(enfantMoisNaissance == null)
				setEnfantMoisNaissance(enfantMoisNaissanceCouverture.o);
		}
		enfantMoisNaissanceCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrEnfantMoisNaissance() {
		return enfantMoisNaissance;
	}

	public String strEnfantMoisNaissance() {
		return enfantMoisNaissance == null ? "" : enfantMoisNaissance.toString();
	}

	public String jsonEnfantMoisNaissance() {
		return enfantMoisNaissance == null ? "" : enfantMoisNaissance.toString();
	}

	public String nomAffichageEnfantMoisNaissance() {
		return null;
	}

	public String htmTooltipEnfantMoisNaissance() {
		return null;
	}

	public String htmEnfantMoisNaissance() {
		return enfantMoisNaissance == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantMoisNaissance());
	}

	/////////////////////////
	// enfantJourNaissance //
	/////////////////////////

	/**	 L'entité enfantJourNaissance
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer enfantJourNaissance;
	@JsonIgnore
	public Couverture<Integer> enfantJourNaissanceCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("enfantJourNaissance").o(enfantJourNaissance);

	/**	<br/> L'entité enfantJourNaissance
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantJourNaissance">Trouver l'entité enfantJourNaissance dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantJourNaissance(Couverture<Integer> c);

	public Integer getEnfantJourNaissance() {
		return enfantJourNaissance;
	}

	public void setEnfantJourNaissance(Integer enfantJourNaissance) {
		this.enfantJourNaissance = enfantJourNaissance;
		this.enfantJourNaissanceCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setEnfantJourNaissance(String o) {
		if(NumberUtils.isParsable(o))
			this.enfantJourNaissance = Integer.parseInt(o);
		this.enfantJourNaissanceCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire enfantJourNaissanceInit() {
		if(!enfantJourNaissanceCouverture.dejaInitialise) {
			_enfantJourNaissance(enfantJourNaissanceCouverture);
			if(enfantJourNaissance == null)
				setEnfantJourNaissance(enfantJourNaissanceCouverture.o);
		}
		enfantJourNaissanceCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrEnfantJourNaissance() {
		return enfantJourNaissance;
	}

	public String strEnfantJourNaissance() {
		return enfantJourNaissance == null ? "" : enfantJourNaissance.toString();
	}

	public String jsonEnfantJourNaissance() {
		return enfantJourNaissance == null ? "" : enfantJourNaissance.toString();
	}

	public String nomAffichageEnfantJourNaissance() {
		return null;
	}

	public String htmTooltipEnfantJourNaissance() {
		return null;
	}

	public String htmEnfantJourNaissance() {
		return enfantJourNaissance == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantJourNaissance());
	}

	//////////////
	// ecoleNom //
	//////////////

	/**	 L'entité ecoleNom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleNom;
	@JsonIgnore
	public Couverture<String> ecoleNomCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNom").o(ecoleNom);

	/**	<br/> L'entité ecoleNom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
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

	/**	 L'entité ecoleNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleNomComplet;
	@JsonIgnore
	public Couverture<String> ecoleNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNomComplet").o(ecoleNomComplet);

	/**	<br/> L'entité ecoleNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
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

	/**	 L'entité ecoleEmplacement
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleEmplacement;
	@JsonIgnore
	public Couverture<String> ecoleEmplacementCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleEmplacement").o(ecoleEmplacement);

	/**	<br/> L'entité ecoleEmplacement
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleEmplacement">Trouver l'entité ecoleEmplacement dans Solr</a>
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

	/**	 L'entité ecoleAddresse
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleAddresse;
	@JsonIgnore
	public Couverture<String> ecoleAddresseCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleAddresse").o(ecoleAddresse);

	/**	<br/> L'entité ecoleAddresse
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "addresse")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_ecoleAddresse");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleAddresse classInscriptionScolaire inputInscriptionScolaire", pk, "EcoleAddresse w3-input w3-border ");
					a("name", "setEcoleAddresse");
				} else {
					a("class", "valeurEcoleAddresse w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "EcoleAddresse w3-input w3-border ");
					a("name", "ecoleAddresse");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleAddresse', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }); ");
				}
				a("value", strEcoleAddresse())
			.fg();

		} else {
			sx(htmEcoleAddresse());
		}
	}

	public void htmEcoleAddresse(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEcoleAddresse").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleAddresse").a("class", "").f().sx("addresse").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleAddresse(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); $('#", classeApiMethodeMethode, "_ecoleAddresse').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEcoleAddresse', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité ecoleNumeroTelephone
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleNumeroTelephone;
	@JsonIgnore
	public Couverture<String> ecoleNumeroTelephoneCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNumeroTelephone").o(ecoleNumeroTelephone);

	/**	<br/> L'entité ecoleNumeroTelephone
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
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

	///////////////
	// ecoleForm //
	///////////////

	/**	 L'entité ecoleForm
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleForm;
	@JsonIgnore
	public Couverture<String> ecoleFormCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleForm").o(ecoleForm);

	/**	<br/> L'entité ecoleForm
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleForm">Trouver l'entité ecoleForm dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleForm(Couverture<String> c);

	public String getEcoleForm() {
		return ecoleForm;
	}

	public void setEcoleForm(String ecoleForm) {
		this.ecoleForm = ecoleForm;
		this.ecoleFormCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire ecoleFormInit() {
		if(!ecoleFormCouverture.dejaInitialise) {
			_ecoleForm(ecoleFormCouverture);
			if(ecoleForm == null)
				setEcoleForm(ecoleFormCouverture.o);
		}
		ecoleFormCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrEcoleForm() {
		return ecoleForm;
	}

	public String strEcoleForm() {
		return ecoleForm == null ? "" : ecoleForm;
	}

	public String jsonEcoleForm() {
		return ecoleForm == null ? "" : ecoleForm;
	}

	public String nomAffichageEcoleForm() {
		return null;
	}

	public String htmTooltipEcoleForm() {
		return null;
	}

	public String htmEcoleForm() {
		return ecoleForm == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleForm());
	}

	/////////////////
	// ecoleNumero //
	/////////////////

	/**	 L'entité ecoleNumero
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ecoleNumero;
	@JsonIgnore
	public Couverture<Integer> ecoleNumeroCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ecoleNumero").o(ecoleNumero);

	/**	<br/> L'entité ecoleNumero
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumero">Trouver l'entité ecoleNumero dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNumero(Couverture<Integer> c);

	public Integer getEcoleNumero() {
		return ecoleNumero;
	}

	public void setEcoleNumero(Integer ecoleNumero) {
		this.ecoleNumero = ecoleNumero;
		this.ecoleNumeroCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setEcoleNumero(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleNumero = Integer.parseInt(o);
		this.ecoleNumeroCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire ecoleNumeroInit() {
		if(!ecoleNumeroCouverture.dejaInitialise) {
			_ecoleNumero(ecoleNumeroCouverture);
			if(ecoleNumero == null)
				setEcoleNumero(ecoleNumeroCouverture.o);
		}
		ecoleNumeroCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrEcoleNumero() {
		return ecoleNumero;
	}

	public String strEcoleNumero() {
		return ecoleNumero == null ? "" : ecoleNumero.toString();
	}

	public String jsonEcoleNumero() {
		return ecoleNumero == null ? "" : ecoleNumero.toString();
	}

	public String nomAffichageEcoleNumero() {
		return null;
	}

	public String htmTooltipEcoleNumero() {
		return null;
	}

	public String htmEcoleNumero() {
		return ecoleNumero == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNumero());
	}

	////////////////////////////
	// ecoleAdministrateurNom //
	////////////////////////////

	/**	 L'entité ecoleAdministrateurNom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleAdministrateurNom;
	@JsonIgnore
	public Couverture<String> ecoleAdministrateurNomCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleAdministrateurNom").o(ecoleAdministrateurNom);

	/**	<br/> L'entité ecoleAdministrateurNom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAdministrateurNom">Trouver l'entité ecoleAdministrateurNom dans Solr</a>
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

	/**	 L'entité anneeDebut
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer anneeDebut;
	@JsonIgnore
	public Couverture<Integer> anneeDebutCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeDebut").o(anneeDebut);

	/**	<br/> L'entité anneeDebut
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
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

	/**	 L'entité anneeFin
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer anneeFin;
	@JsonIgnore
	public Couverture<Integer> anneeFinCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeFin").o(anneeFin);

	/**	<br/> L'entité anneeFin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
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
	// saisonDateDebut //
	/////////////////////

	/**	 L'entité saisonDateDebut
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate saisonDateDebut;
	@JsonIgnore
	public Couverture<LocalDate> saisonDateDebutCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("saisonDateDebut").o(saisonDateDebut);

	/**	<br/> L'entité saisonDateDebut
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonDateDebut">Trouver l'entité saisonDateDebut dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonDateDebut(Couverture<LocalDate> c);

	public LocalDate getSaisonDateDebut() {
		return saisonDateDebut;
	}

	public void setSaisonDateDebut(LocalDate saisonDateDebut) {
		this.saisonDateDebut = saisonDateDebut;
		this.saisonDateDebutCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setSaisonDateDebut(Instant o) {
		this.saisonDateDebut = o == null ? null : LocalDate.from(o);
		this.saisonDateDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setSaisonDateDebut(String o) {
		this.saisonDateDebut = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.saisonDateDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setSaisonDateDebut(Date o) {
		this.saisonDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.saisonDateDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire saisonDateDebutInit() {
		if(!saisonDateDebutCouverture.dejaInitialise) {
			_saisonDateDebut(saisonDateDebutCouverture);
			if(saisonDateDebut == null)
				setSaisonDateDebut(saisonDateDebutCouverture.o);
		}
		saisonDateDebutCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrSaisonDateDebut() {
		return saisonDateDebut == null ? null : Date.from(saisonDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strSaisonDateDebut() {
		return saisonDateDebut == null ? "" : saisonDateDebut.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonSaisonDateDebut() {
		return saisonDateDebut == null ? "" : saisonDateDebut.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSaisonDateDebut() {
		return "début de l'année";
	}

	public String htmTooltipSaisonDateDebut() {
		return null;
	}

	public String htmSaisonDateDebut() {
		return saisonDateDebut == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonDateDebut());
	}

	///////////////////////////
	// anneeFraisInscription //
	///////////////////////////

	/**	 L'entité anneeFraisInscription
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal anneeFraisInscription;
	@JsonIgnore
	public Couverture<BigDecimal> anneeFraisInscriptionCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("anneeFraisInscription").o(anneeFraisInscription);

	/**	<br/> L'entité anneeFraisInscription
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFraisInscription">Trouver l'entité anneeFraisInscription dans Solr</a>
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
	// sessionDateDebut //
	//////////////////////

	/**	 L'entité sessionDateDebut
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionDateDebut;
	@JsonIgnore
	public Couverture<LocalDate> sessionDateDebutCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("sessionDateDebut").o(sessionDateDebut);

	/**	<br/> L'entité sessionDateDebut
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionDateDebut">Trouver l'entité sessionDateDebut dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionDateDebut(Couverture<LocalDate> c);

	public LocalDate getSessionDateDebut() {
		return sessionDateDebut;
	}

	public void setSessionDateDebut(LocalDate sessionDateDebut) {
		this.sessionDateDebut = sessionDateDebut;
		this.sessionDateDebutCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setSessionDateDebut(Instant o) {
		this.sessionDateDebut = o == null ? null : LocalDate.from(o);
		this.sessionDateDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setSessionDateDebut(String o) {
		this.sessionDateDebut = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionDateDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setSessionDateDebut(Date o) {
		this.sessionDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateDebutCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire sessionDateDebutInit() {
		if(!sessionDateDebutCouverture.dejaInitialise) {
			_sessionDateDebut(sessionDateDebutCouverture);
			if(sessionDateDebut == null)
				setSessionDateDebut(sessionDateDebutCouverture.o);
		}
		sessionDateDebutCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrSessionDateDebut() {
		return sessionDateDebut == null ? null : Date.from(sessionDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strSessionDateDebut() {
		return sessionDateDebut == null ? "" : sessionDateDebut.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonSessionDateDebut() {
		return sessionDateDebut == null ? "" : sessionDateDebut.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSessionDateDebut() {
		return "début de la session";
	}

	public String htmTooltipSessionDateDebut() {
		return null;
	}

	public String htmSessionDateDebut() {
		return sessionDateDebut == null ? "" : StringEscapeUtils.escapeHtml4(strSessionDateDebut());
	}

	////////////////////
	// sessionDateFin //
	////////////////////

	/**	 L'entité sessionDateFin
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionDateFin;
	@JsonIgnore
	public Couverture<LocalDate> sessionDateFinCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("sessionDateFin").o(sessionDateFin);

	/**	<br/> L'entité sessionDateFin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionDateFin">Trouver l'entité sessionDateFin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionDateFin(Couverture<LocalDate> c);

	public LocalDate getSessionDateFin() {
		return sessionDateFin;
	}

	public void setSessionDateFin(LocalDate sessionDateFin) {
		this.sessionDateFin = sessionDateFin;
		this.sessionDateFinCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setSessionDateFin(Instant o) {
		this.sessionDateFin = o == null ? null : LocalDate.from(o);
		this.sessionDateFinCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setSessionDateFin(String o) {
		this.sessionDateFin = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionDateFinCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setSessionDateFin(Date o) {
		this.sessionDateFin = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateFinCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire sessionDateFinInit() {
		if(!sessionDateFinCouverture.dejaInitialise) {
			_sessionDateFin(sessionDateFinCouverture);
			if(sessionDateFin == null)
				setSessionDateFin(sessionDateFinCouverture.o);
		}
		sessionDateFinCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrSessionDateFin() {
		return sessionDateFin == null ? null : Date.from(sessionDateFin.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strSessionDateFin() {
		return sessionDateFin == null ? "" : sessionDateFin.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonSessionDateFin() {
		return sessionDateFin == null ? "" : sessionDateFin.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSessionDateFin() {
		return "fin de la session";
	}

	public String htmTooltipSessionDateFin() {
		return null;
	}

	public String htmSessionDateFin() {
		return sessionDateFin == null ? "" : StringEscapeUtils.escapeHtml4(strSessionDateFin());
	}

	///////////////////
	// ageNomComplet //
	///////////////////

	/**	 L'entité ageNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ageNomComplet;
	@JsonIgnore
	public Couverture<String> ageNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("ageNomComplet").o(ageNomComplet);

	/**	<br/> L'entité ageNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageNomComplet">Trouver l'entité ageNomComplet dans Solr</a>
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

	/**	 L'entité ageDebut
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageDebut;
	@JsonIgnore
	public Couverture<Integer> ageDebutCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ageDebut").o(ageDebut);

	/**	<br/> L'entité ageDebut
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageDebut">Trouver l'entité ageDebut dans Solr</a>
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

	/**	 L'entité ageFin
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageFin;
	@JsonIgnore
	public Couverture<Integer> ageFinCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ageFin").o(ageFin);

	/**	<br/> L'entité ageFin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageFin">Trouver l'entité ageFin dans Solr</a>
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

	/**	 L'entité blocHeureDebut
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected LocalTime blocHeureDebut;
	@JsonIgnore
	public Couverture<LocalTime> blocHeureDebutCouverture = new Couverture<LocalTime>().p(this).c(LocalTime.class).var("blocHeureDebut").o(blocHeureDebut);

	/**	<br/> L'entité blocHeureDebut
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocHeureDebut">Trouver l'entité blocHeureDebut dans Solr</a>
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
			this.blocHeureDebut = o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
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
		return blocHeureDebut == null ? "" : blocHeureDebut.format(DateTimeFormatter.ofPattern("H'h'mm", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonBlocHeureDebut() {
		return blocHeureDebut == null ? "" : blocHeureDebut.format(DateTimeFormatter.ISO_TIME);
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

	/**	 L'entité blocHeureFin
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected LocalTime blocHeureFin;
	@JsonIgnore
	public Couverture<LocalTime> blocHeureFinCouverture = new Couverture<LocalTime>().p(this).c(LocalTime.class).var("blocHeureFin").o(blocHeureFin);

	/**	<br/> L'entité blocHeureFin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocHeureFin">Trouver l'entité blocHeureFin dans Solr</a>
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
			this.blocHeureFin = o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
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
		return blocHeureFin == null ? "" : blocHeureFin.format(DateTimeFormatter.ofPattern("H'h'mm", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonBlocHeureFin() {
		return blocHeureFin == null ? "" : blocHeureFin.format(DateTimeFormatter.ISO_TIME);
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

	/**	 L'entité blocPrixParMois
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal blocPrixParMois;
	@JsonIgnore
	public Couverture<BigDecimal> blocPrixParMoisCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("blocPrixParMois").o(blocPrixParMois);

	/**	<br/> L'entité blocPrixParMois
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocPrixParMois">Trouver l'entité blocPrixParMois dans Solr</a>
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

	/**	 L'entité blocDimanche
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blocDimanche;
	@JsonIgnore
	public Couverture<Boolean> blocDimancheCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocDimanche").o(blocDimanche);

	/**	<br/> L'entité blocDimanche
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocDimanche">Trouver l'entité blocDimanche dans Solr</a>
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

	/**	 L'entité blocLundi
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blocLundi;
	@JsonIgnore
	public Couverture<Boolean> blocLundiCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocLundi").o(blocLundi);

	/**	<br/> L'entité blocLundi
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocLundi">Trouver l'entité blocLundi dans Solr</a>
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

	/**	 L'entité blocMardi
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blocMardi;
	@JsonIgnore
	public Couverture<Boolean> blocMardiCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocMardi").o(blocMardi);

	/**	<br/> L'entité blocMardi
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocMardi">Trouver l'entité blocMardi dans Solr</a>
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

	/**	 L'entité blocMercredi
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blocMercredi;
	@JsonIgnore
	public Couverture<Boolean> blocMercrediCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocMercredi").o(blocMercredi);

	/**	<br/> L'entité blocMercredi
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocMercredi">Trouver l'entité blocMercredi dans Solr</a>
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

	/**	 L'entité blocJeudi
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blocJeudi;
	@JsonIgnore
	public Couverture<Boolean> blocJeudiCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocJeudi").o(blocJeudi);

	/**	<br/> L'entité blocJeudi
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocJeudi">Trouver l'entité blocJeudi dans Solr</a>
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

	/**	 L'entité blocVendredi
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blocVendredi;
	@JsonIgnore
	public Couverture<Boolean> blocVendrediCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocVendredi").o(blocVendredi);

	/**	<br/> L'entité blocVendredi
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocVendredi">Trouver l'entité blocVendredi dans Solr</a>
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

	/**	 L'entité blocSamedi
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean blocSamedi;
	@JsonIgnore
	public Couverture<Boolean> blocSamediCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("blocSamedi").o(blocSamedi);

	/**	<br/> L'entité blocSamedi
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocSamedi">Trouver l'entité blocSamedi dans Solr</a>
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

	/**	 L'entité blocPrixTotal
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal blocPrixTotal;
	@JsonIgnore
	public Couverture<BigDecimal> blocPrixTotalCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("blocPrixTotal").o(blocPrixTotal);

	/**	<br/> L'entité blocPrixTotal
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocPrixTotal">Trouver l'entité blocPrixTotal dans Solr</a>
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

	/**	 L'entité blocNomAdmin
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blocNomAdmin;
	@JsonIgnore
	public Couverture<String> blocNomAdminCouverture = new Couverture<String>().p(this).c(String.class).var("blocNomAdmin").o(blocNomAdmin);

	/**	<br/> L'entité blocNomAdmin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocNomAdmin">Trouver l'entité blocNomAdmin dans Solr</a>
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

	/**	 L'entité blocNomCourt
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blocNomCourt;
	@JsonIgnore
	public Couverture<String> blocNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("blocNomCourt").o(blocNomCourt);

	/**	<br/> L'entité blocNomCourt
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocNomCourt">Trouver l'entité blocNomCourt dans Solr</a>
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

	/**	 L'entité blocNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String blocNomComplet;
	@JsonIgnore
	public Couverture<String> blocNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("blocNomComplet").o(blocNomComplet);

	/**	<br/> L'entité blocNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocNomComplet">Trouver l'entité blocNomComplet dans Solr</a>
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

	/**	 L'entité inscriptionApprouve
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean inscriptionApprouve;
	@JsonIgnore
	public Couverture<Boolean> inscriptionApprouveCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("inscriptionApprouve").o(inscriptionApprouve);

	/**	<br/> L'entité inscriptionApprouve
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionApprouve">Trouver l'entité inscriptionApprouve dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
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
				a("class", "setInscriptionApprouve classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionApprouve w3-input w3-border ");
				a("name", "setInscriptionApprouve");
			} else {
				a("class", "valeurInscriptionApprouve classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionApprouve w3-input w3-border ");
				a("name", "inscriptionApprouve");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionApprouve', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionApprouve')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionApprouve')); }); ");
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

		} else {
			sx(htmInscriptionApprouve());
		}
	}

	public void htmInscriptionApprouve(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionApprouve").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 L'entité inscriptionImmunisations
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean inscriptionImmunisations;
	@JsonIgnore
	public Couverture<Boolean> inscriptionImmunisationsCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("inscriptionImmunisations").o(inscriptionImmunisations);

	/**	<br/> L'entité inscriptionImmunisations
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionImmunisations">Trouver l'entité inscriptionImmunisations dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
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
				a("class", "setInscriptionImmunisations classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionImmunisations w3-input w3-border ");
				a("name", "setInscriptionImmunisations");
			} else {
				a("class", "valeurInscriptionImmunisations classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionImmunisations w3-input w3-border ");
				a("name", "inscriptionImmunisations");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionImmunisations', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionImmunisations')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionImmunisations')); }); ");
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

		} else {
			sx(htmInscriptionImmunisations());
		}
	}

	public void htmInscriptionImmunisations(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionImmunisations").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	///////////
	// photo //
	///////////

	/**	 L'entité photo
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String photo;
	@JsonIgnore
	public Couverture<String> photoCouverture = new Couverture<String>().p(this).c(String.class).var("photo").o(photo);

	/**	<br/> L'entité photo
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:photo">Trouver l'entité photo dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _photo(Couverture<String> c);

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
		this.photoCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire photoInit() {
		if(!photoCouverture.dejaInitialise) {
			_photo(photoCouverture);
			if(photo == null)
				setPhoto(photoCouverture.o);
		}
		photoCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrPhoto() {
		return photo;
	}

	public String strPhoto() {
		return photo == null ? "" : photo;
	}

	public String jsonPhoto() {
		return photo == null ? "" : photo;
	}

	public String nomAffichagePhoto() {
		return "photo";
	}

	public String htmTooltipPhoto() {
		return null;
	}

	public String htmPhoto() {
		return photo == null ? "" : StringEscapeUtils.escapeHtml4(strPhoto());
	}

	public void inputPhoto(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "imageBase64Div1InscriptionScolaire_photo").a("id", "imageBase64Div1InscriptionScolaire", pk, "photo").f();
				e("h5").f().sx("Télécharger image").g("h5"); 
				e("form").a("method", "POST").a("enctype", "multipart/form-data").a("action", "/photo").a("class", "").f();
				e("input").a("type", "hidden").a("name", "pk").a("value", pk).fg(); 
				e("input").a("type", "hidden").a("name", "classeNomSimple").a("value", "InscriptionScolaire").fg(); 
				e("input").a("name", "fichier").a("type", "file").a("onchange", "$.ajax({ type: 'POST', enctype: 'multipart/form-data', url: '/photo', data: new FormData(this.form), processData: false, contentType: false}); ").fg(); 
				g("form");
				e("img").a("id", "imageBase64ImgInscriptionScolaire", pk, "photo");
					a("class", "imgInscriptionScolaire", pk, "Photo w3-image ");
					a("src", StringUtils.isBlank(photo) ? "data:image/png;base64," : photo).a("alt", "");
				fg();
			g("div");
		} else {
			sx(htmPhoto());
		}
	}

	public void htmPhoto(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolairePhoto").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_photo").a("class", "").f().sx("photo").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPhoto(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_photo')); $('#", classeApiMethodeMethode, "_photo').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setPhoto', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_photo')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_photo')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// familleMarie //
	//////////////////

	/**	 L'entité familleMarie
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean familleMarie;
	@JsonIgnore
	public Couverture<Boolean> familleMarieCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("familleMarie").o(familleMarie);

	/**	<br/> L'entité familleMarie
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleMarie">Trouver l'entité familleMarie dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
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
				a("class", "setFamilleMarie classInscriptionScolaire inputInscriptionScolaire", pk, "FamilleMarie w3-input w3-border ");
				a("name", "setFamilleMarie");
			} else {
				a("class", "valeurFamilleMarie classInscriptionScolaire inputInscriptionScolaire", pk, "FamilleMarie w3-input w3-border ");
				a("name", "familleMarie");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleMarie', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleMarie')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleMarie')); }); ");
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

		} else {
			sx(htmFamilleMarie());
		}
	}

	public void htmFamilleMarie(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireFamilleMarie").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 L'entité familleSepare
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean familleSepare;
	@JsonIgnore
	public Couverture<Boolean> familleSepareCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("familleSepare").o(familleSepare);

	/**	<br/> L'entité familleSepare
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleSepare">Trouver l'entité familleSepare dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
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
				a("class", "setFamilleSepare classInscriptionScolaire inputInscriptionScolaire", pk, "FamilleSepare w3-input w3-border ");
				a("name", "setFamilleSepare");
			} else {
				a("class", "valeurFamilleSepare classInscriptionScolaire inputInscriptionScolaire", pk, "FamilleSepare w3-input w3-border ");
				a("name", "familleSepare");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleSepare', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleSepare')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleSepare')); }); ");
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

		} else {
			sx(htmFamilleSepare());
		}
	}

	public void htmFamilleSepare(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireFamilleSepare").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 L'entité familleDivorce
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean familleDivorce;
	@JsonIgnore
	public Couverture<Boolean> familleDivorceCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("familleDivorce").o(familleDivorce);

	/**	<br/> L'entité familleDivorce
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleDivorce">Trouver l'entité familleDivorce dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
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
				a("class", "setFamilleDivorce classInscriptionScolaire inputInscriptionScolaire", pk, "FamilleDivorce w3-input w3-border ");
				a("name", "setFamilleDivorce");
			} else {
				a("class", "valeurFamilleDivorce classInscriptionScolaire inputInscriptionScolaire", pk, "FamilleDivorce w3-input w3-border ");
				a("name", "familleDivorce");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleDivorce', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleDivorce')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleDivorce')); }); ");
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

		} else {
			sx(htmFamilleDivorce());
		}
	}

	public void htmFamilleDivorce(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireFamilleDivorce").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 L'entité familleAddresse
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String familleAddresse;
	@JsonIgnore
	public Couverture<String> familleAddresseCouverture = new Couverture<String>().p(this).c(String.class).var("familleAddresse").o(familleAddresse);

	/**	<br/> L'entité familleAddresse
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleAddresse">Trouver l'entité familleAddresse dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "addresse de la famille")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_familleAddresse");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setFamilleAddresse classInscriptionScolaire inputInscriptionScolaire", pk, "FamilleAddresse w3-input w3-border ");
					a("name", "setFamilleAddresse");
				} else {
					a("class", "valeurFamilleAddresse w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "FamilleAddresse w3-input w3-border ");
					a("name", "familleAddresse");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleAddresse', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleAddresse')); }); ");
				}
			f().sx(strFamilleAddresse()).g("textarea");

		} else {
			sx(htmFamilleAddresse());
		}
	}

	public void htmFamilleAddresse(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireFamilleAddresse").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_familleAddresse").a("class", "").f().sx("addresse de la famille").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilleAddresse(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_familleAddresse')); $('#", classeApiMethodeMethode, "_familleAddresse').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setFamilleAddresse', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleAddresse')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité familleCommentVousConnaissezEcole
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String familleCommentVousConnaissezEcole;
	@JsonIgnore
	public Couverture<String> familleCommentVousConnaissezEcoleCouverture = new Couverture<String>().p(this).c(String.class).var("familleCommentVousConnaissezEcole").o(familleCommentVousConnaissezEcole);

	/**	<br/> L'entité familleCommentVousConnaissezEcole
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleCommentVousConnaissezEcole">Trouver l'entité familleCommentVousConnaissezEcole dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "comment vous connaissez l'école ? ")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setFamilleCommentVousConnaissezEcole classInscriptionScolaire inputInscriptionScolaire", pk, "FamilleCommentVousConnaissezEcole w3-input w3-border ");
					a("name", "setFamilleCommentVousConnaissezEcole");
				} else {
					a("class", "valeurFamilleCommentVousConnaissezEcole w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "FamilleCommentVousConnaissezEcole w3-input w3-border ");
					a("name", "familleCommentVousConnaissezEcole");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleCommentVousConnaissezEcole', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole')); }); ");
				}
			f().sx(strFamilleCommentVousConnaissezEcole()).g("textarea");

		} else {
			sx(htmFamilleCommentVousConnaissezEcole());
		}
	}

	public void htmFamilleCommentVousConnaissezEcole(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireFamilleCommentVousConnaissezEcole").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole").a("class", "").f().sx("comment vous connaissez l'école ? ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilleCommentVousConnaissezEcole(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole')); $('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setFamilleCommentVousConnaissezEcole', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleCommentVousConnaissezEcole')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionConsiderationsSpeciales
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionConsiderationsSpeciales;
	@JsonIgnore
	public Couverture<String> inscriptionConsiderationsSpecialesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionConsiderationsSpeciales").o(inscriptionConsiderationsSpeciales);

	/**	<br/> L'entité inscriptionConsiderationsSpeciales
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionConsiderationsSpeciales">Trouver l'entité inscriptionConsiderationsSpeciales dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "considérations spéciale")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setInscriptionConsiderationsSpeciales classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionConsiderationsSpeciales w3-input w3-border ");
					a("name", "setInscriptionConsiderationsSpeciales");
				} else {
					a("class", "valeurInscriptionConsiderationsSpeciales w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionConsiderationsSpeciales w3-input w3-border ");
					a("name", "inscriptionConsiderationsSpeciales");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionConsiderationsSpeciales', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales')); }); ");
				}
			f().sx(strInscriptionConsiderationsSpeciales()).g("textarea");

		} else {
			sx(htmInscriptionConsiderationsSpeciales());
		}
	}

	public void htmInscriptionConsiderationsSpeciales(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionConsiderationsSpeciales").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales").a("class", "").f().sx("considérations spéciale").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionConsiderationsSpeciales(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales')); $('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionConsiderationsSpeciales', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionConsiderationsSpeciales')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité enfantConditionsMedicales
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantConditionsMedicales;
	@JsonIgnore
	public Couverture<String> enfantConditionsMedicalesCouverture = new Couverture<String>().p(this).c(String.class).var("enfantConditionsMedicales").o(enfantConditionsMedicales);

	/**	<br/> L'entité enfantConditionsMedicales
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantConditionsMedicales">Trouver l'entité enfantConditionsMedicales dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "conditions médicales")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_enfantConditionsMedicales");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEnfantConditionsMedicales classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantConditionsMedicales w3-input w3-border ");
					a("name", "setEnfantConditionsMedicales");
				} else {
					a("class", "valeurEnfantConditionsMedicales w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantConditionsMedicales w3-input w3-border ");
					a("name", "enfantConditionsMedicales");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantConditionsMedicales', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantConditionsMedicales')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantConditionsMedicales')); }); ");
				}
			f().sx(strEnfantConditionsMedicales()).g("textarea");

		} else {
			sx(htmEnfantConditionsMedicales());
		}
	}

	public void htmEnfantConditionsMedicales(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantConditionsMedicales").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantConditionsMedicales").a("class", "").f().sx("conditions médicales").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantConditionsMedicales(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantConditionsMedicales')); $('#", classeApiMethodeMethode, "_enfantConditionsMedicales').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantConditionsMedicales', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantConditionsMedicales')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantConditionsMedicales')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité enfantEcolesPrecedemmentFrequentees
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantEcolesPrecedemmentFrequentees;
	@JsonIgnore
	public Couverture<String> enfantEcolesPrecedemmentFrequenteesCouverture = new Couverture<String>().p(this).c(String.class).var("enfantEcolesPrecedemmentFrequentees").o(enfantEcolesPrecedemmentFrequentees);

	/**	<br/> L'entité enfantEcolesPrecedemmentFrequentees
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantEcolesPrecedemmentFrequentees">Trouver l'entité enfantEcolesPrecedemmentFrequentees dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "écoles précedemment fréqentées")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEnfantEcolesPrecedemmentFrequentees classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantEcolesPrecedemmentFrequentees w3-input w3-border ");
					a("name", "setEnfantEcolesPrecedemmentFrequentees");
				} else {
					a("class", "valeurEnfantEcolesPrecedemmentFrequentees w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantEcolesPrecedemmentFrequentees w3-input w3-border ");
					a("name", "enfantEcolesPrecedemmentFrequentees");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantEcolesPrecedemmentFrequentees', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees')); }); ");
				}
			f().sx(strEnfantEcolesPrecedemmentFrequentees()).g("textarea");

		} else {
			sx(htmEnfantEcolesPrecedemmentFrequentees());
		}
	}

	public void htmEnfantEcolesPrecedemmentFrequentees(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantEcolesPrecedemmentFrequentees").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees").a("class", "").f().sx("écoles précedemment fréqentées").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantEcolesPrecedemmentFrequentees(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees')); $('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantEcolesPrecedemmentFrequentees', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantEcolesPrecedemmentFrequentees')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité enfantDescription
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantDescription;
	@JsonIgnore
	public Couverture<String> enfantDescriptionCouverture = new Couverture<String>().p(this).c(String.class).var("enfantDescription").o(enfantDescription);

	/**	<br/> L'entité enfantDescription
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantDescription">Trouver l'entité enfantDescription dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "description")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_enfantDescription");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEnfantDescription classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantDescription w3-input w3-border ");
					a("name", "setEnfantDescription");
				} else {
					a("class", "valeurEnfantDescription w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantDescription w3-input w3-border ");
					a("name", "enfantDescription");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantDescription', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantDescription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantDescription')); }); ");
				}
			f().sx(strEnfantDescription()).g("textarea");

		} else {
			sx(htmEnfantDescription());
		}
	}

	public void htmEnfantDescription(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantDescription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantDescription").a("class", "").f().sx("description").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantDescription(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantDescription')); $('#", classeApiMethodeMethode, "_enfantDescription').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantDescription', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantDescription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantDescription')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité enfantObjectifs
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantObjectifs;
	@JsonIgnore
	public Couverture<String> enfantObjectifsCouverture = new Couverture<String>().p(this).c(String.class).var("enfantObjectifs").o(enfantObjectifs);

	/**	<br/> L'entité enfantObjectifs
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantObjectifs">Trouver l'entité enfantObjectifs dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("textarea")
				.a("placeholder", "objectifs")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_enfantObjectifs");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEnfantObjectifs classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantObjectifs w3-input w3-border ");
					a("name", "setEnfantObjectifs");
				} else {
					a("class", "valeurEnfantObjectifs w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantObjectifs w3-input w3-border ");
					a("name", "enfantObjectifs");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantObjectifs', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantObjectifs')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantObjectifs')); }); ");
				}
			f().sx(strEnfantObjectifs()).g("textarea");

		} else {
			sx(htmEnfantObjectifs());
		}
	}

	public void htmEnfantObjectifs(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantObjectifs").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantObjectifs").a("class", "").f().sx("objectifs").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantObjectifs(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantObjectifs')); $('#", classeApiMethodeMethode, "_enfantObjectifs').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setEnfantObjectifs', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantObjectifs')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantObjectifs')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité enfantPropre
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean enfantPropre;
	@JsonIgnore
	public Couverture<Boolean> enfantPropreCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("enfantPropre").o(enfantPropre);

	/**	<br/> L'entité enfantPropre
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantPropre">Trouver l'entité enfantPropre dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
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
				a("class", "setEnfantPropre classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantPropre w3-input w3-border ");
				a("name", "setEnfantPropre");
			} else {
				a("class", "valeurEnfantPropre classInscriptionScolaire inputInscriptionScolaire", pk, "EnfantPropre w3-input w3-border ");
				a("name", "enfantPropre");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantPropre', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantPropre')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantPropre')); }); ");
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

		} else {
			sx(htmEnfantPropre());
		}
	}

	public void htmEnfantPropre(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantPropre").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 L'entité inscriptionNomGroupe
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionNomGroupe;
	@JsonIgnore
	public Couverture<String> inscriptionNomGroupeCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionNomGroupe").o(inscriptionNomGroupe);

	/**	<br/> L'entité inscriptionNomGroupe
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNomGroupe">Trouver l'entité inscriptionNomGroupe dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "nom du groupe")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_inscriptionNomGroupe");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setInscriptionNomGroupe classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionNomGroupe w3-input w3-border ");
					a("name", "setInscriptionNomGroupe");
				} else {
					a("class", "valeurInscriptionNomGroupe w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionNomGroupe w3-input w3-border ");
					a("name", "inscriptionNomGroupe");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionNomGroupe', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionNomGroupe')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionNomGroupe')); }); ");
				}
				a("value", strInscriptionNomGroupe())
			.fg();

		} else {
			sx(htmInscriptionNomGroupe());
		}
	}

	public void htmInscriptionNomGroupe(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionNomGroupe").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_inscriptionNomGroupe").a("class", "").f().sx("nom du groupe").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionNomGroupe(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionNomGroupe')); $('#", classeApiMethodeMethode, "_inscriptionNomGroupe').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionNomGroupe', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionNomGroupe')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionNomGroupe')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////////////
	// inscriptionCouleurGroupe //
	//////////////////////////////

	/**	 L'entité inscriptionCouleurGroupe
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionCouleurGroupe;
	@JsonIgnore
	public Couverture<String> inscriptionCouleurGroupeCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionCouleurGroupe").o(inscriptionCouleurGroupe);

	/**	<br/> L'entité inscriptionCouleurGroupe
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCouleurGroupe">Trouver l'entité inscriptionCouleurGroupe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionCouleurGroupe(Couverture<String> c);

	public String getInscriptionCouleurGroupe() {
		return inscriptionCouleurGroupe;
	}

	public void setInscriptionCouleurGroupe(String inscriptionCouleurGroupe) {
		this.inscriptionCouleurGroupe = inscriptionCouleurGroupe;
		this.inscriptionCouleurGroupeCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionCouleurGroupeInit() {
		if(!inscriptionCouleurGroupeCouverture.dejaInitialise) {
			_inscriptionCouleurGroupe(inscriptionCouleurGroupeCouverture);
			if(inscriptionCouleurGroupe == null)
				setInscriptionCouleurGroupe(inscriptionCouleurGroupeCouverture.o);
		}
		inscriptionCouleurGroupeCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionCouleurGroupe() {
		return inscriptionCouleurGroupe;
	}

	public String strInscriptionCouleurGroupe() {
		return inscriptionCouleurGroupe == null ? "" : inscriptionCouleurGroupe;
	}

	public String jsonInscriptionCouleurGroupe() {
		return inscriptionCouleurGroupe == null ? "" : inscriptionCouleurGroupe;
	}

	public String nomAffichageInscriptionCouleurGroupe() {
		return "couleur du groupe";
	}

	public String htmTooltipInscriptionCouleurGroupe() {
		return null;
	}

	public String htmInscriptionCouleurGroupe() {
		return inscriptionCouleurGroupe == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionCouleurGroupe());
	}

	//////////////////////////////////
	// inscriptionPaimentChaqueMois //
	//////////////////////////////////

	/**	 L'entité inscriptionPaimentChaqueMois
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean inscriptionPaimentChaqueMois;
	@JsonIgnore
	public Couverture<Boolean> inscriptionPaimentChaqueMoisCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("inscriptionPaimentChaqueMois").o(inscriptionPaimentChaqueMois);

	/**	<br/> L'entité inscriptionPaimentChaqueMois
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionPaimentChaqueMois">Trouver l'entité inscriptionPaimentChaqueMois dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
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
				a("class", "setInscriptionPaimentChaqueMois classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionPaimentChaqueMois w3-input w3-border ");
				a("name", "setInscriptionPaimentChaqueMois");
			} else {
				a("class", "valeurInscriptionPaimentChaqueMois classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionPaimentChaqueMois w3-input w3-border ");
				a("name", "inscriptionPaimentChaqueMois");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionPaimentChaqueMois', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionPaimentChaqueMois')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionPaimentChaqueMois')); }); ");
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

		} else {
			sx(htmInscriptionPaimentChaqueMois());
		}
	}

	public void htmInscriptionPaimentChaqueMois(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionPaimentChaqueMois").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	/**	 L'entité inscriptionPaimentComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean inscriptionPaimentComplet;
	@JsonIgnore
	public Couverture<Boolean> inscriptionPaimentCompletCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("inscriptionPaimentComplet").o(inscriptionPaimentComplet);

	/**	<br/> L'entité inscriptionPaimentComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionPaimentComplet">Trouver l'entité inscriptionPaimentComplet dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
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
				a("class", "setInscriptionPaimentComplet classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionPaimentComplet w3-input w3-border ");
				a("name", "setInscriptionPaimentComplet");
			} else {
				a("class", "valeurInscriptionPaimentComplet classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionPaimentComplet w3-input w3-border ");
				a("name", "inscriptionPaimentComplet");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionPaimentComplet', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionPaimentComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionPaimentComplet')); }); ");
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

		} else {
			sx(htmInscriptionPaimentComplet());
		}
	}

	public void htmInscriptionPaimentComplet(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionPaimentComplet").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
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

	///////////////////////
	// customerProfileId //
	///////////////////////

	/**	 L'entité customerProfileId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId;
	@JsonIgnore
	public Couverture<String> customerProfileIdCouverture = new Couverture<String>().p(this).c(String.class).var("customerProfileId").o(customerProfileId);

	/**	<br/> L'entité customerProfileId
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:customerProfileId">Trouver l'entité customerProfileId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _customerProfileId(Couverture<String> c);

	public String getCustomerProfileId() {
		return customerProfileId;
	}

	public void setCustomerProfileId(String customerProfileId) {
		this.customerProfileId = customerProfileId;
		this.customerProfileIdCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire customerProfileIdInit() {
		if(!customerProfileIdCouverture.dejaInitialise) {
			_customerProfileId(customerProfileIdCouverture);
			if(customerProfileId == null)
				setCustomerProfileId(customerProfileIdCouverture.o);
		}
		customerProfileIdCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrCustomerProfileId() {
		return customerProfileId;
	}

	public String strCustomerProfileId() {
		return customerProfileId == null ? "" : customerProfileId;
	}

	public String jsonCustomerProfileId() {
		return customerProfileId == null ? "" : customerProfileId;
	}

	public String nomAffichageCustomerProfileId() {
		return "customer profile ID";
	}

	public String htmTooltipCustomerProfileId() {
		return null;
	}

	public String htmCustomerProfileId() {
		return customerProfileId == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId());
	}

	public void inputCustomerProfileId(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "customer profile ID")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_customerProfileId");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setCustomerProfileId classInscriptionScolaire inputInscriptionScolaire", pk, "CustomerProfileId w3-input w3-border ");
					a("name", "setCustomerProfileId");
				} else {
					a("class", "valeurCustomerProfileId w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "CustomerProfileId w3-input w3-border ");
					a("name", "customerProfileId");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_customerProfileId')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_customerProfileId')); }); ");
				}
				a("value", strCustomerProfileId())
			.fg();

		} else {
			sx(htmCustomerProfileId());
		}
	}

	public void htmCustomerProfileId(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireCustomerProfileId").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_customerProfileId").a("class", "").f().sx("customer profile ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_customerProfileId')); $('#", classeApiMethodeMethode, "_customerProfileId').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setCustomerProfileId', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_customerProfileId')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_customerProfileId')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////////
	// inscriptionDateFrais //
	//////////////////////////

	/**	 L'entité inscriptionDateFrais
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate inscriptionDateFrais;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDateFraisCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDateFrais").o(inscriptionDateFrais);

	/**	<br/> L'entité inscriptionDateFrais
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDateFrais">Trouver l'entité inscriptionDateFrais dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionDateFrais(Couverture<LocalDate> c);

	public LocalDate getInscriptionDateFrais() {
		return inscriptionDateFrais;
	}

	public void setInscriptionDateFrais(LocalDate inscriptionDateFrais) {
		this.inscriptionDateFrais = inscriptionDateFrais;
		this.inscriptionDateFraisCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setInscriptionDateFrais(Instant o) {
		this.inscriptionDateFrais = o == null ? null : LocalDate.from(o);
		this.inscriptionDateFraisCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDateFrais(String o) {
		this.inscriptionDateFrais = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.inscriptionDateFraisCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDateFrais(Date o) {
		this.inscriptionDateFrais = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDateFraisCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionDateFraisInit() {
		if(!inscriptionDateFraisCouverture.dejaInitialise) {
			_inscriptionDateFrais(inscriptionDateFraisCouverture);
			if(inscriptionDateFrais == null)
				setInscriptionDateFrais(inscriptionDateFraisCouverture.o);
		}
		inscriptionDateFraisCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Date solrInscriptionDateFrais() {
		return inscriptionDateFrais == null ? null : Date.from(inscriptionDateFrais.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strInscriptionDateFrais() {
		return inscriptionDateFrais == null ? "" : inscriptionDateFrais.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonInscriptionDateFrais() {
		return inscriptionDateFrais == null ? "" : inscriptionDateFrais.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageInscriptionDateFrais() {
		return null;
	}

	public String htmTooltipInscriptionDateFrais() {
		return null;
	}

	public String htmInscriptionDateFrais() {
		return inscriptionDateFrais == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionDateFrais());
	}

	public void inputInscriptionDateFrais(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setInscriptionDateFrais classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionDateFrais w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_inscriptionDateFrais")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", inscriptionDateFrais == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDateFrais))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDateFrais', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDateFrais')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDateFrais')); }); } ")
				.fg();
		} else {
			sx(htmInscriptionDateFrais());
		}
	}

	public void htmInscriptionDateFrais(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDateFrais").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDateFrais(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDateFrais')); $('#", classeApiMethodeMethode, "_inscriptionDateFrais').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDateFrais', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDateFrais')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDateFrais')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////
	// paiementFacets //
	////////////////////

	/**	 L'entité paiementFacets
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SimpleOrderedMap paiementFacets;
	@JsonIgnore
	public Couverture<SimpleOrderedMap> paiementFacetsCouverture = new Couverture<SimpleOrderedMap>().p(this).c(SimpleOrderedMap.class).var("paiementFacets").o(paiementFacets);

	/**	<br/> L'entité paiementFacets
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementFacets">Trouver l'entité paiementFacets dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementFacets(Couverture<SimpleOrderedMap> c);

	public SimpleOrderedMap getPaiementFacets() {
		return paiementFacets;
	}

	public void setPaiementFacets(SimpleOrderedMap paiementFacets) {
		this.paiementFacets = paiementFacets;
		this.paiementFacetsCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire paiementFacetsInit() {
		if(!paiementFacetsCouverture.dejaInitialise) {
			_paiementFacets(paiementFacetsCouverture);
			if(paiementFacets == null)
				setPaiementFacets(paiementFacetsCouverture.o);
		}
		paiementFacetsCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	/////////////////////
	// paiementLastStr //
	/////////////////////

	/**	 L'entité paiementLastStr
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paiementLastStr;
	@JsonIgnore
	public Couverture<String> paiementLastStrCouverture = new Couverture<String>().p(this).c(String.class).var("paiementLastStr").o(paiementLastStr);

	/**	<br/> L'entité paiementLastStr
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementLastStr">Trouver l'entité paiementLastStr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementLastStr(Couverture<String> c);

	public String getPaiementLastStr() {
		return paiementLastStr;
	}

	public void setPaiementLastStr(String paiementLastStr) {
		this.paiementLastStr = paiementLastStr;
		this.paiementLastStrCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire paiementLastStrInit() {
		if(!paiementLastStrCouverture.dejaInitialise) {
			_paiementLastStr(paiementLastStrCouverture);
			if(paiementLastStr == null)
				setPaiementLastStr(paiementLastStrCouverture.o);
		}
		paiementLastStrCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrPaiementLastStr() {
		return paiementLastStr;
	}

	public String strPaiementLastStr() {
		return paiementLastStr == null ? "" : paiementLastStr;
	}

	public String jsonPaiementLastStr() {
		return paiementLastStr == null ? "" : paiementLastStr;
	}

	public String nomAffichagePaiementLastStr() {
		return null;
	}

	public String htmTooltipPaiementLastStr() {
		return null;
	}

	public String htmPaiementLastStr() {
		return paiementLastStr == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementLastStr());
	}

	/////////////////////
	// paiementMontant //
	/////////////////////

	/**	 L'entité paiementMontant
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal paiementMontant;
	@JsonIgnore
	public Couverture<BigDecimal> paiementMontantCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("paiementMontant").o(paiementMontant);

	/**	<br/> L'entité paiementMontant
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementMontant">Trouver l'entité paiementMontant dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementMontant(Couverture<BigDecimal> c);

	public BigDecimal getPaiementMontant() {
		return paiementMontant;
	}

	public void setPaiementMontant(BigDecimal paiementMontant) {
		this.paiementMontant = paiementMontant;
		this.paiementMontantCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setPaiementMontant(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.paiementMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementMontantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setPaiementMontant(Double o) {
			this.paiementMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementMontantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setPaiementMontant(Integer o) {
			this.paiementMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementMontantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire paiementMontantInit() {
		if(!paiementMontantCouverture.dejaInitialise) {
			_paiementMontant(paiementMontantCouverture);
			if(paiementMontant == null)
				setPaiementMontant(paiementMontantCouverture.o);
		}
		paiementMontantCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Double solrPaiementMontant() {
		return paiementMontant == null ? null : paiementMontant.doubleValue();
	}

	public String strPaiementMontant() {
		return paiementMontant == null ? "" : paiementMontant.setScale(2).toString();
	}

	public String jsonPaiementMontant() {
		return paiementMontant == null ? "" : paiementMontant.toString();
	}

	public String nomAffichagePaiementMontant() {
		return null;
	}

	public String htmTooltipPaiementMontant() {
		return null;
	}

	public String htmPaiementMontant() {
		return paiementMontant == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementMontant());
	}

	//////////////////
	// fraisMontant //
	//////////////////

	/**	 L'entité fraisMontant
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal fraisMontant;
	@JsonIgnore
	public Couverture<BigDecimal> fraisMontantCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("fraisMontant").o(fraisMontant);

	/**	<br/> L'entité fraisMontant
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontant">Trouver l'entité fraisMontant dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisMontant(Couverture<BigDecimal> c);

	public BigDecimal getFraisMontant() {
		return fraisMontant;
	}

	public void setFraisMontant(BigDecimal fraisMontant) {
		this.fraisMontant = fraisMontant;
		this.fraisMontantCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setFraisMontant(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.fraisMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setFraisMontant(Double o) {
			this.fraisMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setFraisMontant(Integer o) {
			this.fraisMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire fraisMontantInit() {
		if(!fraisMontantCouverture.dejaInitialise) {
			_fraisMontant(fraisMontantCouverture);
			if(fraisMontant == null)
				setFraisMontant(fraisMontantCouverture.o);
		}
		fraisMontantCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Double solrFraisMontant() {
		return fraisMontant == null ? null : fraisMontant.doubleValue();
	}

	public String strFraisMontant() {
		return fraisMontant == null ? "" : fraisMontant.setScale(2).toString();
	}

	public String jsonFraisMontant() {
		return fraisMontant == null ? "" : fraisMontant.toString();
	}

	public String nomAffichageFraisMontant() {
		return null;
	}

	public String htmTooltipFraisMontant() {
		return null;
	}

	public String htmFraisMontant() {
		return fraisMontant == null ? "" : StringEscapeUtils.escapeHtml4(strFraisMontant());
	}

	////////////////////////
	// fraisMontantFuture //
	////////////////////////

	/**	 L'entité fraisMontantFuture
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal fraisMontantFuture;
	@JsonIgnore
	public Couverture<BigDecimal> fraisMontantFutureCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("fraisMontantFuture").o(fraisMontantFuture);

	/**	<br/> L'entité fraisMontantFuture
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontantFuture">Trouver l'entité fraisMontantFuture dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisMontantFuture(Couverture<BigDecimal> c);

	public BigDecimal getFraisMontantFuture() {
		return fraisMontantFuture;
	}

	public void setFraisMontantFuture(BigDecimal fraisMontantFuture) {
		this.fraisMontantFuture = fraisMontantFuture;
		this.fraisMontantFutureCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setFraisMontantFuture(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.fraisMontantFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantFutureCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setFraisMontantFuture(Double o) {
			this.fraisMontantFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantFutureCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setFraisMontantFuture(Integer o) {
			this.fraisMontantFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantFutureCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire fraisMontantFutureInit() {
		if(!fraisMontantFutureCouverture.dejaInitialise) {
			_fraisMontantFuture(fraisMontantFutureCouverture);
			if(fraisMontantFuture == null)
				setFraisMontantFuture(fraisMontantFutureCouverture.o);
		}
		fraisMontantFutureCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Double solrFraisMontantFuture() {
		return fraisMontantFuture == null ? null : fraisMontantFuture.doubleValue();
	}

	public String strFraisMontantFuture() {
		return fraisMontantFuture == null ? "" : fraisMontantFuture.setScale(2).toString();
	}

	public String jsonFraisMontantFuture() {
		return fraisMontantFuture == null ? "" : fraisMontantFuture.toString();
	}

	public String nomAffichageFraisMontantFuture() {
		return null;
	}

	public String htmTooltipFraisMontantFuture() {
		return null;
	}

	public String htmFraisMontantFuture() {
		return fraisMontantFuture == null ? "" : StringEscapeUtils.escapeHtml4(strFraisMontantFuture());
	}

	////////////////////
	// fraisMontantDu //
	////////////////////

	/**	 L'entité fraisMontantDu
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal fraisMontantDu;
	@JsonIgnore
	public Couverture<BigDecimal> fraisMontantDuCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("fraisMontantDu").o(fraisMontantDu);

	/**	<br/> L'entité fraisMontantDu
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontantDu">Trouver l'entité fraisMontantDu dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisMontantDu(Couverture<BigDecimal> c);

	public BigDecimal getFraisMontantDu() {
		return fraisMontantDu;
	}

	public void setFraisMontantDu(BigDecimal fraisMontantDu) {
		this.fraisMontantDu = fraisMontantDu;
		this.fraisMontantDuCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setFraisMontantDu(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.fraisMontantDu = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantDuCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setFraisMontantDu(Double o) {
			this.fraisMontantDu = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantDuCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setFraisMontantDu(Integer o) {
			this.fraisMontantDu = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantDuCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire fraisMontantDuInit() {
		if(!fraisMontantDuCouverture.dejaInitialise) {
			_fraisMontantDu(fraisMontantDuCouverture);
			if(fraisMontantDu == null)
				setFraisMontantDu(fraisMontantDuCouverture.o);
		}
		fraisMontantDuCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Double solrFraisMontantDu() {
		return fraisMontantDu == null ? null : fraisMontantDu.doubleValue();
	}

	public String strFraisMontantDu() {
		return fraisMontantDu == null ? "" : fraisMontantDu.setScale(2).toString();
	}

	public String jsonFraisMontantDu() {
		return fraisMontantDu == null ? "" : fraisMontantDu.toString();
	}

	public String nomAffichageFraisMontantDu() {
		return null;
	}

	public String htmTooltipFraisMontantDu() {
		return null;
	}

	public String htmFraisMontantDu() {
		return fraisMontantDu == null ? "" : StringEscapeUtils.escapeHtml4(strFraisMontantDu());
	}

	/////////////////////
	// fraisMaintenant //
	/////////////////////

	/**	 L'entité fraisMaintenant
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal fraisMaintenant;
	@JsonIgnore
	public Couverture<BigDecimal> fraisMaintenantCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("fraisMaintenant").o(fraisMaintenant);

	/**	<br/> L'entité fraisMaintenant
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMaintenant">Trouver l'entité fraisMaintenant dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisMaintenant(Couverture<BigDecimal> c);

	public BigDecimal getFraisMaintenant() {
		return fraisMaintenant;
	}

	public void setFraisMaintenant(BigDecimal fraisMaintenant) {
		this.fraisMaintenant = fraisMaintenant;
		this.fraisMaintenantCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setFraisMaintenant(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.fraisMaintenant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMaintenantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setFraisMaintenant(Double o) {
			this.fraisMaintenant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMaintenantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setFraisMaintenant(Integer o) {
			this.fraisMaintenant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMaintenantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire fraisMaintenantInit() {
		if(!fraisMaintenantCouverture.dejaInitialise) {
			_fraisMaintenant(fraisMaintenantCouverture);
			if(fraisMaintenant == null)
				setFraisMaintenant(fraisMaintenantCouverture.o);
		}
		fraisMaintenantCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Double solrFraisMaintenant() {
		return fraisMaintenant == null ? null : fraisMaintenant.doubleValue();
	}

	public String strFraisMaintenant() {
		return fraisMaintenant == null ? "" : fraisMaintenant.setScale(2).toString();
	}

	public String jsonFraisMaintenant() {
		return fraisMaintenant == null ? "" : fraisMaintenant.toString();
	}

	public String nomAffichageFraisMaintenant() {
		return null;
	}

	public String htmTooltipFraisMaintenant() {
		return null;
	}

	public String htmFraisMaintenant() {
		return fraisMaintenant == null ? "" : StringEscapeUtils.escapeHtml4(strFraisMaintenant());
	}

	////////////////////
	// paiementsAJour //
	////////////////////

	/**	 L'entité paiementsAJour
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementsAJour;
	@JsonIgnore
	public Couverture<Boolean> paiementsAJourCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementsAJour").o(paiementsAJour);

	/**	<br/> L'entité paiementsAJour
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementsAJour">Trouver l'entité paiementsAJour dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementsAJour(Couverture<Boolean> c);

	public Boolean getPaiementsAJour() {
		return paiementsAJour;
	}

	public void setPaiementsAJour(Boolean paiementsAJour) {
		this.paiementsAJour = paiementsAJour;
		this.paiementsAJourCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setPaiementsAJour(String o) {
		this.paiementsAJour = Boolean.parseBoolean(o);
		this.paiementsAJourCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire paiementsAJourInit() {
		if(!paiementsAJourCouverture.dejaInitialise) {
			_paiementsAJour(paiementsAJourCouverture);
			if(paiementsAJour == null)
				setPaiementsAJour(paiementsAJourCouverture.o);
		}
		paiementsAJourCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrPaiementsAJour() {
		return paiementsAJour;
	}

	public String strPaiementsAJour() {
		return paiementsAJour == null ? "" : paiementsAJour.toString();
	}

	public String jsonPaiementsAJour() {
		return paiementsAJour == null ? "" : paiementsAJour.toString();
	}

	public String nomAffichagePaiementsAJour() {
		return null;
	}

	public String htmTooltipPaiementsAJour() {
		return null;
	}

	public String htmPaiementsAJour() {
		return paiementsAJour == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementsAJour());
	}

	///////////////////////
	// paiementsEnRetard //
	///////////////////////

	/**	 L'entité paiementsEnRetard
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementsEnRetard;
	@JsonIgnore
	public Couverture<Boolean> paiementsEnRetardCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementsEnRetard").o(paiementsEnRetard);

	/**	<br/> L'entité paiementsEnRetard
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementsEnRetard">Trouver l'entité paiementsEnRetard dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementsEnRetard(Couverture<Boolean> c);

	public Boolean getPaiementsEnRetard() {
		return paiementsEnRetard;
	}

	public void setPaiementsEnRetard(Boolean paiementsEnRetard) {
		this.paiementsEnRetard = paiementsEnRetard;
		this.paiementsEnRetardCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setPaiementsEnRetard(String o) {
		this.paiementsEnRetard = Boolean.parseBoolean(o);
		this.paiementsEnRetardCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire paiementsEnRetardInit() {
		if(!paiementsEnRetardCouverture.dejaInitialise) {
			_paiementsEnRetard(paiementsEnRetardCouverture);
			if(paiementsEnRetard == null)
				setPaiementsEnRetard(paiementsEnRetardCouverture.o);
		}
		paiementsEnRetardCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrPaiementsEnRetard() {
		return paiementsEnRetard;
	}

	public String strPaiementsEnRetard() {
		return paiementsEnRetard == null ? "" : paiementsEnRetard.toString();
	}

	public String jsonPaiementsEnRetard() {
		return paiementsEnRetard == null ? "" : paiementsEnRetard.toString();
	}

	public String nomAffichagePaiementsEnRetard() {
		return null;
	}

	public String htmTooltipPaiementsEnRetard() {
		return null;
	}

	public String htmPaiementsEnRetard() {
		return paiementsEnRetard == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementsEnRetard());
	}

	//////////////////////////////
	// paiementsEnRetardMontant //
	//////////////////////////////

	/**	 L'entité paiementsEnRetardMontant
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal paiementsEnRetardMontant;
	@JsonIgnore
	public Couverture<BigDecimal> paiementsEnRetardMontantCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("paiementsEnRetardMontant").o(paiementsEnRetardMontant);

	/**	<br/> L'entité paiementsEnRetardMontant
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementsEnRetardMontant">Trouver l'entité paiementsEnRetardMontant dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementsEnRetardMontant(Couverture<BigDecimal> c);

	public BigDecimal getPaiementsEnRetardMontant() {
		return paiementsEnRetardMontant;
	}

	public void setPaiementsEnRetardMontant(BigDecimal paiementsEnRetardMontant) {
		this.paiementsEnRetardMontant = paiementsEnRetardMontant;
		this.paiementsEnRetardMontantCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setPaiementsEnRetardMontant(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.paiementsEnRetardMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementsEnRetardMontantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setPaiementsEnRetardMontant(Double o) {
			this.paiementsEnRetardMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementsEnRetardMontantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setPaiementsEnRetardMontant(Integer o) {
			this.paiementsEnRetardMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementsEnRetardMontantCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire paiementsEnRetardMontantInit() {
		if(!paiementsEnRetardMontantCouverture.dejaInitialise) {
			_paiementsEnRetardMontant(paiementsEnRetardMontantCouverture);
			if(paiementsEnRetardMontant == null)
				setPaiementsEnRetardMontant(paiementsEnRetardMontantCouverture.o);
		}
		paiementsEnRetardMontantCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Double solrPaiementsEnRetardMontant() {
		return paiementsEnRetardMontant == null ? null : paiementsEnRetardMontant.doubleValue();
	}

	public String strPaiementsEnRetardMontant() {
		return paiementsEnRetardMontant == null ? "" : paiementsEnRetardMontant.setScale(2).toString();
	}

	public String jsonPaiementsEnRetardMontant() {
		return paiementsEnRetardMontant == null ? "" : paiementsEnRetardMontant.toString();
	}

	public String nomAffichagePaiementsEnRetardMontant() {
		return null;
	}

	public String htmTooltipPaiementsEnRetardMontant() {
		return null;
	}

	public String htmPaiementsEnRetardMontant() {
		return paiementsEnRetardMontant == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementsEnRetardMontant());
	}

	///////////////////////
	// paiementsEnAvance //
	///////////////////////

	/**	 L'entité paiementsEnAvance
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementsEnAvance;
	@JsonIgnore
	public Couverture<Boolean> paiementsEnAvanceCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementsEnAvance").o(paiementsEnAvance);

	/**	<br/> L'entité paiementsEnAvance
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementsEnAvance">Trouver l'entité paiementsEnAvance dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementsEnAvance(Couverture<Boolean> c);

	public Boolean getPaiementsEnAvance() {
		return paiementsEnAvance;
	}

	public void setPaiementsEnAvance(Boolean paiementsEnAvance) {
		this.paiementsEnAvance = paiementsEnAvance;
		this.paiementsEnAvanceCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setPaiementsEnAvance(String o) {
		this.paiementsEnAvance = Boolean.parseBoolean(o);
		this.paiementsEnAvanceCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire paiementsEnAvanceInit() {
		if(!paiementsEnAvanceCouverture.dejaInitialise) {
			_paiementsEnAvance(paiementsEnAvanceCouverture);
			if(paiementsEnAvance == null)
				setPaiementsEnAvance(paiementsEnAvanceCouverture.o);
		}
		paiementsEnAvanceCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Boolean solrPaiementsEnAvance() {
		return paiementsEnAvance;
	}

	public String strPaiementsEnAvance() {
		return paiementsEnAvance == null ? "" : paiementsEnAvance.toString();
	}

	public String jsonPaiementsEnAvance() {
		return paiementsEnAvance == null ? "" : paiementsEnAvance.toString();
	}

	public String nomAffichagePaiementsEnAvance() {
		return null;
	}

	public String htmTooltipPaiementsEnAvance() {
		return null;
	}

	public String htmPaiementsEnAvance() {
		return paiementsEnAvance == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementsEnAvance());
	}

	////////////////
	// creeDAnnee //
	////////////////

	/**	 L'entité creeDAnnee
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer creeDAnnee;
	@JsonIgnore
	public Couverture<Integer> creeDAnneeCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("creeDAnnee").o(creeDAnnee);

	/**	<br/> L'entité creeDAnnee
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:creeDAnnee">Trouver l'entité creeDAnnee dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _creeDAnnee(Couverture<Integer> c);

	public Integer getCreeDAnnee() {
		return creeDAnnee;
	}

	public void setCreeDAnnee(Integer creeDAnnee) {
		this.creeDAnnee = creeDAnnee;
		this.creeDAnneeCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire setCreeDAnnee(String o) {
		if(NumberUtils.isParsable(o))
			this.creeDAnnee = Integer.parseInt(o);
		this.creeDAnneeCouverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire creeDAnneeInit() {
		if(!creeDAnneeCouverture.dejaInitialise) {
			_creeDAnnee(creeDAnneeCouverture);
			if(creeDAnnee == null)
				setCreeDAnnee(creeDAnneeCouverture.o);
		}
		creeDAnneeCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public Integer solrCreeDAnnee() {
		return creeDAnnee;
	}

	public String strCreeDAnnee() {
		return creeDAnnee == null ? "" : creeDAnnee.toString();
	}

	public String jsonCreeDAnnee() {
		return creeDAnnee == null ? "" : creeDAnnee.toString();
	}

	public String nomAffichageCreeDAnnee() {
		return "crée l'année";
	}

	public String htmTooltipCreeDAnnee() {
		return null;
	}

	public String htmCreeDAnnee() {
		return creeDAnnee == null ? "" : StringEscapeUtils.escapeHtml4(strCreeDAnnee());
	}

	///////////////////////
	// creeJourDeSemaine //
	///////////////////////

	/**	 L'entité creeJourDeSemaine
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String creeJourDeSemaine;
	@JsonIgnore
	public Couverture<String> creeJourDeSemaineCouverture = new Couverture<String>().p(this).c(String.class).var("creeJourDeSemaine").o(creeJourDeSemaine);

	/**	<br/> L'entité creeJourDeSemaine
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:creeJourDeSemaine">Trouver l'entité creeJourDeSemaine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _creeJourDeSemaine(Couverture<String> c);

	public String getCreeJourDeSemaine() {
		return creeJourDeSemaine;
	}

	public void setCreeJourDeSemaine(String creeJourDeSemaine) {
		this.creeJourDeSemaine = creeJourDeSemaine;
		this.creeJourDeSemaineCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire creeJourDeSemaineInit() {
		if(!creeJourDeSemaineCouverture.dejaInitialise) {
			_creeJourDeSemaine(creeJourDeSemaineCouverture);
			if(creeJourDeSemaine == null)
				setCreeJourDeSemaine(creeJourDeSemaineCouverture.o);
		}
		creeJourDeSemaineCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrCreeJourDeSemaine() {
		return creeJourDeSemaine;
	}

	public String strCreeJourDeSemaine() {
		return creeJourDeSemaine == null ? "" : creeJourDeSemaine;
	}

	public String jsonCreeJourDeSemaine() {
		return creeJourDeSemaine == null ? "" : creeJourDeSemaine;
	}

	public String nomAffichageCreeJourDeSemaine() {
		return "crée jour de la semaine";
	}

	public String htmTooltipCreeJourDeSemaine() {
		return null;
	}

	public String htmCreeJourDeSemaine() {
		return creeJourDeSemaine == null ? "" : StringEscapeUtils.escapeHtml4(strCreeJourDeSemaine());
	}

	////////////////////
	// creeMoisDAnnee //
	////////////////////

	/**	 L'entité creeMoisDAnnee
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String creeMoisDAnnee;
	@JsonIgnore
	public Couverture<String> creeMoisDAnneeCouverture = new Couverture<String>().p(this).c(String.class).var("creeMoisDAnnee").o(creeMoisDAnnee);

	/**	<br/> L'entité creeMoisDAnnee
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:creeMoisDAnnee">Trouver l'entité creeMoisDAnnee dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _creeMoisDAnnee(Couverture<String> c);

	public String getCreeMoisDAnnee() {
		return creeMoisDAnnee;
	}

	public void setCreeMoisDAnnee(String creeMoisDAnnee) {
		this.creeMoisDAnnee = creeMoisDAnnee;
		this.creeMoisDAnneeCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire creeMoisDAnneeInit() {
		if(!creeMoisDAnneeCouverture.dejaInitialise) {
			_creeMoisDAnnee(creeMoisDAnneeCouverture);
			if(creeMoisDAnnee == null)
				setCreeMoisDAnnee(creeMoisDAnneeCouverture.o);
		}
		creeMoisDAnneeCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrCreeMoisDAnnee() {
		return creeMoisDAnnee;
	}

	public String strCreeMoisDAnnee() {
		return creeMoisDAnnee == null ? "" : creeMoisDAnnee;
	}

	public String jsonCreeMoisDAnnee() {
		return creeMoisDAnnee == null ? "" : creeMoisDAnnee;
	}

	public String nomAffichageCreeMoisDAnnee() {
		return "crée mois de l'année";
	}

	public String htmTooltipCreeMoisDAnnee() {
		return null;
	}

	public String htmCreeMoisDAnnee() {
		return creeMoisDAnnee == null ? "" : StringEscapeUtils.escapeHtml4(strCreeMoisDAnnee());
	}

	/////////////////////
	// creeHeureDuJour //
	/////////////////////

	/**	 L'entité creeHeureDuJour
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String creeHeureDuJour;
	@JsonIgnore
	public Couverture<String> creeHeureDuJourCouverture = new Couverture<String>().p(this).c(String.class).var("creeHeureDuJour").o(creeHeureDuJour);

	/**	<br/> L'entité creeHeureDuJour
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:creeHeureDuJour">Trouver l'entité creeHeureDuJour dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _creeHeureDuJour(Couverture<String> c);

	public String getCreeHeureDuJour() {
		return creeHeureDuJour;
	}

	public void setCreeHeureDuJour(String creeHeureDuJour) {
		this.creeHeureDuJour = creeHeureDuJour;
		this.creeHeureDuJourCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire creeHeureDuJourInit() {
		if(!creeHeureDuJourCouverture.dejaInitialise) {
			_creeHeureDuJour(creeHeureDuJourCouverture);
			if(creeHeureDuJour == null)
				setCreeHeureDuJour(creeHeureDuJourCouverture.o);
		}
		creeHeureDuJourCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrCreeHeureDuJour() {
		return creeHeureDuJour;
	}

	public String strCreeHeureDuJour() {
		return creeHeureDuJour == null ? "" : creeHeureDuJour;
	}

	public String jsonCreeHeureDuJour() {
		return creeHeureDuJour == null ? "" : creeHeureDuJour;
	}

	public String nomAffichageCreeHeureDuJour() {
		return "heure du jour";
	}

	public String htmTooltipCreeHeureDuJour() {
		return null;
	}

	public String htmCreeHeureDuJour() {
		return creeHeureDuJour == null ? "" : StringEscapeUtils.escapeHtml4(strCreeHeureDuJour());
	}

	///////////////////////////////
	// inscriptionJoursDeSemaine //
	///////////////////////////////

	/**	 L'entité inscriptionJoursDeSemaine
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> inscriptionJoursDeSemaine = new ArrayList<String>();
	@JsonIgnore
	public Couverture<List<String>> inscriptionJoursDeSemaineCouverture = new Couverture<List<String>>().p(this).c(List.class).var("inscriptionJoursDeSemaine").o(inscriptionJoursDeSemaine);

	/**	<br/> L'entité inscriptionJoursDeSemaine
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionJoursDeSemaine">Trouver l'entité inscriptionJoursDeSemaine dans Solr</a>
	 * <br/>
	 * @param inscriptionJoursDeSemaine est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionJoursDeSemaine(List<String> l);

	public List<String> getInscriptionJoursDeSemaine() {
		return inscriptionJoursDeSemaine;
	}

	public void setInscriptionJoursDeSemaine(List<String> inscriptionJoursDeSemaine) {
		this.inscriptionJoursDeSemaine = inscriptionJoursDeSemaine;
		this.inscriptionJoursDeSemaineCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addInscriptionJoursDeSemaine(String...objets) {
		for(String o : objets) {
			addInscriptionJoursDeSemaine(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addInscriptionJoursDeSemaine(String o) {
		if(o != null && !inscriptionJoursDeSemaine.contains(o))
			this.inscriptionJoursDeSemaine.add(o);
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionJoursDeSemaine(JsonArray objets) {
		inscriptionJoursDeSemaine.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addInscriptionJoursDeSemaine(o);
		}
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionJoursDeSemaineInit() {
		if(!inscriptionJoursDeSemaineCouverture.dejaInitialise) {
			_inscriptionJoursDeSemaine(inscriptionJoursDeSemaine);
		}
		inscriptionJoursDeSemaineCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public List<String> solrInscriptionJoursDeSemaine() {
		return inscriptionJoursDeSemaine;
	}

	public String strInscriptionJoursDeSemaine() {
		return inscriptionJoursDeSemaine == null ? "" : inscriptionJoursDeSemaine.toString();
	}

	public String jsonInscriptionJoursDeSemaine() {
		return inscriptionJoursDeSemaine == null ? "" : inscriptionJoursDeSemaine.toString();
	}

	public String nomAffichageInscriptionJoursDeSemaine() {
		return "jours de la semaine";
	}

	public String htmTooltipInscriptionJoursDeSemaine() {
		return null;
	}

	public String htmInscriptionJoursDeSemaine() {
		return inscriptionJoursDeSemaine == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionJoursDeSemaine());
	}

	////////////////////////////
	// inscriptionNomsParents //
	////////////////////////////

	/**	 L'entité inscriptionNomsParents
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionNomsParents;
	@JsonIgnore
	public Couverture<String> inscriptionNomsParentsCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionNomsParents").o(inscriptionNomsParents);

	/**	<br/> L'entité inscriptionNomsParents
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNomsParents">Trouver l'entité inscriptionNomsParents dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_inscriptionNomsParents");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setInscriptionNomsParents classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionNomsParents w3-input w3-border ");
					a("name", "setInscriptionNomsParents");
				} else {
					a("class", "valeurInscriptionNomsParents w3-input w3-border classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionNomsParents w3-input w3-border ");
					a("name", "inscriptionNomsParents");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionNomsParents', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionNomsParents')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionNomsParents')); }); ");
				}
				a("value", strInscriptionNomsParents())
			.fg();

		} else {
			sx(htmInscriptionNomsParents());
		}
	}

	public void htmInscriptionNomsParents(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionNomsParents").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionNomsParents(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionNomsParents')); $('#", classeApiMethodeMethode, "_inscriptionNomsParents').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionNomsParents', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionNomsParents')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionNomsParents')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// inscriptionMails //
	//////////////////////

	/**	 L'entité inscriptionMails
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> inscriptionMails = new ArrayList<String>();
	@JsonIgnore
	public Couverture<List<String>> inscriptionMailsCouverture = new Couverture<List<String>>().p(this).c(List.class).var("inscriptionMails").o(inscriptionMails);

	/**	<br/> L'entité inscriptionMails
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionMails">Trouver l'entité inscriptionMails dans Solr</a>
	 * <br/>
	 * @param inscriptionMails est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionMails(List<String> l);

	public List<String> getInscriptionMails() {
		return inscriptionMails;
	}

	public void setInscriptionMails(List<String> inscriptionMails) {
		this.inscriptionMails = inscriptionMails;
		this.inscriptionMailsCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addInscriptionMails(String...objets) {
		for(String o : objets) {
			addInscriptionMails(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addInscriptionMails(String o) {
		if(o != null && !inscriptionMails.contains(o))
			this.inscriptionMails.add(o);
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionMails(JsonArray objets) {
		inscriptionMails.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addInscriptionMails(o);
		}
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionMailsInit() {
		if(!inscriptionMailsCouverture.dejaInitialise) {
			_inscriptionMails(inscriptionMails);
		}
		inscriptionMailsCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public List<String> solrInscriptionMails() {
		return inscriptionMails;
	}

	public String strInscriptionMails() {
		return inscriptionMails == null ? "" : inscriptionMails.toString();
	}

	public String jsonInscriptionMails() {
		return inscriptionMails == null ? "" : inscriptionMails.toString();
	}

	public String nomAffichageInscriptionMails() {
		return null;
	}

	public String htmTooltipInscriptionMails() {
		return null;
	}

	public String htmInscriptionMails() {
		return inscriptionMails == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionMails());
	}

	/////////////////////
	// inscriptionMail //
	/////////////////////

	/**	 L'entité inscriptionMail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionMail;
	@JsonIgnore
	public Couverture<String> inscriptionMailCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionMail").o(inscriptionMail);

	/**	<br/> L'entité inscriptionMail
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionMail">Trouver l'entité inscriptionMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionMail(Couverture<String> c);

	public String getInscriptionMail() {
		return inscriptionMail;
	}

	public void setInscriptionMail(String inscriptionMail) {
		this.inscriptionMail = inscriptionMail;
		this.inscriptionMailCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionMailInit() {
		if(!inscriptionMailCouverture.dejaInitialise) {
			_inscriptionMail(inscriptionMailCouverture);
			if(inscriptionMail == null)
				setInscriptionMail(inscriptionMailCouverture.o);
		}
		inscriptionMailCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionMail() {
		return inscriptionMail;
	}

	public String strInscriptionMail() {
		return inscriptionMail == null ? "" : inscriptionMail;
	}

	public String jsonInscriptionMail() {
		return inscriptionMail == null ? "" : inscriptionMail;
	}

	public String nomAffichageInscriptionMail() {
		return null;
	}

	public String htmTooltipInscriptionMail() {
		return null;
	}

	public String htmInscriptionMail() {
		return inscriptionMail == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionMail());
	}

	/////////////////////////////
	// inscriptionMailsParents //
	/////////////////////////////

	/**	 L'entité inscriptionMailsParents
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionMailsParents;
	@JsonIgnore
	public Couverture<String> inscriptionMailsParentsCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionMailsParents").o(inscriptionMailsParents);

	/**	<br/> L'entité inscriptionMailsParents
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionMailsParents">Trouver l'entité inscriptionMailsParents dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionMailsParents(Couverture<String> c);

	public String getInscriptionMailsParents() {
		return inscriptionMailsParents;
	}

	public void setInscriptionMailsParents(String inscriptionMailsParents) {
		this.inscriptionMailsParents = inscriptionMailsParents;
		this.inscriptionMailsParentsCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionMailsParentsInit() {
		if(!inscriptionMailsParentsCouverture.dejaInitialise) {
			_inscriptionMailsParents(inscriptionMailsParentsCouverture);
			if(inscriptionMailsParents == null)
				setInscriptionMailsParents(inscriptionMailsParentsCouverture.o);
		}
		inscriptionMailsParentsCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionMailsParents() {
		return inscriptionMailsParents;
	}

	public String strInscriptionMailsParents() {
		return inscriptionMailsParents == null ? "" : inscriptionMailsParents;
	}

	public String jsonInscriptionMailsParents() {
		return inscriptionMailsParents == null ? "" : inscriptionMailsParents;
	}

	public String nomAffichageInscriptionMailsParents() {
		return null;
	}

	public String htmTooltipInscriptionMailsParents() {
		return null;
	}

	public String htmInscriptionMailsParents() {
		return inscriptionMailsParents == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionMailsParents());
	}

	/////////////////////////////////
	// inscriptionNumeroTelephones //
	/////////////////////////////////

	/**	 L'entité inscriptionNumeroTelephones
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> inscriptionNumeroTelephones = new ArrayList<String>();
	@JsonIgnore
	public Couverture<List<String>> inscriptionNumeroTelephonesCouverture = new Couverture<List<String>>().p(this).c(List.class).var("inscriptionNumeroTelephones").o(inscriptionNumeroTelephones);

	/**	<br/> L'entité inscriptionNumeroTelephones
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNumeroTelephones">Trouver l'entité inscriptionNumeroTelephones dans Solr</a>
	 * <br/>
	 * @param inscriptionNumeroTelephones est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionNumeroTelephones(List<String> l);

	public List<String> getInscriptionNumeroTelephones() {
		return inscriptionNumeroTelephones;
	}

	public void setInscriptionNumeroTelephones(List<String> inscriptionNumeroTelephones) {
		this.inscriptionNumeroTelephones = inscriptionNumeroTelephones;
		this.inscriptionNumeroTelephonesCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addInscriptionNumeroTelephones(String...objets) {
		for(String o : objets) {
			addInscriptionNumeroTelephones(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addInscriptionNumeroTelephones(String o) {
		if(o != null && !inscriptionNumeroTelephones.contains(o))
			this.inscriptionNumeroTelephones.add(o);
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionNumeroTelephones(JsonArray objets) {
		inscriptionNumeroTelephones.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addInscriptionNumeroTelephones(o);
		}
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionNumeroTelephonesInit() {
		if(!inscriptionNumeroTelephonesCouverture.dejaInitialise) {
			_inscriptionNumeroTelephones(inscriptionNumeroTelephones);
		}
		inscriptionNumeroTelephonesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public List<String> solrInscriptionNumeroTelephones() {
		return inscriptionNumeroTelephones;
	}

	public String strInscriptionNumeroTelephones() {
		return inscriptionNumeroTelephones == null ? "" : inscriptionNumeroTelephones.toString();
	}

	public String jsonInscriptionNumeroTelephones() {
		return inscriptionNumeroTelephones == null ? "" : inscriptionNumeroTelephones.toString();
	}

	public String nomAffichageInscriptionNumeroTelephones() {
		return null;
	}

	public String htmTooltipInscriptionNumeroTelephones() {
		return null;
	}

	public String htmInscriptionNumeroTelephones() {
		return inscriptionNumeroTelephones == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionNumeroTelephones());
	}

	////////////////////////////////
	// inscriptionNumeroTelephone //
	////////////////////////////////

	/**	 L'entité inscriptionNumeroTelephone
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionNumeroTelephone;
	@JsonIgnore
	public Couverture<String> inscriptionNumeroTelephoneCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionNumeroTelephone").o(inscriptionNumeroTelephone);

	/**	<br/> L'entité inscriptionNumeroTelephone
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNumeroTelephone">Trouver l'entité inscriptionNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionNumeroTelephone(Couverture<String> c);

	public String getInscriptionNumeroTelephone() {
		return inscriptionNumeroTelephone;
	}

	public void setInscriptionNumeroTelephone(String inscriptionNumeroTelephone) {
		this.inscriptionNumeroTelephone = inscriptionNumeroTelephone;
		this.inscriptionNumeroTelephoneCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionNumeroTelephoneInit() {
		if(!inscriptionNumeroTelephoneCouverture.dejaInitialise) {
			_inscriptionNumeroTelephone(inscriptionNumeroTelephoneCouverture);
			if(inscriptionNumeroTelephone == null)
				setInscriptionNumeroTelephone(inscriptionNumeroTelephoneCouverture.o);
		}
		inscriptionNumeroTelephoneCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionNumeroTelephone() {
		return inscriptionNumeroTelephone;
	}

	public String strInscriptionNumeroTelephone() {
		return inscriptionNumeroTelephone == null ? "" : inscriptionNumeroTelephone;
	}

	public String jsonInscriptionNumeroTelephone() {
		return inscriptionNumeroTelephone == null ? "" : inscriptionNumeroTelephone;
	}

	public String nomAffichageInscriptionNumeroTelephone() {
		return null;
	}

	public String htmTooltipInscriptionNumeroTelephone() {
		return null;
	}

	public String htmInscriptionNumeroTelephone() {
		return inscriptionNumeroTelephone == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionNumeroTelephone());
	}

	//////////////////////////
	// inscriptionNomParent //
	//////////////////////////

	/**	 L'entité inscriptionNomParent
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionNomParent;
	@JsonIgnore
	public Couverture<String> inscriptionNomParentCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionNomParent").o(inscriptionNomParent);

	/**	<br/> L'entité inscriptionNomParent
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNomParent">Trouver l'entité inscriptionNomParent dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionNomParent(Couverture<String> c);

	public String getInscriptionNomParent() {
		return inscriptionNomParent;
	}

	public void setInscriptionNomParent(String inscriptionNomParent) {
		this.inscriptionNomParent = inscriptionNomParent;
		this.inscriptionNomParentCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire inscriptionNomParentInit() {
		if(!inscriptionNomParentCouverture.dejaInitialise) {
			_inscriptionNomParent(inscriptionNomParentCouverture);
			if(inscriptionNomParent == null)
				setInscriptionNomParent(inscriptionNomParentCouverture.o);
		}
		inscriptionNomParentCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public String solrInscriptionNomParent() {
		return inscriptionNomParent;
	}

	public String strInscriptionNomParent() {
		return inscriptionNomParent == null ? "" : inscriptionNomParent;
	}

	public String jsonInscriptionNomParent() {
		return inscriptionNomParent == null ? "" : inscriptionNomParent;
	}

	public String nomAffichageInscriptionNomParent() {
		return null;
	}

	public String htmTooltipInscriptionNomParent() {
		return null;
	}

	public String htmInscriptionNomParent() {
		return inscriptionNomParent == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionNomParent());
	}

	////////////////////////////////
	// inscriptionNomParentLignes //
	////////////////////////////////

	/**	 L'entité inscriptionNomParentLignes
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionNomParentLignes;
	@JsonIgnore
	public Couverture<String> inscriptionNomParentLignesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionNomParentLignes").o(inscriptionNomParentLignes);

	/**	<br/> L'entité inscriptionNomParentLignes
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNomParentLignes">Trouver l'entité inscriptionNomParentLignes dans Solr</a>
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

	/**	 L'entité inscriptionMailParentLignes
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionMailParentLignes;
	@JsonIgnore
	public Couverture<String> inscriptionMailParentLignesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionMailParentLignes").o(inscriptionMailParentLignes);

	/**	<br/> L'entité inscriptionMailParentLignes
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionMailParentLignes">Trouver l'entité inscriptionMailParentLignes dans Solr</a>
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

	/**	 L'entité inscriptionDetailParentLignes
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionDetailParentLignes;
	@JsonIgnore
	public Couverture<String> inscriptionDetailParentLignesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionDetailParentLignes").o(inscriptionDetailParentLignes);

	/**	<br/> L'entité inscriptionDetailParentLignes
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDetailParentLignes">Trouver l'entité inscriptionDetailParentLignes dans Solr</a>
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

	/**	 L'entité inscriptionChercherParentLignes
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionChercherParentLignes;
	@JsonIgnore
	public Couverture<String> inscriptionChercherParentLignesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionChercherParentLignes").o(inscriptionChercherParentLignes);

	/**	<br/> L'entité inscriptionChercherParentLignes
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionChercherParentLignes">Trouver l'entité inscriptionChercherParentLignes dans Solr</a>
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

	/**	 L'entité inscriptionContactUrgenceParentLignes
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionContactUrgenceParentLignes;
	@JsonIgnore
	public Couverture<String> inscriptionContactUrgenceParentLignesCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionContactUrgenceParentLignes").o(inscriptionContactUrgenceParentLignes);

	/**	<br/> L'entité inscriptionContactUrgenceParentLignes
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionContactUrgenceParentLignes">Trouver l'entité inscriptionContactUrgenceParentLignes dans Solr</a>
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

	/**	 L'entité inscriptionSignature1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionSignature1;
	@JsonIgnore
	public Couverture<String> inscriptionSignature1Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature1").o(inscriptionSignature1);

	/**	<br/> L'entité inscriptionSignature1
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature1">Trouver l'entité inscriptionSignature1 dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "signatureDiv1InscriptionScolaire_inscriptionSignature1 signatureInputInscriptionScolaire", pk, "InscriptionSignature1").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature1").f();
				e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature1");
					a("style", "display: ", StringUtils.isBlank(inscriptionSignature1) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature1");
					a("class", "signatureImgInscriptionScolaire", pk, "InscriptionSignature1 ");
					a("src", StringUtils.isBlank(inscriptionSignature1) ? "data:image/png;base64," : inscriptionSignature1).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature1) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature1").f();
				e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature1");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature1').show(); ");
						s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature1').hide(); ");
						s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature1')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature1', null); ");
						s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature1')) { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature1').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature1').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Vider");
				g("button");
			g("div");
		} else {
			sx(htmInscriptionSignature1());
		}
	}

	public void htmInscriptionSignature1(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature1").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature1(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature1')); $('#", classeApiMethodeMethode, "_inscriptionSignature1').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature1', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature1')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature1')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionSignature2
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionSignature2;
	@JsonIgnore
	public Couverture<String> inscriptionSignature2Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature2").o(inscriptionSignature2);

	/**	<br/> L'entité inscriptionSignature2
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature2">Trouver l'entité inscriptionSignature2 dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "signatureDiv1InscriptionScolaire_inscriptionSignature2 signatureInputInscriptionScolaire", pk, "InscriptionSignature2").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature2").f();
				e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature2");
					a("style", "display: ", StringUtils.isBlank(inscriptionSignature2) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature2");
					a("class", "signatureImgInscriptionScolaire", pk, "InscriptionSignature2 ");
					a("src", StringUtils.isBlank(inscriptionSignature2) ? "data:image/png;base64," : inscriptionSignature2).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature2) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature2").f();
				e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature2");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature2').show(); ");
						s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature2').hide(); ");
						s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature2')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature2', null); ");
						s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature2')) { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature2').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature2').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Vider");
				g("button");
			g("div");
		} else {
			sx(htmInscriptionSignature2());
		}
	}

	public void htmInscriptionSignature2(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature2").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature2(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature2')); $('#", classeApiMethodeMethode, "_inscriptionSignature2').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature2', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature2')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature2')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionSignature3
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionSignature3;
	@JsonIgnore
	public Couverture<String> inscriptionSignature3Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature3").o(inscriptionSignature3);

	/**	<br/> L'entité inscriptionSignature3
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature3">Trouver l'entité inscriptionSignature3 dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "signatureDiv1InscriptionScolaire_inscriptionSignature3 signatureInputInscriptionScolaire", pk, "InscriptionSignature3").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature3").f();
				e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature3");
					a("style", "display: ", StringUtils.isBlank(inscriptionSignature3) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature3");
					a("class", "signatureImgInscriptionScolaire", pk, "InscriptionSignature3 ");
					a("src", StringUtils.isBlank(inscriptionSignature3) ? "data:image/png;base64," : inscriptionSignature3).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature3) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature3").f();
				e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature3");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature3').show(); ");
						s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature3').hide(); ");
						s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature3')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature3', null); ");
						s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature3')) { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature3').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature3').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Vider");
				g("button");
			g("div");
		} else {
			sx(htmInscriptionSignature3());
		}
	}

	public void htmInscriptionSignature3(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature3").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature3(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature3')); $('#", classeApiMethodeMethode, "_inscriptionSignature3').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature3', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature3')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature3')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionSignature4
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionSignature4;
	@JsonIgnore
	public Couverture<String> inscriptionSignature4Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature4").o(inscriptionSignature4);

	/**	<br/> L'entité inscriptionSignature4
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature4">Trouver l'entité inscriptionSignature4 dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "signatureDiv1InscriptionScolaire_inscriptionSignature4 signatureInputInscriptionScolaire", pk, "InscriptionSignature4").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature4").f();
				e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature4");
					a("style", "display: ", StringUtils.isBlank(inscriptionSignature4) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature4");
					a("class", "signatureImgInscriptionScolaire", pk, "InscriptionSignature4 ");
					a("src", StringUtils.isBlank(inscriptionSignature4) ? "data:image/png;base64," : inscriptionSignature4).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature4) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature4").f();
				e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature4");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature4').show(); ");
						s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature4').hide(); ");
						s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature4')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature4', null); ");
						s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature4')) { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature4').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature4').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Vider");
				g("button");
			g("div");
		} else {
			sx(htmInscriptionSignature4());
		}
	}

	public void htmInscriptionSignature4(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature4").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature4(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature4')); $('#", classeApiMethodeMethode, "_inscriptionSignature4').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature4', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature4')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature4')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionSignature5
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionSignature5;
	@JsonIgnore
	public Couverture<String> inscriptionSignature5Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature5").o(inscriptionSignature5);

	/**	<br/> L'entité inscriptionSignature5
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature5">Trouver l'entité inscriptionSignature5 dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "signatureDiv1InscriptionScolaire_inscriptionSignature5 signatureInputInscriptionScolaire", pk, "InscriptionSignature5").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature5").f();
				e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature5");
					a("style", "display: ", StringUtils.isBlank(inscriptionSignature5) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature5");
					a("class", "signatureImgInscriptionScolaire", pk, "InscriptionSignature5 ");
					a("src", StringUtils.isBlank(inscriptionSignature5) ? "data:image/png;base64," : inscriptionSignature5).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature5) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature5").f();
				e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature5");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature5').show(); ");
						s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature5').hide(); ");
						s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature5')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature5', null); ");
						s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature5')) { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature5').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature5').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Vider");
				g("button");
			g("div");
		} else {
			sx(htmInscriptionSignature5());
		}
	}

	public void htmInscriptionSignature5(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature5").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature5(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature5')); $('#", classeApiMethodeMethode, "_inscriptionSignature5').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature5', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature5')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature5')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionSignature6
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionSignature6;
	@JsonIgnore
	public Couverture<String> inscriptionSignature6Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature6").o(inscriptionSignature6);

	/**	<br/> L'entité inscriptionSignature6
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature6">Trouver l'entité inscriptionSignature6 dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "signatureDiv1InscriptionScolaire_inscriptionSignature6 signatureInputInscriptionScolaire", pk, "InscriptionSignature6").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature6").f();
				e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature6");
					a("style", "display: ", StringUtils.isBlank(inscriptionSignature6) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature6");
					a("class", "signatureImgInscriptionScolaire", pk, "InscriptionSignature6 ");
					a("src", StringUtils.isBlank(inscriptionSignature6) ? "data:image/png;base64," : inscriptionSignature6).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature6) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature6").f();
				e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature6");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature6').show(); ");
						s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature6').hide(); ");
						s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature6')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature6', null); ");
						s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature6')) { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature6').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature6').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Vider");
				g("button");
			g("div");
		} else {
			sx(htmInscriptionSignature6());
		}
	}

	public void htmInscriptionSignature6(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature6").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature6(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature6')); $('#", classeApiMethodeMethode, "_inscriptionSignature6').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature6', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature6')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature6')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionSignature7
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionSignature7;
	@JsonIgnore
	public Couverture<String> inscriptionSignature7Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature7").o(inscriptionSignature7);

	/**	<br/> L'entité inscriptionSignature7
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature7">Trouver l'entité inscriptionSignature7 dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "signatureDiv1InscriptionScolaire_inscriptionSignature7 signatureInputInscriptionScolaire", pk, "InscriptionSignature7").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature7").f();
				e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature7");
					a("style", "display: ", StringUtils.isBlank(inscriptionSignature7) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature7");
					a("class", "signatureImgInscriptionScolaire", pk, "InscriptionSignature7 ");
					a("src", StringUtils.isBlank(inscriptionSignature7) ? "data:image/png;base64," : inscriptionSignature7).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature7) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature7").f();
				e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature7");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature7').show(); ");
						s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature7').hide(); ");
						s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature7')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature7', null); ");
						s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature7')) { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature7').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature7').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Vider");
				g("button");
			g("div");
		} else {
			sx(htmInscriptionSignature7());
		}
	}

	public void htmInscriptionSignature7(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature7").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature7(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature7')); $('#", classeApiMethodeMethode, "_inscriptionSignature7').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature7', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature7')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature7')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionSignature8
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionSignature8;
	@JsonIgnore
	public Couverture<String> inscriptionSignature8Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature8").o(inscriptionSignature8);

	/**	<br/> L'entité inscriptionSignature8
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature8">Trouver l'entité inscriptionSignature8 dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "signatureDiv1InscriptionScolaire_inscriptionSignature8 signatureInputInscriptionScolaire", pk, "InscriptionSignature8").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature8").f();
				e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature8");
					a("style", "display: ", StringUtils.isBlank(inscriptionSignature8) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature8");
					a("class", "signatureImgInscriptionScolaire", pk, "InscriptionSignature8 ");
					a("src", StringUtils.isBlank(inscriptionSignature8) ? "data:image/png;base64," : inscriptionSignature8).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature8) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature8").f();
				e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature8");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature8').show(); ");
						s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature8').hide(); ");
						s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature8')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature8', null); ");
						s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature8')) { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature8').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature8').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Vider");
				g("button");
			g("div");
		} else {
			sx(htmInscriptionSignature8());
		}
	}

	public void htmInscriptionSignature8(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature8").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature8(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature8')); $('#", classeApiMethodeMethode, "_inscriptionSignature8').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature8', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature8')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature8')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionSignature9
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionSignature9;
	@JsonIgnore
	public Couverture<String> inscriptionSignature9Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature9").o(inscriptionSignature9);

	/**	<br/> L'entité inscriptionSignature9
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature9">Trouver l'entité inscriptionSignature9 dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "signatureDiv1InscriptionScolaire_inscriptionSignature9 signatureInputInscriptionScolaire", pk, "InscriptionSignature9").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature9").f();
				e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature9");
					a("style", "display: ", StringUtils.isBlank(inscriptionSignature9) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature9");
					a("class", "signatureImgInscriptionScolaire", pk, "InscriptionSignature9 ");
					a("src", StringUtils.isBlank(inscriptionSignature9) ? "data:image/png;base64," : inscriptionSignature9).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature9) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature9").f();
				e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature9");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature9').show(); ");
						s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature9').hide(); ");
						s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature9')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature9', null); ");
						s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature9')) { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature9').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature9').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Vider");
				g("button");
			g("div");
		} else {
			sx(htmInscriptionSignature9());
		}
	}

	public void htmInscriptionSignature9(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature9").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature9(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature9')); $('#", classeApiMethodeMethode, "_inscriptionSignature9').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature9', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature9')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature9')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionSignature10
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionSignature10;
	@JsonIgnore
	public Couverture<String> inscriptionSignature10Couverture = new Couverture<String>().p(this).c(String.class).var("inscriptionSignature10").o(inscriptionSignature10);

	/**	<br/> L'entité inscriptionSignature10
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionSignature10">Trouver l'entité inscriptionSignature10 dans Solr</a>
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "signatureDiv1InscriptionScolaire_inscriptionSignature10 signatureInputInscriptionScolaire", pk, "InscriptionSignature10").a("id", "signatureDiv1InscriptionScolaire", pk, "inscriptionSignature10").f();
				e("div").a("id", "signatureInputInscriptionScolaire", pk, "inscriptionSignature10");
					a("style", "display: ", StringUtils.isBlank(inscriptionSignature10) ? "block" : "none", "; ");
				f().g("div");
				e("img").a("id", "signatureImgInscriptionScolaire", pk, "inscriptionSignature10");
					a("class", "signatureImgInscriptionScolaire", pk, "InscriptionSignature10 ");
					a("src", StringUtils.isBlank(inscriptionSignature10) ? "data:image/png;base64," : inscriptionSignature10).a("alt", "");
					a("style", "padding: 10px; display: ", StringUtils.isBlank(inscriptionSignature10) ? "none" : "block", "; ");
				fg();
			g("div");
			e("div").a("id", "signatureDiv2InscriptionScolaire", pk, "inscriptionSignature10").f();
				e("button").a("id", "signatureButtonViderInscriptionScolaire", pk, "inscriptionSignature10");
					a("class", "w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin ");
					s(" onclick=", "\"");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature10').show(); ");
						s("$('#signatureImgInscriptionScolaire", pk, "inscriptionSignature10').hide(); ");
						s("enleverLueur($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature10')); ");
						s("patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionSignature10', null); ");
						s("if($('#signatureInputInscriptionScolaire", pk, "inscriptionSignature10')) { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature10').jSignature('reset'); ");
						s(" } else { ");
						s("$('#signatureInputInscriptionScolaire", pk, "inscriptionSignature10').jSignature({'height':200}); ");
						s(" } ");
					s("\"");
					f().sx("Vider");
				g("button");
			g("div");
		} else {
			sx(htmInscriptionSignature10());
		}
	}

	public void htmInscriptionSignature10(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionSignature10").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputInscriptionSignature10(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionSignature10')); $('#", classeApiMethodeMethode, "_inscriptionSignature10').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionSignature10', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionSignature10')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionSignature10')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionDate1
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate inscriptionDate1;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate1Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate1").o(inscriptionDate1);

	/**	<br/> L'entité inscriptionDate1
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate1">Trouver l'entité inscriptionDate1 dans Solr</a>
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
		this.inscriptionDate1 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate1Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate1(String o) {
		this.inscriptionDate1 = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.inscriptionDate1Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate1(Date o) {
		this.inscriptionDate1 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
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
		return inscriptionDate1 == null ? null : Date.from(inscriptionDate1.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strInscriptionDate1() {
		return inscriptionDate1 == null ? "" : inscriptionDate1.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonInscriptionDate1() {
		return inscriptionDate1 == null ? "" : inscriptionDate1.format(DateTimeFormatter.ISO_DATE);
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setInscriptionDate1 classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionDate1 w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_inscriptionDate1")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", inscriptionDate1 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate1))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate1', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate1')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate1')); }); } ")
				.fg();
		} else {
			sx(htmInscriptionDate1());
		}
	}

	public void htmInscriptionDate1(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate1").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate1(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate1')); $('#", classeApiMethodeMethode, "_inscriptionDate1').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate1', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate1')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate1')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionDate2
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate inscriptionDate2;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate2Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate2").o(inscriptionDate2);

	/**	<br/> L'entité inscriptionDate2
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate2">Trouver l'entité inscriptionDate2 dans Solr</a>
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
		this.inscriptionDate2 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate2Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate2(String o) {
		this.inscriptionDate2 = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.inscriptionDate2Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate2(Date o) {
		this.inscriptionDate2 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
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
		return inscriptionDate2 == null ? null : Date.from(inscriptionDate2.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strInscriptionDate2() {
		return inscriptionDate2 == null ? "" : inscriptionDate2.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonInscriptionDate2() {
		return inscriptionDate2 == null ? "" : inscriptionDate2.format(DateTimeFormatter.ISO_DATE);
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setInscriptionDate2 classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionDate2 w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_inscriptionDate2")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", inscriptionDate2 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate2))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate2', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate2')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate2')); }); } ")
				.fg();
		} else {
			sx(htmInscriptionDate2());
		}
	}

	public void htmInscriptionDate2(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate2").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate2(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate2')); $('#", classeApiMethodeMethode, "_inscriptionDate2').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate2', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate2')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate2')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionDate3
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate inscriptionDate3;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate3Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate3").o(inscriptionDate3);

	/**	<br/> L'entité inscriptionDate3
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate3">Trouver l'entité inscriptionDate3 dans Solr</a>
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
		this.inscriptionDate3 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate3Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate3(String o) {
		this.inscriptionDate3 = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.inscriptionDate3Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate3(Date o) {
		this.inscriptionDate3 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
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
		return inscriptionDate3 == null ? null : Date.from(inscriptionDate3.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strInscriptionDate3() {
		return inscriptionDate3 == null ? "" : inscriptionDate3.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonInscriptionDate3() {
		return inscriptionDate3 == null ? "" : inscriptionDate3.format(DateTimeFormatter.ISO_DATE);
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setInscriptionDate3 classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionDate3 w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_inscriptionDate3")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", inscriptionDate3 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate3))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate3', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate3')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate3')); }); } ")
				.fg();
		} else {
			sx(htmInscriptionDate3());
		}
	}

	public void htmInscriptionDate3(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate3").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate3(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate3')); $('#", classeApiMethodeMethode, "_inscriptionDate3').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate3', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate3')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate3')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionDate4
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate inscriptionDate4;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate4Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate4").o(inscriptionDate4);

	/**	<br/> L'entité inscriptionDate4
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate4">Trouver l'entité inscriptionDate4 dans Solr</a>
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
		this.inscriptionDate4 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate4Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate4(String o) {
		this.inscriptionDate4 = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.inscriptionDate4Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate4(Date o) {
		this.inscriptionDate4 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
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
		return inscriptionDate4 == null ? null : Date.from(inscriptionDate4.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strInscriptionDate4() {
		return inscriptionDate4 == null ? "" : inscriptionDate4.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonInscriptionDate4() {
		return inscriptionDate4 == null ? "" : inscriptionDate4.format(DateTimeFormatter.ISO_DATE);
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setInscriptionDate4 classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionDate4 w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_inscriptionDate4")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", inscriptionDate4 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate4))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate4', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate4')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate4')); }); } ")
				.fg();
		} else {
			sx(htmInscriptionDate4());
		}
	}

	public void htmInscriptionDate4(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate4").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate4(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate4')); $('#", classeApiMethodeMethode, "_inscriptionDate4').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate4', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate4')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate4')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionDate5
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate inscriptionDate5;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate5Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate5").o(inscriptionDate5);

	/**	<br/> L'entité inscriptionDate5
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate5">Trouver l'entité inscriptionDate5 dans Solr</a>
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
		this.inscriptionDate5 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate5Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate5(String o) {
		this.inscriptionDate5 = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.inscriptionDate5Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate5(Date o) {
		this.inscriptionDate5 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
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
		return inscriptionDate5 == null ? null : Date.from(inscriptionDate5.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strInscriptionDate5() {
		return inscriptionDate5 == null ? "" : inscriptionDate5.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonInscriptionDate5() {
		return inscriptionDate5 == null ? "" : inscriptionDate5.format(DateTimeFormatter.ISO_DATE);
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setInscriptionDate5 classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionDate5 w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_inscriptionDate5")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", inscriptionDate5 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate5))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate5', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate5')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate5')); }); } ")
				.fg();
		} else {
			sx(htmInscriptionDate5());
		}
	}

	public void htmInscriptionDate5(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate5").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate5(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate5')); $('#", classeApiMethodeMethode, "_inscriptionDate5').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate5', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate5')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate5')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionDate6
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate inscriptionDate6;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate6Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate6").o(inscriptionDate6);

	/**	<br/> L'entité inscriptionDate6
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate6">Trouver l'entité inscriptionDate6 dans Solr</a>
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
		this.inscriptionDate6 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate6Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate6(String o) {
		this.inscriptionDate6 = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.inscriptionDate6Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate6(Date o) {
		this.inscriptionDate6 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
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
		return inscriptionDate6 == null ? null : Date.from(inscriptionDate6.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strInscriptionDate6() {
		return inscriptionDate6 == null ? "" : inscriptionDate6.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonInscriptionDate6() {
		return inscriptionDate6 == null ? "" : inscriptionDate6.format(DateTimeFormatter.ISO_DATE);
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setInscriptionDate6 classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionDate6 w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_inscriptionDate6")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", inscriptionDate6 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate6))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate6', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate6')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate6')); }); } ")
				.fg();
		} else {
			sx(htmInscriptionDate6());
		}
	}

	public void htmInscriptionDate6(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate6").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate6(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate6')); $('#", classeApiMethodeMethode, "_inscriptionDate6').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate6', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate6')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate6')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionDate7
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate inscriptionDate7;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate7Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate7").o(inscriptionDate7);

	/**	<br/> L'entité inscriptionDate7
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate7">Trouver l'entité inscriptionDate7 dans Solr</a>
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
		this.inscriptionDate7 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate7Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate7(String o) {
		this.inscriptionDate7 = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.inscriptionDate7Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate7(Date o) {
		this.inscriptionDate7 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
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
		return inscriptionDate7 == null ? null : Date.from(inscriptionDate7.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strInscriptionDate7() {
		return inscriptionDate7 == null ? "" : inscriptionDate7.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonInscriptionDate7() {
		return inscriptionDate7 == null ? "" : inscriptionDate7.format(DateTimeFormatter.ISO_DATE);
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setInscriptionDate7 classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionDate7 w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_inscriptionDate7")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", inscriptionDate7 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate7))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate7', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate7')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate7')); }); } ")
				.fg();
		} else {
			sx(htmInscriptionDate7());
		}
	}

	public void htmInscriptionDate7(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate7").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate7(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate7')); $('#", classeApiMethodeMethode, "_inscriptionDate7').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate7', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate7')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate7')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionDate8
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate inscriptionDate8;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate8Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate8").o(inscriptionDate8);

	/**	<br/> L'entité inscriptionDate8
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate8">Trouver l'entité inscriptionDate8 dans Solr</a>
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
		this.inscriptionDate8 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate8Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate8(String o) {
		this.inscriptionDate8 = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.inscriptionDate8Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate8(Date o) {
		this.inscriptionDate8 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
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
		return inscriptionDate8 == null ? null : Date.from(inscriptionDate8.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strInscriptionDate8() {
		return inscriptionDate8 == null ? "" : inscriptionDate8.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonInscriptionDate8() {
		return inscriptionDate8 == null ? "" : inscriptionDate8.format(DateTimeFormatter.ISO_DATE);
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setInscriptionDate8 classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionDate8 w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_inscriptionDate8")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", inscriptionDate8 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate8))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate8', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate8')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate8')); }); } ")
				.fg();
		} else {
			sx(htmInscriptionDate8());
		}
	}

	public void htmInscriptionDate8(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate8").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate8(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate8')); $('#", classeApiMethodeMethode, "_inscriptionDate8').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate8', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate8')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate8')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionDate9
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate inscriptionDate9;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate9Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate9").o(inscriptionDate9);

	/**	<br/> L'entité inscriptionDate9
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate9">Trouver l'entité inscriptionDate9 dans Solr</a>
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
		this.inscriptionDate9 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate9Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate9(String o) {
		this.inscriptionDate9 = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.inscriptionDate9Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate9(Date o) {
		this.inscriptionDate9 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
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
		return inscriptionDate9 == null ? null : Date.from(inscriptionDate9.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strInscriptionDate9() {
		return inscriptionDate9 == null ? "" : inscriptionDate9.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonInscriptionDate9() {
		return inscriptionDate9 == null ? "" : inscriptionDate9.format(DateTimeFormatter.ISO_DATE);
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setInscriptionDate9 classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionDate9 w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_inscriptionDate9")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", inscriptionDate9 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate9))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate9', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate9')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate9')); }); } ")
				.fg();
		} else {
			sx(htmInscriptionDate9());
		}
	}

	public void htmInscriptionDate9(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate9").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate9(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate9')); $('#", classeApiMethodeMethode, "_inscriptionDate9').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate9', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate9')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate9')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
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

	/**	 L'entité inscriptionDate10
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate inscriptionDate10;
	@JsonIgnore
	public Couverture<LocalDate> inscriptionDate10Couverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("inscriptionDate10").o(inscriptionDate10);

	/**	<br/> L'entité inscriptionDate10
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionDate10">Trouver l'entité inscriptionDate10 dans Solr</a>
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
		this.inscriptionDate10 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate10Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public InscriptionScolaire setInscriptionDate10(String o) {
		this.inscriptionDate10 = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.inscriptionDate10Couverture.dejaInitialise = true;
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire setInscriptionDate10(Date o) {
		this.inscriptionDate10 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
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
		return inscriptionDate10 == null ? null : Date.from(inscriptionDate10.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strInscriptionDate10() {
		return inscriptionDate10 == null ? "" : inscriptionDate10.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonInscriptionDate10() {
		return inscriptionDate10 == null ? "" : inscriptionDate10.format(DateTimeFormatter.ISO_DATE);
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
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setInscriptionDate10 classInscriptionScolaire inputInscriptionScolaire", pk, "InscriptionDate10 w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_inscriptionDate10")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
				.a("value", inscriptionDate10 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate10))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate10', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate10')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate10')); }); } ")
				.fg();
		} else {
			sx(htmInscriptionDate10());
		}
	}

	public void htmInscriptionDate10(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireInscriptionDate10").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputInscriptionDate10(classeApiMethodeMethode);
							} g("div");
							if(
									utilisateurCles.contains(requeteSite_.getUtilisateurCle())
									|| Objects.equals(sessionId, requeteSite_.getSessionId())
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
							) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_inscriptionDate10')); $('#", classeApiMethodeMethode, "_inscriptionDate10').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=pk]').val() }], 'setInscriptionDate10', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate10')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate10')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////
	// inscriptionsAnnees //
	////////////////////////

	/**	 L'entité inscriptionsAnnees
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<InscriptionScolaire> inscriptionsAnnees = new ArrayList<InscriptionScolaire>();
	@JsonIgnore
	public Couverture<List<InscriptionScolaire>> inscriptionsAnneesCouverture = new Couverture<List<InscriptionScolaire>>().p(this).c(List.class).var("inscriptionsAnnees").o(inscriptionsAnnees);

	/**	<br/> L'entité inscriptionsAnnees
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionsAnnees">Trouver l'entité inscriptionsAnnees dans Solr</a>
	 * <br/>
	 * @param inscriptionsAnnees est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionsAnnees(List<InscriptionScolaire> l);

	public List<InscriptionScolaire> getInscriptionsAnnees() {
		return inscriptionsAnnees;
	}

	public void setInscriptionsAnnees(List<InscriptionScolaire> inscriptionsAnnees) {
		this.inscriptionsAnnees = inscriptionsAnnees;
		this.inscriptionsAnneesCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addInscriptionsAnnees(InscriptionScolaire...objets) {
		for(InscriptionScolaire o : objets) {
			addInscriptionsAnnees(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addInscriptionsAnnees(InscriptionScolaire o) {
		if(o != null && !inscriptionsAnnees.contains(o))
			this.inscriptionsAnnees.add(o);
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionsAnneesInit() {
		if(!inscriptionsAnneesCouverture.dejaInitialise) {
			_inscriptionsAnnees(inscriptionsAnnees);
		}
		inscriptionsAnneesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	///////////////////////////
	// inscriptionsApprouves //
	///////////////////////////

	/**	 L'entité inscriptionsApprouves
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<InscriptionScolaire> inscriptionsApprouves = new ArrayList<InscriptionScolaire>();
	@JsonIgnore
	public Couverture<List<InscriptionScolaire>> inscriptionsApprouvesCouverture = new Couverture<List<InscriptionScolaire>>().p(this).c(List.class).var("inscriptionsApprouves").o(inscriptionsApprouves);

	/**	<br/> L'entité inscriptionsApprouves
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionsApprouves">Trouver l'entité inscriptionsApprouves dans Solr</a>
	 * <br/>
	 * @param inscriptionsApprouves est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionsApprouves(List<InscriptionScolaire> l);

	public List<InscriptionScolaire> getInscriptionsApprouves() {
		return inscriptionsApprouves;
	}

	public void setInscriptionsApprouves(List<InscriptionScolaire> inscriptionsApprouves) {
		this.inscriptionsApprouves = inscriptionsApprouves;
		this.inscriptionsApprouvesCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addInscriptionsApprouves(InscriptionScolaire...objets) {
		for(InscriptionScolaire o : objets) {
			addInscriptionsApprouves(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addInscriptionsApprouves(InscriptionScolaire o) {
		if(o != null && !inscriptionsApprouves.contains(o))
			this.inscriptionsApprouves.add(o);
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionsApprouvesInit() {
		if(!inscriptionsApprouvesCouverture.dejaInitialise) {
			_inscriptionsApprouves(inscriptionsApprouves);
		}
		inscriptionsApprouvesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	////////////////////////
	// inscriptionsGroupe //
	////////////////////////

	/**	 L'entité inscriptionsGroupe
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<InscriptionScolaire> inscriptionsGroupe = new ArrayList<InscriptionScolaire>();
	@JsonIgnore
	public Couverture<List<InscriptionScolaire>> inscriptionsGroupeCouverture = new Couverture<List<InscriptionScolaire>>().p(this).c(List.class).var("inscriptionsGroupe").o(inscriptionsGroupe);

	/**	<br/> L'entité inscriptionsGroupe
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionsGroupe">Trouver l'entité inscriptionsGroupe dans Solr</a>
	 * <br/>
	 * @param inscriptionsGroupe est l'entité déjà construit. 
	 **/
	protected abstract void _inscriptionsGroupe(List<InscriptionScolaire> l);

	public List<InscriptionScolaire> getInscriptionsGroupe() {
		return inscriptionsGroupe;
	}

	public void setInscriptionsGroupe(List<InscriptionScolaire> inscriptionsGroupe) {
		this.inscriptionsGroupe = inscriptionsGroupe;
		this.inscriptionsGroupeCouverture.dejaInitialise = true;
	}
	public InscriptionScolaire addInscriptionsGroupe(InscriptionScolaire...objets) {
		for(InscriptionScolaire o : objets) {
			addInscriptionsGroupe(o);
		}
		return (InscriptionScolaire)this;
	}
	public InscriptionScolaire addInscriptionsGroupe(InscriptionScolaire o) {
		if(o != null && !inscriptionsGroupe.contains(o))
			this.inscriptionsGroupe.add(o);
		return (InscriptionScolaire)this;
	}
	protected InscriptionScolaire inscriptionsGroupeInit() {
		if(!inscriptionsGroupeCouverture.dejaInitialise) {
			_inscriptionsGroupe(inscriptionsGroupe);
		}
		inscriptionsGroupeCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	/////////////////////////////
	// inscriptionsInscription //
	/////////////////////////////

	/**	 L'entité inscriptionsInscription
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<InscriptionScolaire> inscriptionsInscription = new ArrayList<InscriptionScolaire>();
	@JsonIgnore
	public Couverture<List<InscriptionScolaire>> inscriptionsInscriptionCouverture = new Couverture<List<InscriptionScolaire>>().p(this).c(List.class).var("inscriptionsInscription").o(inscriptionsInscription);

	/**	<br/> L'entité inscriptionsInscription
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionsInscription">Trouver l'entité inscriptionsInscription dans Solr</a>
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

	/**	 L'entité enfantImmunisationsRecu
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantImmunisationsRecu;
	@JsonIgnore
	public Couverture<String> enfantImmunisationsRecuCouverture = new Couverture<String>().p(this).c(String.class).var("enfantImmunisationsRecu").o(enfantImmunisationsRecu);

	/**	<br/> L'entité enfantImmunisationsRecu
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantImmunisationsRecu">Trouver l'entité enfantImmunisationsRecu dans Solr</a>
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

	/**	 L'entité enfantPhotosApprouve
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantPhotosApprouve;
	@JsonIgnore
	public Couverture<String> enfantPhotosApprouveCouverture = new Couverture<String>().p(this).c(String.class).var("enfantPhotosApprouve").o(enfantPhotosApprouve);

	/**	<br/> L'entité enfantPhotosApprouve
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantPhotosApprouve">Trouver l'entité enfantPhotosApprouve dans Solr</a>
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

	/**	 L'entité inscriptionNumero
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer inscriptionNumero;
	@JsonIgnore
	public Couverture<Integer> inscriptionNumeroCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("inscriptionNumero").o(inscriptionNumero);

	/**	<br/> L'entité inscriptionNumero
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNumero">Trouver l'entité inscriptionNumero dans Solr</a>
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

	/**	 L'entité inscriptionNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String inscriptionNomComplet;
	@JsonIgnore
	public Couverture<String> inscriptionNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("inscriptionNomComplet").o(inscriptionNomComplet);

	/**	<br/> L'entité inscriptionNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNomComplet">Trouver l'entité inscriptionNomComplet dans Solr</a>
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
		sessionCleInit();
		ageCleInit();
		blocCleInit();
		enfantCleInit();
		mereClesInit();
		pereClesInit();
		gardienClesInit();
		paiementClesInit();
		formInscriptionCleInit();
		utilisateurClesInit();
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
		fraisRechercheInit();
		paiementRechercheInit();
		enfantPrenomInit();
		enfantPrenomPrefereInit();
		enfantFamilleNomInit();
		merePrenomInit();
		merePrenomPrefereInit();
		mereNomCompletPrefereInit();
		perePrenomInit();
		perePrenomPrefereInit();
		pereNomCompletPrefereInit();
		enfantNomCompletInit();
		enfantNomCompletPrefereInit();
		enfantDateNaissanceInit();
		enfantDateNaissanceDAnneeInit();
		enfantDateNaissanceMoisDAnneeInit();
		enfantDateNaissanceJourDeSemaineInit();
		enfantMoisNaissanceInit();
		enfantJourNaissanceInit();
		ecoleNomInit();
		ecoleNomCompletInit();
		ecoleEmplacementInit();
		ecoleAddresseInit();
		ecoleNumeroTelephoneInit();
		ecoleFormInit();
		ecoleNumeroInit();
		ecoleAdministrateurNomInit();
		anneeDebutInit();
		anneeFinInit();
		saisonDateDebutInit();
		anneeFraisInscriptionInit();
		sessionDateDebutInit();
		sessionDateFinInit();
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
		photoInit();
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
		inscriptionCouleurGroupeInit();
		inscriptionPaimentChaqueMoisInit();
		inscriptionPaimentCompletInit();
		customerProfileIdInit();
		inscriptionDateFraisInit();
		paiementFacetsInit();
		paiementLastStrInit();
		paiementMontantInit();
		fraisMontantInit();
		fraisMontantFutureInit();
		fraisMontantDuInit();
		fraisMaintenantInit();
		paiementsAJourInit();
		paiementsEnRetardInit();
		paiementsEnRetardMontantInit();
		paiementsEnAvanceInit();
		creeDAnneeInit();
		creeJourDeSemaineInit();
		creeMoisDAnneeInit();
		creeHeureDuJourInit();
		inscriptionJoursDeSemaineInit();
		inscriptionNomsParentsInit();
		inscriptionMailsInit();
		inscriptionMailInit();
		inscriptionMailsParentsInit();
		inscriptionNumeroTelephonesInit();
		inscriptionNumeroTelephoneInit();
		inscriptionNomParentInit();
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
		inscriptionsAnneesInit();
		inscriptionsApprouvesInit();
		inscriptionsGroupeInit();
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
		if(fraisRecherche != null)
			fraisRecherche.setRequeteSite_(requeteSite_);
		if(paiementRecherche != null)
			paiementRecherche.setRequeteSite_(requeteSite_);
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
			case "utilisateurCles":
				return oInscriptionScolaire.utilisateurCles;
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
			case "fraisRecherche":
				return oInscriptionScolaire.fraisRecherche;
			case "paiementRecherche":
				return oInscriptionScolaire.paiementRecherche;
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
			case "mereNomCompletPrefere":
				return oInscriptionScolaire.mereNomCompletPrefere;
			case "perePrenom":
				return oInscriptionScolaire.perePrenom;
			case "perePrenomPrefere":
				return oInscriptionScolaire.perePrenomPrefere;
			case "pereNomCompletPrefere":
				return oInscriptionScolaire.pereNomCompletPrefere;
			case "enfantNomComplet":
				return oInscriptionScolaire.enfantNomComplet;
			case "enfantNomCompletPrefere":
				return oInscriptionScolaire.enfantNomCompletPrefere;
			case "enfantDateNaissance":
				return oInscriptionScolaire.enfantDateNaissance;
			case "enfantDateNaissanceDAnnee":
				return oInscriptionScolaire.enfantDateNaissanceDAnnee;
			case "enfantDateNaissanceMoisDAnnee":
				return oInscriptionScolaire.enfantDateNaissanceMoisDAnnee;
			case "enfantDateNaissanceJourDeSemaine":
				return oInscriptionScolaire.enfantDateNaissanceJourDeSemaine;
			case "enfantMoisNaissance":
				return oInscriptionScolaire.enfantMoisNaissance;
			case "enfantJourNaissance":
				return oInscriptionScolaire.enfantJourNaissance;
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
			case "ecoleForm":
				return oInscriptionScolaire.ecoleForm;
			case "ecoleNumero":
				return oInscriptionScolaire.ecoleNumero;
			case "ecoleAdministrateurNom":
				return oInscriptionScolaire.ecoleAdministrateurNom;
			case "anneeDebut":
				return oInscriptionScolaire.anneeDebut;
			case "anneeFin":
				return oInscriptionScolaire.anneeFin;
			case "saisonDateDebut":
				return oInscriptionScolaire.saisonDateDebut;
			case "anneeFraisInscription":
				return oInscriptionScolaire.anneeFraisInscription;
			case "sessionDateDebut":
				return oInscriptionScolaire.sessionDateDebut;
			case "sessionDateFin":
				return oInscriptionScolaire.sessionDateFin;
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
			case "photo":
				return oInscriptionScolaire.photo;
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
			case "inscriptionCouleurGroupe":
				return oInscriptionScolaire.inscriptionCouleurGroupe;
			case "inscriptionPaimentChaqueMois":
				return oInscriptionScolaire.inscriptionPaimentChaqueMois;
			case "inscriptionPaimentComplet":
				return oInscriptionScolaire.inscriptionPaimentComplet;
			case "customerProfileId":
				return oInscriptionScolaire.customerProfileId;
			case "inscriptionDateFrais":
				return oInscriptionScolaire.inscriptionDateFrais;
			case "paiementFacets":
				return oInscriptionScolaire.paiementFacets;
			case "paiementLastStr":
				return oInscriptionScolaire.paiementLastStr;
			case "paiementMontant":
				return oInscriptionScolaire.paiementMontant;
			case "fraisMontant":
				return oInscriptionScolaire.fraisMontant;
			case "fraisMontantFuture":
				return oInscriptionScolaire.fraisMontantFuture;
			case "fraisMontantDu":
				return oInscriptionScolaire.fraisMontantDu;
			case "fraisMaintenant":
				return oInscriptionScolaire.fraisMaintenant;
			case "paiementsAJour":
				return oInscriptionScolaire.paiementsAJour;
			case "paiementsEnRetard":
				return oInscriptionScolaire.paiementsEnRetard;
			case "paiementsEnRetardMontant":
				return oInscriptionScolaire.paiementsEnRetardMontant;
			case "paiementsEnAvance":
				return oInscriptionScolaire.paiementsEnAvance;
			case "creeDAnnee":
				return oInscriptionScolaire.creeDAnnee;
			case "creeJourDeSemaine":
				return oInscriptionScolaire.creeJourDeSemaine;
			case "creeMoisDAnnee":
				return oInscriptionScolaire.creeMoisDAnnee;
			case "creeHeureDuJour":
				return oInscriptionScolaire.creeHeureDuJour;
			case "inscriptionJoursDeSemaine":
				return oInscriptionScolaire.inscriptionJoursDeSemaine;
			case "inscriptionNomsParents":
				return oInscriptionScolaire.inscriptionNomsParents;
			case "inscriptionMails":
				return oInscriptionScolaire.inscriptionMails;
			case "inscriptionMail":
				return oInscriptionScolaire.inscriptionMail;
			case "inscriptionMailsParents":
				return oInscriptionScolaire.inscriptionMailsParents;
			case "inscriptionNumeroTelephones":
				return oInscriptionScolaire.inscriptionNumeroTelephones;
			case "inscriptionNumeroTelephone":
				return oInscriptionScolaire.inscriptionNumeroTelephone;
			case "inscriptionNomParent":
				return oInscriptionScolaire.inscriptionNomParent;
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
			case "inscriptionsAnnees":
				return oInscriptionScolaire.inscriptionsAnnees;
			case "inscriptionsApprouves":
				return oInscriptionScolaire.inscriptionsApprouves;
			case "inscriptionsGroupe":
				return oInscriptionScolaire.inscriptionsGroupe;
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
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "blocCles":
				oInscriptionScolaire.addBlocCles((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "enfantCle":
				oInscriptionScolaire.setEnfantCle((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "mereCles":
				oInscriptionScolaire.addMereCles((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "pereCles":
				oInscriptionScolaire.addPereCles((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "gardienCles":
				oInscriptionScolaire.addGardienCles((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "paiementCles":
				oInscriptionScolaire.addPaiementCles((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "utilisateurCles":
				oInscriptionScolaire.addUtilisateurCles((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
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
				if(val != null)
					setEnfantNomComplet(val);
				sauvegardes.add(var);
				return val;
			case "enfantNomCompletPrefere":
				if(val != null)
					setEnfantNomCompletPrefere(val);
				sauvegardes.add(var);
				return val;
			case "enfantDateNaissance":
				if(val != null)
					setEnfantDateNaissance(val);
				sauvegardes.add(var);
				return val;
			case "ecoleAddresse":
				if(val != null)
					setEcoleAddresse(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionApprouve":
				if(val != null)
					setInscriptionApprouve(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionImmunisations":
				if(val != null)
					setInscriptionImmunisations(val);
				sauvegardes.add(var);
				return val;
			case "photo":
				if(val != null)
					setPhoto(val);
				sauvegardes.add(var);
				return val;
			case "familleMarie":
				if(val != null)
					setFamilleMarie(val);
				sauvegardes.add(var);
				return val;
			case "familleSepare":
				if(val != null)
					setFamilleSepare(val);
				sauvegardes.add(var);
				return val;
			case "familleDivorce":
				if(val != null)
					setFamilleDivorce(val);
				sauvegardes.add(var);
				return val;
			case "familleAddresse":
				if(val != null)
					setFamilleAddresse(val);
				sauvegardes.add(var);
				return val;
			case "familleCommentVousConnaissezEcole":
				if(val != null)
					setFamilleCommentVousConnaissezEcole(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionConsiderationsSpeciales":
				if(val != null)
					setInscriptionConsiderationsSpeciales(val);
				sauvegardes.add(var);
				return val;
			case "enfantConditionsMedicales":
				if(val != null)
					setEnfantConditionsMedicales(val);
				sauvegardes.add(var);
				return val;
			case "enfantEcolesPrecedemmentFrequentees":
				if(val != null)
					setEnfantEcolesPrecedemmentFrequentees(val);
				sauvegardes.add(var);
				return val;
			case "enfantDescription":
				if(val != null)
					setEnfantDescription(val);
				sauvegardes.add(var);
				return val;
			case "enfantObjectifs":
				if(val != null)
					setEnfantObjectifs(val);
				sauvegardes.add(var);
				return val;
			case "enfantPropre":
				if(val != null)
					setEnfantPropre(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionNomGroupe":
				if(val != null)
					setInscriptionNomGroupe(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionPaimentChaqueMois":
				if(val != null)
					setInscriptionPaimentChaqueMois(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionPaimentComplet":
				if(val != null)
					setInscriptionPaimentComplet(val);
				sauvegardes.add(var);
				return val;
			case "customerProfileId":
				if(val != null)
					setCustomerProfileId(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionDateFrais":
				if(val != null)
					setInscriptionDateFrais(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionNomsParents":
				if(val != null)
					setInscriptionNomsParents(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionSignature1":
				if(val != null)
					setInscriptionSignature1(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionSignature2":
				if(val != null)
					setInscriptionSignature2(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionSignature3":
				if(val != null)
					setInscriptionSignature3(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionSignature4":
				if(val != null)
					setInscriptionSignature4(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionSignature5":
				if(val != null)
					setInscriptionSignature5(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionSignature6":
				if(val != null)
					setInscriptionSignature6(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionSignature7":
				if(val != null)
					setInscriptionSignature7(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionSignature8":
				if(val != null)
					setInscriptionSignature8(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionSignature9":
				if(val != null)
					setInscriptionSignature9(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionSignature10":
				if(val != null)
					setInscriptionSignature10(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionDate1":
				if(val != null)
					setInscriptionDate1(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionDate2":
				if(val != null)
					setInscriptionDate2(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionDate3":
				if(val != null)
					setInscriptionDate3(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionDate4":
				if(val != null)
					setInscriptionDate4(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionDate5":
				if(val != null)
					setInscriptionDate5(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionDate6":
				if(val != null)
					setInscriptionDate6(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionDate7":
				if(val != null)
					setInscriptionDate7(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionDate8":
				if(val != null)
					setInscriptionDate8(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionDate9":
				if(val != null)
					setInscriptionDate9(val);
				sauvegardes.add(var);
				return val;
			case "inscriptionDate10":
				if(val != null)
					setInscriptionDate10(val);
				sauvegardes.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerInscriptionScolaire(solrDocument);
	}
	public void peuplerInscriptionScolaire(SolrDocument solrDocument) {
		InscriptionScolaire oInscriptionScolaire = (InscriptionScolaire)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("inscriptionCle")) {
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

			if(sauvegardes.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oInscriptionScolaire.setEcoleCle(ecoleCle);
			}

			if(sauvegardes.contains("sessionCle")) {
				Long sessionCle = (Long)solrDocument.get("sessionCle_stored_long");
				if(sessionCle != null)
					oInscriptionScolaire.setSessionCle(sessionCle);
			}

			if(sauvegardes.contains("ageCle")) {
				Long ageCle = (Long)solrDocument.get("ageCle_stored_long");
				if(ageCle != null)
					oInscriptionScolaire.setAgeCle(ageCle);
			}

			if(sauvegardes.contains("blocCle")) {
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

			if(sauvegardes.contains("formInscriptionCle")) {
				Long formInscriptionCle = (Long)solrDocument.get("formInscriptionCle_stored_long");
				if(formInscriptionCle != null)
					oInscriptionScolaire.setFormInscriptionCle(formInscriptionCle);
			}

			List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
			if(utilisateurCles != null)
				oInscriptionScolaire.utilisateurCles.addAll(utilisateurCles);

			if(sauvegardes.contains("scolaireTri")) {
				Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
				if(scolaireTri != null)
					oInscriptionScolaire.setScolaireTri(scolaireTri);
			}

			if(sauvegardes.contains("ecoleTri")) {
				Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
				if(ecoleTri != null)
					oInscriptionScolaire.setEcoleTri(ecoleTri);
			}

			if(sauvegardes.contains("anneeTri")) {
				Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
				if(anneeTri != null)
					oInscriptionScolaire.setAnneeTri(anneeTri);
			}

			if(sauvegardes.contains("saisonTri")) {
				Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
				if(saisonTri != null)
					oInscriptionScolaire.setSaisonTri(saisonTri);
			}

			if(sauvegardes.contains("sessionTri")) {
				Integer sessionTri = (Integer)solrDocument.get("sessionTri_stored_int");
				if(sessionTri != null)
					oInscriptionScolaire.setSessionTri(sessionTri);
			}

			if(sauvegardes.contains("ageTri")) {
				Integer ageTri = (Integer)solrDocument.get("ageTri_stored_int");
				if(ageTri != null)
					oInscriptionScolaire.setAgeTri(ageTri);
			}

			if(sauvegardes.contains("enfantPrenom")) {
				String enfantPrenom = (String)solrDocument.get("enfantPrenom_stored_string");
				if(enfantPrenom != null)
					oInscriptionScolaire.setEnfantPrenom(enfantPrenom);
			}

			if(sauvegardes.contains("enfantPrenomPrefere")) {
				String enfantPrenomPrefere = (String)solrDocument.get("enfantPrenomPrefere_stored_string");
				if(enfantPrenomPrefere != null)
					oInscriptionScolaire.setEnfantPrenomPrefere(enfantPrenomPrefere);
			}

			if(sauvegardes.contains("enfantFamilleNom")) {
				String enfantFamilleNom = (String)solrDocument.get("enfantFamilleNom_stored_string");
				if(enfantFamilleNom != null)
					oInscriptionScolaire.setEnfantFamilleNom(enfantFamilleNom);
			}

			if(sauvegardes.contains("merePrenom")) {
				String merePrenom = (String)solrDocument.get("merePrenom_stored_string");
				if(merePrenom != null)
					oInscriptionScolaire.setMerePrenom(merePrenom);
			}

			if(sauvegardes.contains("merePrenomPrefere")) {
				String merePrenomPrefere = (String)solrDocument.get("merePrenomPrefere_stored_string");
				if(merePrenomPrefere != null)
					oInscriptionScolaire.setMerePrenomPrefere(merePrenomPrefere);
			}

			if(sauvegardes.contains("mereNomCompletPrefere")) {
				String mereNomCompletPrefere = (String)solrDocument.get("mereNomCompletPrefere_stored_string");
				if(mereNomCompletPrefere != null)
					oInscriptionScolaire.setMereNomCompletPrefere(mereNomCompletPrefere);
			}

			if(sauvegardes.contains("perePrenom")) {
				String perePrenom = (String)solrDocument.get("perePrenom_stored_string");
				if(perePrenom != null)
					oInscriptionScolaire.setPerePrenom(perePrenom);
			}

			if(sauvegardes.contains("perePrenomPrefere")) {
				String perePrenomPrefere = (String)solrDocument.get("perePrenomPrefere_stored_string");
				if(perePrenomPrefere != null)
					oInscriptionScolaire.setPerePrenomPrefere(perePrenomPrefere);
			}

			if(sauvegardes.contains("pereNomCompletPrefere")) {
				String pereNomCompletPrefere = (String)solrDocument.get("pereNomCompletPrefere_stored_string");
				if(pereNomCompletPrefere != null)
					oInscriptionScolaire.setPereNomCompletPrefere(pereNomCompletPrefere);
			}

			if(sauvegardes.contains("enfantNomComplet")) {
				String enfantNomComplet = (String)solrDocument.get("enfantNomComplet_stored_string");
				if(enfantNomComplet != null)
					oInscriptionScolaire.setEnfantNomComplet(enfantNomComplet);
			}

			if(sauvegardes.contains("enfantNomCompletPrefere")) {
				String enfantNomCompletPrefere = (String)solrDocument.get("enfantNomCompletPrefere_stored_string");
				if(enfantNomCompletPrefere != null)
					oInscriptionScolaire.setEnfantNomCompletPrefere(enfantNomCompletPrefere);
			}

			if(sauvegardes.contains("enfantDateNaissance")) {
				Date enfantDateNaissance = (Date)solrDocument.get("enfantDateNaissance_stored_date");
				if(enfantDateNaissance != null)
					oInscriptionScolaire.setEnfantDateNaissance(enfantDateNaissance);
			}

			if(sauvegardes.contains("enfantDateNaissanceDAnnee")) {
				Integer enfantDateNaissanceDAnnee = (Integer)solrDocument.get("enfantDateNaissanceDAnnee_stored_int");
				if(enfantDateNaissanceDAnnee != null)
					oInscriptionScolaire.setEnfantDateNaissanceDAnnee(enfantDateNaissanceDAnnee);
			}

			if(sauvegardes.contains("enfantDateNaissanceMoisDAnnee")) {
				String enfantDateNaissanceMoisDAnnee = (String)solrDocument.get("enfantDateNaissanceMoisDAnnee_stored_string");
				if(enfantDateNaissanceMoisDAnnee != null)
					oInscriptionScolaire.setEnfantDateNaissanceMoisDAnnee(enfantDateNaissanceMoisDAnnee);
			}

			if(sauvegardes.contains("enfantDateNaissanceJourDeSemaine")) {
				String enfantDateNaissanceJourDeSemaine = (String)solrDocument.get("enfantDateNaissanceJourDeSemaine_stored_string");
				if(enfantDateNaissanceJourDeSemaine != null)
					oInscriptionScolaire.setEnfantDateNaissanceJourDeSemaine(enfantDateNaissanceJourDeSemaine);
			}

			if(sauvegardes.contains("enfantMoisNaissance")) {
				Integer enfantMoisNaissance = (Integer)solrDocument.get("enfantMoisNaissance_stored_int");
				if(enfantMoisNaissance != null)
					oInscriptionScolaire.setEnfantMoisNaissance(enfantMoisNaissance);
			}

			if(sauvegardes.contains("enfantJourNaissance")) {
				Integer enfantJourNaissance = (Integer)solrDocument.get("enfantJourNaissance_stored_int");
				if(enfantJourNaissance != null)
					oInscriptionScolaire.setEnfantJourNaissance(enfantJourNaissance);
			}

			if(sauvegardes.contains("ecoleNom")) {
				String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
				if(ecoleNom != null)
					oInscriptionScolaire.setEcoleNom(ecoleNom);
			}

			if(sauvegardes.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oInscriptionScolaire.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardes.contains("ecoleEmplacement")) {
				String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
				if(ecoleEmplacement != null)
					oInscriptionScolaire.setEcoleEmplacement(ecoleEmplacement);
			}

			if(sauvegardes.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oInscriptionScolaire.setEcoleAddresse(ecoleAddresse);
			}

			if(sauvegardes.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oInscriptionScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardes.contains("ecoleForm")) {
				String ecoleForm = (String)solrDocument.get("ecoleForm_stored_string");
				if(ecoleForm != null)
					oInscriptionScolaire.setEcoleForm(ecoleForm);
			}

			if(sauvegardes.contains("ecoleNumero")) {
				Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
				if(ecoleNumero != null)
					oInscriptionScolaire.setEcoleNumero(ecoleNumero);
			}

			if(sauvegardes.contains("ecoleAdministrateurNom")) {
				String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
				if(ecoleAdministrateurNom != null)
					oInscriptionScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);
			}

			if(sauvegardes.contains("anneeDebut")) {
				Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
				if(anneeDebut != null)
					oInscriptionScolaire.setAnneeDebut(anneeDebut);
			}

			if(sauvegardes.contains("anneeFin")) {
				Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
				if(anneeFin != null)
					oInscriptionScolaire.setAnneeFin(anneeFin);
			}

			if(sauvegardes.contains("saisonDateDebut")) {
				Date saisonDateDebut = (Date)solrDocument.get("saisonDateDebut_stored_date");
				if(saisonDateDebut != null)
					oInscriptionScolaire.setSaisonDateDebut(saisonDateDebut);
			}

			if(sauvegardes.contains("anneeFraisInscription")) {
				Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
				if(anneeFraisInscription != null)
					oInscriptionScolaire.setAnneeFraisInscription(anneeFraisInscription);
			}

			if(sauvegardes.contains("sessionDateDebut")) {
				Date sessionDateDebut = (Date)solrDocument.get("sessionDateDebut_stored_date");
				if(sessionDateDebut != null)
					oInscriptionScolaire.setSessionDateDebut(sessionDateDebut);
			}

			if(sauvegardes.contains("sessionDateFin")) {
				Date sessionDateFin = (Date)solrDocument.get("sessionDateFin_stored_date");
				if(sessionDateFin != null)
					oInscriptionScolaire.setSessionDateFin(sessionDateFin);
			}

			if(sauvegardes.contains("ageNomComplet")) {
				String ageNomComplet = (String)solrDocument.get("ageNomComplet_stored_string");
				if(ageNomComplet != null)
					oInscriptionScolaire.setAgeNomComplet(ageNomComplet);
			}

			if(sauvegardes.contains("ageDebut")) {
				Integer ageDebut = (Integer)solrDocument.get("ageDebut_stored_int");
				if(ageDebut != null)
					oInscriptionScolaire.setAgeDebut(ageDebut);
			}

			if(sauvegardes.contains("ageFin")) {
				Integer ageFin = (Integer)solrDocument.get("ageFin_stored_int");
				if(ageFin != null)
					oInscriptionScolaire.setAgeFin(ageFin);
			}

			if(sauvegardes.contains("blocHeureDebut")) {
				String blocHeureDebut = (String)solrDocument.get("blocHeureDebut_stored_string");
				if(blocHeureDebut != null)
					oInscriptionScolaire.setBlocHeureDebut(blocHeureDebut);
			}

			if(sauvegardes.contains("blocHeureFin")) {
				String blocHeureFin = (String)solrDocument.get("blocHeureFin_stored_string");
				if(blocHeureFin != null)
					oInscriptionScolaire.setBlocHeureFin(blocHeureFin);
			}

			if(sauvegardes.contains("blocPrixParMois")) {
				Double blocPrixParMois = (Double)solrDocument.get("blocPrixParMois_stored_double");
				if(blocPrixParMois != null)
					oInscriptionScolaire.setBlocPrixParMois(blocPrixParMois);
			}

			if(sauvegardes.contains("blocDimanche")) {
				Boolean blocDimanche = (Boolean)solrDocument.get("blocDimanche_stored_boolean");
				if(blocDimanche != null)
					oInscriptionScolaire.setBlocDimanche(blocDimanche);
			}

			if(sauvegardes.contains("blocLundi")) {
				Boolean blocLundi = (Boolean)solrDocument.get("blocLundi_stored_boolean");
				if(blocLundi != null)
					oInscriptionScolaire.setBlocLundi(blocLundi);
			}

			if(sauvegardes.contains("blocMardi")) {
				Boolean blocMardi = (Boolean)solrDocument.get("blocMardi_stored_boolean");
				if(blocMardi != null)
					oInscriptionScolaire.setBlocMardi(blocMardi);
			}

			if(sauvegardes.contains("blocMercredi")) {
				Boolean blocMercredi = (Boolean)solrDocument.get("blocMercredi_stored_boolean");
				if(blocMercredi != null)
					oInscriptionScolaire.setBlocMercredi(blocMercredi);
			}

			if(sauvegardes.contains("blocJeudi")) {
				Boolean blocJeudi = (Boolean)solrDocument.get("blocJeudi_stored_boolean");
				if(blocJeudi != null)
					oInscriptionScolaire.setBlocJeudi(blocJeudi);
			}

			if(sauvegardes.contains("blocVendredi")) {
				Boolean blocVendredi = (Boolean)solrDocument.get("blocVendredi_stored_boolean");
				if(blocVendredi != null)
					oInscriptionScolaire.setBlocVendredi(blocVendredi);
			}

			if(sauvegardes.contains("blocSamedi")) {
				Boolean blocSamedi = (Boolean)solrDocument.get("blocSamedi_stored_boolean");
				if(blocSamedi != null)
					oInscriptionScolaire.setBlocSamedi(blocSamedi);
			}

			if(sauvegardes.contains("blocPrixTotal")) {
				Double blocPrixTotal = (Double)solrDocument.get("blocPrixTotal_stored_double");
				if(blocPrixTotal != null)
					oInscriptionScolaire.setBlocPrixTotal(blocPrixTotal);
			}

			if(sauvegardes.contains("blocNomAdmin")) {
				String blocNomAdmin = (String)solrDocument.get("blocNomAdmin_stored_string");
				if(blocNomAdmin != null)
					oInscriptionScolaire.setBlocNomAdmin(blocNomAdmin);
			}

			if(sauvegardes.contains("blocNomCourt")) {
				String blocNomCourt = (String)solrDocument.get("blocNomCourt_stored_string");
				if(blocNomCourt != null)
					oInscriptionScolaire.setBlocNomCourt(blocNomCourt);
			}

			if(sauvegardes.contains("blocNomComplet")) {
				String blocNomComplet = (String)solrDocument.get("blocNomComplet_stored_string");
				if(blocNomComplet != null)
					oInscriptionScolaire.setBlocNomComplet(blocNomComplet);
			}

			if(sauvegardes.contains("inscriptionApprouve")) {
				Boolean inscriptionApprouve = (Boolean)solrDocument.get("inscriptionApprouve_stored_boolean");
				if(inscriptionApprouve != null)
					oInscriptionScolaire.setInscriptionApprouve(inscriptionApprouve);
			}

			if(sauvegardes.contains("inscriptionImmunisations")) {
				Boolean inscriptionImmunisations = (Boolean)solrDocument.get("inscriptionImmunisations_stored_boolean");
				if(inscriptionImmunisations != null)
					oInscriptionScolaire.setInscriptionImmunisations(inscriptionImmunisations);
			}

			if(sauvegardes.contains("photo")) {
				String photo = (String)solrDocument.get("photo_stored_string");
				if(photo != null)
					oInscriptionScolaire.setPhoto(photo);
			}

			if(sauvegardes.contains("familleMarie")) {
				Boolean familleMarie = (Boolean)solrDocument.get("familleMarie_stored_boolean");
				if(familleMarie != null)
					oInscriptionScolaire.setFamilleMarie(familleMarie);
			}

			if(sauvegardes.contains("familleSepare")) {
				Boolean familleSepare = (Boolean)solrDocument.get("familleSepare_stored_boolean");
				if(familleSepare != null)
					oInscriptionScolaire.setFamilleSepare(familleSepare);
			}

			if(sauvegardes.contains("familleDivorce")) {
				Boolean familleDivorce = (Boolean)solrDocument.get("familleDivorce_stored_boolean");
				if(familleDivorce != null)
					oInscriptionScolaire.setFamilleDivorce(familleDivorce);
			}

			if(sauvegardes.contains("familleAddresse")) {
				String familleAddresse = (String)solrDocument.get("familleAddresse_stored_string");
				if(familleAddresse != null)
					oInscriptionScolaire.setFamilleAddresse(familleAddresse);
			}

			if(sauvegardes.contains("familleCommentVousConnaissezEcole")) {
				String familleCommentVousConnaissezEcole = (String)solrDocument.get("familleCommentVousConnaissezEcole_stored_string");
				if(familleCommentVousConnaissezEcole != null)
					oInscriptionScolaire.setFamilleCommentVousConnaissezEcole(familleCommentVousConnaissezEcole);
			}

			if(sauvegardes.contains("inscriptionConsiderationsSpeciales")) {
				String inscriptionConsiderationsSpeciales = (String)solrDocument.get("inscriptionConsiderationsSpeciales_stored_string");
				if(inscriptionConsiderationsSpeciales != null)
					oInscriptionScolaire.setInscriptionConsiderationsSpeciales(inscriptionConsiderationsSpeciales);
			}

			if(sauvegardes.contains("enfantConditionsMedicales")) {
				String enfantConditionsMedicales = (String)solrDocument.get("enfantConditionsMedicales_stored_string");
				if(enfantConditionsMedicales != null)
					oInscriptionScolaire.setEnfantConditionsMedicales(enfantConditionsMedicales);
			}

			if(sauvegardes.contains("enfantEcolesPrecedemmentFrequentees")) {
				String enfantEcolesPrecedemmentFrequentees = (String)solrDocument.get("enfantEcolesPrecedemmentFrequentees_stored_string");
				if(enfantEcolesPrecedemmentFrequentees != null)
					oInscriptionScolaire.setEnfantEcolesPrecedemmentFrequentees(enfantEcolesPrecedemmentFrequentees);
			}

			if(sauvegardes.contains("enfantDescription")) {
				String enfantDescription = (String)solrDocument.get("enfantDescription_stored_string");
				if(enfantDescription != null)
					oInscriptionScolaire.setEnfantDescription(enfantDescription);
			}

			if(sauvegardes.contains("enfantObjectifs")) {
				String enfantObjectifs = (String)solrDocument.get("enfantObjectifs_stored_string");
				if(enfantObjectifs != null)
					oInscriptionScolaire.setEnfantObjectifs(enfantObjectifs);
			}

			if(sauvegardes.contains("enfantPropre")) {
				Boolean enfantPropre = (Boolean)solrDocument.get("enfantPropre_stored_boolean");
				if(enfantPropre != null)
					oInscriptionScolaire.setEnfantPropre(enfantPropre);
			}

			if(sauvegardes.contains("inscriptionNomGroupe")) {
				String inscriptionNomGroupe = (String)solrDocument.get("inscriptionNomGroupe_stored_string");
				if(inscriptionNomGroupe != null)
					oInscriptionScolaire.setInscriptionNomGroupe(inscriptionNomGroupe);
			}

			if(sauvegardes.contains("inscriptionCouleurGroupe")) {
				String inscriptionCouleurGroupe = (String)solrDocument.get("inscriptionCouleurGroupe_stored_string");
				if(inscriptionCouleurGroupe != null)
					oInscriptionScolaire.setInscriptionCouleurGroupe(inscriptionCouleurGroupe);
			}

			if(sauvegardes.contains("inscriptionPaimentChaqueMois")) {
				Boolean inscriptionPaimentChaqueMois = (Boolean)solrDocument.get("inscriptionPaimentChaqueMois_stored_boolean");
				if(inscriptionPaimentChaqueMois != null)
					oInscriptionScolaire.setInscriptionPaimentChaqueMois(inscriptionPaimentChaqueMois);
			}

			if(sauvegardes.contains("inscriptionPaimentComplet")) {
				Boolean inscriptionPaimentComplet = (Boolean)solrDocument.get("inscriptionPaimentComplet_stored_boolean");
				if(inscriptionPaimentComplet != null)
					oInscriptionScolaire.setInscriptionPaimentComplet(inscriptionPaimentComplet);
			}

			if(sauvegardes.contains("customerProfileId")) {
				String customerProfileId = (String)solrDocument.get("customerProfileId_stored_string");
				if(customerProfileId != null)
					oInscriptionScolaire.setCustomerProfileId(customerProfileId);
			}

			if(sauvegardes.contains("inscriptionDateFrais")) {
				Date inscriptionDateFrais = (Date)solrDocument.get("inscriptionDateFrais_stored_date");
				if(inscriptionDateFrais != null)
					oInscriptionScolaire.setInscriptionDateFrais(inscriptionDateFrais);
			}

			if(sauvegardes.contains("paiementLastStr")) {
				String paiementLastStr = (String)solrDocument.get("paiementLastStr_stored_string");
				if(paiementLastStr != null)
					oInscriptionScolaire.setPaiementLastStr(paiementLastStr);
			}

			if(sauvegardes.contains("paiementMontant")) {
				Double paiementMontant = (Double)solrDocument.get("paiementMontant_stored_double");
				if(paiementMontant != null)
					oInscriptionScolaire.setPaiementMontant(paiementMontant);
			}

			if(sauvegardes.contains("fraisMontant")) {
				Double fraisMontant = (Double)solrDocument.get("fraisMontant_stored_double");
				if(fraisMontant != null)
					oInscriptionScolaire.setFraisMontant(fraisMontant);
			}

			if(sauvegardes.contains("fraisMontantFuture")) {
				Double fraisMontantFuture = (Double)solrDocument.get("fraisMontantFuture_stored_double");
				if(fraisMontantFuture != null)
					oInscriptionScolaire.setFraisMontantFuture(fraisMontantFuture);
			}

			if(sauvegardes.contains("fraisMontantDu")) {
				Double fraisMontantDu = (Double)solrDocument.get("fraisMontantDu_stored_double");
				if(fraisMontantDu != null)
					oInscriptionScolaire.setFraisMontantDu(fraisMontantDu);
			}

			if(sauvegardes.contains("fraisMaintenant")) {
				Double fraisMaintenant = (Double)solrDocument.get("fraisMaintenant_stored_double");
				if(fraisMaintenant != null)
					oInscriptionScolaire.setFraisMaintenant(fraisMaintenant);
			}

			if(sauvegardes.contains("paiementsAJour")) {
				Boolean paiementsAJour = (Boolean)solrDocument.get("paiementsAJour_stored_boolean");
				if(paiementsAJour != null)
					oInscriptionScolaire.setPaiementsAJour(paiementsAJour);
			}

			if(sauvegardes.contains("paiementsEnRetard")) {
				Boolean paiementsEnRetard = (Boolean)solrDocument.get("paiementsEnRetard_stored_boolean");
				if(paiementsEnRetard != null)
					oInscriptionScolaire.setPaiementsEnRetard(paiementsEnRetard);
			}

			if(sauvegardes.contains("paiementsEnRetardMontant")) {
				Double paiementsEnRetardMontant = (Double)solrDocument.get("paiementsEnRetardMontant_stored_double");
				if(paiementsEnRetardMontant != null)
					oInscriptionScolaire.setPaiementsEnRetardMontant(paiementsEnRetardMontant);
			}

			if(sauvegardes.contains("paiementsEnAvance")) {
				Boolean paiementsEnAvance = (Boolean)solrDocument.get("paiementsEnAvance_stored_boolean");
				if(paiementsEnAvance != null)
					oInscriptionScolaire.setPaiementsEnAvance(paiementsEnAvance);
			}

			if(sauvegardes.contains("creeDAnnee")) {
				Integer creeDAnnee = (Integer)solrDocument.get("creeDAnnee_stored_int");
				if(creeDAnnee != null)
					oInscriptionScolaire.setCreeDAnnee(creeDAnnee);
			}

			if(sauvegardes.contains("creeJourDeSemaine")) {
				String creeJourDeSemaine = (String)solrDocument.get("creeJourDeSemaine_stored_string");
				if(creeJourDeSemaine != null)
					oInscriptionScolaire.setCreeJourDeSemaine(creeJourDeSemaine);
			}

			if(sauvegardes.contains("creeMoisDAnnee")) {
				String creeMoisDAnnee = (String)solrDocument.get("creeMoisDAnnee_stored_string");
				if(creeMoisDAnnee != null)
					oInscriptionScolaire.setCreeMoisDAnnee(creeMoisDAnnee);
			}

			if(sauvegardes.contains("creeHeureDuJour")) {
				String creeHeureDuJour = (String)solrDocument.get("creeHeureDuJour_stored_string");
				if(creeHeureDuJour != null)
					oInscriptionScolaire.setCreeHeureDuJour(creeHeureDuJour);
			}

			if(sauvegardes.contains("inscriptionJoursDeSemaine")) {
				List<String> inscriptionJoursDeSemaine = (List<String>)solrDocument.get("inscriptionJoursDeSemaine_stored_strings");
				if(inscriptionJoursDeSemaine != null)
					oInscriptionScolaire.inscriptionJoursDeSemaine.addAll(inscriptionJoursDeSemaine);
			}

			if(sauvegardes.contains("inscriptionNomsParents")) {
				String inscriptionNomsParents = (String)solrDocument.get("inscriptionNomsParents_stored_string");
				if(inscriptionNomsParents != null)
					oInscriptionScolaire.setInscriptionNomsParents(inscriptionNomsParents);
			}

			if(sauvegardes.contains("inscriptionMails")) {
				List<String> inscriptionMails = (List<String>)solrDocument.get("inscriptionMails_stored_strings");
				if(inscriptionMails != null)
					oInscriptionScolaire.inscriptionMails.addAll(inscriptionMails);
			}

			if(sauvegardes.contains("inscriptionMail")) {
				String inscriptionMail = (String)solrDocument.get("inscriptionMail_stored_string");
				if(inscriptionMail != null)
					oInscriptionScolaire.setInscriptionMail(inscriptionMail);
			}

			if(sauvegardes.contains("inscriptionMailsParents")) {
				String inscriptionMailsParents = (String)solrDocument.get("inscriptionMailsParents_stored_string");
				if(inscriptionMailsParents != null)
					oInscriptionScolaire.setInscriptionMailsParents(inscriptionMailsParents);
			}

			if(sauvegardes.contains("inscriptionNumeroTelephones")) {
				List<String> inscriptionNumeroTelephones = (List<String>)solrDocument.get("inscriptionNumeroTelephones_stored_strings");
				if(inscriptionNumeroTelephones != null)
					oInscriptionScolaire.inscriptionNumeroTelephones.addAll(inscriptionNumeroTelephones);
			}

			if(sauvegardes.contains("inscriptionNumeroTelephone")) {
				String inscriptionNumeroTelephone = (String)solrDocument.get("inscriptionNumeroTelephone_stored_string");
				if(inscriptionNumeroTelephone != null)
					oInscriptionScolaire.setInscriptionNumeroTelephone(inscriptionNumeroTelephone);
			}

			if(sauvegardes.contains("inscriptionNomParent")) {
				String inscriptionNomParent = (String)solrDocument.get("inscriptionNomParent_stored_string");
				if(inscriptionNomParent != null)
					oInscriptionScolaire.setInscriptionNomParent(inscriptionNomParent);
			}

			if(sauvegardes.contains("inscriptionNomParentLignes")) {
				String inscriptionNomParentLignes = (String)solrDocument.get("inscriptionNomParentLignes_stored_string");
				if(inscriptionNomParentLignes != null)
					oInscriptionScolaire.setInscriptionNomParentLignes(inscriptionNomParentLignes);
			}

			if(sauvegardes.contains("inscriptionMailParentLignes")) {
				String inscriptionMailParentLignes = (String)solrDocument.get("inscriptionMailParentLignes_stored_string");
				if(inscriptionMailParentLignes != null)
					oInscriptionScolaire.setInscriptionMailParentLignes(inscriptionMailParentLignes);
			}

			if(sauvegardes.contains("inscriptionDetailParentLignes")) {
				String inscriptionDetailParentLignes = (String)solrDocument.get("inscriptionDetailParentLignes_stored_string");
				if(inscriptionDetailParentLignes != null)
					oInscriptionScolaire.setInscriptionDetailParentLignes(inscriptionDetailParentLignes);
			}

			if(sauvegardes.contains("inscriptionChercherParentLignes")) {
				String inscriptionChercherParentLignes = (String)solrDocument.get("inscriptionChercherParentLignes_stored_string");
				if(inscriptionChercherParentLignes != null)
					oInscriptionScolaire.setInscriptionChercherParentLignes(inscriptionChercherParentLignes);
			}

			if(sauvegardes.contains("inscriptionContactUrgenceParentLignes")) {
				String inscriptionContactUrgenceParentLignes = (String)solrDocument.get("inscriptionContactUrgenceParentLignes_stored_string");
				if(inscriptionContactUrgenceParentLignes != null)
					oInscriptionScolaire.setInscriptionContactUrgenceParentLignes(inscriptionContactUrgenceParentLignes);
			}

			if(sauvegardes.contains("inscriptionSignature1")) {
				String inscriptionSignature1 = (String)solrDocument.get("inscriptionSignature1_stored_string");
				if(inscriptionSignature1 != null)
					oInscriptionScolaire.setInscriptionSignature1(inscriptionSignature1);
			}

			if(sauvegardes.contains("inscriptionSignature2")) {
				String inscriptionSignature2 = (String)solrDocument.get("inscriptionSignature2_stored_string");
				if(inscriptionSignature2 != null)
					oInscriptionScolaire.setInscriptionSignature2(inscriptionSignature2);
			}

			if(sauvegardes.contains("inscriptionSignature3")) {
				String inscriptionSignature3 = (String)solrDocument.get("inscriptionSignature3_stored_string");
				if(inscriptionSignature3 != null)
					oInscriptionScolaire.setInscriptionSignature3(inscriptionSignature3);
			}

			if(sauvegardes.contains("inscriptionSignature4")) {
				String inscriptionSignature4 = (String)solrDocument.get("inscriptionSignature4_stored_string");
				if(inscriptionSignature4 != null)
					oInscriptionScolaire.setInscriptionSignature4(inscriptionSignature4);
			}

			if(sauvegardes.contains("inscriptionSignature5")) {
				String inscriptionSignature5 = (String)solrDocument.get("inscriptionSignature5_stored_string");
				if(inscriptionSignature5 != null)
					oInscriptionScolaire.setInscriptionSignature5(inscriptionSignature5);
			}

			if(sauvegardes.contains("inscriptionSignature6")) {
				String inscriptionSignature6 = (String)solrDocument.get("inscriptionSignature6_stored_string");
				if(inscriptionSignature6 != null)
					oInscriptionScolaire.setInscriptionSignature6(inscriptionSignature6);
			}

			if(sauvegardes.contains("inscriptionSignature7")) {
				String inscriptionSignature7 = (String)solrDocument.get("inscriptionSignature7_stored_string");
				if(inscriptionSignature7 != null)
					oInscriptionScolaire.setInscriptionSignature7(inscriptionSignature7);
			}

			if(sauvegardes.contains("inscriptionSignature8")) {
				String inscriptionSignature8 = (String)solrDocument.get("inscriptionSignature8_stored_string");
				if(inscriptionSignature8 != null)
					oInscriptionScolaire.setInscriptionSignature8(inscriptionSignature8);
			}

			if(sauvegardes.contains("inscriptionSignature9")) {
				String inscriptionSignature9 = (String)solrDocument.get("inscriptionSignature9_stored_string");
				if(inscriptionSignature9 != null)
					oInscriptionScolaire.setInscriptionSignature9(inscriptionSignature9);
			}

			if(sauvegardes.contains("inscriptionSignature10")) {
				String inscriptionSignature10 = (String)solrDocument.get("inscriptionSignature10_stored_string");
				if(inscriptionSignature10 != null)
					oInscriptionScolaire.setInscriptionSignature10(inscriptionSignature10);
			}

			if(sauvegardes.contains("inscriptionDate1")) {
				Date inscriptionDate1 = (Date)solrDocument.get("inscriptionDate1_stored_date");
				if(inscriptionDate1 != null)
					oInscriptionScolaire.setInscriptionDate1(inscriptionDate1);
			}

			if(sauvegardes.contains("inscriptionDate2")) {
				Date inscriptionDate2 = (Date)solrDocument.get("inscriptionDate2_stored_date");
				if(inscriptionDate2 != null)
					oInscriptionScolaire.setInscriptionDate2(inscriptionDate2);
			}

			if(sauvegardes.contains("inscriptionDate3")) {
				Date inscriptionDate3 = (Date)solrDocument.get("inscriptionDate3_stored_date");
				if(inscriptionDate3 != null)
					oInscriptionScolaire.setInscriptionDate3(inscriptionDate3);
			}

			if(sauvegardes.contains("inscriptionDate4")) {
				Date inscriptionDate4 = (Date)solrDocument.get("inscriptionDate4_stored_date");
				if(inscriptionDate4 != null)
					oInscriptionScolaire.setInscriptionDate4(inscriptionDate4);
			}

			if(sauvegardes.contains("inscriptionDate5")) {
				Date inscriptionDate5 = (Date)solrDocument.get("inscriptionDate5_stored_date");
				if(inscriptionDate5 != null)
					oInscriptionScolaire.setInscriptionDate5(inscriptionDate5);
			}

			if(sauvegardes.contains("inscriptionDate6")) {
				Date inscriptionDate6 = (Date)solrDocument.get("inscriptionDate6_stored_date");
				if(inscriptionDate6 != null)
					oInscriptionScolaire.setInscriptionDate6(inscriptionDate6);
			}

			if(sauvegardes.contains("inscriptionDate7")) {
				Date inscriptionDate7 = (Date)solrDocument.get("inscriptionDate7_stored_date");
				if(inscriptionDate7 != null)
					oInscriptionScolaire.setInscriptionDate7(inscriptionDate7);
			}

			if(sauvegardes.contains("inscriptionDate8")) {
				Date inscriptionDate8 = (Date)solrDocument.get("inscriptionDate8_stored_date");
				if(inscriptionDate8 != null)
					oInscriptionScolaire.setInscriptionDate8(inscriptionDate8);
			}

			if(sauvegardes.contains("inscriptionDate9")) {
				Date inscriptionDate9 = (Date)solrDocument.get("inscriptionDate9_stored_date");
				if(inscriptionDate9 != null)
					oInscriptionScolaire.setInscriptionDate9(inscriptionDate9);
			}

			if(sauvegardes.contains("inscriptionDate10")) {
				Date inscriptionDate10 = (Date)solrDocument.get("inscriptionDate10_stored_date");
				if(inscriptionDate10 != null)
					oInscriptionScolaire.setInscriptionDate10(inscriptionDate10);
			}

			if(sauvegardes.contains("enfantImmunisationsRecu")) {
				String enfantImmunisationsRecu = (String)solrDocument.get("enfantImmunisationsRecu_stored_string");
				if(enfantImmunisationsRecu != null)
					oInscriptionScolaire.setEnfantImmunisationsRecu(enfantImmunisationsRecu);
			}

			if(sauvegardes.contains("enfantPhotosApprouve")) {
				String enfantPhotosApprouve = (String)solrDocument.get("enfantPhotosApprouve_stored_string");
				if(enfantPhotosApprouve != null)
					oInscriptionScolaire.setEnfantPhotosApprouve(enfantPhotosApprouve);
			}

			if(sauvegardes.contains("inscriptionNomComplet")) {
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
		if(utilisateurCles != null) {
			for(java.lang.Long o : utilisateurCles) {
				document.addField("utilisateurCles_indexed_longs", o);
			}
			for(java.lang.Long o : utilisateurCles) {
				document.addField("utilisateurCles_stored_longs", o);
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
		if(mereNomCompletPrefere != null) {
			document.addField("mereNomCompletPrefere_indexed_string", mereNomCompletPrefere);
			document.addField("mereNomCompletPrefere_stored_string", mereNomCompletPrefere);
		}
		if(perePrenom != null) {
			document.addField("perePrenom_indexed_string", perePrenom);
			document.addField("perePrenom_stored_string", perePrenom);
		}
		if(perePrenomPrefere != null) {
			document.addField("perePrenomPrefere_indexed_string", perePrenomPrefere);
			document.addField("perePrenomPrefere_stored_string", perePrenomPrefere);
		}
		if(pereNomCompletPrefere != null) {
			document.addField("pereNomCompletPrefere_indexed_string", pereNomCompletPrefere);
			document.addField("pereNomCompletPrefere_stored_string", pereNomCompletPrefere);
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
			document.addField("enfantDateNaissance_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enfantDateNaissance.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enfantDateNaissance_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enfantDateNaissance.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(enfantDateNaissanceDAnnee != null) {
			document.addField("enfantDateNaissanceDAnnee_indexed_int", enfantDateNaissanceDAnnee);
			document.addField("enfantDateNaissanceDAnnee_stored_int", enfantDateNaissanceDAnnee);
		}
		if(enfantDateNaissanceMoisDAnnee != null) {
			document.addField("enfantDateNaissanceMoisDAnnee_indexed_string", enfantDateNaissanceMoisDAnnee);
			document.addField("enfantDateNaissanceMoisDAnnee_stored_string", enfantDateNaissanceMoisDAnnee);
		}
		if(enfantDateNaissanceJourDeSemaine != null) {
			document.addField("enfantDateNaissanceJourDeSemaine_indexed_string", enfantDateNaissanceJourDeSemaine);
			document.addField("enfantDateNaissanceJourDeSemaine_stored_string", enfantDateNaissanceJourDeSemaine);
		}
		if(enfantMoisNaissance != null) {
			document.addField("enfantMoisNaissance_indexed_int", enfantMoisNaissance);
			document.addField("enfantMoisNaissance_stored_int", enfantMoisNaissance);
		}
		if(enfantJourNaissance != null) {
			document.addField("enfantJourNaissance_indexed_int", enfantJourNaissance);
			document.addField("enfantJourNaissance_stored_int", enfantJourNaissance);
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
		if(ecoleForm != null) {
			document.addField("ecoleForm_indexed_string", ecoleForm);
			document.addField("ecoleForm_stored_string", ecoleForm);
		}
		if(ecoleNumero != null) {
			document.addField("ecoleNumero_indexed_int", ecoleNumero);
			document.addField("ecoleNumero_stored_int", ecoleNumero);
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
		if(saisonDateDebut != null) {
			document.addField("saisonDateDebut_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(saisonDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("saisonDateDebut_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(saisonDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(anneeFraisInscription != null) {
			document.addField("anneeFraisInscription_indexed_double", anneeFraisInscription.doubleValue());
			document.addField("anneeFraisInscription_stored_double", anneeFraisInscription.doubleValue());
		}
		if(sessionDateDebut != null) {
			document.addField("sessionDateDebut_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionDateDebut_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionDateFin != null) {
			document.addField("sessionDateFin_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateFin.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionDateFin_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateFin.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
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
			document.addField("blocHeureDebut_indexed_string", DateTimeFormatter.ofPattern("HH:mm").format(blocHeureDebut.atOffset(ZoneOffset.UTC)));
			document.addField("blocHeureDebut_stored_string", DateTimeFormatter.ofPattern("HH:mm").format(blocHeureDebut.atOffset(ZoneOffset.UTC)));
		}
		if(blocHeureFin != null) {
			document.addField("blocHeureFin_indexed_string", DateTimeFormatter.ofPattern("HH:mm").format(blocHeureFin.atOffset(ZoneOffset.UTC)));
			document.addField("blocHeureFin_stored_string", DateTimeFormatter.ofPattern("HH:mm").format(blocHeureFin.atOffset(ZoneOffset.UTC)));
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
		if(photo != null) {
			document.addField("photo_stored_string", photo);
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
			document.addField("enfantConditionsMedicales_text_frFR", enfantConditionsMedicales.toString());
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
		if(inscriptionCouleurGroupe != null) {
			document.addField("inscriptionCouleurGroupe_indexed_string", inscriptionCouleurGroupe);
			document.addField("inscriptionCouleurGroupe_stored_string", inscriptionCouleurGroupe);
		}
		if(inscriptionPaimentChaqueMois != null) {
			document.addField("inscriptionPaimentChaqueMois_indexed_boolean", inscriptionPaimentChaqueMois);
			document.addField("inscriptionPaimentChaqueMois_stored_boolean", inscriptionPaimentChaqueMois);
		}
		if(inscriptionPaimentComplet != null) {
			document.addField("inscriptionPaimentComplet_indexed_boolean", inscriptionPaimentComplet);
			document.addField("inscriptionPaimentComplet_stored_boolean", inscriptionPaimentComplet);
		}
		if(customerProfileId != null) {
			document.addField("customerProfileId_indexed_string", customerProfileId);
			document.addField("customerProfileId_stored_string", customerProfileId);
		}
		if(inscriptionDateFrais != null) {
			document.addField("inscriptionDateFrais_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDateFrais.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDateFrais_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDateFrais.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(paiementLastStr != null) {
			document.addField("paiementLastStr_indexed_string", paiementLastStr);
			document.addField("paiementLastStr_stored_string", paiementLastStr);
		}
		if(paiementMontant != null) {
			document.addField("paiementMontant_indexed_double", paiementMontant.doubleValue());
			document.addField("paiementMontant_stored_double", paiementMontant.doubleValue());
		}
		if(fraisMontant != null) {
			document.addField("fraisMontant_indexed_double", fraisMontant.doubleValue());
			document.addField("fraisMontant_stored_double", fraisMontant.doubleValue());
		}
		if(fraisMontantFuture != null) {
			document.addField("fraisMontantFuture_indexed_double", fraisMontantFuture.doubleValue());
			document.addField("fraisMontantFuture_stored_double", fraisMontantFuture.doubleValue());
		}
		if(fraisMontantDu != null) {
			document.addField("fraisMontantDu_indexed_double", fraisMontantDu.doubleValue());
			document.addField("fraisMontantDu_stored_double", fraisMontantDu.doubleValue());
		}
		if(fraisMaintenant != null) {
			document.addField("fraisMaintenant_indexed_double", fraisMaintenant.doubleValue());
			document.addField("fraisMaintenant_stored_double", fraisMaintenant.doubleValue());
		}
		if(paiementsAJour != null) {
			document.addField("paiementsAJour_indexed_boolean", paiementsAJour);
			document.addField("paiementsAJour_stored_boolean", paiementsAJour);
		}
		if(paiementsEnRetard != null) {
			document.addField("paiementsEnRetard_indexed_boolean", paiementsEnRetard);
			document.addField("paiementsEnRetard_stored_boolean", paiementsEnRetard);
		}
		if(paiementsEnRetardMontant != null) {
			document.addField("paiementsEnRetardMontant_indexed_double", paiementsEnRetardMontant.doubleValue());
			document.addField("paiementsEnRetardMontant_stored_double", paiementsEnRetardMontant.doubleValue());
		}
		if(paiementsEnAvance != null) {
			document.addField("paiementsEnAvance_indexed_boolean", paiementsEnAvance);
			document.addField("paiementsEnAvance_stored_boolean", paiementsEnAvance);
		}
		if(creeDAnnee != null) {
			document.addField("creeDAnnee_indexed_int", creeDAnnee);
			document.addField("creeDAnnee_stored_int", creeDAnnee);
		}
		if(creeJourDeSemaine != null) {
			document.addField("creeJourDeSemaine_indexed_string", creeJourDeSemaine);
			document.addField("creeJourDeSemaine_stored_string", creeJourDeSemaine);
		}
		if(creeMoisDAnnee != null) {
			document.addField("creeMoisDAnnee_indexed_string", creeMoisDAnnee);
			document.addField("creeMoisDAnnee_stored_string", creeMoisDAnnee);
		}
		if(creeHeureDuJour != null) {
			document.addField("creeHeureDuJour_indexed_string", creeHeureDuJour);
			document.addField("creeHeureDuJour_stored_string", creeHeureDuJour);
		}
		if(inscriptionJoursDeSemaine != null) {
			for(java.lang.String o : inscriptionJoursDeSemaine) {
				document.addField("inscriptionJoursDeSemaine_indexed_strings", o);
			}
			for(java.lang.String o : inscriptionJoursDeSemaine) {
				document.addField("inscriptionJoursDeSemaine_stored_strings", o);
			}
		}
		if(inscriptionNomsParents != null) {
			document.addField("inscriptionNomsParents_stored_string", inscriptionNomsParents);
		}
		if(inscriptionMails != null) {
			for(java.lang.String o : inscriptionMails) {
				document.addField("inscriptionMails_indexed_strings", o);
			}
			for(java.lang.String o : inscriptionMails) {
				document.addField("inscriptionMails_stored_strings", o);
			}
		}
		if(inscriptionMail != null) {
			document.addField("inscriptionMail_indexed_string", inscriptionMail);
			document.addField("inscriptionMail_stored_string", inscriptionMail);
		}
		if(inscriptionMailsParents != null) {
			document.addField("inscriptionMailsParents_stored_string", inscriptionMailsParents);
		}
		if(inscriptionNumeroTelephones != null) {
			for(java.lang.String o : inscriptionNumeroTelephones) {
				document.addField("inscriptionNumeroTelephones_indexed_strings", o);
			}
			for(java.lang.String o : inscriptionNumeroTelephones) {
				document.addField("inscriptionNumeroTelephones_stored_strings", o);
			}
		}
		if(inscriptionNumeroTelephone != null) {
			document.addField("inscriptionNumeroTelephone_indexed_string", inscriptionNumeroTelephone);
			document.addField("inscriptionNumeroTelephone_stored_string", inscriptionNumeroTelephone);
		}
		if(inscriptionNomParent != null) {
			document.addField("inscriptionNomParent_indexed_string", inscriptionNomParent);
			document.addField("inscriptionNomParent_stored_string", inscriptionNomParent);
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
			document.addField("inscriptionDate1_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate1.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate1_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate1.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate2 != null) {
			document.addField("inscriptionDate2_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate2.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate2_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate2.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate3 != null) {
			document.addField("inscriptionDate3_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate3.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate3_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate3.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate4 != null) {
			document.addField("inscriptionDate4_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate4.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate4_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate4.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate5 != null) {
			document.addField("inscriptionDate5_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate5.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate5_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate5.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate6 != null) {
			document.addField("inscriptionDate6_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate6.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate6_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate6.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate7 != null) {
			document.addField("inscriptionDate7_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate7.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate7_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate7.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate8 != null) {
			document.addField("inscriptionDate8_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate8.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate8_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate8.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate9 != null) {
			document.addField("inscriptionDate9_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate9.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate9_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate9.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(inscriptionDate10 != null) {
			document.addField("inscriptionDate10_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate10.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("inscriptionDate10_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(inscriptionDate10.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
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
			case "utilisateurCles":
				return "utilisateurCles_indexed_longs";
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
			case "mereNomCompletPrefere":
				return "mereNomCompletPrefere_indexed_string";
			case "perePrenom":
				return "perePrenom_indexed_string";
			case "perePrenomPrefere":
				return "perePrenomPrefere_indexed_string";
			case "pereNomCompletPrefere":
				return "pereNomCompletPrefere_indexed_string";
			case "enfantNomComplet":
				return "enfantNomComplet_indexed_string";
			case "enfantNomCompletPrefere":
				return "enfantNomCompletPrefere_indexed_string";
			case "enfantDateNaissance":
				return "enfantDateNaissance_indexed_date";
			case "enfantDateNaissanceDAnnee":
				return "enfantDateNaissanceDAnnee_indexed_int";
			case "enfantDateNaissanceMoisDAnnee":
				return "enfantDateNaissanceMoisDAnnee_indexed_string";
			case "enfantDateNaissanceJourDeSemaine":
				return "enfantDateNaissanceJourDeSemaine_indexed_string";
			case "enfantMoisNaissance":
				return "enfantMoisNaissance_indexed_int";
			case "enfantJourNaissance":
				return "enfantJourNaissance_indexed_int";
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
			case "ecoleForm":
				return "ecoleForm_indexed_string";
			case "ecoleNumero":
				return "ecoleNumero_indexed_int";
			case "ecoleAdministrateurNom":
				return "ecoleAdministrateurNom_indexed_string";
			case "anneeDebut":
				return "anneeDebut_indexed_int";
			case "anneeFin":
				return "anneeFin_indexed_int";
			case "saisonDateDebut":
				return "saisonDateDebut_indexed_date";
			case "anneeFraisInscription":
				return "anneeFraisInscription_indexed_double";
			case "sessionDateDebut":
				return "sessionDateDebut_indexed_date";
			case "sessionDateFin":
				return "sessionDateFin_indexed_date";
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
			case "inscriptionCouleurGroupe":
				return "inscriptionCouleurGroupe_indexed_string";
			case "inscriptionPaimentChaqueMois":
				return "inscriptionPaimentChaqueMois_indexed_boolean";
			case "inscriptionPaimentComplet":
				return "inscriptionPaimentComplet_indexed_boolean";
			case "customerProfileId":
				return "customerProfileId_indexed_string";
			case "inscriptionDateFrais":
				return "inscriptionDateFrais_indexed_date";
			case "paiementLastStr":
				return "paiementLastStr_indexed_string";
			case "paiementMontant":
				return "paiementMontant_indexed_double";
			case "fraisMontant":
				return "fraisMontant_indexed_double";
			case "fraisMontantFuture":
				return "fraisMontantFuture_indexed_double";
			case "fraisMontantDu":
				return "fraisMontantDu_indexed_double";
			case "fraisMaintenant":
				return "fraisMaintenant_indexed_double";
			case "paiementsAJour":
				return "paiementsAJour_indexed_boolean";
			case "paiementsEnRetard":
				return "paiementsEnRetard_indexed_boolean";
			case "paiementsEnRetardMontant":
				return "paiementsEnRetardMontant_indexed_double";
			case "paiementsEnAvance":
				return "paiementsEnAvance_indexed_boolean";
			case "creeDAnnee":
				return "creeDAnnee_indexed_int";
			case "creeJourDeSemaine":
				return "creeJourDeSemaine_indexed_string";
			case "creeMoisDAnnee":
				return "creeMoisDAnnee_indexed_string";
			case "creeHeureDuJour":
				return "creeHeureDuJour_indexed_string";
			case "inscriptionJoursDeSemaine":
				return "inscriptionJoursDeSemaine_indexed_strings";
			case "inscriptionMails":
				return "inscriptionMails_indexed_strings";
			case "inscriptionMail":
				return "inscriptionMail_indexed_string";
			case "inscriptionNumeroTelephones":
				return "inscriptionNumeroTelephones_indexed_strings";
			case "inscriptionNumeroTelephone":
				return "inscriptionNumeroTelephone_indexed_string";
			case "inscriptionNomParent":
				return "inscriptionNomParent_indexed_string";
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
			case "enfantConditionsMedicales":
				return "enfantConditionsMedicales_text_frFR";
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

		List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
		if(utilisateurCles != null)
			oInscriptionScolaire.utilisateurCles.addAll(utilisateurCles);

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

		String mereNomCompletPrefere = (String)solrDocument.get("mereNomCompletPrefere_stored_string");
		if(mereNomCompletPrefere != null)
			oInscriptionScolaire.setMereNomCompletPrefere(mereNomCompletPrefere);

		String perePrenom = (String)solrDocument.get("perePrenom_stored_string");
		if(perePrenom != null)
			oInscriptionScolaire.setPerePrenom(perePrenom);

		String perePrenomPrefere = (String)solrDocument.get("perePrenomPrefere_stored_string");
		if(perePrenomPrefere != null)
			oInscriptionScolaire.setPerePrenomPrefere(perePrenomPrefere);

		String pereNomCompletPrefere = (String)solrDocument.get("pereNomCompletPrefere_stored_string");
		if(pereNomCompletPrefere != null)
			oInscriptionScolaire.setPereNomCompletPrefere(pereNomCompletPrefere);

		String enfantNomComplet = (String)solrDocument.get("enfantNomComplet_stored_string");
		if(enfantNomComplet != null)
			oInscriptionScolaire.setEnfantNomComplet(enfantNomComplet);

		String enfantNomCompletPrefere = (String)solrDocument.get("enfantNomCompletPrefere_stored_string");
		if(enfantNomCompletPrefere != null)
			oInscriptionScolaire.setEnfantNomCompletPrefere(enfantNomCompletPrefere);

		Date enfantDateNaissance = (Date)solrDocument.get("enfantDateNaissance_stored_date");
		if(enfantDateNaissance != null)
			oInscriptionScolaire.setEnfantDateNaissance(enfantDateNaissance);

		Integer enfantDateNaissanceDAnnee = (Integer)solrDocument.get("enfantDateNaissanceDAnnee_stored_int");
		if(enfantDateNaissanceDAnnee != null)
			oInscriptionScolaire.setEnfantDateNaissanceDAnnee(enfantDateNaissanceDAnnee);

		String enfantDateNaissanceMoisDAnnee = (String)solrDocument.get("enfantDateNaissanceMoisDAnnee_stored_string");
		if(enfantDateNaissanceMoisDAnnee != null)
			oInscriptionScolaire.setEnfantDateNaissanceMoisDAnnee(enfantDateNaissanceMoisDAnnee);

		String enfantDateNaissanceJourDeSemaine = (String)solrDocument.get("enfantDateNaissanceJourDeSemaine_stored_string");
		if(enfantDateNaissanceJourDeSemaine != null)
			oInscriptionScolaire.setEnfantDateNaissanceJourDeSemaine(enfantDateNaissanceJourDeSemaine);

		Integer enfantMoisNaissance = (Integer)solrDocument.get("enfantMoisNaissance_stored_int");
		if(enfantMoisNaissance != null)
			oInscriptionScolaire.setEnfantMoisNaissance(enfantMoisNaissance);

		Integer enfantJourNaissance = (Integer)solrDocument.get("enfantJourNaissance_stored_int");
		if(enfantJourNaissance != null)
			oInscriptionScolaire.setEnfantJourNaissance(enfantJourNaissance);

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

		String ecoleForm = (String)solrDocument.get("ecoleForm_stored_string");
		if(ecoleForm != null)
			oInscriptionScolaire.setEcoleForm(ecoleForm);

		Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
		if(ecoleNumero != null)
			oInscriptionScolaire.setEcoleNumero(ecoleNumero);

		String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
		if(ecoleAdministrateurNom != null)
			oInscriptionScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);

		Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
		if(anneeDebut != null)
			oInscriptionScolaire.setAnneeDebut(anneeDebut);

		Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
		if(anneeFin != null)
			oInscriptionScolaire.setAnneeFin(anneeFin);

		Date saisonDateDebut = (Date)solrDocument.get("saisonDateDebut_stored_date");
		if(saisonDateDebut != null)
			oInscriptionScolaire.setSaisonDateDebut(saisonDateDebut);

		Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
		if(anneeFraisInscription != null)
			oInscriptionScolaire.setAnneeFraisInscription(anneeFraisInscription);

		Date sessionDateDebut = (Date)solrDocument.get("sessionDateDebut_stored_date");
		if(sessionDateDebut != null)
			oInscriptionScolaire.setSessionDateDebut(sessionDateDebut);

		Date sessionDateFin = (Date)solrDocument.get("sessionDateFin_stored_date");
		if(sessionDateFin != null)
			oInscriptionScolaire.setSessionDateFin(sessionDateFin);

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

		String photo = (String)solrDocument.get("photo_stored_string");
		if(photo != null)
			oInscriptionScolaire.setPhoto(photo);

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

		String inscriptionCouleurGroupe = (String)solrDocument.get("inscriptionCouleurGroupe_stored_string");
		if(inscriptionCouleurGroupe != null)
			oInscriptionScolaire.setInscriptionCouleurGroupe(inscriptionCouleurGroupe);

		Boolean inscriptionPaimentChaqueMois = (Boolean)solrDocument.get("inscriptionPaimentChaqueMois_stored_boolean");
		if(inscriptionPaimentChaqueMois != null)
			oInscriptionScolaire.setInscriptionPaimentChaqueMois(inscriptionPaimentChaqueMois);

		Boolean inscriptionPaimentComplet = (Boolean)solrDocument.get("inscriptionPaimentComplet_stored_boolean");
		if(inscriptionPaimentComplet != null)
			oInscriptionScolaire.setInscriptionPaimentComplet(inscriptionPaimentComplet);

		String customerProfileId = (String)solrDocument.get("customerProfileId_stored_string");
		if(customerProfileId != null)
			oInscriptionScolaire.setCustomerProfileId(customerProfileId);

		Date inscriptionDateFrais = (Date)solrDocument.get("inscriptionDateFrais_stored_date");
		if(inscriptionDateFrais != null)
			oInscriptionScolaire.setInscriptionDateFrais(inscriptionDateFrais);

		String paiementLastStr = (String)solrDocument.get("paiementLastStr_stored_string");
		if(paiementLastStr != null)
			oInscriptionScolaire.setPaiementLastStr(paiementLastStr);

		Double paiementMontant = (Double)solrDocument.get("paiementMontant_stored_double");
		if(paiementMontant != null)
			oInscriptionScolaire.setPaiementMontant(paiementMontant);

		Double fraisMontant = (Double)solrDocument.get("fraisMontant_stored_double");
		if(fraisMontant != null)
			oInscriptionScolaire.setFraisMontant(fraisMontant);

		Double fraisMontantFuture = (Double)solrDocument.get("fraisMontantFuture_stored_double");
		if(fraisMontantFuture != null)
			oInscriptionScolaire.setFraisMontantFuture(fraisMontantFuture);

		Double fraisMontantDu = (Double)solrDocument.get("fraisMontantDu_stored_double");
		if(fraisMontantDu != null)
			oInscriptionScolaire.setFraisMontantDu(fraisMontantDu);

		Double fraisMaintenant = (Double)solrDocument.get("fraisMaintenant_stored_double");
		if(fraisMaintenant != null)
			oInscriptionScolaire.setFraisMaintenant(fraisMaintenant);

		Boolean paiementsAJour = (Boolean)solrDocument.get("paiementsAJour_stored_boolean");
		if(paiementsAJour != null)
			oInscriptionScolaire.setPaiementsAJour(paiementsAJour);

		Boolean paiementsEnRetard = (Boolean)solrDocument.get("paiementsEnRetard_stored_boolean");
		if(paiementsEnRetard != null)
			oInscriptionScolaire.setPaiementsEnRetard(paiementsEnRetard);

		Double paiementsEnRetardMontant = (Double)solrDocument.get("paiementsEnRetardMontant_stored_double");
		if(paiementsEnRetardMontant != null)
			oInscriptionScolaire.setPaiementsEnRetardMontant(paiementsEnRetardMontant);

		Boolean paiementsEnAvance = (Boolean)solrDocument.get("paiementsEnAvance_stored_boolean");
		if(paiementsEnAvance != null)
			oInscriptionScolaire.setPaiementsEnAvance(paiementsEnAvance);

		Integer creeDAnnee = (Integer)solrDocument.get("creeDAnnee_stored_int");
		if(creeDAnnee != null)
			oInscriptionScolaire.setCreeDAnnee(creeDAnnee);

		String creeJourDeSemaine = (String)solrDocument.get("creeJourDeSemaine_stored_string");
		if(creeJourDeSemaine != null)
			oInscriptionScolaire.setCreeJourDeSemaine(creeJourDeSemaine);

		String creeMoisDAnnee = (String)solrDocument.get("creeMoisDAnnee_stored_string");
		if(creeMoisDAnnee != null)
			oInscriptionScolaire.setCreeMoisDAnnee(creeMoisDAnnee);

		String creeHeureDuJour = (String)solrDocument.get("creeHeureDuJour_stored_string");
		if(creeHeureDuJour != null)
			oInscriptionScolaire.setCreeHeureDuJour(creeHeureDuJour);

		List<String> inscriptionJoursDeSemaine = (List<String>)solrDocument.get("inscriptionJoursDeSemaine_stored_strings");
		if(inscriptionJoursDeSemaine != null)
			oInscriptionScolaire.inscriptionJoursDeSemaine.addAll(inscriptionJoursDeSemaine);

		String inscriptionNomsParents = (String)solrDocument.get("inscriptionNomsParents_stored_string");
		if(inscriptionNomsParents != null)
			oInscriptionScolaire.setInscriptionNomsParents(inscriptionNomsParents);

		List<String> inscriptionMails = (List<String>)solrDocument.get("inscriptionMails_stored_strings");
		if(inscriptionMails != null)
			oInscriptionScolaire.inscriptionMails.addAll(inscriptionMails);

		String inscriptionMail = (String)solrDocument.get("inscriptionMail_stored_string");
		if(inscriptionMail != null)
			oInscriptionScolaire.setInscriptionMail(inscriptionMail);

		String inscriptionMailsParents = (String)solrDocument.get("inscriptionMailsParents_stored_string");
		if(inscriptionMailsParents != null)
			oInscriptionScolaire.setInscriptionMailsParents(inscriptionMailsParents);

		List<String> inscriptionNumeroTelephones = (List<String>)solrDocument.get("inscriptionNumeroTelephones_stored_strings");
		if(inscriptionNumeroTelephones != null)
			oInscriptionScolaire.inscriptionNumeroTelephones.addAll(inscriptionNumeroTelephones);

		String inscriptionNumeroTelephone = (String)solrDocument.get("inscriptionNumeroTelephone_stored_string");
		if(inscriptionNumeroTelephone != null)
			oInscriptionScolaire.setInscriptionNumeroTelephone(inscriptionNumeroTelephone);

		String inscriptionNomParent = (String)solrDocument.get("inscriptionNomParent_stored_string");
		if(inscriptionNomParent != null)
			oInscriptionScolaire.setInscriptionNomParent(inscriptionNomParent);

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
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof InscriptionScolaire) {
			InscriptionScolaire original = (InscriptionScolaire)o;
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
			if(!Objects.equals(utilisateurCles, original.getUtilisateurCles()))
				requeteApi.addVars("utilisateurCles");
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
			if(!Objects.equals(photo, original.getPhoto()))
				requeteApi.addVars("photo");
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
			if(!Objects.equals(customerProfileId, original.getCustomerProfileId()))
				requeteApi.addVars("customerProfileId");
			if(!Objects.equals(inscriptionDateFrais, original.getInscriptionDateFrais()))
				requeteApi.addVars("inscriptionDateFrais");
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
		return Objects.hash(super.hashCode(), anneeCle, blocCles, enfantCle, mereCles, pereCles, gardienCles, paiementCles, utilisateurCles, enfantNomComplet, enfantNomCompletPrefere, enfantDateNaissance, ecoleAddresse, inscriptionApprouve, inscriptionImmunisations, photo, familleMarie, familleSepare, familleDivorce, familleAddresse, familleCommentVousConnaissezEcole, inscriptionConsiderationsSpeciales, enfantConditionsMedicales, enfantEcolesPrecedemmentFrequentees, enfantDescription, enfantObjectifs, enfantPropre, inscriptionNomGroupe, inscriptionPaimentChaqueMois, inscriptionPaimentComplet, customerProfileId, inscriptionDateFrais, inscriptionNomsParents, inscriptionSignature1, inscriptionSignature2, inscriptionSignature3, inscriptionSignature4, inscriptionSignature5, inscriptionSignature6, inscriptionSignature7, inscriptionSignature8, inscriptionSignature9, inscriptionSignature10, inscriptionDate1, inscriptionDate2, inscriptionDate3, inscriptionDate4, inscriptionDate5, inscriptionDate6, inscriptionDate7, inscriptionDate8, inscriptionDate9, inscriptionDate10);
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
				&& Objects.equals( utilisateurCles, that.utilisateurCles )
				&& Objects.equals( enfantNomComplet, that.enfantNomComplet )
				&& Objects.equals( enfantNomCompletPrefere, that.enfantNomCompletPrefere )
				&& Objects.equals( enfantDateNaissance, that.enfantDateNaissance )
				&& Objects.equals( ecoleAddresse, that.ecoleAddresse )
				&& Objects.equals( inscriptionApprouve, that.inscriptionApprouve )
				&& Objects.equals( inscriptionImmunisations, that.inscriptionImmunisations )
				&& Objects.equals( photo, that.photo )
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
				&& Objects.equals( customerProfileId, that.customerProfileId )
				&& Objects.equals( inscriptionDateFrais, that.inscriptionDateFrais )
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
		sb.append( ", utilisateurCles: " ).append(utilisateurCles);
		sb.append( ", enfantNomComplet: \"" ).append(enfantNomComplet).append( "\"" );
		sb.append( ", enfantNomCompletPrefere: \"" ).append(enfantNomCompletPrefere).append( "\"" );
		sb.append( ", enfantDateNaissance: " ).append(enfantDateNaissance);
		sb.append( ", ecoleAddresse: \"" ).append(ecoleAddresse).append( "\"" );
		sb.append( ", inscriptionApprouve: " ).append(inscriptionApprouve);
		sb.append( ", inscriptionImmunisations: " ).append(inscriptionImmunisations);
		sb.append( ", photo: \"" ).append(photo).append( "\"" );
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
		sb.append( ", customerProfileId: \"" ).append(customerProfileId).append( "\"" );
		sb.append( ", inscriptionDateFrais: " ).append(inscriptionDateFrais);
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
