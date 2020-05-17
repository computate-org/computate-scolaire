package org.computate.scolaire.frFR.saison;

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
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe seasonCompleteName dans Solr</a>
 * <br/>
 **/
public abstract class SaisonScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SaisonScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCle">Trouver l'entité saisonCle dans Solr</a>
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
	public SaisonScolaire setSaisonCle(String o) {
		if(NumberUtils.isParsable(o))
			this.saisonCle = Long.parseLong(o);
		this.saisonCleCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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

	/**	L'entité « inscriptionCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> inscriptionCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> inscriptionClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("inscriptionCles").o(inscriptionCles);

	/**	<br/>L'entité « inscriptionCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
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
	public SaisonScolaire setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
		return (SaisonScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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
	public SaisonScolaire setAnneeCle(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeCle = Long.parseLong(o);
		this.anneeCleCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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
		SaisonScolaire s = (SaisonScolaire)this;
		{
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "année")
					.a("title", "Les sessions scolaires de la saison scolaire. ")
					.a("class", "valeur suggereAnneeCle w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setAnneeCle")
					.a("id", classeApiMethodeMethode, "_anneeCle")
					.a("autocomplete", "off")
					.a("oninput", "suggereSaisonScolaireAnneeCle($(this).val() ? rechercherAnneeScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'saisonCles:" + pk + "'}", "], $('#listSaisonScolaireAnneeCle_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		}
	}

	public void htmAnneeCle(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SaisonScolaireAnneeCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=saisonCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-orange w3-hover-orange ").f();
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
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
											.a("onclick", "postAnneeScolaireVals({ saisonCles: \"", pk, "\" }, function() { patchSaisonScolaireVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "anneeCle')); });")
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

	/**	L'entité « sessionCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> sessionCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> sessionClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("sessionCles").o(sessionCles);

	/**	<br/>L'entité « sessionCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCles">Trouver l'entité sessionCles dans Solr</a>
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
	public SaisonScolaire setSessionCles(JsonArray objets) {
		sessionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionCles(o);
		}
		return (SaisonScolaire)this;
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

	public List<Long> solrSessionCles() {
		return sessionCles;
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
		{
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "sessions")
					.a("title", "Les sessions scolaires de la saison scolaire. ")
					.a("class", "valeur suggereSessionCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setSessionCles")
					.a("id", classeApiMethodeMethode, "_sessionCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereSaisonScolaireSessionCles($(this).val() ? rechercherSessionScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'saisonCle:" + pk + "'}", "], $('#listSaisonScolaireSessionCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		}
	}

	public void htmSessionCles(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SaisonScolaireSessionCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=saisonCle:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
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
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
											.a("onclick", "postSessionScolaireVals({ saisonCle: \"", pk, "\" }, function() { patchSaisonScolaireVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "sessionCles')); });")
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

	/**	L'entité « scolaireTri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer scolaireTri;
	@JsonIgnore
	public Couverture<Integer> scolaireTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("scolaireTri").o(scolaireTri);

	/**	<br/>L'entité « scolaireTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:scolaireTri">Trouver l'entité scolaireTri dans Solr</a>
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
	public SaisonScolaire setScolaireTri(String o) {
		if(NumberUtils.isParsable(o))
			this.scolaireTri = Integer.parseInt(o);
		this.scolaireTriCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected Integer ecoleTri;
	@JsonIgnore
	public Couverture<Integer> ecoleTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("ecoleTri").o(ecoleTri);

	/**	<br/>L'entité « ecoleTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleTri">Trouver l'entité ecoleTri dans Solr</a>
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
	public SaisonScolaire setEcoleTri(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleTri = Integer.parseInt(o);
		this.ecoleTriCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected Integer anneeTri;
	@JsonIgnore
	public Couverture<Integer> anneeTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeTri").o(anneeTri);

	/**	<br/>L'entité « anneeTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeTri">Trouver l'entité anneeTri dans Solr</a>
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
	public SaisonScolaire setAnneeTri(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeTri = Integer.parseInt(o);
		this.anneeTriCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected Integer saisonTri;
	@JsonIgnore
	public Couverture<Integer> saisonTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("saisonTri").o(saisonTri);

	/**	<br/>L'entité « saisonTri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonTri">Trouver l'entité saisonTri dans Solr</a>
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
	public SaisonScolaire setSaisonTri(String o) {
		if(NumberUtils.isParsable(o))
			this.saisonTri = Integer.parseInt(o);
		this.saisonTriCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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

	////////////////////
	// anneeRecherche //
	////////////////////

	/**	L'entité « anneeRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<AnneeScolaire>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<AnneeScolaire> anneeRecherche = new ListeRecherche<AnneeScolaire>();
	@JsonIgnore
	public Couverture<ListeRecherche<AnneeScolaire>> anneeRechercheCouverture = new Couverture<ListeRecherche<AnneeScolaire>>().p(this).c(ListeRecherche.class).var("anneeRecherche").o(anneeRecherche);

	/**	<br/>L'entité « anneeRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<AnneeScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeRecherche">Trouver l'entité anneeRecherche dans Solr</a>
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

	/**	L'entité « annee_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected AnneeScolaire annee_;
	@JsonIgnore
	public Couverture<AnneeScolaire> annee_Couverture = new Couverture<AnneeScolaire>().p(this).c(AnneeScolaire.class).var("annee_").o(annee_);

	/**	<br/>L'entité « annee_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:annee_">Trouver l'entité annee_ dans Solr</a>
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
	public SaisonScolaire setEcoleCle(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleCle = Long.parseLong(o);
		this.ecoleCleCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
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
	protected SaisonScolaire ecoleNomInit() {
		if(!ecoleNomCouverture.dejaInitialise) {
			_ecoleNom(ecoleNomCouverture);
			if(ecoleNom == null)
				setEcoleNom(ecoleNomCouverture.o);
		}
		ecoleNomCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
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
	protected SaisonScolaire ecoleNomCompletInit() {
		if(!ecoleNomCompletCouverture.dejaInitialise) {
			_ecoleNomComplet(ecoleNomCompletCouverture);
			if(ecoleNomComplet == null)
				setEcoleNomComplet(ecoleNomCompletCouverture.o);
		}
		ecoleNomCompletCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleEmplacement">Trouver l'entité ecoleEmplacement dans Solr</a>
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
	protected SaisonScolaire ecoleEmplacementInit() {
		if(!ecoleEmplacementCouverture.dejaInitialise) {
			_ecoleEmplacement(ecoleEmplacementCouverture);
			if(ecoleEmplacement == null)
				setEcoleEmplacement(ecoleEmplacementCouverture.o);
		}
		ecoleEmplacementCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected String ecoleAddresse;
	@JsonIgnore
	public Couverture<String> ecoleAddresseCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleAddresse").o(ecoleAddresse);

	/**	<br/>L'entité « ecoleAddresse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
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
	protected SaisonScolaire ecoleAddresseInit() {
		if(!ecoleAddresseCouverture.dejaInitialise) {
			_ecoleAddresse(ecoleAddresseCouverture);
			if(ecoleAddresse == null)
				setEcoleAddresse(ecoleAddresseCouverture.o);
		}
		ecoleAddresseCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
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

	//////////////////////////
	// ecoleNumeroTelephone //
	//////////////////////////

	/**	L'entité « ecoleNumeroTelephone »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String ecoleNumeroTelephone;
	@JsonIgnore
	public Couverture<String> ecoleNumeroTelephoneCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleNumeroTelephone").o(ecoleNumeroTelephone);

	/**	<br/>L'entité « ecoleNumeroTelephone »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
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
	protected SaisonScolaire ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneCouverture.dejaInitialise) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneCouverture);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneCouverture.o);
		}
		ecoleNumeroTelephoneCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected String ecoleAdministrateurNom;
	@JsonIgnore
	public Couverture<String> ecoleAdministrateurNomCouverture = new Couverture<String>().p(this).c(String.class).var("ecoleAdministrateurNom").o(ecoleAdministrateurNom);

	/**	<br/>L'entité « ecoleAdministrateurNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAdministrateurNom">Trouver l'entité ecoleAdministrateurNom dans Solr</a>
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
	protected SaisonScolaire ecoleAdministrateurNomInit() {
		if(!ecoleAdministrateurNomCouverture.dejaInitialise) {
			_ecoleAdministrateurNom(ecoleAdministrateurNomCouverture);
			if(ecoleAdministrateurNom == null)
				setEcoleAdministrateurNom(ecoleAdministrateurNomCouverture.o);
		}
		ecoleAdministrateurNomCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
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
	@JsonInclude(Include.NON_NULL)
	protected Integer anneeDebut;
	@JsonIgnore
	public Couverture<Integer> anneeDebutCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("anneeDebut").o(anneeDebut);

	/**	<br/>L'entité « anneeDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
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
	public SaisonScolaire setAnneeDebut(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeDebut = Integer.parseInt(o);
		this.anneeDebutCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
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
	public SaisonScolaire setAnneeFin(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeFin = Integer.parseInt(o);
		this.anneeFinCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFraisInscription">Trouver l'entité anneeFraisInscription dans Solr</a>
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
	public SaisonScolaire setAnneeFraisInscription(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
	}
	public SaisonScolaire setAnneeFraisInscription(Double o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
	}
	public SaisonScolaire setAnneeFraisInscription(Integer o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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

	/////////////////////
	// saisonJourDebut //
	/////////////////////

	/**	L'entité « saisonJourDebut »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate saisonJourDebut;
	@JsonIgnore
	public Couverture<LocalDate> saisonJourDebutCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("saisonJourDebut").o(saisonJourDebut);

	/**	<br/>L'entité « saisonJourDebut »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonJourDebut">Trouver l'entité saisonJourDebut dans Solr</a>
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
	public SaisonScolaire setSaisonJourDebut(Instant o) {
		this.saisonJourDebut = LocalDate.from(o);
		this.saisonJourDebutCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SaisonScolaire setSaisonJourDebut(String o) {
		this.saisonJourDebut = LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.saisonJourDebutCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
	}
	public SaisonScolaire setSaisonJourDebut(Date o) {
		this.saisonJourDebut = o.toInstant().atZone(ZoneId.of(requeteSite_.getConfigSite_().getSiteZone())).toLocalDate();
		this.saisonJourDebutCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
	}
	protected SaisonScolaire saisonJourDebutInit() {
		if(!saisonJourDebutCouverture.dejaInitialise) {
			_saisonJourDebut(saisonJourDebutCouverture);
			if(saisonJourDebut == null)
				setSaisonJourDebut(saisonJourDebutCouverture.o);
		}
		saisonJourDebutCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
	}

	public Date solrSaisonJourDebut() {
		return saisonJourDebut == null ? null : Date.from(saisonJourDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strSaisonJourDebut() {
		return saisonJourDebut == null ? "" : saisonJourDebut.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.forLanguageTag("fr-FR")));
	}

	public String jsonSaisonJourDebut() {
		return saisonJourDebut == null ? "" : saisonJourDebut.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSaisonJourDebut() {
		return "début de la saison";
	}

	public String htmTooltipSaisonJourDebut() {
		return null;
	}

	public String htmSaisonJourDebut() {
		return saisonJourDebut == null ? "" : StringEscapeUtils.escapeHtml4(strSaisonJourDebut());
	}

	public void inputSaisonJourDebut(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setSaisonJourDebut inputSaisonScolaire", pk, "SaisonJourDebut w3-input w3-border ")
				.a("placeholder", "DD-MM-YYYY")
				.a("data-timeformat", "DD-MM-YYYY")
				.a("id", classeApiMethodeMethode, "_saisonJourDebut")
				.a("onclick", "enleverLueur($(this)); ")
				.a("title", "Les sessions scolaires de la saison scolaire.  (DD-MM-YYYY)")
				.a("value", saisonJourDebut == null ? "" : DateTimeFormatter.ISO_LOCAL_DATE.format(saisonJourDebut))
				.a("onchange", "var t = moment(this.value, 'DD-MM-YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patchSaisonScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setSaisonJourDebut', s, function() { ajouterLueur($('#", classeApiMethodeMethode, "_saisonJourDebut')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_saisonJourDebut')); }); } ")
				.fg();
		}
	}

	public void htmSaisonJourDebut(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "SaisonScolaireSaisonJourDebut").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_saisonJourDebut").a("class", "").f().sx("début de la saison").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputSaisonJourDebut(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_saisonJourDebut')); $('#", classeApiMethodeMethode, "_saisonJourDebut').val(null); patchSaisonScolaireVal([{ name: 'fq', value: 'pk:' + $('#SaisonScolaireForm :input[name=pk]').val() }], 'setSaisonJourDebut', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_saisonJourDebut')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_saisonJourDebut')); }); ")
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

	/**	L'entité « saisonEte »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean saisonEte;
	@JsonIgnore
	public Couverture<Boolean> saisonEteCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonEte").o(saisonEte);

	/**	<br/>L'entité « saisonEte »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonEte">Trouver l'entité saisonEte dans Solr</a>
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
	public SaisonScolaire setSaisonEte(String o) {
		this.saisonEte = Boolean.parseBoolean(o);
		this.saisonEteCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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

	public void inputSaisonEte(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{
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
				a("class", "setSaisonEte inputSaisonScolaire", pk, "SaisonEte w3-input w3-border ");
				a("name", "setSaisonEte");
			} else {
				a("class", "valeurSaisonEte inputSaisonScolaire", pk, "SaisonEte w3-input w3-border ");
				a("name", "saisonEte");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patchSaisonScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setSaisonEte', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_saisonEte')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_saisonEte')); }); ");
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

	/**	L'entité « saisonHiver »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean saisonHiver;
	@JsonIgnore
	public Couverture<Boolean> saisonHiverCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonHiver").o(saisonHiver);

	/**	<br/>L'entité « saisonHiver »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonHiver">Trouver l'entité saisonHiver dans Solr</a>
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
	public SaisonScolaire setSaisonHiver(String o) {
		this.saisonHiver = Boolean.parseBoolean(o);
		this.saisonHiverCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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

	public void inputSaisonHiver(String classeApiMethodeMethode) {
		SaisonScolaire s = (SaisonScolaire)this;
		{
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
				a("class", "setSaisonHiver inputSaisonScolaire", pk, "SaisonHiver w3-input w3-border ");
				a("name", "setSaisonHiver");
			} else {
				a("class", "valeurSaisonHiver inputSaisonScolaire", pk, "SaisonHiver w3-input w3-border ");
				a("name", "saisonHiver");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patchSaisonScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setSaisonHiver', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_saisonHiver')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_saisonHiver')); }); ");
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

	/**	L'entité « saisonFuture »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean saisonFuture;
	@JsonIgnore
	public Couverture<Boolean> saisonFutureCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("saisonFuture").o(saisonFuture);

	/**	<br/>L'entité « saisonFuture »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonFuture">Trouver l'entité saisonFuture dans Solr</a>
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
	public SaisonScolaire setSaisonFuture(String o) {
		this.saisonFuture = Boolean.parseBoolean(o);
		this.saisonFutureCouverture.dejaInitialise = true;
		return (SaisonScolaire)this;
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

	public Boolean solrSaisonFuture() {
		return saisonFuture;
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
		{
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
				a("class", "setSaisonFuture inputSaisonScolaire", pk, "SaisonFuture w3-input w3-border ");
				a("name", "setSaisonFuture");
			} else {
				a("class", "valeurSaisonFuture inputSaisonScolaire", pk, "SaisonFuture w3-input w3-border ");
				a("name", "saisonFuture");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patchSaisonScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setSaisonFuture', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_saisonFuture')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_saisonFuture')); }); ");
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

	/**	L'entité « saisonNomCourt »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String saisonNomCourt;
	@JsonIgnore
	public Couverture<String> saisonNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("saisonNomCourt").o(saisonNomCourt);

	/**	<br/>L'entité « saisonNomCourt »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonNomCourt">Trouver l'entité saisonNomCourt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _saisonNomCourt(Couverture<String> c);

	public String getSaisonNomCourt() {
		return saisonNomCourt;
	}

	public void setSaisonNomCourt(String saisonNomCourt) {
		this.saisonNomCourt = saisonNomCourt;
		this.saisonNomCourtCouverture.dejaInitialise = true;
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

	public String solrSaisonNomCourt() {
		return saisonNomCourt;
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

	/**	L'entité « saisonNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String saisonNomComplet;
	@JsonIgnore
	public Couverture<String> saisonNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("saisonNomComplet").o(saisonNomComplet);

	/**	<br/>L'entité « saisonNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.saison.SaisonScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonNomComplet">Trouver l'entité saisonNomComplet dans Solr</a>
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
	protected SaisonScolaire saisonNomCompletInit() {
		if(!saisonNomCompletCouverture.dejaInitialise) {
			_saisonNomComplet(saisonNomCompletCouverture);
			if(saisonNomComplet == null)
				setSaisonNomComplet(saisonNomCompletCouverture.o);
		}
		saisonNomCompletCouverture.dejaInitialise(true);
		return (SaisonScolaire)this;
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
		ecoleAdministrateurNomInit();
		anneeDebutInit();
		anneeFinInit();
		anneeFraisInscriptionInit();
		saisonJourDebutInit();
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
			case "ecoleAdministrateurNom":
				return oSaisonScolaire.ecoleAdministrateurNom;
			case "anneeDebut":
				return oSaisonScolaire.anneeDebut;
			case "anneeFin":
				return oSaisonScolaire.anneeFin;
			case "anneeFraisInscription":
				return oSaisonScolaire.anneeFraisInscription;
			case "saisonJourDebut":
				return oSaisonScolaire.saisonJourDebut;
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
				oSaisonScolaire.setAnneeCle((Long)val);
				if(!sauvegardesSaisonScolaire.contains(var))
					sauvegardesSaisonScolaire.add(var);
				return val;
			case "sessionCles":
				oSaisonScolaire.addSessionCles((Long)val);
				if(!sauvegardesSaisonScolaire.contains(var))
					sauvegardesSaisonScolaire.add(var);
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
			case "saisonJourDebut":
				if(val != null)
					setSaisonJourDebut(val);
				sauvegardesSaisonScolaire.add(var);
				return val;
			case "saisonEte":
				if(val != null)
					setSaisonEte(val);
				sauvegardesSaisonScolaire.add(var);
				return val;
			case "saisonHiver":
				if(val != null)
					setSaisonHiver(val);
				sauvegardesSaisonScolaire.add(var);
				return val;
			case "saisonFuture":
				if(val != null)
					setSaisonFuture(val);
				sauvegardesSaisonScolaire.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesSaisonScolaire = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerSaisonScolaire(solrDocument);
	}
	public void peuplerSaisonScolaire(SolrDocument solrDocument) {
		SaisonScolaire oSaisonScolaire = (SaisonScolaire)this;
		sauvegardesSaisonScolaire = (List<String>)solrDocument.get("sauvegardesSaisonScolaire_stored_strings");
		if(sauvegardesSaisonScolaire != null) {

			if(sauvegardesSaisonScolaire.contains("saisonCle")) {
				Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
				if(saisonCle != null)
					oSaisonScolaire.setSaisonCle(saisonCle);
			}

			if(sauvegardesSaisonScolaire.contains("inscriptionCles")) {
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

			if(sauvegardesSaisonScolaire.contains("scolaireTri")) {
				Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
				if(scolaireTri != null)
					oSaisonScolaire.setScolaireTri(scolaireTri);
			}

			if(sauvegardesSaisonScolaire.contains("ecoleTri")) {
				Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
				if(ecoleTri != null)
					oSaisonScolaire.setEcoleTri(ecoleTri);
			}

			if(sauvegardesSaisonScolaire.contains("anneeTri")) {
				Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
				if(anneeTri != null)
					oSaisonScolaire.setAnneeTri(anneeTri);
			}

			if(sauvegardesSaisonScolaire.contains("saisonTri")) {
				Integer saisonTri = (Integer)solrDocument.get("saisonTri_stored_int");
				if(saisonTri != null)
					oSaisonScolaire.setSaisonTri(saisonTri);
			}

			if(sauvegardesSaisonScolaire.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oSaisonScolaire.setEcoleCle(ecoleCle);
			}

			if(sauvegardesSaisonScolaire.contains("ecoleNom")) {
				String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
				if(ecoleNom != null)
					oSaisonScolaire.setEcoleNom(ecoleNom);
			}

			if(sauvegardesSaisonScolaire.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oSaisonScolaire.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardesSaisonScolaire.contains("ecoleEmplacement")) {
				String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
				if(ecoleEmplacement != null)
					oSaisonScolaire.setEcoleEmplacement(ecoleEmplacement);
			}

			if(sauvegardesSaisonScolaire.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oSaisonScolaire.setEcoleAddresse(ecoleAddresse);
			}

			if(sauvegardesSaisonScolaire.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oSaisonScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardesSaisonScolaire.contains("ecoleAdministrateurNom")) {
				String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
				if(ecoleAdministrateurNom != null)
					oSaisonScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);
			}

			if(sauvegardesSaisonScolaire.contains("anneeDebut")) {
				Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
				if(anneeDebut != null)
					oSaisonScolaire.setAnneeDebut(anneeDebut);
			}

			if(sauvegardesSaisonScolaire.contains("anneeFin")) {
				Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
				if(anneeFin != null)
					oSaisonScolaire.setAnneeFin(anneeFin);
			}

			if(sauvegardesSaisonScolaire.contains("anneeFraisInscription")) {
				Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
				if(anneeFraisInscription != null)
					oSaisonScolaire.setAnneeFraisInscription(anneeFraisInscription);
			}

			if(sauvegardesSaisonScolaire.contains("saisonJourDebut")) {
				Date saisonJourDebut = (Date)solrDocument.get("saisonJourDebut_stored_date");
				if(saisonJourDebut != null)
					oSaisonScolaire.setSaisonJourDebut(saisonJourDebut);
			}

			if(sauvegardesSaisonScolaire.contains("saisonEte")) {
				Boolean saisonEte = (Boolean)solrDocument.get("saisonEte_stored_boolean");
				if(saisonEte != null)
					oSaisonScolaire.setSaisonEte(saisonEte);
			}

			if(sauvegardesSaisonScolaire.contains("saisonHiver")) {
				Boolean saisonHiver = (Boolean)solrDocument.get("saisonHiver_stored_boolean");
				if(saisonHiver != null)
					oSaisonScolaire.setSaisonHiver(saisonHiver);
			}

			if(sauvegardesSaisonScolaire.contains("saisonFuture")) {
				Boolean saisonFuture = (Boolean)solrDocument.get("saisonFuture_stored_boolean");
				if(saisonFuture != null)
					oSaisonScolaire.setSaisonFuture(saisonFuture);
			}

			if(sauvegardesSaisonScolaire.contains("saisonNomCourt")) {
				String saisonNomCourt = (String)solrDocument.get("saisonNomCourt_stored_string");
				if(saisonNomCourt != null)
					oSaisonScolaire.setSaisonNomCourt(saisonNomCourt);
			}

			if(sauvegardesSaisonScolaire.contains("saisonNomComplet")) {
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
		if(sauvegardesSaisonScolaire != null)
			document.addField("sauvegardesSaisonScolaire_stored_strings", sauvegardesSaisonScolaire);

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
			case "ecoleAdministrateurNom":
				return "ecoleAdministrateurNom_indexed_string";
			case "anneeDebut":
				return "anneeDebut_indexed_int";
			case "anneeFin":
				return "anneeFin_indexed_int";
			case "anneeFraisInscription":
				return "anneeFraisInscription_indexed_double";
			case "saisonJourDebut":
				return "saisonJourDebut_indexed_date";
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

		Date saisonJourDebut = (Date)solrDocument.get("saisonJourDebut_stored_date");
		if(saisonJourDebut != null)
			oSaisonScolaire.setSaisonJourDebut(saisonJourDebut);

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
			if(!Objects.equals(anneeCle, original.getAnneeCle()))
				requeteApi.addVars("anneeCle");
			if(!Objects.equals(sessionCles, original.getSessionCles()))
				requeteApi.addVars("sessionCles");
			if(!Objects.equals(saisonJourDebut, original.getSaisonJourDebut()))
				requeteApi.addVars("saisonJourDebut");
			if(!Objects.equals(saisonEte, original.getSaisonEte()))
				requeteApi.addVars("saisonEte");
			if(!Objects.equals(saisonHiver, original.getSaisonHiver()))
				requeteApi.addVars("saisonHiver");
			if(!Objects.equals(saisonFuture, original.getSaisonFuture()))
				requeteApi.addVars("saisonFuture");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), anneeCle, sessionCles, saisonJourDebut, saisonEte, saisonHiver, saisonFuture);
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
				&& Objects.equals( anneeCle, that.anneeCle )
				&& Objects.equals( sessionCles, that.sessionCles )
				&& Objects.equals( saisonJourDebut, that.saisonJourDebut )
				&& Objects.equals( saisonEte, that.saisonEte )
				&& Objects.equals( saisonHiver, that.saisonHiver )
				&& Objects.equals( saisonFuture, that.saisonFuture );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SaisonScolaire { ");
		sb.append( "anneeCle: " ).append(anneeCle);
		sb.append( ", sessionCles: " ).append(sessionCles);
		sb.append( ", saisonJourDebut: " ).append(saisonJourDebut);
		sb.append( ", saisonEte: " ).append(saisonEte);
		sb.append( ", saisonHiver: " ).append(saisonHiver);
		sb.append( ", saisonFuture: " ).append(saisonFuture);
		sb.append(" }");
		return sb.toString();
	}
}
