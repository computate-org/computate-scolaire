package org.computate.scolaire.frFR.config;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import org.apache.commons.configuration2.INIConfiguration;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
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
 * Charge les propriétés dans le fichier de config de l'application dans des champs spécifiques. 
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class ConfigSiteGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ConfigSite.class);

	//////////////////
	// configChemin //
	//////////////////

	/**	 L'entité configChemin
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String configChemin;
	@JsonIgnore
	public Couverture<String> configCheminCouverture = new Couverture<String>().p(this).c(String.class).var("configChemin").o(configChemin);

	/**	<br/> L'entité configChemin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configChemin">Trouver l'entité configChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _configChemin(Couverture<String> c);

	public String getConfigChemin() {
		return configChemin;
	}
	public void setConfigChemin(String o) {
		this.configChemin = ConfigSite.staticSetConfigChemin(null, o);
		this.configCheminCouverture.dejaInitialise = true;
	}
	public static String staticSetConfigChemin(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrConfigChemin(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrConfigChemin(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqConfigChemin(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrConfigChemin(requeteSite_, ConfigSite.staticSolrConfigChemin(requeteSite_, ConfigSite.staticSetConfigChemin(requeteSite_, o)));
	}

	////////////
	// config //
	////////////

	/**	 L'entité config
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected INIConfiguration config;
	@JsonIgnore
	public Couverture<INIConfiguration> configCouverture = new Couverture<INIConfiguration>().p(this).c(INIConfiguration.class).var("config").o(config);

	/**	<br/> L'entité config
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:config">Trouver l'entité config dans Solr</a>
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
	public static INIConfiguration staticSetConfig(RequeteSiteFrFR requeteSite_, String o) {
		return null;
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

	/**	 L'entité identifiantSite
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String identifiantSite;
	@JsonIgnore
	public Couverture<String> identifiantSiteCouverture = new Couverture<String>().p(this).c(String.class).var("identifiantSite").o(identifiantSite);

	/**	<br/> L'entité identifiantSite
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:identifiantSite">Trouver l'entité identifiantSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _identifiantSite(Couverture<String> c);

	public String getIdentifiantSite() {
		return identifiantSite;
	}
	public void setIdentifiantSite(String o) {
		this.identifiantSite = ConfigSite.staticSetIdentifiantSite(null, o);
		this.identifiantSiteCouverture.dejaInitialise = true;
	}
	public static String staticSetIdentifiantSite(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrIdentifiantSite(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrIdentifiantSite(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqIdentifiantSite(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrIdentifiantSite(requeteSite_, ConfigSite.staticSolrIdentifiantSite(requeteSite_, ConfigSite.staticSetIdentifiantSite(requeteSite_, o)));
	}

	////////////////////
	// prefixeEchappe //
	////////////////////

	/**	 L'entité prefixeEchappe
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String prefixeEchappe;
	@JsonIgnore
	public Couverture<String> prefixeEchappeCouverture = new Couverture<String>().p(this).c(String.class).var("prefixeEchappe").o(prefixeEchappe);

	/**	<br/> L'entité prefixeEchappe
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:prefixeEchappe">Trouver l'entité prefixeEchappe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _prefixeEchappe(Couverture<String> c);

	public String getPrefixeEchappe() {
		return prefixeEchappe;
	}
	public void setPrefixeEchappe(String o) {
		this.prefixeEchappe = ConfigSite.staticSetPrefixeEchappe(null, o);
		this.prefixeEchappeCouverture.dejaInitialise = true;
	}
	public static String staticSetPrefixeEchappe(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrPrefixeEchappe(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrPrefixeEchappe(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqPrefixeEchappe(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrPrefixeEchappe(requeteSite_, ConfigSite.staticSolrPrefixeEchappe(requeteSite_, ConfigSite.staticSetPrefixeEchappe(requeteSite_, o)));
	}

	/////////////////
	// appliChemin //
	/////////////////

	/**	 L'entité appliChemin
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String appliChemin;
	@JsonIgnore
	public Couverture<String> appliCheminCouverture = new Couverture<String>().p(this).c(String.class).var("appliChemin").o(appliChemin);

	/**	<br/> L'entité appliChemin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:appliChemin">Trouver l'entité appliChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _appliChemin(Couverture<String> c);

	public String getAppliChemin() {
		return appliChemin;
	}
	public void setAppliChemin(String o) {
		this.appliChemin = ConfigSite.staticSetAppliChemin(null, o);
		this.appliCheminCouverture.dejaInitialise = true;
	}
	public static String staticSetAppliChemin(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAppliChemin(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAppliChemin(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAppliChemin(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAppliChemin(requeteSite_, ConfigSite.staticSolrAppliChemin(requeteSite_, ConfigSite.staticSetAppliChemin(requeteSite_, o)));
	}

	////////////////////
	// racineDocument //
	////////////////////

	/**	 L'entité racineDocument
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String racineDocument;
	@JsonIgnore
	public Couverture<String> racineDocumentCouverture = new Couverture<String>().p(this).c(String.class).var("racineDocument").o(racineDocument);

	/**	<br/> L'entité racineDocument
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:racineDocument">Trouver l'entité racineDocument dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _racineDocument(Couverture<String> c);

	public String getRacineDocument() {
		return racineDocument;
	}
	public void setRacineDocument(String o) {
		this.racineDocument = ConfigSite.staticSetRacineDocument(null, o);
		this.racineDocumentCouverture.dejaInitialise = true;
	}
	public static String staticSetRacineDocument(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrRacineDocument(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrRacineDocument(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqRacineDocument(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrRacineDocument(requeteSite_, ConfigSite.staticSolrRacineDocument(requeteSite_, ConfigSite.staticSetRacineDocument(requeteSite_, o)));
	}

	///////////////////
	// nomEntreprise //
	///////////////////

	/**	 L'entité nomEntreprise
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String nomEntreprise;
	@JsonIgnore
	public Couverture<String> nomEntrepriseCouverture = new Couverture<String>().p(this).c(String.class).var("nomEntreprise").o(nomEntreprise);

	/**	<br/> L'entité nomEntreprise
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:nomEntreprise">Trouver l'entité nomEntreprise dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _nomEntreprise(Couverture<String> c);

	public String getNomEntreprise() {
		return nomEntreprise;
	}
	public void setNomEntreprise(String o) {
		this.nomEntreprise = ConfigSite.staticSetNomEntreprise(null, o);
		this.nomEntrepriseCouverture.dejaInitialise = true;
	}
	public static String staticSetNomEntreprise(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrNomEntreprise(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrNomEntreprise(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqNomEntreprise(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrNomEntreprise(requeteSite_, ConfigSite.staticSolrNomEntreprise(requeteSite_, ConfigSite.staticSetNomEntreprise(requeteSite_, o)));
	}

	////////////////
	// nomDomaine //
	////////////////

	/**	 L'entité nomDomaine
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String nomDomaine;
	@JsonIgnore
	public Couverture<String> nomDomaineCouverture = new Couverture<String>().p(this).c(String.class).var("nomDomaine").o(nomDomaine);

	/**	<br/> L'entité nomDomaine
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:nomDomaine">Trouver l'entité nomDomaine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _nomDomaine(Couverture<String> c);

	public String getNomDomaine() {
		return nomDomaine;
	}
	public void setNomDomaine(String o) {
		this.nomDomaine = ConfigSite.staticSetNomDomaine(null, o);
		this.nomDomaineCouverture.dejaInitialise = true;
	}
	public static String staticSetNomDomaine(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrNomDomaine(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrNomDomaine(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqNomDomaine(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrNomDomaine(requeteSite_, ConfigSite.staticSolrNomDomaine(requeteSite_, ConfigSite.staticSetNomDomaine(requeteSite_, o)));
	}

	/////////////////
	// siteNomHote //
	/////////////////

	/**	 L'entité siteNomHote
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteNomHote;
	@JsonIgnore
	public Couverture<String> siteNomHoteCouverture = new Couverture<String>().p(this).c(String.class).var("siteNomHote").o(siteNomHote);

	/**	<br/> L'entité siteNomHote
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteNomHote">Trouver l'entité siteNomHote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteNomHote(Couverture<String> c);

	public String getSiteNomHote() {
		return siteNomHote;
	}
	public void setSiteNomHote(String o) {
		this.siteNomHote = ConfigSite.staticSetSiteNomHote(null, o);
		this.siteNomHoteCouverture.dejaInitialise = true;
	}
	public static String staticSetSiteNomHote(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrSiteNomHote(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSiteNomHote(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSiteNomHote(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSiteNomHote(requeteSite_, ConfigSite.staticSolrSiteNomHote(requeteSite_, ConfigSite.staticSetSiteNomHote(requeteSite_, o)));
	}

	//////////////
	// sitePort //
	//////////////

	/**	 L'entité sitePort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer sitePort;
	@JsonIgnore
	public Couverture<Integer> sitePortCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("sitePort").o(sitePort);

	/**	<br/> L'entité sitePort
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sitePort">Trouver l'entité sitePort dans Solr</a>
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
	public void setSitePort(String o) {
		this.sitePort = ConfigSite.staticSetSitePort(null, o);
		this.sitePortCouverture.dejaInitialise = true;
	}
	public static Integer staticSetSitePort(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrSitePort(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrSitePort(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSitePort(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSitePort(requeteSite_, ConfigSite.staticSolrSitePort(requeteSite_, ConfigSite.staticSetSitePort(requeteSite_, o)));
	}

	///////////////////
	// siteInstances //
	///////////////////

	/**	 L'entité siteInstances
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer siteInstances;
	@JsonIgnore
	public Couverture<Integer> siteInstancesCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("siteInstances").o(siteInstances);

	/**	<br/> L'entité siteInstances
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteInstances">Trouver l'entité siteInstances dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteInstances(Couverture<Integer> c);

	public Integer getSiteInstances() {
		return siteInstances;
	}

	public void setSiteInstances(Integer siteInstances) {
		this.siteInstances = siteInstances;
		this.siteInstancesCouverture.dejaInitialise = true;
	}
	public void setSiteInstances(String o) {
		this.siteInstances = ConfigSite.staticSetSiteInstances(null, o);
		this.siteInstancesCouverture.dejaInitialise = true;
	}
	public static Integer staticSetSiteInstances(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected ConfigSite siteInstancesInit() {
		if(!siteInstancesCouverture.dejaInitialise) {
			_siteInstances(siteInstancesCouverture);
			if(siteInstances == null)
				setSiteInstances(siteInstancesCouverture.o);
		}
		siteInstancesCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	public static Object staticSolrSiteInstances(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrSiteInstances(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSiteInstances(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSiteInstances(requeteSite_, ConfigSite.staticSolrSiteInstances(requeteSite_, ConfigSite.staticSetSiteInstances(requeteSite_, o)));
	}

	/////////////////
	// authRoyaume //
	/////////////////

	/**	 L'entité authRoyaume
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authRoyaume;
	@JsonIgnore
	public Couverture<String> authRoyaumeCouverture = new Couverture<String>().p(this).c(String.class).var("authRoyaume").o(authRoyaume);

	/**	<br/> L'entité authRoyaume
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authRoyaume">Trouver l'entité authRoyaume dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authRoyaume(Couverture<String> c);

	public String getAuthRoyaume() {
		return authRoyaume;
	}
	public void setAuthRoyaume(String o) {
		this.authRoyaume = ConfigSite.staticSetAuthRoyaume(null, o);
		this.authRoyaumeCouverture.dejaInitialise = true;
	}
	public static String staticSetAuthRoyaume(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAuthRoyaume(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthRoyaume(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthRoyaume(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthRoyaume(requeteSite_, ConfigSite.staticSolrAuthRoyaume(requeteSite_, ConfigSite.staticSetAuthRoyaume(requeteSite_, o)));
	}

	///////////////////
	// authRessource //
	///////////////////

	/**	 L'entité authRessource
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authRessource;
	@JsonIgnore
	public Couverture<String> authRessourceCouverture = new Couverture<String>().p(this).c(String.class).var("authRessource").o(authRessource);

	/**	<br/> L'entité authRessource
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authRessource">Trouver l'entité authRessource dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authRessource(Couverture<String> c);

	public String getAuthRessource() {
		return authRessource;
	}
	public void setAuthRessource(String o) {
		this.authRessource = ConfigSite.staticSetAuthRessource(null, o);
		this.authRessourceCouverture.dejaInitialise = true;
	}
	public static String staticSetAuthRessource(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAuthRessource(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthRessource(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthRessource(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthRessource(requeteSite_, ConfigSite.staticSolrAuthRessource(requeteSite_, ConfigSite.staticSetAuthRessource(requeteSite_, o)));
	}

	////////////////
	// authSecret //
	////////////////

	/**	 L'entité authSecret
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authSecret;
	@JsonIgnore
	public Couverture<String> authSecretCouverture = new Couverture<String>().p(this).c(String.class).var("authSecret").o(authSecret);

	/**	<br/> L'entité authSecret
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authSecret">Trouver l'entité authSecret dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authSecret(Couverture<String> c);

	public String getAuthSecret() {
		return authSecret;
	}
	public void setAuthSecret(String o) {
		this.authSecret = ConfigSite.staticSetAuthSecret(null, o);
		this.authSecretCouverture.dejaInitialise = true;
	}
	public static String staticSetAuthSecret(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAuthSecret(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthSecret(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthSecret(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthSecret(requeteSite_, ConfigSite.staticSolrAuthSecret(requeteSite_, ConfigSite.staticSetAuthSecret(requeteSite_, o)));
	}

	///////////////////
	// authSslRequis //
	///////////////////

	/**	 L'entité authSslRequis
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authSslRequis;
	@JsonIgnore
	public Couverture<String> authSslRequisCouverture = new Couverture<String>().p(this).c(String.class).var("authSslRequis").o(authSslRequis);

	/**	<br/> L'entité authSslRequis
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authSslRequis">Trouver l'entité authSslRequis dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authSslRequis(Couverture<String> c);

	public String getAuthSslRequis() {
		return authSslRequis;
	}
	public void setAuthSslRequis(String o) {
		this.authSslRequis = ConfigSite.staticSetAuthSslRequis(null, o);
		this.authSslRequisCouverture.dejaInitialise = true;
	}
	public static String staticSetAuthSslRequis(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAuthSslRequis(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthSslRequis(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthSslRequis(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthSslRequis(requeteSite_, ConfigSite.staticSolrAuthSslRequis(requeteSite_, ConfigSite.staticSetAuthSslRequis(requeteSite_, o)));
	}

	//////////////////
	// sslJksChemin //
	//////////////////

	/**	 L'entité sslJksChemin
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sslJksChemin;
	@JsonIgnore
	public Couverture<String> sslJksCheminCouverture = new Couverture<String>().p(this).c(String.class).var("sslJksChemin").o(sslJksChemin);

	/**	<br/> L'entité sslJksChemin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sslJksChemin">Trouver l'entité sslJksChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sslJksChemin(Couverture<String> c);

	public String getSslJksChemin() {
		return sslJksChemin;
	}
	public void setSslJksChemin(String o) {
		this.sslJksChemin = ConfigSite.staticSetSslJksChemin(null, o);
		this.sslJksCheminCouverture.dejaInitialise = true;
	}
	public static String staticSetSslJksChemin(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrSslJksChemin(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSslJksChemin(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSslJksChemin(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSslJksChemin(requeteSite_, ConfigSite.staticSolrSslJksChemin(requeteSite_, ConfigSite.staticSetSslJksChemin(requeteSite_, o)));
	}

	//////////////////////
	// sslJksMotDePasse //
	//////////////////////

	/**	 L'entité sslJksMotDePasse
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String sslJksMotDePasse;
	@JsonIgnore
	public Couverture<String> sslJksMotDePasseCouverture = new Couverture<String>().p(this).c(String.class).var("sslJksMotDePasse").o(sslJksMotDePasse);

	/**	<br/> L'entité sslJksMotDePasse
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sslJksMotDePasse">Trouver l'entité sslJksMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sslJksMotDePasse(Couverture<String> c);

	public String getSslJksMotDePasse() {
		return sslJksMotDePasse;
	}
	public void setSslJksMotDePasse(String o) {
		this.sslJksMotDePasse = ConfigSite.staticSetSslJksMotDePasse(null, o);
		this.sslJksMotDePasseCouverture.dejaInitialise = true;
	}
	public static String staticSetSslJksMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrSslJksMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSslJksMotDePasse(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSslJksMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSslJksMotDePasse(requeteSite_, ConfigSite.staticSolrSslJksMotDePasse(requeteSite_, ConfigSite.staticSetSslJksMotDePasse(requeteSite_, o)));
	}

	/////////////
	// authUrl //
	/////////////

	/**	 L'entité authUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authUrl;
	@JsonIgnore
	public Couverture<String> authUrlCouverture = new Couverture<String>().p(this).c(String.class).var("authUrl").o(authUrl);

	/**	<br/> L'entité authUrl
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authUrl">Trouver l'entité authUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authUrl(Couverture<String> c);

	public String getAuthUrl() {
		return authUrl;
	}
	public void setAuthUrl(String o) {
		this.authUrl = ConfigSite.staticSetAuthUrl(null, o);
		this.authUrlCouverture.dejaInitialise = true;
	}
	public static String staticSetAuthUrl(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAuthUrl(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthUrl(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthUrl(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthUrl(requeteSite_, ConfigSite.staticSolrAuthUrl(requeteSite_, ConfigSite.staticSetAuthUrl(requeteSite_, o)));
	}

	/////////////////
	// cryptageSel //
	/////////////////

	/**	 L'entité cryptageSel
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String cryptageSel;
	@JsonIgnore
	public Couverture<String> cryptageSelCouverture = new Couverture<String>().p(this).c(String.class).var("cryptageSel").o(cryptageSel);

	/**	<br/> L'entité cryptageSel
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cryptageSel">Trouver l'entité cryptageSel dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageSel(Couverture<String> c);

	public String getCryptageSel() {
		return cryptageSel;
	}
	public void setCryptageSel(String o) {
		this.cryptageSel = ConfigSite.staticSetCryptageSel(null, o);
		this.cryptageSelCouverture.dejaInitialise = true;
	}
	public static String staticSetCryptageSel(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrCryptageSel(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrCryptageSel(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqCryptageSel(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrCryptageSel(requeteSite_, ConfigSite.staticSolrCryptageSel(requeteSite_, ConfigSite.staticSetCryptageSel(requeteSite_, o)));
	}

	////////////////////////
	// cryptageMotDePasse //
	////////////////////////

	/**	 L'entité cryptageMotDePasse
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String cryptageMotDePasse;
	@JsonIgnore
	public Couverture<String> cryptageMotDePasseCouverture = new Couverture<String>().p(this).c(String.class).var("cryptageMotDePasse").o(cryptageMotDePasse);

	/**	<br/> L'entité cryptageMotDePasse
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:cryptageMotDePasse">Trouver l'entité cryptageMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageMotDePasse(Couverture<String> c);

	public String getCryptageMotDePasse() {
		return cryptageMotDePasse;
	}
	public void setCryptageMotDePasse(String o) {
		this.cryptageMotDePasse = ConfigSite.staticSetCryptageMotDePasse(null, o);
		this.cryptageMotDePasseCouverture.dejaInitialise = true;
	}
	public static String staticSetCryptageMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrCryptageMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrCryptageMotDePasse(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqCryptageMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrCryptageMotDePasse(requeteSite_, ConfigSite.staticSolrCryptageMotDePasse(requeteSite_, ConfigSite.staticSetCryptageMotDePasse(requeteSite_, o)));
	}

	/////////////////
	// siteUrlBase //
	/////////////////

	/**	 L'entité siteUrlBase
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteUrlBase;
	@JsonIgnore
	public Couverture<String> siteUrlBaseCouverture = new Couverture<String>().p(this).c(String.class).var("siteUrlBase").o(siteUrlBase);

	/**	<br/> L'entité siteUrlBase
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteUrlBase">Trouver l'entité siteUrlBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteUrlBase(Couverture<String> c);

	public String getSiteUrlBase() {
		return siteUrlBase;
	}
	public void setSiteUrlBase(String o) {
		this.siteUrlBase = ConfigSite.staticSetSiteUrlBase(null, o);
		this.siteUrlBaseCouverture.dejaInitialise = true;
	}
	public static String staticSetSiteUrlBase(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrSiteUrlBase(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSiteUrlBase(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSiteUrlBase(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSiteUrlBase(requeteSite_, ConfigSite.staticSolrSiteUrlBase(requeteSite_, ConfigSite.staticSetSiteUrlBase(requeteSite_, o)));
	}

	//////////////////////
	// siteNomAffichage //
	//////////////////////

	/**	 L'entité siteNomAffichage
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteNomAffichage;
	@JsonIgnore
	public Couverture<String> siteNomAffichageCouverture = new Couverture<String>().p(this).c(String.class).var("siteNomAffichage").o(siteNomAffichage);

	/**	<br/> L'entité siteNomAffichage
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteNomAffichage">Trouver l'entité siteNomAffichage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteNomAffichage(Couverture<String> c);

	public String getSiteNomAffichage() {
		return siteNomAffichage;
	}
	public void setSiteNomAffichage(String o) {
		this.siteNomAffichage = ConfigSite.staticSetSiteNomAffichage(null, o);
		this.siteNomAffichageCouverture.dejaInitialise = true;
	}
	public static String staticSetSiteNomAffichage(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrSiteNomAffichage(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSiteNomAffichage(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSiteNomAffichage(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSiteNomAffichage(requeteSite_, ConfigSite.staticSolrSiteNomAffichage(requeteSite_, ConfigSite.staticSetSiteNomAffichage(requeteSite_, o)));
	}

	//////////////////////
	// jdbcClassePilote //
	//////////////////////

	/**	 L'entité jdbcClassePilote
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcClassePilote;
	@JsonIgnore
	public Couverture<String> jdbcClassePiloteCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcClassePilote").o(jdbcClassePilote);

	/**	<br/> L'entité jdbcClassePilote
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcClassePilote">Trouver l'entité jdbcClassePilote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcClassePilote(Couverture<String> c);

	public String getJdbcClassePilote() {
		return jdbcClassePilote;
	}
	public void setJdbcClassePilote(String o) {
		this.jdbcClassePilote = ConfigSite.staticSetJdbcClassePilote(null, o);
		this.jdbcClassePiloteCouverture.dejaInitialise = true;
	}
	public static String staticSetJdbcClassePilote(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrJdbcClassePilote(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrJdbcClassePilote(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcClassePilote(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcClassePilote(requeteSite_, ConfigSite.staticSolrJdbcClassePilote(requeteSite_, ConfigSite.staticSetJdbcClassePilote(requeteSite_, o)));
	}

	/////////////////////
	// jdbcUtilisateur //
	/////////////////////

	/**	 L'entité jdbcUtilisateur
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcUtilisateur;
	@JsonIgnore
	public Couverture<String> jdbcUtilisateurCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcUtilisateur").o(jdbcUtilisateur);

	/**	<br/> L'entité jdbcUtilisateur
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcUtilisateur">Trouver l'entité jdbcUtilisateur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcUtilisateur(Couverture<String> c);

	public String getJdbcUtilisateur() {
		return jdbcUtilisateur;
	}
	public void setJdbcUtilisateur(String o) {
		this.jdbcUtilisateur = ConfigSite.staticSetJdbcUtilisateur(null, o);
		this.jdbcUtilisateurCouverture.dejaInitialise = true;
	}
	public static String staticSetJdbcUtilisateur(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrJdbcUtilisateur(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrJdbcUtilisateur(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcUtilisateur(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcUtilisateur(requeteSite_, ConfigSite.staticSolrJdbcUtilisateur(requeteSite_, ConfigSite.staticSetJdbcUtilisateur(requeteSite_, o)));
	}

	////////////////////
	// jdbcMotDePasse //
	////////////////////

	/**	 L'entité jdbcMotDePasse
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcMotDePasse;
	@JsonIgnore
	public Couverture<String> jdbcMotDePasseCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcMotDePasse").o(jdbcMotDePasse);

	/**	<br/> L'entité jdbcMotDePasse
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcMotDePasse">Trouver l'entité jdbcMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMotDePasse(Couverture<String> c);

	public String getJdbcMotDePasse() {
		return jdbcMotDePasse;
	}
	public void setJdbcMotDePasse(String o) {
		this.jdbcMotDePasse = ConfigSite.staticSetJdbcMotDePasse(null, o);
		this.jdbcMotDePasseCouverture.dejaInitialise = true;
	}
	public static String staticSetJdbcMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrJdbcMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrJdbcMotDePasse(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcMotDePasse(requeteSite_, ConfigSite.staticSolrJdbcMotDePasse(requeteSite_, ConfigSite.staticSetJdbcMotDePasse(requeteSite_, o)));
	}

	//////////////////////////
	// jdbcTailleMaxPiscine //
	//////////////////////////

	/**	 L'entité jdbcTailleMaxPiscine
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcTailleMaxPiscine;
	@JsonIgnore
	public Couverture<Integer> jdbcTailleMaxPiscineCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcTailleMaxPiscine").o(jdbcTailleMaxPiscine);

	/**	<br/> L'entité jdbcTailleMaxPiscine
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcTailleMaxPiscine">Trouver l'entité jdbcTailleMaxPiscine dans Solr</a>
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
	public void setJdbcTailleMaxPiscine(String o) {
		this.jdbcTailleMaxPiscine = ConfigSite.staticSetJdbcTailleMaxPiscine(null, o);
		this.jdbcTailleMaxPiscineCouverture.dejaInitialise = true;
	}
	public static Integer staticSetJdbcTailleMaxPiscine(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrJdbcTailleMaxPiscine(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrJdbcTailleMaxPiscine(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcTailleMaxPiscine(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcTailleMaxPiscine(requeteSite_, ConfigSite.staticSolrJdbcTailleMaxPiscine(requeteSite_, ConfigSite.staticSetJdbcTailleMaxPiscine(requeteSite_, o)));
	}

	////////////////////////
	// jdbcMaxFileAttente //
	////////////////////////

	/**	 L'entité jdbcMaxFileAttente
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxFileAttente;
	@JsonIgnore
	public Couverture<Integer> jdbcMaxFileAttenteCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcMaxFileAttente").o(jdbcMaxFileAttente);

	/**	<br/> L'entité jdbcMaxFileAttente
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcMaxFileAttente">Trouver l'entité jdbcMaxFileAttente dans Solr</a>
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
	public void setJdbcMaxFileAttente(String o) {
		this.jdbcMaxFileAttente = ConfigSite.staticSetJdbcMaxFileAttente(null, o);
		this.jdbcMaxFileAttenteCouverture.dejaInitialise = true;
	}
	public static Integer staticSetJdbcMaxFileAttente(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrJdbcMaxFileAttente(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrJdbcMaxFileAttente(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcMaxFileAttente(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcMaxFileAttente(requeteSite_, ConfigSite.staticSolrJdbcMaxFileAttente(requeteSite_, ConfigSite.staticSetJdbcMaxFileAttente(requeteSite_, o)));
	}

	//////////////////////////
	// jdbcTailleMinPiscine //
	//////////////////////////

	/**	 L'entité jdbcTailleMinPiscine
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcTailleMinPiscine;
	@JsonIgnore
	public Couverture<Integer> jdbcTailleMinPiscineCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcTailleMinPiscine").o(jdbcTailleMinPiscine);

	/**	<br/> L'entité jdbcTailleMinPiscine
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcTailleMinPiscine">Trouver l'entité jdbcTailleMinPiscine dans Solr</a>
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
	public void setJdbcTailleMinPiscine(String o) {
		this.jdbcTailleMinPiscine = ConfigSite.staticSetJdbcTailleMinPiscine(null, o);
		this.jdbcTailleMinPiscineCouverture.dejaInitialise = true;
	}
	public static Integer staticSetJdbcTailleMinPiscine(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrJdbcTailleMinPiscine(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrJdbcTailleMinPiscine(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcTailleMinPiscine(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcTailleMinPiscine(requeteSite_, ConfigSite.staticSolrJdbcTailleMinPiscine(requeteSite_, ConfigSite.staticSetJdbcTailleMinPiscine(requeteSite_, o)));
	}

	/////////////////////////
	// jdbcMaxDeclarations //
	/////////////////////////

	/**	 L'entité jdbcMaxDeclarations
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxDeclarations;
	@JsonIgnore
	public Couverture<Integer> jdbcMaxDeclarationsCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcMaxDeclarations").o(jdbcMaxDeclarations);

	/**	<br/> L'entité jdbcMaxDeclarations
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcMaxDeclarations">Trouver l'entité jdbcMaxDeclarations dans Solr</a>
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
	public void setJdbcMaxDeclarations(String o) {
		this.jdbcMaxDeclarations = ConfigSite.staticSetJdbcMaxDeclarations(null, o);
		this.jdbcMaxDeclarationsCouverture.dejaInitialise = true;
	}
	public static Integer staticSetJdbcMaxDeclarations(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrJdbcMaxDeclarations(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrJdbcMaxDeclarations(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcMaxDeclarations(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcMaxDeclarations(requeteSite_, ConfigSite.staticSolrJdbcMaxDeclarations(requeteSite_, ConfigSite.staticSetJdbcMaxDeclarations(requeteSite_, o)));
	}

	/////////////////////////////////////
	// jdbcMaxDeclarationsParConnexion //
	/////////////////////////////////////

	/**	 L'entité jdbcMaxDeclarationsParConnexion
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcMaxDeclarationsParConnexion;
	@JsonIgnore
	public Couverture<Integer> jdbcMaxDeclarationsParConnexionCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcMaxDeclarationsParConnexion").o(jdbcMaxDeclarationsParConnexion);

	/**	<br/> L'entité jdbcMaxDeclarationsParConnexion
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcMaxDeclarationsParConnexion">Trouver l'entité jdbcMaxDeclarationsParConnexion dans Solr</a>
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
	public void setJdbcMaxDeclarationsParConnexion(String o) {
		this.jdbcMaxDeclarationsParConnexion = ConfigSite.staticSetJdbcMaxDeclarationsParConnexion(null, o);
		this.jdbcMaxDeclarationsParConnexionCouverture.dejaInitialise = true;
	}
	public static Integer staticSetJdbcMaxDeclarationsParConnexion(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrJdbcMaxDeclarationsParConnexion(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrJdbcMaxDeclarationsParConnexion(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcMaxDeclarationsParConnexion(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcMaxDeclarationsParConnexion(requeteSite_, ConfigSite.staticSolrJdbcMaxDeclarationsParConnexion(requeteSite_, ConfigSite.staticSetJdbcMaxDeclarationsParConnexion(requeteSite_, o)));
	}

	////////////////////////////
	// jdbcTempsInactiviteMax //
	////////////////////////////

	/**	 L'entité jdbcTempsInactiviteMax
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcTempsInactiviteMax;
	@JsonIgnore
	public Couverture<Integer> jdbcTempsInactiviteMaxCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcTempsInactiviteMax").o(jdbcTempsInactiviteMax);

	/**	<br/> L'entité jdbcTempsInactiviteMax
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcTempsInactiviteMax">Trouver l'entité jdbcTempsInactiviteMax dans Solr</a>
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
	public void setJdbcTempsInactiviteMax(String o) {
		this.jdbcTempsInactiviteMax = ConfigSite.staticSetJdbcTempsInactiviteMax(null, o);
		this.jdbcTempsInactiviteMaxCouverture.dejaInitialise = true;
	}
	public static Integer staticSetJdbcTempsInactiviteMax(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrJdbcTempsInactiviteMax(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrJdbcTempsInactiviteMax(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcTempsInactiviteMax(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcTempsInactiviteMax(requeteSite_, ConfigSite.staticSolrJdbcTempsInactiviteMax(requeteSite_, ConfigSite.staticSetJdbcTempsInactiviteMax(requeteSite_, o)));
	}

	////////////////////////
	// jdbcDelaiConnexion //
	////////////////////////

	/**	 L'entité jdbcDelaiConnexion
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcDelaiConnexion;
	@JsonIgnore
	public Couverture<Integer> jdbcDelaiConnexionCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcDelaiConnexion").o(jdbcDelaiConnexion);

	/**	<br/> L'entité jdbcDelaiConnexion
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcDelaiConnexion">Trouver l'entité jdbcDelaiConnexion dans Solr</a>
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
	public void setJdbcDelaiConnexion(String o) {
		this.jdbcDelaiConnexion = ConfigSite.staticSetJdbcDelaiConnexion(null, o);
		this.jdbcDelaiConnexionCouverture.dejaInitialise = true;
	}
	public static Integer staticSetJdbcDelaiConnexion(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrJdbcDelaiConnexion(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrJdbcDelaiConnexion(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcDelaiConnexion(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcDelaiConnexion(requeteSite_, ConfigSite.staticSolrJdbcDelaiConnexion(requeteSite_, ConfigSite.staticSetJdbcDelaiConnexion(requeteSite_, o)));
	}

	//////////////
	// jdbcHote //
	//////////////

	/**	 L'entité jdbcHote
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcHote;
	@JsonIgnore
	public Couverture<String> jdbcHoteCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcHote").o(jdbcHote);

	/**	<br/> L'entité jdbcHote
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcHote">Trouver l'entité jdbcHote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcHote(Couverture<String> c);

	public String getJdbcHote() {
		return jdbcHote;
	}
	public void setJdbcHote(String o) {
		this.jdbcHote = ConfigSite.staticSetJdbcHote(null, o);
		this.jdbcHoteCouverture.dejaInitialise = true;
	}
	public static String staticSetJdbcHote(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrJdbcHote(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrJdbcHote(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcHote(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcHote(requeteSite_, ConfigSite.staticSolrJdbcHote(requeteSite_, ConfigSite.staticSetJdbcHote(requeteSite_, o)));
	}

	//////////////
	// jdbcPort //
	//////////////

	/**	 L'entité jdbcPort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer jdbcPort;
	@JsonIgnore
	public Couverture<Integer> jdbcPortCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcPort").o(jdbcPort);

	/**	<br/> L'entité jdbcPort
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcPort">Trouver l'entité jdbcPort dans Solr</a>
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
	public void setJdbcPort(String o) {
		this.jdbcPort = ConfigSite.staticSetJdbcPort(null, o);
		this.jdbcPortCouverture.dejaInitialise = true;
	}
	public static Integer staticSetJdbcPort(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrJdbcPort(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrJdbcPort(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcPort(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcPort(requeteSite_, ConfigSite.staticSolrJdbcPort(requeteSite_, ConfigSite.staticSetJdbcPort(requeteSite_, o)));
	}

	///////////////////////
	// jdbcBaseDeDonnees //
	///////////////////////

	/**	 L'entité jdbcBaseDeDonnees
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String jdbcBaseDeDonnees;
	@JsonIgnore
	public Couverture<String> jdbcBaseDeDonneesCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcBaseDeDonnees").o(jdbcBaseDeDonnees);

	/**	<br/> L'entité jdbcBaseDeDonnees
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcBaseDeDonnees">Trouver l'entité jdbcBaseDeDonnees dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcBaseDeDonnees(Couverture<String> c);

	public String getJdbcBaseDeDonnees() {
		return jdbcBaseDeDonnees;
	}
	public void setJdbcBaseDeDonnees(String o) {
		this.jdbcBaseDeDonnees = ConfigSite.staticSetJdbcBaseDeDonnees(null, o);
		this.jdbcBaseDeDonneesCouverture.dejaInitialise = true;
	}
	public static String staticSetJdbcBaseDeDonnees(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrJdbcBaseDeDonnees(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrJdbcBaseDeDonnees(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqJdbcBaseDeDonnees(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrJdbcBaseDeDonnees(requeteSite_, ConfigSite.staticSolrJdbcBaseDeDonnees(requeteSite_, ConfigSite.staticSetJdbcBaseDeDonnees(requeteSite_, o)));
	}

	/////////////
	// solrUrl //
	/////////////

	/**	 L'entité solrUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String solrUrl;
	@JsonIgnore
	public Couverture<String> solrUrlCouverture = new Couverture<String>().p(this).c(String.class).var("solrUrl").o(solrUrl);

	/**	<br/> L'entité solrUrl
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:solrUrl">Trouver l'entité solrUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrUrl(Couverture<String> c);

	public String getSolrUrl() {
		return solrUrl;
	}
	public void setSolrUrl(String o) {
		this.solrUrl = ConfigSite.staticSetSolrUrl(null, o);
		this.solrUrlCouverture.dejaInitialise = true;
	}
	public static String staticSetSolrUrl(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrSolrUrl(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSolrUrl(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSolrUrl(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSolrUrl(requeteSite_, ConfigSite.staticSolrSolrUrl(requeteSite_, ConfigSite.staticSetSolrUrl(requeteSite_, o)));
	}

	//////////////////////
	// solrUrlComputate //
	//////////////////////

	/**	 L'entité solrUrlComputate
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String solrUrlComputate;
	@JsonIgnore
	public Couverture<String> solrUrlComputateCouverture = new Couverture<String>().p(this).c(String.class).var("solrUrlComputate").o(solrUrlComputate);

	/**	<br/> L'entité solrUrlComputate
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:solrUrlComputate">Trouver l'entité solrUrlComputate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrUrlComputate(Couverture<String> c);

	public String getSolrUrlComputate() {
		return solrUrlComputate;
	}
	public void setSolrUrlComputate(String o) {
		this.solrUrlComputate = ConfigSite.staticSetSolrUrlComputate(null, o);
		this.solrUrlComputateCouverture.dejaInitialise = true;
	}
	public static String staticSetSolrUrlComputate(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrSolrUrlComputate(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSolrUrlComputate(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSolrUrlComputate(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSolrUrlComputate(requeteSite_, ConfigSite.staticSolrSolrUrlComputate(requeteSite_, ConfigSite.staticSetSolrUrlComputate(requeteSite_, o)));
	}

	////////////////////
	// compteFacebook //
	////////////////////

	/**	 L'entité compteFacebook
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String compteFacebook;
	@JsonIgnore
	public Couverture<String> compteFacebookCouverture = new Couverture<String>().p(this).c(String.class).var("compteFacebook").o(compteFacebook);

	/**	<br/> L'entité compteFacebook
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteFacebook">Trouver l'entité compteFacebook dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteFacebook(Couverture<String> c);

	public String getCompteFacebook() {
		return compteFacebook;
	}
	public void setCompteFacebook(String o) {
		this.compteFacebook = ConfigSite.staticSetCompteFacebook(null, o);
		this.compteFacebookCouverture.dejaInitialise = true;
	}
	public static String staticSetCompteFacebook(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrCompteFacebook(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrCompteFacebook(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqCompteFacebook(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrCompteFacebook(requeteSite_, ConfigSite.staticSolrCompteFacebook(requeteSite_, ConfigSite.staticSetCompteFacebook(requeteSite_, o)));
	}

	///////////////////
	// compteTwitter //
	///////////////////

	/**	 L'entité compteTwitter
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String compteTwitter;
	@JsonIgnore
	public Couverture<String> compteTwitterCouverture = new Couverture<String>().p(this).c(String.class).var("compteTwitter").o(compteTwitter);

	/**	<br/> L'entité compteTwitter
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteTwitter">Trouver l'entité compteTwitter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteTwitter(Couverture<String> c);

	public String getCompteTwitter() {
		return compteTwitter;
	}
	public void setCompteTwitter(String o) {
		this.compteTwitter = ConfigSite.staticSetCompteTwitter(null, o);
		this.compteTwitterCouverture.dejaInitialise = true;
	}
	public static String staticSetCompteTwitter(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrCompteTwitter(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrCompteTwitter(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqCompteTwitter(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrCompteTwitter(requeteSite_, ConfigSite.staticSolrCompteTwitter(requeteSite_, ConfigSite.staticSetCompteTwitter(requeteSite_, o)));
	}

	/////////////////////
	// compteInstagram //
	/////////////////////

	/**	 L'entité compteInstagram
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String compteInstagram;
	@JsonIgnore
	public Couverture<String> compteInstagramCouverture = new Couverture<String>().p(this).c(String.class).var("compteInstagram").o(compteInstagram);

	/**	<br/> L'entité compteInstagram
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteInstagram">Trouver l'entité compteInstagram dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteInstagram(Couverture<String> c);

	public String getCompteInstagram() {
		return compteInstagram;
	}
	public void setCompteInstagram(String o) {
		this.compteInstagram = ConfigSite.staticSetCompteInstagram(null, o);
		this.compteInstagramCouverture.dejaInitialise = true;
	}
	public static String staticSetCompteInstagram(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrCompteInstagram(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrCompteInstagram(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqCompteInstagram(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrCompteInstagram(requeteSite_, ConfigSite.staticSolrCompteInstagram(requeteSite_, ConfigSite.staticSetCompteInstagram(requeteSite_, o)));
	}

	///////////////////
	// compteYoutube //
	///////////////////

	/**	 L'entité compteYoutube
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String compteYoutube;
	@JsonIgnore
	public Couverture<String> compteYoutubeCouverture = new Couverture<String>().p(this).c(String.class).var("compteYoutube").o(compteYoutube);

	/**	<br/> L'entité compteYoutube
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteYoutube">Trouver l'entité compteYoutube dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteYoutube(Couverture<String> c);

	public String getCompteYoutube() {
		return compteYoutube;
	}
	public void setCompteYoutube(String o) {
		this.compteYoutube = ConfigSite.staticSetCompteYoutube(null, o);
		this.compteYoutubeCouverture.dejaInitialise = true;
	}
	public static String staticSetCompteYoutube(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrCompteYoutube(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrCompteYoutube(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqCompteYoutube(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrCompteYoutube(requeteSite_, ConfigSite.staticSolrCompteYoutube(requeteSite_, ConfigSite.staticSetCompteYoutube(requeteSite_, o)));
	}

	/////////////////////
	// comptePinterest //
	/////////////////////

	/**	 L'entité comptePinterest
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String comptePinterest;
	@JsonIgnore
	public Couverture<String> comptePinterestCouverture = new Couverture<String>().p(this).c(String.class).var("comptePinterest").o(comptePinterest);

	/**	<br/> L'entité comptePinterest
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:comptePinterest">Trouver l'entité comptePinterest dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _comptePinterest(Couverture<String> c);

	public String getComptePinterest() {
		return comptePinterest;
	}
	public void setComptePinterest(String o) {
		this.comptePinterest = ConfigSite.staticSetComptePinterest(null, o);
		this.comptePinterestCouverture.dejaInitialise = true;
	}
	public static String staticSetComptePinterest(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrComptePinterest(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrComptePinterest(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqComptePinterest(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrComptePinterest(requeteSite_, ConfigSite.staticSolrComptePinterest(requeteSite_, ConfigSite.staticSetComptePinterest(requeteSite_, o)));
	}

	////////////////
	// compteMail //
	////////////////

	/**	 L'entité compteMail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String compteMail;
	@JsonIgnore
	public Couverture<String> compteMailCouverture = new Couverture<String>().p(this).c(String.class).var("compteMail").o(compteMail);

	/**	<br/> L'entité compteMail
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteMail">Trouver l'entité compteMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteMail(Couverture<String> c);

	public String getCompteMail() {
		return compteMail;
	}
	public void setCompteMail(String o) {
		this.compteMail = ConfigSite.staticSetCompteMail(null, o);
		this.compteMailCouverture.dejaInitialise = true;
	}
	public static String staticSetCompteMail(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrCompteMail(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrCompteMail(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqCompteMail(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrCompteMail(requeteSite_, ConfigSite.staticSolrCompteMail(requeteSite_, ConfigSite.staticSetCompteMail(requeteSite_, o)));
	}

	///////////////
	// roleAdmin //
	///////////////

	/**	 L'entité roleAdmin
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String roleAdmin;
	@JsonIgnore
	public Couverture<String> roleAdminCouverture = new Couverture<String>().p(this).c(String.class).var("roleAdmin").o(roleAdmin);

	/**	<br/> L'entité roleAdmin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:roleAdmin">Trouver l'entité roleAdmin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _roleAdmin(Couverture<String> c);

	public String getRoleAdmin() {
		return roleAdmin;
	}
	public void setRoleAdmin(String o) {
		this.roleAdmin = ConfigSite.staticSetRoleAdmin(null, o);
		this.roleAdminCouverture.dejaInitialise = true;
	}
	public static String staticSetRoleAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrRoleAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrRoleAdmin(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqRoleAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrRoleAdmin(requeteSite_, ConfigSite.staticSolrRoleAdmin(requeteSite_, ConfigSite.staticSetRoleAdmin(requeteSite_, o)));
	}

	///////////////
	// mailAdmin //
	///////////////

	/**	 L'entité mailAdmin
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mailAdmin;
	@JsonIgnore
	public Couverture<String> mailAdminCouverture = new Couverture<String>().p(this).c(String.class).var("mailAdmin").o(mailAdmin);

	/**	<br/> L'entité mailAdmin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailAdmin">Trouver l'entité mailAdmin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailAdmin(Couverture<String> c);

	public String getMailAdmin() {
		return mailAdmin;
	}
	public void setMailAdmin(String o) {
		this.mailAdmin = ConfigSite.staticSetMailAdmin(null, o);
		this.mailAdminCouverture.dejaInitialise = true;
	}
	public static String staticSetMailAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrMailAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrMailAdmin(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqMailAdmin(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrMailAdmin(requeteSite_, ConfigSite.staticSolrMailAdmin(requeteSite_, ConfigSite.staticSetMailAdmin(requeteSite_, o)));
	}

	//////////////////////
	// nombreExecuteurs //
	//////////////////////

	/**	 L'entité nombreExecuteurs
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer nombreExecuteurs;
	@JsonIgnore
	public Couverture<Integer> nombreExecuteursCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("nombreExecuteurs").o(nombreExecuteurs);

	/**	<br/> L'entité nombreExecuteurs
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:nombreExecuteurs">Trouver l'entité nombreExecuteurs dans Solr</a>
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
	public void setNombreExecuteurs(String o) {
		this.nombreExecuteurs = ConfigSite.staticSetNombreExecuteurs(null, o);
		this.nombreExecuteursCouverture.dejaInitialise = true;
	}
	public static Integer staticSetNombreExecuteurs(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrNombreExecuteurs(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrNombreExecuteurs(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqNombreExecuteurs(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrNombreExecuteurs(requeteSite_, ConfigSite.staticSolrNombreExecuteurs(requeteSite_, ConfigSite.staticSetNombreExecuteurs(requeteSite_, o)));
	}

	////////////////////
	// openApiVersion //
	////////////////////

	/**	 L'entité openApiVersion
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String openApiVersion;
	@JsonIgnore
	public Couverture<String> openApiVersionCouverture = new Couverture<String>().p(this).c(String.class).var("openApiVersion").o(openApiVersion);

	/**	<br/> L'entité openApiVersion
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiVersion">Trouver l'entité openApiVersion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiVersion(Couverture<String> c);

	public String getOpenApiVersion() {
		return openApiVersion;
	}
	public void setOpenApiVersion(String o) {
		this.openApiVersion = ConfigSite.staticSetOpenApiVersion(null, o);
		this.openApiVersionCouverture.dejaInitialise = true;
	}
	public static String staticSetOpenApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrOpenApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrOpenApiVersion(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqOpenApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrOpenApiVersion(requeteSite_, ConfigSite.staticSolrOpenApiVersion(requeteSite_, ConfigSite.staticSetOpenApiVersion(requeteSite_, o)));
	}

	////////////////////
	// apiDescription //
	////////////////////

	/**	 L'entité apiDescription
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiDescription;
	@JsonIgnore
	public Couverture<String> apiDescriptionCouverture = new Couverture<String>().p(this).c(String.class).var("apiDescription").o(apiDescription);

	/**	<br/> L'entité apiDescription
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiDescription">Trouver l'entité apiDescription dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiDescription(Couverture<String> c);

	public String getApiDescription() {
		return apiDescription;
	}
	public void setApiDescription(String o) {
		this.apiDescription = ConfigSite.staticSetApiDescription(null, o);
		this.apiDescriptionCouverture.dejaInitialise = true;
	}
	public static String staticSetApiDescription(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrApiDescription(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrApiDescription(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqApiDescription(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrApiDescription(requeteSite_, ConfigSite.staticSolrApiDescription(requeteSite_, ConfigSite.staticSetApiDescription(requeteSite_, o)));
	}

	//////////////
	// apiTitre //
	//////////////

	/**	 L'entité apiTitre
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiTitre;
	@JsonIgnore
	public Couverture<String> apiTitreCouverture = new Couverture<String>().p(this).c(String.class).var("apiTitre").o(apiTitre);

	/**	<br/> L'entité apiTitre
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiTitre">Trouver l'entité apiTitre dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiTitre(Couverture<String> c);

	public String getApiTitre() {
		return apiTitre;
	}
	public void setApiTitre(String o) {
		this.apiTitre = ConfigSite.staticSetApiTitre(null, o);
		this.apiTitreCouverture.dejaInitialise = true;
	}
	public static String staticSetApiTitre(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrApiTitre(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrApiTitre(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqApiTitre(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrApiTitre(requeteSite_, ConfigSite.staticSolrApiTitre(requeteSite_, ConfigSite.staticSetApiTitre(requeteSite_, o)));
	}

	/////////////////////
	// apiTermsService //
	/////////////////////

	/**	 L'entité apiTermsService
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiTermsService;
	@JsonIgnore
	public Couverture<String> apiTermsServiceCouverture = new Couverture<String>().p(this).c(String.class).var("apiTermsService").o(apiTermsService);

	/**	<br/> L'entité apiTermsService
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiTermsService">Trouver l'entité apiTermsService dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiTermsService(Couverture<String> c);

	public String getApiTermsService() {
		return apiTermsService;
	}
	public void setApiTermsService(String o) {
		this.apiTermsService = ConfigSite.staticSetApiTermsService(null, o);
		this.apiTermsServiceCouverture.dejaInitialise = true;
	}
	public static String staticSetApiTermsService(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrApiTermsService(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrApiTermsService(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqApiTermsService(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrApiTermsService(requeteSite_, ConfigSite.staticSolrApiTermsService(requeteSite_, ConfigSite.staticSetApiTermsService(requeteSite_, o)));
	}

	////////////////
	// apiVersion //
	////////////////

	/**	 L'entité apiVersion
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiVersion;
	@JsonIgnore
	public Couverture<String> apiVersionCouverture = new Couverture<String>().p(this).c(String.class).var("apiVersion").o(apiVersion);

	/**	<br/> L'entité apiVersion
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiVersion">Trouver l'entité apiVersion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiVersion(Couverture<String> c);

	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String o) {
		this.apiVersion = ConfigSite.staticSetApiVersion(null, o);
		this.apiVersionCouverture.dejaInitialise = true;
	}
	public static String staticSetApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrApiVersion(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrApiVersion(requeteSite_, ConfigSite.staticSolrApiVersion(requeteSite_, ConfigSite.staticSetApiVersion(requeteSite_, o)));
	}

	////////////////////
	// apiContactMail //
	////////////////////

	/**	 L'entité apiContactMail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiContactMail;
	@JsonIgnore
	public Couverture<String> apiContactMailCouverture = new Couverture<String>().p(this).c(String.class).var("apiContactMail").o(apiContactMail);

	/**	<br/> L'entité apiContactMail
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiContactMail">Trouver l'entité apiContactMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiContactMail(Couverture<String> c);

	public String getApiContactMail() {
		return apiContactMail;
	}
	public void setApiContactMail(String o) {
		this.apiContactMail = ConfigSite.staticSetApiContactMail(null, o);
		this.apiContactMailCouverture.dejaInitialise = true;
	}
	public static String staticSetApiContactMail(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrApiContactMail(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrApiContactMail(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqApiContactMail(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrApiContactMail(requeteSite_, ConfigSite.staticSolrApiContactMail(requeteSite_, ConfigSite.staticSetApiContactMail(requeteSite_, o)));
	}

	///////////////////
	// apiLicenceNom //
	///////////////////

	/**	 L'entité apiLicenceNom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiLicenceNom;
	@JsonIgnore
	public Couverture<String> apiLicenceNomCouverture = new Couverture<String>().p(this).c(String.class).var("apiLicenceNom").o(apiLicenceNom);

	/**	<br/> L'entité apiLicenceNom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiLicenceNom">Trouver l'entité apiLicenceNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiLicenceNom(Couverture<String> c);

	public String getApiLicenceNom() {
		return apiLicenceNom;
	}
	public void setApiLicenceNom(String o) {
		this.apiLicenceNom = ConfigSite.staticSetApiLicenceNom(null, o);
		this.apiLicenceNomCouverture.dejaInitialise = true;
	}
	public static String staticSetApiLicenceNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrApiLicenceNom(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrApiLicenceNom(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqApiLicenceNom(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrApiLicenceNom(requeteSite_, ConfigSite.staticSolrApiLicenceNom(requeteSite_, ConfigSite.staticSetApiLicenceNom(requeteSite_, o)));
	}

	///////////////////
	// apiLicenceUrl //
	///////////////////

	/**	 L'entité apiLicenceUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiLicenceUrl;
	@JsonIgnore
	public Couverture<String> apiLicenceUrlCouverture = new Couverture<String>().p(this).c(String.class).var("apiLicenceUrl").o(apiLicenceUrl);

	/**	<br/> L'entité apiLicenceUrl
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiLicenceUrl">Trouver l'entité apiLicenceUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiLicenceUrl(Couverture<String> c);

	public String getApiLicenceUrl() {
		return apiLicenceUrl;
	}
	public void setApiLicenceUrl(String o) {
		this.apiLicenceUrl = ConfigSite.staticSetApiLicenceUrl(null, o);
		this.apiLicenceUrlCouverture.dejaInitialise = true;
	}
	public static String staticSetApiLicenceUrl(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrApiLicenceUrl(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrApiLicenceUrl(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqApiLicenceUrl(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrApiLicenceUrl(requeteSite_, ConfigSite.staticSolrApiLicenceUrl(requeteSite_, ConfigSite.staticSetApiLicenceUrl(requeteSite_, o)));
	}

	////////////////
	// apiNomHote //
	////////////////

	/**	 L'entité apiNomHote
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiNomHote;
	@JsonIgnore
	public Couverture<String> apiNomHoteCouverture = new Couverture<String>().p(this).c(String.class).var("apiNomHote").o(apiNomHote);

	/**	<br/> L'entité apiNomHote
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiNomHote">Trouver l'entité apiNomHote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiNomHote(Couverture<String> c);

	public String getApiNomHote() {
		return apiNomHote;
	}
	public void setApiNomHote(String o) {
		this.apiNomHote = ConfigSite.staticSetApiNomHote(null, o);
		this.apiNomHoteCouverture.dejaInitialise = true;
	}
	public static String staticSetApiNomHote(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrApiNomHote(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrApiNomHote(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqApiNomHote(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrApiNomHote(requeteSite_, ConfigSite.staticSolrApiNomHote(requeteSite_, ConfigSite.staticSetApiNomHote(requeteSite_, o)));
	}

	///////////////////
	// apiCheminBase //
	///////////////////

	/**	 L'entité apiCheminBase
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiCheminBase;
	@JsonIgnore
	public Couverture<String> apiCheminBaseCouverture = new Couverture<String>().p(this).c(String.class).var("apiCheminBase").o(apiCheminBase);

	/**	<br/> L'entité apiCheminBase
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiCheminBase">Trouver l'entité apiCheminBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiCheminBase(Couverture<String> c);

	public String getApiCheminBase() {
		return apiCheminBase;
	}
	public void setApiCheminBase(String o) {
		this.apiCheminBase = ConfigSite.staticSetApiCheminBase(null, o);
		this.apiCheminBaseCouverture.dejaInitialise = true;
	}
	public static String staticSetApiCheminBase(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrApiCheminBase(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrApiCheminBase(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqApiCheminBase(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrApiCheminBase(requeteSite_, ConfigSite.staticSolrApiCheminBase(requeteSite_, ConfigSite.staticSetApiCheminBase(requeteSite_, o)));
	}

	/////////////////////
	// statiqueUrlBase //
	/////////////////////

	/**	 L'entité statiqueUrlBase
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String statiqueUrlBase;
	@JsonIgnore
	public Couverture<String> statiqueUrlBaseCouverture = new Couverture<String>().p(this).c(String.class).var("statiqueUrlBase").o(statiqueUrlBase);

	/**	<br/> L'entité statiqueUrlBase
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:statiqueUrlBase">Trouver l'entité statiqueUrlBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _statiqueUrlBase(Couverture<String> c);

	public String getStatiqueUrlBase() {
		return statiqueUrlBase;
	}
	public void setStatiqueUrlBase(String o) {
		this.statiqueUrlBase = ConfigSite.staticSetStatiqueUrlBase(null, o);
		this.statiqueUrlBaseCouverture.dejaInitialise = true;
	}
	public static String staticSetStatiqueUrlBase(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrStatiqueUrlBase(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrStatiqueUrlBase(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqStatiqueUrlBase(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrStatiqueUrlBase(requeteSite_, ConfigSite.staticSolrStatiqueUrlBase(requeteSite_, ConfigSite.staticSetStatiqueUrlBase(requeteSite_, o)));
	}

	//////////////
	// mailHote //
	//////////////

	/**	 L'entité mailHote
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mailHote;
	@JsonIgnore
	public Couverture<String> mailHoteCouverture = new Couverture<String>().p(this).c(String.class).var("mailHote").o(mailHote);

	/**	<br/> L'entité mailHote
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailHote">Trouver l'entité mailHote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailHote(Couverture<String> c);

	public String getMailHote() {
		return mailHote;
	}
	public void setMailHote(String o) {
		this.mailHote = ConfigSite.staticSetMailHote(null, o);
		this.mailHoteCouverture.dejaInitialise = true;
	}
	public static String staticSetMailHote(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrMailHote(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrMailHote(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqMailHote(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrMailHote(requeteSite_, ConfigSite.staticSolrMailHote(requeteSite_, ConfigSite.staticSetMailHote(requeteSite_, o)));
	}

	//////////////
	// mailPort //
	//////////////

	/**	 L'entité mailPort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer mailPort;
	@JsonIgnore
	public Couverture<Integer> mailPortCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("mailPort").o(mailPort);

	/**	<br/> L'entité mailPort
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailPort">Trouver l'entité mailPort dans Solr</a>
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
	public void setMailPort(String o) {
		this.mailPort = ConfigSite.staticSetMailPort(null, o);
		this.mailPortCouverture.dejaInitialise = true;
	}
	public static Integer staticSetMailPort(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrMailPort(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrMailPort(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqMailPort(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrMailPort(requeteSite_, ConfigSite.staticSolrMailPort(requeteSite_, ConfigSite.staticSetMailPort(requeteSite_, o)));
	}

	/////////////////////
	// mailUtilisateur //
	/////////////////////

	/**	 L'entité mailUtilisateur
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mailUtilisateur;
	@JsonIgnore
	public Couverture<String> mailUtilisateurCouverture = new Couverture<String>().p(this).c(String.class).var("mailUtilisateur").o(mailUtilisateur);

	/**	<br/> L'entité mailUtilisateur
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailUtilisateur">Trouver l'entité mailUtilisateur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailUtilisateur(Couverture<String> c);

	public String getMailUtilisateur() {
		return mailUtilisateur;
	}
	public void setMailUtilisateur(String o) {
		this.mailUtilisateur = ConfigSite.staticSetMailUtilisateur(null, o);
		this.mailUtilisateurCouverture.dejaInitialise = true;
	}
	public static String staticSetMailUtilisateur(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrMailUtilisateur(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrMailUtilisateur(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqMailUtilisateur(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrMailUtilisateur(requeteSite_, ConfigSite.staticSolrMailUtilisateur(requeteSite_, ConfigSite.staticSetMailUtilisateur(requeteSite_, o)));
	}

	////////////////////
	// mailMotDePasse //
	////////////////////

	/**	 L'entité mailMotDePasse
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mailMotDePasse;
	@JsonIgnore
	public Couverture<String> mailMotDePasseCouverture = new Couverture<String>().p(this).c(String.class).var("mailMotDePasse").o(mailMotDePasse);

	/**	<br/> L'entité mailMotDePasse
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailMotDePasse">Trouver l'entité mailMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailMotDePasse(Couverture<String> c);

	public String getMailMotDePasse() {
		return mailMotDePasse;
	}
	public void setMailMotDePasse(String o) {
		this.mailMotDePasse = ConfigSite.staticSetMailMotDePasse(null, o);
		this.mailMotDePasseCouverture.dejaInitialise = true;
	}
	public static String staticSetMailMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrMailMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrMailMotDePasse(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqMailMotDePasse(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrMailMotDePasse(requeteSite_, ConfigSite.staticSolrMailMotDePasse(requeteSite_, ConfigSite.staticSetMailMotDePasse(requeteSite_, o)));
	}

	////////////
	// mailDe //
	////////////

	/**	 L'entité mailDe
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String mailDe;
	@JsonIgnore
	public Couverture<String> mailDeCouverture = new Couverture<String>().p(this).c(String.class).var("mailDe").o(mailDe);

	/**	<br/> L'entité mailDe
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailDe">Trouver l'entité mailDe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailDe(Couverture<String> c);

	public String getMailDe() {
		return mailDe;
	}
	public void setMailDe(String o) {
		this.mailDe = ConfigSite.staticSetMailDe(null, o);
		this.mailDeCouverture.dejaInitialise = true;
	}
	public static String staticSetMailDe(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrMailDe(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrMailDe(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqMailDe(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrMailDe(requeteSite_, ConfigSite.staticSolrMailDe(requeteSite_, ConfigSite.staticSetMailDe(requeteSite_, o)));
	}

	//////////////
	// mailAuth //
	//////////////

	/**	 L'entité mailAuth
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean mailAuth;
	@JsonIgnore
	public Couverture<Boolean> mailAuthCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("mailAuth").o(mailAuth);

	/**	<br/> L'entité mailAuth
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailAuth">Trouver l'entité mailAuth dans Solr</a>
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
	public void setMailAuth(String o) {
		this.mailAuth = ConfigSite.staticSetMailAuth(null, o);
		this.mailAuthCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetMailAuth(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Object staticSolrMailAuth(RequeteSiteFrFR requeteSite_, Boolean o) {
		return null;
	}

	public static String staticSolrStrMailAuth(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqMailAuth(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrMailAuth(requeteSite_, ConfigSite.staticSolrMailAuth(requeteSite_, ConfigSite.staticSetMailAuth(requeteSite_, o)));
	}

	/////////////
	// mailSsl //
	/////////////

	/**	 L'entité mailSsl
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean mailSsl;
	@JsonIgnore
	public Couverture<Boolean> mailSslCouverture = new Couverture<Boolean>().p(this).c(Boolean.class).var("mailSsl").o(mailSsl);

	/**	<br/> L'entité mailSsl
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailSsl">Trouver l'entité mailSsl dans Solr</a>
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
	public void setMailSsl(String o) {
		this.mailSsl = ConfigSite.staticSetMailSsl(null, o);
		this.mailSslCouverture.dejaInitialise = true;
	}
	public static Boolean staticSetMailSsl(RequeteSiteFrFR requeteSite_, String o) {
		return Boolean.parseBoolean(o);
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

	public static Object staticSolrMailSsl(RequeteSiteFrFR requeteSite_, Boolean o) {
		return null;
	}

	public static String staticSolrStrMailSsl(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqMailSsl(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrMailSsl(requeteSite_, ConfigSite.staticSolrMailSsl(requeteSite_, ConfigSite.staticSetMailSsl(requeteSite_, o)));
	}

	//////////////
	// siteZone //
	//////////////

	/**	 L'entité siteZone
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String siteZone;
	@JsonIgnore
	public Couverture<String> siteZoneCouverture = new Couverture<String>().p(this).c(String.class).var("siteZone").o(siteZone);

	/**	<br/> L'entité siteZone
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteZone">Trouver l'entité siteZone dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteZone(Couverture<String> c);

	public String getSiteZone() {
		return siteZone;
	}
	public void setSiteZone(String o) {
		this.siteZone = ConfigSite.staticSetSiteZone(null, o);
		this.siteZoneCouverture.dejaInitialise = true;
	}
	public static String staticSetSiteZone(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrSiteZone(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSiteZone(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSiteZone(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSiteZone(requeteSite_, ConfigSite.staticSolrSiteZone(requeteSite_, ConfigSite.staticSetSiteZone(requeteSite_, o)));
	}

	//////////////////////////
	// authorizeApiLoginId1 //
	//////////////////////////

	/**	 L'entité authorizeApiLoginId1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeApiLoginId1;
	@JsonIgnore
	public Couverture<String> authorizeApiLoginId1Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeApiLoginId1").o(authorizeApiLoginId1);

	/**	<br/> L'entité authorizeApiLoginId1
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeApiLoginId1">Trouver l'entité authorizeApiLoginId1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeApiLoginId1(Couverture<String> c);

	public String getAuthorizeApiLoginId1() {
		return authorizeApiLoginId1;
	}
	public void setAuthorizeApiLoginId1(String o) {
		this.authorizeApiLoginId1 = ConfigSite.staticSetAuthorizeApiLoginId1(null, o);
		this.authorizeApiLoginId1Couverture.dejaInitialise = true;
	}
	public static String staticSetAuthorizeApiLoginId1(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAuthorizeApiLoginId1(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthorizeApiLoginId1(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthorizeApiLoginId1(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthorizeApiLoginId1(requeteSite_, ConfigSite.staticSolrAuthorizeApiLoginId1(requeteSite_, ConfigSite.staticSetAuthorizeApiLoginId1(requeteSite_, o)));
	}

	//////////////////////////////
	// authorizeTransactionKey1 //
	//////////////////////////////

	/**	 L'entité authorizeTransactionKey1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeTransactionKey1;
	@JsonIgnore
	public Couverture<String> authorizeTransactionKey1Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeTransactionKey1").o(authorizeTransactionKey1);

	/**	<br/> L'entité authorizeTransactionKey1
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeTransactionKey1">Trouver l'entité authorizeTransactionKey1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeTransactionKey1(Couverture<String> c);

	public String getAuthorizeTransactionKey1() {
		return authorizeTransactionKey1;
	}
	public void setAuthorizeTransactionKey1(String o) {
		this.authorizeTransactionKey1 = ConfigSite.staticSetAuthorizeTransactionKey1(null, o);
		this.authorizeTransactionKey1Couverture.dejaInitialise = true;
	}
	public static String staticSetAuthorizeTransactionKey1(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAuthorizeTransactionKey1(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthorizeTransactionKey1(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthorizeTransactionKey1(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthorizeTransactionKey1(requeteSite_, ConfigSite.staticSolrAuthorizeTransactionKey1(requeteSite_, ConfigSite.staticSetAuthorizeTransactionKey1(requeteSite_, o)));
	}

	/////////////////////
	// schoolLocation1 //
	/////////////////////

	/**	 L'entité schoolLocation1
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation1;
	@JsonIgnore
	public Couverture<String> schoolLocation1Couverture = new Couverture<String>().p(this).c(String.class).var("schoolLocation1").o(schoolLocation1);

	/**	<br/> L'entité schoolLocation1
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:schoolLocation1">Trouver l'entité schoolLocation1 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolLocation1(Couverture<String> c);

	public String getSchoolLocation1() {
		return schoolLocation1;
	}
	public void setSchoolLocation1(String o) {
		this.schoolLocation1 = ConfigSite.staticSetSchoolLocation1(null, o);
		this.schoolLocation1Couverture.dejaInitialise = true;
	}
	public static String staticSetSchoolLocation1(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrSchoolLocation1(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSchoolLocation1(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSchoolLocation1(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSchoolLocation1(requeteSite_, ConfigSite.staticSolrSchoolLocation1(requeteSite_, ConfigSite.staticSetSchoolLocation1(requeteSite_, o)));
	}

	//////////////////////////
	// authorizeApiLoginId2 //
	//////////////////////////

	/**	 L'entité authorizeApiLoginId2
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeApiLoginId2;
	@JsonIgnore
	public Couverture<String> authorizeApiLoginId2Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeApiLoginId2").o(authorizeApiLoginId2);

	/**	<br/> L'entité authorizeApiLoginId2
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeApiLoginId2">Trouver l'entité authorizeApiLoginId2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeApiLoginId2(Couverture<String> c);

	public String getAuthorizeApiLoginId2() {
		return authorizeApiLoginId2;
	}
	public void setAuthorizeApiLoginId2(String o) {
		this.authorizeApiLoginId2 = ConfigSite.staticSetAuthorizeApiLoginId2(null, o);
		this.authorizeApiLoginId2Couverture.dejaInitialise = true;
	}
	public static String staticSetAuthorizeApiLoginId2(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAuthorizeApiLoginId2(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthorizeApiLoginId2(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthorizeApiLoginId2(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthorizeApiLoginId2(requeteSite_, ConfigSite.staticSolrAuthorizeApiLoginId2(requeteSite_, ConfigSite.staticSetAuthorizeApiLoginId2(requeteSite_, o)));
	}

	//////////////////////////////
	// authorizeTransactionKey2 //
	//////////////////////////////

	/**	 L'entité authorizeTransactionKey2
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeTransactionKey2;
	@JsonIgnore
	public Couverture<String> authorizeTransactionKey2Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeTransactionKey2").o(authorizeTransactionKey2);

	/**	<br/> L'entité authorizeTransactionKey2
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeTransactionKey2">Trouver l'entité authorizeTransactionKey2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeTransactionKey2(Couverture<String> c);

	public String getAuthorizeTransactionKey2() {
		return authorizeTransactionKey2;
	}
	public void setAuthorizeTransactionKey2(String o) {
		this.authorizeTransactionKey2 = ConfigSite.staticSetAuthorizeTransactionKey2(null, o);
		this.authorizeTransactionKey2Couverture.dejaInitialise = true;
	}
	public static String staticSetAuthorizeTransactionKey2(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAuthorizeTransactionKey2(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthorizeTransactionKey2(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthorizeTransactionKey2(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthorizeTransactionKey2(requeteSite_, ConfigSite.staticSolrAuthorizeTransactionKey2(requeteSite_, ConfigSite.staticSetAuthorizeTransactionKey2(requeteSite_, o)));
	}

	/////////////////////
	// schoolLocation2 //
	/////////////////////

	/**	 L'entité schoolLocation2
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation2;
	@JsonIgnore
	public Couverture<String> schoolLocation2Couverture = new Couverture<String>().p(this).c(String.class).var("schoolLocation2").o(schoolLocation2);

	/**	<br/> L'entité schoolLocation2
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:schoolLocation2">Trouver l'entité schoolLocation2 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolLocation2(Couverture<String> c);

	public String getSchoolLocation2() {
		return schoolLocation2;
	}
	public void setSchoolLocation2(String o) {
		this.schoolLocation2 = ConfigSite.staticSetSchoolLocation2(null, o);
		this.schoolLocation2Couverture.dejaInitialise = true;
	}
	public static String staticSetSchoolLocation2(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrSchoolLocation2(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSchoolLocation2(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSchoolLocation2(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSchoolLocation2(requeteSite_, ConfigSite.staticSolrSchoolLocation2(requeteSite_, ConfigSite.staticSetSchoolLocation2(requeteSite_, o)));
	}

	//////////////////////////
	// authorizeApiLoginId3 //
	//////////////////////////

	/**	 L'entité authorizeApiLoginId3
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeApiLoginId3;
	@JsonIgnore
	public Couverture<String> authorizeApiLoginId3Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeApiLoginId3").o(authorizeApiLoginId3);

	/**	<br/> L'entité authorizeApiLoginId3
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeApiLoginId3">Trouver l'entité authorizeApiLoginId3 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeApiLoginId3(Couverture<String> c);

	public String getAuthorizeApiLoginId3() {
		return authorizeApiLoginId3;
	}
	public void setAuthorizeApiLoginId3(String o) {
		this.authorizeApiLoginId3 = ConfigSite.staticSetAuthorizeApiLoginId3(null, o);
		this.authorizeApiLoginId3Couverture.dejaInitialise = true;
	}
	public static String staticSetAuthorizeApiLoginId3(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected ConfigSite authorizeApiLoginId3Init() {
		if(!authorizeApiLoginId3Couverture.dejaInitialise) {
			_authorizeApiLoginId3(authorizeApiLoginId3Couverture);
			if(authorizeApiLoginId3 == null)
				setAuthorizeApiLoginId3(authorizeApiLoginId3Couverture.o);
		}
		authorizeApiLoginId3Couverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	public static Object staticSolrAuthorizeApiLoginId3(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthorizeApiLoginId3(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthorizeApiLoginId3(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthorizeApiLoginId3(requeteSite_, ConfigSite.staticSolrAuthorizeApiLoginId3(requeteSite_, ConfigSite.staticSetAuthorizeApiLoginId3(requeteSite_, o)));
	}

	//////////////////////////////
	// authorizeTransactionKey3 //
	//////////////////////////////

	/**	 L'entité authorizeTransactionKey3
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeTransactionKey3;
	@JsonIgnore
	public Couverture<String> authorizeTransactionKey3Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeTransactionKey3").o(authorizeTransactionKey3);

	/**	<br/> L'entité authorizeTransactionKey3
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeTransactionKey3">Trouver l'entité authorizeTransactionKey3 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeTransactionKey3(Couverture<String> c);

	public String getAuthorizeTransactionKey3() {
		return authorizeTransactionKey3;
	}
	public void setAuthorizeTransactionKey3(String o) {
		this.authorizeTransactionKey3 = ConfigSite.staticSetAuthorizeTransactionKey3(null, o);
		this.authorizeTransactionKey3Couverture.dejaInitialise = true;
	}
	public static String staticSetAuthorizeTransactionKey3(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected ConfigSite authorizeTransactionKey3Init() {
		if(!authorizeTransactionKey3Couverture.dejaInitialise) {
			_authorizeTransactionKey3(authorizeTransactionKey3Couverture);
			if(authorizeTransactionKey3 == null)
				setAuthorizeTransactionKey3(authorizeTransactionKey3Couverture.o);
		}
		authorizeTransactionKey3Couverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	public static Object staticSolrAuthorizeTransactionKey3(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthorizeTransactionKey3(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthorizeTransactionKey3(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthorizeTransactionKey3(requeteSite_, ConfigSite.staticSolrAuthorizeTransactionKey3(requeteSite_, ConfigSite.staticSetAuthorizeTransactionKey3(requeteSite_, o)));
	}

	/////////////////////
	// schoolLocation3 //
	/////////////////////

	/**	 L'entité schoolLocation3
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation3;
	@JsonIgnore
	public Couverture<String> schoolLocation3Couverture = new Couverture<String>().p(this).c(String.class).var("schoolLocation3").o(schoolLocation3);

	/**	<br/> L'entité schoolLocation3
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:schoolLocation3">Trouver l'entité schoolLocation3 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolLocation3(Couverture<String> c);

	public String getSchoolLocation3() {
		return schoolLocation3;
	}
	public void setSchoolLocation3(String o) {
		this.schoolLocation3 = ConfigSite.staticSetSchoolLocation3(null, o);
		this.schoolLocation3Couverture.dejaInitialise = true;
	}
	public static String staticSetSchoolLocation3(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected ConfigSite schoolLocation3Init() {
		if(!schoolLocation3Couverture.dejaInitialise) {
			_schoolLocation3(schoolLocation3Couverture);
			if(schoolLocation3 == null)
				setSchoolLocation3(schoolLocation3Couverture.o);
		}
		schoolLocation3Couverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	public static Object staticSolrSchoolLocation3(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSchoolLocation3(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSchoolLocation3(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSchoolLocation3(requeteSite_, ConfigSite.staticSolrSchoolLocation3(requeteSite_, ConfigSite.staticSetSchoolLocation3(requeteSite_, o)));
	}

	//////////////////////////
	// authorizeApiLoginId4 //
	//////////////////////////

	/**	 L'entité authorizeApiLoginId4
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeApiLoginId4;
	@JsonIgnore
	public Couverture<String> authorizeApiLoginId4Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeApiLoginId4").o(authorizeApiLoginId4);

	/**	<br/> L'entité authorizeApiLoginId4
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeApiLoginId4">Trouver l'entité authorizeApiLoginId4 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeApiLoginId4(Couverture<String> c);

	public String getAuthorizeApiLoginId4() {
		return authorizeApiLoginId4;
	}
	public void setAuthorizeApiLoginId4(String o) {
		this.authorizeApiLoginId4 = ConfigSite.staticSetAuthorizeApiLoginId4(null, o);
		this.authorizeApiLoginId4Couverture.dejaInitialise = true;
	}
	public static String staticSetAuthorizeApiLoginId4(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected ConfigSite authorizeApiLoginId4Init() {
		if(!authorizeApiLoginId4Couverture.dejaInitialise) {
			_authorizeApiLoginId4(authorizeApiLoginId4Couverture);
			if(authorizeApiLoginId4 == null)
				setAuthorizeApiLoginId4(authorizeApiLoginId4Couverture.o);
		}
		authorizeApiLoginId4Couverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	public static Object staticSolrAuthorizeApiLoginId4(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthorizeApiLoginId4(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthorizeApiLoginId4(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthorizeApiLoginId4(requeteSite_, ConfigSite.staticSolrAuthorizeApiLoginId4(requeteSite_, ConfigSite.staticSetAuthorizeApiLoginId4(requeteSite_, o)));
	}

	//////////////////////////////
	// authorizeTransactionKey4 //
	//////////////////////////////

	/**	 L'entité authorizeTransactionKey4
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeTransactionKey4;
	@JsonIgnore
	public Couverture<String> authorizeTransactionKey4Couverture = new Couverture<String>().p(this).c(String.class).var("authorizeTransactionKey4").o(authorizeTransactionKey4);

	/**	<br/> L'entité authorizeTransactionKey4
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeTransactionKey4">Trouver l'entité authorizeTransactionKey4 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeTransactionKey4(Couverture<String> c);

	public String getAuthorizeTransactionKey4() {
		return authorizeTransactionKey4;
	}
	public void setAuthorizeTransactionKey4(String o) {
		this.authorizeTransactionKey4 = ConfigSite.staticSetAuthorizeTransactionKey4(null, o);
		this.authorizeTransactionKey4Couverture.dejaInitialise = true;
	}
	public static String staticSetAuthorizeTransactionKey4(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected ConfigSite authorizeTransactionKey4Init() {
		if(!authorizeTransactionKey4Couverture.dejaInitialise) {
			_authorizeTransactionKey4(authorizeTransactionKey4Couverture);
			if(authorizeTransactionKey4 == null)
				setAuthorizeTransactionKey4(authorizeTransactionKey4Couverture.o);
		}
		authorizeTransactionKey4Couverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	public static Object staticSolrAuthorizeTransactionKey4(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthorizeTransactionKey4(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthorizeTransactionKey4(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthorizeTransactionKey4(requeteSite_, ConfigSite.staticSolrAuthorizeTransactionKey4(requeteSite_, ConfigSite.staticSetAuthorizeTransactionKey4(requeteSite_, o)));
	}

	/////////////////////
	// schoolLocation4 //
	/////////////////////

	/**	 L'entité schoolLocation4
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation4;
	@JsonIgnore
	public Couverture<String> schoolLocation4Couverture = new Couverture<String>().p(this).c(String.class).var("schoolLocation4").o(schoolLocation4);

	/**	<br/> L'entité schoolLocation4
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:schoolLocation4">Trouver l'entité schoolLocation4 dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolLocation4(Couverture<String> c);

	public String getSchoolLocation4() {
		return schoolLocation4;
	}
	public void setSchoolLocation4(String o) {
		this.schoolLocation4 = ConfigSite.staticSetSchoolLocation4(null, o);
		this.schoolLocation4Couverture.dejaInitialise = true;
	}
	public static String staticSetSchoolLocation4(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected ConfigSite schoolLocation4Init() {
		if(!schoolLocation4Couverture.dejaInitialise) {
			_schoolLocation4(schoolLocation4Couverture);
			if(schoolLocation4 == null)
				setSchoolLocation4(schoolLocation4Couverture.o);
		}
		schoolLocation4Couverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	public static Object staticSolrSchoolLocation4(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrSchoolLocation4(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqSchoolLocation4(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrSchoolLocation4(requeteSite_, ConfigSite.staticSolrSchoolLocation4(requeteSite_, ConfigSite.staticSetSchoolLocation4(requeteSite_, o)));
	}

	//////////////////////////
	// authorizeEnvironment //
	//////////////////////////

	/**	 L'entité authorizeEnvironment
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeEnvironment;
	@JsonIgnore
	public Couverture<String> authorizeEnvironmentCouverture = new Couverture<String>().p(this).c(String.class).var("authorizeEnvironment").o(authorizeEnvironment);

	/**	<br/> L'entité authorizeEnvironment
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeEnvironment">Trouver l'entité authorizeEnvironment dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeEnvironment(Couverture<String> c);

	public String getAuthorizeEnvironment() {
		return authorizeEnvironment;
	}
	public void setAuthorizeEnvironment(String o) {
		this.authorizeEnvironment = ConfigSite.staticSetAuthorizeEnvironment(null, o);
		this.authorizeEnvironmentCouverture.dejaInitialise = true;
	}
	public static String staticSetAuthorizeEnvironment(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAuthorizeEnvironment(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthorizeEnvironment(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthorizeEnvironment(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthorizeEnvironment(requeteSite_, ConfigSite.staticSolrAuthorizeEnvironment(requeteSite_, ConfigSite.staticSetAuthorizeEnvironment(requeteSite_, o)));
	}

	//////////////////
	// authorizeUrl //
	//////////////////

	/**	 L'entité authorizeUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String authorizeUrl;
	@JsonIgnore
	public Couverture<String> authorizeUrlCouverture = new Couverture<String>().p(this).c(String.class).var("authorizeUrl").o(authorizeUrl);

	/**	<br/> L'entité authorizeUrl
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authorizeUrl">Trouver l'entité authorizeUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authorizeUrl(Couverture<String> c);

	public String getAuthorizeUrl() {
		return authorizeUrl;
	}
	public void setAuthorizeUrl(String o) {
		this.authorizeUrl = ConfigSite.staticSetAuthorizeUrl(null, o);
		this.authorizeUrlCouverture.dejaInitialise = true;
	}
	public static String staticSetAuthorizeUrl(RequeteSiteFrFR requeteSite_, String o) {
		return o;
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

	public static Object staticSolrAuthorizeUrl(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}

	public static String staticSolrStrAuthorizeUrl(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqAuthorizeUrl(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrAuthorizeUrl(requeteSite_, ConfigSite.staticSolrAuthorizeUrl(requeteSite_, ConfigSite.staticSetAuthorizeUrl(requeteSite_, o)));
	}

	//////////////////
	// paiementJour //
	//////////////////

	/**	 L'entité paiementJour
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer paiementJour;
	@JsonIgnore
	public Couverture<Integer> paiementJourCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("paiementJour").o(paiementJour);

	/**	<br/> L'entité paiementJour
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:paiementJour">Trouver l'entité paiementJour dans Solr</a>
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
	public void setPaiementJour(String o) {
		this.paiementJour = ConfigSite.staticSetPaiementJour(null, o);
		this.paiementJourCouverture.dejaInitialise = true;
	}
	public static Integer staticSetPaiementJour(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
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

	public static Object staticSolrPaiementJour(RequeteSiteFrFR requeteSite_, Integer o) {
		return null;
	}

	public static String staticSolrStrPaiementJour(RequeteSiteFrFR requeteSite_, Object o) {
		return null;
	}

	public static String staticSolrFqPaiementJour(RequeteSiteFrFR requeteSite_, String o) {
		return ConfigSite.staticSolrStrPaiementJour(requeteSite_, ConfigSite.staticSolrPaiementJour(requeteSite_, ConfigSite.staticSetPaiementJour(requeteSite_, o)));
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
		siteInstancesInit();
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
		authorizeApiLoginId3Init();
		authorizeTransactionKey3Init();
		schoolLocation3Init();
		authorizeApiLoginId4Init();
		authorizeTransactionKey4Init();
		schoolLocation4Init();
		authorizeEnvironmentInit();
		authorizeUrlInit();
		paiementJourInit();
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
			case "siteInstances":
				return oConfigSite.siteInstances;
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
			case "authorizeApiLoginId3":
				return oConfigSite.authorizeApiLoginId3;
			case "authorizeTransactionKey3":
				return oConfigSite.authorizeTransactionKey3;
			case "schoolLocation3":
				return oConfigSite.schoolLocation3;
			case "authorizeApiLoginId4":
				return oConfigSite.authorizeApiLoginId4;
			case "authorizeTransactionKey4":
				return oConfigSite.authorizeTransactionKey4;
			case "schoolLocation4":
				return oConfigSite.schoolLocation4;
			case "authorizeEnvironment":
				return oConfigSite.authorizeEnvironment;
			case "authorizeUrl":
				return oConfigSite.authorizeUrl;
			case "paiementJour":
				return oConfigSite.paiementJour;
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetConfigSite(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetConfigSite(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "configChemin":
			return ConfigSite.staticSetConfigChemin(requeteSite_, o);
		case "identifiantSite":
			return ConfigSite.staticSetIdentifiantSite(requeteSite_, o);
		case "prefixeEchappe":
			return ConfigSite.staticSetPrefixeEchappe(requeteSite_, o);
		case "appliChemin":
			return ConfigSite.staticSetAppliChemin(requeteSite_, o);
		case "racineDocument":
			return ConfigSite.staticSetRacineDocument(requeteSite_, o);
		case "nomEntreprise":
			return ConfigSite.staticSetNomEntreprise(requeteSite_, o);
		case "nomDomaine":
			return ConfigSite.staticSetNomDomaine(requeteSite_, o);
		case "siteNomHote":
			return ConfigSite.staticSetSiteNomHote(requeteSite_, o);
		case "sitePort":
			return ConfigSite.staticSetSitePort(requeteSite_, o);
		case "siteInstances":
			return ConfigSite.staticSetSiteInstances(requeteSite_, o);
		case "authRoyaume":
			return ConfigSite.staticSetAuthRoyaume(requeteSite_, o);
		case "authRessource":
			return ConfigSite.staticSetAuthRessource(requeteSite_, o);
		case "authSecret":
			return ConfigSite.staticSetAuthSecret(requeteSite_, o);
		case "authSslRequis":
			return ConfigSite.staticSetAuthSslRequis(requeteSite_, o);
		case "sslJksChemin":
			return ConfigSite.staticSetSslJksChemin(requeteSite_, o);
		case "sslJksMotDePasse":
			return ConfigSite.staticSetSslJksMotDePasse(requeteSite_, o);
		case "authUrl":
			return ConfigSite.staticSetAuthUrl(requeteSite_, o);
		case "cryptageSel":
			return ConfigSite.staticSetCryptageSel(requeteSite_, o);
		case "cryptageMotDePasse":
			return ConfigSite.staticSetCryptageMotDePasse(requeteSite_, o);
		case "siteUrlBase":
			return ConfigSite.staticSetSiteUrlBase(requeteSite_, o);
		case "siteNomAffichage":
			return ConfigSite.staticSetSiteNomAffichage(requeteSite_, o);
		case "jdbcClassePilote":
			return ConfigSite.staticSetJdbcClassePilote(requeteSite_, o);
		case "jdbcUtilisateur":
			return ConfigSite.staticSetJdbcUtilisateur(requeteSite_, o);
		case "jdbcMotDePasse":
			return ConfigSite.staticSetJdbcMotDePasse(requeteSite_, o);
		case "jdbcTailleMaxPiscine":
			return ConfigSite.staticSetJdbcTailleMaxPiscine(requeteSite_, o);
		case "jdbcMaxFileAttente":
			return ConfigSite.staticSetJdbcMaxFileAttente(requeteSite_, o);
		case "jdbcTailleMinPiscine":
			return ConfigSite.staticSetJdbcTailleMinPiscine(requeteSite_, o);
		case "jdbcMaxDeclarations":
			return ConfigSite.staticSetJdbcMaxDeclarations(requeteSite_, o);
		case "jdbcMaxDeclarationsParConnexion":
			return ConfigSite.staticSetJdbcMaxDeclarationsParConnexion(requeteSite_, o);
		case "jdbcTempsInactiviteMax":
			return ConfigSite.staticSetJdbcTempsInactiviteMax(requeteSite_, o);
		case "jdbcDelaiConnexion":
			return ConfigSite.staticSetJdbcDelaiConnexion(requeteSite_, o);
		case "jdbcHote":
			return ConfigSite.staticSetJdbcHote(requeteSite_, o);
		case "jdbcPort":
			return ConfigSite.staticSetJdbcPort(requeteSite_, o);
		case "jdbcBaseDeDonnees":
			return ConfigSite.staticSetJdbcBaseDeDonnees(requeteSite_, o);
		case "solrUrl":
			return ConfigSite.staticSetSolrUrl(requeteSite_, o);
		case "solrUrlComputate":
			return ConfigSite.staticSetSolrUrlComputate(requeteSite_, o);
		case "compteFacebook":
			return ConfigSite.staticSetCompteFacebook(requeteSite_, o);
		case "compteTwitter":
			return ConfigSite.staticSetCompteTwitter(requeteSite_, o);
		case "compteInstagram":
			return ConfigSite.staticSetCompteInstagram(requeteSite_, o);
		case "compteYoutube":
			return ConfigSite.staticSetCompteYoutube(requeteSite_, o);
		case "comptePinterest":
			return ConfigSite.staticSetComptePinterest(requeteSite_, o);
		case "compteMail":
			return ConfigSite.staticSetCompteMail(requeteSite_, o);
		case "roleAdmin":
			return ConfigSite.staticSetRoleAdmin(requeteSite_, o);
		case "mailAdmin":
			return ConfigSite.staticSetMailAdmin(requeteSite_, o);
		case "nombreExecuteurs":
			return ConfigSite.staticSetNombreExecuteurs(requeteSite_, o);
		case "openApiVersion":
			return ConfigSite.staticSetOpenApiVersion(requeteSite_, o);
		case "apiDescription":
			return ConfigSite.staticSetApiDescription(requeteSite_, o);
		case "apiTitre":
			return ConfigSite.staticSetApiTitre(requeteSite_, o);
		case "apiTermsService":
			return ConfigSite.staticSetApiTermsService(requeteSite_, o);
		case "apiVersion":
			return ConfigSite.staticSetApiVersion(requeteSite_, o);
		case "apiContactMail":
			return ConfigSite.staticSetApiContactMail(requeteSite_, o);
		case "apiLicenceNom":
			return ConfigSite.staticSetApiLicenceNom(requeteSite_, o);
		case "apiLicenceUrl":
			return ConfigSite.staticSetApiLicenceUrl(requeteSite_, o);
		case "apiNomHote":
			return ConfigSite.staticSetApiNomHote(requeteSite_, o);
		case "apiCheminBase":
			return ConfigSite.staticSetApiCheminBase(requeteSite_, o);
		case "statiqueUrlBase":
			return ConfigSite.staticSetStatiqueUrlBase(requeteSite_, o);
		case "mailHote":
			return ConfigSite.staticSetMailHote(requeteSite_, o);
		case "mailPort":
			return ConfigSite.staticSetMailPort(requeteSite_, o);
		case "mailUtilisateur":
			return ConfigSite.staticSetMailUtilisateur(requeteSite_, o);
		case "mailMotDePasse":
			return ConfigSite.staticSetMailMotDePasse(requeteSite_, o);
		case "mailDe":
			return ConfigSite.staticSetMailDe(requeteSite_, o);
		case "mailAuth":
			return ConfigSite.staticSetMailAuth(requeteSite_, o);
		case "mailSsl":
			return ConfigSite.staticSetMailSsl(requeteSite_, o);
		case "siteZone":
			return ConfigSite.staticSetSiteZone(requeteSite_, o);
		case "authorizeApiLoginId1":
			return ConfigSite.staticSetAuthorizeApiLoginId1(requeteSite_, o);
		case "authorizeTransactionKey1":
			return ConfigSite.staticSetAuthorizeTransactionKey1(requeteSite_, o);
		case "schoolLocation1":
			return ConfigSite.staticSetSchoolLocation1(requeteSite_, o);
		case "authorizeApiLoginId2":
			return ConfigSite.staticSetAuthorizeApiLoginId2(requeteSite_, o);
		case "authorizeTransactionKey2":
			return ConfigSite.staticSetAuthorizeTransactionKey2(requeteSite_, o);
		case "schoolLocation2":
			return ConfigSite.staticSetSchoolLocation2(requeteSite_, o);
		case "authorizeApiLoginId3":
			return ConfigSite.staticSetAuthorizeApiLoginId3(requeteSite_, o);
		case "authorizeTransactionKey3":
			return ConfigSite.staticSetAuthorizeTransactionKey3(requeteSite_, o);
		case "schoolLocation3":
			return ConfigSite.staticSetSchoolLocation3(requeteSite_, o);
		case "authorizeApiLoginId4":
			return ConfigSite.staticSetAuthorizeApiLoginId4(requeteSite_, o);
		case "authorizeTransactionKey4":
			return ConfigSite.staticSetAuthorizeTransactionKey4(requeteSite_, o);
		case "schoolLocation4":
			return ConfigSite.staticSetSchoolLocation4(requeteSite_, o);
		case "authorizeEnvironment":
			return ConfigSite.staticSetAuthorizeEnvironment(requeteSite_, o);
		case "authorizeUrl":
			return ConfigSite.staticSetAuthorizeUrl(requeteSite_, o);
		case "paiementJour":
			return ConfigSite.staticSetPaiementJour(requeteSite_, o);
			default:
				return null;
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrConfigSite(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrConfigSite(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "configChemin":
			return ConfigSite.staticSolrConfigChemin(requeteSite_, (String)o);
		case "identifiantSite":
			return ConfigSite.staticSolrIdentifiantSite(requeteSite_, (String)o);
		case "prefixeEchappe":
			return ConfigSite.staticSolrPrefixeEchappe(requeteSite_, (String)o);
		case "appliChemin":
			return ConfigSite.staticSolrAppliChemin(requeteSite_, (String)o);
		case "racineDocument":
			return ConfigSite.staticSolrRacineDocument(requeteSite_, (String)o);
		case "nomEntreprise":
			return ConfigSite.staticSolrNomEntreprise(requeteSite_, (String)o);
		case "nomDomaine":
			return ConfigSite.staticSolrNomDomaine(requeteSite_, (String)o);
		case "siteNomHote":
			return ConfigSite.staticSolrSiteNomHote(requeteSite_, (String)o);
		case "sitePort":
			return ConfigSite.staticSolrSitePort(requeteSite_, (Integer)o);
		case "siteInstances":
			return ConfigSite.staticSolrSiteInstances(requeteSite_, (Integer)o);
		case "authRoyaume":
			return ConfigSite.staticSolrAuthRoyaume(requeteSite_, (String)o);
		case "authRessource":
			return ConfigSite.staticSolrAuthRessource(requeteSite_, (String)o);
		case "authSecret":
			return ConfigSite.staticSolrAuthSecret(requeteSite_, (String)o);
		case "authSslRequis":
			return ConfigSite.staticSolrAuthSslRequis(requeteSite_, (String)o);
		case "sslJksChemin":
			return ConfigSite.staticSolrSslJksChemin(requeteSite_, (String)o);
		case "sslJksMotDePasse":
			return ConfigSite.staticSolrSslJksMotDePasse(requeteSite_, (String)o);
		case "authUrl":
			return ConfigSite.staticSolrAuthUrl(requeteSite_, (String)o);
		case "cryptageSel":
			return ConfigSite.staticSolrCryptageSel(requeteSite_, (String)o);
		case "cryptageMotDePasse":
			return ConfigSite.staticSolrCryptageMotDePasse(requeteSite_, (String)o);
		case "siteUrlBase":
			return ConfigSite.staticSolrSiteUrlBase(requeteSite_, (String)o);
		case "siteNomAffichage":
			return ConfigSite.staticSolrSiteNomAffichage(requeteSite_, (String)o);
		case "jdbcClassePilote":
			return ConfigSite.staticSolrJdbcClassePilote(requeteSite_, (String)o);
		case "jdbcUtilisateur":
			return ConfigSite.staticSolrJdbcUtilisateur(requeteSite_, (String)o);
		case "jdbcMotDePasse":
			return ConfigSite.staticSolrJdbcMotDePasse(requeteSite_, (String)o);
		case "jdbcTailleMaxPiscine":
			return ConfigSite.staticSolrJdbcTailleMaxPiscine(requeteSite_, (Integer)o);
		case "jdbcMaxFileAttente":
			return ConfigSite.staticSolrJdbcMaxFileAttente(requeteSite_, (Integer)o);
		case "jdbcTailleMinPiscine":
			return ConfigSite.staticSolrJdbcTailleMinPiscine(requeteSite_, (Integer)o);
		case "jdbcMaxDeclarations":
			return ConfigSite.staticSolrJdbcMaxDeclarations(requeteSite_, (Integer)o);
		case "jdbcMaxDeclarationsParConnexion":
			return ConfigSite.staticSolrJdbcMaxDeclarationsParConnexion(requeteSite_, (Integer)o);
		case "jdbcTempsInactiviteMax":
			return ConfigSite.staticSolrJdbcTempsInactiviteMax(requeteSite_, (Integer)o);
		case "jdbcDelaiConnexion":
			return ConfigSite.staticSolrJdbcDelaiConnexion(requeteSite_, (Integer)o);
		case "jdbcHote":
			return ConfigSite.staticSolrJdbcHote(requeteSite_, (String)o);
		case "jdbcPort":
			return ConfigSite.staticSolrJdbcPort(requeteSite_, (Integer)o);
		case "jdbcBaseDeDonnees":
			return ConfigSite.staticSolrJdbcBaseDeDonnees(requeteSite_, (String)o);
		case "solrUrl":
			return ConfigSite.staticSolrSolrUrl(requeteSite_, (String)o);
		case "solrUrlComputate":
			return ConfigSite.staticSolrSolrUrlComputate(requeteSite_, (String)o);
		case "compteFacebook":
			return ConfigSite.staticSolrCompteFacebook(requeteSite_, (String)o);
		case "compteTwitter":
			return ConfigSite.staticSolrCompteTwitter(requeteSite_, (String)o);
		case "compteInstagram":
			return ConfigSite.staticSolrCompteInstagram(requeteSite_, (String)o);
		case "compteYoutube":
			return ConfigSite.staticSolrCompteYoutube(requeteSite_, (String)o);
		case "comptePinterest":
			return ConfigSite.staticSolrComptePinterest(requeteSite_, (String)o);
		case "compteMail":
			return ConfigSite.staticSolrCompteMail(requeteSite_, (String)o);
		case "roleAdmin":
			return ConfigSite.staticSolrRoleAdmin(requeteSite_, (String)o);
		case "mailAdmin":
			return ConfigSite.staticSolrMailAdmin(requeteSite_, (String)o);
		case "nombreExecuteurs":
			return ConfigSite.staticSolrNombreExecuteurs(requeteSite_, (Integer)o);
		case "openApiVersion":
			return ConfigSite.staticSolrOpenApiVersion(requeteSite_, (String)o);
		case "apiDescription":
			return ConfigSite.staticSolrApiDescription(requeteSite_, (String)o);
		case "apiTitre":
			return ConfigSite.staticSolrApiTitre(requeteSite_, (String)o);
		case "apiTermsService":
			return ConfigSite.staticSolrApiTermsService(requeteSite_, (String)o);
		case "apiVersion":
			return ConfigSite.staticSolrApiVersion(requeteSite_, (String)o);
		case "apiContactMail":
			return ConfigSite.staticSolrApiContactMail(requeteSite_, (String)o);
		case "apiLicenceNom":
			return ConfigSite.staticSolrApiLicenceNom(requeteSite_, (String)o);
		case "apiLicenceUrl":
			return ConfigSite.staticSolrApiLicenceUrl(requeteSite_, (String)o);
		case "apiNomHote":
			return ConfigSite.staticSolrApiNomHote(requeteSite_, (String)o);
		case "apiCheminBase":
			return ConfigSite.staticSolrApiCheminBase(requeteSite_, (String)o);
		case "statiqueUrlBase":
			return ConfigSite.staticSolrStatiqueUrlBase(requeteSite_, (String)o);
		case "mailHote":
			return ConfigSite.staticSolrMailHote(requeteSite_, (String)o);
		case "mailPort":
			return ConfigSite.staticSolrMailPort(requeteSite_, (Integer)o);
		case "mailUtilisateur":
			return ConfigSite.staticSolrMailUtilisateur(requeteSite_, (String)o);
		case "mailMotDePasse":
			return ConfigSite.staticSolrMailMotDePasse(requeteSite_, (String)o);
		case "mailDe":
			return ConfigSite.staticSolrMailDe(requeteSite_, (String)o);
		case "mailAuth":
			return ConfigSite.staticSolrMailAuth(requeteSite_, (Boolean)o);
		case "mailSsl":
			return ConfigSite.staticSolrMailSsl(requeteSite_, (Boolean)o);
		case "siteZone":
			return ConfigSite.staticSolrSiteZone(requeteSite_, (String)o);
		case "authorizeApiLoginId1":
			return ConfigSite.staticSolrAuthorizeApiLoginId1(requeteSite_, (String)o);
		case "authorizeTransactionKey1":
			return ConfigSite.staticSolrAuthorizeTransactionKey1(requeteSite_, (String)o);
		case "schoolLocation1":
			return ConfigSite.staticSolrSchoolLocation1(requeteSite_, (String)o);
		case "authorizeApiLoginId2":
			return ConfigSite.staticSolrAuthorizeApiLoginId2(requeteSite_, (String)o);
		case "authorizeTransactionKey2":
			return ConfigSite.staticSolrAuthorizeTransactionKey2(requeteSite_, (String)o);
		case "schoolLocation2":
			return ConfigSite.staticSolrSchoolLocation2(requeteSite_, (String)o);
		case "authorizeApiLoginId3":
			return ConfigSite.staticSolrAuthorizeApiLoginId3(requeteSite_, (String)o);
		case "authorizeTransactionKey3":
			return ConfigSite.staticSolrAuthorizeTransactionKey3(requeteSite_, (String)o);
		case "schoolLocation3":
			return ConfigSite.staticSolrSchoolLocation3(requeteSite_, (String)o);
		case "authorizeApiLoginId4":
			return ConfigSite.staticSolrAuthorizeApiLoginId4(requeteSite_, (String)o);
		case "authorizeTransactionKey4":
			return ConfigSite.staticSolrAuthorizeTransactionKey4(requeteSite_, (String)o);
		case "schoolLocation4":
			return ConfigSite.staticSolrSchoolLocation4(requeteSite_, (String)o);
		case "authorizeEnvironment":
			return ConfigSite.staticSolrAuthorizeEnvironment(requeteSite_, (String)o);
		case "authorizeUrl":
			return ConfigSite.staticSolrAuthorizeUrl(requeteSite_, (String)o);
		case "paiementJour":
			return ConfigSite.staticSolrPaiementJour(requeteSite_, (Integer)o);
			default:
				return null;
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrConfigSite(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrConfigSite(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "configChemin":
			return ConfigSite.staticSolrStrConfigChemin(requeteSite_, (String)o);
		case "identifiantSite":
			return ConfigSite.staticSolrStrIdentifiantSite(requeteSite_, (String)o);
		case "prefixeEchappe":
			return ConfigSite.staticSolrStrPrefixeEchappe(requeteSite_, (String)o);
		case "appliChemin":
			return ConfigSite.staticSolrStrAppliChemin(requeteSite_, (String)o);
		case "racineDocument":
			return ConfigSite.staticSolrStrRacineDocument(requeteSite_, (String)o);
		case "nomEntreprise":
			return ConfigSite.staticSolrStrNomEntreprise(requeteSite_, (String)o);
		case "nomDomaine":
			return ConfigSite.staticSolrStrNomDomaine(requeteSite_, (String)o);
		case "siteNomHote":
			return ConfigSite.staticSolrStrSiteNomHote(requeteSite_, (String)o);
		case "sitePort":
			return ConfigSite.staticSolrStrSitePort(requeteSite_, (Integer)o);
		case "siteInstances":
			return ConfigSite.staticSolrStrSiteInstances(requeteSite_, (Integer)o);
		case "authRoyaume":
			return ConfigSite.staticSolrStrAuthRoyaume(requeteSite_, (String)o);
		case "authRessource":
			return ConfigSite.staticSolrStrAuthRessource(requeteSite_, (String)o);
		case "authSecret":
			return ConfigSite.staticSolrStrAuthSecret(requeteSite_, (String)o);
		case "authSslRequis":
			return ConfigSite.staticSolrStrAuthSslRequis(requeteSite_, (String)o);
		case "sslJksChemin":
			return ConfigSite.staticSolrStrSslJksChemin(requeteSite_, (String)o);
		case "sslJksMotDePasse":
			return ConfigSite.staticSolrStrSslJksMotDePasse(requeteSite_, (String)o);
		case "authUrl":
			return ConfigSite.staticSolrStrAuthUrl(requeteSite_, (String)o);
		case "cryptageSel":
			return ConfigSite.staticSolrStrCryptageSel(requeteSite_, (String)o);
		case "cryptageMotDePasse":
			return ConfigSite.staticSolrStrCryptageMotDePasse(requeteSite_, (String)o);
		case "siteUrlBase":
			return ConfigSite.staticSolrStrSiteUrlBase(requeteSite_, (String)o);
		case "siteNomAffichage":
			return ConfigSite.staticSolrStrSiteNomAffichage(requeteSite_, (String)o);
		case "jdbcClassePilote":
			return ConfigSite.staticSolrStrJdbcClassePilote(requeteSite_, (String)o);
		case "jdbcUtilisateur":
			return ConfigSite.staticSolrStrJdbcUtilisateur(requeteSite_, (String)o);
		case "jdbcMotDePasse":
			return ConfigSite.staticSolrStrJdbcMotDePasse(requeteSite_, (String)o);
		case "jdbcTailleMaxPiscine":
			return ConfigSite.staticSolrStrJdbcTailleMaxPiscine(requeteSite_, (Integer)o);
		case "jdbcMaxFileAttente":
			return ConfigSite.staticSolrStrJdbcMaxFileAttente(requeteSite_, (Integer)o);
		case "jdbcTailleMinPiscine":
			return ConfigSite.staticSolrStrJdbcTailleMinPiscine(requeteSite_, (Integer)o);
		case "jdbcMaxDeclarations":
			return ConfigSite.staticSolrStrJdbcMaxDeclarations(requeteSite_, (Integer)o);
		case "jdbcMaxDeclarationsParConnexion":
			return ConfigSite.staticSolrStrJdbcMaxDeclarationsParConnexion(requeteSite_, (Integer)o);
		case "jdbcTempsInactiviteMax":
			return ConfigSite.staticSolrStrJdbcTempsInactiviteMax(requeteSite_, (Integer)o);
		case "jdbcDelaiConnexion":
			return ConfigSite.staticSolrStrJdbcDelaiConnexion(requeteSite_, (Integer)o);
		case "jdbcHote":
			return ConfigSite.staticSolrStrJdbcHote(requeteSite_, (String)o);
		case "jdbcPort":
			return ConfigSite.staticSolrStrJdbcPort(requeteSite_, (Integer)o);
		case "jdbcBaseDeDonnees":
			return ConfigSite.staticSolrStrJdbcBaseDeDonnees(requeteSite_, (String)o);
		case "solrUrl":
			return ConfigSite.staticSolrStrSolrUrl(requeteSite_, (String)o);
		case "solrUrlComputate":
			return ConfigSite.staticSolrStrSolrUrlComputate(requeteSite_, (String)o);
		case "compteFacebook":
			return ConfigSite.staticSolrStrCompteFacebook(requeteSite_, (String)o);
		case "compteTwitter":
			return ConfigSite.staticSolrStrCompteTwitter(requeteSite_, (String)o);
		case "compteInstagram":
			return ConfigSite.staticSolrStrCompteInstagram(requeteSite_, (String)o);
		case "compteYoutube":
			return ConfigSite.staticSolrStrCompteYoutube(requeteSite_, (String)o);
		case "comptePinterest":
			return ConfigSite.staticSolrStrComptePinterest(requeteSite_, (String)o);
		case "compteMail":
			return ConfigSite.staticSolrStrCompteMail(requeteSite_, (String)o);
		case "roleAdmin":
			return ConfigSite.staticSolrStrRoleAdmin(requeteSite_, (String)o);
		case "mailAdmin":
			return ConfigSite.staticSolrStrMailAdmin(requeteSite_, (String)o);
		case "nombreExecuteurs":
			return ConfigSite.staticSolrStrNombreExecuteurs(requeteSite_, (Integer)o);
		case "openApiVersion":
			return ConfigSite.staticSolrStrOpenApiVersion(requeteSite_, (String)o);
		case "apiDescription":
			return ConfigSite.staticSolrStrApiDescription(requeteSite_, (String)o);
		case "apiTitre":
			return ConfigSite.staticSolrStrApiTitre(requeteSite_, (String)o);
		case "apiTermsService":
			return ConfigSite.staticSolrStrApiTermsService(requeteSite_, (String)o);
		case "apiVersion":
			return ConfigSite.staticSolrStrApiVersion(requeteSite_, (String)o);
		case "apiContactMail":
			return ConfigSite.staticSolrStrApiContactMail(requeteSite_, (String)o);
		case "apiLicenceNom":
			return ConfigSite.staticSolrStrApiLicenceNom(requeteSite_, (String)o);
		case "apiLicenceUrl":
			return ConfigSite.staticSolrStrApiLicenceUrl(requeteSite_, (String)o);
		case "apiNomHote":
			return ConfigSite.staticSolrStrApiNomHote(requeteSite_, (String)o);
		case "apiCheminBase":
			return ConfigSite.staticSolrStrApiCheminBase(requeteSite_, (String)o);
		case "statiqueUrlBase":
			return ConfigSite.staticSolrStrStatiqueUrlBase(requeteSite_, (String)o);
		case "mailHote":
			return ConfigSite.staticSolrStrMailHote(requeteSite_, (String)o);
		case "mailPort":
			return ConfigSite.staticSolrStrMailPort(requeteSite_, (Integer)o);
		case "mailUtilisateur":
			return ConfigSite.staticSolrStrMailUtilisateur(requeteSite_, (String)o);
		case "mailMotDePasse":
			return ConfigSite.staticSolrStrMailMotDePasse(requeteSite_, (String)o);
		case "mailDe":
			return ConfigSite.staticSolrStrMailDe(requeteSite_, (String)o);
		case "mailAuth":
			return ConfigSite.staticSolrStrMailAuth(requeteSite_, (Boolean)o);
		case "mailSsl":
			return ConfigSite.staticSolrStrMailSsl(requeteSite_, (Boolean)o);
		case "siteZone":
			return ConfigSite.staticSolrStrSiteZone(requeteSite_, (String)o);
		case "authorizeApiLoginId1":
			return ConfigSite.staticSolrStrAuthorizeApiLoginId1(requeteSite_, (String)o);
		case "authorizeTransactionKey1":
			return ConfigSite.staticSolrStrAuthorizeTransactionKey1(requeteSite_, (String)o);
		case "schoolLocation1":
			return ConfigSite.staticSolrStrSchoolLocation1(requeteSite_, (String)o);
		case "authorizeApiLoginId2":
			return ConfigSite.staticSolrStrAuthorizeApiLoginId2(requeteSite_, (String)o);
		case "authorizeTransactionKey2":
			return ConfigSite.staticSolrStrAuthorizeTransactionKey2(requeteSite_, (String)o);
		case "schoolLocation2":
			return ConfigSite.staticSolrStrSchoolLocation2(requeteSite_, (String)o);
		case "authorizeApiLoginId3":
			return ConfigSite.staticSolrStrAuthorizeApiLoginId3(requeteSite_, (String)o);
		case "authorizeTransactionKey3":
			return ConfigSite.staticSolrStrAuthorizeTransactionKey3(requeteSite_, (String)o);
		case "schoolLocation3":
			return ConfigSite.staticSolrStrSchoolLocation3(requeteSite_, (String)o);
		case "authorizeApiLoginId4":
			return ConfigSite.staticSolrStrAuthorizeApiLoginId4(requeteSite_, (String)o);
		case "authorizeTransactionKey4":
			return ConfigSite.staticSolrStrAuthorizeTransactionKey4(requeteSite_, (String)o);
		case "schoolLocation4":
			return ConfigSite.staticSolrStrSchoolLocation4(requeteSite_, (String)o);
		case "authorizeEnvironment":
			return ConfigSite.staticSolrStrAuthorizeEnvironment(requeteSite_, (String)o);
		case "authorizeUrl":
			return ConfigSite.staticSolrStrAuthorizeUrl(requeteSite_, (String)o);
		case "paiementJour":
			return ConfigSite.staticSolrStrPaiementJour(requeteSite_, (Integer)o);
			default:
				return null;
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqConfigSite(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqConfigSite(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "configChemin":
			return ConfigSite.staticSolrFqConfigChemin(requeteSite_, o);
		case "identifiantSite":
			return ConfigSite.staticSolrFqIdentifiantSite(requeteSite_, o);
		case "prefixeEchappe":
			return ConfigSite.staticSolrFqPrefixeEchappe(requeteSite_, o);
		case "appliChemin":
			return ConfigSite.staticSolrFqAppliChemin(requeteSite_, o);
		case "racineDocument":
			return ConfigSite.staticSolrFqRacineDocument(requeteSite_, o);
		case "nomEntreprise":
			return ConfigSite.staticSolrFqNomEntreprise(requeteSite_, o);
		case "nomDomaine":
			return ConfigSite.staticSolrFqNomDomaine(requeteSite_, o);
		case "siteNomHote":
			return ConfigSite.staticSolrFqSiteNomHote(requeteSite_, o);
		case "sitePort":
			return ConfigSite.staticSolrFqSitePort(requeteSite_, o);
		case "siteInstances":
			return ConfigSite.staticSolrFqSiteInstances(requeteSite_, o);
		case "authRoyaume":
			return ConfigSite.staticSolrFqAuthRoyaume(requeteSite_, o);
		case "authRessource":
			return ConfigSite.staticSolrFqAuthRessource(requeteSite_, o);
		case "authSecret":
			return ConfigSite.staticSolrFqAuthSecret(requeteSite_, o);
		case "authSslRequis":
			return ConfigSite.staticSolrFqAuthSslRequis(requeteSite_, o);
		case "sslJksChemin":
			return ConfigSite.staticSolrFqSslJksChemin(requeteSite_, o);
		case "sslJksMotDePasse":
			return ConfigSite.staticSolrFqSslJksMotDePasse(requeteSite_, o);
		case "authUrl":
			return ConfigSite.staticSolrFqAuthUrl(requeteSite_, o);
		case "cryptageSel":
			return ConfigSite.staticSolrFqCryptageSel(requeteSite_, o);
		case "cryptageMotDePasse":
			return ConfigSite.staticSolrFqCryptageMotDePasse(requeteSite_, o);
		case "siteUrlBase":
			return ConfigSite.staticSolrFqSiteUrlBase(requeteSite_, o);
		case "siteNomAffichage":
			return ConfigSite.staticSolrFqSiteNomAffichage(requeteSite_, o);
		case "jdbcClassePilote":
			return ConfigSite.staticSolrFqJdbcClassePilote(requeteSite_, o);
		case "jdbcUtilisateur":
			return ConfigSite.staticSolrFqJdbcUtilisateur(requeteSite_, o);
		case "jdbcMotDePasse":
			return ConfigSite.staticSolrFqJdbcMotDePasse(requeteSite_, o);
		case "jdbcTailleMaxPiscine":
			return ConfigSite.staticSolrFqJdbcTailleMaxPiscine(requeteSite_, o);
		case "jdbcMaxFileAttente":
			return ConfigSite.staticSolrFqJdbcMaxFileAttente(requeteSite_, o);
		case "jdbcTailleMinPiscine":
			return ConfigSite.staticSolrFqJdbcTailleMinPiscine(requeteSite_, o);
		case "jdbcMaxDeclarations":
			return ConfigSite.staticSolrFqJdbcMaxDeclarations(requeteSite_, o);
		case "jdbcMaxDeclarationsParConnexion":
			return ConfigSite.staticSolrFqJdbcMaxDeclarationsParConnexion(requeteSite_, o);
		case "jdbcTempsInactiviteMax":
			return ConfigSite.staticSolrFqJdbcTempsInactiviteMax(requeteSite_, o);
		case "jdbcDelaiConnexion":
			return ConfigSite.staticSolrFqJdbcDelaiConnexion(requeteSite_, o);
		case "jdbcHote":
			return ConfigSite.staticSolrFqJdbcHote(requeteSite_, o);
		case "jdbcPort":
			return ConfigSite.staticSolrFqJdbcPort(requeteSite_, o);
		case "jdbcBaseDeDonnees":
			return ConfigSite.staticSolrFqJdbcBaseDeDonnees(requeteSite_, o);
		case "solrUrl":
			return ConfigSite.staticSolrFqSolrUrl(requeteSite_, o);
		case "solrUrlComputate":
			return ConfigSite.staticSolrFqSolrUrlComputate(requeteSite_, o);
		case "compteFacebook":
			return ConfigSite.staticSolrFqCompteFacebook(requeteSite_, o);
		case "compteTwitter":
			return ConfigSite.staticSolrFqCompteTwitter(requeteSite_, o);
		case "compteInstagram":
			return ConfigSite.staticSolrFqCompteInstagram(requeteSite_, o);
		case "compteYoutube":
			return ConfigSite.staticSolrFqCompteYoutube(requeteSite_, o);
		case "comptePinterest":
			return ConfigSite.staticSolrFqComptePinterest(requeteSite_, o);
		case "compteMail":
			return ConfigSite.staticSolrFqCompteMail(requeteSite_, o);
		case "roleAdmin":
			return ConfigSite.staticSolrFqRoleAdmin(requeteSite_, o);
		case "mailAdmin":
			return ConfigSite.staticSolrFqMailAdmin(requeteSite_, o);
		case "nombreExecuteurs":
			return ConfigSite.staticSolrFqNombreExecuteurs(requeteSite_, o);
		case "openApiVersion":
			return ConfigSite.staticSolrFqOpenApiVersion(requeteSite_, o);
		case "apiDescription":
			return ConfigSite.staticSolrFqApiDescription(requeteSite_, o);
		case "apiTitre":
			return ConfigSite.staticSolrFqApiTitre(requeteSite_, o);
		case "apiTermsService":
			return ConfigSite.staticSolrFqApiTermsService(requeteSite_, o);
		case "apiVersion":
			return ConfigSite.staticSolrFqApiVersion(requeteSite_, o);
		case "apiContactMail":
			return ConfigSite.staticSolrFqApiContactMail(requeteSite_, o);
		case "apiLicenceNom":
			return ConfigSite.staticSolrFqApiLicenceNom(requeteSite_, o);
		case "apiLicenceUrl":
			return ConfigSite.staticSolrFqApiLicenceUrl(requeteSite_, o);
		case "apiNomHote":
			return ConfigSite.staticSolrFqApiNomHote(requeteSite_, o);
		case "apiCheminBase":
			return ConfigSite.staticSolrFqApiCheminBase(requeteSite_, o);
		case "statiqueUrlBase":
			return ConfigSite.staticSolrFqStatiqueUrlBase(requeteSite_, o);
		case "mailHote":
			return ConfigSite.staticSolrFqMailHote(requeteSite_, o);
		case "mailPort":
			return ConfigSite.staticSolrFqMailPort(requeteSite_, o);
		case "mailUtilisateur":
			return ConfigSite.staticSolrFqMailUtilisateur(requeteSite_, o);
		case "mailMotDePasse":
			return ConfigSite.staticSolrFqMailMotDePasse(requeteSite_, o);
		case "mailDe":
			return ConfigSite.staticSolrFqMailDe(requeteSite_, o);
		case "mailAuth":
			return ConfigSite.staticSolrFqMailAuth(requeteSite_, o);
		case "mailSsl":
			return ConfigSite.staticSolrFqMailSsl(requeteSite_, o);
		case "siteZone":
			return ConfigSite.staticSolrFqSiteZone(requeteSite_, o);
		case "authorizeApiLoginId1":
			return ConfigSite.staticSolrFqAuthorizeApiLoginId1(requeteSite_, o);
		case "authorizeTransactionKey1":
			return ConfigSite.staticSolrFqAuthorizeTransactionKey1(requeteSite_, o);
		case "schoolLocation1":
			return ConfigSite.staticSolrFqSchoolLocation1(requeteSite_, o);
		case "authorizeApiLoginId2":
			return ConfigSite.staticSolrFqAuthorizeApiLoginId2(requeteSite_, o);
		case "authorizeTransactionKey2":
			return ConfigSite.staticSolrFqAuthorizeTransactionKey2(requeteSite_, o);
		case "schoolLocation2":
			return ConfigSite.staticSolrFqSchoolLocation2(requeteSite_, o);
		case "authorizeApiLoginId3":
			return ConfigSite.staticSolrFqAuthorizeApiLoginId3(requeteSite_, o);
		case "authorizeTransactionKey3":
			return ConfigSite.staticSolrFqAuthorizeTransactionKey3(requeteSite_, o);
		case "schoolLocation3":
			return ConfigSite.staticSolrFqSchoolLocation3(requeteSite_, o);
		case "authorizeApiLoginId4":
			return ConfigSite.staticSolrFqAuthorizeApiLoginId4(requeteSite_, o);
		case "authorizeTransactionKey4":
			return ConfigSite.staticSolrFqAuthorizeTransactionKey4(requeteSite_, o);
		case "schoolLocation4":
			return ConfigSite.staticSolrFqSchoolLocation4(requeteSite_, o);
		case "authorizeEnvironment":
			return ConfigSite.staticSolrFqAuthorizeEnvironment(requeteSite_, o);
		case "authorizeUrl":
			return ConfigSite.staticSolrFqAuthorizeUrl(requeteSite_, o);
		case "paiementJour":
			return ConfigSite.staticSolrFqPaiementJour(requeteSite_, o);
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
