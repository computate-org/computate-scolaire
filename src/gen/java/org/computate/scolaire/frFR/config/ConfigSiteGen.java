package org.computate.scolaire.frFR.config;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.apache.commons.configuration2.INIConfiguration;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
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
 * Charge les propriétés dans le fichier de config de l'application dans des champs spécifiques. 
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ConfigSiteGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ConfigSite.class);

	//////////////////
	// configChemin //
	//////////////////

	/**	L'entité « configChemin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String configChemin;
	@JsonIgnore
	public Couverture<String> configCheminCouverture = new Couverture<String>().p(this).c(String.class).var("configChemin").o(configChemin);

	/**	<br/>L'entité « configChemin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configChemin">Trouver l'entité configChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _configChemin(Couverture<String> c);

	public String getConfigChemin() {
		return configChemin;
	}

	public void setConfigChemin(String configChemin) {
		this.configChemin = configChemin;
		this.configCheminCouverture.dejaInitialise = true;
	}
	protected ConfigSite configCheminInit() {
		if(!configCheminCouverture.dejaInitialise) {
			_configChemin(configCheminCouverture);
			if(configChemin == null)
				setConfigChemin(configCheminCouverture.o);
		}
		configCheminCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////
	// config //
	////////////

	/**	L'entité « config »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected INIConfiguration config;
	@JsonIgnore
	public Couverture<INIConfiguration> configCouverture = new Couverture<INIConfiguration>().p(this).c(INIConfiguration.class).var("config").o(config);

	/**	<br/>L'entité « config »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:config">Trouver l'entité config dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _config(Couverture<INIConfiguration> c);

	public INIConfiguration getConfig() {
		return config;
	}

	public void setConfig(INIConfiguration config) {
		this.config = config;
		this.configCouverture.dejaInitialise = true;
	}
	protected ConfigSite configInit() {
		if(!configCouverture.dejaInitialise) {
			_config(configCouverture);
			if(config == null)
				setConfig(configCouverture.o);
		}
		configCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////////
	// identifiantSite //
	/////////////////////

	/**	L'entité « identifiantSite »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String identifiantSite;
	@JsonIgnore
	public Couverture<String> identifiantSiteCouverture = new Couverture<String>().p(this).c(String.class).var("identifiantSite").o(identifiantSite);

	/**	<br/>L'entité « identifiantSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:identifiantSite">Trouver l'entité identifiantSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _identifiantSite(Couverture<String> c);

	public String getIdentifiantSite() {
		return identifiantSite;
	}

	public void setIdentifiantSite(String identifiantSite) {
		this.identifiantSite = identifiantSite;
		this.identifiantSiteCouverture.dejaInitialise = true;
	}
	protected ConfigSite identifiantSiteInit() {
		if(!identifiantSiteCouverture.dejaInitialise) {
			_identifiantSite(identifiantSiteCouverture);
			if(identifiantSite == null)
				setIdentifiantSite(identifiantSiteCouverture.o);
		}
		identifiantSiteCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////////
	// prefixeEchappe //
	////////////////////

	/**	L'entité « prefixeEchappe »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String prefixeEchappe;
	@JsonIgnore
	public Couverture<String> prefixeEchappeCouverture = new Couverture<String>().p(this).c(String.class).var("prefixeEchappe").o(prefixeEchappe);

	/**	<br/>L'entité « prefixeEchappe »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:prefixeEchappe">Trouver l'entité prefixeEchappe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _prefixeEchappe(Couverture<String> c);

	public String getPrefixeEchappe() {
		return prefixeEchappe;
	}

	public void setPrefixeEchappe(String prefixeEchappe) {
		this.prefixeEchappe = prefixeEchappe;
		this.prefixeEchappeCouverture.dejaInitialise = true;
	}
	protected ConfigSite prefixeEchappeInit() {
		if(!prefixeEchappeCouverture.dejaInitialise) {
			_prefixeEchappe(prefixeEchappeCouverture);
			if(prefixeEchappe == null)
				setPrefixeEchappe(prefixeEchappeCouverture.o);
		}
		prefixeEchappeCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////
	// appliChemin //
	/////////////////

	/**	L'entité « appliChemin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String appliChemin;
	@JsonIgnore
	public Couverture<String> appliCheminCouverture = new Couverture<String>().p(this).c(String.class).var("appliChemin").o(appliChemin);

	/**	<br/>L'entité « appliChemin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:appliChemin">Trouver l'entité appliChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _appliChemin(Couverture<String> c);

	public String getAppliChemin() {
		return appliChemin;
	}

	public void setAppliChemin(String appliChemin) {
		this.appliChemin = appliChemin;
		this.appliCheminCouverture.dejaInitialise = true;
	}
	protected ConfigSite appliCheminInit() {
		if(!appliCheminCouverture.dejaInitialise) {
			_appliChemin(appliCheminCouverture);
			if(appliChemin == null)
				setAppliChemin(appliCheminCouverture.o);
		}
		appliCheminCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////////
	// racineDocument //
	////////////////////

	/**	L'entité « racineDocument »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String racineDocument;
	@JsonIgnore
	public Couverture<String> racineDocumentCouverture = new Couverture<String>().p(this).c(String.class).var("racineDocument").o(racineDocument);

	/**	<br/>L'entité « racineDocument »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:racineDocument">Trouver l'entité racineDocument dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _racineDocument(Couverture<String> c);

	public String getRacineDocument() {
		return racineDocument;
	}

	public void setRacineDocument(String racineDocument) {
		this.racineDocument = racineDocument;
		this.racineDocumentCouverture.dejaInitialise = true;
	}
	protected ConfigSite racineDocumentInit() {
		if(!racineDocumentCouverture.dejaInitialise) {
			_racineDocument(racineDocumentCouverture);
			if(racineDocument == null)
				setRacineDocument(racineDocumentCouverture.o);
		}
		racineDocumentCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	///////////////////
	// nomEntreprise //
	///////////////////

	/**	L'entité « nomEntreprise »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String nomEntreprise;
	@JsonIgnore
	public Couverture<String> nomEntrepriseCouverture = new Couverture<String>().p(this).c(String.class).var("nomEntreprise").o(nomEntreprise);

	/**	<br/>L'entité « nomEntreprise »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:nomEntreprise">Trouver l'entité nomEntreprise dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _nomEntreprise(Couverture<String> c);

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
		this.nomEntrepriseCouverture.dejaInitialise = true;
	}
	protected ConfigSite nomEntrepriseInit() {
		if(!nomEntrepriseCouverture.dejaInitialise) {
			_nomEntreprise(nomEntrepriseCouverture);
			if(nomEntreprise == null)
				setNomEntreprise(nomEntrepriseCouverture.o);
		}
		nomEntrepriseCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////
	// nomDomaine //
	////////////////

	/**	L'entité « nomDomaine »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String nomDomaine;
	@JsonIgnore
	public Couverture<String> nomDomaineCouverture = new Couverture<String>().p(this).c(String.class).var("nomDomaine").o(nomDomaine);

	/**	<br/>L'entité « nomDomaine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:nomDomaine">Trouver l'entité nomDomaine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _nomDomaine(Couverture<String> c);

	public String getNomDomaine() {
		return nomDomaine;
	}

	public void setNomDomaine(String nomDomaine) {
		this.nomDomaine = nomDomaine;
		this.nomDomaineCouverture.dejaInitialise = true;
	}
	protected ConfigSite nomDomaineInit() {
		if(!nomDomaineCouverture.dejaInitialise) {
			_nomDomaine(nomDomaineCouverture);
			if(nomDomaine == null)
				setNomDomaine(nomDomaineCouverture.o);
		}
		nomDomaineCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////
	// siteNomHote //
	/////////////////

	/**	L'entité « siteNomHote »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteNomHote;
	@JsonIgnore
	public Couverture<String> siteNomHoteCouverture = new Couverture<String>().p(this).c(String.class).var("siteNomHote").o(siteNomHote);

	/**	<br/>L'entité « siteNomHote »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteNomHote">Trouver l'entité siteNomHote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteNomHote(Couverture<String> c);

	public String getSiteNomHote() {
		return siteNomHote;
	}

	public void setSiteNomHote(String siteNomHote) {
		this.siteNomHote = siteNomHote;
		this.siteNomHoteCouverture.dejaInitialise = true;
	}
	protected ConfigSite siteNomHoteInit() {
		if(!siteNomHoteCouverture.dejaInitialise) {
			_siteNomHote(siteNomHoteCouverture);
			if(siteNomHote == null)
				setSiteNomHote(siteNomHoteCouverture.o);
		}
		siteNomHoteCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////
	// sitePort //
	//////////////

	/**	L'entité « sitePort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer sitePort;
	@JsonIgnore
	public Couverture<Integer> sitePortCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("sitePort").o(sitePort);

	/**	<br/>L'entité « sitePort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sitePort">Trouver l'entité sitePort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sitePort(Couverture<Integer> c);

	public Integer getSitePort() {
		return sitePort;
	}

	public void setSitePort(Integer sitePort) {
		this.sitePort = sitePort;
		this.sitePortCouverture.dejaInitialise = true;
	}
	public ConfigSite setSitePort(String o) {
		if(NumberUtils.isParsable(o))
			this.sitePort = Integer.parseInt(o);
		this.sitePortCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite sitePortInit() {
		if(!sitePortCouverture.dejaInitialise) {
			_sitePort(sitePortCouverture);
			if(sitePort == null)
				setSitePort(sitePortCouverture.o);
		}
		sitePortCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////
	// authRoyaume //
	/////////////////

	/**	L'entité « authRoyaume »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authRoyaume;
	@JsonIgnore
	public Couverture<String> authRoyaumeCouverture = new Couverture<String>().p(this).c(String.class).var("authRoyaume").o(authRoyaume);

	/**	<br/>L'entité « authRoyaume »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authRoyaume">Trouver l'entité authRoyaume dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authRoyaume(Couverture<String> c);

	public String getAuthRoyaume() {
		return authRoyaume;
	}

	public void setAuthRoyaume(String authRoyaume) {
		this.authRoyaume = authRoyaume;
		this.authRoyaumeCouverture.dejaInitialise = true;
	}
	protected ConfigSite authRoyaumeInit() {
		if(!authRoyaumeCouverture.dejaInitialise) {
			_authRoyaume(authRoyaumeCouverture);
			if(authRoyaume == null)
				setAuthRoyaume(authRoyaumeCouverture.o);
		}
		authRoyaumeCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	///////////////////
	// authRessource //
	///////////////////

	/**	L'entité « authRessource »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authRessource;
	@JsonIgnore
	public Couverture<String> authRessourceCouverture = new Couverture<String>().p(this).c(String.class).var("authRessource").o(authRessource);

	/**	<br/>L'entité « authRessource »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authRessource">Trouver l'entité authRessource dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authRessource(Couverture<String> c);

	public String getAuthRessource() {
		return authRessource;
	}

	public void setAuthRessource(String authRessource) {
		this.authRessource = authRessource;
		this.authRessourceCouverture.dejaInitialise = true;
	}
	protected ConfigSite authRessourceInit() {
		if(!authRessourceCouverture.dejaInitialise) {
			_authRessource(authRessourceCouverture);
			if(authRessource == null)
				setAuthRessource(authRessourceCouverture.o);
		}
		authRessourceCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////
	// authSecret //
	////////////////

	/**	L'entité « authSecret »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authSecret;
	@JsonIgnore
	public Couverture<String> authSecretCouverture = new Couverture<String>().p(this).c(String.class).var("authSecret").o(authSecret);

	/**	<br/>L'entité « authSecret »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authSecret">Trouver l'entité authSecret dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authSecret(Couverture<String> c);

	public String getAuthSecret() {
		return authSecret;
	}

	public void setAuthSecret(String authSecret) {
		this.authSecret = authSecret;
		this.authSecretCouverture.dejaInitialise = true;
	}
	protected ConfigSite authSecretInit() {
		if(!authSecretCouverture.dejaInitialise) {
			_authSecret(authSecretCouverture);
			if(authSecret == null)
				setAuthSecret(authSecretCouverture.o);
		}
		authSecretCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	///////////////////
	// authSslRequis //
	///////////////////

	/**	L'entité « authSslRequis »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authSslRequis;
	@JsonIgnore
	public Couverture<String> authSslRequisCouverture = new Couverture<String>().p(this).c(String.class).var("authSslRequis").o(authSslRequis);

	/**	<br/>L'entité « authSslRequis »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authSslRequis">Trouver l'entité authSslRequis dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authSslRequis(Couverture<String> c);

	public String getAuthSslRequis() {
		return authSslRequis;
	}

	public void setAuthSslRequis(String authSslRequis) {
		this.authSslRequis = authSslRequis;
		this.authSslRequisCouverture.dejaInitialise = true;
	}
	protected ConfigSite authSslRequisInit() {
		if(!authSslRequisCouverture.dejaInitialise) {
			_authSslRequis(authSslRequisCouverture);
			if(authSslRequis == null)
				setAuthSslRequis(authSslRequisCouverture.o);
		}
		authSslRequisCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////
	// sslJksChemin //
	//////////////////

	/**	L'entité « sslJksChemin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sslJksChemin;
	@JsonIgnore
	public Couverture<String> sslJksCheminCouverture = new Couverture<String>().p(this).c(String.class).var("sslJksChemin").o(sslJksChemin);

	/**	<br/>L'entité « sslJksChemin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sslJksChemin">Trouver l'entité sslJksChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sslJksChemin(Couverture<String> c);

	public String getSslJksChemin() {
		return sslJksChemin;
	}

	public void setSslJksChemin(String sslJksChemin) {
		this.sslJksChemin = sslJksChemin;
		this.sslJksCheminCouverture.dejaInitialise = true;
	}
	protected ConfigSite sslJksCheminInit() {
		if(!sslJksCheminCouverture.dejaInitialise) {
			_sslJksChemin(sslJksCheminCouverture);
			if(sslJksChemin == null)
				setSslJksChemin(sslJksCheminCouverture.o);
		}
		sslJksCheminCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////
	// sslJksMotDePasse //
	//////////////////////

	/**	L'entité « sslJksMotDePasse »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sslJksMotDePasse;
	@JsonIgnore
	public Couverture<String> sslJksMotDePasseCouverture = new Couverture<String>().p(this).c(String.class).var("sslJksMotDePasse").o(sslJksMotDePasse);

	/**	<br/>L'entité « sslJksMotDePasse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sslJksMotDePasse">Trouver l'entité sslJksMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sslJksMotDePasse(Couverture<String> c);

	public String getSslJksMotDePasse() {
		return sslJksMotDePasse;
	}

	public void setSslJksMotDePasse(String sslJksMotDePasse) {
		this.sslJksMotDePasse = sslJksMotDePasse;
		this.sslJksMotDePasseCouverture.dejaInitialise = true;
	}
	protected ConfigSite sslJksMotDePasseInit() {
		if(!sslJksMotDePasseCouverture.dejaInitialise) {
			_sslJksMotDePasse(sslJksMotDePasseCouverture);
			if(sslJksMotDePasse == null)
				setSslJksMotDePasse(sslJksMotDePasseCouverture.o);
		}
		sslJksMotDePasseCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////
	// authUrl //
	/////////////

	/**	L'entité « authUrl »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authUrl;
	@JsonIgnore
	public Couverture<String> authUrlCouverture = new Couverture<String>().p(this).c(String.class).var("authUrl").o(authUrl);

	/**	<br/>L'entité « authUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authUrl">Trouver l'entité authUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authUrl(Couverture<String> c);

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
		this.authUrlCouverture.dejaInitialise = true;
	}
	protected ConfigSite authUrlInit() {
		if(!authUrlCouverture.dejaInitialise) {
			_authUrl(authUrlCouverture);
			if(authUrl == null)
				setAuthUrl(authUrlCouverture.o);
		}
		authUrlCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////
	// cryptageSel //
	/////////////////

	/**	L'entité « cryptageSel »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String cryptageSel;
	@JsonIgnore
	public Couverture<String> cryptageSelCouverture = new Couverture<String>().p(this).c(String.class).var("cryptageSel").o(cryptageSel);

	/**	<br/>L'entité « cryptageSel »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cryptageSel">Trouver l'entité cryptageSel dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageSel(Couverture<String> c);

	public String getCryptageSel() {
		return cryptageSel;
	}

	public void setCryptageSel(String cryptageSel) {
		this.cryptageSel = cryptageSel;
		this.cryptageSelCouverture.dejaInitialise = true;
	}
	protected ConfigSite cryptageSelInit() {
		if(!cryptageSelCouverture.dejaInitialise) {
			_cryptageSel(cryptageSelCouverture);
			if(cryptageSel == null)
				setCryptageSel(cryptageSelCouverture.o);
		}
		cryptageSelCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////////////
	// cryptageMotDePasse //
	////////////////////////

	/**	L'entité « cryptageMotDePasse »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String cryptageMotDePasse;
	@JsonIgnore
	public Couverture<String> cryptageMotDePasseCouverture = new Couverture<String>().p(this).c(String.class).var("cryptageMotDePasse").o(cryptageMotDePasse);

	/**	<br/>L'entité « cryptageMotDePasse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cryptageMotDePasse">Trouver l'entité cryptageMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageMotDePasse(Couverture<String> c);

	public String getCryptageMotDePasse() {
		return cryptageMotDePasse;
	}

	public void setCryptageMotDePasse(String cryptageMotDePasse) {
		this.cryptageMotDePasse = cryptageMotDePasse;
		this.cryptageMotDePasseCouverture.dejaInitialise = true;
	}
	protected ConfigSite cryptageMotDePasseInit() {
		if(!cryptageMotDePasseCouverture.dejaInitialise) {
			_cryptageMotDePasse(cryptageMotDePasseCouverture);
			if(cryptageMotDePasse == null)
				setCryptageMotDePasse(cryptageMotDePasseCouverture.o);
		}
		cryptageMotDePasseCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////
	// siteUrlBase //
	/////////////////

	/**	L'entité « siteUrlBase »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteUrlBase;
	@JsonIgnore
	public Couverture<String> siteUrlBaseCouverture = new Couverture<String>().p(this).c(String.class).var("siteUrlBase").o(siteUrlBase);

	/**	<br/>L'entité « siteUrlBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteUrlBase">Trouver l'entité siteUrlBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteUrlBase(Couverture<String> c);

	public String getSiteUrlBase() {
		return siteUrlBase;
	}

	public void setSiteUrlBase(String siteUrlBase) {
		this.siteUrlBase = siteUrlBase;
		this.siteUrlBaseCouverture.dejaInitialise = true;
	}
	protected ConfigSite siteUrlBaseInit() {
		if(!siteUrlBaseCouverture.dejaInitialise) {
			_siteUrlBase(siteUrlBaseCouverture);
			if(siteUrlBase == null)
				setSiteUrlBase(siteUrlBaseCouverture.o);
		}
		siteUrlBaseCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////
	// siteNomAffichage //
	//////////////////////

	/**	L'entité « siteNomAffichage »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteNomAffichage;
	@JsonIgnore
	public Couverture<String> siteNomAffichageCouverture = new Couverture<String>().p(this).c(String.class).var("siteNomAffichage").o(siteNomAffichage);

	/**	<br/>L'entité « siteNomAffichage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteNomAffichage">Trouver l'entité siteNomAffichage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteNomAffichage(Couverture<String> c);

	public String getSiteNomAffichage() {
		return siteNomAffichage;
	}

	public void setSiteNomAffichage(String siteNomAffichage) {
		this.siteNomAffichage = siteNomAffichage;
		this.siteNomAffichageCouverture.dejaInitialise = true;
	}
	protected ConfigSite siteNomAffichageInit() {
		if(!siteNomAffichageCouverture.dejaInitialise) {
			_siteNomAffichage(siteNomAffichageCouverture);
			if(siteNomAffichage == null)
				setSiteNomAffichage(siteNomAffichageCouverture.o);
		}
		siteNomAffichageCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////
	// jdbcClassePilote //
	//////////////////////

	/**	L'entité « jdbcClassePilote »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcClassePilote;
	@JsonIgnore
	public Couverture<String> jdbcClassePiloteCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcClassePilote").o(jdbcClassePilote);

	/**	<br/>L'entité « jdbcClassePilote »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcClassePilote">Trouver l'entité jdbcClassePilote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcClassePilote(Couverture<String> c);

	public String getJdbcClassePilote() {
		return jdbcClassePilote;
	}

	public void setJdbcClassePilote(String jdbcClassePilote) {
		this.jdbcClassePilote = jdbcClassePilote;
		this.jdbcClassePiloteCouverture.dejaInitialise = true;
	}
	protected ConfigSite jdbcClassePiloteInit() {
		if(!jdbcClassePiloteCouverture.dejaInitialise) {
			_jdbcClassePilote(jdbcClassePiloteCouverture);
			if(jdbcClassePilote == null)
				setJdbcClassePilote(jdbcClassePiloteCouverture.o);
		}
		jdbcClassePiloteCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////////
	// jdbcUtilisateur //
	/////////////////////

	/**	L'entité « jdbcUtilisateur »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcUtilisateur;
	@JsonIgnore
	public Couverture<String> jdbcUtilisateurCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcUtilisateur").o(jdbcUtilisateur);

	/**	<br/>L'entité « jdbcUtilisateur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcUtilisateur">Trouver l'entité jdbcUtilisateur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcUtilisateur(Couverture<String> c);

	public String getJdbcUtilisateur() {
		return jdbcUtilisateur;
	}

	public void setJdbcUtilisateur(String jdbcUtilisateur) {
		this.jdbcUtilisateur = jdbcUtilisateur;
		this.jdbcUtilisateurCouverture.dejaInitialise = true;
	}
	protected ConfigSite jdbcUtilisateurInit() {
		if(!jdbcUtilisateurCouverture.dejaInitialise) {
			_jdbcUtilisateur(jdbcUtilisateurCouverture);
			if(jdbcUtilisateur == null)
				setJdbcUtilisateur(jdbcUtilisateurCouverture.o);
		}
		jdbcUtilisateurCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////////
	// jdbcMotDePasse //
	////////////////////

	/**	L'entité « jdbcMotDePasse »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcMotDePasse;
	@JsonIgnore
	public Couverture<String> jdbcMotDePasseCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcMotDePasse").o(jdbcMotDePasse);

	/**	<br/>L'entité « jdbcMotDePasse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcMotDePasse">Trouver l'entité jdbcMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMotDePasse(Couverture<String> c);

	public String getJdbcMotDePasse() {
		return jdbcMotDePasse;
	}

	public void setJdbcMotDePasse(String jdbcMotDePasse) {
		this.jdbcMotDePasse = jdbcMotDePasse;
		this.jdbcMotDePasseCouverture.dejaInitialise = true;
	}
	protected ConfigSite jdbcMotDePasseInit() {
		if(!jdbcMotDePasseCouverture.dejaInitialise) {
			_jdbcMotDePasse(jdbcMotDePasseCouverture);
			if(jdbcMotDePasse == null)
				setJdbcMotDePasse(jdbcMotDePasseCouverture.o);
		}
		jdbcMotDePasseCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////////
	// jdbcTailleMaxPiscine //
	//////////////////////////

	/**	L'entité « jdbcTailleMaxPiscine »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcTailleMaxPiscine;
	@JsonIgnore
	public Couverture<Integer> jdbcTailleMaxPiscineCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcTailleMaxPiscine").o(jdbcTailleMaxPiscine);

	/**	<br/>L'entité « jdbcTailleMaxPiscine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcTailleMaxPiscine">Trouver l'entité jdbcTailleMaxPiscine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcTailleMaxPiscine(Couverture<Integer> c);

	public Integer getJdbcTailleMaxPiscine() {
		return jdbcTailleMaxPiscine;
	}

	public void setJdbcTailleMaxPiscine(Integer jdbcTailleMaxPiscine) {
		this.jdbcTailleMaxPiscine = jdbcTailleMaxPiscine;
		this.jdbcTailleMaxPiscineCouverture.dejaInitialise = true;
	}
	public ConfigSite setJdbcTailleMaxPiscine(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcTailleMaxPiscine = Integer.parseInt(o);
		this.jdbcTailleMaxPiscineCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite jdbcTailleMaxPiscineInit() {
		if(!jdbcTailleMaxPiscineCouverture.dejaInitialise) {
			_jdbcTailleMaxPiscine(jdbcTailleMaxPiscineCouverture);
			if(jdbcTailleMaxPiscine == null)
				setJdbcTailleMaxPiscine(jdbcTailleMaxPiscineCouverture.o);
		}
		jdbcTailleMaxPiscineCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////////////
	// jdbcMaxFileAttente //
	////////////////////////

	/**	L'entité « jdbcMaxFileAttente »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxFileAttente;
	@JsonIgnore
	public Couverture<Integer> jdbcMaxFileAttenteCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcMaxFileAttente").o(jdbcMaxFileAttente);

	/**	<br/>L'entité « jdbcMaxFileAttente »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcMaxFileAttente">Trouver l'entité jdbcMaxFileAttente dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMaxFileAttente(Couverture<Integer> c);

	public Integer getJdbcMaxFileAttente() {
		return jdbcMaxFileAttente;
	}

	public void setJdbcMaxFileAttente(Integer jdbcMaxFileAttente) {
		this.jdbcMaxFileAttente = jdbcMaxFileAttente;
		this.jdbcMaxFileAttenteCouverture.dejaInitialise = true;
	}
	public ConfigSite setJdbcMaxFileAttente(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcMaxFileAttente = Integer.parseInt(o);
		this.jdbcMaxFileAttenteCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite jdbcMaxFileAttenteInit() {
		if(!jdbcMaxFileAttenteCouverture.dejaInitialise) {
			_jdbcMaxFileAttente(jdbcMaxFileAttenteCouverture);
			if(jdbcMaxFileAttente == null)
				setJdbcMaxFileAttente(jdbcMaxFileAttenteCouverture.o);
		}
		jdbcMaxFileAttenteCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////////
	// jdbcTailleMinPiscine //
	//////////////////////////

	/**	L'entité « jdbcTailleMinPiscine »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcTailleMinPiscine;
	@JsonIgnore
	public Couverture<Integer> jdbcTailleMinPiscineCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcTailleMinPiscine").o(jdbcTailleMinPiscine);

	/**	<br/>L'entité « jdbcTailleMinPiscine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcTailleMinPiscine">Trouver l'entité jdbcTailleMinPiscine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcTailleMinPiscine(Couverture<Integer> c);

	public Integer getJdbcTailleMinPiscine() {
		return jdbcTailleMinPiscine;
	}

	public void setJdbcTailleMinPiscine(Integer jdbcTailleMinPiscine) {
		this.jdbcTailleMinPiscine = jdbcTailleMinPiscine;
		this.jdbcTailleMinPiscineCouverture.dejaInitialise = true;
	}
	public ConfigSite setJdbcTailleMinPiscine(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcTailleMinPiscine = Integer.parseInt(o);
		this.jdbcTailleMinPiscineCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite jdbcTailleMinPiscineInit() {
		if(!jdbcTailleMinPiscineCouverture.dejaInitialise) {
			_jdbcTailleMinPiscine(jdbcTailleMinPiscineCouverture);
			if(jdbcTailleMinPiscine == null)
				setJdbcTailleMinPiscine(jdbcTailleMinPiscineCouverture.o);
		}
		jdbcTailleMinPiscineCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////////////
	// jdbcMaxDeclarations //
	/////////////////////////

	/**	L'entité « jdbcMaxDeclarations »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxDeclarations;
	@JsonIgnore
	public Couverture<Integer> jdbcMaxDeclarationsCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcMaxDeclarations").o(jdbcMaxDeclarations);

	/**	<br/>L'entité « jdbcMaxDeclarations »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcMaxDeclarations">Trouver l'entité jdbcMaxDeclarations dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMaxDeclarations(Couverture<Integer> c);

	public Integer getJdbcMaxDeclarations() {
		return jdbcMaxDeclarations;
	}

	public void setJdbcMaxDeclarations(Integer jdbcMaxDeclarations) {
		this.jdbcMaxDeclarations = jdbcMaxDeclarations;
		this.jdbcMaxDeclarationsCouverture.dejaInitialise = true;
	}
	public ConfigSite setJdbcMaxDeclarations(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcMaxDeclarations = Integer.parseInt(o);
		this.jdbcMaxDeclarationsCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite jdbcMaxDeclarationsInit() {
		if(!jdbcMaxDeclarationsCouverture.dejaInitialise) {
			_jdbcMaxDeclarations(jdbcMaxDeclarationsCouverture);
			if(jdbcMaxDeclarations == null)
				setJdbcMaxDeclarations(jdbcMaxDeclarationsCouverture.o);
		}
		jdbcMaxDeclarationsCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////////////////////////
	// jdbcMaxDeclarationsParConnexion //
	/////////////////////////////////////

	/**	L'entité « jdbcMaxDeclarationsParConnexion »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxDeclarationsParConnexion;
	@JsonIgnore
	public Couverture<Integer> jdbcMaxDeclarationsParConnexionCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcMaxDeclarationsParConnexion").o(jdbcMaxDeclarationsParConnexion);

	/**	<br/>L'entité « jdbcMaxDeclarationsParConnexion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcMaxDeclarationsParConnexion">Trouver l'entité jdbcMaxDeclarationsParConnexion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMaxDeclarationsParConnexion(Couverture<Integer> c);

	public Integer getJdbcMaxDeclarationsParConnexion() {
		return jdbcMaxDeclarationsParConnexion;
	}

	public void setJdbcMaxDeclarationsParConnexion(Integer jdbcMaxDeclarationsParConnexion) {
		this.jdbcMaxDeclarationsParConnexion = jdbcMaxDeclarationsParConnexion;
		this.jdbcMaxDeclarationsParConnexionCouverture.dejaInitialise = true;
	}
	public ConfigSite setJdbcMaxDeclarationsParConnexion(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcMaxDeclarationsParConnexion = Integer.parseInt(o);
		this.jdbcMaxDeclarationsParConnexionCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite jdbcMaxDeclarationsParConnexionInit() {
		if(!jdbcMaxDeclarationsParConnexionCouverture.dejaInitialise) {
			_jdbcMaxDeclarationsParConnexion(jdbcMaxDeclarationsParConnexionCouverture);
			if(jdbcMaxDeclarationsParConnexion == null)
				setJdbcMaxDeclarationsParConnexion(jdbcMaxDeclarationsParConnexionCouverture.o);
		}
		jdbcMaxDeclarationsParConnexionCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////////////////
	// jdbcTempsInactiviteMax //
	////////////////////////////

	/**	L'entité « jdbcTempsInactiviteMax »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcTempsInactiviteMax;
	@JsonIgnore
	public Couverture<Integer> jdbcTempsInactiviteMaxCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcTempsInactiviteMax").o(jdbcTempsInactiviteMax);

	/**	<br/>L'entité « jdbcTempsInactiviteMax »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcTempsInactiviteMax">Trouver l'entité jdbcTempsInactiviteMax dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcTempsInactiviteMax(Couverture<Integer> c);

	public Integer getJdbcTempsInactiviteMax() {
		return jdbcTempsInactiviteMax;
	}

	public void setJdbcTempsInactiviteMax(Integer jdbcTempsInactiviteMax) {
		this.jdbcTempsInactiviteMax = jdbcTempsInactiviteMax;
		this.jdbcTempsInactiviteMaxCouverture.dejaInitialise = true;
	}
	public ConfigSite setJdbcTempsInactiviteMax(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcTempsInactiviteMax = Integer.parseInt(o);
		this.jdbcTempsInactiviteMaxCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite jdbcTempsInactiviteMaxInit() {
		if(!jdbcTempsInactiviteMaxCouverture.dejaInitialise) {
			_jdbcTempsInactiviteMax(jdbcTempsInactiviteMaxCouverture);
			if(jdbcTempsInactiviteMax == null)
				setJdbcTempsInactiviteMax(jdbcTempsInactiviteMaxCouverture.o);
		}
		jdbcTempsInactiviteMaxCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////////////
	// jdbcDelaiConnexion //
	////////////////////////

	/**	L'entité « jdbcDelaiConnexion »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcDelaiConnexion;
	@JsonIgnore
	public Couverture<Integer> jdbcDelaiConnexionCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcDelaiConnexion").o(jdbcDelaiConnexion);

	/**	<br/>L'entité « jdbcDelaiConnexion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcDelaiConnexion">Trouver l'entité jdbcDelaiConnexion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcDelaiConnexion(Couverture<Integer> c);

	public Integer getJdbcDelaiConnexion() {
		return jdbcDelaiConnexion;
	}

	public void setJdbcDelaiConnexion(Integer jdbcDelaiConnexion) {
		this.jdbcDelaiConnexion = jdbcDelaiConnexion;
		this.jdbcDelaiConnexionCouverture.dejaInitialise = true;
	}
	public ConfigSite setJdbcDelaiConnexion(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcDelaiConnexion = Integer.parseInt(o);
		this.jdbcDelaiConnexionCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite jdbcDelaiConnexionInit() {
		if(!jdbcDelaiConnexionCouverture.dejaInitialise) {
			_jdbcDelaiConnexion(jdbcDelaiConnexionCouverture);
			if(jdbcDelaiConnexion == null)
				setJdbcDelaiConnexion(jdbcDelaiConnexionCouverture.o);
		}
		jdbcDelaiConnexionCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////
	// jdbcHote //
	//////////////

	/**	L'entité « jdbcHote »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcHote;
	@JsonIgnore
	public Couverture<String> jdbcHoteCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcHote").o(jdbcHote);

	/**	<br/>L'entité « jdbcHote »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcHote">Trouver l'entité jdbcHote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcHote(Couverture<String> c);

	public String getJdbcHote() {
		return jdbcHote;
	}

	public void setJdbcHote(String jdbcHote) {
		this.jdbcHote = jdbcHote;
		this.jdbcHoteCouverture.dejaInitialise = true;
	}
	protected ConfigSite jdbcHoteInit() {
		if(!jdbcHoteCouverture.dejaInitialise) {
			_jdbcHote(jdbcHoteCouverture);
			if(jdbcHote == null)
				setJdbcHote(jdbcHoteCouverture.o);
		}
		jdbcHoteCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////
	// jdbcPort //
	//////////////

	/**	L'entité « jdbcPort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcPort;
	@JsonIgnore
	public Couverture<Integer> jdbcPortCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcPort").o(jdbcPort);

	/**	<br/>L'entité « jdbcPort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcPort">Trouver l'entité jdbcPort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcPort(Couverture<Integer> c);

	public Integer getJdbcPort() {
		return jdbcPort;
	}

	public void setJdbcPort(Integer jdbcPort) {
		this.jdbcPort = jdbcPort;
		this.jdbcPortCouverture.dejaInitialise = true;
	}
	public ConfigSite setJdbcPort(String o) {
		if(NumberUtils.isParsable(o))
			this.jdbcPort = Integer.parseInt(o);
		this.jdbcPortCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite jdbcPortInit() {
		if(!jdbcPortCouverture.dejaInitialise) {
			_jdbcPort(jdbcPortCouverture);
			if(jdbcPort == null)
				setJdbcPort(jdbcPortCouverture.o);
		}
		jdbcPortCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	///////////////////////
	// jdbcBaseDeDonnees //
	///////////////////////

	/**	L'entité « jdbcBaseDeDonnees »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcBaseDeDonnees;
	@JsonIgnore
	public Couverture<String> jdbcBaseDeDonneesCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcBaseDeDonnees").o(jdbcBaseDeDonnees);

	/**	<br/>L'entité « jdbcBaseDeDonnees »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcBaseDeDonnees">Trouver l'entité jdbcBaseDeDonnees dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcBaseDeDonnees(Couverture<String> c);

	public String getJdbcBaseDeDonnees() {
		return jdbcBaseDeDonnees;
	}

	public void setJdbcBaseDeDonnees(String jdbcBaseDeDonnees) {
		this.jdbcBaseDeDonnees = jdbcBaseDeDonnees;
		this.jdbcBaseDeDonneesCouverture.dejaInitialise = true;
	}
	protected ConfigSite jdbcBaseDeDonneesInit() {
		if(!jdbcBaseDeDonneesCouverture.dejaInitialise) {
			_jdbcBaseDeDonnees(jdbcBaseDeDonneesCouverture);
			if(jdbcBaseDeDonnees == null)
				setJdbcBaseDeDonnees(jdbcBaseDeDonneesCouverture.o);
		}
		jdbcBaseDeDonneesCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////
	// solrUrl //
	/////////////

	/**	L'entité « solrUrl »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String solrUrl;
	@JsonIgnore
	public Couverture<String> solrUrlCouverture = new Couverture<String>().p(this).c(String.class).var("solrUrl").o(solrUrl);

	/**	<br/>L'entité « solrUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:solrUrl">Trouver l'entité solrUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrUrl(Couverture<String> c);

	public String getSolrUrl() {
		return solrUrl;
	}

	public void setSolrUrl(String solrUrl) {
		this.solrUrl = solrUrl;
		this.solrUrlCouverture.dejaInitialise = true;
	}
	protected ConfigSite solrUrlInit() {
		if(!solrUrlCouverture.dejaInitialise) {
			_solrUrl(solrUrlCouverture);
			if(solrUrl == null)
				setSolrUrl(solrUrlCouverture.o);
		}
		solrUrlCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////
	// solrUrlComputate //
	//////////////////////

	/**	L'entité « solrUrlComputate »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String solrUrlComputate;
	@JsonIgnore
	public Couverture<String> solrUrlComputateCouverture = new Couverture<String>().p(this).c(String.class).var("solrUrlComputate").o(solrUrlComputate);

	/**	<br/>L'entité « solrUrlComputate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:solrUrlComputate">Trouver l'entité solrUrlComputate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrUrlComputate(Couverture<String> c);

	public String getSolrUrlComputate() {
		return solrUrlComputate;
	}

	public void setSolrUrlComputate(String solrUrlComputate) {
		this.solrUrlComputate = solrUrlComputate;
		this.solrUrlComputateCouverture.dejaInitialise = true;
	}
	protected ConfigSite solrUrlComputateInit() {
		if(!solrUrlComputateCouverture.dejaInitialise) {
			_solrUrlComputate(solrUrlComputateCouverture);
			if(solrUrlComputate == null)
				setSolrUrlComputate(solrUrlComputateCouverture.o);
		}
		solrUrlComputateCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////////
	// compteFacebook //
	////////////////////

	/**	L'entité « compteFacebook »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String compteFacebook;
	@JsonIgnore
	public Couverture<String> compteFacebookCouverture = new Couverture<String>().p(this).c(String.class).var("compteFacebook").o(compteFacebook);

	/**	<br/>L'entité « compteFacebook »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteFacebook">Trouver l'entité compteFacebook dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteFacebook(Couverture<String> c);

	public String getCompteFacebook() {
		return compteFacebook;
	}

	public void setCompteFacebook(String compteFacebook) {
		this.compteFacebook = compteFacebook;
		this.compteFacebookCouverture.dejaInitialise = true;
	}
	protected ConfigSite compteFacebookInit() {
		if(!compteFacebookCouverture.dejaInitialise) {
			_compteFacebook(compteFacebookCouverture);
			if(compteFacebook == null)
				setCompteFacebook(compteFacebookCouverture.o);
		}
		compteFacebookCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	///////////////////
	// compteTwitter //
	///////////////////

	/**	L'entité « compteTwitter »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String compteTwitter;
	@JsonIgnore
	public Couverture<String> compteTwitterCouverture = new Couverture<String>().p(this).c(String.class).var("compteTwitter").o(compteTwitter);

	/**	<br/>L'entité « compteTwitter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteTwitter">Trouver l'entité compteTwitter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteTwitter(Couverture<String> c);

	public String getCompteTwitter() {
		return compteTwitter;
	}

	public void setCompteTwitter(String compteTwitter) {
		this.compteTwitter = compteTwitter;
		this.compteTwitterCouverture.dejaInitialise = true;
	}
	protected ConfigSite compteTwitterInit() {
		if(!compteTwitterCouverture.dejaInitialise) {
			_compteTwitter(compteTwitterCouverture);
			if(compteTwitter == null)
				setCompteTwitter(compteTwitterCouverture.o);
		}
		compteTwitterCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////////
	// compteInstagram //
	/////////////////////

	/**	L'entité « compteInstagram »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String compteInstagram;
	@JsonIgnore
	public Couverture<String> compteInstagramCouverture = new Couverture<String>().p(this).c(String.class).var("compteInstagram").o(compteInstagram);

	/**	<br/>L'entité « compteInstagram »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteInstagram">Trouver l'entité compteInstagram dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteInstagram(Couverture<String> c);

	public String getCompteInstagram() {
		return compteInstagram;
	}

	public void setCompteInstagram(String compteInstagram) {
		this.compteInstagram = compteInstagram;
		this.compteInstagramCouverture.dejaInitialise = true;
	}
	protected ConfigSite compteInstagramInit() {
		if(!compteInstagramCouverture.dejaInitialise) {
			_compteInstagram(compteInstagramCouverture);
			if(compteInstagram == null)
				setCompteInstagram(compteInstagramCouverture.o);
		}
		compteInstagramCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	///////////////////
	// compteYoutube //
	///////////////////

	/**	L'entité « compteYoutube »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String compteYoutube;
	@JsonIgnore
	public Couverture<String> compteYoutubeCouverture = new Couverture<String>().p(this).c(String.class).var("compteYoutube").o(compteYoutube);

	/**	<br/>L'entité « compteYoutube »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteYoutube">Trouver l'entité compteYoutube dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteYoutube(Couverture<String> c);

	public String getCompteYoutube() {
		return compteYoutube;
	}

	public void setCompteYoutube(String compteYoutube) {
		this.compteYoutube = compteYoutube;
		this.compteYoutubeCouverture.dejaInitialise = true;
	}
	protected ConfigSite compteYoutubeInit() {
		if(!compteYoutubeCouverture.dejaInitialise) {
			_compteYoutube(compteYoutubeCouverture);
			if(compteYoutube == null)
				setCompteYoutube(compteYoutubeCouverture.o);
		}
		compteYoutubeCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////////
	// comptePinterest //
	/////////////////////

	/**	L'entité « comptePinterest »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String comptePinterest;
	@JsonIgnore
	public Couverture<String> comptePinterestCouverture = new Couverture<String>().p(this).c(String.class).var("comptePinterest").o(comptePinterest);

	/**	<br/>L'entité « comptePinterest »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:comptePinterest">Trouver l'entité comptePinterest dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _comptePinterest(Couverture<String> c);

	public String getComptePinterest() {
		return comptePinterest;
	}

	public void setComptePinterest(String comptePinterest) {
		this.comptePinterest = comptePinterest;
		this.comptePinterestCouverture.dejaInitialise = true;
	}
	protected ConfigSite comptePinterestInit() {
		if(!comptePinterestCouverture.dejaInitialise) {
			_comptePinterest(comptePinterestCouverture);
			if(comptePinterest == null)
				setComptePinterest(comptePinterestCouverture.o);
		}
		comptePinterestCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////
	// compteMail //
	////////////////

	/**	L'entité « compteMail »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String compteMail;
	@JsonIgnore
	public Couverture<String> compteMailCouverture = new Couverture<String>().p(this).c(String.class).var("compteMail").o(compteMail);

	/**	<br/>L'entité « compteMail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteMail">Trouver l'entité compteMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteMail(Couverture<String> c);

	public String getCompteMail() {
		return compteMail;
	}

	public void setCompteMail(String compteMail) {
		this.compteMail = compteMail;
		this.compteMailCouverture.dejaInitialise = true;
	}
	protected ConfigSite compteMailInit() {
		if(!compteMailCouverture.dejaInitialise) {
			_compteMail(compteMailCouverture);
			if(compteMail == null)
				setCompteMail(compteMailCouverture.o);
		}
		compteMailCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	///////////////
	// roleAdmin //
	///////////////

	/**	L'entité « roleAdmin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String roleAdmin;
	@JsonIgnore
	public Couverture<String> roleAdminCouverture = new Couverture<String>().p(this).c(String.class).var("roleAdmin").o(roleAdmin);

	/**	<br/>L'entité « roleAdmin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:roleAdmin">Trouver l'entité roleAdmin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _roleAdmin(Couverture<String> c);

	public String getRoleAdmin() {
		return roleAdmin;
	}

	public void setRoleAdmin(String roleAdmin) {
		this.roleAdmin = roleAdmin;
		this.roleAdminCouverture.dejaInitialise = true;
	}
	protected ConfigSite roleAdminInit() {
		if(!roleAdminCouverture.dejaInitialise) {
			_roleAdmin(roleAdminCouverture);
			if(roleAdmin == null)
				setRoleAdmin(roleAdminCouverture.o);
		}
		roleAdminCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	///////////////
	// mailAdmin //
	///////////////

	/**	L'entité « mailAdmin »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mailAdmin;
	@JsonIgnore
	public Couverture<String> mailAdminCouverture = new Couverture<String>().p(this).c(String.class).var("mailAdmin").o(mailAdmin);

	/**	<br/>L'entité « mailAdmin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailAdmin">Trouver l'entité mailAdmin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailAdmin(Couverture<String> c);

	public String getMailAdmin() {
		return mailAdmin;
	}

	public void setMailAdmin(String mailAdmin) {
		this.mailAdmin = mailAdmin;
		this.mailAdminCouverture.dejaInitialise = true;
	}
	protected ConfigSite mailAdminInit() {
		if(!mailAdminCouverture.dejaInitialise) {
			_mailAdmin(mailAdminCouverture);
			if(mailAdmin == null)
				setMailAdmin(mailAdminCouverture.o);
		}
		mailAdminCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////
	// nombreExecuteurs //
	//////////////////////

	/**	L'entité « nombreExecuteurs »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer nombreExecuteurs;
	@JsonIgnore
	public Couverture<Integer> nombreExecuteursCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("nombreExecuteurs").o(nombreExecuteurs);

	/**	<br/>L'entité « nombreExecuteurs »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:nombreExecuteurs">Trouver l'entité nombreExecuteurs dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _nombreExecuteurs(Couverture<Integer> c);

	public Integer getNombreExecuteurs() {
		return nombreExecuteurs;
	}

	public void setNombreExecuteurs(Integer nombreExecuteurs) {
		this.nombreExecuteurs = nombreExecuteurs;
		this.nombreExecuteursCouverture.dejaInitialise = true;
	}
	public ConfigSite setNombreExecuteurs(String o) {
		if(NumberUtils.isParsable(o))
			this.nombreExecuteurs = Integer.parseInt(o);
		this.nombreExecuteursCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite nombreExecuteursInit() {
		if(!nombreExecuteursCouverture.dejaInitialise) {
			_nombreExecuteurs(nombreExecuteursCouverture);
			if(nombreExecuteurs == null)
				setNombreExecuteurs(nombreExecuteursCouverture.o);
		}
		nombreExecuteursCouverture.dejaInitialise(true);
		return (ConfigSite)this;
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
	public Couverture<String> openApiVersionCouverture = new Couverture<String>().p(this).c(String.class).var("openApiVersion").o(openApiVersion);

	/**	<br/>L'entité « openApiVersion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiVersion">Trouver l'entité openApiVersion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiVersion(Couverture<String> c);

	public String getOpenApiVersion() {
		return openApiVersion;
	}

	public void setOpenApiVersion(String openApiVersion) {
		this.openApiVersion = openApiVersion;
		this.openApiVersionCouverture.dejaInitialise = true;
	}
	protected ConfigSite openApiVersionInit() {
		if(!openApiVersionCouverture.dejaInitialise) {
			_openApiVersion(openApiVersionCouverture);
			if(openApiVersion == null)
				setOpenApiVersion(openApiVersionCouverture.o);
		}
		openApiVersionCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////////
	// apiDescription //
	////////////////////

	/**	L'entité « apiDescription »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiDescription;
	@JsonIgnore
	public Couverture<String> apiDescriptionCouverture = new Couverture<String>().p(this).c(String.class).var("apiDescription").o(apiDescription);

	/**	<br/>L'entité « apiDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiDescription">Trouver l'entité apiDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiDescription(Couverture<String> c);

	public String getApiDescription() {
		return apiDescription;
	}

	public void setApiDescription(String apiDescription) {
		this.apiDescription = apiDescription;
		this.apiDescriptionCouverture.dejaInitialise = true;
	}
	protected ConfigSite apiDescriptionInit() {
		if(!apiDescriptionCouverture.dejaInitialise) {
			_apiDescription(apiDescriptionCouverture);
			if(apiDescription == null)
				setApiDescription(apiDescriptionCouverture.o);
		}
		apiDescriptionCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////
	// apiTitre //
	//////////////

	/**	L'entité « apiTitre »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiTitre;
	@JsonIgnore
	public Couverture<String> apiTitreCouverture = new Couverture<String>().p(this).c(String.class).var("apiTitre").o(apiTitre);

	/**	<br/>L'entité « apiTitre »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiTitre">Trouver l'entité apiTitre dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiTitre(Couverture<String> c);

	public String getApiTitre() {
		return apiTitre;
	}

	public void setApiTitre(String apiTitre) {
		this.apiTitre = apiTitre;
		this.apiTitreCouverture.dejaInitialise = true;
	}
	protected ConfigSite apiTitreInit() {
		if(!apiTitreCouverture.dejaInitialise) {
			_apiTitre(apiTitreCouverture);
			if(apiTitre == null)
				setApiTitre(apiTitreCouverture.o);
		}
		apiTitreCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////////
	// apiTermsService //
	/////////////////////

	/**	L'entité « apiTermsService »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiTermsService;
	@JsonIgnore
	public Couverture<String> apiTermsServiceCouverture = new Couverture<String>().p(this).c(String.class).var("apiTermsService").o(apiTermsService);

	/**	<br/>L'entité « apiTermsService »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiTermsService">Trouver l'entité apiTermsService dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiTermsService(Couverture<String> c);

	public String getApiTermsService() {
		return apiTermsService;
	}

	public void setApiTermsService(String apiTermsService) {
		this.apiTermsService = apiTermsService;
		this.apiTermsServiceCouverture.dejaInitialise = true;
	}
	protected ConfigSite apiTermsServiceInit() {
		if(!apiTermsServiceCouverture.dejaInitialise) {
			_apiTermsService(apiTermsServiceCouverture);
			if(apiTermsService == null)
				setApiTermsService(apiTermsServiceCouverture.o);
		}
		apiTermsServiceCouverture.dejaInitialise(true);
		return (ConfigSite)this;
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
	public Couverture<String> apiVersionCouverture = new Couverture<String>().p(this).c(String.class).var("apiVersion").o(apiVersion);

	/**	<br/>L'entité « apiVersion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiVersion">Trouver l'entité apiVersion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiVersion(Couverture<String> c);

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
		this.apiVersionCouverture.dejaInitialise = true;
	}
	protected ConfigSite apiVersionInit() {
		if(!apiVersionCouverture.dejaInitialise) {
			_apiVersion(apiVersionCouverture);
			if(apiVersion == null)
				setApiVersion(apiVersionCouverture.o);
		}
		apiVersionCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////////
	// apiContactMail //
	////////////////////

	/**	L'entité « apiContactMail »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiContactMail;
	@JsonIgnore
	public Couverture<String> apiContactMailCouverture = new Couverture<String>().p(this).c(String.class).var("apiContactMail").o(apiContactMail);

	/**	<br/>L'entité « apiContactMail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiContactMail">Trouver l'entité apiContactMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiContactMail(Couverture<String> c);

	public String getApiContactMail() {
		return apiContactMail;
	}

	public void setApiContactMail(String apiContactMail) {
		this.apiContactMail = apiContactMail;
		this.apiContactMailCouverture.dejaInitialise = true;
	}
	protected ConfigSite apiContactMailInit() {
		if(!apiContactMailCouverture.dejaInitialise) {
			_apiContactMail(apiContactMailCouverture);
			if(apiContactMail == null)
				setApiContactMail(apiContactMailCouverture.o);
		}
		apiContactMailCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	///////////////////
	// apiLicenceNom //
	///////////////////

	/**	L'entité « apiLicenceNom »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiLicenceNom;
	@JsonIgnore
	public Couverture<String> apiLicenceNomCouverture = new Couverture<String>().p(this).c(String.class).var("apiLicenceNom").o(apiLicenceNom);

	/**	<br/>L'entité « apiLicenceNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiLicenceNom">Trouver l'entité apiLicenceNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiLicenceNom(Couverture<String> c);

	public String getApiLicenceNom() {
		return apiLicenceNom;
	}

	public void setApiLicenceNom(String apiLicenceNom) {
		this.apiLicenceNom = apiLicenceNom;
		this.apiLicenceNomCouverture.dejaInitialise = true;
	}
	protected ConfigSite apiLicenceNomInit() {
		if(!apiLicenceNomCouverture.dejaInitialise) {
			_apiLicenceNom(apiLicenceNomCouverture);
			if(apiLicenceNom == null)
				setApiLicenceNom(apiLicenceNomCouverture.o);
		}
		apiLicenceNomCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	///////////////////
	// apiLicenceUrl //
	///////////////////

	/**	L'entité « apiLicenceUrl »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiLicenceUrl;
	@JsonIgnore
	public Couverture<String> apiLicenceUrlCouverture = new Couverture<String>().p(this).c(String.class).var("apiLicenceUrl").o(apiLicenceUrl);

	/**	<br/>L'entité « apiLicenceUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiLicenceUrl">Trouver l'entité apiLicenceUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiLicenceUrl(Couverture<String> c);

	public String getApiLicenceUrl() {
		return apiLicenceUrl;
	}

	public void setApiLicenceUrl(String apiLicenceUrl) {
		this.apiLicenceUrl = apiLicenceUrl;
		this.apiLicenceUrlCouverture.dejaInitialise = true;
	}
	protected ConfigSite apiLicenceUrlInit() {
		if(!apiLicenceUrlCouverture.dejaInitialise) {
			_apiLicenceUrl(apiLicenceUrlCouverture);
			if(apiLicenceUrl == null)
				setApiLicenceUrl(apiLicenceUrlCouverture.o);
		}
		apiLicenceUrlCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////
	// apiNomHote //
	////////////////

	/**	L'entité « apiNomHote »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiNomHote;
	@JsonIgnore
	public Couverture<String> apiNomHoteCouverture = new Couverture<String>().p(this).c(String.class).var("apiNomHote").o(apiNomHote);

	/**	<br/>L'entité « apiNomHote »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiNomHote">Trouver l'entité apiNomHote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiNomHote(Couverture<String> c);

	public String getApiNomHote() {
		return apiNomHote;
	}

	public void setApiNomHote(String apiNomHote) {
		this.apiNomHote = apiNomHote;
		this.apiNomHoteCouverture.dejaInitialise = true;
	}
	protected ConfigSite apiNomHoteInit() {
		if(!apiNomHoteCouverture.dejaInitialise) {
			_apiNomHote(apiNomHoteCouverture);
			if(apiNomHote == null)
				setApiNomHote(apiNomHoteCouverture.o);
		}
		apiNomHoteCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	///////////////////
	// apiCheminBase //
	///////////////////

	/**	L'entité « apiCheminBase »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiCheminBase;
	@JsonIgnore
	public Couverture<String> apiCheminBaseCouverture = new Couverture<String>().p(this).c(String.class).var("apiCheminBase").o(apiCheminBase);

	/**	<br/>L'entité « apiCheminBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiCheminBase">Trouver l'entité apiCheminBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiCheminBase(Couverture<String> c);

	public String getApiCheminBase() {
		return apiCheminBase;
	}

	public void setApiCheminBase(String apiCheminBase) {
		this.apiCheminBase = apiCheminBase;
		this.apiCheminBaseCouverture.dejaInitialise = true;
	}
	protected ConfigSite apiCheminBaseInit() {
		if(!apiCheminBaseCouverture.dejaInitialise) {
			_apiCheminBase(apiCheminBaseCouverture);
			if(apiCheminBase == null)
				setApiCheminBase(apiCheminBaseCouverture.o);
		}
		apiCheminBaseCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////////
	// statiqueUrlBase //
	/////////////////////

	/**	L'entité « statiqueUrlBase »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String statiqueUrlBase;
	@JsonIgnore
	public Couverture<String> statiqueUrlBaseCouverture = new Couverture<String>().p(this).c(String.class).var("statiqueUrlBase").o(statiqueUrlBase);

	/**	<br/>L'entité « statiqueUrlBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:statiqueUrlBase">Trouver l'entité statiqueUrlBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _statiqueUrlBase(Couverture<String> c);

	public String getStatiqueUrlBase() {
		return statiqueUrlBase;
	}

	public void setStatiqueUrlBase(String statiqueUrlBase) {
		this.statiqueUrlBase = statiqueUrlBase;
		this.statiqueUrlBaseCouverture.dejaInitialise = true;
	}
	protected ConfigSite statiqueUrlBaseInit() {
		if(!statiqueUrlBaseCouverture.dejaInitialise) {
			_statiqueUrlBase(statiqueUrlBaseCouverture);
			if(statiqueUrlBase == null)
				setStatiqueUrlBase(statiqueUrlBaseCouverture.o);
		}
		statiqueUrlBaseCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////
	// mailHote //
	//////////////

	/**	L'entité « mailHote »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mailHote;
	@JsonIgnore
	public Couverture<String> mailHoteCouverture = new Couverture<String>().p(this).c(String.class).var("mailHote").o(mailHote);

	/**	<br/>L'entité « mailHote »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailHote">Trouver l'entité mailHote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailHote(Couverture<String> c);

	public String getMailHote() {
		return mailHote;
	}

	public void setMailHote(String mailHote) {
		this.mailHote = mailHote;
		this.mailHoteCouverture.dejaInitialise = true;
	}
	protected ConfigSite mailHoteInit() {
		if(!mailHoteCouverture.dejaInitialise) {
			_mailHote(mailHoteCouverture);
			if(mailHote == null)
				setMailHote(mailHoteCouverture.o);
		}
		mailHoteCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////
	// mailPort //
	//////////////

	/**	L'entité « mailPort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer mailPort;
	@JsonIgnore
	public Couverture<Integer> mailPortCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("mailPort").o(mailPort);

	/**	<br/>L'entité « mailPort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailPort">Trouver l'entité mailPort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailPort(Couverture<Integer> c);

	public Integer getMailPort() {
		return mailPort;
	}

	public void setMailPort(Integer mailPort) {
		this.mailPort = mailPort;
		this.mailPortCouverture.dejaInitialise = true;
	}
	public ConfigSite setMailPort(String o) {
		if(NumberUtils.isParsable(o))
			this.mailPort = Integer.parseInt(o);
		this.mailPortCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite mailPortInit() {
		if(!mailPortCouverture.dejaInitialise) {
			_mailPort(mailPortCouverture);
			if(mailPort == null)
				setMailPort(mailPortCouverture.o);
		}
		mailPortCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////////
	// mailUtilisateur //
	/////////////////////

	/**	L'entité « mailUtilisateur »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mailUtilisateur;
	@JsonIgnore
	public Couverture<String> mailUtilisateurCouverture = new Couverture<String>().p(this).c(String.class).var("mailUtilisateur").o(mailUtilisateur);

	/**	<br/>L'entité « mailUtilisateur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailUtilisateur">Trouver l'entité mailUtilisateur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailUtilisateur(Couverture<String> c);

	public String getMailUtilisateur() {
		return mailUtilisateur;
	}

	public void setMailUtilisateur(String mailUtilisateur) {
		this.mailUtilisateur = mailUtilisateur;
		this.mailUtilisateurCouverture.dejaInitialise = true;
	}
	protected ConfigSite mailUtilisateurInit() {
		if(!mailUtilisateurCouverture.dejaInitialise) {
			_mailUtilisateur(mailUtilisateurCouverture);
			if(mailUtilisateur == null)
				setMailUtilisateur(mailUtilisateurCouverture.o);
		}
		mailUtilisateurCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////////////
	// mailMotDePasse //
	////////////////////

	/**	L'entité « mailMotDePasse »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mailMotDePasse;
	@JsonIgnore
	public Couverture<String> mailMotDePasseCouverture = new Couverture<String>().p(this).c(String.class).var("mailMotDePasse").o(mailMotDePasse);

	/**	<br/>L'entité « mailMotDePasse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailMotDePasse">Trouver l'entité mailMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailMotDePasse(Couverture<String> c);

	public String getMailMotDePasse() {
		return mailMotDePasse;
	}

	public void setMailMotDePasse(String mailMotDePasse) {
		this.mailMotDePasse = mailMotDePasse;
		this.mailMotDePasseCouverture.dejaInitialise = true;
	}
	protected ConfigSite mailMotDePasseInit() {
		if(!mailMotDePasseCouverture.dejaInitialise) {
			_mailMotDePasse(mailMotDePasseCouverture);
			if(mailMotDePasse == null)
				setMailMotDePasse(mailMotDePasseCouverture.o);
		}
		mailMotDePasseCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	////////////
	// mailDe //
	////////////

	/**	L'entité « mailDe »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mailDe;
	@JsonIgnore
	public Couverture<String> mailDeCouverture = new Couverture<String>().p(this).c(String.class).var("mailDe").o(mailDe);

	/**	<br/>L'entité « mailDe »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailDe">Trouver l'entité mailDe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailDe(Couverture<String> c);

	public String getMailDe() {
		return mailDe;
	}

	public void setMailDe(String mailDe) {
		this.mailDe = mailDe;
		this.mailDeCouverture.dejaInitialise = true;
	}
	protected ConfigSite mailDeInit() {
		if(!mailDeCouverture.dejaInitialise) {
			_mailDe(mailDeCouverture);
			if(mailDe == null)
				setMailDe(mailDeCouverture.o);
		}
		mailDeCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////
	// mailAuth //
	//////////////

	/**	L'entité « mailAuth »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean mailAuth;
	@JsonIgnore
	public Couverture<Boolean> mailAuthCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("mailAuth").o(mailAuth);

	/**	<br/>L'entité « mailAuth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailAuth">Trouver l'entité mailAuth dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailAuth(Couverture<Boolean> c);

	public Boolean getMailAuth() {
		return mailAuth;
	}

	public void setMailAuth(Boolean mailAuth) {
		this.mailAuth = mailAuth;
		this.mailAuthCouverture.dejaInitialise = true;
	}
	public ConfigSite setMailAuth(String o) {
		this.mailAuth = Boolean.parseBoolean(o);
		this.mailAuthCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite mailAuthInit() {
		if(!mailAuthCouverture.dejaInitialise) {
			_mailAuth(mailAuthCouverture);
			if(mailAuth == null)
				setMailAuth(mailAuthCouverture.o);
		}
		mailAuthCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////
	// mailSsl //
	/////////////

	/**	L'entité « mailSsl »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean mailSsl;
	@JsonIgnore
	public Couverture<Boolean> mailSslCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("mailSsl").o(mailSsl);

	/**	<br/>L'entité « mailSsl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailSsl">Trouver l'entité mailSsl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailSsl(Couverture<Boolean> c);

	public Boolean getMailSsl() {
		return mailSsl;
	}

	public void setMailSsl(Boolean mailSsl) {
		this.mailSsl = mailSsl;
		this.mailSslCouverture.dejaInitialise = true;
	}
	public ConfigSite setMailSsl(String o) {
		this.mailSsl = Boolean.parseBoolean(o);
		this.mailSslCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite mailSslInit() {
		if(!mailSslCouverture.dejaInitialise) {
			_mailSsl(mailSslCouverture);
			if(mailSsl == null)
				setMailSsl(mailSslCouverture.o);
		}
		mailSslCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////
	// siteZone //
	//////////////

	/**	L'entité « siteZone »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteZone;
	@JsonIgnore
	public Couverture<String> siteZoneCouverture = new Couverture<String>().p(this).c(String.class).var("siteZone").o(siteZone);

	/**	<br/>L'entité « siteZone »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteZone">Trouver l'entité siteZone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteZone(Couverture<String> c);

	public String getSiteZone() {
		return siteZone;
	}

	public void setSiteZone(String siteZone) {
		this.siteZone = siteZone;
		this.siteZoneCouverture.dejaInitialise = true;
	}
	protected ConfigSite siteZoneInit() {
		if(!siteZoneCouverture.dejaInitialise) {
			_siteZone(siteZoneCouverture);
			if(siteZone == null)
				setSiteZone(siteZoneCouverture.o);
		}
		siteZoneCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////////
	// authorizeApiLoginId1 //
	//////////////////////////

	/**	L'entité « authorizeApiLoginId1 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeApiLoginId1;
	@JsonIgnore
	public Couverture<String> authorizeApiLoginId1Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeApiLoginId1").o(authorizeApiLoginId1);

	/**	<br/>L'entité « authorizeApiLoginId1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeApiLoginId1">Trouver l'entité authorizeApiLoginId1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeApiLoginId1(Couverture<String> c);

	public String getAuthorizeApiLoginId1() {
		return authorizeApiLoginId1;
	}

	public void setAuthorizeApiLoginId1(String authorizeApiLoginId1) {
		this.authorizeApiLoginId1 = authorizeApiLoginId1;
		this.authorizeApiLoginId1Couverture.dejaInitialise = true;
	}
	protected ConfigSite authorizeApiLoginId1Init() {
		if(!authorizeApiLoginId1Couverture.dejaInitialise) {
			_authorizeApiLoginId1(authorizeApiLoginId1Couverture);
			if(authorizeApiLoginId1 == null)
				setAuthorizeApiLoginId1(authorizeApiLoginId1Couverture.o);
		}
		authorizeApiLoginId1Couverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////////////
	// authorizeTransactionKey1 //
	//////////////////////////////

	/**	L'entité « authorizeTransactionKey1 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeTransactionKey1;
	@JsonIgnore
	public Couverture<String> authorizeTransactionKey1Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeTransactionKey1").o(authorizeTransactionKey1);

	/**	<br/>L'entité « authorizeTransactionKey1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeTransactionKey1">Trouver l'entité authorizeTransactionKey1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeTransactionKey1(Couverture<String> c);

	public String getAuthorizeTransactionKey1() {
		return authorizeTransactionKey1;
	}

	public void setAuthorizeTransactionKey1(String authorizeTransactionKey1) {
		this.authorizeTransactionKey1 = authorizeTransactionKey1;
		this.authorizeTransactionKey1Couverture.dejaInitialise = true;
	}
	protected ConfigSite authorizeTransactionKey1Init() {
		if(!authorizeTransactionKey1Couverture.dejaInitialise) {
			_authorizeTransactionKey1(authorizeTransactionKey1Couverture);
			if(authorizeTransactionKey1 == null)
				setAuthorizeTransactionKey1(authorizeTransactionKey1Couverture.o);
		}
		authorizeTransactionKey1Couverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////////
	// schoolLocation1 //
	/////////////////////

	/**	L'entité « schoolLocation1 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation1;
	@JsonIgnore
	public Couverture<String> schoolLocation1Couverture = new Couverture<String>().p(this).c(String.class).var("schoolLocation1").o(schoolLocation1);

	/**	<br/>L'entité « schoolLocation1 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:schoolLocation1">Trouver l'entité schoolLocation1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolLocation1(Couverture<String> c);

	public String getSchoolLocation1() {
		return schoolLocation1;
	}

	public void setSchoolLocation1(String schoolLocation1) {
		this.schoolLocation1 = schoolLocation1;
		this.schoolLocation1Couverture.dejaInitialise = true;
	}
	protected ConfigSite schoolLocation1Init() {
		if(!schoolLocation1Couverture.dejaInitialise) {
			_schoolLocation1(schoolLocation1Couverture);
			if(schoolLocation1 == null)
				setSchoolLocation1(schoolLocation1Couverture.o);
		}
		schoolLocation1Couverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////////
	// authorizeApiLoginId2 //
	//////////////////////////

	/**	L'entité « authorizeApiLoginId2 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeApiLoginId2;
	@JsonIgnore
	public Couverture<String> authorizeApiLoginId2Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeApiLoginId2").o(authorizeApiLoginId2);

	/**	<br/>L'entité « authorizeApiLoginId2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeApiLoginId2">Trouver l'entité authorizeApiLoginId2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeApiLoginId2(Couverture<String> c);

	public String getAuthorizeApiLoginId2() {
		return authorizeApiLoginId2;
	}

	public void setAuthorizeApiLoginId2(String authorizeApiLoginId2) {
		this.authorizeApiLoginId2 = authorizeApiLoginId2;
		this.authorizeApiLoginId2Couverture.dejaInitialise = true;
	}
	protected ConfigSite authorizeApiLoginId2Init() {
		if(!authorizeApiLoginId2Couverture.dejaInitialise) {
			_authorizeApiLoginId2(authorizeApiLoginId2Couverture);
			if(authorizeApiLoginId2 == null)
				setAuthorizeApiLoginId2(authorizeApiLoginId2Couverture.o);
		}
		authorizeApiLoginId2Couverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////////////
	// authorizeTransactionKey2 //
	//////////////////////////////

	/**	L'entité « authorizeTransactionKey2 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeTransactionKey2;
	@JsonIgnore
	public Couverture<String> authorizeTransactionKey2Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeTransactionKey2").o(authorizeTransactionKey2);

	/**	<br/>L'entité « authorizeTransactionKey2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeTransactionKey2">Trouver l'entité authorizeTransactionKey2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeTransactionKey2(Couverture<String> c);

	public String getAuthorizeTransactionKey2() {
		return authorizeTransactionKey2;
	}

	public void setAuthorizeTransactionKey2(String authorizeTransactionKey2) {
		this.authorizeTransactionKey2 = authorizeTransactionKey2;
		this.authorizeTransactionKey2Couverture.dejaInitialise = true;
	}
	protected ConfigSite authorizeTransactionKey2Init() {
		if(!authorizeTransactionKey2Couverture.dejaInitialise) {
			_authorizeTransactionKey2(authorizeTransactionKey2Couverture);
			if(authorizeTransactionKey2 == null)
				setAuthorizeTransactionKey2(authorizeTransactionKey2Couverture.o);
		}
		authorizeTransactionKey2Couverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	/////////////////////
	// schoolLocation2 //
	/////////////////////

	/**	L'entité « schoolLocation2 »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation2;
	@JsonIgnore
	public Couverture<String> schoolLocation2Couverture = new Couverture<String>().p(this).c(String.class).var("schoolLocation2").o(schoolLocation2);

	/**	<br/>L'entité « schoolLocation2 »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:schoolLocation2">Trouver l'entité schoolLocation2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolLocation2(Couverture<String> c);

	public String getSchoolLocation2() {
		return schoolLocation2;
	}

	public void setSchoolLocation2(String schoolLocation2) {
		this.schoolLocation2 = schoolLocation2;
		this.schoolLocation2Couverture.dejaInitialise = true;
	}
	protected ConfigSite schoolLocation2Init() {
		if(!schoolLocation2Couverture.dejaInitialise) {
			_schoolLocation2(schoolLocation2Couverture);
			if(schoolLocation2 == null)
				setSchoolLocation2(schoolLocation2Couverture.o);
		}
		schoolLocation2Couverture.dejaInitialise(true);
		return (ConfigSite)this;
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
	public Couverture<String> authorizeEnvironmentCouverture = new Couverture<String>().p(this).c(String.class).var("authorizeEnvironment").o(authorizeEnvironment);

	/**	<br/>L'entité « authorizeEnvironment »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeEnvironment">Trouver l'entité authorizeEnvironment dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeEnvironment(Couverture<String> c);

	public String getAuthorizeEnvironment() {
		return authorizeEnvironment;
	}

	public void setAuthorizeEnvironment(String authorizeEnvironment) {
		this.authorizeEnvironment = authorizeEnvironment;
		this.authorizeEnvironmentCouverture.dejaInitialise = true;
	}
	protected ConfigSite authorizeEnvironmentInit() {
		if(!authorizeEnvironmentCouverture.dejaInitialise) {
			_authorizeEnvironment(authorizeEnvironmentCouverture);
			if(authorizeEnvironment == null)
				setAuthorizeEnvironment(authorizeEnvironmentCouverture.o);
		}
		authorizeEnvironmentCouverture.dejaInitialise(true);
		return (ConfigSite)this;
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
	public Couverture<String> authorizeUrlCouverture = new Couverture<String>().p(this).c(String.class).var("authorizeUrl").o(authorizeUrl);

	/**	<br/>L'entité « authorizeUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeUrl">Trouver l'entité authorizeUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeUrl(Couverture<String> c);

	public String getAuthorizeUrl() {
		return authorizeUrl;
	}

	public void setAuthorizeUrl(String authorizeUrl) {
		this.authorizeUrl = authorizeUrl;
		this.authorizeUrlCouverture.dejaInitialise = true;
	}
	protected ConfigSite authorizeUrlInit() {
		if(!authorizeUrlCouverture.dejaInitialise) {
			_authorizeUrl(authorizeUrlCouverture);
			if(authorizeUrl == null)
				setAuthorizeUrl(authorizeUrlCouverture.o);
		}
		authorizeUrlCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////
	// paiementJour //
	//////////////////

	/**	L'entité « paiementJour »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer paiementJour;
	@JsonIgnore
	public Couverture<Integer> paiementJourCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("paiementJour").o(paiementJour);

	/**	<br/>L'entité « paiementJour »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementJour">Trouver l'entité paiementJour dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementJour(Couverture<Integer> c);

	public Integer getPaiementJour() {
		return paiementJour;
	}

	public void setPaiementJour(Integer paiementJour) {
		this.paiementJour = paiementJour;
		this.paiementJourCouverture.dejaInitialise = true;
	}
	public ConfigSite setPaiementJour(String o) {
		if(NumberUtils.isParsable(o))
			this.paiementJour = Integer.parseInt(o);
		this.paiementJourCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite paiementJourInit() {
		if(!paiementJourCouverture.dejaInitialise) {
			_paiementJour(paiementJourCouverture);
			if(paiementJour == null)
				setPaiementJour(paiementJourCouverture.o);
		}
		paiementJourCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////////////
	// paiementProchain //
	//////////////////////

	/**	L'entité « paiementProchain »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate paiementProchain;
	@JsonIgnore
	public Couverture<LocalDate> paiementProchainCouverture = new Couverture<LocalDate>().p(this).c(LocalDate.class).var("paiementProchain").o(paiementProchain);

	/**	<br/>L'entité « paiementProchain »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementProchain">Trouver l'entité paiementProchain dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _paiementProchain(Couverture<LocalDate> c);

	public LocalDate getPaiementProchain() {
		return paiementProchain;
	}

	public void setPaiementProchain(LocalDate paiementProchain) {
		this.paiementProchain = paiementProchain;
		this.paiementProchainCouverture.dejaInitialise = true;
	}
	public ConfigSite setPaiementProchain(Instant o) {
		this.paiementProchain = o == null ? null : LocalDate.from(o);
		this.paiementProchainCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public ConfigSite setPaiementProchain(String o) {
		this.paiementProchain = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.paiementProchainCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite paiementProchainInit() {
		if(!paiementProchainCouverture.dejaInitialise) {
			_paiementProchain(paiementProchainCouverture);
			if(paiementProchain == null)
				setPaiementProchain(paiementProchainCouverture.o);
		}
		paiementProchainCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseConfigSite = false;

	public ConfigSite initLoinConfigSite(RequeteSiteFrFR requeteSite_) {
		if(!dejaInitialiseConfigSite) {
			dejaInitialiseConfigSite = true;
			initLoinConfigSite();
		}
		return (ConfigSite)this;
	}

	public void initLoinConfigSite() {
		initConfigSite();
	}

	public void initConfigSite() {
		configCheminInit();
		configInit();
		identifiantSiteInit();
		prefixeEchappeInit();
		appliCheminInit();
		racineDocumentInit();
		nomEntrepriseInit();
		nomDomaineInit();
		siteNomHoteInit();
		sitePortInit();
		authRoyaumeInit();
		authRessourceInit();
		authSecretInit();
		authSslRequisInit();
		sslJksCheminInit();
		sslJksMotDePasseInit();
		authUrlInit();
		cryptageSelInit();
		cryptageMotDePasseInit();
		siteUrlBaseInit();
		siteNomAffichageInit();
		jdbcClassePiloteInit();
		jdbcUtilisateurInit();
		jdbcMotDePasseInit();
		jdbcTailleMaxPiscineInit();
		jdbcMaxFileAttenteInit();
		jdbcTailleMinPiscineInit();
		jdbcMaxDeclarationsInit();
		jdbcMaxDeclarationsParConnexionInit();
		jdbcTempsInactiviteMaxInit();
		jdbcDelaiConnexionInit();
		jdbcHoteInit();
		jdbcPortInit();
		jdbcBaseDeDonneesInit();
		solrUrlInit();
		solrUrlComputateInit();
		compteFacebookInit();
		compteTwitterInit();
		compteInstagramInit();
		compteYoutubeInit();
		comptePinterestInit();
		compteMailInit();
		roleAdminInit();
		mailAdminInit();
		nombreExecuteursInit();
		openApiVersionInit();
		apiDescriptionInit();
		apiTitreInit();
		apiTermsServiceInit();
		apiVersionInit();
		apiContactMailInit();
		apiLicenceNomInit();
		apiLicenceUrlInit();
		apiNomHoteInit();
		apiCheminBaseInit();
		statiqueUrlBaseInit();
		mailHoteInit();
		mailPortInit();
		mailUtilisateurInit();
		mailMotDePasseInit();
		mailDeInit();
		mailAuthInit();
		mailSslInit();
		siteZoneInit();
		authorizeApiLoginId1Init();
		authorizeTransactionKey1Init();
		schoolLocation1Init();
		authorizeApiLoginId2Init();
		authorizeTransactionKey2Init();
		schoolLocation2Init();
		authorizeEnvironmentInit();
		authorizeUrlInit();
		paiementJourInit();
		paiementProchainInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinConfigSite(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirConfigSite(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirConfigSite(String var) {
		ConfigSite oConfigSite = (ConfigSite)this;
		switch(var) {
			case "configChemin":
				return oConfigSite.configChemin;
			case "config":
				return oConfigSite.config;
			case "identifiantSite":
				return oConfigSite.identifiantSite;
			case "prefixeEchappe":
				return oConfigSite.prefixeEchappe;
			case "appliChemin":
				return oConfigSite.appliChemin;
			case "racineDocument":
				return oConfigSite.racineDocument;
			case "nomEntreprise":
				return oConfigSite.nomEntreprise;
			case "nomDomaine":
				return oConfigSite.nomDomaine;
			case "siteNomHote":
				return oConfigSite.siteNomHote;
			case "sitePort":
				return oConfigSite.sitePort;
			case "authRoyaume":
				return oConfigSite.authRoyaume;
			case "authRessource":
				return oConfigSite.authRessource;
			case "authSecret":
				return oConfigSite.authSecret;
			case "authSslRequis":
				return oConfigSite.authSslRequis;
			case "sslJksChemin":
				return oConfigSite.sslJksChemin;
			case "sslJksMotDePasse":
				return oConfigSite.sslJksMotDePasse;
			case "authUrl":
				return oConfigSite.authUrl;
			case "cryptageSel":
				return oConfigSite.cryptageSel;
			case "cryptageMotDePasse":
				return oConfigSite.cryptageMotDePasse;
			case "siteUrlBase":
				return oConfigSite.siteUrlBase;
			case "siteNomAffichage":
				return oConfigSite.siteNomAffichage;
			case "jdbcClassePilote":
				return oConfigSite.jdbcClassePilote;
			case "jdbcUtilisateur":
				return oConfigSite.jdbcUtilisateur;
			case "jdbcMotDePasse":
				return oConfigSite.jdbcMotDePasse;
			case "jdbcTailleMaxPiscine":
				return oConfigSite.jdbcTailleMaxPiscine;
			case "jdbcMaxFileAttente":
				return oConfigSite.jdbcMaxFileAttente;
			case "jdbcTailleMinPiscine":
				return oConfigSite.jdbcTailleMinPiscine;
			case "jdbcMaxDeclarations":
				return oConfigSite.jdbcMaxDeclarations;
			case "jdbcMaxDeclarationsParConnexion":
				return oConfigSite.jdbcMaxDeclarationsParConnexion;
			case "jdbcTempsInactiviteMax":
				return oConfigSite.jdbcTempsInactiviteMax;
			case "jdbcDelaiConnexion":
				return oConfigSite.jdbcDelaiConnexion;
			case "jdbcHote":
				return oConfigSite.jdbcHote;
			case "jdbcPort":
				return oConfigSite.jdbcPort;
			case "jdbcBaseDeDonnees":
				return oConfigSite.jdbcBaseDeDonnees;
			case "solrUrl":
				return oConfigSite.solrUrl;
			case "solrUrlComputate":
				return oConfigSite.solrUrlComputate;
			case "compteFacebook":
				return oConfigSite.compteFacebook;
			case "compteTwitter":
				return oConfigSite.compteTwitter;
			case "compteInstagram":
				return oConfigSite.compteInstagram;
			case "compteYoutube":
				return oConfigSite.compteYoutube;
			case "comptePinterest":
				return oConfigSite.comptePinterest;
			case "compteMail":
				return oConfigSite.compteMail;
			case "roleAdmin":
				return oConfigSite.roleAdmin;
			case "mailAdmin":
				return oConfigSite.mailAdmin;
			case "nombreExecuteurs":
				return oConfigSite.nombreExecuteurs;
			case "openApiVersion":
				return oConfigSite.openApiVersion;
			case "apiDescription":
				return oConfigSite.apiDescription;
			case "apiTitre":
				return oConfigSite.apiTitre;
			case "apiTermsService":
				return oConfigSite.apiTermsService;
			case "apiVersion":
				return oConfigSite.apiVersion;
			case "apiContactMail":
				return oConfigSite.apiContactMail;
			case "apiLicenceNom":
				return oConfigSite.apiLicenceNom;
			case "apiLicenceUrl":
				return oConfigSite.apiLicenceUrl;
			case "apiNomHote":
				return oConfigSite.apiNomHote;
			case "apiCheminBase":
				return oConfigSite.apiCheminBase;
			case "statiqueUrlBase":
				return oConfigSite.statiqueUrlBase;
			case "mailHote":
				return oConfigSite.mailHote;
			case "mailPort":
				return oConfigSite.mailPort;
			case "mailUtilisateur":
				return oConfigSite.mailUtilisateur;
			case "mailMotDePasse":
				return oConfigSite.mailMotDePasse;
			case "mailDe":
				return oConfigSite.mailDe;
			case "mailAuth":
				return oConfigSite.mailAuth;
			case "mailSsl":
				return oConfigSite.mailSsl;
			case "siteZone":
				return oConfigSite.siteZone;
			case "authorizeApiLoginId1":
				return oConfigSite.authorizeApiLoginId1;
			case "authorizeTransactionKey1":
				return oConfigSite.authorizeTransactionKey1;
			case "schoolLocation1":
				return oConfigSite.schoolLocation1;
			case "authorizeApiLoginId2":
				return oConfigSite.authorizeApiLoginId2;
			case "authorizeTransactionKey2":
				return oConfigSite.authorizeTransactionKey2;
			case "schoolLocation2":
				return oConfigSite.schoolLocation2;
			case "authorizeEnvironment":
				return oConfigSite.authorizeEnvironment;
			case "authorizeUrl":
				return oConfigSite.authorizeUrl;
			case "paiementJour":
				return oConfigSite.paiementJour;
			case "paiementProchain":
				return oConfigSite.paiementProchain;
			default:
				return null;
		}
	}

	///////////////
	// attribuer //
	///////////////

	public boolean attribuerPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerConfigSite(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerConfigSite(String var, Object val) {
		ConfigSite oConfigSite = (ConfigSite)this;
		switch(var) {
			default:
				return null;
		}
	}

	/////////////
	// definir //
	/////////////

	public boolean definirPourClasse(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirConfigSite(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirConfigSite(String var, String val) {
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
		if(!(o instanceof ConfigSite))
			return false;
		ConfigSite that = (ConfigSite)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ConfigSite { ");
		sb.append(" }");
		return sb.toString();
	}
}
