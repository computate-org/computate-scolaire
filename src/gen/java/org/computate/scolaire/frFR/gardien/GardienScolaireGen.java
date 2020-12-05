package org.computate.scolaire.frFR.gardien;

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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true">Trouver la classe guardianCompleteName dans Solr. </a>
 * <br/>
 **/
public abstract class GardienScolaireGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(GardienScolaire.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String GardienScolaire_UnNom = "un gardien";
	public static final String GardienScolaire_Ce = "ce ";
	public static final String GardienScolaire_CeNom = "ce gardien";
	public static final String GardienScolaire_Un = "un ";
	public static final String GardienScolaire_LeNom = "le gardien";
	public static final String GardienScolaire_NomSingulier = "gardien";
	public static final String GardienScolaire_NomPluriel = "gardiens";
	public static final String GardienScolaire_NomActuel = "gardien actuel";
	public static final String GardienScolaire_Tous = "all ";
	public static final String GardienScolaire_TousNom = "tous les gardiens";
	public static final String GardienScolaire_RechercherTousNomPar = "rechercher gardiens par ";
	public static final String GardienScolaire_RechercherTousNom = "rechercher gardiens";
	public static final String GardienScolaire_Titre = "gardiens";
	public static final String GardienScolaire_LesNom = "les gardiens";
	public static final String GardienScolaire_AucunNomTrouve = "aucun gardien trouvé";
	public static final String GardienScolaire_NomVar = "gardien";
	public static final String GardienScolaire_DeNom = "de gardien";
	public static final String GardienScolaire_NomAdjectifSingulier = "gardien";
	public static final String GardienScolaire_NomAdjectifPluriel = "gardiens";
	public static final String GardienScolaire_Couleur = "yellow";
	public static final String GardienScolaire_IconeGroupe = "regular";
	public static final String GardienScolaire_IconeNom = "phone";

	////////////////
	// gardienCle //
	////////////////

	/**	 L'entité gardienCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long gardienCle;
	@JsonIgnore
	public Couverture<Long> gardienCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("gardienCle").o(gardienCle);

	/**	<br/> L'entité gardienCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienCle">Trouver l'entité gardienCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _gardienCle(Couverture<Long> c);

	public Long getGardienCle() {
		return gardienCle;
	}

	public void setGardienCle(Long gardienCle) {
		this.gardienCle = gardienCle;
		this.gardienCleCouverture.dejaInitialise = true;
	}
	public void setGardienCle(String o) {
		this.gardienCle = GardienScolaire.staticSetGardienCle(requeteSite_, o);
		this.gardienCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetGardienCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected GardienScolaire gardienCleInit() {
		if(!gardienCleCouverture.dejaInitialise) {
			_gardienCle(gardienCleCouverture);
			if(gardienCle == null)
				setGardienCle(gardienCleCouverture.o);
		}
		gardienCleCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Long staticSolrGardienCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrGardienCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqGardienCle(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrGardienCle(requeteSite_, GardienScolaire.staticSolrGardienCle(requeteSite_, GardienScolaire.staticSetGardienCle(requeteSite_, o)));
	}

	public Long solrGardienCle() {
		return GardienScolaire.staticSolrGardienCle(requeteSite_, gardienCle);
	}

	public String strGardienCle() {
		return gardienCle == null ? "" : gardienCle.toString();
	}

	public String jsonGardienCle() {
		return gardienCle == null ? "" : gardienCle.toString();
	}

	public String nomAffichageGardienCle() {
		return "clé";
	}

	public String htmTooltipGardienCle() {
		return null;
	}

	public String htmGardienCle() {
		return gardienCle == null ? "" : StringEscapeUtils.escapeHtml4(strGardienCle());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
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
		Long l = GardienScolaire.staticSetInscriptionCles(requeteSite_, o);
		if(l != null)
			addInscriptionCles(l);
		this.inscriptionClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetInscriptionCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public GardienScolaire addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (GardienScolaire)this;
	}
	public GardienScolaire addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (GardienScolaire)this;
	}
	public void setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
	}
	public GardienScolaire addInscriptionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (GardienScolaire)this;
	}
	protected GardienScolaire inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Long staticSolrInscriptionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrInscriptionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqInscriptionCles(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrInscriptionCles(requeteSite_, GardienScolaire.staticSolrInscriptionCles(requeteSite_, GardienScolaire.staticSetInscriptionCles(requeteSite_, o)));
	}

	public List<Long> solrInscriptionCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : inscriptionCles) {
			l.add(GardienScolaire.staticSolrInscriptionCles(requeteSite_, o));
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
		GardienScolaire s = (GardienScolaire)this;
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
				a("oninput", "suggereGardienScolaireInscriptionCles($(this).val() ? rechercherInscriptionScolaireFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'gardienCles:" + pk + "'}", "], $('#listGardienScolaireInscriptionCles_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varGardienScolaire", pk, "InscriptionCles ").f().sx(htmInscriptionCles()).g("span");
		}
	}

	public void htmInscriptionCles(String classeApiMethodeMethode) {
		GardienScolaire s = (GardienScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "GardienScolaireInscriptionCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/inscription?fq=gardienCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("inscriptions");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a ce gardien");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listGardienScolaireInscriptionCles_", classeApiMethodeMethode).f();
								} g("ul");
								{
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
												.a("id", classeApiMethodeMethode, "_inscriptionCles_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postInscriptionScolaireVals({ gardienCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "inscriptionCles')); });")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleTri">Trouver l'entité familleTri dans Solr</a>
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
		this.familleTri = GardienScolaire.staticSetFamilleTri(requeteSite_, o);
		this.familleTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetFamilleTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected GardienScolaire familleTriInit() {
		if(!familleTriCouverture.dejaInitialise) {
			_familleTri(familleTriCouverture);
			if(familleTri == null)
				setFamilleTri(familleTriCouverture.o);
		}
		familleTriCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Integer staticSolrFamilleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrFamilleTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilleTri(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrFamilleTri(requeteSite_, GardienScolaire.staticSolrFamilleTri(requeteSite_, GardienScolaire.staticSetFamilleTri(requeteSite_, o)));
	}

	public Integer solrFamilleTri() {
		return GardienScolaire.staticSolrFamilleTri(requeteSite_, familleTri);
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

	////////////////
	// gardienTri //
	////////////////

	/**	 L'entité gardienTri
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer gardienTri;
	@JsonIgnore
	public Couverture<Integer> gardienTriCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("gardienTri").o(gardienTri);

	/**	<br/> L'entité gardienTri
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienTri">Trouver l'entité gardienTri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _gardienTri(Couverture<Integer> c);

	public Integer getGardienTri() {
		return gardienTri;
	}

	public void setGardienTri(Integer gardienTri) {
		this.gardienTri = gardienTri;
		this.gardienTriCouverture.dejaInitialise = true;
	}
	public void setGardienTri(String o) {
		this.gardienTri = GardienScolaire.staticSetGardienTri(requeteSite_, o);
		this.gardienTriCouverture.dejaInitialise = true;
	}
	public static Integer staticSetGardienTri(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected GardienScolaire gardienTriInit() {
		if(!gardienTriCouverture.dejaInitialise) {
			_gardienTri(gardienTriCouverture);
			if(gardienTri == null)
				setGardienTri(gardienTriCouverture.o);
		}
		gardienTriCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Integer staticSolrGardienTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrGardienTri(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqGardienTri(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrGardienTri(requeteSite_, GardienScolaire.staticSolrGardienTri(requeteSite_, GardienScolaire.staticSetGardienTri(requeteSite_, o)));
	}

	public Integer solrGardienTri() {
		return GardienScolaire.staticSolrGardienTri(requeteSite_, gardienTri);
	}

	public String strGardienTri() {
		return gardienTri == null ? "" : gardienTri.toString();
	}

	public String jsonGardienTri() {
		return gardienTri == null ? "" : gardienTri.toString();
	}

	public String nomAffichageGardienTri() {
		return null;
	}

	public String htmTooltipGardienTri() {
		return null;
	}

	public String htmGardienTri() {
		return gardienTri == null ? "" : StringEscapeUtils.escapeHtml4(strGardienTri());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionRecherche">Trouver l'entité inscriptionRecherche dans Solr</a>
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
	protected GardienScolaire inscriptionRechercheInit() {
		if(!inscriptionRechercheCouverture.dejaInitialise) {
			_inscriptionRecherche(inscriptionRecherche);
		}
		inscriptionRecherche.initLoinPourClasse(requeteSite_);
		inscriptionRechercheCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptions">Trouver l'entité inscriptions dans Solr</a>
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
	public GardienScolaire addInscriptions(InscriptionScolaire...objets) {
		for(InscriptionScolaire o : objets) {
			addInscriptions(o);
		}
		return (GardienScolaire)this;
	}
	public GardienScolaire addInscriptions(InscriptionScolaire o) {
		if(o != null && !inscriptions.contains(o))
			this.inscriptions.add(o);
		return (GardienScolaire)this;
	}
	protected GardienScolaire inscriptionsInit() {
		if(!inscriptionsCouverture.dejaInitialise) {
			_inscriptions(inscriptions);
		}
		inscriptionsCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:utilisateurCles">Trouver l'entité utilisateurCles dans Solr</a>
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
		Long l = GardienScolaire.staticSetUtilisateurCles(requeteSite_, o);
		if(l != null)
			addUtilisateurCles(l);
		this.utilisateurClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetUtilisateurCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public GardienScolaire addUtilisateurCles(Long...objets) {
		for(Long o : objets) {
			addUtilisateurCles(o);
		}
		return (GardienScolaire)this;
	}
	public GardienScolaire addUtilisateurCles(Long o) {
		if(o != null && !utilisateurCles.contains(o))
			this.utilisateurCles.add(o);
		return (GardienScolaire)this;
	}
	public void setUtilisateurCles(JsonArray objets) {
		utilisateurCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUtilisateurCles(o);
		}
	}
	public GardienScolaire addUtilisateurCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUtilisateurCles(p);
		}
		return (GardienScolaire)this;
	}
	protected GardienScolaire utilisateurClesInit() {
		if(!utilisateurClesCouverture.dejaInitialise) {
			_utilisateurCles(utilisateurCles);
		}
		utilisateurClesCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Long staticSolrUtilisateurCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrUtilisateurCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUtilisateurCles(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrUtilisateurCles(requeteSite_, GardienScolaire.staticSolrUtilisateurCles(requeteSite_, GardienScolaire.staticSetUtilisateurCles(requeteSite_, o)));
	}

	public List<Long> solrUtilisateurCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : utilisateurCles) {
			l.add(GardienScolaire.staticSolrUtilisateurCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCles">Trouver l'entité ecoleCles dans Solr</a>
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
		Long l = GardienScolaire.staticSetEcoleCles(requeteSite_, o);
		if(l != null)
			addEcoleCles(l);
		this.ecoleClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetEcoleCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public GardienScolaire addEcoleCles(Long...objets) {
		for(Long o : objets) {
			addEcoleCles(o);
		}
		return (GardienScolaire)this;
	}
	public GardienScolaire addEcoleCles(Long o) {
		if(o != null && !ecoleCles.contains(o))
			this.ecoleCles.add(o);
		return (GardienScolaire)this;
	}
	public void setEcoleCles(JsonArray objets) {
		ecoleCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEcoleCles(o);
		}
	}
	public GardienScolaire addEcoleCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEcoleCles(p);
		}
		return (GardienScolaire)this;
	}
	protected GardienScolaire ecoleClesInit() {
		if(!ecoleClesCouverture.dejaInitialise) {
			_ecoleCles(ecoleCles);
		}
		ecoleClesCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Long staticSolrEcoleCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrEcoleCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEcoleCles(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrEcoleCles(requeteSite_, GardienScolaire.staticSolrEcoleCles(requeteSite_, GardienScolaire.staticSetEcoleCles(requeteSite_, o)));
	}

	public List<Long> solrEcoleCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : ecoleCles) {
			l.add(GardienScolaire.staticSolrEcoleCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCles">Trouver l'entité anneeCles dans Solr</a>
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
		Long l = GardienScolaire.staticSetAnneeCles(requeteSite_, o);
		if(l != null)
			addAnneeCles(l);
		this.anneeClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetAnneeCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public GardienScolaire addAnneeCles(Long...objets) {
		for(Long o : objets) {
			addAnneeCles(o);
		}
		return (GardienScolaire)this;
	}
	public GardienScolaire addAnneeCles(Long o) {
		if(o != null && !anneeCles.contains(o))
			this.anneeCles.add(o);
		return (GardienScolaire)this;
	}
	public void setAnneeCles(JsonArray objets) {
		anneeCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAnneeCles(o);
		}
	}
	public GardienScolaire addAnneeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAnneeCles(p);
		}
		return (GardienScolaire)this;
	}
	protected GardienScolaire anneeClesInit() {
		if(!anneeClesCouverture.dejaInitialise) {
			_anneeCles(anneeCles);
		}
		anneeClesCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Long staticSolrAnneeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAnneeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAnneeCles(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrAnneeCles(requeteSite_, GardienScolaire.staticSolrAnneeCles(requeteSite_, GardienScolaire.staticSetAnneeCles(requeteSite_, o)));
	}

	public List<Long> solrAnneeCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : anneeCles) {
			l.add(GardienScolaire.staticSolrAnneeCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCles">Trouver l'entité saisonCles dans Solr</a>
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
		Long l = GardienScolaire.staticSetSaisonCles(requeteSite_, o);
		if(l != null)
			addSaisonCles(l);
		this.saisonClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetSaisonCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public GardienScolaire addSaisonCles(Long...objets) {
		for(Long o : objets) {
			addSaisonCles(o);
		}
		return (GardienScolaire)this;
	}
	public GardienScolaire addSaisonCles(Long o) {
		if(o != null && !saisonCles.contains(o))
			this.saisonCles.add(o);
		return (GardienScolaire)this;
	}
	public void setSaisonCles(JsonArray objets) {
		saisonCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSaisonCles(o);
		}
	}
	public GardienScolaire addSaisonCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSaisonCles(p);
		}
		return (GardienScolaire)this;
	}
	protected GardienScolaire saisonClesInit() {
		if(!saisonClesCouverture.dejaInitialise) {
			_saisonCles(saisonCles);
		}
		saisonClesCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Long staticSolrSaisonCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSaisonCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSaisonCles(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrSaisonCles(requeteSite_, GardienScolaire.staticSolrSaisonCles(requeteSite_, GardienScolaire.staticSetSaisonCles(requeteSite_, o)));
	}

	public List<Long> solrSaisonCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : saisonCles) {
			l.add(GardienScolaire.staticSolrSaisonCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sessionCles">Trouver l'entité sessionCles dans Solr</a>
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
		Long l = GardienScolaire.staticSetSessionCles(requeteSite_, o);
		if(l != null)
			addSessionCles(l);
		this.sessionClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetSessionCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public GardienScolaire addSessionCles(Long...objets) {
		for(Long o : objets) {
			addSessionCles(o);
		}
		return (GardienScolaire)this;
	}
	public GardienScolaire addSessionCles(Long o) {
		if(o != null && !sessionCles.contains(o))
			this.sessionCles.add(o);
		return (GardienScolaire)this;
	}
	public void setSessionCles(JsonArray objets) {
		sessionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionCles(o);
		}
	}
	public GardienScolaire addSessionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionCles(p);
		}
		return (GardienScolaire)this;
	}
	protected GardienScolaire sessionClesInit() {
		if(!sessionClesCouverture.dejaInitialise) {
			_sessionCles(sessionCles);
		}
		sessionClesCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Long staticSolrSessionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionCles(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrSessionCles(requeteSite_, GardienScolaire.staticSolrSessionCles(requeteSite_, GardienScolaire.staticSetSessionCles(requeteSite_, o)));
	}

	public List<Long> solrSessionCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : sessionCles) {
			l.add(GardienScolaire.staticSolrSessionCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ageCles">Trouver l'entité ageCles dans Solr</a>
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
		Long l = GardienScolaire.staticSetAgeCles(requeteSite_, o);
		if(l != null)
			addAgeCles(l);
		this.ageClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetAgeCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public GardienScolaire addAgeCles(Long...objets) {
		for(Long o : objets) {
			addAgeCles(o);
		}
		return (GardienScolaire)this;
	}
	public GardienScolaire addAgeCles(Long o) {
		if(o != null && !ageCles.contains(o))
			this.ageCles.add(o);
		return (GardienScolaire)this;
	}
	public void setAgeCles(JsonArray objets) {
		ageCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeCles(o);
		}
	}
	public GardienScolaire addAgeCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAgeCles(p);
		}
		return (GardienScolaire)this;
	}
	protected GardienScolaire ageClesInit() {
		if(!ageClesCouverture.dejaInitialise) {
			_ageCles(ageCles);
		}
		ageClesCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Long staticSolrAgeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeCles(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrAgeCles(requeteSite_, GardienScolaire.staticSolrAgeCles(requeteSite_, GardienScolaire.staticSetAgeCles(requeteSite_, o)));
	}

	public List<Long> solrAgeCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : ageCles) {
			l.add(GardienScolaire.staticSolrAgeCles(requeteSite_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personnePrenom">Trouver l'entité personnePrenom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personnePrenom(Couverture<String> c);

	public String getPersonnePrenom() {
		return personnePrenom;
	}
	public void setPersonnePrenom(String o) {
		this.personnePrenom = GardienScolaire.staticSetPersonnePrenom(requeteSite_, o);
		this.personnePrenomCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire personnePrenomInit() {
		if(!personnePrenomCouverture.dejaInitialise) {
			_personnePrenom(personnePrenomCouverture);
			if(personnePrenom == null)
				setPersonnePrenom(personnePrenomCouverture.o);
		}
		personnePrenomCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonnePrenom(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonnePrenom(requeteSite_, GardienScolaire.staticSolrPersonnePrenom(requeteSite_, GardienScolaire.staticSetPersonnePrenom(requeteSite_, o)));
	}

	public String solrPersonnePrenom() {
		return GardienScolaire.staticSolrPersonnePrenom(requeteSite_, personnePrenom);
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
		GardienScolaire s = (GardienScolaire)this;
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
					a("class", "setPersonnePrenom classGardienScolaire inputGardienScolaire", pk, "PersonnePrenom w3-input w3-border ");
					a("name", "setPersonnePrenom");
				} else {
					a("class", "valeurPersonnePrenom w3-input w3-border classGardienScolaire inputGardienScolaire", pk, "PersonnePrenom w3-input w3-border ");
					a("name", "personnePrenom");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonnePrenom', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenom')); }); ");
				}
				a("value", strPersonnePrenom())
			.fg();

		} else {
			e("span").a("class", "varGardienScolaire", pk, "PersonnePrenom ").f().sx(htmPersonnePrenom()).g("span");
		}
	}

	public void htmPersonnePrenom(String classeApiMethodeMethode) {
		GardienScolaire s = (GardienScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "GardienScolairePersonnePrenom").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personnePrenom')); $('#", classeApiMethodeMethode, "_personnePrenom').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#GardienScolaireForm :input[name=pk]').val() }], 'setPersonnePrenom', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenom')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personnePrenomPrefere">Trouver l'entité personnePrenomPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personnePrenomPrefere(Couverture<String> c);

	public String getPersonnePrenomPrefere() {
		return personnePrenomPrefere;
	}
	public void setPersonnePrenomPrefere(String o) {
		this.personnePrenomPrefere = GardienScolaire.staticSetPersonnePrenomPrefere(requeteSite_, o);
		this.personnePrenomPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire personnePrenomPrefereInit() {
		if(!personnePrenomPrefereCouverture.dejaInitialise) {
			_personnePrenomPrefere(personnePrenomPrefereCouverture);
			if(personnePrenomPrefere == null)
				setPersonnePrenomPrefere(personnePrenomPrefereCouverture.o);
		}
		personnePrenomPrefereCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonnePrenomPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonnePrenomPrefere(requeteSite_, GardienScolaire.staticSolrPersonnePrenomPrefere(requeteSite_, GardienScolaire.staticSetPersonnePrenomPrefere(requeteSite_, o)));
	}

	public String solrPersonnePrenomPrefere() {
		return GardienScolaire.staticSolrPersonnePrenomPrefere(requeteSite_, personnePrenomPrefere);
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
		GardienScolaire s = (GardienScolaire)this;
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
					a("class", "setPersonnePrenomPrefere classGardienScolaire inputGardienScolaire", pk, "PersonnePrenomPrefere w3-input w3-border ");
					a("name", "setPersonnePrenomPrefere");
				} else {
					a("class", "valeurPersonnePrenomPrefere w3-input w3-border classGardienScolaire inputGardienScolaire", pk, "PersonnePrenomPrefere w3-input w3-border ");
					a("name", "personnePrenomPrefere");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonnePrenomPrefere', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }); ");
				}
				a("value", strPersonnePrenomPrefere())
			.fg();

		} else {
			e("span").a("class", "varGardienScolaire", pk, "PersonnePrenomPrefere ").f().sx(htmPersonnePrenomPrefere()).g("span");
		}
	}

	public void htmPersonnePrenomPrefere(String classeApiMethodeMethode) {
		GardienScolaire s = (GardienScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "GardienScolairePersonnePrenomPrefere").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); $('#", classeApiMethodeMethode, "_personnePrenomPrefere').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#GardienScolaireForm :input[name=pk]').val() }], 'setPersonnePrenomPrefere', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personnePrenomPrefere')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:familleNom">Trouver l'entité familleNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familleNom(Couverture<String> c);

	public String getFamilleNom() {
		return familleNom;
	}
	public void setFamilleNom(String o) {
		this.familleNom = GardienScolaire.staticSetFamilleNom(requeteSite_, o);
		this.familleNomCouverture.dejaInitialise = true;
	}
	public static String staticSetFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire familleNomInit() {
		if(!familleNomCouverture.dejaInitialise) {
			_familleNom(familleNomCouverture);
			if(familleNom == null)
				setFamilleNom(familleNomCouverture.o);
		}
		familleNomCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilleNom(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrFamilleNom(requeteSite_, GardienScolaire.staticSolrFamilleNom(requeteSite_, GardienScolaire.staticSetFamilleNom(requeteSite_, o)));
	}

	public String solrFamilleNom() {
		return GardienScolaire.staticSolrFamilleNom(requeteSite_, familleNom);
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
		GardienScolaire s = (GardienScolaire)this;
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
					a("class", "setFamilleNom classGardienScolaire inputGardienScolaire", pk, "FamilleNom w3-input w3-border ");
					a("name", "setFamilleNom");
				} else {
					a("class", "valeurFamilleNom w3-input w3-border classGardienScolaire inputGardienScolaire", pk, "FamilleNom w3-input w3-border ");
					a("name", "familleNom");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilleNom', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleNom')); }); ");
				}
				a("value", strFamilleNom())
			.fg();

		} else {
			e("span").a("class", "varGardienScolaire", pk, "FamilleNom ").f().sx(htmFamilleNom()).g("span");
		}
	}

	public void htmFamilleNom(String classeApiMethodeMethode) {
		GardienScolaire s = (GardienScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "GardienScolaireFamilleNom").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_familleNom')); $('#", classeApiMethodeMethode, "_familleNom').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#GardienScolaireForm :input[name=pk]').val() }], 'setFamilleNom', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_familleNom')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_familleNom')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomComplet">Trouver l'entité personneNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomComplet(Couverture<String> c);

	public String getPersonneNomComplet() {
		return personneNomComplet;
	}
	public void setPersonneNomComplet(String o) {
		this.personneNomComplet = GardienScolaire.staticSetPersonneNomComplet(requeteSite_, o);
		this.personneNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire personneNomCompletInit() {
		if(!personneNomCompletCouverture.dejaInitialise) {
			_personneNomComplet(personneNomCompletCouverture);
			if(personneNomComplet == null)
				setPersonneNomComplet(personneNomCompletCouverture.o);
		}
		personneNomCompletCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonneNomComplet(requeteSite_, GardienScolaire.staticSolrPersonneNomComplet(requeteSite_, GardienScolaire.staticSetPersonneNomComplet(requeteSite_, o)));
	}

	public String solrPersonneNomComplet() {
		return GardienScolaire.staticSolrPersonneNomComplet(requeteSite_, personneNomComplet);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomCompletPrefere">Trouver l'entité personneNomCompletPrefere dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomCompletPrefere(Couverture<String> c);

	public String getPersonneNomCompletPrefere() {
		return personneNomCompletPrefere;
	}
	public void setPersonneNomCompletPrefere(String o) {
		this.personneNomCompletPrefere = GardienScolaire.staticSetPersonneNomCompletPrefere(requeteSite_, o);
		this.personneNomCompletPrefereCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire personneNomCompletPrefereInit() {
		if(!personneNomCompletPrefereCouverture.dejaInitialise) {
			_personneNomCompletPrefere(personneNomCompletPrefereCouverture);
			if(personneNomCompletPrefere == null)
				setPersonneNomCompletPrefere(personneNomCompletPrefereCouverture.o);
		}
		personneNomCompletPrefereCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneNomCompletPrefere(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonneNomCompletPrefere(requeteSite_, GardienScolaire.staticSolrPersonneNomCompletPrefere(requeteSite_, GardienScolaire.staticSetPersonneNomCompletPrefere(requeteSite_, o)));
	}

	public String solrPersonneNomCompletPrefere() {
		return GardienScolaire.staticSolrPersonneNomCompletPrefere(requeteSite_, personneNomCompletPrefere);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNomFormel">Trouver l'entité personneNomFormel dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNomFormel(Couverture<String> c);

	public String getPersonneNomFormel() {
		return personneNomFormel;
	}
	public void setPersonneNomFormel(String o) {
		this.personneNomFormel = GardienScolaire.staticSetPersonneNomFormel(requeteSite_, o);
		this.personneNomFormelCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire personneNomFormelInit() {
		if(!personneNomFormelCouverture.dejaInitialise) {
			_personneNomFormel(personneNomFormelCouverture);
			if(personneNomFormel == null)
				setPersonneNomFormel(personneNomFormelCouverture.o);
		}
		personneNomFormelCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneNomFormel(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonneNomFormel(requeteSite_, GardienScolaire.staticSolrPersonneNomFormel(requeteSite_, GardienScolaire.staticSetPersonneNomFormel(requeteSite_, o)));
	}

	public String solrPersonneNomFormel() {
		return GardienScolaire.staticSolrPersonneNomFormel(requeteSite_, personneNomFormel);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneOccupation">Trouver l'entité personneOccupation dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneOccupation(Couverture<String> c);

	public String getPersonneOccupation() {
		return personneOccupation;
	}
	public void setPersonneOccupation(String o) {
		this.personneOccupation = GardienScolaire.staticSetPersonneOccupation(requeteSite_, o);
		this.personneOccupationCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneOccupation(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire personneOccupationInit() {
		if(!personneOccupationCouverture.dejaInitialise) {
			_personneOccupation(personneOccupationCouverture);
			if(personneOccupation == null)
				setPersonneOccupation(personneOccupationCouverture.o);
		}
		personneOccupationCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrPersonneOccupation(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneOccupation(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneOccupation(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonneOccupation(requeteSite_, GardienScolaire.staticSolrPersonneOccupation(requeteSite_, GardienScolaire.staticSetPersonneOccupation(requeteSite_, o)));
	}

	public String solrPersonneOccupation() {
		return GardienScolaire.staticSolrPersonneOccupation(requeteSite_, personneOccupation);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneNumeroTelephone">Trouver l'entité personneNumeroTelephone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneNumeroTelephone(Couverture<String> c);

	public String getPersonneNumeroTelephone() {
		return personneNumeroTelephone;
	}
	public void setPersonneNumeroTelephone(String o) {
		this.personneNumeroTelephone = GardienScolaire.staticSetPersonneNumeroTelephone(requeteSite_, o);
		this.personneNumeroTelephoneCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire personneNumeroTelephoneInit() {
		if(!personneNumeroTelephoneCouverture.dejaInitialise) {
			_personneNumeroTelephone(personneNumeroTelephoneCouverture);
			if(personneNumeroTelephone == null)
				setPersonneNumeroTelephone(personneNumeroTelephoneCouverture.o);
		}
		personneNumeroTelephoneCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrPersonneNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneNumeroTelephone(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonneNumeroTelephone(requeteSite_, GardienScolaire.staticSolrPersonneNumeroTelephone(requeteSite_, GardienScolaire.staticSetPersonneNumeroTelephone(requeteSite_, o)));
	}

	public String solrPersonneNumeroTelephone() {
		return GardienScolaire.staticSolrPersonneNumeroTelephone(requeteSite_, personneNumeroTelephone);
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
		GardienScolaire s = (GardienScolaire)this;
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
					a("class", "setPersonneNumeroTelephone classGardienScolaire inputGardienScolaire", pk, "PersonneNumeroTelephone w3-input w3-border ");
					a("name", "setPersonneNumeroTelephone");
				} else {
					a("class", "valeurPersonneNumeroTelephone w3-input w3-border classGardienScolaire inputGardienScolaire", pk, "PersonneNumeroTelephone w3-input w3-border ");
					a("name", "personneNumeroTelephone");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonneNumeroTelephone', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneNumeroTelephone')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneNumeroTelephone')); }); ");
				}
				a("value", strPersonneNumeroTelephone())
			.fg();

		} else {
			e("span").a("class", "varGardienScolaire", pk, "PersonneNumeroTelephone ").f().sx(htmPersonneNumeroTelephone()).g("span");
		}
	}

	public void htmPersonneNumeroTelephone(String classeApiMethodeMethode) {
		GardienScolaire s = (GardienScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "GardienScolairePersonneNumeroTelephone").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personneNumeroTelephone')); $('#", classeApiMethodeMethode, "_personneNumeroTelephone').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#GardienScolaireForm :input[name=pk]').val() }], 'setPersonneNumeroTelephone', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneNumeroTelephone')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneNumeroTelephone')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneMail">Trouver l'entité personneMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneMail(Couverture<String> c);

	public String getPersonneMail() {
		return personneMail;
	}
	public void setPersonneMail(String o) {
		this.personneMail = GardienScolaire.staticSetPersonneMail(requeteSite_, o);
		this.personneMailCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneMail(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire personneMailInit() {
		if(!personneMailCouverture.dejaInitialise) {
			_personneMail(personneMailCouverture);
			if(personneMail == null)
				setPersonneMail(personneMailCouverture.o);
		}
		personneMailCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrPersonneMail(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneMail(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneMail(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonneMail(requeteSite_, GardienScolaire.staticSolrPersonneMail(requeteSite_, GardienScolaire.staticSetPersonneMail(requeteSite_, o)));
	}

	public String solrPersonneMail() {
		return GardienScolaire.staticSolrPersonneMail(requeteSite_, personneMail);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneRelation">Trouver l'entité personneRelation dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personneRelation(Couverture<String> c);

	public String getPersonneRelation() {
		return personneRelation;
	}
	public void setPersonneRelation(String o) {
		this.personneRelation = GardienScolaire.staticSetPersonneRelation(requeteSite_, o);
		this.personneRelationCouverture.dejaInitialise = true;
	}
	public static String staticSetPersonneRelation(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire personneRelationInit() {
		if(!personneRelationCouverture.dejaInitialise) {
			_personneRelation(personneRelationCouverture);
			if(personneRelation == null)
				setPersonneRelation(personneRelationCouverture.o);
		}
		personneRelationCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrPersonneRelation(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPersonneRelation(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneRelation(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonneRelation(requeteSite_, GardienScolaire.staticSolrPersonneRelation(requeteSite_, GardienScolaire.staticSetPersonneRelation(requeteSite_, o)));
	}

	public String solrPersonneRelation() {
		return GardienScolaire.staticSolrPersonneRelation(requeteSite_, personneRelation);
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

	public void inputPersonneRelation(String classeApiMethodeMethode) {
		GardienScolaire s = (GardienScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "relation")
				.a("id", classeApiMethodeMethode, "_personneRelation");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setPersonneRelation classGardienScolaire inputGardienScolaire", pk, "PersonneRelation w3-input w3-border ");
					a("name", "setPersonneRelation");
				} else {
					a("class", "valeurPersonneRelation w3-input w3-border classGardienScolaire inputGardienScolaire", pk, "PersonneRelation w3-input w3-border ");
					a("name", "personneRelation");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonneRelation', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneRelation')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneRelation')); }); ");
				}
				a("value", strPersonneRelation())
			.fg();

		} else {
			e("span").a("class", "varGardienScolaire", pk, "PersonneRelation ").f().sx(htmPersonneRelation()).g("span");
		}
	}

	public void htmPersonneRelation(String classeApiMethodeMethode) {
		GardienScolaire s = (GardienScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "GardienScolairePersonneRelation").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_personneRelation").a("class", "").f().sx("relation").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonneRelation(classeApiMethodeMethode);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_personneRelation')); $('#", classeApiMethodeMethode, "_personneRelation').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#GardienScolaireForm :input[name=pk]').val() }], 'setPersonneRelation', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_personneRelation')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_personneRelation')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneSms">Trouver l'entité personneSms dans Solr</a>
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
		this.personneSms = GardienScolaire.staticSetPersonneSms(requeteSite_, o);
		this.personneSmsCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPersonneSms(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected GardienScolaire personneSmsInit() {
		if(!personneSmsCouverture.dejaInitialise) {
			_personneSms(personneSmsCouverture);
			if(personneSms == null)
				setPersonneSms(personneSmsCouverture.o);
		}
		personneSmsCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Boolean staticSolrPersonneSms(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonneSms(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneSms(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonneSms(requeteSite_, GardienScolaire.staticSolrPersonneSms(requeteSite_, GardienScolaire.staticSetPersonneSms(requeteSite_, o)));
	}

	public Boolean solrPersonneSms() {
		return GardienScolaire.staticSolrPersonneSms(requeteSite_, personneSms);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneRecevoirMail">Trouver l'entité personneRecevoirMail dans Solr</a>
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
		this.personneRecevoirMail = GardienScolaire.staticSetPersonneRecevoirMail(requeteSite_, o);
		this.personneRecevoirMailCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPersonneRecevoirMail(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected GardienScolaire personneRecevoirMailInit() {
		if(!personneRecevoirMailCouverture.dejaInitialise) {
			_personneRecevoirMail(personneRecevoirMailCouverture);
			if(personneRecevoirMail == null)
				setPersonneRecevoirMail(personneRecevoirMailCouverture.o);
		}
		personneRecevoirMailCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Boolean staticSolrPersonneRecevoirMail(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonneRecevoirMail(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneRecevoirMail(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonneRecevoirMail(requeteSite_, GardienScolaire.staticSolrPersonneRecevoirMail(requeteSite_, GardienScolaire.staticSetPersonneRecevoirMail(requeteSite_, o)));
	}

	public Boolean solrPersonneRecevoirMail() {
		return GardienScolaire.staticSolrPersonneRecevoirMail(requeteSite_, personneRecevoirMail);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneContactUrgence">Trouver l'entité personneContactUrgence dans Solr</a>
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
		this.personneContactUrgence = GardienScolaire.staticSetPersonneContactUrgence(requeteSite_, o);
		this.personneContactUrgenceCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPersonneContactUrgence(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected GardienScolaire personneContactUrgenceInit() {
		if(!personneContactUrgenceCouverture.dejaInitialise) {
			_personneContactUrgence(personneContactUrgenceCouverture);
			if(personneContactUrgence == null)
				setPersonneContactUrgence(personneContactUrgenceCouverture.o);
		}
		personneContactUrgenceCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Boolean staticSolrPersonneContactUrgence(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonneContactUrgence(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneContactUrgence(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonneContactUrgence(requeteSite_, GardienScolaire.staticSolrPersonneContactUrgence(requeteSite_, GardienScolaire.staticSetPersonneContactUrgence(requeteSite_, o)));
	}

	public Boolean solrPersonneContactUrgence() {
		return GardienScolaire.staticSolrPersonneContactUrgence(requeteSite_, personneContactUrgence);
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
		GardienScolaire s = (GardienScolaire)this;
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
				a("class", "setPersonneContactUrgence classGardienScolaire inputGardienScolaire", pk, "PersonneContactUrgence w3-input w3-border ");
				a("name", "setPersonneContactUrgence");
			} else {
				a("class", "valeurPersonneContactUrgence classGardienScolaire inputGardienScolaire", pk, "PersonneContactUrgence w3-input w3-border ");
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
			e("span").a("class", "varGardienScolaire", pk, "PersonneContactUrgence ").f().sx(htmPersonneContactUrgence()).g("span");
		}
	}

	public void htmPersonneContactUrgence(String classeApiMethodeMethode) {
		GardienScolaire s = (GardienScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "GardienScolairePersonneContactUrgence").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:personneChercher">Trouver l'entité personneChercher dans Solr</a>
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
		this.personneChercher = GardienScolaire.staticSetPersonneChercher(requeteSite_, o);
		this.personneChercherCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPersonneChercher(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected GardienScolaire personneChercherInit() {
		if(!personneChercherCouverture.dejaInitialise) {
			_personneChercher(personneChercherCouverture);
			if(personneChercher == null)
				setPersonneChercher(personneChercherCouverture.o);
		}
		personneChercherCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static Boolean staticSolrPersonneChercher(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonneChercher(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonneChercher(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPersonneChercher(requeteSite_, GardienScolaire.staticSolrPersonneChercher(requeteSite_, GardienScolaire.staticSetPersonneChercher(requeteSite_, o)));
	}

	public Boolean solrPersonneChercher() {
		return GardienScolaire.staticSolrPersonneChercher(requeteSite_, personneChercher);
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
		GardienScolaire s = (GardienScolaire)this;
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
				a("class", "setPersonneChercher classGardienScolaire inputGardienScolaire", pk, "PersonneChercher w3-input w3-border ");
				a("name", "setPersonneChercher");
			} else {
				a("class", "valeurPersonneChercher classGardienScolaire inputGardienScolaire", pk, "PersonneChercher w3-input w3-border ");
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
			e("span").a("class", "varGardienScolaire", pk, "PersonneChercher ").f().sx(htmPersonneChercher()).g("span");
		}
	}

	public void htmPersonneChercher(String classeApiMethodeMethode) {
		GardienScolaire s = (GardienScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "GardienScolairePersonneChercher").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:photo">Trouver l'entité photo dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _photo(Couverture<String> c);

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String o) {
		this.photo = GardienScolaire.staticSetPhoto(requeteSite_, o);
		this.photoCouverture.dejaInitialise = true;
	}
	public static String staticSetPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire photoInit() {
		if(!photoCouverture.dejaInitialise) {
			_photo(photoCouverture);
			if(photo == null)
				setPhoto(photoCouverture.o);
		}
		photoCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPhoto(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrPhoto(requeteSite_, GardienScolaire.staticSolrPhoto(requeteSite_, GardienScolaire.staticSetPhoto(requeteSite_, o)));
	}

	public String solrPhoto() {
		return GardienScolaire.staticSolrPhoto(requeteSite_, photo);
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
		GardienScolaire s = (GardienScolaire)this;
		if(
				utilisateurCles.contains(requeteSite_.getUtilisateurCle())
				|| Objects.equals(sessionId, requeteSite_.getSessionId())
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
		) {
			e("div").a("class", "imageBase64Div1GardienScolaire_photo").a("id", "imageBase64Div1GardienScolaire", pk, "photo").f();
				e("h5").f().sx("Télécharger image").g("h5"); 
				e("form").a("method", "POST").a("enctype", "multipart/form-data").a("action", "/photo").a("class", "").f();
				e("input").a("type", "hidden").a("name", "pk").a("value", pk).fg(); 
				e("input").a("type", "hidden").a("name", "classeNomSimple").a("value", "GardienScolaire").fg(); 
				e("input").a("name", "fichier").a("type", "file").a("onchange", "$.ajax({ type: 'POST', enctype: 'multipart/form-data', url: '/photo', data: new FormData(this.form), processData: false, contentType: false}); ").fg(); 
				g("form");
				e("img").a("id", "imageBase64ImgGardienScolaire", pk, "photo");
					a("class", "imgGardienScolaire", pk, "Photo w3-image ");
					a("src", StringUtils.isBlank(photo) ? "data:image/png;base64," : photo).a("alt", "");
				fg();
			g("div");
		} else {
			e("span").a("class", "varGardienScolaire", pk, "Photo ").f().sx(htmPhoto()).g("span");
		}
	}

	public void htmPhoto(String classeApiMethodeMethode) {
		GardienScolaire s = (GardienScolaire)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "GardienScolairePhoto").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_photo')); $('#", classeApiMethodeMethode, "_photo').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#GardienScolaireForm :input[name=pk]').val() }], 'setPhoto', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_photo')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_photo')); }); ")
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
	// gardienNomComplet //
	///////////////////////

	/**	 L'entité gardienNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String gardienNomComplet;
	@JsonIgnore
	public Couverture<String> gardienNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("gardienNomComplet").o(gardienNomComplet);

	/**	<br/> L'entité gardienNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.gardien.GardienScolaire&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gardienNomComplet">Trouver l'entité gardienNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _gardienNomComplet(Couverture<String> c);

	public String getGardienNomComplet() {
		return gardienNomComplet;
	}
	public void setGardienNomComplet(String o) {
		this.gardienNomComplet = GardienScolaire.staticSetGardienNomComplet(requeteSite_, o);
		this.gardienNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetGardienNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected GardienScolaire gardienNomCompletInit() {
		if(!gardienNomCompletCouverture.dejaInitialise) {
			_gardienNomComplet(gardienNomCompletCouverture);
			if(gardienNomComplet == null)
				setGardienNomComplet(gardienNomCompletCouverture.o);
		}
		gardienNomCompletCouverture.dejaInitialise(true);
		return (GardienScolaire)this;
	}

	public static String staticSolrGardienNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrGardienNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqGardienNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return GardienScolaire.staticSolrStrGardienNomComplet(requeteSite_, GardienScolaire.staticSolrGardienNomComplet(requeteSite_, GardienScolaire.staticSetGardienNomComplet(requeteSite_, o)));
	}

	public String solrGardienNomComplet() {
		return GardienScolaire.staticSolrGardienNomComplet(requeteSite_, gardienNomComplet);
	}

	public String strGardienNomComplet() {
		return gardienNomComplet == null ? "" : gardienNomComplet;
	}

	public String jsonGardienNomComplet() {
		return gardienNomComplet == null ? "" : gardienNomComplet;
	}

	public String nomAffichageGardienNomComplet() {
		return null;
	}

	public String htmTooltipGardienNomComplet() {
		return null;
	}

	public String htmGardienNomComplet() {
		return gardienNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strGardienNomComplet());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseGardienScolaire = false;

	public GardienScolaire initLoinGardienScolaire(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseGardienScolaire) {
			dejaInitialiseGardienScolaire = true;
			initLoinGardienScolaire();
		}
		return (GardienScolaire)this;
	}

	public void initLoinGardienScolaire() {
		initGardienScolaire();
		super.initLoinCluster(requeteSite_);
	}

	public void initGardienScolaire() {
		gardienCleInit();
		inscriptionClesInit();
		familleTriInit();
		gardienTriInit();
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
		gardienNomCompletInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinGardienScolaire(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteGardienScolaire(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(inscriptionRecherche != null)
			inscriptionRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteGardienScolaire(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirGardienScolaire(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirGardienScolaire(String var) {
		GardienScolaire oGardienScolaire = (GardienScolaire)this;
		switch(var) {
			case "gardienCle":
				return oGardienScolaire.gardienCle;
			case "inscriptionCles":
				return oGardienScolaire.inscriptionCles;
			case "familleTri":
				return oGardienScolaire.familleTri;
			case "gardienTri":
				return oGardienScolaire.gardienTri;
			case "inscriptionRecherche":
				return oGardienScolaire.inscriptionRecherche;
			case "inscriptions":
				return oGardienScolaire.inscriptions;
			case "utilisateurCles":
				return oGardienScolaire.utilisateurCles;
			case "ecoleCles":
				return oGardienScolaire.ecoleCles;
			case "anneeCles":
				return oGardienScolaire.anneeCles;
			case "saisonCles":
				return oGardienScolaire.saisonCles;
			case "sessionCles":
				return oGardienScolaire.sessionCles;
			case "ageCles":
				return oGardienScolaire.ageCles;
			case "personnePrenom":
				return oGardienScolaire.personnePrenom;
			case "personnePrenomPrefere":
				return oGardienScolaire.personnePrenomPrefere;
			case "familleNom":
				return oGardienScolaire.familleNom;
			case "personneNomComplet":
				return oGardienScolaire.personneNomComplet;
			case "personneNomCompletPrefere":
				return oGardienScolaire.personneNomCompletPrefere;
			case "personneNomFormel":
				return oGardienScolaire.personneNomFormel;
			case "personneOccupation":
				return oGardienScolaire.personneOccupation;
			case "personneNumeroTelephone":
				return oGardienScolaire.personneNumeroTelephone;
			case "personneMail":
				return oGardienScolaire.personneMail;
			case "personneRelation":
				return oGardienScolaire.personneRelation;
			case "personneSms":
				return oGardienScolaire.personneSms;
			case "personneRecevoirMail":
				return oGardienScolaire.personneRecevoirMail;
			case "personneContactUrgence":
				return oGardienScolaire.personneContactUrgence;
			case "personneChercher":
				return oGardienScolaire.personneChercher;
			case "photo":
				return oGardienScolaire.photo;
			case "gardienNomComplet":
				return oGardienScolaire.gardienNomComplet;
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
				o = attribuerGardienScolaire(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerGardienScolaire(String var, Object val) {
		GardienScolaire oGardienScolaire = (GardienScolaire)this;
		switch(var) {
			case "inscriptionCles":
				oGardienScolaire.addInscriptionCles((Long)val);
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
		return staticSetGardienScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetGardienScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "gardienCle":
			return GardienScolaire.staticSetGardienCle(requeteSite_, o);
		case "inscriptionCles":
			return GardienScolaire.staticSetInscriptionCles(requeteSite_, o);
		case "familleTri":
			return GardienScolaire.staticSetFamilleTri(requeteSite_, o);
		case "gardienTri":
			return GardienScolaire.staticSetGardienTri(requeteSite_, o);
		case "utilisateurCles":
			return GardienScolaire.staticSetUtilisateurCles(requeteSite_, o);
		case "ecoleCles":
			return GardienScolaire.staticSetEcoleCles(requeteSite_, o);
		case "anneeCles":
			return GardienScolaire.staticSetAnneeCles(requeteSite_, o);
		case "saisonCles":
			return GardienScolaire.staticSetSaisonCles(requeteSite_, o);
		case "sessionCles":
			return GardienScolaire.staticSetSessionCles(requeteSite_, o);
		case "ageCles":
			return GardienScolaire.staticSetAgeCles(requeteSite_, o);
		case "personnePrenom":
			return GardienScolaire.staticSetPersonnePrenom(requeteSite_, o);
		case "personnePrenomPrefere":
			return GardienScolaire.staticSetPersonnePrenomPrefere(requeteSite_, o);
		case "familleNom":
			return GardienScolaire.staticSetFamilleNom(requeteSite_, o);
		case "personneNomComplet":
			return GardienScolaire.staticSetPersonneNomComplet(requeteSite_, o);
		case "personneNomCompletPrefere":
			return GardienScolaire.staticSetPersonneNomCompletPrefere(requeteSite_, o);
		case "personneNomFormel":
			return GardienScolaire.staticSetPersonneNomFormel(requeteSite_, o);
		case "personneOccupation":
			return GardienScolaire.staticSetPersonneOccupation(requeteSite_, o);
		case "personneNumeroTelephone":
			return GardienScolaire.staticSetPersonneNumeroTelephone(requeteSite_, o);
		case "personneMail":
			return GardienScolaire.staticSetPersonneMail(requeteSite_, o);
		case "personneRelation":
			return GardienScolaire.staticSetPersonneRelation(requeteSite_, o);
		case "personneSms":
			return GardienScolaire.staticSetPersonneSms(requeteSite_, o);
		case "personneRecevoirMail":
			return GardienScolaire.staticSetPersonneRecevoirMail(requeteSite_, o);
		case "personneContactUrgence":
			return GardienScolaire.staticSetPersonneContactUrgence(requeteSite_, o);
		case "personneChercher":
			return GardienScolaire.staticSetPersonneChercher(requeteSite_, o);
		case "photo":
			return GardienScolaire.staticSetPhoto(requeteSite_, o);
		case "gardienNomComplet":
			return GardienScolaire.staticSetGardienNomComplet(requeteSite_, o);
			default:
				return Cluster.staticSetCluster(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrGardienScolaire(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrGardienScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "gardienCle":
			return GardienScolaire.staticSolrGardienCle(requeteSite_, (Long)o);
		case "inscriptionCles":
			return GardienScolaire.staticSolrInscriptionCles(requeteSite_, (Long)o);
		case "familleTri":
			return GardienScolaire.staticSolrFamilleTri(requeteSite_, (Integer)o);
		case "gardienTri":
			return GardienScolaire.staticSolrGardienTri(requeteSite_, (Integer)o);
		case "utilisateurCles":
			return GardienScolaire.staticSolrUtilisateurCles(requeteSite_, (Long)o);
		case "ecoleCles":
			return GardienScolaire.staticSolrEcoleCles(requeteSite_, (Long)o);
		case "anneeCles":
			return GardienScolaire.staticSolrAnneeCles(requeteSite_, (Long)o);
		case "saisonCles":
			return GardienScolaire.staticSolrSaisonCles(requeteSite_, (Long)o);
		case "sessionCles":
			return GardienScolaire.staticSolrSessionCles(requeteSite_, (Long)o);
		case "ageCles":
			return GardienScolaire.staticSolrAgeCles(requeteSite_, (Long)o);
		case "personnePrenom":
			return GardienScolaire.staticSolrPersonnePrenom(requeteSite_, (String)o);
		case "personnePrenomPrefere":
			return GardienScolaire.staticSolrPersonnePrenomPrefere(requeteSite_, (String)o);
		case "familleNom":
			return GardienScolaire.staticSolrFamilleNom(requeteSite_, (String)o);
		case "personneNomComplet":
			return GardienScolaire.staticSolrPersonneNomComplet(requeteSite_, (String)o);
		case "personneNomCompletPrefere":
			return GardienScolaire.staticSolrPersonneNomCompletPrefere(requeteSite_, (String)o);
		case "personneNomFormel":
			return GardienScolaire.staticSolrPersonneNomFormel(requeteSite_, (String)o);
		case "personneOccupation":
			return GardienScolaire.staticSolrPersonneOccupation(requeteSite_, (String)o);
		case "personneNumeroTelephone":
			return GardienScolaire.staticSolrPersonneNumeroTelephone(requeteSite_, (String)o);
		case "personneMail":
			return GardienScolaire.staticSolrPersonneMail(requeteSite_, (String)o);
		case "personneRelation":
			return GardienScolaire.staticSolrPersonneRelation(requeteSite_, (String)o);
		case "personneSms":
			return GardienScolaire.staticSolrPersonneSms(requeteSite_, (Boolean)o);
		case "personneRecevoirMail":
			return GardienScolaire.staticSolrPersonneRecevoirMail(requeteSite_, (Boolean)o);
		case "personneContactUrgence":
			return GardienScolaire.staticSolrPersonneContactUrgence(requeteSite_, (Boolean)o);
		case "personneChercher":
			return GardienScolaire.staticSolrPersonneChercher(requeteSite_, (Boolean)o);
		case "photo":
			return GardienScolaire.staticSolrPhoto(requeteSite_, (String)o);
		case "gardienNomComplet":
			return GardienScolaire.staticSolrGardienNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrCluster(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrGardienScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrGardienScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "gardienCle":
			return GardienScolaire.staticSolrStrGardienCle(requeteSite_, (Long)o);
		case "inscriptionCles":
			return GardienScolaire.staticSolrStrInscriptionCles(requeteSite_, (Long)o);
		case "familleTri":
			return GardienScolaire.staticSolrStrFamilleTri(requeteSite_, (Integer)o);
		case "gardienTri":
			return GardienScolaire.staticSolrStrGardienTri(requeteSite_, (Integer)o);
		case "utilisateurCles":
			return GardienScolaire.staticSolrStrUtilisateurCles(requeteSite_, (Long)o);
		case "ecoleCles":
			return GardienScolaire.staticSolrStrEcoleCles(requeteSite_, (Long)o);
		case "anneeCles":
			return GardienScolaire.staticSolrStrAnneeCles(requeteSite_, (Long)o);
		case "saisonCles":
			return GardienScolaire.staticSolrStrSaisonCles(requeteSite_, (Long)o);
		case "sessionCles":
			return GardienScolaire.staticSolrStrSessionCles(requeteSite_, (Long)o);
		case "ageCles":
			return GardienScolaire.staticSolrStrAgeCles(requeteSite_, (Long)o);
		case "personnePrenom":
			return GardienScolaire.staticSolrStrPersonnePrenom(requeteSite_, (String)o);
		case "personnePrenomPrefere":
			return GardienScolaire.staticSolrStrPersonnePrenomPrefere(requeteSite_, (String)o);
		case "familleNom":
			return GardienScolaire.staticSolrStrFamilleNom(requeteSite_, (String)o);
		case "personneNomComplet":
			return GardienScolaire.staticSolrStrPersonneNomComplet(requeteSite_, (String)o);
		case "personneNomCompletPrefere":
			return GardienScolaire.staticSolrStrPersonneNomCompletPrefere(requeteSite_, (String)o);
		case "personneNomFormel":
			return GardienScolaire.staticSolrStrPersonneNomFormel(requeteSite_, (String)o);
		case "personneOccupation":
			return GardienScolaire.staticSolrStrPersonneOccupation(requeteSite_, (String)o);
		case "personneNumeroTelephone":
			return GardienScolaire.staticSolrStrPersonneNumeroTelephone(requeteSite_, (String)o);
		case "personneMail":
			return GardienScolaire.staticSolrStrPersonneMail(requeteSite_, (String)o);
		case "personneRelation":
			return GardienScolaire.staticSolrStrPersonneRelation(requeteSite_, (String)o);
		case "personneSms":
			return GardienScolaire.staticSolrStrPersonneSms(requeteSite_, (Boolean)o);
		case "personneRecevoirMail":
			return GardienScolaire.staticSolrStrPersonneRecevoirMail(requeteSite_, (Boolean)o);
		case "personneContactUrgence":
			return GardienScolaire.staticSolrStrPersonneContactUrgence(requeteSite_, (Boolean)o);
		case "personneChercher":
			return GardienScolaire.staticSolrStrPersonneChercher(requeteSite_, (Boolean)o);
		case "photo":
			return GardienScolaire.staticSolrStrPhoto(requeteSite_, (String)o);
		case "gardienNomComplet":
			return GardienScolaire.staticSolrStrGardienNomComplet(requeteSite_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqGardienScolaire(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqGardienScolaire(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "gardienCle":
			return GardienScolaire.staticSolrFqGardienCle(requeteSite_, o);
		case "inscriptionCles":
			return GardienScolaire.staticSolrFqInscriptionCles(requeteSite_, o);
		case "familleTri":
			return GardienScolaire.staticSolrFqFamilleTri(requeteSite_, o);
		case "gardienTri":
			return GardienScolaire.staticSolrFqGardienTri(requeteSite_, o);
		case "utilisateurCles":
			return GardienScolaire.staticSolrFqUtilisateurCles(requeteSite_, o);
		case "ecoleCles":
			return GardienScolaire.staticSolrFqEcoleCles(requeteSite_, o);
		case "anneeCles":
			return GardienScolaire.staticSolrFqAnneeCles(requeteSite_, o);
		case "saisonCles":
			return GardienScolaire.staticSolrFqSaisonCles(requeteSite_, o);
		case "sessionCles":
			return GardienScolaire.staticSolrFqSessionCles(requeteSite_, o);
		case "ageCles":
			return GardienScolaire.staticSolrFqAgeCles(requeteSite_, o);
		case "personnePrenom":
			return GardienScolaire.staticSolrFqPersonnePrenom(requeteSite_, o);
		case "personnePrenomPrefere":
			return GardienScolaire.staticSolrFqPersonnePrenomPrefere(requeteSite_, o);
		case "familleNom":
			return GardienScolaire.staticSolrFqFamilleNom(requeteSite_, o);
		case "personneNomComplet":
			return GardienScolaire.staticSolrFqPersonneNomComplet(requeteSite_, o);
		case "personneNomCompletPrefere":
			return GardienScolaire.staticSolrFqPersonneNomCompletPrefere(requeteSite_, o);
		case "personneNomFormel":
			return GardienScolaire.staticSolrFqPersonneNomFormel(requeteSite_, o);
		case "personneOccupation":
			return GardienScolaire.staticSolrFqPersonneOccupation(requeteSite_, o);
		case "personneNumeroTelephone":
			return GardienScolaire.staticSolrFqPersonneNumeroTelephone(requeteSite_, o);
		case "personneMail":
			return GardienScolaire.staticSolrFqPersonneMail(requeteSite_, o);
		case "personneRelation":
			return GardienScolaire.staticSolrFqPersonneRelation(requeteSite_, o);
		case "personneSms":
			return GardienScolaire.staticSolrFqPersonneSms(requeteSite_, o);
		case "personneRecevoirMail":
			return GardienScolaire.staticSolrFqPersonneRecevoirMail(requeteSite_, o);
		case "personneContactUrgence":
			return GardienScolaire.staticSolrFqPersonneContactUrgence(requeteSite_, o);
		case "personneChercher":
			return GardienScolaire.staticSolrFqPersonneChercher(requeteSite_, o);
		case "photo":
			return GardienScolaire.staticSolrFqPhoto(requeteSite_, o);
		case "gardienNomComplet":
			return GardienScolaire.staticSolrFqGardienNomComplet(requeteSite_, o);
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
					o = definirGardienScolaire(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirGardienScolaire(String var, String val) {
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
			case "personneNumeroTelephone":
				if(val != null)
					setPersonneNumeroTelephone(val);
				sauvegardes.add(var);
				return val;
			case "personneRelation":
				if(val != null)
					setPersonneRelation(val);
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
		peuplerGardienScolaire(solrDocument);
	}
	public void peuplerGardienScolaire(SolrDocument solrDocument) {
		GardienScolaire oGardienScolaire = (GardienScolaire)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("gardienCle")) {
				Long gardienCle = (Long)solrDocument.get("gardienCle_stored_long");
				if(gardienCle != null)
					oGardienScolaire.setGardienCle(gardienCle);
			}

			List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
			if(inscriptionCles != null)
				oGardienScolaire.inscriptionCles.addAll(inscriptionCles);

			if(sauvegardes.contains("familleTri")) {
				Integer familleTri = (Integer)solrDocument.get("familleTri_stored_int");
				if(familleTri != null)
					oGardienScolaire.setFamilleTri(familleTri);
			}

			if(sauvegardes.contains("gardienTri")) {
				Integer gardienTri = (Integer)solrDocument.get("gardienTri_stored_int");
				if(gardienTri != null)
					oGardienScolaire.setGardienTri(gardienTri);
			}

			if(sauvegardes.contains("utilisateurCles")) {
				List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
				if(utilisateurCles != null)
					oGardienScolaire.utilisateurCles.addAll(utilisateurCles);
			}

			if(sauvegardes.contains("ecoleCles")) {
				List<Long> ecoleCles = (List<Long>)solrDocument.get("ecoleCles_stored_longs");
				if(ecoleCles != null)
					oGardienScolaire.ecoleCles.addAll(ecoleCles);
			}

			if(sauvegardes.contains("anneeCles")) {
				List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
				if(anneeCles != null)
					oGardienScolaire.anneeCles.addAll(anneeCles);
			}

			if(sauvegardes.contains("saisonCles")) {
				List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
				if(saisonCles != null)
					oGardienScolaire.saisonCles.addAll(saisonCles);
			}

			if(sauvegardes.contains("sessionCles")) {
				List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
				if(sessionCles != null)
					oGardienScolaire.sessionCles.addAll(sessionCles);
			}

			if(sauvegardes.contains("ageCles")) {
				List<Long> ageCles = (List<Long>)solrDocument.get("ageCles_stored_longs");
				if(ageCles != null)
					oGardienScolaire.ageCles.addAll(ageCles);
			}

			if(sauvegardes.contains("personnePrenom")) {
				String personnePrenom = (String)solrDocument.get("personnePrenom_stored_string");
				if(personnePrenom != null)
					oGardienScolaire.setPersonnePrenom(personnePrenom);
			}

			if(sauvegardes.contains("personnePrenomPrefere")) {
				String personnePrenomPrefere = (String)solrDocument.get("personnePrenomPrefere_stored_string");
				if(personnePrenomPrefere != null)
					oGardienScolaire.setPersonnePrenomPrefere(personnePrenomPrefere);
			}

			if(sauvegardes.contains("familleNom")) {
				String familleNom = (String)solrDocument.get("familleNom_stored_string");
				if(familleNom != null)
					oGardienScolaire.setFamilleNom(familleNom);
			}

			if(sauvegardes.contains("personneNomComplet")) {
				String personneNomComplet = (String)solrDocument.get("personneNomComplet_stored_string");
				if(personneNomComplet != null)
					oGardienScolaire.setPersonneNomComplet(personneNomComplet);
			}

			if(sauvegardes.contains("personneNomCompletPrefere")) {
				String personneNomCompletPrefere = (String)solrDocument.get("personneNomCompletPrefere_stored_string");
				if(personneNomCompletPrefere != null)
					oGardienScolaire.setPersonneNomCompletPrefere(personneNomCompletPrefere);
			}

			if(sauvegardes.contains("personneNomFormel")) {
				String personneNomFormel = (String)solrDocument.get("personneNomFormel_stored_string");
				if(personneNomFormel != null)
					oGardienScolaire.setPersonneNomFormel(personneNomFormel);
			}

			if(sauvegardes.contains("personneOccupation")) {
				String personneOccupation = (String)solrDocument.get("personneOccupation_stored_string");
				if(personneOccupation != null)
					oGardienScolaire.setPersonneOccupation(personneOccupation);
			}

			if(sauvegardes.contains("personneNumeroTelephone")) {
				String personneNumeroTelephone = (String)solrDocument.get("personneNumeroTelephone_stored_string");
				if(personneNumeroTelephone != null)
					oGardienScolaire.setPersonneNumeroTelephone(personneNumeroTelephone);
			}

			if(sauvegardes.contains("personneMail")) {
				String personneMail = (String)solrDocument.get("personneMail_stored_string");
				if(personneMail != null)
					oGardienScolaire.setPersonneMail(personneMail);
			}

			if(sauvegardes.contains("personneRelation")) {
				String personneRelation = (String)solrDocument.get("personneRelation_stored_string");
				if(personneRelation != null)
					oGardienScolaire.setPersonneRelation(personneRelation);
			}

			if(sauvegardes.contains("personneSms")) {
				Boolean personneSms = (Boolean)solrDocument.get("personneSms_stored_boolean");
				if(personneSms != null)
					oGardienScolaire.setPersonneSms(personneSms);
			}

			if(sauvegardes.contains("personneRecevoirMail")) {
				Boolean personneRecevoirMail = (Boolean)solrDocument.get("personneRecevoirMail_stored_boolean");
				if(personneRecevoirMail != null)
					oGardienScolaire.setPersonneRecevoirMail(personneRecevoirMail);
			}

			if(sauvegardes.contains("personneContactUrgence")) {
				Boolean personneContactUrgence = (Boolean)solrDocument.get("personneContactUrgence_stored_boolean");
				if(personneContactUrgence != null)
					oGardienScolaire.setPersonneContactUrgence(personneContactUrgence);
			}

			if(sauvegardes.contains("personneChercher")) {
				Boolean personneChercher = (Boolean)solrDocument.get("personneChercher_stored_boolean");
				if(personneChercher != null)
					oGardienScolaire.setPersonneChercher(personneChercher);
			}

			if(sauvegardes.contains("photo")) {
				String photo = (String)solrDocument.get("photo_stored_string");
				if(photo != null)
					oGardienScolaire.setPhoto(photo);
			}

			if(sauvegardes.contains("gardienNomComplet")) {
				String gardienNomComplet = (String)solrDocument.get("gardienNomComplet_stored_string");
				if(gardienNomComplet != null)
					oGardienScolaire.setGardienNomComplet(gardienNomComplet);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.gardien.GardienScolaire"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			GardienScolaire o = new GardienScolaire();
			o.requeteSiteGardienScolaire(requeteSite);
			o.initLoinGardienScolaire(requeteSite);
			o.indexerGardienScolaire();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerGardienScolaire();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerGardienScolaire(document);
	}

	public void indexerGardienScolaire(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerGardienScolaire(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerGardienScolaire() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerGardienScolaire(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerGardienScolaire(SolrInputDocument document) {
		if(gardienCle != null) {
			document.addField("gardienCle_indexed_long", gardienCle);
			document.addField("gardienCle_stored_long", gardienCle);
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
		if(gardienTri != null) {
			document.addField("gardienTri_indexed_int", gardienTri);
			document.addField("gardienTri_stored_int", gardienTri);
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
		if(gardienNomComplet != null) {
			document.addField("gardienNomComplet_indexed_string", gardienNomComplet);
			document.addField("gardienNomComplet_stored_string", gardienNomComplet);
		}
		super.indexerCluster(document);

	}

	public void desindexerGardienScolaire() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinGardienScolaire(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeGardienScolaire(String entiteVar) {
		switch(entiteVar) {
			case "gardienCle":
				return "gardienCle_indexed_long";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "familleTri":
				return "familleTri_indexed_int";
			case "gardienTri":
				return "gardienTri_indexed_int";
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
			case "gardienNomComplet":
				return "gardienNomComplet_indexed_string";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheGardienScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereGardienScolaire(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerGardienScolaire(solrDocument);
	}
	public void stockerGardienScolaire(SolrDocument solrDocument) {
		GardienScolaire oGardienScolaire = (GardienScolaire)this;

		Long gardienCle = (Long)solrDocument.get("gardienCle_stored_long");
		if(gardienCle != null)
			oGardienScolaire.setGardienCle(gardienCle);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oGardienScolaire.inscriptionCles.addAll(inscriptionCles);

		Integer familleTri = (Integer)solrDocument.get("familleTri_stored_int");
		if(familleTri != null)
			oGardienScolaire.setFamilleTri(familleTri);

		Integer gardienTri = (Integer)solrDocument.get("gardienTri_stored_int");
		if(gardienTri != null)
			oGardienScolaire.setGardienTri(gardienTri);

		List<Long> utilisateurCles = (List<Long>)solrDocument.get("utilisateurCles_stored_longs");
		if(utilisateurCles != null)
			oGardienScolaire.utilisateurCles.addAll(utilisateurCles);

		List<Long> ecoleCles = (List<Long>)solrDocument.get("ecoleCles_stored_longs");
		if(ecoleCles != null)
			oGardienScolaire.ecoleCles.addAll(ecoleCles);

		List<Long> anneeCles = (List<Long>)solrDocument.get("anneeCles_stored_longs");
		if(anneeCles != null)
			oGardienScolaire.anneeCles.addAll(anneeCles);

		List<Long> saisonCles = (List<Long>)solrDocument.get("saisonCles_stored_longs");
		if(saisonCles != null)
			oGardienScolaire.saisonCles.addAll(saisonCles);

		List<Long> sessionCles = (List<Long>)solrDocument.get("sessionCles_stored_longs");
		if(sessionCles != null)
			oGardienScolaire.sessionCles.addAll(sessionCles);

		List<Long> ageCles = (List<Long>)solrDocument.get("ageCles_stored_longs");
		if(ageCles != null)
			oGardienScolaire.ageCles.addAll(ageCles);

		String personnePrenom = (String)solrDocument.get("personnePrenom_stored_string");
		if(personnePrenom != null)
			oGardienScolaire.setPersonnePrenom(personnePrenom);

		String personnePrenomPrefere = (String)solrDocument.get("personnePrenomPrefere_stored_string");
		if(personnePrenomPrefere != null)
			oGardienScolaire.setPersonnePrenomPrefere(personnePrenomPrefere);

		String familleNom = (String)solrDocument.get("familleNom_stored_string");
		if(familleNom != null)
			oGardienScolaire.setFamilleNom(familleNom);

		String personneNomComplet = (String)solrDocument.get("personneNomComplet_stored_string");
		if(personneNomComplet != null)
			oGardienScolaire.setPersonneNomComplet(personneNomComplet);

		String personneNomCompletPrefere = (String)solrDocument.get("personneNomCompletPrefere_stored_string");
		if(personneNomCompletPrefere != null)
			oGardienScolaire.setPersonneNomCompletPrefere(personneNomCompletPrefere);

		String personneNomFormel = (String)solrDocument.get("personneNomFormel_stored_string");
		if(personneNomFormel != null)
			oGardienScolaire.setPersonneNomFormel(personneNomFormel);

		String personneOccupation = (String)solrDocument.get("personneOccupation_stored_string");
		if(personneOccupation != null)
			oGardienScolaire.setPersonneOccupation(personneOccupation);

		String personneNumeroTelephone = (String)solrDocument.get("personneNumeroTelephone_stored_string");
		if(personneNumeroTelephone != null)
			oGardienScolaire.setPersonneNumeroTelephone(personneNumeroTelephone);

		String personneMail = (String)solrDocument.get("personneMail_stored_string");
		if(personneMail != null)
			oGardienScolaire.setPersonneMail(personneMail);

		String personneRelation = (String)solrDocument.get("personneRelation_stored_string");
		if(personneRelation != null)
			oGardienScolaire.setPersonneRelation(personneRelation);

		Boolean personneSms = (Boolean)solrDocument.get("personneSms_stored_boolean");
		if(personneSms != null)
			oGardienScolaire.setPersonneSms(personneSms);

		Boolean personneRecevoirMail = (Boolean)solrDocument.get("personneRecevoirMail_stored_boolean");
		if(personneRecevoirMail != null)
			oGardienScolaire.setPersonneRecevoirMail(personneRecevoirMail);

		Boolean personneContactUrgence = (Boolean)solrDocument.get("personneContactUrgence_stored_boolean");
		if(personneContactUrgence != null)
			oGardienScolaire.setPersonneContactUrgence(personneContactUrgence);

		Boolean personneChercher = (Boolean)solrDocument.get("personneChercher_stored_boolean");
		if(personneChercher != null)
			oGardienScolaire.setPersonneChercher(personneChercher);

		String photo = (String)solrDocument.get("photo_stored_string");
		if(photo != null)
			oGardienScolaire.setPhoto(photo);

		String gardienNomComplet = (String)solrDocument.get("gardienNomComplet_stored_string");
		if(gardienNomComplet != null)
			oGardienScolaire.setGardienNomComplet(gardienNomComplet);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiGardienScolaire() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof GardienScolaire) {
			GardienScolaire original = (GardienScolaire)o;
			if(!Objects.equals(gardienCle, original.getGardienCle()))
				requeteApi.addVars("gardienCle");
			if(!Objects.equals(inscriptionCles, original.getInscriptionCles()))
				requeteApi.addVars("inscriptionCles");
			if(!Objects.equals(familleTri, original.getFamilleTri()))
				requeteApi.addVars("familleTri");
			if(!Objects.equals(gardienTri, original.getGardienTri()))
				requeteApi.addVars("gardienTri");
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
			if(!Objects.equals(gardienNomComplet, original.getGardienNomComplet()))
				requeteApi.addVars("gardienNomComplet");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), gardienCle, inscriptionCles, familleTri, gardienTri, utilisateurCles, ecoleCles, anneeCles, saisonCles, sessionCles, ageCles, personnePrenom, personnePrenomPrefere, familleNom, personneNomComplet, personneNomCompletPrefere, personneNomFormel, personneOccupation, personneNumeroTelephone, personneMail, personneRelation, personneSms, personneRecevoirMail, personneContactUrgence, personneChercher, photo, gardienNomComplet);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof GardienScolaire))
			return false;
		GardienScolaire that = (GardienScolaire)o;
		return super.equals(o)
				&& Objects.equals( gardienCle, that.gardienCle )
				&& Objects.equals( inscriptionCles, that.inscriptionCles )
				&& Objects.equals( familleTri, that.familleTri )
				&& Objects.equals( gardienTri, that.gardienTri )
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
				&& Objects.equals( gardienNomComplet, that.gardienNomComplet );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("GardienScolaire { ");
		sb.append( "gardienCle: " ).append(gardienCle);
		sb.append( ", inscriptionCles: " ).append(inscriptionCles);
		sb.append( ", familleTri: " ).append(familleTri);
		sb.append( ", gardienTri: " ).append(gardienTri);
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
		sb.append( ", gardienNomComplet: \"" ).append(gardienNomComplet).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
