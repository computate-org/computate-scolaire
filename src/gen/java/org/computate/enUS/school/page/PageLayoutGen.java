package org.computate.enUS.school.page;

import org.computate.scolaire.frFR.page.parti.PagePart;
import java.util.Date;
import java.time.ZonedDateTime;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.time.LocalDateTime;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import java.time.Instant;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.time.ZoneId;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.lang.Boolean;
import java.lang.Object;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import java.time.ZoneOffset;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PageLayoutGen<DEV> extends Object {

	///////////////
	// pageParts //
	///////////////

	/**	L'entité « pageParts »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<PagePart>(). 
	 */
	protected List<PagePart> pageParts = new java.util.ArrayList<org.computate.scolaire.frFR.page.parti.PagePart>();
	public Wrap<List<PagePart>> pagePartsWrap = new Wrap<List<PagePart>>().p(this).c(List.class).var("pageParts").o(pageParts);

	/**	<br/>L'entité « pageParts »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<PagePart>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageParts">Trouver l'entité pageParts dans Solr</a>
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
	public abstract void avantPagePart(PagePart o, String entiteVar);
	protected PageLayout pagePartsInit() {
		if(!pagePartsWrap.alreadyInitialized) {
			_pageParts(pageParts);
		}
		pagePartsWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteRequestEnUS requeteSite_;
	public Wrap<SiteRequestEnUS> requeteSite_Wrap = new Wrap<SiteRequestEnUS>().p(this).c(SiteRequestEnUS.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteSite_(Wrap<SiteRequestEnUS> c);

	public SiteRequestEnUS getRequeteSite_() {
		return requeteSite_;
	}

	public void setRequeteSite_(SiteRequestEnUS requeteSite_) {
		this.requeteSite_ = requeteSite_;
		this.requeteSite_Wrap.alreadyInitialized = true;
	}
	protected PageLayout requeteSite_Init() {
		if(!requeteSite_Wrap.alreadyInitialized) {
			_requeteSite_(requeteSite_Wrap);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Wrap.o);
		}
		requeteSite_Wrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	/////////////////////
	// statiqueUrlBase //
	/////////////////////

	/**	L'entité « statiqueUrlBase »
	 *	 is defined as null before being initialized. 
	 */
	protected String statiqueUrlBase;
	public Wrap<String> statiqueUrlBaseWrap = new Wrap<String>().p(this).c(String.class).var("statiqueUrlBase").o(statiqueUrlBase);

	/**	<br/>L'entité « statiqueUrlBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:statiqueUrlBase">Trouver l'entité statiqueUrlBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _statiqueUrlBase(Wrap<String> c);

	public String getStatiqueUrlBase() {
		return statiqueUrlBase;
	}

	public void setStatiqueUrlBase(String statiqueUrlBase) {
		this.statiqueUrlBase = statiqueUrlBase;
		this.statiqueUrlBaseWrap.alreadyInitialized = true;
	}
	protected PageLayout statiqueUrlBaseInit() {
		if(!statiqueUrlBaseWrap.alreadyInitialized) {
			_statiqueUrlBase(statiqueUrlBaseWrap);
			if(statiqueUrlBase == null)
				setStatiqueUrlBase(statiqueUrlBaseWrap.o);
		}
		statiqueUrlBaseWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrStatiqueUrlBase() {
		return statiqueUrlBase;
	}

	public String strStatiqueUrlBase() {
		return statiqueUrlBase == null ? "" : statiqueUrlBase;
	}

	public String nomAffichageStatiqueUrlBase() {
		return null;
	}

	public String htmTooltipStatiqueUrlBase() {
		return null;
	}

	public String htmStatiqueUrlBase() {
		return statiqueUrlBase == null ? "" : StringEscapeUtils.escapeHtml4(strStatiqueUrlBase());
	}

	//////////////////////
	// pageDocumentSolr //
	//////////////////////

	/**	L'entité « pageDocumentSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected SolrDocument pageDocumentSolr;
	public Wrap<SolrDocument> pageDocumentSolrWrap = new Wrap<SolrDocument>().p(this).c(SolrDocument.class).var("pageDocumentSolr").o(pageDocumentSolr);

	/**	<br/>L'entité « pageDocumentSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDocumentSolr">Trouver l'entité pageDocumentSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageDocumentSolr(Wrap<SolrDocument> c);

	public SolrDocument getPageDocumentSolr() {
		return pageDocumentSolr;
	}

	public void setPageDocumentSolr(SolrDocument pageDocumentSolr) {
		this.pageDocumentSolr = pageDocumentSolr;
		this.pageDocumentSolrWrap.alreadyInitialized = true;
	}
	protected PageLayout pageDocumentSolrInit() {
		if(!pageDocumentSolrWrap.alreadyInitialized) {
			_pageDocumentSolr(pageDocumentSolrWrap);
			if(pageDocumentSolr == null)
				setPageDocumentSolr(pageDocumentSolrWrap.o);
		}
		pageDocumentSolrWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	/////////////
	// _writer //
	/////////////

	/**	L'entité « _writer »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain _writer;
	public Wrap<ToutEcrivain> _writerWrap = new Wrap<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("_writer").o(_writer);

	/**	<br/>L'entité « _writer »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_writer">Trouver l'entité _writer dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __writer(Wrap<ToutEcrivain> c);

	public ToutEcrivain get_writer() {
		return _writer;
	}

	public void set_writer(ToutEcrivain _writer) {
		this._writer = _writer;
		this._writerWrap.alreadyInitialized = true;
	}
	protected PageLayout _writerInit() {
		if(!_writerWrap.alreadyInitialized) {
			__writer(_writerWrap);
			if(_writer == null)
				set_writer(_writerWrap.o);
		}
		if(_writer != null)
			_writer.initDeepForClass(siteRequest_);
		_writerWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	/////////////////////////
	// contexteIconeGroupe //
	/////////////////////////

	/**	L'entité « contexteIconeGroupe »
	 *	 is defined as null before being initialized. 
	 */
	protected String contexteIconeGroupe;
	public Wrap<String> contexteIconeGroupeWrap = new Wrap<String>().p(this).c(String.class).var("contexteIconeGroupe").o(contexteIconeGroupe);

	/**	<br/>L'entité « contexteIconeGroupe »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:contexteIconeGroupe">Trouver l'entité contexteIconeGroupe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _contexteIconeGroupe(Wrap<String> c);

	public String getContexteIconeGroupe() {
		return contexteIconeGroupe;
	}

	public void setContexteIconeGroupe(String contexteIconeGroupe) {
		this.contexteIconeGroupe = contexteIconeGroupe;
		this.contexteIconeGroupeWrap.alreadyInitialized = true;
	}
	protected PageLayout contexteIconeGroupeInit() {
		if(!contexteIconeGroupeWrap.alreadyInitialized) {
			_contexteIconeGroupe(contexteIconeGroupeWrap);
			if(contexteIconeGroupe == null)
				setContexteIconeGroupe(contexteIconeGroupeWrap.o);
		}
		contexteIconeGroupeWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrContexteIconeGroupe() {
		return contexteIconeGroupe;
	}

	public String strContexteIconeGroupe() {
		return contexteIconeGroupe == null ? "" : contexteIconeGroupe;
	}

	public String nomAffichageContexteIconeGroupe() {
		return null;
	}

	public String htmTooltipContexteIconeGroupe() {
		return null;
	}

	public String htmContexteIconeGroupe() {
		return contexteIconeGroupe == null ? "" : StringEscapeUtils.escapeHtml4(strContexteIconeGroupe());
	}

	//////////////////////
	// contexteIconeNom //
	//////////////////////

	/**	L'entité « contexteIconeNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String contexteIconeNom;
	public Wrap<String> contexteIconeNomWrap = new Wrap<String>().p(this).c(String.class).var("contexteIconeNom").o(contexteIconeNom);

	/**	<br/>L'entité « contexteIconeNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:contexteIconeNom">Trouver l'entité contexteIconeNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _contexteIconeNom(Wrap<String> c);

	public String getContexteIconeNom() {
		return contexteIconeNom;
	}

	public void setContexteIconeNom(String contexteIconeNom) {
		this.contexteIconeNom = contexteIconeNom;
		this.contexteIconeNomWrap.alreadyInitialized = true;
	}
	protected PageLayout contexteIconeNomInit() {
		if(!contexteIconeNomWrap.alreadyInitialized) {
			_contexteIconeNom(contexteIconeNomWrap);
			if(contexteIconeNom == null)
				setContexteIconeNom(contexteIconeNomWrap.o);
		}
		contexteIconeNomWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrContexteIconeNom() {
		return contexteIconeNom;
	}

	public String strContexteIconeNom() {
		return contexteIconeNom == null ? "" : contexteIconeNom;
	}

	public String nomAffichageContexteIconeNom() {
		return null;
	}

	public String htmTooltipContexteIconeNom() {
		return null;
	}

	public String htmContexteIconeNom() {
		return contexteIconeNom == null ? "" : StringEscapeUtils.escapeHtml4(strContexteIconeNom());
	}

	/////////////////////////////
	// contexteIconeClassesCss //
	/////////////////////////////

	/**	L'entité « contexteIconeClassesCss »
	 *	 is defined as null before being initialized. 
	 */
	protected String contexteIconeClassesCss;
	public Wrap<String> contexteIconeClassesCssWrap = new Wrap<String>().p(this).c(String.class).var("contexteIconeClassesCss").o(contexteIconeClassesCss);

	/**	<br/>L'entité « contexteIconeClassesCss »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:contexteIconeClassesCss">Trouver l'entité contexteIconeClassesCss dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _contexteIconeClassesCss(Wrap<String> c);

	public String getContexteIconeClassesCss() {
		return contexteIconeClassesCss;
	}

	public void setContexteIconeClassesCss(String contexteIconeClassesCss) {
		this.contexteIconeClassesCss = contexteIconeClassesCss;
		this.contexteIconeClassesCssWrap.alreadyInitialized = true;
	}
	protected PageLayout contexteIconeClassesCssInit() {
		if(!contexteIconeClassesCssWrap.alreadyInitialized) {
			_contexteIconeClassesCss(contexteIconeClassesCssWrap);
			if(contexteIconeClassesCss == null)
				setContexteIconeClassesCss(contexteIconeClassesCssWrap.o);
		}
		contexteIconeClassesCssWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrContexteIconeClassesCss() {
		return contexteIconeClassesCss;
	}

	public String strContexteIconeClassesCss() {
		return contexteIconeClassesCss == null ? "" : contexteIconeClassesCss;
	}

	public String nomAffichageContexteIconeClassesCss() {
		return null;
	}

	public String htmTooltipContexteIconeClassesCss() {
		return null;
	}

	public String htmContexteIconeClassesCss() {
		return contexteIconeClassesCss == null ? "" : StringEscapeUtils.escapeHtml4(strContexteIconeClassesCss());
	}

	///////////////////////
	// pageVisibleToBots //
	///////////////////////

	/**	L'entité « pageVisibleToBots »
	 *	 is defined as null before being initialized. 
	 */
	protected Boolean pageVisibleToBots;
	public Wrap<Boolean> pageVisibleToBotsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("pageVisibleToBots").o(pageVisibleToBots);

	/**	<br/>L'entité « pageVisibleToBots »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageVisibleToBots">Trouver l'entité pageVisibleToBots dans Solr</a>
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
	protected String pageH1;
	public Wrap<String> pageH1Wrap = new Wrap<String>().p(this).c(String.class).var("pageH1").o(pageH1);

	/**	<br/>L'entité « pageH1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageH1">Trouver l'entité pageH1 dans Solr</a>
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
	protected String pageH2;
	public Wrap<String> pageH2Wrap = new Wrap<String>().p(this).c(String.class).var("pageH2").o(pageH2);

	/**	<br/>L'entité « pageH2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageH2">Trouver l'entité pageH2 dans Solr</a>
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
	protected String pageH3;
	public Wrap<String> pageH3Wrap = new Wrap<String>().p(this).c(String.class).var("pageH3").o(pageH3);

	/**	<br/>L'entité « pageH3 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageH3">Trouver l'entité pageH3 dans Solr</a>
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
	protected String _pageH1Short;
	public Wrap<String> _pageH1ShortWrap = new Wrap<String>().p(this).c(String.class).var("_pageH1Short").o(_pageH1Short);

	/**	<br/>L'entité « _pageH1Short »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageH1Short">Trouver l'entité _pageH1Short dans Solr</a>
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
	protected String _pageH2Short;
	public Wrap<String> _pageH2ShortWrap = new Wrap<String>().p(this).c(String.class).var("_pageH2Short").o(_pageH2Short);

	/**	<br/>L'entité « _pageH2Short »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageH2Short">Trouver l'entité _pageH2Short dans Solr</a>
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
	// _pageH2Short //
	//////////////////

	/**	L'entité « _pageH2Short »
	 *	 is defined as null before being initialized. 
	 */
	protected String _pageH2Short;
	public Wrap<String> _pageH2ShortWrap = new Wrap<String>().p(this).c(String.class).var("_pageH2Short").o(_pageH2Short);

	/**	<br/>L'entité « _pageH2Short »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageH2Short">Trouver l'entité _pageH2Short dans Solr</a>
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

	public String nomAffichage_pageH2Short() {
		return null;
	}

	public String htmTooltip_pageH2Short() {
		return null;
	}

	public String htm_pageH2Short() {
		return _pageH2Short == null ? "" : StringEscapeUtils.escapeHtml4(str_pageH2Short());
	}

	///////////////
	// pageTitle //
	///////////////

	/**	L'entité « pageTitle »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageTitle;
	public Wrap<String> pageTitleWrap = new Wrap<String>().p(this).c(String.class).var("pageTitle").o(pageTitle);

	/**	<br/>L'entité « pageTitle »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageTitle">Trouver l'entité pageTitle dans Solr</a>
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
	protected String pageUri;
	public Wrap<String> pageUriWrap = new Wrap<String>().p(this).c(String.class).var("pageUri").o(pageUri);

	/**	<br/>L'entité « pageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUri">Trouver l'entité pageUri dans Solr</a>
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

	public String nomAffichagePageUri() {
		return null;
	}

	public String htmTooltipPageUri() {
		return null;
	}

	public String htmPageUri() {
		return pageUri == null ? "" : StringEscapeUtils.escapeHtml4(strPageUri());
	}

	/////////////////
	// pageUriFrFR //
	/////////////////

	/**	L'entité « pageUriFrFR »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUriFrFR;
	public Wrap<String> pageUriFrFRWrap = new Wrap<String>().p(this).c(String.class).var("pageUriFrFR").o(pageUriFrFR);

	/**	<br/>L'entité « pageUriFrFR »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUriFrFR">Trouver l'entité pageUriFrFR dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUriFrFR(Wrap<String> c);

	public String getPageUriFrFR() {
		return pageUriFrFR;
	}

	public void setPageUriFrFR(String pageUriFrFR) {
		this.pageUriFrFR = pageUriFrFR;
		this.pageUriFrFRWrap.alreadyInitialized = true;
	}
	protected PageLayout pageUriFrFRInit() {
		if(!pageUriFrFRWrap.alreadyInitialized) {
			_pageUriFrFR(pageUriFrFRWrap);
			if(pageUriFrFR == null)
				setPageUriFrFR(pageUriFrFRWrap.o);
		}
		pageUriFrFRWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageUriFrFR() {
		return pageUriFrFR;
	}

	public String strPageUriFrFR() {
		return pageUriFrFR == null ? "" : pageUriFrFR;
	}

	public String nomAffichagePageUriFrFR() {
		return null;
	}

	public String htmTooltipPageUriFrFR() {
		return null;
	}

	public String htmPageUriFrFR() {
		return pageUriFrFR == null ? "" : StringEscapeUtils.escapeHtml4(strPageUriFrFR());
	}

	//////////////
	// pageUris //
	//////////////

	/**	L'entité « pageUris »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 */
	protected List<String> pageUris = new java.util.ArrayList<java.lang.String>();
	public Wrap<List<String>> pageUrisWrap = new Wrap<List<String>>().p(this).c(List.class).var("pageUris").o(pageUris);

	/**	<br/>L'entité « pageUris »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<String>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUris">Trouver l'entité pageUris dans Solr</a>
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
	protected String pageUrl;
	public Wrap<String> pageUrlWrap = new Wrap<String>().p(this).c(String.class).var("pageUrl").o(pageUrl);

	/**	<br/>L'entité « pageUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUrl">Trouver l'entité pageUrl dans Solr</a>
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
	protected String pageImageUri;
	public Wrap<String> pageImageUriWrap = new Wrap<String>().p(this).c(String.class).var("pageImageUri").o(pageImageUri);

	/**	<br/>L'entité « pageImageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageImageUri">Trouver l'entité pageImageUri dans Solr</a>
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
	protected String pageImageUrl;
	public Wrap<String> pageImageUrlWrap = new Wrap<String>().p(this).c(String.class).var("pageImageUrl").o(pageImageUrl);

	/**	<br/>L'entité « pageImageUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageImageUrl">Trouver l'entité pageImageUrl dans Solr</a>
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
	protected String pageVideoId;
	public Wrap<String> pageVideoIdWrap = new Wrap<String>().p(this).c(String.class).var("pageVideoId").o(pageVideoId);

	/**	<br/>L'entité « pageVideoId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageVideoId">Trouver l'entité pageVideoId dans Solr</a>
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
	protected String pageVideoUrl;
	public Wrap<String> pageVideoUrlWrap = new Wrap<String>().p(this).c(String.class).var("pageVideoUrl").o(pageVideoUrl);

	/**	<br/>L'entité « pageVideoUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageVideoUrl">Trouver l'entité pageVideoUrl dans Solr</a>
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
	protected String pageVideoUrlEmbed;
	public Wrap<String> pageVideoUrlEmbedWrap = new Wrap<String>().p(this).c(String.class).var("pageVideoUrlEmbed").o(pageVideoUrlEmbed);

	/**	<br/>L'entité « pageVideoUrlEmbed »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageVideoUrlEmbed">Trouver l'entité pageVideoUrlEmbed dans Solr</a>
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

	public String nomAffichagePageVideoUrlEmbed() {
		return null;
	}

	public String htmTooltipPageVideoUrlEmbed() {
		return null;
	}

	public String htmPageVideoUrlEmbed() {
		return pageVideoUrlEmbed == null ? "" : StringEscapeUtils.escapeHtml4(strPageVideoUrlEmbed());
	}

	/////////////////////
	// _pageImageWidth //
	/////////////////////

	/**	L'entité « _pageImageWidth »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer _pageImageWidth;
	public Wrap<Integer> _pageImageWidthWrap = new Wrap<Integer>().p(this).c(Integer.class).var("_pageImageWidth").o(_pageImageWidth);

	/**	<br/>L'entité « _pageImageWidth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageImageWidth">Trouver l'entité _pageImageWidth dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __pageImageWidth(Wrap<Integer> c);

	public Integer get_pageImageWidth() {
		return _pageImageWidth;
	}

	public void set_pageImageWidth(Integer _pageImageWidth) {
		this._pageImageWidth = _pageImageWidth;
		this._pageImageWidthWrap.alreadyInitialized = true;
	}
	public PageLayout set_pageImageWidth(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this._pageImageWidth = Integer.parseInt(o);
		this._pageImageWidthWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	protected PageLayout _pageImageWidthInit() {
		if(!_pageImageWidthWrap.alreadyInitialized) {
			__pageImageWidth(_pageImageWidthWrap);
			if(_pageImageWidth == null)
				set_pageImageWidth(_pageImageWidthWrap.o);
		}
		_pageImageWidthWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public Integer solr_pageImageWidth() {
		return _pageImageWidth;
	}

	public String str_pageImageWidth() {
		return _pageImageWidth == null ? "" : _pageImageWidth.toString();
	}

	public String nomAffichage_pageImageWidth() {
		return null;
	}

	public String htmTooltip_pageImageWidth() {
		return null;
	}

	public String htm_pageImageWidth() {
		return _pageImageWidth == null ? "" : StringEscapeUtils.escapeHtml4(str_pageImageWidth());
	}

	//////////////////////
	// _pageImageHeight //
	//////////////////////

	/**	L'entité « _pageImageHeight »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer _pageImageHeight;
	public Wrap<Integer> _pageImageHeightWrap = new Wrap<Integer>().p(this).c(Integer.class).var("_pageImageHeight").o(_pageImageHeight);

	/**	<br/>L'entité « _pageImageHeight »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageImageHeight">Trouver l'entité _pageImageHeight dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __pageImageHeight(Wrap<Integer> c);

	public Integer get_pageImageHeight() {
		return _pageImageHeight;
	}

	public void set_pageImageHeight(Integer _pageImageHeight) {
		this._pageImageHeight = _pageImageHeight;
		this._pageImageHeightWrap.alreadyInitialized = true;
	}
	public PageLayout set_pageImageHeight(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this._pageImageHeight = Integer.parseInt(o);
		this._pageImageHeightWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	protected PageLayout _pageImageHeightInit() {
		if(!_pageImageHeightWrap.alreadyInitialized) {
			__pageImageHeight(_pageImageHeightWrap);
			if(_pageImageHeight == null)
				set_pageImageHeight(_pageImageHeightWrap.o);
		}
		_pageImageHeightWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public Integer solr_pageImageHeight() {
		return _pageImageHeight;
	}

	public String str_pageImageHeight() {
		return _pageImageHeight == null ? "" : _pageImageHeight.toString();
	}

	public String nomAffichage_pageImageHeight() {
		return null;
	}

	public String htmTooltip_pageImageHeight() {
		return null;
	}

	public String htm_pageImageHeight() {
		return _pageImageHeight == null ? "" : StringEscapeUtils.escapeHtml4(str_pageImageHeight());
	}

	///////////////////////////
	// _pageImageContentType //
	///////////////////////////

	/**	L'entité « _pageImageContentType »
	 *	 is defined as null before being initialized. 
	 */
	protected String _pageImageContentType;
	public Wrap<String> _pageImageContentTypeWrap = new Wrap<String>().p(this).c(String.class).var("_pageImageContentType").o(_pageImageContentType);

	/**	<br/>L'entité « _pageImageContentType »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageImageContentType">Trouver l'entité _pageImageContentType dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __pageImageContentType(Wrap<String> c);

	public String get_pageImageContentType() {
		return _pageImageContentType;
	}

	public void set_pageImageContentType(String _pageImageContentType) {
		this._pageImageContentType = _pageImageContentType;
		this._pageImageContentTypeWrap.alreadyInitialized = true;
	}
	protected PageLayout _pageImageContentTypeInit() {
		if(!_pageImageContentTypeWrap.alreadyInitialized) {
			__pageImageContentType(_pageImageContentTypeWrap);
			if(_pageImageContentType == null)
				set_pageImageContentType(_pageImageContentTypeWrap.o);
		}
		_pageImageContentTypeWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solr_pageImageContentType() {
		return _pageImageContentType;
	}

	public String str_pageImageContentType() {
		return _pageImageContentType == null ? "" : _pageImageContentType;
	}

	public String nomAffichage_pageImageContentType() {
		return null;
	}

	public String htmTooltip_pageImageContentType() {
		return null;
	}

	public String htm_pageImageContentType() {
		return _pageImageContentType == null ? "" : StringEscapeUtils.escapeHtml4(str_pageImageContentType());
	}

	//////////////////////
	// _pageContentType //
	//////////////////////

	/**	L'entité « _pageContentType »
	 *	 is defined as null before being initialized. 
	 */
	protected String _pageContentType;
	public Wrap<String> _pageContentTypeWrap = new Wrap<String>().p(this).c(String.class).var("_pageContentType").o(_pageContentType);

	/**	<br/>L'entité « _pageContentType »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageContentType">Trouver l'entité _pageContentType dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __pageContentType(Wrap<String> c);

	public String get_pageContentType() {
		return _pageContentType;
	}

	public void set_pageContentType(String _pageContentType) {
		this._pageContentType = _pageContentType;
		this._pageContentTypeWrap.alreadyInitialized = true;
	}
	protected PageLayout _pageContentTypeInit() {
		if(!_pageContentTypeWrap.alreadyInitialized) {
			__pageContentType(_pageContentTypeWrap);
			if(_pageContentType == null)
				set_pageContentType(_pageContentTypeWrap.o);
		}
		_pageContentTypeWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solr_pageContentType() {
		return _pageContentType;
	}

	public String str_pageContentType() {
		return _pageContentType == null ? "" : _pageContentType;
	}

	public String nomAffichage_pageContentType() {
		return null;
	}

	public String htmTooltip_pageContentType() {
		return null;
	}

	public String htm_pageContentType() {
		return _pageContentType == null ? "" : StringEscapeUtils.escapeHtml4(str_pageContentType());
	}

	//////////////////
	// _pageCreated //
	//////////////////

	/**	L'entité « _pageCreated »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDateTime _pageCreated;
	public Wrap<LocalDateTime> _pageCreatedWrap = new Wrap<LocalDateTime>().p(this).c(LocalDateTime.class).var("_pageCreated").o(_pageCreated);

	/**	<br/>L'entité « _pageCreated »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageCreated">Trouver l'entité _pageCreated dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __pageCreated(Wrap<LocalDateTime> c);

	public LocalDateTime get_pageCreated() {
		return _pageCreated;
	}

	public void set_pageCreated(LocalDateTime _pageCreated) {
		this._pageCreated = _pageCreated;
		this._pageCreatedWrap.alreadyInitialized = true;
	}
	public PageLayout set_pageCreated(Instant o) {
		this._pageCreated = LocalDateTime.from(o);
		this._pageCreatedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public PageLayout set_pageCreated(String o) {
		this._pageCreated = LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		this._pageCreatedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	public PageLayout set_pageCreated(Date o) {
		this._pageCreated = LocalDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());
		this._pageCreatedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	protected PageLayout _pageCreatedInit() {
		if(!_pageCreatedWrap.alreadyInitialized) {
			__pageCreated(_pageCreatedWrap);
			if(_pageCreated == null)
				set_pageCreated(_pageCreatedWrap.o);
		}
		_pageCreatedWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public Date solr_pageCreated() {
		return _pageCreated == null ? null : Date.from(_pageCreated.atZone(ZoneId.systemDefault()).toInstant());
	}

	public String str_pageCreated() {
		return _pageCreated == null ? "" : _pageCreated.toString();
	}

	public String nomAffichage_pageCreated() {
		return null;
	}

	public String htmTooltip_pageCreated() {
		return null;
	}

	public String htm_pageCreated() {
		return _pageCreated == null ? "" : StringEscapeUtils.escapeHtml4(str_pageCreated());
	}

	///////////////////
	// _pageModified //
	///////////////////

	/**	L'entité « _pageModified »
	 *	 is defined as null before being initialized. 
	 */
	protected LocalDateTime _pageModified;
	public Wrap<LocalDateTime> _pageModifiedWrap = new Wrap<LocalDateTime>().p(this).c(LocalDateTime.class).var("_pageModified").o(_pageModified);

	/**	<br/>L'entité « _pageModified »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageModified">Trouver l'entité _pageModified dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __pageModified(Wrap<LocalDateTime> c);

	public LocalDateTime get_pageModified() {
		return _pageModified;
	}

	public void set_pageModified(LocalDateTime _pageModified) {
		this._pageModified = _pageModified;
		this._pageModifiedWrap.alreadyInitialized = true;
	}
	public PageLayout set_pageModified(Instant o) {
		this._pageModified = LocalDateTime.from(o);
		this._pageModifiedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	/** Example: 2011-12-03T10:15:30+01:00 **/
	public PageLayout set_pageModified(String o) {
		this._pageModified = LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		this._pageModifiedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	public PageLayout set_pageModified(Date o) {
		this._pageModified = LocalDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());
		this._pageModifiedWrap.alreadyInitialized = true;
		return (PageLayout)this;
	}
	protected PageLayout _pageModifiedInit() {
		if(!_pageModifiedWrap.alreadyInitialized) {
			__pageModified(_pageModifiedWrap);
			if(_pageModified == null)
				set_pageModified(_pageModifiedWrap.o);
		}
		_pageModifiedWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public Date solr_pageModified() {
		return _pageModified == null ? null : Date.from(_pageModified.atZone(ZoneId.systemDefault()).toInstant());
	}

	public String str_pageModified() {
		return _pageModified == null ? "" : _pageModified.toString();
	}

	public String nomAffichage_pageModified() {
		return null;
	}

	public String htmTooltip_pageModified() {
		return null;
	}

	public String htm_pageModified() {
		return _pageModified == null ? "" : StringEscapeUtils.escapeHtml4(str_pageModified());
	}

	///////////////////
	// _pageKeywords //
	///////////////////

	/**	L'entité « _pageKeywords »
	 *	 is defined as null before being initialized. 
	 */
	protected String _pageKeywords;
	public Wrap<String> _pageKeywordsWrap = new Wrap<String>().p(this).c(String.class).var("_pageKeywords").o(_pageKeywords);

	/**	<br/>L'entité « _pageKeywords »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageKeywords">Trouver l'entité _pageKeywords dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __pageKeywords(Wrap<String> c);

	public String get_pageKeywords() {
		return _pageKeywords;
	}

	public void set_pageKeywords(String _pageKeywords) {
		this._pageKeywords = _pageKeywords;
		this._pageKeywordsWrap.alreadyInitialized = true;
	}
	protected PageLayout _pageKeywordsInit() {
		if(!_pageKeywordsWrap.alreadyInitialized) {
			__pageKeywords(_pageKeywordsWrap);
			if(_pageKeywords == null)
				set_pageKeywords(_pageKeywordsWrap.o);
		}
		_pageKeywordsWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solr_pageKeywords() {
		return _pageKeywords;
	}

	public String str_pageKeywords() {
		return _pageKeywords == null ? "" : _pageKeywords;
	}

	public String nomAffichage_pageKeywords() {
		return null;
	}

	public String htmTooltip_pageKeywords() {
		return null;
	}

	public String htm_pageKeywords() {
		return _pageKeywords == null ? "" : StringEscapeUtils.escapeHtml4(str_pageKeywords());
	}

	/////////////////////
	// pageDescription //
	/////////////////////

	/**	L'entité « pageDescription »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageDescription;
	public Wrap<String> pageDescriptionWrap = new Wrap<String>().p(this).c(String.class).var("pageDescription").o(pageDescription);

	/**	<br/>L'entité « pageDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDescription">Trouver l'entité pageDescription dans Solr</a>
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

	public String nomAffichagePageDescription() {
		return null;
	}

	public String htmTooltipPageDescription() {
		return null;
	}

	public String htmPageDescription() {
		return pageDescription == null ? "" : StringEscapeUtils.escapeHtml4(strPageDescription());
	}

	//////////////////
	// _homePageUri //
	//////////////////

	/**	L'entité « _homePageUri »
	 *	 is defined as null before being initialized. 
	 */
	protected String _homePageUri;
	public Wrap<String> _homePageUriWrap = new Wrap<String>().p(this).c(String.class).var("_homePageUri").o(_homePageUri);

	/**	<br/>L'entité « _homePageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_homePageUri">Trouver l'entité _homePageUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __homePageUri(Wrap<String> c);

	public String get_homePageUri() {
		return _homePageUri;
	}

	public void set_homePageUri(String _homePageUri) {
		this._homePageUri = _homePageUri;
		this._homePageUriWrap.alreadyInitialized = true;
	}
	protected PageLayout _homePageUriInit() {
		if(!_homePageUriWrap.alreadyInitialized) {
			__homePageUri(_homePageUriWrap);
			if(_homePageUri == null)
				set_homePageUri(_homePageUriWrap.o);
		}
		_homePageUriWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solr_homePageUri() {
		return _homePageUri;
	}

	public String str_homePageUri() {
		return _homePageUri == null ? "" : _homePageUri;
	}

	public String nomAffichage_homePageUri() {
		return null;
	}

	public String htmTooltip_homePageUri() {
		return null;
	}

	public String htm_homePageUri() {
		return _homePageUri == null ? "" : StringEscapeUtils.escapeHtml4(str_homePageUri());
	}

	///////////////////
	// _pageInrEntry //
	///////////////////

	/**	L'entité « _pageInrEntry »
	 *	 is defined as null before being initialized. 
	 */
	protected String _pageInrEntry;
	public Wrap<String> _pageInrEntryWrap = new Wrap<String>().p(this).c(String.class).var("_pageInrEntry").o(_pageInrEntry);

	/**	<br/>L'entité « _pageInrEntry »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_pageInrEntry">Trouver l'entité _pageInrEntry dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __pageInrEntry(Wrap<String> c);

	public String get_pageInrEntry() {
		return _pageInrEntry;
	}

	public void set_pageInrEntry(String _pageInrEntry) {
		this._pageInrEntry = _pageInrEntry;
		this._pageInrEntryWrap.alreadyInitialized = true;
	}
	protected PageLayout _pageInrEntryInit() {
		if(!_pageInrEntryWrap.alreadyInitialized) {
			__pageInrEntry(_pageInrEntryWrap);
			if(_pageInrEntry == null)
				set_pageInrEntry(_pageInrEntryWrap.o);
		}
		_pageInrEntryWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solr_pageInrEntry() {
		return _pageInrEntry;
	}

	public String str_pageInrEntry() {
		return _pageInrEntry == null ? "" : _pageInrEntry;
	}

	public String nomAffichage_pageInrEntry() {
		return null;
	}

	public String htmTooltip_pageInrEntry() {
		return null;
	}

	public String htm_pageInrEntry() {
		return _pageInrEntry == null ? "" : StringEscapeUtils.escapeHtml4(str_pageInrEntry());
	}

	///////////////////
	// _aboutPageUri //
	///////////////////

	/**	L'entité « _aboutPageUri »
	 *	 is defined as null before being initialized. 
	 */
	protected String _aboutPageUri;
	public Wrap<String> _aboutPageUriWrap = new Wrap<String>().p(this).c(String.class).var("_aboutPageUri").o(_aboutPageUri);

	/**	<br/>L'entité « _aboutPageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_aboutPageUri">Trouver l'entité _aboutPageUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __aboutPageUri(Wrap<String> c);

	public String get_aboutPageUri() {
		return _aboutPageUri;
	}

	public void set_aboutPageUri(String _aboutPageUri) {
		this._aboutPageUri = _aboutPageUri;
		this._aboutPageUriWrap.alreadyInitialized = true;
	}
	protected PageLayout _aboutPageUriInit() {
		if(!_aboutPageUriWrap.alreadyInitialized) {
			__aboutPageUri(_aboutPageUriWrap);
			if(_aboutPageUri == null)
				set_aboutPageUri(_aboutPageUriWrap.o);
		}
		_aboutPageUriWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solr_aboutPageUri() {
		return _aboutPageUri;
	}

	public String str_aboutPageUri() {
		return _aboutPageUri == null ? "" : _aboutPageUri;
	}

	public String nomAffichage_aboutPageUri() {
		return null;
	}

	public String htmTooltip_aboutPageUri() {
		return null;
	}

	public String htm_aboutPageUri() {
		return _aboutPageUri == null ? "" : StringEscapeUtils.escapeHtml4(str_aboutPageUri());
	}

	////////////////
	// pageFaqUri //
	////////////////

	/**	L'entité « pageFaqUri »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageFaqUri;
	public Wrap<String> pageFaqUriWrap = new Wrap<String>().p(this).c(String.class).var("pageFaqUri").o(pageFaqUri);

	/**	<br/>L'entité « pageFaqUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageFaqUri">Trouver l'entité pageFaqUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageFaqUri(Wrap<String> c);

	public String getPageFaqUri() {
		return pageFaqUri;
	}

	public void setPageFaqUri(String pageFaqUri) {
		this.pageFaqUri = pageFaqUri;
		this.pageFaqUriWrap.alreadyInitialized = true;
	}
	protected PageLayout pageFaqUriInit() {
		if(!pageFaqUriWrap.alreadyInitialized) {
			_pageFaqUri(pageFaqUriWrap);
			if(pageFaqUri == null)
				setPageFaqUri(pageFaqUriWrap.o);
		}
		pageFaqUriWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solrPageFaqUri() {
		return pageFaqUri;
	}

	public String strPageFaqUri() {
		return pageFaqUri == null ? "" : pageFaqUri;
	}

	public String nomAffichagePageFaqUri() {
		return null;
	}

	public String htmTooltipPageFaqUri() {
		return null;
	}

	public String htmPageFaqUri() {
		return pageFaqUri == null ? "" : StringEscapeUtils.escapeHtml4(strPageFaqUri());
	}

	//////////////////
	// _userPageUri //
	//////////////////

	/**	L'entité « _userPageUri »
	 *	 is defined as null before being initialized. 
	 */
	protected String _userPageUri;
	public Wrap<String> _userPageUriWrap = new Wrap<String>().p(this).c(String.class).var("_userPageUri").o(_userPageUri);

	/**	<br/>L'entité « _userPageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_userPageUri">Trouver l'entité _userPageUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __userPageUri(Wrap<String> c);

	public String get_userPageUri() {
		return _userPageUri;
	}

	public void set_userPageUri(String _userPageUri) {
		this._userPageUri = _userPageUri;
		this._userPageUriWrap.alreadyInitialized = true;
	}
	protected PageLayout _userPageUriInit() {
		if(!_userPageUriWrap.alreadyInitialized) {
			__userPageUri(_userPageUriWrap);
			if(_userPageUri == null)
				set_userPageUri(_userPageUriWrap.o);
		}
		_userPageUriWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solr_userPageUri() {
		return _userPageUri;
	}

	public String str_userPageUri() {
		return _userPageUri == null ? "" : _userPageUri;
	}

	public String nomAffichage_userPageUri() {
		return null;
	}

	public String htmTooltip_userPageUri() {
		return null;
	}

	public String htm_userPageUri() {
		return _userPageUri == null ? "" : StringEscapeUtils.escapeHtml4(str_userPageUri());
	}

	////////////////////
	// _logoutPageUri //
	////////////////////

	/**	L'entité « _logoutPageUri »
	 *	 is defined as null before being initialized. 
	 */
	protected String _logoutPageUri;
	public Wrap<String> _logoutPageUriWrap = new Wrap<String>().p(this).c(String.class).var("_logoutPageUri").o(_logoutPageUri);

	/**	<br/>L'entité « _logoutPageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.enUS.school.page.PageLayout&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:_logoutPageUri">Trouver l'entité _logoutPageUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void __logoutPageUri(Wrap<String> c);

	public String get_logoutPageUri() {
		return _logoutPageUri;
	}

	public void set_logoutPageUri(String _logoutPageUri) {
		this._logoutPageUri = _logoutPageUri;
		this._logoutPageUriWrap.alreadyInitialized = true;
	}
	protected PageLayout _logoutPageUriInit() {
		if(!_logoutPageUriWrap.alreadyInitialized) {
			__logoutPageUri(_logoutPageUriWrap);
			if(_logoutPageUri == null)
				set_logoutPageUri(_logoutPageUriWrap.o);
		}
		_logoutPageUriWrap.alreadyInitialized(true);
		return (PageLayout)this;
	}

	public String solr_logoutPageUri() {
		return _logoutPageUri;
	}

	public String str_logoutPageUri() {
		return _logoutPageUri == null ? "" : _logoutPageUri;
	}

	public String nomAffichage_logoutPageUri() {
		return null;
	}

	public String htmTooltip_logoutPageUri() {
		return null;
	}

	public String htm_logoutPageUri() {
		return _logoutPageUri == null ? "" : StringEscapeUtils.escapeHtml4(str_logoutPageUri());
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
		requeteSite_Init();
		statiqueUrlBaseInit();
		pageDocumentSolrInit();
		_writerInit();
		contexteIconeGroupeInit();
		contexteIconeNomInit();
		contexteIconeClassesCssInit();
		pageVisibleToBotsInit();
		pageH1Init();
		pageH2Init();
		pageH3Init();
		_pageH1ShortInit();
		_pageH2ShortInit();
		_pageH2ShortInit();
		pageTitleInit();
		pageUriInit();
		pageUriFrFRInit();
		pageUrisInit();
		pageUrlInit();
		pageImageUriInit();
		pageImageUrlInit();
		pageVideoIdInit();
		pageVideoUrlInit();
		pageVideoUrlEmbedInit();
		_pageImageWidthInit();
		_pageImageHeightInit();
		_pageImageContentTypeInit();
		_pageContentTypeInit();
		_pageCreatedInit();
		_pageModifiedInit();
		_pageKeywordsInit();
		pageDescriptionInit();
		_homePageUriInit();
		_pageInrEntryInit();
		_aboutPageUriInit();
		pageFaqUriInit();
		_userPageUriInit();
		_logoutPageUriInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepPageLayout(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestPageLayout(SiteRequestEnUS siteRequest_) {
		if(_writer != null)
			_writer.setSiteRequest_(siteRequest_);
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
			case "requeteSite_":
				return oPageLayout.requeteSite_;
			case "statiqueUrlBase":
				return oPageLayout.statiqueUrlBase;
			case "pageDocumentSolr":
				return oPageLayout.pageDocumentSolr;
			case "_writer":
				return oPageLayout._writer;
			case "contexteIconeGroupe":
				return oPageLayout.contexteIconeGroupe;
			case "contexteIconeNom":
				return oPageLayout.contexteIconeNom;
			case "contexteIconeClassesCss":
				return oPageLayout.contexteIconeClassesCss;
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
			case "_pageH2Short":
				return oPageLayout._pageH2Short;
			case "pageTitle":
				return oPageLayout.pageTitle;
			case "pageUri":
				return oPageLayout.pageUri;
			case "pageUriFrFR":
				return oPageLayout.pageUriFrFR;
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
			case "_pageImageWidth":
				return oPageLayout._pageImageWidth;
			case "_pageImageHeight":
				return oPageLayout._pageImageHeight;
			case "_pageImageContentType":
				return oPageLayout._pageImageContentType;
			case "_pageContentType":
				return oPageLayout._pageContentType;
			case "_pageCreated":
				return oPageLayout._pageCreated;
			case "_pageModified":
				return oPageLayout._pageModified;
			case "_pageKeywords":
				return oPageLayout._pageKeywords;
			case "pageDescription":
				return oPageLayout.pageDescription;
			case "_homePageUri":
				return oPageLayout._homePageUri;
			case "_pageInrEntry":
				return oPageLayout._pageInrEntry;
			case "_aboutPageUri":
				return oPageLayout._aboutPageUri;
			case "pageFaqUri":
				return oPageLayout.pageFaqUri;
			case "_userPageUri":
				return oPageLayout._userPageUri;
			case "_logoutPageUri":
				return oPageLayout._logoutPageUri;
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
	// definir //
	/////////////

	public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirPageLayout(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirPageLayout(String var, String val) {
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
		sb.append("PageLayout {");
		sb.append(" }");
		return sb.toString();
	}
}
