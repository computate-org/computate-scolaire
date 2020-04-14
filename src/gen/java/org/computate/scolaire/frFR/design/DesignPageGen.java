package org.computate.scolaire.frFR.design;

import java.util.Arrays;
import org.computate.scolaire.frFR.html.part.PartHtml;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.frFR.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe designHidden dans Solr</a>
 * <br/>
 **/
public abstract class DesignPageGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(DesignPage.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String DesignPage_UnNom = "un design de page";
	public static final String DesignPage_Ce = "ce ";
	public static final String DesignPage_CeNom = "ce design de page";
	public static final String DesignPage_Un = "un ";
	public static final String DesignPage_LeNom = "le design de page";
	public static final String DesignPage_NomSingulier = "design de page";
	public static final String DesignPage_NomPluriel = "design de pages";
	public static final String DesignPage_NomActuel = "design de page actuel";
	public static final String DesignPage_Tous = "all ";
	public static final String DesignPage_TousNom = "tous les design de pages";
	public static final String DesignPage_RechercherTousNomPar = "rechercher design de pages par ";
	public static final String DesignPage_RechercherTousNom = "rechercher design de pages";
	public static final String DesignPage_LesNom = "les design de pages";
	public static final String DesignPage_AucunNomTrouve = "aucun design de page trouvé";
	public static final String DesignPage_NomVar = "design-page";
	public static final String DesignPage_DeNom = "de design de page";
	public static final String DesignPage_NomAdjectifSingulier = "design de page";
	public static final String DesignPage_NomAdjectifPluriel = "design de pages";
	public static final String DesignPage_Couleur = "khaki";
	public static final String DesignPage_IconeGroupe = "regular";
	public static final String DesignPage_IconeNom = "drafting-compass";

	///////////////////
	// designPageCle //
	///////////////////

	/**	L'entité « designPageCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long designPageCle;
	@JsonIgnore
	public Couverture<Long> designPageCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("designPageCle").o(designPageCle);

	/**	<br/>L'entité « designPageCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designPageCle">Trouver l'entité designPageCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designPageCle(Couverture<Long> c);

	public Long getDesignPageCle() {
		return designPageCle;
	}

	public void setDesignPageCle(Long designPageCle) {
		this.designPageCle = designPageCle;
		this.designPageCleCouverture.dejaInitialise = true;
	}
	public DesignPage setDesignPageCle(String o) {
		if(NumberUtils.isParsable(o))
			this.designPageCle = Long.parseLong(o);
		this.designPageCleCouverture.dejaInitialise = true;
		return (DesignPage)this;
	}
	protected DesignPage designPageCleInit() {
		if(!designPageCleCouverture.dejaInitialise) {
			_designPageCle(designPageCleCouverture);
			if(designPageCle == null)
				setDesignPageCle(designPageCleCouverture.o);
		}
		designPageCleCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public Long solrDesignPageCle() {
		return designPageCle;
	}

	public String strDesignPageCle() {
		return designPageCle == null ? "" : designPageCle.toString();
	}

	public String jsonDesignPageCle() {
		return designPageCle == null ? "" : designPageCle.toString();
	}

	public String nomAffichageDesignPageCle() {
		return "clé";
	}

	public String htmTooltipDesignPageCle() {
		return null;
	}

	public String htmDesignPageCle() {
		return designPageCle == null ? "" : StringEscapeUtils.escapeHtml4(strDesignPageCle());
	}

	//////////////////////
	// designEnfantCles //
	//////////////////////

	/**	L'entité « designEnfantCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> designEnfantCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> designEnfantClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("designEnfantCles").o(designEnfantCles);

	/**	<br/>L'entité « designEnfantCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designEnfantCles">Trouver l'entité designEnfantCles dans Solr</a>
	 * <br/>
	 * @param designEnfantCles est l'entité déjà construit. 
	 **/
	protected abstract void _designEnfantCles(List<Long> c);

	public List<Long> getDesignEnfantCles() {
		return designEnfantCles;
	}

	public void setDesignEnfantCles(List<Long> designEnfantCles) {
		this.designEnfantCles = designEnfantCles;
		this.designEnfantClesCouverture.dejaInitialise = true;
	}
	public DesignPage addDesignEnfantCles(Long...objets) {
		for(Long o : objets) {
			addDesignEnfantCles(o);
		}
		return (DesignPage)this;
	}
	public DesignPage addDesignEnfantCles(Long o) {
		if(o != null && !designEnfantCles.contains(o))
			this.designEnfantCles.add(o);
		return (DesignPage)this;
	}
	public DesignPage setDesignEnfantCles(JsonArray objets) {
		designEnfantCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addDesignEnfantCles(o);
		}
		return (DesignPage)this;
	}
	public DesignPage addDesignEnfantCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addDesignEnfantCles(p);
		}
		return (DesignPage)this;
	}
	protected DesignPage designEnfantClesInit() {
		if(!designEnfantClesCouverture.dejaInitialise) {
			_designEnfantCles(designEnfantCles);
		}
		designEnfantClesCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public List<Long> solrDesignEnfantCles() {
		return designEnfantCles;
	}

	public String strDesignEnfantCles() {
		return designEnfantCles == null ? "" : designEnfantCles.toString();
	}

	public String jsonDesignEnfantCles() {
		return designEnfantCles == null ? "" : designEnfantCles.toString();
	}

	public String nomAffichageDesignEnfantCles() {
		return "designs d'enfant";
	}

	public String htmTooltipDesignEnfantCles() {
		return null;
	}

	public String htmDesignEnfantCles() {
		return designEnfantCles == null ? "" : StringEscapeUtils.escapeHtml4(strDesignEnfantCles());
	}

	//////////////////////
	// designParentCles //
	//////////////////////

	/**	L'entité « designParentCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> designParentCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> designParentClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("designParentCles").o(designParentCles);

	/**	<br/>L'entité « designParentCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designParentCles">Trouver l'entité designParentCles dans Solr</a>
	 * <br/>
	 * @param designParentCles est l'entité déjà construit. 
	 **/
	protected abstract void _designParentCles(List<Long> c);

	public List<Long> getDesignParentCles() {
		return designParentCles;
	}

	public void setDesignParentCles(List<Long> designParentCles) {
		this.designParentCles = designParentCles;
		this.designParentClesCouverture.dejaInitialise = true;
	}
	public DesignPage addDesignParentCles(Long...objets) {
		for(Long o : objets) {
			addDesignParentCles(o);
		}
		return (DesignPage)this;
	}
	public DesignPage addDesignParentCles(Long o) {
		if(o != null && !designParentCles.contains(o))
			this.designParentCles.add(o);
		return (DesignPage)this;
	}
	public DesignPage setDesignParentCles(JsonArray objets) {
		designParentCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addDesignParentCles(o);
		}
		return (DesignPage)this;
	}
	public DesignPage addDesignParentCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addDesignParentCles(p);
		}
		return (DesignPage)this;
	}
	protected DesignPage designParentClesInit() {
		if(!designParentClesCouverture.dejaInitialise) {
			_designParentCles(designParentCles);
		}
		designParentClesCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public List<Long> solrDesignParentCles() {
		return designParentCles;
	}

	public String strDesignParentCles() {
		return designParentCles == null ? "" : designParentCles.toString();
	}

	public String jsonDesignParentCles() {
		return designParentCles == null ? "" : designParentCles.toString();
	}

	public String nomAffichageDesignParentCles() {
		return "designs parent";
	}

	public String htmTooltipDesignParentCles() {
		return null;
	}

	public String htmDesignParentCles() {
		return designParentCles == null ? "" : StringEscapeUtils.escapeHtml4(strDesignParentCles());
	}

	public void inputDesignParentCles(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "designs parent")
					.a("class", "valeur suggereDesignParentCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setDesignParentCles")
					.a("id", classeApiMethodeMethode, "_designParentCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereDesignPageDesignParentCles($(this).val() ? rechercherDesignPageFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'designEnfantCles:" + pk + "'}", "], $('#listDesignPageDesignParentCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			sx(htmDesignParentCles());
		}
	}

	public void htmDesignParentCles(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignParentCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=designEnfantCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-khaki w3-hover-khaki ").f();
								e("i").a("class", "far fa-drafting-compass ").f().g("i");
								sx("designs parent");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a ce design de page");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputDesignParentCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listDesignPageDesignParentCles_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
											.a("onclick", "postDesignPageVals({ designEnfantCles: [ \"", pk, "\" ] }, function() { patchDesignPageVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "designParentCles')); });")
											.f().sx("ajouter un design de page")
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partHtmlCles">Trouver l'entité partHtmlCles dans Solr</a>
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
	public DesignPage addPartHtmlCles(Long...objets) {
		for(Long o : objets) {
			addPartHtmlCles(o);
		}
		return (DesignPage)this;
	}
	public DesignPage addPartHtmlCles(Long o) {
		if(o != null && !partHtmlCles.contains(o))
			this.partHtmlCles.add(o);
		return (DesignPage)this;
	}
	public DesignPage setPartHtmlCles(JsonArray objets) {
		partHtmlCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPartHtmlCles(o);
		}
		return (DesignPage)this;
	}
	public DesignPage addPartHtmlCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addPartHtmlCles(p);
		}
		return (DesignPage)this;
	}
	protected DesignPage partHtmlClesInit() {
		if(!partHtmlClesCouverture.dejaInitialise) {
			_partHtmlCles(partHtmlCles);
		}
		partHtmlClesCouverture.dejaInitialise(true);
		return (DesignPage)this;
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
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "parts")
					.a("class", "valeur suggerePartHtmlCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setPartHtmlCles")
					.a("id", classeApiMethodeMethode, "_partHtmlCles")
					.a("autocomplete", "off")
					.a("oninput", "suggereDesignPagePartHtmlCles($(this).val() ? rechercherPartHtmlFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'designPageCles:" + pk + "'}", "], $('#listDesignPagePartHtmlCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		} else {
			sx(htmPartHtmlCles());
		}
	}

	public void htmPartHtmlCles(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPagePartHtmlCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=designPageCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
								e("i").a("class", "far fa-sun ").f().g("i");
								sx("parts");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a ce design de page");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputPartHtmlCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listDesignPagePartHtmlCles_", classeApiMethodeMethode).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
											.a("onclick", "postPartHtmlVals({ designPageCles: [ \"", pk, "\" ] }, function() { patchDesignPageVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "partHtmlCles')); });")
											.f().sx("ajouter un part de HTML")
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partHtmlRecherche">Trouver l'entité partHtmlRecherche dans Solr</a>
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
	protected DesignPage partHtmlRechercheInit() {
		if(!partHtmlRechercheCouverture.dejaInitialise) {
			_partHtmlRecherche(partHtmlRecherche);
		}
		partHtmlRecherche.initLoinPourClasse(requeteSite_);
		partHtmlRechercheCouverture.dejaInitialise(true);
		return (DesignPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partHtmlListe_">Trouver l'entité partHtmlListe_ dans Solr</a>
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
	public DesignPage addPartHtmlListe_(PartHtml...objets) {
		for(PartHtml o : objets) {
			addPartHtmlListe_(o);
		}
		return (DesignPage)this;
	}
	public DesignPage addPartHtmlListe_(PartHtml o) {
		if(o != null && !partHtmlListe_.contains(o))
			this.partHtmlListe_.add(o);
		return (DesignPage)this;
	}
	protected DesignPage partHtmlListe_Init() {
		if(!partHtmlListe_Couverture.dejaInitialise) {
			_partHtmlListe_(partHtmlListe_Couverture);
			if(partHtmlListe_ == null)
				setPartHtmlListe_(partHtmlListe_Couverture.o);
		}
		partHtmlListe_Couverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	//////////////////////////
	// designPageNomComplet //
	//////////////////////////

	/**	L'entité « designPageNomComplet »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected String designPageNomComplet;
	@JsonIgnore
	public Couverture<String> designPageNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("designPageNomComplet").o(designPageNomComplet);

	/**	<br/>L'entité « designPageNomComplet »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designPageNomComplet">Trouver l'entité designPageNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designPageNomComplet(Couverture<String> c);

	public String getDesignPageNomComplet() {
		return designPageNomComplet;
	}

	public void setDesignPageNomComplet(String designPageNomComplet) {
		this.designPageNomComplet = designPageNomComplet;
		this.designPageNomCompletCouverture.dejaInitialise = true;
	}
	protected DesignPage designPageNomCompletInit() {
		if(!designPageNomCompletCouverture.dejaInitialise) {
			_designPageNomComplet(designPageNomCompletCouverture);
			if(designPageNomComplet == null)
				setDesignPageNomComplet(designPageNomCompletCouverture.o);
		}
		designPageNomCompletCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public String solrDesignPageNomComplet() {
		return designPageNomComplet;
	}

	public String strDesignPageNomComplet() {
		return designPageNomComplet == null ? "" : designPageNomComplet;
	}

	public String jsonDesignPageNomComplet() {
		return designPageNomComplet == null ? "" : designPageNomComplet;
	}

	public String nomAffichageDesignPageNomComplet() {
		return "nom";
	}

	public String htmTooltipDesignPageNomComplet() {
		return null;
	}

	public String htmDesignPageNomComplet() {
		return designPageNomComplet == null ? "" : StringEscapeUtils.escapeHtml4(strDesignPageNomComplet());
	}

	public void inputDesignPageNomComplet(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "nom")
				.a("id", classeApiMethodeMethode, "_designPageNomComplet");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setDesignPageNomComplet inputDesignPage", pk, "DesignPageNomComplet w3-input w3-border ");
					a("name", "setDesignPageNomComplet");
				} else {
					a("class", "valeurDesignPageNomComplet w3-input w3-border inputDesignPage", pk, "DesignPageNomComplet w3-input w3-border ");
					a("name", "designPageNomComplet");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchDesignPageVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignPageNomComplet', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designPageNomComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designPageNomComplet')); }); ");
				}
				a("value", strDesignPageNomComplet())
			.fg();

		} else {
			sx(htmDesignPageNomComplet());
		}
	}

	public void htmDesignPageNomComplet(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignPageNomComplet").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_designPageNomComplet").a("class", "").f().sx("nom").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignPageNomComplet(classeApiMethodeMethode);
							} g("div");
								if(
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
										) {
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-khaki ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_designPageNomComplet')); $('#", classeApiMethodeMethode, "_designPageNomComplet').val(null); patchDesignPageVal([{ name: 'fq', value: 'pk:' + $('#DesignPageForm :input[name=pk]').val() }], 'setDesignPageNomComplet', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_designPageNomComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designPageNomComplet')); }); ")
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
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Boolean designCache;
	@JsonIgnore
	public Couverture<Boolean> designCacheCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("designCache").o(designCache);

	/**	<br/>L'entité « designCache »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designCache">Trouver l'entité designCache dans Solr</a>
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
	public DesignPage setDesignCache(String o) {
		this.designCache = Boolean.parseBoolean(o);
		this.designCacheCouverture.dejaInitialise = true;
		return (DesignPage)this;
	}
	protected DesignPage designCacheInit() {
		if(!designCacheCouverture.dejaInitialise) {
			_designCache(designCacheCouverture);
			if(designCache == null)
				setDesignCache(designCacheCouverture.o);
		}
		designCacheCouverture.dejaInitialise(true);
		return (DesignPage)this;
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
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
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
				a("class", "setDesignCache inputDesignPage", pk, "DesignCache w3-input w3-border ");
				a("name", "setDesignCache");
			} else {
				a("class", "valeurDesignCache inputDesignPage", pk, "DesignCache w3-input w3-border ");
				a("name", "designCache");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patchDesignPageVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignCache', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designCache')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designCache')); }); ");
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

		} else {
			sx(htmDesignCache());
		}
	}

	public void htmDesignCache(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignCache").f();
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

	protected boolean dejaInitialiseDesignPage = false;

	public DesignPage initLoinDesignPage(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseDesignPage) {
			dejaInitialiseDesignPage = true;
			initLoinDesignPage();
		}
		return (DesignPage)this;
	}

	public void initLoinDesignPage() {
		initDesignPage();
		super.initLoinCluster(requeteSite_);
	}

	public void initDesignPage() {
		designPageCleInit();
		designEnfantClesInit();
		designParentClesInit();
		partHtmlClesInit();
		partHtmlRechercheInit();
		partHtmlListe_Init();
		designPageNomCompletInit();
		designCacheInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinDesignPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteDesignPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
		if(partHtmlRecherche != null)
			partHtmlRecherche.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteDesignPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirDesignPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirDesignPage(String var) {
		DesignPage oDesignPage = (DesignPage)this;
		switch(var) {
			case "designPageCle":
				return oDesignPage.designPageCle;
			case "designEnfantCles":
				return oDesignPage.designEnfantCles;
			case "designParentCles":
				return oDesignPage.designParentCles;
			case "partHtmlCles":
				return oDesignPage.partHtmlCles;
			case "partHtmlRecherche":
				return oDesignPage.partHtmlRecherche;
			case "partHtmlListe_":
				return oDesignPage.partHtmlListe_;
			case "designPageNomComplet":
				return oDesignPage.designPageNomComplet;
			case "designCache":
				return oDesignPage.designCache;
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
				o = attribuerDesignPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerDesignPage(String var, Object val) {
		DesignPage oDesignPage = (DesignPage)this;
		switch(var) {
			case "designEnfantCles":
				oDesignPage.addDesignEnfantCles((Long)val);
				return val;
			case "designParentCles":
				oDesignPage.addDesignParentCles((Long)val);
				return val;
			case "partHtmlCles":
				oDesignPage.addPartHtmlCles((Long)val);
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
					o = definirDesignPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirDesignPage(String var, String val) {
		switch(var) {
			case "designPageNomComplet":
				setDesignPageNomComplet(val);
				sauvegardesDesignPage.add(var);
				return val;
			case "designCache":
				setDesignCache(val);
				sauvegardesDesignPage.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesDesignPage = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerDesignPage(solrDocument);
	}
	public void peuplerDesignPage(SolrDocument solrDocument) {
		DesignPage oDesignPage = (DesignPage)this;
		sauvegardesDesignPage = (List<String>)solrDocument.get("sauvegardesDesignPage_stored_strings");
		if(sauvegardesDesignPage != null) {

			if(sauvegardesDesignPage.contains("designPageCle")) {
				Long designPageCle = (Long)solrDocument.get("designPageCle_stored_long");
				if(designPageCle != null)
					oDesignPage.setDesignPageCle(designPageCle);
			}

			List<Long> designEnfantCles = (List<Long>)solrDocument.get("designEnfantCles_stored_longs");
			if(designEnfantCles != null)
				oDesignPage.designEnfantCles.addAll(designEnfantCles);

			List<Long> designParentCles = (List<Long>)solrDocument.get("designParentCles_stored_longs");
			if(designParentCles != null)
				oDesignPage.designParentCles.addAll(designParentCles);

			List<Long> partHtmlCles = (List<Long>)solrDocument.get("partHtmlCles_stored_longs");
			if(partHtmlCles != null)
				oDesignPage.partHtmlCles.addAll(partHtmlCles);

			if(sauvegardesDesignPage.contains("designPageNomComplet")) {
				String designPageNomComplet = (String)solrDocument.get("designPageNomComplet_stored_string");
				if(designPageNomComplet != null)
					oDesignPage.setDesignPageNomComplet(designPageNomComplet);
			}

			if(sauvegardesDesignPage.contains("designCache")) {
				Boolean designCache = (Boolean)solrDocument.get("designCache_stored_boolean");
				if(designCache != null)
					oDesignPage.setDesignCache(designCache);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.design.DesignPage"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			DesignPage o = new DesignPage();
			o.requeteSiteDesignPage(requeteSite);
			o.initLoinDesignPage(requeteSite);
			o.indexerDesignPage();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerDesignPage();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerDesignPage(document);
	}

	public void indexerDesignPage(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerDesignPage(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerDesignPage() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerDesignPage(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerDesignPage(SolrInputDocument document) {
		if(sauvegardesDesignPage != null)
			document.addField("sauvegardesDesignPage_stored_strings", sauvegardesDesignPage);

		if(designPageCle != null) {
			document.addField("designPageCle_indexed_long", designPageCle);
			document.addField("designPageCle_stored_long", designPageCle);
		}
		if(designEnfantCles != null) {
			for(java.lang.Long o : designEnfantCles) {
				document.addField("designEnfantCles_indexed_longs", o);
			}
			for(java.lang.Long o : designEnfantCles) {
				document.addField("designEnfantCles_stored_longs", o);
			}
		}
		if(designParentCles != null) {
			for(java.lang.Long o : designParentCles) {
				document.addField("designParentCles_indexed_longs", o);
			}
			for(java.lang.Long o : designParentCles) {
				document.addField("designParentCles_stored_longs", o);
			}
		}
		if(partHtmlCles != null) {
			for(java.lang.Long o : partHtmlCles) {
				document.addField("partHtmlCles_indexed_longs", o);
			}
			for(java.lang.Long o : partHtmlCles) {
				document.addField("partHtmlCles_stored_longs", o);
			}
		}
		if(designPageNomComplet != null) {
			document.addField("designPageNomComplet_indexed_string", designPageNomComplet);
			document.addField("designPageNomComplet_stored_string", designPageNomComplet);
		}
		if(designCache != null) {
			document.addField("designCache_indexed_boolean", designCache);
			document.addField("designCache_stored_boolean", designCache);
		}
		super.indexerCluster(document);

	}

	public void desindexerDesignPage() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinDesignPage(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexeDesignPage(String entiteVar) {
		switch(entiteVar) {
			case "designPageCle":
				return "designPageCle_indexed_long";
			case "designEnfantCles":
				return "designEnfantCles_indexed_longs";
			case "designParentCles":
				return "designParentCles_indexed_longs";
			case "partHtmlCles":
				return "partHtmlCles_indexed_longs";
			case "designPageNomComplet":
				return "designPageNomComplet_indexed_string";
			case "designCache":
				return "designCache_indexed_boolean";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRechercheDesignPage(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggereDesignPage(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerDesignPage(solrDocument);
	}
	public void stockerDesignPage(SolrDocument solrDocument) {
		DesignPage oDesignPage = (DesignPage)this;

		Long designPageCle = (Long)solrDocument.get("designPageCle_stored_long");
		if(designPageCle != null)
			oDesignPage.setDesignPageCle(designPageCle);

		List<Long> designEnfantCles = (List<Long>)solrDocument.get("designEnfantCles_stored_longs");
		if(designEnfantCles != null)
			oDesignPage.designEnfantCles.addAll(designEnfantCles);

		List<Long> designParentCles = (List<Long>)solrDocument.get("designParentCles_stored_longs");
		if(designParentCles != null)
			oDesignPage.designParentCles.addAll(designParentCles);

		List<Long> partHtmlCles = (List<Long>)solrDocument.get("partHtmlCles_stored_longs");
		if(partHtmlCles != null)
			oDesignPage.partHtmlCles.addAll(partHtmlCles);

		String designPageNomComplet = (String)solrDocument.get("designPageNomComplet_stored_string");
		if(designPageNomComplet != null)
			oDesignPage.setDesignPageNomComplet(designPageNomComplet);

		Boolean designCache = (Boolean)solrDocument.get("designCache_stored_boolean");
		if(designCache != null)
			oDesignPage.setDesignCache(designCache);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiDesignPage() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof DesignPage) {
			DesignPage original = (DesignPage)o;
			if(!Objects.equals(designEnfantCles, original.getDesignEnfantCles()))
				requeteApi.addVars("designEnfantCles");
			if(!Objects.equals(designParentCles, original.getDesignParentCles()))
				requeteApi.addVars("designParentCles");
			if(!Objects.equals(partHtmlCles, original.getPartHtmlCles()))
				requeteApi.addVars("partHtmlCles");
			if(!Objects.equals(designPageNomComplet, original.getDesignPageNomComplet()))
				requeteApi.addVars("designPageNomComplet");
			if(!Objects.equals(designCache, original.getDesignCache()))
				requeteApi.addVars("designCache");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), designEnfantCles, designParentCles, partHtmlCles, designPageNomComplet, designCache);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof DesignPage))
			return false;
		DesignPage that = (DesignPage)o;
		return super.equals(o)
				&& Objects.equals( designEnfantCles, that.designEnfantCles )
				&& Objects.equals( designParentCles, that.designParentCles )
				&& Objects.equals( partHtmlCles, that.partHtmlCles )
				&& Objects.equals( designPageNomComplet, that.designPageNomComplet )
				&& Objects.equals( designCache, that.designCache );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DesignPage { ");
		sb.append( "designEnfantCles: " ).append(designEnfantCles);
		sb.append( ", designParentCles: " ).append(designParentCles);
		sb.append( ", partHtmlCles: " ).append(partHtmlCles);
		sb.append( ", designPageNomComplet: \"" ).append(designPageNomComplet).append( "\"" );
		sb.append( ", designCache: " ).append(designCache);
		sb.append(" }");
		return sb.toString();
	}
}
