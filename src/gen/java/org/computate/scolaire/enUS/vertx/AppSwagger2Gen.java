package org.computate.scolaire.enUS.vertx;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import java.io.File;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.config.SiteConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class AppSwagger2Gen<DEV> extends Object {

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteRequest_">Trouver l'entité siteRequest_ dans Solr</a>
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
	protected AppSwagger2 siteRequest_Init() {
		if(!siteRequest_Wrap.alreadyInitialized) {
			_siteRequest_(siteRequest_Wrap);
			if(siteRequest_ == null)
				setSiteRequest_(siteRequest_Wrap.o);
		}
		siteRequest_Wrap.alreadyInitialized(true);
		return (AppSwagger2)this;
	}

	/////////////////
	// siteContext //
	/////////////////

	/**	L'entité « siteContext »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SiteContextEnUS(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SiteContextEnUS siteContext = new SiteContextEnUS();
	@JsonIgnore
	public Wrap<SiteContextEnUS> siteContextWrap = new Wrap<SiteContextEnUS>().p(this).c(SiteContextEnUS.class).var("siteContext").o(siteContext);

	/**	<br/>L'entité « siteContext »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SiteContextEnUS(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteContext">Trouver l'entité siteContext dans Solr</a>
	 * <br/>
	 * @param siteContext est l'entité déjà construit. 
	 **/
	protected abstract void _siteContext(SiteContextEnUS o);

	public SiteContextEnUS getSiteContext() {
		return siteContext;
	}

	public void setSiteContext(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
		this.siteContextWrap.alreadyInitialized = true;
	}
	protected AppSwagger2 siteContextInit() {
		if(!siteContextWrap.alreadyInitialized) {
			_siteContext(siteContext);
		}
		siteContext.initDeepForClass(siteRequest_);
		siteContextWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteConfig">Trouver l'entité siteConfig dans Solr</a>
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
	protected AppSwagger2 siteConfigInit() {
		if(!siteConfigWrap.alreadyInitialized) {
			_siteConfig(siteConfigWrap);
			if(siteConfig == null)
				setSiteConfig(siteConfigWrap.o);
		}
		if(siteConfig != null)
			siteConfig.initDeepForClass(siteRequest_);
		siteConfigWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
	}

	/////////////
	// appPath //
	/////////////

	/**	L'entité « appPath »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String appPath;
	@JsonIgnore
	public Wrap<String> appPathWrap = new Wrap<String>().p(this).c(String.class).var("appPath").o(appPath);

	/**	<br/>L'entité « appPath »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:appPath">Trouver l'entité appPath dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _appPath(Wrap<String> c);

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
		this.appPathWrap.alreadyInitialized = true;
	}
	protected AppSwagger2 appPathInit() {
		if(!appPathWrap.alreadyInitialized) {
			_appPath(appPathWrap);
			if(appPath == null)
				setAppPath(appPathWrap.o);
		}
		appPathWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
	}

	public String solrAppPath() {
		return appPath;
	}

	public String strAppPath() {
		return appPath == null ? "" : appPath;
	}

	public String jsonAppPath() {
		return appPath == null ? "" : appPath;
	}

	public String nomAffichageAppPath() {
		return null;
	}

	public String htmTooltipAppPath() {
		return null;
	}

	public String htmAppPath() {
		return appPath == null ? "" : StringEscapeUtils.escapeHtml4(strAppPath());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:openApiVersion">Trouver l'entité openApiVersion dans Solr</a>
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
	protected AppSwagger2 openApiVersionInit() {
		if(!openApiVersionWrap.alreadyInitialized) {
			_openApiVersion(openApiVersionWrap);
			if(openApiVersion == null)
				setOpenApiVersion(openApiVersionWrap.o);
		}
		openApiVersionWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:openApiVersionNumber">Trouver l'entité openApiVersionNumber dans Solr</a>
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
	public AppSwagger2 setOpenApiVersionNumber(String o) {
		if(NumberUtils.isParsable(o))
			this.openApiVersionNumber = Integer.parseInt(o);
		this.openApiVersionNumberWrap.alreadyInitialized = true;
		return (AppSwagger2)this;
	}
	protected AppSwagger2 openApiVersionNumberInit() {
		if(!openApiVersionNumberWrap.alreadyInitialized) {
			_openApiVersionNumber(openApiVersionNumberWrap);
			if(openApiVersionNumber == null)
				setOpenApiVersionNumber(openApiVersionNumberWrap.o);
		}
		openApiVersionNumberWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:tabsSchema">Trouver l'entité tabsSchema dans Solr</a>
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
	public AppSwagger2 setTabsSchema(String o) {
		if(NumberUtils.isParsable(o))
			this.tabsSchema = Integer.parseInt(o);
		this.tabsSchemaWrap.alreadyInitialized = true;
		return (AppSwagger2)this;
	}
	protected AppSwagger2 tabsSchemaInit() {
		if(!tabsSchemaWrap.alreadyInitialized) {
			_tabsSchema(tabsSchemaWrap);
			if(tabsSchema == null)
				setTabsSchema(tabsSchemaWrap.o);
		}
		tabsSchemaWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
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

	////////////////
	// apiVersion //
	////////////////

	/**	L'entité « apiVersion »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiVersion;
	@JsonIgnore
	public Wrap<String> apiVersionWrap = new Wrap<String>().p(this).c(String.class).var("apiVersion").o(apiVersion);

	/**	<br/>L'entité « apiVersion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiVersion">Trouver l'entité apiVersion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiVersion(Wrap<String> c);

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
		this.apiVersionWrap.alreadyInitialized = true;
	}
	protected AppSwagger2 apiVersionInit() {
		if(!apiVersionWrap.alreadyInitialized) {
			_apiVersion(apiVersionWrap);
			if(apiVersion == null)
				setApiVersion(apiVersionWrap.o);
		}
		apiVersionWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
	}

	public String solrApiVersion() {
		return apiVersion;
	}

	public String strApiVersion() {
		return apiVersion == null ? "" : apiVersion;
	}

	public String jsonApiVersion() {
		return apiVersion == null ? "" : apiVersion;
	}

	public String nomAffichageApiVersion() {
		return null;
	}

	public String htmTooltipApiVersion() {
		return null;
	}

	public String htmApiVersion() {
		return apiVersion == null ? "" : StringEscapeUtils.escapeHtml4(strApiVersion());
	}

	/////////////////////
	// openApiYamlPath //
	/////////////////////

	/**	L'entité « openApiYamlPath »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String openApiYamlPath;
	@JsonIgnore
	public Wrap<String> openApiYamlPathWrap = new Wrap<String>().p(this).c(String.class).var("openApiYamlPath").o(openApiYamlPath);

	/**	<br/>L'entité « openApiYamlPath »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:openApiYamlPath">Trouver l'entité openApiYamlPath dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiYamlPath(Wrap<String> c);

	public String getOpenApiYamlPath() {
		return openApiYamlPath;
	}

	public void setOpenApiYamlPath(String openApiYamlPath) {
		this.openApiYamlPath = openApiYamlPath;
		this.openApiYamlPathWrap.alreadyInitialized = true;
	}
	protected AppSwagger2 openApiYamlPathInit() {
		if(!openApiYamlPathWrap.alreadyInitialized) {
			_openApiYamlPath(openApiYamlPathWrap);
			if(openApiYamlPath == null)
				setOpenApiYamlPath(openApiYamlPathWrap.o);
		}
		openApiYamlPathWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
	}

	public String solrOpenApiYamlPath() {
		return openApiYamlPath;
	}

	public String strOpenApiYamlPath() {
		return openApiYamlPath == null ? "" : openApiYamlPath;
	}

	public String jsonOpenApiYamlPath() {
		return openApiYamlPath == null ? "" : openApiYamlPath;
	}

	public String nomAffichageOpenApiYamlPath() {
		return null;
	}

	public String htmTooltipOpenApiYamlPath() {
		return null;
	}

	public String htmOpenApiYamlPath() {
		return openApiYamlPath == null ? "" : StringEscapeUtils.escapeHtml4(strOpenApiYamlPath());
	}

	/////////////////////
	// openApiYamlFile //
	/////////////////////

	/**	L'entité « openApiYamlFile »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected File openApiYamlFile;
	@JsonIgnore
	public Wrap<File> openApiYamlFileWrap = new Wrap<File>().p(this).c(File.class).var("openApiYamlFile").o(openApiYamlFile);

	/**	<br/>L'entité « openApiYamlFile »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:openApiYamlFile">Trouver l'entité openApiYamlFile dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiYamlFile(Wrap<File> c);

	public File getOpenApiYamlFile() {
		return openApiYamlFile;
	}

	public void setOpenApiYamlFile(File openApiYamlFile) {
		this.openApiYamlFile = openApiYamlFile;
		this.openApiYamlFileWrap.alreadyInitialized = true;
	}
	protected AppSwagger2 openApiYamlFileInit() {
		if(!openApiYamlFileWrap.alreadyInitialized) {
			_openApiYamlFile(openApiYamlFileWrap);
			if(openApiYamlFile == null)
				setOpenApiYamlFile(openApiYamlFileWrap.o);
		}
		openApiYamlFileWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:w">Trouver l'entité w dans Solr</a>
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
	protected AppSwagger2 wInit() {
		if(!wWrap.alreadyInitialized) {
			_w(wWrap);
			if(w == null)
				setW(wWrap.o);
		}
		if(w != null)
			w.initDeepForClass(siteRequest_);
		wWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wPaths">Trouver l'entité wPaths dans Solr</a>
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
	protected AppSwagger2 wPathsInit() {
		if(!wPathsWrap.alreadyInitialized) {
			_wPaths(wPathsWrap);
			if(wPaths == null)
				setWPaths(wPathsWrap.o);
		}
		if(wPaths != null)
			wPaths.initDeepForClass(siteRequest_);
		wPathsWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wRequestBodies">Trouver l'entité wRequestBodies dans Solr</a>
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
	protected AppSwagger2 wRequestBodiesInit() {
		if(!wRequestBodiesWrap.alreadyInitialized) {
			_wRequestBodies(wRequestBodiesWrap);
			if(wRequestBodies == null)
				setWRequestBodies(wRequestBodiesWrap.o);
		}
		if(wRequestBodies != null)
			wRequestBodies.initDeepForClass(siteRequest_);
		wRequestBodiesWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.vertx.AppSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:wSchemas">Trouver l'entité wSchemas dans Solr</a>
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
	protected AppSwagger2 wSchemasInit() {
		if(!wSchemasWrap.alreadyInitialized) {
			_wSchemas(wSchemasWrap);
			if(wSchemas == null)
				setWSchemas(wSchemasWrap.o);
		}
		if(wSchemas != null)
			wSchemas.initDeepForClass(siteRequest_);
		wSchemasWrap.alreadyInitialized(true);
		return (AppSwagger2)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedAppSwagger2 = false;

	public AppSwagger2 initDeepAppSwagger2(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedAppSwagger2) {
			alreadyInitializedAppSwagger2 = true;
			initDeepAppSwagger2();
		}
		return (AppSwagger2)this;
	}

	public void initDeepAppSwagger2() {
		initAppSwagger2();
	}

	public void initAppSwagger2() {
		siteRequest_Init();
		siteContextInit();
		siteConfigInit();
		appPathInit();
		openApiVersionInit();
		openApiVersionNumberInit();
		tabsSchemaInit();
		apiVersionInit();
		openApiYamlPathInit();
		openApiYamlFileInit();
		wInit();
		wPathsInit();
		wRequestBodiesInit();
		wSchemasInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepAppSwagger2(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAppSwagger2(SiteRequestEnUS siteRequest_) {
		if(w != null)
			w.setSiteRequest_(siteRequest_);
		if(wPaths != null)
			wPaths.setSiteRequest_(siteRequest_);
		if(wRequestBodies != null)
			wRequestBodies.setSiteRequest_(siteRequest_);
		if(wSchemas != null)
			wSchemas.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestAppSwagger2(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAppSwagger2(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainAppSwagger2(String var) {
		AppSwagger2 oAppSwagger2 = (AppSwagger2)this;
		switch(var) {
			case "siteRequest_":
				return oAppSwagger2.siteRequest_;
			case "siteContext":
				return oAppSwagger2.siteContext;
			case "siteConfig":
				return oAppSwagger2.siteConfig;
			case "appPath":
				return oAppSwagger2.appPath;
			case "openApiVersion":
				return oAppSwagger2.openApiVersion;
			case "openApiVersionNumber":
				return oAppSwagger2.openApiVersionNumber;
			case "tabsSchema":
				return oAppSwagger2.tabsSchema;
			case "apiVersion":
				return oAppSwagger2.apiVersion;
			case "openApiYamlPath":
				return oAppSwagger2.openApiYamlPath;
			case "openApiYamlFile":
				return oAppSwagger2.openApiYamlFile;
			case "w":
				return oAppSwagger2.w;
			case "wPaths":
				return oAppSwagger2.wPaths;
			case "wRequestBodies":
				return oAppSwagger2.wRequestBodies;
			case "wSchemas":
				return oAppSwagger2.wSchemas;
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
				o = attributeAppSwagger2(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeAppSwagger2(String var, Object val) {
		AppSwagger2 oAppSwagger2 = (AppSwagger2)this;
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
					o = defineAppSwagger2(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineAppSwagger2(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestAppSwagger2() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof AppSwagger2) {
			AppSwagger2 original = (AppSwagger2)o;
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
		if(!(o instanceof AppSwagger2))
			return false;
		AppSwagger2 that = (AppSwagger2)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AppSwagger2 { ");
		sb.append(" }");
		return sb.toString();
	}
}
