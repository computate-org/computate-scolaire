package org.computate.scolaire.enUS.config;

import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.configuration2.INIConfiguration;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.frFR.cluster.Cluster;
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

	//////////////////
	// configChemin //
	//////////////////

	/**	L'entité « configChemin »
	 *	 is defined as null before being initialized. 
	 */
	protected String configChemin;
	public Wrap<String> configCheminWrap = new Wrap<String>().p(this).c(String.class).var("configChemin").o(configChemin);

	/**	<br/>L'entité « configChemin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:configChemin">Trouver l'entité configChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _configChemin(Wrap<String> c);

	public String getConfigChemin() {
		return configChemin;
	}

	public void setConfigChemin(String configChemin) {
		this.configChemin = configChemin;
		this.configCheminWrap.alreadyInitialized = true;
	}
	protected SiteConfig configCheminInit() {
		if(!configCheminWrap.alreadyInitialized) {
			_configChemin(configCheminWrap);
			if(configChemin == null)
				setConfigChemin(configCheminWrap.o);
		}
		configCheminWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrConfigChemin() {
		return configChemin;
	}

	public String strConfigChemin() {
		return configChemin == null ? "" : configChemin;
	}

	public String nomAffichageConfigChemin() {
		return null;
	}

	public String htmTooltipConfigChemin() {
		return null;
	}

	public String htmConfigChemin() {
		return configChemin == null ? "" : StringEscapeUtils.escapeHtml4(strConfigChemin());
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

	/////////////////////
	// identifiantSite //
	/////////////////////

	/**	L'entité « identifiantSite »
	 *	 is defined as null before being initialized. 
	 */
	protected String identifiantSite;
	public Wrap<String> identifiantSiteWrap = new Wrap<String>().p(this).c(String.class).var("identifiantSite").o(identifiantSite);

	/**	<br/>L'entité « identifiantSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:identifiantSite">Trouver l'entité identifiantSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _identifiantSite(Wrap<String> c);

	public String getIdentifiantSite() {
		return identifiantSite;
	}

	public void setIdentifiantSite(String identifiantSite) {
		this.identifiantSite = identifiantSite;
		this.identifiantSiteWrap.alreadyInitialized = true;
	}
	protected SiteConfig identifiantSiteInit() {
		if(!identifiantSiteWrap.alreadyInitialized) {
			_identifiantSite(identifiantSiteWrap);
			if(identifiantSite == null)
				setIdentifiantSite(identifiantSiteWrap.o);
		}
		identifiantSiteWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrIdentifiantSite() {
		return identifiantSite;
	}

	public String strIdentifiantSite() {
		return identifiantSite == null ? "" : identifiantSite;
	}

	public String nomAffichageIdentifiantSite() {
		return null;
	}

	public String htmTooltipIdentifiantSite() {
		return null;
	}

	public String htmIdentifiantSite() {
		return identifiantSite == null ? "" : StringEscapeUtils.escapeHtml4(strIdentifiantSite());
	}

	////////////////////
	// prefixeEchappe //
	////////////////////

	/**	L'entité « prefixeEchappe »
	 *	 is defined as null before being initialized. 
	 */
	protected String prefixeEchappe;
	public Wrap<String> prefixeEchappeWrap = new Wrap<String>().p(this).c(String.class).var("prefixeEchappe").o(prefixeEchappe);

	/**	<br/>L'entité « prefixeEchappe »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:prefixeEchappe">Trouver l'entité prefixeEchappe dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _prefixeEchappe(Wrap<String> c);

	public String getPrefixeEchappe() {
		return prefixeEchappe;
	}

	public void setPrefixeEchappe(String prefixeEchappe) {
		this.prefixeEchappe = prefixeEchappe;
		this.prefixeEchappeWrap.alreadyInitialized = true;
	}
	protected SiteConfig prefixeEchappeInit() {
		if(!prefixeEchappeWrap.alreadyInitialized) {
			_prefixeEchappe(prefixeEchappeWrap);
			if(prefixeEchappe == null)
				setPrefixeEchappe(prefixeEchappeWrap.o);
		}
		prefixeEchappeWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrPrefixeEchappe() {
		return prefixeEchappe;
	}

	public String strPrefixeEchappe() {
		return prefixeEchappe == null ? "" : prefixeEchappe;
	}

	public String nomAffichagePrefixeEchappe() {
		return null;
	}

	public String htmTooltipPrefixeEchappe() {
		return null;
	}

	public String htmPrefixeEchappe() {
		return prefixeEchappe == null ? "" : StringEscapeUtils.escapeHtml4(strPrefixeEchappe());
	}

	/////////////////
	// appliChemin //
	/////////////////

	/**	L'entité « appliChemin »
	 *	 is defined as null before being initialized. 
	 */
	protected String appliChemin;
	public Wrap<String> appliCheminWrap = new Wrap<String>().p(this).c(String.class).var("appliChemin").o(appliChemin);

	/**	<br/>L'entité « appliChemin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:appliChemin">Trouver l'entité appliChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _appliChemin(Wrap<String> c);

	public String getAppliChemin() {
		return appliChemin;
	}

	public void setAppliChemin(String appliChemin) {
		this.appliChemin = appliChemin;
		this.appliCheminWrap.alreadyInitialized = true;
	}
	protected SiteConfig appliCheminInit() {
		if(!appliCheminWrap.alreadyInitialized) {
			_appliChemin(appliCheminWrap);
			if(appliChemin == null)
				setAppliChemin(appliCheminWrap.o);
		}
		appliCheminWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAppliChemin() {
		return appliChemin;
	}

	public String strAppliChemin() {
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

	////////////////////
	// racineDocument //
	////////////////////

	/**	L'entité « racineDocument »
	 *	 is defined as null before being initialized. 
	 */
	protected String racineDocument;
	public Wrap<String> racineDocumentWrap = new Wrap<String>().p(this).c(String.class).var("racineDocument").o(racineDocument);

	/**	<br/>L'entité « racineDocument »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:racineDocument">Trouver l'entité racineDocument dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _racineDocument(Wrap<String> c);

	public String getRacineDocument() {
		return racineDocument;
	}

	public void setRacineDocument(String racineDocument) {
		this.racineDocument = racineDocument;
		this.racineDocumentWrap.alreadyInitialized = true;
	}
	protected SiteConfig racineDocumentInit() {
		if(!racineDocumentWrap.alreadyInitialized) {
			_racineDocument(racineDocumentWrap);
			if(racineDocument == null)
				setRacineDocument(racineDocumentWrap.o);
		}
		racineDocumentWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrRacineDocument() {
		return racineDocument;
	}

	public String strRacineDocument() {
		return racineDocument == null ? "" : racineDocument;
	}

	public String nomAffichageRacineDocument() {
		return null;
	}

	public String htmTooltipRacineDocument() {
		return null;
	}

	public String htmRacineDocument() {
		return racineDocument == null ? "" : StringEscapeUtils.escapeHtml4(strRacineDocument());
	}

	///////////////////
	// nomEntreprise //
	///////////////////

	/**	L'entité « nomEntreprise »
	 *	 is defined as null before being initialized. 
	 */
	protected String nomEntreprise;
	public Wrap<String> nomEntrepriseWrap = new Wrap<String>().p(this).c(String.class).var("nomEntreprise").o(nomEntreprise);

	/**	<br/>L'entité « nomEntreprise »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:nomEntreprise">Trouver l'entité nomEntreprise dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _nomEntreprise(Wrap<String> c);

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
		this.nomEntrepriseWrap.alreadyInitialized = true;
	}
	protected SiteConfig nomEntrepriseInit() {
		if(!nomEntrepriseWrap.alreadyInitialized) {
			_nomEntreprise(nomEntrepriseWrap);
			if(nomEntreprise == null)
				setNomEntreprise(nomEntrepriseWrap.o);
		}
		nomEntrepriseWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrNomEntreprise() {
		return nomEntreprise;
	}

	public String strNomEntreprise() {
		return nomEntreprise == null ? "" : nomEntreprise;
	}

	public String nomAffichageNomEntreprise() {
		return null;
	}

	public String htmTooltipNomEntreprise() {
		return null;
	}

	public String htmNomEntreprise() {
		return nomEntreprise == null ? "" : StringEscapeUtils.escapeHtml4(strNomEntreprise());
	}

	////////////////
	// nomDomaine //
	////////////////

	/**	L'entité « nomDomaine »
	 *	 is defined as null before being initialized. 
	 */
	protected String nomDomaine;
	public Wrap<String> nomDomaineWrap = new Wrap<String>().p(this).c(String.class).var("nomDomaine").o(nomDomaine);

	/**	<br/>L'entité « nomDomaine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:nomDomaine">Trouver l'entité nomDomaine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _nomDomaine(Wrap<String> c);

	public String getNomDomaine() {
		return nomDomaine;
	}

	public void setNomDomaine(String nomDomaine) {
		this.nomDomaine = nomDomaine;
		this.nomDomaineWrap.alreadyInitialized = true;
	}
	protected SiteConfig nomDomaineInit() {
		if(!nomDomaineWrap.alreadyInitialized) {
			_nomDomaine(nomDomaineWrap);
			if(nomDomaine == null)
				setNomDomaine(nomDomaineWrap.o);
		}
		nomDomaineWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrNomDomaine() {
		return nomDomaine;
	}

	public String strNomDomaine() {
		return nomDomaine == null ? "" : nomDomaine;
	}

	public String nomAffichageNomDomaine() {
		return null;
	}

	public String htmTooltipNomDomaine() {
		return null;
	}

	public String htmNomDomaine() {
		return nomDomaine == null ? "" : StringEscapeUtils.escapeHtml4(strNomDomaine());
	}

	/////////////////
	// siteNomHote //
	/////////////////

	/**	L'entité « siteNomHote »
	 *	 is defined as null before being initialized. 
	 */
	protected String siteNomHote;
	public Wrap<String> siteNomHoteWrap = new Wrap<String>().p(this).c(String.class).var("siteNomHote").o(siteNomHote);

	/**	<br/>L'entité « siteNomHote »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteNomHote">Trouver l'entité siteNomHote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteNomHote(Wrap<String> c);

	public String getSiteNomHote() {
		return siteNomHote;
	}

	public void setSiteNomHote(String siteNomHote) {
		this.siteNomHote = siteNomHote;
		this.siteNomHoteWrap.alreadyInitialized = true;
	}
	protected SiteConfig siteNomHoteInit() {
		if(!siteNomHoteWrap.alreadyInitialized) {
			_siteNomHote(siteNomHoteWrap);
			if(siteNomHote == null)
				setSiteNomHote(siteNomHoteWrap.o);
		}
		siteNomHoteWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSiteNomHote() {
		return siteNomHote;
	}

	public String strSiteNomHote() {
		return siteNomHote == null ? "" : siteNomHote;
	}

	public String nomAffichageSiteNomHote() {
		return null;
	}

	public String htmTooltipSiteNomHote() {
		return null;
	}

	public String htmSiteNomHote() {
		return siteNomHote == null ? "" : StringEscapeUtils.escapeHtml4(strSiteNomHote());
	}

	//////////////
	// sitePort //
	//////////////

	/**	L'entité « sitePort »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer sitePort;
	public Wrap<Integer> sitePortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("sitePort").o(sitePort);

	/**	<br/>L'entité « sitePort »
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

	/////////////////
	// authRoyaume //
	/////////////////

	/**	L'entité « authRoyaume »
	 *	 is defined as null before being initialized. 
	 */
	protected String authRoyaume;
	public Wrap<String> authRoyaumeWrap = new Wrap<String>().p(this).c(String.class).var("authRoyaume").o(authRoyaume);

	/**	<br/>L'entité « authRoyaume »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authRoyaume">Trouver l'entité authRoyaume dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authRoyaume(Wrap<String> c);

	public String getAuthRoyaume() {
		return authRoyaume;
	}

	public void setAuthRoyaume(String authRoyaume) {
		this.authRoyaume = authRoyaume;
		this.authRoyaumeWrap.alreadyInitialized = true;
	}
	protected SiteConfig authRoyaumeInit() {
		if(!authRoyaumeWrap.alreadyInitialized) {
			_authRoyaume(authRoyaumeWrap);
			if(authRoyaume == null)
				setAuthRoyaume(authRoyaumeWrap.o);
		}
		authRoyaumeWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthRoyaume() {
		return authRoyaume;
	}

	public String strAuthRoyaume() {
		return authRoyaume == null ? "" : authRoyaume;
	}

	public String nomAffichageAuthRoyaume() {
		return null;
	}

	public String htmTooltipAuthRoyaume() {
		return null;
	}

	public String htmAuthRoyaume() {
		return authRoyaume == null ? "" : StringEscapeUtils.escapeHtml4(strAuthRoyaume());
	}

	///////////////////
	// authRessource //
	///////////////////

	/**	L'entité « authRessource »
	 *	 is defined as null before being initialized. 
	 */
	protected String authRessource;
	public Wrap<String> authRessourceWrap = new Wrap<String>().p(this).c(String.class).var("authRessource").o(authRessource);

	/**	<br/>L'entité « authRessource »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authRessource">Trouver l'entité authRessource dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authRessource(Wrap<String> c);

	public String getAuthRessource() {
		return authRessource;
	}

	public void setAuthRessource(String authRessource) {
		this.authRessource = authRessource;
		this.authRessourceWrap.alreadyInitialized = true;
	}
	protected SiteConfig authRessourceInit() {
		if(!authRessourceWrap.alreadyInitialized) {
			_authRessource(authRessourceWrap);
			if(authRessource == null)
				setAuthRessource(authRessourceWrap.o);
		}
		authRessourceWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthRessource() {
		return authRessource;
	}

	public String strAuthRessource() {
		return authRessource == null ? "" : authRessource;
	}

	public String nomAffichageAuthRessource() {
		return null;
	}

	public String htmTooltipAuthRessource() {
		return null;
	}

	public String htmAuthRessource() {
		return authRessource == null ? "" : StringEscapeUtils.escapeHtml4(strAuthRessource());
	}

	////////////////
	// authSecret //
	////////////////

	/**	L'entité « authSecret »
	 *	 is defined as null before being initialized. 
	 */
	protected String authSecret;
	public Wrap<String> authSecretWrap = new Wrap<String>().p(this).c(String.class).var("authSecret").o(authSecret);

	/**	<br/>L'entité « authSecret »
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

	///////////////////
	// authSslRequis //
	///////////////////

	/**	L'entité « authSslRequis »
	 *	 is defined as null before being initialized. 
	 */
	protected String authSslRequis;
	public Wrap<String> authSslRequisWrap = new Wrap<String>().p(this).c(String.class).var("authSslRequis").o(authSslRequis);

	/**	<br/>L'entité « authSslRequis »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authSslRequis">Trouver l'entité authSslRequis dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authSslRequis(Wrap<String> c);

	public String getAuthSslRequis() {
		return authSslRequis;
	}

	public void setAuthSslRequis(String authSslRequis) {
		this.authSslRequis = authSslRequis;
		this.authSslRequisWrap.alreadyInitialized = true;
	}
	protected SiteConfig authSslRequisInit() {
		if(!authSslRequisWrap.alreadyInitialized) {
			_authSslRequis(authSslRequisWrap);
			if(authSslRequis == null)
				setAuthSslRequis(authSslRequisWrap.o);
		}
		authSslRequisWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrAuthSslRequis() {
		return authSslRequis;
	}

	public String strAuthSslRequis() {
		return authSslRequis == null ? "" : authSslRequis;
	}

	public String nomAffichageAuthSslRequis() {
		return null;
	}

	public String htmTooltipAuthSslRequis() {
		return null;
	}

	public String htmAuthSslRequis() {
		return authSslRequis == null ? "" : StringEscapeUtils.escapeHtml4(strAuthSslRequis());
	}

	//////////////////
	// sslJksChemin //
	//////////////////

	/**	L'entité « sslJksChemin »
	 *	 is defined as null before being initialized. 
	 */
	protected String sslJksChemin;
	public Wrap<String> sslJksCheminWrap = new Wrap<String>().p(this).c(String.class).var("sslJksChemin").o(sslJksChemin);

	/**	<br/>L'entité « sslJksChemin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sslJksChemin">Trouver l'entité sslJksChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sslJksChemin(Wrap<String> c);

	public String getSslJksChemin() {
		return sslJksChemin;
	}

	public void setSslJksChemin(String sslJksChemin) {
		this.sslJksChemin = sslJksChemin;
		this.sslJksCheminWrap.alreadyInitialized = true;
	}
	protected SiteConfig sslJksCheminInit() {
		if(!sslJksCheminWrap.alreadyInitialized) {
			_sslJksChemin(sslJksCheminWrap);
			if(sslJksChemin == null)
				setSslJksChemin(sslJksCheminWrap.o);
		}
		sslJksCheminWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSslJksChemin() {
		return sslJksChemin;
	}

	public String strSslJksChemin() {
		return sslJksChemin == null ? "" : sslJksChemin;
	}

	public String nomAffichageSslJksChemin() {
		return null;
	}

	public String htmTooltipSslJksChemin() {
		return null;
	}

	public String htmSslJksChemin() {
		return sslJksChemin == null ? "" : StringEscapeUtils.escapeHtml4(strSslJksChemin());
	}

	//////////////////////
	// sslJksMotDePasse //
	//////////////////////

	/**	L'entité « sslJksMotDePasse »
	 *	 is defined as null before being initialized. 
	 */
	protected String sslJksMotDePasse;
	public Wrap<String> sslJksMotDePasseWrap = new Wrap<String>().p(this).c(String.class).var("sslJksMotDePasse").o(sslJksMotDePasse);

	/**	<br/>L'entité « sslJksMotDePasse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sslJksMotDePasse">Trouver l'entité sslJksMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sslJksMotDePasse(Wrap<String> c);

	public String getSslJksMotDePasse() {
		return sslJksMotDePasse;
	}

	public void setSslJksMotDePasse(String sslJksMotDePasse) {
		this.sslJksMotDePasse = sslJksMotDePasse;
		this.sslJksMotDePasseWrap.alreadyInitialized = true;
	}
	protected SiteConfig sslJksMotDePasseInit() {
		if(!sslJksMotDePasseWrap.alreadyInitialized) {
			_sslJksMotDePasse(sslJksMotDePasseWrap);
			if(sslJksMotDePasse == null)
				setSslJksMotDePasse(sslJksMotDePasseWrap.o);
		}
		sslJksMotDePasseWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSslJksMotDePasse() {
		return sslJksMotDePasse;
	}

	public String strSslJksMotDePasse() {
		return sslJksMotDePasse == null ? "" : sslJksMotDePasse;
	}

	public String nomAffichageSslJksMotDePasse() {
		return null;
	}

	public String htmTooltipSslJksMotDePasse() {
		return null;
	}

	public String htmSslJksMotDePasse() {
		return sslJksMotDePasse == null ? "" : StringEscapeUtils.escapeHtml4(strSslJksMotDePasse());
	}

	/////////////
	// authUrl //
	/////////////

	/**	L'entité « authUrl »
	 *	 is defined as null before being initialized. 
	 */
	protected String authUrl;
	public Wrap<String> authUrlWrap = new Wrap<String>().p(this).c(String.class).var("authUrl").o(authUrl);

	/**	<br/>L'entité « authUrl »
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

	/////////////////
	// cryptageSel //
	/////////////////

	/**	L'entité « cryptageSel »
	 *	 is defined as null before being initialized. 
	 */
	protected String cryptageSel;
	public Wrap<String> cryptageSelWrap = new Wrap<String>().p(this).c(String.class).var("cryptageSel").o(cryptageSel);

	/**	<br/>L'entité « cryptageSel »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:cryptageSel">Trouver l'entité cryptageSel dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageSel(Wrap<String> c);

	public String getCryptageSel() {
		return cryptageSel;
	}

	public void setCryptageSel(String cryptageSel) {
		this.cryptageSel = cryptageSel;
		this.cryptageSelWrap.alreadyInitialized = true;
	}
	protected SiteConfig cryptageSelInit() {
		if(!cryptageSelWrap.alreadyInitialized) {
			_cryptageSel(cryptageSelWrap);
			if(cryptageSel == null)
				setCryptageSel(cryptageSelWrap.o);
		}
		cryptageSelWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrCryptageSel() {
		return cryptageSel;
	}

	public String strCryptageSel() {
		return cryptageSel == null ? "" : cryptageSel;
	}

	public String nomAffichageCryptageSel() {
		return null;
	}

	public String htmTooltipCryptageSel() {
		return null;
	}

	public String htmCryptageSel() {
		return cryptageSel == null ? "" : StringEscapeUtils.escapeHtml4(strCryptageSel());
	}

	////////////////////////
	// cryptageMotDePasse //
	////////////////////////

	/**	L'entité « cryptageMotDePasse »
	 *	 is defined as null before being initialized. 
	 */
	protected String cryptageMotDePasse;
	public Wrap<String> cryptageMotDePasseWrap = new Wrap<String>().p(this).c(String.class).var("cryptageMotDePasse").o(cryptageMotDePasse);

	/**	<br/>L'entité « cryptageMotDePasse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:cryptageMotDePasse">Trouver l'entité cryptageMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cryptageMotDePasse(Wrap<String> c);

	public String getCryptageMotDePasse() {
		return cryptageMotDePasse;
	}

	public void setCryptageMotDePasse(String cryptageMotDePasse) {
		this.cryptageMotDePasse = cryptageMotDePasse;
		this.cryptageMotDePasseWrap.alreadyInitialized = true;
	}
	protected SiteConfig cryptageMotDePasseInit() {
		if(!cryptageMotDePasseWrap.alreadyInitialized) {
			_cryptageMotDePasse(cryptageMotDePasseWrap);
			if(cryptageMotDePasse == null)
				setCryptageMotDePasse(cryptageMotDePasseWrap.o);
		}
		cryptageMotDePasseWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrCryptageMotDePasse() {
		return cryptageMotDePasse;
	}

	public String strCryptageMotDePasse() {
		return cryptageMotDePasse == null ? "" : cryptageMotDePasse;
	}

	public String nomAffichageCryptageMotDePasse() {
		return null;
	}

	public String htmTooltipCryptageMotDePasse() {
		return null;
	}

	public String htmCryptageMotDePasse() {
		return cryptageMotDePasse == null ? "" : StringEscapeUtils.escapeHtml4(strCryptageMotDePasse());
	}

	/////////////////
	// siteBaseUrl //
	/////////////////

	/**	L'entité « siteBaseUrl »
	 *	 is defined as null before being initialized. 
	 */
	protected String siteBaseUrl;
	public Wrap<String> siteBaseUrlWrap = new Wrap<String>().p(this).c(String.class).var("siteBaseUrl").o(siteBaseUrl);

	/**	<br/>L'entité « siteBaseUrl »
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

	//////////////////////
	// siteNomAffichage //
	//////////////////////

	/**	L'entité « siteNomAffichage »
	 *	 is defined as null before being initialized. 
	 */
	protected String siteNomAffichage;
	public Wrap<String> siteNomAffichageWrap = new Wrap<String>().p(this).c(String.class).var("siteNomAffichage").o(siteNomAffichage);

	/**	<br/>L'entité « siteNomAffichage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteNomAffichage">Trouver l'entité siteNomAffichage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteNomAffichage(Wrap<String> c);

	public String getSiteNomAffichage() {
		return siteNomAffichage;
	}

	public void setSiteNomAffichage(String siteNomAffichage) {
		this.siteNomAffichage = siteNomAffichage;
		this.siteNomAffichageWrap.alreadyInitialized = true;
	}
	protected SiteConfig siteNomAffichageInit() {
		if(!siteNomAffichageWrap.alreadyInitialized) {
			_siteNomAffichage(siteNomAffichageWrap);
			if(siteNomAffichage == null)
				setSiteNomAffichage(siteNomAffichageWrap.o);
		}
		siteNomAffichageWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrSiteNomAffichage() {
		return siteNomAffichage;
	}

	public String strSiteNomAffichage() {
		return siteNomAffichage == null ? "" : siteNomAffichage;
	}

	public String nomAffichageSiteNomAffichage() {
		return null;
	}

	public String htmTooltipSiteNomAffichage() {
		return null;
	}

	public String htmSiteNomAffichage() {
		return siteNomAffichage == null ? "" : StringEscapeUtils.escapeHtml4(strSiteNomAffichage());
	}

	//////////////////////
	// jdbcClassePilote //
	//////////////////////

	/**	L'entité « jdbcClassePilote »
	 *	 is defined as null before being initialized. 
	 */
	protected String jdbcClassePilote;
	public Wrap<String> jdbcClassePiloteWrap = new Wrap<String>().p(this).c(String.class).var("jdbcClassePilote").o(jdbcClassePilote);

	/**	<br/>L'entité « jdbcClassePilote »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcClassePilote">Trouver l'entité jdbcClassePilote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcClassePilote(Wrap<String> c);

	public String getJdbcClassePilote() {
		return jdbcClassePilote;
	}

	public void setJdbcClassePilote(String jdbcClassePilote) {
		this.jdbcClassePilote = jdbcClassePilote;
		this.jdbcClassePiloteWrap.alreadyInitialized = true;
	}
	protected SiteConfig jdbcClassePiloteInit() {
		if(!jdbcClassePiloteWrap.alreadyInitialized) {
			_jdbcClassePilote(jdbcClassePiloteWrap);
			if(jdbcClassePilote == null)
				setJdbcClassePilote(jdbcClassePiloteWrap.o);
		}
		jdbcClassePiloteWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrJdbcClassePilote() {
		return jdbcClassePilote;
	}

	public String strJdbcClassePilote() {
		return jdbcClassePilote == null ? "" : jdbcClassePilote;
	}

	public String nomAffichageJdbcClassePilote() {
		return null;
	}

	public String htmTooltipJdbcClassePilote() {
		return null;
	}

	public String htmJdbcClassePilote() {
		return jdbcClassePilote == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcClassePilote());
	}

	/////////////////////
	// jdbcUtilisateur //
	/////////////////////

	/**	L'entité « jdbcUtilisateur »
	 *	 is defined as null before being initialized. 
	 */
	protected String jdbcUtilisateur;
	public Wrap<String> jdbcUtilisateurWrap = new Wrap<String>().p(this).c(String.class).var("jdbcUtilisateur").o(jdbcUtilisateur);

	/**	<br/>L'entité « jdbcUtilisateur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcUtilisateur">Trouver l'entité jdbcUtilisateur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcUtilisateur(Wrap<String> c);

	public String getJdbcUtilisateur() {
		return jdbcUtilisateur;
	}

	public void setJdbcUtilisateur(String jdbcUtilisateur) {
		this.jdbcUtilisateur = jdbcUtilisateur;
		this.jdbcUtilisateurWrap.alreadyInitialized = true;
	}
	protected SiteConfig jdbcUtilisateurInit() {
		if(!jdbcUtilisateurWrap.alreadyInitialized) {
			_jdbcUtilisateur(jdbcUtilisateurWrap);
			if(jdbcUtilisateur == null)
				setJdbcUtilisateur(jdbcUtilisateurWrap.o);
		}
		jdbcUtilisateurWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrJdbcUtilisateur() {
		return jdbcUtilisateur;
	}

	public String strJdbcUtilisateur() {
		return jdbcUtilisateur == null ? "" : jdbcUtilisateur;
	}

	public String nomAffichageJdbcUtilisateur() {
		return null;
	}

	public String htmTooltipJdbcUtilisateur() {
		return null;
	}

	public String htmJdbcUtilisateur() {
		return jdbcUtilisateur == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcUtilisateur());
	}

	////////////////////
	// jdbcMotDePasse //
	////////////////////

	/**	L'entité « jdbcMotDePasse »
	 *	 is defined as null before being initialized. 
	 */
	protected String jdbcMotDePasse;
	public Wrap<String> jdbcMotDePasseWrap = new Wrap<String>().p(this).c(String.class).var("jdbcMotDePasse").o(jdbcMotDePasse);

	/**	<br/>L'entité « jdbcMotDePasse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMotDePasse">Trouver l'entité jdbcMotDePasse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMotDePasse(Wrap<String> c);

	public String getJdbcMotDePasse() {
		return jdbcMotDePasse;
	}

	public void setJdbcMotDePasse(String jdbcMotDePasse) {
		this.jdbcMotDePasse = jdbcMotDePasse;
		this.jdbcMotDePasseWrap.alreadyInitialized = true;
	}
	protected SiteConfig jdbcMotDePasseInit() {
		if(!jdbcMotDePasseWrap.alreadyInitialized) {
			_jdbcMotDePasse(jdbcMotDePasseWrap);
			if(jdbcMotDePasse == null)
				setJdbcMotDePasse(jdbcMotDePasseWrap.o);
		}
		jdbcMotDePasseWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrJdbcMotDePasse() {
		return jdbcMotDePasse;
	}

	public String strJdbcMotDePasse() {
		return jdbcMotDePasse == null ? "" : jdbcMotDePasse;
	}

	public String nomAffichageJdbcMotDePasse() {
		return null;
	}

	public String htmTooltipJdbcMotDePasse() {
		return null;
	}

	public String htmJdbcMotDePasse() {
		return jdbcMotDePasse == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcMotDePasse());
	}

	//////////////////////////
	// jdbcTailleMaxPiscine //
	//////////////////////////

	/**	L'entité « jdbcTailleMaxPiscine »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer jdbcTailleMaxPiscine;
	public Wrap<Integer> jdbcTailleMaxPiscineWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcTailleMaxPiscine").o(jdbcTailleMaxPiscine);

	/**	<br/>L'entité « jdbcTailleMaxPiscine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcTailleMaxPiscine">Trouver l'entité jdbcTailleMaxPiscine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcTailleMaxPiscine(Wrap<Integer> c);

	public Integer getJdbcTailleMaxPiscine() {
		return jdbcTailleMaxPiscine;
	}

	public void setJdbcTailleMaxPiscine(Integer jdbcTailleMaxPiscine) {
		this.jdbcTailleMaxPiscine = jdbcTailleMaxPiscine;
		this.jdbcTailleMaxPiscineWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcTailleMaxPiscine(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.jdbcTailleMaxPiscine = Integer.parseInt(o);
		this.jdbcTailleMaxPiscineWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcTailleMaxPiscineInit() {
		if(!jdbcTailleMaxPiscineWrap.alreadyInitialized) {
			_jdbcTailleMaxPiscine(jdbcTailleMaxPiscineWrap);
			if(jdbcTailleMaxPiscine == null)
				setJdbcTailleMaxPiscine(jdbcTailleMaxPiscineWrap.o);
		}
		jdbcTailleMaxPiscineWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcTailleMaxPiscine() {
		return jdbcTailleMaxPiscine;
	}

	public String strJdbcTailleMaxPiscine() {
		return jdbcTailleMaxPiscine == null ? "" : jdbcTailleMaxPiscine.toString();
	}

	public String nomAffichageJdbcTailleMaxPiscine() {
		return null;
	}

	public String htmTooltipJdbcTailleMaxPiscine() {
		return null;
	}

	public String htmJdbcTailleMaxPiscine() {
		return jdbcTailleMaxPiscine == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcTailleMaxPiscine());
	}

	///////////////////////////////
	// jdbcTailleInitialePiscine //
	///////////////////////////////

	/**	L'entité « jdbcTailleInitialePiscine »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer jdbcTailleInitialePiscine;
	public Wrap<Integer> jdbcTailleInitialePiscineWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcTailleInitialePiscine").o(jdbcTailleInitialePiscine);

	/**	<br/>L'entité « jdbcTailleInitialePiscine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcTailleInitialePiscine">Trouver l'entité jdbcTailleInitialePiscine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcTailleInitialePiscine(Wrap<Integer> c);

	public Integer getJdbcTailleInitialePiscine() {
		return jdbcTailleInitialePiscine;
	}

	public void setJdbcTailleInitialePiscine(Integer jdbcTailleInitialePiscine) {
		this.jdbcTailleInitialePiscine = jdbcTailleInitialePiscine;
		this.jdbcTailleInitialePiscineWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcTailleInitialePiscine(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.jdbcTailleInitialePiscine = Integer.parseInt(o);
		this.jdbcTailleInitialePiscineWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcTailleInitialePiscineInit() {
		if(!jdbcTailleInitialePiscineWrap.alreadyInitialized) {
			_jdbcTailleInitialePiscine(jdbcTailleInitialePiscineWrap);
			if(jdbcTailleInitialePiscine == null)
				setJdbcTailleInitialePiscine(jdbcTailleInitialePiscineWrap.o);
		}
		jdbcTailleInitialePiscineWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcTailleInitialePiscine() {
		return jdbcTailleInitialePiscine;
	}

	public String strJdbcTailleInitialePiscine() {
		return jdbcTailleInitialePiscine == null ? "" : jdbcTailleInitialePiscine.toString();
	}

	public String nomAffichageJdbcTailleInitialePiscine() {
		return null;
	}

	public String htmTooltipJdbcTailleInitialePiscine() {
		return null;
	}

	public String htmJdbcTailleInitialePiscine() {
		return jdbcTailleInitialePiscine == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcTailleInitialePiscine());
	}

	//////////////////////////
	// jdbcTailleMinPiscine //
	//////////////////////////

	/**	L'entité « jdbcTailleMinPiscine »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer jdbcTailleMinPiscine;
	public Wrap<Integer> jdbcTailleMinPiscineWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcTailleMinPiscine").o(jdbcTailleMinPiscine);

	/**	<br/>L'entité « jdbcTailleMinPiscine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcTailleMinPiscine">Trouver l'entité jdbcTailleMinPiscine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcTailleMinPiscine(Wrap<Integer> c);

	public Integer getJdbcTailleMinPiscine() {
		return jdbcTailleMinPiscine;
	}

	public void setJdbcTailleMinPiscine(Integer jdbcTailleMinPiscine) {
		this.jdbcTailleMinPiscine = jdbcTailleMinPiscine;
		this.jdbcTailleMinPiscineWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcTailleMinPiscine(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.jdbcTailleMinPiscine = Integer.parseInt(o);
		this.jdbcTailleMinPiscineWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcTailleMinPiscineInit() {
		if(!jdbcTailleMinPiscineWrap.alreadyInitialized) {
			_jdbcTailleMinPiscine(jdbcTailleMinPiscineWrap);
			if(jdbcTailleMinPiscine == null)
				setJdbcTailleMinPiscine(jdbcTailleMinPiscineWrap.o);
		}
		jdbcTailleMinPiscineWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcTailleMinPiscine() {
		return jdbcTailleMinPiscine;
	}

	public String strJdbcTailleMinPiscine() {
		return jdbcTailleMinPiscine == null ? "" : jdbcTailleMinPiscine.toString();
	}

	public String nomAffichageJdbcTailleMinPiscine() {
		return null;
	}

	public String htmTooltipJdbcTailleMinPiscine() {
		return null;
	}

	public String htmJdbcTailleMinPiscine() {
		return jdbcTailleMinPiscine == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcTailleMinPiscine());
	}

	/////////////////////////
	// jdbcMaxDeclarations //
	/////////////////////////

	/**	L'entité « jdbcMaxDeclarations »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer jdbcMaxDeclarations;
	public Wrap<Integer> jdbcMaxDeclarationsWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMaxDeclarations").o(jdbcMaxDeclarations);

	/**	<br/>L'entité « jdbcMaxDeclarations »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxDeclarations">Trouver l'entité jdbcMaxDeclarations dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMaxDeclarations(Wrap<Integer> c);

	public Integer getJdbcMaxDeclarations() {
		return jdbcMaxDeclarations;
	}

	public void setJdbcMaxDeclarations(Integer jdbcMaxDeclarations) {
		this.jdbcMaxDeclarations = jdbcMaxDeclarations;
		this.jdbcMaxDeclarationsWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcMaxDeclarations(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.jdbcMaxDeclarations = Integer.parseInt(o);
		this.jdbcMaxDeclarationsWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcMaxDeclarationsInit() {
		if(!jdbcMaxDeclarationsWrap.alreadyInitialized) {
			_jdbcMaxDeclarations(jdbcMaxDeclarationsWrap);
			if(jdbcMaxDeclarations == null)
				setJdbcMaxDeclarations(jdbcMaxDeclarationsWrap.o);
		}
		jdbcMaxDeclarationsWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcMaxDeclarations() {
		return jdbcMaxDeclarations;
	}

	public String strJdbcMaxDeclarations() {
		return jdbcMaxDeclarations == null ? "" : jdbcMaxDeclarations.toString();
	}

	public String nomAffichageJdbcMaxDeclarations() {
		return null;
	}

	public String htmTooltipJdbcMaxDeclarations() {
		return null;
	}

	public String htmJdbcMaxDeclarations() {
		return jdbcMaxDeclarations == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcMaxDeclarations());
	}

	/////////////////////////////////////
	// jdbcMaxDeclarationsParConnexion //
	/////////////////////////////////////

	/**	L'entité « jdbcMaxDeclarationsParConnexion »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer jdbcMaxDeclarationsParConnexion;
	public Wrap<Integer> jdbcMaxDeclarationsParConnexionWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcMaxDeclarationsParConnexion").o(jdbcMaxDeclarationsParConnexion);

	/**	<br/>L'entité « jdbcMaxDeclarationsParConnexion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcMaxDeclarationsParConnexion">Trouver l'entité jdbcMaxDeclarationsParConnexion dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcMaxDeclarationsParConnexion(Wrap<Integer> c);

	public Integer getJdbcMaxDeclarationsParConnexion() {
		return jdbcMaxDeclarationsParConnexion;
	}

	public void setJdbcMaxDeclarationsParConnexion(Integer jdbcMaxDeclarationsParConnexion) {
		this.jdbcMaxDeclarationsParConnexion = jdbcMaxDeclarationsParConnexion;
		this.jdbcMaxDeclarationsParConnexionWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcMaxDeclarationsParConnexion(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.jdbcMaxDeclarationsParConnexion = Integer.parseInt(o);
		this.jdbcMaxDeclarationsParConnexionWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcMaxDeclarationsParConnexionInit() {
		if(!jdbcMaxDeclarationsParConnexionWrap.alreadyInitialized) {
			_jdbcMaxDeclarationsParConnexion(jdbcMaxDeclarationsParConnexionWrap);
			if(jdbcMaxDeclarationsParConnexion == null)
				setJdbcMaxDeclarationsParConnexion(jdbcMaxDeclarationsParConnexionWrap.o);
		}
		jdbcMaxDeclarationsParConnexionWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcMaxDeclarationsParConnexion() {
		return jdbcMaxDeclarationsParConnexion;
	}

	public String strJdbcMaxDeclarationsParConnexion() {
		return jdbcMaxDeclarationsParConnexion == null ? "" : jdbcMaxDeclarationsParConnexion.toString();
	}

	public String nomAffichageJdbcMaxDeclarationsParConnexion() {
		return null;
	}

	public String htmTooltipJdbcMaxDeclarationsParConnexion() {
		return null;
	}

	public String htmJdbcMaxDeclarationsParConnexion() {
		return jdbcMaxDeclarationsParConnexion == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcMaxDeclarationsParConnexion());
	}

	////////////////////////////
	// jdbcTempsInactiviteMax //
	////////////////////////////

	/**	L'entité « jdbcTempsInactiviteMax »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer jdbcTempsInactiviteMax;
	public Wrap<Integer> jdbcTempsInactiviteMaxWrap = new Wrap<Integer>().p(this).c(Integer.class).var("jdbcTempsInactiviteMax").o(jdbcTempsInactiviteMax);

	/**	<br/>L'entité « jdbcTempsInactiviteMax »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcTempsInactiviteMax">Trouver l'entité jdbcTempsInactiviteMax dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcTempsInactiviteMax(Wrap<Integer> c);

	public Integer getJdbcTempsInactiviteMax() {
		return jdbcTempsInactiviteMax;
	}

	public void setJdbcTempsInactiviteMax(Integer jdbcTempsInactiviteMax) {
		this.jdbcTempsInactiviteMax = jdbcTempsInactiviteMax;
		this.jdbcTempsInactiviteMaxWrap.alreadyInitialized = true;
	}
	public SiteConfig setJdbcTempsInactiviteMax(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.jdbcTempsInactiviteMax = Integer.parseInt(o);
		this.jdbcTempsInactiviteMaxWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig jdbcTempsInactiviteMaxInit() {
		if(!jdbcTempsInactiviteMaxWrap.alreadyInitialized) {
			_jdbcTempsInactiviteMax(jdbcTempsInactiviteMaxWrap);
			if(jdbcTempsInactiviteMax == null)
				setJdbcTempsInactiviteMax(jdbcTempsInactiviteMaxWrap.o);
		}
		jdbcTempsInactiviteMaxWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrJdbcTempsInactiviteMax() {
		return jdbcTempsInactiviteMax;
	}

	public String strJdbcTempsInactiviteMax() {
		return jdbcTempsInactiviteMax == null ? "" : jdbcTempsInactiviteMax.toString();
	}

	public String nomAffichageJdbcTempsInactiviteMax() {
		return null;
	}

	public String htmTooltipJdbcTempsInactiviteMax() {
		return null;
	}

	public String htmJdbcTempsInactiviteMax() {
		return jdbcTempsInactiviteMax == null ? "" : StringEscapeUtils.escapeHtml4(strJdbcTempsInactiviteMax());
	}

	/////////////
	// jdbcUrl //
	/////////////

	/**	L'entité « jdbcUrl »
	 *	 is defined as null before being initialized. 
	 */
	protected String jdbcUrl;
	public Wrap<String> jdbcUrlWrap = new Wrap<String>().p(this).c(String.class).var("jdbcUrl").o(jdbcUrl);

	/**	<br/>L'entité « jdbcUrl »
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
	 *	 is defined as null before being initialized. 
	 */
	protected String solrUrl;
	public Wrap<String> solrUrlWrap = new Wrap<String>().p(this).c(String.class).var("solrUrl").o(solrUrl);

	/**	<br/>L'entité « solrUrl »
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

	//////////////////////
	// solrUrlComputate //
	//////////////////////

	/**	L'entité « solrUrlComputate »
	 *	 is defined as null before being initialized. 
	 */
	protected String solrUrlComputate;
	public Wrap<String> solrUrlComputateWrap = new Wrap<String>().p(this).c(String.class).var("solrUrlComputate").o(solrUrlComputate);

	/**	<br/>L'entité « solrUrlComputate »
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

	public String nomAffichageSolrUrlComputate() {
		return null;
	}

	public String htmTooltipSolrUrlComputate() {
		return null;
	}

	public String htmSolrUrlComputate() {
		return solrUrlComputate == null ? "" : StringEscapeUtils.escapeHtml4(strSolrUrlComputate());
	}

	/////////////////////////
	// jetonIdentitePaypal //
	/////////////////////////

	/**	L'entité « jetonIdentitePaypal »
	 *	 is defined as null before being initialized. 
	 */
	protected String jetonIdentitePaypal;
	public Wrap<String> jetonIdentitePaypalWrap = new Wrap<String>().p(this).c(String.class).var("jetonIdentitePaypal").o(jetonIdentitePaypal);

	/**	<br/>L'entité « jetonIdentitePaypal »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jetonIdentitePaypal">Trouver l'entité jetonIdentitePaypal dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jetonIdentitePaypal(Wrap<String> c);

	public String getJetonIdentitePaypal() {
		return jetonIdentitePaypal;
	}

	public void setJetonIdentitePaypal(String jetonIdentitePaypal) {
		this.jetonIdentitePaypal = jetonIdentitePaypal;
		this.jetonIdentitePaypalWrap.alreadyInitialized = true;
	}
	protected SiteConfig jetonIdentitePaypalInit() {
		if(!jetonIdentitePaypalWrap.alreadyInitialized) {
			_jetonIdentitePaypal(jetonIdentitePaypalWrap);
			if(jetonIdentitePaypal == null)
				setJetonIdentitePaypal(jetonIdentitePaypalWrap.o);
		}
		jetonIdentitePaypalWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrJetonIdentitePaypal() {
		return jetonIdentitePaypal;
	}

	public String strJetonIdentitePaypal() {
		return jetonIdentitePaypal == null ? "" : jetonIdentitePaypal;
	}

	public String nomAffichageJetonIdentitePaypal() {
		return null;
	}

	public String htmTooltipJetonIdentitePaypal() {
		return null;
	}

	public String htmJetonIdentitePaypal() {
		return jetonIdentitePaypal == null ? "" : StringEscapeUtils.escapeHtml4(strJetonIdentitePaypal());
	}

	////////////////////
	// compteFacebook //
	////////////////////

	/**	L'entité « compteFacebook »
	 *	 is defined as null before being initialized. 
	 */
	protected String compteFacebook;
	public Wrap<String> compteFacebookWrap = new Wrap<String>().p(this).c(String.class).var("compteFacebook").o(compteFacebook);

	/**	<br/>L'entité « compteFacebook »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:compteFacebook">Trouver l'entité compteFacebook dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteFacebook(Wrap<String> c);

	public String getCompteFacebook() {
		return compteFacebook;
	}

	public void setCompteFacebook(String compteFacebook) {
		this.compteFacebook = compteFacebook;
		this.compteFacebookWrap.alreadyInitialized = true;
	}
	protected SiteConfig compteFacebookInit() {
		if(!compteFacebookWrap.alreadyInitialized) {
			_compteFacebook(compteFacebookWrap);
			if(compteFacebook == null)
				setCompteFacebook(compteFacebookWrap.o);
		}
		compteFacebookWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrCompteFacebook() {
		return compteFacebook;
	}

	public String strCompteFacebook() {
		return compteFacebook == null ? "" : compteFacebook;
	}

	public String nomAffichageCompteFacebook() {
		return null;
	}

	public String htmTooltipCompteFacebook() {
		return null;
	}

	public String htmCompteFacebook() {
		return compteFacebook == null ? "" : StringEscapeUtils.escapeHtml4(strCompteFacebook());
	}

	///////////////////
	// compteTwitter //
	///////////////////

	/**	L'entité « compteTwitter »
	 *	 is defined as null before being initialized. 
	 */
	protected String compteTwitter;
	public Wrap<String> compteTwitterWrap = new Wrap<String>().p(this).c(String.class).var("compteTwitter").o(compteTwitter);

	/**	<br/>L'entité « compteTwitter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:compteTwitter">Trouver l'entité compteTwitter dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteTwitter(Wrap<String> c);

	public String getCompteTwitter() {
		return compteTwitter;
	}

	public void setCompteTwitter(String compteTwitter) {
		this.compteTwitter = compteTwitter;
		this.compteTwitterWrap.alreadyInitialized = true;
	}
	protected SiteConfig compteTwitterInit() {
		if(!compteTwitterWrap.alreadyInitialized) {
			_compteTwitter(compteTwitterWrap);
			if(compteTwitter == null)
				setCompteTwitter(compteTwitterWrap.o);
		}
		compteTwitterWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrCompteTwitter() {
		return compteTwitter;
	}

	public String strCompteTwitter() {
		return compteTwitter == null ? "" : compteTwitter;
	}

	public String nomAffichageCompteTwitter() {
		return null;
	}

	public String htmTooltipCompteTwitter() {
		return null;
	}

	public String htmCompteTwitter() {
		return compteTwitter == null ? "" : StringEscapeUtils.escapeHtml4(strCompteTwitter());
	}

	//////////////////////
	// compteGooglePlus //
	//////////////////////

	/**	L'entité « compteGooglePlus »
	 *	 is defined as null before being initialized. 
	 */
	protected String compteGooglePlus;
	public Wrap<String> compteGooglePlusWrap = new Wrap<String>().p(this).c(String.class).var("compteGooglePlus").o(compteGooglePlus);

	/**	<br/>L'entité « compteGooglePlus »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:compteGooglePlus">Trouver l'entité compteGooglePlus dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteGooglePlus(Wrap<String> c);

	public String getCompteGooglePlus() {
		return compteGooglePlus;
	}

	public void setCompteGooglePlus(String compteGooglePlus) {
		this.compteGooglePlus = compteGooglePlus;
		this.compteGooglePlusWrap.alreadyInitialized = true;
	}
	protected SiteConfig compteGooglePlusInit() {
		if(!compteGooglePlusWrap.alreadyInitialized) {
			_compteGooglePlus(compteGooglePlusWrap);
			if(compteGooglePlus == null)
				setCompteGooglePlus(compteGooglePlusWrap.o);
		}
		compteGooglePlusWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrCompteGooglePlus() {
		return compteGooglePlus;
	}

	public String strCompteGooglePlus() {
		return compteGooglePlus == null ? "" : compteGooglePlus;
	}

	public String nomAffichageCompteGooglePlus() {
		return null;
	}

	public String htmTooltipCompteGooglePlus() {
		return null;
	}

	public String htmCompteGooglePlus() {
		return compteGooglePlus == null ? "" : StringEscapeUtils.escapeHtml4(strCompteGooglePlus());
	}

	/////////////////////
	// compteInstagram //
	/////////////////////

	/**	L'entité « compteInstagram »
	 *	 is defined as null before being initialized. 
	 */
	protected String compteInstagram;
	public Wrap<String> compteInstagramWrap = new Wrap<String>().p(this).c(String.class).var("compteInstagram").o(compteInstagram);

	/**	<br/>L'entité « compteInstagram »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:compteInstagram">Trouver l'entité compteInstagram dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteInstagram(Wrap<String> c);

	public String getCompteInstagram() {
		return compteInstagram;
	}

	public void setCompteInstagram(String compteInstagram) {
		this.compteInstagram = compteInstagram;
		this.compteInstagramWrap.alreadyInitialized = true;
	}
	protected SiteConfig compteInstagramInit() {
		if(!compteInstagramWrap.alreadyInitialized) {
			_compteInstagram(compteInstagramWrap);
			if(compteInstagram == null)
				setCompteInstagram(compteInstagramWrap.o);
		}
		compteInstagramWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrCompteInstagram() {
		return compteInstagram;
	}

	public String strCompteInstagram() {
		return compteInstagram == null ? "" : compteInstagram;
	}

	public String nomAffichageCompteInstagram() {
		return null;
	}

	public String htmTooltipCompteInstagram() {
		return null;
	}

	public String htmCompteInstagram() {
		return compteInstagram == null ? "" : StringEscapeUtils.escapeHtml4(strCompteInstagram());
	}

	///////////////////
	// compteYoutube //
	///////////////////

	/**	L'entité « compteYoutube »
	 *	 is defined as null before being initialized. 
	 */
	protected String compteYoutube;
	public Wrap<String> compteYoutubeWrap = new Wrap<String>().p(this).c(String.class).var("compteYoutube").o(compteYoutube);

	/**	<br/>L'entité « compteYoutube »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:compteYoutube">Trouver l'entité compteYoutube dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteYoutube(Wrap<String> c);

	public String getCompteYoutube() {
		return compteYoutube;
	}

	public void setCompteYoutube(String compteYoutube) {
		this.compteYoutube = compteYoutube;
		this.compteYoutubeWrap.alreadyInitialized = true;
	}
	protected SiteConfig compteYoutubeInit() {
		if(!compteYoutubeWrap.alreadyInitialized) {
			_compteYoutube(compteYoutubeWrap);
			if(compteYoutube == null)
				setCompteYoutube(compteYoutubeWrap.o);
		}
		compteYoutubeWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrCompteYoutube() {
		return compteYoutube;
	}

	public String strCompteYoutube() {
		return compteYoutube == null ? "" : compteYoutube;
	}

	public String nomAffichageCompteYoutube() {
		return null;
	}

	public String htmTooltipCompteYoutube() {
		return null;
	}

	public String htmCompteYoutube() {
		return compteYoutube == null ? "" : StringEscapeUtils.escapeHtml4(strCompteYoutube());
	}

	/////////////////////////////
	// identifiantCanalYoutube //
	/////////////////////////////

	/**	L'entité « identifiantCanalYoutube »
	 *	 is defined as null before being initialized. 
	 */
	protected String identifiantCanalYoutube;
	public Wrap<String> identifiantCanalYoutubeWrap = new Wrap<String>().p(this).c(String.class).var("identifiantCanalYoutube").o(identifiantCanalYoutube);

	/**	<br/>L'entité « identifiantCanalYoutube »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:identifiantCanalYoutube">Trouver l'entité identifiantCanalYoutube dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _identifiantCanalYoutube(Wrap<String> c);

	public String getIdentifiantCanalYoutube() {
		return identifiantCanalYoutube;
	}

	public void setIdentifiantCanalYoutube(String identifiantCanalYoutube) {
		this.identifiantCanalYoutube = identifiantCanalYoutube;
		this.identifiantCanalYoutubeWrap.alreadyInitialized = true;
	}
	protected SiteConfig identifiantCanalYoutubeInit() {
		if(!identifiantCanalYoutubeWrap.alreadyInitialized) {
			_identifiantCanalYoutube(identifiantCanalYoutubeWrap);
			if(identifiantCanalYoutube == null)
				setIdentifiantCanalYoutube(identifiantCanalYoutubeWrap.o);
		}
		identifiantCanalYoutubeWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrIdentifiantCanalYoutube() {
		return identifiantCanalYoutube;
	}

	public String strIdentifiantCanalYoutube() {
		return identifiantCanalYoutube == null ? "" : identifiantCanalYoutube;
	}

	public String nomAffichageIdentifiantCanalYoutube() {
		return null;
	}

	public String htmTooltipIdentifiantCanalYoutube() {
		return null;
	}

	public String htmIdentifiantCanalYoutube() {
		return identifiantCanalYoutube == null ? "" : StringEscapeUtils.escapeHtml4(strIdentifiantCanalYoutube());
	}

	/////////////////////
	// comptePinterest //
	/////////////////////

	/**	L'entité « comptePinterest »
	 *	 is defined as null before being initialized. 
	 */
	protected String comptePinterest;
	public Wrap<String> comptePinterestWrap = new Wrap<String>().p(this).c(String.class).var("comptePinterest").o(comptePinterest);

	/**	<br/>L'entité « comptePinterest »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:comptePinterest">Trouver l'entité comptePinterest dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _comptePinterest(Wrap<String> c);

	public String getComptePinterest() {
		return comptePinterest;
	}

	public void setComptePinterest(String comptePinterest) {
		this.comptePinterest = comptePinterest;
		this.comptePinterestWrap.alreadyInitialized = true;
	}
	protected SiteConfig comptePinterestInit() {
		if(!comptePinterestWrap.alreadyInitialized) {
			_comptePinterest(comptePinterestWrap);
			if(comptePinterest == null)
				setComptePinterest(comptePinterestWrap.o);
		}
		comptePinterestWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrComptePinterest() {
		return comptePinterest;
	}

	public String strComptePinterest() {
		return comptePinterest == null ? "" : comptePinterest;
	}

	public String nomAffichageComptePinterest() {
		return null;
	}

	public String htmTooltipComptePinterest() {
		return null;
	}

	public String htmComptePinterest() {
		return comptePinterest == null ? "" : StringEscapeUtils.escapeHtml4(strComptePinterest());
	}

	///////////////////////
	// compteOpenclipart //
	///////////////////////

	/**	L'entité « compteOpenclipart »
	 *	 is defined as null before being initialized. 
	 */
	protected String compteOpenclipart;
	public Wrap<String> compteOpenclipartWrap = new Wrap<String>().p(this).c(String.class).var("compteOpenclipart").o(compteOpenclipart);

	/**	<br/>L'entité « compteOpenclipart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:compteOpenclipart">Trouver l'entité compteOpenclipart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteOpenclipart(Wrap<String> c);

	public String getCompteOpenclipart() {
		return compteOpenclipart;
	}

	public void setCompteOpenclipart(String compteOpenclipart) {
		this.compteOpenclipart = compteOpenclipart;
		this.compteOpenclipartWrap.alreadyInitialized = true;
	}
	protected SiteConfig compteOpenclipartInit() {
		if(!compteOpenclipartWrap.alreadyInitialized) {
			_compteOpenclipart(compteOpenclipartWrap);
			if(compteOpenclipart == null)
				setCompteOpenclipart(compteOpenclipartWrap.o);
		}
		compteOpenclipartWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrCompteOpenclipart() {
		return compteOpenclipart;
	}

	public String strCompteOpenclipart() {
		return compteOpenclipart == null ? "" : compteOpenclipart;
	}

	public String nomAffichageCompteOpenclipart() {
		return null;
	}

	public String htmTooltipCompteOpenclipart() {
		return null;
	}

	public String htmCompteOpenclipart() {
		return compteOpenclipart == null ? "" : StringEscapeUtils.escapeHtml4(strCompteOpenclipart());
	}

	////////////////
	// compteMail //
	////////////////

	/**	L'entité « compteMail »
	 *	 is defined as null before being initialized. 
	 */
	protected String compteMail;
	public Wrap<String> compteMailWrap = new Wrap<String>().p(this).c(String.class).var("compteMail").o(compteMail);

	/**	<br/>L'entité « compteMail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:compteMail">Trouver l'entité compteMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteMail(Wrap<String> c);

	public String getCompteMail() {
		return compteMail;
	}

	public void setCompteMail(String compteMail) {
		this.compteMail = compteMail;
		this.compteMailWrap.alreadyInitialized = true;
	}
	protected SiteConfig compteMailInit() {
		if(!compteMailWrap.alreadyInitialized) {
			_compteMail(compteMailWrap);
			if(compteMail == null)
				setCompteMail(compteMailWrap.o);
		}
		compteMailWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrCompteMail() {
		return compteMail;
	}

	public String strCompteMail() {
		return compteMail == null ? "" : compteMail;
	}

	public String nomAffichageCompteMail() {
		return null;
	}

	public String htmTooltipCompteMail() {
		return null;
	}

	public String htmCompteMail() {
		return compteMail == null ? "" : StringEscapeUtils.escapeHtml4(strCompteMail());
	}

	///////////////
	// roleAdmin //
	///////////////

	/**	L'entité « roleAdmin »
	 *	 is defined as null before being initialized. 
	 */
	protected String roleAdmin;
	public Wrap<String> roleAdminWrap = new Wrap<String>().p(this).c(String.class).var("roleAdmin").o(roleAdmin);

	/**	<br/>L'entité « roleAdmin »
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

	///////////////
	// mailAdmin //
	///////////////

	/**	L'entité « mailAdmin »
	 *	 is defined as null before being initialized. 
	 */
	protected String mailAdmin;
	public Wrap<String> mailAdminWrap = new Wrap<String>().p(this).c(String.class).var("mailAdmin").o(mailAdmin);

	/**	<br/>L'entité « mailAdmin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:mailAdmin">Trouver l'entité mailAdmin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _mailAdmin(Wrap<String> c);

	public String getMailAdmin() {
		return mailAdmin;
	}

	public void setMailAdmin(String mailAdmin) {
		this.mailAdmin = mailAdmin;
		this.mailAdminWrap.alreadyInitialized = true;
	}
	protected SiteConfig mailAdminInit() {
		if(!mailAdminWrap.alreadyInitialized) {
			_mailAdmin(mailAdminWrap);
			if(mailAdmin == null)
				setMailAdmin(mailAdminWrap.o);
		}
		mailAdminWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrMailAdmin() {
		return mailAdmin;
	}

	public String strMailAdmin() {
		return mailAdmin == null ? "" : mailAdmin;
	}

	public String nomAffichageMailAdmin() {
		return null;
	}

	public String htmTooltipMailAdmin() {
		return null;
	}

	public String htmMailAdmin() {
		return mailAdmin == null ? "" : StringEscapeUtils.escapeHtml4(strMailAdmin());
	}

	//////////////////////
	// nombreExecuteurs //
	//////////////////////

	/**	L'entité « nombreExecuteurs »
	 *	 is defined as null before being initialized. 
	 */
	protected Integer nombreExecuteurs;
	public Wrap<Integer> nombreExecuteursWrap = new Wrap<Integer>().p(this).c(Integer.class).var("nombreExecuteurs").o(nombreExecuteurs);

	/**	<br/>L'entité « nombreExecuteurs »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:nombreExecuteurs">Trouver l'entité nombreExecuteurs dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _nombreExecuteurs(Wrap<Integer> c);

	public Integer getNombreExecuteurs() {
		return nombreExecuteurs;
	}

	public void setNombreExecuteurs(Integer nombreExecuteurs) {
		this.nombreExecuteurs = nombreExecuteurs;
		this.nombreExecuteursWrap.alreadyInitialized = true;
	}
	public SiteConfig setNombreExecuteurs(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.nombreExecuteurs = Integer.parseInt(o);
		this.nombreExecuteursWrap.alreadyInitialized = true;
		return (SiteConfig)this;
	}
	protected SiteConfig nombreExecuteursInit() {
		if(!nombreExecuteursWrap.alreadyInitialized) {
			_nombreExecuteurs(nombreExecuteursWrap);
			if(nombreExecuteurs == null)
				setNombreExecuteurs(nombreExecuteursWrap.o);
		}
		nombreExecuteursWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public Integer solrNombreExecuteurs() {
		return nombreExecuteurs;
	}

	public String strNombreExecuteurs() {
		return nombreExecuteurs == null ? "" : nombreExecuteurs.toString();
	}

	public String nomAffichageNombreExecuteurs() {
		return null;
	}

	public String htmTooltipNombreExecuteurs() {
		return null;
	}

	public String htmNombreExecuteurs() {
		return nombreExecuteurs == null ? "" : StringEscapeUtils.escapeHtml4(strNombreExecuteurs());
	}

	////////////////////
	// openApiVersion //
	////////////////////

	/**	L'entité « openApiVersion »
	 *	 is defined as null before being initialized. 
	 */
	protected String openApiVersion;
	public Wrap<String> openApiVersionWrap = new Wrap<String>().p(this).c(String.class).var("openApiVersion").o(openApiVersion);

	/**	<br/>L'entité « openApiVersion »
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
	 *	 is defined as null before being initialized. 
	 */
	protected String apiDescription;
	public Wrap<String> apiDescriptionWrap = new Wrap<String>().p(this).c(String.class).var("apiDescription").o(apiDescription);

	/**	<br/>L'entité « apiDescription »
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
	// apiTitre //
	//////////////

	/**	L'entité « apiTitre »
	 *	 is defined as null before being initialized. 
	 */
	protected String apiTitre;
	public Wrap<String> apiTitreWrap = new Wrap<String>().p(this).c(String.class).var("apiTitre").o(apiTitre);

	/**	<br/>L'entité « apiTitre »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiTitre">Trouver l'entité apiTitre dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiTitre(Wrap<String> c);

	public String getApiTitre() {
		return apiTitre;
	}

	public void setApiTitre(String apiTitre) {
		this.apiTitre = apiTitre;
		this.apiTitreWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiTitreInit() {
		if(!apiTitreWrap.alreadyInitialized) {
			_apiTitre(apiTitreWrap);
			if(apiTitre == null)
				setApiTitre(apiTitreWrap.o);
		}
		apiTitreWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiTitre() {
		return apiTitre;
	}

	public String strApiTitre() {
		return apiTitre == null ? "" : apiTitre;
	}

	public String nomAffichageApiTitre() {
		return null;
	}

	public String htmTooltipApiTitre() {
		return null;
	}

	public String htmApiTitre() {
		return apiTitre == null ? "" : StringEscapeUtils.escapeHtml4(strApiTitre());
	}

	/////////////////////
	// apiTermsService //
	/////////////////////

	/**	L'entité « apiTermsService »
	 *	 is defined as null before being initialized. 
	 */
	protected String apiTermsService;
	public Wrap<String> apiTermsServiceWrap = new Wrap<String>().p(this).c(String.class).var("apiTermsService").o(apiTermsService);

	/**	<br/>L'entité « apiTermsService »
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
	 *	 is defined as null before being initialized. 
	 */
	protected String apiVersion;
	public Wrap<String> apiVersionWrap = new Wrap<String>().p(this).c(String.class).var("apiVersion").o(apiVersion);

	/**	<br/>L'entité « apiVersion »
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

	////////////////////
	// apiContactMail //
	////////////////////

	/**	L'entité « apiContactMail »
	 *	 is defined as null before being initialized. 
	 */
	protected String apiContactMail;
	public Wrap<String> apiContactMailWrap = new Wrap<String>().p(this).c(String.class).var("apiContactMail").o(apiContactMail);

	/**	<br/>L'entité « apiContactMail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiContactMail">Trouver l'entité apiContactMail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiContactMail(Wrap<String> c);

	public String getApiContactMail() {
		return apiContactMail;
	}

	public void setApiContactMail(String apiContactMail) {
		this.apiContactMail = apiContactMail;
		this.apiContactMailWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiContactMailInit() {
		if(!apiContactMailWrap.alreadyInitialized) {
			_apiContactMail(apiContactMailWrap);
			if(apiContactMail == null)
				setApiContactMail(apiContactMailWrap.o);
		}
		apiContactMailWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiContactMail() {
		return apiContactMail;
	}

	public String strApiContactMail() {
		return apiContactMail == null ? "" : apiContactMail;
	}

	public String nomAffichageApiContactMail() {
		return null;
	}

	public String htmTooltipApiContactMail() {
		return null;
	}

	public String htmApiContactMail() {
		return apiContactMail == null ? "" : StringEscapeUtils.escapeHtml4(strApiContactMail());
	}

	///////////////////
	// apiLicenceNom //
	///////////////////

	/**	L'entité « apiLicenceNom »
	 *	 is defined as null before being initialized. 
	 */
	protected String apiLicenceNom;
	public Wrap<String> apiLicenceNomWrap = new Wrap<String>().p(this).c(String.class).var("apiLicenceNom").o(apiLicenceNom);

	/**	<br/>L'entité « apiLicenceNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiLicenceNom">Trouver l'entité apiLicenceNom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiLicenceNom(Wrap<String> c);

	public String getApiLicenceNom() {
		return apiLicenceNom;
	}

	public void setApiLicenceNom(String apiLicenceNom) {
		this.apiLicenceNom = apiLicenceNom;
		this.apiLicenceNomWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiLicenceNomInit() {
		if(!apiLicenceNomWrap.alreadyInitialized) {
			_apiLicenceNom(apiLicenceNomWrap);
			if(apiLicenceNom == null)
				setApiLicenceNom(apiLicenceNomWrap.o);
		}
		apiLicenceNomWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiLicenceNom() {
		return apiLicenceNom;
	}

	public String strApiLicenceNom() {
		return apiLicenceNom == null ? "" : apiLicenceNom;
	}

	public String nomAffichageApiLicenceNom() {
		return null;
	}

	public String htmTooltipApiLicenceNom() {
		return null;
	}

	public String htmApiLicenceNom() {
		return apiLicenceNom == null ? "" : StringEscapeUtils.escapeHtml4(strApiLicenceNom());
	}

	///////////////////
	// apiLicenceUrl //
	///////////////////

	/**	L'entité « apiLicenceUrl »
	 *	 is defined as null before being initialized. 
	 */
	protected String apiLicenceUrl;
	public Wrap<String> apiLicenceUrlWrap = new Wrap<String>().p(this).c(String.class).var("apiLicenceUrl").o(apiLicenceUrl);

	/**	<br/>L'entité « apiLicenceUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiLicenceUrl">Trouver l'entité apiLicenceUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiLicenceUrl(Wrap<String> c);

	public String getApiLicenceUrl() {
		return apiLicenceUrl;
	}

	public void setApiLicenceUrl(String apiLicenceUrl) {
		this.apiLicenceUrl = apiLicenceUrl;
		this.apiLicenceUrlWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiLicenceUrlInit() {
		if(!apiLicenceUrlWrap.alreadyInitialized) {
			_apiLicenceUrl(apiLicenceUrlWrap);
			if(apiLicenceUrl == null)
				setApiLicenceUrl(apiLicenceUrlWrap.o);
		}
		apiLicenceUrlWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiLicenceUrl() {
		return apiLicenceUrl;
	}

	public String strApiLicenceUrl() {
		return apiLicenceUrl == null ? "" : apiLicenceUrl;
	}

	public String nomAffichageApiLicenceUrl() {
		return null;
	}

	public String htmTooltipApiLicenceUrl() {
		return null;
	}

	public String htmApiLicenceUrl() {
		return apiLicenceUrl == null ? "" : StringEscapeUtils.escapeHtml4(strApiLicenceUrl());
	}

	////////////////
	// apiNomHote //
	////////////////

	/**	L'entité « apiNomHote »
	 *	 is defined as null before being initialized. 
	 */
	protected String apiNomHote;
	public Wrap<String> apiNomHoteWrap = new Wrap<String>().p(this).c(String.class).var("apiNomHote").o(apiNomHote);

	/**	<br/>L'entité « apiNomHote »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiNomHote">Trouver l'entité apiNomHote dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiNomHote(Wrap<String> c);

	public String getApiNomHote() {
		return apiNomHote;
	}

	public void setApiNomHote(String apiNomHote) {
		this.apiNomHote = apiNomHote;
		this.apiNomHoteWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiNomHoteInit() {
		if(!apiNomHoteWrap.alreadyInitialized) {
			_apiNomHote(apiNomHoteWrap);
			if(apiNomHote == null)
				setApiNomHote(apiNomHoteWrap.o);
		}
		apiNomHoteWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiNomHote() {
		return apiNomHote;
	}

	public String strApiNomHote() {
		return apiNomHote == null ? "" : apiNomHote;
	}

	public String nomAffichageApiNomHote() {
		return null;
	}

	public String htmTooltipApiNomHote() {
		return null;
	}

	public String htmApiNomHote() {
		return apiNomHote == null ? "" : StringEscapeUtils.escapeHtml4(strApiNomHote());
	}

	///////////////////
	// apiCheminBase //
	///////////////////

	/**	L'entité « apiCheminBase »
	 *	 is defined as null before being initialized. 
	 */
	protected String apiCheminBase;
	public Wrap<String> apiCheminBaseWrap = new Wrap<String>().p(this).c(String.class).var("apiCheminBase").o(apiCheminBase);

	/**	<br/>L'entité « apiCheminBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:apiCheminBase">Trouver l'entité apiCheminBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _apiCheminBase(Wrap<String> c);

	public String getApiCheminBase() {
		return apiCheminBase;
	}

	public void setApiCheminBase(String apiCheminBase) {
		this.apiCheminBase = apiCheminBase;
		this.apiCheminBaseWrap.alreadyInitialized = true;
	}
	protected SiteConfig apiCheminBaseInit() {
		if(!apiCheminBaseWrap.alreadyInitialized) {
			_apiCheminBase(apiCheminBaseWrap);
			if(apiCheminBase == null)
				setApiCheminBase(apiCheminBaseWrap.o);
		}
		apiCheminBaseWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrApiCheminBase() {
		return apiCheminBase;
	}

	public String strApiCheminBase() {
		return apiCheminBase == null ? "" : apiCheminBase;
	}

	public String nomAffichageApiCheminBase() {
		return null;
	}

	public String htmTooltipApiCheminBase() {
		return null;
	}

	public String htmApiCheminBase() {
		return apiCheminBase == null ? "" : StringEscapeUtils.escapeHtml4(strApiCheminBase());
	}

	/////////////////////////
	// vertxServiceAddress //
	/////////////////////////

	/**	L'entité « vertxServiceAddress »
	 *	 is defined as null before being initialized. 
	 */
	protected String vertxServiceAddress;
	public Wrap<String> vertxServiceAddressWrap = new Wrap<String>().p(this).c(String.class).var("vertxServiceAddress").o(vertxServiceAddress);

	/**	<br/>L'entité « vertxServiceAddress »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:vertxServiceAddress">Trouver l'entité vertxServiceAddress dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _vertxServiceAddress(Wrap<String> c);

	public String getVertxServiceAddress() {
		return vertxServiceAddress;
	}

	public void setVertxServiceAddress(String vertxServiceAddress) {
		this.vertxServiceAddress = vertxServiceAddress;
		this.vertxServiceAddressWrap.alreadyInitialized = true;
	}
	protected SiteConfig vertxServiceAddressInit() {
		if(!vertxServiceAddressWrap.alreadyInitialized) {
			_vertxServiceAddress(vertxServiceAddressWrap);
			if(vertxServiceAddress == null)
				setVertxServiceAddress(vertxServiceAddressWrap.o);
		}
		vertxServiceAddressWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrVertxServiceAddress() {
		return vertxServiceAddress;
	}

	public String strVertxServiceAddress() {
		return vertxServiceAddress == null ? "" : vertxServiceAddress;
	}

	public String nomAffichageVertxServiceAddress() {
		return null;
	}

	public String htmTooltipVertxServiceAddress() {
		return null;
	}

	public String htmVertxServiceAddress() {
		return vertxServiceAddress == null ? "" : StringEscapeUtils.escapeHtml4(strVertxServiceAddress());
	}

	/////////////////////
	// statiqueUrlBase //
	/////////////////////

	/**	L'entité « statiqueUrlBase »
	 *	 is defined as null before being initialized. 
	 */
	protected String statiqueUrlBase;
	public Wrap<String> statiqueUrlBaseWrap = new Wrap<String>().p(this).c(String.class).var("statiqueUrlBase").o(statiqueUrlBase);

	/**	<br/>L'entité « statiqueUrlBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.config.SiteConfig&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:statiqueUrlBase">Trouver l'entité statiqueUrlBase dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _statiqueUrlBase(Wrap<String> c);

	public String getStatiqueUrlBase() {
		return statiqueUrlBase;
	}

	public void setStatiqueUrlBase(String statiqueUrlBase) {
		this.statiqueUrlBase = statiqueUrlBase;
		this.statiqueUrlBaseWrap.alreadyInitialized = true;
	}
	protected SiteConfig statiqueUrlBaseInit() {
		if(!statiqueUrlBaseWrap.alreadyInitialized) {
			_statiqueUrlBase(statiqueUrlBaseWrap);
			if(statiqueUrlBase == null)
				setStatiqueUrlBase(statiqueUrlBaseWrap.o);
		}
		statiqueUrlBaseWrap.alreadyInitialized(true);
		return (SiteConfig)this;
	}

	public String solrStatiqueUrlBase() {
		return statiqueUrlBase;
	}

	public String strStatiqueUrlBase() {
		return statiqueUrlBase == null ? "" : statiqueUrlBase;
	}

	public String nomAffichageStatiqueUrlBase() {
		return null;
	}

	public String htmTooltipStatiqueUrlBase() {
		return null;
	}

	public String htmStatiqueUrlBase() {
		return statiqueUrlBase == null ? "" : StringEscapeUtils.escapeHtml4(strStatiqueUrlBase());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSiteConfig = false;

	public SiteConfig initDeepSiteConfig(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
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
		siteBaseUrlInit();
		siteNomAffichageInit();
		jdbcClassePiloteInit();
		jdbcUtilisateurInit();
		jdbcMotDePasseInit();
		jdbcTailleMaxPiscineInit();
		jdbcTailleInitialePiscineInit();
		jdbcTailleMinPiscineInit();
		jdbcMaxDeclarationsInit();
		jdbcMaxDeclarationsParConnexionInit();
		jdbcTempsInactiviteMaxInit();
		jdbcUrlInit();
		solrUrlInit();
		solrUrlComputateInit();
		jetonIdentitePaypalInit();
		compteFacebookInit();
		compteTwitterInit();
		compteGooglePlusInit();
		compteInstagramInit();
		compteYoutubeInit();
		identifiantCanalYoutubeInit();
		comptePinterestInit();
		compteOpenclipartInit();
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
		vertxServiceAddressInit();
		statiqueUrlBaseInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSiteConfig(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSiteConfig(SiteRequestEnUS siteRequest_) {
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSiteConfig(siteRequest_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSiteConfig(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirSiteConfig(String var) {
		SiteConfig oSiteConfig = (SiteConfig)this;
		switch(var) {
			case "configChemin":
				return oSiteConfig.configChemin;
			case "config":
				return oSiteConfig.config;
			case "identifiantSite":
				return oSiteConfig.identifiantSite;
			case "prefixeEchappe":
				return oSiteConfig.prefixeEchappe;
			case "appliChemin":
				return oSiteConfig.appliChemin;
			case "racineDocument":
				return oSiteConfig.racineDocument;
			case "nomEntreprise":
				return oSiteConfig.nomEntreprise;
			case "nomDomaine":
				return oSiteConfig.nomDomaine;
			case "siteNomHote":
				return oSiteConfig.siteNomHote;
			case "sitePort":
				return oSiteConfig.sitePort;
			case "authRoyaume":
				return oSiteConfig.authRoyaume;
			case "authRessource":
				return oSiteConfig.authRessource;
			case "authSecret":
				return oSiteConfig.authSecret;
			case "authSslRequis":
				return oSiteConfig.authSslRequis;
			case "sslJksChemin":
				return oSiteConfig.sslJksChemin;
			case "sslJksMotDePasse":
				return oSiteConfig.sslJksMotDePasse;
			case "authUrl":
				return oSiteConfig.authUrl;
			case "cryptageSel":
				return oSiteConfig.cryptageSel;
			case "cryptageMotDePasse":
				return oSiteConfig.cryptageMotDePasse;
			case "siteBaseUrl":
				return oSiteConfig.siteBaseUrl;
			case "siteNomAffichage":
				return oSiteConfig.siteNomAffichage;
			case "jdbcClassePilote":
				return oSiteConfig.jdbcClassePilote;
			case "jdbcUtilisateur":
				return oSiteConfig.jdbcUtilisateur;
			case "jdbcMotDePasse":
				return oSiteConfig.jdbcMotDePasse;
			case "jdbcTailleMaxPiscine":
				return oSiteConfig.jdbcTailleMaxPiscine;
			case "jdbcTailleInitialePiscine":
				return oSiteConfig.jdbcTailleInitialePiscine;
			case "jdbcTailleMinPiscine":
				return oSiteConfig.jdbcTailleMinPiscine;
			case "jdbcMaxDeclarations":
				return oSiteConfig.jdbcMaxDeclarations;
			case "jdbcMaxDeclarationsParConnexion":
				return oSiteConfig.jdbcMaxDeclarationsParConnexion;
			case "jdbcTempsInactiviteMax":
				return oSiteConfig.jdbcTempsInactiviteMax;
			case "jdbcUrl":
				return oSiteConfig.jdbcUrl;
			case "solrUrl":
				return oSiteConfig.solrUrl;
			case "solrUrlComputate":
				return oSiteConfig.solrUrlComputate;
			case "jetonIdentitePaypal":
				return oSiteConfig.jetonIdentitePaypal;
			case "compteFacebook":
				return oSiteConfig.compteFacebook;
			case "compteTwitter":
				return oSiteConfig.compteTwitter;
			case "compteGooglePlus":
				return oSiteConfig.compteGooglePlus;
			case "compteInstagram":
				return oSiteConfig.compteInstagram;
			case "compteYoutube":
				return oSiteConfig.compteYoutube;
			case "identifiantCanalYoutube":
				return oSiteConfig.identifiantCanalYoutube;
			case "comptePinterest":
				return oSiteConfig.comptePinterest;
			case "compteOpenclipart":
				return oSiteConfig.compteOpenclipart;
			case "compteMail":
				return oSiteConfig.compteMail;
			case "roleAdmin":
				return oSiteConfig.roleAdmin;
			case "mailAdmin":
				return oSiteConfig.mailAdmin;
			case "nombreExecuteurs":
				return oSiteConfig.nombreExecuteurs;
			case "openApiVersion":
				return oSiteConfig.openApiVersion;
			case "apiDescription":
				return oSiteConfig.apiDescription;
			case "apiTitre":
				return oSiteConfig.apiTitre;
			case "apiTermsService":
				return oSiteConfig.apiTermsService;
			case "apiVersion":
				return oSiteConfig.apiVersion;
			case "apiContactMail":
				return oSiteConfig.apiContactMail;
			case "apiLicenceNom":
				return oSiteConfig.apiLicenceNom;
			case "apiLicenceUrl":
				return oSiteConfig.apiLicenceUrl;
			case "apiNomHote":
				return oSiteConfig.apiNomHote;
			case "apiCheminBase":
				return oSiteConfig.apiCheminBase;
			case "vertxServiceAddress":
				return oSiteConfig.vertxServiceAddress;
			case "statiqueUrlBase":
				return oSiteConfig.statiqueUrlBase;
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
				o = attribuerSiteConfig(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSiteConfig(String var, Object val) {
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
