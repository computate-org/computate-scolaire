package org.computate.scolaire.frFR.age;

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
import org.computate.scolaire.frFR.bloc.BlocScolaire;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe ageCompleteName dans Solr. </a>
 * <br/>
 **/
public abstract class AgeScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(AgeScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String AgeScolaire_UnNom = "un âge";
	public static final String AgeScolaire_Ce = "ce ";
	public static final String AgeScolaire_CeNom = "cet âge";
	public static final String AgeScolaire_Un = "un ";
	public static final String AgeScolaire_LeNom = "l'âge";
	public static final String AgeScolaire_NomSingulier = "âge";
	public static final String AgeScolaire_NomPluriel = "âges";
	public static final String AgeScolaire_NomActuel = "âge actuel";
	public static final String AgeScolaire_Tous = "all ";
	public static final String AgeScolaire_TousNom = "tous les âges";
	public static final String AgeScolaire_RechercherTousNomPar = "rechercher âges par ";
	public static final String AgeScolaire_RechercherTousNom = "rechercher âges";
	public static final String AgeScolaire_Titre = "âges";
	public static final String AgeScolaire_LesNom = "les âges";
	public static final String AgeScolaire_AucunNomTrouve = "aucun âge trouvé";
	public static final String AgeScolaire_NomVar = "âge";
	public static final String AgeScolaire_DeNom = "d'âge";
	public static final String AgeScolaire_NomAdjectifSingulier = "âge";
	public static final String AgeScolaire_NomAdjectifPluriel = "âges";
	public static final String AgeScolaire_Couleur = "blue";
	public static final String AgeScolaire_IconeGroupe = "duotone";
	public static final String AgeScolaire_IconeNom = "birthday-cake";

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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCle">Trouver l'entité ageCle dans Solr</a>
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
	public AgeScolaire setAgeCle(String o) {
		if(NumberUtils.isParsable(o))
			this.ageCle = Long.parseLong(o);
		this.ageCleCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire ageCleInit() {
		if(!ageCleCouverture.dejaInitialise) {
			_ageCle(ageCleCouverture);
			if(ageCle == null)
				setAgeCle(ageCleCouverture.o);
		}
		ageCleCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
		return "clé";
	}

	public String htmTooltipAgeCle() {
		return null;
	}

	public String htmAgeCle() {
		return ageCle == null ? "" : StringEscapeUtils.escapeHtml4(strAgeCle());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
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
	public AgeScolaire addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (AgeScolaire)this;
	}
	public AgeScolaire addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (AgeScolaire)this;
	}
	public AgeScolaire setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
		return (AgeScolaire)this;
	}
	public AgeScolaire addInscriptionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (AgeScolaire)this;
	}
	protected AgeScolaire inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
		return null;
	}

	public String htmTooltipInscriptionCles() {
		return null;
	}

	public String htmInscriptionCles() {
		return inscriptionCles == null ? "" : StringEscapeUtils.escapeHtml4(strInscriptionCles());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:blocCles">Trouver l'entité blocCles dans Solr</a>
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
	public AgeScolaire addBlocCles(Long...objets) {
		for(Long o : objets) {
			addBlocCles(o);
		}
		return (AgeScolaire)this;
	}
	public AgeScolaire addBlocCles(Long o) {
		if(o != null && !blocCles.contains(o))
			this.blocCles.add(o);
		return (AgeScolaire)this;
	}
	public AgeScolaire setBlocCles(JsonArray objets) {
		blocCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addBlocCles(o);
		}
		return (AgeScolaire)this;
	}
	public AgeScolaire addBlocCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addBlocCles(p);
		}
		return (AgeScolaire)this;
	}
	protected AgeScolaire blocClesInit() {
		if(!blocClesCouverture.dejaInitialise) {
			_blocCles(blocCles);
		}
		blocClesCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
		AgeScolaire s = (AgeScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "blocs")
					.a("title", "L'année scolaire de la saison scolaire. ")
					.a("class", "valeur suggereBlocCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setBlocCles")
					.a("id", classeApiMethodeMethode, "_blocCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereAgeScolaireBlocCles($(this).val() ? rechercherBlocScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'ageCle:" + pk + "'}", "], $('#listAgeScolaireBlocCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varAgeScolaire", pk, "BlocCles ").f().sx(htmBlocCles()).g("span");
			}
		}
	}

	public void htmBlocCles(String classeApiMethodeMethode) {
		AgeScolaire s = (AgeScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "AgeScolaireBlocCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/bloc?fq=ageCle:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-indigo w3-hover-indigo ").f();
								e("i").a("class", "far fa-bell ").f().g("i");
								sx("blocs");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cet âge");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listAgeScolaireBlocCles_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), BlocScolaire.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), BlocScolaire.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
											.a("id", classeApiMethodeMethode, "_blocCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postBlocScolaireVals({ ageCle: \"", pk, "\" }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "blocCles')); });")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:scolaireTri">Trouver l'entité scolaireTri dans Solr</a>
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
	public AgeScolaire setScolaireTri(String o) {
		if(NumberUtils.isParsable(o))
			this.scolaireTri = Integer.parseInt(o);
		this.scolaireTriCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire scolaireTriInit() {
		if(!scolaireTriCouverture.dejaInitialise) {
			_scolaireTri(scolaireTriCouverture);
			if(scolaireTri == null)
				setScolaireTri(scolaireTriCouverture.o);
		}
		scolaireTriCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleTri">Trouver l'entité ecoleTri dans Solr</a>
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
	public AgeScolaire setEcoleTri(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleTri = Integer.parseInt(o);
		this.ecoleTriCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire ecoleTriInit() {
		if(!ecoleTriCouverture.dejaInitialise) {
			_ecoleTri(ecoleTriCouverture);
			if(ecoleTri == null)
				setEcoleTri(ecoleTriCouverture.o);
		}
		ecoleTriCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeTri">Trouver l'entité anneeTri dans Solr</a>
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
	public AgeScolaire setAnneeTri(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeTri = Integer.parseInt(o);
		this.anneeTriCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire anneeTriInit() {
		if(!anneeTriCouverture.dejaInitialise) {
			_anneeTri(anneeTriCouverture);
			if(anneeTri == null)
				setAnneeTri(anneeTriCouverture.o);
		}
		anneeTriCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonTri">Trouver l'entité saisonTri dans Solr</a>
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
	public AgeScolaire setSaisonTri(String o) {
		if(NumberUtils.isParsable(o))
			this.saisonTri = Integer.parseInt(o);
		this.saisonTriCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire saisonTriInit() {
		if(!saisonTriCouverture.dejaInitialise) {
			_saisonTri(saisonTriCouverture);
			if(saisonTri == null)
				setSaisonTri(saisonTriCouverture.o);
		}
		saisonTriCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionTri">Trouver l'entité sessionTri dans Solr</a>
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
	public AgeScolaire setSessionTri(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionTri = Integer.parseInt(o);
		this.sessionTriCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire sessionTriInit() {
		if(!sessionTriCouverture.dejaInitialise) {
			_sessionTri(sessionTriCouverture);
			if(sessionTri == null)
				setSessionTri(sessionTriCouverture.o);
		}
		sessionTriCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCle">Trouver l'entité sessionCle dans Solr</a>
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
	public AgeScolaire setSessionCle(String o) {
		if(NumberUtils.isParsable(o))
			this.sessionCle = Long.parseLong(o);
		this.sessionCleCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire sessionCleInit() {
		if(!sessionCleCouverture.dejaInitialise) {
			_sessionCle(sessionCleCouverture);
			if(sessionCle == null)
				setSessionCle(sessionCleCouverture.o);
		}
		sessionCleCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeRecherche">Trouver l'entité anneeRecherche dans Solr</a>
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
	protected AgeScolaire anneeRechercheInit() {
		if(!anneeRechercheCouverture.dejaInitialise) {
			_anneeRecherche(anneeRecherche);
		}
		anneeRecherche.initLoinPourClasse(requeteSite_);
		anneeRechercheCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:annee_">Trouver l'entité annee_ dans Solr</a>
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
	protected AgeScolaire annee_Init() {
		if(!annee_Couverture.dejaInitialise) {
			_annee_(annee_Couverture);
			if(annee_ == null)
				setAnnee_(annee_Couverture.o);
		}
		annee_Couverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
	public AgeScolaire setEcoleCle(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleCle = Long.parseLong(o);
		this.ecoleCleCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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
	public AgeScolaire setAnneeCle(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeCle = Long.parseLong(o);
		this.anneeCleCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire anneeCleInit() {
		if(!anneeCleCouverture.dejaInitialise) {
			_anneeCle(anneeCleCouverture);
			if(anneeCle == null)
				setAnneeCle(anneeCleCouverture.o);
		}
		anneeCleCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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

	public void inputAnneeCle(String classeApiMethodeMethode) {
		AgeScolaire s = (AgeScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "année")
					.a("title", "L'année scolaire de la saison scolaire. ")
					.a("class", "valeur suggereAnneeCle w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setAnneeCle")
					.a("id", classeApiMethodeMethode, "_anneeCle")
					.a("autocomplete", "off")
					.a("oninput", "suggereAgeScolaireAnneeCle($(this).val() ? rechercherAnneeScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'ageCles:" + pk + "'}", "], $('#listAgeScolaireAnneeCle_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varAgeScolaire", pk, "AnneeCle ").f().sx(htmAnneeCle()).g("span");
			}
		}
	}

	public void htmAnneeCle(String classeApiMethodeMethode) {
		AgeScolaire s = (AgeScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "AgeScolaireAnneeCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/annee?fq=ageCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-orange w3-hover-orange ").f();
								e("i").a("class", "far fa-calendar-check ").f().g("i");
								sx("année");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier une année a cet âge");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listAgeScolaireAnneeCle_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), AnneeScolaire.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), AnneeScolaire.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
											.a("id", classeApiMethodeMethode, "_anneeCle_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postAnneeScolaireVals({ ageCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "anneeCle')); });")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
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
	protected AgeScolaire ecoleNomInit() {
		if(!ecoleNomCouverture.dejaInitialise) {
			_ecoleNom(ecoleNomCouverture);
			if(ecoleNom == null)
				setEcoleNom(ecoleNomCouverture.o);
		}
		ecoleNomCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
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
	protected AgeScolaire ecoleNomCompletInit() {
		if(!ecoleNomCompletCouverture.dejaInitialise) {
			_ecoleNomComplet(ecoleNomCompletCouverture);
			if(ecoleNomComplet == null)
				setEcoleNomComplet(ecoleNomCompletCouverture.o);
		}
		ecoleNomCompletCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleEmplacement">Trouver l'entité ecoleEmplacement dans Solr</a>
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
	protected AgeScolaire ecoleEmplacementInit() {
		if(!ecoleEmplacementCouverture.dejaInitialise) {
			_ecoleEmplacement(ecoleEmplacementCouverture);
			if(ecoleEmplacement == null)
				setEcoleEmplacement(ecoleEmplacementCouverture.o);
		}
		ecoleEmplacementCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
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
	protected AgeScolaire ecoleAddresseInit() {
		if(!ecoleAddresseCouverture.dejaInitialise) {
			_ecoleAddresse(ecoleAddresseCouverture);
			if(ecoleAddresse == null)
				setEcoleAddresse(ecoleAddresseCouverture.o);
		}
		ecoleAddresseCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
		AgeScolaire s = (AgeScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "addresse")
				.a("title", "L'année scolaire de la saison scolaire. ")
				.a("id", classeApiMethodeMethode, "_ecoleAddresse");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setEcoleAddresse classAgeScolaire inputAgeScolaire", pk, "EcoleAddresse w3-input w3-border ");
					a("name", "setEcoleAddresse");
				} else {
					a("class", "valeurEcoleAddresse w3-input w3-border classAgeScolaire inputAgeScolaire", pk, "EcoleAddresse w3-input w3-border ");
					a("name", "ecoleAddresse");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setEcoleAddresse', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }); ");
				}
				a("value", strEcoleAddresse())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varAgeScolaire", pk, "EcoleAddresse ").f().sx(htmEcoleAddresse()).g("span");
			}
		}
	}

	public void htmEcoleAddresse(String classeApiMethodeMethode) {
		AgeScolaire s = (AgeScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "AgeScolaireEcoleAddresse").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", classeApiMethodeMethode, "_ecoleAddresse").a("class", "").f().sx("addresse").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputEcoleAddresse(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); $('#", classeApiMethodeMethode, "_ecoleAddresse').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#AgeScolaireForm :input[name=pk]').val() }], 'setEcoleAddresse', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ecoleAddresse')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
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
	protected AgeScolaire ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneCouverture.dejaInitialise) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneCouverture);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneCouverture.o);
		}
		ecoleNumeroTelephoneCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleForm">Trouver l'entité ecoleForm dans Solr</a>
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
	protected AgeScolaire ecoleFormInit() {
		if(!ecoleFormCouverture.dejaInitialise) {
			_ecoleForm(ecoleFormCouverture);
			if(ecoleForm == null)
				setEcoleForm(ecoleFormCouverture.o);
		}
		ecoleFormCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumero">Trouver l'entité ecoleNumero dans Solr</a>
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
	public AgeScolaire setEcoleNumero(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleNumero = Integer.parseInt(o);
		this.ecoleNumeroCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire ecoleNumeroInit() {
		if(!ecoleNumeroCouverture.dejaInitialise) {
			_ecoleNumero(ecoleNumeroCouverture);
			if(ecoleNumero == null)
				setEcoleNumero(ecoleNumeroCouverture.o);
		}
		ecoleNumeroCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAdministrateurNom">Trouver l'entité ecoleAdministrateurNom dans Solr</a>
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
	protected AgeScolaire ecoleAdministrateurNomInit() {
		if(!ecoleAdministrateurNomCouverture.dejaInitialise) {
			_ecoleAdministrateurNom(ecoleAdministrateurNomCouverture);
			if(ecoleAdministrateurNom == null)
				setEcoleAdministrateurNom(ecoleAdministrateurNomCouverture.o);
		}
		ecoleAdministrateurNomCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
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
	public AgeScolaire setAnneeDebut(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeDebut = Integer.parseInt(o);
		this.anneeDebutCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire anneeDebutInit() {
		if(!anneeDebutCouverture.dejaInitialise) {
			_anneeDebut(anneeDebutCouverture);
			if(anneeDebut == null)
				setAnneeDebut(anneeDebutCouverture.o);
		}
		anneeDebutCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
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
	public AgeScolaire setAnneeFin(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeFin = Integer.parseInt(o);
		this.anneeFinCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire anneeFinInit() {
		if(!anneeFinCouverture.dejaInitialise) {
			_anneeFin(anneeFinCouverture);
			if(anneeFin == null)
				setAnneeFin(anneeFinCouverture.o);
		}
		anneeFinCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonDateDebut">Trouver l'entité saisonDateDebut dans Solr</a>
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
	public AgeScolaire setSaisonDateDebut(Instant o) {
		this.saisonDateDebut = o == null ? null : LocalDate.from(o);
		this.saisonDateDebutCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public AgeScolaire setSaisonDateDebut(String o) {
		this.saisonDateDebut = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.saisonDateDebutCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	public AgeScolaire setSaisonDateDebut(Date o) {
		this.saisonDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.saisonDateDebutCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire saisonDateDebutInit() {
		if(!saisonDateDebutCouverture.dejaInitialise) {
			_saisonDateDebut(saisonDateDebutCouverture);
			if(saisonDateDebut == null)
				setSaisonDateDebut(saisonDateDebutCouverture.o);
		}
		saisonDateDebutCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFraisInscription">Trouver l'entité anneeFraisInscription dans Solr</a>
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
	public AgeScolaire setAnneeFraisInscription(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	public AgeScolaire setAnneeFraisInscription(Double o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	public AgeScolaire setAnneeFraisInscription(Integer o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire anneeFraisInscriptionInit() {
		if(!anneeFraisInscriptionCouverture.dejaInitialise) {
			_anneeFraisInscription(anneeFraisInscriptionCouverture);
			if(anneeFraisInscription == null)
				setAnneeFraisInscription(anneeFraisInscriptionCouverture.o);
		}
		anneeFraisInscriptionCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
	}

	public Double solrAnneeFraisInscription() {
		return anneeFraisInscription == null ? null : anneeFraisInscription.doubleValue();
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

	///////////////////
	// anneeNomCourt //
	///////////////////

	/**	 L'entité anneeNomCourt
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String anneeNomCourt;
	@JsonIgnore
	public Couverture<String> anneeNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("anneeNomCourt").o(anneeNomCourt);

	/**	<br/> L'entité anneeNomCourt
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeNomCourt">Trouver l'entité anneeNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeNomCourt(Couverture<String> c);

	public String getAnneeNomCourt() {
		return anneeNomCourt;
	}

	public void setAnneeNomCourt(String anneeNomCourt) {
		this.anneeNomCourt = anneeNomCourt;
		this.anneeNomCourtCouverture.dejaInitialise = true;
	}
	protected AgeScolaire anneeNomCourtInit() {
		if(!anneeNomCourtCouverture.dejaInitialise) {
			_anneeNomCourt(anneeNomCourtCouverture);
			if(anneeNomCourt == null)
				setAnneeNomCourt(anneeNomCourtCouverture.o);
		}
		anneeNomCourtCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
	}

	public String solrAnneeNomCourt() {
		return anneeNomCourt;
	}

	public String strAnneeNomCourt() {
		return anneeNomCourt == null ? "" : anneeNomCourt;
	}

	public String jsonAnneeNomCourt() {
		return anneeNomCourt == null ? "" : anneeNomCourt;
	}

	public String nomAffichageAnneeNomCourt() {
		return null;
	}

	public String htmTooltipAnneeNomCourt() {
		return null;
	}

	public String htmAnneeNomCourt() {
		return anneeNomCourt == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeNomCourt());
	}

	/////////////////////
	// anneeNomComplet //
	/////////////////////

	/**	 L'entité anneeNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String anneeNomComplet;
	@JsonIgnore
	public Couverture<String> anneeNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("anneeNomComplet").o(anneeNomComplet);

	/**	<br/> L'entité anneeNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeNomComplet">Trouver l'entité anneeNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _anneeNomComplet(Couverture<String> c);

	public String getAnneeNomComplet() {
		return anneeNomComplet;
	}

	public void setAnneeNomComplet(String anneeNomComplet) {
		this.anneeNomComplet = anneeNomComplet;
		this.anneeNomCompletCouverture.dejaInitialise = true;
	}
	protected AgeScolaire anneeNomCompletInit() {
		if(!anneeNomCompletCouverture.dejaInitialise) {
			_anneeNomComplet(anneeNomCompletCouverture);
			if(anneeNomComplet == null)
				setAnneeNomComplet(anneeNomCompletCouverture.o);
		}
		anneeNomCompletCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
	}

	public String solrAnneeNomComplet() {
		return anneeNomComplet;
	}

	public String strAnneeNomComplet() {
		return anneeNomComplet == null ? "" : anneeNomComplet;
	}

	public String jsonAnneeNomComplet() {
		return anneeNomComplet == null ? "" : anneeNomComplet;
	}

	public String nomAffichageAnneeNomComplet() {
		return null;
	}

	public String htmTooltipAnneeNomComplet() {
		return null;
	}

	public String htmAnneeNomComplet() {
		return anneeNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeNomComplet());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionDateDebut">Trouver l'entité sessionDateDebut dans Solr</a>
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
	public AgeScolaire setSessionDateDebut(Instant o) {
		this.sessionDateDebut = o == null ? null : LocalDate.from(o);
		this.sessionDateDebutCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public AgeScolaire setSessionDateDebut(String o) {
		this.sessionDateDebut = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionDateDebutCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	public AgeScolaire setSessionDateDebut(Date o) {
		this.sessionDateDebut = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateDebutCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire sessionDateDebutInit() {
		if(!sessionDateDebutCouverture.dejaInitialise) {
			_sessionDateDebut(sessionDateDebutCouverture);
			if(sessionDateDebut == null)
				setSessionDateDebut(sessionDateDebutCouverture.o);
		}
		sessionDateDebutCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionDateFin">Trouver l'entité sessionDateFin dans Solr</a>
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
	public AgeScolaire setSessionDateFin(Instant o) {
		this.sessionDateFin = o == null ? null : LocalDate.from(o);
		this.sessionDateFinCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public AgeScolaire setSessionDateFin(String o) {
		this.sessionDateFin = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionDateFinCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	public AgeScolaire setSessionDateFin(Date o) {
		this.sessionDateFin = o == null ? null : o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.sessionDateFinCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire sessionDateFinInit() {
		if(!sessionDateFinCouverture.dejaInitialise) {
			_sessionDateFin(sessionDateFinCouverture);
			if(sessionDateFin == null)
				setSessionDateFin(sessionDateFinCouverture.o);
		}
		sessionDateFinCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageDebut">Trouver l'entité ageDebut dans Solr</a>
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
	public AgeScolaire setAgeDebut(String o) {
		if(NumberUtils.isParsable(o))
			this.ageDebut = Integer.parseInt(o);
		this.ageDebutCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire ageDebutInit() {
		if(!ageDebutCouverture.dejaInitialise) {
			_ageDebut(ageDebutCouverture);
			if(ageDebut == null)
				setAgeDebut(ageDebutCouverture.o);
		}
		ageDebutCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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

	public void inputAgeDebut(String classeApiMethodeMethode) {
		AgeScolaire s = (AgeScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "début du groupe d'âge")
				.a("title", "L'année scolaire de la saison scolaire. ")
				.a("id", classeApiMethodeMethode, "_ageDebut");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setAgeDebut classAgeScolaire inputAgeScolaire", pk, "AgeDebut w3-input w3-border ");
					a("name", "setAgeDebut");
				} else {
					a("class", "valeurAgeDebut w3-input w3-border classAgeScolaire inputAgeScolaire", pk, "AgeDebut w3-input w3-border ");
					a("name", "ageDebut");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setAgeDebut', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ageDebut')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ageDebut')); }); ");
				}
				a("value", strAgeDebut())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varAgeScolaire", pk, "AgeDebut ").f().sx(htmAgeDebut()).g("span");
			}
		}
	}

	public void htmAgeDebut(String classeApiMethodeMethode) {
		AgeScolaire s = (AgeScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "AgeScolaireAgeDebut").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", classeApiMethodeMethode, "_ageDebut").a("class", "").f().sx("début du groupe d'âge").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputAgeDebut(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ageDebut')); $('#", classeApiMethodeMethode, "_ageDebut').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#AgeScolaireForm :input[name=pk]').val() }], 'setAgeDebut', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ageDebut')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ageDebut')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageFin">Trouver l'entité ageFin dans Solr</a>
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
	public AgeScolaire setAgeFin(String o) {
		if(NumberUtils.isParsable(o))
			this.ageFin = Integer.parseInt(o);
		this.ageFinCouverture.dejaInitialise = true;
		return (AgeScolaire)this;
	}
	protected AgeScolaire ageFinInit() {
		if(!ageFinCouverture.dejaInitialise) {
			_ageFin(ageFinCouverture);
			if(ageFin == null)
				setAgeFin(ageFinCouverture.o);
		}
		ageFinCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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

	public void inputAgeFin(String classeApiMethodeMethode) {
		AgeScolaire s = (AgeScolaire)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "fin du groupe d'âge")
				.a("title", "L'année scolaire de la saison scolaire. ")
				.a("id", classeApiMethodeMethode, "_ageFin");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setAgeFin classAgeScolaire inputAgeScolaire", pk, "AgeFin w3-input w3-border ");
					a("name", "setAgeFin");
				} else {
					a("class", "valeurAgeFin w3-input w3-border classAgeScolaire inputAgeScolaire", pk, "AgeFin w3-input w3-border ");
					a("name", "ageFin");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setAgeFin', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_ageFin')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ageFin')); }); ");
				}
				a("value", strAgeFin())
			.fg();

		} else {
			if(
					CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
					|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
				e("span").a("class", "varAgeScolaire", pk, "AgeFin ").f().sx(htmAgeFin()).g("span");
			}
		}
	}

	public void htmAgeFin(String classeApiMethodeMethode) {
		AgeScolaire s = (AgeScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "AgeScolaireAgeFin").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-blue ").f();
							e("label").a("for", classeApiMethodeMethode, "_ageFin").a("class", "").f().sx("fin du groupe d'âge").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputAgeFin(classeApiMethodeMethode);
							} g("div");
							if(
									CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
									|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
									) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-blue ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_ageFin')); $('#", classeApiMethodeMethode, "_ageFin').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#AgeScolaireForm :input[name=pk]').val() }], 'setAgeFin', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_ageFin')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_ageFin')); }); ")
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

	/////////////////
	// ageNomCourt //
	/////////////////

	/**	 L'entité ageNomCourt
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ageNomCourt;
	@JsonIgnore
	public Couverture<String> ageNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("ageNomCourt").o(ageNomCourt);

	/**	<br/> L'entité ageNomCourt
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageNomCourt">Trouver l'entité ageNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _ageNomCourt(Couverture<String> c);

	public String getAgeNomCourt() {
		return ageNomCourt;
	}

	public void setAgeNomCourt(String ageNomCourt) {
		this.ageNomCourt = ageNomCourt;
		this.ageNomCourtCouverture.dejaInitialise = true;
	}
	protected AgeScolaire ageNomCourtInit() {
		if(!ageNomCourtCouverture.dejaInitialise) {
			_ageNomCourt(ageNomCourtCouverture);
			if(ageNomCourt == null)
				setAgeNomCourt(ageNomCourtCouverture.o);
		}
		ageNomCourtCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
	}

	public String solrAgeNomCourt() {
		return ageNomCourt;
	}

	public String strAgeNomCourt() {
		return ageNomCourt == null ? "" : ageNomCourt;
	}

	public String jsonAgeNomCourt() {
		return ageNomCourt == null ? "" : ageNomCourt;
	}

	public String nomAffichageAgeNomCourt() {
		return null;
	}

	public String htmTooltipAgeNomCourt() {
		return null;
	}

	public String htmAgeNomCourt() {
		return ageNomCourt == null ? "" : StringEscapeUtils.escapeHtml4(strAgeNomCourt());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.age.AgeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageNomComplet">Trouver l'entité ageNomComplet dans Solr</a>
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
	protected AgeScolaire ageNomCompletInit() {
		if(!ageNomCompletCouverture.dejaInitialise) {
			_ageNomComplet(ageNomCompletCouverture);
			if(ageNomComplet == null)
				setAgeNomComplet(ageNomCompletCouverture.o);
		}
		ageNomCompletCouverture.dejaInitialise(true);
		return (AgeScolaire)this;
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
		return "nom";
	}

	public String htmTooltipAgeNomComplet() {
		return null;
	}

	public String htmAgeNomComplet() {
		return ageNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strAgeNomComplet());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseAgeScolaire = false;

	public AgeScolaire initLoinAgeScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseAgeScolaire) {
			dejaInitialiseAgeScolaire = true;
			initLoinAgeScolaire();
		}
		return (AgeScolaire)this;
	}

	public void initLoinAgeScolaire() {
		initAgeScolaire();
		super.initLoinCluster(requeteSite_);
	}

	public void initAgeScolaire() {
		ageCleInit();
		inscriptionClesInit();
		blocClesInit();
		scolaireTriInit();
		ecoleTriInit();
		anneeTriInit();
		saisonTriInit();
		sessionTriInit();
		sessionCleInit();
		anneeRechercheInit();
		annee_Init();
		ecoleCleInit();
		anneeCleInit();
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
		anneeNomCourtInit();
		anneeNomCompletInit();
		sessionDateDebutInit();
		sessionDateFinInit();
		ageDebutInit();
		ageFinInit();
		ageNomCourtInit();
		ageNomCompletInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinAgeScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteAgeScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(anneeRecherche != null)
			anneeRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteAgeScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAgeScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirAgeScolaire(String var) {
		AgeScolaire oAgeScolaire = (AgeScolaire)this;
		switch(var) {
			case "ageCle":
				return oAgeScolaire.ageCle;
			case "inscriptionCles":
				return oAgeScolaire.inscriptionCles;
			case "blocCles":
				return oAgeScolaire.blocCles;
			case "scolaireTri":
				return oAgeScolaire.scolaireTri;
			case "ecoleTri":
				return oAgeScolaire.ecoleTri;
			case "anneeTri":
				return oAgeScolaire.anneeTri;
			case "saisonTri":
				return oAgeScolaire.saisonTri;
			case "sessionTri":
				return oAgeScolaire.sessionTri;
			case "sessionCle":
				return oAgeScolaire.sessionCle;
			case "anneeRecherche":
				return oAgeScolaire.anneeRecherche;
			case "annee_":
				return oAgeScolaire.annee_;
			case "ecoleCle":
				return oAgeScolaire.ecoleCle;
			case "anneeCle":
				return oAgeScolaire.anneeCle;
			case "ecoleNom":
				return oAgeScolaire.ecoleNom;
			case "ecoleNomComplet":
				return oAgeScolaire.ecoleNomComplet;
			case "ecoleEmplacement":
				return oAgeScolaire.ecoleEmplacement;
			case "ecoleAddresse":
				return oAgeScolaire.ecoleAddresse;
			case "ecoleNumeroTelephone":
				return oAgeScolaire.ecoleNumeroTelephone;
			case "ecoleForm":
				return oAgeScolaire.ecoleForm;
			case "ecoleNumero":
				return oAgeScolaire.ecoleNumero;
			case "ecoleAdministrateurNom":
				return oAgeScolaire.ecoleAdministrateurNom;
			case "anneeDebut":
				return oAgeScolaire.anneeDebut;
			case "anneeFin":
				return oAgeScolaire.anneeFin;
			case "saisonDateDebut":
				return oAgeScolaire.saisonDateDebut;
			case "anneeFraisInscription":
				return oAgeScolaire.anneeFraisInscription;
			case "anneeNomCourt":
				return oAgeScolaire.anneeNomCourt;
			case "anneeNomComplet":
				return oAgeScolaire.anneeNomComplet;
			case "sessionDateDebut":
				return oAgeScolaire.sessionDateDebut;
			case "sessionDateFin":
				return oAgeScolaire.sessionDateFin;
			case "ageDebut":
				return oAgeScolaire.ageDebut;
			case "ageFin":
				return oAgeScolaire.ageFin;
			case "ageNomCourt":
				return oAgeScolaire.ageNomCourt;
			case "ageNomComplet":
				return oAgeScolaire.ageNomComplet;
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
				o = attribuerAgeScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAgeScolaire(String var, Object val) {
		AgeScolaire oAgeScolaire = (AgeScolaire)this;
		switch(var) {
			case "blocCles":
				oAgeScolaire.addBlocCles((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "sessionCle":
				if(oAgeScolaire.getSessionCle() == null)
					oAgeScolaire.setSessionCle((Long)val);
				if(!sauvegardes.contains(var))
					sauvegardes.add(var);
				return val;
			case "anneeCle":
				if(oAgeScolaire.getAnneeCle() == null)
					oAgeScolaire.setAnneeCle((Long)val);
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
					o = definirAgeScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAgeScolaire(String var, String val) {
		switch(var) {
			case "ecoleAddresse":
				if(val != null)
					setEcoleAddresse(val);
				sauvegardes.add(var);
				return val;
			case "ageDebut":
				if(val != null)
					setAgeDebut(val);
				sauvegardes.add(var);
				return val;
			case "ageFin":
				if(val != null)
					setAgeFin(val);
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
		peuplerAgeScolaire(solrDocument);
	}
	public void peuplerAgeScolaire(SolrDocument solrDocument) {
		AgeScolaire oAgeScolaire = (AgeScolaire)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("ageCle")) {
				Long ageCle = (Long)solrDocument.get("ageCle_stored_long");
				if(ageCle != null)
					oAgeScolaire.setAgeCle(ageCle);
			}

			if(sauvegardes.contains("inscriptionCles")) {
				List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
				if(inscriptionCles != null)
					oAgeScolaire.inscriptionCles.addAll(inscriptionCles);
			}

			List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
			if(blocCles != null)
				oAgeScolaire.blocCles.addAll(blocCles);

			if(sauvegardes.contains("scolaireTri")) {
				Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
				if(scolaireTri != null)
					oAgeScolaire.setScolaireTri(scolaireTri);
			}

			if(sauvegardes.contains("ecoleTri")) {
				Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
				if(ecoleTri != null)
					oAgeScolaire.setEcoleTri(ecoleTri);
			}

			if(sauvegardes.contains("anneeTri")) {
				Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
				if(anneeTri != null)
					oAgeScolaire.setAnneeTri(anneeTri);
			}

			if(sauvegardes.contains("saisonTri")) {
				Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
				if(saisonTri != null)
					oAgeScolaire.setSaisonTri(saisonTri);
			}

			if(sauvegardes.contains("sessionTri")) {
				Integer sessionTri = (Integer)solrDocument.get("sessionTri_stored_int");
				if(sessionTri != null)
					oAgeScolaire.setSessionTri(sessionTri);
			}

			Long sessionCle = (Long)solrDocument.get("sessionCle_stored_long");
			if(sessionCle != null)
				oAgeScolaire.setSessionCle(sessionCle);

			if(sauvegardes.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oAgeScolaire.setEcoleCle(ecoleCle);
			}

			Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
			if(anneeCle != null)
				oAgeScolaire.setAnneeCle(anneeCle);

			if(sauvegardes.contains("ecoleNom")) {
				String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
				if(ecoleNom != null)
					oAgeScolaire.setEcoleNom(ecoleNom);
			}

			if(sauvegardes.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oAgeScolaire.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardes.contains("ecoleEmplacement")) {
				String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
				if(ecoleEmplacement != null)
					oAgeScolaire.setEcoleEmplacement(ecoleEmplacement);
			}

			if(sauvegardes.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oAgeScolaire.setEcoleAddresse(ecoleAddresse);
			}

			if(sauvegardes.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oAgeScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardes.contains("ecoleForm")) {
				String ecoleForm = (String)solrDocument.get("ecoleForm_stored_string");
				if(ecoleForm != null)
					oAgeScolaire.setEcoleForm(ecoleForm);
			}

			if(sauvegardes.contains("ecoleNumero")) {
				Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
				if(ecoleNumero != null)
					oAgeScolaire.setEcoleNumero(ecoleNumero);
			}

			if(sauvegardes.contains("ecoleAdministrateurNom")) {
				String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
				if(ecoleAdministrateurNom != null)
					oAgeScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);
			}

			if(sauvegardes.contains("anneeDebut")) {
				Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
				if(anneeDebut != null)
					oAgeScolaire.setAnneeDebut(anneeDebut);
			}

			if(sauvegardes.contains("anneeFin")) {
				Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
				if(anneeFin != null)
					oAgeScolaire.setAnneeFin(anneeFin);
			}

			if(sauvegardes.contains("saisonDateDebut")) {
				Date saisonDateDebut = (Date)solrDocument.get("saisonDateDebut_stored_date");
				if(saisonDateDebut != null)
					oAgeScolaire.setSaisonDateDebut(saisonDateDebut);
			}

			if(sauvegardes.contains("anneeFraisInscription")) {
				Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
				if(anneeFraisInscription != null)
					oAgeScolaire.setAnneeFraisInscription(anneeFraisInscription);
			}

			if(sauvegardes.contains("anneeNomCourt")) {
				String anneeNomCourt = (String)solrDocument.get("anneeNomCourt_stored_string");
				if(anneeNomCourt != null)
					oAgeScolaire.setAnneeNomCourt(anneeNomCourt);
			}

			if(sauvegardes.contains("anneeNomComplet")) {
				String anneeNomComplet = (String)solrDocument.get("anneeNomComplet_stored_string");
				if(anneeNomComplet != null)
					oAgeScolaire.setAnneeNomComplet(anneeNomComplet);
			}

			if(sauvegardes.contains("sessionDateDebut")) {
				Date sessionDateDebut = (Date)solrDocument.get("sessionDateDebut_stored_date");
				if(sessionDateDebut != null)
					oAgeScolaire.setSessionDateDebut(sessionDateDebut);
			}

			if(sauvegardes.contains("sessionDateFin")) {
				Date sessionDateFin = (Date)solrDocument.get("sessionDateFin_stored_date");
				if(sessionDateFin != null)
					oAgeScolaire.setSessionDateFin(sessionDateFin);
			}

			if(sauvegardes.contains("ageDebut")) {
				Integer ageDebut = (Integer)solrDocument.get("ageDebut_stored_int");
				if(ageDebut != null)
					oAgeScolaire.setAgeDebut(ageDebut);
			}

			if(sauvegardes.contains("ageFin")) {
				Integer ageFin = (Integer)solrDocument.get("ageFin_stored_int");
				if(ageFin != null)
					oAgeScolaire.setAgeFin(ageFin);
			}

			if(sauvegardes.contains("ageNomCourt")) {
				String ageNomCourt = (String)solrDocument.get("ageNomCourt_stored_string");
				if(ageNomCourt != null)
					oAgeScolaire.setAgeNomCourt(ageNomCourt);
			}

			if(sauvegardes.contains("ageNomComplet")) {
				String ageNomComplet = (String)solrDocument.get("ageNomComplet_stored_string");
				if(ageNomComplet != null)
					oAgeScolaire.setAgeNomComplet(ageNomComplet);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.age.AgeScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			AgeScolaire o = new AgeScolaire();
			o.requeteSiteAgeScolaire(requeteSite);
			o.initLoinAgeScolaire(requeteSite);
			o.indexerAgeScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerAgeScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerAgeScolaire(document);
	}

	public void indexerAgeScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerAgeScolaire(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerAgeScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerAgeScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerAgeScolaire(SolrInputDocument document) {
		if(ageCle != null) {
			document.addField("ageCle_indexed_long", ageCle);
			document.addField("ageCle_stored_long", ageCle);
		}
		if(inscriptionCles != null) {
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_indexed_longs", o);
			}
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_stored_longs", o);
			}
		}
		if(blocCles != null) {
			for(java.lang.Long o : blocCles) {
				document.addField("blocCles_indexed_longs", o);
			}
			for(java.lang.Long o : blocCles) {
				document.addField("blocCles_stored_longs", o);
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
		if(sessionCle != null) {
			document.addField("sessionCle_indexed_long", sessionCle);
			document.addField("sessionCle_stored_long", sessionCle);
		}
		if(ecoleCle != null) {
			document.addField("ecoleCle_indexed_long", ecoleCle);
			document.addField("ecoleCle_stored_long", ecoleCle);
		}
		if(anneeCle != null) {
			document.addField("anneeCle_indexed_long", anneeCle);
			document.addField("anneeCle_stored_long", anneeCle);
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
		if(anneeNomCourt != null) {
			document.addField("anneeNomCourt_indexed_string", anneeNomCourt);
			document.addField("anneeNomCourt_stored_string", anneeNomCourt);
		}
		if(anneeNomComplet != null) {
			document.addField("anneeNomComplet_indexed_string", anneeNomComplet);
			document.addField("anneeNomComplet_stored_string", anneeNomComplet);
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
		if(ageNomCourt != null) {
			document.addField("ageNomCourt_indexed_string", ageNomCourt);
			document.addField("ageNomCourt_stored_string", ageNomCourt);
		}
		if(ageNomComplet != null) {
			document.addField("ageNomComplet_indexed_string", ageNomComplet);
			document.addField("ageNomComplet_stored_string", ageNomComplet);
		}
		super.indexerCluster(document);

	}

	public void desindexerAgeScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinAgeScolaire(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeAgeScolaire(String entiteVar) {
		switch(entiteVar) {
			case "ageCle":
				return "ageCle_indexed_long";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "blocCles":
				return "blocCles_indexed_longs";
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
			case "sessionCle":
				return "sessionCle_indexed_long";
			case "ecoleCle":
				return "ecoleCle_indexed_long";
			case "anneeCle":
				return "anneeCle_indexed_long";
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
			case "anneeNomCourt":
				return "anneeNomCourt_indexed_string";
			case "anneeNomComplet":
				return "anneeNomComplet_indexed_string";
			case "sessionDateDebut":
				return "sessionDateDebut_indexed_date";
			case "sessionDateFin":
				return "sessionDateFin_indexed_date";
			case "ageDebut":
				return "ageDebut_indexed_int";
			case "ageFin":
				return "ageFin_indexed_int";
			case "ageNomCourt":
				return "ageNomCourt_indexed_string";
			case "ageNomComplet":
				return "ageNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheAgeScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereAgeScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerAgeScolaire(solrDocument);
	}
	public void stockerAgeScolaire(SolrDocument solrDocument) {
		AgeScolaire oAgeScolaire = (AgeScolaire)this;

		Long ageCle = (Long)solrDocument.get("ageCle_stored_long");
		if(ageCle != null)
			oAgeScolaire.setAgeCle(ageCle);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oAgeScolaire.inscriptionCles.addAll(inscriptionCles);

		List<Long> blocCles = (List<Long>)solrDocument.get("blocCles_stored_longs");
		if(blocCles != null)
			oAgeScolaire.blocCles.addAll(blocCles);

		Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
		if(scolaireTri != null)
			oAgeScolaire.setScolaireTri(scolaireTri);

		Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
		if(ecoleTri != null)
			oAgeScolaire.setEcoleTri(ecoleTri);

		Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
		if(anneeTri != null)
			oAgeScolaire.setAnneeTri(anneeTri);

		Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
		if(saisonTri != null)
			oAgeScolaire.setSaisonTri(saisonTri);

		Integer sessionTri = (Integer)solrDocument.get("sessionTri_stored_int");
		if(sessionTri != null)
			oAgeScolaire.setSessionTri(sessionTri);

		Long sessionCle = (Long)solrDocument.get("sessionCle_stored_long");
		if(sessionCle != null)
			oAgeScolaire.setSessionCle(sessionCle);

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oAgeScolaire.setEcoleCle(ecoleCle);

		Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
		if(anneeCle != null)
			oAgeScolaire.setAnneeCle(anneeCle);

		String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
		if(ecoleNom != null)
			oAgeScolaire.setEcoleNom(ecoleNom);

		String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
		if(ecoleNomComplet != null)
			oAgeScolaire.setEcoleNomComplet(ecoleNomComplet);

		String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
		if(ecoleEmplacement != null)
			oAgeScolaire.setEcoleEmplacement(ecoleEmplacement);

		String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
		if(ecoleAddresse != null)
			oAgeScolaire.setEcoleAddresse(ecoleAddresse);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oAgeScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		String ecoleForm = (String)solrDocument.get("ecoleForm_stored_string");
		if(ecoleForm != null)
			oAgeScolaire.setEcoleForm(ecoleForm);

		Integer ecoleNumero = (Integer)solrDocument.get("ecoleNumero_stored_int");
		if(ecoleNumero != null)
			oAgeScolaire.setEcoleNumero(ecoleNumero);

		String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
		if(ecoleAdministrateurNom != null)
			oAgeScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);

		Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
		if(anneeDebut != null)
			oAgeScolaire.setAnneeDebut(anneeDebut);

		Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
		if(anneeFin != null)
			oAgeScolaire.setAnneeFin(anneeFin);

		Date saisonDateDebut = (Date)solrDocument.get("saisonDateDebut_stored_date");
		if(saisonDateDebut != null)
			oAgeScolaire.setSaisonDateDebut(saisonDateDebut);

		Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
		if(anneeFraisInscription != null)
			oAgeScolaire.setAnneeFraisInscription(anneeFraisInscription);

		String anneeNomCourt = (String)solrDocument.get("anneeNomCourt_stored_string");
		if(anneeNomCourt != null)
			oAgeScolaire.setAnneeNomCourt(anneeNomCourt);

		String anneeNomComplet = (String)solrDocument.get("anneeNomComplet_stored_string");
		if(anneeNomComplet != null)
			oAgeScolaire.setAnneeNomComplet(anneeNomComplet);

		Date sessionDateDebut = (Date)solrDocument.get("sessionDateDebut_stored_date");
		if(sessionDateDebut != null)
			oAgeScolaire.setSessionDateDebut(sessionDateDebut);

		Date sessionDateFin = (Date)solrDocument.get("sessionDateFin_stored_date");
		if(sessionDateFin != null)
			oAgeScolaire.setSessionDateFin(sessionDateFin);

		Integer ageDebut = (Integer)solrDocument.get("ageDebut_stored_int");
		if(ageDebut != null)
			oAgeScolaire.setAgeDebut(ageDebut);

		Integer ageFin = (Integer)solrDocument.get("ageFin_stored_int");
		if(ageFin != null)
			oAgeScolaire.setAgeFin(ageFin);

		String ageNomCourt = (String)solrDocument.get("ageNomCourt_stored_string");
		if(ageNomCourt != null)
			oAgeScolaire.setAgeNomCourt(ageNomCourt);

		String ageNomComplet = (String)solrDocument.get("ageNomComplet_stored_string");
		if(ageNomComplet != null)
			oAgeScolaire.setAgeNomComplet(ageNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiAgeScolaire() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof AgeScolaire) {
			AgeScolaire original = (AgeScolaire)o;
			if(!Objects.equals(ageCle, original.getAgeCle()))
				requeteApi.addVars("ageCle");
			if(!Objects.equals(inscriptionCles, original.getInscriptionCles()))
				requeteApi.addVars("inscriptionCles");
			if(!Objects.equals(blocCles, original.getBlocCles()))
				requeteApi.addVars("blocCles");
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
			if(!Objects.equals(sessionCle, original.getSessionCle()))
				requeteApi.addVars("sessionCle");
			if(!Objects.equals(ecoleCle, original.getEcoleCle()))
				requeteApi.addVars("ecoleCle");
			if(!Objects.equals(anneeCle, original.getAnneeCle()))
				requeteApi.addVars("anneeCle");
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
			if(!Objects.equals(anneeNomCourt, original.getAnneeNomCourt()))
				requeteApi.addVars("anneeNomCourt");
			if(!Objects.equals(anneeNomComplet, original.getAnneeNomComplet()))
				requeteApi.addVars("anneeNomComplet");
			if(!Objects.equals(sessionDateDebut, original.getSessionDateDebut()))
				requeteApi.addVars("sessionDateDebut");
			if(!Objects.equals(sessionDateFin, original.getSessionDateFin()))
				requeteApi.addVars("sessionDateFin");
			if(!Objects.equals(ageDebut, original.getAgeDebut()))
				requeteApi.addVars("ageDebut");
			if(!Objects.equals(ageFin, original.getAgeFin()))
				requeteApi.addVars("ageFin");
			if(!Objects.equals(ageNomCourt, original.getAgeNomCourt()))
				requeteApi.addVars("ageNomCourt");
			if(!Objects.equals(ageNomComplet, original.getAgeNomComplet()))
				requeteApi.addVars("ageNomComplet");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), ageCle, inscriptionCles, blocCles, scolaireTri, ecoleTri, anneeTri, saisonTri, sessionTri, sessionCle, ecoleCle, anneeCle, ecoleNom, ecoleNomComplet, ecoleEmplacement, ecoleAddresse, ecoleNumeroTelephone, ecoleForm, ecoleNumero, ecoleAdministrateurNom, anneeDebut, anneeFin, saisonDateDebut, anneeFraisInscription, anneeNomCourt, anneeNomComplet, sessionDateDebut, sessionDateFin, ageDebut, ageFin, ageNomCourt, ageNomComplet);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof AgeScolaire))
			return false;
		AgeScolaire that = (AgeScolaire)o;
		return super.equals(o)
				&& Objects.equals( ageCle, that.ageCle )
				&& Objects.equals( inscriptionCles, that.inscriptionCles )
				&& Objects.equals( blocCles, that.blocCles )
				&& Objects.equals( scolaireTri, that.scolaireTri )
				&& Objects.equals( ecoleTri, that.ecoleTri )
				&& Objects.equals( anneeTri, that.anneeTri )
				&& Objects.equals( saisonTri, that.saisonTri )
				&& Objects.equals( sessionTri, that.sessionTri )
				&& Objects.equals( sessionCle, that.sessionCle )
				&& Objects.equals( ecoleCle, that.ecoleCle )
				&& Objects.equals( anneeCle, that.anneeCle )
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
				&& Objects.equals( anneeNomCourt, that.anneeNomCourt )
				&& Objects.equals( anneeNomComplet, that.anneeNomComplet )
				&& Objects.equals( sessionDateDebut, that.sessionDateDebut )
				&& Objects.equals( sessionDateFin, that.sessionDateFin )
				&& Objects.equals( ageDebut, that.ageDebut )
				&& Objects.equals( ageFin, that.ageFin )
				&& Objects.equals( ageNomCourt, that.ageNomCourt )
				&& Objects.equals( ageNomComplet, that.ageNomComplet );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("AgeScolaire { ");
		sb.append( "ageCle: " ).append(ageCle);
		sb.append( ", inscriptionCles: " ).append(inscriptionCles);
		sb.append( ", blocCles: " ).append(blocCles);
		sb.append( ", scolaireTri: " ).append(scolaireTri);
		sb.append( ", ecoleTri: " ).append(ecoleTri);
		sb.append( ", anneeTri: " ).append(anneeTri);
		sb.append( ", saisonTri: " ).append(saisonTri);
		sb.append( ", sessionTri: " ).append(sessionTri);
		sb.append( ", sessionCle: " ).append(sessionCle);
		sb.append( ", ecoleCle: " ).append(ecoleCle);
		sb.append( ", anneeCle: " ).append(anneeCle);
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
		sb.append( ", anneeNomCourt: \"" ).append(anneeNomCourt).append( "\"" );
		sb.append( ", anneeNomComplet: \"" ).append(anneeNomComplet).append( "\"" );
		sb.append( ", sessionDateDebut: " ).append(sessionDateDebut);
		sb.append( ", sessionDateFin: " ).append(sessionDateFin);
		sb.append( ", ageDebut: " ).append(ageDebut);
		sb.append( ", ageFin: " ).append(ageFin);
		sb.append( ", ageNomCourt: \"" ).append(ageNomCourt).append( "\"" );
		sb.append( ", ageNomComplet: \"" ).append(ageNomComplet).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
