package org.computate.scolaire.enUS.design;

import java.util.Arrays;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PageDesignGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(PageDesign.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String PageDesign_UnNom = "a page design";
	public static final String PageDesign_Ce = "this ";
	public static final String PageDesign_CeNom = "this page design";
	public static final String PageDesign_Un = "a ";
	public static final String PageDesign_LeNom = "the page design";
	public static final String PageDesign_NomSingulier = "page design";
	public static final String PageDesign_NomPluriel = "page designs";
	public static final String PageDesign_NomActuel = "current page design";
	public static final String PageDesign_TousNom = "all the page designs";
	public static final String PageDesign_RechercherTousNomPar = "search page designs by ";
	public static final String PageDesign_LesNoms = "the page designs";
	public static final String PageDesign_AucunNomTrouve = "no page design found";
	public static final String PageDesign_NomVar = "page-design";
	public static final String PageDesign_DeNom = "of page design";
	public static final String PageDesign_UnNomAdjectif = "a page design";
	public static final String PageDesign_NomAdjectifSingulier = "page design";
	public static final String PageDesign_NomAdjectifPluriel = "page designs";
	public static final String PageDesign_Couleur = "khaki";
	public static final String PageDesign_IconeGroupe = "regular";
	public static final String PageDesign_IconeNom = "drafting-compass";

	///////////////////
	// pageDesignKey //
	///////////////////

	/**	L'entité « pageDesignKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long pageDesignKey;
	@JsonIgnore
	public Wrap<Long> pageDesignKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("pageDesignKey").o(pageDesignKey);

	/**	<br/>L'entité « pageDesignKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesignKey">Trouver l'entité pageDesignKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageDesignKey(Wrap<Long> c);

	public Long getPageDesignKey() {
		return pageDesignKey;
	}

	public void setPageDesignKey(Long pageDesignKey) {
		this.pageDesignKey = pageDesignKey;
		this.pageDesignKeyWrap.alreadyInitialized = true;
	}
	public PageDesign setPageDesignKey(String o) {
		if(NumberUtils.isParsable(o))
			this.pageDesignKey = Long.parseLong(o);
		this.pageDesignKeyWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign pageDesignKeyInit() {
		if(!pageDesignKeyWrap.alreadyInitialized) {
			_pageDesignKey(pageDesignKeyWrap);
			if(pageDesignKey == null)
				setPageDesignKey(pageDesignKeyWrap.o);
		}
		pageDesignKeyWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Long solrPageDesignKey() {
		return pageDesignKey;
	}

	public String strPageDesignKey() {
		return pageDesignKey == null ? "" : pageDesignKey.toString();
	}

	public String jsonPageDesignKey() {
		return pageDesignKey == null ? "" : pageDesignKey.toString();
	}

	public String nomAffichagePageDesignKey() {
		return "key";
	}

	public String htmTooltipPageDesignKey() {
		return null;
	}

	public String htmPageDesignKey() {
		return pageDesignKey == null ? "" : StringEscapeUtils.escapeHtml4(strPageDesignKey());
	}

	//////////////////
	// htmlPartKeys //
	//////////////////

	/**	L'entité « htmlPartKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> htmlPartKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> htmlPartKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("htmlPartKeys").o(htmlPartKeys);

	/**	<br/>L'entité « htmlPartKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPartKeys">Trouver l'entité htmlPartKeys dans Solr</a>
	 * <br/>
	 * @param htmlPartKeys est l'entité déjà construit. 
	 **/
	protected abstract void _htmlPartKeys(List<Long> o);

	public List<Long> getHtmlPartKeys() {
		return htmlPartKeys;
	}

	public void setHtmlPartKeys(List<Long> htmlPartKeys) {
		this.htmlPartKeys = htmlPartKeys;
		this.htmlPartKeysWrap.alreadyInitialized = true;
	}
	public PageDesign addHtmlPartKeys(Long...objets) {
		for(Long o : objets) {
			addHtmlPartKeys(o);
		}
		return (PageDesign)this;
	}
	public PageDesign addHtmlPartKeys(Long o) {
		if(o != null && !htmlPartKeys.contains(o))
			this.htmlPartKeys.add(o);
		return (PageDesign)this;
	}
	public PageDesign setHtmlPartKeys(JsonArray objets) {
		htmlPartKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addHtmlPartKeys(o);
		}
		return (PageDesign)this;
	}
	public PageDesign addHtmlPartKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addHtmlPartKeys(p);
		}
		return (PageDesign)this;
	}
	protected PageDesign htmlPartKeysInit() {
		if(!htmlPartKeysWrap.alreadyInitialized) {
			_htmlPartKeys(htmlPartKeys);
		}
		htmlPartKeysWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public List<Long> solrHtmlPartKeys() {
		return htmlPartKeys;
	}

	public String strHtmlPartKeys() {
		return htmlPartKeys == null ? "" : htmlPartKeys.toString();
	}

	public String jsonHtmlPartKeys() {
		return htmlPartKeys == null ? "" : htmlPartKeys.toString();
	}

	public String nomAffichageHtmlPartKeys() {
		return "parts";
	}

	public String htmTooltipHtmlPartKeys() {
		return null;
	}

	public String htmHtmlPartKeys() {
		return htmlPartKeys == null ? "" : StringEscapeUtils.escapeHtml4(strHtmlPartKeys());
	}

	public void inputHtmlPartKeys(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "parts")
					.a("class", "valueObjectSuggest suggestHtmlPartKeys w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setHtmlPartKeys")
					.a("id", classApiMethodMethod, "_htmlPartKeys")
					.a("autocomplete", "off")
					.a("oninput", "suggestPageDesignHtmlPartKeys($(this).val() ? searchHtmlPartFilters($('#suggest", classApiMethodMethod, "PageDesignHtmlPartKeys')) : [{'name':'fq','value':'pageDesignKey:", pk, "'}], $('#listPageDesignHtmlPartKeys_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

		}
	}

	public void htmHtmlPartKeys(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignHtmlPartKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/html-part?fq=pageDesignKey:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
								e("i").a("class", "far fa-sun ").f().g("i");
								sx("parts");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate HTML parts to this page design");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputHtmlPartKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listPageDesignHtmlPartKeys_", classApiMethodMethod).f();
								} g("ul");
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
											.a("onclick", "postHtmlPartVals({ pageDesignKey: \"", pk, "\" }, function() { patchPageDesignVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { addError($('#", classApiMethodMethod, "htmlPartKeys')); });")
											.f().sx("add an HTML part")
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

	////////////////////
	// htmlPartSearch //
	////////////////////

	/**	L'entité « htmlPartSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<HtmlPart>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<HtmlPart> htmlPartSearch = new SearchList<HtmlPart>();
	@JsonIgnore
	public Wrap<SearchList<HtmlPart>> htmlPartSearchWrap = new Wrap<SearchList<HtmlPart>>().p(this).c(SearchList.class).var("htmlPartSearch").o(htmlPartSearch);

	/**	<br/>L'entité « htmlPartSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<HtmlPart>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPartSearch">Trouver l'entité htmlPartSearch dans Solr</a>
	 * <br/>
	 * @param htmlPartSearch est l'entité déjà construit. 
	 **/
	protected abstract void _htmlPartSearch(SearchList<HtmlPart> l);

	public SearchList<HtmlPart> getHtmlPartSearch() {
		return htmlPartSearch;
	}

	public void setHtmlPartSearch(SearchList<HtmlPart> htmlPartSearch) {
		this.htmlPartSearch = htmlPartSearch;
		this.htmlPartSearchWrap.alreadyInitialized = true;
	}
	protected PageDesign htmlPartSearchInit() {
		if(!htmlPartSearchWrap.alreadyInitialized) {
			_htmlPartSearch(htmlPartSearch);
		}
		htmlPartSearch.initDeepForClass(siteRequest_);
		htmlPartSearchWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	//////////////////
	// htmlPartList //
	//////////////////

	/**	L'entité « htmlPartList »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<HtmlPart> htmlPartList;
	@JsonIgnore
	public Wrap<List<HtmlPart>> htmlPartListWrap = new Wrap<List<HtmlPart>>().p(this).c(List.class).var("htmlPartList").o(htmlPartList);

	/**	<br/>L'entité « htmlPartList »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPartList">Trouver l'entité htmlPartList dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlPartList(Wrap<List<HtmlPart>> c);

	public List<HtmlPart> getHtmlPartList() {
		return htmlPartList;
	}

	public void setHtmlPartList(List<HtmlPart> htmlPartList) {
		this.htmlPartList = htmlPartList;
		this.htmlPartListWrap.alreadyInitialized = true;
	}
	public PageDesign addHtmlPartList(HtmlPart...objets) {
		for(HtmlPart o : objets) {
			addHtmlPartList(o);
		}
		return (PageDesign)this;
	}
	public PageDesign addHtmlPartList(HtmlPart o) {
		if(o != null && !htmlPartList.contains(o))
			this.htmlPartList.add(o);
		return (PageDesign)this;
	}
	protected PageDesign htmlPartListInit() {
		if(!htmlPartListWrap.alreadyInitialized) {
			_htmlPartList(htmlPartListWrap);
			if(htmlPartList == null)
				setHtmlPartList(htmlPartListWrap.o);
		}
		htmlPartListWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	////////////////////////////
	// pageDesignCompleteName //
	////////////////////////////

	/**	L'entité « pageDesignCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected String pageDesignCompleteName;
	@JsonIgnore
	public Wrap<String> pageDesignCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("pageDesignCompleteName").o(pageDesignCompleteName);

	/**	<br/>L'entité « pageDesignCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesignCompleteName">Trouver l'entité pageDesignCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageDesignCompleteName(Wrap<String> c);

	public String getPageDesignCompleteName() {
		return pageDesignCompleteName;
	}

	public void setPageDesignCompleteName(String pageDesignCompleteName) {
		this.pageDesignCompleteName = pageDesignCompleteName;
		this.pageDesignCompleteNameWrap.alreadyInitialized = true;
	}
	protected PageDesign pageDesignCompleteNameInit() {
		if(!pageDesignCompleteNameWrap.alreadyInitialized) {
			_pageDesignCompleteName(pageDesignCompleteNameWrap);
			if(pageDesignCompleteName == null)
				setPageDesignCompleteName(pageDesignCompleteNameWrap.o);
		}
		pageDesignCompleteNameWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public String solrPageDesignCompleteName() {
		return pageDesignCompleteName;
	}

	public String strPageDesignCompleteName() {
		return pageDesignCompleteName == null ? "" : pageDesignCompleteName;
	}

	public String jsonPageDesignCompleteName() {
		return pageDesignCompleteName == null ? "" : pageDesignCompleteName;
	}

	public String nomAffichagePageDesignCompleteName() {
		return "name";
	}

	public String htmTooltipPageDesignCompleteName() {
		return null;
	}

	public String htmPageDesignCompleteName() {
		return pageDesignCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strPageDesignCompleteName());
	}

	public void inputPageDesignCompleteName(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "name")
				.a("id", classApiMethodMethod, "_pageDesignCompleteName");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPageDesignCompleteName inputPageDesign", pk, "PageDesignCompleteName w3-input w3-border ");
					a("name", "setPageDesignCompleteName");
				} else {
					a("class", "valuePageDesignCompleteName w3-input w3-border inputPageDesign", pk, "PageDesignCompleteName w3-input w3-border ");
					a("name", "pageDesignCompleteName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patchPageDesignVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPageDesignCompleteName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_pageDesignCompleteName')); }, function() { addError($('#", classApiMethodMethod, "_pageDesignCompleteName')); }); ");
				}
				a("value", strPageDesignCompleteName())
			.fg();

		}
	}

	public void htmPageDesignCompleteName(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignPageDesignCompleteName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_pageDesignCompleteName").a("class", "").f().sx("name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPageDesignCompleteName(classApiMethodMethod);
							} g("div");
							{
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-khaki ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_pageDesignCompleteName')); $('#", classApiMethodMethod, "_pageDesignCompleteName').val(null); patchPageDesignVal([{ name: 'fq', value: 'pk:' + $('#PageDesignForm :input[name=pk]').val() }], 'setPageDesignCompleteName', null, function() { addGlow($('#", classApiMethodMethod, "_pageDesignCompleteName')); }, function() { addError($('#", classApiMethodMethod, "_pageDesignCompleteName')); }); ")
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
	// designHidden //
	//////////////////

	/**	L'entité « designHidden »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Boolean designHidden;
	@JsonIgnore
	public Wrap<Boolean> designHiddenWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("designHidden").o(designHidden);

	/**	<br/>L'entité « designHidden »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:designHidden">Trouver l'entité designHidden dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _designHidden(Wrap<Boolean> c);

	public Boolean getDesignHidden() {
		return designHidden;
	}

	public void setDesignHidden(Boolean designHidden) {
		this.designHidden = designHidden;
		this.designHiddenWrap.alreadyInitialized = true;
	}
	public PageDesign setDesignHidden(String o) {
		this.designHidden = Boolean.parseBoolean(o);
		this.designHiddenWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign designHiddenInit() {
		if(!designHiddenWrap.alreadyInitialized) {
			_designHidden(designHiddenWrap);
			if(designHidden == null)
				setDesignHidden(designHiddenWrap.o);
		}
		designHiddenWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Boolean solrDesignHidden() {
		return designHidden;
	}

	public String strDesignHidden() {
		return designHidden == null ? "" : designHidden.toString();
	}

	public String jsonDesignHidden() {
		return designHidden == null ? "" : designHidden.toString();
	}

	public String nomAffichageDesignHidden() {
		return "hidden";
	}

	public String htmTooltipDesignHidden() {
		return null;
	}

	public String htmDesignHidden() {
		return designHidden == null ? "" : StringEscapeUtils.escapeHtml4(strDesignHidden());
	}

	public void inputDesignHidden(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_designHidden")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_designHidden");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setDesignHidden inputPageDesign", pk, "DesignHidden w3-input w3-border ");
				a("name", "setDesignHidden");
			} else {
				a("class", "valueDesignHidden inputPageDesign", pk, "DesignHidden w3-input w3-border ");
				a("name", "designHidden");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchPageDesignVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignHidden', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_designHidden')); }, function() { addError($('#", classApiMethodMethod, "_designHidden')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getDesignHidden() != null && getDesignHidden())
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

	public void htmDesignHidden(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignDesignHidden").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_designHidden").a("class", "").f().sx("hidden").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignHidden(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedPageDesign = false;

	public PageDesign initDeepPageDesign(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedPageDesign) {
			alreadyInitializedPageDesign = true;
			initDeepPageDesign();
		}
		return (PageDesign)this;
	}

	public void initDeepPageDesign() {
		initPageDesign();
		super.initDeepCluster(siteRequest_);
	}

	public void initPageDesign() {
		pageDesignKeyInit();
		htmlPartKeysInit();
		htmlPartSearchInit();
		htmlPartListInit();
		pageDesignCompleteNameInit();
		designHiddenInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepPageDesign(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestPageDesign(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(htmlPartSearch != null)
			htmlPartSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestPageDesign(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainPageDesign(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainPageDesign(String var) {
		PageDesign oPageDesign = (PageDesign)this;
		switch(var) {
			case "pageDesignKey":
				return oPageDesign.pageDesignKey;
			case "htmlPartKeys":
				return oPageDesign.htmlPartKeys;
			case "htmlPartSearch":
				return oPageDesign.htmlPartSearch;
			case "htmlPartList":
				return oPageDesign.htmlPartList;
			case "pageDesignCompleteName":
				return oPageDesign.pageDesignCompleteName;
			case "designHidden":
				return oPageDesign.designHidden;
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
				o = attributePageDesign(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributePageDesign(String var, Object val) {
		PageDesign oPageDesign = (PageDesign)this;
		switch(var) {
			case "htmlPartKeys":
				oPageDesign.addHtmlPartKeys((Long)val);
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
					o = definePageDesign(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definePageDesign(String var, String val) {
		switch(var) {
			case "pageDesignCompleteName":
				setPageDesignCompleteName(val);
				savesPageDesign.add(var);
				return val;
			case "designHidden":
				setDesignHidden(val);
				savesPageDesign.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesPageDesign = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populatePageDesign(solrDocument);
	}
	public void populatePageDesign(SolrDocument solrDocument) {
		PageDesign oPageDesign = (PageDesign)this;
		savesPageDesign = (List<String>)solrDocument.get("savesPageDesign_stored_strings");
		if(savesPageDesign != null) {

			if(savesPageDesign.contains("pageDesignKey")) {
				Long pageDesignKey = (Long)solrDocument.get("pageDesignKey_stored_long");
				if(pageDesignKey != null)
					oPageDesign.setPageDesignKey(pageDesignKey);
			}

			List<Long> htmlPartKeys = (List<Long>)solrDocument.get("htmlPartKeys_stored_longs");
			if(htmlPartKeys != null)
				oPageDesign.htmlPartKeys.addAll(htmlPartKeys);

			if(savesPageDesign.contains("pageDesignCompleteName")) {
				String pageDesignCompleteName = (String)solrDocument.get("pageDesignCompleteName_stored_string");
				if(pageDesignCompleteName != null)
					oPageDesign.setPageDesignCompleteName(pageDesignCompleteName);
			}

			if(savesPageDesign.contains("designHidden")) {
				Boolean designHidden = (Boolean)solrDocument.get("designHidden_stored_boolean");
				if(designHidden != null)
					oPageDesign.setDesignHidden(designHidden);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.design.PageDesign"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			PageDesign o = new PageDesign();
			o.siteRequestPageDesign(siteRequest);
			o.initDeepPageDesign(siteRequest);
			o.indexPageDesign();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexPageDesign();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexPageDesign(document);
	}

	public void indexPageDesign(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexPageDesign(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexPageDesign() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexPageDesign(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexPageDesign(SolrInputDocument document) {
		if(savesPageDesign != null)
			document.addField("savesPageDesign_stored_strings", savesPageDesign);

		if(pageDesignKey != null) {
			document.addField("pageDesignKey_indexed_long", pageDesignKey);
			document.addField("pageDesignKey_stored_long", pageDesignKey);
		}
		if(htmlPartKeys != null) {
			for(java.lang.Long o : htmlPartKeys) {
				document.addField("htmlPartKeys_indexed_longs", o);
			}
			for(java.lang.Long o : htmlPartKeys) {
				document.addField("htmlPartKeys_stored_longs", o);
			}
		}
		if(pageDesignCompleteName != null) {
			document.addField("pageDesignCompleteName_indexed_string", pageDesignCompleteName);
			document.addField("pageDesignCompleteName_stored_string", pageDesignCompleteName);
		}
		if(designHidden != null) {
			document.addField("designHidden_indexed_boolean", designHidden);
			document.addField("designHidden_stored_boolean", designHidden);
		}
		super.indexCluster(document);

	}

	public void unindexPageDesign() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepPageDesign(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedPageDesign(String entityVar) {
		switch(entityVar) {
			case "pageDesignKey":
				return "pageDesignKey_indexed_long";
			case "htmlPartKeys":
				return "htmlPartKeys_indexed_longs";
			case "pageDesignCompleteName":
				return "pageDesignCompleteName_indexed_string";
			case "designHidden":
				return "designHidden_indexed_boolean";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchPageDesign(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestPageDesign(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestCluster(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storePageDesign(solrDocument);
	}
	public void storePageDesign(SolrDocument solrDocument) {
		PageDesign oPageDesign = (PageDesign)this;

		Long pageDesignKey = (Long)solrDocument.get("pageDesignKey_stored_long");
		if(pageDesignKey != null)
			oPageDesign.setPageDesignKey(pageDesignKey);

		List<Long> htmlPartKeys = (List<Long>)solrDocument.get("htmlPartKeys_stored_longs");
		if(htmlPartKeys != null)
			oPageDesign.htmlPartKeys.addAll(htmlPartKeys);

		String pageDesignCompleteName = (String)solrDocument.get("pageDesignCompleteName_stored_string");
		if(pageDesignCompleteName != null)
			oPageDesign.setPageDesignCompleteName(pageDesignCompleteName);

		Boolean designHidden = (Boolean)solrDocument.get("designHidden_stored_boolean");
		if(designHidden != null)
			oPageDesign.setDesignHidden(designHidden);

		super.storeCluster(solrDocument);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestPageDesign() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof PageDesign) {
			PageDesign original = (PageDesign)o;
			if(!Objects.equals(htmlPartKeys, original.getHtmlPartKeys()))
				apiRequest.addVars("htmlPartKeys");
			if(!Objects.equals(pageDesignCompleteName, original.getPageDesignCompleteName()))
				apiRequest.addVars("pageDesignCompleteName");
			if(!Objects.equals(designHidden, original.getDesignHidden()))
				apiRequest.addVars("designHidden");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), htmlPartKeys, pageDesignCompleteName, designHidden);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof PageDesign))
			return false;
		PageDesign that = (PageDesign)o;
		return super.equals(o)
				&& Objects.equals( htmlPartKeys, that.htmlPartKeys )
				&& Objects.equals( pageDesignCompleteName, that.pageDesignCompleteName )
				&& Objects.equals( designHidden, that.designHidden );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PageDesign { ");
		sb.append( "htmlPartKeys: " ).append(htmlPartKeys);
		sb.append( ", pageDesignCompleteName: \"" ).append(pageDesignCompleteName).append( "\"" );
		sb.append( ", designHidden: " ).append(designHidden);
		sb.append(" }");
		return sb.toString();
	}
}
