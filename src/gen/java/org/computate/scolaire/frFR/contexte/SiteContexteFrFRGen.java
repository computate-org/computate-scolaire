package org.computate.scolaire.frFR.contexte;

import java.math.MathContext;
import org.computate.scolaire.frFR.cluster.Cluster;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.WorkerExecutor;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.lang.Object;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.sql.SQLClient;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SiteContexteFrFRGen<DEV> extends Object {

	///////////
	// vertx //
	///////////

	/**	L'entité « vertx »
	 *	 is defined as null before being initialized. 
	 */
	protected Vertx vertx;
	@JsonIgnore
	public Couverture<Vertx> vertxCouverture = new Couverture<Vertx>().p(this).c(Vertx.class).var("vertx").o(vertx);

	/**	<br/>L'entité « vertx »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:vertx">Trouver l'entité vertx dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _vertx(Couverture<Vertx> c);

	public Vertx getVertx() {
		return vertx;
	}

	public void setVertx(Vertx vertx) {
		this.vertx = vertx;
		this.vertxCouverture.dejaInitialise = true;
	}
	protected SiteContexteFrFR vertxInit() {
		if(!vertxCouverture.dejaInitialise) {
			_vertx(vertxCouverture);
			if(vertx == null)
				setVertx(vertxCouverture.o);
		}
		vertxCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	//////////////////
	// usineRouteur //
	//////////////////

	/**	L'entité « usineRouteur »
	 *	 is defined as null before being initialized. 
	 */
	protected OpenAPI3RouterFactory usineRouteur;
	@JsonIgnore
	public Couverture<OpenAPI3RouterFactory> usineRouteurCouverture = new Couverture<OpenAPI3RouterFactory>().p(this).c(OpenAPI3RouterFactory.class).var("usineRouteur").o(usineRouteur);

	/**	<br/>L'entité « usineRouteur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:usineRouteur">Trouver l'entité usineRouteur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _usineRouteur(Couverture<OpenAPI3RouterFactory> c);

	public OpenAPI3RouterFactory getUsineRouteur() {
		return usineRouteur;
	}

	public void setUsineRouteur(OpenAPI3RouterFactory usineRouteur) {
		this.usineRouteur = usineRouteur;
		this.usineRouteurCouverture.dejaInitialise = true;
	}
	protected SiteContexteFrFR usineRouteurInit() {
		if(!usineRouteurCouverture.dejaInitialise) {
			_usineRouteur(usineRouteurCouverture);
			if(usineRouteur == null)
				setUsineRouteur(usineRouteurCouverture.o);
		}
		usineRouteurCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	/////////////
	// routeur //
	/////////////

	/**	L'entité « routeur »
	 *	 is defined as null before being initialized. 
	 */
	protected Router routeur;
	@JsonIgnore
	public Couverture<Router> routeurCouverture = new Couverture<Router>().p(this).c(Router.class).var("routeur").o(routeur);

	/**	<br/>L'entité « routeur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:routeur">Trouver l'entité routeur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _routeur(Couverture<Router> c);

	public Router getRouteur() {
		return routeur;
	}

	public void setRouteur(Router routeur) {
		this.routeur = routeur;
		this.routeurCouverture.dejaInitialise = true;
	}
	protected SiteContexteFrFR routeurInit() {
		if(!routeurCouverture.dejaInitialise) {
			_routeur(routeurCouverture);
			if(routeur == null)
				setRouteur(routeurCouverture.o);
		}
		routeurCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	//////////////////////
	// gestionnaireAuth //
	//////////////////////

	/**	L'entité « gestionnaireAuth »
	 *	 is defined as null before being initialized. 
	 */
	protected OAuth2AuthHandler gestionnaireAuth;
	@JsonIgnore
	public Couverture<OAuth2AuthHandler> gestionnaireAuthCouverture = new Couverture<OAuth2AuthHandler>().p(this).c(OAuth2AuthHandler.class).var("gestionnaireAuth").o(gestionnaireAuth);

	/**	<br/>L'entité « gestionnaireAuth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gestionnaireAuth">Trouver l'entité gestionnaireAuth dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _gestionnaireAuth(Couverture<OAuth2AuthHandler> c);

	public OAuth2AuthHandler getGestionnaireAuth() {
		return gestionnaireAuth;
	}

	public void setGestionnaireAuth(OAuth2AuthHandler gestionnaireAuth) {
		this.gestionnaireAuth = gestionnaireAuth;
		this.gestionnaireAuthCouverture.dejaInitialise = true;
	}
	protected SiteContexteFrFR gestionnaireAuthInit() {
		if(!gestionnaireAuthCouverture.dejaInitialise) {
			_gestionnaireAuth(gestionnaireAuthCouverture);
			if(gestionnaireAuth == null)
				setGestionnaireAuth(gestionnaireAuthCouverture.o);
		}
		gestionnaireAuthCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	/////////////////////
	// authFournisseur //
	/////////////////////

	/**	L'entité « authFournisseur »
	 *	 is defined as null before being initialized. 
	 */
	protected OAuth2Auth authFournisseur;
	@JsonIgnore
	public Couverture<OAuth2Auth> authFournisseurCouverture = new Couverture<OAuth2Auth>().p(this).c(OAuth2Auth.class).var("authFournisseur").o(authFournisseur);

	/**	<br/>L'entité « authFournisseur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authFournisseur">Trouver l'entité authFournisseur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authFournisseur(Couverture<OAuth2Auth> c);

	public OAuth2Auth getAuthFournisseur() {
		return authFournisseur;
	}

	public void setAuthFournisseur(OAuth2Auth authFournisseur) {
		this.authFournisseur = authFournisseur;
		this.authFournisseurCouverture.dejaInitialise = true;
	}
	protected SiteContexteFrFR authFournisseurInit() {
		if(!authFournisseurCouverture.dejaInitialise) {
			_authFournisseur(authFournisseurCouverture);
			if(authFournisseur == null)
				setAuthFournisseur(authFournisseurCouverture.o);
		}
		authFournisseurCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	//////////////////////////
	// executeurTravailleur //
	//////////////////////////

	/**	L'entité « executeurTravailleur »
	 *	 is defined as null before being initialized. 
	 */
	protected WorkerExecutor executeurTravailleur;
	@JsonIgnore
	public Couverture<WorkerExecutor> executeurTravailleurCouverture = new Couverture<WorkerExecutor>().p(this).c(WorkerExecutor.class).var("executeurTravailleur").o(executeurTravailleur);

	/**	<br/>L'entité « executeurTravailleur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:executeurTravailleur">Trouver l'entité executeurTravailleur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _executeurTravailleur(Couverture<WorkerExecutor> c);

	public WorkerExecutor getExecuteurTravailleur() {
		return executeurTravailleur;
	}

	public void setExecuteurTravailleur(WorkerExecutor executeurTravailleur) {
		this.executeurTravailleur = executeurTravailleur;
		this.executeurTravailleurCouverture.dejaInitialise = true;
	}
	protected SiteContexteFrFR executeurTravailleurInit() {
		if(!executeurTravailleurCouverture.dejaInitialise) {
			_executeurTravailleur(executeurTravailleurCouverture);
			if(executeurTravailleur == null)
				setExecuteurTravailleur(executeurTravailleurCouverture.o);
		}
		executeurTravailleurCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	////////////////
	// configSite //
	////////////////

	/**	L'entité « configSite »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ConfigSite(). 
	 */
	protected ConfigSite configSite = new ConfigSite();
	@JsonIgnore
	public Couverture<ConfigSite> configSiteCouverture = new Couverture<ConfigSite>().p(this).c(ConfigSite.class).var("configSite").o(configSite);

	/**	<br/>L'entité « configSite »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ConfigSite(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configSite">Trouver l'entité configSite dans Solr</a>
	 * <br/>
	 * @param configSite est l'entité déjà construit. 
	 **/
	protected abstract void _configSite(ConfigSite o);

	public ConfigSite getConfigSite() {
		return configSite;
	}

	public void setConfigSite(ConfigSite configSite) {
		this.configSite = configSite;
		this.configSiteCouverture.dejaInitialise = true;
	}
	protected SiteContexteFrFR configSiteInit() {
		if(!configSiteCouverture.dejaInitialise) {
			_configSite(configSite);
		}
		configSite.initLoinPourClasse(null);
		configSiteCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	///////////////
	// clientSql //
	///////////////

	/**	L'entité « clientSql »
	 *	 is defined as null before being initialized. 
	 */
	protected SQLClient clientSql;
	@JsonIgnore
	public Couverture<SQLClient> clientSqlCouverture = new Couverture<SQLClient>().p(this).c(SQLClient.class).var("clientSql").o(clientSql);

	/**	<br/>L'entité « clientSql »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:clientSql">Trouver l'entité clientSql dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _clientSql(Couverture<SQLClient> c);

	public SQLClient getClientSql() {
		return clientSql;
	}

	public void setClientSql(SQLClient clientSql) {
		this.clientSql = clientSql;
		this.clientSqlCouverture.dejaInitialise = true;
	}
	protected SiteContexteFrFR clientSqlInit() {
		if(!clientSqlCouverture.dejaInitialise) {
			_clientSql(clientSqlCouverture);
			if(clientSql == null)
				setClientSql(clientSqlCouverture.o);
		}
		clientSqlCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	////////////////
	// clientSolr //
	////////////////

	/**	L'entité « clientSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected HttpSolrClient clientSolr;
	@JsonIgnore
	public Couverture<HttpSolrClient> clientSolrCouverture = new Couverture<HttpSolrClient>().p(this).c(HttpSolrClient.class).var("clientSolr").o(clientSolr);

	/**	<br/>L'entité « clientSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:clientSolr">Trouver l'entité clientSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _clientSolr(Couverture<HttpSolrClient> c);

	public HttpSolrClient getClientSolr() {
		return clientSolr;
	}

	public void setClientSolr(HttpSolrClient clientSolr) {
		this.clientSolr = clientSolr;
		this.clientSolrCouverture.dejaInitialise = true;
	}
	protected SiteContexteFrFR clientSolrInit() {
		if(!clientSolrCouverture.dejaInitialise) {
			_clientSolr(clientSolrCouverture);
			if(clientSolr == null)
				setClientSolr(clientSolrCouverture.o);
		}
		clientSolrCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	/////////////////////////
	// clientSolrComputate //
	/////////////////////////

	/**	L'entité « clientSolrComputate »
	 *	 is defined as null before being initialized. 
	 */
	protected HttpSolrClient clientSolrComputate;
	@JsonIgnore
	public Couverture<HttpSolrClient> clientSolrComputateCouverture = new Couverture<HttpSolrClient>().p(this).c(HttpSolrClient.class).var("clientSolrComputate").o(clientSolrComputate);

	/**	<br/>L'entité « clientSolrComputate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.frFR.contexte.SiteContexteFrFR&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:clientSolrComputate">Trouver l'entité clientSolrComputate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _clientSolrComputate(Couverture<HttpSolrClient> c);

	public HttpSolrClient getClientSolrComputate() {
		return clientSolrComputate;
	}

	public void setClientSolrComputate(HttpSolrClient clientSolrComputate) {
		this.clientSolrComputate = clientSolrComputate;
		this.clientSolrComputateCouverture.dejaInitialise = true;
	}
	protected SiteContexteFrFR clientSolrComputateInit() {
		if(!clientSolrComputateCouverture.dejaInitialise) {
			_clientSolrComputate(clientSolrComputateCouverture);
			if(clientSolrComputate == null)
				setClientSolrComputate(clientSolrComputateCouverture.o);
		}
		clientSolrComputateCouverture.dejaInitialise(true);
		return (SiteContexteFrFR)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseSiteContexteFrFR = false;

	public SiteContexteFrFR initLoinSiteContexteFrFR(RequeteSiteFrFR requeteSite_) {
		if(!dejaInitialiseSiteContexteFrFR) {
			dejaInitialiseSiteContexteFrFR = true;
			initLoinSiteContexteFrFR();
		}
		return (SiteContexteFrFR)this;
	}

	public void initLoinSiteContexteFrFR() {
		initSiteContexteFrFR();
	}

	public void initSiteContexteFrFR() {
		vertxInit();
		usineRouteurInit();
		routeurInit();
		gestionnaireAuthInit();
		authFournisseurInit();
		executeurTravailleurInit();
		configSiteInit();
		clientSqlInit();
		clientSolrInit();
		clientSolrComputateInit();
	}

	public void initLoinPourClasse(RequeteSiteFrFR requeteSite_) {
		initLoinSiteContexteFrFR(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSiteContexteFrFR(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirSiteContexteFrFR(String var) {
		SiteContexteFrFR oSiteContexteFrFR = (SiteContexteFrFR)this;
		switch(var) {
			case "vertx":
				return oSiteContexteFrFR.vertx;
			case "usineRouteur":
				return oSiteContexteFrFR.usineRouteur;
			case "routeur":
				return oSiteContexteFrFR.routeur;
			case "gestionnaireAuth":
				return oSiteContexteFrFR.gestionnaireAuth;
			case "authFournisseur":
				return oSiteContexteFrFR.authFournisseur;
			case "executeurTravailleur":
				return oSiteContexteFrFR.executeurTravailleur;
			case "configSite":
				return oSiteContexteFrFR.configSite;
			case "clientSql":
				return oSiteContexteFrFR.clientSql;
			case "clientSolr":
				return oSiteContexteFrFR.clientSolr;
			case "clientSolrComputate":
				return oSiteContexteFrFR.clientSolrComputate;
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
				o = attribuerSiteContexteFrFR(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSiteContexteFrFR(String var, Object val) {
		SiteContexteFrFR oSiteContexteFrFR = (SiteContexteFrFR)this;
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
					o = definirSiteContexteFrFR(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSiteContexteFrFR(String var, String val) {
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
		if(!(o instanceof SiteContexteFrFR))
			return false;
		SiteContexteFrFR that = (SiteContexteFrFR)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SiteContexteFrFR { ");
		sb.append(" }");
		return sb.toString();
	}
}
