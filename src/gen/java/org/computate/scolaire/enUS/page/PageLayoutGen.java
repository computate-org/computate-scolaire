package org.computate.scolaire.enUS.page;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import org.computate.scolaire.enUS.page.part.PagePart;
import java.time.ZoneId;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PageLayoutGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PageLayout.class);

	///////////////
	// pageParts //
	///////////////

	/**	L'entité « pageParts »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<PagePart>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<PagePart> pageParts = new ArrayList<PagePart>();
	@JsonIgnore
	public Wrap<List<PagePart>> pagePartsWrap = new Wrap<List<PagePart>>().p(this).c(List.class).var("pageParts").o(pageParts);

	/**	<br/>L'entité « pageParts »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<PagePart>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageParts">Trouver l'entité pageParts dans Solr</a>
	 * <br/>
	 * @param pageParts est l'entité déjà construit. 
	 **/
	protected abstract void _pageParts(List<PagePart> l);

	public List<PagePart> getPageParts() {
		return pageParts;
	}

	public void setPageParts(List<PagePart> pageParts) {
		this.pageParts = pageParts;
		this.pagePartsWrap.alreadyInitialized = true;
	}
	public PageLayout addPageParts(PagePart...objets) {
		for(PagePart o : objets) {
			addPageParts(o);
		}
		return (PageLayout)this;
	}
	public PageLayout addPageParts(PagePart o) {
		if(o != null && !pageParts.contains(o))
			this.pageParts.add(o);
		return (PageLayout)this;
	}
	public abstract void beforePagePart(PagePart o, String entiteVar);
	protected PageLayout pagePartsInit() {
		if(!pagePartsWrap.alreadyInitialized) {
			_pageParts(pageParts);
		}
		pagePartsWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	//////////////////
	// siteRequest_ //
	//////////////////

	/**	L'entité « siteRequest_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SiteRequestEnUS siteRequest_;
	@JsonIgnore
	public Wrap<SiteRequestEnUS> siteRequest_Wrap = new Wrap<SiteRequestEnUS>().p(this).c(SiteRequestEnUS.class).var("siteRequest_").o(siteRequest_);

	/**	<br/>L'entité « siteRequest_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteRequest_">Trouver l'entité siteRequest_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteRequest_(Wrap<SiteRequestEnUS> c);

	public SiteRequestEnUS getSiteRequest_() {
		return siteRequest_;
	}

	public void setSiteRequest_(SiteRequestEnUS siteRequest_) {
		this.siteRequest_ = siteRequest_;
		this.siteRequest_Wrap.alreadyInitialized = true;
	}
	protected PageLayout siteRequest_Init() {
		if(!siteRequest_Wrap.alreadyInitialized) {
			_siteRequest_(siteRequest_Wrap);
			if(siteRequest_ == null)
				setSiteRequest_(siteRequest_Wrap.o);
		}
		siteRequest_Wrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	/////////////////
	// siteBaseUrl //
	/////////////////

	/**	L'entité « siteBaseUrl »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteBaseUrl;
	@JsonIgnore
	public Wrap<String> siteBaseUrlWrap = new Wrap<String>().p(this).c(String.class).var("siteBaseUrl").o(siteBaseUrl);

	/**	<br/>L'entité « siteBaseUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteBaseUrl">Trouver l'entité siteBaseUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteBaseUrl(Wrap<String> c);

	public String getSiteBaseUrl() {
		return siteBaseUrl;
	}

	public void setSiteBaseUrl(String siteBaseUrl) {
		this.siteBaseUrl = siteBaseUrl;
		this.siteBaseUrlWrap.alreadyInitialized = true;
	}
	protected PageLayout siteBaseUrlInit() {
		if(!siteBaseUrlWrap.alreadyInitialized) {
			_siteBaseUrl(siteBaseUrlWrap);
			if(siteBaseUrl == null)
				setSiteBaseUrl(siteBaseUrlWrap.o);
		}
		siteBaseUrlWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrSiteBaseUrl() {
		return siteBaseUrl;
	}

	public String strSiteBaseUrl() {
		return siteBaseUrl == null ? "" : siteBaseUrl;
	}

	public String jsonSiteBaseUrl() {
		return siteBaseUrl == null ? "" : siteBaseUrl;
	}

	public String nomAffichageSiteBaseUrl() {
		return null;
	}

	public String htmTooltipSiteBaseUrl() {
		return null;
	}

	public String htmSiteBaseUrl() {
		return siteBaseUrl == null ? "" : StringEscapeUtils.escapeHtml4(strSiteBaseUrl());
	}

	///////////////////
	// staticBaseUrl //
	///////////////////

	/**	L'entité « staticBaseUrl »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String staticBaseUrl;
	@JsonIgnore
	public Wrap<String> staticBaseUrlWrap = new Wrap<String>().p(this).c(String.class).var("staticBaseUrl").o(staticBaseUrl);

	/**	<br/>L'entité « staticBaseUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:staticBaseUrl">Trouver l'entité staticBaseUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _staticBaseUrl(Wrap<String> c);

	public String getStaticBaseUrl() {
		return staticBaseUrl;
	}

	public void setStaticBaseUrl(String staticBaseUrl) {
		this.staticBaseUrl = staticBaseUrl;
		this.staticBaseUrlWrap.alreadyInitialized = true;
	}
	protected PageLayout staticBaseUrlInit() {
		if(!staticBaseUrlWrap.alreadyInitialized) {
			_staticBaseUrl(staticBaseUrlWrap);
			if(staticBaseUrl == null)
				setStaticBaseUrl(staticBaseUrlWrap.o);
		}
		staticBaseUrlWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrStaticBaseUrl() {
		return staticBaseUrl;
	}

	public String strStaticBaseUrl() {
		return staticBaseUrl == null ? "" : staticBaseUrl;
	}

	public String jsonStaticBaseUrl() {
		return staticBaseUrl == null ? "" : staticBaseUrl;
	}

	public String nomAffichageStaticBaseUrl() {
		return null;
	}

	public String htmTooltipStaticBaseUrl() {
		return null;
	}

	public String htmStaticBaseUrl() {
		return staticBaseUrl == null ? "" : StringEscapeUtils.escapeHtml4(strStaticBaseUrl());
	}

	//////////////////////
	// pageSolrDocument //
	//////////////////////

	/**	L'entité « pageSolrDocument »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SolrDocument pageSolrDocument;
	@JsonIgnore
	public Wrap<SolrDocument> pageSolrDocumentWrap = new Wrap<SolrDocument>().p(this).c(SolrDocument.class).var("pageSolrDocument").o(pageSolrDocument);

	/**	<br/>L'entité « pageSolrDocument »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageSolrDocument">Trouver l'entité pageSolrDocument dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageSolrDocument(Wrap<SolrDocument> c);

	public SolrDocument getPageSolrDocument() {
		return pageSolrDocument;
	}

	public void setPageSolrDocument(SolrDocument pageSolrDocument) {
		this.pageSolrDocument = pageSolrDocument;
		this.pageSolrDocumentWrap.alreadyInitialized = true;
	}
	protected PageLayout pageSolrDocumentInit() {
		if(!pageSolrDocumentWrap.alreadyInitialized) {
			_pageSolrDocument(pageSolrDocumentWrap);
			if(pageSolrDocument == null)
				setPageSolrDocument(pageSolrDocumentWrap.o);
		}
		pageSolrDocumentWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	///////
	// w //
	///////

	/**	L'entité « w »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter w;
	@JsonIgnore
	public Wrap<AllWriter> wWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("w").o(w);

	/**	<br/>L'entité « w »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:w">Trouver l'entité w dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _w(Wrap<AllWriter> c);

	public AllWriter getW() {
		return w;
	}

	public void setW(AllWriter w) {
		this.w = w;
		this.wWrap.alreadyInitialized = true;
	}
	protected PageLayout wInit() {
		if(!wWrap.alreadyInitialized) {
			_w(wWrap);
			if(w == null)
				setW(wWrap.o);
		}
		if(w != null)
			w.initDeepForClass(siteRequest_);
		wWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	//////////////////////
	// contextIconGroup //
	//////////////////////

	/**	L'entité « contextIconGroup »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String contextIconGroup;
	@JsonIgnore
	public Wrap<String> contextIconGroupWrap = new Wrap<String>().p(this).c(String.class).var("contextIconGroup").o(contextIconGroup);

	/**	<br/>L'entité « contextIconGroup »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:contextIconGroup">Trouver l'entité contextIconGroup dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _contextIconGroup(Wrap<String> c);

	public String getContextIconGroup() {
		return contextIconGroup;
	}

	public void setContextIconGroup(String contextIconGroup) {
		this.contextIconGroup = contextIconGroup;
		this.contextIconGroupWrap.alreadyInitialized = true;
	}
	protected PageLayout contextIconGroupInit() {
		if(!contextIconGroupWrap.alreadyInitialized) {
			_contextIconGroup(contextIconGroupWrap);
			if(contextIconGroup == null)
				setContextIconGroup(contextIconGroupWrap.o);
		}
		contextIconGroupWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrContextIconGroup() {
		return contextIconGroup;
	}

	public String strContextIconGroup() {
		return contextIconGroup == null ? "" : contextIconGroup;
	}

	public String jsonContextIconGroup() {
		return contextIconGroup == null ? "" : contextIconGroup;
	}

	public String nomAffichageContextIconGroup() {
		return null;
	}

	public String htmTooltipContextIconGroup() {
		return null;
	}

	public String htmContextIconGroup() {
		return contextIconGroup == null ? "" : StringEscapeUtils.escapeHtml4(strContextIconGroup());
	}

	/////////////////////
	// contextIconName //
	/////////////////////

	/**	L'entité « contextIconName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String contextIconName;
	@JsonIgnore
	public Wrap<String> contextIconNameWrap = new Wrap<String>().p(this).c(String.class).var("contextIconName").o(contextIconName);

	/**	<br/>L'entité « contextIconName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:contextIconName">Trouver l'entité contextIconName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _contextIconName(Wrap<String> c);

	public String getContextIconName() {
		return contextIconName;
	}

	public void setContextIconName(String contextIconName) {
		this.contextIconName = contextIconName;
		this.contextIconNameWrap.alreadyInitialized = true;
	}
	protected PageLayout contextIconNameInit() {
		if(!contextIconNameWrap.alreadyInitialized) {
			_contextIconName(contextIconNameWrap);
			if(contextIconName == null)
				setContextIconName(contextIconNameWrap.o);
		}
		contextIconNameWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrContextIconName() {
		return contextIconName;
	}

	public String strContextIconName() {
		return contextIconName == null ? "" : contextIconName;
	}

	public String jsonContextIconName() {
		return contextIconName == null ? "" : contextIconName;
	}

	public String nomAffichageContextIconName() {
		return null;
	}

	public String htmTooltipContextIconName() {
		return null;
	}

	public String htmContextIconName() {
		return contextIconName == null ? "" : StringEscapeUtils.escapeHtml4(strContextIconName());
	}

	///////////////////////////
	// contextIconCssClasses //
	///////////////////////////

	/**	L'entité « contextIconCssClasses »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String contextIconCssClasses;
	@JsonIgnore
	public Wrap<String> contextIconCssClassesWrap = new Wrap<String>().p(this).c(String.class).var("contextIconCssClasses").o(contextIconCssClasses);

	/**	<br/>L'entité « contextIconCssClasses »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:contextIconCssClasses">Trouver l'entité contextIconCssClasses dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _contextIconCssClasses(Wrap<String> c);

	public String getContextIconCssClasses() {
		return contextIconCssClasses;
	}

	public void setContextIconCssClasses(String contextIconCssClasses) {
		this.contextIconCssClasses = contextIconCssClasses;
		this.contextIconCssClassesWrap.alreadyInitialized = true;
	}
	protected PageLayout contextIconCssClassesInit() {
		if(!contextIconCssClassesWrap.alreadyInitialized) {
			_contextIconCssClasses(contextIconCssClassesWrap);
			if(contextIconCssClasses == null)
				setContextIconCssClasses(contextIconCssClassesWrap.o);
		}
		contextIconCssClassesWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrContextIconCssClasses() {
		return contextIconCssClasses;
	}

	public String strContextIconCssClasses() {
		return contextIconCssClasses == null ? "" : contextIconCssClasses;
	}

	public String jsonContextIconCssClasses() {
		return contextIconCssClasses == null ? "" : contextIconCssClasses;
	}

	public String nomAffichageContextIconCssClasses() {
		return null;
	}

	public String htmTooltipContextIconCssClasses() {
		return null;
	}

	public String htmContextIconCssClasses() {
		return contextIconCssClasses == null ? "" : StringEscapeUtils.escapeHtml4(strContextIconCssClasses());
	}

	///////////////////////
	// pageVisibleToBots //
	///////////////////////

	/**	L'entité « pageVisibleToBots »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean pageVisibleToBots;
	@JsonIgnore
	public Wrap<Boolean> pageVisibleToBotsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("pageVisibleToBots").o(pageVisibleToBots);

	/**	<br/>L'entité « pageVisibleToBots »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageVisibleToBots">Trouver l'entité pageVisibleToBots dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageVisibleToBots(Wrap<Boolean> c);

	public Boolean getPageVisibleToBots() {
		return pageVisibleToBots;
	}

	public void setPageVisibleToBots(Boolean pageVisibleToBots) {
		this.pageVisibleToBots = pageVisibleToBots;
		this.pageVisibleToBotsWrap.alreadyInitialized = true;
	}
	public PageLayout setPageVisibleToBots(String o) {
		this.pageVisibleToBots = Boolean.parseBoolean(o);
		this.pageVisibleToBotsWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	protected PageLayout pageVisibleToBotsInit() {
		if(!pageVisibleToBotsWrap.alreadyInitialized) {
			_pageVisibleToBots(pageVisibleToBotsWrap);
			if(pageVisibleToBots == null)
				setPageVisibleToBots(pageVisibleToBotsWrap.o);
		}
		pageVisibleToBotsWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public Boolean solrPageVisibleToBots() {
		return pageVisibleToBots;
	}

	public String strPageVisibleToBots() {
		return pageVisibleToBots == null ? "" : pageVisibleToBots.toString();
	}

	public String jsonPageVisibleToBots() {
		return pageVisibleToBots == null ? "" : pageVisibleToBots.toString();
	}

	public String nomAffichagePageVisibleToBots() {
		return null;
	}

	public String htmTooltipPageVisibleToBots() {
		return null;
	}

	public String htmPageVisibleToBots() {
		return pageVisibleToBots == null ? "" : StringEscapeUtils.escapeHtml4(strPageVisibleToBots());
	}

	////////////
	// pageH1 //
	////////////

	/**	L'entité « pageH1 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageH1;
	@JsonIgnore
	public Wrap<String> pageH1Wrap = new Wrap<String>().p(this).c(String.class).var("pageH1").o(pageH1);

	/**	<br/>L'entité « pageH1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageH1">Trouver l'entité pageH1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageH1(Wrap<String> c);

	public String getPageH1() {
		return pageH1;
	}

	public void setPageH1(String pageH1) {
		this.pageH1 = pageH1;
		this.pageH1Wrap.alreadyInitialized = true;
	}
	protected PageLayout pageH1Init() {
		if(!pageH1Wrap.alreadyInitialized) {
			_pageH1(pageH1Wrap);
			if(pageH1 == null)
				setPageH1(pageH1Wrap.o);
		}
		pageH1Wrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageH1() {
		return pageH1;
	}

	public String strPageH1() {
		return pageH1 == null ? "" : pageH1;
	}

	public String jsonPageH1() {
		return pageH1 == null ? "" : pageH1;
	}

	public String nomAffichagePageH1() {
		return null;
	}

	public String htmTooltipPageH1() {
		return null;
	}

	public String htmPageH1() {
		return pageH1 == null ? "" : StringEscapeUtils.escapeHtml4(strPageH1());
	}

	////////////
	// pageH2 //
	////////////

	/**	L'entité « pageH2 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageH2;
	@JsonIgnore
	public Wrap<String> pageH2Wrap = new Wrap<String>().p(this).c(String.class).var("pageH2").o(pageH2);

	/**	<br/>L'entité « pageH2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageH2">Trouver l'entité pageH2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageH2(Wrap<String> c);

	public String getPageH2() {
		return pageH2;
	}

	public void setPageH2(String pageH2) {
		this.pageH2 = pageH2;
		this.pageH2Wrap.alreadyInitialized = true;
	}
	protected PageLayout pageH2Init() {
		if(!pageH2Wrap.alreadyInitialized) {
			_pageH2(pageH2Wrap);
			if(pageH2 == null)
				setPageH2(pageH2Wrap.o);
		}
		pageH2Wrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageH2() {
		return pageH2;
	}

	public String strPageH2() {
		return pageH2 == null ? "" : pageH2;
	}

	public String jsonPageH2() {
		return pageH2 == null ? "" : pageH2;
	}

	public String nomAffichagePageH2() {
		return null;
	}

	public String htmTooltipPageH2() {
		return null;
	}

	public String htmPageH2() {
		return pageH2 == null ? "" : StringEscapeUtils.escapeHtml4(strPageH2());
	}

	////////////
	// pageH3 //
	////////////

	/**	L'entité « pageH3 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageH3;
	@JsonIgnore
	public Wrap<String> pageH3Wrap = new Wrap<String>().p(this).c(String.class).var("pageH3").o(pageH3);

	/**	<br/>L'entité « pageH3 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageH3">Trouver l'entité pageH3 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageH3(Wrap<String> c);

	public String getPageH3() {
		return pageH3;
	}

	public void setPageH3(String pageH3) {
		this.pageH3 = pageH3;
		this.pageH3Wrap.alreadyInitialized = true;
	}
	protected PageLayout pageH3Init() {
		if(!pageH3Wrap.alreadyInitialized) {
			_pageH3(pageH3Wrap);
			if(pageH3 == null)
				setPageH3(pageH3Wrap.o);
		}
		pageH3Wrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageH3() {
		return pageH3;
	}

	public String strPageH3() {
		return pageH3 == null ? "" : pageH3;
	}

	public String jsonPageH3() {
		return pageH3 == null ? "" : pageH3;
	}

	public String nomAffichagePageH3() {
		return null;
	}

	public String htmTooltipPageH3() {
		return null;
	}

	public String htmPageH3() {
		return pageH3 == null ? "" : StringEscapeUtils.escapeHtml4(strPageH3());
	}

	//////////////////
	// _pageH1Short //
	//////////////////

	/**	L'entité « _pageH1Short »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String _pageH1Short;
	@JsonIgnore
	public Wrap<String> _pageH1ShortWrap = new Wrap<String>().p(this).c(String.class).var("_pageH1Short").o(_pageH1Short);

	/**	<br/>L'entité « _pageH1Short »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageH1Short">Trouver l'entité _pageH1Short dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __pageH1Short(Wrap<String> c);

	public String get_pageH1Short() {
		return _pageH1Short;
	}

	public void set_pageH1Short(String _pageH1Short) {
		this._pageH1Short = _pageH1Short;
		this._pageH1ShortWrap.alreadyInitialized = true;
	}
	protected PageLayout _pageH1ShortInit() {
		if(!_pageH1ShortWrap.alreadyInitialized) {
			__pageH1Short(_pageH1ShortWrap);
			if(_pageH1Short == null)
				set_pageH1Short(_pageH1ShortWrap.o);
		}
		_pageH1ShortWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solr_pageH1Short() {
		return _pageH1Short;
	}

	public String str_pageH1Short() {
		return _pageH1Short == null ? "" : _pageH1Short;
	}

	public String json_pageH1Short() {
		return _pageH1Short == null ? "" : _pageH1Short;
	}

	public String nomAffichage_pageH1Short() {
		return null;
	}

	public String htmTooltip_pageH1Short() {
		return null;
	}

	public String htm_pageH1Short() {
		return _pageH1Short == null ? "" : StringEscapeUtils.escapeHtml4(str_pageH1Short());
	}

	//////////////////
	// _pageH2Short //
	//////////////////

	/**	L'entité « _pageH2Short »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String _pageH2Short;
	@JsonIgnore
	public Wrap<String> _pageH2ShortWrap = new Wrap<String>().p(this).c(String.class).var("_pageH2Short").o(_pageH2Short);

	/**	<br/>L'entité « _pageH2Short »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageH2Short">Trouver l'entité _pageH2Short dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __pageH2Short(Wrap<String> c);

	public String get_pageH2Short() {
		return _pageH2Short;
	}

	public void set_pageH2Short(String _pageH2Short) {
		this._pageH2Short = _pageH2Short;
		this._pageH2ShortWrap.alreadyInitialized = true;
	}
	protected PageLayout _pageH2ShortInit() {
		if(!_pageH2ShortWrap.alreadyInitialized) {
			__pageH2Short(_pageH2ShortWrap);
			if(_pageH2Short == null)
				set_pageH2Short(_pageH2ShortWrap.o);
		}
		_pageH2ShortWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solr_pageH2Short() {
		return _pageH2Short;
	}

	public String str_pageH2Short() {
		return _pageH2Short == null ? "" : _pageH2Short;
	}

	public String json_pageH2Short() {
		return _pageH2Short == null ? "" : _pageH2Short;
	}

	public String nomAffichage_pageH2Short() {
		return null;
	}

	public String htmTooltip_pageH2Short() {
		return null;
	}

	public String htm_pageH2Short() {
		return _pageH2Short == null ? "" : StringEscapeUtils.escapeHtml4(str_pageH2Short());
	}

	//////////////////
	// _pageH3Short //
	//////////////////

	/**	L'entité « _pageH3Short »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String _pageH3Short;
	@JsonIgnore
	public Wrap<String> _pageH3ShortWrap = new Wrap<String>().p(this).c(String.class).var("_pageH3Short").o(_pageH3Short);

	/**	<br/>L'entité « _pageH3Short »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageH3Short">Trouver l'entité _pageH3Short dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __pageH3Short(Wrap<String> c);

	public String get_pageH3Short() {
		return _pageH3Short;
	}

	public void set_pageH3Short(String _pageH3Short) {
		this._pageH3Short = _pageH3Short;
		this._pageH3ShortWrap.alreadyInitialized = true;
	}
	protected PageLayout _pageH3ShortInit() {
		if(!_pageH3ShortWrap.alreadyInitialized) {
			__pageH3Short(_pageH3ShortWrap);
			if(_pageH3Short == null)
				set_pageH3Short(_pageH3ShortWrap.o);
		}
		_pageH3ShortWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solr_pageH3Short() {
		return _pageH3Short;
	}

	public String str_pageH3Short() {
		return _pageH3Short == null ? "" : _pageH3Short;
	}

	public String json_pageH3Short() {
		return _pageH3Short == null ? "" : _pageH3Short;
	}

	public String nomAffichage_pageH3Short() {
		return null;
	}

	public String htmTooltip_pageH3Short() {
		return null;
	}

	public String htm_pageH3Short() {
		return _pageH3Short == null ? "" : StringEscapeUtils.escapeHtml4(str_pageH3Short());
	}

	///////////////
	// pageTitle //
	///////////////

	/**	L'entité « pageTitle »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageTitle;
	@JsonIgnore
	public Wrap<String> pageTitleWrap = new Wrap<String>().p(this).c(String.class).var("pageTitle").o(pageTitle);

	/**	<br/>L'entité « pageTitle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageTitle">Trouver l'entité pageTitle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageTitle(Wrap<String> c);

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
		this.pageTitleWrap.alreadyInitialized = true;
	}
	protected PageLayout pageTitleInit() {
		if(!pageTitleWrap.alreadyInitialized) {
			_pageTitle(pageTitleWrap);
			if(pageTitle == null)
				setPageTitle(pageTitleWrap.o);
		}
		pageTitleWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageTitle() {
		return pageTitle;
	}

	public String strPageTitle() {
		return pageTitle == null ? "" : pageTitle;
	}

	public String jsonPageTitle() {
		return pageTitle == null ? "" : pageTitle;
	}

	public String nomAffichagePageTitle() {
		return null;
	}

	public String htmTooltipPageTitle() {
		return null;
	}

	public String htmPageTitle() {
		return pageTitle == null ? "" : StringEscapeUtils.escapeHtml4(strPageTitle());
	}

	/////////////
	// pageUri //
	/////////////

	/**	L'entité « pageUri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageUri;
	@JsonIgnore
	public Wrap<String> pageUriWrap = new Wrap<String>().p(this).c(String.class).var("pageUri").o(pageUri);

	/**	<br/>L'entité « pageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUri">Trouver l'entité pageUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUri(Wrap<String> c);

	public String getPageUri() {
		return pageUri;
	}

	public void setPageUri(String pageUri) {
		this.pageUri = pageUri;
		this.pageUriWrap.alreadyInitialized = true;
	}
	protected PageLayout pageUriInit() {
		if(!pageUriWrap.alreadyInitialized) {
			_pageUri(pageUriWrap);
			if(pageUri == null)
				setPageUri(pageUriWrap.o);
		}
		pageUriWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageUri() {
		return pageUri;
	}

	public String strPageUri() {
		return pageUri == null ? "" : pageUri;
	}

	public String jsonPageUri() {
		return pageUri == null ? "" : pageUri;
	}

	public String nomAffichagePageUri() {
		return null;
	}

	public String htmTooltipPageUri() {
		return null;
	}

	public String htmPageUri() {
		return pageUri == null ? "" : StringEscapeUtils.escapeHtml4(strPageUri());
	}

	//////////////
	// pageUris //
	//////////////

	/**	L'entité « pageUris »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> pageUris = new ArrayList<String>();
	@JsonIgnore
	public Wrap<List<String>> pageUrisWrap = new Wrap<List<String>>().p(this).c(List.class).var("pageUris").o(pageUris);

	/**	<br/>L'entité « pageUris »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUris">Trouver l'entité pageUris dans Solr</a>
	 * <br/>
	 * @param pageUris est l'entité déjà construit. 
	 **/
	protected abstract void _pageUris(List<String> l);

	public List<String> getPageUris() {
		return pageUris;
	}

	public void setPageUris(List<String> pageUris) {
		this.pageUris = pageUris;
		this.pageUrisWrap.alreadyInitialized = true;
	}
	public PageLayout addPageUris(String...objets) {
		for(String o : objets) {
			addPageUris(o);
		}
		return (PageLayout)this;
	}
	public PageLayout addPageUris(String o) {
		if(o != null && !pageUris.contains(o))
			this.pageUris.add(o);
		return (PageLayout)this;
	}
	public PageLayout setPageUris(JsonArray objets) {
		pageUris.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addPageUris(o);
		}
		return (PageLayout)this;
	}
	protected PageLayout pageUrisInit() {
		if(!pageUrisWrap.alreadyInitialized) {
			_pageUris(pageUris);
		}
		pageUrisWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public List<String> solrPageUris() {
		return pageUris;
	}

	public String strPageUris() {
		return pageUris == null ? "" : pageUris.toString();
	}

	public String jsonPageUris() {
		return pageUris == null ? "" : pageUris.toString();
	}

	public String nomAffichagePageUris() {
		return null;
	}

	public String htmTooltipPageUris() {
		return null;
	}

	public String htmPageUris() {
		return pageUris == null ? "" : StringEscapeUtils.escapeHtml4(strPageUris());
	}

	/////////////
	// pageUrl //
	/////////////

	/**	L'entité « pageUrl »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageUrl;
	@JsonIgnore
	public Wrap<String> pageUrlWrap = new Wrap<String>().p(this).c(String.class).var("pageUrl").o(pageUrl);

	/**	<br/>L'entité « pageUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUrl(Wrap<String> c);

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
		this.pageUrlWrap.alreadyInitialized = true;
	}
	protected PageLayout pageUrlInit() {
		if(!pageUrlWrap.alreadyInitialized) {
			_pageUrl(pageUrlWrap);
			if(pageUrl == null)
				setPageUrl(pageUrlWrap.o);
		}
		pageUrlWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageUrl() {
		return pageUrl;
	}

	public String strPageUrl() {
		return pageUrl == null ? "" : pageUrl;
	}

	public String jsonPageUrl() {
		return pageUrl == null ? "" : pageUrl;
	}

	public String nomAffichagePageUrl() {
		return null;
	}

	public String htmTooltipPageUrl() {
		return null;
	}

	public String htmPageUrl() {
		return pageUrl == null ? "" : StringEscapeUtils.escapeHtml4(strPageUrl());
	}

	//////////////////
	// pageImageUri //
	//////////////////

	/**	L'entité « pageImageUri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageImageUri;
	@JsonIgnore
	public Wrap<String> pageImageUriWrap = new Wrap<String>().p(this).c(String.class).var("pageImageUri").o(pageImageUri);

	/**	<br/>L'entité « pageImageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageImageUri">Trouver l'entité pageImageUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageImageUri(Wrap<String> c);

	public String getPageImageUri() {
		return pageImageUri;
	}

	public void setPageImageUri(String pageImageUri) {
		this.pageImageUri = pageImageUri;
		this.pageImageUriWrap.alreadyInitialized = true;
	}
	protected PageLayout pageImageUriInit() {
		if(!pageImageUriWrap.alreadyInitialized) {
			_pageImageUri(pageImageUriWrap);
			if(pageImageUri == null)
				setPageImageUri(pageImageUriWrap.o);
		}
		pageImageUriWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageImageUri() {
		return pageImageUri;
	}

	public String strPageImageUri() {
		return pageImageUri == null ? "" : pageImageUri;
	}

	public String jsonPageImageUri() {
		return pageImageUri == null ? "" : pageImageUri;
	}

	public String nomAffichagePageImageUri() {
		return null;
	}

	public String htmTooltipPageImageUri() {
		return null;
	}

	public String htmPageImageUri() {
		return pageImageUri == null ? "" : StringEscapeUtils.escapeHtml4(strPageImageUri());
	}

	//////////////////
	// pageImageUrl //
	//////////////////

	/**	L'entité « pageImageUrl »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageImageUrl;
	@JsonIgnore
	public Wrap<String> pageImageUrlWrap = new Wrap<String>().p(this).c(String.class).var("pageImageUrl").o(pageImageUrl);

	/**	<br/>L'entité « pageImageUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageImageUrl">Trouver l'entité pageImageUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageImageUrl(Wrap<String> c);

	public String getPageImageUrl() {
		return pageImageUrl;
	}

	public void setPageImageUrl(String pageImageUrl) {
		this.pageImageUrl = pageImageUrl;
		this.pageImageUrlWrap.alreadyInitialized = true;
	}
	protected PageLayout pageImageUrlInit() {
		if(!pageImageUrlWrap.alreadyInitialized) {
			_pageImageUrl(pageImageUrlWrap);
			if(pageImageUrl == null)
				setPageImageUrl(pageImageUrlWrap.o);
		}
		pageImageUrlWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageImageUrl() {
		return pageImageUrl;
	}

	public String strPageImageUrl() {
		return pageImageUrl == null ? "" : pageImageUrl;
	}

	public String jsonPageImageUrl() {
		return pageImageUrl == null ? "" : pageImageUrl;
	}

	public String nomAffichagePageImageUrl() {
		return null;
	}

	public String htmTooltipPageImageUrl() {
		return null;
	}

	public String htmPageImageUrl() {
		return pageImageUrl == null ? "" : StringEscapeUtils.escapeHtml4(strPageImageUrl());
	}

	/////////////////
	// pageVideoId //
	/////////////////

	/**	L'entité « pageVideoId »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageVideoId;
	@JsonIgnore
	public Wrap<String> pageVideoIdWrap = new Wrap<String>().p(this).c(String.class).var("pageVideoId").o(pageVideoId);

	/**	<br/>L'entité « pageVideoId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageVideoId">Trouver l'entité pageVideoId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageVideoId(Wrap<String> c);

	public String getPageVideoId() {
		return pageVideoId;
	}

	public void setPageVideoId(String pageVideoId) {
		this.pageVideoId = pageVideoId;
		this.pageVideoIdWrap.alreadyInitialized = true;
	}
	protected PageLayout pageVideoIdInit() {
		if(!pageVideoIdWrap.alreadyInitialized) {
			_pageVideoId(pageVideoIdWrap);
			if(pageVideoId == null)
				setPageVideoId(pageVideoIdWrap.o);
		}
		pageVideoIdWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageVideoId() {
		return pageVideoId;
	}

	public String strPageVideoId() {
		return pageVideoId == null ? "" : pageVideoId;
	}

	public String jsonPageVideoId() {
		return pageVideoId == null ? "" : pageVideoId;
	}

	public String nomAffichagePageVideoId() {
		return null;
	}

	public String htmTooltipPageVideoId() {
		return null;
	}

	public String htmPageVideoId() {
		return pageVideoId == null ? "" : StringEscapeUtils.escapeHtml4(strPageVideoId());
	}

	//////////////////
	// pageVideoUrl //
	//////////////////

	/**	L'entité « pageVideoUrl »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageVideoUrl;
	@JsonIgnore
	public Wrap<String> pageVideoUrlWrap = new Wrap<String>().p(this).c(String.class).var("pageVideoUrl").o(pageVideoUrl);

	/**	<br/>L'entité « pageVideoUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageVideoUrl">Trouver l'entité pageVideoUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageVideoUrl(Wrap<String> c);

	public String getPageVideoUrl() {
		return pageVideoUrl;
	}

	public void setPageVideoUrl(String pageVideoUrl) {
		this.pageVideoUrl = pageVideoUrl;
		this.pageVideoUrlWrap.alreadyInitialized = true;
	}
	protected PageLayout pageVideoUrlInit() {
		if(!pageVideoUrlWrap.alreadyInitialized) {
			_pageVideoUrl(pageVideoUrlWrap);
			if(pageVideoUrl == null)
				setPageVideoUrl(pageVideoUrlWrap.o);
		}
		pageVideoUrlWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageVideoUrl() {
		return pageVideoUrl;
	}

	public String strPageVideoUrl() {
		return pageVideoUrl == null ? "" : pageVideoUrl;
	}

	public String jsonPageVideoUrl() {
		return pageVideoUrl == null ? "" : pageVideoUrl;
	}

	public String nomAffichagePageVideoUrl() {
		return null;
	}

	public String htmTooltipPageVideoUrl() {
		return null;
	}

	public String htmPageVideoUrl() {
		return pageVideoUrl == null ? "" : StringEscapeUtils.escapeHtml4(strPageVideoUrl());
	}

	///////////////////////
	// pageVideoUrlEmbed //
	///////////////////////

	/**	L'entité « pageVideoUrlEmbed »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageVideoUrlEmbed;
	@JsonIgnore
	public Wrap<String> pageVideoUrlEmbedWrap = new Wrap<String>().p(this).c(String.class).var("pageVideoUrlEmbed").o(pageVideoUrlEmbed);

	/**	<br/>L'entité « pageVideoUrlEmbed »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageVideoUrlEmbed">Trouver l'entité pageVideoUrlEmbed dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageVideoUrlEmbed(Wrap<String> c);

	public String getPageVideoUrlEmbed() {
		return pageVideoUrlEmbed;
	}

	public void setPageVideoUrlEmbed(String pageVideoUrlEmbed) {
		this.pageVideoUrlEmbed = pageVideoUrlEmbed;
		this.pageVideoUrlEmbedWrap.alreadyInitialized = true;
	}
	protected PageLayout pageVideoUrlEmbedInit() {
		if(!pageVideoUrlEmbedWrap.alreadyInitialized) {
			_pageVideoUrlEmbed(pageVideoUrlEmbedWrap);
			if(pageVideoUrlEmbed == null)
				setPageVideoUrlEmbed(pageVideoUrlEmbedWrap.o);
		}
		pageVideoUrlEmbedWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageVideoUrlEmbed() {
		return pageVideoUrlEmbed;
	}

	public String strPageVideoUrlEmbed() {
		return pageVideoUrlEmbed == null ? "" : pageVideoUrlEmbed;
	}

	public String jsonPageVideoUrlEmbed() {
		return pageVideoUrlEmbed == null ? "" : pageVideoUrlEmbed;
	}

	public String nomAffichagePageVideoUrlEmbed() {
		return null;
	}

	public String htmTooltipPageVideoUrlEmbed() {
		return null;
	}

	public String htmPageVideoUrlEmbed() {
		return pageVideoUrlEmbed == null ? "" : StringEscapeUtils.escapeHtml4(strPageVideoUrlEmbed());
	}

	////////////////////
	// pageImageWidth //
	////////////////////

	/**	L'entité « pageImageWidth »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer pageImageWidth;
	@JsonIgnore
	public Wrap<Integer> pageImageWidthWrap = new Wrap<Integer>().p(this).c(Integer.class).var("pageImageWidth").o(pageImageWidth);

	/**	<br/>L'entité « pageImageWidth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageImageWidth">Trouver l'entité pageImageWidth dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageImageWidth(Wrap<Integer> c);

	public Integer getPageImageWidth() {
		return pageImageWidth;
	}

	public void setPageImageWidth(Integer pageImageWidth) {
		this.pageImageWidth = pageImageWidth;
		this.pageImageWidthWrap.alreadyInitialized = true;
	}
	public PageLayout setPageImageWidth(String o) {
		if(NumberUtils.isParsable(o))
			this.pageImageWidth = Integer.parseInt(o);
		this.pageImageWidthWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	protected PageLayout pageImageWidthInit() {
		if(!pageImageWidthWrap.alreadyInitialized) {
			_pageImageWidth(pageImageWidthWrap);
			if(pageImageWidth == null)
				setPageImageWidth(pageImageWidthWrap.o);
		}
		pageImageWidthWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public Integer solrPageImageWidth() {
		return pageImageWidth;
	}

	public String strPageImageWidth() {
		return pageImageWidth == null ? "" : pageImageWidth.toString();
	}

	public String jsonPageImageWidth() {
		return pageImageWidth == null ? "" : pageImageWidth.toString();
	}

	public String nomAffichagePageImageWidth() {
		return null;
	}

	public String htmTooltipPageImageWidth() {
		return null;
	}

	public String htmPageImageWidth() {
		return pageImageWidth == null ? "" : StringEscapeUtils.escapeHtml4(strPageImageWidth());
	}

	/////////////////////
	// pageImageHeight //
	/////////////////////

	/**	L'entité « pageImageHeight »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer pageImageHeight;
	@JsonIgnore
	public Wrap<Integer> pageImageHeightWrap = new Wrap<Integer>().p(this).c(Integer.class).var("pageImageHeight").o(pageImageHeight);

	/**	<br/>L'entité « pageImageHeight »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageImageHeight">Trouver l'entité pageImageHeight dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageImageHeight(Wrap<Integer> c);

	public Integer getPageImageHeight() {
		return pageImageHeight;
	}

	public void setPageImageHeight(Integer pageImageHeight) {
		this.pageImageHeight = pageImageHeight;
		this.pageImageHeightWrap.alreadyInitialized = true;
	}
	public PageLayout setPageImageHeight(String o) {
		if(NumberUtils.isParsable(o))
			this.pageImageHeight = Integer.parseInt(o);
		this.pageImageHeightWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	protected PageLayout pageImageHeightInit() {
		if(!pageImageHeightWrap.alreadyInitialized) {
			_pageImageHeight(pageImageHeightWrap);
			if(pageImageHeight == null)
				setPageImageHeight(pageImageHeightWrap.o);
		}
		pageImageHeightWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public Integer solrPageImageHeight() {
		return pageImageHeight;
	}

	public String strPageImageHeight() {
		return pageImageHeight == null ? "" : pageImageHeight.toString();
	}

	public String jsonPageImageHeight() {
		return pageImageHeight == null ? "" : pageImageHeight.toString();
	}

	public String nomAffichagePageImageHeight() {
		return null;
	}

	public String htmTooltipPageImageHeight() {
		return null;
	}

	public String htmPageImageHeight() {
		return pageImageHeight == null ? "" : StringEscapeUtils.escapeHtml4(strPageImageHeight());
	}

	//////////////////////////
	// pageImageContentType //
	//////////////////////////

	/**	L'entité « pageImageContentType »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageImageContentType;
	@JsonIgnore
	public Wrap<String> pageImageContentTypeWrap = new Wrap<String>().p(this).c(String.class).var("pageImageContentType").o(pageImageContentType);

	/**	<br/>L'entité « pageImageContentType »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageImageContentType">Trouver l'entité pageImageContentType dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageImageContentType(Wrap<String> c);

	public String getPageImageContentType() {
		return pageImageContentType;
	}

	public void setPageImageContentType(String pageImageContentType) {
		this.pageImageContentType = pageImageContentType;
		this.pageImageContentTypeWrap.alreadyInitialized = true;
	}
	protected PageLayout pageImageContentTypeInit() {
		if(!pageImageContentTypeWrap.alreadyInitialized) {
			_pageImageContentType(pageImageContentTypeWrap);
			if(pageImageContentType == null)
				setPageImageContentType(pageImageContentTypeWrap.o);
		}
		pageImageContentTypeWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageImageContentType() {
		return pageImageContentType;
	}

	public String strPageImageContentType() {
		return pageImageContentType == null ? "" : pageImageContentType;
	}

	public String jsonPageImageContentType() {
		return pageImageContentType == null ? "" : pageImageContentType;
	}

	public String nomAffichagePageImageContentType() {
		return null;
	}

	public String htmTooltipPageImageContentType() {
		return null;
	}

	public String htmPageImageContentType() {
		return pageImageContentType == null ? "" : StringEscapeUtils.escapeHtml4(strPageImageContentType());
	}

	/////////////////////
	// pageContentType //
	/////////////////////

	/**	L'entité « pageContentType »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageContentType;
	@JsonIgnore
	public Wrap<String> pageContentTypeWrap = new Wrap<String>().p(this).c(String.class).var("pageContentType").o(pageContentType);

	/**	<br/>L'entité « pageContentType »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageContentType">Trouver l'entité pageContentType dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageContentType(Wrap<String> c);

	public String getPageContentType() {
		return pageContentType;
	}

	public void setPageContentType(String pageContentType) {
		this.pageContentType = pageContentType;
		this.pageContentTypeWrap.alreadyInitialized = true;
	}
	protected PageLayout pageContentTypeInit() {
		if(!pageContentTypeWrap.alreadyInitialized) {
			_pageContentType(pageContentTypeWrap);
			if(pageContentType == null)
				setPageContentType(pageContentTypeWrap.o);
		}
		pageContentTypeWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageContentType() {
		return pageContentType;
	}

	public String strPageContentType() {
		return pageContentType == null ? "" : pageContentType;
	}

	public String jsonPageContentType() {
		return pageContentType == null ? "" : pageContentType;
	}

	public String nomAffichagePageContentType() {
		return null;
	}

	public String htmTooltipPageContentType() {
		return null;
	}

	public String htmPageContentType() {
		return pageContentType == null ? "" : StringEscapeUtils.escapeHtml4(strPageContentType());
	}

	/////////////////
	// pageCreated //
	/////////////////

	/**	L'entité « pageCreated »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected LocalDateTime pageCreated;
	@JsonIgnore
	public Wrap<LocalDateTime> pageCreatedWrap = new Wrap<LocalDateTime>().p(this).c(LocalDateTime.class).var("pageCreated").o(pageCreated);

	/**	<br/>L'entité « pageCreated »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageCreated">Trouver l'entité pageCreated dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageCreated(Wrap<LocalDateTime> c);

	public LocalDateTime getPageCreated() {
		return pageCreated;
	}

	public void setPageCreated(LocalDateTime pageCreated) {
		this.pageCreated = pageCreated;
		this.pageCreatedWrap.alreadyInitialized = true;
	}
	public PageLayout setPageCreated(Instant o) {
		this.pageCreated = o == null ? null : LocalDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
		this.pageCreatedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public PageLayout setPageCreated(String o) {
		this.pageCreated = o == null ? null : LocalDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME).truncatedTo(ChronoUnit.MILLIS);
		this.pageCreatedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	public PageLayout setPageCreated(Date o) {
		this.pageCreated = o == null ? null : LocalDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).truncatedTo(ChronoUnit.MILLIS);
		this.pageCreatedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	protected PageLayout pageCreatedInit() {
		if(!pageCreatedWrap.alreadyInitialized) {
			_pageCreated(pageCreatedWrap);
			if(pageCreated == null)
				setPageCreated(pageCreatedWrap.o);
		}
		pageCreatedWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public Date solrPageCreated() {
		return pageCreated == null ? null : Date.from(pageCreated.atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strPageCreated() {
		return pageCreated == null ? "" : pageCreated.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy H:mm:ss a zz", Locale.forLanguageTag("en-US")));
	}

	public String jsonPageCreated() {
		return pageCreated == null ? "" : pageCreated.format(DateTimeFormatter.ISO_DATE_TIME);
	}

	public String nomAffichagePageCreated() {
		return null;
	}

	public String htmTooltipPageCreated() {
		return null;
	}

	public String htmPageCreated() {
		return pageCreated == null ? "" : StringEscapeUtils.escapeHtml4(strPageCreated());
	}

	//////////////////
	// pageModified //
	//////////////////

	/**	L'entité « pageModified »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected LocalDateTime pageModified;
	@JsonIgnore
	public Wrap<LocalDateTime> pageModifiedWrap = new Wrap<LocalDateTime>().p(this).c(LocalDateTime.class).var("pageModified").o(pageModified);

	/**	<br/>L'entité « pageModified »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageModified">Trouver l'entité pageModified dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageModified(Wrap<LocalDateTime> c);

	public LocalDateTime getPageModified() {
		return pageModified;
	}

	public void setPageModified(LocalDateTime pageModified) {
		this.pageModified = pageModified;
		this.pageModifiedWrap.alreadyInitialized = true;
	}
	public PageLayout setPageModified(Instant o) {
		this.pageModified = o == null ? null : LocalDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);
		this.pageModifiedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public PageLayout setPageModified(String o) {
		this.pageModified = o == null ? null : LocalDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME).truncatedTo(ChronoUnit.MILLIS);
		this.pageModifiedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	public PageLayout setPageModified(Date o) {
		this.pageModified = o == null ? null : LocalDateTime.ofInstant(o.toInstant(), ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).truncatedTo(ChronoUnit.MILLIS);
		this.pageModifiedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	protected PageLayout pageModifiedInit() {
		if(!pageModifiedWrap.alreadyInitialized) {
			_pageModified(pageModifiedWrap);
			if(pageModified == null)
				setPageModified(pageModifiedWrap.o);
		}
		pageModifiedWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public Date solrPageModified() {
		return pageModified == null ? null : Date.from(pageModified.atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strPageModified() {
		return pageModified == null ? "" : pageModified.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy H:mm:ss a zz", Locale.forLanguageTag("en-US")));
	}

	public String jsonPageModified() {
		return pageModified == null ? "" : pageModified.format(DateTimeFormatter.ISO_DATE_TIME);
	}

	public String nomAffichagePageModified() {
		return null;
	}

	public String htmTooltipPageModified() {
		return null;
	}

	public String htmPageModified() {
		return pageModified == null ? "" : StringEscapeUtils.escapeHtml4(strPageModified());
	}

	//////////////////
	// pageKeywords //
	//////////////////

	/**	L'entité « pageKeywords »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageKeywords;
	@JsonIgnore
	public Wrap<String> pageKeywordsWrap = new Wrap<String>().p(this).c(String.class).var("pageKeywords").o(pageKeywords);

	/**	<br/>L'entité « pageKeywords »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageKeywords">Trouver l'entité pageKeywords dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageKeywords(Wrap<String> c);

	public String getPageKeywords() {
		return pageKeywords;
	}

	public void setPageKeywords(String pageKeywords) {
		this.pageKeywords = pageKeywords;
		this.pageKeywordsWrap.alreadyInitialized = true;
	}
	protected PageLayout pageKeywordsInit() {
		if(!pageKeywordsWrap.alreadyInitialized) {
			_pageKeywords(pageKeywordsWrap);
			if(pageKeywords == null)
				setPageKeywords(pageKeywordsWrap.o);
		}
		pageKeywordsWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageKeywords() {
		return pageKeywords;
	}

	public String strPageKeywords() {
		return pageKeywords == null ? "" : pageKeywords;
	}

	public String jsonPageKeywords() {
		return pageKeywords == null ? "" : pageKeywords;
	}

	public String nomAffichagePageKeywords() {
		return null;
	}

	public String htmTooltipPageKeywords() {
		return null;
	}

	public String htmPageKeywords() {
		return pageKeywords == null ? "" : StringEscapeUtils.escapeHtml4(strPageKeywords());
	}

	/////////////////////
	// pageDescription //
	/////////////////////

	/**	L'entité « pageDescription »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageDescription;
	@JsonIgnore
	public Wrap<String> pageDescriptionWrap = new Wrap<String>().p(this).c(String.class).var("pageDescription").o(pageDescription);

	/**	<br/>L'entité « pageDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDescription">Trouver l'entité pageDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageDescription(Wrap<String> c);

	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
		this.pageDescriptionWrap.alreadyInitialized = true;
	}
	protected PageLayout pageDescriptionInit() {
		if(!pageDescriptionWrap.alreadyInitialized) {
			_pageDescription(pageDescriptionWrap);
			if(pageDescription == null)
				setPageDescription(pageDescriptionWrap.o);
		}
		pageDescriptionWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageDescription() {
		return pageDescription;
	}

	public String strPageDescription() {
		return pageDescription == null ? "" : pageDescription;
	}

	public String jsonPageDescription() {
		return pageDescription == null ? "" : pageDescription;
	}

	public String nomAffichagePageDescription() {
		return null;
	}

	public String htmTooltipPageDescription() {
		return null;
	}

	public String htmPageDescription() {
		return pageDescription == null ? "" : StringEscapeUtils.escapeHtml4(strPageDescription());
	}

	/////////////////
	// pageHomeUri //
	/////////////////

	/**	L'entité « pageHomeUri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageHomeUri;
	@JsonIgnore
	public Wrap<String> pageHomeUriWrap = new Wrap<String>().p(this).c(String.class).var("pageHomeUri").o(pageHomeUri);

	/**	<br/>L'entité « pageHomeUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageHomeUri">Trouver l'entité pageHomeUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageHomeUri(Wrap<String> c);

	public String getPageHomeUri() {
		return pageHomeUri;
	}

	public void setPageHomeUri(String pageHomeUri) {
		this.pageHomeUri = pageHomeUri;
		this.pageHomeUriWrap.alreadyInitialized = true;
	}
	protected PageLayout pageHomeUriInit() {
		if(!pageHomeUriWrap.alreadyInitialized) {
			_pageHomeUri(pageHomeUriWrap);
			if(pageHomeUri == null)
				setPageHomeUri(pageHomeUriWrap.o);
		}
		pageHomeUriWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageHomeUri() {
		return pageHomeUri;
	}

	public String strPageHomeUri() {
		return pageHomeUri == null ? "" : pageHomeUri;
	}

	public String jsonPageHomeUri() {
		return pageHomeUri == null ? "" : pageHomeUri;
	}

	public String nomAffichagePageHomeUri() {
		return null;
	}

	public String htmTooltipPageHomeUri() {
		return null;
	}

	public String htmPageHomeUri() {
		return pageHomeUri == null ? "" : StringEscapeUtils.escapeHtml4(strPageHomeUri());
	}

	///////////////////
	// pageSchoolUri //
	///////////////////

	/**	L'entité « pageSchoolUri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageSchoolUri;
	@JsonIgnore
	public Wrap<String> pageSchoolUriWrap = new Wrap<String>().p(this).c(String.class).var("pageSchoolUri").o(pageSchoolUri);

	/**	<br/>L'entité « pageSchoolUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageSchoolUri">Trouver l'entité pageSchoolUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageSchoolUri(Wrap<String> c);

	public String getPageSchoolUri() {
		return pageSchoolUri;
	}

	public void setPageSchoolUri(String pageSchoolUri) {
		this.pageSchoolUri = pageSchoolUri;
		this.pageSchoolUriWrap.alreadyInitialized = true;
	}
	protected PageLayout pageSchoolUriInit() {
		if(!pageSchoolUriWrap.alreadyInitialized) {
			_pageSchoolUri(pageSchoolUriWrap);
			if(pageSchoolUri == null)
				setPageSchoolUri(pageSchoolUriWrap.o);
		}
		pageSchoolUriWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageSchoolUri() {
		return pageSchoolUri;
	}

	public String strPageSchoolUri() {
		return pageSchoolUri == null ? "" : pageSchoolUri;
	}

	public String jsonPageSchoolUri() {
		return pageSchoolUri == null ? "" : pageSchoolUri;
	}

	public String nomAffichagePageSchoolUri() {
		return null;
	}

	public String htmTooltipPageSchoolUri() {
		return null;
	}

	public String htmPageSchoolUri() {
		return pageSchoolUri == null ? "" : StringEscapeUtils.escapeHtml4(strPageSchoolUri());
	}

	/////////////////
	// pageUserUri //
	/////////////////

	/**	L'entité « pageUserUri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageUserUri;
	@JsonIgnore
	public Wrap<String> pageUserUriWrap = new Wrap<String>().p(this).c(String.class).var("pageUserUri").o(pageUserUri);

	/**	<br/>L'entité « pageUserUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUserUri">Trouver l'entité pageUserUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUserUri(Wrap<String> c);

	public String getPageUserUri() {
		return pageUserUri;
	}

	public void setPageUserUri(String pageUserUri) {
		this.pageUserUri = pageUserUri;
		this.pageUserUriWrap.alreadyInitialized = true;
	}
	protected PageLayout pageUserUriInit() {
		if(!pageUserUriWrap.alreadyInitialized) {
			_pageUserUri(pageUserUriWrap);
			if(pageUserUri == null)
				setPageUserUri(pageUserUriWrap.o);
		}
		pageUserUriWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageUserUri() {
		return pageUserUri;
	}

	public String strPageUserUri() {
		return pageUserUri == null ? "" : pageUserUri;
	}

	public String jsonPageUserUri() {
		return pageUserUri == null ? "" : pageUserUri;
	}

	public String nomAffichagePageUserUri() {
		return null;
	}

	public String htmTooltipPageUserUri() {
		return null;
	}

	public String htmPageUserUri() {
		return pageUserUri == null ? "" : StringEscapeUtils.escapeHtml4(strPageUserUri());
	}

	///////////////////
	// pageLogoutUri //
	///////////////////

	/**	L'entité « pageLogoutUri »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageLogoutUri;
	@JsonIgnore
	public Wrap<String> pageLogoutUriWrap = new Wrap<String>().p(this).c(String.class).var("pageLogoutUri").o(pageLogoutUri);

	/**	<br/>L'entité « pageLogoutUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageLogoutUri">Trouver l'entité pageLogoutUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageLogoutUri(Wrap<String> c);

	public String getPageLogoutUri() {
		return pageLogoutUri;
	}

	public void setPageLogoutUri(String pageLogoutUri) {
		this.pageLogoutUri = pageLogoutUri;
		this.pageLogoutUriWrap.alreadyInitialized = true;
	}
	protected PageLayout pageLogoutUriInit() {
		if(!pageLogoutUriWrap.alreadyInitialized) {
			_pageLogoutUri(pageLogoutUriWrap);
			if(pageLogoutUri == null)
				setPageLogoutUri(pageLogoutUriWrap.o);
		}
		pageLogoutUriWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageLogoutUri() {
		return pageLogoutUri;
	}

	public String strPageLogoutUri() {
		return pageLogoutUri == null ? "" : pageLogoutUri;
	}

	public String jsonPageLogoutUri() {
		return pageLogoutUri == null ? "" : pageLogoutUri;
	}

	public String nomAffichagePageLogoutUri() {
		return null;
	}

	public String htmTooltipPageLogoutUri() {
		return null;
	}

	public String htmPageLogoutUri() {
		return pageLogoutUri == null ? "" : StringEscapeUtils.escapeHtml4(strPageLogoutUri());
	}

	////////////////
	// listSchool //
	////////////////

	/**	L'entité « listSchool »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<School>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<School> listSchool = new SearchList<School>();
	@JsonIgnore
	public Wrap<SearchList<School>> listSchoolWrap = new Wrap<SearchList<School>>().p(this).c(SearchList.class).var("listSchool").o(listSchool);

	/**	<br/>L'entité « listSchool »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<School>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchool">Trouver l'entité listSchool dans Solr</a>
	 * <br/>
	 * @param listSchool est l'entité déjà construit. 
	 **/
	protected abstract void _listSchool(SearchList<School> l);

	public SearchList<School> getListSchool() {
		return listSchool;
	}

	public void setListSchool(SearchList<School> listSchool) {
		this.listSchool = listSchool;
		this.listSchoolWrap.alreadyInitialized = true;
	}
	protected PageLayout listSchoolInit() {
		if(!listSchoolWrap.alreadyInitialized) {
			_listSchool(listSchool);
		}
		listSchool.initDeepForClass(siteRequest_);
		listSchoolWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	/////////////
	// schools //
	/////////////

	/**	L'entité « schools »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<School> schools;
	@JsonIgnore
	public Wrap<List<School>> schoolsWrap = new Wrap<List<School>>().p(this).c(List.class).var("schools").o(schools);

	/**	<br/>L'entité « schools »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schools">Trouver l'entité schools dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schools(Wrap<List<School>> c);

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
		this.schoolsWrap.alreadyInitialized = true;
	}
	public PageLayout addSchools(School...objets) {
		for(School o : objets) {
			addSchools(o);
		}
		return (PageLayout)this;
	}
	public PageLayout addSchools(School o) {
		if(o != null && !schools.contains(o))
			this.schools.add(o);
		return (PageLayout)this;
	}
	protected PageLayout schoolsInit() {
		if(!schoolsWrap.alreadyInitialized) {
			_schools(schoolsWrap);
			if(schools == null)
				setSchools(schoolsWrap.o);
		}
		schoolsWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	/////////////
	// school_ //
	/////////////

	/**	L'entité « school_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected School school_;
	@JsonIgnore
	public Wrap<School> school_Wrap = new Wrap<School>().p(this).c(School.class).var("school_").o(school_);

	/**	<br/>L'entité « school_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:school_">Trouver l'entité school_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _school_(Wrap<School> c);

	public School getSchool_() {
		return school_;
	}

	public void setSchool_(School school_) {
		this.school_ = school_;
		this.school_Wrap.alreadyInitialized = true;
	}
	protected PageLayout school_Init() {
		if(!school_Wrap.alreadyInitialized) {
			_school_(school_Wrap);
			if(school_ == null)
				setSchool_(school_Wrap.o);
		}
		school_Wrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	/////////////
	// yearVal //
	/////////////

	/**	L'entité « yearVal »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearVal;
	@JsonIgnore
	public Wrap<Integer> yearValWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearVal").o(yearVal);

	/**	<br/>L'entité « yearVal »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearVal">Trouver l'entité yearVal dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearVal(Wrap<Integer> c);

	public Integer getYearVal() {
		return yearVal;
	}

	public void setYearVal(Integer yearVal) {
		this.yearVal = yearVal;
		this.yearValWrap.alreadyInitialized = true;
	}
	public PageLayout setYearVal(String o) {
		if(NumberUtils.isParsable(o))
			this.yearVal = Integer.parseInt(o);
		this.yearValWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	protected PageLayout yearValInit() {
		if(!yearValWrap.alreadyInitialized) {
			_yearVal(yearValWrap);
			if(yearVal == null)
				setYearVal(yearValWrap.o);
		}
		yearValWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public Integer solrYearVal() {
		return yearVal;
	}

	public String strYearVal() {
		return yearVal == null ? "" : yearVal.toString();
	}

	public String jsonYearVal() {
		return yearVal == null ? "" : yearVal.toString();
	}

	public String nomAffichageYearVal() {
		return null;
	}

	public String htmTooltipYearVal() {
		return null;
	}

	public String htmYearVal() {
		return yearVal == null ? "" : StringEscapeUtils.escapeHtml4(strYearVal());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedPageLayout = false;

	public PageLayout initDeepPageLayout(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedPageLayout) {
			alreadyInitializedPageLayout = true;
			initDeepPageLayout();
		}
		return (PageLayout)this;
	}

	public void initDeepPageLayout() {
		initPageLayout();
	}

	public void initPageLayout() {
		pagePartsInit();
		siteRequest_Init();
		siteBaseUrlInit();
		staticBaseUrlInit();
		pageSolrDocumentInit();
		wInit();
		contextIconGroupInit();
		contextIconNameInit();
		contextIconCssClassesInit();
		pageVisibleToBotsInit();
		pageH1Init();
		pageH2Init();
		pageH3Init();
		_pageH1ShortInit();
		_pageH2ShortInit();
		_pageH3ShortInit();
		pageTitleInit();
		pageUriInit();
		pageUrisInit();
		pageUrlInit();
		pageImageUriInit();
		pageImageUrlInit();
		pageVideoIdInit();
		pageVideoUrlInit();
		pageVideoUrlEmbedInit();
		pageImageWidthInit();
		pageImageHeightInit();
		pageImageContentTypeInit();
		pageContentTypeInit();
		pageCreatedInit();
		pageModifiedInit();
		pageKeywordsInit();
		pageDescriptionInit();
		pageHomeUriInit();
		pageSchoolUriInit();
		pageUserUriInit();
		pageLogoutUriInit();
		listSchoolInit();
		schoolsInit();
		school_Init();
		yearValInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepPageLayout(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestPageLayout(SiteRequestEnUS siteRequest_) {
		if(w != null)
			w.setSiteRequest_(siteRequest_);
		if(listSchool != null)
			listSchool.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestPageLayout(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainPageLayout(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainPageLayout(String var) {
		PageLayout oPageLayout = (PageLayout)this;
		switch(var) {
			case "pageParts":
				return oPageLayout.pageParts;
			case "siteRequest_":
				return oPageLayout.siteRequest_;
			case "siteBaseUrl":
				return oPageLayout.siteBaseUrl;
			case "staticBaseUrl":
				return oPageLayout.staticBaseUrl;
			case "pageSolrDocument":
				return oPageLayout.pageSolrDocument;
			case "w":
				return oPageLayout.w;
			case "contextIconGroup":
				return oPageLayout.contextIconGroup;
			case "contextIconName":
				return oPageLayout.contextIconName;
			case "contextIconCssClasses":
				return oPageLayout.contextIconCssClasses;
			case "pageVisibleToBots":
				return oPageLayout.pageVisibleToBots;
			case "pageH1":
				return oPageLayout.pageH1;
			case "pageH2":
				return oPageLayout.pageH2;
			case "pageH3":
				return oPageLayout.pageH3;
			case "_pageH1Short":
				return oPageLayout._pageH1Short;
			case "_pageH2Short":
				return oPageLayout._pageH2Short;
			case "_pageH3Short":
				return oPageLayout._pageH3Short;
			case "pageTitle":
				return oPageLayout.pageTitle;
			case "pageUri":
				return oPageLayout.pageUri;
			case "pageUris":
				return oPageLayout.pageUris;
			case "pageUrl":
				return oPageLayout.pageUrl;
			case "pageImageUri":
				return oPageLayout.pageImageUri;
			case "pageImageUrl":
				return oPageLayout.pageImageUrl;
			case "pageVideoId":
				return oPageLayout.pageVideoId;
			case "pageVideoUrl":
				return oPageLayout.pageVideoUrl;
			case "pageVideoUrlEmbed":
				return oPageLayout.pageVideoUrlEmbed;
			case "pageImageWidth":
				return oPageLayout.pageImageWidth;
			case "pageImageHeight":
				return oPageLayout.pageImageHeight;
			case "pageImageContentType":
				return oPageLayout.pageImageContentType;
			case "pageContentType":
				return oPageLayout.pageContentType;
			case "pageCreated":
				return oPageLayout.pageCreated;
			case "pageModified":
				return oPageLayout.pageModified;
			case "pageKeywords":
				return oPageLayout.pageKeywords;
			case "pageDescription":
				return oPageLayout.pageDescription;
			case "pageHomeUri":
				return oPageLayout.pageHomeUri;
			case "pageSchoolUri":
				return oPageLayout.pageSchoolUri;
			case "pageUserUri":
				return oPageLayout.pageUserUri;
			case "pageLogoutUri":
				return oPageLayout.pageLogoutUri;
			case "listSchool":
				return oPageLayout.listSchool;
			case "schools":
				return oPageLayout.schools;
			case "school_":
				return oPageLayout.school_;
			case "yearVal":
				return oPageLayout.yearVal;
			default:
				return null;
		}
	}

	///////////////
	// attribute //
	///////////////

	public boolean attributeForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attributePageLayout(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributePageLayout(String var, Object val) {
		PageLayout oPageLayout = (PageLayout)this;
		switch(var) {
			default:
				return null;
		}
	}

	/////////////
	// define //
	/////////////

	public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definePageLayout(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definePageLayout(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////
	// html //
	//////////

	public void html() {
		htmlPageLayout();
	}

	public void htmlPageLayout() {
	}

	//////////////
	// htmlMeta //
	//////////////

	public void htmlMeta() {
		htmlMetaPageLayout();
	}

	public void htmlMetaPageLayout() {
	}

	/////////////////
	// htmlScripts //
	/////////////////

	public void htmlScripts() {
		htmlScriptsPageLayout();
	}

	public void htmlScriptsPageLayout() {
	}

	////////////////
	// htmlScript //
	////////////////

	public void htmlScript() {
		htmlScriptPageLayout();
	}

	public void htmlScriptPageLayout() {
	}

	////////////////
	// htmlStyles //
	////////////////

	public void htmlStyles() {
		htmlStylesPageLayout();
	}

	public void htmlStylesPageLayout() {
	}

	///////////////
	// htmlStyle //
	///////////////

	public void htmlStyle() {
		htmlStylePageLayout();
	}

	public void htmlStylePageLayout() {
	}

	//////////////
	// htmlBody //
	//////////////

	public void htmlBody() {
		htmlBodyPageLayout();
	}

	public void htmlBodyPageLayout() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestPageLayout() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof PageLayout) {
			PageLayout original = (PageLayout)o;
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash();
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof PageLayout))
			return false;
		PageLayout that = (PageLayout)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PageLayout { ");
		sb.append(" }");
		return sb.toString();
	}
}
