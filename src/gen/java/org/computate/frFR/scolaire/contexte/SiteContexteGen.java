package org.computate.frFR.scolaire.contexte;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.computate.frFR.scolaire.cluster.Cluster;
import org.computate.frFR.scolaire.config.ConfigSite;
import org.computate.frFR.scolaire.couverture.Couverture;
import org.computate.frFR.scolaire.requete.RequeteSite;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.handler.OAuth2AuthHandler;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SiteContexteGen<DEV> extends Object {

	///////////
	// vertx //
	///////////

	/**	L'entité « vertx »
	 *	 is defined as null before being initialized. 
	 */
	protected Vertx vertx;
	public Couverture<Vertx> vertxCouverture = new Couverture<Vertx>().p(this).c(Vertx.class).var("vertx").o(vertx);

	/**	<br/>L'entité « vertx »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:vertx">Trouver l'entité vertx dans Solr</a>
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
	protected SiteContexte vertxInit() {
		if(!vertxCouverture.dejaInitialise) {
			_vertx(vertxCouverture);
			if(vertx == null)
				setVertx(vertxCouverture.o);
		}
		vertxCouverture.dejaInitialise(true);
		return (SiteContexte)this;
	}

	//////////////////
	// usineRouteur //
	//////////////////

	/**	L'entité « usineRouteur »
	 *	 is defined as null before being initialized. 
	 */
	protected OpenAPI3RouterFactory usineRouteur;
	public Couverture<OpenAPI3RouterFactory> usineRouteurCouverture = new Couverture<OpenAPI3RouterFactory>().p(this).c(OpenAPI3RouterFactory.class).var("usineRouteur").o(usineRouteur);

	/**	<br/>L'entité « usineRouteur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:usineRouteur">Trouver l'entité usineRouteur dans Solr</a>
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
	protected SiteContexte usineRouteurInit() {
		if(!usineRouteurCouverture.dejaInitialise) {
			_usineRouteur(usineRouteurCouverture);
			if(usineRouteur == null)
				setUsineRouteur(usineRouteurCouverture.o);
		}
		usineRouteurCouverture.dejaInitialise(true);
		return (SiteContexte)this;
	}

	//////////////////////
	// gestionnaireAuth //
	//////////////////////

	/**	L'entité « gestionnaireAuth »
	 *	 is defined as null before being initialized. 
	 */
	protected OAuth2AuthHandler gestionnaireAuth;
	public Couverture<OAuth2AuthHandler> gestionnaireAuthCouverture = new Couverture<OAuth2AuthHandler>().p(this).c(OAuth2AuthHandler.class).var("gestionnaireAuth").o(gestionnaireAuth);

	/**	<br/>L'entité « gestionnaireAuth »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:gestionnaireAuth">Trouver l'entité gestionnaireAuth dans Solr</a>
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
	protected SiteContexte gestionnaireAuthInit() {
		if(!gestionnaireAuthCouverture.dejaInitialise) {
			_gestionnaireAuth(gestionnaireAuthCouverture);
			if(gestionnaireAuth == null)
				setGestionnaireAuth(gestionnaireAuthCouverture.o);
		}
		gestionnaireAuthCouverture.dejaInitialise(true);
		return (SiteContexte)this;
	}

	/////////////////////
	// authFournisseur //
	/////////////////////

	/**	L'entité « authFournisseur »
	 *	 is defined as null before being initialized. 
	 */
	protected OAuth2Auth authFournisseur;
	public Couverture<OAuth2Auth> authFournisseurCouverture = new Couverture<OAuth2Auth>().p(this).c(OAuth2Auth.class).var("authFournisseur").o(authFournisseur);

	/**	<br/>L'entité « authFournisseur »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:authFournisseur">Trouver l'entité authFournisseur dans Solr</a>
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
	protected SiteContexte authFournisseurInit() {
		if(!authFournisseurCouverture.dejaInitialise) {
			_authFournisseur(authFournisseurCouverture);
			if(authFournisseur == null)
				setAuthFournisseur(authFournisseurCouverture.o);
		}
		authFournisseurCouverture.dejaInitialise(true);
		return (SiteContexte)this;
	}

	////////////////
	// configSite //
	////////////////

	/**	L'entité « configSite »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut ConfigSite(). 
	 */
	protected ConfigSite configSite = new ConfigSite();
	public Couverture<ConfigSite> configSiteCouverture = new Couverture<ConfigSite>().p(this).c(ConfigSite.class).var("configSite").o(configSite);

	/**	<br/>L'entité « configSite »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut ConfigSite(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:configSite">Trouver l'entité configSite dans Solr</a>
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
	protected SiteContexte configSiteInit() {
		if(!configSiteCouverture.dejaInitialise) {
			_configSite(configSite);
		}
		configSite.initLoinPourClasse(requeteSite_);
		configSiteCouverture.dejaInitialise(true);
		return (SiteContexte)this;
	}

	////////////////
	// jdbcConfig //
	////////////////

	/**	L'entité « jdbcConfig »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut JsonObject(). 
	 */
	protected JsonObject jdbcConfig = new JsonObject();
	public Couverture<JsonObject> jdbcConfigCouverture = new Couverture<JsonObject>().p(this).c(JsonObject.class).var("jdbcConfig").o(jdbcConfig);

	/**	<br/>L'entité « jdbcConfig »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut JsonObject(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jdbcConfig">Trouver l'entité jdbcConfig dans Solr</a>
	 * <br/>
	 * @param jdbcConfig est l'entité déjà construit. 
	 **/
	protected abstract void _jdbcConfig(JsonObject o);

	public JsonObject getJdbcConfig() {
		return jdbcConfig;
	}

	public void setJdbcConfig(JsonObject jdbcConfig) {
		this.jdbcConfig = jdbcConfig;
		this.jdbcConfigCouverture.dejaInitialise = true;
	}
	protected SiteContexte jdbcConfigInit() {
		if(!jdbcConfigCouverture.dejaInitialise) {
			_jdbcConfig(jdbcConfig);
		}
		jdbcConfigCouverture.dejaInitialise(true);
		return (SiteContexte)this;
	}

	///////////////
	// clientSql //
	///////////////

	/**	L'entité « clientSql »
	 *	 is defined as null before being initialized. 
	 */
	protected SQLClient clientSql;
	public Couverture<SQLClient> clientSqlCouverture = new Couverture<SQLClient>().p(this).c(SQLClient.class).var("clientSql").o(clientSql);

	/**	<br/>L'entité « clientSql »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:clientSql">Trouver l'entité clientSql dans Solr</a>
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
	protected SiteContexte clientSqlInit() {
		if(!clientSqlCouverture.dejaInitialise) {
			_clientSql(clientSqlCouverture);
			if(clientSql == null)
				setClientSql(clientSqlCouverture.o);
		}
		clientSqlCouverture.dejaInitialise(true);
		return (SiteContexte)this;
	}

	////////////////
	// clientSolr //
	////////////////

	/**	L'entité « clientSolr »
	 *	 is defined as null before being initialized. 
	 */
	protected HttpSolrClient clientSolr;
	public Couverture<HttpSolrClient> clientSolrCouverture = new Couverture<HttpSolrClient>().p(this).c(HttpSolrClient.class).var("clientSolr").o(clientSolr);

	/**	<br/>L'entité « clientSolr »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:clientSolr">Trouver l'entité clientSolr dans Solr</a>
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
	protected SiteContexte clientSolrInit() {
		if(!clientSolrCouverture.dejaInitialise) {
			_clientSolr(clientSolrCouverture);
			if(clientSolr == null)
				setClientSolr(clientSolrCouverture.o);
		}
		clientSolrCouverture.dejaInitialise(true);
		return (SiteContexte)this;
	}

	/////////////////////////
	// clientSolrComputate //
	/////////////////////////

	/**	L'entité « clientSolrComputate »
	 *	 is defined as null before being initialized. 
	 */
	protected HttpSolrClient clientSolrComputate;
	public Couverture<HttpSolrClient> clientSolrComputateCouverture = new Couverture<HttpSolrClient>().p(this).c(HttpSolrClient.class).var("clientSolrComputate").o(clientSolrComputate);

	/**	<br/>L'entité « clientSolrComputate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:clientSolrComputate">Trouver l'entité clientSolrComputate dans Solr</a>
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
	protected SiteContexte clientSolrComputateInit() {
		if(!clientSolrComputateCouverture.dejaInitialise) {
			_clientSolrComputate(clientSolrComputateCouverture);
			if(clientSolrComputate == null)
				setClientSolrComputate(clientSolrComputateCouverture.o);
		}
		clientSolrComputateCouverture.dejaInitialise(true);
		return (SiteContexte)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:selCryptage">Trouver l'entité selCryptage dans Solr</a>
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
	protected SiteContexte selCryptageInit() {
		if(!selCryptageCouverture.dejaInitialise) {
			_selCryptage(selCryptageCouverture);
			if(selCryptage == null)
				setSelCryptage(selCryptageCouverture.o);
		}
		selCryptageCouverture.dejaInitialise(true);
		return (SiteContexte)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:motDePasseCryptage">Trouver l'entité motDePasseCryptage dans Solr</a>
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
	protected SiteContexte motDePasseCryptageInit() {
		if(!motDePasseCryptageCouverture.dejaInitialise) {
			_motDePasseCryptage(motDePasseCryptageCouverture);
			if(motDePasseCryptage == null)
				setMotDePasseCryptage(motDePasseCryptageCouverture.o);
		}
		motDePasseCryptageCouverture.dejaInitialise(true);
		return (SiteContexte)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:jetonIdentitePaypal">Trouver l'entité jetonIdentitePaypal dans Solr</a>
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
	protected SiteContexte jetonIdentitePaypalInit() {
		if(!jetonIdentitePaypalCouverture.dejaInitialise) {
			_jetonIdentitePaypal(jetonIdentitePaypalCouverture);
			if(jetonIdentitePaypal == null)
				setJetonIdentitePaypal(jetonIdentitePaypalCouverture.o);
		}
		jetonIdentitePaypalCouverture.dejaInitialise(true);
		return (SiteContexte)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:nombreExecuteurs">Trouver l'entité nombreExecuteurs dans Solr</a>
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
	public SiteContexte setNombreExecuteurs(String o) {
		if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))
			this.nombreExecuteurs = Integer.parseInt(o);
		this.nombreExecuteursCouverture.dejaInitialise = true;
		return (SiteContexte)this;
	}
	protected SiteContexte nombreExecuteursInit() {
		if(!nombreExecuteursCouverture.dejaInitialise) {
			_nombreExecuteurs(nombreExecuteursCouverture);
			if(nombreExecuteurs == null)
				setNombreExecuteurs(nombreExecuteursCouverture.o);
		}
		nombreExecuteursCouverture.dejaInitialise(true);
		return (SiteContexte)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.frFR.scolaire.contexte.SiteContexte&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:requeteSite_">Trouver l'entité requeteSite_ dans Solr</a>
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

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseSiteContexte = false;

	public SiteContexte initLoinSiteContexte(RequeteSite requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseSiteContexte) {
			dejaInitialiseSiteContexte = true;
			initLoinSiteContexte();
		}
		return (SiteContexte)this;
	}

	public void initLoinSiteContexte() {
		initSiteContexte();
	}

	public void initSiteContexte() {
		vertxInit();
		usineRouteurInit();
		gestionnaireAuthInit();
		authFournisseurInit();
		configSiteInit();
		jdbcConfigInit();
		clientSqlInit();
		clientSolrInit();
		clientSolrComputateInit();
		selCryptageInit();
		motDePasseCryptageInit();
		jetonIdentitePaypalInit();
		nombreExecuteursInit();
	}

	public void initLoinPourClasse(RequeteSite requeteSite_) {
		initLoinSiteContexte(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteSiteContexte(RequeteSite requeteSite_) {
		configSite.setRequeteSite_(requeteSite_);
	}

	public void requeteSitePourClasse(RequeteSite requeteSite_) {
		requeteSiteSiteContexte(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	public Object obtenirPourClasse(String var) throws Exception {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirSiteContexte(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirSiteContexte(String var) throws Exception {
		SiteContexte oSiteContexte = (SiteContexte)this;
		switch(var) {
			case "vertx":
				return oSiteContexte.vertx;
			case "usineRouteur":
				return oSiteContexte.usineRouteur;
			case "gestionnaireAuth":
				return oSiteContexte.gestionnaireAuth;
			case "authFournisseur":
				return oSiteContexte.authFournisseur;
			case "configSite":
				return oSiteContexte.configSite;
			case "jdbcConfig":
				return oSiteContexte.jdbcConfig;
			case "clientSql":
				return oSiteContexte.clientSql;
			case "clientSolr":
				return oSiteContexte.clientSolr;
			case "clientSolrComputate":
				return oSiteContexte.clientSolrComputate;
			case "selCryptage":
				return oSiteContexte.selCryptage;
			case "motDePasseCryptage":
				return oSiteContexte.motDePasseCryptage;
			case "jetonIdentitePaypal":
				return oSiteContexte.jetonIdentitePaypal;
			case "nombreExecuteurs":
				return oSiteContexte.nombreExecuteurs;
			case "requeteSite_":
				return oSiteContexte.requeteSite_;
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
				o = attribuerSiteContexte(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerSiteContexte(String var, Object val) {
		SiteContexte oSiteContexte = (SiteContexte)this;
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
					o = definirSiteContexte(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirSiteContexte(String var, String val) {
		switch(var) {
			default:
				return null;
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(selCryptage, motDePasseCryptage, jetonIdentitePaypal, nombreExecuteurs);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SiteContexte))
			return false;
		SiteContexte that = (SiteContexte)o;
		return Objects.equals( selCryptage, that.selCryptage )
				&& Objects.equals( motDePasseCryptage, that.motDePasseCryptage )
				&& Objects.equals( jetonIdentitePaypal, that.jetonIdentitePaypal )
				&& Objects.equals( nombreExecuteurs, that.nombreExecuteurs );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SiteContexte {");
		sb.append( "selCryptage: \"" ).append(selCryptage).append( "\"" );
		sb.append( ", motDePasseCryptage: \"" ).append(motDePasseCryptage).append( "\"" );
		sb.append( ", jetonIdentitePaypal: \"" ).append(jetonIdentitePaypal).append( "\"" );
		sb.append( ", nombreExecuteurs: " ).append(nombreExecuteurs);
		sb.append(" }");
		return sb.toString();
	}
}
