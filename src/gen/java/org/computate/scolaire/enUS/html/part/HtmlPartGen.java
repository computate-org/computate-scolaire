package org.computate.scolaire.enUS.html.part;

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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class HtmlPartGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlPart.class);

	public static final String HtmlPart_UnNom = "an HTML part";
	public static final String HtmlPart_Ce = "this ";
	public static final String HtmlPart_CeNom = "this HTML part";
	public static final String HtmlPart_Un = "a ";
	public static final String HtmlPart_LeNom = "theHTML part";
	public static final String HtmlPart_NomSingulier = "HTML part";
	public static final String HtmlPart_NomPluriel = "HTML parts";
	public static final String HtmlPart_NomActuel = "current HTML part";
	public static final String HtmlPart_TousNom = "all the HTML parts";
	public static final String HtmlPart_RechercherTousNomPar = "search HTML parts by ";
	public static final String HtmlPart_LesNoms = "the HTML parts";
	public static final String HtmlPart_AucunNomTrouve = "no HTML part found";
	public static final String HtmlPart_NomVar = "html-part";
	public static final String HtmlPart_DeNom = "of HTML part";
	public static final String HtmlPart_UnNomAdjectif = "an HTML part";
	public static final String HtmlPart_NomAdjectifSingulier = "HTML part";
	public static final String HtmlPart_NomAdjectifPluriel = "HTML parts";
	public static final String HtmlPart_Couleur = "yellow";
	public static final String HtmlPart_IconeGroupe = "regular";
	public static final String HtmlPart_IconeNom = "sun";

	/////////////////
	// htmlPartKey //
	/////////////////

	/**	L'entité « htmlPartKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long htmlPartKey;
	@JsonIgnore
	public Wrap<Long> htmlPartKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("htmlPartKey").o(htmlPartKey);

	/**	<br/>L'entité « htmlPartKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPartKey">Trouver l'entité htmlPartKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlPartKey(Wrap<Long> c);

	public Long getHtmlPartKey() {
		return htmlPartKey;
	}

	public void setHtmlPartKey(Long htmlPartKey) {
		this.htmlPartKey = htmlPartKey;
		this.htmlPartKeyWrap.alreadyInitialized = true;
	}
	public HtmlPart setHtmlPartKey(String o) {
		if(NumberUtils.isParsable(o))
			this.htmlPartKey = Long.parseLong(o);
		this.htmlPartKeyWrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart htmlPartKeyInit() {
		if(!htmlPartKeyWrap.alreadyInitialized) {
			_htmlPartKey(htmlPartKeyWrap);
			if(htmlPartKey == null)
				setHtmlPartKey(htmlPartKeyWrap.o);
		}
		htmlPartKeyWrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Long solrHtmlPartKey() {
		return htmlPartKey;
	}

	public String strHtmlPartKey() {
		return htmlPartKey == null ? "" : htmlPartKey.toString();
	}

	public String jsonHtmlPartKey() {
		return htmlPartKey == null ? "" : htmlPartKey.toString();
	}

	public String nomAffichageHtmlPartKey() {
		return "key";
	}

	public String htmTooltipHtmlPartKey() {
		return null;
	}

	public String htmHtmlPartKey() {
		return htmlPartKey == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlPartKey());
	}

	public void htmHtmlPartKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "HtmlPartKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "HtmlPartKey() {");
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
				r.l("				data: {\"setHtmlPartKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageHtmlPartKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"htmlPartKey\"");
							r.s(" value=\"", htmHtmlPartKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmHtmlPartKey());
			}
			r.l("</div>");
		}
	}

	/////////////////////////
	// enrollmentDesignKey //
	/////////////////////////

	/**	L'entité « enrollmentDesignKey »
	 *	 is defined as null before being initialized. 
	 */
	protected Long enrollmentDesignKey;
	@JsonIgnore
	public Wrap<Long> enrollmentDesignKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("enrollmentDesignKey").o(enrollmentDesignKey);

	/**	<br/>L'entité « enrollmentDesignKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDesignKey">Trouver l'entité enrollmentDesignKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDesignKey(Wrap<Long> c);

	public Long getEnrollmentDesignKey() {
		return enrollmentDesignKey;
	}

	public void setEnrollmentDesignKey(Long enrollmentDesignKey) {
		this.enrollmentDesignKey = enrollmentDesignKey;
		this.enrollmentDesignKeyWrap.alreadyInitialized = true;
	}
	public HtmlPart setEnrollmentDesignKey(String o) {
		if(NumberUtils.isParsable(o))
			this.enrollmentDesignKey = Long.parseLong(o);
		this.enrollmentDesignKeyWrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart enrollmentDesignKeyInit() {
		if(!enrollmentDesignKeyWrap.alreadyInitialized) {
			_enrollmentDesignKey(enrollmentDesignKeyWrap);
			if(enrollmentDesignKey == null)
				setEnrollmentDesignKey(enrollmentDesignKeyWrap.o);
		}
		enrollmentDesignKeyWrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Long solrEnrollmentDesignKey() {
		return enrollmentDesignKey;
	}

	public String strEnrollmentDesignKey() {
		return enrollmentDesignKey == null ? "" : enrollmentDesignKey.toString();
	}

	public String jsonEnrollmentDesignKey() {
		return enrollmentDesignKey == null ? "" : enrollmentDesignKey.toString();
	}

	public String nomAffichageEnrollmentDesignKey() {
		return "enrollment design";
	}

	public String htmTooltipEnrollmentDesignKey() {
		return null;
	}

	public String htmEnrollmentDesignKey() {
		return enrollmentDesignKey == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentDesignKey());
	}

	public void htmEnrollmentDesignKey(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "EnrollmentDesignKey\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "EnrollmentDesignKey() {");
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
				r.l("				data: {\"setEnrollmentDesignKey\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageEnrollmentDesignKey()), "</span>");
				r.s("			<input");
							r.s(" name=\"enrollmentDesignKey\"");
							r.s(" value=\"", htmEnrollmentDesignKey(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmEnrollmentDesignKey());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlLink">Trouver l'entité htmlLink dans Solr</a>
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
	protected HtmlPart htmlLinkInit() {
		if(!htmlLinkWrap.alreadyInitialized) {
			_htmlLink(htmlLinkWrap);
			if(htmlLink == null)
				setHtmlLink(htmlLinkWrap.o);
		}
		htmlLinkWrap.alreadyInitialized(true);
		return (HtmlPart)this;
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
		return "link";
	}

	public String htmTooltipHtmlLink() {
		return null;
	}

	public String htmHtmlLink() {
		return htmlLink == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlLink());
	}

	public void htmHtmlLink(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "HtmlLink\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "HtmlLink() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlElement">Trouver l'entité htmlElement dans Solr</a>
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
	protected HtmlPart htmlElementInit() {
		if(!htmlElementWrap.alreadyInitialized) {
			_htmlElement(htmlElementWrap);
			if(htmlElement == null)
				setHtmlElement(htmlElementWrap.o);
		}
		htmlElementWrap.alreadyInitialized(true);
		return (HtmlPart)this;
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
			r.s("<div id=\"patchHtmlPart", strPk(), "HtmlElement\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "HtmlElement() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlId">Trouver l'entité htmlId dans Solr</a>
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
	protected HtmlPart htmlIdInit() {
		if(!htmlIdWrap.alreadyInitialized) {
			_htmlId(htmlIdWrap);
			if(htmlId == null)
				setHtmlId(htmlIdWrap.o);
		}
		htmlIdWrap.alreadyInitialized(true);
		return (HtmlPart)this;
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
			r.s("<div id=\"patchHtmlPart", strPk(), "HtmlId\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "HtmlId() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlClasses">Trouver l'entité htmlClasses dans Solr</a>
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
	protected HtmlPart htmlClassesInit() {
		if(!htmlClassesWrap.alreadyInitialized) {
			_htmlClasses(htmlClassesWrap);
			if(htmlClasses == null)
				setHtmlClasses(htmlClassesWrap.o);
		}
		htmlClassesWrap.alreadyInitialized(true);
		return (HtmlPart)this;
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
			r.s("<div id=\"patchHtmlPart", strPk(), "HtmlClasses\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "HtmlClasses() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlStyle">Trouver l'entité htmlStyle dans Solr</a>
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
	protected HtmlPart htmlStyleInit() {
		if(!htmlStyleWrap.alreadyInitialized) {
			_htmlStyle(htmlStyleWrap);
			if(htmlStyle == null)
				setHtmlStyle(htmlStyleWrap.o);
		}
		htmlStyleWrap.alreadyInitialized(true);
		return (HtmlPart)this;
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

	public void htmHtmlStyle(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "HtmlStyle\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "HtmlStyle() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlBefore">Trouver l'entité htmlBefore dans Solr</a>
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
	protected HtmlPart htmlBeforeInit() {
		if(!htmlBeforeWrap.alreadyInitialized) {
			_htmlBefore(htmlBeforeWrap);
			if(htmlBefore == null)
				setHtmlBefore(htmlBeforeWrap.o);
		}
		htmlBeforeWrap.alreadyInitialized(true);
		return (HtmlPart)this;
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
		return "HTML before";
	}

	public String htmTooltipHtmlBefore() {
		return null;
	}

	public String htmHtmlBefore() {
		return htmlBefore == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlBefore());
	}

	public void htmHtmlBefore(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "HtmlBefore\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "HtmlBefore() {");
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

	/////////////
	// htmlVar //
	/////////////

	/**	L'entité « htmlVar »
	 *	 is defined as null before being initialized. 
	 */
	protected String htmlVar;
	@JsonIgnore
	public Wrap<String> htmlVarWrap = new Wrap<String>().p(this).c(String.class).var("htmlVar").o(htmlVar);

	/**	<br/>L'entité « htmlVar »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlVar">Trouver l'entité htmlVar dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlVar(Wrap<String> c);

	public String getHtmlVar() {
		return htmlVar;
	}

	public void setHtmlVar(String htmlVar) {
		this.htmlVar = htmlVar;
		this.htmlVarWrap.alreadyInitialized = true;
	}
	protected HtmlPart htmlVarInit() {
		if(!htmlVarWrap.alreadyInitialized) {
			_htmlVar(htmlVarWrap);
			if(htmlVar == null)
				setHtmlVar(htmlVarWrap.o);
		}
		htmlVarWrap.alreadyInitialized(true);
		return (HtmlPart)this;
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

	public void htmHtmlVar(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "HtmlVar\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "HtmlVar() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlAfter">Trouver l'entité htmlAfter dans Solr</a>
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
	protected HtmlPart htmlAfterInit() {
		if(!htmlAfterWrap.alreadyInitialized) {
			_htmlAfter(htmlAfterWrap);
			if(htmlAfter == null)
				setHtmlAfter(htmlAfterWrap.o);
		}
		htmlAfterWrap.alreadyInitialized(true);
		return (HtmlPart)this;
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
		return "HTML after";
	}

	public String htmTooltipHtmlAfter() {
		return null;
	}

	public String htmHtmlAfter() {
		return htmlAfter == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlAfter());
	}

	public void htmHtmlAfter(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "HtmlAfter\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "HtmlAfter() {");
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlText">Trouver l'entité htmlText dans Solr</a>
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
	protected HtmlPart htmlTextInit() {
		if(!htmlTextWrap.alreadyInitialized) {
			_htmlText(htmlTextWrap);
			if(htmlText == null)
				setHtmlText(htmlTextWrap.o);
		}
		htmlTextWrap.alreadyInitialized(true);
		return (HtmlPart)this;
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
		return "text";
	}

	public String htmTooltipHtmlText() {
		return null;
	}

	public String htmHtmlText() {
		return htmlText == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlText());
	}

	public void htmHtmlText(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "HtmlText\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "HtmlText() {");
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

	///////////
	// sort1 //
	///////////

	/**	L'entité « sort1 »
	 *	 is defined as null before being initialized. 
	 */
	protected Double sort1;
	@JsonIgnore
	public Wrap<Double> sort1Wrap = new Wrap<Double>().p(this).c(Double.class).var("sort1").o(sort1);

	/**	<br/>L'entité « sort1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sort1">Trouver l'entité sort1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sort1(Wrap<Double> c);

	public Double getSort1() {
		return sort1;
	}

	public void setSort1(Double sort1) {
		this.sort1 = sort1;
		this.sort1Wrap.alreadyInitialized = true;
	}
	public HtmlPart setSort1(String o) {
		if(NumberUtils.isParsable(o))
			this.sort1 = Double.parseDouble(o);
		this.sort1Wrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart sort1Init() {
		if(!sort1Wrap.alreadyInitialized) {
			_sort1(sort1Wrap);
			if(sort1 == null)
				setSort1(sort1Wrap.o);
		}
		sort1Wrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Double solrSort1() {
		return sort1;
	}

	public String strSort1() {
		return sort1 == null ? "" : sort1.toString();
	}

	public String jsonSort1() {
		return sort1 == null ? "" : sort1.toString();
	}

	public String nomAffichageSort1() {
		return "sort1";
	}

	public String htmTooltipSort1() {
		return null;
	}

	public String htmSort1() {
		return sort1 == null ? "" : StringEscapeUtils.escapeHtml4(strSort1());
	}

	public void htmSort1(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "Sort1\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "Sort1() {");
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
				r.l("				data: {\"setSort1\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSort1()), "</span>");
				r.s("			<input");
							r.s(" name=\"sort1\"");
							r.s(" value=\"", htmSort1(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSort1());
			}
			r.l("</div>");
		}
	}

	///////////
	// sort2 //
	///////////

	/**	L'entité « sort2 »
	 *	 is defined as null before being initialized. 
	 */
	protected Double sort2;
	@JsonIgnore
	public Wrap<Double> sort2Wrap = new Wrap<Double>().p(this).c(Double.class).var("sort2").o(sort2);

	/**	<br/>L'entité « sort2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sort2">Trouver l'entité sort2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sort2(Wrap<Double> c);

	public Double getSort2() {
		return sort2;
	}

	public void setSort2(Double sort2) {
		this.sort2 = sort2;
		this.sort2Wrap.alreadyInitialized = true;
	}
	public HtmlPart setSort2(String o) {
		if(NumberUtils.isParsable(o))
			this.sort2 = Double.parseDouble(o);
		this.sort2Wrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart sort2Init() {
		if(!sort2Wrap.alreadyInitialized) {
			_sort2(sort2Wrap);
			if(sort2 == null)
				setSort2(sort2Wrap.o);
		}
		sort2Wrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Double solrSort2() {
		return sort2;
	}

	public String strSort2() {
		return sort2 == null ? "" : sort2.toString();
	}

	public String jsonSort2() {
		return sort2 == null ? "" : sort2.toString();
	}

	public String nomAffichageSort2() {
		return "sort2";
	}

	public String htmTooltipSort2() {
		return null;
	}

	public String htmSort2() {
		return sort2 == null ? "" : StringEscapeUtils.escapeHtml4(strSort2());
	}

	public void htmSort2(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "Sort2\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "Sort2() {");
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
				r.l("				data: {\"setSort2\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSort2()), "</span>");
				r.s("			<input");
							r.s(" name=\"sort2\"");
							r.s(" value=\"", htmSort2(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSort2());
			}
			r.l("</div>");
		}
	}

	///////////
	// sort3 //
	///////////

	/**	L'entité « sort3 »
	 *	 is defined as null before being initialized. 
	 */
	protected Double sort3;
	@JsonIgnore
	public Wrap<Double> sort3Wrap = new Wrap<Double>().p(this).c(Double.class).var("sort3").o(sort3);

	/**	<br/>L'entité « sort3 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sort3">Trouver l'entité sort3 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sort3(Wrap<Double> c);

	public Double getSort3() {
		return sort3;
	}

	public void setSort3(Double sort3) {
		this.sort3 = sort3;
		this.sort3Wrap.alreadyInitialized = true;
	}
	public HtmlPart setSort3(String o) {
		if(NumberUtils.isParsable(o))
			this.sort3 = Double.parseDouble(o);
		this.sort3Wrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart sort3Init() {
		if(!sort3Wrap.alreadyInitialized) {
			_sort3(sort3Wrap);
			if(sort3 == null)
				setSort3(sort3Wrap.o);
		}
		sort3Wrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Double solrSort3() {
		return sort3;
	}

	public String strSort3() {
		return sort3 == null ? "" : sort3.toString();
	}

	public String jsonSort3() {
		return sort3 == null ? "" : sort3.toString();
	}

	public String nomAffichageSort3() {
		return "sort3";
	}

	public String htmTooltipSort3() {
		return null;
	}

	public String htmSort3() {
		return sort3 == null ? "" : StringEscapeUtils.escapeHtml4(strSort3());
	}

	public void htmSort3(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "Sort3\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "Sort3() {");
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
				r.l("				data: {\"setSort3\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSort3()), "</span>");
				r.s("			<input");
							r.s(" name=\"sort3\"");
							r.s(" value=\"", htmSort3(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSort3());
			}
			r.l("</div>");
		}
	}

	///////////
	// sort4 //
	///////////

	/**	L'entité « sort4 »
	 *	 is defined as null before being initialized. 
	 */
	protected Double sort4;
	@JsonIgnore
	public Wrap<Double> sort4Wrap = new Wrap<Double>().p(this).c(Double.class).var("sort4").o(sort4);

	/**	<br/>L'entité « sort4 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sort4">Trouver l'entité sort4 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sort4(Wrap<Double> c);

	public Double getSort4() {
		return sort4;
	}

	public void setSort4(Double sort4) {
		this.sort4 = sort4;
		this.sort4Wrap.alreadyInitialized = true;
	}
	public HtmlPart setSort4(String o) {
		if(NumberUtils.isParsable(o))
			this.sort4 = Double.parseDouble(o);
		this.sort4Wrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart sort4Init() {
		if(!sort4Wrap.alreadyInitialized) {
			_sort4(sort4Wrap);
			if(sort4 == null)
				setSort4(sort4Wrap.o);
		}
		sort4Wrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Double solrSort4() {
		return sort4;
	}

	public String strSort4() {
		return sort4 == null ? "" : sort4.toString();
	}

	public String jsonSort4() {
		return sort4 == null ? "" : sort4.toString();
	}

	public String nomAffichageSort4() {
		return "sort4";
	}

	public String htmTooltipSort4() {
		return null;
	}

	public String htmSort4() {
		return sort4 == null ? "" : StringEscapeUtils.escapeHtml4(strSort4());
	}

	public void htmSort4(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "Sort4\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "Sort4() {");
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
				r.l("				data: {\"setSort4\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSort4()), "</span>");
				r.s("			<input");
							r.s(" name=\"sort4\"");
							r.s(" value=\"", htmSort4(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSort4());
			}
			r.l("</div>");
		}
	}

	///////////
	// sort5 //
	///////////

	/**	L'entité « sort5 »
	 *	 is defined as null before being initialized. 
	 */
	protected Double sort5;
	@JsonIgnore
	public Wrap<Double> sort5Wrap = new Wrap<Double>().p(this).c(Double.class).var("sort5").o(sort5);

	/**	<br/>L'entité « sort5 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sort5">Trouver l'entité sort5 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sort5(Wrap<Double> c);

	public Double getSort5() {
		return sort5;
	}

	public void setSort5(Double sort5) {
		this.sort5 = sort5;
		this.sort5Wrap.alreadyInitialized = true;
	}
	public HtmlPart setSort5(String o) {
		if(NumberUtils.isParsable(o))
			this.sort5 = Double.parseDouble(o);
		this.sort5Wrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart sort5Init() {
		if(!sort5Wrap.alreadyInitialized) {
			_sort5(sort5Wrap);
			if(sort5 == null)
				setSort5(sort5Wrap.o);
		}
		sort5Wrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Double solrSort5() {
		return sort5;
	}

	public String strSort5() {
		return sort5 == null ? "" : sort5.toString();
	}

	public String jsonSort5() {
		return sort5 == null ? "" : sort5.toString();
	}

	public String nomAffichageSort5() {
		return "sort5";
	}

	public String htmTooltipSort5() {
		return null;
	}

	public String htmSort5() {
		return sort5 == null ? "" : StringEscapeUtils.escapeHtml4(strSort5());
	}

	public void htmSort5(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "Sort5\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "Sort5() {");
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
				r.l("				data: {\"setSort5\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSort5()), "</span>");
				r.s("			<input");
							r.s(" name=\"sort5\"");
							r.s(" value=\"", htmSort5(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSort5());
			}
			r.l("</div>");
		}
	}

	///////////
	// sort6 //
	///////////

	/**	L'entité « sort6 »
	 *	 is defined as null before being initialized. 
	 */
	protected Double sort6;
	@JsonIgnore
	public Wrap<Double> sort6Wrap = new Wrap<Double>().p(this).c(Double.class).var("sort6").o(sort6);

	/**	<br/>L'entité « sort6 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sort6">Trouver l'entité sort6 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sort6(Wrap<Double> c);

	public Double getSort6() {
		return sort6;
	}

	public void setSort6(Double sort6) {
		this.sort6 = sort6;
		this.sort6Wrap.alreadyInitialized = true;
	}
	public HtmlPart setSort6(String o) {
		if(NumberUtils.isParsable(o))
			this.sort6 = Double.parseDouble(o);
		this.sort6Wrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart sort6Init() {
		if(!sort6Wrap.alreadyInitialized) {
			_sort6(sort6Wrap);
			if(sort6 == null)
				setSort6(sort6Wrap.o);
		}
		sort6Wrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Double solrSort6() {
		return sort6;
	}

	public String strSort6() {
		return sort6 == null ? "" : sort6.toString();
	}

	public String jsonSort6() {
		return sort6 == null ? "" : sort6.toString();
	}

	public String nomAffichageSort6() {
		return "sort6";
	}

	public String htmTooltipSort6() {
		return null;
	}

	public String htmSort6() {
		return sort6 == null ? "" : StringEscapeUtils.escapeHtml4(strSort6());
	}

	public void htmSort6(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "Sort6\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "Sort6() {");
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
				r.l("				data: {\"setSort6\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSort6()), "</span>");
				r.s("			<input");
							r.s(" name=\"sort6\"");
							r.s(" value=\"", htmSort6(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSort6());
			}
			r.l("</div>");
		}
	}

	///////////
	// sort7 //
	///////////

	/**	L'entité « sort7 »
	 *	 is defined as null before being initialized. 
	 */
	protected Double sort7;
	@JsonIgnore
	public Wrap<Double> sort7Wrap = new Wrap<Double>().p(this).c(Double.class).var("sort7").o(sort7);

	/**	<br/>L'entité « sort7 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sort7">Trouver l'entité sort7 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sort7(Wrap<Double> c);

	public Double getSort7() {
		return sort7;
	}

	public void setSort7(Double sort7) {
		this.sort7 = sort7;
		this.sort7Wrap.alreadyInitialized = true;
	}
	public HtmlPart setSort7(String o) {
		if(NumberUtils.isParsable(o))
			this.sort7 = Double.parseDouble(o);
		this.sort7Wrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart sort7Init() {
		if(!sort7Wrap.alreadyInitialized) {
			_sort7(sort7Wrap);
			if(sort7 == null)
				setSort7(sort7Wrap.o);
		}
		sort7Wrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Double solrSort7() {
		return sort7;
	}

	public String strSort7() {
		return sort7 == null ? "" : sort7.toString();
	}

	public String jsonSort7() {
		return sort7 == null ? "" : sort7.toString();
	}

	public String nomAffichageSort7() {
		return "sort7";
	}

	public String htmTooltipSort7() {
		return null;
	}

	public String htmSort7() {
		return sort7 == null ? "" : StringEscapeUtils.escapeHtml4(strSort7());
	}

	public void htmSort7(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "Sort7\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "Sort7() {");
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
				r.l("				data: {\"setSort7\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSort7()), "</span>");
				r.s("			<input");
							r.s(" name=\"sort7\"");
							r.s(" value=\"", htmSort7(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSort7());
			}
			r.l("</div>");
		}
	}

	///////////
	// sort8 //
	///////////

	/**	L'entité « sort8 »
	 *	 is defined as null before being initialized. 
	 */
	protected Double sort8;
	@JsonIgnore
	public Wrap<Double> sort8Wrap = new Wrap<Double>().p(this).c(Double.class).var("sort8").o(sort8);

	/**	<br/>L'entité « sort8 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sort8">Trouver l'entité sort8 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sort8(Wrap<Double> c);

	public Double getSort8() {
		return sort8;
	}

	public void setSort8(Double sort8) {
		this.sort8 = sort8;
		this.sort8Wrap.alreadyInitialized = true;
	}
	public HtmlPart setSort8(String o) {
		if(NumberUtils.isParsable(o))
			this.sort8 = Double.parseDouble(o);
		this.sort8Wrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart sort8Init() {
		if(!sort8Wrap.alreadyInitialized) {
			_sort8(sort8Wrap);
			if(sort8 == null)
				setSort8(sort8Wrap.o);
		}
		sort8Wrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Double solrSort8() {
		return sort8;
	}

	public String strSort8() {
		return sort8 == null ? "" : sort8.toString();
	}

	public String jsonSort8() {
		return sort8 == null ? "" : sort8.toString();
	}

	public String nomAffichageSort8() {
		return "sort8";
	}

	public String htmTooltipSort8() {
		return null;
	}

	public String htmSort8() {
		return sort8 == null ? "" : StringEscapeUtils.escapeHtml4(strSort8());
	}

	public void htmSort8(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "Sort8\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "Sort8() {");
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
				r.l("				data: {\"setSort8\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSort8()), "</span>");
				r.s("			<input");
							r.s(" name=\"sort8\"");
							r.s(" value=\"", htmSort8(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSort8());
			}
			r.l("</div>");
		}
	}

	///////////
	// sort9 //
	///////////

	/**	L'entité « sort9 »
	 *	 is defined as null before being initialized. 
	 */
	protected Double sort9;
	@JsonIgnore
	public Wrap<Double> sort9Wrap = new Wrap<Double>().p(this).c(Double.class).var("sort9").o(sort9);

	/**	<br/>L'entité « sort9 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sort9">Trouver l'entité sort9 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sort9(Wrap<Double> c);

	public Double getSort9() {
		return sort9;
	}

	public void setSort9(Double sort9) {
		this.sort9 = sort9;
		this.sort9Wrap.alreadyInitialized = true;
	}
	public HtmlPart setSort9(String o) {
		if(NumberUtils.isParsable(o))
			this.sort9 = Double.parseDouble(o);
		this.sort9Wrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart sort9Init() {
		if(!sort9Wrap.alreadyInitialized) {
			_sort9(sort9Wrap);
			if(sort9 == null)
				setSort9(sort9Wrap.o);
		}
		sort9Wrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Double solrSort9() {
		return sort9;
	}

	public String strSort9() {
		return sort9 == null ? "" : sort9.toString();
	}

	public String jsonSort9() {
		return sort9 == null ? "" : sort9.toString();
	}

	public String nomAffichageSort9() {
		return "sort9";
	}

	public String htmTooltipSort9() {
		return null;
	}

	public String htmSort9() {
		return sort9 == null ? "" : StringEscapeUtils.escapeHtml4(strSort9());
	}

	public void htmSort9(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "Sort9\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "Sort9() {");
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
				r.l("				data: {\"setSort9\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSort9()), "</span>");
				r.s("			<input");
							r.s(" name=\"sort9\"");
							r.s(" value=\"", htmSort9(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSort9());
			}
			r.l("</div>");
		}
	}

	////////////
	// sort10 //
	////////////

	/**	L'entité « sort10 »
	 *	 is defined as null before being initialized. 
	 */
	protected Double sort10;
	@JsonIgnore
	public Wrap<Double> sort10Wrap = new Wrap<Double>().p(this).c(Double.class).var("sort10").o(sort10);

	/**	<br/>L'entité « sort10 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPart&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sort10">Trouver l'entité sort10 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sort10(Wrap<Double> c);

	public Double getSort10() {
		return sort10;
	}

	public void setSort10(Double sort10) {
		this.sort10 = sort10;
		this.sort10Wrap.alreadyInitialized = true;
	}
	public HtmlPart setSort10(String o) {
		if(NumberUtils.isParsable(o))
			this.sort10 = Double.parseDouble(o);
		this.sort10Wrap.alreadyInitialized = true;
		return (HtmlPart)this;
	}
	protected HtmlPart sort10Init() {
		if(!sort10Wrap.alreadyInitialized) {
			_sort10(sort10Wrap);
			if(sort10 == null)
				setSort10(sort10Wrap.o);
		}
		sort10Wrap.alreadyInitialized(true);
		return (HtmlPart)this;
	}

	public Double solrSort10() {
		return sort10;
	}

	public String strSort10() {
		return sort10 == null ? "" : sort10.toString();
	}

	public String jsonSort10() {
		return sort10 == null ? "" : sort10.toString();
	}

	public String nomAffichageSort10() {
		return "sort10";
	}

	public String htmTooltipSort10() {
		return null;
	}

	public String htmSort10() {
		return sort10 == null ? "" : StringEscapeUtils.escapeHtml4(strSort10());
	}

	public void htmSort10(AllWriter r, Boolean patchRights) {
		if(pk!= null) {
			r.s("<div id=\"patchHtmlPart", strPk(), "Sort10\">");
			if(patchRights) {
				r.l();
				r.l("	<script>//<![CDATA[");
				r.l("		function patchHtmlPart", strPk(), "Sort10() {");
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
				r.l("				data: {\"setSort10\": this.value },");
				r.l("				");
				r.l("			});");
				r.l("		}");
				r.l("	//]]></script>");
				r.l("	<div class=\"\">");
				r.l("		<label class=\"w3-tooltip \">");
				r.l("			<span>", StringEscapeUtils.escapeHtml4(nomAffichageSort10()), "</span>");
				r.s("			<input");
							r.s(" name=\"sort10\"");
							r.s(" value=\"", htmSort10(), "\");");
							r.s(" onchange=\"\"");
							r.l("/>");
				r.l("		</label>");
				r.l("	</div>");
			} else {
				r.s(htmSort10());
			}
			r.l("</div>");
		}
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedHtmlPart = false;

	public HtmlPart initDeepHtmlPart(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedHtmlPart) {
			alreadyInitializedHtmlPart = true;
			initDeepHtmlPart();
		}
		return (HtmlPart)this;
	}

	public void initDeepHtmlPart() {
		initHtmlPart();
		super.initDeepCluster(siteRequest_);
	}

	public void initHtmlPart() {
		htmlPartKeyInit();
		enrollmentDesignKeyInit();
		htmlLinkInit();
		htmlElementInit();
		htmlIdInit();
		htmlClassesInit();
		htmlStyleInit();
		htmlBeforeInit();
		htmlVarInit();
		htmlAfterInit();
		htmlTextInit();
		sort1Init();
		sort2Init();
		sort3Init();
		sort4Init();
		sort5Init();
		sort6Init();
		sort7Init();
		sort8Init();
		sort9Init();
		sort10Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepHtmlPart(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestHtmlPart(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestHtmlPart(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainHtmlPart(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainHtmlPart(String var) {
		HtmlPart oHtmlPart = (HtmlPart)this;
		switch(var) {
			case "htmlPartKey":
				return oHtmlPart.htmlPartKey;
			case "enrollmentDesignKey":
				return oHtmlPart.enrollmentDesignKey;
			case "htmlLink":
				return oHtmlPart.htmlLink;
			case "htmlElement":
				return oHtmlPart.htmlElement;
			case "htmlId":
				return oHtmlPart.htmlId;
			case "htmlClasses":
				return oHtmlPart.htmlClasses;
			case "htmlStyle":
				return oHtmlPart.htmlStyle;
			case "htmlBefore":
				return oHtmlPart.htmlBefore;
			case "htmlVar":
				return oHtmlPart.htmlVar;
			case "htmlAfter":
				return oHtmlPart.htmlAfter;
			case "htmlText":
				return oHtmlPart.htmlText;
			case "sort1":
				return oHtmlPart.sort1;
			case "sort2":
				return oHtmlPart.sort2;
			case "sort3":
				return oHtmlPart.sort3;
			case "sort4":
				return oHtmlPart.sort4;
			case "sort5":
				return oHtmlPart.sort5;
			case "sort6":
				return oHtmlPart.sort6;
			case "sort7":
				return oHtmlPart.sort7;
			case "sort8":
				return oHtmlPart.sort8;
			case "sort9":
				return oHtmlPart.sort9;
			case "sort10":
				return oHtmlPart.sort10;
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
				o = attributeHtmlPart(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeHtmlPart(String var, Object val) {
		HtmlPart oHtmlPart = (HtmlPart)this;
		switch(var) {
			case "enrollmentDesignKey":
				oHtmlPart.setEnrollmentDesignKey((Long)val);
				return val;
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
					o = defineHtmlPart(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineHtmlPart(String var, String val) {
		switch(var) {
			case "htmlLink":
				setHtmlLink(val);
				savesHtmlPart.add(var);
				return val;
			case "htmlElement":
				setHtmlElement(val);
				savesHtmlPart.add(var);
				return val;
			case "htmlId":
				setHtmlId(val);
				savesHtmlPart.add(var);
				return val;
			case "htmlClasses":
				setHtmlClasses(val);
				savesHtmlPart.add(var);
				return val;
			case "htmlStyle":
				setHtmlStyle(val);
				savesHtmlPart.add(var);
				return val;
			case "htmlBefore":
				setHtmlBefore(val);
				savesHtmlPart.add(var);
				return val;
			case "htmlVar":
				setHtmlVar(val);
				savesHtmlPart.add(var);
				return val;
			case "htmlAfter":
				setHtmlAfter(val);
				savesHtmlPart.add(var);
				return val;
			case "htmlText":
				setHtmlText(val);
				savesHtmlPart.add(var);
				return val;
			case "sort1":
				setSort1(val);
				savesHtmlPart.add(var);
				return val;
			case "sort2":
				setSort2(val);
				savesHtmlPart.add(var);
				return val;
			case "sort3":
				setSort3(val);
				savesHtmlPart.add(var);
				return val;
			case "sort4":
				setSort4(val);
				savesHtmlPart.add(var);
				return val;
			case "sort5":
				setSort5(val);
				savesHtmlPart.add(var);
				return val;
			case "sort6":
				setSort6(val);
				savesHtmlPart.add(var);
				return val;
			case "sort7":
				setSort7(val);
				savesHtmlPart.add(var);
				return val;
			case "sort8":
				setSort8(val);
				savesHtmlPart.add(var);
				return val;
			case "sort9":
				setSort9(val);
				savesHtmlPart.add(var);
				return val;
			case "sort10":
				setSort10(val);
				savesHtmlPart.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesHtmlPart = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateHtmlPart(solrDocument);
	}
	public void populateHtmlPart(SolrDocument solrDocument) {
		HtmlPart oHtmlPart = (HtmlPart)this;
		savesHtmlPart = (List<String>)solrDocument.get("savesHtmlPart_stored_strings");
		if(savesHtmlPart != null) {

			if(savesHtmlPart.contains("htmlPartKey")) {
				Long htmlPartKey = (Long)solrDocument.get("htmlPartKey_stored_long");
				if(htmlPartKey != null)
					oHtmlPart.setHtmlPartKey(htmlPartKey);
			}

			Long enrollmentDesignKey = (Long)solrDocument.get("enrollmentDesignKey_stored_long");
			if(enrollmentDesignKey != null)
				oHtmlPart.setEnrollmentDesignKey(enrollmentDesignKey);

			if(savesHtmlPart.contains("htmlLink")) {
				String htmlLink = (String)solrDocument.get("htmlLink_stored_string");
				if(htmlLink != null)
					oHtmlPart.setHtmlLink(htmlLink);
			}

			if(savesHtmlPart.contains("htmlElement")) {
				String htmlElement = (String)solrDocument.get("htmlElement_stored_string");
				if(htmlElement != null)
					oHtmlPart.setHtmlElement(htmlElement);
			}

			if(savesHtmlPart.contains("htmlId")) {
				String htmlId = (String)solrDocument.get("htmlId_stored_string");
				if(htmlId != null)
					oHtmlPart.setHtmlId(htmlId);
			}

			if(savesHtmlPart.contains("htmlClasses")) {
				String htmlClasses = (String)solrDocument.get("htmlClasses_stored_string");
				if(htmlClasses != null)
					oHtmlPart.setHtmlClasses(htmlClasses);
			}

			if(savesHtmlPart.contains("htmlStyle")) {
				String htmlStyle = (String)solrDocument.get("htmlStyle_stored_string");
				if(htmlStyle != null)
					oHtmlPart.setHtmlStyle(htmlStyle);
			}

			if(savesHtmlPart.contains("htmlBefore")) {
				String htmlBefore = (String)solrDocument.get("htmlBefore_stored_string");
				if(htmlBefore != null)
					oHtmlPart.setHtmlBefore(htmlBefore);
			}

			if(savesHtmlPart.contains("htmlVar")) {
				String htmlVar = (String)solrDocument.get("htmlVar_stored_string");
				if(htmlVar != null)
					oHtmlPart.setHtmlVar(htmlVar);
			}

			if(savesHtmlPart.contains("htmlAfter")) {
				String htmlAfter = (String)solrDocument.get("htmlAfter_stored_string");
				if(htmlAfter != null)
					oHtmlPart.setHtmlAfter(htmlAfter);
			}

			if(savesHtmlPart.contains("htmlText")) {
				String htmlText = (String)solrDocument.get("htmlText_stored_string");
				if(htmlText != null)
					oHtmlPart.setHtmlText(htmlText);
			}

			if(savesHtmlPart.contains("sort1")) {
				Double sort1 = (Double)solrDocument.get("sort1_stored_double");
				if(sort1 != null)
					oHtmlPart.setSort1(sort1);
			}

			if(savesHtmlPart.contains("sort2")) {
				Double sort2 = (Double)solrDocument.get("sort2_stored_double");
				if(sort2 != null)
					oHtmlPart.setSort2(sort2);
			}

			if(savesHtmlPart.contains("sort3")) {
				Double sort3 = (Double)solrDocument.get("sort3_stored_double");
				if(sort3 != null)
					oHtmlPart.setSort3(sort3);
			}

			if(savesHtmlPart.contains("sort4")) {
				Double sort4 = (Double)solrDocument.get("sort4_stored_double");
				if(sort4 != null)
					oHtmlPart.setSort4(sort4);
			}

			if(savesHtmlPart.contains("sort5")) {
				Double sort5 = (Double)solrDocument.get("sort5_stored_double");
				if(sort5 != null)
					oHtmlPart.setSort5(sort5);
			}

			if(savesHtmlPart.contains("sort6")) {
				Double sort6 = (Double)solrDocument.get("sort6_stored_double");
				if(sort6 != null)
					oHtmlPart.setSort6(sort6);
			}

			if(savesHtmlPart.contains("sort7")) {
				Double sort7 = (Double)solrDocument.get("sort7_stored_double");
				if(sort7 != null)
					oHtmlPart.setSort7(sort7);
			}

			if(savesHtmlPart.contains("sort8")) {
				Double sort8 = (Double)solrDocument.get("sort8_stored_double");
				if(sort8 != null)
					oHtmlPart.setSort8(sort8);
			}

			if(savesHtmlPart.contains("sort9")) {
				Double sort9 = (Double)solrDocument.get("sort9_stored_double");
				if(sort9 != null)
					oHtmlPart.setSort9(sort9);
			}

			if(savesHtmlPart.contains("sort10")) {
				Double sort10 = (Double)solrDocument.get("sort10_stored_double");
				if(sort10 != null)
					oHtmlPart.setSort10(sort10);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.html.part.HtmlPart"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			HtmlPart o = new HtmlPart();
			o.siteRequestHtmlPart(siteRequest);
			o.initDeepHtmlPart(siteRequest);
			o.indexHtmlPart();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexHtmlPart();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexHtmlPart(document);
	}

	public void indexHtmlPart(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexHtmlPart(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, false);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexHtmlPart() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexHtmlPart(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, false);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexHtmlPart(SolrInputDocument document) {
		if(savesHtmlPart != null)
			document.addField("savesHtmlPart_stored_strings", savesHtmlPart);

		if(htmlPartKey != null) {
			document.addField("htmlPartKey_indexed_long", htmlPartKey);
			document.addField("htmlPartKey_stored_long", htmlPartKey);
		}
		if(enrollmentDesignKey != null) {
			document.addField("enrollmentDesignKey_indexed_long", enrollmentDesignKey);
			document.addField("enrollmentDesignKey_stored_long", enrollmentDesignKey);
		}
		if(htmlLink != null) {
			document.addField("htmlLink_indexed_string", htmlLink);
			document.addField("htmlLink_stored_string", htmlLink);
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
		if(htmlBefore != null) {
			document.addField("htmlBefore_indexed_string", htmlBefore);
			document.addField("htmlBefore_stored_string", htmlBefore);
		}
		if(htmlVar != null) {
			document.addField("htmlVar_indexed_string", htmlVar);
			document.addField("htmlVar_stored_string", htmlVar);
		}
		if(htmlAfter != null) {
			document.addField("htmlAfter_indexed_string", htmlAfter);
			document.addField("htmlAfter_stored_string", htmlAfter);
		}
		if(htmlText != null) {
			document.addField("htmlText_indexed_string", htmlText);
			document.addField("htmlText_stored_string", htmlText);
		}
		if(sort1 != null) {
			document.addField("sort1_indexed_double", sort1);
			document.addField("sort1_stored_double", sort1);
		}
		if(sort2 != null) {
			document.addField("sort2_indexed_double", sort2);
			document.addField("sort2_stored_double", sort2);
		}
		if(sort3 != null) {
			document.addField("sort3_indexed_double", sort3);
			document.addField("sort3_stored_double", sort3);
		}
		if(sort4 != null) {
			document.addField("sort4_indexed_double", sort4);
			document.addField("sort4_stored_double", sort4);
		}
		if(sort5 != null) {
			document.addField("sort5_indexed_double", sort5);
			document.addField("sort5_stored_double", sort5);
		}
		if(sort6 != null) {
			document.addField("sort6_indexed_double", sort6);
			document.addField("sort6_stored_double", sort6);
		}
		if(sort7 != null) {
			document.addField("sort7_indexed_double", sort7);
			document.addField("sort7_stored_double", sort7);
		}
		if(sort8 != null) {
			document.addField("sort8_indexed_double", sort8);
			document.addField("sort8_stored_double", sort8);
		}
		if(sort9 != null) {
			document.addField("sort9_indexed_double", sort9);
			document.addField("sort9_stored_double", sort9);
		}
		if(sort10 != null) {
			document.addField("sort10_indexed_double", sort10);
			document.addField("sort10_stored_double", sort10);
		}
		super.indexCluster(document);

	}

	public void unindexHtmlPart() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepHtmlPart(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, false);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeHtmlPart(solrDocument);
	}
	public void storeHtmlPart(SolrDocument solrDocument) {
		HtmlPart oHtmlPart = (HtmlPart)this;

		Long htmlPartKey = (Long)solrDocument.get("htmlPartKey_stored_long");
		if(htmlPartKey != null)
			oHtmlPart.setHtmlPartKey(htmlPartKey);

		Long enrollmentDesignKey = (Long)solrDocument.get("enrollmentDesignKey_stored_long");
		if(enrollmentDesignKey != null)
			oHtmlPart.setEnrollmentDesignKey(enrollmentDesignKey);

		String htmlLink = (String)solrDocument.get("htmlLink_stored_string");
		if(htmlLink != null)
			oHtmlPart.setHtmlLink(htmlLink);

		String htmlElement = (String)solrDocument.get("htmlElement_stored_string");
		if(htmlElement != null)
			oHtmlPart.setHtmlElement(htmlElement);

		String htmlId = (String)solrDocument.get("htmlId_stored_string");
		if(htmlId != null)
			oHtmlPart.setHtmlId(htmlId);

		String htmlClasses = (String)solrDocument.get("htmlClasses_stored_string");
		if(htmlClasses != null)
			oHtmlPart.setHtmlClasses(htmlClasses);

		String htmlStyle = (String)solrDocument.get("htmlStyle_stored_string");
		if(htmlStyle != null)
			oHtmlPart.setHtmlStyle(htmlStyle);

		String htmlBefore = (String)solrDocument.get("htmlBefore_stored_string");
		if(htmlBefore != null)
			oHtmlPart.setHtmlBefore(htmlBefore);

		String htmlVar = (String)solrDocument.get("htmlVar_stored_string");
		if(htmlVar != null)
			oHtmlPart.setHtmlVar(htmlVar);

		String htmlAfter = (String)solrDocument.get("htmlAfter_stored_string");
		if(htmlAfter != null)
			oHtmlPart.setHtmlAfter(htmlAfter);

		String htmlText = (String)solrDocument.get("htmlText_stored_string");
		if(htmlText != null)
			oHtmlPart.setHtmlText(htmlText);

		Double sort1 = (Double)solrDocument.get("sort1_stored_double");
		if(sort1 != null)
			oHtmlPart.setSort1(sort1);

		Double sort2 = (Double)solrDocument.get("sort2_stored_double");
		if(sort2 != null)
			oHtmlPart.setSort2(sort2);

		Double sort3 = (Double)solrDocument.get("sort3_stored_double");
		if(sort3 != null)
			oHtmlPart.setSort3(sort3);

		Double sort4 = (Double)solrDocument.get("sort4_stored_double");
		if(sort4 != null)
			oHtmlPart.setSort4(sort4);

		Double sort5 = (Double)solrDocument.get("sort5_stored_double");
		if(sort5 != null)
			oHtmlPart.setSort5(sort5);

		Double sort6 = (Double)solrDocument.get("sort6_stored_double");
		if(sort6 != null)
			oHtmlPart.setSort6(sort6);

		Double sort7 = (Double)solrDocument.get("sort7_stored_double");
		if(sort7 != null)
			oHtmlPart.setSort7(sort7);

		Double sort8 = (Double)solrDocument.get("sort8_stored_double");
		if(sort8 != null)
			oHtmlPart.setSort8(sort8);

		Double sort9 = (Double)solrDocument.get("sort9_stored_double");
		if(sort9 != null)
			oHtmlPart.setSort9(sort9);

		Double sort10 = (Double)solrDocument.get("sort10_stored_double");
		if(sort10 != null)
			oHtmlPart.setSort10(sort10);

		super.storeCluster(solrDocument);
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), enrollmentDesignKey, htmlLink, htmlElement, htmlId, htmlClasses, htmlStyle, htmlBefore, htmlVar, htmlAfter, htmlText, sort1, sort2, sort3, sort4, sort5, sort6, sort7, sort8, sort9, sort10);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof HtmlPart))
			return false;
		HtmlPart that = (HtmlPart)o;
		return super.equals(o)
				&& Objects.equals( enrollmentDesignKey, that.enrollmentDesignKey )
				&& Objects.equals( htmlLink, that.htmlLink )
				&& Objects.equals( htmlElement, that.htmlElement )
				&& Objects.equals( htmlId, that.htmlId )
				&& Objects.equals( htmlClasses, that.htmlClasses )
				&& Objects.equals( htmlStyle, that.htmlStyle )
				&& Objects.equals( htmlBefore, that.htmlBefore )
				&& Objects.equals( htmlVar, that.htmlVar )
				&& Objects.equals( htmlAfter, that.htmlAfter )
				&& Objects.equals( htmlText, that.htmlText )
				&& Objects.equals( sort1, that.sort1 )
				&& Objects.equals( sort2, that.sort2 )
				&& Objects.equals( sort3, that.sort3 )
				&& Objects.equals( sort4, that.sort4 )
				&& Objects.equals( sort5, that.sort5 )
				&& Objects.equals( sort6, that.sort6 )
				&& Objects.equals( sort7, that.sort7 )
				&& Objects.equals( sort8, that.sort8 )
				&& Objects.equals( sort9, that.sort9 )
				&& Objects.equals( sort10, that.sort10 );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("HtmlPart { ");
		sb.append( "enrollmentDesignKey: " ).append(enrollmentDesignKey);
		sb.append( ", htmlLink: \"" ).append(htmlLink).append( "\"" );
		sb.append( ", htmlElement: \"" ).append(htmlElement).append( "\"" );
		sb.append( ", htmlId: \"" ).append(htmlId).append( "\"" );
		sb.append( ", htmlClasses: \"" ).append(htmlClasses).append( "\"" );
		sb.append( ", htmlStyle: \"" ).append(htmlStyle).append( "\"" );
		sb.append( ", htmlBefore: \"" ).append(htmlBefore).append( "\"" );
		sb.append( ", htmlVar: \"" ).append(htmlVar).append( "\"" );
		sb.append( ", htmlAfter: \"" ).append(htmlAfter).append( "\"" );
		sb.append( ", htmlText: \"" ).append(htmlText).append( "\"" );
		sb.append( ", sort1: " ).append(sort1);
		sb.append( ", sort2: " ).append(sort2);
		sb.append( ", sort3: " ).append(sort3);
		sb.append( ", sort4: " ).append(sort4);
		sb.append( ", sort5: " ).append(sort5);
		sb.append( ", sort6: " ).append(sort6);
		sb.append( ", sort7: " ).append(sort7);
		sb.append( ", sort8: " ).append(sort8);
		sb.append( ", sort9: " ).append(sort9);
		sb.append( ", sort10: " ).append(sort10);
		sb.append(" }");
		return sb.toString();
	}
}
