package org.computate.scolaire.frFR.openshift;

import org.computate.scolaire.frFR.cluster.Cluster;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.config.ConfigSite;
import java.lang.Object;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import java.lang.String;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.openshift.OpenshiftTemplate&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class OpenshiftTemplateGen<DEV> extends Object {

	//////////////////
	// requeteSite_ //
	//////////////////

	/**	L'entité « requeteSite_ »
	 *	 is defined as null before being initialized. 
	 */
	protected RequeteSiteFrFR requeteSite_;
	public Couverture<RequeteSiteFrFR> requeteSite_Couverture = new Couverture<RequeteSiteFrFR>().p(this).c(RequeteSiteFrFR.class).var("requeteSite_").o(requeteSite_);

	/**	<br/>L'entité « requeteSite_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.openshift.OpenshiftTemplate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
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
	protected OpenshiftTemplate requeteSite_Init() {
		if(!requeteSite_Couverture.dejaInitialise) {
			_requeteSite_(requeteSite_Couverture);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Couverture.o);
		}
		requeteSite_Couverture.dejaInitialise(true);
		return (OpenshiftTemplate)this;
	}

	//////////////////
	// siteContexte //
	//////////////////

	/**	L'entité « siteContexte »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SiteContexteFrFR(). 
	 */
	protected SiteContexteFrFR siteContexte = new SiteContexteFrFR();
	public Couverture<SiteContexteFrFR> siteContexteCouverture = new Couverture<SiteContexteFrFR>().p(this).c(SiteContexteFrFR.class).var("siteContexte").o(siteContexte);

	/**	<br/>L'entité « siteContexte »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SiteContexteFrFR(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.openshift.OpenshiftTemplate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteContexte">Trouver l'entité siteContexte dans Solr</a>
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
	protected OpenshiftTemplate siteContexteInit() {
		if(!siteContexteCouverture.dejaInitialise) {
			_siteContexte(siteContexte);
		}
		siteContexte.initLoinPourClasse(requeteSite_);
		siteContexteCouverture.dejaInitialise(true);
		return (OpenshiftTemplate)this;
	}

	////////////////
	// configSite //
	////////////////

	/**	L'entité « configSite »
	 *	 is defined as null before being initialized. 
	 */
	protected ConfigSite configSite;
	public Couverture<ConfigSite> configSiteCouverture = new Couverture<ConfigSite>().p(this).c(ConfigSite.class).var("configSite").o(configSite);

	/**	<br/>L'entité « configSite »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.openshift.OpenshiftTemplate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configSite">Trouver l'entité configSite dans Solr</a>
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
	protected OpenshiftTemplate configSiteInit() {
		if(!configSiteCouverture.dejaInitialise) {
			_configSite(configSiteCouverture);
			if(configSite == null)
				setConfigSite(configSiteCouverture.o);
		}
		if(configSite != null)
			configSite.initLoinPourClasse(requeteSite_);
		configSiteCouverture.dejaInitialise(true);
		return (OpenshiftTemplate)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.openshift.OpenshiftTemplate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:appliChemin">Trouver l'entité appliChemin dans Solr</a>
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
	protected OpenshiftTemplate appliCheminInit() {
		if(!appliCheminCouverture.dejaInitialise) {
			_appliChemin(appliCheminCouverture);
			if(appliChemin == null)
				setAppliChemin(appliCheminCouverture.o);
		}
		appliCheminCouverture.dejaInitialise(true);
		return (OpenshiftTemplate)this;
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

	///////////////////////
	// openApiYamlChemin //
	///////////////////////

	/**	L'entité « openApiYamlChemin »
	 *	 is defined as null before being initialized. 
	 */
	protected String openApiYamlChemin;
	public Couverture<String> openApiYamlCheminCouverture = new Couverture<String>().p(this).c(String.class).var("openApiYamlChemin").o(openApiYamlChemin);

	/**	<br/>L'entité « openApiYamlChemin »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.openshift.OpenshiftTemplate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiYamlChemin">Trouver l'entité openApiYamlChemin dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _openApiYamlChemin(Couverture<String> c);

	public String getOpenApiYamlChemin() {
		return openApiYamlChemin;
	}

	public void setOpenApiYamlChemin(String openApiYamlChemin) {
		this.openApiYamlChemin = openApiYamlChemin;
		this.openApiYamlCheminCouverture.dejaInitialise = true;
	}
	protected OpenshiftTemplate openApiYamlCheminInit() {
		if(!openApiYamlCheminCouverture.dejaInitialise) {
			_openApiYamlChemin(openApiYamlCheminCouverture);
			if(openApiYamlChemin == null)
				setOpenApiYamlChemin(openApiYamlCheminCouverture.o);
		}
		openApiYamlCheminCouverture.dejaInitialise(true);
		return (OpenshiftTemplate)this;
	}

	public String solrOpenApiYamlChemin() {
		return openApiYamlChemin;
	}

	public String strOpenApiYamlChemin() {
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

	/**	L'entité « openApiYamlFichier »
	 *	 is defined as null before being initialized. 
	 */
	protected File openApiYamlFichier;
	public Couverture<File> openApiYamlFichierCouverture = new Couverture<File>().p(this).c(File.class).var("openApiYamlFichier").o(openApiYamlFichier);

	/**	<br/>L'entité « openApiYamlFichier »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.openshift.OpenshiftTemplate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:openApiYamlFichier">Trouver l'entité openApiYamlFichier dans Solr</a>
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
	protected OpenshiftTemplate openApiYamlFichierInit() {
		if(!openApiYamlFichierCouverture.dejaInitialise) {
			_openApiYamlFichier(openApiYamlFichierCouverture);
			if(openApiYamlFichier == null)
				setOpenApiYamlFichier(openApiYamlFichierCouverture.o);
		}
		openApiYamlFichierCouverture.dejaInitialise(true);
		return (OpenshiftTemplate)this;
	}

	///////
	// w //
	///////

	/**	L'entité « w »
	 *	 is defined as null before being initialized. 
	 */
	protected ToutEcrivain w;
	public Couverture<ToutEcrivain> wCouverture = new Couverture<ToutEcrivain>().p(this).c(ToutEcrivain.class).var("w").o(w);

	/**	<br/>L'entité « w »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.openshift.OpenshiftTemplate&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:w">Trouver l'entité w dans Solr</a>
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
	protected OpenshiftTemplate wInit() {
		if(!wCouverture.dejaInitialise) {
			_w(wCouverture);
			if(w == null)
				setW(wCouverture.o);
		}
		if(w != null)
			w.initLoinPourClasse(requeteSite_);
		wCouverture.dejaInitialise(true);
		return (OpenshiftTemplate)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseOpenshiftTemplate = false;

	public OpenshiftTemplate initLoinOpenshiftTemplate(RequeteSiteFrFR requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseOpenshiftTemplate) {
			dejaInitialiseOpenshiftTemplate = true;
			initLoinOpenshiftTemplate();
		}
		return (OpenshiftTemplate)this;
	}

	public void initLoinOpenshiftTemplate() {
		initOpenshiftTemplate();
	}

	public void initOpenshiftTemplate() {
		requeteSite_Init();
		siteContexteInit();
		configSiteInit();
		appliCheminInit();
		openApiYamlCheminInit();
		openApiYamlFichierInit();
		wInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinOpenshiftTemplate(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteOpenshiftTemplate(RequeteSiteFrFR requeteSite_) {
		if(siteContexte != null)
			siteContexte.setRequeteSite_(requeteSite_);
		if(configSite != null)
			configSite.setRequeteSite_(requeteSite_);
		if(w != null)
			w.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteOpenshiftTemplate(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirOpenshiftTemplate(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirOpenshiftTemplate(String var) {
		OpenshiftTemplate oOpenshiftTemplate = (OpenshiftTemplate)this;
		switch(var) {
			case "requeteSite_":
				return oOpenshiftTemplate.requeteSite_;
			case "siteContexte":
				return oOpenshiftTemplate.siteContexte;
			case "configSite":
				return oOpenshiftTemplate.configSite;
			case "appliChemin":
				return oOpenshiftTemplate.appliChemin;
			case "openApiYamlChemin":
				return oOpenshiftTemplate.openApiYamlChemin;
			case "openApiYamlFichier":
				return oOpenshiftTemplate.openApiYamlFichier;
			case "w":
				return oOpenshiftTemplate.w;
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
				o = attribuerOpenshiftTemplate(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerOpenshiftTemplate(String var, Object val) {
		OpenshiftTemplate oOpenshiftTemplate = (OpenshiftTemplate)this;
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
					o = definirOpenshiftTemplate(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirOpenshiftTemplate(String var, String val) {
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
		if(!(o instanceof OpenshiftTemplate))
			return false;
		OpenshiftTemplate that = (OpenshiftTemplate)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("OpenshiftTemplate {");
		sb.append(" }");
		return sb.toString();
	}
}
