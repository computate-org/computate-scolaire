package org.computate.scolaire.enUS.config;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.configuration2.INIConfiguration;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import java.lang.Object;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import java.lang.String;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;

/**	
 *	Loads the properties in the application config file into specific fields. 
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SiteConfigGen<DEV> extends Object {

	////////////////
	// configPath //
	////////////////

	/**	L'entité « configPath »
The path to the config file of the site. 
	 *	 is defined as null before being initialized. 
	 */
	protected String configPath;
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
	protected INIConfiguration config;
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
	protected String siteIdentifier;
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
	protected String prefixEscaped;
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
	protected String appPath;
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
	protected String docRoot;
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
	protected String companyName;
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
	protected String domainName;
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
	protected String siteHostName;
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
	protected Integer sitePort;
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	protected String authRealm;
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
	protected String authResource;
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
	protected String authSecret;
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
	protected String authSslRequired;
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
	protected String sslJksPath;
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
	protected String sslJksPassword;
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
	protected String authUrl;
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
	protected String encryptionSalt;
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
	protected String encryptionPassword;
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
	protected String siteBaseUrl;
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
	protected String siteDisplayName;
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
	protected String jdbcDriverClass;
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
	protected String jdbcUsername;
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
	protected String jdbcPassword;
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
	protected Integer jdbcMaxPoolSize;
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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

	public String nomAffichageJdbcMaxPoolSize() {
		return null;
	}

	public String htmTooltipJdbcMaxPoolSize() {
		return null;
	}

	public String htmJdbcMaxPoolSize() {
		return jdbcMaxPoolSize == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcMaxPoolSize());
	}

	/////////////////////////
	// jdbcInitialPoolSize //
	/////////////////////////

	/**	L'entité « jdbcInitialPoolSize »
The max pool size for the database. 
	 *	 is defined as null before being initialized. 
	 */
	protected Integer jdbcInitialPoolSize;
	public Wrap<Integer> jdbcInitialPoolSizeWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcInitialPoolSize").o(jdbcInitialPoolSize);

	/**	<br/>L'entité « jdbcInitialPoolSize »
The max pool size for the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcInitialPoolSize">Trouver l'entité jdbcInitialPoolSize dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcInitialPoolSize(Wrap<Integer> c);

	public Integer getJdbcInitialPoolSize() {
		return jdbcInitialPoolSize;
	}

	public void setJdbcInitialPoolSize(Integer jdbcInitialPoolSize) {
		this.jdbcInitialPoolSize = jdbcInitialPoolSize;
		this.jdbcInitialPoolSizeWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcInitialPoolSize(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.jdbcInitialPoolSize = Integer.parseInt(o);
		this.jdbcInitialPoolSizeWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcInitialPoolSizeInit() {
		if(!jdbcInitialPoolSizeWrap.alreadyInitialized) {
			_jdbcInitialPoolSize(jdbcInitialPoolSizeWrap);
			if(jdbcInitialPoolSize == null)
				setJdbcInitialPoolSize(jdbcInitialPoolSizeWrap.o);
		}
		jdbcInitialPoolSizeWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcInitialPoolSize() {
		return jdbcInitialPoolSize;
	}

	public String strJdbcInitialPoolSize() {
		return jdbcInitialPoolSize == null ? "" : jdbcInitialPoolSize.toString();
	}

	public String nomAffichageJdbcInitialPoolSize() {
		return null;
	}

	public String htmTooltipJdbcInitialPoolSize() {
		return null;
	}

	public String htmJdbcInitialPoolSize() {
		return jdbcInitialPoolSize == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcInitialPoolSize());
	}

	/////////////////////
	// jdbcMinPoolSize //
	/////////////////////

	/**	L'entité « jdbcMinPoolSize »
The max pool size for the database. 
	 *	 is defined as null before being initialized. 
	 */
	protected Integer jdbcMinPoolSize;
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	protected Integer jdbcMaxStatements;
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	protected Integer jdbcMaxStatementsPerConnection;
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	protected Integer jdbcMaxIdleTime;
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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

	public String nomAffichageJdbcMaxIdleTime() {
		return null;
	}

	public String htmTooltipJdbcMaxIdleTime() {
		return null;
	}

	public String htmJdbcMaxIdleTime() {
		return jdbcMaxIdleTime == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcMaxIdleTime());
	}

	/////////////
	// jdbcUrl //
	/////////////

	/**	L'entité « jdbcUrl »
The JDBC URL to the database. 
	 *	 is defined as null before being initialized. 
	 */
	protected String jdbcUrl;
	public Wrap<String> jdbcUrlWrap = new Wrap<String>().p(this).c(String.class).var("jdbcUrl").o(jdbcUrl);

	/**	<br/>L'entité « jdbcUrl »
The JDBC URL to the database. 
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcUrl">Trouver l'entité jdbcUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcUrl(Wrap<String> c);

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
		this.jdbcUrlWrap.alreadyInitialized = true;
	}
	protected SiteConfig jdbcUrlInit() {
		if(!jdbcUrlWrap.alreadyInitialized) {
			_jdbcUrl(jdbcUrlWrap);
			if(jdbcUrl == null)
				setJdbcUrl(jdbcUrlWrap.o);
		}
		jdbcUrlWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrJdbcUrl() {
		return jdbcUrl;
	}

	public String strJdbcUrl() {
		return jdbcUrl == null ? "" : jdbcUrl;
	}

	public String nomAffichageJdbcUrl() {
		return null;
	}

	public String htmTooltipJdbcUrl() {
		return null;
	}

	public String htmJdbcUrl() {
		return jdbcUrl == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcUrl());
	}

	/////////////
	// solrUrl //
	/////////////

	/**	L'entité « solrUrl »
The URL to the SOLR search engine. 
	 *	 is defined as null before being initialized. 
	 */
	protected String solrUrl;
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

	public String nomAffichageSolrUrl() {
		return null;
	}

	public String htmTooltipSolrUrl() {
		return null;
	}

	public String htmSolrUrl() {
		return solrUrl == null ? "" : StringEscapeUtils.escapeHtml4(strSolrUrl());
	}

	/////////////////////
	// accountFacebook //
	/////////////////////

	/**	L'entité « accountFacebook »
The Facebook account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	protected String accountFacebook;
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
	protected String accountTwitter;
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
	protected String accountInstagram;
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
	protected String accountYoutube;
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
	protected String accountPinterest;
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
	protected String accountEmail;
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
	protected String roleAdmin;
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
	protected String emailAdmin;
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
	protected Integer numberExecutors;
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	protected String openApiVersion;
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
	protected String apiDescription;
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
	protected String apiTitle;
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
	protected String apiTermsService;
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
	protected String apiVersion;
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
	protected String apiContactEmail;
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
	protected String apiLicenseName;
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
	protected String apiLicenseUrl;
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
	protected String apiHostName;
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
	protected String apiBasePath;
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
	protected String staticBaseUrl;
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

	public String nomAffichageStaticBaseUrl() {
		return null;
	}

	public String htmTooltipStaticBaseUrl() {
		return null;
	}

	public String htmStaticBaseUrl() {
		return staticBaseUrl == null ? "" : StringEscapeUtils.escapeHtml4(strStaticBaseUrl());
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
		jdbcInitialPoolSizeInit();
		jdbcMinPoolSizeInit();
		jdbcMaxStatementsInit();
		jdbcMaxStatementsPerConnectionInit();
		jdbcMaxIdleTimeInit();
		jdbcUrlInit();
		solrUrlInit();
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
			case "jdbcInitialPoolSize":
				return oSiteConfig.jdbcInitialPoolSize;
			case "jdbcMinPoolSize":
				return oSiteConfig.jdbcMinPoolSize;
			case "jdbcMaxStatements":
				return oSiteConfig.jdbcMaxStatements;
			case "jdbcMaxStatementsPerConnection":
				return oSiteConfig.jdbcMaxStatementsPerConnection;
			case "jdbcMaxIdleTime":
				return oSiteConfig.jdbcMaxIdleTime;
			case "jdbcUrl":
				return oSiteConfig.jdbcUrl;
			case "solrUrl":
				return oSiteConfig.solrUrl;
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
	// definir //
	/////////////

	public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirSiteConfig(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSiteConfig(String var, String val) {
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
		sb.append("SiteConfig {");
		sb.append(" }");
		return sb.toString();
	}
}