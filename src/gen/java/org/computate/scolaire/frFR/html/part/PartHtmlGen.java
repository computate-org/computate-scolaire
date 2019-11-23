package org.computate.scolaire.frFR.html.part;

import java.lang.Double;
import java.util.Date;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import java.lang.Long;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.frFR.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true">Trouver la classe sort10 dans Solr</a>
 * <br/>
 **/
public abstract class PartHtmlGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(PartHtml.class);

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
	public static final String PartHtml_LesNoms = "les part de HTMLs";
	public static final String PartHtml_AucunNomTrouve = "aucun part de HTML trouvé";
	public static final String PartHtml_NomVar = "part-html";
	public static final String PartHtml_DeNom = "de part de HTML";
	public static final String PartHtml_NomAdjectifSingulier = "part de HTML";
	public static final String PartHtml_NomAdjectifPluriel = "part de HTMLs";
	public static final String PartHtml_Couleur = "yellow";
	public static final String PartHtml_IconeGroupe = "regular";
	public static final String PartHtml_IconeNom = "sun";
	public static final Integer PartHtml_Rows = 1000000;

	/////////////////
	// partHtmlCle //
	/////////////////

	/**	L'entité « partHtmlCle »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmPartHtmlCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "PartHtmlCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "PartHtmlCle() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setPartHtmlCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichagePartHtmlCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"partHtmlCle\"");
							r.s(" value=\"", htmPartHtmlCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmPartHtmlCle());
			}
			r.l("</div>");
		}
	}

	//////////////////////////
	// designInscriptionCle //
	//////////////////////////

	/**	L'entité « designInscriptionCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long designInscriptionCle;
	@JsonIgnore
	public Couverture<Long> designInscriptionCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("designInscriptionCle").o(designInscriptionCle);

	/**	<br/>L'entité « designInscriptionCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.html.part.PartHtml&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:designInscriptionCle">Trouver l'entité designInscriptionCle dans Solr</a>
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
	public PartHtml setDesignInscriptionCle(String o) {
		if(NumberUtils.isParsable(o))
			this.designInscriptionCle = Long.parseLong(o);
		this.designInscriptionCleCouverture.dejaInitialise = true;
		return (PartHtml)this;
	}
	protected PartHtml designInscriptionCleInit() {
		if(!designInscriptionCleCouverture.dejaInitialise) {
			_designInscriptionCle(designInscriptionCleCouverture);
			if(designInscriptionCle == null)
				setDesignInscriptionCle(designInscriptionCleCouverture.o);
		}
		designInscriptionCleCouverture.dejaInitialise(true);
		return (PartHtml)this;
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
		return "design d'inscription";
	}

	public String htmTooltipDesignInscriptionCle() {
		return null;
	}

	public String htmDesignInscriptionCle() {
		return designInscriptionCle == null ? "" : StringEscapeUtils.escapeHtml4(strDesignInscriptionCle());
	}

	public void htmDesignInscriptionCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "DesignInscriptionCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "DesignInscriptionCle() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setDesignInscriptionCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageDesignInscriptionCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"designInscriptionCle\"");
							r.s(" value=\"", htmDesignInscriptionCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmDesignInscriptionCle());
			}
			r.l("</div>");
		}
	}

	//////////////
	// htmlLien //
	//////////////

	/**	L'entité « htmlLien »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmHtmlLien(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "HtmlLien\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "HtmlLien() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setHtmlLien\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlLien()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlLien\"");
							r.s(" value=\"", htmHtmlLien(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlLien());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// htmlElement //
	/////////////////

	/**	L'entité « htmlElement »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmHtmlElement(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "HtmlElement\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "HtmlElement() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setHtmlElement\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlElement()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlElement\"");
							r.s(" value=\"", htmHtmlElement(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlElement());
			}
			r.l("</div>");
		}
	}

	////////////
	// htmlId //
	////////////

	/**	L'entité « htmlId »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmHtmlId(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "HtmlId\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "HtmlId() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setHtmlId\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlId()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlId\"");
							r.s(" value=\"", htmHtmlId(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlId());
			}
			r.l("</div>");
		}
	}

	/////////////////
	// htmlClasses //
	/////////////////

	/**	L'entité « htmlClasses »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmHtmlClasses(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "HtmlClasses\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "HtmlClasses() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setHtmlClasses\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlClasses()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlClasses\"");
							r.s(" value=\"", htmHtmlClasses(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlClasses());
			}
			r.l("</div>");
		}
	}

	///////////////
	// htmlStyle //
	///////////////

	/**	L'entité « htmlStyle »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmHtmlStyle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "HtmlStyle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "HtmlStyle() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setHtmlStyle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlStyle()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlStyle\"");
							r.s(" value=\"", htmHtmlStyle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlStyle());
			}
			r.l("</div>");
		}
	}

	///////////////
	// htmlAvant //
	///////////////

	/**	L'entité « htmlAvant »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmHtmlAvant(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "HtmlAvant\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "HtmlAvant() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setHtmlAvant\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlAvant()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlAvant\"");
							r.s(" value=\"", htmHtmlAvant(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlAvant());
			}
			r.l("</div>");
		}
	}

	/////////////
	// htmlVar //
	/////////////

	/**	L'entité « htmlVar »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmHtmlVar(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "HtmlVar\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "HtmlVar() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setHtmlVar\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlVar()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlVar\"");
							r.s(" value=\"", htmHtmlVar(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlVar());
			}
			r.l("</div>");
		}
	}

	///////////////
	// htmlApres //
	///////////////

	/**	L'entité « htmlApres »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmHtmlApres(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "HtmlApres\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "HtmlApres() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setHtmlApres\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlApres()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlApres\"");
							r.s(" value=\"", htmHtmlApres(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlApres());
			}
			r.l("</div>");
		}
	}

	///////////////
	// htmlTexte //
	///////////////

	/**	L'entité « htmlTexte »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmHtmlTexte(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "HtmlTexte\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "HtmlTexte() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setHtmlTexte\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlTexte()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlTexte\"");
							r.s(" value=\"", htmHtmlTexte(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlTexte());
			}
			r.l("</div>");
		}
	}

	//////////
	// tri1 //
	//////////

	/**	L'entité « tri1 »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmTri1(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "Tri1\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "Tri1() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setTri1\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageTri1()), "</span>");
				r.s("			<input");
							r.s(" name=\"tri1\"");
							r.s(" value=\"", htmTri1(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmTri1());
			}
			r.l("</div>");
		}
	}

	//////////
	// tri2 //
	//////////

	/**	L'entité « tri2 »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmTri2(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "Tri2\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "Tri2() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setTri2\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageTri2()), "</span>");
				r.s("			<input");
							r.s(" name=\"tri2\"");
							r.s(" value=\"", htmTri2(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmTri2());
			}
			r.l("</div>");
		}
	}

	//////////
	// tri3 //
	//////////

	/**	L'entité « tri3 »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmTri3(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "Tri3\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "Tri3() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setTri3\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageTri3()), "</span>");
				r.s("			<input");
							r.s(" name=\"tri3\"");
							r.s(" value=\"", htmTri3(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmTri3());
			}
			r.l("</div>");
		}
	}

	//////////
	// tri4 //
	//////////

	/**	L'entité « tri4 »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmTri4(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "Tri4\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "Tri4() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setTri4\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageTri4()), "</span>");
				r.s("			<input");
							r.s(" name=\"tri4\"");
							r.s(" value=\"", htmTri4(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmTri4());
			}
			r.l("</div>");
		}
	}

	//////////
	// tri5 //
	//////////

	/**	L'entité « tri5 »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmTri5(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "Tri5\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "Tri5() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setTri5\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageTri5()), "</span>");
				r.s("			<input");
							r.s(" name=\"tri5\"");
							r.s(" value=\"", htmTri5(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmTri5());
			}
			r.l("</div>");
		}
	}

	//////////
	// tri6 //
	//////////

	/**	L'entité « tri6 »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmTri6(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "Tri6\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "Tri6() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setTri6\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageTri6()), "</span>");
				r.s("			<input");
							r.s(" name=\"tri6\"");
							r.s(" value=\"", htmTri6(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmTri6());
			}
			r.l("</div>");
		}
	}

	//////////
	// tri7 //
	//////////

	/**	L'entité « tri7 »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmTri7(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "Tri7\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "Tri7() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setTri7\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageTri7()), "</span>");
				r.s("			<input");
							r.s(" name=\"tri7\"");
							r.s(" value=\"", htmTri7(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmTri7());
			}
			r.l("</div>");
		}
	}

	//////////
	// tri8 //
	//////////

	/**	L'entité « tri8 »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmTri8(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "Tri8\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "Tri8() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setTri8\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageTri8()), "</span>");
				r.s("			<input");
							r.s(" name=\"tri8\"");
							r.s(" value=\"", htmTri8(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmTri8());
			}
			r.l("</div>");
		}
	}

	//////////
	// tri9 //
	//////////

	/**	L'entité « tri9 »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmTri9(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "Tri9\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "Tri9() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setTri9\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageTri9()), "</span>");
				r.s("			<input");
							r.s(" name=\"tri9\"");
							r.s(" value=\"", htmTri9(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmTri9());
			}
			r.l("</div>");
		}
	}

	///////////
	// tri10 //
	///////////

	/**	L'entité « tri10 »
	 *	 is defined as null before being initialized. 
	 */
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

	public void htmTri10(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartHtml", strPk(), "Tri10\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartHtml", strPk(), "Tri10() {");
				r.l("			$.ajax({");
				r.l("				url: '?fq=pk:", strPk(), "',");
				r.l("				dataType: 'json',");
				r.l("				type: 'patch',");
				r.l("				contentType: 'application/json',");
				r.l("				processData: false,");
				r.l("				success: function( data, textStatus, jQxhr ) {");
				r.l("					");
				r.l("				},");
				r.l("				error: function( jqXhr, textStatus, errorThrown ) {");
				r.l("					");
				r.l("				},");
				r.l("				data: {\"setTri10\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageTri10()), "</span>");
				r.s("			<input");
							r.s(" name=\"tri10\"");
							r.s(" value=\"", htmTri10(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmTri10());
			}
			r.l("</div>");
		}
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
		designInscriptionCleInit();
		htmlLienInit();
		htmlElementInit();
		htmlIdInit();
		htmlClassesInit();
		htmlStyleInit();
		htmlAvantInit();
		htmlVarInit();
		htmlApresInit();
		htmlTexteInit();
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
			case "designInscriptionCle":
				return oPartHtml.designInscriptionCle;
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
			case "htmlVar":
				return oPartHtml.htmlVar;
			case "htmlApres":
				return oPartHtml.htmlApres;
			case "htmlTexte":
				return oPartHtml.htmlTexte;
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
			case "designInscriptionCle":
				oPartHtml.setDesignInscriptionCle((Long)val);
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
			case "htmlVar":
				setHtmlVar(val);
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

			Long designInscriptionCle = (Long)solrDocument.get("designInscriptionCle_stored_long");
			if(designInscriptionCle != null)
				oPartHtml.setDesignInscriptionCle(designInscriptionCle);

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

			if(sauvegardesPartHtml.contains("htmlVar")) {
				String htmlVar = (String)solrDocument.get("htmlVar_stored_string");
				if(htmlVar != null)
					oPartHtml.setHtmlVar(htmlVar);
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
		if(designInscriptionCle != null) {
			document.addField("designInscriptionCle_indexed_long", designInscriptionCle);
			document.addField("designInscriptionCle_stored_long", designInscriptionCle);
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
			document.addField("htmlAvant_indexed_string", htmlAvant);
			document.addField("htmlAvant_stored_string", htmlAvant);
		}
		if(htmlVar != null) {
			document.addField("htmlVar_indexed_string", htmlVar);
			document.addField("htmlVar_stored_string", htmlVar);
		}
		if(htmlApres != null) {
			document.addField("htmlApres_indexed_string", htmlApres);
			document.addField("htmlApres_stored_string", htmlApres);
		}
		if(htmlTexte != null) {
			document.addField("htmlTexte_indexed_string", htmlTexte);
			document.addField("htmlTexte_stored_string", htmlTexte);
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

		Long designInscriptionCle = (Long)solrDocument.get("designInscriptionCle_stored_long");
		if(designInscriptionCle != null)
			oPartHtml.setDesignInscriptionCle(designInscriptionCle);

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

		String htmlVar = (String)solrDocument.get("htmlVar_stored_string");
		if(htmlVar != null)
			oPartHtml.setHtmlVar(htmlVar);

		String htmlApres = (String)solrDocument.get("htmlApres_stored_string");
		if(htmlApres != null)
			oPartHtml.setHtmlApres(htmlApres);

		String htmlTexte = (String)solrDocument.get("htmlTexte_stored_string");
		if(htmlTexte != null)
			oPartHtml.setHtmlTexte(htmlTexte);

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

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), designInscriptionCle, htmlLien, htmlElement, htmlId, htmlClasses, htmlStyle, htmlAvant, htmlVar, htmlApres, htmlTexte, tri1, tri2, tri3, tri4, tri5, tri6, tri7, tri8, tri9, tri10);
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
				&& Objects.equals( designInscriptionCle, that.designInscriptionCle )
				&& Objects.equals( htmlLien, that.htmlLien )
				&& Objects.equals( htmlElement, that.htmlElement )
				&& Objects.equals( htmlId, that.htmlId )
				&& Objects.equals( htmlClasses, that.htmlClasses )
				&& Objects.equals( htmlStyle, that.htmlStyle )
				&& Objects.equals( htmlAvant, that.htmlAvant )
				&& Objects.equals( htmlVar, that.htmlVar )
				&& Objects.equals( htmlApres, that.htmlApres )
				&& Objects.equals( htmlTexte, that.htmlTexte )
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
		sb.append( "designInscriptionCle: " ).append(designInscriptionCle);
		sb.append( ", htmlLien: \"" ).append(htmlLien).append( "\"" );
		sb.append( ", htmlElement: \"" ).append(htmlElement).append( "\"" );
		sb.append( ", htmlId: \"" ).append(htmlId).append( "\"" );
		sb.append( ", htmlClasses: \"" ).append(htmlClasses).append( "\"" );
		sb.append( ", htmlStyle: \"" ).append(htmlStyle).append( "\"" );
		sb.append( ", htmlAvant: \"" ).append(htmlAvant).append( "\"" );
		sb.append( ", htmlVar: \"" ).append(htmlVar).append( "\"" );
		sb.append( ", htmlApres: \"" ).append(htmlApres).append( "\"" );
		sb.append( ", htmlTexte: \"" ).append(htmlTexte).append( "\"" );
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
