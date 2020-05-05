package org.computate.scolaire.frFR.html.part;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.lang.Double;
import java.util.Date;
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
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true">Trouver la classe sort10 dans Solr</a>
 * <br/>
 **/
public abstract class PartHtmlGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PartHtml.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String PartHtml_UnNom = "un part de HTML";
	public static final String PartHtml_Ce = "ce ";
	public static final String PartHtml_CeNom = "ce part de HTML";
	public static final String PartHtml_Un = "un ";
	public static final String PartHtml_LeNom = "le part de HTML";
	public static final String PartHtml_NomSingulier = "part de HTML";
	public static final String PartHtml_NomPluriel = "part de HTMLs";
	public static final String PartHtml_NomActuel = "part de HTML actuel";
	public static final String PartHtml_Tous = "all ";
	public static final String PartHtml_TousNom = "tous les part de HTMLs";
	public static final String PartHtml_RechercherTousNomPar = "rechercher part de HTMLs par ";
	public static final String PartHtml_RechercherTousNom = "rechercher part de HTMLs";
	public static final String PartHtml_LesNom = "les part de HTMLs";
	public static final String PartHtml_AucunNomTrouve = "aucun part de HTML trouvé";
	public static final String PartHtml_NomVar = "part-html";
	public static final String PartHtml_DeNom = "de part de HTML";
	public static final String PartHtml_NomAdjectifSingulier = "part de HTML";
	public static final String PartHtml_NomAdjectifPluriel = "part de HTMLs";
	public static final String PartHtml_Couleur = "yellow";
	public static final String PartHtml_IconeGroupe = "regular";
	public static final String PartHtml_IconeNom = "sun";
	public static final Integer PartHtml_Lignes = 300;

	/////////////////
	// partHtmlCle //
	/////////////////

	/**	L'entité « partHtmlCle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long partHtmlCle;
	@JsonIgnore
	public Couverture<Long> partHtmlCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("partHtmlCle").o(partHtmlCle);

	/**	<br/>L'entité « partHtmlCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:partHtmlCle">Trouver l'entité partHtmlCle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _partHtmlCle(Couverture<Long> c);

	public Long getPartHtmlCle() {
		return partHtmlCle;
	}

	public void setPartHtmlCle(Long partHtmlCle) {
		this.partHtmlCle = partHtmlCle;
		this.partHtmlCleCouverture.dejaInitialise = true;
	}
	public PartHtml setPartHtmlCle(String o) {
		if(NumberUtils.isParsable(o))
			this.partHtmlCle = Long.parseLong(o);
		this.partHtmlCleCouverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml partHtmlCleInit() {
		if(!partHtmlCleCouverture.dejaInitialise) {
			_partHtmlCle(partHtmlCleCouverture);
			if(partHtmlCle == null)
				setPartHtmlCle(partHtmlCleCouverture.o);
		}
		partHtmlCleCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Long solrPartHtmlCle() {
		return partHtmlCle;
	}

	public String strPartHtmlCle() {
		return partHtmlCle == null ? "" : partHtmlCle.toString();
	}

	public String jsonPartHtmlCle() {
		return partHtmlCle == null ? "" : partHtmlCle.toString();
	}

	public String nomAffichagePartHtmlCle() {
		return "clé";
	}

	public String htmTooltipPartHtmlCle() {
		return null;
	}

	public String htmPartHtmlCle() {
		return partHtmlCle == null ? "" : StringEscapeUtils.escapeHtml4(strPartHtmlCle());
	}

	////////////////////
	// designPageCles //
	////////////////////

	/**	L'entité « designPageCles »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> designPageCles = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Couverture<List<Long>> designPageClesCouverture = new Couverture<List<Long>>().p(this).c(List.class).var("designPageCles").o(designPageCles);

	/**	<br/>L'entité « designPageCles »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designPageCles">Trouver l'entité designPageCles dans Solr</a>
	 * <br/>
	 * @param designPageCles est l'entité déjà construit. 
	 **/
	protected abstract void _designPageCles(List<Long> l);

	public List<Long> getDesignPageCles() {
		return designPageCles;
	}

	public void setDesignPageCles(List<Long> designPageCles) {
		this.designPageCles = designPageCles;
		this.designPageClesCouverture.dejaInitialise = true;
	}
	public PartHtml addDesignPageCles(Long...objets) {
		for(Long o : objets) {
			addDesignPageCles(o);
		}
		return (PartHtml)this;
	}
	public PartHtml addDesignPageCles(Long o) {
		if(o != null && !designPageCles.contains(o))
			this.designPageCles.add(o);
		return (PartHtml)this;
	}
	public PartHtml setDesignPageCles(JsonArray objets) {
		designPageCles.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addDesignPageCles(o);
		}
		return (PartHtml)this;
	}
	public PartHtml addDesignPageCles(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addDesignPageCles(p);
		}
		return (PartHtml)this;
	}
	protected PartHtml designPageClesInit() {
		if(!designPageClesCouverture.dejaInitialise) {
			_designPageCles(designPageCles);
		}
		designPageClesCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public List<Long> solrDesignPageCles() {
		return designPageCles;
	}

	public String strDesignPageCles() {
		return designPageCles == null ? "" : designPageCles.toString();
	}

	public String jsonDesignPageCles() {
		return designPageCles == null ? "" : designPageCles.toString();
	}

	public String nomAffichageDesignPageCles() {
		return "designs de page";
	}

	public String htmTooltipDesignPageCles() {
		return null;
	}

	public String htmDesignPageCles() {
		return designPageCles == null ? "" : StringEscapeUtils.escapeHtml4(strDesignPageCles());
	}

	public void inputDesignPageCles(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "designs de page")
					.a("class", "valeur suggereDesignPageCles w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setDesignPageCles")
					.a("id", classeApiMethodeMethode, "_designPageCles")
					.a("autocomplete", "off")
					.a("oninput", "suggerePartHtmlDesignPageCles($(this).val() ? rechercherDesignPageFiltres($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'partHtmlCles:" + pk + "'}", "], $('#listPartHtmlDesignPageCles_", classeApiMethodeMethode, "'), ", pk, "); ")
				.fg();

		}
	}

	public void htmDesignPageCles(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlDesignPageCles").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "?fq=partHtmlCles:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-khaki w3-hover-khaki ").f();
								e("i").a("class", "far fa-drafting-compass ").f().g("i");
								sx("designs de page");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a ce part de HTML");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputDesignPageCles(classeApiMethodeMethode);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listPartHtmlDesignPageCles_", classeApiMethodeMethode).f();
								} g("ul");
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
											.a("onclick", "postDesignPageVals({ partHtmlCles: [ \"", pk, "\" ] }, function() { patchPartHtmlVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "designPageCles')); });")
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

	//////////////
	// htmlLien //
	//////////////

	/**	L'entité « htmlLien »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlLien;
	@JsonIgnore
	public Couverture<String> htmlLienCouverture = new Couverture<String>().p(this).c(String.class).var("htmlLien").o(htmlLien);

	/**	<br/>L'entité « htmlLien »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlLien">Trouver l'entité htmlLien dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlLien(Couverture<String> c);

	public String getHtmlLien() {
		return htmlLien;
	}

	public void setHtmlLien(String htmlLien) {
		this.htmlLien = htmlLien;
		this.htmlLienCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlLienInit() {
		if(!htmlLienCouverture.dejaInitialise) {
			_htmlLien(htmlLienCouverture);
			if(htmlLien == null)
				setHtmlLien(htmlLienCouverture.o);
		}
		htmlLienCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlLien() {
		return htmlLien;
	}

	public String strHtmlLien() {
		return htmlLien == null ? "" : htmlLien;
	}

	public String jsonHtmlLien() {
		return htmlLien == null ? "" : htmlLien;
	}

	public String nomAffichageHtmlLien() {
		return "lien";
	}

	public String htmTooltipHtmlLien() {
		return null;
	}

	public String htmHtmlLien() {
		return htmlLien == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlLien());
	}

	public void inputHtmlLien(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "lien")
				.a("id", classeApiMethodeMethode, "_htmlLien");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlLien inputPartHtml", pk, "HtmlLien w3-input w3-border ");
					a("name", "setHtmlLien");
				} else {
					a("class", "valeurHtmlLien w3-input w3-border inputPartHtml", pk, "HtmlLien w3-input w3-border ");
					a("name", "htmlLien");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlLien', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlLien')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlLien')); }); ");
				}
				a("value", strHtmlLien())
			.fg();

		}
	}

	public void htmHtmlLien(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlLien").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlLien").a("class", "").f().sx("lien").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlLien(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlLien')); $('#", classeApiMethodeMethode, "_htmlLien').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlLien', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlLien')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlLien')); }); ")
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
	// htmlElement //
	/////////////////

	/**	L'entité « htmlElement »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlElement;
	@JsonIgnore
	public Couverture<String> htmlElementCouverture = new Couverture<String>().p(this).c(String.class).var("htmlElement").o(htmlElement);

	/**	<br/>L'entité « htmlElement »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlElement">Trouver l'entité htmlElement dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlElement(Couverture<String> c);

	public String getHtmlElement() {
		return htmlElement;
	}

	public void setHtmlElement(String htmlElement) {
		this.htmlElement = htmlElement;
		this.htmlElementCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlElementInit() {
		if(!htmlElementCouverture.dejaInitialise) {
			_htmlElement(htmlElementCouverture);
			if(htmlElement == null)
				setHtmlElement(htmlElementCouverture.o);
		}
		htmlElementCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlElement() {
		return htmlElement;
	}

	public String strHtmlElement() {
		return htmlElement == null ? "" : htmlElement;
	}

	public String jsonHtmlElement() {
		return htmlElement == null ? "" : htmlElement;
	}

	public String nomAffichageHtmlElement() {
		return "HTML élément";
	}

	public String htmTooltipHtmlElement() {
		return null;
	}

	public String htmHtmlElement() {
		return htmlElement == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlElement());
	}

	public void inputHtmlElement(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "HTML élément")
				.a("id", classeApiMethodeMethode, "_htmlElement");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlElement inputPartHtml", pk, "HtmlElement w3-input w3-border ");
					a("name", "setHtmlElement");
				} else {
					a("class", "valeurHtmlElement w3-input w3-border inputPartHtml", pk, "HtmlElement w3-input w3-border ");
					a("name", "htmlElement");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlElement', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlElement')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlElement')); }); ");
				}
				a("value", strHtmlElement())
			.fg();

		}
	}

	public void htmHtmlElement(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlElement").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlElement").a("class", "").f().sx("HTML élément").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlElement(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlElement')); $('#", classeApiMethodeMethode, "_htmlElement').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlElement', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlElement')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlElement')); }); ")
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
	// htmlId //
	////////////

	/**	L'entité « htmlId »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlId;
	@JsonIgnore
	public Couverture<String> htmlIdCouverture = new Couverture<String>().p(this).c(String.class).var("htmlId").o(htmlId);

	/**	<br/>L'entité « htmlId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlId">Trouver l'entité htmlId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlId(Couverture<String> c);

	public String getHtmlId() {
		return htmlId;
	}

	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
		this.htmlIdCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlIdInit() {
		if(!htmlIdCouverture.dejaInitialise) {
			_htmlId(htmlIdCouverture);
			if(htmlId == null)
				setHtmlId(htmlIdCouverture.o);
		}
		htmlIdCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlId() {
		return htmlId;
	}

	public String strHtmlId() {
		return htmlId == null ? "" : htmlId;
	}

	public String jsonHtmlId() {
		return htmlId == null ? "" : htmlId;
	}

	public String nomAffichageHtmlId() {
		return "HTML ID";
	}

	public String htmTooltipHtmlId() {
		return null;
	}

	public String htmHtmlId() {
		return htmlId == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlId());
	}

	public void inputHtmlId(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "HTML ID")
				.a("id", classeApiMethodeMethode, "_htmlId");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlId inputPartHtml", pk, "HtmlId w3-input w3-border ");
					a("name", "setHtmlId");
				} else {
					a("class", "valeurHtmlId w3-input w3-border inputPartHtml", pk, "HtmlId w3-input w3-border ");
					a("name", "htmlId");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlId', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlId')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlId')); }); ");
				}
				a("value", strHtmlId())
			.fg();

		}
	}

	public void htmHtmlId(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlId").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlId").a("class", "").f().sx("HTML ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlId(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlId')); $('#", classeApiMethodeMethode, "_htmlId').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlId', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlId')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlId')); }); ")
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
	// htmlClasses //
	/////////////////

	/**	L'entité « htmlClasses »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlClasses;
	@JsonIgnore
	public Couverture<String> htmlClassesCouverture = new Couverture<String>().p(this).c(String.class).var("htmlClasses").o(htmlClasses);

	/**	<br/>L'entité « htmlClasses »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlClasses">Trouver l'entité htmlClasses dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlClasses(Couverture<String> c);

	public String getHtmlClasses() {
		return htmlClasses;
	}

	public void setHtmlClasses(String htmlClasses) {
		this.htmlClasses = htmlClasses;
		this.htmlClassesCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlClassesInit() {
		if(!htmlClassesCouverture.dejaInitialise) {
			_htmlClasses(htmlClassesCouverture);
			if(htmlClasses == null)
				setHtmlClasses(htmlClassesCouverture.o);
		}
		htmlClassesCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlClasses() {
		return htmlClasses;
	}

	public String strHtmlClasses() {
		return htmlClasses == null ? "" : htmlClasses;
	}

	public String jsonHtmlClasses() {
		return htmlClasses == null ? "" : htmlClasses;
	}

	public String nomAffichageHtmlClasses() {
		return "HTML classes";
	}

	public String htmTooltipHtmlClasses() {
		return null;
	}

	public String htmHtmlClasses() {
		return htmlClasses == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlClasses());
	}

	public void inputHtmlClasses(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "HTML classes")
				.a("id", classeApiMethodeMethode, "_htmlClasses");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlClasses inputPartHtml", pk, "HtmlClasses w3-input w3-border ");
					a("name", "setHtmlClasses");
				} else {
					a("class", "valeurHtmlClasses w3-input w3-border inputPartHtml", pk, "HtmlClasses w3-input w3-border ");
					a("name", "htmlClasses");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlClasses', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlClasses')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlClasses')); }); ");
				}
				a("value", strHtmlClasses())
			.fg();

		}
	}

	public void htmHtmlClasses(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlClasses").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlClasses").a("class", "").f().sx("HTML classes").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlClasses(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlClasses')); $('#", classeApiMethodeMethode, "_htmlClasses').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlClasses', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlClasses')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlClasses')); }); ")
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
	// htmlStyle //
	///////////////

	/**	L'entité « htmlStyle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlStyle;
	@JsonIgnore
	public Couverture<String> htmlStyleCouverture = new Couverture<String>().p(this).c(String.class).var("htmlStyle").o(htmlStyle);

	/**	<br/>L'entité « htmlStyle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlStyle">Trouver l'entité htmlStyle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlStyle(Couverture<String> c);

	public String getHtmlStyle() {
		return htmlStyle;
	}

	public void setHtmlStyle(String htmlStyle) {
		this.htmlStyle = htmlStyle;
		this.htmlStyleCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlStyleInit() {
		if(!htmlStyleCouverture.dejaInitialise) {
			_htmlStyle(htmlStyleCouverture);
			if(htmlStyle == null)
				setHtmlStyle(htmlStyleCouverture.o);
		}
		htmlStyleCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlStyle() {
		return htmlStyle;
	}

	public String strHtmlStyle() {
		return htmlStyle == null ? "" : htmlStyle;
	}

	public String jsonHtmlStyle() {
		return htmlStyle == null ? "" : htmlStyle;
	}

	public String nomAffichageHtmlStyle() {
		return "HTML style";
	}

	public String htmTooltipHtmlStyle() {
		return null;
	}

	public String htmHtmlStyle() {
		return htmlStyle == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlStyle());
	}

	public void inputHtmlStyle(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "HTML style")
				.a("id", classeApiMethodeMethode, "_htmlStyle");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlStyle inputPartHtml", pk, "HtmlStyle w3-input w3-border ");
					a("name", "setHtmlStyle");
				} else {
					a("class", "valeurHtmlStyle w3-input w3-border inputPartHtml", pk, "HtmlStyle w3-input w3-border ");
					a("name", "htmlStyle");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlStyle', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlStyle')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlStyle')); }); ");
				}
				a("value", strHtmlStyle())
			.fg();

		}
	}

	public void htmHtmlStyle(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlStyle").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlStyle").a("class", "").f().sx("HTML style").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlStyle(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlStyle')); $('#", classeApiMethodeMethode, "_htmlStyle').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlStyle', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlStyle')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlStyle')); }); ")
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
	// htmlAvant //
	///////////////

	/**	L'entité « htmlAvant »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlAvant;
	@JsonIgnore
	public Couverture<String> htmlAvantCouverture = new Couverture<String>().p(this).c(String.class).var("htmlAvant").o(htmlAvant);

	/**	<br/>L'entité « htmlAvant »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlAvant">Trouver l'entité htmlAvant dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlAvant(Couverture<String> c);

	public String getHtmlAvant() {
		return htmlAvant;
	}

	public void setHtmlAvant(String htmlAvant) {
		this.htmlAvant = htmlAvant;
		this.htmlAvantCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlAvantInit() {
		if(!htmlAvantCouverture.dejaInitialise) {
			_htmlAvant(htmlAvantCouverture);
			if(htmlAvant == null)
				setHtmlAvant(htmlAvantCouverture.o);
		}
		htmlAvantCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlAvant() {
		return htmlAvant;
	}

	public String strHtmlAvant() {
		return htmlAvant == null ? "" : htmlAvant;
	}

	public String jsonHtmlAvant() {
		return htmlAvant == null ? "" : htmlAvant;
	}

	public String nomAffichageHtmlAvant() {
		return "HTML avant";
	}

	public String htmTooltipHtmlAvant() {
		return null;
	}

	public String htmHtmlAvant() {
		return htmlAvant == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlAvant());
	}

	public void inputHtmlAvant(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("textarea")
				.a("placeholder", "HTML avant")
				.a("id", classeApiMethodeMethode, "_htmlAvant");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlAvant inputPartHtml", pk, "HtmlAvant w3-input w3-border ");
					a("name", "setHtmlAvant");
				} else {
					a("class", "valeurHtmlAvant w3-input w3-border inputPartHtml", pk, "HtmlAvant w3-input w3-border ");
					a("name", "htmlAvant");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlAvant', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlAvant')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlAvant')); }); ");
				}
			f().sx(strHtmlAvant()).g("textarea");

		}
	}

	public void htmHtmlAvant(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlAvant").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlAvant").a("class", "").f().sx("HTML avant").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlAvant(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlAvant')); $('#", classeApiMethodeMethode, "_htmlAvant').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlAvant', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlAvant')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlAvant')); }); ")
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
	// htmlApres //
	///////////////

	/**	L'entité « htmlApres »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlApres;
	@JsonIgnore
	public Couverture<String> htmlApresCouverture = new Couverture<String>().p(this).c(String.class).var("htmlApres").o(htmlApres);

	/**	<br/>L'entité « htmlApres »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlApres">Trouver l'entité htmlApres dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlApres(Couverture<String> c);

	public String getHtmlApres() {
		return htmlApres;
	}

	public void setHtmlApres(String htmlApres) {
		this.htmlApres = htmlApres;
		this.htmlApresCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlApresInit() {
		if(!htmlApresCouverture.dejaInitialise) {
			_htmlApres(htmlApresCouverture);
			if(htmlApres == null)
				setHtmlApres(htmlApresCouverture.o);
		}
		htmlApresCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlApres() {
		return htmlApres;
	}

	public String strHtmlApres() {
		return htmlApres == null ? "" : htmlApres;
	}

	public String jsonHtmlApres() {
		return htmlApres == null ? "" : htmlApres;
	}

	public String nomAffichageHtmlApres() {
		return "HTML après";
	}

	public String htmTooltipHtmlApres() {
		return null;
	}

	public String htmHtmlApres() {
		return htmlApres == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlApres());
	}

	public void inputHtmlApres(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("textarea")
				.a("placeholder", "HTML après")
				.a("id", classeApiMethodeMethode, "_htmlApres");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlApres inputPartHtml", pk, "HtmlApres w3-input w3-border ");
					a("name", "setHtmlApres");
				} else {
					a("class", "valeurHtmlApres w3-input w3-border inputPartHtml", pk, "HtmlApres w3-input w3-border ");
					a("name", "htmlApres");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlApres', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlApres')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlApres')); }); ");
				}
			f().sx(strHtmlApres()).g("textarea");

		}
	}

	public void htmHtmlApres(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlApres").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlApres").a("class", "").f().sx("HTML après").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlApres(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlApres')); $('#", classeApiMethodeMethode, "_htmlApres').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlApres', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlApres')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlApres')); }); ")
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
	// htmlTexte //
	///////////////

	/**	L'entité « htmlTexte »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlTexte;
	@JsonIgnore
	public Couverture<String> htmlTexteCouverture = new Couverture<String>().p(this).c(String.class).var("htmlTexte").o(htmlTexte);

	/**	<br/>L'entité « htmlTexte »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlTexte">Trouver l'entité htmlTexte dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlTexte(Couverture<String> c);

	public String getHtmlTexte() {
		return htmlTexte;
	}

	public void setHtmlTexte(String htmlTexte) {
		this.htmlTexte = htmlTexte;
		this.htmlTexteCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlTexteInit() {
		if(!htmlTexteCouverture.dejaInitialise) {
			_htmlTexte(htmlTexteCouverture);
			if(htmlTexte == null)
				setHtmlTexte(htmlTexteCouverture.o);
		}
		htmlTexteCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlTexte() {
		return htmlTexte;
	}

	public String strHtmlTexte() {
		return htmlTexte == null ? "" : htmlTexte;
	}

	public String jsonHtmlTexte() {
		return htmlTexte == null ? "" : htmlTexte;
	}

	public String nomAffichageHtmlTexte() {
		return "texte";
	}

	public String htmTooltipHtmlTexte() {
		return null;
	}

	public String htmHtmlTexte() {
		return htmlTexte == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlTexte());
	}

	public void inputHtmlTexte(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("textarea")
				.a("placeholder", "texte")
				.a("id", classeApiMethodeMethode, "_htmlTexte");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlTexte inputPartHtml", pk, "HtmlTexte w3-input w3-border ");
					a("name", "setHtmlTexte");
				} else {
					a("class", "valeurHtmlTexte w3-input w3-border inputPartHtml", pk, "HtmlTexte w3-input w3-border ");
					a("name", "htmlTexte");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlTexte', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlTexte')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlTexte')); }); ");
				}
			f().sx(strHtmlTexte()).g("textarea");

		}
	}

	public void htmHtmlTexte(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlTexte").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlTexte").a("class", "").f().sx("texte").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlTexte(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlTexte')); $('#", classeApiMethodeMethode, "_htmlTexte').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlTexte', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlTexte')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlTexte')); }); ")
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

	/////////////
	// htmlVar //
	/////////////

	/**	L'entité « htmlVar »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlVar;
	@JsonIgnore
	public Couverture<String> htmlVarCouverture = new Couverture<String>().p(this).c(String.class).var("htmlVar").o(htmlVar);

	/**	<br/>L'entité « htmlVar »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlVar">Trouver l'entité htmlVar dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlVar(Couverture<String> c);

	public String getHtmlVar() {
		return htmlVar;
	}

	public void setHtmlVar(String htmlVar) {
		this.htmlVar = htmlVar;
		this.htmlVarCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlVarInit() {
		if(!htmlVarCouverture.dejaInitialise) {
			_htmlVar(htmlVarCouverture);
			if(htmlVar == null)
				setHtmlVar(htmlVarCouverture.o);
		}
		htmlVarCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlVar() {
		return htmlVar;
	}

	public String strHtmlVar() {
		return htmlVar == null ? "" : htmlVar;
	}

	public String jsonHtmlVar() {
		return htmlVar == null ? "" : htmlVar;
	}

	public String nomAffichageHtmlVar() {
		return "var";
	}

	public String htmTooltipHtmlVar() {
		return null;
	}

	public String htmHtmlVar() {
		return htmlVar == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlVar());
	}

	public void inputHtmlVar(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "var")
				.a("id", classeApiMethodeMethode, "_htmlVar");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlVar inputPartHtml", pk, "HtmlVar w3-input w3-border ");
					a("name", "setHtmlVar");
				} else {
					a("class", "valeurHtmlVar w3-input w3-border inputPartHtml", pk, "HtmlVar w3-input w3-border ");
					a("name", "htmlVar");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlVar', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlVar')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlVar')); }); ");
				}
				a("value", strHtmlVar())
			.fg();

		}
	}

	public void htmHtmlVar(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlVar").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlVar").a("class", "").f().sx("var").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlVar(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlVar')); $('#", classeApiMethodeMethode, "_htmlVar').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlVar', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlVar')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlVar')); }); ")
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
	// htmlVarSpan //
	/////////////////

	/**	L'entité « htmlVarSpan »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlVarSpan;
	@JsonIgnore
	public Couverture<String> htmlVarSpanCouverture = new Couverture<String>().p(this).c(String.class).var("htmlVarSpan").o(htmlVarSpan);

	/**	<br/>L'entité « htmlVarSpan »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlVarSpan">Trouver l'entité htmlVarSpan dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlVarSpan(Couverture<String> c);

	public String getHtmlVarSpan() {
		return htmlVarSpan;
	}

	public void setHtmlVarSpan(String htmlVarSpan) {
		this.htmlVarSpan = htmlVarSpan;
		this.htmlVarSpanCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlVarSpanInit() {
		if(!htmlVarSpanCouverture.dejaInitialise) {
			_htmlVarSpan(htmlVarSpanCouverture);
			if(htmlVarSpan == null)
				setHtmlVarSpan(htmlVarSpanCouverture.o);
		}
		htmlVarSpanCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlVarSpan() {
		return htmlVarSpan;
	}

	public String strHtmlVarSpan() {
		return htmlVarSpan == null ? "" : htmlVarSpan;
	}

	public String jsonHtmlVarSpan() {
		return htmlVarSpan == null ? "" : htmlVarSpan;
	}

	public String nomAffichageHtmlVarSpan() {
		return "var span";
	}

	public String htmTooltipHtmlVarSpan() {
		return null;
	}

	public String htmHtmlVarSpan() {
		return htmlVarSpan == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlVarSpan());
	}

	public void inputHtmlVarSpan(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "var span")
				.a("id", classeApiMethodeMethode, "_htmlVarSpan");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlVarSpan inputPartHtml", pk, "HtmlVarSpan w3-input w3-border ");
					a("name", "setHtmlVarSpan");
				} else {
					a("class", "valeurHtmlVarSpan w3-input w3-border inputPartHtml", pk, "HtmlVarSpan w3-input w3-border ");
					a("name", "htmlVarSpan");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlVarSpan', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlVarSpan')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlVarSpan')); }); ");
				}
				a("value", strHtmlVarSpan())
			.fg();

		}
	}

	public void htmHtmlVarSpan(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlVarSpan").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlVarSpan").a("class", "").f().sx("var span").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlVarSpan(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlVarSpan')); $('#", classeApiMethodeMethode, "_htmlVarSpan').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlVarSpan', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlVarSpan')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlVarSpan')); }); ")
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
	// htmlVarForm //
	/////////////////

	/**	L'entité « htmlVarForm »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlVarForm;
	@JsonIgnore
	public Couverture<String> htmlVarFormCouverture = new Couverture<String>().p(this).c(String.class).var("htmlVarForm").o(htmlVarForm);

	/**	<br/>L'entité « htmlVarForm »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlVarForm">Trouver l'entité htmlVarForm dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlVarForm(Couverture<String> c);

	public String getHtmlVarForm() {
		return htmlVarForm;
	}

	public void setHtmlVarForm(String htmlVarForm) {
		this.htmlVarForm = htmlVarForm;
		this.htmlVarFormCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlVarFormInit() {
		if(!htmlVarFormCouverture.dejaInitialise) {
			_htmlVarForm(htmlVarFormCouverture);
			if(htmlVarForm == null)
				setHtmlVarForm(htmlVarFormCouverture.o);
		}
		htmlVarFormCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlVarForm() {
		return htmlVarForm;
	}

	public String strHtmlVarForm() {
		return htmlVarForm == null ? "" : htmlVarForm;
	}

	public String jsonHtmlVarForm() {
		return htmlVarForm == null ? "" : htmlVarForm;
	}

	public String nomAffichageHtmlVarForm() {
		return "var form";
	}

	public String htmTooltipHtmlVarForm() {
		return null;
	}

	public String htmHtmlVarForm() {
		return htmlVarForm == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlVarForm());
	}

	public void inputHtmlVarForm(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "var form")
				.a("id", classeApiMethodeMethode, "_htmlVarForm");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlVarForm inputPartHtml", pk, "HtmlVarForm w3-input w3-border ");
					a("name", "setHtmlVarForm");
				} else {
					a("class", "valeurHtmlVarForm w3-input w3-border inputPartHtml", pk, "HtmlVarForm w3-input w3-border ");
					a("name", "htmlVarForm");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlVarForm', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlVarForm')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlVarForm')); }); ");
				}
				a("value", strHtmlVarForm())
			.fg();

		}
	}

	public void htmHtmlVarForm(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlVarForm").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlVarForm").a("class", "").f().sx("var form").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlVarForm(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlVarForm')); $('#", classeApiMethodeMethode, "_htmlVarForm').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlVarForm', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlVarForm')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlVarForm')); }); ")
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
	// htmlVarInput //
	//////////////////

	/**	L'entité « htmlVarInput »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlVarInput;
	@JsonIgnore
	public Couverture<String> htmlVarInputCouverture = new Couverture<String>().p(this).c(String.class).var("htmlVarInput").o(htmlVarInput);

	/**	<br/>L'entité « htmlVarInput »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlVarInput">Trouver l'entité htmlVarInput dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlVarInput(Couverture<String> c);

	public String getHtmlVarInput() {
		return htmlVarInput;
	}

	public void setHtmlVarInput(String htmlVarInput) {
		this.htmlVarInput = htmlVarInput;
		this.htmlVarInputCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlVarInputInit() {
		if(!htmlVarInputCouverture.dejaInitialise) {
			_htmlVarInput(htmlVarInputCouverture);
			if(htmlVarInput == null)
				setHtmlVarInput(htmlVarInputCouverture.o);
		}
		htmlVarInputCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlVarInput() {
		return htmlVarInput;
	}

	public String strHtmlVarInput() {
		return htmlVarInput == null ? "" : htmlVarInput;
	}

	public String jsonHtmlVarInput() {
		return htmlVarInput == null ? "" : htmlVarInput;
	}

	public String nomAffichageHtmlVarInput() {
		return "var input";
	}

	public String htmTooltipHtmlVarInput() {
		return null;
	}

	public String htmHtmlVarInput() {
		return htmlVarInput == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlVarInput());
	}

	public void inputHtmlVarInput(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "var input")
				.a("id", classeApiMethodeMethode, "_htmlVarInput");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlVarInput inputPartHtml", pk, "HtmlVarInput w3-input w3-border ");
					a("name", "setHtmlVarInput");
				} else {
					a("class", "valeurHtmlVarInput w3-input w3-border inputPartHtml", pk, "HtmlVarInput w3-input w3-border ");
					a("name", "htmlVarInput");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlVarInput', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlVarInput')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlVarInput')); }); ");
				}
				a("value", strHtmlVarInput())
			.fg();

		}
	}

	public void htmHtmlVarInput(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlVarInput").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlVarInput").a("class", "").f().sx("var input").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlVarInput(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlVarInput')); $('#", classeApiMethodeMethode, "_htmlVarInput').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlVarInput', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlVarInput')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlVarInput')); }); ")
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
	// htmlVarForEach //
	////////////////////

	/**	L'entité « htmlVarForEach »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String htmlVarForEach;
	@JsonIgnore
	public Couverture<String> htmlVarForEachCouverture = new Couverture<String>().p(this).c(String.class).var("htmlVarForEach").o(htmlVarForEach);

	/**	<br/>L'entité « htmlVarForEach »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlVarForEach">Trouver l'entité htmlVarForEach dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlVarForEach(Couverture<String> c);

	public String getHtmlVarForEach() {
		return htmlVarForEach;
	}

	public void setHtmlVarForEach(String htmlVarForEach) {
		this.htmlVarForEach = htmlVarForEach;
		this.htmlVarForEachCouverture.dejaInitialise = true;
	}
	protected PartHtml htmlVarForEachInit() {
		if(!htmlVarForEachCouverture.dejaInitialise) {
			_htmlVarForEach(htmlVarForEachCouverture);
			if(htmlVarForEach == null)
				setHtmlVarForEach(htmlVarForEachCouverture.o);
		}
		htmlVarForEachCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public String solrHtmlVarForEach() {
		return htmlVarForEach;
	}

	public String strHtmlVarForEach() {
		return htmlVarForEach == null ? "" : htmlVarForEach;
	}

	public String jsonHtmlVarForEach() {
		return htmlVarForEach == null ? "" : htmlVarForEach;
	}

	public String nomAffichageHtmlVarForEach() {
		return "var for each";
	}

	public String htmTooltipHtmlVarForEach() {
		return null;
	}

	public String htmHtmlVarForEach() {
		return htmlVarForEach == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlVarForEach());
	}

	public void inputHtmlVarForEach(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "var for each")
				.a("id", classeApiMethodeMethode, "_htmlVarForEach");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setHtmlVarForEach inputPartHtml", pk, "HtmlVarForEach w3-input w3-border ");
					a("name", "setHtmlVarForEach");
				} else {
					a("class", "valeurHtmlVarForEach w3-input w3-border inputPartHtml", pk, "HtmlVarForEach w3-input w3-border ");
					a("name", "htmlVarForEach");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlVarForEach', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlVarForEach')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlVarForEach')); }); ");
				}
				a("value", strHtmlVarForEach())
			.fg();

		}
	}

	public void htmHtmlVarForEach(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlVarForEach").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlVarForEach").a("class", "").f().sx("var for each").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlVarForEach(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_htmlVarForEach')); $('#", classeApiMethodeMethode, "_htmlVarForEach').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setHtmlVarForEach', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlVarForEach')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlVarForEach')); }); ")
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
	// htmlExclure //
	/////////////////

	/**	L'entité « htmlExclure »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean htmlExclure;
	@JsonIgnore
	public Couverture<Boolean> htmlExclureCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("htmlExclure").o(htmlExclure);

	/**	<br/>L'entité « htmlExclure »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlExclure">Trouver l'entité htmlExclure dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlExclure(Couverture<Boolean> c);

	public Boolean getHtmlExclure() {
		return htmlExclure;
	}

	public void setHtmlExclure(Boolean htmlExclure) {
		this.htmlExclure = htmlExclure;
		this.htmlExclureCouverture.dejaInitialise = true;
	}
	public PartHtml setHtmlExclure(String o) {
		this.htmlExclure = Boolean.parseBoolean(o);
		this.htmlExclureCouverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml htmlExclureInit() {
		if(!htmlExclureCouverture.dejaInitialise) {
			_htmlExclure(htmlExclureCouverture);
			if(htmlExclure == null)
				setHtmlExclure(htmlExclureCouverture.o);
		}
		htmlExclureCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Boolean solrHtmlExclure() {
		return htmlExclure;
	}

	public String strHtmlExclure() {
		return htmlExclure == null ? "" : htmlExclure.toString();
	}

	public String jsonHtmlExclure() {
		return htmlExclure == null ? "" : htmlExclure.toString();
	}

	public String nomAffichageHtmlExclure() {
		return "HTML exclure";
	}

	public String htmTooltipHtmlExclure() {
		return null;
	}

	public String htmHtmlExclure() {
		return htmlExclure == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlExclure());
	}

	public void inputHtmlExclure(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_htmlExclure")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_htmlExclure");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setHtmlExclure inputPartHtml", pk, "HtmlExclure w3-input w3-border ");
				a("name", "setHtmlExclure");
			} else {
				a("class", "valeurHtmlExclure inputPartHtml", pk, "HtmlExclure w3-input w3-border ");
				a("name", "htmlExclure");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setHtmlExclure', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_htmlExclure')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_htmlExclure')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getHtmlExclure() != null && getHtmlExclure())
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

	public void htmHtmlExclure(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlHtmlExclure").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_htmlExclure").a("class", "").f().sx("HTML exclure").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputHtmlExclure(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////
	// pdfExclure //
	////////////////

	/**	L'entité « pdfExclure »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean pdfExclure;
	@JsonIgnore
	public Couverture<Boolean> pdfExclureCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("pdfExclure").o(pdfExclure);

	/**	<br/>L'entité « pdfExclure »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:pdfExclure">Trouver l'entité pdfExclure dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pdfExclure(Couverture<Boolean> c);

	public Boolean getPdfExclure() {
		return pdfExclure;
	}

	public void setPdfExclure(Boolean pdfExclure) {
		this.pdfExclure = pdfExclure;
		this.pdfExclureCouverture.dejaInitialise = true;
	}
	public PartHtml setPdfExclure(String o) {
		this.pdfExclure = Boolean.parseBoolean(o);
		this.pdfExclureCouverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml pdfExclureInit() {
		if(!pdfExclureCouverture.dejaInitialise) {
			_pdfExclure(pdfExclureCouverture);
			if(pdfExclure == null)
				setPdfExclure(pdfExclureCouverture.o);
		}
		pdfExclureCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Boolean solrPdfExclure() {
		return pdfExclure;
	}

	public String strPdfExclure() {
		return pdfExclure == null ? "" : pdfExclure.toString();
	}

	public String jsonPdfExclure() {
		return pdfExclure == null ? "" : pdfExclure.toString();
	}

	public String nomAffichagePdfExclure() {
		return "PDF exclure";
	}

	public String htmTooltipPdfExclure() {
		return null;
	}

	public String htmPdfExclure() {
		return pdfExclure == null ? "" : StringEscapeUtils.escapeHtml4(strPdfExclure());
	}

	public void inputPdfExclure(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_pdfExclure")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_pdfExclure");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setPdfExclure inputPartHtml", pk, "PdfExclure w3-input w3-border ");
				a("name", "setPdfExclure");
			} else {
				a("class", "valeurPdfExclure inputPartHtml", pk, "PdfExclure w3-input w3-border ");
				a("name", "pdfExclure");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPdfExclure', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_pdfExclure')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_pdfExclure')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getPdfExclure() != null && getPdfExclure())
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

	public void htmPdfExclure(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlPdfExclure").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_pdfExclure").a("class", "").f().sx("PDF exclure").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPdfExclure(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////////
	// connecterDeconnecter //
	//////////////////////////

	/**	L'entité « connecterDeconnecter »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean connecterDeconnecter;
	@JsonIgnore
	public Couverture<Boolean> connecterDeconnecterCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("connecterDeconnecter").o(connecterDeconnecter);

	/**	<br/>L'entité « connecterDeconnecter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:connecterDeconnecter">Trouver l'entité connecterDeconnecter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _connecterDeconnecter(Couverture<Boolean> c);

	public Boolean getConnecterDeconnecter() {
		return connecterDeconnecter;
	}

	public void setConnecterDeconnecter(Boolean connecterDeconnecter) {
		this.connecterDeconnecter = connecterDeconnecter;
		this.connecterDeconnecterCouverture.dejaInitialise = true;
	}
	public PartHtml setConnecterDeconnecter(String o) {
		this.connecterDeconnecter = Boolean.parseBoolean(o);
		this.connecterDeconnecterCouverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml connecterDeconnecterInit() {
		if(!connecterDeconnecterCouverture.dejaInitialise) {
			_connecterDeconnecter(connecterDeconnecterCouverture);
			if(connecterDeconnecter == null)
				setConnecterDeconnecter(connecterDeconnecterCouverture.o);
		}
		connecterDeconnecterCouverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Boolean solrConnecterDeconnecter() {
		return connecterDeconnecter;
	}

	public String strConnecterDeconnecter() {
		return connecterDeconnecter == null ? "" : connecterDeconnecter.toString();
	}

	public String jsonConnecterDeconnecter() {
		return connecterDeconnecter == null ? "" : connecterDeconnecter.toString();
	}

	public String nomAffichageConnecterDeconnecter() {
		return "se connecter / se deconnecter";
	}

	public String htmTooltipConnecterDeconnecter() {
		return null;
	}

	public String htmConnecterDeconnecter() {
		return connecterDeconnecter == null ? "" : StringEscapeUtils.escapeHtml4(strConnecterDeconnecter());
	}

	public void inputConnecterDeconnecter(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			if("Page".equals(classeApiMethodeMethode)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classeApiMethodeMethode, "_connecterDeconnecter")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classeApiMethodeMethode, "_connecterDeconnecter");
			}
			if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
				a("class", "setConnecterDeconnecter inputPartHtml", pk, "ConnecterDeconnecter w3-input w3-border ");
				a("name", "setConnecterDeconnecter");
			} else {
				a("class", "valeurConnecterDeconnecter inputPartHtml", pk, "ConnecterDeconnecter w3-input w3-border ");
				a("name", "connecterDeconnecter");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setConnecterDeconnecter', $(this).prop('checked'), function() { ajouterLueur($('#", classeApiMethodeMethode, "_connecterDeconnecter')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_connecterDeconnecter')); }); ");
			}
			if("Page".equals(classeApiMethodeMethode)) {
				if(getConnecterDeconnecter() != null && getConnecterDeconnecter())
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

	public void htmConnecterDeconnecter(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlConnecterDeconnecter").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_connecterDeconnecter").a("class", "").f().sx("se connecter / se deconnecter").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputConnecterDeconnecter(classeApiMethodeMethode);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////
	// tri1 //
	//////////

	/**	L'entité « tri1 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Double tri1;
	@JsonIgnore
	public Couverture<Double> tri1Couverture = new Couverture<Double>().p(this).c(Double.class).var("tri1").o(tri1);

	/**	<br/>L'entité « tri1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tri1">Trouver l'entité tri1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tri1(Couverture<Double> c);

	public Double getTri1() {
		return tri1;
	}

	public void setTri1(Double tri1) {
		this.tri1 = tri1;
		this.tri1Couverture.dejaInitialise = true;
	}
	public PartHtml setTri1(String o) {
		if(NumberUtils.isParsable(o))
			this.tri1 = Double.parseDouble(o);
		this.tri1Couverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml tri1Init() {
		if(!tri1Couverture.dejaInitialise) {
			_tri1(tri1Couverture);
			if(tri1 == null)
				setTri1(tri1Couverture.o);
		}
		tri1Couverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Double solrTri1() {
		return tri1;
	}

	public String strTri1() {
		return tri1 == null ? "" : tri1.toString();
	}

	public String jsonTri1() {
		return tri1 == null ? "" : tri1.toString();
	}

	public String nomAffichageTri1() {
		return "tri1";
	}

	public String htmTooltipTri1() {
		return null;
	}

	public String htmTri1() {
		return tri1 == null ? "" : StringEscapeUtils.escapeHtml4(strTri1());
	}

	public void inputTri1(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "tri1")
				.a("id", classeApiMethodeMethode, "_tri1");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTri1 inputPartHtml", pk, "Tri1 w3-input w3-border ");
					a("name", "setTri1");
				} else {
					a("class", "valeurTri1 w3-input w3-border inputPartHtml", pk, "Tri1 w3-input w3-border ");
					a("name", "tri1");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setTri1', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri1')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri1')); }); ");
				}
				a("value", strTri1())
			.fg();

		}
	}

	public void htmTri1(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlTri1").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_tri1").a("class", "").f().sx("tri1").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTri1(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_tri1')); $('#", classeApiMethodeMethode, "_tri1').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setTri1', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri1')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri1')); }); ")
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

	//////////
	// tri2 //
	//////////

	/**	L'entité « tri2 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Double tri2;
	@JsonIgnore
	public Couverture<Double> tri2Couverture = new Couverture<Double>().p(this).c(Double.class).var("tri2").o(tri2);

	/**	<br/>L'entité « tri2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tri2">Trouver l'entité tri2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tri2(Couverture<Double> c);

	public Double getTri2() {
		return tri2;
	}

	public void setTri2(Double tri2) {
		this.tri2 = tri2;
		this.tri2Couverture.dejaInitialise = true;
	}
	public PartHtml setTri2(String o) {
		if(NumberUtils.isParsable(o))
			this.tri2 = Double.parseDouble(o);
		this.tri2Couverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml tri2Init() {
		if(!tri2Couverture.dejaInitialise) {
			_tri2(tri2Couverture);
			if(tri2 == null)
				setTri2(tri2Couverture.o);
		}
		tri2Couverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Double solrTri2() {
		return tri2;
	}

	public String strTri2() {
		return tri2 == null ? "" : tri2.toString();
	}

	public String jsonTri2() {
		return tri2 == null ? "" : tri2.toString();
	}

	public String nomAffichageTri2() {
		return "tri2";
	}

	public String htmTooltipTri2() {
		return null;
	}

	public String htmTri2() {
		return tri2 == null ? "" : StringEscapeUtils.escapeHtml4(strTri2());
	}

	public void inputTri2(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "tri2")
				.a("id", classeApiMethodeMethode, "_tri2");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTri2 inputPartHtml", pk, "Tri2 w3-input w3-border ");
					a("name", "setTri2");
				} else {
					a("class", "valeurTri2 w3-input w3-border inputPartHtml", pk, "Tri2 w3-input w3-border ");
					a("name", "tri2");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setTri2', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri2')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri2')); }); ");
				}
				a("value", strTri2())
			.fg();

		}
	}

	public void htmTri2(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlTri2").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_tri2").a("class", "").f().sx("tri2").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTri2(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_tri2')); $('#", classeApiMethodeMethode, "_tri2').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setTri2', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri2')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri2')); }); ")
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

	//////////
	// tri3 //
	//////////

	/**	L'entité « tri3 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Double tri3;
	@JsonIgnore
	public Couverture<Double> tri3Couverture = new Couverture<Double>().p(this).c(Double.class).var("tri3").o(tri3);

	/**	<br/>L'entité « tri3 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tri3">Trouver l'entité tri3 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tri3(Couverture<Double> c);

	public Double getTri3() {
		return tri3;
	}

	public void setTri3(Double tri3) {
		this.tri3 = tri3;
		this.tri3Couverture.dejaInitialise = true;
	}
	public PartHtml setTri3(String o) {
		if(NumberUtils.isParsable(o))
			this.tri3 = Double.parseDouble(o);
		this.tri3Couverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml tri3Init() {
		if(!tri3Couverture.dejaInitialise) {
			_tri3(tri3Couverture);
			if(tri3 == null)
				setTri3(tri3Couverture.o);
		}
		tri3Couverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Double solrTri3() {
		return tri3;
	}

	public String strTri3() {
		return tri3 == null ? "" : tri3.toString();
	}

	public String jsonTri3() {
		return tri3 == null ? "" : tri3.toString();
	}

	public String nomAffichageTri3() {
		return "tri3";
	}

	public String htmTooltipTri3() {
		return null;
	}

	public String htmTri3() {
		return tri3 == null ? "" : StringEscapeUtils.escapeHtml4(strTri3());
	}

	public void inputTri3(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "tri3")
				.a("id", classeApiMethodeMethode, "_tri3");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTri3 inputPartHtml", pk, "Tri3 w3-input w3-border ");
					a("name", "setTri3");
				} else {
					a("class", "valeurTri3 w3-input w3-border inputPartHtml", pk, "Tri3 w3-input w3-border ");
					a("name", "tri3");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setTri3', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri3')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri3')); }); ");
				}
				a("value", strTri3())
			.fg();

		}
	}

	public void htmTri3(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlTri3").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_tri3").a("class", "").f().sx("tri3").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTri3(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_tri3')); $('#", classeApiMethodeMethode, "_tri3').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setTri3', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri3')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri3')); }); ")
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

	//////////
	// tri4 //
	//////////

	/**	L'entité « tri4 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Double tri4;
	@JsonIgnore
	public Couverture<Double> tri4Couverture = new Couverture<Double>().p(this).c(Double.class).var("tri4").o(tri4);

	/**	<br/>L'entité « tri4 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tri4">Trouver l'entité tri4 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tri4(Couverture<Double> c);

	public Double getTri4() {
		return tri4;
	}

	public void setTri4(Double tri4) {
		this.tri4 = tri4;
		this.tri4Couverture.dejaInitialise = true;
	}
	public PartHtml setTri4(String o) {
		if(NumberUtils.isParsable(o))
			this.tri4 = Double.parseDouble(o);
		this.tri4Couverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml tri4Init() {
		if(!tri4Couverture.dejaInitialise) {
			_tri4(tri4Couverture);
			if(tri4 == null)
				setTri4(tri4Couverture.o);
		}
		tri4Couverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Double solrTri4() {
		return tri4;
	}

	public String strTri4() {
		return tri4 == null ? "" : tri4.toString();
	}

	public String jsonTri4() {
		return tri4 == null ? "" : tri4.toString();
	}

	public String nomAffichageTri4() {
		return "tri4";
	}

	public String htmTooltipTri4() {
		return null;
	}

	public String htmTri4() {
		return tri4 == null ? "" : StringEscapeUtils.escapeHtml4(strTri4());
	}

	public void inputTri4(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "tri4")
				.a("id", classeApiMethodeMethode, "_tri4");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTri4 inputPartHtml", pk, "Tri4 w3-input w3-border ");
					a("name", "setTri4");
				} else {
					a("class", "valeurTri4 w3-input w3-border inputPartHtml", pk, "Tri4 w3-input w3-border ");
					a("name", "tri4");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setTri4', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri4')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri4')); }); ");
				}
				a("value", strTri4())
			.fg();

		}
	}

	public void htmTri4(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlTri4").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_tri4").a("class", "").f().sx("tri4").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTri4(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_tri4')); $('#", classeApiMethodeMethode, "_tri4').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setTri4', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri4')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri4')); }); ")
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

	//////////
	// tri5 //
	//////////

	/**	L'entité « tri5 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Double tri5;
	@JsonIgnore
	public Couverture<Double> tri5Couverture = new Couverture<Double>().p(this).c(Double.class).var("tri5").o(tri5);

	/**	<br/>L'entité « tri5 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tri5">Trouver l'entité tri5 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tri5(Couverture<Double> c);

	public Double getTri5() {
		return tri5;
	}

	public void setTri5(Double tri5) {
		this.tri5 = tri5;
		this.tri5Couverture.dejaInitialise = true;
	}
	public PartHtml setTri5(String o) {
		if(NumberUtils.isParsable(o))
			this.tri5 = Double.parseDouble(o);
		this.tri5Couverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml tri5Init() {
		if(!tri5Couverture.dejaInitialise) {
			_tri5(tri5Couverture);
			if(tri5 == null)
				setTri5(tri5Couverture.o);
		}
		tri5Couverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Double solrTri5() {
		return tri5;
	}

	public String strTri5() {
		return tri5 == null ? "" : tri5.toString();
	}

	public String jsonTri5() {
		return tri5 == null ? "" : tri5.toString();
	}

	public String nomAffichageTri5() {
		return "tri5";
	}

	public String htmTooltipTri5() {
		return null;
	}

	public String htmTri5() {
		return tri5 == null ? "" : StringEscapeUtils.escapeHtml4(strTri5());
	}

	public void inputTri5(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "tri5")
				.a("id", classeApiMethodeMethode, "_tri5");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTri5 inputPartHtml", pk, "Tri5 w3-input w3-border ");
					a("name", "setTri5");
				} else {
					a("class", "valeurTri5 w3-input w3-border inputPartHtml", pk, "Tri5 w3-input w3-border ");
					a("name", "tri5");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setTri5', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri5')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri5')); }); ");
				}
				a("value", strTri5())
			.fg();

		}
	}

	public void htmTri5(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlTri5").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_tri5").a("class", "").f().sx("tri5").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTri5(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_tri5')); $('#", classeApiMethodeMethode, "_tri5').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setTri5', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri5')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri5')); }); ")
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

	//////////
	// tri6 //
	//////////

	/**	L'entité « tri6 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Double tri6;
	@JsonIgnore
	public Couverture<Double> tri6Couverture = new Couverture<Double>().p(this).c(Double.class).var("tri6").o(tri6);

	/**	<br/>L'entité « tri6 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tri6">Trouver l'entité tri6 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tri6(Couverture<Double> c);

	public Double getTri6() {
		return tri6;
	}

	public void setTri6(Double tri6) {
		this.tri6 = tri6;
		this.tri6Couverture.dejaInitialise = true;
	}
	public PartHtml setTri6(String o) {
		if(NumberUtils.isParsable(o))
			this.tri6 = Double.parseDouble(o);
		this.tri6Couverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml tri6Init() {
		if(!tri6Couverture.dejaInitialise) {
			_tri6(tri6Couverture);
			if(tri6 == null)
				setTri6(tri6Couverture.o);
		}
		tri6Couverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Double solrTri6() {
		return tri6;
	}

	public String strTri6() {
		return tri6 == null ? "" : tri6.toString();
	}

	public String jsonTri6() {
		return tri6 == null ? "" : tri6.toString();
	}

	public String nomAffichageTri6() {
		return "tri6";
	}

	public String htmTooltipTri6() {
		return null;
	}

	public String htmTri6() {
		return tri6 == null ? "" : StringEscapeUtils.escapeHtml4(strTri6());
	}

	public void inputTri6(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "tri6")
				.a("id", classeApiMethodeMethode, "_tri6");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTri6 inputPartHtml", pk, "Tri6 w3-input w3-border ");
					a("name", "setTri6");
				} else {
					a("class", "valeurTri6 w3-input w3-border inputPartHtml", pk, "Tri6 w3-input w3-border ");
					a("name", "tri6");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setTri6', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri6')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri6')); }); ");
				}
				a("value", strTri6())
			.fg();

		}
	}

	public void htmTri6(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlTri6").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_tri6").a("class", "").f().sx("tri6").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTri6(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_tri6')); $('#", classeApiMethodeMethode, "_tri6').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setTri6', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri6')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri6')); }); ")
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

	//////////
	// tri7 //
	//////////

	/**	L'entité « tri7 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Double tri7;
	@JsonIgnore
	public Couverture<Double> tri7Couverture = new Couverture<Double>().p(this).c(Double.class).var("tri7").o(tri7);

	/**	<br/>L'entité « tri7 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tri7">Trouver l'entité tri7 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tri7(Couverture<Double> c);

	public Double getTri7() {
		return tri7;
	}

	public void setTri7(Double tri7) {
		this.tri7 = tri7;
		this.tri7Couverture.dejaInitialise = true;
	}
	public PartHtml setTri7(String o) {
		if(NumberUtils.isParsable(o))
			this.tri7 = Double.parseDouble(o);
		this.tri7Couverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml tri7Init() {
		if(!tri7Couverture.dejaInitialise) {
			_tri7(tri7Couverture);
			if(tri7 == null)
				setTri7(tri7Couverture.o);
		}
		tri7Couverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Double solrTri7() {
		return tri7;
	}

	public String strTri7() {
		return tri7 == null ? "" : tri7.toString();
	}

	public String jsonTri7() {
		return tri7 == null ? "" : tri7.toString();
	}

	public String nomAffichageTri7() {
		return "tri7";
	}

	public String htmTooltipTri7() {
		return null;
	}

	public String htmTri7() {
		return tri7 == null ? "" : StringEscapeUtils.escapeHtml4(strTri7());
	}

	public void inputTri7(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "tri7")
				.a("id", classeApiMethodeMethode, "_tri7");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTri7 inputPartHtml", pk, "Tri7 w3-input w3-border ");
					a("name", "setTri7");
				} else {
					a("class", "valeurTri7 w3-input w3-border inputPartHtml", pk, "Tri7 w3-input w3-border ");
					a("name", "tri7");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setTri7', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri7')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri7')); }); ");
				}
				a("value", strTri7())
			.fg();

		}
	}

	public void htmTri7(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlTri7").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_tri7").a("class", "").f().sx("tri7").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTri7(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_tri7')); $('#", classeApiMethodeMethode, "_tri7').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setTri7', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri7')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri7')); }); ")
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

	//////////
	// tri8 //
	//////////

	/**	L'entité « tri8 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Double tri8;
	@JsonIgnore
	public Couverture<Double> tri8Couverture = new Couverture<Double>().p(this).c(Double.class).var("tri8").o(tri8);

	/**	<br/>L'entité « tri8 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tri8">Trouver l'entité tri8 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tri8(Couverture<Double> c);

	public Double getTri8() {
		return tri8;
	}

	public void setTri8(Double tri8) {
		this.tri8 = tri8;
		this.tri8Couverture.dejaInitialise = true;
	}
	public PartHtml setTri8(String o) {
		if(NumberUtils.isParsable(o))
			this.tri8 = Double.parseDouble(o);
		this.tri8Couverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml tri8Init() {
		if(!tri8Couverture.dejaInitialise) {
			_tri8(tri8Couverture);
			if(tri8 == null)
				setTri8(tri8Couverture.o);
		}
		tri8Couverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Double solrTri8() {
		return tri8;
	}

	public String strTri8() {
		return tri8 == null ? "" : tri8.toString();
	}

	public String jsonTri8() {
		return tri8 == null ? "" : tri8.toString();
	}

	public String nomAffichageTri8() {
		return "tri8";
	}

	public String htmTooltipTri8() {
		return null;
	}

	public String htmTri8() {
		return tri8 == null ? "" : StringEscapeUtils.escapeHtml4(strTri8());
	}

	public void inputTri8(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "tri8")
				.a("id", classeApiMethodeMethode, "_tri8");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTri8 inputPartHtml", pk, "Tri8 w3-input w3-border ");
					a("name", "setTri8");
				} else {
					a("class", "valeurTri8 w3-input w3-border inputPartHtml", pk, "Tri8 w3-input w3-border ");
					a("name", "tri8");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setTri8', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri8')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri8')); }); ");
				}
				a("value", strTri8())
			.fg();

		}
	}

	public void htmTri8(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlTri8").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_tri8").a("class", "").f().sx("tri8").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTri8(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_tri8')); $('#", classeApiMethodeMethode, "_tri8').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setTri8', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri8')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri8')); }); ")
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

	//////////
	// tri9 //
	//////////

	/**	L'entité « tri9 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Double tri9;
	@JsonIgnore
	public Couverture<Double> tri9Couverture = new Couverture<Double>().p(this).c(Double.class).var("tri9").o(tri9);

	/**	<br/>L'entité « tri9 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tri9">Trouver l'entité tri9 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tri9(Couverture<Double> c);

	public Double getTri9() {
		return tri9;
	}

	public void setTri9(Double tri9) {
		this.tri9 = tri9;
		this.tri9Couverture.dejaInitialise = true;
	}
	public PartHtml setTri9(String o) {
		if(NumberUtils.isParsable(o))
			this.tri9 = Double.parseDouble(o);
		this.tri9Couverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml tri9Init() {
		if(!tri9Couverture.dejaInitialise) {
			_tri9(tri9Couverture);
			if(tri9 == null)
				setTri9(tri9Couverture.o);
		}
		tri9Couverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Double solrTri9() {
		return tri9;
	}

	public String strTri9() {
		return tri9 == null ? "" : tri9.toString();
	}

	public String jsonTri9() {
		return tri9 == null ? "" : tri9.toString();
	}

	public String nomAffichageTri9() {
		return "tri9";
	}

	public String htmTooltipTri9() {
		return null;
	}

	public String htmTri9() {
		return tri9 == null ? "" : StringEscapeUtils.escapeHtml4(strTri9());
	}

	public void inputTri9(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "tri9")
				.a("id", classeApiMethodeMethode, "_tri9");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTri9 inputPartHtml", pk, "Tri9 w3-input w3-border ");
					a("name", "setTri9");
				} else {
					a("class", "valeurTri9 w3-input w3-border inputPartHtml", pk, "Tri9 w3-input w3-border ");
					a("name", "tri9");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setTri9', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri9')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri9')); }); ");
				}
				a("value", strTri9())
			.fg();

		}
	}

	public void htmTri9(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlTri9").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_tri9").a("class", "").f().sx("tri9").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTri9(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_tri9')); $('#", classeApiMethodeMethode, "_tri9').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setTri9', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri9')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri9')); }); ")
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

	///////////
	// tri10 //
	///////////

	/**	L'entité « tri10 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Double tri10;
	@JsonIgnore
	public Couverture<Double> tri10Couverture = new Couverture<Double>().p(this).c(Double.class).var("tri10").o(tri10);

	/**	<br/>L'entité « tri10 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tri10">Trouver l'entité tri10 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tri10(Couverture<Double> c);

	public Double getTri10() {
		return tri10;
	}

	public void setTri10(Double tri10) {
		this.tri10 = tri10;
		this.tri10Couverture.dejaInitialise = true;
	}
	public PartHtml setTri10(String o) {
		if(NumberUtils.isParsable(o))
			this.tri10 = Double.parseDouble(o);
		this.tri10Couverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml tri10Init() {
		if(!tri10Couverture.dejaInitialise) {
			_tri10(tri10Couverture);
			if(tri10 == null)
				setTri10(tri10Couverture.o);
		}
		tri10Couverture.dejaInitialise(true);
		return (PartHtml)this;
	}

	public Double solrTri10() {
		return tri10;
	}

	public String strTri10() {
		return tri10 == null ? "" : tri10.toString();
	}

	public String jsonTri10() {
		return tri10 == null ? "" : tri10.toString();
	}

	public String nomAffichageTri10() {
		return "tri10";
	}

	public String htmTooltipTri10() {
		return null;
	}

	public String htmTri10() {
		return tri10 == null ? "" : StringEscapeUtils.escapeHtml4(strTri10());
	}

	public void inputTri10(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "tri10")
				.a("id", classeApiMethodeMethode, "_tri10");
				if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
					a("class", "setTri10 inputPartHtml", pk, "Tri10 w3-input w3-border ");
					a("name", "setTri10");
				} else {
					a("class", "valeurTri10 w3-input w3-border inputPartHtml", pk, "Tri10 w3-input w3-border ");
					a("name", "tri10");
				}
				if("Page".equals(classeApiMethodeMethode)) {
					a("onclick", "enleverLueur($(this)); ");
					a("onchange", "patchPartHtmlVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setTri10', $(this).val(), function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri10')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri10')); }); ");
				}
				a("value", strTri10())
			.fg();

		}
	}

	public void htmTri10(String classeApiMethodeMethode) {
		PartHtml s = (PartHtml)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggere", classeApiMethodeMethode, "PartHtmlTri10").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classeApiMethodeMethode, "_tri10").a("class", "").f().sx("tri10").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputTri10(classeApiMethodeMethode);
							} g("div");
							{
								if("Page".equals(classeApiMethodeMethode)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "enleverLueur($('#", classeApiMethodeMethode, "_tri10')); $('#", classeApiMethodeMethode, "_tri10').val(null); patchPartHtmlVal([{ name: 'fq', value: 'pk:' + $('#PartHtmlForm :input[name=pk]').val() }], 'setTri10', null, function() { ajouterLueur($('#", classeApiMethodeMethode, "_tri10')); }, function() { ajouterErreur($('#", classeApiMethodeMethode, "_tri10')); }); ")
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
	// initLoin //
	//////////////

	protected boolean dejaInitialisePartHtml = false;

	public PartHtml initLoinPartHtml(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialisePartHtml) {
			dejaInitialisePartHtml = true;
			initLoinPartHtml();
		}
		return (PartHtml)this;
	}

	public void initLoinPartHtml() {
		initPartHtml();
		super.initLoinCluster(requeteSite_);
	}

	public void initPartHtml() {
		partHtmlCleInit();
		designPageClesInit();
		htmlLienInit();
		htmlElementInit();
		htmlIdInit();
		htmlClassesInit();
		htmlStyleInit();
		htmlAvantInit();
		htmlApresInit();
		htmlTexteInit();
		htmlVarInit();
		htmlVarSpanInit();
		htmlVarFormInit();
		htmlVarInputInit();
		htmlVarForEachInit();
		htmlExclureInit();
		pdfExclureInit();
		connecterDeconnecterInit();
		tri1Init();
		tri2Init();
		tri3Init();
		tri4Init();
		tri5Init();
		tri6Init();
		tri7Init();
		tri8Init();
		tri9Init();
		tri10Init();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinPartHtml(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePartHtml(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSitePartHtml(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirPartHtml(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirPartHtml(String var) {
		PartHtml oPartHtml = (PartHtml)this;
		switch(var) {
			case "partHtmlCle":
				return oPartHtml.partHtmlCle;
			case "designPageCles":
				return oPartHtml.designPageCles;
			case "htmlLien":
				return oPartHtml.htmlLien;
			case "htmlElement":
				return oPartHtml.htmlElement;
			case "htmlId":
				return oPartHtml.htmlId;
			case "htmlClasses":
				return oPartHtml.htmlClasses;
			case "htmlStyle":
				return oPartHtml.htmlStyle;
			case "htmlAvant":
				return oPartHtml.htmlAvant;
			case "htmlApres":
				return oPartHtml.htmlApres;
			case "htmlTexte":
				return oPartHtml.htmlTexte;
			case "htmlVar":
				return oPartHtml.htmlVar;
			case "htmlVarSpan":
				return oPartHtml.htmlVarSpan;
			case "htmlVarForm":
				return oPartHtml.htmlVarForm;
			case "htmlVarInput":
				return oPartHtml.htmlVarInput;
			case "htmlVarForEach":
				return oPartHtml.htmlVarForEach;
			case "htmlExclure":
				return oPartHtml.htmlExclure;
			case "pdfExclure":
				return oPartHtml.pdfExclure;
			case "connecterDeconnecter":
				return oPartHtml.connecterDeconnecter;
			case "tri1":
				return oPartHtml.tri1;
			case "tri2":
				return oPartHtml.tri2;
			case "tri3":
				return oPartHtml.tri3;
			case "tri4":
				return oPartHtml.tri4;
			case "tri5":
				return oPartHtml.tri5;
			case "tri6":
				return oPartHtml.tri6;
			case "tri7":
				return oPartHtml.tri7;
			case "tri8":
				return oPartHtml.tri8;
			case "tri9":
				return oPartHtml.tri9;
			case "tri10":
				return oPartHtml.tri10;
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
				o = attribuerPartHtml(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerPartHtml(String var, Object val) {
		PartHtml oPartHtml = (PartHtml)this;
		switch(var) {
			case "designPageCles":
				oPartHtml.addDesignPageCles((Long)val);
				if(!sauvegardesPartHtml.contains(var))
					sauvegardesPartHtml.add(var);
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
					o = definirPartHtml(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPartHtml(String var, String val) {
		switch(var) {
			case "htmlLien":
				setHtmlLien(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlElement":
				setHtmlElement(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlId":
				setHtmlId(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlClasses":
				setHtmlClasses(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlStyle":
				setHtmlStyle(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlAvant":
				setHtmlAvant(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlApres":
				setHtmlApres(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlTexte":
				setHtmlTexte(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlVar":
				setHtmlVar(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlVarSpan":
				setHtmlVarSpan(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlVarForm":
				setHtmlVarForm(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlVarInput":
				setHtmlVarInput(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlVarForEach":
				setHtmlVarForEach(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "htmlExclure":
				setHtmlExclure(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "pdfExclure":
				setPdfExclure(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "connecterDeconnecter":
				setConnecterDeconnecter(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "tri1":
				setTri1(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "tri2":
				setTri2(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "tri3":
				setTri3(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "tri4":
				setTri4(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "tri5":
				setTri5(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "tri6":
				setTri6(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "tri7":
				setTri7(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "tri8":
				setTri8(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "tri9":
				setTri9(val);
				sauvegardesPartHtml.add(var);
				return val;
			case "tri10":
				setTri10(val);
				sauvegardesPartHtml.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesPartHtml = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerPartHtml(solrDocument);
	}
	public void peuplerPartHtml(SolrDocument solrDocument) {
		PartHtml oPartHtml = (PartHtml)this;
		sauvegardesPartHtml = (List<String>)solrDocument.get("sauvegardesPartHtml_stored_strings");
		if(sauvegardesPartHtml != null) {

			if(sauvegardesPartHtml.contains("partHtmlCle")) {
				Long partHtmlCle = (Long)solrDocument.get("partHtmlCle_stored_long");
				if(partHtmlCle != null)
					oPartHtml.setPartHtmlCle(partHtmlCle);
			}

			List<Long> designPageCles = (List<Long>)solrDocument.get("designPageCles_stored_longs");
			if(designPageCles != null)
				oPartHtml.designPageCles.addAll(designPageCles);

			if(sauvegardesPartHtml.contains("htmlLien")) {
				String htmlLien = (String)solrDocument.get("htmlLien_stored_string");
				if(htmlLien != null)
					oPartHtml.setHtmlLien(htmlLien);
			}

			if(sauvegardesPartHtml.contains("htmlElement")) {
				String htmlElement = (String)solrDocument.get("htmlElement_stored_string");
				if(htmlElement != null)
					oPartHtml.setHtmlElement(htmlElement);
			}

			if(sauvegardesPartHtml.contains("htmlId")) {
				String htmlId = (String)solrDocument.get("htmlId_stored_string");
				if(htmlId != null)
					oPartHtml.setHtmlId(htmlId);
			}

			if(sauvegardesPartHtml.contains("htmlClasses")) {
				String htmlClasses = (String)solrDocument.get("htmlClasses_stored_string");
				if(htmlClasses != null)
					oPartHtml.setHtmlClasses(htmlClasses);
			}

			if(sauvegardesPartHtml.contains("htmlStyle")) {
				String htmlStyle = (String)solrDocument.get("htmlStyle_stored_string");
				if(htmlStyle != null)
					oPartHtml.setHtmlStyle(htmlStyle);
			}

			if(sauvegardesPartHtml.contains("htmlAvant")) {
				String htmlAvant = (String)solrDocument.get("htmlAvant_stored_string");
				if(htmlAvant != null)
					oPartHtml.setHtmlAvant(htmlAvant);
			}

			if(sauvegardesPartHtml.contains("htmlApres")) {
				String htmlApres = (String)solrDocument.get("htmlApres_stored_string");
				if(htmlApres != null)
					oPartHtml.setHtmlApres(htmlApres);
			}

			if(sauvegardesPartHtml.contains("htmlTexte")) {
				String htmlTexte = (String)solrDocument.get("htmlTexte_stored_string");
				if(htmlTexte != null)
					oPartHtml.setHtmlTexte(htmlTexte);
			}

			if(sauvegardesPartHtml.contains("htmlVar")) {
				String htmlVar = (String)solrDocument.get("htmlVar_stored_string");
				if(htmlVar != null)
					oPartHtml.setHtmlVar(htmlVar);
			}

			if(sauvegardesPartHtml.contains("htmlVarSpan")) {
				String htmlVarSpan = (String)solrDocument.get("htmlVarSpan_stored_string");
				if(htmlVarSpan != null)
					oPartHtml.setHtmlVarSpan(htmlVarSpan);
			}

			if(sauvegardesPartHtml.contains("htmlVarForm")) {
				String htmlVarForm = (String)solrDocument.get("htmlVarForm_stored_string");
				if(htmlVarForm != null)
					oPartHtml.setHtmlVarForm(htmlVarForm);
			}

			if(sauvegardesPartHtml.contains("htmlVarInput")) {
				String htmlVarInput = (String)solrDocument.get("htmlVarInput_stored_string");
				if(htmlVarInput != null)
					oPartHtml.setHtmlVarInput(htmlVarInput);
			}

			if(sauvegardesPartHtml.contains("htmlVarForEach")) {
				String htmlVarForEach = (String)solrDocument.get("htmlVarForEach_stored_string");
				if(htmlVarForEach != null)
					oPartHtml.setHtmlVarForEach(htmlVarForEach);
			}

			if(sauvegardesPartHtml.contains("htmlExclure")) {
				Boolean htmlExclure = (Boolean)solrDocument.get("htmlExclure_stored_boolean");
				if(htmlExclure != null)
					oPartHtml.setHtmlExclure(htmlExclure);
			}

			if(sauvegardesPartHtml.contains("pdfExclure")) {
				Boolean pdfExclure = (Boolean)solrDocument.get("pdfExclure_stored_boolean");
				if(pdfExclure != null)
					oPartHtml.setPdfExclure(pdfExclure);
			}

			if(sauvegardesPartHtml.contains("connecterDeconnecter")) {
				Boolean connecterDeconnecter = (Boolean)solrDocument.get("connecterDeconnecter_stored_boolean");
				if(connecterDeconnecter != null)
					oPartHtml.setConnecterDeconnecter(connecterDeconnecter);
			}

			if(sauvegardesPartHtml.contains("tri1")) {
				Double tri1 = (Double)solrDocument.get("tri1_stored_double");
				if(tri1 != null)
					oPartHtml.setTri1(tri1);
			}

			if(sauvegardesPartHtml.contains("tri2")) {
				Double tri2 = (Double)solrDocument.get("tri2_stored_double");
				if(tri2 != null)
					oPartHtml.setTri2(tri2);
			}

			if(sauvegardesPartHtml.contains("tri3")) {
				Double tri3 = (Double)solrDocument.get("tri3_stored_double");
				if(tri3 != null)
					oPartHtml.setTri3(tri3);
			}

			if(sauvegardesPartHtml.contains("tri4")) {
				Double tri4 = (Double)solrDocument.get("tri4_stored_double");
				if(tri4 != null)
					oPartHtml.setTri4(tri4);
			}

			if(sauvegardesPartHtml.contains("tri5")) {
				Double tri5 = (Double)solrDocument.get("tri5_stored_double");
				if(tri5 != null)
					oPartHtml.setTri5(tri5);
			}

			if(sauvegardesPartHtml.contains("tri6")) {
				Double tri6 = (Double)solrDocument.get("tri6_stored_double");
				if(tri6 != null)
					oPartHtml.setTri6(tri6);
			}

			if(sauvegardesPartHtml.contains("tri7")) {
				Double tri7 = (Double)solrDocument.get("tri7_stored_double");
				if(tri7 != null)
					oPartHtml.setTri7(tri7);
			}

			if(sauvegardesPartHtml.contains("tri8")) {
				Double tri8 = (Double)solrDocument.get("tri8_stored_double");
				if(tri8 != null)
					oPartHtml.setTri8(tri8);
			}

			if(sauvegardesPartHtml.contains("tri9")) {
				Double tri9 = (Double)solrDocument.get("tri9_stored_double");
				if(tri9 != null)
					oPartHtml.setTri9(tri9);
			}

			if(sauvegardesPartHtml.contains("tri10")) {
				Double tri10 = (Double)solrDocument.get("tri10_stored_double");
				if(tri10 != null)
					oPartHtml.setTri10(tri10);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.html.part.PartHtml"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			PartHtml o = new PartHtml();
			o.requeteSitePartHtml(requeteSite);
			o.initLoinPartHtml(requeteSite);
			o.indexerPartHtml();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerPartHtml();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerPartHtml(document);
	}

	public void indexerPartHtml(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerPartHtml(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerPartHtml() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerPartHtml(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerPartHtml(SolrInputDocument document) {
		if(sauvegardesPartHtml != null)
			document.addField("sauvegardesPartHtml_stored_strings", sauvegardesPartHtml);

		if(partHtmlCle != null) {
			document.addField("partHtmlCle_indexed_long", partHtmlCle);
			document.addField("partHtmlCle_stored_long", partHtmlCle);
		}
		if(designPageCles != null) {
			for(java.lang.Long o : designPageCles) {
				document.addField("designPageCles_indexed_longs", o);
			}
			for(java.lang.Long o : designPageCles) {
				document.addField("designPageCles_stored_longs", o);
			}
		}
		if(htmlLien != null) {
			document.addField("htmlLien_indexed_string", htmlLien);
			document.addField("htmlLien_stored_string", htmlLien);
		}
		if(htmlElement != null) {
			document.addField("htmlElement_indexed_string", htmlElement);
			document.addField("htmlElement_stored_string", htmlElement);
		}
		if(htmlId != null) {
			document.addField("htmlId_indexed_string", htmlId);
			document.addField("htmlId_stored_string", htmlId);
		}
		if(htmlClasses != null) {
			document.addField("htmlClasses_indexed_string", htmlClasses);
			document.addField("htmlClasses_stored_string", htmlClasses);
		}
		if(htmlStyle != null) {
			document.addField("htmlStyle_indexed_string", htmlStyle);
			document.addField("htmlStyle_stored_string", htmlStyle);
		}
		if(htmlAvant != null) {
			document.addField("htmlAvant_stored_string", htmlAvant);
		}
		if(htmlApres != null) {
			document.addField("htmlApres_stored_string", htmlApres);
		}
		if(htmlTexte != null) {
			document.addField("htmlTexte_stored_string", htmlTexte);
		}
		if(htmlVar != null) {
			document.addField("htmlVar_indexed_string", htmlVar);
			document.addField("htmlVar_stored_string", htmlVar);
		}
		if(htmlVarSpan != null) {
			document.addField("htmlVarSpan_indexed_string", htmlVarSpan);
			document.addField("htmlVarSpan_stored_string", htmlVarSpan);
		}
		if(htmlVarForm != null) {
			document.addField("htmlVarForm_indexed_string", htmlVarForm);
			document.addField("htmlVarForm_stored_string", htmlVarForm);
		}
		if(htmlVarInput != null) {
			document.addField("htmlVarInput_indexed_string", htmlVarInput);
			document.addField("htmlVarInput_stored_string", htmlVarInput);
		}
		if(htmlVarForEach != null) {
			document.addField("htmlVarForEach_indexed_string", htmlVarForEach);
			document.addField("htmlVarForEach_stored_string", htmlVarForEach);
		}
		if(htmlExclure != null) {
			document.addField("htmlExclure_indexed_boolean", htmlExclure);
			document.addField("htmlExclure_stored_boolean", htmlExclure);
		}
		if(pdfExclure != null) {
			document.addField("pdfExclure_indexed_boolean", pdfExclure);
			document.addField("pdfExclure_stored_boolean", pdfExclure);
		}
		if(connecterDeconnecter != null) {
			document.addField("connecterDeconnecter_indexed_boolean", connecterDeconnecter);
			document.addField("connecterDeconnecter_stored_boolean", connecterDeconnecter);
		}
		if(tri1 != null) {
			document.addField("tri1_indexed_double", tri1);
			document.addField("tri1_stored_double", tri1);
		}
		if(tri2 != null) {
			document.addField("tri2_indexed_double", tri2);
			document.addField("tri2_stored_double", tri2);
		}
		if(tri3 != null) {
			document.addField("tri3_indexed_double", tri3);
			document.addField("tri3_stored_double", tri3);
		}
		if(tri4 != null) {
			document.addField("tri4_indexed_double", tri4);
			document.addField("tri4_stored_double", tri4);
		}
		if(tri5 != null) {
			document.addField("tri5_indexed_double", tri5);
			document.addField("tri5_stored_double", tri5);
		}
		if(tri6 != null) {
			document.addField("tri6_indexed_double", tri6);
			document.addField("tri6_stored_double", tri6);
		}
		if(tri7 != null) {
			document.addField("tri7_indexed_double", tri7);
			document.addField("tri7_stored_double", tri7);
		}
		if(tri8 != null) {
			document.addField("tri8_indexed_double", tri8);
			document.addField("tri8_stored_double", tri8);
		}
		if(tri9 != null) {
			document.addField("tri9_indexed_double", tri9);
			document.addField("tri9_stored_double", tri9);
		}
		if(tri10 != null) {
			document.addField("tri10_indexed_double", tri10);
			document.addField("tri10_stored_double", tri10);
		}
		super.indexerCluster(document);

	}

	public void desindexerPartHtml() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinPartHtml(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexePartHtml(String entiteVar) {
		switch(entiteVar) {
			case "partHtmlCle":
				return "partHtmlCle_indexed_long";
			case "designPageCles":
				return "designPageCles_indexed_longs";
			case "htmlLien":
				return "htmlLien_indexed_string";
			case "htmlElement":
				return "htmlElement_indexed_string";
			case "htmlId":
				return "htmlId_indexed_string";
			case "htmlClasses":
				return "htmlClasses_indexed_string";
			case "htmlStyle":
				return "htmlStyle_indexed_string";
			case "htmlVar":
				return "htmlVar_indexed_string";
			case "htmlVarSpan":
				return "htmlVarSpan_indexed_string";
			case "htmlVarForm":
				return "htmlVarForm_indexed_string";
			case "htmlVarInput":
				return "htmlVarInput_indexed_string";
			case "htmlVarForEach":
				return "htmlVarForEach_indexed_string";
			case "htmlExclure":
				return "htmlExclure_indexed_boolean";
			case "pdfExclure":
				return "pdfExclure_indexed_boolean";
			case "connecterDeconnecter":
				return "connecterDeconnecter_indexed_boolean";
			case "tri1":
				return "tri1_indexed_double";
			case "tri2":
				return "tri2_indexed_double";
			case "tri3":
				return "tri3_indexed_double";
			case "tri4":
				return "tri4_indexed_double";
			case "tri5":
				return "tri5_indexed_double";
			case "tri6":
				return "tri6_indexed_double";
			case "tri7":
				return "tri7_indexed_double";
			case "tri8":
				return "tri8_indexed_double";
			case "tri9":
				return "tri9_indexed_double";
			case "tri10":
				return "tri10_indexed_double";
			default:
				return Cluster.varIndexeCluster(entiteVar);
		}
	}

	public static String varRecherchePartHtml(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varRechercheCluster(entiteVar);
		}
	}

	public static String varSuggerePartHtml(String entiteVar) {
		switch(entiteVar) {
			default:
				return Cluster.varSuggereCluster(entiteVar);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerPartHtml(solrDocument);
	}
	public void stockerPartHtml(SolrDocument solrDocument) {
		PartHtml oPartHtml = (PartHtml)this;

		Long partHtmlCle = (Long)solrDocument.get("partHtmlCle_stored_long");
		if(partHtmlCle != null)
			oPartHtml.setPartHtmlCle(partHtmlCle);

		List<Long> designPageCles = (List<Long>)solrDocument.get("designPageCles_stored_longs");
		if(designPageCles != null)
			oPartHtml.designPageCles.addAll(designPageCles);

		String htmlLien = (String)solrDocument.get("htmlLien_stored_string");
		if(htmlLien != null)
			oPartHtml.setHtmlLien(htmlLien);

		String htmlElement = (String)solrDocument.get("htmlElement_stored_string");
		if(htmlElement != null)
			oPartHtml.setHtmlElement(htmlElement);

		String htmlId = (String)solrDocument.get("htmlId_stored_string");
		if(htmlId != null)
			oPartHtml.setHtmlId(htmlId);

		String htmlClasses = (String)solrDocument.get("htmlClasses_stored_string");
		if(htmlClasses != null)
			oPartHtml.setHtmlClasses(htmlClasses);

		String htmlStyle = (String)solrDocument.get("htmlStyle_stored_string");
		if(htmlStyle != null)
			oPartHtml.setHtmlStyle(htmlStyle);

		String htmlAvant = (String)solrDocument.get("htmlAvant_stored_string");
		if(htmlAvant != null)
			oPartHtml.setHtmlAvant(htmlAvant);

		String htmlApres = (String)solrDocument.get("htmlApres_stored_string");
		if(htmlApres != null)
			oPartHtml.setHtmlApres(htmlApres);

		String htmlTexte = (String)solrDocument.get("htmlTexte_stored_string");
		if(htmlTexte != null)
			oPartHtml.setHtmlTexte(htmlTexte);

		String htmlVar = (String)solrDocument.get("htmlVar_stored_string");
		if(htmlVar != null)
			oPartHtml.setHtmlVar(htmlVar);

		String htmlVarSpan = (String)solrDocument.get("htmlVarSpan_stored_string");
		if(htmlVarSpan != null)
			oPartHtml.setHtmlVarSpan(htmlVarSpan);

		String htmlVarForm = (String)solrDocument.get("htmlVarForm_stored_string");
		if(htmlVarForm != null)
			oPartHtml.setHtmlVarForm(htmlVarForm);

		String htmlVarInput = (String)solrDocument.get("htmlVarInput_stored_string");
		if(htmlVarInput != null)
			oPartHtml.setHtmlVarInput(htmlVarInput);

		String htmlVarForEach = (String)solrDocument.get("htmlVarForEach_stored_string");
		if(htmlVarForEach != null)
			oPartHtml.setHtmlVarForEach(htmlVarForEach);

		Boolean htmlExclure = (Boolean)solrDocument.get("htmlExclure_stored_boolean");
		if(htmlExclure != null)
			oPartHtml.setHtmlExclure(htmlExclure);

		Boolean pdfExclure = (Boolean)solrDocument.get("pdfExclure_stored_boolean");
		if(pdfExclure != null)
			oPartHtml.setPdfExclure(pdfExclure);

		Boolean connecterDeconnecter = (Boolean)solrDocument.get("connecterDeconnecter_stored_boolean");
		if(connecterDeconnecter != null)
			oPartHtml.setConnecterDeconnecter(connecterDeconnecter);

		Double tri1 = (Double)solrDocument.get("tri1_stored_double");
		if(tri1 != null)
			oPartHtml.setTri1(tri1);

		Double tri2 = (Double)solrDocument.get("tri2_stored_double");
		if(tri2 != null)
			oPartHtml.setTri2(tri2);

		Double tri3 = (Double)solrDocument.get("tri3_stored_double");
		if(tri3 != null)
			oPartHtml.setTri3(tri3);

		Double tri4 = (Double)solrDocument.get("tri4_stored_double");
		if(tri4 != null)
			oPartHtml.setTri4(tri4);

		Double tri5 = (Double)solrDocument.get("tri5_stored_double");
		if(tri5 != null)
			oPartHtml.setTri5(tri5);

		Double tri6 = (Double)solrDocument.get("tri6_stored_double");
		if(tri6 != null)
			oPartHtml.setTri6(tri6);

		Double tri7 = (Double)solrDocument.get("tri7_stored_double");
		if(tri7 != null)
			oPartHtml.setTri7(tri7);

		Double tri8 = (Double)solrDocument.get("tri8_stored_double");
		if(tri8 != null)
			oPartHtml.setTri8(tri8);

		Double tri9 = (Double)solrDocument.get("tri9_stored_double");
		if(tri9 != null)
			oPartHtml.setTri9(tri9);

		Double tri10 = (Double)solrDocument.get("tri10_stored_double");
		if(tri10 != null)
			oPartHtml.setTri10(tri10);

		super.stockerCluster(solrDocument);
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiPartHtml() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof PartHtml) {
			PartHtml original = (PartHtml)o;
			if(!Objects.equals(designPageCles, original.getDesignPageCles()))
				requeteApi.addVars("designPageCles");
			if(!Objects.equals(htmlLien, original.getHtmlLien()))
				requeteApi.addVars("htmlLien");
			if(!Objects.equals(htmlElement, original.getHtmlElement()))
				requeteApi.addVars("htmlElement");
			if(!Objects.equals(htmlId, original.getHtmlId()))
				requeteApi.addVars("htmlId");
			if(!Objects.equals(htmlClasses, original.getHtmlClasses()))
				requeteApi.addVars("htmlClasses");
			if(!Objects.equals(htmlStyle, original.getHtmlStyle()))
				requeteApi.addVars("htmlStyle");
			if(!Objects.equals(htmlAvant, original.getHtmlAvant()))
				requeteApi.addVars("htmlAvant");
			if(!Objects.equals(htmlApres, original.getHtmlApres()))
				requeteApi.addVars("htmlApres");
			if(!Objects.equals(htmlTexte, original.getHtmlTexte()))
				requeteApi.addVars("htmlTexte");
			if(!Objects.equals(htmlVar, original.getHtmlVar()))
				requeteApi.addVars("htmlVar");
			if(!Objects.equals(htmlVarSpan, original.getHtmlVarSpan()))
				requeteApi.addVars("htmlVarSpan");
			if(!Objects.equals(htmlVarForm, original.getHtmlVarForm()))
				requeteApi.addVars("htmlVarForm");
			if(!Objects.equals(htmlVarInput, original.getHtmlVarInput()))
				requeteApi.addVars("htmlVarInput");
			if(!Objects.equals(htmlVarForEach, original.getHtmlVarForEach()))
				requeteApi.addVars("htmlVarForEach");
			if(!Objects.equals(htmlExclure, original.getHtmlExclure()))
				requeteApi.addVars("htmlExclure");
			if(!Objects.equals(pdfExclure, original.getPdfExclure()))
				requeteApi.addVars("pdfExclure");
			if(!Objects.equals(connecterDeconnecter, original.getConnecterDeconnecter()))
				requeteApi.addVars("connecterDeconnecter");
			if(!Objects.equals(tri1, original.getTri1()))
				requeteApi.addVars("tri1");
			if(!Objects.equals(tri2, original.getTri2()))
				requeteApi.addVars("tri2");
			if(!Objects.equals(tri3, original.getTri3()))
				requeteApi.addVars("tri3");
			if(!Objects.equals(tri4, original.getTri4()))
				requeteApi.addVars("tri4");
			if(!Objects.equals(tri5, original.getTri5()))
				requeteApi.addVars("tri5");
			if(!Objects.equals(tri6, original.getTri6()))
				requeteApi.addVars("tri6");
			if(!Objects.equals(tri7, original.getTri7()))
				requeteApi.addVars("tri7");
			if(!Objects.equals(tri8, original.getTri8()))
				requeteApi.addVars("tri8");
			if(!Objects.equals(tri9, original.getTri9()))
				requeteApi.addVars("tri9");
			if(!Objects.equals(tri10, original.getTri10()))
				requeteApi.addVars("tri10");
			super.requeteApiCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), designPageCles, htmlLien, htmlElement, htmlId, htmlClasses, htmlStyle, htmlAvant, htmlApres, htmlTexte, htmlVar, htmlVarSpan, htmlVarForm, htmlVarInput, htmlVarForEach, htmlExclure, pdfExclure, connecterDeconnecter, tri1, tri2, tri3, tri4, tri5, tri6, tri7, tri8, tri9, tri10);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof PartHtml))
			return false;
		PartHtml that = (PartHtml)o;
		return super.equals(o)
				&& Objects.equals( designPageCles, that.designPageCles )
				&& Objects.equals( htmlLien, that.htmlLien )
				&& Objects.equals( htmlElement, that.htmlElement )
				&& Objects.equals( htmlId, that.htmlId )
				&& Objects.equals( htmlClasses, that.htmlClasses )
				&& Objects.equals( htmlStyle, that.htmlStyle )
				&& Objects.equals( htmlAvant, that.htmlAvant )
				&& Objects.equals( htmlApres, that.htmlApres )
				&& Objects.equals( htmlTexte, that.htmlTexte )
				&& Objects.equals( htmlVar, that.htmlVar )
				&& Objects.equals( htmlVarSpan, that.htmlVarSpan )
				&& Objects.equals( htmlVarForm, that.htmlVarForm )
				&& Objects.equals( htmlVarInput, that.htmlVarInput )
				&& Objects.equals( htmlVarForEach, that.htmlVarForEach )
				&& Objects.equals( htmlExclure, that.htmlExclure )
				&& Objects.equals( pdfExclure, that.pdfExclure )
				&& Objects.equals( connecterDeconnecter, that.connecterDeconnecter )
				&& Objects.equals( tri1, that.tri1 )
				&& Objects.equals( tri2, that.tri2 )
				&& Objects.equals( tri3, that.tri3 )
				&& Objects.equals( tri4, that.tri4 )
				&& Objects.equals( tri5, that.tri5 )
				&& Objects.equals( tri6, that.tri6 )
				&& Objects.equals( tri7, that.tri7 )
				&& Objects.equals( tri8, that.tri8 )
				&& Objects.equals( tri9, that.tri9 )
				&& Objects.equals( tri10, that.tri10 );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PartHtml { ");
		sb.append( "designPageCles: " ).append(designPageCles);
		sb.append( ", htmlLien: \"" ).append(htmlLien).append( "\"" );
		sb.append( ", htmlElement: \"" ).append(htmlElement).append( "\"" );
		sb.append( ", htmlId: \"" ).append(htmlId).append( "\"" );
		sb.append( ", htmlClasses: \"" ).append(htmlClasses).append( "\"" );
		sb.append( ", htmlStyle: \"" ).append(htmlStyle).append( "\"" );
		sb.append( ", htmlAvant: \"" ).append(htmlAvant).append( "\"" );
		sb.append( ", htmlApres: \"" ).append(htmlApres).append( "\"" );
		sb.append( ", htmlTexte: \"" ).append(htmlTexte).append( "\"" );
		sb.append( ", htmlVar: \"" ).append(htmlVar).append( "\"" );
		sb.append( ", htmlVarSpan: \"" ).append(htmlVarSpan).append( "\"" );
		sb.append( ", htmlVarForm: \"" ).append(htmlVarForm).append( "\"" );
		sb.append( ", htmlVarInput: \"" ).append(htmlVarInput).append( "\"" );
		sb.append( ", htmlVarForEach: \"" ).append(htmlVarForEach).append( "\"" );
		sb.append( ", htmlExclure: " ).append(htmlExclure);
		sb.append( ", pdfExclure: " ).append(pdfExclure);
		sb.append( ", connecterDeconnecter: " ).append(connecterDeconnecter);
		sb.append( ", tri1: " ).append(tri1);
		sb.append( ", tri2: " ).append(tri2);
		sb.append( ", tri3: " ).append(tri3);
		sb.append( ", tri4: " ).append(tri4);
		sb.append( ", tri5: " ).append(tri5);
		sb.append( ", tri6: " ).append(tri6);
		sb.append( ", tri7: " ).append(tri7);
		sb.append( ", tri8: " ).append(tri8);
		sb.append( ", tri9: " ).append(tri9);
		sb.append( ", tri10: " ).append(tri10);
		sb.append(" }");
		return sb.toString();
	}
}
