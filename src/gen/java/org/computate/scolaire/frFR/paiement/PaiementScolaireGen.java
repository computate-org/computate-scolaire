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
import org.computate.scolaire.frFR.paiement.PaiementScolaire;
import java.math.RoundingMode;
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
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe paymentNumber dans Solr. </a>
 * <br/>
 **/
public abstract class PaiementScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PaiementScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("SiteManager");

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
	public static final String PaiementScolaire_Titre = "paiements";
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

	/**	 L'entité paiementCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long paiementCle;
	@JsonIgnore
	public Couverture<Long> paiementCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("paiementCle").o(paiementCle);

	/**	<br/> L'entité paiementCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementCle">Trouver l'entité paiementCle dans Solr</a>
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
	public void setPaiementCle(String o) {
		this.paiementCle = PaiementScolaire.staticSetPaiementCle(requeteSite_, o);
		this.paiementCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetPaiementCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrPaiementCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrPaiementCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementCle(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementCle(requeteSite_, PaiementScolaire.staticSolrPaiementCle(requeteSite_, PaiementScolaire.staticSetPaiementCle(requeteSite_, o)));
	}

	public Long solrPaiementCle() {
		return PaiementScolaire.staticSolrPaiementCle(requeteSite_, paiementCle);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCle">Trouver l'entité inscriptionCle dans Solr</a>
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
		this.inscriptionCle = PaiementScolaire.staticSetInscriptionCle(requeteSite_, o);
		this.inscriptionCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetInscriptionCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrInscriptionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrInscriptionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionCle(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrInscriptionCle(requeteSite_, PaiementScolaire.staticSolrInscriptionCle(requeteSite_, PaiementScolaire.staticSetInscriptionCle(requeteSite_, o)));
	}

	public Long solrInscriptionCle() {
		return PaiementScolaire.staticSolrInscriptionCle(requeteSite_, inscriptionCle);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_inscriptionCle_vider")
						.a("class", "inscriptionCle_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_inscriptionCle_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "inscription")
				.a("title", "La clé primaire des enfants dans la base de données. ")
				.a("class", "valeur suggereInscriptionCle w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setInscriptionCle")
				.a("id", classeApiMethodeMethode, "_inscriptionCle")
				.a("autocomplete", "off");
				a("oninput", "suggerePaiementScolaireInscriptionCle($(this).val() ? rechercherInscriptionScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'paiementCles:" + pk + "'}", "], $('#listPaiementScolaireInscriptionCle_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "InscriptionCle ").f().sx(htmInscriptionCle()).g("span");
		}
	}

	public void htmInscriptionCle(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireInscriptionCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/inscription?fq=paiementCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
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
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
												.a("id", classeApiMethodeMethode, "_inscriptionCle_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postInscriptionScolaireVals({ paiementCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "inscriptionCle')); });")
												.f().sx("ajouter une inscription")
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

	//////////////////////////
	// inscriptionRecherche //
	//////////////////////////

	/**	 L'entité inscriptionRecherche
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<InscriptionScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<InscriptionScolaire> inscriptionRecherche = new ListeRecherche<InscriptionScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<InscriptionScolaire>> inscriptionRechercheCouverture = new Couverture<ListeRecherche<InscriptionScolaire>>().p(this).c(ListeRecherche.class).var("inscriptionRecherche").o(inscriptionRecherche);

	/**	<br/> L'entité inscriptionRecherche
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<InscriptionScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionRecherche">Trouver l'entité inscriptionRecherche dans Solr</a>
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
	public static ListeRecherche<InscriptionScolaire> staticSetInscriptionRecherche(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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

	/**	 L'entité inscription_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected InscriptionScolaire inscription_;
	@JsonIgnore
	public Couverture<InscriptionScolaire> inscription_Couverture = new Couverture<InscriptionScolaire>().p(this).c(InscriptionScolaire.class).var("inscription_").o(inscription_);

	/**	<br/> L'entité inscription_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscription_">Trouver l'entité inscription_ dans Solr</a>
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
	public static InscriptionScolaire staticSetInscription_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumero">Trouver l'entité ecoleNumero dans Solr</a>
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
		this.ecoleNumero = PaiementScolaire.staticSetEcoleNumero(requeteSite_, o);
		this.ecoleNumeroCouverture.dejaInitialise = true;
	}
	public static Integer staticSetEcoleNumero(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEcoleNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrEcoleNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNumero(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrEcoleNumero(requeteSite_, PaiementScolaire.staticSolrEcoleNumero(requeteSite_, PaiementScolaire.staticSetEcoleNumero(requeteSite_, o)));
	}

	public Integer solrEcoleNumero() {
		return PaiementScolaire.staticSolrEcoleNumero(requeteSite_, ecoleNumero);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurCles">Trouver l'entité utilisateurCles dans Solr</a>
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
		Long l = PaiementScolaire.staticSetUtilisateurCles(requeteSite_, o);
		if(l != null)
			addUtilisateurCles(l);
		this.utilisateurClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetUtilisateurCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setUtilisateurCles(JsonArray objets) {
		utilisateurCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUtilisateurCles(o);
		}
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

	public static Long staticSolrUtilisateurCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrUtilisateurCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUtilisateurCles(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrUtilisateurCles(requeteSite_, PaiementScolaire.staticSolrUtilisateurCles(requeteSite_, PaiementScolaire.staticSetUtilisateurCles(requeteSite_, o)));
	}

	public List<Long> solrUtilisateurCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : utilisateurCles) {
			l.add(PaiementScolaire.staticSolrUtilisateurCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
		this.ecoleCle = PaiementScolaire.staticSetEcoleCle(requeteSite_, o);
		this.ecoleCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetEcoleCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrEcoleCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrEcoleCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleCle(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrEcoleCle(requeteSite_, PaiementScolaire.staticSolrEcoleCle(requeteSite_, PaiementScolaire.staticSetEcoleCle(requeteSite_, o)));
	}

	public Long solrEcoleCle() {
		return PaiementScolaire.staticSolrEcoleCle(requeteSite_, ecoleCle);
	}

	public String strEcoleCle() {
		return ecoleCle == null ? "" : ecoleCle.toString();
	}

	public String jsonEcoleCle() {
		return ecoleCle == null ? "" : ecoleCle.toString();
	}

	public String nomAffichageEcoleCle() {
		return null;
	}

	public String htmTooltipEcoleCle() {
		return null;
	}

	public String htmEcoleCle() {
		return ecoleCle == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleCle());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAddresse(Couverture<String> c);

	public String getEcoleAddresse() {
		return ecoleAddresse;
	}
	public void setEcoleAddresse(String o) {
		this.ecoleAddresse = PaiementScolaire.staticSetEcoleAddresse(requeteSite_, o);
		this.ecoleAddresseCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected PaiementScolaire ecoleAddresseInit() {
		if(!ecoleAddresseCouverture.dejaInitialise) {
			_ecoleAddresse(ecoleAddresseCouverture);
			if(ecoleAddresse == null)
				setEcoleAddresse(ecoleAddresseCouverture.o);
		}
		ecoleAddresseCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static String staticSolrEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrEcoleAddresse(requeteSite_, PaiementScolaire.staticSolrEcoleAddresse(requeteSite_, PaiementScolaire.staticSetEcoleAddresse(requeteSite_, o)));
	}

	public String solrEcoleAddresse() {
		return PaiementScolaire.staticSolrEcoleAddresse(requeteSite_, ecoleAddresse);
	}

	public String strEcoleAddresse() {
		return ecoleAddresse == null ? "" : ecoleAddresse;
	}

	public String jsonEcoleAddresse() {
		return ecoleAddresse == null ? "" : ecoleAddresse;
	}

	public String nomAffichageEcoleAddresse() {
		return null;
	}

	public String htmTooltipEcoleAddresse() {
		return null;
	}

	public String htmEcoleAddresse() {
		return ecoleAddresse == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleAddresse());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNumeroTelephone(Couverture<String> c);

	public String getEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}
	public void setEcoleNumeroTelephone(String o) {
		this.ecoleNumeroTelephone = PaiementScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o);
		this.ecoleNumeroTelephoneCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected PaiementScolaire ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneCouverture.dejaInitialise) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneCouverture);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneCouverture.o);
		}
		ecoleNumeroTelephoneCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static String staticSolrEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrEcoleNumeroTelephone(requeteSite_, PaiementScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, PaiementScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o)));
	}

	public String solrEcoleNumeroTelephone() {
		return PaiementScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, ecoleNumeroTelephone);
	}

	public String strEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : ecoleNumeroTelephone;
	}

	public String jsonEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : ecoleNumeroTelephone;
	}

	public String nomAffichageEcoleNumeroTelephone() {
		return null;
	}

	public String htmTooltipEcoleNumeroTelephone() {
		return null;
	}

	public String htmEcoleNumeroTelephone() {
		return ecoleNumeroTelephone == null ? "" : StringEscapeUtils.escapeHtml4(strEcoleNumeroTelephone());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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
		this.anneeCle = PaiementScolaire.staticSetAnneeCle(requeteSite_, o);
		this.anneeCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetAnneeCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrAnneeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAnneeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeCle(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrAnneeCle(requeteSite_, PaiementScolaire.staticSolrAnneeCle(requeteSite_, PaiementScolaire.staticSetAnneeCle(requeteSite_, o)));
	}

	public Long solrAnneeCle() {
		return PaiementScolaire.staticSolrAnneeCle(requeteSite_, anneeCle);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCle">Trouver l'entité sessionCle dans Solr</a>
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
		this.sessionCle = PaiementScolaire.staticSetSessionCle(requeteSite_, o);
		this.sessionCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetSessionCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrSessionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionCle(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrSessionCle(requeteSite_, PaiementScolaire.staticSolrSessionCle(requeteSite_, PaiementScolaire.staticSetSessionCle(requeteSite_, o)));
	}

	public Long solrSessionCle() {
		return PaiementScolaire.staticSolrSessionCle(requeteSite_, sessionCle);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCle">Trouver l'entité ageCle dans Solr</a>
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
		this.ageCle = PaiementScolaire.staticSetAgeCle(requeteSite_, o);
		this.ageCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetAgeCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrAgeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeCle(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrAgeCle(requeteSite_, PaiementScolaire.staticSolrAgeCle(requeteSite_, PaiementScolaire.staticSetAgeCle(requeteSite_, o)));
	}

	public Long solrAgeCle() {
		return PaiementScolaire.staticSolrAgeCle(requeteSite_, ageCle);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocCle">Trouver l'entité blocCle dans Solr</a>
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
		this.blocCle = PaiementScolaire.staticSetBlocCle(requeteSite_, o);
		this.blocCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetBlocCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrBlocCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrBlocCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocCle(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrBlocCle(requeteSite_, PaiementScolaire.staticSolrBlocCle(requeteSite_, PaiementScolaire.staticSetBlocCle(requeteSite_, o)));
	}

	public Long solrBlocCle() {
		return PaiementScolaire.staticSolrBlocCle(requeteSite_, blocCle);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantCle">Trouver l'entité enfantCle dans Solr</a>
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
		this.enfantCle = PaiementScolaire.staticSetEnfantCle(requeteSite_, o);
		this.enfantCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetEnfantCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrEnfantCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrEnfantCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantCle(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrEnfantCle(requeteSite_, PaiementScolaire.staticSolrEnfantCle(requeteSite_, PaiementScolaire.staticSetEnfantCle(requeteSite_, o)));
	}

	public Long solrEnfantCle() {
		return PaiementScolaire.staticSolrEnfantCle(requeteSite_, enfantCle);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereCles">Trouver l'entité mereCles dans Solr</a>
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
	public void setMereCles(String o) {
		Long l = PaiementScolaire.staticSetMereCles(requeteSite_, o);
		if(l != null)
			addMereCles(l);
		this.mereClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetMereCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setMereCles(JsonArray objets) {
		mereCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addMereCles(o);
		}
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

	public static Long staticSolrMereCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrMereCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMereCles(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrMereCles(requeteSite_, PaiementScolaire.staticSolrMereCles(requeteSite_, PaiementScolaire.staticSetMereCles(requeteSite_, o)));
	}

	public List<Long> solrMereCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : mereCles) {
			l.add(PaiementScolaire.staticSolrMereCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereCles">Trouver l'entité pereCles dans Solr</a>
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
	public void setPereCles(String o) {
		Long l = PaiementScolaire.staticSetPereCles(requeteSite_, o);
		if(l != null)
			addPereCles(l);
		this.pereClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetPereCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setPereCles(JsonArray objets) {
		pereCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPereCles(o);
		}
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

	public static Long staticSolrPereCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrPereCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPereCles(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPereCles(requeteSite_, PaiementScolaire.staticSolrPereCles(requeteSite_, PaiementScolaire.staticSetPereCles(requeteSite_, o)));
	}

	public List<Long> solrPereCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : pereCles) {
			l.add(PaiementScolaire.staticSolrPereCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienCles">Trouver l'entité gardienCles dans Solr</a>
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
	public void setGardienCles(String o) {
		Long l = PaiementScolaire.staticSetGardienCles(requeteSite_, o);
		if(l != null)
			addGardienCles(l);
		this.gardienClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetGardienCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setGardienCles(JsonArray objets) {
		gardienCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addGardienCles(o);
		}
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

	public static Long staticSolrGardienCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrGardienCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqGardienCles(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrGardienCles(requeteSite_, PaiementScolaire.staticSolrGardienCles(requeteSite_, PaiementScolaire.staticSetGardienCles(requeteSite_, o)));
	}

	public List<Long> solrGardienCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : gardienCles) {
			l.add(PaiementScolaire.staticSolrGardienCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantNomCompletPrefere">Trouver l'entité enfantNomCompletPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantNomCompletPrefere(Couverture<String> c);

	public String getEnfantNomCompletPrefere() {
		return enfantNomCompletPrefere;
	}
	public void setEnfantNomCompletPrefere(String o) {
		this.enfantNomCompletPrefere = PaiementScolaire.staticSetEnfantNomCompletPrefere(requeteSite_, o);
		this.enfantNomCompletPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrEnfantNomCompletPrefere(requeteSite_, PaiementScolaire.staticSolrEnfantNomCompletPrefere(requeteSite_, PaiementScolaire.staticSetEnfantNomCompletPrefere(requeteSite_, o)));
	}

	public String solrEnfantNomCompletPrefere() {
		return PaiementScolaire.staticSolrEnfantNomCompletPrefere(requeteSite_, enfantNomCompletPrefere);
	}

	public String strEnfantNomCompletPrefere() {
		return enfantNomCompletPrefere == null ? "" : enfantNomCompletPrefere;
	}

	public String jsonEnfantNomCompletPrefere() {
		return enfantNomCompletPrefere == null ? "" : enfantNomCompletPrefere;
	}

	public String nomAffichageEnfantNomCompletPrefere() {
		return null;
	}

	public String htmTooltipEnfantNomCompletPrefere() {
		return null;
	}

	public String htmEnfantNomCompletPrefere() {
		return enfantNomCompletPrefere == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantNomCompletPrefere());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantDateNaissance">Trouver l'entité enfantDateNaissance dans Solr</a>
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
		this.enfantDateNaissance = PaiementScolaire.staticSetEnfantDateNaissance(requeteSite_, o);
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetEnfantDateNaissance(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setEnfantDateNaissance(Date o) {
		this.enfantDateNaissance = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.enfantDateNaissanceCouverture.dejaInitialise = true;
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

	public static Date staticSolrEnfantDateNaissance(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrEnfantDateNaissance(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqEnfantDateNaissance(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrEnfantDateNaissance(requeteSite_, PaiementScolaire.staticSolrEnfantDateNaissance(requeteSite_, PaiementScolaire.staticSetEnfantDateNaissance(requeteSite_, o)));
	}

	public Date solrEnfantDateNaissance() {
		return PaiementScolaire.staticSolrEnfantDateNaissance(requeteSite_, enfantDateNaissance);
	}

	public String strEnfantDateNaissance() {
		return enfantDateNaissance == null ? "" : enfantDateNaissance.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonEnfantDateNaissance() {
		return enfantDateNaissance == null ? "" : enfantDateNaissance.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageEnfantDateNaissance() {
		return null;
	}

	public String htmTooltipEnfantDateNaissance() {
		return null;
	}

	public String htmEnfantDateNaissance() {
		return enfantDateNaissance == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantDateNaissance());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereNomCompletPrefere">Trouver l'entité mereNomCompletPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mereNomCompletPrefere(Couverture<String> c);

	public String getMereNomCompletPrefere() {
		return mereNomCompletPrefere;
	}
	public void setMereNomCompletPrefere(String o) {
		this.mereNomCompletPrefere = PaiementScolaire.staticSetMereNomCompletPrefere(requeteSite_, o);
		this.mereNomCompletPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetMereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrMereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrMereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrMereNomCompletPrefere(requeteSite_, PaiementScolaire.staticSolrMereNomCompletPrefere(requeteSite_, PaiementScolaire.staticSetMereNomCompletPrefere(requeteSite_, o)));
	}

	public String solrMereNomCompletPrefere() {
		return PaiementScolaire.staticSolrMereNomCompletPrefere(requeteSite_, mereNomCompletPrefere);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pereNomCompletPrefere">Trouver l'entité pereNomCompletPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pereNomCompletPrefere(Couverture<String> c);

	public String getPereNomCompletPrefere() {
		return pereNomCompletPrefere;
	}
	public void setPereNomCompletPrefere(String o) {
		this.pereNomCompletPrefere = PaiementScolaire.staticSetPereNomCompletPrefere(requeteSite_, o);
		this.pereNomCompletPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetPereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPereNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPereNomCompletPrefere(requeteSite_, PaiementScolaire.staticSolrPereNomCompletPrefere(requeteSite_, PaiementScolaire.staticSetPereNomCompletPrefere(requeteSite_, o)));
	}

	public String solrPereNomCompletPrefere() {
		return PaiementScolaire.staticSolrPereNomCompletPrefere(requeteSite_, pereNomCompletPrefere);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNom(Couverture<String> c);

	public String getEcoleNom() {
		return ecoleNom;
	}
	public void setEcoleNom(String o) {
		this.ecoleNom = PaiementScolaire.staticSetEcoleNom(requeteSite_, o);
		this.ecoleNomCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrEcoleNom(requeteSite_, PaiementScolaire.staticSolrEcoleNom(requeteSite_, PaiementScolaire.staticSetEcoleNom(requeteSite_, o)));
	}

	public String solrEcoleNom() {
		return PaiementScolaire.staticSolrEcoleNom(requeteSite_, ecoleNom);
	}

	public String strEcoleNom() {
		return ecoleNom == null ? "" : ecoleNom;
	}

	public String jsonEcoleNom() {
		return ecoleNom == null ? "" : ecoleNom;
	}

	public String nomAffichageEcoleNom() {
		return null;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNomComplet(Couverture<String> c);

	public String getEcoleNomComplet() {
		return ecoleNomComplet;
	}
	public void setEcoleNomComplet(String o) {
		this.ecoleNomComplet = PaiementScolaire.staticSetEcoleNomComplet(requeteSite_, o);
		this.ecoleNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrEcoleNomComplet(requeteSite_, PaiementScolaire.staticSolrEcoleNomComplet(requeteSite_, PaiementScolaire.staticSetEcoleNomComplet(requeteSite_, o)));
	}

	public String solrEcoleNomComplet() {
		return PaiementScolaire.staticSolrEcoleNomComplet(requeteSite_, ecoleNomComplet);
	}

	public String strEcoleNomComplet() {
		return ecoleNomComplet == null ? "" : ecoleNomComplet;
	}

	public String jsonEcoleNomComplet() {
		return ecoleNomComplet == null ? "" : ecoleNomComplet;
	}

	public String nomAffichageEcoleNomComplet() {
		return null;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleEmplacement">Trouver l'entité ecoleEmplacement dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleEmplacement(Couverture<String> c);

	public String getEcoleEmplacement() {
		return ecoleEmplacement;
	}
	public void setEcoleEmplacement(String o) {
		this.ecoleEmplacement = PaiementScolaire.staticSetEcoleEmplacement(requeteSite_, o);
		this.ecoleEmplacementCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrEcoleEmplacement(requeteSite_, PaiementScolaire.staticSolrEcoleEmplacement(requeteSite_, PaiementScolaire.staticSetEcoleEmplacement(requeteSite_, o)));
	}

	public String solrEcoleEmplacement() {
		return PaiementScolaire.staticSolrEcoleEmplacement(requeteSite_, ecoleEmplacement);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
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
		this.anneeDebut = PaiementScolaire.staticSetAnneeDebut(requeteSite_, o);
		this.anneeDebutCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAnneeDebut(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAnneeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAnneeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeDebut(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrAnneeDebut(requeteSite_, PaiementScolaire.staticSolrAnneeDebut(requeteSite_, PaiementScolaire.staticSetAnneeDebut(requeteSite_, o)));
	}

	public Integer solrAnneeDebut() {
		return PaiementScolaire.staticSolrAnneeDebut(requeteSite_, anneeDebut);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
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
		this.anneeFin = PaiementScolaire.staticSetAnneeFin(requeteSite_, o);
		this.anneeFinCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAnneeFin(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAnneeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAnneeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeFin(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrAnneeFin(requeteSite_, PaiementScolaire.staticSolrAnneeFin(requeteSite_, PaiementScolaire.staticSetAnneeFin(requeteSite_, o)));
	}

	public Integer solrAnneeFin() {
		return PaiementScolaire.staticSolrAnneeFin(requeteSite_, anneeFin);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonDateDebut">Trouver l'entité saisonDateDebut dans Solr</a>
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
		this.saisonDateDebut = PaiementScolaire.staticSetSaisonDateDebut(requeteSite_, o);
		this.saisonDateDebutCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetSaisonDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSaisonDateDebut(Date o) {
		this.saisonDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.saisonDateDebutCouverture.dejaInitialise = true;
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

	public static Date staticSolrSaisonDateDebut(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSaisonDateDebut(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSaisonDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrSaisonDateDebut(requeteSite_, PaiementScolaire.staticSolrSaisonDateDebut(requeteSite_, PaiementScolaire.staticSetSaisonDateDebut(requeteSite_, o)));
	}

	public Date solrSaisonDateDebut() {
		return PaiementScolaire.staticSolrSaisonDateDebut(requeteSite_, saisonDateDebut);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFraisInscription">Trouver l'entité anneeFraisInscription dans Solr</a>
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
		this.anneeFraisInscription = PaiementScolaire.staticSetAnneeFraisInscription(requeteSite_, o);
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
	protected PaiementScolaire anneeFraisInscriptionInit() {
		if(!anneeFraisInscriptionCouverture.dejaInitialise) {
			_anneeFraisInscription(anneeFraisInscriptionCouverture);
			if(anneeFraisInscription == null)
				setAnneeFraisInscription(anneeFraisInscriptionCouverture.o);
		}
		anneeFraisInscriptionCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Double staticSolrAnneeFraisInscription(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrAnneeFraisInscription(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeFraisInscription(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrAnneeFraisInscription(requeteSite_, PaiementScolaire.staticSolrAnneeFraisInscription(requeteSite_, PaiementScolaire.staticSetAnneeFraisInscription(requeteSite_, o)));
	}

	public Double solrAnneeFraisInscription() {
		return PaiementScolaire.staticSolrAnneeFraisInscription(requeteSite_, anneeFraisInscription);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionDateDebut">Trouver l'entité sessionDateDebut dans Solr</a>
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
		this.sessionDateDebut = PaiementScolaire.staticSetSessionDateDebut(requeteSite_, o);
		this.sessionDateDebutCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetSessionDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionDateDebut(Date o) {
		this.sessionDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateDebutCouverture.dejaInitialise = true;
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

	public static Date staticSolrSessionDateDebut(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionDateDebut(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrSessionDateDebut(requeteSite_, PaiementScolaire.staticSolrSessionDateDebut(requeteSite_, PaiementScolaire.staticSetSessionDateDebut(requeteSite_, o)));
	}

	public Date solrSessionDateDebut() {
		return PaiementScolaire.staticSolrSessionDateDebut(requeteSite_, sessionDateDebut);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionDateFin">Trouver l'entité sessionDateFin dans Solr</a>
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
		this.sessionDateFin = PaiementScolaire.staticSetSessionDateFin(requeteSite_, o);
		this.sessionDateFinCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetSessionDateFin(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSessionDateFin(Date o) {
		this.sessionDateFin = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateFinCouverture.dejaInitialise = true;
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

	public static Date staticSolrSessionDateFin(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSessionDateFin(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSessionDateFin(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrSessionDateFin(requeteSite_, PaiementScolaire.staticSolrSessionDateFin(requeteSite_, PaiementScolaire.staticSetSessionDateFin(requeteSite_, o)));
	}

	public Date solrSessionDateFin() {
		return PaiementScolaire.staticSolrSessionDateFin(requeteSite_, sessionDateFin);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageDebut">Trouver l'entité ageDebut dans Solr</a>
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
		this.ageDebut = PaiementScolaire.staticSetAgeDebut(requeteSite_, o);
		this.ageDebutCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAgeDebut(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAgeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeDebut(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrAgeDebut(requeteSite_, PaiementScolaire.staticSolrAgeDebut(requeteSite_, PaiementScolaire.staticSetAgeDebut(requeteSite_, o)));
	}

	public Integer solrAgeDebut() {
		return PaiementScolaire.staticSolrAgeDebut(requeteSite_, ageDebut);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageFin">Trouver l'entité ageFin dans Solr</a>
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
		this.ageFin = PaiementScolaire.staticSetAgeFin(requeteSite_, o);
		this.ageFinCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAgeFin(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrAgeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAgeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeFin(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrAgeFin(requeteSite_, PaiementScolaire.staticSolrAgeFin(requeteSite_, PaiementScolaire.staticSetAgeFin(requeteSite_, o)));
	}

	public Integer solrAgeFin() {
		return PaiementScolaire.staticSolrAgeFin(requeteSite_, ageFin);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocNomComplet">Trouver l'entité blocNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _blocNomComplet(Couverture<String> c);

	public String getBlocNomComplet() {
		return blocNomComplet;
	}
	public void setBlocNomComplet(String o) {
		this.blocNomComplet = PaiementScolaire.staticSetBlocNomComplet(requeteSite_, o);
		this.blocNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetBlocNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected PaiementScolaire blocNomCompletInit() {
		if(!blocNomCompletCouverture.dejaInitialise) {
			_blocNomComplet(blocNomCompletCouverture);
			if(blocNomComplet == null)
				setBlocNomComplet(blocNomCompletCouverture.o);
		}
		blocNomCompletCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static String staticSolrBlocNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrBlocNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrBlocNomComplet(requeteSite_, PaiementScolaire.staticSolrBlocNomComplet(requeteSite_, PaiementScolaire.staticSetBlocNomComplet(requeteSite_, o)));
	}

	public String solrBlocNomComplet() {
		return PaiementScolaire.staticSolrBlocNomComplet(requeteSite_, blocNomComplet);
	}

	public String strBlocNomComplet() {
		return blocNomComplet == null ? "" : blocNomComplet;
	}

	public String jsonBlocNomComplet() {
		return blocNomComplet == null ? "" : blocNomComplet;
	}

	public String nomAffichageBlocNomComplet() {
		return "bloc nom complet";
	}

	public String htmTooltipBlocNomComplet() {
		return null;
	}

	public String htmBlocNomComplet() {
		return blocNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strBlocNomComplet());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocHeureDebut">Trouver l'entité blocHeureDebut dans Solr</a>
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
		this.blocHeureDebut = PaiementScolaire.staticSetBlocHeureDebut(requeteSite_, o);
		this.blocHeureDebutCouverture.dejaInitialise = true;
	}
	public static LocalTime staticSetBlocHeureDebut(RequeteSiteFrFR requeteSite_, String o) {
		try {
			return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
		} catch(Exception e) {
		}
		return null;
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

	public static String staticSolrBlocHeureDebut(RequeteSiteFrFR requeteSite_, LocalTime o) {
		return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static String staticSolrStrBlocHeureDebut(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocHeureDebut(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrBlocHeureDebut(requeteSite_, PaiementScolaire.staticSolrBlocHeureDebut(requeteSite_, PaiementScolaire.staticSetBlocHeureDebut(requeteSite_, o)));
	}

	public String solrBlocHeureDebut() {
		return PaiementScolaire.staticSolrBlocHeureDebut(requeteSite_, blocHeureDebut);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocHeureFin">Trouver l'entité blocHeureFin dans Solr</a>
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
		this.blocHeureFin = PaiementScolaire.staticSetBlocHeureFin(requeteSite_, o);
		this.blocHeureFinCouverture.dejaInitialise = true;
	}
	public static LocalTime staticSetBlocHeureFin(RequeteSiteFrFR requeteSite_, String o) {
		try {
			return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);
		} catch(Exception e) {
		}
		return null;
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

	public static String staticSolrBlocHeureFin(RequeteSiteFrFR requeteSite_, LocalTime o) {
		return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static String staticSolrStrBlocHeureFin(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocHeureFin(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrBlocHeureFin(requeteSite_, PaiementScolaire.staticSolrBlocHeureFin(requeteSite_, PaiementScolaire.staticSetBlocHeureFin(requeteSite_, o)));
	}

	public String solrBlocHeureFin() {
		return PaiementScolaire.staticSolrBlocHeureFin(requeteSite_, blocHeureFin);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocPrixParMois">Trouver l'entité blocPrixParMois dans Solr</a>
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
		this.blocPrixParMois = PaiementScolaire.staticSetBlocPrixParMois(requeteSite_, o);
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
	protected PaiementScolaire blocPrixParMoisInit() {
		if(!blocPrixParMoisCouverture.dejaInitialise) {
			_blocPrixParMois(blocPrixParMoisCouverture);
			if(blocPrixParMois == null)
				setBlocPrixParMois(blocPrixParMoisCouverture.o);
		}
		blocPrixParMoisCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Double staticSolrBlocPrixParMois(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrBlocPrixParMois(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocPrixParMois(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrBlocPrixParMois(requeteSite_, PaiementScolaire.staticSolrBlocPrixParMois(requeteSite_, PaiementScolaire.staticSetBlocPrixParMois(requeteSite_, o)));
	}

	public Double solrBlocPrixParMois() {
		return PaiementScolaire.staticSolrBlocPrixParMois(requeteSite_, blocPrixParMois);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionNomGroupe">Trouver l'entité inscriptionNomGroupe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptionNomGroupe(Couverture<String> c);

	public String getInscriptionNomGroupe() {
		return inscriptionNomGroupe;
	}
	public void setInscriptionNomGroupe(String o) {
		this.inscriptionNomGroupe = PaiementScolaire.staticSetInscriptionNomGroupe(requeteSite_, o);
		this.inscriptionNomGroupeCouverture.dejaInitialise = true;
	}
	public static String staticSetInscriptionNomGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected PaiementScolaire inscriptionNomGroupeInit() {
		if(!inscriptionNomGroupeCouverture.dejaInitialise) {
			_inscriptionNomGroupe(inscriptionNomGroupeCouverture);
			if(inscriptionNomGroupe == null)
				setInscriptionNomGroupe(inscriptionNomGroupeCouverture.o);
		}
		inscriptionNomGroupeCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static String staticSolrInscriptionNomGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrInscriptionNomGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionNomGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrInscriptionNomGroupe(requeteSite_, PaiementScolaire.staticSolrInscriptionNomGroupe(requeteSite_, PaiementScolaire.staticSetInscriptionNomGroupe(requeteSite_, o)));
	}

	public String solrInscriptionNomGroupe() {
		return PaiementScolaire.staticSolrInscriptionNomGroupe(requeteSite_, inscriptionNomGroupe);
	}

	public String strInscriptionNomGroupe() {
		return inscriptionNomGroupe == null ? "" : inscriptionNomGroupe;
	}

	public String jsonInscriptionNomGroupe() {
		return inscriptionNomGroupe == null ? "" : inscriptionNomGroupe;
	}

	public String nomAffichageInscriptionNomGroupe() {
		return "nom de groupe";
	}

	public String htmTooltipInscriptionNomGroupe() {
		return null;
	}

	public String htmInscriptionNomGroupe() {
		return inscriptionNomGroupe == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionNomGroupe());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocPrixTotal">Trouver l'entité blocPrixTotal dans Solr</a>
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
		this.blocPrixTotal = PaiementScolaire.staticSetBlocPrixTotal(requeteSite_, o);
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
	protected PaiementScolaire blocPrixTotalInit() {
		if(!blocPrixTotalCouverture.dejaInitialise) {
			_blocPrixTotal(blocPrixTotalCouverture);
			if(blocPrixTotal == null)
				setBlocPrixTotal(blocPrixTotalCouverture.o);
		}
		blocPrixTotalCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Double staticSolrBlocPrixTotal(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrBlocPrixTotal(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqBlocPrixTotal(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrBlocPrixTotal(requeteSite_, PaiementScolaire.staticSolrBlocPrixTotal(requeteSite_, PaiementScolaire.staticSetBlocPrixTotal(requeteSite_, o)));
	}

	public Double solrBlocPrixTotal() {
		return PaiementScolaire.staticSolrBlocPrixTotal(requeteSite_, blocPrixTotal);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionPaimentChaqueMois">Trouver l'entité inscriptionPaimentChaqueMois dans Solr</a>
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
		this.inscriptionPaimentChaqueMois = PaiementScolaire.staticSetInscriptionPaimentChaqueMois(requeteSite_, o);
		this.inscriptionPaimentChaqueMoisCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetInscriptionPaimentChaqueMois(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrInscriptionPaimentChaqueMois(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrInscriptionPaimentChaqueMois(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionPaimentChaqueMois(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrInscriptionPaimentChaqueMois(requeteSite_, PaiementScolaire.staticSolrInscriptionPaimentChaqueMois(requeteSite_, PaiementScolaire.staticSetInscriptionPaimentChaqueMois(requeteSite_, o)));
	}

	public Boolean solrInscriptionPaimentChaqueMois() {
		return PaiementScolaire.staticSolrInscriptionPaimentChaqueMois(requeteSite_, inscriptionPaimentChaqueMois);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "InscriptionPaimentChaqueMois ").f().sx(htmInscriptionPaimentChaqueMois()).g("span");
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

	/////////////////////////
	// paiementDescription //
	/////////////////////////

	/**	 L'entité paiementDescription
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paiementDescription;
	@JsonIgnore
	public Couverture<String> paiementDescriptionCouverture = new Couverture<String>().p(this).c(String.class).var("paiementDescription").o(paiementDescription);

	/**	<br/> L'entité paiementDescription
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementDescription">Trouver l'entité paiementDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementDescription(Couverture<String> c);

	public String getPaiementDescription() {
		return paiementDescription;
	}
	public void setPaiementDescription(String o) {
		this.paiementDescription = PaiementScolaire.staticSetPaiementDescription(requeteSite_, o);
		this.paiementDescriptionCouverture.dejaInitialise = true;
	}
	public static String staticSetPaiementDescription(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPaiementDescription(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPaiementDescription(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementDescription(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementDescription(requeteSite_, PaiementScolaire.staticSolrPaiementDescription(requeteSite_, PaiementScolaire.staticSetPaiementDescription(requeteSite_, o)));
	}

	public String solrPaiementDescription() {
		return PaiementScolaire.staticSolrPaiementDescription(requeteSite_, paiementDescription);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "PaiementDescription ").f().sx(htmPaiementDescription()).g("span");
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
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
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

	/**	 L'entité paiementDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate paiementDate;
	@JsonIgnore
	public Couverture<LocalDate> paiementDateCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("paiementDate").o(paiementDate);

	/**	<br/> L'entité paiementDate
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementDate">Trouver l'entité paiementDate dans Solr</a>
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
	public void setPaiementDate(Instant o) {
		this.paiementDate = o == null ? null : LocalDate.from(o);
		this.paiementDateCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setPaiementDate(String o) {
		this.paiementDate = PaiementScolaire.staticSetPaiementDate(requeteSite_, o);
		this.paiementDateCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetPaiementDate(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setPaiementDate(Date o) {
		this.paiementDate = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.paiementDateCouverture.dejaInitialise = true;
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

	public static Date staticSolrPaiementDate(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrPaiementDate(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqPaiementDate(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementDate(requeteSite_, PaiementScolaire.staticSolrPaiementDate(requeteSite_, PaiementScolaire.staticSetPaiementDate(requeteSite_, o)));
	}

	public Date solrPaiementDate() {
		return PaiementScolaire.staticSolrPaiementDate(requeteSite_, paiementDate);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setPaiementDate classPaiementScolaire inputPaiementScolaire", pk, "PaiementDate w3-input w3-border ")
					.a("placeholder", "DD-MM-YYYY")
					.a("data-timeformat", "dd-MM-yyyy")
					.a("id", classeApiMethodeMethode, "_paiementDate")
					.a("title", "La clé primaire des enfants dans la base de données.  (DD-MM-YYYY)")
					.a("value", paiementDate == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(paiementDate));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementDate', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDate')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDate')); }); } ");
			}
			fg();
		} else {
				e("span").a("class", "varPaiementScolaire", pk, "PaiementDate ").f().sx(htmPaiementDate()).g("span");
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
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
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
	// fraisRetardDate //
	/////////////////////

	/**	 L'entité fraisRetardDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate fraisRetardDate;
	@JsonIgnore
	public Couverture<LocalDate> fraisRetardDateCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("fraisRetardDate").o(fraisRetardDate);

	/**	<br/> L'entité fraisRetardDate
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisRetardDate">Trouver l'entité fraisRetardDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisRetardDate(Couverture<LocalDate> c);

	public LocalDate getFraisRetardDate() {
		return fraisRetardDate;
	}

	public void setFraisRetardDate(LocalDate fraisRetardDate) {
		this.fraisRetardDate = fraisRetardDate;
		this.fraisRetardDateCouverture.dejaInitialise = true;
	}
	public void setFraisRetardDate(Instant o) {
		this.fraisRetardDate = o == null ? null : LocalDate.from(o);
		this.fraisRetardDateCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setFraisRetardDate(String o) {
		this.fraisRetardDate = PaiementScolaire.staticSetFraisRetardDate(requeteSite_, o);
		this.fraisRetardDateCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetFraisRetardDate(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setFraisRetardDate(Date o) {
		this.fraisRetardDate = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.fraisRetardDateCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire fraisRetardDateInit() {
		if(!fraisRetardDateCouverture.dejaInitialise) {
			_fraisRetardDate(fraisRetardDateCouverture);
			if(fraisRetardDate == null)
				setFraisRetardDate(fraisRetardDateCouverture.o);
		}
		fraisRetardDateCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Date staticSolrFraisRetardDate(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrFraisRetardDate(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqFraisRetardDate(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrFraisRetardDate(requeteSite_, PaiementScolaire.staticSolrFraisRetardDate(requeteSite_, PaiementScolaire.staticSetFraisRetardDate(requeteSite_, o)));
	}

	public Date solrFraisRetardDate() {
		return PaiementScolaire.staticSolrFraisRetardDate(requeteSite_, fraisRetardDate);
	}

	public String strFraisRetardDate() {
		return fraisRetardDate == null ? "" : fraisRetardDate.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonFraisRetardDate() {
		return fraisRetardDate == null ? "" : fraisRetardDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageFraisRetardDate() {
		return "date frais de retard";
	}

	public String htmTooltipFraisRetardDate() {
		return null;
	}

	public String htmFraisRetardDate() {
		return fraisRetardDate == null ? "" : StringEscapeUtils.escapeHtml4(strFraisRetardDate());
	}

	public void inputFraisRetardDate(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setFraisRetardDate classPaiementScolaire inputPaiementScolaire", pk, "FraisRetardDate w3-input w3-border ")
					.a("placeholder", "DD-MM-YYYY")
					.a("data-timeformat", "dd-MM-yyyy")
					.a("id", classeApiMethodeMethode, "_fraisRetardDate")
					.a("title", "La clé primaire des enfants dans la base de données.  (DD-MM-YYYY)")
					.a("value", fraisRetardDate == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(fraisRetardDate));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFraisRetardDate', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_fraisRetardDate')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_fraisRetardDate')); }); } ");
			}
			fg();
		} else {
				e("span").a("class", "varPaiementScolaire", pk, "FraisRetardDate ").f().sx(htmFraisRetardDate()).g("span");
		}
	}

	public void htmFraisRetardDate(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolaireFraisRetardDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_fraisRetardDate").a("class", "").f().sx("date frais de retard").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputFraisRetardDate(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_fraisRetardDate')); $('#", classeApiMethodeMethode, "_fraisRetardDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PaiementScolaireForm :input[name=pk]').val() }], 'setFraisRetardDate', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_fraisRetardDate')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_fraisRetardDate')); }); ")
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
	// paiementAnnee //
	///////////////////

	/**	 L'entité paiementAnnee
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer paiementAnnee;
	@JsonIgnore
	public Couverture<Integer> paiementAnneeCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("paiementAnnee").o(paiementAnnee);

	/**	<br/> L'entité paiementAnnee
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementAnnee">Trouver l'entité paiementAnnee dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementAnnee(Couverture<Integer> c);

	public Integer getPaiementAnnee() {
		return paiementAnnee;
	}

	public void setPaiementAnnee(Integer paiementAnnee) {
		this.paiementAnnee = paiementAnnee;
		this.paiementAnneeCouverture.dejaInitialise = true;
	}
	public void setPaiementAnnee(String o) {
		this.paiementAnnee = PaiementScolaire.staticSetPaiementAnnee(requeteSite_, o);
		this.paiementAnneeCouverture.dejaInitialise = true;
	}
	public static Integer staticSetPaiementAnnee(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected PaiementScolaire paiementAnneeInit() {
		if(!paiementAnneeCouverture.dejaInitialise) {
			_paiementAnnee(paiementAnneeCouverture);
			if(paiementAnnee == null)
				setPaiementAnnee(paiementAnneeCouverture.o);
		}
		paiementAnneeCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Integer staticSolrPaiementAnnee(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrPaiementAnnee(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementAnnee(requeteSite_, PaiementScolaire.staticSolrPaiementAnnee(requeteSite_, PaiementScolaire.staticSetPaiementAnnee(requeteSite_, o)));
	}

	public Integer solrPaiementAnnee() {
		return PaiementScolaire.staticSolrPaiementAnnee(requeteSite_, paiementAnnee);
	}

	public String strPaiementAnnee() {
		return paiementAnnee == null ? "" : paiementAnnee.toString();
	}

	public String jsonPaiementAnnee() {
		return paiementAnnee == null ? "" : paiementAnnee.toString();
	}

	public String nomAffichagePaiementAnnee() {
		return null;
	}

	public String htmTooltipPaiementAnnee() {
		return null;
	}

	public String htmPaiementAnnee() {
		return paiementAnnee == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementAnnee());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementMontant">Trouver l'entité paiementMontant dans Solr</a>
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
		this.paiementMontant = PaiementScolaire.staticSetPaiementMontant(requeteSite_, o);
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
	protected PaiementScolaire paiementMontantInit() {
		if(!paiementMontantCouverture.dejaInitialise) {
			_paiementMontant(paiementMontantCouverture);
			if(paiementMontant == null)
				setPaiementMontant(paiementMontantCouverture.o);
		}
		paiementMontantCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Double staticSolrPaiementMontant(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaiementMontant(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementMontant(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementMontant(requeteSite_, PaiementScolaire.staticSolrPaiementMontant(requeteSite_, PaiementScolaire.staticSetPaiementMontant(requeteSite_, o)));
	}

	public Double solrPaiementMontant() {
		return PaiementScolaire.staticSolrPaiementMontant(requeteSite_, paiementMontant);
	}

	public String strPaiementMontant() {
		return paiementMontant == null ? "" : paiementMontant.setScale(2, RoundingMode.HALF_UP).toString();
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "PaiementMontant ").f().sx(htmPaiementMontant()).g("span");
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
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
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

	/**	 L'entité paiementEspeces
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementEspeces;
	@JsonIgnore
	public Couverture<Boolean> paiementEspecesCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementEspeces").o(paiementEspeces);

	/**	<br/> L'entité paiementEspeces
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementEspeces">Trouver l'entité paiementEspeces dans Solr</a>
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
	public void setPaiementEspeces(String o) {
		this.paiementEspeces = PaiementScolaire.staticSetPaiementEspeces(requeteSite_, o);
		this.paiementEspecesCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPaiementEspeces(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrPaiementEspeces(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaiementEspeces(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementEspeces(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementEspeces(requeteSite_, PaiementScolaire.staticSolrPaiementEspeces(requeteSite_, PaiementScolaire.staticSetPaiementEspeces(requeteSite_, o)));
	}

	public Boolean solrPaiementEspeces() {
		return PaiementScolaire.staticSolrPaiementEspeces(requeteSite_, paiementEspeces);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "PaiementEspeces ").f().sx(htmPaiementEspeces()).g("span");
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

	/**	 L'entité paiementCheque
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementCheque;
	@JsonIgnore
	public Couverture<Boolean> paiementChequeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementCheque").o(paiementCheque);

	/**	<br/> L'entité paiementCheque
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementCheque">Trouver l'entité paiementCheque dans Solr</a>
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
	public void setPaiementCheque(String o) {
		this.paiementCheque = PaiementScolaire.staticSetPaiementCheque(requeteSite_, o);
		this.paiementChequeCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPaiementCheque(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrPaiementCheque(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaiementCheque(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementCheque(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementCheque(requeteSite_, PaiementScolaire.staticSolrPaiementCheque(requeteSite_, PaiementScolaire.staticSetPaiementCheque(requeteSite_, o)));
	}

	public Boolean solrPaiementCheque() {
		return PaiementScolaire.staticSolrPaiementCheque(requeteSite_, paiementCheque);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "PaiementCheque ").f().sx(htmPaiementCheque()).g("span");
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

	////////////////////
	// paiementECheck //
	////////////////////

	/**	 L'entité paiementECheck
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementECheck;
	@JsonIgnore
	public Couverture<Boolean> paiementECheckCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementECheck").o(paiementECheck);

	/**	<br/> L'entité paiementECheck
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementECheck">Trouver l'entité paiementECheck dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementECheck(Couverture<Boolean> c);

	public Boolean getPaiementECheck() {
		return paiementECheck;
	}

	public void setPaiementECheck(Boolean paiementECheck) {
		this.paiementECheck = paiementECheck;
		this.paiementECheckCouverture.dejaInitialise = true;
	}
	public void setPaiementECheck(String o) {
		this.paiementECheck = PaiementScolaire.staticSetPaiementECheck(requeteSite_, o);
		this.paiementECheckCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPaiementECheck(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected PaiementScolaire paiementECheckInit() {
		if(!paiementECheckCouverture.dejaInitialise) {
			_paiementECheck(paiementECheckCouverture);
			if(paiementECheck == null)
				setPaiementECheck(paiementECheckCouverture.o);
		}
		paiementECheckCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Boolean staticSolrPaiementECheck(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaiementECheck(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementECheck(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementECheck(requeteSite_, PaiementScolaire.staticSolrPaiementECheck(requeteSite_, PaiementScolaire.staticSetPaiementECheck(requeteSite_, o)));
	}

	public Boolean solrPaiementECheck() {
		return PaiementScolaire.staticSolrPaiementECheck(requeteSite_, paiementECheck);
	}

	public String strPaiementECheck() {
		return paiementECheck == null ? "" : paiementECheck.toString();
	}

	public String jsonPaiementECheck() {
		return paiementECheck == null ? "" : paiementECheck.toString();
	}

	public String nomAffichagePaiementECheck() {
		return "e-check";
	}

	public String htmTooltipPaiementECheck() {
		return null;
	}

	public String htmPaiementECheck() {
		return paiementECheck == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementECheck());
	}

	public void inputPaiementECheck(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_paiementECheck")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_paiementECheck");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPaiementECheck classPaiementScolaire inputPaiementScolaire", pk, "PaiementECheck w3-input w3-border ");
				a("name", "setPaiementECheck");
			} else {
				a("class", "valeurPaiementECheck classPaiementScolaire inputPaiementScolaire", pk, "PaiementECheck w3-input w3-border ");
				a("name", "paiementECheck");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementECheck', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementECheck')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementECheck')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPaiementECheck() != null && getPaiementECheck())
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
				e("span").a("class", "varPaiementScolaire", pk, "PaiementECheck ").f().sx(htmPaiementECheck()).g("span");
		}
	}

	public void htmPaiementECheck(String classeApiMethodeMethode) {
		PaiementScolaire s = (PaiementScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PaiementScolairePaiementECheck").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("for", classeApiMethodeMethode, "_paiementECheck").a("class", "").f().sx("e-check").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPaiementECheck(classeApiMethodeMethode);
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

	/**	 L'entité paiementSysteme
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementSysteme;
	@JsonIgnore
	public Couverture<Boolean> paiementSystemeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementSysteme").o(paiementSysteme);

	/**	<br/> L'entité paiementSysteme
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementSysteme">Trouver l'entité paiementSysteme dans Solr</a>
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
	public void setPaiementSysteme(String o) {
		this.paiementSysteme = PaiementScolaire.staticSetPaiementSysteme(requeteSite_, o);
		this.paiementSystemeCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPaiementSysteme(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrPaiementSysteme(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaiementSysteme(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementSysteme(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementSysteme(requeteSite_, PaiementScolaire.staticSolrPaiementSysteme(requeteSite_, PaiementScolaire.staticSetPaiementSysteme(requeteSite_, o)));
	}

	public Boolean solrPaiementSysteme() {
		return PaiementScolaire.staticSolrPaiementSysteme(requeteSite_, paiementSysteme);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "PaiementSysteme ").f().sx(htmPaiementSysteme()).g("span");
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

	//////////////////
	// paiementType //
	//////////////////

	/**	 L'entité paiementType
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paiementType;
	@JsonIgnore
	public Couverture<String> paiementTypeCouverture = new Couverture<String>().p(this).c(String.class).var("paiementType").o(paiementType);

	/**	<br/> L'entité paiementType
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementType">Trouver l'entité paiementType dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementType(Couverture<String> c);

	public String getPaiementType() {
		return paiementType;
	}
	public void setPaiementType(String o) {
		this.paiementType = PaiementScolaire.staticSetPaiementType(requeteSite_, o);
		this.paiementTypeCouverture.dejaInitialise = true;
	}
	public static String staticSetPaiementType(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected PaiementScolaire paiementTypeInit() {
		if(!paiementTypeCouverture.dejaInitialise) {
			_paiementType(paiementTypeCouverture);
			if(paiementType == null)
				setPaiementType(paiementTypeCouverture.o);
		}
		paiementTypeCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static String staticSolrPaiementType(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPaiementType(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementType(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementType(requeteSite_, PaiementScolaire.staticSolrPaiementType(requeteSite_, PaiementScolaire.staticSetPaiementType(requeteSite_, o)));
	}

	public String solrPaiementType() {
		return PaiementScolaire.staticSolrPaiementType(requeteSite_, paiementType);
	}

	public String strPaiementType() {
		return paiementType == null ? "" : paiementType;
	}

	public String jsonPaiementType() {
		return paiementType == null ? "" : paiementType;
	}

	public String nomAffichagePaiementType() {
		return null;
	}

	public String htmTooltipPaiementType() {
		return null;
	}

	public String htmPaiementType() {
		return paiementType == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementType());
	}

	/////////////////
	// paiementPar //
	/////////////////

	/**	 L'entité paiementPar
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paiementPar;
	@JsonIgnore
	public Couverture<String> paiementParCouverture = new Couverture<String>().p(this).c(String.class).var("paiementPar").o(paiementPar);

	/**	<br/> L'entité paiementPar
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementPar">Trouver l'entité paiementPar dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementPar(Couverture<String> c);

	public String getPaiementPar() {
		return paiementPar;
	}
	public void setPaiementPar(String o) {
		this.paiementPar = PaiementScolaire.staticSetPaiementPar(requeteSite_, o);
		this.paiementParCouverture.dejaInitialise = true;
	}
	public static String staticSetPaiementPar(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPaiementPar(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPaiementPar(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementPar(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementPar(requeteSite_, PaiementScolaire.staticSolrPaiementPar(requeteSite_, PaiementScolaire.staticSetPaiementPar(requeteSite_, o)));
	}

	public String solrPaiementPar() {
		return PaiementScolaire.staticSolrPaiementPar(requeteSite_, paiementPar);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "PaiementPar ").f().sx(htmPaiementPar()).g("span");
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
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
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

	/**	 L'entité transactionId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String transactionId;
	@JsonIgnore
	public Couverture<String> transactionIdCouverture = new Couverture<String>().p(this).c(String.class).var("transactionId").o(transactionId);

	/**	<br/> L'entité transactionId
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:transactionId">Trouver l'entité transactionId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _transactionId(Couverture<String> c);

	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String o) {
		this.transactionId = PaiementScolaire.staticSetTransactionId(requeteSite_, o);
		this.transactionIdCouverture.dejaInitialise = true;
	}
	public static String staticSetTransactionId(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrTransactionId(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrTransactionId(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqTransactionId(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrTransactionId(requeteSite_, PaiementScolaire.staticSolrTransactionId(requeteSite_, PaiementScolaire.staticSetTransactionId(requeteSite_, o)));
	}

	public String solrTransactionId() {
		return PaiementScolaire.staticSolrTransactionId(requeteSite_, transactionId);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "TransactionId ").f().sx(htmTransactionId()).g("span");
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
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
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

	/**	 L'entité customerProfileId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId;
	@JsonIgnore
	public Couverture<String> customerProfileIdCouverture = new Couverture<String>().p(this).c(String.class).var("customerProfileId").o(customerProfileId);

	/**	<br/> L'entité customerProfileId
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:customerProfileId">Trouver l'entité customerProfileId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _customerProfileId(Couverture<String> c);

	public String getCustomerProfileId() {
		return customerProfileId;
	}
	public void setCustomerProfileId(String o) {
		this.customerProfileId = PaiementScolaire.staticSetCustomerProfileId(requeteSite_, o);
		this.customerProfileIdCouverture.dejaInitialise = true;
	}
	public static String staticSetCustomerProfileId(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrCustomerProfileId(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrCustomerProfileId(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqCustomerProfileId(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrCustomerProfileId(requeteSite_, PaiementScolaire.staticSolrCustomerProfileId(requeteSite_, PaiementScolaire.staticSetCustomerProfileId(requeteSite_, o)));
	}

	public String solrCustomerProfileId() {
		return PaiementScolaire.staticSolrCustomerProfileId(requeteSite_, customerProfileId);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "CustomerProfileId ").f().sx(htmCustomerProfileId()).g("span");
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
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
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

	/**	 L'entité transactionStatus
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String transactionStatus;
	@JsonIgnore
	public Couverture<String> transactionStatusCouverture = new Couverture<String>().p(this).c(String.class).var("transactionStatus").o(transactionStatus);

	/**	<br/> L'entité transactionStatus
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:transactionStatus">Trouver l'entité transactionStatus dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _transactionStatus(Couverture<String> c);

	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String o) {
		this.transactionStatus = PaiementScolaire.staticSetTransactionStatus(requeteSite_, o);
		this.transactionStatusCouverture.dejaInitialise = true;
	}
	public static String staticSetTransactionStatus(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrTransactionStatus(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrTransactionStatus(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqTransactionStatus(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrTransactionStatus(requeteSite_, PaiementScolaire.staticSolrTransactionStatus(requeteSite_, PaiementScolaire.staticSetTransactionStatus(requeteSite_, o)));
	}

	public String solrTransactionStatus() {
		return PaiementScolaire.staticSolrTransactionStatus(requeteSite_, transactionStatus);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "TransactionStatus ").f().sx(htmTransactionStatus()).g("span");
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
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
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

	/**	 L'entité paiementRecu
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paiementRecu;
	@JsonIgnore
	public Couverture<Boolean> paiementRecuCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("paiementRecu").o(paiementRecu);

	/**	<br/> L'entité paiementRecu
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementRecu">Trouver l'entité paiementRecu dans Solr</a>
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
	public void setPaiementRecu(String o) {
		this.paiementRecu = PaiementScolaire.staticSetPaiementRecu(requeteSite_, o);
		this.paiementRecuCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPaiementRecu(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrPaiementRecu(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaiementRecu(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementRecu(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementRecu(requeteSite_, PaiementScolaire.staticSolrPaiementRecu(requeteSite_, PaiementScolaire.staticSetPaiementRecu(requeteSite_, o)));
	}

	public Boolean solrPaiementRecu() {
		return PaiementScolaire.staticSolrPaiementRecu(requeteSite_, paiementRecu);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "PaiementRecu ").f().sx(htmPaiementRecu()).g("span");
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontant">Trouver l'entité fraisMontant dans Solr</a>
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
		this.fraisMontant = PaiementScolaire.staticSetFraisMontant(requeteSite_, o);
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
	protected PaiementScolaire fraisMontantInit() {
		if(!fraisMontantCouverture.dejaInitialise) {
			_fraisMontant(fraisMontantCouverture);
			if(fraisMontant == null)
				setFraisMontant(fraisMontantCouverture.o);
		}
		fraisMontantCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Double staticSolrFraisMontant(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrFraisMontant(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisMontant(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrFraisMontant(requeteSite_, PaiementScolaire.staticSolrFraisMontant(requeteSite_, PaiementScolaire.staticSetFraisMontant(requeteSite_, o)));
	}

	public Double solrFraisMontant() {
		return PaiementScolaire.staticSolrFraisMontant(requeteSite_, fraisMontant);
	}

	public String strFraisMontant() {
		return fraisMontant == null ? "" : fraisMontant.setScale(2, RoundingMode.HALF_UP).toString();
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "FraisMontant ").f().sx(htmFraisMontant()).g("span");
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
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
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

	/**	 L'entité fraisPremierDernier
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean fraisPremierDernier;
	@JsonIgnore
	public Couverture<Boolean> fraisPremierDernierCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("fraisPremierDernier").o(fraisPremierDernier);

	/**	<br/> L'entité fraisPremierDernier
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisPremierDernier">Trouver l'entité fraisPremierDernier dans Solr</a>
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
	public void setFraisPremierDernier(String o) {
		this.fraisPremierDernier = PaiementScolaire.staticSetFraisPremierDernier(requeteSite_, o);
		this.fraisPremierDernierCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetFraisPremierDernier(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrFraisPremierDernier(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrFraisPremierDernier(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisPremierDernier(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrFraisPremierDernier(requeteSite_, PaiementScolaire.staticSolrFraisPremierDernier(requeteSite_, PaiementScolaire.staticSetFraisPremierDernier(requeteSite_, o)));
	}

	public Boolean solrFraisPremierDernier() {
		return PaiementScolaire.staticSolrFraisPremierDernier(requeteSite_, fraisPremierDernier);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "FraisPremierDernier ").f().sx(htmFraisPremierDernier()).g("span");
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

	/**	 L'entité fraisInscription
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean fraisInscription;
	@JsonIgnore
	public Couverture<Boolean> fraisInscriptionCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("fraisInscription").o(fraisInscription);

	/**	<br/> L'entité fraisInscription
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisInscription">Trouver l'entité fraisInscription dans Solr</a>
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
	public void setFraisInscription(String o) {
		this.fraisInscription = PaiementScolaire.staticSetFraisInscription(requeteSite_, o);
		this.fraisInscriptionCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetFraisInscription(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrFraisInscription(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrFraisInscription(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisInscription(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrFraisInscription(requeteSite_, PaiementScolaire.staticSolrFraisInscription(requeteSite_, PaiementScolaire.staticSetFraisInscription(requeteSite_, o)));
	}

	public Boolean solrFraisInscription() {
		return PaiementScolaire.staticSolrFraisInscription(requeteSite_, fraisInscription);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "FraisInscription ").f().sx(htmFraisInscription()).g("span");
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

	/**	 L'entité fraisMois
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean fraisMois;
	@JsonIgnore
	public Couverture<Boolean> fraisMoisCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("fraisMois").o(fraisMois);

	/**	<br/> L'entité fraisMois
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMois">Trouver l'entité fraisMois dans Solr</a>
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
	public void setFraisMois(String o) {
		this.fraisMois = PaiementScolaire.staticSetFraisMois(requeteSite_, o);
		this.fraisMoisCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetFraisMois(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrFraisMois(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrFraisMois(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisMois(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrFraisMois(requeteSite_, PaiementScolaire.staticSolrFraisMois(requeteSite_, PaiementScolaire.staticSetFraisMois(requeteSite_, o)));
	}

	public Boolean solrFraisMois() {
		return PaiementScolaire.staticSolrFraisMois(requeteSite_, fraisMois);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "FraisMois ").f().sx(htmFraisMois()).g("span");
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

	/**	 L'entité fraisRetard
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean fraisRetard;
	@JsonIgnore
	public Couverture<Boolean> fraisRetardCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("fraisRetard").o(fraisRetard);

	/**	<br/> L'entité fraisRetard
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisRetard">Trouver l'entité fraisRetard dans Solr</a>
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
	public void setFraisRetard(String o) {
		this.fraisRetard = PaiementScolaire.staticSetFraisRetard(requeteSite_, o);
		this.fraisRetardCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetFraisRetard(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrFraisRetard(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrFraisRetard(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisRetard(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrFraisRetard(requeteSite_, PaiementScolaire.staticSolrFraisRetard(requeteSite_, PaiementScolaire.staticSetFraisRetard(requeteSite_, o)));
	}

	public Boolean solrFraisRetard() {
		return PaiementScolaire.staticSolrFraisRetard(requeteSite_, fraisRetard);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "FraisRetard ").f().sx(htmFraisRetard()).g("span");
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

	/////////
	// now //
	/////////

	/**	 L'entité now
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate now;
	@JsonIgnore
	public Couverture<LocalDate> nowCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("now").o(now);

	/**	<br/> L'entité now
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:now">Trouver l'entité now dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _now(Couverture<LocalDate> c);

	public LocalDate getNow() {
		return now;
	}

	public void setNow(LocalDate now) {
		this.now = now;
		this.nowCouverture.dejaInitialise = true;
	}
	public void setNow(Instant o) {
		this.now = o == null ? null : LocalDate.from(o);
		this.nowCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setNow(String o) {
		this.now = PaiementScolaire.staticSetNow(requeteSite_, o);
		this.nowCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetNow(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setNow(Date o) {
		this.now = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.nowCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire nowInit() {
		if(!nowCouverture.dejaInitialise) {
			_now(nowCouverture);
			if(now == null)
				setNow(nowCouverture.o);
		}
		nowCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Date staticSolrNow(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrNow(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqNow(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrNow(requeteSite_, PaiementScolaire.staticSolrNow(requeteSite_, PaiementScolaire.staticSetNow(requeteSite_, o)));
	}

	public Date solrNow() {
		return PaiementScolaire.staticSolrNow(requeteSite_, now);
	}

	public String strNow() {
		return now == null ? "" : now.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonNow() {
		return now == null ? "" : now.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageNow() {
		return null;
	}

	public String htmTooltipNow() {
		return null;
	}

	public String htmNow() {
		return now == null ? "" : StringEscapeUtils.escapeHtml4(strNow());
	}

	//////////////////
	// paiementJour //
	//////////////////

	/**	 L'entité paiementJour
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer paiementJour;
	@JsonIgnore
	public Couverture<Integer> paiementJourCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("paiementJour").o(paiementJour);

	/**	<br/> L'entité paiementJour
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementJour">Trouver l'entité paiementJour dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementJour(Couverture<Integer> c);

	public Integer getPaiementJour() {
		return paiementJour;
	}

	public void setPaiementJour(Integer paiementJour) {
		this.paiementJour = paiementJour;
		this.paiementJourCouverture.dejaInitialise = true;
	}
	public void setPaiementJour(String o) {
		this.paiementJour = PaiementScolaire.staticSetPaiementJour(requeteSite_, o);
		this.paiementJourCouverture.dejaInitialise = true;
	}
	public static Integer staticSetPaiementJour(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected PaiementScolaire paiementJourInit() {
		if(!paiementJourCouverture.dejaInitialise) {
			_paiementJour(paiementJourCouverture);
			if(paiementJour == null)
				setPaiementJour(paiementJourCouverture.o);
		}
		paiementJourCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Integer staticSolrPaiementJour(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrPaiementJour(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementJour(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementJour(requeteSite_, PaiementScolaire.staticSolrPaiementJour(requeteSite_, PaiementScolaire.staticSetPaiementJour(requeteSite_, o)));
	}

	public Integer solrPaiementJour() {
		return PaiementScolaire.staticSolrPaiementJour(requeteSite_, paiementJour);
	}

	public String strPaiementJour() {
		return paiementJour == null ? "" : paiementJour.toString();
	}

	public String jsonPaiementJour() {
		return paiementJour == null ? "" : paiementJour.toString();
	}

	public String nomAffichagePaiementJour() {
		return null;
	}

	public String htmTooltipPaiementJour() {
		return null;
	}

	public String htmPaiementJour() {
		return paiementJour == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementJour());
	}

	//////////////////////
	// paiementProchain //
	//////////////////////

	/**	 L'entité paiementProchain
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate paiementProchain;
	@JsonIgnore
	public Couverture<LocalDate> paiementProchainCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("paiementProchain").o(paiementProchain);

	/**	<br/> L'entité paiementProchain
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementProchain">Trouver l'entité paiementProchain dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementProchain(Couverture<LocalDate> c);

	public LocalDate getPaiementProchain() {
		return paiementProchain;
	}

	public void setPaiementProchain(LocalDate paiementProchain) {
		this.paiementProchain = paiementProchain;
		this.paiementProchainCouverture.dejaInitialise = true;
	}
	public void setPaiementProchain(Instant o) {
		this.paiementProchain = o == null ? null : LocalDate.from(o);
		this.paiementProchainCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setPaiementProchain(String o) {
		this.paiementProchain = PaiementScolaire.staticSetPaiementProchain(requeteSite_, o);
		this.paiementProchainCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetPaiementProchain(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setPaiementProchain(Date o) {
		this.paiementProchain = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.paiementProchainCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire paiementProchainInit() {
		if(!paiementProchainCouverture.dejaInitialise) {
			_paiementProchain(paiementProchainCouverture);
			if(paiementProchain == null)
				setPaiementProchain(paiementProchainCouverture.o);
		}
		paiementProchainCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Date staticSolrPaiementProchain(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrPaiementProchain(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqPaiementProchain(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementProchain(requeteSite_, PaiementScolaire.staticSolrPaiementProchain(requeteSite_, PaiementScolaire.staticSetPaiementProchain(requeteSite_, o)));
	}

	public Date solrPaiementProchain() {
		return PaiementScolaire.staticSolrPaiementProchain(requeteSite_, paiementProchain);
	}

	public String strPaiementProchain() {
		return paiementProchain == null ? "" : paiementProchain.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonPaiementProchain() {
		return paiementProchain == null ? "" : paiementProchain.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichagePaiementProchain() {
		return "date de paiement prochaîne";
	}

	public String htmTooltipPaiementProchain() {
		return null;
	}

	public String htmPaiementProchain() {
		return paiementProchain == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementProchain());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontantDu">Trouver l'entité fraisMontantDu dans Solr</a>
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
		this.fraisMontantDu = PaiementScolaire.staticSetFraisMontantDu(requeteSite_, o);
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
	protected PaiementScolaire fraisMontantDuInit() {
		if(!fraisMontantDuCouverture.dejaInitialise) {
			_fraisMontantDu(fraisMontantDuCouverture);
			if(fraisMontantDu == null)
				setFraisMontantDu(fraisMontantDuCouverture.o);
		}
		fraisMontantDuCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Double staticSolrFraisMontantDu(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrFraisMontantDu(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisMontantDu(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrFraisMontantDu(requeteSite_, PaiementScolaire.staticSolrFraisMontantDu(requeteSite_, PaiementScolaire.staticSetFraisMontantDu(requeteSite_, o)));
	}

	public Double solrFraisMontantDu() {
		return PaiementScolaire.staticSolrFraisMontantDu(requeteSite_, fraisMontantDu);
	}

	public String strFraisMontantDu() {
		return fraisMontantDu == null ? "" : fraisMontantDu.setScale(2, RoundingMode.HALF_UP).toString();
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

	///////////////////////
	// fraisMontantPasse //
	///////////////////////

	/**	 L'entité fraisMontantPasse
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal fraisMontantPasse;
	@JsonIgnore
	public Couverture<BigDecimal> fraisMontantPasseCouverture = new Couverture<BigDecimal>().p(this).c(BigDecimal.class).var("fraisMontantPasse").o(fraisMontantPasse);

	/**	<br/> L'entité fraisMontantPasse
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontantPasse">Trouver l'entité fraisMontantPasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _fraisMontantPasse(Couverture<BigDecimal> c);

	public BigDecimal getFraisMontantPasse() {
		return fraisMontantPasse;
	}

	public void setFraisMontantPasse(BigDecimal fraisMontantPasse) {
		this.fraisMontantPasse = fraisMontantPasse;
		this.fraisMontantPasseCouverture.dejaInitialise = true;
	}
	public void setFraisMontantPasse(String o) {
		this.fraisMontantPasse = PaiementScolaire.staticSetFraisMontantPasse(requeteSite_, o);
		this.fraisMontantPasseCouverture.dejaInitialise = true;
	}
	public static BigDecimal staticSetFraisMontantPasse(RequeteSiteFrFR requeteSite_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setFraisMontantPasse(Double o) {
			this.fraisMontantPasse = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMontantPasseCouverture.dejaInitialise = true;
	}
	public void setFraisMontantPasse(Integer o) {
			this.fraisMontantPasse = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.fraisMontantPasseCouverture.dejaInitialise = true;
	}
	protected PaiementScolaire fraisMontantPasseInit() {
		if(!fraisMontantPasseCouverture.dejaInitialise) {
			_fraisMontantPasse(fraisMontantPasseCouverture);
			if(fraisMontantPasse == null)
				setFraisMontantPasse(fraisMontantPasseCouverture.o);
		}
		fraisMontantPasseCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Double staticSolrFraisMontantPasse(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrFraisMontantPasse(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisMontantPasse(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrFraisMontantPasse(requeteSite_, PaiementScolaire.staticSolrFraisMontantPasse(requeteSite_, PaiementScolaire.staticSetFraisMontantPasse(requeteSite_, o)));
	}

	public Double solrFraisMontantPasse() {
		return PaiementScolaire.staticSolrFraisMontantPasse(requeteSite_, fraisMontantPasse);
	}

	public String strFraisMontantPasse() {
		return fraisMontantPasse == null ? "" : fraisMontantPasse.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonFraisMontantPasse() {
		return fraisMontantPasse == null ? "" : fraisMontantPasse.toString();
	}

	public String nomAffichageFraisMontantPasse() {
		return "frais montant du passé";
	}

	public String htmTooltipFraisMontantPasse() {
		return null;
	}

	public String htmFraisMontantPasse() {
		return fraisMontantPasse == null ? "" : StringEscapeUtils.escapeHtml4(strFraisMontantPasse());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontantNonPasse">Trouver l'entité fraisMontantNonPasse dans Solr</a>
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
		this.fraisMontantNonPasse = PaiementScolaire.staticSetFraisMontantNonPasse(requeteSite_, o);
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
	protected PaiementScolaire fraisMontantNonPasseInit() {
		if(!fraisMontantNonPasseCouverture.dejaInitialise) {
			_fraisMontantNonPasse(fraisMontantNonPasseCouverture);
			if(fraisMontantNonPasse == null)
				setFraisMontantNonPasse(fraisMontantNonPasseCouverture.o);
		}
		fraisMontantNonPasseCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Double staticSolrFraisMontantNonPasse(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrFraisMontantNonPasse(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisMontantNonPasse(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrFraisMontantNonPasse(requeteSite_, PaiementScolaire.staticSolrFraisMontantNonPasse(requeteSite_, PaiementScolaire.staticSetFraisMontantNonPasse(requeteSite_, o)));
	}

	public Double solrFraisMontantNonPasse() {
		return PaiementScolaire.staticSolrFraisMontantNonPasse(requeteSite_, fraisMontantNonPasse);
	}

	public String strFraisMontantNonPasse() {
		return fraisMontantNonPasse == null ? "" : fraisMontantNonPasse.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonFraisMontantNonPasse() {
		return fraisMontantNonPasse == null ? "" : fraisMontantNonPasse.toString();
	}

	public String nomAffichageFraisMontantNonPasse() {
		return "frais montant pas du passé";
	}

	public String htmTooltipFraisMontantNonPasse() {
		return null;
	}

	public String htmFraisMontantNonPasse() {
		return fraisMontantNonPasse == null ? "" : StringEscapeUtils.escapeHtml4(strFraisMontantNonPasse());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:fraisMontantFuture">Trouver l'entité fraisMontantFuture dans Solr</a>
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
		this.fraisMontantFuture = PaiementScolaire.staticSetFraisMontantFuture(requeteSite_, o);
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
	protected PaiementScolaire fraisMontantFutureInit() {
		if(!fraisMontantFutureCouverture.dejaInitialise) {
			_fraisMontantFuture(fraisMontantFutureCouverture);
			if(fraisMontantFuture == null)
				setFraisMontantFuture(fraisMontantFutureCouverture.o);
		}
		fraisMontantFutureCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Double staticSolrFraisMontantFuture(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrFraisMontantFuture(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFraisMontantFuture(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrFraisMontantFuture(requeteSite_, PaiementScolaire.staticSolrFraisMontantFuture(requeteSite_, PaiementScolaire.staticSetFraisMontantFuture(requeteSite_, o)));
	}

	public Double solrFraisMontantFuture() {
		return PaiementScolaire.staticSolrFraisMontantFuture(requeteSite_, fraisMontantFuture);
	}

	public String strFraisMontantFuture() {
		return fraisMontantFuture == null ? "" : fraisMontantFuture.setScale(2, RoundingMode.HALF_UP).toString();
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

	//////////////////////
	// paiementNomCourt //
	//////////////////////

	/**	 L'entité paiementNomCourt
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paiementNomCourt;
	@JsonIgnore
	public Couverture<String> paiementNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("paiementNomCourt").o(paiementNomCourt);

	/**	<br/> L'entité paiementNomCourt
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementNomCourt">Trouver l'entité paiementNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementNomCourt(Couverture<String> c);

	public String getPaiementNomCourt() {
		return paiementNomCourt;
	}
	public void setPaiementNomCourt(String o) {
		this.paiementNomCourt = PaiementScolaire.staticSetPaiementNomCourt(requeteSite_, o);
		this.paiementNomCourtCouverture.dejaInitialise = true;
	}
	public static String staticSetPaiementNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPaiementNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPaiementNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementNomCourt(requeteSite_, PaiementScolaire.staticSolrPaiementNomCourt(requeteSite_, PaiementScolaire.staticSetPaiementNomCourt(requeteSite_, o)));
	}

	public String solrPaiementNomCourt() {
		return PaiementScolaire.staticSolrPaiementNomCourt(requeteSite_, paiementNomCourt);
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
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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

		} else {
				e("span").a("class", "varPaiementScolaire", pk, "PaiementNomCourt ").f().sx(htmPaiementNomCourt()).g("span");
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
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
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

	/**	 L'entité paiementNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paiementNomComplet;
	@JsonIgnore
	public Couverture<String> paiementNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("paiementNomComplet").o(paiementNomComplet);

	/**	<br/> L'entité paiementNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementNomComplet">Trouver l'entité paiementNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementNomComplet(Couverture<String> c);

	public String getPaiementNomComplet() {
		return paiementNomComplet;
	}
	public void setPaiementNomComplet(String o) {
		this.paiementNomComplet = PaiementScolaire.staticSetPaiementNomComplet(requeteSite_, o);
		this.paiementNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetPaiementNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPaiementNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPaiementNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementNomComplet(requeteSite_, PaiementScolaire.staticSolrPaiementNomComplet(requeteSite_, PaiementScolaire.staticSetPaiementNomComplet(requeteSite_, o)));
	}

	public String solrPaiementNomComplet() {
		return PaiementScolaire.staticSolrPaiementNomComplet(requeteSite_, paiementNomComplet);
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

	/////////////////////
	// paiementsGroupe //
	/////////////////////

	/**	 L'entité paiementsGroupe
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<PaiementScolaire>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<PaiementScolaire> paiementsGroupe = new ArrayList<PaiementScolaire>();
	@JsonIgnore
	public Couverture<List<PaiementScolaire>> paiementsGroupeCouverture = new Couverture<List<PaiementScolaire>>().p(this).c(List.class).var("paiementsGroupe").o(paiementsGroupe);

	/**	<br/> L'entité paiementsGroupe
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<PaiementScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementsGroupe">Trouver l'entité paiementsGroupe dans Solr</a>
	 * <br/>
	 * @param paiementsGroupe est l'entité déjà construit. 
	 **/
	protected abstract void _paiementsGroupe(List<PaiementScolaire> l);

	public List<PaiementScolaire> getPaiementsGroupe() {
		return paiementsGroupe;
	}

	public void setPaiementsGroupe(List<PaiementScolaire> paiementsGroupe) {
		this.paiementsGroupe = paiementsGroupe;
		this.paiementsGroupeCouverture.dejaInitialise = true;
	}
	public static PaiementScolaire staticSetPaiementsGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	public PaiementScolaire addPaiementsGroupe(PaiementScolaire...objets) {
		for(PaiementScolaire o : objets) {
			addPaiementsGroupe(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addPaiementsGroupe(PaiementScolaire o) {
		if(o != null && !paiementsGroupe.contains(o))
			this.paiementsGroupe.add(o);
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementsGroupeInit() {
		if(!paiementsGroupeCouverture.dejaInitialise) {
			_paiementsGroupe(paiementsGroupe);
		}
		paiementsGroupeCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	//////////////////////
	// paiementsPaiment //
	//////////////////////

	/**	 L'entité paiementsPaiment
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<PaiementScolaire>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<PaiementScolaire> paiementsPaiment = new ArrayList<PaiementScolaire>();
	@JsonIgnore
	public Couverture<List<PaiementScolaire>> paiementsPaimentCouverture = new Couverture<List<PaiementScolaire>>().p(this).c(List.class).var("paiementsPaiment").o(paiementsPaiment);

	/**	<br/> L'entité paiementsPaiment
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<PaiementScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementsPaiment">Trouver l'entité paiementsPaiment dans Solr</a>
	 * <br/>
	 * @param paiementsPaiment est l'entité déjà construit. 
	 **/
	protected abstract void _paiementsPaiment(List<PaiementScolaire> l);

	public List<PaiementScolaire> getPaiementsPaiment() {
		return paiementsPaiment;
	}

	public void setPaiementsPaiment(List<PaiementScolaire> paiementsPaiment) {
		this.paiementsPaiment = paiementsPaiment;
		this.paiementsPaimentCouverture.dejaInitialise = true;
	}
	public static PaiementScolaire staticSetPaiementsPaiment(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	public PaiementScolaire addPaiementsPaiment(PaiementScolaire...objets) {
		for(PaiementScolaire o : objets) {
			addPaiementsPaiment(o);
		}
		return (PaiementScolaire)this;
	}
	public PaiementScolaire addPaiementsPaiment(PaiementScolaire o) {
		if(o != null && !paiementsPaiment.contains(o))
			this.paiementsPaiment.add(o);
		return (PaiementScolaire)this;
	}
	protected PaiementScolaire paiementsPaimentInit() {
		if(!paiementsPaimentCouverture.dejaInitialise) {
			_paiementsPaiment(paiementsPaiment);
		}
		paiementsPaimentCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	////////////////////
	// paiementNumero //
	////////////////////

	/**	 L'entité paiementNumero
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer paiementNumero;
	@JsonIgnore
	public Couverture<Integer> paiementNumeroCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("paiementNumero").o(paiementNumero);

	/**	<br/> L'entité paiementNumero
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.paiement.PaiementScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementNumero">Trouver l'entité paiementNumero dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementNumero(Couverture<Integer> c);

	public Integer getPaiementNumero() {
		return paiementNumero;
	}

	public void setPaiementNumero(Integer paiementNumero) {
		this.paiementNumero = paiementNumero;
		this.paiementNumeroCouverture.dejaInitialise = true;
	}
	public void setPaiementNumero(String o) {
		this.paiementNumero = PaiementScolaire.staticSetPaiementNumero(requeteSite_, o);
		this.paiementNumeroCouverture.dejaInitialise = true;
	}
	public static Integer staticSetPaiementNumero(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected PaiementScolaire paiementNumeroInit() {
		if(!paiementNumeroCouverture.dejaInitialise) {
			_paiementNumero(paiementNumeroCouverture);
			if(paiementNumero == null)
				setPaiementNumero(paiementNumeroCouverture.o);
		}
		paiementNumeroCouverture.dejaInitialise(true);
		return (PaiementScolaire)this;
	}

	public static Integer staticSolrPaiementNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrPaiementNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementNumero(RequeteSiteFrFR requeteSite_, String o) {
		return PaiementScolaire.staticSolrStrPaiementNumero(requeteSite_, PaiementScolaire.staticSolrPaiementNumero(requeteSite_, PaiementScolaire.staticSetPaiementNumero(requeteSite_, o)));
	}

	public Integer solrPaiementNumero() {
		return PaiementScolaire.staticSolrPaiementNumero(requeteSite_, paiementNumero);
	}

	public String strPaiementNumero() {
		return paiementNumero == null ? "" : paiementNumero.toString();
	}

	public String jsonPaiementNumero() {
		return paiementNumero == null ? "" : paiementNumero.toString();
	}

	public String nomAffichagePaiementNumero() {
		return null;
	}

	public String htmTooltipPaiementNumero() {
		return null;
	}

	public String htmPaiementNumero() {
		return paiementNumero == null ? "" : StringEscapeUtils.escapeHtml4(strPaiementNumero());
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
		ecoleAddresseInit();
		ecoleNumeroTelephoneInit();
		anneeCleInit();
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
		anneeFraisInscriptionInit();
		sessionDateDebutInit();
		sessionDateFinInit();
		ageDebutInit();
		ageFinInit();
		blocNomCompletInit();
		blocHeureDebutInit();
		blocHeureFinInit();
		blocPrixParMoisInit();
		inscriptionNomGroupeInit();
		blocPrixTotalInit();
		inscriptionPaimentChaqueMoisInit();
		paiementDescriptionInit();
		paiementDateInit();
		fraisRetardDateInit();
		paiementAnneeInit();
		paiementMontantInit();
		paiementEspecesInit();
		paiementChequeInit();
		paiementECheckInit();
		paiementSystemeInit();
		paiementTypeInit();
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
		nowInit();
		paiementJourInit();
		paiementProchainInit();
		fraisMontantDuInit();
		fraisMontantPasseInit();
		fraisMontantNonPasseInit();
		fraisMontantFutureInit();
		paiementNomCourtInit();
		paiementNomCompletInit();
		paiementsGroupeInit();
		paiementsPaimentInit();
		paiementNumeroInit();
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
			case "ecoleAddresse":
				return oPaiementScolaire.ecoleAddresse;
			case "ecoleNumeroTelephone":
				return oPaiementScolaire.ecoleNumeroTelephone;
			case "anneeCle":
				return oPaiementScolaire.anneeCle;
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
			case "blocNomComplet":
				return oPaiementScolaire.blocNomComplet;
			case "blocHeureDebut":
				return oPaiementScolaire.blocHeureDebut;
			case "blocHeureFin":
				return oPaiementScolaire.blocHeureFin;
			case "blocPrixParMois":
				return oPaiementScolaire.blocPrixParMois;
			case "inscriptionNomGroupe":
				return oPaiementScolaire.inscriptionNomGroupe;
			case "blocPrixTotal":
				return oPaiementScolaire.blocPrixTotal;
			case "inscriptionPaimentChaqueMois":
				return oPaiementScolaire.inscriptionPaimentChaqueMois;
			case "paiementDescription":
				return oPaiementScolaire.paiementDescription;
			case "paiementDate":
				return oPaiementScolaire.paiementDate;
			case "fraisRetardDate":
				return oPaiementScolaire.fraisRetardDate;
			case "paiementAnnee":
				return oPaiementScolaire.paiementAnnee;
			case "paiementMontant":
				return oPaiementScolaire.paiementMontant;
			case "paiementEspeces":
				return oPaiementScolaire.paiementEspeces;
			case "paiementCheque":
				return oPaiementScolaire.paiementCheque;
			case "paiementECheck":
				return oPaiementScolaire.paiementECheck;
			case "paiementSysteme":
				return oPaiementScolaire.paiementSysteme;
			case "paiementType":
				return oPaiementScolaire.paiementType;
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
			case "now":
				return oPaiementScolaire.now;
			case "paiementJour":
				return oPaiementScolaire.paiementJour;
			case "paiementProchain":
				return oPaiementScolaire.paiementProchain;
			case "fraisMontantDu":
				return oPaiementScolaire.fraisMontantDu;
			case "fraisMontantPasse":
				return oPaiementScolaire.fraisMontantPasse;
			case "fraisMontantNonPasse":
				return oPaiementScolaire.fraisMontantNonPasse;
			case "fraisMontantFuture":
				return oPaiementScolaire.fraisMontantFuture;
			case "paiementNomCourt":
				return oPaiementScolaire.paiementNomCourt;
			case "paiementNomComplet":
				return oPaiementScolaire.paiementNomComplet;
			case "paiementsGroupe":
				return oPaiementScolaire.paiementsGroupe;
			case "paiementsPaiment":
				return oPaiementScolaire.paiementsPaiment;
			case "paiementNumero":
				return oPaiementScolaire.paiementNumero;
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
				if(oPaiementScolaire.getInscriptionCle() == null)
					oPaiementScolaire.setInscriptionCle((Long)val);
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
		return staticSetPaiementScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetPaiementScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "paiementCle":
			return PaiementScolaire.staticSetPaiementCle(requeteSite_, o);
		case "inscriptionCle":
			return PaiementScolaire.staticSetInscriptionCle(requeteSite_, o);
		case "ecoleNumero":
			return PaiementScolaire.staticSetEcoleNumero(requeteSite_, o);
		case "utilisateurCles":
			return PaiementScolaire.staticSetUtilisateurCles(requeteSite_, o);
		case "ecoleCle":
			return PaiementScolaire.staticSetEcoleCle(requeteSite_, o);
		case "ecoleAddresse":
			return PaiementScolaire.staticSetEcoleAddresse(requeteSite_, o);
		case "ecoleNumeroTelephone":
			return PaiementScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o);
		case "anneeCle":
			return PaiementScolaire.staticSetAnneeCle(requeteSite_, o);
		case "sessionCle":
			return PaiementScolaire.staticSetSessionCle(requeteSite_, o);
		case "ageCle":
			return PaiementScolaire.staticSetAgeCle(requeteSite_, o);
		case "blocCle":
			return PaiementScolaire.staticSetBlocCle(requeteSite_, o);
		case "enfantCle":
			return PaiementScolaire.staticSetEnfantCle(requeteSite_, o);
		case "mereCles":
			return PaiementScolaire.staticSetMereCles(requeteSite_, o);
		case "pereCles":
			return PaiementScolaire.staticSetPereCles(requeteSite_, o);
		case "gardienCles":
			return PaiementScolaire.staticSetGardienCles(requeteSite_, o);
		case "enfantNomCompletPrefere":
			return PaiementScolaire.staticSetEnfantNomCompletPrefere(requeteSite_, o);
		case "enfantDateNaissance":
			return PaiementScolaire.staticSetEnfantDateNaissance(requeteSite_, o);
		case "mereNomCompletPrefere":
			return PaiementScolaire.staticSetMereNomCompletPrefere(requeteSite_, o);
		case "pereNomCompletPrefere":
			return PaiementScolaire.staticSetPereNomCompletPrefere(requeteSite_, o);
		case "ecoleNom":
			return PaiementScolaire.staticSetEcoleNom(requeteSite_, o);
		case "ecoleNomComplet":
			return PaiementScolaire.staticSetEcoleNomComplet(requeteSite_, o);
		case "ecoleEmplacement":
			return PaiementScolaire.staticSetEcoleEmplacement(requeteSite_, o);
		case "anneeDebut":
			return PaiementScolaire.staticSetAnneeDebut(requeteSite_, o);
		case "anneeFin":
			return PaiementScolaire.staticSetAnneeFin(requeteSite_, o);
		case "saisonDateDebut":
			return PaiementScolaire.staticSetSaisonDateDebut(requeteSite_, o);
		case "anneeFraisInscription":
			return PaiementScolaire.staticSetAnneeFraisInscription(requeteSite_, o);
		case "sessionDateDebut":
			return PaiementScolaire.staticSetSessionDateDebut(requeteSite_, o);
		case "sessionDateFin":
			return PaiementScolaire.staticSetSessionDateFin(requeteSite_, o);
		case "ageDebut":
			return PaiementScolaire.staticSetAgeDebut(requeteSite_, o);
		case "ageFin":
			return PaiementScolaire.staticSetAgeFin(requeteSite_, o);
		case "blocNomComplet":
			return PaiementScolaire.staticSetBlocNomComplet(requeteSite_, o);
		case "blocHeureDebut":
			return PaiementScolaire.staticSetBlocHeureDebut(requeteSite_, o);
		case "blocHeureFin":
			return PaiementScolaire.staticSetBlocHeureFin(requeteSite_, o);
		case "blocPrixParMois":
			return PaiementScolaire.staticSetBlocPrixParMois(requeteSite_, o);
		case "inscriptionNomGroupe":
			return PaiementScolaire.staticSetInscriptionNomGroupe(requeteSite_, o);
		case "blocPrixTotal":
			return PaiementScolaire.staticSetBlocPrixTotal(requeteSite_, o);
		case "inscriptionPaimentChaqueMois":
			return PaiementScolaire.staticSetInscriptionPaimentChaqueMois(requeteSite_, o);
		case "paiementDescription":
			return PaiementScolaire.staticSetPaiementDescription(requeteSite_, o);
		case "paiementDate":
			return PaiementScolaire.staticSetPaiementDate(requeteSite_, o);
		case "fraisRetardDate":
			return PaiementScolaire.staticSetFraisRetardDate(requeteSite_, o);
		case "paiementAnnee":
			return PaiementScolaire.staticSetPaiementAnnee(requeteSite_, o);
		case "paiementMontant":
			return PaiementScolaire.staticSetPaiementMontant(requeteSite_, o);
		case "paiementEspeces":
			return PaiementScolaire.staticSetPaiementEspeces(requeteSite_, o);
		case "paiementCheque":
			return PaiementScolaire.staticSetPaiementCheque(requeteSite_, o);
		case "paiementECheck":
			return PaiementScolaire.staticSetPaiementECheck(requeteSite_, o);
		case "paiementSysteme":
			return PaiementScolaire.staticSetPaiementSysteme(requeteSite_, o);
		case "paiementType":
			return PaiementScolaire.staticSetPaiementType(requeteSite_, o);
		case "paiementPar":
			return PaiementScolaire.staticSetPaiementPar(requeteSite_, o);
		case "transactionId":
			return PaiementScolaire.staticSetTransactionId(requeteSite_, o);
		case "customerProfileId":
			return PaiementScolaire.staticSetCustomerProfileId(requeteSite_, o);
		case "transactionStatus":
			return PaiementScolaire.staticSetTransactionStatus(requeteSite_, o);
		case "paiementRecu":
			return PaiementScolaire.staticSetPaiementRecu(requeteSite_, o);
		case "fraisMontant":
			return PaiementScolaire.staticSetFraisMontant(requeteSite_, o);
		case "fraisPremierDernier":
			return PaiementScolaire.staticSetFraisPremierDernier(requeteSite_, o);
		case "fraisInscription":
			return PaiementScolaire.staticSetFraisInscription(requeteSite_, o);
		case "fraisMois":
			return PaiementScolaire.staticSetFraisMois(requeteSite_, o);
		case "fraisRetard":
			return PaiementScolaire.staticSetFraisRetard(requeteSite_, o);
		case "now":
			return PaiementScolaire.staticSetNow(requeteSite_, o);
		case "paiementJour":
			return PaiementScolaire.staticSetPaiementJour(requeteSite_, o);
		case "paiementProchain":
			return PaiementScolaire.staticSetPaiementProchain(requeteSite_, o);
		case "fraisMontantDu":
			return PaiementScolaire.staticSetFraisMontantDu(requeteSite_, o);
		case "fraisMontantPasse":
			return PaiementScolaire.staticSetFraisMontantPasse(requeteSite_, o);
		case "fraisMontantNonPasse":
			return PaiementScolaire.staticSetFraisMontantNonPasse(requeteSite_, o);
		case "fraisMontantFuture":
			return PaiementScolaire.staticSetFraisMontantFuture(requeteSite_, o);
		case "paiementNomCourt":
			return PaiementScolaire.staticSetPaiementNomCourt(requeteSite_, o);
		case "paiementNomComplet":
			return PaiementScolaire.staticSetPaiementNomComplet(requeteSite_, o);
		case "paiementNumero":
			return PaiementScolaire.staticSetPaiementNumero(requeteSite_, o);
			default:
				return Cluster.staticSetCluster(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrPaiementScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrPaiementScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "paiementCle":
			return PaiementScolaire.staticSolrPaiementCle(requeteSite_, (Long)o);
		case "inscriptionCle":
			return PaiementScolaire.staticSolrInscriptionCle(requeteSite_, (Long)o);
		case "ecoleNumero":
			return PaiementScolaire.staticSolrEcoleNumero(requeteSite_, (Integer)o);
		case "utilisateurCles":
			return PaiementScolaire.staticSolrUtilisateurCles(requeteSite_, (Long)o);
		case "ecoleCle":
			return PaiementScolaire.staticSolrEcoleCle(requeteSite_, (Long)o);
		case "ecoleAddresse":
			return PaiementScolaire.staticSolrEcoleAddresse(requeteSite_, (String)o);
		case "ecoleNumeroTelephone":
			return PaiementScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, (String)o);
		case "anneeCle":
			return PaiementScolaire.staticSolrAnneeCle(requeteSite_, (Long)o);
		case "sessionCle":
			return PaiementScolaire.staticSolrSessionCle(requeteSite_, (Long)o);
		case "ageCle":
			return PaiementScolaire.staticSolrAgeCle(requeteSite_, (Long)o);
		case "blocCle":
			return PaiementScolaire.staticSolrBlocCle(requeteSite_, (Long)o);
		case "enfantCle":
			return PaiementScolaire.staticSolrEnfantCle(requeteSite_, (Long)o);
		case "mereCles":
			return PaiementScolaire.staticSolrMereCles(requeteSite_, (Long)o);
		case "pereCles":
			return PaiementScolaire.staticSolrPereCles(requeteSite_, (Long)o);
		case "gardienCles":
			return PaiementScolaire.staticSolrGardienCles(requeteSite_, (Long)o);
		case "enfantNomCompletPrefere":
			return PaiementScolaire.staticSolrEnfantNomCompletPrefere(requeteSite_, (String)o);
		case "enfantDateNaissance":
			return PaiementScolaire.staticSolrEnfantDateNaissance(requeteSite_, (LocalDate)o);
		case "mereNomCompletPrefere":
			return PaiementScolaire.staticSolrMereNomCompletPrefere(requeteSite_, (String)o);
		case "pereNomCompletPrefere":
			return PaiementScolaire.staticSolrPereNomCompletPrefere(requeteSite_, (String)o);
		case "ecoleNom":
			return PaiementScolaire.staticSolrEcoleNom(requeteSite_, (String)o);
		case "ecoleNomComplet":
			return PaiementScolaire.staticSolrEcoleNomComplet(requeteSite_, (String)o);
		case "ecoleEmplacement":
			return PaiementScolaire.staticSolrEcoleEmplacement(requeteSite_, (String)o);
		case "anneeDebut":
			return PaiementScolaire.staticSolrAnneeDebut(requeteSite_, (Integer)o);
		case "anneeFin":
			return PaiementScolaire.staticSolrAnneeFin(requeteSite_, (Integer)o);
		case "saisonDateDebut":
			return PaiementScolaire.staticSolrSaisonDateDebut(requeteSite_, (LocalDate)o);
		case "anneeFraisInscription":
			return PaiementScolaire.staticSolrAnneeFraisInscription(requeteSite_, (BigDecimal)o);
		case "sessionDateDebut":
			return PaiementScolaire.staticSolrSessionDateDebut(requeteSite_, (LocalDate)o);
		case "sessionDateFin":
			return PaiementScolaire.staticSolrSessionDateFin(requeteSite_, (LocalDate)o);
		case "ageDebut":
			return PaiementScolaire.staticSolrAgeDebut(requeteSite_, (Integer)o);
		case "ageFin":
			return PaiementScolaire.staticSolrAgeFin(requeteSite_, (Integer)o);
		case "blocNomComplet":
			return PaiementScolaire.staticSolrBlocNomComplet(requeteSite_, (String)o);
		case "blocHeureDebut":
			return PaiementScolaire.staticSolrBlocHeureDebut(requeteSite_, (LocalTime)o);
		case "blocHeureFin":
			return PaiementScolaire.staticSolrBlocHeureFin(requeteSite_, (LocalTime)o);
		case "blocPrixParMois":
			return PaiementScolaire.staticSolrBlocPrixParMois(requeteSite_, (BigDecimal)o);
		case "inscriptionNomGroupe":
			return PaiementScolaire.staticSolrInscriptionNomGroupe(requeteSite_, (String)o);
		case "blocPrixTotal":
			return PaiementScolaire.staticSolrBlocPrixTotal(requeteSite_, (BigDecimal)o);
		case "inscriptionPaimentChaqueMois":
			return PaiementScolaire.staticSolrInscriptionPaimentChaqueMois(requeteSite_, (Boolean)o);
		case "paiementDescription":
			return PaiementScolaire.staticSolrPaiementDescription(requeteSite_, (String)o);
		case "paiementDate":
			return PaiementScolaire.staticSolrPaiementDate(requeteSite_, (LocalDate)o);
		case "fraisRetardDate":
			return PaiementScolaire.staticSolrFraisRetardDate(requeteSite_, (LocalDate)o);
		case "paiementAnnee":
			return PaiementScolaire.staticSolrPaiementAnnee(requeteSite_, (Integer)o);
		case "paiementMontant":
			return PaiementScolaire.staticSolrPaiementMontant(requeteSite_, (BigDecimal)o);
		case "paiementEspeces":
			return PaiementScolaire.staticSolrPaiementEspeces(requeteSite_, (Boolean)o);
		case "paiementCheque":
			return PaiementScolaire.staticSolrPaiementCheque(requeteSite_, (Boolean)o);
		case "paiementECheck":
			return PaiementScolaire.staticSolrPaiementECheck(requeteSite_, (Boolean)o);
		case "paiementSysteme":
			return PaiementScolaire.staticSolrPaiementSysteme(requeteSite_, (Boolean)o);
		case "paiementType":
			return PaiementScolaire.staticSolrPaiementType(requeteSite_, (String)o);
		case "paiementPar":
			return PaiementScolaire.staticSolrPaiementPar(requeteSite_, (String)o);
		case "transactionId":
			return PaiementScolaire.staticSolrTransactionId(requeteSite_, (String)o);
		case "customerProfileId":
			return PaiementScolaire.staticSolrCustomerProfileId(requeteSite_, (String)o);
		case "transactionStatus":
			return PaiementScolaire.staticSolrTransactionStatus(requeteSite_, (String)o);
		case "paiementRecu":
			return PaiementScolaire.staticSolrPaiementRecu(requeteSite_, (Boolean)o);
		case "fraisMontant":
			return PaiementScolaire.staticSolrFraisMontant(requeteSite_, (BigDecimal)o);
		case "fraisPremierDernier":
			return PaiementScolaire.staticSolrFraisPremierDernier(requeteSite_, (Boolean)o);
		case "fraisInscription":
			return PaiementScolaire.staticSolrFraisInscription(requeteSite_, (Boolean)o);
		case "fraisMois":
			return PaiementScolaire.staticSolrFraisMois(requeteSite_, (Boolean)o);
		case "fraisRetard":
			return PaiementScolaire.staticSolrFraisRetard(requeteSite_, (Boolean)o);
		case "now":
			return PaiementScolaire.staticSolrNow(requeteSite_, (LocalDate)o);
		case "paiementJour":
			return PaiementScolaire.staticSolrPaiementJour(requeteSite_, (Integer)o);
		case "paiementProchain":
			return PaiementScolaire.staticSolrPaiementProchain(requeteSite_, (LocalDate)o);
		case "fraisMontantDu":
			return PaiementScolaire.staticSolrFraisMontantDu(requeteSite_, (BigDecimal)o);
		case "fraisMontantPasse":
			return PaiementScolaire.staticSolrFraisMontantPasse(requeteSite_, (BigDecimal)o);
		case "fraisMontantNonPasse":
			return PaiementScolaire.staticSolrFraisMontantNonPasse(requeteSite_, (BigDecimal)o);
		case "fraisMontantFuture":
			return PaiementScolaire.staticSolrFraisMontantFuture(requeteSite_, (BigDecimal)o);
		case "paiementNomCourt":
			return PaiementScolaire.staticSolrPaiementNomCourt(requeteSite_, (String)o);
		case "paiementNomComplet":
			return PaiementScolaire.staticSolrPaiementNomComplet(requeteSite_, (String)o);
		case "paiementNumero":
			return PaiementScolaire.staticSolrPaiementNumero(requeteSite_, (Integer)o);
			default:
				return Cluster.staticSolrCluster(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrPaiementScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrPaiementScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "paiementCle":
			return PaiementScolaire.staticSolrStrPaiementCle(requeteSite_, (Long)o);
		case "inscriptionCle":
			return PaiementScolaire.staticSolrStrInscriptionCle(requeteSite_, (Long)o);
		case "ecoleNumero":
			return PaiementScolaire.staticSolrStrEcoleNumero(requeteSite_, (Integer)o);
		case "utilisateurCles":
			return PaiementScolaire.staticSolrStrUtilisateurCles(requeteSite_, (Long)o);
		case "ecoleCle":
			return PaiementScolaire.staticSolrStrEcoleCle(requeteSite_, (Long)o);
		case "ecoleAddresse":
			return PaiementScolaire.staticSolrStrEcoleAddresse(requeteSite_, (String)o);
		case "ecoleNumeroTelephone":
			return PaiementScolaire.staticSolrStrEcoleNumeroTelephone(requeteSite_, (String)o);
		case "anneeCle":
			return PaiementScolaire.staticSolrStrAnneeCle(requeteSite_, (Long)o);
		case "sessionCle":
			return PaiementScolaire.staticSolrStrSessionCle(requeteSite_, (Long)o);
		case "ageCle":
			return PaiementScolaire.staticSolrStrAgeCle(requeteSite_, (Long)o);
		case "blocCle":
			return PaiementScolaire.staticSolrStrBlocCle(requeteSite_, (Long)o);
		case "enfantCle":
			return PaiementScolaire.staticSolrStrEnfantCle(requeteSite_, (Long)o);
		case "mereCles":
			return PaiementScolaire.staticSolrStrMereCles(requeteSite_, (Long)o);
		case "pereCles":
			return PaiementScolaire.staticSolrStrPereCles(requeteSite_, (Long)o);
		case "gardienCles":
			return PaiementScolaire.staticSolrStrGardienCles(requeteSite_, (Long)o);
		case "enfantNomCompletPrefere":
			return PaiementScolaire.staticSolrStrEnfantNomCompletPrefere(requeteSite_, (String)o);
		case "enfantDateNaissance":
			return PaiementScolaire.staticSolrStrEnfantDateNaissance(requeteSite_, (Date)o);
		case "mereNomCompletPrefere":
			return PaiementScolaire.staticSolrStrMereNomCompletPrefere(requeteSite_, (String)o);
		case "pereNomCompletPrefere":
			return PaiementScolaire.staticSolrStrPereNomCompletPrefere(requeteSite_, (String)o);
		case "ecoleNom":
			return PaiementScolaire.staticSolrStrEcoleNom(requeteSite_, (String)o);
		case "ecoleNomComplet":
			return PaiementScolaire.staticSolrStrEcoleNomComplet(requeteSite_, (String)o);
		case "ecoleEmplacement":
			return PaiementScolaire.staticSolrStrEcoleEmplacement(requeteSite_, (String)o);
		case "anneeDebut":
			return PaiementScolaire.staticSolrStrAnneeDebut(requeteSite_, (Integer)o);
		case "anneeFin":
			return PaiementScolaire.staticSolrStrAnneeFin(requeteSite_, (Integer)o);
		case "saisonDateDebut":
			return PaiementScolaire.staticSolrStrSaisonDateDebut(requeteSite_, (Date)o);
		case "anneeFraisInscription":
			return PaiementScolaire.staticSolrStrAnneeFraisInscription(requeteSite_, (Double)o);
		case "sessionDateDebut":
			return PaiementScolaire.staticSolrStrSessionDateDebut(requeteSite_, (Date)o);
		case "sessionDateFin":
			return PaiementScolaire.staticSolrStrSessionDateFin(requeteSite_, (Date)o);
		case "ageDebut":
			return PaiementScolaire.staticSolrStrAgeDebut(requeteSite_, (Integer)o);
		case "ageFin":
			return PaiementScolaire.staticSolrStrAgeFin(requeteSite_, (Integer)o);
		case "blocNomComplet":
			return PaiementScolaire.staticSolrStrBlocNomComplet(requeteSite_, (String)o);
		case "blocHeureDebut":
			return PaiementScolaire.staticSolrStrBlocHeureDebut(requeteSite_, (String)o);
		case "blocHeureFin":
			return PaiementScolaire.staticSolrStrBlocHeureFin(requeteSite_, (String)o);
		case "blocPrixParMois":
			return PaiementScolaire.staticSolrStrBlocPrixParMois(requeteSite_, (Double)o);
		case "inscriptionNomGroupe":
			return PaiementScolaire.staticSolrStrInscriptionNomGroupe(requeteSite_, (String)o);
		case "blocPrixTotal":
			return PaiementScolaire.staticSolrStrBlocPrixTotal(requeteSite_, (Double)o);
		case "inscriptionPaimentChaqueMois":
			return PaiementScolaire.staticSolrStrInscriptionPaimentChaqueMois(requeteSite_, (Boolean)o);
		case "paiementDescription":
			return PaiementScolaire.staticSolrStrPaiementDescription(requeteSite_, (String)o);
		case "paiementDate":
			return PaiementScolaire.staticSolrStrPaiementDate(requeteSite_, (Date)o);
		case "fraisRetardDate":
			return PaiementScolaire.staticSolrStrFraisRetardDate(requeteSite_, (Date)o);
		case "paiementAnnee":
			return PaiementScolaire.staticSolrStrPaiementAnnee(requeteSite_, (Integer)o);
		case "paiementMontant":
			return PaiementScolaire.staticSolrStrPaiementMontant(requeteSite_, (Double)o);
		case "paiementEspeces":
			return PaiementScolaire.staticSolrStrPaiementEspeces(requeteSite_, (Boolean)o);
		case "paiementCheque":
			return PaiementScolaire.staticSolrStrPaiementCheque(requeteSite_, (Boolean)o);
		case "paiementECheck":
			return PaiementScolaire.staticSolrStrPaiementECheck(requeteSite_, (Boolean)o);
		case "paiementSysteme":
			return PaiementScolaire.staticSolrStrPaiementSysteme(requeteSite_, (Boolean)o);
		case "paiementType":
			return PaiementScolaire.staticSolrStrPaiementType(requeteSite_, (String)o);
		case "paiementPar":
			return PaiementScolaire.staticSolrStrPaiementPar(requeteSite_, (String)o);
		case "transactionId":
			return PaiementScolaire.staticSolrStrTransactionId(requeteSite_, (String)o);
		case "customerProfileId":
			return PaiementScolaire.staticSolrStrCustomerProfileId(requeteSite_, (String)o);
		case "transactionStatus":
			return PaiementScolaire.staticSolrStrTransactionStatus(requeteSite_, (String)o);
		case "paiementRecu":
			return PaiementScolaire.staticSolrStrPaiementRecu(requeteSite_, (Boolean)o);
		case "fraisMontant":
			return PaiementScolaire.staticSolrStrFraisMontant(requeteSite_, (Double)o);
		case "fraisPremierDernier":
			return PaiementScolaire.staticSolrStrFraisPremierDernier(requeteSite_, (Boolean)o);
		case "fraisInscription":
			return PaiementScolaire.staticSolrStrFraisInscription(requeteSite_, (Boolean)o);
		case "fraisMois":
			return PaiementScolaire.staticSolrStrFraisMois(requeteSite_, (Boolean)o);
		case "fraisRetard":
			return PaiementScolaire.staticSolrStrFraisRetard(requeteSite_, (Boolean)o);
		case "now":
			return PaiementScolaire.staticSolrStrNow(requeteSite_, (Date)o);
		case "paiementJour":
			return PaiementScolaire.staticSolrStrPaiementJour(requeteSite_, (Integer)o);
		case "paiementProchain":
			return PaiementScolaire.staticSolrStrPaiementProchain(requeteSite_, (Date)o);
		case "fraisMontantDu":
			return PaiementScolaire.staticSolrStrFraisMontantDu(requeteSite_, (Double)o);
		case "fraisMontantPasse":
			return PaiementScolaire.staticSolrStrFraisMontantPasse(requeteSite_, (Double)o);
		case "fraisMontantNonPasse":
			return PaiementScolaire.staticSolrStrFraisMontantNonPasse(requeteSite_, (Double)o);
		case "fraisMontantFuture":
			return PaiementScolaire.staticSolrStrFraisMontantFuture(requeteSite_, (Double)o);
		case "paiementNomCourt":
			return PaiementScolaire.staticSolrStrPaiementNomCourt(requeteSite_, (String)o);
		case "paiementNomComplet":
			return PaiementScolaire.staticSolrStrPaiementNomComplet(requeteSite_, (String)o);
		case "paiementNumero":
			return PaiementScolaire.staticSolrStrPaiementNumero(requeteSite_, (Integer)o);
			default:
				return Cluster.staticSolrStrCluster(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqPaiementScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqPaiementScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "paiementCle":
			return PaiementScolaire.staticSolrFqPaiementCle(requeteSite_, o);
		case "inscriptionCle":
			return PaiementScolaire.staticSolrFqInscriptionCle(requeteSite_, o);
		case "ecoleNumero":
			return PaiementScolaire.staticSolrFqEcoleNumero(requeteSite_, o);
		case "utilisateurCles":
			return PaiementScolaire.staticSolrFqUtilisateurCles(requeteSite_, o);
		case "ecoleCle":
			return PaiementScolaire.staticSolrFqEcoleCle(requeteSite_, o);
		case "ecoleAddresse":
			return PaiementScolaire.staticSolrFqEcoleAddresse(requeteSite_, o);
		case "ecoleNumeroTelephone":
			return PaiementScolaire.staticSolrFqEcoleNumeroTelephone(requeteSite_, o);
		case "anneeCle":
			return PaiementScolaire.staticSolrFqAnneeCle(requeteSite_, o);
		case "sessionCle":
			return PaiementScolaire.staticSolrFqSessionCle(requeteSite_, o);
		case "ageCle":
			return PaiementScolaire.staticSolrFqAgeCle(requeteSite_, o);
		case "blocCle":
			return PaiementScolaire.staticSolrFqBlocCle(requeteSite_, o);
		case "enfantCle":
			return PaiementScolaire.staticSolrFqEnfantCle(requeteSite_, o);
		case "mereCles":
			return PaiementScolaire.staticSolrFqMereCles(requeteSite_, o);
		case "pereCles":
			return PaiementScolaire.staticSolrFqPereCles(requeteSite_, o);
		case "gardienCles":
			return PaiementScolaire.staticSolrFqGardienCles(requeteSite_, o);
		case "enfantNomCompletPrefere":
			return PaiementScolaire.staticSolrFqEnfantNomCompletPrefere(requeteSite_, o);
		case "enfantDateNaissance":
			return PaiementScolaire.staticSolrFqEnfantDateNaissance(requeteSite_, o);
		case "mereNomCompletPrefere":
			return PaiementScolaire.staticSolrFqMereNomCompletPrefere(requeteSite_, o);
		case "pereNomCompletPrefere":
			return PaiementScolaire.staticSolrFqPereNomCompletPrefere(requeteSite_, o);
		case "ecoleNom":
			return PaiementScolaire.staticSolrFqEcoleNom(requeteSite_, o);
		case "ecoleNomComplet":
			return PaiementScolaire.staticSolrFqEcoleNomComplet(requeteSite_, o);
		case "ecoleEmplacement":
			return PaiementScolaire.staticSolrFqEcoleEmplacement(requeteSite_, o);
		case "anneeDebut":
			return PaiementScolaire.staticSolrFqAnneeDebut(requeteSite_, o);
		case "anneeFin":
			return PaiementScolaire.staticSolrFqAnneeFin(requeteSite_, o);
		case "saisonDateDebut":
			return PaiementScolaire.staticSolrFqSaisonDateDebut(requeteSite_, o);
		case "anneeFraisInscription":
			return PaiementScolaire.staticSolrFqAnneeFraisInscription(requeteSite_, o);
		case "sessionDateDebut":
			return PaiementScolaire.staticSolrFqSessionDateDebut(requeteSite_, o);
		case "sessionDateFin":
			return PaiementScolaire.staticSolrFqSessionDateFin(requeteSite_, o);
		case "ageDebut":
			return PaiementScolaire.staticSolrFqAgeDebut(requeteSite_, o);
		case "ageFin":
			return PaiementScolaire.staticSolrFqAgeFin(requeteSite_, o);
		case "blocNomComplet":
			return PaiementScolaire.staticSolrFqBlocNomComplet(requeteSite_, o);
		case "blocHeureDebut":
			return PaiementScolaire.staticSolrFqBlocHeureDebut(requeteSite_, o);
		case "blocHeureFin":
			return PaiementScolaire.staticSolrFqBlocHeureFin(requeteSite_, o);
		case "blocPrixParMois":
			return PaiementScolaire.staticSolrFqBlocPrixParMois(requeteSite_, o);
		case "inscriptionNomGroupe":
			return PaiementScolaire.staticSolrFqInscriptionNomGroupe(requeteSite_, o);
		case "blocPrixTotal":
			return PaiementScolaire.staticSolrFqBlocPrixTotal(requeteSite_, o);
		case "inscriptionPaimentChaqueMois":
			return PaiementScolaire.staticSolrFqInscriptionPaimentChaqueMois(requeteSite_, o);
		case "paiementDescription":
			return PaiementScolaire.staticSolrFqPaiementDescription(requeteSite_, o);
		case "paiementDate":
			return PaiementScolaire.staticSolrFqPaiementDate(requeteSite_, o);
		case "fraisRetardDate":
			return PaiementScolaire.staticSolrFqFraisRetardDate(requeteSite_, o);
		case "paiementAnnee":
			return PaiementScolaire.staticSolrFqPaiementAnnee(requeteSite_, o);
		case "paiementMontant":
			return PaiementScolaire.staticSolrFqPaiementMontant(requeteSite_, o);
		case "paiementEspeces":
			return PaiementScolaire.staticSolrFqPaiementEspeces(requeteSite_, o);
		case "paiementCheque":
			return PaiementScolaire.staticSolrFqPaiementCheque(requeteSite_, o);
		case "paiementECheck":
			return PaiementScolaire.staticSolrFqPaiementECheck(requeteSite_, o);
		case "paiementSysteme":
			return PaiementScolaire.staticSolrFqPaiementSysteme(requeteSite_, o);
		case "paiementType":
			return PaiementScolaire.staticSolrFqPaiementType(requeteSite_, o);
		case "paiementPar":
			return PaiementScolaire.staticSolrFqPaiementPar(requeteSite_, o);
		case "transactionId":
			return PaiementScolaire.staticSolrFqTransactionId(requeteSite_, o);
		case "customerProfileId":
			return PaiementScolaire.staticSolrFqCustomerProfileId(requeteSite_, o);
		case "transactionStatus":
			return PaiementScolaire.staticSolrFqTransactionStatus(requeteSite_, o);
		case "paiementRecu":
			return PaiementScolaire.staticSolrFqPaiementRecu(requeteSite_, o);
		case "fraisMontant":
			return PaiementScolaire.staticSolrFqFraisMontant(requeteSite_, o);
		case "fraisPremierDernier":
			return PaiementScolaire.staticSolrFqFraisPremierDernier(requeteSite_, o);
		case "fraisInscription":
			return PaiementScolaire.staticSolrFqFraisInscription(requeteSite_, o);
		case "fraisMois":
			return PaiementScolaire.staticSolrFqFraisMois(requeteSite_, o);
		case "fraisRetard":
			return PaiementScolaire.staticSolrFqFraisRetard(requeteSite_, o);
		case "now":
			return PaiementScolaire.staticSolrFqNow(requeteSite_, o);
		case "paiementJour":
			return PaiementScolaire.staticSolrFqPaiementJour(requeteSite_, o);
		case "paiementProchain":
			return PaiementScolaire.staticSolrFqPaiementProchain(requeteSite_, o);
		case "fraisMontantDu":
			return PaiementScolaire.staticSolrFqFraisMontantDu(requeteSite_, o);
		case "fraisMontantPasse":
			return PaiementScolaire.staticSolrFqFraisMontantPasse(requeteSite_, o);
		case "fraisMontantNonPasse":
			return PaiementScolaire.staticSolrFqFraisMontantNonPasse(requeteSite_, o);
		case "fraisMontantFuture":
			return PaiementScolaire.staticSolrFqFraisMontantFuture(requeteSite_, o);
		case "paiementNomCourt":
			return PaiementScolaire.staticSolrFqPaiementNomCourt(requeteSite_, o);
		case "paiementNomComplet":
			return PaiementScolaire.staticSolrFqPaiementNomComplet(requeteSite_, o);
		case "paiementNumero":
			return PaiementScolaire.staticSolrFqPaiementNumero(requeteSite_, o);
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
					o = definirPaiementScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPaiementScolaire(String var, String val) {
		switch(var) {
			case "inscriptionPaimentChaqueMois":
				if(val != null)
					setInscriptionPaimentChaqueMois(val);
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
			case "fraisRetardDate":
				if(val != null)
					setFraisRetardDate(val);
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
			case "paiementECheck":
				if(val != null)
					setPaiementECheck(val);
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

			if(sauvegardes.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oPaiementScolaire.setEcoleAddresse(ecoleAddresse);
			}

			if(sauvegardes.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oPaiementScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardes.contains("anneeCle")) {
				Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
				if(anneeCle != null)
					oPaiementScolaire.setAnneeCle(anneeCle);
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

			if(sauvegardes.contains("blocNomComplet")) {
				String blocNomComplet = (String)solrDocument.get("blocNomComplet_stored_string");
				if(blocNomComplet != null)
					oPaiementScolaire.setBlocNomComplet(blocNomComplet);
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

			if(sauvegardes.contains("inscriptionNomGroupe")) {
				String inscriptionNomGroupe = (String)solrDocument.get("inscriptionNomGroupe_stored_string");
				if(inscriptionNomGroupe != null)
					oPaiementScolaire.setInscriptionNomGroupe(inscriptionNomGroupe);
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

			if(sauvegardes.contains("fraisRetardDate")) {
				Date fraisRetardDate = (Date)solrDocument.get("fraisRetardDate_stored_date");
				if(fraisRetardDate != null)
					oPaiementScolaire.setFraisRetardDate(fraisRetardDate);
			}

			if(sauvegardes.contains("paiementAnnee")) {
				Integer paiementAnnee = (Integer)solrDocument.get("paiementAnnee_stored_int");
				if(paiementAnnee != null)
					oPaiementScolaire.setPaiementAnnee(paiementAnnee);
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

			if(sauvegardes.contains("paiementECheck")) {
				Boolean paiementECheck = (Boolean)solrDocument.get("paiementECheck_stored_boolean");
				if(paiementECheck != null)
					oPaiementScolaire.setPaiementECheck(paiementECheck);
			}

			if(sauvegardes.contains("paiementSysteme")) {
				Boolean paiementSysteme = (Boolean)solrDocument.get("paiementSysteme_stored_boolean");
				if(paiementSysteme != null)
					oPaiementScolaire.setPaiementSysteme(paiementSysteme);
			}

			if(sauvegardes.contains("paiementType")) {
				String paiementType = (String)solrDocument.get("paiementType_stored_string");
				if(paiementType != null)
					oPaiementScolaire.setPaiementType(paiementType);
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

			if(sauvegardes.contains("paiementProchain")) {
				Date paiementProchain = (Date)solrDocument.get("paiementProchain_stored_date");
				if(paiementProchain != null)
					oPaiementScolaire.setPaiementProchain(paiementProchain);
			}

			if(sauvegardes.contains("fraisMontantDu")) {
				Double fraisMontantDu = (Double)solrDocument.get("fraisMontantDu_stored_double");
				if(fraisMontantDu != null)
					oPaiementScolaire.setFraisMontantDu(fraisMontantDu);
			}

			if(sauvegardes.contains("fraisMontantPasse")) {
				Double fraisMontantPasse = (Double)solrDocument.get("fraisMontantPasse_stored_double");
				if(fraisMontantPasse != null)
					oPaiementScolaire.setFraisMontantPasse(fraisMontantPasse);
			}

			if(sauvegardes.contains("fraisMontantNonPasse")) {
				Double fraisMontantNonPasse = (Double)solrDocument.get("fraisMontantNonPasse_stored_double");
				if(fraisMontantNonPasse != null)
					oPaiementScolaire.setFraisMontantNonPasse(fraisMontantNonPasse);
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
		if(ecoleAddresse != null) {
			document.addField("ecoleAddresse_indexed_string", ecoleAddresse);
			document.addField("ecoleAddresse_stored_string", ecoleAddresse);
		}
		if(ecoleNumeroTelephone != null) {
			document.addField("ecoleNumeroTelephone_indexed_string", ecoleNumeroTelephone);
			document.addField("ecoleNumeroTelephone_stored_string", ecoleNumeroTelephone);
		}
		if(anneeCle != null) {
			document.addField("anneeCle_indexed_long", anneeCle);
			document.addField("anneeCle_stored_long", anneeCle);
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
		if(blocNomComplet != null) {
			document.addField("blocNomComplet_stored_string", blocNomComplet);
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
		if(inscriptionNomGroupe != null) {
			document.addField("inscriptionNomGroupe_indexed_string", inscriptionNomGroupe);
			document.addField("inscriptionNomGroupe_stored_string", inscriptionNomGroupe);
		}
		if(blocPrixTotal != null) {
			document.addField("blocPrixTotal_indexed_double", blocPrixTotal.doubleValue());
			document.addField("blocPrixTotal_stored_double", blocPrixTotal.doubleValue());
		}
		if(inscriptionPaimentChaqueMois != null) {
			document.addField("inscriptionPaimentChaqueMois_indexed_boolean", inscriptionPaimentChaqueMois);
			document.addField("inscriptionPaimentChaqueMois_stored_boolean", inscriptionPaimentChaqueMois);
		}
		if(paiementDescription != null) {
			document.addField("paiementDescription_indexed_string", paiementDescription);
			document.addField("paiementDescription_stored_string", paiementDescription);
		}
		if(paiementDate != null) {
			document.addField("paiementDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementDate.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("paiementDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementDate.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(fraisRetardDate != null) {
			document.addField("fraisRetardDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(fraisRetardDate.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("fraisRetardDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(fraisRetardDate.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(paiementAnnee != null) {
			document.addField("paiementAnnee_indexed_int", paiementAnnee);
			document.addField("paiementAnnee_stored_int", paiementAnnee);
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
		if(paiementECheck != null) {
			document.addField("paiementECheck_indexed_boolean", paiementECheck);
			document.addField("paiementECheck_stored_boolean", paiementECheck);
		}
		if(paiementSysteme != null) {
			document.addField("paiementSysteme_indexed_boolean", paiementSysteme);
			document.addField("paiementSysteme_stored_boolean", paiementSysteme);
		}
		if(paiementType != null) {
			document.addField("paiementType_indexed_string", paiementType);
			document.addField("paiementType_stored_string", paiementType);
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
		if(paiementProchain != null) {
			document.addField("paiementProchain_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementProchain.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("paiementProchain_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementProchain.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(fraisMontantDu != null) {
			document.addField("fraisMontantDu_indexed_double", fraisMontantDu.doubleValue());
			document.addField("fraisMontantDu_stored_double", fraisMontantDu.doubleValue());
		}
		if(fraisMontantPasse != null) {
			document.addField("fraisMontantPasse_indexed_double", fraisMontantPasse.doubleValue());
			document.addField("fraisMontantPasse_stored_double", fraisMontantPasse.doubleValue());
		}
		if(fraisMontantNonPasse != null) {
			document.addField("fraisMontantNonPasse_indexed_double", fraisMontantNonPasse.doubleValue());
			document.addField("fraisMontantNonPasse_stored_double", fraisMontantNonPasse.doubleValue());
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
			case "ecoleAddresse":
				return "ecoleAddresse_indexed_string";
			case "ecoleNumeroTelephone":
				return "ecoleNumeroTelephone_indexed_string";
			case "anneeCle":
				return "anneeCle_indexed_long";
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
			case "inscriptionNomGroupe":
				return "inscriptionNomGroupe_indexed_string";
			case "blocPrixTotal":
				return "blocPrixTotal_indexed_double";
			case "inscriptionPaimentChaqueMois":
				return "inscriptionPaimentChaqueMois_indexed_boolean";
			case "paiementDescription":
				return "paiementDescription_indexed_string";
			case "paiementDate":
				return "paiementDate_indexed_date";
			case "fraisRetardDate":
				return "fraisRetardDate_indexed_date";
			case "paiementAnnee":
				return "paiementAnnee_indexed_int";
			case "paiementMontant":
				return "paiementMontant_indexed_double";
			case "paiementEspeces":
				return "paiementEspeces_indexed_boolean";
			case "paiementCheque":
				return "paiementCheque_indexed_boolean";
			case "paiementECheck":
				return "paiementECheck_indexed_boolean";
			case "paiementSysteme":
				return "paiementSysteme_indexed_boolean";
			case "paiementType":
				return "paiementType_indexed_string";
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
			case "paiementProchain":
				return "paiementProchain_indexed_date";
			case "fraisMontantDu":
				return "fraisMontantDu_indexed_double";
			case "fraisMontantPasse":
				return "fraisMontantPasse_indexed_double";
			case "fraisMontantNonPasse":
				return "fraisMontantNonPasse_indexed_double";
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

		String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
		if(ecoleAddresse != null)
			oPaiementScolaire.setEcoleAddresse(ecoleAddresse);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oPaiementScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
		if(anneeCle != null)
			oPaiementScolaire.setAnneeCle(anneeCle);

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

		String blocNomComplet = (String)solrDocument.get("blocNomComplet_stored_string");
		if(blocNomComplet != null)
			oPaiementScolaire.setBlocNomComplet(blocNomComplet);

		String blocHeureDebut = (String)solrDocument.get("blocHeureDebut_stored_string");
		if(blocHeureDebut != null)
			oPaiementScolaire.setBlocHeureDebut(blocHeureDebut);

		String blocHeureFin = (String)solrDocument.get("blocHeureFin_stored_string");
		if(blocHeureFin != null)
			oPaiementScolaire.setBlocHeureFin(blocHeureFin);

		Double blocPrixParMois = (Double)solrDocument.get("blocPrixParMois_stored_double");
		if(blocPrixParMois != null)
			oPaiementScolaire.setBlocPrixParMois(blocPrixParMois);

		String inscriptionNomGroupe = (String)solrDocument.get("inscriptionNomGroupe_stored_string");
		if(inscriptionNomGroupe != null)
			oPaiementScolaire.setInscriptionNomGroupe(inscriptionNomGroupe);

		Double blocPrixTotal = (Double)solrDocument.get("blocPrixTotal_stored_double");
		if(blocPrixTotal != null)
			oPaiementScolaire.setBlocPrixTotal(blocPrixTotal);

		Boolean inscriptionPaimentChaqueMois = (Boolean)solrDocument.get("inscriptionPaimentChaqueMois_stored_boolean");
		if(inscriptionPaimentChaqueMois != null)
			oPaiementScolaire.setInscriptionPaimentChaqueMois(inscriptionPaimentChaqueMois);

		String paiementDescription = (String)solrDocument.get("paiementDescription_stored_string");
		if(paiementDescription != null)
			oPaiementScolaire.setPaiementDescription(paiementDescription);

		Date paiementDate = (Date)solrDocument.get("paiementDate_stored_date");
		if(paiementDate != null)
			oPaiementScolaire.setPaiementDate(paiementDate);

		Date fraisRetardDate = (Date)solrDocument.get("fraisRetardDate_stored_date");
		if(fraisRetardDate != null)
			oPaiementScolaire.setFraisRetardDate(fraisRetardDate);

		Integer paiementAnnee = (Integer)solrDocument.get("paiementAnnee_stored_int");
		if(paiementAnnee != null)
			oPaiementScolaire.setPaiementAnnee(paiementAnnee);

		Double paiementMontant = (Double)solrDocument.get("paiementMontant_stored_double");
		if(paiementMontant != null)
			oPaiementScolaire.setPaiementMontant(paiementMontant);

		Boolean paiementEspeces = (Boolean)solrDocument.get("paiementEspeces_stored_boolean");
		if(paiementEspeces != null)
			oPaiementScolaire.setPaiementEspeces(paiementEspeces);

		Boolean paiementCheque = (Boolean)solrDocument.get("paiementCheque_stored_boolean");
		if(paiementCheque != null)
			oPaiementScolaire.setPaiementCheque(paiementCheque);

		Boolean paiementECheck = (Boolean)solrDocument.get("paiementECheck_stored_boolean");
		if(paiementECheck != null)
			oPaiementScolaire.setPaiementECheck(paiementECheck);

		Boolean paiementSysteme = (Boolean)solrDocument.get("paiementSysteme_stored_boolean");
		if(paiementSysteme != null)
			oPaiementScolaire.setPaiementSysteme(paiementSysteme);

		String paiementType = (String)solrDocument.get("paiementType_stored_string");
		if(paiementType != null)
			oPaiementScolaire.setPaiementType(paiementType);

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

		Date paiementProchain = (Date)solrDocument.get("paiementProchain_stored_date");
		if(paiementProchain != null)
			oPaiementScolaire.setPaiementProchain(paiementProchain);

		Double fraisMontantDu = (Double)solrDocument.get("fraisMontantDu_stored_double");
		if(fraisMontantDu != null)
			oPaiementScolaire.setFraisMontantDu(fraisMontantDu);

		Double fraisMontantPasse = (Double)solrDocument.get("fraisMontantPasse_stored_double");
		if(fraisMontantPasse != null)
			oPaiementScolaire.setFraisMontantPasse(fraisMontantPasse);

		Double fraisMontantNonPasse = (Double)solrDocument.get("fraisMontantNonPasse_stored_double");
		if(fraisMontantNonPasse != null)
			oPaiementScolaire.setFraisMontantNonPasse(fraisMontantNonPasse);

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
			if(!Objects.equals(paiementCle, original.getPaiementCle()))
				requeteApi.addVars("paiementCle");
			if(!Objects.equals(inscriptionCle, original.getInscriptionCle()))
				requeteApi.addVars("inscriptionCle");
			if(!Objects.equals(ecoleNumero, original.getEcoleNumero()))
				requeteApi.addVars("ecoleNumero");
			if(!Objects.equals(utilisateurCles, original.getUtilisateurCles()))
				requeteApi.addVars("utilisateurCles");
			if(!Objects.equals(ecoleCle, original.getEcoleCle()))
				requeteApi.addVars("ecoleCle");
			if(!Objects.equals(ecoleAddresse, original.getEcoleAddresse()))
				requeteApi.addVars("ecoleAddresse");
			if(!Objects.equals(ecoleNumeroTelephone, original.getEcoleNumeroTelephone()))
				requeteApi.addVars("ecoleNumeroTelephone");
			if(!Objects.equals(anneeCle, original.getAnneeCle()))
				requeteApi.addVars("anneeCle");
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
			if(!Objects.equals(enfantNomCompletPrefere, original.getEnfantNomCompletPrefere()))
				requeteApi.addVars("enfantNomCompletPrefere");
			if(!Objects.equals(enfantDateNaissance, original.getEnfantDateNaissance()))
				requeteApi.addVars("enfantDateNaissance");
			if(!Objects.equals(mereNomCompletPrefere, original.getMereNomCompletPrefere()))
				requeteApi.addVars("mereNomCompletPrefere");
			if(!Objects.equals(pereNomCompletPrefere, original.getPereNomCompletPrefere()))
				requeteApi.addVars("pereNomCompletPrefere");
			if(!Objects.equals(ecoleNom, original.getEcoleNom()))
				requeteApi.addVars("ecoleNom");
			if(!Objects.equals(ecoleNomComplet, original.getEcoleNomComplet()))
				requeteApi.addVars("ecoleNomComplet");
			if(!Objects.equals(ecoleEmplacement, original.getEcoleEmplacement()))
				requeteApi.addVars("ecoleEmplacement");
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
			if(!Objects.equals(ageDebut, original.getAgeDebut()))
				requeteApi.addVars("ageDebut");
			if(!Objects.equals(ageFin, original.getAgeFin()))
				requeteApi.addVars("ageFin");
			if(!Objects.equals(blocNomComplet, original.getBlocNomComplet()))
				requeteApi.addVars("blocNomComplet");
			if(!Objects.equals(blocHeureDebut, original.getBlocHeureDebut()))
				requeteApi.addVars("blocHeureDebut");
			if(!Objects.equals(blocHeureFin, original.getBlocHeureFin()))
				requeteApi.addVars("blocHeureFin");
			if(!Objects.equals(blocPrixParMois, original.getBlocPrixParMois()))
				requeteApi.addVars("blocPrixParMois");
			if(!Objects.equals(inscriptionNomGroupe, original.getInscriptionNomGroupe()))
				requeteApi.addVars("inscriptionNomGroupe");
			if(!Objects.equals(blocPrixTotal, original.getBlocPrixTotal()))
				requeteApi.addVars("blocPrixTotal");
			if(!Objects.equals(inscriptionPaimentChaqueMois, original.getInscriptionPaimentChaqueMois()))
				requeteApi.addVars("inscriptionPaimentChaqueMois");
			if(!Objects.equals(paiementDescription, original.getPaiementDescription()))
				requeteApi.addVars("paiementDescription");
			if(!Objects.equals(paiementDate, original.getPaiementDate()))
				requeteApi.addVars("paiementDate");
			if(!Objects.equals(fraisRetardDate, original.getFraisRetardDate()))
				requeteApi.addVars("fraisRetardDate");
			if(!Objects.equals(paiementAnnee, original.getPaiementAnnee()))
				requeteApi.addVars("paiementAnnee");
			if(!Objects.equals(paiementMontant, original.getPaiementMontant()))
				requeteApi.addVars("paiementMontant");
			if(!Objects.equals(paiementEspeces, original.getPaiementEspeces()))
				requeteApi.addVars("paiementEspeces");
			if(!Objects.equals(paiementCheque, original.getPaiementCheque()))
				requeteApi.addVars("paiementCheque");
			if(!Objects.equals(paiementECheck, original.getPaiementECheck()))
				requeteApi.addVars("paiementECheck");
			if(!Objects.equals(paiementSysteme, original.getPaiementSysteme()))
				requeteApi.addVars("paiementSysteme");
			if(!Objects.equals(paiementType, original.getPaiementType()))
				requeteApi.addVars("paiementType");
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
			if(!Objects.equals(paiementProchain, original.getPaiementProchain()))
				requeteApi.addVars("paiementProchain");
			if(!Objects.equals(fraisMontantDu, original.getFraisMontantDu()))
				requeteApi.addVars("fraisMontantDu");
			if(!Objects.equals(fraisMontantPasse, original.getFraisMontantPasse()))
				requeteApi.addVars("fraisMontantPasse");
			if(!Objects.equals(fraisMontantNonPasse, original.getFraisMontantNonPasse()))
				requeteApi.addVars("fraisMontantNonPasse");
			if(!Objects.equals(fraisMontantFuture, original.getFraisMontantFuture()))
				requeteApi.addVars("fraisMontantFuture");
			if(!Objects.equals(paiementNomCourt, original.getPaiementNomCourt()))
				requeteApi.addVars("paiementNomCourt");
			if(!Objects.equals(paiementNomComplet, original.getPaiementNomComplet()))
				requeteApi.addVars("paiementNomComplet");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), paiementCle, inscriptionCle, ecoleNumero, utilisateurCles, ecoleCle, ecoleAddresse, ecoleNumeroTelephone, anneeCle, sessionCle, ageCle, blocCle, enfantCle, mereCles, pereCles, gardienCles, enfantNomCompletPrefere, enfantDateNaissance, mereNomCompletPrefere, pereNomCompletPrefere, ecoleNom, ecoleNomComplet, ecoleEmplacement, anneeDebut, anneeFin, saisonDateDebut, anneeFraisInscription, sessionDateDebut, sessionDateFin, ageDebut, ageFin, blocNomComplet, blocHeureDebut, blocHeureFin, blocPrixParMois, inscriptionNomGroupe, blocPrixTotal, inscriptionPaimentChaqueMois, paiementDescription, paiementDate, fraisRetardDate, paiementAnnee, paiementMontant, paiementEspeces, paiementCheque, paiementECheck, paiementSysteme, paiementType, paiementPar, transactionId, customerProfileId, transactionStatus, paiementRecu, fraisMontant, fraisPremierDernier, fraisInscription, fraisMois, fraisRetard, paiementProchain, fraisMontantDu, fraisMontantPasse, fraisMontantNonPasse, fraisMontantFuture, paiementNomCourt, paiementNomComplet);
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
				&& Objects.equals( paiementCle, that.paiementCle )
				&& Objects.equals( inscriptionCle, that.inscriptionCle )
				&& Objects.equals( ecoleNumero, that.ecoleNumero )
				&& Objects.equals( utilisateurCles, that.utilisateurCles )
				&& Objects.equals( ecoleCle, that.ecoleCle )
				&& Objects.equals( ecoleAddresse, that.ecoleAddresse )
				&& Objects.equals( ecoleNumeroTelephone, that.ecoleNumeroTelephone )
				&& Objects.equals( anneeCle, that.anneeCle )
				&& Objects.equals( sessionCle, that.sessionCle )
				&& Objects.equals( ageCle, that.ageCle )
				&& Objects.equals( blocCle, that.blocCle )
				&& Objects.equals( enfantCle, that.enfantCle )
				&& Objects.equals( mereCles, that.mereCles )
				&& Objects.equals( pereCles, that.pereCles )
				&& Objects.equals( gardienCles, that.gardienCles )
				&& Objects.equals( enfantNomCompletPrefere, that.enfantNomCompletPrefere )
				&& Objects.equals( enfantDateNaissance, that.enfantDateNaissance )
				&& Objects.equals( mereNomCompletPrefere, that.mereNomCompletPrefere )
				&& Objects.equals( pereNomCompletPrefere, that.pereNomCompletPrefere )
				&& Objects.equals( ecoleNom, that.ecoleNom )
				&& Objects.equals( ecoleNomComplet, that.ecoleNomComplet )
				&& Objects.equals( ecoleEmplacement, that.ecoleEmplacement )
				&& Objects.equals( anneeDebut, that.anneeDebut )
				&& Objects.equals( anneeFin, that.anneeFin )
				&& Objects.equals( saisonDateDebut, that.saisonDateDebut )
				&& Objects.equals( anneeFraisInscription, that.anneeFraisInscription )
				&& Objects.equals( sessionDateDebut, that.sessionDateDebut )
				&& Objects.equals( sessionDateFin, that.sessionDateFin )
				&& Objects.equals( ageDebut, that.ageDebut )
				&& Objects.equals( ageFin, that.ageFin )
				&& Objects.equals( blocNomComplet, that.blocNomComplet )
				&& Objects.equals( blocHeureDebut, that.blocHeureDebut )
				&& Objects.equals( blocHeureFin, that.blocHeureFin )
				&& Objects.equals( blocPrixParMois, that.blocPrixParMois )
				&& Objects.equals( inscriptionNomGroupe, that.inscriptionNomGroupe )
				&& Objects.equals( blocPrixTotal, that.blocPrixTotal )
				&& Objects.equals( inscriptionPaimentChaqueMois, that.inscriptionPaimentChaqueMois )
				&& Objects.equals( paiementDescription, that.paiementDescription )
				&& Objects.equals( paiementDate, that.paiementDate )
				&& Objects.equals( fraisRetardDate, that.fraisRetardDate )
				&& Objects.equals( paiementAnnee, that.paiementAnnee )
				&& Objects.equals( paiementMontant, that.paiementMontant )
				&& Objects.equals( paiementEspeces, that.paiementEspeces )
				&& Objects.equals( paiementCheque, that.paiementCheque )
				&& Objects.equals( paiementECheck, that.paiementECheck )
				&& Objects.equals( paiementSysteme, that.paiementSysteme )
				&& Objects.equals( paiementType, that.paiementType )
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
				&& Objects.equals( paiementProchain, that.paiementProchain )
				&& Objects.equals( fraisMontantDu, that.fraisMontantDu )
				&& Objects.equals( fraisMontantPasse, that.fraisMontantPasse )
				&& Objects.equals( fraisMontantNonPasse, that.fraisMontantNonPasse )
				&& Objects.equals( fraisMontantFuture, that.fraisMontantFuture )
				&& Objects.equals( paiementNomCourt, that.paiementNomCourt )
				&& Objects.equals( paiementNomComplet, that.paiementNomComplet );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PaiementScolaire { ");
		sb.append( "paiementCle: " ).append(paiementCle);
		sb.append( ", inscriptionCle: " ).append(inscriptionCle);
		sb.append( ", ecoleNumero: " ).append(ecoleNumero);
		sb.append( ", utilisateurCles: " ).append(utilisateurCles);
		sb.append( ", ecoleCle: " ).append(ecoleCle);
		sb.append( ", ecoleAddresse: \"" ).append(ecoleAddresse).append( "\"" );
		sb.append( ", ecoleNumeroTelephone: \"" ).append(ecoleNumeroTelephone).append( "\"" );
		sb.append( ", anneeCle: " ).append(anneeCle);
		sb.append( ", sessionCle: " ).append(sessionCle);
		sb.append( ", ageCle: " ).append(ageCle);
		sb.append( ", blocCle: " ).append(blocCle);
		sb.append( ", enfantCle: " ).append(enfantCle);
		sb.append( ", mereCles: " ).append(mereCles);
		sb.append( ", pereCles: " ).append(pereCles);
		sb.append( ", gardienCles: " ).append(gardienCles);
		sb.append( ", enfantNomCompletPrefere: \"" ).append(enfantNomCompletPrefere).append( "\"" );
		sb.append( ", enfantDateNaissance: " ).append(enfantDateNaissance);
		sb.append( ", mereNomCompletPrefere: \"" ).append(mereNomCompletPrefere).append( "\"" );
		sb.append( ", pereNomCompletPrefere: \"" ).append(pereNomCompletPrefere).append( "\"" );
		sb.append( ", ecoleNom: \"" ).append(ecoleNom).append( "\"" );
		sb.append( ", ecoleNomComplet: \"" ).append(ecoleNomComplet).append( "\"" );
		sb.append( ", ecoleEmplacement: \"" ).append(ecoleEmplacement).append( "\"" );
		sb.append( ", anneeDebut: " ).append(anneeDebut);
		sb.append( ", anneeFin: " ).append(anneeFin);
		sb.append( ", saisonDateDebut: " ).append(saisonDateDebut);
		sb.append( ", anneeFraisInscription: " ).append(anneeFraisInscription);
		sb.append( ", sessionDateDebut: " ).append(sessionDateDebut);
		sb.append( ", sessionDateFin: " ).append(sessionDateFin);
		sb.append( ", ageDebut: " ).append(ageDebut);
		sb.append( ", ageFin: " ).append(ageFin);
		sb.append( ", blocNomComplet: \"" ).append(blocNomComplet).append( "\"" );
		sb.append( ", blocHeureDebut: " ).append(blocHeureDebut);
		sb.append( ", blocHeureFin: " ).append(blocHeureFin);
		sb.append( ", blocPrixParMois: " ).append(blocPrixParMois);
		sb.append( ", inscriptionNomGroupe: \"" ).append(inscriptionNomGroupe).append( "\"" );
		sb.append( ", blocPrixTotal: " ).append(blocPrixTotal);
		sb.append( ", inscriptionPaimentChaqueMois: " ).append(inscriptionPaimentChaqueMois);
		sb.append( ", paiementDescription: \"" ).append(paiementDescription).append( "\"" );
		sb.append( ", paiementDate: " ).append(paiementDate);
		sb.append( ", fraisRetardDate: " ).append(fraisRetardDate);
		sb.append( ", paiementAnnee: " ).append(paiementAnnee);
		sb.append( ", paiementMontant: " ).append(paiementMontant);
		sb.append( ", paiementEspeces: " ).append(paiementEspeces);
		sb.append( ", paiementCheque: " ).append(paiementCheque);
		sb.append( ", paiementECheck: " ).append(paiementECheck);
		sb.append( ", paiementSysteme: " ).append(paiementSysteme);
		sb.append( ", paiementType: \"" ).append(paiementType).append( "\"" );
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
		sb.append( ", paiementProchain: " ).append(paiementProchain);
		sb.append( ", fraisMontantDu: " ).append(fraisMontantDu);
		sb.append( ", fraisMontantPasse: " ).append(fraisMontantPasse);
		sb.append( ", fraisMontantNonPasse: " ).append(fraisMontantNonPasse);
		sb.append( ", fraisMontantFuture: " ).append(fraisMontantFuture);
		sb.append( ", paiementNomCourt: \"" ).append(paiementNomCourt).append( "\"" );
		sb.append( ", paiementNomComplet: \"" ).append(paiementNomComplet).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
