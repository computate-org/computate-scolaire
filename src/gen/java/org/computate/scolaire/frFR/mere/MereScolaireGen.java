package org.computate.scolaire.frFR.mere;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe momCompleteName dans Solr. </a>
 * <br/>
 **/
public abstract class MereScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(MereScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String MereScolaire_UnNom = "une mère";
	public static final String MereScolaire_Ce = "cette ";
	public static final String MereScolaire_CeNom = "cette mère";
	public static final String MereScolaire_Un = "une ";
	public static final String MereScolaire_LeNom = "la mère";
	public static final String MereScolaire_NomSingulier = "mère";
	public static final String MereScolaire_NomPluriel = "mères";
	public static final String MereScolaire_NomActuel = "mère actuelle";
	public static final String MereScolaire_Tous = "all ";
	public static final String MereScolaire_TousNom = "toutes les mères";
	public static final String MereScolaire_RechercherTousNomPar = "rechercher mères par ";
	public static final String MereScolaire_RechercherTousNom = "rechercher mères";
	public static final String MereScolaire_Titre = "mères";
	public static final String MereScolaire_LesNom = "les mères";
	public static final String MereScolaire_AucunNomTrouve = "aucune mère trouvée";
	public static final String MereScolaire_NomVar = "mère";
	public static final String MereScolaire_DeNom = "de mère";
	public static final String MereScolaire_NomAdjectifSingulier = "mère";
	public static final String MereScolaire_NomAdjectifPluriel = "mères";
	public static final String MereScolaire_Couleur = "pink";
	public static final String MereScolaire_IconeGroupe = "regular";
	public static final String MereScolaire_IconeNom = "female";

	/////////////
	// mereCle //
	/////////////

	/**	 L'entité mereCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long mereCle;
	@JsonIgnore
	public Couverture<Long> mereCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("mereCle").o(mereCle);

	/**	<br/> L'entité mereCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereCle">Trouver l'entité mereCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mereCle(Couverture<Long> c);

	public Long getMereCle() {
		return mereCle;
	}

	public void setMereCle(Long mereCle) {
		this.mereCle = mereCle;
		this.mereCleCouverture.dejaInitialise = true;
	}
	public void setMereCle(String o) {
		this.mereCle = MereScolaire.staticSetMereCle(requeteSite_, o);
		this.mereCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetMereCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected MereScolaire mereCleInit() {
		if(!mereCleCouverture.dejaInitialise) {
			_mereCle(mereCleCouverture);
			if(mereCle == null)
				setMereCle(mereCleCouverture.o);
		}
		mereCleCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Long staticSolrMereCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrMereCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMereCle(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrMereCle(requeteSite_, MereScolaire.staticSolrMereCle(requeteSite_, MereScolaire.staticSetMereCle(requeteSite_, o)));
	}

	public Long solrMereCle() {
		return MereScolaire.staticSolrMereCle(requeteSite_, mereCle);
	}

	public String strMereCle() {
		return mereCle == null ? "" : mereCle.toString();
	}

	public String jsonMereCle() {
		return mereCle == null ? "" : mereCle.toString();
	}

	public String nomAffichageMereCle() {
		return "clé";
	}

	public String htmTooltipMereCle() {
		return null;
	}

	public String htmMereCle() {
		return mereCle == null ? "" : StringEscapeUtils.escapeHtml4(strMereCle());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
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
		Long l = MereScolaire.staticSetInscriptionCles(requeteSite_, o);
		if(l != null)
			addInscriptionCles(l);
		this.inscriptionClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetInscriptionCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public MereScolaire addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (MereScolaire)this;
	}
	public MereScolaire addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (MereScolaire)this;
	}
	public void setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
	}
	public MereScolaire addInscriptionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (MereScolaire)this;
	}
	protected MereScolaire inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Long staticSolrInscriptionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrInscriptionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionCles(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrInscriptionCles(requeteSite_, MereScolaire.staticSolrInscriptionCles(requeteSite_, MereScolaire.staticSetInscriptionCles(requeteSite_, o)));
	}

	public List<Long> solrInscriptionCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : inscriptionCles) {
			l.add(MereScolaire.staticSolrInscriptionCles(requeteSite_, o));
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
		MereScolaire s = (MereScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "inscriptions")
					.a("class", "valeur suggereInscriptionCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setInscriptionCles")
					.a("id", classeApiMethodeMethode, "_inscriptionCles")
					.a("autocomplete", "off");
					if("Page".equals(classeApiMethodeMethode)) {
						a("oninput", "suggereMereScolaireInscriptionCles($(this).val() ? rechercherInscriptionScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'mereCles:" + pk + "'}", "], $('#listMereScolaireInscriptionCles_", classeApiMethodeMethode, "'), ", pk, "); ");
					}

				fg();

		} else {
			e("span").a("class", "varMereScolaire", pk, "InscriptionCles ").f().sx(htmInscriptionCles()).g("span");
		}
	}

	public void htmInscriptionCles(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolaireInscriptionCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/inscription?fq=mereCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("inscriptions");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette mère");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listMereScolaireInscriptionCles_", classeApiMethodeMethode).f();
								} g("ul");
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
											.a("id", classeApiMethodeMethode, "_inscriptionCles_ajouter")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postInscriptionScolaireVals({ mereCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "inscriptionCles')); });")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleTri">Trouver l'entité familleTri dans Solr</a>
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
		this.familleTri = MereScolaire.staticSetFamilleTri(requeteSite_, o);
		this.familleTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetFamilleTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected MereScolaire familleTriInit() {
		if(!familleTriCouverture.dejaInitialise) {
			_familleTri(familleTriCouverture);
			if(familleTri == null)
				setFamilleTri(familleTriCouverture.o);
		}
		familleTriCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Integer staticSolrFamilleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrFamilleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilleTri(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrFamilleTri(requeteSite_, MereScolaire.staticSolrFamilleTri(requeteSite_, MereScolaire.staticSetFamilleTri(requeteSite_, o)));
	}

	public Integer solrFamilleTri() {
		return MereScolaire.staticSolrFamilleTri(requeteSite_, familleTri);
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

	/////////////
	// mereTri //
	/////////////

	/**	 L'entité mereTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer mereTri;
	@JsonIgnore
	public Couverture<Integer> mereTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("mereTri").o(mereTri);

	/**	<br/> L'entité mereTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereTri">Trouver l'entité mereTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mereTri(Couverture<Integer> c);

	public Integer getMereTri() {
		return mereTri;
	}

	public void setMereTri(Integer mereTri) {
		this.mereTri = mereTri;
		this.mereTriCouverture.dejaInitialise = true;
	}
	public void setMereTri(String o) {
		this.mereTri = MereScolaire.staticSetMereTri(requeteSite_, o);
		this.mereTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetMereTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected MereScolaire mereTriInit() {
		if(!mereTriCouverture.dejaInitialise) {
			_mereTri(mereTriCouverture);
			if(mereTri == null)
				setMereTri(mereTriCouverture.o);
		}
		mereTriCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Integer staticSolrMereTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrMereTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMereTri(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrMereTri(requeteSite_, MereScolaire.staticSolrMereTri(requeteSite_, MereScolaire.staticSetMereTri(requeteSite_, o)));
	}

	public Integer solrMereTri() {
		return MereScolaire.staticSolrMereTri(requeteSite_, mereTri);
	}

	public String strMereTri() {
		return mereTri == null ? "" : mereTri.toString();
	}

	public String jsonMereTri() {
		return mereTri == null ? "" : mereTri.toString();
	}

	public String nomAffichageMereTri() {
		return null;
	}

	public String htmTooltipMereTri() {
		return null;
	}

	public String htmMereTri() {
		return mereTri == null ? "" : StringEscapeUtils.escapeHtml4(strMereTri());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionRecherche">Trouver l'entité inscriptionRecherche dans Solr</a>
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
	protected MereScolaire inscriptionRechercheInit() {
		if(!inscriptionRechercheCouverture.dejaInitialise) {
			_inscriptionRecherche(inscriptionRecherche);
		}
		inscriptionRecherche.initLoinPourClasse(requeteSite_);
		inscriptionRechercheCouverture.dejaInitialise(true);
		return (MereScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptions">Trouver l'entité inscriptions dans Solr</a>
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
	public static List<InscriptionScolaire> staticSetInscriptions(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	public MereScolaire addInscriptions(InscriptionScolaire...objets) {
		for(InscriptionScolaire o : objets) {
			addInscriptions(o);
		}
		return (MereScolaire)this;
	}
	public MereScolaire addInscriptions(InscriptionScolaire o) {
		if(o != null && !inscriptions.contains(o))
			this.inscriptions.add(o);
		return (MereScolaire)this;
	}
	protected MereScolaire inscriptionsInit() {
		if(!inscriptionsCouverture.dejaInitialise) {
			_inscriptions(inscriptions);
		}
		inscriptionsCouverture.dejaInitialise(true);
		return (MereScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurCles">Trouver l'entité utilisateurCles dans Solr</a>
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
		Long l = MereScolaire.staticSetUtilisateurCles(requeteSite_, o);
		if(l != null)
			addUtilisateurCles(l);
		this.utilisateurClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetUtilisateurCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public MereScolaire addUtilisateurCles(Long...objets) {
		for(Long o : objets) {
			addUtilisateurCles(o);
		}
		return (MereScolaire)this;
	}
	public MereScolaire addUtilisateurCles(Long o) {
		if(o != null && !utilisateurCles.contains(o))
			this.utilisateurCles.add(o);
		return (MereScolaire)this;
	}
	public void setUtilisateurCles(JsonArray objets) {
		utilisateurCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUtilisateurCles(o);
		}
	}
	public MereScolaire addUtilisateurCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUtilisateurCles(p);
		}
		return (MereScolaire)this;
	}
	protected MereScolaire utilisateurClesInit() {
		if(!utilisateurClesCouverture.dejaInitialise) {
			_utilisateurCles(utilisateurCles);
		}
		utilisateurClesCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Long staticSolrUtilisateurCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrUtilisateurCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUtilisateurCles(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrUtilisateurCles(requeteSite_, MereScolaire.staticSolrUtilisateurCles(requeteSite_, MereScolaire.staticSetUtilisateurCles(requeteSite_, o)));
	}

	public List<Long> solrUtilisateurCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : utilisateurCles) {
			l.add(MereScolaire.staticSolrUtilisateurCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCles">Trouver l'entité ecoleCles dans Solr</a>
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
		Long l = MereScolaire.staticSetEcoleCles(requeteSite_, o);
		if(l != null)
			addEcoleCles(l);
		this.ecoleClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetEcoleCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public MereScolaire addEcoleCles(Long...objets) {
		for(Long o : objets) {
			addEcoleCles(o);
		}
		return (MereScolaire)this;
	}
	public MereScolaire addEcoleCles(Long o) {
		if(o != null && !ecoleCles.contains(o))
			this.ecoleCles.add(o);
		return (MereScolaire)this;
	}
	public void setEcoleCles(JsonArray objets) {
		ecoleCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEcoleCles(o);
		}
	}
	public MereScolaire addEcoleCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEcoleCles(p);
		}
		return (MereScolaire)this;
	}
	protected MereScolaire ecoleClesInit() {
		if(!ecoleClesCouverture.dejaInitialise) {
			_ecoleCles(ecoleCles);
		}
		ecoleClesCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Long staticSolrEcoleCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrEcoleCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleCles(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrEcoleCles(requeteSite_, MereScolaire.staticSolrEcoleCles(requeteSite_, MereScolaire.staticSetEcoleCles(requeteSite_, o)));
	}

	public List<Long> solrEcoleCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : ecoleCles) {
			l.add(MereScolaire.staticSolrEcoleCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCles">Trouver l'entité anneeCles dans Solr</a>
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
		Long l = MereScolaire.staticSetAnneeCles(requeteSite_, o);
		if(l != null)
			addAnneeCles(l);
		this.anneeClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetAnneeCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public MereScolaire addAnneeCles(Long...objets) {
		for(Long o : objets) {
			addAnneeCles(o);
		}
		return (MereScolaire)this;
	}
	public MereScolaire addAnneeCles(Long o) {
		if(o != null && !anneeCles.contains(o))
			this.anneeCles.add(o);
		return (MereScolaire)this;
	}
	public void setAnneeCles(JsonArray objets) {
		anneeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAnneeCles(o);
		}
	}
	public MereScolaire addAnneeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAnneeCles(p);
		}
		return (MereScolaire)this;
	}
	protected MereScolaire anneeClesInit() {
		if(!anneeClesCouverture.dejaInitialise) {
			_anneeCles(anneeCles);
		}
		anneeClesCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Long staticSolrAnneeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAnneeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeCles(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrAnneeCles(requeteSite_, MereScolaire.staticSolrAnneeCles(requeteSite_, MereScolaire.staticSetAnneeCles(requeteSite_, o)));
	}

	public List<Long> solrAnneeCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : anneeCles) {
			l.add(MereScolaire.staticSolrAnneeCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCles">Trouver l'entité saisonCles dans Solr</a>
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
		Long l = MereScolaire.staticSetSaisonCles(requeteSite_, o);
		if(l != null)
			addSaisonCles(l);
		this.saisonClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetSaisonCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public MereScolaire addSaisonCles(Long...objets) {
		for(Long o : objets) {
			addSaisonCles(o);
		}
		return (MereScolaire)this;
	}
	public MereScolaire addSaisonCles(Long o) {
		if(o != null && !saisonCles.contains(o))
			this.saisonCles.add(o);
		return (MereScolaire)this;
	}
	public void setSaisonCles(JsonArray objets) {
		saisonCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSaisonCles(o);
		}
	}
	public MereScolaire addSaisonCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSaisonCles(p);
		}
		return (MereScolaire)this;
	}
	protected MereScolaire saisonClesInit() {
		if(!saisonClesCouverture.dejaInitialise) {
			_saisonCles(saisonCles);
		}
		saisonClesCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Long staticSolrSaisonCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSaisonCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonCles(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrSaisonCles(requeteSite_, MereScolaire.staticSolrSaisonCles(requeteSite_, MereScolaire.staticSetSaisonCles(requeteSite_, o)));
	}

	public List<Long> solrSaisonCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : saisonCles) {
			l.add(MereScolaire.staticSolrSaisonCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCles">Trouver l'entité sessionCles dans Solr</a>
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
		Long l = MereScolaire.staticSetSessionCles(requeteSite_, o);
		if(l != null)
			addSessionCles(l);
		this.sessionClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetSessionCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public MereScolaire addSessionCles(Long...objets) {
		for(Long o : objets) {
			addSessionCles(o);
		}
		return (MereScolaire)this;
	}
	public MereScolaire addSessionCles(Long o) {
		if(o != null && !sessionCles.contains(o))
			this.sessionCles.add(o);
		return (MereScolaire)this;
	}
	public void setSessionCles(JsonArray objets) {
		sessionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionCles(o);
		}
	}
	public MereScolaire addSessionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionCles(p);
		}
		return (MereScolaire)this;
	}
	protected MereScolaire sessionClesInit() {
		if(!sessionClesCouverture.dejaInitialise) {
			_sessionCles(sessionCles);
		}
		sessionClesCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Long staticSolrSessionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionCles(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrSessionCles(requeteSite_, MereScolaire.staticSolrSessionCles(requeteSite_, MereScolaire.staticSetSessionCles(requeteSite_, o)));
	}

	public List<Long> solrSessionCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : sessionCles) {
			l.add(MereScolaire.staticSolrSessionCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCles">Trouver l'entité ageCles dans Solr</a>
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
		Long l = MereScolaire.staticSetAgeCles(requeteSite_, o);
		if(l != null)
			addAgeCles(l);
		this.ageClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetAgeCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public MereScolaire addAgeCles(Long...objets) {
		for(Long o : objets) {
			addAgeCles(o);
		}
		return (MereScolaire)this;
	}
	public MereScolaire addAgeCles(Long o) {
		if(o != null && !ageCles.contains(o))
			this.ageCles.add(o);
		return (MereScolaire)this;
	}
	public void setAgeCles(JsonArray objets) {
		ageCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeCles(o);
		}
	}
	public MereScolaire addAgeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAgeCles(p);
		}
		return (MereScolaire)this;
	}
	protected MereScolaire ageClesInit() {
		if(!ageClesCouverture.dejaInitialise) {
			_ageCles(ageCles);
		}
		ageClesCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Long staticSolrAgeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeCles(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrAgeCles(requeteSite_, MereScolaire.staticSolrAgeCles(requeteSite_, MereScolaire.staticSetAgeCles(requeteSite_, o)));
	}

	public List<Long> solrAgeCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : ageCles) {
			l.add(MereScolaire.staticSolrAgeCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personnePrenom">Trouver l'entité personnePrenom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personnePrenom(Couverture<String> c);

	public String getPersonnePrenom() {
		return personnePrenom;
	}
	public void setPersonnePrenom(String o) {
		this.personnePrenom = MereScolaire.staticSetPersonnePrenom(requeteSite_, o);
		this.personnePrenomCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire personnePrenomInit() {
		if(!personnePrenomCouverture.dejaInitialise) {
			_personnePrenom(personnePrenomCouverture);
			if(personnePrenom == null)
				setPersonnePrenom(personnePrenomCouverture.o);
		}
		personnePrenomCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonnePrenom(requeteSite_, MereScolaire.staticSolrPersonnePrenom(requeteSite_, MereScolaire.staticSetPersonnePrenom(requeteSite_, o)));
	}

	public String solrPersonnePrenom() {
		return MereScolaire.staticSolrPersonnePrenom(requeteSite_, personnePrenom);
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
		MereScolaire s = (MereScolaire)this;
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
					a("class", "setPersonnePrenom classMereScolaire inputMereScolaire", pk, "PersonnePrenom w3-input w3-border ");
					a("name", "setPersonnePrenom");
				} else {
					a("class", "valeurPersonnePrenom w3-input w3-border classMereScolaire inputMereScolaire", pk, "PersonnePrenom w3-input w3-border ");
					a("name", "personnePrenom");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonnePrenom', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenom')); }); ");
				}
				a("value", strPersonnePrenom())
			.fg();

		} else {
			e("span").a("class", "varMereScolaire", pk, "PersonnePrenom ").f().sx(htmPersonnePrenom()).g("span");
		}
	}

	public void htmPersonnePrenom(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolairePersonnePrenom").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personnePrenom')); $('#", classeApiMethodeMethode, "_personnePrenom').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#MereScolaireForm :input[name=pk]').val() }], 'setPersonnePrenom', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenom')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personnePrenomPrefere">Trouver l'entité personnePrenomPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personnePrenomPrefere(Couverture<String> c);

	public String getPersonnePrenomPrefere() {
		return personnePrenomPrefere;
	}
	public void setPersonnePrenomPrefere(String o) {
		this.personnePrenomPrefere = MereScolaire.staticSetPersonnePrenomPrefere(requeteSite_, o);
		this.personnePrenomPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire personnePrenomPrefereInit() {
		if(!personnePrenomPrefereCouverture.dejaInitialise) {
			_personnePrenomPrefere(personnePrenomPrefereCouverture);
			if(personnePrenomPrefere == null)
				setPersonnePrenomPrefere(personnePrenomPrefereCouverture.o);
		}
		personnePrenomPrefereCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonnePrenomPrefere(requeteSite_, MereScolaire.staticSolrPersonnePrenomPrefere(requeteSite_, MereScolaire.staticSetPersonnePrenomPrefere(requeteSite_, o)));
	}

	public String solrPersonnePrenomPrefere() {
		return MereScolaire.staticSolrPersonnePrenomPrefere(requeteSite_, personnePrenomPrefere);
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
		MereScolaire s = (MereScolaire)this;
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
					a("class", "setPersonnePrenomPrefere classMereScolaire inputMereScolaire", pk, "PersonnePrenomPrefere w3-input w3-border ");
					a("name", "setPersonnePrenomPrefere");
				} else {
					a("class", "valeurPersonnePrenomPrefere w3-input w3-border classMereScolaire inputMereScolaire", pk, "PersonnePrenomPrefere w3-input w3-border ");
					a("name", "personnePrenomPrefere");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonnePrenomPrefere', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }); ");
				}
				a("value", strPersonnePrenomPrefere())
			.fg();

		} else {
			e("span").a("class", "varMereScolaire", pk, "PersonnePrenomPrefere ").f().sx(htmPersonnePrenomPrefere()).g("span");
		}
	}

	public void htmPersonnePrenomPrefere(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolairePersonnePrenomPrefere").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); $('#", classeApiMethodeMethode, "_personnePrenomPrefere').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#MereScolaireForm :input[name=pk]').val() }], 'setPersonnePrenomPrefere', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleNom">Trouver l'entité familleNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familleNom(Couverture<String> c);

	public String getFamilleNom() {
		return familleNom;
	}
	public void setFamilleNom(String o) {
		this.familleNom = MereScolaire.staticSetFamilleNom(requeteSite_, o);
		this.familleNomCouverture.dejaInitialise = true;
	}
	public static String staticSetFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire familleNomInit() {
		if(!familleNomCouverture.dejaInitialise) {
			_familleNom(familleNomCouverture);
			if(familleNom == null)
				setFamilleNom(familleNomCouverture.o);
		}
		familleNomCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrFamilleNom(requeteSite_, MereScolaire.staticSolrFamilleNom(requeteSite_, MereScolaire.staticSetFamilleNom(requeteSite_, o)));
	}

	public String solrFamilleNom() {
		return MereScolaire.staticSolrFamilleNom(requeteSite_, familleNom);
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
		MereScolaire s = (MereScolaire)this;
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
					a("class", "setFamilleNom classMereScolaire inputMereScolaire", pk, "FamilleNom w3-input w3-border ");
					a("name", "setFamilleNom");
				} else {
					a("class", "valeurFamilleNom w3-input w3-border classMereScolaire inputMereScolaire", pk, "FamilleNom w3-input w3-border ");
					a("name", "familleNom");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleNom', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleNom')); }); ");
				}
				a("value", strFamilleNom())
			.fg();

		} else {
			e("span").a("class", "varMereScolaire", pk, "FamilleNom ").f().sx(htmFamilleNom()).g("span");
		}
	}

	public void htmFamilleNom(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolaireFamilleNom").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_familleNom')); $('#", classeApiMethodeMethode, "_familleNom').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#MereScolaireForm :input[name=pk]').val() }], 'setFamilleNom', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleNom')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomComplet">Trouver l'entité personneNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomComplet(Couverture<String> c);

	public String getPersonneNomComplet() {
		return personneNomComplet;
	}
	public void setPersonneNomComplet(String o) {
		this.personneNomComplet = MereScolaire.staticSetPersonneNomComplet(requeteSite_, o);
		this.personneNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire personneNomCompletInit() {
		if(!personneNomCompletCouverture.dejaInitialise) {
			_personneNomComplet(personneNomCompletCouverture);
			if(personneNomComplet == null)
				setPersonneNomComplet(personneNomCompletCouverture.o);
		}
		personneNomCompletCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonneNomComplet(requeteSite_, MereScolaire.staticSolrPersonneNomComplet(requeteSite_, MereScolaire.staticSetPersonneNomComplet(requeteSite_, o)));
	}

	public String solrPersonneNomComplet() {
		return MereScolaire.staticSolrPersonneNomComplet(requeteSite_, personneNomComplet);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomCompletPrefere">Trouver l'entité personneNomCompletPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomCompletPrefere(Couverture<String> c);

	public String getPersonneNomCompletPrefere() {
		return personneNomCompletPrefere;
	}
	public void setPersonneNomCompletPrefere(String o) {
		this.personneNomCompletPrefere = MereScolaire.staticSetPersonneNomCompletPrefere(requeteSite_, o);
		this.personneNomCompletPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire personneNomCompletPrefereInit() {
		if(!personneNomCompletPrefereCouverture.dejaInitialise) {
			_personneNomCompletPrefere(personneNomCompletPrefereCouverture);
			if(personneNomCompletPrefere == null)
				setPersonneNomCompletPrefere(personneNomCompletPrefereCouverture.o);
		}
		personneNomCompletPrefereCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonneNomCompletPrefere(requeteSite_, MereScolaire.staticSolrPersonneNomCompletPrefere(requeteSite_, MereScolaire.staticSetPersonneNomCompletPrefere(requeteSite_, o)));
	}

	public String solrPersonneNomCompletPrefere() {
		return MereScolaire.staticSolrPersonneNomCompletPrefere(requeteSite_, personneNomCompletPrefere);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomFormel">Trouver l'entité personneNomFormel dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomFormel(Couverture<String> c);

	public String getPersonneNomFormel() {
		return personneNomFormel;
	}
	public void setPersonneNomFormel(String o) {
		this.personneNomFormel = MereScolaire.staticSetPersonneNomFormel(requeteSite_, o);
		this.personneNomFormelCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire personneNomFormelInit() {
		if(!personneNomFormelCouverture.dejaInitialise) {
			_personneNomFormel(personneNomFormelCouverture);
			if(personneNomFormel == null)
				setPersonneNomFormel(personneNomFormelCouverture.o);
		}
		personneNomFormelCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonneNomFormel(requeteSite_, MereScolaire.staticSolrPersonneNomFormel(requeteSite_, MereScolaire.staticSetPersonneNomFormel(requeteSite_, o)));
	}

	public String solrPersonneNomFormel() {
		return MereScolaire.staticSolrPersonneNomFormel(requeteSite_, personneNomFormel);
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

	////////////////////////
	// personneOccupation //
	////////////////////////

	/**	 L'entité personneOccupation
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personneOccupation;
	@JsonIgnore
	public Couverture<String> personneOccupationCouverture = new Couverture<String>().p(this).c(String.class).var("personneOccupation").o(personneOccupation);

	/**	<br/> L'entité personneOccupation
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneOccupation">Trouver l'entité personneOccupation dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneOccupation(Couverture<String> c);

	public String getPersonneOccupation() {
		return personneOccupation;
	}
	public void setPersonneOccupation(String o) {
		this.personneOccupation = MereScolaire.staticSetPersonneOccupation(requeteSite_, o);
		this.personneOccupationCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneOccupation(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire personneOccupationInit() {
		if(!personneOccupationCouverture.dejaInitialise) {
			_personneOccupation(personneOccupationCouverture);
			if(personneOccupation == null)
				setPersonneOccupation(personneOccupationCouverture.o);
		}
		personneOccupationCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrPersonneOccupation(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneOccupation(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneOccupation(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonneOccupation(requeteSite_, MereScolaire.staticSolrPersonneOccupation(requeteSite_, MereScolaire.staticSetPersonneOccupation(requeteSite_, o)));
	}

	public String solrPersonneOccupation() {
		return MereScolaire.staticSolrPersonneOccupation(requeteSite_, personneOccupation);
	}

	public String strPersonneOccupation() {
		return personneOccupation == null ? "" : personneOccupation;
	}

	public String jsonPersonneOccupation() {
		return personneOccupation == null ? "" : personneOccupation;
	}

	public String nomAffichagePersonneOccupation() {
		return "occupation";
	}

	public String htmTooltipPersonneOccupation() {
		return null;
	}

	public String htmPersonneOccupation() {
		return personneOccupation == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneOccupation());
	}

	public void inputPersonneOccupation(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "occupation")
				.a("id", classeApiMethodeMethode, "_personneOccupation");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPersonneOccupation classMereScolaire inputMereScolaire", pk, "PersonneOccupation w3-input w3-border ");
					a("name", "setPersonneOccupation");
				} else {
					a("class", "valeurPersonneOccupation w3-input w3-border classMereScolaire inputMereScolaire", pk, "PersonneOccupation w3-input w3-border ");
					a("name", "personneOccupation");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonneOccupation', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneOccupation')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneOccupation')); }); ");
				}
				a("value", strPersonneOccupation())
			.fg();

		} else {
			e("span").a("class", "varMereScolaire", pk, "PersonneOccupation ").f().sx(htmPersonneOccupation()).g("span");
		}
	}

	public void htmPersonneOccupation(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolairePersonneOccupation").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_personneOccupation").a("class", "").f().sx("occupation").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonneOccupation(classeApiMethodeMethode);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personneOccupation')); $('#", classeApiMethodeMethode, "_personneOccupation').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#MereScolaireForm :input[name=pk]').val() }], 'setPersonneOccupation', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneOccupation')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneOccupation')); }); ")
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
	// personneNumeroTelephone //
	/////////////////////////////

	/**	 L'entité personneNumeroTelephone
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personneNumeroTelephone;
	@JsonIgnore
	public Couverture<String> personneNumeroTelephoneCouverture = new Couverture<String>().p(this).c(String.class).var("personneNumeroTelephone").o(personneNumeroTelephone);

	/**	<br/> L'entité personneNumeroTelephone
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNumeroTelephone">Trouver l'entité personneNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNumeroTelephone(Couverture<String> c);

	public String getPersonneNumeroTelephone() {
		return personneNumeroTelephone;
	}
	public void setPersonneNumeroTelephone(String o) {
		this.personneNumeroTelephone = MereScolaire.staticSetPersonneNumeroTelephone(requeteSite_, o);
		this.personneNumeroTelephoneCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire personneNumeroTelephoneInit() {
		if(!personneNumeroTelephoneCouverture.dejaInitialise) {
			_personneNumeroTelephone(personneNumeroTelephoneCouverture);
			if(personneNumeroTelephone == null)
				setPersonneNumeroTelephone(personneNumeroTelephoneCouverture.o);
		}
		personneNumeroTelephoneCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrPersonneNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonneNumeroTelephone(requeteSite_, MereScolaire.staticSolrPersonneNumeroTelephone(requeteSite_, MereScolaire.staticSetPersonneNumeroTelephone(requeteSite_, o)));
	}

	public String solrPersonneNumeroTelephone() {
		return MereScolaire.staticSolrPersonneNumeroTelephone(requeteSite_, personneNumeroTelephone);
	}

	public String strPersonneNumeroTelephone() {
		return personneNumeroTelephone == null ? "" : personneNumeroTelephone;
	}

	public String jsonPersonneNumeroTelephone() {
		return personneNumeroTelephone == null ? "" : personneNumeroTelephone;
	}

	public String nomAffichagePersonneNumeroTelephone() {
		return "numéro de téléphone";
	}

	public String htmTooltipPersonneNumeroTelephone() {
		return null;
	}

	public String htmPersonneNumeroTelephone() {
		return personneNumeroTelephone == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneNumeroTelephone());
	}

	public void inputPersonneNumeroTelephone(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "numéro de téléphone")
				.a("id", classeApiMethodeMethode, "_personneNumeroTelephone");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPersonneNumeroTelephone classMereScolaire inputMereScolaire", pk, "PersonneNumeroTelephone w3-input w3-border ");
					a("name", "setPersonneNumeroTelephone");
				} else {
					a("class", "valeurPersonneNumeroTelephone w3-input w3-border classMereScolaire inputMereScolaire", pk, "PersonneNumeroTelephone w3-input w3-border ");
					a("name", "personneNumeroTelephone");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonneNumeroTelephone', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneNumeroTelephone')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneNumeroTelephone')); }); ");
				}
				a("value", strPersonneNumeroTelephone())
			.fg();

		} else {
			e("span").a("class", "varMereScolaire", pk, "PersonneNumeroTelephone ").f().sx(htmPersonneNumeroTelephone()).g("span");
		}
	}

	public void htmPersonneNumeroTelephone(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolairePersonneNumeroTelephone").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_personneNumeroTelephone").a("class", "").f().sx("numéro de téléphone").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonneNumeroTelephone(classeApiMethodeMethode);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personneNumeroTelephone')); $('#", classeApiMethodeMethode, "_personneNumeroTelephone').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#MereScolaireForm :input[name=pk]').val() }], 'setPersonneNumeroTelephone', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneNumeroTelephone')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneNumeroTelephone')); }); ")
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
	// personneMail //
	//////////////////

	/**	 L'entité personneMail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personneMail;
	@JsonIgnore
	public Couverture<String> personneMailCouverture = new Couverture<String>().p(this).c(String.class).var("personneMail").o(personneMail);

	/**	<br/> L'entité personneMail
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneMail">Trouver l'entité personneMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneMail(Couverture<String> c);

	public String getPersonneMail() {
		return personneMail;
	}
	public void setPersonneMail(String o) {
		this.personneMail = MereScolaire.staticSetPersonneMail(requeteSite_, o);
		this.personneMailCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneMail(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire personneMailInit() {
		if(!personneMailCouverture.dejaInitialise) {
			_personneMail(personneMailCouverture);
			if(personneMail == null)
				setPersonneMail(personneMailCouverture.o);
		}
		personneMailCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrPersonneMail(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneMail(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneMail(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonneMail(requeteSite_, MereScolaire.staticSolrPersonneMail(requeteSite_, MereScolaire.staticSetPersonneMail(requeteSite_, o)));
	}

	public String solrPersonneMail() {
		return MereScolaire.staticSolrPersonneMail(requeteSite_, personneMail);
	}

	public String strPersonneMail() {
		return personneMail == null ? "" : personneMail;
	}

	public String jsonPersonneMail() {
		return personneMail == null ? "" : personneMail;
	}

	public String nomAffichagePersonneMail() {
		return "mail";
	}

	public String htmTooltipPersonneMail() {
		return null;
	}

	public String htmPersonneMail() {
		return personneMail == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneMail());
	}

	public void inputPersonneMail(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "mail")
				.a("id", classeApiMethodeMethode, "_personneMail");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPersonneMail classMereScolaire inputMereScolaire", pk, "PersonneMail w3-input w3-border ");
					a("name", "setPersonneMail");
				} else {
					a("class", "valeurPersonneMail w3-input w3-border classMereScolaire inputMereScolaire", pk, "PersonneMail w3-input w3-border ");
					a("name", "personneMail");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonneMail', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneMail')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneMail')); }); ");
				}
				a("value", strPersonneMail())
			.fg();

		} else {
			e("span").a("class", "varMereScolaire", pk, "PersonneMail ").f().sx(htmPersonneMail()).g("span");
		}
	}

	public void htmPersonneMail(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolairePersonneMail").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_personneMail").a("class", "").f().sx("mail").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonneMail(classeApiMethodeMethode);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personneMail')); $('#", classeApiMethodeMethode, "_personneMail').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#MereScolaireForm :input[name=pk]').val() }], 'setPersonneMail', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneMail')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneMail')); }); ")
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
	// personneRelation //
	//////////////////////

	/**	 L'entité personneRelation
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personneRelation;
	@JsonIgnore
	public Couverture<String> personneRelationCouverture = new Couverture<String>().p(this).c(String.class).var("personneRelation").o(personneRelation);

	/**	<br/> L'entité personneRelation
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneRelation">Trouver l'entité personneRelation dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneRelation(Couverture<String> c);

	public String getPersonneRelation() {
		return personneRelation;
	}
	public void setPersonneRelation(String o) {
		this.personneRelation = MereScolaire.staticSetPersonneRelation(requeteSite_, o);
		this.personneRelationCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneRelation(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire personneRelationInit() {
		if(!personneRelationCouverture.dejaInitialise) {
			_personneRelation(personneRelationCouverture);
			if(personneRelation == null)
				setPersonneRelation(personneRelationCouverture.o);
		}
		personneRelationCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrPersonneRelation(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneRelation(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneRelation(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonneRelation(requeteSite_, MereScolaire.staticSolrPersonneRelation(requeteSite_, MereScolaire.staticSetPersonneRelation(requeteSite_, o)));
	}

	public String solrPersonneRelation() {
		return MereScolaire.staticSolrPersonneRelation(requeteSite_, personneRelation);
	}

	public String strPersonneRelation() {
		return personneRelation == null ? "" : personneRelation;
	}

	public String jsonPersonneRelation() {
		return personneRelation == null ? "" : personneRelation;
	}

	public String nomAffichagePersonneRelation() {
		return "relation";
	}

	public String htmTooltipPersonneRelation() {
		return null;
	}

	public String htmPersonneRelation() {
		return personneRelation == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneRelation());
	}

	/////////////////
	// personneSms //
	/////////////////

	/**	 L'entité personneSms
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personneSms;
	@JsonIgnore
	public Couverture<Boolean> personneSmsCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("personneSms").o(personneSms);

	/**	<br/> L'entité personneSms
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneSms">Trouver l'entité personneSms dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneSms(Couverture<Boolean> c);

	public Boolean getPersonneSms() {
		return personneSms;
	}

	public void setPersonneSms(Boolean personneSms) {
		this.personneSms = personneSms;
		this.personneSmsCouverture.dejaInitialise = true;
	}
	public void setPersonneSms(String o) {
		this.personneSms = MereScolaire.staticSetPersonneSms(requeteSite_, o);
		this.personneSmsCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPersonneSms(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected MereScolaire personneSmsInit() {
		if(!personneSmsCouverture.dejaInitialise) {
			_personneSms(personneSmsCouverture);
			if(personneSms == null)
				setPersonneSms(personneSmsCouverture.o);
		}
		personneSmsCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Boolean staticSolrPersonneSms(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonneSms(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneSms(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonneSms(requeteSite_, MereScolaire.staticSolrPersonneSms(requeteSite_, MereScolaire.staticSetPersonneSms(requeteSite_, o)));
	}

	public Boolean solrPersonneSms() {
		return MereScolaire.staticSolrPersonneSms(requeteSite_, personneSms);
	}

	public String strPersonneSms() {
		return personneSms == null ? "" : personneSms.toString();
	}

	public String jsonPersonneSms() {
		return personneSms == null ? "" : personneSms.toString();
	}

	public String nomAffichagePersonneSms() {
		return "envoyer SMS";
	}

	public String htmTooltipPersonneSms() {
		return null;
	}

	public String htmPersonneSms() {
		return personneSms == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneSms());
	}

	public void inputPersonneSms(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_personneSms")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_personneSms");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPersonneSms classMereScolaire inputMereScolaire", pk, "PersonneSms w3-input w3-border ");
				a("name", "setPersonneSms");
			} else {
				a("class", "valeurPersonneSms classMereScolaire inputMereScolaire", pk, "PersonneSms w3-input w3-border ");
				a("name", "personneSms");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonneSms', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneSms')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneSms')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPersonneSms() != null && getPersonneSms())
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
			e("span").a("class", "varMereScolaire", pk, "PersonneSms ").f().sx(htmPersonneSms()).g("span");
		}
	}

	public void htmPersonneSms(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolairePersonneSms").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_personneSms").a("class", "").f().sx("envoyer SMS").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonneSms(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////////
	// personneRecevoirMail //
	//////////////////////////

	/**	 L'entité personneRecevoirMail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personneRecevoirMail;
	@JsonIgnore
	public Couverture<Boolean> personneRecevoirMailCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("personneRecevoirMail").o(personneRecevoirMail);

	/**	<br/> L'entité personneRecevoirMail
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneRecevoirMail">Trouver l'entité personneRecevoirMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneRecevoirMail(Couverture<Boolean> c);

	public Boolean getPersonneRecevoirMail() {
		return personneRecevoirMail;
	}

	public void setPersonneRecevoirMail(Boolean personneRecevoirMail) {
		this.personneRecevoirMail = personneRecevoirMail;
		this.personneRecevoirMailCouverture.dejaInitialise = true;
	}
	public void setPersonneRecevoirMail(String o) {
		this.personneRecevoirMail = MereScolaire.staticSetPersonneRecevoirMail(requeteSite_, o);
		this.personneRecevoirMailCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPersonneRecevoirMail(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected MereScolaire personneRecevoirMailInit() {
		if(!personneRecevoirMailCouverture.dejaInitialise) {
			_personneRecevoirMail(personneRecevoirMailCouverture);
			if(personneRecevoirMail == null)
				setPersonneRecevoirMail(personneRecevoirMailCouverture.o);
		}
		personneRecevoirMailCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Boolean staticSolrPersonneRecevoirMail(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonneRecevoirMail(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneRecevoirMail(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonneRecevoirMail(requeteSite_, MereScolaire.staticSolrPersonneRecevoirMail(requeteSite_, MereScolaire.staticSetPersonneRecevoirMail(requeteSite_, o)));
	}

	public Boolean solrPersonneRecevoirMail() {
		return MereScolaire.staticSolrPersonneRecevoirMail(requeteSite_, personneRecevoirMail);
	}

	public String strPersonneRecevoirMail() {
		return personneRecevoirMail == null ? "" : personneRecevoirMail.toString();
	}

	public String jsonPersonneRecevoirMail() {
		return personneRecevoirMail == null ? "" : personneRecevoirMail.toString();
	}

	public String nomAffichagePersonneRecevoirMail() {
		return "recevoir des mails";
	}

	public String htmTooltipPersonneRecevoirMail() {
		return null;
	}

	public String htmPersonneRecevoirMail() {
		return personneRecevoirMail == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneRecevoirMail());
	}

	public void inputPersonneRecevoirMail(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_personneRecevoirMail")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_personneRecevoirMail");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPersonneRecevoirMail classMereScolaire inputMereScolaire", pk, "PersonneRecevoirMail w3-input w3-border ");
				a("name", "setPersonneRecevoirMail");
			} else {
				a("class", "valeurPersonneRecevoirMail classMereScolaire inputMereScolaire", pk, "PersonneRecevoirMail w3-input w3-border ");
				a("name", "personneRecevoirMail");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonneRecevoirMail', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneRecevoirMail')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneRecevoirMail')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPersonneRecevoirMail() != null && getPersonneRecevoirMail())
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
			e("span").a("class", "varMereScolaire", pk, "PersonneRecevoirMail ").f().sx(htmPersonneRecevoirMail()).g("span");
		}
	}

	public void htmPersonneRecevoirMail(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolairePersonneRecevoirMail").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_personneRecevoirMail").a("class", "").f().sx("recevoir des mails").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonneRecevoirMail(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////////
	// personneContactUrgence //
	////////////////////////////

	/**	 L'entité personneContactUrgence
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personneContactUrgence;
	@JsonIgnore
	public Couverture<Boolean> personneContactUrgenceCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("personneContactUrgence").o(personneContactUrgence);

	/**	<br/> L'entité personneContactUrgence
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneContactUrgence">Trouver l'entité personneContactUrgence dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneContactUrgence(Couverture<Boolean> c);

	public Boolean getPersonneContactUrgence() {
		return personneContactUrgence;
	}

	public void setPersonneContactUrgence(Boolean personneContactUrgence) {
		this.personneContactUrgence = personneContactUrgence;
		this.personneContactUrgenceCouverture.dejaInitialise = true;
	}
	public void setPersonneContactUrgence(String o) {
		this.personneContactUrgence = MereScolaire.staticSetPersonneContactUrgence(requeteSite_, o);
		this.personneContactUrgenceCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPersonneContactUrgence(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected MereScolaire personneContactUrgenceInit() {
		if(!personneContactUrgenceCouverture.dejaInitialise) {
			_personneContactUrgence(personneContactUrgenceCouverture);
			if(personneContactUrgence == null)
				setPersonneContactUrgence(personneContactUrgenceCouverture.o);
		}
		personneContactUrgenceCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Boolean staticSolrPersonneContactUrgence(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonneContactUrgence(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneContactUrgence(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonneContactUrgence(requeteSite_, MereScolaire.staticSolrPersonneContactUrgence(requeteSite_, MereScolaire.staticSetPersonneContactUrgence(requeteSite_, o)));
	}

	public Boolean solrPersonneContactUrgence() {
		return MereScolaire.staticSolrPersonneContactUrgence(requeteSite_, personneContactUrgence);
	}

	public String strPersonneContactUrgence() {
		return personneContactUrgence == null ? "" : personneContactUrgence.toString();
	}

	public String jsonPersonneContactUrgence() {
		return personneContactUrgence == null ? "" : personneContactUrgence.toString();
	}

	public String nomAffichagePersonneContactUrgence() {
		return "contacter en cas d'urgence";
	}

	public String htmTooltipPersonneContactUrgence() {
		return null;
	}

	public String htmPersonneContactUrgence() {
		return personneContactUrgence == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneContactUrgence());
	}

	public void inputPersonneContactUrgence(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_personneContactUrgence")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_personneContactUrgence");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPersonneContactUrgence classMereScolaire inputMereScolaire", pk, "PersonneContactUrgence w3-input w3-border ");
				a("name", "setPersonneContactUrgence");
			} else {
				a("class", "valeurPersonneContactUrgence classMereScolaire inputMereScolaire", pk, "PersonneContactUrgence w3-input w3-border ");
				a("name", "personneContactUrgence");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonneContactUrgence', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneContactUrgence')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneContactUrgence')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPersonneContactUrgence() != null && getPersonneContactUrgence())
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
			e("span").a("class", "varMereScolaire", pk, "PersonneContactUrgence ").f().sx(htmPersonneContactUrgence()).g("span");
		}
	}

	public void htmPersonneContactUrgence(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolairePersonneContactUrgence").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_personneContactUrgence").a("class", "").f().sx("contacter en cas d'urgence").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonneContactUrgence(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// personneChercher //
	//////////////////////

	/**	 L'entité personneChercher
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personneChercher;
	@JsonIgnore
	public Couverture<Boolean> personneChercherCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("personneChercher").o(personneChercher);

	/**	<br/> L'entité personneChercher
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneChercher">Trouver l'entité personneChercher dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneChercher(Couverture<Boolean> c);

	public Boolean getPersonneChercher() {
		return personneChercher;
	}

	public void setPersonneChercher(Boolean personneChercher) {
		this.personneChercher = personneChercher;
		this.personneChercherCouverture.dejaInitialise = true;
	}
	public void setPersonneChercher(String o) {
		this.personneChercher = MereScolaire.staticSetPersonneChercher(requeteSite_, o);
		this.personneChercherCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPersonneChercher(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected MereScolaire personneChercherInit() {
		if(!personneChercherCouverture.dejaInitialise) {
			_personneChercher(personneChercherCouverture);
			if(personneChercher == null)
				setPersonneChercher(personneChercherCouverture.o);
		}
		personneChercherCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static Boolean staticSolrPersonneChercher(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonneChercher(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneChercher(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPersonneChercher(requeteSite_, MereScolaire.staticSolrPersonneChercher(requeteSite_, MereScolaire.staticSetPersonneChercher(requeteSite_, o)));
	}

	public Boolean solrPersonneChercher() {
		return MereScolaire.staticSolrPersonneChercher(requeteSite_, personneChercher);
	}

	public String strPersonneChercher() {
		return personneChercher == null ? "" : personneChercher.toString();
	}

	public String jsonPersonneChercher() {
		return personneChercher == null ? "" : personneChercher.toString();
	}

	public String nomAffichagePersonneChercher() {
		return "autorisé à venir chercher";
	}

	public String htmTooltipPersonneChercher() {
		return null;
	}

	public String htmPersonneChercher() {
		return personneChercher == null ? "" : StringEscapeUtils.escapeHtml4(strPersonneChercher());
	}

	public void inputPersonneChercher(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_personneChercher")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_personneChercher");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPersonneChercher classMereScolaire inputMereScolaire", pk, "PersonneChercher w3-input w3-border ");
				a("name", "setPersonneChercher");
			} else {
				a("class", "valeurPersonneChercher classMereScolaire inputMereScolaire", pk, "PersonneChercher w3-input w3-border ");
				a("name", "personneChercher");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonneChercher', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneChercher')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneChercher')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPersonneChercher() != null && getPersonneChercher())
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
			e("span").a("class", "varMereScolaire", pk, "PersonneChercher ").f().sx(htmPersonneChercher()).g("span");
		}
	}

	public void htmPersonneChercher(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolairePersonneChercher").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
							e("label").a("for", classeApiMethodeMethode, "_personneChercher").a("class", "").f().sx("autorisé à venir chercher").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonneChercher(classeApiMethodeMethode);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:photo">Trouver l'entité photo dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _photo(Couverture<String> c);

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String o) {
		this.photo = MereScolaire.staticSetPhoto(requeteSite_, o);
		this.photoCouverture.dejaInitialise = true;
	}
	public static String staticSetPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire photoInit() {
		if(!photoCouverture.dejaInitialise) {
			_photo(photoCouverture);
			if(photo == null)
				setPhoto(photoCouverture.o);
		}
		photoCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrPhoto(requeteSite_, MereScolaire.staticSolrPhoto(requeteSite_, MereScolaire.staticSetPhoto(requeteSite_, o)));
	}

	public String solrPhoto() {
		return MereScolaire.staticSolrPhoto(requeteSite_, photo);
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
		MereScolaire s = (MereScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "imageBase64Div1MereScolaire_photo").a("id", "imageBase64Div1MereScolaire", pk, "photo").f();
				e("h5").f().sx("Télécharger image").g("h5"); 
				e("form").a("method", "POST").a("enctype", "multipart/form-data").a("action", "/photo").a("class", "").f();
				e("input").a("type", "hidden").a("name", "pk").a("value", pk).fg(); 
				e("input").a("type", "hidden").a("name", "classeNomSimple").a("value", "MereScolaire").fg(); 
				e("input").a("name", "fichier").a("type", "file").a("onchange", "$.ajax({ type: 'POST', enctype: 'multipart/form-data', url: '/photo', data: new FormData(this.form), processData: false, contentType: false}); ").fg(); 
				g("form");
				e("img").a("id", "imageBase64ImgMereScolaire", pk, "photo");
					a("class", "imgMereScolaire", pk, "Photo w3-image ");
					a("src", StringUtils.isBlank(photo) ? "data:image/png;base64," : photo).a("alt", "");
				fg();
			g("div");
		} else {
			e("span").a("class", "varMereScolaire", pk, "Photo ").f().sx(htmPhoto()).g("span");
		}
	}

	public void htmPhoto(String classeApiMethodeMethode) {
		MereScolaire s = (MereScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "MereScolairePhoto").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-pink ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-pink ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_photo')); $('#", classeApiMethodeMethode, "_photo').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#MereScolaireForm :input[name=pk]').val() }], 'setPhoto', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_photo')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_photo')); }); ")
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
	// mereNomComplet //
	////////////////////

	/**	 L'entité mereNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mereNomComplet;
	@JsonIgnore
	public Couverture<String> mereNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("mereNomComplet").o(mereNomComplet);

	/**	<br/> L'entité mereNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.mere.MereScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mereNomComplet">Trouver l'entité mereNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mereNomComplet(Couverture<String> c);

	public String getMereNomComplet() {
		return mereNomComplet;
	}
	public void setMereNomComplet(String o) {
		this.mereNomComplet = MereScolaire.staticSetMereNomComplet(requeteSite_, o);
		this.mereNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetMereNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected MereScolaire mereNomCompletInit() {
		if(!mereNomCompletCouverture.dejaInitialise) {
			_mereNomComplet(mereNomCompletCouverture);
			if(mereNomComplet == null)
				setMereNomComplet(mereNomCompletCouverture.o);
		}
		mereNomCompletCouverture.dejaInitialise(true);
		return (MereScolaire)this;
	}

	public static String staticSolrMereNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrMereNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqMereNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return MereScolaire.staticSolrStrMereNomComplet(requeteSite_, MereScolaire.staticSolrMereNomComplet(requeteSite_, MereScolaire.staticSetMereNomComplet(requeteSite_, o)));
	}

	public String solrMereNomComplet() {
		return MereScolaire.staticSolrMereNomComplet(requeteSite_, mereNomComplet);
	}

	public String strMereNomComplet() {
		return mereNomComplet == null ? "" : mereNomComplet;
	}

	public String jsonMereNomComplet() {
		return mereNomComplet == null ? "" : mereNomComplet;
	}

	public String nomAffichageMereNomComplet() {
		return "nom";
	}

	public String htmTooltipMereNomComplet() {
		return null;
	}

	public String htmMereNomComplet() {
		return mereNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strMereNomComplet());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseMereScolaire = false;

	public MereScolaire initLoinMereScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseMereScolaire) {
			dejaInitialiseMereScolaire = true;
			initLoinMereScolaire();
		}
		return (MereScolaire)this;
	}

	public void initLoinMereScolaire() {
		initMereScolaire();
		super.initLoinCluster(requeteSite_);
	}

	public void initMereScolaire() {
		mereCleInit();
		inscriptionClesInit();
		familleTriInit();
		mereTriInit();
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
		personneOccupationInit();
		personneNumeroTelephoneInit();
		personneMailInit();
		personneRelationInit();
		personneSmsInit();
		personneRecevoirMailInit();
		personneContactUrgenceInit();
		personneChercherInit();
		photoInit();
		mereNomCompletInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinMereScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteMereScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(inscriptionRecherche != null)
			inscriptionRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteMereScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirMereScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirMereScolaire(String var) {
		MereScolaire oMereScolaire = (MereScolaire)this;
		switch(var) {
			case "mereCle":
				return oMereScolaire.mereCle;
			case "inscriptionCles":
				return oMereScolaire.inscriptionCles;
			case "familleTri":
				return oMereScolaire.familleTri;
			case "mereTri":
				return oMereScolaire.mereTri;
			case "inscriptionRecherche":
				return oMereScolaire.inscriptionRecherche;
			case "inscriptions":
				return oMereScolaire.inscriptions;
			case "utilisateurCles":
				return oMereScolaire.utilisateurCles;
			case "ecoleCles":
				return oMereScolaire.ecoleCles;
			case "anneeCles":
				return oMereScolaire.anneeCles;
			case "saisonCles":
				return oMereScolaire.saisonCles;
			case "sessionCles":
				return oMereScolaire.sessionCles;
			case "ageCles":
				return oMereScolaire.ageCles;
			case "personnePrenom":
				return oMereScolaire.personnePrenom;
			case "personnePrenomPrefere":
				return oMereScolaire.personnePrenomPrefere;
			case "familleNom":
				return oMereScolaire.familleNom;
			case "personneNomComplet":
				return oMereScolaire.personneNomComplet;
			case "personneNomCompletPrefere":
				return oMereScolaire.personneNomCompletPrefere;
			case "personneNomFormel":
				return oMereScolaire.personneNomFormel;
			case "personneOccupation":
				return oMereScolaire.personneOccupation;
			case "personneNumeroTelephone":
				return oMereScolaire.personneNumeroTelephone;
			case "personneMail":
				return oMereScolaire.personneMail;
			case "personneRelation":
				return oMereScolaire.personneRelation;
			case "personneSms":
				return oMereScolaire.personneSms;
			case "personneRecevoirMail":
				return oMereScolaire.personneRecevoirMail;
			case "personneContactUrgence":
				return oMereScolaire.personneContactUrgence;
			case "personneChercher":
				return oMereScolaire.personneChercher;
			case "photo":
				return oMereScolaire.photo;
			case "mereNomComplet":
				return oMereScolaire.mereNomComplet;
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
				o = attribuerMereScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerMereScolaire(String var, Object val) {
		MereScolaire oMereScolaire = (MereScolaire)this;
		switch(var) {
			case "inscriptionCles":
				oMereScolaire.addInscriptionCles((Long)val);
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
		return staticSetMereScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetMereScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "mereCle":
			return MereScolaire.staticSetMereCle(requeteSite_, o);
		case "inscriptionCles":
			return MereScolaire.staticSetInscriptionCles(requeteSite_, o);
		case "familleTri":
			return MereScolaire.staticSetFamilleTri(requeteSite_, o);
		case "mereTri":
			return MereScolaire.staticSetMereTri(requeteSite_, o);
		case "utilisateurCles":
			return MereScolaire.staticSetUtilisateurCles(requeteSite_, o);
		case "ecoleCles":
			return MereScolaire.staticSetEcoleCles(requeteSite_, o);
		case "anneeCles":
			return MereScolaire.staticSetAnneeCles(requeteSite_, o);
		case "saisonCles":
			return MereScolaire.staticSetSaisonCles(requeteSite_, o);
		case "sessionCles":
			return MereScolaire.staticSetSessionCles(requeteSite_, o);
		case "ageCles":
			return MereScolaire.staticSetAgeCles(requeteSite_, o);
		case "personnePrenom":
			return MereScolaire.staticSetPersonnePrenom(requeteSite_, o);
		case "personnePrenomPrefere":
			return MereScolaire.staticSetPersonnePrenomPrefere(requeteSite_, o);
		case "familleNom":
			return MereScolaire.staticSetFamilleNom(requeteSite_, o);
		case "personneNomComplet":
			return MereScolaire.staticSetPersonneNomComplet(requeteSite_, o);
		case "personneNomCompletPrefere":
			return MereScolaire.staticSetPersonneNomCompletPrefere(requeteSite_, o);
		case "personneNomFormel":
			return MereScolaire.staticSetPersonneNomFormel(requeteSite_, o);
		case "personneOccupation":
			return MereScolaire.staticSetPersonneOccupation(requeteSite_, o);
		case "personneNumeroTelephone":
			return MereScolaire.staticSetPersonneNumeroTelephone(requeteSite_, o);
		case "personneMail":
			return MereScolaire.staticSetPersonneMail(requeteSite_, o);
		case "personneRelation":
			return MereScolaire.staticSetPersonneRelation(requeteSite_, o);
		case "personneSms":
			return MereScolaire.staticSetPersonneSms(requeteSite_, o);
		case "personneRecevoirMail":
			return MereScolaire.staticSetPersonneRecevoirMail(requeteSite_, o);
		case "personneContactUrgence":
			return MereScolaire.staticSetPersonneContactUrgence(requeteSite_, o);
		case "personneChercher":
			return MereScolaire.staticSetPersonneChercher(requeteSite_, o);
		case "photo":
			return MereScolaire.staticSetPhoto(requeteSite_, o);
		case "mereNomComplet":
			return MereScolaire.staticSetMereNomComplet(requeteSite_, o);
			default:
				return Cluster.staticSetCluster(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrMereScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrMereScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "mereCle":
			return MereScolaire.staticSolrMereCle(requeteSite_, (Long)o);
		case "inscriptionCles":
			return MereScolaire.staticSolrInscriptionCles(requeteSite_, (Long)o);
		case "familleTri":
			return MereScolaire.staticSolrFamilleTri(requeteSite_, (Integer)o);
		case "mereTri":
			return MereScolaire.staticSolrMereTri(requeteSite_, (Integer)o);
		case "utilisateurCles":
			return MereScolaire.staticSolrUtilisateurCles(requeteSite_, (Long)o);
		case "ecoleCles":
			return MereScolaire.staticSolrEcoleCles(requeteSite_, (Long)o);
		case "anneeCles":
			return MereScolaire.staticSolrAnneeCles(requeteSite_, (Long)o);
		case "saisonCles":
			return MereScolaire.staticSolrSaisonCles(requeteSite_, (Long)o);
		case "sessionCles":
			return MereScolaire.staticSolrSessionCles(requeteSite_, (Long)o);
		case "ageCles":
			return MereScolaire.staticSolrAgeCles(requeteSite_, (Long)o);
		case "personnePrenom":
			return MereScolaire.staticSolrPersonnePrenom(requeteSite_, (String)o);
		case "personnePrenomPrefere":
			return MereScolaire.staticSolrPersonnePrenomPrefere(requeteSite_, (String)o);
		case "familleNom":
			return MereScolaire.staticSolrFamilleNom(requeteSite_, (String)o);
		case "personneNomComplet":
			return MereScolaire.staticSolrPersonneNomComplet(requeteSite_, (String)o);
		case "personneNomCompletPrefere":
			return MereScolaire.staticSolrPersonneNomCompletPrefere(requeteSite_, (String)o);
		case "personneNomFormel":
			return MereScolaire.staticSolrPersonneNomFormel(requeteSite_, (String)o);
		case "personneOccupation":
			return MereScolaire.staticSolrPersonneOccupation(requeteSite_, (String)o);
		case "personneNumeroTelephone":
			return MereScolaire.staticSolrPersonneNumeroTelephone(requeteSite_, (String)o);
		case "personneMail":
			return MereScolaire.staticSolrPersonneMail(requeteSite_, (String)o);
		case "personneRelation":
			return MereScolaire.staticSolrPersonneRelation(requeteSite_, (String)o);
		case "personneSms":
			return MereScolaire.staticSolrPersonneSms(requeteSite_, (Boolean)o);
		case "personneRecevoirMail":
			return MereScolaire.staticSolrPersonneRecevoirMail(requeteSite_, (Boolean)o);
		case "personneContactUrgence":
			return MereScolaire.staticSolrPersonneContactUrgence(requeteSite_, (Boolean)o);
		case "personneChercher":
			return MereScolaire.staticSolrPersonneChercher(requeteSite_, (Boolean)o);
		case "photo":
			return MereScolaire.staticSolrPhoto(requeteSite_, (String)o);
		case "mereNomComplet":
			return MereScolaire.staticSolrMereNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrCluster(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrMereScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrMereScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "mereCle":
			return MereScolaire.staticSolrStrMereCle(requeteSite_, (Long)o);
		case "inscriptionCles":
			return MereScolaire.staticSolrStrInscriptionCles(requeteSite_, (Long)o);
		case "familleTri":
			return MereScolaire.staticSolrStrFamilleTri(requeteSite_, (Integer)o);
		case "mereTri":
			return MereScolaire.staticSolrStrMereTri(requeteSite_, (Integer)o);
		case "utilisateurCles":
			return MereScolaire.staticSolrStrUtilisateurCles(requeteSite_, (Long)o);
		case "ecoleCles":
			return MereScolaire.staticSolrStrEcoleCles(requeteSite_, (Long)o);
		case "anneeCles":
			return MereScolaire.staticSolrStrAnneeCles(requeteSite_, (Long)o);
		case "saisonCles":
			return MereScolaire.staticSolrStrSaisonCles(requeteSite_, (Long)o);
		case "sessionCles":
			return MereScolaire.staticSolrStrSessionCles(requeteSite_, (Long)o);
		case "ageCles":
			return MereScolaire.staticSolrStrAgeCles(requeteSite_, (Long)o);
		case "personnePrenom":
			return MereScolaire.staticSolrStrPersonnePrenom(requeteSite_, (String)o);
		case "personnePrenomPrefere":
			return MereScolaire.staticSolrStrPersonnePrenomPrefere(requeteSite_, (String)o);
		case "familleNom":
			return MereScolaire.staticSolrStrFamilleNom(requeteSite_, (String)o);
		case "personneNomComplet":
			return MereScolaire.staticSolrStrPersonneNomComplet(requeteSite_, (String)o);
		case "personneNomCompletPrefere":
			return MereScolaire.staticSolrStrPersonneNomCompletPrefere(requeteSite_, (String)o);
		case "personneNomFormel":
			return MereScolaire.staticSolrStrPersonneNomFormel(requeteSite_, (String)o);
		case "personneOccupation":
			return MereScolaire.staticSolrStrPersonneOccupation(requeteSite_, (String)o);
		case "personneNumeroTelephone":
			return MereScolaire.staticSolrStrPersonneNumeroTelephone(requeteSite_, (String)o);
		case "personneMail":
			return MereScolaire.staticSolrStrPersonneMail(requeteSite_, (String)o);
		case "personneRelation":
			return MereScolaire.staticSolrStrPersonneRelation(requeteSite_, (String)o);
		case "personneSms":
			return MereScolaire.staticSolrStrPersonneSms(requeteSite_, (Boolean)o);
		case "personneRecevoirMail":
			return MereScolaire.staticSolrStrPersonneRecevoirMail(requeteSite_, (Boolean)o);
		case "personneContactUrgence":
			return MereScolaire.staticSolrStrPersonneContactUrgence(requeteSite_, (Boolean)o);
		case "personneChercher":
			return MereScolaire.staticSolrStrPersonneChercher(requeteSite_, (Boolean)o);
		case "photo":
			return MereScolaire.staticSolrStrPhoto(requeteSite_, (String)o);
		case "mereNomComplet":
			return MereScolaire.staticSolrStrMereNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqMereScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqMereScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "mereCle":
			return MereScolaire.staticSolrFqMereCle(requeteSite_, o);
		case "inscriptionCles":
			return MereScolaire.staticSolrFqInscriptionCles(requeteSite_, o);
		case "familleTri":
			return MereScolaire.staticSolrFqFamilleTri(requeteSite_, o);
		case "mereTri":
			return MereScolaire.staticSolrFqMereTri(requeteSite_, o);
		case "utilisateurCles":
			return MereScolaire.staticSolrFqUtilisateurCles(requeteSite_, o);
		case "ecoleCles":
			return MereScolaire.staticSolrFqEcoleCles(requeteSite_, o);
		case "anneeCles":
			return MereScolaire.staticSolrFqAnneeCles(requeteSite_, o);
		case "saisonCles":
			return MereScolaire.staticSolrFqSaisonCles(requeteSite_, o);
		case "sessionCles":
			return MereScolaire.staticSolrFqSessionCles(requeteSite_, o);
		case "ageCles":
			return MereScolaire.staticSolrFqAgeCles(requeteSite_, o);
		case "personnePrenom":
			return MereScolaire.staticSolrFqPersonnePrenom(requeteSite_, o);
		case "personnePrenomPrefere":
			return MereScolaire.staticSolrFqPersonnePrenomPrefere(requeteSite_, o);
		case "familleNom":
			return MereScolaire.staticSolrFqFamilleNom(requeteSite_, o);
		case "personneNomComplet":
			return MereScolaire.staticSolrFqPersonneNomComplet(requeteSite_, o);
		case "personneNomCompletPrefere":
			return MereScolaire.staticSolrFqPersonneNomCompletPrefere(requeteSite_, o);
		case "personneNomFormel":
			return MereScolaire.staticSolrFqPersonneNomFormel(requeteSite_, o);
		case "personneOccupation":
			return MereScolaire.staticSolrFqPersonneOccupation(requeteSite_, o);
		case "personneNumeroTelephone":
			return MereScolaire.staticSolrFqPersonneNumeroTelephone(requeteSite_, o);
		case "personneMail":
			return MereScolaire.staticSolrFqPersonneMail(requeteSite_, o);
		case "personneRelation":
			return MereScolaire.staticSolrFqPersonneRelation(requeteSite_, o);
		case "personneSms":
			return MereScolaire.staticSolrFqPersonneSms(requeteSite_, o);
		case "personneRecevoirMail":
			return MereScolaire.staticSolrFqPersonneRecevoirMail(requeteSite_, o);
		case "personneContactUrgence":
			return MereScolaire.staticSolrFqPersonneContactUrgence(requeteSite_, o);
		case "personneChercher":
			return MereScolaire.staticSolrFqPersonneChercher(requeteSite_, o);
		case "photo":
			return MereScolaire.staticSolrFqPhoto(requeteSite_, o);
		case "mereNomComplet":
			return MereScolaire.staticSolrFqMereNomComplet(requeteSite_, o);
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
					o = definirMereScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirMereScolaire(String var, String val) {
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
			case "personneOccupation":
				if(val != null)
					setPersonneOccupation(val);
				sauvegardes.add(var);
				return val;
			case "personneNumeroTelephone":
				if(val != null)
					setPersonneNumeroTelephone(val);
				sauvegardes.add(var);
				return val;
			case "personneMail":
				if(val != null)
					setPersonneMail(val);
				sauvegardes.add(var);
				return val;
			case "personneSms":
				if(val != null)
					setPersonneSms(val);
				sauvegardes.add(var);
				return val;
			case "personneRecevoirMail":
				if(val != null)
					setPersonneRecevoirMail(val);
				sauvegardes.add(var);
				return val;
			case "personneContactUrgence":
				if(val != null)
					setPersonneContactUrgence(val);
				sauvegardes.add(var);
				return val;
			case "personneChercher":
				if(val != null)
					setPersonneChercher(val);
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
		peuplerMereScolaire(solrDocument);
	}
	public void peuplerMereScolaire(SolrDocument solrDocument) {
		MereScolaire oMereScolaire = (MereScolaire)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("mereCle")) {
				Long mereCle = (Long)solrDocument.get("mereCle_stored_long");
				if(mereCle != null)
					oMereScolaire.setMereCle(mereCle);
			}

			List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
			if(inscriptionCles != null)
				oMereScolaire.inscriptionCles.addAll(inscriptionCles);

			if(sauvegardes.contains("familleTri")) {
				Integer familleTri = (Integer)solrDocument.get("familleTri_stored_int");
				if(familleTri != null)
					oMereScolaire.setFamilleTri(familleTri);
			}

			if(sauvegardes.contains("mereTri")) {
				Integer mereTri = (Integer)solrDocument.get("mereTri_stored_int");
				if(mereTri != null)
					oMereScolaire.setMereTri(mereTri);
			}

			if(sauvegardes.contains("utilisateurCles")) {
				List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
				if(utilisateurCles != null)
					oMereScolaire.utilisateurCles.addAll(utilisateurCles);
			}

			if(sauvegardes.contains("ecoleCles")) {
				List<Long> ecoleCles = (List<Long>)solrDocument.get("ecoleCles_stored_longs");
				if(ecoleCles != null)
					oMereScolaire.ecoleCles.addAll(ecoleCles);
			}

			if(sauvegardes.contains("anneeCles")) {
				List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
				if(anneeCles != null)
					oMereScolaire.anneeCles.addAll(anneeCles);
			}

			if(sauvegardes.contains("saisonCles")) {
				List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
				if(saisonCles != null)
					oMereScolaire.saisonCles.addAll(saisonCles);
			}

			if(sauvegardes.contains("sessionCles")) {
				List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
				if(sessionCles != null)
					oMereScolaire.sessionCles.addAll(sessionCles);
			}

			if(sauvegardes.contains("ageCles")) {
				List<Long> ageCles = (List<Long>)solrDocument.get("ageCles_stored_longs");
				if(ageCles != null)
					oMereScolaire.ageCles.addAll(ageCles);
			}

			if(sauvegardes.contains("personnePrenom")) {
				String personnePrenom = (String)solrDocument.get("personnePrenom_stored_string");
				if(personnePrenom != null)
					oMereScolaire.setPersonnePrenom(personnePrenom);
			}

			if(sauvegardes.contains("personnePrenomPrefere")) {
				String personnePrenomPrefere = (String)solrDocument.get("personnePrenomPrefere_stored_string");
				if(personnePrenomPrefere != null)
					oMereScolaire.setPersonnePrenomPrefere(personnePrenomPrefere);
			}

			if(sauvegardes.contains("familleNom")) {
				String familleNom = (String)solrDocument.get("familleNom_stored_string");
				if(familleNom != null)
					oMereScolaire.setFamilleNom(familleNom);
			}

			if(sauvegardes.contains("personneNomComplet")) {
				String personneNomComplet = (String)solrDocument.get("personneNomComplet_stored_string");
				if(personneNomComplet != null)
					oMereScolaire.setPersonneNomComplet(personneNomComplet);
			}

			if(sauvegardes.contains("personneNomCompletPrefere")) {
				String personneNomCompletPrefere = (String)solrDocument.get("personneNomCompletPrefere_stored_string");
				if(personneNomCompletPrefere != null)
					oMereScolaire.setPersonneNomCompletPrefere(personneNomCompletPrefere);
			}

			if(sauvegardes.contains("personneNomFormel")) {
				String personneNomFormel = (String)solrDocument.get("personneNomFormel_stored_string");
				if(personneNomFormel != null)
					oMereScolaire.setPersonneNomFormel(personneNomFormel);
			}

			if(sauvegardes.contains("personneOccupation")) {
				String personneOccupation = (String)solrDocument.get("personneOccupation_stored_string");
				if(personneOccupation != null)
					oMereScolaire.setPersonneOccupation(personneOccupation);
			}

			if(sauvegardes.contains("personneNumeroTelephone")) {
				String personneNumeroTelephone = (String)solrDocument.get("personneNumeroTelephone_stored_string");
				if(personneNumeroTelephone != null)
					oMereScolaire.setPersonneNumeroTelephone(personneNumeroTelephone);
			}

			if(sauvegardes.contains("personneMail")) {
				String personneMail = (String)solrDocument.get("personneMail_stored_string");
				if(personneMail != null)
					oMereScolaire.setPersonneMail(personneMail);
			}

			if(sauvegardes.contains("personneRelation")) {
				String personneRelation = (String)solrDocument.get("personneRelation_stored_string");
				if(personneRelation != null)
					oMereScolaire.setPersonneRelation(personneRelation);
			}

			if(sauvegardes.contains("personneSms")) {
				Boolean personneSms = (Boolean)solrDocument.get("personneSms_stored_boolean");
				if(personneSms != null)
					oMereScolaire.setPersonneSms(personneSms);
			}

			if(sauvegardes.contains("personneRecevoirMail")) {
				Boolean personneRecevoirMail = (Boolean)solrDocument.get("personneRecevoirMail_stored_boolean");
				if(personneRecevoirMail != null)
					oMereScolaire.setPersonneRecevoirMail(personneRecevoirMail);
			}

			if(sauvegardes.contains("personneContactUrgence")) {
				Boolean personneContactUrgence = (Boolean)solrDocument.get("personneContactUrgence_stored_boolean");
				if(personneContactUrgence != null)
					oMereScolaire.setPersonneContactUrgence(personneContactUrgence);
			}

			if(sauvegardes.contains("personneChercher")) {
				Boolean personneChercher = (Boolean)solrDocument.get("personneChercher_stored_boolean");
				if(personneChercher != null)
					oMereScolaire.setPersonneChercher(personneChercher);
			}

			if(sauvegardes.contains("photo")) {
				String photo = (String)solrDocument.get("photo_stored_string");
				if(photo != null)
					oMereScolaire.setPhoto(photo);
			}

			if(sauvegardes.contains("mereNomComplet")) {
				String mereNomComplet = (String)solrDocument.get("mereNomComplet_stored_string");
				if(mereNomComplet != null)
					oMereScolaire.setMereNomComplet(mereNomComplet);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.mere.MereScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			MereScolaire o = new MereScolaire();
			o.requeteSiteMereScolaire(requeteSite);
			o.initLoinMereScolaire(requeteSite);
			o.indexerMereScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerMereScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerMereScolaire(document);
	}

	public void indexerMereScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerMereScolaire(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerMereScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerMereScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerMereScolaire(SolrInputDocument document) {
		if(mereCle != null) {
			document.addField("mereCle_indexed_long", mereCle);
			document.addField("mereCle_stored_long", mereCle);
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
		if(mereTri != null) {
			document.addField("mereTri_indexed_int", mereTri);
			document.addField("mereTri_stored_int", mereTri);
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
		if(personneOccupation != null) {
			document.addField("personneOccupation_indexed_string", personneOccupation);
			document.addField("personneOccupation_stored_string", personneOccupation);
		}
		if(personneNumeroTelephone != null) {
			document.addField("personneNumeroTelephone_indexed_string", personneNumeroTelephone);
			document.addField("personneNumeroTelephone_stored_string", personneNumeroTelephone);
		}
		if(personneMail != null) {
			document.addField("personneMail_indexed_string", personneMail);
			document.addField("personneMail_stored_string", personneMail);
		}
		if(personneRelation != null) {
			document.addField("personneRelation_indexed_string", personneRelation);
			document.addField("personneRelation_stored_string", personneRelation);
		}
		if(personneSms != null) {
			document.addField("personneSms_indexed_boolean", personneSms);
			document.addField("personneSms_stored_boolean", personneSms);
		}
		if(personneRecevoirMail != null) {
			document.addField("personneRecevoirMail_indexed_boolean", personneRecevoirMail);
			document.addField("personneRecevoirMail_stored_boolean", personneRecevoirMail);
		}
		if(personneContactUrgence != null) {
			document.addField("personneContactUrgence_indexed_boolean", personneContactUrgence);
			document.addField("personneContactUrgence_stored_boolean", personneContactUrgence);
		}
		if(personneChercher != null) {
			document.addField("personneChercher_indexed_boolean", personneChercher);
			document.addField("personneChercher_stored_boolean", personneChercher);
		}
		if(photo != null) {
			document.addField("photo_stored_string", photo);
		}
		if(mereNomComplet != null) {
			document.addField("mereNomComplet_indexed_string", mereNomComplet);
			document.addField("mereNomComplet_stored_string", mereNomComplet);
		}
		super.indexerCluster(document);

	}

	public void desindexerMereScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinMereScolaire(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeMereScolaire(String entiteVar) {
		switch(entiteVar) {
			case "mereCle":
				return "mereCle_indexed_long";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "familleTri":
				return "familleTri_indexed_int";
			case "mereTri":
				return "mereTri_indexed_int";
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
			case "personneOccupation":
				return "personneOccupation_indexed_string";
			case "personneNumeroTelephone":
				return "personneNumeroTelephone_indexed_string";
			case "personneMail":
				return "personneMail_indexed_string";
			case "personneRelation":
				return "personneRelation_indexed_string";
			case "personneSms":
				return "personneSms_indexed_boolean";
			case "personneRecevoirMail":
				return "personneRecevoirMail_indexed_boolean";
			case "personneContactUrgence":
				return "personneContactUrgence_indexed_boolean";
			case "personneChercher":
				return "personneChercher_indexed_boolean";
			case "mereNomComplet":
				return "mereNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheMereScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereMereScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerMereScolaire(solrDocument);
	}
	public void stockerMereScolaire(SolrDocument solrDocument) {
		MereScolaire oMereScolaire = (MereScolaire)this;

		Long mereCle = (Long)solrDocument.get("mereCle_stored_long");
		if(mereCle != null)
			oMereScolaire.setMereCle(mereCle);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oMereScolaire.inscriptionCles.addAll(inscriptionCles);

		Integer familleTri = (Integer)solrDocument.get("familleTri_stored_int");
		if(familleTri != null)
			oMereScolaire.setFamilleTri(familleTri);

		Integer mereTri = (Integer)solrDocument.get("mereTri_stored_int");
		if(mereTri != null)
			oMereScolaire.setMereTri(mereTri);

		List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
		if(utilisateurCles != null)
			oMereScolaire.utilisateurCles.addAll(utilisateurCles);

		List<Long> ecoleCles = (List<Long>)solrDocument.get("ecoleCles_stored_longs");
		if(ecoleCles != null)
			oMereScolaire.ecoleCles.addAll(ecoleCles);

		List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
		if(anneeCles != null)
			oMereScolaire.anneeCles.addAll(anneeCles);

		List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
		if(saisonCles != null)
			oMereScolaire.saisonCles.addAll(saisonCles);

		List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
		if(sessionCles != null)
			oMereScolaire.sessionCles.addAll(sessionCles);

		List<Long> ageCles = (List<Long>)solrDocument.get("ageCles_stored_longs");
		if(ageCles != null)
			oMereScolaire.ageCles.addAll(ageCles);

		String personnePrenom = (String)solrDocument.get("personnePrenom_stored_string");
		if(personnePrenom != null)
			oMereScolaire.setPersonnePrenom(personnePrenom);

		String personnePrenomPrefere = (String)solrDocument.get("personnePrenomPrefere_stored_string");
		if(personnePrenomPrefere != null)
			oMereScolaire.setPersonnePrenomPrefere(personnePrenomPrefere);

		String familleNom = (String)solrDocument.get("familleNom_stored_string");
		if(familleNom != null)
			oMereScolaire.setFamilleNom(familleNom);

		String personneNomComplet = (String)solrDocument.get("personneNomComplet_stored_string");
		if(personneNomComplet != null)
			oMereScolaire.setPersonneNomComplet(personneNomComplet);

		String personneNomCompletPrefere = (String)solrDocument.get("personneNomCompletPrefere_stored_string");
		if(personneNomCompletPrefere != null)
			oMereScolaire.setPersonneNomCompletPrefere(personneNomCompletPrefere);

		String personneNomFormel = (String)solrDocument.get("personneNomFormel_stored_string");
		if(personneNomFormel != null)
			oMereScolaire.setPersonneNomFormel(personneNomFormel);

		String personneOccupation = (String)solrDocument.get("personneOccupation_stored_string");
		if(personneOccupation != null)
			oMereScolaire.setPersonneOccupation(personneOccupation);

		String personneNumeroTelephone = (String)solrDocument.get("personneNumeroTelephone_stored_string");
		if(personneNumeroTelephone != null)
			oMereScolaire.setPersonneNumeroTelephone(personneNumeroTelephone);

		String personneMail = (String)solrDocument.get("personneMail_stored_string");
		if(personneMail != null)
			oMereScolaire.setPersonneMail(personneMail);

		String personneRelation = (String)solrDocument.get("personneRelation_stored_string");
		if(personneRelation != null)
			oMereScolaire.setPersonneRelation(personneRelation);

		Boolean personneSms = (Boolean)solrDocument.get("personneSms_stored_boolean");
		if(personneSms != null)
			oMereScolaire.setPersonneSms(personneSms);

		Boolean personneRecevoirMail = (Boolean)solrDocument.get("personneRecevoirMail_stored_boolean");
		if(personneRecevoirMail != null)
			oMereScolaire.setPersonneRecevoirMail(personneRecevoirMail);

		Boolean personneContactUrgence = (Boolean)solrDocument.get("personneContactUrgence_stored_boolean");
		if(personneContactUrgence != null)
			oMereScolaire.setPersonneContactUrgence(personneContactUrgence);

		Boolean personneChercher = (Boolean)solrDocument.get("personneChercher_stored_boolean");
		if(personneChercher != null)
			oMereScolaire.setPersonneChercher(personneChercher);

		String photo = (String)solrDocument.get("photo_stored_string");
		if(photo != null)
			oMereScolaire.setPhoto(photo);

		String mereNomComplet = (String)solrDocument.get("mereNomComplet_stored_string");
		if(mereNomComplet != null)
			oMereScolaire.setMereNomComplet(mereNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiMereScolaire() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof MereScolaire) {
			MereScolaire original = (MereScolaire)o;
			if(!Objects.equals(mereCle, original.getMereCle()))
				requeteApi.addVars("mereCle");
			if(!Objects.equals(inscriptionCles, original.getInscriptionCles()))
				requeteApi.addVars("inscriptionCles");
			if(!Objects.equals(familleTri, original.getFamilleTri()))
				requeteApi.addVars("familleTri");
			if(!Objects.equals(mereTri, original.getMereTri()))
				requeteApi.addVars("mereTri");
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
			if(!Objects.equals(personneOccupation, original.getPersonneOccupation()))
				requeteApi.addVars("personneOccupation");
			if(!Objects.equals(personneNumeroTelephone, original.getPersonneNumeroTelephone()))
				requeteApi.addVars("personneNumeroTelephone");
			if(!Objects.equals(personneMail, original.getPersonneMail()))
				requeteApi.addVars("personneMail");
			if(!Objects.equals(personneRelation, original.getPersonneRelation()))
				requeteApi.addVars("personneRelation");
			if(!Objects.equals(personneSms, original.getPersonneSms()))
				requeteApi.addVars("personneSms");
			if(!Objects.equals(personneRecevoirMail, original.getPersonneRecevoirMail()))
				requeteApi.addVars("personneRecevoirMail");
			if(!Objects.equals(personneContactUrgence, original.getPersonneContactUrgence()))
				requeteApi.addVars("personneContactUrgence");
			if(!Objects.equals(personneChercher, original.getPersonneChercher()))
				requeteApi.addVars("personneChercher");
			if(!Objects.equals(photo, original.getPhoto()))
				requeteApi.addVars("photo");
			if(!Objects.equals(mereNomComplet, original.getMereNomComplet()))
				requeteApi.addVars("mereNomComplet");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), mereCle, inscriptionCles, familleTri, mereTri, utilisateurCles, ecoleCles, anneeCles, saisonCles, sessionCles, ageCles, personnePrenom, personnePrenomPrefere, familleNom, personneNomComplet, personneNomCompletPrefere, personneNomFormel, personneOccupation, personneNumeroTelephone, personneMail, personneRelation, personneSms, personneRecevoirMail, personneContactUrgence, personneChercher, photo, mereNomComplet);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof MereScolaire))
			return false;
		MereScolaire that = (MereScolaire)o;
		return super.equals(o)
				&& Objects.equals( mereCle, that.mereCle )
				&& Objects.equals( inscriptionCles, that.inscriptionCles )
				&& Objects.equals( familleTri, that.familleTri )
				&& Objects.equals( mereTri, that.mereTri )
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
				&& Objects.equals( personneOccupation, that.personneOccupation )
				&& Objects.equals( personneNumeroTelephone, that.personneNumeroTelephone )
				&& Objects.equals( personneMail, that.personneMail )
				&& Objects.equals( personneRelation, that.personneRelation )
				&& Objects.equals( personneSms, that.personneSms )
				&& Objects.equals( personneRecevoirMail, that.personneRecevoirMail )
				&& Objects.equals( personneContactUrgence, that.personneContactUrgence )
				&& Objects.equals( personneChercher, that.personneChercher )
				&& Objects.equals( photo, that.photo )
				&& Objects.equals( mereNomComplet, that.mereNomComplet );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("MereScolaire { ");
		sb.append( "mereCle: " ).append(mereCle);
		sb.append( ", inscriptionCles: " ).append(inscriptionCles);
		sb.append( ", familleTri: " ).append(familleTri);
		sb.append( ", mereTri: " ).append(mereTri);
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
		sb.append( ", personneOccupation: \"" ).append(personneOccupation).append( "\"" );
		sb.append( ", personneNumeroTelephone: \"" ).append(personneNumeroTelephone).append( "\"" );
		sb.append( ", personneMail: \"" ).append(personneMail).append( "\"" );
		sb.append( ", personneRelation: \"" ).append(personneRelation).append( "\"" );
		sb.append( ", personneSms: " ).append(personneSms);
		sb.append( ", personneRecevoirMail: " ).append(personneRecevoirMail);
		sb.append( ", personneContactUrgence: " ).append(personneContactUrgence);
		sb.append( ", personneChercher: " ).append(personneChercher);
		sb.append( ", photo: \"" ).append(photo).append( "\"" );
		sb.append( ", mereNomComplet: \"" ).append(mereNomComplet).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
