package org.computate.scolaire.frFR.form.part;

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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true">Trouver la classe htmlAfter dans Solr</a>
 * <br/>
 **/
public abstract class PartFormGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(PartForm.class);

	public static final String PartForm_UnNom = "un part de formulaire";
	public static final String PartForm_Ce = "ce ";
	public static final String PartForm_CeNom = "ce part de formulaire";
	public static final String PartForm_Un = "un ";
	public static final String PartForm_LeNom = "le part de formulaire";
	public static final String PartForm_NomSingulier = "part de formulaire";
	public static final String PartForm_NomPluriel = "part de formulaires";
	public static final String PartForm_NomActuel = "part de formulaire actuel";
	public static final String PartForm_Tous = "all ";
	public static final String PartForm_TousNom = "tous les part de formulaires";
	public static final String PartForm_RechercherTousNomPar = "rechercher part de formulaires par ";
	public static final String PartForm_RechercherTousNom = "rechercher part de formulaires";
	public static final String PartForm_LesNoms = "les part de formulaires";
	public static final String PartForm_AucunNomTrouve = "aucun part de formulaire trouvé";
	public static final String PartForm_NomVar = "partDeFormulaire";
	public static final String PartForm_DeNom = "de part de formulaire";
	public static final String PartForm_NomAdjectifSingulier = "part de formulaire";
	public static final String PartForm_NomAdjectifPluriel = "part de formulaires";
	public static final String PartForm_Couleur = "yellow";
	public static final String PartForm_IconeGroupe = "regular";
	public static final String PartForm_IconeNom = "sun";

	///////////////
	// saisonCle //
	///////////////

	/**	L'entité « saisonCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long saisonCle;
	@JsonIgnore
	public Couverture<Long> saisonCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("saisonCle").o(saisonCle);

	/**	<br/>L'entité « saisonCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:saisonCle">Trouver l'entité saisonCle dans Solr</a>
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
	public PartForm setSaisonCle(String o) {
		if(NumberUtils.isParsable(o))
			this.saisonCle = Long.parseLong(o);
		this.saisonCleCouverture.dejaInitialise = true;
		return (PartForm)this;
	}
	protected PartForm saisonCleInit() {
		if(!saisonCleCouverture.dejaInitialise) {
			_saisonCle(saisonCleCouverture);
			if(saisonCle == null)
				setSaisonCle(saisonCleCouverture.o);
		}
		saisonCleCouverture.dejaInitialise(true);
		return (PartForm)this;
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

	public void htmSaisonCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartForm", strPk(), "SaisonCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartForm", strPk(), "SaisonCle() {");
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
				r.l("				data: {\"setSaisonCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSaisonCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"saisonCle\"");
							r.s(" value=\"", htmSaisonCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSaisonCle());
			}
			r.l("</div>");
		}
	}

	//////////////
	// anneeCle //
	//////////////

	/**	L'entité « anneeCle »
	 *	 is defined as null before being initialized. 
	 */
	protected Long anneeCle;
	@JsonIgnore
	public Couverture<Long> anneeCleCouverture = new Couverture<Long>().p(this).c(Long.class).var("anneeCle").o(anneeCle);

	/**	<br/>L'entité « anneeCle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:anneeCle">Trouver l'entité anneeCle dans Solr</a>
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
	public PartForm setAnneeCle(String o) {
		if(NumberUtils.isParsable(o))
			this.anneeCle = Long.parseLong(o);
		this.anneeCleCouverture.dejaInitialise = true;
		return (PartForm)this;
	}
	protected PartForm anneeCleInit() {
		if(!anneeCleCouverture.dejaInitialise) {
			_anneeCle(anneeCleCouverture);
			if(anneeCle == null)
				setAnneeCle(anneeCleCouverture.o);
		}
		anneeCleCouverture.dejaInitialise(true);
		return (PartForm)this;
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
		return "formulaire d'inscription";
	}

	public String htmTooltipAnneeCle() {
		return null;
	}

	public String htmAnneeCle() {
		return anneeCle == null ? "" : StringEscapeUtils.escapeHtml4(strAnneeCle());
	}

	public void htmAnneeCle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartForm", strPk(), "AnneeCle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartForm", strPk(), "AnneeCle() {");
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
				r.l("				data: {\"setAnneeCle\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageAnneeCle()), "</span>");
				r.s("			<input");
							r.s(" name=\"anneeCle\"");
							r.s(" value=\"", htmAnneeCle(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmAnneeCle());
			}
			r.l("</div>");
		}
	}

	///////////////
	// htmlOrdre //
	///////////////

	/**	L'entité « htmlOrdre »
	 *	 is defined as null before being initialized. 
	 */
	protected Double htmlOrdre;
	@JsonIgnore
	public Couverture<Double> htmlOrdreCouverture = new Couverture<Double>().p(this).c(Double.class).var("htmlOrdre").o(htmlOrdre);

	/**	<br/>L'entité « htmlOrdre »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlOrdre">Trouver l'entité htmlOrdre dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlOrdre(Couverture<Double> c);

	public Double getHtmlOrdre() {
		return htmlOrdre;
	}

	public void setHtmlOrdre(Double htmlOrdre) {
		this.htmlOrdre = htmlOrdre;
		this.htmlOrdreCouverture.dejaInitialise = true;
	}
	public PartForm setHtmlOrdre(String o) {
		if(NumberUtils.isParsable(o))
			this.htmlOrdre = Double.parseDouble(o);
		this.htmlOrdreCouverture.dejaInitialise = true;
		return (PartForm)this;
	}
	protected PartForm htmlOrdreInit() {
		if(!htmlOrdreCouverture.dejaInitialise) {
			_htmlOrdre(htmlOrdreCouverture);
			if(htmlOrdre == null)
				setHtmlOrdre(htmlOrdreCouverture.o);
		}
		htmlOrdreCouverture.dejaInitialise(true);
		return (PartForm)this;
	}

	public Double solrHtmlOrdre() {
		return htmlOrdre;
	}

	public String strHtmlOrdre() {
		return htmlOrdre == null ? "" : htmlOrdre.toString();
	}

	public String jsonHtmlOrdre() {
		return htmlOrdre == null ? "" : htmlOrdre.toString();
	}

	public String nomAffichageHtmlOrdre() {
		return "order";
	}

	public String htmTooltipHtmlOrdre() {
		return null;
	}

	public String htmHtmlOrdre() {
		return htmlOrdre == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlOrdre());
	}

	public void htmHtmlOrdre(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartForm", strPk(), "HtmlOrdre\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartForm", strPk(), "HtmlOrdre() {");
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
				r.l("				data: {\"setHtmlOrdre\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlOrdre()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlOrdre\"");
							r.s(" value=\"", htmHtmlOrdre(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlOrdre());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlLien">Trouver l'entité htmlLien dans Solr</a>
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
	protected PartForm htmlLienInit() {
		if(!htmlLienCouverture.dejaInitialise) {
			_htmlLien(htmlLienCouverture);
			if(htmlLien == null)
				setHtmlLien(htmlLienCouverture.o);
		}
		htmlLienCouverture.dejaInitialise(true);
		return (PartForm)this;
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
		return "HTML after";
	}

	public String htmTooltipHtmlLien() {
		return null;
	}

	public String htmHtmlLien() {
		return htmlLien == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlLien());
	}

	public void htmHtmlLien(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartForm", strPk(), "HtmlLien\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartForm", strPk(), "HtmlLien() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlElement">Trouver l'entité htmlElement dans Solr</a>
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
	protected PartForm htmlElementInit() {
		if(!htmlElementCouverture.dejaInitialise) {
			_htmlElement(htmlElementCouverture);
			if(htmlElement == null)
				setHtmlElement(htmlElementCouverture.o);
		}
		htmlElementCouverture.dejaInitialise(true);
		return (PartForm)this;
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
			r.s("<div id=\"patchPartForm", strPk(), "HtmlElement\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartForm", strPk(), "HtmlElement() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlId">Trouver l'entité htmlId dans Solr</a>
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
	protected PartForm htmlIdInit() {
		if(!htmlIdCouverture.dejaInitialise) {
			_htmlId(htmlIdCouverture);
			if(htmlId == null)
				setHtmlId(htmlIdCouverture.o);
		}
		htmlIdCouverture.dejaInitialise(true);
		return (PartForm)this;
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
			r.s("<div id=\"patchPartForm", strPk(), "HtmlId\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartForm", strPk(), "HtmlId() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlClasses">Trouver l'entité htmlClasses dans Solr</a>
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
	protected PartForm htmlClassesInit() {
		if(!htmlClassesCouverture.dejaInitialise) {
			_htmlClasses(htmlClassesCouverture);
			if(htmlClasses == null)
				setHtmlClasses(htmlClassesCouverture.o);
		}
		htmlClassesCouverture.dejaInitialise(true);
		return (PartForm)this;
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
			r.s("<div id=\"patchPartForm", strPk(), "HtmlClasses\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartForm", strPk(), "HtmlClasses() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlStyle">Trouver l'entité htmlStyle dans Solr</a>
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
	protected PartForm htmlStyleInit() {
		if(!htmlStyleCouverture.dejaInitialise) {
			_htmlStyle(htmlStyleCouverture);
			if(htmlStyle == null)
				setHtmlStyle(htmlStyleCouverture.o);
		}
		htmlStyleCouverture.dejaInitialise(true);
		return (PartForm)this;
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
		return "HTML élément";
	}

	public String htmTooltipHtmlStyle() {
		return null;
	}

	public String htmHtmlStyle() {
		return htmlStyle == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlStyle());
	}

	public void htmHtmlStyle(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartForm", strPk(), "HtmlStyle\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartForm", strPk(), "HtmlStyle() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlAvant">Trouver l'entité htmlAvant dans Solr</a>
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
	protected PartForm htmlAvantInit() {
		if(!htmlAvantCouverture.dejaInitialise) {
			_htmlAvant(htmlAvantCouverture);
			if(htmlAvant == null)
				setHtmlAvant(htmlAvantCouverture.o);
		}
		htmlAvantCouverture.dejaInitialise(true);
		return (PartForm)this;
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
		return "HTML before";
	}

	public String htmTooltipHtmlAvant() {
		return null;
	}

	public String htmHtmlAvant() {
		return htmlAvant == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlAvant());
	}

	public void htmHtmlAvant(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartForm", strPk(), "HtmlAvant\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartForm", strPk(), "HtmlAvant() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlTexte">Trouver l'entité htmlTexte dans Solr</a>
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
	protected PartForm htmlTexteInit() {
		if(!htmlTexteCouverture.dejaInitialise) {
			_htmlTexte(htmlTexteCouverture);
			if(htmlTexte == null)
				setHtmlTexte(htmlTexteCouverture.o);
		}
		htmlTexteCouverture.dejaInitialise(true);
		return (PartForm)this;
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
		return "text";
	}

	public String htmTooltipHtmlTexte() {
		return null;
	}

	public String htmHtmlTexte() {
		return htmlTexte == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlTexte());
	}

	public void htmHtmlTexte(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartForm", strPk(), "HtmlTexte\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartForm", strPk(), "HtmlTexte() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.form.part.PartForm&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:htmlApres">Trouver l'entité htmlApres dans Solr</a>
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
	protected PartForm htmlApresInit() {
		if(!htmlApresCouverture.dejaInitialise) {
			_htmlApres(htmlApresCouverture);
			if(htmlApres == null)
				setHtmlApres(htmlApresCouverture.o);
		}
		htmlApresCouverture.dejaInitialise(true);
		return (PartForm)this;
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
		return "HTML after";
	}

	public String htmTooltipHtmlApres() {
		return null;
	}

	public String htmHtmlApres() {
		return htmlApres == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlApres());
	}

	public void htmHtmlApres(ToutEcrivain r, Boolean patchDroits) {
		if(pk!= null) {
			r.s("<div id=\"patchPartForm", strPk(), "HtmlApres\">");
			if(patchDroits) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchPartForm", strPk(), "HtmlApres() {");
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

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialisePartForm = false;

	public PartForm initLoinPartForm(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialisePartForm) {
			dejaInitialisePartForm = true;
			initLoinPartForm();
		}
		return (PartForm)this;
	}

	public void initLoinPartForm() {
		super.initLoinCluster(requeteSite_);
		initPartForm();
	}

	public void initPartForm() {
		saisonCleInit();
		anneeCleInit();
		htmlOrdreInit();
		htmlLienInit();
		htmlElementInit();
		htmlIdInit();
		htmlClassesInit();
		htmlStyleInit();
		htmlAvantInit();
		htmlTexteInit();
		htmlApresInit();
	}

	@Override public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinPartForm(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSitePartForm(RequeteSiteFrFR requeteSite_) {
			super.requeteSiteCluster(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSitePartForm(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirPartForm(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirPartForm(String var) {
		PartForm oPartForm = (PartForm)this;
		switch(var) {
			case "saisonCle":
				return oPartForm.saisonCle;
			case "anneeCle":
				return oPartForm.anneeCle;
			case "htmlOrdre":
				return oPartForm.htmlOrdre;
			case "htmlLien":
				return oPartForm.htmlLien;
			case "htmlElement":
				return oPartForm.htmlElement;
			case "htmlId":
				return oPartForm.htmlId;
			case "htmlClasses":
				return oPartForm.htmlClasses;
			case "htmlStyle":
				return oPartForm.htmlStyle;
			case "htmlAvant":
				return oPartForm.htmlAvant;
			case "htmlTexte":
				return oPartForm.htmlTexte;
			case "htmlApres":
				return oPartForm.htmlApres;
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
				o = attribuerPartForm(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerPartForm(String var, Object val) {
		PartForm oPartForm = (PartForm)this;
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
					o = definirPartForm(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPartForm(String var, String val) {
		switch(var) {
			case "htmlOrdre":
				setHtmlOrdre(val);
				sauvegardesPartForm.add(var);
				return val;
			case "htmlLien":
				setHtmlLien(val);
				sauvegardesPartForm.add(var);
				return val;
			case "htmlElement":
				setHtmlElement(val);
				sauvegardesPartForm.add(var);
				return val;
			case "htmlId":
				setHtmlId(val);
				sauvegardesPartForm.add(var);
				return val;
			case "htmlClasses":
				setHtmlClasses(val);
				sauvegardesPartForm.add(var);
				return val;
			case "htmlStyle":
				setHtmlStyle(val);
				sauvegardesPartForm.add(var);
				return val;
			case "htmlAvant":
				setHtmlAvant(val);
				sauvegardesPartForm.add(var);
				return val;
			case "htmlTexte":
				setHtmlTexte(val);
				sauvegardesPartForm.add(var);
				return val;
			case "htmlApres":
				setHtmlApres(val);
				sauvegardesPartForm.add(var);
				return val;
			default:
				return super.definirCluster(var, val);
		}
	}

	/////////////////
	// sauvegardes //
	/////////////////

	protected List<String> sauvegardesPartForm = new ArrayList<String>();

	/////////////
	// peupler //
	/////////////

	@Override public void peuplerPourClasse(SolrDocument solrDocument) {
		peuplerPartForm(solrDocument);
	}
	public void peuplerPartForm(SolrDocument solrDocument) {
		PartForm oPartForm = (PartForm)this;
		sauvegardesPartForm = (List<String>)solrDocument.get("sauvegardesPartForm_stored_strings");
		if(sauvegardesPartForm != null) {

			if(sauvegardesPartForm.contains("saisonCle")) {
				Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
				if(saisonCle != null)
					oPartForm.setSaisonCle(saisonCle);
			}

			if(sauvegardesPartForm.contains("anneeCle")) {
				Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
				if(anneeCle != null)
					oPartForm.setAnneeCle(anneeCle);
			}

			if(sauvegardesPartForm.contains("htmlOrdre")) {
				Double htmlOrdre = (Double)solrDocument.get("htmlOrdre_stored_double");
				if(htmlOrdre != null)
					oPartForm.setHtmlOrdre(htmlOrdre);
			}

			if(sauvegardesPartForm.contains("htmlLien")) {
				String htmlLien = (String)solrDocument.get("htmlLien_stored_string");
				if(htmlLien != null)
					oPartForm.setHtmlLien(htmlLien);
			}

			if(sauvegardesPartForm.contains("htmlElement")) {
				String htmlElement = (String)solrDocument.get("htmlElement_stored_string");
				if(htmlElement != null)
					oPartForm.setHtmlElement(htmlElement);
			}

			if(sauvegardesPartForm.contains("htmlId")) {
				String htmlId = (String)solrDocument.get("htmlId_stored_string");
				if(htmlId != null)
					oPartForm.setHtmlId(htmlId);
			}

			if(sauvegardesPartForm.contains("htmlClasses")) {
				String htmlClasses = (String)solrDocument.get("htmlClasses_stored_string");
				if(htmlClasses != null)
					oPartForm.setHtmlClasses(htmlClasses);
			}

			if(sauvegardesPartForm.contains("htmlStyle")) {
				String htmlStyle = (String)solrDocument.get("htmlStyle_stored_string");
				if(htmlStyle != null)
					oPartForm.setHtmlStyle(htmlStyle);
			}

			if(sauvegardesPartForm.contains("htmlAvant")) {
				String htmlAvant = (String)solrDocument.get("htmlAvant_stored_string");
				if(htmlAvant != null)
					oPartForm.setHtmlAvant(htmlAvant);
			}

			if(sauvegardesPartForm.contains("htmlTexte")) {
				String htmlTexte = (String)solrDocument.get("htmlTexte_stored_string");
				if(htmlTexte != null)
					oPartForm.setHtmlTexte(htmlTexte);
			}

			if(sauvegardesPartForm.contains("htmlApres")) {
				String htmlApres = (String)solrDocument.get("htmlApres_stored_string");
				if(htmlApres != null)
					oPartForm.setHtmlApres(htmlApres);
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
			rechercheSolr.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.form.part.PartForm"));
			QueryResponse reponseRecherche = requeteSite.getSiteContexte_().getClientSolr().query(rechercheSolr);
			if(reponseRecherche.getResults().size() > 0)
				requeteSite.setDocumentSolr(reponseRecherche.getResults().get(0));
			PartForm o = new PartForm();
			o.requeteSitePartForm(requeteSite);
			o.initLoinPartForm(requeteSite);
			o.indexerPartForm();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexerPourClasse() {
		indexerPartForm();
	}

	@Override public void indexerPourClasse(SolrInputDocument document) {
		indexerPartForm(document);
	}

	public void indexerPartForm(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerPartForm(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerPartForm() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexerPartForm(document);
			SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexerPartForm(SolrInputDocument document) {
		if(sauvegardesPartForm != null)
			document.addField("sauvegardesPartForm_stored_strings", sauvegardesPartForm);

		if(saisonCle != null) {
			document.addField("saisonCle_indexed_long", saisonCle);
			document.addField("saisonCle_stored_long", saisonCle);
		}
		if(anneeCle != null) {
			document.addField("anneeCle_indexed_long", anneeCle);
			document.addField("anneeCle_stored_long", anneeCle);
		}
		if(htmlOrdre != null) {
			document.addField("htmlOrdre_stored_double", htmlOrdre);
		}
		if(htmlLien != null) {
			document.addField("htmlLien_stored_string", htmlLien);
		}
		if(htmlElement != null) {
			document.addField("htmlElement_stored_string", htmlElement);
		}
		if(htmlId != null) {
			document.addField("htmlId_stored_string", htmlId);
		}
		if(htmlClasses != null) {
			document.addField("htmlClasses_stored_string", htmlClasses);
		}
		if(htmlStyle != null) {
			document.addField("htmlStyle_stored_string", htmlStyle);
		}
		if(htmlAvant != null) {
			document.addField("htmlAvant_stored_string", htmlAvant);
		}
		if(htmlTexte != null) {
			document.addField("htmlTexte_stored_string", htmlTexte);
		}
		if(htmlApres != null) {
			document.addField("htmlApres_stored_string", htmlApres);
		}
		super.indexerCluster(document);

	}

	public void desindexerPartForm() {
		try {
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
			requeteSite.initLoinRequeteSiteFrFR();
			SiteContexteFrFR siteContexte = new SiteContexteFrFR();
			siteContexte.initLoinSiteContexteFrFR();
			requeteSite.setSiteContexte_(siteContexte);
			requeteSite.setConfigSite_(siteContexte.getConfigSite());
			initLoinPartForm(requeteSite);
			SolrClient clientSolr = siteContexte.getClientSolr();
			clientSolr.deleteById(id.toString());
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	/////////////
	// stocker //
	/////////////

	@Override public void stockerPourClasse(SolrDocument solrDocument) {
		stockerPartForm(solrDocument);
	}
	public void stockerPartForm(SolrDocument solrDocument) {
		PartForm oPartForm = (PartForm)this;

		Long saisonCle = (Long)solrDocument.get("saisonCle_stored_long");
		if(saisonCle != null)
			oPartForm.setSaisonCle(saisonCle);

		Long anneeCle = (Long)solrDocument.get("anneeCle_stored_long");
		if(anneeCle != null)
			oPartForm.setAnneeCle(anneeCle);

		Double htmlOrdre = (Double)solrDocument.get("htmlOrdre_stored_double");
		if(htmlOrdre != null)
			oPartForm.setHtmlOrdre(htmlOrdre);

		String htmlLien = (String)solrDocument.get("htmlLien_stored_string");
		if(htmlLien != null)
			oPartForm.setHtmlLien(htmlLien);

		String htmlElement = (String)solrDocument.get("htmlElement_stored_string");
		if(htmlElement != null)
			oPartForm.setHtmlElement(htmlElement);

		String htmlId = (String)solrDocument.get("htmlId_stored_string");
		if(htmlId != null)
			oPartForm.setHtmlId(htmlId);

		String htmlClasses = (String)solrDocument.get("htmlClasses_stored_string");
		if(htmlClasses != null)
			oPartForm.setHtmlClasses(htmlClasses);

		String htmlStyle = (String)solrDocument.get("htmlStyle_stored_string");
		if(htmlStyle != null)
			oPartForm.setHtmlStyle(htmlStyle);

		String htmlAvant = (String)solrDocument.get("htmlAvant_stored_string");
		if(htmlAvant != null)
			oPartForm.setHtmlAvant(htmlAvant);

		String htmlTexte = (String)solrDocument.get("htmlTexte_stored_string");
		if(htmlTexte != null)
			oPartForm.setHtmlTexte(htmlTexte);

		String htmlApres = (String)solrDocument.get("htmlApres_stored_string");
		if(htmlApres != null)
			oPartForm.setHtmlApres(htmlApres);

		super.stockerCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), htmlOrdre, htmlLien, htmlElement, htmlId, htmlClasses, htmlStyle, htmlAvant, htmlTexte, htmlApres);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof PartForm))
			return false;
		PartForm that = (PartForm)o;
		return super.equals(o)
				&& Objects.equals( htmlOrdre, that.htmlOrdre )
				&& Objects.equals( htmlLien, that.htmlLien )
				&& Objects.equals( htmlElement, that.htmlElement )
				&& Objects.equals( htmlId, that.htmlId )
				&& Objects.equals( htmlClasses, that.htmlClasses )
				&& Objects.equals( htmlStyle, that.htmlStyle )
				&& Objects.equals( htmlAvant, that.htmlAvant )
				&& Objects.equals( htmlTexte, that.htmlTexte )
				&& Objects.equals( htmlApres, that.htmlApres );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PartForm { ");
		sb.append( "htmlOrdre: " ).append(htmlOrdre);
		sb.append( ", htmlLien: \"" ).append(htmlLien).append( "\"" );
		sb.append( ", htmlElement: \"" ).append(htmlElement).append( "\"" );
		sb.append( ", htmlId: \"" ).append(htmlId).append( "\"" );
		sb.append( ", htmlClasses: \"" ).append(htmlClasses).append( "\"" );
		sb.append( ", htmlStyle: \"" ).append(htmlStyle).append( "\"" );
		sb.append( ", htmlAvant: \"" ).append(htmlAvant).append( "\"" );
		sb.append( ", htmlTexte: \"" ).append(htmlTexte).append( "\"" );
		sb.append( ", htmlApres: \"" ).append(htmlApres).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
