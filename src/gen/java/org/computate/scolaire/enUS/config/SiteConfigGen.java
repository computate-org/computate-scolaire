package org.computate.scolaire.enUS.config;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
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
import org.apache.commons.configuration2.INIConfiguration;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 *	Loads the properties in the application config file into specific fields. 
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SiteConfigGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SiteConfig.class);

	////////////////
	// configPath //
	////////////////

	/**	L'entité « configPath »
The path to the config file of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String configPath;
	@JsonIgnore
	public Wrap<String> configPathWrap = new Wrap<String>().p(this).c(String.class).var("configPath").o(configPath);

	/**	<br/>L'entité « configPath »
The path to the config file of the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:configPath">Trouver l'entité configPath dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _configPath(Wrap<String> c);

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
		this.configPathWrap.alreadyInitialized = true;
	}
	protected SiteConfig configPathInit() {
		if(!configPathWrap.alreadyInitialized) {
			_configPath(configPathWrap);
			if(configPath == null)
				setConfigPath(configPathWrap.o);
		}
		configPathWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrConfigPath() {
		return configPath;
	}

	public String strConfigPath() {
		return configPath == null ? "" : configPath;
	}

	public String jsonConfigPath() {
		return configPath == null ? "" : configPath;
	}

	public String nomAffichageConfigPath() {
		return null;
	}

	public String htmTooltipConfigPath() {
		return null;
	}

	public String htmConfigPath() {
		return configPath == null ? "" : StringEscapeUtils.escapeHtml4(strConfigPath());
	}

	////////////
	// config //
	////////////

	/**	L'entité « config »
The INI Configuration Object for the config file. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected INIConfiguration config;
	@JsonIgnore
	public Wrap<INIConfiguration> configWrap = new Wrap<INIConfiguration>().p(this).c(INIConfiguration.class).var("config").o(config);

	/**	<br/>L'entité « config »
The INI Configuration Object for the config file. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:config">Trouver l'entité config dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _config(Wrap<INIConfiguration> c);

	public INIConfiguration getConfig() {
		return config;
	}

	public void setConfig(INIConfiguration config) {
		this.config = config;
		this.configWrap.alreadyInitialized = true;
	}
	protected SiteConfig configInit() {
		if(!configWrap.alreadyInitialized) {
			_config(configWrap);
			if(config == null)
				setConfig(configWrap.o);
		}
		configWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	////////////////////
	// siteIdentifier //
	////////////////////

	/**	L'entité « siteIdentifier »
The name of the principal group of settings of the config for this website. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteIdentifier;
	@JsonIgnore
	public Wrap<String> siteIdentifierWrap = new Wrap<String>().p(this).c(String.class).var("siteIdentifier").o(siteIdentifier);

	/**	<br/>L'entité « siteIdentifier »
The name of the principal group of settings of the config for this website. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteIdentifier">Trouver l'entité siteIdentifier dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteIdentifier(Wrap<String> c);

	public String getSiteIdentifier() {
		return siteIdentifier;
	}

	public void setSiteIdentifier(String siteIdentifier) {
		this.siteIdentifier = siteIdentifier;
		this.siteIdentifierWrap.alreadyInitialized = true;
	}
	protected SiteConfig siteIdentifierInit() {
		if(!siteIdentifierWrap.alreadyInitialized) {
			_siteIdentifier(siteIdentifierWrap);
			if(siteIdentifier == null)
				setSiteIdentifier(siteIdentifierWrap.o);
		}
		siteIdentifierWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSiteIdentifier() {
		return siteIdentifier;
	}

	public String strSiteIdentifier() {
		return siteIdentifier == null ? "" : siteIdentifier;
	}

	public String jsonSiteIdentifier() {
		return siteIdentifier == null ? "" : siteIdentifier;
	}

	public String nomAffichageSiteIdentifier() {
		return null;
	}

	public String htmTooltipSiteIdentifier() {
		return null;
	}

	public String htmSiteIdentifier() {
		return siteIdentifier == null ? "" : StringEscapeUtils.escapeHtml4(strSiteIdentifier());
	}

	///////////////////
	// prefixEscaped //
	///////////////////

	/**	L'entité « prefixEscaped »
The already escaped prefix to find the properties of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String prefixEscaped;
	@JsonIgnore
	public Wrap<String> prefixEscapedWrap = new Wrap<String>().p(this).c(String.class).var("prefixEscaped").o(prefixEscaped);

	/**	<br/>L'entité « prefixEscaped »
The already escaped prefix to find the properties of the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:prefixEscaped">Trouver l'entité prefixEscaped dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _prefixEscaped(Wrap<String> c);

	public String getPrefixEscaped() {
		return prefixEscaped;
	}

	public void setPrefixEscaped(String prefixEscaped) {
		this.prefixEscaped = prefixEscaped;
		this.prefixEscapedWrap.alreadyInitialized = true;
	}
	protected SiteConfig prefixEscapedInit() {
		if(!prefixEscapedWrap.alreadyInitialized) {
			_prefixEscaped(prefixEscapedWrap);
			if(prefixEscaped == null)
				setPrefixEscaped(prefixEscapedWrap.o);
		}
		prefixEscapedWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrPrefixEscaped() {
		return prefixEscaped;
	}

	public String strPrefixEscaped() {
		return prefixEscaped == null ? "" : prefixEscaped;
	}

	public String jsonPrefixEscaped() {
		return prefixEscaped == null ? "" : prefixEscaped;
	}

	public String nomAffichagePrefixEscaped() {
		return null;
	}

	public String htmTooltipPrefixEscaped() {
		return null;
	}

	public String htmPrefixEscaped() {
		return prefixEscaped == null ? "" : StringEscapeUtils.escapeHtml4(strPrefixEscaped());
	}

	/////////////
	// appPath //
	/////////////

	/**	L'entité « appPath »
The path to the project of the site cloned from git. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String appPath;
	@JsonIgnore
	public Wrap<String> appPathWrap = new Wrap<String>().p(this).c(String.class).var("appPath").o(appPath);

	/**	<br/>L'entité « appPath »
The path to the project of the site cloned from git. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:appPath">Trouver l'entité appPath dans Solr</a>
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
	protected SiteConfig appPathInit() {
		if(!appPathWrap.alreadyInitialized) {
			_appPath(appPathWrap);
			if(appPath == null)
				setAppPath(appPathWrap.o);
		}
		appPathWrap.alreadyInitialized(true);
		return (SiteConfig)this;
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

	/////////////
	// docRoot //
	/////////////

	/**	L'entité « docRoot »
The path to the docroot for the project. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String docRoot;
	@JsonIgnore
	public Wrap<String> docRootWrap = new Wrap<String>().p(this).c(String.class).var("docRoot").o(docRoot);

	/**	<br/>L'entité « docRoot »
The path to the docroot for the project. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:docRoot">Trouver l'entité docRoot dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _docRoot(Wrap<String> c);

	public String getDocRoot() {
		return docRoot;
	}

	public void setDocRoot(String docRoot) {
		this.docRoot = docRoot;
		this.docRootWrap.alreadyInitialized = true;
	}
	protected SiteConfig docRootInit() {
		if(!docRootWrap.alreadyInitialized) {
			_docRoot(docRootWrap);
			if(docRoot == null)
				setDocRoot(docRootWrap.o);
		}
		docRootWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrDocRoot() {
		return docRoot;
	}

	public String strDocRoot() {
		return docRoot == null ? "" : docRoot;
	}

	public String jsonDocRoot() {
		return docRoot == null ? "" : docRoot;
	}

	public String nomAffichageDocRoot() {
		return null;
	}

	public String htmTooltipDocRoot() {
		return null;
	}

	public String htmDocRoot() {
		return docRoot == null ? "" : StringEscapeUtils.escapeHtml4(strDocRoot());
	}

	/////////////////
	// companyName //
	/////////////////

	/**	L'entité « companyName »
The name of the company. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String companyName;
	@JsonIgnore
	public Wrap<String> companyNameWrap = new Wrap<String>().p(this).c(String.class).var("companyName").o(companyName);

	/**	<br/>L'entité « companyName »
The name of the company. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:companyName">Trouver l'entité companyName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _companyName(Wrap<String> c);

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
		this.companyNameWrap.alreadyInitialized = true;
	}
	protected SiteConfig companyNameInit() {
		if(!companyNameWrap.alreadyInitialized) {
			_companyName(companyNameWrap);
			if(companyName == null)
				setCompanyName(companyNameWrap.o);
		}
		companyNameWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrCompanyName() {
		return companyName;
	}

	public String strCompanyName() {
		return companyName == null ? "" : companyName;
	}

	public String jsonCompanyName() {
		return companyName == null ? "" : companyName;
	}

	public String nomAffichageCompanyName() {
		return null;
	}

	public String htmTooltipCompanyName() {
		return null;
	}

	public String htmCompanyName() {
		return companyName == null ? "" : StringEscapeUtils.escapeHtml4(strCompanyName());
	}

	////////////////
	// domainName //
	////////////////

	/**	L'entité « domainName »
The domain name of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String domainName;
	@JsonIgnore
	public Wrap<String> domainNameWrap = new Wrap<String>().p(this).c(String.class).var("domainName").o(domainName);

	/**	<br/>L'entité « domainName »
The domain name of the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:domainName">Trouver l'entité domainName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _domainName(Wrap<String> c);

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
		this.domainNameWrap.alreadyInitialized = true;
	}
	protected SiteConfig domainNameInit() {
		if(!domainNameWrap.alreadyInitialized) {
			_domainName(domainNameWrap);
			if(domainName == null)
				setDomainName(domainNameWrap.o);
		}
		domainNameWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrDomainName() {
		return domainName;
	}

	public String strDomainName() {
		return domainName == null ? "" : domainName;
	}

	public String jsonDomainName() {
		return domainName == null ? "" : domainName;
	}

	public String nomAffichageDomainName() {
		return null;
	}

	public String htmTooltipDomainName() {
		return null;
	}

	public String htmDomainName() {
		return domainName == null ? "" : StringEscapeUtils.escapeHtml4(strDomainName());
	}

	//////////////////
	// siteHostName //
	//////////////////

	/**	L'entité « siteHostName »
The host name of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteHostName;
	@JsonIgnore
	public Wrap<String> siteHostNameWrap = new Wrap<String>().p(this).c(String.class).var("siteHostName").o(siteHostName);

	/**	<br/>L'entité « siteHostName »
The host name of the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteHostName">Trouver l'entité siteHostName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteHostName(Wrap<String> c);

	public String getSiteHostName() {
		return siteHostName;
	}

	public void setSiteHostName(String siteHostName) {
		this.siteHostName = siteHostName;
		this.siteHostNameWrap.alreadyInitialized = true;
	}
	protected SiteConfig siteHostNameInit() {
		if(!siteHostNameWrap.alreadyInitialized) {
			_siteHostName(siteHostNameWrap);
			if(siteHostName == null)
				setSiteHostName(siteHostNameWrap.o);
		}
		siteHostNameWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSiteHostName() {
		return siteHostName;
	}

	public String strSiteHostName() {
		return siteHostName == null ? "" : siteHostName;
	}

	public String jsonSiteHostName() {
		return siteHostName == null ? "" : siteHostName;
	}

	public String nomAffichageSiteHostName() {
		return null;
	}

	public String htmTooltipSiteHostName() {
		return null;
	}

	public String htmSiteHostName() {
		return siteHostName == null ? "" : StringEscapeUtils.escapeHtml4(strSiteHostName());
	}

	//////////////
	// sitePort //
	//////////////

	/**	L'entité « sitePort »
The port of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer sitePort;
	@JsonIgnore
	public Wrap<Integer> sitePortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("sitePort").o(sitePort);

	/**	<br/>L'entité « sitePort »
The port of the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sitePort">Trouver l'entité sitePort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sitePort(Wrap<Integer> c);

	public Integer getSitePort() {
		return sitePort;
	}

	public void setSitePort(Integer sitePort) {
		this.sitePort = sitePort;
		this.sitePortWrap.alreadyInitialized = true;
	}
	public SiteConfig setSitePort(String o) {
		if(NumberUtils.isParsable(o))
			this.sitePort = Integer.parseInt(o);
		this.sitePortWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig sitePortInit() {
		if(!sitePortWrap.alreadyInitialized) {
			_sitePort(sitePortWrap);
			if(sitePort == null)
				setSitePort(sitePortWrap.o);
		}
		sitePortWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrSitePort() {
		return sitePort;
	}

	public String strSitePort() {
		return sitePort == null ? "" : sitePort.toString();
	}

	public String jsonSitePort() {
		return sitePort == null ? "" : sitePort.toString();
	}

	public String nomAffichageSitePort() {
		return null;
	}

	public String htmTooltipSitePort() {
		return null;
	}

	public String htmSitePort() {
		return sitePort == null ? "" : StringEscapeUtils.escapeHtml4(strSitePort());
	}

	///////////////
	// authRealm //
	///////////////

	/**	L'entité « authRealm »
The Keycloak realm of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authRealm;
	@JsonIgnore
	public Wrap<String> authRealmWrap = new Wrap<String>().p(this).c(String.class).var("authRealm").o(authRealm);

	/**	<br/>L'entité « authRealm »
The Keycloak realm of the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authRealm">Trouver l'entité authRealm dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authRealm(Wrap<String> c);

	public String getAuthRealm() {
		return authRealm;
	}

	public void setAuthRealm(String authRealm) {
		this.authRealm = authRealm;
		this.authRealmWrap.alreadyInitialized = true;
	}
	protected SiteConfig authRealmInit() {
		if(!authRealmWrap.alreadyInitialized) {
			_authRealm(authRealmWrap);
			if(authRealm == null)
				setAuthRealm(authRealmWrap.o);
		}
		authRealmWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthRealm() {
		return authRealm;
	}

	public String strAuthRealm() {
		return authRealm == null ? "" : authRealm;
	}

	public String jsonAuthRealm() {
		return authRealm == null ? "" : authRealm;
	}

	public String nomAffichageAuthRealm() {
		return null;
	}

	public String htmTooltipAuthRealm() {
		return null;
	}

	public String htmAuthRealm() {
		return authRealm == null ? "" : StringEscapeUtils.escapeHtml4(strAuthRealm());
	}

	//////////////////
	// authResource //
	//////////////////

	/**	L'entité « authResource »
The Keycloak client ID of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authResource;
	@JsonIgnore
	public Wrap<String> authResourceWrap = new Wrap<String>().p(this).c(String.class).var("authResource").o(authResource);

	/**	<br/>L'entité « authResource »
The Keycloak client ID of the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authResource">Trouver l'entité authResource dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authResource(Wrap<String> c);

	public String getAuthResource() {
		return authResource;
	}

	public void setAuthResource(String authResource) {
		this.authResource = authResource;
		this.authResourceWrap.alreadyInitialized = true;
	}
	protected SiteConfig authResourceInit() {
		if(!authResourceWrap.alreadyInitialized) {
			_authResource(authResourceWrap);
			if(authResource == null)
				setAuthResource(authResourceWrap.o);
		}
		authResourceWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthResource() {
		return authResource;
	}

	public String strAuthResource() {
		return authResource == null ? "" : authResource;
	}

	public String jsonAuthResource() {
		return authResource == null ? "" : authResource;
	}

	public String nomAffichageAuthResource() {
		return null;
	}

	public String htmTooltipAuthResource() {
		return null;
	}

	public String htmAuthResource() {
		return authResource == null ? "" : StringEscapeUtils.escapeHtml4(strAuthResource());
	}

	////////////////
	// authSecret //
	////////////////

	/**	L'entité « authSecret »
The Keycloak client secret of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authSecret;
	@JsonIgnore
	public Wrap<String> authSecretWrap = new Wrap<String>().p(this).c(String.class).var("authSecret").o(authSecret);

	/**	<br/>L'entité « authSecret »
The Keycloak client secret of the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authSecret">Trouver l'entité authSecret dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authSecret(Wrap<String> c);

	public String getAuthSecret() {
		return authSecret;
	}

	public void setAuthSecret(String authSecret) {
		this.authSecret = authSecret;
		this.authSecretWrap.alreadyInitialized = true;
	}
	protected SiteConfig authSecretInit() {
		if(!authSecretWrap.alreadyInitialized) {
			_authSecret(authSecretWrap);
			if(authSecret == null)
				setAuthSecret(authSecretWrap.o);
		}
		authSecretWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthSecret() {
		return authSecret;
	}

	public String strAuthSecret() {
		return authSecret == null ? "" : authSecret;
	}

	public String jsonAuthSecret() {
		return authSecret == null ? "" : authSecret;
	}

	public String nomAffichageAuthSecret() {
		return null;
	}

	public String htmTooltipAuthSecret() {
		return null;
	}

	public String htmAuthSecret() {
		return authSecret == null ? "" : StringEscapeUtils.escapeHtml4(strAuthSecret());
	}

	/////////////////////
	// authSslRequired //
	/////////////////////

	/**	L'entité « authSslRequired »
Whether SSL is required in Keycloak for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authSslRequired;
	@JsonIgnore
	public Wrap<String> authSslRequiredWrap = new Wrap<String>().p(this).c(String.class).var("authSslRequired").o(authSslRequired);

	/**	<br/>L'entité « authSslRequired »
Whether SSL is required in Keycloak for the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authSslRequired">Trouver l'entité authSslRequired dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authSslRequired(Wrap<String> c);

	public String getAuthSslRequired() {
		return authSslRequired;
	}

	public void setAuthSslRequired(String authSslRequired) {
		this.authSslRequired = authSslRequired;
		this.authSslRequiredWrap.alreadyInitialized = true;
	}
	protected SiteConfig authSslRequiredInit() {
		if(!authSslRequiredWrap.alreadyInitialized) {
			_authSslRequired(authSslRequiredWrap);
			if(authSslRequired == null)
				setAuthSslRequired(authSslRequiredWrap.o);
		}
		authSslRequiredWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthSslRequired() {
		return authSslRequired;
	}

	public String strAuthSslRequired() {
		return authSslRequired == null ? "" : authSslRequired;
	}

	public String jsonAuthSslRequired() {
		return authSslRequired == null ? "" : authSslRequired;
	}

	public String nomAffichageAuthSslRequired() {
		return null;
	}

	public String htmTooltipAuthSslRequired() {
		return null;
	}

	public String htmAuthSslRequired() {
		return authSslRequired == null ? "" : StringEscapeUtils.escapeHtml4(strAuthSslRequired());
	}

	////////////////
	// sslJksPath //
	////////////////

	/**	L'entité « sslJksPath »
The path to the Java keystore for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sslJksPath;
	@JsonIgnore
	public Wrap<String> sslJksPathWrap = new Wrap<String>().p(this).c(String.class).var("sslJksPath").o(sslJksPath);

	/**	<br/>L'entité « sslJksPath »
The path to the Java keystore for the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sslJksPath">Trouver l'entité sslJksPath dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sslJksPath(Wrap<String> c);

	public String getSslJksPath() {
		return sslJksPath;
	}

	public void setSslJksPath(String sslJksPath) {
		this.sslJksPath = sslJksPath;
		this.sslJksPathWrap.alreadyInitialized = true;
	}
	protected SiteConfig sslJksPathInit() {
		if(!sslJksPathWrap.alreadyInitialized) {
			_sslJksPath(sslJksPathWrap);
			if(sslJksPath == null)
				setSslJksPath(sslJksPathWrap.o);
		}
		sslJksPathWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSslJksPath() {
		return sslJksPath;
	}

	public String strSslJksPath() {
		return sslJksPath == null ? "" : sslJksPath;
	}

	public String jsonSslJksPath() {
		return sslJksPath == null ? "" : sslJksPath;
	}

	public String nomAffichageSslJksPath() {
		return null;
	}

	public String htmTooltipSslJksPath() {
		return null;
	}

	public String htmSslJksPath() {
		return sslJksPath == null ? "" : StringEscapeUtils.escapeHtml4(strSslJksPath());
	}

	////////////////////
	// sslJksPassword //
	////////////////////

	/**	L'entité « sslJksPassword »
The password for the Java keystore for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sslJksPassword;
	@JsonIgnore
	public Wrap<String> sslJksPasswordWrap = new Wrap<String>().p(this).c(String.class).var("sslJksPassword").o(sslJksPassword);

	/**	<br/>L'entité « sslJksPassword »
The password for the Java keystore for the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sslJksPassword">Trouver l'entité sslJksPassword dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sslJksPassword(Wrap<String> c);

	public String getSslJksPassword() {
		return sslJksPassword;
	}

	public void setSslJksPassword(String sslJksPassword) {
		this.sslJksPassword = sslJksPassword;
		this.sslJksPasswordWrap.alreadyInitialized = true;
	}
	protected SiteConfig sslJksPasswordInit() {
		if(!sslJksPasswordWrap.alreadyInitialized) {
			_sslJksPassword(sslJksPasswordWrap);
			if(sslJksPassword == null)
				setSslJksPassword(sslJksPasswordWrap.o);
		}
		sslJksPasswordWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSslJksPassword() {
		return sslJksPassword;
	}

	public String strSslJksPassword() {
		return sslJksPassword == null ? "" : sslJksPassword;
	}

	public String jsonSslJksPassword() {
		return sslJksPassword == null ? "" : sslJksPassword;
	}

	public String nomAffichageSslJksPassword() {
		return null;
	}

	public String htmTooltipSslJksPassword() {
		return null;
	}

	public String htmSslJksPassword() {
		return sslJksPassword == null ? "" : StringEscapeUtils.escapeHtml4(strSslJksPassword());
	}

	/////////////
	// authUrl //
	/////////////

	/**	L'entité « authUrl »
The URL to the Keycloak server. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authUrl;
	@JsonIgnore
	public Wrap<String> authUrlWrap = new Wrap<String>().p(this).c(String.class).var("authUrl").o(authUrl);

	/**	<br/>L'entité « authUrl »
The URL to the Keycloak server. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authUrl">Trouver l'entité authUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authUrl(Wrap<String> c);

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
		this.authUrlWrap.alreadyInitialized = true;
	}
	protected SiteConfig authUrlInit() {
		if(!authUrlWrap.alreadyInitialized) {
			_authUrl(authUrlWrap);
			if(authUrl == null)
				setAuthUrl(authUrlWrap.o);
		}
		authUrlWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthUrl() {
		return authUrl;
	}

	public String strAuthUrl() {
		return authUrl == null ? "" : authUrl;
	}

	public String jsonAuthUrl() {
		return authUrl == null ? "" : authUrl;
	}

	public String nomAffichageAuthUrl() {
		return null;
	}

	public String htmTooltipAuthUrl() {
		return null;
	}

	public String htmAuthUrl() {
		return authUrl == null ? "" : StringEscapeUtils.escapeHtml4(strAuthUrl());
	}

	////////////////////
	// encryptionSalt //
	////////////////////

	/**	L'entité « encryptionSalt »
The encryption salt to use for all database encryption. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String encryptionSalt;
	@JsonIgnore
	public Wrap<String> encryptionSaltWrap = new Wrap<String>().p(this).c(String.class).var("encryptionSalt").o(encryptionSalt);

	/**	<br/>L'entité « encryptionSalt »
The encryption salt to use for all database encryption. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:encryptionSalt">Trouver l'entité encryptionSalt dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _encryptionSalt(Wrap<String> c);

	public String getEncryptionSalt() {
		return encryptionSalt;
	}

	public void setEncryptionSalt(String encryptionSalt) {
		this.encryptionSalt = encryptionSalt;
		this.encryptionSaltWrap.alreadyInitialized = true;
	}
	protected SiteConfig encryptionSaltInit() {
		if(!encryptionSaltWrap.alreadyInitialized) {
			_encryptionSalt(encryptionSaltWrap);
			if(encryptionSalt == null)
				setEncryptionSalt(encryptionSaltWrap.o);
		}
		encryptionSaltWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrEncryptionSalt() {
		return encryptionSalt;
	}

	public String strEncryptionSalt() {
		return encryptionSalt == null ? "" : encryptionSalt;
	}

	public String jsonEncryptionSalt() {
		return encryptionSalt == null ? "" : encryptionSalt;
	}

	public String nomAffichageEncryptionSalt() {
		return null;
	}

	public String htmTooltipEncryptionSalt() {
		return null;
	}

	public String htmEncryptionSalt() {
		return encryptionSalt == null ? "" : StringEscapeUtils.escapeHtml4(strEncryptionSalt());
	}

	////////////////////////
	// encryptionPassword //
	////////////////////////

	/**	L'entité « encryptionPassword »
The encryption password to use for all encryption of the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String encryptionPassword;
	@JsonIgnore
	public Wrap<String> encryptionPasswordWrap = new Wrap<String>().p(this).c(String.class).var("encryptionPassword").o(encryptionPassword);

	/**	<br/>L'entité « encryptionPassword »
The encryption password to use for all encryption of the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:encryptionPassword">Trouver l'entité encryptionPassword dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _encryptionPassword(Wrap<String> c);

	public String getEncryptionPassword() {
		return encryptionPassword;
	}

	public void setEncryptionPassword(String encryptionPassword) {
		this.encryptionPassword = encryptionPassword;
		this.encryptionPasswordWrap.alreadyInitialized = true;
	}
	protected SiteConfig encryptionPasswordInit() {
		if(!encryptionPasswordWrap.alreadyInitialized) {
			_encryptionPassword(encryptionPasswordWrap);
			if(encryptionPassword == null)
				setEncryptionPassword(encryptionPasswordWrap.o);
		}
		encryptionPasswordWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrEncryptionPassword() {
		return encryptionPassword;
	}

	public String strEncryptionPassword() {
		return encryptionPassword == null ? "" : encryptionPassword;
	}

	public String jsonEncryptionPassword() {
		return encryptionPassword == null ? "" : encryptionPassword;
	}

	public String nomAffichageEncryptionPassword() {
		return null;
	}

	public String htmTooltipEncryptionPassword() {
		return null;
	}

	public String htmEncryptionPassword() {
		return encryptionPassword == null ? "" : StringEscapeUtils.escapeHtml4(strEncryptionPassword());
	}

	/////////////////
	// siteBaseUrl //
	/////////////////

	/**	L'entité « siteBaseUrl »
The base URL for the URLs of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteBaseUrl;
	@JsonIgnore
	public Wrap<String> siteBaseUrlWrap = new Wrap<String>().p(this).c(String.class).var("siteBaseUrl").o(siteBaseUrl);

	/**	<br/>L'entité « siteBaseUrl »
The base URL for the URLs of the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteBaseUrl">Trouver l'entité siteBaseUrl dans Solr</a>
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
	protected SiteConfig siteBaseUrlInit() {
		if(!siteBaseUrlWrap.alreadyInitialized) {
			_siteBaseUrl(siteBaseUrlWrap);
			if(siteBaseUrl == null)
				setSiteBaseUrl(siteBaseUrlWrap.o);
		}
		siteBaseUrlWrap.alreadyInitialized(true);
		return (SiteConfig)this;
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

	/////////////////////
	// siteDisplayName //
	/////////////////////

	/**	L'entité « siteDisplayName »
The display name of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteDisplayName;
	@JsonIgnore
	public Wrap<String> siteDisplayNameWrap = new Wrap<String>().p(this).c(String.class).var("siteDisplayName").o(siteDisplayName);

	/**	<br/>L'entité « siteDisplayName »
The display name of the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteDisplayName">Trouver l'entité siteDisplayName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteDisplayName(Wrap<String> c);

	public String getSiteDisplayName() {
		return siteDisplayName;
	}

	public void setSiteDisplayName(String siteDisplayName) {
		this.siteDisplayName = siteDisplayName;
		this.siteDisplayNameWrap.alreadyInitialized = true;
	}
	protected SiteConfig siteDisplayNameInit() {
		if(!siteDisplayNameWrap.alreadyInitialized) {
			_siteDisplayName(siteDisplayNameWrap);
			if(siteDisplayName == null)
				setSiteDisplayName(siteDisplayNameWrap.o);
		}
		siteDisplayNameWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSiteDisplayName() {
		return siteDisplayName;
	}

	public String strSiteDisplayName() {
		return siteDisplayName == null ? "" : siteDisplayName;
	}

	public String jsonSiteDisplayName() {
		return siteDisplayName == null ? "" : siteDisplayName;
	}

	public String nomAffichageSiteDisplayName() {
		return null;
	}

	public String htmTooltipSiteDisplayName() {
		return null;
	}

	public String htmSiteDisplayName() {
		return siteDisplayName == null ? "" : StringEscapeUtils.escapeHtml4(strSiteDisplayName());
	}

	/////////////////////
	// jdbcDriverClass //
	/////////////////////

	/**	L'entité « jdbcDriverClass »
The class name of the JDBC driver class for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcDriverClass;
	@JsonIgnore
	public Wrap<String> jdbcDriverClassWrap = new Wrap<String>().p(this).c(String.class).var("jdbcDriverClass").o(jdbcDriverClass);

	/**	<br/>L'entité « jdbcDriverClass »
The class name of the JDBC driver class for the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcDriverClass">Trouver l'entité jdbcDriverClass dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcDriverClass(Wrap<String> c);

	public String getJdbcDriverClass() {
		return jdbcDriverClass;
	}

	public void setJdbcDriverClass(String jdbcDriverClass) {
		this.jdbcDriverClass = jdbcDriverClass;
		this.jdbcDriverClassWrap.alreadyInitialized = true;
	}
	protected SiteConfig jdbcDriverClassInit() {
		if(!jdbcDriverClassWrap.alreadyInitialized) {
			_jdbcDriverClass(jdbcDriverClassWrap);
			if(jdbcDriverClass == null)
				setJdbcDriverClass(jdbcDriverClassWrap.o);
		}
		jdbcDriverClassWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrJdbcDriverClass() {
		return jdbcDriverClass;
	}

	public String strJdbcDriverClass() {
		return jdbcDriverClass == null ? "" : jdbcDriverClass;
	}

	public String jsonJdbcDriverClass() {
		return jdbcDriverClass == null ? "" : jdbcDriverClass;
	}

	public String nomAffichageJdbcDriverClass() {
		return null;
	}

	public String htmTooltipJdbcDriverClass() {
		return null;
	}

	public String htmJdbcDriverClass() {
		return jdbcDriverClass == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcDriverClass());
	}

	//////////////////
	// jdbcUsername //
	//////////////////

	/**	L'entité « jdbcUsername »
The username for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcUsername;
	@JsonIgnore
	public Wrap<String> jdbcUsernameWrap = new Wrap<String>().p(this).c(String.class).var("jdbcUsername").o(jdbcUsername);

	/**	<br/>L'entité « jdbcUsername »
The username for the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcUsername">Trouver l'entité jdbcUsername dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcUsername(Wrap<String> c);

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
		this.jdbcUsernameWrap.alreadyInitialized = true;
	}
	protected SiteConfig jdbcUsernameInit() {
		if(!jdbcUsernameWrap.alreadyInitialized) {
			_jdbcUsername(jdbcUsernameWrap);
			if(jdbcUsername == null)
				setJdbcUsername(jdbcUsernameWrap.o);
		}
		jdbcUsernameWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrJdbcUsername() {
		return jdbcUsername;
	}

	public String strJdbcUsername() {
		return jdbcUsername == null ? "" : jdbcUsername;
	}

	public String jsonJdbcUsername() {
		return jdbcUsername == null ? "" : jdbcUsername;
	}

	public String nomAffichageJdbcUsername() {
		return null;
	}

	public String htmTooltipJdbcUsername() {
		return null;
	}

	public String htmJdbcUsername() {
		return jdbcUsername == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcUsername());
	}

	//////////////////
	// jdbcPassword //
	//////////////////

	/**	L'entité « jdbcPassword »
The password for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcPassword;
	@JsonIgnore
	public Wrap<String> jdbcPasswordWrap = new Wrap<String>().p(this).c(String.class).var("jdbcPassword").o(jdbcPassword);

	/**	<br/>L'entité « jdbcPassword »
The password for the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcPassword">Trouver l'entité jdbcPassword dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcPassword(Wrap<String> c);

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
		this.jdbcPasswordWrap.alreadyInitialized = true;
	}
	protected SiteConfig jdbcPasswordInit() {
		if(!jdbcPasswordWrap.alreadyInitialized) {
			_jdbcPassword(jdbcPasswordWrap);
			if(jdbcPassword == null)
				setJdbcPassword(jdbcPasswordWrap.o);
		}
		jdbcPasswordWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrJdbcPassword() {
		return jdbcPassword;
	}

	public String strJdbcPassword() {
		return jdbcPassword == null ? "" : jdbcPassword;
	}

	public String jsonJdbcPassword() {
		return jdbcPassword == null ? "" : jdbcPassword;
	}

	public String nomAffichageJdbcPassword() {
		return null;
	}

	public String htmTooltipJdbcPassword() {
		return null;
	}

	public String htmJdbcPassword() {
		return jdbcPassword == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcPassword());
	}

	/////////////////////
	// jdbcMaxPoolSize //
	/////////////////////

	/**	L'entité « jdbcMaxPoolSize »
The max pool size for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxPoolSize;
	@JsonIgnore
	public Wrap<Integer> jdbcMaxPoolSizeWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMaxPoolSize").o(jdbcMaxPoolSize);

	/**	<br/>L'entité « jdbcMaxPoolSize »
The max pool size for the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxPoolSize">Trouver l'entité jdbcMaxPoolSize dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMaxPoolSize(Wrap<Integer> c);

	public Integer getJdbcMaxPoolSize() {
		return jdbcMaxPoolSize;
	}

	public void setJdbcMaxPoolSize(Integer jdbcMaxPoolSize) {
		this.jdbcMaxPoolSize = jdbcMaxPoolSize;
		this.jdbcMaxPoolSizeWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcMaxPoolSize(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcMaxPoolSize = Integer.parseInt(o);
		this.jdbcMaxPoolSizeWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcMaxPoolSizeInit() {
		if(!jdbcMaxPoolSizeWrap.alreadyInitialized) {
			_jdbcMaxPoolSize(jdbcMaxPoolSizeWrap);
			if(jdbcMaxPoolSize == null)
				setJdbcMaxPoolSize(jdbcMaxPoolSizeWrap.o);
		}
		jdbcMaxPoolSizeWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcMaxPoolSize() {
		return jdbcMaxPoolSize;
	}

	public String strJdbcMaxPoolSize() {
		return jdbcMaxPoolSize == null ? "" : jdbcMaxPoolSize.toString();
	}

	public String jsonJdbcMaxPoolSize() {
		return jdbcMaxPoolSize == null ? "" : jdbcMaxPoolSize.toString();
	}

	public String nomAffichageJdbcMaxPoolSize() {
		return null;
	}

	public String htmTooltipJdbcMaxPoolSize() {
		return null;
	}

	public String htmJdbcMaxPoolSize() {
		return jdbcMaxPoolSize == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcMaxPoolSize());
	}

	//////////////////////////
	// jdbcMaxWaitQueueSize //
	//////////////////////////

	/**	L'entité « jdbcMaxWaitQueueSize »
Set the maximum connection request allowed in the wait queue, 
	 *	any requests beyond the max size will result in an failure. 
	 *	If the value is set to a negative number then the queue will be unbounded. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxWaitQueueSize;
	@JsonIgnore
	public Wrap<Integer> jdbcMaxWaitQueueSizeWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMaxWaitQueueSize").o(jdbcMaxWaitQueueSize);

	/**	<br/>L'entité « jdbcMaxWaitQueueSize »
Set the maximum connection request allowed in the wait queue, 
	 *	any requests beyond the max size will result in an failure. 
	 *	If the value is set to a negative number then the queue will be unbounded. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxWaitQueueSize">Trouver l'entité jdbcMaxWaitQueueSize dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMaxWaitQueueSize(Wrap<Integer> c);

	public Integer getJdbcMaxWaitQueueSize() {
		return jdbcMaxWaitQueueSize;
	}

	public void setJdbcMaxWaitQueueSize(Integer jdbcMaxWaitQueueSize) {
		this.jdbcMaxWaitQueueSize = jdbcMaxWaitQueueSize;
		this.jdbcMaxWaitQueueSizeWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcMaxWaitQueueSize(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcMaxWaitQueueSize = Integer.parseInt(o);
		this.jdbcMaxWaitQueueSizeWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcMaxWaitQueueSizeInit() {
		if(!jdbcMaxWaitQueueSizeWrap.alreadyInitialized) {
			_jdbcMaxWaitQueueSize(jdbcMaxWaitQueueSizeWrap);
			if(jdbcMaxWaitQueueSize == null)
				setJdbcMaxWaitQueueSize(jdbcMaxWaitQueueSizeWrap.o);
		}
		jdbcMaxWaitQueueSizeWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcMaxWaitQueueSize() {
		return jdbcMaxWaitQueueSize;
	}

	public String strJdbcMaxWaitQueueSize() {
		return jdbcMaxWaitQueueSize == null ? "" : jdbcMaxWaitQueueSize.toString();
	}

	public String jsonJdbcMaxWaitQueueSize() {
		return jdbcMaxWaitQueueSize == null ? "" : jdbcMaxWaitQueueSize.toString();
	}

	public String nomAffichageJdbcMaxWaitQueueSize() {
		return null;
	}

	public String htmTooltipJdbcMaxWaitQueueSize() {
		return null;
	}

	public String htmJdbcMaxWaitQueueSize() {
		return jdbcMaxWaitQueueSize == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcMaxWaitQueueSize());
	}

	/////////////////////
	// jdbcMinPoolSize //
	/////////////////////

	/**	L'entité « jdbcMinPoolSize »
The max pool size for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMinPoolSize;
	@JsonIgnore
	public Wrap<Integer> jdbcMinPoolSizeWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMinPoolSize").o(jdbcMinPoolSize);

	/**	<br/>L'entité « jdbcMinPoolSize »
The max pool size for the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMinPoolSize">Trouver l'entité jdbcMinPoolSize dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMinPoolSize(Wrap<Integer> c);

	public Integer getJdbcMinPoolSize() {
		return jdbcMinPoolSize;
	}

	public void setJdbcMinPoolSize(Integer jdbcMinPoolSize) {
		this.jdbcMinPoolSize = jdbcMinPoolSize;
		this.jdbcMinPoolSizeWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcMinPoolSize(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcMinPoolSize = Integer.parseInt(o);
		this.jdbcMinPoolSizeWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcMinPoolSizeInit() {
		if(!jdbcMinPoolSizeWrap.alreadyInitialized) {
			_jdbcMinPoolSize(jdbcMinPoolSizeWrap);
			if(jdbcMinPoolSize == null)
				setJdbcMinPoolSize(jdbcMinPoolSizeWrap.o);
		}
		jdbcMinPoolSizeWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcMinPoolSize() {
		return jdbcMinPoolSize;
	}

	public String strJdbcMinPoolSize() {
		return jdbcMinPoolSize == null ? "" : jdbcMinPoolSize.toString();
	}

	public String jsonJdbcMinPoolSize() {
		return jdbcMinPoolSize == null ? "" : jdbcMinPoolSize.toString();
	}

	public String nomAffichageJdbcMinPoolSize() {
		return null;
	}

	public String htmTooltipJdbcMinPoolSize() {
		return null;
	}

	public String htmJdbcMinPoolSize() {
		return jdbcMinPoolSize == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcMinPoolSize());
	}

	///////////////////////
	// jdbcMaxStatements //
	///////////////////////

	/**	L'entité « jdbcMaxStatements »
The max statements for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxStatements;
	@JsonIgnore
	public Wrap<Integer> jdbcMaxStatementsWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMaxStatements").o(jdbcMaxStatements);

	/**	<br/>L'entité « jdbcMaxStatements »
The max statements for the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxStatements">Trouver l'entité jdbcMaxStatements dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMaxStatements(Wrap<Integer> c);

	public Integer getJdbcMaxStatements() {
		return jdbcMaxStatements;
	}

	public void setJdbcMaxStatements(Integer jdbcMaxStatements) {
		this.jdbcMaxStatements = jdbcMaxStatements;
		this.jdbcMaxStatementsWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcMaxStatements(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcMaxStatements = Integer.parseInt(o);
		this.jdbcMaxStatementsWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcMaxStatementsInit() {
		if(!jdbcMaxStatementsWrap.alreadyInitialized) {
			_jdbcMaxStatements(jdbcMaxStatementsWrap);
			if(jdbcMaxStatements == null)
				setJdbcMaxStatements(jdbcMaxStatementsWrap.o);
		}
		jdbcMaxStatementsWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcMaxStatements() {
		return jdbcMaxStatements;
	}

	public String strJdbcMaxStatements() {
		return jdbcMaxStatements == null ? "" : jdbcMaxStatements.toString();
	}

	public String jsonJdbcMaxStatements() {
		return jdbcMaxStatements == null ? "" : jdbcMaxStatements.toString();
	}

	public String nomAffichageJdbcMaxStatements() {
		return null;
	}

	public String htmTooltipJdbcMaxStatements() {
		return null;
	}

	public String htmJdbcMaxStatements() {
		return jdbcMaxStatements == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcMaxStatements());
	}

	////////////////////////////////////
	// jdbcMaxStatementsPerConnection //
	////////////////////////////////////

	/**	L'entité « jdbcMaxStatementsPerConnection »
The max statements per connection for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxStatementsPerConnection;
	@JsonIgnore
	public Wrap<Integer> jdbcMaxStatementsPerConnectionWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMaxStatementsPerConnection").o(jdbcMaxStatementsPerConnection);

	/**	<br/>L'entité « jdbcMaxStatementsPerConnection »
The max statements per connection for the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxStatementsPerConnection">Trouver l'entité jdbcMaxStatementsPerConnection dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMaxStatementsPerConnection(Wrap<Integer> c);

	public Integer getJdbcMaxStatementsPerConnection() {
		return jdbcMaxStatementsPerConnection;
	}

	public void setJdbcMaxStatementsPerConnection(Integer jdbcMaxStatementsPerConnection) {
		this.jdbcMaxStatementsPerConnection = jdbcMaxStatementsPerConnection;
		this.jdbcMaxStatementsPerConnectionWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcMaxStatementsPerConnection(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcMaxStatementsPerConnection = Integer.parseInt(o);
		this.jdbcMaxStatementsPerConnectionWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcMaxStatementsPerConnectionInit() {
		if(!jdbcMaxStatementsPerConnectionWrap.alreadyInitialized) {
			_jdbcMaxStatementsPerConnection(jdbcMaxStatementsPerConnectionWrap);
			if(jdbcMaxStatementsPerConnection == null)
				setJdbcMaxStatementsPerConnection(jdbcMaxStatementsPerConnectionWrap.o);
		}
		jdbcMaxStatementsPerConnectionWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcMaxStatementsPerConnection() {
		return jdbcMaxStatementsPerConnection;
	}

	public String strJdbcMaxStatementsPerConnection() {
		return jdbcMaxStatementsPerConnection == null ? "" : jdbcMaxStatementsPerConnection.toString();
	}

	public String jsonJdbcMaxStatementsPerConnection() {
		return jdbcMaxStatementsPerConnection == null ? "" : jdbcMaxStatementsPerConnection.toString();
	}

	public String nomAffichageJdbcMaxStatementsPerConnection() {
		return null;
	}

	public String htmTooltipJdbcMaxStatementsPerConnection() {
		return null;
	}

	public String htmJdbcMaxStatementsPerConnection() {
		return jdbcMaxStatementsPerConnection == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcMaxStatementsPerConnection());
	}

	/////////////////////
	// jdbcMaxIdleTime //
	/////////////////////

	/**	L'entité « jdbcMaxIdleTime »
The max idle time for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxIdleTime;
	@JsonIgnore
	public Wrap<Integer> jdbcMaxIdleTimeWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMaxIdleTime").o(jdbcMaxIdleTime);

	/**	<br/>L'entité « jdbcMaxIdleTime »
The max idle time for the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxIdleTime">Trouver l'entité jdbcMaxIdleTime dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMaxIdleTime(Wrap<Integer> c);

	public Integer getJdbcMaxIdleTime() {
		return jdbcMaxIdleTime;
	}

	public void setJdbcMaxIdleTime(Integer jdbcMaxIdleTime) {
		this.jdbcMaxIdleTime = jdbcMaxIdleTime;
		this.jdbcMaxIdleTimeWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcMaxIdleTime(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcMaxIdleTime = Integer.parseInt(o);
		this.jdbcMaxIdleTimeWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcMaxIdleTimeInit() {
		if(!jdbcMaxIdleTimeWrap.alreadyInitialized) {
			_jdbcMaxIdleTime(jdbcMaxIdleTimeWrap);
			if(jdbcMaxIdleTime == null)
				setJdbcMaxIdleTime(jdbcMaxIdleTimeWrap.o);
		}
		jdbcMaxIdleTimeWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcMaxIdleTime() {
		return jdbcMaxIdleTime;
	}

	public String strJdbcMaxIdleTime() {
		return jdbcMaxIdleTime == null ? "" : jdbcMaxIdleTime.toString();
	}

	public String jsonJdbcMaxIdleTime() {
		return jdbcMaxIdleTime == null ? "" : jdbcMaxIdleTime.toString();
	}

	public String nomAffichageJdbcMaxIdleTime() {
		return null;
	}

	public String htmTooltipJdbcMaxIdleTime() {
		return null;
	}

	public String htmJdbcMaxIdleTime() {
		return jdbcMaxIdleTime == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcMaxIdleTime());
	}

	////////////////////////
	// jdbcConnectTimeout //
	////////////////////////

	/**	L'entité « jdbcConnectTimeout »
The max idle time for the connection to the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcConnectTimeout;
	@JsonIgnore
	public Wrap<Integer> jdbcConnectTimeoutWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcConnectTimeout").o(jdbcConnectTimeout);

	/**	<br/>L'entité « jdbcConnectTimeout »
The max idle time for the connection to the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcConnectTimeout">Trouver l'entité jdbcConnectTimeout dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcConnectTimeout(Wrap<Integer> c);

	public Integer getJdbcConnectTimeout() {
		return jdbcConnectTimeout;
	}

	public void setJdbcConnectTimeout(Integer jdbcConnectTimeout) {
		this.jdbcConnectTimeout = jdbcConnectTimeout;
		this.jdbcConnectTimeoutWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcConnectTimeout(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcConnectTimeout = Integer.parseInt(o);
		this.jdbcConnectTimeoutWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcConnectTimeoutInit() {
		if(!jdbcConnectTimeoutWrap.alreadyInitialized) {
			_jdbcConnectTimeout(jdbcConnectTimeoutWrap);
			if(jdbcConnectTimeout == null)
				setJdbcConnectTimeout(jdbcConnectTimeoutWrap.o);
		}
		jdbcConnectTimeoutWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcConnectTimeout() {
		return jdbcConnectTimeout;
	}

	public String strJdbcConnectTimeout() {
		return jdbcConnectTimeout == null ? "" : jdbcConnectTimeout.toString();
	}

	public String jsonJdbcConnectTimeout() {
		return jdbcConnectTimeout == null ? "" : jdbcConnectTimeout.toString();
	}

	public String nomAffichageJdbcConnectTimeout() {
		return null;
	}

	public String htmTooltipJdbcConnectTimeout() {
		return null;
	}

	public String htmJdbcConnectTimeout() {
		return jdbcConnectTimeout == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcConnectTimeout());
	}

	//////////////
	// jdbcHost //
	//////////////

	/**	L'entité « jdbcHost »
The JDBC URL to the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcHost;
	@JsonIgnore
	public Wrap<String> jdbcHostWrap = new Wrap<String>().p(this).c(String.class).var("jdbcHost").o(jdbcHost);

	/**	<br/>L'entité « jdbcHost »
The JDBC URL to the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcHost">Trouver l'entité jdbcHost dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcHost(Wrap<String> c);

	public String getJdbcHost() {
		return jdbcHost;
	}

	public void setJdbcHost(String jdbcHost) {
		this.jdbcHost = jdbcHost;
		this.jdbcHostWrap.alreadyInitialized = true;
	}
	protected SiteConfig jdbcHostInit() {
		if(!jdbcHostWrap.alreadyInitialized) {
			_jdbcHost(jdbcHostWrap);
			if(jdbcHost == null)
				setJdbcHost(jdbcHostWrap.o);
		}
		jdbcHostWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrJdbcHost() {
		return jdbcHost;
	}

	public String strJdbcHost() {
		return jdbcHost == null ? "" : jdbcHost;
	}

	public String jsonJdbcHost() {
		return jdbcHost == null ? "" : jdbcHost;
	}

	public String nomAffichageJdbcHost() {
		return null;
	}

	public String htmTooltipJdbcHost() {
		return null;
	}

	public String htmJdbcHost() {
		return jdbcHost == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcHost());
	}

	//////////////
	// jdbcPort //
	//////////////

	/**	L'entité « jdbcPort »
The JDBC URL to the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcPort;
	@JsonIgnore
	public Wrap<Integer> jdbcPortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcPort").o(jdbcPort);

	/**	<br/>L'entité « jdbcPort »
The JDBC URL to the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcPort">Trouver l'entité jdbcPort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcPort(Wrap<Integer> c);

	public Integer getJdbcPort() {
		return jdbcPort;
	}

	public void setJdbcPort(Integer jdbcPort) {
		this.jdbcPort = jdbcPort;
		this.jdbcPortWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcPort(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcPort = Integer.parseInt(o);
		this.jdbcPortWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcPortInit() {
		if(!jdbcPortWrap.alreadyInitialized) {
			_jdbcPort(jdbcPortWrap);
			if(jdbcPort == null)
				setJdbcPort(jdbcPortWrap.o);
		}
		jdbcPortWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcPort() {
		return jdbcPort;
	}

	public String strJdbcPort() {
		return jdbcPort == null ? "" : jdbcPort.toString();
	}

	public String jsonJdbcPort() {
		return jdbcPort == null ? "" : jdbcPort.toString();
	}

	public String nomAffichageJdbcPort() {
		return null;
	}

	public String htmTooltipJdbcPort() {
		return null;
	}

	public String htmJdbcPort() {
		return jdbcPort == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcPort());
	}

	//////////////////
	// jdbcDatabase //
	//////////////////

	/**	L'entité « jdbcDatabase »
The JDBC URL to the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcDatabase;
	@JsonIgnore
	public Wrap<String> jdbcDatabaseWrap = new Wrap<String>().p(this).c(String.class).var("jdbcDatabase").o(jdbcDatabase);

	/**	<br/>L'entité « jdbcDatabase »
The JDBC URL to the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcDatabase">Trouver l'entité jdbcDatabase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcDatabase(Wrap<String> c);

	public String getJdbcDatabase() {
		return jdbcDatabase;
	}

	public void setJdbcDatabase(String jdbcDatabase) {
		this.jdbcDatabase = jdbcDatabase;
		this.jdbcDatabaseWrap.alreadyInitialized = true;
	}
	protected SiteConfig jdbcDatabaseInit() {
		if(!jdbcDatabaseWrap.alreadyInitialized) {
			_jdbcDatabase(jdbcDatabaseWrap);
			if(jdbcDatabase == null)
				setJdbcDatabase(jdbcDatabaseWrap.o);
		}
		jdbcDatabaseWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrJdbcDatabase() {
		return jdbcDatabase;
	}

	public String strJdbcDatabase() {
		return jdbcDatabase == null ? "" : jdbcDatabase;
	}

	public String jsonJdbcDatabase() {
		return jdbcDatabase == null ? "" : jdbcDatabase;
	}

	public String nomAffichageJdbcDatabase() {
		return null;
	}

	public String htmTooltipJdbcDatabase() {
		return null;
	}

	public String htmJdbcDatabase() {
		return jdbcDatabase == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcDatabase());
	}

	/////////////
	// solrUrl //
	/////////////

	/**	L'entité « solrUrl »
The URL to the SOLR search engine. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String solrUrl;
	@JsonIgnore
	public Wrap<String> solrUrlWrap = new Wrap<String>().p(this).c(String.class).var("solrUrl").o(solrUrl);

	/**	<br/>L'entité « solrUrl »
The URL to the SOLR search engine. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:solrUrl">Trouver l'entité solrUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrUrl(Wrap<String> c);

	public String getSolrUrl() {
		return solrUrl;
	}

	public void setSolrUrl(String solrUrl) {
		this.solrUrl = solrUrl;
		this.solrUrlWrap.alreadyInitialized = true;
	}
	protected SiteConfig solrUrlInit() {
		if(!solrUrlWrap.alreadyInitialized) {
			_solrUrl(solrUrlWrap);
			if(solrUrl == null)
				setSolrUrl(solrUrlWrap.o);
		}
		solrUrlWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSolrUrl() {
		return solrUrl;
	}

	public String strSolrUrl() {
		return solrUrl == null ? "" : solrUrl;
	}

	public String jsonSolrUrl() {
		return solrUrl == null ? "" : solrUrl;
	}

	public String nomAffichageSolrUrl() {
		return null;
	}

	public String htmTooltipSolrUrl() {
		return null;
	}

	public String htmSolrUrl() {
		return solrUrl == null ? "" : StringEscapeUtils.escapeHtml4(strSolrUrl());
	}

	//////////////////////
	// solrUrlComputate //
	//////////////////////

	/**	L'entité « solrUrlComputate »
The URL to the SOLR search engine for the computate project. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String solrUrlComputate;
	@JsonIgnore
	public Wrap<String> solrUrlComputateWrap = new Wrap<String>().p(this).c(String.class).var("solrUrlComputate").o(solrUrlComputate);

	/**	<br/>L'entité « solrUrlComputate »
The URL to the SOLR search engine for the computate project. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:solrUrlComputate">Trouver l'entité solrUrlComputate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrUrlComputate(Wrap<String> c);

	public String getSolrUrlComputate() {
		return solrUrlComputate;
	}

	public void setSolrUrlComputate(String solrUrlComputate) {
		this.solrUrlComputate = solrUrlComputate;
		this.solrUrlComputateWrap.alreadyInitialized = true;
	}
	protected SiteConfig solrUrlComputateInit() {
		if(!solrUrlComputateWrap.alreadyInitialized) {
			_solrUrlComputate(solrUrlComputateWrap);
			if(solrUrlComputate == null)
				setSolrUrlComputate(solrUrlComputateWrap.o);
		}
		solrUrlComputateWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSolrUrlComputate() {
		return solrUrlComputate;
	}

	public String strSolrUrlComputate() {
		return solrUrlComputate == null ? "" : solrUrlComputate;
	}

	public String jsonSolrUrlComputate() {
		return solrUrlComputate == null ? "" : solrUrlComputate;
	}

	public String nomAffichageSolrUrlComputate() {
		return null;
	}

	public String htmTooltipSolrUrlComputate() {
		return null;
	}

	public String htmSolrUrlComputate() {
		return solrUrlComputate == null ? "" : StringEscapeUtils.escapeHtml4(strSolrUrlComputate());
	}

	/////////////////////
	// accountFacebook //
	/////////////////////

	/**	L'entité « accountFacebook »
The Facebook account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountFacebook;
	@JsonIgnore
	public Wrap<String> accountFacebookWrap = new Wrap<String>().p(this).c(String.class).var("accountFacebook").o(accountFacebook);

	/**	<br/>L'entité « accountFacebook »
The Facebook account for the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountFacebook">Trouver l'entité accountFacebook dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _accountFacebook(Wrap<String> c);

	public String getAccountFacebook() {
		return accountFacebook;
	}

	public void setAccountFacebook(String accountFacebook) {
		this.accountFacebook = accountFacebook;
		this.accountFacebookWrap.alreadyInitialized = true;
	}
	protected SiteConfig accountFacebookInit() {
		if(!accountFacebookWrap.alreadyInitialized) {
			_accountFacebook(accountFacebookWrap);
			if(accountFacebook == null)
				setAccountFacebook(accountFacebookWrap.o);
		}
		accountFacebookWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAccountFacebook() {
		return accountFacebook;
	}

	public String strAccountFacebook() {
		return accountFacebook == null ? "" : accountFacebook;
	}

	public String jsonAccountFacebook() {
		return accountFacebook == null ? "" : accountFacebook;
	}

	public String nomAffichageAccountFacebook() {
		return null;
	}

	public String htmTooltipAccountFacebook() {
		return null;
	}

	public String htmAccountFacebook() {
		return accountFacebook == null ? "" : StringEscapeUtils.escapeHtml4(strAccountFacebook());
	}

	////////////////////
	// accountTwitter //
	////////////////////

	/**	L'entité « accountTwitter »
The Twitter account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountTwitter;
	@JsonIgnore
	public Wrap<String> accountTwitterWrap = new Wrap<String>().p(this).c(String.class).var("accountTwitter").o(accountTwitter);

	/**	<br/>L'entité « accountTwitter »
The Twitter account for the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountTwitter">Trouver l'entité accountTwitter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _accountTwitter(Wrap<String> c);

	public String getAccountTwitter() {
		return accountTwitter;
	}

	public void setAccountTwitter(String accountTwitter) {
		this.accountTwitter = accountTwitter;
		this.accountTwitterWrap.alreadyInitialized = true;
	}
	protected SiteConfig accountTwitterInit() {
		if(!accountTwitterWrap.alreadyInitialized) {
			_accountTwitter(accountTwitterWrap);
			if(accountTwitter == null)
				setAccountTwitter(accountTwitterWrap.o);
		}
		accountTwitterWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAccountTwitter() {
		return accountTwitter;
	}

	public String strAccountTwitter() {
		return accountTwitter == null ? "" : accountTwitter;
	}

	public String jsonAccountTwitter() {
		return accountTwitter == null ? "" : accountTwitter;
	}

	public String nomAffichageAccountTwitter() {
		return null;
	}

	public String htmTooltipAccountTwitter() {
		return null;
	}

	public String htmAccountTwitter() {
		return accountTwitter == null ? "" : StringEscapeUtils.escapeHtml4(strAccountTwitter());
	}

	//////////////////////
	// accountInstagram //
	//////////////////////

	/**	L'entité « accountInstagram »
The Instagram account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountInstagram;
	@JsonIgnore
	public Wrap<String> accountInstagramWrap = new Wrap<String>().p(this).c(String.class).var("accountInstagram").o(accountInstagram);

	/**	<br/>L'entité « accountInstagram »
The Instagram account for the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountInstagram">Trouver l'entité accountInstagram dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _accountInstagram(Wrap<String> c);

	public String getAccountInstagram() {
		return accountInstagram;
	}

	public void setAccountInstagram(String accountInstagram) {
		this.accountInstagram = accountInstagram;
		this.accountInstagramWrap.alreadyInitialized = true;
	}
	protected SiteConfig accountInstagramInit() {
		if(!accountInstagramWrap.alreadyInitialized) {
			_accountInstagram(accountInstagramWrap);
			if(accountInstagram == null)
				setAccountInstagram(accountInstagramWrap.o);
		}
		accountInstagramWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAccountInstagram() {
		return accountInstagram;
	}

	public String strAccountInstagram() {
		return accountInstagram == null ? "" : accountInstagram;
	}

	public String jsonAccountInstagram() {
		return accountInstagram == null ? "" : accountInstagram;
	}

	public String nomAffichageAccountInstagram() {
		return null;
	}

	public String htmTooltipAccountInstagram() {
		return null;
	}

	public String htmAccountInstagram() {
		return accountInstagram == null ? "" : StringEscapeUtils.escapeHtml4(strAccountInstagram());
	}

	////////////////////
	// accountYoutube //
	////////////////////

	/**	L'entité « accountYoutube »
The Youtube account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountYoutube;
	@JsonIgnore
	public Wrap<String> accountYoutubeWrap = new Wrap<String>().p(this).c(String.class).var("accountYoutube").o(accountYoutube);

	/**	<br/>L'entité « accountYoutube »
The Youtube account for the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountYoutube">Trouver l'entité accountYoutube dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _accountYoutube(Wrap<String> c);

	public String getAccountYoutube() {
		return accountYoutube;
	}

	public void setAccountYoutube(String accountYoutube) {
		this.accountYoutube = accountYoutube;
		this.accountYoutubeWrap.alreadyInitialized = true;
	}
	protected SiteConfig accountYoutubeInit() {
		if(!accountYoutubeWrap.alreadyInitialized) {
			_accountYoutube(accountYoutubeWrap);
			if(accountYoutube == null)
				setAccountYoutube(accountYoutubeWrap.o);
		}
		accountYoutubeWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAccountYoutube() {
		return accountYoutube;
	}

	public String strAccountYoutube() {
		return accountYoutube == null ? "" : accountYoutube;
	}

	public String jsonAccountYoutube() {
		return accountYoutube == null ? "" : accountYoutube;
	}

	public String nomAffichageAccountYoutube() {
		return null;
	}

	public String htmTooltipAccountYoutube() {
		return null;
	}

	public String htmAccountYoutube() {
		return accountYoutube == null ? "" : StringEscapeUtils.escapeHtml4(strAccountYoutube());
	}

	//////////////////////
	// accountPinterest //
	//////////////////////

	/**	L'entité « accountPinterest »
The Pinterest account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountPinterest;
	@JsonIgnore
	public Wrap<String> accountPinterestWrap = new Wrap<String>().p(this).c(String.class).var("accountPinterest").o(accountPinterest);

	/**	<br/>L'entité « accountPinterest »
The Pinterest account for the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountPinterest">Trouver l'entité accountPinterest dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _accountPinterest(Wrap<String> c);

	public String getAccountPinterest() {
		return accountPinterest;
	}

	public void setAccountPinterest(String accountPinterest) {
		this.accountPinterest = accountPinterest;
		this.accountPinterestWrap.alreadyInitialized = true;
	}
	protected SiteConfig accountPinterestInit() {
		if(!accountPinterestWrap.alreadyInitialized) {
			_accountPinterest(accountPinterestWrap);
			if(accountPinterest == null)
				setAccountPinterest(accountPinterestWrap.o);
		}
		accountPinterestWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAccountPinterest() {
		return accountPinterest;
	}

	public String strAccountPinterest() {
		return accountPinterest == null ? "" : accountPinterest;
	}

	public String jsonAccountPinterest() {
		return accountPinterest == null ? "" : accountPinterest;
	}

	public String nomAffichageAccountPinterest() {
		return null;
	}

	public String htmTooltipAccountPinterest() {
		return null;
	}

	public String htmAccountPinterest() {
		return accountPinterest == null ? "" : StringEscapeUtils.escapeHtml4(strAccountPinterest());
	}

	//////////////////
	// accountEmail //
	//////////////////

	/**	L'entité « accountEmail »
The Email account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountEmail;
	@JsonIgnore
	public Wrap<String> accountEmailWrap = new Wrap<String>().p(this).c(String.class).var("accountEmail").o(accountEmail);

	/**	<br/>L'entité « accountEmail »
The Email account for the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountEmail">Trouver l'entité accountEmail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _accountEmail(Wrap<String> c);

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
		this.accountEmailWrap.alreadyInitialized = true;
	}
	protected SiteConfig accountEmailInit() {
		if(!accountEmailWrap.alreadyInitialized) {
			_accountEmail(accountEmailWrap);
			if(accountEmail == null)
				setAccountEmail(accountEmailWrap.o);
		}
		accountEmailWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAccountEmail() {
		return accountEmail;
	}

	public String strAccountEmail() {
		return accountEmail == null ? "" : accountEmail;
	}

	public String jsonAccountEmail() {
		return accountEmail == null ? "" : accountEmail;
	}

	public String nomAffichageAccountEmail() {
		return null;
	}

	public String htmTooltipAccountEmail() {
		return null;
	}

	public String htmAccountEmail() {
		return accountEmail == null ? "" : StringEscapeUtils.escapeHtml4(strAccountEmail());
	}

	///////////////
	// roleAdmin //
	///////////////

	/**	L'entité « roleAdmin »
The OpenID Connect role for an administrator. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String roleAdmin;
	@JsonIgnore
	public Wrap<String> roleAdminWrap = new Wrap<String>().p(this).c(String.class).var("roleAdmin").o(roleAdmin);

	/**	<br/>L'entité « roleAdmin »
The OpenID Connect role for an administrator. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:roleAdmin">Trouver l'entité roleAdmin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _roleAdmin(Wrap<String> c);

	public String getRoleAdmin() {
		return roleAdmin;
	}

	public void setRoleAdmin(String roleAdmin) {
		this.roleAdmin = roleAdmin;
		this.roleAdminWrap.alreadyInitialized = true;
	}
	protected SiteConfig roleAdminInit() {
		if(!roleAdminWrap.alreadyInitialized) {
			_roleAdmin(roleAdminWrap);
			if(roleAdmin == null)
				setRoleAdmin(roleAdminWrap.o);
		}
		roleAdminWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrRoleAdmin() {
		return roleAdmin;
	}

	public String strRoleAdmin() {
		return roleAdmin == null ? "" : roleAdmin;
	}

	public String jsonRoleAdmin() {
		return roleAdmin == null ? "" : roleAdmin;
	}

	public String nomAffichageRoleAdmin() {
		return null;
	}

	public String htmTooltipRoleAdmin() {
		return null;
	}

	public String htmRoleAdmin() {
		return roleAdmin == null ? "" : StringEscapeUtils.escapeHtml4(strRoleAdmin());
	}

	////////////////
	// emailAdmin //
	////////////////

	/**	L'entité « emailAdmin »
The email address for the administrator of the site for the error reports. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailAdmin;
	@JsonIgnore
	public Wrap<String> emailAdminWrap = new Wrap<String>().p(this).c(String.class).var("emailAdmin").o(emailAdmin);

	/**	<br/>L'entité « emailAdmin »
The email address for the administrator of the site for the error reports. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailAdmin">Trouver l'entité emailAdmin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _emailAdmin(Wrap<String> c);

	public String getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
		this.emailAdminWrap.alreadyInitialized = true;
	}
	protected SiteConfig emailAdminInit() {
		if(!emailAdminWrap.alreadyInitialized) {
			_emailAdmin(emailAdminWrap);
			if(emailAdmin == null)
				setEmailAdmin(emailAdminWrap.o);
		}
		emailAdminWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrEmailAdmin() {
		return emailAdmin;
	}

	public String strEmailAdmin() {
		return emailAdmin == null ? "" : emailAdmin;
	}

	public String jsonEmailAdmin() {
		return emailAdmin == null ? "" : emailAdmin;
	}

	public String nomAffichageEmailAdmin() {
		return null;
	}

	public String htmTooltipEmailAdmin() {
		return null;
	}

	public String htmEmailAdmin() {
		return emailAdmin == null ? "" : StringEscapeUtils.escapeHtml4(strEmailAdmin());
	}

	/////////////////////
	// numberExecutors //
	/////////////////////

	/**	L'entité « numberExecutors »
The number of executors for executing background tasks in the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer numberExecutors;
	@JsonIgnore
	public Wrap<Integer> numberExecutorsWrap = new Wrap<Integer>().p(this).c(Integer.class).var("numberExecutors").o(numberExecutors);

	/**	<br/>L'entité « numberExecutors »
The number of executors for executing background tasks in the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:numberExecutors">Trouver l'entité numberExecutors dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _numberExecutors(Wrap<Integer> c);

	public Integer getNumberExecutors() {
		return numberExecutors;
	}

	public void setNumberExecutors(Integer numberExecutors) {
		this.numberExecutors = numberExecutors;
		this.numberExecutorsWrap.alreadyInitialized = true;
	}
	public SiteConfig setNumberExecutors(String o) {
		if(NumberUtils.isParsable(o))
			this.numberExecutors = Integer.parseInt(o);
		this.numberExecutorsWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig numberExecutorsInit() {
		if(!numberExecutorsWrap.alreadyInitialized) {
			_numberExecutors(numberExecutorsWrap);
			if(numberExecutors == null)
				setNumberExecutors(numberExecutorsWrap.o);
		}
		numberExecutorsWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrNumberExecutors() {
		return numberExecutors;
	}

	public String strNumberExecutors() {
		return numberExecutors == null ? "" : numberExecutors.toString();
	}

	public String jsonNumberExecutors() {
		return numberExecutors == null ? "" : numberExecutors.toString();
	}

	public String nomAffichageNumberExecutors() {
		return null;
	}

	public String htmTooltipNumberExecutors() {
		return null;
	}

	public String htmNumberExecutors() {
		return numberExecutors == null ? "" : StringEscapeUtils.escapeHtml4(strNumberExecutors());
	}

	////////////////////
	// openApiVersion //
	////////////////////

	/**	L'entité « openApiVersion »
The version of OpenAPI used with Vert.x which should probably be 3.0. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String openApiVersion;
	@JsonIgnore
	public Wrap<String> openApiVersionWrap = new Wrap<String>().p(this).c(String.class).var("openApiVersion").o(openApiVersion);

	/**	<br/>L'entité « openApiVersion »
The version of OpenAPI used with Vert.x which should probably be 3.0. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:openApiVersion">Trouver l'entité openApiVersion dans Solr</a>
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
	protected SiteConfig openApiVersionInit() {
		if(!openApiVersionWrap.alreadyInitialized) {
			_openApiVersion(openApiVersionWrap);
			if(openApiVersion == null)
				setOpenApiVersion(openApiVersionWrap.o);
		}
		openApiVersionWrap.alreadyInitialized(true);
		return (SiteConfig)this;
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

	////////////////////
	// apiDescription //
	////////////////////

	/**	L'entité « apiDescription »
The description of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiDescription;
	@JsonIgnore
	public Wrap<String> apiDescriptionWrap = new Wrap<String>().p(this).c(String.class).var("apiDescription").o(apiDescription);

	/**	<br/>L'entité « apiDescription »
The description of your site API. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiDescription">Trouver l'entité apiDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiDescription(Wrap<String> c);

	public String getApiDescription() {
		return apiDescription;
	}

	public void setApiDescription(String apiDescription) {
		this.apiDescription = apiDescription;
		this.apiDescriptionWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiDescriptionInit() {
		if(!apiDescriptionWrap.alreadyInitialized) {
			_apiDescription(apiDescriptionWrap);
			if(apiDescription == null)
				setApiDescription(apiDescriptionWrap.o);
		}
		apiDescriptionWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiDescription() {
		return apiDescription;
	}

	public String strApiDescription() {
		return apiDescription == null ? "" : apiDescription;
	}

	public String jsonApiDescription() {
		return apiDescription == null ? "" : apiDescription;
	}

	public String nomAffichageApiDescription() {
		return null;
	}

	public String htmTooltipApiDescription() {
		return null;
	}

	public String htmApiDescription() {
		return apiDescription == null ? "" : StringEscapeUtils.escapeHtml4(strApiDescription());
	}

	//////////////
	// apiTitle //
	//////////////

	/**	L'entité « apiTitle »
The title of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiTitle;
	@JsonIgnore
	public Wrap<String> apiTitleWrap = new Wrap<String>().p(this).c(String.class).var("apiTitle").o(apiTitle);

	/**	<br/>L'entité « apiTitle »
The title of your site API. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiTitle">Trouver l'entité apiTitle dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiTitle(Wrap<String> c);

	public String getApiTitle() {
		return apiTitle;
	}

	public void setApiTitle(String apiTitle) {
		this.apiTitle = apiTitle;
		this.apiTitleWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiTitleInit() {
		if(!apiTitleWrap.alreadyInitialized) {
			_apiTitle(apiTitleWrap);
			if(apiTitle == null)
				setApiTitle(apiTitleWrap.o);
		}
		apiTitleWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiTitle() {
		return apiTitle;
	}

	public String strApiTitle() {
		return apiTitle == null ? "" : apiTitle;
	}

	public String jsonApiTitle() {
		return apiTitle == null ? "" : apiTitle;
	}

	public String nomAffichageApiTitle() {
		return null;
	}

	public String htmTooltipApiTitle() {
		return null;
	}

	public String htmApiTitle() {
		return apiTitle == null ? "" : StringEscapeUtils.escapeHtml4(strApiTitle());
	}

	/////////////////////
	// apiTermsService //
	/////////////////////

	/**	L'entité « apiTermsService »
The terms of service of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiTermsService;
	@JsonIgnore
	public Wrap<String> apiTermsServiceWrap = new Wrap<String>().p(this).c(String.class).var("apiTermsService").o(apiTermsService);

	/**	<br/>L'entité « apiTermsService »
The terms of service of your site API. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiTermsService">Trouver l'entité apiTermsService dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiTermsService(Wrap<String> c);

	public String getApiTermsService() {
		return apiTermsService;
	}

	public void setApiTermsService(String apiTermsService) {
		this.apiTermsService = apiTermsService;
		this.apiTermsServiceWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiTermsServiceInit() {
		if(!apiTermsServiceWrap.alreadyInitialized) {
			_apiTermsService(apiTermsServiceWrap);
			if(apiTermsService == null)
				setApiTermsService(apiTermsServiceWrap.o);
		}
		apiTermsServiceWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiTermsService() {
		return apiTermsService;
	}

	public String strApiTermsService() {
		return apiTermsService == null ? "" : apiTermsService;
	}

	public String jsonApiTermsService() {
		return apiTermsService == null ? "" : apiTermsService;
	}

	public String nomAffichageApiTermsService() {
		return null;
	}

	public String htmTooltipApiTermsService() {
		return null;
	}

	public String htmApiTermsService() {
		return apiTermsService == null ? "" : StringEscapeUtils.escapeHtml4(strApiTermsService());
	}

	////////////////
	// apiVersion //
	////////////////

	/**	L'entité « apiVersion »
The version of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiVersion;
	@JsonIgnore
	public Wrap<String> apiVersionWrap = new Wrap<String>().p(this).c(String.class).var("apiVersion").o(apiVersion);

	/**	<br/>L'entité « apiVersion »
The version of your site API. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiVersion">Trouver l'entité apiVersion dans Solr</a>
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
	protected SiteConfig apiVersionInit() {
		if(!apiVersionWrap.alreadyInitialized) {
			_apiVersion(apiVersionWrap);
			if(apiVersion == null)
				setApiVersion(apiVersionWrap.o);
		}
		apiVersionWrap.alreadyInitialized(true);
		return (SiteConfig)this;
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
	// apiContactEmail //
	/////////////////////

	/**	L'entité « apiContactEmail »
The contact email of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiContactEmail;
	@JsonIgnore
	public Wrap<String> apiContactEmailWrap = new Wrap<String>().p(this).c(String.class).var("apiContactEmail").o(apiContactEmail);

	/**	<br/>L'entité « apiContactEmail »
The contact email of your site API. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiContactEmail">Trouver l'entité apiContactEmail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiContactEmail(Wrap<String> c);

	public String getApiContactEmail() {
		return apiContactEmail;
	}

	public void setApiContactEmail(String apiContactEmail) {
		this.apiContactEmail = apiContactEmail;
		this.apiContactEmailWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiContactEmailInit() {
		if(!apiContactEmailWrap.alreadyInitialized) {
			_apiContactEmail(apiContactEmailWrap);
			if(apiContactEmail == null)
				setApiContactEmail(apiContactEmailWrap.o);
		}
		apiContactEmailWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiContactEmail() {
		return apiContactEmail;
	}

	public String strApiContactEmail() {
		return apiContactEmail == null ? "" : apiContactEmail;
	}

	public String jsonApiContactEmail() {
		return apiContactEmail == null ? "" : apiContactEmail;
	}

	public String nomAffichageApiContactEmail() {
		return null;
	}

	public String htmTooltipApiContactEmail() {
		return null;
	}

	public String htmApiContactEmail() {
		return apiContactEmail == null ? "" : StringEscapeUtils.escapeHtml4(strApiContactEmail());
	}

	////////////////////
	// apiLicenseName //
	////////////////////

	/**	L'entité « apiLicenseName »
The open source license name of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiLicenseName;
	@JsonIgnore
	public Wrap<String> apiLicenseNameWrap = new Wrap<String>().p(this).c(String.class).var("apiLicenseName").o(apiLicenseName);

	/**	<br/>L'entité « apiLicenseName »
The open source license name of your site API. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiLicenseName">Trouver l'entité apiLicenseName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiLicenseName(Wrap<String> c);

	public String getApiLicenseName() {
		return apiLicenseName;
	}

	public void setApiLicenseName(String apiLicenseName) {
		this.apiLicenseName = apiLicenseName;
		this.apiLicenseNameWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiLicenseNameInit() {
		if(!apiLicenseNameWrap.alreadyInitialized) {
			_apiLicenseName(apiLicenseNameWrap);
			if(apiLicenseName == null)
				setApiLicenseName(apiLicenseNameWrap.o);
		}
		apiLicenseNameWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiLicenseName() {
		return apiLicenseName;
	}

	public String strApiLicenseName() {
		return apiLicenseName == null ? "" : apiLicenseName;
	}

	public String jsonApiLicenseName() {
		return apiLicenseName == null ? "" : apiLicenseName;
	}

	public String nomAffichageApiLicenseName() {
		return null;
	}

	public String htmTooltipApiLicenseName() {
		return null;
	}

	public String htmApiLicenseName() {
		return apiLicenseName == null ? "" : StringEscapeUtils.escapeHtml4(strApiLicenseName());
	}

	///////////////////
	// apiLicenseUrl //
	///////////////////

	/**	L'entité « apiLicenseUrl »
The open source license URL of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiLicenseUrl;
	@JsonIgnore
	public Wrap<String> apiLicenseUrlWrap = new Wrap<String>().p(this).c(String.class).var("apiLicenseUrl").o(apiLicenseUrl);

	/**	<br/>L'entité « apiLicenseUrl »
The open source license URL of your site API. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiLicenseUrl">Trouver l'entité apiLicenseUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiLicenseUrl(Wrap<String> c);

	public String getApiLicenseUrl() {
		return apiLicenseUrl;
	}

	public void setApiLicenseUrl(String apiLicenseUrl) {
		this.apiLicenseUrl = apiLicenseUrl;
		this.apiLicenseUrlWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiLicenseUrlInit() {
		if(!apiLicenseUrlWrap.alreadyInitialized) {
			_apiLicenseUrl(apiLicenseUrlWrap);
			if(apiLicenseUrl == null)
				setApiLicenseUrl(apiLicenseUrlWrap.o);
		}
		apiLicenseUrlWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiLicenseUrl() {
		return apiLicenseUrl;
	}

	public String strApiLicenseUrl() {
		return apiLicenseUrl == null ? "" : apiLicenseUrl;
	}

	public String jsonApiLicenseUrl() {
		return apiLicenseUrl == null ? "" : apiLicenseUrl;
	}

	public String nomAffichageApiLicenseUrl() {
		return null;
	}

	public String htmTooltipApiLicenseUrl() {
		return null;
	}

	public String htmApiLicenseUrl() {
		return apiLicenseUrl == null ? "" : StringEscapeUtils.escapeHtml4(strApiLicenseUrl());
	}

	/////////////////
	// apiHostName //
	/////////////////

	/**	L'entité « apiHostName »
The host name of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiHostName;
	@JsonIgnore
	public Wrap<String> apiHostNameWrap = new Wrap<String>().p(this).c(String.class).var("apiHostName").o(apiHostName);

	/**	<br/>L'entité « apiHostName »
The host name of your site API. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiHostName">Trouver l'entité apiHostName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiHostName(Wrap<String> c);

	public String getApiHostName() {
		return apiHostName;
	}

	public void setApiHostName(String apiHostName) {
		this.apiHostName = apiHostName;
		this.apiHostNameWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiHostNameInit() {
		if(!apiHostNameWrap.alreadyInitialized) {
			_apiHostName(apiHostNameWrap);
			if(apiHostName == null)
				setApiHostName(apiHostNameWrap.o);
		}
		apiHostNameWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiHostName() {
		return apiHostName;
	}

	public String strApiHostName() {
		return apiHostName == null ? "" : apiHostName;
	}

	public String jsonApiHostName() {
		return apiHostName == null ? "" : apiHostName;
	}

	public String nomAffichageApiHostName() {
		return null;
	}

	public String htmTooltipApiHostName() {
		return null;
	}

	public String htmApiHostName() {
		return apiHostName == null ? "" : StringEscapeUtils.escapeHtml4(strApiHostName());
	}

	/////////////////
	// apiBasePath //
	/////////////////

	/**	L'entité « apiBasePath »
The base path of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiBasePath;
	@JsonIgnore
	public Wrap<String> apiBasePathWrap = new Wrap<String>().p(this).c(String.class).var("apiBasePath").o(apiBasePath);

	/**	<br/>L'entité « apiBasePath »
The base path of your site API. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiBasePath">Trouver l'entité apiBasePath dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiBasePath(Wrap<String> c);

	public String getApiBasePath() {
		return apiBasePath;
	}

	public void setApiBasePath(String apiBasePath) {
		this.apiBasePath = apiBasePath;
		this.apiBasePathWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiBasePathInit() {
		if(!apiBasePathWrap.alreadyInitialized) {
			_apiBasePath(apiBasePathWrap);
			if(apiBasePath == null)
				setApiBasePath(apiBasePathWrap.o);
		}
		apiBasePathWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiBasePath() {
		return apiBasePath;
	}

	public String strApiBasePath() {
		return apiBasePath == null ? "" : apiBasePath;
	}

	public String jsonApiBasePath() {
		return apiBasePath == null ? "" : apiBasePath;
	}

	public String nomAffichageApiBasePath() {
		return null;
	}

	public String htmTooltipApiBasePath() {
		return null;
	}

	public String htmApiBasePath() {
		return apiBasePath == null ? "" : StringEscapeUtils.escapeHtml4(strApiBasePath());
	}

	///////////////////
	// staticBaseUrl //
	///////////////////

	/**	L'entité « staticBaseUrl »
The base URL of your static files. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String staticBaseUrl;
	@JsonIgnore
	public Wrap<String> staticBaseUrlWrap = new Wrap<String>().p(this).c(String.class).var("staticBaseUrl").o(staticBaseUrl);

	/**	<br/>L'entité « staticBaseUrl »
The base URL of your static files. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:staticBaseUrl">Trouver l'entité staticBaseUrl dans Solr</a>
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
	protected SiteConfig staticBaseUrlInit() {
		if(!staticBaseUrlWrap.alreadyInitialized) {
			_staticBaseUrl(staticBaseUrlWrap);
			if(staticBaseUrl == null)
				setStaticBaseUrl(staticBaseUrlWrap.o);
		}
		staticBaseUrlWrap.alreadyInitialized(true);
		return (SiteConfig)this;
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

	///////////////
	// emailHost //
	///////////////

	/**	L'entité « emailHost »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailHost;
	@JsonIgnore
	public Wrap<String> emailHostWrap = new Wrap<String>().p(this).c(String.class).var("emailHost").o(emailHost);

	/**	<br/>L'entité « emailHost »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailHost">Trouver l'entité emailHost dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _emailHost(Wrap<String> c);

	public String getEmailHost() {
		return emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
		this.emailHostWrap.alreadyInitialized = true;
	}
	protected SiteConfig emailHostInit() {
		if(!emailHostWrap.alreadyInitialized) {
			_emailHost(emailHostWrap);
			if(emailHost == null)
				setEmailHost(emailHostWrap.o);
		}
		emailHostWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrEmailHost() {
		return emailHost;
	}

	public String strEmailHost() {
		return emailHost == null ? "" : emailHost;
	}

	public String jsonEmailHost() {
		return emailHost == null ? "" : emailHost;
	}

	public String nomAffichageEmailHost() {
		return null;
	}

	public String htmTooltipEmailHost() {
		return null;
	}

	public String htmEmailHost() {
		return emailHost == null ? "" : StringEscapeUtils.escapeHtml4(strEmailHost());
	}

	///////////////
	// emailPort //
	///////////////

	/**	L'entité « emailPort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer emailPort;
	@JsonIgnore
	public Wrap<Integer> emailPortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("emailPort").o(emailPort);

	/**	<br/>L'entité « emailPort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailPort">Trouver l'entité emailPort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _emailPort(Wrap<Integer> c);

	public Integer getEmailPort() {
		return emailPort;
	}

	public void setEmailPort(Integer emailPort) {
		this.emailPort = emailPort;
		this.emailPortWrap.alreadyInitialized = true;
	}
	public SiteConfig setEmailPort(String o) {
		if(NumberUtils.isParsable(o))
			this.emailPort = Integer.parseInt(o);
		this.emailPortWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig emailPortInit() {
		if(!emailPortWrap.alreadyInitialized) {
			_emailPort(emailPortWrap);
			if(emailPort == null)
				setEmailPort(emailPortWrap.o);
		}
		emailPortWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrEmailPort() {
		return emailPort;
	}

	public String strEmailPort() {
		return emailPort == null ? "" : emailPort.toString();
	}

	public String jsonEmailPort() {
		return emailPort == null ? "" : emailPort.toString();
	}

	public String nomAffichageEmailPort() {
		return null;
	}

	public String htmTooltipEmailPort() {
		return null;
	}

	public String htmEmailPort() {
		return emailPort == null ? "" : StringEscapeUtils.escapeHtml4(strEmailPort());
	}

	///////////////////
	// emailUsername //
	///////////////////

	/**	L'entité « emailUsername »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailUsername;
	@JsonIgnore
	public Wrap<String> emailUsernameWrap = new Wrap<String>().p(this).c(String.class).var("emailUsername").o(emailUsername);

	/**	<br/>L'entité « emailUsername »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailUsername">Trouver l'entité emailUsername dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _emailUsername(Wrap<String> c);

	public String getEmailUsername() {
		return emailUsername;
	}

	public void setEmailUsername(String emailUsername) {
		this.emailUsername = emailUsername;
		this.emailUsernameWrap.alreadyInitialized = true;
	}
	protected SiteConfig emailUsernameInit() {
		if(!emailUsernameWrap.alreadyInitialized) {
			_emailUsername(emailUsernameWrap);
			if(emailUsername == null)
				setEmailUsername(emailUsernameWrap.o);
		}
		emailUsernameWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrEmailUsername() {
		return emailUsername;
	}

	public String strEmailUsername() {
		return emailUsername == null ? "" : emailUsername;
	}

	public String jsonEmailUsername() {
		return emailUsername == null ? "" : emailUsername;
	}

	public String nomAffichageEmailUsername() {
		return null;
	}

	public String htmTooltipEmailUsername() {
		return null;
	}

	public String htmEmailUsername() {
		return emailUsername == null ? "" : StringEscapeUtils.escapeHtml4(strEmailUsername());
	}

	///////////////////
	// emailPassword //
	///////////////////

	/**	L'entité « emailPassword »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailPassword;
	@JsonIgnore
	public Wrap<String> emailPasswordWrap = new Wrap<String>().p(this).c(String.class).var("emailPassword").o(emailPassword);

	/**	<br/>L'entité « emailPassword »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailPassword">Trouver l'entité emailPassword dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _emailPassword(Wrap<String> c);

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
		this.emailPasswordWrap.alreadyInitialized = true;
	}
	protected SiteConfig emailPasswordInit() {
		if(!emailPasswordWrap.alreadyInitialized) {
			_emailPassword(emailPasswordWrap);
			if(emailPassword == null)
				setEmailPassword(emailPasswordWrap.o);
		}
		emailPasswordWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrEmailPassword() {
		return emailPassword;
	}

	public String strEmailPassword() {
		return emailPassword == null ? "" : emailPassword;
	}

	public String jsonEmailPassword() {
		return emailPassword == null ? "" : emailPassword;
	}

	public String nomAffichageEmailPassword() {
		return null;
	}

	public String htmTooltipEmailPassword() {
		return null;
	}

	public String htmEmailPassword() {
		return emailPassword == null ? "" : StringEscapeUtils.escapeHtml4(strEmailPassword());
	}

	///////////////
	// emailFrom //
	///////////////

	/**	L'entité « emailFrom »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailFrom;
	@JsonIgnore
	public Wrap<String> emailFromWrap = new Wrap<String>().p(this).c(String.class).var("emailFrom").o(emailFrom);

	/**	<br/>L'entité « emailFrom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailFrom">Trouver l'entité emailFrom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _emailFrom(Wrap<String> c);

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
		this.emailFromWrap.alreadyInitialized = true;
	}
	protected SiteConfig emailFromInit() {
		if(!emailFromWrap.alreadyInitialized) {
			_emailFrom(emailFromWrap);
			if(emailFrom == null)
				setEmailFrom(emailFromWrap.o);
		}
		emailFromWrap.alreadyInitialized(true);
		return (SiteConfig)this;
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

	///////////////
	// emailAuth //
	///////////////

	/**	L'entité « emailAuth »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean emailAuth;
	@JsonIgnore
	public Wrap<Boolean> emailAuthWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("emailAuth").o(emailAuth);

	/**	<br/>L'entité « emailAuth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailAuth">Trouver l'entité emailAuth dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _emailAuth(Wrap<Boolean> c);

	public Boolean getEmailAuth() {
		return emailAuth;
	}

	public void setEmailAuth(Boolean emailAuth) {
		this.emailAuth = emailAuth;
		this.emailAuthWrap.alreadyInitialized = true;
	}
	public SiteConfig setEmailAuth(String o) {
		this.emailAuth = Boolean.parseBoolean(o);
		this.emailAuthWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig emailAuthInit() {
		if(!emailAuthWrap.alreadyInitialized) {
			_emailAuth(emailAuthWrap);
			if(emailAuth == null)
				setEmailAuth(emailAuthWrap.o);
		}
		emailAuthWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Boolean solrEmailAuth() {
		return emailAuth;
	}

	public String strEmailAuth() {
		return emailAuth == null ? "" : emailAuth.toString();
	}

	public String jsonEmailAuth() {
		return emailAuth == null ? "" : emailAuth.toString();
	}

	public String nomAffichageEmailAuth() {
		return null;
	}

	public String htmTooltipEmailAuth() {
		return null;
	}

	public String htmEmailAuth() {
		return emailAuth == null ? "" : StringEscapeUtils.escapeHtml4(strEmailAuth());
	}

	//////////////
	// emailSsl //
	//////////////

	/**	L'entité « emailSsl »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean emailSsl;
	@JsonIgnore
	public Wrap<Boolean> emailSslWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("emailSsl").o(emailSsl);

	/**	<br/>L'entité « emailSsl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailSsl">Trouver l'entité emailSsl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _emailSsl(Wrap<Boolean> c);

	public Boolean getEmailSsl() {
		return emailSsl;
	}

	public void setEmailSsl(Boolean emailSsl) {
		this.emailSsl = emailSsl;
		this.emailSslWrap.alreadyInitialized = true;
	}
	public SiteConfig setEmailSsl(String o) {
		this.emailSsl = Boolean.parseBoolean(o);
		this.emailSslWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig emailSslInit() {
		if(!emailSslWrap.alreadyInitialized) {
			_emailSsl(emailSslWrap);
			if(emailSsl == null)
				setEmailSsl(emailSslWrap.o);
		}
		emailSslWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Boolean solrEmailSsl() {
		return emailSsl;
	}

	public String strEmailSsl() {
		return emailSsl == null ? "" : emailSsl.toString();
	}

	public String jsonEmailSsl() {
		return emailSsl == null ? "" : emailSsl.toString();
	}

	public String nomAffichageEmailSsl() {
		return null;
	}

	public String htmTooltipEmailSsl() {
		return null;
	}

	public String htmEmailSsl() {
		return emailSsl == null ? "" : StringEscapeUtils.escapeHtml4(strEmailSsl());
	}

	//////////////
	// siteZone //
	//////////////

	/**	L'entité « siteZone »
The default timezone of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteZone;
	@JsonIgnore
	public Wrap<String> siteZoneWrap = new Wrap<String>().p(this).c(String.class).var("siteZone").o(siteZone);

	/**	<br/>L'entité « siteZone »
The default timezone of the site. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteZone">Trouver l'entité siteZone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteZone(Wrap<String> c);

	public String getSiteZone() {
		return siteZone;
	}

	public void setSiteZone(String siteZone) {
		this.siteZone = siteZone;
		this.siteZoneWrap.alreadyInitialized = true;
	}
	protected SiteConfig siteZoneInit() {
		if(!siteZoneWrap.alreadyInitialized) {
			_siteZone(siteZoneWrap);
			if(siteZone == null)
				setSiteZone(siteZoneWrap.o);
		}
		siteZoneWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSiteZone() {
		return siteZone;
	}

	public String strSiteZone() {
		return siteZone == null ? "" : siteZone;
	}

	public String jsonSiteZone() {
		return siteZone == null ? "" : siteZone;
	}

	public String nomAffichageSiteZone() {
		return null;
	}

	public String htmTooltipSiteZone() {
		return null;
	}

	public String htmSiteZone() {
		return siteZone == null ? "" : StringEscapeUtils.escapeHtml4(strSiteZone());
	}

	/////////////////////////
	// authorizeApiLoginId //
	/////////////////////////

	/**	L'entité « authorizeApiLoginId »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeApiLoginId;
	@JsonIgnore
	public Wrap<String> authorizeApiLoginIdWrap = new Wrap<String>().p(this).c(String.class).var("authorizeApiLoginId").o(authorizeApiLoginId);

	/**	<br/>L'entité « authorizeApiLoginId »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authorizeApiLoginId">Trouver l'entité authorizeApiLoginId dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeApiLoginId(Wrap<String> c);

	public String getAuthorizeApiLoginId() {
		return authorizeApiLoginId;
	}

	public void setAuthorizeApiLoginId(String authorizeApiLoginId) {
		this.authorizeApiLoginId = authorizeApiLoginId;
		this.authorizeApiLoginIdWrap.alreadyInitialized = true;
	}
	protected SiteConfig authorizeApiLoginIdInit() {
		if(!authorizeApiLoginIdWrap.alreadyInitialized) {
			_authorizeApiLoginId(authorizeApiLoginIdWrap);
			if(authorizeApiLoginId == null)
				setAuthorizeApiLoginId(authorizeApiLoginIdWrap.o);
		}
		authorizeApiLoginIdWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthorizeApiLoginId() {
		return authorizeApiLoginId;
	}

	public String strAuthorizeApiLoginId() {
		return authorizeApiLoginId == null ? "" : authorizeApiLoginId;
	}

	public String jsonAuthorizeApiLoginId() {
		return authorizeApiLoginId == null ? "" : authorizeApiLoginId;
	}

	public String nomAffichageAuthorizeApiLoginId() {
		return null;
	}

	public String htmTooltipAuthorizeApiLoginId() {
		return null;
	}

	public String htmAuthorizeApiLoginId() {
		return authorizeApiLoginId == null ? "" : StringEscapeUtils.escapeHtml4(strAuthorizeApiLoginId());
	}

	/////////////////////////////
	// authorizeTransactionKey //
	/////////////////////////////

	/**	L'entité « authorizeTransactionKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeTransactionKey;
	@JsonIgnore
	public Wrap<String> authorizeTransactionKeyWrap = new Wrap<String>().p(this).c(String.class).var("authorizeTransactionKey").o(authorizeTransactionKey);

	/**	<br/>L'entité « authorizeTransactionKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authorizeTransactionKey">Trouver l'entité authorizeTransactionKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeTransactionKey(Wrap<String> c);

	public String getAuthorizeTransactionKey() {
		return authorizeTransactionKey;
	}

	public void setAuthorizeTransactionKey(String authorizeTransactionKey) {
		this.authorizeTransactionKey = authorizeTransactionKey;
		this.authorizeTransactionKeyWrap.alreadyInitialized = true;
	}
	protected SiteConfig authorizeTransactionKeyInit() {
		if(!authorizeTransactionKeyWrap.alreadyInitialized) {
			_authorizeTransactionKey(authorizeTransactionKeyWrap);
			if(authorizeTransactionKey == null)
				setAuthorizeTransactionKey(authorizeTransactionKeyWrap.o);
		}
		authorizeTransactionKeyWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthorizeTransactionKey() {
		return authorizeTransactionKey;
	}

	public String strAuthorizeTransactionKey() {
		return authorizeTransactionKey == null ? "" : authorizeTransactionKey;
	}

	public String jsonAuthorizeTransactionKey() {
		return authorizeTransactionKey == null ? "" : authorizeTransactionKey;
	}

	public String nomAffichageAuthorizeTransactionKey() {
		return null;
	}

	public String htmTooltipAuthorizeTransactionKey() {
		return null;
	}

	public String htmAuthorizeTransactionKey() {
		return authorizeTransactionKey == null ? "" : StringEscapeUtils.escapeHtml4(strAuthorizeTransactionKey());
	}

	//////////////////////////
	// authorizeEnvironment //
	//////////////////////////

	/**	L'entité « authorizeEnvironment »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeEnvironment;
	@JsonIgnore
	public Wrap<String> authorizeEnvironmentWrap = new Wrap<String>().p(this).c(String.class).var("authorizeEnvironment").o(authorizeEnvironment);

	/**	<br/>L'entité « authorizeEnvironment »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authorizeEnvironment">Trouver l'entité authorizeEnvironment dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeEnvironment(Wrap<String> c);

	public String getAuthorizeEnvironment() {
		return authorizeEnvironment;
	}

	public void setAuthorizeEnvironment(String authorizeEnvironment) {
		this.authorizeEnvironment = authorizeEnvironment;
		this.authorizeEnvironmentWrap.alreadyInitialized = true;
	}
	protected SiteConfig authorizeEnvironmentInit() {
		if(!authorizeEnvironmentWrap.alreadyInitialized) {
			_authorizeEnvironment(authorizeEnvironmentWrap);
			if(authorizeEnvironment == null)
				setAuthorizeEnvironment(authorizeEnvironmentWrap.o);
		}
		authorizeEnvironmentWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthorizeEnvironment() {
		return authorizeEnvironment;
	}

	public String strAuthorizeEnvironment() {
		return authorizeEnvironment == null ? "" : authorizeEnvironment;
	}

	public String jsonAuthorizeEnvironment() {
		return authorizeEnvironment == null ? "" : authorizeEnvironment;
	}

	public String nomAffichageAuthorizeEnvironment() {
		return null;
	}

	public String htmTooltipAuthorizeEnvironment() {
		return null;
	}

	public String htmAuthorizeEnvironment() {
		return authorizeEnvironment == null ? "" : StringEscapeUtils.escapeHtml4(strAuthorizeEnvironment());
	}

	//////////////////
	// authorizeUrl //
	//////////////////

	/**	L'entité « authorizeUrl »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeUrl;
	@JsonIgnore
	public Wrap<String> authorizeUrlWrap = new Wrap<String>().p(this).c(String.class).var("authorizeUrl").o(authorizeUrl);

	/**	<br/>L'entité « authorizeUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authorizeUrl">Trouver l'entité authorizeUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeUrl(Wrap<String> c);

	public String getAuthorizeUrl() {
		return authorizeUrl;
	}

	public void setAuthorizeUrl(String authorizeUrl) {
		this.authorizeUrl = authorizeUrl;
		this.authorizeUrlWrap.alreadyInitialized = true;
	}
	protected SiteConfig authorizeUrlInit() {
		if(!authorizeUrlWrap.alreadyInitialized) {
			_authorizeUrl(authorizeUrlWrap);
			if(authorizeUrl == null)
				setAuthorizeUrl(authorizeUrlWrap.o);
		}
		authorizeUrlWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthorizeUrl() {
		return authorizeUrl;
	}

	public String strAuthorizeUrl() {
		return authorizeUrl == null ? "" : authorizeUrl;
	}

	public String jsonAuthorizeUrl() {
		return authorizeUrl == null ? "" : authorizeUrl;
	}

	public String nomAffichageAuthorizeUrl() {
		return null;
	}

	public String htmTooltipAuthorizeUrl() {
		return null;
	}

	public String htmAuthorizeUrl() {
		return authorizeUrl == null ? "" : StringEscapeUtils.escapeHtml4(strAuthorizeUrl());
	}

	////////////////
	// paymentDay //
	////////////////

	/**	L'entité « paymentDay »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer paymentDay;
	@JsonIgnore
	public Wrap<Integer> paymentDayWrap = new Wrap<Integer>().p(this).c(Integer.class).var("paymentDay").o(paymentDay);

	/**	<br/>L'entité « paymentDay »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentDay">Trouver l'entité paymentDay dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paymentDay(Wrap<Integer> c);

	public Integer getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(Integer paymentDay) {
		this.paymentDay = paymentDay;
		this.paymentDayWrap.alreadyInitialized = true;
	}
	public SiteConfig setPaymentDay(String o) {
		if(NumberUtils.isParsable(o))
			this.paymentDay = Integer.parseInt(o);
		this.paymentDayWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig paymentDayInit() {
		if(!paymentDayWrap.alreadyInitialized) {
			_paymentDay(paymentDayWrap);
			if(paymentDay == null)
				setPaymentDay(paymentDayWrap.o);
		}
		paymentDayWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrPaymentDay() {
		return paymentDay;
	}

	public String strPaymentDay() {
		return paymentDay == null ? "" : paymentDay.toString();
	}

	public String jsonPaymentDay() {
		return paymentDay == null ? "" : paymentDay.toString();
	}

	public String nomAffichagePaymentDay() {
		return null;
	}

	public String htmTooltipPaymentDay() {
		return null;
	}

	public String htmPaymentDay() {
		return paymentDay == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentDay());
	}

	/////////////////
	// paymentNext //
	/////////////////

	/**	L'entité « paymentNext »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate paymentNext;
	@JsonIgnore
	public Wrap<LocalDate> paymentNextWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("paymentNext").o(paymentNext);

	/**	<br/>L'entité « paymentNext »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentNext">Trouver l'entité paymentNext dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paymentNext(Wrap<LocalDate> c);

	public LocalDate getPaymentNext() {
		return paymentNext;
	}

	public void setPaymentNext(LocalDate paymentNext) {
		this.paymentNext = paymentNext;
		this.paymentNextWrap.alreadyInitialized = true;
	}
	public SiteConfig setPaymentNext(Instant o) {
		this.paymentNext = LocalDate.from(o);
		this.paymentNextWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SiteConfig setPaymentNext(String o) {
		this.paymentNext = LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.paymentNextWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig paymentNextInit() {
		if(!paymentNextWrap.alreadyInitialized) {
			_paymentNext(paymentNextWrap);
			if(paymentNext == null)
				setPaymentNext(paymentNextWrap.o);
		}
		paymentNextWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Date solrPaymentNext() {
		return paymentNext == null ? null : Date.from(paymentNext.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public String strPaymentNext() {
		return paymentNext == null ? "" : paymentNext.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonPaymentNext() {
		return paymentNext == null ? "" : paymentNext.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichagePaymentNext() {
		return null;
	}

	public String htmTooltipPaymentNext() {
		return null;
	}

	public String htmPaymentNext() {
		return paymentNext == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentNext());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSiteConfig = false;

	public SiteConfig initDeepSiteConfig(SiteRequestEnUS siteRequest_) {
		if(!alreadyInitializedSiteConfig) {
			alreadyInitializedSiteConfig = true;
			initDeepSiteConfig();
		}
		return (SiteConfig)this;
	}

	public void initDeepSiteConfig() {
		initSiteConfig();
	}

	public void initSiteConfig() {
		configPathInit();
		configInit();
		siteIdentifierInit();
		prefixEscapedInit();
		appPathInit();
		docRootInit();
		companyNameInit();
		domainNameInit();
		siteHostNameInit();
		sitePortInit();
		authRealmInit();
		authResourceInit();
		authSecretInit();
		authSslRequiredInit();
		sslJksPathInit();
		sslJksPasswordInit();
		authUrlInit();
		encryptionSaltInit();
		encryptionPasswordInit();
		siteBaseUrlInit();
		siteDisplayNameInit();
		jdbcDriverClassInit();
		jdbcUsernameInit();
		jdbcPasswordInit();
		jdbcMaxPoolSizeInit();
		jdbcMaxWaitQueueSizeInit();
		jdbcMinPoolSizeInit();
		jdbcMaxStatementsInit();
		jdbcMaxStatementsPerConnectionInit();
		jdbcMaxIdleTimeInit();
		jdbcConnectTimeoutInit();
		jdbcHostInit();
		jdbcPortInit();
		jdbcDatabaseInit();
		solrUrlInit();
		solrUrlComputateInit();
		accountFacebookInit();
		accountTwitterInit();
		accountInstagramInit();
		accountYoutubeInit();
		accountPinterestInit();
		accountEmailInit();
		roleAdminInit();
		emailAdminInit();
		numberExecutorsInit();
		openApiVersionInit();
		apiDescriptionInit();
		apiTitleInit();
		apiTermsServiceInit();
		apiVersionInit();
		apiContactEmailInit();
		apiLicenseNameInit();
		apiLicenseUrlInit();
		apiHostNameInit();
		apiBasePathInit();
		staticBaseUrlInit();
		emailHostInit();
		emailPortInit();
		emailUsernameInit();
		emailPasswordInit();
		emailFromInit();
		emailAuthInit();
		emailSslInit();
		siteZoneInit();
		authorizeApiLoginIdInit();
		authorizeTransactionKeyInit();
		authorizeEnvironmentInit();
		authorizeUrlInit();
		paymentDayInit();
		paymentNextInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSiteConfig(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSiteConfig(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSiteConfig(String var) {
		SiteConfig oSiteConfig = (SiteConfig)this;
		switch(var) {
			case "configPath":
				return oSiteConfig.configPath;
			case "config":
				return oSiteConfig.config;
			case "siteIdentifier":
				return oSiteConfig.siteIdentifier;
			case "prefixEscaped":
				return oSiteConfig.prefixEscaped;
			case "appPath":
				return oSiteConfig.appPath;
			case "docRoot":
				return oSiteConfig.docRoot;
			case "companyName":
				return oSiteConfig.companyName;
			case "domainName":
				return oSiteConfig.domainName;
			case "siteHostName":
				return oSiteConfig.siteHostName;
			case "sitePort":
				return oSiteConfig.sitePort;
			case "authRealm":
				return oSiteConfig.authRealm;
			case "authResource":
				return oSiteConfig.authResource;
			case "authSecret":
				return oSiteConfig.authSecret;
			case "authSslRequired":
				return oSiteConfig.authSslRequired;
			case "sslJksPath":
				return oSiteConfig.sslJksPath;
			case "sslJksPassword":
				return oSiteConfig.sslJksPassword;
			case "authUrl":
				return oSiteConfig.authUrl;
			case "encryptionSalt":
				return oSiteConfig.encryptionSalt;
			case "encryptionPassword":
				return oSiteConfig.encryptionPassword;
			case "siteBaseUrl":
				return oSiteConfig.siteBaseUrl;
			case "siteDisplayName":
				return oSiteConfig.siteDisplayName;
			case "jdbcDriverClass":
				return oSiteConfig.jdbcDriverClass;
			case "jdbcUsername":
				return oSiteConfig.jdbcUsername;
			case "jdbcPassword":
				return oSiteConfig.jdbcPassword;
			case "jdbcMaxPoolSize":
				return oSiteConfig.jdbcMaxPoolSize;
			case "jdbcMaxWaitQueueSize":
				return oSiteConfig.jdbcMaxWaitQueueSize;
			case "jdbcMinPoolSize":
				return oSiteConfig.jdbcMinPoolSize;
			case "jdbcMaxStatements":
				return oSiteConfig.jdbcMaxStatements;
			case "jdbcMaxStatementsPerConnection":
				return oSiteConfig.jdbcMaxStatementsPerConnection;
			case "jdbcMaxIdleTime":
				return oSiteConfig.jdbcMaxIdleTime;
			case "jdbcConnectTimeout":
				return oSiteConfig.jdbcConnectTimeout;
			case "jdbcHost":
				return oSiteConfig.jdbcHost;
			case "jdbcPort":
				return oSiteConfig.jdbcPort;
			case "jdbcDatabase":
				return oSiteConfig.jdbcDatabase;
			case "solrUrl":
				return oSiteConfig.solrUrl;
			case "solrUrlComputate":
				return oSiteConfig.solrUrlComputate;
			case "accountFacebook":
				return oSiteConfig.accountFacebook;
			case "accountTwitter":
				return oSiteConfig.accountTwitter;
			case "accountInstagram":
				return oSiteConfig.accountInstagram;
			case "accountYoutube":
				return oSiteConfig.accountYoutube;
			case "accountPinterest":
				return oSiteConfig.accountPinterest;
			case "accountEmail":
				return oSiteConfig.accountEmail;
			case "roleAdmin":
				return oSiteConfig.roleAdmin;
			case "emailAdmin":
				return oSiteConfig.emailAdmin;
			case "numberExecutors":
				return oSiteConfig.numberExecutors;
			case "openApiVersion":
				return oSiteConfig.openApiVersion;
			case "apiDescription":
				return oSiteConfig.apiDescription;
			case "apiTitle":
				return oSiteConfig.apiTitle;
			case "apiTermsService":
				return oSiteConfig.apiTermsService;
			case "apiVersion":
				return oSiteConfig.apiVersion;
			case "apiContactEmail":
				return oSiteConfig.apiContactEmail;
			case "apiLicenseName":
				return oSiteConfig.apiLicenseName;
			case "apiLicenseUrl":
				return oSiteConfig.apiLicenseUrl;
			case "apiHostName":
				return oSiteConfig.apiHostName;
			case "apiBasePath":
				return oSiteConfig.apiBasePath;
			case "staticBaseUrl":
				return oSiteConfig.staticBaseUrl;
			case "emailHost":
				return oSiteConfig.emailHost;
			case "emailPort":
				return oSiteConfig.emailPort;
			case "emailUsername":
				return oSiteConfig.emailUsername;
			case "emailPassword":
				return oSiteConfig.emailPassword;
			case "emailFrom":
				return oSiteConfig.emailFrom;
			case "emailAuth":
				return oSiteConfig.emailAuth;
			case "emailSsl":
				return oSiteConfig.emailSsl;
			case "siteZone":
				return oSiteConfig.siteZone;
			case "authorizeApiLoginId":
				return oSiteConfig.authorizeApiLoginId;
			case "authorizeTransactionKey":
				return oSiteConfig.authorizeTransactionKey;
			case "authorizeEnvironment":
				return oSiteConfig.authorizeEnvironment;
			case "authorizeUrl":
				return oSiteConfig.authorizeUrl;
			case "paymentDay":
				return oSiteConfig.paymentDay;
			case "paymentNext":
				return oSiteConfig.paymentNext;
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
				o = attributeSiteConfig(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSiteConfig(String var, Object val) {
		SiteConfig oSiteConfig = (SiteConfig)this;
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
					o = defineSiteConfig(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSiteConfig(String var, String val) {
		switch(var) {
			default:
				return null;
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
		if(!(o instanceof SiteConfig))
			return false;
		SiteConfig that = (SiteConfig)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SiteConfig { ");
		sb.append(" }");
		return sb.toString();
	}
}
