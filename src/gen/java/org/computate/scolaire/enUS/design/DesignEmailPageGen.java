package org.computate.scolaire.enUS.design;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import org.apache.solr.common.util.SimpleOrderedMap;
import java.util.Date;
import org.computate.scolaire.enUS.design.DesignEmailGenPage;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import org.computate.scolaire.enUS.block.SchoolBlock;
import java.math.BigDecimal;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import org.computate.scolaire.enUS.writer.AllWriter;
import java.math.MathContext;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.computate.scolaire.enUS.year.SchoolYear;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.List;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import java.time.LocalDate;
import org.computate.scolaire.enUS.mom.SchoolMom;
import java.util.Optional;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import java.util.HashMap;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import java.lang.String;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.design.PageDesign;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.receipt.SchoolReceipt;
import java.time.temporal.ChronoUnit;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class DesignEmailPageGen<DEV> extends DesignEmailGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DesignEmailPage.class);

	////////
	// w1 //
	////////

	/**	 The entity w1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter w1;
	@JsonIgnore
	public Wrap<AllWriter> w1Wrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("w1").o(w1);

	/**	<br/> The entity w1
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:w1">Find the entity w1 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _w1(Wrap<AllWriter> c);

	public AllWriter getW1() {
		return w1;
	}

	public void setW1(AllWriter w1) {
		this.w1 = w1;
		this.w1Wrap.alreadyInitialized = true;
	}
	public static AllWriter staticSetW1(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage w1Init() {
		if(!w1Wrap.alreadyInitialized) {
			_w1(w1Wrap);
			if(w1 == null)
				setW1(w1Wrap.o);
		}
		if(w1 != null)
			w1.initDeepForClass(siteRequest_);
		w1Wrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////
	// wPage //
	///////////

	/**	 The entity wPage
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wPage;
	@JsonIgnore
	public Wrap<AllWriter> wPageWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wPage").o(wPage);

	/**	<br/> The entity wPage
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wPage">Find the entity wPage in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _wPage(Wrap<AllWriter> c);

	public AllWriter getWPage() {
		return wPage;
	}

	public void setWPage(AllWriter wPage) {
		this.wPage = wPage;
		this.wPageWrap.alreadyInitialized = true;
	}
	public static AllWriter staticSetWPage(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage wPageInit() {
		if(!wPageWrap.alreadyInitialized) {
			_wPage(wPageWrap);
			if(wPage == null)
				setWPage(wPageWrap.o);
		}
		if(wPage != null)
			wPage.initDeepForClass(siteRequest_);
		wPageWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////
	// pageDesignId //
	//////////////////

	/**	 The entity pageDesignId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageDesignId;
	@JsonIgnore
	public Wrap<String> pageDesignIdWrap = new Wrap<String>().p(this).c(String.class).var("pageDesignId").o(pageDesignId);

	/**	<br/> The entity pageDesignId
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesignId">Find the entity pageDesignId in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageDesignId(Wrap<String> c);

	public String getPageDesignId() {
		return pageDesignId;
	}
	public void setPageDesignId(String o) {
		this.pageDesignId = DesignEmailPage.staticSetPageDesignId(siteRequest_, o);
		this.pageDesignIdWrap.alreadyInitialized = true;
	}
	public static String staticSetPageDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage pageDesignIdInit() {
		if(!pageDesignIdWrap.alreadyInitialized) {
			_pageDesignId(pageDesignIdWrap);
			if(pageDesignId == null)
				setPageDesignId(pageDesignIdWrap.o);
		}
		pageDesignIdWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrPageDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPageDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPageDesignId(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrPageDesignId(siteRequest_, DesignEmailPage.staticSolrPageDesignId(siteRequest_, DesignEmailPage.staticSetPageDesignId(siteRequest_, o)));
	}

	public String solrPageDesignId() {
		return DesignEmailPage.staticSolrPageDesignId(siteRequest_, pageDesignId);
	}

	public String strPageDesignId() {
		return pageDesignId == null ? "" : pageDesignId;
	}

	public String jsonPageDesignId() {
		return pageDesignId == null ? "" : pageDesignId;
	}

	public String nomAffichagePageDesignId() {
		return null;
	}

	public String htmTooltipPageDesignId() {
		return null;
	}

	public String htmPageDesignId() {
		return pageDesignId == null ? "" : StringEscapeUtils.escapeHtml4(strPageDesignId());
	}

	////////////////////////
	// pageHtmlPartSearch //
	////////////////////////

	/**	 The entity pageHtmlPartSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<HtmlPart>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<HtmlPart> pageHtmlPartSearch = new SearchList<HtmlPart>();
	@JsonIgnore
	public Wrap<SearchList<HtmlPart>> pageHtmlPartSearchWrap = new Wrap<SearchList<HtmlPart>>().p(this).c(SearchList.class).var("pageHtmlPartSearch").o(pageHtmlPartSearch);

	/**	<br/> The entity pageHtmlPartSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<HtmlPart>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageHtmlPartSearch">Find the entity pageHtmlPartSearch in Solr</a>
	 * <br/>
	 * @param pageHtmlPartSearch is the entity already constructed. 
	 **/
	protected abstract void _pageHtmlPartSearch(SearchList<HtmlPart> l);

	public SearchList<HtmlPart> getPageHtmlPartSearch() {
		return pageHtmlPartSearch;
	}

	public void setPageHtmlPartSearch(SearchList<HtmlPart> pageHtmlPartSearch) {
		this.pageHtmlPartSearch = pageHtmlPartSearch;
		this.pageHtmlPartSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<HtmlPart> staticSetPageHtmlPartSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage pageHtmlPartSearchInit() {
		if(!pageHtmlPartSearchWrap.alreadyInitialized) {
			_pageHtmlPartSearch(pageHtmlPartSearch);
		}
		pageHtmlPartSearch.initDeepForClass(siteRequest_);
		pageHtmlPartSearchWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////////
	// pageHtmlPartList //
	//////////////////////

	/**	 The entity pageHtmlPartList
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<HtmlPart> pageHtmlPartList;
	@JsonIgnore
	public Wrap<List<HtmlPart>> pageHtmlPartListWrap = new Wrap<List<HtmlPart>>().p(this).c(List.class).var("pageHtmlPartList").o(pageHtmlPartList);

	/**	<br/> The entity pageHtmlPartList
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageHtmlPartList">Find the entity pageHtmlPartList in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageHtmlPartList(Wrap<List<HtmlPart>> c);

	public List<HtmlPart> getPageHtmlPartList() {
		return pageHtmlPartList;
	}

	public void setPageHtmlPartList(List<HtmlPart> pageHtmlPartList) {
		this.pageHtmlPartList = pageHtmlPartList;
		this.pageHtmlPartListWrap.alreadyInitialized = true;
	}
	public static HtmlPart staticSetPageHtmlPartList(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addPageHtmlPartList(HtmlPart...objets) {
		for(HtmlPart o : objets) {
			addPageHtmlPartList(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addPageHtmlPartList(HtmlPart o) {
		if(o != null && !pageHtmlPartList.contains(o))
			this.pageHtmlPartList.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage pageHtmlPartListInit() {
		if(!pageHtmlPartListWrap.alreadyInitialized) {
			_pageHtmlPartList(pageHtmlPartListWrap);
			if(pageHtmlPartList == null)
				setPageHtmlPartList(pageHtmlPartListWrap.o);
		}
		pageHtmlPartListWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	////////////
	// wEmail //
	////////////

	/**	 The entity wEmail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wEmail;
	@JsonIgnore
	public Wrap<AllWriter> wEmailWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wEmail").o(wEmail);

	/**	<br/> The entity wEmail
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wEmail">Find the entity wEmail in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _wEmail(Wrap<AllWriter> c);

	public AllWriter getWEmail() {
		return wEmail;
	}

	public void setWEmail(AllWriter wEmail) {
		this.wEmail = wEmail;
		this.wEmailWrap.alreadyInitialized = true;
	}
	public static AllWriter staticSetWEmail(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage wEmailInit() {
		if(!wEmailWrap.alreadyInitialized) {
			_wEmail(wEmailWrap);
			if(wEmail == null)
				setWEmail(wEmailWrap.o);
		}
		if(wEmail != null)
			wEmail.initDeepForClass(siteRequest_);
		wEmailWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////////
	// emailContentType //
	//////////////////////

	/**	 The entity emailContentType
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailContentType;
	@JsonIgnore
	public Wrap<String> emailContentTypeWrap = new Wrap<String>().p(this).c(String.class).var("emailContentType").o(emailContentType);

	/**	<br/> The entity emailContentType
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailContentType">Find the entity emailContentType in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailContentType(Wrap<String> c);

	public String getEmailContentType() {
		return emailContentType;
	}
	public void setEmailContentType(String o) {
		this.emailContentType = DesignEmailPage.staticSetEmailContentType(siteRequest_, o);
		this.emailContentTypeWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailContentType(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage emailContentTypeInit() {
		if(!emailContentTypeWrap.alreadyInitialized) {
			_emailContentType(emailContentTypeWrap);
			if(emailContentType == null)
				setEmailContentType(emailContentTypeWrap.o);
		}
		emailContentTypeWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrEmailContentType(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailContentType(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailContentType(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrEmailContentType(siteRequest_, DesignEmailPage.staticSolrEmailContentType(siteRequest_, DesignEmailPage.staticSetEmailContentType(siteRequest_, o)));
	}

	public String solrEmailContentType() {
		return DesignEmailPage.staticSolrEmailContentType(siteRequest_, emailContentType);
	}

	public String strEmailContentType() {
		return emailContentType == null ? "" : emailContentType;
	}

	public String jsonEmailContentType() {
		return emailContentType == null ? "" : emailContentType;
	}

	public String nomAffichageEmailContentType() {
		return null;
	}

	public String htmTooltipEmailContentType() {
		return null;
	}

	public String htmEmailContentType() {
		return emailContentType == null ? "" : StringEscapeUtils.escapeHtml4(strEmailContentType());
	}

	///////////////////
	// emailDesignId //
	///////////////////

	/**	 The entity emailDesignId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailDesignId;
	@JsonIgnore
	public Wrap<String> emailDesignIdWrap = new Wrap<String>().p(this).c(String.class).var("emailDesignId").o(emailDesignId);

	/**	<br/> The entity emailDesignId
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailDesignId">Find the entity emailDesignId in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailDesignId(Wrap<String> c);

	public String getEmailDesignId() {
		return emailDesignId;
	}
	public void setEmailDesignId(String o) {
		this.emailDesignId = DesignEmailPage.staticSetEmailDesignId(siteRequest_, o);
		this.emailDesignIdWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage emailDesignIdInit() {
		if(!emailDesignIdWrap.alreadyInitialized) {
			_emailDesignId(emailDesignIdWrap);
			if(emailDesignId == null)
				setEmailDesignId(emailDesignIdWrap.o);
		}
		emailDesignIdWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrEmailDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailDesignId(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrEmailDesignId(siteRequest_, DesignEmailPage.staticSolrEmailDesignId(siteRequest_, DesignEmailPage.staticSetEmailDesignId(siteRequest_, o)));
	}

	public String solrEmailDesignId() {
		return DesignEmailPage.staticSolrEmailDesignId(siteRequest_, emailDesignId);
	}

	public String strEmailDesignId() {
		return emailDesignId == null ? "" : emailDesignId;
	}

	public String jsonEmailDesignId() {
		return emailDesignId == null ? "" : emailDesignId;
	}

	public String nomAffichageEmailDesignId() {
		return null;
	}

	public String htmTooltipEmailDesignId() {
		return null;
	}

	public String htmEmailDesignId() {
		return emailDesignId == null ? "" : StringEscapeUtils.escapeHtml4(strEmailDesignId());
	}

	///////////////////////
	// emailDesignSearch //
	///////////////////////

	/**	 The entity emailDesignSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<PageDesign>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<PageDesign> emailDesignSearch = new SearchList<PageDesign>();
	@JsonIgnore
	public Wrap<SearchList<PageDesign>> emailDesignSearchWrap = new Wrap<SearchList<PageDesign>>().p(this).c(SearchList.class).var("emailDesignSearch").o(emailDesignSearch);

	/**	<br/> The entity emailDesignSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<PageDesign>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailDesignSearch">Find the entity emailDesignSearch in Solr</a>
	 * <br/>
	 * @param emailDesignSearch is the entity already constructed. 
	 **/
	protected abstract void _emailDesignSearch(SearchList<PageDesign> l);

	public SearchList<PageDesign> getEmailDesignSearch() {
		return emailDesignSearch;
	}

	public void setEmailDesignSearch(SearchList<PageDesign> emailDesignSearch) {
		this.emailDesignSearch = emailDesignSearch;
		this.emailDesignSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<PageDesign> staticSetEmailDesignSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage emailDesignSearchInit() {
		if(!emailDesignSearchWrap.alreadyInitialized) {
			_emailDesignSearch(emailDesignSearch);
		}
		emailDesignSearch.initDeepForClass(siteRequest_);
		emailDesignSearchWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	/////////////////
	// emailDesign //
	/////////////////

	/**	 The entity emailDesign
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected PageDesign emailDesign;
	@JsonIgnore
	public Wrap<PageDesign> emailDesignWrap = new Wrap<PageDesign>().p(this).c(PageDesign.class).var("emailDesign").o(emailDesign);

	/**	<br/> The entity emailDesign
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailDesign">Find the entity emailDesign in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailDesign(Wrap<PageDesign> c);

	public PageDesign getEmailDesign() {
		return emailDesign;
	}

	public void setEmailDesign(PageDesign emailDesign) {
		this.emailDesign = emailDesign;
		this.emailDesignWrap.alreadyInitialized = true;
	}
	public static PageDesign staticSetEmailDesign(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage emailDesignInit() {
		if(!emailDesignWrap.alreadyInitialized) {
			_emailDesign(emailDesignWrap);
			if(emailDesign == null)
				setEmailDesign(emailDesignWrap.o);
		}
		if(emailDesign != null)
			emailDesign.initDeepForClass(siteRequest_);
		emailDesignWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	/////////////////////////
	// emailHtmlPartSearch //
	/////////////////////////

	/**	 The entity emailHtmlPartSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<HtmlPart>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<HtmlPart> emailHtmlPartSearch = new SearchList<HtmlPart>();
	@JsonIgnore
	public Wrap<SearchList<HtmlPart>> emailHtmlPartSearchWrap = new Wrap<SearchList<HtmlPart>>().p(this).c(SearchList.class).var("emailHtmlPartSearch").o(emailHtmlPartSearch);

	/**	<br/> The entity emailHtmlPartSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<HtmlPart>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailHtmlPartSearch">Find the entity emailHtmlPartSearch in Solr</a>
	 * <br/>
	 * @param emailHtmlPartSearch is the entity already constructed. 
	 **/
	protected abstract void _emailHtmlPartSearch(SearchList<HtmlPart> l);

	public SearchList<HtmlPart> getEmailHtmlPartSearch() {
		return emailHtmlPartSearch;
	}

	public void setEmailHtmlPartSearch(SearchList<HtmlPart> emailHtmlPartSearch) {
		this.emailHtmlPartSearch = emailHtmlPartSearch;
		this.emailHtmlPartSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<HtmlPart> staticSetEmailHtmlPartSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage emailHtmlPartSearchInit() {
		if(!emailHtmlPartSearchWrap.alreadyInitialized) {
			_emailHtmlPartSearch(emailHtmlPartSearch);
		}
		emailHtmlPartSearch.initDeepForClass(siteRequest_);
		emailHtmlPartSearchWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////////////////
	// emailHtmlPartList //
	///////////////////////

	/**	 The entity emailHtmlPartList
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<HtmlPart> emailHtmlPartList;
	@JsonIgnore
	public Wrap<List<HtmlPart>> emailHtmlPartListWrap = new Wrap<List<HtmlPart>>().p(this).c(List.class).var("emailHtmlPartList").o(emailHtmlPartList);

	/**	<br/> The entity emailHtmlPartList
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailHtmlPartList">Find the entity emailHtmlPartList in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailHtmlPartList(Wrap<List<HtmlPart>> c);

	public List<HtmlPart> getEmailHtmlPartList() {
		return emailHtmlPartList;
	}

	public void setEmailHtmlPartList(List<HtmlPart> emailHtmlPartList) {
		this.emailHtmlPartList = emailHtmlPartList;
		this.emailHtmlPartListWrap.alreadyInitialized = true;
	}
	public static HtmlPart staticSetEmailHtmlPartList(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addEmailHtmlPartList(HtmlPart...objets) {
		for(HtmlPart o : objets) {
			addEmailHtmlPartList(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addEmailHtmlPartList(HtmlPart o) {
		if(o != null && !emailHtmlPartList.contains(o))
			this.emailHtmlPartList.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage emailHtmlPartListInit() {
		if(!emailHtmlPartListWrap.alreadyInitialized) {
			_emailHtmlPartList(emailHtmlPartListWrap);
			if(emailHtmlPartList == null)
				setEmailHtmlPartList(emailHtmlPartListWrap.o);
		}
		emailHtmlPartListWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	/////////////////
	// wAttachment //
	/////////////////

	/**	 The entity wAttachment
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wAttachment;
	@JsonIgnore
	public Wrap<AllWriter> wAttachmentWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wAttachment").o(wAttachment);

	/**	<br/> The entity wAttachment
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wAttachment">Find the entity wAttachment in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _wAttachment(Wrap<AllWriter> c);

	public AllWriter getWAttachment() {
		return wAttachment;
	}

	public void setWAttachment(AllWriter wAttachment) {
		this.wAttachment = wAttachment;
		this.wAttachmentWrap.alreadyInitialized = true;
	}
	public static AllWriter staticSetWAttachment(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage wAttachmentInit() {
		if(!wAttachmentWrap.alreadyInitialized) {
			_wAttachment(wAttachmentWrap);
			if(wAttachment == null)
				setWAttachment(wAttachmentWrap.o);
		}
		if(wAttachment != null)
			wAttachment.initDeepForClass(siteRequest_);
		wAttachmentWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////////////////////
	// attachmentContentType //
	///////////////////////////

	/**	 The entity attachmentContentType
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String attachmentContentType;
	@JsonIgnore
	public Wrap<String> attachmentContentTypeWrap = new Wrap<String>().p(this).c(String.class).var("attachmentContentType").o(attachmentContentType);

	/**	<br/> The entity attachmentContentType
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:attachmentContentType">Find the entity attachmentContentType in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _attachmentContentType(Wrap<String> c);

	public String getAttachmentContentType() {
		return attachmentContentType;
	}
	public void setAttachmentContentType(String o) {
		this.attachmentContentType = DesignEmailPage.staticSetAttachmentContentType(siteRequest_, o);
		this.attachmentContentTypeWrap.alreadyInitialized = true;
	}
	public static String staticSetAttachmentContentType(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage attachmentContentTypeInit() {
		if(!attachmentContentTypeWrap.alreadyInitialized) {
			_attachmentContentType(attachmentContentTypeWrap);
			if(attachmentContentType == null)
				setAttachmentContentType(attachmentContentTypeWrap.o);
		}
		attachmentContentTypeWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrAttachmentContentType(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrAttachmentContentType(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAttachmentContentType(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrAttachmentContentType(siteRequest_, DesignEmailPage.staticSolrAttachmentContentType(siteRequest_, DesignEmailPage.staticSetAttachmentContentType(siteRequest_, o)));
	}

	public String solrAttachmentContentType() {
		return DesignEmailPage.staticSolrAttachmentContentType(siteRequest_, attachmentContentType);
	}

	public String strAttachmentContentType() {
		return attachmentContentType == null ? "" : attachmentContentType;
	}

	public String jsonAttachmentContentType() {
		return attachmentContentType == null ? "" : attachmentContentType;
	}

	public String nomAffichageAttachmentContentType() {
		return null;
	}

	public String htmTooltipAttachmentContentType() {
		return null;
	}

	public String htmAttachmentContentType() {
		return attachmentContentType == null ? "" : StringEscapeUtils.escapeHtml4(strAttachmentContentType());
	}

	////////////////////////
	// attachmentDesignId //
	////////////////////////

	/**	 The entity attachmentDesignId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String attachmentDesignId;
	@JsonIgnore
	public Wrap<String> attachmentDesignIdWrap = new Wrap<String>().p(this).c(String.class).var("attachmentDesignId").o(attachmentDesignId);

	/**	<br/> The entity attachmentDesignId
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:attachmentDesignId">Find the entity attachmentDesignId in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _attachmentDesignId(Wrap<String> c);

	public String getAttachmentDesignId() {
		return attachmentDesignId;
	}
	public void setAttachmentDesignId(String o) {
		this.attachmentDesignId = DesignEmailPage.staticSetAttachmentDesignId(siteRequest_, o);
		this.attachmentDesignIdWrap.alreadyInitialized = true;
	}
	public static String staticSetAttachmentDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage attachmentDesignIdInit() {
		if(!attachmentDesignIdWrap.alreadyInitialized) {
			_attachmentDesignId(attachmentDesignIdWrap);
			if(attachmentDesignId == null)
				setAttachmentDesignId(attachmentDesignIdWrap.o);
		}
		attachmentDesignIdWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrAttachmentDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrAttachmentDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAttachmentDesignId(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrAttachmentDesignId(siteRequest_, DesignEmailPage.staticSolrAttachmentDesignId(siteRequest_, DesignEmailPage.staticSetAttachmentDesignId(siteRequest_, o)));
	}

	public String solrAttachmentDesignId() {
		return DesignEmailPage.staticSolrAttachmentDesignId(siteRequest_, attachmentDesignId);
	}

	public String strAttachmentDesignId() {
		return attachmentDesignId == null ? "" : attachmentDesignId;
	}

	public String jsonAttachmentDesignId() {
		return attachmentDesignId == null ? "" : attachmentDesignId;
	}

	public String nomAffichageAttachmentDesignId() {
		return null;
	}

	public String htmTooltipAttachmentDesignId() {
		return null;
	}

	public String htmAttachmentDesignId() {
		return attachmentDesignId == null ? "" : StringEscapeUtils.escapeHtml4(strAttachmentDesignId());
	}

	////////////////////////////
	// attachmentDesignSearch //
	////////////////////////////

	/**	 The entity attachmentDesignSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<PageDesign>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<PageDesign> attachmentDesignSearch = new SearchList<PageDesign>();
	@JsonIgnore
	public Wrap<SearchList<PageDesign>> attachmentDesignSearchWrap = new Wrap<SearchList<PageDesign>>().p(this).c(SearchList.class).var("attachmentDesignSearch").o(attachmentDesignSearch);

	/**	<br/> The entity attachmentDesignSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<PageDesign>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:attachmentDesignSearch">Find the entity attachmentDesignSearch in Solr</a>
	 * <br/>
	 * @param attachmentDesignSearch is the entity already constructed. 
	 **/
	protected abstract void _attachmentDesignSearch(SearchList<PageDesign> l);

	public SearchList<PageDesign> getAttachmentDesignSearch() {
		return attachmentDesignSearch;
	}

	public void setAttachmentDesignSearch(SearchList<PageDesign> attachmentDesignSearch) {
		this.attachmentDesignSearch = attachmentDesignSearch;
		this.attachmentDesignSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<PageDesign> staticSetAttachmentDesignSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage attachmentDesignSearchInit() {
		if(!attachmentDesignSearchWrap.alreadyInitialized) {
			_attachmentDesignSearch(attachmentDesignSearch);
		}
		attachmentDesignSearch.initDeepForClass(siteRequest_);
		attachmentDesignSearchWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////////
	// attachmentDesign //
	//////////////////////

	/**	 The entity attachmentDesign
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected PageDesign attachmentDesign;
	@JsonIgnore
	public Wrap<PageDesign> attachmentDesignWrap = new Wrap<PageDesign>().p(this).c(PageDesign.class).var("attachmentDesign").o(attachmentDesign);

	/**	<br/> The entity attachmentDesign
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:attachmentDesign">Find the entity attachmentDesign in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _attachmentDesign(Wrap<PageDesign> c);

	public PageDesign getAttachmentDesign() {
		return attachmentDesign;
	}

	public void setAttachmentDesign(PageDesign attachmentDesign) {
		this.attachmentDesign = attachmentDesign;
		this.attachmentDesignWrap.alreadyInitialized = true;
	}
	public static PageDesign staticSetAttachmentDesign(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage attachmentDesignInit() {
		if(!attachmentDesignWrap.alreadyInitialized) {
			_attachmentDesign(attachmentDesignWrap);
			if(attachmentDesign == null)
				setAttachmentDesign(attachmentDesignWrap.o);
		}
		if(attachmentDesign != null)
			attachmentDesign.initDeepForClass(siteRequest_);
		attachmentDesignWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////////////////
	// attachmentHtmlPartSearch //
	//////////////////////////////

	/**	 The entity attachmentHtmlPartSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<HtmlPart>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<HtmlPart> attachmentHtmlPartSearch = new SearchList<HtmlPart>();
	@JsonIgnore
	public Wrap<SearchList<HtmlPart>> attachmentHtmlPartSearchWrap = new Wrap<SearchList<HtmlPart>>().p(this).c(SearchList.class).var("attachmentHtmlPartSearch").o(attachmentHtmlPartSearch);

	/**	<br/> The entity attachmentHtmlPartSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<HtmlPart>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:attachmentHtmlPartSearch">Find the entity attachmentHtmlPartSearch in Solr</a>
	 * <br/>
	 * @param attachmentHtmlPartSearch is the entity already constructed. 
	 **/
	protected abstract void _attachmentHtmlPartSearch(SearchList<HtmlPart> l);

	public SearchList<HtmlPart> getAttachmentHtmlPartSearch() {
		return attachmentHtmlPartSearch;
	}

	public void setAttachmentHtmlPartSearch(SearchList<HtmlPart> attachmentHtmlPartSearch) {
		this.attachmentHtmlPartSearch = attachmentHtmlPartSearch;
		this.attachmentHtmlPartSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<HtmlPart> staticSetAttachmentHtmlPartSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage attachmentHtmlPartSearchInit() {
		if(!attachmentHtmlPartSearchWrap.alreadyInitialized) {
			_attachmentHtmlPartSearch(attachmentHtmlPartSearch);
		}
		attachmentHtmlPartSearch.initDeepForClass(siteRequest_);
		attachmentHtmlPartSearchWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	////////////////////////////
	// attachmentHtmlPartList //
	////////////////////////////

	/**	 The entity attachmentHtmlPartList
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<HtmlPart> attachmentHtmlPartList;
	@JsonIgnore
	public Wrap<List<HtmlPart>> attachmentHtmlPartListWrap = new Wrap<List<HtmlPart>>().p(this).c(List.class).var("attachmentHtmlPartList").o(attachmentHtmlPartList);

	/**	<br/> The entity attachmentHtmlPartList
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:attachmentHtmlPartList">Find the entity attachmentHtmlPartList in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _attachmentHtmlPartList(Wrap<List<HtmlPart>> c);

	public List<HtmlPart> getAttachmentHtmlPartList() {
		return attachmentHtmlPartList;
	}

	public void setAttachmentHtmlPartList(List<HtmlPart> attachmentHtmlPartList) {
		this.attachmentHtmlPartList = attachmentHtmlPartList;
		this.attachmentHtmlPartListWrap.alreadyInitialized = true;
	}
	public static HtmlPart staticSetAttachmentHtmlPartList(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addAttachmentHtmlPartList(HtmlPart...objets) {
		for(HtmlPart o : objets) {
			addAttachmentHtmlPartList(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addAttachmentHtmlPartList(HtmlPart o) {
		if(o != null && !attachmentHtmlPartList.contains(o))
			this.attachmentHtmlPartList.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage attachmentHtmlPartListInit() {
		if(!attachmentHtmlPartListWrap.alreadyInitialized) {
			_attachmentHtmlPartList(attachmentHtmlPartListWrap);
			if(attachmentHtmlPartList == null)
				setAttachmentHtmlPartList(attachmentHtmlPartListWrap.o);
		}
		attachmentHtmlPartListWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////////
	// enrollmentSearch //
	//////////////////////

	/**	 The entity enrollmentSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolEnrollment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolEnrollment> enrollmentSearch = new SearchList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<SearchList<SchoolEnrollment>> enrollmentSearchWrap = new Wrap<SearchList<SchoolEnrollment>>().p(this).c(SearchList.class).var("enrollmentSearch").o(enrollmentSearch);

	/**	<br/> The entity enrollmentSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSearch">Find the entity enrollmentSearch in Solr</a>
	 * <br/>
	 * @param enrollmentSearch is the entity already constructed. 
	 **/
	protected abstract void _enrollmentSearch(SearchList<SchoolEnrollment> l);

	public SearchList<SchoolEnrollment> getEnrollmentSearch() {
		return enrollmentSearch;
	}

	public void setEnrollmentSearch(SearchList<SchoolEnrollment> enrollmentSearch) {
		this.enrollmentSearch = enrollmentSearch;
		this.enrollmentSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolEnrollment> staticSetEnrollmentSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage enrollmentSearchInit() {
		if(!enrollmentSearchWrap.alreadyInitialized) {
			_enrollmentSearch(enrollmentSearch);
		}
		enrollmentSearch.initDeepForClass(siteRequest_);
		enrollmentSearchWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////////
	// schoolEnrollment //
	//////////////////////

	/**	 The entity schoolEnrollment
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment schoolEnrollment;
	@JsonIgnore
	public Wrap<SchoolEnrollment> schoolEnrollmentWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("schoolEnrollment").o(schoolEnrollment);

	/**	<br/> The entity schoolEnrollment
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolEnrollment">Find the entity schoolEnrollment in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolEnrollment(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getSchoolEnrollment() {
		return schoolEnrollment;
	}

	public void setSchoolEnrollment(SchoolEnrollment schoolEnrollment) {
		this.schoolEnrollment = schoolEnrollment;
		this.schoolEnrollmentWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetSchoolEnrollment(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage schoolEnrollmentInit() {
		if(!schoolEnrollmentWrap.alreadyInitialized) {
			_schoolEnrollment(schoolEnrollmentWrap);
			if(schoolEnrollment == null)
				setSchoolEnrollment(schoolEnrollmentWrap.o);
		}
		if(schoolEnrollment != null)
			schoolEnrollment.initDeepForClass(siteRequest_);
		schoolEnrollmentWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	/////////////////
	// enrollments //
	/////////////////

	/**	 The entity enrollments
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollments;
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollments").o(enrollments);

	/**	<br/> The entity enrollments
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollments">Find the entity enrollments in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollments(Wrap<List<SchoolEnrollment>> c);

	public List<SchoolEnrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<SchoolEnrollment> enrollments) {
		this.enrollments = enrollments;
		this.enrollmentsWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollments(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addEnrollments(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollments(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addEnrollments(SchoolEnrollment o) {
		if(o != null && !enrollments.contains(o))
			this.enrollments.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage enrollmentsInit() {
		if(!enrollmentsWrap.alreadyInitialized) {
			_enrollments(enrollmentsWrap);
			if(enrollments == null)
				setEnrollments(enrollmentsWrap.o);
		}
		enrollmentsWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////////
	// enrollmentBlocks //
	//////////////////////

	/**	 The entity enrollmentBlocks
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollmentBlocks = new ArrayList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentBlocksWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentBlocks").o(enrollmentBlocks);

	/**	<br/> The entity enrollmentBlocks
	 *  It is constructed before being initialized with the constructor by default List<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentBlocks">Find the entity enrollmentBlocks in Solr</a>
	 * <br/>
	 * @param enrollmentBlocks is the entity already constructed. 
	 **/
	protected abstract void _enrollmentBlocks(List<SchoolEnrollment> c);

	public List<SchoolEnrollment> getEnrollmentBlocks() {
		return enrollmentBlocks;
	}

	public void setEnrollmentBlocks(List<SchoolEnrollment> enrollmentBlocks) {
		this.enrollmentBlocks = enrollmentBlocks;
		this.enrollmentBlocksWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentBlocks(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addEnrollmentBlocks(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollmentBlocks(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addEnrollmentBlocks(SchoolEnrollment o) {
		if(o != null && !enrollmentBlocks.contains(o))
			this.enrollmentBlocks.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage enrollmentBlocksInit() {
		if(!enrollmentBlocksWrap.alreadyInitialized) {
			_enrollmentBlocks(enrollmentBlocks);
		}
		enrollmentBlocksWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////////
	// enrollmentGroups //
	//////////////////////

	/**	 The entity enrollmentGroups
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollmentGroups;
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentGroupsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentGroups").o(enrollmentGroups);

	/**	<br/> The entity enrollmentGroups
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentGroups">Find the entity enrollmentGroups in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentGroups(Wrap<List<SchoolEnrollment>> c);

	public List<SchoolEnrollment> getEnrollmentGroups() {
		return enrollmentGroups;
	}

	public void setEnrollmentGroups(List<SchoolEnrollment> enrollmentGroups) {
		this.enrollmentGroups = enrollmentGroups;
		this.enrollmentGroupsWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentGroups(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addEnrollmentGroups(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollmentGroups(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addEnrollmentGroups(SchoolEnrollment o) {
		if(o != null && !enrollmentGroups.contains(o))
			this.enrollmentGroups.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage enrollmentGroupsInit() {
		if(!enrollmentGroupsWrap.alreadyInitialized) {
			_enrollmentGroups(enrollmentGroupsWrap);
			if(enrollmentGroups == null)
				setEnrollmentGroups(enrollmentGroupsWrap.o);
		}
		enrollmentGroupsWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	/////////////////////
	// enrollmentBlock //
	/////////////////////

	/**	 The entity enrollmentBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment enrollmentBlock;
	@JsonIgnore
	public Wrap<SchoolEnrollment> enrollmentBlockWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("enrollmentBlock").o(enrollmentBlock);

	/**	<br/> The entity enrollmentBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentBlock">Find the entity enrollmentBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentBlock(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getEnrollmentBlock() {
		return enrollmentBlock;
	}

	public void setEnrollmentBlock(SchoolEnrollment enrollmentBlock) {
		this.enrollmentBlock = enrollmentBlock;
		this.enrollmentBlockWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage enrollmentBlockInit() {
		if(!enrollmentBlockWrap.alreadyInitialized) {
			_enrollmentBlock(enrollmentBlockWrap);
			if(enrollmentBlock == null)
				setEnrollmentBlock(enrollmentBlockWrap.o);
		}
		if(enrollmentBlock != null)
			enrollmentBlock.initDeepForClass(siteRequest_);
		enrollmentBlockWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	/////////////////////
	// enrollmentGroup //
	/////////////////////

	/**	 The entity enrollmentGroup
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment enrollmentGroup;
	@JsonIgnore
	public Wrap<SchoolEnrollment> enrollmentGroupWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("enrollmentGroup").o(enrollmentGroup);

	/**	<br/> The entity enrollmentGroup
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentGroup">Find the entity enrollmentGroup in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentGroup(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getEnrollmentGroup() {
		return enrollmentGroup;
	}

	public void setEnrollmentGroup(SchoolEnrollment enrollmentGroup) {
		this.enrollmentGroup = enrollmentGroup;
		this.enrollmentGroupWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentGroup(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage enrollmentGroupInit() {
		if(!enrollmentGroupWrap.alreadyInitialized) {
			_enrollmentGroup(enrollmentGroupWrap);
			if(enrollmentGroup == null)
				setEnrollmentGroup(enrollmentGroupWrap.o);
		}
		if(enrollmentGroup != null)
			enrollmentGroup.initDeepForClass(siteRequest_);
		enrollmentGroupWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////////////
	// enrollmentEnrollment //
	//////////////////////////

	/**	 The entity enrollmentEnrollment
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment enrollmentEnrollment;
	@JsonIgnore
	public Wrap<SchoolEnrollment> enrollmentEnrollmentWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("enrollmentEnrollment").o(enrollmentEnrollment);

	/**	<br/> The entity enrollmentEnrollment
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentEnrollment">Find the entity enrollmentEnrollment in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentEnrollment(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getEnrollmentEnrollment() {
		return enrollmentEnrollment;
	}

	public void setEnrollmentEnrollment(SchoolEnrollment enrollmentEnrollment) {
		this.enrollmentEnrollment = enrollmentEnrollment;
		this.enrollmentEnrollmentWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentEnrollment(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage enrollmentEnrollmentInit() {
		if(!enrollmentEnrollmentWrap.alreadyInitialized) {
			_enrollmentEnrollment(enrollmentEnrollmentWrap);
			if(enrollmentEnrollment == null)
				setEnrollmentEnrollment(enrollmentEnrollmentWrap.o);
		}
		if(enrollmentEnrollment != null)
			enrollmentEnrollment.initDeepForClass(siteRequest_);
		enrollmentEnrollmentWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	////////////////
	// yearSearch //
	////////////////

	/**	 The entity yearSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolYear>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolYear> yearSearch = new SearchList<SchoolYear>();
	@JsonIgnore
	public Wrap<SearchList<SchoolYear>> yearSearchWrap = new Wrap<SearchList<SchoolYear>>().p(this).c(SearchList.class).var("yearSearch").o(yearSearch);

	/**	<br/> The entity yearSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolYear>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSearch">Find the entity yearSearch in Solr</a>
	 * <br/>
	 * @param yearSearch is the entity already constructed. 
	 **/
	protected abstract void _yearSearch(SearchList<SchoolYear> l);

	public SearchList<SchoolYear> getYearSearch() {
		return yearSearch;
	}

	public void setYearSearch(SearchList<SchoolYear> yearSearch) {
		this.yearSearch = yearSearch;
		this.yearSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolYear> staticSetYearSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage yearSearchInit() {
		if(!yearSearchWrap.alreadyInitialized) {
			_yearSearch(yearSearch);
		}
		yearSearch.initDeepForClass(siteRequest_);
		yearSearchWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////
	// year_ //
	///////////

	/**	 The entity year_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolYear year_;
	@JsonIgnore
	public Wrap<SchoolYear> year_Wrap = new Wrap<SchoolYear>().p(this).c(SchoolYear.class).var("year_").o(year_);

	/**	<br/> The entity year_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:year_">Find the entity year_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _year_(Wrap<SchoolYear> c);

	public SchoolYear getYear_() {
		return year_;
	}

	public void setYear_(SchoolYear year_) {
		this.year_ = year_;
		this.year_Wrap.alreadyInitialized = true;
	}
	public static SchoolYear staticSetYear_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage year_Init() {
		if(!year_Wrap.alreadyInitialized) {
			_year_(year_Wrap);
			if(year_ == null)
				setYear_(year_Wrap.o);
		}
		year_Wrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	/////////////
	// yearKey //
	/////////////

	/**	 The entity yearKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long yearKey;
	@JsonIgnore
	public Wrap<Long> yearKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("yearKey").o(yearKey);

	/**	<br/> The entity yearKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Find the entity yearKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearKey(Wrap<Long> c);

	public Long getYearKey() {
		return yearKey;
	}

	public void setYearKey(Long yearKey) {
		this.yearKey = yearKey;
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public void setYearKey(String o) {
		this.yearKey = DesignEmailPage.staticSetYearKey(siteRequest_, o);
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetYearKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected DesignEmailPage yearKeyInit() {
		if(!yearKeyWrap.alreadyInitialized) {
			_yearKey(yearKeyWrap);
			if(yearKey == null)
				setYearKey(yearKeyWrap.o);
		}
		yearKeyWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Long staticSolrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearKey(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrYearKey(siteRequest_, DesignEmailPage.staticSolrYearKey(siteRequest_, DesignEmailPage.staticSetYearKey(siteRequest_, o)));
	}

	public Long solrYearKey() {
		return DesignEmailPage.staticSolrYearKey(siteRequest_, yearKey);
	}

	public String strYearKey() {
		return yearKey == null ? "" : yearKey.toString();
	}

	public String jsonYearKey() {
		return yearKey == null ? "" : yearKey.toString();
	}

	public String nomAffichageYearKey() {
		return null;
	}

	public String htmTooltipYearKey() {
		return null;
	}

	public String htmYearKey() {
		return yearKey == null ? "" : StringEscapeUtils.escapeHtml4(strYearKey());
	}

	///////////////
	// yearStart //
	///////////////

	/**	 The entity yearStart
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearStart;
	@JsonIgnore
	public Wrap<Integer> yearStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearStart").o(yearStart);

	/**	<br/> The entity yearStart
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Find the entity yearStart in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearStart(Wrap<Integer> c);

	public Integer getYearStart() {
		return yearStart;
	}

	public void setYearStart(Integer yearStart) {
		this.yearStart = yearStart;
		this.yearStartWrap.alreadyInitialized = true;
	}
	public void setYearStart(String o) {
		this.yearStart = DesignEmailPage.staticSetYearStart(siteRequest_, o);
		this.yearStartWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearStart(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected DesignEmailPage yearStartInit() {
		if(!yearStartWrap.alreadyInitialized) {
			_yearStart(yearStartWrap);
			if(yearStart == null)
				setYearStart(yearStartWrap.o);
		}
		yearStartWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Integer staticSolrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearStart(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrYearStart(siteRequest_, DesignEmailPage.staticSolrYearStart(siteRequest_, DesignEmailPage.staticSetYearStart(siteRequest_, o)));
	}

	public Integer solrYearStart() {
		return DesignEmailPage.staticSolrYearStart(siteRequest_, yearStart);
	}

	public String strYearStart() {
		return yearStart == null ? "" : yearStart.toString();
	}

	public String jsonYearStart() {
		return yearStart == null ? "" : yearStart.toString();
	}

	public String nomAffichageYearStart() {
		return null;
	}

	public String htmTooltipYearStart() {
		return null;
	}

	public String htmYearStart() {
		return yearStart == null ? "" : StringEscapeUtils.escapeHtml4(strYearStart());
	}

	/////////////
	// yearEnd //
	/////////////

	/**	 The entity yearEnd
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearEnd;
	@JsonIgnore
	public Wrap<Integer> yearEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearEnd").o(yearEnd);

	/**	<br/> The entity yearEnd
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Find the entity yearEnd in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearEnd(Wrap<Integer> c);

	public Integer getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Integer yearEnd) {
		this.yearEnd = yearEnd;
		this.yearEndWrap.alreadyInitialized = true;
	}
	public void setYearEnd(String o) {
		this.yearEnd = DesignEmailPage.staticSetYearEnd(siteRequest_, o);
		this.yearEndWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearEnd(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected DesignEmailPage yearEndInit() {
		if(!yearEndWrap.alreadyInitialized) {
			_yearEnd(yearEndWrap);
			if(yearEnd == null)
				setYearEnd(yearEndWrap.o);
		}
		yearEndWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Integer staticSolrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnd(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrYearEnd(siteRequest_, DesignEmailPage.staticSolrYearEnd(siteRequest_, DesignEmailPage.staticSetYearEnd(siteRequest_, o)));
	}

	public Integer solrYearEnd() {
		return DesignEmailPage.staticSolrYearEnd(siteRequest_, yearEnd);
	}

	public String strYearEnd() {
		return yearEnd == null ? "" : yearEnd.toString();
	}

	public String jsonYearEnd() {
		return yearEnd == null ? "" : yearEnd.toString();
	}

	public String nomAffichageYearEnd() {
		return null;
	}

	public String htmTooltipYearEnd() {
		return null;
	}

	public String htmYearEnd() {
		return yearEnd == null ? "" : StringEscapeUtils.escapeHtml4(strYearEnd());
	}

	/////////////
	// yearVar //
	/////////////

	/**	 The entity yearVar
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String yearVar;
	@JsonIgnore
	public Wrap<String> yearVarWrap = new Wrap<String>().p(this).c(String.class).var("yearVar").o(yearVar);

	/**	<br/> The entity yearVar
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearVar">Find the entity yearVar in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearVar(Wrap<String> c);

	public String getYearVar() {
		return yearVar;
	}
	public void setYearVar(String o) {
		this.yearVar = DesignEmailPage.staticSetYearVar(siteRequest_, o);
		this.yearVarWrap.alreadyInitialized = true;
	}
	public static String staticSetYearVar(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage yearVarInit() {
		if(!yearVarWrap.alreadyInitialized) {
			_yearVar(yearVarWrap);
			if(yearVar == null)
				setYearVar(yearVarWrap.o);
		}
		yearVarWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrYearVar(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrYearVar(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearVar(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrYearVar(siteRequest_, DesignEmailPage.staticSolrYearVar(siteRequest_, DesignEmailPage.staticSetYearVar(siteRequest_, o)));
	}

	public String solrYearVar() {
		return DesignEmailPage.staticSolrYearVar(siteRequest_, yearVar);
	}

	public String strYearVar() {
		return yearVar == null ? "" : yearVar;
	}

	public String jsonYearVar() {
		return yearVar == null ? "" : yearVar;
	}

	public String nomAffichageYearVar() {
		return null;
	}

	public String htmTooltipYearVar() {
		return null;
	}

	public String htmYearVar() {
		return yearVar == null ? "" : StringEscapeUtils.escapeHtml4(strYearVar());
	}

	//////////////////
	// schoolSearch //
	//////////////////

	/**	 The entity schoolSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<School>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<School> schoolSearch = new SearchList<School>();
	@JsonIgnore
	public Wrap<SearchList<School>> schoolSearchWrap = new Wrap<SearchList<School>>().p(this).c(SearchList.class).var("schoolSearch").o(schoolSearch);

	/**	<br/> The entity schoolSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<School>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSearch">Find the entity schoolSearch in Solr</a>
	 * <br/>
	 * @param schoolSearch is the entity already constructed. 
	 **/
	protected abstract void _schoolSearch(SearchList<School> l);

	public SearchList<School> getSchoolSearch() {
		return schoolSearch;
	}

	public void setSchoolSearch(SearchList<School> schoolSearch) {
		this.schoolSearch = schoolSearch;
		this.schoolSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<School> staticSetSchoolSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage schoolSearchInit() {
		if(!schoolSearchWrap.alreadyInitialized) {
			_schoolSearch(schoolSearch);
		}
		schoolSearch.initDeepForClass(siteRequest_);
		schoolSearchWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	/////////////
	// school_ //
	/////////////

	/**	 The entity school_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected School school_;
	@JsonIgnore
	public Wrap<School> school_Wrap = new Wrap<School>().p(this).c(School.class).var("school_").o(school_);

	/**	<br/> The entity school_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:school_">Find the entity school_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _school_(Wrap<School> c);

	public School getSchool_() {
		return school_;
	}

	public void setSchool_(School school_) {
		this.school_ = school_;
		this.school_Wrap.alreadyInitialized = true;
	}
	public static School staticSetSchool_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage school_Init() {
		if(!school_Wrap.alreadyInitialized) {
			_school_(school_Wrap);
			if(school_ == null)
				setSchool_(school_Wrap.o);
		}
		school_Wrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////////
	// schoolKey //
	///////////////

	/**	 The entity schoolKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long schoolKey;
	@JsonIgnore
	public Wrap<Long> schoolKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("schoolKey").o(schoolKey);

	/**	<br/> The entity schoolKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Find the entity schoolKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolKey(Wrap<Long> c);

	public Long getSchoolKey() {
		return schoolKey;
	}

	public void setSchoolKey(Long schoolKey) {
		this.schoolKey = schoolKey;
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public void setSchoolKey(String o) {
		this.schoolKey = DesignEmailPage.staticSetSchoolKey(siteRequest_, o);
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected DesignEmailPage schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Long staticSolrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrSchoolKey(siteRequest_, DesignEmailPage.staticSolrSchoolKey(siteRequest_, DesignEmailPage.staticSetSchoolKey(siteRequest_, o)));
	}

	public Long solrSchoolKey() {
		return DesignEmailPage.staticSolrSchoolKey(siteRequest_, schoolKey);
	}

	public String strSchoolKey() {
		return schoolKey == null ? "" : schoolKey.toString();
	}

	public String jsonSchoolKey() {
		return schoolKey == null ? "" : schoolKey.toString();
	}

	public String nomAffichageSchoolKey() {
		return null;
	}

	public String htmTooltipSchoolKey() {
		return null;
	}

	public String htmSchoolKey() {
		return schoolKey == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolKey());
	}

	////////////////
	// schoolName //
	////////////////

	/**	 The entity schoolName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolName;
	@JsonIgnore
	public Wrap<String> schoolNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolName").o(schoolName);

	/**	<br/> The entity schoolName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Find the entity schoolName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolName(Wrap<String> c);

	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String o) {
		this.schoolName = DesignEmailPage.staticSetSchoolName(siteRequest_, o);
		this.schoolNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage schoolNameInit() {
		if(!schoolNameWrap.alreadyInitialized) {
			_schoolName(schoolNameWrap);
			if(schoolName == null)
				setSchoolName(schoolNameWrap.o);
		}
		schoolNameWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrSchoolName(siteRequest_, DesignEmailPage.staticSolrSchoolName(siteRequest_, DesignEmailPage.staticSetSchoolName(siteRequest_, o)));
	}

	public String solrSchoolName() {
		return DesignEmailPage.staticSolrSchoolName(siteRequest_, schoolName);
	}

	public String strSchoolName() {
		return schoolName == null ? "" : schoolName;
	}

	public String jsonSchoolName() {
		return schoolName == null ? "" : schoolName;
	}

	public String nomAffichageSchoolName() {
		return null;
	}

	public String htmTooltipSchoolName() {
		return null;
	}

	public String htmSchoolName() {
		return schoolName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolName());
	}

	////////////////////////
	// schoolCompleteName //
	////////////////////////

	/**	 The entity schoolCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolCompleteName;
	@JsonIgnore
	public Wrap<String> schoolCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolCompleteName").o(schoolCompleteName);

	/**	<br/> The entity schoolCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Find the entity schoolCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolCompleteName(Wrap<String> c);

	public String getSchoolCompleteName() {
		return schoolCompleteName;
	}
	public void setSchoolCompleteName(String o) {
		this.schoolCompleteName = DesignEmailPage.staticSetSchoolCompleteName(siteRequest_, o);
		this.schoolCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage schoolCompleteNameInit() {
		if(!schoolCompleteNameWrap.alreadyInitialized) {
			_schoolCompleteName(schoolCompleteNameWrap);
			if(schoolCompleteName == null)
				setSchoolCompleteName(schoolCompleteNameWrap.o);
		}
		schoolCompleteNameWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrSchoolCompleteName(siteRequest_, DesignEmailPage.staticSolrSchoolCompleteName(siteRequest_, DesignEmailPage.staticSetSchoolCompleteName(siteRequest_, o)));
	}

	public String solrSchoolCompleteName() {
		return DesignEmailPage.staticSolrSchoolCompleteName(siteRequest_, schoolCompleteName);
	}

	public String strSchoolCompleteName() {
		return schoolCompleteName == null ? "" : schoolCompleteName;
	}

	public String jsonSchoolCompleteName() {
		return schoolCompleteName == null ? "" : schoolCompleteName;
	}

	public String nomAffichageSchoolCompleteName() {
		return null;
	}

	public String htmTooltipSchoolCompleteName() {
		return null;
	}

	public String htmSchoolCompleteName() {
		return schoolCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolCompleteName());
	}

	////////////////////
	// schoolLocation //
	////////////////////

	/**	 The entity schoolLocation
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation;
	@JsonIgnore
	public Wrap<String> schoolLocationWrap = new Wrap<String>().p(this).c(String.class).var("schoolLocation").o(schoolLocation);

	/**	<br/> The entity schoolLocation
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Find the entity schoolLocation in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolLocation(Wrap<String> c);

	public String getSchoolLocation() {
		return schoolLocation;
	}
	public void setSchoolLocation(String o) {
		this.schoolLocation = DesignEmailPage.staticSetSchoolLocation(siteRequest_, o);
		this.schoolLocationWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage schoolLocationInit() {
		if(!schoolLocationWrap.alreadyInitialized) {
			_schoolLocation(schoolLocationWrap);
			if(schoolLocation == null)
				setSchoolLocation(schoolLocationWrap.o);
		}
		schoolLocationWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrSchoolLocation(siteRequest_, DesignEmailPage.staticSolrSchoolLocation(siteRequest_, DesignEmailPage.staticSetSchoolLocation(siteRequest_, o)));
	}

	public String solrSchoolLocation() {
		return DesignEmailPage.staticSolrSchoolLocation(siteRequest_, schoolLocation);
	}

	public String strSchoolLocation() {
		return schoolLocation == null ? "" : schoolLocation;
	}

	public String jsonSchoolLocation() {
		return schoolLocation == null ? "" : schoolLocation;
	}

	public String nomAffichageSchoolLocation() {
		return null;
	}

	public String htmTooltipSchoolLocation() {
		return null;
	}

	public String htmSchoolLocation() {
		return schoolLocation == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolLocation());
	}

	///////////////////
	// schoolAddress //
	///////////////////

	/**	 The entity schoolAddress
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAddress;
	@JsonIgnore
	public Wrap<String> schoolAddressWrap = new Wrap<String>().p(this).c(String.class).var("schoolAddress").o(schoolAddress);

	/**	<br/> The entity schoolAddress
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Find the entity schoolAddress in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAddress(Wrap<String> c);

	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String o) {
		this.schoolAddress = DesignEmailPage.staticSetSchoolAddress(siteRequest_, o);
		this.schoolAddressWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage schoolAddressInit() {
		if(!schoolAddressWrap.alreadyInitialized) {
			_schoolAddress(schoolAddressWrap);
			if(schoolAddress == null)
				setSchoolAddress(schoolAddressWrap.o);
		}
		schoolAddressWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrSchoolAddress(siteRequest_, DesignEmailPage.staticSolrSchoolAddress(siteRequest_, DesignEmailPage.staticSetSchoolAddress(siteRequest_, o)));
	}

	public String solrSchoolAddress() {
		return DesignEmailPage.staticSolrSchoolAddress(siteRequest_, schoolAddress);
	}

	public String strSchoolAddress() {
		return schoolAddress == null ? "" : schoolAddress;
	}

	public String jsonSchoolAddress() {
		return schoolAddress == null ? "" : schoolAddress;
	}

	public String nomAffichageSchoolAddress() {
		return null;
	}

	public String htmTooltipSchoolAddress() {
		return null;
	}

	public String htmSchoolAddress() {
		return schoolAddress == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolAddress());
	}

	///////////////////////
	// schoolPhoneNumber //
	///////////////////////

	/**	 The entity schoolPhoneNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolPhoneNumber;
	@JsonIgnore
	public Wrap<String> schoolPhoneNumberWrap = new Wrap<String>().p(this).c(String.class).var("schoolPhoneNumber").o(schoolPhoneNumber);

	/**	<br/> The entity schoolPhoneNumber
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Find the entity schoolPhoneNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolPhoneNumber(Wrap<String> c);

	public String getSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}
	public void setSchoolPhoneNumber(String o) {
		this.schoolPhoneNumber = DesignEmailPage.staticSetSchoolPhoneNumber(siteRequest_, o);
		this.schoolPhoneNumberWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage schoolPhoneNumberInit() {
		if(!schoolPhoneNumberWrap.alreadyInitialized) {
			_schoolPhoneNumber(schoolPhoneNumberWrap);
			if(schoolPhoneNumber == null)
				setSchoolPhoneNumber(schoolPhoneNumberWrap.o);
		}
		schoolPhoneNumberWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrSchoolPhoneNumber(siteRequest_, DesignEmailPage.staticSolrSchoolPhoneNumber(siteRequest_, DesignEmailPage.staticSetSchoolPhoneNumber(siteRequest_, o)));
	}

	public String solrSchoolPhoneNumber() {
		return DesignEmailPage.staticSolrSchoolPhoneNumber(siteRequest_, schoolPhoneNumber);
	}

	public String strSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : schoolPhoneNumber;
	}

	public String jsonSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : schoolPhoneNumber;
	}

	public String nomAffichageSchoolPhoneNumber() {
		return null;
	}

	public String htmTooltipSchoolPhoneNumber() {
		return null;
	}

	public String htmSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolPhoneNumber());
	}

	/////////////////////////////
	// schoolAdministratorName //
	/////////////////////////////

	/**	 The entity schoolAdministratorName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAdministratorName;
	@JsonIgnore
	public Wrap<String> schoolAdministratorNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolAdministratorName").o(schoolAdministratorName);

	/**	<br/> The entity schoolAdministratorName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Find the entity schoolAdministratorName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAdministratorName(Wrap<String> c);

	public String getSchoolAdministratorName() {
		return schoolAdministratorName;
	}
	public void setSchoolAdministratorName(String o) {
		this.schoolAdministratorName = DesignEmailPage.staticSetSchoolAdministratorName(siteRequest_, o);
		this.schoolAdministratorNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage schoolAdministratorNameInit() {
		if(!schoolAdministratorNameWrap.alreadyInitialized) {
			_schoolAdministratorName(schoolAdministratorNameWrap);
			if(schoolAdministratorName == null)
				setSchoolAdministratorName(schoolAdministratorNameWrap.o);
		}
		schoolAdministratorNameWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrSchoolAdministratorName(siteRequest_, DesignEmailPage.staticSolrSchoolAdministratorName(siteRequest_, DesignEmailPage.staticSetSchoolAdministratorName(siteRequest_, o)));
	}

	public String solrSchoolAdministratorName() {
		return DesignEmailPage.staticSolrSchoolAdministratorName(siteRequest_, schoolAdministratorName);
	}

	public String strSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : schoolAdministratorName;
	}

	public String jsonSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : schoolAdministratorName;
	}

	public String nomAffichageSchoolAdministratorName() {
		return null;
	}

	public String htmTooltipSchoolAdministratorName() {
		return null;
	}

	public String htmSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolAdministratorName());
	}

	/////////////////////
	// seasonStartDate //
	/////////////////////

	/**	 The entity seasonStartDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate seasonStartDate;
	@JsonIgnore
	public Wrap<LocalDate> seasonStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("seasonStartDate").o(seasonStartDate);

	/**	<br/> The entity seasonStartDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Find the entity seasonStartDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonStartDate(Wrap<LocalDate> c);

	public LocalDate getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(LocalDate seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public void setSeasonStartDate(Instant o) {
		this.seasonStartDate = o == null ? null : LocalDate.from(o);
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSeasonStartDate(String o) {
		this.seasonStartDate = DesignEmailPage.staticSetSeasonStartDate(siteRequest_, o);
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSeasonStartDate(Date o) {
		this.seasonStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	protected DesignEmailPage seasonStartDateInit() {
		if(!seasonStartDateWrap.alreadyInitialized) {
			_seasonStartDate(seasonStartDateWrap);
			if(seasonStartDate == null)
				setSeasonStartDate(seasonStartDateWrap.o);
		}
		seasonStartDateWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Date staticSolrSeasonStartDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSeasonStartDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrSeasonStartDate(siteRequest_, DesignEmailPage.staticSolrSeasonStartDate(siteRequest_, DesignEmailPage.staticSetSeasonStartDate(siteRequest_, o)));
	}

	public Date solrSeasonStartDate() {
		return DesignEmailPage.staticSolrSeasonStartDate(siteRequest_, seasonStartDate);
	}

	public String strSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSeasonStartDate() {
		return null;
	}

	public String htmTooltipSeasonStartDate() {
		return null;
	}

	public String htmSeasonStartDate() {
		return seasonStartDate == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonStartDate());
	}

	//////////
	// mom_ //
	//////////

	/**	 The entity mom_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolMom mom_;
	@JsonIgnore
	public Wrap<SchoolMom> mom_Wrap = new Wrap<SchoolMom>().p(this).c(SchoolMom.class).var("mom_").o(mom_);

	/**	<br/> The entity mom_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:mom_">Find the entity mom_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _mom_(Wrap<SchoolMom> c);

	public SchoolMom getMom_() {
		return mom_;
	}

	public void setMom_(SchoolMom mom_) {
		this.mom_ = mom_;
		this.mom_Wrap.alreadyInitialized = true;
	}
	public static SchoolMom staticSetMom_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage mom_Init() {
		if(!mom_Wrap.alreadyInitialized) {
			_mom_(mom_Wrap);
			if(mom_ == null)
				setMom_(mom_Wrap.o);
		}
		mom_Wrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////
	// dad_ //
	//////////

	/**	 The entity dad_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolDad dad_;
	@JsonIgnore
	public Wrap<SchoolDad> dad_Wrap = new Wrap<SchoolDad>().p(this).c(SchoolDad.class).var("dad_").o(dad_);

	/**	<br/> The entity dad_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dad_">Find the entity dad_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dad_(Wrap<SchoolDad> c);

	public SchoolDad getDad_() {
		return dad_;
	}

	public void setDad_(SchoolDad dad_) {
		this.dad_ = dad_;
		this.dad_Wrap.alreadyInitialized = true;
	}
	public static SchoolDad staticSetDad_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage dad_Init() {
		if(!dad_Wrap.alreadyInitialized) {
			_dad_(dad_Wrap);
			if(dad_ == null)
				setDad_(dad_Wrap.o);
		}
		dad_Wrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////////
	// guardian_ //
	///////////////

	/**	 The entity guardian_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolGuardian guardian_;
	@JsonIgnore
	public Wrap<SchoolGuardian> guardian_Wrap = new Wrap<SchoolGuardian>().p(this).c(SchoolGuardian.class).var("guardian_").o(guardian_);

	/**	<br/> The entity guardian_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardian_">Find the entity guardian_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _guardian_(Wrap<SchoolGuardian> c);

	public SchoolGuardian getGuardian_() {
		return guardian_;
	}

	public void setGuardian_(SchoolGuardian guardian_) {
		this.guardian_ = guardian_;
		this.guardian_Wrap.alreadyInitialized = true;
	}
	public static SchoolGuardian staticSetGuardian_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage guardian_Init() {
		if(!guardian_Wrap.alreadyInitialized) {
			_guardian_(guardian_Wrap);
			if(guardian_ == null)
				setGuardian_(guardian_Wrap.o);
		}
		guardian_Wrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	/////////////////
	// blockSearch //
	/////////////////

	/**	 The entity blockSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolBlock>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolBlock> blockSearch = new SearchList<SchoolBlock>();
	@JsonIgnore
	public Wrap<SearchList<SchoolBlock>> blockSearchWrap = new Wrap<SearchList<SchoolBlock>>().p(this).c(SearchList.class).var("blockSearch").o(blockSearch);

	/**	<br/> The entity blockSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolBlock>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSearch">Find the entity blockSearch in Solr</a>
	 * <br/>
	 * @param blockSearch is the entity already constructed. 
	 **/
	protected abstract void _blockSearch(SearchList<SchoolBlock> l);

	public SearchList<SchoolBlock> getBlockSearch() {
		return blockSearch;
	}

	public void setBlockSearch(SearchList<SchoolBlock> blockSearch) {
		this.blockSearch = blockSearch;
		this.blockSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolBlock> staticSetBlockSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage blockSearchInit() {
		if(!blockSearchWrap.alreadyInitialized) {
			_blockSearch(blockSearch);
		}
		blockSearch.initDeepForClass(siteRequest_);
		blockSearchWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	////////////
	// blocks //
	////////////

	/**	 The entity blocks
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolBlock> blocks;
	@JsonIgnore
	public Wrap<List<SchoolBlock>> blocksWrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("blocks").o(blocks);

	/**	<br/> The entity blocks
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blocks">Find the entity blocks in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blocks(Wrap<List<SchoolBlock>> c);

	public List<SchoolBlock> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<SchoolBlock> blocks) {
		this.blocks = blocks;
		this.blocksWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetBlocks(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addBlocks(SchoolBlock...objets) {
		for(SchoolBlock o : objets) {
			addBlocks(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addBlocks(SchoolBlock o) {
		if(o != null && !blocks.contains(o))
			this.blocks.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage blocksInit() {
		if(!blocksWrap.alreadyInitialized) {
			_blocks(blocksWrap);
			if(blocks == null)
				setBlocks(blocksWrap.o);
		}
		blocksWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////
	// seasonBlocks //
	//////////////////

	/**	 The entity seasonBlocks
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolBlock>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolBlock> seasonBlocks = new ArrayList<SchoolBlock>();
	@JsonIgnore
	public Wrap<List<SchoolBlock>> seasonBlocksWrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("seasonBlocks").o(seasonBlocks);

	/**	<br/> The entity seasonBlocks
	 *  It is constructed before being initialized with the constructor by default List<SchoolBlock>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonBlocks">Find the entity seasonBlocks in Solr</a>
	 * <br/>
	 * @param seasonBlocks is the entity already constructed. 
	 **/
	protected abstract void _seasonBlocks(List<SchoolBlock> l);

	public List<SchoolBlock> getSeasonBlocks() {
		return seasonBlocks;
	}

	public void setSeasonBlocks(List<SchoolBlock> seasonBlocks) {
		this.seasonBlocks = seasonBlocks;
		this.seasonBlocksWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetSeasonBlocks(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addSeasonBlocks(SchoolBlock...objets) {
		for(SchoolBlock o : objets) {
			addSeasonBlocks(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addSeasonBlocks(SchoolBlock o) {
		if(o != null && !seasonBlocks.contains(o))
			this.seasonBlocks.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage seasonBlocksInit() {
		if(!seasonBlocksWrap.alreadyInitialized) {
			_seasonBlocks(seasonBlocks);
		}
		seasonBlocksWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	/////////////////
	// seasonBlock //
	/////////////////

	/**	 The entity seasonBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolBlock seasonBlock;
	@JsonIgnore
	public Wrap<SchoolBlock> seasonBlockWrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("seasonBlock").o(seasonBlock);

	/**	<br/> The entity seasonBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonBlock">Find the entity seasonBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonBlock(Wrap<SchoolBlock> c);

	public SchoolBlock getSeasonBlock() {
		return seasonBlock;
	}

	public void setSeasonBlock(SchoolBlock seasonBlock) {
		this.seasonBlock = seasonBlock;
		this.seasonBlockWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetSeasonBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage seasonBlockInit() {
		if(!seasonBlockWrap.alreadyInitialized) {
			_seasonBlock(seasonBlockWrap);
			if(seasonBlock == null)
				setSeasonBlock(seasonBlockWrap.o);
		}
		if(seasonBlock != null)
			seasonBlock.initDeepForClass(siteRequest_);
		seasonBlockWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////
	// sessionBlock //
	//////////////////

	/**	 The entity sessionBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolBlock sessionBlock;
	@JsonIgnore
	public Wrap<SchoolBlock> sessionBlockWrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("sessionBlock").o(sessionBlock);

	/**	<br/> The entity sessionBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionBlock">Find the entity sessionBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionBlock(Wrap<SchoolBlock> c);

	public SchoolBlock getSessionBlock() {
		return sessionBlock;
	}

	public void setSessionBlock(SchoolBlock sessionBlock) {
		this.sessionBlock = sessionBlock;
		this.sessionBlockWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetSessionBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage sessionBlockInit() {
		if(!sessionBlockWrap.alreadyInitialized) {
			_sessionBlock(sessionBlockWrap);
			if(sessionBlock == null)
				setSessionBlock(sessionBlockWrap.o);
		}
		if(sessionBlock != null)
			sessionBlock.initDeepForClass(siteRequest_);
		sessionBlockWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////
	// ageBlock //
	//////////////

	/**	 The entity ageBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolBlock ageBlock;
	@JsonIgnore
	public Wrap<SchoolBlock> ageBlockWrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("ageBlock").o(ageBlock);

	/**	<br/> The entity ageBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageBlock">Find the entity ageBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageBlock(Wrap<SchoolBlock> c);

	public SchoolBlock getAgeBlock() {
		return ageBlock;
	}

	public void setAgeBlock(SchoolBlock ageBlock) {
		this.ageBlock = ageBlock;
		this.ageBlockWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetAgeBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage ageBlockInit() {
		if(!ageBlockWrap.alreadyInitialized) {
			_ageBlock(ageBlockWrap);
			if(ageBlock == null)
				setAgeBlock(ageBlockWrap.o);
		}
		if(ageBlock != null)
			ageBlock.initDeepForClass(siteRequest_);
		ageBlockWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	////////////////
	// blockBlock //
	////////////////

	/**	 The entity blockBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolBlock blockBlock;
	@JsonIgnore
	public Wrap<SchoolBlock> blockBlockWrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("blockBlock").o(blockBlock);

	/**	<br/> The entity blockBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockBlock">Find the entity blockBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockBlock(Wrap<SchoolBlock> c);

	public SchoolBlock getBlockBlock() {
		return blockBlock;
	}

	public void setBlockBlock(SchoolBlock blockBlock) {
		this.blockBlock = blockBlock;
		this.blockBlockWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetBlockBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage blockBlockInit() {
		if(!blockBlockWrap.alreadyInitialized) {
			_blockBlock(blockBlockWrap);
			if(blockBlock == null)
				setBlockBlock(blockBlockWrap.o);
		}
		if(blockBlock != null)
			blockBlock.initDeepForClass(siteRequest_);
		blockBlockWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////////////
	// paymentSearch //
	///////////////////

	/**	 The entity paymentSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolPayment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolPayment> paymentSearch = new SearchList<SchoolPayment>();
	@JsonIgnore
	public Wrap<SearchList<SchoolPayment>> paymentSearchWrap = new Wrap<SearchList<SchoolPayment>>().p(this).c(SearchList.class).var("paymentSearch").o(paymentSearch);

	/**	<br/> The entity paymentSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolPayment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentSearch">Find the entity paymentSearch in Solr</a>
	 * <br/>
	 * @param paymentSearch is the entity already constructed. 
	 **/
	protected abstract void _paymentSearch(SearchList<SchoolPayment> l);

	public SearchList<SchoolPayment> getPaymentSearch() {
		return paymentSearch;
	}

	public void setPaymentSearch(SearchList<SchoolPayment> paymentSearch) {
		this.paymentSearch = paymentSearch;
		this.paymentSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolPayment> staticSetPaymentSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage paymentSearchInit() {
		if(!paymentSearchWrap.alreadyInitialized) {
			_paymentSearch(paymentSearch);
		}
		paymentSearch.initDeepForClass(siteRequest_);
		paymentSearchWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////////
	// payments_ //
	///////////////

	/**	 The entity payments_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolPayment> payments_;
	@JsonIgnore
	public Wrap<List<SchoolPayment>> payments_Wrap = new Wrap<List<SchoolPayment>>().p(this).c(List.class).var("payments_").o(payments_);

	/**	<br/> The entity payments_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:payments_">Find the entity payments_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _payments_(Wrap<List<SchoolPayment>> c);

	public List<SchoolPayment> getPayments_() {
		return payments_;
	}

	public void setPayments_(List<SchoolPayment> payments_) {
		this.payments_ = payments_;
		this.payments_Wrap.alreadyInitialized = true;
	}
	public static SchoolPayment staticSetPayments_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addPayments_(SchoolPayment...objets) {
		for(SchoolPayment o : objets) {
			addPayments_(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addPayments_(SchoolPayment o) {
		if(o != null && !payments_.contains(o))
			this.payments_.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage payments_Init() {
		if(!payments_Wrap.alreadyInitialized) {
			_payments_(payments_Wrap);
			if(payments_ == null)
				setPayments_(payments_Wrap.o);
		}
		payments_Wrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////
	// payment_ //
	//////////////

	/**	 The entity payment_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolPayment payment_;
	@JsonIgnore
	public Wrap<SchoolPayment> payment_Wrap = new Wrap<SchoolPayment>().p(this).c(SchoolPayment.class).var("payment_").o(payment_);

	/**	<br/> The entity payment_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:payment_">Find the entity payment_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _payment_(Wrap<SchoolPayment> c);

	public SchoolPayment getPayment_() {
		return payment_;
	}

	public void setPayment_(SchoolPayment payment_) {
		this.payment_ = payment_;
		this.payment_Wrap.alreadyInitialized = true;
	}
	public static SchoolPayment staticSetPayment_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage payment_Init() {
		if(!payment_Wrap.alreadyInitialized) {
			_payment_(payment_Wrap);
			if(payment_ == null)
				setPayment_(payment_Wrap.o);
		}
		payment_Wrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////////////
	// paymentBlocks //
	///////////////////

	/**	 The entity paymentBlocks
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolPayment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolPayment> paymentBlocks = new ArrayList<SchoolPayment>();
	@JsonIgnore
	public Wrap<List<SchoolPayment>> paymentBlocksWrap = new Wrap<List<SchoolPayment>>().p(this).c(List.class).var("paymentBlocks").o(paymentBlocks);

	/**	<br/> The entity paymentBlocks
	 *  It is constructed before being initialized with the constructor by default List<SchoolPayment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentBlocks">Find the entity paymentBlocks in Solr</a>
	 * <br/>
	 * @param paymentBlocks is the entity already constructed. 
	 **/
	protected abstract void _paymentBlocks(List<SchoolPayment> c);

	public List<SchoolPayment> getPaymentBlocks() {
		return paymentBlocks;
	}

	public void setPaymentBlocks(List<SchoolPayment> paymentBlocks) {
		this.paymentBlocks = paymentBlocks;
		this.paymentBlocksWrap.alreadyInitialized = true;
	}
	public static SchoolPayment staticSetPaymentBlocks(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addPaymentBlocks(SchoolPayment...objets) {
		for(SchoolPayment o : objets) {
			addPaymentBlocks(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addPaymentBlocks(SchoolPayment o) {
		if(o != null && !paymentBlocks.contains(o))
			this.paymentBlocks.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage paymentBlocksInit() {
		if(!paymentBlocksWrap.alreadyInitialized) {
			_paymentBlocks(paymentBlocks);
		}
		paymentBlocksWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////////////
	// paymentGroups //
	///////////////////

	/**	 The entity paymentGroups
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolPayment> paymentGroups;
	@JsonIgnore
	public Wrap<List<SchoolPayment>> paymentGroupsWrap = new Wrap<List<SchoolPayment>>().p(this).c(List.class).var("paymentGroups").o(paymentGroups);

	/**	<br/> The entity paymentGroups
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentGroups">Find the entity paymentGroups in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentGroups(Wrap<List<SchoolPayment>> c);

	public List<SchoolPayment> getPaymentGroups() {
		return paymentGroups;
	}

	public void setPaymentGroups(List<SchoolPayment> paymentGroups) {
		this.paymentGroups = paymentGroups;
		this.paymentGroupsWrap.alreadyInitialized = true;
	}
	public static SchoolPayment staticSetPaymentGroups(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addPaymentGroups(SchoolPayment...objets) {
		for(SchoolPayment o : objets) {
			addPaymentGroups(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addPaymentGroups(SchoolPayment o) {
		if(o != null && !paymentGroups.contains(o))
			this.paymentGroups.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage paymentGroupsInit() {
		if(!paymentGroupsWrap.alreadyInitialized) {
			_paymentGroups(paymentGroupsWrap);
			if(paymentGroups == null)
				setPaymentGroups(paymentGroupsWrap.o);
		}
		paymentGroupsWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////
	// paymentBlock //
	//////////////////

	/**	 The entity paymentBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolPayment paymentBlock;
	@JsonIgnore
	public Wrap<SchoolPayment> paymentBlockWrap = new Wrap<SchoolPayment>().p(this).c(SchoolPayment.class).var("paymentBlock").o(paymentBlock);

	/**	<br/> The entity paymentBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentBlock">Find the entity paymentBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentBlock(Wrap<SchoolPayment> c);

	public SchoolPayment getPaymentBlock() {
		return paymentBlock;
	}

	public void setPaymentBlock(SchoolPayment paymentBlock) {
		this.paymentBlock = paymentBlock;
		this.paymentBlockWrap.alreadyInitialized = true;
	}
	public static SchoolPayment staticSetPaymentBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage paymentBlockInit() {
		if(!paymentBlockWrap.alreadyInitialized) {
			_paymentBlock(paymentBlockWrap);
			if(paymentBlock == null)
				setPaymentBlock(paymentBlockWrap.o);
		}
		if(paymentBlock != null)
			paymentBlock.initDeepForClass(siteRequest_);
		paymentBlockWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////////
	// paymentGroup //
	//////////////////

	/**	 The entity paymentGroup
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolPayment paymentGroup;
	@JsonIgnore
	public Wrap<SchoolPayment> paymentGroupWrap = new Wrap<SchoolPayment>().p(this).c(SchoolPayment.class).var("paymentGroup").o(paymentGroup);

	/**	<br/> The entity paymentGroup
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentGroup">Find the entity paymentGroup in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentGroup(Wrap<SchoolPayment> c);

	public SchoolPayment getPaymentGroup() {
		return paymentGroup;
	}

	public void setPaymentGroup(SchoolPayment paymentGroup) {
		this.paymentGroup = paymentGroup;
		this.paymentGroupWrap.alreadyInitialized = true;
	}
	public static SchoolPayment staticSetPaymentGroup(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage paymentGroupInit() {
		if(!paymentGroupWrap.alreadyInitialized) {
			_paymentGroup(paymentGroupWrap);
			if(paymentGroup == null)
				setPaymentGroup(paymentGroupWrap.o);
		}
		if(paymentGroup != null)
			paymentGroup.initDeepForClass(siteRequest_);
		paymentGroupWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	////////////////////
	// paymentPayment //
	////////////////////

	/**	 The entity paymentPayment
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolPayment paymentPayment;
	@JsonIgnore
	public Wrap<SchoolPayment> paymentPaymentWrap = new Wrap<SchoolPayment>().p(this).c(SchoolPayment.class).var("paymentPayment").o(paymentPayment);

	/**	<br/> The entity paymentPayment
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentPayment">Find the entity paymentPayment in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentPayment(Wrap<SchoolPayment> c);

	public SchoolPayment getPaymentPayment() {
		return paymentPayment;
	}

	public void setPaymentPayment(SchoolPayment paymentPayment) {
		this.paymentPayment = paymentPayment;
		this.paymentPaymentWrap.alreadyInitialized = true;
	}
	public static SchoolPayment staticSetPaymentPayment(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage paymentPaymentInit() {
		if(!paymentPaymentWrap.alreadyInitialized) {
			_paymentPayment(paymentPaymentWrap);
			if(paymentPayment == null)
				setPaymentPayment(paymentPaymentWrap.o);
		}
		if(paymentPayment != null)
			paymentPayment.initDeepForClass(siteRequest_);
		paymentPaymentWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////////////
	// paymentFacets //
	///////////////////

	/**	 The entity paymentFacets
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SimpleOrderedMap paymentFacets;
	@JsonIgnore
	public Wrap<SimpleOrderedMap> paymentFacetsWrap = new Wrap<SimpleOrderedMap>().p(this).c(SimpleOrderedMap.class).var("paymentFacets").o(paymentFacets);

	/**	<br/> The entity paymentFacets
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentFacets">Find the entity paymentFacets in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentFacets(Wrap<SimpleOrderedMap> c);

	public SimpleOrderedMap getPaymentFacets() {
		return paymentFacets;
	}

	public void setPaymentFacets(SimpleOrderedMap paymentFacets) {
		this.paymentFacets = paymentFacets;
		this.paymentFacetsWrap.alreadyInitialized = true;
	}
	public static SimpleOrderedMap staticSetPaymentFacets(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage paymentFacetsInit() {
		if(!paymentFacetsWrap.alreadyInitialized) {
			_paymentFacets(paymentFacetsWrap);
			if(paymentFacets == null)
				setPaymentFacets(paymentFacetsWrap.o);
		}
		paymentFacetsWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	////////////////////
	// paymentLastStr //
	////////////////////

	/**	 The entity paymentLastStr
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paymentLastStr;
	@JsonIgnore
	public Wrap<String> paymentLastStrWrap = new Wrap<String>().p(this).c(String.class).var("paymentLastStr").o(paymentLastStr);

	/**	<br/> The entity paymentLastStr
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentLastStr">Find the entity paymentLastStr in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentLastStr(Wrap<String> c);

	public String getPaymentLastStr() {
		return paymentLastStr;
	}
	public void setPaymentLastStr(String o) {
		this.paymentLastStr = DesignEmailPage.staticSetPaymentLastStr(siteRequest_, o);
		this.paymentLastStrWrap.alreadyInitialized = true;
	}
	public static String staticSetPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage paymentLastStrInit() {
		if(!paymentLastStrWrap.alreadyInitialized) {
			_paymentLastStr(paymentLastStrWrap);
			if(paymentLastStr == null)
				setPaymentLastStr(paymentLastStrWrap.o);
		}
		paymentLastStrWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrPaymentLastStr(siteRequest_, DesignEmailPage.staticSolrPaymentLastStr(siteRequest_, DesignEmailPage.staticSetPaymentLastStr(siteRequest_, o)));
	}

	public String solrPaymentLastStr() {
		return DesignEmailPage.staticSolrPaymentLastStr(siteRequest_, paymentLastStr);
	}

	public String strPaymentLastStr() {
		return paymentLastStr == null ? "" : paymentLastStr;
	}

	public String jsonPaymentLastStr() {
		return paymentLastStr == null ? "" : paymentLastStr;
	}

	public String nomAffichagePaymentLastStr() {
		return null;
	}

	public String htmTooltipPaymentLastStr() {
		return null;
	}

	public String htmPaymentLastStr() {
		return paymentLastStr == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentLastStr());
	}

	///////////////////
	// paymentAmount //
	///////////////////

	/**	 The entity paymentAmount
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal paymentAmount;
	@JsonIgnore
	public Wrap<BigDecimal> paymentAmountWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("paymentAmount").o(paymentAmount);

	/**	<br/> The entity paymentAmount
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentAmount">Find the entity paymentAmount in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentAmount(Wrap<BigDecimal> c);

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentAmount(String o) {
		this.paymentAmount = DesignEmailPage.staticSetPaymentAmount(siteRequest_, o);
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetPaymentAmount(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setPaymentAmount(Double o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentAmount(Integer o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	protected DesignEmailPage paymentAmountInit() {
		if(!paymentAmountWrap.alreadyInitialized) {
			_paymentAmount(paymentAmountWrap);
			if(paymentAmount == null)
				setPaymentAmount(paymentAmountWrap.o);
		}
		paymentAmountWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Double staticSolrPaymentAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaymentAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentAmount(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrPaymentAmount(siteRequest_, DesignEmailPage.staticSolrPaymentAmount(siteRequest_, DesignEmailPage.staticSetPaymentAmount(siteRequest_, o)));
	}

	public Double solrPaymentAmount() {
		return DesignEmailPage.staticSolrPaymentAmount(siteRequest_, paymentAmount);
	}

	public String strPaymentAmount() {
		return paymentAmount == null ? "" : paymentAmount.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonPaymentAmount() {
		return paymentAmount == null ? "" : paymentAmount.toString();
	}

	public String nomAffichagePaymentAmount() {
		return null;
	}

	public String htmTooltipPaymentAmount() {
		return null;
	}

	public String htmPaymentAmount() {
		return paymentAmount == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentAmount());
	}

	//////////////////
	// chargeAmount //
	//////////////////

	/**	 The entity chargeAmount
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmount;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmount").o(chargeAmount);

	/**	<br/> The entity chargeAmount
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmount">Find the entity chargeAmount in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmount(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	public void setChargeAmount(String o) {
		this.chargeAmount = DesignEmailPage.staticSetChargeAmount(siteRequest_, o);
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmount(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmount(Double o) {
			this.chargeAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	public void setChargeAmount(Integer o) {
			this.chargeAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	protected DesignEmailPage chargeAmountInit() {
		if(!chargeAmountWrap.alreadyInitialized) {
			_chargeAmount(chargeAmountWrap);
			if(chargeAmount == null)
				setChargeAmount(chargeAmountWrap.o);
		}
		chargeAmountWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Double staticSolrChargeAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmount(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrChargeAmount(siteRequest_, DesignEmailPage.staticSolrChargeAmount(siteRequest_, DesignEmailPage.staticSetChargeAmount(siteRequest_, o)));
	}

	public Double solrChargeAmount() {
		return DesignEmailPage.staticSolrChargeAmount(siteRequest_, chargeAmount);
	}

	public String strChargeAmount() {
		return chargeAmount == null ? "" : chargeAmount.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonChargeAmount() {
		return chargeAmount == null ? "" : chargeAmount.toString();
	}

	public String nomAffichageChargeAmount() {
		return null;
	}

	public String htmTooltipChargeAmount() {
		return null;
	}

	public String htmChargeAmount() {
		return chargeAmount == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmount());
	}

	////////////////////////
	// chargeAmountFuture //
	////////////////////////

	/**	 The entity chargeAmountFuture
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmountFuture;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountFutureWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmountFuture").o(chargeAmountFuture);

	/**	<br/> The entity chargeAmountFuture
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmountFuture">Find the entity chargeAmountFuture in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmountFuture(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmountFuture() {
		return chargeAmountFuture;
	}

	public void setChargeAmountFuture(BigDecimal chargeAmountFuture) {
		this.chargeAmountFuture = chargeAmountFuture;
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	public void setChargeAmountFuture(String o) {
		this.chargeAmountFuture = DesignEmailPage.staticSetChargeAmountFuture(siteRequest_, o);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmountFuture(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmountFuture(Double o) {
			this.chargeAmountFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	public void setChargeAmountFuture(Integer o) {
			this.chargeAmountFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	protected DesignEmailPage chargeAmountFutureInit() {
		if(!chargeAmountFutureWrap.alreadyInitialized) {
			_chargeAmountFuture(chargeAmountFutureWrap);
			if(chargeAmountFuture == null)
				setChargeAmountFuture(chargeAmountFutureWrap.o);
		}
		chargeAmountFutureWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Double staticSolrChargeAmountFuture(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmountFuture(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmountFuture(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrChargeAmountFuture(siteRequest_, DesignEmailPage.staticSolrChargeAmountFuture(siteRequest_, DesignEmailPage.staticSetChargeAmountFuture(siteRequest_, o)));
	}

	public Double solrChargeAmountFuture() {
		return DesignEmailPage.staticSolrChargeAmountFuture(siteRequest_, chargeAmountFuture);
	}

	public String strChargeAmountFuture() {
		return chargeAmountFuture == null ? "" : chargeAmountFuture.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonChargeAmountFuture() {
		return chargeAmountFuture == null ? "" : chargeAmountFuture.toString();
	}

	public String nomAffichageChargeAmountFuture() {
		return null;
	}

	public String htmTooltipChargeAmountFuture() {
		return null;
	}

	public String htmChargeAmountFuture() {
		return chargeAmountFuture == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmountFuture());
	}

	/////////////////////
	// chargeAmountDue //
	/////////////////////

	/**	 The entity chargeAmountDue
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmountDue;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountDueWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmountDue").o(chargeAmountDue);

	/**	<br/> The entity chargeAmountDue
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmountDue">Find the entity chargeAmountDue in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmountDue(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmountDue() {
		return chargeAmountDue;
	}

	public void setChargeAmountDue(BigDecimal chargeAmountDue) {
		this.chargeAmountDue = chargeAmountDue;
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	public void setChargeAmountDue(String o) {
		this.chargeAmountDue = DesignEmailPage.staticSetChargeAmountDue(siteRequest_, o);
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmountDue(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmountDue(Double o) {
			this.chargeAmountDue = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	public void setChargeAmountDue(Integer o) {
			this.chargeAmountDue = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	protected DesignEmailPage chargeAmountDueInit() {
		if(!chargeAmountDueWrap.alreadyInitialized) {
			_chargeAmountDue(chargeAmountDueWrap);
			if(chargeAmountDue == null)
				setChargeAmountDue(chargeAmountDueWrap.o);
		}
		chargeAmountDueWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Double staticSolrChargeAmountDue(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmountDue(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmountDue(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrChargeAmountDue(siteRequest_, DesignEmailPage.staticSolrChargeAmountDue(siteRequest_, DesignEmailPage.staticSetChargeAmountDue(siteRequest_, o)));
	}

	public Double solrChargeAmountDue() {
		return DesignEmailPage.staticSolrChargeAmountDue(siteRequest_, chargeAmountDue);
	}

	public String strChargeAmountDue() {
		return chargeAmountDue == null ? "" : chargeAmountDue.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonChargeAmountDue() {
		return chargeAmountDue == null ? "" : chargeAmountDue.toString();
	}

	public String nomAffichageChargeAmountDue() {
		return null;
	}

	public String htmTooltipChargeAmountDue() {
		return null;
	}

	public String htmChargeAmountDue() {
		return chargeAmountDue == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmountDue());
	}

	////////////////
	// chargesNow //
	////////////////

	/**	 The entity chargesNow
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargesNow;
	@JsonIgnore
	public Wrap<BigDecimal> chargesNowWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargesNow").o(chargesNow);

	/**	<br/> The entity chargesNow
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargesNow">Find the entity chargesNow in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargesNow(Wrap<BigDecimal> c);

	public BigDecimal getChargesNow() {
		return chargesNow;
	}

	public void setChargesNow(BigDecimal chargesNow) {
		this.chargesNow = chargesNow;
		this.chargesNowWrap.alreadyInitialized = true;
	}
	public void setChargesNow(String o) {
		this.chargesNow = DesignEmailPage.staticSetChargesNow(siteRequest_, o);
		this.chargesNowWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargesNow(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargesNow(Double o) {
			this.chargesNow = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargesNowWrap.alreadyInitialized = true;
	}
	public void setChargesNow(Integer o) {
			this.chargesNow = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargesNowWrap.alreadyInitialized = true;
	}
	protected DesignEmailPage chargesNowInit() {
		if(!chargesNowWrap.alreadyInitialized) {
			_chargesNow(chargesNowWrap);
			if(chargesNow == null)
				setChargesNow(chargesNowWrap.o);
		}
		chargesNowWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Double staticSolrChargesNow(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargesNow(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargesNow(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrChargesNow(siteRequest_, DesignEmailPage.staticSolrChargesNow(siteRequest_, DesignEmailPage.staticSetChargesNow(siteRequest_, o)));
	}

	public Double solrChargesNow() {
		return DesignEmailPage.staticSolrChargesNow(siteRequest_, chargesNow);
	}

	public String strChargesNow() {
		return chargesNow == null ? "" : chargesNow.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonChargesNow() {
		return chargesNow == null ? "" : chargesNow.toString();
	}

	public String nomAffichageChargesNow() {
		return null;
	}

	public String htmTooltipChargesNow() {
		return null;
	}

	public String htmChargesNow() {
		return chargesNow == null ? "" : StringEscapeUtils.escapeHtml4(strChargesNow());
	}

	/////////////////////
	// paymentsCurrent //
	/////////////////////

	/**	 The entity paymentsCurrent
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentsCurrent;
	@JsonIgnore
	public Wrap<Boolean> paymentsCurrentWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentsCurrent").o(paymentsCurrent);

	/**	<br/> The entity paymentsCurrent
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsCurrent">Find the entity paymentsCurrent in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentsCurrent(Wrap<Boolean> c);

	public Boolean getPaymentsCurrent() {
		return paymentsCurrent;
	}

	public void setPaymentsCurrent(Boolean paymentsCurrent) {
		this.paymentsCurrent = paymentsCurrent;
		this.paymentsCurrentWrap.alreadyInitialized = true;
	}
	public void setPaymentsCurrent(String o) {
		this.paymentsCurrent = DesignEmailPage.staticSetPaymentsCurrent(siteRequest_, o);
		this.paymentsCurrentWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentsCurrent(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignEmailPage paymentsCurrentInit() {
		if(!paymentsCurrentWrap.alreadyInitialized) {
			_paymentsCurrent(paymentsCurrentWrap);
			if(paymentsCurrent == null)
				setPaymentsCurrent(paymentsCurrentWrap.o);
		}
		paymentsCurrentWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Boolean staticSolrPaymentsCurrent(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentsCurrent(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsCurrent(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrPaymentsCurrent(siteRequest_, DesignEmailPage.staticSolrPaymentsCurrent(siteRequest_, DesignEmailPage.staticSetPaymentsCurrent(siteRequest_, o)));
	}

	public Boolean solrPaymentsCurrent() {
		return DesignEmailPage.staticSolrPaymentsCurrent(siteRequest_, paymentsCurrent);
	}

	public String strPaymentsCurrent() {
		return paymentsCurrent == null ? "" : paymentsCurrent.toString();
	}

	public String jsonPaymentsCurrent() {
		return paymentsCurrent == null ? "" : paymentsCurrent.toString();
	}

	public String nomAffichagePaymentsCurrent() {
		return null;
	}

	public String htmTooltipPaymentsCurrent() {
		return null;
	}

	public String htmPaymentsCurrent() {
		return paymentsCurrent == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentsCurrent());
	}

	//////////////////
	// paymentsLate //
	//////////////////

	/**	 The entity paymentsLate
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentsLate;
	@JsonIgnore
	public Wrap<Boolean> paymentsLateWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentsLate").o(paymentsLate);

	/**	<br/> The entity paymentsLate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsLate">Find the entity paymentsLate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentsLate(Wrap<Boolean> c);

	public Boolean getPaymentsLate() {
		return paymentsLate;
	}

	public void setPaymentsLate(Boolean paymentsLate) {
		this.paymentsLate = paymentsLate;
		this.paymentsLateWrap.alreadyInitialized = true;
	}
	public void setPaymentsLate(String o) {
		this.paymentsLate = DesignEmailPage.staticSetPaymentsLate(siteRequest_, o);
		this.paymentsLateWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentsLate(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignEmailPage paymentsLateInit() {
		if(!paymentsLateWrap.alreadyInitialized) {
			_paymentsLate(paymentsLateWrap);
			if(paymentsLate == null)
				setPaymentsLate(paymentsLateWrap.o);
		}
		paymentsLateWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Boolean staticSolrPaymentsLate(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentsLate(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsLate(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrPaymentsLate(siteRequest_, DesignEmailPage.staticSolrPaymentsLate(siteRequest_, DesignEmailPage.staticSetPaymentsLate(siteRequest_, o)));
	}

	public Boolean solrPaymentsLate() {
		return DesignEmailPage.staticSolrPaymentsLate(siteRequest_, paymentsLate);
	}

	public String strPaymentsLate() {
		return paymentsLate == null ? "" : paymentsLate.toString();
	}

	public String jsonPaymentsLate() {
		return paymentsLate == null ? "" : paymentsLate.toString();
	}

	public String nomAffichagePaymentsLate() {
		return null;
	}

	public String htmTooltipPaymentsLate() {
		return null;
	}

	public String htmPaymentsLate() {
		return paymentsLate == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentsLate());
	}

	////////////////////////
	// paymentsLateAmount //
	////////////////////////

	/**	 The entity paymentsLateAmount
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal paymentsLateAmount;
	@JsonIgnore
	public Wrap<BigDecimal> paymentsLateAmountWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("paymentsLateAmount").o(paymentsLateAmount);

	/**	<br/> The entity paymentsLateAmount
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsLateAmount">Find the entity paymentsLateAmount in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentsLateAmount(Wrap<BigDecimal> c);

	public BigDecimal getPaymentsLateAmount() {
		return paymentsLateAmount;
	}

	public void setPaymentsLateAmount(BigDecimal paymentsLateAmount) {
		this.paymentsLateAmount = paymentsLateAmount;
		this.paymentsLateAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentsLateAmount(String o) {
		this.paymentsLateAmount = DesignEmailPage.staticSetPaymentsLateAmount(siteRequest_, o);
		this.paymentsLateAmountWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetPaymentsLateAmount(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setPaymentsLateAmount(Double o) {
			this.paymentsLateAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentsLateAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentsLateAmount(Integer o) {
			this.paymentsLateAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentsLateAmountWrap.alreadyInitialized = true;
	}
	protected DesignEmailPage paymentsLateAmountInit() {
		if(!paymentsLateAmountWrap.alreadyInitialized) {
			_paymentsLateAmount(paymentsLateAmountWrap);
			if(paymentsLateAmount == null)
				setPaymentsLateAmount(paymentsLateAmountWrap.o);
		}
		paymentsLateAmountWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Double staticSolrPaymentsLateAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaymentsLateAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsLateAmount(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrPaymentsLateAmount(siteRequest_, DesignEmailPage.staticSolrPaymentsLateAmount(siteRequest_, DesignEmailPage.staticSetPaymentsLateAmount(siteRequest_, o)));
	}

	public Double solrPaymentsLateAmount() {
		return DesignEmailPage.staticSolrPaymentsLateAmount(siteRequest_, paymentsLateAmount);
	}

	public String strPaymentsLateAmount() {
		return paymentsLateAmount == null ? "" : paymentsLateAmount.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonPaymentsLateAmount() {
		return paymentsLateAmount == null ? "" : paymentsLateAmount.toString();
	}

	public String nomAffichagePaymentsLateAmount() {
		return null;
	}

	public String htmTooltipPaymentsLateAmount() {
		return null;
	}

	public String htmPaymentsLateAmount() {
		return paymentsLateAmount == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentsLateAmount());
	}

	///////////////////
	// paymentsAhead //
	///////////////////

	/**	 The entity paymentsAhead
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentsAhead;
	@JsonIgnore
	public Wrap<Boolean> paymentsAheadWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentsAhead").o(paymentsAhead);

	/**	<br/> The entity paymentsAhead
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsAhead">Find the entity paymentsAhead in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentsAhead(Wrap<Boolean> c);

	public Boolean getPaymentsAhead() {
		return paymentsAhead;
	}

	public void setPaymentsAhead(Boolean paymentsAhead) {
		this.paymentsAhead = paymentsAhead;
		this.paymentsAheadWrap.alreadyInitialized = true;
	}
	public void setPaymentsAhead(String o) {
		this.paymentsAhead = DesignEmailPage.staticSetPaymentsAhead(siteRequest_, o);
		this.paymentsAheadWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentsAhead(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignEmailPage paymentsAheadInit() {
		if(!paymentsAheadWrap.alreadyInitialized) {
			_paymentsAhead(paymentsAheadWrap);
			if(paymentsAhead == null)
				setPaymentsAhead(paymentsAheadWrap.o);
		}
		paymentsAheadWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static Boolean staticSolrPaymentsAhead(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentsAhead(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsAhead(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrPaymentsAhead(siteRequest_, DesignEmailPage.staticSolrPaymentsAhead(siteRequest_, DesignEmailPage.staticSetPaymentsAhead(siteRequest_, o)));
	}

	public Boolean solrPaymentsAhead() {
		return DesignEmailPage.staticSolrPaymentsAhead(siteRequest_, paymentsAhead);
	}

	public String strPaymentsAhead() {
		return paymentsAhead == null ? "" : paymentsAhead.toString();
	}

	public String jsonPaymentsAhead() {
		return paymentsAhead == null ? "" : paymentsAhead.toString();
	}

	public String nomAffichagePaymentsAhead() {
		return null;
	}

	public String htmTooltipPaymentsAhead() {
		return null;
	}

	public String htmPaymentsAhead() {
		return paymentsAhead == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentsAhead());
	}

	///////////////////
	// receiptSearch //
	///////////////////

	/**	 The entity receiptSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolReceipt>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolReceipt> receiptSearch = new SearchList<SchoolReceipt>();
	@JsonIgnore
	public Wrap<SearchList<SchoolReceipt>> receiptSearchWrap = new Wrap<SearchList<SchoolReceipt>>().p(this).c(SearchList.class).var("receiptSearch").o(receiptSearch);

	/**	<br/> The entity receiptSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolReceipt>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:receiptSearch">Find the entity receiptSearch in Solr</a>
	 * <br/>
	 * @param receiptSearch is the entity already constructed. 
	 **/
	protected abstract void _receiptSearch(SearchList<SchoolReceipt> l);

	public SearchList<SchoolReceipt> getReceiptSearch() {
		return receiptSearch;
	}

	public void setReceiptSearch(SearchList<SchoolReceipt> receiptSearch) {
		this.receiptSearch = receiptSearch;
		this.receiptSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolReceipt> staticSetReceiptSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage receiptSearchInit() {
		if(!receiptSearchWrap.alreadyInitialized) {
			_receiptSearch(receiptSearch);
		}
		receiptSearch.initDeepForClass(siteRequest_);
		receiptSearchWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////////
	// receipts_ //
	///////////////

	/**	 The entity receipts_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolReceipt> receipts_;
	@JsonIgnore
	public Wrap<List<SchoolReceipt>> receipts_Wrap = new Wrap<List<SchoolReceipt>>().p(this).c(List.class).var("receipts_").o(receipts_);

	/**	<br/> The entity receipts_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:receipts_">Find the entity receipts_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _receipts_(Wrap<List<SchoolReceipt>> c);

	public List<SchoolReceipt> getReceipts_() {
		return receipts_;
	}

	public void setReceipts_(List<SchoolReceipt> receipts_) {
		this.receipts_ = receipts_;
		this.receipts_Wrap.alreadyInitialized = true;
	}
	public static SchoolReceipt staticSetReceipts_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignEmailPage addReceipts_(SchoolReceipt...objets) {
		for(SchoolReceipt o : objets) {
			addReceipts_(o);
		}
		return (DesignEmailPage)this;
	}
	public DesignEmailPage addReceipts_(SchoolReceipt o) {
		if(o != null && !receipts_.contains(o))
			this.receipts_.add(o);
		return (DesignEmailPage)this;
	}
	protected DesignEmailPage receipts_Init() {
		if(!receipts_Wrap.alreadyInitialized) {
			_receipts_(receipts_Wrap);
			if(receipts_ == null)
				setReceipts_(receipts_Wrap.o);
		}
		receipts_Wrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	//////////////
	// receipt_ //
	//////////////

	/**	 The entity receipt_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolReceipt receipt_;
	@JsonIgnore
	public Wrap<SchoolReceipt> receipt_Wrap = new Wrap<SchoolReceipt>().p(this).c(SchoolReceipt.class).var("receipt_").o(receipt_);

	/**	<br/> The entity receipt_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:receipt_">Find the entity receipt_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _receipt_(Wrap<SchoolReceipt> c);

	public SchoolReceipt getReceipt_() {
		return receipt_;
	}

	public void setReceipt_(SchoolReceipt receipt_) {
		this.receipt_ = receipt_;
		this.receipt_Wrap.alreadyInitialized = true;
	}
	public static SchoolReceipt staticSetReceipt_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailPage receipt_Init() {
		if(!receipt_Wrap.alreadyInitialized) {
			_receipt_(receipt_Wrap);
			if(receipt_ == null)
				setReceipt_(receipt_Wrap.o);
		}
		receipt_Wrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	///////////////
	// emailFrom //
	///////////////

	/**	 The entity emailFrom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailFrom;
	@JsonIgnore
	public Wrap<String> emailFromWrap = new Wrap<String>().p(this).c(String.class).var("emailFrom").o(emailFrom);

	/**	<br/> The entity emailFrom
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailFrom">Find the entity emailFrom in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailFrom(Wrap<String> c);

	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String o) {
		this.emailFrom = DesignEmailPage.staticSetEmailFrom(siteRequest_, o);
		this.emailFromWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailFrom(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage emailFromInit() {
		if(!emailFromWrap.alreadyInitialized) {
			_emailFrom(emailFromWrap);
			if(emailFrom == null)
				setEmailFrom(emailFromWrap.o);
		}
		emailFromWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrEmailFrom(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailFrom(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailFrom(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrEmailFrom(siteRequest_, DesignEmailPage.staticSolrEmailFrom(siteRequest_, DesignEmailPage.staticSetEmailFrom(siteRequest_, o)));
	}

	public String solrEmailFrom() {
		return DesignEmailPage.staticSolrEmailFrom(siteRequest_, emailFrom);
	}

	public String strEmailFrom() {
		return emailFrom == null ? "" : emailFrom;
	}

	public String jsonEmailFrom() {
		return emailFrom == null ? "" : emailFrom;
	}

	public String nomAffichageEmailFrom() {
		return null;
	}

	public String htmTooltipEmailFrom() {
		return null;
	}

	public String htmEmailFrom() {
		return emailFrom == null ? "" : StringEscapeUtils.escapeHtml4(strEmailFrom());
	}

	///////////////////
	// emailToSchool //
	///////////////////

	/**	 The entity emailToSchool
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailToSchool;
	@JsonIgnore
	public Wrap<String> emailToSchoolWrap = new Wrap<String>().p(this).c(String.class).var("emailToSchool").o(emailToSchool);

	/**	<br/> The entity emailToSchool
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailToSchool">Find the entity emailToSchool in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailToSchool(Wrap<String> c);

	public String getEmailToSchool() {
		return emailToSchool;
	}
	public void setEmailToSchool(String o) {
		this.emailToSchool = DesignEmailPage.staticSetEmailToSchool(siteRequest_, o);
		this.emailToSchoolWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailToSchool(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage emailToSchoolInit() {
		if(!emailToSchoolWrap.alreadyInitialized) {
			_emailToSchool(emailToSchoolWrap);
			if(emailToSchool == null)
				setEmailToSchool(emailToSchoolWrap.o);
		}
		emailToSchoolWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrEmailToSchool(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailToSchool(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailToSchool(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrEmailToSchool(siteRequest_, DesignEmailPage.staticSolrEmailToSchool(siteRequest_, DesignEmailPage.staticSetEmailToSchool(siteRequest_, o)));
	}

	public String solrEmailToSchool() {
		return DesignEmailPage.staticSolrEmailToSchool(siteRequest_, emailToSchool);
	}

	public String strEmailToSchool() {
		return emailToSchool == null ? "" : emailToSchool;
	}

	public String jsonEmailToSchool() {
		return emailToSchool == null ? "" : emailToSchool;
	}

	public String nomAffichageEmailToSchool() {
		return null;
	}

	public String htmTooltipEmailToSchool() {
		return null;
	}

	public String htmEmailToSchool() {
		return emailToSchool == null ? "" : StringEscapeUtils.escapeHtml4(strEmailToSchool());
	}

	////////////////////
	// emailToAddress //
	////////////////////

	/**	 The entity emailToAddress
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailToAddress;
	@JsonIgnore
	public Wrap<String> emailToAddressWrap = new Wrap<String>().p(this).c(String.class).var("emailToAddress").o(emailToAddress);

	/**	<br/> The entity emailToAddress
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailToAddress">Find the entity emailToAddress in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailToAddress(Wrap<String> c);

	public String getEmailToAddress() {
		return emailToAddress;
	}
	public void setEmailToAddress(String o) {
		this.emailToAddress = DesignEmailPage.staticSetEmailToAddress(siteRequest_, o);
		this.emailToAddressWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailToAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage emailToAddressInit() {
		if(!emailToAddressWrap.alreadyInitialized) {
			_emailToAddress(emailToAddressWrap);
			if(emailToAddress == null)
				setEmailToAddress(emailToAddressWrap.o);
		}
		emailToAddressWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrEmailToAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailToAddress(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailToAddress(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrEmailToAddress(siteRequest_, DesignEmailPage.staticSolrEmailToAddress(siteRequest_, DesignEmailPage.staticSetEmailToAddress(siteRequest_, o)));
	}

	public String solrEmailToAddress() {
		return DesignEmailPage.staticSolrEmailToAddress(siteRequest_, emailToAddress);
	}

	public String strEmailToAddress() {
		return emailToAddress == null ? "" : emailToAddress;
	}

	public String jsonEmailToAddress() {
		return emailToAddress == null ? "" : emailToAddress;
	}

	public String nomAffichageEmailToAddress() {
		return null;
	}

	public String htmTooltipEmailToAddress() {
		return null;
	}

	public String htmEmailToAddress() {
		return emailToAddress == null ? "" : StringEscapeUtils.escapeHtml4(strEmailToAddress());
	}

	/////////////////
	// emailToName //
	/////////////////

	/**	 The entity emailToName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailToName;
	@JsonIgnore
	public Wrap<String> emailToNameWrap = new Wrap<String>().p(this).c(String.class).var("emailToName").o(emailToName);

	/**	<br/> The entity emailToName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailToName">Find the entity emailToName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailToName(Wrap<String> c);

	public String getEmailToName() {
		return emailToName;
	}
	public void setEmailToName(String o) {
		this.emailToName = DesignEmailPage.staticSetEmailToName(siteRequest_, o);
		this.emailToNameWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailToName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage emailToNameInit() {
		if(!emailToNameWrap.alreadyInitialized) {
			_emailToName(emailToNameWrap);
			if(emailToName == null)
				setEmailToName(emailToNameWrap.o);
		}
		emailToNameWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrEmailToName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailToName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailToName(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrEmailToName(siteRequest_, DesignEmailPage.staticSolrEmailToName(siteRequest_, DesignEmailPage.staticSetEmailToName(siteRequest_, o)));
	}

	public String solrEmailToName() {
		return DesignEmailPage.staticSolrEmailToName(siteRequest_, emailToName);
	}

	public String strEmailToName() {
		return emailToName == null ? "" : emailToName;
	}

	public String jsonEmailToName() {
		return emailToName == null ? "" : emailToName;
	}

	public String nomAffichageEmailToName() {
		return null;
	}

	public String htmTooltipEmailToName() {
		return null;
	}

	public String htmEmailToName() {
		return emailToName == null ? "" : StringEscapeUtils.escapeHtml4(strEmailToName());
	}

	//////////////////
	// emailMessage //
	//////////////////

	/**	 The entity emailMessage
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailMessage;
	@JsonIgnore
	public Wrap<String> emailMessageWrap = new Wrap<String>().p(this).c(String.class).var("emailMessage").o(emailMessage);

	/**	<br/> The entity emailMessage
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailMessage">Find the entity emailMessage in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailMessage(Wrap<String> c);

	public String getEmailMessage() {
		return emailMessage;
	}
	public void setEmailMessage(String o) {
		this.emailMessage = DesignEmailPage.staticSetEmailMessage(siteRequest_, o);
		this.emailMessageWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailMessage(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage emailMessageInit() {
		if(!emailMessageWrap.alreadyInitialized) {
			_emailMessage(emailMessageWrap);
			if(emailMessage == null)
				setEmailMessage(emailMessageWrap.o);
		}
		emailMessageWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrEmailMessage(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailMessage(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailMessage(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrEmailMessage(siteRequest_, DesignEmailPage.staticSolrEmailMessage(siteRequest_, DesignEmailPage.staticSetEmailMessage(siteRequest_, o)));
	}

	public String solrEmailMessage() {
		return DesignEmailPage.staticSolrEmailMessage(siteRequest_, emailMessage);
	}

	public String strEmailMessage() {
		return emailMessage == null ? "" : emailMessage;
	}

	public String jsonEmailMessage() {
		return emailMessage == null ? "" : emailMessage;
	}

	public String nomAffichageEmailMessage() {
		return null;
	}

	public String htmTooltipEmailMessage() {
		return null;
	}

	public String htmEmailMessage() {
		return emailMessage == null ? "" : StringEscapeUtils.escapeHtml4(strEmailMessage());
	}

	//////////////////
	// emailSubject //
	//////////////////

	/**	 The entity emailSubject
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailSubject;
	@JsonIgnore
	public Wrap<String> emailSubjectWrap = new Wrap<String>().p(this).c(String.class).var("emailSubject").o(emailSubject);

	/**	<br/> The entity emailSubject
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailSubject">Find the entity emailSubject in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailSubject(Wrap<String> c);

	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String o) {
		this.emailSubject = DesignEmailPage.staticSetEmailSubject(siteRequest_, o);
		this.emailSubjectWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailSubject(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignEmailPage emailSubjectInit() {
		if(!emailSubjectWrap.alreadyInitialized) {
			_emailSubject(emailSubjectWrap);
			if(emailSubject == null)
				setEmailSubject(emailSubjectWrap.o);
		}
		emailSubjectWrap.alreadyInitialized(true);
		return (DesignEmailPage)this;
	}

	public static String staticSolrEmailSubject(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailSubject(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailSubject(SiteRequestEnUS siteRequest_, String o) {
		return DesignEmailPage.staticSolrStrEmailSubject(siteRequest_, DesignEmailPage.staticSolrEmailSubject(siteRequest_, DesignEmailPage.staticSetEmailSubject(siteRequest_, o)));
	}

	public String solrEmailSubject() {
		return DesignEmailPage.staticSolrEmailSubject(siteRequest_, emailSubject);
	}

	public String strEmailSubject() {
		return emailSubject == null ? "" : emailSubject;
	}

	public String jsonEmailSubject() {
		return emailSubject == null ? "" : emailSubject;
	}

	public String nomAffichageEmailSubject() {
		return null;
	}

	public String htmTooltipEmailSubject() {
		return null;
	}

	public String htmEmailSubject() {
		return emailSubject == null ? "" : StringEscapeUtils.escapeHtml4(strEmailSubject());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedDesignEmailPage = false;

	public DesignEmailPage initDeepDesignEmailPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedDesignEmailPage) {
			alreadyInitializedDesignEmailPage = true;
			initDeepDesignEmailPage();
		}
		return (DesignEmailPage)this;
	}

	public void initDeepDesignEmailPage() {
		initDesignEmailPage();
		super.initDeepDesignEmailGenPage(siteRequest_);
	}

	public void initDesignEmailPage() {
		w1Init();
		wPageInit();
		pageDesignIdInit();
		pageHtmlPartSearchInit();
		pageHtmlPartListInit();
		wEmailInit();
		emailContentTypeInit();
		emailDesignIdInit();
		emailDesignSearchInit();
		emailDesignInit();
		emailHtmlPartSearchInit();
		emailHtmlPartListInit();
		wAttachmentInit();
		attachmentContentTypeInit();
		attachmentDesignIdInit();
		attachmentDesignSearchInit();
		attachmentDesignInit();
		attachmentHtmlPartSearchInit();
		attachmentHtmlPartListInit();
		enrollmentSearchInit();
		schoolEnrollmentInit();
		enrollmentsInit();
		enrollmentBlocksInit();
		enrollmentGroupsInit();
		enrollmentBlockInit();
		enrollmentGroupInit();
		enrollmentEnrollmentInit();
		yearSearchInit();
		year_Init();
		yearKeyInit();
		yearStartInit();
		yearEndInit();
		yearVarInit();
		schoolSearchInit();
		school_Init();
		schoolKeyInit();
		schoolNameInit();
		schoolCompleteNameInit();
		schoolLocationInit();
		schoolAddressInit();
		schoolPhoneNumberInit();
		schoolAdministratorNameInit();
		seasonStartDateInit();
		mom_Init();
		dad_Init();
		guardian_Init();
		blockSearchInit();
		blocksInit();
		seasonBlocksInit();
		seasonBlockInit();
		sessionBlockInit();
		ageBlockInit();
		blockBlockInit();
		paymentSearchInit();
		payments_Init();
		payment_Init();
		paymentBlocksInit();
		paymentGroupsInit();
		paymentBlockInit();
		paymentGroupInit();
		paymentPaymentInit();
		paymentFacetsInit();
		paymentLastStrInit();
		paymentAmountInit();
		chargeAmountInit();
		chargeAmountFutureInit();
		chargeAmountDueInit();
		chargesNowInit();
		paymentsCurrentInit();
		paymentsLateInit();
		paymentsLateAmountInit();
		paymentsAheadInit();
		receiptSearchInit();
		receipts_Init();
		receipt_Init();
		emailFromInit();
		emailToSchoolInit();
		emailToAddressInit();
		emailToNameInit();
		emailMessageInit();
		emailSubjectInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepDesignEmailPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestDesignEmailPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestDesignEmailGenPage(siteRequest_);
		if(w1 != null)
			w1.setSiteRequest_(siteRequest_);
		if(wPage != null)
			wPage.setSiteRequest_(siteRequest_);
		if(pageHtmlPartSearch != null)
			pageHtmlPartSearch.setSiteRequest_(siteRequest_);
		if(wEmail != null)
			wEmail.setSiteRequest_(siteRequest_);
		if(emailDesignSearch != null)
			emailDesignSearch.setSiteRequest_(siteRequest_);
		if(emailDesign != null)
			emailDesign.setSiteRequest_(siteRequest_);
		if(emailHtmlPartSearch != null)
			emailHtmlPartSearch.setSiteRequest_(siteRequest_);
		if(wAttachment != null)
			wAttachment.setSiteRequest_(siteRequest_);
		if(attachmentDesignSearch != null)
			attachmentDesignSearch.setSiteRequest_(siteRequest_);
		if(attachmentDesign != null)
			attachmentDesign.setSiteRequest_(siteRequest_);
		if(attachmentHtmlPartSearch != null)
			attachmentHtmlPartSearch.setSiteRequest_(siteRequest_);
		if(enrollmentSearch != null)
			enrollmentSearch.setSiteRequest_(siteRequest_);
		if(schoolEnrollment != null)
			schoolEnrollment.setSiteRequest_(siteRequest_);
		if(enrollmentBlock != null)
			enrollmentBlock.setSiteRequest_(siteRequest_);
		if(enrollmentGroup != null)
			enrollmentGroup.setSiteRequest_(siteRequest_);
		if(enrollmentEnrollment != null)
			enrollmentEnrollment.setSiteRequest_(siteRequest_);
		if(yearSearch != null)
			yearSearch.setSiteRequest_(siteRequest_);
		if(schoolSearch != null)
			schoolSearch.setSiteRequest_(siteRequest_);
		if(blockSearch != null)
			blockSearch.setSiteRequest_(siteRequest_);
		if(seasonBlock != null)
			seasonBlock.setSiteRequest_(siteRequest_);
		if(sessionBlock != null)
			sessionBlock.setSiteRequest_(siteRequest_);
		if(ageBlock != null)
			ageBlock.setSiteRequest_(siteRequest_);
		if(blockBlock != null)
			blockBlock.setSiteRequest_(siteRequest_);
		if(paymentSearch != null)
			paymentSearch.setSiteRequest_(siteRequest_);
		if(paymentBlock != null)
			paymentBlock.setSiteRequest_(siteRequest_);
		if(paymentGroup != null)
			paymentGroup.setSiteRequest_(siteRequest_);
		if(paymentPayment != null)
			paymentPayment.setSiteRequest_(siteRequest_);
		if(receiptSearch != null)
			receiptSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestDesignEmailPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainDesignEmailPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainDesignEmailPage(String var) {
		DesignEmailPage oDesignEmailPage = (DesignEmailPage)this;
		switch(var) {
			case "w1":
				return oDesignEmailPage.w1;
			case "wPage":
				return oDesignEmailPage.wPage;
			case "pageDesignId":
				return oDesignEmailPage.pageDesignId;
			case "pageHtmlPartSearch":
				return oDesignEmailPage.pageHtmlPartSearch;
			case "pageHtmlPartList":
				return oDesignEmailPage.pageHtmlPartList;
			case "wEmail":
				return oDesignEmailPage.wEmail;
			case "emailContentType":
				return oDesignEmailPage.emailContentType;
			case "emailDesignId":
				return oDesignEmailPage.emailDesignId;
			case "emailDesignSearch":
				return oDesignEmailPage.emailDesignSearch;
			case "emailDesign":
				return oDesignEmailPage.emailDesign;
			case "emailHtmlPartSearch":
				return oDesignEmailPage.emailHtmlPartSearch;
			case "emailHtmlPartList":
				return oDesignEmailPage.emailHtmlPartList;
			case "wAttachment":
				return oDesignEmailPage.wAttachment;
			case "attachmentContentType":
				return oDesignEmailPage.attachmentContentType;
			case "attachmentDesignId":
				return oDesignEmailPage.attachmentDesignId;
			case "attachmentDesignSearch":
				return oDesignEmailPage.attachmentDesignSearch;
			case "attachmentDesign":
				return oDesignEmailPage.attachmentDesign;
			case "attachmentHtmlPartSearch":
				return oDesignEmailPage.attachmentHtmlPartSearch;
			case "attachmentHtmlPartList":
				return oDesignEmailPage.attachmentHtmlPartList;
			case "enrollmentSearch":
				return oDesignEmailPage.enrollmentSearch;
			case "schoolEnrollment":
				return oDesignEmailPage.schoolEnrollment;
			case "enrollments":
				return oDesignEmailPage.enrollments;
			case "enrollmentBlocks":
				return oDesignEmailPage.enrollmentBlocks;
			case "enrollmentGroups":
				return oDesignEmailPage.enrollmentGroups;
			case "enrollmentBlock":
				return oDesignEmailPage.enrollmentBlock;
			case "enrollmentGroup":
				return oDesignEmailPage.enrollmentGroup;
			case "enrollmentEnrollment":
				return oDesignEmailPage.enrollmentEnrollment;
			case "yearSearch":
				return oDesignEmailPage.yearSearch;
			case "year_":
				return oDesignEmailPage.year_;
			case "yearKey":
				return oDesignEmailPage.yearKey;
			case "yearStart":
				return oDesignEmailPage.yearStart;
			case "yearEnd":
				return oDesignEmailPage.yearEnd;
			case "yearVar":
				return oDesignEmailPage.yearVar;
			case "schoolSearch":
				return oDesignEmailPage.schoolSearch;
			case "school_":
				return oDesignEmailPage.school_;
			case "schoolKey":
				return oDesignEmailPage.schoolKey;
			case "schoolName":
				return oDesignEmailPage.schoolName;
			case "schoolCompleteName":
				return oDesignEmailPage.schoolCompleteName;
			case "schoolLocation":
				return oDesignEmailPage.schoolLocation;
			case "schoolAddress":
				return oDesignEmailPage.schoolAddress;
			case "schoolPhoneNumber":
				return oDesignEmailPage.schoolPhoneNumber;
			case "schoolAdministratorName":
				return oDesignEmailPage.schoolAdministratorName;
			case "seasonStartDate":
				return oDesignEmailPage.seasonStartDate;
			case "mom_":
				return oDesignEmailPage.mom_;
			case "dad_":
				return oDesignEmailPage.dad_;
			case "guardian_":
				return oDesignEmailPage.guardian_;
			case "blockSearch":
				return oDesignEmailPage.blockSearch;
			case "blocks":
				return oDesignEmailPage.blocks;
			case "seasonBlocks":
				return oDesignEmailPage.seasonBlocks;
			case "seasonBlock":
				return oDesignEmailPage.seasonBlock;
			case "sessionBlock":
				return oDesignEmailPage.sessionBlock;
			case "ageBlock":
				return oDesignEmailPage.ageBlock;
			case "blockBlock":
				return oDesignEmailPage.blockBlock;
			case "paymentSearch":
				return oDesignEmailPage.paymentSearch;
			case "payments_":
				return oDesignEmailPage.payments_;
			case "payment_":
				return oDesignEmailPage.payment_;
			case "paymentBlocks":
				return oDesignEmailPage.paymentBlocks;
			case "paymentGroups":
				return oDesignEmailPage.paymentGroups;
			case "paymentBlock":
				return oDesignEmailPage.paymentBlock;
			case "paymentGroup":
				return oDesignEmailPage.paymentGroup;
			case "paymentPayment":
				return oDesignEmailPage.paymentPayment;
			case "paymentFacets":
				return oDesignEmailPage.paymentFacets;
			case "paymentLastStr":
				return oDesignEmailPage.paymentLastStr;
			case "paymentAmount":
				return oDesignEmailPage.paymentAmount;
			case "chargeAmount":
				return oDesignEmailPage.chargeAmount;
			case "chargeAmountFuture":
				return oDesignEmailPage.chargeAmountFuture;
			case "chargeAmountDue":
				return oDesignEmailPage.chargeAmountDue;
			case "chargesNow":
				return oDesignEmailPage.chargesNow;
			case "paymentsCurrent":
				return oDesignEmailPage.paymentsCurrent;
			case "paymentsLate":
				return oDesignEmailPage.paymentsLate;
			case "paymentsLateAmount":
				return oDesignEmailPage.paymentsLateAmount;
			case "paymentsAhead":
				return oDesignEmailPage.paymentsAhead;
			case "receiptSearch":
				return oDesignEmailPage.receiptSearch;
			case "receipts_":
				return oDesignEmailPage.receipts_;
			case "receipt_":
				return oDesignEmailPage.receipt_;
			case "emailFrom":
				return oDesignEmailPage.emailFrom;
			case "emailToSchool":
				return oDesignEmailPage.emailToSchool;
			case "emailToAddress":
				return oDesignEmailPage.emailToAddress;
			case "emailToName":
				return oDesignEmailPage.emailToName;
			case "emailMessage":
				return oDesignEmailPage.emailMessage;
			case "emailSubject":
				return oDesignEmailPage.emailSubject;
			default:
				return super.obtainDesignEmailGenPage(var);
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
				o = attributeDesignEmailPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeDesignEmailPage(String var, Object val) {
		DesignEmailPage oDesignEmailPage = (DesignEmailPage)this;
		switch(var) {
			default:
				return super.attributeDesignEmailGenPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetDesignEmailPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetDesignEmailPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "pageDesignId":
			return DesignEmailPage.staticSetPageDesignId(siteRequest_, o);
		case "emailContentType":
			return DesignEmailPage.staticSetEmailContentType(siteRequest_, o);
		case "emailDesignId":
			return DesignEmailPage.staticSetEmailDesignId(siteRequest_, o);
		case "attachmentContentType":
			return DesignEmailPage.staticSetAttachmentContentType(siteRequest_, o);
		case "attachmentDesignId":
			return DesignEmailPage.staticSetAttachmentDesignId(siteRequest_, o);
		case "yearKey":
			return DesignEmailPage.staticSetYearKey(siteRequest_, o);
		case "yearStart":
			return DesignEmailPage.staticSetYearStart(siteRequest_, o);
		case "yearEnd":
			return DesignEmailPage.staticSetYearEnd(siteRequest_, o);
		case "yearVar":
			return DesignEmailPage.staticSetYearVar(siteRequest_, o);
		case "schoolKey":
			return DesignEmailPage.staticSetSchoolKey(siteRequest_, o);
		case "schoolName":
			return DesignEmailPage.staticSetSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return DesignEmailPage.staticSetSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return DesignEmailPage.staticSetSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return DesignEmailPage.staticSetSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return DesignEmailPage.staticSetSchoolPhoneNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return DesignEmailPage.staticSetSchoolAdministratorName(siteRequest_, o);
		case "seasonStartDate":
			return DesignEmailPage.staticSetSeasonStartDate(siteRequest_, o);
		case "paymentLastStr":
			return DesignEmailPage.staticSetPaymentLastStr(siteRequest_, o);
		case "paymentAmount":
			return DesignEmailPage.staticSetPaymentAmount(siteRequest_, o);
		case "chargeAmount":
			return DesignEmailPage.staticSetChargeAmount(siteRequest_, o);
		case "chargeAmountFuture":
			return DesignEmailPage.staticSetChargeAmountFuture(siteRequest_, o);
		case "chargeAmountDue":
			return DesignEmailPage.staticSetChargeAmountDue(siteRequest_, o);
		case "chargesNow":
			return DesignEmailPage.staticSetChargesNow(siteRequest_, o);
		case "paymentsCurrent":
			return DesignEmailPage.staticSetPaymentsCurrent(siteRequest_, o);
		case "paymentsLate":
			return DesignEmailPage.staticSetPaymentsLate(siteRequest_, o);
		case "paymentsLateAmount":
			return DesignEmailPage.staticSetPaymentsLateAmount(siteRequest_, o);
		case "paymentsAhead":
			return DesignEmailPage.staticSetPaymentsAhead(siteRequest_, o);
		case "emailFrom":
			return DesignEmailPage.staticSetEmailFrom(siteRequest_, o);
		case "emailToSchool":
			return DesignEmailPage.staticSetEmailToSchool(siteRequest_, o);
		case "emailToAddress":
			return DesignEmailPage.staticSetEmailToAddress(siteRequest_, o);
		case "emailToName":
			return DesignEmailPage.staticSetEmailToName(siteRequest_, o);
		case "emailMessage":
			return DesignEmailPage.staticSetEmailMessage(siteRequest_, o);
		case "emailSubject":
			return DesignEmailPage.staticSetEmailSubject(siteRequest_, o);
			default:
				return DesignEmailGenPage.staticSetDesignEmailGenPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrDesignEmailPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrDesignEmailPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "pageDesignId":
			return DesignEmailPage.staticSolrPageDesignId(siteRequest_, (String)o);
		case "emailContentType":
			return DesignEmailPage.staticSolrEmailContentType(siteRequest_, (String)o);
		case "emailDesignId":
			return DesignEmailPage.staticSolrEmailDesignId(siteRequest_, (String)o);
		case "attachmentContentType":
			return DesignEmailPage.staticSolrAttachmentContentType(siteRequest_, (String)o);
		case "attachmentDesignId":
			return DesignEmailPage.staticSolrAttachmentDesignId(siteRequest_, (String)o);
		case "yearKey":
			return DesignEmailPage.staticSolrYearKey(siteRequest_, (Long)o);
		case "yearStart":
			return DesignEmailPage.staticSolrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return DesignEmailPage.staticSolrYearEnd(siteRequest_, (Integer)o);
		case "yearVar":
			return DesignEmailPage.staticSolrYearVar(siteRequest_, (String)o);
		case "schoolKey":
			return DesignEmailPage.staticSolrSchoolKey(siteRequest_, (Long)o);
		case "schoolName":
			return DesignEmailPage.staticSolrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return DesignEmailPage.staticSolrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return DesignEmailPage.staticSolrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return DesignEmailPage.staticSolrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return DesignEmailPage.staticSolrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolAdministratorName":
			return DesignEmailPage.staticSolrSchoolAdministratorName(siteRequest_, (String)o);
		case "seasonStartDate":
			return DesignEmailPage.staticSolrSeasonStartDate(siteRequest_, (LocalDate)o);
		case "paymentLastStr":
			return DesignEmailPage.staticSolrPaymentLastStr(siteRequest_, (String)o);
		case "paymentAmount":
			return DesignEmailPage.staticSolrPaymentAmount(siteRequest_, (BigDecimal)o);
		case "chargeAmount":
			return DesignEmailPage.staticSolrChargeAmount(siteRequest_, (BigDecimal)o);
		case "chargeAmountFuture":
			return DesignEmailPage.staticSolrChargeAmountFuture(siteRequest_, (BigDecimal)o);
		case "chargeAmountDue":
			return DesignEmailPage.staticSolrChargeAmountDue(siteRequest_, (BigDecimal)o);
		case "chargesNow":
			return DesignEmailPage.staticSolrChargesNow(siteRequest_, (BigDecimal)o);
		case "paymentsCurrent":
			return DesignEmailPage.staticSolrPaymentsCurrent(siteRequest_, (Boolean)o);
		case "paymentsLate":
			return DesignEmailPage.staticSolrPaymentsLate(siteRequest_, (Boolean)o);
		case "paymentsLateAmount":
			return DesignEmailPage.staticSolrPaymentsLateAmount(siteRequest_, (BigDecimal)o);
		case "paymentsAhead":
			return DesignEmailPage.staticSolrPaymentsAhead(siteRequest_, (Boolean)o);
		case "emailFrom":
			return DesignEmailPage.staticSolrEmailFrom(siteRequest_, (String)o);
		case "emailToSchool":
			return DesignEmailPage.staticSolrEmailToSchool(siteRequest_, (String)o);
		case "emailToAddress":
			return DesignEmailPage.staticSolrEmailToAddress(siteRequest_, (String)o);
		case "emailToName":
			return DesignEmailPage.staticSolrEmailToName(siteRequest_, (String)o);
		case "emailMessage":
			return DesignEmailPage.staticSolrEmailMessage(siteRequest_, (String)o);
		case "emailSubject":
			return DesignEmailPage.staticSolrEmailSubject(siteRequest_, (String)o);
			default:
				return DesignEmailGenPage.staticSolrDesignEmailGenPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrDesignEmailPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrDesignEmailPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "pageDesignId":
			return DesignEmailPage.staticSolrStrPageDesignId(siteRequest_, (String)o);
		case "emailContentType":
			return DesignEmailPage.staticSolrStrEmailContentType(siteRequest_, (String)o);
		case "emailDesignId":
			return DesignEmailPage.staticSolrStrEmailDesignId(siteRequest_, (String)o);
		case "attachmentContentType":
			return DesignEmailPage.staticSolrStrAttachmentContentType(siteRequest_, (String)o);
		case "attachmentDesignId":
			return DesignEmailPage.staticSolrStrAttachmentDesignId(siteRequest_, (String)o);
		case "yearKey":
			return DesignEmailPage.staticSolrStrYearKey(siteRequest_, (Long)o);
		case "yearStart":
			return DesignEmailPage.staticSolrStrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return DesignEmailPage.staticSolrStrYearEnd(siteRequest_, (Integer)o);
		case "yearVar":
			return DesignEmailPage.staticSolrStrYearVar(siteRequest_, (String)o);
		case "schoolKey":
			return DesignEmailPage.staticSolrStrSchoolKey(siteRequest_, (Long)o);
		case "schoolName":
			return DesignEmailPage.staticSolrStrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return DesignEmailPage.staticSolrStrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return DesignEmailPage.staticSolrStrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return DesignEmailPage.staticSolrStrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return DesignEmailPage.staticSolrStrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolAdministratorName":
			return DesignEmailPage.staticSolrStrSchoolAdministratorName(siteRequest_, (String)o);
		case "seasonStartDate":
			return DesignEmailPage.staticSolrStrSeasonStartDate(siteRequest_, (Date)o);
		case "paymentLastStr":
			return DesignEmailPage.staticSolrStrPaymentLastStr(siteRequest_, (String)o);
		case "paymentAmount":
			return DesignEmailPage.staticSolrStrPaymentAmount(siteRequest_, (Double)o);
		case "chargeAmount":
			return DesignEmailPage.staticSolrStrChargeAmount(siteRequest_, (Double)o);
		case "chargeAmountFuture":
			return DesignEmailPage.staticSolrStrChargeAmountFuture(siteRequest_, (Double)o);
		case "chargeAmountDue":
			return DesignEmailPage.staticSolrStrChargeAmountDue(siteRequest_, (Double)o);
		case "chargesNow":
			return DesignEmailPage.staticSolrStrChargesNow(siteRequest_, (Double)o);
		case "paymentsCurrent":
			return DesignEmailPage.staticSolrStrPaymentsCurrent(siteRequest_, (Boolean)o);
		case "paymentsLate":
			return DesignEmailPage.staticSolrStrPaymentsLate(siteRequest_, (Boolean)o);
		case "paymentsLateAmount":
			return DesignEmailPage.staticSolrStrPaymentsLateAmount(siteRequest_, (Double)o);
		case "paymentsAhead":
			return DesignEmailPage.staticSolrStrPaymentsAhead(siteRequest_, (Boolean)o);
		case "emailFrom":
			return DesignEmailPage.staticSolrStrEmailFrom(siteRequest_, (String)o);
		case "emailToSchool":
			return DesignEmailPage.staticSolrStrEmailToSchool(siteRequest_, (String)o);
		case "emailToAddress":
			return DesignEmailPage.staticSolrStrEmailToAddress(siteRequest_, (String)o);
		case "emailToName":
			return DesignEmailPage.staticSolrStrEmailToName(siteRequest_, (String)o);
		case "emailMessage":
			return DesignEmailPage.staticSolrStrEmailMessage(siteRequest_, (String)o);
		case "emailSubject":
			return DesignEmailPage.staticSolrStrEmailSubject(siteRequest_, (String)o);
			default:
				return DesignEmailGenPage.staticSolrStrDesignEmailGenPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqDesignEmailPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqDesignEmailPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "pageDesignId":
			return DesignEmailPage.staticSolrFqPageDesignId(siteRequest_, o);
		case "emailContentType":
			return DesignEmailPage.staticSolrFqEmailContentType(siteRequest_, o);
		case "emailDesignId":
			return DesignEmailPage.staticSolrFqEmailDesignId(siteRequest_, o);
		case "attachmentContentType":
			return DesignEmailPage.staticSolrFqAttachmentContentType(siteRequest_, o);
		case "attachmentDesignId":
			return DesignEmailPage.staticSolrFqAttachmentDesignId(siteRequest_, o);
		case "yearKey":
			return DesignEmailPage.staticSolrFqYearKey(siteRequest_, o);
		case "yearStart":
			return DesignEmailPage.staticSolrFqYearStart(siteRequest_, o);
		case "yearEnd":
			return DesignEmailPage.staticSolrFqYearEnd(siteRequest_, o);
		case "yearVar":
			return DesignEmailPage.staticSolrFqYearVar(siteRequest_, o);
		case "schoolKey":
			return DesignEmailPage.staticSolrFqSchoolKey(siteRequest_, o);
		case "schoolName":
			return DesignEmailPage.staticSolrFqSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return DesignEmailPage.staticSolrFqSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return DesignEmailPage.staticSolrFqSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return DesignEmailPage.staticSolrFqSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return DesignEmailPage.staticSolrFqSchoolPhoneNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return DesignEmailPage.staticSolrFqSchoolAdministratorName(siteRequest_, o);
		case "seasonStartDate":
			return DesignEmailPage.staticSolrFqSeasonStartDate(siteRequest_, o);
		case "paymentLastStr":
			return DesignEmailPage.staticSolrFqPaymentLastStr(siteRequest_, o);
		case "paymentAmount":
			return DesignEmailPage.staticSolrFqPaymentAmount(siteRequest_, o);
		case "chargeAmount":
			return DesignEmailPage.staticSolrFqChargeAmount(siteRequest_, o);
		case "chargeAmountFuture":
			return DesignEmailPage.staticSolrFqChargeAmountFuture(siteRequest_, o);
		case "chargeAmountDue":
			return DesignEmailPage.staticSolrFqChargeAmountDue(siteRequest_, o);
		case "chargesNow":
			return DesignEmailPage.staticSolrFqChargesNow(siteRequest_, o);
		case "paymentsCurrent":
			return DesignEmailPage.staticSolrFqPaymentsCurrent(siteRequest_, o);
		case "paymentsLate":
			return DesignEmailPage.staticSolrFqPaymentsLate(siteRequest_, o);
		case "paymentsLateAmount":
			return DesignEmailPage.staticSolrFqPaymentsLateAmount(siteRequest_, o);
		case "paymentsAhead":
			return DesignEmailPage.staticSolrFqPaymentsAhead(siteRequest_, o);
		case "emailFrom":
			return DesignEmailPage.staticSolrFqEmailFrom(siteRequest_, o);
		case "emailToSchool":
			return DesignEmailPage.staticSolrFqEmailToSchool(siteRequest_, o);
		case "emailToAddress":
			return DesignEmailPage.staticSolrFqEmailToAddress(siteRequest_, o);
		case "emailToName":
			return DesignEmailPage.staticSolrFqEmailToName(siteRequest_, o);
		case "emailMessage":
			return DesignEmailPage.staticSolrFqEmailMessage(siteRequest_, o);
		case "emailSubject":
			return DesignEmailPage.staticSolrFqEmailSubject(siteRequest_, o);
			default:
				return DesignEmailGenPage.staticSolrFqDesignEmailGenPage(entityVar,  siteRequest_, o);
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
					o = defineDesignEmailPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineDesignEmailPage(String var, String val) {
		switch(var) {
			default:
				return super.defineDesignEmailGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsDesignEmailPage();
		super.htmlScripts();
	}

	public void htmlScriptsDesignEmailPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptDesignEmailPage();
		super.htmlScript();
	}

	public void htmlScriptDesignEmailPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyDesignEmailPage();
		super.htmlBody();
	}

	public void htmlBodyDesignEmailPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlDesignEmailPage();
		super.html();
	}

	public void htmlDesignEmailPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaDesignEmailPage();
		super.htmlMeta();
	}

	public void htmlMetaDesignEmailPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesDesignEmailPage();
		super.htmlStyles();
	}

	public void htmlStylesDesignEmailPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleDesignEmailPage();
		super.htmlStyle();
	}

	public void htmlStyleDesignEmailPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestDesignEmailPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof DesignEmailPage) {
			DesignEmailPage original = (DesignEmailPage)o;
			super.apiRequestDesignEmailGenPage();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode());
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof DesignEmailPage))
			return false;
		DesignEmailPage that = (DesignEmailPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DesignEmailPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
