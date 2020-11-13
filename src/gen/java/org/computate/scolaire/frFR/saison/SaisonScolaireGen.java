package org.computate.scolaire.frFR.saison;

import java.util.Arrays;
import java.util.Date;
import org.computate.scolaire.frFR.session.SessionScolaire;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
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
import org.computate.scolaire.frFR.annee.AnneeScolaire;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe seasonCompleteName dans Solr. </a>
 * <br/>
 **/
public abstract class SaisonScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SaisonScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String SaisonScolaire_UnNom = "une saison";
	public static final String SaisonScolaire_Ce = "cette ";
	public static final String SaisonScolaire_CeNom = "cette saison";
	public static final String SaisonScolaire_Un = "une ";
	public static final String SaisonScolaire_LeNom = "la saison";
	public static final String SaisonScolaire_NomSingulier = "saison";
	public static final String SaisonScolaire_NomPluriel = "saisons";
	public static final String SaisonScolaire_NomActuel = "saison actuelle";
	public static final String SaisonScolaire_Tous = "all ";
	public static final String SaisonScolaire_TousNom = "toutes les saisons";
	public static final String SaisonScolaire_RechercherTousNomPar = "rechercher saisons par ";
	public static final String SaisonScolaire_RechercherTousNom = "rechercher saisons";
	public static final String SaisonScolaire_Titre = "saisons";
	public static final String SaisonScolaire_LesNom = "les saisons";
	public static final String SaisonScolaire_AucunNomTrouve = "aucune saison trouvée";
	public static final String SaisonScolaire_NomVar = "saison";
	public static final String SaisonScolaire_DeNom = "de saison";
	public static final String SaisonScolaire_AdjectifPluriel = "scolaires";
	public static final String SaisonScolaire_AdjectifVar = "scolaire";
	public static final String SaisonScolaire_UnNomAdjectif = "une saison scolaire";
	public static final String SaisonScolaire_NomAdjectifSingulier = "saison scolaire";
	public static final String SaisonScolaire_Couleur = "yellow";
	public static final String SaisonScolaire_IconeGroupe = "regular";
	public static final String SaisonScolaire_IconeNom = "sun";

	///////////////
	// saisonCle //
	///////////////

	/**	 L'entité saisonCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long saisonCle;
	@JsonIgnore
	public Couverture<Long> saisonCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("saisonCle").o(saisonCle);

	/**	<br/> L'entité saisonCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCle">Trouver l'entité saisonCle dans Solr</a>
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
	public void setSaisonCle(String o) {
		this.saisonCle = SaisonScolaire.staticSetSaisonCle(requeteSite_, o);
		this.saisonCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetSaisonCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SaisonScolaire saisonCleInit() {
		if(!saisonCleCouverture.dejaInitialise) {
			_saisonCle(saisonCleCouverture);
			if(saisonCle == null)
				setSaisonCle(saisonCleCouverture.o);
		}
		saisonCleCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Long staticSolrSaisonCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSaisonCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonCle(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrSaisonCle(requeteSite_, SaisonScolaire.staticSolrSaisonCle(requeteSite_, SaisonScolaire.staticSetSaisonCle(requeteSite_, o)));
	}

	public Long solrSaisonCle() {
		return SaisonScolaire.staticSolrSaisonCle(requeteSite_, saisonCle);
	}

	public String strSaisonCle() {
		return saisonCle == null ? "" : saisonCle.toString();
	}

	public String jsonSaisonCle() {
		return saisonCle == null ? "" : saisonCle.toString();
	}

	public String nomAffichageSaisonCle() {
		return "clé";
	}

	public String htmTooltipSaisonCle() {
		return null;
	}

	public String htmSaisonCle() {
		return saisonCle == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonCle());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
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
	public void setInscriptionCles(String o) {
		Long l = SaisonScolaire.staticSetInscriptionCles(requeteSite_, o);
		if(l != null)
			addInscriptionCles(l);
		this.inscriptionClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetInscriptionCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SaisonScolaire addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (SaisonScolaire)this;
	}
	public SaisonScolaire addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (SaisonScolaire)this;
	}
	public void setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
	}
	public SaisonScolaire addInscriptionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (SaisonScolaire)this;
	}
	protected SaisonScolaire inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Long staticSolrInscriptionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrInscriptionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionCles(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrInscriptionCles(requeteSite_, SaisonScolaire.staticSolrInscriptionCles(requeteSite_, SaisonScolaire.staticSetInscriptionCles(requeteSite_, o)));
	}

	public List<Long> solrInscriptionCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : inscriptionCles) {
			l.add(SaisonScolaire.staticSolrInscriptionCles(requeteSite_, o));
		}
		return l;
	}

	public String strInscriptionCles() {
		return inscriptionCles == null ? "" : inscriptionCles.toString();
	}

	public String jsonInscriptionCles() {
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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
		this.anneeCle = SaisonScolaire.staticSetAnneeCle(requeteSite_, o);
		this.anneeCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetAnneeCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SaisonScolaire anneeCleInit() {
		if(!anneeCleCouverture.dejaInitialise) {
			_anneeCle(anneeCleCouverture);
			if(anneeCle == null)
				setAnneeCle(anneeCleCouverture.o);
		}
		anneeCleCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Long staticSolrAnneeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAnneeCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeCle(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrAnneeCle(requeteSite_, SaisonScolaire.staticSolrAnneeCle(requeteSite_, SaisonScolaire.staticSetAnneeCle(requeteSite_, o)));
	}

	public Long solrAnneeCle() {
		return SaisonScolaire.staticSolrAnneeCle(requeteSite_, anneeCle);
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
		SaisonScolaire s = (SaisonScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "année")
					.a("title", "Les sessions scolaires de la saison scolaire. ")
					.a("class", "valeur suggereAnneeCle w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setAnneeCle")
					.a("id", classeApiMethodeMethode, "_anneeCle")
					.a("autocomplete", "off");
					if("Page".equals(classeApiMethodeMethode)) {
						a("oninput", "suggereSaisonScolaireAnneeCle($(this).val() ? rechercherAnneeScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'saisonCles:" + pk + "'}", "], $('#listSaisonScolaireAnneeCle_", classeApiMethodeMethode, "'), ", pk, "); ");
					}

				fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varSaisonScolaire", pk, "AnneeCle ").f().sx(htmAnneeCle()).g("span");
			}
		}
	}

	public void htmAnneeCle(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SaisonScolaireAnneeCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/annee?fq=saisonCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-orange w3-hover-orange ").f();
								e("i").a("class", "far fa-calendar-check ").f().g("i");
								sx("année");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier une année a cette saison");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSaisonScolaireAnneeCle_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), AnneeScolaire.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), AnneeScolaire.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
											.a("id", classeApiMethodeMethode, "_anneeCle_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postAnneeScolaireVals({ saisonCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "anneeCle')); });")
											.f().sx("ajouter une année")
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
	// sessionCles //
	/////////////////

	/**	 L'entité sessionCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> sessionCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> sessionClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("sessionCles").o(sessionCles);

	/**	<br/> L'entité sessionCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCles">Trouver l'entité sessionCles dans Solr</a>
	 * <br/>
	 * @param sessionCles est l'entité déjà construit. 
	 **/
	protected abstract void _sessionCles(List<Long> o);

	public List<Long> getSessionCles() {
		return sessionCles;
	}

	public void setSessionCles(List<Long> sessionCles) {
		this.sessionCles = sessionCles;
		this.sessionClesCouverture.dejaInitialise = true;
	}
	public void setSessionCles(String o) {
		Long l = SaisonScolaire.staticSetSessionCles(requeteSite_, o);
		if(l != null)
			addSessionCles(l);
		this.sessionClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetSessionCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SaisonScolaire addSessionCles(Long...objets) {
		for(Long o : objets) {
			addSessionCles(o);
		}
		return (SaisonScolaire)this;
	}
	public SaisonScolaire addSessionCles(Long o) {
		if(o != null && !sessionCles.contains(o))
			this.sessionCles.add(o);
		return (SaisonScolaire)this;
	}
	public void setSessionCles(JsonArray objets) {
		sessionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionCles(o);
		}
	}
	public SaisonScolaire addSessionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionCles(p);
		}
		return (SaisonScolaire)this;
	}
	protected SaisonScolaire sessionClesInit() {
		if(!sessionClesCouverture.dejaInitialise) {
			_sessionCles(sessionCles);
		}
		sessionClesCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Long staticSolrSessionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionCles(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrSessionCles(requeteSite_, SaisonScolaire.staticSolrSessionCles(requeteSite_, SaisonScolaire.staticSetSessionCles(requeteSite_, o)));
	}

	public List<Long> solrSessionCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : sessionCles) {
			l.add(SaisonScolaire.staticSolrSessionCles(requeteSite_, o));
		}
		return l;
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

	public void inputSessionCles(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "sessions")
					.a("title", "Les sessions scolaires de la saison scolaire. ")
					.a("class", "valeur suggereSessionCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setSessionCles")
					.a("id", classeApiMethodeMethode, "_sessionCles")
					.a("autocomplete", "off");
					if("Page".equals(classeApiMethodeMethode)) {
						a("oninput", "suggereSaisonScolaireSessionCles($(this).val() ? rechercherSessionScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'saisonCle:" + pk + "'}", "], $('#listSaisonScolaireSessionCles_", classeApiMethodeMethode, "'), ", pk, "); ");
					}

				fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varSaisonScolaire", pk, "SessionCles ").f().sx(htmSessionCles()).g("span");
			}
		}
	}

	public void htmSessionCles(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SaisonScolaireSessionCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/session?fq=saisonCle:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "fad fa-graduation-cap ").f().g("i");
								sx("sessions");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette saison");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputSessionCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSaisonScolaireSessionCles_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), SessionScolaire.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), SessionScolaire.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
											.a("id", classeApiMethodeMethode, "_sessionCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postSessionScolaireVals({ saisonCle: \"", pk, "\" }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "sessionCles')); });")
											.f().sx("ajouter une session")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:scolaireTri">Trouver l'entité scolaireTri dans Solr</a>
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
		this.scolaireTri = SaisonScolaire.staticSetScolaireTri(requeteSite_, o);
		this.scolaireTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetScolaireTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SaisonScolaire scolaireTriInit() {
		if(!scolaireTriCouverture.dejaInitialise) {
			_scolaireTri(scolaireTriCouverture);
			if(scolaireTri == null)
				setScolaireTri(scolaireTriCouverture.o);
		}
		scolaireTriCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Integer staticSolrScolaireTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrScolaireTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqScolaireTri(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrScolaireTri(requeteSite_, SaisonScolaire.staticSolrScolaireTri(requeteSite_, SaisonScolaire.staticSetScolaireTri(requeteSite_, o)));
	}

	public Integer solrScolaireTri() {
		return SaisonScolaire.staticSolrScolaireTri(requeteSite_, scolaireTri);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleTri">Trouver l'entité ecoleTri dans Solr</a>
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
		this.ecoleTri = SaisonScolaire.staticSetEcoleTri(requeteSite_, o);
		this.ecoleTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetEcoleTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SaisonScolaire ecoleTriInit() {
		if(!ecoleTriCouverture.dejaInitialise) {
			_ecoleTri(ecoleTriCouverture);
			if(ecoleTri == null)
				setEcoleTri(ecoleTriCouverture.o);
		}
		ecoleTriCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Integer staticSolrEcoleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrEcoleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleTri(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrEcoleTri(requeteSite_, SaisonScolaire.staticSolrEcoleTri(requeteSite_, SaisonScolaire.staticSetEcoleTri(requeteSite_, o)));
	}

	public Integer solrEcoleTri() {
		return SaisonScolaire.staticSolrEcoleTri(requeteSite_, ecoleTri);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeTri">Trouver l'entité anneeTri dans Solr</a>
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
		this.anneeTri = SaisonScolaire.staticSetAnneeTri(requeteSite_, o);
		this.anneeTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAnneeTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SaisonScolaire anneeTriInit() {
		if(!anneeTriCouverture.dejaInitialise) {
			_anneeTri(anneeTriCouverture);
			if(anneeTri == null)
				setAnneeTri(anneeTriCouverture.o);
		}
		anneeTriCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Integer staticSolrAnneeTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAnneeTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeTri(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrAnneeTri(requeteSite_, SaisonScolaire.staticSolrAnneeTri(requeteSite_, SaisonScolaire.staticSetAnneeTri(requeteSite_, o)));
	}

	public Integer solrAnneeTri() {
		return SaisonScolaire.staticSolrAnneeTri(requeteSite_, anneeTri);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonTri">Trouver l'entité saisonTri dans Solr</a>
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
		this.saisonTri = SaisonScolaire.staticSetSaisonTri(requeteSite_, o);
		this.saisonTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetSaisonTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SaisonScolaire saisonTriInit() {
		if(!saisonTriCouverture.dejaInitialise) {
			_saisonTri(saisonTriCouverture);
			if(saisonTri == null)
				setSaisonTri(saisonTriCouverture.o);
		}
		saisonTriCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Integer staticSolrSaisonTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrSaisonTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonTri(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrSaisonTri(requeteSite_, SaisonScolaire.staticSolrSaisonTri(requeteSite_, SaisonScolaire.staticSetSaisonTri(requeteSite_, o)));
	}

	public Integer solrSaisonTri() {
		return SaisonScolaire.staticSolrSaisonTri(requeteSite_, saisonTri);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeRecherche">Trouver l'entité anneeRecherche dans Solr</a>
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
	protected SaisonScolaire anneeRechercheInit() {
		if(!anneeRechercheCouverture.dejaInitialise) {
			_anneeRecherche(anneeRecherche);
		}
		anneeRecherche.initLoinPourClasse(requeteSite_);
		anneeRechercheCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:annee_">Trouver l'entité annee_ dans Solr</a>
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
	protected SaisonScolaire annee_Init() {
		if(!annee_Couverture.dejaInitialise) {
			_annee_(annee_Couverture);
			if(annee_ == null)
				setAnnee_(annee_Couverture.o);
		}
		annee_Couverture.dejaInitialise(true);
		return (SaisonScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
		this.ecoleCle = SaisonScolaire.staticSetEcoleCle(requeteSite_, o);
		this.ecoleCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetEcoleCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SaisonScolaire ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Long staticSolrEcoleCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrEcoleCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleCle(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrEcoleCle(requeteSite_, SaisonScolaire.staticSolrEcoleCle(requeteSite_, SaisonScolaire.staticSetEcoleCle(requeteSite_, o)));
	}

	public Long solrEcoleCle() {
		return SaisonScolaire.staticSolrEcoleCle(requeteSite_, ecoleCle);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNom(Couverture<String> c);

	public String getEcoleNom() {
		return ecoleNom;
	}
	public void setEcoleNom(String o) {
		this.ecoleNom = SaisonScolaire.staticSetEcoleNom(requeteSite_, o);
		this.ecoleNomCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SaisonScolaire ecoleNomInit() {
		if(!ecoleNomCouverture.dejaInitialise) {
			_ecoleNom(ecoleNomCouverture);
			if(ecoleNom == null)
				setEcoleNom(ecoleNomCouverture.o);
		}
		ecoleNomCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static String staticSolrEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNom(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrEcoleNom(requeteSite_, SaisonScolaire.staticSolrEcoleNom(requeteSite_, SaisonScolaire.staticSetEcoleNom(requeteSite_, o)));
	}

	public String solrEcoleNom() {
		return SaisonScolaire.staticSolrEcoleNom(requeteSite_, ecoleNom);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNomComplet(Couverture<String> c);

	public String getEcoleNomComplet() {
		return ecoleNomComplet;
	}
	public void setEcoleNomComplet(String o) {
		this.ecoleNomComplet = SaisonScolaire.staticSetEcoleNomComplet(requeteSite_, o);
		this.ecoleNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SaisonScolaire ecoleNomCompletInit() {
		if(!ecoleNomCompletCouverture.dejaInitialise) {
			_ecoleNomComplet(ecoleNomCompletCouverture);
			if(ecoleNomComplet == null)
				setEcoleNomComplet(ecoleNomCompletCouverture.o);
		}
		ecoleNomCompletCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static String staticSolrEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrEcoleNomComplet(requeteSite_, SaisonScolaire.staticSolrEcoleNomComplet(requeteSite_, SaisonScolaire.staticSetEcoleNomComplet(requeteSite_, o)));
	}

	public String solrEcoleNomComplet() {
		return SaisonScolaire.staticSolrEcoleNomComplet(requeteSite_, ecoleNomComplet);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleEmplacement">Trouver l'entité ecoleEmplacement dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleEmplacement(Couverture<String> c);

	public String getEcoleEmplacement() {
		return ecoleEmplacement;
	}
	public void setEcoleEmplacement(String o) {
		this.ecoleEmplacement = SaisonScolaire.staticSetEcoleEmplacement(requeteSite_, o);
		this.ecoleEmplacementCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SaisonScolaire ecoleEmplacementInit() {
		if(!ecoleEmplacementCouverture.dejaInitialise) {
			_ecoleEmplacement(ecoleEmplacementCouverture);
			if(ecoleEmplacement == null)
				setEcoleEmplacement(ecoleEmplacementCouverture.o);
		}
		ecoleEmplacementCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static String staticSolrEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleEmplacement(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrEcoleEmplacement(requeteSite_, SaisonScolaire.staticSolrEcoleEmplacement(requeteSite_, SaisonScolaire.staticSetEcoleEmplacement(requeteSite_, o)));
	}

	public String solrEcoleEmplacement() {
		return SaisonScolaire.staticSolrEcoleEmplacement(requeteSite_, ecoleEmplacement);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAddresse(Couverture<String> c);

	public String getEcoleAddresse() {
		return ecoleAddresse;
	}
	public void setEcoleAddresse(String o) {
		this.ecoleAddresse = SaisonScolaire.staticSetEcoleAddresse(requeteSite_, o);
		this.ecoleAddresseCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SaisonScolaire ecoleAddresseInit() {
		if(!ecoleAddresseCouverture.dejaInitialise) {
			_ecoleAddresse(ecoleAddresseCouverture);
			if(ecoleAddresse == null)
				setEcoleAddresse(ecoleAddresseCouverture.o);
		}
		ecoleAddresseCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static String staticSolrEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleAddresse(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrEcoleAddresse(requeteSite_, SaisonScolaire.staticSolrEcoleAddresse(requeteSite_, SaisonScolaire.staticSetEcoleAddresse(requeteSite_, o)));
	}

	public String solrEcoleAddresse() {
		return SaisonScolaire.staticSolrEcoleAddresse(requeteSite_, ecoleAddresse);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleNumeroTelephone(Couverture<String> c);

	public String getEcoleNumeroTelephone() {
		return ecoleNumeroTelephone;
	}
	public void setEcoleNumeroTelephone(String o) {
		this.ecoleNumeroTelephone = SaisonScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o);
		this.ecoleNumeroTelephoneCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SaisonScolaire ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneCouverture.dejaInitialise) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneCouverture);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneCouverture.o);
		}
		ecoleNumeroTelephoneCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static String staticSolrEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrEcoleNumeroTelephone(requeteSite_, SaisonScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, SaisonScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o)));
	}

	public String solrEcoleNumeroTelephone() {
		return SaisonScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, ecoleNumeroTelephone);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleForm">Trouver l'entité ecoleForm dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleForm(Couverture<String> c);

	public String getEcoleForm() {
		return ecoleForm;
	}
	public void setEcoleForm(String o) {
		this.ecoleForm = SaisonScolaire.staticSetEcoleForm(requeteSite_, o);
		this.ecoleFormCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SaisonScolaire ecoleFormInit() {
		if(!ecoleFormCouverture.dejaInitialise) {
			_ecoleForm(ecoleFormCouverture);
			if(ecoleForm == null)
				setEcoleForm(ecoleFormCouverture.o);
		}
		ecoleFormCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static String staticSolrEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleForm(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrEcoleForm(requeteSite_, SaisonScolaire.staticSolrEcoleForm(requeteSite_, SaisonScolaire.staticSetEcoleForm(requeteSite_, o)));
	}

	public String solrEcoleForm() {
		return SaisonScolaire.staticSolrEcoleForm(requeteSite_, ecoleForm);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumero">Trouver l'entité ecoleNumero dans Solr</a>
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
		this.ecoleNumero = SaisonScolaire.staticSetEcoleNumero(requeteSite_, o);
		this.ecoleNumeroCouverture.dejaInitialise = true;
	}
	public static Integer staticSetEcoleNumero(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SaisonScolaire ecoleNumeroInit() {
		if(!ecoleNumeroCouverture.dejaInitialise) {
			_ecoleNumero(ecoleNumeroCouverture);
			if(ecoleNumero == null)
				setEcoleNumero(ecoleNumeroCouverture.o);
		}
		ecoleNumeroCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Integer staticSolrEcoleNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrEcoleNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleNumero(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrEcoleNumero(requeteSite_, SaisonScolaire.staticSolrEcoleNumero(requeteSite_, SaisonScolaire.staticSetEcoleNumero(requeteSite_, o)));
	}

	public Integer solrEcoleNumero() {
		return SaisonScolaire.staticSolrEcoleNumero(requeteSite_, ecoleNumero);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAdministrateurNom">Trouver l'entité ecoleAdministrateurNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ecoleAdministrateurNom(Couverture<String> c);

	public String getEcoleAdministrateurNom() {
		return ecoleAdministrateurNom;
	}
	public void setEcoleAdministrateurNom(String o) {
		this.ecoleAdministrateurNom = SaisonScolaire.staticSetEcoleAdministrateurNom(requeteSite_, o);
		this.ecoleAdministrateurNomCouverture.dejaInitialise = true;
	}
	public static String staticSetEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SaisonScolaire ecoleAdministrateurNomInit() {
		if(!ecoleAdministrateurNomCouverture.dejaInitialise) {
			_ecoleAdministrateurNom(ecoleAdministrateurNomCouverture);
			if(ecoleAdministrateurNom == null)
				setEcoleAdministrateurNom(ecoleAdministrateurNomCouverture.o);
		}
		ecoleAdministrateurNomCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static String staticSolrEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleAdministrateurNom(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrEcoleAdministrateurNom(requeteSite_, SaisonScolaire.staticSolrEcoleAdministrateurNom(requeteSite_, SaisonScolaire.staticSetEcoleAdministrateurNom(requeteSite_, o)));
	}

	public String solrEcoleAdministrateurNom() {
		return SaisonScolaire.staticSolrEcoleAdministrateurNom(requeteSite_, ecoleAdministrateurNom);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
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
		this.anneeDebut = SaisonScolaire.staticSetAnneeDebut(requeteSite_, o);
		this.anneeDebutCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAnneeDebut(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SaisonScolaire anneeDebutInit() {
		if(!anneeDebutCouverture.dejaInitialise) {
			_anneeDebut(anneeDebutCouverture);
			if(anneeDebut == null)
				setAnneeDebut(anneeDebutCouverture.o);
		}
		anneeDebutCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Integer staticSolrAnneeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAnneeDebut(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeDebut(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrAnneeDebut(requeteSite_, SaisonScolaire.staticSolrAnneeDebut(requeteSite_, SaisonScolaire.staticSetAnneeDebut(requeteSite_, o)));
	}

	public Integer solrAnneeDebut() {
		return SaisonScolaire.staticSolrAnneeDebut(requeteSite_, anneeDebut);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
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
		this.anneeFin = SaisonScolaire.staticSetAnneeFin(requeteSite_, o);
		this.anneeFinCouverture.dejaInitialise = true;
	}
	public static Integer staticSetAnneeFin(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SaisonScolaire anneeFinInit() {
		if(!anneeFinCouverture.dejaInitialise) {
			_anneeFin(anneeFinCouverture);
			if(anneeFin == null)
				setAnneeFin(anneeFinCouverture.o);
		}
		anneeFinCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Integer staticSolrAnneeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrAnneeFin(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeFin(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrAnneeFin(requeteSite_, SaisonScolaire.staticSolrAnneeFin(requeteSite_, SaisonScolaire.staticSetAnneeFin(requeteSite_, o)));
	}

	public Integer solrAnneeFin() {
		return SaisonScolaire.staticSolrAnneeFin(requeteSite_, anneeFin);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFraisInscription">Trouver l'entité anneeFraisInscription dans Solr</a>
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
		this.anneeFraisInscription = SaisonScolaire.staticSetAnneeFraisInscription(requeteSite_, o);
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
	protected SaisonScolaire anneeFraisInscriptionInit() {
		if(!anneeFraisInscriptionCouverture.dejaInitialise) {
			_anneeFraisInscription(anneeFraisInscriptionCouverture);
			if(anneeFraisInscription == null)
				setAnneeFraisInscription(anneeFraisInscriptionCouverture.o);
		}
		anneeFraisInscriptionCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Double staticSolrAnneeFraisInscription(RequeteSiteFrFR requeteSite_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrAnneeFraisInscription(RequeteSiteFrFR requeteSite_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeFraisInscription(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrAnneeFraisInscription(requeteSite_, SaisonScolaire.staticSolrAnneeFraisInscription(requeteSite_, SaisonScolaire.staticSetAnneeFraisInscription(requeteSite_, o)));
	}

	public Double solrAnneeFraisInscription() {
		return SaisonScolaire.staticSolrAnneeFraisInscription(requeteSite_, anneeFraisInscription);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonDateDebut">Trouver l'entité saisonDateDebut dans Solr</a>
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
		this.saisonDateDebut = SaisonScolaire.staticSetSaisonDateDebut(requeteSite_, o);
		this.saisonDateDebutCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetSaisonDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSaisonDateDebut(Date o) {
		this.saisonDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.saisonDateDebutCouverture.dejaInitialise = true;
	}
	protected SaisonScolaire saisonDateDebutInit() {
		if(!saisonDateDebutCouverture.dejaInitialise) {
			_saisonDateDebut(saisonDateDebutCouverture);
			if(saisonDateDebut == null)
				setSaisonDateDebut(saisonDateDebutCouverture.o);
		}
		saisonDateDebutCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Date staticSolrSaisonDateDebut(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSaisonDateDebut(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSaisonDateDebut(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrSaisonDateDebut(requeteSite_, SaisonScolaire.staticSolrSaisonDateDebut(requeteSite_, SaisonScolaire.staticSetSaisonDateDebut(requeteSite_, o)));
	}

	public Date solrSaisonDateDebut() {
		return SaisonScolaire.staticSolrSaisonDateDebut(requeteSite_, saisonDateDebut);
	}

	public String strSaisonDateDebut() {
		return saisonDateDebut == null ? "" : saisonDateDebut.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonSaisonDateDebut() {
		return saisonDateDebut == null ? "" : saisonDateDebut.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSaisonDateDebut() {
		return "début de la saison";
	}

	public String htmTooltipSaisonDateDebut() {
		return null;
	}

	public String htmSaisonDateDebut() {
		return saisonDateDebut == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonDateDebut());
	}

	public void inputSaisonDateDebut(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setSaisonDateDebut classSaisonScolaire inputSaisonScolaire", pk, "SaisonDateDebut w3-input w3-border ")
					.a("placeholder", "DD-MM-YYYY")
					.a("data-timeformat", "dd-MM-yyyy")
					.a("id", classeApiMethodeMethode, "_saisonDateDebut")
					.a("title", "Les sessions scolaires de la saison scolaire.  (DD-MM-YYYY)")
					.a("value", saisonDateDebut == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(saisonDateDebut));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSaisonDateDebut', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_saisonDateDebut')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_saisonDateDebut')); }); } ");
			}
			fg();
		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varSaisonScolaire", pk, "SaisonDateDebut ").f().sx(htmSaisonDateDebut()).g("span");
			}
		}
	}

	public void htmSaisonDateDebut(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SaisonScolaireSaisonDateDebut").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_saisonDateDebut").a("class", "").f().sx("début de la saison").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputSaisonDateDebut(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_saisonDateDebut')); $('#", classeApiMethodeMethode, "_saisonDateDebut').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=pk]').val() }], 'setSaisonDateDebut', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_saisonDateDebut')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_saisonDateDebut')); }); ")
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

	///////////////
	// saisonEte //
	///////////////

	/**	 L'entité saisonEte
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean saisonEte;
	@JsonIgnore
	public Couverture<Boolean> saisonEteCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonEte").o(saisonEte);

	/**	<br/> L'entité saisonEte
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonEte">Trouver l'entité saisonEte dans Solr</a>
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
	public void setSaisonEte(String o) {
		this.saisonEte = SaisonScolaire.staticSetSaisonEte(requeteSite_, o);
		this.saisonEteCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetSaisonEte(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SaisonScolaire saisonEteInit() {
		if(!saisonEteCouverture.dejaInitialise) {
			_saisonEte(saisonEteCouverture);
			if(saisonEte == null)
				setSaisonEte(saisonEteCouverture.o);
		}
		saisonEteCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Boolean staticSolrSaisonEte(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrSaisonEte(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonEte(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrSaisonEte(requeteSite_, SaisonScolaire.staticSolrSaisonEte(requeteSite_, SaisonScolaire.staticSetSaisonEte(requeteSite_, o)));
	}

	public Boolean solrSaisonEte() {
		return SaisonScolaire.staticSolrSaisonEte(requeteSite_, saisonEte);
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

	public void inputSaisonEte(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_saisonEte")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_saisonEte");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setSaisonEte classSaisonScolaire inputSaisonScolaire", pk, "SaisonEte w3-input w3-border ");
				a("name", "setSaisonEte");
			} else {
				a("class", "valeurSaisonEte classSaisonScolaire inputSaisonScolaire", pk, "SaisonEte w3-input w3-border ");
				a("name", "saisonEte");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSaisonEte', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_saisonEte')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_saisonEte')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getSaisonEte() != null && getSaisonEte())
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
				e("span").a("class", "varSaisonScolaire", pk, "SaisonEte ").f().sx(htmSaisonEte()).g("span");
			}
		}
	}

	public void htmSaisonEte(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SaisonScolaireSaisonEte").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_saisonEte").a("class", "").f().sx("été").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSaisonEte(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// saisonHiver //
	/////////////////

	/**	 L'entité saisonHiver
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean saisonHiver;
	@JsonIgnore
	public Couverture<Boolean> saisonHiverCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonHiver").o(saisonHiver);

	/**	<br/> L'entité saisonHiver
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonHiver">Trouver l'entité saisonHiver dans Solr</a>
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
	public void setSaisonHiver(String o) {
		this.saisonHiver = SaisonScolaire.staticSetSaisonHiver(requeteSite_, o);
		this.saisonHiverCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetSaisonHiver(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SaisonScolaire saisonHiverInit() {
		if(!saisonHiverCouverture.dejaInitialise) {
			_saisonHiver(saisonHiverCouverture);
			if(saisonHiver == null)
				setSaisonHiver(saisonHiverCouverture.o);
		}
		saisonHiverCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Boolean staticSolrSaisonHiver(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrSaisonHiver(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonHiver(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrSaisonHiver(requeteSite_, SaisonScolaire.staticSolrSaisonHiver(requeteSite_, SaisonScolaire.staticSetSaisonHiver(requeteSite_, o)));
	}

	public Boolean solrSaisonHiver() {
		return SaisonScolaire.staticSolrSaisonHiver(requeteSite_, saisonHiver);
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

	public void inputSaisonHiver(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_saisonHiver")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_saisonHiver");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setSaisonHiver classSaisonScolaire inputSaisonScolaire", pk, "SaisonHiver w3-input w3-border ");
				a("name", "setSaisonHiver");
			} else {
				a("class", "valeurSaisonHiver classSaisonScolaire inputSaisonScolaire", pk, "SaisonHiver w3-input w3-border ");
				a("name", "saisonHiver");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSaisonHiver', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_saisonHiver')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_saisonHiver')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getSaisonHiver() != null && getSaisonHiver())
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
				e("span").a("class", "varSaisonScolaire", pk, "SaisonHiver ").f().sx(htmSaisonHiver()).g("span");
			}
		}
	}

	public void htmSaisonHiver(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SaisonScolaireSaisonHiver").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_saisonHiver").a("class", "").f().sx("hiver").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSaisonHiver(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// saisonFuture //
	//////////////////

	/**	 L'entité saisonFuture
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean saisonFuture;
	@JsonIgnore
	public Couverture<Boolean> saisonFutureCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonFuture").o(saisonFuture);

	/**	<br/> L'entité saisonFuture
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonFuture">Trouver l'entité saisonFuture dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonFuture(Couverture<Boolean> c);

	public Boolean getSaisonFuture() {
		return saisonFuture;
	}

	public void setSaisonFuture(Boolean saisonFuture) {
		this.saisonFuture = saisonFuture;
		this.saisonFutureCouverture.dejaInitialise = true;
	}
	public void setSaisonFuture(String o) {
		this.saisonFuture = SaisonScolaire.staticSetSaisonFuture(requeteSite_, o);
		this.saisonFutureCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetSaisonFuture(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SaisonScolaire saisonFutureInit() {
		if(!saisonFutureCouverture.dejaInitialise) {
			_saisonFuture(saisonFutureCouverture);
			if(saisonFuture == null)
				setSaisonFuture(saisonFutureCouverture.o);
		}
		saisonFutureCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static Boolean staticSolrSaisonFuture(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrSaisonFuture(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonFuture(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrSaisonFuture(requeteSite_, SaisonScolaire.staticSolrSaisonFuture(requeteSite_, SaisonScolaire.staticSetSaisonFuture(requeteSite_, o)));
	}

	public Boolean solrSaisonFuture() {
		return SaisonScolaire.staticSolrSaisonFuture(requeteSite_, saisonFuture);
	}

	public String strSaisonFuture() {
		return saisonFuture == null ? "" : saisonFuture.toString();
	}

	public String jsonSaisonFuture() {
		return saisonFuture == null ? "" : saisonFuture.toString();
	}

	public String nomAffichageSaisonFuture() {
		return "saison future";
	}

	public String htmTooltipSaisonFuture() {
		return null;
	}

	public String htmSaisonFuture() {
		return saisonFuture == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonFuture());
	}

	public void inputSaisonFuture(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_saisonFuture")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_saisonFuture");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setSaisonFuture classSaisonScolaire inputSaisonScolaire", pk, "SaisonFuture w3-input w3-border ");
				a("name", "setSaisonFuture");
			} else {
				a("class", "valeurSaisonFuture classSaisonScolaire inputSaisonScolaire", pk, "SaisonFuture w3-input w3-border ");
				a("name", "saisonFuture");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSaisonFuture', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_saisonFuture')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_saisonFuture')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getSaisonFuture() != null && getSaisonFuture())
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
				e("span").a("class", "varSaisonScolaire", pk, "SaisonFuture ").f().sx(htmSaisonFuture()).g("span");
			}
		}
	}

	public void htmSaisonFuture(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SaisonScolaireSaisonFuture").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_saisonFuture").a("class", "").f().sx("saison future").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSaisonFuture(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////
	// saisonNomCourt //
	////////////////////

	/**	 L'entité saisonNomCourt
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String saisonNomCourt;
	@JsonIgnore
	public Couverture<String> saisonNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("saisonNomCourt").o(saisonNomCourt);

	/**	<br/> L'entité saisonNomCourt
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonNomCourt">Trouver l'entité saisonNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonNomCourt(Couverture<String> c);

	public String getSaisonNomCourt() {
		return saisonNomCourt;
	}
	public void setSaisonNomCourt(String o) {
		this.saisonNomCourt = SaisonScolaire.staticSetSaisonNomCourt(requeteSite_, o);
		this.saisonNomCourtCouverture.dejaInitialise = true;
	}
	public static String staticSetSaisonNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SaisonScolaire saisonNomCourtInit() {
		if(!saisonNomCourtCouverture.dejaInitialise) {
			_saisonNomCourt(saisonNomCourtCouverture);
			if(saisonNomCourt == null)
				setSaisonNomCourt(saisonNomCourtCouverture.o);
		}
		saisonNomCourtCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static String staticSolrSaisonNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrSaisonNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonNomCourt(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrSaisonNomCourt(requeteSite_, SaisonScolaire.staticSolrSaisonNomCourt(requeteSite_, SaisonScolaire.staticSetSaisonNomCourt(requeteSite_, o)));
	}

	public String solrSaisonNomCourt() {
		return SaisonScolaire.staticSolrSaisonNomCourt(requeteSite_, saisonNomCourt);
	}

	public String strSaisonNomCourt() {
		return saisonNomCourt == null ? "" : saisonNomCourt;
	}

	public String jsonSaisonNomCourt() {
		return saisonNomCourt == null ? "" : saisonNomCourt;
	}

	public String nomAffichageSaisonNomCourt() {
		return null;
	}

	public String htmTooltipSaisonNomCourt() {
		return null;
	}

	public String htmSaisonNomCourt() {
		return saisonNomCourt == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonNomCourt());
	}

	//////////////////////
	// saisonNomComplet //
	//////////////////////

	/**	 L'entité saisonNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String saisonNomComplet;
	@JsonIgnore
	public Couverture<String> saisonNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("saisonNomComplet").o(saisonNomComplet);

	/**	<br/> L'entité saisonNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonNomComplet">Trouver l'entité saisonNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonNomComplet(Couverture<String> c);

	public String getSaisonNomComplet() {
		return saisonNomComplet;
	}
	public void setSaisonNomComplet(String o) {
		this.saisonNomComplet = SaisonScolaire.staticSetSaisonNomComplet(requeteSite_, o);
		this.saisonNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetSaisonNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected SaisonScolaire saisonNomCompletInit() {
		if(!saisonNomCompletCouverture.dejaInitialise) {
			_saisonNomComplet(saisonNomCompletCouverture);
			if(saisonNomComplet == null)
				setSaisonNomComplet(saisonNomCompletCouverture.o);
		}
		saisonNomCompletCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public static String staticSolrSaisonNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrSaisonNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return SaisonScolaire.staticSolrStrSaisonNomComplet(requeteSite_, SaisonScolaire.staticSolrSaisonNomComplet(requeteSite_, SaisonScolaire.staticSetSaisonNomComplet(requeteSite_, o)));
	}

	public String solrSaisonNomComplet() {
		return SaisonScolaire.staticSolrSaisonNomComplet(requeteSite_, saisonNomComplet);
	}

	public String strSaisonNomComplet() {
		return saisonNomComplet == null ? "" : saisonNomComplet;
	}

	public String jsonSaisonNomComplet() {
		return saisonNomComplet == null ? "" : saisonNomComplet;
	}

	public String nomAffichageSaisonNomComplet() {
		return "nom";
	}

	public String htmTooltipSaisonNomComplet() {
		return null;
	}

	public String htmSaisonNomComplet() {
		return saisonNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonNomComplet());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseSaisonScolaire = false;

	public SaisonScolaire initLoinSaisonScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseSaisonScolaire) {
			dejaInitialiseSaisonScolaire = true;
			initLoinSaisonScolaire();
		}
		return (SaisonScolaire)this;
	}

	public void initLoinSaisonScolaire() {
		initSaisonScolaire();
		super.initLoinCluster(requeteSite_);
	}

	public void initSaisonScolaire() {
		saisonCleInit();
		inscriptionClesInit();
		anneeCleInit();
		sessionClesInit();
		scolaireTriInit();
		ecoleTriInit();
		anneeTriInit();
		saisonTriInit();
		anneeRechercheInit();
		annee_Init();
		ecoleCleInit();
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
		anneeFraisInscriptionInit();
		saisonDateDebutInit();
		saisonEteInit();
		saisonHiverInit();
		saisonFutureInit();
		saisonNomCourtInit();
		saisonNomCompletInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinSaisonScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteSaisonScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(anneeRecherche != null)
			anneeRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteSaisonScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSaisonScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirSaisonScolaire(String var) {
		SaisonScolaire oSaisonScolaire = (SaisonScolaire)this;
		switch(var) {
			case "saisonCle":
				return oSaisonScolaire.saisonCle;
			case "inscriptionCles":
				return oSaisonScolaire.inscriptionCles;
			case "anneeCle":
				return oSaisonScolaire.anneeCle;
			case "sessionCles":
				return oSaisonScolaire.sessionCles;
			case "scolaireTri":
				return oSaisonScolaire.scolaireTri;
			case "ecoleTri":
				return oSaisonScolaire.ecoleTri;
			case "anneeTri":
				return oSaisonScolaire.anneeTri;
			case "saisonTri":
				return oSaisonScolaire.saisonTri;
			case "anneeRecherche":
				return oSaisonScolaire.anneeRecherche;
			case "annee_":
				return oSaisonScolaire.annee_;
			case "ecoleCle":
				return oSaisonScolaire.ecoleCle;
			case "ecoleNom":
				return oSaisonScolaire.ecoleNom;
			case "ecoleNomComplet":
				return oSaisonScolaire.ecoleNomComplet;
			case "ecoleEmplacement":
				return oSaisonScolaire.ecoleEmplacement;
			case "ecoleAddresse":
				return oSaisonScolaire.ecoleAddresse;
			case "ecoleNumeroTelephone":
				return oSaisonScolaire.ecoleNumeroTelephone;
			case "ecoleForm":
				return oSaisonScolaire.ecoleForm;
			case "ecoleNumero":
				return oSaisonScolaire.ecoleNumero;
			case "ecoleAdministrateurNom":
				return oSaisonScolaire.ecoleAdministrateurNom;
			case "anneeDebut":
				return oSaisonScolaire.anneeDebut;
			case "anneeFin":
				return oSaisonScolaire.anneeFin;
			case "anneeFraisInscription":
				return oSaisonScolaire.anneeFraisInscription;
			case "saisonDateDebut":
				return oSaisonScolaire.saisonDateDebut;
			case "saisonEte":
				return oSaisonScolaire.saisonEte;
			case "saisonHiver":
				return oSaisonScolaire.saisonHiver;
			case "saisonFuture":
				return oSaisonScolaire.saisonFuture;
			case "saisonNomCourt":
				return oSaisonScolaire.saisonNomCourt;
			case "saisonNomComplet":
				return oSaisonScolaire.saisonNomComplet;
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
				o = attribuerSaisonScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSaisonScolaire(String var, Object val) {
		SaisonScolaire oSaisonScolaire = (SaisonScolaire)this;
		switch(var) {
			case "anneeCle":
				if(oSaisonScolaire.getAnneeCle() == null)
					oSaisonScolaire.setAnneeCle((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "sessionCles":
				oSaisonScolaire.addSessionCles((Long)val);
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
		return staticSetSaisonScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetSaisonScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "saisonCle":
			return SaisonScolaire.staticSetSaisonCle(requeteSite_, o);
		case "inscriptionCles":
			return SaisonScolaire.staticSetInscriptionCles(requeteSite_, o);
		case "anneeCle":
			return SaisonScolaire.staticSetAnneeCle(requeteSite_, o);
		case "sessionCles":
			return SaisonScolaire.staticSetSessionCles(requeteSite_, o);
		case "scolaireTri":
			return SaisonScolaire.staticSetScolaireTri(requeteSite_, o);
		case "ecoleTri":
			return SaisonScolaire.staticSetEcoleTri(requeteSite_, o);
		case "anneeTri":
			return SaisonScolaire.staticSetAnneeTri(requeteSite_, o);
		case "saisonTri":
			return SaisonScolaire.staticSetSaisonTri(requeteSite_, o);
		case "ecoleCle":
			return SaisonScolaire.staticSetEcoleCle(requeteSite_, o);
		case "ecoleNom":
			return SaisonScolaire.staticSetEcoleNom(requeteSite_, o);
		case "ecoleNomComplet":
			return SaisonScolaire.staticSetEcoleNomComplet(requeteSite_, o);
		case "ecoleEmplacement":
			return SaisonScolaire.staticSetEcoleEmplacement(requeteSite_, o);
		case "ecoleAddresse":
			return SaisonScolaire.staticSetEcoleAddresse(requeteSite_, o);
		case "ecoleNumeroTelephone":
			return SaisonScolaire.staticSetEcoleNumeroTelephone(requeteSite_, o);
		case "ecoleForm":
			return SaisonScolaire.staticSetEcoleForm(requeteSite_, o);
		case "ecoleNumero":
			return SaisonScolaire.staticSetEcoleNumero(requeteSite_, o);
		case "ecoleAdministrateurNom":
			return SaisonScolaire.staticSetEcoleAdministrateurNom(requeteSite_, o);
		case "anneeDebut":
			return SaisonScolaire.staticSetAnneeDebut(requeteSite_, o);
		case "anneeFin":
			return SaisonScolaire.staticSetAnneeFin(requeteSite_, o);
		case "anneeFraisInscription":
			return SaisonScolaire.staticSetAnneeFraisInscription(requeteSite_, o);
		case "saisonDateDebut":
			return SaisonScolaire.staticSetSaisonDateDebut(requeteSite_, o);
		case "saisonEte":
			return SaisonScolaire.staticSetSaisonEte(requeteSite_, o);
		case "saisonHiver":
			return SaisonScolaire.staticSetSaisonHiver(requeteSite_, o);
		case "saisonFuture":
			return SaisonScolaire.staticSetSaisonFuture(requeteSite_, o);
		case "saisonNomCourt":
			return SaisonScolaire.staticSetSaisonNomCourt(requeteSite_, o);
		case "saisonNomComplet":
			return SaisonScolaire.staticSetSaisonNomComplet(requeteSite_, o);
			default:
				return Cluster.staticSetCluster(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrSaisonScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrSaisonScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "saisonCle":
			return SaisonScolaire.staticSolrSaisonCle(requeteSite_, (Long)o);
		case "inscriptionCles":
			return SaisonScolaire.staticSolrInscriptionCles(requeteSite_, (Long)o);
		case "anneeCle":
			return SaisonScolaire.staticSolrAnneeCle(requeteSite_, (Long)o);
		case "sessionCles":
			return SaisonScolaire.staticSolrSessionCles(requeteSite_, (Long)o);
		case "scolaireTri":
			return SaisonScolaire.staticSolrScolaireTri(requeteSite_, (Integer)o);
		case "ecoleTri":
			return SaisonScolaire.staticSolrEcoleTri(requeteSite_, (Integer)o);
		case "anneeTri":
			return SaisonScolaire.staticSolrAnneeTri(requeteSite_, (Integer)o);
		case "saisonTri":
			return SaisonScolaire.staticSolrSaisonTri(requeteSite_, (Integer)o);
		case "ecoleCle":
			return SaisonScolaire.staticSolrEcoleCle(requeteSite_, (Long)o);
		case "ecoleNom":
			return SaisonScolaire.staticSolrEcoleNom(requeteSite_, (String)o);
		case "ecoleNomComplet":
			return SaisonScolaire.staticSolrEcoleNomComplet(requeteSite_, (String)o);
		case "ecoleEmplacement":
			return SaisonScolaire.staticSolrEcoleEmplacement(requeteSite_, (String)o);
		case "ecoleAddresse":
			return SaisonScolaire.staticSolrEcoleAddresse(requeteSite_, (String)o);
		case "ecoleNumeroTelephone":
			return SaisonScolaire.staticSolrEcoleNumeroTelephone(requeteSite_, (String)o);
		case "ecoleForm":
			return SaisonScolaire.staticSolrEcoleForm(requeteSite_, (String)o);
		case "ecoleNumero":
			return SaisonScolaire.staticSolrEcoleNumero(requeteSite_, (Integer)o);
		case "ecoleAdministrateurNom":
			return SaisonScolaire.staticSolrEcoleAdministrateurNom(requeteSite_, (String)o);
		case "anneeDebut":
			return SaisonScolaire.staticSolrAnneeDebut(requeteSite_, (Integer)o);
		case "anneeFin":
			return SaisonScolaire.staticSolrAnneeFin(requeteSite_, (Integer)o);
		case "anneeFraisInscription":
			return SaisonScolaire.staticSolrAnneeFraisInscription(requeteSite_, (BigDecimal)o);
		case "saisonDateDebut":
			return SaisonScolaire.staticSolrSaisonDateDebut(requeteSite_, (LocalDate)o);
		case "saisonEte":
			return SaisonScolaire.staticSolrSaisonEte(requeteSite_, (Boolean)o);
		case "saisonHiver":
			return SaisonScolaire.staticSolrSaisonHiver(requeteSite_, (Boolean)o);
		case "saisonFuture":
			return SaisonScolaire.staticSolrSaisonFuture(requeteSite_, (Boolean)o);
		case "saisonNomCourt":
			return SaisonScolaire.staticSolrSaisonNomCourt(requeteSite_, (String)o);
		case "saisonNomComplet":
			return SaisonScolaire.staticSolrSaisonNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrCluster(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrSaisonScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrSaisonScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "saisonCle":
			return SaisonScolaire.staticSolrStrSaisonCle(requeteSite_, (Long)o);
		case "inscriptionCles":
			return SaisonScolaire.staticSolrStrInscriptionCles(requeteSite_, (Long)o);
		case "anneeCle":
			return SaisonScolaire.staticSolrStrAnneeCle(requeteSite_, (Long)o);
		case "sessionCles":
			return SaisonScolaire.staticSolrStrSessionCles(requeteSite_, (Long)o);
		case "scolaireTri":
			return SaisonScolaire.staticSolrStrScolaireTri(requeteSite_, (Integer)o);
		case "ecoleTri":
			return SaisonScolaire.staticSolrStrEcoleTri(requeteSite_, (Integer)o);
		case "anneeTri":
			return SaisonScolaire.staticSolrStrAnneeTri(requeteSite_, (Integer)o);
		case "saisonTri":
			return SaisonScolaire.staticSolrStrSaisonTri(requeteSite_, (Integer)o);
		case "ecoleCle":
			return SaisonScolaire.staticSolrStrEcoleCle(requeteSite_, (Long)o);
		case "ecoleNom":
			return SaisonScolaire.staticSolrStrEcoleNom(requeteSite_, (String)o);
		case "ecoleNomComplet":
			return SaisonScolaire.staticSolrStrEcoleNomComplet(requeteSite_, (String)o);
		case "ecoleEmplacement":
			return SaisonScolaire.staticSolrStrEcoleEmplacement(requeteSite_, (String)o);
		case "ecoleAddresse":
			return SaisonScolaire.staticSolrStrEcoleAddresse(requeteSite_, (String)o);
		case "ecoleNumeroTelephone":
			return SaisonScolaire.staticSolrStrEcoleNumeroTelephone(requeteSite_, (String)o);
		case "ecoleForm":
			return SaisonScolaire.staticSolrStrEcoleForm(requeteSite_, (String)o);
		case "ecoleNumero":
			return SaisonScolaire.staticSolrStrEcoleNumero(requeteSite_, (Integer)o);
		case "ecoleAdministrateurNom":
			return SaisonScolaire.staticSolrStrEcoleAdministrateurNom(requeteSite_, (String)o);
		case "anneeDebut":
			return SaisonScolaire.staticSolrStrAnneeDebut(requeteSite_, (Integer)o);
		case "anneeFin":
			return SaisonScolaire.staticSolrStrAnneeFin(requeteSite_, (Integer)o);
		case "anneeFraisInscription":
			return SaisonScolaire.staticSolrStrAnneeFraisInscription(requeteSite_, (Double)o);
		case "saisonDateDebut":
			return SaisonScolaire.staticSolrStrSaisonDateDebut(requeteSite_, (Date)o);
		case "saisonEte":
			return SaisonScolaire.staticSolrStrSaisonEte(requeteSite_, (Boolean)o);
		case "saisonHiver":
			return SaisonScolaire.staticSolrStrSaisonHiver(requeteSite_, (Boolean)o);
		case "saisonFuture":
			return SaisonScolaire.staticSolrStrSaisonFuture(requeteSite_, (Boolean)o);
		case "saisonNomCourt":
			return SaisonScolaire.staticSolrStrSaisonNomCourt(requeteSite_, (String)o);
		case "saisonNomComplet":
			return SaisonScolaire.staticSolrStrSaisonNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqSaisonScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqSaisonScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "saisonCle":
			return SaisonScolaire.staticSolrFqSaisonCle(requeteSite_, o);
		case "inscriptionCles":
			return SaisonScolaire.staticSolrFqInscriptionCles(requeteSite_, o);
		case "anneeCle":
			return SaisonScolaire.staticSolrFqAnneeCle(requeteSite_, o);
		case "sessionCles":
			return SaisonScolaire.staticSolrFqSessionCles(requeteSite_, o);
		case "scolaireTri":
			return SaisonScolaire.staticSolrFqScolaireTri(requeteSite_, o);
		case "ecoleTri":
			return SaisonScolaire.staticSolrFqEcoleTri(requeteSite_, o);
		case "anneeTri":
			return SaisonScolaire.staticSolrFqAnneeTri(requeteSite_, o);
		case "saisonTri":
			return SaisonScolaire.staticSolrFqSaisonTri(requeteSite_, o);
		case "ecoleCle":
			return SaisonScolaire.staticSolrFqEcoleCle(requeteSite_, o);
		case "ecoleNom":
			return SaisonScolaire.staticSolrFqEcoleNom(requeteSite_, o);
		case "ecoleNomComplet":
			return SaisonScolaire.staticSolrFqEcoleNomComplet(requeteSite_, o);
		case "ecoleEmplacement":
			return SaisonScolaire.staticSolrFqEcoleEmplacement(requeteSite_, o);
		case "ecoleAddresse":
			return SaisonScolaire.staticSolrFqEcoleAddresse(requeteSite_, o);
		case "ecoleNumeroTelephone":
			return SaisonScolaire.staticSolrFqEcoleNumeroTelephone(requeteSite_, o);
		case "ecoleForm":
			return SaisonScolaire.staticSolrFqEcoleForm(requeteSite_, o);
		case "ecoleNumero":
			return SaisonScolaire.staticSolrFqEcoleNumero(requeteSite_, o);
		case "ecoleAdministrateurNom":
			return SaisonScolaire.staticSolrFqEcoleAdministrateurNom(requeteSite_, o);
		case "anneeDebut":
			return SaisonScolaire.staticSolrFqAnneeDebut(requeteSite_, o);
		case "anneeFin":
			return SaisonScolaire.staticSolrFqAnneeFin(requeteSite_, o);
		case "anneeFraisInscription":
			return SaisonScolaire.staticSolrFqAnneeFraisInscription(requeteSite_, o);
		case "saisonDateDebut":
			return SaisonScolaire.staticSolrFqSaisonDateDebut(requeteSite_, o);
		case "saisonEte":
			return SaisonScolaire.staticSolrFqSaisonEte(requeteSite_, o);
		case "saisonHiver":
			return SaisonScolaire.staticSolrFqSaisonHiver(requeteSite_, o);
		case "saisonFuture":
			return SaisonScolaire.staticSolrFqSaisonFuture(requeteSite_, o);
		case "saisonNomCourt":
			return SaisonScolaire.staticSolrFqSaisonNomCourt(requeteSite_, o);
		case "saisonNomComplet":
			return SaisonScolaire.staticSolrFqSaisonNomComplet(requeteSite_, o);
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
					o = definirSaisonScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSaisonScolaire(String var, String val) {
		switch(var) {
			case "saisonDateDebut":
				if(val != null)
					setSaisonDateDebut(val);
				sauvegardes.add(var);
				return val;
			case "saisonEte":
				if(val != null)
					setSaisonEte(val);
				sauvegardes.add(var);
				return val;
			case "saisonHiver":
				if(val != null)
					setSaisonHiver(val);
				sauvegardes.add(var);
				return val;
			case "saisonFuture":
				if(val != null)
					setSaisonFuture(val);
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
		peuplerSaisonScolaire(solrDocument);
	}
	public void peuplerSaisonScolaire(SolrDocument solrDocument) {
		SaisonScolaire oSaisonScolaire = (SaisonScolaire)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("saisonCle")) {
				Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
				if(saisonCle != null)
					oSaisonScolaire.setSaisonCle(saisonCle);
			}

			if(sauvegardes.contains("inscriptionCles")) {
				List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
				if(inscriptionCles != null)
					oSaisonScolaire.inscriptionCles.addAll(inscriptionCles);
			}

			Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
			if(anneeCle != null)
				oSaisonScolaire.setAnneeCle(anneeCle);

			List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
			if(sessionCles != null)
				oSaisonScolaire.sessionCles.addAll(sessionCles);

			if(sauvegardes.contains("scolaireTri")) {
				Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
				if(scolaireTri != null)
					oSaisonScolaire.setScolaireTri(scolaireTri);
			}

			if(sauvegardes.contains("ecoleTri")) {
				Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
				if(ecoleTri != null)
					oSaisonScolaire.setEcoleTri(ecoleTri);
			}

			if(sauvegardes.contains("anneeTri")) {
				Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
				if(anneeTri != null)
					oSaisonScolaire.setAnneeTri(anneeTri);
			}

			if(sauvegardes.contains("saisonTri")) {
				Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
				if(saisonTri != null)
					oSaisonScolaire.setSaisonTri(saisonTri);
			}

			if(sauvegardes.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oSaisonScolaire.setEcoleCle(ecoleCle);
			}

			if(sauvegardes.contains("ecoleNom")) {
				String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
				if(ecoleNom != null)
					oSaisonScolaire.setEcoleNom(ecoleNom);
			}

			if(sauvegardes.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oSaisonScolaire.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardes.contains("ecoleEmplacement")) {
				String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
				if(ecoleEmplacement != null)
					oSaisonScolaire.setEcoleEmplacement(ecoleEmplacement);
			}

			if(sauvegardes.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oSaisonScolaire.setEcoleAddresse(ecoleAddresse);
			}

			if(sauvegardes.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oSaisonScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardes.contains("ecoleForm")) {
				String ecoleForm = (String)solrDocument.get("ecoleForm_stored_string");
				if(ecoleForm != null)
					oSaisonScolaire.setEcoleForm(ecoleForm);
			}

			if(sauvegardes.contains("ecoleNumero")) {
				Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
				if(ecoleNumero != null)
					oSaisonScolaire.setEcoleNumero(ecoleNumero);
			}

			if(sauvegardes.contains("ecoleAdministrateurNom")) {
				String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
				if(ecoleAdministrateurNom != null)
					oSaisonScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);
			}

			if(sauvegardes.contains("anneeDebut")) {
				Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
				if(anneeDebut != null)
					oSaisonScolaire.setAnneeDebut(anneeDebut);
			}

			if(sauvegardes.contains("anneeFin")) {
				Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
				if(anneeFin != null)
					oSaisonScolaire.setAnneeFin(anneeFin);
			}

			if(sauvegardes.contains("anneeFraisInscription")) {
				Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
				if(anneeFraisInscription != null)
					oSaisonScolaire.setAnneeFraisInscription(anneeFraisInscription);
			}

			if(sauvegardes.contains("saisonDateDebut")) {
				Date saisonDateDebut = (Date)solrDocument.get("saisonDateDebut_stored_date");
				if(saisonDateDebut != null)
					oSaisonScolaire.setSaisonDateDebut(saisonDateDebut);
			}

			if(sauvegardes.contains("saisonEte")) {
				Boolean saisonEte = (Boolean)solrDocument.get("saisonEte_stored_boolean");
				if(saisonEte != null)
					oSaisonScolaire.setSaisonEte(saisonEte);
			}

			if(sauvegardes.contains("saisonHiver")) {
				Boolean saisonHiver = (Boolean)solrDocument.get("saisonHiver_stored_boolean");
				if(saisonHiver != null)
					oSaisonScolaire.setSaisonHiver(saisonHiver);
			}

			if(sauvegardes.contains("saisonFuture")) {
				Boolean saisonFuture = (Boolean)solrDocument.get("saisonFuture_stored_boolean");
				if(saisonFuture != null)
					oSaisonScolaire.setSaisonFuture(saisonFuture);
			}

			if(sauvegardes.contains("saisonNomCourt")) {
				String saisonNomCourt = (String)solrDocument.get("saisonNomCourt_stored_string");
				if(saisonNomCourt != null)
					oSaisonScolaire.setSaisonNomCourt(saisonNomCourt);
			}

			if(sauvegardes.contains("saisonNomComplet")) {
				String saisonNomComplet = (String)solrDocument.get("saisonNomComplet_stored_string");
				if(saisonNomComplet != null)
					oSaisonScolaire.setSaisonNomComplet(saisonNomComplet);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.saison.SaisonScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			SaisonScolaire o = new SaisonScolaire();
			o.requeteSiteSaisonScolaire(requeteSite);
			o.initLoinSaisonScolaire(requeteSite);
			o.indexerSaisonScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerSaisonScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerSaisonScolaire(document);
	}

	public void indexerSaisonScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerSaisonScolaire(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerSaisonScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerSaisonScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerSaisonScolaire(SolrInputDocument document) {
		if(saisonCle != null) {
			document.addField("saisonCle_indexed_long", saisonCle);
			document.addField("saisonCle_stored_long", saisonCle);
		}
		if(inscriptionCles != null) {
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_indexed_longs", o);
			}
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_stored_longs", o);
			}
		}
		if(anneeCle != null) {
			document.addField("anneeCle_indexed_long", anneeCle);
			document.addField("anneeCle_stored_long", anneeCle);
		}
		if(sessionCles != null) {
			for(java.lang.Long o : sessionCles) {
				document.addField("sessionCles_indexed_longs", o);
			}
			for(java.lang.Long o : sessionCles) {
				document.addField("sessionCles_stored_longs", o);
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
		if(ecoleCle != null) {
			document.addField("ecoleCle_indexed_long", ecoleCle);
			document.addField("ecoleCle_stored_long", ecoleCle);
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
		if(anneeFraisInscription != null) {
			document.addField("anneeFraisInscription_indexed_double", anneeFraisInscription.doubleValue());
			document.addField("anneeFraisInscription_stored_double", anneeFraisInscription.doubleValue());
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
		if(saisonFuture != null) {
			document.addField("saisonFuture_indexed_boolean", saisonFuture);
			document.addField("saisonFuture_stored_boolean", saisonFuture);
		}
		if(saisonNomCourt != null) {
			document.addField("saisonNomCourt_indexed_string", saisonNomCourt);
			document.addField("saisonNomCourt_stored_string", saisonNomCourt);
		}
		if(saisonNomComplet != null) {
			document.addField("saisonNomComplet_indexed_string", saisonNomComplet);
			document.addField("saisonNomComplet_stored_string", saisonNomComplet);
		}
		super.indexerCluster(document);

	}

	public void desindexerSaisonScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinSaisonScolaire(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeSaisonScolaire(String entiteVar) {
		switch(entiteVar) {
			case "saisonCle":
				return "saisonCle_indexed_long";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "anneeCle":
				return "anneeCle_indexed_long";
			case "sessionCles":
				return "sessionCles_indexed_longs";
			case "scolaireTri":
				return "scolaireTri_indexed_int";
			case "ecoleTri":
				return "ecoleTri_indexed_int";
			case "anneeTri":
				return "anneeTri_indexed_int";
			case "saisonTri":
				return "saisonTri_indexed_int";
			case "ecoleCle":
				return "ecoleCle_indexed_long";
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
			case "anneeFraisInscription":
				return "anneeFraisInscription_indexed_double";
			case "saisonDateDebut":
				return "saisonDateDebut_indexed_date";
			case "saisonEte":
				return "saisonEte_indexed_boolean";
			case "saisonHiver":
				return "saisonHiver_indexed_boolean";
			case "saisonFuture":
				return "saisonFuture_indexed_boolean";
			case "saisonNomCourt":
				return "saisonNomCourt_indexed_string";
			case "saisonNomComplet":
				return "saisonNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheSaisonScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereSaisonScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerSaisonScolaire(solrDocument);
	}
	public void stockerSaisonScolaire(SolrDocument solrDocument) {
		SaisonScolaire oSaisonScolaire = (SaisonScolaire)this;

		Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
		if(saisonCle != null)
			oSaisonScolaire.setSaisonCle(saisonCle);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oSaisonScolaire.inscriptionCles.addAll(inscriptionCles);

		Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
		if(anneeCle != null)
			oSaisonScolaire.setAnneeCle(anneeCle);

		List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
		if(sessionCles != null)
			oSaisonScolaire.sessionCles.addAll(sessionCles);

		Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
		if(scolaireTri != null)
			oSaisonScolaire.setScolaireTri(scolaireTri);

		Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
		if(ecoleTri != null)
			oSaisonScolaire.setEcoleTri(ecoleTri);

		Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
		if(anneeTri != null)
			oSaisonScolaire.setAnneeTri(anneeTri);

		Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
		if(saisonTri != null)
			oSaisonScolaire.setSaisonTri(saisonTri);

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oSaisonScolaire.setEcoleCle(ecoleCle);

		String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
		if(ecoleNom != null)
			oSaisonScolaire.setEcoleNom(ecoleNom);

		String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
		if(ecoleNomComplet != null)
			oSaisonScolaire.setEcoleNomComplet(ecoleNomComplet);

		String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
		if(ecoleEmplacement != null)
			oSaisonScolaire.setEcoleEmplacement(ecoleEmplacement);

		String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
		if(ecoleAddresse != null)
			oSaisonScolaire.setEcoleAddresse(ecoleAddresse);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oSaisonScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		String ecoleForm = (String)solrDocument.get("ecoleForm_stored_string");
		if(ecoleForm != null)
			oSaisonScolaire.setEcoleForm(ecoleForm);

		Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
		if(ecoleNumero != null)
			oSaisonScolaire.setEcoleNumero(ecoleNumero);

		String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
		if(ecoleAdministrateurNom != null)
			oSaisonScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);

		Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
		if(anneeDebut != null)
			oSaisonScolaire.setAnneeDebut(anneeDebut);

		Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
		if(anneeFin != null)
			oSaisonScolaire.setAnneeFin(anneeFin);

		Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
		if(anneeFraisInscription != null)
			oSaisonScolaire.setAnneeFraisInscription(anneeFraisInscription);

		Date saisonDateDebut = (Date)solrDocument.get("saisonDateDebut_stored_date");
		if(saisonDateDebut != null)
			oSaisonScolaire.setSaisonDateDebut(saisonDateDebut);

		Boolean saisonEte = (Boolean)solrDocument.get("saisonEte_stored_boolean");
		if(saisonEte != null)
			oSaisonScolaire.setSaisonEte(saisonEte);

		Boolean saisonHiver = (Boolean)solrDocument.get("saisonHiver_stored_boolean");
		if(saisonHiver != null)
			oSaisonScolaire.setSaisonHiver(saisonHiver);

		Boolean saisonFuture = (Boolean)solrDocument.get("saisonFuture_stored_boolean");
		if(saisonFuture != null)
			oSaisonScolaire.setSaisonFuture(saisonFuture);

		String saisonNomCourt = (String)solrDocument.get("saisonNomCourt_stored_string");
		if(saisonNomCourt != null)
			oSaisonScolaire.setSaisonNomCourt(saisonNomCourt);

		String saisonNomComplet = (String)solrDocument.get("saisonNomComplet_stored_string");
		if(saisonNomComplet != null)
			oSaisonScolaire.setSaisonNomComplet(saisonNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiSaisonScolaire() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof SaisonScolaire) {
			SaisonScolaire original = (SaisonScolaire)o;
			if(!Objects.equals(saisonCle, original.getSaisonCle()))
				requeteApi.addVars("saisonCle");
			if(!Objects.equals(inscriptionCles, original.getInscriptionCles()))
				requeteApi.addVars("inscriptionCles");
			if(!Objects.equals(anneeCle, original.getAnneeCle()))
				requeteApi.addVars("anneeCle");
			if(!Objects.equals(sessionCles, original.getSessionCles()))
				requeteApi.addVars("sessionCles");
			if(!Objects.equals(scolaireTri, original.getScolaireTri()))
				requeteApi.addVars("scolaireTri");
			if(!Objects.equals(ecoleTri, original.getEcoleTri()))
				requeteApi.addVars("ecoleTri");
			if(!Objects.equals(anneeTri, original.getAnneeTri()))
				requeteApi.addVars("anneeTri");
			if(!Objects.equals(saisonTri, original.getSaisonTri()))
				requeteApi.addVars("saisonTri");
			if(!Objects.equals(ecoleCle, original.getEcoleCle()))
				requeteApi.addVars("ecoleCle");
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
			if(!Objects.equals(anneeFraisInscription, original.getAnneeFraisInscription()))
				requeteApi.addVars("anneeFraisInscription");
			if(!Objects.equals(saisonDateDebut, original.getSaisonDateDebut()))
				requeteApi.addVars("saisonDateDebut");
			if(!Objects.equals(saisonEte, original.getSaisonEte()))
				requeteApi.addVars("saisonEte");
			if(!Objects.equals(saisonHiver, original.getSaisonHiver()))
				requeteApi.addVars("saisonHiver");
			if(!Objects.equals(saisonFuture, original.getSaisonFuture()))
				requeteApi.addVars("saisonFuture");
			if(!Objects.equals(saisonNomCourt, original.getSaisonNomCourt()))
				requeteApi.addVars("saisonNomCourt");
			if(!Objects.equals(saisonNomComplet, original.getSaisonNomComplet()))
				requeteApi.addVars("saisonNomComplet");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), saisonCle, inscriptionCles, anneeCle, sessionCles, scolaireTri, ecoleTri, anneeTri, saisonTri, ecoleCle, ecoleNom, ecoleNomComplet, ecoleEmplacement, ecoleAddresse, ecoleNumeroTelephone, ecoleForm, ecoleNumero, ecoleAdministrateurNom, anneeDebut, anneeFin, anneeFraisInscription, saisonDateDebut, saisonEte, saisonHiver, saisonFuture, saisonNomCourt, saisonNomComplet);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SaisonScolaire))
			return false;
		SaisonScolaire that = (SaisonScolaire)o;
		return super.equals(o)
				&& Objects.equals( saisonCle, that.saisonCle )
				&& Objects.equals( inscriptionCles, that.inscriptionCles )
				&& Objects.equals( anneeCle, that.anneeCle )
				&& Objects.equals( sessionCles, that.sessionCles )
				&& Objects.equals( scolaireTri, that.scolaireTri )
				&& Objects.equals( ecoleTri, that.ecoleTri )
				&& Objects.equals( anneeTri, that.anneeTri )
				&& Objects.equals( saisonTri, that.saisonTri )
				&& Objects.equals( ecoleCle, that.ecoleCle )
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
				&& Objects.equals( anneeFraisInscription, that.anneeFraisInscription )
				&& Objects.equals( saisonDateDebut, that.saisonDateDebut )
				&& Objects.equals( saisonEte, that.saisonEte )
				&& Objects.equals( saisonHiver, that.saisonHiver )
				&& Objects.equals( saisonFuture, that.saisonFuture )
				&& Objects.equals( saisonNomCourt, that.saisonNomCourt )
				&& Objects.equals( saisonNomComplet, that.saisonNomComplet );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SaisonScolaire { ");
		sb.append( "saisonCle: " ).append(saisonCle);
		sb.append( ", inscriptionCles: " ).append(inscriptionCles);
		sb.append( ", anneeCle: " ).append(anneeCle);
		sb.append( ", sessionCles: " ).append(sessionCles);
		sb.append( ", scolaireTri: " ).append(scolaireTri);
		sb.append( ", ecoleTri: " ).append(ecoleTri);
		sb.append( ", anneeTri: " ).append(anneeTri);
		sb.append( ", saisonTri: " ).append(saisonTri);
		sb.append( ", ecoleCle: " ).append(ecoleCle);
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
		sb.append( ", anneeFraisInscription: " ).append(anneeFraisInscription);
		sb.append( ", saisonDateDebut: " ).append(saisonDateDebut);
		sb.append( ", saisonEte: " ).append(saisonEte);
		sb.append( ", saisonHiver: " ).append(saisonHiver);
		sb.append( ", saisonFuture: " ).append(saisonFuture);
		sb.append( ", saisonNomCourt: \"" ).append(saisonNomCourt).append( "\"" );
		sb.append( ", saisonNomComplet: \"" ).append(saisonNomComplet).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
