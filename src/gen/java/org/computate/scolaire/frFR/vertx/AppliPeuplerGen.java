package org.computate.scolaire.frFR.vertx;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import java.util.HashMap;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.apache.commons.lang3.StringUtils;
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
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Exception;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.lang.Object;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliPeupler&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr. </a>
 * <br/>
 **/
public abstract class AppliPeuplerGen<DEV> extends Object {
	protected static final Logger LOGGER = LoggerFactory.getLogger(AppliPeupler.class);

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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliPeupler&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _requeteSite_(Couverture<RequeteSiteFrFR> c) throws Exception, Exception;

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
	protected AppliPeupler requeteSite_Init() throws Exception {
		if(!requeteSite_Couverture.dejaInitialise) {
			_requeteSite_(requeteSite_Couverture);
			if(requeteSite_ == null)
				setRequeteSite_(requeteSite_Couverture.o);
		}
		requeteSite_Couverture.dejaInitialise(true);
		return (AppliPeupler)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliPeupler&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:siteContexte">Trouver l'entité siteContexte dans Solr</a>
	 * <br/>
	 * @param siteContexte est l'entité déjà construit. 
	 **/
	protected abstract void _siteContexte(SiteContexteFrFR o) throws Exception, Exception;

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
	protected AppliPeupler siteContexteInit() throws Exception {
		if(!siteContexteCouverture.dejaInitialise) {
			_siteContexte(siteContexte);
		}
		siteContexte.initLoinPourClasse(requeteSite_);
		siteContexteCouverture.dejaInitialise(true);
		return (AppliPeupler)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.vertx.AppliPeupler&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configSite">Trouver l'entité configSite dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _configSite(Couverture<ConfigSite> c) throws Exception, Exception;

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
	protected AppliPeupler configSiteInit() throws Exception {
		if(!configSiteCouverture.dejaInitialise) {
			_configSite(configSiteCouverture);
			if(configSite == null)
				setConfigSite(configSiteCouverture.o);
		}
		if(configSite != null)
			configSite.initLoinPourClasse(requeteSite_);
		configSiteCouverture.dejaInitialise(true);
		return (AppliPeupler)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseAppliPeupler = false;

	public AppliPeupler initLoinAppliPeupler(RequeteSiteFrFR requeteSite_) throws Exception {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseAppliPeupler) {
			dejaInitialiseAppliPeupler = true;
			initLoinAppliPeupler();
		}
		return (AppliPeupler)this;
	}

	public void initLoinAppliPeupler() throws Exception {
		initAppliPeupler();
	}

	public void initAppliPeupler() throws Exception {
		requeteSite_Init();
		siteContexteInit();
		configSiteInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) throws Exception {
		initLoinAppliPeupler(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteAppliPeupler(RequeteSiteFrFR requeteSite_) {
	}

	public void requeteSitePourClasse(RequeteSiteFrFR requeteSite_) {
		requeteSiteAppliPeupler(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirAppliPeupler(v);
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
	public Object obtenirAppliPeupler(String var) {
		AppliPeupler oAppliPeupler = (AppliPeupler)this;
		switch(var) {
			case "requeteSite_":
				return oAppliPeupler.requeteSite_;
			case "siteContexte":
				return oAppliPeupler.siteContexte;
			case "configSite":
				return oAppliPeupler.configSite;
			default:
				return null;
		}
	}

	///////////////
	// attribuer //
	///////////////

	public boolean attribuerPourClasse(String var, Object val) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerAppliPeupler(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerAppliPeupler(String var, Object val) {
		AppliPeupler oAppliPeupler = (AppliPeupler)this;
		switch(var) {
			default:
				return null;
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSetAppliPeupler(entiteVar,  requeteSite_, o);
	}
	public static Object staticSetAppliPeupler(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return null;
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrAppliPeupler(entiteVar,  requeteSite_, o);
	}
	public static Object staticSolrAppliPeupler(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return null;
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		return staticSolrStrAppliPeupler(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrStrAppliPeupler(String entiteVar, RequeteSiteFrFR requeteSite_, Object o) {
		switch(entiteVar) {
			default:
				return null;
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqPourClasse(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		return staticSolrFqAppliPeupler(entiteVar,  requeteSite_, o);
	}
	public static String staticSolrFqAppliPeupler(String entiteVar, RequeteSiteFrFR requeteSite_, String o) {
		switch(entiteVar) {
			default:
				return null;
		}
	}

	/////////////
	// definir //
	/////////////

	public boolean definirPourClasse(String var, String val) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirAppliPeupler(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAppliPeupler(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	public boolean definirPourClasse(String var, Object val) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirAppliPeupler(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirAppliPeupler(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return null;
		}
	}

	//////////////////
	// requeteApi //
	//////////////////

	public void requeteApiAppliPeupler() {
		RequeteApi requeteApi = Optional.ofNullable(requeteSite_).map(RequeteSiteFrFR::getRequeteApi_).orElse(null);
		Object o = Optional.ofNullable(requeteApi).map(RequeteApi::getOriginal).orElse(null);
		if(o != null && o instanceof AppliPeupler) {
			AppliPeupler original = (AppliPeupler)o;
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
		if(!(o instanceof AppliPeupler))
			return false;
		AppliPeupler that = (AppliPeupler)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AppliPeupler { ");
		sb.append(" }");
		return sb.toString();
	}
}
