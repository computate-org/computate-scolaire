package org.computate.scolaire.enUS.contexte;

import org.computate.scolaire.enUS.cluster.Cluster;
import io.vertx.core.Vertx;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.enUS.school.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import io.vertx.core.WorkerExecutor;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import java.lang.Object;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
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

	///////////////////
	// routerFactory //
	///////////////////

	/**	L'entité « routerFactory »
	 *	 is defined as null before being initialized. 
	 */
	protected OpenAPI3RouterFactory routerFactory;
	public Wrap<OpenAPI3RouterFactory> routerFactoryWrap = new Wrap<OpenAPI3RouterFactory>().p(this).c(OpenAPI3RouterFactory.class).var("routerFactory").o(routerFactory);

	/**	<br/>L'entité « routerFactory »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:routerFactory">Trouver l'entité routerFactory dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _routerFactory(Wrap<OpenAPI3RouterFactory> c);

	public OpenAPI3RouterFactory getRouterFactory() {
		return routerFactory;
	}

	public void setRouterFactory(OpenAPI3RouterFactory routerFactory) {
		this.routerFactory = routerFactory;
		this.routerFactoryWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS routerFactoryInit() {
		if(!routerFactoryWrap.alreadyInitialized) {
			_routerFactory(routerFactoryWrap);
			if(routerFactory == null)
				setRouterFactory(routerFactoryWrap.o);
		}
		routerFactoryWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	/////////////////
	// authHandler //
	/////////////////

	/**	L'entité « authHandler »
	 *	 is defined as null before being initialized. 
	 */
	protected OAuth2AuthHandler authHandler;
	public Wrap<OAuth2AuthHandler> authHandlerWrap = new Wrap<OAuth2AuthHandler>().p(this).c(OAuth2AuthHandler.class).var("authHandler").o(authHandler);

	/**	<br/>L'entité « authHandler »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authHandler">Trouver l'entité authHandler dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authHandler(Wrap<OAuth2AuthHandler> c);

	public OAuth2AuthHandler getAuthHandler() {
		return authHandler;
	}

	public void setAuthHandler(OAuth2AuthHandler authHandler) {
		this.authHandler = authHandler;
		this.authHandlerWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS authHandlerInit() {
		if(!authHandlerWrap.alreadyInitialized) {
			_authHandler(authHandlerWrap);
			if(authHandler == null)
				setAuthHandler(authHandlerWrap.o);
		}
		authHandlerWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	//////////////////
	// authProvider //
	//////////////////

	/**	L'entité « authProvider »
	 *	 is defined as null before being initialized. 
	 */
	protected OAuth2Auth authProvider;
	public Wrap<OAuth2Auth> authProviderWrap = new Wrap<OAuth2Auth>().p(this).c(OAuth2Auth.class).var("authProvider").o(authProvider);

	/**	<br/>L'entité « authProvider »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:authProvider">Trouver l'entité authProvider dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _authProvider(Wrap<OAuth2Auth> c);

	public OAuth2Auth getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(OAuth2Auth authProvider) {
		this.authProvider = authProvider;
		this.authProviderWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS authProviderInit() {
		if(!authProviderWrap.alreadyInitialized) {
			_authProvider(authProviderWrap);
			if(authProvider == null)
				setAuthProvider(authProviderWrap.o);
		}
		authProviderWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
	}

	////////////////////
	// workerExecutor //
	////////////////////

	/**	L'entité « workerExecutor »
	 *	 is defined as null before being initialized. 
	 */
	protected WorkerExecutor workerExecutor;
	public Wrap<WorkerExecutor> workerExecutorWrap = new Wrap<WorkerExecutor>().p(this).c(WorkerExecutor.class).var("workerExecutor").o(workerExecutor);

	/**	<br/>L'entité « workerExecutor »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:workerExecutor">Trouver l'entité workerExecutor dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _workerExecutor(Wrap<WorkerExecutor> c);

	public WorkerExecutor getWorkerExecutor() {
		return workerExecutor;
	}

	public void setWorkerExecutor(WorkerExecutor workerExecutor) {
		this.workerExecutor = workerExecutor;
		this.workerExecutorWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS workerExecutorInit() {
		if(!workerExecutorWrap.alreadyInitialized) {
			_workerExecutor(workerExecutorWrap);
			if(workerExecutor == null)
				setWorkerExecutor(workerExecutorWrap.o);
		}
		workerExecutorWrap.alreadyInitialized(true);
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
	// solrClient //
	////////////////

	/**	L'entité « solrClient »
	 *	 is defined as null before being initialized. 
	 */
	protected HttpSolrClient solrClient;
	public Wrap<HttpSolrClient> solrClientWrap = new Wrap<HttpSolrClient>().p(this).c(HttpSolrClient.class).var("solrClient").o(solrClient);

	/**	<br/>L'entité « solrClient »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.contexte.SiteContextEnUS&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:solrClient">Trouver l'entité solrClient dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _solrClient(Wrap<HttpSolrClient> c);

	public HttpSolrClient getSolrClient() {
		return solrClient;
	}

	public void setSolrClient(HttpSolrClient solrClient) {
		this.solrClient = solrClient;
		this.solrClientWrap.alreadyInitialized = true;
	}
	protected SiteContextEnUS solrClientInit() {
		if(!solrClientWrap.alreadyInitialized) {
			_solrClient(solrClientWrap);
			if(solrClient == null)
				setSolrClient(solrClientWrap.o);
		}
		solrClientWrap.alreadyInitialized(true);
		return (SiteContextEnUS)this;
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
		routerFactoryInit();
		authHandlerInit();
		authProviderInit();
		workerExecutorInit();
		siteConfigInit();
		clientSqlInit();
		solrClientInit();
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
			case "routerFactory":
				return oSiteContextEnUS.routerFactory;
			case "authHandler":
				return oSiteContextEnUS.authHandler;
			case "authProvider":
				return oSiteContextEnUS.authProvider;
			case "workerExecutor":
				return oSiteContextEnUS.workerExecutor;
			case "siteConfig":
				return oSiteContextEnUS.siteConfig;
			case "clientSql":
				return oSiteContextEnUS.clientSql;
			case "solrClient":
				return oSiteContextEnUS.solrClient;
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
