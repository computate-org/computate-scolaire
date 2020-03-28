package org.computate.scolaire.enUS.writer;

import org.computate.scolaire.enUS.writer.AllWriters;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.vertx.AppSwagger2;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ApiWriterGen<DEV> extends Object {

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteRequest_">Trouver l'entité siteRequest_ dans Solr</a>
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
	protected ApiWriter siteRequest_Init() {
		if(!siteRequest_Wrap.alreadyInitialized) {
			_siteRequest_(siteRequest_Wrap);
			if(siteRequest_ == null)
				setSiteRequest_(siteRequest_Wrap.o);
		}
		siteRequest_Wrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	///////////////////////
	// classSolrDocument //
	///////////////////////

	/**	L'entité « classSolrDocument »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SolrDocument classSolrDocument;
	@JsonIgnore
	public Wrap<SolrDocument> classSolrDocumentWrap = new Wrap<SolrDocument>().p(this).c(SolrDocument.class).var("classSolrDocument").o(classSolrDocument);

	/**	<br/>L'entité « classSolrDocument »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classSolrDocument">Trouver l'entité classSolrDocument dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classSolrDocument(Wrap<SolrDocument> c);

	public SolrDocument getClassSolrDocument() {
		return classSolrDocument;
	}

	public void setClassSolrDocument(SolrDocument classSolrDocument) {
		this.classSolrDocument = classSolrDocument;
		this.classSolrDocumentWrap.alreadyInitialized = true;
	}
	protected ApiWriter classSolrDocumentInit() {
		if(!classSolrDocumentWrap.alreadyInitialized) {
			_classSolrDocument(classSolrDocumentWrap);
			if(classSolrDocument == null)
				setClassSolrDocument(classSolrDocumentWrap.o);
		}
		classSolrDocumentWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	/////////////////
	// contextRows //
	/////////////////

	/**	L'entité « contextRows »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer contextRows;
	@JsonIgnore
	public Wrap<Integer> contextRowsWrap = new Wrap<Integer>().p(this).c(Integer.class).var("contextRows").o(contextRows);

	/**	<br/>L'entité « contextRows »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:contextRows">Trouver l'entité contextRows dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _contextRows(Wrap<Integer> c);

	public Integer getContextRows() {
		return contextRows;
	}

	public void setContextRows(Integer contextRows) {
		this.contextRows = contextRows;
		this.contextRowsWrap.alreadyInitialized = true;
	}
	public ApiWriter setContextRows(String o) {
		if(NumberUtils.isParsable(o))
			this.contextRows = Integer.parseInt(o);
		this.contextRowsWrap.alreadyInitialized = true;
		return (ApiWriter)this;
	}
	protected ApiWriter contextRowsInit() {
		if(!contextRowsWrap.alreadyInitialized) {
			_contextRows(contextRowsWrap);
			if(contextRows == null)
				setContextRows(contextRowsWrap.o);
		}
		contextRowsWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public Integer solrContextRows() {
		return contextRows;
	}

	public String strContextRows() {
		return contextRows == null ? "" : contextRows.toString();
	}

	public String jsonContextRows() {
		return contextRows == null ? "" : contextRows.toString();
	}

	public String nomAffichageContextRows() {
		return null;
	}

	public String htmTooltipContextRows() {
		return null;
	}

	public String htmContextRows() {
		return contextRows == null ? "" : StringEscapeUtils.escapeHtml4(strContextRows());
	}

	////////////////////
	// classApiMethod //
	////////////////////

	/**	L'entité « classApiMethod »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classApiMethod;
	@JsonIgnore
	public Wrap<String> classApiMethodWrap = new Wrap<String>().p(this).c(String.class).var("classApiMethod").o(classApiMethod);

	/**	<br/>L'entité « classApiMethod »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classApiMethod">Trouver l'entité classApiMethod dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classApiMethod(Wrap<String> c);

	public String getClassApiMethod() {
		return classApiMethod;
	}

	public void setClassApiMethod(String classApiMethod) {
		this.classApiMethod = classApiMethod;
		this.classApiMethodWrap.alreadyInitialized = true;
	}
	protected ApiWriter classApiMethodInit() {
		if(!classApiMethodWrap.alreadyInitialized) {
			_classApiMethod(classApiMethodWrap);
			if(classApiMethod == null)
				setClassApiMethod(classApiMethodWrap.o);
		}
		classApiMethodWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassApiMethod() {
		return classApiMethod;
	}

	public String strClassApiMethod() {
		return classApiMethod == null ? "" : classApiMethod;
	}

	public String jsonClassApiMethod() {
		return classApiMethod == null ? "" : classApiMethod;
	}

	public String nomAffichageClassApiMethod() {
		return null;
	}

	public String htmTooltipClassApiMethod() {
		return null;
	}

	public String htmClassApiMethod() {
		return classApiMethod == null ? "" : StringEscapeUtils.escapeHtml4(strClassApiMethod());
	}

	////////////////////
	// openApiVersion //
	////////////////////

	/**	L'entité « openApiVersion »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String openApiVersion;
	@JsonIgnore
	public Wrap<String> openApiVersionWrap = new Wrap<String>().p(this).c(String.class).var("openApiVersion").o(openApiVersion);

	/**	<br/>L'entité « openApiVersion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:openApiVersion">Trouver l'entité openApiVersion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiVersion(Wrap<String> c);

	public String getOpenApiVersion() {
		return openApiVersion;
	}

	public void setOpenApiVersion(String openApiVersion) {
		this.openApiVersion = openApiVersion;
		this.openApiVersionWrap.alreadyInitialized = true;
	}
	protected ApiWriter openApiVersionInit() {
		if(!openApiVersionWrap.alreadyInitialized) {
			_openApiVersion(openApiVersionWrap);
			if(openApiVersion == null)
				setOpenApiVersion(openApiVersionWrap.o);
		}
		openApiVersionWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrOpenApiVersion() {
		return openApiVersion;
	}

	public String strOpenApiVersion() {
		return openApiVersion == null ? "" : openApiVersion;
	}

	public String jsonOpenApiVersion() {
		return openApiVersion == null ? "" : openApiVersion;
	}

	public String nomAffichageOpenApiVersion() {
		return null;
	}

	public String htmTooltipOpenApiVersion() {
		return null;
	}

	public String htmOpenApiVersion() {
		return openApiVersion == null ? "" : StringEscapeUtils.escapeHtml4(strOpenApiVersion());
	}

	/////////////////
	// appSwagger2 //
	/////////////////

	/**	L'entité « appSwagger2 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AppSwagger2 appSwagger2;
	@JsonIgnore
	public Wrap<AppSwagger2> appSwagger2Wrap = new Wrap<AppSwagger2>().p(this).c(AppSwagger2.class).var("appSwagger2").o(appSwagger2);

	/**	<br/>L'entité « appSwagger2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:appSwagger2">Trouver l'entité appSwagger2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _appSwagger2(Wrap<AppSwagger2> c);

	public AppSwagger2 getAppSwagger2() {
		return appSwagger2;
	}

	public void setAppSwagger2(AppSwagger2 appSwagger2) {
		this.appSwagger2 = appSwagger2;
		this.appSwagger2Wrap.alreadyInitialized = true;
	}
	protected ApiWriter appSwagger2Init() {
		if(!appSwagger2Wrap.alreadyInitialized) {
			_appSwagger2(appSwagger2Wrap);
			if(appSwagger2 == null)
				setAppSwagger2(appSwagger2Wrap.o);
		}
		if(appSwagger2 != null)
			appSwagger2.initDeepForClass(siteRequest_);
		appSwagger2Wrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	///////////////
	// classUris //
	///////////////

	/**	L'entité « classUris »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> classUris;
	@JsonIgnore
	public Wrap<List<String>> classUrisWrap = new Wrap<List<String>>().p(this).c(List.class).var("classUris").o(classUris);

	/**	<br/>L'entité « classUris »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classUris">Trouver l'entité classUris dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classUris(Wrap<List<String>> c);

	public List<String> getClassUris() {
		return classUris;
	}

	public void setClassUris(List<String> classUris) {
		this.classUris = classUris;
		this.classUrisWrap.alreadyInitialized = true;
	}
	public ApiWriter addClassUris(String...objets) {
		for(String o : objets) {
			addClassUris(o);
		}
		return (ApiWriter)this;
	}
	public ApiWriter addClassUris(String o) {
		if(o != null && !classUris.contains(o))
			this.classUris.add(o);
		return (ApiWriter)this;
	}
	public ApiWriter setClassUris(JsonArray objets) {
		classUris.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClassUris(o);
		}
		return (ApiWriter)this;
	}
	protected ApiWriter classUrisInit() {
		if(!classUrisWrap.alreadyInitialized) {
			_classUris(classUrisWrap);
			if(classUris == null)
				setClassUris(classUrisWrap.o);
		}
		classUrisWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public List<String> solrClassUris() {
		return classUris;
	}

	public String strClassUris() {
		return classUris == null ? "" : classUris.toString();
	}

	public String jsonClassUris() {
		return classUris == null ? "" : classUris.toString();
	}

	public String nomAffichageClassUris() {
		return null;
	}

	public String htmTooltipClassUris() {
		return null;
	}

	public String htmClassUris() {
		return classUris == null ? "" : StringEscapeUtils.escapeHtml4(strClassUris());
	}

	//////////////////////////
	// openApiVersionNumber //
	//////////////////////////

	/**	L'entité « openApiVersionNumber »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer openApiVersionNumber;
	@JsonIgnore
	public Wrap<Integer> openApiVersionNumberWrap = new Wrap<Integer>().p(this).c(Integer.class).var("openApiVersionNumber").o(openApiVersionNumber);

	/**	<br/>L'entité « openApiVersionNumber »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:openApiVersionNumber">Trouver l'entité openApiVersionNumber dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiVersionNumber(Wrap<Integer> c);

	public Integer getOpenApiVersionNumber() {
		return openApiVersionNumber;
	}

	public void setOpenApiVersionNumber(Integer openApiVersionNumber) {
		this.openApiVersionNumber = openApiVersionNumber;
		this.openApiVersionNumberWrap.alreadyInitialized = true;
	}
	public ApiWriter setOpenApiVersionNumber(String o) {
		if(NumberUtils.isParsable(o))
			this.openApiVersionNumber = Integer.parseInt(o);
		this.openApiVersionNumberWrap.alreadyInitialized = true;
		return (ApiWriter)this;
	}
	protected ApiWriter openApiVersionNumberInit() {
		if(!openApiVersionNumberWrap.alreadyInitialized) {
			_openApiVersionNumber(openApiVersionNumberWrap);
			if(openApiVersionNumber == null)
				setOpenApiVersionNumber(openApiVersionNumberWrap.o);
		}
		openApiVersionNumberWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public Integer solrOpenApiVersionNumber() {
		return openApiVersionNumber;
	}

	public String strOpenApiVersionNumber() {
		return openApiVersionNumber == null ? "" : openApiVersionNumber.toString();
	}

	public String jsonOpenApiVersionNumber() {
		return openApiVersionNumber == null ? "" : openApiVersionNumber.toString();
	}

	public String nomAffichageOpenApiVersionNumber() {
		return null;
	}

	public String htmTooltipOpenApiVersionNumber() {
		return null;
	}

	public String htmOpenApiVersionNumber() {
		return openApiVersionNumber == null ? "" : StringEscapeUtils.escapeHtml4(strOpenApiVersionNumber());
	}

	////////////////
	// tabsSchema //
	////////////////

	/**	L'entité « tabsSchema »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer tabsSchema;
	@JsonIgnore
	public Wrap<Integer> tabsSchemaWrap = new Wrap<Integer>().p(this).c(Integer.class).var("tabsSchema").o(tabsSchema);

	/**	<br/>L'entité « tabsSchema »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:tabsSchema">Trouver l'entité tabsSchema dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tabsSchema(Wrap<Integer> c);

	public Integer getTabsSchema() {
		return tabsSchema;
	}

	public void setTabsSchema(Integer tabsSchema) {
		this.tabsSchema = tabsSchema;
		this.tabsSchemaWrap.alreadyInitialized = true;
	}
	public ApiWriter setTabsSchema(String o) {
		if(NumberUtils.isParsable(o))
			this.tabsSchema = Integer.parseInt(o);
		this.tabsSchemaWrap.alreadyInitialized = true;
		return (ApiWriter)this;
	}
	protected ApiWriter tabsSchemaInit() {
		if(!tabsSchemaWrap.alreadyInitialized) {
			_tabsSchema(tabsSchemaWrap);
			if(tabsSchema == null)
				setTabsSchema(tabsSchemaWrap.o);
		}
		tabsSchemaWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public Integer solrTabsSchema() {
		return tabsSchema;
	}

	public String strTabsSchema() {
		return tabsSchema == null ? "" : tabsSchema.toString();
	}

	public String jsonTabsSchema() {
		return tabsSchema == null ? "" : tabsSchema.toString();
	}

	public String nomAffichageTabsSchema() {
		return null;
	}

	public String htmTooltipTabsSchema() {
		return null;
	}

	public String htmTabsSchema() {
		return tabsSchema == null ? "" : StringEscapeUtils.escapeHtml4(strTabsSchema());
	}

	///////////////////
	// tabsResponses //
	///////////////////

	/**	L'entité « tabsResponses »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer tabsResponses;
	@JsonIgnore
	public Wrap<Integer> tabsResponsesWrap = new Wrap<Integer>().p(this).c(Integer.class).var("tabsResponses").o(tabsResponses);

	/**	<br/>L'entité « tabsResponses »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:tabsResponses">Trouver l'entité tabsResponses dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tabsResponses(Wrap<Integer> c);

	public Integer getTabsResponses() {
		return tabsResponses;
	}

	public void setTabsResponses(Integer tabsResponses) {
		this.tabsResponses = tabsResponses;
		this.tabsResponsesWrap.alreadyInitialized = true;
	}
	public ApiWriter setTabsResponses(String o) {
		if(NumberUtils.isParsable(o))
			this.tabsResponses = Integer.parseInt(o);
		this.tabsResponsesWrap.alreadyInitialized = true;
		return (ApiWriter)this;
	}
	protected ApiWriter tabsResponsesInit() {
		if(!tabsResponsesWrap.alreadyInitialized) {
			_tabsResponses(tabsResponsesWrap);
			if(tabsResponses == null)
				setTabsResponses(tabsResponsesWrap.o);
		}
		tabsResponsesWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public Integer solrTabsResponses() {
		return tabsResponses;
	}

	public String strTabsResponses() {
		return tabsResponses == null ? "" : tabsResponses.toString();
	}

	public String jsonTabsResponses() {
		return tabsResponses == null ? "" : tabsResponses.toString();
	}

	public String nomAffichageTabsResponses() {
		return null;
	}

	public String htmTooltipTabsResponses() {
		return null;
	}

	public String htmTabsResponses() {
		return tabsResponses == null ? "" : StringEscapeUtils.escapeHtml4(strTabsResponses());
	}

	////////////
	// wPaths //
	////////////

	/**	L'entité « wPaths »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wPaths;
	@JsonIgnore
	public Wrap<AllWriter> wPathsWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wPaths").o(wPaths);

	/**	<br/>L'entité « wPaths »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wPaths">Trouver l'entité wPaths dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wPaths(Wrap<AllWriter> c);

	public AllWriter getWPaths() {
		return wPaths;
	}

	public void setWPaths(AllWriter wPaths) {
		this.wPaths = wPaths;
		this.wPathsWrap.alreadyInitialized = true;
	}
	protected ApiWriter wPathsInit() {
		if(!wPathsWrap.alreadyInitialized) {
			_wPaths(wPathsWrap);
			if(wPaths == null)
				setWPaths(wPathsWrap.o);
		}
		if(wPaths != null)
			wPaths.initDeepForClass(siteRequest_);
		wPathsWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	////////////////////
	// wRequestBodies //
	////////////////////

	/**	L'entité « wRequestBodies »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wRequestBodies;
	@JsonIgnore
	public Wrap<AllWriter> wRequestBodiesWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wRequestBodies").o(wRequestBodies);

	/**	<br/>L'entité « wRequestBodies »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wRequestBodies">Trouver l'entité wRequestBodies dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wRequestBodies(Wrap<AllWriter> c);

	public AllWriter getWRequestBodies() {
		return wRequestBodies;
	}

	public void setWRequestBodies(AllWriter wRequestBodies) {
		this.wRequestBodies = wRequestBodies;
		this.wRequestBodiesWrap.alreadyInitialized = true;
	}
	protected ApiWriter wRequestBodiesInit() {
		if(!wRequestBodiesWrap.alreadyInitialized) {
			_wRequestBodies(wRequestBodiesWrap);
			if(wRequestBodies == null)
				setWRequestBodies(wRequestBodiesWrap.o);
		}
		if(wRequestBodies != null)
			wRequestBodies.initDeepForClass(siteRequest_);
		wRequestBodiesWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	//////////////
	// wSchemas //
	//////////////

	/**	L'entité « wSchemas »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wSchemas;
	@JsonIgnore
	public Wrap<AllWriter> wSchemasWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wSchemas").o(wSchemas);

	/**	<br/>L'entité « wSchemas »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wSchemas">Trouver l'entité wSchemas dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wSchemas(Wrap<AllWriter> c);

	public AllWriter getWSchemas() {
		return wSchemas;
	}

	public void setWSchemas(AllWriter wSchemas) {
		this.wSchemas = wSchemas;
		this.wSchemasWrap.alreadyInitialized = true;
	}
	protected ApiWriter wSchemasInit() {
		if(!wSchemasWrap.alreadyInitialized) {
			_wSchemas(wSchemasWrap);
			if(wSchemas == null)
				setWSchemas(wSchemasWrap.o);
		}
		if(wSchemas != null)
			wSchemas.initDeepForClass(siteRequest_);
		wSchemasWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	/////////////////
	// siteContext //
	/////////////////

	/**	L'entité « siteContext »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SiteContextEnUS siteContext;
	@JsonIgnore
	public Wrap<SiteContextEnUS> siteContextWrap = new Wrap<SiteContextEnUS>().p(this).c(SiteContextEnUS.class).var("siteContext").o(siteContext);

	/**	<br/>L'entité « siteContext »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteContext">Trouver l'entité siteContext dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteContext(Wrap<SiteContextEnUS> c);

	public SiteContextEnUS getSiteContext() {
		return siteContext;
	}

	public void setSiteContext(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
		this.siteContextWrap.alreadyInitialized = true;
	}
	protected ApiWriter siteContextInit() {
		if(!siteContextWrap.alreadyInitialized) {
			_siteContext(siteContextWrap);
			if(siteContext == null)
				setSiteContext(siteContextWrap.o);
		}
		if(siteContext != null)
			siteContext.initDeepForClass(siteRequest_);
		siteContextWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	////////////////
	// siteConfig //
	////////////////

	/**	L'entité « siteConfig »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SiteConfig siteConfig;
	@JsonIgnore
	public Wrap<SiteConfig> siteConfigWrap = new Wrap<SiteConfig>().p(this).c(SiteConfig.class).var("siteConfig").o(siteConfig);

	/**	<br/>L'entité « siteConfig »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteConfig">Trouver l'entité siteConfig dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteConfig(Wrap<SiteConfig> c);

	public SiteConfig getSiteConfig() {
		return siteConfig;
	}

	public void setSiteConfig(SiteConfig siteConfig) {
		this.siteConfig = siteConfig;
		this.siteConfigWrap.alreadyInitialized = true;
	}
	protected ApiWriter siteConfigInit() {
		if(!siteConfigWrap.alreadyInitialized) {
			_siteConfig(siteConfigWrap);
			if(siteConfig == null)
				setSiteConfig(siteConfigWrap.o);
		}
		if(siteConfig != null)
			siteConfig.initDeepForClass(siteRequest_);
		siteConfigWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	/////////////////////
	// wRequestHeaders //
	/////////////////////

	/**	L'entité « wRequestHeaders »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wRequestHeaders;
	@JsonIgnore
	public Wrap<AllWriter> wRequestHeadersWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wRequestHeaders").o(wRequestHeaders);

	/**	<br/>L'entité « wRequestHeaders »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wRequestHeaders">Trouver l'entité wRequestHeaders dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wRequestHeaders(Wrap<AllWriter> c);

	public AllWriter getWRequestHeaders() {
		return wRequestHeaders;
	}

	public void setWRequestHeaders(AllWriter wRequestHeaders) {
		this.wRequestHeaders = wRequestHeaders;
		this.wRequestHeadersWrap.alreadyInitialized = true;
	}
	protected ApiWriter wRequestHeadersInit() {
		if(!wRequestHeadersWrap.alreadyInitialized) {
			_wRequestHeaders(wRequestHeadersWrap);
			if(wRequestHeaders == null)
				setWRequestHeaders(wRequestHeadersWrap.o);
		}
		if(wRequestHeaders != null)
			wRequestHeaders.initDeepForClass(siteRequest_);
		wRequestHeadersWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	/////////////////////////
	// wRequestDescription //
	/////////////////////////

	/**	L'entité « wRequestDescription »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wRequestDescription;
	@JsonIgnore
	public Wrap<AllWriter> wRequestDescriptionWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wRequestDescription").o(wRequestDescription);

	/**	<br/>L'entité « wRequestDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wRequestDescription">Trouver l'entité wRequestDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wRequestDescription(Wrap<AllWriter> c);

	public AllWriter getWRequestDescription() {
		return wRequestDescription;
	}

	public void setWRequestDescription(AllWriter wRequestDescription) {
		this.wRequestDescription = wRequestDescription;
		this.wRequestDescriptionWrap.alreadyInitialized = true;
	}
	protected ApiWriter wRequestDescriptionInit() {
		if(!wRequestDescriptionWrap.alreadyInitialized) {
			_wRequestDescription(wRequestDescriptionWrap);
			if(wRequestDescription == null)
				setWRequestDescription(wRequestDescriptionWrap.o);
		}
		if(wRequestDescription != null)
			wRequestDescription.initDeepForClass(siteRequest_);
		wRequestDescriptionWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	//////////////////////////
	// wResponseDescription //
	//////////////////////////

	/**	L'entité « wResponseDescription »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wResponseDescription;
	@JsonIgnore
	public Wrap<AllWriter> wResponseDescriptionWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wResponseDescription").o(wResponseDescription);

	/**	<br/>L'entité « wResponseDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wResponseDescription">Trouver l'entité wResponseDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wResponseDescription(Wrap<AllWriter> c);

	public AllWriter getWResponseDescription() {
		return wResponseDescription;
	}

	public void setWResponseDescription(AllWriter wResponseDescription) {
		this.wResponseDescription = wResponseDescription;
		this.wResponseDescriptionWrap.alreadyInitialized = true;
	}
	protected ApiWriter wResponseDescriptionInit() {
		if(!wResponseDescriptionWrap.alreadyInitialized) {
			_wResponseDescription(wResponseDescriptionWrap);
			if(wResponseDescription == null)
				setWResponseDescription(wResponseDescriptionWrap.o);
		}
		if(wResponseDescription != null)
			wResponseDescription.initDeepForClass(siteRequest_);
		wResponseDescriptionWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	//////////////////
	// wRequestBody //
	//////////////////

	/**	L'entité « wRequestBody »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wRequestBody;
	@JsonIgnore
	public Wrap<AllWriter> wRequestBodyWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wRequestBody").o(wRequestBody);

	/**	<br/>L'entité « wRequestBody »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wRequestBody">Trouver l'entité wRequestBody dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wRequestBody(Wrap<AllWriter> c);

	public AllWriter getWRequestBody() {
		return wRequestBody;
	}

	public void setWRequestBody(AllWriter wRequestBody) {
		this.wRequestBody = wRequestBody;
		this.wRequestBodyWrap.alreadyInitialized = true;
	}
	protected ApiWriter wRequestBodyInit() {
		if(!wRequestBodyWrap.alreadyInitialized) {
			_wRequestBody(wRequestBodyWrap);
			if(wRequestBody == null)
				setWRequestBody(wRequestBodyWrap.o);
		}
		if(wRequestBody != null)
			wRequestBody.initDeepForClass(siteRequest_);
		wRequestBodyWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	///////////////////
	// wResponseBody //
	///////////////////

	/**	L'entité « wResponseBody »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wResponseBody;
	@JsonIgnore
	public Wrap<AllWriter> wResponseBodyWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wResponseBody").o(wResponseBody);

	/**	<br/>L'entité « wResponseBody »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wResponseBody">Trouver l'entité wResponseBody dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wResponseBody(Wrap<AllWriter> c);

	public AllWriter getWResponseBody() {
		return wResponseBody;
	}

	public void setWResponseBody(AllWriter wResponseBody) {
		this.wResponseBody = wResponseBody;
		this.wResponseBodyWrap.alreadyInitialized = true;
	}
	protected ApiWriter wResponseBodyInit() {
		if(!wResponseBodyWrap.alreadyInitialized) {
			_wResponseBody(wResponseBodyWrap);
			if(wResponseBody == null)
				setWResponseBody(wResponseBodyWrap.o);
		}
		if(wResponseBody != null)
			wResponseBody.initDeepForClass(siteRequest_);
		wResponseBodyWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	////////////////////
	// wRequestSchema //
	////////////////////

	/**	L'entité « wRequestSchema »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wRequestSchema;
	@JsonIgnore
	public Wrap<AllWriter> wRequestSchemaWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wRequestSchema").o(wRequestSchema);

	/**	<br/>L'entité « wRequestSchema »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wRequestSchema">Trouver l'entité wRequestSchema dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wRequestSchema(Wrap<AllWriter> c);

	public AllWriter getWRequestSchema() {
		return wRequestSchema;
	}

	public void setWRequestSchema(AllWriter wRequestSchema) {
		this.wRequestSchema = wRequestSchema;
		this.wRequestSchemaWrap.alreadyInitialized = true;
	}
	protected ApiWriter wRequestSchemaInit() {
		if(!wRequestSchemaWrap.alreadyInitialized) {
			_wRequestSchema(wRequestSchemaWrap);
			if(wRequestSchema == null)
				setWRequestSchema(wRequestSchemaWrap.o);
		}
		if(wRequestSchema != null)
			wRequestSchema.initDeepForClass(siteRequest_);
		wRequestSchemaWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	/////////////////////
	// wResponseSchema //
	/////////////////////

	/**	L'entité « wResponseSchema »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriter wResponseSchema;
	@JsonIgnore
	public Wrap<AllWriter> wResponseSchemaWrap = new Wrap<AllWriter>().p(this).c(AllWriter.class).var("wResponseSchema").o(wResponseSchema);

	/**	<br/>L'entité « wResponseSchema »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wResponseSchema">Trouver l'entité wResponseSchema dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wResponseSchema(Wrap<AllWriter> c);

	public AllWriter getWResponseSchema() {
		return wResponseSchema;
	}

	public void setWResponseSchema(AllWriter wResponseSchema) {
		this.wResponseSchema = wResponseSchema;
		this.wResponseSchemaWrap.alreadyInitialized = true;
	}
	protected ApiWriter wResponseSchemaInit() {
		if(!wResponseSchemaWrap.alreadyInitialized) {
			_wResponseSchema(wResponseSchemaWrap);
			if(wResponseSchema == null)
				setWResponseSchema(wResponseSchemaWrap.o);
		}
		if(wResponseSchema != null)
			wResponseSchema.initDeepForClass(siteRequest_);
		wResponseSchemaWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	/////////////
	// writers //
	/////////////

	/**	L'entité « writers »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected AllWriters writers;
	@JsonIgnore
	public Wrap<AllWriters> writersWrap = new Wrap<AllWriters>().p(this).c(AllWriters.class).var("writers").o(writers);

	/**	<br/>L'entité « writers »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:writers">Trouver l'entité writers dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _writers(Wrap<AllWriters> c);

	public AllWriters getWriters() {
		return writers;
	}

	public void setWriters(AllWriters writers) {
		this.writers = writers;
		this.writersWrap.alreadyInitialized = true;
	}
	protected ApiWriter writersInit() {
		if(!writersWrap.alreadyInitialized) {
			_writers(writersWrap);
			if(writers == null)
				setWriters(writersWrap.o);
		}
		if(writers != null)
			writers.initDeepForClass(siteRequest_);
		writersWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	/////////////////
	// classApiTag //
	/////////////////

	/**	L'entité « classApiTag »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classApiTag;
	@JsonIgnore
	public Wrap<String> classApiTagWrap = new Wrap<String>().p(this).c(String.class).var("classApiTag").o(classApiTag);

	/**	<br/>L'entité « classApiTag »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classApiTag">Trouver l'entité classApiTag dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classApiTag(Wrap<String> c);

	public String getClassApiTag() {
		return classApiTag;
	}

	public void setClassApiTag(String classApiTag) {
		this.classApiTag = classApiTag;
		this.classApiTagWrap.alreadyInitialized = true;
	}
	protected ApiWriter classApiTagInit() {
		if(!classApiTagWrap.alreadyInitialized) {
			_classApiTag(classApiTagWrap);
			if(classApiTag == null)
				setClassApiTag(classApiTagWrap.o);
		}
		classApiTagWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassApiTag() {
		return classApiTag;
	}

	public String strClassApiTag() {
		return classApiTag == null ? "" : classApiTag;
	}

	public String jsonClassApiTag() {
		return classApiTag == null ? "" : classApiTag;
	}

	public String nomAffichageClassApiTag() {
		return null;
	}

	public String htmTooltipClassApiTag() {
		return null;
	}

	public String htmClassApiTag() {
		return classApiTag == null ? "" : StringEscapeUtils.escapeHtml4(strClassApiTag());
	}

	//////////////////////
	// classExtendsBase //
	//////////////////////

	/**	L'entité « classExtendsBase »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classExtendsBase;
	@JsonIgnore
	public Wrap<Boolean> classExtendsBaseWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("classExtendsBase").o(classExtendsBase);

	/**	<br/>L'entité « classExtendsBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classExtendsBase">Trouver l'entité classExtendsBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classExtendsBase(Wrap<Boolean> c);

	public Boolean getClassExtendsBase() {
		return classExtendsBase;
	}

	public void setClassExtendsBase(Boolean classExtendsBase) {
		this.classExtendsBase = classExtendsBase;
		this.classExtendsBaseWrap.alreadyInitialized = true;
	}
	public ApiWriter setClassExtendsBase(String o) {
		this.classExtendsBase = Boolean.parseBoolean(o);
		this.classExtendsBaseWrap.alreadyInitialized = true;
		return (ApiWriter)this;
	}
	protected ApiWriter classExtendsBaseInit() {
		if(!classExtendsBaseWrap.alreadyInitialized) {
			_classExtendsBase(classExtendsBaseWrap);
			if(classExtendsBase == null)
				setClassExtendsBase(classExtendsBaseWrap.o);
		}
		classExtendsBaseWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public Boolean solrClassExtendsBase() {
		return classExtendsBase;
	}

	public String strClassExtendsBase() {
		return classExtendsBase == null ? "" : classExtendsBase.toString();
	}

	public String jsonClassExtendsBase() {
		return classExtendsBase == null ? "" : classExtendsBase.toString();
	}

	public String nomAffichageClassExtendsBase() {
		return null;
	}

	public String htmTooltipClassExtendsBase() {
		return null;
	}

	public String htmClassExtendsBase() {
		return classExtendsBase == null ? "" : StringEscapeUtils.escapeHtml4(strClassExtendsBase());
	}

	/////////////////
	// classIsBase //
	/////////////////

	/**	L'entité « classIsBase »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classIsBase;
	@JsonIgnore
	public Wrap<Boolean> classIsBaseWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("classIsBase").o(classIsBase);

	/**	<br/>L'entité « classIsBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classIsBase">Trouver l'entité classIsBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classIsBase(Wrap<Boolean> c);

	public Boolean getClassIsBase() {
		return classIsBase;
	}

	public void setClassIsBase(Boolean classIsBase) {
		this.classIsBase = classIsBase;
		this.classIsBaseWrap.alreadyInitialized = true;
	}
	public ApiWriter setClassIsBase(String o) {
		this.classIsBase = Boolean.parseBoolean(o);
		this.classIsBaseWrap.alreadyInitialized = true;
		return (ApiWriter)this;
	}
	protected ApiWriter classIsBaseInit() {
		if(!classIsBaseWrap.alreadyInitialized) {
			_classIsBase(classIsBaseWrap);
			if(classIsBase == null)
				setClassIsBase(classIsBaseWrap.o);
		}
		classIsBaseWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public Boolean solrClassIsBase() {
		return classIsBase;
	}

	public String strClassIsBase() {
		return classIsBase == null ? "" : classIsBase.toString();
	}

	public String jsonClassIsBase() {
		return classIsBase == null ? "" : classIsBase.toString();
	}

	public String nomAffichageClassIsBase() {
		return null;
	}

	public String htmTooltipClassIsBase() {
		return null;
	}

	public String htmClassIsBase() {
		return classIsBase == null ? "" : StringEscapeUtils.escapeHtml4(strClassIsBase());
	}

	/////////////////////
	// classSimpleName //
	/////////////////////

	/**	L'entité « classSimpleName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classSimpleName;
	@JsonIgnore
	public Wrap<String> classSimpleNameWrap = new Wrap<String>().p(this).c(String.class).var("classSimpleName").o(classSimpleName);

	/**	<br/>L'entité « classSimpleName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classSimpleName">Trouver l'entité classSimpleName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classSimpleName(Wrap<String> c);

	public String getClassSimpleName() {
		return classSimpleName;
	}

	public void setClassSimpleName(String classSimpleName) {
		this.classSimpleName = classSimpleName;
		this.classSimpleNameWrap.alreadyInitialized = true;
	}
	protected ApiWriter classSimpleNameInit() {
		if(!classSimpleNameWrap.alreadyInitialized) {
			_classSimpleName(classSimpleNameWrap);
			if(classSimpleName == null)
				setClassSimpleName(classSimpleNameWrap.o);
		}
		classSimpleNameWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassSimpleName() {
		return classSimpleName;
	}

	public String strClassSimpleName() {
		return classSimpleName == null ? "" : classSimpleName;
	}

	public String jsonClassSimpleName() {
		return classSimpleName == null ? "" : classSimpleName;
	}

	public String nomAffichageClassSimpleName() {
		return null;
	}

	public String htmTooltipClassSimpleName() {
		return null;
	}

	public String htmClassSimpleName() {
		return classSimpleName == null ? "" : StringEscapeUtils.escapeHtml4(strClassSimpleName());
	}

	/////////////
	// appName //
	/////////////

	/**	L'entité « appName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String appName;
	@JsonIgnore
	public Wrap<String> appNameWrap = new Wrap<String>().p(this).c(String.class).var("appName").o(appName);

	/**	<br/>L'entité « appName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:appName">Trouver l'entité appName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _appName(Wrap<String> c);

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
		this.appNameWrap.alreadyInitialized = true;
	}
	protected ApiWriter appNameInit() {
		if(!appNameWrap.alreadyInitialized) {
			_appName(appNameWrap);
			if(appName == null)
				setAppName(appNameWrap.o);
		}
		appNameWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrAppName() {
		return appName;
	}

	public String strAppName() {
		return appName == null ? "" : appName;
	}

	public String jsonAppName() {
		return appName == null ? "" : appName;
	}

	public String nomAffichageAppName() {
		return null;
	}

	public String htmTooltipAppName() {
		return null;
	}

	public String htmAppName() {
		return appName == null ? "" : StringEscapeUtils.escapeHtml4(strAppName());
	}

	///////////////////////
	// classAbsolutePath //
	///////////////////////

	/**	L'entité « classAbsolutePath »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classAbsolutePath;
	@JsonIgnore
	public Wrap<String> classAbsolutePathWrap = new Wrap<String>().p(this).c(String.class).var("classAbsolutePath").o(classAbsolutePath);

	/**	<br/>L'entité « classAbsolutePath »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classAbsolutePath">Trouver l'entité classAbsolutePath dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classAbsolutePath(Wrap<String> c);

	public String getClassAbsolutePath() {
		return classAbsolutePath;
	}

	public void setClassAbsolutePath(String classAbsolutePath) {
		this.classAbsolutePath = classAbsolutePath;
		this.classAbsolutePathWrap.alreadyInitialized = true;
	}
	protected ApiWriter classAbsolutePathInit() {
		if(!classAbsolutePathWrap.alreadyInitialized) {
			_classAbsolutePath(classAbsolutePathWrap);
			if(classAbsolutePath == null)
				setClassAbsolutePath(classAbsolutePathWrap.o);
		}
		classAbsolutePathWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassAbsolutePath() {
		return classAbsolutePath;
	}

	public String strClassAbsolutePath() {
		return classAbsolutePath == null ? "" : classAbsolutePath;
	}

	public String jsonClassAbsolutePath() {
		return classAbsolutePath == null ? "" : classAbsolutePath;
	}

	public String nomAffichageClassAbsolutePath() {
		return null;
	}

	public String htmTooltipClassAbsolutePath() {
		return null;
	}

	public String htmClassAbsolutePath() {
		return classAbsolutePath == null ? "" : StringEscapeUtils.escapeHtml4(strClassAbsolutePath());
	}

	///////////////////////
	// classApiUriMethod //
	///////////////////////

	/**	L'entité « classApiUriMethod »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classApiUriMethod;
	@JsonIgnore
	public Wrap<String> classApiUriMethodWrap = new Wrap<String>().p(this).c(String.class).var("classApiUriMethod").o(classApiUriMethod);

	/**	<br/>L'entité « classApiUriMethod »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classApiUriMethod">Trouver l'entité classApiUriMethod dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classApiUriMethod(Wrap<String> c);

	public String getClassApiUriMethod() {
		return classApiUriMethod;
	}

	public void setClassApiUriMethod(String classApiUriMethod) {
		this.classApiUriMethod = classApiUriMethod;
		this.classApiUriMethodWrap.alreadyInitialized = true;
	}
	protected ApiWriter classApiUriMethodInit() {
		if(!classApiUriMethodWrap.alreadyInitialized) {
			_classApiUriMethod(classApiUriMethodWrap);
			if(classApiUriMethod == null)
				setClassApiUriMethod(classApiUriMethodWrap.o);
		}
		classApiUriMethodWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassApiUriMethod() {
		return classApiUriMethod;
	}

	public String strClassApiUriMethod() {
		return classApiUriMethod == null ? "" : classApiUriMethod;
	}

	public String jsonClassApiUriMethod() {
		return classApiUriMethod == null ? "" : classApiUriMethod;
	}

	public String nomAffichageClassApiUriMethod() {
		return null;
	}

	public String htmTooltipClassApiUriMethod() {
		return null;
	}

	public String htmClassApiUriMethod() {
		return classApiUriMethod == null ? "" : StringEscapeUtils.escapeHtml4(strClassApiUriMethod());
	}

	//////////////////////////
	// classApiMethodMethod //
	//////////////////////////

	/**	L'entité « classApiMethodMethod »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classApiMethodMethod;
	@JsonIgnore
	public Wrap<String> classApiMethodMethodWrap = new Wrap<String>().p(this).c(String.class).var("classApiMethodMethod").o(classApiMethodMethod);

	/**	<br/>L'entité « classApiMethodMethod »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classApiMethodMethod">Trouver l'entité classApiMethodMethod dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classApiMethodMethod(Wrap<String> c);

	public String getClassApiMethodMethod() {
		return classApiMethodMethod;
	}

	public void setClassApiMethodMethod(String classApiMethodMethod) {
		this.classApiMethodMethod = classApiMethodMethod;
		this.classApiMethodMethodWrap.alreadyInitialized = true;
	}
	protected ApiWriter classApiMethodMethodInit() {
		if(!classApiMethodMethodWrap.alreadyInitialized) {
			_classApiMethodMethod(classApiMethodMethodWrap);
			if(classApiMethodMethod == null)
				setClassApiMethodMethod(classApiMethodMethodWrap.o);
		}
		classApiMethodMethodWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassApiMethodMethod() {
		return classApiMethodMethod;
	}

	public String strClassApiMethodMethod() {
		return classApiMethodMethod == null ? "" : classApiMethodMethod;
	}

	public String jsonClassApiMethodMethod() {
		return classApiMethodMethod == null ? "" : classApiMethodMethod;
	}

	public String nomAffichageClassApiMethodMethod() {
		return null;
	}

	public String htmTooltipClassApiMethodMethod() {
		return null;
	}

	public String htmClassApiMethodMethod() {
		return classApiMethodMethod == null ? "" : StringEscapeUtils.escapeHtml4(strClassApiMethodMethod());
	}

	////////////////////////////////
	// classApiMediaType200Method //
	////////////////////////////////

	/**	L'entité « classApiMediaType200Method »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classApiMediaType200Method;
	@JsonIgnore
	public Wrap<String> classApiMediaType200MethodWrap = new Wrap<String>().p(this).c(String.class).var("classApiMediaType200Method").o(classApiMediaType200Method);

	/**	<br/>L'entité « classApiMediaType200Method »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classApiMediaType200Method">Trouver l'entité classApiMediaType200Method dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classApiMediaType200Method(Wrap<String> c);

	public String getClassApiMediaType200Method() {
		return classApiMediaType200Method;
	}

	public void setClassApiMediaType200Method(String classApiMediaType200Method) {
		this.classApiMediaType200Method = classApiMediaType200Method;
		this.classApiMediaType200MethodWrap.alreadyInitialized = true;
	}
	protected ApiWriter classApiMediaType200MethodInit() {
		if(!classApiMediaType200MethodWrap.alreadyInitialized) {
			_classApiMediaType200Method(classApiMediaType200MethodWrap);
			if(classApiMediaType200Method == null)
				setClassApiMediaType200Method(classApiMediaType200MethodWrap.o);
		}
		classApiMediaType200MethodWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassApiMediaType200Method() {
		return classApiMediaType200Method;
	}

	public String strClassApiMediaType200Method() {
		return classApiMediaType200Method == null ? "" : classApiMediaType200Method;
	}

	public String jsonClassApiMediaType200Method() {
		return classApiMediaType200Method == null ? "" : classApiMediaType200Method;
	}

	public String nomAffichageClassApiMediaType200Method() {
		return null;
	}

	public String htmTooltipClassApiMediaType200Method() {
		return null;
	}

	public String htmClassApiMediaType200Method() {
		return classApiMediaType200Method == null ? "" : StringEscapeUtils.escapeHtml4(strClassApiMediaType200Method());
	}

	///////////////////////////////
	// classApiOperationIdMethod //
	///////////////////////////////

	/**	L'entité « classApiOperationIdMethod »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classApiOperationIdMethod;
	@JsonIgnore
	public Wrap<String> classApiOperationIdMethodWrap = new Wrap<String>().p(this).c(String.class).var("classApiOperationIdMethod").o(classApiOperationIdMethod);

	/**	<br/>L'entité « classApiOperationIdMethod »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classApiOperationIdMethod">Trouver l'entité classApiOperationIdMethod dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classApiOperationIdMethod(Wrap<String> c);

	public String getClassApiOperationIdMethod() {
		return classApiOperationIdMethod;
	}

	public void setClassApiOperationIdMethod(String classApiOperationIdMethod) {
		this.classApiOperationIdMethod = classApiOperationIdMethod;
		this.classApiOperationIdMethodWrap.alreadyInitialized = true;
	}
	protected ApiWriter classApiOperationIdMethodInit() {
		if(!classApiOperationIdMethodWrap.alreadyInitialized) {
			_classApiOperationIdMethod(classApiOperationIdMethodWrap);
			if(classApiOperationIdMethod == null)
				setClassApiOperationIdMethod(classApiOperationIdMethodWrap.o);
		}
		classApiOperationIdMethodWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassApiOperationIdMethod() {
		return classApiOperationIdMethod;
	}

	public String strClassApiOperationIdMethod() {
		return classApiOperationIdMethod == null ? "" : classApiOperationIdMethod;
	}

	public String jsonClassApiOperationIdMethod() {
		return classApiOperationIdMethod == null ? "" : classApiOperationIdMethod;
	}

	public String nomAffichageClassApiOperationIdMethod() {
		return null;
	}

	public String htmTooltipClassApiOperationIdMethod() {
		return null;
	}

	public String htmClassApiOperationIdMethod() {
		return classApiOperationIdMethod == null ? "" : StringEscapeUtils.escapeHtml4(strClassApiOperationIdMethod());
	}

	//////////////////////////////////////
	// classApiOperationIdMethodRequest //
	//////////////////////////////////////

	/**	L'entité « classApiOperationIdMethodRequest »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classApiOperationIdMethodRequest;
	@JsonIgnore
	public Wrap<String> classApiOperationIdMethodRequestWrap = new Wrap<String>().p(this).c(String.class).var("classApiOperationIdMethodRequest").o(classApiOperationIdMethodRequest);

	/**	<br/>L'entité « classApiOperationIdMethodRequest »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classApiOperationIdMethodRequest">Trouver l'entité classApiOperationIdMethodRequest dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classApiOperationIdMethodRequest(Wrap<String> c);

	public String getClassApiOperationIdMethodRequest() {
		return classApiOperationIdMethodRequest;
	}

	public void setClassApiOperationIdMethodRequest(String classApiOperationIdMethodRequest) {
		this.classApiOperationIdMethodRequest = classApiOperationIdMethodRequest;
		this.classApiOperationIdMethodRequestWrap.alreadyInitialized = true;
	}
	protected ApiWriter classApiOperationIdMethodRequestInit() {
		if(!classApiOperationIdMethodRequestWrap.alreadyInitialized) {
			_classApiOperationIdMethodRequest(classApiOperationIdMethodRequestWrap);
			if(classApiOperationIdMethodRequest == null)
				setClassApiOperationIdMethodRequest(classApiOperationIdMethodRequestWrap.o);
		}
		classApiOperationIdMethodRequestWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassApiOperationIdMethodRequest() {
		return classApiOperationIdMethodRequest;
	}

	public String strClassApiOperationIdMethodRequest() {
		return classApiOperationIdMethodRequest == null ? "" : classApiOperationIdMethodRequest;
	}

	public String jsonClassApiOperationIdMethodRequest() {
		return classApiOperationIdMethodRequest == null ? "" : classApiOperationIdMethodRequest;
	}

	public String nomAffichageClassApiOperationIdMethodRequest() {
		return null;
	}

	public String htmTooltipClassApiOperationIdMethodRequest() {
		return null;
	}

	public String htmClassApiOperationIdMethodRequest() {
		return classApiOperationIdMethodRequest == null ? "" : StringEscapeUtils.escapeHtml4(strClassApiOperationIdMethodRequest());
	}

	///////////////////////////////////////
	// classApiOperationIdMethodResponse //
	///////////////////////////////////////

	/**	L'entité « classApiOperationIdMethodResponse »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classApiOperationIdMethodResponse;
	@JsonIgnore
	public Wrap<String> classApiOperationIdMethodResponseWrap = new Wrap<String>().p(this).c(String.class).var("classApiOperationIdMethodResponse").o(classApiOperationIdMethodResponse);

	/**	<br/>L'entité « classApiOperationIdMethodResponse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classApiOperationIdMethodResponse">Trouver l'entité classApiOperationIdMethodResponse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classApiOperationIdMethodResponse(Wrap<String> c);

	public String getClassApiOperationIdMethodResponse() {
		return classApiOperationIdMethodResponse;
	}

	public void setClassApiOperationIdMethodResponse(String classApiOperationIdMethodResponse) {
		this.classApiOperationIdMethodResponse = classApiOperationIdMethodResponse;
		this.classApiOperationIdMethodResponseWrap.alreadyInitialized = true;
	}
	protected ApiWriter classApiOperationIdMethodResponseInit() {
		if(!classApiOperationIdMethodResponseWrap.alreadyInitialized) {
			_classApiOperationIdMethodResponse(classApiOperationIdMethodResponseWrap);
			if(classApiOperationIdMethodResponse == null)
				setClassApiOperationIdMethodResponse(classApiOperationIdMethodResponseWrap.o);
		}
		classApiOperationIdMethodResponseWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassApiOperationIdMethodResponse() {
		return classApiOperationIdMethodResponse;
	}

	public String strClassApiOperationIdMethodResponse() {
		return classApiOperationIdMethodResponse == null ? "" : classApiOperationIdMethodResponse;
	}

	public String jsonClassApiOperationIdMethodResponse() {
		return classApiOperationIdMethodResponse == null ? "" : classApiOperationIdMethodResponse;
	}

	public String nomAffichageClassApiOperationIdMethodResponse() {
		return null;
	}

	public String htmTooltipClassApiOperationIdMethodResponse() {
		return null;
	}

	public String htmClassApiOperationIdMethodResponse() {
		return classApiOperationIdMethodResponse == null ? "" : StringEscapeUtils.escapeHtml4(strClassApiOperationIdMethodResponse());
	}

	///////////////////////////////////////////
	// classSuperApiOperationIdMethodRequest //
	///////////////////////////////////////////

	/**	L'entité « classSuperApiOperationIdMethodRequest »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classSuperApiOperationIdMethodRequest;
	@JsonIgnore
	public Wrap<String> classSuperApiOperationIdMethodRequestWrap = new Wrap<String>().p(this).c(String.class).var("classSuperApiOperationIdMethodRequest").o(classSuperApiOperationIdMethodRequest);

	/**	<br/>L'entité « classSuperApiOperationIdMethodRequest »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classSuperApiOperationIdMethodRequest">Trouver l'entité classSuperApiOperationIdMethodRequest dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classSuperApiOperationIdMethodRequest(Wrap<String> c);

	public String getClassSuperApiOperationIdMethodRequest() {
		return classSuperApiOperationIdMethodRequest;
	}

	public void setClassSuperApiOperationIdMethodRequest(String classSuperApiOperationIdMethodRequest) {
		this.classSuperApiOperationIdMethodRequest = classSuperApiOperationIdMethodRequest;
		this.classSuperApiOperationIdMethodRequestWrap.alreadyInitialized = true;
	}
	protected ApiWriter classSuperApiOperationIdMethodRequestInit() {
		if(!classSuperApiOperationIdMethodRequestWrap.alreadyInitialized) {
			_classSuperApiOperationIdMethodRequest(classSuperApiOperationIdMethodRequestWrap);
			if(classSuperApiOperationIdMethodRequest == null)
				setClassSuperApiOperationIdMethodRequest(classSuperApiOperationIdMethodRequestWrap.o);
		}
		classSuperApiOperationIdMethodRequestWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassSuperApiOperationIdMethodRequest() {
		return classSuperApiOperationIdMethodRequest;
	}

	public String strClassSuperApiOperationIdMethodRequest() {
		return classSuperApiOperationIdMethodRequest == null ? "" : classSuperApiOperationIdMethodRequest;
	}

	public String jsonClassSuperApiOperationIdMethodRequest() {
		return classSuperApiOperationIdMethodRequest == null ? "" : classSuperApiOperationIdMethodRequest;
	}

	public String nomAffichageClassSuperApiOperationIdMethodRequest() {
		return null;
	}

	public String htmTooltipClassSuperApiOperationIdMethodRequest() {
		return null;
	}

	public String htmClassSuperApiOperationIdMethodRequest() {
		return classSuperApiOperationIdMethodRequest == null ? "" : StringEscapeUtils.escapeHtml4(strClassSuperApiOperationIdMethodRequest());
	}

	////////////////////////////////////////////
	// classSuperApiOperationIdMethodResponse //
	////////////////////////////////////////////

	/**	L'entité « classSuperApiOperationIdMethodResponse »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classSuperApiOperationIdMethodResponse;
	@JsonIgnore
	public Wrap<String> classSuperApiOperationIdMethodResponseWrap = new Wrap<String>().p(this).c(String.class).var("classSuperApiOperationIdMethodResponse").o(classSuperApiOperationIdMethodResponse);

	/**	<br/>L'entité « classSuperApiOperationIdMethodResponse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classSuperApiOperationIdMethodResponse">Trouver l'entité classSuperApiOperationIdMethodResponse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classSuperApiOperationIdMethodResponse(Wrap<String> c);

	public String getClassSuperApiOperationIdMethodResponse() {
		return classSuperApiOperationIdMethodResponse;
	}

	public void setClassSuperApiOperationIdMethodResponse(String classSuperApiOperationIdMethodResponse) {
		this.classSuperApiOperationIdMethodResponse = classSuperApiOperationIdMethodResponse;
		this.classSuperApiOperationIdMethodResponseWrap.alreadyInitialized = true;
	}
	protected ApiWriter classSuperApiOperationIdMethodResponseInit() {
		if(!classSuperApiOperationIdMethodResponseWrap.alreadyInitialized) {
			_classSuperApiOperationIdMethodResponse(classSuperApiOperationIdMethodResponseWrap);
			if(classSuperApiOperationIdMethodResponse == null)
				setClassSuperApiOperationIdMethodResponse(classSuperApiOperationIdMethodResponseWrap.o);
		}
		classSuperApiOperationIdMethodResponseWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassSuperApiOperationIdMethodResponse() {
		return classSuperApiOperationIdMethodResponse;
	}

	public String strClassSuperApiOperationIdMethodResponse() {
		return classSuperApiOperationIdMethodResponse == null ? "" : classSuperApiOperationIdMethodResponse;
	}

	public String jsonClassSuperApiOperationIdMethodResponse() {
		return classSuperApiOperationIdMethodResponse == null ? "" : classSuperApiOperationIdMethodResponse;
	}

	public String nomAffichageClassSuperApiOperationIdMethodResponse() {
		return null;
	}

	public String htmTooltipClassSuperApiOperationIdMethodResponse() {
		return null;
	}

	public String htmClassSuperApiOperationIdMethodResponse() {
		return classSuperApiOperationIdMethodResponse == null ? "" : StringEscapeUtils.escapeHtml4(strClassSuperApiOperationIdMethodResponse());
	}

	//////////////////////////////////
	// classPageCanonicalNameMethod //
	//////////////////////////////////

	/**	L'entité « classPageCanonicalNameMethod »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String classPageCanonicalNameMethod;
	@JsonIgnore
	public Wrap<String> classPageCanonicalNameMethodWrap = new Wrap<String>().p(this).c(String.class).var("classPageCanonicalNameMethod").o(classPageCanonicalNameMethod);

	/**	<br/>L'entité « classPageCanonicalNameMethod »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classPageCanonicalNameMethod">Trouver l'entité classPageCanonicalNameMethod dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classPageCanonicalNameMethod(Wrap<String> c);

	public String getClassPageCanonicalNameMethod() {
		return classPageCanonicalNameMethod;
	}

	public void setClassPageCanonicalNameMethod(String classPageCanonicalNameMethod) {
		this.classPageCanonicalNameMethod = classPageCanonicalNameMethod;
		this.classPageCanonicalNameMethodWrap.alreadyInitialized = true;
	}
	protected ApiWriter classPageCanonicalNameMethodInit() {
		if(!classPageCanonicalNameMethodWrap.alreadyInitialized) {
			_classPageCanonicalNameMethod(classPageCanonicalNameMethodWrap);
			if(classPageCanonicalNameMethod == null)
				setClassPageCanonicalNameMethod(classPageCanonicalNameMethodWrap.o);
		}
		classPageCanonicalNameMethodWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrClassPageCanonicalNameMethod() {
		return classPageCanonicalNameMethod;
	}

	public String strClassPageCanonicalNameMethod() {
		return classPageCanonicalNameMethod == null ? "" : classPageCanonicalNameMethod;
	}

	public String jsonClassPageCanonicalNameMethod() {
		return classPageCanonicalNameMethod == null ? "" : classPageCanonicalNameMethod;
	}

	public String nomAffichageClassPageCanonicalNameMethod() {
		return null;
	}

	public String htmTooltipClassPageCanonicalNameMethod() {
		return null;
	}

	public String htmClassPageCanonicalNameMethod() {
		return classPageCanonicalNameMethod == null ? "" : StringEscapeUtils.escapeHtml4(strClassPageCanonicalNameMethod());
	}

	////////////////////////
	// classKeywordsFound //
	////////////////////////

	/**	L'entité « classKeywordsFound »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classKeywordsFound;
	@JsonIgnore
	public Wrap<Boolean> classKeywordsFoundWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("classKeywordsFound").o(classKeywordsFound);

	/**	<br/>L'entité « classKeywordsFound »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classKeywordsFound">Trouver l'entité classKeywordsFound dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classKeywordsFound(Wrap<Boolean> c);

	public Boolean getClassKeywordsFound() {
		return classKeywordsFound;
	}

	public void setClassKeywordsFound(Boolean classKeywordsFound) {
		this.classKeywordsFound = classKeywordsFound;
		this.classKeywordsFoundWrap.alreadyInitialized = true;
	}
	public ApiWriter setClassKeywordsFound(String o) {
		this.classKeywordsFound = Boolean.parseBoolean(o);
		this.classKeywordsFoundWrap.alreadyInitialized = true;
		return (ApiWriter)this;
	}
	protected ApiWriter classKeywordsFoundInit() {
		if(!classKeywordsFoundWrap.alreadyInitialized) {
			_classKeywordsFound(classKeywordsFoundWrap);
			if(classKeywordsFound == null)
				setClassKeywordsFound(classKeywordsFoundWrap.o);
		}
		classKeywordsFoundWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public Boolean solrClassKeywordsFound() {
		return classKeywordsFound;
	}

	public String strClassKeywordsFound() {
		return classKeywordsFound == null ? "" : classKeywordsFound.toString();
	}

	public String jsonClassKeywordsFound() {
		return classKeywordsFound == null ? "" : classKeywordsFound.toString();
	}

	public String nomAffichageClassKeywordsFound() {
		return null;
	}

	public String htmTooltipClassKeywordsFound() {
		return null;
	}

	public String htmClassKeywordsFound() {
		return classKeywordsFound == null ? "" : StringEscapeUtils.escapeHtml4(strClassKeywordsFound());
	}

	///////////////////
	// classKeywords //
	///////////////////

	/**	L'entité « classKeywords »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> classKeywords;
	@JsonIgnore
	public Wrap<List<String>> classKeywordsWrap = new Wrap<List<String>>().p(this).c(List.class).var("classKeywords").o(classKeywords);

	/**	<br/>L'entité « classKeywords »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classKeywords">Trouver l'entité classKeywords dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classKeywords(Wrap<List<String>> c);

	public List<String> getClassKeywords() {
		return classKeywords;
	}

	public void setClassKeywords(List<String> classKeywords) {
		this.classKeywords = classKeywords;
		this.classKeywordsWrap.alreadyInitialized = true;
	}
	public ApiWriter addClassKeywords(String...objets) {
		for(String o : objets) {
			addClassKeywords(o);
		}
		return (ApiWriter)this;
	}
	public ApiWriter addClassKeywords(String o) {
		if(o != null && !classKeywords.contains(o))
			this.classKeywords.add(o);
		return (ApiWriter)this;
	}
	public ApiWriter setClassKeywords(JsonArray objets) {
		classKeywords.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClassKeywords(o);
		}
		return (ApiWriter)this;
	}
	protected ApiWriter classKeywordsInit() {
		if(!classKeywordsWrap.alreadyInitialized) {
			_classKeywords(classKeywordsWrap);
			if(classKeywords == null)
				setClassKeywords(classKeywordsWrap.o);
		}
		classKeywordsWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public List<String> solrClassKeywords() {
		return classKeywords;
	}

	public String strClassKeywords() {
		return classKeywords == null ? "" : classKeywords.toString();
	}

	public String jsonClassKeywords() {
		return classKeywords == null ? "" : classKeywords.toString();
	}

	public String nomAffichageClassKeywords() {
		return null;
	}

	public String htmTooltipClassKeywords() {
		return null;
	}

	public String htmClassKeywords() {
		return classKeywords == null ? "" : StringEscapeUtils.escapeHtml4(strClassKeywords());
	}

	/////////////////////
	// classPublicRead //
	/////////////////////

	/**	L'entité « classPublicRead »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classPublicRead;
	@JsonIgnore
	public Wrap<Boolean> classPublicReadWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("classPublicRead").o(classPublicRead);

	/**	<br/>L'entité « classPublicRead »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classPublicRead">Trouver l'entité classPublicRead dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classPublicRead(Wrap<Boolean> c);

	public Boolean getClassPublicRead() {
		return classPublicRead;
	}

	public void setClassPublicRead(Boolean classPublicRead) {
		this.classPublicRead = classPublicRead;
		this.classPublicReadWrap.alreadyInitialized = true;
	}
	public ApiWriter setClassPublicRead(String o) {
		this.classPublicRead = Boolean.parseBoolean(o);
		this.classPublicReadWrap.alreadyInitialized = true;
		return (ApiWriter)this;
	}
	protected ApiWriter classPublicReadInit() {
		if(!classPublicReadWrap.alreadyInitialized) {
			_classPublicRead(classPublicReadWrap);
			if(classPublicRead == null)
				setClassPublicRead(classPublicReadWrap.o);
		}
		classPublicReadWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public Boolean solrClassPublicRead() {
		return classPublicRead;
	}

	public String strClassPublicRead() {
		return classPublicRead == null ? "" : classPublicRead.toString();
	}

	public String jsonClassPublicRead() {
		return classPublicRead == null ? "" : classPublicRead.toString();
	}

	public String nomAffichageClassPublicRead() {
		return null;
	}

	public String htmTooltipClassPublicRead() {
		return null;
	}

	public String htmClassPublicRead() {
		return classPublicRead == null ? "" : StringEscapeUtils.escapeHtml4(strClassPublicRead());
	}

	//////////////////////
	// classRoleSession //
	//////////////////////

	/**	L'entité « classRoleSession »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classRoleSession;
	@JsonIgnore
	public Wrap<Boolean> classRoleSessionWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("classRoleSession").o(classRoleSession);

	/**	<br/>L'entité « classRoleSession »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classRoleSession">Trouver l'entité classRoleSession dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classRoleSession(Wrap<Boolean> c);

	public Boolean getClassRoleSession() {
		return classRoleSession;
	}

	public void setClassRoleSession(Boolean classRoleSession) {
		this.classRoleSession = classRoleSession;
		this.classRoleSessionWrap.alreadyInitialized = true;
	}
	public ApiWriter setClassRoleSession(String o) {
		this.classRoleSession = Boolean.parseBoolean(o);
		this.classRoleSessionWrap.alreadyInitialized = true;
		return (ApiWriter)this;
	}
	protected ApiWriter classRoleSessionInit() {
		if(!classRoleSessionWrap.alreadyInitialized) {
			_classRoleSession(classRoleSessionWrap);
			if(classRoleSession == null)
				setClassRoleSession(classRoleSessionWrap.o);
		}
		classRoleSessionWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public Boolean solrClassRoleSession() {
		return classRoleSession;
	}

	public String strClassRoleSession() {
		return classRoleSession == null ? "" : classRoleSession.toString();
	}

	public String jsonClassRoleSession() {
		return classRoleSession == null ? "" : classRoleSession.toString();
	}

	public String nomAffichageClassRoleSession() {
		return null;
	}

	public String htmTooltipClassRoleSession() {
		return null;
	}

	public String htmClassRoleSession() {
		return classRoleSession == null ? "" : StringEscapeUtils.escapeHtml4(strClassRoleSession());
	}

	//////////////////////////
	// classRoleUtilisateur //
	//////////////////////////

	/**	L'entité « classRoleUtilisateur »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classRoleUtilisateur;
	@JsonIgnore
	public Wrap<Boolean> classRoleUtilisateurWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("classRoleUtilisateur").o(classRoleUtilisateur);

	/**	<br/>L'entité « classRoleUtilisateur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classRoleUtilisateur">Trouver l'entité classRoleUtilisateur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classRoleUtilisateur(Wrap<Boolean> c);

	public Boolean getClassRoleUtilisateur() {
		return classRoleUtilisateur;
	}

	public void setClassRoleUtilisateur(Boolean classRoleUtilisateur) {
		this.classRoleUtilisateur = classRoleUtilisateur;
		this.classRoleUtilisateurWrap.alreadyInitialized = true;
	}
	public ApiWriter setClassRoleUtilisateur(String o) {
		this.classRoleUtilisateur = Boolean.parseBoolean(o);
		this.classRoleUtilisateurWrap.alreadyInitialized = true;
		return (ApiWriter)this;
	}
	protected ApiWriter classRoleUtilisateurInit() {
		if(!classRoleUtilisateurWrap.alreadyInitialized) {
			_classRoleUtilisateur(classRoleUtilisateurWrap);
			if(classRoleUtilisateur == null)
				setClassRoleUtilisateur(classRoleUtilisateurWrap.o);
		}
		classRoleUtilisateurWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public Boolean solrClassRoleUtilisateur() {
		return classRoleUtilisateur;
	}

	public String strClassRoleUtilisateur() {
		return classRoleUtilisateur == null ? "" : classRoleUtilisateur.toString();
	}

	public String jsonClassRoleUtilisateur() {
		return classRoleUtilisateur == null ? "" : classRoleUtilisateur.toString();
	}

	public String nomAffichageClassRoleUtilisateur() {
		return null;
	}

	public String htmTooltipClassRoleUtilisateur() {
		return null;
	}

	public String htmClassRoleUtilisateur() {
		return classRoleUtilisateur == null ? "" : StringEscapeUtils.escapeHtml4(strClassRoleUtilisateur());
	}

	/////////////////////
	// classRolesFound //
	/////////////////////

	/**	L'entité « classRolesFound »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean classRolesFound;
	@JsonIgnore
	public Wrap<Boolean> classRolesFoundWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("classRolesFound").o(classRolesFound);

	/**	<br/>L'entité « classRolesFound »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classRolesFound">Trouver l'entité classRolesFound dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classRolesFound(Wrap<Boolean> c);

	public Boolean getClassRolesFound() {
		return classRolesFound;
	}

	public void setClassRolesFound(Boolean classRolesFound) {
		this.classRolesFound = classRolesFound;
		this.classRolesFoundWrap.alreadyInitialized = true;
	}
	public ApiWriter setClassRolesFound(String o) {
		this.classRolesFound = Boolean.parseBoolean(o);
		this.classRolesFoundWrap.alreadyInitialized = true;
		return (ApiWriter)this;
	}
	protected ApiWriter classRolesFoundInit() {
		if(!classRolesFoundWrap.alreadyInitialized) {
			_classRolesFound(classRolesFoundWrap);
			if(classRolesFound == null)
				setClassRolesFound(classRolesFoundWrap.o);
		}
		classRolesFoundWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public Boolean solrClassRolesFound() {
		return classRolesFound;
	}

	public String strClassRolesFound() {
		return classRolesFound == null ? "" : classRolesFound.toString();
	}

	public String jsonClassRolesFound() {
		return classRolesFound == null ? "" : classRolesFound.toString();
	}

	public String nomAffichageClassRolesFound() {
		return null;
	}

	public String htmTooltipClassRolesFound() {
		return null;
	}

	public String htmClassRolesFound() {
		return classRolesFound == null ? "" : StringEscapeUtils.escapeHtml4(strClassRolesFound());
	}

	////////////////
	// classRoles //
	////////////////

	/**	L'entité « classRoles »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> classRoles;
	@JsonIgnore
	public Wrap<List<String>> classRolesWrap = new Wrap<List<String>>().p(this).c(List.class).var("classRoles").o(classRoles);

	/**	<br/>L'entité « classRoles »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classRoles">Trouver l'entité classRoles dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classRoles(Wrap<List<String>> c);

	public List<String> getClassRoles() {
		return classRoles;
	}

	public void setClassRoles(List<String> classRoles) {
		this.classRoles = classRoles;
		this.classRolesWrap.alreadyInitialized = true;
	}
	public ApiWriter addClassRoles(String...objets) {
		for(String o : objets) {
			addClassRoles(o);
		}
		return (ApiWriter)this;
	}
	public ApiWriter addClassRoles(String o) {
		if(o != null && !classRoles.contains(o))
			this.classRoles.add(o);
		return (ApiWriter)this;
	}
	public ApiWriter setClassRoles(JsonArray objets) {
		classRoles.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClassRoles(o);
		}
		return (ApiWriter)this;
	}
	protected ApiWriter classRolesInit() {
		if(!classRolesWrap.alreadyInitialized) {
			_classRoles(classRolesWrap);
			if(classRoles == null)
				setClassRoles(classRolesWrap.o);
		}
		classRolesWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public List<String> solrClassRoles() {
		return classRoles;
	}

	public String strClassRoles() {
		return classRoles == null ? "" : classRoles.toString();
	}

	public String jsonClassRoles() {
		return classRoles == null ? "" : classRoles.toString();
	}

	public String nomAffichageClassRoles() {
		return null;
	}

	public String htmTooltipClassRoles() {
		return null;
	}

	public String htmClassRoles() {
		return classRoles == null ? "" : StringEscapeUtils.escapeHtml4(strClassRoles());
	}

	////////////////////////
	// classRolesLanguage //
	////////////////////////

	/**	L'entité « classRolesLanguage »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<String> classRolesLanguage;
	@JsonIgnore
	public Wrap<List<String>> classRolesLanguageWrap = new Wrap<List<String>>().p(this).c(List.class).var("classRolesLanguage").o(classRolesLanguage);

	/**	<br/>L'entité « classRolesLanguage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:classRolesLanguage">Trouver l'entité classRolesLanguage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _classRolesLanguage(Wrap<List<String>> c);

	public List<String> getClassRolesLanguage() {
		return classRolesLanguage;
	}

	public void setClassRolesLanguage(List<String> classRolesLanguage) {
		this.classRolesLanguage = classRolesLanguage;
		this.classRolesLanguageWrap.alreadyInitialized = true;
	}
	public ApiWriter addClassRolesLanguage(String...objets) {
		for(String o : objets) {
			addClassRolesLanguage(o);
		}
		return (ApiWriter)this;
	}
	public ApiWriter addClassRolesLanguage(String o) {
		if(o != null && !classRolesLanguage.contains(o))
			this.classRolesLanguage.add(o);
		return (ApiWriter)this;
	}
	public ApiWriter setClassRolesLanguage(JsonArray objets) {
		classRolesLanguage.clear();
		for(int i = 0; i < objets.size(); i++) {
			String o = objets.getString(i);
			addClassRolesLanguage(o);
		}
		return (ApiWriter)this;
	}
	protected ApiWriter classRolesLanguageInit() {
		if(!classRolesLanguageWrap.alreadyInitialized) {
			_classRolesLanguage(classRolesLanguageWrap);
			if(classRolesLanguage == null)
				setClassRolesLanguage(classRolesLanguageWrap.o);
		}
		classRolesLanguageWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public List<String> solrClassRolesLanguage() {
		return classRolesLanguage;
	}

	public String strClassRolesLanguage() {
		return classRolesLanguage == null ? "" : classRolesLanguage.toString();
	}

	public String jsonClassRolesLanguage() {
		return classRolesLanguage == null ? "" : classRolesLanguage.toString();
	}

	public String nomAffichageClassRolesLanguage() {
		return null;
	}

	public String htmTooltipClassRolesLanguage() {
		return null;
	}

	public String htmClassRolesLanguage() {
		return classRolesLanguage == null ? "" : StringEscapeUtils.escapeHtml4(strClassRolesLanguage());
	}

	//////////////////
	// languageName //
	//////////////////

	/**	L'entité « languageName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String languageName;
	@JsonIgnore
	public Wrap<String> languageNameWrap = new Wrap<String>().p(this).c(String.class).var("languageName").o(languageName);

	/**	<br/>L'entité « languageName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:languageName">Trouver l'entité languageName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _languageName(Wrap<String> c);

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
		this.languageNameWrap.alreadyInitialized = true;
	}
	protected ApiWriter languageNameInit() {
		if(!languageNameWrap.alreadyInitialized) {
			_languageName(languageNameWrap);
			if(languageName == null)
				setLanguageName(languageNameWrap.o);
		}
		languageNameWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	public String solrLanguageName() {
		return languageName;
	}

	public String strLanguageName() {
		return languageName == null ? "" : languageName;
	}

	public String jsonLanguageName() {
		return languageName == null ? "" : languageName;
	}

	public String nomAffichageLanguageName() {
		return null;
	}

	public String htmTooltipLanguageName() {
		return null;
	}

	public String htmLanguageName() {
		return languageName == null ? "" : StringEscapeUtils.escapeHtml4(strLanguageName());
	}

	////////////////////////
	// entitySolrDocument //
	////////////////////////

	/**	L'entité « entitySolrDocument »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SolrDocument entitySolrDocument;
	@JsonIgnore
	public Wrap<SolrDocument> entitySolrDocumentWrap = new Wrap<SolrDocument>().p(this).c(SolrDocument.class).var("entitySolrDocument").o(entitySolrDocument);

	/**	<br/>L'entité « entitySolrDocument »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.writer.ApiWriter&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:entitySolrDocument">Trouver l'entité entitySolrDocument dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _entitySolrDocument(Wrap<SolrDocument> c);

	public SolrDocument getEntitySolrDocument() {
		return entitySolrDocument;
	}

	public void setEntitySolrDocument(SolrDocument entitySolrDocument) {
		this.entitySolrDocument = entitySolrDocument;
		this.entitySolrDocumentWrap.alreadyInitialized = true;
	}
	protected ApiWriter entitySolrDocumentInit() {
		if(!entitySolrDocumentWrap.alreadyInitialized) {
			_entitySolrDocument(entitySolrDocumentWrap);
			if(entitySolrDocument == null)
				setEntitySolrDocument(entitySolrDocumentWrap.o);
		}
		entitySolrDocumentWrap.alreadyInitialized(true);
		return (ApiWriter)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedApiWriter = false;

	public ApiWriter initDeepApiWriter(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedApiWriter) {
			alreadyInitializedApiWriter = true;
			initDeepApiWriter();
		}
		return (ApiWriter)this;
	}

	public void initDeepApiWriter() {
		initApiWriter();
	}

	public void initApiWriter() {
		siteRequest_Init();
		classSolrDocumentInit();
		contextRowsInit();
		classApiMethodInit();
		openApiVersionInit();
		appSwagger2Init();
		classUrisInit();
		openApiVersionNumberInit();
		tabsSchemaInit();
		tabsResponsesInit();
		wPathsInit();
		wRequestBodiesInit();
		wSchemasInit();
		siteContextInit();
		siteConfigInit();
		wRequestHeadersInit();
		wRequestDescriptionInit();
		wResponseDescriptionInit();
		wRequestBodyInit();
		wResponseBodyInit();
		wRequestSchemaInit();
		wResponseSchemaInit();
		writersInit();
		classApiTagInit();
		classExtendsBaseInit();
		classIsBaseInit();
		classSimpleNameInit();
		appNameInit();
		classAbsolutePathInit();
		classApiUriMethodInit();
		classApiMethodMethodInit();
		classApiMediaType200MethodInit();
		classApiOperationIdMethodInit();
		classApiOperationIdMethodRequestInit();
		classApiOperationIdMethodResponseInit();
		classSuperApiOperationIdMethodRequestInit();
		classSuperApiOperationIdMethodResponseInit();
		classPageCanonicalNameMethodInit();
		classKeywordsFoundInit();
		classKeywordsInit();
		classPublicReadInit();
		classRoleSessionInit();
		classRoleUtilisateurInit();
		classRolesFoundInit();
		classRolesInit();
		classRolesLanguageInit();
		languageNameInit();
		entitySolrDocumentInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepApiWriter(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestApiWriter(SiteRequestEnUS siteRequest_) {
		if(appSwagger2 != null)
			appSwagger2.setSiteRequest_(siteRequest_);
		if(wPaths != null)
			wPaths.setSiteRequest_(siteRequest_);
		if(wRequestBodies != null)
			wRequestBodies.setSiteRequest_(siteRequest_);
		if(wSchemas != null)
			wSchemas.setSiteRequest_(siteRequest_);
		if(wRequestHeaders != null)
			wRequestHeaders.setSiteRequest_(siteRequest_);
		if(wRequestDescription != null)
			wRequestDescription.setSiteRequest_(siteRequest_);
		if(wResponseDescription != null)
			wResponseDescription.setSiteRequest_(siteRequest_);
		if(wRequestBody != null)
			wRequestBody.setSiteRequest_(siteRequest_);
		if(wResponseBody != null)
			wResponseBody.setSiteRequest_(siteRequest_);
		if(wRequestSchema != null)
			wRequestSchema.setSiteRequest_(siteRequest_);
		if(wResponseSchema != null)
			wResponseSchema.setSiteRequest_(siteRequest_);
		if(writers != null)
			writers.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestApiWriter(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainApiWriter(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainApiWriter(String var) {
		ApiWriter oApiWriter = (ApiWriter)this;
		switch(var) {
			case "siteRequest_":
				return oApiWriter.siteRequest_;
			case "classSolrDocument":
				return oApiWriter.classSolrDocument;
			case "contextRows":
				return oApiWriter.contextRows;
			case "classApiMethod":
				return oApiWriter.classApiMethod;
			case "openApiVersion":
				return oApiWriter.openApiVersion;
			case "appSwagger2":
				return oApiWriter.appSwagger2;
			case "classUris":
				return oApiWriter.classUris;
			case "openApiVersionNumber":
				return oApiWriter.openApiVersionNumber;
			case "tabsSchema":
				return oApiWriter.tabsSchema;
			case "tabsResponses":
				return oApiWriter.tabsResponses;
			case "wPaths":
				return oApiWriter.wPaths;
			case "wRequestBodies":
				return oApiWriter.wRequestBodies;
			case "wSchemas":
				return oApiWriter.wSchemas;
			case "siteContext":
				return oApiWriter.siteContext;
			case "siteConfig":
				return oApiWriter.siteConfig;
			case "wRequestHeaders":
				return oApiWriter.wRequestHeaders;
			case "wRequestDescription":
				return oApiWriter.wRequestDescription;
			case "wResponseDescription":
				return oApiWriter.wResponseDescription;
			case "wRequestBody":
				return oApiWriter.wRequestBody;
			case "wResponseBody":
				return oApiWriter.wResponseBody;
			case "wRequestSchema":
				return oApiWriter.wRequestSchema;
			case "wResponseSchema":
				return oApiWriter.wResponseSchema;
			case "writers":
				return oApiWriter.writers;
			case "classApiTag":
				return oApiWriter.classApiTag;
			case "classExtendsBase":
				return oApiWriter.classExtendsBase;
			case "classIsBase":
				return oApiWriter.classIsBase;
			case "classSimpleName":
				return oApiWriter.classSimpleName;
			case "appName":
				return oApiWriter.appName;
			case "classAbsolutePath":
				return oApiWriter.classAbsolutePath;
			case "classApiUriMethod":
				return oApiWriter.classApiUriMethod;
			case "classApiMethodMethod":
				return oApiWriter.classApiMethodMethod;
			case "classApiMediaType200Method":
				return oApiWriter.classApiMediaType200Method;
			case "classApiOperationIdMethod":
				return oApiWriter.classApiOperationIdMethod;
			case "classApiOperationIdMethodRequest":
				return oApiWriter.classApiOperationIdMethodRequest;
			case "classApiOperationIdMethodResponse":
				return oApiWriter.classApiOperationIdMethodResponse;
			case "classSuperApiOperationIdMethodRequest":
				return oApiWriter.classSuperApiOperationIdMethodRequest;
			case "classSuperApiOperationIdMethodResponse":
				return oApiWriter.classSuperApiOperationIdMethodResponse;
			case "classPageCanonicalNameMethod":
				return oApiWriter.classPageCanonicalNameMethod;
			case "classKeywordsFound":
				return oApiWriter.classKeywordsFound;
			case "classKeywords":
				return oApiWriter.classKeywords;
			case "classPublicRead":
				return oApiWriter.classPublicRead;
			case "classRoleSession":
				return oApiWriter.classRoleSession;
			case "classRoleUtilisateur":
				return oApiWriter.classRoleUtilisateur;
			case "classRolesFound":
				return oApiWriter.classRolesFound;
			case "classRoles":
				return oApiWriter.classRoles;
			case "classRolesLanguage":
				return oApiWriter.classRolesLanguage;
			case "languageName":
				return oApiWriter.languageName;
			case "entitySolrDocument":
				return oApiWriter.entitySolrDocument;
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
				o = attributeApiWriter(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeApiWriter(String var, Object val) {
		ApiWriter oApiWriter = (ApiWriter)this;
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
					o = defineApiWriter(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineApiWriter(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestApiWriter() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ApiWriter) {
			ApiWriter original = (ApiWriter)o;
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
		if(!(o instanceof ApiWriter))
			return false;
		ApiWriter that = (ApiWriter)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ApiWriter { ");
		sb.append(" }");
		return sb.toString();
	}
}
