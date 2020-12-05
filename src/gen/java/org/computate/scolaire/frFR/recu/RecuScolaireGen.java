package org.computate.scolaire.frFR.recu;

import java.util.Arrays;
import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.ecole.Ecole;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
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
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe paymentCompleteName dans Solr. </a>
 * <br/>
 **/
public abstract class RecuScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(RecuScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String RecuScolaire_UnNom = "un reçu";
	public static final String RecuScolaire_Ce = "ce ";
	public static final String RecuScolaire_CeNom = "ce reçu";
	public static final String RecuScolaire_Un = "un ";
	public static final String RecuScolaire_LeNom = "le reçu";
	public static final String RecuScolaire_NomSingulier = "reçu";
	public static final String RecuScolaire_NomPluriel = "reçus";
	public static final String RecuScolaire_NomActuel = "reçu actuel";
	public static final String RecuScolaire_Tous = "all ";
	public static final String RecuScolaire_TousNom = "tous les reçus";
	public static final String RecuScolaire_RechercherTousNomPar = "rechercher reçus par ";
	public static final String RecuScolaire_RechercherTousNom = "rechercher reçus";
	public static final String RecuScolaire_Titre = "reçus";
	public static final String RecuScolaire_LesNom = "les reçus";
	public static final String RecuScolaire_AucunNomTrouve = "aucun reçu trouvé";
	public static final String RecuScolaire_NomVar = "reçu";
	public static final String RecuScolaire_DeNom = "de reçu";
	public static final String RecuScolaire_NomAdjectifSingulier = "reçu";
	public static final String RecuScolaire_NomAdjectifPluriel = "reçus";
	public static final String RecuScolaire_Couleur = "light-green";
	public static final String RecuScolaire_IconeGroupe = "solid";
	public static final String RecuScolaire_IconeNom = "file-invoice-dollar";
	public static final Integer RecuScolaire_Lignes = 100;

	/////////////
	// recuCle //
	/////////////

	/**	 L'entité recuCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long recuCle;
	@JsonIgnore
	public Couverture<Long> recuCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("recuCle").o(recuCle);

	/**	<br/> L'entité recuCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:recuCle">Trouver l'entité recuCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _recuCle(Couverture<Long> c);

	public Long getRecuCle() {
		return recuCle;
	}

	public void setRecuCle(Long recuCle) {
		this.recuCle = recuCle;
		this.recuCleCouverture.dejaInitialise = true;
	}
	public void setRecuCle(String o) {
		this.recuCle = RecuScolaire.staticSetRecuCle(requeteSite_, o);
		this.recuCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetRecuCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected RecuScolaire recuCleInit() {
		if(!recuCleCouverture.dejaInitialise) {
			_recuCle(recuCleCouverture);
			if(recuCle == null)
				setRecuCle(recuCleCouverture.o);
		}
		recuCleCouverture.dejaInitialise(true);
		return (RecuScolaire)this;
	}

	public static Long staticSolrRecuCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrRecuCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqRecuCle(RequeteSiteFrFR requeteSite_, String o) {
		return RecuScolaire.staticSolrStrRecuCle(requeteSite_, RecuScolaire.staticSolrRecuCle(requeteSite_, RecuScolaire.staticSetRecuCle(requeteSite_, o)));
	}

	public Long solrRecuCle() {
		return RecuScolaire.staticSolrRecuCle(requeteSite_, recuCle);
	}

	public String strRecuCle() {
		return recuCle == null ? "" : recuCle.toString();
	}

	public String jsonRecuCle() {
		return recuCle == null ? "" : recuCle.toString();
	}

	public String nomAffichageRecuCle() {
		return "clé";
	}

	public String htmTooltipRecuCle() {
		return null;
	}

	public String htmRecuCle() {
		return recuCle == null ? "" : StringEscapeUtils.escapeHtml4(strRecuCle());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
		this.ecoleCle = RecuScolaire.staticSetEcoleCle(requeteSite_, o);
		this.ecoleCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetEcoleCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected RecuScolaire ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (RecuScolaire)this;
	}

	public static Long staticSolrEcoleCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrEcoleCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleCle(RequeteSiteFrFR requeteSite_, String o) {
		return RecuScolaire.staticSolrStrEcoleCle(requeteSite_, RecuScolaire.staticSolrEcoleCle(requeteSite_, RecuScolaire.staticSetEcoleCle(requeteSite_, o)));
	}

	public Long solrEcoleCle() {
		return RecuScolaire.staticSolrEcoleCle(requeteSite_, ecoleCle);
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

	public void inputEcoleCle(String classeApiMethodeMethode) {
		RecuScolaire s = (RecuScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_ecoleCle_vider")
						.a("class", "ecoleCle_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_ecoleCle_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "école")
				.a("title", "La clé primaire de l'école dans la base de données. ")
				.a("class", "valeur suggereEcoleCle w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setEcoleCle")
				.a("id", classeApiMethodeMethode, "_ecoleCle")
				.a("autocomplete", "off");
				a("oninput", "suggereRecuScolaireEcoleCle($(this).val() ? rechercherEcoleFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'recuCles:" + pk + "'}", "], $('#listRecuScolaireEcoleCle_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varRecuScolaire", pk, "EcoleCle ").f().sx(htmEcoleCle()).g("span");
			}
		}
	}

	public void htmEcoleCle(String classeApiMethodeMethode) {
		RecuScolaire s = (RecuScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "RecuScolaireEcoleCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/ecole?fq=recuCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-pink w3-hover-pink ").f();
								e("i").a("class", "far fa-school ").f().g("i");
								sx("école");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier une école a ce reçu");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputEcoleCle(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listRecuScolaireEcoleCle_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), Ecole.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), Ecole.ROLES)
										) {
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
												.a("id", classeApiMethodeMethode, "_ecoleCle_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postEcoleVals({ recuCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "ecoleCle')); });")
												.f().sx("ajouter une école")
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
	// ecoleRecherche //
	////////////////////

	/**	 L'entité ecoleRecherche
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<Ecole>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<Ecole> ecoleRecherche = new ListeRecherche<Ecole>();
	@JsonIgnore
	public Couverture<ListeRecherche<Ecole>> ecoleRechercheCouverture = new Couverture<ListeRecherche<Ecole>>().p(this).c(ListeRecherche.class).var("ecoleRecherche").o(ecoleRecherche);

	/**	<br/> L'entité ecoleRecherche
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<Ecole>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleRecherche">Trouver l'entité ecoleRecherche dans Solr</a>
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
	public static ListeRecherche<Ecole> staticSetEcoleRecherche(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected RecuScolaire ecoleRechercheInit() {
		if(!ecoleRechercheCouverture.dejaInitialise) {
			_ecoleRecherche(ecoleRecherche);
		}
		ecoleRecherche.initLoinPourClasse(requeteSite_);
		ecoleRechercheCouverture.dejaInitialise(true);
		return (RecuScolaire)this;
	}

	////////////
	// ecole_ //
	////////////

	/**	 L'entité ecole_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected Ecole ecole_;
	@JsonIgnore
	public Couverture<Ecole> ecole_Couverture = new Couverture<Ecole>().p(this).c(Ecole.class).var("ecole_").o(ecole_);

	/**	<br/> L'entité ecole_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecole_">Trouver l'entité ecole_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecole_(Couverture<Ecole> c);

	public Ecole getEcole_() {
		return ecole_;
	}

	public void setEcole_(Ecole ecole_) {
		this.ecole_ = ecole_;
		this.ecole_Couverture.dejaInitialise = true;
	}
	public static Ecole staticSetEcole_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected RecuScolaire ecole_Init() {
		if(!ecole_Couverture.dejaInitialise) {
			_ecole_(ecole_Couverture);
			if(ecole_ == null)
				setEcole_(ecole_Couverture.o);
		}
		ecole_Couverture.dejaInitialise(true);
		return (RecuScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAddresse(Couverture<String> c);

	public String getEcoleAddresse() {
		return ecoleAddresse;
	}
	public void setEcoleAddresse(String o) {
		this.ecoleAddresse = RecuScolaire.staticSetEcoleAddresse(requeteSite_, o);
		this.ecoleAddresseCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected RecuScolaire ecoleAddresseInit() {
		if(!ecoleAddresseCouverture.dejaInitialise) {
			_ecoleAddresse(ecoleAddresseCouverture);
			if(ecoleAddresse == null)
				setEcoleAddresse(ecoleAddresseCouverture.o);
		}
		ecoleAddresseCouverture.dejaInitialise(true);
		return (RecuScolaire)this;
	}

	public static String staticSolrEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return RecuScolaire.staticSolrStrEcoleAddresse(requeteSite_, RecuScolaire.staticSolrEcoleAddresse(requeteSite_, RecuScolaire.staticSetEcoleAddresse(requeteSite_, o)));
	}

	public String solrEcoleAddresse() {
		return RecuScolaire.staticSolrEcoleAddresse(requeteSite_, ecoleAddresse);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNumeroTelephone(Couverture<String> c);

	public String getEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}
	public void setEcoleNumeroTelephone(String o) {
		this.ecoleNumeroTelephone = RecuScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o);
		this.ecoleNumeroTelephoneCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected RecuScolaire ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneCouverture.dejaInitialise) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneCouverture);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneCouverture.o);
		}
		ecoleNumeroTelephoneCouverture.dejaInitialise(true);
		return (RecuScolaire)this;
	}

	public static String staticSolrEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return RecuScolaire.staticSolrStrEcoleNumeroTelephone(requeteSite_, RecuScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, RecuScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o)));
	}

	public String solrEcoleNumeroTelephone() {
		return RecuScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, ecoleNumeroTelephone);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementDate">Trouver l'entité paiementDate dans Solr</a>
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
		this.paiementDate = RecuScolaire.staticSetPaiementDate(requeteSite_, o);
		this.paiementDateCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetPaiementDate(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setPaiementDate(Date o) {
		this.paiementDate = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.paiementDateCouverture.dejaInitialise = true;
	}
	protected RecuScolaire paiementDateInit() {
		if(!paiementDateCouverture.dejaInitialise) {
			_paiementDate(paiementDateCouverture);
			if(paiementDate == null)
				setPaiementDate(paiementDateCouverture.o);
		}
		paiementDateCouverture.dejaInitialise(true);
		return (RecuScolaire)this;
	}

	public static Date staticSolrPaiementDate(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrPaiementDate(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqPaiementDate(RequeteSiteFrFR requeteSite_, String o) {
		return RecuScolaire.staticSolrStrPaiementDate(requeteSite_, RecuScolaire.staticSolrPaiementDate(requeteSite_, RecuScolaire.staticSetPaiementDate(requeteSite_, o)));
	}

	public Date solrPaiementDate() {
		return RecuScolaire.staticSolrPaiementDate(requeteSite_, paiementDate);
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
		RecuScolaire s = (RecuScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setPaiementDate classRecuScolaire inputRecuScolaire", pk, "PaiementDate w3-input w3-border ")
					.a("placeholder", "DD-MM-YYYY")
					.a("data-timeformat", "dd-MM-yyyy")
					.a("id", classeApiMethodeMethode, "_paiementDate")
					.a("title", "La clé primaire de l'école dans la base de données.  (DD-MM-YYYY)")
					.a("value", paiementDate == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(paiementDate));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementDate', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDate')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDate')); }); } ");
			}
			fg();
		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varRecuScolaire", pk, "PaiementDate ").f().sx(htmPaiementDate()).g("span");
			}
		}
	}

	public void htmPaiementDate(String classeApiMethodeMethode) {
		RecuScolaire s = (RecuScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "RecuScolairePaiementDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-green ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementDate')); $('#", classeApiMethodeMethode, "_paiementDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#RecuScolaireForm :input[name=pk]').val() }], 'setPaiementDate', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDate')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDate')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementAnnee">Trouver l'entité paiementAnnee dans Solr</a>
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
		this.paiementAnnee = RecuScolaire.staticSetPaiementAnnee(requeteSite_, o);
		this.paiementAnneeCouverture.dejaInitialise = true;
	}
	public static Integer staticSetPaiementAnnee(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected RecuScolaire paiementAnneeInit() {
		if(!paiementAnneeCouverture.dejaInitialise) {
			_paiementAnnee(paiementAnneeCouverture);
			if(paiementAnnee == null)
				setPaiementAnnee(paiementAnneeCouverture.o);
		}
		paiementAnneeCouverture.dejaInitialise(true);
		return (RecuScolaire)this;
	}

	public static Integer staticSolrPaiementAnnee(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrPaiementAnnee(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return RecuScolaire.staticSolrStrPaiementAnnee(requeteSite_, RecuScolaire.staticSolrPaiementAnnee(requeteSite_, RecuScolaire.staticSetPaiementAnnee(requeteSite_, o)));
	}

	public Integer solrPaiementAnnee() {
		return RecuScolaire.staticSolrPaiementAnnee(requeteSite_, paiementAnnee);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementMontant">Trouver l'entité paiementMontant dans Solr</a>
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
		this.paiementMontant = RecuScolaire.staticSetPaiementMontant(requeteSite_, o);
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
	protected RecuScolaire paiementMontantInit() {
		if(!paiementMontantCouverture.dejaInitialise) {
			_paiementMontant(paiementMontantCouverture);
			if(paiementMontant == null)
				setPaiementMontant(paiementMontantCouverture.o);
		}
		paiementMontantCouverture.dejaInitialise(true);
		return (RecuScolaire)this;
	}

	public static Double staticSolrPaiementMontant(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaiementMontant(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementMontant(RequeteSiteFrFR requeteSite_, String o) {
		return RecuScolaire.staticSolrStrPaiementMontant(requeteSite_, RecuScolaire.staticSolrPaiementMontant(requeteSite_, RecuScolaire.staticSetPaiementMontant(requeteSite_, o)));
	}

	public Double solrPaiementMontant() {
		return RecuScolaire.staticSolrPaiementMontant(requeteSite_, paiementMontant);
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
		RecuScolaire s = (RecuScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "paiement montant")
				.a("title", "La clé primaire de l'école dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_paiementMontant");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPaiementMontant classRecuScolaire inputRecuScolaire", pk, "PaiementMontant w3-input w3-border ");
					a("name", "setPaiementMontant");
				} else {
					a("class", "valeurPaiementMontant w3-input w3-border classRecuScolaire inputRecuScolaire", pk, "PaiementMontant w3-input w3-border ");
					a("name", "paiementMontant");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementMontant', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementMontant')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementMontant')); }); ");
				}
				a("value", strPaiementMontant())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varRecuScolaire", pk, "PaiementMontant ").f().sx(htmPaiementMontant()).g("span");
			}
		}
	}

	public void htmPaiementMontant(String classeApiMethodeMethode) {
		RecuScolaire s = (RecuScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "RecuScolairePaiementMontant").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-green ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementMontant')); $('#", classeApiMethodeMethode, "_paiementMontant').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#RecuScolaireForm :input[name=pk]').val() }], 'setPaiementMontant', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementMontant')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementMontant')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementDescription">Trouver l'entité paiementDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementDescription(Couverture<String> c);

	public String getPaiementDescription() {
		return paiementDescription;
	}
	public void setPaiementDescription(String o) {
		this.paiementDescription = RecuScolaire.staticSetPaiementDescription(requeteSite_, o);
		this.paiementDescriptionCouverture.dejaInitialise = true;
	}
	public static String staticSetPaiementDescription(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected RecuScolaire paiementDescriptionInit() {
		if(!paiementDescriptionCouverture.dejaInitialise) {
			_paiementDescription(paiementDescriptionCouverture);
			if(paiementDescription == null)
				setPaiementDescription(paiementDescriptionCouverture.o);
		}
		paiementDescriptionCouverture.dejaInitialise(true);
		return (RecuScolaire)this;
	}

	public static String staticSolrPaiementDescription(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPaiementDescription(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementDescription(RequeteSiteFrFR requeteSite_, String o) {
		return RecuScolaire.staticSolrStrPaiementDescription(requeteSite_, RecuScolaire.staticSolrPaiementDescription(requeteSite_, RecuScolaire.staticSetPaiementDescription(requeteSite_, o)));
	}

	public String solrPaiementDescription() {
		return RecuScolaire.staticSolrPaiementDescription(requeteSite_, paiementDescription);
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
		RecuScolaire s = (RecuScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "description")
				.a("title", "La clé primaire de l'école dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_paiementDescription");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPaiementDescription classRecuScolaire inputRecuScolaire", pk, "PaiementDescription w3-input w3-border ");
					a("name", "setPaiementDescription");
				} else {
					a("class", "valeurPaiementDescription w3-input w3-border classRecuScolaire inputRecuScolaire", pk, "PaiementDescription w3-input w3-border ");
					a("name", "paiementDescription");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementDescription', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDescription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDescription')); }); ");
				}
				a("value", strPaiementDescription())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varRecuScolaire", pk, "PaiementDescription ").f().sx(htmPaiementDescription()).g("span");
			}
		}
	}

	public void htmPaiementDescription(String classeApiMethodeMethode) {
		RecuScolaire s = (RecuScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "RecuScolairePaiementDescription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-green ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementDescription')); $('#", classeApiMethodeMethode, "_paiementDescription').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#RecuScolaireForm :input[name=pk]').val() }], 'setPaiementDescription', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementDescription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementDescription')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementNomCourt">Trouver l'entité paiementNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementNomCourt(Couverture<String> c);

	public String getPaiementNomCourt() {
		return paiementNomCourt;
	}
	public void setPaiementNomCourt(String o) {
		this.paiementNomCourt = RecuScolaire.staticSetPaiementNomCourt(requeteSite_, o);
		this.paiementNomCourtCouverture.dejaInitialise = true;
	}
	public static String staticSetPaiementNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected RecuScolaire paiementNomCourtInit() {
		if(!paiementNomCourtCouverture.dejaInitialise) {
			_paiementNomCourt(paiementNomCourtCouverture);
			if(paiementNomCourt == null)
				setPaiementNomCourt(paiementNomCourtCouverture.o);
		}
		paiementNomCourtCouverture.dejaInitialise(true);
		return (RecuScolaire)this;
	}

	public static String staticSolrPaiementNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPaiementNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return RecuScolaire.staticSolrStrPaiementNomCourt(requeteSite_, RecuScolaire.staticSolrPaiementNomCourt(requeteSite_, RecuScolaire.staticSetPaiementNomCourt(requeteSite_, o)));
	}

	public String solrPaiementNomCourt() {
		return RecuScolaire.staticSolrPaiementNomCourt(requeteSite_, paiementNomCourt);
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
		RecuScolaire s = (RecuScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "nom")
				.a("title", "La clé primaire de l'école dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_paiementNomCourt");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPaiementNomCourt classRecuScolaire inputRecuScolaire", pk, "PaiementNomCourt w3-input w3-border ");
					a("name", "setPaiementNomCourt");
				} else {
					a("class", "valeurPaiementNomCourt w3-input w3-border classRecuScolaire inputRecuScolaire", pk, "PaiementNomCourt w3-input w3-border ");
					a("name", "paiementNomCourt");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPaiementNomCourt', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementNomCourt')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementNomCourt')); }); ");
				}
				a("value", strPaiementNomCourt())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varRecuScolaire", pk, "PaiementNomCourt ").f().sx(htmPaiementNomCourt()).g("span");
			}
		}
	}

	public void htmPaiementNomCourt(String classeApiMethodeMethode) {
		RecuScolaire s = (RecuScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "RecuScolairePaiementNomCourt").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-green ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-green ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_paiementNomCourt')); $('#", classeApiMethodeMethode, "_paiementNomCourt').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#RecuScolaireForm :input[name=pk]').val() }], 'setPaiementNomCourt', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_paiementNomCourt')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_paiementNomCourt')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.recu.RecuScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementNomComplet">Trouver l'entité paiementNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementNomComplet(Couverture<String> c);

	public String getPaiementNomComplet() {
		return paiementNomComplet;
	}
	public void setPaiementNomComplet(String o) {
		this.paiementNomComplet = RecuScolaire.staticSetPaiementNomComplet(requeteSite_, o);
		this.paiementNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetPaiementNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected RecuScolaire paiementNomCompletInit() {
		if(!paiementNomCompletCouverture.dejaInitialise) {
			_paiementNomComplet(paiementNomCompletCouverture);
			if(paiementNomComplet == null)
				setPaiementNomComplet(paiementNomCompletCouverture.o);
		}
		paiementNomCompletCouverture.dejaInitialise(true);
		return (RecuScolaire)this;
	}

	public static String staticSolrPaiementNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPaiementNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaiementNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return RecuScolaire.staticSolrStrPaiementNomComplet(requeteSite_, RecuScolaire.staticSolrPaiementNomComplet(requeteSite_, RecuScolaire.staticSetPaiementNomComplet(requeteSite_, o)));
	}

	public String solrPaiementNomComplet() {
		return RecuScolaire.staticSolrPaiementNomComplet(requeteSite_, paiementNomComplet);
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

	protected boolean dejaInitialiseRecuScolaire = false;

	public RecuScolaire initLoinRecuScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseRecuScolaire) {
			dejaInitialiseRecuScolaire = true;
			initLoinRecuScolaire();
		}
		return (RecuScolaire)this;
	}

	public void initLoinRecuScolaire() {
		initRecuScolaire();
		super.initLoinCluster(requeteSite_);
	}

	public void initRecuScolaire() {
		recuCleInit();
		ecoleCleInit();
		ecoleRechercheInit();
		ecole_Init();
		ecoleAddresseInit();
		ecoleNumeroTelephoneInit();
		paiementDateInit();
		paiementAnneeInit();
		paiementMontantInit();
		paiementDescriptionInit();
		paiementNomCourtInit();
		paiementNomCompletInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinRecuScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteRecuScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(ecoleRecherche != null)
			ecoleRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteRecuScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirRecuScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirRecuScolaire(String var) {
		RecuScolaire oRecuScolaire = (RecuScolaire)this;
		switch(var) {
			case "recuCle":
				return oRecuScolaire.recuCle;
			case "ecoleCle":
				return oRecuScolaire.ecoleCle;
			case "ecoleRecherche":
				return oRecuScolaire.ecoleRecherche;
			case "ecole_":
				return oRecuScolaire.ecole_;
			case "ecoleAddresse":
				return oRecuScolaire.ecoleAddresse;
			case "ecoleNumeroTelephone":
				return oRecuScolaire.ecoleNumeroTelephone;
			case "paiementDate":
				return oRecuScolaire.paiementDate;
			case "paiementAnnee":
				return oRecuScolaire.paiementAnnee;
			case "paiementMontant":
				return oRecuScolaire.paiementMontant;
			case "paiementDescription":
				return oRecuScolaire.paiementDescription;
			case "paiementNomCourt":
				return oRecuScolaire.paiementNomCourt;
			case "paiementNomComplet":
				return oRecuScolaire.paiementNomComplet;
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
				o = attribuerRecuScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerRecuScolaire(String var, Object val) {
		RecuScolaire oRecuScolaire = (RecuScolaire)this;
		switch(var) {
			case "ecoleCle":
				if(oRecuScolaire.getEcoleCle() == null)
					oRecuScolaire.setEcoleCle((Long)val);
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
		return staticSetRecuScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetRecuScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "recuCle":
			return RecuScolaire.staticSetRecuCle(requeteSite_, o);
		case "ecoleCle":
			return RecuScolaire.staticSetEcoleCle(requeteSite_, o);
		case "ecoleAddresse":
			return RecuScolaire.staticSetEcoleAddresse(requeteSite_, o);
		case "ecoleNumeroTelephone":
			return RecuScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o);
		case "paiementDate":
			return RecuScolaire.staticSetPaiementDate(requeteSite_, o);
		case "paiementAnnee":
			return RecuScolaire.staticSetPaiementAnnee(requeteSite_, o);
		case "paiementMontant":
			return RecuScolaire.staticSetPaiementMontant(requeteSite_, o);
		case "paiementDescription":
			return RecuScolaire.staticSetPaiementDescription(requeteSite_, o);
		case "paiementNomCourt":
			return RecuScolaire.staticSetPaiementNomCourt(requeteSite_, o);
		case "paiementNomComplet":
			return RecuScolaire.staticSetPaiementNomComplet(requeteSite_, o);
			default:
				return Cluster.staticSetCluster(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrRecuScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrRecuScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "recuCle":
			return RecuScolaire.staticSolrRecuCle(requeteSite_, (Long)o);
		case "ecoleCle":
			return RecuScolaire.staticSolrEcoleCle(requeteSite_, (Long)o);
		case "ecoleAddresse":
			return RecuScolaire.staticSolrEcoleAddresse(requeteSite_, (String)o);
		case "ecoleNumeroTelephone":
			return RecuScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, (String)o);
		case "paiementDate":
			return RecuScolaire.staticSolrPaiementDate(requeteSite_, (LocalDate)o);
		case "paiementAnnee":
			return RecuScolaire.staticSolrPaiementAnnee(requeteSite_, (Integer)o);
		case "paiementMontant":
			return RecuScolaire.staticSolrPaiementMontant(requeteSite_, (BigDecimal)o);
		case "paiementDescription":
			return RecuScolaire.staticSolrPaiementDescription(requeteSite_, (String)o);
		case "paiementNomCourt":
			return RecuScolaire.staticSolrPaiementNomCourt(requeteSite_, (String)o);
		case "paiementNomComplet":
			return RecuScolaire.staticSolrPaiementNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrCluster(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrRecuScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrRecuScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "recuCle":
			return RecuScolaire.staticSolrStrRecuCle(requeteSite_, (Long)o);
		case "ecoleCle":
			return RecuScolaire.staticSolrStrEcoleCle(requeteSite_, (Long)o);
		case "ecoleAddresse":
			return RecuScolaire.staticSolrStrEcoleAddresse(requeteSite_, (String)o);
		case "ecoleNumeroTelephone":
			return RecuScolaire.staticSolrStrEcoleNumeroTelephone(requeteSite_, (String)o);
		case "paiementDate":
			return RecuScolaire.staticSolrStrPaiementDate(requeteSite_, (Date)o);
		case "paiementAnnee":
			return RecuScolaire.staticSolrStrPaiementAnnee(requeteSite_, (Integer)o);
		case "paiementMontant":
			return RecuScolaire.staticSolrStrPaiementMontant(requeteSite_, (Double)o);
		case "paiementDescription":
			return RecuScolaire.staticSolrStrPaiementDescription(requeteSite_, (String)o);
		case "paiementNomCourt":
			return RecuScolaire.staticSolrStrPaiementNomCourt(requeteSite_, (String)o);
		case "paiementNomComplet":
			return RecuScolaire.staticSolrStrPaiementNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqRecuScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqRecuScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "recuCle":
			return RecuScolaire.staticSolrFqRecuCle(requeteSite_, o);
		case "ecoleCle":
			return RecuScolaire.staticSolrFqEcoleCle(requeteSite_, o);
		case "ecoleAddresse":
			return RecuScolaire.staticSolrFqEcoleAddresse(requeteSite_, o);
		case "ecoleNumeroTelephone":
			return RecuScolaire.staticSolrFqEcoleNumeroTelephone(requeteSite_, o);
		case "paiementDate":
			return RecuScolaire.staticSolrFqPaiementDate(requeteSite_, o);
		case "paiementAnnee":
			return RecuScolaire.staticSolrFqPaiementAnnee(requeteSite_, o);
		case "paiementMontant":
			return RecuScolaire.staticSolrFqPaiementMontant(requeteSite_, o);
		case "paiementDescription":
			return RecuScolaire.staticSolrFqPaiementDescription(requeteSite_, o);
		case "paiementNomCourt":
			return RecuScolaire.staticSolrFqPaiementNomCourt(requeteSite_, o);
		case "paiementNomComplet":
			return RecuScolaire.staticSolrFqPaiementNomComplet(requeteSite_, o);
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
					o = definirRecuScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirRecuScolaire(String var, String val) {
		switch(var) {
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
			case "paiementDescription":
				if(val != null)
					setPaiementDescription(val);
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
		peuplerRecuScolaire(solrDocument);
	}
	public void peuplerRecuScolaire(SolrDocument solrDocument) {
		RecuScolaire oRecuScolaire = (RecuScolaire)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("recuCle")) {
				Long recuCle = (Long)solrDocument.get("recuCle_stored_long");
				if(recuCle != null)
					oRecuScolaire.setRecuCle(recuCle);
			}

			Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
			if(ecoleCle != null)
				oRecuScolaire.setEcoleCle(ecoleCle);

			if(sauvegardes.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oRecuScolaire.setEcoleAddresse(ecoleAddresse);
			}

			if(sauvegardes.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oRecuScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardes.contains("paiementDate")) {
				Date paiementDate = (Date)solrDocument.get("paiementDate_stored_date");
				if(paiementDate != null)
					oRecuScolaire.setPaiementDate(paiementDate);
			}

			if(sauvegardes.contains("paiementAnnee")) {
				Integer paiementAnnee = (Integer)solrDocument.get("paiementAnnee_stored_int");
				if(paiementAnnee != null)
					oRecuScolaire.setPaiementAnnee(paiementAnnee);
			}

			if(sauvegardes.contains("paiementMontant")) {
				Double paiementMontant = (Double)solrDocument.get("paiementMontant_stored_double");
				if(paiementMontant != null)
					oRecuScolaire.setPaiementMontant(paiementMontant);
			}

			if(sauvegardes.contains("paiementDescription")) {
				String paiementDescription = (String)solrDocument.get("paiementDescription_stored_string");
				if(paiementDescription != null)
					oRecuScolaire.setPaiementDescription(paiementDescription);
			}

			if(sauvegardes.contains("paiementNomCourt")) {
				String paiementNomCourt = (String)solrDocument.get("paiementNomCourt_stored_string");
				if(paiementNomCourt != null)
					oRecuScolaire.setPaiementNomCourt(paiementNomCourt);
			}

			if(sauvegardes.contains("paiementNomComplet")) {
				String paiementNomComplet = (String)solrDocument.get("paiementNomComplet_stored_string");
				if(paiementNomComplet != null)
					oRecuScolaire.setPaiementNomComplet(paiementNomComplet);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.recu.RecuScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			RecuScolaire o = new RecuScolaire();
			o.requeteSiteRecuScolaire(requeteSite);
			o.initLoinRecuScolaire(requeteSite);
			o.indexerRecuScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerRecuScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerRecuScolaire(document);
	}

	public void indexerRecuScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerRecuScolaire(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerRecuScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerRecuScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerRecuScolaire(SolrInputDocument document) {
		if(recuCle != null) {
			document.addField("recuCle_indexed_long", recuCle);
			document.addField("recuCle_stored_long", recuCle);
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
		if(paiementDate != null) {
			document.addField("paiementDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementDate.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("paiementDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(paiementDate.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(paiementAnnee != null) {
			document.addField("paiementAnnee_indexed_int", paiementAnnee);
			document.addField("paiementAnnee_stored_int", paiementAnnee);
		}
		if(paiementMontant != null) {
			document.addField("paiementMontant_indexed_double", paiementMontant.doubleValue());
			document.addField("paiementMontant_stored_double", paiementMontant.doubleValue());
		}
		if(paiementDescription != null) {
			document.addField("paiementDescription_indexed_string", paiementDescription);
			document.addField("paiementDescription_stored_string", paiementDescription);
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

	public void desindexerRecuScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinRecuScolaire(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeRecuScolaire(String entiteVar) {
		switch(entiteVar) {
			case "recuCle":
				return "recuCle_indexed_long";
			case "ecoleCle":
				return "ecoleCle_indexed_long";
			case "ecoleAddresse":
				return "ecoleAddresse_indexed_string";
			case "ecoleNumeroTelephone":
				return "ecoleNumeroTelephone_indexed_string";
			case "paiementDate":
				return "paiementDate_indexed_date";
			case "paiementAnnee":
				return "paiementAnnee_indexed_int";
			case "paiementMontant":
				return "paiementMontant_indexed_double";
			case "paiementDescription":
				return "paiementDescription_indexed_string";
			case "paiementNomCourt":
				return "paiementNomCourt_indexed_string";
			case "paiementNomComplet":
				return "paiementNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheRecuScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereRecuScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerRecuScolaire(solrDocument);
	}
	public void stockerRecuScolaire(SolrDocument solrDocument) {
		RecuScolaire oRecuScolaire = (RecuScolaire)this;

		Long recuCle = (Long)solrDocument.get("recuCle_stored_long");
		if(recuCle != null)
			oRecuScolaire.setRecuCle(recuCle);

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oRecuScolaire.setEcoleCle(ecoleCle);

		String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
		if(ecoleAddresse != null)
			oRecuScolaire.setEcoleAddresse(ecoleAddresse);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oRecuScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		Date paiementDate = (Date)solrDocument.get("paiementDate_stored_date");
		if(paiementDate != null)
			oRecuScolaire.setPaiementDate(paiementDate);

		Integer paiementAnnee = (Integer)solrDocument.get("paiementAnnee_stored_int");
		if(paiementAnnee != null)
			oRecuScolaire.setPaiementAnnee(paiementAnnee);

		Double paiementMontant = (Double)solrDocument.get("paiementMontant_stored_double");
		if(paiementMontant != null)
			oRecuScolaire.setPaiementMontant(paiementMontant);

		String paiementDescription = (String)solrDocument.get("paiementDescription_stored_string");
		if(paiementDescription != null)
			oRecuScolaire.setPaiementDescription(paiementDescription);

		String paiementNomCourt = (String)solrDocument.get("paiementNomCourt_stored_string");
		if(paiementNomCourt != null)
			oRecuScolaire.setPaiementNomCourt(paiementNomCourt);

		String paiementNomComplet = (String)solrDocument.get("paiementNomComplet_stored_string");
		if(paiementNomComplet != null)
			oRecuScolaire.setPaiementNomComplet(paiementNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiRecuScolaire() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof RecuScolaire) {
			RecuScolaire original = (RecuScolaire)o;
			if(!Objects.equals(recuCle, original.getRecuCle()))
				requeteApi.addVars("recuCle");
			if(!Objects.equals(ecoleCle, original.getEcoleCle()))
				requeteApi.addVars("ecoleCle");
			if(!Objects.equals(ecoleAddresse, original.getEcoleAddresse()))
				requeteApi.addVars("ecoleAddresse");
			if(!Objects.equals(ecoleNumeroTelephone, original.getEcoleNumeroTelephone()))
				requeteApi.addVars("ecoleNumeroTelephone");
			if(!Objects.equals(paiementDate, original.getPaiementDate()))
				requeteApi.addVars("paiementDate");
			if(!Objects.equals(paiementAnnee, original.getPaiementAnnee()))
				requeteApi.addVars("paiementAnnee");
			if(!Objects.equals(paiementMontant, original.getPaiementMontant()))
				requeteApi.addVars("paiementMontant");
			if(!Objects.equals(paiementDescription, original.getPaiementDescription()))
				requeteApi.addVars("paiementDescription");
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
		return Objects.hash(super.hashCode(), recuCle, ecoleCle, ecoleAddresse, ecoleNumeroTelephone, paiementDate, paiementAnnee, paiementMontant, paiementDescription, paiementNomCourt, paiementNomComplet);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof RecuScolaire))
			return false;
		RecuScolaire that = (RecuScolaire)o;
		return super.equals(o)
				&& Objects.equals( recuCle, that.recuCle )
				&& Objects.equals( ecoleCle, that.ecoleCle )
				&& Objects.equals( ecoleAddresse, that.ecoleAddresse )
				&& Objects.equals( ecoleNumeroTelephone, that.ecoleNumeroTelephone )
				&& Objects.equals( paiementDate, that.paiementDate )
				&& Objects.equals( paiementAnnee, that.paiementAnnee )
				&& Objects.equals( paiementMontant, that.paiementMontant )
				&& Objects.equals( paiementDescription, that.paiementDescription )
				&& Objects.equals( paiementNomCourt, that.paiementNomCourt )
				&& Objects.equals( paiementNomComplet, that.paiementNomComplet );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("RecuScolaire { ");
		sb.append( "recuCle: " ).append(recuCle);
		sb.append( ", ecoleCle: " ).append(ecoleCle);
		sb.append( ", ecoleAddresse: \"" ).append(ecoleAddresse).append( "\"" );
		sb.append( ", ecoleNumeroTelephone: \"" ).append(ecoleNumeroTelephone).append( "\"" );
		sb.append( ", paiementDate: " ).append(paiementDate);
		sb.append( ", paiementAnnee: " ).append(paiementAnnee);
		sb.append( ", paiementMontant: " ).append(paiementMontant);
		sb.append( ", paiementDescription: \"" ).append(paiementDescription).append( "\"" );
		sb.append( ", paiementNomCourt: \"" ).append(paiementNomCourt).append( "\"" );
		sb.append( ", paiementNomComplet: \"" ).append(paiementNomComplet).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
