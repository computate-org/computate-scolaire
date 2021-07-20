package org.computate.scolaire.frFR.vertx;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.File;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class AppliSwagger2Gen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(AppliSwagger2.class);

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	 L'entité requeteSite_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected RequeteSiteFrFR requeteSite_;
	@JsonIgnore
	public Couverture<RequeteSiteFrFR> requeteSite_Couverture = new Couverture<RequeteSiteFrFR>().p(this).c(RequeteSiteFrFR.class).var("requeteSite_").o(requeteSite_);

	/**	<br/> L'entité requeteSite_
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteSite_(Couverture<RequeteSiteFrFR> c);

	public RequeteSiteFrFR getRequeteSite_() {
		return requeteSite_;
	}

	public void setRequeteSite_(RequeteSiteFrFR requeteSite_) {
		this.requeteSite_ = requeteSite_;
		this.requeteSite_Couverture.dejaInitialise = true;
	}
	public static RequeteSiteFrFR staticSetRequeteSite_(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected AppliSwagger2 requeteSite_Init() {
		if(!requeteSite_Couverture.dejaInitialise) {
			_requeteSite_(requeteSite_Couverture);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Couverture.o);
		}
		requeteSite_Couverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	//////////////////
	// siteContexte //
	//////////////////

	/**	 L'entité siteContexte
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SiteContexteFrFR(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SiteContexteFrFR siteContexte = new SiteContexteFrFR();
	@JsonIgnore
	public Couverture<SiteContexteFrFR> siteContexteCouverture = new Couverture<SiteContexteFrFR>().p(this).c(SiteContexteFrFR.class).var("siteContexte").o(siteContexte);

	/**	<br/> L'entité siteContexte
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SiteContexteFrFR(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteContexte">Trouver l'entité siteContexte dans Solr</a>
	 * <br/>
	 * @param siteContexte est l'entité déjà construit. 
	 **/
	protected abstract void _siteContexte(SiteContexteFrFR o);

	public SiteContexteFrFR getSiteContexte() {
		return siteContexte;
	}

	public void setSiteContexte(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
		this.siteContexteCouverture.dejaInitialise = true;
	}
	public static SiteContexteFrFR staticSetSiteContexte(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected AppliSwagger2 siteContexteInit() {
		if(!siteContexteCouverture.dejaInitialise) {
			_siteContexte(siteContexte);
		}
		siteContexte.initLoinPourClasse(requeteSite_);
		siteContexteCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	////////////////
	// configSite //
	////////////////

	/**	 L'entité configSite
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ConfigSite configSite;
	@JsonIgnore
	public Couverture<ConfigSite> configSiteCouverture = new Couverture<ConfigSite>().p(this).c(ConfigSite.class).var("configSite").o(configSite);

	/**	<br/> L'entité configSite
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configSite">Trouver l'entité configSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _configSite(Couverture<ConfigSite> c);

	public ConfigSite getConfigSite() {
		return configSite;
	}

	public void setConfigSite(ConfigSite configSite) {
		this.configSite = configSite;
		this.configSiteCouverture.dejaInitialise = true;
	}
	public static ConfigSite staticSetConfigSite(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected AppliSwagger2 configSiteInit() {
		if(!configSiteCouverture.dejaInitialise) {
			_configSite(configSiteCouverture);
			if(configSite == null)
				setConfigSite(configSiteCouverture.o);
		}
		if(configSite != null)
			configSite.initLoinPourClasse(requeteSite_);
		configSiteCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:appliChemin">Trouver l'entité appliChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _appliChemin(Couverture<String> c);

	public String getAppliChemin() {
		return appliChemin;
	}
	public void setAppliChemin(String o) {
		this.appliChemin = AppliSwagger2.staticSetAppliChemin(requeteSite_, o);
		this.appliCheminCouverture.dejaInitialise = true;
	}
	public static String staticSetAppliChemin(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected AppliSwagger2 appliCheminInit() {
		if(!appliCheminCouverture.dejaInitialise) {
			_appliChemin(appliCheminCouverture);
			if(appliChemin == null)
				setAppliChemin(appliCheminCouverture.o);
		}
		appliCheminCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	public static String staticSolrAppliChemin(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrAppliChemin(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAppliChemin(RequeteSiteFrFR requeteSite_, String o) {
		return AppliSwagger2.staticSolrStrAppliChemin(requeteSite_, AppliSwagger2.staticSolrAppliChemin(requeteSite_, AppliSwagger2.staticSetAppliChemin(requeteSite_, o)));
	}

	public String solrAppliChemin() {
		return AppliSwagger2.staticSolrAppliChemin(requeteSite_, appliChemin);
	}

	public String strAppliChemin() {
		return appliChemin == null ? "" : appliChemin;
	}

	public String sqlAppliChemin() {
		return appliChemin;
	}

	public String jsonAppliChemin() {
		return appliChemin == null ? "" : appliChemin;
	}

	public String nomAffichageAppliChemin() {
		return null;
	}

	public String htmTooltipAppliChemin() {
		return null;
	}

	public String htmAppliChemin() {
		return appliChemin == null ? "" : StringEscapeUtils.escapeHtml4(strAppliChemin());
	}

	//////////////
	// appliNom //
	//////////////

	/**	 L'entité appliNom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String appliNom;
	@JsonIgnore
	public Couverture<String> appliNomCouverture = new Couverture<String>().p(this).c(String.class).var("appliNom").o(appliNom);

	/**	<br/> L'entité appliNom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:appliNom">Trouver l'entité appliNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _appliNom(Couverture<String> c);

	public String getAppliNom() {
		return appliNom;
	}
	public void setAppliNom(String o) {
		this.appliNom = AppliSwagger2.staticSetAppliNom(requeteSite_, o);
		this.appliNomCouverture.dejaInitialise = true;
	}
	public static String staticSetAppliNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected AppliSwagger2 appliNomInit() {
		if(!appliNomCouverture.dejaInitialise) {
			_appliNom(appliNomCouverture);
			if(appliNom == null)
				setAppliNom(appliNomCouverture.o);
		}
		appliNomCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	public static String staticSolrAppliNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrAppliNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAppliNom(RequeteSiteFrFR requeteSite_, String o) {
		return AppliSwagger2.staticSolrStrAppliNom(requeteSite_, AppliSwagger2.staticSolrAppliNom(requeteSite_, AppliSwagger2.staticSetAppliNom(requeteSite_, o)));
	}

	public String solrAppliNom() {
		return AppliSwagger2.staticSolrAppliNom(requeteSite_, appliNom);
	}

	public String strAppliNom() {
		return appliNom == null ? "" : appliNom;
	}

	public String sqlAppliNom() {
		return appliNom;
	}

	public String jsonAppliNom() {
		return appliNom == null ? "" : appliNom;
	}

	public String nomAffichageAppliNom() {
		return null;
	}

	public String htmTooltipAppliNom() {
		return null;
	}

	public String htmAppliNom() {
		return appliNom == null ? "" : StringEscapeUtils.escapeHtml4(strAppliNom());
	}

	///////////////
	// langueNom //
	///////////////

	/**	 L'entité langueNom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String langueNom;
	@JsonIgnore
	public Couverture<String> langueNomCouverture = new Couverture<String>().p(this).c(String.class).var("langueNom").o(langueNom);

	/**	<br/> L'entité langueNom
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:langueNom">Trouver l'entité langueNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _langueNom(Couverture<String> c);

	public String getLangueNom() {
		return langueNom;
	}
	public void setLangueNom(String o) {
		this.langueNom = AppliSwagger2.staticSetLangueNom(requeteSite_, o);
		this.langueNomCouverture.dejaInitialise = true;
	}
	public static String staticSetLangueNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected AppliSwagger2 langueNomInit() {
		if(!langueNomCouverture.dejaInitialise) {
			_langueNom(langueNomCouverture);
			if(langueNom == null)
				setLangueNom(langueNomCouverture.o);
		}
		langueNomCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	public static String staticSolrLangueNom(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrLangueNom(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqLangueNom(RequeteSiteFrFR requeteSite_, String o) {
		return AppliSwagger2.staticSolrStrLangueNom(requeteSite_, AppliSwagger2.staticSolrLangueNom(requeteSite_, AppliSwagger2.staticSetLangueNom(requeteSite_, o)));
	}

	public String solrLangueNom() {
		return AppliSwagger2.staticSolrLangueNom(requeteSite_, langueNom);
	}

	public String strLangueNom() {
		return langueNom == null ? "" : langueNom;
	}

	public String sqlLangueNom() {
		return langueNom;
	}

	public String jsonLangueNom() {
		return langueNom == null ? "" : langueNom;
	}

	public String nomAffichageLangueNom() {
		return null;
	}

	public String htmTooltipLangueNom() {
		return null;
	}

	public String htmLangueNom() {
		return langueNom == null ? "" : StringEscapeUtils.escapeHtml4(strLangueNom());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiVersion">Trouver l'entité openApiVersion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiVersion(Couverture<String> c);

	public String getOpenApiVersion() {
		return openApiVersion;
	}
	public void setOpenApiVersion(String o) {
		this.openApiVersion = AppliSwagger2.staticSetOpenApiVersion(requeteSite_, o);
		this.openApiVersionCouverture.dejaInitialise = true;
	}
	public static String staticSetOpenApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected AppliSwagger2 openApiVersionInit() {
		if(!openApiVersionCouverture.dejaInitialise) {
			_openApiVersion(openApiVersionCouverture);
			if(openApiVersion == null)
				setOpenApiVersion(openApiVersionCouverture.o);
		}
		openApiVersionCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	public static String staticSolrOpenApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrOpenApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqOpenApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return AppliSwagger2.staticSolrStrOpenApiVersion(requeteSite_, AppliSwagger2.staticSolrOpenApiVersion(requeteSite_, AppliSwagger2.staticSetOpenApiVersion(requeteSite_, o)));
	}

	public String solrOpenApiVersion() {
		return AppliSwagger2.staticSolrOpenApiVersion(requeteSite_, openApiVersion);
	}

	public String strOpenApiVersion() {
		return openApiVersion == null ? "" : openApiVersion;
	}

	public String sqlOpenApiVersion() {
		return openApiVersion;
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
	// openApiVersionNumero //
	//////////////////////////

	/**	 L'entité openApiVersionNumero
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer openApiVersionNumero;
	@JsonIgnore
	public Couverture<Integer> openApiVersionNumeroCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("openApiVersionNumero").o(openApiVersionNumero);

	/**	<br/> L'entité openApiVersionNumero
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiVersionNumero">Trouver l'entité openApiVersionNumero dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiVersionNumero(Couverture<Integer> c);

	public Integer getOpenApiVersionNumero() {
		return openApiVersionNumero;
	}

	public void setOpenApiVersionNumero(Integer openApiVersionNumero) {
		this.openApiVersionNumero = openApiVersionNumero;
		this.openApiVersionNumeroCouverture.dejaInitialise = true;
	}
	public void setOpenApiVersionNumero(String o) {
		this.openApiVersionNumero = AppliSwagger2.staticSetOpenApiVersionNumero(requeteSite_, o);
		this.openApiVersionNumeroCouverture.dejaInitialise = true;
	}
	public static Integer staticSetOpenApiVersionNumero(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected AppliSwagger2 openApiVersionNumeroInit() {
		if(!openApiVersionNumeroCouverture.dejaInitialise) {
			_openApiVersionNumero(openApiVersionNumeroCouverture);
			if(openApiVersionNumero == null)
				setOpenApiVersionNumero(openApiVersionNumeroCouverture.o);
		}
		openApiVersionNumeroCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	public static Integer staticSolrOpenApiVersionNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrOpenApiVersionNumero(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqOpenApiVersionNumero(RequeteSiteFrFR requeteSite_, String o) {
		return AppliSwagger2.staticSolrStrOpenApiVersionNumero(requeteSite_, AppliSwagger2.staticSolrOpenApiVersionNumero(requeteSite_, AppliSwagger2.staticSetOpenApiVersionNumero(requeteSite_, o)));
	}

	public Integer solrOpenApiVersionNumero() {
		return AppliSwagger2.staticSolrOpenApiVersionNumero(requeteSite_, openApiVersionNumero);
	}

	public String strOpenApiVersionNumero() {
		return openApiVersionNumero == null ? "" : openApiVersionNumero.toString();
	}

	public Integer sqlOpenApiVersionNumero() {
		return openApiVersionNumero;
	}

	public String jsonOpenApiVersionNumero() {
		return openApiVersionNumero == null ? "" : openApiVersionNumero.toString();
	}

	public String nomAffichageOpenApiVersionNumero() {
		return null;
	}

	public String htmTooltipOpenApiVersionNumero() {
		return null;
	}

	public String htmOpenApiVersionNumero() {
		return openApiVersionNumero == null ? "" : StringEscapeUtils.escapeHtml4(strOpenApiVersionNumero());
	}

	////////////////
	// tabsSchema //
	////////////////

	/**	 L'entité tabsSchema
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer tabsSchema;
	@JsonIgnore
	public Couverture<Integer> tabsSchemaCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("tabsSchema").o(tabsSchema);

	/**	<br/> L'entité tabsSchema
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:tabsSchema">Trouver l'entité tabsSchema dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _tabsSchema(Couverture<Integer> c);

	public Integer getTabsSchema() {
		return tabsSchema;
	}

	public void setTabsSchema(Integer tabsSchema) {
		this.tabsSchema = tabsSchema;
		this.tabsSchemaCouverture.dejaInitialise = true;
	}
	public void setTabsSchema(String o) {
		this.tabsSchema = AppliSwagger2.staticSetTabsSchema(requeteSite_, o);
		this.tabsSchemaCouverture.dejaInitialise = true;
	}
	public static Integer staticSetTabsSchema(RequeteSiteFrFR requeteSite_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected AppliSwagger2 tabsSchemaInit() {
		if(!tabsSchemaCouverture.dejaInitialise) {
			_tabsSchema(tabsSchemaCouverture);
			if(tabsSchema == null)
				setTabsSchema(tabsSchemaCouverture.o);
		}
		tabsSchemaCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	public static Integer staticSolrTabsSchema(RequeteSiteFrFR requeteSite_, Integer o) {
		return o;
	}

	public static String staticSolrStrTabsSchema(RequeteSiteFrFR requeteSite_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqTabsSchema(RequeteSiteFrFR requeteSite_, String o) {
		return AppliSwagger2.staticSolrStrTabsSchema(requeteSite_, AppliSwagger2.staticSolrTabsSchema(requeteSite_, AppliSwagger2.staticSetTabsSchema(requeteSite_, o)));
	}

	public Integer solrTabsSchema() {
		return AppliSwagger2.staticSolrTabsSchema(requeteSite_, tabsSchema);
	}

	public String strTabsSchema() {
		return tabsSchema == null ? "" : tabsSchema.toString();
	}

	public Integer sqlTabsSchema() {
		return tabsSchema;
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

	/**	 L'entité apiVersion
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String apiVersion;
	@JsonIgnore
	public Couverture<String> apiVersionCouverture = new Couverture<String>().p(this).c(String.class).var("apiVersion").o(apiVersion);

	/**	<br/> L'entité apiVersion
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiVersion">Trouver l'entité apiVersion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiVersion(Couverture<String> c);

	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String o) {
		this.apiVersion = AppliSwagger2.staticSetApiVersion(requeteSite_, o);
		this.apiVersionCouverture.dejaInitialise = true;
	}
	public static String staticSetApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected AppliSwagger2 apiVersionInit() {
		if(!apiVersionCouverture.dejaInitialise) {
			_apiVersion(apiVersionCouverture);
			if(apiVersion == null)
				setApiVersion(apiVersionCouverture.o);
		}
		apiVersionCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	public static String staticSolrApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqApiVersion(RequeteSiteFrFR requeteSite_, String o) {
		return AppliSwagger2.staticSolrStrApiVersion(requeteSite_, AppliSwagger2.staticSolrApiVersion(requeteSite_, AppliSwagger2.staticSetApiVersion(requeteSite_, o)));
	}

	public String solrApiVersion() {
		return AppliSwagger2.staticSolrApiVersion(requeteSite_, apiVersion);
	}

	public String strApiVersion() {
		return apiVersion == null ? "" : apiVersion;
	}

	public String sqlApiVersion() {
		return apiVersion;
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

	///////////////////////
	// openApiYamlChemin //
	///////////////////////

	/**	 L'entité openApiYamlChemin
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String openApiYamlChemin;
	@JsonIgnore
	public Couverture<String> openApiYamlCheminCouverture = new Couverture<String>().p(this).c(String.class).var("openApiYamlChemin").o(openApiYamlChemin);

	/**	<br/> L'entité openApiYamlChemin
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiYamlChemin">Trouver l'entité openApiYamlChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiYamlChemin(Couverture<String> c);

	public String getOpenApiYamlChemin() {
		return openApiYamlChemin;
	}
	public void setOpenApiYamlChemin(String o) {
		this.openApiYamlChemin = AppliSwagger2.staticSetOpenApiYamlChemin(requeteSite_, o);
		this.openApiYamlCheminCouverture.dejaInitialise = true;
	}
	public static String staticSetOpenApiYamlChemin(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}
	protected AppliSwagger2 openApiYamlCheminInit() {
		if(!openApiYamlCheminCouverture.dejaInitialise) {
			_openApiYamlChemin(openApiYamlCheminCouverture);
			if(openApiYamlChemin == null)
				setOpenApiYamlChemin(openApiYamlCheminCouverture.o);
		}
		openApiYamlCheminCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	public static String staticSolrOpenApiYamlChemin(RequeteSiteFrFR requeteSite_, String o) {
		return o;
	}

	public static String staticSolrStrOpenApiYamlChemin(RequeteSiteFrFR requeteSite_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqOpenApiYamlChemin(RequeteSiteFrFR requeteSite_, String o) {
		return AppliSwagger2.staticSolrStrOpenApiYamlChemin(requeteSite_, AppliSwagger2.staticSolrOpenApiYamlChemin(requeteSite_, AppliSwagger2.staticSetOpenApiYamlChemin(requeteSite_, o)));
	}

	public String solrOpenApiYamlChemin() {
		return AppliSwagger2.staticSolrOpenApiYamlChemin(requeteSite_, openApiYamlChemin);
	}

	public String strOpenApiYamlChemin() {
		return openApiYamlChemin == null ? "" : openApiYamlChemin;
	}

	public String sqlOpenApiYamlChemin() {
		return openApiYamlChemin;
	}

	public String jsonOpenApiYamlChemin() {
		return openApiYamlChemin == null ? "" : openApiYamlChemin;
	}

	public String nomAffichageOpenApiYamlChemin() {
		return null;
	}

	public String htmTooltipOpenApiYamlChemin() {
		return null;
	}

	public String htmOpenApiYamlChemin() {
		return openApiYamlChemin == null ? "" : StringEscapeUtils.escapeHtml4(strOpenApiYamlChemin());
	}

	////////////////////////
	// openApiYamlFichier //
	////////////////////////

	/**	 L'entité openApiYamlFichier
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected File openApiYamlFichier;
	@JsonIgnore
	public Couverture<File> openApiYamlFichierCouverture = new Couverture<File>().p(this).c(File.class).var("openApiYamlFichier").o(openApiYamlFichier);

	/**	<br/> L'entité openApiYamlFichier
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiYamlFichier">Trouver l'entité openApiYamlFichier dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiYamlFichier(Couverture<File> c);

	public File getOpenApiYamlFichier() {
		return openApiYamlFichier;
	}

	public void setOpenApiYamlFichier(File openApiYamlFichier) {
		this.openApiYamlFichier = openApiYamlFichier;
		this.openApiYamlFichierCouverture.dejaInitialise = true;
	}
	public static File staticSetOpenApiYamlFichier(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected AppliSwagger2 openApiYamlFichierInit() {
		if(!openApiYamlFichierCouverture.dejaInitialise) {
			_openApiYamlFichier(openApiYamlFichierCouverture);
			if(openApiYamlFichier == null)
				setOpenApiYamlFichier(openApiYamlFichierCouverture.o);
		}
		openApiYamlFichierCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	///////
	// w //
	///////

	/**	 L'entité w
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain w;
	@JsonIgnore
	public Couverture<ToutEcrivain> wCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("w").o(w);

	/**	<br/> L'entité w
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:w">Trouver l'entité w dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _w(Couverture<ToutEcrivain> c);

	public ToutEcrivain getW() {
		return w;
	}

	public void setW(ToutEcrivain w) {
		this.w = w;
		this.wCouverture.dejaInitialise = true;
	}
	public static ToutEcrivain staticSetW(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected AppliSwagger2 wInit() {
		if(!wCouverture.dejaInitialise) {
			_w(wCouverture);
			if(w == null)
				setW(wCouverture.o);
		}
		if(w != null)
			w.initLoinPourClasse(requeteSite_);
		wCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	//////////////
	// wChemins //
	//////////////

	/**	 L'entité wChemins
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wChemins;
	@JsonIgnore
	public Couverture<ToutEcrivain> wCheminsCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wChemins").o(wChemins);

	/**	<br/> L'entité wChemins
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wChemins">Trouver l'entité wChemins dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wChemins(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWChemins() {
		return wChemins;
	}

	public void setWChemins(ToutEcrivain wChemins) {
		this.wChemins = wChemins;
		this.wCheminsCouverture.dejaInitialise = true;
	}
	public static ToutEcrivain staticSetWChemins(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected AppliSwagger2 wCheminsInit() {
		if(!wCheminsCouverture.dejaInitialise) {
			_wChemins(wCheminsCouverture);
			if(wChemins == null)
				setWChemins(wCheminsCouverture.o);
		}
		if(wChemins != null)
			wChemins.initLoinPourClasse(requeteSite_);
		wCheminsCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	////////////////////
	// wCorpsRequetes //
	////////////////////

	/**	 L'entité wCorpsRequetes
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wCorpsRequetes;
	@JsonIgnore
	public Couverture<ToutEcrivain> wCorpsRequetesCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wCorpsRequetes").o(wCorpsRequetes);

	/**	<br/> L'entité wCorpsRequetes
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wCorpsRequetes">Trouver l'entité wCorpsRequetes dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wCorpsRequetes(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWCorpsRequetes() {
		return wCorpsRequetes;
	}

	public void setWCorpsRequetes(ToutEcrivain wCorpsRequetes) {
		this.wCorpsRequetes = wCorpsRequetes;
		this.wCorpsRequetesCouverture.dejaInitialise = true;
	}
	public static ToutEcrivain staticSetWCorpsRequetes(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected AppliSwagger2 wCorpsRequetesInit() {
		if(!wCorpsRequetesCouverture.dejaInitialise) {
			_wCorpsRequetes(wCorpsRequetesCouverture);
			if(wCorpsRequetes == null)
				setWCorpsRequetes(wCorpsRequetesCouverture.o);
		}
		if(wCorpsRequetes != null)
			wCorpsRequetes.initLoinPourClasse(requeteSite_);
		wCorpsRequetesCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	//////////////
	// wSchemas //
	//////////////

	/**	 L'entité wSchemas
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected ToutEcrivain wSchemas;
	@JsonIgnore
	public Couverture<ToutEcrivain> wSchemasCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("wSchemas").o(wSchemas);

	/**	<br/> L'entité wSchemas
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliSwagger2&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:wSchemas">Trouver l'entité wSchemas dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _wSchemas(Couverture<ToutEcrivain> c);

	public ToutEcrivain getWSchemas() {
		return wSchemas;
	}

	public void setWSchemas(ToutEcrivain wSchemas) {
		this.wSchemas = wSchemas;
		this.wSchemasCouverture.dejaInitialise = true;
	}
	public static ToutEcrivain staticSetWSchemas(RequeteSiteFrFR requeteSite_, String o) {
		return null;
	}
	protected AppliSwagger2 wSchemasInit() {
		if(!wSchemasCouverture.dejaInitialise) {
			_wSchemas(wSchemasCouverture);
			if(wSchemas == null)
				setWSchemas(wSchemasCouverture.o);
		}
		if(wSchemas != null)
			wSchemas.initLoinPourClasse(requeteSite_);
		wSchemasCouverture.dejaInitialise(true);
		return (AppliSwagger2)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseAppliSwagger2 = false;

	public AppliSwagger2 initLoinAppliSwagger2(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseAppliSwagger2) {
			dejaInitialiseAppliSwagger2 = true;
			initLoinAppliSwagger2();
		}
		return (AppliSwagger2)this;
	}

	public void initLoinAppliSwagger2() {
		initAppliSwagger2();
	}

	public void initAppliSwagger2() {
		requeteSite_Init();
		siteContexteInit();
		configSiteInit();
		appliCheminInit();
		appliNomInit();
		langueNomInit();
		openApiVersionInit();
		openApiVersionNumeroInit();
		tabsSchemaInit();
		apiVersionInit();
		openApiYamlCheminInit();
		openApiYamlFichierInit();
		wInit();
		wCheminsInit();
		wCorpsRequetesInit();
		wSchemasInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinAppliSwagger2(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteAppliSwagger2(RequeteSiteFrFR requeteSite_) {
		if(w != null)
			w.setRequeteSite_(requeteSite_);
		if(wChemins != null)
			wChemins.setRequeteSite_(requeteSite_);
		if(wCorpsRequetes != null)
			wCorpsRequetes.setRequeteSite_(requeteSite_);
		if(wSchemas != null)
			wSchemas.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteAppliSwagger2(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAppliSwagger2(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtenirAppliSwagger2(String var) {
		AppliSwagger2 oAppliSwagger2 = (AppliSwagger2)this;
		switch(var) {
			case "requeteSite_":
				return oAppliSwagger2.requeteSite_;
			case "siteContexte":
				return oAppliSwagger2.siteContexte;
			case "configSite":
				return oAppliSwagger2.configSite;
			case "appliChemin":
				return oAppliSwagger2.appliChemin;
			case "appliNom":
				return oAppliSwagger2.appliNom;
			case "langueNom":
				return oAppliSwagger2.langueNom;
			case "openApiVersion":
				return oAppliSwagger2.openApiVersion;
			case "openApiVersionNumero":
				return oAppliSwagger2.openApiVersionNumero;
			case "tabsSchema":
				return oAppliSwagger2.tabsSchema;
			case "apiVersion":
				return oAppliSwagger2.apiVersion;
			case "openApiYamlChemin":
				return oAppliSwagger2.openApiYamlChemin;
			case "openApiYamlFichier":
				return oAppliSwagger2.openApiYamlFichier;
			case "w":
				return oAppliSwagger2.w;
			case "wChemins":
				return oAppliSwagger2.wChemins;
			case "wCorpsRequetes":
				return oAppliSwagger2.wCorpsRequetes;
			case "wSchemas":
				return oAppliSwagger2.wSchemas;
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
				o = attribuerAppliSwagger2(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAppliSwagger2(String var, Object val) {
		AppliSwagger2 oAppliSwagger2 = (AppliSwagger2)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetAppliSwagger2(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetAppliSwagger2(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "appliChemin":
			return AppliSwagger2.staticSetAppliChemin(requeteSite_, o);
		case "appliNom":
			return AppliSwagger2.staticSetAppliNom(requeteSite_, o);
		case "langueNom":
			return AppliSwagger2.staticSetLangueNom(requeteSite_, o);
		case "openApiVersion":
			return AppliSwagger2.staticSetOpenApiVersion(requeteSite_, o);
		case "openApiVersionNumero":
			return AppliSwagger2.staticSetOpenApiVersionNumero(requeteSite_, o);
		case "tabsSchema":
			return AppliSwagger2.staticSetTabsSchema(requeteSite_, o);
		case "apiVersion":
			return AppliSwagger2.staticSetApiVersion(requeteSite_, o);
		case "openApiYamlChemin":
			return AppliSwagger2.staticSetOpenApiYamlChemin(requeteSite_, o);
			default:
				return null;
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrAppliSwagger2(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrAppliSwagger2(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "appliChemin":
			return AppliSwagger2.staticSolrAppliChemin(requeteSite_, (String)o);
		case "appliNom":
			return AppliSwagger2.staticSolrAppliNom(requeteSite_, (String)o);
		case "langueNom":
			return AppliSwagger2.staticSolrLangueNom(requeteSite_, (String)o);
		case "openApiVersion":
			return AppliSwagger2.staticSolrOpenApiVersion(requeteSite_, (String)o);
		case "openApiVersionNumero":
			return AppliSwagger2.staticSolrOpenApiVersionNumero(requeteSite_, (Integer)o);
		case "tabsSchema":
			return AppliSwagger2.staticSolrTabsSchema(requeteSite_, (Integer)o);
		case "apiVersion":
			return AppliSwagger2.staticSolrApiVersion(requeteSite_, (String)o);
		case "openApiYamlChemin":
			return AppliSwagger2.staticSolrOpenApiYamlChemin(requeteSite_, (String)o);
			default:
				return null;
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrAppliSwagger2(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrAppliSwagger2(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
		case "appliChemin":
			return AppliSwagger2.staticSolrStrAppliChemin(requeteSite_, (String)o);
		case "appliNom":
			return AppliSwagger2.staticSolrStrAppliNom(requeteSite_, (String)o);
		case "langueNom":
			return AppliSwagger2.staticSolrStrLangueNom(requeteSite_, (String)o);
		case "openApiVersion":
			return AppliSwagger2.staticSolrStrOpenApiVersion(requeteSite_, (String)o);
		case "openApiVersionNumero":
			return AppliSwagger2.staticSolrStrOpenApiVersionNumero(requeteSite_, (Integer)o);
		case "tabsSchema":
			return AppliSwagger2.staticSolrStrTabsSchema(requeteSite_, (Integer)o);
		case "apiVersion":
			return AppliSwagger2.staticSolrStrApiVersion(requeteSite_, (String)o);
		case "openApiYamlChemin":
			return AppliSwagger2.staticSolrStrOpenApiYamlChemin(requeteSite_, (String)o);
			default:
				return null;
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqAppliSwagger2(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqAppliSwagger2(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
		case "appliChemin":
			return AppliSwagger2.staticSolrFqAppliChemin(requeteSite_, o);
		case "appliNom":
			return AppliSwagger2.staticSolrFqAppliNom(requeteSite_, o);
		case "langueNom":
			return AppliSwagger2.staticSolrFqLangueNom(requeteSite_, o);
		case "openApiVersion":
			return AppliSwagger2.staticSolrFqOpenApiVersion(requeteSite_, o);
		case "openApiVersionNumero":
			return AppliSwagger2.staticSolrFqOpenApiVersionNumero(requeteSite_, o);
		case "tabsSchema":
			return AppliSwagger2.staticSolrFqTabsSchema(requeteSite_, o);
		case "apiVersion":
			return AppliSwagger2.staticSolrFqApiVersion(requeteSite_, o);
		case "openApiYamlChemin":
			return AppliSwagger2.staticSolrFqOpenApiYamlChemin(requeteSite_, o);
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
					o = definirAppliSwagger2(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAppliSwagger2(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	public boolean definirPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirAppliSwagger2(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAppliSwagger2(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiAppliSwagger2() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof AppliSwagger2) {
			AppliSwagger2 original = (AppliSwagger2)o;
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
		if(!(o instanceof AppliSwagger2))
			return false;
		AppliSwagger2 that = (AppliSwagger2)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AppliSwagger2 { ");
		sb.append(" }");
		return sb.toString();
	}
}
