package org.computate.scolaire.enUS.form.part;

import java.lang.Double;
import java.util.Date;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.lang.Long;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class FormPartGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(FormPart.class);

	public static final String FormPart_UnNom = "a form part";
	public static final String FormPart_Ce = "this ";
	public static final String FormPart_CeNom = "this form part";
	public static final String FormPart_Un = "a ";
	public static final String FormPart_LeNom = "the form part";
	public static final String FormPart_NomSingulier = "form part";
	public static final String FormPart_NomPluriel = "form parts";
	public static final String FormPart_NomActuel = "current form part";
	public static final String FormPart_TousNom = "all the form parts";
	public static final String FormPart_RechercherTousNomPar = "search form parts by ";
	public static final String FormPart_LesNoms = "the form parts";
	public static final String FormPart_AucunNomTrouve = "no form part found";
	public static final String FormPart_NomVar = "formPart";
	public static final String FormPart_DeNom = "of form part";
	public static final String FormPart_UnNomAdjectif = "a form part";
	public static final String FormPart_NomAdjectifSingulier = "form part";
	public static final String FormPart_NomAdjectifPluriel = "form parts";
	public static final String FormPart_Couleur = "yellow";
	public static final String FormPart_IconeGroupe = "regular";
	public static final String FormPart_IconeNom = "sun";

	/////////////////
	// formPartKey //
	/////////////////

	/**	L'entité « formPartKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long formPartKey;
	@JsonIgnore
	public Wrap<Long> formPartKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("formPartKey").o(formPartKey);

	/**	<br/>L'entité « formPartKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:formPartKey">Trouver l'entité formPartKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _formPartKey(Wrap<Long> c);

	public Long getFormPartKey() {
		return formPartKey;
	}

	public void setFormPartKey(Long formPartKey) {
		this.formPartKey = formPartKey;
		this.formPartKeyWrap.alreadyInitialized = true;
	}
	public FormPart setFormPartKey(String o) {
		if(NumberUtils.isParsable(o))
			this.formPartKey = Long.parseLong(o);
		this.formPartKeyWrap.alreadyInitialized = true;
		return (FormPart)this;
	}
	protected FormPart formPartKeyInit() {
		if(!formPartKeyWrap.alreadyInitialized) {
			_formPartKey(formPartKeyWrap);
			if(formPartKey == null)
				setFormPartKey(formPartKeyWrap.o);
		}
		formPartKeyWrap.alreadyInitialized(true);
		return (FormPart)this;
	}

	public Long solrFormPartKey() {
		return formPartKey;
	}

	public String strFormPartKey() {
		return formPartKey == null ? "" : formPartKey.toString();
	}

	public String jsonFormPartKey() {
		return formPartKey == null ? "" : formPartKey.toString();
	}

	public String nomAffichageFormPartKey() {
		return "key";
	}

	public String htmTooltipFormPartKey() {
		return null;
	}

	public String htmFormPartKey() {
		return formPartKey == null ? "" : StringEscapeUtils.escapeHtml4(strFormPartKey());
	}

	public void htmFormPartKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchFormPart", strPk(), "FormPartKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchFormPart", strPk(), "FormPartKey() {");
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
				r.l("				data: {\"setFormPartKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageFormPartKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"formPartKey\"");
							r.s(" value=\"", htmFormPartKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmFormPartKey());
			}
			r.l("</div>");
		}
	}

	///////////////////////
	// enrollmentFormKey //
	///////////////////////

	/**	L'entité « enrollmentFormKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long enrollmentFormKey;
	@JsonIgnore
	public Wrap<Long> enrollmentFormKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("enrollmentFormKey").o(enrollmentFormKey);

	/**	<br/>L'entité « enrollmentFormKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentFormKey">Trouver l'entité enrollmentFormKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentFormKey(Wrap<Long> c);

	public Long getEnrollmentFormKey() {
		return enrollmentFormKey;
	}

	public void setEnrollmentFormKey(Long enrollmentFormKey) {
		this.enrollmentFormKey = enrollmentFormKey;
		this.enrollmentFormKeyWrap.alreadyInitialized = true;
	}
	public FormPart setEnrollmentFormKey(String o) {
		if(NumberUtils.isParsable(o))
			this.enrollmentFormKey = Long.parseLong(o);
		this.enrollmentFormKeyWrap.alreadyInitialized = true;
		return (FormPart)this;
	}
	protected FormPart enrollmentFormKeyInit() {
		if(!enrollmentFormKeyWrap.alreadyInitialized) {
			_enrollmentFormKey(enrollmentFormKeyWrap);
			if(enrollmentFormKey == null)
				setEnrollmentFormKey(enrollmentFormKeyWrap.o);
		}
		enrollmentFormKeyWrap.alreadyInitialized(true);
		return (FormPart)this;
	}

	public Long solrEnrollmentFormKey() {
		return enrollmentFormKey;
	}

	public String strEnrollmentFormKey() {
		return enrollmentFormKey == null ? "" : enrollmentFormKey.toString();
	}

	public String jsonEnrollmentFormKey() {
		return enrollmentFormKey == null ? "" : enrollmentFormKey.toString();
	}

	public String nomAffichageEnrollmentFormKey() {
		return "enrollment form";
	}

	public String htmTooltipEnrollmentFormKey() {
		return null;
	}

	public String htmEnrollmentFormKey() {
		return enrollmentFormKey == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentFormKey());
	}

	public void htmEnrollmentFormKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchFormPart", strPk(), "EnrollmentFormKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchFormPart", strPk(), "EnrollmentFormKey() {");
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
				r.l("				data: {\"setEnrollmentFormKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentFormKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentFormKey\"");
							r.s(" value=\"", htmEnrollmentFormKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentFormKey());
			}
			r.l("</div>");
		}
	}

	///////////////
	// htmlOrder //
	///////////////

	/**	L'entité « htmlOrder »
	 *	 is defined as null before being initialized. 
	 */
	protected Double htmlOrder;
	@JsonIgnore
	public Wrap<Double> htmlOrderWrap = new Wrap<Double>().p(this).c(Double.class).var("htmlOrder").o(htmlOrder);

	/**	<br/>L'entité « htmlOrder »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlOrder">Trouver l'entité htmlOrder dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlOrder(Wrap<Double> c);

	public Double getHtmlOrder() {
		return htmlOrder;
	}

	public void setHtmlOrder(Double htmlOrder) {
		this.htmlOrder = htmlOrder;
		this.htmlOrderWrap.alreadyInitialized = true;
	}
	public FormPart setHtmlOrder(String o) {
		if(NumberUtils.isParsable(o))
			this.htmlOrder = Double.parseDouble(o);
		this.htmlOrderWrap.alreadyInitialized = true;
		return (FormPart)this;
	}
	protected FormPart htmlOrderInit() {
		if(!htmlOrderWrap.alreadyInitialized) {
			_htmlOrder(htmlOrderWrap);
			if(htmlOrder == null)
				setHtmlOrder(htmlOrderWrap.o);
		}
		htmlOrderWrap.alreadyInitialized(true);
		return (FormPart)this;
	}

	public Double solrHtmlOrder() {
		return htmlOrder;
	}

	public String strHtmlOrder() {
		return htmlOrder == null ? "" : htmlOrder.toString();
	}

	public String jsonHtmlOrder() {
		return htmlOrder == null ? "" : htmlOrder.toString();
	}

	public String nomAffichageHtmlOrder() {
		return "ordre";
	}

	public String htmTooltipHtmlOrder() {
		return null;
	}

	public String htmHtmlOrder() {
		return htmlOrder == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlOrder());
	}

	public void htmHtmlOrder(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchFormPart", strPk(), "HtmlOrder\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchFormPart", strPk(), "HtmlOrder() {");
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
				r.l("				data: {\"setHtmlOrder\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlOrder()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlOrder\"");
							r.s(" value=\"", htmHtmlOrder(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlOrder());
			}
			r.l("</div>");
		}
	}

	//////////////
	// htmlLink //
	//////////////

	/**	L'entité « htmlLink »
	 *	 is defined as null before being initialized. 
	 */
	protected String htmlLink;
	@JsonIgnore
	public Wrap<String> htmlLinkWrap = new Wrap<String>().p(this).c(String.class).var("htmlLink").o(htmlLink);

	/**	<br/>L'entité « htmlLink »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlLink">Trouver l'entité htmlLink dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlLink(Wrap<String> c);

	public String getHtmlLink() {
		return htmlLink;
	}

	public void setHtmlLink(String htmlLink) {
		this.htmlLink = htmlLink;
		this.htmlLinkWrap.alreadyInitialized = true;
	}
	protected FormPart htmlLinkInit() {
		if(!htmlLinkWrap.alreadyInitialized) {
			_htmlLink(htmlLinkWrap);
			if(htmlLink == null)
				setHtmlLink(htmlLinkWrap.o);
		}
		htmlLinkWrap.alreadyInitialized(true);
		return (FormPart)this;
	}

	public String solrHtmlLink() {
		return htmlLink;
	}

	public String strHtmlLink() {
		return htmlLink == null ? "" : htmlLink;
	}

	public String jsonHtmlLink() {
		return htmlLink == null ? "" : htmlLink;
	}

	public String nomAffichageHtmlLink() {
		return "HTML après";
	}

	public String htmTooltipHtmlLink() {
		return null;
	}

	public String htmHtmlLink() {
		return htmlLink == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlLink());
	}

	public void htmHtmlLink(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchFormPart", strPk(), "HtmlLink\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchFormPart", strPk(), "HtmlLink() {");
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
				r.l("				data: {\"setHtmlLink\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlLink()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlLink\"");
							r.s(" value=\"", htmHtmlLink(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlLink());
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
	public Wrap<String> htmlElementWrap = new Wrap<String>().p(this).c(String.class).var("htmlElement").o(htmlElement);

	/**	<br/>L'entité « htmlElement »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlElement">Trouver l'entité htmlElement dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlElement(Wrap<String> c);

	public String getHtmlElement() {
		return htmlElement;
	}

	public void setHtmlElement(String htmlElement) {
		this.htmlElement = htmlElement;
		this.htmlElementWrap.alreadyInitialized = true;
	}
	protected FormPart htmlElementInit() {
		if(!htmlElementWrap.alreadyInitialized) {
			_htmlElement(htmlElementWrap);
			if(htmlElement == null)
				setHtmlElement(htmlElementWrap.o);
		}
		htmlElementWrap.alreadyInitialized(true);
		return (FormPart)this;
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
		return "HTML element";
	}

	public String htmTooltipHtmlElement() {
		return null;
	}

	public String htmHtmlElement() {
		return htmlElement == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlElement());
	}

	public void htmHtmlElement(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchFormPart", strPk(), "HtmlElement\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchFormPart", strPk(), "HtmlElement() {");
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
	public Wrap<String> htmlIdWrap = new Wrap<String>().p(this).c(String.class).var("htmlId").o(htmlId);

	/**	<br/>L'entité « htmlId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlId">Trouver l'entité htmlId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlId(Wrap<String> c);

	public String getHtmlId() {
		return htmlId;
	}

	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
		this.htmlIdWrap.alreadyInitialized = true;
	}
	protected FormPart htmlIdInit() {
		if(!htmlIdWrap.alreadyInitialized) {
			_htmlId(htmlIdWrap);
			if(htmlId == null)
				setHtmlId(htmlIdWrap.o);
		}
		htmlIdWrap.alreadyInitialized(true);
		return (FormPart)this;
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

	public void htmHtmlId(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchFormPart", strPk(), "HtmlId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchFormPart", strPk(), "HtmlId() {");
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
	public Wrap<String> htmlClassesWrap = new Wrap<String>().p(this).c(String.class).var("htmlClasses").o(htmlClasses);

	/**	<br/>L'entité « htmlClasses »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlClasses">Trouver l'entité htmlClasses dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlClasses(Wrap<String> c);

	public String getHtmlClasses() {
		return htmlClasses;
	}

	public void setHtmlClasses(String htmlClasses) {
		this.htmlClasses = htmlClasses;
		this.htmlClassesWrap.alreadyInitialized = true;
	}
	protected FormPart htmlClassesInit() {
		if(!htmlClassesWrap.alreadyInitialized) {
			_htmlClasses(htmlClassesWrap);
			if(htmlClasses == null)
				setHtmlClasses(htmlClassesWrap.o);
		}
		htmlClassesWrap.alreadyInitialized(true);
		return (FormPart)this;
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

	public void htmHtmlClasses(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchFormPart", strPk(), "HtmlClasses\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchFormPart", strPk(), "HtmlClasses() {");
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
	public Wrap<String> htmlStyleWrap = new Wrap<String>().p(this).c(String.class).var("htmlStyle").o(htmlStyle);

	/**	<br/>L'entité « htmlStyle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlStyle">Trouver l'entité htmlStyle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlStyle(Wrap<String> c);

	public String getHtmlStyle() {
		return htmlStyle;
	}

	public void setHtmlStyle(String htmlStyle) {
		this.htmlStyle = htmlStyle;
		this.htmlStyleWrap.alreadyInitialized = true;
	}
	protected FormPart htmlStyleInit() {
		if(!htmlStyleWrap.alreadyInitialized) {
			_htmlStyle(htmlStyleWrap);
			if(htmlStyle == null)
				setHtmlStyle(htmlStyleWrap.o);
		}
		htmlStyleWrap.alreadyInitialized(true);
		return (FormPart)this;
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
		return "HTML element";
	}

	public String htmTooltipHtmlStyle() {
		return null;
	}

	public String htmHtmlStyle() {
		return htmlStyle == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlStyle());
	}

	public void htmHtmlStyle(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchFormPart", strPk(), "HtmlStyle\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchFormPart", strPk(), "HtmlStyle() {");
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

	////////////////
	// htmlBefore //
	////////////////

	/**	L'entité « htmlBefore »
	 *	 is defined as null before being initialized. 
	 */
	protected String htmlBefore;
	@JsonIgnore
	public Wrap<String> htmlBeforeWrap = new Wrap<String>().p(this).c(String.class).var("htmlBefore").o(htmlBefore);

	/**	<br/>L'entité « htmlBefore »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlBefore">Trouver l'entité htmlBefore dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlBefore(Wrap<String> c);

	public String getHtmlBefore() {
		return htmlBefore;
	}

	public void setHtmlBefore(String htmlBefore) {
		this.htmlBefore = htmlBefore;
		this.htmlBeforeWrap.alreadyInitialized = true;
	}
	protected FormPart htmlBeforeInit() {
		if(!htmlBeforeWrap.alreadyInitialized) {
			_htmlBefore(htmlBeforeWrap);
			if(htmlBefore == null)
				setHtmlBefore(htmlBeforeWrap.o);
		}
		htmlBeforeWrap.alreadyInitialized(true);
		return (FormPart)this;
	}

	public String solrHtmlBefore() {
		return htmlBefore;
	}

	public String strHtmlBefore() {
		return htmlBefore == null ? "" : htmlBefore;
	}

	public String jsonHtmlBefore() {
		return htmlBefore == null ? "" : htmlBefore;
	}

	public String nomAffichageHtmlBefore() {
		return "HTML avant";
	}

	public String htmTooltipHtmlBefore() {
		return null;
	}

	public String htmHtmlBefore() {
		return htmlBefore == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlBefore());
	}

	public void htmHtmlBefore(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchFormPart", strPk(), "HtmlBefore\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchFormPart", strPk(), "HtmlBefore() {");
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
				r.l("				data: {\"setHtmlBefore\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlBefore()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlBefore\"");
							r.s(" value=\"", htmHtmlBefore(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlBefore());
			}
			r.l("</div>");
		}
	}

	//////////////
	// htmlText //
	//////////////

	/**	L'entité « htmlText »
	 *	 is defined as null before being initialized. 
	 */
	protected String htmlText;
	@JsonIgnore
	public Wrap<String> htmlTextWrap = new Wrap<String>().p(this).c(String.class).var("htmlText").o(htmlText);

	/**	<br/>L'entité « htmlText »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlText">Trouver l'entité htmlText dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlText(Wrap<String> c);

	public String getHtmlText() {
		return htmlText;
	}

	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
		this.htmlTextWrap.alreadyInitialized = true;
	}
	protected FormPart htmlTextInit() {
		if(!htmlTextWrap.alreadyInitialized) {
			_htmlText(htmlTextWrap);
			if(htmlText == null)
				setHtmlText(htmlTextWrap.o);
		}
		htmlTextWrap.alreadyInitialized(true);
		return (FormPart)this;
	}

	public String solrHtmlText() {
		return htmlText;
	}

	public String strHtmlText() {
		return htmlText == null ? "" : htmlText;
	}

	public String jsonHtmlText() {
		return htmlText == null ? "" : htmlText;
	}

	public String nomAffichageHtmlText() {
		return "texte";
	}

	public String htmTooltipHtmlText() {
		return null;
	}

	public String htmHtmlText() {
		return htmlText == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlText());
	}

	public void htmHtmlText(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchFormPart", strPk(), "HtmlText\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchFormPart", strPk(), "HtmlText() {");
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
				r.l("				data: {\"setHtmlText\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlText()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlText\"");
							r.s(" value=\"", htmHtmlText(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlText());
			}
			r.l("</div>");
		}
	}

	///////////////
	// htmlAfter //
	///////////////

	/**	L'entité « htmlAfter »
	 *	 is defined as null before being initialized. 
	 */
	protected String htmlAfter;
	@JsonIgnore
	public Wrap<String> htmlAfterWrap = new Wrap<String>().p(this).c(String.class).var("htmlAfter").o(htmlAfter);

	/**	<br/>L'entité « htmlAfter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.form.part.FormPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlAfter">Trouver l'entité htmlAfter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlAfter(Wrap<String> c);

	public String getHtmlAfter() {
		return htmlAfter;
	}

	public void setHtmlAfter(String htmlAfter) {
		this.htmlAfter = htmlAfter;
		this.htmlAfterWrap.alreadyInitialized = true;
	}
	protected FormPart htmlAfterInit() {
		if(!htmlAfterWrap.alreadyInitialized) {
			_htmlAfter(htmlAfterWrap);
			if(htmlAfter == null)
				setHtmlAfter(htmlAfterWrap.o);
		}
		htmlAfterWrap.alreadyInitialized(true);
		return (FormPart)this;
	}

	public String solrHtmlAfter() {
		return htmlAfter;
	}

	public String strHtmlAfter() {
		return htmlAfter == null ? "" : htmlAfter;
	}

	public String jsonHtmlAfter() {
		return htmlAfter == null ? "" : htmlAfter;
	}

	public String nomAffichageHtmlAfter() {
		return "HTML après";
	}

	public String htmTooltipHtmlAfter() {
		return null;
	}

	public String htmHtmlAfter() {
		return htmlAfter == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlAfter());
	}

	public void htmHtmlAfter(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchFormPart", strPk(), "HtmlAfter\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchFormPart", strPk(), "HtmlAfter() {");
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
				r.l("				data: {\"setHtmlAfter\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlAfter()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlAfter\"");
							r.s(" value=\"", htmHtmlAfter(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlAfter());
			}
			r.l("</div>");
		}
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedFormPart = false;

	public FormPart initDeepFormPart(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedFormPart) {
			alreadyInitializedFormPart = true;
			initDeepFormPart();
		}
		return (FormPart)this;
	}

	public void initDeepFormPart() {
		super.initDeepCluster(siteRequest_);
		initFormPart();
	}

	public void initFormPart() {
		formPartKeyInit();
		enrollmentFormKeyInit();
		htmlOrderInit();
		htmlLinkInit();
		htmlElementInit();
		htmlIdInit();
		htmlClassesInit();
		htmlStyleInit();
		htmlBeforeInit();
		htmlTextInit();
		htmlAfterInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepFormPart(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestFormPart(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestFormPart(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainFormPart(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainFormPart(String var) {
		FormPart oFormPart = (FormPart)this;
		switch(var) {
			case "formPartKey":
				return oFormPart.formPartKey;
			case "enrollmentFormKey":
				return oFormPart.enrollmentFormKey;
			case "htmlOrder":
				return oFormPart.htmlOrder;
			case "htmlLink":
				return oFormPart.htmlLink;
			case "htmlElement":
				return oFormPart.htmlElement;
			case "htmlId":
				return oFormPart.htmlId;
			case "htmlClasses":
				return oFormPart.htmlClasses;
			case "htmlStyle":
				return oFormPart.htmlStyle;
			case "htmlBefore":
				return oFormPart.htmlBefore;
			case "htmlText":
				return oFormPart.htmlText;
			case "htmlAfter":
				return oFormPart.htmlAfter;
			default:
				return super.obtainCluster(var);
		}
	}

	///////////////
	// attribute //
	///////////////

	@Override public boolean attributeForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attributeFormPart(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeFormPart(String var, Object val) {
		FormPart oFormPart = (FormPart)this;
		switch(var) {
			default:
				return super.attributeCluster(var, val);
		}
	}

	/////////////
	// define //
	/////////////

	@Override public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = defineFormPart(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineFormPart(String var, String val) {
		switch(var) {
			case "htmlOrder":
				setHtmlOrder(val);
				savesFormPart.add(var);
				return val;
			case "htmlLink":
				setHtmlLink(val);
				savesFormPart.add(var);
				return val;
			case "htmlElement":
				setHtmlElement(val);
				savesFormPart.add(var);
				return val;
			case "htmlId":
				setHtmlId(val);
				savesFormPart.add(var);
				return val;
			case "htmlClasses":
				setHtmlClasses(val);
				savesFormPart.add(var);
				return val;
			case "htmlStyle":
				setHtmlStyle(val);
				savesFormPart.add(var);
				return val;
			case "htmlBefore":
				setHtmlBefore(val);
				savesFormPart.add(var);
				return val;
			case "htmlText":
				setHtmlText(val);
				savesFormPart.add(var);
				return val;
			case "htmlAfter":
				setHtmlAfter(val);
				savesFormPart.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesFormPart = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateFormPart(solrDocument);
	}
	public void populateFormPart(SolrDocument solrDocument) {
		FormPart oFormPart = (FormPart)this;
		savesFormPart = (List<String>)solrDocument.get("savesFormPart_stored_strings");
		if(savesFormPart != null) {

			if(savesFormPart.contains("formPartKey")) {
				Long formPartKey = (Long)solrDocument.get("formPartKey_stored_long");
				if(formPartKey != null)
					oFormPart.setFormPartKey(formPartKey);
			}

			if(savesFormPart.contains("enrollmentFormKey")) {
				Long enrollmentFormKey = (Long)solrDocument.get("enrollmentFormKey_stored_long");
				if(enrollmentFormKey != null)
					oFormPart.setEnrollmentFormKey(enrollmentFormKey);
			}

			if(savesFormPart.contains("htmlOrder")) {
				Double htmlOrder = (Double)solrDocument.get("htmlOrder_stored_double");
				if(htmlOrder != null)
					oFormPart.setHtmlOrder(htmlOrder);
			}

			if(savesFormPart.contains("htmlLink")) {
				String htmlLink = (String)solrDocument.get("htmlLink_stored_string");
				if(htmlLink != null)
					oFormPart.setHtmlLink(htmlLink);
			}

			if(savesFormPart.contains("htmlElement")) {
				String htmlElement = (String)solrDocument.get("htmlElement_stored_string");
				if(htmlElement != null)
					oFormPart.setHtmlElement(htmlElement);
			}

			if(savesFormPart.contains("htmlId")) {
				String htmlId = (String)solrDocument.get("htmlId_stored_string");
				if(htmlId != null)
					oFormPart.setHtmlId(htmlId);
			}

			if(savesFormPart.contains("htmlClasses")) {
				String htmlClasses = (String)solrDocument.get("htmlClasses_stored_string");
				if(htmlClasses != null)
					oFormPart.setHtmlClasses(htmlClasses);
			}

			if(savesFormPart.contains("htmlStyle")) {
				String htmlStyle = (String)solrDocument.get("htmlStyle_stored_string");
				if(htmlStyle != null)
					oFormPart.setHtmlStyle(htmlStyle);
			}

			if(savesFormPart.contains("htmlBefore")) {
				String htmlBefore = (String)solrDocument.get("htmlBefore_stored_string");
				if(htmlBefore != null)
					oFormPart.setHtmlBefore(htmlBefore);
			}

			if(savesFormPart.contains("htmlText")) {
				String htmlText = (String)solrDocument.get("htmlText_stored_string");
				if(htmlText != null)
					oFormPart.setHtmlText(htmlText);
			}

			if(savesFormPart.contains("htmlAfter")) {
				String htmlAfter = (String)solrDocument.get("htmlAfter_stored_string");
				if(htmlAfter != null)
					oFormPart.setHtmlAfter(htmlAfter);
			}
		}

		super.populateCluster(solrDocument);
	}

	/////////////
	// index //
	/////////////

	public static void index() {
		try {
			SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.getSiteConfig().setConfigPath("/usr/local/src/computate-scolaire/config/computate-scolaire.config");
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setQuery("*:*");
			solrQuery.setRows(1);
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.form.part.FormPart"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			FormPart o = new FormPart();
			o.siteRequestFormPart(siteRequest);
			o.initDeepFormPart(siteRequest);
			o.indexFormPart();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexFormPart();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexFormPart(document);
	}

	public void indexFormPart(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexFormPart(document);
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexFormPart() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexFormPart(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexFormPart(SolrInputDocument document) {
		if(savesFormPart != null)
			document.addField("savesFormPart_stored_strings", savesFormPart);

		if(formPartKey != null) {
			document.addField("formPartKey_indexed_long", formPartKey);
			document.addField("formPartKey_stored_long", formPartKey);
		}
		if(enrollmentFormKey != null) {
			document.addField("enrollmentFormKey_indexed_long", enrollmentFormKey);
			document.addField("enrollmentFormKey_stored_long", enrollmentFormKey);
		}
		if(htmlOrder != null) {
			document.addField("htmlOrder_stored_double", htmlOrder);
		}
		if(htmlLink != null) {
			document.addField("htmlLink_stored_string", htmlLink);
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
		if(htmlBefore != null) {
			document.addField("htmlBefore_stored_string", htmlBefore);
		}
		if(htmlText != null) {
			document.addField("htmlText_stored_string", htmlText);
		}
		if(htmlAfter != null) {
			document.addField("htmlAfter_stored_string", htmlAfter);
		}
		super.indexCluster(document);

	}

	public void unindexFormPart() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepFormPart(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeFormPart(solrDocument);
	}
	public void storeFormPart(SolrDocument solrDocument) {
		FormPart oFormPart = (FormPart)this;

		Long formPartKey = (Long)solrDocument.get("formPartKey_stored_long");
		if(formPartKey != null)
			oFormPart.setFormPartKey(formPartKey);

		Long enrollmentFormKey = (Long)solrDocument.get("enrollmentFormKey_stored_long");
		if(enrollmentFormKey != null)
			oFormPart.setEnrollmentFormKey(enrollmentFormKey);

		Double htmlOrder = (Double)solrDocument.get("htmlOrder_stored_double");
		if(htmlOrder != null)
			oFormPart.setHtmlOrder(htmlOrder);

		String htmlLink = (String)solrDocument.get("htmlLink_stored_string");
		if(htmlLink != null)
			oFormPart.setHtmlLink(htmlLink);

		String htmlElement = (String)solrDocument.get("htmlElement_stored_string");
		if(htmlElement != null)
			oFormPart.setHtmlElement(htmlElement);

		String htmlId = (String)solrDocument.get("htmlId_stored_string");
		if(htmlId != null)
			oFormPart.setHtmlId(htmlId);

		String htmlClasses = (String)solrDocument.get("htmlClasses_stored_string");
		if(htmlClasses != null)
			oFormPart.setHtmlClasses(htmlClasses);

		String htmlStyle = (String)solrDocument.get("htmlStyle_stored_string");
		if(htmlStyle != null)
			oFormPart.setHtmlStyle(htmlStyle);

		String htmlBefore = (String)solrDocument.get("htmlBefore_stored_string");
		if(htmlBefore != null)
			oFormPart.setHtmlBefore(htmlBefore);

		String htmlText = (String)solrDocument.get("htmlText_stored_string");
		if(htmlText != null)
			oFormPart.setHtmlText(htmlText);

		String htmlAfter = (String)solrDocument.get("htmlAfter_stored_string");
		if(htmlAfter != null)
			oFormPart.setHtmlAfter(htmlAfter);

		super.storeCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), htmlOrder, htmlLink, htmlElement, htmlId, htmlClasses, htmlStyle, htmlBefore, htmlText, htmlAfter);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof FormPart))
			return false;
		FormPart that = (FormPart)o;
		return super.equals(o)
				&& Objects.equals( htmlOrder, that.htmlOrder )
				&& Objects.equals( htmlLink, that.htmlLink )
				&& Objects.equals( htmlElement, that.htmlElement )
				&& Objects.equals( htmlId, that.htmlId )
				&& Objects.equals( htmlClasses, that.htmlClasses )
				&& Objects.equals( htmlStyle, that.htmlStyle )
				&& Objects.equals( htmlBefore, that.htmlBefore )
				&& Objects.equals( htmlText, that.htmlText )
				&& Objects.equals( htmlAfter, that.htmlAfter );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("FormPart { ");
		sb.append( "htmlOrder: " ).append(htmlOrder);
		sb.append( ", htmlLink: \"" ).append(htmlLink).append( "\"" );
		sb.append( ", htmlElement: \"" ).append(htmlElement).append( "\"" );
		sb.append( ", htmlId: \"" ).append(htmlId).append( "\"" );
		sb.append( ", htmlClasses: \"" ).append(htmlClasses).append( "\"" );
		sb.append( ", htmlStyle: \"" ).append(htmlStyle).append( "\"" );
		sb.append( ", htmlBefore: \"" ).append(htmlBefore).append( "\"" );
		sb.append( ", htmlText: \"" ).append(htmlText).append( "\"" );
		sb.append( ", htmlAfter: \"" ).append(htmlAfter).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
