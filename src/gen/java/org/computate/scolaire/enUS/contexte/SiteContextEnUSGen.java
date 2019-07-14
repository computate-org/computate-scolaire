package org.computate.scolaire.enUS.contexte;

import org.computate.scolaire.enUS.cluster.Cluster;
import io.vertx.core.Vertx;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.WorkerExecutor;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import java.lang.Object;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import java.lang.String;
import io.vertx.ext.sql.SQLClient;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SiteContextEnUSGen<DEV> extends Object {

	///////////
	// vertx //
	///////////

	/**	L'entité « vertx »
	 *	 is defined as null before being initialized. 
	 */
	protected Vertx vertx;
	public Wrap<Vertx> vertxWrap = new Wrap<Vertx>().p(this).c(Vertx.class).var("vertx").o(vertx);

	/**	<br/>L'entité « vertx »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:vertx">Trouver l'entité vertx dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _vertx(Wrap<Vertx> c);

	public Vertx getVertx() {
		return vertx;
	}

	public void setVertx(Vertx vertx) {
		this.vertx = vertx;
		this.vertxWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS vertxInit() {
		if(!vertxWrap.alreadyInitialized) {
			_vertx(vertxWrap);
			if(vertx == null)
				setVertx(vertxWrap.o);
		}
		vertxWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	//////////////////
	// usineRouteur //
	//////////////////

	/**	L'entité « usineRouteur »
	 *	 is defined as null before being initialized. 
	 */
	protected OpenAPI3RouterFactory usineRouteur;
	public Wrap<OpenAPI3RouterFactory> usineRouteurWrap = new Wrap<OpenAPI3RouterFactory>().p(this).c(OpenAPI3RouterFactory.class).var("usineRouteur").o(usineRouteur);

	/**	<br/>L'entité « usineRouteur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:usineRouteur">Trouver l'entité usineRouteur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _usineRouteur(Wrap<OpenAPI3RouterFactory> c);

	public OpenAPI3RouterFactory getUsineRouteur() {
		return usineRouteur;
	}

	public void setUsineRouteur(OpenAPI3RouterFactory usineRouteur) {
		this.usineRouteur = usineRouteur;
		this.usineRouteurWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS usineRouteurInit() {
		if(!usineRouteurWrap.alreadyInitialized) {
			_usineRouteur(usineRouteurWrap);
			if(usineRouteur == null)
				setUsineRouteur(usineRouteurWrap.o);
		}
		usineRouteurWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	//////////////////////
	// gestionnaireAuth //
	//////////////////////

	/**	L'entité « gestionnaireAuth »
	 *	 is defined as null before being initialized. 
	 */
	protected OAuth2AuthHandler gestionnaireAuth;
	public Wrap<OAuth2AuthHandler> gestionnaireAuthWrap = new Wrap<OAuth2AuthHandler>().p(this).c(OAuth2AuthHandler.class).var("gestionnaireAuth").o(gestionnaireAuth);

	/**	<br/>L'entité « gestionnaireAuth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:gestionnaireAuth">Trouver l'entité gestionnaireAuth dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _gestionnaireAuth(Wrap<OAuth2AuthHandler> c);

	public OAuth2AuthHandler getGestionnaireAuth() {
		return gestionnaireAuth;
	}

	public void setGestionnaireAuth(OAuth2AuthHandler gestionnaireAuth) {
		this.gestionnaireAuth = gestionnaireAuth;
		this.gestionnaireAuthWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS gestionnaireAuthInit() {
		if(!gestionnaireAuthWrap.alreadyInitialized) {
			_gestionnaireAuth(gestionnaireAuthWrap);
			if(gestionnaireAuth == null)
				setGestionnaireAuth(gestionnaireAuthWrap.o);
		}
		gestionnaireAuthWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	/////////////////////
	// authFournisseur //
	/////////////////////

	/**	L'entité « authFournisseur »
	 *	 is defined as null before being initialized. 
	 */
	protected OAuth2Auth authFournisseur;
	public Wrap<OAuth2Auth> authFournisseurWrap = new Wrap<OAuth2Auth>().p(this).c(OAuth2Auth.class).var("authFournisseur").o(authFournisseur);

	/**	<br/>L'entité « authFournisseur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authFournisseur">Trouver l'entité authFournisseur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authFournisseur(Wrap<OAuth2Auth> c);

	public OAuth2Auth getAuthFournisseur() {
		return authFournisseur;
	}

	public void setAuthFournisseur(OAuth2Auth authFournisseur) {
		this.authFournisseur = authFournisseur;
		this.authFournisseurWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS authFournisseurInit() {
		if(!authFournisseurWrap.alreadyInitialized) {
			_authFournisseur(authFournisseurWrap);
			if(authFournisseur == null)
				setAuthFournisseur(authFournisseurWrap.o);
		}
		authFournisseurWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	//////////////////////////
	// executeurTravailleur //
	//////////////////////////

	/**	L'entité « executeurTravailleur »
	 *	 is defined as null before being initialized. 
	 */
	protected WorkerExecutor executeurTravailleur;
	public Wrap<WorkerExecutor> executeurTravailleurWrap = new Wrap<WorkerExecutor>().p(this).c(WorkerExecutor.class).var("executeurTravailleur").o(executeurTravailleur);

	/**	<br/>L'entité « executeurTravailleur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:executeurTravailleur">Trouver l'entité executeurTravailleur dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _executeurTravailleur(Wrap<WorkerExecutor> c);

	public WorkerExecutor getExecuteurTravailleur() {
		return executeurTravailleur;
	}

	public void setExecuteurTravailleur(WorkerExecutor executeurTravailleur) {
		this.executeurTravailleur = executeurTravailleur;
		this.executeurTravailleurWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS executeurTravailleurInit() {
		if(!executeurTravailleurWrap.alreadyInitialized) {
			_executeurTravailleur(executeurTravailleurWrap);
			if(executeurTravailleur == null)
				setExecuteurTravailleur(executeurTravailleurWrap.o);
		}
		executeurTravailleurWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	////////////////
	// siteConfig //
	////////////////

	/**	L'entité « siteConfig »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SiteConfig(). 
	 */
	protected SiteConfig siteConfig = new SiteConfig();
	public Wrap<SiteConfig> siteConfigWrap = new Wrap<SiteConfig>().p(this).c(SiteConfig.class).var("siteConfig").o(siteConfig);

	/**	<br/>L'entité « siteConfig »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SiteConfig(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteConfig">Trouver l'entité siteConfig dans Solr</a>
	 * <br/>
	 * @param siteConfig est l'entité déjà construit. 
	 **/
	protected abstract void _siteConfig(SiteConfig o);

	public SiteConfig getSiteConfig() {
		return siteConfig;
	}

	public void setSiteConfig(SiteConfig siteConfig) {
		this.siteConfig = siteConfig;
		this.siteConfigWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS siteConfigInit() {
		if(!siteConfigWrap.alreadyInitialized) {
			_siteConfig(siteConfig);
		}
		siteConfig.initDeepForClass(null);
		siteConfigWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	////////////////
	// jdbcConfig //
	////////////////

	/**	L'entité « jdbcConfig »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut JsonObject(). 
	 */
	protected JsonObject jdbcConfig = new JsonObject();
	public Wrap<JsonObject> jdbcConfigWrap = new Wrap<JsonObject>().p(this).c(JsonObject.class).var("jdbcConfig").o(jdbcConfig);

	/**	<br/>L'entité « jdbcConfig »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut JsonObject(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:jdbcConfig">Trouver l'entité jdbcConfig dans Solr</a>
	 * <br/>
	 * @param jdbcConfig est l'entité déjà construit. 
	 **/
	protected abstract void _jdbcConfig(JsonObject o);

	public JsonObject getJdbcConfig() {
		return jdbcConfig;
	}

	public void setJdbcConfig(JsonObject jdbcConfig) {
		this.jdbcConfig = jdbcConfig;
		this.jdbcConfigWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS jdbcConfigInit() {
		if(!jdbcConfigWrap.alreadyInitialized) {
			_jdbcConfig(jdbcConfig);
		}
		jdbcConfigWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	///////////////
	// clientSql //
	///////////////

	/**	L'entité « clientSql »
	 *	 is defined as null before being initialized. 
	 */
	protected SQLClient clientSql;
	public Wrap<SQLClient> clientSqlWrap = new Wrap<SQLClient>().p(this).c(SQLClient.class).var("clientSql").o(clientSql);

	/**	<br/>L'entité « clientSql »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:clientSql">Trouver l'entité clientSql dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _clientSql(Wrap<SQLClient> c);

	public SQLClient getClientSql() {
		return clientSql;
	}

	public void setClientSql(SQLClient clientSql) {
		this.clientSql = clientSql;
		this.clientSqlWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS clientSqlInit() {
		if(!clientSqlWrap.alreadyInitialized) {
			_clientSql(clientSqlWrap);
			if(clientSql == null)
				setClientSql(clientSqlWrap.o);
		}
		clientSqlWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	////////////////
	// clientSolr //
	////////////////

	/**	L'entité « clientSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected HttpSolrClient clientSolr;
	public Wrap<HttpSolrClient> clientSolrWrap = new Wrap<HttpSolrClient>().p(this).c(HttpSolrClient.class).var("clientSolr").o(clientSolr);

	/**	<br/>L'entité « clientSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:clientSolr">Trouver l'entité clientSolr dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _clientSolr(Wrap<HttpSolrClient> c);

	public HttpSolrClient getClientSolr() {
		return clientSolr;
	}

	public void setClientSolr(HttpSolrClient clientSolr) {
		this.clientSolr = clientSolr;
		this.clientSolrWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS clientSolrInit() {
		if(!clientSolrWrap.alreadyInitialized) {
			_clientSolr(clientSolrWrap);
			if(clientSolr == null)
				setClientSolr(clientSolrWrap.o);
		}
		clientSolrWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	/////////////////////////
	// clientSolrComputate //
	/////////////////////////

	/**	L'entité « clientSolrComputate »
	 *	 is defined as null before being initialized. 
	 */
	protected HttpSolrClient clientSolrComputate;
	public Wrap<HttpSolrClient> clientSolrComputateWrap = new Wrap<HttpSolrClient>().p(this).c(HttpSolrClient.class).var("clientSolrComputate").o(clientSolrComputate);

	/**	<br/>L'entité « clientSolrComputate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:clientSolrComputate">Trouver l'entité clientSolrComputate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _clientSolrComputate(Wrap<HttpSolrClient> c);

	public HttpSolrClient getClientSolrComputate() {
		return clientSolrComputate;
	}

	public void setClientSolrComputate(HttpSolrClient clientSolrComputate) {
		this.clientSolrComputate = clientSolrComputate;
		this.clientSolrComputateWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS clientSolrComputateInit() {
		if(!clientSolrComputateWrap.alreadyInitialized) {
			_clientSolrComputate(clientSolrComputateWrap);
			if(clientSolrComputate == null)
				setClientSolrComputate(clientSolrComputateWrap.o);
		}
		clientSolrComputateWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	////////////////////////
	// motDePasseCryptage //
	////////////////////////

	/**	L'entité « motDePasseCryptage »
	 *	 is defined as null before being initialized. 
	 */
	protected String motDePasseCryptage;
	public Wrap<String> motDePasseCryptageWrap = new Wrap<String>().p(this).c(String.class).var("motDePasseCryptage").o(motDePasseCryptage);

	/**	<br/>L'entité « motDePasseCryptage »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:motDePasseCryptage">Trouver l'entité motDePasseCryptage dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _motDePasseCryptage(Wrap<String> c);

	public String getMotDePasseCryptage() {
		return motDePasseCryptage;
	}

	public void setMotDePasseCryptage(String motDePasseCryptage) {
		this.motDePasseCryptage = motDePasseCryptage;
		this.motDePasseCryptageWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS motDePasseCryptageInit() {
		if(!motDePasseCryptageWrap.alreadyInitialized) {
			_motDePasseCryptage(motDePasseCryptageWrap);
			if(motDePasseCryptage == null)
				setMotDePasseCryptage(motDePasseCryptageWrap.o);
		}
		motDePasseCryptageWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:nombreExecuteurs">Trouver l'entité nombreExecuteurs dans Solr</a>
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
	public SiteContextEnUS setNombreExecuteurs(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.nombreExecuteurs = Integer.parseInt(o);
		this.nombreExecuteursWrap.alreadyInitialized = true;
		return (SiteContextEnUS)this;
	}
	protected SiteContextEnUS nombreExecuteursInit() {
		if(!nombreExecuteursWrap.alreadyInitialized) {
			_nombreExecuteurs(nombreExecuteursWrap);
			if(nombreExecuteurs == null)
				setNombreExecuteurs(nombreExecuteursWrap.o);
		}
		nombreExecuteursWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
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

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSiteContextEnUS = false;

	public SiteContextEnUS initDeepSiteContextEnUS(SiteRequestEnUS siteRequest_) {
		if(!alreadyInitializedSiteContextEnUS) {
			alreadyInitializedSiteContextEnUS = true;
			initDeepSiteContextEnUS();
		}
		return (SiteContextEnUS)this;
	}

	public void initDeepSiteContextEnUS() {
		initSiteContextEnUS();
	}

	public void initSiteContextEnUS() {
		vertxInit();
		usineRouteurInit();
		gestionnaireAuthInit();
		authFournisseurInit();
		executeurTravailleurInit();
		siteConfigInit();
		jdbcConfigInit();
		clientSqlInit();
		clientSolrInit();
		clientSolrComputateInit();
		motDePasseCryptageInit();
		nombreExecuteursInit();
	}

	public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSiteContextEnUS(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSiteContextEnUS(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSiteContextEnUS(String var) {
		SiteContextEnUS oSiteContextEnUS = (SiteContextEnUS)this;
		switch(var) {
			case "vertx":
				return oSiteContextEnUS.vertx;
			case "usineRouteur":
				return oSiteContextEnUS.usineRouteur;
			case "gestionnaireAuth":
				return oSiteContextEnUS.gestionnaireAuth;
			case "authFournisseur":
				return oSiteContextEnUS.authFournisseur;
			case "executeurTravailleur":
				return oSiteContextEnUS.executeurTravailleur;
			case "siteConfig":
				return oSiteContextEnUS.siteConfig;
			case "jdbcConfig":
				return oSiteContextEnUS.jdbcConfig;
			case "clientSql":
				return oSiteContextEnUS.clientSql;
			case "clientSolr":
				return oSiteContextEnUS.clientSolr;
			case "clientSolrComputate":
				return oSiteContextEnUS.clientSolrComputate;
			case "motDePasseCryptage":
				return oSiteContextEnUS.motDePasseCryptage;
			case "nombreExecuteurs":
				return oSiteContextEnUS.nombreExecuteurs;
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
				o = attributeSiteContextEnUS(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSiteContextEnUS(String var, Object val) {
		SiteContextEnUS oSiteContextEnUS = (SiteContextEnUS)this;
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
					o = definirSiteContextEnUS(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSiteContextEnUS(String var, String val) {
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
		if(!(o instanceof SiteContextEnUS))
			return false;
		SiteContextEnUS that = (SiteContextEnUS)o;
		return true;
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SiteContextEnUS {");
		sb.append(" }");
		return sb.toString();
	}
}
