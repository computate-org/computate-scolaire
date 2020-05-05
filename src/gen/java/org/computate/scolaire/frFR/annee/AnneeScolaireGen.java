package org.computate.scolaire.frFR.annee;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.ecole.Ecole;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
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
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe yearCompleteName dans Solr</a>
 * <br/>
 **/
public abstract class AnneeScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(AnneeScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String AnneeScolaire_UnNom = "une année";
	public static final String AnneeScolaire_Ce = "cette ";
	public static final String AnneeScolaire_CeNom = "cette année";
	public static final String AnneeScolaire_Un = "une ";
	public static final String AnneeScolaire_LeNom = "l'année";
	public static final String AnneeScolaire_NomSingulier = "année";
	public static final String AnneeScolaire_NomPluriel = "années";
	public static final String AnneeScolaire_NomActuel = "année actuelle";
	public static final String AnneeScolaire_Tous = "all ";
	public static final String AnneeScolaire_TousNom = "toutes les années";
	public static final String AnneeScolaire_RechercherTousNomPar = "rechercher années par ";
	public static final String AnneeScolaire_RechercherTousNom = "rechercher années";
	public static final String AnneeScolaire_LesNom = "les années";
	public static final String AnneeScolaire_AucunNomTrouve = "aucune année trouvée";
	public static final String AnneeScolaire_NomVar = "année";
	public static final String AnneeScolaire_DeNom = "d'année";
	public static final String AnneeScolaire_NomAdjectifSingulier = "année";
	public static final String AnneeScolaire_NomAdjectifPluriel = "années";
	public static final String AnneeScolaire_Couleur = "orange";
	public static final String AnneeScolaire_IconeGroupe = "regular";
	public static final String AnneeScolaire_IconeNom = "calendar-check";

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
	public AnneeScolaire setEcoleCle(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleCle = Long.parseLong(o);
		this.ecoleCleCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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

	public void inputEcoleCle(String classeApiMethodeMethode) {
		AnneeScolaire s = (AnneeScolaire)this;
		{
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "école")
					.a("title", "Description.enUS: ")
					.a("class", "valeur suggereEcoleCle w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setEcoleCle")
					.a("id", classeApiMethodeMethode, "_ecoleCle")
					.a("autocomplete", "off")
					.a("oninput", "suggereAnneeScolaireEcoleCle($(this).val() ? rechercherEcoleFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'anneeCles:" + pk + "'}", "], $('#listAnneeScolaireEcoleCle_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		}
	}

	public void htmEcoleCle(String classeApiMethodeMethode) {
		AnneeScolaire s = (AnneeScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "AnneeScolaireEcoleCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=anneeCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-pink w3-hover-pink ").f();
								e("i").a("class", "far fa-school ").f().g("i");
								sx("école");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier une école a cette année");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listAnneeScolaireEcoleCle_", classeApiMethodeMethode).f();
								} g("ul");
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
											.a("onclick", "postEcoleVals({ anneeCles: \"", pk, "\" }, function() { patchAnneeScolaireVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "ecoleCle')); });")
											.f().sx("ajouter une école")
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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
	public AnneeScolaire setAnneeCle(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeCle = Long.parseLong(o);
		this.anneeCleCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire anneeCleInit() {
		if(!anneeCleCouverture.dejaInitialise) {
			_anneeCle(anneeCleCouverture);
			if(anneeCle == null)
				setAnneeCle(anneeCleCouverture.o);
		}
		anneeCleCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
		return "clé";
	}

	public String htmTooltipAnneeCle() {
		return null;
	}

	public String htmAnneeCle() {
		return anneeCle == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCle());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
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
	public AnneeScolaire addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (AnneeScolaire)this;
	}
	public AnneeScolaire addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (AnneeScolaire)this;
	}
	public AnneeScolaire setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
		return (AnneeScolaire)this;
	}
	public AnneeScolaire addInscriptionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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

	////////////////
	// saisonCles //
	////////////////

	/**	L'entité « saisonCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> saisonCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> saisonClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("saisonCles").o(saisonCles);

	/**	<br/>L'entité « saisonCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCles">Trouver l'entité saisonCles dans Solr</a>
	 * <br/>
	 * @param saisonCles est l'entité déjà construit. 
	 **/
	protected abstract void _saisonCles(List<Long> o);

	public List<Long> getSaisonCles() {
		return saisonCles;
	}

	public void setSaisonCles(List<Long> saisonCles) {
		this.saisonCles = saisonCles;
		this.saisonClesCouverture.dejaInitialise = true;
	}
	public AnneeScolaire addSaisonCles(Long...objets) {
		for(Long o : objets) {
			addSaisonCles(o);
		}
		return (AnneeScolaire)this;
	}
	public AnneeScolaire addSaisonCles(Long o) {
		if(o != null && !saisonCles.contains(o))
			this.saisonCles.add(o);
		return (AnneeScolaire)this;
	}
	public AnneeScolaire setSaisonCles(JsonArray objets) {
		saisonCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSaisonCles(o);
		}
		return (AnneeScolaire)this;
	}
	public AnneeScolaire addSaisonCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSaisonCles(p);
		}
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire saisonClesInit() {
		if(!saisonClesCouverture.dejaInitialise) {
			_saisonCles(saisonCles);
		}
		saisonClesCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
	}

	public List<Long> solrSaisonCles() {
		return saisonCles;
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

	public void inputSaisonCles(String classeApiMethodeMethode) {
		AnneeScolaire s = (AnneeScolaire)this;
		{
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "saisons")
					.a("title", "Description.enUS: ")
					.a("class", "valeur suggereSaisonCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setSaisonCles")
					.a("id", classeApiMethodeMethode, "_saisonCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereAnneeScolaireSaisonCles($(this).val() ? rechercherSaisonScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'anneeCle:" + pk + "'}", "], $('#listAnneeScolaireSaisonCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		}
	}

	public void htmSaisonCles(String classeApiMethodeMethode) {
		AnneeScolaire s = (AnneeScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "AnneeScolaireSaisonCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=anneeCle:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
								e("i").a("class", "far fa-sun ").f().g("i");
								sx("saisons");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette année");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputSaisonCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listAnneeScolaireSaisonCles_", classeApiMethodeMethode).f();
								} g("ul");
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
											.a("onclick", "postSaisonScolaireVals({ anneeCle: \"", pk, "\" }, function() { patchAnneeScolaireVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "saisonCles')); });")
											.f().sx("ajouter une saison")
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:scolaireTri">Trouver l'entité scolaireTri dans Solr</a>
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
	public AnneeScolaire setScolaireTri(String o) {
		if(NumberUtils.isParsable(o))
			this.scolaireTri = Integer.parseInt(o);
		this.scolaireTriCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire scolaireTriInit() {
		if(!scolaireTriCouverture.dejaInitialise) {
			_scolaireTri(scolaireTriCouverture);
			if(scolaireTri == null)
				setScolaireTri(scolaireTriCouverture.o);
		}
		scolaireTriCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleTri">Trouver l'entité ecoleTri dans Solr</a>
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
	public AnneeScolaire setEcoleTri(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleTri = Integer.parseInt(o);
		this.ecoleTriCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire ecoleTriInit() {
		if(!ecoleTriCouverture.dejaInitialise) {
			_ecoleTri(ecoleTriCouverture);
			if(ecoleTri == null)
				setEcoleTri(ecoleTriCouverture.o);
		}
		ecoleTriCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeTri">Trouver l'entité anneeTri dans Solr</a>
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
	public AnneeScolaire setAnneeTri(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeTri = Integer.parseInt(o);
		this.anneeTriCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire anneeTriInit() {
		if(!anneeTriCouverture.dejaInitialise) {
			_anneeTri(anneeTriCouverture);
			if(anneeTri == null)
				setAnneeTri(anneeTriCouverture.o);
		}
		anneeTriCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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

	////////////////////
	// ecoleRecherche //
	////////////////////

	/**	L'entité « ecoleRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<Ecole>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<Ecole> ecoleRecherche = new ListeRecherche<Ecole>();
	@JsonIgnore
	public Couverture<ListeRecherche<Ecole>> ecoleRechercheCouverture = new Couverture<ListeRecherche<Ecole>>().p(this).c(ListeRecherche.class).var("ecoleRecherche").o(ecoleRecherche);

	/**	<br/>L'entité « ecoleRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<Ecole>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleRecherche">Trouver l'entité ecoleRecherche dans Solr</a>
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
	protected AnneeScolaire ecoleRechercheInit() {
		if(!ecoleRechercheCouverture.dejaInitialise) {
			_ecoleRecherche(ecoleRecherche);
		}
		ecoleRecherche.initLoinPourClasse(requeteSite_);
		ecoleRechercheCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
	}

	////////////
	// ecole_ //
	////////////

	/**	L'entité « ecole_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected Ecole ecole_;
	@JsonIgnore
	public Couverture<Ecole> ecole_Couverture = new Couverture<Ecole>().p(this).c(Ecole.class).var("ecole_").o(ecole_);

	/**	<br/>L'entité « ecole_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecole_">Trouver l'entité ecole_ dans Solr</a>
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
	protected AnneeScolaire ecole_Init() {
		if(!ecole_Couverture.dejaInitialise) {
			_ecole_(ecole_Couverture);
			if(ecole_ == null)
				setEcole_(ecole_Couverture.o);
		}
		ecole_Couverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNom">Trouver l'entité ecoleNom dans Solr</a>
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
	protected AnneeScolaire ecoleNomInit() {
		if(!ecoleNomCouverture.dejaInitialise) {
			_ecoleNom(ecoleNomCouverture);
			if(ecoleNom == null)
				setEcoleNom(ecoleNomCouverture.o);
		}
		ecoleNomCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
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
	protected AnneeScolaire ecoleNomCompletInit() {
		if(!ecoleNomCompletCouverture.dejaInitialise) {
			_ecoleNomComplet(ecoleNomCompletCouverture);
			if(ecoleNomComplet == null)
				setEcoleNomComplet(ecoleNomCompletCouverture.o);
		}
		ecoleNomCompletCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleEmplacement">Trouver l'entité ecoleEmplacement dans Solr</a>
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
	protected AnneeScolaire ecoleEmplacementInit() {
		if(!ecoleEmplacementCouverture.dejaInitialise) {
			_ecoleEmplacement(ecoleEmplacementCouverture);
			if(ecoleEmplacement == null)
				setEcoleEmplacement(ecoleEmplacementCouverture.o);
		}
		ecoleEmplacementCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAddresse">Trouver l'entité ecoleAddresse dans Solr</a>
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
	protected AnneeScolaire ecoleAddresseInit() {
		if(!ecoleAddresseCouverture.dejaInitialise) {
			_ecoleAddresse(ecoleAddresseCouverture);
			if(ecoleAddresse == null)
				setEcoleAddresse(ecoleAddresseCouverture.o);
		}
		ecoleAddresseCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNumeroTelephone">Trouver l'entité ecoleNumeroTelephone dans Solr</a>
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
	protected AnneeScolaire ecoleNumeroTelephoneInit() {
		if(!ecoleNumeroTelephoneCouverture.dejaInitialise) {
			_ecoleNumeroTelephone(ecoleNumeroTelephoneCouverture);
			if(ecoleNumeroTelephone == null)
				setEcoleNumeroTelephone(ecoleNumeroTelephoneCouverture.o);
		}
		ecoleNumeroTelephoneCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleAdministrateurNom">Trouver l'entité ecoleAdministrateurNom dans Solr</a>
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
	protected AnneeScolaire ecoleAdministrateurNomInit() {
		if(!ecoleAdministrateurNomCouverture.dejaInitialise) {
			_ecoleAdministrateurNom(ecoleAdministrateurNomCouverture);
			if(ecoleAdministrateurNom == null)
				setEcoleAdministrateurNom(ecoleAdministrateurNomCouverture.o);
		}
		ecoleAdministrateurNomCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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

	////////////////////////
	// formInscriptionCle //
	////////////////////////

	/**	L'entité « formInscriptionCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long formInscriptionCle;
	@JsonIgnore
	public Couverture<Long> formInscriptionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("formInscriptionCle").o(formInscriptionCle);

	/**	<br/>L'entité « formInscriptionCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:formInscriptionCle">Trouver l'entité formInscriptionCle dans Solr</a>
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
	public AnneeScolaire setFormInscriptionCle(String o) {
		if(NumberUtils.isParsable(o))
			this.formInscriptionCle = Long.parseLong(o);
		this.formInscriptionCleCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire formInscriptionCleInit() {
		if(!formInscriptionCleCouverture.dejaInitialise) {
			_formInscriptionCle(formInscriptionCleCouverture);
			if(formInscriptionCle == null)
				setFormInscriptionCle(formInscriptionCleCouverture.o);
		}
		formInscriptionCleCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
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
	public AnneeScolaire setAnneeDebut(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeDebut = Integer.parseInt(o);
		this.anneeDebutCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire anneeDebutInit() {
		if(!anneeDebutCouverture.dejaInitialise) {
			_anneeDebut(anneeDebutCouverture);
			if(anneeDebut == null)
				setAnneeDebut(anneeDebutCouverture.o);
		}
		anneeDebutCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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

	public void inputAnneeDebut(String classeApiMethodeMethode) {
		AnneeScolaire s = (AnneeScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "début de l'année")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_anneeDebut");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setAnneeDebut inputAnneeScolaire", pk, "AnneeDebut w3-input w3-border ");
					a("name", "setAnneeDebut");
				} else {
					a("class", "valeurAnneeDebut w3-input w3-border inputAnneeScolaire", pk, "AnneeDebut w3-input w3-border ");
					a("name", "anneeDebut");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchAnneeScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setAnneeDebut', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_anneeDebut')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_anneeDebut')); }); ");
				}
				a("value", strAnneeDebut())
			.fg();

		}
	}

	public void htmAnneeDebut(String classeApiMethodeMethode) {
		AnneeScolaire s = (AnneeScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "AnneeScolaireAnneeDebut").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classeApiMethodeMethode, "_anneeDebut").a("class", "").f().sx("début de l'année").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputAnneeDebut(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_anneeDebut')); $('#", classeApiMethodeMethode, "_anneeDebut').val(null); patchAnneeScolaireVal([{ name: 'fq', value: 'pk:' + $('#AnneeScolaireForm :input[name=pk]').val() }], 'setAnneeDebut', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_anneeDebut')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_anneeDebut')); }); ")
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
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
	public AnneeScolaire setAnneeFin(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeFin = Integer.parseInt(o);
		this.anneeFinCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire anneeFinInit() {
		if(!anneeFinCouverture.dejaInitialise) {
			_anneeFin(anneeFinCouverture);
			if(anneeFin == null)
				setAnneeFin(anneeFinCouverture.o);
		}
		anneeFinCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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

	public void inputAnneeFin(String classeApiMethodeMethode) {
		AnneeScolaire s = (AnneeScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "le fin de l'année")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_anneeFin");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setAnneeFin inputAnneeScolaire", pk, "AnneeFin w3-input w3-border ");
					a("name", "setAnneeFin");
				} else {
					a("class", "valeurAnneeFin w3-input w3-border inputAnneeScolaire", pk, "AnneeFin w3-input w3-border ");
					a("name", "anneeFin");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchAnneeScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setAnneeFin', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_anneeFin')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_anneeFin')); }); ");
				}
				a("value", strAnneeFin())
			.fg();

		}
	}

	public void htmAnneeFin(String classeApiMethodeMethode) {
		AnneeScolaire s = (AnneeScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "AnneeScolaireAnneeFin").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classeApiMethodeMethode, "_anneeFin").a("class", "").f().sx("le fin de l'année").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputAnneeFin(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_anneeFin')); $('#", classeApiMethodeMethode, "_anneeFin').val(null); patchAnneeScolaireVal([{ name: 'fq', value: 'pk:' + $('#AnneeScolaireForm :input[name=pk]').val() }], 'setAnneeFin', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_anneeFin')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_anneeFin')); }); ")
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFraisInscription">Trouver l'entité anneeFraisInscription dans Solr</a>
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
	public AnneeScolaire setAnneeFraisInscription(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	public AnneeScolaire setAnneeFraisInscription(Double o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	public AnneeScolaire setAnneeFraisInscription(Integer o) {
			this.anneeFraisInscription = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.anneeFraisInscriptionCouverture.dejaInitialise = true;
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire anneeFraisInscriptionInit() {
		if(!anneeFraisInscriptionCouverture.dejaInitialise) {
			_anneeFraisInscription(anneeFraisInscriptionCouverture);
			if(anneeFraisInscription == null)
				setAnneeFraisInscription(anneeFraisInscriptionCouverture.o);
		}
		anneeFraisInscriptionCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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
		return "frais d'inscription";
	}

	public String htmTooltipAnneeFraisInscription() {
		return null;
	}

	public String htmAnneeFraisInscription() {
		return anneeFraisInscription == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeFraisInscription());
	}

	public void inputAnneeFraisInscription(String classeApiMethodeMethode) {
		AnneeScolaire s = (AnneeScolaire)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "frais d'inscription")
				.a("title", "Description.enUS: ")
				.a("id", classeApiMethodeMethode, "_anneeFraisInscription");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setAnneeFraisInscription inputAnneeScolaire", pk, "AnneeFraisInscription w3-input w3-border ");
					a("name", "setAnneeFraisInscription");
				} else {
					a("class", "valeurAnneeFraisInscription w3-input w3-border inputAnneeScolaire", pk, "AnneeFraisInscription w3-input w3-border ");
					a("name", "anneeFraisInscription");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchAnneeScolaireVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setAnneeFraisInscription', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_anneeFraisInscription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_anneeFraisInscription')); }); ");
				}
				a("value", strAnneeFraisInscription())
			.fg();

		}
	}

	public void htmAnneeFraisInscription(String classeApiMethodeMethode) {
		AnneeScolaire s = (AnneeScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "AnneeScolaireAnneeFraisInscription").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classeApiMethodeMethode, "_anneeFraisInscription").a("class", "").f().sx("frais d'inscription").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputAnneeFraisInscription(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_anneeFraisInscription')); $('#", classeApiMethodeMethode, "_anneeFraisInscription').val(null); patchAnneeScolaireVal([{ name: 'fq', value: 'pk:' + $('#AnneeScolaireForm :input[name=pk]').val() }], 'setAnneeFraisInscription', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_anneeFraisInscription')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_anneeFraisInscription')); }); ")
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
	// anneesAnnee //
	/////////////////

	/**	L'entité « anneesAnnee »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<AnneeScolaire>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<AnneeScolaire> anneesAnnee = new java.util.ArrayList<org.computate.scolaire.frFR.annee.AnneeScolaire>();
	@JsonIgnore
	public Couverture<List<AnneeScolaire>> anneesAnneeCouverture = new Couverture<List<AnneeScolaire>>().p(this).c(List.class).var("anneesAnnee").o(anneesAnnee);

	/**	<br/>L'entité « anneesAnnee »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<AnneeScolaire>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneesAnnee">Trouver l'entité anneesAnnee dans Solr</a>
	 * <br/>
	 * @param anneesAnnee est l'entité déjà construit. 
	 **/
	protected abstract void _anneesAnnee(List<AnneeScolaire> l);

	public List<AnneeScolaire> getAnneesAnnee() {
		return anneesAnnee;
	}

	public void setAnneesAnnee(List<AnneeScolaire> anneesAnnee) {
		this.anneesAnnee = anneesAnnee;
		this.anneesAnneeCouverture.dejaInitialise = true;
	}
	public AnneeScolaire addAnneesAnnee(AnneeScolaire...objets) {
		for(AnneeScolaire o : objets) {
			addAnneesAnnee(o);
		}
		return (AnneeScolaire)this;
	}
	public AnneeScolaire addAnneesAnnee(AnneeScolaire o) {
		if(o != null && !anneesAnnee.contains(o))
			this.anneesAnnee.add(o);
		return (AnneeScolaire)this;
	}
	protected AnneeScolaire anneesAnneeInit() {
		if(!anneesAnneeCouverture.dejaInitialise) {
			_anneesAnnee(anneesAnnee);
		}
		anneesAnneeCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
	}

	///////////////////
	// anneeNomCourt //
	///////////////////

	/**	L'entité « anneeNomCourt »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String anneeNomCourt;
	@JsonIgnore
	public Couverture<String> anneeNomCourtCouverture = new Couverture<String>().p(this).c(String.class).var("anneeNomCourt").o(anneeNomCourt);

	/**	<br/>L'entité « anneeNomCourt »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeNomCourt">Trouver l'entité anneeNomCourt dans Solr</a>
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
	protected AnneeScolaire anneeNomCourtInit() {
		if(!anneeNomCourtCouverture.dejaInitialise) {
			_anneeNomCourt(anneeNomCourtCouverture);
			if(anneeNomCourt == null)
				setAnneeNomCourt(anneeNomCourtCouverture.o);
		}
		anneeNomCourtCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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

	/**	L'entité « anneeNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String anneeNomComplet;
	@JsonIgnore
	public Couverture<String> anneeNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("anneeNomComplet").o(anneeNomComplet);

	/**	<br/>L'entité « anneeNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.annee.AnneeScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeNomComplet">Trouver l'entité anneeNomComplet dans Solr</a>
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
	protected AnneeScolaire anneeNomCompletInit() {
		if(!anneeNomCompletCouverture.dejaInitialise) {
			_anneeNomComplet(anneeNomCompletCouverture);
			if(anneeNomComplet == null)
				setAnneeNomComplet(anneeNomCompletCouverture.o);
		}
		anneeNomCompletCouverture.dejaInitialise(true);
		return (AnneeScolaire)this;
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

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseAnneeScolaire = false;

	public AnneeScolaire initLoinAnneeScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseAnneeScolaire) {
			dejaInitialiseAnneeScolaire = true;
			initLoinAnneeScolaire();
		}
		return (AnneeScolaire)this;
	}

	public void initLoinAnneeScolaire() {
		initAnneeScolaire();
		super.initLoinCluster(requeteSite_);
	}

	public void initAnneeScolaire() {
		ecoleCleInit();
		anneeCleInit();
		inscriptionClesInit();
		saisonClesInit();
		scolaireTriInit();
		ecoleTriInit();
		anneeTriInit();
		ecoleRechercheInit();
		ecole_Init();
		ecoleNomInit();
		ecoleNomCompletInit();
		ecoleEmplacementInit();
		ecoleAddresseInit();
		ecoleNumeroTelephoneInit();
		ecoleAdministrateurNomInit();
		formInscriptionCleInit();
		anneeDebutInit();
		anneeFinInit();
		anneeFraisInscriptionInit();
		anneesAnneeInit();
		anneeNomCourtInit();
		anneeNomCompletInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinAnneeScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteAnneeScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(ecoleRecherche != null)
			ecoleRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteAnneeScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAnneeScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirAnneeScolaire(String var) {
		AnneeScolaire oAnneeScolaire = (AnneeScolaire)this;
		switch(var) {
			case "ecoleCle":
				return oAnneeScolaire.ecoleCle;
			case "anneeCle":
				return oAnneeScolaire.anneeCle;
			case "inscriptionCles":
				return oAnneeScolaire.inscriptionCles;
			case "saisonCles":
				return oAnneeScolaire.saisonCles;
			case "scolaireTri":
				return oAnneeScolaire.scolaireTri;
			case "ecoleTri":
				return oAnneeScolaire.ecoleTri;
			case "anneeTri":
				return oAnneeScolaire.anneeTri;
			case "ecoleRecherche":
				return oAnneeScolaire.ecoleRecherche;
			case "ecole_":
				return oAnneeScolaire.ecole_;
			case "ecoleNom":
				return oAnneeScolaire.ecoleNom;
			case "ecoleNomComplet":
				return oAnneeScolaire.ecoleNomComplet;
			case "ecoleEmplacement":
				return oAnneeScolaire.ecoleEmplacement;
			case "ecoleAddresse":
				return oAnneeScolaire.ecoleAddresse;
			case "ecoleNumeroTelephone":
				return oAnneeScolaire.ecoleNumeroTelephone;
			case "ecoleAdministrateurNom":
				return oAnneeScolaire.ecoleAdministrateurNom;
			case "formInscriptionCle":
				return oAnneeScolaire.formInscriptionCle;
			case "anneeDebut":
				return oAnneeScolaire.anneeDebut;
			case "anneeFin":
				return oAnneeScolaire.anneeFin;
			case "anneeFraisInscription":
				return oAnneeScolaire.anneeFraisInscription;
			case "anneesAnnee":
				return oAnneeScolaire.anneesAnnee;
			case "anneeNomCourt":
				return oAnneeScolaire.anneeNomCourt;
			case "anneeNomComplet":
				return oAnneeScolaire.anneeNomComplet;
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
				o = attribuerAnneeScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAnneeScolaire(String var, Object val) {
		AnneeScolaire oAnneeScolaire = (AnneeScolaire)this;
		switch(var) {
			case "ecoleCle":
				oAnneeScolaire.setEcoleCle((Long)val);
				if(!sauvegardesAnneeScolaire.contains(var))
					sauvegardesAnneeScolaire.add(var);
				return val;
			case "saisonCles":
				oAnneeScolaire.addSaisonCles((Long)val);
				if(!sauvegardesAnneeScolaire.contains(var))
					sauvegardesAnneeScolaire.add(var);
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
					o = definirAnneeScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAnneeScolaire(String var, String val) {
		switch(var) {
			case "anneeDebut":
				setAnneeDebut(val);
				sauvegardesAnneeScolaire.add(var);
				return val;
			case "anneeFin":
				setAnneeFin(val);
				sauvegardesAnneeScolaire.add(var);
				return val;
			case "anneeFraisInscription":
				setAnneeFraisInscription(val);
				sauvegardesAnneeScolaire.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesAnneeScolaire = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerAnneeScolaire(solrDocument);
	}
	public void peuplerAnneeScolaire(SolrDocument solrDocument) {
		AnneeScolaire oAnneeScolaire = (AnneeScolaire)this;
		sauvegardesAnneeScolaire = (List<String>)solrDocument.get("sauvegardesAnneeScolaire_stored_strings");
		if(sauvegardesAnneeScolaire != null) {

			Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
			if(ecoleCle != null)
				oAnneeScolaire.setEcoleCle(ecoleCle);

			if(sauvegardesAnneeScolaire.contains("anneeCle")) {
				Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
				if(anneeCle != null)
					oAnneeScolaire.setAnneeCle(anneeCle);
			}

			if(sauvegardesAnneeScolaire.contains("inscriptionCles")) {
				List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
				if(inscriptionCles != null)
					oAnneeScolaire.inscriptionCles.addAll(inscriptionCles);
			}

			List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
			if(saisonCles != null)
				oAnneeScolaire.saisonCles.addAll(saisonCles);

			if(sauvegardesAnneeScolaire.contains("scolaireTri")) {
				Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
				if(scolaireTri != null)
					oAnneeScolaire.setScolaireTri(scolaireTri);
			}

			if(sauvegardesAnneeScolaire.contains("ecoleTri")) {
				Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
				if(ecoleTri != null)
					oAnneeScolaire.setEcoleTri(ecoleTri);
			}

			if(sauvegardesAnneeScolaire.contains("anneeTri")) {
				Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
				if(anneeTri != null)
					oAnneeScolaire.setAnneeTri(anneeTri);
			}

			if(sauvegardesAnneeScolaire.contains("ecoleNom")) {
				String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
				if(ecoleNom != null)
					oAnneeScolaire.setEcoleNom(ecoleNom);
			}

			if(sauvegardesAnneeScolaire.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oAnneeScolaire.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardesAnneeScolaire.contains("ecoleEmplacement")) {
				String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
				if(ecoleEmplacement != null)
					oAnneeScolaire.setEcoleEmplacement(ecoleEmplacement);
			}

			if(sauvegardesAnneeScolaire.contains("ecoleAddresse")) {
				String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
				if(ecoleAddresse != null)
					oAnneeScolaire.setEcoleAddresse(ecoleAddresse);
			}

			if(sauvegardesAnneeScolaire.contains("ecoleNumeroTelephone")) {
				String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
				if(ecoleNumeroTelephone != null)
					oAnneeScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);
			}

			if(sauvegardesAnneeScolaire.contains("ecoleAdministrateurNom")) {
				String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
				if(ecoleAdministrateurNom != null)
					oAnneeScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);
			}

			if(sauvegardesAnneeScolaire.contains("formInscriptionCle")) {
				Long formInscriptionCle = (Long)solrDocument.get("formInscriptionCle_stored_long");
				if(formInscriptionCle != null)
					oAnneeScolaire.setFormInscriptionCle(formInscriptionCle);
			}

			if(sauvegardesAnneeScolaire.contains("anneeDebut")) {
				Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
				if(anneeDebut != null)
					oAnneeScolaire.setAnneeDebut(anneeDebut);
			}

			if(sauvegardesAnneeScolaire.contains("anneeFin")) {
				Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
				if(anneeFin != null)
					oAnneeScolaire.setAnneeFin(anneeFin);
			}

			if(sauvegardesAnneeScolaire.contains("anneeFraisInscription")) {
				Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
				if(anneeFraisInscription != null)
					oAnneeScolaire.setAnneeFraisInscription(anneeFraisInscription);
			}

			if(sauvegardesAnneeScolaire.contains("anneeNomCourt")) {
				String anneeNomCourt = (String)solrDocument.get("anneeNomCourt_stored_string");
				if(anneeNomCourt != null)
					oAnneeScolaire.setAnneeNomCourt(anneeNomCourt);
			}

			if(sauvegardesAnneeScolaire.contains("anneeNomComplet")) {
				String anneeNomComplet = (String)solrDocument.get("anneeNomComplet_stored_string");
				if(anneeNomComplet != null)
					oAnneeScolaire.setAnneeNomComplet(anneeNomComplet);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.annee.AnneeScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			AnneeScolaire o = new AnneeScolaire();
			o.requeteSiteAnneeScolaire(requeteSite);
			o.initLoinAnneeScolaire(requeteSite);
			o.indexerAnneeScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerAnneeScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerAnneeScolaire(document);
	}

	public void indexerAnneeScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerAnneeScolaire(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerAnneeScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerAnneeScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerAnneeScolaire(SolrInputDocument document) {
		if(sauvegardesAnneeScolaire != null)
			document.addField("sauvegardesAnneeScolaire_stored_strings", sauvegardesAnneeScolaire);

		if(ecoleCle != null) {
			document.addField("ecoleCle_indexed_long", ecoleCle);
			document.addField("ecoleCle_stored_long", ecoleCle);
		}
		if(anneeCle != null) {
			document.addField("anneeCle_indexed_long", anneeCle);
			document.addField("anneeCle_stored_long", anneeCle);
		}
		if(inscriptionCles != null) {
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_indexed_longs", o);
			}
			for(java.lang.Long o : inscriptionCles) {
				document.addField("inscriptionCles_stored_longs", o);
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
		if(formInscriptionCle != null) {
			document.addField("formInscriptionCle_indexed_long", formInscriptionCle);
			document.addField("formInscriptionCle_stored_long", formInscriptionCle);
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
		if(anneeNomCourt != null) {
			document.addField("anneeNomCourt_indexed_string", anneeNomCourt);
			document.addField("anneeNomCourt_stored_string", anneeNomCourt);
		}
		if(anneeNomComplet != null) {
			document.addField("anneeNomComplet_indexed_string", anneeNomComplet);
			document.addField("anneeNomComplet_stored_string", anneeNomComplet);
		}
		super.indexerCluster(document);

	}

	public void desindexerAnneeScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinAnneeScolaire(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeAnneeScolaire(String entiteVar) {
		switch(entiteVar) {
			case "ecoleCle":
				return "ecoleCle_indexed_long";
			case "anneeCle":
				return "anneeCle_indexed_long";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "saisonCles":
				return "saisonCles_indexed_longs";
			case "scolaireTri":
				return "scolaireTri_indexed_int";
			case "ecoleTri":
				return "ecoleTri_indexed_int";
			case "anneeTri":
				return "anneeTri_indexed_int";
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
			case "formInscriptionCle":
				return "formInscriptionCle_indexed_long";
			case "anneeDebut":
				return "anneeDebut_indexed_int";
			case "anneeFin":
				return "anneeFin_indexed_int";
			case "anneeFraisInscription":
				return "anneeFraisInscription_indexed_double";
			case "anneeNomCourt":
				return "anneeNomCourt_indexed_string";
			case "anneeNomComplet":
				return "anneeNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheAnneeScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereAnneeScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerAnneeScolaire(solrDocument);
	}
	public void stockerAnneeScolaire(SolrDocument solrDocument) {
		AnneeScolaire oAnneeScolaire = (AnneeScolaire)this;

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oAnneeScolaire.setEcoleCle(ecoleCle);

		Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
		if(anneeCle != null)
			oAnneeScolaire.setAnneeCle(anneeCle);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oAnneeScolaire.inscriptionCles.addAll(inscriptionCles);

		List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
		if(saisonCles != null)
			oAnneeScolaire.saisonCles.addAll(saisonCles);

		Integer scolaireTri = (Integer)solrDocument.get("scolaireTri_stored_int");
		if(scolaireTri != null)
			oAnneeScolaire.setScolaireTri(scolaireTri);

		Integer ecoleTri = (Integer)solrDocument.get("ecoleTri_stored_int");
		if(ecoleTri != null)
			oAnneeScolaire.setEcoleTri(ecoleTri);

		Integer anneeTri = (Integer)solrDocument.get("anneeTri_stored_int");
		if(anneeTri != null)
			oAnneeScolaire.setAnneeTri(anneeTri);

		String ecoleNom = (String)solrDocument.get("ecoleNom_stored_string");
		if(ecoleNom != null)
			oAnneeScolaire.setEcoleNom(ecoleNom);

		String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
		if(ecoleNomComplet != null)
			oAnneeScolaire.setEcoleNomComplet(ecoleNomComplet);

		String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
		if(ecoleEmplacement != null)
			oAnneeScolaire.setEcoleEmplacement(ecoleEmplacement);

		String ecoleAddresse = (String)solrDocument.get("ecoleAddresse_stored_string");
		if(ecoleAddresse != null)
			oAnneeScolaire.setEcoleAddresse(ecoleAddresse);

		String ecoleNumeroTelephone = (String)solrDocument.get("ecoleNumeroTelephone_stored_string");
		if(ecoleNumeroTelephone != null)
			oAnneeScolaire.setEcoleNumeroTelephone(ecoleNumeroTelephone);

		String ecoleAdministrateurNom = (String)solrDocument.get("ecoleAdministrateurNom_stored_string");
		if(ecoleAdministrateurNom != null)
			oAnneeScolaire.setEcoleAdministrateurNom(ecoleAdministrateurNom);

		Long formInscriptionCle = (Long)solrDocument.get("formInscriptionCle_stored_long");
		if(formInscriptionCle != null)
			oAnneeScolaire.setFormInscriptionCle(formInscriptionCle);

		Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
		if(anneeDebut != null)
			oAnneeScolaire.setAnneeDebut(anneeDebut);

		Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
		if(anneeFin != null)
			oAnneeScolaire.setAnneeFin(anneeFin);

		Double anneeFraisInscription = (Double)solrDocument.get("anneeFraisInscription_stored_double");
		if(anneeFraisInscription != null)
			oAnneeScolaire.setAnneeFraisInscription(anneeFraisInscription);

		String anneeNomCourt = (String)solrDocument.get("anneeNomCourt_stored_string");
		if(anneeNomCourt != null)
			oAnneeScolaire.setAnneeNomCourt(anneeNomCourt);

		String anneeNomComplet = (String)solrDocument.get("anneeNomComplet_stored_string");
		if(anneeNomComplet != null)
			oAnneeScolaire.setAnneeNomComplet(anneeNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiAnneeScolaire() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof AnneeScolaire) {
			AnneeScolaire original = (AnneeScolaire)o;
			if(!Objects.equals(ecoleCle, original.getEcoleCle()))
				requeteApi.addVars("ecoleCle");
			if(!Objects.equals(saisonCles, original.getSaisonCles()))
				requeteApi.addVars("saisonCles");
			if(!Objects.equals(anneeDebut, original.getAnneeDebut()))
				requeteApi.addVars("anneeDebut");
			if(!Objects.equals(anneeFin, original.getAnneeFin()))
				requeteApi.addVars("anneeFin");
			if(!Objects.equals(anneeFraisInscription, original.getAnneeFraisInscription()))
				requeteApi.addVars("anneeFraisInscription");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), ecoleCle, saisonCles, anneeDebut, anneeFin, anneeFraisInscription);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof AnneeScolaire))
			return false;
		AnneeScolaire that = (AnneeScolaire)o;
		return super.equals(o)
				&& Objects.equals( ecoleCle, that.ecoleCle )
				&& Objects.equals( saisonCles, that.saisonCles )
				&& Objects.equals( anneeDebut, that.anneeDebut )
				&& Objects.equals( anneeFin, that.anneeFin )
				&& Objects.equals( anneeFraisInscription, that.anneeFraisInscription );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("AnneeScolaire { ");
		sb.append( "ecoleCle: " ).append(ecoleCle);
		sb.append( ", saisonCles: " ).append(saisonCles);
		sb.append( ", anneeDebut: " ).append(anneeDebut);
		sb.append( ", anneeFin: " ).append(anneeFin);
		sb.append( ", anneeFraisInscription: " ).append(anneeFraisInscription);
		sb.append(" }");
		return sb.toString();
	}
}
