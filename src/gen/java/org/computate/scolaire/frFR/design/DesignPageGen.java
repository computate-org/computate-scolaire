package org.computate.scolaire.frFR.design;

import java.util.Arrays;
import org.computate.scolaire.frFR.html.part.PartHtml;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Map;
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
import org.computate.scolaire.frFR.design.DesignPage;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe pageCsv dans Solr. </a>
 * <br/>
 **/
public abstract class DesignPageGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DesignPage.class);

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
	public static final String DesignPage_Titre = "design de pages";
	public static final String DesignPage_LesNom = "les design de pages";
	public static final String DesignPage_AucunNomTrouve = "aucun design de page trouvé";
	public static final String DesignPage_NomVar = "design-page";
	public static final String DesignPage_DeNom = "de design de page";
	public static final String DesignPage_NomAdjectifSingulier = "design de page";
	public static final String DesignPage_NomAdjectifPluriel = "design de pages";
	public static final String DesignPage_Couleur = "khaki";
	public static final String DesignPage_IconeGroupe = "regular";
	public static final String DesignPage_IconeNom = "drafting-compass";
	public static final Integer DesignPage_Lignes = 100;

	///////////////////
	// designPageCle //
	///////////////////

	/**	 L'entité designPageCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long designPageCle;
	@JsonIgnore
	public Couverture<Long> designPageCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("designPageCle").o(designPageCle);

	/**	<br/> L'entité designPageCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designPageCle">Trouver l'entité designPageCle dans Solr</a>
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
	public void setDesignPageCle(String o) {
		this.designPageCle = DesignPage.staticSetDesignPageCle(requeteSite_, o);
		this.designPageCleCouverture.dejaInitialise = true;
	}
	public static Long staticSetDesignPageCle(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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

	public static Long staticSolrDesignPageCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrDesignPageCle(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignPageCle(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignPageCle(requeteSite_, DesignPage.staticSolrDesignPageCle(requeteSite_, DesignPage.staticSetDesignPageCle(requeteSite_, o)));
	}

	public Long solrDesignPageCle() {
		return DesignPage.staticSolrDesignPageCle(requeteSite_, designPageCle);
	}

	public String strDesignPageCle() {
		return designPageCle == null ? "" : designPageCle.toString();
	}

	public Long sqlDesignPageCle() {
		return designPageCle;
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

	/**	 L'entité designEnfantCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> designEnfantCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> designEnfantClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("designEnfantCles").o(designEnfantCles);

	/**	<br/> L'entité designEnfantCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designEnfantCles">Trouver l'entité designEnfantCles dans Solr</a>
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
	public void setDesignEnfantCles(String o) {
		Long l = DesignPage.staticSetDesignEnfantCles(requeteSite_, o);
		if(l != null)
			addDesignEnfantCles(l);
		this.designEnfantClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetDesignEnfantCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setDesignEnfantCles(JsonArray objets) {
		designEnfantCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addDesignEnfantCles(o);
		}
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

	public static Long staticSolrDesignEnfantCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrDesignEnfantCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignEnfantCles(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignEnfantCles(requeteSite_, DesignPage.staticSolrDesignEnfantCles(requeteSite_, DesignPage.staticSetDesignEnfantCles(requeteSite_, o)));
	}

	public List<Long> solrDesignEnfantCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : designEnfantCles) {
			l.add(DesignPage.staticSolrDesignEnfantCles(requeteSite_, o));
		}
		return l;
	}

	public String strDesignEnfantCles() {
		return designEnfantCles == null ? "" : designEnfantCles.toString();
	}

	public List<Long> sqlDesignEnfantCles() {
		return designEnfantCles;
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

	/**	 L'entité designParentCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> designParentCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> designParentClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("designParentCles").o(designParentCles);

	/**	<br/> L'entité designParentCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designParentCles">Trouver l'entité designParentCles dans Solr</a>
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
	public void setDesignParentCles(String o) {
		Long l = DesignPage.staticSetDesignParentCles(requeteSite_, o);
		if(l != null)
			addDesignParentCles(l);
		this.designParentClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetDesignParentCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setDesignParentCles(JsonArray objets) {
		designParentCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addDesignParentCles(o);
		}
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

	public static Long staticSolrDesignParentCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrDesignParentCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignParentCles(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignParentCles(requeteSite_, DesignPage.staticSolrDesignParentCles(requeteSite_, DesignPage.staticSetDesignParentCles(requeteSite_, o)));
	}

	public List<Long> solrDesignParentCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : designParentCles) {
			l.add(DesignPage.staticSolrDesignParentCles(requeteSite_, o));
		}
		return l;
	}

	public String strDesignParentCles() {
		return designParentCles == null ? "" : designParentCles.toString();
	}

	public List<Long> sqlDesignParentCles() {
		return designParentCles;
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
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_designParentCles_vider")
						.a("class", "designParentCles_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_designParentCles_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "designs parent")
				.a("class", "valeurObjetSuggere suggereDesignParentCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setDesignParentCles")
				.a("id", classeApiMethodeMethode, "_designParentCles")
				.a("autocomplete", "off");
				a("oninput", "suggereDesignPageDesignParentCles($(this).val() ? [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': 'pk,pageUrlPk,designPageNomComplet' } ] : [", pk == null ? "" : "{'name':'fq','value':'designEnfantCles:" + pk + "'}", "], $('#listDesignPageDesignParentCles_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
		}
	}

	public void htmDesignParentCles(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignParentCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/design-page?fq=designEnfantCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-khaki w3-hover-khaki ").f();
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
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), DesignPage.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), DesignPage.ROLES)
										) {
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
												.a("id", classeApiMethodeMethode, "_designParentCles_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postDesignPageVals({ designEnfantCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "designParentCles')); });")
												.f().sx("ajouter un design de page")
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

	//////////////////
	// partHtmlCles //
	//////////////////

	/**	 L'entité partHtmlCles
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> partHtmlCles = new ArrayList<Long>();
	@JsonIgnore
	public Couverture<List<Long>> partHtmlClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("partHtmlCles").o(partHtmlCles);

	/**	<br/> L'entité partHtmlCles
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partHtmlCles">Trouver l'entité partHtmlCles dans Solr</a>
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
	public void setPartHtmlCles(String o) {
		Long l = DesignPage.staticSetPartHtmlCles(requeteSite_, o);
		if(l != null)
			addPartHtmlCles(l);
		this.partHtmlClesCouverture.dejaInitialise = true;
	}
	public static Long staticSetPartHtmlCles(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
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
	public void setPartHtmlCles(JsonArray objets) {
		partHtmlCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addPartHtmlCles(o);
		}
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

	public static Long staticSolrPartHtmlCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o;
	}

	public static String staticSolrStrPartHtmlCles(RequeteSiteFrFR requeteSite_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPartHtmlCles(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrPartHtmlCles(requeteSite_, DesignPage.staticSolrPartHtmlCles(requeteSite_, DesignPage.staticSetPartHtmlCles(requeteSite_, o)));
	}

	public List<Long> solrPartHtmlCles() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : partHtmlCles) {
			l.add(DesignPage.staticSolrPartHtmlCles(requeteSite_, o));
		}
		return l;
	}

	public String strPartHtmlCles() {
		return partHtmlCles == null ? "" : partHtmlCles.toString();
	}

	public List<Long> sqlPartHtmlCles() {
		return partHtmlCles;
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
			if("PUTCopie".equals(classeApiMethodeMethode)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classeApiMethodeMethode, "_partHtmlCles_vider")
						.a("class", "partHtmlCles_vider ")
						.fg();
					e("label").a("for", "classeApiMethodeMethode, \"_partHtmlCles_vider").f().sx("vider").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "parts")
				.a("class", "valeurObjetSuggere suggerePartHtmlCles w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setPartHtmlCles")
				.a("id", classeApiMethodeMethode, "_partHtmlCles")
				.a("autocomplete", "off");
				a("oninput", "suggereDesignPagePartHtmlCles($(this).val() ? [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': 'pk,pageUrlPk,objetTitre' } ] : [", pk == null ? "" : "{'name':'fq','value':'designPageCles:" + pk + "'}", "], $('#listDesignPagePartHtmlCles_", classeApiMethodeMethode, "'), ", pk, "); ");

				fg();

		} else {
		}
	}

	public void htmPartHtmlCles(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPagePartHtmlCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/part-html?fq=designPageCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-khaki w3-hover-khaki ").f();
								e("i").a("class", "far fa-puzzle-piece ").f().g("i");
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
										CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), PartHtml.ROLES)
										|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), PartHtml.ROLES)
										) {
									if("Page".equals(classeApiMethodeMethode)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
												.a("id", classeApiMethodeMethode, "_partHtmlCles_ajouter")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Envoi…'; postPartHtmlVals({ designPageCles: [ \"", pk, "\" ] }, function() {}, function() { ajouterErreur($('#", classeApiMethodeMethode, "partHtmlCles')); });")
												.f().sx("ajouter un part de HTML")
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

	//////////////////////////
	// designPageNomComplet //
	//////////////////////////

	/**	 L'entité designPageNomComplet
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String designPageNomComplet;
	@JsonIgnore
	public Couverture<String> designPageNomCompletCouverture = new Couverture<String>().p(this).c(String.class).var("designPageNomComplet").o(designPageNomComplet);

	/**	<br/> L'entité designPageNomComplet
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designPageNomComplet">Trouver l'entité designPageNomComplet dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designPageNomComplet(Couverture<String> c);

	public String getDesignPageNomComplet() {
		return designPageNomComplet;
	}
	public void setDesignPageNomComplet(String o) {
		this.designPageNomComplet = DesignPage.staticSetDesignPageNomComplet(requeteSite_, o);
		this.designPageNomCompletCouverture.dejaInitialise = true;
	}
	public static String staticSetDesignPageNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static String staticSolrDesignPageNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrDesignPageNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignPageNomComplet(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignPageNomComplet(requeteSite_, DesignPage.staticSolrDesignPageNomComplet(requeteSite_, DesignPage.staticSetDesignPageNomComplet(requeteSite_, o)));
	}

	public String solrDesignPageNomComplet() {
		return DesignPage.staticSolrDesignPageNomComplet(requeteSite_, designPageNomComplet);
	}

	public String strDesignPageNomComplet() {
		return designPageNomComplet == null ? "" : designPageNomComplet;
	}

	public String sqlDesignPageNomComplet() {
		return designPageNomComplet;
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
					a("class", "setDesignPageNomComplet classDesignPage inputDesignPage", pk, "DesignPageNomComplet w3-input w3-border ");
					a("name", "setDesignPageNomComplet");
				} else {
					a("class", "valeurDesignPageNomComplet w3-input w3-border classDesignPage inputDesignPage", pk, "DesignPageNomComplet w3-input w3-border ");
					a("name", "designPageNomComplet");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignPageNomComplet', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designPageNomComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designPageNomComplet')); }); ");
				}
				a("value", strDesignPageNomComplet())
			.fg();

		} else {
			e("span").a("class", "varDesignPage", pk, "DesignPageNomComplet ").f().sx(htmDesignPageNomComplet()).g("span");
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
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_designPageNomComplet')); $('#", classeApiMethodeMethode, "_designPageNomComplet').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#DesignPageForm :input[name=pk]').val() }], 'setDesignPageNomComplet', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_designPageNomComplet')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designPageNomComplet')); }); ")
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

	/**	 L'entité designCache
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designCache;
	@JsonIgnore
	public Couverture<Boolean> designCacheCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("designCache").o(designCache);

	/**	<br/> L'entité designCache
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designCache">Trouver l'entité designCache dans Solr</a>
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
	public void setDesignCache(String o) {
		this.designCache = DesignPage.staticSetDesignCache(requeteSite_, o);
		this.designCacheCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetDesignCache(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Boolean staticSolrDesignCache(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrDesignCache(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignCache(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignCache(requeteSite_, DesignPage.staticSolrDesignCache(requeteSite_, DesignPage.staticSetDesignCache(requeteSite_, o)));
	}

	public Boolean solrDesignCache() {
		return DesignPage.staticSolrDesignCache(requeteSite_, designCache);
	}

	public String strDesignCache() {
		return designCache == null ? "" : designCache.toString();
	}

	public Boolean sqlDesignCache() {
		return designCache;
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
				a("class", "setDesignCache classDesignPage inputDesignPage", pk, "DesignCache w3-input w3-border ");
				a("name", "setDesignCache");
			} else {
				a("class", "valeurDesignCache classDesignPage inputDesignPage", pk, "DesignCache w3-input w3-border ");
				a("name", "designCache");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignCache', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designCache')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designCache')); }); ");
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
			e("span").a("class", "varDesignPage", pk, "DesignCache ").f().sx(htmDesignCache()).g("span");
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

	/////////////////
	// designAdmin //
	/////////////////

	/**	 L'entité designAdmin
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designAdmin;
	@JsonIgnore
	public Couverture<Boolean> designAdminCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("designAdmin").o(designAdmin);

	/**	<br/> L'entité designAdmin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designAdmin">Trouver l'entité designAdmin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designAdmin(Couverture<Boolean> c);

	public Boolean getDesignAdmin() {
		return designAdmin;
	}

	public void setDesignAdmin(Boolean designAdmin) {
		this.designAdmin = designAdmin;
		this.designAdminCouverture.dejaInitialise = true;
	}
	public void setDesignAdmin(String o) {
		this.designAdmin = DesignPage.staticSetDesignAdmin(requeteSite_, o);
		this.designAdminCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetDesignAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage designAdminInit() {
		if(!designAdminCouverture.dejaInitialise) {
			_designAdmin(designAdminCouverture);
			if(designAdmin == null)
				setDesignAdmin(designAdminCouverture.o);
		}
		designAdminCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrDesignAdmin(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrDesignAdmin(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignAdmin(requeteSite_, DesignPage.staticSolrDesignAdmin(requeteSite_, DesignPage.staticSetDesignAdmin(requeteSite_, o)));
	}

	public Boolean solrDesignAdmin() {
		return DesignPage.staticSolrDesignAdmin(requeteSite_, designAdmin);
	}

	public String strDesignAdmin() {
		return designAdmin == null ? "" : designAdmin.toString();
	}

	public Boolean sqlDesignAdmin() {
		return designAdmin;
	}

	public String jsonDesignAdmin() {
		return designAdmin == null ? "" : designAdmin.toString();
	}

	public String nomAffichageDesignAdmin() {
		return "pour admin";
	}

	public String htmTooltipDesignAdmin() {
		return null;
	}

	public String htmDesignAdmin() {
		return designAdmin == null ? "" : StringEscapeUtils.escapeHtml4(strDesignAdmin());
	}

	public void inputDesignAdmin(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_designAdmin")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_designAdmin");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setDesignAdmin classDesignPage inputDesignPage", pk, "DesignAdmin w3-input w3-border ");
				a("name", "setDesignAdmin");
			} else {
				a("class", "valeurDesignAdmin classDesignPage inputDesignPage", pk, "DesignAdmin w3-input w3-border ");
				a("name", "designAdmin");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignAdmin', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designAdmin')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designAdmin')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getDesignAdmin() != null && getDesignAdmin())
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
			e("span").a("class", "varDesignPage", pk, "DesignAdmin ").f().sx(htmDesignAdmin()).g("span");
		}
	}

	public void htmDesignAdmin(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignAdmin").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_designAdmin").a("class", "").f().sx("pour admin").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignAdmin(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////////////
	// designIgnorerNomEnfantVide //
	////////////////////////////////

	/**	 L'entité designIgnorerNomEnfantVide
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designIgnorerNomEnfantVide;
	@JsonIgnore
	public Couverture<Boolean> designIgnorerNomEnfantVideCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("designIgnorerNomEnfantVide").o(designIgnorerNomEnfantVide);

	/**	<br/> L'entité designIgnorerNomEnfantVide
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designIgnorerNomEnfantVide">Trouver l'entité designIgnorerNomEnfantVide dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designIgnorerNomEnfantVide(Couverture<Boolean> c);

	public Boolean getDesignIgnorerNomEnfantVide() {
		return designIgnorerNomEnfantVide;
	}

	public void setDesignIgnorerNomEnfantVide(Boolean designIgnorerNomEnfantVide) {
		this.designIgnorerNomEnfantVide = designIgnorerNomEnfantVide;
		this.designIgnorerNomEnfantVideCouverture.dejaInitialise = true;
	}
	public void setDesignIgnorerNomEnfantVide(String o) {
		this.designIgnorerNomEnfantVide = DesignPage.staticSetDesignIgnorerNomEnfantVide(requeteSite_, o);
		this.designIgnorerNomEnfantVideCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetDesignIgnorerNomEnfantVide(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage designIgnorerNomEnfantVideInit() {
		if(!designIgnorerNomEnfantVideCouverture.dejaInitialise) {
			_designIgnorerNomEnfantVide(designIgnorerNomEnfantVideCouverture);
			if(designIgnorerNomEnfantVide == null)
				setDesignIgnorerNomEnfantVide(designIgnorerNomEnfantVideCouverture.o);
		}
		designIgnorerNomEnfantVideCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrDesignIgnorerNomEnfantVide(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrDesignIgnorerNomEnfantVide(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignIgnorerNomEnfantVide(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignIgnorerNomEnfantVide(requeteSite_, DesignPage.staticSolrDesignIgnorerNomEnfantVide(requeteSite_, DesignPage.staticSetDesignIgnorerNomEnfantVide(requeteSite_, o)));
	}

	public Boolean solrDesignIgnorerNomEnfantVide() {
		return DesignPage.staticSolrDesignIgnorerNomEnfantVide(requeteSite_, designIgnorerNomEnfantVide);
	}

	public String strDesignIgnorerNomEnfantVide() {
		return designIgnorerNomEnfantVide == null ? "" : designIgnorerNomEnfantVide.toString();
	}

	public Boolean sqlDesignIgnorerNomEnfantVide() {
		return designIgnorerNomEnfantVide;
	}

	public String jsonDesignIgnorerNomEnfantVide() {
		return designIgnorerNomEnfantVide == null ? "" : designIgnorerNomEnfantVide.toString();
	}

	public String nomAffichageDesignIgnorerNomEnfantVide() {
		return "ignorer nom d'enfant vide";
	}

	public String htmTooltipDesignIgnorerNomEnfantVide() {
		return null;
	}

	public String htmDesignIgnorerNomEnfantVide() {
		return designIgnorerNomEnfantVide == null ? "" : StringEscapeUtils.escapeHtml4(strDesignIgnorerNomEnfantVide());
	}

	public void inputDesignIgnorerNomEnfantVide(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_designIgnorerNomEnfantVide")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_designIgnorerNomEnfantVide");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setDesignIgnorerNomEnfantVide classDesignPage inputDesignPage", pk, "DesignIgnorerNomEnfantVide w3-input w3-border ");
				a("name", "setDesignIgnorerNomEnfantVide");
			} else {
				a("class", "valeurDesignIgnorerNomEnfantVide classDesignPage inputDesignPage", pk, "DesignIgnorerNomEnfantVide w3-input w3-border ");
				a("name", "designIgnorerNomEnfantVide");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignIgnorerNomEnfantVide', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designIgnorerNomEnfantVide')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designIgnorerNomEnfantVide')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getDesignIgnorerNomEnfantVide() != null && getDesignIgnorerNomEnfantVide())
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
			e("span").a("class", "varDesignPage", pk, "DesignIgnorerNomEnfantVide ").f().sx(htmDesignIgnorerNomEnfantVide()).g("span");
		}
	}

	public void htmDesignIgnorerNomEnfantVide(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignIgnorerNomEnfantVide").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_designIgnorerNomEnfantVide").a("class", "").f().sx("ignorer nom d'enfant vide").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignIgnorerNomEnfantVide(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////////////////////
	// designIgnorerPaiementsPasEnSouffrance //
	///////////////////////////////////////////

	/**	 L'entité designIgnorerPaiementsPasEnSouffrance
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designIgnorerPaiementsPasEnSouffrance;
	@JsonIgnore
	public Couverture<Boolean> designIgnorerPaiementsPasEnSouffranceCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("designIgnorerPaiementsPasEnSouffrance").o(designIgnorerPaiementsPasEnSouffrance);

	/**	<br/> L'entité designIgnorerPaiementsPasEnSouffrance
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designIgnorerPaiementsPasEnSouffrance">Trouver l'entité designIgnorerPaiementsPasEnSouffrance dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designIgnorerPaiementsPasEnSouffrance(Couverture<Boolean> c);

	public Boolean getDesignIgnorerPaiementsPasEnSouffrance() {
		return designIgnorerPaiementsPasEnSouffrance;
	}

	public void setDesignIgnorerPaiementsPasEnSouffrance(Boolean designIgnorerPaiementsPasEnSouffrance) {
		this.designIgnorerPaiementsPasEnSouffrance = designIgnorerPaiementsPasEnSouffrance;
		this.designIgnorerPaiementsPasEnSouffranceCouverture.dejaInitialise = true;
	}
	public void setDesignIgnorerPaiementsPasEnSouffrance(String o) {
		this.designIgnorerPaiementsPasEnSouffrance = DesignPage.staticSetDesignIgnorerPaiementsPasEnSouffrance(requeteSite_, o);
		this.designIgnorerPaiementsPasEnSouffranceCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetDesignIgnorerPaiementsPasEnSouffrance(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage designIgnorerPaiementsPasEnSouffranceInit() {
		if(!designIgnorerPaiementsPasEnSouffranceCouverture.dejaInitialise) {
			_designIgnorerPaiementsPasEnSouffrance(designIgnorerPaiementsPasEnSouffranceCouverture);
			if(designIgnorerPaiementsPasEnSouffrance == null)
				setDesignIgnorerPaiementsPasEnSouffrance(designIgnorerPaiementsPasEnSouffranceCouverture.o);
		}
		designIgnorerPaiementsPasEnSouffranceCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrDesignIgnorerPaiementsPasEnSouffrance(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrDesignIgnorerPaiementsPasEnSouffrance(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignIgnorerPaiementsPasEnSouffrance(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignIgnorerPaiementsPasEnSouffrance(requeteSite_, DesignPage.staticSolrDesignIgnorerPaiementsPasEnSouffrance(requeteSite_, DesignPage.staticSetDesignIgnorerPaiementsPasEnSouffrance(requeteSite_, o)));
	}

	public Boolean solrDesignIgnorerPaiementsPasEnSouffrance() {
		return DesignPage.staticSolrDesignIgnorerPaiementsPasEnSouffrance(requeteSite_, designIgnorerPaiementsPasEnSouffrance);
	}

	public String strDesignIgnorerPaiementsPasEnSouffrance() {
		return designIgnorerPaiementsPasEnSouffrance == null ? "" : designIgnorerPaiementsPasEnSouffrance.toString();
	}

	public Boolean sqlDesignIgnorerPaiementsPasEnSouffrance() {
		return designIgnorerPaiementsPasEnSouffrance;
	}

	public String jsonDesignIgnorerPaiementsPasEnSouffrance() {
		return designIgnorerPaiementsPasEnSouffrance == null ? "" : designIgnorerPaiementsPasEnSouffrance.toString();
	}

	public String nomAffichageDesignIgnorerPaiementsPasEnSouffrance() {
		return "ignorer inscriptions pas en souffrance";
	}

	public String htmTooltipDesignIgnorerPaiementsPasEnSouffrance() {
		return null;
	}

	public String htmDesignIgnorerPaiementsPasEnSouffrance() {
		return designIgnorerPaiementsPasEnSouffrance == null ? "" : StringEscapeUtils.escapeHtml4(strDesignIgnorerPaiementsPasEnSouffrance());
	}

	public void inputDesignIgnorerPaiementsPasEnSouffrance(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_designIgnorerPaiementsPasEnSouffrance")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_designIgnorerPaiementsPasEnSouffrance");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setDesignIgnorerPaiementsPasEnSouffrance classDesignPage inputDesignPage", pk, "DesignIgnorerPaiementsPasEnSouffrance w3-input w3-border ");
				a("name", "setDesignIgnorerPaiementsPasEnSouffrance");
			} else {
				a("class", "valeurDesignIgnorerPaiementsPasEnSouffrance classDesignPage inputDesignPage", pk, "DesignIgnorerPaiementsPasEnSouffrance w3-input w3-border ");
				a("name", "designIgnorerPaiementsPasEnSouffrance");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignIgnorerPaiementsPasEnSouffrance', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designIgnorerPaiementsPasEnSouffrance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designIgnorerPaiementsPasEnSouffrance')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getDesignIgnorerPaiementsPasEnSouffrance() != null && getDesignIgnorerPaiementsPasEnSouffrance())
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
			e("span").a("class", "varDesignPage", pk, "DesignIgnorerPaiementsPasEnSouffrance ").f().sx(htmDesignIgnorerPaiementsPasEnSouffrance()).g("span");
		}
	}

	public void htmDesignIgnorerPaiementsPasEnSouffrance(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignIgnorerPaiementsPasEnSouffrance").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_designIgnorerPaiementsPasEnSouffrance").a("class", "").f().sx("ignorer inscriptions pas en souffrance").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignIgnorerPaiementsPasEnSouffrance(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////////////////////
	// designIgnorerPaiementsEnSouffrance //
	////////////////////////////////////////

	/**	 L'entité designIgnorerPaiementsEnSouffrance
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designIgnorerPaiementsEnSouffrance;
	@JsonIgnore
	public Couverture<Boolean> designIgnorerPaiementsEnSouffranceCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("designIgnorerPaiementsEnSouffrance").o(designIgnorerPaiementsEnSouffrance);

	/**	<br/> L'entité designIgnorerPaiementsEnSouffrance
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designIgnorerPaiementsEnSouffrance">Trouver l'entité designIgnorerPaiementsEnSouffrance dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designIgnorerPaiementsEnSouffrance(Couverture<Boolean> c);

	public Boolean getDesignIgnorerPaiementsEnSouffrance() {
		return designIgnorerPaiementsEnSouffrance;
	}

	public void setDesignIgnorerPaiementsEnSouffrance(Boolean designIgnorerPaiementsEnSouffrance) {
		this.designIgnorerPaiementsEnSouffrance = designIgnorerPaiementsEnSouffrance;
		this.designIgnorerPaiementsEnSouffranceCouverture.dejaInitialise = true;
	}
	public void setDesignIgnorerPaiementsEnSouffrance(String o) {
		this.designIgnorerPaiementsEnSouffrance = DesignPage.staticSetDesignIgnorerPaiementsEnSouffrance(requeteSite_, o);
		this.designIgnorerPaiementsEnSouffranceCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetDesignIgnorerPaiementsEnSouffrance(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage designIgnorerPaiementsEnSouffranceInit() {
		if(!designIgnorerPaiementsEnSouffranceCouverture.dejaInitialise) {
			_designIgnorerPaiementsEnSouffrance(designIgnorerPaiementsEnSouffranceCouverture);
			if(designIgnorerPaiementsEnSouffrance == null)
				setDesignIgnorerPaiementsEnSouffrance(designIgnorerPaiementsEnSouffranceCouverture.o);
		}
		designIgnorerPaiementsEnSouffranceCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrDesignIgnorerPaiementsEnSouffrance(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrDesignIgnorerPaiementsEnSouffrance(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignIgnorerPaiementsEnSouffrance(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignIgnorerPaiementsEnSouffrance(requeteSite_, DesignPage.staticSolrDesignIgnorerPaiementsEnSouffrance(requeteSite_, DesignPage.staticSetDesignIgnorerPaiementsEnSouffrance(requeteSite_, o)));
	}

	public Boolean solrDesignIgnorerPaiementsEnSouffrance() {
		return DesignPage.staticSolrDesignIgnorerPaiementsEnSouffrance(requeteSite_, designIgnorerPaiementsEnSouffrance);
	}

	public String strDesignIgnorerPaiementsEnSouffrance() {
		return designIgnorerPaiementsEnSouffrance == null ? "" : designIgnorerPaiementsEnSouffrance.toString();
	}

	public Boolean sqlDesignIgnorerPaiementsEnSouffrance() {
		return designIgnorerPaiementsEnSouffrance;
	}

	public String jsonDesignIgnorerPaiementsEnSouffrance() {
		return designIgnorerPaiementsEnSouffrance == null ? "" : designIgnorerPaiementsEnSouffrance.toString();
	}

	public String nomAffichageDesignIgnorerPaiementsEnSouffrance() {
		return "ignorer inscriptions en souffrance";
	}

	public String htmTooltipDesignIgnorerPaiementsEnSouffrance() {
		return null;
	}

	public String htmDesignIgnorerPaiementsEnSouffrance() {
		return designIgnorerPaiementsEnSouffrance == null ? "" : StringEscapeUtils.escapeHtml4(strDesignIgnorerPaiementsEnSouffrance());
	}

	public void inputDesignIgnorerPaiementsEnSouffrance(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_designIgnorerPaiementsEnSouffrance")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_designIgnorerPaiementsEnSouffrance");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setDesignIgnorerPaiementsEnSouffrance classDesignPage inputDesignPage", pk, "DesignIgnorerPaiementsEnSouffrance w3-input w3-border ");
				a("name", "setDesignIgnorerPaiementsEnSouffrance");
			} else {
				a("class", "valeurDesignIgnorerPaiementsEnSouffrance classDesignPage inputDesignPage", pk, "DesignIgnorerPaiementsEnSouffrance w3-input w3-border ");
				a("name", "designIgnorerPaiementsEnSouffrance");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignIgnorerPaiementsEnSouffrance', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designIgnorerPaiementsEnSouffrance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designIgnorerPaiementsEnSouffrance')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getDesignIgnorerPaiementsEnSouffrance() != null && getDesignIgnorerPaiementsEnSouffrance())
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
			e("span").a("class", "varDesignPage", pk, "DesignIgnorerPaiementsEnSouffrance ").f().sx(htmDesignIgnorerPaiementsEnSouffrance()).g("span");
		}
	}

	public void htmDesignIgnorerPaiementsEnSouffrance(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignIgnorerPaiementsEnSouffrance").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_designIgnorerPaiementsEnSouffrance").a("class", "").f().sx("ignorer inscriptions en souffrance").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignIgnorerPaiementsEnSouffrance(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////////////
	// designFiltrerInscriptionCle //
	/////////////////////////////////

	/**	 L'entité designFiltrerInscriptionCle
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designFiltrerInscriptionCle;
	@JsonIgnore
	public Couverture<Boolean> designFiltrerInscriptionCleCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("designFiltrerInscriptionCle").o(designFiltrerInscriptionCle);

	/**	<br/> L'entité designFiltrerInscriptionCle
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designFiltrerInscriptionCle">Trouver l'entité designFiltrerInscriptionCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designFiltrerInscriptionCle(Couverture<Boolean> c);

	public Boolean getDesignFiltrerInscriptionCle() {
		return designFiltrerInscriptionCle;
	}

	public void setDesignFiltrerInscriptionCle(Boolean designFiltrerInscriptionCle) {
		this.designFiltrerInscriptionCle = designFiltrerInscriptionCle;
		this.designFiltrerInscriptionCleCouverture.dejaInitialise = true;
	}
	public void setDesignFiltrerInscriptionCle(String o) {
		this.designFiltrerInscriptionCle = DesignPage.staticSetDesignFiltrerInscriptionCle(requeteSite_, o);
		this.designFiltrerInscriptionCleCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetDesignFiltrerInscriptionCle(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage designFiltrerInscriptionCleInit() {
		if(!designFiltrerInscriptionCleCouverture.dejaInitialise) {
			_designFiltrerInscriptionCle(designFiltrerInscriptionCleCouverture);
			if(designFiltrerInscriptionCle == null)
				setDesignFiltrerInscriptionCle(designFiltrerInscriptionCleCouverture.o);
		}
		designFiltrerInscriptionCleCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrDesignFiltrerInscriptionCle(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrDesignFiltrerInscriptionCle(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignFiltrerInscriptionCle(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignFiltrerInscriptionCle(requeteSite_, DesignPage.staticSolrDesignFiltrerInscriptionCle(requeteSite_, DesignPage.staticSetDesignFiltrerInscriptionCle(requeteSite_, o)));
	}

	public Boolean solrDesignFiltrerInscriptionCle() {
		return DesignPage.staticSolrDesignFiltrerInscriptionCle(requeteSite_, designFiltrerInscriptionCle);
	}

	public String strDesignFiltrerInscriptionCle() {
		return designFiltrerInscriptionCle == null ? "" : designFiltrerInscriptionCle.toString();
	}

	public Boolean sqlDesignFiltrerInscriptionCle() {
		return designFiltrerInscriptionCle;
	}

	public String jsonDesignFiltrerInscriptionCle() {
		return designFiltrerInscriptionCle == null ? "" : designFiltrerInscriptionCle.toString();
	}

	public String nomAffichageDesignFiltrerInscriptionCle() {
		return "filtrer inscription clé";
	}

	public String htmTooltipDesignFiltrerInscriptionCle() {
		return null;
	}

	public String htmDesignFiltrerInscriptionCle() {
		return designFiltrerInscriptionCle == null ? "" : StringEscapeUtils.escapeHtml4(strDesignFiltrerInscriptionCle());
	}

	public void inputDesignFiltrerInscriptionCle(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_designFiltrerInscriptionCle")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_designFiltrerInscriptionCle");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setDesignFiltrerInscriptionCle classDesignPage inputDesignPage", pk, "DesignFiltrerInscriptionCle w3-input w3-border ");
				a("name", "setDesignFiltrerInscriptionCle");
			} else {
				a("class", "valeurDesignFiltrerInscriptionCle classDesignPage inputDesignPage", pk, "DesignFiltrerInscriptionCle w3-input w3-border ");
				a("name", "designFiltrerInscriptionCle");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignFiltrerInscriptionCle', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designFiltrerInscriptionCle')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designFiltrerInscriptionCle')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getDesignFiltrerInscriptionCle() != null && getDesignFiltrerInscriptionCle())
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
			e("span").a("class", "varDesignPage", pk, "DesignFiltrerInscriptionCle ").f().sx(htmDesignFiltrerInscriptionCle()).g("span");
		}
	}

	public void htmDesignFiltrerInscriptionCle(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignFiltrerInscriptionCle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_designFiltrerInscriptionCle").a("class", "").f().sx("filtrer inscription clé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignFiltrerInscriptionCle(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////////////////////////
	// designInscriptionTriMoisJourDeNaissance //
	/////////////////////////////////////////////

	/**	 L'entité designInscriptionTriMoisJourDeNaissance
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designInscriptionTriMoisJourDeNaissance;
	@JsonIgnore
	public Couverture<Boolean> designInscriptionTriMoisJourDeNaissanceCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("designInscriptionTriMoisJourDeNaissance").o(designInscriptionTriMoisJourDeNaissance);

	/**	<br/> L'entité designInscriptionTriMoisJourDeNaissance
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designInscriptionTriMoisJourDeNaissance">Trouver l'entité designInscriptionTriMoisJourDeNaissance dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designInscriptionTriMoisJourDeNaissance(Couverture<Boolean> c);

	public Boolean getDesignInscriptionTriMoisJourDeNaissance() {
		return designInscriptionTriMoisJourDeNaissance;
	}

	public void setDesignInscriptionTriMoisJourDeNaissance(Boolean designInscriptionTriMoisJourDeNaissance) {
		this.designInscriptionTriMoisJourDeNaissance = designInscriptionTriMoisJourDeNaissance;
		this.designInscriptionTriMoisJourDeNaissanceCouverture.dejaInitialise = true;
	}
	public void setDesignInscriptionTriMoisJourDeNaissance(String o) {
		this.designInscriptionTriMoisJourDeNaissance = DesignPage.staticSetDesignInscriptionTriMoisJourDeNaissance(requeteSite_, o);
		this.designInscriptionTriMoisJourDeNaissanceCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetDesignInscriptionTriMoisJourDeNaissance(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage designInscriptionTriMoisJourDeNaissanceInit() {
		if(!designInscriptionTriMoisJourDeNaissanceCouverture.dejaInitialise) {
			_designInscriptionTriMoisJourDeNaissance(designInscriptionTriMoisJourDeNaissanceCouverture);
			if(designInscriptionTriMoisJourDeNaissance == null)
				setDesignInscriptionTriMoisJourDeNaissance(designInscriptionTriMoisJourDeNaissanceCouverture.o);
		}
		designInscriptionTriMoisJourDeNaissanceCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrDesignInscriptionTriMoisJourDeNaissance(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrDesignInscriptionTriMoisJourDeNaissance(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignInscriptionTriMoisJourDeNaissance(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignInscriptionTriMoisJourDeNaissance(requeteSite_, DesignPage.staticSolrDesignInscriptionTriMoisJourDeNaissance(requeteSite_, DesignPage.staticSetDesignInscriptionTriMoisJourDeNaissance(requeteSite_, o)));
	}

	public Boolean solrDesignInscriptionTriMoisJourDeNaissance() {
		return DesignPage.staticSolrDesignInscriptionTriMoisJourDeNaissance(requeteSite_, designInscriptionTriMoisJourDeNaissance);
	}

	public String strDesignInscriptionTriMoisJourDeNaissance() {
		return designInscriptionTriMoisJourDeNaissance == null ? "" : designInscriptionTriMoisJourDeNaissance.toString();
	}

	public Boolean sqlDesignInscriptionTriMoisJourDeNaissance() {
		return designInscriptionTriMoisJourDeNaissance;
	}

	public String jsonDesignInscriptionTriMoisJourDeNaissance() {
		return designInscriptionTriMoisJourDeNaissance == null ? "" : designInscriptionTriMoisJourDeNaissance.toString();
	}

	public String nomAffichageDesignInscriptionTriMoisJourDeNaissance() {
		return "inscription tri mois jour de naissance";
	}

	public String htmTooltipDesignInscriptionTriMoisJourDeNaissance() {
		return null;
	}

	public String htmDesignInscriptionTriMoisJourDeNaissance() {
		return designInscriptionTriMoisJourDeNaissance == null ? "" : StringEscapeUtils.escapeHtml4(strDesignInscriptionTriMoisJourDeNaissance());
	}

	public void inputDesignInscriptionTriMoisJourDeNaissance(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_designInscriptionTriMoisJourDeNaissance")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_designInscriptionTriMoisJourDeNaissance");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setDesignInscriptionTriMoisJourDeNaissance classDesignPage inputDesignPage", pk, "DesignInscriptionTriMoisJourDeNaissance w3-input w3-border ");
				a("name", "setDesignInscriptionTriMoisJourDeNaissance");
			} else {
				a("class", "valeurDesignInscriptionTriMoisJourDeNaissance classDesignPage inputDesignPage", pk, "DesignInscriptionTriMoisJourDeNaissance w3-input w3-border ");
				a("name", "designInscriptionTriMoisJourDeNaissance");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignInscriptionTriMoisJourDeNaissance', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designInscriptionTriMoisJourDeNaissance')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designInscriptionTriMoisJourDeNaissance')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getDesignInscriptionTriMoisJourDeNaissance() != null && getDesignInscriptionTriMoisJourDeNaissance())
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
			e("span").a("class", "varDesignPage", pk, "DesignInscriptionTriMoisJourDeNaissance ").f().sx(htmDesignInscriptionTriMoisJourDeNaissance()).g("span");
		}
	}

	public void htmDesignInscriptionTriMoisJourDeNaissance(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignInscriptionTriMoisJourDeNaissance").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_designInscriptionTriMoisJourDeNaissance").a("class", "").f().sx("inscription tri mois jour de naissance").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignInscriptionTriMoisJourDeNaissance(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////////////
	// designInscriptionTriNomGroupe //
	///////////////////////////////////

	/**	 L'entité designInscriptionTriNomGroupe
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designInscriptionTriNomGroupe;
	@JsonIgnore
	public Couverture<Boolean> designInscriptionTriNomGroupeCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("designInscriptionTriNomGroupe").o(designInscriptionTriNomGroupe);

	/**	<br/> L'entité designInscriptionTriNomGroupe
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designInscriptionTriNomGroupe">Trouver l'entité designInscriptionTriNomGroupe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designInscriptionTriNomGroupe(Couverture<Boolean> c);

	public Boolean getDesignInscriptionTriNomGroupe() {
		return designInscriptionTriNomGroupe;
	}

	public void setDesignInscriptionTriNomGroupe(Boolean designInscriptionTriNomGroupe) {
		this.designInscriptionTriNomGroupe = designInscriptionTriNomGroupe;
		this.designInscriptionTriNomGroupeCouverture.dejaInitialise = true;
	}
	public void setDesignInscriptionTriNomGroupe(String o) {
		this.designInscriptionTriNomGroupe = DesignPage.staticSetDesignInscriptionTriNomGroupe(requeteSite_, o);
		this.designInscriptionTriNomGroupeCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetDesignInscriptionTriNomGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage designInscriptionTriNomGroupeInit() {
		if(!designInscriptionTriNomGroupeCouverture.dejaInitialise) {
			_designInscriptionTriNomGroupe(designInscriptionTriNomGroupeCouverture);
			if(designInscriptionTriNomGroupe == null)
				setDesignInscriptionTriNomGroupe(designInscriptionTriNomGroupeCouverture.o);
		}
		designInscriptionTriNomGroupeCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrDesignInscriptionTriNomGroupe(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrDesignInscriptionTriNomGroupe(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignInscriptionTriNomGroupe(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignInscriptionTriNomGroupe(requeteSite_, DesignPage.staticSolrDesignInscriptionTriNomGroupe(requeteSite_, DesignPage.staticSetDesignInscriptionTriNomGroupe(requeteSite_, o)));
	}

	public Boolean solrDesignInscriptionTriNomGroupe() {
		return DesignPage.staticSolrDesignInscriptionTriNomGroupe(requeteSite_, designInscriptionTriNomGroupe);
	}

	public String strDesignInscriptionTriNomGroupe() {
		return designInscriptionTriNomGroupe == null ? "" : designInscriptionTriNomGroupe.toString();
	}

	public Boolean sqlDesignInscriptionTriNomGroupe() {
		return designInscriptionTriNomGroupe;
	}

	public String jsonDesignInscriptionTriNomGroupe() {
		return designInscriptionTriNomGroupe == null ? "" : designInscriptionTriNomGroupe.toString();
	}

	public String nomAffichageDesignInscriptionTriNomGroupe() {
		return "inscription tri nom de groupe";
	}

	public String htmTooltipDesignInscriptionTriNomGroupe() {
		return null;
	}

	public String htmDesignInscriptionTriNomGroupe() {
		return designInscriptionTriNomGroupe == null ? "" : StringEscapeUtils.escapeHtml4(strDesignInscriptionTriNomGroupe());
	}

	public void inputDesignInscriptionTriNomGroupe(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_designInscriptionTriNomGroupe")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_designInscriptionTriNomGroupe");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setDesignInscriptionTriNomGroupe classDesignPage inputDesignPage", pk, "DesignInscriptionTriNomGroupe w3-input w3-border ");
				a("name", "setDesignInscriptionTriNomGroupe");
			} else {
				a("class", "valeurDesignInscriptionTriNomGroupe classDesignPage inputDesignPage", pk, "DesignInscriptionTriNomGroupe w3-input w3-border ");
				a("name", "designInscriptionTriNomGroupe");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignInscriptionTriNomGroupe', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designInscriptionTriNomGroupe')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designInscriptionTriNomGroupe')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getDesignInscriptionTriNomGroupe() != null && getDesignInscriptionTriNomGroupe())
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
			e("span").a("class", "varDesignPage", pk, "DesignInscriptionTriNomGroupe ").f().sx(htmDesignInscriptionTriNomGroupe()).g("span");
		}
	}

	public void htmDesignInscriptionTriNomGroupe(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignInscriptionTriNomGroupe").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_designInscriptionTriNomGroupe").a("class", "").f().sx("inscription tri nom de groupe").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignInscriptionTriNomGroupe(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////////////
	// designInscriptionTriNomEnfant //
	///////////////////////////////////

	/**	 L'entité designInscriptionTriNomEnfant
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designInscriptionTriNomEnfant;
	@JsonIgnore
	public Couverture<Boolean> designInscriptionTriNomEnfantCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("designInscriptionTriNomEnfant").o(designInscriptionTriNomEnfant);

	/**	<br/> L'entité designInscriptionTriNomEnfant
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designInscriptionTriNomEnfant">Trouver l'entité designInscriptionTriNomEnfant dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designInscriptionTriNomEnfant(Couverture<Boolean> c);

	public Boolean getDesignInscriptionTriNomEnfant() {
		return designInscriptionTriNomEnfant;
	}

	public void setDesignInscriptionTriNomEnfant(Boolean designInscriptionTriNomEnfant) {
		this.designInscriptionTriNomEnfant = designInscriptionTriNomEnfant;
		this.designInscriptionTriNomEnfantCouverture.dejaInitialise = true;
	}
	public void setDesignInscriptionTriNomEnfant(String o) {
		this.designInscriptionTriNomEnfant = DesignPage.staticSetDesignInscriptionTriNomEnfant(requeteSite_, o);
		this.designInscriptionTriNomEnfantCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetDesignInscriptionTriNomEnfant(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage designInscriptionTriNomEnfantInit() {
		if(!designInscriptionTriNomEnfantCouverture.dejaInitialise) {
			_designInscriptionTriNomEnfant(designInscriptionTriNomEnfantCouverture);
			if(designInscriptionTriNomEnfant == null)
				setDesignInscriptionTriNomEnfant(designInscriptionTriNomEnfantCouverture.o);
		}
		designInscriptionTriNomEnfantCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrDesignInscriptionTriNomEnfant(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrDesignInscriptionTriNomEnfant(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDesignInscriptionTriNomEnfant(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrDesignInscriptionTriNomEnfant(requeteSite_, DesignPage.staticSolrDesignInscriptionTriNomEnfant(requeteSite_, DesignPage.staticSetDesignInscriptionTriNomEnfant(requeteSite_, o)));
	}

	public Boolean solrDesignInscriptionTriNomEnfant() {
		return DesignPage.staticSolrDesignInscriptionTriNomEnfant(requeteSite_, designInscriptionTriNomEnfant);
	}

	public String strDesignInscriptionTriNomEnfant() {
		return designInscriptionTriNomEnfant == null ? "" : designInscriptionTriNomEnfant.toString();
	}

	public Boolean sqlDesignInscriptionTriNomEnfant() {
		return designInscriptionTriNomEnfant;
	}

	public String jsonDesignInscriptionTriNomEnfant() {
		return designInscriptionTriNomEnfant == null ? "" : designInscriptionTriNomEnfant.toString();
	}

	public String nomAffichageDesignInscriptionTriNomEnfant() {
		return "inscription tri nom d'enfant";
	}

	public String htmTooltipDesignInscriptionTriNomEnfant() {
		return null;
	}

	public String htmDesignInscriptionTriNomEnfant() {
		return designInscriptionTriNomEnfant == null ? "" : StringEscapeUtils.escapeHtml4(strDesignInscriptionTriNomEnfant());
	}

	public void inputDesignInscriptionTriNomEnfant(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_designInscriptionTriNomEnfant")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_designInscriptionTriNomEnfant");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setDesignInscriptionTriNomEnfant classDesignPage inputDesignPage", pk, "DesignInscriptionTriNomEnfant w3-input w3-border ");
				a("name", "setDesignInscriptionTriNomEnfant");
			} else {
				a("class", "valeurDesignInscriptionTriNomEnfant classDesignPage inputDesignPage", pk, "DesignInscriptionTriNomEnfant w3-input w3-border ");
				a("name", "designInscriptionTriNomEnfant");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignInscriptionTriNomEnfant', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_designInscriptionTriNomEnfant')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_designInscriptionTriNomEnfant')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getDesignInscriptionTriNomEnfant() != null && getDesignInscriptionTriNomEnfant())
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
			e("span").a("class", "varDesignPage", pk, "DesignInscriptionTriNomEnfant ").f().sx(htmDesignInscriptionTriNomEnfant()).g("span");
		}
	}

	public void htmDesignInscriptionTriNomEnfant(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageDesignInscriptionTriNomEnfant").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_designInscriptionTriNomEnfant").a("class", "").f().sx("inscription tri nom d'enfant").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignInscriptionTriNomEnfant(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////
	// rechercherAnnees //
	//////////////////////

	/**	 L'entité rechercherAnnees
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean rechercherAnnees;
	@JsonIgnore
	public Couverture<Boolean> rechercherAnneesCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("rechercherAnnees").o(rechercherAnnees);

	/**	<br/> L'entité rechercherAnnees
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:rechercherAnnees">Trouver l'entité rechercherAnnees dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _rechercherAnnees(Couverture<Boolean> c);

	public Boolean getRechercherAnnees() {
		return rechercherAnnees;
	}

	public void setRechercherAnnees(Boolean rechercherAnnees) {
		this.rechercherAnnees = rechercherAnnees;
		this.rechercherAnneesCouverture.dejaInitialise = true;
	}
	public void setRechercherAnnees(String o) {
		this.rechercherAnnees = DesignPage.staticSetRechercherAnnees(requeteSite_, o);
		this.rechercherAnneesCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetRechercherAnnees(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage rechercherAnneesInit() {
		if(!rechercherAnneesCouverture.dejaInitialise) {
			_rechercherAnnees(rechercherAnneesCouverture);
			if(rechercherAnnees == null)
				setRechercherAnnees(rechercherAnneesCouverture.o);
		}
		rechercherAnneesCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrRechercherAnnees(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrRechercherAnnees(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqRechercherAnnees(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrRechercherAnnees(requeteSite_, DesignPage.staticSolrRechercherAnnees(requeteSite_, DesignPage.staticSetRechercherAnnees(requeteSite_, o)));
	}

	public Boolean solrRechercherAnnees() {
		return DesignPage.staticSolrRechercherAnnees(requeteSite_, rechercherAnnees);
	}

	public String strRechercherAnnees() {
		return rechercherAnnees == null ? "" : rechercherAnnees.toString();
	}

	public Boolean sqlRechercherAnnees() {
		return rechercherAnnees;
	}

	public String jsonRechercherAnnees() {
		return rechercherAnnees == null ? "" : rechercherAnnees.toString();
	}

	public String nomAffichageRechercherAnnees() {
		return "rechercher années";
	}

	public String htmTooltipRechercherAnnees() {
		return null;
	}

	public String htmRechercherAnnees() {
		return rechercherAnnees == null ? "" : StringEscapeUtils.escapeHtml4(strRechercherAnnees());
	}

	public void inputRechercherAnnees(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_rechercherAnnees")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_rechercherAnnees");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setRechercherAnnees classDesignPage inputDesignPage", pk, "RechercherAnnees w3-input w3-border ");
				a("name", "setRechercherAnnees");
			} else {
				a("class", "valeurRechercherAnnees classDesignPage inputDesignPage", pk, "RechercherAnnees w3-input w3-border ");
				a("name", "rechercherAnnees");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setRechercherAnnees', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_rechercherAnnees')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_rechercherAnnees')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getRechercherAnnees() != null && getRechercherAnnees())
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
			e("span").a("class", "varDesignPage", pk, "RechercherAnnees ").f().sx(htmRechercherAnnees()).g("span");
		}
	}

	public void htmRechercherAnnees(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageRechercherAnnees").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_rechercherAnnees").a("class", "").f().sx("rechercher années").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputRechercherAnnees(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////
	// rechercherPaiements //
	/////////////////////////

	/**	 L'entité rechercherPaiements
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean rechercherPaiements;
	@JsonIgnore
	public Couverture<Boolean> rechercherPaiementsCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("rechercherPaiements").o(rechercherPaiements);

	/**	<br/> L'entité rechercherPaiements
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:rechercherPaiements">Trouver l'entité rechercherPaiements dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _rechercherPaiements(Couverture<Boolean> c);

	public Boolean getRechercherPaiements() {
		return rechercherPaiements;
	}

	public void setRechercherPaiements(Boolean rechercherPaiements) {
		this.rechercherPaiements = rechercherPaiements;
		this.rechercherPaiementsCouverture.dejaInitialise = true;
	}
	public void setRechercherPaiements(String o) {
		this.rechercherPaiements = DesignPage.staticSetRechercherPaiements(requeteSite_, o);
		this.rechercherPaiementsCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetRechercherPaiements(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage rechercherPaiementsInit() {
		if(!rechercherPaiementsCouverture.dejaInitialise) {
			_rechercherPaiements(rechercherPaiementsCouverture);
			if(rechercherPaiements == null)
				setRechercherPaiements(rechercherPaiementsCouverture.o);
		}
		rechercherPaiementsCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrRechercherPaiements(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrRechercherPaiements(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqRechercherPaiements(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrRechercherPaiements(requeteSite_, DesignPage.staticSolrRechercherPaiements(requeteSite_, DesignPage.staticSetRechercherPaiements(requeteSite_, o)));
	}

	public Boolean solrRechercherPaiements() {
		return DesignPage.staticSolrRechercherPaiements(requeteSite_, rechercherPaiements);
	}

	public String strRechercherPaiements() {
		return rechercherPaiements == null ? "" : rechercherPaiements.toString();
	}

	public Boolean sqlRechercherPaiements() {
		return rechercherPaiements;
	}

	public String jsonRechercherPaiements() {
		return rechercherPaiements == null ? "" : rechercherPaiements.toString();
	}

	public String nomAffichageRechercherPaiements() {
		return "rechercher paiements";
	}

	public String htmTooltipRechercherPaiements() {
		return null;
	}

	public String htmRechercherPaiements() {
		return rechercherPaiements == null ? "" : StringEscapeUtils.escapeHtml4(strRechercherPaiements());
	}

	public void inputRechercherPaiements(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_rechercherPaiements")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_rechercherPaiements");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setRechercherPaiements classDesignPage inputDesignPage", pk, "RechercherPaiements w3-input w3-border ");
				a("name", "setRechercherPaiements");
			} else {
				a("class", "valeurRechercherPaiements classDesignPage inputDesignPage", pk, "RechercherPaiements w3-input w3-border ");
				a("name", "rechercherPaiements");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setRechercherPaiements', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_rechercherPaiements')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_rechercherPaiements')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getRechercherPaiements() != null && getRechercherPaiements())
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
			e("span").a("class", "varDesignPage", pk, "RechercherPaiements ").f().sx(htmRechercherPaiements()).g("span");
		}
	}

	public void htmRechercherPaiements(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageRechercherPaiements").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_rechercherPaiements").a("class", "").f().sx("rechercher paiements").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputRechercherPaiements(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////////
	// rechercherPaiementsActuel //
	///////////////////////////////

	/**	 L'entité rechercherPaiementsActuel
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean rechercherPaiementsActuel;
	@JsonIgnore
	public Couverture<Boolean> rechercherPaiementsActuelCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("rechercherPaiementsActuel").o(rechercherPaiementsActuel);

	/**	<br/> L'entité rechercherPaiementsActuel
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:rechercherPaiementsActuel">Trouver l'entité rechercherPaiementsActuel dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _rechercherPaiementsActuel(Couverture<Boolean> c);

	public Boolean getRechercherPaiementsActuel() {
		return rechercherPaiementsActuel;
	}

	public void setRechercherPaiementsActuel(Boolean rechercherPaiementsActuel) {
		this.rechercherPaiementsActuel = rechercherPaiementsActuel;
		this.rechercherPaiementsActuelCouverture.dejaInitialise = true;
	}
	public void setRechercherPaiementsActuel(String o) {
		this.rechercherPaiementsActuel = DesignPage.staticSetRechercherPaiementsActuel(requeteSite_, o);
		this.rechercherPaiementsActuelCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetRechercherPaiementsActuel(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage rechercherPaiementsActuelInit() {
		if(!rechercherPaiementsActuelCouverture.dejaInitialise) {
			_rechercherPaiementsActuel(rechercherPaiementsActuelCouverture);
			if(rechercherPaiementsActuel == null)
				setRechercherPaiementsActuel(rechercherPaiementsActuelCouverture.o);
		}
		rechercherPaiementsActuelCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrRechercherPaiementsActuel(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrRechercherPaiementsActuel(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqRechercherPaiementsActuel(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrRechercherPaiementsActuel(requeteSite_, DesignPage.staticSolrRechercherPaiementsActuel(requeteSite_, DesignPage.staticSetRechercherPaiementsActuel(requeteSite_, o)));
	}

	public Boolean solrRechercherPaiementsActuel() {
		return DesignPage.staticSolrRechercherPaiementsActuel(requeteSite_, rechercherPaiementsActuel);
	}

	public String strRechercherPaiementsActuel() {
		return rechercherPaiementsActuel == null ? "" : rechercherPaiementsActuel.toString();
	}

	public Boolean sqlRechercherPaiementsActuel() {
		return rechercherPaiementsActuel;
	}

	public String jsonRechercherPaiementsActuel() {
		return rechercherPaiementsActuel == null ? "" : rechercherPaiementsActuel.toString();
	}

	public String nomAffichageRechercherPaiementsActuel() {
		return "rechercher paiements actuel";
	}

	public String htmTooltipRechercherPaiementsActuel() {
		return null;
	}

	public String htmRechercherPaiementsActuel() {
		return rechercherPaiementsActuel == null ? "" : StringEscapeUtils.escapeHtml4(strRechercherPaiementsActuel());
	}

	public void inputRechercherPaiementsActuel(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_rechercherPaiementsActuel")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_rechercherPaiementsActuel");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setRechercherPaiementsActuel classDesignPage inputDesignPage", pk, "RechercherPaiementsActuel w3-input w3-border ");
				a("name", "setRechercherPaiementsActuel");
			} else {
				a("class", "valeurRechercherPaiementsActuel classDesignPage inputDesignPage", pk, "RechercherPaiementsActuel w3-input w3-border ");
				a("name", "rechercherPaiementsActuel");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setRechercherPaiementsActuel', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_rechercherPaiementsActuel')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_rechercherPaiementsActuel')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getRechercherPaiementsActuel() != null && getRechercherPaiementsActuel())
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
			e("span").a("class", "varDesignPage", pk, "RechercherPaiementsActuel ").f().sx(htmRechercherPaiementsActuel()).g("span");
		}
	}

	public void htmRechercherPaiementsActuel(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPageRechercherPaiementsActuel").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_rechercherPaiementsActuel").a("class", "").f().sx("rechercher paiements actuel").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputRechercherPaiementsActuel(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////
	// pagePdf //
	/////////////

	/**	 L'entité pagePdf
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean pagePdf;
	@JsonIgnore
	public Couverture<Boolean> pagePdfCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("pagePdf").o(pagePdf);

	/**	<br/> L'entité pagePdf
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pagePdf">Trouver l'entité pagePdf dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pagePdf(Couverture<Boolean> c);

	public Boolean getPagePdf() {
		return pagePdf;
	}

	public void setPagePdf(Boolean pagePdf) {
		this.pagePdf = pagePdf;
		this.pagePdfCouverture.dejaInitialise = true;
	}
	public void setPagePdf(String o) {
		this.pagePdf = DesignPage.staticSetPagePdf(requeteSite_, o);
		this.pagePdfCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPagePdf(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage pagePdfInit() {
		if(!pagePdfCouverture.dejaInitialise) {
			_pagePdf(pagePdfCouverture);
			if(pagePdf == null)
				setPagePdf(pagePdfCouverture.o);
		}
		pagePdfCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrPagePdf(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPagePdf(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPagePdf(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrPagePdf(requeteSite_, DesignPage.staticSolrPagePdf(requeteSite_, DesignPage.staticSetPagePdf(requeteSite_, o)));
	}

	public Boolean solrPagePdf() {
		return DesignPage.staticSolrPagePdf(requeteSite_, pagePdf);
	}

	public String strPagePdf() {
		return pagePdf == null ? "" : pagePdf.toString();
	}

	public Boolean sqlPagePdf() {
		return pagePdf;
	}

	public String jsonPagePdf() {
		return pagePdf == null ? "" : pagePdf.toString();
	}

	public String nomAffichagePagePdf() {
		return "page PDF";
	}

	public String htmTooltipPagePdf() {
		return null;
	}

	public String htmPagePdf() {
		return pagePdf == null ? "" : StringEscapeUtils.escapeHtml4(strPagePdf());
	}

	public void inputPagePdf(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_pagePdf")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_pagePdf");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPagePdf classDesignPage inputDesignPage", pk, "PagePdf w3-input w3-border ");
				a("name", "setPagePdf");
			} else {
				a("class", "valeurPagePdf classDesignPage inputDesignPage", pk, "PagePdf w3-input w3-border ");
				a("name", "pagePdf");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPagePdf', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_pagePdf')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_pagePdf')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPagePdf() != null && getPagePdf())
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
			e("span").a("class", "varDesignPage", pk, "PagePdf ").f().sx(htmPagePdf()).g("span");
		}
	}

	public void htmPagePdf(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPagePagePdf").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_pagePdf").a("class", "").f().sx("page PDF").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPagePdf(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////
	// pageCsv //
	/////////////

	/**	 L'entité pageCsv
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean pageCsv;
	@JsonIgnore
	public Couverture<Boolean> pageCsvCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("pageCsv").o(pageCsv);

	/**	<br/> L'entité pageCsv
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.design.DesignPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pageCsv">Trouver l'entité pageCsv dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageCsv(Couverture<Boolean> c);

	public Boolean getPageCsv() {
		return pageCsv;
	}

	public void setPageCsv(Boolean pageCsv) {
		this.pageCsv = pageCsv;
		this.pageCsvCouverture.dejaInitialise = true;
	}
	public void setPageCsv(String o) {
		this.pageCsv = DesignPage.staticSetPageCsv(requeteSite_, o);
		this.pageCsvCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetPageCsv(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignPage pageCsvInit() {
		if(!pageCsvCouverture.dejaInitialise) {
			_pageCsv(pageCsvCouverture);
			if(pageCsv == null)
				setPageCsv(pageCsvCouverture.o);
		}
		pageCsvCouverture.dejaInitialise(true);
		return (DesignPage)this;
	}

	public static Boolean staticSolrPageCsv(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPageCsv(RequeteSiteFrFR requeteSite_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPageCsv(RequeteSiteFrFR requeteSite_, String o) {
		return DesignPage.staticSolrStrPageCsv(requeteSite_, DesignPage.staticSolrPageCsv(requeteSite_, DesignPage.staticSetPageCsv(requeteSite_, o)));
	}

	public Boolean solrPageCsv() {
		return DesignPage.staticSolrPageCsv(requeteSite_, pageCsv);
	}

	public String strPageCsv() {
		return pageCsv == null ? "" : pageCsv.toString();
	}

	public Boolean sqlPageCsv() {
		return pageCsv;
	}

	public String jsonPageCsv() {
		return pageCsv == null ? "" : pageCsv.toString();
	}

	public String nomAffichagePageCsv() {
		return "page CSV";
	}

	public String htmTooltipPageCsv() {
		return null;
	}

	public String htmPageCsv() {
		return pageCsv == null ? "" : StringEscapeUtils.escapeHtml4(strPageCsv());
	}

	public void inputPageCsv(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		if(
				CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRessource(), ROLES)
				|| CollectionUtils.containsAny(requeteSite_.getUtilisateurRolesRoyaume(), ROLES)
				) {
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_pageCsv")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_pageCsv");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPageCsv classDesignPage inputDesignPage", pk, "PageCsv w3-input w3-border ");
				a("name", "setPageCsv");
			} else {
				a("class", "valeurPageCsv classDesignPage inputDesignPage", pk, "PageCsv w3-input w3-border ");
				a("name", "pageCsv");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPageCsv', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_pageCsv')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_pageCsv')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPageCsv() != null && getPageCsv())
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
			e("span").a("class", "varDesignPage", pk, "PageCsv ").f().sx(htmPageCsv()).g("span");
		}
	}

	public void htmPageCsv(String classeApiMethodeMethode) {
		DesignPage s = (DesignPage)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "DesignPagePageCsv").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classeApiMethodeMethode, "_pageCsv").a("class", "").f().sx("page CSV").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPageCsv(classeApiMethodeMethode);
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
		designPageNomCompletInit();
		designCacheInit();
		designAdminInit();
		designIgnorerNomEnfantVideInit();
		designIgnorerPaiementsPasEnSouffranceInit();
		designIgnorerPaiementsEnSouffranceInit();
		designFiltrerInscriptionCleInit();
		designInscriptionTriMoisJourDeNaissanceInit();
		designInscriptionTriNomGroupeInit();
		designInscriptionTriNomEnfantInit();
		rechercherAnneesInit();
		rechercherPaiementsInit();
		rechercherPaiementsActuelInit();
		pagePdfInit();
		pageCsvInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinDesignPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteDesignPage(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
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
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
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
			case "designPageNomComplet":
				return oDesignPage.designPageNomComplet;
			case "designCache":
				return oDesignPage.designCache;
			case "designAdmin":
				return oDesignPage.designAdmin;
			case "designIgnorerNomEnfantVide":
				return oDesignPage.designIgnorerNomEnfantVide;
			case "designIgnorerPaiementsPasEnSouffrance":
				return oDesignPage.designIgnorerPaiementsPasEnSouffrance;
			case "designIgnorerPaiementsEnSouffrance":
				return oDesignPage.designIgnorerPaiementsEnSouffrance;
			case "designFiltrerInscriptionCle":
				return oDesignPage.designFiltrerInscriptionCle;
			case "designInscriptionTriMoisJourDeNaissance":
				return oDesignPage.designInscriptionTriMoisJourDeNaissance;
			case "designInscriptionTriNomGroupe":
				return oDesignPage.designInscriptionTriNomGroupe;
			case "designInscriptionTriNomEnfant":
				return oDesignPage.designInscriptionTriNomEnfant;
			case "rechercherAnnees":
				return oDesignPage.rechercherAnnees;
			case "rechercherPaiements":
				return oDesignPage.rechercherPaiements;
			case "rechercherPaiementsActuel":
				return oDesignPage.rechercherPaiementsActuel;
			case "pagePdf":
				return oDesignPage.pagePdf;
			case "pageCsv":
				return oDesignPage.pageCsv;
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
				if(!sauvegardes.contains("designEnfantCles"))
					sauvegardes.add("designEnfantCles");
				return val;
			case "designParentCles":
				oDesignPage.addDesignParentCles((Long)val);
				if(!sauvegardes.contains("designParentCles"))
					sauvegardes.add("designParentCles");
				return val;
			case "partHtmlCles":
				oDesignPage.addPartHtmlCles((Long)val);
				if(!sauvegardes.contains("partHtmlCles"))
					sauvegardes.add("partHtmlCles");
				return val;
			default:
				return super.attribuerCluster(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetDesignPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetDesignPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "designPageCle":
			return DesignPage.staticSetDesignPageCle(requeteSite_, o);
		case "designEnfantCles":
			return DesignPage.staticSetDesignEnfantCles(requeteSite_, o);
		case "designParentCles":
			return DesignPage.staticSetDesignParentCles(requeteSite_, o);
		case "partHtmlCles":
			return DesignPage.staticSetPartHtmlCles(requeteSite_, o);
		case "designPageNomComplet":
			return DesignPage.staticSetDesignPageNomComplet(requeteSite_, o);
		case "designCache":
			return DesignPage.staticSetDesignCache(requeteSite_, o);
		case "designAdmin":
			return DesignPage.staticSetDesignAdmin(requeteSite_, o);
		case "designIgnorerNomEnfantVide":
			return DesignPage.staticSetDesignIgnorerNomEnfantVide(requeteSite_, o);
		case "designIgnorerPaiementsPasEnSouffrance":
			return DesignPage.staticSetDesignIgnorerPaiementsPasEnSouffrance(requeteSite_, o);
		case "designIgnorerPaiementsEnSouffrance":
			return DesignPage.staticSetDesignIgnorerPaiementsEnSouffrance(requeteSite_, o);
		case "designFiltrerInscriptionCle":
			return DesignPage.staticSetDesignFiltrerInscriptionCle(requeteSite_, o);
		case "designInscriptionTriMoisJourDeNaissance":
			return DesignPage.staticSetDesignInscriptionTriMoisJourDeNaissance(requeteSite_, o);
		case "designInscriptionTriNomGroupe":
			return DesignPage.staticSetDesignInscriptionTriNomGroupe(requeteSite_, o);
		case "designInscriptionTriNomEnfant":
			return DesignPage.staticSetDesignInscriptionTriNomEnfant(requeteSite_, o);
		case "rechercherAnnees":
			return DesignPage.staticSetRechercherAnnees(requeteSite_, o);
		case "rechercherPaiements":
			return DesignPage.staticSetRechercherPaiements(requeteSite_, o);
		case "rechercherPaiementsActuel":
			return DesignPage.staticSetRechercherPaiementsActuel(requeteSite_, o);
		case "pagePdf":
			return DesignPage.staticSetPagePdf(requeteSite_, o);
		case "pageCsv":
			return DesignPage.staticSetPageCsv(requeteSite_, o);
			default:
				return Cluster.staticSetCluster(entiteVar,  requeteSite_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrDesignPage(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrDesignPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "designPageCle":
			return DesignPage.staticSolrDesignPageCle(requeteSite_, (Long)o);
		case "designEnfantCles":
			return DesignPage.staticSolrDesignEnfantCles(requeteSite_, (Long)o);
		case "designParentCles":
			return DesignPage.staticSolrDesignParentCles(requeteSite_, (Long)o);
		case "partHtmlCles":
			return DesignPage.staticSolrPartHtmlCles(requeteSite_, (Long)o);
		case "designPageNomComplet":
			return DesignPage.staticSolrDesignPageNomComplet(requeteSite_, (String)o);
		case "designCache":
			return DesignPage.staticSolrDesignCache(requeteSite_, (Boolean)o);
		case "designAdmin":
			return DesignPage.staticSolrDesignAdmin(requeteSite_, (Boolean)o);
		case "designIgnorerNomEnfantVide":
			return DesignPage.staticSolrDesignIgnorerNomEnfantVide(requeteSite_, (Boolean)o);
		case "designIgnorerPaiementsPasEnSouffrance":
			return DesignPage.staticSolrDesignIgnorerPaiementsPasEnSouffrance(requeteSite_, (Boolean)o);
		case "designIgnorerPaiementsEnSouffrance":
			return DesignPage.staticSolrDesignIgnorerPaiementsEnSouffrance(requeteSite_, (Boolean)o);
		case "designFiltrerInscriptionCle":
			return DesignPage.staticSolrDesignFiltrerInscriptionCle(requeteSite_, (Boolean)o);
		case "designInscriptionTriMoisJourDeNaissance":
			return DesignPage.staticSolrDesignInscriptionTriMoisJourDeNaissance(requeteSite_, (Boolean)o);
		case "designInscriptionTriNomGroupe":
			return DesignPage.staticSolrDesignInscriptionTriNomGroupe(requeteSite_, (Boolean)o);
		case "designInscriptionTriNomEnfant":
			return DesignPage.staticSolrDesignInscriptionTriNomEnfant(requeteSite_, (Boolean)o);
		case "rechercherAnnees":
			return DesignPage.staticSolrRechercherAnnees(requeteSite_, (Boolean)o);
		case "rechercherPaiements":
			return DesignPage.staticSolrRechercherPaiements(requeteSite_, (Boolean)o);
		case "rechercherPaiementsActuel":
			return DesignPage.staticSolrRechercherPaiementsActuel(requeteSite_, (Boolean)o);
		case "pagePdf":
			return DesignPage.staticSolrPagePdf(requeteSite_, (Boolean)o);
		case "pageCsv":
			return DesignPage.staticSolrPageCsv(requeteSite_, (Boolean)o);
			default:
				return Cluster.staticSolrCluster(entiteVar,  requeteSite_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrDesignPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrDesignPage(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "designPageCle":
			return DesignPage.staticSolrStrDesignPageCle(requeteSite_, (Long)o);
		case "designEnfantCles":
			return DesignPage.staticSolrStrDesignEnfantCles(requeteSite_, (Long)o);
		case "designParentCles":
			return DesignPage.staticSolrStrDesignParentCles(requeteSite_, (Long)o);
		case "partHtmlCles":
			return DesignPage.staticSolrStrPartHtmlCles(requeteSite_, (Long)o);
		case "designPageNomComplet":
			return DesignPage.staticSolrStrDesignPageNomComplet(requeteSite_, (String)o);
		case "designCache":
			return DesignPage.staticSolrStrDesignCache(requeteSite_, (Boolean)o);
		case "designAdmin":
			return DesignPage.staticSolrStrDesignAdmin(requeteSite_, (Boolean)o);
		case "designIgnorerNomEnfantVide":
			return DesignPage.staticSolrStrDesignIgnorerNomEnfantVide(requeteSite_, (Boolean)o);
		case "designIgnorerPaiementsPasEnSouffrance":
			return DesignPage.staticSolrStrDesignIgnorerPaiementsPasEnSouffrance(requeteSite_, (Boolean)o);
		case "designIgnorerPaiementsEnSouffrance":
			return DesignPage.staticSolrStrDesignIgnorerPaiementsEnSouffrance(requeteSite_, (Boolean)o);
		case "designFiltrerInscriptionCle":
			return DesignPage.staticSolrStrDesignFiltrerInscriptionCle(requeteSite_, (Boolean)o);
		case "designInscriptionTriMoisJourDeNaissance":
			return DesignPage.staticSolrStrDesignInscriptionTriMoisJourDeNaissance(requeteSite_, (Boolean)o);
		case "designInscriptionTriNomGroupe":
			return DesignPage.staticSolrStrDesignInscriptionTriNomGroupe(requeteSite_, (Boolean)o);
		case "designInscriptionTriNomEnfant":
			return DesignPage.staticSolrStrDesignInscriptionTriNomEnfant(requeteSite_, (Boolean)o);
		case "rechercherAnnees":
			return DesignPage.staticSolrStrRechercherAnnees(requeteSite_, (Boolean)o);
		case "rechercherPaiements":
			return DesignPage.staticSolrStrRechercherPaiements(requeteSite_, (Boolean)o);
		case "rechercherPaiementsActuel":
			return DesignPage.staticSolrStrRechercherPaiementsActuel(requeteSite_, (Boolean)o);
		case "pagePdf":
			return DesignPage.staticSolrStrPagePdf(requeteSite_, (Boolean)o);
		case "pageCsv":
			return DesignPage.staticSolrStrPageCsv(requeteSite_, (Boolean)o);
			default:
				return Cluster.staticSolrStrCluster(entiteVar,  requeteSite_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqDesignPage(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqDesignPage(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "designPageCle":
			return DesignPage.staticSolrFqDesignPageCle(requeteSite_, o);
		case "designEnfantCles":
			return DesignPage.staticSolrFqDesignEnfantCles(requeteSite_, o);
		case "designParentCles":
			return DesignPage.staticSolrFqDesignParentCles(requeteSite_, o);
		case "partHtmlCles":
			return DesignPage.staticSolrFqPartHtmlCles(requeteSite_, o);
		case "designPageNomComplet":
			return DesignPage.staticSolrFqDesignPageNomComplet(requeteSite_, o);
		case "designCache":
			return DesignPage.staticSolrFqDesignCache(requeteSite_, o);
		case "designAdmin":
			return DesignPage.staticSolrFqDesignAdmin(requeteSite_, o);
		case "designIgnorerNomEnfantVide":
			return DesignPage.staticSolrFqDesignIgnorerNomEnfantVide(requeteSite_, o);
		case "designIgnorerPaiementsPasEnSouffrance":
			return DesignPage.staticSolrFqDesignIgnorerPaiementsPasEnSouffrance(requeteSite_, o);
		case "designIgnorerPaiementsEnSouffrance":
			return DesignPage.staticSolrFqDesignIgnorerPaiementsEnSouffrance(requeteSite_, o);
		case "designFiltrerInscriptionCle":
			return DesignPage.staticSolrFqDesignFiltrerInscriptionCle(requeteSite_, o);
		case "designInscriptionTriMoisJourDeNaissance":
			return DesignPage.staticSolrFqDesignInscriptionTriMoisJourDeNaissance(requeteSite_, o);
		case "designInscriptionTriNomGroupe":
			return DesignPage.staticSolrFqDesignInscriptionTriNomGroupe(requeteSite_, o);
		case "designInscriptionTriNomEnfant":
			return DesignPage.staticSolrFqDesignInscriptionTriNomEnfant(requeteSite_, o);
		case "rechercherAnnees":
			return DesignPage.staticSolrFqRechercherAnnees(requeteSite_, o);
		case "rechercherPaiements":
			return DesignPage.staticSolrFqRechercherPaiements(requeteSite_, o);
		case "rechercherPaiementsActuel":
			return DesignPage.staticSolrFqRechercherPaiementsActuel(requeteSite_, o);
		case "pagePdf":
			return DesignPage.staticSolrFqPagePdf(requeteSite_, o);
		case "pageCsv":
			return DesignPage.staticSolrFqPageCsv(requeteSite_, o);
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
					o = definirDesignPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirDesignPage(String var, String val) {
		switch(var.toLowerCase()) {
			case "designpagenomcomplet":
				if(val != null)
					setDesignPageNomComplet(val);
				sauvegardes.add("designPageNomComplet");
				return val;
			case "designcache":
				if(val != null)
					setDesignCache(val);
				sauvegardes.add("designCache");
				return val;
			case "designadmin":
				if(val != null)
					setDesignAdmin(val);
				sauvegardes.add("designAdmin");
				return val;
			case "designignorernomenfantvide":
				if(val != null)
					setDesignIgnorerNomEnfantVide(val);
				sauvegardes.add("designIgnorerNomEnfantVide");
				return val;
			case "designignorerpaiementspasensouffrance":
				if(val != null)
					setDesignIgnorerPaiementsPasEnSouffrance(val);
				sauvegardes.add("designIgnorerPaiementsPasEnSouffrance");
				return val;
			case "designignorerpaiementsensouffrance":
				if(val != null)
					setDesignIgnorerPaiementsEnSouffrance(val);
				sauvegardes.add("designIgnorerPaiementsEnSouffrance");
				return val;
			case "designfiltrerinscriptioncle":
				if(val != null)
					setDesignFiltrerInscriptionCle(val);
				sauvegardes.add("designFiltrerInscriptionCle");
				return val;
			case "designinscriptiontrimoisjourdenaissance":
				if(val != null)
					setDesignInscriptionTriMoisJourDeNaissance(val);
				sauvegardes.add("designInscriptionTriMoisJourDeNaissance");
				return val;
			case "designinscriptiontrinomgroupe":
				if(val != null)
					setDesignInscriptionTriNomGroupe(val);
				sauvegardes.add("designInscriptionTriNomGroupe");
				return val;
			case "designinscriptiontrinomenfant":
				if(val != null)
					setDesignInscriptionTriNomEnfant(val);
				sauvegardes.add("designInscriptionTriNomEnfant");
				return val;
			case "rechercherannees":
				if(val != null)
					setRechercherAnnees(val);
				sauvegardes.add("rechercherAnnees");
				return val;
			case "rechercherpaiements":
				if(val != null)
					setRechercherPaiements(val);
				sauvegardes.add("rechercherPaiements");
				return val;
			case "rechercherpaiementsactuel":
				if(val != null)
					setRechercherPaiementsActuel(val);
				sauvegardes.add("rechercherPaiementsActuel");
				return val;
			case "pagepdf":
				if(val != null)
					setPagePdf(val);
				sauvegardes.add("pagePdf");
				return val;
			case "pagecsv":
				if(val != null)
					setPageCsv(val);
				sauvegardes.add("pageCsv");
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	@Override public boolean definirPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirDesignPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirDesignPage(String var, Object val) {
		switch(var.toLowerCase()) {
			case "designpagenomcomplet":
				if(val instanceof String)
					setDesignPageNomComplet((String)val);
				sauvegardes.add("designPageNomComplet");
				return val;
			case "designcache":
				if(val instanceof Boolean)
					setDesignCache((Boolean)val);
				sauvegardes.add("designCache");
				return val;
			case "designadmin":
				if(val instanceof Boolean)
					setDesignAdmin((Boolean)val);
				sauvegardes.add("designAdmin");
				return val;
			case "designignorernomenfantvide":
				if(val instanceof Boolean)
					setDesignIgnorerNomEnfantVide((Boolean)val);
				sauvegardes.add("designIgnorerNomEnfantVide");
				return val;
			case "designignorerpaiementspasensouffrance":
				if(val instanceof Boolean)
					setDesignIgnorerPaiementsPasEnSouffrance((Boolean)val);
				sauvegardes.add("designIgnorerPaiementsPasEnSouffrance");
				return val;
			case "designignorerpaiementsensouffrance":
				if(val instanceof Boolean)
					setDesignIgnorerPaiementsEnSouffrance((Boolean)val);
				sauvegardes.add("designIgnorerPaiementsEnSouffrance");
				return val;
			case "designfiltrerinscriptioncle":
				if(val instanceof Boolean)
					setDesignFiltrerInscriptionCle((Boolean)val);
				sauvegardes.add("designFiltrerInscriptionCle");
				return val;
			case "designinscriptiontrimoisjourdenaissance":
				if(val instanceof Boolean)
					setDesignInscriptionTriMoisJourDeNaissance((Boolean)val);
				sauvegardes.add("designInscriptionTriMoisJourDeNaissance");
				return val;
			case "designinscriptiontrinomgroupe":
				if(val instanceof Boolean)
					setDesignInscriptionTriNomGroupe((Boolean)val);
				sauvegardes.add("designInscriptionTriNomGroupe");
				return val;
			case "designinscriptiontrinomenfant":
				if(val instanceof Boolean)
					setDesignInscriptionTriNomEnfant((Boolean)val);
				sauvegardes.add("designInscriptionTriNomEnfant");
				return val;
			case "rechercherannees":
				if(val instanceof Boolean)
					setRechercherAnnees((Boolean)val);
				sauvegardes.add("rechercherAnnees");
				return val;
			case "rechercherpaiements":
				if(val instanceof Boolean)
					setRechercherPaiements((Boolean)val);
				sauvegardes.add("rechercherPaiements");
				return val;
			case "rechercherpaiementsactuel":
				if(val instanceof Boolean)
					setRechercherPaiementsActuel((Boolean)val);
				sauvegardes.add("rechercherPaiementsActuel");
				return val;
			case "pagepdf":
				if(val instanceof Boolean)
					setPagePdf((Boolean)val);
				sauvegardes.add("pagePdf");
				return val;
			case "pagecsv":
				if(val instanceof Boolean)
					setPageCsv((Boolean)val);
				sauvegardes.add("pageCsv");
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerDesignPage(solrDocument);
	}
	public void peuplerDesignPage(SolrDocument solrDocument) {
		DesignPage oDesignPage = (DesignPage)this;
		sauvegardes = (List<String>)solrDocument.get("sauvegardes_stored_strings");
		if(sauvegardes != null) {

			if(sauvegardes.contains("designPageCle")) {
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

			if(sauvegardes.contains("designPageNomComplet")) {
				String designPageNomComplet = (String)solrDocument.get("designPageNomComplet_stored_string");
				if(designPageNomComplet != null)
					oDesignPage.setDesignPageNomComplet(designPageNomComplet);
			}

			if(sauvegardes.contains("designCache")) {
				Boolean designCache = (Boolean)solrDocument.get("designCache_stored_boolean");
				if(designCache != null)
					oDesignPage.setDesignCache(designCache);
			}

			if(sauvegardes.contains("designAdmin")) {
				Boolean designAdmin = (Boolean)solrDocument.get("designAdmin_stored_boolean");
				if(designAdmin != null)
					oDesignPage.setDesignAdmin(designAdmin);
			}

			if(sauvegardes.contains("designIgnorerNomEnfantVide")) {
				Boolean designIgnorerNomEnfantVide = (Boolean)solrDocument.get("designIgnorerNomEnfantVide_stored_boolean");
				if(designIgnorerNomEnfantVide != null)
					oDesignPage.setDesignIgnorerNomEnfantVide(designIgnorerNomEnfantVide);
			}

			if(sauvegardes.contains("designIgnorerPaiementsPasEnSouffrance")) {
				Boolean designIgnorerPaiementsPasEnSouffrance = (Boolean)solrDocument.get("designIgnorerPaiementsPasEnSouffrance_stored_boolean");
				if(designIgnorerPaiementsPasEnSouffrance != null)
					oDesignPage.setDesignIgnorerPaiementsPasEnSouffrance(designIgnorerPaiementsPasEnSouffrance);
			}

			if(sauvegardes.contains("designIgnorerPaiementsEnSouffrance")) {
				Boolean designIgnorerPaiementsEnSouffrance = (Boolean)solrDocument.get("designIgnorerPaiementsEnSouffrance_stored_boolean");
				if(designIgnorerPaiementsEnSouffrance != null)
					oDesignPage.setDesignIgnorerPaiementsEnSouffrance(designIgnorerPaiementsEnSouffrance);
			}

			if(sauvegardes.contains("designFiltrerInscriptionCle")) {
				Boolean designFiltrerInscriptionCle = (Boolean)solrDocument.get("designFiltrerInscriptionCle_stored_boolean");
				if(designFiltrerInscriptionCle != null)
					oDesignPage.setDesignFiltrerInscriptionCle(designFiltrerInscriptionCle);
			}

			if(sauvegardes.contains("designInscriptionTriMoisJourDeNaissance")) {
				Boolean designInscriptionTriMoisJourDeNaissance = (Boolean)solrDocument.get("designInscriptionTriMoisJourDeNaissance_stored_boolean");
				if(designInscriptionTriMoisJourDeNaissance != null)
					oDesignPage.setDesignInscriptionTriMoisJourDeNaissance(designInscriptionTriMoisJourDeNaissance);
			}

			if(sauvegardes.contains("designInscriptionTriNomGroupe")) {
				Boolean designInscriptionTriNomGroupe = (Boolean)solrDocument.get("designInscriptionTriNomGroupe_stored_boolean");
				if(designInscriptionTriNomGroupe != null)
					oDesignPage.setDesignInscriptionTriNomGroupe(designInscriptionTriNomGroupe);
			}

			if(sauvegardes.contains("designInscriptionTriNomEnfant")) {
				Boolean designInscriptionTriNomEnfant = (Boolean)solrDocument.get("designInscriptionTriNomEnfant_stored_boolean");
				if(designInscriptionTriNomEnfant != null)
					oDesignPage.setDesignInscriptionTriNomEnfant(designInscriptionTriNomEnfant);
			}

			if(sauvegardes.contains("rechercherAnnees")) {
				Boolean rechercherAnnees = (Boolean)solrDocument.get("rechercherAnnees_stored_boolean");
				if(rechercherAnnees != null)
					oDesignPage.setRechercherAnnees(rechercherAnnees);
			}

			if(sauvegardes.contains("rechercherPaiements")) {
				Boolean rechercherPaiements = (Boolean)solrDocument.get("rechercherPaiements_stored_boolean");
				if(rechercherPaiements != null)
					oDesignPage.setRechercherPaiements(rechercherPaiements);
			}

			if(sauvegardes.contains("rechercherPaiementsActuel")) {
				Boolean rechercherPaiementsActuel = (Boolean)solrDocument.get("rechercherPaiementsActuel_stored_boolean");
				if(rechercherPaiementsActuel != null)
					oDesignPage.setRechercherPaiementsActuel(rechercherPaiementsActuel);
			}

			if(sauvegardes.contains("pagePdf")) {
				Boolean pagePdf = (Boolean)solrDocument.get("pagePdf_stored_boolean");
				if(pagePdf != null)
					oDesignPage.setPagePdf(pagePdf);
			}

			if(sauvegardes.contains("pageCsv")) {
				Boolean pageCsv = (Boolean)solrDocument.get("pageCsv_stored_boolean");
				if(pageCsv != null)
					oDesignPage.setPageCsv(pageCsv);
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
		if(designAdmin != null) {
			document.addField("designAdmin_indexed_boolean", designAdmin);
			document.addField("designAdmin_stored_boolean", designAdmin);
		}
		if(designIgnorerNomEnfantVide != null) {
			document.addField("designIgnorerNomEnfantVide_indexed_boolean", designIgnorerNomEnfantVide);
			document.addField("designIgnorerNomEnfantVide_stored_boolean", designIgnorerNomEnfantVide);
		}
		if(designIgnorerPaiementsPasEnSouffrance != null) {
			document.addField("designIgnorerPaiementsPasEnSouffrance_indexed_boolean", designIgnorerPaiementsPasEnSouffrance);
			document.addField("designIgnorerPaiementsPasEnSouffrance_stored_boolean", designIgnorerPaiementsPasEnSouffrance);
		}
		if(designIgnorerPaiementsEnSouffrance != null) {
			document.addField("designIgnorerPaiementsEnSouffrance_indexed_boolean", designIgnorerPaiementsEnSouffrance);
			document.addField("designIgnorerPaiementsEnSouffrance_stored_boolean", designIgnorerPaiementsEnSouffrance);
		}
		if(designFiltrerInscriptionCle != null) {
			document.addField("designFiltrerInscriptionCle_indexed_boolean", designFiltrerInscriptionCle);
			document.addField("designFiltrerInscriptionCle_stored_boolean", designFiltrerInscriptionCle);
		}
		if(designInscriptionTriMoisJourDeNaissance != null) {
			document.addField("designInscriptionTriMoisJourDeNaissance_indexed_boolean", designInscriptionTriMoisJourDeNaissance);
			document.addField("designInscriptionTriMoisJourDeNaissance_stored_boolean", designInscriptionTriMoisJourDeNaissance);
		}
		if(designInscriptionTriNomGroupe != null) {
			document.addField("designInscriptionTriNomGroupe_indexed_boolean", designInscriptionTriNomGroupe);
			document.addField("designInscriptionTriNomGroupe_stored_boolean", designInscriptionTriNomGroupe);
		}
		if(designInscriptionTriNomEnfant != null) {
			document.addField("designInscriptionTriNomEnfant_indexed_boolean", designInscriptionTriNomEnfant);
			document.addField("designInscriptionTriNomEnfant_stored_boolean", designInscriptionTriNomEnfant);
		}
		if(rechercherAnnees != null) {
			document.addField("rechercherAnnees_indexed_boolean", rechercherAnnees);
			document.addField("rechercherAnnees_stored_boolean", rechercherAnnees);
		}
		if(rechercherPaiements != null) {
			document.addField("rechercherPaiements_indexed_boolean", rechercherPaiements);
			document.addField("rechercherPaiements_stored_boolean", rechercherPaiements);
		}
		if(rechercherPaiementsActuel != null) {
			document.addField("rechercherPaiementsActuel_indexed_boolean", rechercherPaiementsActuel);
			document.addField("rechercherPaiementsActuel_stored_boolean", rechercherPaiementsActuel);
		}
		if(pagePdf != null) {
			document.addField("pagePdf_indexed_boolean", pagePdf);
			document.addField("pagePdf_stored_boolean", pagePdf);
		}
		if(pageCsv != null) {
			document.addField("pageCsv_indexed_boolean", pageCsv);
			document.addField("pageCsv_stored_boolean", pageCsv);
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
			case "designAdmin":
				return "designAdmin_indexed_boolean";
			case "designIgnorerNomEnfantVide":
				return "designIgnorerNomEnfantVide_indexed_boolean";
			case "designIgnorerPaiementsPasEnSouffrance":
				return "designIgnorerPaiementsPasEnSouffrance_indexed_boolean";
			case "designIgnorerPaiementsEnSouffrance":
				return "designIgnorerPaiementsEnSouffrance_indexed_boolean";
			case "designFiltrerInscriptionCle":
				return "designFiltrerInscriptionCle_indexed_boolean";
			case "designInscriptionTriMoisJourDeNaissance":
				return "designInscriptionTriMoisJourDeNaissance_indexed_boolean";
			case "designInscriptionTriNomGroupe":
				return "designInscriptionTriNomGroupe_indexed_boolean";
			case "designInscriptionTriNomEnfant":
				return "designInscriptionTriNomEnfant_indexed_boolean";
			case "rechercherAnnees":
				return "rechercherAnnees_indexed_boolean";
			case "rechercherPaiements":
				return "rechercherPaiements_indexed_boolean";
			case "rechercherPaiementsActuel":
				return "rechercherPaiementsActuel_indexed_boolean";
			case "pagePdf":
				return "pagePdf_indexed_boolean";
			case "pageCsv":
				return "pageCsv_indexed_boolean";
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

		Boolean designAdmin = (Boolean)solrDocument.get("designAdmin_stored_boolean");
		if(designAdmin != null)
			oDesignPage.setDesignAdmin(designAdmin);

		Boolean designIgnorerNomEnfantVide = (Boolean)solrDocument.get("designIgnorerNomEnfantVide_stored_boolean");
		if(designIgnorerNomEnfantVide != null)
			oDesignPage.setDesignIgnorerNomEnfantVide(designIgnorerNomEnfantVide);

		Boolean designIgnorerPaiementsPasEnSouffrance = (Boolean)solrDocument.get("designIgnorerPaiementsPasEnSouffrance_stored_boolean");
		if(designIgnorerPaiementsPasEnSouffrance != null)
			oDesignPage.setDesignIgnorerPaiementsPasEnSouffrance(designIgnorerPaiementsPasEnSouffrance);

		Boolean designIgnorerPaiementsEnSouffrance = (Boolean)solrDocument.get("designIgnorerPaiementsEnSouffrance_stored_boolean");
		if(designIgnorerPaiementsEnSouffrance != null)
			oDesignPage.setDesignIgnorerPaiementsEnSouffrance(designIgnorerPaiementsEnSouffrance);

		Boolean designFiltrerInscriptionCle = (Boolean)solrDocument.get("designFiltrerInscriptionCle_stored_boolean");
		if(designFiltrerInscriptionCle != null)
			oDesignPage.setDesignFiltrerInscriptionCle(designFiltrerInscriptionCle);

		Boolean designInscriptionTriMoisJourDeNaissance = (Boolean)solrDocument.get("designInscriptionTriMoisJourDeNaissance_stored_boolean");
		if(designInscriptionTriMoisJourDeNaissance != null)
			oDesignPage.setDesignInscriptionTriMoisJourDeNaissance(designInscriptionTriMoisJourDeNaissance);

		Boolean designInscriptionTriNomGroupe = (Boolean)solrDocument.get("designInscriptionTriNomGroupe_stored_boolean");
		if(designInscriptionTriNomGroupe != null)
			oDesignPage.setDesignInscriptionTriNomGroupe(designInscriptionTriNomGroupe);

		Boolean designInscriptionTriNomEnfant = (Boolean)solrDocument.get("designInscriptionTriNomEnfant_stored_boolean");
		if(designInscriptionTriNomEnfant != null)
			oDesignPage.setDesignInscriptionTriNomEnfant(designInscriptionTriNomEnfant);

		Boolean rechercherAnnees = (Boolean)solrDocument.get("rechercherAnnees_stored_boolean");
		if(rechercherAnnees != null)
			oDesignPage.setRechercherAnnees(rechercherAnnees);

		Boolean rechercherPaiements = (Boolean)solrDocument.get("rechercherPaiements_stored_boolean");
		if(rechercherPaiements != null)
			oDesignPage.setRechercherPaiements(rechercherPaiements);

		Boolean rechercherPaiementsActuel = (Boolean)solrDocument.get("rechercherPaiementsActuel_stored_boolean");
		if(rechercherPaiementsActuel != null)
			oDesignPage.setRechercherPaiementsActuel(rechercherPaiementsActuel);

		Boolean pagePdf = (Boolean)solrDocument.get("pagePdf_stored_boolean");
		if(pagePdf != null)
			oDesignPage.setPagePdf(pagePdf);

		Boolean pageCsv = (Boolean)solrDocument.get("pageCsv_stored_boolean");
		if(pageCsv != null)
			oDesignPage.setPageCsv(pageCsv);

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
			if(!Objects.equals(designPageCle, original.getDesignPageCle()))
				requeteApi.addVars("designPageCle");
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
			if(!Objects.equals(designAdmin, original.getDesignAdmin()))
				requeteApi.addVars("designAdmin");
			if(!Objects.equals(designIgnorerNomEnfantVide, original.getDesignIgnorerNomEnfantVide()))
				requeteApi.addVars("designIgnorerNomEnfantVide");
			if(!Objects.equals(designIgnorerPaiementsPasEnSouffrance, original.getDesignIgnorerPaiementsPasEnSouffrance()))
				requeteApi.addVars("designIgnorerPaiementsPasEnSouffrance");
			if(!Objects.equals(designIgnorerPaiementsEnSouffrance, original.getDesignIgnorerPaiementsEnSouffrance()))
				requeteApi.addVars("designIgnorerPaiementsEnSouffrance");
			if(!Objects.equals(designFiltrerInscriptionCle, original.getDesignFiltrerInscriptionCle()))
				requeteApi.addVars("designFiltrerInscriptionCle");
			if(!Objects.equals(designInscriptionTriMoisJourDeNaissance, original.getDesignInscriptionTriMoisJourDeNaissance()))
				requeteApi.addVars("designInscriptionTriMoisJourDeNaissance");
			if(!Objects.equals(designInscriptionTriNomGroupe, original.getDesignInscriptionTriNomGroupe()))
				requeteApi.addVars("designInscriptionTriNomGroupe");
			if(!Objects.equals(designInscriptionTriNomEnfant, original.getDesignInscriptionTriNomEnfant()))
				requeteApi.addVars("designInscriptionTriNomEnfant");
			if(!Objects.equals(rechercherAnnees, original.getRechercherAnnees()))
				requeteApi.addVars("rechercherAnnees");
			if(!Objects.equals(rechercherPaiements, original.getRechercherPaiements()))
				requeteApi.addVars("rechercherPaiements");
			if(!Objects.equals(rechercherPaiementsActuel, original.getRechercherPaiementsActuel()))
				requeteApi.addVars("rechercherPaiementsActuel");
			if(!Objects.equals(pagePdf, original.getPagePdf()))
				requeteApi.addVars("pagePdf");
			if(!Objects.equals(pageCsv, original.getPageCsv()))
				requeteApi.addVars("pageCsv");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), designPageCle, designEnfantCles, designParentCles, partHtmlCles, designPageNomComplet, designCache, designAdmin, designIgnorerNomEnfantVide, designIgnorerPaiementsPasEnSouffrance, designIgnorerPaiementsEnSouffrance, designFiltrerInscriptionCle, designInscriptionTriMoisJourDeNaissance, designInscriptionTriNomGroupe, designInscriptionTriNomEnfant, rechercherAnnees, rechercherPaiements, rechercherPaiementsActuel, pagePdf, pageCsv);
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
				&& Objects.equals( designPageCle, that.designPageCle )
				&& Objects.equals( designEnfantCles, that.designEnfantCles )
				&& Objects.equals( designParentCles, that.designParentCles )
				&& Objects.equals( partHtmlCles, that.partHtmlCles )
				&& Objects.equals( designPageNomComplet, that.designPageNomComplet )
				&& Objects.equals( designCache, that.designCache )
				&& Objects.equals( designAdmin, that.designAdmin )
				&& Objects.equals( designIgnorerNomEnfantVide, that.designIgnorerNomEnfantVide )
				&& Objects.equals( designIgnorerPaiementsPasEnSouffrance, that.designIgnorerPaiementsPasEnSouffrance )
				&& Objects.equals( designIgnorerPaiementsEnSouffrance, that.designIgnorerPaiementsEnSouffrance )
				&& Objects.equals( designFiltrerInscriptionCle, that.designFiltrerInscriptionCle )
				&& Objects.equals( designInscriptionTriMoisJourDeNaissance, that.designInscriptionTriMoisJourDeNaissance )
				&& Objects.equals( designInscriptionTriNomGroupe, that.designInscriptionTriNomGroupe )
				&& Objects.equals( designInscriptionTriNomEnfant, that.designInscriptionTriNomEnfant )
				&& Objects.equals( rechercherAnnees, that.rechercherAnnees )
				&& Objects.equals( rechercherPaiements, that.rechercherPaiements )
				&& Objects.equals( rechercherPaiementsActuel, that.rechercherPaiementsActuel )
				&& Objects.equals( pagePdf, that.pagePdf )
				&& Objects.equals( pageCsv, that.pageCsv );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DesignPage { ");
		sb.append( "designPageCle: " ).append(designPageCle);
		sb.append( ", designEnfantCles: " ).append(designEnfantCles);
		sb.append( ", designParentCles: " ).append(designParentCles);
		sb.append( ", partHtmlCles: " ).append(partHtmlCles);
		sb.append( ", designPageNomComplet: \"" ).append(designPageNomComplet).append( "\"" );
		sb.append( ", designCache: " ).append(designCache);
		sb.append( ", designAdmin: " ).append(designAdmin);
		sb.append( ", designIgnorerNomEnfantVide: " ).append(designIgnorerNomEnfantVide);
		sb.append( ", designIgnorerPaiementsPasEnSouffrance: " ).append(designIgnorerPaiementsPasEnSouffrance);
		sb.append( ", designIgnorerPaiementsEnSouffrance: " ).append(designIgnorerPaiementsEnSouffrance);
		sb.append( ", designFiltrerInscriptionCle: " ).append(designFiltrerInscriptionCle);
		sb.append( ", designInscriptionTriMoisJourDeNaissance: " ).append(designInscriptionTriMoisJourDeNaissance);
		sb.append( ", designInscriptionTriNomGroupe: " ).append(designInscriptionTriNomGroupe);
		sb.append( ", designInscriptionTriNomEnfant: " ).append(designInscriptionTriNomEnfant);
		sb.append( ", rechercherAnnees: " ).append(rechercherAnnees);
		sb.append( ", rechercherPaiements: " ).append(rechercherPaiements);
		sb.append( ", rechercherPaiementsActuel: " ).append(rechercherPaiementsActuel);
		sb.append( ", pagePdf: " ).append(pagePdf);
		sb.append( ", pageCsv: " ).append(pageCsv);
		sb.append(" }");
		return sb.toString();
	}
}
