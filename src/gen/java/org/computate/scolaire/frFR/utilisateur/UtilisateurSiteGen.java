package org.computate.scolaire.frFR.utilisateur;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.frFR.paiement.PaiementScolaire;
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.frFR.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true">Trouver la classe seeDeleted dans Solr. </a>
 * <br/>
 **/
public abstract class UtilisateurSiteGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurSite.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin", "SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String UtilisateurSite_UnNom = "un utilisateur du site";
	public static final String UtilisateurSite_Ce = "ce ";
	public static final String UtilisateurSite_CeNom = "cet utilisateur du site";
	public static final String UtilisateurSite_Un = "un ";
	public static final String UtilisateurSite_LeNom = "l'utilisateur du site";
	public static final String UtilisateurSite_NomSingulier = "utilisateur du site";
	public static final String UtilisateurSite_NomPluriel = "utilisateurs du site";
	public static final String UtilisateurSite_NomActuel = "utilisateur du site actuel";
	public static final String UtilisateurSite_Tous = "all ";
	public static final String UtilisateurSite_TousNom = "tous les utilisateurs du site";
	public static final String UtilisateurSite_RechercherTousNomPar = "rechercher utilisateurs du site par ";
	public static final String UtilisateurSite_RechercherTousNom = "rechercher utilisateurs du site";
	public static final String UtilisateurSite_Titre = "utilisateurs du site";
	public static final String UtilisateurSite_LesNom = "les utilisateurs du site";
	public static final String UtilisateurSite_AucunNomTrouve = "aucun utilisateur du site trouvé";
	public static final String UtilisateurSite_NomVar = "utilisateur";
	public static final String UtilisateurSite_DeNom = "d'utilisateur du site";
	public static final String UtilisateurSite_NomAdjectifSingulier = "utilisateur du site";
	public static final String UtilisateurSite_NomAdjectifPluriel = "utilisateurs du site";
	public static final String UtilisateurSite_Couleur = "gray";
	public static final String UtilisateurSite_IconeGroupe = "regular";
	public static final String UtilisateurSite_IconeNom = "user-cog";

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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurCles">Trouver l'entité utilisateurCles dans Solr</a>
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
	public UtilisateurSite addUtilisateurCles(Long...objets) {
		for(Long o : objets) {
			addUtilisateurCles(o);
		}
		return (UtilisateurSite)this;
	}
	public UtilisateurSite addUtilisateurCles(Long o) {
		if(o != null && !utilisateurCles.contains(o))
			this.utilisateurCles.add(o);
		return (UtilisateurSite)this;
	}
	public UtilisateurSite setUtilisateurCles(JsonArray objets) {
		utilisateurCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUtilisateurCles(o);
		}
		return (UtilisateurSite)this;
	}
	public UtilisateurSite addUtilisateurCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUtilisateurCles(p);
		}
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite utilisateurClesInit() {
		if(!utilisateurClesCouverture.dejaInitialise) {
			_utilisateurCles(utilisateurCles);
		}
		utilisateurClesCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
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

	/////////////////////
	// inscriptionCles //
	/////////////////////

	/**	 L'entité inscriptionCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> inscriptionCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> inscriptionClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("inscriptionCles").o(inscriptionCles);

	/**	<br/> L'entité inscriptionCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
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
	public UtilisateurSite addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (UtilisateurSite)this;
	}
	public UtilisateurSite addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (UtilisateurSite)this;
	}
	public UtilisateurSite setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
		return (UtilisateurSite)this;
	}
	public UtilisateurSite addInscriptionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
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

	public void inputInscriptionCles(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "inscriptions")
					.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
					.a("class", "valeur suggereInscriptionCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setInscriptionCles")
					.a("id", classeApiMethodeMethode, "_inscriptionCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereUtilisateurSiteInscriptionCles($(this).val() ? rechercherInscriptionScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'utilisateurCles:" + pk + "'}", "], $('#listUtilisateurSiteInscriptionCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					) {
				sx(htmInscriptionCles());
			}
		}
	}

	public void htmInscriptionCles(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "UtilisateurSiteInscriptionCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=utilisateurCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("inscriptions");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cet utilisateur du site");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputInscriptionCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listUtilisateurSiteInscriptionCles_", classeApiMethodeMethode).f();
								} g("ul");
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
											.a("id", classeApiMethodeMethode, "_inscriptionCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postInscriptionScolaireVals({ utilisateurCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "inscriptionCles')); });")
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

	///////////////////
	// inscriptions_ //
	///////////////////

	/**	 L'entité inscriptions_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<InscriptionScolaire> inscriptions_;
	@JsonIgnore
	public Couverture<List<InscriptionScolaire>> inscriptions_Couverture = new Couverture<List<InscriptionScolaire>>().p(this).c(List.class).var("inscriptions_").o(inscriptions_);

	/**	<br/> L'entité inscriptions_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptions_">Trouver l'entité inscriptions_ dans Solr</a>
	 * <br/>
	 * @param o est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _inscriptions_(Couverture<List<InscriptionScolaire>> o);

	public List<InscriptionScolaire> getInscriptions_() {
		return inscriptions_;
	}

	public void setInscriptions_(List<InscriptionScolaire> inscriptions_) {
		this.inscriptions_ = inscriptions_;
		this.inscriptions_Couverture.dejaInitialise = true;
	}
	public UtilisateurSite addInscriptions_(InscriptionScolaire...objets) {
		for(InscriptionScolaire o : objets) {
			addInscriptions_(o);
		}
		return (UtilisateurSite)this;
	}
	public UtilisateurSite addInscriptions_(InscriptionScolaire o) {
		if(o != null && !inscriptions_.contains(o))
			this.inscriptions_.add(o);
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite inscriptions_Init() {
		if(!inscriptions_Couverture.dejaInitialise) {
			_inscriptions_(inscriptions_Couverture);
			if(inscriptions_ == null)
				setInscriptions_(inscriptions_Couverture.o);
		}
		inscriptions_Couverture.dejaInitialise(true);
		return (UtilisateurSite)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementCles">Trouver l'entité paiementCles dans Solr</a>
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
	public UtilisateurSite addPaiementCles(Long...objets) {
		for(Long o : objets) {
			addPaiementCles(o);
		}
		return (UtilisateurSite)this;
	}
	public UtilisateurSite addPaiementCles(Long o) {
		if(o != null && !paiementCles.contains(o))
			this.paiementCles.add(o);
		return (UtilisateurSite)this;
	}
	public UtilisateurSite setPaiementCles(JsonArray objets) {
		paiementCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPaiementCles(o);
		}
		return (UtilisateurSite)this;
	}
	public UtilisateurSite addPaiementCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPaiementCles(p);
		}
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite paiementClesInit() {
		if(!paiementClesCouverture.dejaInitialise) {
			_paiementCles(paiementCles);
		}
		paiementClesCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
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
		UtilisateurSite s = (UtilisateurSite)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "paiements")
					.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
					.a("class", "valeur suggerePaiementCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setPaiementCles")
					.a("id", classeApiMethodeMethode, "_paiementCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereUtilisateurSitePaiementCles($(this).val() ? rechercherPaiementScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'utilisateurCles:" + pk + "'}", "], $('#listUtilisateurSitePaiementCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					) {
				sx(htmPaiementCles());
			}
		}
	}

	public void htmPaiementCles(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "UtilisateurSitePaiementCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=utilisateurCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "fas fa-search-dollar ").f().g("i");
								sx("paiements");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cet utilisateur du site");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listUtilisateurSitePaiementCles_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), PaiementScolaire.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), PaiementScolaire.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
											.a("id", classeApiMethodeMethode, "_paiementCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postPaiementScolaireVals({ utilisateurCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "paiementCles')); });")
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

	////////////////////
	// utilisateurNom //
	////////////////////

	/**	 L'entité utilisateurNom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String utilisateurNom;
	@JsonIgnore
	public Couverture<String> utilisateurNomCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNom").o(utilisateurNom);

	/**	<br/> L'entité utilisateurNom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNom">Trouver l'entité utilisateurNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNom(Couverture<String> c);

	public String getUtilisateurNom() {
		return utilisateurNom;
	}

	public void setUtilisateurNom(String utilisateurNom) {
		this.utilisateurNom = utilisateurNom;
		this.utilisateurNomCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurNomInit() {
		if(!utilisateurNomCouverture.dejaInitialise) {
			_utilisateurNom(utilisateurNomCouverture);
			if(utilisateurNom == null)
				setUtilisateurNom(utilisateurNomCouverture.o);
		}
		utilisateurNomCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurNom() {
		return utilisateurNom;
	}

	public String strUtilisateurNom() {
		return utilisateurNom == null ? "" : utilisateurNom;
	}

	public String jsonUtilisateurNom() {
		return utilisateurNom == null ? "" : utilisateurNom;
	}

	public String nomAffichageUtilisateurNom() {
		return null;
	}

	public String htmTooltipUtilisateurNom() {
		return null;
	}

	public String htmUtilisateurNom() {
		return utilisateurNom == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurNom());
	}

	public void inputUtilisateurNom(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_utilisateurNom");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setUtilisateurNom classUtilisateurSite inputUtilisateurSite", pk, "UtilisateurNom w3-input w3-border ");
					a("name", "setUtilisateurNom");
				} else {
					a("class", "valeurUtilisateurNom w3-input w3-border classUtilisateurSite inputUtilisateurSite", pk, "UtilisateurNom w3-input w3-border ");
					a("name", "utilisateurNom");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setUtilisateurNom', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_utilisateurNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_utilisateurNom')); }); ");
				}
				a("value", strUtilisateurNom())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					) {
				sx(htmUtilisateurNom());
			}
		}
	}

	public void htmUtilisateurNom(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "UtilisateurSiteUtilisateurNom").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputUtilisateurNom(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_utilisateurNom')); $('#", classeApiMethodeMethode, "_utilisateurNom').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#UtilisateurSiteForm :input[name=pk]').val() }], 'setUtilisateurNom', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_utilisateurNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_utilisateurNom')); }); ")
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
	// utilisateurMail //
	/////////////////////

	/**	 L'entité utilisateurMail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String utilisateurMail;
	@JsonIgnore
	public Couverture<String> utilisateurMailCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurMail").o(utilisateurMail);

	/**	<br/> L'entité utilisateurMail
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurMail">Trouver l'entité utilisateurMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurMail(Couverture<String> c);

	public String getUtilisateurMail() {
		return utilisateurMail;
	}

	public void setUtilisateurMail(String utilisateurMail) {
		this.utilisateurMail = utilisateurMail;
		this.utilisateurMailCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurMailInit() {
		if(!utilisateurMailCouverture.dejaInitialise) {
			_utilisateurMail(utilisateurMailCouverture);
			if(utilisateurMail == null)
				setUtilisateurMail(utilisateurMailCouverture.o);
		}
		utilisateurMailCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurMail() {
		return utilisateurMail;
	}

	public String strUtilisateurMail() {
		return utilisateurMail == null ? "" : utilisateurMail;
	}

	public String jsonUtilisateurMail() {
		return utilisateurMail == null ? "" : utilisateurMail;
	}

	public String nomAffichageUtilisateurMail() {
		return null;
	}

	public String htmTooltipUtilisateurMail() {
		return null;
	}

	public String htmUtilisateurMail() {
		return utilisateurMail == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurMail());
	}

	public void inputUtilisateurMail(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_utilisateurMail");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setUtilisateurMail classUtilisateurSite inputUtilisateurSite", pk, "UtilisateurMail w3-input w3-border ");
					a("name", "setUtilisateurMail");
				} else {
					a("class", "valeurUtilisateurMail w3-input w3-border classUtilisateurSite inputUtilisateurSite", pk, "UtilisateurMail w3-input w3-border ");
					a("name", "utilisateurMail");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setUtilisateurMail', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_utilisateurMail')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_utilisateurMail')); }); ");
				}
				a("value", strUtilisateurMail())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					) {
				sx(htmUtilisateurMail());
			}
		}
	}

	public void htmUtilisateurMail(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "UtilisateurSiteUtilisateurMail").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputUtilisateurMail(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_utilisateurMail')); $('#", classeApiMethodeMethode, "_utilisateurMail').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#UtilisateurSiteForm :input[name=pk]').val() }], 'setUtilisateurMail', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_utilisateurMail')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_utilisateurMail')); }); ")
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
	// utilisateurPrenom //
	///////////////////////

	/**	 L'entité utilisateurPrenom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String utilisateurPrenom;
	@JsonIgnore
	public Couverture<String> utilisateurPrenomCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurPrenom").o(utilisateurPrenom);

	/**	<br/> L'entité utilisateurPrenom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurPrenom">Trouver l'entité utilisateurPrenom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurPrenom(Couverture<String> c);

	public String getUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	public void setUtilisateurPrenom(String utilisateurPrenom) {
		this.utilisateurPrenom = utilisateurPrenom;
		this.utilisateurPrenomCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurPrenomInit() {
		if(!utilisateurPrenomCouverture.dejaInitialise) {
			_utilisateurPrenom(utilisateurPrenomCouverture);
			if(utilisateurPrenom == null)
				setUtilisateurPrenom(utilisateurPrenomCouverture.o);
		}
		utilisateurPrenomCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	public String strUtilisateurPrenom() {
		return utilisateurPrenom == null ? "" : utilisateurPrenom;
	}

	public String jsonUtilisateurPrenom() {
		return utilisateurPrenom == null ? "" : utilisateurPrenom;
	}

	public String nomAffichageUtilisateurPrenom() {
		return null;
	}

	public String htmTooltipUtilisateurPrenom() {
		return null;
	}

	public String htmUtilisateurPrenom() {
		return utilisateurPrenom == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurPrenom());
	}

	///////////////////////////
	// utilisateurNomFamille //
	///////////////////////////

	/**	 L'entité utilisateurNomFamille
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String utilisateurNomFamille;
	@JsonIgnore
	public Couverture<String> utilisateurNomFamilleCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNomFamille").o(utilisateurNomFamille);

	/**	<br/> L'entité utilisateurNomFamille
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNomFamille">Trouver l'entité utilisateurNomFamille dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNomFamille(Couverture<String> c);

	public String getUtilisateurNomFamille() {
		return utilisateurNomFamille;
	}

	public void setUtilisateurNomFamille(String utilisateurNomFamille) {
		this.utilisateurNomFamille = utilisateurNomFamille;
		this.utilisateurNomFamilleCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurNomFamilleInit() {
		if(!utilisateurNomFamilleCouverture.dejaInitialise) {
			_utilisateurNomFamille(utilisateurNomFamilleCouverture);
			if(utilisateurNomFamille == null)
				setUtilisateurNomFamille(utilisateurNomFamilleCouverture.o);
		}
		utilisateurNomFamilleCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurNomFamille() {
		return utilisateurNomFamille;
	}

	public String strUtilisateurNomFamille() {
		return utilisateurNomFamille == null ? "" : utilisateurNomFamille;
	}

	public String jsonUtilisateurNomFamille() {
		return utilisateurNomFamille == null ? "" : utilisateurNomFamille;
	}

	public String nomAffichageUtilisateurNomFamille() {
		return null;
	}

	public String htmTooltipUtilisateurNomFamille() {
		return null;
	}

	public String htmUtilisateurNomFamille() {
		return utilisateurNomFamille == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurNomFamille());
	}

	///////////////////////////
	// utilisateurNomComplet //
	///////////////////////////

	/**	 L'entité utilisateurNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String utilisateurNomComplet;
	@JsonIgnore
	public Couverture<String> utilisateurNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurNomComplet").o(utilisateurNomComplet);

	/**	<br/> L'entité utilisateurNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurNomComplet">Trouver l'entité utilisateurNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurNomComplet(Couverture<String> c);

	public String getUtilisateurNomComplet() {
		return utilisateurNomComplet;
	}

	public void setUtilisateurNomComplet(String utilisateurNomComplet) {
		this.utilisateurNomComplet = utilisateurNomComplet;
		this.utilisateurNomCompletCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurNomCompletInit() {
		if(!utilisateurNomCompletCouverture.dejaInitialise) {
			_utilisateurNomComplet(utilisateurNomCompletCouverture);
			if(utilisateurNomComplet == null)
				setUtilisateurNomComplet(utilisateurNomCompletCouverture.o);
		}
		utilisateurNomCompletCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurNomComplet() {
		return utilisateurNomComplet;
	}

	public String strUtilisateurNomComplet() {
		return utilisateurNomComplet == null ? "" : utilisateurNomComplet;
	}

	public String jsonUtilisateurNomComplet() {
		return utilisateurNomComplet == null ? "" : utilisateurNomComplet;
	}

	public String nomAffichageUtilisateurNomComplet() {
		return null;
	}

	public String htmTooltipUtilisateurNomComplet() {
		return null;
	}

	public String htmUtilisateurNomComplet() {
		return utilisateurNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurNomComplet());
	}

	/////////////////////
	// utilisateurSite //
	/////////////////////

	/**	 L'entité utilisateurSite
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String utilisateurSite;
	@JsonIgnore
	public Couverture<String> utilisateurSiteCouverture = new Couverture<String>().p(this).c(String.class).var("utilisateurSite").o(utilisateurSite);

	/**	<br/> L'entité utilisateurSite
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurSite">Trouver l'entité utilisateurSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurSite(Couverture<String> c);

	public String getUtilisateurSite() {
		return utilisateurSite;
	}

	public void setUtilisateurSite(String utilisateurSite) {
		this.utilisateurSite = utilisateurSite;
		this.utilisateurSiteCouverture.dejaInitialise = true;
	}
	protected UtilisateurSite utilisateurSiteInit() {
		if(!utilisateurSiteCouverture.dejaInitialise) {
			_utilisateurSite(utilisateurSiteCouverture);
			if(utilisateurSite == null)
				setUtilisateurSite(utilisateurSiteCouverture.o);
		}
		utilisateurSiteCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrUtilisateurSite() {
		return utilisateurSite;
	}

	public String strUtilisateurSite() {
		return utilisateurSite == null ? "" : utilisateurSite;
	}

	public String jsonUtilisateurSite() {
		return utilisateurSite == null ? "" : utilisateurSite;
	}

	public String nomAffichageUtilisateurSite() {
		return null;
	}

	public String htmTooltipUtilisateurSite() {
		return null;
	}

	public String htmUtilisateurSite() {
		return utilisateurSite == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurSite());
	}

	////////////////////////
	// customerProfileId1 //
	////////////////////////

	/**	 L'entité customerProfileId1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId1;
	@JsonIgnore
	public Couverture<String> customerProfileId1Couverture = new Couverture<String>().p(this).c(String.class).var("customerProfileId1").o(customerProfileId1);

	/**	<br/> L'entité customerProfileId1
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:customerProfileId1">Trouver l'entité customerProfileId1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _customerProfileId1(Couverture<String> c);

	public String getCustomerProfileId1() {
		return customerProfileId1;
	}

	public void setCustomerProfileId1(String customerProfileId1) {
		this.customerProfileId1 = customerProfileId1;
		this.customerProfileId1Couverture.dejaInitialise = true;
	}
	protected UtilisateurSite customerProfileId1Init() {
		if(!customerProfileId1Couverture.dejaInitialise) {
			_customerProfileId1(customerProfileId1Couverture);
			if(customerProfileId1 == null)
				setCustomerProfileId1(customerProfileId1Couverture.o);
		}
		customerProfileId1Couverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrCustomerProfileId1() {
		return customerProfileId1;
	}

	public String strCustomerProfileId1() {
		return customerProfileId1 == null ? "" : customerProfileId1;
	}

	public String jsonCustomerProfileId1() {
		return customerProfileId1 == null ? "" : customerProfileId1;
	}

	public String nomAffichageCustomerProfileId1() {
		return "customer profile ID 1";
	}

	public String htmTooltipCustomerProfileId1() {
		return null;
	}

	public String htmCustomerProfileId1() {
		return customerProfileId1 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId1());
	}

	public void inputCustomerProfileId1(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "customer profile ID 1")
				.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_customerProfileId1");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setCustomerProfileId1 classUtilisateurSite inputUtilisateurSite", pk, "CustomerProfileId1 w3-input w3-border ");
					a("name", "setCustomerProfileId1");
				} else {
					a("class", "valeurCustomerProfileId1 w3-input w3-border classUtilisateurSite inputUtilisateurSite", pk, "CustomerProfileId1 w3-input w3-border ");
					a("name", "customerProfileId1");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId1', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_customerProfileId1')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_customerProfileId1')); }); ");
				}
				a("value", strCustomerProfileId1())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					) {
				sx(htmCustomerProfileId1());
			}
		}
	}

	public void htmCustomerProfileId1(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "UtilisateurSiteCustomerProfileId1").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_customerProfileId1").a("class", "").f().sx("customer profile ID 1").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId1(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_customerProfileId1')); $('#", classeApiMethodeMethode, "_customerProfileId1').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#UtilisateurSiteForm :input[name=pk]').val() }], 'setCustomerProfileId1', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_customerProfileId1')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_customerProfileId1')); }); ")
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
	// customerProfileId2 //
	////////////////////////

	/**	 L'entité customerProfileId2
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String customerProfileId2;
	@JsonIgnore
	public Couverture<String> customerProfileId2Couverture = new Couverture<String>().p(this).c(String.class).var("customerProfileId2").o(customerProfileId2);

	/**	<br/> L'entité customerProfileId2
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:customerProfileId2">Trouver l'entité customerProfileId2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _customerProfileId2(Couverture<String> c);

	public String getCustomerProfileId2() {
		return customerProfileId2;
	}

	public void setCustomerProfileId2(String customerProfileId2) {
		this.customerProfileId2 = customerProfileId2;
		this.customerProfileId2Couverture.dejaInitialise = true;
	}
	protected UtilisateurSite customerProfileId2Init() {
		if(!customerProfileId2Couverture.dejaInitialise) {
			_customerProfileId2(customerProfileId2Couverture);
			if(customerProfileId2 == null)
				setCustomerProfileId2(customerProfileId2Couverture.o);
		}
		customerProfileId2Couverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public String solrCustomerProfileId2() {
		return customerProfileId2;
	}

	public String strCustomerProfileId2() {
		return customerProfileId2 == null ? "" : customerProfileId2;
	}

	public String jsonCustomerProfileId2() {
		return customerProfileId2 == null ? "" : customerProfileId2;
	}

	public String nomAffichageCustomerProfileId2() {
		return "customer profile ID 2";
	}

	public String htmTooltipCustomerProfileId2() {
		return null;
	}

	public String htmCustomerProfileId2() {
		return customerProfileId2 == null ? "" : StringEscapeUtils.escapeHtml4(strCustomerProfileId2());
	}

	public void inputCustomerProfileId2(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "customer profile ID 2")
				.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
				.a("id", classeApiMethodeMethode, "_customerProfileId2");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setCustomerProfileId2 classUtilisateurSite inputUtilisateurSite", pk, "CustomerProfileId2 w3-input w3-border ");
					a("name", "setCustomerProfileId2");
				} else {
					a("class", "valeurCustomerProfileId2 w3-input w3-border classUtilisateurSite inputUtilisateurSite", pk, "CustomerProfileId2 w3-input w3-border ");
					a("name", "customerProfileId2");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setCustomerProfileId2', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_customerProfileId2')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_customerProfileId2')); }); ");
				}
				a("value", strCustomerProfileId2())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					) {
				sx(htmCustomerProfileId2());
			}
		}
	}

	public void htmCustomerProfileId2(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "UtilisateurSiteCustomerProfileId2").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_customerProfileId2").a("class", "").f().sx("customer profile ID 2").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputCustomerProfileId2(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_customerProfileId2')); $('#", classeApiMethodeMethode, "_customerProfileId2').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#UtilisateurSiteForm :input[name=pk]').val() }], 'setCustomerProfileId2', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_customerProfileId2')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_customerProfileId2')); }); ")
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

	//////////////////////////////////
	// utilisateurRecevoirCourriels //
	//////////////////////////////////

	/**	 L'entité utilisateurRecevoirCourriels
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean utilisateurRecevoirCourriels;
	@JsonIgnore
	public Couverture<Boolean> utilisateurRecevoirCourrielsCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("utilisateurRecevoirCourriels").o(utilisateurRecevoirCourriels);

	/**	<br/> L'entité utilisateurRecevoirCourriels
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurRecevoirCourriels">Trouver l'entité utilisateurRecevoirCourriels dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _utilisateurRecevoirCourriels(Couverture<Boolean> c);

	public Boolean getUtilisateurRecevoirCourriels() {
		return utilisateurRecevoirCourriels;
	}

	public void setUtilisateurRecevoirCourriels(Boolean utilisateurRecevoirCourriels) {
		this.utilisateurRecevoirCourriels = utilisateurRecevoirCourriels;
		this.utilisateurRecevoirCourrielsCouverture.dejaInitialise = true;
	}
	public UtilisateurSite setUtilisateurRecevoirCourriels(String o) {
		this.utilisateurRecevoirCourriels = Boolean.parseBoolean(o);
		this.utilisateurRecevoirCourrielsCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite utilisateurRecevoirCourrielsInit() {
		if(!utilisateurRecevoirCourrielsCouverture.dejaInitialise) {
			_utilisateurRecevoirCourriels(utilisateurRecevoirCourrielsCouverture);
			if(utilisateurRecevoirCourriels == null)
				setUtilisateurRecevoirCourriels(utilisateurRecevoirCourrielsCouverture.o);
		}
		utilisateurRecevoirCourrielsCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public Boolean solrUtilisateurRecevoirCourriels() {
		return utilisateurRecevoirCourriels;
	}

	public String strUtilisateurRecevoirCourriels() {
		return utilisateurRecevoirCourriels == null ? "" : utilisateurRecevoirCourriels.toString();
	}

	public String jsonUtilisateurRecevoirCourriels() {
		return utilisateurRecevoirCourriels == null ? "" : utilisateurRecevoirCourriels.toString();
	}

	public String nomAffichageUtilisateurRecevoirCourriels() {
		return "recevoir des courriels";
	}

	public String htmTooltipUtilisateurRecevoirCourriels() {
		return null;
	}

	public String htmUtilisateurRecevoirCourriels() {
		return utilisateurRecevoirCourriels == null ? "" : StringEscapeUtils.escapeHtml4(strUtilisateurRecevoirCourriels());
	}

	public void inputUtilisateurRecevoirCourriels(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_utilisateurRecevoirCourriels")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_utilisateurRecevoirCourriels");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setUtilisateurRecevoirCourriels classUtilisateurSite inputUtilisateurSite", pk, "UtilisateurRecevoirCourriels w3-input w3-border ");
				a("name", "setUtilisateurRecevoirCourriels");
			} else {
				a("class", "valeurUtilisateurRecevoirCourriels classUtilisateurSite inputUtilisateurSite", pk, "UtilisateurRecevoirCourriels w3-input w3-border ");
				a("name", "utilisateurRecevoirCourriels");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setUtilisateurRecevoirCourriels', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_utilisateurRecevoirCourriels')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_utilisateurRecevoirCourriels')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getUtilisateurRecevoirCourriels() != null && getUtilisateurRecevoirCourriels())
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
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					) {
				sx(htmUtilisateurRecevoirCourriels());
			}
		}
	}

	public void htmUtilisateurRecevoirCourriels(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "UtilisateurSiteUtilisateurRecevoirCourriels").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_utilisateurRecevoirCourriels").a("class", "").f().sx("recevoir des courriels").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputUtilisateurRecevoirCourriels(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// voirArchive //
	/////////////////

	/**	 L'entité voirArchive
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean voirArchive;
	@JsonIgnore
	public Couverture<Boolean> voirArchiveCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("voirArchive").o(voirArchive);

	/**	<br/> L'entité voirArchive
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:voirArchive">Trouver l'entité voirArchive dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _voirArchive(Couverture<Boolean> c);

	public Boolean getVoirArchive() {
		return voirArchive;
	}

	public void setVoirArchive(Boolean voirArchive) {
		this.voirArchive = voirArchive;
		this.voirArchiveCouverture.dejaInitialise = true;
	}
	public UtilisateurSite setVoirArchive(String o) {
		this.voirArchive = Boolean.parseBoolean(o);
		this.voirArchiveCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite voirArchiveInit() {
		if(!voirArchiveCouverture.dejaInitialise) {
			_voirArchive(voirArchiveCouverture);
			if(voirArchive == null)
				setVoirArchive(voirArchiveCouverture.o);
		}
		voirArchiveCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public Boolean solrVoirArchive() {
		return voirArchive;
	}

	public String strVoirArchive() {
		return voirArchive == null ? "" : voirArchive.toString();
	}

	public String jsonVoirArchive() {
		return voirArchive == null ? "" : voirArchive.toString();
	}

	public String nomAffichageVoirArchive() {
		return "voir archivé";
	}

	public String htmTooltipVoirArchive() {
		return null;
	}

	public String htmVoirArchive() {
		return voirArchive == null ? "" : StringEscapeUtils.escapeHtml4(strVoirArchive());
	}

	public void inputVoirArchive(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_voirArchive")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_voirArchive");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setVoirArchive classUtilisateurSite inputUtilisateurSite", pk, "VoirArchive w3-input w3-border ");
				a("name", "setVoirArchive");
			} else {
				a("class", "valeurVoirArchive classUtilisateurSite inputUtilisateurSite", pk, "VoirArchive w3-input w3-border ");
				a("name", "voirArchive");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setVoirArchive', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_voirArchive')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_voirArchive')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getVoirArchive() != null && getVoirArchive())
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
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					) {
				sx(htmVoirArchive());
			}
		}
	}

	public void htmVoirArchive(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "UtilisateurSiteVoirArchive").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_voirArchive").a("class", "").f().sx("voir archivé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputVoirArchive(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// voirSupprime //
	//////////////////

	/**	 L'entité voirSupprime
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean voirSupprime;
	@JsonIgnore
	public Couverture<Boolean> voirSupprimeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("voirSupprime").o(voirSupprime);

	/**	<br/> L'entité voirSupprime
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.utilisateur.UtilisateurSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:voirSupprime">Trouver l'entité voirSupprime dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _voirSupprime(Couverture<Boolean> c);

	public Boolean getVoirSupprime() {
		return voirSupprime;
	}

	public void setVoirSupprime(Boolean voirSupprime) {
		this.voirSupprime = voirSupprime;
		this.voirSupprimeCouverture.dejaInitialise = true;
	}
	public UtilisateurSite setVoirSupprime(String o) {
		this.voirSupprime = Boolean.parseBoolean(o);
		this.voirSupprimeCouverture.dejaInitialise = true;
		return (UtilisateurSite)this;
	}
	protected UtilisateurSite voirSupprimeInit() {
		if(!voirSupprimeCouverture.dejaInitialise) {
			_voirSupprime(voirSupprimeCouverture);
			if(voirSupprime == null)
				setVoirSupprime(voirSupprimeCouverture.o);
		}
		voirSupprimeCouverture.dejaInitialise(true);
		return (UtilisateurSite)this;
	}

	public Boolean solrVoirSupprime() {
		return voirSupprime;
	}

	public String strVoirSupprime() {
		return voirSupprime == null ? "" : voirSupprime.toString();
	}

	public String jsonVoirSupprime() {
		return voirSupprime == null ? "" : voirSupprime.toString();
	}

	public String nomAffichageVoirSupprime() {
		return "voir supprimé";
	}

	public String htmTooltipVoirSupprime() {
		return null;
	}

	public String htmVoirSupprime() {
		return voirSupprime == null ? "" : StringEscapeUtils.escapeHtml4(strVoirSupprime());
	}

	public void inputVoirSupprime(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_voirSupprime")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_voirSupprime");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setVoirSupprime classUtilisateurSite inputUtilisateurSite", pk, "VoirSupprime w3-input w3-border ");
				a("name", "setVoirSupprime");
			} else {
				a("class", "valeurVoirSupprime classUtilisateurSite inputUtilisateurSite", pk, "VoirSupprime w3-input w3-border ");
				a("name", "voirSupprime");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setVoirSupprime', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_voirSupprime')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_voirSupprime')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getVoirSupprime() != null && getVoirSupprime())
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
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
					) {
				sx(htmVoirSupprime());
			}
		}
	}

	public void htmVoirSupprime(String classeApiMethodeMethode) {
		UtilisateurSite s = (UtilisateurSite)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "UtilisateurSiteVoirSupprime").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-gray ").f();
							e("label").a("for", classeApiMethodeMethode, "_voirSupprime").a("class", "").f().sx("voir supprimé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputVoirSupprime(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseUtilisateurSite = false;

	public UtilisateurSite initLoinUtilisateurSite(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseUtilisateurSite) {
			dejaInitialiseUtilisateurSite = true;
			initLoinUtilisateurSite();
		}
		return (UtilisateurSite)this;
	}

	public void initLoinUtilisateurSite() {
		initUtilisateurSite();
		super.initLoinCluster(requeteSite_);
	}

	public void initUtilisateurSite() {
		utilisateurClesInit();
		inscriptionClesInit();
		inscriptions_Init();
		paiementClesInit();
		utilisateurNomInit();
		utilisateurMailInit();
		utilisateurPrenomInit();
		utilisateurNomFamilleInit();
		utilisateurNomCompletInit();
		utilisateurSiteInit();
		customerProfileId1Init();
		customerProfileId2Init();
		utilisateurRecevoirCourrielsInit();
		voirArchiveInit();
		voirSupprimeInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinUtilisateurSite(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteUtilisateurSite(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteUtilisateurSite(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirUtilisateurSite(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirUtilisateurSite(String var) {
		UtilisateurSite oUtilisateurSite = (UtilisateurSite)this;
		switch(var) {
			case "utilisateurCles":
				return oUtilisateurSite.utilisateurCles;
			case "inscriptionCles":
				return oUtilisateurSite.inscriptionCles;
			case "inscriptions_":
				return oUtilisateurSite.inscriptions_;
			case "paiementCles":
				return oUtilisateurSite.paiementCles;
			case "utilisateurNom":
				return oUtilisateurSite.utilisateurNom;
			case "utilisateurMail":
				return oUtilisateurSite.utilisateurMail;
			case "utilisateurPrenom":
				return oUtilisateurSite.utilisateurPrenom;
			case "utilisateurNomFamille":
				return oUtilisateurSite.utilisateurNomFamille;
			case "utilisateurNomComplet":
				return oUtilisateurSite.utilisateurNomComplet;
			case "utilisateurSite":
				return oUtilisateurSite.utilisateurSite;
			case "customerProfileId1":
				return oUtilisateurSite.customerProfileId1;
			case "customerProfileId2":
				return oUtilisateurSite.customerProfileId2;
			case "utilisateurRecevoirCourriels":
				return oUtilisateurSite.utilisateurRecevoirCourriels;
			case "voirArchive":
				return oUtilisateurSite.voirArchive;
			case "voirSupprime":
				return oUtilisateurSite.voirSupprime;
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
				o = attribuerUtilisateurSite(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerUtilisateurSite(String var, Object val) {
		UtilisateurSite oUtilisateurSite = (UtilisateurSite)this;
		switch(var) {
			case "inscriptionCles":
				oUtilisateurSite.addInscriptionCles((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "paiementCles":
				oUtilisateurSite.addPaiementCles((Long)val);
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
					o = definirUtilisateurSite(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirUtilisateurSite(String var, String val) {
		switch(var) {
			case "utilisateurNom":
				if(val != null)
					setUtilisateurNom(val);
				sauvegardes.add(var);
				return val;
			case "utilisateurMail":
				if(val != null)
					setUtilisateurMail(val);
				sauvegardes.add(var);
				return val;
			case "customerProfileId1":
				if(val != null)
					setCustomerProfileId1(val);
				sauvegardes.add(var);
				return val;
			case "customerProfileId2":
				if(val != null)
					setCustomerProfileId2(val);
				sauvegardes.add(var);
				return val;
			case "utilisateurRecevoirCourriels":
				if(val != null)
					setUtilisateurRecevoirCourriels(val);
				sauvegardes.add(var);
				return val;
			case "voirArchive":
				if(val != null)
					setVoirArchive(val);
				sauvegardes.add(var);
				return val;
			case "voirSupprime":
				if(val != null)
					setVoirSupprime(val);
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
		peuplerUtilisateurSite(solrDocument);
	}
	public void peuplerUtilisateurSite(SolrDocument solrDocument) {
		UtilisateurSite oUtilisateurSite = (UtilisateurSite)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("utilisateurCles")) {
				List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
				if(utilisateurCles != null)
					oUtilisateurSite.utilisateurCles.addAll(utilisateurCles);
			}

			List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
			if(inscriptionCles != null)
				oUtilisateurSite.inscriptionCles.addAll(inscriptionCles);

			List<Long> paiementCles = (List<Long>)solrDocument.get("paiementCles_stored_longs");
			if(paiementCles != null)
				oUtilisateurSite.paiementCles.addAll(paiementCles);

			if(sauvegardes.contains("utilisateurNom")) {
				String utilisateurNom = (String)solrDocument.get("utilisateurNom_stored_string");
				if(utilisateurNom != null)
					oUtilisateurSite.setUtilisateurNom(utilisateurNom);
			}

			if(sauvegardes.contains("utilisateurMail")) {
				String utilisateurMail = (String)solrDocument.get("utilisateurMail_stored_string");
				if(utilisateurMail != null)
					oUtilisateurSite.setUtilisateurMail(utilisateurMail);
			}

			if(sauvegardes.contains("utilisateurPrenom")) {
				String utilisateurPrenom = (String)solrDocument.get("utilisateurPrenom_stored_string");
				if(utilisateurPrenom != null)
					oUtilisateurSite.setUtilisateurPrenom(utilisateurPrenom);
			}

			if(sauvegardes.contains("utilisateurNomFamille")) {
				String utilisateurNomFamille = (String)solrDocument.get("utilisateurNomFamille_stored_string");
				if(utilisateurNomFamille != null)
					oUtilisateurSite.setUtilisateurNomFamille(utilisateurNomFamille);
			}

			if(sauvegardes.contains("utilisateurNomComplet")) {
				String utilisateurNomComplet = (String)solrDocument.get("utilisateurNomComplet_stored_string");
				if(utilisateurNomComplet != null)
					oUtilisateurSite.setUtilisateurNomComplet(utilisateurNomComplet);
			}

			if(sauvegardes.contains("utilisateurSite")) {
				String utilisateurSite = (String)solrDocument.get("utilisateurSite_stored_string");
				if(utilisateurSite != null)
					oUtilisateurSite.setUtilisateurSite(utilisateurSite);
			}

			if(sauvegardes.contains("customerProfileId1")) {
				String customerProfileId1 = (String)solrDocument.get("customerProfileId1_stored_string");
				if(customerProfileId1 != null)
					oUtilisateurSite.setCustomerProfileId1(customerProfileId1);
			}

			if(sauvegardes.contains("customerProfileId2")) {
				String customerProfileId2 = (String)solrDocument.get("customerProfileId2_stored_string");
				if(customerProfileId2 != null)
					oUtilisateurSite.setCustomerProfileId2(customerProfileId2);
			}

			if(sauvegardes.contains("utilisateurRecevoirCourriels")) {
				Boolean utilisateurRecevoirCourriels = (Boolean)solrDocument.get("utilisateurRecevoirCourriels_stored_boolean");
				if(utilisateurRecevoirCourriels != null)
					oUtilisateurSite.setUtilisateurRecevoirCourriels(utilisateurRecevoirCourriels);
			}

			if(sauvegardes.contains("voirArchive")) {
				Boolean voirArchive = (Boolean)solrDocument.get("voirArchive_stored_boolean");
				if(voirArchive != null)
					oUtilisateurSite.setVoirArchive(voirArchive);
			}

			if(sauvegardes.contains("voirSupprime")) {
				Boolean voirSupprime = (Boolean)solrDocument.get("voirSupprime_stored_boolean");
				if(voirSupprime != null)
					oUtilisateurSite.setVoirSupprime(voirSupprime);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.utilisateur.UtilisateurSite"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			UtilisateurSite o = new UtilisateurSite();
			o.requeteSiteUtilisateurSite(requeteSite);
			o.initLoinUtilisateurSite(requeteSite);
			o.indexerUtilisateurSite();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerUtilisateurSite();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerUtilisateurSite(document);
	}

	public void indexerUtilisateurSite(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerUtilisateurSite(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerUtilisateurSite() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerUtilisateurSite(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerUtilisateurSite(SolrInputDocument document) {
		if(utilisateurCles != null) {
			for(java.lang.Long o : utilisateurCles) {
				document.addField("utilisateurCles_indexed_longs", o);
			}
			for(java.lang.Long o : utilisateurCles) {
				document.addField("utilisateurCles_stored_longs", o);
			}
		}
		if(inscriptionCles != null) {
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_indexed_longs", o);
			}
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_stored_longs", o);
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
		if(utilisateurNom != null) {
			document.addField("utilisateurNom_indexed_string", utilisateurNom);
			document.addField("utilisateurNom_stored_string", utilisateurNom);
		}
		if(utilisateurMail != null) {
			document.addField("utilisateurMail_indexed_string", utilisateurMail);
			document.addField("utilisateurMail_stored_string", utilisateurMail);
		}
		if(utilisateurPrenom != null) {
			document.addField("utilisateurPrenom_indexed_string", utilisateurPrenom);
			document.addField("utilisateurPrenom_stored_string", utilisateurPrenom);
		}
		if(utilisateurNomFamille != null) {
			document.addField("utilisateurNomFamille_indexed_string", utilisateurNomFamille);
			document.addField("utilisateurNomFamille_stored_string", utilisateurNomFamille);
		}
		if(utilisateurNomComplet != null) {
			document.addField("utilisateurNomComplet_indexed_string", utilisateurNomComplet);
			document.addField("utilisateurNomComplet_stored_string", utilisateurNomComplet);
		}
		if(utilisateurSite != null) {
			document.addField("utilisateurSite_indexed_string", utilisateurSite);
			document.addField("utilisateurSite_stored_string", utilisateurSite);
		}
		if(customerProfileId1 != null) {
			document.addField("customerProfileId1_indexed_string", customerProfileId1);
			document.addField("customerProfileId1_stored_string", customerProfileId1);
		}
		if(customerProfileId2 != null) {
			document.addField("customerProfileId2_indexed_string", customerProfileId2);
			document.addField("customerProfileId2_stored_string", customerProfileId2);
		}
		if(utilisateurRecevoirCourriels != null) {
			document.addField("utilisateurRecevoirCourriels_indexed_boolean", utilisateurRecevoirCourriels);
			document.addField("utilisateurRecevoirCourriels_stored_boolean", utilisateurRecevoirCourriels);
		}
		if(voirArchive != null) {
			document.addField("voirArchive_indexed_boolean", voirArchive);
			document.addField("voirArchive_stored_boolean", voirArchive);
		}
		if(voirSupprime != null) {
			document.addField("voirSupprime_indexed_boolean", voirSupprime);
			document.addField("voirSupprime_stored_boolean", voirSupprime);
		}
		super.indexerCluster(document);

	}

	public void desindexerUtilisateurSite() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinUtilisateurSite(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeUtilisateurSite(String entiteVar) {
		switch(entiteVar) {
			case "utilisateurCles":
				return "utilisateurCles_indexed_longs";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "paiementCles":
				return "paiementCles_indexed_longs";
			case "utilisateurNom":
				return "utilisateurNom_indexed_string";
			case "utilisateurMail":
				return "utilisateurMail_indexed_string";
			case "utilisateurPrenom":
				return "utilisateurPrenom_indexed_string";
			case "utilisateurNomFamille":
				return "utilisateurNomFamille_indexed_string";
			case "utilisateurNomComplet":
				return "utilisateurNomComplet_indexed_string";
			case "utilisateurSite":
				return "utilisateurSite_indexed_string";
			case "customerProfileId1":
				return "customerProfileId1_indexed_string";
			case "customerProfileId2":
				return "customerProfileId2_indexed_string";
			case "utilisateurRecevoirCourriels":
				return "utilisateurRecevoirCourriels_indexed_boolean";
			case "voirArchive":
				return "voirArchive_indexed_boolean";
			case "voirSupprime":
				return "voirSupprime_indexed_boolean";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheUtilisateurSite(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereUtilisateurSite(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerUtilisateurSite(solrDocument);
	}
	public void stockerUtilisateurSite(SolrDocument solrDocument) {
		UtilisateurSite oUtilisateurSite = (UtilisateurSite)this;

		List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
		if(utilisateurCles != null)
			oUtilisateurSite.utilisateurCles.addAll(utilisateurCles);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oUtilisateurSite.inscriptionCles.addAll(inscriptionCles);

		List<Long> paiementCles = (List<Long>)solrDocument.get("paiementCles_stored_longs");
		if(paiementCles != null)
			oUtilisateurSite.paiementCles.addAll(paiementCles);

		String utilisateurNom = (String)solrDocument.get("utilisateurNom_stored_string");
		if(utilisateurNom != null)
			oUtilisateurSite.setUtilisateurNom(utilisateurNom);

		String utilisateurMail = (String)solrDocument.get("utilisateurMail_stored_string");
		if(utilisateurMail != null)
			oUtilisateurSite.setUtilisateurMail(utilisateurMail);

		String utilisateurPrenom = (String)solrDocument.get("utilisateurPrenom_stored_string");
		if(utilisateurPrenom != null)
			oUtilisateurSite.setUtilisateurPrenom(utilisateurPrenom);

		String utilisateurNomFamille = (String)solrDocument.get("utilisateurNomFamille_stored_string");
		if(utilisateurNomFamille != null)
			oUtilisateurSite.setUtilisateurNomFamille(utilisateurNomFamille);

		String utilisateurNomComplet = (String)solrDocument.get("utilisateurNomComplet_stored_string");
		if(utilisateurNomComplet != null)
			oUtilisateurSite.setUtilisateurNomComplet(utilisateurNomComplet);

		String utilisateurSite = (String)solrDocument.get("utilisateurSite_stored_string");
		if(utilisateurSite != null)
			oUtilisateurSite.setUtilisateurSite(utilisateurSite);

		String customerProfileId1 = (String)solrDocument.get("customerProfileId1_stored_string");
		if(customerProfileId1 != null)
			oUtilisateurSite.setCustomerProfileId1(customerProfileId1);

		String customerProfileId2 = (String)solrDocument.get("customerProfileId2_stored_string");
		if(customerProfileId2 != null)
			oUtilisateurSite.setCustomerProfileId2(customerProfileId2);

		Boolean utilisateurRecevoirCourriels = (Boolean)solrDocument.get("utilisateurRecevoirCourriels_stored_boolean");
		if(utilisateurRecevoirCourriels != null)
			oUtilisateurSite.setUtilisateurRecevoirCourriels(utilisateurRecevoirCourriels);

		Boolean voirArchive = (Boolean)solrDocument.get("voirArchive_stored_boolean");
		if(voirArchive != null)
			oUtilisateurSite.setVoirArchive(voirArchive);

		Boolean voirSupprime = (Boolean)solrDocument.get("voirSupprime_stored_boolean");
		if(voirSupprime != null)
			oUtilisateurSite.setVoirSupprime(voirSupprime);

		super.stockerCluster(solrDocument);
	}

	//////////////
	// htmlBody //
	//////////////

	public void htmlBody() {
		htmlBodyUtilisateurSite();
	}

	public void htmlBodyUtilisateurSite() {
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiUtilisateurSite() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof UtilisateurSite) {
			UtilisateurSite original = (UtilisateurSite)o;
			if(!Objects.equals(inscriptionCles, original.getInscriptionCles()))
				requeteApi.addVars("inscriptionCles");
			if(!Objects.equals(paiementCles, original.getPaiementCles()))
				requeteApi.addVars("paiementCles");
			if(!Objects.equals(utilisateurNom, original.getUtilisateurNom()))
				requeteApi.addVars("utilisateurNom");
			if(!Objects.equals(utilisateurMail, original.getUtilisateurMail()))
				requeteApi.addVars("utilisateurMail");
			if(!Objects.equals(customerProfileId1, original.getCustomerProfileId1()))
				requeteApi.addVars("customerProfileId1");
			if(!Objects.equals(customerProfileId2, original.getCustomerProfileId2()))
				requeteApi.addVars("customerProfileId2");
			if(!Objects.equals(utilisateurRecevoirCourriels, original.getUtilisateurRecevoirCourriels()))
				requeteApi.addVars("utilisateurRecevoirCourriels");
			if(!Objects.equals(voirArchive, original.getVoirArchive()))
				requeteApi.addVars("voirArchive");
			if(!Objects.equals(voirSupprime, original.getVoirSupprime()))
				requeteApi.addVars("voirSupprime");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), inscriptionCles, paiementCles, utilisateurNom, utilisateurMail, customerProfileId1, customerProfileId2, utilisateurRecevoirCourriels, voirArchive, voirSupprime);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof UtilisateurSite))
			return false;
		UtilisateurSite that = (UtilisateurSite)o;
		return super.equals(o)
				&& Objects.equals( inscriptionCles, that.inscriptionCles )
				&& Objects.equals( paiementCles, that.paiementCles )
				&& Objects.equals( utilisateurNom, that.utilisateurNom )
				&& Objects.equals( utilisateurMail, that.utilisateurMail )
				&& Objects.equals( customerProfileId1, that.customerProfileId1 )
				&& Objects.equals( customerProfileId2, that.customerProfileId2 )
				&& Objects.equals( utilisateurRecevoirCourriels, that.utilisateurRecevoirCourriels )
				&& Objects.equals( voirArchive, that.voirArchive )
				&& Objects.equals( voirSupprime, that.voirSupprime );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("UtilisateurSite { ");
		sb.append( "inscriptionCles: " ).append(inscriptionCles);
		sb.append( ", paiementCles: " ).append(paiementCles);
		sb.append( ", utilisateurNom: \"" ).append(utilisateurNom).append( "\"" );
		sb.append( ", utilisateurMail: \"" ).append(utilisateurMail).append( "\"" );
		sb.append( ", customerProfileId1: \"" ).append(customerProfileId1).append( "\"" );
		sb.append( ", customerProfileId2: \"" ).append(customerProfileId2).append( "\"" );
		sb.append( ", utilisateurRecevoirCourriels: " ).append(utilisateurRecevoirCourriels);
		sb.append( ", voirArchive: " ).append(voirArchive);
		sb.append( ", voirSupprime: " ).append(voirSupprime);
		sb.append(" }");
		return sb.toString();
	}
}
