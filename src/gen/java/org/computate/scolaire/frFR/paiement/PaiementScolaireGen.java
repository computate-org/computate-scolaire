package org.computate.scolaire.frFR.paiement;

import java.util.Arrays;
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
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import java.util.Set;
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
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe paymentCompleteName dans Solr</a>
 * <br/>
 **/
public abstract class PaiementScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PaiementScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("User");

	public static final String PaiementScolaire_UnNom = "un paiement";
	public static final String PaiementScolaire_Ce = "ce ";
	public static final String PaiementScolaire_CeNom = "ce paiement";
	public static final String PaiementScolaire_Un = "un ";
	public static final String PaiementScolaire_LeNom = "le paiement";
	public static final String PaiementScolaire_NomSingulier = "paiement";
	public static final String PaiementScolaire_NomPluriel = "paiements";
	public static final String PaiementScolaire_NomActuel = "paiement actuel";
	public static final String PaiementScolaire_Tous = "all ";
	public static final String PaiementScolaire_TousNom = "tous les paiements";
	public static final String PaiementScolaire_RechercherTousNomPar = "rechercher paiements par ";
	public static final String PaiementScolaire_RechercherTousNom = "rechercher paiements";
	public static final String PaiementScolaire_LesNom = "les paiements";
	public static final String PaiementScolaire_AucunNomTrouve = "aucun paiement trouvé";
	public static final String PaiementScolaire_NomVar = "paiement";
	public static final String PaiementScolaire_DeNom = "de paiement";
	public static final String PaiementScolaire_NomAdjectifSingulier = "paiement";
	public static final String PaiementScolaire_NomAdjectifPluriel = "paiements";
	public static final String PaiementScolaire_Couleur = "green";
	public static final String PaiementScolaire_IconeGroupe = "solid";
	public static final String PaiementScolaire_IconeNom = "search-dollar";
	public static final Integer PaiementScolaire_Lignes = 50;

	/////////////////
	// paiementCle //
	/////////////////

	/**	L'entité « paiementCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long paiementCle;
	@JsonIgnore
	public Couverture<Long> paiementCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("paiementCle").o(paiementCle);

	/**	<br/>L'entité « paiementCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementCle">Trouver l'entité paiementCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementCle(Couverture<Long> c);

	public Long getPaiementCle() {
		return paiementCle;
	}

	public void setPaiementCle(Long paiementCle) {
		this.paiementCle = paiementCle;
		this.paiementCleCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementCle(String o) {
		if(NumberUtils.isParsable(o))
			this.paiementCle = Long.parseLong(o);
		this.paiementCleCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementCleInit() {
		if(!paiementCleCouverture.dejaInitialise) {
			_paiementCle(paiementCleCouverture);
			if(paiementCle == null)
				setPaiementCle(paiementCleCouverture.o);
		}
		paiementCleCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Long solrPaiementCle() {
		return paiementCle;
	}

	public String strPaiementCle() {
		return paiementCle == null ? "" : paiementCle.toString();
	}

	public String jsonPaiementCle() {
		return paiementCle == null ? "" : paiementCle.toString();
	}

	public String nomAffichagePaiementCle() {
		return "clé";
	}

	public String htmTooltipPaiementCle() {
		return null;
	}

	public String htmPaiementCle() {
		return paiementCle == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementCle());
	}

	////////////////////
	// inscriptionCle //
	////////////////////

	/**	L'entité « inscriptionCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long inscriptionCle;
	@JsonIgnore
	public Couverture<Long> inscriptionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("inscriptionCle").o(inscriptionCle);

	/**	<br/>L'entité « inscriptionCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCle">Trouver l'entité inscriptionCle dans Solr</a>
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
	public PaiementScolaire setInscriptionCle(String o) {
		if(NumberUtils.isParsable(o))
			this.inscriptionCle = Long.parseLong(o);
		this.inscriptionCleCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire inscriptionCleInit() {
		if(!inscriptionCleCouverture.dejaInitialise) {
			_inscriptionCle(inscriptionCleCouverture);
			if(inscriptionCle == null)
				setInscriptionCle(inscriptionCleCouverture.o);
		}
		inscriptionCleCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "inscription";
	}

	public String htmTooltipInscriptionCle() {
		return null;
	}

	public String htmInscriptionCle() {
		return inscriptionCle == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionCle());
	}

	public void inputInscriptionCle(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "inscription")
					.a("title", "La clé primaire des enfants dans la base de données. ")
					.a("class", "valeur suggereInscriptionCle w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setInscriptionCle")
					.a("id", classeApiMethodeMethode, "_inscriptionCle")
					.a("autocomplete", "off")
					.a("oninput", "suggerePaiementScolaireInscriptionCle($(this).val() ? rechercherInscriptionScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'paiementCles:" + pk + "'}", "], $('#listPaiementScolaireInscriptionCle_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		}
	}

	public void htmInscriptionCle(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireInscriptionCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=paiementCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("inscription");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier une inscription a ce paiement");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputInscriptionCle(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listPaiementScolaireInscriptionCle_", classeApiMethodeMethode).f();
								} g("ul");
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
											.a("id", classeApiMethodeMethode, "_inscriptionCle_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postInscriptionScolaireVals({ paiementCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "inscriptionCle')); });")
											.f().sx("ajouter une inscription")
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

	//////////////////////////
	// inscriptionRecherche //
	//////////////////////////

	/**	L'entité « inscriptionRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<InscriptionScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<InscriptionScolaire> inscriptionRecherche = new ListeRecherche<InscriptionScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<InscriptionScolaire>> inscriptionRechercheCouverture = new Couverture<ListeRecherche<InscriptionScolaire>>().p(this).c(ListeRecherche.class).var("inscriptionRecherche").o(inscriptionRecherche);

	/**	<br/>L'entité « inscriptionRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<InscriptionScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionRecherche">Trouver l'entité inscriptionRecherche dans Solr</a>
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
	protected PaiementScolaire inscriptionRechercheInit() {
		if(!inscriptionRechercheCouverture.dejaInitialise) {
			_inscriptionRecherche(inscriptionRecherche);
		}
		inscriptionRecherche.initLoinPourClasse(requeteSite_);
		inscriptionRechercheCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	//////////////////
	// inscription_ //
	//////////////////

	/**	L'entité « inscription_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected InscriptionScolaire inscription_;
	@JsonIgnore
	public Couverture<InscriptionScolaire> inscription_Couverture = new Couverture<InscriptionScolaire>().p(this).c(InscriptionScolaire.class).var("inscription_").o(inscription_);

	/**	<br/>L'entité « inscription_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscription_">Trouver l'entité inscription_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscription_(Couverture<InscriptionScolaire> c);

	public InscriptionScolaire getInscription_() {
		return inscription_;
	}

	public void setInscription_(InscriptionScolaire inscription_) {
		this.inscription_ = inscription_;
		this.inscription_Couverture.dejaInitialise = true;
	}
	protected PaiementScolaire inscription_Init() {
		if(!inscription_Couverture.dejaInitialise) {
			_inscription_(inscription_Couverture);
			if(inscription_ == null)
				setInscription_(inscription_Couverture.o);
		}
		inscription_Couverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	/////////////////
	// ecoleNumero //
	/////////////////

	/**	L'entité « ecoleNumero »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ecoleNumero;
	@JsonIgnore
	public Couverture<Integer> ecoleNumeroCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ecoleNumero").o(ecoleNumero);

	/**	<br/>L'entité « ecoleNumero »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumero">Trouver l'entité ecoleNumero dans Solr</a>
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
	public PaiementScolaire setEcoleNumero(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleNumero = Integer.parseInt(o);
		this.ecoleNumeroCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire ecoleNumeroInit() {
		if(!ecoleNumeroCouverture.dejaInitialise) {
			_ecoleNumero(ecoleNumeroCouverture);
			if(ecoleNumero == null)
				setEcoleNumero(ecoleNumeroCouverture.o);
		}
		ecoleNumeroCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	/////////////////////
	// utilisateurCles //
	/////////////////////

	/**	L'entité « utilisateurCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> utilisateurCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> utilisateurClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("utilisateurCles").o(utilisateurCles);

	/**	<br/>L'entité « utilisateurCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurCles">Trouver l'entité utilisateurCles dans Solr</a>
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
	public PaiementScolaire addUtilisateurCles(Long...objets) {
		for(Long o : objets) {
			addUtilisateurCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addUtilisateurCles(Long o) {
		if(o != null && !utilisateurCles.contains(o))
			this.utilisateurCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setUtilisateurCles(JsonArray objets) {
		utilisateurCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUtilisateurCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addUtilisateurCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUtilisateurCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire utilisateurClesInit() {
		if(!utilisateurClesCouverture.dejaInitialise) {
			_utilisateurCles(utilisateurCles);
		}
		utilisateurClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return null;
	}

	public String htmTooltipUtilisateurCles() {
		return null;
	}

	public String htmUtilisateurCles() {
		return utilisateurCles == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurCles());
	}

	//////////////
	// ecoleCle //
	//////////////

	/**	L'entité « ecoleCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long ecoleCle;
	@JsonIgnore
	public Couverture<Long> ecoleCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("ecoleCle").o(ecoleCle);

	/**	<br/>L'entité « ecoleCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
	public PaiementScolaire setEcoleCle(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleCle = Long.parseLong(o);
		this.ecoleCleCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "écoles";
	}

	public String htmTooltipEcoleCle() {
		return null;
	}

	public String htmEcoleCle() {
		return ecoleCle == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleCle());
	}

	//////////////
	// anneeCle //
	//////////////

	/**	L'entité « anneeCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long anneeCle;
	@JsonIgnore
	public Couverture<Long> anneeCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("anneeCle").o(anneeCle);

	/**	<br/>L'entité « anneeCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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
	public PaiementScolaire setAnneeCle(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeCle = Long.parseLong(o);
		this.anneeCleCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire anneeCleInit() {
		if(!anneeCleCouverture.dejaInitialise) {
			_anneeCle(anneeCleCouverture);
			if(anneeCle == null)
				setAnneeCle(anneeCleCouverture.o);
		}
		anneeCleCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "années";
	}

	public String htmTooltipAnneeCle() {
		return null;
	}

	public String htmAnneeCle() {
		return anneeCle == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCle());
	}

	///////////////
	// saisonCle //
	///////////////

	/**	L'entité « saisonCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long saisonCle;
	@JsonIgnore
	public Couverture<Long> saisonCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("saisonCle").o(saisonCle);

	/**	<br/>L'entité « saisonCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCle">Trouver l'entité saisonCle dans Solr</a>
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
	public PaiementScolaire setSaisonCle(String o) {
		if(NumberUtils.isParsable(o))
			this.saisonCle = Long.parseLong(o);
		this.saisonCleCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire saisonCleInit() {
		if(!saisonCleCouverture.dejaInitialise) {
			_saisonCle(saisonCleCouverture);
			if(saisonCle == null)
				setSaisonCle(saisonCleCouverture.o);
		}
		saisonCleCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "saisons";
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
	@JsonInclude(Include.NON_NULL)
	protected Long sessionCle;
	@JsonIgnore
	public Couverture<Long> sessionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("sessionCle").o(sessionCle);

	/**	<br/>L'entité « sessionCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCle">Trouver l'entité sessionCle dans Solr</a>
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
	public PaiementScolaire setSessionCle(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionCle = Long.parseLong(o);
		this.sessionCleCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire sessionCleInit() {
		if(!sessionCleCouverture.dejaInitialise) {
			_sessionCle(sessionCleCouverture);
			if(sessionCle == null)
				setSessionCle(sessionCleCouverture.o);
		}
		sessionCleCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "sessions";
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
	@JsonInclude(Include.NON_NULL)
	protected Long ageCle;
	@JsonIgnore
	public Couverture<Long> ageCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("ageCle").o(ageCle);

	/**	<br/>L'entité « ageCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCle">Trouver l'entité ageCle dans Solr</a>
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
	public PaiementScolaire setAgeCle(String o) {
		if(NumberUtils.isParsable(o))
			this.ageCle = Long.parseLong(o);
		this.ageCleCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire ageCleInit() {
		if(!ageCleCouverture.dejaInitialise) {
			_ageCle(ageCleCouverture);
			if(ageCle == null)
				setAgeCle(ageCleCouverture.o);
		}
		ageCleCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "âges";
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
	@JsonInclude(Include.NON_NULL)
	protected Long blocCle;
	@JsonIgnore
	public Couverture<Long> blocCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("blocCle").o(blocCle);

	/**	<br/>L'entité « blocCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocCle">Trouver l'entité blocCle dans Solr</a>
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
	public PaiementScolaire setBlocCle(String o) {
		if(NumberUtils.isParsable(o))
			this.blocCle = Long.parseLong(o);
		this.blocCleCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire blocCleInit() {
		if(!blocCleCouverture.dejaInitialise) {
			_blocCle(blocCleCouverture);
			if(blocCle == null)
				setBlocCle(blocCleCouverture.o);
		}
		blocCleCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "sessions";
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
	@JsonInclude(Include.NON_NULL)
	protected Long enfantCle;
	@JsonIgnore
	public Couverture<Long> enfantCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("enfantCle").o(enfantCle);

	/**	<br/>L'entité « enfantCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantCle">Trouver l'entité enfantCle dans Solr</a>
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
	public PaiementScolaire setEnfantCle(String o) {
		if(NumberUtils.isParsable(o))
			this.enfantCle = Long.parseLong(o);
		this.enfantCleCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire enfantCleInit() {
		if(!enfantCleCouverture.dejaInitialise) {
			_enfantCle(enfantCleCouverture);
			if(enfantCle == null)
				setEnfantCle(enfantCleCouverture.o);
		}
		enfantCleCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	//////////////
	// mereCles //
	//////////////

	/**	L'entité « mereCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> mereCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> mereClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("mereCles").o(mereCles);

	/**	<br/>L'entité « mereCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereCles">Trouver l'entité mereCles dans Solr</a>
	 * <br/>
	 * @param mereCles est l'entité déjà construit. 
	 **/
	protected abstract void _mereCles(List<Long> l);

	public List<Long> getMereCles() {
		return mereCles;
	}

	public void setMereCles(List<Long> mereCles) {
		this.mereCles = mereCles;
		this.mereClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addMereCles(Long...objets) {
		for(Long o : objets) {
			addMereCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addMereCles(Long o) {
		if(o != null && !mereCles.contains(o))
			this.mereCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setMereCles(JsonArray objets) {
		mereCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addMereCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addMereCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addMereCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire mereClesInit() {
		if(!mereClesCouverture.dejaInitialise) {
			_mereCles(mereCles);
		}
		mereClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	//////////////
	// pereCles //
	//////////////

	/**	L'entité « pereCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> pereCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> pereClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("pereCles").o(pereCles);

	/**	<br/>L'entité « pereCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereCles">Trouver l'entité pereCles dans Solr</a>
	 * <br/>
	 * @param pereCles est l'entité déjà construit. 
	 **/
	protected abstract void _pereCles(List<Long> l);

	public List<Long> getPereCles() {
		return pereCles;
	}

	public void setPereCles(List<Long> pereCles) {
		this.pereCles = pereCles;
		this.pereClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addPereCles(Long...objets) {
		for(Long o : objets) {
			addPereCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addPereCles(Long o) {
		if(o != null && !pereCles.contains(o))
			this.pereCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setPereCles(JsonArray objets) {
		pereCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPereCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addPereCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPereCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire pereClesInit() {
		if(!pereClesCouverture.dejaInitialise) {
			_pereCles(pereCles);
		}
		pereClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	/////////////////
	// gardienCles //
	/////////////////

	/**	L'entité « gardienCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> gardienCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> gardienClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("gardienCles").o(gardienCles);

	/**	<br/>L'entité « gardienCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienCles">Trouver l'entité gardienCles dans Solr</a>
	 * <br/>
	 * @param gardienCles est l'entité déjà construit. 
	 **/
	protected abstract void _gardienCles(List<Long> l);

	public List<Long> getGardienCles() {
		return gardienCles;
	}

	public void setGardienCles(List<Long> gardienCles) {
		this.gardienCles = gardienCles;
		this.gardienClesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire addGardienCles(Long...objets) {
		for(Long o : objets) {
			addGardienCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addGardienCles(Long o) {
		if(o != null && !gardienCles.contains(o))
			this.gardienCles.add(o);
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setGardienCles(JsonArray objets) {
		gardienCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGardienCles(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addGardienCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addGardienCles(p);
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire gardienClesInit() {
		if(!gardienClesCouverture.dejaInitialise) {
			_gardienCles(gardienCles);
		}
		gardienClesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	/////////////////////////////
	// enfantNomCompletPrefere //
	/////////////////////////////

	/**	L'entité « enfantNomCompletPrefere »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String enfantNomCompletPrefere;
	@JsonIgnore
	public Couverture<String> enfantNomCompletPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("enfantNomCompletPrefere").o(enfantNomCompletPrefere);

	/**	<br/>L'entité « enfantNomCompletPrefere »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantNomCompletPrefere">Trouver l'entité enfantNomCompletPrefere dans Solr</a>
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
	protected PaiementScolaire enfantNomCompletPrefereInit() {
		if(!enfantNomCompletPrefereCouverture.dejaInitialise) {
			_enfantNomCompletPrefere(enfantNomCompletPrefereCouverture);
			if(enfantNomCompletPrefere == null)
				setEnfantNomCompletPrefere(enfantNomCompletPrefereCouverture.o);
		}
		enfantNomCompletPrefereCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "NomAffichage.enUS: ")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_enfantNomCompletPrefere");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEnfantNomCompletPrefere classPaiementScolaire inputPaiementScolaire", pk, "EnfantNomCompletPrefere w3-input w3-border ");
					a("name", "setEnfantNomCompletPrefere");
				} else {
					a("class", "valeurEnfantNomCompletPrefere w3-input w3-border classPaiementScolaire inputPaiementScolaire", pk, "EnfantNomCompletPrefere w3-input w3-border ");
					a("name", "enfantNomCompletPrefere");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantNomCompletPrefere', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }); ");
				}
				a("value", strEnfantNomCompletPrefere())
			.fg();

		}
	}

	public void htmEnfantNomCompletPrefere(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireEnfantNomCompletPrefere").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantNomCompletPrefere").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEnfantNomCompletPrefere(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); $('#", classeApiMethodeMethode, "_enfantNomCompletPrefere').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setEnfantNomCompletPrefere', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantNomCompletPrefere')); }); ")
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

	/**	L'entité « enfantDateNaissance »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate enfantDateNaissance;
	@JsonIgnore
	public Couverture<LocalDate> enfantDateNaissanceCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("enfantDateNaissance").o(enfantDateNaissance);

	/**	<br/>L'entité « enfantDateNaissance »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantDateNaissance">Trouver l'entité enfantDateNaissance dans Solr</a>
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
	public PaiementScolaire setEnfantDateNaissance(Instant o) {
		this.enfantDateNaissance = o == null ? null : LocalDate.from(o);
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public PaiementScolaire setEnfantDateNaissance(String o) {
		this.enfantDateNaissance = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setEnfantDateNaissance(Date o) {
		this.enfantDateNaissance = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire enfantDateNaissanceInit() {
		if(!enfantDateNaissanceCouverture.dejaInitialise) {
			_enfantDateNaissance(enfantDateNaissanceCouverture);
			if(enfantDateNaissance == null)
				setEnfantDateNaissance(enfantDateNaissanceCouverture.o);
		}
		enfantDateNaissanceCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setEnfantDateNaissance classPaiementScolaire inputPaiementScolaire", pk, "EnfantDateNaissance w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_enfantDateNaissance")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des enfants dans la base de données.  (DD-MM-YYYY)")
				.a("value", enfantDateNaissance == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(enfantDateNaissance))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEnfantDateNaissance', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }); } ")
				.fg();
		}
	}

	public void htmEnfantDateNaissance(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireEnfantDateNaissance").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_enfantDateNaissance").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputEnfantDateNaissance(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); $('#", classeApiMethodeMethode, "_enfantDateNaissance').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setEnfantDateNaissance', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_enfantDateNaissance')); }); ")
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
	// mereNomCompletPrefere //
	///////////////////////////

	/**	L'entité « mereNomCompletPrefere »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mereNomCompletPrefere;
	@JsonIgnore
	public Couverture<String> mereNomCompletPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("mereNomCompletPrefere").o(mereNomCompletPrefere);

	/**	<br/>L'entité « mereNomCompletPrefere »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereNomCompletPrefere">Trouver l'entité mereNomCompletPrefere dans Solr</a>
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
	protected PaiementScolaire mereNomCompletPrefereInit() {
		if(!mereNomCompletPrefereCouverture.dejaInitialise) {
			_mereNomCompletPrefere(mereNomCompletPrefereCouverture);
			if(mereNomCompletPrefere == null)
				setMereNomCompletPrefere(mereNomCompletPrefereCouverture.o);
		}
		mereNomCompletPrefereCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipMereNomCompletPrefere() {
		return null;
	}

	public String htmMereNomCompletPrefere() {
		return mereNomCompletPrefere == null ? "" : StringEscapeUtils.escapeHtml4(strMereNomCompletPrefere());
	}

	public void inputMereNomCompletPrefere(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "NomAffichage.enUS: ")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_mereNomCompletPrefere");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setMereNomCompletPrefere classPaiementScolaire inputPaiementScolaire", pk, "MereNomCompletPrefere w3-input w3-border ");
					a("name", "setMereNomCompletPrefere");
				} else {
					a("class", "valeurMereNomCompletPrefere w3-input w3-border classPaiementScolaire inputPaiementScolaire", pk, "MereNomCompletPrefere w3-input w3-border ");
					a("name", "mereNomCompletPrefere");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setMereNomCompletPrefere', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_mereNomCompletPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_mereNomCompletPrefere')); }); ");
				}
				a("value", strMereNomCompletPrefere())
			.fg();

		}
	}

	public void htmMereNomCompletPrefere(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireMereNomCompletPrefere").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_mereNomCompletPrefere").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputMereNomCompletPrefere(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_mereNomCompletPrefere')); $('#", classeApiMethodeMethode, "_mereNomCompletPrefere').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setMereNomCompletPrefere', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_mereNomCompletPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_mereNomCompletPrefere')); }); ")
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
	// pereNomCompletPrefere //
	///////////////////////////

	/**	L'entité « pereNomCompletPrefere »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pereNomCompletPrefere;
	@JsonIgnore
	public Couverture<String> pereNomCompletPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("pereNomCompletPrefere").o(pereNomCompletPrefere);

	/**	<br/>L'entité « pereNomCompletPrefere »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereNomCompletPrefere">Trouver l'entité pereNomCompletPrefere dans Solr</a>
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
	protected PaiementScolaire pereNomCompletPrefereInit() {
		if(!pereNomCompletPrefereCouverture.dejaInitialise) {
			_pereNomCompletPrefere(pereNomCompletPrefereCouverture);
			if(pereNomCompletPrefere == null)
				setPereNomCompletPrefere(pereNomCompletPrefereCouverture.o);
		}
		pereNomCompletPrefereCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "NomAffichage.enUS: ";
	}

	public String htmTooltipPereNomCompletPrefere() {
		return null;
	}

	public String htmPereNomCompletPrefere() {
		return pereNomCompletPrefere == null ? "" : StringEscapeUtils.escapeHtml4(strPereNomCompletPrefere());
	}

	public void inputPereNomCompletPrefere(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "NomAffichage.enUS: ")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_pereNomCompletPrefere");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPereNomCompletPrefere classPaiementScolaire inputPaiementScolaire", pk, "PereNomCompletPrefere w3-input w3-border ");
					a("name", "setPereNomCompletPrefere");
				} else {
					a("class", "valeurPereNomCompletPrefere w3-input w3-border classPaiementScolaire inputPaiementScolaire", pk, "PereNomCompletPrefere w3-input w3-border ");
					a("name", "pereNomCompletPrefere");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPereNomCompletPrefere', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_pereNomCompletPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_pereNomCompletPrefere')); }); ");
				}
				a("value", strPereNomCompletPrefere())
			.fg();

		}
	}

	public void htmPereNomCompletPrefere(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolairePereNomCompletPrefere").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_pereNomCompletPrefere").a("class", "").f().sx("NomAffichage.enUS: ").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPereNomCompletPrefere(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_pereNomCompletPrefere')); $('#", classeApiMethodeMethode, "_pereNomCompletPrefere').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setPereNomCompletPrefere', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_pereNomCompletPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_pereNomCompletPrefere')); }); ")
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

	//////////////
	// ecoleNom //
	//////////////

	/**	L'entité « ecoleNom »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleNom;
	@JsonIgnore
	public Couverture<String> ecoleNomCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNom").o(ecoleNom);

	/**	<br/>L'entité « ecoleNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
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
	protected PaiementScolaire ecoleNomInit() {
		if(!ecoleNomCouverture.dejaInitialise) {
			_ecoleNom(ecoleNomCouverture);
			if(ecoleNom == null)
				setEcoleNom(ecoleNomCouverture.o);
		}
		ecoleNomCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected String ecoleNomComplet;
	@JsonIgnore
	public Couverture<String> ecoleNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNomComplet").o(ecoleNomComplet);

	/**	<br/>L'entité « ecoleNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
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
	protected PaiementScolaire ecoleNomCompletInit() {
		if(!ecoleNomCompletCouverture.dejaInitialise) {
			_ecoleNomComplet(ecoleNomCompletCouverture);
			if(ecoleNomComplet == null)
				setEcoleNomComplet(ecoleNomCompletCouverture.o);
		}
		ecoleNomCompletCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected String ecoleEmplacement;
	@JsonIgnore
	public Couverture<String> ecoleEmplacementCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleEmplacement").o(ecoleEmplacement);

	/**	<br/>L'entité « ecoleEmplacement »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleEmplacement">Trouver l'entité ecoleEmplacement dans Solr</a>
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
	protected PaiementScolaire ecoleEmplacementInit() {
		if(!ecoleEmplacementCouverture.dejaInitialise) {
			_ecoleEmplacement(ecoleEmplacementCouverture);
			if(ecoleEmplacement == null)
				setEcoleEmplacement(ecoleEmplacementCouverture.o);
		}
		ecoleEmplacementCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	////////////////
	// anneeDebut //
	////////////////

	/**	L'entité « anneeDebut »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer anneeDebut;
	@JsonIgnore
	public Couverture<Integer> anneeDebutCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeDebut").o(anneeDebut);

	/**	<br/>L'entité « anneeDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
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
	public PaiementScolaire setAnneeDebut(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeDebut = Integer.parseInt(o);
		this.anneeDebutCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire anneeDebutInit() {
		if(!anneeDebutCouverture.dejaInitialise) {
			_anneeDebut(anneeDebutCouverture);
			if(anneeDebut == null)
				setAnneeDebut(anneeDebutCouverture.o);
		}
		anneeDebutCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected Integer anneeFin;
	@JsonIgnore
	public Couverture<Integer> anneeFinCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeFin").o(anneeFin);

	/**	<br/>L'entité « anneeFin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
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
	public PaiementScolaire setAnneeFin(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeFin = Integer.parseInt(o);
		this.anneeFinCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire anneeFinInit() {
		if(!anneeFinCouverture.dejaInitialise) {
			_anneeFin(anneeFinCouverture);
			if(anneeFin == null)
				setAnneeFin(anneeFinCouverture.o);
		}
		anneeFinCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	/**	L'entité « saisonDateDebut »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate saisonDateDebut;
	@JsonIgnore
	public Couverture<LocalDate> saisonDateDebutCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("saisonDateDebut").o(saisonDateDebut);

	/**	<br/>L'entité « saisonDateDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonDateDebut">Trouver l'entité saisonDateDebut dans Solr</a>
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
	public PaiementScolaire setSaisonDateDebut(Instant o) {
		this.saisonDateDebut = o == null ? null : LocalDate.from(o);
		this.saisonDateDebutCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public PaiementScolaire setSaisonDateDebut(String o) {
		this.saisonDateDebut = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.saisonDateDebutCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setSaisonDateDebut(Date o) {
		this.saisonDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.saisonDateDebutCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire saisonDateDebutInit() {
		if(!saisonDateDebutCouverture.dejaInitialise) {
			_saisonDateDebut(saisonDateDebutCouverture);
			if(saisonDateDebut == null)
				setSaisonDateDebut(saisonDateDebutCouverture.o);
		}
		saisonDateDebutCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	///////////////
	// saisonEte //
	///////////////

	/**	L'entité « saisonEte »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean saisonEte;
	@JsonIgnore
	public Couverture<Boolean> saisonEteCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonEte").o(saisonEte);

	/**	<br/>L'entité « saisonEte »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonEte">Trouver l'entité saisonEte dans Solr</a>
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
	public PaiementScolaire setSaisonEte(String o) {
		this.saisonEte = Boolean.parseBoolean(o);
		this.saisonEteCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire saisonEteInit() {
		if(!saisonEteCouverture.dejaInitialise) {
			_saisonEte(saisonEteCouverture);
			if(saisonEte == null)
				setSaisonEte(saisonEteCouverture.o);
		}
		saisonEteCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected Boolean saisonHiver;
	@JsonIgnore
	public Couverture<Boolean> saisonHiverCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonHiver").o(saisonHiver);

	/**	<br/>L'entité « saisonHiver »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonHiver">Trouver l'entité saisonHiver dans Solr</a>
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
	public PaiementScolaire setSaisonHiver(String o) {
		this.saisonHiver = Boolean.parseBoolean(o);
		this.saisonHiverCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire saisonHiverInit() {
		if(!saisonHiverCouverture.dejaInitialise) {
			_saisonHiver(saisonHiverCouverture);
			if(saisonHiver == null)
				setSaisonHiver(saisonHiverCouverture.o);
		}
		saisonHiverCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal anneeFraisInscription;
	@JsonIgnore
	public Couverture<BigDecimal> anneeFraisInscriptionCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("anneeFraisInscription").o(anneeFraisInscription);

	/**	<br/>L'entité « anneeFraisInscription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFraisInscription">Trouver l'entité anneeFraisInscription dans Solr</a>
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
	public PaiementScolaire setAnneeFraisInscription(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setAnneeFraisInscription(Double o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setAnneeFraisInscription(Integer o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire anneeFraisInscriptionInit() {
		if(!anneeFraisInscriptionCouverture.dejaInitialise) {
			_anneeFraisInscription(anneeFraisInscriptionCouverture);
			if(anneeFraisInscription == null)
				setAnneeFraisInscription(anneeFraisInscriptionCouverture.o);
		}
		anneeFraisInscriptionCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	/**	L'entité « sessionDateDebut »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionDateDebut;
	@JsonIgnore
	public Couverture<LocalDate> sessionDateDebutCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("sessionDateDebut").o(sessionDateDebut);

	/**	<br/>L'entité « sessionDateDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionDateDebut">Trouver l'entité sessionDateDebut dans Solr</a>
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
	public PaiementScolaire setSessionDateDebut(Instant o) {
		this.sessionDateDebut = o == null ? null : LocalDate.from(o);
		this.sessionDateDebutCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public PaiementScolaire setSessionDateDebut(String o) {
		this.sessionDateDebut = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionDateDebutCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setSessionDateDebut(Date o) {
		this.sessionDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateDebutCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire sessionDateDebutInit() {
		if(!sessionDateDebutCouverture.dejaInitialise) {
			_sessionDateDebut(sessionDateDebutCouverture);
			if(sessionDateDebut == null)
				setSessionDateDebut(sessionDateDebutCouverture.o);
		}
		sessionDateDebutCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	/**	L'entité « sessionDateFin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionDateFin;
	@JsonIgnore
	public Couverture<LocalDate> sessionDateFinCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("sessionDateFin").o(sessionDateFin);

	/**	<br/>L'entité « sessionDateFin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionDateFin">Trouver l'entité sessionDateFin dans Solr</a>
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
	public PaiementScolaire setSessionDateFin(Instant o) {
		this.sessionDateFin = o == null ? null : LocalDate.from(o);
		this.sessionDateFinCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public PaiementScolaire setSessionDateFin(String o) {
		this.sessionDateFin = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionDateFinCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setSessionDateFin(Date o) {
		this.sessionDateFin = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateFinCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire sessionDateFinInit() {
		if(!sessionDateFinCouverture.dejaInitialise) {
			_sessionDateFin(sessionDateFinCouverture);
			if(sessionDateFin == null)
				setSessionDateFin(sessionDateFinCouverture.o);
		}
		sessionDateFinCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	//////////////
	// ageDebut //
	//////////////

	/**	L'entité « ageDebut »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer ageDebut;
	@JsonIgnore
	public Couverture<Integer> ageDebutCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ageDebut").o(ageDebut);

	/**	<br/>L'entité « ageDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageDebut">Trouver l'entité ageDebut dans Solr</a>
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
	public PaiementScolaire setAgeDebut(String o) {
		if(NumberUtils.isParsable(o))
			this.ageDebut = Integer.parseInt(o);
		this.ageDebutCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire ageDebutInit() {
		if(!ageDebutCouverture.dejaInitialise) {
			_ageDebut(ageDebutCouverture);
			if(ageDebut == null)
				setAgeDebut(ageDebutCouverture.o);
		}
		ageDebutCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected Integer ageFin;
	@JsonIgnore
	public Couverture<Integer> ageFinCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ageFin").o(ageFin);

	/**	<br/>L'entité « ageFin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageFin">Trouver l'entité ageFin dans Solr</a>
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
	public PaiementScolaire setAgeFin(String o) {
		if(NumberUtils.isParsable(o))
			this.ageFin = Integer.parseInt(o);
		this.ageFinCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire ageFinInit() {
		if(!ageFinCouverture.dejaInitialise) {
			_ageFin(ageFinCouverture);
			if(ageFin == null)
				setAgeFin(ageFinCouverture.o);
		}
		ageFinCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected LocalTime blocHeureDebut;
	@JsonIgnore
	public Couverture<LocalTime> blocHeureDebutCouverture = new Couverture<LocalTime>().p(this).c(LocalTime.class).var("blocHeureDebut").o(blocHeureDebut);

	/**	<br/>L'entité « blocHeureDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocHeureDebut">Trouver l'entité blocHeureDebut dans Solr</a>
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
	public PaiementScolaire setBlocHeureDebut(String o) {
		try {
			this.blocHeureDebut = o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
			this.blocHeureDebutCouverture.dejaInitialise = true;
		} catch(Exception e) {
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire blocHeureDebutInit() {
		if(!blocHeureDebutCouverture.dejaInitialise) {
			_blocHeureDebut(blocHeureDebutCouverture);
			if(blocHeureDebut == null)
				setBlocHeureDebut(blocHeureDebutCouverture.o);
		}
		blocHeureDebutCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	/**	L'entité « blocHeureFin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected LocalTime blocHeureFin;
	@JsonIgnore
	public Couverture<LocalTime> blocHeureFinCouverture = new Couverture<LocalTime>().p(this).c(LocalTime.class).var("blocHeureFin").o(blocHeureFin);

	/**	<br/>L'entité « blocHeureFin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocHeureFin">Trouver l'entité blocHeureFin dans Solr</a>
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
	public PaiementScolaire setBlocHeureFin(String o) {
		try {
			this.blocHeureFin = o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
			this.blocHeureFinCouverture.dejaInitialise = true;
		} catch(Exception e) {
		}
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire blocHeureFinInit() {
		if(!blocHeureFinCouverture.dejaInitialise) {
			_blocHeureFin(blocHeureFinCouverture);
			if(blocHeureFin == null)
				setBlocHeureFin(blocHeureFinCouverture.o);
		}
		blocHeureFinCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	/**	L'entité « blocPrixParMois »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal blocPrixParMois;
	@JsonIgnore
	public Couverture<BigDecimal> blocPrixParMoisCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("blocPrixParMois").o(blocPrixParMois);

	/**	<br/>L'entité « blocPrixParMois »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocPrixParMois">Trouver l'entité blocPrixParMois dans Solr</a>
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
	public PaiementScolaire setBlocPrixParMois(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blocPrixParMois = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixParMoisCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setBlocPrixParMois(Double o) {
			this.blocPrixParMois = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixParMoisCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setBlocPrixParMois(Integer o) {
			this.blocPrixParMois = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixParMoisCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire blocPrixParMoisInit() {
		if(!blocPrixParMoisCouverture.dejaInitialise) {
			_blocPrixParMois(blocPrixParMoisCouverture);
			if(blocPrixParMois == null)
				setBlocPrixParMois(blocPrixParMoisCouverture.o);
		}
		blocPrixParMoisCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	///////////////////
	// blocPrixTotal //
	///////////////////

	/**	L'entité « blocPrixTotal »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal blocPrixTotal;
	@JsonIgnore
	public Couverture<BigDecimal> blocPrixTotalCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("blocPrixTotal").o(blocPrixTotal);

	/**	<br/>L'entité « blocPrixTotal »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocPrixTotal">Trouver l'entité blocPrixTotal dans Solr</a>
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
	public PaiementScolaire setBlocPrixTotal(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.blocPrixTotal = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixTotalCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setBlocPrixTotal(Double o) {
			this.blocPrixTotal = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixTotalCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setBlocPrixTotal(Integer o) {
			this.blocPrixTotal = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.blocPrixTotalCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire blocPrixTotalInit() {
		if(!blocPrixTotalCouverture.dejaInitialise) {
			_blocPrixTotal(blocPrixTotalCouverture);
			if(blocPrixTotal == null)
				setBlocPrixTotal(blocPrixTotalCouverture.o);
		}
		blocPrixTotalCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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

	//////////////////////////////////
	// inscriptionPaimentChaqueMois //
	//////////////////////////////////

	/**	L'entité « inscriptionPaimentChaqueMois »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean inscriptionPaimentChaqueMois;
	@JsonIgnore
	public Couverture<Boolean> inscriptionPaimentChaqueMoisCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("inscriptionPaimentChaqueMois").o(inscriptionPaimentChaqueMois);

	/**	<br/>L'entité « inscriptionPaimentChaqueMois »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionPaimentChaqueMois">Trouver l'entité inscriptionPaimentChaqueMois dans Solr</a>
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
	public PaiementScolaire setInscriptionPaimentChaqueMois(String o) {
		this.inscriptionPaimentChaqueMois = Boolean.parseBoolean(o);
		this.inscriptionPaimentChaqueMoisCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire inscriptionPaimentChaqueMoisInit() {
		if(!inscriptionPaimentChaqueMoisCouverture.dejaInitialise) {
			_inscriptionPaimentChaqueMois(inscriptionPaimentChaqueMoisCouverture);
			if(inscriptionPaimentChaqueMois == null)
				setInscriptionPaimentChaqueMois(inscriptionPaimentChaqueMoisCouverture.o);
		}
		inscriptionPaimentChaqueMoisCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		PaiementScolaire s = (PaiementScolaire)this;
		{
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
				a("class", "setInscriptionPaimentChaqueMois classPaiementScolaire inputPaiementScolaire", pk, "InscriptionPaimentChaqueMois w3-input w3-border ");
				a("name", "setInscriptionPaimentChaqueMois");
			} else {
				a("class", "valeurInscriptionPaimentChaqueMois classPaiementScolaire inputPaiementScolaire", pk, "InscriptionPaimentChaqueMois w3-input w3-border ");
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

		}
	}

	public void htmInscriptionPaimentChaqueMois(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireInscriptionPaimentChaqueMois").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
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
	@JsonInclude(Include.NON_NULL)
	protected Boolean inscriptionPaimentComplet;
	@JsonIgnore
	public Couverture<Boolean> inscriptionPaimentCompletCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("inscriptionPaimentComplet").o(inscriptionPaimentComplet);

	/**	<br/>L'entité « inscriptionPaimentComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionPaimentComplet">Trouver l'entité inscriptionPaimentComplet dans Solr</a>
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
	public PaiementScolaire setInscriptionPaimentComplet(String o) {
		this.inscriptionPaimentComplet = Boolean.parseBoolean(o);
		this.inscriptionPaimentCompletCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire inscriptionPaimentCompletInit() {
		if(!inscriptionPaimentCompletCouverture.dejaInitialise) {
			_inscriptionPaimentComplet(inscriptionPaimentCompletCouverture);
			if(inscriptionPaimentComplet == null)
				setInscriptionPaimentComplet(inscriptionPaimentCompletCouverture.o);
		}
		inscriptionPaimentCompletCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		PaiementScolaire s = (PaiementScolaire)this;
		{
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
				a("class", "setInscriptionPaimentComplet classPaiementScolaire inputPaiementScolaire", pk, "InscriptionPaimentComplet w3-input w3-border ");
				a("name", "setInscriptionPaimentComplet");
			} else {
				a("class", "valeurInscriptionPaimentComplet classPaiementScolaire inputPaiementScolaire", pk, "InscriptionPaimentComplet w3-input w3-border ");
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

		}
	}

	public void htmInscriptionPaimentComplet(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireInscriptionPaimentComplet").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
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

	/////////////////////////
	// paiementDescription //
	/////////////////////////

	/**	L'entité « paiementDescription »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paiementDescription;
	@JsonIgnore
	public Couverture<String> paiementDescriptionCouverture = new Couverture<String>().p(this).c(String.class).var("paiementDescription").o(paiementDescription);

	/**	<br/>L'entité « paiementDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementDescription">Trouver l'entité paiementDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementDescription(Couverture<String> c);

	public String getPaiementDescription() {
		return paiementDescription;
	}

	public void setPaiementDescription(String paiementDescription) {
		this.paiementDescription = paiementDescription;
		this.paiementDescriptionCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire paiementDescriptionInit() {
		if(!paiementDescriptionCouverture.dejaInitialise) {
			_paiementDescription(paiementDescriptionCouverture);
			if(paiementDescription == null)
				setPaiementDescription(paiementDescriptionCouverture.o);
		}
		paiementDescriptionCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public String solrPaiementDescription() {
		return paiementDescription;
	}

	public String strPaiementDescription() {
		return paiementDescription == null ? "" : paiementDescription;
	}

	public String jsonPaiementDescription() {
		return paiementDescription == null ? "" : paiementDescription;
	}

	public String nomAffichagePaiementDescription() {
		return "description";
	}

	public String htmTooltipPaiementDescription() {
		return null;
	}

	public String htmPaiementDescription() {
		return paiementDescription == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementDescription());
	}

	public void inputPaiementDescription(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "description")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_paiementDescription");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPaiementDescription classPaiementScolaire inputPaiementScolaire", pk, "PaiementDescription w3-input w3-border ");
					a("name", "setPaiementDescription");
				} else {
					a("class", "valeurPaiementDescription w3-input w3-border classPaiementScolaire inputPaiementScolaire", pk, "PaiementDescription w3-input w3-border ");
					a("name", "paiementDescription");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementDescription', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDescription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDescription')); }); ");
				}
				a("value", strPaiementDescription())
			.fg();

		}
	}

	public void htmPaiementDescription(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolairePaiementDescription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementDescription").a("class", "").f().sx("description").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementDescription(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementDescription')); $('#", classeApiMethodeMethode, "_paiementDescription').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setPaiementDescription', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDescription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDescription')); }); ")
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
	// paiementDate //
	//////////////////

	/**	L'entité « paiementDate »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate paiementDate;
	@JsonIgnore
	public Couverture<LocalDate> paiementDateCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("paiementDate").o(paiementDate);

	/**	<br/>L'entité « paiementDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementDate">Trouver l'entité paiementDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementDate(Couverture<LocalDate> c);

	public LocalDate getPaiementDate() {
		return paiementDate;
	}

	public void setPaiementDate(LocalDate paiementDate) {
		this.paiementDate = paiementDate;
		this.paiementDateCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementDate(Instant o) {
		this.paiementDate = o == null ? null : LocalDate.from(o);
		this.paiementDateCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public PaiementScolaire setPaiementDate(String o) {
		this.paiementDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.paiementDateCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setPaiementDate(Date o) {
		this.paiementDate = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.paiementDateCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementDateInit() {
		if(!paiementDateCouverture.dejaInitialise) {
			_paiementDate(paiementDateCouverture);
			if(paiementDate == null)
				setPaiementDate(paiementDateCouverture.o);
		}
		paiementDateCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Date solrPaiementDate() {
		return paiementDate == null ? null : Date.from(paiementDate.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strPaiementDate() {
		return paiementDate == null ? "" : paiementDate.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonPaiementDate() {
		return paiementDate == null ? "" : paiementDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichagePaiementDate() {
		return "date de paiement";
	}

	public String htmTooltipPaiementDate() {
		return null;
	}

	public String htmPaiementDate() {
		return paiementDate == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementDate());
	}

	public void inputPaiementDate(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setPaiementDate classPaiementScolaire inputPaiementScolaire", pk, "PaiementDate w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "dd-MM-yyyy")
				.a("id", classeApiMethodeMethode, "_paiementDate")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "La clé primaire des enfants dans la base de données.  (DD-MM-YYYY)")
				.a("value", paiementDate == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(paiementDate))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementDate', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDate')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDate')); }); } ")
				.fg();
		}
	}

	public void htmPaiementDate(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolairePaiementDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementDate").a("class", "").f().sx("date de paiement").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputPaiementDate(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementDate')); $('#", classeApiMethodeMethode, "_paiementDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setPaiementDate', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDate')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDate')); }); ")
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
	// paiementMontant //
	/////////////////////

	/**	L'entité « paiementMontant »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal paiementMontant;
	@JsonIgnore
	public Couverture<BigDecimal> paiementMontantCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("paiementMontant").o(paiementMontant);

	/**	<br/>L'entité « paiementMontant »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementMontant">Trouver l'entité paiementMontant dans Solr</a>
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
	public PaiementScolaire setPaiementMontant(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.paiementMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementMontantCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setPaiementMontant(Double o) {
			this.paiementMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementMontantCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setPaiementMontant(Integer o) {
			this.paiementMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.paiementMontantCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementMontantInit() {
		if(!paiementMontantCouverture.dejaInitialise) {
			_paiementMontant(paiementMontantCouverture);
			if(paiementMontant == null)
				setPaiementMontant(paiementMontantCouverture.o);
		}
		paiementMontantCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "paiement montant";
	}

	public String htmTooltipPaiementMontant() {
		return null;
	}

	public String htmPaiementMontant() {
		return paiementMontant == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementMontant());
	}

	public void inputPaiementMontant(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "paiement montant")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_paiementMontant");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPaiementMontant classPaiementScolaire inputPaiementScolaire", pk, "PaiementMontant w3-input w3-border ");
					a("name", "setPaiementMontant");
				} else {
					a("class", "valeurPaiementMontant w3-input w3-border classPaiementScolaire inputPaiementScolaire", pk, "PaiementMontant w3-input w3-border ");
					a("name", "paiementMontant");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementMontant', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementMontant')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementMontant')); }); ");
				}
				a("value", strPaiementMontant())
			.fg();

		}
	}

	public void htmPaiementMontant(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolairePaiementMontant").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementMontant").a("class", "").f().sx("paiement montant").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementMontant(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementMontant')); $('#", classeApiMethodeMethode, "_paiementMontant').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setPaiementMontant', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementMontant')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementMontant')); }); ")
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
	// paiementEspeces //
	/////////////////////

	/**	L'entité « paiementEspeces »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementEspeces;
	@JsonIgnore
	public Couverture<Boolean> paiementEspecesCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementEspeces").o(paiementEspeces);

	/**	<br/>L'entité « paiementEspeces »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementEspeces">Trouver l'entité paiementEspeces dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementEspeces(Couverture<Boolean> c);

	public Boolean getPaiementEspeces() {
		return paiementEspeces;
	}

	public void setPaiementEspeces(Boolean paiementEspeces) {
		this.paiementEspeces = paiementEspeces;
		this.paiementEspecesCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementEspeces(String o) {
		this.paiementEspeces = Boolean.parseBoolean(o);
		this.paiementEspecesCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementEspecesInit() {
		if(!paiementEspecesCouverture.dejaInitialise) {
			_paiementEspeces(paiementEspecesCouverture);
			if(paiementEspeces == null)
				setPaiementEspeces(paiementEspecesCouverture.o);
		}
		paiementEspecesCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Boolean solrPaiementEspeces() {
		return paiementEspeces;
	}

	public String strPaiementEspeces() {
		return paiementEspeces == null ? "" : paiementEspeces.toString();
	}

	public String jsonPaiementEspeces() {
		return paiementEspeces == null ? "" : paiementEspeces.toString();
	}

	public String nomAffichagePaiementEspeces() {
		return "espèces";
	}

	public String htmTooltipPaiementEspeces() {
		return null;
	}

	public String htmPaiementEspeces() {
		return paiementEspeces == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementEspeces());
	}

	public void inputPaiementEspeces(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_paiementEspeces")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_paiementEspeces");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPaiementEspeces classPaiementScolaire inputPaiementScolaire", pk, "PaiementEspeces w3-input w3-border ");
				a("name", "setPaiementEspeces");
			} else {
				a("class", "valeurPaiementEspeces classPaiementScolaire inputPaiementScolaire", pk, "PaiementEspeces w3-input w3-border ");
				a("name", "paiementEspeces");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementEspeces', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementEspeces')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementEspeces')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPaiementEspeces() != null && getPaiementEspeces())
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
	}

	public void htmPaiementEspeces(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolairePaiementEspeces").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementEspeces").a("class", "").f().sx("espèces").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementEspeces(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////
	// paiementCheque //
	////////////////////

	/**	L'entité « paiementCheque »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementCheque;
	@JsonIgnore
	public Couverture<Boolean> paiementChequeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementCheque").o(paiementCheque);

	/**	<br/>L'entité « paiementCheque »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementCheque">Trouver l'entité paiementCheque dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementCheque(Couverture<Boolean> c);

	public Boolean getPaiementCheque() {
		return paiementCheque;
	}

	public void setPaiementCheque(Boolean paiementCheque) {
		this.paiementCheque = paiementCheque;
		this.paiementChequeCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementCheque(String o) {
		this.paiementCheque = Boolean.parseBoolean(o);
		this.paiementChequeCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementChequeInit() {
		if(!paiementChequeCouverture.dejaInitialise) {
			_paiementCheque(paiementChequeCouverture);
			if(paiementCheque == null)
				setPaiementCheque(paiementChequeCouverture.o);
		}
		paiementChequeCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Boolean solrPaiementCheque() {
		return paiementCheque;
	}

	public String strPaiementCheque() {
		return paiementCheque == null ? "" : paiementCheque.toString();
	}

	public String jsonPaiementCheque() {
		return paiementCheque == null ? "" : paiementCheque.toString();
	}

	public String nomAffichagePaiementCheque() {
		return "chèque";
	}

	public String htmTooltipPaiementCheque() {
		return null;
	}

	public String htmPaiementCheque() {
		return paiementCheque == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementCheque());
	}

	public void inputPaiementCheque(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_paiementCheque")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_paiementCheque");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPaiementCheque classPaiementScolaire inputPaiementScolaire", pk, "PaiementCheque w3-input w3-border ");
				a("name", "setPaiementCheque");
			} else {
				a("class", "valeurPaiementCheque classPaiementScolaire inputPaiementScolaire", pk, "PaiementCheque w3-input w3-border ");
				a("name", "paiementCheque");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementCheque', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementCheque')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementCheque')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPaiementCheque() != null && getPaiementCheque())
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
	}

	public void htmPaiementCheque(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolairePaiementCheque").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementCheque").a("class", "").f().sx("chèque").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementCheque(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////
	// paiementSysteme //
	/////////////////////

	/**	L'entité « paiementSysteme »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementSysteme;
	@JsonIgnore
	public Couverture<Boolean> paiementSystemeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementSysteme").o(paiementSysteme);

	/**	<br/>L'entité « paiementSysteme »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementSysteme">Trouver l'entité paiementSysteme dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementSysteme(Couverture<Boolean> c);

	public Boolean getPaiementSysteme() {
		return paiementSysteme;
	}

	public void setPaiementSysteme(Boolean paiementSysteme) {
		this.paiementSysteme = paiementSysteme;
		this.paiementSystemeCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementSysteme(String o) {
		this.paiementSysteme = Boolean.parseBoolean(o);
		this.paiementSystemeCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementSystemeInit() {
		if(!paiementSystemeCouverture.dejaInitialise) {
			_paiementSysteme(paiementSystemeCouverture);
			if(paiementSysteme == null)
				setPaiementSysteme(paiementSystemeCouverture.o);
		}
		paiementSystemeCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Boolean solrPaiementSysteme() {
		return paiementSysteme;
	}

	public String strPaiementSysteme() {
		return paiementSysteme == null ? "" : paiementSysteme.toString();
	}

	public String jsonPaiementSysteme() {
		return paiementSysteme == null ? "" : paiementSysteme.toString();
	}

	public String nomAffichagePaiementSysteme() {
		return "authorize.net";
	}

	public String htmTooltipPaiementSysteme() {
		return null;
	}

	public String htmPaiementSysteme() {
		return paiementSysteme == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementSysteme());
	}

	public void inputPaiementSysteme(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_paiementSysteme")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_paiementSysteme");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPaiementSysteme classPaiementScolaire inputPaiementScolaire", pk, "PaiementSysteme w3-input w3-border ");
				a("name", "setPaiementSysteme");
			} else {
				a("class", "valeurPaiementSysteme classPaiementScolaire inputPaiementScolaire", pk, "PaiementSysteme w3-input w3-border ");
				a("name", "paiementSysteme");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementSysteme', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementSysteme')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementSysteme')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPaiementSysteme() != null && getPaiementSysteme())
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
	}

	public void htmPaiementSysteme(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolairePaiementSysteme").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementSysteme").a("class", "").f().sx("authorize.net").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementSysteme(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// paiementPar //
	/////////////////

	/**	L'entité « paiementPar »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paiementPar;
	@JsonIgnore
	public Couverture<String> paiementParCouverture = new Couverture<String>().p(this).c(String.class).var("paiementPar").o(paiementPar);

	/**	<br/>L'entité « paiementPar »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementPar">Trouver l'entité paiementPar dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementPar(Couverture<String> c);

	public String getPaiementPar() {
		return paiementPar;
	}

	public void setPaiementPar(String paiementPar) {
		this.paiementPar = paiementPar;
		this.paiementParCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire paiementParInit() {
		if(!paiementParCouverture.dejaInitialise) {
			_paiementPar(paiementParCouverture);
			if(paiementPar == null)
				setPaiementPar(paiementParCouverture.o);
		}
		paiementParCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public String solrPaiementPar() {
		return paiementPar;
	}

	public String strPaiementPar() {
		return paiementPar == null ? "" : paiementPar;
	}

	public String jsonPaiementPar() {
		return paiementPar == null ? "" : paiementPar;
	}

	public String nomAffichagePaiementPar() {
		return "paiement par/pour";
	}

	public String htmTooltipPaiementPar() {
		return null;
	}

	public String htmPaiementPar() {
		return paiementPar == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementPar());
	}

	public void inputPaiementPar(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "paiement par/pour")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_paiementPar");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPaiementPar classPaiementScolaire inputPaiementScolaire", pk, "PaiementPar w3-input w3-border ");
					a("name", "setPaiementPar");
				} else {
					a("class", "valeurPaiementPar w3-input w3-border classPaiementScolaire inputPaiementScolaire", pk, "PaiementPar w3-input w3-border ");
					a("name", "paiementPar");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementPar', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementPar')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementPar')); }); ");
				}
				a("value", strPaiementPar())
			.fg();

		}
	}

	public void htmPaiementPar(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolairePaiementPar").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementPar").a("class", "").f().sx("paiement par/pour").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementPar(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementPar')); $('#", classeApiMethodeMethode, "_paiementPar').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setPaiementPar', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementPar')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementPar')); }); ")
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

	///////////////////
	// transactionId //
	///////////////////

	/**	L'entité « transactionId »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String transactionId;
	@JsonIgnore
	public Couverture<String> transactionIdCouverture = new Couverture<String>().p(this).c(String.class).var("transactionId").o(transactionId);

	/**	<br/>L'entité « transactionId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:transactionId">Trouver l'entité transactionId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _transactionId(Couverture<String> c);

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
		this.transactionIdCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire transactionIdInit() {
		if(!transactionIdCouverture.dejaInitialise) {
			_transactionId(transactionIdCouverture);
			if(transactionId == null)
				setTransactionId(transactionIdCouverture.o);
		}
		transactionIdCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public String solrTransactionId() {
		return transactionId;
	}

	public String strTransactionId() {
		return transactionId == null ? "" : transactionId;
	}

	public String jsonTransactionId() {
		return transactionId == null ? "" : transactionId;
	}

	public String nomAffichageTransactionId() {
		return "transaction ID";
	}

	public String htmTooltipTransactionId() {
		return null;
	}

	public String htmTransactionId() {
		return transactionId == null ? "" : StringEscapeUtils.escapeHtml4(strTransactionId());
	}

	public void inputTransactionId(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "transaction ID")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_transactionId");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTransactionId classPaiementScolaire inputPaiementScolaire", pk, "TransactionId w3-input w3-border ");
					a("name", "setTransactionId");
				} else {
					a("class", "valeurTransactionId w3-input w3-border classPaiementScolaire inputPaiementScolaire", pk, "TransactionId w3-input w3-border ");
					a("name", "transactionId");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setTransactionId', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_transactionId')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_transactionId')); }); ");
				}
				a("value", strTransactionId())
			.fg();

		}
	}

	public void htmTransactionId(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireTransactionId").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_transactionId").a("class", "").f().sx("transaction ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTransactionId(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_transactionId')); $('#", classeApiMethodeMethode, "_transactionId').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setTransactionId', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_transactionId')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_transactionId')); }); ")
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
	// customerProfileId //
	///////////////////////

	/**	L'entité « customerProfileId »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId;
	@JsonIgnore
	public Couverture<String> customerProfileIdCouverture = new Couverture<String>().p(this).c(String.class).var("customerProfileId").o(customerProfileId);

	/**	<br/>L'entité « customerProfileId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:customerProfileId">Trouver l'entité customerProfileId dans Solr</a>
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
	protected PaiementScolaire customerProfileIdInit() {
		if(!customerProfileIdCouverture.dejaInitialise) {
			_customerProfileId(customerProfileIdCouverture);
			if(customerProfileId == null)
				setCustomerProfileId(customerProfileIdCouverture.o);
		}
		customerProfileIdCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "customer profile ID")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_customerProfileId");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setCustomerProfileId classPaiementScolaire inputPaiementScolaire", pk, "CustomerProfileId w3-input w3-border ");
					a("name", "setCustomerProfileId");
				} else {
					a("class", "valeurCustomerProfileId w3-input w3-border classPaiementScolaire inputPaiementScolaire", pk, "CustomerProfileId w3-input w3-border ");
					a("name", "customerProfileId");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_customerProfileId')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_customerProfileId')); }); ");
				}
				a("value", strCustomerProfileId())
			.fg();

		}
	}

	public void htmCustomerProfileId(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireCustomerProfileId").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_customerProfileId").a("class", "").f().sx("customer profile ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_customerProfileId')); $('#", classeApiMethodeMethode, "_customerProfileId').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setCustomerProfileId', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_customerProfileId')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_customerProfileId')); }); ")
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
	// transactionStatus //
	///////////////////////

	/**	L'entité « transactionStatus »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String transactionStatus;
	@JsonIgnore
	public Couverture<String> transactionStatusCouverture = new Couverture<String>().p(this).c(String.class).var("transactionStatus").o(transactionStatus);

	/**	<br/>L'entité « transactionStatus »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:transactionStatus">Trouver l'entité transactionStatus dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _transactionStatus(Couverture<String> c);

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
		this.transactionStatusCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire transactionStatusInit() {
		if(!transactionStatusCouverture.dejaInitialise) {
			_transactionStatus(transactionStatusCouverture);
			if(transactionStatus == null)
				setTransactionStatus(transactionStatusCouverture.o);
		}
		transactionStatusCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public String solrTransactionStatus() {
		return transactionStatus;
	}

	public String strTransactionStatus() {
		return transactionStatus == null ? "" : transactionStatus;
	}

	public String jsonTransactionStatus() {
		return transactionStatus == null ? "" : transactionStatus;
	}

	public String nomAffichageTransactionStatus() {
		return "état de transaction";
	}

	public String htmTooltipTransactionStatus() {
		return null;
	}

	public String htmTransactionStatus() {
		return transactionStatus == null ? "" : StringEscapeUtils.escapeHtml4(strTransactionStatus());
	}

	public void inputTransactionStatus(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "état de transaction")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_transactionStatus");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTransactionStatus classPaiementScolaire inputPaiementScolaire", pk, "TransactionStatus w3-input w3-border ");
					a("name", "setTransactionStatus");
				} else {
					a("class", "valeurTransactionStatus w3-input w3-border classPaiementScolaire inputPaiementScolaire", pk, "TransactionStatus w3-input w3-border ");
					a("name", "transactionStatus");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setTransactionStatus', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_transactionStatus')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_transactionStatus')); }); ");
				}
				a("value", strTransactionStatus())
			.fg();

		}
	}

	public void htmTransactionStatus(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireTransactionStatus").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_transactionStatus").a("class", "").f().sx("état de transaction").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTransactionStatus(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_transactionStatus')); $('#", classeApiMethodeMethode, "_transactionStatus').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setTransactionStatus', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_transactionStatus')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_transactionStatus')); }); ")
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
	// paiementRecu //
	//////////////////

	/**	L'entité « paiementRecu »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementRecu;
	@JsonIgnore
	public Couverture<Boolean> paiementRecuCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementRecu").o(paiementRecu);

	/**	<br/>L'entité « paiementRecu »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementRecu">Trouver l'entité paiementRecu dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementRecu(Couverture<Boolean> c);

	public Boolean getPaiementRecu() {
		return paiementRecu;
	}

	public void setPaiementRecu(Boolean paiementRecu) {
		this.paiementRecu = paiementRecu;
		this.paiementRecuCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setPaiementRecu(String o) {
		this.paiementRecu = Boolean.parseBoolean(o);
		this.paiementRecuCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementRecuInit() {
		if(!paiementRecuCouverture.dejaInitialise) {
			_paiementRecu(paiementRecuCouverture);
			if(paiementRecu == null)
				setPaiementRecu(paiementRecuCouverture.o);
		}
		paiementRecuCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Boolean solrPaiementRecu() {
		return paiementRecu;
	}

	public String strPaiementRecu() {
		return paiementRecu == null ? "" : paiementRecu.toString();
	}

	public String jsonPaiementRecu() {
		return paiementRecu == null ? "" : paiementRecu.toString();
	}

	public String nomAffichagePaiementRecu() {
		return "paiement récu";
	}

	public String htmTooltipPaiementRecu() {
		return null;
	}

	public String htmPaiementRecu() {
		return paiementRecu == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementRecu());
	}

	public void inputPaiementRecu(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_paiementRecu")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_paiementRecu");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPaiementRecu classPaiementScolaire inputPaiementScolaire", pk, "PaiementRecu w3-input w3-border ");
				a("name", "setPaiementRecu");
			} else {
				a("class", "valeurPaiementRecu classPaiementScolaire inputPaiementScolaire", pk, "PaiementRecu w3-input w3-border ");
				a("name", "paiementRecu");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementRecu', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementRecu')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementRecu')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPaiementRecu() != null && getPaiementRecu())
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
	}

	public void htmPaiementRecu(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolairePaiementRecu").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementRecu").a("class", "").f().sx("paiement récu").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementRecu(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// fraisMontant //
	//////////////////

	/**	L'entité « fraisMontant »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal fraisMontant;
	@JsonIgnore
	public Couverture<BigDecimal> fraisMontantCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("fraisMontant").o(fraisMontant);

	/**	<br/>L'entité « fraisMontant »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontant">Trouver l'entité fraisMontant dans Solr</a>
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
	public PaiementScolaire setFraisMontant(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.fraisMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setFraisMontant(Double o) {
			this.fraisMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setFraisMontant(Integer o) {
			this.fraisMontant = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire fraisMontantInit() {
		if(!fraisMontantCouverture.dejaInitialise) {
			_fraisMontant(fraisMontantCouverture);
			if(fraisMontant == null)
				setFraisMontant(fraisMontantCouverture.o);
		}
		fraisMontantCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "frais montant";
	}

	public String htmTooltipFraisMontant() {
		return null;
	}

	public String htmFraisMontant() {
		return fraisMontant == null ? "" : StringEscapeUtils.escapeHtml4(strFraisMontant());
	}

	public void inputFraisMontant(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "frais montant")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_fraisMontant");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setFraisMontant classPaiementScolaire inputPaiementScolaire", pk, "FraisMontant w3-input w3-border ");
					a("name", "setFraisMontant");
				} else {
					a("class", "valeurFraisMontant w3-input w3-border classPaiementScolaire inputPaiementScolaire", pk, "FraisMontant w3-input w3-border ");
					a("name", "fraisMontant");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFraisMontant', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_fraisMontant')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_fraisMontant')); }); ");
				}
				a("value", strFraisMontant())
			.fg();

		}
	}

	public void htmFraisMontant(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireFraisMontant").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_fraisMontant").a("class", "").f().sx("frais montant").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFraisMontant(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_fraisMontant')); $('#", classeApiMethodeMethode, "_fraisMontant').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setFraisMontant', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_fraisMontant')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_fraisMontant')); }); ")
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
	// fraisPremierDernier //
	/////////////////////////

	/**	L'entité « fraisPremierDernier »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean fraisPremierDernier;
	@JsonIgnore
	public Couverture<Boolean> fraisPremierDernierCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("fraisPremierDernier").o(fraisPremierDernier);

	/**	<br/>L'entité « fraisPremierDernier »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisPremierDernier">Trouver l'entité fraisPremierDernier dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisPremierDernier(Couverture<Boolean> c);

	public Boolean getFraisPremierDernier() {
		return fraisPremierDernier;
	}

	public void setFraisPremierDernier(Boolean fraisPremierDernier) {
		this.fraisPremierDernier = fraisPremierDernier;
		this.fraisPremierDernierCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setFraisPremierDernier(String o) {
		this.fraisPremierDernier = Boolean.parseBoolean(o);
		this.fraisPremierDernierCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire fraisPremierDernierInit() {
		if(!fraisPremierDernierCouverture.dejaInitialise) {
			_fraisPremierDernier(fraisPremierDernierCouverture);
			if(fraisPremierDernier == null)
				setFraisPremierDernier(fraisPremierDernierCouverture.o);
		}
		fraisPremierDernierCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Boolean solrFraisPremierDernier() {
		return fraisPremierDernier;
	}

	public String strFraisPremierDernier() {
		return fraisPremierDernier == null ? "" : fraisPremierDernier.toString();
	}

	public String jsonFraisPremierDernier() {
		return fraisPremierDernier == null ? "" : fraisPremierDernier.toString();
	}

	public String nomAffichageFraisPremierDernier() {
		return "frais mois premier et dernier";
	}

	public String htmTooltipFraisPremierDernier() {
		return null;
	}

	public String htmFraisPremierDernier() {
		return fraisPremierDernier == null ? "" : StringEscapeUtils.escapeHtml4(strFraisPremierDernier());
	}

	public void inputFraisPremierDernier(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_fraisPremierDernier")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_fraisPremierDernier");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setFraisPremierDernier classPaiementScolaire inputPaiementScolaire", pk, "FraisPremierDernier w3-input w3-border ");
				a("name", "setFraisPremierDernier");
			} else {
				a("class", "valeurFraisPremierDernier classPaiementScolaire inputPaiementScolaire", pk, "FraisPremierDernier w3-input w3-border ");
				a("name", "fraisPremierDernier");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFraisPremierDernier', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_fraisPremierDernier')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_fraisPremierDernier')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getFraisPremierDernier() != null && getFraisPremierDernier())
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
	}

	public void htmFraisPremierDernier(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireFraisPremierDernier").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_fraisPremierDernier").a("class", "").f().sx("frais mois premier et dernier").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFraisPremierDernier(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// fraisInscription //
	//////////////////////

	/**	L'entité « fraisInscription »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean fraisInscription;
	@JsonIgnore
	public Couverture<Boolean> fraisInscriptionCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("fraisInscription").o(fraisInscription);

	/**	<br/>L'entité « fraisInscription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisInscription">Trouver l'entité fraisInscription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisInscription(Couverture<Boolean> c);

	public Boolean getFraisInscription() {
		return fraisInscription;
	}

	public void setFraisInscription(Boolean fraisInscription) {
		this.fraisInscription = fraisInscription;
		this.fraisInscriptionCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setFraisInscription(String o) {
		this.fraisInscription = Boolean.parseBoolean(o);
		this.fraisInscriptionCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire fraisInscriptionInit() {
		if(!fraisInscriptionCouverture.dejaInitialise) {
			_fraisInscription(fraisInscriptionCouverture);
			if(fraisInscription == null)
				setFraisInscription(fraisInscriptionCouverture.o);
		}
		fraisInscriptionCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Boolean solrFraisInscription() {
		return fraisInscription;
	}

	public String strFraisInscription() {
		return fraisInscription == null ? "" : fraisInscription.toString();
	}

	public String jsonFraisInscription() {
		return fraisInscription == null ? "" : fraisInscription.toString();
	}

	public String nomAffichageFraisInscription() {
		return "frais d'inscription";
	}

	public String htmTooltipFraisInscription() {
		return null;
	}

	public String htmFraisInscription() {
		return fraisInscription == null ? "" : StringEscapeUtils.escapeHtml4(strFraisInscription());
	}

	public void inputFraisInscription(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_fraisInscription")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_fraisInscription");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setFraisInscription classPaiementScolaire inputPaiementScolaire", pk, "FraisInscription w3-input w3-border ");
				a("name", "setFraisInscription");
			} else {
				a("class", "valeurFraisInscription classPaiementScolaire inputPaiementScolaire", pk, "FraisInscription w3-input w3-border ");
				a("name", "fraisInscription");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFraisInscription', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_fraisInscription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_fraisInscription')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getFraisInscription() != null && getFraisInscription())
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
	}

	public void htmFraisInscription(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireFraisInscription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_fraisInscription").a("class", "").f().sx("frais d'inscription").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFraisInscription(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////
	// fraisMois //
	///////////////

	/**	L'entité « fraisMois »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean fraisMois;
	@JsonIgnore
	public Couverture<Boolean> fraisMoisCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("fraisMois").o(fraisMois);

	/**	<br/>L'entité « fraisMois »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMois">Trouver l'entité fraisMois dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisMois(Couverture<Boolean> c);

	public Boolean getFraisMois() {
		return fraisMois;
	}

	public void setFraisMois(Boolean fraisMois) {
		this.fraisMois = fraisMois;
		this.fraisMoisCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setFraisMois(String o) {
		this.fraisMois = Boolean.parseBoolean(o);
		this.fraisMoisCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire fraisMoisInit() {
		if(!fraisMoisCouverture.dejaInitialise) {
			_fraisMois(fraisMoisCouverture);
			if(fraisMois == null)
				setFraisMois(fraisMoisCouverture.o);
		}
		fraisMoisCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Boolean solrFraisMois() {
		return fraisMois;
	}

	public String strFraisMois() {
		return fraisMois == null ? "" : fraisMois.toString();
	}

	public String jsonFraisMois() {
		return fraisMois == null ? "" : fraisMois.toString();
	}

	public String nomAffichageFraisMois() {
		return "frais du mois";
	}

	public String htmTooltipFraisMois() {
		return null;
	}

	public String htmFraisMois() {
		return fraisMois == null ? "" : StringEscapeUtils.escapeHtml4(strFraisMois());
	}

	public void inputFraisMois(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_fraisMois")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_fraisMois");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setFraisMois classPaiementScolaire inputPaiementScolaire", pk, "FraisMois w3-input w3-border ");
				a("name", "setFraisMois");
			} else {
				a("class", "valeurFraisMois classPaiementScolaire inputPaiementScolaire", pk, "FraisMois w3-input w3-border ");
				a("name", "fraisMois");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFraisMois', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_fraisMois')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_fraisMois')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getFraisMois() != null && getFraisMois())
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
	}

	public void htmFraisMois(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireFraisMois").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_fraisMois").a("class", "").f().sx("frais du mois").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFraisMois(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// fraisRetard //
	/////////////////

	/**	L'entité « fraisRetard »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean fraisRetard;
	@JsonIgnore
	public Couverture<Boolean> fraisRetardCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("fraisRetard").o(fraisRetard);

	/**	<br/>L'entité « fraisRetard »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisRetard">Trouver l'entité fraisRetard dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisRetard(Couverture<Boolean> c);

	public Boolean getFraisRetard() {
		return fraisRetard;
	}

	public void setFraisRetard(Boolean fraisRetard) {
		this.fraisRetard = fraisRetard;
		this.fraisRetardCouverture.dejaInitialise = true;
	}
	public PaiementScolaire setFraisRetard(String o) {
		this.fraisRetard = Boolean.parseBoolean(o);
		this.fraisRetardCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire fraisRetardInit() {
		if(!fraisRetardCouverture.dejaInitialise) {
			_fraisRetard(fraisRetardCouverture);
			if(fraisRetard == null)
				setFraisRetard(fraisRetardCouverture.o);
		}
		fraisRetardCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public Boolean solrFraisRetard() {
		return fraisRetard;
	}

	public String strFraisRetard() {
		return fraisRetard == null ? "" : fraisRetard.toString();
	}

	public String jsonFraisRetard() {
		return fraisRetard == null ? "" : fraisRetard.toString();
	}

	public String nomAffichageFraisRetard() {
		return "frais de retard";
	}

	public String htmTooltipFraisRetard() {
		return null;
	}

	public String htmFraisRetard() {
		return fraisRetard == null ? "" : StringEscapeUtils.escapeHtml4(strFraisRetard());
	}

	public void inputFraisRetard(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_fraisRetard")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_fraisRetard");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setFraisRetard classPaiementScolaire inputPaiementScolaire", pk, "FraisRetard w3-input w3-border ");
				a("name", "setFraisRetard");
			} else {
				a("class", "valeurFraisRetard classPaiementScolaire inputPaiementScolaire", pk, "FraisRetard w3-input w3-border ");
				a("name", "fraisRetard");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFraisRetard', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_fraisRetard')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_fraisRetard')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getFraisRetard() != null && getFraisRetard())
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
	}

	public void htmFraisRetard(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireFraisRetard").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_fraisRetard").a("class", "").f().sx("frais de retard").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFraisRetard(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////
	// fraisMontantDu //
	////////////////////

	/**	L'entité « fraisMontantDu »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal fraisMontantDu;
	@JsonIgnore
	public Couverture<BigDecimal> fraisMontantDuCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("fraisMontantDu").o(fraisMontantDu);

	/**	<br/>L'entité « fraisMontantDu »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontantDu">Trouver l'entité fraisMontantDu dans Solr</a>
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
	public PaiementScolaire setFraisMontantDu(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.fraisMontantDu = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantDuCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setFraisMontantDu(Double o) {
			this.fraisMontantDu = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantDuCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setFraisMontantDu(Integer o) {
			this.fraisMontantDu = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantDuCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire fraisMontantDuInit() {
		if(!fraisMontantDuCouverture.dejaInitialise) {
			_fraisMontantDu(fraisMontantDuCouverture);
			if(fraisMontantDu == null)
				setFraisMontantDu(fraisMontantDuCouverture.o);
		}
		fraisMontantDuCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "frais montant dû";
	}

	public String htmTooltipFraisMontantDu() {
		return null;
	}

	public String htmFraisMontantDu() {
		return fraisMontantDu == null ? "" : StringEscapeUtils.escapeHtml4(strFraisMontantDu());
	}

	public void inputFraisMontantDu(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		sx(htmFraisMontantDu());
	}

	public void htmFraisMontantDu(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireFraisMontantDu").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_fraisMontantDu").a("class", "").f().sx("frais montant dû").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFraisMontantDu(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////
	// fraisMontantFuture //
	////////////////////////

	/**	L'entité « fraisMontantFuture »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal fraisMontantFuture;
	@JsonIgnore
	public Couverture<BigDecimal> fraisMontantFutureCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("fraisMontantFuture").o(fraisMontantFuture);

	/**	<br/>L'entité « fraisMontantFuture »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontantFuture">Trouver l'entité fraisMontantFuture dans Solr</a>
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
	public PaiementScolaire setFraisMontantFuture(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.fraisMontantFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantFutureCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setFraisMontantFuture(Double o) {
			this.fraisMontantFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantFutureCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	public PaiementScolaire setFraisMontantFuture(Integer o) {
			this.fraisMontantFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.fraisMontantFutureCouverture.dejaInitialise = true;
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire fraisMontantFutureInit() {
		if(!fraisMontantFutureCouverture.dejaInitialise) {
			_fraisMontantFuture(fraisMontantFutureCouverture);
			if(fraisMontantFuture == null)
				setFraisMontantFuture(fraisMontantFutureCouverture.o);
		}
		fraisMontantFutureCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
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
		return "frais montant future";
	}

	public String htmTooltipFraisMontantFuture() {
		return null;
	}

	public String htmFraisMontantFuture() {
		return fraisMontantFuture == null ? "" : StringEscapeUtils.escapeHtml4(strFraisMontantFuture());
	}

	public void inputFraisMontantFuture(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		sx(htmFraisMontantFuture());
	}

	public void htmFraisMontantFuture(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireFraisMontantFuture").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_fraisMontantFuture").a("class", "").f().sx("frais montant future").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFraisMontantFuture(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// paiementNomCourt //
	//////////////////////

	/**	L'entité « paiementNomCourt »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paiementNomCourt;
	@JsonIgnore
	public Couverture<String> paiementNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("paiementNomCourt").o(paiementNomCourt);

	/**	<br/>L'entité « paiementNomCourt »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementNomCourt">Trouver l'entité paiementNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementNomCourt(Couverture<String> c);

	public String getPaiementNomCourt() {
		return paiementNomCourt;
	}

	public void setPaiementNomCourt(String paiementNomCourt) {
		this.paiementNomCourt = paiementNomCourt;
		this.paiementNomCourtCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire paiementNomCourtInit() {
		if(!paiementNomCourtCouverture.dejaInitialise) {
			_paiementNomCourt(paiementNomCourtCouverture);
			if(paiementNomCourt == null)
				setPaiementNomCourt(paiementNomCourtCouverture.o);
		}
		paiementNomCourtCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public String solrPaiementNomCourt() {
		return paiementNomCourt;
	}

	public String strPaiementNomCourt() {
		return paiementNomCourt == null ? "" : paiementNomCourt;
	}

	public String jsonPaiementNomCourt() {
		return paiementNomCourt == null ? "" : paiementNomCourt;
	}

	public String nomAffichagePaiementNomCourt() {
		return "nom";
	}

	public String htmTooltipPaiementNomCourt() {
		return null;
	}

	public String htmPaiementNomCourt() {
		return paiementNomCourt == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementNomCourt());
	}

	public void inputPaiementNomCourt(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "nom")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_paiementNomCourt");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPaiementNomCourt classPaiementScolaire inputPaiementScolaire", pk, "PaiementNomCourt w3-input w3-border ");
					a("name", "setPaiementNomCourt");
				} else {
					a("class", "valeurPaiementNomCourt w3-input w3-border classPaiementScolaire inputPaiementScolaire", pk, "PaiementNomCourt w3-input w3-border ");
					a("name", "paiementNomCourt");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementNomCourt', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementNomCourt')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementNomCourt')); }); ");
				}
				a("value", strPaiementNomCourt())
			.fg();

		}
	}

	public void htmPaiementNomCourt(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolairePaiementNomCourt").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementNomCourt").a("class", "").f().sx("nom").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementNomCourt(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementNomCourt')); $('#", classeApiMethodeMethode, "_paiementNomCourt').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setPaiementNomCourt', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementNomCourt')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementNomCourt')); }); ")
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
	// paiementNomComplet //
	////////////////////////

	/**	L'entité « paiementNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paiementNomComplet;
	@JsonIgnore
	public Couverture<String> paiementNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("paiementNomComplet").o(paiementNomComplet);

	/**	<br/>L'entité « paiementNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementNomComplet">Trouver l'entité paiementNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementNomComplet(Couverture<String> c);

	public String getPaiementNomComplet() {
		return paiementNomComplet;
	}

	public void setPaiementNomComplet(String paiementNomComplet) {
		this.paiementNomComplet = paiementNomComplet;
		this.paiementNomCompletCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire paiementNomCompletInit() {
		if(!paiementNomCompletCouverture.dejaInitialise) {
			_paiementNomComplet(paiementNomCompletCouverture);
			if(paiementNomComplet == null)
				setPaiementNomComplet(paiementNomCompletCouverture.o);
		}
		paiementNomCompletCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public String solrPaiementNomComplet() {
		return paiementNomComplet;
	}

	public String strPaiementNomComplet() {
		return paiementNomComplet == null ? "" : paiementNomComplet;
	}

	public String jsonPaiementNomComplet() {
		return paiementNomComplet == null ? "" : paiementNomComplet;
	}

	public String nomAffichagePaiementNomComplet() {
		return "nom";
	}

	public String htmTooltipPaiementNomComplet() {
		return null;
	}

	public String htmPaiementNomComplet() {
		return paiementNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementNomComplet());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialisePaiementScolaire = false;

	public PaiementScolaire initLoinPaiementScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialisePaiementScolaire) {
			dejaInitialisePaiementScolaire = true;
			initLoinPaiementScolaire();
		}
		return (PaiementScolaire)this;
	}

	public void initLoinPaiementScolaire() {
		initPaiementScolaire();
		super.initLoinCluster(requeteSite_);
	}

	public void initPaiementScolaire() {
		paiementCleInit();
		inscriptionCleInit();
		inscriptionRechercheInit();
		inscription_Init();
		ecoleNumeroInit();
		utilisateurClesInit();
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
		enfantNomCompletPrefereInit();
		enfantDateNaissanceInit();
		mereNomCompletPrefereInit();
		pereNomCompletPrefereInit();
		ecoleNomInit();
		ecoleNomCompletInit();
		ecoleEmplacementInit();
		anneeDebutInit();
		anneeFinInit();
		saisonDateDebutInit();
		saisonEteInit();
		saisonHiverInit();
		anneeFraisInscriptionInit();
		sessionDateDebutInit();
		sessionDateFinInit();
		ageDebutInit();
		ageFinInit();
		blocHeureDebutInit();
		blocHeureFinInit();
		blocPrixParMoisInit();
		blocPrixTotalInit();
		inscriptionPaimentChaqueMoisInit();
		inscriptionPaimentCompletInit();
		paiementDescriptionInit();
		paiementDateInit();
		paiementMontantInit();
		paiementEspecesInit();
		paiementChequeInit();
		paiementSystemeInit();
		paiementParInit();
		transactionIdInit();
		customerProfileIdInit();
		transactionStatusInit();
		paiementRecuInit();
		fraisMontantInit();
		fraisPremierDernierInit();
		fraisInscriptionInit();
		fraisMoisInit();
		fraisRetardInit();
		fraisMontantDuInit();
		fraisMontantFutureInit();
		paiementNomCourtInit();
		paiementNomCompletInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinPaiementScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePaiementScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(inscriptionRecherche != null)
			inscriptionRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSitePaiementScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirPaiementScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirPaiementScolaire(String var) {
		PaiementScolaire oPaiementScolaire = (PaiementScolaire)this;
		switch(var) {
			case "paiementCle":
				return oPaiementScolaire.paiementCle;
			case "inscriptionCle":
				return oPaiementScolaire.inscriptionCle;
			case "inscriptionRecherche":
				return oPaiementScolaire.inscriptionRecherche;
			case "inscription_":
				return oPaiementScolaire.inscription_;
			case "ecoleNumero":
				return oPaiementScolaire.ecoleNumero;
			case "utilisateurCles":
				return oPaiementScolaire.utilisateurCles;
			case "ecoleCle":
				return oPaiementScolaire.ecoleCle;
			case "anneeCle":
				return oPaiementScolaire.anneeCle;
			case "saisonCle":
				return oPaiementScolaire.saisonCle;
			case "sessionCle":
				return oPaiementScolaire.sessionCle;
			case "ageCle":
				return oPaiementScolaire.ageCle;
			case "blocCle":
				return oPaiementScolaire.blocCle;
			case "enfantCle":
				return oPaiementScolaire.enfantCle;
			case "mereCles":
				return oPaiementScolaire.mereCles;
			case "pereCles":
				return oPaiementScolaire.pereCles;
			case "gardienCles":
				return oPaiementScolaire.gardienCles;
			case "enfantNomCompletPrefere":
				return oPaiementScolaire.enfantNomCompletPrefere;
			case "enfantDateNaissance":
				return oPaiementScolaire.enfantDateNaissance;
			case "mereNomCompletPrefere":
				return oPaiementScolaire.mereNomCompletPrefere;
			case "pereNomCompletPrefere":
				return oPaiementScolaire.pereNomCompletPrefere;
			case "ecoleNom":
				return oPaiementScolaire.ecoleNom;
			case "ecoleNomComplet":
				return oPaiementScolaire.ecoleNomComplet;
			case "ecoleEmplacement":
				return oPaiementScolaire.ecoleEmplacement;
			case "anneeDebut":
				return oPaiementScolaire.anneeDebut;
			case "anneeFin":
				return oPaiementScolaire.anneeFin;
			case "saisonDateDebut":
				return oPaiementScolaire.saisonDateDebut;
			case "saisonEte":
				return oPaiementScolaire.saisonEte;
			case "saisonHiver":
				return oPaiementScolaire.saisonHiver;
			case "anneeFraisInscription":
				return oPaiementScolaire.anneeFraisInscription;
			case "sessionDateDebut":
				return oPaiementScolaire.sessionDateDebut;
			case "sessionDateFin":
				return oPaiementScolaire.sessionDateFin;
			case "ageDebut":
				return oPaiementScolaire.ageDebut;
			case "ageFin":
				return oPaiementScolaire.ageFin;
			case "blocHeureDebut":
				return oPaiementScolaire.blocHeureDebut;
			case "blocHeureFin":
				return oPaiementScolaire.blocHeureFin;
			case "blocPrixParMois":
				return oPaiementScolaire.blocPrixParMois;
			case "blocPrixTotal":
				return oPaiementScolaire.blocPrixTotal;
			case "inscriptionPaimentChaqueMois":
				return oPaiementScolaire.inscriptionPaimentChaqueMois;
			case "inscriptionPaimentComplet":
				return oPaiementScolaire.inscriptionPaimentComplet;
			case "paiementDescription":
				return oPaiementScolaire.paiementDescription;
			case "paiementDate":
				return oPaiementScolaire.paiementDate;
			case "paiementMontant":
				return oPaiementScolaire.paiementMontant;
			case "paiementEspeces":
				return oPaiementScolaire.paiementEspeces;
			case "paiementCheque":
				return oPaiementScolaire.paiementCheque;
			case "paiementSysteme":
				return oPaiementScolaire.paiementSysteme;
			case "paiementPar":
				return oPaiementScolaire.paiementPar;
			case "transactionId":
				return oPaiementScolaire.transactionId;
			case "customerProfileId":
				return oPaiementScolaire.customerProfileId;
			case "transactionStatus":
				return oPaiementScolaire.transactionStatus;
			case "paiementRecu":
				return oPaiementScolaire.paiementRecu;
			case "fraisMontant":
				return oPaiementScolaire.fraisMontant;
			case "fraisPremierDernier":
				return oPaiementScolaire.fraisPremierDernier;
			case "fraisInscription":
				return oPaiementScolaire.fraisInscription;
			case "fraisMois":
				return oPaiementScolaire.fraisMois;
			case "fraisRetard":
				return oPaiementScolaire.fraisRetard;
			case "fraisMontantDu":
				return oPaiementScolaire.fraisMontantDu;
			case "fraisMontantFuture":
				return oPaiementScolaire.fraisMontantFuture;
			case "paiementNomCourt":
				return oPaiementScolaire.paiementNomCourt;
			case "paiementNomComplet":
				return oPaiementScolaire.paiementNomComplet;
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
				o = attribuerPaiementScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerPaiementScolaire(String var, Object val) {
		PaiementScolaire oPaiementScolaire = (PaiementScolaire)this;
		switch(var) {
			case "inscriptionCle":
				oPaiementScolaire.setInscriptionCle((Long)val);
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
					o = definirPaiementScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPaiementScolaire(String var, String val) {
		switch(var) {
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
			case "mereNomCompletPrefere":
				if(val != null)
					setMereNomCompletPrefere(val);
				sauvegardes.add(var);
				return val;
			case "pereNomCompletPrefere":
				if(val != null)
					setPereNomCompletPrefere(val);
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
			case "paiementDescription":
				if(val != null)
					setPaiementDescription(val);
				sauvegardes.add(var);
				return val;
			case "paiementDate":
				if(val != null)
					setPaiementDate(val);
				sauvegardes.add(var);
				return val;
			case "paiementMontant":
				if(val != null)
					setPaiementMontant(val);
				sauvegardes.add(var);
				return val;
			case "paiementEspeces":
				if(val != null)
					setPaiementEspeces(val);
				sauvegardes.add(var);
				return val;
			case "paiementCheque":
				if(val != null)
					setPaiementCheque(val);
				sauvegardes.add(var);
				return val;
			case "paiementSysteme":
				if(val != null)
					setPaiementSysteme(val);
				sauvegardes.add(var);
				return val;
			case "paiementPar":
				if(val != null)
					setPaiementPar(val);
				sauvegardes.add(var);
				return val;
			case "transactionId":
				if(val != null)
					setTransactionId(val);
				sauvegardes.add(var);
				return val;
			case "customerProfileId":
				if(val != null)
					setCustomerProfileId(val);
				sauvegardes.add(var);
				return val;
			case "transactionStatus":
				if(val != null)
					setTransactionStatus(val);
				sauvegardes.add(var);
				return val;
			case "paiementRecu":
				if(val != null)
					setPaiementRecu(val);
				sauvegardes.add(var);
				return val;
			case "fraisMontant":
				if(val != null)
					setFraisMontant(val);
				sauvegardes.add(var);
				return val;
			case "fraisPremierDernier":
				if(val != null)
					setFraisPremierDernier(val);
				sauvegardes.add(var);
				return val;
			case "fraisInscription":
				if(val != null)
					setFraisInscription(val);
				sauvegardes.add(var);
				return val;
			case "fraisMois":
				if(val != null)
					setFraisMois(val);
				sauvegardes.add(var);
				return val;
			case "fraisRetard":
				if(val != null)
					setFraisRetard(val);
				sauvegardes.add(var);
				return val;
			case "fraisMontantDu":
				if(val != null)
					setFraisMontantDu(val);
				sauvegardes.add(var);
				return val;
			case "fraisMontantFuture":
				if(val != null)
					setFraisMontantFuture(val);
				sauvegardes.add(var);
				return val;
			case "paiementNomCourt":
				if(val != null)
					setPaiementNomCourt(val);
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
		peuplerPaiementScolaire(solrDocument);
	}
	public void peuplerPaiementScolaire(SolrDocument solrDocument) {
		PaiementScolaire oPaiementScolaire = (PaiementScolaire)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("paiementCle")) {
				Long paiementCle = (Long)solrDocument.get("paiementCle_stored_long");
				if(paiementCle != null)
					oPaiementScolaire.setPaiementCle(paiementCle);
			}

			Long inscriptionCle = (Long)solrDocument.get("inscriptionCle_stored_long");
			if(inscriptionCle != null)
				oPaiementScolaire.setInscriptionCle(inscriptionCle);

			if(sauvegardes.contains("ecoleNumero")) {
				Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
				if(ecoleNumero != null)
					oPaiementScolaire.setEcoleNumero(ecoleNumero);
			}

			if(sauvegardes.contains("utilisateurCles")) {
				List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
				if(utilisateurCles != null)
					oPaiementScolaire.utilisateurCles.addAll(utilisateurCles);
			}

			if(sauvegardes.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oPaiementScolaire.setEcoleCle(ecoleCle);
			}

			if(sauvegardes.contains("anneeCle")) {
				Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
				if(anneeCle != null)
					oPaiementScolaire.setAnneeCle(anneeCle);
			}

			if(sauvegardes.contains("saisonCle")) {
				Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
				if(saisonCle != null)
					oPaiementScolaire.setSaisonCle(saisonCle);
			}

			if(sauvegardes.contains("sessionCle")) {
				Long sessionCle = (Long)solrDocument.get("sessionCle_stored_long");
				if(sessionCle != null)
					oPaiementScolaire.setSessionCle(sessionCle);
			}

			if(sauvegardes.contains("ageCle")) {
				Long ageCle = (Long)solrDocument.get("ageCle_stored_long");
				if(ageCle != null)
					oPaiementScolaire.setAgeCle(ageCle);
			}

			if(sauvegardes.contains("blocCle")) {
				Long blocCle = (Long)solrDocument.get("blocCle_stored_long");
				if(blocCle != null)
					oPaiementScolaire.setBlocCle(blocCle);
			}

			if(sauvegardes.contains("enfantCle")) {
				Long enfantCle = (Long)solrDocument.get("enfantCle_stored_long");
				if(enfantCle != null)
					oPaiementScolaire.setEnfantCle(enfantCle);
			}

			if(sauvegardes.contains("mereCles")) {
				List<Long> mereCles = (List<Long>)solrDocument.get("mereCles_stored_longs");
				if(mereCles != null)
					oPaiementScolaire.mereCles.addAll(mereCles);
			}

			if(sauvegardes.contains("pereCles")) {
				List<Long> pereCles = (List<Long>)solrDocument.get("pereCles_stored_longs");
				if(pereCles != null)
					oPaiementScolaire.pereCles.addAll(pereCles);
			}

			if(sauvegardes.contains("gardienCles")) {
				List<Long> gardienCles = (List<Long>)solrDocument.get("gardienCles_stored_longs");
				if(gardienCles != null)
					oPaiementScolaire.gardienCles.addAll(gardienCles);
			}

			if(sauvegardes.contains("enfantNomCompletPrefere")) {
				String enfantNomCompletPrefere = (String)solrDocument.get("enfantNomCompletPrefere_stored_string");
				if(enfantNomCompletPrefere != null)
					oPaiementScolaire.setEnfantNomCompletPrefere(enfantNomCompletPrefere);
			}

			if(sauvegardes.contains("enfantDateNaissance")) {
				Date enfantDateNaissance = (Date)solrDocument.get("enfantDateNaissance_stored_date");
				if(enfantDateNaissance != null)
					oPaiementScolaire.setEnfantDateNaissance(enfantDateNaissance);
			}

			if(sauvegardes.contains("mereNomCompletPrefere")) {
				String mereNomCompletPrefere = (String)solrDocument.get("mereNomCompletPrefere_stored_string");
				if(mereNomCompletPrefere != null)
					oPaiementScolaire.setMereNomCompletPrefere(mereNomCompletPrefere);
			}

			if(sauvegardes.contains("pereNomCompletPrefere")) {
				String pereNomCompletPrefere = (String)solrDocument.get("pereNomCompletPrefere_stored_string");
				if(pereNomCompletPrefere != null)
					oPaiementScolaire.setPereNomCompletPrefere(pereNomCompletPrefere);
			}

			if(sauvegardes.contains("ecoleNom")) {
				String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
				if(ecoleNom != null)
					oPaiementScolaire.setEcoleNom(ecoleNom);
			}

			if(sauvegardes.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oPaiementScolaire.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardes.contains("ecoleEmplacement")) {
				String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
				if(ecoleEmplacement != null)
					oPaiementScolaire.setEcoleEmplacement(ecoleEmplacement);
			}

			if(sauvegardes.contains("anneeDebut")) {
				Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
				if(anneeDebut != null)
					oPaiementScolaire.setAnneeDebut(anneeDebut);
			}

			if(sauvegardes.contains("anneeFin")) {
				Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
				if(anneeFin != null)
					oPaiementScolaire.setAnneeFin(anneeFin);
			}

			if(sauvegardes.contains("saisonDateDebut")) {
				Date saisonDateDebut = (Date)solrDocument.get("saisonDateDebut_stored_date");
				if(saisonDateDebut != null)
					oPaiementScolaire.setSaisonDateDebut(saisonDateDebut);
			}

			if(sauvegardes.contains("saisonEte")) {
				Boolean saisonEte = (Boolean)solrDocument.get("saisonEte_stored_boolean");
				if(saisonEte != null)
					oPaiementScolaire.setSaisonEte(saisonEte);
			}

			if(sauvegardes.contains("saisonHiver")) {
				Boolean saisonHiver = (Boolean)solrDocument.get("saisonHiver_stored_boolean");
				if(saisonHiver != null)
					oPaiementScolaire.setSaisonHiver(saisonHiver);
			}

			if(sauvegardes.contains("anneeFraisInscription")) {
				Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
				if(anneeFraisInscription != null)
					oPaiementScolaire.setAnneeFraisInscription(anneeFraisInscription);
			}

			if(sauvegardes.contains("sessionDateDebut")) {
				Date sessionDateDebut = (Date)solrDocument.get("sessionDateDebut_stored_date");
				if(sessionDateDebut != null)
					oPaiementScolaire.setSessionDateDebut(sessionDateDebut);
			}

			if(sauvegardes.contains("sessionDateFin")) {
				Date sessionDateFin = (Date)solrDocument.get("sessionDateFin_stored_date");
				if(sessionDateFin != null)
					oPaiementScolaire.setSessionDateFin(sessionDateFin);
			}

			if(sauvegardes.contains("ageDebut")) {
				Integer ageDebut = (Integer)solrDocument.get("ageDebut_stored_int");
				if(ageDebut != null)
					oPaiementScolaire.setAgeDebut(ageDebut);
			}

			if(sauvegardes.contains("ageFin")) {
				Integer ageFin = (Integer)solrDocument.get("ageFin_stored_int");
				if(ageFin != null)
					oPaiementScolaire.setAgeFin(ageFin);
			}

			if(sauvegardes.contains("blocHeureDebut")) {
				String blocHeureDebut = (String)solrDocument.get("blocHeureDebut_stored_string");
				if(blocHeureDebut != null)
					oPaiementScolaire.setBlocHeureDebut(blocHeureDebut);
			}

			if(sauvegardes.contains("blocHeureFin")) {
				String blocHeureFin = (String)solrDocument.get("blocHeureFin_stored_string");
				if(blocHeureFin != null)
					oPaiementScolaire.setBlocHeureFin(blocHeureFin);
			}

			if(sauvegardes.contains("blocPrixParMois")) {
				Double blocPrixParMois = (Double)solrDocument.get("blocPrixParMois_stored_double");
				if(blocPrixParMois != null)
					oPaiementScolaire.setBlocPrixParMois(blocPrixParMois);
			}

			if(sauvegardes.contains("blocPrixTotal")) {
				Double blocPrixTotal = (Double)solrDocument.get("blocPrixTotal_stored_double");
				if(blocPrixTotal != null)
					oPaiementScolaire.setBlocPrixTotal(blocPrixTotal);
			}

			if(sauvegardes.contains("inscriptionPaimentChaqueMois")) {
				Boolean inscriptionPaimentChaqueMois = (Boolean)solrDocument.get("inscriptionPaimentChaqueMois_stored_boolean");
				if(inscriptionPaimentChaqueMois != null)
					oPaiementScolaire.setInscriptionPaimentChaqueMois(inscriptionPaimentChaqueMois);
			}

			if(sauvegardes.contains("inscriptionPaimentComplet")) {
				Boolean inscriptionPaimentComplet = (Boolean)solrDocument.get("inscriptionPaimentComplet_stored_boolean");
				if(inscriptionPaimentComplet != null)
					oPaiementScolaire.setInscriptionPaimentComplet(inscriptionPaimentComplet);
			}

			if(sauvegardes.contains("paiementDescription")) {
				String paiementDescription = (String)solrDocument.get("paiementDescription_stored_string");
				if(paiementDescription != null)
					oPaiementScolaire.setPaiementDescription(paiementDescription);
			}

			if(sauvegardes.contains("paiementDate")) {
				Date paiementDate = (Date)solrDocument.get("paiementDate_stored_date");
				if(paiementDate != null)
					oPaiementScolaire.setPaiementDate(paiementDate);
			}

			if(sauvegardes.contains("paiementMontant")) {
				Double paiementMontant = (Double)solrDocument.get("paiementMontant_stored_double");
				if(paiementMontant != null)
					oPaiementScolaire.setPaiementMontant(paiementMontant);
			}

			if(sauvegardes.contains("paiementEspeces")) {
				Boolean paiementEspeces = (Boolean)solrDocument.get("paiementEspeces_stored_boolean");
				if(paiementEspeces != null)
					oPaiementScolaire.setPaiementEspeces(paiementEspeces);
			}

			if(sauvegardes.contains("paiementCheque")) {
				Boolean paiementCheque = (Boolean)solrDocument.get("paiementCheque_stored_boolean");
				if(paiementCheque != null)
					oPaiementScolaire.setPaiementCheque(paiementCheque);
			}

			if(sauvegardes.contains("paiementSysteme")) {
				Boolean paiementSysteme = (Boolean)solrDocument.get("paiementSysteme_stored_boolean");
				if(paiementSysteme != null)
					oPaiementScolaire.setPaiementSysteme(paiementSysteme);
			}

			if(sauvegardes.contains("paiementPar")) {
				String paiementPar = (String)solrDocument.get("paiementPar_stored_string");
				if(paiementPar != null)
					oPaiementScolaire.setPaiementPar(paiementPar);
			}

			if(sauvegardes.contains("transactionId")) {
				String transactionId = (String)solrDocument.get("transactionId_stored_string");
				if(transactionId != null)
					oPaiementScolaire.setTransactionId(transactionId);
			}

			if(sauvegardes.contains("customerProfileId")) {
				String customerProfileId = (String)solrDocument.get("customerProfileId_stored_string");
				if(customerProfileId != null)
					oPaiementScolaire.setCustomerProfileId(customerProfileId);
			}

			if(sauvegardes.contains("transactionStatus")) {
				String transactionStatus = (String)solrDocument.get("transactionStatus_stored_string");
				if(transactionStatus != null)
					oPaiementScolaire.setTransactionStatus(transactionStatus);
			}

			if(sauvegardes.contains("paiementRecu")) {
				Boolean paiementRecu = (Boolean)solrDocument.get("paiementRecu_stored_boolean");
				if(paiementRecu != null)
					oPaiementScolaire.setPaiementRecu(paiementRecu);
			}

			if(sauvegardes.contains("fraisMontant")) {
				Double fraisMontant = (Double)solrDocument.get("fraisMontant_stored_double");
				if(fraisMontant != null)
					oPaiementScolaire.setFraisMontant(fraisMontant);
			}

			if(sauvegardes.contains("fraisPremierDernier")) {
				Boolean fraisPremierDernier = (Boolean)solrDocument.get("fraisPremierDernier_stored_boolean");
				if(fraisPremierDernier != null)
					oPaiementScolaire.setFraisPremierDernier(fraisPremierDernier);
			}

			if(sauvegardes.contains("fraisInscription")) {
				Boolean fraisInscription = (Boolean)solrDocument.get("fraisInscription_stored_boolean");
				if(fraisInscription != null)
					oPaiementScolaire.setFraisInscription(fraisInscription);
			}

			if(sauvegardes.contains("fraisMois")) {
				Boolean fraisMois = (Boolean)solrDocument.get("fraisMois_stored_boolean");
				if(fraisMois != null)
					oPaiementScolaire.setFraisMois(fraisMois);
			}

			if(sauvegardes.contains("fraisRetard")) {
				Boolean fraisRetard = (Boolean)solrDocument.get("fraisRetard_stored_boolean");
				if(fraisRetard != null)
					oPaiementScolaire.setFraisRetard(fraisRetard);
			}

			if(sauvegardes.contains("fraisMontantDu")) {
				Double fraisMontantDu = (Double)solrDocument.get("fraisMontantDu_stored_double");
				if(fraisMontantDu != null)
					oPaiementScolaire.setFraisMontantDu(fraisMontantDu);
			}

			if(sauvegardes.contains("fraisMontantFuture")) {
				Double fraisMontantFuture = (Double)solrDocument.get("fraisMontantFuture_stored_double");
				if(fraisMontantFuture != null)
					oPaiementScolaire.setFraisMontantFuture(fraisMontantFuture);
			}

			if(sauvegardes.contains("paiementNomCourt")) {
				String paiementNomCourt = (String)solrDocument.get("paiementNomCourt_stored_string");
				if(paiementNomCourt != null)
					oPaiementScolaire.setPaiementNomCourt(paiementNomCourt);
			}

			if(sauvegardes.contains("paiementNomComplet")) {
				String paiementNomComplet = (String)solrDocument.get("paiementNomComplet_stored_string");
				if(paiementNomComplet != null)
					oPaiementScolaire.setPaiementNomComplet(paiementNomComplet);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.paiement.PaiementScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			PaiementScolaire o = new PaiementScolaire();
			o.requeteSitePaiementScolaire(requeteSite);
			o.initLoinPaiementScolaire(requeteSite);
			o.indexerPaiementScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerPaiementScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerPaiementScolaire(document);
	}

	public void indexerPaiementScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerPaiementScolaire(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerPaiementScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerPaiementScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerPaiementScolaire(SolrInputDocument document) {
		if(paiementCle != null) {
			document.addField("paiementCle_indexed_long", paiementCle);
			document.addField("paiementCle_stored_long", paiementCle);
		}
		if(inscriptionCle != null) {
			document.addField("inscriptionCle_indexed_long", inscriptionCle);
			document.addField("inscriptionCle_stored_long", inscriptionCle);
		}
		if(ecoleNumero != null) {
			document.addField("ecoleNumero_indexed_int", ecoleNumero);
			document.addField("ecoleNumero_stored_int", ecoleNumero);
		}
		if(utilisateurCles != null) {
			for(java.lang.Long o : utilisateurCles) {
				document.addField("utilisateurCles_indexed_longs", o);
			}
			for(java.lang.Long o : utilisateurCles) {
				document.addField("utilisateurCles_stored_longs", o);
			}
		}
		if(ecoleCle != null) {
			document.addField("ecoleCle_indexed_long", ecoleCle);
			document.addField("ecoleCle_stored_long", ecoleCle);
		}
		if(anneeCle != null) {
			document.addField("anneeCle_indexed_long", anneeCle);
			document.addField("anneeCle_stored_long", anneeCle);
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
		if(enfantNomCompletPrefere != null) {
			document.addField("enfantNomCompletPrefere_indexed_string", enfantNomCompletPrefere);
			document.addField("enfantNomCompletPrefere_stored_string", enfantNomCompletPrefere);
		}
		if(enfantDateNaissance != null) {
			document.addField("enfantDateNaissance_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enfantDateNaissance.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("enfantDateNaissance_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(enfantDateNaissance.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(mereNomCompletPrefere != null) {
			document.addField("mereNomCompletPrefere_indexed_string", mereNomCompletPrefere);
			document.addField("mereNomCompletPrefere_stored_string", mereNomCompletPrefere);
		}
		if(pereNomCompletPrefere != null) {
			document.addField("pereNomCompletPrefere_indexed_string", pereNomCompletPrefere);
			document.addField("pereNomCompletPrefere_stored_string", pereNomCompletPrefere);
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
		if(sessionDateDebut != null) {
			document.addField("sessionDateDebut_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionDateDebut_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateDebut.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionDateFin != null) {
			document.addField("sessionDateFin_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateFin.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionDateFin_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionDateFin.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
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
		if(blocPrixTotal != null) {
			document.addField("blocPrixTotal_indexed_double", blocPrixTotal.doubleValue());
			document.addField("blocPrixTotal_stored_double", blocPrixTotal.doubleValue());
		}
		if(inscriptionPaimentChaqueMois != null) {
			document.addField("inscriptionPaimentChaqueMois_indexed_boolean", inscriptionPaimentChaqueMois);
			document.addField("inscriptionPaimentChaqueMois_stored_boolean", inscriptionPaimentChaqueMois);
		}
		if(inscriptionPaimentComplet != null) {
			document.addField("inscriptionPaimentComplet_indexed_boolean", inscriptionPaimentComplet);
			document.addField("inscriptionPaimentComplet_stored_boolean", inscriptionPaimentComplet);
		}
		if(paiementDescription != null) {
			document.addField("paiementDescription_indexed_string", paiementDescription);
			document.addField("paiementDescription_stored_string", paiementDescription);
		}
		if(paiementDate != null) {
			document.addField("paiementDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementDate.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("paiementDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementDate.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(paiementMontant != null) {
			document.addField("paiementMontant_indexed_double", paiementMontant.doubleValue());
			document.addField("paiementMontant_stored_double", paiementMontant.doubleValue());
		}
		if(paiementEspeces != null) {
			document.addField("paiementEspeces_indexed_boolean", paiementEspeces);
			document.addField("paiementEspeces_stored_boolean", paiementEspeces);
		}
		if(paiementCheque != null) {
			document.addField("paiementCheque_indexed_boolean", paiementCheque);
			document.addField("paiementCheque_stored_boolean", paiementCheque);
		}
		if(paiementSysteme != null) {
			document.addField("paiementSysteme_indexed_boolean", paiementSysteme);
			document.addField("paiementSysteme_stored_boolean", paiementSysteme);
		}
		if(paiementPar != null) {
			document.addField("paiementPar_indexed_string", paiementPar);
			document.addField("paiementPar_stored_string", paiementPar);
		}
		if(transactionId != null) {
			document.addField("transactionId_indexed_string", transactionId);
			document.addField("transactionId_stored_string", transactionId);
		}
		if(customerProfileId != null) {
			document.addField("customerProfileId_indexed_string", customerProfileId);
			document.addField("customerProfileId_stored_string", customerProfileId);
		}
		if(transactionStatus != null) {
			document.addField("transactionStatus_indexed_string", transactionStatus);
			document.addField("transactionStatus_stored_string", transactionStatus);
		}
		if(paiementRecu != null) {
			document.addField("paiementRecu_indexed_boolean", paiementRecu);
			document.addField("paiementRecu_stored_boolean", paiementRecu);
		}
		if(fraisMontant != null) {
			document.addField("fraisMontant_indexed_double", fraisMontant.doubleValue());
			document.addField("fraisMontant_stored_double", fraisMontant.doubleValue());
		}
		if(fraisPremierDernier != null) {
			document.addField("fraisPremierDernier_indexed_boolean", fraisPremierDernier);
			document.addField("fraisPremierDernier_stored_boolean", fraisPremierDernier);
		}
		if(fraisInscription != null) {
			document.addField("fraisInscription_indexed_boolean", fraisInscription);
			document.addField("fraisInscription_stored_boolean", fraisInscription);
		}
		if(fraisMois != null) {
			document.addField("fraisMois_indexed_boolean", fraisMois);
			document.addField("fraisMois_stored_boolean", fraisMois);
		}
		if(fraisRetard != null) {
			document.addField("fraisRetard_indexed_boolean", fraisRetard);
			document.addField("fraisRetard_stored_boolean", fraisRetard);
		}
		if(fraisMontantDu != null) {
			document.addField("fraisMontantDu_indexed_double", fraisMontantDu.doubleValue());
			document.addField("fraisMontantDu_stored_double", fraisMontantDu.doubleValue());
		}
		if(fraisMontantFuture != null) {
			document.addField("fraisMontantFuture_indexed_double", fraisMontantFuture.doubleValue());
			document.addField("fraisMontantFuture_stored_double", fraisMontantFuture.doubleValue());
		}
		if(paiementNomCourt != null) {
			document.addField("paiementNomCourt_indexed_string", paiementNomCourt);
			document.addField("paiementNomCourt_stored_string", paiementNomCourt);
		}
		if(paiementNomComplet != null) {
			document.addField("paiementNomComplet_indexed_string", paiementNomComplet);
			document.addField("paiementNomComplet_stored_string", paiementNomComplet);
		}
		super.indexerCluster(document);

	}

	public void desindexerPaiementScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinPaiementScolaire(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexePaiementScolaire(String entiteVar) {
		switch(entiteVar) {
			case "paiementCle":
				return "paiementCle_indexed_long";
			case "inscriptionCle":
				return "inscriptionCle_indexed_long";
			case "ecoleNumero":
				return "ecoleNumero_indexed_int";
			case "utilisateurCles":
				return "utilisateurCles_indexed_longs";
			case "ecoleCle":
				return "ecoleCle_indexed_long";
			case "anneeCle":
				return "anneeCle_indexed_long";
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
			case "enfantNomCompletPrefere":
				return "enfantNomCompletPrefere_indexed_string";
			case "enfantDateNaissance":
				return "enfantDateNaissance_indexed_date";
			case "mereNomCompletPrefere":
				return "mereNomCompletPrefere_indexed_string";
			case "pereNomCompletPrefere":
				return "pereNomCompletPrefere_indexed_string";
			case "ecoleNom":
				return "ecoleNom_indexed_string";
			case "ecoleNomComplet":
				return "ecoleNomComplet_indexed_string";
			case "ecoleEmplacement":
				return "ecoleEmplacement_indexed_string";
			case "anneeDebut":
				return "anneeDebut_indexed_int";
			case "anneeFin":
				return "anneeFin_indexed_int";
			case "saisonDateDebut":
				return "saisonDateDebut_indexed_date";
			case "saisonEte":
				return "saisonEte_indexed_boolean";
			case "saisonHiver":
				return "saisonHiver_indexed_boolean";
			case "anneeFraisInscription":
				return "anneeFraisInscription_indexed_double";
			case "sessionDateDebut":
				return "sessionDateDebut_indexed_date";
			case "sessionDateFin":
				return "sessionDateFin_indexed_date";
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
			case "blocPrixTotal":
				return "blocPrixTotal_indexed_double";
			case "inscriptionPaimentChaqueMois":
				return "inscriptionPaimentChaqueMois_indexed_boolean";
			case "inscriptionPaimentComplet":
				return "inscriptionPaimentComplet_indexed_boolean";
			case "paiementDescription":
				return "paiementDescription_indexed_string";
			case "paiementDate":
				return "paiementDate_indexed_date";
			case "paiementMontant":
				return "paiementMontant_indexed_double";
			case "paiementEspeces":
				return "paiementEspeces_indexed_boolean";
			case "paiementCheque":
				return "paiementCheque_indexed_boolean";
			case "paiementSysteme":
				return "paiementSysteme_indexed_boolean";
			case "paiementPar":
				return "paiementPar_indexed_string";
			case "transactionId":
				return "transactionId_indexed_string";
			case "customerProfileId":
				return "customerProfileId_indexed_string";
			case "transactionStatus":
				return "transactionStatus_indexed_string";
			case "paiementRecu":
				return "paiementRecu_indexed_boolean";
			case "fraisMontant":
				return "fraisMontant_indexed_double";
			case "fraisPremierDernier":
				return "fraisPremierDernier_indexed_boolean";
			case "fraisInscription":
				return "fraisInscription_indexed_boolean";
			case "fraisMois":
				return "fraisMois_indexed_boolean";
			case "fraisRetard":
				return "fraisRetard_indexed_boolean";
			case "fraisMontantDu":
				return "fraisMontantDu_indexed_double";
			case "fraisMontantFuture":
				return "fraisMontantFuture_indexed_double";
			case "paiementNomCourt":
				return "paiementNomCourt_indexed_string";
			case "paiementNomComplet":
				return "paiementNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRecherchePaiementScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggerePaiementScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerPaiementScolaire(solrDocument);
	}
	public void stockerPaiementScolaire(SolrDocument solrDocument) {
		PaiementScolaire oPaiementScolaire = (PaiementScolaire)this;

		Long paiementCle = (Long)solrDocument.get("paiementCle_stored_long");
		if(paiementCle != null)
			oPaiementScolaire.setPaiementCle(paiementCle);

		Long inscriptionCle = (Long)solrDocument.get("inscriptionCle_stored_long");
		if(inscriptionCle != null)
			oPaiementScolaire.setInscriptionCle(inscriptionCle);

		Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
		if(ecoleNumero != null)
			oPaiementScolaire.setEcoleNumero(ecoleNumero);

		List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
		if(utilisateurCles != null)
			oPaiementScolaire.utilisateurCles.addAll(utilisateurCles);

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oPaiementScolaire.setEcoleCle(ecoleCle);

		Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
		if(anneeCle != null)
			oPaiementScolaire.setAnneeCle(anneeCle);

		Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
		if(saisonCle != null)
			oPaiementScolaire.setSaisonCle(saisonCle);

		Long sessionCle = (Long)solrDocument.get("sessionCle_stored_long");
		if(sessionCle != null)
			oPaiementScolaire.setSessionCle(sessionCle);

		Long ageCle = (Long)solrDocument.get("ageCle_stored_long");
		if(ageCle != null)
			oPaiementScolaire.setAgeCle(ageCle);

		Long blocCle = (Long)solrDocument.get("blocCle_stored_long");
		if(blocCle != null)
			oPaiementScolaire.setBlocCle(blocCle);

		Long enfantCle = (Long)solrDocument.get("enfantCle_stored_long");
		if(enfantCle != null)
			oPaiementScolaire.setEnfantCle(enfantCle);

		List<Long> mereCles = (List<Long>)solrDocument.get("mereCles_stored_longs");
		if(mereCles != null)
			oPaiementScolaire.mereCles.addAll(mereCles);

		List<Long> pereCles = (List<Long>)solrDocument.get("pereCles_stored_longs");
		if(pereCles != null)
			oPaiementScolaire.pereCles.addAll(pereCles);

		List<Long> gardienCles = (List<Long>)solrDocument.get("gardienCles_stored_longs");
		if(gardienCles != null)
			oPaiementScolaire.gardienCles.addAll(gardienCles);

		String enfantNomCompletPrefere = (String)solrDocument.get("enfantNomCompletPrefere_stored_string");
		if(enfantNomCompletPrefere != null)
			oPaiementScolaire.setEnfantNomCompletPrefere(enfantNomCompletPrefere);

		Date enfantDateNaissance = (Date)solrDocument.get("enfantDateNaissance_stored_date");
		if(enfantDateNaissance != null)
			oPaiementScolaire.setEnfantDateNaissance(enfantDateNaissance);

		String mereNomCompletPrefere = (String)solrDocument.get("mereNomCompletPrefere_stored_string");
		if(mereNomCompletPrefere != null)
			oPaiementScolaire.setMereNomCompletPrefere(mereNomCompletPrefere);

		String pereNomCompletPrefere = (String)solrDocument.get("pereNomCompletPrefere_stored_string");
		if(pereNomCompletPrefere != null)
			oPaiementScolaire.setPereNomCompletPrefere(pereNomCompletPrefere);

		String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
		if(ecoleNom != null)
			oPaiementScolaire.setEcoleNom(ecoleNom);

		String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
		if(ecoleNomComplet != null)
			oPaiementScolaire.setEcoleNomComplet(ecoleNomComplet);

		String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
		if(ecoleEmplacement != null)
			oPaiementScolaire.setEcoleEmplacement(ecoleEmplacement);

		Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
		if(anneeDebut != null)
			oPaiementScolaire.setAnneeDebut(anneeDebut);

		Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
		if(anneeFin != null)
			oPaiementScolaire.setAnneeFin(anneeFin);

		Date saisonDateDebut = (Date)solrDocument.get("saisonDateDebut_stored_date");
		if(saisonDateDebut != null)
			oPaiementScolaire.setSaisonDateDebut(saisonDateDebut);

		Boolean saisonEte = (Boolean)solrDocument.get("saisonEte_stored_boolean");
		if(saisonEte != null)
			oPaiementScolaire.setSaisonEte(saisonEte);

		Boolean saisonHiver = (Boolean)solrDocument.get("saisonHiver_stored_boolean");
		if(saisonHiver != null)
			oPaiementScolaire.setSaisonHiver(saisonHiver);

		Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
		if(anneeFraisInscription != null)
			oPaiementScolaire.setAnneeFraisInscription(anneeFraisInscription);

		Date sessionDateDebut = (Date)solrDocument.get("sessionDateDebut_stored_date");
		if(sessionDateDebut != null)
			oPaiementScolaire.setSessionDateDebut(sessionDateDebut);

		Date sessionDateFin = (Date)solrDocument.get("sessionDateFin_stored_date");
		if(sessionDateFin != null)
			oPaiementScolaire.setSessionDateFin(sessionDateFin);

		Integer ageDebut = (Integer)solrDocument.get("ageDebut_stored_int");
		if(ageDebut != null)
			oPaiementScolaire.setAgeDebut(ageDebut);

		Integer ageFin = (Integer)solrDocument.get("ageFin_stored_int");
		if(ageFin != null)
			oPaiementScolaire.setAgeFin(ageFin);

		String blocHeureDebut = (String)solrDocument.get("blocHeureDebut_stored_string");
		if(blocHeureDebut != null)
			oPaiementScolaire.setBlocHeureDebut(blocHeureDebut);

		String blocHeureFin = (String)solrDocument.get("blocHeureFin_stored_string");
		if(blocHeureFin != null)
			oPaiementScolaire.setBlocHeureFin(blocHeureFin);

		Double blocPrixParMois = (Double)solrDocument.get("blocPrixParMois_stored_double");
		if(blocPrixParMois != null)
			oPaiementScolaire.setBlocPrixParMois(blocPrixParMois);

		Double blocPrixTotal = (Double)solrDocument.get("blocPrixTotal_stored_double");
		if(blocPrixTotal != null)
			oPaiementScolaire.setBlocPrixTotal(blocPrixTotal);

		Boolean inscriptionPaimentChaqueMois = (Boolean)solrDocument.get("inscriptionPaimentChaqueMois_stored_boolean");
		if(inscriptionPaimentChaqueMois != null)
			oPaiementScolaire.setInscriptionPaimentChaqueMois(inscriptionPaimentChaqueMois);

		Boolean inscriptionPaimentComplet = (Boolean)solrDocument.get("inscriptionPaimentComplet_stored_boolean");
		if(inscriptionPaimentComplet != null)
			oPaiementScolaire.setInscriptionPaimentComplet(inscriptionPaimentComplet);

		String paiementDescription = (String)solrDocument.get("paiementDescription_stored_string");
		if(paiementDescription != null)
			oPaiementScolaire.setPaiementDescription(paiementDescription);

		Date paiementDate = (Date)solrDocument.get("paiementDate_stored_date");
		if(paiementDate != null)
			oPaiementScolaire.setPaiementDate(paiementDate);

		Double paiementMontant = (Double)solrDocument.get("paiementMontant_stored_double");
		if(paiementMontant != null)
			oPaiementScolaire.setPaiementMontant(paiementMontant);

		Boolean paiementEspeces = (Boolean)solrDocument.get("paiementEspeces_stored_boolean");
		if(paiementEspeces != null)
			oPaiementScolaire.setPaiementEspeces(paiementEspeces);

		Boolean paiementCheque = (Boolean)solrDocument.get("paiementCheque_stored_boolean");
		if(paiementCheque != null)
			oPaiementScolaire.setPaiementCheque(paiementCheque);

		Boolean paiementSysteme = (Boolean)solrDocument.get("paiementSysteme_stored_boolean");
		if(paiementSysteme != null)
			oPaiementScolaire.setPaiementSysteme(paiementSysteme);

		String paiementPar = (String)solrDocument.get("paiementPar_stored_string");
		if(paiementPar != null)
			oPaiementScolaire.setPaiementPar(paiementPar);

		String transactionId = (String)solrDocument.get("transactionId_stored_string");
		if(transactionId != null)
			oPaiementScolaire.setTransactionId(transactionId);

		String customerProfileId = (String)solrDocument.get("customerProfileId_stored_string");
		if(customerProfileId != null)
			oPaiementScolaire.setCustomerProfileId(customerProfileId);

		String transactionStatus = (String)solrDocument.get("transactionStatus_stored_string");
		if(transactionStatus != null)
			oPaiementScolaire.setTransactionStatus(transactionStatus);

		Boolean paiementRecu = (Boolean)solrDocument.get("paiementRecu_stored_boolean");
		if(paiementRecu != null)
			oPaiementScolaire.setPaiementRecu(paiementRecu);

		Double fraisMontant = (Double)solrDocument.get("fraisMontant_stored_double");
		if(fraisMontant != null)
			oPaiementScolaire.setFraisMontant(fraisMontant);

		Boolean fraisPremierDernier = (Boolean)solrDocument.get("fraisPremierDernier_stored_boolean");
		if(fraisPremierDernier != null)
			oPaiementScolaire.setFraisPremierDernier(fraisPremierDernier);

		Boolean fraisInscription = (Boolean)solrDocument.get("fraisInscription_stored_boolean");
		if(fraisInscription != null)
			oPaiementScolaire.setFraisInscription(fraisInscription);

		Boolean fraisMois = (Boolean)solrDocument.get("fraisMois_stored_boolean");
		if(fraisMois != null)
			oPaiementScolaire.setFraisMois(fraisMois);

		Boolean fraisRetard = (Boolean)solrDocument.get("fraisRetard_stored_boolean");
		if(fraisRetard != null)
			oPaiementScolaire.setFraisRetard(fraisRetard);

		Double fraisMontantDu = (Double)solrDocument.get("fraisMontantDu_stored_double");
		if(fraisMontantDu != null)
			oPaiementScolaire.setFraisMontantDu(fraisMontantDu);

		Double fraisMontantFuture = (Double)solrDocument.get("fraisMontantFuture_stored_double");
		if(fraisMontantFuture != null)
			oPaiementScolaire.setFraisMontantFuture(fraisMontantFuture);

		String paiementNomCourt = (String)solrDocument.get("paiementNomCourt_stored_string");
		if(paiementNomCourt != null)
			oPaiementScolaire.setPaiementNomCourt(paiementNomCourt);

		String paiementNomComplet = (String)solrDocument.get("paiementNomComplet_stored_string");
		if(paiementNomComplet != null)
			oPaiementScolaire.setPaiementNomComplet(paiementNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiPaiementScolaire() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof PaiementScolaire) {
			PaiementScolaire original = (PaiementScolaire)o;
			if(!Objects.equals(inscriptionCle, original.getInscriptionCle()))
				requeteApi.addVars("inscriptionCle");
			if(!Objects.equals(enfantNomCompletPrefere, original.getEnfantNomCompletPrefere()))
				requeteApi.addVars("enfantNomCompletPrefere");
			if(!Objects.equals(enfantDateNaissance, original.getEnfantDateNaissance()))
				requeteApi.addVars("enfantDateNaissance");
			if(!Objects.equals(mereNomCompletPrefere, original.getMereNomCompletPrefere()))
				requeteApi.addVars("mereNomCompletPrefere");
			if(!Objects.equals(pereNomCompletPrefere, original.getPereNomCompletPrefere()))
				requeteApi.addVars("pereNomCompletPrefere");
			if(!Objects.equals(inscriptionPaimentChaqueMois, original.getInscriptionPaimentChaqueMois()))
				requeteApi.addVars("inscriptionPaimentChaqueMois");
			if(!Objects.equals(inscriptionPaimentComplet, original.getInscriptionPaimentComplet()))
				requeteApi.addVars("inscriptionPaimentComplet");
			if(!Objects.equals(paiementDescription, original.getPaiementDescription()))
				requeteApi.addVars("paiementDescription");
			if(!Objects.equals(paiementDate, original.getPaiementDate()))
				requeteApi.addVars("paiementDate");
			if(!Objects.equals(paiementMontant, original.getPaiementMontant()))
				requeteApi.addVars("paiementMontant");
			if(!Objects.equals(paiementEspeces, original.getPaiementEspeces()))
				requeteApi.addVars("paiementEspeces");
			if(!Objects.equals(paiementCheque, original.getPaiementCheque()))
				requeteApi.addVars("paiementCheque");
			if(!Objects.equals(paiementSysteme, original.getPaiementSysteme()))
				requeteApi.addVars("paiementSysteme");
			if(!Objects.equals(paiementPar, original.getPaiementPar()))
				requeteApi.addVars("paiementPar");
			if(!Objects.equals(transactionId, original.getTransactionId()))
				requeteApi.addVars("transactionId");
			if(!Objects.equals(customerProfileId, original.getCustomerProfileId()))
				requeteApi.addVars("customerProfileId");
			if(!Objects.equals(transactionStatus, original.getTransactionStatus()))
				requeteApi.addVars("transactionStatus");
			if(!Objects.equals(paiementRecu, original.getPaiementRecu()))
				requeteApi.addVars("paiementRecu");
			if(!Objects.equals(fraisMontant, original.getFraisMontant()))
				requeteApi.addVars("fraisMontant");
			if(!Objects.equals(fraisPremierDernier, original.getFraisPremierDernier()))
				requeteApi.addVars("fraisPremierDernier");
			if(!Objects.equals(fraisInscription, original.getFraisInscription()))
				requeteApi.addVars("fraisInscription");
			if(!Objects.equals(fraisMois, original.getFraisMois()))
				requeteApi.addVars("fraisMois");
			if(!Objects.equals(fraisRetard, original.getFraisRetard()))
				requeteApi.addVars("fraisRetard");
			if(!Objects.equals(fraisMontantDu, original.getFraisMontantDu()))
				requeteApi.addVars("fraisMontantDu");
			if(!Objects.equals(fraisMontantFuture, original.getFraisMontantFuture()))
				requeteApi.addVars("fraisMontantFuture");
			if(!Objects.equals(paiementNomCourt, original.getPaiementNomCourt()))
				requeteApi.addVars("paiementNomCourt");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), inscriptionCle, enfantNomCompletPrefere, enfantDateNaissance, mereNomCompletPrefere, pereNomCompletPrefere, inscriptionPaimentChaqueMois, inscriptionPaimentComplet, paiementDescription, paiementDate, paiementMontant, paiementEspeces, paiementCheque, paiementSysteme, paiementPar, transactionId, customerProfileId, transactionStatus, paiementRecu, fraisMontant, fraisPremierDernier, fraisInscription, fraisMois, fraisRetard, fraisMontantDu, fraisMontantFuture, paiementNomCourt);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof PaiementScolaire))
			return false;
		PaiementScolaire that = (PaiementScolaire)o;
		return super.equals(o)
				&& Objects.equals( inscriptionCle, that.inscriptionCle )
				&& Objects.equals( enfantNomCompletPrefere, that.enfantNomCompletPrefere )
				&& Objects.equals( enfantDateNaissance, that.enfantDateNaissance )
				&& Objects.equals( mereNomCompletPrefere, that.mereNomCompletPrefere )
				&& Objects.equals( pereNomCompletPrefere, that.pereNomCompletPrefere )
				&& Objects.equals( inscriptionPaimentChaqueMois, that.inscriptionPaimentChaqueMois )
				&& Objects.equals( inscriptionPaimentComplet, that.inscriptionPaimentComplet )
				&& Objects.equals( paiementDescription, that.paiementDescription )
				&& Objects.equals( paiementDate, that.paiementDate )
				&& Objects.equals( paiementMontant, that.paiementMontant )
				&& Objects.equals( paiementEspeces, that.paiementEspeces )
				&& Objects.equals( paiementCheque, that.paiementCheque )
				&& Objects.equals( paiementSysteme, that.paiementSysteme )
				&& Objects.equals( paiementPar, that.paiementPar )
				&& Objects.equals( transactionId, that.transactionId )
				&& Objects.equals( customerProfileId, that.customerProfileId )
				&& Objects.equals( transactionStatus, that.transactionStatus )
				&& Objects.equals( paiementRecu, that.paiementRecu )
				&& Objects.equals( fraisMontant, that.fraisMontant )
				&& Objects.equals( fraisPremierDernier, that.fraisPremierDernier )
				&& Objects.equals( fraisInscription, that.fraisInscription )
				&& Objects.equals( fraisMois, that.fraisMois )
				&& Objects.equals( fraisRetard, that.fraisRetard )
				&& Objects.equals( fraisMontantDu, that.fraisMontantDu )
				&& Objects.equals( fraisMontantFuture, that.fraisMontantFuture )
				&& Objects.equals( paiementNomCourt, that.paiementNomCourt );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PaiementScolaire { ");
		sb.append( "inscriptionCle: " ).append(inscriptionCle);
		sb.append( ", enfantNomCompletPrefere: \"" ).append(enfantNomCompletPrefere).append( "\"" );
		sb.append( ", enfantDateNaissance: " ).append(enfantDateNaissance);
		sb.append( ", mereNomCompletPrefere: \"" ).append(mereNomCompletPrefere).append( "\"" );
		sb.append( ", pereNomCompletPrefere: \"" ).append(pereNomCompletPrefere).append( "\"" );
		sb.append( ", inscriptionPaimentChaqueMois: " ).append(inscriptionPaimentChaqueMois);
		sb.append( ", inscriptionPaimentComplet: " ).append(inscriptionPaimentComplet);
		sb.append( ", paiementDescription: \"" ).append(paiementDescription).append( "\"" );
		sb.append( ", paiementDate: " ).append(paiementDate);
		sb.append( ", paiementMontant: " ).append(paiementMontant);
		sb.append( ", paiementEspeces: " ).append(paiementEspeces);
		sb.append( ", paiementCheque: " ).append(paiementCheque);
		sb.append( ", paiementSysteme: " ).append(paiementSysteme);
		sb.append( ", paiementPar: \"" ).append(paiementPar).append( "\"" );
		sb.append( ", transactionId: \"" ).append(transactionId).append( "\"" );
		sb.append( ", customerProfileId: \"" ).append(customerProfileId).append( "\"" );
		sb.append( ", transactionStatus: \"" ).append(transactionStatus).append( "\"" );
		sb.append( ", paiementRecu: " ).append(paiementRecu);
		sb.append( ", fraisMontant: " ).append(fraisMontant);
		sb.append( ", fraisPremierDernier: " ).append(fraisPremierDernier);
		sb.append( ", fraisInscription: " ).append(fraisInscription);
		sb.append( ", fraisMois: " ).append(fraisMois);
		sb.append( ", fraisRetard: " ).append(fraisRetard);
		sb.append( ", fraisMontantDu: " ).append(fraisMontantDu);
		sb.append( ", fraisMontantFuture: " ).append(fraisMontantFuture);
		sb.append( ", paiementNomCourt: \"" ).append(paiementNomCourt).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
