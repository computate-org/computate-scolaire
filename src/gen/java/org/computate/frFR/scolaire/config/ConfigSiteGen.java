package org.computate.frFR.scolaire.config;

import java.util.Objects;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.requete.RequeteSite;

/**	
 *	Charge les propriétés dans le fichier de config de l'application dans des champs spécifiques. 
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ConfigSiteGen<DEV> extends Object {

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut RequeteSite(). 
	 */
	protected RequeteSite requeteSite_ = new RequeteSite();
	public Couverture<RequeteSite> requeteSite_Couverture = new Couverture<RequeteSite>().p(this).c(RequeteSite.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut RequeteSite(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param requeteSite_ est l'entité déjà construit. 
	 **/
	protected abstract void _requeteSite_(RequeteSite o);

	public RequeteSite getRequeteSite_() {
		return requeteSite_;
	}

	public void setRequeteSite_(RequeteSite requeteSite_) {
		this.requeteSite_ = requeteSite_;
		this.requeteSite_Couverture.dejaInitialise = true;
	}

	///////////////////
	// siteContexte_ //
	///////////////////

	/**	L'entité « siteContexte_ »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteContexte siteContexte_;
	public Couverture<SiteContexte> siteContexte_Couverture = new Couverture<SiteContexte>().p(this).c(SiteContexte.class).var("siteContexte_").o(siteContexte_);

	/**	<br/>L'entité « siteContexte_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteContexte_">Trouver l'entité siteContexte_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteContexte_(Couverture<SiteContexte> c);

	public SiteContexte getSiteContexte_() {
		return siteContexte_;
	}

	public void setSiteContexte_(SiteContexte siteContexte_) {
		this.siteContexte_ = siteContexte_;
		this.siteContexte_Couverture.dejaInitialise = true;
	}

	//////////////////
	// configChemin //
	//////////////////

	/**	L'entité « configChemin »
	 *	 is defined as null before being initialized. 
	 */
	protected String configChemin;
	public Couverture<String> configCheminCouverture = new Couverture<String>().p(this).c(String.class).var("configChemin").o(configChemin);

	/**	<br/>L'entité « configChemin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configChemin">Trouver l'entité configChemin dans Solr</a>
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
	 *	 is defined as null before being initialized. 
	 */
	protected INIConfiguration config;
	public Couverture<INIConfiguration> configCouverture = new Couverture<INIConfiguration>().p(this).c(INIConfiguration.class).var("config").o(config);

	/**	<br/>L'entité « config »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:config">Trouver l'entité config dans Solr</a>
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
	protected String identifiantSite;
	public Couverture<String> identifiantSiteCouverture = new Couverture<String>().p(this).c(String.class).var("identifiantSite").o(identifiantSite);

	/**	<br/>L'entité « identifiantSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:identifiantSite">Trouver l'entité identifiantSite dans Solr</a>
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
	public Couverture<String> prefixeEchappeCouverture = new Couverture<String>().p(this).c(String.class).var("prefixeEchappe").o(prefixeEchappe);

	/**	<br/>L'entité « prefixeEchappe »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:prefixeEchappe">Trouver l'entité prefixeEchappe dans Solr</a>
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
	public Couverture<String> appliCheminCouverture = new Couverture<String>().p(this).c(String.class).var("appliChemin").o(appliChemin);

	/**	<br/>L'entité « appliChemin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:appliChemin">Trouver l'entité appliChemin dans Solr</a>
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
	public Couverture<String> racineDocumentCouverture = new Couverture<String>().p(this).c(String.class).var("racineDocument").o(racineDocument);

	/**	<br/>L'entité « racineDocument »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:racineDocument">Trouver l'entité racineDocument dans Solr</a>
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
	public Couverture<String> nomEntrepriseCouverture = new Couverture<String>().p(this).c(String.class).var("nomEntreprise").o(nomEntreprise);

	/**	<br/>L'entité « nomEntreprise »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:nomEntreprise">Trouver l'entité nomEntreprise dans Solr</a>
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
	public Couverture<String> nomDomaineCouverture = new Couverture<String>().p(this).c(String.class).var("nomDomaine").o(nomDomaine);

	/**	<br/>L'entité « nomDomaine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:nomDomaine">Trouver l'entité nomDomaine dans Solr</a>
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
	public Couverture<String> siteNomHoteCouverture = new Couverture<String>().p(this).c(String.class).var("siteNomHote").o(siteNomHote);

	/**	<br/>L'entité « siteNomHote »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteNomHote">Trouver l'entité siteNomHote dans Solr</a>
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
	public Couverture<Integer> sitePortCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("sitePort").o(sitePort);

	/**	<br/>L'entité « sitePort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sitePort">Trouver l'entité sitePort dans Solr</a>
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	public Couverture<String> authRoyaumeCouverture = new Couverture<String>().p(this).c(String.class).var("authRoyaume").o(authRoyaume);

	/**	<br/>L'entité « authRoyaume »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authRoyaume">Trouver l'entité authRoyaume dans Solr</a>
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
	public Couverture<String> authRessourceCouverture = new Couverture<String>().p(this).c(String.class).var("authRessource").o(authRessource);

	/**	<br/>L'entité « authRessource »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authRessource">Trouver l'entité authRessource dans Solr</a>
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
	public Couverture<String> authSecretCouverture = new Couverture<String>().p(this).c(String.class).var("authSecret").o(authSecret);

	/**	<br/>L'entité « authSecret »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authSecret">Trouver l'entité authSecret dans Solr</a>
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
	public Couverture<String> authSslRequisCouverture = new Couverture<String>().p(this).c(String.class).var("authSslRequis").o(authSslRequis);

	/**	<br/>L'entité « authSslRequis »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authSslRequis">Trouver l'entité authSslRequis dans Solr</a>
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
	public Couverture<String> sslJksCheminCouverture = new Couverture<String>().p(this).c(String.class).var("sslJksChemin").o(sslJksChemin);

	/**	<br/>L'entité « sslJksChemin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sslJksChemin">Trouver l'entité sslJksChemin dans Solr</a>
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
	public Couverture<String> sslJksMotDePasseCouverture = new Couverture<String>().p(this).c(String.class).var("sslJksMotDePasse").o(sslJksMotDePasse);

	/**	<br/>L'entité « sslJksMotDePasse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:sslJksMotDePasse">Trouver l'entité sslJksMotDePasse dans Solr</a>
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
	public Couverture<String> authUrlCouverture = new Couverture<String>().p(this).c(String.class).var("authUrl").o(authUrl);

	/**	<br/>L'entité « authUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authUrl">Trouver l'entité authUrl dans Solr</a>
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
	// selCryptage //
	/////////////////

	/**	L'entité « selCryptage »
	 *	 is defined as null before being initialized. 
	 */
	protected String selCryptage;
	public Couverture<String> selCryptageCouverture = new Couverture<String>().p(this).c(String.class).var("selCryptage").o(selCryptage);

	/**	<br/>L'entité « selCryptage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:selCryptage">Trouver l'entité selCryptage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _selCryptage(Couverture<String> c);

	public String getSelCryptage() {
		return selCryptage;
	}

	public void setSelCryptage(String selCryptage) {
		this.selCryptage = selCryptage;
		this.selCryptageCouverture.dejaInitialise = true;
	}
	protected ConfigSite selCryptageInit() {
		if(!selCryptageCouverture.dejaInitialise) {
			_selCryptage(selCryptageCouverture);
			if(selCryptage == null)
				setSelCryptage(selCryptageCouverture.o);
		}
		selCryptageCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	public String solrSelCryptage() {
		return selCryptage;
	}

	public String strSelCryptage() {
		return selCryptage == null ? "" : selCryptage;
	}

	public String nomAffichageSelCryptage() {
		return null;
	}

	public String htmTooltipSelCryptage() {
		return null;
	}

	public String htmSelCryptage() {
		return selCryptage == null ? "" : StringEscapeUtils.escapeHtml4(strSelCryptage());
	}

	////////////////////////
	// motDePasseCryptage //
	////////////////////////

	/**	L'entité « motDePasseCryptage »
	 *	 is defined as null before being initialized. 
	 */
	protected String motDePasseCryptage;
	public Couverture<String> motDePasseCryptageCouverture = new Couverture<String>().p(this).c(String.class).var("motDePasseCryptage").o(motDePasseCryptage);

	/**	<br/>L'entité « motDePasseCryptage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:motDePasseCryptage">Trouver l'entité motDePasseCryptage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _motDePasseCryptage(Couverture<String> c);

	public String getMotDePasseCryptage() {
		return motDePasseCryptage;
	}

	public void setMotDePasseCryptage(String motDePasseCryptage) {
		this.motDePasseCryptage = motDePasseCryptage;
		this.motDePasseCryptageCouverture.dejaInitialise = true;
	}
	protected ConfigSite motDePasseCryptageInit() {
		if(!motDePasseCryptageCouverture.dejaInitialise) {
			_motDePasseCryptage(motDePasseCryptageCouverture);
			if(motDePasseCryptage == null)
				setMotDePasseCryptage(motDePasseCryptageCouverture.o);
		}
		motDePasseCryptageCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	public String solrMotDePasseCryptage() {
		return motDePasseCryptage;
	}

	public String strMotDePasseCryptage() {
		return motDePasseCryptage == null ? "" : motDePasseCryptage;
	}

	public String nomAffichageMotDePasseCryptage() {
		return null;
	}

	public String htmTooltipMotDePasseCryptage() {
		return null;
	}

	public String htmMotDePasseCryptage() {
		return motDePasseCryptage == null ? "" : StringEscapeUtils.escapeHtml4(strMotDePasseCryptage());
	}

	/////////////////
	// siteUrlBase //
	/////////////////

	/**	L'entité « siteUrlBase »
	 *	 is defined as null before being initialized. 
	 */
	protected String siteUrlBase;
	public Couverture<String> siteUrlBaseCouverture = new Couverture<String>().p(this).c(String.class).var("siteUrlBase").o(siteUrlBase);

	/**	<br/>L'entité « siteUrlBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteUrlBase">Trouver l'entité siteUrlBase dans Solr</a>
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

	public String solrSiteUrlBase() {
		return siteUrlBase;
	}

	public String strSiteUrlBase() {
		return siteUrlBase == null ? "" : siteUrlBase;
	}

	public String nomAffichageSiteUrlBase() {
		return null;
	}

	public String htmTooltipSiteUrlBase() {
		return null;
	}

	public String htmSiteUrlBase() {
		return siteUrlBase == null ? "" : StringEscapeUtils.escapeHtml4(strSiteUrlBase());
	}

	//////////////////////
	// siteNomAffichage //
	//////////////////////

	/**	L'entité « siteNomAffichage »
	 *	 is defined as null before being initialized. 
	 */
	protected String siteNomAffichage;
	public Couverture<String> siteNomAffichageCouverture = new Couverture<String>().p(this).c(String.class).var("siteNomAffichage").o(siteNomAffichage);

	/**	<br/>L'entité « siteNomAffichage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteNomAffichage">Trouver l'entité siteNomAffichage dans Solr</a>
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
	public Couverture<String> jdbcClassePiloteCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcClassePilote").o(jdbcClassePilote);

	/**	<br/>L'entité « jdbcClassePilote »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcClassePilote">Trouver l'entité jdbcClassePilote dans Solr</a>
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
	public Couverture<String> jdbcUtilisateurCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcUtilisateur").o(jdbcUtilisateur);

	/**	<br/>L'entité « jdbcUtilisateur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcUtilisateur">Trouver l'entité jdbcUtilisateur dans Solr</a>
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
	public Couverture<String> jdbcMotDePasseCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcMotDePasse").o(jdbcMotDePasse);

	/**	<br/>L'entité « jdbcMotDePasse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcMotDePasse">Trouver l'entité jdbcMotDePasse dans Solr</a>
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
	public Couverture<Integer> jdbcTailleMaxPiscineCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcTailleMaxPiscine").o(jdbcTailleMaxPiscine);

	/**	<br/>L'entité « jdbcTailleMaxPiscine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcTailleMaxPiscine">Trouver l'entité jdbcTailleMaxPiscine dans Solr</a>
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	public Couverture<Integer> jdbcTailleInitialePiscineCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcTailleInitialePiscine").o(jdbcTailleInitialePiscine);

	/**	<br/>L'entité « jdbcTailleInitialePiscine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcTailleInitialePiscine">Trouver l'entité jdbcTailleInitialePiscine dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcTailleInitialePiscine(Couverture<Integer> c);

	public Integer getJdbcTailleInitialePiscine() {
		return jdbcTailleInitialePiscine;
	}

	public void setJdbcTailleInitialePiscine(Integer jdbcTailleInitialePiscine) {
		this.jdbcTailleInitialePiscine = jdbcTailleInitialePiscine;
		this.jdbcTailleInitialePiscineCouverture.dejaInitialise = true;
	}
	public ConfigSite setJdbcTailleInitialePiscine(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.jdbcTailleInitialePiscine = Integer.parseInt(o);
		this.jdbcTailleInitialePiscineCouverture.dejaInitialise = true;
		return (ConfigSite)this;
	}
	protected ConfigSite jdbcTailleInitialePiscineInit() {
		if(!jdbcTailleInitialePiscineCouverture.dejaInitialise) {
			_jdbcTailleInitialePiscine(jdbcTailleInitialePiscineCouverture);
			if(jdbcTailleInitialePiscine == null)
				setJdbcTailleInitialePiscine(jdbcTailleInitialePiscineCouverture.o);
		}
		jdbcTailleInitialePiscineCouverture.dejaInitialise(true);
		return (ConfigSite)this;
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
	public Couverture<Integer> jdbcTailleMinPiscineCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcTailleMinPiscine").o(jdbcTailleMinPiscine);

	/**	<br/>L'entité « jdbcTailleMinPiscine »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcTailleMinPiscine">Trouver l'entité jdbcTailleMinPiscine dans Solr</a>
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	public Couverture<Integer> jdbcMaxDeclarationsCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcMaxDeclarations").o(jdbcMaxDeclarations);

	/**	<br/>L'entité « jdbcMaxDeclarations »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcMaxDeclarations">Trouver l'entité jdbcMaxDeclarations dans Solr</a>
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	public Couverture<Integer> jdbcMaxDeclarationsParConnexionCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcMaxDeclarationsParConnexion").o(jdbcMaxDeclarationsParConnexion);

	/**	<br/>L'entité « jdbcMaxDeclarationsParConnexion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcMaxDeclarationsParConnexion">Trouver l'entité jdbcMaxDeclarationsParConnexion dans Solr</a>
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	public Couverture<Integer> jdbcTempsInactiviteMaxCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("jdbcTempsInactiviteMax").o(jdbcTempsInactiviteMax);

	/**	<br/>L'entité « jdbcTempsInactiviteMax »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcTempsInactiviteMax">Trouver l'entité jdbcTempsInactiviteMax dans Solr</a>
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	public Couverture<String> jdbcUrlCouverture = new Couverture<String>().p(this).c(String.class).var("jdbcUrl").o(jdbcUrl);

	/**	<br/>L'entité « jdbcUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcUrl">Trouver l'entité jdbcUrl dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jdbcUrl(Couverture<String> c);

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
		this.jdbcUrlCouverture.dejaInitialise = true;
	}
	protected ConfigSite jdbcUrlInit() {
		if(!jdbcUrlCouverture.dejaInitialise) {
			_jdbcUrl(jdbcUrlCouverture);
			if(jdbcUrl == null)
				setJdbcUrl(jdbcUrlCouverture.o);
		}
		jdbcUrlCouverture.dejaInitialise(true);
		return (ConfigSite)this;
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
	public Couverture<String> solrUrlCouverture = new Couverture<String>().p(this).c(String.class).var("solrUrl").o(solrUrl);

	/**	<br/>L'entité « solrUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:solrUrl">Trouver l'entité solrUrl dans Solr</a>
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
	public Couverture<String> solrUrlComputateCouverture = new Couverture<String>().p(this).c(String.class).var("solrUrlComputate").o(solrUrlComputate);

	/**	<br/>L'entité « solrUrlComputate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:solrUrlComputate">Trouver l'entité solrUrlComputate dans Solr</a>
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
	public Couverture<String> jetonIdentitePaypalCouverture = new Couverture<String>().p(this).c(String.class).var("jetonIdentitePaypal").o(jetonIdentitePaypal);

	/**	<br/>L'entité « jetonIdentitePaypal »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jetonIdentitePaypal">Trouver l'entité jetonIdentitePaypal dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _jetonIdentitePaypal(Couverture<String> c);

	public String getJetonIdentitePaypal() {
		return jetonIdentitePaypal;
	}

	public void setJetonIdentitePaypal(String jetonIdentitePaypal) {
		this.jetonIdentitePaypal = jetonIdentitePaypal;
		this.jetonIdentitePaypalCouverture.dejaInitialise = true;
	}
	protected ConfigSite jetonIdentitePaypalInit() {
		if(!jetonIdentitePaypalCouverture.dejaInitialise) {
			_jetonIdentitePaypal(jetonIdentitePaypalCouverture);
			if(jetonIdentitePaypal == null)
				setJetonIdentitePaypal(jetonIdentitePaypalCouverture.o);
		}
		jetonIdentitePaypalCouverture.dejaInitialise(true);
		return (ConfigSite)this;
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
	public Couverture<String> compteFacebookCouverture = new Couverture<String>().p(this).c(String.class).var("compteFacebook").o(compteFacebook);

	/**	<br/>L'entité « compteFacebook »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteFacebook">Trouver l'entité compteFacebook dans Solr</a>
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
	public Couverture<String> compteTwitterCouverture = new Couverture<String>().p(this).c(String.class).var("compteTwitter").o(compteTwitter);

	/**	<br/>L'entité « compteTwitter »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteTwitter">Trouver l'entité compteTwitter dans Solr</a>
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
	public Couverture<String> compteGooglePlusCouverture = new Couverture<String>().p(this).c(String.class).var("compteGooglePlus").o(compteGooglePlus);

	/**	<br/>L'entité « compteGooglePlus »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteGooglePlus">Trouver l'entité compteGooglePlus dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteGooglePlus(Couverture<String> c);

	public String getCompteGooglePlus() {
		return compteGooglePlus;
	}

	public void setCompteGooglePlus(String compteGooglePlus) {
		this.compteGooglePlus = compteGooglePlus;
		this.compteGooglePlusCouverture.dejaInitialise = true;
	}
	protected ConfigSite compteGooglePlusInit() {
		if(!compteGooglePlusCouverture.dejaInitialise) {
			_compteGooglePlus(compteGooglePlusCouverture);
			if(compteGooglePlus == null)
				setCompteGooglePlus(compteGooglePlusCouverture.o);
		}
		compteGooglePlusCouverture.dejaInitialise(true);
		return (ConfigSite)this;
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
	public Couverture<String> compteInstagramCouverture = new Couverture<String>().p(this).c(String.class).var("compteInstagram").o(compteInstagram);

	/**	<br/>L'entité « compteInstagram »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteInstagram">Trouver l'entité compteInstagram dans Solr</a>
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
	public Couverture<String> compteYoutubeCouverture = new Couverture<String>().p(this).c(String.class).var("compteYoutube").o(compteYoutube);

	/**	<br/>L'entité « compteYoutube »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteYoutube">Trouver l'entité compteYoutube dans Solr</a>
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
	public Couverture<String> identifiantCanalYoutubeCouverture = new Couverture<String>().p(this).c(String.class).var("identifiantCanalYoutube").o(identifiantCanalYoutube);

	/**	<br/>L'entité « identifiantCanalYoutube »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:identifiantCanalYoutube">Trouver l'entité identifiantCanalYoutube dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _identifiantCanalYoutube(Couverture<String> c);

	public String getIdentifiantCanalYoutube() {
		return identifiantCanalYoutube;
	}

	public void setIdentifiantCanalYoutube(String identifiantCanalYoutube) {
		this.identifiantCanalYoutube = identifiantCanalYoutube;
		this.identifiantCanalYoutubeCouverture.dejaInitialise = true;
	}
	protected ConfigSite identifiantCanalYoutubeInit() {
		if(!identifiantCanalYoutubeCouverture.dejaInitialise) {
			_identifiantCanalYoutube(identifiantCanalYoutubeCouverture);
			if(identifiantCanalYoutube == null)
				setIdentifiantCanalYoutube(identifiantCanalYoutubeCouverture.o);
		}
		identifiantCanalYoutubeCouverture.dejaInitialise(true);
		return (ConfigSite)this;
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
	public Couverture<String> comptePinterestCouverture = new Couverture<String>().p(this).c(String.class).var("comptePinterest").o(comptePinterest);

	/**	<br/>L'entité « comptePinterest »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:comptePinterest">Trouver l'entité comptePinterest dans Solr</a>
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
	public Couverture<String> compteOpenclipartCouverture = new Couverture<String>().p(this).c(String.class).var("compteOpenclipart").o(compteOpenclipart);

	/**	<br/>L'entité « compteOpenclipart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteOpenclipart">Trouver l'entité compteOpenclipart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _compteOpenclipart(Couverture<String> c);

	public String getCompteOpenclipart() {
		return compteOpenclipart;
	}

	public void setCompteOpenclipart(String compteOpenclipart) {
		this.compteOpenclipart = compteOpenclipart;
		this.compteOpenclipartCouverture.dejaInitialise = true;
	}
	protected ConfigSite compteOpenclipartInit() {
		if(!compteOpenclipartCouverture.dejaInitialise) {
			_compteOpenclipart(compteOpenclipartCouverture);
			if(compteOpenclipart == null)
				setCompteOpenclipart(compteOpenclipartCouverture.o);
		}
		compteOpenclipartCouverture.dejaInitialise(true);
		return (ConfigSite)this;
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
	public Couverture<String> compteMailCouverture = new Couverture<String>().p(this).c(String.class).var("compteMail").o(compteMail);

	/**	<br/>L'entité « compteMail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:compteMail">Trouver l'entité compteMail dans Solr</a>
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
	public Couverture<String> roleAdminCouverture = new Couverture<String>().p(this).c(String.class).var("roleAdmin").o(roleAdmin);

	/**	<br/>L'entité « roleAdmin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:roleAdmin">Trouver l'entité roleAdmin dans Solr</a>
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
	public Couverture<String> mailAdminCouverture = new Couverture<String>().p(this).c(String.class).var("mailAdmin").o(mailAdmin);

	/**	<br/>L'entité « mailAdmin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:mailAdmin">Trouver l'entité mailAdmin dans Solr</a>
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
	public Couverture<Integer> nombreExecuteursCouverture = new Couverture<Integer>().p(this).c(Integer.class).var("nombreExecuteurs").o(nombreExecuteurs);

	/**	<br/>L'entité « nombreExecuteurs »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:nombreExecuteurs">Trouver l'entité nombreExecuteurs dans Solr</a>
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
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
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
	public Couverture<String> openApiVersionCouverture = new Couverture<String>().p(this).c(String.class).var("openApiVersion").o(openApiVersion);

	/**	<br/>L'entité « openApiVersion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiVersion">Trouver l'entité openApiVersion dans Solr</a>
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
	public Couverture<String> apiDescriptionCouverture = new Couverture<String>().p(this).c(String.class).var("apiDescription").o(apiDescription);

	/**	<br/>L'entité « apiDescription »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiDescription">Trouver l'entité apiDescription dans Solr</a>
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
	public Couverture<String> apiTitreCouverture = new Couverture<String>().p(this).c(String.class).var("apiTitre").o(apiTitre);

	/**	<br/>L'entité « apiTitre »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiTitre">Trouver l'entité apiTitre dans Solr</a>
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
	public Couverture<String> apiTermsServiceCouverture = new Couverture<String>().p(this).c(String.class).var("apiTermsService").o(apiTermsService);

	/**	<br/>L'entité « apiTermsService »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiTermsService">Trouver l'entité apiTermsService dans Solr</a>
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
	public Couverture<String> apiVersionCouverture = new Couverture<String>().p(this).c(String.class).var("apiVersion").o(apiVersion);

	/**	<br/>L'entité « apiVersion »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiVersion">Trouver l'entité apiVersion dans Solr</a>
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
	public Couverture<String> apiContactMailCouverture = new Couverture<String>().p(this).c(String.class).var("apiContactMail").o(apiContactMail);

	/**	<br/>L'entité « apiContactMail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiContactMail">Trouver l'entité apiContactMail dans Solr</a>
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
	public Couverture<String> apiLicenceNomCouverture = new Couverture<String>().p(this).c(String.class).var("apiLicenceNom").o(apiLicenceNom);

	/**	<br/>L'entité « apiLicenceNom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiLicenceNom">Trouver l'entité apiLicenceNom dans Solr</a>
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
	public Couverture<String> apiLicenceUrlCouverture = new Couverture<String>().p(this).c(String.class).var("apiLicenceUrl").o(apiLicenceUrl);

	/**	<br/>L'entité « apiLicenceUrl »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiLicenceUrl">Trouver l'entité apiLicenceUrl dans Solr</a>
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
	public Couverture<String> apiNomHoteCouverture = new Couverture<String>().p(this).c(String.class).var("apiNomHote").o(apiNomHote);

	/**	<br/>L'entité « apiNomHote »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiNomHote">Trouver l'entité apiNomHote dans Solr</a>
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
	public Couverture<String> apiCheminBaseCouverture = new Couverture<String>().p(this).c(String.class).var("apiCheminBase").o(apiCheminBase);

	/**	<br/>L'entité « apiCheminBase »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:apiCheminBase">Trouver l'entité apiCheminBase dans Solr</a>
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

	//////////////////////////
	// vertxServiceAddresse //
	//////////////////////////

	/**	L'entité « vertxServiceAddresse »
	 *	 is defined as null before being initialized. 
	 */
	protected String vertxServiceAddresse;
	public Couverture<String> vertxServiceAddresseCouverture = new Couverture<String>().p(this).c(String.class).var("vertxServiceAddresse").o(vertxServiceAddresse);

	/**	<br/>L'entité « vertxServiceAddresse »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.config.ConfigSite&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:vertxServiceAddresse">Trouver l'entité vertxServiceAddresse dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _vertxServiceAddresse(Couverture<String> c);

	public String getVertxServiceAddresse() {
		return vertxServiceAddresse;
	}

	public void setVertxServiceAddresse(String vertxServiceAddresse) {
		this.vertxServiceAddresse = vertxServiceAddresse;
		this.vertxServiceAddresseCouverture.dejaInitialise = true;
	}
	protected ConfigSite vertxServiceAddresseInit() {
		if(!vertxServiceAddresseCouverture.dejaInitialise) {
			_vertxServiceAddresse(vertxServiceAddresseCouverture);
			if(vertxServiceAddresse == null)
				setVertxServiceAddresse(vertxServiceAddresseCouverture.o);
		}
		vertxServiceAddresseCouverture.dejaInitialise(true);
		return (ConfigSite)this;
	}

	public String solrVertxServiceAddresse() {
		return vertxServiceAddresse;
	}

	public String strVertxServiceAddresse() {
		return vertxServiceAddresse == null ? "" : vertxServiceAddresse;
	}

	public String nomAffichageVertxServiceAddresse() {
		return null;
	}

	public String htmTooltipVertxServiceAddresse() {
		return null;
	}

	public String htmVertxServiceAddresse() {
		return vertxServiceAddresse == null ? "" : StringEscapeUtils.escapeHtml4(strVertxServiceAddresse());
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseConfigSite = false;

	public ConfigSite initLoinConfigSite(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
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
		selCryptageInit();
		motDePasseCryptageInit();
		siteUrlBaseInit();
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
		vertxServiceAddresseInit();
	}

	public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinConfigSite(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteConfigSite(RequeteSite requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteConfigSite(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) throws Exception {
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
	public Object obtenirConfigSite(String var) throws Exception {
		ConfigSite oConfigSite = (ConfigSite)this;
		switch(var) {
			case "requeteSite_":
				return oConfigSite.requeteSite_;
			case "siteContexte_":
				return oConfigSite.siteContexte_;
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
			case "selCryptage":
				return oConfigSite.selCryptage;
			case "motDePasseCryptage":
				return oConfigSite.motDePasseCryptage;
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
			case "jdbcTailleInitialePiscine":
				return oConfigSite.jdbcTailleInitialePiscine;
			case "jdbcTailleMinPiscine":
				return oConfigSite.jdbcTailleMinPiscine;
			case "jdbcMaxDeclarations":
				return oConfigSite.jdbcMaxDeclarations;
			case "jdbcMaxDeclarationsParConnexion":
				return oConfigSite.jdbcMaxDeclarationsParConnexion;
			case "jdbcTempsInactiviteMax":
				return oConfigSite.jdbcTempsInactiviteMax;
			case "jdbcUrl":
				return oConfigSite.jdbcUrl;
			case "solrUrl":
				return oConfigSite.solrUrl;
			case "solrUrlComputate":
				return oConfigSite.solrUrlComputate;
			case "jetonIdentitePaypal":
				return oConfigSite.jetonIdentitePaypal;
			case "compteFacebook":
				return oConfigSite.compteFacebook;
			case "compteTwitter":
				return oConfigSite.compteTwitter;
			case "compteGooglePlus":
				return oConfigSite.compteGooglePlus;
			case "compteInstagram":
				return oConfigSite.compteInstagram;
			case "compteYoutube":
				return oConfigSite.compteYoutube;
			case "identifiantCanalYoutube":
				return oConfigSite.identifiantCanalYoutube;
			case "comptePinterest":
				return oConfigSite.comptePinterest;
			case "compteOpenclipart":
				return oConfigSite.compteOpenclipart;
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
			case "vertxServiceAddresse":
				return oConfigSite.vertxServiceAddresse;
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
		return Objects.hash(configChemin, identifiantSite, prefixeEchappe, appliChemin, racineDocument, nomEntreprise, nomDomaine, siteNomHote, sitePort, authRoyaume, authRessource, authSecret, authSslRequis, sslJksChemin, sslJksMotDePasse, authUrl, selCryptage, motDePasseCryptage, siteUrlBase, siteNomAffichage, jdbcClassePilote, jdbcUtilisateur, jdbcMotDePasse, jdbcTailleMaxPiscine, jdbcTailleInitialePiscine, jdbcTailleMinPiscine, jdbcMaxDeclarations, jdbcMaxDeclarationsParConnexion, jdbcTempsInactiviteMax, jdbcUrl, solrUrl, solrUrlComputate, jetonIdentitePaypal, compteFacebook, compteTwitter, compteGooglePlus, compteInstagram, compteYoutube, identifiantCanalYoutube, comptePinterest, compteOpenclipart, compteMail, roleAdmin, mailAdmin, nombreExecuteurs, openApiVersion, apiDescription, apiTitre, apiTermsService, apiVersion, apiContactMail, apiLicenceNom, apiLicenceUrl, apiNomHote, apiCheminBase, vertxServiceAddresse);
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
		return Objects.equals( configChemin, that.configChemin )
				&& Objects.equals( identifiantSite, that.identifiantSite )
				&& Objects.equals( prefixeEchappe, that.prefixeEchappe )
				&& Objects.equals( appliChemin, that.appliChemin )
				&& Objects.equals( racineDocument, that.racineDocument )
				&& Objects.equals( nomEntreprise, that.nomEntreprise )
				&& Objects.equals( nomDomaine, that.nomDomaine )
				&& Objects.equals( siteNomHote, that.siteNomHote )
				&& Objects.equals( sitePort, that.sitePort )
				&& Objects.equals( authRoyaume, that.authRoyaume )
				&& Objects.equals( authRessource, that.authRessource )
				&& Objects.equals( authSecret, that.authSecret )
				&& Objects.equals( authSslRequis, that.authSslRequis )
				&& Objects.equals( sslJksChemin, that.sslJksChemin )
				&& Objects.equals( sslJksMotDePasse, that.sslJksMotDePasse )
				&& Objects.equals( authUrl, that.authUrl )
				&& Objects.equals( selCryptage, that.selCryptage )
				&& Objects.equals( motDePasseCryptage, that.motDePasseCryptage )
				&& Objects.equals( siteUrlBase, that.siteUrlBase )
				&& Objects.equals( siteNomAffichage, that.siteNomAffichage )
				&& Objects.equals( jdbcClassePilote, that.jdbcClassePilote )
				&& Objects.equals( jdbcUtilisateur, that.jdbcUtilisateur )
				&& Objects.equals( jdbcMotDePasse, that.jdbcMotDePasse )
				&& Objects.equals( jdbcTailleMaxPiscine, that.jdbcTailleMaxPiscine )
				&& Objects.equals( jdbcTailleInitialePiscine, that.jdbcTailleInitialePiscine )
				&& Objects.equals( jdbcTailleMinPiscine, that.jdbcTailleMinPiscine )
				&& Objects.equals( jdbcMaxDeclarations, that.jdbcMaxDeclarations )
				&& Objects.equals( jdbcMaxDeclarationsParConnexion, that.jdbcMaxDeclarationsParConnexion )
				&& Objects.equals( jdbcTempsInactiviteMax, that.jdbcTempsInactiviteMax )
				&& Objects.equals( jdbcUrl, that.jdbcUrl )
				&& Objects.equals( solrUrl, that.solrUrl )
				&& Objects.equals( solrUrlComputate, that.solrUrlComputate )
				&& Objects.equals( jetonIdentitePaypal, that.jetonIdentitePaypal )
				&& Objects.equals( compteFacebook, that.compteFacebook )
				&& Objects.equals( compteTwitter, that.compteTwitter )
				&& Objects.equals( compteGooglePlus, that.compteGooglePlus )
				&& Objects.equals( compteInstagram, that.compteInstagram )
				&& Objects.equals( compteYoutube, that.compteYoutube )
				&& Objects.equals( identifiantCanalYoutube, that.identifiantCanalYoutube )
				&& Objects.equals( comptePinterest, that.comptePinterest )
				&& Objects.equals( compteOpenclipart, that.compteOpenclipart )
				&& Objects.equals( compteMail, that.compteMail )
				&& Objects.equals( roleAdmin, that.roleAdmin )
				&& Objects.equals( mailAdmin, that.mailAdmin )
				&& Objects.equals( nombreExecuteurs, that.nombreExecuteurs )
				&& Objects.equals( openApiVersion, that.openApiVersion )
				&& Objects.equals( apiDescription, that.apiDescription )
				&& Objects.equals( apiTitre, that.apiTitre )
				&& Objects.equals( apiTermsService, that.apiTermsService )
				&& Objects.equals( apiVersion, that.apiVersion )
				&& Objects.equals( apiContactMail, that.apiContactMail )
				&& Objects.equals( apiLicenceNom, that.apiLicenceNom )
				&& Objects.equals( apiLicenceUrl, that.apiLicenceUrl )
				&& Objects.equals( apiNomHote, that.apiNomHote )
				&& Objects.equals( apiCheminBase, that.apiCheminBase )
				&& Objects.equals( vertxServiceAddresse, that.vertxServiceAddresse );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ConfigSite {");
		sb.append( "configChemin: \"" ).append(configChemin).append( "\"" );
		sb.append( ", identifiantSite: \"" ).append(identifiantSite).append( "\"" );
		sb.append( ", prefixeEchappe: \"" ).append(prefixeEchappe).append( "\"" );
		sb.append( ", appliChemin: \"" ).append(appliChemin).append( "\"" );
		sb.append( ", racineDocument: \"" ).append(racineDocument).append( "\"" );
		sb.append( ", nomEntreprise: \"" ).append(nomEntreprise).append( "\"" );
		sb.append( ", nomDomaine: \"" ).append(nomDomaine).append( "\"" );
		sb.append( ", siteNomHote: \"" ).append(siteNomHote).append( "\"" );
		sb.append( ", sitePort: " ).append(sitePort);
		sb.append( ", authRoyaume: \"" ).append(authRoyaume).append( "\"" );
		sb.append( ", authRessource: \"" ).append(authRessource).append( "\"" );
		sb.append( ", authSecret: \"" ).append(authSecret).append( "\"" );
		sb.append( ", authSslRequis: \"" ).append(authSslRequis).append( "\"" );
		sb.append( ", sslJksChemin: \"" ).append(sslJksChemin).append( "\"" );
		sb.append( ", sslJksMotDePasse: \"" ).append(sslJksMotDePasse).append( "\"" );
		sb.append( ", authUrl: \"" ).append(authUrl).append( "\"" );
		sb.append( ", selCryptage: \"" ).append(selCryptage).append( "\"" );
		sb.append( ", motDePasseCryptage: \"" ).append(motDePasseCryptage).append( "\"" );
		sb.append( ", siteUrlBase: \"" ).append(siteUrlBase).append( "\"" );
		sb.append( ", siteNomAffichage: \"" ).append(siteNomAffichage).append( "\"" );
		sb.append( ", jdbcClassePilote: \"" ).append(jdbcClassePilote).append( "\"" );
		sb.append( ", jdbcUtilisateur: \"" ).append(jdbcUtilisateur).append( "\"" );
		sb.append( ", jdbcMotDePasse: \"" ).append(jdbcMotDePasse).append( "\"" );
		sb.append( ", jdbcTailleMaxPiscine: " ).append(jdbcTailleMaxPiscine);
		sb.append( ", jdbcTailleInitialePiscine: " ).append(jdbcTailleInitialePiscine);
		sb.append( ", jdbcTailleMinPiscine: " ).append(jdbcTailleMinPiscine);
		sb.append( ", jdbcMaxDeclarations: " ).append(jdbcMaxDeclarations);
		sb.append( ", jdbcMaxDeclarationsParConnexion: " ).append(jdbcMaxDeclarationsParConnexion);
		sb.append( ", jdbcTempsInactiviteMax: " ).append(jdbcTempsInactiviteMax);
		sb.append( ", jdbcUrl: \"" ).append(jdbcUrl).append( "\"" );
		sb.append( ", solrUrl: \"" ).append(solrUrl).append( "\"" );
		sb.append( ", solrUrlComputate: \"" ).append(solrUrlComputate).append( "\"" );
		sb.append( ", jetonIdentitePaypal: \"" ).append(jetonIdentitePaypal).append( "\"" );
		sb.append( ", compteFacebook: \"" ).append(compteFacebook).append( "\"" );
		sb.append( ", compteTwitter: \"" ).append(compteTwitter).append( "\"" );
		sb.append( ", compteGooglePlus: \"" ).append(compteGooglePlus).append( "\"" );
		sb.append( ", compteInstagram: \"" ).append(compteInstagram).append( "\"" );
		sb.append( ", compteYoutube: \"" ).append(compteYoutube).append( "\"" );
		sb.append( ", identifiantCanalYoutube: \"" ).append(identifiantCanalYoutube).append( "\"" );
		sb.append( ", comptePinterest: \"" ).append(comptePinterest).append( "\"" );
		sb.append( ", compteOpenclipart: \"" ).append(compteOpenclipart).append( "\"" );
		sb.append( ", compteMail: \"" ).append(compteMail).append( "\"" );
		sb.append( ", roleAdmin: \"" ).append(roleAdmin).append( "\"" );
		sb.append( ", mailAdmin: \"" ).append(mailAdmin).append( "\"" );
		sb.append( ", nombreExecuteurs: " ).append(nombreExecuteurs);
		sb.append( ", openApiVersion: \"" ).append(openApiVersion).append( "\"" );
		sb.append( ", apiDescription: \"" ).append(apiDescription).append( "\"" );
		sb.append( ", apiTitre: \"" ).append(apiTitre).append( "\"" );
		sb.append( ", apiTermsService: \"" ).append(apiTermsService).append( "\"" );
		sb.append( ", apiVersion: \"" ).append(apiVersion).append( "\"" );
		sb.append( ", apiContactMail: \"" ).append(apiContactMail).append( "\"" );
		sb.append( ", apiLicenceNom: \"" ).append(apiLicenceNom).append( "\"" );
		sb.append( ", apiLicenceUrl: \"" ).append(apiLicenceUrl).append( "\"" );
		sb.append( ", apiNomHote: \"" ).append(apiNomHote).append( "\"" );
		sb.append( ", apiCheminBase: \"" ).append(apiCheminBase).append( "\"" );
		sb.append( ", vertxServiceAddresse: \"" ).append(vertxServiceAddresse).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
