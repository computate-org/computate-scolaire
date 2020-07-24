package org.computate.scolaire.enUS.design;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import java.util.Date;
import org.computate.scolaire.enUS.design.DesignEmailGenPage;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import org.computate.scolaire.enUS.block.SchoolBlock;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
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
import java.lang.String;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.design.PageDesign;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.vertx.core.json.JsonArray;
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

	////////////////
	// pageDesign //
	////////////////

	/**	 The entity pageDesign
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected PageDesign pageDesign;
	@JsonIgnore
	public Wrap<PageDesign> pageDesignWrap = new Wrap<PageDesign>().p(this).c(PageDesign.class).var("pageDesign").o(pageDesign);

	/**	<br/> The entity pageDesign
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesign">Find the entity pageDesign in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageDesign(Wrap<PageDesign> c);

	public PageDesign getPageDesign() {
		return pageDesign;
	}

	public void setPageDesign(PageDesign pageDesign) {
		this.pageDesign = pageDesign;
		this.pageDesignWrap.alreadyInitialized = true;
	}
	protected DesignEmailPage pageDesignInit() {
		if(!pageDesignWrap.alreadyInitialized) {
			_pageDesign(pageDesignWrap);
			if(pageDesign == null)
				setPageDesign(pageDesignWrap.o);
		}
		if(pageDesign != null)
			pageDesign.initDeepForClass(siteRequest_);
		pageDesignWrap.alreadyInitialized(true);
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

	public void setPageDesignId(String pageDesignId) {
		this.pageDesignId = pageDesignId;
		this.pageDesignIdWrap.alreadyInitialized = true;
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

	public String solrPageDesignId() {
		return pageDesignId;
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

	public void setEmailContentType(String emailContentType) {
		this.emailContentType = emailContentType;
		this.emailContentTypeWrap.alreadyInitialized = true;
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

	public String solrEmailContentType() {
		return emailContentType;
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

	public void setEmailDesignId(String emailDesignId) {
		this.emailDesignId = emailDesignId;
		this.emailDesignIdWrap.alreadyInitialized = true;
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

	public String solrEmailDesignId() {
		return emailDesignId;
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

	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
		this.attachmentContentTypeWrap.alreadyInitialized = true;
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

	public String solrAttachmentContentType() {
		return attachmentContentType;
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

	public void setAttachmentDesignId(String attachmentDesignId) {
		this.attachmentDesignId = attachmentDesignId;
		this.attachmentDesignIdWrap.alreadyInitialized = true;
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

	public String solrAttachmentDesignId() {
		return attachmentDesignId;
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
	public DesignEmailPage setYearKey(String o) {
		if(NumberUtils.isParsable(o))
			this.yearKey = Long.parseLong(o);
		this.yearKeyWrap.alreadyInitialized = true;
		return (DesignEmailPage)this;
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

	public Long solrYearKey() {
		return yearKey;
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
	public DesignEmailPage setYearStart(String o) {
		if(NumberUtils.isParsable(o))
			this.yearStart = Integer.parseInt(o);
		this.yearStartWrap.alreadyInitialized = true;
		return (DesignEmailPage)this;
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

	public Integer solrYearStart() {
		return yearStart;
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
	public DesignEmailPage setYearEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.yearEnd = Integer.parseInt(o);
		this.yearEndWrap.alreadyInitialized = true;
		return (DesignEmailPage)this;
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

	public Integer solrYearEnd() {
		return yearEnd;
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
	public DesignEmailPage setSchoolKey(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolKey = Long.parseLong(o);
		this.schoolKeyWrap.alreadyInitialized = true;
		return (DesignEmailPage)this;
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

	public Long solrSchoolKey() {
		return schoolKey;
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

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
		this.schoolNameWrap.alreadyInitialized = true;
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

	public String solrSchoolName() {
		return schoolName;
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

	public void setSchoolCompleteName(String schoolCompleteName) {
		this.schoolCompleteName = schoolCompleteName;
		this.schoolCompleteNameWrap.alreadyInitialized = true;
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

	public String solrSchoolCompleteName() {
		return schoolCompleteName;
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

	public void setSchoolLocation(String schoolLocation) {
		this.schoolLocation = schoolLocation;
		this.schoolLocationWrap.alreadyInitialized = true;
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

	public String solrSchoolLocation() {
		return schoolLocation;
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

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
		this.schoolAddressWrap.alreadyInitialized = true;
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

	public String solrSchoolAddress() {
		return schoolAddress;
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

	public void setSchoolPhoneNumber(String schoolPhoneNumber) {
		this.schoolPhoneNumber = schoolPhoneNumber;
		this.schoolPhoneNumberWrap.alreadyInitialized = true;
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

	public String solrSchoolPhoneNumber() {
		return schoolPhoneNumber;
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

	public void setSchoolAdministratorName(String schoolAdministratorName) {
		this.schoolAdministratorName = schoolAdministratorName;
		this.schoolAdministratorNameWrap.alreadyInitialized = true;
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

	public String solrSchoolAdministratorName() {
		return schoolAdministratorName;
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
	public DesignEmailPage setSeasonStartDate(Instant o) {
		this.seasonStartDate = o == null ? null : LocalDate.from(o);
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (DesignEmailPage)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public DesignEmailPage setSeasonStartDate(String o) {
		this.seasonStartDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (DesignEmailPage)this;
	}
	public DesignEmailPage setSeasonStartDate(Date o) {
		this.seasonStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
		return (DesignEmailPage)this;
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

	public Date solrSeasonStartDate() {
		return seasonStartDate == null ? null : Date.from(seasonStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
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
	protected DesignEmailPage payment_Init() {
		if(!payment_Wrap.alreadyInitialized) {
			_payment_(payment_Wrap);
			if(payment_ == null)
				setPayment_(payment_Wrap.o);
		}
		payment_Wrap.alreadyInitialized(true);
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

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
		this.emailFromWrap.alreadyInitialized = true;
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

	public String solrEmailFrom() {
		return emailFrom;
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

	public void setEmailToSchool(String emailToSchool) {
		this.emailToSchool = emailToSchool;
		this.emailToSchoolWrap.alreadyInitialized = true;
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

	public String solrEmailToSchool() {
		return emailToSchool;
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

	public void setEmailToAddress(String emailToAddress) {
		this.emailToAddress = emailToAddress;
		this.emailToAddressWrap.alreadyInitialized = true;
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

	public String solrEmailToAddress() {
		return emailToAddress;
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

	public void setEmailToName(String emailToName) {
		this.emailToName = emailToName;
		this.emailToNameWrap.alreadyInitialized = true;
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

	public String solrEmailToName() {
		return emailToName;
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

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
		this.emailMessageWrap.alreadyInitialized = true;
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

	public String solrEmailMessage() {
		return emailMessage;
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

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
		this.emailSubjectWrap.alreadyInitialized = true;
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

	public String solrEmailSubject() {
		return emailSubject;
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
		pageDesignInit();
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
		if(pageDesign != null)
			pageDesign.setSiteRequest_(siteRequest_);
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
			case "pageDesign":
				return oDesignEmailPage.pageDesign;
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
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
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
