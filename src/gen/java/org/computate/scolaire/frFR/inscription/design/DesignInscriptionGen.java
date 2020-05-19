package org.computate.scolaire.frFR.inscription.design;

import java.util.Arrays;
import org.computate.scolaire.frFR.html.part.PartHtml;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
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
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true">Trouver la classe designHidden dans Solr</a>
 * <br/>
 **/
public abstract class DesignInscriptionGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DesignInscription.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String DesignInscription_UnNom = "un design d'inscription";
	public static final String DesignInscription_Ce = "ce ";
	public static final String DesignInscription_CeNom = "ce design d'inscription";
	public static final String DesignInscription_Un = "un ";
	public static final String DesignInscription_LeNom = "le design d'inscription";
	public static final String DesignInscription_NomSingulier = "design d'inscription";
	public static final String DesignInscription_NomPluriel = "design d'inscriptions";
	public static final String DesignInscription_NomActuel = "design d'inscription actuel";
	public static final String DesignInscription_Tous = "all ";
	public static final String DesignInscription_TousNom = "tous les design d'inscriptions";
	public static final String DesignInscription_RechercherTousNomPar = "rechercher design d'inscriptions par ";
	public static final String DesignInscription_RechercherTousNom = "rechercher design d'inscriptions";
	public static final String DesignInscription_LesNom = "les design d'inscriptions";
	public static final String DesignInscription_AucunNomTrouve = "aucun design d'inscription trouvé";
	public static final String DesignInscription_NomVar = "design-inscription";
	public static final String DesignInscription_DeNom = "de design d'inscription";
	public static final String DesignInscription_NomAdjectifSingulier = "design d'inscription";
	public static final String DesignInscription_NomAdjectifPluriel = "design d'inscriptions";
	public static final String DesignInscription_Couleur = "khaki";
	public static final String DesignInscription_IconeGroupe = "regular";
	public static final String DesignInscription_IconeNom = "drafting-compass";

	//////////////////////////
	// designInscriptionCle //
	//////////////////////////

	/**	L'entité « designInscriptionCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long designInscriptionCle;
	@JsonIgnore
	public Couverture<Long> designInscriptionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("designInscriptionCle").o(designInscriptionCle);

	/**	<br/>L'entité « designInscriptionCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designInscriptionCle">Trouver l'entité designInscriptionCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designInscriptionCle(Couverture<Long> c);

	public Long getDesignInscriptionCle() {
		return designInscriptionCle;
	}

	public void setDesignInscriptionCle(Long designInscriptionCle) {
		this.designInscriptionCle = designInscriptionCle;
		this.designInscriptionCleCouverture.dejaInitialise = true;
	}
	public DesignInscription setDesignInscriptionCle(String o) {
		if(NumberUtils.isParsable(o))
			this.designInscriptionCle = Long.parseLong(o);
		this.designInscriptionCleCouverture.dejaInitialise = true;
		return (DesignInscription)this;
	}
	protected DesignInscription designInscriptionCleInit() {
		if(!designInscriptionCleCouverture.dejaInitialise) {
			_designInscriptionCle(designInscriptionCleCouverture);
			if(designInscriptionCle == null)
				setDesignInscriptionCle(designInscriptionCleCouverture.o);
		}
		designInscriptionCleCouverture.dejaInitialise(true);
		return (DesignInscription)this;
	}

	public Long solrDesignInscriptionCle() {
		return designInscriptionCle;
	}

	public String strDesignInscriptionCle() {
		return designInscriptionCle == null ? "" : designInscriptionCle.toString();
	}

	public String jsonDesignInscriptionCle() {
		return designInscriptionCle == null ? "" : designInscriptionCle.toString();
	}

	public String nomAffichageDesignInscriptionCle() {
		return "clé";
	}

	public String htmTooltipDesignInscriptionCle() {
		return null;
	}

	public String htmDesignInscriptionCle() {
		return designInscriptionCle == null ? "" : StringEscapeUtils.escapeHtml4(strDesignInscriptionCle());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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
	public DesignInscription setAnneeCle(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeCle = Long.parseLong(o);
		this.anneeCleCouverture.dejaInitialise = true;
		return (DesignInscription)this;
	}
	protected DesignInscription anneeCleInit() {
		if(!anneeCleCouverture.dejaInitialise) {
			_anneeCle(anneeCleCouverture);
			if(anneeCle == null)
				setAnneeCle(anneeCleCouverture.o);
		}
		anneeCleCouverture.dejaInitialise(true);
		return (DesignInscription)this;
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

	//////////////////
	// partHtmlCles //
	//////////////////

	/**	L'entité « partHtmlCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> partHtmlCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> partHtmlClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("partHtmlCles").o(partHtmlCles);

	/**	<br/>L'entité « partHtmlCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partHtmlCles">Trouver l'entité partHtmlCles dans Solr</a>
	 * <br/>
	 * @param partHtmlCles est l'entité déjà construit. 
	 **/
	protected abstract void _partHtmlCles(List<Long> o);

	public List<Long> getPartHtmlCles() {
		return partHtmlCles;
	}

	public void setPartHtmlCles(List<Long> partHtmlCles) {
		this.partHtmlCles = partHtmlCles;
		this.partHtmlClesCouverture.dejaInitialise = true;
	}
	public DesignInscription addPartHtmlCles(Long...objets) {
		for(Long o : objets) {
			addPartHtmlCles(o);
		}
		return (DesignInscription)this;
	}
	public DesignInscription addPartHtmlCles(Long o) {
		if(o != null && !partHtmlCles.contains(o))
			this.partHtmlCles.add(o);
		return (DesignInscription)this;
	}
	public DesignInscription setPartHtmlCles(JsonArray objets) {
		partHtmlCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPartHtmlCles(o);
		}
		return (DesignInscription)this;
	}
	public DesignInscription addPartHtmlCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPartHtmlCles(p);
		}
		return (DesignInscription)this;
	}
	protected DesignInscription partHtmlClesInit() {
		if(!partHtmlClesCouverture.dejaInitialise) {
			_partHtmlCles(partHtmlCles);
		}
		partHtmlClesCouverture.dejaInitialise(true);
		return (DesignInscription)this;
	}

	public List<Long> solrPartHtmlCles() {
		return partHtmlCles;
	}

	public String strPartHtmlCles() {
		return partHtmlCles == null ? "" : partHtmlCles.toString();
	}

	public String jsonPartHtmlCles() {
		return partHtmlCles == null ? "" : partHtmlCles.toString();
	}

	public String nomAffichagePartHtmlCles() {
		return "parts";
	}

	public String htmTooltipPartHtmlCles() {
		return null;
	}

	public String htmPartHtmlCles() {
		return partHtmlCles == null ? "" : StringEscapeUtils.escapeHtml4(strPartHtmlCles());
	}

	public void inputPartHtmlCles(String classeApiMethodeMethode) {
		DesignInscription s = (DesignInscription)this;
	}

	public void htmPartHtmlCles(String classeApiMethodeMethode) {
		DesignInscription s = (DesignInscription)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			if("Page".equals(classeApiMethodeMethode)) {
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("class", "").f().sx("parts").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(strPartHtmlCles()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			}
		} g("div");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:inscriptionCles">Trouver l'entité inscriptionCles dans Solr</a>
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
	public DesignInscription addInscriptionCles(Long...objets) {
		for(Long o : objets) {
			addInscriptionCles(o);
		}
		return (DesignInscription)this;
	}
	public DesignInscription addInscriptionCles(Long o) {
		if(o != null && !inscriptionCles.contains(o))
			this.inscriptionCles.add(o);
		return (DesignInscription)this;
	}
	public DesignInscription setInscriptionCles(JsonArray objets) {
		inscriptionCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addInscriptionCles(o);
		}
		return (DesignInscription)this;
	}
	public DesignInscription addInscriptionCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addInscriptionCles(p);
		}
		return (DesignInscription)this;
	}
	protected DesignInscription inscriptionClesInit() {
		if(!inscriptionClesCouverture.dejaInitialise) {
			_inscriptionCles(inscriptionCles);
		}
		inscriptionClesCouverture.dejaInitialise(true);
		return (DesignInscription)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeRecherche">Trouver l'entité anneeRecherche dans Solr</a>
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
	protected DesignInscription anneeRechercheInit() {
		if(!anneeRechercheCouverture.dejaInitialise) {
			_anneeRecherche(anneeRecherche);
		}
		anneeRecherche.initLoinPourClasse(requeteSite_);
		anneeRechercheCouverture.dejaInitialise(true);
		return (DesignInscription)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:annee_">Trouver l'entité annee_ dans Solr</a>
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
	protected DesignInscription annee_Init() {
		if(!annee_Couverture.dejaInitialise) {
			_annee_(annee_Couverture);
			if(annee_ == null)
				setAnnee_(annee_Couverture.o);
		}
		annee_Couverture.dejaInitialise(true);
		return (DesignInscription)this;
	}

	///////////////////////
	// partHtmlRecherche //
	///////////////////////

	/**	L'entité « partHtmlRecherche »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<PartHtml>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected ListeRecherche<PartHtml> partHtmlRecherche = new ListeRecherche<PartHtml>();
	@JsonIgnore
	public Couverture<ListeRecherche<PartHtml>> partHtmlRechercheCouverture = new Couverture<ListeRecherche<PartHtml>>().p(this).c(ListeRecherche.class).var("partHtmlRecherche").o(partHtmlRecherche);

	/**	<br/>L'entité « partHtmlRecherche »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ListeRecherche<PartHtml>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partHtmlRecherche">Trouver l'entité partHtmlRecherche dans Solr</a>
	 * <br/>
	 * @param partHtmlRecherche est l'entité déjà construit. 
	 **/
	protected abstract void _partHtmlRecherche(ListeRecherche<PartHtml> l);

	public ListeRecherche<PartHtml> getPartHtmlRecherche() {
		return partHtmlRecherche;
	}

	public void setPartHtmlRecherche(ListeRecherche<PartHtml> partHtmlRecherche) {
		this.partHtmlRecherche = partHtmlRecherche;
		this.partHtmlRechercheCouverture.dejaInitialise = true;
	}
	protected DesignInscription partHtmlRechercheInit() {
		if(!partHtmlRechercheCouverture.dejaInitialise) {
			_partHtmlRecherche(partHtmlRecherche);
		}
		partHtmlRecherche.initLoinPourClasse(requeteSite_);
		partHtmlRechercheCouverture.dejaInitialise(true);
		return (DesignInscription)this;
	}

	////////////////////
	// partHtmlListe_ //
	////////////////////

	/**	L'entité « partHtmlListe_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<PartHtml> partHtmlListe_;
	@JsonIgnore
	public Couverture<List<PartHtml>> partHtmlListe_Couverture = new Couverture<List<PartHtml>>().p(this).c(List.class).var("partHtmlListe_").o(partHtmlListe_);

	/**	<br/>L'entité « partHtmlListe_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partHtmlListe_">Trouver l'entité partHtmlListe_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _partHtmlListe_(Couverture<List<PartHtml>> c);

	public List<PartHtml> getPartHtmlListe_() {
		return partHtmlListe_;
	}

	public void setPartHtmlListe_(List<PartHtml> partHtmlListe_) {
		this.partHtmlListe_ = partHtmlListe_;
		this.partHtmlListe_Couverture.dejaInitialise = true;
	}
	public DesignInscription addPartHtmlListe_(PartHtml...objets) {
		for(PartHtml o : objets) {
			addPartHtmlListe_(o);
		}
		return (DesignInscription)this;
	}
	public DesignInscription addPartHtmlListe_(PartHtml o) {
		if(o != null && !partHtmlListe_.contains(o))
			this.partHtmlListe_.add(o);
		return (DesignInscription)this;
	}
	protected DesignInscription partHtmlListe_Init() {
		if(!partHtmlListe_Couverture.dejaInitialise) {
			_partHtmlListe_(partHtmlListe_Couverture);
			if(partHtmlListe_ == null)
				setPartHtmlListe_(partHtmlListe_Couverture.o);
		}
		partHtmlListe_Couverture.dejaInitialise(true);
		return (DesignInscription)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleCle">Trouver l'entité ecoleCle dans Solr</a>
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
	public DesignInscription setEcoleCle(String o) {
		if(NumberUtils.isParsable(o))
			this.ecoleCle = Long.parseLong(o);
		this.ecoleCleCouverture.dejaInitialise = true;
		return (DesignInscription)this;
	}
	protected DesignInscription ecoleCleInit() {
		if(!ecoleCleCouverture.dejaInitialise) {
			_ecoleCle(ecoleCleCouverture);
			if(ecoleCle == null)
				setEcoleCle(ecoleCleCouverture.o);
		}
		ecoleCleCouverture.dejaInitialise(true);
		return (DesignInscription)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleNomComplet">Trouver l'entité ecoleNomComplet dans Solr</a>
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
	protected DesignInscription ecoleNomCompletInit() {
		if(!ecoleNomCompletCouverture.dejaInitialise) {
			_ecoleNomComplet(ecoleNomCompletCouverture);
			if(ecoleNomComplet == null)
				setEcoleNomComplet(ecoleNomCompletCouverture.o);
		}
		ecoleNomCompletCouverture.dejaInitialise(true);
		return (DesignInscription)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:ecoleEmplacement">Trouver l'entité ecoleEmplacement dans Solr</a>
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
	protected DesignInscription ecoleEmplacementInit() {
		if(!ecoleEmplacementCouverture.dejaInitialise) {
			_ecoleEmplacement(ecoleEmplacementCouverture);
			if(ecoleEmplacement == null)
				setEcoleEmplacement(ecoleEmplacementCouverture.o);
		}
		ecoleEmplacementCouverture.dejaInitialise(true);
		return (DesignInscription)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeDebut">Trouver l'entité anneeDebut dans Solr</a>
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
	public DesignInscription setAnneeDebut(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeDebut = Integer.parseInt(o);
		this.anneeDebutCouverture.dejaInitialise = true;
		return (DesignInscription)this;
	}
	protected DesignInscription anneeDebutInit() {
		if(!anneeDebutCouverture.dejaInitialise) {
			_anneeDebut(anneeDebutCouverture);
			if(anneeDebut == null)
				setAnneeDebut(anneeDebutCouverture.o);
		}
		anneeDebutCouverture.dejaInitialise(true);
		return (DesignInscription)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeFin">Trouver l'entité anneeFin dans Solr</a>
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
	public DesignInscription setAnneeFin(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeFin = Integer.parseInt(o);
		this.anneeFinCouverture.dejaInitialise = true;
		return (DesignInscription)this;
	}
	protected DesignInscription anneeFinInit() {
		if(!anneeFinCouverture.dejaInitialise) {
			_anneeFin(anneeFinCouverture);
			if(anneeFin == null)
				setAnneeFin(anneeFinCouverture.o);
		}
		anneeFinCouverture.dejaInitialise(true);
		return (DesignInscription)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeNomCourt">Trouver l'entité anneeNomCourt dans Solr</a>
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
	protected DesignInscription anneeNomCourtInit() {
		if(!anneeNomCourtCouverture.dejaInitialise) {
			_anneeNomCourt(anneeNomCourtCouverture);
			if(anneeNomCourt == null)
				setAnneeNomCourt(anneeNomCourtCouverture.o);
		}
		anneeNomCourtCouverture.dejaInitialise(true);
		return (DesignInscription)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeNomComplet">Trouver l'entité anneeNomComplet dans Solr</a>
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
	protected DesignInscription anneeNomCompletInit() {
		if(!anneeNomCompletCouverture.dejaInitialise) {
			_anneeNomComplet(anneeNomCompletCouverture);
			if(anneeNomComplet == null)
				setAnneeNomComplet(anneeNomCompletCouverture.o);
		}
		anneeNomCompletCouverture.dejaInitialise(true);
		return (DesignInscription)this;
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

	/////////////////////////////////
	// designInscriptionNomComplet //
	/////////////////////////////////

	/**	L'entité « designInscriptionNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String designInscriptionNomComplet;
	@JsonIgnore
	public Couverture<String> designInscriptionNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("designInscriptionNomComplet").o(designInscriptionNomComplet);

	/**	<br/>L'entité « designInscriptionNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designInscriptionNomComplet">Trouver l'entité designInscriptionNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designInscriptionNomComplet(Couverture<String> c);

	public String getDesignInscriptionNomComplet() {
		return designInscriptionNomComplet;
	}

	public void setDesignInscriptionNomComplet(String designInscriptionNomComplet) {
		this.designInscriptionNomComplet = designInscriptionNomComplet;
		this.designInscriptionNomCompletCouverture.dejaInitialise = true;
	}
	protected DesignInscription designInscriptionNomCompletInit() {
		if(!designInscriptionNomCompletCouverture.dejaInitialise) {
			_designInscriptionNomComplet(designInscriptionNomCompletCouverture);
			if(designInscriptionNomComplet == null)
				setDesignInscriptionNomComplet(designInscriptionNomCompletCouverture.o);
		}
		designInscriptionNomCompletCouverture.dejaInitialise(true);
		return (DesignInscription)this;
	}

	public String solrDesignInscriptionNomComplet() {
		return designInscriptionNomComplet;
	}

	public String strDesignInscriptionNomComplet() {
		return designInscriptionNomComplet == null ? "" : designInscriptionNomComplet;
	}

	public String jsonDesignInscriptionNomComplet() {
		return designInscriptionNomComplet == null ? "" : designInscriptionNomComplet;
	}

	public String nomAffichageDesignInscriptionNomComplet() {
		return "nom";
	}

	public String htmTooltipDesignInscriptionNomComplet() {
		return null;
	}

	public String htmDesignInscriptionNomComplet() {
		return designInscriptionNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strDesignInscriptionNomComplet());
	}

	public void inputDesignInscriptionNomComplet(String classeApiMethodeMethode) {
		DesignInscription s = (DesignInscription)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "nom")
				.a("id", classeApiMethodeMethode, "_designInscriptionNomComplet");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setDesignInscriptionNomComplet classDesignInscription inputDesignInscription", pk, "DesignInscriptionNomComplet w3-input w3-border ");
					a("name", "setDesignInscriptionNomComplet");
				} else {
					a("class", "valeurDesignInscriptionNomComplet w3-input w3-border classDesignInscription inputDesignInscription", pk, "DesignInscriptionNomComplet w3-input w3-border ");
					a("name", "designInscriptionNomComplet");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchDesignInscriptionVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignInscriptionNomComplet', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designInscriptionNomComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designInscriptionNomComplet')); }); ");
				}
				a("value", strDesignInscriptionNomComplet())
			.fg();

		}
	}

	public void htmDesignInscriptionNomComplet(String classeApiMethodeMethode) {
		DesignInscription s = (DesignInscription)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignInscriptionDesignInscriptionNomComplet").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_designInscriptionNomComplet").a("class", "").f().sx("nom").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignInscriptionNomComplet(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-khaki ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_designInscriptionNomComplet')); $('#", classeApiMethodeMethode, "_designInscriptionNomComplet').val(null); patchDesignInscriptionVal([{ name: 'fq', value: 'pk:' + $('#DesignInscriptionForm :input[name=pk]').val() }], 'setDesignInscriptionNomComplet', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_designInscriptionNomComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designInscriptionNomComplet')); }); ")
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
	// designCache //
	/////////////////

	/**	L'entité « designCache »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designCache;
	@JsonIgnore
	public Couverture<Boolean> designCacheCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("designCache").o(designCache);

	/**	<br/>L'entité « designCache »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.inscription.design.DesignInscription&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designCache">Trouver l'entité designCache dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designCache(Couverture<Boolean> c);

	public Boolean getDesignCache() {
		return designCache;
	}

	public void setDesignCache(Boolean designCache) {
		this.designCache = designCache;
		this.designCacheCouverture.dejaInitialise = true;
	}
	public DesignInscription setDesignCache(String o) {
		this.designCache = Boolean.parseBoolean(o);
		this.designCacheCouverture.dejaInitialise = true;
		return (DesignInscription)this;
	}
	protected DesignInscription designCacheInit() {
		if(!designCacheCouverture.dejaInitialise) {
			_designCache(designCacheCouverture);
			if(designCache == null)
				setDesignCache(designCacheCouverture.o);
		}
		designCacheCouverture.dejaInitialise(true);
		return (DesignInscription)this;
	}

	public Boolean solrDesignCache() {
		return designCache;
	}

	public String strDesignCache() {
		return designCache == null ? "" : designCache.toString();
	}

	public String jsonDesignCache() {
		return designCache == null ? "" : designCache.toString();
	}

	public String nomAffichageDesignCache() {
		return "caché";
	}

	public String htmTooltipDesignCache() {
		return null;
	}

	public String htmDesignCache() {
		return designCache == null ? "" : StringEscapeUtils.escapeHtml4(strDesignCache());
	}

	public void inputDesignCache(String classeApiMethodeMethode) {
		DesignInscription s = (DesignInscription)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_designCache")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_designCache");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setDesignCache classDesignInscription inputDesignInscription", pk, "DesignCache w3-input w3-border ");
				a("name", "setDesignCache");
			} else {
				a("class", "valeurDesignCache classDesignInscription inputDesignInscription", pk, "DesignCache w3-input w3-border ");
				a("name", "designCache");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patchDesignInscriptionVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignCache', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designCache')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designCache')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getDesignCache() != null && getDesignCache())
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

	public void htmDesignCache(String classeApiMethodeMethode) {
		DesignInscription s = (DesignInscription)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignInscriptionDesignCache").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_designCache").a("class", "").f().sx("caché").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignCache(classeApiMethodeMethode);
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

	protected boolean dejaInitialiseDesignInscription = false;

	public DesignInscription initLoinDesignInscription(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseDesignInscription) {
			dejaInitialiseDesignInscription = true;
			initLoinDesignInscription();
		}
		return (DesignInscription)this;
	}

	public void initLoinDesignInscription() {
		initDesignInscription();
		super.initLoinCluster(requeteSite_);
	}

	public void initDesignInscription() {
		designInscriptionCleInit();
		anneeCleInit();
		partHtmlClesInit();
		inscriptionClesInit();
		anneeRechercheInit();
		annee_Init();
		partHtmlRechercheInit();
		partHtmlListe_Init();
		ecoleCleInit();
		ecoleNomCompletInit();
		ecoleEmplacementInit();
		anneeDebutInit();
		anneeFinInit();
		anneeNomCourtInit();
		anneeNomCompletInit();
		designInscriptionNomCompletInit();
		designCacheInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinDesignInscription(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteDesignInscription(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(anneeRecherche != null)
			anneeRecherche.setRequeteSite_(requeteSite_);
		if(partHtmlRecherche != null)
			partHtmlRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteDesignInscription(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirDesignInscription(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirDesignInscription(String var) {
		DesignInscription oDesignInscription = (DesignInscription)this;
		switch(var) {
			case "designInscriptionCle":
				return oDesignInscription.designInscriptionCle;
			case "anneeCle":
				return oDesignInscription.anneeCle;
			case "partHtmlCles":
				return oDesignInscription.partHtmlCles;
			case "inscriptionCles":
				return oDesignInscription.inscriptionCles;
			case "anneeRecherche":
				return oDesignInscription.anneeRecherche;
			case "annee_":
				return oDesignInscription.annee_;
			case "partHtmlRecherche":
				return oDesignInscription.partHtmlRecherche;
			case "partHtmlListe_":
				return oDesignInscription.partHtmlListe_;
			case "ecoleCle":
				return oDesignInscription.ecoleCle;
			case "ecoleNomComplet":
				return oDesignInscription.ecoleNomComplet;
			case "ecoleEmplacement":
				return oDesignInscription.ecoleEmplacement;
			case "anneeDebut":
				return oDesignInscription.anneeDebut;
			case "anneeFin":
				return oDesignInscription.anneeFin;
			case "anneeNomCourt":
				return oDesignInscription.anneeNomCourt;
			case "anneeNomComplet":
				return oDesignInscription.anneeNomComplet;
			case "designInscriptionNomComplet":
				return oDesignInscription.designInscriptionNomComplet;
			case "designCache":
				return oDesignInscription.designCache;
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
				o = attribuerDesignInscription(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerDesignInscription(String var, Object val) {
		DesignInscription oDesignInscription = (DesignInscription)this;
		switch(var) {
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
					o = definirDesignInscription(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirDesignInscription(String var, String val) {
		switch(var) {
			case "designInscriptionNomComplet":
				if(val != null)
					setDesignInscriptionNomComplet(val);
				sauvegardesDesignInscription.add(var);
				return val;
			case "designCache":
				if(val != null)
					setDesignCache(val);
				sauvegardesDesignInscription.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesDesignInscription = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerDesignInscription(solrDocument);
	}
	public void peuplerDesignInscription(SolrDocument solrDocument) {
		DesignInscription oDesignInscription = (DesignInscription)this;
		sauvegardesDesignInscription = (List<String>)solrDocument.get("sauvegardesDesignInscription_stored_strings");
		if(sauvegardesDesignInscription != null) {

			if(sauvegardesDesignInscription.contains("designInscriptionCle")) {
				Long designInscriptionCle = (Long)solrDocument.get("designInscriptionCle_stored_long");
				if(designInscriptionCle != null)
					oDesignInscription.setDesignInscriptionCle(designInscriptionCle);
			}

			if(sauvegardesDesignInscription.contains("anneeCle")) {
				Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
				if(anneeCle != null)
					oDesignInscription.setAnneeCle(anneeCle);
			}

			if(sauvegardesDesignInscription.contains("partHtmlCles")) {
				List<Long> partHtmlCles = (List<Long>)solrDocument.get("partHtmlCles_stored_longs");
				if(partHtmlCles != null)
					oDesignInscription.partHtmlCles.addAll(partHtmlCles);
			}

			if(sauvegardesDesignInscription.contains("inscriptionCles")) {
				List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
				if(inscriptionCles != null)
					oDesignInscription.inscriptionCles.addAll(inscriptionCles);
			}

			if(sauvegardesDesignInscription.contains("ecoleCle")) {
				Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
				if(ecoleCle != null)
					oDesignInscription.setEcoleCle(ecoleCle);
			}

			if(sauvegardesDesignInscription.contains("ecoleNomComplet")) {
				String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
				if(ecoleNomComplet != null)
					oDesignInscription.setEcoleNomComplet(ecoleNomComplet);
			}

			if(sauvegardesDesignInscription.contains("ecoleEmplacement")) {
				String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
				if(ecoleEmplacement != null)
					oDesignInscription.setEcoleEmplacement(ecoleEmplacement);
			}

			if(sauvegardesDesignInscription.contains("anneeDebut")) {
				Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
				if(anneeDebut != null)
					oDesignInscription.setAnneeDebut(anneeDebut);
			}

			if(sauvegardesDesignInscription.contains("anneeFin")) {
				Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
				if(anneeFin != null)
					oDesignInscription.setAnneeFin(anneeFin);
			}

			if(sauvegardesDesignInscription.contains("anneeNomCourt")) {
				String anneeNomCourt = (String)solrDocument.get("anneeNomCourt_stored_string");
				if(anneeNomCourt != null)
					oDesignInscription.setAnneeNomCourt(anneeNomCourt);
			}

			if(sauvegardesDesignInscription.contains("anneeNomComplet")) {
				String anneeNomComplet = (String)solrDocument.get("anneeNomComplet_stored_string");
				if(anneeNomComplet != null)
					oDesignInscription.setAnneeNomComplet(anneeNomComplet);
			}

			if(sauvegardesDesignInscription.contains("designInscriptionNomComplet")) {
				String designInscriptionNomComplet = (String)solrDocument.get("designInscriptionNomComplet_stored_string");
				if(designInscriptionNomComplet != null)
					oDesignInscription.setDesignInscriptionNomComplet(designInscriptionNomComplet);
			}

			if(sauvegardesDesignInscription.contains("designCache")) {
				Boolean designCache = (Boolean)solrDocument.get("designCache_stored_boolean");
				if(designCache != null)
					oDesignInscription.setDesignCache(designCache);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.inscription.design.DesignInscription"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			DesignInscription o = new DesignInscription();
			o.requeteSiteDesignInscription(requeteSite);
			o.initLoinDesignInscription(requeteSite);
			o.indexerDesignInscription();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerDesignInscription();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerDesignInscription(document);
	}

	public void indexerDesignInscription(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerDesignInscription(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerDesignInscription() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerDesignInscription(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerDesignInscription(SolrInputDocument document) {
		if(sauvegardesDesignInscription != null)
			document.addField("sauvegardesDesignInscription_stored_strings", sauvegardesDesignInscription);

		if(designInscriptionCle != null) {
			document.addField("designInscriptionCle_indexed_long", designInscriptionCle);
			document.addField("designInscriptionCle_stored_long", designInscriptionCle);
		}
		if(anneeCle != null) {
			document.addField("anneeCle_indexed_long", anneeCle);
			document.addField("anneeCle_stored_long", anneeCle);
		}
		if(partHtmlCles != null) {
			for(java.lang.Long o : partHtmlCles) {
				document.addField("partHtmlCles_indexed_longs", o);
			}
			for(java.lang.Long o : partHtmlCles) {
				document.addField("partHtmlCles_stored_longs", o);
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
		if(ecoleCle != null) {
			document.addField("ecoleCle_indexed_long", ecoleCle);
			document.addField("ecoleCle_stored_long", ecoleCle);
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
		if(anneeNomCourt != null) {
			document.addField("anneeNomCourt_indexed_string", anneeNomCourt);
			document.addField("anneeNomCourt_stored_string", anneeNomCourt);
		}
		if(anneeNomComplet != null) {
			document.addField("anneeNomComplet_indexed_string", anneeNomComplet);
			document.addField("anneeNomComplet_stored_string", anneeNomComplet);
		}
		if(designInscriptionNomComplet != null) {
			document.addField("designInscriptionNomComplet_indexed_string", designInscriptionNomComplet);
			document.addField("designInscriptionNomComplet_stored_string", designInscriptionNomComplet);
		}
		if(designCache != null) {
			document.addField("designCache_indexed_boolean", designCache);
			document.addField("designCache_stored_boolean", designCache);
		}
		super.indexerCluster(document);

	}

	public void desindexerDesignInscription() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinDesignInscription(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeDesignInscription(String entiteVar) {
		switch(entiteVar) {
			case "designInscriptionCle":
				return "designInscriptionCle_indexed_long";
			case "anneeCle":
				return "anneeCle_indexed_long";
			case "partHtmlCles":
				return "partHtmlCles_indexed_longs";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "ecoleCle":
				return "ecoleCle_indexed_long";
			case "ecoleNomComplet":
				return "ecoleNomComplet_indexed_string";
			case "ecoleEmplacement":
				return "ecoleEmplacement_indexed_string";
			case "anneeDebut":
				return "anneeDebut_indexed_int";
			case "anneeFin":
				return "anneeFin_indexed_int";
			case "anneeNomCourt":
				return "anneeNomCourt_indexed_string";
			case "anneeNomComplet":
				return "anneeNomComplet_indexed_string";
			case "designInscriptionNomComplet":
				return "designInscriptionNomComplet_indexed_string";
			case "designCache":
				return "designCache_indexed_boolean";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheDesignInscription(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereDesignInscription(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerDesignInscription(solrDocument);
	}
	public void stockerDesignInscription(SolrDocument solrDocument) {
		DesignInscription oDesignInscription = (DesignInscription)this;

		Long designInscriptionCle = (Long)solrDocument.get("designInscriptionCle_stored_long");
		if(designInscriptionCle != null)
			oDesignInscription.setDesignInscriptionCle(designInscriptionCle);

		Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
		if(anneeCle != null)
			oDesignInscription.setAnneeCle(anneeCle);

		List<Long> partHtmlCles = (List<Long>)solrDocument.get("partHtmlCles_stored_longs");
		if(partHtmlCles != null)
			oDesignInscription.partHtmlCles.addAll(partHtmlCles);

		List<Long> inscriptionCles = (List<Long>)solrDocument.get("inscriptionCles_stored_longs");
		if(inscriptionCles != null)
			oDesignInscription.inscriptionCles.addAll(inscriptionCles);

		Long ecoleCle = (Long)solrDocument.get("ecoleCle_stored_long");
		if(ecoleCle != null)
			oDesignInscription.setEcoleCle(ecoleCle);

		String ecoleNomComplet = (String)solrDocument.get("ecoleNomComplet_stored_string");
		if(ecoleNomComplet != null)
			oDesignInscription.setEcoleNomComplet(ecoleNomComplet);

		String ecoleEmplacement = (String)solrDocument.get("ecoleEmplacement_stored_string");
		if(ecoleEmplacement != null)
			oDesignInscription.setEcoleEmplacement(ecoleEmplacement);

		Integer anneeDebut = (Integer)solrDocument.get("anneeDebut_stored_int");
		if(anneeDebut != null)
			oDesignInscription.setAnneeDebut(anneeDebut);

		Integer anneeFin = (Integer)solrDocument.get("anneeFin_stored_int");
		if(anneeFin != null)
			oDesignInscription.setAnneeFin(anneeFin);

		String anneeNomCourt = (String)solrDocument.get("anneeNomCourt_stored_string");
		if(anneeNomCourt != null)
			oDesignInscription.setAnneeNomCourt(anneeNomCourt);

		String anneeNomComplet = (String)solrDocument.get("anneeNomComplet_stored_string");
		if(anneeNomComplet != null)
			oDesignInscription.setAnneeNomComplet(anneeNomComplet);

		String designInscriptionNomComplet = (String)solrDocument.get("designInscriptionNomComplet_stored_string");
		if(designInscriptionNomComplet != null)
			oDesignInscription.setDesignInscriptionNomComplet(designInscriptionNomComplet);

		Boolean designCache = (Boolean)solrDocument.get("designCache_stored_boolean");
		if(designCache != null)
			oDesignInscription.setDesignCache(designCache);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiDesignInscription() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof DesignInscription) {
			DesignInscription original = (DesignInscription)o;
			if(!Objects.equals(designInscriptionNomComplet, original.getDesignInscriptionNomComplet()))
				requeteApi.addVars("designInscriptionNomComplet");
			if(!Objects.equals(designCache, original.getDesignCache()))
				requeteApi.addVars("designCache");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), designInscriptionNomComplet, designCache);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof DesignInscription))
			return false;
		DesignInscription that = (DesignInscription)o;
		return super.equals(o)
				&& Objects.equals( designInscriptionNomComplet, that.designInscriptionNomComplet )
				&& Objects.equals( designCache, that.designCache );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DesignInscription { ");
		sb.append( "designInscriptionNomComplet: \"" ).append(designInscriptionNomComplet).append( "\"" );
		sb.append( ", designCache: " ).append(designCache);
		sb.append(" }");
		return sb.toString();
	}
}
