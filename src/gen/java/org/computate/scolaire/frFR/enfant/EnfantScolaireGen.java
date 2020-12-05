package org.computate.scolaire.frFR.enfant;

import java.util.Arrays;
import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
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
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe childCompleteName dans Solr. </a>
 * <br/>
 **/
public abstract class EnfantScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(EnfantScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

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
	public static final String EnfantScolaire_Titre = "enfants";
	public static final String EnfantScolaire_LesNom = "les enfants";
	public static final String EnfantScolaire_AucunNomTrouve = "aucun enfant trouvé";
	public static final String EnfantScolaire_NomVar = "enfant";
	public static final String EnfantScolaire_DeNom = "d'enfant";
	public static final String EnfantScolaire_NomAdjectifSingulier = "enfant";
	public static final String EnfantScolaire_NomAdjectifPluriel = "enfants";
	public static final String EnfantScolaire_Couleur = "orange";
	public static final String EnfantScolaire_IconeGroupe = "regular";
	public static final String EnfantScolaire_IconeNom = "child";

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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantCle">Trouver l'entité enfantCle dans Solr</a>
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
		this.enfantCle = EnfantScolaire.staticSetEnfantCle(requeteSite_, o);
		this.enfantCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetEnfantCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrEnfantCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrEnfantCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantCle(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrEnfantCle(requeteSite_, EnfantScolaire.staticSolrEnfantCle(requeteSite_, EnfantScolaire.staticSetEnfantCle(requeteSite_, o)));
	}

	public Long solrEnfantCle() {
		return EnfantScolaire.staticSolrEnfantCle(requeteSite_, enfantCle);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
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
		Long l = EnfantScolaire.staticSetInscriptionCles(requeteSite_, o);
		if(l != null)
			addInscriptionCles(l);
		this.inscriptionClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetInscriptionCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
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

	public static Long staticSolrInscriptionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrInscriptionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionCles(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrInscriptionCles(requeteSite_, EnfantScolaire.staticSolrInscriptionCles(requeteSite_, EnfantScolaire.staticSetInscriptionCles(requeteSite_, o)));
	}

	public List<Long> solrInscriptionCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : inscriptionCles) {
			l.add(EnfantScolaire.staticSolrInscriptionCles(requeteSite_, o));
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
		return "inscriptions";
	}

	public String htmTooltipInscriptionCles() {
		return null;
	}

	public String htmInscriptionCles() {
		return inscriptionCles == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionCles());
	}

	public void inputInscriptionCles(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
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
						.a("id", classeApiMethodeMethode, "_inscriptionCles_vider")
						.a("class", "inscriptionCles_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_inscriptionCles_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "inscriptions")
				.a("class", "valeur suggereInscriptionCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setInscriptionCles")
				.a("id", classeApiMethodeMethode, "_inscriptionCles")
				.a("autocomplete", "off");
				a("oninput", "suggereEnfantScolaireInscriptionCles($(this).val() ? rechercherInscriptionScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'enfantCle:" + pk + "'}", "], $('#listEnfantScolaireInscriptionCles_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varEnfantScolaire", pk, "InscriptionCles ").f().sx(htmInscriptionCles()).g("span");
		}
	}

	public void htmInscriptionCles(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EnfantScolaireInscriptionCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/inscription?fq=enfantCle:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("inscriptions");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cet enfant");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listEnfantScolaireInscriptionCles_", classeApiMethodeMethode).f();
								} g("ul");
								{
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
												.a("id", classeApiMethodeMethode, "_inscriptionCles_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postInscriptionScolaireVals({ enfantCle: \"", pk, "\" }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "inscriptionCles')); });")
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

	////////////////
	// familleTri //
	////////////////

	/**	 L'entité familleTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer familleTri;
	@JsonIgnore
	public Couverture<Integer> familleTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("familleTri").o(familleTri);

	/**	<br/> L'entité familleTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleTri">Trouver l'entité familleTri dans Solr</a>
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
	public void setFamilleTri(String o) {
		this.familleTri = EnfantScolaire.staticSetFamilleTri(requeteSite_, o);
		this.familleTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetFamilleTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrFamilleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrFamilleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilleTri(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrFamilleTri(requeteSite_, EnfantScolaire.staticSolrFamilleTri(requeteSite_, EnfantScolaire.staticSetFamilleTri(requeteSite_, o)));
	}

	public Integer solrFamilleTri() {
		return EnfantScolaire.staticSolrFamilleTri(requeteSite_, familleTri);
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

	///////////////
	// enfantTri //
	///////////////

	/**	 L'entité enfantTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer enfantTri;
	@JsonIgnore
	public Couverture<Integer> enfantTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("enfantTri").o(enfantTri);

	/**	<br/> L'entité enfantTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantTri">Trouver l'entité enfantTri dans Solr</a>
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
	public void setEnfantTri(String o) {
		this.enfantTri = EnfantScolaire.staticSetEnfantTri(requeteSite_, o);
		this.enfantTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetEnfantTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Integer staticSolrEnfantTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrEnfantTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantTri(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrEnfantTri(requeteSite_, EnfantScolaire.staticSolrEnfantTri(requeteSite_, EnfantScolaire.staticSetEnfantTri(requeteSite_, o)));
	}

	public Integer solrEnfantTri() {
		return EnfantScolaire.staticSolrEnfantTri(requeteSite_, enfantTri);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionRecherche">Trouver l'entité inscriptionRecherche dans Solr</a>
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

	/**	 L'entité inscriptions
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<InscriptionScolaire> inscriptions = new ArrayList<InscriptionScolaire>();
	@JsonIgnore
	public Couverture<List<InscriptionScolaire>> inscriptionsCouverture = new Couverture<List<InscriptionScolaire>>().p(this).c(List.class).var("inscriptions").o(inscriptions);

	/**	<br/> L'entité inscriptions
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<InscriptionScolaire>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptions">Trouver l'entité inscriptions dans Solr</a>
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
	public static InscriptionScolaire staticSetInscriptions(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurCles">Trouver l'entité utilisateurCles dans Solr</a>
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
		Long l = EnfantScolaire.staticSetUtilisateurCles(requeteSite_, o);
		if(l != null)
			addUtilisateurCles(l);
		this.utilisateurClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetUtilisateurCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public EnfantScolaire addUtilisateurCles(Long...objets) {
		for(Long o : objets) {
			addUtilisateurCles(o);
		}
		return (EnfantScolaire)this;
	}
	public EnfantScolaire addUtilisateurCles(Long o) {
		if(o != null && !utilisateurCles.contains(o))
			this.utilisateurCles.add(o);
		return (EnfantScolaire)this;
	}
	public void setUtilisateurCles(JsonArray objets) {
		utilisateurCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUtilisateurCles(o);
		}
	}
	public EnfantScolaire addUtilisateurCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUtilisateurCles(p);
		}
		return (EnfantScolaire)this;
	}
	protected EnfantScolaire utilisateurClesInit() {
		if(!utilisateurClesCouverture.dejaInitialise) {
			_utilisateurCles(utilisateurCles);
		}
		utilisateurClesCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public static Long staticSolrUtilisateurCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrUtilisateurCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUtilisateurCles(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrUtilisateurCles(requeteSite_, EnfantScolaire.staticSolrUtilisateurCles(requeteSite_, EnfantScolaire.staticSetUtilisateurCles(requeteSite_, o)));
	}

	public List<Long> solrUtilisateurCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : utilisateurCles) {
			l.add(EnfantScolaire.staticSolrUtilisateurCles(requeteSite_, o));
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

	///////////////
	// ecoleCles //
	///////////////

	/**	 L'entité ecoleCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> ecoleCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> ecoleClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("ecoleCles").o(ecoleCles);

	/**	<br/> L'entité ecoleCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCles">Trouver l'entité ecoleCles dans Solr</a>
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
	public void setEcoleCles(String o) {
		Long l = EnfantScolaire.staticSetEcoleCles(requeteSite_, o);
		if(l != null)
			addEcoleCles(l);
		this.ecoleClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetEcoleCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setEcoleCles(JsonArray objets) {
		ecoleCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEcoleCles(o);
		}
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

	public static Long staticSolrEcoleCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrEcoleCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleCles(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrEcoleCles(requeteSite_, EnfantScolaire.staticSolrEcoleCles(requeteSite_, EnfantScolaire.staticSetEcoleCles(requeteSite_, o)));
	}

	public List<Long> solrEcoleCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : ecoleCles) {
			l.add(EnfantScolaire.staticSolrEcoleCles(requeteSite_, o));
		}
		return l;
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

	///////////////
	// anneeCles //
	///////////////

	/**	 L'entité anneeCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> anneeCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> anneeClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("anneeCles").o(anneeCles);

	/**	<br/> L'entité anneeCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCles">Trouver l'entité anneeCles dans Solr</a>
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
	public void setAnneeCles(String o) {
		Long l = EnfantScolaire.staticSetAnneeCles(requeteSite_, o);
		if(l != null)
			addAnneeCles(l);
		this.anneeClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetAnneeCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setAnneeCles(JsonArray objets) {
		anneeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAnneeCles(o);
		}
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

	public static Long staticSolrAnneeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAnneeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeCles(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrAnneeCles(requeteSite_, EnfantScolaire.staticSolrAnneeCles(requeteSite_, EnfantScolaire.staticSetAnneeCles(requeteSite_, o)));
	}

	public List<Long> solrAnneeCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : anneeCles) {
			l.add(EnfantScolaire.staticSolrAnneeCles(requeteSite_, o));
		}
		return l;
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

	////////////////
	// saisonCles //
	////////////////

	/**	 L'entité saisonCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> saisonCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> saisonClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("saisonCles").o(saisonCles);

	/**	<br/> L'entité saisonCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCles">Trouver l'entité saisonCles dans Solr</a>
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
	public void setSaisonCles(String o) {
		Long l = EnfantScolaire.staticSetSaisonCles(requeteSite_, o);
		if(l != null)
			addSaisonCles(l);
		this.saisonClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetSaisonCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setSaisonCles(JsonArray objets) {
		saisonCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSaisonCles(o);
		}
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

	public static Long staticSolrSaisonCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSaisonCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonCles(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrSaisonCles(requeteSite_, EnfantScolaire.staticSolrSaisonCles(requeteSite_, EnfantScolaire.staticSetSaisonCles(requeteSite_, o)));
	}

	public List<Long> solrSaisonCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : saisonCles) {
			l.add(EnfantScolaire.staticSolrSaisonCles(requeteSite_, o));
		}
		return l;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCles">Trouver l'entité sessionCles dans Solr</a>
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
	public void setSessionCles(String o) {
		Long l = EnfantScolaire.staticSetSessionCles(requeteSite_, o);
		if(l != null)
			addSessionCles(l);
		this.sessionClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetSessionCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setSessionCles(JsonArray objets) {
		sessionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionCles(o);
		}
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

	public static Long staticSolrSessionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionCles(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrSessionCles(requeteSite_, EnfantScolaire.staticSolrSessionCles(requeteSite_, EnfantScolaire.staticSetSessionCles(requeteSite_, o)));
	}

	public List<Long> solrSessionCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : sessionCles) {
			l.add(EnfantScolaire.staticSolrSessionCles(requeteSite_, o));
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

	/////////////
	// ageCles //
	/////////////

	/**	 L'entité ageCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> ageCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> ageClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("ageCles").o(ageCles);

	/**	<br/> L'entité ageCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCles">Trouver l'entité ageCles dans Solr</a>
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
	public void setAgeCles(String o) {
		Long l = EnfantScolaire.staticSetAgeCles(requeteSite_, o);
		if(l != null)
			addAgeCles(l);
		this.ageClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetAgeCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setAgeCles(JsonArray objets) {
		ageCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeCles(o);
		}
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

	public static Long staticSolrAgeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeCles(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrAgeCles(requeteSite_, EnfantScolaire.staticSolrAgeCles(requeteSite_, EnfantScolaire.staticSetAgeCles(requeteSite_, o)));
	}

	public List<Long> solrAgeCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : ageCles) {
			l.add(EnfantScolaire.staticSolrAgeCles(requeteSite_, o));
		}
		return l;
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

	////////////////////
	// personnePrenom //
	////////////////////

	/**	 L'entité personnePrenom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personnePrenom;
	@JsonIgnore
	public Couverture<String> personnePrenomCouverture = new Couverture<String>().p(this).c(String.class).var("personnePrenom").o(personnePrenom);

	/**	<br/> L'entité personnePrenom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personnePrenom">Trouver l'entité personnePrenom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personnePrenom(Couverture<String> c);

	public String getPersonnePrenom() {
		return personnePrenom;
	}
	public void setPersonnePrenom(String o) {
		this.personnePrenom = EnfantScolaire.staticSetPersonnePrenom(requeteSite_, o);
		this.personnePrenomCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrPersonnePrenom(requeteSite_, EnfantScolaire.staticSolrPersonnePrenom(requeteSite_, EnfantScolaire.staticSetPersonnePrenom(requeteSite_, o)));
	}

	public String solrPersonnePrenom() {
		return EnfantScolaire.staticSolrPersonnePrenom(requeteSite_, personnePrenom);
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

	public void inputPersonnePrenom(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "prénom")
				.a("id", classeApiMethodeMethode, "_personnePrenom");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPersonnePrenom classEnfantScolaire inputEnfantScolaire", pk, "PersonnePrenom w3-input w3-border ");
					a("name", "setPersonnePrenom");
				} else {
					a("class", "valeurPersonnePrenom w3-input w3-border classEnfantScolaire inputEnfantScolaire", pk, "PersonnePrenom w3-input w3-border ");
					a("name", "personnePrenom");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonnePrenom', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenom')); }); ");
				}
				a("value", strPersonnePrenom())
			.fg();

		} else {
			e("span").a("class", "varEnfantScolaire", pk, "PersonnePrenom ").f().sx(htmPersonnePrenom()).g("span");
		}
	}

	public void htmPersonnePrenom(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EnfantScolairePersonnePrenom").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classeApiMethodeMethode, "_personnePrenom").a("class", "").f().sx("prénom").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonnePrenom(classeApiMethodeMethode);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personnePrenom')); $('#", classeApiMethodeMethode, "_personnePrenom').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=pk]').val() }], 'setPersonnePrenom', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenom')); }); ")
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
	// personnePrenomPrefere //
	///////////////////////////

	/**	 L'entité personnePrenomPrefere
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personnePrenomPrefere;
	@JsonIgnore
	public Couverture<String> personnePrenomPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("personnePrenomPrefere").o(personnePrenomPrefere);

	/**	<br/> L'entité personnePrenomPrefere
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personnePrenomPrefere">Trouver l'entité personnePrenomPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personnePrenomPrefere(Couverture<String> c);

	public String getPersonnePrenomPrefere() {
		return personnePrenomPrefere;
	}
	public void setPersonnePrenomPrefere(String o) {
		this.personnePrenomPrefere = EnfantScolaire.staticSetPersonnePrenomPrefere(requeteSite_, o);
		this.personnePrenomPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected EnfantScolaire personnePrenomPrefereInit() {
		if(!personnePrenomPrefereCouverture.dejaInitialise) {
			_personnePrenomPrefere(personnePrenomPrefereCouverture);
			if(personnePrenomPrefere == null)
				setPersonnePrenomPrefere(personnePrenomPrefereCouverture.o);
		}
		personnePrenomPrefereCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public static String staticSolrPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrPersonnePrenomPrefere(requeteSite_, EnfantScolaire.staticSolrPersonnePrenomPrefere(requeteSite_, EnfantScolaire.staticSetPersonnePrenomPrefere(requeteSite_, o)));
	}

	public String solrPersonnePrenomPrefere() {
		return EnfantScolaire.staticSolrPersonnePrenomPrefere(requeteSite_, personnePrenomPrefere);
	}

	public String strPersonnePrenomPrefere() {
		return personnePrenomPrefere == null ? "" : personnePrenomPrefere;
	}

	public String jsonPersonnePrenomPrefere() {
		return personnePrenomPrefere == null ? "" : personnePrenomPrefere;
	}

	public String nomAffichagePersonnePrenomPrefere() {
		return "prénom préferé";
	}

	public String htmTooltipPersonnePrenomPrefere() {
		return null;
	}

	public String htmPersonnePrenomPrefere() {
		return personnePrenomPrefere == null ? "" : StringEscapeUtils.escapeHtml4(strPersonnePrenomPrefere());
	}

	public void inputPersonnePrenomPrefere(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "prénom préferé")
				.a("id", classeApiMethodeMethode, "_personnePrenomPrefere");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPersonnePrenomPrefere classEnfantScolaire inputEnfantScolaire", pk, "PersonnePrenomPrefere w3-input w3-border ");
					a("name", "setPersonnePrenomPrefere");
				} else {
					a("class", "valeurPersonnePrenomPrefere w3-input w3-border classEnfantScolaire inputEnfantScolaire", pk, "PersonnePrenomPrefere w3-input w3-border ");
					a("name", "personnePrenomPrefere");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonnePrenomPrefere', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }); ");
				}
				a("value", strPersonnePrenomPrefere())
			.fg();

		} else {
			e("span").a("class", "varEnfantScolaire", pk, "PersonnePrenomPrefere ").f().sx(htmPersonnePrenomPrefere()).g("span");
		}
	}

	public void htmPersonnePrenomPrefere(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EnfantScolairePersonnePrenomPrefere").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classeApiMethodeMethode, "_personnePrenomPrefere").a("class", "").f().sx("prénom préferé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonnePrenomPrefere(classeApiMethodeMethode);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); $('#", classeApiMethodeMethode, "_personnePrenomPrefere').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=pk]').val() }], 'setPersonnePrenomPrefere', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }); ")
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

	////////////////
	// familleNom //
	////////////////

	/**	 L'entité familleNom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String familleNom;
	@JsonIgnore
	public Couverture<String> familleNomCouverture = new Couverture<String>().p(this).c(String.class).var("familleNom").o(familleNom);

	/**	<br/> L'entité familleNom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleNom">Trouver l'entité familleNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familleNom(Couverture<String> c);

	public String getFamilleNom() {
		return familleNom;
	}
	public void setFamilleNom(String o) {
		this.familleNom = EnfantScolaire.staticSetFamilleNom(requeteSite_, o);
		this.familleNomCouverture.dejaInitialise = true;
	}
	public static String staticSetFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrFamilleNom(requeteSite_, EnfantScolaire.staticSolrFamilleNom(requeteSite_, EnfantScolaire.staticSetFamilleNom(requeteSite_, o)));
	}

	public String solrFamilleNom() {
		return EnfantScolaire.staticSolrFamilleNom(requeteSite_, familleNom);
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

	public void inputFamilleNom(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "nom de famille")
				.a("id", classeApiMethodeMethode, "_familleNom");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setFamilleNom classEnfantScolaire inputEnfantScolaire", pk, "FamilleNom w3-input w3-border ");
					a("name", "setFamilleNom");
				} else {
					a("class", "valeurFamilleNom w3-input w3-border classEnfantScolaire inputEnfantScolaire", pk, "FamilleNom w3-input w3-border ");
					a("name", "familleNom");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleNom', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleNom')); }); ");
				}
				a("value", strFamilleNom())
			.fg();

		} else {
			e("span").a("class", "varEnfantScolaire", pk, "FamilleNom ").f().sx(htmFamilleNom()).g("span");
		}
	}

	public void htmFamilleNom(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EnfantScolaireFamilleNom").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classeApiMethodeMethode, "_familleNom").a("class", "").f().sx("nom de famille").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilleNom(classeApiMethodeMethode);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_familleNom')); $('#", classeApiMethodeMethode, "_familleNom').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=pk]').val() }], 'setFamilleNom', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleNom')); }); ")
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
	// personneNomComplet //
	////////////////////////

	/**	 L'entité personneNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personneNomComplet;
	@JsonIgnore
	public Couverture<String> personneNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("personneNomComplet").o(personneNomComplet);

	/**	<br/> L'entité personneNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomComplet">Trouver l'entité personneNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomComplet(Couverture<String> c);

	public String getPersonneNomComplet() {
		return personneNomComplet;
	}
	public void setPersonneNomComplet(String o) {
		this.personneNomComplet = EnfantScolaire.staticSetPersonneNomComplet(requeteSite_, o);
		this.personneNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrPersonneNomComplet(requeteSite_, EnfantScolaire.staticSolrPersonneNomComplet(requeteSite_, EnfantScolaire.staticSetPersonneNomComplet(requeteSite_, o)));
	}

	public String solrPersonneNomComplet() {
		return EnfantScolaire.staticSolrPersonneNomComplet(requeteSite_, personneNomComplet);
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

	///////////////////////////////
	// personneNomCompletPrefere //
	///////////////////////////////

	/**	 L'entité personneNomCompletPrefere
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personneNomCompletPrefere;
	@JsonIgnore
	public Couverture<String> personneNomCompletPrefereCouverture = new Couverture<String>().p(this).c(String.class).var("personneNomCompletPrefere").o(personneNomCompletPrefere);

	/**	<br/> L'entité personneNomCompletPrefere
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomCompletPrefere">Trouver l'entité personneNomCompletPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomCompletPrefere(Couverture<String> c);

	public String getPersonneNomCompletPrefere() {
		return personneNomCompletPrefere;
	}
	public void setPersonneNomCompletPrefere(String o) {
		this.personneNomCompletPrefere = EnfantScolaire.staticSetPersonneNomCompletPrefere(requeteSite_, o);
		this.personneNomCompletPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrPersonneNomCompletPrefere(requeteSite_, EnfantScolaire.staticSolrPersonneNomCompletPrefere(requeteSite_, EnfantScolaire.staticSetPersonneNomCompletPrefere(requeteSite_, o)));
	}

	public String solrPersonneNomCompletPrefere() {
		return EnfantScolaire.staticSolrPersonneNomCompletPrefere(requeteSite_, personneNomCompletPrefere);
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

	///////////////////////
	// personneNomFormel //
	///////////////////////

	/**	 L'entité personneNomFormel
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personneNomFormel;
	@JsonIgnore
	public Couverture<String> personneNomFormelCouverture = new Couverture<String>().p(this).c(String.class).var("personneNomFormel").o(personneNomFormel);

	/**	<br/> L'entité personneNomFormel
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomFormel">Trouver l'entité personneNomFormel dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomFormel(Couverture<String> c);

	public String getPersonneNomFormel() {
		return personneNomFormel;
	}
	public void setPersonneNomFormel(String o) {
		this.personneNomFormel = EnfantScolaire.staticSetPersonneNomFormel(requeteSite_, o);
		this.personneNomFormelCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrPersonneNomFormel(requeteSite_, EnfantScolaire.staticSolrPersonneNomFormel(requeteSite_, EnfantScolaire.staticSetPersonneNomFormel(requeteSite_, o)));
	}

	public String solrPersonneNomFormel() {
		return EnfantScolaire.staticSolrPersonneNomFormel(requeteSite_, personneNomFormel);
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

	///////////////////////////
	// personneDateNaissance //
	///////////////////////////

	/**	 L'entité personneDateNaissance
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate personneDateNaissance;
	@JsonIgnore
	public Couverture<LocalDate> personneDateNaissanceCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("personneDateNaissance").o(personneDateNaissance);

	/**	<br/> L'entité personneDateNaissance
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneDateNaissance">Trouver l'entité personneDateNaissance dans Solr</a>
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
	public void setPersonneDateNaissance(Instant o) {
		this.personneDateNaissance = o == null ? null : LocalDate.from(o);
		this.personneDateNaissanceCouverture.dejaInitialise = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setPersonneDateNaissance(String o) {
		this.personneDateNaissance = EnfantScolaire.staticSetPersonneDateNaissance(requeteSite_, o);
		this.personneDateNaissanceCouverture.dejaInitialise = true;
	}
	public static LocalDate staticSetPersonneDateNaissance(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setPersonneDateNaissance(Date o) {
		this.personneDateNaissance = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.personneDateNaissanceCouverture.dejaInitialise = true;
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

	public static Date staticSolrPersonneDateNaissance(RequeteSiteFrFR requeteSite_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrPersonneDateNaissance(RequeteSiteFrFR requeteSite_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqPersonneDateNaissance(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrPersonneDateNaissance(requeteSite_, EnfantScolaire.staticSolrPersonneDateNaissance(requeteSite_, EnfantScolaire.staticSetPersonneDateNaissance(requeteSite_, o)));
	}

	public Date solrPersonneDateNaissance() {
		return EnfantScolaire.staticSolrPersonneDateNaissance(requeteSite_, personneDateNaissance);
	}

	public String strPersonneDateNaissance() {
		return personneDateNaissance == null ? "" : personneDateNaissance.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonPersonneDateNaissance() {
		return personneDateNaissance == null ? "" : personneDateNaissance.format(DateTimeFormatter.ISO_DATE);
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

	public void inputPersonneDateNaissance(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setPersonneDateNaissance classEnfantScolaire inputEnfantScolaire", pk, "PersonneDateNaissance w3-input w3-border ")
					.a("placeholder", "DD-MM-YYYY")
					.a("data-timeformat", "dd-MM-yyyy")
					.a("id", classeApiMethodeMethode, "_personneDateNaissance")
					.a("value", personneDateNaissance == null ? "" : DateTimeFormatter.ofPattern("dd-MM-yyyy").format(personneDateNaissance));
			if("Page".equals(classeApiMethodeMethode)) {
				a("onclick", "enleverLueur($(this)); ");
				a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonneDateNaissance', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneDateNaissance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneDateNaissance')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varEnfantScolaire", pk, "PersonneDateNaissance ").f().sx(htmPersonneDateNaissance()).g("span");
		}
	}

	public void htmPersonneDateNaissance(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EnfantScolairePersonneDateNaissance").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classeApiMethodeMethode, "_personneDateNaissance").a("class", "").f().sx("date de naissance").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputPersonneDateNaissance(classeApiMethodeMethode);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personneDateNaissance')); $('#", classeApiMethodeMethode, "_personneDateNaissance').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=pk]').val() }], 'setPersonneDateNaissance', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneDateNaissance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneDateNaissance')); }); ")
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

	/////////////////////////////////
	// personneDateNaissanceDAnnee //
	/////////////////////////////////

	/**	 L'entité personneDateNaissanceDAnnee
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer personneDateNaissanceDAnnee;
	@JsonIgnore
	public Couverture<Integer> personneDateNaissanceDAnneeCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("personneDateNaissanceDAnnee").o(personneDateNaissanceDAnnee);

	/**	<br/> L'entité personneDateNaissanceDAnnee
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneDateNaissanceDAnnee">Trouver l'entité personneDateNaissanceDAnnee dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneDateNaissanceDAnnee(Couverture<Integer> c);

	public Integer getPersonneDateNaissanceDAnnee() {
		return personneDateNaissanceDAnnee;
	}

	public void setPersonneDateNaissanceDAnnee(Integer personneDateNaissanceDAnnee) {
		this.personneDateNaissanceDAnnee = personneDateNaissanceDAnnee;
		this.personneDateNaissanceDAnneeCouverture.dejaInitialise = true;
	}
	public void setPersonneDateNaissanceDAnnee(String o) {
		this.personneDateNaissanceDAnnee = EnfantScolaire.staticSetPersonneDateNaissanceDAnnee(requeteSite_, o);
		this.personneDateNaissanceDAnneeCouverture.dejaInitialise = true;
	}
	public static Integer staticSetPersonneDateNaissanceDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected EnfantScolaire personneDateNaissanceDAnneeInit() {
		if(!personneDateNaissanceDAnneeCouverture.dejaInitialise) {
			_personneDateNaissanceDAnnee(personneDateNaissanceDAnneeCouverture);
			if(personneDateNaissanceDAnnee == null)
				setPersonneDateNaissanceDAnnee(personneDateNaissanceDAnneeCouverture.o);
		}
		personneDateNaissanceDAnneeCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public static Integer staticSolrPersonneDateNaissanceDAnnee(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrPersonneDateNaissanceDAnnee(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneDateNaissanceDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrPersonneDateNaissanceDAnnee(requeteSite_, EnfantScolaire.staticSolrPersonneDateNaissanceDAnnee(requeteSite_, EnfantScolaire.staticSetPersonneDateNaissanceDAnnee(requeteSite_, o)));
	}

	public Integer solrPersonneDateNaissanceDAnnee() {
		return EnfantScolaire.staticSolrPersonneDateNaissanceDAnnee(requeteSite_, personneDateNaissanceDAnnee);
	}

	public String strPersonneDateNaissanceDAnnee() {
		return personneDateNaissanceDAnnee == null ? "" : personneDateNaissanceDAnnee.toString();
	}

	public String jsonPersonneDateNaissanceDAnnee() {
		return personneDateNaissanceDAnnee == null ? "" : personneDateNaissanceDAnnee.toString();
	}

	public String nomAffichagePersonneDateNaissanceDAnnee() {
		return null;
	}

	public String htmTooltipPersonneDateNaissanceDAnnee() {
		return null;
	}

	public String htmPersonneDateNaissanceDAnnee() {
		return personneDateNaissanceDAnnee == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneDateNaissanceDAnnee());
	}

	/////////////////////////////////////
	// personneDateNaissanceMoisDAnnee //
	/////////////////////////////////////

	/**	 L'entité personneDateNaissanceMoisDAnnee
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personneDateNaissanceMoisDAnnee;
	@JsonIgnore
	public Couverture<String> personneDateNaissanceMoisDAnneeCouverture = new Couverture<String>().p(this).c(String.class).var("personneDateNaissanceMoisDAnnee").o(personneDateNaissanceMoisDAnnee);

	/**	<br/> L'entité personneDateNaissanceMoisDAnnee
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneDateNaissanceMoisDAnnee">Trouver l'entité personneDateNaissanceMoisDAnnee dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneDateNaissanceMoisDAnnee(Couverture<String> c);

	public String getPersonneDateNaissanceMoisDAnnee() {
		return personneDateNaissanceMoisDAnnee;
	}
	public void setPersonneDateNaissanceMoisDAnnee(String o) {
		this.personneDateNaissanceMoisDAnnee = EnfantScolaire.staticSetPersonneDateNaissanceMoisDAnnee(requeteSite_, o);
		this.personneDateNaissanceMoisDAnneeCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneDateNaissanceMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected EnfantScolaire personneDateNaissanceMoisDAnneeInit() {
		if(!personneDateNaissanceMoisDAnneeCouverture.dejaInitialise) {
			_personneDateNaissanceMoisDAnnee(personneDateNaissanceMoisDAnneeCouverture);
			if(personneDateNaissanceMoisDAnnee == null)
				setPersonneDateNaissanceMoisDAnnee(personneDateNaissanceMoisDAnneeCouverture.o);
		}
		personneDateNaissanceMoisDAnneeCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public static String staticSolrPersonneDateNaissanceMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneDateNaissanceMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneDateNaissanceMoisDAnnee(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrPersonneDateNaissanceMoisDAnnee(requeteSite_, EnfantScolaire.staticSolrPersonneDateNaissanceMoisDAnnee(requeteSite_, EnfantScolaire.staticSetPersonneDateNaissanceMoisDAnnee(requeteSite_, o)));
	}

	public String solrPersonneDateNaissanceMoisDAnnee() {
		return EnfantScolaire.staticSolrPersonneDateNaissanceMoisDAnnee(requeteSite_, personneDateNaissanceMoisDAnnee);
	}

	public String strPersonneDateNaissanceMoisDAnnee() {
		return personneDateNaissanceMoisDAnnee == null ? "" : personneDateNaissanceMoisDAnnee;
	}

	public String jsonPersonneDateNaissanceMoisDAnnee() {
		return personneDateNaissanceMoisDAnnee == null ? "" : personneDateNaissanceMoisDAnnee;
	}

	public String nomAffichagePersonneDateNaissanceMoisDAnnee() {
		return null;
	}

	public String htmTooltipPersonneDateNaissanceMoisDAnnee() {
		return null;
	}

	public String htmPersonneDateNaissanceMoisDAnnee() {
		return personneDateNaissanceMoisDAnnee == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneDateNaissanceMoisDAnnee());
	}

	////////////////////////////////////////
	// personneDateNaissanceJourDeSemaine //
	////////////////////////////////////////

	/**	 L'entité personneDateNaissanceJourDeSemaine
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personneDateNaissanceJourDeSemaine;
	@JsonIgnore
	public Couverture<String> personneDateNaissanceJourDeSemaineCouverture = new Couverture<String>().p(this).c(String.class).var("personneDateNaissanceJourDeSemaine").o(personneDateNaissanceJourDeSemaine);

	/**	<br/> L'entité personneDateNaissanceJourDeSemaine
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneDateNaissanceJourDeSemaine">Trouver l'entité personneDateNaissanceJourDeSemaine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneDateNaissanceJourDeSemaine(Couverture<String> c);

	public String getPersonneDateNaissanceJourDeSemaine() {
		return personneDateNaissanceJourDeSemaine;
	}
	public void setPersonneDateNaissanceJourDeSemaine(String o) {
		this.personneDateNaissanceJourDeSemaine = EnfantScolaire.staticSetPersonneDateNaissanceJourDeSemaine(requeteSite_, o);
		this.personneDateNaissanceJourDeSemaineCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneDateNaissanceJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected EnfantScolaire personneDateNaissanceJourDeSemaineInit() {
		if(!personneDateNaissanceJourDeSemaineCouverture.dejaInitialise) {
			_personneDateNaissanceJourDeSemaine(personneDateNaissanceJourDeSemaineCouverture);
			if(personneDateNaissanceJourDeSemaine == null)
				setPersonneDateNaissanceJourDeSemaine(personneDateNaissanceJourDeSemaineCouverture.o);
		}
		personneDateNaissanceJourDeSemaineCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public static String staticSolrPersonneDateNaissanceJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneDateNaissanceJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneDateNaissanceJourDeSemaine(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrPersonneDateNaissanceJourDeSemaine(requeteSite_, EnfantScolaire.staticSolrPersonneDateNaissanceJourDeSemaine(requeteSite_, EnfantScolaire.staticSetPersonneDateNaissanceJourDeSemaine(requeteSite_, o)));
	}

	public String solrPersonneDateNaissanceJourDeSemaine() {
		return EnfantScolaire.staticSolrPersonneDateNaissanceJourDeSemaine(requeteSite_, personneDateNaissanceJourDeSemaine);
	}

	public String strPersonneDateNaissanceJourDeSemaine() {
		return personneDateNaissanceJourDeSemaine == null ? "" : personneDateNaissanceJourDeSemaine;
	}

	public String jsonPersonneDateNaissanceJourDeSemaine() {
		return personneDateNaissanceJourDeSemaine == null ? "" : personneDateNaissanceJourDeSemaine;
	}

	public String nomAffichagePersonneDateNaissanceJourDeSemaine() {
		return null;
	}

	public String htmTooltipPersonneDateNaissanceJourDeSemaine() {
		return null;
	}

	public String htmPersonneDateNaissanceJourDeSemaine() {
		return personneDateNaissanceJourDeSemaine == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneDateNaissanceJourDeSemaine());
	}

	////////////////////////////
	// personneAgeEnSeptembre //
	////////////////////////////

	/**	 L'entité personneAgeEnSeptembre
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personneAgeEnSeptembre;
	@JsonIgnore
	public Couverture<String> personneAgeEnSeptembreCouverture = new Couverture<String>().p(this).c(String.class).var("personneAgeEnSeptembre").o(personneAgeEnSeptembre);

	/**	<br/> L'entité personneAgeEnSeptembre
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneAgeEnSeptembre">Trouver l'entité personneAgeEnSeptembre dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneAgeEnSeptembre(Couverture<String> c);

	public String getPersonneAgeEnSeptembre() {
		return personneAgeEnSeptembre;
	}
	public void setPersonneAgeEnSeptembre(String o) {
		this.personneAgeEnSeptembre = EnfantScolaire.staticSetPersonneAgeEnSeptembre(requeteSite_, o);
		this.personneAgeEnSeptembreCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneAgeEnSeptembre(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected EnfantScolaire personneAgeEnSeptembreInit() {
		if(!personneAgeEnSeptembreCouverture.dejaInitialise) {
			_personneAgeEnSeptembre(personneAgeEnSeptembreCouverture);
			if(personneAgeEnSeptembre == null)
				setPersonneAgeEnSeptembre(personneAgeEnSeptembreCouverture.o);
		}
		personneAgeEnSeptembreCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public static String staticSolrPersonneAgeEnSeptembre(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneAgeEnSeptembre(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneAgeEnSeptembre(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrPersonneAgeEnSeptembre(requeteSite_, EnfantScolaire.staticSolrPersonneAgeEnSeptembre(requeteSite_, EnfantScolaire.staticSetPersonneAgeEnSeptembre(requeteSite_, o)));
	}

	public String solrPersonneAgeEnSeptembre() {
		return EnfantScolaire.staticSolrPersonneAgeEnSeptembre(requeteSite_, personneAgeEnSeptembre);
	}

	public String strPersonneAgeEnSeptembre() {
		return personneAgeEnSeptembre == null ? "" : personneAgeEnSeptembre;
	}

	public String jsonPersonneAgeEnSeptembre() {
		return personneAgeEnSeptembre == null ? "" : personneAgeEnSeptembre;
	}

	public String nomAffichagePersonneAgeEnSeptembre() {
		return "âge";
	}

	public String htmTooltipPersonneAgeEnSeptembre() {
		return null;
	}

	public String htmPersonneAgeEnSeptembre() {
		return personneAgeEnSeptembre == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneAgeEnSeptembre());
	}

	public void inputPersonneAgeEnSeptembre(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
	}

	public void htmPersonneAgeEnSeptembre(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			if("Page".equals(classeApiMethodeMethode)) {
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("class", "").f().sx("âge").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").a("class", "varEnfantScolaire", pk, "PersonneAgeEnSeptembre ").f().sx(strPersonneAgeEnSeptembre()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			}
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:photo">Trouver l'entité photo dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _photo(Couverture<String> c);

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String o) {
		this.photo = EnfantScolaire.staticSetPhoto(requeteSite_, o);
		this.photoCouverture.dejaInitialise = true;
	}
	public static String staticSetPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected EnfantScolaire photoInit() {
		if(!photoCouverture.dejaInitialise) {
			_photo(photoCouverture);
			if(photo == null)
				setPhoto(photoCouverture.o);
		}
		photoCouverture.dejaInitialise(true);
		return (EnfantScolaire)this;
	}

	public static String staticSolrPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrPhoto(requeteSite_, EnfantScolaire.staticSolrPhoto(requeteSite_, EnfantScolaire.staticSetPhoto(requeteSite_, o)));
	}

	public String solrPhoto() {
		return EnfantScolaire.staticSolrPhoto(requeteSite_, photo);
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
		EnfantScolaire s = (EnfantScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "imageBase64Div1EnfantScolaire_photo").a("id", "imageBase64Div1EnfantScolaire", pk, "photo").f();
				e("h5").f().sx("Télécharger image").g("h5"); 
				e("form").a("method", "POST").a("enctype", "multipart/form-data").a("action", "/photo").a("class", "").f();
				e("input").a("type", "hidden").a("name", "pk").a("value", pk).fg(); 
				e("input").a("type", "hidden").a("name", "classeNomSimple").a("value", "EnfantScolaire").fg(); 
				e("input").a("name", "fichier").a("type", "file").a("onchange", "$.ajax({ type: 'POST', enctype: 'multipart/form-data', url: '/photo', data: new FormData(this.form), processData: false, contentType: false}); ").fg(); 
				g("form");
				e("img").a("id", "imageBase64ImgEnfantScolaire", pk, "photo");
					a("class", "imgEnfantScolaire", pk, "Photo w3-image ");
					a("src", StringUtils.isBlank(photo) ? "data:image/png;base64," : photo).a("alt", "");
				fg();
			g("div");
		} else {
			e("span").a("class", "varEnfantScolaire", pk, "Photo ").f().sx(htmPhoto()).g("span");
		}
	}

	public void htmPhoto(String classeApiMethodeMethode) {
		EnfantScolaire s = (EnfantScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "EnfantScolairePhoto").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_photo')); $('#", classeApiMethodeMethode, "_photo').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#EnfantScolaireForm :input[name=pk]').val() }], 'setPhoto', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_photo')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_photo')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.enfant.EnfantScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:enfantNomComplet">Trouver l'entité enfantNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enfantNomComplet(Couverture<String> c);

	public String getEnfantNomComplet() {
		return enfantNomComplet;
	}
	public void setEnfantNomComplet(String o) {
		this.enfantNomComplet = EnfantScolaire.staticSetEnfantNomComplet(requeteSite_, o);
		this.enfantNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetEnfantNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrEnfantNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrEnfantNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnfantNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return EnfantScolaire.staticSolrStrEnfantNomComplet(requeteSite_, EnfantScolaire.staticSolrEnfantNomComplet(requeteSite_, EnfantScolaire.staticSetEnfantNomComplet(requeteSite_, o)));
	}

	public String solrEnfantNomComplet() {
		return EnfantScolaire.staticSolrEnfantNomComplet(requeteSite_, enfantNomComplet);
	}

	public String strEnfantNomComplet() {
		return enfantNomComplet == null ? "" : enfantNomComplet;
	}

	public String jsonEnfantNomComplet() {
		return enfantNomComplet == null ? "" : enfantNomComplet;
	}

	public String nomAffichageEnfantNomComplet() {
		return "nom";
	}

	public String htmTooltipEnfantNomComplet() {
		return null;
	}

	public String htmEnfantNomComplet() {
		return enfantNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strEnfantNomComplet());
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
		initEnfantScolaire();
		super.initLoinCluster(requeteSite_);
	}

	public void initEnfantScolaire() {
		enfantCleInit();
		inscriptionClesInit();
		familleTriInit();
		enfantTriInit();
		inscriptionRechercheInit();
		inscriptionsInit();
		utilisateurClesInit();
		ecoleClesInit();
		anneeClesInit();
		saisonClesInit();
		sessionClesInit();
		ageClesInit();
		personnePrenomInit();
		personnePrenomPrefereInit();
		familleNomInit();
		personneNomCompletInit();
		personneNomCompletPrefereInit();
		personneNomFormelInit();
		personneDateNaissanceInit();
		personneDateNaissanceDAnneeInit();
		personneDateNaissanceMoisDAnneeInit();
		personneDateNaissanceJourDeSemaineInit();
		personneAgeEnSeptembreInit();
		photoInit();
		enfantNomCompletInit();
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
			case "utilisateurCles":
				return oEnfantScolaire.utilisateurCles;
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
			case "personnePrenomPrefere":
				return oEnfantScolaire.personnePrenomPrefere;
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
			case "personneDateNaissanceDAnnee":
				return oEnfantScolaire.personneDateNaissanceDAnnee;
			case "personneDateNaissanceMoisDAnnee":
				return oEnfantScolaire.personneDateNaissanceMoisDAnnee;
			case "personneDateNaissanceJourDeSemaine":
				return oEnfantScolaire.personneDateNaissanceJourDeSemaine;
			case "personneAgeEnSeptembre":
				return oEnfantScolaire.personneAgeEnSeptembre;
			case "photo":
				return oEnfantScolaire.photo;
			case "enfantNomComplet":
				return oEnfantScolaire.enfantNomComplet;
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
			case "inscriptionCles":
				oEnfantScolaire.addInscriptionCles((Long)val);
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
		return staticSetEnfantScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetEnfantScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "enfantCle":
			return EnfantScolaire.staticSetEnfantCle(requeteSite_, o);
		case "inscriptionCles":
			return EnfantScolaire.staticSetInscriptionCles(requeteSite_, o);
		case "familleTri":
			return EnfantScolaire.staticSetFamilleTri(requeteSite_, o);
		case "enfantTri":
			return EnfantScolaire.staticSetEnfantTri(requeteSite_, o);
		case "utilisateurCles":
			return EnfantScolaire.staticSetUtilisateurCles(requeteSite_, o);
		case "ecoleCles":
			return EnfantScolaire.staticSetEcoleCles(requeteSite_, o);
		case "anneeCles":
			return EnfantScolaire.staticSetAnneeCles(requeteSite_, o);
		case "saisonCles":
			return EnfantScolaire.staticSetSaisonCles(requeteSite_, o);
		case "sessionCles":
			return EnfantScolaire.staticSetSessionCles(requeteSite_, o);
		case "ageCles":
			return EnfantScolaire.staticSetAgeCles(requeteSite_, o);
		case "personnePrenom":
			return EnfantScolaire.staticSetPersonnePrenom(requeteSite_, o);
		case "personnePrenomPrefere":
			return EnfantScolaire.staticSetPersonnePrenomPrefere(requeteSite_, o);
		case "familleNom":
			return EnfantScolaire.staticSetFamilleNom(requeteSite_, o);
		case "personneNomComplet":
			return EnfantScolaire.staticSetPersonneNomComplet(requeteSite_, o);
		case "personneNomCompletPrefere":
			return EnfantScolaire.staticSetPersonneNomCompletPrefere(requeteSite_, o);
		case "personneNomFormel":
			return EnfantScolaire.staticSetPersonneNomFormel(requeteSite_, o);
		case "personneDateNaissance":
			return EnfantScolaire.staticSetPersonneDateNaissance(requeteSite_, o);
		case "personneDateNaissanceDAnnee":
			return EnfantScolaire.staticSetPersonneDateNaissanceDAnnee(requeteSite_, o);
		case "personneDateNaissanceMoisDAnnee":
			return EnfantScolaire.staticSetPersonneDateNaissanceMoisDAnnee(requeteSite_, o);
		case "personneDateNaissanceJourDeSemaine":
			return EnfantScolaire.staticSetPersonneDateNaissanceJourDeSemaine(requeteSite_, o);
		case "personneAgeEnSeptembre":
			return EnfantScolaire.staticSetPersonneAgeEnSeptembre(requeteSite_, o);
		case "photo":
			return EnfantScolaire.staticSetPhoto(requeteSite_, o);
		case "enfantNomComplet":
			return EnfantScolaire.staticSetEnfantNomComplet(requeteSite_, o);
			default:
				return Cluster.staticSetCluster(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrEnfantScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrEnfantScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "enfantCle":
			return EnfantScolaire.staticSolrEnfantCle(requeteSite_, (Long)o);
		case "inscriptionCles":
			return EnfantScolaire.staticSolrInscriptionCles(requeteSite_, (Long)o);
		case "familleTri":
			return EnfantScolaire.staticSolrFamilleTri(requeteSite_, (Integer)o);
		case "enfantTri":
			return EnfantScolaire.staticSolrEnfantTri(requeteSite_, (Integer)o);
		case "utilisateurCles":
			return EnfantScolaire.staticSolrUtilisateurCles(requeteSite_, (Long)o);
		case "ecoleCles":
			return EnfantScolaire.staticSolrEcoleCles(requeteSite_, (Long)o);
		case "anneeCles":
			return EnfantScolaire.staticSolrAnneeCles(requeteSite_, (Long)o);
		case "saisonCles":
			return EnfantScolaire.staticSolrSaisonCles(requeteSite_, (Long)o);
		case "sessionCles":
			return EnfantScolaire.staticSolrSessionCles(requeteSite_, (Long)o);
		case "ageCles":
			return EnfantScolaire.staticSolrAgeCles(requeteSite_, (Long)o);
		case "personnePrenom":
			return EnfantScolaire.staticSolrPersonnePrenom(requeteSite_, (String)o);
		case "personnePrenomPrefere":
			return EnfantScolaire.staticSolrPersonnePrenomPrefere(requeteSite_, (String)o);
		case "familleNom":
			return EnfantScolaire.staticSolrFamilleNom(requeteSite_, (String)o);
		case "personneNomComplet":
			return EnfantScolaire.staticSolrPersonneNomComplet(requeteSite_, (String)o);
		case "personneNomCompletPrefere":
			return EnfantScolaire.staticSolrPersonneNomCompletPrefere(requeteSite_, (String)o);
		case "personneNomFormel":
			return EnfantScolaire.staticSolrPersonneNomFormel(requeteSite_, (String)o);
		case "personneDateNaissance":
			return EnfantScolaire.staticSolrPersonneDateNaissance(requeteSite_, (LocalDate)o);
		case "personneDateNaissanceDAnnee":
			return EnfantScolaire.staticSolrPersonneDateNaissanceDAnnee(requeteSite_, (Integer)o);
		case "personneDateNaissanceMoisDAnnee":
			return EnfantScolaire.staticSolrPersonneDateNaissanceMoisDAnnee(requeteSite_, (String)o);
		case "personneDateNaissanceJourDeSemaine":
			return EnfantScolaire.staticSolrPersonneDateNaissanceJourDeSemaine(requeteSite_, (String)o);
		case "personneAgeEnSeptembre":
			return EnfantScolaire.staticSolrPersonneAgeEnSeptembre(requeteSite_, (String)o);
		case "photo":
			return EnfantScolaire.staticSolrPhoto(requeteSite_, (String)o);
		case "enfantNomComplet":
			return EnfantScolaire.staticSolrEnfantNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrCluster(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrEnfantScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrEnfantScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "enfantCle":
			return EnfantScolaire.staticSolrStrEnfantCle(requeteSite_, (Long)o);
		case "inscriptionCles":
			return EnfantScolaire.staticSolrStrInscriptionCles(requeteSite_, (Long)o);
		case "familleTri":
			return EnfantScolaire.staticSolrStrFamilleTri(requeteSite_, (Integer)o);
		case "enfantTri":
			return EnfantScolaire.staticSolrStrEnfantTri(requeteSite_, (Integer)o);
		case "utilisateurCles":
			return EnfantScolaire.staticSolrStrUtilisateurCles(requeteSite_, (Long)o);
		case "ecoleCles":
			return EnfantScolaire.staticSolrStrEcoleCles(requeteSite_, (Long)o);
		case "anneeCles":
			return EnfantScolaire.staticSolrStrAnneeCles(requeteSite_, (Long)o);
		case "saisonCles":
			return EnfantScolaire.staticSolrStrSaisonCles(requeteSite_, (Long)o);
		case "sessionCles":
			return EnfantScolaire.staticSolrStrSessionCles(requeteSite_, (Long)o);
		case "ageCles":
			return EnfantScolaire.staticSolrStrAgeCles(requeteSite_, (Long)o);
		case "personnePrenom":
			return EnfantScolaire.staticSolrStrPersonnePrenom(requeteSite_, (String)o);
		case "personnePrenomPrefere":
			return EnfantScolaire.staticSolrStrPersonnePrenomPrefere(requeteSite_, (String)o);
		case "familleNom":
			return EnfantScolaire.staticSolrStrFamilleNom(requeteSite_, (String)o);
		case "personneNomComplet":
			return EnfantScolaire.staticSolrStrPersonneNomComplet(requeteSite_, (String)o);
		case "personneNomCompletPrefere":
			return EnfantScolaire.staticSolrStrPersonneNomCompletPrefere(requeteSite_, (String)o);
		case "personneNomFormel":
			return EnfantScolaire.staticSolrStrPersonneNomFormel(requeteSite_, (String)o);
		case "personneDateNaissance":
			return EnfantScolaire.staticSolrStrPersonneDateNaissance(requeteSite_, (Date)o);
		case "personneDateNaissanceDAnnee":
			return EnfantScolaire.staticSolrStrPersonneDateNaissanceDAnnee(requeteSite_, (Integer)o);
		case "personneDateNaissanceMoisDAnnee":
			return EnfantScolaire.staticSolrStrPersonneDateNaissanceMoisDAnnee(requeteSite_, (String)o);
		case "personneDateNaissanceJourDeSemaine":
			return EnfantScolaire.staticSolrStrPersonneDateNaissanceJourDeSemaine(requeteSite_, (String)o);
		case "personneAgeEnSeptembre":
			return EnfantScolaire.staticSolrStrPersonneAgeEnSeptembre(requeteSite_, (String)o);
		case "photo":
			return EnfantScolaire.staticSolrStrPhoto(requeteSite_, (String)o);
		case "enfantNomComplet":
			return EnfantScolaire.staticSolrStrEnfantNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqEnfantScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqEnfantScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "enfantCle":
			return EnfantScolaire.staticSolrFqEnfantCle(requeteSite_, o);
		case "inscriptionCles":
			return EnfantScolaire.staticSolrFqInscriptionCles(requeteSite_, o);
		case "familleTri":
			return EnfantScolaire.staticSolrFqFamilleTri(requeteSite_, o);
		case "enfantTri":
			return EnfantScolaire.staticSolrFqEnfantTri(requeteSite_, o);
		case "utilisateurCles":
			return EnfantScolaire.staticSolrFqUtilisateurCles(requeteSite_, o);
		case "ecoleCles":
			return EnfantScolaire.staticSolrFqEcoleCles(requeteSite_, o);
		case "anneeCles":
			return EnfantScolaire.staticSolrFqAnneeCles(requeteSite_, o);
		case "saisonCles":
			return EnfantScolaire.staticSolrFqSaisonCles(requeteSite_, o);
		case "sessionCles":
			return EnfantScolaire.staticSolrFqSessionCles(requeteSite_, o);
		case "ageCles":
			return EnfantScolaire.staticSolrFqAgeCles(requeteSite_, o);
		case "personnePrenom":
			return EnfantScolaire.staticSolrFqPersonnePrenom(requeteSite_, o);
		case "personnePrenomPrefere":
			return EnfantScolaire.staticSolrFqPersonnePrenomPrefere(requeteSite_, o);
		case "familleNom":
			return EnfantScolaire.staticSolrFqFamilleNom(requeteSite_, o);
		case "personneNomComplet":
			return EnfantScolaire.staticSolrFqPersonneNomComplet(requeteSite_, o);
		case "personneNomCompletPrefere":
			return EnfantScolaire.staticSolrFqPersonneNomCompletPrefere(requeteSite_, o);
		case "personneNomFormel":
			return EnfantScolaire.staticSolrFqPersonneNomFormel(requeteSite_, o);
		case "personneDateNaissance":
			return EnfantScolaire.staticSolrFqPersonneDateNaissance(requeteSite_, o);
		case "personneDateNaissanceDAnnee":
			return EnfantScolaire.staticSolrFqPersonneDateNaissanceDAnnee(requeteSite_, o);
		case "personneDateNaissanceMoisDAnnee":
			return EnfantScolaire.staticSolrFqPersonneDateNaissanceMoisDAnnee(requeteSite_, o);
		case "personneDateNaissanceJourDeSemaine":
			return EnfantScolaire.staticSolrFqPersonneDateNaissanceJourDeSemaine(requeteSite_, o);
		case "personneAgeEnSeptembre":
			return EnfantScolaire.staticSolrFqPersonneAgeEnSeptembre(requeteSite_, o);
		case "photo":
			return EnfantScolaire.staticSolrFqPhoto(requeteSite_, o);
		case "enfantNomComplet":
			return EnfantScolaire.staticSolrFqEnfantNomComplet(requeteSite_, o);
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
				if(val != null)
					setPersonnePrenom(val);
				sauvegardes.add(var);
				return val;
			case "personnePrenomPrefere":
				if(val != null)
					setPersonnePrenomPrefere(val);
				sauvegardes.add(var);
				return val;
			case "familleNom":
				if(val != null)
					setFamilleNom(val);
				sauvegardes.add(var);
				return val;
			case "personneDateNaissance":
				if(val != null)
					setPersonneDateNaissance(val);
				sauvegardes.add(var);
				return val;
			case "photo":
				if(val != null)
					setPhoto(val);
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
		peuplerEnfantScolaire(solrDocument);
	}
	public void peuplerEnfantScolaire(SolrDocument solrDocument) {
		EnfantScolaire oEnfantScolaire = (EnfantScolaire)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("enfantCle")) {
				Long enfantCle = (Long)solrDocument.get("enfantCle_stored_long");
				if(enfantCle != null)
					oEnfantScolaire.setEnfantCle(enfantCle);
			}

			List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
			if(inscriptionCles != null)
				oEnfantScolaire.inscriptionCles.addAll(inscriptionCles);

			if(sauvegardes.contains("familleTri")) {
				Integer familleTri = (Integer)solrDocument.get("familleTri_stored_int");
				if(familleTri != null)
					oEnfantScolaire.setFamilleTri(familleTri);
			}

			if(sauvegardes.contains("enfantTri")) {
				Integer enfantTri = (Integer)solrDocument.get("enfantTri_stored_int");
				if(enfantTri != null)
					oEnfantScolaire.setEnfantTri(enfantTri);
			}

			if(sauvegardes.contains("utilisateurCles")) {
				List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
				if(utilisateurCles != null)
					oEnfantScolaire.utilisateurCles.addAll(utilisateurCles);
			}

			if(sauvegardes.contains("ecoleCles")) {
				List<Long> ecoleCles = (List<Long>)solrDocument.get("ecoleCles_stored_longs");
				if(ecoleCles != null)
					oEnfantScolaire.ecoleCles.addAll(ecoleCles);
			}

			if(sauvegardes.contains("anneeCles")) {
				List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
				if(anneeCles != null)
					oEnfantScolaire.anneeCles.addAll(anneeCles);
			}

			if(sauvegardes.contains("saisonCles")) {
				List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
				if(saisonCles != null)
					oEnfantScolaire.saisonCles.addAll(saisonCles);
			}

			if(sauvegardes.contains("sessionCles")) {
				List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
				if(sessionCles != null)
					oEnfantScolaire.sessionCles.addAll(sessionCles);
			}

			if(sauvegardes.contains("ageCles")) {
				List<Long> ageCles = (List<Long>)solrDocument.get("ageCles_stored_longs");
				if(ageCles != null)
					oEnfantScolaire.ageCles.addAll(ageCles);
			}

			if(sauvegardes.contains("personnePrenom")) {
				String personnePrenom = (String)solrDocument.get("personnePrenom_stored_string");
				if(personnePrenom != null)
					oEnfantScolaire.setPersonnePrenom(personnePrenom);
			}

			if(sauvegardes.contains("personnePrenomPrefere")) {
				String personnePrenomPrefere = (String)solrDocument.get("personnePrenomPrefere_stored_string");
				if(personnePrenomPrefere != null)
					oEnfantScolaire.setPersonnePrenomPrefere(personnePrenomPrefere);
			}

			if(sauvegardes.contains("familleNom")) {
				String familleNom = (String)solrDocument.get("familleNom_stored_string");
				if(familleNom != null)
					oEnfantScolaire.setFamilleNom(familleNom);
			}

			if(sauvegardes.contains("personneNomComplet")) {
				String personneNomComplet = (String)solrDocument.get("personneNomComplet_stored_string");
				if(personneNomComplet != null)
					oEnfantScolaire.setPersonneNomComplet(personneNomComplet);
			}

			if(sauvegardes.contains("personneNomCompletPrefere")) {
				String personneNomCompletPrefere = (String)solrDocument.get("personneNomCompletPrefere_stored_string");
				if(personneNomCompletPrefere != null)
					oEnfantScolaire.setPersonneNomCompletPrefere(personneNomCompletPrefere);
			}

			if(sauvegardes.contains("personneNomFormel")) {
				String personneNomFormel = (String)solrDocument.get("personneNomFormel_stored_string");
				if(personneNomFormel != null)
					oEnfantScolaire.setPersonneNomFormel(personneNomFormel);
			}

			if(sauvegardes.contains("personneDateNaissance")) {
				Date personneDateNaissance = (Date)solrDocument.get("personneDateNaissance_stored_date");
				if(personneDateNaissance != null)
					oEnfantScolaire.setPersonneDateNaissance(personneDateNaissance);
			}

			if(sauvegardes.contains("personneDateNaissanceDAnnee")) {
				Integer personneDateNaissanceDAnnee = (Integer)solrDocument.get("personneDateNaissanceDAnnee_stored_int");
				if(personneDateNaissanceDAnnee != null)
					oEnfantScolaire.setPersonneDateNaissanceDAnnee(personneDateNaissanceDAnnee);
			}

			if(sauvegardes.contains("personneDateNaissanceMoisDAnnee")) {
				String personneDateNaissanceMoisDAnnee = (String)solrDocument.get("personneDateNaissanceMoisDAnnee_stored_string");
				if(personneDateNaissanceMoisDAnnee != null)
					oEnfantScolaire.setPersonneDateNaissanceMoisDAnnee(personneDateNaissanceMoisDAnnee);
			}

			if(sauvegardes.contains("personneDateNaissanceJourDeSemaine")) {
				String personneDateNaissanceJourDeSemaine = (String)solrDocument.get("personneDateNaissanceJourDeSemaine_stored_string");
				if(personneDateNaissanceJourDeSemaine != null)
					oEnfantScolaire.setPersonneDateNaissanceJourDeSemaine(personneDateNaissanceJourDeSemaine);
			}

			if(sauvegardes.contains("personneAgeEnSeptembre")) {
				String personneAgeEnSeptembre = (String)solrDocument.get("personneAgeEnSeptembre_stored_string");
				if(personneAgeEnSeptembre != null)
					oEnfantScolaire.setPersonneAgeEnSeptembre(personneAgeEnSeptembre);
			}

			if(sauvegardes.contains("photo")) {
				String photo = (String)solrDocument.get("photo_stored_string");
				if(photo != null)
					oEnfantScolaire.setPhoto(photo);
			}

			if(sauvegardes.contains("enfantNomComplet")) {
				String enfantNomComplet = (String)solrDocument.get("enfantNomComplet_stored_string");
				if(enfantNomComplet != null)
					oEnfantScolaire.setEnfantNomComplet(enfantNomComplet);
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
			clientSolr.commit(false, false, true);
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
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerEnfantScolaire(SolrInputDocument document) {
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
		if(utilisateurCles != null) {
			for(java.lang.Long o : utilisateurCles) {
				document.addField("utilisateurCles_indexed_longs", o);
			}
			for(java.lang.Long o : utilisateurCles) {
				document.addField("utilisateurCles_stored_longs", o);
			}
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
		if(personnePrenomPrefere != null) {
			document.addField("personnePrenomPrefere_indexed_string", personnePrenomPrefere);
			document.addField("personnePrenomPrefere_stored_string", personnePrenomPrefere);
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
			document.addField("personneDateNaissance_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(personneDateNaissance.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("personneDateNaissance_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(personneDateNaissance.atStartOfDay(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(personneDateNaissanceDAnnee != null) {
			document.addField("personneDateNaissanceDAnnee_indexed_int", personneDateNaissanceDAnnee);
			document.addField("personneDateNaissanceDAnnee_stored_int", personneDateNaissanceDAnnee);
		}
		if(personneDateNaissanceMoisDAnnee != null) {
			document.addField("personneDateNaissanceMoisDAnnee_indexed_string", personneDateNaissanceMoisDAnnee);
			document.addField("personneDateNaissanceMoisDAnnee_stored_string", personneDateNaissanceMoisDAnnee);
		}
		if(personneDateNaissanceJourDeSemaine != null) {
			document.addField("personneDateNaissanceJourDeSemaine_indexed_string", personneDateNaissanceJourDeSemaine);
			document.addField("personneDateNaissanceJourDeSemaine_stored_string", personneDateNaissanceJourDeSemaine);
		}
		if(personneAgeEnSeptembre != null) {
			document.addField("personneAgeEnSeptembre_indexed_string", personneAgeEnSeptembre);
			document.addField("personneAgeEnSeptembre_stored_string", personneAgeEnSeptembre);
		}
		if(photo != null) {
			document.addField("photo_stored_string", photo);
		}
		if(enfantNomComplet != null) {
			document.addField("enfantNomComplet_indexed_string", enfantNomComplet);
			document.addField("enfantNomComplet_stored_string", enfantNomComplet);
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
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeEnfantScolaire(String entiteVar) {
		switch(entiteVar) {
			case "enfantCle":
				return "enfantCle_indexed_long";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "familleTri":
				return "familleTri_indexed_int";
			case "enfantTri":
				return "enfantTri_indexed_int";
			case "utilisateurCles":
				return "utilisateurCles_indexed_longs";
			case "ecoleCles":
				return "ecoleCles_indexed_longs";
			case "anneeCles":
				return "anneeCles_indexed_longs";
			case "saisonCles":
				return "saisonCles_indexed_longs";
			case "sessionCles":
				return "sessionCles_indexed_longs";
			case "ageCles":
				return "ageCles_indexed_longs";
			case "personnePrenom":
				return "personnePrenom_indexed_string";
			case "personnePrenomPrefere":
				return "personnePrenomPrefere_indexed_string";
			case "familleNom":
				return "familleNom_indexed_string";
			case "personneNomComplet":
				return "personneNomComplet_indexed_string";
			case "personneNomCompletPrefere":
				return "personneNomCompletPrefere_indexed_string";
			case "personneNomFormel":
				return "personneNomFormel_indexed_string";
			case "personneDateNaissance":
				return "personneDateNaissance_indexed_date";
			case "personneDateNaissanceDAnnee":
				return "personneDateNaissanceDAnnee_indexed_int";
			case "personneDateNaissanceMoisDAnnee":
				return "personneDateNaissanceMoisDAnnee_indexed_string";
			case "personneDateNaissanceJourDeSemaine":
				return "personneDateNaissanceJourDeSemaine_indexed_string";
			case "personneAgeEnSeptembre":
				return "personneAgeEnSeptembre_indexed_string";
			case "enfantNomComplet":
				return "enfantNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheEnfantScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereEnfantScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
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

		List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
		if(utilisateurCles != null)
			oEnfantScolaire.utilisateurCles.addAll(utilisateurCles);

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

		String personnePrenomPrefere = (String)solrDocument.get("personnePrenomPrefere_stored_string");
		if(personnePrenomPrefere != null)
			oEnfantScolaire.setPersonnePrenomPrefere(personnePrenomPrefere);

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

		Integer personneDateNaissanceDAnnee = (Integer)solrDocument.get("personneDateNaissanceDAnnee_stored_int");
		if(personneDateNaissanceDAnnee != null)
			oEnfantScolaire.setPersonneDateNaissanceDAnnee(personneDateNaissanceDAnnee);

		String personneDateNaissanceMoisDAnnee = (String)solrDocument.get("personneDateNaissanceMoisDAnnee_stored_string");
		if(personneDateNaissanceMoisDAnnee != null)
			oEnfantScolaire.setPersonneDateNaissanceMoisDAnnee(personneDateNaissanceMoisDAnnee);

		String personneDateNaissanceJourDeSemaine = (String)solrDocument.get("personneDateNaissanceJourDeSemaine_stored_string");
		if(personneDateNaissanceJourDeSemaine != null)
			oEnfantScolaire.setPersonneDateNaissanceJourDeSemaine(personneDateNaissanceJourDeSemaine);

		String personneAgeEnSeptembre = (String)solrDocument.get("personneAgeEnSeptembre_stored_string");
		if(personneAgeEnSeptembre != null)
			oEnfantScolaire.setPersonneAgeEnSeptembre(personneAgeEnSeptembre);

		String photo = (String)solrDocument.get("photo_stored_string");
		if(photo != null)
			oEnfantScolaire.setPhoto(photo);

		String enfantNomComplet = (String)solrDocument.get("enfantNomComplet_stored_string");
		if(enfantNomComplet != null)
			oEnfantScolaire.setEnfantNomComplet(enfantNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiEnfantScolaire() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof EnfantScolaire) {
			EnfantScolaire original = (EnfantScolaire)o;
			if(!Objects.equals(enfantCle, original.getEnfantCle()))
				requeteApi.addVars("enfantCle");
			if(!Objects.equals(inscriptionCles, original.getInscriptionCles()))
				requeteApi.addVars("inscriptionCles");
			if(!Objects.equals(familleTri, original.getFamilleTri()))
				requeteApi.addVars("familleTri");
			if(!Objects.equals(enfantTri, original.getEnfantTri()))
				requeteApi.addVars("enfantTri");
			if(!Objects.equals(utilisateurCles, original.getUtilisateurCles()))
				requeteApi.addVars("utilisateurCles");
			if(!Objects.equals(ecoleCles, original.getEcoleCles()))
				requeteApi.addVars("ecoleCles");
			if(!Objects.equals(anneeCles, original.getAnneeCles()))
				requeteApi.addVars("anneeCles");
			if(!Objects.equals(saisonCles, original.getSaisonCles()))
				requeteApi.addVars("saisonCles");
			if(!Objects.equals(sessionCles, original.getSessionCles()))
				requeteApi.addVars("sessionCles");
			if(!Objects.equals(ageCles, original.getAgeCles()))
				requeteApi.addVars("ageCles");
			if(!Objects.equals(personnePrenom, original.getPersonnePrenom()))
				requeteApi.addVars("personnePrenom");
			if(!Objects.equals(personnePrenomPrefere, original.getPersonnePrenomPrefere()))
				requeteApi.addVars("personnePrenomPrefere");
			if(!Objects.equals(familleNom, original.getFamilleNom()))
				requeteApi.addVars("familleNom");
			if(!Objects.equals(personneNomComplet, original.getPersonneNomComplet()))
				requeteApi.addVars("personneNomComplet");
			if(!Objects.equals(personneNomCompletPrefere, original.getPersonneNomCompletPrefere()))
				requeteApi.addVars("personneNomCompletPrefere");
			if(!Objects.equals(personneNomFormel, original.getPersonneNomFormel()))
				requeteApi.addVars("personneNomFormel");
			if(!Objects.equals(personneDateNaissance, original.getPersonneDateNaissance()))
				requeteApi.addVars("personneDateNaissance");
			if(!Objects.equals(personneDateNaissanceDAnnee, original.getPersonneDateNaissanceDAnnee()))
				requeteApi.addVars("personneDateNaissanceDAnnee");
			if(!Objects.equals(personneDateNaissanceMoisDAnnee, original.getPersonneDateNaissanceMoisDAnnee()))
				requeteApi.addVars("personneDateNaissanceMoisDAnnee");
			if(!Objects.equals(personneDateNaissanceJourDeSemaine, original.getPersonneDateNaissanceJourDeSemaine()))
				requeteApi.addVars("personneDateNaissanceJourDeSemaine");
			if(!Objects.equals(personneAgeEnSeptembre, original.getPersonneAgeEnSeptembre()))
				requeteApi.addVars("personneAgeEnSeptembre");
			if(!Objects.equals(photo, original.getPhoto()))
				requeteApi.addVars("photo");
			if(!Objects.equals(enfantNomComplet, original.getEnfantNomComplet()))
				requeteApi.addVars("enfantNomComplet");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), enfantCle, inscriptionCles, familleTri, enfantTri, utilisateurCles, ecoleCles, anneeCles, saisonCles, sessionCles, ageCles, personnePrenom, personnePrenomPrefere, familleNom, personneNomComplet, personneNomCompletPrefere, personneNomFormel, personneDateNaissance, personneDateNaissanceDAnnee, personneDateNaissanceMoisDAnnee, personneDateNaissanceJourDeSemaine, personneAgeEnSeptembre, photo, enfantNomComplet);
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
				&& Objects.equals( enfantCle, that.enfantCle )
				&& Objects.equals( inscriptionCles, that.inscriptionCles )
				&& Objects.equals( familleTri, that.familleTri )
				&& Objects.equals( enfantTri, that.enfantTri )
				&& Objects.equals( utilisateurCles, that.utilisateurCles )
				&& Objects.equals( ecoleCles, that.ecoleCles )
				&& Objects.equals( anneeCles, that.anneeCles )
				&& Objects.equals( saisonCles, that.saisonCles )
				&& Objects.equals( sessionCles, that.sessionCles )
				&& Objects.equals( ageCles, that.ageCles )
				&& Objects.equals( personnePrenom, that.personnePrenom )
				&& Objects.equals( personnePrenomPrefere, that.personnePrenomPrefere )
				&& Objects.equals( familleNom, that.familleNom )
				&& Objects.equals( personneNomComplet, that.personneNomComplet )
				&& Objects.equals( personneNomCompletPrefere, that.personneNomCompletPrefere )
				&& Objects.equals( personneNomFormel, that.personneNomFormel )
				&& Objects.equals( personneDateNaissance, that.personneDateNaissance )
				&& Objects.equals( personneDateNaissanceDAnnee, that.personneDateNaissanceDAnnee )
				&& Objects.equals( personneDateNaissanceMoisDAnnee, that.personneDateNaissanceMoisDAnnee )
				&& Objects.equals( personneDateNaissanceJourDeSemaine, that.personneDateNaissanceJourDeSemaine )
				&& Objects.equals( personneAgeEnSeptembre, that.personneAgeEnSeptembre )
				&& Objects.equals( photo, that.photo )
				&& Objects.equals( enfantNomComplet, that.enfantNomComplet );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EnfantScolaire { ");
		sb.append( "enfantCle: " ).append(enfantCle);
		sb.append( ", inscriptionCles: " ).append(inscriptionCles);
		sb.append( ", familleTri: " ).append(familleTri);
		sb.append( ", enfantTri: " ).append(enfantTri);
		sb.append( ", utilisateurCles: " ).append(utilisateurCles);
		sb.append( ", ecoleCles: " ).append(ecoleCles);
		sb.append( ", anneeCles: " ).append(anneeCles);
		sb.append( ", saisonCles: " ).append(saisonCles);
		sb.append( ", sessionCles: " ).append(sessionCles);
		sb.append( ", ageCles: " ).append(ageCles);
		sb.append( ", personnePrenom: \"" ).append(personnePrenom).append( "\"" );
		sb.append( ", personnePrenomPrefere: \"" ).append(personnePrenomPrefere).append( "\"" );
		sb.append( ", familleNom: \"" ).append(familleNom).append( "\"" );
		sb.append( ", personneNomComplet: \"" ).append(personneNomComplet).append( "\"" );
		sb.append( ", personneNomCompletPrefere: \"" ).append(personneNomCompletPrefere).append( "\"" );
		sb.append( ", personneNomFormel: \"" ).append(personneNomFormel).append( "\"" );
		sb.append( ", personneDateNaissance: " ).append(personneDateNaissance);
		sb.append( ", personneDateNaissanceDAnnee: " ).append(personneDateNaissanceDAnnee);
		sb.append( ", personneDateNaissanceMoisDAnnee: \"" ).append(personneDateNaissanceMoisDAnnee).append( "\"" );
		sb.append( ", personneDateNaissanceJourDeSemaine: \"" ).append(personneDateNaissanceJourDeSemaine).append( "\"" );
		sb.append( ", personneAgeEnSeptembre: \"" ).append(personneAgeEnSeptembre).append( "\"" );
		sb.append( ", photo: \"" ).append(photo).append( "\"" );
		sb.append( ", enfantNomComplet: \"" ).append(enfantNomComplet).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
