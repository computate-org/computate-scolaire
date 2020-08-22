package org.computate.scolaire.enUS.design;

import java.util.Arrays;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import java.util.HashMap;
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
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
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
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.design.PageDesign;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class PageDesignGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PageDesign.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String PageDesign_AName = "a page design";
	public static final String PageDesign_This = "this ";
	public static final String PageDesign_ThisName = "this page design";
	public static final String PageDesign_A = "a ";
	public static final String PageDesign_TheName = "the page design";
	public static final String PageDesign_NameSingular = "page design";
	public static final String PageDesign_NamePlural = "page designs";
	public static final String PageDesign_NameActual = "current page design";
	public static final String PageDesign_AllName = "all the page designs";
	public static final String PageDesign_SearchAllNameBy = "search page designs by ";
	public static final String PageDesign_Title = "page designs";
	public static final String PageDesign_ThePluralName = "the page designs";
	public static final String PageDesign_NoNameFound = "no page design found";
	public static final String PageDesign_NameVar = "page-design";
	public static final String PageDesign_OfName = "of page design";
	public static final String PageDesign_ANameAdjective = "a page design";
	public static final String PageDesign_NameAdjectiveSingular = "page design";
	public static final String PageDesign_NameAdjectivePlural = "page designs";
	public static final String PageDesign_Color = "khaki";
	public static final String PageDesign_IconGroup = "regular";
	public static final String PageDesign_IconName = "drafting-compass";
	public static final Integer PageDesign_Rows = 100;

	///////////////////
	// pageDesignKey //
	///////////////////

	/**	 The entity pageDesignKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long pageDesignKey;
	@JsonIgnore
	public Wrap<Long> pageDesignKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("pageDesignKey").o(pageDesignKey);

	/**	<br/> The entity pageDesignKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesignKey">Find the entity pageDesignKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////////
	// childDesignKeys //
	/////////////////////

	/**	 The entity childDesignKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> childDesignKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> childDesignKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("childDesignKeys").o(childDesignKeys);

	/**	<br/> The entity childDesignKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childDesignKeys">Find the entity childDesignKeys in Solr</a>
	 * <br/>
	 * @param childDesignKeys is the entity already constructed. 
	 **/
	protected abstract void _childDesignKeys(List<Long> c);

	public List<Long> getChildDesignKeys() {
		return childDesignKeys;
	}

	public void setChildDesignKeys(List<Long> childDesignKeys) {
		this.childDesignKeys = childDesignKeys;
		this.childDesignKeysWrap.alreadyInitialized = true;
	}
	public PageDesign addChildDesignKeys(Long...objets) {
		for(Long o : objets) {
			addChildDesignKeys(o);
		}
		return (PageDesign)this;
	}
	public PageDesign addChildDesignKeys(Long o) {
		if(o != null && !childDesignKeys.contains(o))
			this.childDesignKeys.add(o);
		return (PageDesign)this;
	}
	public PageDesign setChildDesignKeys(JsonArray objets) {
		childDesignKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addChildDesignKeys(o);
		}
		return (PageDesign)this;
	}
	public PageDesign addChildDesignKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addChildDesignKeys(p);
		}
		return (PageDesign)this;
	}
	protected PageDesign childDesignKeysInit() {
		if(!childDesignKeysWrap.alreadyInitialized) {
			_childDesignKeys(childDesignKeys);
		}
		childDesignKeysWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public List<Long> solrChildDesignKeys() {
		return childDesignKeys;
	}

	public String strChildDesignKeys() {
		return childDesignKeys == null ? "" : childDesignKeys.toString();
	}

	public String jsonChildDesignKeys() {
		return childDesignKeys == null ? "" : childDesignKeys.toString();
	}

	public String nomAffichageChildDesignKeys() {
		return "child designs";
	}

	public String htmTooltipChildDesignKeys() {
		return null;
	}

	public String htmChildDesignKeys() {
		return childDesignKeys == null ? "" : StringEscapeUtils.escapeHtml4(strChildDesignKeys());
	}

	//////////////////////
	// parentDesignKeys //
	//////////////////////

	/**	 The entity parentDesignKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> parentDesignKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> parentDesignKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("parentDesignKeys").o(parentDesignKeys);

	/**	<br/> The entity parentDesignKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:parentDesignKeys">Find the entity parentDesignKeys in Solr</a>
	 * <br/>
	 * @param parentDesignKeys is the entity already constructed. 
	 **/
	protected abstract void _parentDesignKeys(List<Long> c);

	public List<Long> getParentDesignKeys() {
		return parentDesignKeys;
	}

	public void setParentDesignKeys(List<Long> parentDesignKeys) {
		this.parentDesignKeys = parentDesignKeys;
		this.parentDesignKeysWrap.alreadyInitialized = true;
	}
	public PageDesign addParentDesignKeys(Long...objets) {
		for(Long o : objets) {
			addParentDesignKeys(o);
		}
		return (PageDesign)this;
	}
	public PageDesign addParentDesignKeys(Long o) {
		if(o != null && !parentDesignKeys.contains(o))
			this.parentDesignKeys.add(o);
		return (PageDesign)this;
	}
	public PageDesign setParentDesignKeys(JsonArray objets) {
		parentDesignKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addParentDesignKeys(o);
		}
		return (PageDesign)this;
	}
	public PageDesign addParentDesignKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addParentDesignKeys(p);
		}
		return (PageDesign)this;
	}
	protected PageDesign parentDesignKeysInit() {
		if(!parentDesignKeysWrap.alreadyInitialized) {
			_parentDesignKeys(parentDesignKeys);
		}
		parentDesignKeysWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public List<Long> solrParentDesignKeys() {
		return parentDesignKeys;
	}

	public String strParentDesignKeys() {
		return parentDesignKeys == null ? "" : parentDesignKeys.toString();
	}

	public String jsonParentDesignKeys() {
		return parentDesignKeys == null ? "" : parentDesignKeys.toString();
	}

	public String nomAffichageParentDesignKeys() {
		return "parent designs";
	}

	public String htmTooltipParentDesignKeys() {
		return null;
	}

	public String htmParentDesignKeys() {
		return parentDesignKeys == null ? "" : StringEscapeUtils.escapeHtml4(strParentDesignKeys());
	}

	public void inputParentDesignKeys(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "parent designs")
					.a("class", "valueObjectSuggest suggestParentDesignKeys w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setParentDesignKeys")
					.a("id", classApiMethodMethod, "_parentDesignKeys")
					.a("autocomplete", "off")
					.a("oninput", "suggestPageDesignParentDesignKeys($(this).val() ? searchPageDesignFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'childDesignKeys:" + pk + "'}", "], $('#listPageDesignParentDesignKeys_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

		} else {
			e("span").a("class", "varPageDesign", pk, "ParentDesignKeys ").f().sx(htmParentDesignKeys()).g("span");
		}
	}

	public void htmParentDesignKeys(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignParentDesignKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/page-design?fq=childDesignKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-khaki w3-hover-khaki ").f();
								e("i").a("class", "far fa-drafting-compass ").f().g("i");
								sx("parent designs");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate page designs to this page design");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputParentDesignKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listPageDesignParentDesignKeys_", classApiMethodMethod).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), PageDesign.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), PageDesign.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
											.a("id", classApiMethodMethod, "_parentDesignKeys_add")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postPageDesignVals({ childDesignKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "parentDesignKeys')); });")
											.f().sx("add a page design")
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

	//////////////////
	// htmlPartKeys //
	//////////////////

	/**	 The entity htmlPartKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> htmlPartKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> htmlPartKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("htmlPartKeys").o(htmlPartKeys);

	/**	<br/> The entity htmlPartKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPartKeys">Find the entity htmlPartKeys in Solr</a>
	 * <br/>
	 * @param htmlPartKeys is the entity already constructed. 
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "parts")
					.a("class", "valueObjectSuggest suggestHtmlPartKeys w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setHtmlPartKeys")
					.a("id", classApiMethodMethod, "_htmlPartKeys")
					.a("autocomplete", "off")
					.a("oninput", "suggestPageDesignHtmlPartKeys($(this).val() ? searchHtmlPartFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'pageDesignKeys:" + pk + "'}", "], $('#listPageDesignHtmlPartKeys_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

		} else {
			e("span").a("class", "varPageDesign", pk, "HtmlPartKeys ").f().sx(htmHtmlPartKeys()).g("span");
		}
	}

	public void htmHtmlPartKeys(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignHtmlPartKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/html-part?fq=pageDesignKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-khaki w3-hover-khaki ").f();
								e("i").a("class", "far fa-puzzle-piece ").f().g("i");
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
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), HtmlPart.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), HtmlPart.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-khaki ")
											.a("id", classApiMethodMethod, "_htmlPartKeys_add")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postHtmlPartVals({ pageDesignKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "htmlPartKeys')); });")
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

	////////////////////////////
	// pageDesignCompleteName //
	////////////////////////////

	/**	 The entity pageDesignCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageDesignCompleteName;
	@JsonIgnore
	public Wrap<String> pageDesignCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("pageDesignCompleteName").o(pageDesignCompleteName);

	/**	<br/> The entity pageDesignCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesignCompleteName">Find the entity pageDesignCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("input")
				.a("type", "text")
				.a("placeholder", "name")
				.a("id", classApiMethodMethod, "_pageDesignCompleteName");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPageDesignCompleteName classPageDesign inputPageDesign", pk, "PageDesignCompleteName w3-input w3-border ");
					a("name", "setPageDesignCompleteName");
				} else {
					a("class", "valuePageDesignCompleteName w3-input w3-border classPageDesign inputPageDesign", pk, "PageDesignCompleteName w3-input w3-border ");
					a("name", "pageDesignCompleteName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPageDesignCompleteName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_pageDesignCompleteName')); }, function() { addError($('#", classApiMethodMethod, "_pageDesignCompleteName')); }); ");
				}
				a("value", strPageDesignCompleteName())
			.fg();

		} else {
			e("span").a("class", "varPageDesign", pk, "PageDesignCompleteName ").f().sx(htmPageDesignCompleteName()).g("span");
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
							if(
									CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
									) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-khaki ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_pageDesignCompleteName')); $('#", classApiMethodMethod, "_pageDesignCompleteName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#PageDesignForm :input[name=pk]').val() }], 'setPageDesignCompleteName', null, function() { addGlow($('#", classApiMethodMethod, "_pageDesignCompleteName')); }, function() { addError($('#", classApiMethodMethod, "_pageDesignCompleteName')); }); ")
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

	/**	 The entity designHidden
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designHidden;
	@JsonIgnore
	public Wrap<Boolean> designHiddenWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("designHidden").o(designHidden);

	/**	<br/> The entity designHidden
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:designHidden">Find the entity designHidden in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
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
				a("class", "setDesignHidden classPageDesign inputPageDesign", pk, "DesignHidden w3-input w3-border ");
				a("name", "setDesignHidden");
			} else {
				a("class", "valueDesignHidden classPageDesign inputPageDesign", pk, "DesignHidden w3-input w3-border ");
				a("name", "designHidden");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignHidden', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_designHidden')); }, function() { addError($('#", classApiMethodMethod, "_designHidden')); }); ");
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

		} else {
			e("span").a("class", "varPageDesign", pk, "DesignHidden ").f().sx(htmDesignHidden()).g("span");
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

	/////////////////
	// designAdmin //
	/////////////////

	/**	 The entity designAdmin
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designAdmin;
	@JsonIgnore
	public Wrap<Boolean> designAdminWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("designAdmin").o(designAdmin);

	/**	<br/> The entity designAdmin
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:designAdmin">Find the entity designAdmin in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _designAdmin(Wrap<Boolean> c);

	public Boolean getDesignAdmin() {
		return designAdmin;
	}

	public void setDesignAdmin(Boolean designAdmin) {
		this.designAdmin = designAdmin;
		this.designAdminWrap.alreadyInitialized = true;
	}
	public PageDesign setDesignAdmin(String o) {
		this.designAdmin = Boolean.parseBoolean(o);
		this.designAdminWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign designAdminInit() {
		if(!designAdminWrap.alreadyInitialized) {
			_designAdmin(designAdminWrap);
			if(designAdmin == null)
				setDesignAdmin(designAdminWrap.o);
		}
		designAdminWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Boolean solrDesignAdmin() {
		return designAdmin;
	}

	public String strDesignAdmin() {
		return designAdmin == null ? "" : designAdmin.toString();
	}

	public String jsonDesignAdmin() {
		return designAdmin == null ? "" : designAdmin.toString();
	}

	public String nomAffichageDesignAdmin() {
		return "for admin";
	}

	public String htmTooltipDesignAdmin() {
		return null;
	}

	public String htmDesignAdmin() {
		return designAdmin == null ? "" : StringEscapeUtils.escapeHtml4(strDesignAdmin());
	}

	public void inputDesignAdmin(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_designAdmin")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_designAdmin");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setDesignAdmin classPageDesign inputPageDesign", pk, "DesignAdmin w3-input w3-border ");
				a("name", "setDesignAdmin");
			} else {
				a("class", "valueDesignAdmin classPageDesign inputPageDesign", pk, "DesignAdmin w3-input w3-border ");
				a("name", "designAdmin");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignAdmin', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_designAdmin')); }, function() { addError($('#", classApiMethodMethod, "_designAdmin')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getDesignAdmin() != null && getDesignAdmin())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varPageDesign", pk, "DesignAdmin ").f().sx(htmDesignAdmin()).g("span");
		}
	}

	public void htmDesignAdmin(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignDesignAdmin").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_designAdmin").a("class", "").f().sx("for admin").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignAdmin(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////////////
	// designIgnoreEmptyChildName //
	////////////////////////////////

	/**	 The entity designIgnoreEmptyChildName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designIgnoreEmptyChildName;
	@JsonIgnore
	public Wrap<Boolean> designIgnoreEmptyChildNameWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("designIgnoreEmptyChildName").o(designIgnoreEmptyChildName);

	/**	<br/> The entity designIgnoreEmptyChildName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:designIgnoreEmptyChildName">Find the entity designIgnoreEmptyChildName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _designIgnoreEmptyChildName(Wrap<Boolean> c);

	public Boolean getDesignIgnoreEmptyChildName() {
		return designIgnoreEmptyChildName;
	}

	public void setDesignIgnoreEmptyChildName(Boolean designIgnoreEmptyChildName) {
		this.designIgnoreEmptyChildName = designIgnoreEmptyChildName;
		this.designIgnoreEmptyChildNameWrap.alreadyInitialized = true;
	}
	public PageDesign setDesignIgnoreEmptyChildName(String o) {
		this.designIgnoreEmptyChildName = Boolean.parseBoolean(o);
		this.designIgnoreEmptyChildNameWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign designIgnoreEmptyChildNameInit() {
		if(!designIgnoreEmptyChildNameWrap.alreadyInitialized) {
			_designIgnoreEmptyChildName(designIgnoreEmptyChildNameWrap);
			if(designIgnoreEmptyChildName == null)
				setDesignIgnoreEmptyChildName(designIgnoreEmptyChildNameWrap.o);
		}
		designIgnoreEmptyChildNameWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Boolean solrDesignIgnoreEmptyChildName() {
		return designIgnoreEmptyChildName;
	}

	public String strDesignIgnoreEmptyChildName() {
		return designIgnoreEmptyChildName == null ? "" : designIgnoreEmptyChildName.toString();
	}

	public String jsonDesignIgnoreEmptyChildName() {
		return designIgnoreEmptyChildName == null ? "" : designIgnoreEmptyChildName.toString();
	}

	public String nomAffichageDesignIgnoreEmptyChildName() {
		return "ignore empty child name";
	}

	public String htmTooltipDesignIgnoreEmptyChildName() {
		return null;
	}

	public String htmDesignIgnoreEmptyChildName() {
		return designIgnoreEmptyChildName == null ? "" : StringEscapeUtils.escapeHtml4(strDesignIgnoreEmptyChildName());
	}

	public void inputDesignIgnoreEmptyChildName(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_designIgnoreEmptyChildName")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_designIgnoreEmptyChildName");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setDesignIgnoreEmptyChildName classPageDesign inputPageDesign", pk, "DesignIgnoreEmptyChildName w3-input w3-border ");
				a("name", "setDesignIgnoreEmptyChildName");
			} else {
				a("class", "valueDesignIgnoreEmptyChildName classPageDesign inputPageDesign", pk, "DesignIgnoreEmptyChildName w3-input w3-border ");
				a("name", "designIgnoreEmptyChildName");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignIgnoreEmptyChildName', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_designIgnoreEmptyChildName')); }, function() { addError($('#", classApiMethodMethod, "_designIgnoreEmptyChildName')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getDesignIgnoreEmptyChildName() != null && getDesignIgnoreEmptyChildName())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varPageDesign", pk, "DesignIgnoreEmptyChildName ").f().sx(htmDesignIgnoreEmptyChildName()).g("span");
		}
	}

	public void htmDesignIgnoreEmptyChildName(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignDesignIgnoreEmptyChildName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_designIgnoreEmptyChildName").a("class", "").f().sx("ignore empty child name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignIgnoreEmptyChildName(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////////////////
	// designIgnorePaymentsNotPastDue //
	////////////////////////////////////

	/**	 The entity designIgnorePaymentsNotPastDue
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designIgnorePaymentsNotPastDue;
	@JsonIgnore
	public Wrap<Boolean> designIgnorePaymentsNotPastDueWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("designIgnorePaymentsNotPastDue").o(designIgnorePaymentsNotPastDue);

	/**	<br/> The entity designIgnorePaymentsNotPastDue
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:designIgnorePaymentsNotPastDue">Find the entity designIgnorePaymentsNotPastDue in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _designIgnorePaymentsNotPastDue(Wrap<Boolean> c);

	public Boolean getDesignIgnorePaymentsNotPastDue() {
		return designIgnorePaymentsNotPastDue;
	}

	public void setDesignIgnorePaymentsNotPastDue(Boolean designIgnorePaymentsNotPastDue) {
		this.designIgnorePaymentsNotPastDue = designIgnorePaymentsNotPastDue;
		this.designIgnorePaymentsNotPastDueWrap.alreadyInitialized = true;
	}
	public PageDesign setDesignIgnorePaymentsNotPastDue(String o) {
		this.designIgnorePaymentsNotPastDue = Boolean.parseBoolean(o);
		this.designIgnorePaymentsNotPastDueWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign designIgnorePaymentsNotPastDueInit() {
		if(!designIgnorePaymentsNotPastDueWrap.alreadyInitialized) {
			_designIgnorePaymentsNotPastDue(designIgnorePaymentsNotPastDueWrap);
			if(designIgnorePaymentsNotPastDue == null)
				setDesignIgnorePaymentsNotPastDue(designIgnorePaymentsNotPastDueWrap.o);
		}
		designIgnorePaymentsNotPastDueWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Boolean solrDesignIgnorePaymentsNotPastDue() {
		return designIgnorePaymentsNotPastDue;
	}

	public String strDesignIgnorePaymentsNotPastDue() {
		return designIgnorePaymentsNotPastDue == null ? "" : designIgnorePaymentsNotPastDue.toString();
	}

	public String jsonDesignIgnorePaymentsNotPastDue() {
		return designIgnorePaymentsNotPastDue == null ? "" : designIgnorePaymentsNotPastDue.toString();
	}

	public String nomAffichageDesignIgnorePaymentsNotPastDue() {
		return "ignore enrollments not past due";
	}

	public String htmTooltipDesignIgnorePaymentsNotPastDue() {
		return null;
	}

	public String htmDesignIgnorePaymentsNotPastDue() {
		return designIgnorePaymentsNotPastDue == null ? "" : StringEscapeUtils.escapeHtml4(strDesignIgnorePaymentsNotPastDue());
	}

	public void inputDesignIgnorePaymentsNotPastDue(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_designIgnorePaymentsNotPastDue")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_designIgnorePaymentsNotPastDue");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setDesignIgnorePaymentsNotPastDue classPageDesign inputPageDesign", pk, "DesignIgnorePaymentsNotPastDue w3-input w3-border ");
				a("name", "setDesignIgnorePaymentsNotPastDue");
			} else {
				a("class", "valueDesignIgnorePaymentsNotPastDue classPageDesign inputPageDesign", pk, "DesignIgnorePaymentsNotPastDue w3-input w3-border ");
				a("name", "designIgnorePaymentsNotPastDue");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignIgnorePaymentsNotPastDue', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_designIgnorePaymentsNotPastDue')); }, function() { addError($('#", classApiMethodMethod, "_designIgnorePaymentsNotPastDue')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getDesignIgnorePaymentsNotPastDue() != null && getDesignIgnorePaymentsNotPastDue())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varPageDesign", pk, "DesignIgnorePaymentsNotPastDue ").f().sx(htmDesignIgnorePaymentsNotPastDue()).g("span");
		}
	}

	public void htmDesignIgnorePaymentsNotPastDue(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignDesignIgnorePaymentsNotPastDue").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_designIgnorePaymentsNotPastDue").a("class", "").f().sx("ignore enrollments not past due").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignIgnorePaymentsNotPastDue(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////////////
	// designIgnorePaymentsPastDue //
	/////////////////////////////////

	/**	 The entity designIgnorePaymentsPastDue
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designIgnorePaymentsPastDue;
	@JsonIgnore
	public Wrap<Boolean> designIgnorePaymentsPastDueWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("designIgnorePaymentsPastDue").o(designIgnorePaymentsPastDue);

	/**	<br/> The entity designIgnorePaymentsPastDue
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:designIgnorePaymentsPastDue">Find the entity designIgnorePaymentsPastDue in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _designIgnorePaymentsPastDue(Wrap<Boolean> c);

	public Boolean getDesignIgnorePaymentsPastDue() {
		return designIgnorePaymentsPastDue;
	}

	public void setDesignIgnorePaymentsPastDue(Boolean designIgnorePaymentsPastDue) {
		this.designIgnorePaymentsPastDue = designIgnorePaymentsPastDue;
		this.designIgnorePaymentsPastDueWrap.alreadyInitialized = true;
	}
	public PageDesign setDesignIgnorePaymentsPastDue(String o) {
		this.designIgnorePaymentsPastDue = Boolean.parseBoolean(o);
		this.designIgnorePaymentsPastDueWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign designIgnorePaymentsPastDueInit() {
		if(!designIgnorePaymentsPastDueWrap.alreadyInitialized) {
			_designIgnorePaymentsPastDue(designIgnorePaymentsPastDueWrap);
			if(designIgnorePaymentsPastDue == null)
				setDesignIgnorePaymentsPastDue(designIgnorePaymentsPastDueWrap.o);
		}
		designIgnorePaymentsPastDueWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Boolean solrDesignIgnorePaymentsPastDue() {
		return designIgnorePaymentsPastDue;
	}

	public String strDesignIgnorePaymentsPastDue() {
		return designIgnorePaymentsPastDue == null ? "" : designIgnorePaymentsPastDue.toString();
	}

	public String jsonDesignIgnorePaymentsPastDue() {
		return designIgnorePaymentsPastDue == null ? "" : designIgnorePaymentsPastDue.toString();
	}

	public String nomAffichageDesignIgnorePaymentsPastDue() {
		return "ignore enrollments past due";
	}

	public String htmTooltipDesignIgnorePaymentsPastDue() {
		return null;
	}

	public String htmDesignIgnorePaymentsPastDue() {
		return designIgnorePaymentsPastDue == null ? "" : StringEscapeUtils.escapeHtml4(strDesignIgnorePaymentsPastDue());
	}

	public void inputDesignIgnorePaymentsPastDue(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_designIgnorePaymentsPastDue")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_designIgnorePaymentsPastDue");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setDesignIgnorePaymentsPastDue classPageDesign inputPageDesign", pk, "DesignIgnorePaymentsPastDue w3-input w3-border ");
				a("name", "setDesignIgnorePaymentsPastDue");
			} else {
				a("class", "valueDesignIgnorePaymentsPastDue classPageDesign inputPageDesign", pk, "DesignIgnorePaymentsPastDue w3-input w3-border ");
				a("name", "designIgnorePaymentsPastDue");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignIgnorePaymentsPastDue', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_designIgnorePaymentsPastDue')); }, function() { addError($('#", classApiMethodMethod, "_designIgnorePaymentsPastDue')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getDesignIgnorePaymentsPastDue() != null && getDesignIgnorePaymentsPastDue())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varPageDesign", pk, "DesignIgnorePaymentsPastDue ").f().sx(htmDesignIgnorePaymentsPastDue()).g("span");
		}
	}

	public void htmDesignIgnorePaymentsPastDue(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignDesignIgnorePaymentsPastDue").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_designIgnorePaymentsPastDue").a("class", "").f().sx("ignore enrollments past due").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignIgnorePaymentsPastDue(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////////
	// designFilterEnrollmentKey //
	///////////////////////////////

	/**	 The entity designFilterEnrollmentKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designFilterEnrollmentKey;
	@JsonIgnore
	public Wrap<Boolean> designFilterEnrollmentKeyWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("designFilterEnrollmentKey").o(designFilterEnrollmentKey);

	/**	<br/> The entity designFilterEnrollmentKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:designFilterEnrollmentKey">Find the entity designFilterEnrollmentKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _designFilterEnrollmentKey(Wrap<Boolean> c);

	public Boolean getDesignFilterEnrollmentKey() {
		return designFilterEnrollmentKey;
	}

	public void setDesignFilterEnrollmentKey(Boolean designFilterEnrollmentKey) {
		this.designFilterEnrollmentKey = designFilterEnrollmentKey;
		this.designFilterEnrollmentKeyWrap.alreadyInitialized = true;
	}
	public PageDesign setDesignFilterEnrollmentKey(String o) {
		this.designFilterEnrollmentKey = Boolean.parseBoolean(o);
		this.designFilterEnrollmentKeyWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign designFilterEnrollmentKeyInit() {
		if(!designFilterEnrollmentKeyWrap.alreadyInitialized) {
			_designFilterEnrollmentKey(designFilterEnrollmentKeyWrap);
			if(designFilterEnrollmentKey == null)
				setDesignFilterEnrollmentKey(designFilterEnrollmentKeyWrap.o);
		}
		designFilterEnrollmentKeyWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Boolean solrDesignFilterEnrollmentKey() {
		return designFilterEnrollmentKey;
	}

	public String strDesignFilterEnrollmentKey() {
		return designFilterEnrollmentKey == null ? "" : designFilterEnrollmentKey.toString();
	}

	public String jsonDesignFilterEnrollmentKey() {
		return designFilterEnrollmentKey == null ? "" : designFilterEnrollmentKey.toString();
	}

	public String nomAffichageDesignFilterEnrollmentKey() {
		return "filter enrollment key";
	}

	public String htmTooltipDesignFilterEnrollmentKey() {
		return null;
	}

	public String htmDesignFilterEnrollmentKey() {
		return designFilterEnrollmentKey == null ? "" : StringEscapeUtils.escapeHtml4(strDesignFilterEnrollmentKey());
	}

	public void inputDesignFilterEnrollmentKey(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_designFilterEnrollmentKey")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_designFilterEnrollmentKey");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setDesignFilterEnrollmentKey classPageDesign inputPageDesign", pk, "DesignFilterEnrollmentKey w3-input w3-border ");
				a("name", "setDesignFilterEnrollmentKey");
			} else {
				a("class", "valueDesignFilterEnrollmentKey classPageDesign inputPageDesign", pk, "DesignFilterEnrollmentKey w3-input w3-border ");
				a("name", "designFilterEnrollmentKey");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignFilterEnrollmentKey', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_designFilterEnrollmentKey')); }, function() { addError($('#", classApiMethodMethod, "_designFilterEnrollmentKey')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getDesignFilterEnrollmentKey() != null && getDesignFilterEnrollmentKey())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varPageDesign", pk, "DesignFilterEnrollmentKey ").f().sx(htmDesignFilterEnrollmentKey()).g("span");
		}
	}

	public void htmDesignFilterEnrollmentKey(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignDesignFilterEnrollmentKey").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_designFilterEnrollmentKey").a("class", "").f().sx("filter enrollment key").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignFilterEnrollmentKey(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////////////////////////////
	// designEnrollmentSortMonthDayOfBirth //
	/////////////////////////////////////////

	/**	 The entity designEnrollmentSortMonthDayOfBirth
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designEnrollmentSortMonthDayOfBirth;
	@JsonIgnore
	public Wrap<Boolean> designEnrollmentSortMonthDayOfBirthWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("designEnrollmentSortMonthDayOfBirth").o(designEnrollmentSortMonthDayOfBirth);

	/**	<br/> The entity designEnrollmentSortMonthDayOfBirth
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:designEnrollmentSortMonthDayOfBirth">Find the entity designEnrollmentSortMonthDayOfBirth in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _designEnrollmentSortMonthDayOfBirth(Wrap<Boolean> c);

	public Boolean getDesignEnrollmentSortMonthDayOfBirth() {
		return designEnrollmentSortMonthDayOfBirth;
	}

	public void setDesignEnrollmentSortMonthDayOfBirth(Boolean designEnrollmentSortMonthDayOfBirth) {
		this.designEnrollmentSortMonthDayOfBirth = designEnrollmentSortMonthDayOfBirth;
		this.designEnrollmentSortMonthDayOfBirthWrap.alreadyInitialized = true;
	}
	public PageDesign setDesignEnrollmentSortMonthDayOfBirth(String o) {
		this.designEnrollmentSortMonthDayOfBirth = Boolean.parseBoolean(o);
		this.designEnrollmentSortMonthDayOfBirthWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign designEnrollmentSortMonthDayOfBirthInit() {
		if(!designEnrollmentSortMonthDayOfBirthWrap.alreadyInitialized) {
			_designEnrollmentSortMonthDayOfBirth(designEnrollmentSortMonthDayOfBirthWrap);
			if(designEnrollmentSortMonthDayOfBirth == null)
				setDesignEnrollmentSortMonthDayOfBirth(designEnrollmentSortMonthDayOfBirthWrap.o);
		}
		designEnrollmentSortMonthDayOfBirthWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Boolean solrDesignEnrollmentSortMonthDayOfBirth() {
		return designEnrollmentSortMonthDayOfBirth;
	}

	public String strDesignEnrollmentSortMonthDayOfBirth() {
		return designEnrollmentSortMonthDayOfBirth == null ? "" : designEnrollmentSortMonthDayOfBirth.toString();
	}

	public String jsonDesignEnrollmentSortMonthDayOfBirth() {
		return designEnrollmentSortMonthDayOfBirth == null ? "" : designEnrollmentSortMonthDayOfBirth.toString();
	}

	public String nomAffichageDesignEnrollmentSortMonthDayOfBirth() {
		return "enrollment sort month day of birth";
	}

	public String htmTooltipDesignEnrollmentSortMonthDayOfBirth() {
		return null;
	}

	public String htmDesignEnrollmentSortMonthDayOfBirth() {
		return designEnrollmentSortMonthDayOfBirth == null ? "" : StringEscapeUtils.escapeHtml4(strDesignEnrollmentSortMonthDayOfBirth());
	}

	public void inputDesignEnrollmentSortMonthDayOfBirth(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_designEnrollmentSortMonthDayOfBirth")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_designEnrollmentSortMonthDayOfBirth");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setDesignEnrollmentSortMonthDayOfBirth classPageDesign inputPageDesign", pk, "DesignEnrollmentSortMonthDayOfBirth w3-input w3-border ");
				a("name", "setDesignEnrollmentSortMonthDayOfBirth");
			} else {
				a("class", "valueDesignEnrollmentSortMonthDayOfBirth classPageDesign inputPageDesign", pk, "DesignEnrollmentSortMonthDayOfBirth w3-input w3-border ");
				a("name", "designEnrollmentSortMonthDayOfBirth");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignEnrollmentSortMonthDayOfBirth', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_designEnrollmentSortMonthDayOfBirth')); }, function() { addError($('#", classApiMethodMethod, "_designEnrollmentSortMonthDayOfBirth')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getDesignEnrollmentSortMonthDayOfBirth() != null && getDesignEnrollmentSortMonthDayOfBirth())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varPageDesign", pk, "DesignEnrollmentSortMonthDayOfBirth ").f().sx(htmDesignEnrollmentSortMonthDayOfBirth()).g("span");
		}
	}

	public void htmDesignEnrollmentSortMonthDayOfBirth(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignDesignEnrollmentSortMonthDayOfBirth").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_designEnrollmentSortMonthDayOfBirth").a("class", "").f().sx("enrollment sort month day of birth").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignEnrollmentSortMonthDayOfBirth(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////////////
	// designEnrollmentSortGroupName //
	///////////////////////////////////

	/**	 The entity designEnrollmentSortGroupName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designEnrollmentSortGroupName;
	@JsonIgnore
	public Wrap<Boolean> designEnrollmentSortGroupNameWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("designEnrollmentSortGroupName").o(designEnrollmentSortGroupName);

	/**	<br/> The entity designEnrollmentSortGroupName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:designEnrollmentSortGroupName">Find the entity designEnrollmentSortGroupName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _designEnrollmentSortGroupName(Wrap<Boolean> c);

	public Boolean getDesignEnrollmentSortGroupName() {
		return designEnrollmentSortGroupName;
	}

	public void setDesignEnrollmentSortGroupName(Boolean designEnrollmentSortGroupName) {
		this.designEnrollmentSortGroupName = designEnrollmentSortGroupName;
		this.designEnrollmentSortGroupNameWrap.alreadyInitialized = true;
	}
	public PageDesign setDesignEnrollmentSortGroupName(String o) {
		this.designEnrollmentSortGroupName = Boolean.parseBoolean(o);
		this.designEnrollmentSortGroupNameWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign designEnrollmentSortGroupNameInit() {
		if(!designEnrollmentSortGroupNameWrap.alreadyInitialized) {
			_designEnrollmentSortGroupName(designEnrollmentSortGroupNameWrap);
			if(designEnrollmentSortGroupName == null)
				setDesignEnrollmentSortGroupName(designEnrollmentSortGroupNameWrap.o);
		}
		designEnrollmentSortGroupNameWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Boolean solrDesignEnrollmentSortGroupName() {
		return designEnrollmentSortGroupName;
	}

	public String strDesignEnrollmentSortGroupName() {
		return designEnrollmentSortGroupName == null ? "" : designEnrollmentSortGroupName.toString();
	}

	public String jsonDesignEnrollmentSortGroupName() {
		return designEnrollmentSortGroupName == null ? "" : designEnrollmentSortGroupName.toString();
	}

	public String nomAffichageDesignEnrollmentSortGroupName() {
		return "enrollment sort group name";
	}

	public String htmTooltipDesignEnrollmentSortGroupName() {
		return null;
	}

	public String htmDesignEnrollmentSortGroupName() {
		return designEnrollmentSortGroupName == null ? "" : StringEscapeUtils.escapeHtml4(strDesignEnrollmentSortGroupName());
	}

	public void inputDesignEnrollmentSortGroupName(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_designEnrollmentSortGroupName")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_designEnrollmentSortGroupName");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setDesignEnrollmentSortGroupName classPageDesign inputPageDesign", pk, "DesignEnrollmentSortGroupName w3-input w3-border ");
				a("name", "setDesignEnrollmentSortGroupName");
			} else {
				a("class", "valueDesignEnrollmentSortGroupName classPageDesign inputPageDesign", pk, "DesignEnrollmentSortGroupName w3-input w3-border ");
				a("name", "designEnrollmentSortGroupName");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignEnrollmentSortGroupName', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_designEnrollmentSortGroupName')); }, function() { addError($('#", classApiMethodMethod, "_designEnrollmentSortGroupName')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getDesignEnrollmentSortGroupName() != null && getDesignEnrollmentSortGroupName())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varPageDesign", pk, "DesignEnrollmentSortGroupName ").f().sx(htmDesignEnrollmentSortGroupName()).g("span");
		}
	}

	public void htmDesignEnrollmentSortGroupName(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignDesignEnrollmentSortGroupName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_designEnrollmentSortGroupName").a("class", "").f().sx("enrollment sort group name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignEnrollmentSortGroupName(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////////////////////////
	// designEnrollmentSortChildName //
	///////////////////////////////////

	/**	 The entity designEnrollmentSortChildName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean designEnrollmentSortChildName;
	@JsonIgnore
	public Wrap<Boolean> designEnrollmentSortChildNameWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("designEnrollmentSortChildName").o(designEnrollmentSortChildName);

	/**	<br/> The entity designEnrollmentSortChildName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:designEnrollmentSortChildName">Find the entity designEnrollmentSortChildName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _designEnrollmentSortChildName(Wrap<Boolean> c);

	public Boolean getDesignEnrollmentSortChildName() {
		return designEnrollmentSortChildName;
	}

	public void setDesignEnrollmentSortChildName(Boolean designEnrollmentSortChildName) {
		this.designEnrollmentSortChildName = designEnrollmentSortChildName;
		this.designEnrollmentSortChildNameWrap.alreadyInitialized = true;
	}
	public PageDesign setDesignEnrollmentSortChildName(String o) {
		this.designEnrollmentSortChildName = Boolean.parseBoolean(o);
		this.designEnrollmentSortChildNameWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign designEnrollmentSortChildNameInit() {
		if(!designEnrollmentSortChildNameWrap.alreadyInitialized) {
			_designEnrollmentSortChildName(designEnrollmentSortChildNameWrap);
			if(designEnrollmentSortChildName == null)
				setDesignEnrollmentSortChildName(designEnrollmentSortChildNameWrap.o);
		}
		designEnrollmentSortChildNameWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Boolean solrDesignEnrollmentSortChildName() {
		return designEnrollmentSortChildName;
	}

	public String strDesignEnrollmentSortChildName() {
		return designEnrollmentSortChildName == null ? "" : designEnrollmentSortChildName.toString();
	}

	public String jsonDesignEnrollmentSortChildName() {
		return designEnrollmentSortChildName == null ? "" : designEnrollmentSortChildName.toString();
	}

	public String nomAffichageDesignEnrollmentSortChildName() {
		return "enrollment sort child name";
	}

	public String htmTooltipDesignEnrollmentSortChildName() {
		return null;
	}

	public String htmDesignEnrollmentSortChildName() {
		return designEnrollmentSortChildName == null ? "" : StringEscapeUtils.escapeHtml4(strDesignEnrollmentSortChildName());
	}

	public void inputDesignEnrollmentSortChildName(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_designEnrollmentSortChildName")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_designEnrollmentSortChildName");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setDesignEnrollmentSortChildName classPageDesign inputPageDesign", pk, "DesignEnrollmentSortChildName w3-input w3-border ");
				a("name", "setDesignEnrollmentSortChildName");
			} else {
				a("class", "valueDesignEnrollmentSortChildName classPageDesign inputPageDesign", pk, "DesignEnrollmentSortChildName w3-input w3-border ");
				a("name", "designEnrollmentSortChildName");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setDesignEnrollmentSortChildName', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_designEnrollmentSortChildName')); }, function() { addError($('#", classApiMethodMethod, "_designEnrollmentSortChildName')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getDesignEnrollmentSortChildName() != null && getDesignEnrollmentSortChildName())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varPageDesign", pk, "DesignEnrollmentSortChildName ").f().sx(htmDesignEnrollmentSortChildName()).g("span");
		}
	}

	public void htmDesignEnrollmentSortChildName(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignDesignEnrollmentSortChildName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_designEnrollmentSortChildName").a("class", "").f().sx("enrollment sort child name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputDesignEnrollmentSortChildName(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// searchYears //
	/////////////////

	/**	 The entity searchYears
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean searchYears;
	@JsonIgnore
	public Wrap<Boolean> searchYearsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("searchYears").o(searchYears);

	/**	<br/> The entity searchYears
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:searchYears">Find the entity searchYears in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchYears(Wrap<Boolean> c);

	public Boolean getSearchYears() {
		return searchYears;
	}

	public void setSearchYears(Boolean searchYears) {
		this.searchYears = searchYears;
		this.searchYearsWrap.alreadyInitialized = true;
	}
	public PageDesign setSearchYears(String o) {
		this.searchYears = Boolean.parseBoolean(o);
		this.searchYearsWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign searchYearsInit() {
		if(!searchYearsWrap.alreadyInitialized) {
			_searchYears(searchYearsWrap);
			if(searchYears == null)
				setSearchYears(searchYearsWrap.o);
		}
		searchYearsWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Boolean solrSearchYears() {
		return searchYears;
	}

	public String strSearchYears() {
		return searchYears == null ? "" : searchYears.toString();
	}

	public String jsonSearchYears() {
		return searchYears == null ? "" : searchYears.toString();
	}

	public String nomAffichageSearchYears() {
		return "search years";
	}

	public String htmTooltipSearchYears() {
		return null;
	}

	public String htmSearchYears() {
		return searchYears == null ? "" : StringEscapeUtils.escapeHtml4(strSearchYears());
	}

	public void inputSearchYears(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_searchYears")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_searchYears");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSearchYears classPageDesign inputPageDesign", pk, "SearchYears w3-input w3-border ");
				a("name", "setSearchYears");
			} else {
				a("class", "valueSearchYears classPageDesign inputPageDesign", pk, "SearchYears w3-input w3-border ");
				a("name", "searchYears");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSearchYears', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_searchYears')); }, function() { addError($('#", classApiMethodMethod, "_searchYears')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getSearchYears() != null && getSearchYears())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varPageDesign", pk, "SearchYears ").f().sx(htmSearchYears()).g("span");
		}
	}

	public void htmSearchYears(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignSearchYears").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_searchYears").a("class", "").f().sx("search years").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSearchYears(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////
	// searchPayments //
	////////////////////

	/**	 The entity searchPayments
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean searchPayments;
	@JsonIgnore
	public Wrap<Boolean> searchPaymentsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("searchPayments").o(searchPayments);

	/**	<br/> The entity searchPayments
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesign&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:searchPayments">Find the entity searchPayments in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchPayments(Wrap<Boolean> c);

	public Boolean getSearchPayments() {
		return searchPayments;
	}

	public void setSearchPayments(Boolean searchPayments) {
		this.searchPayments = searchPayments;
		this.searchPaymentsWrap.alreadyInitialized = true;
	}
	public PageDesign setSearchPayments(String o) {
		this.searchPayments = Boolean.parseBoolean(o);
		this.searchPaymentsWrap.alreadyInitialized = true;
		return (PageDesign)this;
	}
	protected PageDesign searchPaymentsInit() {
		if(!searchPaymentsWrap.alreadyInitialized) {
			_searchPayments(searchPaymentsWrap);
			if(searchPayments == null)
				setSearchPayments(searchPaymentsWrap.o);
		}
		searchPaymentsWrap.alreadyInitialized(true);
		return (PageDesign)this;
	}

	public Boolean solrSearchPayments() {
		return searchPayments;
	}

	public String strSearchPayments() {
		return searchPayments == null ? "" : searchPayments.toString();
	}

	public String jsonSearchPayments() {
		return searchPayments == null ? "" : searchPayments.toString();
	}

	public String nomAffichageSearchPayments() {
		return "search payments";
	}

	public String htmTooltipSearchPayments() {
		return null;
	}

	public String htmSearchPayments() {
		return searchPayments == null ? "" : StringEscapeUtils.escapeHtml4(strSearchPayments());
	}

	public void inputSearchPayments(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_searchPayments")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_searchPayments");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setSearchPayments classPageDesign inputPageDesign", pk, "SearchPayments w3-input w3-border ");
				a("name", "setSearchPayments");
			} else {
				a("class", "valueSearchPayments classPageDesign inputPageDesign", pk, "SearchPayments w3-input w3-border ");
				a("name", "searchPayments");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSearchPayments', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_searchPayments')); }, function() { addError($('#", classApiMethodMethod, "_searchPayments')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getSearchPayments() != null && getSearchPayments())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varPageDesign", pk, "SearchPayments ").f().sx(htmSearchPayments()).g("span");
		}
	}

	public void htmSearchPayments(String classApiMethodMethod) {
		PageDesign s = (PageDesign)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "PageDesignSearchPayments").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-khaki ").f();
							e("label").a("for", classApiMethodMethod, "_searchPayments").a("class", "").f().sx("search payments").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputSearchPayments(classApiMethodMethod);
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
		childDesignKeysInit();
		parentDesignKeysInit();
		htmlPartKeysInit();
		pageDesignCompleteNameInit();
		designHiddenInit();
		designAdminInit();
		designIgnoreEmptyChildNameInit();
		designIgnorePaymentsNotPastDueInit();
		designIgnorePaymentsPastDueInit();
		designFilterEnrollmentKeyInit();
		designEnrollmentSortMonthDayOfBirthInit();
		designEnrollmentSortGroupNameInit();
		designEnrollmentSortChildNameInit();
		searchYearsInit();
		searchPaymentsInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepPageDesign(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestPageDesign(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
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
			case "childDesignKeys":
				return oPageDesign.childDesignKeys;
			case "parentDesignKeys":
				return oPageDesign.parentDesignKeys;
			case "htmlPartKeys":
				return oPageDesign.htmlPartKeys;
			case "pageDesignCompleteName":
				return oPageDesign.pageDesignCompleteName;
			case "designHidden":
				return oPageDesign.designHidden;
			case "designAdmin":
				return oPageDesign.designAdmin;
			case "designIgnoreEmptyChildName":
				return oPageDesign.designIgnoreEmptyChildName;
			case "designIgnorePaymentsNotPastDue":
				return oPageDesign.designIgnorePaymentsNotPastDue;
			case "designIgnorePaymentsPastDue":
				return oPageDesign.designIgnorePaymentsPastDue;
			case "designFilterEnrollmentKey":
				return oPageDesign.designFilterEnrollmentKey;
			case "designEnrollmentSortMonthDayOfBirth":
				return oPageDesign.designEnrollmentSortMonthDayOfBirth;
			case "designEnrollmentSortGroupName":
				return oPageDesign.designEnrollmentSortGroupName;
			case "designEnrollmentSortChildName":
				return oPageDesign.designEnrollmentSortChildName;
			case "searchYears":
				return oPageDesign.searchYears;
			case "searchPayments":
				return oPageDesign.searchPayments;
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
			case "childDesignKeys":
				oPageDesign.addChildDesignKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "parentDesignKeys":
				oPageDesign.addParentDesignKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "htmlPartKeys":
				oPageDesign.addHtmlPartKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
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
				if(val != null)
					setPageDesignCompleteName(val);
				saves.add(var);
				return val;
			case "designHidden":
				if(val != null)
					setDesignHidden(val);
				saves.add(var);
				return val;
			case "designAdmin":
				if(val != null)
					setDesignAdmin(val);
				saves.add(var);
				return val;
			case "designIgnoreEmptyChildName":
				if(val != null)
					setDesignIgnoreEmptyChildName(val);
				saves.add(var);
				return val;
			case "designIgnorePaymentsNotPastDue":
				if(val != null)
					setDesignIgnorePaymentsNotPastDue(val);
				saves.add(var);
				return val;
			case "designIgnorePaymentsPastDue":
				if(val != null)
					setDesignIgnorePaymentsPastDue(val);
				saves.add(var);
				return val;
			case "designFilterEnrollmentKey":
				if(val != null)
					setDesignFilterEnrollmentKey(val);
				saves.add(var);
				return val;
			case "designEnrollmentSortMonthDayOfBirth":
				if(val != null)
					setDesignEnrollmentSortMonthDayOfBirth(val);
				saves.add(var);
				return val;
			case "designEnrollmentSortGroupName":
				if(val != null)
					setDesignEnrollmentSortGroupName(val);
				saves.add(var);
				return val;
			case "designEnrollmentSortChildName":
				if(val != null)
					setDesignEnrollmentSortChildName(val);
				saves.add(var);
				return val;
			case "searchYears":
				if(val != null)
					setSearchYears(val);
				saves.add(var);
				return val;
			case "searchPayments":
				if(val != null)
					setSearchPayments(val);
				saves.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populatePageDesign(solrDocument);
	}
	public void populatePageDesign(SolrDocument solrDocument) {
		PageDesign oPageDesign = (PageDesign)this;
		saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null) {

			if(saves.contains("pageDesignKey")) {
				Long pageDesignKey = (Long)solrDocument.get("pageDesignKey_stored_long");
				if(pageDesignKey != null)
					oPageDesign.setPageDesignKey(pageDesignKey);
			}

			List<Long> childDesignKeys = (List<Long>)solrDocument.get("childDesignKeys_stored_longs");
			if(childDesignKeys != null)
				oPageDesign.childDesignKeys.addAll(childDesignKeys);

			List<Long> parentDesignKeys = (List<Long>)solrDocument.get("parentDesignKeys_stored_longs");
			if(parentDesignKeys != null)
				oPageDesign.parentDesignKeys.addAll(parentDesignKeys);

			List<Long> htmlPartKeys = (List<Long>)solrDocument.get("htmlPartKeys_stored_longs");
			if(htmlPartKeys != null)
				oPageDesign.htmlPartKeys.addAll(htmlPartKeys);

			if(saves.contains("pageDesignCompleteName")) {
				String pageDesignCompleteName = (String)solrDocument.get("pageDesignCompleteName_stored_string");
				if(pageDesignCompleteName != null)
					oPageDesign.setPageDesignCompleteName(pageDesignCompleteName);
			}

			if(saves.contains("designHidden")) {
				Boolean designHidden = (Boolean)solrDocument.get("designHidden_stored_boolean");
				if(designHidden != null)
					oPageDesign.setDesignHidden(designHidden);
			}

			if(saves.contains("designAdmin")) {
				Boolean designAdmin = (Boolean)solrDocument.get("designAdmin_stored_boolean");
				if(designAdmin != null)
					oPageDesign.setDesignAdmin(designAdmin);
			}

			if(saves.contains("designIgnoreEmptyChildName")) {
				Boolean designIgnoreEmptyChildName = (Boolean)solrDocument.get("designIgnoreEmptyChildName_stored_boolean");
				if(designIgnoreEmptyChildName != null)
					oPageDesign.setDesignIgnoreEmptyChildName(designIgnoreEmptyChildName);
			}

			if(saves.contains("designIgnorePaymentsNotPastDue")) {
				Boolean designIgnorePaymentsNotPastDue = (Boolean)solrDocument.get("designIgnorePaymentsNotPastDue_stored_boolean");
				if(designIgnorePaymentsNotPastDue != null)
					oPageDesign.setDesignIgnorePaymentsNotPastDue(designIgnorePaymentsNotPastDue);
			}

			if(saves.contains("designIgnorePaymentsPastDue")) {
				Boolean designIgnorePaymentsPastDue = (Boolean)solrDocument.get("designIgnorePaymentsPastDue_stored_boolean");
				if(designIgnorePaymentsPastDue != null)
					oPageDesign.setDesignIgnorePaymentsPastDue(designIgnorePaymentsPastDue);
			}

			if(saves.contains("designFilterEnrollmentKey")) {
				Boolean designFilterEnrollmentKey = (Boolean)solrDocument.get("designFilterEnrollmentKey_stored_boolean");
				if(designFilterEnrollmentKey != null)
					oPageDesign.setDesignFilterEnrollmentKey(designFilterEnrollmentKey);
			}

			if(saves.contains("designEnrollmentSortMonthDayOfBirth")) {
				Boolean designEnrollmentSortMonthDayOfBirth = (Boolean)solrDocument.get("designEnrollmentSortMonthDayOfBirth_stored_boolean");
				if(designEnrollmentSortMonthDayOfBirth != null)
					oPageDesign.setDesignEnrollmentSortMonthDayOfBirth(designEnrollmentSortMonthDayOfBirth);
			}

			if(saves.contains("designEnrollmentSortGroupName")) {
				Boolean designEnrollmentSortGroupName = (Boolean)solrDocument.get("designEnrollmentSortGroupName_stored_boolean");
				if(designEnrollmentSortGroupName != null)
					oPageDesign.setDesignEnrollmentSortGroupName(designEnrollmentSortGroupName);
			}

			if(saves.contains("designEnrollmentSortChildName")) {
				Boolean designEnrollmentSortChildName = (Boolean)solrDocument.get("designEnrollmentSortChildName_stored_boolean");
				if(designEnrollmentSortChildName != null)
					oPageDesign.setDesignEnrollmentSortChildName(designEnrollmentSortChildName);
			}

			if(saves.contains("searchYears")) {
				Boolean searchYears = (Boolean)solrDocument.get("searchYears_stored_boolean");
				if(searchYears != null)
					oPageDesign.setSearchYears(searchYears);
			}

			if(saves.contains("searchPayments")) {
				Boolean searchPayments = (Boolean)solrDocument.get("searchPayments_stored_boolean");
				if(searchPayments != null)
					oPageDesign.setSearchPayments(searchPayments);
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
		if(pageDesignKey != null) {
			document.addField("pageDesignKey_indexed_long", pageDesignKey);
			document.addField("pageDesignKey_stored_long", pageDesignKey);
		}
		if(childDesignKeys != null) {
			for(java.lang.Long o : childDesignKeys) {
				document.addField("childDesignKeys_indexed_longs", o);
			}
			for(java.lang.Long o : childDesignKeys) {
				document.addField("childDesignKeys_stored_longs", o);
			}
		}
		if(parentDesignKeys != null) {
			for(java.lang.Long o : parentDesignKeys) {
				document.addField("parentDesignKeys_indexed_longs", o);
			}
			for(java.lang.Long o : parentDesignKeys) {
				document.addField("parentDesignKeys_stored_longs", o);
			}
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
		if(designAdmin != null) {
			document.addField("designAdmin_indexed_boolean", designAdmin);
			document.addField("designAdmin_stored_boolean", designAdmin);
		}
		if(designIgnoreEmptyChildName != null) {
			document.addField("designIgnoreEmptyChildName_indexed_boolean", designIgnoreEmptyChildName);
			document.addField("designIgnoreEmptyChildName_stored_boolean", designIgnoreEmptyChildName);
		}
		if(designIgnorePaymentsNotPastDue != null) {
			document.addField("designIgnorePaymentsNotPastDue_indexed_boolean", designIgnorePaymentsNotPastDue);
			document.addField("designIgnorePaymentsNotPastDue_stored_boolean", designIgnorePaymentsNotPastDue);
		}
		if(designIgnorePaymentsPastDue != null) {
			document.addField("designIgnorePaymentsPastDue_indexed_boolean", designIgnorePaymentsPastDue);
			document.addField("designIgnorePaymentsPastDue_stored_boolean", designIgnorePaymentsPastDue);
		}
		if(designFilterEnrollmentKey != null) {
			document.addField("designFilterEnrollmentKey_indexed_boolean", designFilterEnrollmentKey);
			document.addField("designFilterEnrollmentKey_stored_boolean", designFilterEnrollmentKey);
		}
		if(designEnrollmentSortMonthDayOfBirth != null) {
			document.addField("designEnrollmentSortMonthDayOfBirth_indexed_boolean", designEnrollmentSortMonthDayOfBirth);
			document.addField("designEnrollmentSortMonthDayOfBirth_stored_boolean", designEnrollmentSortMonthDayOfBirth);
		}
		if(designEnrollmentSortGroupName != null) {
			document.addField("designEnrollmentSortGroupName_indexed_boolean", designEnrollmentSortGroupName);
			document.addField("designEnrollmentSortGroupName_stored_boolean", designEnrollmentSortGroupName);
		}
		if(designEnrollmentSortChildName != null) {
			document.addField("designEnrollmentSortChildName_indexed_boolean", designEnrollmentSortChildName);
			document.addField("designEnrollmentSortChildName_stored_boolean", designEnrollmentSortChildName);
		}
		if(searchYears != null) {
			document.addField("searchYears_indexed_boolean", searchYears);
			document.addField("searchYears_stored_boolean", searchYears);
		}
		if(searchPayments != null) {
			document.addField("searchPayments_indexed_boolean", searchPayments);
			document.addField("searchPayments_stored_boolean", searchPayments);
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
			case "childDesignKeys":
				return "childDesignKeys_indexed_longs";
			case "parentDesignKeys":
				return "parentDesignKeys_indexed_longs";
			case "htmlPartKeys":
				return "htmlPartKeys_indexed_longs";
			case "pageDesignCompleteName":
				return "pageDesignCompleteName_indexed_string";
			case "designHidden":
				return "designHidden_indexed_boolean";
			case "designAdmin":
				return "designAdmin_indexed_boolean";
			case "designIgnoreEmptyChildName":
				return "designIgnoreEmptyChildName_indexed_boolean";
			case "designIgnorePaymentsNotPastDue":
				return "designIgnorePaymentsNotPastDue_indexed_boolean";
			case "designIgnorePaymentsPastDue":
				return "designIgnorePaymentsPastDue_indexed_boolean";
			case "designFilterEnrollmentKey":
				return "designFilterEnrollmentKey_indexed_boolean";
			case "designEnrollmentSortMonthDayOfBirth":
				return "designEnrollmentSortMonthDayOfBirth_indexed_boolean";
			case "designEnrollmentSortGroupName":
				return "designEnrollmentSortGroupName_indexed_boolean";
			case "designEnrollmentSortChildName":
				return "designEnrollmentSortChildName_indexed_boolean";
			case "searchYears":
				return "searchYears_indexed_boolean";
			case "searchPayments":
				return "searchPayments_indexed_boolean";
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

	public static String varSuggestedPageDesign(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestedCluster(entityVar);
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

		List<Long> childDesignKeys = (List<Long>)solrDocument.get("childDesignKeys_stored_longs");
		if(childDesignKeys != null)
			oPageDesign.childDesignKeys.addAll(childDesignKeys);

		List<Long> parentDesignKeys = (List<Long>)solrDocument.get("parentDesignKeys_stored_longs");
		if(parentDesignKeys != null)
			oPageDesign.parentDesignKeys.addAll(parentDesignKeys);

		List<Long> htmlPartKeys = (List<Long>)solrDocument.get("htmlPartKeys_stored_longs");
		if(htmlPartKeys != null)
			oPageDesign.htmlPartKeys.addAll(htmlPartKeys);

		String pageDesignCompleteName = (String)solrDocument.get("pageDesignCompleteName_stored_string");
		if(pageDesignCompleteName != null)
			oPageDesign.setPageDesignCompleteName(pageDesignCompleteName);

		Boolean designHidden = (Boolean)solrDocument.get("designHidden_stored_boolean");
		if(designHidden != null)
			oPageDesign.setDesignHidden(designHidden);

		Boolean designAdmin = (Boolean)solrDocument.get("designAdmin_stored_boolean");
		if(designAdmin != null)
			oPageDesign.setDesignAdmin(designAdmin);

		Boolean designIgnoreEmptyChildName = (Boolean)solrDocument.get("designIgnoreEmptyChildName_stored_boolean");
		if(designIgnoreEmptyChildName != null)
			oPageDesign.setDesignIgnoreEmptyChildName(designIgnoreEmptyChildName);

		Boolean designIgnorePaymentsNotPastDue = (Boolean)solrDocument.get("designIgnorePaymentsNotPastDue_stored_boolean");
		if(designIgnorePaymentsNotPastDue != null)
			oPageDesign.setDesignIgnorePaymentsNotPastDue(designIgnorePaymentsNotPastDue);

		Boolean designIgnorePaymentsPastDue = (Boolean)solrDocument.get("designIgnorePaymentsPastDue_stored_boolean");
		if(designIgnorePaymentsPastDue != null)
			oPageDesign.setDesignIgnorePaymentsPastDue(designIgnorePaymentsPastDue);

		Boolean designFilterEnrollmentKey = (Boolean)solrDocument.get("designFilterEnrollmentKey_stored_boolean");
		if(designFilterEnrollmentKey != null)
			oPageDesign.setDesignFilterEnrollmentKey(designFilterEnrollmentKey);

		Boolean designEnrollmentSortMonthDayOfBirth = (Boolean)solrDocument.get("designEnrollmentSortMonthDayOfBirth_stored_boolean");
		if(designEnrollmentSortMonthDayOfBirth != null)
			oPageDesign.setDesignEnrollmentSortMonthDayOfBirth(designEnrollmentSortMonthDayOfBirth);

		Boolean designEnrollmentSortGroupName = (Boolean)solrDocument.get("designEnrollmentSortGroupName_stored_boolean");
		if(designEnrollmentSortGroupName != null)
			oPageDesign.setDesignEnrollmentSortGroupName(designEnrollmentSortGroupName);

		Boolean designEnrollmentSortChildName = (Boolean)solrDocument.get("designEnrollmentSortChildName_stored_boolean");
		if(designEnrollmentSortChildName != null)
			oPageDesign.setDesignEnrollmentSortChildName(designEnrollmentSortChildName);

		Boolean searchYears = (Boolean)solrDocument.get("searchYears_stored_boolean");
		if(searchYears != null)
			oPageDesign.setSearchYears(searchYears);

		Boolean searchPayments = (Boolean)solrDocument.get("searchPayments_stored_boolean");
		if(searchPayments != null)
			oPageDesign.setSearchPayments(searchPayments);

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
			if(!Objects.equals(pageDesignKey, original.getPageDesignKey()))
				apiRequest.addVars("pageDesignKey");
			if(!Objects.equals(childDesignKeys, original.getChildDesignKeys()))
				apiRequest.addVars("childDesignKeys");
			if(!Objects.equals(parentDesignKeys, original.getParentDesignKeys()))
				apiRequest.addVars("parentDesignKeys");
			if(!Objects.equals(htmlPartKeys, original.getHtmlPartKeys()))
				apiRequest.addVars("htmlPartKeys");
			if(!Objects.equals(pageDesignCompleteName, original.getPageDesignCompleteName()))
				apiRequest.addVars("pageDesignCompleteName");
			if(!Objects.equals(designHidden, original.getDesignHidden()))
				apiRequest.addVars("designHidden");
			if(!Objects.equals(designAdmin, original.getDesignAdmin()))
				apiRequest.addVars("designAdmin");
			if(!Objects.equals(designIgnoreEmptyChildName, original.getDesignIgnoreEmptyChildName()))
				apiRequest.addVars("designIgnoreEmptyChildName");
			if(!Objects.equals(designIgnorePaymentsNotPastDue, original.getDesignIgnorePaymentsNotPastDue()))
				apiRequest.addVars("designIgnorePaymentsNotPastDue");
			if(!Objects.equals(designIgnorePaymentsPastDue, original.getDesignIgnorePaymentsPastDue()))
				apiRequest.addVars("designIgnorePaymentsPastDue");
			if(!Objects.equals(designFilterEnrollmentKey, original.getDesignFilterEnrollmentKey()))
				apiRequest.addVars("designFilterEnrollmentKey");
			if(!Objects.equals(designEnrollmentSortMonthDayOfBirth, original.getDesignEnrollmentSortMonthDayOfBirth()))
				apiRequest.addVars("designEnrollmentSortMonthDayOfBirth");
			if(!Objects.equals(designEnrollmentSortGroupName, original.getDesignEnrollmentSortGroupName()))
				apiRequest.addVars("designEnrollmentSortGroupName");
			if(!Objects.equals(designEnrollmentSortChildName, original.getDesignEnrollmentSortChildName()))
				apiRequest.addVars("designEnrollmentSortChildName");
			if(!Objects.equals(searchYears, original.getSearchYears()))
				apiRequest.addVars("searchYears");
			if(!Objects.equals(searchPayments, original.getSearchPayments()))
				apiRequest.addVars("searchPayments");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), pageDesignKey, childDesignKeys, parentDesignKeys, htmlPartKeys, pageDesignCompleteName, designHidden, designAdmin, designIgnoreEmptyChildName, designIgnorePaymentsNotPastDue, designIgnorePaymentsPastDue, designFilterEnrollmentKey, designEnrollmentSortMonthDayOfBirth, designEnrollmentSortGroupName, designEnrollmentSortChildName, searchYears, searchPayments);
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
				&& Objects.equals( pageDesignKey, that.pageDesignKey )
				&& Objects.equals( childDesignKeys, that.childDesignKeys )
				&& Objects.equals( parentDesignKeys, that.parentDesignKeys )
				&& Objects.equals( htmlPartKeys, that.htmlPartKeys )
				&& Objects.equals( pageDesignCompleteName, that.pageDesignCompleteName )
				&& Objects.equals( designHidden, that.designHidden )
				&& Objects.equals( designAdmin, that.designAdmin )
				&& Objects.equals( designIgnoreEmptyChildName, that.designIgnoreEmptyChildName )
				&& Objects.equals( designIgnorePaymentsNotPastDue, that.designIgnorePaymentsNotPastDue )
				&& Objects.equals( designIgnorePaymentsPastDue, that.designIgnorePaymentsPastDue )
				&& Objects.equals( designFilterEnrollmentKey, that.designFilterEnrollmentKey )
				&& Objects.equals( designEnrollmentSortMonthDayOfBirth, that.designEnrollmentSortMonthDayOfBirth )
				&& Objects.equals( designEnrollmentSortGroupName, that.designEnrollmentSortGroupName )
				&& Objects.equals( designEnrollmentSortChildName, that.designEnrollmentSortChildName )
				&& Objects.equals( searchYears, that.searchYears )
				&& Objects.equals( searchPayments, that.searchPayments );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PageDesign { ");
		sb.append( "pageDesignKey: " ).append(pageDesignKey);
		sb.append( ", childDesignKeys: " ).append(childDesignKeys);
		sb.append( ", parentDesignKeys: " ).append(parentDesignKeys);
		sb.append( ", htmlPartKeys: " ).append(htmlPartKeys);
		sb.append( ", pageDesignCompleteName: \"" ).append(pageDesignCompleteName).append( "\"" );
		sb.append( ", designHidden: " ).append(designHidden);
		sb.append( ", designAdmin: " ).append(designAdmin);
		sb.append( ", designIgnoreEmptyChildName: " ).append(designIgnoreEmptyChildName);
		sb.append( ", designIgnorePaymentsNotPastDue: " ).append(designIgnorePaymentsNotPastDue);
		sb.append( ", designIgnorePaymentsPastDue: " ).append(designIgnorePaymentsPastDue);
		sb.append( ", designFilterEnrollmentKey: " ).append(designFilterEnrollmentKey);
		sb.append( ", designEnrollmentSortMonthDayOfBirth: " ).append(designEnrollmentSortMonthDayOfBirth);
		sb.append( ", designEnrollmentSortGroupName: " ).append(designEnrollmentSortGroupName);
		sb.append( ", designEnrollmentSortChildName: " ).append(designEnrollmentSortChildName);
		sb.append( ", searchYears: " ).append(searchYears);
		sb.append( ", searchPayments: " ).append(searchPayments);
		sb.append(" }");
		return sb.toString();
	}
}
