package org.computate.scolaire.enUS.config;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import org.apache.commons.configuration2.INIConfiguration;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * Loads the properties in the application config file into specific fields. 
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SiteConfigGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SiteConfig.class);

	////////////////
	// configPath //
	////////////////

	/**	 The entity configPath
The path to the config file of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String configPath;
	@JsonIgnore
	public Wrap<String> configPathWrap = new Wrap<String>().p(this).c(String.class).var("configPath").o(configPath);

	/**	<br/> The entity configPath
The path to the config file of the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:configPath">Find the entity configPath in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////
	// config //
	////////////

	/**	 The entity config
The INI Configuration Object for the config file. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected INIConfiguration config;
	@JsonIgnore
	public Wrap<INIConfiguration> configWrap = new Wrap<INIConfiguration>().p(this).c(INIConfiguration.class).var("config").o(config);

	/**	<br/> The entity config
The INI Configuration Object for the config file. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:config">Find the entity config in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/**	 The entity siteIdentifier
The name of the principal group of settings of the config for this website. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteIdentifier;
	@JsonIgnore
	public Wrap<String> siteIdentifierWrap = new Wrap<String>().p(this).c(String.class).var("siteIdentifier").o(siteIdentifier);

	/**	<br/> The entity siteIdentifier
The name of the principal group of settings of the config for this website. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteIdentifier">Find the entity siteIdentifier in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	///////////////////
	// prefixEscaped //
	///////////////////

	/**	 The entity prefixEscaped
The already escaped prefix to find the properties of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String prefixEscaped;
	@JsonIgnore
	public Wrap<String> prefixEscapedWrap = new Wrap<String>().p(this).c(String.class).var("prefixEscaped").o(prefixEscaped);

	/**	<br/> The entity prefixEscaped
The already escaped prefix to find the properties of the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:prefixEscaped">Find the entity prefixEscaped in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////
	// appPath //
	/////////////

	/**	 The entity appPath
The path to the project of the site cloned from git. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String appPath;
	@JsonIgnore
	public Wrap<String> appPathWrap = new Wrap<String>().p(this).c(String.class).var("appPath").o(appPath);

	/**	<br/> The entity appPath
The path to the project of the site cloned from git. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:appPath">Find the entity appPath in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////
	// docRoot //
	/////////////

	/**	 The entity docRoot
The path to the docroot for the project. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String docRoot;
	@JsonIgnore
	public Wrap<String> docRootWrap = new Wrap<String>().p(this).c(String.class).var("docRoot").o(docRoot);

	/**	<br/> The entity docRoot
The path to the docroot for the project. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:docRoot">Find the entity docRoot in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////
	// companyName //
	/////////////////

	/**	 The entity companyName
The name of the company. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String companyName;
	@JsonIgnore
	public Wrap<String> companyNameWrap = new Wrap<String>().p(this).c(String.class).var("companyName").o(companyName);

	/**	<br/> The entity companyName
The name of the company. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:companyName">Find the entity companyName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////
	// domainName //
	////////////////

	/**	 The entity domainName
The domain name of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String domainName;
	@JsonIgnore
	public Wrap<String> domainNameWrap = new Wrap<String>().p(this).c(String.class).var("domainName").o(domainName);

	/**	<br/> The entity domainName
The domain name of the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:domainName">Find the entity domainName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////
	// siteHostName //
	//////////////////

	/**	 The entity siteHostName
The host name of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteHostName;
	@JsonIgnore
	public Wrap<String> siteHostNameWrap = new Wrap<String>().p(this).c(String.class).var("siteHostName").o(siteHostName);

	/**	<br/> The entity siteHostName
The host name of the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteHostName">Find the entity siteHostName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////
	// sitePort //
	//////////////

	/**	 The entity sitePort
The port of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer sitePort;
	@JsonIgnore
	public Wrap<Integer> sitePortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("sitePort").o(sitePort);

	/**	<br/> The entity sitePort
The port of the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sitePort">Find the entity sitePort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	///////////////////
	// siteInstances //
	///////////////////

	/**	 The entity siteInstances
The number of instances of the Vertx verticle to deploy. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer siteInstances;
	@JsonIgnore
	public Wrap<Integer> siteInstancesWrap = new Wrap<Integer>().p(this).c(Integer.class).var("siteInstances").o(siteInstances);

	/**	<br/> The entity siteInstances
The number of instances of the Vertx verticle to deploy. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteInstances">Find the entity siteInstances in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteInstances(Wrap<Integer> c);

	public Integer getSiteInstances() {
		return siteInstances;
	}

	public void setSiteInstances(Integer siteInstances) {
		this.siteInstances = siteInstances;
		this.siteInstancesWrap.alreadyInitialized = true;
	}
	public SiteConfig setSiteInstances(String o) {
		if(NumberUtils.isParsable(o))
			this.siteInstances = Integer.parseInt(o);
		this.siteInstancesWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig siteInstancesInit() {
		if(!siteInstancesWrap.alreadyInitialized) {
			_siteInstances(siteInstancesWrap);
			if(siteInstances == null)
				setSiteInstances(siteInstancesWrap.o);
		}
		siteInstancesWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	///////////////
	// authRealm //
	///////////////

	/**	 The entity authRealm
The Keycloak realm of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authRealm;
	@JsonIgnore
	public Wrap<String> authRealmWrap = new Wrap<String>().p(this).c(String.class).var("authRealm").o(authRealm);

	/**	<br/> The entity authRealm
The Keycloak realm of the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authRealm">Find the entity authRealm in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////
	// authResource //
	//////////////////

	/**	 The entity authResource
The Keycloak client ID of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authResource;
	@JsonIgnore
	public Wrap<String> authResourceWrap = new Wrap<String>().p(this).c(String.class).var("authResource").o(authResource);

	/**	<br/> The entity authResource
The Keycloak client ID of the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authResource">Find the entity authResource in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////
	// authSecret //
	////////////////

	/**	 The entity authSecret
The Keycloak client secret of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authSecret;
	@JsonIgnore
	public Wrap<String> authSecretWrap = new Wrap<String>().p(this).c(String.class).var("authSecret").o(authSecret);

	/**	<br/> The entity authSecret
The Keycloak client secret of the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authSecret">Find the entity authSecret in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////////
	// authSslRequired //
	/////////////////////

	/**	 The entity authSslRequired
Whether SSL is required in Keycloak for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authSslRequired;
	@JsonIgnore
	public Wrap<String> authSslRequiredWrap = new Wrap<String>().p(this).c(String.class).var("authSslRequired").o(authSslRequired);

	/**	<br/> The entity authSslRequired
Whether SSL is required in Keycloak for the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authSslRequired">Find the entity authSslRequired in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////
	// sslJksPath //
	////////////////

	/**	 The entity sslJksPath
The path to the Java keystore for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sslJksPath;
	@JsonIgnore
	public Wrap<String> sslJksPathWrap = new Wrap<String>().p(this).c(String.class).var("sslJksPath").o(sslJksPath);

	/**	<br/> The entity sslJksPath
The path to the Java keystore for the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sslJksPath">Find the entity sslJksPath in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////////
	// sslJksPassword //
	////////////////////

	/**	 The entity sslJksPassword
The password for the Java keystore for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sslJksPassword;
	@JsonIgnore
	public Wrap<String> sslJksPasswordWrap = new Wrap<String>().p(this).c(String.class).var("sslJksPassword").o(sslJksPassword);

	/**	<br/> The entity sslJksPassword
The password for the Java keystore for the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sslJksPassword">Find the entity sslJksPassword in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////
	// authUrl //
	/////////////

	/**	 The entity authUrl
The URL to the Keycloak server. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authUrl;
	@JsonIgnore
	public Wrap<String> authUrlWrap = new Wrap<String>().p(this).c(String.class).var("authUrl").o(authUrl);

	/**	<br/> The entity authUrl
The URL to the Keycloak server. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authUrl">Find the entity authUrl in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////////
	// encryptionSalt //
	////////////////////

	/**	 The entity encryptionSalt
The encryption salt to use for all database encryption. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String encryptionSalt;
	@JsonIgnore
	public Wrap<String> encryptionSaltWrap = new Wrap<String>().p(this).c(String.class).var("encryptionSalt").o(encryptionSalt);

	/**	<br/> The entity encryptionSalt
The encryption salt to use for all database encryption. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:encryptionSalt">Find the entity encryptionSalt in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////////////
	// encryptionPassword //
	////////////////////////

	/**	 The entity encryptionPassword
The encryption password to use for all encryption of the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String encryptionPassword;
	@JsonIgnore
	public Wrap<String> encryptionPasswordWrap = new Wrap<String>().p(this).c(String.class).var("encryptionPassword").o(encryptionPassword);

	/**	<br/> The entity encryptionPassword
The encryption password to use for all encryption of the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:encryptionPassword">Find the entity encryptionPassword in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////
	// siteBaseUrl //
	/////////////////

	/**	 The entity siteBaseUrl
The base URL for the URLs of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteBaseUrl;
	@JsonIgnore
	public Wrap<String> siteBaseUrlWrap = new Wrap<String>().p(this).c(String.class).var("siteBaseUrl").o(siteBaseUrl);

	/**	<br/> The entity siteBaseUrl
The base URL for the URLs of the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteBaseUrl">Find the entity siteBaseUrl in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////////
	// siteDisplayName //
	/////////////////////

	/**	 The entity siteDisplayName
The display name of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteDisplayName;
	@JsonIgnore
	public Wrap<String> siteDisplayNameWrap = new Wrap<String>().p(this).c(String.class).var("siteDisplayName").o(siteDisplayName);

	/**	<br/> The entity siteDisplayName
The display name of the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteDisplayName">Find the entity siteDisplayName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////////
	// jdbcDriverClass //
	/////////////////////

	/**	 The entity jdbcDriverClass
The class name of the JDBC driver class for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcDriverClass;
	@JsonIgnore
	public Wrap<String> jdbcDriverClassWrap = new Wrap<String>().p(this).c(String.class).var("jdbcDriverClass").o(jdbcDriverClass);

	/**	<br/> The entity jdbcDriverClass
The class name of the JDBC driver class for the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcDriverClass">Find the entity jdbcDriverClass in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////
	// jdbcUsername //
	//////////////////

	/**	 The entity jdbcUsername
The username for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcUsername;
	@JsonIgnore
	public Wrap<String> jdbcUsernameWrap = new Wrap<String>().p(this).c(String.class).var("jdbcUsername").o(jdbcUsername);

	/**	<br/> The entity jdbcUsername
The username for the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcUsername">Find the entity jdbcUsername in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////
	// jdbcPassword //
	//////////////////

	/**	 The entity jdbcPassword
The password for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcPassword;
	@JsonIgnore
	public Wrap<String> jdbcPasswordWrap = new Wrap<String>().p(this).c(String.class).var("jdbcPassword").o(jdbcPassword);

	/**	<br/> The entity jdbcPassword
The password for the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcPassword">Find the entity jdbcPassword in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////////
	// jdbcMaxPoolSize //
	/////////////////////

	/**	 The entity jdbcMaxPoolSize
The max pool size for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxPoolSize;
	@JsonIgnore
	public Wrap<Integer> jdbcMaxPoolSizeWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMaxPoolSize").o(jdbcMaxPoolSize);

	/**	<br/> The entity jdbcMaxPoolSize
The max pool size for the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxPoolSize">Find the entity jdbcMaxPoolSize in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////////////
	// jdbcMaxWaitQueueSize //
	//////////////////////////

	/**	 The entity jdbcMaxWaitQueueSize
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

	/**	<br/> The entity jdbcMaxWaitQueueSize
Set the maximum connection request allowed in the wait queue, 
	 *	any requests beyond the max size will result in an failure. 
	 *	If the value is set to a negative number then the queue will be unbounded. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxWaitQueueSize">Find the entity jdbcMaxWaitQueueSize in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////////
	// jdbcMinPoolSize //
	/////////////////////

	/**	 The entity jdbcMinPoolSize
The max pool size for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMinPoolSize;
	@JsonIgnore
	public Wrap<Integer> jdbcMinPoolSizeWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMinPoolSize").o(jdbcMinPoolSize);

	/**	<br/> The entity jdbcMinPoolSize
The max pool size for the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMinPoolSize">Find the entity jdbcMinPoolSize in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	///////////////////////
	// jdbcMaxStatements //
	///////////////////////

	/**	 The entity jdbcMaxStatements
The max statements for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxStatements;
	@JsonIgnore
	public Wrap<Integer> jdbcMaxStatementsWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMaxStatements").o(jdbcMaxStatements);

	/**	<br/> The entity jdbcMaxStatements
The max statements for the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxStatements">Find the entity jdbcMaxStatements in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////////////////////////
	// jdbcMaxStatementsPerConnection //
	////////////////////////////////////

	/**	 The entity jdbcMaxStatementsPerConnection
The max statements per connection for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxStatementsPerConnection;
	@JsonIgnore
	public Wrap<Integer> jdbcMaxStatementsPerConnectionWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMaxStatementsPerConnection").o(jdbcMaxStatementsPerConnection);

	/**	<br/> The entity jdbcMaxStatementsPerConnection
The max statements per connection for the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxStatementsPerConnection">Find the entity jdbcMaxStatementsPerConnection in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////////
	// jdbcMaxIdleTime //
	/////////////////////

	/**	 The entity jdbcMaxIdleTime
The max idle time for the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxIdleTime;
	@JsonIgnore
	public Wrap<Integer> jdbcMaxIdleTimeWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMaxIdleTime").o(jdbcMaxIdleTime);

	/**	<br/> The entity jdbcMaxIdleTime
The max idle time for the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxIdleTime">Find the entity jdbcMaxIdleTime in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////////////
	// jdbcConnectTimeout //
	////////////////////////

	/**	 The entity jdbcConnectTimeout
The max idle time for the connection to the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcConnectTimeout;
	@JsonIgnore
	public Wrap<Integer> jdbcConnectTimeoutWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcConnectTimeout").o(jdbcConnectTimeout);

	/**	<br/> The entity jdbcConnectTimeout
The max idle time for the connection to the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcConnectTimeout">Find the entity jdbcConnectTimeout in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////
	// jdbcHost //
	//////////////

	/**	 The entity jdbcHost
The JDBC URL to the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcHost;
	@JsonIgnore
	public Wrap<String> jdbcHostWrap = new Wrap<String>().p(this).c(String.class).var("jdbcHost").o(jdbcHost);

	/**	<br/> The entity jdbcHost
The JDBC URL to the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcHost">Find the entity jdbcHost in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////
	// jdbcPort //
	//////////////

	/**	 The entity jdbcPort
The JDBC URL to the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcPort;
	@JsonIgnore
	public Wrap<Integer> jdbcPortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcPort").o(jdbcPort);

	/**	<br/> The entity jdbcPort
The JDBC URL to the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcPort">Find the entity jdbcPort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////
	// jdbcDatabase //
	//////////////////

	/**	 The entity jdbcDatabase
The JDBC URL to the database. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcDatabase;
	@JsonIgnore
	public Wrap<String> jdbcDatabaseWrap = new Wrap<String>().p(this).c(String.class).var("jdbcDatabase").o(jdbcDatabase);

	/**	<br/> The entity jdbcDatabase
The JDBC URL to the database. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcDatabase">Find the entity jdbcDatabase in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////
	// solrUrl //
	/////////////

	/**	 The entity solrUrl
The URL to the SOLR search engine. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String solrUrl;
	@JsonIgnore
	public Wrap<String> solrUrlWrap = new Wrap<String>().p(this).c(String.class).var("solrUrl").o(solrUrl);

	/**	<br/> The entity solrUrl
The URL to the SOLR search engine. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:solrUrl">Find the entity solrUrl in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////////
	// solrUrlComputate //
	//////////////////////

	/**	 The entity solrUrlComputate
The URL to the SOLR search engine for the computate project. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String solrUrlComputate;
	@JsonIgnore
	public Wrap<String> solrUrlComputateWrap = new Wrap<String>().p(this).c(String.class).var("solrUrlComputate").o(solrUrlComputate);

	/**	<br/> The entity solrUrlComputate
The URL to the SOLR search engine for the computate project. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:solrUrlComputate">Find the entity solrUrlComputate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////////
	// accountFacebook //
	/////////////////////

	/**	 The entity accountFacebook
The Facebook account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountFacebook;
	@JsonIgnore
	public Wrap<String> accountFacebookWrap = new Wrap<String>().p(this).c(String.class).var("accountFacebook").o(accountFacebook);

	/**	<br/> The entity accountFacebook
The Facebook account for the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountFacebook">Find the entity accountFacebook in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////////
	// accountTwitter //
	////////////////////

	/**	 The entity accountTwitter
The Twitter account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountTwitter;
	@JsonIgnore
	public Wrap<String> accountTwitterWrap = new Wrap<String>().p(this).c(String.class).var("accountTwitter").o(accountTwitter);

	/**	<br/> The entity accountTwitter
The Twitter account for the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountTwitter">Find the entity accountTwitter in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////////
	// accountInstagram //
	//////////////////////

	/**	 The entity accountInstagram
The Instagram account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountInstagram;
	@JsonIgnore
	public Wrap<String> accountInstagramWrap = new Wrap<String>().p(this).c(String.class).var("accountInstagram").o(accountInstagram);

	/**	<br/> The entity accountInstagram
The Instagram account for the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountInstagram">Find the entity accountInstagram in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////////
	// accountYoutube //
	////////////////////

	/**	 The entity accountYoutube
The Youtube account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountYoutube;
	@JsonIgnore
	public Wrap<String> accountYoutubeWrap = new Wrap<String>().p(this).c(String.class).var("accountYoutube").o(accountYoutube);

	/**	<br/> The entity accountYoutube
The Youtube account for the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountYoutube">Find the entity accountYoutube in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////////
	// accountPinterest //
	//////////////////////

	/**	 The entity accountPinterest
The Pinterest account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountPinterest;
	@JsonIgnore
	public Wrap<String> accountPinterestWrap = new Wrap<String>().p(this).c(String.class).var("accountPinterest").o(accountPinterest);

	/**	<br/> The entity accountPinterest
The Pinterest account for the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountPinterest">Find the entity accountPinterest in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////
	// accountEmail //
	//////////////////

	/**	 The entity accountEmail
The Email account for the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String accountEmail;
	@JsonIgnore
	public Wrap<String> accountEmailWrap = new Wrap<String>().p(this).c(String.class).var("accountEmail").o(accountEmail);

	/**	<br/> The entity accountEmail
The Email account for the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:accountEmail">Find the entity accountEmail in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	///////////////
	// roleAdmin //
	///////////////

	/**	 The entity roleAdmin
The OpenID Connect role for an administrator. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String roleAdmin;
	@JsonIgnore
	public Wrap<String> roleAdminWrap = new Wrap<String>().p(this).c(String.class).var("roleAdmin").o(roleAdmin);

	/**	<br/> The entity roleAdmin
The OpenID Connect role for an administrator. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:roleAdmin">Find the entity roleAdmin in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////
	// emailAdmin //
	////////////////

	/**	 The entity emailAdmin
The email address for the administrator of the site for the error reports. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailAdmin;
	@JsonIgnore
	public Wrap<String> emailAdminWrap = new Wrap<String>().p(this).c(String.class).var("emailAdmin").o(emailAdmin);

	/**	<br/> The entity emailAdmin
The email address for the administrator of the site for the error reports. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailAdmin">Find the entity emailAdmin in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////////
	// numberExecutors //
	/////////////////////

	/**	 The entity numberExecutors
The number of executors for executing background tasks in the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer numberExecutors;
	@JsonIgnore
	public Wrap<Integer> numberExecutorsWrap = new Wrap<Integer>().p(this).c(Integer.class).var("numberExecutors").o(numberExecutors);

	/**	<br/> The entity numberExecutors
The number of executors for executing background tasks in the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:numberExecutors">Find the entity numberExecutors in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////////
	// openApiVersion //
	////////////////////

	/**	 The entity openApiVersion
The version of OpenAPI used with Vert.x which should probably be 3.0. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String openApiVersion;
	@JsonIgnore
	public Wrap<String> openApiVersionWrap = new Wrap<String>().p(this).c(String.class).var("openApiVersion").o(openApiVersion);

	/**	<br/> The entity openApiVersion
The version of OpenAPI used with Vert.x which should probably be 3.0. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:openApiVersion">Find the entity openApiVersion in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////////
	// apiDescription //
	////////////////////

	/**	 The entity apiDescription
The description of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiDescription;
	@JsonIgnore
	public Wrap<String> apiDescriptionWrap = new Wrap<String>().p(this).c(String.class).var("apiDescription").o(apiDescription);

	/**	<br/> The entity apiDescription
The description of your site API. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiDescription">Find the entity apiDescription in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////
	// apiTitle //
	//////////////

	/**	 The entity apiTitle
The title of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiTitle;
	@JsonIgnore
	public Wrap<String> apiTitleWrap = new Wrap<String>().p(this).c(String.class).var("apiTitle").o(apiTitle);

	/**	<br/> The entity apiTitle
The title of your site API. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiTitle">Find the entity apiTitle in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////////
	// apiTermsService //
	/////////////////////

	/**	 The entity apiTermsService
The terms of service of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiTermsService;
	@JsonIgnore
	public Wrap<String> apiTermsServiceWrap = new Wrap<String>().p(this).c(String.class).var("apiTermsService").o(apiTermsService);

	/**	<br/> The entity apiTermsService
The terms of service of your site API. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiTermsService">Find the entity apiTermsService in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////
	// apiVersion //
	////////////////

	/**	 The entity apiVersion
The version of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiVersion;
	@JsonIgnore
	public Wrap<String> apiVersionWrap = new Wrap<String>().p(this).c(String.class).var("apiVersion").o(apiVersion);

	/**	<br/> The entity apiVersion
The version of your site API. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiVersion">Find the entity apiVersion in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////////
	// apiContactEmail //
	/////////////////////

	/**	 The entity apiContactEmail
The contact email of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiContactEmail;
	@JsonIgnore
	public Wrap<String> apiContactEmailWrap = new Wrap<String>().p(this).c(String.class).var("apiContactEmail").o(apiContactEmail);

	/**	<br/> The entity apiContactEmail
The contact email of your site API. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiContactEmail">Find the entity apiContactEmail in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////////
	// apiLicenseName //
	////////////////////

	/**	 The entity apiLicenseName
The open source license name of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiLicenseName;
	@JsonIgnore
	public Wrap<String> apiLicenseNameWrap = new Wrap<String>().p(this).c(String.class).var("apiLicenseName").o(apiLicenseName);

	/**	<br/> The entity apiLicenseName
The open source license name of your site API. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiLicenseName">Find the entity apiLicenseName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	///////////////////
	// apiLicenseUrl //
	///////////////////

	/**	 The entity apiLicenseUrl
The open source license URL of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiLicenseUrl;
	@JsonIgnore
	public Wrap<String> apiLicenseUrlWrap = new Wrap<String>().p(this).c(String.class).var("apiLicenseUrl").o(apiLicenseUrl);

	/**	<br/> The entity apiLicenseUrl
The open source license URL of your site API. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiLicenseUrl">Find the entity apiLicenseUrl in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////
	// apiHostName //
	/////////////////

	/**	 The entity apiHostName
The host name of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiHostName;
	@JsonIgnore
	public Wrap<String> apiHostNameWrap = new Wrap<String>().p(this).c(String.class).var("apiHostName").o(apiHostName);

	/**	<br/> The entity apiHostName
The host name of your site API. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiHostName">Find the entity apiHostName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	/////////////////
	// apiBasePath //
	/////////////////

	/**	 The entity apiBasePath
The base path of your site API. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiBasePath;
	@JsonIgnore
	public Wrap<String> apiBasePathWrap = new Wrap<String>().p(this).c(String.class).var("apiBasePath").o(apiBasePath);

	/**	<br/> The entity apiBasePath
The base path of your site API. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiBasePath">Find the entity apiBasePath in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	///////////////////
	// staticBaseUrl //
	///////////////////

	/**	 The entity staticBaseUrl
The base URL of your static files. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String staticBaseUrl;
	@JsonIgnore
	public Wrap<String> staticBaseUrlWrap = new Wrap<String>().p(this).c(String.class).var("staticBaseUrl").o(staticBaseUrl);

	/**	<br/> The entity staticBaseUrl
The base URL of your static files. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:staticBaseUrl">Find the entity staticBaseUrl in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	///////////////
	// emailHost //
	///////////////

	/**	 The entity emailHost
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailHost;
	@JsonIgnore
	public Wrap<String> emailHostWrap = new Wrap<String>().p(this).c(String.class).var("emailHost").o(emailHost);

	/**	<br/> The entity emailHost
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailHost">Find the entity emailHost in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	///////////////
	// emailPort //
	///////////////

	/**	 The entity emailPort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer emailPort;
	@JsonIgnore
	public Wrap<Integer> emailPortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("emailPort").o(emailPort);

	/**	<br/> The entity emailPort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailPort">Find the entity emailPort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	///////////////////
	// emailUsername //
	///////////////////

	/**	 The entity emailUsername
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailUsername;
	@JsonIgnore
	public Wrap<String> emailUsernameWrap = new Wrap<String>().p(this).c(String.class).var("emailUsername").o(emailUsername);

	/**	<br/> The entity emailUsername
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailUsername">Find the entity emailUsername in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	///////////////////
	// emailPassword //
	///////////////////

	/**	 The entity emailPassword
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailPassword;
	@JsonIgnore
	public Wrap<String> emailPasswordWrap = new Wrap<String>().p(this).c(String.class).var("emailPassword").o(emailPassword);

	/**	<br/> The entity emailPassword
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailPassword">Find the entity emailPassword in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailFrom">Find the entity emailFrom in Solr</a>
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
	protected SiteConfig emailFromInit() {
		if(!emailFromWrap.alreadyInitialized) {
			_emailFrom(emailFromWrap);
			if(emailFrom == null)
				setEmailFrom(emailFromWrap.o);
		}
		emailFromWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	///////////////
	// emailAuth //
	///////////////

	/**	 The entity emailAuth
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean emailAuth;
	@JsonIgnore
	public Wrap<Boolean> emailAuthWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("emailAuth").o(emailAuth);

	/**	<br/> The entity emailAuth
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailAuth">Find the entity emailAuth in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////
	// emailSsl //
	//////////////

	/**	 The entity emailSsl
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean emailSsl;
	@JsonIgnore
	public Wrap<Boolean> emailSslWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("emailSsl").o(emailSsl);

	/**	<br/> The entity emailSsl
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailSsl">Find the entity emailSsl in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////
	// siteZone //
	//////////////

	/**	 The entity siteZone
The default timezone of the site. 
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteZone;
	@JsonIgnore
	public Wrap<String> siteZoneWrap = new Wrap<String>().p(this).c(String.class).var("siteZone").o(siteZone);

	/**	<br/> The entity siteZone
The default timezone of the site. 
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteZone">Find the entity siteZone in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////////////
	// authorizeApiLoginId1 //
	//////////////////////////

	/**	 The entity authorizeApiLoginId1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeApiLoginId1;
	@JsonIgnore
	public Wrap<String> authorizeApiLoginId1Wrap = new Wrap<String>().p(this).c(String.class).var("authorizeApiLoginId1").o(authorizeApiLoginId1);

	/**	<br/> The entity authorizeApiLoginId1
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authorizeApiLoginId1">Find the entity authorizeApiLoginId1 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _authorizeApiLoginId1(Wrap<String> c);

	public String getAuthorizeApiLoginId1() {
		return authorizeApiLoginId1;
	}

	public void setAuthorizeApiLoginId1(String authorizeApiLoginId1) {
		this.authorizeApiLoginId1 = authorizeApiLoginId1;
		this.authorizeApiLoginId1Wrap.alreadyInitialized = true;
	}
	protected SiteConfig authorizeApiLoginId1Init() {
		if(!authorizeApiLoginId1Wrap.alreadyInitialized) {
			_authorizeApiLoginId1(authorizeApiLoginId1Wrap);
			if(authorizeApiLoginId1 == null)
				setAuthorizeApiLoginId1(authorizeApiLoginId1Wrap.o);
		}
		authorizeApiLoginId1Wrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	//////////////////////////////
	// authorizeTransactionKey1 //
	//////////////////////////////

	/**	 The entity authorizeTransactionKey1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeTransactionKey1;
	@JsonIgnore
	public Wrap<String> authorizeTransactionKey1Wrap = new Wrap<String>().p(this).c(String.class).var("authorizeTransactionKey1").o(authorizeTransactionKey1);

	/**	<br/> The entity authorizeTransactionKey1
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authorizeTransactionKey1">Find the entity authorizeTransactionKey1 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _authorizeTransactionKey1(Wrap<String> c);

	public String getAuthorizeTransactionKey1() {
		return authorizeTransactionKey1;
	}

	public void setAuthorizeTransactionKey1(String authorizeTransactionKey1) {
		this.authorizeTransactionKey1 = authorizeTransactionKey1;
		this.authorizeTransactionKey1Wrap.alreadyInitialized = true;
	}
	protected SiteConfig authorizeTransactionKey1Init() {
		if(!authorizeTransactionKey1Wrap.alreadyInitialized) {
			_authorizeTransactionKey1(authorizeTransactionKey1Wrap);
			if(authorizeTransactionKey1 == null)
				setAuthorizeTransactionKey1(authorizeTransactionKey1Wrap.o);
		}
		authorizeTransactionKey1Wrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	/////////////////////
	// schoolLocation1 //
	/////////////////////

	/**	 The entity schoolLocation1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation1;
	@JsonIgnore
	public Wrap<String> schoolLocation1Wrap = new Wrap<String>().p(this).c(String.class).var("schoolLocation1").o(schoolLocation1);

	/**	<br/> The entity schoolLocation1
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation1">Find the entity schoolLocation1 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolLocation1(Wrap<String> c);

	public String getSchoolLocation1() {
		return schoolLocation1;
	}

	public void setSchoolLocation1(String schoolLocation1) {
		this.schoolLocation1 = schoolLocation1;
		this.schoolLocation1Wrap.alreadyInitialized = true;
	}
	protected SiteConfig schoolLocation1Init() {
		if(!schoolLocation1Wrap.alreadyInitialized) {
			_schoolLocation1(schoolLocation1Wrap);
			if(schoolLocation1 == null)
				setSchoolLocation1(schoolLocation1Wrap.o);
		}
		schoolLocation1Wrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	//////////////////////////
	// authorizeApiLoginId2 //
	//////////////////////////

	/**	 The entity authorizeApiLoginId2
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeApiLoginId2;
	@JsonIgnore
	public Wrap<String> authorizeApiLoginId2Wrap = new Wrap<String>().p(this).c(String.class).var("authorizeApiLoginId2").o(authorizeApiLoginId2);

	/**	<br/> The entity authorizeApiLoginId2
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authorizeApiLoginId2">Find the entity authorizeApiLoginId2 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _authorizeApiLoginId2(Wrap<String> c);

	public String getAuthorizeApiLoginId2() {
		return authorizeApiLoginId2;
	}

	public void setAuthorizeApiLoginId2(String authorizeApiLoginId2) {
		this.authorizeApiLoginId2 = authorizeApiLoginId2;
		this.authorizeApiLoginId2Wrap.alreadyInitialized = true;
	}
	protected SiteConfig authorizeApiLoginId2Init() {
		if(!authorizeApiLoginId2Wrap.alreadyInitialized) {
			_authorizeApiLoginId2(authorizeApiLoginId2Wrap);
			if(authorizeApiLoginId2 == null)
				setAuthorizeApiLoginId2(authorizeApiLoginId2Wrap.o);
		}
		authorizeApiLoginId2Wrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	//////////////////////////////
	// authorizeTransactionKey2 //
	//////////////////////////////

	/**	 The entity authorizeTransactionKey2
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeTransactionKey2;
	@JsonIgnore
	public Wrap<String> authorizeTransactionKey2Wrap = new Wrap<String>().p(this).c(String.class).var("authorizeTransactionKey2").o(authorizeTransactionKey2);

	/**	<br/> The entity authorizeTransactionKey2
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authorizeTransactionKey2">Find the entity authorizeTransactionKey2 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _authorizeTransactionKey2(Wrap<String> c);

	public String getAuthorizeTransactionKey2() {
		return authorizeTransactionKey2;
	}

	public void setAuthorizeTransactionKey2(String authorizeTransactionKey2) {
		this.authorizeTransactionKey2 = authorizeTransactionKey2;
		this.authorizeTransactionKey2Wrap.alreadyInitialized = true;
	}
	protected SiteConfig authorizeTransactionKey2Init() {
		if(!authorizeTransactionKey2Wrap.alreadyInitialized) {
			_authorizeTransactionKey2(authorizeTransactionKey2Wrap);
			if(authorizeTransactionKey2 == null)
				setAuthorizeTransactionKey2(authorizeTransactionKey2Wrap.o);
		}
		authorizeTransactionKey2Wrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	/////////////////////
	// schoolLocation2 //
	/////////////////////

	/**	 The entity schoolLocation2
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation2;
	@JsonIgnore
	public Wrap<String> schoolLocation2Wrap = new Wrap<String>().p(this).c(String.class).var("schoolLocation2").o(schoolLocation2);

	/**	<br/> The entity schoolLocation2
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation2">Find the entity schoolLocation2 in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolLocation2(Wrap<String> c);

	public String getSchoolLocation2() {
		return schoolLocation2;
	}

	public void setSchoolLocation2(String schoolLocation2) {
		this.schoolLocation2 = schoolLocation2;
		this.schoolLocation2Wrap.alreadyInitialized = true;
	}
	protected SiteConfig schoolLocation2Init() {
		if(!schoolLocation2Wrap.alreadyInitialized) {
			_schoolLocation2(schoolLocation2Wrap);
			if(schoolLocation2 == null)
				setSchoolLocation2(schoolLocation2Wrap.o);
		}
		schoolLocation2Wrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	//////////////////////////
	// authorizeEnvironment //
	//////////////////////////

	/**	 The entity authorizeEnvironment
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeEnvironment;
	@JsonIgnore
	public Wrap<String> authorizeEnvironmentWrap = new Wrap<String>().p(this).c(String.class).var("authorizeEnvironment").o(authorizeEnvironment);

	/**	<br/> The entity authorizeEnvironment
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authorizeEnvironment">Find the entity authorizeEnvironment in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	//////////////////
	// authorizeUrl //
	//////////////////

	/**	 The entity authorizeUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeUrl;
	@JsonIgnore
	public Wrap<String> authorizeUrlWrap = new Wrap<String>().p(this).c(String.class).var("authorizeUrl").o(authorizeUrl);

	/**	<br/> The entity authorizeUrl
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authorizeUrl">Find the entity authorizeUrl in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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

	////////////////
	// paymentDay //
	////////////////

	/**	 The entity paymentDay
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer paymentDay;
	@JsonIgnore
	public Wrap<Integer> paymentDayWrap = new Wrap<Integer>().p(this).c(Integer.class).var("paymentDay").o(paymentDay);

	/**	<br/> The entity paymentDay
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentDay">Find the entity paymentDay in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
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
		siteInstancesInit();
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
		authorizeApiLoginId1Init();
		authorizeTransactionKey1Init();
		schoolLocation1Init();
		authorizeApiLoginId2Init();
		authorizeTransactionKey2Init();
		schoolLocation2Init();
		authorizeEnvironmentInit();
		authorizeUrlInit();
		paymentDayInit();
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
			case "siteInstances":
				return oSiteConfig.siteInstances;
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
			case "authorizeApiLoginId1":
				return oSiteConfig.authorizeApiLoginId1;
			case "authorizeTransactionKey1":
				return oSiteConfig.authorizeTransactionKey1;
			case "schoolLocation1":
				return oSiteConfig.schoolLocation1;
			case "authorizeApiLoginId2":
				return oSiteConfig.authorizeApiLoginId2;
			case "authorizeTransactionKey2":
				return oSiteConfig.authorizeTransactionKey2;
			case "schoolLocation2":
				return oSiteConfig.schoolLocation2;
			case "authorizeEnvironment":
				return oSiteConfig.authorizeEnvironment;
			case "authorizeUrl":
				return oSiteConfig.authorizeUrl;
			case "paymentDay":
				return oSiteConfig.paymentDay;
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
