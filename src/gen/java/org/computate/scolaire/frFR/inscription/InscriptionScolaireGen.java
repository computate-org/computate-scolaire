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
import java.math.RoundingMode;
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
	public static final Integer InscriptionScolaire_Lignes = 20;

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
	public void setInscriptionCle(String o) {
		this.inscriptionCle = InscriptionScolaire.staticSetInscriptionCle(requeteSite_, o);
		this.inscriptionCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetInscriptionCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrInscriptionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrInscriptionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionCle(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionCle(requeteSite_, InscriptionScolaire.staticSolrInscriptionCle(requeteSite_, InscriptionScolaire.staticSetInscriptionCle(requeteSite_, o)));
	}

	public Long solrInscriptionCle() {
		return InscriptionScolaire.staticSolrInscriptionCle(requeteSite_, inscriptionCle);
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
	public void setAnneeCle(String o) {
		this.anneeCle = InscriptionScolaire.staticSetAnneeCle(requeteSite_, o);
		this.anneeCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetAnneeCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrAnneeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAnneeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeCle(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrAnneeCle(requeteSite_, InscriptionScolaire.staticSolrAnneeCle(requeteSite_, InscriptionScolaire.staticSetAnneeCle(requeteSite_, o)));
	}

	public Long solrAnneeCle() {
		return InscriptionScolaire.staticSolrAnneeCle(requeteSite_, anneeCle);
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

	public void inputAnneeCle(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_anneeCle_vider")
						.a("class", "anneeCle_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_anneeCle_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "année")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("class", "valeur suggereAnneeCle w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setAnneeCle")
				.a("id", classeApiMethodeMethode, "_anneeCle")
				.a("autocomplete", "off");
				a("oninput", "suggereInscriptionScolaireAnneeCle($(this).val() ? rechercherAnneeScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolaireAnneeCle_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "AnneeCle ").f().sx(htmAnneeCle()).g("span");
		}
	}

	public void htmAnneeCle(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireAnneeCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/annee?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-orange w3-hover-orange ").f();
								e("i").a("class", "far fa-calendar-check ").f().g("i");
								sx("année");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier une année a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputAnneeCle(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireAnneeCle_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), AnneeScolaire.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), AnneeScolaire.ROLES)
										) {
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
												.a("id", classeApiMethodeMethode, "_anneeCle_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postAnneeScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "anneeCle')); });")
												.f().sx("ajouter une année")
											.g("button");
										} g("div");
									}
								}
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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
	public static ListeRecherche<AnneeScolaire> staticSetAnneeRecherche(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static AnneeScolaire staticSetAnnee_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public void setBlocCles(String o) {
		Long l = InscriptionScolaire.staticSetBlocCles(requeteSite_, o);
		if(l != null)
			addBlocCles(l);
		this.blocClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetBlocCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setBlocCles(JsonArray objets) {
		blocCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlocCles(o);
		}
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

	public static Long staticSolrBlocCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrBlocCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocCles(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocCles(requeteSite_, InscriptionScolaire.staticSolrBlocCles(requeteSite_, InscriptionScolaire.staticSetBlocCles(requeteSite_, o)));
	}

	public List<Long> solrBlocCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : blocCles) {
			l.add(InscriptionScolaire.staticSolrBlocCles(requeteSite_, o));
		}
		return l;
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
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_blocCles_vider")
						.a("class", "blocCles_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_blocCles_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "blocs")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("class", "valeur suggereBlocCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setBlocCles")
				.a("id", classeApiMethodeMethode, "_blocCles")
				.a("autocomplete", "off");
				a("oninput", "suggereInscriptionScolaireBlocCles($(this).val() ? rechercherBlocScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolaireBlocCles_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "BlocCles ").f().sx(htmBlocCles()).g("span");
		}
	}

	public void htmBlocCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireBlocCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/bloc?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-indigo w3-hover-indigo ").f();
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
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
												.a("id", classeApiMethodeMethode, "_blocCles_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postBlocScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "blocCles')); });")
												.f().sx("ajouter un bloc")
											.g("button");
										} g("div");
									}
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
	public static ListeRecherche<BlocScolaire> staticSetBlocRecherche(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static BlocScolaire staticSetBlocs_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static SaisonScolaire staticSetSaisons_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static BlocScolaire staticSetBloc_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public void setEcoleCle(String o) {
		this.ecoleCle = InscriptionScolaire.staticSetEcoleCle(requeteSite_, o);
		this.ecoleCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetEcoleCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrEcoleCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrEcoleCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleCle(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEcoleCle(requeteSite_, InscriptionScolaire.staticSolrEcoleCle(requeteSite_, InscriptionScolaire.staticSetEcoleCle(requeteSite_, o)));
	}

	public Long solrEcoleCle() {
		return InscriptionScolaire.staticSolrEcoleCle(requeteSite_, ecoleCle);
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
	public void setSessionCle(String o) {
		this.sessionCle = InscriptionScolaire.staticSetSessionCle(requeteSite_, o);
		this.sessionCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetSessionCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrSessionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionCle(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrSessionCle(requeteSite_, InscriptionScolaire.staticSolrSessionCle(requeteSite_, InscriptionScolaire.staticSetSessionCle(requeteSite_, o)));
	}

	public Long solrSessionCle() {
		return InscriptionScolaire.staticSolrSessionCle(requeteSite_, sessionCle);
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
	public void setAgeCle(String o) {
		this.ageCle = InscriptionScolaire.staticSetAgeCle(requeteSite_, o);
		this.ageCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetAgeCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrAgeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeCle(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrAgeCle(requeteSite_, InscriptionScolaire.staticSolrAgeCle(requeteSite_, InscriptionScolaire.staticSetAgeCle(requeteSite_, o)));
	}

	public Long solrAgeCle() {
		return InscriptionScolaire.staticSolrAgeCle(requeteSite_, ageCle);
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
	public void setBlocCle(String o) {
		this.blocCle = InscriptionScolaire.staticSetBlocCle(requeteSite_, o);
		this.blocCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetBlocCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrBlocCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrBlocCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocCle(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocCle(requeteSite_, InscriptionScolaire.staticSolrBlocCle(requeteSite_, InscriptionScolaire.staticSetBlocCle(requeteSite_, o)));
	}

	public Long solrBlocCle() {
		return InscriptionScolaire.staticSolrBlocCle(requeteSite_, blocCle);
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
	public void setEnfantCle(String o) {
		this.enfantCle = InscriptionScolaire.staticSetEnfantCle(requeteSite_, o);
		this.enfantCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetEnfantCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrEnfantCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrEnfantCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantCle(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantCle(requeteSite_, InscriptionScolaire.staticSolrEnfantCle(requeteSite_, InscriptionScolaire.staticSetEnfantCle(requeteSite_, o)));
	}

	public Long solrEnfantCle() {
		return InscriptionScolaire.staticSolrEnfantCle(requeteSite_, enfantCle);
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
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_enfantCle_vider")
						.a("class", "enfantCle_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_enfantCle_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "enfants")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("class", "valeur suggereEnfantCle w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setEnfantCle")
				.a("id", classeApiMethodeMethode, "_enfantCle")
				.a("autocomplete", "off");
				a("oninput", "suggereInscriptionScolaireEnfantCle($(this).val() ? rechercherEnfantScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolaireEnfantCle_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "EnfantCle ").f().sx(htmEnfantCle()).g("span");
		}
	}

	public void htmEnfantCle(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireEnfantCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/enfant?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-orange w3-hover-orange ").f();
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
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
												.a("id", classeApiMethodeMethode, "_enfantCle_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postEnfantScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "enfantCle')); });")
												.f().sx("ajouter un enfant")
											.g("button");
										} g("div");
									}
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
	public void setMereCles(String o) {
		Long l = InscriptionScolaire.staticSetMereCles(requeteSite_, o);
		if(l != null)
			addMereCles(l);
		this.mereClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetMereCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setMereCles(JsonArray objets) {
		mereCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addMereCles(o);
		}
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

	public static Long staticSolrMereCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrMereCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMereCles(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrMereCles(requeteSite_, InscriptionScolaire.staticSolrMereCles(requeteSite_, InscriptionScolaire.staticSetMereCles(requeteSite_, o)));
	}

	public List<Long> solrMereCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : mereCles) {
			l.add(InscriptionScolaire.staticSolrMereCles(requeteSite_, o));
		}
		return l;
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
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_mereCles_vider")
						.a("class", "mereCles_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_mereCles_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "mères")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("class", "valeur suggereMereCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setMereCles")
				.a("id", classeApiMethodeMethode, "_mereCles")
				.a("autocomplete", "off");
				a("oninput", "suggereInscriptionScolaireMereCles($(this).val() ? rechercherMereScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolaireMereCles_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "MereCles ").f().sx(htmMereCles()).g("span");
		}
	}

	public void htmMereCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireMereCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/mere?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-pink w3-hover-pink ").f();
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
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
												.a("id", classeApiMethodeMethode, "_mereCles_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postMereScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "mereCles')); });")
												.f().sx("ajouter une mère")
											.g("button");
										} g("div");
									}
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
	public void setPereCles(String o) {
		Long l = InscriptionScolaire.staticSetPereCles(requeteSite_, o);
		if(l != null)
			addPereCles(l);
		this.pereClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetPereCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setPereCles(JsonArray objets) {
		pereCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPereCles(o);
		}
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

	public static Long staticSolrPereCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrPereCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPereCles(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPereCles(requeteSite_, InscriptionScolaire.staticSolrPereCles(requeteSite_, InscriptionScolaire.staticSetPereCles(requeteSite_, o)));
	}

	public List<Long> solrPereCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : pereCles) {
			l.add(InscriptionScolaire.staticSolrPereCles(requeteSite_, o));
		}
		return l;
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
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_pereCles_vider")
						.a("class", "pereCles_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_pereCles_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "pères")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("class", "valeur suggerePereCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setPereCles")
				.a("id", classeApiMethodeMethode, "_pereCles")
				.a("autocomplete", "off");
				a("oninput", "suggereInscriptionScolairePereCles($(this).val() ? rechercherPereScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolairePereCles_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "PereCles ").f().sx(htmPereCles()).g("span");
		}
	}

	public void htmPereCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolairePereCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/pere?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-light-blue w3-hover-light-blue ").f();
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
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
												.a("id", classeApiMethodeMethode, "_pereCles_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postPereScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "pereCles')); });")
												.f().sx("ajouter un père")
											.g("button");
										} g("div");
									}
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
	public void setGardienCles(String o) {
		Long l = InscriptionScolaire.staticSetGardienCles(requeteSite_, o);
		if(l != null)
			addGardienCles(l);
		this.gardienClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetGardienCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setGardienCles(JsonArray objets) {
		gardienCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGardienCles(o);
		}
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

	public static Long staticSolrGardienCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrGardienCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqGardienCles(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrGardienCles(requeteSite_, InscriptionScolaire.staticSolrGardienCles(requeteSite_, InscriptionScolaire.staticSetGardienCles(requeteSite_, o)));
	}

	public List<Long> solrGardienCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : gardienCles) {
			l.add(InscriptionScolaire.staticSolrGardienCles(requeteSite_, o));
		}
		return l;
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
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_gardienCles_vider")
						.a("class", "gardienCles_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_gardienCles_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "gardiens")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("class", "valeur suggereGardienCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setGardienCles")
				.a("id", classeApiMethodeMethode, "_gardienCles")
				.a("autocomplete", "off");
				a("oninput", "suggereInscriptionScolaireGardienCles($(this).val() ? rechercherGardienScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolaireGardienCles_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "GardienCles ").f().sx(htmGardienCles()).g("span");
		}
	}

	public void htmGardienCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireGardienCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/gardien?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
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
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
												.a("id", classeApiMethodeMethode, "_gardienCles_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postGardienScolaireVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "gardienCles')); });")
												.f().sx("ajouter un gardien")
											.g("button");
										} g("div");
									}
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
	public void setPaiementCles(String o) {
		Long l = InscriptionScolaire.staticSetPaiementCles(requeteSite_, o);
		if(l != null)
			addPaiementCles(l);
		this.paiementClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetPaiementCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setPaiementCles(JsonArray objets) {
		paiementCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPaiementCles(o);
		}
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

	public static Long staticSolrPaiementCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrPaiementCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementCles(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPaiementCles(requeteSite_, InscriptionScolaire.staticSolrPaiementCles(requeteSite_, InscriptionScolaire.staticSetPaiementCles(requeteSite_, o)));
	}

	public List<Long> solrPaiementCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : paiementCles) {
			l.add(InscriptionScolaire.staticSolrPaiementCles(requeteSite_, o));
		}
		return l;
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
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_paiementCles_vider")
						.a("class", "paiementCles_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_paiementCles_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "paiements")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("class", "valeur suggerePaiementCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setPaiementCles")
				.a("id", classeApiMethodeMethode, "_paiementCles")
				.a("autocomplete", "off");
				a("oninput", "suggereInscriptionScolairePaiementCles($(this).val() ? rechercherPaiementScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCle:" + pk + "'}", "], $('#listInscriptionScolairePaiementCles_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "PaiementCles ").f().sx(htmPaiementCles()).g("span");
		}
	}

	public void htmPaiementCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolairePaiementCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/paiement?fq=inscriptionCle:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
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
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
												.a("id", classeApiMethodeMethode, "_paiementCles_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postPaiementScolaireVals({ inscriptionCle: \"", pk, "\" }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "paiementCles')); });")
												.f().sx("ajouter un paiement")
											.g("button");
										} g("div");
									}
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
	public void setFormInscriptionCle(String o) {
		this.formInscriptionCle = InscriptionScolaire.staticSetFormInscriptionCle(requeteSite_, o);
		this.formInscriptionCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetFormInscriptionCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrFormInscriptionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrFormInscriptionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFormInscriptionCle(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFormInscriptionCle(requeteSite_, InscriptionScolaire.staticSolrFormInscriptionCle(requeteSite_, InscriptionScolaire.staticSetFormInscriptionCle(requeteSite_, o)));
	}

	public Long solrFormInscriptionCle() {
		return InscriptionScolaire.staticSolrFormInscriptionCle(requeteSite_, formInscriptionCle);
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
	public void setUtilisateurCles(String o) {
		Long l = InscriptionScolaire.staticSetUtilisateurCles(requeteSite_, o);
		if(l != null)
			addUtilisateurCles(l);
		this.utilisateurClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetUtilisateurCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setUtilisateurCles(JsonArray objets) {
		utilisateurCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUtilisateurCles(o);
		}
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

	public static Long staticSolrUtilisateurCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrUtilisateurCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUtilisateurCles(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrUtilisateurCles(requeteSite_, InscriptionScolaire.staticSolrUtilisateurCles(requeteSite_, InscriptionScolaire.staticSetUtilisateurCles(requeteSite_, o)));
	}

	public List<Long> solrUtilisateurCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : utilisateurCles) {
			l.add(InscriptionScolaire.staticSolrUtilisateurCles(requeteSite_, o));
		}
		return l;
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
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_utilisateurCles_vider")
						.a("class", "utilisateurCles_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_utilisateurCles_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "utilisateurs")
				.a("title", "La clé primaire des utilisateurs dans la base de données. ")
				.a("class", "valeur suggereUtilisateurCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setUtilisateurCles")
				.a("id", classeApiMethodeMethode, "_utilisateurCles")
				.a("autocomplete", "off");
				a("oninput", "suggereInscriptionScolaireUtilisateurCles($(this).val() ? rechercherUtilisateurSiteFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'inscriptionCles:" + pk + "'}", "], $('#listInscriptionScolaireUtilisateurCles_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "UtilisateurCles ").f().sx(htmUtilisateurCles()).g("span");
		}
	}

	public void htmUtilisateurCles(String classeApiMethodeMethode) {
		InscriptionScolaire s = (InscriptionScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "InscriptionScolaireUtilisateurCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/utilisateur?fq=inscriptionCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-gray w3-hover-gray ").f();
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
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
												.a("id", classeApiMethodeMethode, "_utilisateurCles_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postUtilisateurSiteVals({ inscriptionCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "utilisateurCles')); });")
												.f().sx("ajouter un utilisateur du site")
											.g("button");
										} g("div");
									}
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
	public void setScolaireTri(String o) {
		this.scolaireTri = InscriptionScolaire.staticSetScolaireTri(requeteSite_, o);
		this.scolaireTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetScolaireTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrScolaireTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrScolaireTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqScolaireTri(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrScolaireTri(requeteSite_, InscriptionScolaire.staticSolrScolaireTri(requeteSite_, InscriptionScolaire.staticSetScolaireTri(requeteSite_, o)));
	}

	public Integer solrScolaireTri() {
		return InscriptionScolaire.staticSolrScolaireTri(requeteSite_, scolaireTri);
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
	public void setEcoleTri(String o) {
		this.ecoleTri = InscriptionScolaire.staticSetEcoleTri(requeteSite_, o);
		this.ecoleTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetEcoleTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEcoleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrEcoleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleTri(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEcoleTri(requeteSite_, InscriptionScolaire.staticSolrEcoleTri(requeteSite_, InscriptionScolaire.staticSetEcoleTri(requeteSite_, o)));
	}

	public Integer solrEcoleTri() {
		return InscriptionScolaire.staticSolrEcoleTri(requeteSite_, ecoleTri);
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
	public void setAnneeTri(String o) {
		this.anneeTri = InscriptionScolaire.staticSetAnneeTri(requeteSite_, o);
		this.anneeTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAnneeTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAnneeTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAnneeTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeTri(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrAnneeTri(requeteSite_, InscriptionScolaire.staticSolrAnneeTri(requeteSite_, InscriptionScolaire.staticSetAnneeTri(requeteSite_, o)));
	}

	public Integer solrAnneeTri() {
		return InscriptionScolaire.staticSolrAnneeTri(requeteSite_, anneeTri);
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
	public void setSaisonTri(String o) {
		this.saisonTri = InscriptionScolaire.staticSetSaisonTri(requeteSite_, o);
		this.saisonTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetSaisonTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSaisonTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrSaisonTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonTri(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrSaisonTri(requeteSite_, InscriptionScolaire.staticSolrSaisonTri(requeteSite_, InscriptionScolaire.staticSetSaisonTri(requeteSite_, o)));
	}

	public Integer solrSaisonTri() {
		return InscriptionScolaire.staticSolrSaisonTri(requeteSite_, saisonTri);
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
	public void setSessionTri(String o) {
		this.sessionTri = InscriptionScolaire.staticSetSessionTri(requeteSite_, o);
		this.sessionTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetSessionTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrSessionTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrSessionTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionTri(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrSessionTri(requeteSite_, InscriptionScolaire.staticSolrSessionTri(requeteSite_, InscriptionScolaire.staticSetSessionTri(requeteSite_, o)));
	}

	public Integer solrSessionTri() {
		return InscriptionScolaire.staticSolrSessionTri(requeteSite_, sessionTri);
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
	public void setAgeTri(String o) {
		this.ageTri = InscriptionScolaire.staticSetAgeTri(requeteSite_, o);
		this.ageTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAgeTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAgeTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeTri(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrAgeTri(requeteSite_, InscriptionScolaire.staticSolrAgeTri(requeteSite_, InscriptionScolaire.staticSetAgeTri(requeteSite_, o)));
	}

	public Integer solrAgeTri() {
		return InscriptionScolaire.staticSolrAgeTri(requeteSite_, ageTri);
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
	public static ListeRecherche<EnfantScolaire> staticSetEnfantRecherche(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static EnfantScolaire staticSetEnfant_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static ListeRecherche<MereScolaire> staticSetMereRecherche(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static MereScolaire staticSetMeres(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static ListeRecherche<PereScolaire> staticSetPereRecherche(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static PereScolaire staticSetPeres(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static ListeRecherche<GardienScolaire> staticSetGardienRecherche(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static GardienScolaire staticSetGardiens(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static ListeRecherche<PaiementScolaire> staticSetFraisRecherche(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static ListeRecherche<PaiementScolaire> staticSetPaiementRecherche(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public void setEnfantPrenom(String o) {
		this.enfantPrenom = InscriptionScolaire.staticSetEnfantPrenom(requeteSite_, o);
		this.enfantPrenomCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantPrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantPrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantPrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantPrenom(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantPrenom(requeteSite_, InscriptionScolaire.staticSolrEnfantPrenom(requeteSite_, InscriptionScolaire.staticSetEnfantPrenom(requeteSite_, o)));
	}

	public String solrEnfantPrenom() {
		return InscriptionScolaire.staticSolrEnfantPrenom(requeteSite_, enfantPrenom);
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
	public void setEnfantPrenomPrefere(String o) {
		this.enfantPrenomPrefere = InscriptionScolaire.staticSetEnfantPrenomPrefere(requeteSite_, o);
		this.enfantPrenomPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantPrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantPrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantPrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantPrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantPrenomPrefere(requeteSite_, InscriptionScolaire.staticSolrEnfantPrenomPrefere(requeteSite_, InscriptionScolaire.staticSetEnfantPrenomPrefere(requeteSite_, o)));
	}

	public String solrEnfantPrenomPrefere() {
		return InscriptionScolaire.staticSolrEnfantPrenomPrefere(requeteSite_, enfantPrenomPrefere);
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
	public void setEnfantFamilleNom(String o) {
		this.enfantFamilleNom = InscriptionScolaire.staticSetEnfantFamilleNom(requeteSite_, o);
		this.enfantFamilleNomCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantFamilleNom(requeteSite_, InscriptionScolaire.staticSolrEnfantFamilleNom(requeteSite_, InscriptionScolaire.staticSetEnfantFamilleNom(requeteSite_, o)));
	}

	public String solrEnfantFamilleNom() {
		return InscriptionScolaire.staticSolrEnfantFamilleNom(requeteSite_, enfantFamilleNom);
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
	public void setMerePrenom(String o) {
		this.merePrenom = InscriptionScolaire.staticSetMerePrenom(requeteSite_, o);
		this.merePrenomCouverture.dejaInitialise = true;
	}
	public static String staticSetMerePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrMerePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrMerePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMerePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrMerePrenom(requeteSite_, InscriptionScolaire.staticSolrMerePrenom(requeteSite_, InscriptionScolaire.staticSetMerePrenom(requeteSite_, o)));
	}

	public String solrMerePrenom() {
		return InscriptionScolaire.staticSolrMerePrenom(requeteSite_, merePrenom);
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
	public void setMerePrenomPrefere(String o) {
		this.merePrenomPrefere = InscriptionScolaire.staticSetMerePrenomPrefere(requeteSite_, o);
		this.merePrenomPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetMerePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrMerePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrMerePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMerePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrMerePrenomPrefere(requeteSite_, InscriptionScolaire.staticSolrMerePrenomPrefere(requeteSite_, InscriptionScolaire.staticSetMerePrenomPrefere(requeteSite_, o)));
	}

	public String solrMerePrenomPrefere() {
		return InscriptionScolaire.staticSolrMerePrenomPrefere(requeteSite_, merePrenomPrefere);
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
	public void setMereNomCompletPrefere(String o) {
		this.mereNomCompletPrefere = InscriptionScolaire.staticSetMereNomCompletPrefere(requeteSite_, o);
		this.mereNomCompletPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetMereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrMereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrMereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrMereNomCompletPrefere(requeteSite_, InscriptionScolaire.staticSolrMereNomCompletPrefere(requeteSite_, InscriptionScolaire.staticSetMereNomCompletPrefere(requeteSite_, o)));
	}

	public String solrMereNomCompletPrefere() {
		return InscriptionScolaire.staticSolrMereNomCompletPrefere(requeteSite_, mereNomCompletPrefere);
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
	public void setPerePrenom(String o) {
		this.perePrenom = InscriptionScolaire.staticSetPerePrenom(requeteSite_, o);
		this.perePrenomCouverture.dejaInitialise = true;
	}
	public static String staticSetPerePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPerePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPerePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPerePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPerePrenom(requeteSite_, InscriptionScolaire.staticSolrPerePrenom(requeteSite_, InscriptionScolaire.staticSetPerePrenom(requeteSite_, o)));
	}

	public String solrPerePrenom() {
		return InscriptionScolaire.staticSolrPerePrenom(requeteSite_, perePrenom);
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
	public void setPerePrenomPrefere(String o) {
		this.perePrenomPrefere = InscriptionScolaire.staticSetPerePrenomPrefere(requeteSite_, o);
		this.perePrenomPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetPerePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPerePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPerePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPerePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPerePrenomPrefere(requeteSite_, InscriptionScolaire.staticSolrPerePrenomPrefere(requeteSite_, InscriptionScolaire.staticSetPerePrenomPrefere(requeteSite_, o)));
	}

	public String solrPerePrenomPrefere() {
		return InscriptionScolaire.staticSolrPerePrenomPrefere(requeteSite_, perePrenomPrefere);
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
	public void setPereNomCompletPrefere(String o) {
		this.pereNomCompletPrefere = InscriptionScolaire.staticSetPereNomCompletPrefere(requeteSite_, o);
		this.pereNomCompletPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetPereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPereNomCompletPrefere(requeteSite_, InscriptionScolaire.staticSolrPereNomCompletPrefere(requeteSite_, InscriptionScolaire.staticSetPereNomCompletPrefere(requeteSite_, o)));
	}

	public String solrPereNomCompletPrefere() {
		return InscriptionScolaire.staticSolrPereNomCompletPrefere(requeteSite_, pereNomCompletPrefere);
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
	public void setEnfantNomComplet(String o) {
		this.enfantNomComplet = InscriptionScolaire.staticSetEnfantNomComplet(requeteSite_, o);
		this.enfantNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantNomComplet(requeteSite_, InscriptionScolaire.staticSolrEnfantNomComplet(requeteSite_, InscriptionScolaire.staticSetEnfantNomComplet(requeteSite_, o)));
	}

	public String solrEnfantNomComplet() {
		return InscriptionScolaire.staticSolrEnfantNomComplet(requeteSite_, enfantNomComplet);
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
			e("span").a("class", "varInscriptionScolaire", pk, "EnfantNomComplet ").f().sx(htmEnfantNomComplet()).g("span");
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
	public void setEnfantNomCompletPrefere(String o) {
		this.enfantNomCompletPrefere = InscriptionScolaire.staticSetEnfantNomCompletPrefere(requeteSite_, o);
		this.enfantNomCompletPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantNomCompletPrefere(requeteSite_, InscriptionScolaire.staticSolrEnfantNomCompletPrefere(requeteSite_, InscriptionScolaire.staticSetEnfantNomCompletPrefere(requeteSite_, o)));
	}

	public String solrEnfantNomCompletPrefere() {
		return InscriptionScolaire.staticSolrEnfantNomCompletPrefere(requeteSite_, enfantNomCompletPrefere);
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
			e("span").a("class", "varInscriptionScolaire", pk, "EnfantNomCompletPrefere ").f().sx(htmEnfantNomCompletPrefere()).g("span");
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
	public void setEnfantDateNaissance(Instant o) {
		this.enfantDateNaissance = o == null ? null : LocalDate.from(o);
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setEnfantDateNaissance(String o) {
		this.enfantDateNaissance = InscriptionScolaire.staticSetEnfantDateNaissance(requeteSite_, o);
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetEnfantDateNaissance(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnfantDateNaissance(Date o) {
		this.enfantDateNaissance = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
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

	public static Date staticSolrEnfantDateNaissance(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnfantDateNaissance(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnfantDateNaissance(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantDateNaissance(requeteSite_, InscriptionScolaire.staticSolrEnfantDateNaissance(requeteSite_, InscriptionScolaire.staticSetEnfantDateNaissance(requeteSite_, o)));
	}

	public Date solrEnfantDateNaissance() {
		return InscriptionScolaire.staticSolrEnfantDateNaissance(requeteSite_, enfantDateNaissance);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", enfantDateNaissance == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(enfantDateNaissance));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantDateNaissance', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "EnfantDateNaissance ").f().sx(htmEnfantDateNaissance()).g("span");
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
	public void setEnfantDateNaissanceDAnnee(String o) {
		this.enfantDateNaissanceDAnnee = InscriptionScolaire.staticSetEnfantDateNaissanceDAnnee(requeteSite_, o);
		this.enfantDateNaissanceDAnneeCouverture.dejaInitialise = true;
	}
	public static Integer staticSetEnfantDateNaissanceDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEnfantDateNaissanceDAnnee(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrEnfantDateNaissanceDAnnee(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantDateNaissanceDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantDateNaissanceDAnnee(requeteSite_, InscriptionScolaire.staticSolrEnfantDateNaissanceDAnnee(requeteSite_, InscriptionScolaire.staticSetEnfantDateNaissanceDAnnee(requeteSite_, o)));
	}

	public Integer solrEnfantDateNaissanceDAnnee() {
		return InscriptionScolaire.staticSolrEnfantDateNaissanceDAnnee(requeteSite_, enfantDateNaissanceDAnnee);
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
	public void setEnfantDateNaissanceMoisDAnnee(String o) {
		this.enfantDateNaissanceMoisDAnnee = InscriptionScolaire.staticSetEnfantDateNaissanceMoisDAnnee(requeteSite_, o);
		this.enfantDateNaissanceMoisDAnneeCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantDateNaissanceMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantDateNaissanceMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantDateNaissanceMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantDateNaissanceMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantDateNaissanceMoisDAnnee(requeteSite_, InscriptionScolaire.staticSolrEnfantDateNaissanceMoisDAnnee(requeteSite_, InscriptionScolaire.staticSetEnfantDateNaissanceMoisDAnnee(requeteSite_, o)));
	}

	public String solrEnfantDateNaissanceMoisDAnnee() {
		return InscriptionScolaire.staticSolrEnfantDateNaissanceMoisDAnnee(requeteSite_, enfantDateNaissanceMoisDAnnee);
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
	public void setEnfantDateNaissanceJourDeSemaine(String o) {
		this.enfantDateNaissanceJourDeSemaine = InscriptionScolaire.staticSetEnfantDateNaissanceJourDeSemaine(requeteSite_, o);
		this.enfantDateNaissanceJourDeSemaineCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantDateNaissanceJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantDateNaissanceJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantDateNaissanceJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantDateNaissanceJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantDateNaissanceJourDeSemaine(requeteSite_, InscriptionScolaire.staticSolrEnfantDateNaissanceJourDeSemaine(requeteSite_, InscriptionScolaire.staticSetEnfantDateNaissanceJourDeSemaine(requeteSite_, o)));
	}

	public String solrEnfantDateNaissanceJourDeSemaine() {
		return InscriptionScolaire.staticSolrEnfantDateNaissanceJourDeSemaine(requeteSite_, enfantDateNaissanceJourDeSemaine);
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
	public void setEnfantMoisNaissance(String o) {
		this.enfantMoisNaissance = InscriptionScolaire.staticSetEnfantMoisNaissance(requeteSite_, o);
		this.enfantMoisNaissanceCouverture.dejaInitialise = true;
	}
	public static Integer staticSetEnfantMoisNaissance(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEnfantMoisNaissance(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrEnfantMoisNaissance(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantMoisNaissance(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantMoisNaissance(requeteSite_, InscriptionScolaire.staticSolrEnfantMoisNaissance(requeteSite_, InscriptionScolaire.staticSetEnfantMoisNaissance(requeteSite_, o)));
	}

	public Integer solrEnfantMoisNaissance() {
		return InscriptionScolaire.staticSolrEnfantMoisNaissance(requeteSite_, enfantMoisNaissance);
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
	public void setEnfantJourNaissance(String o) {
		this.enfantJourNaissance = InscriptionScolaire.staticSetEnfantJourNaissance(requeteSite_, o);
		this.enfantJourNaissanceCouverture.dejaInitialise = true;
	}
	public static Integer staticSetEnfantJourNaissance(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEnfantJourNaissance(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrEnfantJourNaissance(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantJourNaissance(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantJourNaissance(requeteSite_, InscriptionScolaire.staticSolrEnfantJourNaissance(requeteSite_, InscriptionScolaire.staticSetEnfantJourNaissance(requeteSite_, o)));
	}

	public Integer solrEnfantJourNaissance() {
		return InscriptionScolaire.staticSolrEnfantJourNaissance(requeteSite_, enfantJourNaissance);
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
	public void setEcoleNom(String o) {
		this.ecoleNom = InscriptionScolaire.staticSetEcoleNom(requeteSite_, o);
		this.ecoleNomCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEcoleNom(requeteSite_, InscriptionScolaire.staticSolrEcoleNom(requeteSite_, InscriptionScolaire.staticSetEcoleNom(requeteSite_, o)));
	}

	public String solrEcoleNom() {
		return InscriptionScolaire.staticSolrEcoleNom(requeteSite_, ecoleNom);
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
	public void setEcoleNomComplet(String o) {
		this.ecoleNomComplet = InscriptionScolaire.staticSetEcoleNomComplet(requeteSite_, o);
		this.ecoleNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEcoleNomComplet(requeteSite_, InscriptionScolaire.staticSolrEcoleNomComplet(requeteSite_, InscriptionScolaire.staticSetEcoleNomComplet(requeteSite_, o)));
	}

	public String solrEcoleNomComplet() {
		return InscriptionScolaire.staticSolrEcoleNomComplet(requeteSite_, ecoleNomComplet);
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
	public void setEcoleEmplacement(String o) {
		this.ecoleEmplacement = InscriptionScolaire.staticSetEcoleEmplacement(requeteSite_, o);
		this.ecoleEmplacementCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEcoleEmplacement(requeteSite_, InscriptionScolaire.staticSolrEcoleEmplacement(requeteSite_, InscriptionScolaire.staticSetEcoleEmplacement(requeteSite_, o)));
	}

	public String solrEcoleEmplacement() {
		return InscriptionScolaire.staticSolrEcoleEmplacement(requeteSite_, ecoleEmplacement);
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
	public void setEcoleAddresse(String o) {
		this.ecoleAddresse = InscriptionScolaire.staticSetEcoleAddresse(requeteSite_, o);
		this.ecoleAddresseCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEcoleAddresse(requeteSite_, InscriptionScolaire.staticSolrEcoleAddresse(requeteSite_, InscriptionScolaire.staticSetEcoleAddresse(requeteSite_, o)));
	}

	public String solrEcoleAddresse() {
		return InscriptionScolaire.staticSolrEcoleAddresse(requeteSite_, ecoleAddresse);
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
			e("span").a("class", "varInscriptionScolaire", pk, "EcoleAddresse ").f().sx(htmEcoleAddresse()).g("span");
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
	public void setEcoleNumeroTelephone(String o) {
		this.ecoleNumeroTelephone = InscriptionScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o);
		this.ecoleNumeroTelephoneCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEcoleNumeroTelephone(requeteSite_, InscriptionScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, InscriptionScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o)));
	}

	public String solrEcoleNumeroTelephone() {
		return InscriptionScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, ecoleNumeroTelephone);
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
	public void setEcoleForm(String o) {
		this.ecoleForm = InscriptionScolaire.staticSetEcoleForm(requeteSite_, o);
		this.ecoleFormCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEcoleForm(requeteSite_, InscriptionScolaire.staticSolrEcoleForm(requeteSite_, InscriptionScolaire.staticSetEcoleForm(requeteSite_, o)));
	}

	public String solrEcoleForm() {
		return InscriptionScolaire.staticSolrEcoleForm(requeteSite_, ecoleForm);
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
	public void setEcoleNumero(String o) {
		this.ecoleNumero = InscriptionScolaire.staticSetEcoleNumero(requeteSite_, o);
		this.ecoleNumeroCouverture.dejaInitialise = true;
	}
	public static Integer staticSetEcoleNumero(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEcoleNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrEcoleNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNumero(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEcoleNumero(requeteSite_, InscriptionScolaire.staticSolrEcoleNumero(requeteSite_, InscriptionScolaire.staticSetEcoleNumero(requeteSite_, o)));
	}

	public Integer solrEcoleNumero() {
		return InscriptionScolaire.staticSolrEcoleNumero(requeteSite_, ecoleNumero);
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
	public void setEcoleAdministrateurNom(String o) {
		this.ecoleAdministrateurNom = InscriptionScolaire.staticSetEcoleAdministrateurNom(requeteSite_, o);
		this.ecoleAdministrateurNomCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEcoleAdministrateurNom(requeteSite_, InscriptionScolaire.staticSolrEcoleAdministrateurNom(requeteSite_, InscriptionScolaire.staticSetEcoleAdministrateurNom(requeteSite_, o)));
	}

	public String solrEcoleAdministrateurNom() {
		return InscriptionScolaire.staticSolrEcoleAdministrateurNom(requeteSite_, ecoleAdministrateurNom);
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
	public void setAnneeDebut(String o) {
		this.anneeDebut = InscriptionScolaire.staticSetAnneeDebut(requeteSite_, o);
		this.anneeDebutCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAnneeDebut(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAnneeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAnneeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeDebut(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrAnneeDebut(requeteSite_, InscriptionScolaire.staticSolrAnneeDebut(requeteSite_, InscriptionScolaire.staticSetAnneeDebut(requeteSite_, o)));
	}

	public Integer solrAnneeDebut() {
		return InscriptionScolaire.staticSolrAnneeDebut(requeteSite_, anneeDebut);
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
	public void setAnneeFin(String o) {
		this.anneeFin = InscriptionScolaire.staticSetAnneeFin(requeteSite_, o);
		this.anneeFinCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAnneeFin(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAnneeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAnneeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeFin(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrAnneeFin(requeteSite_, InscriptionScolaire.staticSolrAnneeFin(requeteSite_, InscriptionScolaire.staticSetAnneeFin(requeteSite_, o)));
	}

	public Integer solrAnneeFin() {
		return InscriptionScolaire.staticSolrAnneeFin(requeteSite_, anneeFin);
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
	public void setSaisonDateDebut(Instant o) {
		this.saisonDateDebut = o == null ? null : LocalDate.from(o);
		this.saisonDateDebutCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSaisonDateDebut(String o) {
		this.saisonDateDebut = InscriptionScolaire.staticSetSaisonDateDebut(requeteSite_, o);
		this.saisonDateDebutCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetSaisonDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSaisonDateDebut(Date o) {
		this.saisonDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.saisonDateDebutCouverture.dejaInitialise = true;
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

	public static Date staticSolrSaisonDateDebut(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSaisonDateDebut(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSaisonDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrSaisonDateDebut(requeteSite_, InscriptionScolaire.staticSolrSaisonDateDebut(requeteSite_, InscriptionScolaire.staticSetSaisonDateDebut(requeteSite_, o)));
	}

	public Date solrSaisonDateDebut() {
		return InscriptionScolaire.staticSolrSaisonDateDebut(requeteSite_, saisonDateDebut);
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
	public void setAnneeFraisInscription(String o) {
		this.anneeFraisInscription = InscriptionScolaire.staticSetAnneeFraisInscription(requeteSite_, o);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetAnneeFraisInscription(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setAnneeFraisInscription(Double o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
	}
	public void setAnneeFraisInscription(Integer o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
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

	public static Double staticSolrAnneeFraisInscription(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrAnneeFraisInscription(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeFraisInscription(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrAnneeFraisInscription(requeteSite_, InscriptionScolaire.staticSolrAnneeFraisInscription(requeteSite_, InscriptionScolaire.staticSetAnneeFraisInscription(requeteSite_, o)));
	}

	public Double solrAnneeFraisInscription() {
		return InscriptionScolaire.staticSolrAnneeFraisInscription(requeteSite_, anneeFraisInscription);
	}

	public String strAnneeFraisInscription() {
		return anneeFraisInscription == null ? "" : anneeFraisInscription.setScale(2, RoundingMode.HALF_UP).toString();
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
	public void setSessionDateDebut(Instant o) {
		this.sessionDateDebut = o == null ? null : LocalDate.from(o);
		this.sessionDateDebutCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSessionDateDebut(String o) {
		this.sessionDateDebut = InscriptionScolaire.staticSetSessionDateDebut(requeteSite_, o);
		this.sessionDateDebutCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetSessionDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionDateDebut(Date o) {
		this.sessionDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateDebutCouverture.dejaInitialise = true;
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

	public static Date staticSolrSessionDateDebut(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionDateDebut(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrSessionDateDebut(requeteSite_, InscriptionScolaire.staticSolrSessionDateDebut(requeteSite_, InscriptionScolaire.staticSetSessionDateDebut(requeteSite_, o)));
	}

	public Date solrSessionDateDebut() {
		return InscriptionScolaire.staticSolrSessionDateDebut(requeteSite_, sessionDateDebut);
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
	public void setSessionDateFin(Instant o) {
		this.sessionDateFin = o == null ? null : LocalDate.from(o);
		this.sessionDateFinCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSessionDateFin(String o) {
		this.sessionDateFin = InscriptionScolaire.staticSetSessionDateFin(requeteSite_, o);
		this.sessionDateFinCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetSessionDateFin(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionDateFin(Date o) {
		this.sessionDateFin = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateFinCouverture.dejaInitialise = true;
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

	public static Date staticSolrSessionDateFin(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionDateFin(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionDateFin(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrSessionDateFin(requeteSite_, InscriptionScolaire.staticSolrSessionDateFin(requeteSite_, InscriptionScolaire.staticSetSessionDateFin(requeteSite_, o)));
	}

	public Date solrSessionDateFin() {
		return InscriptionScolaire.staticSolrSessionDateFin(requeteSite_, sessionDateFin);
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
	public void setAgeNomComplet(String o) {
		this.ageNomComplet = InscriptionScolaire.staticSetAgeNomComplet(requeteSite_, o);
		this.ageNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetAgeNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrAgeNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrAgeNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrAgeNomComplet(requeteSite_, InscriptionScolaire.staticSolrAgeNomComplet(requeteSite_, InscriptionScolaire.staticSetAgeNomComplet(requeteSite_, o)));
	}

	public String solrAgeNomComplet() {
		return InscriptionScolaire.staticSolrAgeNomComplet(requeteSite_, ageNomComplet);
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
	public void setAgeDebut(String o) {
		this.ageDebut = InscriptionScolaire.staticSetAgeDebut(requeteSite_, o);
		this.ageDebutCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAgeDebut(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAgeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeDebut(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrAgeDebut(requeteSite_, InscriptionScolaire.staticSolrAgeDebut(requeteSite_, InscriptionScolaire.staticSetAgeDebut(requeteSite_, o)));
	}

	public Integer solrAgeDebut() {
		return InscriptionScolaire.staticSolrAgeDebut(requeteSite_, ageDebut);
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
	public void setAgeFin(String o) {
		this.ageFin = InscriptionScolaire.staticSetAgeFin(requeteSite_, o);
		this.ageFinCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAgeFin(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAgeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeFin(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrAgeFin(requeteSite_, InscriptionScolaire.staticSolrAgeFin(requeteSite_, InscriptionScolaire.staticSetAgeFin(requeteSite_, o)));
	}

	public Integer solrAgeFin() {
		return InscriptionScolaire.staticSolrAgeFin(requeteSite_, ageFin);
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
	public void setBlocHeureDebut(String o) {
		this.blocHeureDebut = InscriptionScolaire.staticSetBlocHeureDebut(requeteSite_, o);
		this.blocHeureDebutCouverture.dejaInitialise = true;
	}
	public static LocalTime staticSetBlocHeureDebut(RequeteSiteFrFR requeteSite_, String o) {
		try {
			return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
		} catch(Exception e) {
		}
		return null;
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

	public static String staticSolrBlocHeureDebut(RequeteSiteFrFR requeteSite_, LocalTime o) {
		return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static String staticSolrStrBlocHeureDebut(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocHeureDebut(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocHeureDebut(requeteSite_, InscriptionScolaire.staticSolrBlocHeureDebut(requeteSite_, InscriptionScolaire.staticSetBlocHeureDebut(requeteSite_, o)));
	}

	public String solrBlocHeureDebut() {
		return InscriptionScolaire.staticSolrBlocHeureDebut(requeteSite_, blocHeureDebut);
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
	public void setBlocHeureFin(String o) {
		this.blocHeureFin = InscriptionScolaire.staticSetBlocHeureFin(requeteSite_, o);
		this.blocHeureFinCouverture.dejaInitialise = true;
	}
	public static LocalTime staticSetBlocHeureFin(RequeteSiteFrFR requeteSite_, String o) {
		try {
			return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
		} catch(Exception e) {
		}
		return null;
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

	public static String staticSolrBlocHeureFin(RequeteSiteFrFR requeteSite_, LocalTime o) {
		return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static String staticSolrStrBlocHeureFin(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocHeureFin(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocHeureFin(requeteSite_, InscriptionScolaire.staticSolrBlocHeureFin(requeteSite_, InscriptionScolaire.staticSetBlocHeureFin(requeteSite_, o)));
	}

	public String solrBlocHeureFin() {
		return InscriptionScolaire.staticSolrBlocHeureFin(requeteSite_, blocHeureFin);
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
	public void setBlocPrixParMois(String o) {
		this.blocPrixParMois = InscriptionScolaire.staticSetBlocPrixParMois(requeteSite_, o);
		this.blocPrixParMoisCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetBlocPrixParMois(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setBlocPrixParMois(Double o) {
			this.blocPrixParMois = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blocPrixParMoisCouverture.dejaInitialise = true;
	}
	public void setBlocPrixParMois(Integer o) {
			this.blocPrixParMois = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blocPrixParMoisCouverture.dejaInitialise = true;
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

	public static Double staticSolrBlocPrixParMois(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrBlocPrixParMois(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocPrixParMois(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocPrixParMois(requeteSite_, InscriptionScolaire.staticSolrBlocPrixParMois(requeteSite_, InscriptionScolaire.staticSetBlocPrixParMois(requeteSite_, o)));
	}

	public Double solrBlocPrixParMois() {
		return InscriptionScolaire.staticSolrBlocPrixParMois(requeteSite_, blocPrixParMois);
	}

	public String strBlocPrixParMois() {
		return blocPrixParMois == null ? "" : blocPrixParMois.setScale(2, RoundingMode.HALF_UP).toString();
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
	public void setBlocDimanche(String o) {
		this.blocDimanche = InscriptionScolaire.staticSetBlocDimanche(requeteSite_, o);
		this.blocDimancheCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetBlocDimanche(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlocDimanche(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlocDimanche(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocDimanche(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocDimanche(requeteSite_, InscriptionScolaire.staticSolrBlocDimanche(requeteSite_, InscriptionScolaire.staticSetBlocDimanche(requeteSite_, o)));
	}

	public Boolean solrBlocDimanche() {
		return InscriptionScolaire.staticSolrBlocDimanche(requeteSite_, blocDimanche);
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
	public void setBlocLundi(String o) {
		this.blocLundi = InscriptionScolaire.staticSetBlocLundi(requeteSite_, o);
		this.blocLundiCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetBlocLundi(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlocLundi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlocLundi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocLundi(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocLundi(requeteSite_, InscriptionScolaire.staticSolrBlocLundi(requeteSite_, InscriptionScolaire.staticSetBlocLundi(requeteSite_, o)));
	}

	public Boolean solrBlocLundi() {
		return InscriptionScolaire.staticSolrBlocLundi(requeteSite_, blocLundi);
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
	public void setBlocMardi(String o) {
		this.blocMardi = InscriptionScolaire.staticSetBlocMardi(requeteSite_, o);
		this.blocMardiCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetBlocMardi(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlocMardi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlocMardi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocMardi(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocMardi(requeteSite_, InscriptionScolaire.staticSolrBlocMardi(requeteSite_, InscriptionScolaire.staticSetBlocMardi(requeteSite_, o)));
	}

	public Boolean solrBlocMardi() {
		return InscriptionScolaire.staticSolrBlocMardi(requeteSite_, blocMardi);
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
	public void setBlocMercredi(String o) {
		this.blocMercredi = InscriptionScolaire.staticSetBlocMercredi(requeteSite_, o);
		this.blocMercrediCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetBlocMercredi(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlocMercredi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlocMercredi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocMercredi(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocMercredi(requeteSite_, InscriptionScolaire.staticSolrBlocMercredi(requeteSite_, InscriptionScolaire.staticSetBlocMercredi(requeteSite_, o)));
	}

	public Boolean solrBlocMercredi() {
		return InscriptionScolaire.staticSolrBlocMercredi(requeteSite_, blocMercredi);
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
	public void setBlocJeudi(String o) {
		this.blocJeudi = InscriptionScolaire.staticSetBlocJeudi(requeteSite_, o);
		this.blocJeudiCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetBlocJeudi(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlocJeudi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlocJeudi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocJeudi(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocJeudi(requeteSite_, InscriptionScolaire.staticSolrBlocJeudi(requeteSite_, InscriptionScolaire.staticSetBlocJeudi(requeteSite_, o)));
	}

	public Boolean solrBlocJeudi() {
		return InscriptionScolaire.staticSolrBlocJeudi(requeteSite_, blocJeudi);
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
	public void setBlocVendredi(String o) {
		this.blocVendredi = InscriptionScolaire.staticSetBlocVendredi(requeteSite_, o);
		this.blocVendrediCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetBlocVendredi(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlocVendredi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlocVendredi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocVendredi(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocVendredi(requeteSite_, InscriptionScolaire.staticSolrBlocVendredi(requeteSite_, InscriptionScolaire.staticSetBlocVendredi(requeteSite_, o)));
	}

	public Boolean solrBlocVendredi() {
		return InscriptionScolaire.staticSolrBlocVendredi(requeteSite_, blocVendredi);
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
	public void setBlocSamedi(String o) {
		this.blocSamedi = InscriptionScolaire.staticSetBlocSamedi(requeteSite_, o);
		this.blocSamediCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetBlocSamedi(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrBlocSamedi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrBlocSamedi(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocSamedi(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocSamedi(requeteSite_, InscriptionScolaire.staticSolrBlocSamedi(requeteSite_, InscriptionScolaire.staticSetBlocSamedi(requeteSite_, o)));
	}

	public Boolean solrBlocSamedi() {
		return InscriptionScolaire.staticSolrBlocSamedi(requeteSite_, blocSamedi);
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
	public void setBlocPrixTotal(String o) {
		this.blocPrixTotal = InscriptionScolaire.staticSetBlocPrixTotal(requeteSite_, o);
		this.blocPrixTotalCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetBlocPrixTotal(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setBlocPrixTotal(Double o) {
			this.blocPrixTotal = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blocPrixTotalCouverture.dejaInitialise = true;
	}
	public void setBlocPrixTotal(Integer o) {
			this.blocPrixTotal = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.blocPrixTotalCouverture.dejaInitialise = true;
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

	public static Double staticSolrBlocPrixTotal(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrBlocPrixTotal(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocPrixTotal(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocPrixTotal(requeteSite_, InscriptionScolaire.staticSolrBlocPrixTotal(requeteSite_, InscriptionScolaire.staticSetBlocPrixTotal(requeteSite_, o)));
	}

	public Double solrBlocPrixTotal() {
		return InscriptionScolaire.staticSolrBlocPrixTotal(requeteSite_, blocPrixTotal);
	}

	public String strBlocPrixTotal() {
		return blocPrixTotal == null ? "" : blocPrixTotal.setScale(2, RoundingMode.HALF_UP).toString();
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
	public void setBlocNomAdmin(String o) {
		this.blocNomAdmin = InscriptionScolaire.staticSetBlocNomAdmin(requeteSite_, o);
		this.blocNomAdminCouverture.dejaInitialise = true;
	}
	public static String staticSetBlocNomAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrBlocNomAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrBlocNomAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocNomAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocNomAdmin(requeteSite_, InscriptionScolaire.staticSolrBlocNomAdmin(requeteSite_, InscriptionScolaire.staticSetBlocNomAdmin(requeteSite_, o)));
	}

	public String solrBlocNomAdmin() {
		return InscriptionScolaire.staticSolrBlocNomAdmin(requeteSite_, blocNomAdmin);
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
	public void setBlocNomCourt(String o) {
		this.blocNomCourt = InscriptionScolaire.staticSetBlocNomCourt(requeteSite_, o);
		this.blocNomCourtCouverture.dejaInitialise = true;
	}
	public static String staticSetBlocNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrBlocNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrBlocNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocNomCourt(requeteSite_, InscriptionScolaire.staticSolrBlocNomCourt(requeteSite_, InscriptionScolaire.staticSetBlocNomCourt(requeteSite_, o)));
	}

	public String solrBlocNomCourt() {
		return InscriptionScolaire.staticSolrBlocNomCourt(requeteSite_, blocNomCourt);
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
	public void setBlocNomComplet(String o) {
		this.blocNomComplet = InscriptionScolaire.staticSetBlocNomComplet(requeteSite_, o);
		this.blocNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetBlocNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrBlocNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrBlocNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrBlocNomComplet(requeteSite_, InscriptionScolaire.staticSolrBlocNomComplet(requeteSite_, InscriptionScolaire.staticSetBlocNomComplet(requeteSite_, o)));
	}

	public String solrBlocNomComplet() {
		return InscriptionScolaire.staticSolrBlocNomComplet(requeteSite_, blocNomComplet);
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
	public void setInscriptionApprouve(String o) {
		this.inscriptionApprouve = InscriptionScolaire.staticSetInscriptionApprouve(requeteSite_, o);
		this.inscriptionApprouveCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetInscriptionApprouve(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrInscriptionApprouve(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrInscriptionApprouve(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionApprouve(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionApprouve(requeteSite_, InscriptionScolaire.staticSolrInscriptionApprouve(requeteSite_, InscriptionScolaire.staticSetInscriptionApprouve(requeteSite_, o)));
	}

	public Boolean solrInscriptionApprouve() {
		return InscriptionScolaire.staticSolrInscriptionApprouve(requeteSite_, inscriptionApprouve);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionApprouve ").f().sx(htmInscriptionApprouve()).g("span");
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
	public void setInscriptionImmunisations(String o) {
		this.inscriptionImmunisations = InscriptionScolaire.staticSetInscriptionImmunisations(requeteSite_, o);
		this.inscriptionImmunisationsCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetInscriptionImmunisations(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrInscriptionImmunisations(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrInscriptionImmunisations(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionImmunisations(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionImmunisations(requeteSite_, InscriptionScolaire.staticSolrInscriptionImmunisations(requeteSite_, InscriptionScolaire.staticSetInscriptionImmunisations(requeteSite_, o)));
	}

	public Boolean solrInscriptionImmunisations() {
		return InscriptionScolaire.staticSolrInscriptionImmunisations(requeteSite_, inscriptionImmunisations);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionImmunisations ").f().sx(htmInscriptionImmunisations()).g("span");
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
	public void setPhoto(String o) {
		this.photo = InscriptionScolaire.staticSetPhoto(requeteSite_, o);
		this.photoCouverture.dejaInitialise = true;
	}
	public static String staticSetPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPhoto(requeteSite_, InscriptionScolaire.staticSolrPhoto(requeteSite_, InscriptionScolaire.staticSetPhoto(requeteSite_, o)));
	}

	public String solrPhoto() {
		return InscriptionScolaire.staticSolrPhoto(requeteSite_, photo);
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
			e("span").a("class", "varInscriptionScolaire", pk, "Photo ").f().sx(htmPhoto()).g("span");
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
	public void setFamilleMarie(String o) {
		this.familleMarie = InscriptionScolaire.staticSetFamilleMarie(requeteSite_, o);
		this.familleMarieCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetFamilleMarie(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrFamilleMarie(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrFamilleMarie(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilleMarie(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFamilleMarie(requeteSite_, InscriptionScolaire.staticSolrFamilleMarie(requeteSite_, InscriptionScolaire.staticSetFamilleMarie(requeteSite_, o)));
	}

	public Boolean solrFamilleMarie() {
		return InscriptionScolaire.staticSolrFamilleMarie(requeteSite_, familleMarie);
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
			e("span").a("class", "varInscriptionScolaire", pk, "FamilleMarie ").f().sx(htmFamilleMarie()).g("span");
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
	public void setFamilleSepare(String o) {
		this.familleSepare = InscriptionScolaire.staticSetFamilleSepare(requeteSite_, o);
		this.familleSepareCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetFamilleSepare(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrFamilleSepare(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrFamilleSepare(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilleSepare(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFamilleSepare(requeteSite_, InscriptionScolaire.staticSolrFamilleSepare(requeteSite_, InscriptionScolaire.staticSetFamilleSepare(requeteSite_, o)));
	}

	public Boolean solrFamilleSepare() {
		return InscriptionScolaire.staticSolrFamilleSepare(requeteSite_, familleSepare);
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
			e("span").a("class", "varInscriptionScolaire", pk, "FamilleSepare ").f().sx(htmFamilleSepare()).g("span");
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
	public void setFamilleDivorce(String o) {
		this.familleDivorce = InscriptionScolaire.staticSetFamilleDivorce(requeteSite_, o);
		this.familleDivorceCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetFamilleDivorce(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrFamilleDivorce(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrFamilleDivorce(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilleDivorce(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFamilleDivorce(requeteSite_, InscriptionScolaire.staticSolrFamilleDivorce(requeteSite_, InscriptionScolaire.staticSetFamilleDivorce(requeteSite_, o)));
	}

	public Boolean solrFamilleDivorce() {
		return InscriptionScolaire.staticSolrFamilleDivorce(requeteSite_, familleDivorce);
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
			e("span").a("class", "varInscriptionScolaire", pk, "FamilleDivorce ").f().sx(htmFamilleDivorce()).g("span");
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
	public void setFamilleAddresse(String o) {
		this.familleAddresse = InscriptionScolaire.staticSetFamilleAddresse(requeteSite_, o);
		this.familleAddresseCouverture.dejaInitialise = true;
	}
	public static String staticSetFamilleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrFamilleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrFamilleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFamilleAddresse(requeteSite_, InscriptionScolaire.staticSolrFamilleAddresse(requeteSite_, InscriptionScolaire.staticSetFamilleAddresse(requeteSite_, o)));
	}

	public String solrFamilleAddresse() {
		return InscriptionScolaire.staticSolrFamilleAddresse(requeteSite_, familleAddresse);
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
			e("span").a("class", "varInscriptionScolaire", pk, "FamilleAddresse ").f().sx(htmFamilleAddresse()).g("span");
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
	public void setFamilleCommentVousConnaissezEcole(String o) {
		this.familleCommentVousConnaissezEcole = InscriptionScolaire.staticSetFamilleCommentVousConnaissezEcole(requeteSite_, o);
		this.familleCommentVousConnaissezEcoleCouverture.dejaInitialise = true;
	}
	public static String staticSetFamilleCommentVousConnaissezEcole(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrFamilleCommentVousConnaissezEcole(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrFamilleCommentVousConnaissezEcole(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilleCommentVousConnaissezEcole(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFamilleCommentVousConnaissezEcole(requeteSite_, InscriptionScolaire.staticSolrFamilleCommentVousConnaissezEcole(requeteSite_, InscriptionScolaire.staticSetFamilleCommentVousConnaissezEcole(requeteSite_, o)));
	}

	public String solrFamilleCommentVousConnaissezEcole() {
		return InscriptionScolaire.staticSolrFamilleCommentVousConnaissezEcole(requeteSite_, familleCommentVousConnaissezEcole);
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
			e("span").a("class", "varInscriptionScolaire", pk, "FamilleCommentVousConnaissezEcole ").f().sx(htmFamilleCommentVousConnaissezEcole()).g("span");
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
	public void setInscriptionConsiderationsSpeciales(String o) {
		this.inscriptionConsiderationsSpeciales = InscriptionScolaire.staticSetInscriptionConsiderationsSpeciales(requeteSite_, o);
		this.inscriptionConsiderationsSpecialesCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionConsiderationsSpeciales(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionConsiderationsSpeciales(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionConsiderationsSpeciales(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionConsiderationsSpeciales(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionConsiderationsSpeciales(requeteSite_, InscriptionScolaire.staticSolrInscriptionConsiderationsSpeciales(requeteSite_, InscriptionScolaire.staticSetInscriptionConsiderationsSpeciales(requeteSite_, o)));
	}

	public String solrInscriptionConsiderationsSpeciales() {
		return InscriptionScolaire.staticSolrInscriptionConsiderationsSpeciales(requeteSite_, inscriptionConsiderationsSpeciales);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionConsiderationsSpeciales ").f().sx(htmInscriptionConsiderationsSpeciales()).g("span");
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
	public void setEnfantConditionsMedicales(String o) {
		this.enfantConditionsMedicales = InscriptionScolaire.staticSetEnfantConditionsMedicales(requeteSite_, o);
		this.enfantConditionsMedicalesCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantConditionsMedicales(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantConditionsMedicales(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantConditionsMedicales(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantConditionsMedicales(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantConditionsMedicales(requeteSite_, InscriptionScolaire.staticSolrEnfantConditionsMedicales(requeteSite_, InscriptionScolaire.staticSetEnfantConditionsMedicales(requeteSite_, o)));
	}

	public String solrEnfantConditionsMedicales() {
		return InscriptionScolaire.staticSolrEnfantConditionsMedicales(requeteSite_, enfantConditionsMedicales);
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
			e("span").a("class", "varInscriptionScolaire", pk, "EnfantConditionsMedicales ").f().sx(htmEnfantConditionsMedicales()).g("span");
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
	public void setEnfantEcolesPrecedemmentFrequentees(String o) {
		this.enfantEcolesPrecedemmentFrequentees = InscriptionScolaire.staticSetEnfantEcolesPrecedemmentFrequentees(requeteSite_, o);
		this.enfantEcolesPrecedemmentFrequenteesCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantEcolesPrecedemmentFrequentees(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantEcolesPrecedemmentFrequentees(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantEcolesPrecedemmentFrequentees(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantEcolesPrecedemmentFrequentees(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantEcolesPrecedemmentFrequentees(requeteSite_, InscriptionScolaire.staticSolrEnfantEcolesPrecedemmentFrequentees(requeteSite_, InscriptionScolaire.staticSetEnfantEcolesPrecedemmentFrequentees(requeteSite_, o)));
	}

	public String solrEnfantEcolesPrecedemmentFrequentees() {
		return InscriptionScolaire.staticSolrEnfantEcolesPrecedemmentFrequentees(requeteSite_, enfantEcolesPrecedemmentFrequentees);
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
			e("span").a("class", "varInscriptionScolaire", pk, "EnfantEcolesPrecedemmentFrequentees ").f().sx(htmEnfantEcolesPrecedemmentFrequentees()).g("span");
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
	public void setEnfantDescription(String o) {
		this.enfantDescription = InscriptionScolaire.staticSetEnfantDescription(requeteSite_, o);
		this.enfantDescriptionCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantDescription(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantDescription(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantDescription(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantDescription(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantDescription(requeteSite_, InscriptionScolaire.staticSolrEnfantDescription(requeteSite_, InscriptionScolaire.staticSetEnfantDescription(requeteSite_, o)));
	}

	public String solrEnfantDescription() {
		return InscriptionScolaire.staticSolrEnfantDescription(requeteSite_, enfantDescription);
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
			e("span").a("class", "varInscriptionScolaire", pk, "EnfantDescription ").f().sx(htmEnfantDescription()).g("span");
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
	public void setEnfantObjectifs(String o) {
		this.enfantObjectifs = InscriptionScolaire.staticSetEnfantObjectifs(requeteSite_, o);
		this.enfantObjectifsCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantObjectifs(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantObjectifs(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantObjectifs(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantObjectifs(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantObjectifs(requeteSite_, InscriptionScolaire.staticSolrEnfantObjectifs(requeteSite_, InscriptionScolaire.staticSetEnfantObjectifs(requeteSite_, o)));
	}

	public String solrEnfantObjectifs() {
		return InscriptionScolaire.staticSolrEnfantObjectifs(requeteSite_, enfantObjectifs);
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
			e("span").a("class", "varInscriptionScolaire", pk, "EnfantObjectifs ").f().sx(htmEnfantObjectifs()).g("span");
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
	public void setEnfantPropre(String o) {
		this.enfantPropre = InscriptionScolaire.staticSetEnfantPropre(requeteSite_, o);
		this.enfantPropreCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetEnfantPropre(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrEnfantPropre(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrEnfantPropre(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantPropre(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantPropre(requeteSite_, InscriptionScolaire.staticSolrEnfantPropre(requeteSite_, InscriptionScolaire.staticSetEnfantPropre(requeteSite_, o)));
	}

	public Boolean solrEnfantPropre() {
		return InscriptionScolaire.staticSolrEnfantPropre(requeteSite_, enfantPropre);
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
			e("span").a("class", "varInscriptionScolaire", pk, "EnfantPropre ").f().sx(htmEnfantPropre()).g("span");
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
	public void setInscriptionNomGroupe(String o) {
		this.inscriptionNomGroupe = InscriptionScolaire.staticSetInscriptionNomGroupe(requeteSite_, o);
		this.inscriptionNomGroupeCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionNomGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionNomGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionNomGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionNomGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionNomGroupe(requeteSite_, InscriptionScolaire.staticSolrInscriptionNomGroupe(requeteSite_, InscriptionScolaire.staticSetInscriptionNomGroupe(requeteSite_, o)));
	}

	public String solrInscriptionNomGroupe() {
		return InscriptionScolaire.staticSolrInscriptionNomGroupe(requeteSite_, inscriptionNomGroupe);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionNomGroupe ").f().sx(htmInscriptionNomGroupe()).g("span");
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
	public void setInscriptionCouleurGroupe(String o) {
		this.inscriptionCouleurGroupe = InscriptionScolaire.staticSetInscriptionCouleurGroupe(requeteSite_, o);
		this.inscriptionCouleurGroupeCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionCouleurGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionCouleurGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionCouleurGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionCouleurGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionCouleurGroupe(requeteSite_, InscriptionScolaire.staticSolrInscriptionCouleurGroupe(requeteSite_, InscriptionScolaire.staticSetInscriptionCouleurGroupe(requeteSite_, o)));
	}

	public String solrInscriptionCouleurGroupe() {
		return InscriptionScolaire.staticSolrInscriptionCouleurGroupe(requeteSite_, inscriptionCouleurGroupe);
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
	public void setInscriptionPaimentChaqueMois(String o) {
		this.inscriptionPaimentChaqueMois = InscriptionScolaire.staticSetInscriptionPaimentChaqueMois(requeteSite_, o);
		this.inscriptionPaimentChaqueMoisCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetInscriptionPaimentChaqueMois(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrInscriptionPaimentChaqueMois(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrInscriptionPaimentChaqueMois(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionPaimentChaqueMois(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionPaimentChaqueMois(requeteSite_, InscriptionScolaire.staticSolrInscriptionPaimentChaqueMois(requeteSite_, InscriptionScolaire.staticSetInscriptionPaimentChaqueMois(requeteSite_, o)));
	}

	public Boolean solrInscriptionPaimentChaqueMois() {
		return InscriptionScolaire.staticSolrInscriptionPaimentChaqueMois(requeteSite_, inscriptionPaimentChaqueMois);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionPaimentChaqueMois ").f().sx(htmInscriptionPaimentChaqueMois()).g("span");
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
	public void setInscriptionPaimentComplet(String o) {
		this.inscriptionPaimentComplet = InscriptionScolaire.staticSetInscriptionPaimentComplet(requeteSite_, o);
		this.inscriptionPaimentCompletCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetInscriptionPaimentComplet(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrInscriptionPaimentComplet(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrInscriptionPaimentComplet(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionPaimentComplet(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionPaimentComplet(requeteSite_, InscriptionScolaire.staticSolrInscriptionPaimentComplet(requeteSite_, InscriptionScolaire.staticSetInscriptionPaimentComplet(requeteSite_, o)));
	}

	public Boolean solrInscriptionPaimentComplet() {
		return InscriptionScolaire.staticSolrInscriptionPaimentComplet(requeteSite_, inscriptionPaimentComplet);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionPaimentComplet ").f().sx(htmInscriptionPaimentComplet()).g("span");
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
	public void setCustomerProfileId(String o) {
		this.customerProfileId = InscriptionScolaire.staticSetCustomerProfileId(requeteSite_, o);
		this.customerProfileIdCouverture.dejaInitialise = true;
	}
	public static String staticSetCustomerProfileId(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrCustomerProfileId(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrCustomerProfileId(requeteSite_, InscriptionScolaire.staticSolrCustomerProfileId(requeteSite_, InscriptionScolaire.staticSetCustomerProfileId(requeteSite_, o)));
	}

	public String solrCustomerProfileId() {
		return InscriptionScolaire.staticSolrCustomerProfileId(requeteSite_, customerProfileId);
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
			e("span").a("class", "varInscriptionScolaire", pk, "CustomerProfileId ").f().sx(htmCustomerProfileId()).g("span");
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
	public void setInscriptionDateFrais(Instant o) {
		this.inscriptionDateFrais = o == null ? null : LocalDate.from(o);
		this.inscriptionDateFraisCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setInscriptionDateFrais(String o) {
		this.inscriptionDateFrais = InscriptionScolaire.staticSetInscriptionDateFrais(requeteSite_, o);
		this.inscriptionDateFraisCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetInscriptionDateFrais(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setInscriptionDateFrais(Date o) {
		this.inscriptionDateFrais = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDateFraisCouverture.dejaInitialise = true;
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

	public static Date staticSolrInscriptionDateFrais(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrInscriptionDateFrais(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqInscriptionDateFrais(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDateFrais(requeteSite_, InscriptionScolaire.staticSolrInscriptionDateFrais(requeteSite_, InscriptionScolaire.staticSetInscriptionDateFrais(requeteSite_, o)));
	}

	public Date solrInscriptionDateFrais() {
		return InscriptionScolaire.staticSolrInscriptionDateFrais(requeteSite_, inscriptionDateFrais);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", inscriptionDateFrais == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDateFrais));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDateFrais', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDateFrais')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDateFrais')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionDateFrais ").f().sx(htmInscriptionDateFrais()).g("span");
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
	public static SimpleOrderedMap staticSetPaiementFacets(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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

	//////////////////////
	// paiementLastDate //
	//////////////////////

	/**	 L'entité paiementLastDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate paiementLastDate;
	@JsonIgnore
	public Couverture<LocalDate> paiementLastDateCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("paiementLastDate").o(paiementLastDate);

	/**	<br/> L'entité paiementLastDate
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementLastDate">Trouver l'entité paiementLastDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementLastDate(Couverture<LocalDate> c);

	public LocalDate getPaiementLastDate() {
		return paiementLastDate;
	}

	public void setPaiementLastDate(LocalDate paiementLastDate) {
		this.paiementLastDate = paiementLastDate;
		this.paiementLastDateCouverture.dejaInitialise = true;
	}
	public void setPaiementLastDate(Instant o) {
		this.paiementLastDate = o == null ? null : LocalDate.from(o);
		this.paiementLastDateCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setPaiementLastDate(String o) {
		this.paiementLastDate = InscriptionScolaire.staticSetPaiementLastDate(requeteSite_, o);
		this.paiementLastDateCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetPaiementLastDate(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setPaiementLastDate(Date o) {
		this.paiementLastDate = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.paiementLastDateCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire paiementLastDateInit() {
		if(!paiementLastDateCouverture.dejaInitialise) {
			_paiementLastDate(paiementLastDateCouverture);
			if(paiementLastDate == null)
				setPaiementLastDate(paiementLastDateCouverture.o);
		}
		paiementLastDateCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public static Date staticSolrPaiementLastDate(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrPaiementLastDate(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqPaiementLastDate(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPaiementLastDate(requeteSite_, InscriptionScolaire.staticSolrPaiementLastDate(requeteSite_, InscriptionScolaire.staticSetPaiementLastDate(requeteSite_, o)));
	}

	public Date solrPaiementLastDate() {
		return InscriptionScolaire.staticSolrPaiementLastDate(requeteSite_, paiementLastDate);
	}

	public String strPaiementLastDate() {
		return paiementLastDate == null ? "" : paiementLastDate.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonPaiementLastDate() {
		return paiementLastDate == null ? "" : paiementLastDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichagePaiementLastDate() {
		return null;
	}

	public String htmTooltipPaiementLastDate() {
		return null;
	}

	public String htmPaiementLastDate() {
		return paiementLastDate == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementLastDate());
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
	public void setPaiementLastStr(String o) {
		this.paiementLastStr = InscriptionScolaire.staticSetPaiementLastStr(requeteSite_, o);
		this.paiementLastStrCouverture.dejaInitialise = true;
	}
	public static String staticSetPaiementLastStr(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPaiementLastStr(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPaiementLastStr(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementLastStr(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPaiementLastStr(requeteSite_, InscriptionScolaire.staticSolrPaiementLastStr(requeteSite_, InscriptionScolaire.staticSetPaiementLastStr(requeteSite_, o)));
	}

	public String solrPaiementLastStr() {
		return InscriptionScolaire.staticSolrPaiementLastStr(requeteSite_, paiementLastStr);
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
	public void setPaiementMontant(String o) {
		this.paiementMontant = InscriptionScolaire.staticSetPaiementMontant(requeteSite_, o);
		this.paiementMontantCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetPaiementMontant(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setPaiementMontant(Double o) {
			this.paiementMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paiementMontantCouverture.dejaInitialise = true;
	}
	public void setPaiementMontant(Integer o) {
			this.paiementMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paiementMontantCouverture.dejaInitialise = true;
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

	public static Double staticSolrPaiementMontant(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaiementMontant(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementMontant(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPaiementMontant(requeteSite_, InscriptionScolaire.staticSolrPaiementMontant(requeteSite_, InscriptionScolaire.staticSetPaiementMontant(requeteSite_, o)));
	}

	public Double solrPaiementMontant() {
		return InscriptionScolaire.staticSolrPaiementMontant(requeteSite_, paiementMontant);
	}

	public String strPaiementMontant() {
		return paiementMontant == null ? "" : paiementMontant.setScale(2, RoundingMode.HALF_UP).toString();
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
	public void setFraisMontant(String o) {
		this.fraisMontant = InscriptionScolaire.staticSetFraisMontant(requeteSite_, o);
		this.fraisMontantCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetFraisMontant(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setFraisMontant(Double o) {
			this.fraisMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMontantCouverture.dejaInitialise = true;
	}
	public void setFraisMontant(Integer o) {
			this.fraisMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMontantCouverture.dejaInitialise = true;
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

	public static Double staticSolrFraisMontant(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrFraisMontant(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisMontant(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFraisMontant(requeteSite_, InscriptionScolaire.staticSolrFraisMontant(requeteSite_, InscriptionScolaire.staticSetFraisMontant(requeteSite_, o)));
	}

	public Double solrFraisMontant() {
		return InscriptionScolaire.staticSolrFraisMontant(requeteSite_, fraisMontant);
	}

	public String strFraisMontant() {
		return fraisMontant == null ? "" : fraisMontant.setScale(2, RoundingMode.HALF_UP).toString();
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
	public void setFraisMontantFuture(String o) {
		this.fraisMontantFuture = InscriptionScolaire.staticSetFraisMontantFuture(requeteSite_, o);
		this.fraisMontantFutureCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetFraisMontantFuture(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setFraisMontantFuture(Double o) {
			this.fraisMontantFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMontantFutureCouverture.dejaInitialise = true;
	}
	public void setFraisMontantFuture(Integer o) {
			this.fraisMontantFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMontantFutureCouverture.dejaInitialise = true;
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

	public static Double staticSolrFraisMontantFuture(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrFraisMontantFuture(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisMontantFuture(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFraisMontantFuture(requeteSite_, InscriptionScolaire.staticSolrFraisMontantFuture(requeteSite_, InscriptionScolaire.staticSetFraisMontantFuture(requeteSite_, o)));
	}

	public Double solrFraisMontantFuture() {
		return InscriptionScolaire.staticSolrFraisMontantFuture(requeteSite_, fraisMontantFuture);
	}

	public String strFraisMontantFuture() {
		return fraisMontantFuture == null ? "" : fraisMontantFuture.setScale(2, RoundingMode.HALF_UP).toString();
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
	public void setFraisMontantDu(String o) {
		this.fraisMontantDu = InscriptionScolaire.staticSetFraisMontantDu(requeteSite_, o);
		this.fraisMontantDuCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetFraisMontantDu(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setFraisMontantDu(Double o) {
			this.fraisMontantDu = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMontantDuCouverture.dejaInitialise = true;
	}
	public void setFraisMontantDu(Integer o) {
			this.fraisMontantDu = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMontantDuCouverture.dejaInitialise = true;
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

	public static Double staticSolrFraisMontantDu(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrFraisMontantDu(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisMontantDu(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFraisMontantDu(requeteSite_, InscriptionScolaire.staticSolrFraisMontantDu(requeteSite_, InscriptionScolaire.staticSetFraisMontantDu(requeteSite_, o)));
	}

	public Double solrFraisMontantDu() {
		return InscriptionScolaire.staticSolrFraisMontantDu(requeteSite_, fraisMontantDu);
	}

	public String strFraisMontantDu() {
		return fraisMontantDu == null ? "" : fraisMontantDu.setScale(2, RoundingMode.HALF_UP).toString();
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

	//////////////////////////
	// fraisMontantNonPasse //
	//////////////////////////

	/**	 L'entité fraisMontantNonPasse
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal fraisMontantNonPasse;
	@JsonIgnore
	public Couverture<BigDecimal> fraisMontantNonPasseCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("fraisMontantNonPasse").o(fraisMontantNonPasse);

	/**	<br/> L'entité fraisMontantNonPasse
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontantNonPasse">Trouver l'entité fraisMontantNonPasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisMontantNonPasse(Couverture<BigDecimal> c);

	public BigDecimal getFraisMontantNonPasse() {
		return fraisMontantNonPasse;
	}

	public void setFraisMontantNonPasse(BigDecimal fraisMontantNonPasse) {
		this.fraisMontantNonPasse = fraisMontantNonPasse;
		this.fraisMontantNonPasseCouverture.dejaInitialise = true;
	}
	public void setFraisMontantNonPasse(String o) {
		this.fraisMontantNonPasse = InscriptionScolaire.staticSetFraisMontantNonPasse(requeteSite_, o);
		this.fraisMontantNonPasseCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetFraisMontantNonPasse(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setFraisMontantNonPasse(Double o) {
			this.fraisMontantNonPasse = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMontantNonPasseCouverture.dejaInitialise = true;
	}
	public void setFraisMontantNonPasse(Integer o) {
			this.fraisMontantNonPasse = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMontantNonPasseCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire fraisMontantNonPasseInit() {
		if(!fraisMontantNonPasseCouverture.dejaInitialise) {
			_fraisMontantNonPasse(fraisMontantNonPasseCouverture);
			if(fraisMontantNonPasse == null)
				setFraisMontantNonPasse(fraisMontantNonPasseCouverture.o);
		}
		fraisMontantNonPasseCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public static Double staticSolrFraisMontantNonPasse(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrFraisMontantNonPasse(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisMontantNonPasse(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFraisMontantNonPasse(requeteSite_, InscriptionScolaire.staticSolrFraisMontantNonPasse(requeteSite_, InscriptionScolaire.staticSetFraisMontantNonPasse(requeteSite_, o)));
	}

	public Double solrFraisMontantNonPasse() {
		return InscriptionScolaire.staticSolrFraisMontantNonPasse(requeteSite_, fraisMontantNonPasse);
	}

	public String strFraisMontantNonPasse() {
		return fraisMontantNonPasse == null ? "" : fraisMontantNonPasse.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonFraisMontantNonPasse() {
		return fraisMontantNonPasse == null ? "" : fraisMontantNonPasse.toString();
	}

	public String nomAffichageFraisMontantNonPasse() {
		return null;
	}

	public String htmTooltipFraisMontantNonPasse() {
		return null;
	}

	public String htmFraisMontantNonPasse() {
		return fraisMontantNonPasse == null ? "" : StringEscapeUtils.escapeHtml4(strFraisMontantNonPasse());
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
	public void setFraisMaintenant(String o) {
		this.fraisMaintenant = InscriptionScolaire.staticSetFraisMaintenant(requeteSite_, o);
		this.fraisMaintenantCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetFraisMaintenant(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setFraisMaintenant(Double o) {
			this.fraisMaintenant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMaintenantCouverture.dejaInitialise = true;
	}
	public void setFraisMaintenant(Integer o) {
			this.fraisMaintenant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMaintenantCouverture.dejaInitialise = true;
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

	public static Double staticSolrFraisMaintenant(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrFraisMaintenant(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisMaintenant(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFraisMaintenant(requeteSite_, InscriptionScolaire.staticSolrFraisMaintenant(requeteSite_, InscriptionScolaire.staticSetFraisMaintenant(requeteSite_, o)));
	}

	public Double solrFraisMaintenant() {
		return InscriptionScolaire.staticSolrFraisMaintenant(requeteSite_, fraisMaintenant);
	}

	public String strFraisMaintenant() {
		return fraisMaintenant == null ? "" : fraisMaintenant.setScale(2, RoundingMode.HALF_UP).toString();
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
	public void setPaiementsAJour(String o) {
		this.paiementsAJour = InscriptionScolaire.staticSetPaiementsAJour(requeteSite_, o);
		this.paiementsAJourCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPaiementsAJour(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrPaiementsAJour(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaiementsAJour(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementsAJour(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPaiementsAJour(requeteSite_, InscriptionScolaire.staticSolrPaiementsAJour(requeteSite_, InscriptionScolaire.staticSetPaiementsAJour(requeteSite_, o)));
	}

	public Boolean solrPaiementsAJour() {
		return InscriptionScolaire.staticSolrPaiementsAJour(requeteSite_, paiementsAJour);
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
	public void setPaiementsEnRetard(String o) {
		this.paiementsEnRetard = InscriptionScolaire.staticSetPaiementsEnRetard(requeteSite_, o);
		this.paiementsEnRetardCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPaiementsEnRetard(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrPaiementsEnRetard(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaiementsEnRetard(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementsEnRetard(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPaiementsEnRetard(requeteSite_, InscriptionScolaire.staticSolrPaiementsEnRetard(requeteSite_, InscriptionScolaire.staticSetPaiementsEnRetard(requeteSite_, o)));
	}

	public Boolean solrPaiementsEnRetard() {
		return InscriptionScolaire.staticSolrPaiementsEnRetard(requeteSite_, paiementsEnRetard);
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
	public void setPaiementsEnRetardMontant(String o) {
		this.paiementsEnRetardMontant = InscriptionScolaire.staticSetPaiementsEnRetardMontant(requeteSite_, o);
		this.paiementsEnRetardMontantCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetPaiementsEnRetardMontant(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setPaiementsEnRetardMontant(Double o) {
			this.paiementsEnRetardMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paiementsEnRetardMontantCouverture.dejaInitialise = true;
	}
	public void setPaiementsEnRetardMontant(Integer o) {
			this.paiementsEnRetardMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paiementsEnRetardMontantCouverture.dejaInitialise = true;
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

	public static Double staticSolrPaiementsEnRetardMontant(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaiementsEnRetardMontant(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementsEnRetardMontant(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPaiementsEnRetardMontant(requeteSite_, InscriptionScolaire.staticSolrPaiementsEnRetardMontant(requeteSite_, InscriptionScolaire.staticSetPaiementsEnRetardMontant(requeteSite_, o)));
	}

	public Double solrPaiementsEnRetardMontant() {
		return InscriptionScolaire.staticSolrPaiementsEnRetardMontant(requeteSite_, paiementsEnRetardMontant);
	}

	public String strPaiementsEnRetardMontant() {
		return paiementsEnRetardMontant == null ? "" : paiementsEnRetardMontant.setScale(2, RoundingMode.HALF_UP).toString();
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
	public void setPaiementsEnAvance(String o) {
		this.paiementsEnAvance = InscriptionScolaire.staticSetPaiementsEnAvance(requeteSite_, o);
		this.paiementsEnAvanceCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPaiementsEnAvance(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrPaiementsEnAvance(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaiementsEnAvance(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementsEnAvance(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPaiementsEnAvance(requeteSite_, InscriptionScolaire.staticSolrPaiementsEnAvance(requeteSite_, InscriptionScolaire.staticSetPaiementsEnAvance(requeteSite_, o)));
	}

	public Boolean solrPaiementsEnAvance() {
		return InscriptionScolaire.staticSolrPaiementsEnAvance(requeteSite_, paiementsEnAvance);
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

	///////////////////////////
	// paiementsEnSouffrance //
	///////////////////////////

	/**	 L'entité paiementsEnSouffrance
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementsEnSouffrance;
	@JsonIgnore
	public Couverture<Boolean> paiementsEnSouffranceCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementsEnSouffrance").o(paiementsEnSouffrance);

	/**	<br/> L'entité paiementsEnSouffrance
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementsEnSouffrance">Trouver l'entité paiementsEnSouffrance dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementsEnSouffrance(Couverture<Boolean> c);

	public Boolean getPaiementsEnSouffrance() {
		return paiementsEnSouffrance;
	}

	public void setPaiementsEnSouffrance(Boolean paiementsEnSouffrance) {
		this.paiementsEnSouffrance = paiementsEnSouffrance;
		this.paiementsEnSouffranceCouverture.dejaInitialise = true;
	}
	public void setPaiementsEnSouffrance(String o) {
		this.paiementsEnSouffrance = InscriptionScolaire.staticSetPaiementsEnSouffrance(requeteSite_, o);
		this.paiementsEnSouffranceCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPaiementsEnSouffrance(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected InscriptionScolaire paiementsEnSouffranceInit() {
		if(!paiementsEnSouffranceCouverture.dejaInitialise) {
			_paiementsEnSouffrance(paiementsEnSouffranceCouverture);
			if(paiementsEnSouffrance == null)
				setPaiementsEnSouffrance(paiementsEnSouffranceCouverture.o);
		}
		paiementsEnSouffranceCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public static Boolean staticSolrPaiementsEnSouffrance(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaiementsEnSouffrance(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementsEnSouffrance(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPaiementsEnSouffrance(requeteSite_, InscriptionScolaire.staticSolrPaiementsEnSouffrance(requeteSite_, InscriptionScolaire.staticSetPaiementsEnSouffrance(requeteSite_, o)));
	}

	public Boolean solrPaiementsEnSouffrance() {
		return InscriptionScolaire.staticSolrPaiementsEnSouffrance(requeteSite_, paiementsEnSouffrance);
	}

	public String strPaiementsEnSouffrance() {
		return paiementsEnSouffrance == null ? "" : paiementsEnSouffrance.toString();
	}

	public String jsonPaiementsEnSouffrance() {
		return paiementsEnSouffrance == null ? "" : paiementsEnSouffrance.toString();
	}

	public String nomAffichagePaiementsEnSouffrance() {
		return null;
	}

	public String htmTooltipPaiementsEnSouffrance() {
		return null;
	}

	public String htmPaiementsEnSouffrance() {
		return paiementsEnSouffrance == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementsEnSouffrance());
	}

	//////////////////////////////////
	// paiementsEnSouffranceMontant //
	//////////////////////////////////

	/**	 L'entité paiementsEnSouffranceMontant
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal paiementsEnSouffranceMontant;
	@JsonIgnore
	public Couverture<BigDecimal> paiementsEnSouffranceMontantCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("paiementsEnSouffranceMontant").o(paiementsEnSouffranceMontant);

	/**	<br/> L'entité paiementsEnSouffranceMontant
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementsEnSouffranceMontant">Trouver l'entité paiementsEnSouffranceMontant dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementsEnSouffranceMontant(Couverture<BigDecimal> c);

	public BigDecimal getPaiementsEnSouffranceMontant() {
		return paiementsEnSouffranceMontant;
	}

	public void setPaiementsEnSouffranceMontant(BigDecimal paiementsEnSouffranceMontant) {
		this.paiementsEnSouffranceMontant = paiementsEnSouffranceMontant;
		this.paiementsEnSouffranceMontantCouverture.dejaInitialise = true;
	}
	public void setPaiementsEnSouffranceMontant(String o) {
		this.paiementsEnSouffranceMontant = InscriptionScolaire.staticSetPaiementsEnSouffranceMontant(requeteSite_, o);
		this.paiementsEnSouffranceMontantCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetPaiementsEnSouffranceMontant(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setPaiementsEnSouffranceMontant(Double o) {
			this.paiementsEnSouffranceMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paiementsEnSouffranceMontantCouverture.dejaInitialise = true;
	}
	public void setPaiementsEnSouffranceMontant(Integer o) {
			this.paiementsEnSouffranceMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paiementsEnSouffranceMontantCouverture.dejaInitialise = true;
	}
	protected InscriptionScolaire paiementsEnSouffranceMontantInit() {
		if(!paiementsEnSouffranceMontantCouverture.dejaInitialise) {
			_paiementsEnSouffranceMontant(paiementsEnSouffranceMontantCouverture);
			if(paiementsEnSouffranceMontant == null)
				setPaiementsEnSouffranceMontant(paiementsEnSouffranceMontantCouverture.o);
		}
		paiementsEnSouffranceMontantCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public static Double staticSolrPaiementsEnSouffranceMontant(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaiementsEnSouffranceMontant(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementsEnSouffranceMontant(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrPaiementsEnSouffranceMontant(requeteSite_, InscriptionScolaire.staticSolrPaiementsEnSouffranceMontant(requeteSite_, InscriptionScolaire.staticSetPaiementsEnSouffranceMontant(requeteSite_, o)));
	}

	public Double solrPaiementsEnSouffranceMontant() {
		return InscriptionScolaire.staticSolrPaiementsEnSouffranceMontant(requeteSite_, paiementsEnSouffranceMontant);
	}

	public String strPaiementsEnSouffranceMontant() {
		return paiementsEnSouffranceMontant == null ? "" : paiementsEnSouffranceMontant.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonPaiementsEnSouffranceMontant() {
		return paiementsEnSouffranceMontant == null ? "" : paiementsEnSouffranceMontant.toString();
	}

	public String nomAffichagePaiementsEnSouffranceMontant() {
		return null;
	}

	public String htmTooltipPaiementsEnSouffranceMontant() {
		return null;
	}

	public String htmPaiementsEnSouffranceMontant() {
		return paiementsEnSouffranceMontant == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementsEnSouffranceMontant());
	}

	////////////////
	// fraisCrees //
	////////////////

	/**	 L'entité fraisCrees
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean fraisCrees;
	@JsonIgnore
	public Couverture<Boolean> fraisCreesCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("fraisCrees").o(fraisCrees);

	/**	<br/> L'entité fraisCrees
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.InscriptionScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisCrees">Trouver l'entité fraisCrees dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisCrees(Couverture<Boolean> c);

	public Boolean getFraisCrees() {
		return fraisCrees;
	}

	public void setFraisCrees(Boolean fraisCrees) {
		this.fraisCrees = fraisCrees;
		this.fraisCreesCouverture.dejaInitialise = true;
	}
	public void setFraisCrees(String o) {
		this.fraisCrees = InscriptionScolaire.staticSetFraisCrees(requeteSite_, o);
		this.fraisCreesCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetFraisCrees(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected InscriptionScolaire fraisCreesInit() {
		if(!fraisCreesCouverture.dejaInitialise) {
			_fraisCrees(fraisCreesCouverture);
			if(fraisCrees == null)
				setFraisCrees(fraisCreesCouverture.o);
		}
		fraisCreesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public static Boolean staticSolrFraisCrees(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrFraisCrees(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisCrees(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrFraisCrees(requeteSite_, InscriptionScolaire.staticSolrFraisCrees(requeteSite_, InscriptionScolaire.staticSetFraisCrees(requeteSite_, o)));
	}

	public Boolean solrFraisCrees() {
		return InscriptionScolaire.staticSolrFraisCrees(requeteSite_, fraisCrees);
	}

	public String strFraisCrees() {
		return fraisCrees == null ? "" : fraisCrees.toString();
	}

	public String jsonFraisCrees() {
		return fraisCrees == null ? "" : fraisCrees.toString();
	}

	public String nomAffichageFraisCrees() {
		return null;
	}

	public String htmTooltipFraisCrees() {
		return null;
	}

	public String htmFraisCrees() {
		return fraisCrees == null ? "" : StringEscapeUtils.escapeHtml4(strFraisCrees());
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
	public void setCreeDAnnee(String o) {
		this.creeDAnnee = InscriptionScolaire.staticSetCreeDAnnee(requeteSite_, o);
		this.creeDAnneeCouverture.dejaInitialise = true;
	}
	public static Integer staticSetCreeDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrCreeDAnnee(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrCreeDAnnee(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCreeDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrCreeDAnnee(requeteSite_, InscriptionScolaire.staticSolrCreeDAnnee(requeteSite_, InscriptionScolaire.staticSetCreeDAnnee(requeteSite_, o)));
	}

	public Integer solrCreeDAnnee() {
		return InscriptionScolaire.staticSolrCreeDAnnee(requeteSite_, creeDAnnee);
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
	public void setCreeJourDeSemaine(String o) {
		this.creeJourDeSemaine = InscriptionScolaire.staticSetCreeJourDeSemaine(requeteSite_, o);
		this.creeJourDeSemaineCouverture.dejaInitialise = true;
	}
	public static String staticSetCreeJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrCreeJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrCreeJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCreeJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrCreeJourDeSemaine(requeteSite_, InscriptionScolaire.staticSolrCreeJourDeSemaine(requeteSite_, InscriptionScolaire.staticSetCreeJourDeSemaine(requeteSite_, o)));
	}

	public String solrCreeJourDeSemaine() {
		return InscriptionScolaire.staticSolrCreeJourDeSemaine(requeteSite_, creeJourDeSemaine);
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
	public void setCreeMoisDAnnee(String o) {
		this.creeMoisDAnnee = InscriptionScolaire.staticSetCreeMoisDAnnee(requeteSite_, o);
		this.creeMoisDAnneeCouverture.dejaInitialise = true;
	}
	public static String staticSetCreeMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrCreeMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrCreeMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCreeMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrCreeMoisDAnnee(requeteSite_, InscriptionScolaire.staticSolrCreeMoisDAnnee(requeteSite_, InscriptionScolaire.staticSetCreeMoisDAnnee(requeteSite_, o)));
	}

	public String solrCreeMoisDAnnee() {
		return InscriptionScolaire.staticSolrCreeMoisDAnnee(requeteSite_, creeMoisDAnnee);
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
	public void setCreeHeureDuJour(String o) {
		this.creeHeureDuJour = InscriptionScolaire.staticSetCreeHeureDuJour(requeteSite_, o);
		this.creeHeureDuJourCouverture.dejaInitialise = true;
	}
	public static String staticSetCreeHeureDuJour(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrCreeHeureDuJour(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrCreeHeureDuJour(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCreeHeureDuJour(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrCreeHeureDuJour(requeteSite_, InscriptionScolaire.staticSolrCreeHeureDuJour(requeteSite_, InscriptionScolaire.staticSetCreeHeureDuJour(requeteSite_, o)));
	}

	public String solrCreeHeureDuJour() {
		return InscriptionScolaire.staticSolrCreeHeureDuJour(requeteSite_, creeHeureDuJour);
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
	public static String staticSetInscriptionJoursDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public void setInscriptionJoursDeSemaine(JsonArray objets) {
		inscriptionJoursDeSemaine.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addInscriptionJoursDeSemaine(o);
		}
	}
	protected InscriptionScolaire inscriptionJoursDeSemaineInit() {
		if(!inscriptionJoursDeSemaineCouverture.dejaInitialise) {
			_inscriptionJoursDeSemaine(inscriptionJoursDeSemaine);
		}
		inscriptionJoursDeSemaineCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public static String staticSolrInscriptionJoursDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionJoursDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionJoursDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionJoursDeSemaine(requeteSite_, InscriptionScolaire.staticSolrInscriptionJoursDeSemaine(requeteSite_, InscriptionScolaire.staticSetInscriptionJoursDeSemaine(requeteSite_, o)));
	}

	public List<String> solrInscriptionJoursDeSemaine() {
		List<String> l = new ArrayList<String>();
		for(String o : inscriptionJoursDeSemaine) {
			l.add(InscriptionScolaire.staticSolrInscriptionJoursDeSemaine(requeteSite_, o));
		}
		return l;
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
	public void setInscriptionNomsParents(String o) {
		this.inscriptionNomsParents = InscriptionScolaire.staticSetInscriptionNomsParents(requeteSite_, o);
		this.inscriptionNomsParentsCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionNomsParents(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionNomsParents(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionNomsParents(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionNomsParents(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionNomsParents(requeteSite_, InscriptionScolaire.staticSolrInscriptionNomsParents(requeteSite_, InscriptionScolaire.staticSetInscriptionNomsParents(requeteSite_, o)));
	}

	public String solrInscriptionNomsParents() {
		return InscriptionScolaire.staticSolrInscriptionNomsParents(requeteSite_, inscriptionNomsParents);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionNomsParents ").f().sx(htmInscriptionNomsParents()).g("span");
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
	public static String staticSetInscriptionMails(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public void setInscriptionMails(JsonArray objets) {
		inscriptionMails.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addInscriptionMails(o);
		}
	}
	protected InscriptionScolaire inscriptionMailsInit() {
		if(!inscriptionMailsCouverture.dejaInitialise) {
			_inscriptionMails(inscriptionMails);
		}
		inscriptionMailsCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public static String staticSolrInscriptionMails(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionMails(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionMails(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionMails(requeteSite_, InscriptionScolaire.staticSolrInscriptionMails(requeteSite_, InscriptionScolaire.staticSetInscriptionMails(requeteSite_, o)));
	}

	public List<String> solrInscriptionMails() {
		List<String> l = new ArrayList<String>();
		for(String o : inscriptionMails) {
			l.add(InscriptionScolaire.staticSolrInscriptionMails(requeteSite_, o));
		}
		return l;
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
	public void setInscriptionMail(String o) {
		this.inscriptionMail = InscriptionScolaire.staticSetInscriptionMail(requeteSite_, o);
		this.inscriptionMailCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionMail(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionMail(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionMail(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionMail(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionMail(requeteSite_, InscriptionScolaire.staticSolrInscriptionMail(requeteSite_, InscriptionScolaire.staticSetInscriptionMail(requeteSite_, o)));
	}

	public String solrInscriptionMail() {
		return InscriptionScolaire.staticSolrInscriptionMail(requeteSite_, inscriptionMail);
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
	public void setInscriptionMailsParents(String o) {
		this.inscriptionMailsParents = InscriptionScolaire.staticSetInscriptionMailsParents(requeteSite_, o);
		this.inscriptionMailsParentsCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionMailsParents(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionMailsParents(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionMailsParents(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionMailsParents(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionMailsParents(requeteSite_, InscriptionScolaire.staticSolrInscriptionMailsParents(requeteSite_, InscriptionScolaire.staticSetInscriptionMailsParents(requeteSite_, o)));
	}

	public String solrInscriptionMailsParents() {
		return InscriptionScolaire.staticSolrInscriptionMailsParents(requeteSite_, inscriptionMailsParents);
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
	public static String staticSetInscriptionNumeroTelephones(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public void setInscriptionNumeroTelephones(JsonArray objets) {
		inscriptionNumeroTelephones.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addInscriptionNumeroTelephones(o);
		}
	}
	protected InscriptionScolaire inscriptionNumeroTelephonesInit() {
		if(!inscriptionNumeroTelephonesCouverture.dejaInitialise) {
			_inscriptionNumeroTelephones(inscriptionNumeroTelephones);
		}
		inscriptionNumeroTelephonesCouverture.dejaInitialise(true);
		return (InscriptionScolaire)this;
	}

	public static String staticSolrInscriptionNumeroTelephones(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionNumeroTelephones(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionNumeroTelephones(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionNumeroTelephones(requeteSite_, InscriptionScolaire.staticSolrInscriptionNumeroTelephones(requeteSite_, InscriptionScolaire.staticSetInscriptionNumeroTelephones(requeteSite_, o)));
	}

	public List<String> solrInscriptionNumeroTelephones() {
		List<String> l = new ArrayList<String>();
		for(String o : inscriptionNumeroTelephones) {
			l.add(InscriptionScolaire.staticSolrInscriptionNumeroTelephones(requeteSite_, o));
		}
		return l;
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
	public void setInscriptionNumeroTelephone(String o) {
		this.inscriptionNumeroTelephone = InscriptionScolaire.staticSetInscriptionNumeroTelephone(requeteSite_, o);
		this.inscriptionNumeroTelephoneCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionNumeroTelephone(requeteSite_, InscriptionScolaire.staticSolrInscriptionNumeroTelephone(requeteSite_, InscriptionScolaire.staticSetInscriptionNumeroTelephone(requeteSite_, o)));
	}

	public String solrInscriptionNumeroTelephone() {
		return InscriptionScolaire.staticSolrInscriptionNumeroTelephone(requeteSite_, inscriptionNumeroTelephone);
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
	public void setInscriptionNomParent(String o) {
		this.inscriptionNomParent = InscriptionScolaire.staticSetInscriptionNomParent(requeteSite_, o);
		this.inscriptionNomParentCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionNomParent(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionNomParent(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionNomParent(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionNomParent(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionNomParent(requeteSite_, InscriptionScolaire.staticSolrInscriptionNomParent(requeteSite_, InscriptionScolaire.staticSetInscriptionNomParent(requeteSite_, o)));
	}

	public String solrInscriptionNomParent() {
		return InscriptionScolaire.staticSolrInscriptionNomParent(requeteSite_, inscriptionNomParent);
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
	public void setInscriptionNomParentLignes(String o) {
		this.inscriptionNomParentLignes = InscriptionScolaire.staticSetInscriptionNomParentLignes(requeteSite_, o);
		this.inscriptionNomParentLignesCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionNomParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionNomParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionNomParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionNomParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionNomParentLignes(requeteSite_, InscriptionScolaire.staticSolrInscriptionNomParentLignes(requeteSite_, InscriptionScolaire.staticSetInscriptionNomParentLignes(requeteSite_, o)));
	}

	public String solrInscriptionNomParentLignes() {
		return InscriptionScolaire.staticSolrInscriptionNomParentLignes(requeteSite_, inscriptionNomParentLignes);
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
	public void setInscriptionMailParentLignes(String o) {
		this.inscriptionMailParentLignes = InscriptionScolaire.staticSetInscriptionMailParentLignes(requeteSite_, o);
		this.inscriptionMailParentLignesCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionMailParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionMailParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionMailParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionMailParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionMailParentLignes(requeteSite_, InscriptionScolaire.staticSolrInscriptionMailParentLignes(requeteSite_, InscriptionScolaire.staticSetInscriptionMailParentLignes(requeteSite_, o)));
	}

	public String solrInscriptionMailParentLignes() {
		return InscriptionScolaire.staticSolrInscriptionMailParentLignes(requeteSite_, inscriptionMailParentLignes);
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
	public void setInscriptionDetailParentLignes(String o) {
		this.inscriptionDetailParentLignes = InscriptionScolaire.staticSetInscriptionDetailParentLignes(requeteSite_, o);
		this.inscriptionDetailParentLignesCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionDetailParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionDetailParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionDetailParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionDetailParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDetailParentLignes(requeteSite_, InscriptionScolaire.staticSolrInscriptionDetailParentLignes(requeteSite_, InscriptionScolaire.staticSetInscriptionDetailParentLignes(requeteSite_, o)));
	}

	public String solrInscriptionDetailParentLignes() {
		return InscriptionScolaire.staticSolrInscriptionDetailParentLignes(requeteSite_, inscriptionDetailParentLignes);
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
	public void setInscriptionChercherParentLignes(String o) {
		this.inscriptionChercherParentLignes = InscriptionScolaire.staticSetInscriptionChercherParentLignes(requeteSite_, o);
		this.inscriptionChercherParentLignesCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionChercherParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionChercherParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionChercherParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionChercherParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionChercherParentLignes(requeteSite_, InscriptionScolaire.staticSolrInscriptionChercherParentLignes(requeteSite_, InscriptionScolaire.staticSetInscriptionChercherParentLignes(requeteSite_, o)));
	}

	public String solrInscriptionChercherParentLignes() {
		return InscriptionScolaire.staticSolrInscriptionChercherParentLignes(requeteSite_, inscriptionChercherParentLignes);
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
	public void setInscriptionContactUrgenceParentLignes(String o) {
		this.inscriptionContactUrgenceParentLignes = InscriptionScolaire.staticSetInscriptionContactUrgenceParentLignes(requeteSite_, o);
		this.inscriptionContactUrgenceParentLignesCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionContactUrgenceParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionContactUrgenceParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionContactUrgenceParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionContactUrgenceParentLignes(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionContactUrgenceParentLignes(requeteSite_, InscriptionScolaire.staticSolrInscriptionContactUrgenceParentLignes(requeteSite_, InscriptionScolaire.staticSetInscriptionContactUrgenceParentLignes(requeteSite_, o)));
	}

	public String solrInscriptionContactUrgenceParentLignes() {
		return InscriptionScolaire.staticSolrInscriptionContactUrgenceParentLignes(requeteSite_, inscriptionContactUrgenceParentLignes);
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
	public void setInscriptionSignature1(String o) {
		this.inscriptionSignature1 = InscriptionScolaire.staticSetInscriptionSignature1(requeteSite_, o);
		this.inscriptionSignature1Couverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionSignature1(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionSignature1(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionSignature1(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionSignature1(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionSignature1(requeteSite_, InscriptionScolaire.staticSolrInscriptionSignature1(requeteSite_, InscriptionScolaire.staticSetInscriptionSignature1(requeteSite_, o)));
	}

	public String solrInscriptionSignature1() {
		return InscriptionScolaire.staticSolrInscriptionSignature1(requeteSite_, inscriptionSignature1);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionSignature1 ").f().sx(htmInscriptionSignature1()).g("span");
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
	public void setInscriptionSignature2(String o) {
		this.inscriptionSignature2 = InscriptionScolaire.staticSetInscriptionSignature2(requeteSite_, o);
		this.inscriptionSignature2Couverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionSignature2(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionSignature2(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionSignature2(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionSignature2(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionSignature2(requeteSite_, InscriptionScolaire.staticSolrInscriptionSignature2(requeteSite_, InscriptionScolaire.staticSetInscriptionSignature2(requeteSite_, o)));
	}

	public String solrInscriptionSignature2() {
		return InscriptionScolaire.staticSolrInscriptionSignature2(requeteSite_, inscriptionSignature2);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionSignature2 ").f().sx(htmInscriptionSignature2()).g("span");
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
	public void setInscriptionSignature3(String o) {
		this.inscriptionSignature3 = InscriptionScolaire.staticSetInscriptionSignature3(requeteSite_, o);
		this.inscriptionSignature3Couverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionSignature3(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionSignature3(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionSignature3(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionSignature3(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionSignature3(requeteSite_, InscriptionScolaire.staticSolrInscriptionSignature3(requeteSite_, InscriptionScolaire.staticSetInscriptionSignature3(requeteSite_, o)));
	}

	public String solrInscriptionSignature3() {
		return InscriptionScolaire.staticSolrInscriptionSignature3(requeteSite_, inscriptionSignature3);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionSignature3 ").f().sx(htmInscriptionSignature3()).g("span");
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
	public void setInscriptionSignature4(String o) {
		this.inscriptionSignature4 = InscriptionScolaire.staticSetInscriptionSignature4(requeteSite_, o);
		this.inscriptionSignature4Couverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionSignature4(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionSignature4(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionSignature4(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionSignature4(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionSignature4(requeteSite_, InscriptionScolaire.staticSolrInscriptionSignature4(requeteSite_, InscriptionScolaire.staticSetInscriptionSignature4(requeteSite_, o)));
	}

	public String solrInscriptionSignature4() {
		return InscriptionScolaire.staticSolrInscriptionSignature4(requeteSite_, inscriptionSignature4);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionSignature4 ").f().sx(htmInscriptionSignature4()).g("span");
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
	public void setInscriptionSignature5(String o) {
		this.inscriptionSignature5 = InscriptionScolaire.staticSetInscriptionSignature5(requeteSite_, o);
		this.inscriptionSignature5Couverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionSignature5(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionSignature5(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionSignature5(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionSignature5(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionSignature5(requeteSite_, InscriptionScolaire.staticSolrInscriptionSignature5(requeteSite_, InscriptionScolaire.staticSetInscriptionSignature5(requeteSite_, o)));
	}

	public String solrInscriptionSignature5() {
		return InscriptionScolaire.staticSolrInscriptionSignature5(requeteSite_, inscriptionSignature5);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionSignature5 ").f().sx(htmInscriptionSignature5()).g("span");
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
	public void setInscriptionSignature6(String o) {
		this.inscriptionSignature6 = InscriptionScolaire.staticSetInscriptionSignature6(requeteSite_, o);
		this.inscriptionSignature6Couverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionSignature6(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionSignature6(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionSignature6(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionSignature6(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionSignature6(requeteSite_, InscriptionScolaire.staticSolrInscriptionSignature6(requeteSite_, InscriptionScolaire.staticSetInscriptionSignature6(requeteSite_, o)));
	}

	public String solrInscriptionSignature6() {
		return InscriptionScolaire.staticSolrInscriptionSignature6(requeteSite_, inscriptionSignature6);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionSignature6 ").f().sx(htmInscriptionSignature6()).g("span");
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
	public void setInscriptionSignature7(String o) {
		this.inscriptionSignature7 = InscriptionScolaire.staticSetInscriptionSignature7(requeteSite_, o);
		this.inscriptionSignature7Couverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionSignature7(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionSignature7(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionSignature7(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionSignature7(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionSignature7(requeteSite_, InscriptionScolaire.staticSolrInscriptionSignature7(requeteSite_, InscriptionScolaire.staticSetInscriptionSignature7(requeteSite_, o)));
	}

	public String solrInscriptionSignature7() {
		return InscriptionScolaire.staticSolrInscriptionSignature7(requeteSite_, inscriptionSignature7);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionSignature7 ").f().sx(htmInscriptionSignature7()).g("span");
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
	public void setInscriptionSignature8(String o) {
		this.inscriptionSignature8 = InscriptionScolaire.staticSetInscriptionSignature8(requeteSite_, o);
		this.inscriptionSignature8Couverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionSignature8(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionSignature8(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionSignature8(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionSignature8(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionSignature8(requeteSite_, InscriptionScolaire.staticSolrInscriptionSignature8(requeteSite_, InscriptionScolaire.staticSetInscriptionSignature8(requeteSite_, o)));
	}

	public String solrInscriptionSignature8() {
		return InscriptionScolaire.staticSolrInscriptionSignature8(requeteSite_, inscriptionSignature8);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionSignature8 ").f().sx(htmInscriptionSignature8()).g("span");
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
	public void setInscriptionSignature9(String o) {
		this.inscriptionSignature9 = InscriptionScolaire.staticSetInscriptionSignature9(requeteSite_, o);
		this.inscriptionSignature9Couverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionSignature9(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionSignature9(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionSignature9(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionSignature9(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionSignature9(requeteSite_, InscriptionScolaire.staticSolrInscriptionSignature9(requeteSite_, InscriptionScolaire.staticSetInscriptionSignature9(requeteSite_, o)));
	}

	public String solrInscriptionSignature9() {
		return InscriptionScolaire.staticSolrInscriptionSignature9(requeteSite_, inscriptionSignature9);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionSignature9 ").f().sx(htmInscriptionSignature9()).g("span");
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
	public void setInscriptionSignature10(String o) {
		this.inscriptionSignature10 = InscriptionScolaire.staticSetInscriptionSignature10(requeteSite_, o);
		this.inscriptionSignature10Couverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionSignature10(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionSignature10(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionSignature10(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionSignature10(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionSignature10(requeteSite_, InscriptionScolaire.staticSolrInscriptionSignature10(requeteSite_, InscriptionScolaire.staticSetInscriptionSignature10(requeteSite_, o)));
	}

	public String solrInscriptionSignature10() {
		return InscriptionScolaire.staticSolrInscriptionSignature10(requeteSite_, inscriptionSignature10);
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
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionSignature10 ").f().sx(htmInscriptionSignature10()).g("span");
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
	public void setInscriptionDate1(Instant o) {
		this.inscriptionDate1 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate1Couverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setInscriptionDate1(String o) {
		this.inscriptionDate1 = InscriptionScolaire.staticSetInscriptionDate1(requeteSite_, o);
		this.inscriptionDate1Couverture.dejaInitialise = true;
	}
	public static LocalDate staticSetInscriptionDate1(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setInscriptionDate1(Date o) {
		this.inscriptionDate1 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate1Couverture.dejaInitialise = true;
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

	public static Date staticSolrInscriptionDate1(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrInscriptionDate1(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqInscriptionDate1(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDate1(requeteSite_, InscriptionScolaire.staticSolrInscriptionDate1(requeteSite_, InscriptionScolaire.staticSetInscriptionDate1(requeteSite_, o)));
	}

	public Date solrInscriptionDate1() {
		return InscriptionScolaire.staticSolrInscriptionDate1(requeteSite_, inscriptionDate1);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", inscriptionDate1 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate1));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate1', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate1')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate1')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionDate1 ").f().sx(htmInscriptionDate1()).g("span");
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
	public void setInscriptionDate2(Instant o) {
		this.inscriptionDate2 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate2Couverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setInscriptionDate2(String o) {
		this.inscriptionDate2 = InscriptionScolaire.staticSetInscriptionDate2(requeteSite_, o);
		this.inscriptionDate2Couverture.dejaInitialise = true;
	}
	public static LocalDate staticSetInscriptionDate2(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setInscriptionDate2(Date o) {
		this.inscriptionDate2 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate2Couverture.dejaInitialise = true;
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

	public static Date staticSolrInscriptionDate2(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrInscriptionDate2(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqInscriptionDate2(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDate2(requeteSite_, InscriptionScolaire.staticSolrInscriptionDate2(requeteSite_, InscriptionScolaire.staticSetInscriptionDate2(requeteSite_, o)));
	}

	public Date solrInscriptionDate2() {
		return InscriptionScolaire.staticSolrInscriptionDate2(requeteSite_, inscriptionDate2);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", inscriptionDate2 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate2));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate2', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate2')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate2')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionDate2 ").f().sx(htmInscriptionDate2()).g("span");
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
	public void setInscriptionDate3(Instant o) {
		this.inscriptionDate3 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate3Couverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setInscriptionDate3(String o) {
		this.inscriptionDate3 = InscriptionScolaire.staticSetInscriptionDate3(requeteSite_, o);
		this.inscriptionDate3Couverture.dejaInitialise = true;
	}
	public static LocalDate staticSetInscriptionDate3(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setInscriptionDate3(Date o) {
		this.inscriptionDate3 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate3Couverture.dejaInitialise = true;
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

	public static Date staticSolrInscriptionDate3(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrInscriptionDate3(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqInscriptionDate3(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDate3(requeteSite_, InscriptionScolaire.staticSolrInscriptionDate3(requeteSite_, InscriptionScolaire.staticSetInscriptionDate3(requeteSite_, o)));
	}

	public Date solrInscriptionDate3() {
		return InscriptionScolaire.staticSolrInscriptionDate3(requeteSite_, inscriptionDate3);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", inscriptionDate3 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate3));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate3', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate3')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate3')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionDate3 ").f().sx(htmInscriptionDate3()).g("span");
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
	public void setInscriptionDate4(Instant o) {
		this.inscriptionDate4 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate4Couverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setInscriptionDate4(String o) {
		this.inscriptionDate4 = InscriptionScolaire.staticSetInscriptionDate4(requeteSite_, o);
		this.inscriptionDate4Couverture.dejaInitialise = true;
	}
	public static LocalDate staticSetInscriptionDate4(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setInscriptionDate4(Date o) {
		this.inscriptionDate4 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate4Couverture.dejaInitialise = true;
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

	public static Date staticSolrInscriptionDate4(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrInscriptionDate4(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqInscriptionDate4(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDate4(requeteSite_, InscriptionScolaire.staticSolrInscriptionDate4(requeteSite_, InscriptionScolaire.staticSetInscriptionDate4(requeteSite_, o)));
	}

	public Date solrInscriptionDate4() {
		return InscriptionScolaire.staticSolrInscriptionDate4(requeteSite_, inscriptionDate4);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", inscriptionDate4 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate4));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate4', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate4')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate4')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionDate4 ").f().sx(htmInscriptionDate4()).g("span");
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
	public void setInscriptionDate5(Instant o) {
		this.inscriptionDate5 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate5Couverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setInscriptionDate5(String o) {
		this.inscriptionDate5 = InscriptionScolaire.staticSetInscriptionDate5(requeteSite_, o);
		this.inscriptionDate5Couverture.dejaInitialise = true;
	}
	public static LocalDate staticSetInscriptionDate5(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setInscriptionDate5(Date o) {
		this.inscriptionDate5 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate5Couverture.dejaInitialise = true;
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

	public static Date staticSolrInscriptionDate5(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrInscriptionDate5(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqInscriptionDate5(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDate5(requeteSite_, InscriptionScolaire.staticSolrInscriptionDate5(requeteSite_, InscriptionScolaire.staticSetInscriptionDate5(requeteSite_, o)));
	}

	public Date solrInscriptionDate5() {
		return InscriptionScolaire.staticSolrInscriptionDate5(requeteSite_, inscriptionDate5);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", inscriptionDate5 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate5));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate5', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate5')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate5')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionDate5 ").f().sx(htmInscriptionDate5()).g("span");
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
	public void setInscriptionDate6(Instant o) {
		this.inscriptionDate6 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate6Couverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setInscriptionDate6(String o) {
		this.inscriptionDate6 = InscriptionScolaire.staticSetInscriptionDate6(requeteSite_, o);
		this.inscriptionDate6Couverture.dejaInitialise = true;
	}
	public static LocalDate staticSetInscriptionDate6(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setInscriptionDate6(Date o) {
		this.inscriptionDate6 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate6Couverture.dejaInitialise = true;
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

	public static Date staticSolrInscriptionDate6(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrInscriptionDate6(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqInscriptionDate6(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDate6(requeteSite_, InscriptionScolaire.staticSolrInscriptionDate6(requeteSite_, InscriptionScolaire.staticSetInscriptionDate6(requeteSite_, o)));
	}

	public Date solrInscriptionDate6() {
		return InscriptionScolaire.staticSolrInscriptionDate6(requeteSite_, inscriptionDate6);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", inscriptionDate6 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate6));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate6', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate6')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate6')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionDate6 ").f().sx(htmInscriptionDate6()).g("span");
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
	public void setInscriptionDate7(Instant o) {
		this.inscriptionDate7 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate7Couverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setInscriptionDate7(String o) {
		this.inscriptionDate7 = InscriptionScolaire.staticSetInscriptionDate7(requeteSite_, o);
		this.inscriptionDate7Couverture.dejaInitialise = true;
	}
	public static LocalDate staticSetInscriptionDate7(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setInscriptionDate7(Date o) {
		this.inscriptionDate7 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate7Couverture.dejaInitialise = true;
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

	public static Date staticSolrInscriptionDate7(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrInscriptionDate7(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqInscriptionDate7(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDate7(requeteSite_, InscriptionScolaire.staticSolrInscriptionDate7(requeteSite_, InscriptionScolaire.staticSetInscriptionDate7(requeteSite_, o)));
	}

	public Date solrInscriptionDate7() {
		return InscriptionScolaire.staticSolrInscriptionDate7(requeteSite_, inscriptionDate7);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", inscriptionDate7 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate7));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate7', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate7')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate7')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionDate7 ").f().sx(htmInscriptionDate7()).g("span");
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
	public void setInscriptionDate8(Instant o) {
		this.inscriptionDate8 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate8Couverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setInscriptionDate8(String o) {
		this.inscriptionDate8 = InscriptionScolaire.staticSetInscriptionDate8(requeteSite_, o);
		this.inscriptionDate8Couverture.dejaInitialise = true;
	}
	public static LocalDate staticSetInscriptionDate8(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setInscriptionDate8(Date o) {
		this.inscriptionDate8 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate8Couverture.dejaInitialise = true;
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

	public static Date staticSolrInscriptionDate8(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrInscriptionDate8(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqInscriptionDate8(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDate8(requeteSite_, InscriptionScolaire.staticSolrInscriptionDate8(requeteSite_, InscriptionScolaire.staticSetInscriptionDate8(requeteSite_, o)));
	}

	public Date solrInscriptionDate8() {
		return InscriptionScolaire.staticSolrInscriptionDate8(requeteSite_, inscriptionDate8);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", inscriptionDate8 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate8));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate8', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate8')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate8')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionDate8 ").f().sx(htmInscriptionDate8()).g("span");
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
	public void setInscriptionDate9(Instant o) {
		this.inscriptionDate9 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate9Couverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setInscriptionDate9(String o) {
		this.inscriptionDate9 = InscriptionScolaire.staticSetInscriptionDate9(requeteSite_, o);
		this.inscriptionDate9Couverture.dejaInitialise = true;
	}
	public static LocalDate staticSetInscriptionDate9(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setInscriptionDate9(Date o) {
		this.inscriptionDate9 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate9Couverture.dejaInitialise = true;
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

	public static Date staticSolrInscriptionDate9(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrInscriptionDate9(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqInscriptionDate9(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDate9(requeteSite_, InscriptionScolaire.staticSolrInscriptionDate9(requeteSite_, InscriptionScolaire.staticSetInscriptionDate9(requeteSite_, o)));
	}

	public Date solrInscriptionDate9() {
		return InscriptionScolaire.staticSolrInscriptionDate9(requeteSite_, inscriptionDate9);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", inscriptionDate9 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate9));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate9', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate9')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate9')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionDate9 ").f().sx(htmInscriptionDate9()).g("span");
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
	public void setInscriptionDate10(Instant o) {
		this.inscriptionDate10 = o == null ? null : LocalDate.from(o);
		this.inscriptionDate10Couverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setInscriptionDate10(String o) {
		this.inscriptionDate10 = InscriptionScolaire.staticSetInscriptionDate10(requeteSite_, o);
		this.inscriptionDate10Couverture.dejaInitialise = true;
	}
	public static LocalDate staticSetInscriptionDate10(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setInscriptionDate10(Date o) {
		this.inscriptionDate10 = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.inscriptionDate10Couverture.dejaInitialise = true;
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

	public static Date staticSolrInscriptionDate10(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrInscriptionDate10(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqInscriptionDate10(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionDate10(requeteSite_, InscriptionScolaire.staticSolrInscriptionDate10(requeteSite_, InscriptionScolaire.staticSetInscriptionDate10(requeteSite_, o)));
	}

	public Date solrInscriptionDate10() {
		return InscriptionScolaire.staticSolrInscriptionDate10(requeteSite_, inscriptionDate10);
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
					.a("title", "La clé primaire des utilisateurs dans la base de données.  (DD-MM-YYYY)")
					.a("value", inscriptionDate10 == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(inscriptionDate10));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setInscriptionDate10', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_inscriptionDate10')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_inscriptionDate10')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varInscriptionScolaire", pk, "InscriptionDate10 ").f().sx(htmInscriptionDate10()).g("span");
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
	public static InscriptionScolaire staticSetInscriptionsAnnees(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static InscriptionScolaire staticSetInscriptionsApprouves(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static InscriptionScolaire staticSetInscriptionsGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public static InscriptionScolaire staticSetInscriptionsInscription(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	public void setEnfantImmunisationsRecu(String o) {
		this.enfantImmunisationsRecu = InscriptionScolaire.staticSetEnfantImmunisationsRecu(requeteSite_, o);
		this.enfantImmunisationsRecuCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantImmunisationsRecu(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantImmunisationsRecu(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantImmunisationsRecu(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantImmunisationsRecu(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantImmunisationsRecu(requeteSite_, InscriptionScolaire.staticSolrEnfantImmunisationsRecu(requeteSite_, InscriptionScolaire.staticSetEnfantImmunisationsRecu(requeteSite_, o)));
	}

	public String solrEnfantImmunisationsRecu() {
		return InscriptionScolaire.staticSolrEnfantImmunisationsRecu(requeteSite_, enfantImmunisationsRecu);
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
	public void setEnfantPhotosApprouve(String o) {
		this.enfantPhotosApprouve = InscriptionScolaire.staticSetEnfantPhotosApprouve(requeteSite_, o);
		this.enfantPhotosApprouveCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantPhotosApprouve(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantPhotosApprouve(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantPhotosApprouve(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantPhotosApprouve(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrEnfantPhotosApprouve(requeteSite_, InscriptionScolaire.staticSolrEnfantPhotosApprouve(requeteSite_, InscriptionScolaire.staticSetEnfantPhotosApprouve(requeteSite_, o)));
	}

	public String solrEnfantPhotosApprouve() {
		return InscriptionScolaire.staticSolrEnfantPhotosApprouve(requeteSite_, enfantPhotosApprouve);
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
	public void setInscriptionNumero(String o) {
		this.inscriptionNumero = InscriptionScolaire.staticSetInscriptionNumero(requeteSite_, o);
		this.inscriptionNumeroCouverture.dejaInitialise = true;
	}
	public static Integer staticSetInscriptionNumero(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrInscriptionNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrInscriptionNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionNumero(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionNumero(requeteSite_, InscriptionScolaire.staticSolrInscriptionNumero(requeteSite_, InscriptionScolaire.staticSetInscriptionNumero(requeteSite_, o)));
	}

	public Integer solrInscriptionNumero() {
		return InscriptionScolaire.staticSolrInscriptionNumero(requeteSite_, inscriptionNumero);
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
	public void setInscriptionNomComplet(String o) {
		this.inscriptionNomComplet = InscriptionScolaire.staticSetInscriptionNomComplet(requeteSite_, o);
		this.inscriptionNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrInscriptionNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return InscriptionScolaire.staticSolrStrInscriptionNomComplet(requeteSite_, InscriptionScolaire.staticSolrInscriptionNomComplet(requeteSite_, InscriptionScolaire.staticSetInscriptionNomComplet(requeteSite_, o)));
	}

	public String solrInscriptionNomComplet() {
		return InscriptionScolaire.staticSolrInscriptionNomComplet(requeteSite_, inscriptionNomComplet);
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
		paiementLastDateInit();
		paiementLastStrInit();
		paiementMontantInit();
		fraisMontantInit();
		fraisMontantFutureInit();
		fraisMontantDuInit();
		fraisMontantNonPasseInit();
		fraisMaintenantInit();
		paiementsAJourInit();
		paiementsEnRetardInit();
		paiementsEnRetardMontantInit();
		paiementsEnAvanceInit();
		paiementsEnSouffranceInit();
		paiementsEnSouffranceMontantInit();
		fraisCreesInit();
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
			case "paiementLastDate":
				return oInscriptionScolaire.paiementLastDate;
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
			case "fraisMontantNonPasse":
				return oInscriptionScolaire.fraisMontantNonPasse;
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
			case "paiementsEnSouffrance":
				return oInscriptionScolaire.paiementsEnSouffrance;
			case "paiementsEnSouffranceMontant":
				return oInscriptionScolaire.paiementsEnSouffranceMontant;
			case "fraisCrees":
				return oInscriptionScolaire.fraisCrees;
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
				if(oInscriptionScolaire.getAnneeCle() == null)
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
				if(oInscriptionScolaire.getEnfantCle() == null)
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetInscriptionScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetInscriptionScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "inscriptionCle":
			return InscriptionScolaire.staticSetInscriptionCle(requeteSite_, o);
		case "anneeCle":
			return InscriptionScolaire.staticSetAnneeCle(requeteSite_, o);
		case "blocCles":
			return InscriptionScolaire.staticSetBlocCles(requeteSite_, o);
		case "ecoleCle":
			return InscriptionScolaire.staticSetEcoleCle(requeteSite_, o);
		case "sessionCle":
			return InscriptionScolaire.staticSetSessionCle(requeteSite_, o);
		case "ageCle":
			return InscriptionScolaire.staticSetAgeCle(requeteSite_, o);
		case "blocCle":
			return InscriptionScolaire.staticSetBlocCle(requeteSite_, o);
		case "enfantCle":
			return InscriptionScolaire.staticSetEnfantCle(requeteSite_, o);
		case "mereCles":
			return InscriptionScolaire.staticSetMereCles(requeteSite_, o);
		case "pereCles":
			return InscriptionScolaire.staticSetPereCles(requeteSite_, o);
		case "gardienCles":
			return InscriptionScolaire.staticSetGardienCles(requeteSite_, o);
		case "paiementCles":
			return InscriptionScolaire.staticSetPaiementCles(requeteSite_, o);
		case "formInscriptionCle":
			return InscriptionScolaire.staticSetFormInscriptionCle(requeteSite_, o);
		case "utilisateurCles":
			return InscriptionScolaire.staticSetUtilisateurCles(requeteSite_, o);
		case "scolaireTri":
			return InscriptionScolaire.staticSetScolaireTri(requeteSite_, o);
		case "ecoleTri":
			return InscriptionScolaire.staticSetEcoleTri(requeteSite_, o);
		case "anneeTri":
			return InscriptionScolaire.staticSetAnneeTri(requeteSite_, o);
		case "saisonTri":
			return InscriptionScolaire.staticSetSaisonTri(requeteSite_, o);
		case "sessionTri":
			return InscriptionScolaire.staticSetSessionTri(requeteSite_, o);
		case "ageTri":
			return InscriptionScolaire.staticSetAgeTri(requeteSite_, o);
		case "enfantPrenom":
			return InscriptionScolaire.staticSetEnfantPrenom(requeteSite_, o);
		case "enfantPrenomPrefere":
			return InscriptionScolaire.staticSetEnfantPrenomPrefere(requeteSite_, o);
		case "enfantFamilleNom":
			return InscriptionScolaire.staticSetEnfantFamilleNom(requeteSite_, o);
		case "merePrenom":
			return InscriptionScolaire.staticSetMerePrenom(requeteSite_, o);
		case "merePrenomPrefere":
			return InscriptionScolaire.staticSetMerePrenomPrefere(requeteSite_, o);
		case "mereNomCompletPrefere":
			return InscriptionScolaire.staticSetMereNomCompletPrefere(requeteSite_, o);
		case "perePrenom":
			return InscriptionScolaire.staticSetPerePrenom(requeteSite_, o);
		case "perePrenomPrefere":
			return InscriptionScolaire.staticSetPerePrenomPrefere(requeteSite_, o);
		case "pereNomCompletPrefere":
			return InscriptionScolaire.staticSetPereNomCompletPrefere(requeteSite_, o);
		case "enfantNomComplet":
			return InscriptionScolaire.staticSetEnfantNomComplet(requeteSite_, o);
		case "enfantNomCompletPrefere":
			return InscriptionScolaire.staticSetEnfantNomCompletPrefere(requeteSite_, o);
		case "enfantDateNaissance":
			return InscriptionScolaire.staticSetEnfantDateNaissance(requeteSite_, o);
		case "enfantDateNaissanceDAnnee":
			return InscriptionScolaire.staticSetEnfantDateNaissanceDAnnee(requeteSite_, o);
		case "enfantDateNaissanceMoisDAnnee":
			return InscriptionScolaire.staticSetEnfantDateNaissanceMoisDAnnee(requeteSite_, o);
		case "enfantDateNaissanceJourDeSemaine":
			return InscriptionScolaire.staticSetEnfantDateNaissanceJourDeSemaine(requeteSite_, o);
		case "enfantMoisNaissance":
			return InscriptionScolaire.staticSetEnfantMoisNaissance(requeteSite_, o);
		case "enfantJourNaissance":
			return InscriptionScolaire.staticSetEnfantJourNaissance(requeteSite_, o);
		case "ecoleNom":
			return InscriptionScolaire.staticSetEcoleNom(requeteSite_, o);
		case "ecoleNomComplet":
			return InscriptionScolaire.staticSetEcoleNomComplet(requeteSite_, o);
		case "ecoleEmplacement":
			return InscriptionScolaire.staticSetEcoleEmplacement(requeteSite_, o);
		case "ecoleAddresse":
			return InscriptionScolaire.staticSetEcoleAddresse(requeteSite_, o);
		case "ecoleNumeroTelephone":
			return InscriptionScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o);
		case "ecoleForm":
			return InscriptionScolaire.staticSetEcoleForm(requeteSite_, o);
		case "ecoleNumero":
			return InscriptionScolaire.staticSetEcoleNumero(requeteSite_, o);
		case "ecoleAdministrateurNom":
			return InscriptionScolaire.staticSetEcoleAdministrateurNom(requeteSite_, o);
		case "anneeDebut":
			return InscriptionScolaire.staticSetAnneeDebut(requeteSite_, o);
		case "anneeFin":
			return InscriptionScolaire.staticSetAnneeFin(requeteSite_, o);
		case "saisonDateDebut":
			return InscriptionScolaire.staticSetSaisonDateDebut(requeteSite_, o);
		case "anneeFraisInscription":
			return InscriptionScolaire.staticSetAnneeFraisInscription(requeteSite_, o);
		case "sessionDateDebut":
			return InscriptionScolaire.staticSetSessionDateDebut(requeteSite_, o);
		case "sessionDateFin":
			return InscriptionScolaire.staticSetSessionDateFin(requeteSite_, o);
		case "ageNomComplet":
			return InscriptionScolaire.staticSetAgeNomComplet(requeteSite_, o);
		case "ageDebut":
			return InscriptionScolaire.staticSetAgeDebut(requeteSite_, o);
		case "ageFin":
			return InscriptionScolaire.staticSetAgeFin(requeteSite_, o);
		case "blocHeureDebut":
			return InscriptionScolaire.staticSetBlocHeureDebut(requeteSite_, o);
		case "blocHeureFin":
			return InscriptionScolaire.staticSetBlocHeureFin(requeteSite_, o);
		case "blocPrixParMois":
			return InscriptionScolaire.staticSetBlocPrixParMois(requeteSite_, o);
		case "blocDimanche":
			return InscriptionScolaire.staticSetBlocDimanche(requeteSite_, o);
		case "blocLundi":
			return InscriptionScolaire.staticSetBlocLundi(requeteSite_, o);
		case "blocMardi":
			return InscriptionScolaire.staticSetBlocMardi(requeteSite_, o);
		case "blocMercredi":
			return InscriptionScolaire.staticSetBlocMercredi(requeteSite_, o);
		case "blocJeudi":
			return InscriptionScolaire.staticSetBlocJeudi(requeteSite_, o);
		case "blocVendredi":
			return InscriptionScolaire.staticSetBlocVendredi(requeteSite_, o);
		case "blocSamedi":
			return InscriptionScolaire.staticSetBlocSamedi(requeteSite_, o);
		case "blocPrixTotal":
			return InscriptionScolaire.staticSetBlocPrixTotal(requeteSite_, o);
		case "blocNomAdmin":
			return InscriptionScolaire.staticSetBlocNomAdmin(requeteSite_, o);
		case "blocNomCourt":
			return InscriptionScolaire.staticSetBlocNomCourt(requeteSite_, o);
		case "blocNomComplet":
			return InscriptionScolaire.staticSetBlocNomComplet(requeteSite_, o);
		case "inscriptionApprouve":
			return InscriptionScolaire.staticSetInscriptionApprouve(requeteSite_, o);
		case "inscriptionImmunisations":
			return InscriptionScolaire.staticSetInscriptionImmunisations(requeteSite_, o);
		case "photo":
			return InscriptionScolaire.staticSetPhoto(requeteSite_, o);
		case "familleMarie":
			return InscriptionScolaire.staticSetFamilleMarie(requeteSite_, o);
		case "familleSepare":
			return InscriptionScolaire.staticSetFamilleSepare(requeteSite_, o);
		case "familleDivorce":
			return InscriptionScolaire.staticSetFamilleDivorce(requeteSite_, o);
		case "familleAddresse":
			return InscriptionScolaire.staticSetFamilleAddresse(requeteSite_, o);
		case "familleCommentVousConnaissezEcole":
			return InscriptionScolaire.staticSetFamilleCommentVousConnaissezEcole(requeteSite_, o);
		case "inscriptionConsiderationsSpeciales":
			return InscriptionScolaire.staticSetInscriptionConsiderationsSpeciales(requeteSite_, o);
		case "enfantConditionsMedicales":
			return InscriptionScolaire.staticSetEnfantConditionsMedicales(requeteSite_, o);
		case "enfantEcolesPrecedemmentFrequentees":
			return InscriptionScolaire.staticSetEnfantEcolesPrecedemmentFrequentees(requeteSite_, o);
		case "enfantDescription":
			return InscriptionScolaire.staticSetEnfantDescription(requeteSite_, o);
		case "enfantObjectifs":
			return InscriptionScolaire.staticSetEnfantObjectifs(requeteSite_, o);
		case "enfantPropre":
			return InscriptionScolaire.staticSetEnfantPropre(requeteSite_, o);
		case "inscriptionNomGroupe":
			return InscriptionScolaire.staticSetInscriptionNomGroupe(requeteSite_, o);
		case "inscriptionCouleurGroupe":
			return InscriptionScolaire.staticSetInscriptionCouleurGroupe(requeteSite_, o);
		case "inscriptionPaimentChaqueMois":
			return InscriptionScolaire.staticSetInscriptionPaimentChaqueMois(requeteSite_, o);
		case "inscriptionPaimentComplet":
			return InscriptionScolaire.staticSetInscriptionPaimentComplet(requeteSite_, o);
		case "customerProfileId":
			return InscriptionScolaire.staticSetCustomerProfileId(requeteSite_, o);
		case "inscriptionDateFrais":
			return InscriptionScolaire.staticSetInscriptionDateFrais(requeteSite_, o);
		case "paiementLastDate":
			return InscriptionScolaire.staticSetPaiementLastDate(requeteSite_, o);
		case "paiementLastStr":
			return InscriptionScolaire.staticSetPaiementLastStr(requeteSite_, o);
		case "paiementMontant":
			return InscriptionScolaire.staticSetPaiementMontant(requeteSite_, o);
		case "fraisMontant":
			return InscriptionScolaire.staticSetFraisMontant(requeteSite_, o);
		case "fraisMontantFuture":
			return InscriptionScolaire.staticSetFraisMontantFuture(requeteSite_, o);
		case "fraisMontantDu":
			return InscriptionScolaire.staticSetFraisMontantDu(requeteSite_, o);
		case "fraisMontantNonPasse":
			return InscriptionScolaire.staticSetFraisMontantNonPasse(requeteSite_, o);
		case "fraisMaintenant":
			return InscriptionScolaire.staticSetFraisMaintenant(requeteSite_, o);
		case "paiementsAJour":
			return InscriptionScolaire.staticSetPaiementsAJour(requeteSite_, o);
		case "paiementsEnRetard":
			return InscriptionScolaire.staticSetPaiementsEnRetard(requeteSite_, o);
		case "paiementsEnRetardMontant":
			return InscriptionScolaire.staticSetPaiementsEnRetardMontant(requeteSite_, o);
		case "paiementsEnAvance":
			return InscriptionScolaire.staticSetPaiementsEnAvance(requeteSite_, o);
		case "paiementsEnSouffrance":
			return InscriptionScolaire.staticSetPaiementsEnSouffrance(requeteSite_, o);
		case "paiementsEnSouffranceMontant":
			return InscriptionScolaire.staticSetPaiementsEnSouffranceMontant(requeteSite_, o);
		case "fraisCrees":
			return InscriptionScolaire.staticSetFraisCrees(requeteSite_, o);
		case "creeDAnnee":
			return InscriptionScolaire.staticSetCreeDAnnee(requeteSite_, o);
		case "creeJourDeSemaine":
			return InscriptionScolaire.staticSetCreeJourDeSemaine(requeteSite_, o);
		case "creeMoisDAnnee":
			return InscriptionScolaire.staticSetCreeMoisDAnnee(requeteSite_, o);
		case "creeHeureDuJour":
			return InscriptionScolaire.staticSetCreeHeureDuJour(requeteSite_, o);
		case "inscriptionJoursDeSemaine":
			return InscriptionScolaire.staticSetInscriptionJoursDeSemaine(requeteSite_, o);
		case "inscriptionNomsParents":
			return InscriptionScolaire.staticSetInscriptionNomsParents(requeteSite_, o);
		case "inscriptionMails":
			return InscriptionScolaire.staticSetInscriptionMails(requeteSite_, o);
		case "inscriptionMail":
			return InscriptionScolaire.staticSetInscriptionMail(requeteSite_, o);
		case "inscriptionMailsParents":
			return InscriptionScolaire.staticSetInscriptionMailsParents(requeteSite_, o);
		case "inscriptionNumeroTelephones":
			return InscriptionScolaire.staticSetInscriptionNumeroTelephones(requeteSite_, o);
		case "inscriptionNumeroTelephone":
			return InscriptionScolaire.staticSetInscriptionNumeroTelephone(requeteSite_, o);
		case "inscriptionNomParent":
			return InscriptionScolaire.staticSetInscriptionNomParent(requeteSite_, o);
		case "inscriptionNomParentLignes":
			return InscriptionScolaire.staticSetInscriptionNomParentLignes(requeteSite_, o);
		case "inscriptionMailParentLignes":
			return InscriptionScolaire.staticSetInscriptionMailParentLignes(requeteSite_, o);
		case "inscriptionDetailParentLignes":
			return InscriptionScolaire.staticSetInscriptionDetailParentLignes(requeteSite_, o);
		case "inscriptionChercherParentLignes":
			return InscriptionScolaire.staticSetInscriptionChercherParentLignes(requeteSite_, o);
		case "inscriptionContactUrgenceParentLignes":
			return InscriptionScolaire.staticSetInscriptionContactUrgenceParentLignes(requeteSite_, o);
		case "inscriptionSignature1":
			return InscriptionScolaire.staticSetInscriptionSignature1(requeteSite_, o);
		case "inscriptionSignature2":
			return InscriptionScolaire.staticSetInscriptionSignature2(requeteSite_, o);
		case "inscriptionSignature3":
			return InscriptionScolaire.staticSetInscriptionSignature3(requeteSite_, o);
		case "inscriptionSignature4":
			return InscriptionScolaire.staticSetInscriptionSignature4(requeteSite_, o);
		case "inscriptionSignature5":
			return InscriptionScolaire.staticSetInscriptionSignature5(requeteSite_, o);
		case "inscriptionSignature6":
			return InscriptionScolaire.staticSetInscriptionSignature6(requeteSite_, o);
		case "inscriptionSignature7":
			return InscriptionScolaire.staticSetInscriptionSignature7(requeteSite_, o);
		case "inscriptionSignature8":
			return InscriptionScolaire.staticSetInscriptionSignature8(requeteSite_, o);
		case "inscriptionSignature9":
			return InscriptionScolaire.staticSetInscriptionSignature9(requeteSite_, o);
		case "inscriptionSignature10":
			return InscriptionScolaire.staticSetInscriptionSignature10(requeteSite_, o);
		case "inscriptionDate1":
			return InscriptionScolaire.staticSetInscriptionDate1(requeteSite_, o);
		case "inscriptionDate2":
			return InscriptionScolaire.staticSetInscriptionDate2(requeteSite_, o);
		case "inscriptionDate3":
			return InscriptionScolaire.staticSetInscriptionDate3(requeteSite_, o);
		case "inscriptionDate4":
			return InscriptionScolaire.staticSetInscriptionDate4(requeteSite_, o);
		case "inscriptionDate5":
			return InscriptionScolaire.staticSetInscriptionDate5(requeteSite_, o);
		case "inscriptionDate6":
			return InscriptionScolaire.staticSetInscriptionDate6(requeteSite_, o);
		case "inscriptionDate7":
			return InscriptionScolaire.staticSetInscriptionDate7(requeteSite_, o);
		case "inscriptionDate8":
			return InscriptionScolaire.staticSetInscriptionDate8(requeteSite_, o);
		case "inscriptionDate9":
			return InscriptionScolaire.staticSetInscriptionDate9(requeteSite_, o);
		case "inscriptionDate10":
			return InscriptionScolaire.staticSetInscriptionDate10(requeteSite_, o);
		case "enfantImmunisationsRecu":
			return InscriptionScolaire.staticSetEnfantImmunisationsRecu(requeteSite_, o);
		case "enfantPhotosApprouve":
			return InscriptionScolaire.staticSetEnfantPhotosApprouve(requeteSite_, o);
		case "inscriptionNumero":
			return InscriptionScolaire.staticSetInscriptionNumero(requeteSite_, o);
		case "inscriptionNomComplet":
			return InscriptionScolaire.staticSetInscriptionNomComplet(requeteSite_, o);
			default:
				return Cluster.staticSetCluster(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrInscriptionScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrInscriptionScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "inscriptionCle":
			return InscriptionScolaire.staticSolrInscriptionCle(requeteSite_, (Long)o);
		case "anneeCle":
			return InscriptionScolaire.staticSolrAnneeCle(requeteSite_, (Long)o);
		case "blocCles":
			return InscriptionScolaire.staticSolrBlocCles(requeteSite_, (Long)o);
		case "ecoleCle":
			return InscriptionScolaire.staticSolrEcoleCle(requeteSite_, (Long)o);
		case "sessionCle":
			return InscriptionScolaire.staticSolrSessionCle(requeteSite_, (Long)o);
		case "ageCle":
			return InscriptionScolaire.staticSolrAgeCle(requeteSite_, (Long)o);
		case "blocCle":
			return InscriptionScolaire.staticSolrBlocCle(requeteSite_, (Long)o);
		case "enfantCle":
			return InscriptionScolaire.staticSolrEnfantCle(requeteSite_, (Long)o);
		case "mereCles":
			return InscriptionScolaire.staticSolrMereCles(requeteSite_, (Long)o);
		case "pereCles":
			return InscriptionScolaire.staticSolrPereCles(requeteSite_, (Long)o);
		case "gardienCles":
			return InscriptionScolaire.staticSolrGardienCles(requeteSite_, (Long)o);
		case "paiementCles":
			return InscriptionScolaire.staticSolrPaiementCles(requeteSite_, (Long)o);
		case "formInscriptionCle":
			return InscriptionScolaire.staticSolrFormInscriptionCle(requeteSite_, (Long)o);
		case "utilisateurCles":
			return InscriptionScolaire.staticSolrUtilisateurCles(requeteSite_, (Long)o);
		case "scolaireTri":
			return InscriptionScolaire.staticSolrScolaireTri(requeteSite_, (Integer)o);
		case "ecoleTri":
			return InscriptionScolaire.staticSolrEcoleTri(requeteSite_, (Integer)o);
		case "anneeTri":
			return InscriptionScolaire.staticSolrAnneeTri(requeteSite_, (Integer)o);
		case "saisonTri":
			return InscriptionScolaire.staticSolrSaisonTri(requeteSite_, (Integer)o);
		case "sessionTri":
			return InscriptionScolaire.staticSolrSessionTri(requeteSite_, (Integer)o);
		case "ageTri":
			return InscriptionScolaire.staticSolrAgeTri(requeteSite_, (Integer)o);
		case "enfantPrenom":
			return InscriptionScolaire.staticSolrEnfantPrenom(requeteSite_, (String)o);
		case "enfantPrenomPrefere":
			return InscriptionScolaire.staticSolrEnfantPrenomPrefere(requeteSite_, (String)o);
		case "enfantFamilleNom":
			return InscriptionScolaire.staticSolrEnfantFamilleNom(requeteSite_, (String)o);
		case "merePrenom":
			return InscriptionScolaire.staticSolrMerePrenom(requeteSite_, (String)o);
		case "merePrenomPrefere":
			return InscriptionScolaire.staticSolrMerePrenomPrefere(requeteSite_, (String)o);
		case "mereNomCompletPrefere":
			return InscriptionScolaire.staticSolrMereNomCompletPrefere(requeteSite_, (String)o);
		case "perePrenom":
			return InscriptionScolaire.staticSolrPerePrenom(requeteSite_, (String)o);
		case "perePrenomPrefere":
			return InscriptionScolaire.staticSolrPerePrenomPrefere(requeteSite_, (String)o);
		case "pereNomCompletPrefere":
			return InscriptionScolaire.staticSolrPereNomCompletPrefere(requeteSite_, (String)o);
		case "enfantNomComplet":
			return InscriptionScolaire.staticSolrEnfantNomComplet(requeteSite_, (String)o);
		case "enfantNomCompletPrefere":
			return InscriptionScolaire.staticSolrEnfantNomCompletPrefere(requeteSite_, (String)o);
		case "enfantDateNaissance":
			return InscriptionScolaire.staticSolrEnfantDateNaissance(requeteSite_, (LocalDate)o);
		case "enfantDateNaissanceDAnnee":
			return InscriptionScolaire.staticSolrEnfantDateNaissanceDAnnee(requeteSite_, (Integer)o);
		case "enfantDateNaissanceMoisDAnnee":
			return InscriptionScolaire.staticSolrEnfantDateNaissanceMoisDAnnee(requeteSite_, (String)o);
		case "enfantDateNaissanceJourDeSemaine":
			return InscriptionScolaire.staticSolrEnfantDateNaissanceJourDeSemaine(requeteSite_, (String)o);
		case "enfantMoisNaissance":
			return InscriptionScolaire.staticSolrEnfantMoisNaissance(requeteSite_, (Integer)o);
		case "enfantJourNaissance":
			return InscriptionScolaire.staticSolrEnfantJourNaissance(requeteSite_, (Integer)o);
		case "ecoleNom":
			return InscriptionScolaire.staticSolrEcoleNom(requeteSite_, (String)o);
		case "ecoleNomComplet":
			return InscriptionScolaire.staticSolrEcoleNomComplet(requeteSite_, (String)o);
		case "ecoleEmplacement":
			return InscriptionScolaire.staticSolrEcoleEmplacement(requeteSite_, (String)o);
		case "ecoleAddresse":
			return InscriptionScolaire.staticSolrEcoleAddresse(requeteSite_, (String)o);
		case "ecoleNumeroTelephone":
			return InscriptionScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, (String)o);
		case "ecoleForm":
			return InscriptionScolaire.staticSolrEcoleForm(requeteSite_, (String)o);
		case "ecoleNumero":
			return InscriptionScolaire.staticSolrEcoleNumero(requeteSite_, (Integer)o);
		case "ecoleAdministrateurNom":
			return InscriptionScolaire.staticSolrEcoleAdministrateurNom(requeteSite_, (String)o);
		case "anneeDebut":
			return InscriptionScolaire.staticSolrAnneeDebut(requeteSite_, (Integer)o);
		case "anneeFin":
			return InscriptionScolaire.staticSolrAnneeFin(requeteSite_, (Integer)o);
		case "saisonDateDebut":
			return InscriptionScolaire.staticSolrSaisonDateDebut(requeteSite_, (LocalDate)o);
		case "anneeFraisInscription":
			return InscriptionScolaire.staticSolrAnneeFraisInscription(requeteSite_, (BigDecimal)o);
		case "sessionDateDebut":
			return InscriptionScolaire.staticSolrSessionDateDebut(requeteSite_, (LocalDate)o);
		case "sessionDateFin":
			return InscriptionScolaire.staticSolrSessionDateFin(requeteSite_, (LocalDate)o);
		case "ageNomComplet":
			return InscriptionScolaire.staticSolrAgeNomComplet(requeteSite_, (String)o);
		case "ageDebut":
			return InscriptionScolaire.staticSolrAgeDebut(requeteSite_, (Integer)o);
		case "ageFin":
			return InscriptionScolaire.staticSolrAgeFin(requeteSite_, (Integer)o);
		case "blocHeureDebut":
			return InscriptionScolaire.staticSolrBlocHeureDebut(requeteSite_, (LocalTime)o);
		case "blocHeureFin":
			return InscriptionScolaire.staticSolrBlocHeureFin(requeteSite_, (LocalTime)o);
		case "blocPrixParMois":
			return InscriptionScolaire.staticSolrBlocPrixParMois(requeteSite_, (BigDecimal)o);
		case "blocDimanche":
			return InscriptionScolaire.staticSolrBlocDimanche(requeteSite_, (Boolean)o);
		case "blocLundi":
			return InscriptionScolaire.staticSolrBlocLundi(requeteSite_, (Boolean)o);
		case "blocMardi":
			return InscriptionScolaire.staticSolrBlocMardi(requeteSite_, (Boolean)o);
		case "blocMercredi":
			return InscriptionScolaire.staticSolrBlocMercredi(requeteSite_, (Boolean)o);
		case "blocJeudi":
			return InscriptionScolaire.staticSolrBlocJeudi(requeteSite_, (Boolean)o);
		case "blocVendredi":
			return InscriptionScolaire.staticSolrBlocVendredi(requeteSite_, (Boolean)o);
		case "blocSamedi":
			return InscriptionScolaire.staticSolrBlocSamedi(requeteSite_, (Boolean)o);
		case "blocPrixTotal":
			return InscriptionScolaire.staticSolrBlocPrixTotal(requeteSite_, (BigDecimal)o);
		case "blocNomAdmin":
			return InscriptionScolaire.staticSolrBlocNomAdmin(requeteSite_, (String)o);
		case "blocNomCourt":
			return InscriptionScolaire.staticSolrBlocNomCourt(requeteSite_, (String)o);
		case "blocNomComplet":
			return InscriptionScolaire.staticSolrBlocNomComplet(requeteSite_, (String)o);
		case "inscriptionApprouve":
			return InscriptionScolaire.staticSolrInscriptionApprouve(requeteSite_, (Boolean)o);
		case "inscriptionImmunisations":
			return InscriptionScolaire.staticSolrInscriptionImmunisations(requeteSite_, (Boolean)o);
		case "photo":
			return InscriptionScolaire.staticSolrPhoto(requeteSite_, (String)o);
		case "familleMarie":
			return InscriptionScolaire.staticSolrFamilleMarie(requeteSite_, (Boolean)o);
		case "familleSepare":
			return InscriptionScolaire.staticSolrFamilleSepare(requeteSite_, (Boolean)o);
		case "familleDivorce":
			return InscriptionScolaire.staticSolrFamilleDivorce(requeteSite_, (Boolean)o);
		case "familleAddresse":
			return InscriptionScolaire.staticSolrFamilleAddresse(requeteSite_, (String)o);
		case "familleCommentVousConnaissezEcole":
			return InscriptionScolaire.staticSolrFamilleCommentVousConnaissezEcole(requeteSite_, (String)o);
		case "inscriptionConsiderationsSpeciales":
			return InscriptionScolaire.staticSolrInscriptionConsiderationsSpeciales(requeteSite_, (String)o);
		case "enfantConditionsMedicales":
			return InscriptionScolaire.staticSolrEnfantConditionsMedicales(requeteSite_, (String)o);
		case "enfantEcolesPrecedemmentFrequentees":
			return InscriptionScolaire.staticSolrEnfantEcolesPrecedemmentFrequentees(requeteSite_, (String)o);
		case "enfantDescription":
			return InscriptionScolaire.staticSolrEnfantDescription(requeteSite_, (String)o);
		case "enfantObjectifs":
			return InscriptionScolaire.staticSolrEnfantObjectifs(requeteSite_, (String)o);
		case "enfantPropre":
			return InscriptionScolaire.staticSolrEnfantPropre(requeteSite_, (Boolean)o);
		case "inscriptionNomGroupe":
			return InscriptionScolaire.staticSolrInscriptionNomGroupe(requeteSite_, (String)o);
		case "inscriptionCouleurGroupe":
			return InscriptionScolaire.staticSolrInscriptionCouleurGroupe(requeteSite_, (String)o);
		case "inscriptionPaimentChaqueMois":
			return InscriptionScolaire.staticSolrInscriptionPaimentChaqueMois(requeteSite_, (Boolean)o);
		case "inscriptionPaimentComplet":
			return InscriptionScolaire.staticSolrInscriptionPaimentComplet(requeteSite_, (Boolean)o);
		case "customerProfileId":
			return InscriptionScolaire.staticSolrCustomerProfileId(requeteSite_, (String)o);
		case "inscriptionDateFrais":
			return InscriptionScolaire.staticSolrInscriptionDateFrais(requeteSite_, (LocalDate)o);
		case "paiementLastDate":
			return InscriptionScolaire.staticSolrPaiementLastDate(requeteSite_, (LocalDate)o);
		case "paiementLastStr":
			return InscriptionScolaire.staticSolrPaiementLastStr(requeteSite_, (String)o);
		case "paiementMontant":
			return InscriptionScolaire.staticSolrPaiementMontant(requeteSite_, (BigDecimal)o);
		case "fraisMontant":
			return InscriptionScolaire.staticSolrFraisMontant(requeteSite_, (BigDecimal)o);
		case "fraisMontantFuture":
			return InscriptionScolaire.staticSolrFraisMontantFuture(requeteSite_, (BigDecimal)o);
		case "fraisMontantDu":
			return InscriptionScolaire.staticSolrFraisMontantDu(requeteSite_, (BigDecimal)o);
		case "fraisMontantNonPasse":
			return InscriptionScolaire.staticSolrFraisMontantNonPasse(requeteSite_, (BigDecimal)o);
		case "fraisMaintenant":
			return InscriptionScolaire.staticSolrFraisMaintenant(requeteSite_, (BigDecimal)o);
		case "paiementsAJour":
			return InscriptionScolaire.staticSolrPaiementsAJour(requeteSite_, (Boolean)o);
		case "paiementsEnRetard":
			return InscriptionScolaire.staticSolrPaiementsEnRetard(requeteSite_, (Boolean)o);
		case "paiementsEnRetardMontant":
			return InscriptionScolaire.staticSolrPaiementsEnRetardMontant(requeteSite_, (BigDecimal)o);
		case "paiementsEnAvance":
			return InscriptionScolaire.staticSolrPaiementsEnAvance(requeteSite_, (Boolean)o);
		case "paiementsEnSouffrance":
			return InscriptionScolaire.staticSolrPaiementsEnSouffrance(requeteSite_, (Boolean)o);
		case "paiementsEnSouffranceMontant":
			return InscriptionScolaire.staticSolrPaiementsEnSouffranceMontant(requeteSite_, (BigDecimal)o);
		case "fraisCrees":
			return InscriptionScolaire.staticSolrFraisCrees(requeteSite_, (Boolean)o);
		case "creeDAnnee":
			return InscriptionScolaire.staticSolrCreeDAnnee(requeteSite_, (Integer)o);
		case "creeJourDeSemaine":
			return InscriptionScolaire.staticSolrCreeJourDeSemaine(requeteSite_, (String)o);
		case "creeMoisDAnnee":
			return InscriptionScolaire.staticSolrCreeMoisDAnnee(requeteSite_, (String)o);
		case "creeHeureDuJour":
			return InscriptionScolaire.staticSolrCreeHeureDuJour(requeteSite_, (String)o);
		case "inscriptionJoursDeSemaine":
			return InscriptionScolaire.staticSolrInscriptionJoursDeSemaine(requeteSite_, (String)o);
		case "inscriptionNomsParents":
			return InscriptionScolaire.staticSolrInscriptionNomsParents(requeteSite_, (String)o);
		case "inscriptionMails":
			return InscriptionScolaire.staticSolrInscriptionMails(requeteSite_, (String)o);
		case "inscriptionMail":
			return InscriptionScolaire.staticSolrInscriptionMail(requeteSite_, (String)o);
		case "inscriptionMailsParents":
			return InscriptionScolaire.staticSolrInscriptionMailsParents(requeteSite_, (String)o);
		case "inscriptionNumeroTelephones":
			return InscriptionScolaire.staticSolrInscriptionNumeroTelephones(requeteSite_, (String)o);
		case "inscriptionNumeroTelephone":
			return InscriptionScolaire.staticSolrInscriptionNumeroTelephone(requeteSite_, (String)o);
		case "inscriptionNomParent":
			return InscriptionScolaire.staticSolrInscriptionNomParent(requeteSite_, (String)o);
		case "inscriptionNomParentLignes":
			return InscriptionScolaire.staticSolrInscriptionNomParentLignes(requeteSite_, (String)o);
		case "inscriptionMailParentLignes":
			return InscriptionScolaire.staticSolrInscriptionMailParentLignes(requeteSite_, (String)o);
		case "inscriptionDetailParentLignes":
			return InscriptionScolaire.staticSolrInscriptionDetailParentLignes(requeteSite_, (String)o);
		case "inscriptionChercherParentLignes":
			return InscriptionScolaire.staticSolrInscriptionChercherParentLignes(requeteSite_, (String)o);
		case "inscriptionContactUrgenceParentLignes":
			return InscriptionScolaire.staticSolrInscriptionContactUrgenceParentLignes(requeteSite_, (String)o);
		case "inscriptionSignature1":
			return InscriptionScolaire.staticSolrInscriptionSignature1(requeteSite_, (String)o);
		case "inscriptionSignature2":
			return InscriptionScolaire.staticSolrInscriptionSignature2(requeteSite_, (String)o);
		case "inscriptionSignature3":
			return InscriptionScolaire.staticSolrInscriptionSignature3(requeteSite_, (String)o);
		case "inscriptionSignature4":
			return InscriptionScolaire.staticSolrInscriptionSignature4(requeteSite_, (String)o);
		case "inscriptionSignature5":
			return InscriptionScolaire.staticSolrInscriptionSignature5(requeteSite_, (String)o);
		case "inscriptionSignature6":
			return InscriptionScolaire.staticSolrInscriptionSignature6(requeteSite_, (String)o);
		case "inscriptionSignature7":
			return InscriptionScolaire.staticSolrInscriptionSignature7(requeteSite_, (String)o);
		case "inscriptionSignature8":
			return InscriptionScolaire.staticSolrInscriptionSignature8(requeteSite_, (String)o);
		case "inscriptionSignature9":
			return InscriptionScolaire.staticSolrInscriptionSignature9(requeteSite_, (String)o);
		case "inscriptionSignature10":
			return InscriptionScolaire.staticSolrInscriptionSignature10(requeteSite_, (String)o);
		case "inscriptionDate1":
			return InscriptionScolaire.staticSolrInscriptionDate1(requeteSite_, (LocalDate)o);
		case "inscriptionDate2":
			return InscriptionScolaire.staticSolrInscriptionDate2(requeteSite_, (LocalDate)o);
		case "inscriptionDate3":
			return InscriptionScolaire.staticSolrInscriptionDate3(requeteSite_, (LocalDate)o);
		case "inscriptionDate4":
			return InscriptionScolaire.staticSolrInscriptionDate4(requeteSite_, (LocalDate)o);
		case "inscriptionDate5":
			return InscriptionScolaire.staticSolrInscriptionDate5(requeteSite_, (LocalDate)o);
		case "inscriptionDate6":
			return InscriptionScolaire.staticSolrInscriptionDate6(requeteSite_, (LocalDate)o);
		case "inscriptionDate7":
			return InscriptionScolaire.staticSolrInscriptionDate7(requeteSite_, (LocalDate)o);
		case "inscriptionDate8":
			return InscriptionScolaire.staticSolrInscriptionDate8(requeteSite_, (LocalDate)o);
		case "inscriptionDate9":
			return InscriptionScolaire.staticSolrInscriptionDate9(requeteSite_, (LocalDate)o);
		case "inscriptionDate10":
			return InscriptionScolaire.staticSolrInscriptionDate10(requeteSite_, (LocalDate)o);
		case "enfantImmunisationsRecu":
			return InscriptionScolaire.staticSolrEnfantImmunisationsRecu(requeteSite_, (String)o);
		case "enfantPhotosApprouve":
			return InscriptionScolaire.staticSolrEnfantPhotosApprouve(requeteSite_, (String)o);
		case "inscriptionNumero":
			return InscriptionScolaire.staticSolrInscriptionNumero(requeteSite_, (Integer)o);
		case "inscriptionNomComplet":
			return InscriptionScolaire.staticSolrInscriptionNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrCluster(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrInscriptionScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrInscriptionScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "inscriptionCle":
			return InscriptionScolaire.staticSolrStrInscriptionCle(requeteSite_, (Long)o);
		case "anneeCle":
			return InscriptionScolaire.staticSolrStrAnneeCle(requeteSite_, (Long)o);
		case "blocCles":
			return InscriptionScolaire.staticSolrStrBlocCles(requeteSite_, (Long)o);
		case "ecoleCle":
			return InscriptionScolaire.staticSolrStrEcoleCle(requeteSite_, (Long)o);
		case "sessionCle":
			return InscriptionScolaire.staticSolrStrSessionCle(requeteSite_, (Long)o);
		case "ageCle":
			return InscriptionScolaire.staticSolrStrAgeCle(requeteSite_, (Long)o);
		case "blocCle":
			return InscriptionScolaire.staticSolrStrBlocCle(requeteSite_, (Long)o);
		case "enfantCle":
			return InscriptionScolaire.staticSolrStrEnfantCle(requeteSite_, (Long)o);
		case "mereCles":
			return InscriptionScolaire.staticSolrStrMereCles(requeteSite_, (Long)o);
		case "pereCles":
			return InscriptionScolaire.staticSolrStrPereCles(requeteSite_, (Long)o);
		case "gardienCles":
			return InscriptionScolaire.staticSolrStrGardienCles(requeteSite_, (Long)o);
		case "paiementCles":
			return InscriptionScolaire.staticSolrStrPaiementCles(requeteSite_, (Long)o);
		case "formInscriptionCle":
			return InscriptionScolaire.staticSolrStrFormInscriptionCle(requeteSite_, (Long)o);
		case "utilisateurCles":
			return InscriptionScolaire.staticSolrStrUtilisateurCles(requeteSite_, (Long)o);
		case "scolaireTri":
			return InscriptionScolaire.staticSolrStrScolaireTri(requeteSite_, (Integer)o);
		case "ecoleTri":
			return InscriptionScolaire.staticSolrStrEcoleTri(requeteSite_, (Integer)o);
		case "anneeTri":
			return InscriptionScolaire.staticSolrStrAnneeTri(requeteSite_, (Integer)o);
		case "saisonTri":
			return InscriptionScolaire.staticSolrStrSaisonTri(requeteSite_, (Integer)o);
		case "sessionTri":
			return InscriptionScolaire.staticSolrStrSessionTri(requeteSite_, (Integer)o);
		case "ageTri":
			return InscriptionScolaire.staticSolrStrAgeTri(requeteSite_, (Integer)o);
		case "enfantPrenom":
			return InscriptionScolaire.staticSolrStrEnfantPrenom(requeteSite_, (String)o);
		case "enfantPrenomPrefere":
			return InscriptionScolaire.staticSolrStrEnfantPrenomPrefere(requeteSite_, (String)o);
		case "enfantFamilleNom":
			return InscriptionScolaire.staticSolrStrEnfantFamilleNom(requeteSite_, (String)o);
		case "merePrenom":
			return InscriptionScolaire.staticSolrStrMerePrenom(requeteSite_, (String)o);
		case "merePrenomPrefere":
			return InscriptionScolaire.staticSolrStrMerePrenomPrefere(requeteSite_, (String)o);
		case "mereNomCompletPrefere":
			return InscriptionScolaire.staticSolrStrMereNomCompletPrefere(requeteSite_, (String)o);
		case "perePrenom":
			return InscriptionScolaire.staticSolrStrPerePrenom(requeteSite_, (String)o);
		case "perePrenomPrefere":
			return InscriptionScolaire.staticSolrStrPerePrenomPrefere(requeteSite_, (String)o);
		case "pereNomCompletPrefere":
			return InscriptionScolaire.staticSolrStrPereNomCompletPrefere(requeteSite_, (String)o);
		case "enfantNomComplet":
			return InscriptionScolaire.staticSolrStrEnfantNomComplet(requeteSite_, (String)o);
		case "enfantNomCompletPrefere":
			return InscriptionScolaire.staticSolrStrEnfantNomCompletPrefere(requeteSite_, (String)o);
		case "enfantDateNaissance":
			return InscriptionScolaire.staticSolrStrEnfantDateNaissance(requeteSite_, (Date)o);
		case "enfantDateNaissanceDAnnee":
			return InscriptionScolaire.staticSolrStrEnfantDateNaissanceDAnnee(requeteSite_, (Integer)o);
		case "enfantDateNaissanceMoisDAnnee":
			return InscriptionScolaire.staticSolrStrEnfantDateNaissanceMoisDAnnee(requeteSite_, (String)o);
		case "enfantDateNaissanceJourDeSemaine":
			return InscriptionScolaire.staticSolrStrEnfantDateNaissanceJourDeSemaine(requeteSite_, (String)o);
		case "enfantMoisNaissance":
			return InscriptionScolaire.staticSolrStrEnfantMoisNaissance(requeteSite_, (Integer)o);
		case "enfantJourNaissance":
			return InscriptionScolaire.staticSolrStrEnfantJourNaissance(requeteSite_, (Integer)o);
		case "ecoleNom":
			return InscriptionScolaire.staticSolrStrEcoleNom(requeteSite_, (String)o);
		case "ecoleNomComplet":
			return InscriptionScolaire.staticSolrStrEcoleNomComplet(requeteSite_, (String)o);
		case "ecoleEmplacement":
			return InscriptionScolaire.staticSolrStrEcoleEmplacement(requeteSite_, (String)o);
		case "ecoleAddresse":
			return InscriptionScolaire.staticSolrStrEcoleAddresse(requeteSite_, (String)o);
		case "ecoleNumeroTelephone":
			return InscriptionScolaire.staticSolrStrEcoleNumeroTelephone(requeteSite_, (String)o);
		case "ecoleForm":
			return InscriptionScolaire.staticSolrStrEcoleForm(requeteSite_, (String)o);
		case "ecoleNumero":
			return InscriptionScolaire.staticSolrStrEcoleNumero(requeteSite_, (Integer)o);
		case "ecoleAdministrateurNom":
			return InscriptionScolaire.staticSolrStrEcoleAdministrateurNom(requeteSite_, (String)o);
		case "anneeDebut":
			return InscriptionScolaire.staticSolrStrAnneeDebut(requeteSite_, (Integer)o);
		case "anneeFin":
			return InscriptionScolaire.staticSolrStrAnneeFin(requeteSite_, (Integer)o);
		case "saisonDateDebut":
			return InscriptionScolaire.staticSolrStrSaisonDateDebut(requeteSite_, (Date)o);
		case "anneeFraisInscription":
			return InscriptionScolaire.staticSolrStrAnneeFraisInscription(requeteSite_, (Double)o);
		case "sessionDateDebut":
			return InscriptionScolaire.staticSolrStrSessionDateDebut(requeteSite_, (Date)o);
		case "sessionDateFin":
			return InscriptionScolaire.staticSolrStrSessionDateFin(requeteSite_, (Date)o);
		case "ageNomComplet":
			return InscriptionScolaire.staticSolrStrAgeNomComplet(requeteSite_, (String)o);
		case "ageDebut":
			return InscriptionScolaire.staticSolrStrAgeDebut(requeteSite_, (Integer)o);
		case "ageFin":
			return InscriptionScolaire.staticSolrStrAgeFin(requeteSite_, (Integer)o);
		case "blocHeureDebut":
			return InscriptionScolaire.staticSolrStrBlocHeureDebut(requeteSite_, (String)o);
		case "blocHeureFin":
			return InscriptionScolaire.staticSolrStrBlocHeureFin(requeteSite_, (String)o);
		case "blocPrixParMois":
			return InscriptionScolaire.staticSolrStrBlocPrixParMois(requeteSite_, (Double)o);
		case "blocDimanche":
			return InscriptionScolaire.staticSolrStrBlocDimanche(requeteSite_, (Boolean)o);
		case "blocLundi":
			return InscriptionScolaire.staticSolrStrBlocLundi(requeteSite_, (Boolean)o);
		case "blocMardi":
			return InscriptionScolaire.staticSolrStrBlocMardi(requeteSite_, (Boolean)o);
		case "blocMercredi":
			return InscriptionScolaire.staticSolrStrBlocMercredi(requeteSite_, (Boolean)o);
		case "blocJeudi":
			return InscriptionScolaire.staticSolrStrBlocJeudi(requeteSite_, (Boolean)o);
		case "blocVendredi":
			return InscriptionScolaire.staticSolrStrBlocVendredi(requeteSite_, (Boolean)o);
		case "blocSamedi":
			return InscriptionScolaire.staticSolrStrBlocSamedi(requeteSite_, (Boolean)o);
		case "blocPrixTotal":
			return InscriptionScolaire.staticSolrStrBlocPrixTotal(requeteSite_, (Double)o);
		case "blocNomAdmin":
			return InscriptionScolaire.staticSolrStrBlocNomAdmin(requeteSite_, (String)o);
		case "blocNomCourt":
			return InscriptionScolaire.staticSolrStrBlocNomCourt(requeteSite_, (String)o);
		case "blocNomComplet":
			return InscriptionScolaire.staticSolrStrBlocNomComplet(requeteSite_, (String)o);
		case "inscriptionApprouve":
			return InscriptionScolaire.staticSolrStrInscriptionApprouve(requeteSite_, (Boolean)o);
		case "inscriptionImmunisations":
			return InscriptionScolaire.staticSolrStrInscriptionImmunisations(requeteSite_, (Boolean)o);
		case "photo":
			return InscriptionScolaire.staticSolrStrPhoto(requeteSite_, (String)o);
		case "familleMarie":
			return InscriptionScolaire.staticSolrStrFamilleMarie(requeteSite_, (Boolean)o);
		case "familleSepare":
			return InscriptionScolaire.staticSolrStrFamilleSepare(requeteSite_, (Boolean)o);
		case "familleDivorce":
			return InscriptionScolaire.staticSolrStrFamilleDivorce(requeteSite_, (Boolean)o);
		case "familleAddresse":
			return InscriptionScolaire.staticSolrStrFamilleAddresse(requeteSite_, (String)o);
		case "familleCommentVousConnaissezEcole":
			return InscriptionScolaire.staticSolrStrFamilleCommentVousConnaissezEcole(requeteSite_, (String)o);
		case "inscriptionConsiderationsSpeciales":
			return InscriptionScolaire.staticSolrStrInscriptionConsiderationsSpeciales(requeteSite_, (String)o);
		case "enfantConditionsMedicales":
			return InscriptionScolaire.staticSolrStrEnfantConditionsMedicales(requeteSite_, (String)o);
		case "enfantEcolesPrecedemmentFrequentees":
			return InscriptionScolaire.staticSolrStrEnfantEcolesPrecedemmentFrequentees(requeteSite_, (String)o);
		case "enfantDescription":
			return InscriptionScolaire.staticSolrStrEnfantDescription(requeteSite_, (String)o);
		case "enfantObjectifs":
			return InscriptionScolaire.staticSolrStrEnfantObjectifs(requeteSite_, (String)o);
		case "enfantPropre":
			return InscriptionScolaire.staticSolrStrEnfantPropre(requeteSite_, (Boolean)o);
		case "inscriptionNomGroupe":
			return InscriptionScolaire.staticSolrStrInscriptionNomGroupe(requeteSite_, (String)o);
		case "inscriptionCouleurGroupe":
			return InscriptionScolaire.staticSolrStrInscriptionCouleurGroupe(requeteSite_, (String)o);
		case "inscriptionPaimentChaqueMois":
			return InscriptionScolaire.staticSolrStrInscriptionPaimentChaqueMois(requeteSite_, (Boolean)o);
		case "inscriptionPaimentComplet":
			return InscriptionScolaire.staticSolrStrInscriptionPaimentComplet(requeteSite_, (Boolean)o);
		case "customerProfileId":
			return InscriptionScolaire.staticSolrStrCustomerProfileId(requeteSite_, (String)o);
		case "inscriptionDateFrais":
			return InscriptionScolaire.staticSolrStrInscriptionDateFrais(requeteSite_, (Date)o);
		case "paiementLastDate":
			return InscriptionScolaire.staticSolrStrPaiementLastDate(requeteSite_, (Date)o);
		case "paiementLastStr":
			return InscriptionScolaire.staticSolrStrPaiementLastStr(requeteSite_, (String)o);
		case "paiementMontant":
			return InscriptionScolaire.staticSolrStrPaiementMontant(requeteSite_, (Double)o);
		case "fraisMontant":
			return InscriptionScolaire.staticSolrStrFraisMontant(requeteSite_, (Double)o);
		case "fraisMontantFuture":
			return InscriptionScolaire.staticSolrStrFraisMontantFuture(requeteSite_, (Double)o);
		case "fraisMontantDu":
			return InscriptionScolaire.staticSolrStrFraisMontantDu(requeteSite_, (Double)o);
		case "fraisMontantNonPasse":
			return InscriptionScolaire.staticSolrStrFraisMontantNonPasse(requeteSite_, (Double)o);
		case "fraisMaintenant":
			return InscriptionScolaire.staticSolrStrFraisMaintenant(requeteSite_, (Double)o);
		case "paiementsAJour":
			return InscriptionScolaire.staticSolrStrPaiementsAJour(requeteSite_, (Boolean)o);
		case "paiementsEnRetard":
			return InscriptionScolaire.staticSolrStrPaiementsEnRetard(requeteSite_, (Boolean)o);
		case "paiementsEnRetardMontant":
			return InscriptionScolaire.staticSolrStrPaiementsEnRetardMontant(requeteSite_, (Double)o);
		case "paiementsEnAvance":
			return InscriptionScolaire.staticSolrStrPaiementsEnAvance(requeteSite_, (Boolean)o);
		case "paiementsEnSouffrance":
			return InscriptionScolaire.staticSolrStrPaiementsEnSouffrance(requeteSite_, (Boolean)o);
		case "paiementsEnSouffranceMontant":
			return InscriptionScolaire.staticSolrStrPaiementsEnSouffranceMontant(requeteSite_, (Double)o);
		case "fraisCrees":
			return InscriptionScolaire.staticSolrStrFraisCrees(requeteSite_, (Boolean)o);
		case "creeDAnnee":
			return InscriptionScolaire.staticSolrStrCreeDAnnee(requeteSite_, (Integer)o);
		case "creeJourDeSemaine":
			return InscriptionScolaire.staticSolrStrCreeJourDeSemaine(requeteSite_, (String)o);
		case "creeMoisDAnnee":
			return InscriptionScolaire.staticSolrStrCreeMoisDAnnee(requeteSite_, (String)o);
		case "creeHeureDuJour":
			return InscriptionScolaire.staticSolrStrCreeHeureDuJour(requeteSite_, (String)o);
		case "inscriptionJoursDeSemaine":
			return InscriptionScolaire.staticSolrStrInscriptionJoursDeSemaine(requeteSite_, (String)o);
		case "inscriptionNomsParents":
			return InscriptionScolaire.staticSolrStrInscriptionNomsParents(requeteSite_, (String)o);
		case "inscriptionMails":
			return InscriptionScolaire.staticSolrStrInscriptionMails(requeteSite_, (String)o);
		case "inscriptionMail":
			return InscriptionScolaire.staticSolrStrInscriptionMail(requeteSite_, (String)o);
		case "inscriptionMailsParents":
			return InscriptionScolaire.staticSolrStrInscriptionMailsParents(requeteSite_, (String)o);
		case "inscriptionNumeroTelephones":
			return InscriptionScolaire.staticSolrStrInscriptionNumeroTelephones(requeteSite_, (String)o);
		case "inscriptionNumeroTelephone":
			return InscriptionScolaire.staticSolrStrInscriptionNumeroTelephone(requeteSite_, (String)o);
		case "inscriptionNomParent":
			return InscriptionScolaire.staticSolrStrInscriptionNomParent(requeteSite_, (String)o);
		case "inscriptionNomParentLignes":
			return InscriptionScolaire.staticSolrStrInscriptionNomParentLignes(requeteSite_, (String)o);
		case "inscriptionMailParentLignes":
			return InscriptionScolaire.staticSolrStrInscriptionMailParentLignes(requeteSite_, (String)o);
		case "inscriptionDetailParentLignes":
			return InscriptionScolaire.staticSolrStrInscriptionDetailParentLignes(requeteSite_, (String)o);
		case "inscriptionChercherParentLignes":
			return InscriptionScolaire.staticSolrStrInscriptionChercherParentLignes(requeteSite_, (String)o);
		case "inscriptionContactUrgenceParentLignes":
			return InscriptionScolaire.staticSolrStrInscriptionContactUrgenceParentLignes(requeteSite_, (String)o);
		case "inscriptionSignature1":
			return InscriptionScolaire.staticSolrStrInscriptionSignature1(requeteSite_, (String)o);
		case "inscriptionSignature2":
			return InscriptionScolaire.staticSolrStrInscriptionSignature2(requeteSite_, (String)o);
		case "inscriptionSignature3":
			return InscriptionScolaire.staticSolrStrInscriptionSignature3(requeteSite_, (String)o);
		case "inscriptionSignature4":
			return InscriptionScolaire.staticSolrStrInscriptionSignature4(requeteSite_, (String)o);
		case "inscriptionSignature5":
			return InscriptionScolaire.staticSolrStrInscriptionSignature5(requeteSite_, (String)o);
		case "inscriptionSignature6":
			return InscriptionScolaire.staticSolrStrInscriptionSignature6(requeteSite_, (String)o);
		case "inscriptionSignature7":
			return InscriptionScolaire.staticSolrStrInscriptionSignature7(requeteSite_, (String)o);
		case "inscriptionSignature8":
			return InscriptionScolaire.staticSolrStrInscriptionSignature8(requeteSite_, (String)o);
		case "inscriptionSignature9":
			return InscriptionScolaire.staticSolrStrInscriptionSignature9(requeteSite_, (String)o);
		case "inscriptionSignature10":
			return InscriptionScolaire.staticSolrStrInscriptionSignature10(requeteSite_, (String)o);
		case "inscriptionDate1":
			return InscriptionScolaire.staticSolrStrInscriptionDate1(requeteSite_, (Date)o);
		case "inscriptionDate2":
			return InscriptionScolaire.staticSolrStrInscriptionDate2(requeteSite_, (Date)o);
		case "inscriptionDate3":
			return InscriptionScolaire.staticSolrStrInscriptionDate3(requeteSite_, (Date)o);
		case "inscriptionDate4":
			return InscriptionScolaire.staticSolrStrInscriptionDate4(requeteSite_, (Date)o);
		case "inscriptionDate5":
			return InscriptionScolaire.staticSolrStrInscriptionDate5(requeteSite_, (Date)o);
		case "inscriptionDate6":
			return InscriptionScolaire.staticSolrStrInscriptionDate6(requeteSite_, (Date)o);
		case "inscriptionDate7":
			return InscriptionScolaire.staticSolrStrInscriptionDate7(requeteSite_, (Date)o);
		case "inscriptionDate8":
			return InscriptionScolaire.staticSolrStrInscriptionDate8(requeteSite_, (Date)o);
		case "inscriptionDate9":
			return InscriptionScolaire.staticSolrStrInscriptionDate9(requeteSite_, (Date)o);
		case "inscriptionDate10":
			return InscriptionScolaire.staticSolrStrInscriptionDate10(requeteSite_, (Date)o);
		case "enfantImmunisationsRecu":
			return InscriptionScolaire.staticSolrStrEnfantImmunisationsRecu(requeteSite_, (String)o);
		case "enfantPhotosApprouve":
			return InscriptionScolaire.staticSolrStrEnfantPhotosApprouve(requeteSite_, (String)o);
		case "inscriptionNumero":
			return InscriptionScolaire.staticSolrStrInscriptionNumero(requeteSite_, (Integer)o);
		case "inscriptionNomComplet":
			return InscriptionScolaire.staticSolrStrInscriptionNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqInscriptionScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqInscriptionScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "inscriptionCle":
			return InscriptionScolaire.staticSolrFqInscriptionCle(requeteSite_, o);
		case "anneeCle":
			return InscriptionScolaire.staticSolrFqAnneeCle(requeteSite_, o);
		case "blocCles":
			return InscriptionScolaire.staticSolrFqBlocCles(requeteSite_, o);
		case "ecoleCle":
			return InscriptionScolaire.staticSolrFqEcoleCle(requeteSite_, o);
		case "sessionCle":
			return InscriptionScolaire.staticSolrFqSessionCle(requeteSite_, o);
		case "ageCle":
			return InscriptionScolaire.staticSolrFqAgeCle(requeteSite_, o);
		case "blocCle":
			return InscriptionScolaire.staticSolrFqBlocCle(requeteSite_, o);
		case "enfantCle":
			return InscriptionScolaire.staticSolrFqEnfantCle(requeteSite_, o);
		case "mereCles":
			return InscriptionScolaire.staticSolrFqMereCles(requeteSite_, o);
		case "pereCles":
			return InscriptionScolaire.staticSolrFqPereCles(requeteSite_, o);
		case "gardienCles":
			return InscriptionScolaire.staticSolrFqGardienCles(requeteSite_, o);
		case "paiementCles":
			return InscriptionScolaire.staticSolrFqPaiementCles(requeteSite_, o);
		case "formInscriptionCle":
			return InscriptionScolaire.staticSolrFqFormInscriptionCle(requeteSite_, o);
		case "utilisateurCles":
			return InscriptionScolaire.staticSolrFqUtilisateurCles(requeteSite_, o);
		case "scolaireTri":
			return InscriptionScolaire.staticSolrFqScolaireTri(requeteSite_, o);
		case "ecoleTri":
			return InscriptionScolaire.staticSolrFqEcoleTri(requeteSite_, o);
		case "anneeTri":
			return InscriptionScolaire.staticSolrFqAnneeTri(requeteSite_, o);
		case "saisonTri":
			return InscriptionScolaire.staticSolrFqSaisonTri(requeteSite_, o);
		case "sessionTri":
			return InscriptionScolaire.staticSolrFqSessionTri(requeteSite_, o);
		case "ageTri":
			return InscriptionScolaire.staticSolrFqAgeTri(requeteSite_, o);
		case "enfantPrenom":
			return InscriptionScolaire.staticSolrFqEnfantPrenom(requeteSite_, o);
		case "enfantPrenomPrefere":
			return InscriptionScolaire.staticSolrFqEnfantPrenomPrefere(requeteSite_, o);
		case "enfantFamilleNom":
			return InscriptionScolaire.staticSolrFqEnfantFamilleNom(requeteSite_, o);
		case "merePrenom":
			return InscriptionScolaire.staticSolrFqMerePrenom(requeteSite_, o);
		case "merePrenomPrefere":
			return InscriptionScolaire.staticSolrFqMerePrenomPrefere(requeteSite_, o);
		case "mereNomCompletPrefere":
			return InscriptionScolaire.staticSolrFqMereNomCompletPrefere(requeteSite_, o);
		case "perePrenom":
			return InscriptionScolaire.staticSolrFqPerePrenom(requeteSite_, o);
		case "perePrenomPrefere":
			return InscriptionScolaire.staticSolrFqPerePrenomPrefere(requeteSite_, o);
		case "pereNomCompletPrefere":
			return InscriptionScolaire.staticSolrFqPereNomCompletPrefere(requeteSite_, o);
		case "enfantNomComplet":
			return InscriptionScolaire.staticSolrFqEnfantNomComplet(requeteSite_, o);
		case "enfantNomCompletPrefere":
			return InscriptionScolaire.staticSolrFqEnfantNomCompletPrefere(requeteSite_, o);
		case "enfantDateNaissance":
			return InscriptionScolaire.staticSolrFqEnfantDateNaissance(requeteSite_, o);
		case "enfantDateNaissanceDAnnee":
			return InscriptionScolaire.staticSolrFqEnfantDateNaissanceDAnnee(requeteSite_, o);
		case "enfantDateNaissanceMoisDAnnee":
			return InscriptionScolaire.staticSolrFqEnfantDateNaissanceMoisDAnnee(requeteSite_, o);
		case "enfantDateNaissanceJourDeSemaine":
			return InscriptionScolaire.staticSolrFqEnfantDateNaissanceJourDeSemaine(requeteSite_, o);
		case "enfantMoisNaissance":
			return InscriptionScolaire.staticSolrFqEnfantMoisNaissance(requeteSite_, o);
		case "enfantJourNaissance":
			return InscriptionScolaire.staticSolrFqEnfantJourNaissance(requeteSite_, o);
		case "ecoleNom":
			return InscriptionScolaire.staticSolrFqEcoleNom(requeteSite_, o);
		case "ecoleNomComplet":
			return InscriptionScolaire.staticSolrFqEcoleNomComplet(requeteSite_, o);
		case "ecoleEmplacement":
			return InscriptionScolaire.staticSolrFqEcoleEmplacement(requeteSite_, o);
		case "ecoleAddresse":
			return InscriptionScolaire.staticSolrFqEcoleAddresse(requeteSite_, o);
		case "ecoleNumeroTelephone":
			return InscriptionScolaire.staticSolrFqEcoleNumeroTelephone(requeteSite_, o);
		case "ecoleForm":
			return InscriptionScolaire.staticSolrFqEcoleForm(requeteSite_, o);
		case "ecoleNumero":
			return InscriptionScolaire.staticSolrFqEcoleNumero(requeteSite_, o);
		case "ecoleAdministrateurNom":
			return InscriptionScolaire.staticSolrFqEcoleAdministrateurNom(requeteSite_, o);
		case "anneeDebut":
			return InscriptionScolaire.staticSolrFqAnneeDebut(requeteSite_, o);
		case "anneeFin":
			return InscriptionScolaire.staticSolrFqAnneeFin(requeteSite_, o);
		case "saisonDateDebut":
			return InscriptionScolaire.staticSolrFqSaisonDateDebut(requeteSite_, o);
		case "anneeFraisInscription":
			return InscriptionScolaire.staticSolrFqAnneeFraisInscription(requeteSite_, o);
		case "sessionDateDebut":
			return InscriptionScolaire.staticSolrFqSessionDateDebut(requeteSite_, o);
		case "sessionDateFin":
			return InscriptionScolaire.staticSolrFqSessionDateFin(requeteSite_, o);
		case "ageNomComplet":
			return InscriptionScolaire.staticSolrFqAgeNomComplet(requeteSite_, o);
		case "ageDebut":
			return InscriptionScolaire.staticSolrFqAgeDebut(requeteSite_, o);
		case "ageFin":
			return InscriptionScolaire.staticSolrFqAgeFin(requeteSite_, o);
		case "blocHeureDebut":
			return InscriptionScolaire.staticSolrFqBlocHeureDebut(requeteSite_, o);
		case "blocHeureFin":
			return InscriptionScolaire.staticSolrFqBlocHeureFin(requeteSite_, o);
		case "blocPrixParMois":
			return InscriptionScolaire.staticSolrFqBlocPrixParMois(requeteSite_, o);
		case "blocDimanche":
			return InscriptionScolaire.staticSolrFqBlocDimanche(requeteSite_, o);
		case "blocLundi":
			return InscriptionScolaire.staticSolrFqBlocLundi(requeteSite_, o);
		case "blocMardi":
			return InscriptionScolaire.staticSolrFqBlocMardi(requeteSite_, o);
		case "blocMercredi":
			return InscriptionScolaire.staticSolrFqBlocMercredi(requeteSite_, o);
		case "blocJeudi":
			return InscriptionScolaire.staticSolrFqBlocJeudi(requeteSite_, o);
		case "blocVendredi":
			return InscriptionScolaire.staticSolrFqBlocVendredi(requeteSite_, o);
		case "blocSamedi":
			return InscriptionScolaire.staticSolrFqBlocSamedi(requeteSite_, o);
		case "blocPrixTotal":
			return InscriptionScolaire.staticSolrFqBlocPrixTotal(requeteSite_, o);
		case "blocNomAdmin":
			return InscriptionScolaire.staticSolrFqBlocNomAdmin(requeteSite_, o);
		case "blocNomCourt":
			return InscriptionScolaire.staticSolrFqBlocNomCourt(requeteSite_, o);
		case "blocNomComplet":
			return InscriptionScolaire.staticSolrFqBlocNomComplet(requeteSite_, o);
		case "inscriptionApprouve":
			return InscriptionScolaire.staticSolrFqInscriptionApprouve(requeteSite_, o);
		case "inscriptionImmunisations":
			return InscriptionScolaire.staticSolrFqInscriptionImmunisations(requeteSite_, o);
		case "photo":
			return InscriptionScolaire.staticSolrFqPhoto(requeteSite_, o);
		case "familleMarie":
			return InscriptionScolaire.staticSolrFqFamilleMarie(requeteSite_, o);
		case "familleSepare":
			return InscriptionScolaire.staticSolrFqFamilleSepare(requeteSite_, o);
		case "familleDivorce":
			return InscriptionScolaire.staticSolrFqFamilleDivorce(requeteSite_, o);
		case "familleAddresse":
			return InscriptionScolaire.staticSolrFqFamilleAddresse(requeteSite_, o);
		case "familleCommentVousConnaissezEcole":
			return InscriptionScolaire.staticSolrFqFamilleCommentVousConnaissezEcole(requeteSite_, o);
		case "inscriptionConsiderationsSpeciales":
			return InscriptionScolaire.staticSolrFqInscriptionConsiderationsSpeciales(requeteSite_, o);
		case "enfantConditionsMedicales":
			return InscriptionScolaire.staticSolrFqEnfantConditionsMedicales(requeteSite_, o);
		case "enfantEcolesPrecedemmentFrequentees":
			return InscriptionScolaire.staticSolrFqEnfantEcolesPrecedemmentFrequentees(requeteSite_, o);
		case "enfantDescription":
			return InscriptionScolaire.staticSolrFqEnfantDescription(requeteSite_, o);
		case "enfantObjectifs":
			return InscriptionScolaire.staticSolrFqEnfantObjectifs(requeteSite_, o);
		case "enfantPropre":
			return InscriptionScolaire.staticSolrFqEnfantPropre(requeteSite_, o);
		case "inscriptionNomGroupe":
			return InscriptionScolaire.staticSolrFqInscriptionNomGroupe(requeteSite_, o);
		case "inscriptionCouleurGroupe":
			return InscriptionScolaire.staticSolrFqInscriptionCouleurGroupe(requeteSite_, o);
		case "inscriptionPaimentChaqueMois":
			return InscriptionScolaire.staticSolrFqInscriptionPaimentChaqueMois(requeteSite_, o);
		case "inscriptionPaimentComplet":
			return InscriptionScolaire.staticSolrFqInscriptionPaimentComplet(requeteSite_, o);
		case "customerProfileId":
			return InscriptionScolaire.staticSolrFqCustomerProfileId(requeteSite_, o);
		case "inscriptionDateFrais":
			return InscriptionScolaire.staticSolrFqInscriptionDateFrais(requeteSite_, o);
		case "paiementLastDate":
			return InscriptionScolaire.staticSolrFqPaiementLastDate(requeteSite_, o);
		case "paiementLastStr":
			return InscriptionScolaire.staticSolrFqPaiementLastStr(requeteSite_, o);
		case "paiementMontant":
			return InscriptionScolaire.staticSolrFqPaiementMontant(requeteSite_, o);
		case "fraisMontant":
			return InscriptionScolaire.staticSolrFqFraisMontant(requeteSite_, o);
		case "fraisMontantFuture":
			return InscriptionScolaire.staticSolrFqFraisMontantFuture(requeteSite_, o);
		case "fraisMontantDu":
			return InscriptionScolaire.staticSolrFqFraisMontantDu(requeteSite_, o);
		case "fraisMontantNonPasse":
			return InscriptionScolaire.staticSolrFqFraisMontantNonPasse(requeteSite_, o);
		case "fraisMaintenant":
			return InscriptionScolaire.staticSolrFqFraisMaintenant(requeteSite_, o);
		case "paiementsAJour":
			return InscriptionScolaire.staticSolrFqPaiementsAJour(requeteSite_, o);
		case "paiementsEnRetard":
			return InscriptionScolaire.staticSolrFqPaiementsEnRetard(requeteSite_, o);
		case "paiementsEnRetardMontant":
			return InscriptionScolaire.staticSolrFqPaiementsEnRetardMontant(requeteSite_, o);
		case "paiementsEnAvance":
			return InscriptionScolaire.staticSolrFqPaiementsEnAvance(requeteSite_, o);
		case "paiementsEnSouffrance":
			return InscriptionScolaire.staticSolrFqPaiementsEnSouffrance(requeteSite_, o);
		case "paiementsEnSouffranceMontant":
			return InscriptionScolaire.staticSolrFqPaiementsEnSouffranceMontant(requeteSite_, o);
		case "fraisCrees":
			return InscriptionScolaire.staticSolrFqFraisCrees(requeteSite_, o);
		case "creeDAnnee":
			return InscriptionScolaire.staticSolrFqCreeDAnnee(requeteSite_, o);
		case "creeJourDeSemaine":
			return InscriptionScolaire.staticSolrFqCreeJourDeSemaine(requeteSite_, o);
		case "creeMoisDAnnee":
			return InscriptionScolaire.staticSolrFqCreeMoisDAnnee(requeteSite_, o);
		case "creeHeureDuJour":
			return InscriptionScolaire.staticSolrFqCreeHeureDuJour(requeteSite_, o);
		case "inscriptionJoursDeSemaine":
			return InscriptionScolaire.staticSolrFqInscriptionJoursDeSemaine(requeteSite_, o);
		case "inscriptionNomsParents":
			return InscriptionScolaire.staticSolrFqInscriptionNomsParents(requeteSite_, o);
		case "inscriptionMails":
			return InscriptionScolaire.staticSolrFqInscriptionMails(requeteSite_, o);
		case "inscriptionMail":
			return InscriptionScolaire.staticSolrFqInscriptionMail(requeteSite_, o);
		case "inscriptionMailsParents":
			return InscriptionScolaire.staticSolrFqInscriptionMailsParents(requeteSite_, o);
		case "inscriptionNumeroTelephones":
			return InscriptionScolaire.staticSolrFqInscriptionNumeroTelephones(requeteSite_, o);
		case "inscriptionNumeroTelephone":
			return InscriptionScolaire.staticSolrFqInscriptionNumeroTelephone(requeteSite_, o);
		case "inscriptionNomParent":
			return InscriptionScolaire.staticSolrFqInscriptionNomParent(requeteSite_, o);
		case "inscriptionNomParentLignes":
			return InscriptionScolaire.staticSolrFqInscriptionNomParentLignes(requeteSite_, o);
		case "inscriptionMailParentLignes":
			return InscriptionScolaire.staticSolrFqInscriptionMailParentLignes(requeteSite_, o);
		case "inscriptionDetailParentLignes":
			return InscriptionScolaire.staticSolrFqInscriptionDetailParentLignes(requeteSite_, o);
		case "inscriptionChercherParentLignes":
			return InscriptionScolaire.staticSolrFqInscriptionChercherParentLignes(requeteSite_, o);
		case "inscriptionContactUrgenceParentLignes":
			return InscriptionScolaire.staticSolrFqInscriptionContactUrgenceParentLignes(requeteSite_, o);
		case "inscriptionSignature1":
			return InscriptionScolaire.staticSolrFqInscriptionSignature1(requeteSite_, o);
		case "inscriptionSignature2":
			return InscriptionScolaire.staticSolrFqInscriptionSignature2(requeteSite_, o);
		case "inscriptionSignature3":
			return InscriptionScolaire.staticSolrFqInscriptionSignature3(requeteSite_, o);
		case "inscriptionSignature4":
			return InscriptionScolaire.staticSolrFqInscriptionSignature4(requeteSite_, o);
		case "inscriptionSignature5":
			return InscriptionScolaire.staticSolrFqInscriptionSignature5(requeteSite_, o);
		case "inscriptionSignature6":
			return InscriptionScolaire.staticSolrFqInscriptionSignature6(requeteSite_, o);
		case "inscriptionSignature7":
			return InscriptionScolaire.staticSolrFqInscriptionSignature7(requeteSite_, o);
		case "inscriptionSignature8":
			return InscriptionScolaire.staticSolrFqInscriptionSignature8(requeteSite_, o);
		case "inscriptionSignature9":
			return InscriptionScolaire.staticSolrFqInscriptionSignature9(requeteSite_, o);
		case "inscriptionSignature10":
			return InscriptionScolaire.staticSolrFqInscriptionSignature10(requeteSite_, o);
		case "inscriptionDate1":
			return InscriptionScolaire.staticSolrFqInscriptionDate1(requeteSite_, o);
		case "inscriptionDate2":
			return InscriptionScolaire.staticSolrFqInscriptionDate2(requeteSite_, o);
		case "inscriptionDate3":
			return InscriptionScolaire.staticSolrFqInscriptionDate3(requeteSite_, o);
		case "inscriptionDate4":
			return InscriptionScolaire.staticSolrFqInscriptionDate4(requeteSite_, o);
		case "inscriptionDate5":
			return InscriptionScolaire.staticSolrFqInscriptionDate5(requeteSite_, o);
		case "inscriptionDate6":
			return InscriptionScolaire.staticSolrFqInscriptionDate6(requeteSite_, o);
		case "inscriptionDate7":
			return InscriptionScolaire.staticSolrFqInscriptionDate7(requeteSite_, o);
		case "inscriptionDate8":
			return InscriptionScolaire.staticSolrFqInscriptionDate8(requeteSite_, o);
		case "inscriptionDate9":
			return InscriptionScolaire.staticSolrFqInscriptionDate9(requeteSite_, o);
		case "inscriptionDate10":
			return InscriptionScolaire.staticSolrFqInscriptionDate10(requeteSite_, o);
		case "enfantImmunisationsRecu":
			return InscriptionScolaire.staticSolrFqEnfantImmunisationsRecu(requeteSite_, o);
		case "enfantPhotosApprouve":
			return InscriptionScolaire.staticSolrFqEnfantPhotosApprouve(requeteSite_, o);
		case "inscriptionNumero":
			return InscriptionScolaire.staticSolrFqInscriptionNumero(requeteSite_, o);
		case "inscriptionNomComplet":
			return InscriptionScolaire.staticSolrFqInscriptionNomComplet(requeteSite_, o);
			default:
				return Cluster.staticSolrFqCluster(entiteVar,  requeteSite_, o);
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

			if(sauvegardes.contains("paiementLastDate")) {
				Date paiementLastDate = (Date)solrDocument.get("paiementLastDate_stored_date");
				if(paiementLastDate != null)
					oInscriptionScolaire.setPaiementLastDate(paiementLastDate);
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

			if(sauvegardes.contains("fraisMontantNonPasse")) {
				Double fraisMontantNonPasse = (Double)solrDocument.get("fraisMontantNonPasse_stored_double");
				if(fraisMontantNonPasse != null)
					oInscriptionScolaire.setFraisMontantNonPasse(fraisMontantNonPasse);
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

			if(sauvegardes.contains("paiementsEnSouffrance")) {
				Boolean paiementsEnSouffrance = (Boolean)solrDocument.get("paiementsEnSouffrance_stored_boolean");
				if(paiementsEnSouffrance != null)
					oInscriptionScolaire.setPaiementsEnSouffrance(paiementsEnSouffrance);
			}

			if(sauvegardes.contains("paiementsEnSouffranceMontant")) {
				Double paiementsEnSouffranceMontant = (Double)solrDocument.get("paiementsEnSouffranceMontant_stored_double");
				if(paiementsEnSouffranceMontant != null)
					oInscriptionScolaire.setPaiementsEnSouffranceMontant(paiementsEnSouffranceMontant);
			}

			if(sauvegardes.contains("fraisCrees")) {
				Boolean fraisCrees = (Boolean)solrDocument.get("fraisCrees_stored_boolean");
				if(fraisCrees != null)
					oInscriptionScolaire.setFraisCrees(fraisCrees);
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
		if(paiementLastDate != null) {
			document.addField("paiementLastDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementLastDate.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("paiementLastDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementLastDate.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
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
		if(fraisMontantNonPasse != null) {
			document.addField("fraisMontantNonPasse_indexed_double", fraisMontantNonPasse.doubleValue());
			document.addField("fraisMontantNonPasse_stored_double", fraisMontantNonPasse.doubleValue());
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
		if(paiementsEnSouffrance != null) {
			document.addField("paiementsEnSouffrance_indexed_boolean", paiementsEnSouffrance);
			document.addField("paiementsEnSouffrance_stored_boolean", paiementsEnSouffrance);
		}
		if(paiementsEnSouffranceMontant != null) {
			document.addField("paiementsEnSouffranceMontant_indexed_double", paiementsEnSouffranceMontant.doubleValue());
			document.addField("paiementsEnSouffranceMontant_stored_double", paiementsEnSouffranceMontant.doubleValue());
		}
		if(fraisCrees != null) {
			document.addField("fraisCrees_indexed_boolean", fraisCrees);
			document.addField("fraisCrees_stored_boolean", fraisCrees);
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
			case "paiementLastDate":
				return "paiementLastDate_indexed_date";
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
			case "fraisMontantNonPasse":
				return "fraisMontantNonPasse_indexed_double";
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
			case "paiementsEnSouffrance":
				return "paiementsEnSouffrance_indexed_boolean";
			case "paiementsEnSouffranceMontant":
				return "paiementsEnSouffranceMontant_indexed_double";
			case "fraisCrees":
				return "fraisCrees_indexed_boolean";
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

		Date paiementLastDate = (Date)solrDocument.get("paiementLastDate_stored_date");
		if(paiementLastDate != null)
			oInscriptionScolaire.setPaiementLastDate(paiementLastDate);

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

		Double fraisMontantNonPasse = (Double)solrDocument.get("fraisMontantNonPasse_stored_double");
		if(fraisMontantNonPasse != null)
			oInscriptionScolaire.setFraisMontantNonPasse(fraisMontantNonPasse);

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

		Boolean paiementsEnSouffrance = (Boolean)solrDocument.get("paiementsEnSouffrance_stored_boolean");
		if(paiementsEnSouffrance != null)
			oInscriptionScolaire.setPaiementsEnSouffrance(paiementsEnSouffrance);

		Double paiementsEnSouffranceMontant = (Double)solrDocument.get("paiementsEnSouffranceMontant_stored_double");
		if(paiementsEnSouffranceMontant != null)
			oInscriptionScolaire.setPaiementsEnSouffranceMontant(paiementsEnSouffranceMontant);

		Boolean fraisCrees = (Boolean)solrDocument.get("fraisCrees_stored_boolean");
		if(fraisCrees != null)
			oInscriptionScolaire.setFraisCrees(fraisCrees);

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
			if(!Objects.equals(inscriptionCle, original.getInscriptionCle()))
				requeteApi.addVars("inscriptionCle");
			if(!Objects.equals(anneeCle, original.getAnneeCle()))
				requeteApi.addVars("anneeCle");
			if(!Objects.equals(blocCles, original.getBlocCles()))
				requeteApi.addVars("blocCles");
			if(!Objects.equals(ecoleCle, original.getEcoleCle()))
				requeteApi.addVars("ecoleCle");
			if(!Objects.equals(sessionCle, original.getSessionCle()))
				requeteApi.addVars("sessionCle");
			if(!Objects.equals(ageCle, original.getAgeCle()))
				requeteApi.addVars("ageCle");
			if(!Objects.equals(blocCle, original.getBlocCle()))
				requeteApi.addVars("blocCle");
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
			if(!Objects.equals(formInscriptionCle, original.getFormInscriptionCle()))
				requeteApi.addVars("formInscriptionCle");
			if(!Objects.equals(utilisateurCles, original.getUtilisateurCles()))
				requeteApi.addVars("utilisateurCles");
			if(!Objects.equals(scolaireTri, original.getScolaireTri()))
				requeteApi.addVars("scolaireTri");
			if(!Objects.equals(ecoleTri, original.getEcoleTri()))
				requeteApi.addVars("ecoleTri");
			if(!Objects.equals(anneeTri, original.getAnneeTri()))
				requeteApi.addVars("anneeTri");
			if(!Objects.equals(saisonTri, original.getSaisonTri()))
				requeteApi.addVars("saisonTri");
			if(!Objects.equals(sessionTri, original.getSessionTri()))
				requeteApi.addVars("sessionTri");
			if(!Objects.equals(ageTri, original.getAgeTri()))
				requeteApi.addVars("ageTri");
			if(!Objects.equals(enfantPrenom, original.getEnfantPrenom()))
				requeteApi.addVars("enfantPrenom");
			if(!Objects.equals(enfantPrenomPrefere, original.getEnfantPrenomPrefere()))
				requeteApi.addVars("enfantPrenomPrefere");
			if(!Objects.equals(enfantFamilleNom, original.getEnfantFamilleNom()))
				requeteApi.addVars("enfantFamilleNom");
			if(!Objects.equals(merePrenom, original.getMerePrenom()))
				requeteApi.addVars("merePrenom");
			if(!Objects.equals(merePrenomPrefere, original.getMerePrenomPrefere()))
				requeteApi.addVars("merePrenomPrefere");
			if(!Objects.equals(mereNomCompletPrefere, original.getMereNomCompletPrefere()))
				requeteApi.addVars("mereNomCompletPrefere");
			if(!Objects.equals(perePrenom, original.getPerePrenom()))
				requeteApi.addVars("perePrenom");
			if(!Objects.equals(perePrenomPrefere, original.getPerePrenomPrefere()))
				requeteApi.addVars("perePrenomPrefere");
			if(!Objects.equals(pereNomCompletPrefere, original.getPereNomCompletPrefere()))
				requeteApi.addVars("pereNomCompletPrefere");
			if(!Objects.equals(enfantNomComplet, original.getEnfantNomComplet()))
				requeteApi.addVars("enfantNomComplet");
			if(!Objects.equals(enfantNomCompletPrefere, original.getEnfantNomCompletPrefere()))
				requeteApi.addVars("enfantNomCompletPrefere");
			if(!Objects.equals(enfantDateNaissance, original.getEnfantDateNaissance()))
				requeteApi.addVars("enfantDateNaissance");
			if(!Objects.equals(enfantDateNaissanceDAnnee, original.getEnfantDateNaissanceDAnnee()))
				requeteApi.addVars("enfantDateNaissanceDAnnee");
			if(!Objects.equals(enfantDateNaissanceMoisDAnnee, original.getEnfantDateNaissanceMoisDAnnee()))
				requeteApi.addVars("enfantDateNaissanceMoisDAnnee");
			if(!Objects.equals(enfantDateNaissanceJourDeSemaine, original.getEnfantDateNaissanceJourDeSemaine()))
				requeteApi.addVars("enfantDateNaissanceJourDeSemaine");
			if(!Objects.equals(enfantMoisNaissance, original.getEnfantMoisNaissance()))
				requeteApi.addVars("enfantMoisNaissance");
			if(!Objects.equals(enfantJourNaissance, original.getEnfantJourNaissance()))
				requeteApi.addVars("enfantJourNaissance");
			if(!Objects.equals(ecoleNom, original.getEcoleNom()))
				requeteApi.addVars("ecoleNom");
			if(!Objects.equals(ecoleNomComplet, original.getEcoleNomComplet()))
				requeteApi.addVars("ecoleNomComplet");
			if(!Objects.equals(ecoleEmplacement, original.getEcoleEmplacement()))
				requeteApi.addVars("ecoleEmplacement");
			if(!Objects.equals(ecoleAddresse, original.getEcoleAddresse()))
				requeteApi.addVars("ecoleAddresse");
			if(!Objects.equals(ecoleNumeroTelephone, original.getEcoleNumeroTelephone()))
				requeteApi.addVars("ecoleNumeroTelephone");
			if(!Objects.equals(ecoleForm, original.getEcoleForm()))
				requeteApi.addVars("ecoleForm");
			if(!Objects.equals(ecoleNumero, original.getEcoleNumero()))
				requeteApi.addVars("ecoleNumero");
			if(!Objects.equals(ecoleAdministrateurNom, original.getEcoleAdministrateurNom()))
				requeteApi.addVars("ecoleAdministrateurNom");
			if(!Objects.equals(anneeDebut, original.getAnneeDebut()))
				requeteApi.addVars("anneeDebut");
			if(!Objects.equals(anneeFin, original.getAnneeFin()))
				requeteApi.addVars("anneeFin");
			if(!Objects.equals(saisonDateDebut, original.getSaisonDateDebut()))
				requeteApi.addVars("saisonDateDebut");
			if(!Objects.equals(anneeFraisInscription, original.getAnneeFraisInscription()))
				requeteApi.addVars("anneeFraisInscription");
			if(!Objects.equals(sessionDateDebut, original.getSessionDateDebut()))
				requeteApi.addVars("sessionDateDebut");
			if(!Objects.equals(sessionDateFin, original.getSessionDateFin()))
				requeteApi.addVars("sessionDateFin");
			if(!Objects.equals(ageNomComplet, original.getAgeNomComplet()))
				requeteApi.addVars("ageNomComplet");
			if(!Objects.equals(ageDebut, original.getAgeDebut()))
				requeteApi.addVars("ageDebut");
			if(!Objects.equals(ageFin, original.getAgeFin()))
				requeteApi.addVars("ageFin");
			if(!Objects.equals(blocHeureDebut, original.getBlocHeureDebut()))
				requeteApi.addVars("blocHeureDebut");
			if(!Objects.equals(blocHeureFin, original.getBlocHeureFin()))
				requeteApi.addVars("blocHeureFin");
			if(!Objects.equals(blocPrixParMois, original.getBlocPrixParMois()))
				requeteApi.addVars("blocPrixParMois");
			if(!Objects.equals(blocDimanche, original.getBlocDimanche()))
				requeteApi.addVars("blocDimanche");
			if(!Objects.equals(blocLundi, original.getBlocLundi()))
				requeteApi.addVars("blocLundi");
			if(!Objects.equals(blocMardi, original.getBlocMardi()))
				requeteApi.addVars("blocMardi");
			if(!Objects.equals(blocMercredi, original.getBlocMercredi()))
				requeteApi.addVars("blocMercredi");
			if(!Objects.equals(blocJeudi, original.getBlocJeudi()))
				requeteApi.addVars("blocJeudi");
			if(!Objects.equals(blocVendredi, original.getBlocVendredi()))
				requeteApi.addVars("blocVendredi");
			if(!Objects.equals(blocSamedi, original.getBlocSamedi()))
				requeteApi.addVars("blocSamedi");
			if(!Objects.equals(blocPrixTotal, original.getBlocPrixTotal()))
				requeteApi.addVars("blocPrixTotal");
			if(!Objects.equals(blocNomAdmin, original.getBlocNomAdmin()))
				requeteApi.addVars("blocNomAdmin");
			if(!Objects.equals(blocNomCourt, original.getBlocNomCourt()))
				requeteApi.addVars("blocNomCourt");
			if(!Objects.equals(blocNomComplet, original.getBlocNomComplet()))
				requeteApi.addVars("blocNomComplet");
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
			if(!Objects.equals(inscriptionCouleurGroupe, original.getInscriptionCouleurGroupe()))
				requeteApi.addVars("inscriptionCouleurGroupe");
			if(!Objects.equals(inscriptionPaimentChaqueMois, original.getInscriptionPaimentChaqueMois()))
				requeteApi.addVars("inscriptionPaimentChaqueMois");
			if(!Objects.equals(inscriptionPaimentComplet, original.getInscriptionPaimentComplet()))
				requeteApi.addVars("inscriptionPaimentComplet");
			if(!Objects.equals(customerProfileId, original.getCustomerProfileId()))
				requeteApi.addVars("customerProfileId");
			if(!Objects.equals(inscriptionDateFrais, original.getInscriptionDateFrais()))
				requeteApi.addVars("inscriptionDateFrais");
			if(!Objects.equals(paiementLastDate, original.getPaiementLastDate()))
				requeteApi.addVars("paiementLastDate");
			if(!Objects.equals(paiementLastStr, original.getPaiementLastStr()))
				requeteApi.addVars("paiementLastStr");
			if(!Objects.equals(paiementMontant, original.getPaiementMontant()))
				requeteApi.addVars("paiementMontant");
			if(!Objects.equals(fraisMontant, original.getFraisMontant()))
				requeteApi.addVars("fraisMontant");
			if(!Objects.equals(fraisMontantFuture, original.getFraisMontantFuture()))
				requeteApi.addVars("fraisMontantFuture");
			if(!Objects.equals(fraisMontantDu, original.getFraisMontantDu()))
				requeteApi.addVars("fraisMontantDu");
			if(!Objects.equals(fraisMontantNonPasse, original.getFraisMontantNonPasse()))
				requeteApi.addVars("fraisMontantNonPasse");
			if(!Objects.equals(fraisMaintenant, original.getFraisMaintenant()))
				requeteApi.addVars("fraisMaintenant");
			if(!Objects.equals(paiementsAJour, original.getPaiementsAJour()))
				requeteApi.addVars("paiementsAJour");
			if(!Objects.equals(paiementsEnRetard, original.getPaiementsEnRetard()))
				requeteApi.addVars("paiementsEnRetard");
			if(!Objects.equals(paiementsEnRetardMontant, original.getPaiementsEnRetardMontant()))
				requeteApi.addVars("paiementsEnRetardMontant");
			if(!Objects.equals(paiementsEnAvance, original.getPaiementsEnAvance()))
				requeteApi.addVars("paiementsEnAvance");
			if(!Objects.equals(paiementsEnSouffrance, original.getPaiementsEnSouffrance()))
				requeteApi.addVars("paiementsEnSouffrance");
			if(!Objects.equals(paiementsEnSouffranceMontant, original.getPaiementsEnSouffranceMontant()))
				requeteApi.addVars("paiementsEnSouffranceMontant");
			if(!Objects.equals(fraisCrees, original.getFraisCrees()))
				requeteApi.addVars("fraisCrees");
			if(!Objects.equals(creeDAnnee, original.getCreeDAnnee()))
				requeteApi.addVars("creeDAnnee");
			if(!Objects.equals(creeJourDeSemaine, original.getCreeJourDeSemaine()))
				requeteApi.addVars("creeJourDeSemaine");
			if(!Objects.equals(creeMoisDAnnee, original.getCreeMoisDAnnee()))
				requeteApi.addVars("creeMoisDAnnee");
			if(!Objects.equals(creeHeureDuJour, original.getCreeHeureDuJour()))
				requeteApi.addVars("creeHeureDuJour");
			if(!Objects.equals(inscriptionJoursDeSemaine, original.getInscriptionJoursDeSemaine()))
				requeteApi.addVars("inscriptionJoursDeSemaine");
			if(!Objects.equals(inscriptionNomsParents, original.getInscriptionNomsParents()))
				requeteApi.addVars("inscriptionNomsParents");
			if(!Objects.equals(inscriptionMails, original.getInscriptionMails()))
				requeteApi.addVars("inscriptionMails");
			if(!Objects.equals(inscriptionMail, original.getInscriptionMail()))
				requeteApi.addVars("inscriptionMail");
			if(!Objects.equals(inscriptionMailsParents, original.getInscriptionMailsParents()))
				requeteApi.addVars("inscriptionMailsParents");
			if(!Objects.equals(inscriptionNumeroTelephones, original.getInscriptionNumeroTelephones()))
				requeteApi.addVars("inscriptionNumeroTelephones");
			if(!Objects.equals(inscriptionNumeroTelephone, original.getInscriptionNumeroTelephone()))
				requeteApi.addVars("inscriptionNumeroTelephone");
			if(!Objects.equals(inscriptionNomParent, original.getInscriptionNomParent()))
				requeteApi.addVars("inscriptionNomParent");
			if(!Objects.equals(inscriptionNomParentLignes, original.getInscriptionNomParentLignes()))
				requeteApi.addVars("inscriptionNomParentLignes");
			if(!Objects.equals(inscriptionMailParentLignes, original.getInscriptionMailParentLignes()))
				requeteApi.addVars("inscriptionMailParentLignes");
			if(!Objects.equals(inscriptionDetailParentLignes, original.getInscriptionDetailParentLignes()))
				requeteApi.addVars("inscriptionDetailParentLignes");
			if(!Objects.equals(inscriptionChercherParentLignes, original.getInscriptionChercherParentLignes()))
				requeteApi.addVars("inscriptionChercherParentLignes");
			if(!Objects.equals(inscriptionContactUrgenceParentLignes, original.getInscriptionContactUrgenceParentLignes()))
				requeteApi.addVars("inscriptionContactUrgenceParentLignes");
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
			if(!Objects.equals(enfantImmunisationsRecu, original.getEnfantImmunisationsRecu()))
				requeteApi.addVars("enfantImmunisationsRecu");
			if(!Objects.equals(enfantPhotosApprouve, original.getEnfantPhotosApprouve()))
				requeteApi.addVars("enfantPhotosApprouve");
			if(!Objects.equals(inscriptionNomComplet, original.getInscriptionNomComplet()))
				requeteApi.addVars("inscriptionNomComplet");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), inscriptionCle, anneeCle, blocCles, ecoleCle, sessionCle, ageCle, blocCle, enfantCle, mereCles, pereCles, gardienCles, paiementCles, formInscriptionCle, utilisateurCles, scolaireTri, ecoleTri, anneeTri, saisonTri, sessionTri, ageTri, enfantPrenom, enfantPrenomPrefere, enfantFamilleNom, merePrenom, merePrenomPrefere, mereNomCompletPrefere, perePrenom, perePrenomPrefere, pereNomCompletPrefere, enfantNomComplet, enfantNomCompletPrefere, enfantDateNaissance, enfantDateNaissanceDAnnee, enfantDateNaissanceMoisDAnnee, enfantDateNaissanceJourDeSemaine, enfantMoisNaissance, enfantJourNaissance, ecoleNom, ecoleNomComplet, ecoleEmplacement, ecoleAddresse, ecoleNumeroTelephone, ecoleForm, ecoleNumero, ecoleAdministrateurNom, anneeDebut, anneeFin, saisonDateDebut, anneeFraisInscription, sessionDateDebut, sessionDateFin, ageNomComplet, ageDebut, ageFin, blocHeureDebut, blocHeureFin, blocPrixParMois, blocDimanche, blocLundi, blocMardi, blocMercredi, blocJeudi, blocVendredi, blocSamedi, blocPrixTotal, blocNomAdmin, blocNomCourt, blocNomComplet, inscriptionApprouve, inscriptionImmunisations, photo, familleMarie, familleSepare, familleDivorce, familleAddresse, familleCommentVousConnaissezEcole, inscriptionConsiderationsSpeciales, enfantConditionsMedicales, enfantEcolesPrecedemmentFrequentees, enfantDescription, enfantObjectifs, enfantPropre, inscriptionNomGroupe, inscriptionCouleurGroupe, inscriptionPaimentChaqueMois, inscriptionPaimentComplet, customerProfileId, inscriptionDateFrais, paiementLastDate, paiementLastStr, paiementMontant, fraisMontant, fraisMontantFuture, fraisMontantDu, fraisMontantNonPasse, fraisMaintenant, paiementsAJour, paiementsEnRetard, paiementsEnRetardMontant, paiementsEnAvance, paiementsEnSouffrance, paiementsEnSouffranceMontant, fraisCrees, creeDAnnee, creeJourDeSemaine, creeMoisDAnnee, creeHeureDuJour, inscriptionJoursDeSemaine, inscriptionNomsParents, inscriptionMails, inscriptionMail, inscriptionMailsParents, inscriptionNumeroTelephones, inscriptionNumeroTelephone, inscriptionNomParent, inscriptionNomParentLignes, inscriptionMailParentLignes, inscriptionDetailParentLignes, inscriptionChercherParentLignes, inscriptionContactUrgenceParentLignes, inscriptionSignature1, inscriptionSignature2, inscriptionSignature3, inscriptionSignature4, inscriptionSignature5, inscriptionSignature6, inscriptionSignature7, inscriptionSignature8, inscriptionSignature9, inscriptionSignature10, inscriptionDate1, inscriptionDate2, inscriptionDate3, inscriptionDate4, inscriptionDate5, inscriptionDate6, inscriptionDate7, inscriptionDate8, inscriptionDate9, inscriptionDate10, enfantImmunisationsRecu, enfantPhotosApprouve, inscriptionNomComplet);
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
				&& Objects.equals( inscriptionCle, that.inscriptionCle )
				&& Objects.equals( anneeCle, that.anneeCle )
				&& Objects.equals( blocCles, that.blocCles )
				&& Objects.equals( ecoleCle, that.ecoleCle )
				&& Objects.equals( sessionCle, that.sessionCle )
				&& Objects.equals( ageCle, that.ageCle )
				&& Objects.equals( blocCle, that.blocCle )
				&& Objects.equals( enfantCle, that.enfantCle )
				&& Objects.equals( mereCles, that.mereCles )
				&& Objects.equals( pereCles, that.pereCles )
				&& Objects.equals( gardienCles, that.gardienCles )
				&& Objects.equals( paiementCles, that.paiementCles )
				&& Objects.equals( formInscriptionCle, that.formInscriptionCle )
				&& Objects.equals( utilisateurCles, that.utilisateurCles )
				&& Objects.equals( scolaireTri, that.scolaireTri )
				&& Objects.equals( ecoleTri, that.ecoleTri )
				&& Objects.equals( anneeTri, that.anneeTri )
				&& Objects.equals( saisonTri, that.saisonTri )
				&& Objects.equals( sessionTri, that.sessionTri )
				&& Objects.equals( ageTri, that.ageTri )
				&& Objects.equals( enfantPrenom, that.enfantPrenom )
				&& Objects.equals( enfantPrenomPrefere, that.enfantPrenomPrefere )
				&& Objects.equals( enfantFamilleNom, that.enfantFamilleNom )
				&& Objects.equals( merePrenom, that.merePrenom )
				&& Objects.equals( merePrenomPrefere, that.merePrenomPrefere )
				&& Objects.equals( mereNomCompletPrefere, that.mereNomCompletPrefere )
				&& Objects.equals( perePrenom, that.perePrenom )
				&& Objects.equals( perePrenomPrefere, that.perePrenomPrefere )
				&& Objects.equals( pereNomCompletPrefere, that.pereNomCompletPrefere )
				&& Objects.equals( enfantNomComplet, that.enfantNomComplet )
				&& Objects.equals( enfantNomCompletPrefere, that.enfantNomCompletPrefere )
				&& Objects.equals( enfantDateNaissance, that.enfantDateNaissance )
				&& Objects.equals( enfantDateNaissanceDAnnee, that.enfantDateNaissanceDAnnee )
				&& Objects.equals( enfantDateNaissanceMoisDAnnee, that.enfantDateNaissanceMoisDAnnee )
				&& Objects.equals( enfantDateNaissanceJourDeSemaine, that.enfantDateNaissanceJourDeSemaine )
				&& Objects.equals( enfantMoisNaissance, that.enfantMoisNaissance )
				&& Objects.equals( enfantJourNaissance, that.enfantJourNaissance )
				&& Objects.equals( ecoleNom, that.ecoleNom )
				&& Objects.equals( ecoleNomComplet, that.ecoleNomComplet )
				&& Objects.equals( ecoleEmplacement, that.ecoleEmplacement )
				&& Objects.equals( ecoleAddresse, that.ecoleAddresse )
				&& Objects.equals( ecoleNumeroTelephone, that.ecoleNumeroTelephone )
				&& Objects.equals( ecoleForm, that.ecoleForm )
				&& Objects.equals( ecoleNumero, that.ecoleNumero )
				&& Objects.equals( ecoleAdministrateurNom, that.ecoleAdministrateurNom )
				&& Objects.equals( anneeDebut, that.anneeDebut )
				&& Objects.equals( anneeFin, that.anneeFin )
				&& Objects.equals( saisonDateDebut, that.saisonDateDebut )
				&& Objects.equals( anneeFraisInscription, that.anneeFraisInscription )
				&& Objects.equals( sessionDateDebut, that.sessionDateDebut )
				&& Objects.equals( sessionDateFin, that.sessionDateFin )
				&& Objects.equals( ageNomComplet, that.ageNomComplet )
				&& Objects.equals( ageDebut, that.ageDebut )
				&& Objects.equals( ageFin, that.ageFin )
				&& Objects.equals( blocHeureDebut, that.blocHeureDebut )
				&& Objects.equals( blocHeureFin, that.blocHeureFin )
				&& Objects.equals( blocPrixParMois, that.blocPrixParMois )
				&& Objects.equals( blocDimanche, that.blocDimanche )
				&& Objects.equals( blocLundi, that.blocLundi )
				&& Objects.equals( blocMardi, that.blocMardi )
				&& Objects.equals( blocMercredi, that.blocMercredi )
				&& Objects.equals( blocJeudi, that.blocJeudi )
				&& Objects.equals( blocVendredi, that.blocVendredi )
				&& Objects.equals( blocSamedi, that.blocSamedi )
				&& Objects.equals( blocPrixTotal, that.blocPrixTotal )
				&& Objects.equals( blocNomAdmin, that.blocNomAdmin )
				&& Objects.equals( blocNomCourt, that.blocNomCourt )
				&& Objects.equals( blocNomComplet, that.blocNomComplet )
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
				&& Objects.equals( inscriptionCouleurGroupe, that.inscriptionCouleurGroupe )
				&& Objects.equals( inscriptionPaimentChaqueMois, that.inscriptionPaimentChaqueMois )
				&& Objects.equals( inscriptionPaimentComplet, that.inscriptionPaimentComplet )
				&& Objects.equals( customerProfileId, that.customerProfileId )
				&& Objects.equals( inscriptionDateFrais, that.inscriptionDateFrais )
				&& Objects.equals( paiementLastDate, that.paiementLastDate )
				&& Objects.equals( paiementLastStr, that.paiementLastStr )
				&& Objects.equals( paiementMontant, that.paiementMontant )
				&& Objects.equals( fraisMontant, that.fraisMontant )
				&& Objects.equals( fraisMontantFuture, that.fraisMontantFuture )
				&& Objects.equals( fraisMontantDu, that.fraisMontantDu )
				&& Objects.equals( fraisMontantNonPasse, that.fraisMontantNonPasse )
				&& Objects.equals( fraisMaintenant, that.fraisMaintenant )
				&& Objects.equals( paiementsAJour, that.paiementsAJour )
				&& Objects.equals( paiementsEnRetard, that.paiementsEnRetard )
				&& Objects.equals( paiementsEnRetardMontant, that.paiementsEnRetardMontant )
				&& Objects.equals( paiementsEnAvance, that.paiementsEnAvance )
				&& Objects.equals( paiementsEnSouffrance, that.paiementsEnSouffrance )
				&& Objects.equals( paiementsEnSouffranceMontant, that.paiementsEnSouffranceMontant )
				&& Objects.equals( fraisCrees, that.fraisCrees )
				&& Objects.equals( creeDAnnee, that.creeDAnnee )
				&& Objects.equals( creeJourDeSemaine, that.creeJourDeSemaine )
				&& Objects.equals( creeMoisDAnnee, that.creeMoisDAnnee )
				&& Objects.equals( creeHeureDuJour, that.creeHeureDuJour )
				&& Objects.equals( inscriptionJoursDeSemaine, that.inscriptionJoursDeSemaine )
				&& Objects.equals( inscriptionNomsParents, that.inscriptionNomsParents )
				&& Objects.equals( inscriptionMails, that.inscriptionMails )
				&& Objects.equals( inscriptionMail, that.inscriptionMail )
				&& Objects.equals( inscriptionMailsParents, that.inscriptionMailsParents )
				&& Objects.equals( inscriptionNumeroTelephones, that.inscriptionNumeroTelephones )
				&& Objects.equals( inscriptionNumeroTelephone, that.inscriptionNumeroTelephone )
				&& Objects.equals( inscriptionNomParent, that.inscriptionNomParent )
				&& Objects.equals( inscriptionNomParentLignes, that.inscriptionNomParentLignes )
				&& Objects.equals( inscriptionMailParentLignes, that.inscriptionMailParentLignes )
				&& Objects.equals( inscriptionDetailParentLignes, that.inscriptionDetailParentLignes )
				&& Objects.equals( inscriptionChercherParentLignes, that.inscriptionChercherParentLignes )
				&& Objects.equals( inscriptionContactUrgenceParentLignes, that.inscriptionContactUrgenceParentLignes )
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
				&& Objects.equals( inscriptionDate10, that.inscriptionDate10 )
				&& Objects.equals( enfantImmunisationsRecu, that.enfantImmunisationsRecu )
				&& Objects.equals( enfantPhotosApprouve, that.enfantPhotosApprouve )
				&& Objects.equals( inscriptionNomComplet, that.inscriptionNomComplet );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("InscriptionScolaire { ");
		sb.append( "inscriptionCle: " ).append(inscriptionCle);
		sb.append( ", anneeCle: " ).append(anneeCle);
		sb.append( ", blocCles: " ).append(blocCles);
		sb.append( ", ecoleCle: " ).append(ecoleCle);
		sb.append( ", sessionCle: " ).append(sessionCle);
		sb.append( ", ageCle: " ).append(ageCle);
		sb.append( ", blocCle: " ).append(blocCle);
		sb.append( ", enfantCle: " ).append(enfantCle);
		sb.append( ", mereCles: " ).append(mereCles);
		sb.append( ", pereCles: " ).append(pereCles);
		sb.append( ", gardienCles: " ).append(gardienCles);
		sb.append( ", paiementCles: " ).append(paiementCles);
		sb.append( ", formInscriptionCle: " ).append(formInscriptionCle);
		sb.append( ", utilisateurCles: " ).append(utilisateurCles);
		sb.append( ", scolaireTri: " ).append(scolaireTri);
		sb.append( ", ecoleTri: " ).append(ecoleTri);
		sb.append( ", anneeTri: " ).append(anneeTri);
		sb.append( ", saisonTri: " ).append(saisonTri);
		sb.append( ", sessionTri: " ).append(sessionTri);
		sb.append( ", ageTri: " ).append(ageTri);
		sb.append( ", enfantPrenom: \"" ).append(enfantPrenom).append( "\"" );
		sb.append( ", enfantPrenomPrefere: \"" ).append(enfantPrenomPrefere).append( "\"" );
		sb.append( ", enfantFamilleNom: \"" ).append(enfantFamilleNom).append( "\"" );
		sb.append( ", merePrenom: \"" ).append(merePrenom).append( "\"" );
		sb.append( ", merePrenomPrefere: \"" ).append(merePrenomPrefere).append( "\"" );
		sb.append( ", mereNomCompletPrefere: \"" ).append(mereNomCompletPrefere).append( "\"" );
		sb.append( ", perePrenom: \"" ).append(perePrenom).append( "\"" );
		sb.append( ", perePrenomPrefere: \"" ).append(perePrenomPrefere).append( "\"" );
		sb.append( ", pereNomCompletPrefere: \"" ).append(pereNomCompletPrefere).append( "\"" );
		sb.append( ", enfantNomComplet: \"" ).append(enfantNomComplet).append( "\"" );
		sb.append( ", enfantNomCompletPrefere: \"" ).append(enfantNomCompletPrefere).append( "\"" );
		sb.append( ", enfantDateNaissance: " ).append(enfantDateNaissance);
		sb.append( ", enfantDateNaissanceDAnnee: " ).append(enfantDateNaissanceDAnnee);
		sb.append( ", enfantDateNaissanceMoisDAnnee: \"" ).append(enfantDateNaissanceMoisDAnnee).append( "\"" );
		sb.append( ", enfantDateNaissanceJourDeSemaine: \"" ).append(enfantDateNaissanceJourDeSemaine).append( "\"" );
		sb.append( ", enfantMoisNaissance: " ).append(enfantMoisNaissance);
		sb.append( ", enfantJourNaissance: " ).append(enfantJourNaissance);
		sb.append( ", ecoleNom: \"" ).append(ecoleNom).append( "\"" );
		sb.append( ", ecoleNomComplet: \"" ).append(ecoleNomComplet).append( "\"" );
		sb.append( ", ecoleEmplacement: \"" ).append(ecoleEmplacement).append( "\"" );
		sb.append( ", ecoleAddresse: \"" ).append(ecoleAddresse).append( "\"" );
		sb.append( ", ecoleNumeroTelephone: \"" ).append(ecoleNumeroTelephone).append( "\"" );
		sb.append( ", ecoleForm: \"" ).append(ecoleForm).append( "\"" );
		sb.append( ", ecoleNumero: " ).append(ecoleNumero);
		sb.append( ", ecoleAdministrateurNom: \"" ).append(ecoleAdministrateurNom).append( "\"" );
		sb.append( ", anneeDebut: " ).append(anneeDebut);
		sb.append( ", anneeFin: " ).append(anneeFin);
		sb.append( ", saisonDateDebut: " ).append(saisonDateDebut);
		sb.append( ", anneeFraisInscription: " ).append(anneeFraisInscription);
		sb.append( ", sessionDateDebut: " ).append(sessionDateDebut);
		sb.append( ", sessionDateFin: " ).append(sessionDateFin);
		sb.append( ", ageNomComplet: \"" ).append(ageNomComplet).append( "\"" );
		sb.append( ", ageDebut: " ).append(ageDebut);
		sb.append( ", ageFin: " ).append(ageFin);
		sb.append( ", blocHeureDebut: " ).append(blocHeureDebut);
		sb.append( ", blocHeureFin: " ).append(blocHeureFin);
		sb.append( ", blocPrixParMois: " ).append(blocPrixParMois);
		sb.append( ", blocDimanche: " ).append(blocDimanche);
		sb.append( ", blocLundi: " ).append(blocLundi);
		sb.append( ", blocMardi: " ).append(blocMardi);
		sb.append( ", blocMercredi: " ).append(blocMercredi);
		sb.append( ", blocJeudi: " ).append(blocJeudi);
		sb.append( ", blocVendredi: " ).append(blocVendredi);
		sb.append( ", blocSamedi: " ).append(blocSamedi);
		sb.append( ", blocPrixTotal: " ).append(blocPrixTotal);
		sb.append( ", blocNomAdmin: \"" ).append(blocNomAdmin).append( "\"" );
		sb.append( ", blocNomCourt: \"" ).append(blocNomCourt).append( "\"" );
		sb.append( ", blocNomComplet: \"" ).append(blocNomComplet).append( "\"" );
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
		sb.append( ", inscriptionCouleurGroupe: \"" ).append(inscriptionCouleurGroupe).append( "\"" );
		sb.append( ", inscriptionPaimentChaqueMois: " ).append(inscriptionPaimentChaqueMois);
		sb.append( ", inscriptionPaimentComplet: " ).append(inscriptionPaimentComplet);
		sb.append( ", customerProfileId: \"" ).append(customerProfileId).append( "\"" );
		sb.append( ", inscriptionDateFrais: " ).append(inscriptionDateFrais);
		sb.append( ", paiementLastDate: " ).append(paiementLastDate);
		sb.append( ", paiementLastStr: \"" ).append(paiementLastStr).append( "\"" );
		sb.append( ", paiementMontant: " ).append(paiementMontant);
		sb.append( ", fraisMontant: " ).append(fraisMontant);
		sb.append( ", fraisMontantFuture: " ).append(fraisMontantFuture);
		sb.append( ", fraisMontantDu: " ).append(fraisMontantDu);
		sb.append( ", fraisMontantNonPasse: " ).append(fraisMontantNonPasse);
		sb.append( ", fraisMaintenant: " ).append(fraisMaintenant);
		sb.append( ", paiementsAJour: " ).append(paiementsAJour);
		sb.append( ", paiementsEnRetard: " ).append(paiementsEnRetard);
		sb.append( ", paiementsEnRetardMontant: " ).append(paiementsEnRetardMontant);
		sb.append( ", paiementsEnAvance: " ).append(paiementsEnAvance);
		sb.append( ", paiementsEnSouffrance: " ).append(paiementsEnSouffrance);
		sb.append( ", paiementsEnSouffranceMontant: " ).append(paiementsEnSouffranceMontant);
		sb.append( ", fraisCrees: " ).append(fraisCrees);
		sb.append( ", creeDAnnee: " ).append(creeDAnnee);
		sb.append( ", creeJourDeSemaine: \"" ).append(creeJourDeSemaine).append( "\"" );
		sb.append( ", creeMoisDAnnee: \"" ).append(creeMoisDAnnee).append( "\"" );
		sb.append( ", creeHeureDuJour: \"" ).append(creeHeureDuJour).append( "\"" );
		sb.append( ", inscriptionJoursDeSemaine: " ).append(inscriptionJoursDeSemaine);
		sb.append( ", inscriptionNomsParents: \"" ).append(inscriptionNomsParents).append( "\"" );
		sb.append( ", inscriptionMails: " ).append(inscriptionMails);
		sb.append( ", inscriptionMail: \"" ).append(inscriptionMail).append( "\"" );
		sb.append( ", inscriptionMailsParents: \"" ).append(inscriptionMailsParents).append( "\"" );
		sb.append( ", inscriptionNumeroTelephones: " ).append(inscriptionNumeroTelephones);
		sb.append( ", inscriptionNumeroTelephone: \"" ).append(inscriptionNumeroTelephone).append( "\"" );
		sb.append( ", inscriptionNomParent: \"" ).append(inscriptionNomParent).append( "\"" );
		sb.append( ", inscriptionNomParentLignes: \"" ).append(inscriptionNomParentLignes).append( "\"" );
		sb.append( ", inscriptionMailParentLignes: \"" ).append(inscriptionMailParentLignes).append( "\"" );
		sb.append( ", inscriptionDetailParentLignes: \"" ).append(inscriptionDetailParentLignes).append( "\"" );
		sb.append( ", inscriptionChercherParentLignes: \"" ).append(inscriptionChercherParentLignes).append( "\"" );
		sb.append( ", inscriptionContactUrgenceParentLignes: \"" ).append(inscriptionContactUrgenceParentLignes).append( "\"" );
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
		sb.append( ", enfantImmunisationsRecu: \"" ).append(enfantImmunisationsRecu).append( "\"" );
		sb.append( ", enfantPhotosApprouve: \"" ).append(enfantPhotosApprouve).append( "\"" );
		sb.append( ", inscriptionNomComplet: \"" ).append(inscriptionNomComplet).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
